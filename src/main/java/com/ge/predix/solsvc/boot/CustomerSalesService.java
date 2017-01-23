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

/**
 * 
 * @author predix -
 */

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ge.predix.solsvc.boot.CustomerSale;

@Service
@SuppressWarnings("unchecked")
@Transactional
public class CustomerSalesService {
public static final String CUSTOMERSALES = "customersales";
	
	@PersistenceContext
    private EntityManager em;

    @Transactional(readOnly = true)
    public List<CustomerSale> getAllCustomerSales() {
        return em.createQuery("FROM CustomerSale").getResultList();
    }
}
