d3.queue()
    .defer(d3.json, "/Rossman/data/analytics_input")
    .await(makeGraphs)

function makeGraphs(error, recordsJson) {
    // console.log(error);
    var records = recordsJson;
    var dateFormat = d3.time.format("%Y-%m-%d")

    records.forEach(function(d){
        d["Date"] = dateFormat.parse(d["Date"]);
    });

    //Dimensions
    var ndx = crossfilter(records);

    var dateDim = ndx.dimension(function(d){
        return d["Date"];
    });

    var salesDim = ndx.dimension(function(d){
        return d["Sales"];
    });

    var upper90Dim = ndx.dimension(function(d){
        return d["Upper90"];
    });

    var upper95Dim = ndx.dimension(function(d){
        return d["Upper95"];
    });

    var lower90Dim = ndx.dimension(function(d){
        return d["Lower90"];
    });

    var lower95Dim = ndx.dimension(function(d){
        return d["Lower95"];
    });

    var remarksDim = ndx.dimension(function(d){
        return d["Remarks"];
    });

    var allDim = ndx.dimension(function(d){
        return d;
    });


    //Groups
    var remarksGroup = remarksDim.group();
    var allAnomalous = ndx.groupAll().reduceSum(function(d){	
	if (d.Remarks == "Warning") return 1 ; 
    	else return 0; 
    });

    var num_unique_ids_by_remarks = ndx.groupAll().reduceSum(function(d){	
	if (d.Remarks == "Anomalous") return 1 ; 
    	else return 0; 
    });

    var lowerGroup = dateDim.group().reduceSum(function(d){
        return d["Lower95"];
    });

    var salesGroup = dateDim.group().reduceSum(function(d){
        return d["Sales"];
    });

    var upperGroup = dateDim.group().reduceSum(function(d){
        return d["Upper95"];
    });

    var minDate = dateDim.bottom(1)[0]["Date"];
    var maxDate = dateDim.top(1)[0]["Date"];

    //Charts
    var timeChart = dc.compositeChart("#time-chart");
    var totalSales = dc.numberDisplay("#number-records-nd");
    var totalCustomers = dc.numberDisplay("#number-customers-nd");
    var remarksChart = dc.pieChart("#remarks-chart");
    var barChart = dc.barChart("#bartest");

    totalSales
	.formatNumber(d3.format(","))
        .valueAccessor(function (d) {
    	    return d;
    	})
	.group(num_unique_ids_by_remarks)

    totalCustomers
	.formatNumber(d3.format(","))
        .valueAccessor(function(d){
            return d;
        })
        .group(allAnomalous)

     timeChart
        .width(675)
        .height(225)
        .margins({top:30, right: 30, bottom: 20, left: 70})
	.dimension(dateDim)
        .x(d3.time.scale().domain([minDate, maxDate]))
	.legend(dc.legend().x(70).y(10).itemHeight(13).gap(5))
	.transitionDuration(1000)
	.rangeChart(barChart)
	.brushOn(false)
	.mouseZoomable(true)
	.round(d3.time.month.round)
	.xUnits(d3.time.months)
	.renderHorizontalGridLines(true)
	.compose([
		dc.lineChart(timeChart)
			.group(salesGroup, "Sales")
			.renderArea(true),
		dc.lineChart(timeChart)
			.group(upperGroup, "95% Upper Limit")
			.ordinalColors(["orange"])
			.renderArea(true),
		dc.lineChart(timeChart)
			.group(lowerGroup, "95% Lower Limit")
			.ordinalColors(["orange"])
			.renderArea(true)
	]);

      barChart
          .width(675)
          .height(40)
          .margins({left: 0, top: 0, right: 0, bottom: 20})
          .x(d3.time.scale().domain([minDate, maxDate]))
          .brushOn(true)
          .dimension(dateDim)
          .group(salesGroup);

     remarksChart
	.width(260)
    	.height(250)
    	.dimension(remarksDim)
    	.group(remarksGroup)
	.slicesCap(4)
    	.innerRadius(35)
    	.legend(dc.legend())
    	// workaround for #703: not enough data is accessible through .label() to display percentages
    	.on('pretransition', function(chart) {
        	chart.selectAll('text.pie-slice').text(function(d) {
            	return d.data.key + ' ' + dc.utils.printSingleValue((d.endAngle - d.startAngle) / (2*Math.PI) * 100) + '%';
        	})
	    });

    dc.renderAll();
    console.log("End!");
}
