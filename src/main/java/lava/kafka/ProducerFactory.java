package lava.kafka;

import java.util.Properties;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.Serializer;

public class ProducerFactory {

	
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
	
	public ProducerFactory(){
		props=new Properties();
	}
	
	public ProducerFactory(Properties props){
		this.props=props;
	}
	
	public void put(Prop prop,String value) {
		this.props.put(prop.propName(), value);
	}
	
	public Properties getProps() {
		return props;
	}

	public <K,V> Producer<K, V> createProducer(Serializer<K> keySer,Serializer<V> valueSer){
		Producer<K, V> ret=new KafkaProducer<>(props,keySer,valueSer);
		return ret;
	}


	
}
