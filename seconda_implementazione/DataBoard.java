package progetto_java_2019_seconda_impl;

import java.util.*;
import javax.xml.crypto.*;

public interface DataBoard <E extends Data> {
	
	// OVERVIEW: il Tipo è composto da più insiemi  di liste di dimensione mutabile:
	//           - l'insieme delle categorie (k: String) è un insieme di liste di tipo DataE<E> (v: DataE<E>)
	//           - l'insieme degli amici (k: String) è un insieme di liste liste di tipo String (v: String)
	
	
	// EFFECTS: Crea una categoria di dati da aggiungere all'insieme di categorie
	// REQUIRES: passw deve essere corretta, category != null, category non deve essere presente nell'insieme di categorie
	// THROWS: se passw non è corretta lancia WrongPasswordException (checked)
	//         se category == null lancia NullPointerException (unchecked)
	//         se category è già presente nell'insieme lancia ExistingCategoryException (checked)
	// MODIFIES: this
	public void createCategory(String category, String passw) throws WrongPasswordException, ExistingCategoryException;
	
	
	// EFFECTS: Rimuove una categoria di dati dall' insieme di categorie
	// REQUIRES: passw deve essere corretta, category != null, category deve essere presente nell'insieme di categorie
	// THROWS: se passw non è corretta lancia WrongPasswordException (checked)
	//		   se category == null lancia NullPointerException (unchecked)
	//		   se category non è presente nell'insieme lancia MissingCategoryException (checked)
	// MODIFIES: this
	public void removeCategory(String category, String passw) throws WrongPasswordException, MissingCategoryException;
	
	
	// EFFECTS: Aggiunge un amico ad una categoria di dati, se l'amico non è già presente lo aggiunge anche all'insieme di amici
	// REQUIRES: passw deve essere corretta, category != null, friend != null, category deve appartenere all'insieme delle categorie, 
	//           category non deve appartenere all'insieme di categorie visibili da friend
	// THROWS: se passw non è corretta lancia WrongPasswordException (checked) 
	//         se category == null || friend == null lancia NullPointerException (unchecked)
	//         se category non appartiene all'insieme delle categorie lancia MissingCategoryException (checked)
	//         se category appartiene all'insieme di categorie visibili da friend lancia ExistingCategoryException (checked)
	// MODIFIES: this
	public void addFriend(String category, String passw, String friend) throws MissingCategoryException, WrongPasswordException, ExistingCategoryException;
	
	
	// EFFECTS: Rimuove un amico da una categoria di dati
	// REQUIRES: passw deve essere corretta, category != null, friend != null, friend deve appartenere all'inisieme di amici, 
	//           category deve appartenere all'insieme di categorie visibili da friend
	// THROWS: se passw non è corretta lancia WrongPasswordException (checked) 
	//         se category == null || friend == null lancia NullPointerException (unchecked)
	//         se friend non appartiene alla lista di amicizie lancia MissingFriendException (checked)
	//         se category non appartiene all'insieme di categorie visibili da friend lancia MissingCategoryException (checked)
	// MODIFIES: this
	public void removeFriend(String category, String passw, String friend) throws WrongPasswordException, MissingFriendException, MissingCategoryException;
	
	
	// EFFECTS: Inserisce un dato nell'insieme di dati
	// REQUIRES: passw deve essere corretta, category != null, data != null, category deve appartenere all'insieme delle categorie, 
	//           data non deve essere già presente nell'insieme di dati
	// THROWS: se passw non è corretta lancia WrongPasswordException (checked) 
	//         se categori == null || data == null lancia NullPointerException (unchecked)
	//         se category non appartiene all'insieme di categorie lancia MissingCategoryException (checked)
	//         se data è già presente nell'insieme di dati lancia ExistingDataException (checked)
	// MODIFIES: this
	public boolean put(String passw, E data, String categoria) throws WrongPasswordException, MissingCategoryException, ExistingDataException;
	
	
	// EFFECTS: Restituisce una copia del dato nell'insieme
	// REQUIRES: passw deve essere corretta, data != null, data deve essere presente nell'insieme di dati
	// THROWS: se passw non è corretta lancia WrongPasswordException (checked)
	//         se data == null lancia NullPointerException (unchecked)
	//         se data non è presente nell'insieme di dati lancia MissingDataException (checked)
	public E get(String passw, E data) throws WrongPasswordException, MissingDataException;
	
	
	// EFFECTS: Rimuove il dato dall'insieme
	// REQUIRES: passw deve essere corretta, data != null, data deve essere presente nell'insieme di dati
	// THROWS: se passw non è corretta lancia WrongPasswordException (checked)
	//         se data == null lancia NullPointerException (unchecked)
	//         se data non è presente nell'insieme di dati lancia MissingDataException (checked)
	public E remove(String passw,  E data) throws WrongPasswordException, MissingDataException;
	
	
	// EFFECTS: Aggiunge un like a un dato nell'insieme
	// REQUIRES: friend != null, friend deve appartenere all'insieme di amicizie, data deve essere presente nell'insieme di dati
	//           l'insieme delle categorie visibili all'amico deve essere != null, l'amico non deve già aver messo like al dato,
	//           la categoria di data deve essere visibile all'amico
	// THROWS: se friend == null lancia NullPointerException (unchecked)
	//         se friend non appartiene all'insieme di amicizie lancia MissingFriendException (checked)
	//         se l'insieme delle categorie visibili all'amico è = null lancia NullPointerException (unchecked)
	//         se l'amico ha già messo like al post lancia IllegalArgumentException (unchecked)
	//         se la categoria di data non è visibile a friend lancia MissingCategoryException (checked)
	//         se data non è presente nell'insieme di dati lancia MissingDataException (checked)
	// MODIFIES: this
	void insertLike(String friend, E data) throws MissingFriendException, MissingDataException, MissingCategoryException;
	

	// EFFECTS: Crea la lista dei dati in bacheca di una determinata categoria
	// REQUIRES: passw deve essere corretta, category != null, category deve far parte dell'insieme di categorie
	// THROWS: se passw non è corretta lancia WrongPasswordException (checked)
	//         se category == null lancia NullPointerException (unchecked)
	//         se category non fa parte dell'insieme di categorie lancia MissingCategoryException (checked)
	public List<E> getDataCategory (String passw, String category) throws WrongPasswordException, MissingCategoryException;
	
	
	// EFFECTS: Restituisce un iteratore (senza remove) che genera tutti i dati nell'insieme di dati ordinati rispetto al numero di like in modo crescente
	// REQUIRES: passw deve essere corretta, non deve essere chiamata l'operazione remove
	// THROWS: se passw non è corretta lancia WrongPasswordException (checked)
	//         se viene chiamata l'operazione remove lancia UnsupportedOperationException (unchecked)
	public Iterator<E> getIterator(String passw) throws WrongPasswordException;
	
	
	// EFFECTS: Restituisce un iteratore (senza remove) che genera tutti i dati nell'insieme di dati visibili  dall'amico friend
	// REQUIRES: friend deve appartenere alla lista di amicizie, non deve essere chiamata l'operazione remove
	// THROWS: se friend non appartiene alla lista di amicizie lancia MissingFriendException (checked)
	//         se viene chiamata l'operazione remove lancia UnsupportedOperationException (unchecked)
	public Iterator<E> getFriendIterator(String friend) throws MissingFriendException;
}

