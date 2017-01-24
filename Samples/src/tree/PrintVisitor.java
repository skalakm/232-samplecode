package tree;

/**
 * A visitor that prints out the key,value pair at each node that it visits.
 * 
 * @author Grant Braught
 * @author Dickinson College
 * @version Feb 25, 2016
 */
public class PrintVisitor<K, V> implements CS232Visitor<K, V> {

	/**
	 * Print out the key,value pair at each node visited.
	 * 
	 * @param key the key for the current node.
	 * @param value the value at the current node.
	 */
	public void visit(K key, V value) {
		System.out.println("(" + key + "," + value + ")");
	}
	
	public static void main(String[] args) {
		String[] keys = { "A", "B", "C", "G", "F", "E", "D", "H", "I", "J" };
		String[] vals = { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10" };
		CS232LinkedBinaryTree<String, String> t = new CS232LinkedBinaryTree<String, String>(
				keys, vals);
		
		System.out.println("Preorder:");
		t.visitPreOrder(new PrintVisitor<String,String>());

//		System.out.println();
//		System.out.println("Inorder:");
//		t.visitInOrder(new PrintVisitor<String,String>());
		
		System.out.println();
		System.out.println("Postorder:");
		t.visitPostOrder(new PrintVisitor<String,String>());
		
		System.out.println();
		System.out.println("Levelorder:");
		t.visitLevelOrder(new PrintVisitor<String,String>());
	}
}
