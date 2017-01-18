/*
 * Copyright (c) 2017 General Electric Company. All rights reserved.
 *
 * The copyright to the computer software herein is the property of
 * General Electric Company. The software may be used and/or copied only
 * with the written permission of General Electric Company or in accordance
 * with the terms and conditions stipulated in the agreement/contract
 * under which the software has been supplied.
 */
 
package com.ge.predix.solsvc.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.apache.http.Header;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.json.JsonParser;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ge.predix.solsvc.restclient.impl.RestClient;

/**
 * 
 * @author Pointwest-SMG
 */
@SuppressWarnings({"javadoc" , "nls"})
@PropertySource("classpath:application-default.properties")
@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public class AnalyticsController {

	public AnalyticsController() {
        super();
    }
	
	@Autowired
	private RestClient restClient;
	
	@Autowired
	private JsonParser parser;
	
	@Value("${predix.analytics.zoneId}")
	private String zoneId;
	
	@Value("${predix.analytics.catalogId.manipulation}")
	private String catalogIdOfManipulation;
	
	@Value("${predix.analytics.catalogId.xgboost}")
	private String catalogIdOfXgboost;
	
	@Value("${predix.analytics.catalogId.rfv1}")
	private String catalogIdOfRfv1;
	
	@Value("${predix.analytics.catalogId.rfv2}")
	private String catalogIdOfRfv2;
	
	private String getAnalyticServiceUrl(String catalogId) {
		return "https://predix-analytics-catalog-release.run.aws-usw02-pr.ice.predix.io/api/v1/catalog/analytics/" 
				+ catalogId + "/execution";
	}
	
	@RequestMapping("/analyticsList")
	public String getListOfAnalytics() throws IOException {
		List<Header> headers = this.restClient.getSecureTokenForClientId();
		
		this.restClient.addZoneToHeaders(headers, this.zoneId);
		
		String url = "https://predix-analytics-catalog-release.run.aws-usw02-pr.ice.predix.io/api/v1/catalog/analytics";
		CloseableHttpResponse response = this.restClient.get(url, headers, 0, 0);
		
        System.out.println(response.getStatusLine());

        try {
            return EntityUtils.toString(response.getEntity());
        } finally {
            response.close();
        }
        
	}
	
	@RequestMapping("/manipulation")
	public String getManipulation(@RequestParam("body") String body) throws IOException {		
		List<Header> headers = this.restClient.getSecureTokenForClientId();
		
		this.restClient.addZoneToHeaders(headers, this.zoneId);
		String url = getAnalyticServiceUrl(this.catalogIdOfManipulation);
		//temp initiation while waiting for data service
		if(body == null || body == "") {
			body = "{\"Date\" : [\"1/1/2013\", \"1/2/2013\", \"1/3/2013\", \"1/4/2013\", \"1/5/2013\", \"1/6/2013\", \"1/7/2013\", \"1/8/2013\", \"1/9/2013\", \"1/10/2013\", \"1/11/2013\", \"1/12/2013\", \"1/13/2013\", \"1/14/2013\", \"1/15/2013\", \"1/16/2013\", \"1/17/2013\", \"1/18/2013\", \"1/19/2013\", \"1/20/2013\", \"1/21/2013\", \"1/22/2013\", \"1/23/2013\", \"1/24/2013\", \"1/25/2013\", \"1/26/2013\", \"1/27/2013\", \"1/28/2013\", \"1/29/2013\", \"1/30/2013\"], \"Sales\" : [3210, 788536, 621409, 655280, 589744, 10509, 1079801, 867423, 774044, 774251, 800499, 598249, 10584, 579521, 550419, 529285, 535156, 604567, 592632, 11390, 864293, 820618, 774627, 743825, 773061, 576890, 7522, 580659, 534087, 583869], \"Customers\" : [619, 80215, 72868, 74653, 66555, 1509, 103230, 87496, 79294, 82316, 85125, 67150, 1499, 70787, 66798, 64089, 64496, 70325, 65235, 1536, 85252, 83029, 79889, 78587, 81700, 63917, 1049, 71018, 64496, 67203], \"Open\" : [1, 114, 114, 114, 114, 1, 114, 114, 114, 114, 114, 114, 1, 114, 114, 114, 114, 114, 114, 1, 114, 114, 114, 114, 114, 114, 1, 114, 114, 114], \"Promo\" : [0, 0, 0, 0, 0, 0, 114, 114, 114, 114, 114, 0, 0, 0, 0, 0, 0, 0, 0, 0, 114, 114, 114, 114, 114, 0, 0, 0, 0, 0], \"SchoolHoliday\" : [1, 114, 106, 106, 5, 0, 5, 5, 5, 5, 5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0], \"Promo2\" : [0, 60, 60, 60, 60, 0, 60, 60, 60, 60, 60, 60, 0, 60, 60, 60, 60, 60, 60, 0, 60, 60, 60, 60, 60, 60, 0, 60, 60, 60]}";
		}
		CloseableHttpResponse response = this.restClient.post(url, body, headers);
		
		System.out.println(response.getStatusLine());
		String output="unprocessed";
		
		try {
			Map<String,Object> resMap = this.parser.parseMap(EntityUtils.toString(response.getEntity()));
			output = resMap.get("result").toString();
        } finally {
            response.close();
        }
		return output;
	}
	
	@RequestMapping("/xgboost")
	public String getXgboost(@RequestParam("body") String body) throws IOException {		
		List<Header> headers = this.restClient.getSecureTokenForClientId();
		
		this.restClient.addZoneToHeaders(headers, this.zoneId);
		String url = getAnalyticServiceUrl(this.catalogIdOfXgboost);
		//temp initiation while waiting for data service
		if(body == null || body == "") {
			body = "{\"January\": [1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1], \"Thursday\": [0, 0, 1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0], \"Monday\": [0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1, 0, 0], \"Promo2\": [0, 60, 60, 60, 60, 0, 60, 60, 60, 60, 60, 60, 0, 60, 60, 60, 60, 60, 60, 0, 60, 60, 60, 60, 60, 60, 0, 60, 60, 60], \"Date\": [\"2013-01-01\", \"2013-01-02\", \"2013-01-03\", \"2013-01-04\", \"2013-01-05\", \"2013-01-06\", \"2013-01-07\", \"2013-01-08\", \"2013-01-09\", \"2013-01-10\", \"2013-01-11\", \"2013-01-12\", \"2013-01-13\", \"2013-01-14\", \"2013-01-15\", \"2013-01-16\", \"2013-01-17\", \"2013-01-18\", \"2013-01-19\", \"2013-01-20\", \"2013-01-21\", \"2013-01-22\", \"2013-01-23\", \"2013-01-24\", \"2013-01-25\", \"2013-01-26\", \"2013-01-27\", \"2013-01-28\", \"2013-01-29\", \"2013-01-30\"], \"Customers\": [619, 80215, 72868, 74653, 66555, 1509, 103230, 87496, 79294, 82316, 85125, 67150, 1499, 70787, 66798, 64089, 64496, 70325, 65235, 1536, 85252, 83029, 79889, 78587, 81700, 63917, 1049, 71018, 64496, 67203], \"Saturday\": [0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0], \"Promo\": [0, 0, 0, 0, 0, 0, 114, 114, 114, 114, 114, 0, 0, 0, 0, 0, 0, 0, 0, 0, 114, 114, 114, 114, 114, 0, 0, 0, 0, 0], \"Tuesday\": [1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1, 0], \"SchoolHoliday\": [1, 114, 106, 106, 5, 0, 5, 5, 5, 5, 5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0], \"Sales\": [4220, 688536, 621409, 655280, 589744, 10509, 1079801, 867423, 774044, 774251, 800499, 598249, 10584, 579521, 550419, 529285, 535156, 604567, 592632, 11390, 864293, 820618, 774627, 743825, 773061, 576890, 7522, 580659, 534087, 583869], \"Friday\": [0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0], \"Sunday\": [0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0], \"Open\": [1, 114, 114, 114, 114, 1, 114, 114, 114, 114, 114, 114, 1, 114, 114, 114, 114, 114, 114, 1, 114, 114, 114, 114, 114, 114, 1, 114, 114, 114], \"Wednesday\": [0, 1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1]}";
		}
		CloseableHttpResponse response = this.restClient.post(url, body, headers);
		
		System.out.println(response.getStatusLine());
		String output="unprocessed";
		
		try {
			Map<String,Object> resMap = this.parser.parseMap(EntityUtils.toString(response.getEntity()));
			output = resMap.get("result").toString();
        } finally {
            response.close();
        }
		return output;
	}
	
	@RequestMapping("/rfv1")
	public String getRfv1(@RequestParam("body") String body) throws IOException {		
		List<Header> headers = this.restClient.getSecureTokenForClientId();
		
		this.restClient.addZoneToHeaders(headers, this.zoneId);
		String url = getAnalyticServiceUrl(this.catalogIdOfRfv1);
		//temp initiation while waiting for data service
		if(body == null || body == "") {
			body = "{\"January\": [1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1], \"Thursday\": [0, 0, 1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0], \"Monday\": [0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1, 0, 0], \"Promo2\": [0, 60, 60, 60, 60, 0, 60, 60, 60, 60, 60, 60, 0, 60, 60, 60, 60, 60, 60, 0, 60, 60, 60, 60, 60, 60, 0, 60, 60, 60], \"Date\": [\"2013-01-01\", \"2013-01-02\", \"2013-01-03\", \"2013-01-04\", \"2013-01-05\", \"2013-01-06\", \"2013-01-07\", \"2013-01-08\", \"2013-01-09\", \"2013-01-10\", \"2013-01-11\", \"2013-01-12\", \"2013-01-13\", \"2013-01-14\", \"2013-01-15\", \"2013-01-16\", \"2013-01-17\", \"2013-01-18\", \"2013-01-19\", \"2013-01-20\", \"2013-01-21\", \"2013-01-22\", \"2013-01-23\", \"2013-01-24\", \"2013-01-25\", \"2013-01-26\", \"2013-01-27\", \"2013-01-28\", \"2013-01-29\", \"2013-01-30\"], \"Customers\": [619, 80215, 72868, 74653, 66555, 1509, 103230, 87496, 79294, 82316, 85125, 67150, 1499, 70787, 66798, 64089, 64496, 70325, 65235, 1536, 85252, 83029, 79889, 78587, 81700, 63917, 1049, 71018, 64496, 67203], \"Saturday\": [0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0], \"Promo\": [0, 0, 0, 0, 0, 0, 114, 114, 114, 114, 114, 0, 0, 0, 0, 0, 0, 0, 0, 0, 114, 114, 114, 114, 114, 0, 0, 0, 0, 0], \"Tuesday\": [1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1, 0], \"SchoolHoliday\": [1, 114, 106, 106, 5, 0, 5, 5, 5, 5, 5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0], \"Sales\": [4220, 688536, 621409, 655280, 589744, 10509, 1079801, 867423, 774044, 774251, 800499, 598249, 10584, 579521, 550419, 529285, 535156, 604567, 592632, 11390, 864293, 820618, 774627, 743825, 773061, 576890, 7522, 580659, 534087, 583869], \"Friday\": [0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0], \"Sunday\": [0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0], \"Open\": [1, 114, 114, 114, 114, 1, 114, 114, 114, 114, 114, 114, 1, 114, 114, 114, 114, 114, 114, 1, 114, 114, 114, 114, 114, 114, 1, 114, 114, 114], \"Wednesday\": [0, 1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1]}";
		}
		CloseableHttpResponse response = this.restClient.post(url, body, headers);
		
		System.out.println(response.getStatusLine());
		String output="unprocessed";
		
		try {
			Map<String,Object> resMap = this.parser.parseMap(EntityUtils.toString(response.getEntity()));
			output = resMap.get("result").toString();
        } finally {
            response.close();
        }
		return output;
	}
	
	@RequestMapping("/rfv2")
	public String getRfv2(@RequestParam("body") String body) throws IOException {		
		List<Header> headers = this.restClient.getSecureTokenForClientId();
		
		this.restClient.addZoneToHeaders(headers, this.zoneId);
		String url = getAnalyticServiceUrl(this.catalogIdOfRfv2);
		//temp initiation while waiting for data service
		if(body == null || body == "") {
			body = "{\"January\": [1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1], \"Thursday\": [0, 0, 1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0], \"Monday\": [0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1, 0, 0], \"Promo2\": [0, 60, 60, 60, 60, 0, 60, 60, 60, 60, 60, 60, 0, 60, 60, 60, 60, 60, 60, 0, 60, 60, 60, 60, 60, 60, 0, 60, 60, 60], \"Date\": [\"2013-01-01\", \"2013-01-02\", \"2013-01-03\", \"2013-01-04\", \"2013-01-05\", \"2013-01-06\", \"2013-01-07\", \"2013-01-08\", \"2013-01-09\", \"2013-01-10\", \"2013-01-11\", \"2013-01-12\", \"2013-01-13\", \"2013-01-14\", \"2013-01-15\", \"2013-01-16\", \"2013-01-17\", \"2013-01-18\", \"2013-01-19\", \"2013-01-20\", \"2013-01-21\", \"2013-01-22\", \"2013-01-23\", \"2013-01-24\", \"2013-01-25\", \"2013-01-26\", \"2013-01-27\", \"2013-01-28\", \"2013-01-29\", \"2013-01-30\"], \"Customers\": [619, 80215, 72868, 74653, 66555, 1509, 103230, 87496, 79294, 82316, 85125, 67150, 1499, 70787, 66798, 64089, 64496, 70325, 65235, 1536, 85252, 83029, 79889, 78587, 81700, 63917, 1049, 71018, 64496, 67203], \"Saturday\": [0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0], \"Promo\": [0, 0, 0, 0, 0, 0, 114, 114, 114, 114, 114, 0, 0, 0, 0, 0, 0, 0, 0, 0, 114, 114, 114, 114, 114, 0, 0, 0, 0, 0], \"Tuesday\": [1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1, 0], \"SchoolHoliday\": [1, 114, 106, 106, 5, 0, 5, 5, 5, 5, 5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0], \"Sales\": [4220, 688536, 621409, 655280, 589744, 10509, 1079801, 867423, 774044, 774251, 800499, 598249, 10584, 579521, 550419, 529285, 535156, 604567, 592632, 11390, 864293, 820618, 774627, 743825, 773061, 576890, 7522, 580659, 534087, 583869], \"Friday\": [0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0], \"Sunday\": [0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0], \"Open\": [1, 114, 114, 114, 114, 1, 114, 114, 114, 114, 114, 114, 1, 114, 114, 114, 114, 114, 114, 1, 114, 114, 114, 114, 114, 114, 1, 114, 114, 114], \"Wednesday\": [0, 1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1]}";
		}
		CloseableHttpResponse response = this.restClient.post(url, body, headers);
		
		System.out.println(response.getStatusLine());
		String output="unprocessed";
		
		try {
			Map<String,Object> resMap = this.parser.parseMap(EntityUtils.toString(response.getEntity()));
			output = resMap.get("result").toString();
        } finally {
            response.close();
        }
		return output;
	}
	
	
}