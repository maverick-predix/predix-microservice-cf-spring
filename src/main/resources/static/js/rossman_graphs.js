$(document).ready(function() {
	$('#loadingModal').modal('show');
//	var url = window.location.href,
//	var url = "https://initial-rossman-predix-jpa-sv-11917.run.aws-usw02-pr.ice.predix.io";
	var url = window.location.href + "sales";
	console.log(url);
	
	d3.queue()
	.defer(d3.json, url)
	.await(makeGraphsEdited)
	
	/*d3.queue()
	.defer(d3.json, url + "/sales")
	.await(makeGraphsEdited)*/

	});

function makeGraphsEdited(error, recordsJson) {
    console.log(error);
    var records = recordsJson;
    console.log(records);
    var dateFormat = d3.time.format("%Y-%m-%d");
    records.forEach(function(d){
    	d["salesDate"] = new Date(moment(d["salesDate"]).format("YYYY-MM-DD"));
    });


    //Dimensions
    var ndx = crossfilter(records);

    var dateDim = ndx.dimension(function(d){
        return d["salesDate"];
    });

    var storeDim = ndx.dimension(function(d){
        return d["storeName"];
    });

    var storeDim2 = ndx.dimension(function(d){
        return d["storeId"];
    });

    var weekdayDim = ndx.dimension(function(d){
        return d["dayOfWeek"];
    });

    var openDim = ndx.dimension(function(d){
        return d["open"];
    });

    var promoDim = ndx.dimension(function(d){
        return d["promo"];
    });

    var stateHolidayDim = ndx.dimension(function(d){
        return d["stateHoliday"];
    });

    var schoolHolidayDim = ndx.dimension(function(d){
        return d["schoolHoliday"];
    });

    var typeDim = ndx.dimension(function(d){
        return d["storeType"];
    });

    var assortmentDim = ndx.dimension(function(d){
        return d["assortment"];
    });

    var promo2Dim = ndx.dimension(function(d){
        return d["promo2"];
    });

    var allDim = ndx.dimension(function(d){
        return d;
    });

    //Groups
    var recordsByDate = dateDim.group().reduceSum(function(d){
        return d["sales"];
    });
    var typeGroup = typeDim.group();
    var assortmentGroup = assortmentDim.group();
    var openGroup = openDim.group().reduceSum(function(d){
        return d["sales"];
    });
    var storeGroup = storeDim.group().reduceSum(function(d){
        return d["sales"];
    });
    var customersByStoreGroup = storeDim.group().reduceSum(function(d){
        return d["customers"];
    });
    var weekdayGroup = weekdayDim.group().reduceSum(function(d){
        return d["sales"];
    });
    var allSales = ndx.groupAll().reduceSum(function(d){
        return d["sales"];
    });
    var allCustomers = ndx.groupAll().reduceSum(function(d){
        return d["customers"];
    });
    var aveSales = ndx.groupAll().reduce(
        function(p, v){
            ++p.count;
            p.total += v["sales"]
            return p;
        },
        function(p, v){
            --p.count;
            p.total -= v["sales"];
            return p;
        },
        function(p, v){
            return {count: 0, total:0}
        }
    );
    var minDate = dateDim.bottom(1)[0]["salesDate"];
    var maxDate = dateDim.top(1)[0]["salesDate"];

    //Charts
    var timeChart = dc.lineChart("#time-chart");
    var typeChart = dc.rowChart("#gender-row-chart");
    var assortmentChart = dc.rowChart("#age-segment-row-chart");
    var top5Chart = dc.rowChart("#location-row-chart");
    var top5ByCustomers = dc.rowChart("#top-stores-customers");
    var totalSales = dc.numberDisplay("#number-records-nd");
    var totalCustomers = dc.numberDisplay("#number-customers-nd");
    var dayOfWeekChart = dc.rowChart("#ave-sales");

    totalSales
        .formatNumber(function(d){
            return "$" + d3.format(",")(d)
        })
        .valueAccessor(function(d){
            return d;
        })
        .group(allSales)

    totalCustomers
        .formatNumber(d3.format(","))
        .valueAccessor(function(d){
            return d;
        })
        .group(allCustomers)

    timeChart
        .width(850)
        .height(162)    
        .margins({top: 10, right: 50, bottom: 20, left: 50})
        .dimension(dateDim)
        .group(recordsByDate)
        .mouseZoomable(true)
        .x(d3.time.scale().domain([minDate, maxDate]))
        .elasticY(true)
        .yAxis().ticks(4);
	//.round(d3.time.month.round)
	//.xUnits(d3.time.months)
	
    typeChart
        .width(300)
        .height(150)
        .dimension(typeDim)
        .group(typeGroup)
        .elasticX(true)
        .xAxis().ticks(4);

    assortmentChart
        .width(300)
        .height(150)
        .dimension(assortmentDim)
        .group(assortmentGroup)
        .elasticX(true)
        .labelOffsetY(10)
        .xAxis().ticks(4);

    dayOfWeekChart
        .width(555)
        .height(150)
        .dimension(weekdayDim)
        .group(weekdayGroup)
        .elasticX(true)
        .labelOffsetY(10)
        .xAxis().ticks(4);

    top5Chart
        .width(555)
        .height(150)
        .dimension(storeDim)
        .group(storeGroup)
        .ordering(function(d) { return -d.value })
        .rowsCap(5)
        .othersGrouper(0)
        .elasticX(true)
        .labelOffsetY(10)
        .xAxis().ticks(4);

    top5ByCustomers
        .width(555)
        .height(150)
        .dimension(storeDim)
        .group(customersByStoreGroup)
        .ordering(function(d) { return -d.value })
        .rowsCap(5)
        .othersGrouper(0)
        .elasticX(true)
        .labelOffsetY(10)
        .xAxis().ticks(4);

    dc.renderAll();
    $('#loadingModal').modal('hide');
    console.log("End!");
}

function makeGraphs(error, recordsJson) {
    console.log(error);
    var records = recordsJson;
    console.log(records);
    var dateFormat = d3.time.format("%Y-%m-%d");

    records.forEach(function(d){
        d["Date"] = dateFormat.parse(d["Date"]);
    });

    //Dimensions
    var ndx = crossfilter(records);

    var dateDim = ndx.dimension(function(d){
        return d["Date"];
    });

    var storeDim = ndx.dimension(function(d){
        return d["StoreName"];
    });

    var storeDim2 = ndx.dimension(function(d){
        return d["Store"];
    });

    var weekdayDim = ndx.dimension(function(d){
        return d["DayOfWeek"];
    });

    var openDim = ndx.dimension(function(d){
        return d["Open"];
    });

    var promoDim = ndx.dimension(function(d){
        return d["Promo"];
    });

    var stateHolidayDim = ndx.dimension(function(d){
        return d["StateHoliday"];
    });

    var schoolHolidayDim = ndx.dimension(function(d){
        return d["SchoolHoliday"];
    });

    var typeDim = ndx.dimension(function(d){
        return d["StoreType"];
    });

    var assortmentDim = ndx.dimension(function(d){
        return d["Assortment"];
    });

    var promo2Dim = ndx.dimension(function(d){
        return d["Promo2"];
    });

    var allDim = ndx.dimension(function(d){
        return d;
    });

    //Groups
    var recordsByDate = dateDim.group().reduceSum(function(d){
        return d["Sales"];
    });
    var typeGroup = typeDim.group();
    var assortmentGroup = assortmentDim.group();
    var openGroup = openDim.group().reduceSum(function(d){
        return d["Sales"];
    });
    var storeGroup = storeDim.group().reduceSum(function(d){
        return d["Sales"];
    });
    var customersByStoreGroup = storeDim.group().reduceSum(function(d){
        return d["Customers"];
    });
    var weekdayGroup = weekdayDim.group().reduceSum(function(d){
        return d["Sales"];
    });
    var allSales = ndx.groupAll().reduceSum(function(d){
        return d["Sales"];
    });
    var allCustomers = ndx.groupAll().reduceSum(function(d){
        return d["Customers"];
    });
    var aveSales = ndx.groupAll().reduce(
        function(p, v){
            ++p.count;
            p.total += v["Sales"]
            return p;
        },
        function(p, v){
            --p.count;
            p.total -= v["Sales"];
            return p;
        },
        function(p, v){
            return {count: 0, total:0}
        }
    );
    var minDate = dateDim.bottom(1)[0]["Date"];
    var maxDate = dateDim.top(1)[0]["Date"];

    //Charts
    var timeChart = dc.barChart("#time-chart");
    var typeChart = dc.rowChart("#gender-row-chart");
    var assortmentChart = dc.rowChart("#age-segment-row-chart");
    var top5Chart = dc.rowChart("#location-row-chart");
    var top5ByCustomers = dc.rowChart("#top-stores-customers");
    var totalSales = dc.numberDisplay("#number-records-nd");
    var totalCustomers = dc.numberDisplay("#number-customers-nd");
    var dayOfWeekChart = dc.rowChart("#ave-sales");

    totalSales
        .formatNumber(function(d){
            return "$" + d3.format(",")(d)
        })
        .valueAccessor(function(d){
            return d;
        })
        .group(allSales)

    totalCustomers
        .formatNumber(d3.format(","))
        .valueAccessor(function(d){
            return d;
        })
        .group(allCustomers)

    timeChart
        .width(850)
        .height(162)
        .margins({top: 10, right: 50, bottom: 20, left: 50})
        .dimension(dateDim)
        .group(recordsByDate)
        .mouseZoomable(true)
        .x(d3.time.scale().domain([minDate, maxDate]))
        .elasticY(true)
        .yAxis().ticks(4);

    typeChart
        .width(300)
        .height(150)
        .dimension(typeDim)
        .group(typeGroup)
        .elasticX(true)
        .xAxis().ticks(4);

    assortmentChart
        .width(300)
        .height(150)
        .dimension(assortmentDim)
        .group(assortmentGroup)
        .elasticX(true)
        .labelOffsetY(10)
        .xAxis().ticks(4);

    dayOfWeekChart
        .width(555)
        .height(150)
        .dimension(weekdayDim)
        .group(weekdayGroup)
        .elasticX(true)
        .labelOffsetY(10)
        .xAxis().ticks(4);

    top5Chart
        .width(555)
        .height(150)
        .dimension(storeDim)
        .group(storeGroup)
        .ordering(function(d) { return -d.value })
        .rowsCap(5)
        .othersGrouper(0)
        .elasticX(true)
        .labelOffsetY(10)
        .xAxis().ticks(4);

    top5ByCustomers
        .width(555)
        .height(150)
        .dimension(storeDim)
        .group(customersByStoreGroup)
        .ordering(function(d) { return -d.value })
        .rowsCap(5)
        .othersGrouper(0)
        .elasticX(true)
        .labelOffsetY(10)
        .xAxis().ticks(4);

    dc.renderAll();
    $('#loadingModal').modal('hide');
    console.log("End!");
}
