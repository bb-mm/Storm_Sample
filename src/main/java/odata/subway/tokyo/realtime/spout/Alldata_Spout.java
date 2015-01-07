package odata.subway.tokyo.realtime.spout;
import backtype.storm.spout.SpoutOutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseRichSpout;
import backtype.storm.tuple.Fields;
import backtype.storm.tuple.Values;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import odata.subway.tokyo.realtime.*;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
public class Alldata_Spout extends BaseRichSpout{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private SpoutOutputCollector collector;
	private HashMap<String,ArrayList<String>> subway = new HashMap<String,ArrayList<String>>();
	
	public double getDate() {
		Date date = new Date();
		double t = (double)date.getHours() + ((double)date.getMinutes()-1)/60;
		return t;
	}
	public void getLines() throws ClientProtocolException, IOException, JSONException {
		String url = "http://mingding.chinacloudapp.cn/TokyoSubway/OdataServlet.cn/Ts_lines?$format=json";
		url = url.replaceAll(" ","%20");
		url = url.replaceAll("'","%27");
		HttpClient client = new DefaultHttpClient();
	    HttpGet post = new HttpGet(url);
	    HttpResponse response = client.execute(post);
	    BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
	    String line = rd.readLine();
	    //System.out.println(line);
	    JSONObject jObject  = new JSONObject(line); // json
	    JSONObject data = jObject.getJSONObject("d"); // get data object
	    JSONObject test = new JSONObject(data.toString());
	    JSONArray jsonArray = test.getJSONArray("results");
	    for(int i=0;i<jsonArray.length();i++) {
	    	JSONObject jb = jsonArray.getJSONObject(i);
	    	String num = jb.getString("TS_LineNum");
	    	//System.out.println(num);
	    	subway.put(num, new ArrayList<String>());
	    }
	    //System.out.println(subway.entrySet().size());
	}
	public void getStations() throws ClientProtocolException, IOException, JSONException {
		for(String line:subway.keySet()) {
		String url = "http://mingding.chinacloudapp.cn/TokyoSubway/OdataServlet.cn/Ts_stations?$format=json"
				+ "&$filter=TS_LineNum eq " + line + " and TS_LineOfStationNum ne 0";
		url = url.replaceAll(" ","%20");
		url = url.replaceAll("'","%27");
		HttpClient client = new DefaultHttpClient();
	    HttpGet post = new HttpGet(url);
	    HttpResponse response = client.execute(post);
	    BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
	    String lines = rd.readLine();
	    //System.out.println(lines);
	    JSONObject jObject  = new JSONObject(lines); // json
	    JSONObject data = jObject.getJSONObject("d"); // get data object
	    JSONObject test = new JSONObject(data.toString());
	    JSONArray jsonArray = test.getJSONArray("results");
	    for(int i=0;i<jsonArray.length();i++) {
	    	JSONObject jb = jsonArray.getJSONObject(i);
	    	String num = jb.getString("TS_LineOfStationNum");
	    	//System.out.println(num);
	    	subway.get(line).add(num);
	    }
		}
	}
	public String getEscalatorAttr() throws ClientProtocolException, IOException, JSONException {
		String res = "";
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
	      JSONObject jObject  = new JSONObject(line); // json
	      JSONObject data = jObject.getJSONObject("d"); // get data object
	      JSONObject test = new JSONObject(data.toString());
	      JSONArray jsonArray = test.getJSONArray("results");
	      for(int i=0;i<jsonArray.length();i++) {
	    	  EscalatorAttr ea = new EscalatorAttr();
	    	  JSONObject jb = jsonArray.getJSONObject(i);
	    	  ea.set(Double.parseDouble(jb.getString("State_RollerFriction")), 
	    			  Double.parseDouble(jb.getString("State_Temperature")),
	    			  Integer.parseInt(jb.getString("State_DeviceUpState")));
	    	  res += ea.toString() + "|||";
	      }
		return res.substring(0, res.length()-3);
	}
	@Override
	public void nextTuple() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void open(Map conf, TopologyContext context, SpoutOutputCollector collector) {
		// TODO Auto-generated method stub
		this.collector = collector;
	}
	@Override
	public void declareOutputFields(OutputFieldsDeclarer arg0) {
		// TODO Auto-generated method stub
		
	}
	public static void main(String args[]) throws JSONException, IOException, InterruptedException {
		Alldata_Spout test = new Alldata_Spout();
		test.getLines();
		test.getStations();
		//System.out.println(getEscalatorAttr());
		//getLine();
	}
	
}
