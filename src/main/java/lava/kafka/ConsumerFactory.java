package lava.kafka;

import java.util.Properties;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.Serializer;

public class ConsumerFactory {

	
	public enum Prop  {

		bootstrap_servers
		,acks
		
		;
		

		protected String propName() {
			String ret= this.name().replaceAll("_", ".");
			return ret;
		}
		
		
		
	}
	
	protected final Properties props;
	
	public ConsumerFactory(){
		props=new Properties();
	}
	
	public ConsumerFactory(Properties props){
		this.props=props;
	}
	
	
	
	public Properties getProps() {
		return props;
	}

	

	public <K,V> Consumer<K, V> createConsumer(Deserializer<K> keySer,Deserializer<V> valueSer){
		Consumer<K, V> ret=new KafkaConsumer<>(props,keySer,valueSer);
		return ret;
	}
	
}
