/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.test;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.opensymphony.xwork2.ActionSupport;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

/**
 *
 * @author stpl
 */
public class Weather extends  ActionSupport implements ServletRequestAware, ServletResponseAware{

    private String location;
    protected HttpServletRequest request;

    public HttpServletRequest getRequest() {
        return request;
    }

    public void setRequest(HttpServletRequest request) {
        this.request = request;
    }

    public HttpServletResponse getResponse() {
        return response;
    }

    public void setResponse(HttpServletResponse response) {
        this.response = response;
    }
    protected HttpServletResponse response;

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    
    public void getWeatherConditon() throws Exception {

        System.out.print("Enter into the method");
        CommonClass obj=new CommonClass();
        JsonObject jsonObject =  obj.detactValidLocation(location);
        JsonArray jsonArray =jsonObject.get("weather").getAsJsonArray();
        JsonObject jo= jsonArray.get(0).getAsJsonObject();
        System.out.print(jo.get("id").getAsString());
        JsonObject jsonObj =  obj.getWeatherTemperature(jo.get("id").getAsString());
        response.setContentType("application/json");
        PrintWriter out= response.getWriter();
        out.print(jsonObj);
	out.flush();
	out.close();
    }

   @Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

   @Override
	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
	}


}
