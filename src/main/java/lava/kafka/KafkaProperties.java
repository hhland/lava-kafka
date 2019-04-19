package lava.kafka;

import java.util.Properties;

public enum KafkaProperties  {

	bootstrap_servers
	,acks
	,key_serializer
	,value_serializer
	;
	

	protected String propName() {
		String ret= this.name().replaceAll("_", ".");
		return ret;
	}
	
	public void put(Properties props,Object val) {
		props.put(propName(), val);
	}
	
	
}
