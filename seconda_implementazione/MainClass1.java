package progetto_java_2019_seconda_impl;

import java.util.*;
import javax.xml.crypto.*;

public class MainClass1 {
	public static <E extends Data> void main (String[] args) throws Exception {
		MyDataBoard<Type> bacheca = new MyDataBoard<Type> ("psw");
		
		
		// PROVA METODO createCategory -----------------------------
		try {
			bacheca.createCategory("categoria 1", "psw");
			bacheca.createCategory("categoria 2", "psw");
			bacheca.createCategory("categoria 3", "psw");
			bacheca.createCategory("categoria 4", "psw");
			bacheca.createCategory("categoria 5", "psw");
			bacheca.createCategory("categoria 6", "psw");
			bacheca.createCategory("categoria 7", "wrong");
		}
		catch(Exception ex) {
			System.out.print("Errore:\n" + ex.getMessage() + " : non tutte le richieste di inserimento di categoria sono andate a buon fine.\n");
		}
		// PROVA METODO removeCategory -----------------------------
		try {
			bacheca.removeCategory("categoria 5", "psw");
			bacheca.removeCategory("categoria 8", "psw");
		}
		catch(Exception ex) {
			System.out.print("\nErrore:\n" + ex.getMessage() + " : non tutte le richieste di rimozione di categoria sono andate a buon fine.\n");
		}
		// PROVA METODO printCategories ----------------------------
		bacheca.printCategories();
		
		
		// PROVA METODO addFriend ----------------------------------
		try {
			// categorie associate a marco
			bacheca.addFriend("categoria 2", "psw", "marco");
			bacheca.addFriend("categoria 4", "psw", "marco");
			bacheca.addFriend("categoria 6", "psw", "marco");
			// categorie associate ad antonio
			bacheca.addFriend("categoria 1", "psw", "antonio");
			bacheca.addFriend("categoria 2", "psw", "antonio");
			bacheca.addFriend("categoria 3", "psw", "antonio");
			bacheca.addFriend("categoria 4", "psw", "antonio");
			// categorie associate a gabriele
			bacheca.addFriend("categoria 1", "psw", "gabriele");
			bacheca.addFriend("categoria 2", "psw", "gabriele");
			// categorie associate ad andrea
			bacheca.addFriend("categoria 3", "psw", "andrea");
			bacheca.addFriend("categoria 2", "psw", "andrea");
			bacheca.addFriend("categoria 2", "psw", "andrea");
		}
		catch(Exception ex) {
			System.out.print("\nErrore:\n" + ex.getMessage() + " : non tutte le richieste di inserimento di amicizia/categoria sono andate a buon fine.\n");
		}
		// PROVA METODO printFriend --------------------------------
		bacheca.printFriend();
		// PROVA METODO removeFriend -------------------------------
		try {
			bacheca.removeFriend("categoria 4", "psw", "antonio");
			bacheca.removeFriend("categoria 6", "psw", "antonio");
		}
		catch(Exception ex) {
			System.out.print("\nErrore:\n" + ex.getMessage() + " : non tutte le richieste di rimozione di amicizia/categoria sono andate a buon fine.\n");
		}
		// PROVA METODO printFriendCategories ----------------------
		System.out.print("\n");
		bacheca.printFriendCategories("antonio");
		
		
		Type dato1 = new Type("dato 1");
		Type dato2 = new Type("dato 2");
		Type dato3 = new Type("dato 3");
		Type dato4 = new Type("dato 4");
		Type dato5 = new Type("dato 5");
		Type dato6 = new Type("dato 6");
		Type dato7 = new Type("dato 7");
		Type dato8 = new Type("dato 8");
		// PROVA METODO put ---------------------------------------- 
		try {
			bacheca.put("psw", dato1, "categoria 1");
			bacheca.put("psw", dato2, "categoria 2");
			bacheca.put("psw", dato3, "categoria 3");
			bacheca.put("psw", dato4, "categoria 4");
			bacheca.put("psw", dato5, "categoria 3");
			bacheca.put("psw", dato6, "categoria 6");
			bacheca.put("psw", dato7, "categoria 3");
			bacheca.put("psw", dato7, "categoria 6");
		}
		catch(Exception ex) {
			System.out.print("\nErrore:\n" + ex.getMessage() + " : non tutte le richieste di inserimento dei dati sono andate a buon fine.\n");
		}
		// PROVA METODO remove -------------------------------------
		try {
			bacheca.remove("psw", dato6);
			bacheca.remove("psw", dato8);
		}
		catch(Exception ex) {
			System.out.print("\nErrore:\n" + ex.getMessage() + " : non tutte le richieste di rimozione dei dati sono andate a buon fine.\n");
		}
		// PROVA METODO get ----------------------------------------
		try {
			System.out.println("\nRESTITUISCO LA COPIA DEL DATO N° 4:");
			((Type)(bacheca.get("psw", dato4))).display();
			System.out.println("RESTITUISCO LA COPIA DEL DATO N° 7:");
			((Type)(bacheca.get("psw", dato7))).display();
			System.out.println("RESTITUISCO LA COPIA DEL DATO N° 8:");
			((Type)(bacheca.get("psw", dato8))).display();
		}
		catch(Exception ex) {
			System.out.print("Errore:\n" + ex.getMessage() + " : questa richiesta di estrazione dei dati non è andata a buon fine.\n");
		}
		// PROVA METODO insertLike --------------------------------- 
		try {
			bacheca.insertLike("marco", dato2);
			bacheca.insertLike("antonio", dato2);
			bacheca.insertLike("andrea", dato3);
			bacheca.insertLike("antonio", dato3);
			bacheca.insertLike("gabriele", dato2);
			bacheca.insertLike("gabriele", dato1);
			bacheca.insertLike("alessio",dato7);
		}
		catch(Exception ex) {
				System.out.print("\nErrore:\n" + ex.getMessage() + " : non tutte le richiesta di aggiunta dei like sono andate a buon fine.\n");
		}
		// PROVA METODO getBoardDatas ------------------------------
		System.out.println("\nQUESTI SONO I DATI PRESENTI SULLA BACHECA:");
		for(int i = 0; i < bacheca.getBoardDatas().size(); i++) {
			bacheca.getBoardDatas().get(i).getData().display();
			System.out.print("(categoria: " + bacheca.getBoardDatas().get(i).getDataCategory() + ")");
			System.out.print(" (like: " + bacheca.getBoardDatas().get(i).getLike() + ")");
			System.out.print("\n");
		}
		// PROVA METODO getDataCategory ----------------------------
		System.out.println("\nQUESTI SONO TUTTI I DATI DELLA CATEGORIA N° 3:");
		for(int i = 0; i < bacheca.getDataCategory("psw", "categoria 3").size(); i++)
			bacheca.getDataCategory("psw", "categoria 3").get(i).display();
		
		
		//PROVA METODO getIterator ---------------------------------
		System.out.println("\nQUESTI SONO TUTTI I DATI ORDINATI PER LIKE DECRESCENTI:");
		try {
			Iterator<Type> itLike = (Iterator<Type>)(bacheca.getIterator("psw"));
			while(itLike.hasNext())
				itLike.next().display();
			System.out.println("\nPROVA METODO remove di getIterator");
			itLike.remove();
		}
		catch(Exception ex) {
				System.out.print("Errore:\n" + ex.getMessage() + " : non tutte le operazioni richieste sono andate a buon fine.\n");
		}
		//PROVA METODO getFriendIterator ---------------------------
		System.out.println("\nQUESTI SONO TUTTI I DATI VISIBILI DA ANTONIO:");
		try {
			Iterator<Type> itFriend = (Iterator<Type>)(bacheca.getFriendIterator("antonio"));
			while(itFriend.hasNext())
				itFriend.next().display();
			System.out.println("\nPROVA METODO remove di getFriendIterator");
			itFriend.remove();
		} 
		catch(Exception ex) {
			System.out.print("Errore:\n" + ex.getMessage() + " : non tutte le operazioni richieste sono andate a buon fine.\n");
		} 
	}
}
