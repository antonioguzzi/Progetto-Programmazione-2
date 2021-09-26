package progetto_java_2019;

@SuppressWarnings("serial")
public class ExistingCategoryException extends Exception {
	public ExistingCategoryException(){
		super();
	}
	public ExistingCategoryException(String s){
		super(s);
	}
}
