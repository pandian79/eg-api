package com.eginnovations.api.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Entity class for component mapping
 * @author pandian
 *
 */
public class ComponentMapping {
	@JsonProperty ("ComponentType")
	String ComponentType;
	List<String> servers;
	public String getComponentType() {
		return ComponentType;
	}
	public void setComponentType(String componentType) {
		ComponentType = componentType;
	}
	public List<String> getServers() {
		return servers;
	}
	public void setServers(List<String> servers) {
		this.servers = servers;
	}
	
}
