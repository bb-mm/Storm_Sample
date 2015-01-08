package odata.subway.tokyo.realtime.bolt;

import java.util.Map;

import backtype.storm.task.OutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseRichBolt;
import backtype.storm.tuple.Tuple;

public class Elevator_calc  extends BaseRichBolt{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5258818638132547784L;
	private OutputCollector collector;
//	private double avg_vibration;
//	private double avg_temperature;
	@Override
	public void execute(Tuple tuple) {
		// TODO Auto-generated method stub
		
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
