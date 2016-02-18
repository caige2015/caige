package song.strom;

import backtype.storm.topology.BasicOutputCollector;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseBasicBolt;
import backtype.storm.tuple.Fields;
import backtype.storm.tuple.Tuple;
import backtype.storm.tuple.Values;


public class SimpleBolt extends BaseBasicBolt{

    /**
     * 
     */
    private static final long serialVersionUID=1L;

    @Override
    public void execute(Tuple input, BasicOutputCollector collector) {
        try {
            String msg = input.getString(0);
            if(msg!=null){
                collector.emit(new Values(msg + " is processed!"));
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void declareOutputFields(OutputFieldsDeclarer declarer) {
        declarer.declare(new Fields("info"));
    }

}
