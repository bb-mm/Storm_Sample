package odata.subway.tokyo.realtime.bolt;

import java.util.HashMap;
import java.util.Map;

import org.apache.storm.guava.base.Stopwatch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import backtype.storm.task.OutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseRichBolt;
import backtype.storm.tuple.Tuple;

public class Escalator_report  extends BaseRichBolt{

	/**
	 * 
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(Escalator_report.class);
	private final long logIntervalInSeconds = 50;
	private Stopwatch stopwatch = null;
	//private HashMap<String,SumBlock> mapper;
	private static final long serialVersionUID = -2454428952684921175L;
	private OutputCollector collector;
	@Override
	public void execute(Tuple tuple) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void prepare(Map conf, TopologyContext context, OutputCollector collector) {
		// TODO Auto-generated method stub
		this.collector = collector;
		this.stopwatch = Stopwatch.createStarted();
	}

	@Override
	public void declareOutputFields(OutputFieldsDeclarer declarer) {
		// TODO Auto-generated method stub
		
	}

}
