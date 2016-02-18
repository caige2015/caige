package song.strom;

import backtype.storm.Config;
import backtype.storm.LocalCluster;
import backtype.storm.StormSubmitter;
import backtype.storm.topology.TopologyBuilder;


public class SimpleTopology {

    public static void main(String[] args) {
        try {
            TopologyBuilder builder = new TopologyBuilder();
            builder.setSpout("SimpleSpout", new SimpleSpout(), 1);
            builder.setBolt("SimpleBolt", new SimpleBolt(), 1).shuffleGrouping("SimpleSpout");
            Config config = new Config();
            config.setDebug(true);
            
            if(args!=null && args.length > 0){
                config.setNumWorkers(1);
                StormSubmitter.submitTopology(args[0], config, builder.createTopology());
            }else {
                config.setMaxTaskParallelism(1);
                LocalCluster cluster = new LocalCluster();
                cluster.submitTopology("simple", config, builder.createTopology());
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
