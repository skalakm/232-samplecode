package tree;

import static org.junit.Assert.*;

import java.util.NoSuchElementException;

import org.junit.Test;

import tree.CS232LinkedBinaryTree.BTNode;

public class CS232LinkedBinarySearchTreeTest {

	@Test
	public void testEmptyTreeConstructor() {
		CS232LinkedBinarySearchTree<Integer, String> bst = new CS232LinkedBinarySearchTree<Integer, String>();
		assertEquals("Incorrect size", 0, bst.size());
	}

	@Test
	public void testRootOnlyConstructor() {
		CS232LinkedBinarySearchTree<Integer, String> bst = new CS232LinkedBinarySearchTree<Integer, String>(
				1, "A");
		assertEquals("Incorrect size", 1, bst.size());
		assertEquals("Incorrect root key", new Integer(1), bst.root.key);
		assertEquals("Incorrect root value", "A", bst.root.value);
	}

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

	@Test
	public void testKeyValueListConstructorValidTree() {
		Integer[] keys = { 30, 15, 45, 10, 20, 40, 50 };
		String[] vals = { "D", "B", "F", "A", "C", "E", "G" };

		CS232LinkedBinarySearchTree<Integer, String> bst = new CS232LinkedBinarySearchTree<Integer, String>(
				keys, vals);

		checkTreeStructure(bst.root);
		checkTreeValuesInOrder(bst.root, "ABCDEFG");
	}

	@Test
	public void testKeyValueListConstructorInvalidTree() {
		Integer[] keys = { 30, 15, 45, 10, 20, 40, 42 };
		String[] vals = { "1", "2", "3", "4", "5", "6", "7" };

		try {
			new CS232LinkedBinarySearchTree<Integer, String>(keys, vals);
			fail("Key,value pairs did not satisfy BST property, should throw exception.");
		} catch (IllegalArgumentException e) {
			// pass
		} catch (Exception e) {
			fail("Incorrect exception type.");
		}
	}

	@Test
	public void testContainsRoot() {
		Integer[] keys = { 30, 15, 45, 10, 20, 40, 50 };
		String[] vals = { "D", "B", "F", "A", "C", "E", "G" };

		CS232LinkedBinarySearchTree<Integer, String> bst = new CS232LinkedBinarySearchTree<Integer, String>(
				keys, vals);

		assertTrue("Should contain root node 30,D.", bst.contains(30));
	}

	@Test
	public void testContainsInternalNode() {
		Integer[] keys = { 30, 15, 45, 10, 20, 40, 50 };
		String[] vals = { "D", "B", "F", "A", "C", "E", "G" };

		CS232LinkedBinarySearchTree<Integer, String> bst = new CS232LinkedBinarySearchTree<Integer, String>(
				keys, vals);

		assertTrue("Should contain internal node 15,B.", bst.contains(15));
		assertTrue("Should contain internal node 45,F.", bst.contains(45));
	}

	@Test
	public void testContainsLeafNodes() {
		Integer[] keys = { 30, 15, 45, 10, 20, 40, 50 };
		String[] vals = { "D", "B", "F", "A", "C", "E", "G" };

		CS232LinkedBinarySearchTree<Integer, String> bst = new CS232LinkedBinarySearchTree<Integer, String>(
				keys, vals);

		assertTrue("Should contain leaf node 10,A.", bst.contains(10));
		assertTrue("Should contain leaf node 20,C.", bst.contains(20));
		assertTrue("Should contain leaf node 40,E.", bst.contains(40));
		assertTrue("Should contain leaf node 50,G.", bst.contains(50));
	}

	@Test
	public void testContainsNotInTree() {
		Integer[] keys = { 30, 15, 45, 10, 20, 40, 50 };
		String[] vals = { "D", "B", "F", "A", "C", "E", "G" };

		CS232LinkedBinarySearchTree<Integer, String> bst = new CS232LinkedBinarySearchTree<Integer, String>(
				keys, vals);

		assertFalse("Should not contain node with key 5", bst.contains(5));
		assertFalse("Should not contain node with key 12", bst.contains(12));

		assertFalse("Should not contain node with key 17", bst.contains(17));
		assertFalse("Should not contain node with key 22", bst.contains(22));

		assertFalse("Should not contain node with key 35", bst.contains(35));
		assertFalse("Should not contain node with key 42", bst.contains(42));

		assertFalse("Should not contain node with key 47", bst.contains(47));
		assertFalse("Should not contain node with key 52", bst.contains(52));
	}

	@Test
	public void testGetRoot() {
		Integer[] keys = { 30, 15, 45, 10, 20, 40, 50 };
		String[] vals = { "D", "B", "F", "A", "C", "E", "G" };

		CS232LinkedBinarySearchTree<Integer, String> bst = new CS232LinkedBinarySearchTree<Integer, String>(
				keys, vals);

		assertEquals("Should contain root node 30,D.", "D", bst.get(30));
	}

	@Test
	public void testGetInternalNode() {
		Integer[] keys = { 30, 15, 45, 10, 20, 40, 50 };
		String[] vals = { "D", "B", "F", "A", "C", "E", "G" };

		CS232LinkedBinarySearchTree<Integer, String> bst = new CS232LinkedBinarySearchTree<Integer, String>(
				keys, vals);

		assertEquals("Should contain internal node 15,B.", "B", bst.get(15));
		assertEquals("Should contain internal node 45,F.", "F", bst.get(45));
	}

	@Test
	public void testGetLeafNode() {
		Integer[] keys = { 30, 15, 45, 10, 20, 40, 50 };
		String[] vals = { "D", "B", "F", "A", "C", "E", "G" };

		CS232LinkedBinarySearchTree<Integer, String> bst = new CS232LinkedBinarySearchTree<Integer, String>(
				keys, vals);

		assertEquals("Should contain leaf node 10,A.", "A", bst.get(10));
		assertEquals("Should contain leaf node 20,C.", "C", bst.get(20));
		assertEquals("Should contain leaf node 40,E.", "E", bst.get(40));
		assertEquals("Should contain leaf node 50,G.", "G", bst.get(50));
	}

	@Test
	public void testGetNodeNotInTree() {
		Integer[] keys = { 30, 15, 45, 10, 20, 40, 50 };
		String[] vals = { "D", "B", "F", "A", "C", "E", "G" };

		CS232LinkedBinarySearchTree<Integer, String> bst = new CS232LinkedBinarySearchTree<Integer, String>(
				keys, vals);

		assertNull("Should not contain node with key 5", bst.get(5));
		assertNull("Should not contain node with key 12", bst.get(12));

		assertNull("Should not contain node with key 17", bst.get(17));
		assertNull("Should not contain node with key 22", bst.get(22));

		assertNull("Should not contain node with key 35", bst.get(35));
		assertNull("Should not contain node with key 42", bst.get(42));

		assertNull("Should not contain node with key 47", bst.get(47));
		assertNull("Should not contain node with key 52", bst.get(52));
	}

	@Test
	public void testSetRoot() {
		Integer[] keys = { 30, 15, 45, 10, 20, 40, 50 };
		String[] vals = { "D", "B", "F", "A", "C", "E", "G" };

		CS232LinkedBinarySearchTree<Integer, String> bst = new CS232LinkedBinarySearchTree<Integer, String>(
				keys, vals);

		bst.set(30, "X");
		assertEquals("Should contain root node 30,X.", "X", bst.get(30));
	}

	@Test
	public void testSetInternalNode() {
		Integer[] keys = { 30, 15, 45, 10, 20, 40, 50 };
		String[] vals = { "D", "B", "F", "A", "C", "E", "G" };

		CS232LinkedBinarySearchTree<Integer, String> bst = new CS232LinkedBinarySearchTree<Integer, String>(
				keys, vals);

		bst.set(15, "X");
		assertEquals("Should contain internal node 15,X.", "X", bst.get(15));

		bst.set(45, "Y");
		assertEquals("Should contain internal node 45,Y.", "Y", bst.get(45));
	}

	@Test
	public void testSetLeafNode() {
		Integer[] keys = { 30, 15, 45, 10, 20, 40, 50 };
		String[] vals = { "D", "B", "F", "A", "C", "E", "G" };

		CS232LinkedBinarySearchTree<Integer, String> bst = new CS232LinkedBinarySearchTree<Integer, String>(
				keys, vals);

		bst.set(10, "W");
		assertEquals("Should contain leaf node 10,W.", "W", bst.get(10));

		bst.set(20, "X");
		assertEquals("Should contain leaf node 20,X.", "X", bst.get(20));

		bst.set(40, "Y");
		assertEquals("Should contain leaf node 40,Y.", "Y", bst.get(40));

		bst.set(50, "Z");
		assertEquals("Should contain leaf node 50,Z.", "Z", bst.get(50));
	}

	@Test
	public void testSetNodeNotInTree() {
		Integer[] keys = { 30, 15, 45, 10, 20, 40, 50 };
		String[] vals = { "D", "B", "F", "A", "C", "E", "G" };

		CS232LinkedBinarySearchTree<Integer, String> bst = new CS232LinkedBinarySearchTree<Integer, String>(
				keys, vals);

		int[] searchKeys = new int[] { 5, 12, 17, 22, 35, 42, 47, 52 };
		for (int k : searchKeys) {
			try {
				bst.set(k, "X");
				fail("Should have thrown exception.");
			} catch (NoSuchElementException e) {
				// pass
			} catch (Exception e) {
				fail("Threw incorrect exception type.");
			}
		}
	}

	@Test
	public void testAddToEmptyTree() {
		CS232LinkedBinarySearchTree<Integer, String> bst = new CS232LinkedBinarySearchTree<Integer, String>();

		bst.add(30, "D");

		assertEquals("Incorrect size", 1, bst.size());
		checkTreeStructure(bst.root);
		checkTreeValuesInOrder(bst.root, "D");
	}

	@Test
	public void testAddLeftChildOfRoot() {
		CS232LinkedBinarySearchTree<Integer, String> bst = new CS232LinkedBinarySearchTree<Integer, String>(
				30, "D");

		bst.add(15, "B");

		assertEquals("Incorrect size", 2, bst.size());
		checkTreeStructure(bst.root);
		checkTreeValuesInOrder(bst.root, "BD");
	}

	@Test
	public void testAddRightChildOfRoot() {
		CS232LinkedBinarySearchTree<Integer, String> bst = new CS232LinkedBinarySearchTree<Integer, String>(
				30, "D");

		bst.add(45, "F");

		assertEquals("Incorrect size", 2, bst.size());
		checkTreeStructure(bst.root);
		checkTreeValuesInOrder(bst.root, "DF");
	}

	@Test
	public void testAddLeftLeaf() {
		Integer[] keys = { 30, 15, 45 };
		String[] vals = { "D", "B", "F" };

		CS232LinkedBinarySearchTree<Integer, String> bst = new CS232LinkedBinarySearchTree<Integer, String>(
				keys, vals);

		bst.add(10, "A");
		assertEquals("Incorrect size", 4, bst.size());
		checkTreeStructure(bst.root);
		checkTreeValuesInOrder(bst.root, "ABDF");

		bst.add(40, "E");
		assertEquals("Incorrect size", 5, bst.size());
		checkTreeStructure(bst.root);
		checkTreeValuesInOrder(bst.root, "ABDEF");
	}

	@Test
	public void testAddRightLeaf() {
		Integer[] keys = { 30, 15, 45 };
		String[] vals = { "D", "B", "F" };

		CS232LinkedBinarySearchTree<Integer, String> bst = new CS232LinkedBinarySearchTree<Integer, String>(
				keys, vals);

		bst.add(20, "C");
		assertEquals("Incorrect size", 4, bst.size());
		checkTreeStructure(bst.root);
		checkTreeValuesInOrder(bst.root, "BCDF");

		bst.add(50, "G");
		assertEquals("Incorrect size", 5, bst.size());
		checkTreeStructure(bst.root);
		checkTreeValuesInOrder(bst.root, "BCDFG");
	}

	@Test
	public void testAddDeeperNodes() {
		Integer[] keys = { 30, 15, 45, 10, 20, 40, 50 };
		String[] vals = { "D", "B", "F", "A", "C", "E", "G" };

		CS232LinkedBinarySearchTree<Integer, String> bst = new CS232LinkedBinarySearchTree<Integer, String>(
				keys, vals);

		bst.add(5, "S");
		assertEquals("Incorrect size", 8, bst.size());
		checkTreeStructure(bst.root);
		checkTreeValuesInOrder(bst.root, "SABCDEFG");

		bst.add(12, "T");
		assertEquals("Incorrect size", 9, bst.size());
		checkTreeStructure(bst.root);
		checkTreeValuesInOrder(bst.root, "SATBCDEFG");

		bst.add(17, "U");
		assertEquals("Incorrect size", 10, bst.size());
		checkTreeStructure(bst.root);
		checkTreeValuesInOrder(bst.root, "SATBUCDEFG");

		bst.add(22, "V");
		assertEquals("Incorrect size", 11, bst.size());
		checkTreeStructure(bst.root);
		checkTreeValuesInOrder(bst.root, "SATBUCVDEFG");

		bst.add(38, "W");
		assertEquals("Incorrect size", 12, bst.size());
		checkTreeStructure(bst.root);
		checkTreeValuesInOrder(bst.root, "SATBUCVDWEFG");

		bst.add(42, "X");
		assertEquals("Incorrect size", 13, bst.size());
		checkTreeStructure(bst.root);
		checkTreeValuesInOrder(bst.root, "SATBUCVDWEXFG");

		bst.add(47, "Y");
		assertEquals("Incorrect size", 14, bst.size());
		checkTreeStructure(bst.root);
		checkTreeValuesInOrder(bst.root, "SATBUCVDWEXFYG");

		bst.add(55, "Z");
		assertEquals("Incorrect size", 15, bst.size());
		checkTreeStructure(bst.root);
		checkTreeValuesInOrder(bst.root, "SATBUCVDWEXFYGZ");
	}

	@Test
	public void testRemoveFromEmptyTree() {
		CS232LinkedBinarySearchTree<Integer, String> bst = new CS232LinkedBinarySearchTree<Integer, String>();
		String val = bst.remove(5);

		assertNull("There was nothing to remove.", val);
		assertEquals("Incorrect size", 0, bst.size());
	}

	@Test
	public void testRemoveFromOneNodeTree() {
		CS232LinkedBinarySearchTree<Integer, String> bst = new CS232LinkedBinarySearchTree<Integer, String>(
				30, "D");

		String val = bst.remove(30);

		assertEquals("Incorrect value returned", "D", val);
		assertEquals("Incorrect size", 0, bst.size());
		assertNull("Tree root should be null", bst.root);
	}

	@Test
	public void testRemoveLeftSubChildLeaf() {
		Integer[] keys = { 30, 15, 45, 10, 20, 40, 50 };
		String[] vals = { "D", "B", "F", "A", "C", "E", "G" };

		CS232LinkedBinarySearchTree<Integer, String> bst = new CS232LinkedBinarySearchTree<Integer, String>(
				keys, vals);

		String val = bst.remove(10);

		assertEquals("Incorrect value returned", "A", val);
		assertEquals("Incorrect size", 6, bst.size());

		checkTreeStructure(bst.root);
		checkTreeValuesInOrder(bst.root, "BCDEFG");

		val = bst.remove(40);

		assertEquals("Incorrect value returned", "E", val);
		assertEquals("Incorrect size", 5, bst.size());

		checkTreeStructure(bst.root);
		checkTreeValuesInOrder(bst.root, "BCDFG");
	}

	@Test
	public void testRemoveRightSubChildLeaf() {
		Integer[] keys = { 30, 15, 45, 10, 20, 40, 50 };
		String[] vals = { "D", "B", "F", "A", "C", "E", "G" };

		CS232LinkedBinarySearchTree<Integer, String> bst = new CS232LinkedBinarySearchTree<Integer, String>(
				keys, vals);

		String val = bst.remove(20);

		assertEquals("Incorrect value returned", "C", val);
		assertEquals("Incorrect size", 6, bst.size());

		checkTreeStructure(bst.root);
		checkTreeValuesInOrder(bst.root, "ABDEFG");

		val = bst.remove(50);

		assertEquals("Incorrect value returned", "G", val);
		assertEquals("Incorrect size", 5, bst.size());

		checkTreeStructure(bst.root);
		checkTreeValuesInOrder(bst.root, "ABDEF");
	}

	@Test
	public void testRemoveNodeWithLeftSubTreeOnly() {
		Integer[] keys = { 30, 15, 45, 10, 20, 40 };
		String[] vals = { "D", "B", "F", "A", "C", "E" };

		CS232LinkedBinarySearchTree<Integer, String> bst = new CS232LinkedBinarySearchTree<Integer, String>(
				keys, vals);

		String val = bst.remove(45);

		assertEquals("Incorrect value returned", "F", val);
		assertEquals("Incorrect size", 5, bst.size());

		checkTreeStructure(bst.root);
		checkTreeValuesInOrder(bst.root, "ABCDE");

		// remove right child of B so it has only a left child.
		bst.root.left.right = null;
		bst.size--;

		val = bst.remove(15);

		assertEquals("Incorrect value returned", "B", val);
		assertEquals("Incorrect size", 3, bst.size());

		checkTreeStructure(bst.root);
		checkTreeValuesInOrder(bst.root, "ADE");
	}

	@Test
	public void testRemoveNodeWithRightSubTreeOnly() {
		Integer[] keys = { 30, 15, 45, 10, 20, 40, 50 };
		String[] vals = { "D", "B", "F", "A", "C", "E", "G" };

		CS232LinkedBinarySearchTree<Integer, String> bst = new CS232LinkedBinarySearchTree<Integer, String>(
				keys, vals);

		// Create some nodes with only right subtrees.
		bst.root.left.left = null; // remove A from tree.
		bst.size--;
		bst.root.right.left = null; // remove E from tree.
		bst.size--;
		
		String val = bst.remove(15);

		assertEquals("Incorrect value returned", "B", val);
		assertEquals("Incorrect size", 4, bst.size());

		checkTreeStructure(bst.root);
		checkTreeValuesInOrder(bst.root, "CDFG");
		
		val = bst.remove(45);

		assertEquals("Incorrect value returned", "F", val);
		assertEquals("Incorrect size", 3, bst.size());

		checkTreeStructure(bst.root);
		checkTreeValuesInOrder(bst.root, "CDG");
	}

	@Test
	public void testRemoveNodeWithTwoChildren() {
		Integer[] keys = { 30, 15, 45, 10, 20, 40, 50 };
		String[] vals = { "D", "B", "F", "A", "C", "E", "G" };

		CS232LinkedBinarySearchTree<Integer, String> bst = new CS232LinkedBinarySearchTree<Integer, String>(
				keys, vals);
		
		String val = bst.remove(15);

		assertEquals("Incorrect value returned", "B", val);
		assertEquals("Incorrect size", 6, bst.size());

		checkTreeStructure(bst.root);
		checkTreeValuesInOrder(bst.root, "ACDEFG");
		
		val = bst.remove(45);

		assertEquals("Incorrect value returned", "F", val);
		assertEquals("Incorrect size", 5, bst.size());

		checkTreeStructure(bst.root);
		checkTreeValuesInOrder(bst.root, "ACDEG");
	}
	
	@Test
	public void testRemoveRootWithTwoChildren() {
		Integer[] keys = { 30, 15, 45, 10, 20, 40, 50 };
		String[] vals = { "D", "B", "F", "A", "C", "E", "G" };

		CS232LinkedBinarySearchTree<Integer, String> bst = new CS232LinkedBinarySearchTree<Integer, String>(
				keys, vals);
		
		String val = bst.remove(30);

		assertEquals("Incorrect value returned", "D", val);
		assertEquals("Incorrect size", 6, bst.size());

		checkTreeStructure(bst.root);
		checkTreeValuesInOrder(bst.root, "ABCEFG");
	}
}
