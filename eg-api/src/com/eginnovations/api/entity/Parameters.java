package com.eginnovations.api.entity;

/**
 * Entity class for REST request header parameters
 * @author pandian
 *
 */
public class Parameters {
	String managerUrl;
	String user;
	String pwd;
	public String getManagerUrl() {
		return managerUrl;
	}
	public void setManagerUrl(String managerUrl) {
		if (managerUrl!=null && managerUrl.endsWith("/")) {
			managerUrl = managerUrl.substring(0, managerUrl.lastIndexOf("/"));
		}
		this.managerUrl = managerUrl;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	@Override
	public String toString() {
		return "Parameters [managerUrl=" + managerUrl + ", user=" + user + ", pwd=" + pwd + "]";
	}

}
