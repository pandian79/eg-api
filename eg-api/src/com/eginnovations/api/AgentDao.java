package com.eginnovations.api;

import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.eginnovations.api.entity.Agent;
import com.eginnovations.api.entity.Parameters;
import com.eginnovations.api.util.ApiUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Implementation for agent related API calls
 * @author pandian
 *
 */
public class AgentDao {
	Logger logger = LoggerFactory.getLogger(InventoryDao.class);
	Parameters parameters;
	
	public Parameters getParameters() {
		return parameters;
	}

	public void setParameters(Parameters parameters) {
		this.parameters = parameters;
	}
	
	/**
	 * Implementation for /api/eg/orchestration/showexternalagents
	 * @return
	 * @throws JsonMappingException
	 * @throws JsonProcessingException
	 */
	public List<Agent> listExternalAgents() throws JsonMappingException, JsonProcessingException {
		HttpHeaders headers = ApiUtils.getHeaders(this.getParameters());
		final String url = this.parameters.getManagerUrl()+"/api/eg/orchestration/showexternalagents";
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
	    List<Agent> convertValue = objectMapper.convertValue(readValue, 
	    		new TypeReference<List<Agent>>() {
	    	
	    });
	    return convertValue;
	    
	}

	/**
	 * Implementation for /api/eg/orchestration/showmanagedhosts
	 * @param agentname	Name of the agent
	 * @param agenttype	Type of the agent like External or Remote
	 * @return	List of hosts managed by this agent
	 */
	public List<String> listManagedHosts(String agentname, String agenttype) {
		HttpHeaders headers = ApiUtils.getHeaders(this.getParameters());
		ObjectMapper objectMapper=new ObjectMapper();
		
		final String url = this.parameters.getManagerUrl()+"/api/eg/orchestration/showmanagedhosts";
		logger.info("Connecting to "+url);
	    RestTemplate restTemplate = new RestTemplate();
	    
	    JSONObject requestBodyJsonObject = new JSONObject(); 
	    requestBodyJsonObject.put("agentname", agentname);
	    requestBodyJsonObject.put("agenttype", agenttype);
	    
	    HttpEntity<String> request = 
		  	      new HttpEntity<String>(requestBodyJsonObject.toString(), headers);
	    
	    ResponseEntity<String> exchange = restTemplate.exchange(url, HttpMethod.POST, request, String.class);
	    if (exchange.getStatusCodeValue()!=200) {
	    	throw new RuntimeException(exchange.getBody()+"");
	    }
	    String postForObject = exchange.getBody();
	    logger.info("postForObject showmanagedhosts:"+postForObject);
	    
	    JSONObject convertValue = objectMapper.convertValue(postForObject, 
	    		new TypeReference<JSONObject>() {
	    	
	    });
	    
	    try {
			String error = convertValue.getString("Error");
			if (error!=null)
				logger.info("Error observed while getting managed hosts for "+agenttype+" "+agentname+": "+error);
		} catch (JSONException e) {
			//can ignore
		}
	    
	    List<String> result = new ArrayList<String>();
	    JSONArray managedHostsArray = null;
		try {
			managedHostsArray = (JSONArray) convertValue.get("managedHosts");
			for (Object host:managedHostsArray) {
		    	result.add(host.toString());
		    }
		} catch (JSONException e) {
			logger.info("Error while getting managed hosts for "+agenttype+" "+agentname+": ", e);
		}
	    
	    return result;
	}
}
