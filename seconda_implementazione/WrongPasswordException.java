package progetto_java_2019_seconda_impl;

@SuppressWarnings("serial")
public class WrongPasswordException extends Exception {
	public WrongPasswordException(){
		super();
	}
	public WrongPasswordException(String s){
		super(s);
	}
}
