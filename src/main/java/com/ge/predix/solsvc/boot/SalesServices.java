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

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

//import com.ge.predix.labs.data.jpa.domain.TrainAndStore;

/**
 * 
 * @author predix -
 */
@Service
@SuppressWarnings("unchecked")
@Transactional
public class SalesServices {

    public static final String sales = "sales";

    @PersistenceContext
    private EntityManager em;

    public List<TrainAndStore> getAllSales() {
        String sql = "SELECT trainId, t.storeId, storeType, assortment, competitionDistance, competitionOpenMonth, competitionOpenYear, promo2, promo2Week, promo2Year, promoInterval, storeName, dayOfWeek, salesDate, sales, customers, open, promo, stateHoliday, schoolHoliday FROM Train t LEFT JOIN Store s ON t.storeId = s.storeId LEFT JOIN StoreName sn ON s.storeId = sn.storeId WHERE s.storeId <= 115 AND t.open = 1";
        return em.createNativeQuery(sql, TrainAndStore.class)
                .getResultList();
        
    }
}
