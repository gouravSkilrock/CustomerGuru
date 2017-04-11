/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.test;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 *
 * @author stpl
 */
public class CommonClass {

    public JsonObject detactValidLocation(String location){
        StringBuilder reqJson=null;
        JsonObject o = null;
        BufferedReader in = null;
        HttpURLConnection connection=null;
        String APIKEY = "70c6b2700e7357d8b1c812437234cfc6";
        String urlString = "http://api.openweathermap.org/data/2.5/weather?q="+location+"&appid="+APIKEY+"";
        try{
            URL url = new URL(urlString);
              System.out.println(urlString);
			 connection = (HttpURLConnection) url
					.openConnection();
			connection.setDoOutput(true);
			/*
			 * connection.setRequestProperty( "Cookie","JSESSIONID=" +
			 * "12ECA807E0C31320DE59FD6E355369A");
			 */
			OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream());
                        int responseCode = connection.getResponseCode();
			reqJson = new StringBuilder("");
			if (responseCode == 200) {
				in = new BufferedReader(new InputStreamReader(
						connection.getInputStream()));
				String responseString;
				while ((responseString = in.readLine()) != null) {
					reqJson.append(responseString);
				}
                                System.out.println(reqJson);

			} else {
				in = new BufferedReader(new InputStreamReader(
						connection.getErrorStream()));
				String responseString;
				while ((responseString = in.readLine()) != null) {
					reqJson.append(responseString);
				}
				System.out.println(reqJson);
			}

                        JsonParser parser = new JsonParser();
			o = (JsonObject) parser.parse(reqJson.toString());

        }catch(Exception e){
            e.printStackTrace();
        }
        return o;
    }


    public JsonObject getWeatherTemperature(String id){
       System.out.println("*****************************8");
        StringBuilder reqJson=null;
        JsonObject o = null;
        BufferedReader in = null;
        HttpURLConnection connection=null;
        String APIKEY = "70c6b2700e7357d8b1c812437234cfc6";
        String urlString = "http://api.openweathermap.org/data/2.5/forecast/daily?id="+id+"&appid="+APIKEY+"";
        System.out.println(urlString);
        try{
            URL url = new URL(urlString);
			 connection = (HttpURLConnection) url
					.openConnection();
			connection.setDoOutput(true);
			/*
			 * connection.setRequestProperty( "Cookie","JSESSIONID=" +
			 * "12ECA807E0C31320DE59FD6E355369A");
			 */
			OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream());
                        int responseCode = connection.getResponseCode();
			reqJson = new StringBuilder("");
			if (responseCode == 200) {
				in = new BufferedReader(new InputStreamReader(
						connection.getInputStream()));
				String responseString;
				while ((responseString = in.readLine()) != null) {
					reqJson.append(responseString);
				}
                                System.out.println(reqJson);

			} else {
				in = new BufferedReader(new InputStreamReader(
						connection.getErrorStream()));
				String responseString;
				while ((responseString = in.readLine()) != null) {
					reqJson.append(responseString);
				}
				System.out.println(reqJson);
			}

                        JsonParser parser = new JsonParser();
			o = (JsonObject) parser.parse(reqJson.toString());

        }catch(Exception e){
            e.printStackTrace();
        }
        return o;
        
    }
}
