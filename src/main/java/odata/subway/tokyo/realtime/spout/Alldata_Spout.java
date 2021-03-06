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
import java.sql.Timestamp;
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
	public JSONArray connect(String url,String frame) throws ClientProtocolException, IOException, JSONException {
		//String frame = Double.toString(getDate());
		String temp_url = url +"'" +frame+ "'";
		//System.out.println(temp_url);
		HttpClient client = new DefaultHttpClient();
	    HttpGet post = new HttpGet(temp_url);
	    HttpResponse response = client.execute(post);
	    BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
	    String line = rd.readLine();
	    //System.out.println(line);
	    JSONObject jObject  = new JSONObject(line); // json
	    JSONObject data = jObject.getJSONObject("d"); // get data object
	    JSONObject test = new JSONObject(data.toString());
	    JSONArray jsonArray = test.getJSONArray("results");
	    
	    return jsonArray;
	}
	public String getEscalatorAttr(String sub_line,String stat_num,String frame) throws ClientProtocolException, IOException, JSONException {
		String res = "";
		String url = "http://mingding.chinacloudapp.cn/TokyoSubway/OdataServlet.cn/Ts_escalatorhistorys?"
				+ "$format=json&$filter=TS_HTIsSingleDevice eq 'yes' and TS_HTLineNum eq 4 and TS_HTLineOfStationNum eq 16 and TS_HTFrame eq ";
		url = url.replaceAll(" ","%20");
		url = url.replaceAll("'","%27");
		url = url.replaceAll("TS_HTLineNum eq 4", "TS_HTLineNum eq "+sub_line);
		url = url.replaceAll("TS_HTLineOfStationNum eq 16", "TS_HTLineOfStationNum eq "+stat_num);
		//System.out.println(url);
	    JSONArray jsonArray = connect(url,frame);
	    Date d = new Date();
	    if(jsonArray.length() == 0)
	    	return "BBMM";
	    for(int i=0;i<jsonArray.length();i++) {
	    	  EscalatorAttr ea = new EscalatorAttr();
	    	  JSONObject jb = jsonArray.getJSONObject(i);
	    	  ea.fromJSON(jb);
	    	  ea.setTS_EscalatorInsertionTimestamp(new Timestamp(d.getDate()));
	    	  ea.setState_upDateTime(new Timestamp(d.getDate()));
//	    	  ea.set(Double.parseDouble(jb.getString("State_RollerFriction")), 
//	    			  Double.parseDouble(jb.getString("State_Temperature")),
//	    			  Integer.parseInt(jb.getString("State_DeviceUpState")));
	    	  res += ea.toString() + "BBMM";
	    }
		//return res.substring(0, res.length()-3);
	    return res.substring(0, res.length()-4);
	}
	public String getElevatorAttr(String sub_line,String stat_num,String frame) throws ClientProtocolException, IOException, JSONException {
		String res="";
		String url = "http://mingding.chinacloudapp.cn/TokyoSubway/OdataServlet.cn/Ts_elevatorhistorys?"
				+ "$format=json&$filter=TS_HTIsSingleDevice eq 'yes' and TS_HTLineNum eq 4 and TS_HTLineOfStationNum eq 16 and TS_HTFrame eq ";
		url = url.replaceAll(" ","%20");
		url = url.replaceAll("'","%27");
		url = url.replaceAll("TS_HTLineNum eq 4", "TS_HTLineNum eq "+sub_line);
		url = url.replaceAll("TS_HTLineOfStationNum eq 16", "TS_HTLineOfStationNum eq "+stat_num);
		JSONArray jsonArray = connect(url,frame);
		Date d = new Date();
		if(jsonArray.length() == 0)
	    	return "BBMM";
		for(int i=0;i<jsonArray.length();i++) {
	    	  ElevatorAttr ea = new ElevatorAttr();
	    	  JSONObject jb = jsonArray.getJSONObject(i);
	    	  ea.fromJSON(jb);
	    	  ea.setTS_ElevatorInsertionTimestamp(new Timestamp(d.getDate()));
	    	  ea.setState_upDateTime(new Timestamp(d.getDate()));
//	    	  ea.set(Double.parseDouble(jb.getString("State_MotorVibration")), 
//	    			  Double.parseDouble(jb.getString("State_Temperature")),
//	    			  Integer.parseInt(jb.getString("State_DeviceUpState")));
	    	  res += ea.toString() + "BBMM";
	    }
		return res.substring(0, res.length()-4);
	}
	public String getCMAttr(String sub_line,String stat_num,String frame) throws ClientProtocolException, IOException, JSONException {
		String res = "";
		String url = "http://mingding.chinacloudapp.cn/TokyoSubway/OdataServlet.cn/Ts_gatehistorys?"
				+ "$format=json&$filter=TS_HTIsSingleDevice eq 'yes' and TS_HTLineNum eq 4 and TS_HTLineOfStationNum eq 16 and TS_HTFrame eq ";
		url = url.replaceAll(" ","%20");
		url = url.replaceAll("'","%27");
		url = url.replaceAll("TS_HTLineNum eq 4", "TS_HTLineNum eq "+sub_line);
		url = url.replaceAll("TS_HTLineOfStationNum eq 16", "TS_HTLineOfStationNum eq "+stat_num);
		JSONArray jsonArray = connect(url,frame);
		Date d = new Date();
		if(jsonArray.length() == 0)
	    	return "BBMM";
		for(int i=0;i<jsonArray.length();i++) {
	    	  CMAttr cma = new CMAttr();
	    	  JSONObject jb = jsonArray.getJSONObject(i);
	    	  cma.fromJSON(jb);
	    	  cma.setState_upDateTime(new Timestamp(d.getDate()));
	    	  cma.setTS_GateInsertionTimestamp(new Timestamp(d.getDate()));
//	    	  cma.set(Double.parseDouble(jb.getString("State_HingeFriction")), 
//	    			  Double.parseDouble(jb.getString("State_Temperature")),
//	    			  Integer.parseInt(jb.getString("State_TicketErrors")),
//	    			  Integer.parseInt(jb.getString("State_TicketsAccepted")),
//	    			  Integer.parseInt(jb.getString("State_TicketsRefused")),
//	    			  Integer.parseInt(jb.getString("State_DeviceUpState")));
	    	  res += cma.toString() + "BBMM";
	    }
		return res.substring(0, res.length()-4);
	}
	public String getPeopleAttr(String sub_line,String stat_num,String frame) throws ClientProtocolException, IOException, JSONException {
		String res = "";
		String url = "http://mingding.chinacloudapp.cn/TokyoSubway/OdataServlet.cn/Ts_totalpassengerhistorys?"
			 + "$format=json&$filter=TS_HTLineNum eq 4 and TS_HTLineOfStationNum eq 16 and TS_HTFrame eq";
		url = url.replaceAll(" ","%20");
		url = url.replaceAll("'","%27");
		url = url.replaceAll("TS_HTLineNum eq 4", "TS_HTLineNum eq "+sub_line);
		url = url.replaceAll("TS_HTLineOfStationNum eq 16", "TS_HTLineOfStationNum eq "+stat_num);
		JSONArray jsonArray = connect(url,frame);
		Date d = new Date();
		//System.out.println(jsonArray.length());
		if(jsonArray.length() == 0)
	    	return "BBMM";
		for(int i=0;i<jsonArray.length();i++) {
	    	  PeopleHistory ph = new PeopleHistory();
	    	  JSONObject jb = jsonArray.getJSONObject(i);
	    	  ph.fromJSON(jb);
//	    	  tma.set(Double.parseDouble(jb.getString("State_RemainingInk")), 
//	    			  Integer.parseInt(jb.getString("State_RemainingTickets")),
//	    			  Double.parseDouble(jb.getString("State_Temperature")),
//	    			  Integer.parseInt(jb.getString("State_DeviceUpState")));
	    	  res += ph.toString() + "BBMM";
	    }
		return res.substring(0, res.length()-4);
	}
	public String getTMAttr(String sub_line,String stat_num,String frame) throws ClientProtocolException, IOException, JSONException {
		String res = "";
		String url = "http://mingding.chinacloudapp.cn/TokyoSubway/OdataServlet.cn/Ts_dispenserhistorys?"
				+ "$format=json&$filter=TS_HTIsSingleDevice eq 'yes' and TS_HTLineNum eq 4 and TS_HTLineOfStationNum eq 16 and TS_HTFrame eq ";
		url = url.replaceAll(" ","%20");
		url = url.replaceAll("'","%27");
		url = url.replaceAll("TS_HTLineNum eq 4", "TS_HTLineNum eq "+sub_line);
		url = url.replaceAll("TS_HTLineOfStationNum eq 16", "TS_HTLineOfStationNum eq "+stat_num);
		JSONArray jsonArray = connect(url,frame);
		Date d = new Date();
		if(jsonArray.length() == 0)
	    	return "BBMM";
		for(int i=0;i<jsonArray.length();i++) {
	    	  TMAttr tma = new TMAttr();
	    	  JSONObject jb = jsonArray.getJSONObject(i);
	    	  tma.fromJSON(jb);
	    	  tma.setState_upDateTime(new Timestamp(d.getDate()));
	    	  tma.setTS_DispenserInsertionTimestamp(new Timestamp(d.getDate()));
//	    	  tma.set(Double.parseDouble(jb.getString("State_RemainingInk")), 
//	    			  Integer.parseInt(jb.getString("State_RemainingTickets")),
//	    			  Double.parseDouble(jb.getString("State_Temperature")),
//	    			  Integer.parseInt(jb.getString("State_DeviceUpState")));
	    	  res += tma.toString() + "BBMM";
	    }
		return res.substring(0, res.length()-4);
	}
	public void es_test() {
		String frame = Double.toString(getDate());
		for(String line:subway.keySet()) {
			for(String num:subway.get(line)) {
				try {
					System.out.println(getEscalatorAttr(line,num,frame));
				} catch (ClientProtocolException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	public void people_test() {
		String frame = Double.toString(getDate());
		for(String line:subway.keySet()) {
			for(String num:subway.get(line)) {
				try {
					System.out.println(getPeopleAttr(line,num,frame));
				} catch (ClientProtocolException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	public void el_test() {
		String frame = Double.toString(getDate());
		for(String line:subway.keySet()) {
			for(String num:subway.get(line)) {
				try {
					System.out.println(getElevatorAttr(line,num,frame));
				} catch (ClientProtocolException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	public void cma_test() {
		String frame = Double.toString(getDate());
		for(String line:subway.keySet()) {
			for(String num:subway.get(line)) {
				try {
					System.out.println(getCMAttr(line,num,frame));
				} catch (ClientProtocolException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	public void tma_test() {
		String frame = Double.toString(getDate());
		for(String line:subway.keySet()) {
			for(String num:subway.get(line)) {
				try {
					System.out.println(getTMAttr(line,num,frame));
				} catch (ClientProtocolException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	@Override
	public void nextTuple() {
		
		String frame = Double.toString(getDate());
		for(String line:subway.keySet()) {
			for(String num:subway.get(line)) {
				String escalator="",elevator="",cm="",tm="",people="";
				try {
					escalator = getEscalatorAttr(line,num,frame);
					elevator = getElevatorAttr(line,num,frame);
					cm = getCMAttr(line,num,frame);
					tm = getTMAttr(line,num,frame);
					people = getPeopleAttr(line,num,frame);
//					System.out.println("+++++++++"+escalator);
//					System.out.println("+++++++++"+elevator);
//					System.out.println("+++++++++"+cm);
//					System.out.println("+++++++++"+tm);
				} catch (ClientProtocolException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} finally {
					if(escalator.length() < 3 || elevator.length() < 3 || cm.length() < 3 || tm.length() < 3
							|| people.length() < 3) {
					escalator = "BBMM";
					elevator = "BBMM";
					cm = "BBMM";
					tm = "BBMM";
					people = "BBMM";
					}
				}
				collector.emit(new Values(line,num,frame,escalator,elevator,tm,cm,people));
			}
		}
		try {
			Thread.sleep(60000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//System.out.println(escalator+elevator+cm+tm);
		
	}
	@Override
	public void open(Map conf, TopologyContext context, SpoutOutputCollector collector) {
		// TODO Auto-generated method stub
		this.collector = collector;
		try {
			this.getLines();
			this.getStations();
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Override
	public void declareOutputFields(OutputFieldsDeclarer declarer) {
		// TODO Auto-generated method stub
		declarer.declare(new Fields("Line","Num","Frame","Escalator","Elevator","TicketMachine","CheckMachine","People"));
	}
	public static void main(String args[]) throws JSONException, IOException, InterruptedException {
		Alldata_Spout test = new Alldata_Spout();
		test.getLines();
		test.getStations();
//		test.tma_test();
//		test.cma_test();
//		test.el_test();
//		test.es_test();
		test.people_test();
//		ElevatorAttr es = new ElevatorAttr();
//		String info = test.getElevatorAttr("4","16","16.9");
//		System.out.println(info);
//		String[] infos = info.split("BBMM");
//		for(int i=0;i<infos.length;i++){
//			System.out.println(infos[i]);
//			es.fromString(infos[i]);
//		}
		//test.nextTuple();
		//System.out.println(getEscalatorAttr());
		//getLine();
	}
	
}
