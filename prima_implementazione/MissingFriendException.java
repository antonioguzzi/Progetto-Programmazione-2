package progetto_java_2019;

@SuppressWarnings("serial")
public class MissingFriendException extends Exception {
	public MissingFriendException(){
		super();
	}
	public MissingFriendException(String s){
		super(s);
	}
}
