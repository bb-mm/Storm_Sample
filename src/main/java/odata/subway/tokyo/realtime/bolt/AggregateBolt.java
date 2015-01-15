package odata.subway.tokyo.realtime.bolt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import odata.subway.tokyo.realtime.CMAttr_history;
import odata.subway.tokyo.realtime.ElevatorAttr_history;
import odata.subway.tokyo.realtime.Escalator_history;
import odata.subway.tokyo.realtime.OlingoClienApp;
import odata.subway.tokyo.realtime.TMAttr_history;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.olingo.odata2.api.edm.Edm;
import org.apache.olingo.odata2.api.ep.entry.ODataEntry;
import org.apache.olingo.odata2.api.exception.ODataException;
import org.apache.storm.guava.base.Stopwatch;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import redis.clients.jedis.Jedis;
import backtype.storm.task.OutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseRichBolt;
import backtype.storm.tuple.Tuple;

public class AggregateBolt  extends BaseRichBolt{

	/**
	 * 
	 */
	//private static final Logger LOGGER = LoggerFactory.getLogger(Line_report.class);
	private final long logIntervalInSeconds = 50;  
	//every 50 seconds, I consider that this frame's all data has been emitted and processed. Then I count the line sum and all sum 
	//I will also write the data of current sum to 30-days-history, and update this entry every 50 secnods
	private Stopwatch stopwatch = null;
	private String current_frame;
	private final String HOST = "master";
    private final int PORT = 6379;
    private Jedis redis;
    private HashMap<String,ArrayList<String>> subway;
	//private Stopwatch stopwatch_month = null;
	//private HashMap<String,SumBlock> mapper;
	
	private static final long serialVersionUID = -2454428952684921175L;
	private OutputCollector collector;
	
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
	public void write(Map data,String type) {
		OlingoClienApp clienApp = new OlingoClienApp();
		 String usedFormat = "application/json";
		 String serviceUrl = "http://mingding.chinacloudapp.cn/TokyoSubway/OdataServlet.cn/";
		 try {
			Edm edm = clienApp.readEdm(serviceUrl);
			ODataEntry createdEntry = clienApp.createEntry(edm, serviceUrl, usedFormat, type, data);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ODataException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public double getDate() {
		Date date = new Date();
		double t = (double)date.getHours() + ((double)date.getMinutes()-1)/60;
		return t;
	}
	public void setFrame(String s){
		this.current_frame = s;
	}
	public String getFrame() {
		return current_frame;
	}
	public Map setGateAttr(String line,String num,String name,String all,double temp,double fric,int error,int rej,int ac,int count) {
		Date d = new Date();
		CMAttr_history history = new CMAttr_history();
		history.setTS_GateId("0"); // 0 for Gate
		history.setTS_GateInsertionTimestamp(new Timestamp(d.getDate()));
		history.setTS_HTFrame(String.valueOf(getDate()));
		history.setTS_HTFrameTime(String.valueOf(getDate()));
		history.setTS_GateDeviceName("GateHistory");
		history.setState_UpTime(0);
		history.setState_MotorEngagedTime(0);
		history.setState_DeviceUpState(0);
		history.setState_upDateTime(new Timestamp(d.getDate()));//更新时间
		/* 标记为station记录 */
		history.setTS_HTStationName(name);//站名
		history.setTS_HTLineNum(Integer.parseInt(line));
		history.setTS_HTLineOfStationNum(Integer.parseInt(num));//站号
		history.setTS_HTAllStation(all);
		history.setTS_HTIsSingleDevice("no");
		/*需要统计的属性*/
		history.setState_TicketsRefused(rej);//拒绝刷卡次数(站内拒绝刷卡次数和)
		history.setState_TicketsAccepted(ac);//刷卡次数(站内刷卡次数和)
		history.setState_TicketErrors(error);//错误数(站内错误次数和)
		history.setState_Temperature(temp/(double)count);//刷卡机温度(站内温度平均值)
		history.setState_HingeFriction(fric/(double)count);//传送带摩擦指数(站内摩擦指数平均值)
		history.setState_DeviceUpState(0);//设备运行状态 0 是正常 1 是故障
		history.setState_DeviceUpStateDescription(null);//设备运行状态描述,如"正常" "警告,温度过高" "故障"
		return history.toMap();
	}
	public Map setElevatorAttr(String line,String num,String name,String all,double temp,double vib,int count) {
		Date d = new Date();
		ElevatorAttr_history history = new ElevatorAttr_history();
		 history.setTS_ElevatorId("1"); //1 for elevator
		  history.setTS_ElevatorDeviceName("ElevatorHistory");//设备名
		  history.setTS_ElevatorInsertionTimestamp(new Timestamp(d.getDate()));//设备接入时间
		  history.setTS_HTFrame(String.valueOf(getDate()));//当前帧
		  history.setTS_HTFrameTime(String.valueOf(getDate()));//和TS_HTFrame是相对的记录的是当前(时+分钟/100)如'11.10' 表示11时10分 
		  history.setState_MotorEngagedTime(0);//电机使用次数
		  history.setState_MotorVibration(vib/(double)count);//振动电机    
		  history.setState_upDateTime(new Timestamp(d.getDate()));//更新时间
		  /* 标记为allstation记录 */
		  history.setTS_HTIsSingleDevice("no");//是否是单个设备历史信息'yes'是   'no'或者null就是非
		  history.setTS_HTLineNum(Integer.parseInt(line));//
		  history.setTS_HTLineOfStationNum(Integer.parseInt(num));//line中的第几站,0表示为line的记录
		  history.setTS_HTAllStation(all);//
		  history.setTS_HTStationName(name);
		  /* 需要统计的属性 */
		  history.setState_Temperature(temp/(double)count);//电梯温度
		  history.setState_UpTime(0);//最大运行次数
		  history.setState_DeviceUpState(0);//设备运行状态 0 是正常 1 是故障
		  history.setState_DeviceUpStateDescription("null");//设备运行状态描述,如"正常" "警告,温度过高" "故障"

		return history.toMap();
	}
	public Map setEscalatorAttr(String line,String num,String name,String all,double temp,double fric,int count){
		Date d =new Date();
		Escalator_history history = new Escalator_history();
		 history.setTS_EscalatorInsertionTimestamp(new Timestamp(d.getDate()));
		  history.setTS_EscalatorId("2");//2 for escalator
		  history.setTS_EscalatorDeviceName("EscalatorHistory");
		  history.setState_UpTime(0);
		  history.setTS_HTFrame(String.valueOf(getDate()));
		  history.setTS_HTFrameTime(String.valueOf(getDate()));
		  history.setState_MotorEngagedTime(0);
		  history.setState_upDateTime(new Timestamp(d.getDate()));//跟新时间
		  /* 标记为allstation记录 */
		  history.setTS_HTStationName(name);
		  history.setTS_HTLineNum(Integer.parseInt(line));
		  history.setTS_HTLineOfStationNum(Integer.parseInt(num));
		  history.setTS_HTAllStation(all);
		  history.setTS_HTIsSingleDevice("no");
		  /*需要统计的属性*/
		  history.setState_Temperature(temp/(double)count);//温度
		  history.setState_RollerFriction(fric/(double)count);//扶梯摩擦数
		  history.setState_DeviceUpState(0);//设备运行状态 0 是正常 1 是故障
		  history.setState_DeviceUpStateDescription("null");//设备运行状态描述,如"正常" "警告,温度过高" "故障"
		  return history.toMap();
	}
	public Map setDispenserAttr(String line,String num,String name,String all,double temp,double ink,int tickets,int count) {
		Date d = new Date();
		TMAttr_history history = new TMAttr_history();
		history.setTS_DispenserId("3"); //3 for dispenser
		history.setTS_HTFrame(String.valueOf(getDate()));
		history.setTS_HTFrameTime(String.valueOf(getDate()));
		history.setTS_DispenserInsertionTimestamp(new Timestamp(d.getDate()));
		history.setTS_DispenserDeviceName("DispenserHistory");
		history.setState_UpTime(0);
		history.setState_MotorEngagedTime(0);
		history.setState_upDateTime(new Timestamp(d.getDate()));//更新时间
		      /* 标记为line记录 */
		history.setTS_HTStationName(name);
		history.setTS_HTLineNum(Integer.parseInt(line));//线号
		history.setTS_HTAllStation(all);
		history.setTS_HTLineOfStationNum(Integer.parseInt(num));
		history.setTS_HTIsSingleDevice("no");//表示不是单个设备记录
		      /* 需要统计的属性 */
		history.setState_RemainingTickets(tickets);//售票机剩余票数(站内所有售票机的剩余票数总数)
		history.setState_RemainingInk(ink/(double)count);//售票机剩余墨水(剩余墨水品均值)
		history.setState_Temperature(temp/(double)count);//售票机温度(温度平均值)
		history.setState_DeviceUpState(0);//设备运行状态 0 是正常 1 是故障
		history.setState_DeviceUpStateDescription("null");//设备运行状态描述,如"正常" "警告,温度过高" "故障"


		return history.toMap();
	}
	public void calcGate(String frame) {
		Date d = new Date();
		double temp_allLine=0;
		double temp_all=0;
		double fric_allLine=0;
		double fric_all=0;
		int error_allLine=0;
		int error_all=0;
		int ac_allLine=0;
		int ac_all=0;
		int rej_allLine=0;
		int rej_all=0;
		int count_allLine=0;
		int count_all=0;
		for(String line:subway.keySet()) {
			for(String num:subway.get(line)) {
				String key_sum_temp=frame+"_"+line+"_"+num+"_gate_"+"sum_temperature";
				String key_sum_error=frame+"_"+line+"_"+num+"_gate_"+"sum_error";
				String key_sum_ac=frame+"_"+line+"_"+num+"_gate_"+"sum_ac";
				String key_sum_rej=frame+"_"+line+"_"+num+"_gate_"+"sum_rej";
				String key_sum_fric=frame+"_"+line+"_"+num+"_gate_"+"sum_friction";
				String key_count=frame+"_"+line+"_"+num+"_gate_"+"count";
				double res_temp = Double.parseDouble(redis.get(key_sum_temp));
				int res_error = Integer.parseInt(redis.get(key_sum_error));
				int res_ac = Integer.parseInt(redis.get(key_sum_ac));
				int res_rej = Integer.parseInt(redis.get(key_sum_rej));
				double res_fric = Double.parseDouble(redis.get(key_sum_fric));
				int count = Integer.parseInt(redis.get(key_count));
				temp_allLine+=res_temp;
				fric_allLine+=res_fric;
				error_allLine +=res_error;
				ac_allLine += res_ac;
				rej_allLine += res_rej;
				count_allLine += count;
				Map data = setGateAttr(line,num,num,"null",res_temp,res_fric,res_error,res_rej,res_ac,count);
				//write(data,"Ts_thirtydaygatehistorys");
				write(data,"Ts_gatehistorys");
			}
			temp_all += temp_allLine;
			fric_all += fric_allLine;
			error_all += error_allLine;
			rej_all += rej_allLine;
			ac_all += ac_allLine;
			count_all += count_allLine;
			Map data = setGateAttr(line,"0","null","null",temp_allLine,fric_allLine,error_allLine,rej_allLine,ac_allLine,count_allLine);
			//write(data,"Ts_thirtydaygatehistorys");
			write(data,"Ts_gatehistorys");
		}
		Map data = setGateAttr("0","0","null","allstation",temp_all,fric_all,error_all,rej_all,ac_all,count_all);
		//write(data,"Ts_thirtydaygatehistorys");
		write(data,"Ts_gatehistorys");
	}
	public void calcDispenser(String frame) {
		Date d = new Date();
		double temp_allLine=0;
		double temp_all=0;
		double ink_allLine=0;
		double ink_all=0;
		int tickets_allLine=0;
		int tickets_all=0;
		int count_allLine=0;
		int count_all=0;
		for(String line:subway.keySet()) {
			for(String num:subway.get(line)) {
				String key_sum_temp=frame+"_"+line+"_"+num+"_dispenser_"+"sum_temperature";
				String key_sum_tickets=frame+"_"+line+"_"+num+"_dispenser_"+"sum_tickets";
				String key_sum_ink=frame+"_"+line+"_"+num+"_dispenser_"+"sum_ink";
				String key_count=frame+"_"+line+"_"+num+"_dispenser_"+"count";
				double res_temp = Double.parseDouble(redis.get(key_sum_temp));
				int res_tickets = Integer.parseInt(redis.get(key_sum_tickets));
				double res_ink = Double.parseDouble(redis.get(key_sum_ink));
				int count = Integer.parseInt(redis.get(key_count));
				temp_allLine+=res_temp;
				ink_allLine+=res_ink;
				tickets_allLine +=res_tickets;
				count_allLine += count;
				Map data = setDispenserAttr(line,num,num,"null",res_temp,res_ink,res_tickets,count);
				//write(data,"Ts_thirtydaygatehistorys");
				write(data,"Ts_dispenserhistorys");
			}
			temp_all += temp_allLine;
			ink_all += ink_allLine;
			tickets_all += tickets_allLine;
			count_all += count_allLine;
			Map data = setDispenserAttr(line,"0","null","null",temp_allLine,ink_allLine,tickets_allLine,count_allLine);
			//write(data,"Ts_thirtydaygatehistorys");
			write(data,"Ts_dispenserhistorys");
		}
		Map data = setDispenserAttr("0","0","null","allstation",temp_all,ink_all,tickets_all,count_all);
		//write(data,"Ts_thirtydaygatehistorys");
		write(data,"Ts_dispenserhistorys");
	}
	public void calcElevator(String frame) {
		Date d = new Date();
		double temp_allLine=0;
		double temp_all=0;
		double vib_allLine=0;
		double vib_all=0;
		int count_allLine=0;
		int count_all=0;
		for(String line:subway.keySet()) {
			for(String num:subway.get(line)) {
				String key_sum_temp=frame+"_"+line+"_"+num+"_elevator_"+"sum_temperature";
				String key_sum_vib=frame+"_"+line+"_"+num+"_elevator_"+"sum_vibration";
				String key_count=frame+"_"+line+"_"+num+"_elevator_"+"count";
				double res_temp = Double.parseDouble(redis.get(key_sum_temp));
				double res_vib = Double.parseDouble(redis.get(key_sum_vib));
				int count = Integer.parseInt(redis.get(key_count));
				temp_allLine+=res_temp;
				vib_allLine+=res_vib;
				count_allLine += count;
				Map data = setElevatorAttr(line,num,num,"null",res_temp,res_vib,count);
				//write(data,"Ts_thirtydaygatehistorys");
				write(data,"Ts_elevatorhistorys");
			}
			temp_all += temp_allLine;
			vib_all += vib_allLine;
			count_all += count_allLine;
			Map data = setElevatorAttr(line,"0","null","null",temp_allLine,vib_allLine,count_allLine);
			//write(data,"Ts_thirtydaygatehistorys");
			write(data,"Ts_elevatorhistorys");
		}
		Map data = setElevatorAttr("0","0","null","allstation",temp_all,vib_allLine,count_all);
		//write(data,"Ts_thirtydaygatehistorys");
		write(data,"Ts_elevatorhistorys");
	}
	public void calcEscalator(String frame) {
		Date d = new Date();
		double temp_allLine=0;
		double temp_all=0;
		double fric_allLine=0;
		double fric_all=0;
		int count_allLine=0;
		int count_all=0;
		for(String line:subway.keySet()) {
			for(String num:subway.get(line)) {
				String key_sum_temp=frame+"_"+line+"_"+num+"_escalator_"+"sum_temperature";
				String key_sum_fric=frame+"_"+line+"_"+num+"_escalator_"+"sum_friction";
				String key_count=frame+"_"+line+"_"+num+"_escalator_"+"count";
				double res_temp = Double.parseDouble(redis.get(key_sum_temp));
				double res_fric = Double.parseDouble(redis.get(key_sum_fric));
				int count = Integer.parseInt(redis.get(key_count));
				temp_allLine+=res_temp;
				fric_allLine+=res_fric;
				count_allLine += count;
				Map data = setElevatorAttr(line,num,num,"null",res_temp,res_fric,count);
				//write(data,"Ts_thirtydaygatehistorys");
				write(data,"Ts_escalatorhistorys");
			}
			temp_all += temp_allLine;
			fric_all += fric_allLine;
			count_all += count_allLine;
			Map data = setElevatorAttr(line,"0","null","null",temp_allLine,fric_allLine,count_allLine);
			//write(data,"Ts_thirtydaygatehistorys");
			write(data,"Ts_escalatorhistorys");
		}
		Map data = setElevatorAttr("0","0","null","allstation",temp_all,fric_allLine,count_all);
		//write(data,"Ts_thirtydaygatehistorys");
		write(data,"Ts_escalatorhistorys");
	}
	@Override
	public void execute(Tuple tuple) {
		// TODO Auto-generated method stub
		String frame = tuple.getString(0);
		if(logIntervalInSeconds <= stopwatch.elapsed(TimeUnit.SECONDS)) {
			try {
				getLines();
				getStations();
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
			
			if(Double.parseDouble(getFrame()) > Double.parseDouble(frame)) {
				setFrame(frame);
			}
			calcGate(getFrame());
			calcDispenser(getFrame());
			calcElevator(getFrame());
			calcEscalator(getFrame());
			this.stopwatch.reset();
			this.stopwatch.start();
		}
		
	}

	@Override
	public void prepare(Map conf, TopologyContext context, OutputCollector collector) {
		// TODO Auto-generated method stub
		this.collector = collector;
		this.stopwatch = Stopwatch.createStarted();
		this.current_frame = String.valueOf(getDate());
		this.redis = new Jedis(HOST,PORT);
		this.subway = new HashMap<String,ArrayList<String>>();
		//this.stopwatch_month = Stopwatch.createStarted();
	}

	@Override
	public void declareOutputFields(OutputFieldsDeclarer declarer) {
		// TODO Auto-generated method stub
		
	}

}
