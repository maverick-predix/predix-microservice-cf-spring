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
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ge.predix.solsvc.boot.CustomerSale;
import com.ge.predix.solsvc.boot.CustomerSalesService;

/**
 * 
 * @author predix -
 */
@ComponentScan
@RestController
public class CustomerSalesApiController {
	@Autowired  private CustomerSalesService customerSalesService;

    public static final String CUSTOMERSALES = "/customersales";

    @RequestMapping(value = CUSTOMERSALES, method = RequestMethod.GET)
    public Collection<CustomerSale> customersales() throws Exception {
        Collection<CustomerSale> customersales = customerSalesService.getAllCustomerSales();
        return customersales;
    }
}
