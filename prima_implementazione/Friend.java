package progetto_java_2019;

import java.util.ArrayList;

public class Friend {
	
	// OVERVIEW: Friend: rappresenta l'amico presente nella lista amici
	//           categories: è un insieme mutabile che rappresenta le categorie visibili a this.friend
	
	// ELEMENTO TIPICO DI categories: <cat_0 . . .cat_n> con n = categories.size()
	// ELEMENTO TIPICO DI Friend: <name, <cat_0 . . .cat_n>>
	
	// IR(c): friend != null, categories != null
	//        && for all i di categories | 0 <= i < c.categories.size() -> c.categories.get(i) != null
	//        && for all i,j di categories | 0 <= i,j < c.categories.size() -> c.categories.get(i) != c.categories.get(j)
	
	// AF(c): {c.categories.get(i) | 0 <= i < c.categories.size()}
	
	private String friend;
	private ArrayList<String> categories;
	
	
	
	// COSTRUTTORE
	public Friend(String name, String category) {
		
		// controlli che permettono di mantenere verificata l'invariante:
		// così facendo impedisco che all'interno dell'insieme delle categorie visibili a this venga inserito un elemento "null"
		// e impedisco che il nome dell'amico venga inizilizzato a "null"
		if (category == null) throw new NullPointerException("Categoria 'null' non valida");
		if (name == null) throw new NullPointerException("Amico 'null' non valido");
		
		this.friend = name;
		this.categories = new ArrayList<String>();
		this.categories.add(category);
	}
	
	
	
	// METODI DELLA CALSSE
	
	/* ritorna il nome dell'amico */
	public String getFriend(){
		// EFFECTS: ritorna this.friend
		
		return friend;
	}
	
	/* ritorna tuttle le categorie di dato visibile all'amico */
	public ArrayList<String> getCategories(){
		// EFFECTS: ritorna thi.categories
		return categories;
	}
	
	/* aggiunge una nuova categoria visibile all'amico */
	public void addCategory(String category) throws ExistingCategoryException {
		// EFFECTS: aggiunge una nuova categoria visibile a this
		// REQUIRES: la nuova categoria non deve già essere presente tra le categorie visibili di this
		// THROWS: se la nuova categoria è presente tra le categorie visibili di this lancia ExistingCategoryException (checked)
		// MODIFIES: thiif (category == null) throw new NullPointerException("Categoria 'null' non valida");s
		
		// con questo controllo impedisco che all'interno dell'insieme delle categorie visibili a this venga inserito un elemento "null"
		// mantenendo così l'invariante verificata
		if (category == null) throw new NullPointerException("Categoria 'null' non valida");
		if(categories.contains(category)) throw new ExistingCategoryException("Categoria già assegnata all'amico");
		
		categories.add(category);
		return;
	}
	
	/* rimuove la categorya "category" dall'insieme delle categorie visibili all'amico */
	public void removeCategory(String category) throws MissingCategoryException {
		// EFFECTS: rimuove la categorya "category" dall'insieme delle categorie visibili di this
		// REQUIRES: la nuova categoria deve essere presente tra le categorie visibili di this
		// THROWS: se la nuova categoria non è presente tra le categorie visibili di this lancia MissingCategoryException (checked)
		// MODIFIES: this
		
		// se la categoria è uguale a "null" impedisco che questa venga cercata all'interno dell'insieme delle categorie visibili a this
		// in quanto ques'ultimo non contiene elementi uguali a "null"
		if (category == null) throw new NullPointerException("Categoria 'null' non valida");
		if(!categories.contains(category)) throw new MissingCategoryException ("Categoria non esistente per questo amico");
		
		categories.remove(category);
		return;
	}
	
	/* controlla che una determinata categoria sia visibile all'amico */
	public boolean catIsIn(String category) {
		// EFFECTS: ritorna true se category fa parte delle categorie visibili di this, false altrimenti
		// REQUIRES: category != null
		// THROWS: se category == null lancia NullPointerException (unchecked)
		
		// se la categoria è uguale a "null" impedisco che questa venga cercata all'interno dell'insieme delle categorie visibili a this
		// in quanto ques'ultimo non contiene elementi uguali a "null"
		if (category == null) throw new NullPointerException("Categoria 'null' non valida");
		return categories.contains(category);
	}
}
