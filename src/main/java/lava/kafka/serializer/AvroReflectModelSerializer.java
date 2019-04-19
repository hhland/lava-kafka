package lava.kafka.serializer;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.avro.Schema;
import org.apache.avro.io.BinaryDecoder;
import org.apache.avro.io.BinaryEncoder;
import org.apache.avro.io.DatumReader;
import org.apache.avro.io.DatumWriter;
import org.apache.avro.io.DecoderFactory;
import org.apache.avro.io.EncoderFactory;
import org.apache.avro.reflect.ReflectDatumReader;
import org.apache.avro.reflect.ReflectDatumWriter;
import org.apache.avro.specific.SpecificDatumReader;
import org.apache.avro.specific.SpecificDatumWriter;
import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.Serializer;

public abstract  class  AvroReflectModelSerializer <M> extends ModelSerializer<M> 
implements Serializer<M>,Deserializer<M> {

	   final static Schema.Parser parser = new Schema.Parser();

	   final static Map<Class, Schema> schemaMap=new HashMap<>();
	   
	   
	
	   
	    
	    @Override
	    public  byte[]  serialize(String topic, M data) {
	        
	        DatumWriter<M> writer = new ReflectDatumWriter<>(getSchema());
	        ByteArrayOutputStream out = new ByteArrayOutputStream();
	        BinaryEncoder encoder = EncoderFactory.get().directBinaryEncoder(out, null);
	        try {
	            writer.write(data, encoder);
	        }catch (IOException e) {
	            throw new SerializationException(e.getMessage());
	        }
	        return out.toByteArray();
	    }
	    
	    @Override
	    public M deserialize(String topic, byte[] data) {
	        
	        M m = newModel();
	        ByteArrayInputStream in = new ByteArrayInputStream(data);
	        DatumReader<M> userDatumReader = new ReflectDatumReader<>(getSchema());
	        BinaryDecoder decoder = DecoderFactory.get().directBinaryDecoder(in, null);
	        try {
	            m = userDatumReader.read(null, decoder);
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	        return m;
	    }

	    
		private Schema getSchema() {
			// TODO Auto-generated method stub
			Schema ret=null;
			if(schemaMap.containsKey(modelClass())) {
				ret=schemaMap.get(modelClass());
			}else {
				ret=createSchema(modelClass());
				schemaMap.put(modelClass(), ret);
			}
			return ret;
		}
		
		
		
		public static Schema createSchema(Class cls) {
            StringBuffer sbf=new StringBuffer();
			sbf
			.append("{")
			.append("\n\t \"namespace\": \""+cls.getPackage().getName()+"\", ")
			.append("\n\t \"type\": \"record\", ")
			.append("\n\t \"name\": \""+cls.getSimpleName()+"\", ")
			.append("\n\t \"fields\": [ ")
			;
			List<Method> mths=new ArrayList<Method>();
			for(Method mth:cls.getMethods()) {
				if(mth.getName().startsWith("set")) {
					mths.add(mth);
				}
			}
			int mthsSize=mths.size();
			for(int i=0;i<mthsSize;i++) {
				Method mth=mths.get(i);
				
				String fieldName=mth.getName().substring("set".length())
						,fieldType=mth.getParameterTypes()[0].getSimpleName()
						;
				fieldName=fieldName.substring(0,1).toLowerCase()+fieldName.substring(1);
				fieldType=fieldType.toLowerCase();
				sbf.append("\n\t\t {\"name\": \""+fieldName+"\", \"type\": \""+fieldType+"\"}");
				if(i!=mthsSize-1) {
					sbf.append(",");
				}
			}
			
			sbf.append("\n\t ] ")
			.append("\n}")
			;
			System.out.println(sbf.toString());
			Schema ret=null;
		
			  ret =parser.parse(sbf.toString());
			
			return ret;
		}
		
		
	
}
