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


    @RequestMapping(value = sales, method = RequestMethod.GET)
    public Collection<TrainAndStore> sales() throws Exception {
        Collection<TrainAndStore> sales = salesService.getAllSales(); 
        return sales;
    }

}
