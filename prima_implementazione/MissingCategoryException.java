package progetto_java_2019;

@SuppressWarnings("serial")
public class MissingCategoryException extends Exception {
	public MissingCategoryException(){
		super();
	}
	public MissingCategoryException(String s){
		super(s);
	}
}
