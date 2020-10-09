package com.eginnovations.api.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Entity class for Zone mapping
 * @author pandian
 * @see https://www.eginnovations.com/documentation/Automatically-Configuring-the-Target-Environment-using-REST-API/Retrieving-Zone-Details-from-eG-Manager.htm
 *
 */
public class ZoneMapping implements Comparable<ZoneMapping> {
	@JsonProperty ("zone")
	String zone;
	@JsonProperty ("Group")
	List<String> groups;
	
	@JsonProperty ("Service")
	List<String> services;
	
	@JsonProperty ("Segment")
	List<String> segments;
	
	@JsonProperty ("Server")
	List<String> components;

	public String getZone() {
		return zone;
	}

	public void setZone(String zone) {
		this.zone = zone;
	}

	public List<String> getGroups() {
		return groups;
	}

	public void setGroups(List<String> groups) {
		this.groups = groups;
	}

	public List<String> getServices() {
		return services;
	}

	public void setServices(List<String> services) {
		this.services = services;
	}

	public List<String> getSegments() {
		return segments;
	}

	public void setSegments(List<String> segments) {
		this.segments = segments;
	}

	public List<String> getComponents() {
		return components;
	}

	public void setComponents(List<String> components) {
		this.components = components;
	}

	@Override
	public int compareTo(ZoneMapping arg0) {
		return arg0.getZone().compareTo(this.getZone());
	}
}
