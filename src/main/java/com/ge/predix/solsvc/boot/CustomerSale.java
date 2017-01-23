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

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @author predix -
 */
@Entity
public class CustomerSale implements Serializable {
	private static final long serialVersionUID = 6836175161826624907L;

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer customerSalesID;

    @NotNull
    private Date salesDate;

    @NotNull
    private String storeName;

    private String promoInterval;
    
    public Integer getCustomerSalesID() {
        return customerSalesID;
    }

    public void setCustomerSalesID(Integer customerSalesID) {
        this.customerSalesID = customerSalesID;
    }
    
    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public Date getSalesDate() {
        return salesDate;
    }

    public void setSalesDate(Date salesDate) {
        this.salesDate = salesDate;
    }

	public String getPromoInterval() {
		return promoInterval;
	}

	public void setPromoInterval(String promoInterval) {
		this.promoInterval = promoInterval;
	}
}
