package de.cardgame.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONObject;

import de.cardgame.Main;

public class WebRequest {
	
	private static final String USER_AGENT = Main.getServerName() + " (VERSION: " 
												+ Main.getServerVersion() + ")";
	private static final String BASE_URL = "http://localhost/Card/v1/";
	
	
	public static boolean isUserVerified(final int userid, final String secretkey) {
		try {
			URL url = new URL(BASE_URL + "verifyuser.php?userid=" + userid + "&secretkey=" + secretkey);
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
			
			JSONObject jb = new JSONObject(response.toString());
			
			return jb.getBoolean("verified");
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public static JSONObject getUserinfo(final int userid, final String secretkey) {
		try {
			URL url = new URL(BASE_URL + "userinfo.php?userid=" + userid + "&secretkey=" + secretkey);
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
	
}
