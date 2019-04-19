package lava.kafka.serializer;

public abstract class ModelSerializer <M> {

	protected M newModel() throws NullPointerException{
		   M ret=null;
		   try {
			ret=modelClass().newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			throw new NullPointerException(e.getMessage());
		}
		  return ret;
	}
	
	protected abstract Class<M> modelClass();
	
}
