package lava.kafka.test;

import java.util.Properties;

import org.apache.avro.Schema;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import lava.kafka.KafkaProperties;

public class SendClient {

	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Properties props=new Properties();
		KafkaProperties.bootstrap_servers.put(props, "localhost:9092"); 
		KafkaProperties.acks.put(props, "all");
		KafkaProperties.key_serializer.put(props, StringSerializer.class.getName());
		KafkaProperties.value_serializer.put(props, StringSerializer.class.getName());
		
		 
        try(KafkaProducer<String, String> producer=new KafkaProducer<>(props)){
          for(int i=0;i<1000;i++) {
            ProducerRecord<String, String> record=new ProducerRecord<String, String>("hhlin77","uusf"+i, "vadfs"+i);
            producer.send(record);
          }
          
        }
	}
	
}
