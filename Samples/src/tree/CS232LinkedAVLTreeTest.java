package tree;

import static org.junit.Assert.*;

import java.util.Random;

import org.junit.Test;

import tree.CS232LinkedAVLTree.AVLNode;
import tree.CS232LinkedBinaryTree.BTNode;

public class CS232LinkedAVLTreeTest {

	/*
	 * Helper method that traverses the tree and checks that every node is
	 * linked back to its proper parent.
	 */
	private void checkTreeStructure(BTNode<Integer, String> node) {

		if (node.left != null) {
			assertEquals("left child of " + node.key
					+ " has incorrect parent pointer", node, node.left.parent);
			checkTreeStructure(node.left);
		}

		if (node.right != null) {
			assertEquals("right child of " + node.key
					+ " has incorrect parent pointer", node, node.right.parent);
			checkTreeStructure(node.right);
		}
	}

	private void checkTreeValuesInOrder(BTNode<Integer, String> node,
			String expKeys) {
		/*
		 * Check the keys in the nodes of the tree against those in keys using
		 * an in-order traversal.
		 */
		String treeKeys = getValuesInOrder(node);
		assertEquals("Keys are not in proper locations in inorder traversal",
				expKeys, treeKeys);
	}

	private String getValuesInOrder(BTNode<Integer, String> root) {
		if (root != null) {
			String keys = getValuesInOrder(root.left);
			keys = keys + root.value;
			keys = keys + getValuesInOrder(root.right);
			return keys.trim();
		} else {
			return "";
		}
	}

	/**
	 * Get the height of a given node. If the node is null, we'll define the
	 * height as -1. This works with the getNodeHeightFromChildren method so
	 * that the height of a leaf node is set to 0.
	 */
	private int getNodeHeight(AVLNode<Integer, String> node) {
		if (node == null) {
			return 0;
		} else {
			return node.height;
		}
	}

	/**
	 * Get the height of a node as one more than the height of its highest
	 * child. This method assumes that the height of any child of node has
	 * already been set.
	 * 
	 * @param root
	 *            a non-null node.
	 * @return the height of the node.
	 */
	private int computeNodeHeightFromChildren(AVLNode<Integer, String> node) {
		return Math.max(getNodeHeight((AVLNode<Integer, String>) node.left),
				getNodeHeight((AVLNode<Integer, String>) node.right)) + 1;
	}

	/**
	 * Get the balance of a node as the difference in height between its
	 * children. This method assumes that the height of any child of node has
	 * already been set.
	 * 
	 * @param root
	 *            a non-null node.
	 * @return the balance of node.
	 */
	private int computeNodeBalance(AVLNode<Integer, String> node) {
		return getNodeHeight((AVLNode<Integer, String>) node.left)
				- getNodeHeight((AVLNode<Integer, String>) node.right);
	}

	private void checkHeightAndBalance(AVLNode<Integer, String> subTreeRoot) {
		/*
		 * Do a post order traversal and check the height and balance at each
		 * node against what we compute here. Also check that every node is
		 * balanced.
		 */

		if (subTreeRoot != null) {
			checkHeightAndBalance((AVLNode<Integer, String>) subTreeRoot.left);
			checkHeightAndBalance((AVLNode<Integer, String>) subTreeRoot.right);

			int correctHeight = computeNodeHeightFromChildren(subTreeRoot);
			int correctBalance = computeNodeBalance(subTreeRoot);

			assertEquals("Height of node with key " + subTreeRoot.key
					+ " is not correct", correctHeight, subTreeRoot.height);
			assertEquals("Balance of node with key " + subTreeRoot.key
					+ " is not correct", correctBalance, subTreeRoot.balance);

			assertTrue(
					"Node with key " + subTreeRoot.key + " is not balanced.",
					subTreeRoot.balance >= -1 && subTreeRoot.balance <= 1);
		}
	}

	@Test
	public void testKeyValueListConstructorValidTree() {
		Integer[] keys = { 30, 15, 45, 10, 20, 40, 50 };
		String[] vals = { "D", "B", "F", "A", "C", "E", "G" };

		CS232LinkedAVLTree<Integer, String> avl = new CS232LinkedAVLTree<Integer, String>(
				keys, vals);

		checkTreeStructure(avl.root);
		checkHeightAndBalance((AVLNode<Integer, String>) avl.root);
		checkTreeValuesInOrder(avl.root, "ABCDEFG");
	}

	@Test
	public void testAddToEmptyTree() {
		CS232LinkedAVLTree<Integer, String> avl = new CS232LinkedAVLTree<Integer, String>();
		avl.add(10, "A");

		assertEquals("Root should have key 10", new Integer(10), avl.root.key);
		assertEquals("Root should have value A", "A", avl.root.value);
		assertEquals("Tree should have size 1", 1, avl.size());
	}

	@Test
	public void testAddToRootOnlyTree() {
		CS232LinkedAVLTree<Integer, String> avl = new CS232LinkedAVLTree<Integer, String>(
				10, "A");
		avl.add(5, "B");

		checkTreeStructure(avl.root);
		checkHeightAndBalance((AVLNode<Integer, String>) avl.root);
		checkTreeValuesInOrder(avl.root, "BA");
	}

	@Test
	public void testAddWithoutUnbalancing() {
		CS232LinkedAVLTree<Integer, String> avl = new CS232LinkedAVLTree<Integer, String>();
		avl.add(10, "A");
		avl.add(5, "B");
		avl.add(15, "C");

		checkTreeStructure(avl.root);
		checkHeightAndBalance((AVLNode<Integer, String>) avl.root);
		checkTreeValuesInOrder(avl.root, "BAC");

		avl.add(20, "G");
		avl.add(13, "F");
		avl.add(8, "E");
		avl.add(3, "D");

		checkTreeStructure(avl.root);
		checkHeightAndBalance((AVLNode<Integer, String>) avl.root);
		checkTreeValuesInOrder(avl.root, "DBEAFCG");
	}

	private CS232LinkedAVLTree<Integer, String> getTestTree() {
		CS232LinkedAVLTree<Integer, String> avl = new CS232LinkedAVLTree<Integer, String>();

		// Does not require any rebalancing.

		avl.add(20, "F");

		avl.add(10, "D");
		avl.add(30, "H");

		avl.add(5, "B");
		avl.add(15, "E");
		avl.add(25, "G");
		avl.add(35, "J");

		avl.add(2, "A");
		avl.add(7, "C");
		avl.add(33, "I");
		avl.add(38, "K");

		return avl;
	}

	@Test
	public void testAddCreatesCaseRROnly3Nodes() {
		CS232LinkedAVLTree<Integer, String> avl = new CS232LinkedAVLTree<Integer, String>();
		avl.add(5, "A");
		avl.add(10, "B");
		avl.add(15, "C");

		checkTreeStructure(avl.root);
		checkHeightAndBalance((AVLNode<Integer, String>) avl.root);
		checkTreeValuesInOrder(avl.root, "ABC");
	}

	@Test
	public void testAddCreatesCaseRRManyNodes() {
		CS232LinkedAVLTree<Integer, String> avl = getTestTree();
		avl.add(40, "Z");

		checkTreeStructure(avl.root);
		checkHeightAndBalance((AVLNode<Integer, String>) avl.root);
		checkTreeValuesInOrder(avl.root, "ABCDEFGHIJKZ");
	}

	@Test
	public void testAddCreatesCaseLLOnly3Nodes() {
		CS232LinkedAVLTree<Integer, String> avl = new CS232LinkedAVLTree<Integer, String>();
		avl.add(15, "C");
		avl.add(10, "B");
		avl.add(5, "A");

		checkTreeStructure(avl.root);
		checkHeightAndBalance((AVLNode<Integer, String>) avl.root);
		checkTreeValuesInOrder(avl.root, "ABC");
	}

	@Test
	public void testAddCreatesCaseLLManyNodes() {
		CS232LinkedAVLTree<Integer, String> avl = getTestTree();
		avl.add(1, "Z");

		checkTreeStructure(avl.root);
		checkHeightAndBalance((AVLNode<Integer, String>) avl.root);
		checkTreeValuesInOrder(avl.root, "ZABCDEFGHIJK");
	}

	@Test
	public void testAddCreatesCaseRLOnly3Nodes() {
		CS232LinkedAVLTree<Integer, String> avl = new CS232LinkedAVLTree<Integer, String>();
		avl.add(10, "A");
		avl.add(15, "C");
		avl.add(13, "B");

		checkTreeStructure(avl.root);
		checkHeightAndBalance((AVLNode<Integer, String>) avl.root);
		checkTreeValuesInOrder(avl.root, "ABC");
	}

	@Test
	public void testAddCreatesCaseRLManyNodes() {
		CS232LinkedAVLTree<Integer, String> avl = getTestTree();
		avl.add(31, "Z");

		checkTreeStructure(avl.root);
		checkHeightAndBalance((AVLNode<Integer, String>) avl.root);
		checkTreeValuesInOrder(avl.root, "ABCDEFGHZIJK");
	}

	@Test
	public void testAddCreatesCaseLROnly3Nodes() {
		CS232LinkedAVLTree<Integer, String> avl = new CS232LinkedAVLTree<Integer, String>();
		avl.add(10, "C");
		avl.add(5, "A");
		avl.add(8, "B");

		checkTreeStructure(avl.root);
		checkHeightAndBalance((AVLNode<Integer, String>) avl.root);
		checkTreeValuesInOrder(avl.root, "ABC");
	}

	@Test
	public void testAddCreatesCaseLRManyNodes() {
		CS232LinkedAVLTree<Integer, String> avl = getTestTree();
		avl.add(8, "Z");

		checkTreeStructure(avl.root);
		checkHeightAndBalance((AVLNode<Integer, String>) avl.root);
		checkTreeValuesInOrder(avl.root, "ABCZDEFGHIJK");
	}

	@Test
	public void testLotsOfRandomAdds() {
		CS232LinkedAVLTree<Integer, String> avl = new CS232LinkedAVLTree<Integer, String>();

		Random rnd = new Random();
		for (int i = 0; i < 1000; i++) {
			int key = rnd.nextInt();
			avl.add(key, "" + key);

			checkTreeStructure(avl.root);
			checkHeightAndBalance((AVLNode<Integer, String>) avl.root);
		}
	}

	@Test
	public void testRemoveNoRebalancing() {
		CS232LinkedAVLTree<Integer, String> avl = getTestTree();

		avl.remove(2);
		checkTreeStructure(avl.root);
		checkHeightAndBalance((AVLNode<Integer, String>) avl.root);
		checkTreeValuesInOrder(avl.root, "BCDEFGHIJK");

		avl.remove(7);
		checkTreeStructure(avl.root);
		checkHeightAndBalance((AVLNode<Integer, String>) avl.root);
		checkTreeValuesInOrder(avl.root, "BDEFGHIJK");

		avl.remove(33);
		checkTreeStructure(avl.root);
		checkHeightAndBalance((AVLNode<Integer, String>) avl.root);
		checkTreeValuesInOrder(avl.root, "BDEFGHJK");

		avl.remove(38);
		checkTreeStructure(avl.root);
		checkHeightAndBalance((AVLNode<Integer, String>) avl.root);
		checkTreeValuesInOrder(avl.root, "BDEFGHJ");
	}

	@Test
	public void testRemoveCreatesLLOnly3Nodes() {
		CS232LinkedAVLTree<Integer, String> avl = new CS232LinkedAVLTree<Integer, String>();
		avl.add(10, "D");
		avl.add(5, "B");
		avl.add(15, "C");
		avl.add(1, "A");

		avl.remove(15); // creates LL imbalance.

		checkTreeStructure(avl.root);
		checkHeightAndBalance((AVLNode<Integer, String>) avl.root);
		checkTreeValuesInOrder(avl.root, "ABD");
	}

	@Test
	public void testRemoveCreatesLLManyNodes() {
		CS232LinkedAVLTree<Integer, String> avl = getTestTree();

		avl.remove(15); // creates LL imbalance

		checkTreeStructure(avl.root);
		checkHeightAndBalance((AVLNode<Integer, String>) avl.root);
		checkTreeValuesInOrder(avl.root, "ABCDFGHIJK");
	}

	@Test
	public void testRemoveCreatesRROnly3Nodes() {
		CS232LinkedAVLTree<Integer, String> avl = new CS232LinkedAVLTree<Integer, String>();
		avl.add(10, "A");
		avl.add(8, "B");
		avl.add(12, "C");
		avl.add(20, "D");

		avl.remove(8); // creates RR imbalance.

		checkTreeStructure(avl.root);
		checkHeightAndBalance((AVLNode<Integer, String>) avl.root);
		checkTreeValuesInOrder(avl.root, "ACD");
	}

	@Test
	public void testRemoveCreatesRRManyNodes() {
		CS232LinkedAVLTree<Integer, String> avl = getTestTree();

		avl.remove(25); // creates LL imbalance

		checkTreeStructure(avl.root);
		checkHeightAndBalance((AVLNode<Integer, String>) avl.root);
		checkTreeValuesInOrder(avl.root, "ABCDEFHIJK");
	}

	@Test
	public void testRemoveCreatesRLOnly3Nodes() {
		CS232LinkedAVLTree<Integer, String> avl = new CS232LinkedAVLTree<Integer, String>();
		avl.add(10, "B");
		avl.add(15, "D");
		avl.add(8, "A");
		avl.add(13, "C");

		avl.remove(8); // Creates RL imbalance

		checkTreeStructure(avl.root);
		checkHeightAndBalance((AVLNode<Integer, String>) avl.root);
		checkTreeValuesInOrder(avl.root, "BCD");
	}

	@Test
	public void testRemoveCreatesRLManyNodes() {
		CS232LinkedAVLTree<Integer, String> avl = getTestTree();

		avl.remove(38);
		avl.remove(25); // creates RL imbalance

		checkTreeStructure(avl.root);
		checkHeightAndBalance((AVLNode<Integer, String>) avl.root);
		checkTreeValuesInOrder(avl.root, "ABCDEFHIJ");
	}

	@Test
	public void testRemoveCreatesLROnly3Nodes() {
		CS232LinkedAVLTree<Integer, String> avl = new CS232LinkedAVLTree<Integer, String>();
		avl.add(10, "C");
		avl.add(5, "A");
		avl.add(15, "D");
		avl.add(8, "B");

		avl.remove(15); // creates LR imbalance.

		checkTreeStructure(avl.root);
		checkHeightAndBalance((AVLNode<Integer, String>) avl.root);
		checkTreeValuesInOrder(avl.root, "ABC");
	}

	@Test
	public void testRemoveCreatesLRManyNodes() {
		CS232LinkedAVLTree<Integer, String> avl = getTestTree();

		avl.remove(2);
		avl.remove(15); // creates LR imbalance

		checkTreeStructure(avl.root);
		checkHeightAndBalance((AVLNode<Integer, String>) avl.root);
		checkTreeValuesInOrder(avl.root, "BCDFGHIJK");
	}

	@Test
	public void testRemoveCreatesRipplingImbalance1() {
		/*
		 * Example from:
		 * http://stackoverflow.com/questions/20912461/more-than-one
		 * -rotation-needed-to-balance-an-avl-tree
		 */

		CS232LinkedAVLTree<Integer, String> avl = new CS232LinkedAVLTree<Integer, String>();
		avl.add(50, "E");
		avl.add(25, "B");
		avl.add(75, "J");
		avl.add(15, "A");
		avl.add(40, "D");
		avl.add(60, "G");
		avl.add(80, "K");
		avl.add(35, "C");
		avl.add(55, "F");
		avl.add(65, "I");
		avl.add(90, "L");
		avl.add(62, "H");

		avl.remove(15);

		checkTreeStructure(avl.root);
		checkHeightAndBalance((AVLNode<Integer, String>) avl.root);
		checkTreeValuesInOrder(avl.root, "BCDEFGHIJKL");
	}

	@Test
	public void testRemoveCreatesRipplingImbalance2() {
		/*
		 * Example from:
		 * http://www.mathcs.emory.edu/~cheung/Courses/323/Syllabus
		 * /Trees/AVL-delete.html
		 */
		
		CS232LinkedAVLTree<Integer, String> avl = new CS232LinkedAVLTree<Integer, String>();
		avl.add(50,"H");
		avl.add(25,"E");
		avl.add(75,"K");
		avl.add(10,"C");
		avl.add(30,"G");
		avl.add(60,"J");
		avl.add(80,"L");
		avl.add(5,"B");	
		avl.add(15,"D");
		avl.add(27,"F");
		avl.add(55,"I");
		avl.add(1,"A");		

		avl.remove(80);

		checkTreeStructure(avl.root);
		checkHeightAndBalance((AVLNode<Integer, String>) avl.root);
		checkTreeValuesInOrder(avl.root, "ABCDEFGHIJK");
		
	}

	@Test
	public void testLotsOfRandomRemoves() {
		CS232LinkedAVLTree<Integer, String> avl = new CS232LinkedAVLTree<Integer, String>();
		for (int i = 0; i < 1000; i++) {
			avl.add(i, "" + i);
		}
		
		checkTreeStructure(avl.root);
		checkHeightAndBalance((AVLNode<Integer, String>) avl.root);

		Random rnd = new Random();
		for (int i = 0; i < 1000; i++) {
			int key = rnd.nextInt(1000);
			String val = avl.remove(key);
			
			checkTreeStructure(avl.root);
			checkHeightAndBalance((AVLNode<Integer, String>) avl.root);
		}
	}
}
