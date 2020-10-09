package com.eginnovations.api;

import java.util.Map;
import java.util.TreeMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.eginnovations.api.entity.Parameters;
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
public class AlarmDao {
	Logger logger = LoggerFactory.getLogger(AlarmDao.class);
	Parameters parameters;
	
	public Parameters getParameters() {
		return parameters;
	}

	public void setParameters(Parameters parameters) {
		this.parameters = parameters;
	}


	/**
	 * Implementation for /api/eg/analytics/getAlarmCount
	 * @return
	 * @throws JsonMappingException
	 * @throws JsonProcessingException
	 */
	public Map<String, Integer> getAlarmCount() throws JsonMappingException, JsonProcessingException {
		HttpHeaders headers = ApiUtils.getHeaders(this.getParameters());
		final String url = this.parameters.getManagerUrl()+"/api/eg/analytics/getAlarmCount";
		logger.info("Connecting to "+url);
	    RestTemplate restTemplate = new RestTemplate();
	    HttpEntity<String> entity = new HttpEntity<String>(headers);
	    ResponseEntity<String> exchange = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);
	    HttpStatus statusCode = exchange.getStatusCode();
	    logger.info("statusCode:"+statusCode.toString());
	    if (!statusCode.is2xxSuccessful()) {
	    	logger.error("Non 200 response found:"+exchange);
	    	return new TreeMap<String, Integer>();
	    }
	    String body = exchange.getBody();
	    logger.info("Response to "+url+" is a "+body.getClass()+" object. -> "+body);
	    ObjectMapper objectMapper=new ObjectMapper();
	    @SuppressWarnings("rawtypes")
		Map readValue = objectMapper.readValue(body, Map.class);
	    Map<String, Integer> convertValue = objectMapper.convertValue(readValue, 
	    		new TypeReference<Map<String, Integer>>() {
	    });
	    return convertValue;
	}
	

}
