package progetto_java_2019_seconda_impl;

import java.util.*;
import javax.xml.crypto.*;

public class MyDataBoard <E extends Data> implements DataBoard<E> {

	// ELEMEENTO TIPICO: boardDataCat: insieme delle coppie della funzione f:INDEX -> LIST<DataE<E>>, f(index) = L, e INDEX insieme finito di String e
	//                             	   L = lista di elementi di tipo DataE<E>
	//                   friendsCat: insieme delle coppie della funzione f:INDEX -> LIST<String>, f(index) = L, e INDEX insieme finito di String e
	//                             	 L = lista di elementi di tipo String
	
	// IR(c): c.psw != null && c.boardCategories != null && c.friendsCat != null 
	//        && forall index in c.boardCategories.keySet() -> c.boardCategories.get(index) != null 
	//        && forall index in c.boardCategories.keySet() -> c.boardCategories_index != null 
	//        && forall i,j in c.boardCategories.keySet() t.c i != j -> c.boardCategories.keySet()_i != c.boardCategories.keySet()_j 
	//        && forall lst in c.boardCategories.values() -> lst != null && forall i di lst.size() => lst.get(i) != null 
	//        && forall i,j di lst.size() t.c i < j -> lst.get(i).getData != lst.get(j).getData 
	//        && forall index in c.friendsCat.keySet() -> c.friendsCat.get(index) != null 
	//  	  && forall index in c.friendsCat.keySet() -> c.friendsCat_index != null 
	//        && forall i,j in c.friendsCat.keySet() t.c i != j -> c.friendsCat.keySet()_i != c.friendsCat.keySet()_j 
	//        && forall lst in c.friendsCat.values() -> lst != null && forall i di lst.size()=> lst.get(i) != null 
	//        && forall i,j di lst.size() t.c i < j -> lst.get(i) != lst.get(j) 
	
	// AF(c): {c.boardCategories.get(index).get(i) | index in c.boardCategories.keySet() && 0 <= i < boardCategories.get(index).size()}
	//        {c.friendsCat.get(index).get(i) | index in c.friendsCat.keySet() && 0 <= i < friendsCat.get(index).size()}
	
	private String psw;
	private HashMap<String,ArrayList<DataE<E>>> boardDataCat;
	private HashMap<String,ArrayList<String>> friendsCat;
	
	
	
	// COSTRUTTORE:
	public MyDataBoard(String passw) {
		// l'invariante viene rispettata per il construttore perché *
		
		if (passw == null) throw new NullPointerException("Password non valida");	// * 1) l'invariante rimane vera grazie a questo controllo, 
																					// infatti non consento mai di inserire un psw == null
		
		this.psw = passw;
		// * 2) per tutti e 2 gli insiemi l'invariante rimane verificata in qunado nessuno dei 3 insiemi è uguale a "null"
		this.boardDataCat = new HashMap<String, ArrayList<DataE<E>>>();
		this.friendsCat = new HashMap<String,ArrayList<String>>();
	}
	
	
	
	// METODI NON PRESENTI NELL'INTERFACCIA:
	public void printCategories() {
		// EFFECTS: stampa tutte le categorie presenti sulla bacheca
		// REQUIRES: boardDataCat != null
		// THROWS: se boardDataCat == null lancia NullPointerException (unchecked)
		
		if(boardDataCat == null) throw new  NullPointerException("Impossibile stampare le categorie se l'insieme è 'null'");
		if(boardDataCat.isEmpty()) System.out.println("/");
		
		System.out.println("\nCATEGORIE IN BACHECA:");
		Set<String> keys = boardDataCat.keySet();
		for(String cat: keys)
			System.out.println(cat);
		return;
	}
	
	
	public void printFriend() {
		// EFFECTS: stampa tutti gli amici presenti nella lista 'friends'
		// REQUIRES: friendsCat != null
		// THROWS: se friendsCat == null lancia NullPointerException (unchecked)
		
		if(friendsCat.isEmpty()) System.out.println("/");
		System.out.println("\nQUESTI SONO GLI AMICI APPARTENENTI ALLA LISTA AMICI:");
		Set<String> keys = friendsCat.keySet();
		for(String cat: keys)
			System.out.println(cat);
		return;
	}
	
	
	public void printFriendCategories(String friend) throws MissingFriendException {
		// EFFECTS: stampa tutte le categorie associate all'amico 'friend'
		// REQUIRES: "friend" deve appartenere alla lista degli amici
		// THROWS: se "friend" non appartiene alla lista di amici lancia MissingFriendException (checked)
		
		System.out.println("QUESTI LE CATEGORIE VISIBILI A " + friend + ":");
		if(!friendsCat.containsKey(friend)) throw new MissingFriendException("Amico non presente nella lista 'friend'");
		if(friendsCat.get(friend).isEmpty()) System.out.println("/");
		for(int i = 0; i < friendsCat.get(friend).size(); i++)
			System.out.println(friendsCat.get(friend).get(i));
		return;
	}
	
	
	public ArrayList<DataE<E>> getBoardDatas() { //forse qui manca un' eccezione 
		// EFFECTS: ritorna l'insieme dei dati presenti in bacheca
		
		Set<String> keys = boardDataCat.keySet();
		ArrayList<DataE<E>> boardData = new ArrayList<DataE<E>>();
		for(String cat: keys) {
			for(int i = 0; i < boardDataCat.get(cat).size(); i++)
				boardData.add(boardDataCat.get(cat).get(i));
		}
		return boardData;
	}
	
	
	
	// METODI DI INTERFACCIA
	@Override
	/* Crea una categoria di dati se vengono rispettati i controlli di identità */
	public void createCategory(String category, String passw) throws WrongPasswordException, ExistingCategoryException {
		// l'invariante viene rispettata per questo metodo perché *
		
		// la psw deve essere corretta per poter modificare l'insieme di categorie
		if (!passw.equals(psw))throw new WrongPasswordException("Password non corretta");
		// * 1) non consento che venga creata una categoria uguale a "null"
		if (category == null) throw new NullPointerException("Categoria 'null' non valida");
		// * 2) tramite il metodo "contains" evito che all'interno dell'insieme vengano inseriti duplicati
		if (boardDataCat.containsKey(category)) throw new ExistingCategoryException("Categoria già presente nella bacheca");
		
		// se i controlli vengono rispettari utilizzo il metodo "put" per aggiungere la "category" nell'insieme associandola a un insieme di dati
		// * 3) l'insieme associato alla categoria è != nulls
		boardDataCat.put(category, new ArrayList<DataE<E>>()); 
		return;
	}
	
	
	@Override
	/* Rimuove una categoria di dati se vengono rispettati i controlli di identità */
	public void removeCategory(String category, String passw) throws WrongPasswordException, MissingCategoryException {
		
		// la psw deve essere corretta per poter modificare l'insieme di categorie
		if (!passw.equals(psw))throw new WrongPasswordException("Password non corretta");	
		// non consento di rimuovere un elemento "null" in qunado non siano contenuti nell'insieme, evitando una ricerca non necessaria
		if (category == null) throw new NullPointerException("Categoria 'null' non valida");
		// non consento di rimuovere un elemento non presente nell'insieme, evitando una ricerca non necessaria
		if (!boardDataCat.containsKey(category)) throw new MissingCategoryException("Categoria non presente in bacheca");
		
		// rimuovo la categoria dall'insieme tramite il metodo "remove"
		boardDataCat.remove(category);
		// nel caso in cui la categoria sia già stata assegnata a degli amici la rimuovo dalle loro categorie visibili.
		// creo un insieme tramite il metodo keySet. l'insieme di chiavi è composto dai nomi degli amici,
		// se l'insieme di categorie associato all'amico contiene la categoria passata come parametro al metodo la rimuovo
		Set<String> keys = friendsCat.keySet();
		for(String friend: keys)
			if (friendsCat.get(friend).contains(category)) friendsCat.get(friend).remove(category);
		return;
	}
	
	
	@Override
	/* Aggiunge un amico ad una categoria di dati se vengono rispettati i controlli di identità */
	public void addFriend(String category, String passw, String friend) throws MissingCategoryException,WrongPasswordException, ExistingCategoryException {
		// l'invariante viene rispettata per questo metodo tramite *
		
		// la psw deve essere corretta per poter modificare l'insieme di categorie
		if (!passw.equals(psw))throw new WrongPasswordException("Password non corretta");
		// * 1) non consento di inserire una categoria o un amico uguale a "null"
		if(category == null || friend == null) throw new NullPointerException("Categoria o Amico 'null' non validi");
		// se la categoria non è presente nell'insieme delle categorie non la aggiungo all'amico
		if(!boardDataCat.containsKey(category)) throw new MissingCategoryException("Categoria non supportata");
		
		// * 2) se l'amico non è presente all'internno dell'insieme, lo aggiungo a tale insieme associandolo alla categoria passata alla funzione.
		if(!friendsCat.containsKey(friend)) {
			friendsCat.put(friend, new ArrayList<String>());
			friendsCat.get(friend).add(category);
			return;
		}
		
		// * 3) se l'amico a cui voglio aggiungere una categoria visibile è presente nell'insieme, aggiungo semplicemente tale categoria all'interno 
		// delle sua categorie visibili, controllando chen non sia già presente nelle sue categorie visibili
		if(friendsCat.get(friend).contains(category)) throw new ExistingCategoryException("Categoria già assegnata all'amico");
		else friendsCat.get(friend).add(category);
		return;
	}
	
	
	@Override
	/* Rimuove un amico da una categoria di dati se vengono rispettati i controlli di identità */
	public void removeFriend(String category, String passw, String friend) throws WrongPasswordException, MissingFriendException, MissingCategoryException {
		
		// la psw deve essere corretta per poter modificare l'insieme di categorie
		if(!passw.equals(psw))throw new WrongPasswordException("Password non corretta");
		// non consento la rimozione di categorie a "null" in quanto non siano presenti all'interno dell'inseme delle categorie visibili
		// lo stesso vale per il parametro "friend" passato come parametro al metodo
		if(category == null || friend == null) throw new NullPointerException("Categoria o Amico 'null' non validi");
		// non consento la rimozione se l'amico non è presente all'interno dell'insieme 
		if(!friendsCat.containsKey(friend)) throw new MissingFriendException("Amico non presente nella lista 'friends'");
		// se l'amico è presente controllo comunque che la categoria passata come parametro al metodo sia effetiivamente presente nell'insieme
		if(!friendsCat.get(friend).contains(category)) throw new MissingCategoryException ("Categoria non esistente per questo amico");
		
		// se invece l'amico è presente rimuovo dalle sue categorie visibili la categoria passata al metodo.
		friendsCat.get(friend).remove(category);
		return;
	}

	
	@Override
	/* Inserisce un dato in bacheca se vengono rispettati i controlli di identità */
	public boolean put(String passw, E data, String category) throws WrongPasswordException, MissingCategoryException,ExistingDataException {
		// l'invariante viene rispettata per questo metodo tramite *
		
		// la psw deve essere corretta per poter modificare l'insieme di categorie
		if(!passw.equals(psw))throw new WrongPasswordException("Password non corretta");
		// * 1) non inserisco un dato uguale a null all'interno dell'insieme di dati
		// e non lo associo a una categoria uguale a null
		if(category == null || data == null) throw new NullPointerException("Categoria o dato 'null' non validi");
		// consento di associare una categoria al dato a patto che questa sia presente all'interno dell'insieme delle categorie
		if(!boardDataCat.containsKey(category)) throw new MissingCategoryException("Categoria non supportata");
		
		// creo un insieme tramite il metodo keySet. l'insieme di chiavi è composto dalle categorie
		Set<String> keys = boardDataCat.keySet();
		for(String cat: keys) {
			for(int i = 0; i < boardDataCat.get(cat).size(); i++)
				// * 2) se il dato è già presente nell'insieme non consento l'inserimento di quest'ultimo per evitare la presenza di duplicati
				if(boardDataCat.get(cat).get(i).getData().equals(data)) throw new ExistingDataException("Dato già presente in bacheca");
		}
		// se tutti i controlli vanno a buon fine aggiungo all'insieme dei dati il dato passato come parametro al metodo associato alla categoria,
		// anche quest'ultima passata come parametro al metodo
		boardDataCat.get(category).add(new DataE<E>(data,category));
		return true;
	}

	
	@Override
	/* Restituisce una copia del dato in bacheca se vengono rispettati i controlli di identità */
	public E get(String passw, E data) throws WrongPasswordException, MissingDataException {
		
		// la psw deve essere corretta per poter modificare l'insieme di categorie
		if(!passw.equals(psw))throw new WrongPasswordException("Password non corretta");
		// non consento la ricerca se il dato è "null" in quanto all'interno dell'insieme dei dati non siano presetni elementi "null"
		if(data == null) throw new NullPointerException("dato 'null' non valido");
		
		// creo un insieme tramite il metodo keySet. l'insieme di chiavi è composto dalle categorie
		Set<String> keys = boardDataCat.keySet();
		for(String cat: keys) {
			for(int i = 0; i < boardDataCat.get(cat).size(); i++)
				// se trovo il dato all'interno dell'insieme ritorno una sua copia
				// la procedura avviene tramite il metodo getData della classe DataE
				if(boardDataCat.get(cat).get(i).getData().equals(data)) return boardDataCat.get(cat).get(i).getData();
		}
		// se il dato non viene trovato lancio un'eccezione
		throw new MissingDataException("Dato non presente nella bacheca");
	}

	
	@Override
	/* Rimuove il dato dalla bacheca se vengono rispettati i controlli di identità */
	public E remove(String passw, E data) throws WrongPasswordException, MissingDataException {
		
		// la psw deve essere corretta per poter modificare l'insieme di categorie
		if(!passw.equals(psw))throw new WrongPasswordException("Password non corretta");
		// non consento la rimozione se il dato è "null" in quanto all'interno dell'insieme dei dati non siano presenti elementi "null"
		if( data == null) throw new NullPointerException("dato 'null' non valido");
		
		// creo un insieme tramite il metodo keySet. l'insieme di chiavi è composto dalle categorie
		Set<String> keys = boardDataCat.keySet();
		for(String cat: keys) {
			for(int i = 0; i < boardDataCat.get(cat).size(); i++) {
				// se il dato è presente all'interno dell'insieme lo rimuovo
				// e ritorno ill dato appena eliminato
				// la procedura avviene tramite il metodo getData della classe DataE
				if(boardDataCat.get(cat).get(i).getData().equals(data)) {
					E tmp = boardDataCat.get(cat).get(i).getData();
					boardDataCat.get(cat).remove(i);
					return tmp;
				}
			}
		}
		// altrimenti lancio un eccezione
		throw new MissingDataException("Dato non presente in bacheca"); 
	}

	
	@Override
	/* Aggiunge un like a un dato se vengono rispettati i controlli di identità */
	public void insertLike(String friend, E data) throws MissingFriendException, MissingDataException,MissingCategoryException {
		
		// non consento di inserire un like al dato da parte di un amico se quest'ultimo è "null" o se il dato è "null"
		if(friend == null || data == null) throw new NullPointerException("Amico o dato 'null' non validi");
		// verifico che l'amico faccia parte dell'insieme di amici
		if(!friendsCat.containsKey(friend)) throw new MissingFriendException("Amico non presente nella lista 'friends'");
		
		Set<String> keys = boardDataCat.keySet();
		String dataCat = null;
		int dataIndex = 0;
		// creo un insieme tramite il metodo keySet. l'insieme di chiavi è composto dalle categorie
		for(String cat: keys) {
			for(int i = 0; i < boardDataCat.get(cat).size(); i++) {
				// controllo che il dato faccia parte dell'insieme dei dati
				// la procedura avviene tramite il metodo getData della classe DataE
				if(boardDataCat.get(cat).get(i).getData().equals(data))  {
					dataCat = cat;
					dataIndex = i;
				}
			}
		}
		// se il dato non è presente nell'insieme dei dati lancio un eccezione
		if(dataCat == null) throw new MissingDataException("Dato non presente in bacheca"); 
		// se il dato è presente nell'insieme, verifico che sia visibile all'amico passato come parametro al metodo
		// se tutti i controlli vengono rispettati aumento il campo like tramite il metodo addlike della classe DataE da me creata
		else {
			if(friendsCat.get(friend).contains(dataCat)) boardDataCat.get(dataCat).get(dataIndex).addlike(friend);
			else throw new MissingCategoryException("La categoria di questo dato non è visibile all'amico");
		}
		return;
	}

	
	@Override
	/* Crea la lista dei dati in bacheca di una determinata categoria se vengono rispettati i controlli di identità */
	public List<E> getDataCategory(String passw, String category) throws WrongPasswordException, MissingCategoryException {
		// richiedo che la psw sia corretta 
		if(!passw.equals(psw))throw new WrongPasswordException("Password non corretta");
		if(category == null) throw new NullPointerException("categoria 'null' non valida");
		if(!boardDataCat.containsKey(category)) throw new MissingCategoryException("Categoria non supportata");
		
		ArrayList<E> datas = new ArrayList<E>();
		for(int i = 0; i < boardDataCat.get(category).size(); i++)
			datas.add(boardDataCat.get(category).get(i).getData());
		return datas;
	}

	
	@Override
	/* restituisce un iteratore (senza remove) che genera tutti i dati in bacheca ordinati rispetto al numero di like */ 
	/* se vengono rispettati i controlli di identità */
	public Iterator<E> getIterator(String passw) throws WrongPasswordException {
		
		// richiedo che la psw sia corretta 
		if(!passw.equals(psw))throw new WrongPasswordException("Password non corretta");
		// ritorno un iteratore di tipo MyIteratorLikes in grado di scorrere la lista per like decrescenti
		return new MyIteratorLikes<E>(getBoardDatas());
	}

	
	@Override
	/* restituisce un iteratore (senza remove) che genera tutti i dati in bacheca vidibili a friend */
	public Iterator<E> getFriendIterator(String friend) throws MissingFriendException {
		
		// non consento operazioni se l'amico passato come parametro al metodo è uguale a null 
		// in quanto non siano presenti all'interno dell'inseme degli amici elementi uguali a "null"
		if(friend == null) throw new NullPointerException("Amico 'null' non valido");
		//se l'amico non è presente nell'insieme delle amicizie lancio un eccezione 
		if(!friendsCat.containsKey(friend)) throw new MissingFriendException("Amico non presente nella lista 'friends'");
		// se ivece l'amico è presente ritorno un iteratore di tipo MyIteratorFriend in grado di visualizzare tutte le categorie visibili
		// all'amico passato come parametro al metodo
		return new MyIteratorFriend<E>(getBoardDatas(),friendsCat.get(friend));
	}

}
