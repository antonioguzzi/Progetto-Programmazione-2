package progetto_java_2019_seconda_impl;

import java.util.*;

public class MyIteratorLikes<E> implements Iterator<E> {

	private ArrayList<E> datas;
	private int indice = -1;
	
	
	
	// COSTRUTTORE:
	public MyIteratorLikes(ArrayList<DataE<E>> boardDatas) {
		// se l'arrayList passato come parametro al metodo è uguale a "null" fermo l'operazione
		if(boardDatas == null) throw new NullPointerException("I dati presenti in bacheca non sono validi");
		
		datas = new ArrayList<E>();  // creo un nuovo arrayList
		Collections.sort(boardDatas);	// ordino l'arrayList passato come parametro al costruttore
		for(int i = 0; i < boardDatas.size(); i++)
			datas.add(boardDatas.get(i).getData());	 // aggiungo in modo ordinato ogni elemeto dell'arrayList passato come parametro al costruttore
		                                             // all'interno del nuovo arrayList
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
		//  EFFECTS: il next della posizione corrente, se l'elemento corrente non ha un successivo lancia NoSuchElementException
		
		if (hasNext() == false) throw new NoSuchElementException("Non sono piu presenti altri elementi");
		 indice++;
		 return datas.get(indice);
	}
	
	@Override
	public void remove() {
		 throw new UnsupportedOperationException("Operzione non consentita");
	 }
}
