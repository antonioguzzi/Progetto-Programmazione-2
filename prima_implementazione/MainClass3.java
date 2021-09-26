package progetto_java_2019;

import java.util.Iterator;

import javax.xml.crypto.Data;

public class MainClass3 {
	public static <E extends Data> void main (String[] args) throws Exception {
		
		try {
			@SuppressWarnings("unused")
			MyDataBoard<Type> bachecaProva = new MyDataBoard<Type> (null);
		}
		catch(Exception ex) {
			System.out.print("Errore:\n" + ex.getMessage() + " : non tutte le richieste di creazione di una nuova bacheca sono andate a buon fine.\n");
		}
		MyDataBoard<Type> bacheca = new MyDataBoard<Type> ("psw");
		
		// PROVA METODO createCategory ---------------------------------
		try {
			bacheca.createCategory("categoria 1", "psw");
			bacheca.createCategory("categoria 2", "psw");
			bacheca.createCategory("categoria 3", "psw");
			bacheca.createCategory("categoria 4", "psw");
			bacheca.createCategory("categoria 5", "psw");
			bacheca.createCategory("categoria 6", "psw");
			bacheca.createCategory(null, "psw");
		}
		catch(Exception ex) {
			System.out.print("Errore:\n" + ex.getMessage() + " : non tutte le richieste di inserimento di categoria sono andate a buon fine.\n");
		}
		// PROVA METODO printCategories --------------------------------
		bacheca.printCategories();
		
		
		// PROVA METODO addFriend --------------------------------------
		try {
			bacheca.addFriend("categoria 4", "psw", "marco");
			bacheca.addFriend("categoria 6", "psw", "marco");
			bacheca.addFriend("categoria 3", "psw", "marco");
			bacheca.addFriend("categoria 4", "psw", "antonio");
			bacheca.addFriend("categoria 6", "psw", "antonio");
			bacheca.addFriend("categoria 3", "psw", "antonio");
			bacheca.addFriend("categoria 1", "psw", "antonio");
			bacheca.addFriend("categoria 4", "psw", "grabriele");
			bacheca.addFriend("categoria 3", "psw", "elisa");
			bacheca.addFriend("categoria 3", "psw", "andrea");
			bacheca.addFriend("categoria 1", "psw", "andrea");
			bacheca.addFriend(null, "psw", "andrea");
		}
		catch(Exception ex) {
			System.out.print("\nErrore:\n" + ex.getMessage() + " : non tutte le richieste di inserimento di amicizia/categoria sono andate a buon fine.\n");
		}
		// PROVA METODO removeCategory ---------------------------------
		// se decido di rimuovere una categoria dalla bacheca ma è già stata assegnata a uno o più amici*
		bacheca.removeCategory("categoria 4", "psw");
		// PROVA METODO printCategories --------------------------------
		System.out.println("\nCATEGORIE IN BACHECA AGGIORNATE ALL'ULTIMA RIMOZIONE:");
		bacheca.printCategories();
		// PROVA METODO printFriend ------------------------------------
		bacheca.printFriend();
		// PROVA METODO printFriendCategories --------------------------
		// *viene rimossa anche dalle categorie visibili di tutti gli amici che la contenevano, qui un esempio
		bacheca.printFriendCategories("marco");
		bacheca.printFriendCategories("antonio");
		bacheca.printFriendCategories("andrea");
		bacheca.printFriendCategories("grabriele");
		// PROVA METODO removeFriend -----------------------------------
		try {
			bacheca.removeFriend("categoria 3", "psw", "elisa");
			bacheca.removeFriend(null, "psw", "andrea");
		}
		catch(Exception ex) {
			System.out.print("\nErrore:\n" + ex.getMessage() + " : non tutte le richieste di rimozione di amicizia/categoria sono andate a buon fine.\n");
		}
		
		
		Type a = new Type("dato 1");
		Type b = new Type("dato 2");
		Type c = new Type("dato 3");
		Type d = new Type("dato 4");
		Type e = new Type("dato 5");
		Type f = new Type("dato 6");
		Type g = new Type("dato 7");
		// PROVA METODO put --------------------------------------------
		try {
			bacheca.put("psw", a, "categoria 6");
			bacheca.put("psw", b, "categoria 1");
			bacheca.put("psw", c, "categoria 3");
			bacheca.put("psw", d, "categoria 3");
			bacheca.put("psw", e, "categoria 3");
			bacheca.put("psw", f, "categoria 6");
			bacheca.put("Wrong", g, "categoria 6");
		}
		catch(Exception ex) {
			System.out.print("\nErrore:\n" + ex.getMessage() + " : non tutte le richieste di inserimento dei dati sono andate a buon fine.\n");
		}
		// PROVA METODO remove -----------------------------------------
		bacheca.remove("psw", a);
		bacheca.remove("psw", d);
		// PROVA METODO get --------------------------------------------
		System.out.println("\nRESTITUISCO LA COPIA DEL DATO  c (N° 3):");
		((Type)(bacheca.get("psw", c))).display();
		System.out.println("RESTITUISCO LA COPIA DEL DATO e (N° 5):");
		((Type)(bacheca.get("psw", e))).display();
		// PROVA METODO insertLike -------------------------------------
		try {
			bacheca.insertLike("marco", c);
			bacheca.insertLike("antonio", c);
			bacheca.insertLike("antonio", b);
			bacheca.insertLike("andrea", c);
			bacheca.insertLike("andrea", b);
			bacheca.insertLike("grabriele", a);
			
		}
		catch(Exception ex) {
				System.out.print("\nErrore:\n" + ex.getMessage() + " : non tutte le richiesta di aggiunta dei like sono andate a buon fine.\n");
		}
		// PROVA METODO getBoardDatas ---------------------------------
		System.out.println("\nQUESTI SONO I DATI PRESENTI SULLA BACHECA:");
		for(int i = 0; i < bacheca.getBoardDatas().size(); i++) {
			bacheca.getBoardDatas().get(i).getData().display();
			System.out.print("(categoria: " + bacheca.getBoardDatas().get(i).getDataCategory() + ")");
			System.out.print(" (like: " + bacheca.getBoardDatas().get(i).getLike() + ")");
			System.out.print("\n");
		}
		// PROVA METODO getDataCategory -------------------------------
		try {
			System.out.println("\nQUESTI SONO TUTTI I DATI DELLA CATEGORIA N° 3:");
			for(int i = 0; i < bacheca.getDataCategory("psw", "categoria 3").size(); i++)
				bacheca.getDataCategory("psw", "categoria 3").get(i).display();
			System.out.println("QUESTI SONO TUTTI I DATI DELLA CATEGORIA N° 1:");
			for(int i = 0; i < bacheca.getDataCategory("wrong", "categoria 1").size(); i++)
				bacheca.getDataCategory("wrong", "categoria 1").get(i).display();
		}
		catch(Exception ex) {
				System.out.print("Errore:\n" + ex.getMessage() + " : non tutte le richieste di visualizzazioni per cattegoria sono andate a buon fine.\n");
		}
		System.out.println("QUESTI SONO TUTTI I DATI DELLA CATEGORIA N° 1:");
		for(int i = 0; i < bacheca.getDataCategory("psw", "categoria 1").size(); i++)
			bacheca.getDataCategory("psw", "categoria 1").get(i).display();
		
		
		// PROVA METODO getIterator -----------------------------------
		System.out.println("\nQUESTI SONO TUTTI I DATI ORDINATI PER LIKE DECRESCENTI:");
		Iterator<Type> itLike = (Iterator<Type>)(bacheca.getIterator("psw"));
		while(itLike.hasNext())
			itLike.next().display();
		// PROVA METODO getFriendIterator -----------------------------
		System.out.println("\nQUESTI SONO TUTTI I DATI VISIBILI DA ANDREA:");
		Iterator<Type> itFriend = (Iterator<Type>)(bacheca.getFriendIterator("andrea"));
		while(itFriend.hasNext())
			itFriend.next().display();
	}
}
