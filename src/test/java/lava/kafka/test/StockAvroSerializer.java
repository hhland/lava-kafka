package lava.kafka.test;

import java.util.Map;

import lava.kafka.serializer.AvroReflectSerializer;


public class StockAvroSerializer extends AvroReflectSerializer<Stock>{

	@Override
	public void configure(Map<String, ?> configs, boolean isKey) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void close() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected Class<Stock> modelClass() {
		// TODO Auto-generated method stub
		return Stock.class;
	}

	
	
}
