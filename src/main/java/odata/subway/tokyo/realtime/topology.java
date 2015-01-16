package odata.subway.tokyo.realtime;

import odata.subway.tokyo.realtime.bolt.*;
import odata.subway.tokyo.realtime.spout.*;
import backtype.storm.LocalCluster;
import backtype.storm.Config;
import backtype.storm.StormSubmitter;
import backtype.storm.generated.AlreadyAliveException;
import backtype.storm.generated.InvalidTopologyException;
import backtype.storm.topology.TopologyBuilder;

public class topology {
	public static void main(String args[]) {
		 TopologyBuilder builder = new TopologyBuilder();
		 builder.setSpout( "spout", new Alldata_Spout());
	     builder.setBolt( "calc_bolt", new CalculatorBolt())
	                .shuffleGrouping("spout");
	     builder.setBolt("report", new AggregateBolt()).globalGrouping("calc_bolt");
	     Config conf = new Config();
	        //conf.put("wordsFile", "OData-test.csv");
	        conf.setDebug(false);
	        conf.put(Config.TOPOLOGY_MAX_SPOUT_PENDING, 1);
	        LocalCluster cluster = new LocalCluster();
	        cluster.submitTopology("test", conf, builder.createTopology());
//	        //Thread.sleep(1000);
	        //cluster.shutdown();
//	        conf.setNumWorkers(5);
//	        conf.setMaxSpoutPending(5000); 
//	        try {
//				StormSubmitter.submitTopology("TokyoSubwayRealTimeAnalytic",conf,builder.createTopology());
//			} catch (AlreadyAliveException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			} catch (InvalidTopologyException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
	}
	
}
