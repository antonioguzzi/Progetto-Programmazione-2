package progetto_java_2019;


import java.util.*;
import javax.xml.crypto.*;

public class MyDataBoard <E extends Data> implements DataBoard<E> {
	
	// ELEMEENTO TIPICO: boardCategories: <category_0 . . . category_n> con n = boardCategories.size()
	//                   boardDatas: <(data_0, like_0, category_0, <friend_0 ... friend_m>_0) . . . (data_n, like_n, category_n, <friend_0 ... friend_m>_n)>
	//                               con n = boardDatas.size() && m = likeFromFriend.size()
	//                   friendsCat: <(name_0, <category_0...category_m>_0) . . . (name_n, <category_0...category_m>_n)> 
	//                               con n = friendsCat.size() && m = categories.size()
	
	// IR(c): c.psw != null && c.boardCategories != null && c.boardDatas != null && c.friendsCat != null 
	//        && forall i di c.boardCategories | 0 <= i < c.boardCategories.size() -> c.boardCategories.get(i) != null
	//        && forall i,j di c.boardCategories | 0 <= i < j < c.boardCategories.size() -> c.boardCategories.get(i) != c.boardCategories.get(j)
	//        && forall i di c.boardDatas | 0 <= i < c.boardDatas.size() -> c.boardDatas.get(i) != null
	//        && forall i,j di c.boardDatas | 0 <= i,j < c.boardDatas.size() -> c.boardDatas.get(i).getData() != c.boardDatas.get(j).getData()
	//        && forall i di c.friendsCat | 0 <= i < c.friendsCat.size() -> c.friendsCat.get(i).getFriend() != null
	//        && forall i,j di c.friendsCat | 0 <= i,j < c.friendsCat.size() -> c.friendsCat.get(i).getFriend() != c.friendsCat.get(j).getFriend()
	
	// AF(c): {c.boardCategories.get(i) | 0 <= i < c.boardCategories.size()}
	//        {c.boardDatas.get(i) | 0 <= i < c.boardDatas.size()}
	//        {c.friendsCat.get(i) | 0 <= i < c.friendsCat.size()}
	
	private String psw;
	private ArrayList<String> boardCategories;
	private ArrayList<DataE<E>> boardDatas;
	private ArrayList<Friend> friendsCat;
	
	
	
	// COSTRUTTORE:
	public MyDataBoard(String passw) {
		// l'invariante viene rispettata per il construttore perché *
		
		if (passw == null) throw new NullPointerException("Password non valida");	// * 1) l'invariante rimane vera grazie a questo controllo, 
																					// infatti non consento mai di inserire un psw == null
		this.psw = passw;
		// * 2) per tutti e 3 gli insiemi l'invariante rimane verificata in qunado nessuno dei 3 insiemi è uguale a "null"
		this.boardCategories = new ArrayList<String>();
		this.boardDatas = new ArrayList<DataE<E>>();
		this.friendsCat = new ArrayList<Friend>();
	}
	
	
	
	// METODI NON PRESENTI NELL'INTERFACCIA:
	public int searchData(E data) {
		// EFFECTS: cerca un determinato dato tra quelli presenti in bacheca e ne restituisce l'indice se presente, -1 altrimenti
		// REQUIRES: data != null 
		// THROWS: se data == null lancia NullPointerException (unchecked)
		
		if (data == null) throw new NullPointerException("Dato null non valido per la ricerca");
		E tmp = null;
		for(int i = 0; i < boardDatas.size(); i++) {
			tmp = (E)boardDatas.get(i).getData();
			if(tmp.equals(data)) return i;
	    }
		return -1;
	}
	
	
	public int searchFriend(String friend) {
		// EFFECTS: cerca un determinato amico tra quelli presenti nella lista di amici e ne restituisce l'indice se presente, -1 altrimenti
		// REQUIRES: friend != null 
		// THROWS: se friend == null lancia NullPointerException (unchecked)
		
		if(friend == null) throw new NullPointerException("Amico 'null' non valido per la ricerca");
		for(int i = 0; i < friendsCat.size(); i++) 
			if(friendsCat.get(i).getFriend().equals(friend)) return i;
		return -1;
	}
	
	
	public void printCategories() {
		// EFFECTS: stampa tutte le categorie presenti sulla bacheca
		// REQUIRES: boardCategories != null
		// THROWS: se boardCategories == null lancia NullPointerException (unchecked)
		
		if(boardCategories == null) throw new  NullPointerException("Impossibile stampare le categorie se l'insieme è 'null'");
		System.out.println("\nCATEGORIE IN BACHECA:");
		if(boardCategories.isEmpty()) System.out.println("/");
		for(int i = 0; i < boardCategories.size(); i++)
			System.out.println(boardCategories.get(i));
	}
	
	
	public void printFriend() {
		// EFFECTS: stampa tutti gli amici presenti nella lista 'friends'
		// REQUIRES: friendsCat != null
		// THROWS: se friendsCat == null lancia NullPointerException (unchecked)
		
		if(friendsCat == null) throw new  NullPointerException("Impossibile stampare gli amici se l'insieme è 'null'");
		System.out.println("\nLISTA AMICI:");
		if(boardCategories.isEmpty()) System.out.println("/");
		for(int i = 0; i < friendsCat.size(); i++)
			System.out.println(friendsCat.get(i).getFriend());
	}
	
	
	public void printFriendCategories(String friend) throws MissingFriendException {
		// EFFECTS: stampa tutte le categorie associate all'amico 'friend'
		// REQUIRES: "friend" deve appartenere alla lista degli amici
		// THROWS: se "friend" non appartiene alla lista di amici lancia MissingFriendException (checked)
		
		if(friend == null) throw new NullPointerException("Amico 'null' non valido per la rstapa delle categorie visbili ");
		System.out.println("\nQUESTE LE CATEGORIE VISIBILI A " + friend + ":");
		int index = searchFriend(friend);
		if(index == -1) throw new MissingFriendException("Amico non presente nella lista 'friend'");
		if(friendsCat.get(index).getCategories().isEmpty()) System.out.println("/");
		for(int i = 0; i < friendsCat.get(index).getCategories().size(); i++)
			System.out.println(friendsCat.get(index).getCategories().get(i));
	}
	
	
	public ArrayList<DataE<E>> getBoardDatas() { //forse qui manca un' eccezione
		// EFFECTS: ritorna il vettore di dati in bacheca
		return this.boardDatas;
	}
	
	
	
	// METODI DI INTERFACCIA:
	@Override
	/* Crea una categoria di dati se vengono rispettati i controlli di identità */
	public void createCategory(String category, String passw) throws WrongPasswordException, ExistingCategoryException{
		// l'invariante viene rispettata per questo metodo perché *
		
		// la psw deve essere corretta per poter modificare l'insieme di categorie
		if (!passw.equals(psw))throw new WrongPasswordException("Password non corretta");
		// * 1) non consento che venga creata una categoria uguale a "null"
		if (category == null) throw new NullPointerException("Categoria 'null' non valida");
		// * 2) tramite il metodo "contains" evito che all'interno dell'insieme vengano inseriti duplicati
		if (boardCategories.contains(category)) throw new ExistingCategoryException("Categoria già presente nella bacheca");	
		
		// se i controlli vengono rispettari utilizzo il metodo "add" per aggiungere la "category" nell'insieme
		boardCategories.add(category); 
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
		if (!boardCategories.contains(category)) throw new MissingCategoryException("Categoria non presente in bacheca");
		
		// rimuovo la categoria dall'insieme tramite il metodo "remove"
		boardCategories.remove(category); 
		// nel caso in cui la categoria sia già stata assegnata a degli amici la rimuovo dalle loro categorie visibili.
		// la porcedura avviene grazie al metodo getCategories della classe "Fried" da me creata
		for(int i = 0; i < friendsCat.size(); i++)
			if(friendsCat.get(i).getCategories().contains(category)) friendsCat.get(i).getCategories().remove(category);
		return;
	}
	
	@Override
	/* Aggiunge un amico ad una categoria di dati se vengono rispettati i controlli di identità */
	public void addFriend(String category, String passw, String friend) throws WrongPasswordException, MissingCategoryException, ExistingCategoryException {
		// l'invariante viene rispettata per questo metodo tramite *
		
		// la psw deve essere corretta per poter modificare l'insieme di categorie
		if (!passw.equals(psw))throw new WrongPasswordException("Password non corretta");
		// * 1) non consento di inserire una categoria o un amico uguale a "null"
		if(category == null || friend == null) throw new NullPointerException("Categoria o Amico 'null' non validi");
		// se la categoria non è presente nell'insieme delle categorie non la aggiungo all'amico
		if(!boardCategories.contains(category)) throw new MissingCategoryException("Categoria non supportata");
		
		
		int i = searchFriend(friend);
		// * 2) se l'amico a cui voglio aggiungere una categoria visibile è presente nell'insieme, aggiungo semplicemente tale categoria all'interno 
		// delle sua categorie visibili tramite il metodo addCategory della classe Friend da me creata 
		if (i != -1) friendsCat.get(i).addCategory(category); 
		// * 3) se l'amico non è presente all'internno dell'insieme, lo aggiungo a tale insieme associandolo alla categoria passata alla funzione.
		// la procedura avviene grazie al metodo add della classe "Fried" da me creata
		else friendsCat.add(new Friend(friend,category));
		return;
	}
	
	@Override
	/* Rimuove un amico da una categoria di dati se vengono rispettati i controlli di identità */
	public void removeFriend(String category, String passw, String friend) throws WrongPasswordException, MissingFriendException, MissingCategoryException{
		
		// la psw deve essere corretta per poter modificare l'insieme di categorie
		if(!passw.equals(psw))throw new WrongPasswordException("Password non corretta");
		// non consento la rimozione di categorie a "null" in quanto non siano presenti all'interno dell'inseme delle categorie visibili
		// lo stesso vale per il parametro "friend" passato come parametro al metodo
		if(category == null || friend == null) throw new NullPointerException("Categoria o Amico 'null' non validi");
		// non consento la rimozione se l'amico non è presente all'interno dell'insieme 
		// la procedura avviene al metodo searchFriend di this
		if(searchFriend(friend) == -1) throw new MissingFriendException("Amico non presente nella lista 'friends'");
		
		// se invece l'amico è presente rimuovo dalle sue categorie visibili la categoria passata al metodo.
		// la procedura avveiene tramite il metodo removeCategory della classe "Friend" da me creata
		int i = searchFriend(friend);
		friendsCat.get(i).removeCategory(category);
		return;
	}
	
	@Override
	/* Inserisce un dato in bacheca se vengono rispettati i controlli di identità */
	public boolean put(String passw, E data, String category) throws WrongPasswordException, MissingCategoryException, ExistingDataException{
		// l'invariante viene rispettata per questo metodo tramite *
		
		// la psw deve essere corretta per poter modificare l'insieme di categorie
		if(!passw.equals(psw))throw new WrongPasswordException("Password non corretta");
		// * 1) non inserisco un dato uguale a null all'interno dell'insieme di dati
		if(category == null || data == null) throw new NullPointerException("Categoria o dato 'null' non validi");
		// consento di aggiungere una categoria al dato a patto che questa sia presente all'interno dell'insieme delle categorie 
		if(!boardCategories.contains(category)) throw new MissingCategoryException("Categoria non supportata");
		// * 2) se il dato è già presente nell'insieme non consento l'inserimento di quest'ultimo
		// tale procedura avviene tramite il metodo searchData di this
		if(searchData(data) != -1) throw new ExistingDataException("Dato già presente in bacheca");
		
		// se tutti i controlli vengono rispettati aggiungo il dato associato alla categoria
		return boardDatas.add(new DataE<E>(data, category));
	}
	
	@Override
	/* Restituisce una copia del dato in bacheca se vengono rispettati i controlli di identità */
	public E get(String passw, E data) throws WrongPasswordException, MissingDataException {
		
		// la psw deve essere corretta per poter modificare l'insieme di categorie
		if(!passw.equals(psw))throw new WrongPasswordException("Password non corretta");
		// non consento la ricerca se il dato è "null" in quanto all'interno dell'insieme dei dati non siano presetni elementi "null"
		if(data == null) throw new NullPointerException("dato 'null' non valido");
		
		// cerco il dato all'interno dell'insieme dei dati tramite il metodo searchData 
		int i = searchData(data);
		// se il dato è presente all'interno dell'insieme ritorno una sua copia
		if (i != -1) return boardDatas.get(i).getData();
		// altrimenti lancio un'eccezione
		else throw new MissingDataException("Dato non presente nella bacheca");
	}
	
	@Override
	/* Rimuove il dato dalla bacheca se vengono rispettati i controlli di identità */
	public E remove(String passw, E data) throws WrongPasswordException, MissingDataException{
		
		// la psw deve essere corretta per poter modificare l'insieme di categorie
		if(!passw.equals(psw))throw new WrongPasswordException("Password non corretta");
		// non consento la rimozione se il dato è "null" in quanto all'interno dell'insieme dei dati non siano presetni elementi "null"
		if(data == null) throw new NullPointerException("dato 'null' non valido");
		
		// cerco il dato all'interno dell'insieme dei dati tramite il metodo searchData
		int i = this.searchData(data);
		// se il dato è presente all'interno dell'insieme lo rimuovo
		if (i != -1) {
			boardDatas.remove(i); 
			return data;
		}
		// altrimenti lancio un eccezione
		else throw new MissingDataException("Dato non presente nella bacheca");
	}

	@Override
	/* Aggiunge un like a un dato se vengono rispettati i controlli di identità */
	public void insertLike(String friend, E data) throws MissingFriendException, MissingCategoryException, MissingDataException {
		
		// non consento di inserire un like al dato da parte di un amico se quest'ultimo è "null" o se il dato è "null"
		if(friend == null || data == null) throw new NullPointerException("Amico o dato 'null' non validi");
		// cerco l'amico tramite il metodo searchFriend di this
		int indexFriend = searchFriend(friend);
		// se l'amico passato al metodo non è presente all'interno dell'insieme lancio un eccezione
		if(indexFriend == -1) throw new MissingFriendException("Amico non presente nella lista 'friends'");
		
		// ricavo l'indice del dato tramite searchData
		int i = this.searchData(data);
		// se il dato è presente all'interno dell'insieme di dati, aggiungo un like tramite il metodo addlike della classe
		// "DataE" da me creata
		if(i != -1) boardDatas.get(i).addlike(friend, friendsCat.get(indexFriend).getCategories());
		// altimenti lancio un'eccezione
		else throw new MissingDataException("Dato non presente in bacheca");
		return;
	}


	@Override
	/* Crea la lista dei dati in bacheca di una determinata categoria se vengono rispettati i controlli di identità */
	public List<E> getDataCategory(String passw, String category) throws WrongPasswordException, MissingCategoryException {
		
		// richiedo che la psw sia corretta 
		if(!passw.equals(psw))throw new WrongPasswordException("Password non corretta");
		// non consento la creazione di un insieme di dati di categoria uguale a "null" 
		// in quanto non siano presenti dati "null" all'interno dell'inseme delle categorie
		if(category == null) throw new NullPointerException("categoria 'null' non valida");
		// controllo che la categoria passata al metodo sia effettivamente presente all'interno dell'insieme delle categorie
		if(!boardCategories.contains(category)) throw new MissingCategoryException("Categoria non supportata");
		
		// creo una nuova lista, mentre scorro l'insieme dei dati, insierisco nella lista appena creata tutti i dati 
		// che hanno categoria uguale a quella passata come parametro al metodo
		List<E> dataCategory = new ArrayList<E>();
		for (int i = 0; i < boardDatas.size(); i++)
			if(boardDatas.get(i).getDataCategory().equals(category)) dataCategory.add(boardDatas.get(i).getData());
		return dataCategory;
	}


	@Override
	/* restituisce un iteratore (senza remove) che genera tutti i dati in bacheca ordinati rispetto al numero di like */ 
	/* se vengono rispettati i controlli di identità */
	public Iterator<E> getIterator(String passw) throws WrongPasswordException {
		
		// richiedo che la psw deve essere corretta 
		if(!passw.equals(psw))throw new WrongPasswordException("Password non corretta");
		// ritorno un iteratore di tipo MyIteratorLikes in grado di scorrere la lista per like decrescenti 
		return new MyIteratorLikes<E>(boardDatas);
	}


	@Override
	/* restituisce un iteratore (senza remove) che genera tutti i dati in bacheca vidibili a friend */
	public Iterator<E> getFriendIterator(String friend) throws MissingFriendException {
		
		// non consento operazioni se l'amico passato come parametro al metodo è uguale a null 
		// in quanto non siano presenti all'interno dell'inseme degli amici elementi uguali a "null"
		if(friend == null) throw new NullPointerException("Amico 'null' non valido");
		// cerco l'amico all'interno dell'insieme tramite il metodo searchFriend
		int index = searchFriend(friend);
		// se l'amico non è presente all'interno dell'insieme degli amici lancio un eccezione 
		if(index == -1) throw new MissingFriendException("Amico non presente nella lista 'friends'");
		// se ivece l'amico è presente ritorno un iteratore di tipo MyIteratorFriend in grado di visualizzare tutte le categorie visibili
		// all'amico passato come parametro al metodo
		return new MyIteratorFriend<E>(boardDatas,friendsCat.get(index).getCategories());
	}
}
