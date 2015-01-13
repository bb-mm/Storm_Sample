package odata.subway.tokyo.realtime.bolt;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.apache.olingo.odata2.api.edm.Edm;
import org.apache.olingo.odata2.api.ep.entry.ODataEntry;
import org.apache.olingo.odata2.api.exception.ODataException;

import odata.subway.tokyo.realtime.CMAttr;
import odata.subway.tokyo.realtime.ElevatorAttr;
import odata.subway.tokyo.realtime.OlingoClienApp;
import odata.subway.tokyo.realtime.EscalatorAttr;
import odata.subway.tokyo.realtime.TMAttr;
import backtype.storm.task.OutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseRichBolt;
import backtype.storm.tuple.Fields;
import backtype.storm.tuple.Tuple;
import backtype.storm.tuple.Values;
import redis.clients.jedis.Jedis;
public class CalculatorBolt  extends BaseRichBolt{

	/**
	 * this bolt is used to calculate the average number of 
	 */
	
	private static final long serialVersionUID = 1L;
	private OutputCollector collector;
	private final String HOST = "master";
    private final int PORT = 6379;
    private Jedis redis;
	//private HashMap<String,HashMap<String,>>
	//private double avg_friction;
	//private double avg_temperature;
	//TODO use local var
	public static String getUUID(){ 
        return UUID.randomUUID().toString(); 
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
	public void executeEscalator(String line,String num,String frame,String info){
		String key_sum_temp=frame+"_"+line+"_"+num+"_escalator_"+"sum_temperature";
		String key_sum_fric=frame+"_"+line+"_"+num+"_escalator_"+"sum_friction";
		String key_count=frame+"_"+line+"_"+num+"_escalator_"+"count";
		String key_sum_temp_now=frame+"_"+line+"_"+num+"_escalator_"+"sum_temperature_now";
		String key_sum_fric_now=frame+"_"+line+"_"+num+"_escalator_"+"sum_friction_now";
		String key_count_now=frame+"_"+line+"_"+num+"_escalator_"+"count_now";
		double sum_temperature=0;
		double sum_friction=0;
		int count=0;
		String[] escalator = info.split("BBMM");
		count=escalator.length;
		EscalatorAttr history = new EscalatorAttr();
		for(int i=0;i<count;i++) {
			//String[] escalator_attr = escalator[i].split("+");
//			EscalatorAttr ea = new EscalatorAttr(escalator[i]);
//			sum_temperature += ea.State_Temperature;
//			sum_friction += ea.State_RollerFriction;
			EscalatorAttr es = new EscalatorAttr();
			es.fromString(escalator[i]);
			if(i==count-1)
				history = es;
			sum_temperature += es.getState_Temperature();
			sum_friction += es.getState_RollerFriction();
		}
		if(redis.get(key_sum_temp) == null){
			redis.set(key_sum_temp, String.valueOf(sum_temperature));
			redis.set(key_sum_temp_now, String.valueOf(sum_temperature));
		}
		else{
			double temp_value = Double.parseDouble(redis.get(key_sum_temp)) + sum_temperature;
			redis.set(key_sum_temp, String.valueOf(temp_value));
			redis.set(key_sum_temp_now, String.valueOf(sum_temperature));
		}
		if(redis.get(key_sum_fric) == null){
			redis.set(key_sum_fric, String.valueOf(sum_friction));
			redis.set(key_sum_fric_now, String.valueOf(sum_friction));
		}
		else{
			double temp_value = Double.parseDouble(redis.get(key_sum_fric)) + sum_friction;
			redis.set(key_sum_fric, String.valueOf(temp_value));
			redis.set(key_sum_fric_now, String.valueOf(sum_friction));
		}
		if(redis.get(key_count) == null){
			redis.set(key_count, String.valueOf(count));
			redis.set(key_count_now, String.valueOf(count));
		}
		else{
			int temp_value = Integer.parseInt(redis.get(key_count)) + count;
			redis.set(key_count, String.valueOf(temp_value));
			redis.set(key_count_now, String.valueOf(count));
		}
		history.setTS_EscalatorDeviceName("AVG");
		history.setTS_HTIsSingleDevice("no");
		history.setState_RollerFriction(sum_friction/(double)count);
		history.setState_Temperature(sum_temperature/(double)count);
		history.setTS_EscalatorId(getUUID());
		Map record = history.toMap();
		write(record,"Ts_escalatorhistorys"); //write the result to SQL Server
		collector.emit(new Values(frame));
//		String Sum = String.valueOf(sum_temperature) +"+"+ String.valueOf(sum_friction);
//		String Count = String.valueOf(count);
//		collector.emit(new Values(line,num,frame,Sum,Count,info,"escalator")); //emit the data to next bolt for line sum and all sum
	}
	public void executeElevator(String line,String num,String frame,String info){
		String key_sum_temp=frame+"_"+line+"_"+num+"_elevator_"+"sum_temperature";
		String key_sum_vib=frame+"_"+line+"_"+num+"_elevator_"+"sum_vibration";
		String key_count=frame+"_"+line+"_"+num+"_elevator_"+"count";
		String key_sum_temp_now=frame+"_"+line+"_"+num+"_elevator_"+"sum_temperature_now";
		String key_sum_vib_now=frame+"_"+line+"_"+num+"_elevator_"+"sum_vibration_now";
		String key_count_now=frame+"_"+line+"_"+num+"_elevator_"+"count_now";
		double sum_temperature=0;
		double sum_vibration=0;
		int count=0;
		String[] elevator = info.split("BBMM");
		count=elevator.length;
		ElevatorAttr history = new ElevatorAttr();
		for(int i=0;i<count;i++) {
			//String[] escalator_attr = escalator[i].split("+");
//			EscalatorAttr ea = new EscalatorAttr(escalator[i]);
//			sum_temperature += ea.State_Temperature;
//			sum_friction += ea.State_RollerFriction;
			ElevatorAttr es = new ElevatorAttr();
			es.fromString(elevator[i]);
			if(i==count-1)
				history = es;
			sum_temperature += es.getState_Temperature();
			sum_vibration += es.getState_MotorVibration();
		}
		if(redis.get(key_sum_temp) == null){
			redis.set(key_sum_temp, String.valueOf(sum_temperature));
			redis.set(key_sum_temp_now, String.valueOf(sum_temperature));
		}
		else{
			double temp_value = Double.parseDouble(redis.get(key_sum_temp)) + sum_temperature;
			redis.set(key_sum_temp, String.valueOf(temp_value));
			redis.set(key_sum_temp_now, String.valueOf(sum_temperature));
		}
		if(redis.get(key_sum_vib) == null){
			redis.set(key_sum_vib, String.valueOf(sum_vibration));
			redis.set(key_sum_vib_now, String.valueOf(sum_vibration));
		}
		else{
			double temp_value = Double.parseDouble(redis.get(key_sum_vib)) + sum_vibration;
			redis.set(key_sum_vib, String.valueOf(temp_value));
			redis.set(key_sum_vib_now, String.valueOf(sum_vibration));
		}
		if(redis.get(key_count) == null){
			redis.set(key_count, String.valueOf(count));
			redis.set(key_count_now, String.valueOf(count));
		}
		else{
			int temp_value = Integer.parseInt(redis.get(key_count)) + count;
			redis.set(key_count, String.valueOf(temp_value));
			redis.set(key_count_now, String.valueOf(count));
		}
		history.setTS_ElevatorDeviceName("AVG");
		history.setTS_HTIsSingleDevice("no");
		history.setState_MotorVibration(sum_vibration/(double)count);
		history.setState_Temperature(sum_temperature/(double)count);
		history.setTS_ElevatorId(getUUID());
		Map record = history.toMap();
		write(record,"Ts_elevatorhistorys"); //write the result to SQL Server
		collector.emit(new Values(frame));
	}
	public void executeGate(String line,String num,String frame,String info){
		String key_sum_temp=frame+"_"+line+"_"+num+"_gate_"+"sum_temperature";
		String key_sum_error=frame+"_"+line+"_"+num+"_gate_"+"sum_error";
		String key_sum_ac=frame+"_"+line+"_"+num+"_gate_"+"sum_ac";
		String key_sum_rej=frame+"_"+line+"_"+num+"_gate_"+"sum_rej";
		String key_sum_fric=frame+"_"+line+"_"+num+"_gate_"+"sum_friction";
		String key_count=frame+"_"+line+"_"+num+"_gate_"+"count";
		String key_sum_temp_now=frame+"_"+line+"_"+num+"_gate_"+"sum_temperature_now";
		String key_sum_error_now=frame+"_"+line+"_"+num+"_gate_"+"sum_error_now";
		String key_sum_ac_now=frame+"_"+line+"_"+num+"_gate_"+"sum_ac_now";
		String key_sum_rej_now=frame+"_"+line+"_"+num+"_gate_"+"sum_rej_now";
		String key_sum_fric_now=frame+"_"+line+"_"+num+"_gate_"+"sum_friction_now";
		String key_count_now=frame+"_"+line+"_"+num+"_gate_"+"count_now";
		double sum_temperature=0;
		double sum_friction=0;
		int sum_ac=0;
		int sum_error=0;
		int sum_rej=0;
		int count=0;
		String[] gate = info.split("BBMM");
		count=gate.length;
		CMAttr history = new CMAttr();
		for(int i=0;i<count;i++) {
			//String[] escalator_attr = escalator[i].split("+");
//			EscalatorAttr ea = new EscalatorAttr(escalator[i]);
//			sum_temperature += ea.State_Temperature;
//			sum_friction += ea.State_RollerFriction;
			CMAttr es = new CMAttr();
			es.fromString(gate[i]);
			if(i==count-1)
				history = es;
			sum_temperature += es.getState_Temperature();
			sum_friction += es.getState_HingeFriction();
			sum_ac += es.getState_TicketsAccepted();
			sum_error += es.getState_TicketErrors();
			sum_rej += es.getState_TicketsRefused();
		}
		if(redis.get(key_sum_temp) == null){
			redis.set(key_sum_temp, String.valueOf(sum_temperature));
			redis.set(key_sum_temp_now, String.valueOf(sum_temperature));
		}
		else{
			double temp_value = Double.parseDouble(redis.get(key_sum_temp)) + sum_temperature;
			redis.set(key_sum_temp, String.valueOf(temp_value));
			redis.set(key_sum_temp_now, String.valueOf(sum_temperature));
		}
		if(redis.get(key_sum_fric) == null){
			redis.set(key_sum_fric, String.valueOf(sum_friction));
			redis.set(key_sum_fric_now, String.valueOf(sum_friction));
		}
		else{
			double temp_value = Double.parseDouble(redis.get(key_sum_fric)) + sum_friction;
			redis.set(key_sum_fric, String.valueOf(temp_value));
			redis.set(key_sum_fric_now, String.valueOf(sum_friction));
		}
		if(redis.get(key_sum_error) == null){
			redis.set(key_sum_error, String.valueOf(sum_error));
			redis.set(key_sum_error_now, String.valueOf(sum_error));
		}
		else{
			int temp_value = Integer.parseInt(redis.get(key_sum_error)) + sum_error;
			redis.set(key_sum_error, String.valueOf(temp_value));
			redis.set(key_sum_error_now, String.valueOf(sum_error));
		}
		if(redis.get(key_sum_ac) == null){
			redis.set(key_sum_ac, String.valueOf(sum_ac));
			redis.set(key_sum_ac_now, String.valueOf(sum_ac));
		}
		else{
			int temp_value = Integer.parseInt(redis.get(key_sum_ac)) + sum_ac;
			redis.set(key_sum_ac, String.valueOf(temp_value));
			redis.set(key_sum_ac_now, String.valueOf(sum_ac));
		}
		if(redis.get(key_sum_rej) == null){
			redis.set(key_sum_rej, String.valueOf(sum_rej));
			redis.set(key_sum_rej_now, String.valueOf(sum_rej));
		}
		else{
			int temp_value = Integer.parseInt(redis.get(key_sum_rej)) + sum_rej;
			redis.set(key_sum_error, String.valueOf(temp_value));
			redis.set(key_sum_rej_now, String.valueOf(sum_rej));
		}
		if(redis.get(key_count) == null){
			redis.set(key_count, String.valueOf(count));
			redis.set(key_count_now, String.valueOf(count));
		}
		else{
			int temp_value = Integer.parseInt(redis.get(key_count)) + count;
			redis.set(key_count, String.valueOf(temp_value));
			redis.set(key_count_now, String.valueOf(count));
		}
		history.setTS_GateDeviceName("AVG");
		history.setTS_HTIsSingleDevice("no");
		history.setState_HingeFriction(sum_friction/(double)count);
		history.setState_Temperature(sum_temperature/(double)count);
		history.setState_TicketErrors(sum_error);
		history.setState_TicketsAccepted(sum_ac);
		history.setState_TicketsRefused(sum_rej);
		history.setTS_GateId(getUUID());
		Map record = history.toMap();
		write(record,"Ts_gatehistorys"); //write the result to SQL Server
		collector.emit(new Values(frame));
	}
	public void executeDispenser(String line,String num,String frame,String info) {
		String key_sum_temp=frame+"_"+line+"_"+num+"_dispenser_"+"sum_temperature";
		String key_sum_tickets=frame+"_"+line+"_"+num+"_dispenser_"+"sum_tickets";
		String key_sum_ink=frame+"_"+line+"_"+num+"_dispenser_"+"sum_ink";
		String key_count=frame+"_"+line+"_"+num+"_dispenser_"+"count";
		String key_sum_temp_now=frame+"_"+line+"_"+num+"_dispenser_"+"sum_temperature_now";
		String key_sum_tickets_now=frame+"_"+line+"_"+num+"_dispenser_"+"sum_tickets_now";
		String key_sum_ink_now=frame+"_"+line+"_"+num+"_dispenser_"+"sum_ink_now";
		String key_count_now=frame+"_"+line+"_"+num+"_dispenser_"+"count_now";
		double sum_temperature=0;
		double sum_ink=0;
		int sum_tickets=0;
		int count=0;
		String[] dispenser = info.split("BBMM");
		count=dispenser.length;
		TMAttr history = new TMAttr();
		for(int i=0;i<count;i++) {
			//String[] escalator_attr = escalator[i].split("+");
//			EscalatorAttr ea = new EscalatorAttr(escalator[i]);
//			sum_temperature += ea.State_Temperature;
//			sum_friction += ea.State_RollerFriction;
			TMAttr es = new TMAttr();
			es.fromString(dispenser[i]);
			if(i==count-1)
				history = es;
			sum_temperature += es.getState_Temperature();
			sum_ink += es.getState_RemainingInk();
			sum_tickets += es.getState_RemainingTickets();
		}
		if(redis.get(key_sum_temp) == null){
			redis.set(key_sum_temp, String.valueOf(sum_temperature));
			redis.set(key_sum_temp_now, String.valueOf(sum_temperature));
		}
		else{
			double temp_value = Double.parseDouble(redis.get(key_sum_temp)) + sum_temperature;
			redis.set(key_sum_temp, String.valueOf(temp_value));
			redis.set(key_sum_temp_now, String.valueOf(sum_temperature));
		}
		if(redis.get(key_sum_ink) == null){
			redis.set(key_sum_ink, String.valueOf(sum_ink));
			redis.set(key_sum_ink_now, String.valueOf(sum_ink));
		}
		else{
			double temp_value = Double.parseDouble(redis.get(key_sum_ink)) + sum_ink;
			redis.set(key_sum_ink, String.valueOf(temp_value));
			redis.set(key_sum_ink_now, String.valueOf(sum_ink));
		}
		if(redis.get(key_sum_tickets) == null) {
			redis.set(key_sum_tickets, String.valueOf(sum_tickets));
			redis.set(key_sum_tickets_now, String.valueOf(sum_tickets));
		}
		else {
			int temp_value = Integer.parseInt(redis.get(key_sum_tickets)) + sum_tickets;
			redis.set(key_sum_tickets, String.valueOf(temp_value));
			redis.set(key_sum_tickets_now, String.valueOf(sum_tickets));
		}
		if(redis.get(key_count) == null){
			redis.set(key_count, String.valueOf(count));
			redis.set(key_count_now, String.valueOf(count));
		}
		else{
			int temp_value = Integer.parseInt(redis.get(key_count)) + count;
			redis.set(key_count, String.valueOf(temp_value));
			redis.set(key_count_now, String.valueOf(count));
		}
		history.setTS_DispenserDeviceName("AVG");
		history.setTS_HTIsSingleDevice("no");
		history.setState_RemainingInk(sum_ink);
		history.setState_Temperature(sum_temperature/(double)count);
		history.setState_RemainingTickets(sum_tickets);
		history.setTS_DispenserId(getUUID());
		Map record = history.toMap();
		write(record, "Ts_dispenserhistorys"); //write the result to SQL Server
		collector.emit(new Values(frame));
	} 
	@Override
	public void execute(Tuple tuple) {
		// TODO Auto-generated method stub
		String line = tuple.getStringByField("Line");
		String num = tuple.getStringByField("Num");
		String frame = tuple.getStringByField("Frame");
		String info_escalator = tuple.getStringByField("Escalator");
		String info_elevator = tuple.getStringByField("Elevator");
		String info_gate = tuple.getStringByField("CheckMachine");
		String info_dispenser = tuple.getStringByField("TicketMachine");
		executeEscalator(line,num,frame,info_escalator);
		executeElevator(line,num,frame,info_elevator);
		executeGate(line,num,frame,info_gate);
		executeDispenser(line,num,frame,info_dispenser);
		//TODO add emit
	}

	@Override
	public void prepare(Map conf, TopologyContext context, OutputCollector collector) {
		// TODO Auto-generated method stub
		this.collector = collector;
		redis = new Jedis(HOST,PORT);
	}

	@Override
	public void declareOutputFields(OutputFieldsDeclarer declarer) {
		// TODO Auto-generated method stub
		declarer.declare(new Fields("Frame"));
	}

}
