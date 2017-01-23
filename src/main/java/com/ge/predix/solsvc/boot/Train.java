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
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

/**
 * 
 * @author predix -
 */
@Entity
public class Train implements Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = 4780185078379437221L;
	@Id
	@NotNull
	private Integer trainId;
	private Integer storeId;
	private Integer dayOfWeek;
	private Date salesDate;
	private Double sales;
	private Integer customers;
	private Integer open;
	private Integer promo;
	private Integer stateHoliday;
	private Integer schoolHoliday;
	
	public Integer getTrainId() {
		return trainId;
	}
	public void setTrainId(Integer trainId) {
		this.trainId = trainId;
	}
	public Integer getDayOfWeek() {
		return dayOfWeek;
	}
	public void setDayOfWeek(Integer dayOfWeek) {
		this.dayOfWeek = dayOfWeek;
	}
	public Date getSalesDate() {
		return salesDate;
	}
	public void setSalesDate(Date salesDate) {
		this.salesDate = salesDate;
	}
	public Double getSales() {
		return sales;
	}
	public void setSales(Double sales) {
		this.sales = sales;
	}
	public Integer getCustomers() {
		return customers;
	}
	public void setCustomers(Integer customers) {
		this.customers = customers;
	}
	public Integer getOpen() {
		return open;
	}
	public void setOpen(Integer open) {
		this.open = open;
	}
	public Integer getPromo() {
		return promo;
	}
	public void setPromo(Integer promo) {
		this.promo = promo;
	}
	public Integer getStateHoliday() {
		return stateHoliday;
	}
	public void setStateHoliday(Integer stateHoliday) {
		this.stateHoliday = stateHoliday;
	}
	public Integer getSchoolHoliday() {
		return schoolHoliday;
	}
	public void setSchoolHoliday(Integer schoolHoliday) {
		this.schoolHoliday = schoolHoliday;
	}
	public Integer getStoreId() {
		return storeId;
	}
	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}
}
