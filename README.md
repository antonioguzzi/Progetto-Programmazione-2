###### Studente:
###### Antonio Guzzi
###### Matricola 581947

##### Anno Accademico 2019/20
# Relazione
## Premessa
Per l’implementazione ho utilizzato 2 strutture dati differenti: nella prima implementazione ho deciso di utilizzare delle strutture dati di tipo ArrayList per rappresentare l’insieme delle categorie,  l’insieme dei dati e  l’insieme degli amici. Nella seconda implementazione ho deciso di utilizzare delle HashMap; L’utente potrà accedere alla propria bacheca tramite l’inserimento di una password (utilizzata per il controllo di identità). L’idea principale è quella di garantire l’unicità all’interno degli insiemi di entrambe le implementazioni.
il proprietario della bacheca potrà, inoltre, decidere quali dati all’interno dell’insieme rendere visibili agli amici, quest’ultimi potranno mettervi un like ai dati resi così visibili.
## Prima implemetazione
Questa implementazione, si basa su tre ArrayList denominati boardCategories, boardDatas
e friendsCat.
Nel primo  ArrayList  saranno memorizzate le categorie della bacheca (questo ArrayList sarà di tipo String).
Nel secondo ArrayList saranno memorizzati dei dati ti tipo **DataE\<E\>**, questa classe da me implementata rappresenta un dato della bacheca e conterrà al suo interno una variabile d’istanza di tipo E che andrà a rappresentare il dato vero e proprio. All’interno della classe è inoltre presente: 
- una variabile di tipo String che rappresenta la categoria a cui il dato appartiene.
- una variabile di tipo int che serve a rappresentare i like del dato.
- un ArrayList di amici nel quale andrò ad inserire i nomi degli amici che hanno messo like al dato, questo insieme mi permetterà anche di garantire che ogni amico possa inserire al più un like per ogni dato a lui visibile.

Il terzo ArrayList servirà a rappresentare gli amici, anche in questo caso ho  implementato la classe **“Friend”** composta dal nome (una variabile ti tipo String) che identificherà l’amico e da un
ArrayList<String> rappresentante le categorie visibili all’amico.
## Seconda implementazione
In questa implementazione ho utilizzato due HashMap rispettivamente denominate boardDataCat e friendsCat.

La prima HashMap (**boardDataCat**) di tipo <String,ArrayList<DataE\<E\>>> è stata realizzata utilizzando come chiave le categorie e come valore un ArrayList di dati, questo mi garantisce che ad ogni categoria venga associato un insieme di dati sempre di tipo **DataE\<E\>** come nella prima implementazione, la classe all’interno dell’insieme dei valori rimane quindi invariata rispetto alla prima implementazione. 

La seconda HashMap (**friendsCat**) di tipo <String,ArrayList\<String\>> è stata realizzata utilizzando come chiave il nome dell’amico e come valore un vettore di String finalizzato alla rappresentazione dell’insieme di categorie visibili all’amico (ovvero alla chiave dell’HashMap).
## Tipo generico
Per gestire il tipo generico E che rappresenta i dati ho creato un nuovo tipo **Type** che implementa Data, questa classe è formata da un costruttore per la realizzazione di dati di tipo String
infatti al suo interno contiene una variabile String chiamata **post**  e il metodo display che serve a
stampare il dato a schermo.
