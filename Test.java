import java.util.Random;


public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BST b = new BST();
		Random r = new Random();
		b.insertR(20);
		System.out.print("Redosljed dodavanja elemenata: ");
		for(int i =0 ; i < 10; i++){
			int temp = r.nextInt(100);
			System.out.print(temp+" ");
			b.insertR(temp);
		}
		System.out.println();
		
		b.inorder();
		b.preorder();
		b.postorder();
		/*
		b.insertR(55);
		b.inorder();
		
		b.deleteR(55);
		b.inorder();
		
		b.deleteR(20);
		b.inorder();
		/*
		try {
			System.out.println(b.height());
		} catch (EmptyTreeException e) {
			// TODO Auto-generated catch block	
		}
		
		System.out.println(b.size());
		System.out.println(b.numberOfLeaves());*/
	}

}