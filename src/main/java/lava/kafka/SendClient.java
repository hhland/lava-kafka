package lava.kafka;

import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

public class SendClient {

	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Properties props=new Properties();
		 props.put("bootstrap.servers", "localhost:9092");
		 props.put("acks", "all");
		 props.put("key.serializer", StringSerializer.class.getName());
		 props.put("value.serializer",  StringSerializer.class.getName());
        try(KafkaProducer<String, String> producer=new KafkaProducer<>(props)){
          for(int i=0;i<1000;i++) {
            ProducerRecord<String, String> record=new ProducerRecord<String, String>("hhlin77","uusf"+i, "vadfs"+i);
            producer.send(record);
          }
          
        }
	}
	
}
