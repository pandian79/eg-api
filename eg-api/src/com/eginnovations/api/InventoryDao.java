package com.eginnovations.api;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.eginnovations.api.entity.ComponentMapping;
import com.eginnovations.api.entity.Parameters;
import com.eginnovations.api.entity.ZoneMapping;
import com.eginnovations.api.util.ApiUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Implementation for component related API calls
 * @author pandian
 *
 */
public class InventoryDao {
	Logger logger = LoggerFactory.getLogger(InventoryDao.class);
	Parameters parameters;
	
	public Parameters getParameters() {
		return parameters;
	}

	public void setParameters(Parameters parameters) {
		this.parameters = parameters;
	}


	/**
	 * Implementation for /api/eg/miscservice/getComponentMapping
	 * @return
	 * @throws JsonMappingException
	 * @throws JsonProcessingException
	 * @see https://www.eginnovations.com/documentation/Automatically-Configuring-the-Target-Environment-using-REST-API/Retrieving-Details-of-Components-Managed-in-eG-Manager.htm
	 */
	public List<ComponentMapping> getComponentMapping() throws JsonMappingException, JsonProcessingException {
		HttpHeaders headers = ApiUtils.getHeaders(this.getParameters());
		final String url = this.parameters.getManagerUrl()+"/api/eg/miscservice/getComponentMapping";
		logger.info("Connecting to "+url);
	    RestTemplate restTemplate = new RestTemplate();
	    HttpEntity<String> entity = new HttpEntity<String>(headers);
	    ResponseEntity<String> exchange = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);
	    HttpStatus statusCode = exchange.getStatusCode();
	    if (!statusCode.is2xxSuccessful()) {
	    	System.err.println(""+exchange);
	    }
	    String body = exchange.getBody();
	    System.out.println(body);
	    ObjectMapper objectMapper=new ObjectMapper();
	    @SuppressWarnings("rawtypes")
		List readValue = objectMapper.readValue(body, List.class);
	    List<ComponentMapping> convertValue = objectMapper.convertValue(readValue, 
	    		new TypeReference<List<ComponentMapping>>() {
	    	
	    });
	    return convertValue;
	}
	
	/**
	 * Implementation for /api/eg/api/eg/miscservice/getZoneMapping
	 * @return
	 * @throws JsonMappingException
	 * @throws JsonProcessingException
	 * @see https://www.eginnovations.com/documentation/Automatically-Configuring-the-Target-Environment-using-REST-API/Retrieving-Zone-Details-from-eG-Manager.htm
	 */
	public List<ZoneMapping> getZoneMapping() throws JsonMappingException, JsonProcessingException {
		HttpHeaders headers = ApiUtils.getHeaders(this.getParameters());
		final String url = this.parameters.getManagerUrl()+"/api/eg/miscservice/getZoneMapping";
		logger.info("Connecting to "+url);
	    RestTemplate restTemplate = new RestTemplate();
	    HttpEntity<String> entity = new HttpEntity<String>(headers);
	    ResponseEntity<String> exchange = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);
	    HttpStatus statusCode = exchange.getStatusCode();
	    if (!statusCode.is2xxSuccessful()) {
	    	System.err.println(""+exchange);
	    }
	    String body = exchange.getBody();
	    System.out.println(body);
	    ObjectMapper objectMapper=new ObjectMapper();
	    @SuppressWarnings("rawtypes")
		List readValue = objectMapper.readValue(body, List.class);
	    List<ZoneMapping> convertValue = objectMapper.convertValue(readValue, 
	    		new TypeReference<List<ZoneMapping>>() {
	    	
	    });
	    return convertValue;
	}
	
	/**
	 * Implementation for /api/eg/miscservice/getTestMapping
	 * @return
	 * @throws JsonMappingException
	 * @throws JsonProcessingException
	 * @see https://www.eginnovations.com/documentation/Automatically-Configuring-the-Target-Environment-using-REST-API/Retrieving-the-Tests-Supported-by-eG-Enterprise.htm
	 */
	public Map<String, String> getTestMapping() throws JsonMappingException, JsonProcessingException {
		HttpHeaders headers = ApiUtils.getHeaders(this.getParameters());
		final String url = this.parameters.getManagerUrl()+"/api/eg/miscservice/getTestMapping";
		logger.info("Connecting to "+url);
	    RestTemplate restTemplate = new RestTemplate();
	    HttpEntity<String> entity = new HttpEntity<String>(headers);
	    ResponseEntity<String> exchange = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);
	    HttpStatus statusCode = exchange.getStatusCode();
	    if (!statusCode.is2xxSuccessful()) {
	    	System.err.println(""+exchange);
	    }
	    String body = exchange.getBody();
	    System.out.println(body);
	    ObjectMapper objectMapper=new ObjectMapper();
	    @SuppressWarnings("rawtypes")
		Map readValue = objectMapper.readValue(body, Map.class);
	    Map<String, String> convertValue = objectMapper.convertValue(readValue, 
	    		new TypeReference<Map<String, String>>() {
	    });
	    return convertValue;
	}
	
	/**
	 * Implementation for /api/eg/miscservice/getMeasureMapping
	 * @return
	 * @throws JsonMappingException
	 * @throws JsonProcessingException
	 * @see https://www.eginnovations.com/documentation/Automatically-Configuring-the-Target-Environment-using-REST-API/Retrieving-the-Measurements-Reported-by-eG-Enterprise.htm
	 */
	public Map<String, String> getMeasureMapping() throws JsonMappingException, JsonProcessingException {
		HttpHeaders headers = ApiUtils.getHeaders(this.getParameters());
		final String url = this.parameters.getManagerUrl()+"/api/eg/miscservice/getMeasureMapping";
		logger.info("Connecting to "+url);
	    RestTemplate restTemplate = new RestTemplate();
	    HttpEntity<String> entity = new HttpEntity<String>(headers);
	    ResponseEntity<String> exchange = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);
	    HttpStatus statusCode = exchange.getStatusCode();
	    if (!statusCode.is2xxSuccessful()) {
	    	System.err.println(""+exchange);
	    }
	    String body = exchange.getBody();
	    System.out.println(body);
	    ObjectMapper objectMapper=new ObjectMapper();
	    @SuppressWarnings("rawtypes")
		Map readValue = objectMapper.readValue(body, Map.class);
	    Map<String, String> convertValue = objectMapper.convertValue(readValue, 
	    		new TypeReference<Map<String, String>>() {
	    });
	    return convertValue;
	}
	
	/**
	 * Implementation for /api/eg/miscservice/getApplicationMapping
	 * @return
	 * @throws JsonMappingException
	 * @throws JsonProcessingException
	 * @see https://www.eginnovations.com/documentation/Automatically-Configuring-the-Target-Environment-using-REST-API/Retrieving-Applications-Monitored-by-eG-Enterprise.htm
	 */
	public Map<String, String> getApplicationMapping() throws JsonMappingException, JsonProcessingException {
		HttpHeaders headers = ApiUtils.getHeaders(this.getParameters());
		final String url = this.parameters.getManagerUrl()+"/api/eg/miscservice/getApplicationMapping";
		logger.info("Connecting to "+url);
	    RestTemplate restTemplate = new RestTemplate();
	    HttpEntity<String> entity = new HttpEntity<String>(headers);
	    ResponseEntity<String> exchange = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);
	    HttpStatus statusCode = exchange.getStatusCode();
	    if (!statusCode.is2xxSuccessful()) {
	    	System.err.println(""+exchange);
	    }
	    String body = exchange.getBody();
	    System.out.println(body);
	    ObjectMapper objectMapper=new ObjectMapper();
	    @SuppressWarnings("rawtypes")
		Map readValue = objectMapper.readValue(body, Map.class);
	    Map<String, String> convertValue = objectMapper.convertValue(readValue, 
	    		new TypeReference<Map<String, String>>() {
	    });
	    return convertValue;
	}
	
}
