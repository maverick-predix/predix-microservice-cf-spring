/*
 * Copyright (c) 2017 General Electric Company. All rights reserved.
 *
 * The copyright to the computer software herein is the property of
 * General Electric Company. The software may be used and/or copied only
 * with the written permission of General Electric Company or in accordance
 * with the terms and conditions stipulated in the agreement/contract
 * under which the software has been supplied.
 */
 
package com.ge.predix.solsvc.boot;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

//import com.ge.predix.labs.data.jpa.domain.TrainAndStore;
//import com.ge.predix.labs.data.jpa.service.SalesServices;

/**
 * 
 * @author predix -
 */
@ComponentScan
@RestController
public class SalesController {

    @Autowired  private SalesServices salesService;

    public static final String sales = "/sales";
    public static final String salestransformed = "/salestransformed";


    @RequestMapping(value = sales, method = RequestMethod.GET)
    public Collection<TrainAndStore> sales() throws Exception {
        Collection<TrainAndStore> sales = salesService.getAllSales(); 
        return sales;
    }
    
    @RequestMapping(value = salestransformed, method = RequestMethod.GET)
    public String salestransformed() throws Exception {
        Collection<TrainAndStore> sales = salesService.getAllSales(); 
        String result = "{";
        
        String DayOfWeek = "\"DayOfWeek\": [";
        String Customers = "\"Customers\": [";
        String CompetitionOpenSinceMonth = "\"CompetitionOpenSinceMonth\": [";
        String StateHoliday = "\"StateHoliday\": [";
        String StoreName = "\"StoreName\": [";
        String Promo = "\"Promo\": [";
        String CompetitionOpenSinceYear = "\"CompetitionOpenSinceYear\": [";
        String Promo2SinceWeek = "\"Promo2SinceWeek\": [";
        String CompetitionDistance = "\"CompetitionDistance\": [";
        String Sales = "\"Sales\": [";
        String PromoInterval = "\"PromoInterval\": [";
        String Promo2 = "\"Promo2\": [";
        String Promo2SinceYear = "\"Promo2SinceYear\": [";
        String Date = "\"Date\": [";
        String StoreType = "\"StoreType\": [";
        String Assortment = "\"Assortment\": [";
        String Open = "\"Open\": [";
        String SchoolHoliday = "\"SchoolHoliday\": [";
        String Store = "\"Store\": [";

        
        for(TrainAndStore ts : sales){
        	//DayOfWeek += ts.getDayOfWeek() + ", ";
        	DayOfWeek += ts.getDayOfWeek() + ", "; 
        	Customers += ts.getCustomers() + ", ";
        	CompetitionOpenSinceMonth += ts.getCompetitionOpenMonth() + ", ";
        	StateHoliday += ts.getStateHoliday() + ", ";
        	StoreName += "\"" + ts.getStoreName() + "\", ";
        	Promo += ts.getPromo() + ", ";
        	CompetitionOpenSinceYear += ts.getCompetitionOpenYear() + ", ";
        	Promo2SinceWeek += ts.getPromo2Week() + ", ";
        	CompetitionDistance += ts.getCompetitionDistance() + ", ";
        	Sales += ts.getSales() + ", ";
        	PromoInterval += "\"" + ts.getPromoInterval() + "\", ";
        	Promo2 += ts.getPromo2() + ", ";
        	Promo2SinceYear += ts.getPromo2Year() + ", ";
        	Date += "\"" + ts.getSalesDate() + "\", ";
        	StoreType += "\"" + ts.getStoreType() + "\", ";
        	Assortment += "\"" + ts.getAssortment() + "\", ";
        	Open += ts.getOpen() + ", ";
        	SchoolHoliday += ts.getSchoolHoliday() + ", ";
        	Store += ts.getStoreId() + ", ";

        }
        
        DayOfWeek = DayOfWeek.substring(0,DayOfWeek.length()-2);
        Customers = Customers.substring(0,Customers.length()-2);
        CompetitionOpenSinceMonth = CompetitionOpenSinceMonth.substring(0,CompetitionOpenSinceMonth.length()-2);
        StateHoliday = StateHoliday.substring(0,StateHoliday.length()-2);
        StoreName = StoreName.substring(0,StoreName.length()-2);
        Promo = Promo.substring(0,Promo.length()-2);
        CompetitionOpenSinceYear = CompetitionOpenSinceYear.substring(0,CompetitionOpenSinceYear.length()-2);
        Promo2SinceWeek = Promo2SinceWeek.substring(0,Promo2SinceWeek.length()-2);
        CompetitionDistance = CompetitionDistance.substring(0,CompetitionDistance.length()-2);
        Sales = Sales.substring(0,Sales.length()-2);
        PromoInterval = PromoInterval.substring(0,PromoInterval.length()-2);
        Promo2 = Promo2.substring(0,Promo2.length()-2);
        Promo2SinceYear = Promo2SinceYear.substring(0,Promo2SinceYear.length()-2);
        Date = Date.substring(0,Date.length()-2);
        StoreType = StoreType.substring(0,StoreType.length()-2);
        Assortment = Assortment.substring(0,Assortment.length()-2);
        Open = Open.substring(0,Open.length()-2);
        SchoolHoliday = SchoolHoliday.substring(0,SchoolHoliday.length()-2);
        Store = Store.substring(0,Store.length()-2);

        
        //DayOfWeek += "],";
        DayOfWeek += "], ";
        Customers += "], ";
        CompetitionOpenSinceMonth += "], ";
        StateHoliday += "], ";
        StoreName += "], ";
        Promo += "], ";
        CompetitionOpenSinceYear += "], ";
        Promo2SinceWeek += "], ";
        CompetitionDistance += "], ";
        Sales += "], ";
        PromoInterval += "], ";
        Promo2 += "], ";
        Promo2SinceYear += "], ";
        Date += "], ";
        StoreType += "], ";
        Assortment += "], ";
        Open += "], ";
        SchoolHoliday += "], ";
        Store += "] ";

        
        result += DayOfWeek + Customers + CompetitionOpenSinceMonth + StateHoliday + StoreName + Promo + CompetitionOpenSinceYear + Promo2SinceWeek + CompetitionDistance + Sales + PromoInterval + Promo2 + Promo2SinceYear + Date + StoreType + Assortment + Open + SchoolHoliday + Store + "}";
        
        return result;
    }

}
