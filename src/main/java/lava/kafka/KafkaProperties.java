package lava.kafka;

import java.util.Properties;

public enum KafkaProperties  {

	bootstrap_servers
	,acks
	,key_serializer
	,value_serializer
	;
	

	protected String propName() {
		return this.name().replaceAll("_", ".");
	}
	
	public void put(Properties props,Object val) {
		props.put(propName(), val);
	}
	
	
}
