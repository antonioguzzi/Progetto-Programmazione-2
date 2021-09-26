package progetto_java_2019_seconda_impl;

import java.util.*;

public class MyIteratorFriend <E> implements Iterator<E> {
	
	private ArrayList<E> datas;
	private int indice = -1;
	
	
	
	// COSTRUTTORE:
	public MyIteratorFriend(ArrayList<DataE<E>> boardDatas, ArrayList<String> friendCategories) {
		// se gli arrayList passati come parametri al metodo sono uguali a "null" fermo l'operazione
		if(boardDatas == null) throw new NullPointerException("I dati presenti in bacheca non sono validi");
		if (friendCategories == null) throw new NullPointerException("Amico non valido per MyIteratorFriend");
		
		datas = new ArrayList<E>();	  // creo un nuovo arrayList
		for(int i = 0; i < boardDatas.size(); i++)
			// se all'interno delle categorie visibili all'amico  è presente la categoria del dato, 
			// quest'ultimo viene inserito all'interno del nuovo arrayList.
			// tale operazione avviene grazie al metodo getDataCategory della classe "DataE" da me creata
			if(friendCategories.contains(boardDatas.get(i).getDataCategory())) datas.add(boardDatas.get(i).getData());
	}
	
	
	
	// METODI ITERATOR:
	@Override
	public boolean hasNext() {
		// EFFECTS: ritorna true se this.datas ha un next, false altrimenti
		
		if (datas.size()-1 == indice) return false;
		 else return true;
	}

	@Override
	public E next() {
		// EFFECTS: il next della posizione corrente, se l'elemento corrente non ha un successivo lancia NoSuchElementException
		
		if (hasNext() == false) throw new NoSuchElementException("Non sono piu presenti altri elementi");
		 indice++;
		 return datas.get(indice);
	}
	
	@Override
	public void remove() {
		 throw new UnsupportedOperationException("Operzione non consentita");
	 }
}
