package com.eginnovations.api;

import java.util.List;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import com.eginnovations.api.entity.Agent;
import com.eginnovations.api.entity.Parameters;
import com.fasterxml.jackson.core.JsonProcessingException;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes={AgentDao.class})
@TestPropertySource("/appTest.properties")
public class AgentDaoTest {
	@Autowired
	AgentDao dao;
	
	@Value("${managerUrl}")
	String managerUrl;
	
	@Value("${user}")
	String user;
	
	@Value("${pwd}")
	String pwd;
	
	@Value("${agentname}")
	String agentname;

	@Test
	public void testListExternalAgents() {
		try {
			Parameters parameters = new Parameters();
			parameters.setManagerUrl(managerUrl);
			parameters.setUser(user);
			parameters.setPwd(pwd);
			dao.setParameters(parameters);
			List<Agent> listExternalAgents = dao.listExternalAgents();
			System.out.println("listExternalAgents:"+listExternalAgents);
			if (listExternalAgents==null)
				Assert.fail("listExternalAgents is null");
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			Assert.fail(e.toString());
		}
	}
	
	@Test
	public void testListManagedHosts() {
		Parameters parameters = new Parameters();
		parameters.setManagerUrl(managerUrl);
		parameters.setUser(user);
		parameters.setPwd(pwd);
		dao.setParameters(parameters);
		List<String> listManagedHosts = dao.listManagedHosts(agentname, "externalagent");
		System.out.println("listManagedHosts:"+listManagedHosts);
		if (listManagedHosts==null)
			Assert.fail("listExternalAgents is null");
	}
	
	@Test
	public void testListManagedHosts2() {
		Parameters parameters = new Parameters();
		parameters.setManagerUrl(managerUrl);
		parameters.setUser(user);
		parameters.setPwd(pwd);
		dao.setParameters(parameters);
		List<String> listManagedHosts = dao.listManagedHosts(agentname, "remoteagent");
		System.out.println("listManagedHosts:"+listManagedHosts);
		if (listManagedHosts==null)
			Assert.fail("listExternalAgents is null");
	}
	
	

}
