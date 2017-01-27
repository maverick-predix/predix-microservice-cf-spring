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
