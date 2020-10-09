package com.eginnovations.api;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.Vector;

import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.eginnovations.api.entity.Parameters;
import com.eginnovations.api.util.ApiUtils;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Implementation for custom SQL query implementation 
 * @author pandian
 *
 */
public class AnalyticalDataDao {
	Logger logger = LoggerFactory.getLogger(AnalyticalDataDao.class);
	Parameters parameters;
	
	public Parameters getParameters() {
		return parameters;
	}

	public void setParameters(Parameters parameters) {
		this.parameters = parameters;
	}

	/**
	 * Getting records from mail_manager_logs
	 * @param fromDate from date in 31/03/2020 format
	 * @param toDate	31/03/2020 format
	 * @param searchStr
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public Vector<Vector> getMailManagerAlertLogs(Date fromDate, Date toDate, String searchStr) {
		Vector<Vector> result = new Vector<Vector>();
		HttpHeaders headers = ApiUtils.getHeaders(this.getParameters());
		ObjectMapper objectMapper=new ObjectMapper();
		
		final String url = this.parameters.getManagerUrl()+"/api/eg/analytics/getCustomQuery";
		logger.info("Connecting to "+url);
	    RestTemplate restTemplate = new RestTemplate();
	    JSONObject requestBodyJsonObject = new JSONObject(); 
	    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	    String fromDateStr = sdf.format(fromDate);
	    String toDateStr = sdf.format(toDate);
	    StringBuffer sb = new StringBuffer();
	    sb.append("select * from mail_manager_logs where LOG_TIME >= convert(datetime,'");
	    sb.append(fromDateStr);
	    sb.append(" 00:00:00',103) and LOG_TIME <= convert(datetime,'");
	    sb.append(toDateStr);
	    sb.append(" 23:59:59',103)");
	    sb.append( " and (ALARM_DETAIL like '%");
	    sb.append(searchStr);
	    sb.append("%' or RECIPIENT_DETAIL like '%");
	    sb.append(searchStr);
	    sb.append("%')");
	    
	    String qry = sb.toString();
	    sb = null;
	    logger.info("qry:"+qry);
		requestBodyJsonObject.put("query", qry);
	    
	    HttpEntity<String> request = 
		  	      new HttpEntity<String>(requestBodyJsonObject.toString(), headers);

	    ResponseEntity<String> exchange = restTemplate.exchange(url, HttpMethod.POST, request, String.class);
	    logger.info("completed REST call");
	    if (exchange.getStatusCodeValue()!=200) {
	    	throw new RuntimeException(exchange.getBody()+"");
	    }
	    String postForObject = exchange.getBody();
	    logger.info("postForObject getCustomQuery:"+postForObject);
	    
	    try {
			JSONObject errorValue = objectMapper.convertValue(postForObject, 
					new TypeReference<JSONObject>() {
			});
			Object object = errorValue.get("error");
			throw new RuntimeException(object.toString());
		} catch (IllegalArgumentException e) {
			//no error
		}
	    
	    JSONArray convertValue = objectMapper.convertValue(postForObject, 
	    		new TypeReference<JSONArray>() {
	    });
	    
	    
	    for (int i=0; i<convertValue.length(); i++) {
	    	String delimiter = (i==0)?"  ":"||";
	    	String record = convertValue.getString(i).toString();
	    	logger.info("process record id "+i+". "+record);
			Vector<String> textToVector = ApiUtils.textToVector(record, delimiter);
			logger.info("processed record id "+i+". "+textToVector);
			result.add(textToVector);
	    }
	    logger.info("postForObject getCustomQuery result count after tokenizing:"+result.size());

	    return result;
	}
	
	/**
	 * Implementation for /api/eg/analytics/getLiveMeasure
	 * 
	 * @param bodyParams
	 * @return 
	 * @see https://www.eginnovations.com/documentation/Automatically-Configuring-the-Target-Environment-using-REST-API/Retrieving-Live-Measures-of-a-Component.htm
	 */
	public JSONArray getLiveMeasure(Map<String, String> bodyParams) {
		HttpHeaders headers = ApiUtils.getHeaders(this.getParameters());
		ObjectMapper objectMapper=new ObjectMapper();
		
		final String url = this.parameters.getManagerUrl()+"/api/eg/analytics/getLiveMeasure";
		logger.info("Connecting to "+url);
	    RestTemplate restTemplate = new RestTemplate();
	    JSONObject requestBodyJsonObject = new JSONObject(bodyParams);
	    HttpEntity<String> request = new HttpEntity<String>(requestBodyJsonObject.toString(), headers);
	    ResponseEntity<String> exchange = restTemplate.exchange(url, HttpMethod.POST, request, String.class);
	    logger.info("completed REST call");
	    if (exchange.getStatusCodeValue()!=200) {
	    	throw new RuntimeException(exchange.getBody()+"");
	    }
	    String postForObject = exchange.getBody();
	    logger.info("postForObject getLiveMeasure:"+postForObject);
	    
	    try {
			JSONObject errorValue = objectMapper.convertValue(postForObject, 
					new TypeReference<JSONObject>() {
			});
			Object object = errorValue.get("error");
			throw new RuntimeException(object.toString());
		} catch (IllegalArgumentException e) {
			//no error
		}
	    
	    JSONArray convertValue = objectMapper.convertValue(postForObject, 
	    		new TypeReference<JSONArray>() {
	    });
	    
	    return convertValue;
	}

	/**
	 * Implementation for /api/eg/analytics/getHistoricalData
	 * @param bodyParams
	 * @return
	 * @see https://www.eginnovations.com/documentation/Automatically-Configuring-the-Target-Environment-using-REST-API/Retrieving-Historical-Data-of-a-Measure.htm
	 */
	public JSONObject getHistoricalData(Map<String, String> bodyParams) {
		HttpHeaders headers = ApiUtils.getHeaders(this.getParameters());
		ObjectMapper objectMapper=new ObjectMapper();
		
		final String url = this.parameters.getManagerUrl()+"/api/eg/analytics/getHistoricalData";
		logger.info("Connecting to "+url);
	    RestTemplate restTemplate = new RestTemplate();
	    JSONObject requestBodyJsonObject = new JSONObject(bodyParams);
	    HttpEntity<String> request = new HttpEntity<String>(requestBodyJsonObject.toString(), headers);
	    ResponseEntity<String> exchange = restTemplate.exchange(url, HttpMethod.POST, request, String.class);
	    logger.info("completed REST call");
	    if (exchange.getStatusCodeValue()!=200) {
	    	throw new RuntimeException(exchange.getBody()+"");
	    }
	    String postForObject = exchange.getBody();
	    logger.info("postForObject getHistoricalData:"+postForObject);
	    
	    JSONObject convertValue = objectMapper.convertValue(postForObject, 
	    		new TypeReference<JSONObject>() {
	    });
	    
	    return convertValue;
	}
	
	/**
	 * Implementation for /api/eg/analytics/getDiagnosisData
	 * @param bodyParams
	 * @return
	 * @see https://www.eginnovations.com/documentation/Automatically-Configuring-the-Target-Environment-using-REST-API/Retrieving-Detailed-Diagnosis-of-a-Measure.htm
	 */
	public JSONArray getDiagnosisData(Map<String, String> bodyParams) {
		HttpHeaders headers = ApiUtils.getHeaders(this.getParameters());
		ObjectMapper objectMapper=new ObjectMapper();
		
		final String url = this.parameters.getManagerUrl()+"/api/eg/analytics/getDiagnosisData";
		logger.info("Connecting to "+url);
	    RestTemplate restTemplate = new RestTemplate();
	    JSONObject requestBodyJsonObject = new JSONObject(bodyParams);
	    HttpEntity<String> request = new HttpEntity<String>(requestBodyJsonObject.toString(), headers);
	    ResponseEntity<String> exchange = restTemplate.exchange(url, HttpMethod.POST, request, String.class);
	    logger.info("completed REST call");
	    if (exchange.getStatusCodeValue()!=200) {
	    	throw new RuntimeException(exchange.getBody()+"");
	    }
	    String postForObject = exchange.getBody();
	    logger.info("postForObject getDiagnosisData:"+postForObject);
	    
	    JSONArray convertValue = objectMapper.convertValue(postForObject, 
	    		new TypeReference<JSONArray>() {
	    });
	    
	    return convertValue;
	}
	
	/**
	 * Implementation for /api/eg/analytics/getTopNData
	 * @param bodyParams
	 * @return
	 * @see https://www.eginnovations.com/documentation/Automatically-Configuring-the-Target-Environment-using-REST-API/Retrieving-Top-N-Analysis-Data.htm
	 */
	public JSONArray getTopNData(Map<String, String> bodyParams) {
		HttpHeaders headers = ApiUtils.getHeaders(this.getParameters());
		ObjectMapper objectMapper=new ObjectMapper();
		
		final String url = this.parameters.getManagerUrl()+"/api/eg/analytics/getTopNData";
		logger.info("Connecting to "+url);
	    RestTemplate restTemplate = new RestTemplate();
	    JSONObject requestBodyJsonObject = new JSONObject(bodyParams);
	    HttpEntity<String> request = new HttpEntity<String>(requestBodyJsonObject.toString(), headers);
	    ResponseEntity<String> exchange = restTemplate.exchange(url, HttpMethod.POST, request, String.class);
	    logger.info("completed REST call");
	    if (exchange.getStatusCodeValue()!=200) {
	    	throw new RuntimeException(exchange.getBody()+"");
	    }
	    String postForObject = exchange.getBody();
	    logger.info("postForObject getTopNData:"+postForObject);
	    
	    JSONArray convertValue = objectMapper.convertValue(postForObject, 
	    		new TypeReference<JSONArray>() {
	    });
	    
	    return convertValue;
	}
	
	/**
	 * Implementation for /api/eg/analytics/getTestData
	 * @param bodyParams
	 * @return
	 * @see https://www.eginnovations.com/documentation/Automatically-Configuring-the-Target-Environment-using-REST-API/Retrieving-Test-Data.htm
	 */
	public JSONArray getTestData(Map<String, String> bodyParams) {
		HttpHeaders headers = ApiUtils.getHeaders(this.getParameters());
		ObjectMapper objectMapper=new ObjectMapper();
		
		final String url = this.parameters.getManagerUrl()+"/api/eg/analytics/getTestData";
		logger.info("Connecting to "+url);
	    RestTemplate restTemplate = new RestTemplate();
	    JSONObject requestBodyJsonObject = new JSONObject(bodyParams);
	    HttpEntity<String> request = new HttpEntity<String>(requestBodyJsonObject.toString(), headers);
	    ResponseEntity<String> exchange = restTemplate.exchange(url, HttpMethod.POST, request, String.class);
	    logger.info("completed REST call");
	    if (exchange.getStatusCodeValue()!=200) {
	    	throw new RuntimeException(exchange.getBody()+"");
	    }
	    String postForObject = exchange.getBody();
	    logger.info("postForObject getTestData:"+postForObject);
	    
	    JSONArray convertValue = objectMapper.convertValue(postForObject, 
	    		new TypeReference<JSONArray>() {
	    });
	    
	    return convertValue;
	}
	
	/**
	 * Implementation for /api/eg/analytics/getTrendData
	 * @param bodyParams
	 * @return
	 */
	public JSONArray getTrendData(Map<String, String> bodyParams) {
		HttpHeaders headers = ApiUtils.getHeaders(this.getParameters());
		ObjectMapper objectMapper=new ObjectMapper();
		
		final String url = this.parameters.getManagerUrl()+"/api/eg/analytics/getTrendData";
		logger.info("Connecting to "+url);
	    RestTemplate restTemplate = new RestTemplate();
	    JSONObject requestBodyJsonObject = new JSONObject(bodyParams);
	    HttpEntity<String> request = new HttpEntity<String>(requestBodyJsonObject.toString(), headers);
	    ResponseEntity<String> exchange = restTemplate.exchange(url, HttpMethod.POST, request, String.class);
	    logger.info("completed REST call");
	    if (exchange.getStatusCodeValue()!=200) {
	    	throw new RuntimeException(exchange.getBody()+"");
	    }
	    String postForObject = exchange.getBody();
	    logger.info("postForObject getTrendData:"+postForObject);
	    
	    JSONArray convertValue = objectMapper.convertValue(postForObject, 
	    		new TypeReference<JSONArray>() {
	    });
	    
	    return convertValue;
	}
	
	/**
	 * Implementation for /api/eg/analytics/getThresholdData
	 * @param bodyParams
	 * @return
	 * @see https://www.eginnovations.com/documentation/Automatically-Configuring-the-Target-Environment-using-REST-API/Retrieving-Threshold-Data.htm
	 */
	public JSONArray getThresholdData(Map<String, String> bodyParams) {
		HttpHeaders headers = ApiUtils.getHeaders(this.getParameters());
		ObjectMapper objectMapper=new ObjectMapper();
		
		final String url = this.parameters.getManagerUrl()+"/api/eg/analytics/getThresholdData";
		logger.info("Connecting to "+url);
	    RestTemplate restTemplate = new RestTemplate();
	    JSONObject requestBodyJsonObject = new JSONObject(bodyParams);
	    HttpEntity<String> request = new HttpEntity<String>(requestBodyJsonObject.toString(), headers);
	    ResponseEntity<String> exchange = restTemplate.exchange(url, HttpMethod.POST, request, String.class);
	    logger.info("completed REST call");
	    if (exchange.getStatusCodeValue()!=200) {
	    	throw new RuntimeException(exchange.getBody()+"");
	    }
	    String postForObject = exchange.getBody();
	    logger.info("postForObject getThresholdData:"+postForObject);
	    
	    JSONArray convertValue = objectMapper.convertValue(postForObject, 
	    		new TypeReference<JSONArray>() {
	    });
	    
	    return convertValue;
	}
	
	/**
	 * Implementation for /api/eg/analytics/getInfraHealth
	 * @param bodyParams
	 * @return
	 * @see https://www.eginnovations.com/documentation/Automatically-Configuring-the-Target-Environment-using-REST-API/Retrieving-Infrastructure-Health.htm
	 */
	public JSONObject getInfraHealth(Map<String, String> bodyParams) {
		HttpHeaders headers = ApiUtils.getHeaders(this.getParameters());
		ObjectMapper objectMapper=new ObjectMapper();
		
		final String url = this.parameters.getManagerUrl()+"/api/eg/analytics/getInfraHealth";
		logger.info("Connecting to "+url);
	    RestTemplate restTemplate = new RestTemplate();
	    JSONObject requestBodyJsonObject = new JSONObject(bodyParams);
	    HttpEntity<String> request = new HttpEntity<String>(requestBodyJsonObject.toString(), headers);
	    ResponseEntity<String> exchange = restTemplate.exchange(url, HttpMethod.POST, request, String.class);
	    logger.info("completed REST call");
	    if (exchange.getStatusCodeValue()!=200) {
	    	throw new RuntimeException(exchange.getBody()+"");
	    }
	    String postForObject = exchange.getBody();
	    logger.info("postForObject getInfraHealth:"+postForObject);
	    
	    JSONObject convertValue = objectMapper.convertValue(postForObject, 
	    		new TypeReference<JSONObject>() {
	    });
	    
	    return convertValue;
	}
	
	/**
	 * Implementation for /api/eg/analytics/getServerListProblemDistribution
	 * @param bodyParams
	 * @return
	 * @see https://www.eginnovations.com/documentation/Automatically-Configuring-the-Target-Environment-using-REST-API/Retrieving-Problem-Distribution-of-Components.htm
	 */
	public JSONObject getServerListProblemDistribution(Map<String, String> bodyParams) {
		HttpHeaders headers = ApiUtils.getHeaders(this.getParameters());
		ObjectMapper objectMapper=new ObjectMapper();
		
		final String url = this.parameters.getManagerUrl()+"/api/eg/analytics/getServerListProblemDistribution";
		logger.info("Connecting to "+url);
	    RestTemplate restTemplate = new RestTemplate();
	    JSONObject requestBodyJsonObject = new JSONObject(bodyParams);
	    HttpEntity<String> request = new HttpEntity<String>(requestBodyJsonObject.toString(), headers);
	    ResponseEntity<String> exchange = restTemplate.exchange(url, HttpMethod.POST, request, String.class);
	    logger.info("completed REST call");
	    if (exchange.getStatusCodeValue()!=200) {
	    	throw new RuntimeException(exchange.getBody()+"");
	    }
	    String postForObject = exchange.getBody();
	    logger.info("postForObject getServerListProblemDistribution:"+postForObject);
	    
	    JSONObject convertValue = objectMapper.convertValue(postForObject, 
	    		new TypeReference<JSONObject>() {
	    });
	    
	    return convertValue;
	}
	
	/**
	 * Implementation for /api/eg/analytics/getProblemDistribution/servertype
	 * @param bodyParams
	 * @param type This can take values like servertype|servername|layer|Component Type
	 * @return
	 * @see https://www.eginnovations.com/documentation/Automatically-Configuring-the-Target-Environment-using-REST-API/Retrieving-Problem-Distribution-of-the-Target-Environment.htm
	 */
	public JSONObject getProblemDistribution(String type, Map<String, String> bodyParams) {
		HttpHeaders headers = ApiUtils.getHeaders(this.getParameters());
		ObjectMapper objectMapper=new ObjectMapper();
		
		String url = null;
		switch(type) {
		case "servertype":
			url = this.parameters.getManagerUrl()+"/api/eg/analytics/getProblemDistribution/servertype";
			break;
		case "servername":
			url = this.parameters.getManagerUrl()+"/api/eg/analytics/getProblemDistribution/servername";
			break;
		case "layer":
			url = this.parameters.getManagerUrl()+"/api/eg/analytics/getProblemDistribution/layer";
			break;
		case "type":
		case "Component Type":
			url = this.parameters.getManagerUrl()+"/api/eg/analytics/getProblemDistribution/test";
			break;
		}
		
		logger.info("Connecting to "+url);
	    RestTemplate restTemplate = new RestTemplate();
	    JSONObject requestBodyJsonObject = new JSONObject(bodyParams);
	    HttpEntity<String> request = new HttpEntity<String>(requestBodyJsonObject.toString(), headers);
	    ResponseEntity<String> exchange = restTemplate.exchange(url, HttpMethod.POST, request, String.class);
	    logger.info("completed REST call");
	    if (exchange.getStatusCodeValue()!=200) {
	    	throw new RuntimeException(exchange.getBody()+"");
	    }
	    String postForObject = exchange.getBody();
	    logger.info("postForObject getServerListProblemDistribution:"+postForObject);
	    
	    JSONObject convertValue = objectMapper.convertValue(postForObject, 
	    		new TypeReference<JSONObject>() {
	    });
	    
	    return convertValue;
	}
	
	/**
	 * Implementation for /api/eg/analytics/getEventCount/servertype
	 * @param type This can take values like servertype|servername|layer|Component Type
	 * @param bodyParams
	 * @return
	 * @see https://www.eginnovations.com/documentation/Automatically-Configuring-the-Target-Environment-using-REST-API/Retrieving-the-Count-of-Events-Recorded-in-Event-Logs.htm
	 */
	public JSONObject getEventCount(String type, Map<String, String> bodyParams) {
		HttpHeaders headers = ApiUtils.getHeaders(this.getParameters());
		ObjectMapper objectMapper=new ObjectMapper();
		
		String url = null;
		switch(type) {
		case "servertype":
			url = this.parameters.getManagerUrl()+"/api/eg/analytics/getEventCount/servertype";
			break;
		case "servername":
			url = this.parameters.getManagerUrl()+"/api/eg/analytics/getEventCount/servername";
			break;
		case "layer":
			url = this.parameters.getManagerUrl()+"/api/eg/analytics/getEventCount/layer";
			break;
		case "type":
		case "Component Type":
			url = this.parameters.getManagerUrl()+"/api/eg/analytics/getEventCount/test";
			break;
		}
		
		logger.info("Connecting to "+url);
	    RestTemplate restTemplate = new RestTemplate();
	    JSONObject requestBodyJsonObject = new JSONObject(bodyParams);
	    HttpEntity<String> request = new HttpEntity<String>(requestBodyJsonObject.toString(), headers);
	    ResponseEntity<String> exchange = restTemplate.exchange(url, HttpMethod.POST, request, String.class);
	    logger.info("completed REST call");
	    if (exchange.getStatusCodeValue()!=200) {
	    	throw new RuntimeException(exchange.getBody()+"");
	    }
	    String postForObject = exchange.getBody();
	    logger.info("postForObject getServerListProblemDistribution:"+postForObject);
	    
	    JSONObject convertValue = objectMapper.convertValue(postForObject, 
	    		new TypeReference<JSONObject>() {
	    });
	    
	    return convertValue;
	}
	
	/**
	 * Implementation for /api/eg/analytics/getProactiveProblemPercent
	 * @param type This can take values like servertype|servername|layer|Component Type
	 * @param bodyParams
	 * @return
	 * @see https://www.eginnovations.com/documentation/Automatically-Configuring-the-Target-Environment-using-REST-API/Retrieving-Percentage-of-Proactive-Alarms.htm
	 */
	public JSONObject getProactiveProblemPercent(String type, Map<String, String> bodyParams) {
		HttpHeaders headers = ApiUtils.getHeaders(this.getParameters());
		ObjectMapper objectMapper=new ObjectMapper();
		
		String url = null;
		switch(type) {
		case "servertype":
			url = this.parameters.getManagerUrl()+"/api/eg/analytics/getProactiveProblemPercent/servertype";
			break;
		case "servername":
			url = this.parameters.getManagerUrl()+"/api/eg/analytics/getProactiveProblemPercent/servername";
			break;
		case "layer":
			url = this.parameters.getManagerUrl()+"/api/eg/analytics/getProactiveProblemPercent/layer";
			break;
		}
		
		logger.info("Connecting to "+url);
	    RestTemplate restTemplate = new RestTemplate();
	    JSONObject requestBodyJsonObject = new JSONObject(bodyParams);
	    HttpEntity<String> request = new HttpEntity<String>(requestBodyJsonObject.toString(), headers);
	    ResponseEntity<String> exchange = restTemplate.exchange(url, HttpMethod.POST, request, String.class);
	    logger.info("completed REST call");
	    if (exchange.getStatusCodeValue()!=200) {
	    	throw new RuntimeException(exchange.getBody()+"");
	    }
	    String postForObject = exchange.getBody();
	    logger.info("postForObject getServerListProblemDistribution:"+postForObject);
	    
	    JSONObject convertValue = objectMapper.convertValue(postForObject, 
	    		new TypeReference<JSONObject>() {
	    });
	    
	    return convertValue;
	}

	
}
