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

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

/**
 * 
 * @author predix -
 */
@Entity
public class StoreName implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7032228787020542234L;
	@Id
	@NotNull
	private Integer storeNameId;
	private Integer storeId;
	private String storeName;
	
	public int getStoreNameId() {
		return storeNameId;
	}
	public void setStoreNameId(int storeNameId) {
		this.storeNameId = storeNameId;
	}
	
	public String getStoreName() {
		return storeName;
	}
	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}
	public Integer getStoreId() {
		return storeId;
	}
	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}
	public void setStoreNameId(Integer storeNameId) {
		this.storeNameId = storeNameId;
	}
}
