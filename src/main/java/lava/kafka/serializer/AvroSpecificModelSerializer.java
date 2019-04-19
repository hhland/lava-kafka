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
import org.apache.avro.generic.IndexedRecord;
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

public abstract  class  AvroSpecificModelSerializer <M extends IndexedRecord> extends ModelSerializer<M> 
implements Serializer<M>,Deserializer<M> {

	   final static Schema.Parser parser = new Schema.Parser();

	   final static Map<Class, Schema> schemaMap=new HashMap<>();
	   
	   
	   
	    
	    @Override
	    public  byte[]  serialize(String topic, M data) {
	        
	        DatumWriter<M> writer = new SpecificDatumWriter<>(modelClass());
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
	        DatumReader<M> userDatumReader = new SpecificDatumReader<>(modelClass());
	        BinaryDecoder decoder = DecoderFactory.get().directBinaryDecoder(in, null);
	        try {
	            m = userDatumReader.read(null, decoder);
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	        return m;
	    }

	    
		
		
		
	
}
