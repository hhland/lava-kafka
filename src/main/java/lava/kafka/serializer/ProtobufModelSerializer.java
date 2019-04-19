package lava.kafka.serializer;



import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.Serializer;

import com.google.protobuf.GeneratedMessage;
import com.google.protobuf.Parser;


public abstract class ProtobufModelSerializer <M extends GeneratedMessage> extends ModelSerializer<M> 
implements Serializer<M>,Deserializer<M>{

	 public abstract  Parser<M> getParser();
	 
	 
	
	@Override
    public byte[] serialize(final String topic, final M data) {
		byte[]  ret= data.toByteArray();
		return ret;
    }

	
	    @Override
	    public M deserialize(final String topic, byte[] data) {
	    	M ret=null;
	        try {
				ret= getParser().parseFrom(data);
	        }
	        catch (final Exception e) {
	            throw new RuntimeException("Received unparseable message " + e.getMessage(), e);
	        }
	        
	        
	        return ret;
	    }
	    
	    
	    
	   
	           
	
}
