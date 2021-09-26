package progetto_java_2019_seconda_impl;

import javax.xml.crypto.Data;

public class Type implements Data{
	String post;
	
	public Type(String postName) {
		if(postName == null) throw new NullPointerException("nome del dato non valido");
		this.post = postName;
	}
	
	public void display() {
		System.out.println(this.post);
	}
}
