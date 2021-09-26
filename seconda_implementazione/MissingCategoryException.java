package progetto_java_2019_seconda_impl;

@SuppressWarnings("serial")
public class MissingCategoryException extends Exception {
	public MissingCategoryException(){
		super();
	}
	public MissingCategoryException(String s){
		super(s);
	}
}
