package song.strom.example;

import backtype.storm.Config;
import backtype.storm.LocalCluster;
import backtype.storm.topology.TopologyBuilder;
import backtype.storm.tuple.Fields;


public class WordTopology {

    public static void main(String[] args) {
        TopologyBuilder builder = new TopologyBuilder();
        builder.setSpout("word-reader", new WordReaderSpout());
        builder.setBolt("word-numizier", new WordNumlierBolt()).shuffleGrouping("word-reader");
        builder.setBolt("word-count", new WordCountBolt(),2).fieldsGrouping("word-numizier", new Fields("word"));
       
        Config config = new Config();
        config.put("wordsFile", args[0]);
        config.setDebug(true);
        
        config.put(Config.TOPOLOGY_MAX_SPOUT_PENDING, 1);
        LocalCluster cluster = new LocalCluster();
        cluster.submitTopology("getting-started", config, builder.createTopology());
        
        try {
            Thread.sleep(1000);
        } catch(InterruptedException e) {
            e.printStackTrace();
        }
        cluster.shutdown();
    }
}
