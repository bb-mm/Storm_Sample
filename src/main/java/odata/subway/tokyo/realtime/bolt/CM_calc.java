package odata.subway.tokyo.realtime.bolt;

import java.util.Map;

import backtype.storm.task.OutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseRichBolt;
import backtype.storm.tuple.Tuple;

public class CM_calc  extends BaseRichBolt{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3266123308627680688L;
	private OutputCollector collector;
//	private double avg_HingeFriction=0;            
//	private double avg_Temperature=0;               
//	private int sum_TicketErrors=0;                 
//	private int sum_TicketsAccepted=0;             
//	private int sum_TicketsRefused=0;      
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
