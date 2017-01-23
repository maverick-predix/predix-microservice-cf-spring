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

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * 
 * @author predix -
 */
@Entity
public class TrainAndStore {
	
	@Id
	private Integer trainId;
	private Integer dayOfWeek;
	private Date salesDate;
	private Double sales;
	private Integer customers;
	private Integer open;
	private Integer promo;
	private Integer stateHoliday;
	private Integer schoolHoliday;
	private Integer storeId;
	private String storeType;
	private String assortment;
	private Integer competitionDistance;
	private Integer competitionOpenMonth;
	private Integer competitionOpenYear;
	private Integer promo2;
	private Integer promo2Week;
	private Integer promo2Year;
	private String promoInterval;
	private String storeName;
	
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
	public String getStoreType() {
		return storeType;
	}
	public void setStoreType(String storeType) {
		this.storeType = storeType;
	}
	public String getAssortment() {
		return assortment;
	}
	public void setAssortment(String assortment) {
		this.assortment = assortment;
	}
	public Integer getCompetitionDistance() {
		return competitionDistance;
	}
	public void setCompetitionDistance(Integer competitionDistance) {
		this.competitionDistance = competitionDistance;
	}
	public Integer getCompetitionOpenMonth() {
		return competitionOpenMonth;
	}
	public void setCompetitionOpenMonth(Integer competitionOpenMonth) {
		this.competitionOpenMonth = competitionOpenMonth;
	}
	public Integer getCompetitionOpenYear() {
		return competitionOpenYear;
	}
	public void setCompetitionOpenYear(Integer competitionOpenYear) {
		this.competitionOpenYear = competitionOpenYear;
	}
	public Integer getPromo2() {
		return promo2;
	}
	public void setPromo2(Integer promo2) {
		this.promo2 = promo2;
	}
	public Integer getPromo2Week() {
		return promo2Week;
	}
	public void setPromo2Week(Integer promo2Week) {
		this.promo2Week = promo2Week;
	}
	public Integer getPromo2Year() {
		return promo2Year;
	}
	public void setPromo2Year(Integer promo2Year) {
		this.promo2Year = promo2Year;
	}
	public String getPromoInterval() {
		return promoInterval;
	}
	public void setPromoInterval(String promoInterval) {
		this.promoInterval = promoInterval;
	}
	public String getStoreName() {
		return storeName;
	}
	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}
}
