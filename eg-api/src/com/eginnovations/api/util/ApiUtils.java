package com.eginnovations.api.util;

import java.util.Arrays;
import java.util.Vector;
import java.util.regex.Pattern;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import com.eginnovations.api.ManagerNotFoundException;
import com.eginnovations.api.UserNotFoundException;
import com.eginnovations.api.entity.Parameters;

/**
 * API Utilities
 * @author pandian
 *
 */
public class ApiUtils {

	/**
	 * Perform basic validations for REST request header parameters
	 * @param parameters
	 */
	public static void validate(Parameters parameters) {
		if (parameters==null)
			throw new IllegalArgumentException("parameters is not provided");
		if (parameters.getManagerUrl()==null)
			throw new ManagerNotFoundException("parameter does not contain managerurl");
		if (parameters.getUser()==null)
			throw new UserNotFoundException("parameter does not contain user");
		if (parameters.getPwd()==null)
			throw new IllegalArgumentException("parameter does not contain base64 encrypted password");
	}

	/**
	 * Convert Parameters to HttpHeaders
	 * @param parameters
	 * @return
	 */
	public static HttpHeaders getHeaders(Parameters parameters) {
		ApiUtils.validate(parameters);
		HttpHeaders headers = new HttpHeaders();
	    headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
	    headers.set("managerurl", parameters.getManagerUrl());
	    headers.set("user", parameters.getUser());
	    headers.set("pwd", parameters.getPwd());
	    headers.set("Content-type", "application/json");
	    headers.set("Accept", "application/json");
	    return headers;
	}

	/**
	 * Tokenize string and add to vector
	 * @param string
	 * @param delimiter
	 * @return
	 */
	public static Vector<String> textToVector(String string, String delimiter) {
		Vector<String> v = new Vector<String>();
		String[] split = string.split(Pattern.quote(delimiter));
		for (String s:split) {
			v.add(s);
		}
		return v;
	}

}
