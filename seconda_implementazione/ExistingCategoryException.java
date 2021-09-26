package progetto_java_2019_seconda_impl;

@SuppressWarnings("serial")
public class ExistingCategoryException extends Exception {
	public ExistingCategoryException(){
		super();
	}
	public ExistingCategoryException(String s){
		super(s);
	}
}
