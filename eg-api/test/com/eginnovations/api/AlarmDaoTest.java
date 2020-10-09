package com.eginnovations.api;

import java.util.Map;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import com.eginnovations.api.entity.Parameters;
import com.fasterxml.jackson.core.JsonProcessingException;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes={AlarmDao.class})
@TestPropertySource("/appTest.properties")
public class AlarmDaoTest {
	@Autowired
	AlarmDao dao;
	
	@Value("${managerUrl}")
	String managerUrl;
	
	@Value("${user}")
	String user;
	
	@Value("${pwd}")
	String pwd;

	@Test
	public void testGetAlarmCount() {
		try {
			Parameters parameters = new Parameters();
			parameters.setManagerUrl(managerUrl);
			parameters.setUser(user);
			parameters.setPwd(pwd);
			dao.setParameters(parameters);
			Map<String, Integer> alarmCount = dao.getAlarmCount();
			System.out.println("alarmCount:"+alarmCount);
			if (alarmCount==null)
				Assert.fail("alarmCount is null");
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			Assert.fail(e.toString());
		}
	}

}
