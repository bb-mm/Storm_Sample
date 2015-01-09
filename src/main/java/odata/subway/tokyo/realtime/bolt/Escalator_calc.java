package odata.subway.tokyo.realtime.bolt;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;




import java.util.UUID;

import org.apache.olingo.odata2.api.edm.Edm;
import org.apache.olingo.odata2.api.exception.ODataException;

import odata.subway.tokyo.realtime.OlingoClienApp;
import odata.subway.tokyo.realtime.EscalatorAttr;
import backtype.storm.task.OutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseRichBolt;
import backtype.storm.tuple.Fields;
import backtype.storm.tuple.Tuple;
import backtype.storm.tuple.Values;

public class Escalator_calc  extends BaseRichBolt{

	/**
	 * this bolt is used to calculate the average number of 
	 */
	
	private static final long serialVersionUID = 1L;
	private OutputCollector collector;
	//private HashMap<String,HashMap<String,>>
	//private double avg_friction;
	//private double avg_temperature;
	//TODO use local var
	public static String getUUID(){ 
        return UUID.randomUUID().toString(); 
	}
	public void write() {
		OlingoClienApp clienApp = new OlingoClienApp();
		 String usedFormat = "application/json";
		 String serviceUrl = "http://mingding.chinacloudapp.cn/TokyoSubway/OdataServlet.cn/";
		 try {
			Edm edm = clienApp.readEdm(serviceUrl);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ODataException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Override
	public void execute(Tuple tuple) {
		// TODO Auto-generated method stub
		double sum_temperature=0;
		double sum_friction=0;
		int count=0;
		String line = tuple.getStringByField("Line");
		String num = tuple.getStringByField("Num");
		String frame = tuple.getStringByField("Frame");
		String info = tuple.getStringByField("Escalator");
		String[] escalator = info.split("|||");
		count=escalator.length;
		for(int i=0;i<count;i++) {
			//String[] escalator_attr = escalator[i].split("+");
//			EscalatorAttr ea = new EscalatorAttr(escalator[i]);
//			sum_temperature += ea.State_Temperature;
//			sum_friction += ea.State_RollerFriction;
		}
		String Sum = String.valueOf(sum_temperature) +"+"+ String.valueOf(sum_friction);
		String Count = String.valueOf(count);
		collector.emit(new Values(line,num,frame,Sum,Count));
	}

	@Override
	public void prepare(Map conf, TopologyContext context, OutputCollector collector) {
		// TODO Auto-generated method stub
		this.collector = collector;
	}

	@Override
	public void declareOutputFields(OutputFieldsDeclarer declarer) {
		// TODO Auto-generated method stub
		declarer.declare(new Fields("Line","Num","Frame","Sum","Count"));
	}

}
