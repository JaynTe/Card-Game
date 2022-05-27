package de.cardgame.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONObject;

import de.cardgame.Main;
import de.cardgame.utils.Logger.LogType;

public class WebRequest {
	
	private static final String USER_AGENT = "APP-" + Main.getApplicationName() + " (APP-VERSION: " 
												+ Main.getApplicationVersion() + ")";
	private static final String BASE_URL = "http://localhost/Card/";
	
	private static final String API_URL = BASE_URL + "v1/";
	
	
	
	public static JSONObject getInventoryFromUserID(int userid, String secretkey) {
			try {
				URL url = new URL(API_URL + "inventory.php?userid=" + userid);
				HttpURLConnection http = (HttpURLConnection) url.openConnection();
				
				http.setRequestMethod("GET");
				http.setRequestProperty("User-Agent", USER_AGENT);
				http.setConnectTimeout(5000);
				http.setReadTimeout(5000);
				
				BufferedReader br = new BufferedReader(new InputStreamReader(http.getInputStream()));
				
				String nextLine;
				StringBuffer response = new StringBuffer();
				while((nextLine = br.readLine()) != null) {
					response.append(nextLine);
				}
				
				br.close();
				http.disconnect();
				
				return new JSONObject(response.toString());
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		
		return new JSONObject("{\"error\": true, \"errormsg\": \"Requesterror\"}");
	}
	
	public static JSONObject checkLoginCredentials(String email, String password) {
		try {
			URL url = new URL(API_URL + "login.php?email=" + email + "&password=" + password);
			HttpURLConnection http = (HttpURLConnection) url.openConnection();
			
			http.setRequestMethod("GET");
			http.setRequestProperty("User-Agent", USER_AGENT);
			http.setConnectTimeout(5000);
			http.setReadTimeout(5000);
			
			BufferedReader br = new BufferedReader(new InputStreamReader(http.getInputStream()));
			
			String nextLine;
			StringBuffer response = new StringBuffer();
			while((nextLine = br.readLine()) != null) {
				response.append(nextLine);
			}
			
			br.close();
			http.disconnect();
			
			return new JSONObject(response.toString());
			
			
		} catch (IOException e) {
			Logger.log("IOException:", LogType.FATAL);
			e.printStackTrace();
		}
		return new JSONObject("{\"error\": true, \"errormsg\": \"Requesterror\"}");
	}
	
}
