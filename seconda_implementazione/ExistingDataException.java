package progetto_java_2019_seconda_impl;

@SuppressWarnings("serial")
public class ExistingDataException extends Exception{
	public ExistingDataException(){
		super();
	}
	public ExistingDataException(String s){
		super(s);
	}
}
