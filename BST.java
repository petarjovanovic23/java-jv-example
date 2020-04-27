
public class BST {
	// U ovoj klasi implementirano je binarno stablo pretrazivanja
	
	Node root;
	
	private class Node{
		int value;
		Node left;
		Node right;
		
		public Node(int val){
			this.value = val;
		}
	}
	
	public BST(){
		this.root = null;
	}
	
	public void insertR(int val){
		this.root = insertR(val, this.root);
	}
	
	// Rekurzivna funkcija koja dodaje novi element u binarno stablo pretrazivanja
	// Za vjezbu implementirati iterativnu verziju ove funkcije
	// Povratna vrijednost ove funkcije je korijen stabla nakon dodavanja novog elementa
	private Node insertR(int val, Node root){
		if(root == null){
			root = new Node(val);
		}else if (val < root.value){
			root.left = insertR(val, root.left);
		}else if (val > root.value){
			root.right = insertR(val, root.right);
		}
		
		return root;
	}
	
	public void deleteR(int val){
		this.root = deleteR(val, this.root);
	}
	
	// Rekurzivna funkcija koja brise element iz binarnog stabla pretrazivanja
	// Za vjezbu implementirati iterativnu verziju ove funkcije
	// Povratna vrijednost ove funkcije je korijen stabla nakon brisanja
	private Node deleteR(int val, Node root){
		if(root == null){
			// ako je stablo prazno, ono ce ostati prazno i posle brisanja
			return null;
		}else if(val < root.value){
			// ako je vrijednost koju treba obrisati manja od vrijednosti u korijenu, pozivamo rekurzivno brisanja iz lijevog podstabla
			root.left = deleteR(val, root.left);
		}else if(val > root.value){
			// ako je vrijednost koju treba obrisati veca od vrijednosti u korijenu, pozivamo rekurzivno brisanja iz desnog podstabla
			root.right = deleteR(val, root.right);
		}else{
			// ako smo u ovom bloku, to znaci da je vrijednost koju brisemo jednaka vrijednosti u korijenu
			// ovdje razlikujemo 4 slucaja
			if(root.left!=null && root.right!=null){
				/* 1. Ako korijen ima oba sina, onda ga treba zamijeniti sa njegovim sljedbenikom ili prethodnikom.
				 * 	  To su dva cvora koja se mogu dovesti na mjesto korijena a da uslov binarnog stabla pretrazivanja bude ocuvan.
				 * 	  Prethodnik nekog cvora je cvor sa najvecom vrijednoscu u stablu, koja je manja od vrijednosti datog cvora.
				 * 	  Sljedbenik nekog cvora je cvor sa najmanjom vrijednoscu u stablu, koja je veca od vrijednosti datog cvora.
				 * 	  Za cvor koji ima oba sina, prethodnik se nalazi krajnje desno u lijevom podstablu (najveci cvor u desnom podstablu),
				 * 	  a sljedbenik se nalazi krajnje lijevo u desnom podstablu (najmanji cvor u lijevom).
				 * 	  Potpuno je svejedno koji od ova dva cvora treba uzeti i njegovu vrijednost upisati u korijenu.*/
				int succValue = minNode(root.right).value;
				root.value = succValue;
				
				/* Nakon toga pozove se brisanje vrijednosti sledbenika iz desnog podstabla. Ovo ce se svesti na slucaj 2. 
				 * jer sledbenik je krajnji lijevi cvor u desnom podstablu, sto znaci da on sigurno nema lijevog sina.*/
				root.right = deleteR(succValue, root.right);
			}else if(root.left==null){
				// 2. Ako korijen nema lijevog sina, onda je dovoljno na njegovo mjesto dovesti desnog sina i time ce cvor biti obrisan iz stabla.
				/* Napomena: Ovdje je pokriven slucaj i kada korijen nema nijednog sina, tada njega mijenja njegov desni sin, 
				 * 			 koji je u ovom slucaju null, tj. korijen postaje null.*/
				root = root.right;
			}else{
				// 3. Ako korijen nema desnog sina, onda je dovoljno na njegovo mjesto dovesti lijevog sina i time ce cvor biti obrisan iz stabla.
				// Java-in Garbage Collector ce osloboditi memoriju koju je zauzimao dati cvor i Java programer ne mora o tome da brine. 
				root = root.left;
			}
		}
		
		return root;
	}
	
	private Node minNode(Node root){
		while(root.left!=null){
			root = root.left;
		}
		
		return root;
	}
	
	// Rekurzivne funkcije za obilazak binarnog stabla (inorder, preorder i postorder)
	// Za vjezbu pokusati iterativnu implementaciju funkcija inorder, preorder i postorder
	// Napomena: Iterativna varijanta je komplikovanija, treba koristiti pomocnu strukturu podataka
	public void inorder(){
		inorder(this.root);
		System.out.println();
	}
	
	private void inorder(Node root){
		if(root == null)
			return;
		
		inorder(root.left);
		System.out.print(root.value+" ");
		inorder(root.right);
	}
	
	public void preorder(){
		preorder(this.root);
		System.out.println();
	}
	
	private void preorder(Node root){
		if(root == null)
			return;

		System.out.print(root.value+" ");
		preorder(root.left);
		preorder(root.right);
	}
	
	public void postorder(){
		postorder(this.root);
		System.out.println();
	}
	
	private void postorder(Node root){
		if(root == null)
			return;
		
		postorder(root.left);
		postorder(root.right);
		System.out.print(root.value+" ");
	}
	
	// Rekurzivna funkcija koja racuna broj cvorova u binarnom stablu
	public int size(){
		return size(this.root);
	}
	
	private int size(Node root){
		if(root == null){
			return 0;
		}else{
			// ako stablo nije prazno izbrojimo cvorove u lijevom i u desnom podstablu i uracunamo korijen
			return 1 + size(root.left) + size(root.right);
		}
	}
	
	// visina (dubina) stabla definise se kao duzina najduze putanje od korijena do nekog cvora
	public int height() throws EmptyTreeException{
		if(this.root==null)
			throw new EmptyTreeException("Stablo je prazno!");
		
		return height(this.root);
	}
	
	private int height(Node root){
		if(root.left == null && root.right == null)
			return 0;
		else {
			int height_left = 0;
			int height_right = 0;
			if (root.left != null)
				height_left = height(root.left);
			if (root.right != null)
				height_right = height(root.right);
			
			return 1+Math.max(height_left, height_right);
		}
	}
	
	// listovi su cvorovi stabla koji nemaju potomke (za binarno stablo to su lijevi i desni sin)
	public int numberOfLeaves(){
		return numberOfLeaves(this.root);
	}
	
	private int numberOfLeaves(Node root){
		if(root == null)
			return 0;
		else if(root.left == null && root.right == null)
			return 1;
		else
			return numberOfLeaves(root.left) + numberOfLeaves(root.right);
	}
	
	// Za vjezbu implementirati stampanje cvorova stabla po nivoima.
	// Napomena: Najefikasnije rjesenje, koje svakim cvorom prolazi samo jednom, koristi pomocnu strukturu podataka (red tj. BFS algoritam).
}