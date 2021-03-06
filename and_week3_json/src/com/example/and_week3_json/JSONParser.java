package com.example.and_week3_json;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

public class JSONParser {

	static InputStream is = null;
	static JSONObject jObj = null;
	static String json = "";
	
	// constructor
	public JSONParser() {
	
	}
	
	// 해당 url로부터 post로 전송된 자료를 반환 
	public JSONObject getJSONFromUrl(String url) {
	// Making HTTP request
		try	 {
	        // defaultHttpClient
	        DefaultHttpClient httpClient = new DefaultHttpClient();
	        HttpPost httpPost = new HttpPost(url);
	
	        HttpResponse httpResponse = httpClient.execute(httpPost);
	        HttpEntity httpEntity = httpResponse.getEntity();
	        is = httpEntity.getContent();                        
		
		} catch (UnsupportedEncodingException e) {
	        e.printStackTrace();
		} catch (ClientProtocolException e) {
	        e.printStackTrace();
		} catch (IOException e) {
	        e.printStackTrace();
		}
		
		// 받아온 데이터를 string으로 변환 
		try {
	        BufferedReader reader = new BufferedReader(new InputStreamReader(is, "iso-8859-1"), 8);
	        StringBuilder sb = new StringBuilder();
	        String line = null;
	        while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
	        }
	        is.close();
	        json = sb.toString();
		} catch (Exception e) {
	        Log.e("Buffer Error", "Error converting result " + e.toString());
		}
		
		// string을 json객체로 변환 
		// try parse the string to a JSON object
		try {
	        jObj = new JSONObject(json);
		} catch (JSONException e) {
	        Log.e("JSON Parser", "Error parsing data " + e.toString());
		}
		
		// return JSON String
        return jObj;
		
		}		
}