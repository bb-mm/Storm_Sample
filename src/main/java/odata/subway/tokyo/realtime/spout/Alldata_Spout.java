package odata.subway.tokyo.realtime.spout;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Date;
import java.util.Iterator;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
public class Alldata_Spout {
	public static double getDate() {
		Date date = new Date();
		double t = (double)date.getHours() + ((double)date.getMinutes()-1)/60;
		return t;
	}
	public static void getAttr() throws ClientProtocolException, IOException, JSONException {
		String url = "http://mingding.chinacloudapp.cn/TokyoSubway/OdataServlet.cn/Ts_escalatorhistorys?"
				+ "$format=json&$filter=TS_HTIsSingleDevice eq 'yes' and TS_HTLineNum eq 4 and TS_HTLineOfStationNum eq 16 and TS_HTFrame eq ";
		url = url.replaceAll(" ","%20");
		url = url.replaceAll("'","%27");
		String frame = Double.toString(getDate());
		String temp_url = url +"'" +frame+ "'";
		HttpClient client = new DefaultHttpClient();
	    HttpGet post = new HttpGet(temp_url);
	    HttpResponse response = client.execute(post);
	      BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
	      String line = rd.readLine();
	      System.out.println(line);
	      JSONObject jObject  = new JSONObject(line); // json
	      JSONObject data = jObject.getJSONObject("d"); // get data object
	      JSONObject test = new JSONObject(data.toString());
	      JSONArray jsonArray = test.getJSONArray("results");
	      System.out.println(jsonArray);
	      for(int i=0;i<jsonArray.length();i++) {
	    	  JSONObject jb = jsonArray.getJSONObject(i);
	    	  System.out.println(frame+" State_Temperature: " + jb.getString("State_Temperature"));
	    	  System.out.println(frame+" TS_EscalatorDeviceName: " + jb.getString("TS_EscalatorDeviceName"));
	      }
		return;
	}
	public static void main(String args[]) throws JSONException, IOException, InterruptedException {
		getAttr();
	}
}
