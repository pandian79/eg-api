package com.eginnovations.api.entity;

import java.util.Vector;

/**
 * Entity class for Remote or External Agent
 * @author pandian
 *
 */
public class Agent implements Comparable<Agent> {
	public static final String EXTERNAL = "externalagent";
	public static final String REMOTE = "remoteagent";
	String agentName;
	String hostIp;
	
	public String getAgentName() {
		return agentName;
	}
	public void setAgentName(String agentName) {
		this.agentName = agentName;
	}
	public String getHostIp() {
		return hostIp;
	}
	public void setHostIp(String hostIp) {
		this.hostIp = hostIp;
	}
	public Vector<String> getAsVector() {
		Vector<String> v = new Vector<String>();
		v.add(this.agentName);
		v.add(this.hostIp);
		return v;
	}
	@Override
	public int compareTo(Agent arg0) {
		Agent a = (Agent) arg0;
		return a.getAgentName().toLowerCase().compareTo(this.getAgentName().toLowerCase());
	}
	@Override
	public String toString() {
		return "Agent [agentName=" + agentName + ", hostIp=" + hostIp + "]";
	}
	

}
