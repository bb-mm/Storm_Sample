package odata.subway.tokyo.realtime.bolt;

import java.util.Map;

import backtype.storm.task.OutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseRichBolt;
import backtype.storm.tuple.Tuple;

public class Escalator_calc  extends BaseRichBolt{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private OutputCollector collector;
	//private HashMap<String,HashMap<String,>>
	//private double avg_friction;
	//private double avg_temperature;
	//TODO use local var
	@Override
	public void execute(Tuple tuple) {
		// TODO Auto-generated method stub
		String line = tuple.getStringByField("Line");
		String num = tuple.getStringByField("Num");
	}

	@Override
	public void prepare(Map conf, TopologyContext context, OutputCollector collector) {
		// TODO Auto-generated method stub
		this.collector = collector;
	}

	@Override
	public void declareOutputFields(OutputFieldsDeclarer arg0) {
		// TODO Auto-generated method stub
		
	}

}
