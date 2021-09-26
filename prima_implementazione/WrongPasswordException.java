package progetto_java_2019;

@SuppressWarnings("serial")
public class WrongPasswordException extends Exception {
	public WrongPasswordException(){
		super();
	}
	public WrongPasswordException(String s){
		super(s);
	}
}
