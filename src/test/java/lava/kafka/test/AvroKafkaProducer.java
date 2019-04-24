package lava.kafka.test;

import java.util.Properties;

import org.apache.avro.Schema;
import org.apache.avro.generic.GenericData;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.serialization.StringSerializer;


import lava.kafka.ProducerFactory;
import lava.kafka.ProducerFactory.Prop;


public class AvroKafkaProducer {

	
public static void main(String[] args) throws Exception {
        
        Stock[] stocks = new Stock[1000];
        for(int i = 0; i < stocks.length; i++) {
            stocks[i] = new Stock();
            stocks[i].setStockCode(String.valueOf(i));
            stocks[i].setStockName("stock" + i);
          
            stocks[i].setPreClosePrice(100.0F);
            stocks[i].setRadeTime(68768);
        }
        
        
        ProducerFactory factory=new ProducerFactory();
        factory.put(Prop.bootstrap_servers, "localhost:9092");

        
        Producer<String, Stock> producer = factory.createProducer(new StringSerializer(),
        		 new StockAvroSerializer());
        
        for(Stock stock : stocks) {
            ProducerRecord<String, Stock> record = new ProducerRecord<>("dev3-yangyunhe-topic001", stock);
            RecordMetadata metadata = producer.send(record).get();
            StringBuilder sb = new StringBuilder();
            sb.append("stock: ").append(stock.toString()).append(" has been sent successfully!").append("\n")
                .append("send to partition ").append(metadata.partition())
                .append(", offset = ").append(metadata.offset());
            System.out.println(sb.toString());
            Thread.sleep(100);
        }
        
        producer.close();
    }
	
}
