package com.eginnovations.api;

import java.util.Hashtable;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import com.eginnovations.api.entity.Parameters;
@RunWith(SpringRunner.class)
@ContextConfiguration(classes={AnalyticalDataDao.class})
@TestPropertySource("/appTest.properties")
public class AnalyticalDataDaoTest {
	AnalyticalDataDao dao = new AnalyticalDataDao();
	
	@Value("${managerUrl}")
	String managerUrl;
	
	@Value("${user}")
	String user;
	
	@Value("${pwd}")
	String pwd;

	@Test
	public void testGetMailManagerAlertLogs() {
		Parameters parameters = new Parameters();
		parameters.setManagerUrl(managerUrl);
		parameters.setUser(user);
		parameters.setPwd(pwd);
		dao.setParameters(parameters);
	}

	@Test
	public void testGetLiveMeasure() {
		Parameters parameters = new Parameters();
		parameters.setManagerUrl(managerUrl);
		parameters.setUser(user);
		parameters.setPwd(pwd);
		dao.setParameters(parameters);
		Map<String, String> bodyParams = new Hashtable<String, String>();
		bodyParams.put("servername", "172.30.0.152:443");
		bodyParams.put("servertype","eG Manager");
		JSONArray liveMeasure = dao.getLiveMeasure(bodyParams);
		if (liveMeasure==null) {
			Assert.fail("null live measure");
		}
		
		if (liveMeasure.isEmpty()) {
			Assert.fail("live measure is empty");
		}
		System.out.println("liveMeasure:"+liveMeasure);
	}
	
	@Test
	public void testGetHistoricalData() {
		Parameters parameters = new Parameters();
		parameters.setManagerUrl(managerUrl);
		parameters.setUser(user);
		parameters.setPwd(pwd);
		dao.setParameters(parameters);
		Map<String, String> bodyParams = new Hashtable<String, String>();
		bodyParams.put("timeline", "1 day");
		bodyParams.put("server", "eG Manager:172.30.0.152:443");
		bodyParams.put("test","System Details");
		bodyParams.put("measure","CPU utilization");
		JSONObject liveMeasure = dao.getHistoricalData(bodyParams);
		if (liveMeasure==null) {
			Assert.fail("null live measure");
		}
		
		if (liveMeasure.isEmpty()) {
			Assert.fail("live measure is empty");
		}
		System.out.println("HistoricalMeasure:"+liveMeasure);
	}
	
	@Test
	public void testGetDiagnosisData() {
		Parameters parameters = new Parameters();
		parameters.setManagerUrl(managerUrl);
		parameters.setUser(user);
		parameters.setPwd(pwd);
		dao.setParameters(parameters);
		Map<String, String> bodyParams = new Hashtable<String, String>();
		bodyParams.put("timeline", "1 day");
		bodyParams.put("server", "eG Manager:172.30.0.152:443");
		bodyParams.put("test","System Details");
		bodyParams.put("measure","CPU utilization");
		bodyParams.put("descriptor", "Summary");
		JSONArray liveMeasure = dao.getDiagnosisData(bodyParams);
		if (liveMeasure==null) {
			Assert.fail("null live measure");
		}
		
		if (liveMeasure.isEmpty()) {
			Assert.fail("live measure is empty");
		}
		System.out.println("HistoricalMeasure:"+liveMeasure);
	}
	
	@Test
	public void testGetTopNData() {
		Parameters parameters = new Parameters();
		parameters.setManagerUrl(managerUrl);
		parameters.setUser(user);
		parameters.setPwd(pwd);
		dao.setParameters(parameters);
		Map<String, String> bodyParams = new Hashtable<String, String>();
		bodyParams.put("timeline", "1 hour");
		bodyParams.put("server", "172.30.0.152:443:eG Manager");
		bodyParams.put("test","System Details");
		bodyParams.put("measure","CPU utilization");
		bodyParams.put("descriptor", "Summary");
		JSONArray output = dao.getTopNData(bodyParams);
		if (output==null) {
			Assert.fail("null live measure");
		}
		
		if (output.isEmpty()) {
			Assert.fail("live measure is empty");
		}
		System.out.println("getTopNData:"+output);
	}
	
	@Test
	public void testGetTestData() {
		Parameters parameters = new Parameters();
		parameters.setManagerUrl(managerUrl);
		parameters.setUser(user);
		parameters.setPwd(pwd);
		dao.setParameters(parameters);
		Map<String, String> bodyParams = new Hashtable<String, String>();
		bodyParams.put("host", "172.30.0.152");
		bodyParams.put("port", "NULL");
		bodyParams.put("test","System Details");
		bodyParams.put("info", "Summary");
		bodyParams.put("lastmeasure", "true");
		JSONArray output = dao.getTestData(bodyParams);
		if (output==null) {
			Assert.fail("null live measure");
		}
		
		if (output.isEmpty()) {
			Assert.fail("live measure is empty");
		}
		System.out.println("getTopNData:"+output);
	}
	
	@Test
	public void testGetTrendData() {
		Parameters parameters = new Parameters();
		parameters.setManagerUrl(managerUrl);
		parameters.setUser(user);
		parameters.setPwd(pwd);
		dao.setParameters(parameters);
		Map<String, String> bodyParams = new Hashtable<String, String>();
		bodyParams.put("host", "172.30.0.152");
		bodyParams.put("port", "NULL");
		bodyParams.put("test","System Details");
		bodyParams.put("info", "Summary");
		bodyParams.put("type", "Trend");
		
		JSONArray output = dao.getTrendData(bodyParams);
		if (output==null) {
			Assert.fail("null live measure");
		}
		
		if (output.isEmpty()) {
			Assert.fail("live measure is empty");
		}
		System.out.println("getTopNData:"+output);
	}
	
	@Test
	public void testGetThresholdData() {
		Parameters parameters = new Parameters();
		parameters.setManagerUrl(managerUrl);
		parameters.setUser(user);
		parameters.setPwd(pwd);
		dao.setParameters(parameters);
		Map<String, String> bodyParams = new Hashtable<String, String>();
		bodyParams.put("host", "172.30.0.152");
		bodyParams.put("port", "NULL");
		bodyParams.put("test","System Details");
		bodyParams.put("info", "Summary");
		
		JSONArray output = dao.getThresholdData(bodyParams);
		if (output==null) {
			Assert.fail("null live measure");
		}
		
		if (output.isEmpty()) {
			Assert.fail("live measure is empty");
		}
		System.out.println("getTopNData:"+output);
	}
	
	@Test
	public void testGetInfraHealth() {
		Parameters parameters = new Parameters();
		parameters.setManagerUrl(managerUrl);
		parameters.setUser(user);
		parameters.setPwd(pwd);
		dao.setParameters(parameters);
		Map<String, String> bodyParams = new Hashtable<String, String>();
		bodyParams.put("type", "Zone");
		bodyParams.put("name", "NeilRoad");
		
		JSONObject output = dao.getInfraHealth(bodyParams);
		if (output==null) {
			Assert.fail("null live measure");
		}
		
		if (output.isEmpty()) {
			Assert.fail("live measure is empty");
		}
		System.out.println("getTopNData:"+output);
	}
	
	@Test
	public void testGetServerListProblemDistribution() {
		Parameters parameters = new Parameters();
		parameters.setManagerUrl(managerUrl);
		parameters.setUser(user);
		parameters.setPwd(pwd);
		dao.setParameters(parameters);
		Map<String, String> bodyParams = new Hashtable<String, String>();
		bodyParams.put("serverlist", "Java Application:neilroad_sg:9996,Dell PowerEdge VRTX:Dell_server:NULL,Citrix XenServer:xsdell:NULL");
		bodyParams.put("name", "NeilRoad");
		
		JSONObject output = dao.getServerListProblemDistribution(bodyParams);
		if (output==null) {
			Assert.fail("null live measure");
		}
		
		if (output.isEmpty()) {
			Assert.fail("live measure is empty");
		}
		System.out.println("getTopNData:"+output);
	}
	
	@Test
	public void testGetProblemDistributionServertype() {
		Parameters parameters = new Parameters();
		parameters.setManagerUrl(managerUrl);
		parameters.setUser(user);
		parameters.setPwd(pwd);
		dao.setParameters(parameters);
		Map<String, String> bodyParams = new Hashtable<String, String>();
		bodyParams.put("timeline", "1 hour");
		
		String [] s = new String [] {"servertype","servername","layer","Component Type"};
		for (String type:s) {
			JSONObject output = dao.getProblemDistribution(type, bodyParams);
			if (output==null) {
				Assert.fail("null live measure");
			}
			
			if (output.isEmpty()) {
				Assert.fail("live measure is empty");
			}
			System.out.println("Result :"+type+":"+output);
		}
		
	}
	
	@Test
	public void testGetEventCount() {
		Parameters parameters = new Parameters();
		parameters.setManagerUrl(managerUrl);
		parameters.setUser(user);
		parameters.setPwd(pwd);
		dao.setParameters(parameters);
		Map<String, String> bodyParams = new Hashtable<String, String>();
		bodyParams.put("timeline", "1 hour");
		
		String [] s = new String [] {"servertype","servername","layer","Component Type"};
		for (String type:s) {
			JSONObject output = dao.getEventCount(type, bodyParams);
			if (output==null) {
				Assert.fail("null getEventCount");
			}
			
			if (output.isEmpty()) {
				Assert.fail("getEventCount is empty");
			}
			System.out.println("Result :"+type+":"+output);
		}
		
	}
	
	@Test
	public void testGetProactiveProblemPercent() {
		Parameters parameters = new Parameters();
		parameters.setManagerUrl(managerUrl);
		parameters.setUser(user);
		parameters.setPwd(pwd);
		dao.setParameters(parameters);
		Map<String, String> bodyParams = new Hashtable<String, String>();
		bodyParams.put("timeline", "1 hour");
		
		String [] s = new String [] {"servertype","servername","layer"};
		for (String type:s) {
			JSONObject output = dao.getProactiveProblemPercent(type, bodyParams);
			if (output==null) {
				Assert.fail("null getProactiveProblemPercent");
			}
			
			if (output.isEmpty()) {
				Assert.fail("getProactiveProblemPercent is empty");
			}
			System.out.println("Result :"+type+":"+output);
		}
		
	}

}
