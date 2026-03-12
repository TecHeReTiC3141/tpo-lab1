package lab1.task2.datastructures;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class SplayTreeTest {

  private SplayTree tree;

  @BeforeEach
  public void setUp() {
    tree = new SplayTree();
  }

  @Test
  @DisplayName("Insert: new node becomes root (splay)")
  public void testInsertMakesNodeRoot() {
    tree.insert(10);
    assertEquals(10, tree.getRoot().data);

    tree.insert(20);
    assertEquals(20, tree.getRoot().data);

    tree.insert(5);
    assertEquals(5, tree.getRoot().data);

    assertTrue(tree.isValidSplayTree());
  }

  @Test
  @DisplayName("Insert + inorder correctness")
  public void testInsertAndInOrder() {
    tree.insert(10);
    tree.insert(20);
    tree.insert(5);
    tree.insert(15);

    List<SplayTree.Node> nodes = tree.inOrder();
    List<Integer> values = nodes.stream()
      .map(n -> n.data)
      .toList();

    assertEquals(List.of(5, 10, 15, 20), values);
    assertTrue(tree.isValidSplayTree());
  }

  @Test
  @DisplayName("Insert + inorder correctness (covers p.left != null in rightRotate)")
  public void testInsertAndInOrderRightRotateBranch() {
    tree.insert(10);
    tree.insert(5);
    tree.insert(20);
    tree.insert(7);

    List<SplayTree.Node> nodes = tree.inOrder();
    List<Integer> values = nodes.stream()
      .map(n -> n.data)
      .toList();

    assertEquals(List.of(5, 7, 10, 20), values);
    assertTrue(tree.isValidSplayTree());
  }

  @Test
  @DisplayName("Search splays node to root")
  public void testSearchSplaysToRoot() {
    tree.insert(10);
    tree.insert(20);
    tree.insert(5);

    SplayTree.Node node = tree.search(10);

    assertNotNull(node);
    assertEquals(10, tree.getRoot().data);
    assertTrue(tree.isValidSplayTree());
  }

  @Test
  @DisplayName("Verify rightRotate handles case when top.right != null")
  public void testRightRotateCondition() {

    tree.insert(30);
    tree.insert(20);
    tree.insert(25);

    tree.search(25);

    assertTrue(tree.isValidSplayTree());
  }

  @Test
  @DisplayName("Search not found")
  public void testSearchNotFound() {
    tree.insert(10);
    assertNull(tree.search(100));
    assertTrue(tree.isValidSplayTree());
  }

  @Test
  @DisplayName("Remove leaf")
  public void testRemoveLeaf() {
    tree.insert(10);
    tree.insert(5);

    tree.remove(5);

    assertNull(tree.search(5));
    assertTrue(tree.isValidSplayTree());
  }

  @Test
  @DisplayName("Remove node with one child")
  public void testRemoveOneChild() {
    tree.insert(10);
    tree.insert(5);
    tree.insert(2);

    tree.remove(5);

    assertNull(tree.search(5));
    assertTrue(tree.isValidSplayTree());
  }


  @Test
  @DisplayName("Remove node with left child")
  public void testRemoveWithLeftChild() {
    tree.insert(30);
    tree.insert(20);
    tree.insert(10);
    tree.insert(50);
    tree.insert(60);
    tree.insert(40);
    tree.insert(35);

    tree.printTree();
    tree.search(30);

    tree.remove(60);

    assertNull(tree.search(60));
    assertEquals(6, tree.countNodes());
    assertTrue(tree.isValidSplayTree());

    tree.remove(30);

    assertNull(tree.search(30));
    assertEquals(5, tree.countNodes());
    assertTrue(tree.isValidSplayTree());

    List<Integer> values = tree.inOrder()
      .stream()
      .map(n -> n.data)
      .toList();

    assertEquals(List.of(10, 20, 35, 40, 50), values);
  }

  @Test
  @DisplayName("Remove node with two children")
  public void testRemoveTwoChildren() {
    tree.insert(10);
    tree.insert(5);
    tree.insert(20);
    tree.insert(15);
    tree.insert(25);

    tree.remove(20);

    assertNull(tree.search(20));
    assertTrue(tree.isValidSplayTree());
  }

  @Test
  @DisplayName("Remove root")
  public void testRemoveRoot() {
    tree.insert(10);
    tree.remove(10);

    assertNull(tree.getRoot());
  }

  @Test
  @DisplayName("Remove not found")
  public void testRemoveNotFound() {
    tree.insert(10);
    tree.remove(100);
    assertTrue(tree.isValidSplayTree());
  }

  @Test
  @DisplayName("Remove from empty tree does nothing and NOT throws anything")
  public void testRemoveFromEmptyTree() {

    assertNull(tree.getRoot());
    assertEquals(0, tree.countNodes());

    tree.remove(100);

    assertNull(tree.getRoot());
    assertEquals(0, tree.countNodes());
  }

  @Test
  @DisplayName("Zig case")
  public void testZigCase() {
    tree.insert(10);
    tree.insert(5);

    tree.search(5);
    assertEquals(5, tree.getRoot().data);
    assertTrue(tree.isValidSplayTree());
  }

  @Test
  @DisplayName("Zig-Zig case")
  public void testZigZigCase() {
    tree.insert(30);
    tree.insert(20);
    tree.insert(10);

    tree.search(10);
    assertEquals(10, tree.getRoot().data);
    assertTrue(tree.isValidSplayTree());
  }

  @Test
  @DisplayName("Zig-Zag case")
  public void testZigZagCase() {
    tree.insert(30);
    tree.insert(10);
    tree.insert(20);

    tree.search(20);
    assertEquals(20, tree.getRoot().data);
    assertTrue(tree.isValidSplayTree());
  }

  @Test
  @DisplayName("Zag case")
  public void testZagCase() {
    tree.insert(10);
    tree.insert(20);

    tree.search(20);

    assertEquals(20, tree.getRoot().data);
    assertTrue(tree.isValidSplayTree());
  }

  @Test
  @DisplayName("Zag-Zag case")
  public void testZagZagCase() {
    tree.insert(10);
    tree.insert(20);
    tree.insert(30);

    tree.search(30);

    assertEquals(30, tree.getRoot().data);
    assertTrue(tree.isValidSplayTree());
  }

  @Test
  @DisplayName("Zag-Zig case")
  public void testZagZigCase() {
    tree.insert(10);
    tree.insert(30);
    tree.insert(20);

    tree.search(20);

    assertEquals(20, tree.getRoot().data);
    assertTrue(tree.isValidSplayTree());
  }

  /* ================= COUNT ================= */

  @Test
  @DisplayName("Count nodes")
  public void testCountNodes() {
    assertEquals(0, tree.countNodes());

    tree.insert(10);
    tree.insert(20);

    assertEquals(2, tree.countNodes());
  }

  @Test
  @DisplayName("Tree validation check")
  public void testIsValidSplayTree() {
    tree.insert(10);
    tree.insert(20);
    tree.insert(5);

    SplayTree.Node root = tree.getRoot();
    assertNotNull(root.right);

    root.right.data = -100; // break BST property

    assertFalse(tree.isValidSplayTree());
  }

  /* ================= PRINT ================= */

  @Test
  @DisplayName("printTree works")
  public void testPrintTree() {
    ByteArrayOutputStream out = new ByteArrayOutputStream();
    System.setOut(new PrintStream(out));

    tree.insert(10);
    tree.insert(20);
    tree.insert(5);

    tree.printTree();

    String output = out.toString();
    assertTrue(output.contains("10") ||
      output.contains("20") ||
      output.contains("5"));

    assertTrue(tree.isValidSplayTree());
  }

  @Test
  @DisplayName("Many operations in a row")
  public void testManyOperations() {
    for (int i = 0; i < 50; i++) {
      tree.insert(i);
      assertTrue(tree.isValidSplayTree());
    }

    for (int i = 0; i < 50; i++) {
      tree.search(i);
      assertTrue(tree.isValidSplayTree());
    }

    for (int i = 0; i < 50; i++) {
      tree.remove(i);
      assertTrue(tree.isValidSplayTree());
    }

    assertEquals(0, tree.countNodes());
  }

  @Test
  @DisplayName("Validation fails: BST property violated")
  public void testInvalidBST() {

    tree.insert(20);
    tree.insert(10);
    tree.insert(30);

    SplayTree.Node root = tree.getRoot();

    // Break BST rule: left child > root
    root.left.data = 100;

    assertFalse(tree.isValidSplayTree());
  }

  @Test
  @DisplayName("Validation fails: wrong parent pointer")
  public void testInvalidParentPointer() {

    tree.insert(20);
    tree.insert(10);
    tree.insert(30);

    SplayTree.Node root = tree.getRoot();

    // Corrupt parent pointer
    root.left.parent = null;

    assertFalse(tree.isValidSplayTree());
  }
}
