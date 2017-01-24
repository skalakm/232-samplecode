package tree;

import static org.junit.Assert.*;

import java.util.NoSuchElementException;

import org.junit.Test;

import tree.CS232LinkedBinaryTree.BTNode;

public class CS232LinkedBinaryTreeTest {

	@Test
	public void testDefaultConstructor() {
		CS232LinkedBinaryTree<String, Integer> t = new CS232LinkedBinaryTree<String, Integer>();
		assertEquals("Default constructor failed", 0, t.size());
	}

	@Test
	public void testRootNodeOnlyConstructor() {
		CS232LinkedBinaryTree<String, String> t = new CS232LinkedBinaryTree<String, String>(
				"Test", "1");
		assertEquals("Default constructor failed", 1, t.size());
		assertNotNull("Key not contained in tree", t.get("Test"));
		assertEquals("Incorrect value for key", "1", t.get("Test"));
	}

	@Test
	public void testCompleteTreeConstructor() {
		String[] keys = { "A", "B", "C", "D", "E", "F", "G" };
		String[] vals = { "1", "2", "3", "4", "5", "6", "7" };
		CS232LinkedBinaryTree<String, String> t = new CS232LinkedBinaryTree<String, String>(
				keys, vals);

		assertEquals("Default constructor failed", 7, t.size());

		for (int i = 0; i < keys.length; i++) {
			assertNotNull("Key not contained in tree: " + keys[i],
					t.get(keys[i]));
			assertEquals("Incorrect value for key: " + keys[i], vals[i],
					t.get(keys[i]));
		}

		checkTreeStructure(t.root);
		checkTreeKeysInOrder(t.root, "DBEAFCG");
	}

	@Test
	public void testSubTreeConstructorEmptySubTrees() {
		CS232LinkedBinaryTree<String, String> rTree = new CS232LinkedBinaryTree<String, String>();
		CS232LinkedBinaryTree<String, String> lTree = new CS232LinkedBinaryTree<String, String>();
		CS232LinkedBinaryTree<String, String> t = new CS232LinkedBinaryTree<String, String>(
				lTree, "A", "1", rTree);

		assertEquals("Incorrect size", 1, t.size());
		assertNotNull("Tree does not contain added key", t.get("A"));
		assertEquals("Incorrect value associated with key", "1", t.get("A"));
	}

	@Test
	public void testSubTreeConstructorEmptyLeftTree() {
		String[] rKeys = { "A", "B", "C" };
		String[] rVals = { "1", "2", "3" };
		CS232LinkedBinaryTree<String, String> rTree = new CS232LinkedBinaryTree<String, String>(
				rKeys, rVals);

		CS232LinkedBinaryTree<String, String> lTree = new CS232LinkedBinaryTree<String, String>();

		CS232LinkedBinaryTree<String, String> t = new CS232LinkedBinaryTree<String, String>(
				lTree, "D", "4", rTree);

		assertEquals("Incorrect size", 4, t.size());

		checkTreeStructure(t.root);
		checkTreeKeysInOrder(t.root, "DBAC");
	}

	@Test
	public void testSubTreeConstructorEmptyRightTree() {

		CS232LinkedBinaryTree<String, String> rTree = new CS232LinkedBinaryTree<String, String>();

		String[] lKeys = { "A", "B", "C" };
		String[] lVals = { "1", "2", "3" };
		CS232LinkedBinaryTree<String, String> lTree = new CS232LinkedBinaryTree<String, String>(
				lKeys, lVals);

		CS232LinkedBinaryTree<String, String> t = new CS232LinkedBinaryTree<String, String>(
				lTree, "D", "4", rTree);

		assertEquals("Incorrect size", 4, t.size());

		checkTreeStructure(t.root);
		checkTreeKeysInOrder(t.root, "BACD");
	}

	@Test
	public void testSubTreeConstructorTwoNonEmptyTrees() {
		String[] rKeys = { "A", "B", "C" };
		String[] rVals = { "1", "2", "3" };
		CS232LinkedBinaryTree<String, String> rTree = new CS232LinkedBinaryTree<String, String>(
				rKeys, rVals);
		
		String[] lKeys = { "D", "E", "F" };
		String[] lVals = { "4", "5", "6" };
		CS232LinkedBinaryTree<String, String> lTree = new CS232LinkedBinaryTree<String, String>(
				lKeys, lVals);

		CS232LinkedBinaryTree<String, String> t = new CS232LinkedBinaryTree<String, String>(
				lTree, "G", "7", rTree);

		assertEquals("Incorrect size", 7, t.size());

		checkTreeStructure(t.root);
		checkTreeKeysInOrder(t.root, "EDFGBAC");
	}

	/*
	 * Helper method that traverses the tree and checks that every node is
	 * linked back to its proper parent.
	 */
	private void checkTreeStructure(BTNode<String, String> node) {
		
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

	private void checkTreeKeysInOrder(BTNode<String, String> node,
			String expKeys) {
		/*
		 * Check the keys in the nodes of the tree against those in keys using
		 * an in-order traversal.
		 */
		String treeKeys = getKeysInOrder(node);
		assertEquals("Keys are not in proper locations in inorder traversal",
				expKeys, treeKeys);
	}

	private String getKeysInOrder(BTNode<String, String> root) {
		if (root != null) {
			String keys = getKeysInOrder(root.left);
			keys = keys + root.key;
			keys = keys + getKeysInOrder(root.right);
			return keys;
		} else {
			return "";
		}
	}

	@Test
	public void testAddNewNodeAsRoot() {
		CS232LinkedBinaryTree<String, String> t = new CS232LinkedBinaryTree<String, String>();
		t.add("A", "1");

		assertEquals("Incorrect size", 1, t.size());
		assertNotNull("Tree does not contain added key", t.get("A"));
		assertEquals("Incorrect value associated with key", "1", t.get("A"));

		checkTreeStructure(t.root);
	}

	@Test
	public void testAddNewNodeAsLeftChildOfRoot() {
		CS232LinkedBinaryTree<String, String> t = new CS232LinkedBinaryTree<String, String>(
				"A", "1");
		t.add("B", "2");

		assertEquals("Incorrect size", 2, t.size());
		assertNotNull("Tree does not contain added key", t.get("B"));
		assertEquals("Incorrect value associated with key", "2", t.get("B"));

		checkTreeStructure(t.root);
		checkTreeKeysInOrder(t.root, "BA");
	}

	@Test
	public void testAddNewNodeAsRightChildOfRoot() {
		String[] keys = new String[] { "A", "B" };
		String[] vals = new String[] { "1", "2" };
		CS232LinkedBinaryTree<String, String> t = new CS232LinkedBinaryTree<String, String>(
				keys, vals);
		t.add("C", "3");

		assertEquals("Incorrect size", 3, t.size());
		assertNotNull("Tree does not contain added key", t.get("C"));
		assertEquals("Incorrect value associated with key", "3", t.get("C"));

		checkTreeStructure(t.root);
		checkTreeKeysInOrder(t.root, "BAC");
	}

	@Test
	public void testAddNewNodeAsLeftChild() {
		String[] keys = new String[] { "A", "B", "C", "D", "E" };
		String[] vals = new String[] { "1", "2", "3", "4", "5" };
		CS232LinkedBinaryTree<String, String> t = new CS232LinkedBinaryTree<String, String>(
				keys, vals);
		t.add("F", "6");

		assertEquals("Incorrect size", 6, t.size());
		assertNotNull("Tree does not contain added key", t.get("F"));
		assertEquals("Incorrect value associated with key", "6", t.get("F"));

		checkTreeStructure(t.root);
		checkTreeKeysInOrder(t.root, "DBEAFC");
	}

	@Test
	public void testAddNewNodeAsRightChild() {
		String[] keys = new String[] { "A", "B", "C", "D", "E", "F" };
		String[] vals = new String[] { "1", "2", "3", "4", "5", "6" };
		CS232LinkedBinaryTree<String, String> t = new CS232LinkedBinaryTree<String, String>(
				keys, vals);
		t.add("G", "7");

		assertEquals("Incorrect size", 7, t.size());
		assertNotNull("Tree does not contain added key", t.get("G"));
		assertEquals("Incorrect value associated with key", "7", t.get("G"));

		checkTreeStructure(t.root);
		checkTreeKeysInOrder(t.root, "DBEAFCG");
	}

	@Test
	public void testAddNewNodeAsLeftChildNotLast() {
		String[] keys = new String[] { "A", "B", "C", "D", "E", "F", "G" };
		String[] vals = new String[] { "1", "2", "3", "4", "5", "6", "7" };
		CS232LinkedBinaryTree<String, String> t = new CS232LinkedBinaryTree<String, String>(
				keys, vals);
		t.remove("F");
		t.add("H", "8");

		assertEquals("Incorrect size", 7, t.size());
		assertNotNull("Tree does not contain added key", t.get("H"));
		assertEquals("Incorrect value associated with key", "8", t.get("H"));

		checkTreeStructure(t.root);
		checkTreeKeysInOrder(t.root, "DBEAHCG");
	}

	@Test
	public void testAddNewNodeAsRightChildNotLast() {
		String[] keys = new String[] { "A", "B", "C", "D", "E", "F", "G" };
		String[] vals = new String[] { "1", "2", "3", "4", "5", "6", "7" };
		CS232LinkedBinaryTree<String, String> t = new CS232LinkedBinaryTree<String, String>(
				keys, vals);
		t.remove("E");
		t.add("H", "8");

		assertEquals("Incorrect size", 7, t.size());
		assertNotNull("Tree does not contain added key", t.get("H"));
		assertEquals("Incorrect value associated with key", "8", t.get("H"));

		checkTreeStructure(t.root);
		checkTreeKeysInOrder(t.root, "DBHAFCG");
	}

	@Test
	public void setRootInOneNodeTree() {
		String[] keys = new String[] { "A" };
		String[] vals = new String[] { "1" };
		CS232LinkedBinaryTree<String, String> t = new CS232LinkedBinaryTree<String, String>(
				keys, vals);
		
		t.set("A", "20");
		
		assertEquals("Value of node not set", "20", t.get("A"));
	}
	
	@Test 
	public void testSetRootInLargerTree() {
		String[] keys = new String[] { "A", "B", "C"};
		String[] vals = new String[] { "1", "2", "3" };
		CS232LinkedBinaryTree<String, String> t = new CS232LinkedBinaryTree<String, String>(
				keys, vals);
		
		t.set("A", "20");
		
		assertEquals("Value of node not set", "20", t.get("A"));
	}
	
	@Test
	public void testSetLeftChildOfRoot() {
		String[] keys = new String[] { "A", "B", "C"};
		String[] vals = new String[] { "1", "2", "3" };
		CS232LinkedBinaryTree<String, String> t = new CS232LinkedBinaryTree<String, String>(
				keys, vals);
		
		t.set("B", "20");
		
		assertEquals("Value of node not set", "20", t.get("B"));
	}
	
	@Test
	public void testSetRightChildOfRoot() {
		String[] keys = new String[] { "A", "B", "C"};
		String[] vals = new String[] { "1", "2", "3" };
		CS232LinkedBinaryTree<String, String> t = new CS232LinkedBinaryTree<String, String>(
				keys, vals);
		
		t.set("C", "20");
		
		assertEquals("Value of node not set", "20", t.get("C"));
	}
	
	@Test
	public void testSetDeeperLeaves() {
		String[] keys = { "A", "B", "C", "D", "E", "F", "G" };
		String[] vals = { "1", "2", "3", "4", "5", "6", "7" };
		CS232LinkedBinaryTree<String, String> t = new CS232LinkedBinaryTree<String, String>(
				keys, vals);
		
		t.set("D", "20");
		assertEquals("Value of node not set", "20", t.get("D"));
		
		t.set("E", "21");
		assertEquals("Value of node not set", "21", t.get("E"));
		
		t.set("F", "22");
		assertEquals("Value of node not set", "22", t.get("F"));
		
		t.set("G", "23");
		assertEquals("Value of node not set", "23", t.get("G"));
	}
	
	@Test
	public void testSetInternalNodes() {
		String[] keys = { "A", "B", "C", "D", "E", "F", "G" };
		String[] vals = { "1", "2", "3", "4", "5", "6", "7" };
		CS232LinkedBinaryTree<String, String> t = new CS232LinkedBinaryTree<String, String>(
				keys, vals);
		
		t.set("B", "20");
		assertEquals("Value of node not set", "20", t.get("B"));
		
		t.set("C", "21");
		assertEquals("Value of node not set", "21", t.get("C"));
	}
	
	@Test
	public void testSetNodeKeyDoesNotExist() {
		String[] keys = new String[] { "A", "B", "C"};
		String[] vals = new String[] { "1", "2", "3" };
		CS232LinkedBinaryTree<String, String> t = new CS232LinkedBinaryTree<String, String>(
				keys, vals);
		
		try {
			t.set("Z", "20");
			fail("Should have thown exception, no key Z");
		}
		catch(NoSuchElementException e) {
			// pass
		}
		catch(Exception e) {
			fail("Incorrect exception type thrown.");
		}
	}
	
	@Test
	public void testRemoveFromEmptyTree() {
		CS232LinkedBinaryTree<String, String> t = new CS232LinkedBinaryTree<String, String>();

		String s = t.remove("Z");
		assertNull("Should not have removed a value", s);
		assertEquals("Incorrect size.", 0, t.size());
	}

	@Test
	public void testRemoveKeyNotInTree() {
		String[] keys = { "A", "B", "C", "D", "E", "F", "G" };
		String[] vals = { "1", "2", "3", "4", "5", "6", "7" };
		CS232LinkedBinaryTree<String, String> t = new CS232LinkedBinaryTree<String, String>(
				keys, vals);

		String s = t.remove("Z");
		assertNull("Should not have removed a value", s);
		assertEquals("Incorrect size.", 7, t.size());

		checkTreeStructure(t.root);
		checkTreeKeysInOrder(t.root, "DBEAFCG");
	}

	@Test
	public void testRemoveLeftChildLeafNode() {
		String[] keys = { "A", "B", "C", "D", "E", "F", "G" };
		String[] vals = { "1", "2", "3", "4", "5", "6", "7" };
		CS232LinkedBinaryTree<String, String> t = new CS232LinkedBinaryTree<String, String>(
				keys, vals);

		String s = t.remove("D");
		assertEquals("Removed incorrect value", "4", s);
		assertEquals("Incorrect size.", 6, t.size());

		checkTreeStructure(t.root);
		checkTreeKeysInOrder(t.root, "BEAFCG");
	}

	@Test
	public void testRemoveRightChildLeafNode() {
		String[] keys = { "A", "B", "C", "D", "E", "F", "G" };
		String[] vals = { "1", "2", "3", "4", "5", "6", "7" };
		CS232LinkedBinaryTree<String, String> t = new CS232LinkedBinaryTree<String, String>(
				keys, vals);

		String s = t.remove("E");
		assertEquals("Removed incorrect value", "5", s);
		assertEquals("Incorrect size.", 6, t.size());

		checkTreeStructure(t.root);
		checkTreeKeysInOrder(t.root, "DBAFCG");
	}

	@Test
	public void testRemoveInternalLeftChildNode() {
		String[] keys = { "A", "B", "C", "D", "E", "F", "G" };
		String[] vals = { "1", "2", "3", "4", "5", "6", "7" };
		CS232LinkedBinaryTree<String, String> t = new CS232LinkedBinaryTree<String, String>(
				keys, vals);

		String s = t.remove("B");
		assertEquals("Removed incorrect value", "2", s);
		assertEquals("Incorrect size.", 6, t.size());

		checkTreeStructure(t.root);
		checkTreeKeysInOrder(t.root, "DEAFCG");
	}

	@Test
	public void testRemoveInternalRightChildNode() {
		String[] keys = { "A", "B", "C", "D", "E", "F", "G" };
		String[] vals = { "1", "2", "3", "4", "5", "6", "7" };
		CS232LinkedBinaryTree<String, String> t = new CS232LinkedBinaryTree<String, String>(
				keys, vals);

		String s = t.remove("C");
		assertEquals("Removed incorrect value", "3", s);
		assertEquals("Incorrect size.", 6, t.size());

		checkTreeStructure(t.root);
		checkTreeKeysInOrder(t.root, "DBEAFG");
	}

	@Test
	public void testRemoveRootNode() {
		String[] keys = { "A", "B", "C", "D", "E", "F", "G" };
		String[] vals = { "1", "2", "3", "4", "5", "6", "7" };
		CS232LinkedBinaryTree<String, String> t = new CS232LinkedBinaryTree<String, String>(
				keys, vals);

		String s = t.remove("A");
		assertEquals("Removed incorrect value", "1", s);
		assertEquals("Incorrect size.", 6, t.size());

		checkTreeStructure(t.root);
		checkTreeKeysInOrder(t.root, "DBEGFC");
	}
	
	@Test
	public void testRemoveRootNodeOneNodeTree() {
		CS232LinkedBinaryTree<String, String> t = new CS232LinkedBinaryTree<String, String>(
				"A", "1");

		String s = t.remove("A");
		assertEquals("Removed incorrect value", "1", s);
		assertEquals("Incorrect size.", 0, t.size());
		assertNull("root should be null in empty tree", t.root);
	}

	@Test
	public void testRemoveMultipleNodes() {
		String[] keys = { "A", "B", "C", "D", "E", "F", "G" };
		String[] vals = { "1", "2", "3", "4", "5", "6", "7" };
		CS232LinkedBinaryTree<String, String> t = new CS232LinkedBinaryTree<String, String>(
				keys, vals);

		String s1 = t.remove("B");
		assertEquals("Removed incorrect value", "2", s1);
		assertEquals("Incorrect size.", 6, t.size());
		checkTreeStructure(t.root);
		checkTreeKeysInOrder(t.root, "DEAFCG");

		String s2 = t.remove("E");
		assertEquals("Removed incorrect value", "5", s2);
		assertEquals("Incorrect size.", 5, t.size());
		checkTreeStructure(t.root);
		checkTreeKeysInOrder(t.root, "DAFCG");

		String s3 = t.remove("F");
		assertEquals("Removed incorrect value", "6", s3);
		assertEquals("Incorrect size.", 4, t.size());
		checkTreeStructure(t.root);
		checkTreeKeysInOrder(t.root, "DACG");

		String s4 = t.remove("A");
		assertEquals("Removed incorrect value", "1", s4);
		assertEquals("Incorrect size.", 3, t.size());
		checkTreeStructure(t.root);
		checkTreeKeysInOrder(t.root, "DGC");
	}

	@Test
	public void testInOrder() {
		String[] keys = { "A", "B", "C", "D", "E", "F", "G", "H", "I", "J" };
		String[] vals = { "1", "2", "3", "4", "5", "6", "7", "8", "9", "0" };
		CS232LinkedBinaryTree<String, String> t = new CS232LinkedBinaryTree<String, String>(
				keys, vals);
		t.remove("H");

		KeyStringVisitor<String, String> v = new KeyStringVisitor<String, String>();
		t.visitInOrder(v);
		String ks = v.getKeys();

		assertEquals("Incorrect inorder traversal", "D4I9B2J0E5A1F6C3G7", ks);
	}

	@Test
	public void testPreOrder() {
		String[] keys = { "A", "B", "C", "D", "E", "F", "G", "H", "I", "J" };
		String[] vals = { "1", "2", "3", "4", "5", "6", "7", "8", "9", "0" };
		CS232LinkedBinaryTree<String, String> t = new CS232LinkedBinaryTree<String, String>(
				keys, vals);
		t.remove("H");

		KeyStringVisitor<String, String> v = new KeyStringVisitor<String, String>();
		t.visitPreOrder(v);
		String ks = v.getKeys();

		assertEquals("Incorrect preorder traversal", "A1B2D4I9E5J0C3F6G7", ks);
	}

	@Test
	public void testPostOrder() {
		String[] keys = { "A", "B", "C", "D", "E", "F", "G", "H", "I", "J" };
		String[] vals = { "1", "2", "3", "4", "5", "6", "7", "8", "9", "0" };
		CS232LinkedBinaryTree<String, String> t = new CS232LinkedBinaryTree<String, String>(
				keys, vals);
		t.remove("H");

		KeyStringVisitor<String, String> v = new KeyStringVisitor<String, String>();
		t.visitPostOrder(v);
		String ks = v.getKeys();

		assertEquals("Incorrect preorder traversal", "I9D4J0E5B2F6G7C3A1", ks);
	}

	@Test
	public void testLevelOrder() {
		String[] keys = { "A", "B", "C", "D", "E", "F", "G", "H", "I", "J" };
		String[] vals = { "1", "2", "3", "4", "5", "6", "7", "8", "9", "0" };
		CS232LinkedBinaryTree<String, String> t = new CS232LinkedBinaryTree<String, String>(
				keys, vals);
		t.remove("H");

		KeyStringVisitor<String, String> v = new KeyStringVisitor<String, String>();
		t.visitLevelOrder(v);
		String ks = v.getKeys();

		assertEquals("Incorrect levelorder traversal", "A1B2C3D4E5F6G7I9J0", ks);
	}

	private static class KeyStringVisitor<K, V> implements CS232Visitor<K, V> {

		private String list;

		public KeyStringVisitor() {
			list = "";
		}

		public void visit(K key, V value) {
			list = list + key + value;
		}

		public String getKeys() {
			return list;
		}
	}
}
