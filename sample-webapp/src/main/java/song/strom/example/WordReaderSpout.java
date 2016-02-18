package song.strom.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Map;

import backtype.storm.spout.SpoutOutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.IRichSpout;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.tuple.Fields;
import backtype.storm.tuple.Values;

public class WordReaderSpout implements IRichSpout{

    /**
     * 
     */
    private static final long serialVersionUID=1L;

    private SpoutOutputCollector collector;
    
    private FileReader fileReader;
    
    private boolean complete = false;
    
    private TopologyContext context;
    
    public boolean isDistriubted(){
        return false;
    }
    
    @Override
    public void open(Map conf, TopologyContext context, SpoutOutputCollector collector) {
        try {
            this.context = context;
            this.fileReader = new FileReader(conf.get("wordsFile").toString());
            this.collector = collector;
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void close() {
    }

    @Override
    public void activate() {
    }

    @Override
    public void deactivate() {
    }

    @Override
    public void nextTuple() {
        if(complete){
            try {
                Thread.sleep(100);
            } catch(Exception e) {
            }
            return;
        }
        String str;
        //创建reader
        BufferedReader reader = new BufferedReader(fileReader);
        try {
            while((str=reader.readLine())!=null) {
                this.collector.emit(new Values(str),str);
            }
        } catch(Exception e) {
            e.printStackTrace();
        }finally{
            complete = true;
        }
    }

    @Override
    public void ack(Object msgId) {
        System.out.println("ok " + msgId);
    }

    @Override
    public void fail(Object msgId) {
        System.out.println("fail" + msgId);
    }

    @Override
    public void declareOutputFields(OutputFieldsDeclarer declarer) {
        declarer.declare(new Fields("line"));
    }

    @Override
    public Map<String, Object> getComponentConfiguration() {
        return null;
    }

}
