package progetto_java_2019_seconda_impl;

@SuppressWarnings("serial")
public class MissingDataException extends Exception  {
	public MissingDataException(){
		super();
	}
	public MissingDataException(String s){
		super(s);
	}
}

