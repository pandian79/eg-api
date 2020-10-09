package com.eginnovations.api;

import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import com.eginnovations.api.entity.ComponentMapping;
import com.eginnovations.api.entity.Parameters;
import com.eginnovations.api.entity.ZoneMapping;
import com.fasterxml.jackson.core.JsonProcessingException;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes={InventoryDao.class})
@TestPropertySource("/appTest.properties")
public class InventoryDaoTest {
	@Value("${managerUrl}")
	String managerUrl;
	
	@Value("${user}")
	String user;
	
	@Value("${pwd}")
	String pwd;
	
	InventoryDao dao = new InventoryDao();

	@Test
	public void testGetComponentMapping() {
		Parameters parameters = new Parameters();
		parameters.setManagerUrl(managerUrl);
		parameters.setUser(user);
		parameters.setPwd(pwd);
		dao.setParameters(parameters);
		try {
			List<ComponentMapping> componentMapping = dao.getComponentMapping();
			if (componentMapping.isEmpty() || componentMapping==null) {
				Assert.fail("componentMapping is empty");
			}
			
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			Assert.fail("Error in getComponentMapping :"+e);
		}
	}
	
	@Test
	public void testGetZoneMapping() {
		Parameters parameters = new Parameters();
		parameters.setManagerUrl(managerUrl);
		parameters.setUser(user);
		parameters.setPwd(pwd);
		dao.setParameters(parameters);
		try {
			List<ZoneMapping> zoneMapping = dao.getZoneMapping();
			if (zoneMapping.isEmpty() || zoneMapping==null) {
				Assert.fail("getZoneMapping is empty");
			}
			
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			Assert.fail("Error in getZoneMapping :"+e);
		}
	}
	
	@Test
	public void testGetTestMapping() {
		Parameters parameters = new Parameters();
		parameters.setManagerUrl(managerUrl);
		parameters.setUser(user);
		parameters.setPwd(pwd);
		dao.setParameters(parameters);
		try {
			Map<String, String> mapping = dao.getTestMapping();
			if (mapping.isEmpty() || mapping==null) {
				Assert.fail("getTestMapping is empty");
			}
			
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			Assert.fail("Error in getTestMapping :"+e);
		}
	}
	
	@Test
	public void testGetMeasureMapping() {
		Parameters parameters = new Parameters();
		parameters.setManagerUrl(managerUrl);
		parameters.setUser(user);
		parameters.setPwd(pwd);
		dao.setParameters(parameters);
		try {
			Map<String, String> mapping = dao.getMeasureMapping();
			if (mapping.isEmpty() || mapping==null) {
				Assert.fail("getTestMapping is empty");
			}
			
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			Assert.fail("Error in getTestMapping :"+e);
		}
	}
	
	@Test
	public void testGetApplicationMapping() {
		Parameters parameters = new Parameters();
		parameters.setManagerUrl(managerUrl);
		parameters.setUser(user);
		parameters.setPwd(pwd);
		dao.setParameters(parameters);
		try {
			Map<String, String> mapping = dao.getApplicationMapping();
			if (mapping.isEmpty() || mapping==null) {
				Assert.fail("getApplicationMapping is empty");
			}
			
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			Assert.fail("Error in getApplicationMapping :"+e);
		}
	}

}
