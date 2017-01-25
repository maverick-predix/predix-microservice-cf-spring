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
public class Store implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7032228787020542234L;
	@Id
	@NotNull
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
	
	public int getStoreId() {
		return storeId;
	}
	public void setStoreId(int storeId) {
		this.storeId = storeId;
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
	public String getStoreType() {
		return storeType;
	}
	public void setStoreType(String storeType) {
		this.storeType = storeType;
	}
}
