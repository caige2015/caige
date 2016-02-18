package song.strom.example;

import java.util.HashMap;
import java.util.Map;

import backtype.storm.task.OutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.IRichBolt;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.tuple.Tuple;


public class WordCountBolt implements IRichBolt{
    /**
     * 
     */
    private static final long serialVersionUID=1L;

    Integer id;
    String name;
    Map<String, Integer> counts;
    
    private OutputCollector collector;
    
    @Override
    public void prepare(Map stormConf, TopologyContext context, OutputCollector collector) {
        this.collector = collector;
        counts = new HashMap<String, Integer>();
        id=context.getThisTaskId();
        name=context.getThisComponentId();
    }

    @Override
    public void execute(Tuple input) {
        String str = input.getString(0);
        
        if(!counts.containsKey(str)){
            counts.put(str, 1);
        }else {
            Integer countStr = counts.get(str)+1;
            counts.put(str, countStr);
        }
        
        collector.ack(input);
    }

    @Override
    public void cleanup() {
        System.out.println("单词数量--name:"+ name + ", id:"+id);
        for(Map.Entry<String, Integer> mapEntry : counts.entrySet()) {
            System.out.println(mapEntry.getKey()+"--"+mapEntry.getValue());
        }
    }

    @Override
    public void declareOutputFields(OutputFieldsDeclarer declarer) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public Map<String, Object> getComponentConfiguration() {
        // TODO Auto-generated method stub
        return null;
    }

}
