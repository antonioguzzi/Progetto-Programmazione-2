package progetto_java_2019;

import java.util.*;

public class DataE<E> implements Comparable<DataE<E>>{
	
	// OVERVIEW: DataE: rappresenta il dato presente in bacheca,
	//           likesFromFriend: è un insieme mutabile che rappresenta i nomi degli amici che hanno messo like al dato
	//           category: è la categoria associata a this
	//           like rappresenta: il numero di like associato a this nonché la dimensione del vettore likesFromFriend
	
	// ELEMENTO TIPICO DI likesFromFriend: <name_0 . . .name_n> con n = likesFromFriend.size()
	// ELEMENTO TIPICO DI DataE: <data, like, category, <name_0 . . . name_n>>
	
	// IR(c): c.likesFromFriend != null
	//        && for all i di c.likesFromFriend | 0 <= i < c.likesFromFriend.size() -> c.likesFromFriend.get(i) != null
	//        && for all i,j c.likesFromFriend | 0 <= i < j < c.likesFromFriend.size() -> c.likesFromFriend.get(i) != c.likesFromFriend.get(j)
	
	// AF(c): {c.likesFromFriend.get(i) | 0 <= i < c.likesFromFriend.size()}
	
	private E data;
	private int like;
	private String category;
	private ArrayList<String> likesFromFriend;
	
	
	
	// COSTRUTTORE:
	public DataE(E data, String category) {
		// non consento di creare un oggetto di questo tipo se i parametri passati al costruttore sono uguali a "null"
		if(data == null) throw new NullPointerException("dato 'null' non valido");
		if (category == null) throw new NullPointerException("Categoria 'null' non valida");
		
		this.data = data;
		this.category = category;
		this.like = 0;
		// rispetto l'invariante in quanto likesFromFriend sia != null
		likesFromFriend = new ArrayList<String>();
	}
	
	
	
	// METODO COMPARE TO:
	
	@Override
	// questo è un metodo che mi servirà all'interno dell'iteratore dei dati per scorrerli in modo decrescente di like
	public int compareTo(DataE<E> compareData) {
		// EFFECTS: restitusice un intero < 0 se this.getLike() > compareData.getLike(), altriemnti restitusice un intero > 0
		// REQUIRES: compareData != null
		// THROWS: se compareData == null lancia NullPointerException (unchecked)
		
		if(compareData == null) throw new NullPointerException("Dato 'null' non valido per la compare");
        int compareLike = compareData.getLike();
        return -(this.getLike()-compareLike);
	}
	
	
	
	// METODI DELLA CLASSE:
	
	/* fa si che l'amico "friend" possa lasciare un like al dato (solo se non l'ha già fatto in precedenza e solo se può vedere la categoria) */
	public void addlike(String friend, ArrayList<String> FriendCategories ) throws MissingCategoryException {
		// EFFECTS: Aumenta il campo like di this e aggiunge "friend" tra gli amici che hanno messo like
		// REQUIRES: l'insieme dei like non deve contenere "friend"
		//			 l'insieme delle categorie visibili dall'amico != null, l'amico non deve già aver messo like al post,
		//           l'insieme delle categorie visibili all'amico devono contenere this.category
		// THROWS: se l'insieme delle categorie visibili dall'amico == null lancia NullPointerException (unchecked)
		//         se l'amico ha gia messo like al post lancia IllegalArgumentException (unchecked)
		//         se l'insieme delle categorie visibili all'amico non contiene this.category lancia MissingCategoryException (checked)
		// MODIFIES: this
		
		// questo metodo rispetta l'invariante tramite *
		
		// * 1) grazie a questo controllo impedisco che nell'insieme degli amici che hanno messo like a this vengano inseriti elementi uguali a "null"
		if(friend == null) throw new NullPointerException("Amico 'null' non valido");
		if(FriendCategories == null) throw new NullPointerException("Categorie visibili dall'amico 'null' non valide");
		// * 2) tramite questo controllo impedisco che un amico che ha già messo like a this venga inserito all'interno dell'insieme 
		// così facendo evito che all'interno dell'insieme degli amici che hanno messo like a this siano presenti duplicati
		if(likesFromFriend.contains(friend)) throw new IllegalArgumentException("L'amico ha già messo like a questo dato");
		// se l'insieme delle categorie visibili all'amico passato come parametro al metodo non contiene la categoria di this
		// l'amico non può vedere il dato in questione e di conseguenza non può mettervi like
		if(!FriendCategories.contains(category)) throw new MissingCategoryException("La categoria di questo dato non è visibile all'amico");
		
		// se tutti i controlli vengono rispettati aumento il campo like 
		// e aggiungo l'amico all'interno dell'insieme degli amici che hanno messo like a this
		if(FriendCategories.contains(category)) like++;
		likesFromFriend.add(friend);
		return;
	}
	
	/* ritorna il dato */
	public E getData() {
		// EFFECTS: ritorna this.data
		return this.data;
	}
	
	/* ritorna il numero di like*/
	public int getLike() {
		// EFFECTS: ritorna this.like
		return this.like;
	}
	
	/* ritorna la categoria del dato*/
	public String getDataCategory() {
		// EFFECTS: ritorna this.category
		return this.category;
	}
	
	/* ritorna una lista contenente gli amici che hanno messo like al dato*/
	public ArrayList<String> getFriendsLikes(){
		// EFFECTS: ritorna this.likesFromFriend
		return this.likesFromFriend;
	}
}
