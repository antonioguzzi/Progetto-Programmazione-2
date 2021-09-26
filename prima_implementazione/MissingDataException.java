package progetto_java_2019;

@SuppressWarnings("serial")
public class MissingDataException extends Exception  {
	public MissingDataException(){
		super();
	}
	public MissingDataException(String s){
		super(s);
	}
}
