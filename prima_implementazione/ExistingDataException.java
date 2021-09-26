package progetto_java_2019;

@SuppressWarnings("serial")
public class ExistingDataException extends Exception{
	public ExistingDataException(){
		super();
	}
	public ExistingDataException(String s){
		super(s);
	}
}
