package lab1.task2.datastructures;

import java.util.ArrayList;
import java.util.List;

public class SplayTree {

  public static class Node {
    int data;
    Node left;
    Node right;
    Node parent;

    Node(int data, Node parent) {
      this.data = data;
      this.parent = parent;
    }
  }

  private Node root;

  public Node getRoot() {
    return root;
  }

    /* =======================================================
                            SPLAY
       ======================================================= */

  private void splay(Node x) {
    while (x.parent != null) {

      if (x.parent.parent == null) {
        // Zig / Zag
        if (x == x.parent.left) {
          rightRotate(x.parent);
        } else {
          leftRotate(x.parent);
        }
      } else {
        Node parent = x.parent;
        Node grand = parent.parent;

        // Zig-Zig
        if (x == parent.left && parent == grand.left) {
          rightRotate(grand);
          rightRotate(parent);

          // Zag-Zag
        } else if (x == parent.right && parent == grand.right) {
          leftRotate(grand);
          leftRotate(parent);

          // Zig-Zag
        } else if (x == parent.left && parent == grand.right) {
          rightRotate(parent);
          leftRotate(grand);

          // Zag-Zig
        } else {
          leftRotate(parent);
          rightRotate(grand);
        }
      }
    }

    root = x;
  }

    /* =======================================================
                            ROTATIONS
       ======================================================= */

  private boolean rotate(Node p, Node top) {
    if (top == null) return true;

    top.parent = p.parent;

    if (p.parent != null) {
      if (p == p.parent.left) {
        p.parent.left = top;
      } else {
        p.parent.right = top;
      }
    }
    return false;
  }

  private void leftRotate(Node p) {
    Node top = p.right;
    if (rotate(p, top)) return;

    p.right = top.left;
    if (p.right != null) {
      p.right.parent = p;
    }

    top.left = p;
    p.parent = top;
  }

  private void rightRotate(Node p) {
    Node top = p.left;
    if (rotate(p, top)) return;

    p.left = top.right;
    if (p.left != null) {
      p.left.parent = p;
    }

    top.right = p;
    p.parent = top;
  }

    /* =======================================================
                            INSERT
       ======================================================= */

  public void insert(int data) {
    if (root == null) {
      root = new Node(data, null);
      return;
    }

    Node p = root;
    Node parent = null;

    while (true) {
      parent = p;

      if (data < p.data) {
        p = p.left;
        if (p == null) {
          p = new Node(data, parent);
          parent.left = p;
          break;
        }
      } else {
        p = p.right;
        if (p == null) {
          p = new Node(data, parent);
          parent.right = p;
          break;
        }
      }
    }

    splay(p);
  }

    /* =======================================================
                            SEARCH
       ======================================================= */

  public Node search(int data) {
    Node p = root;

    while (p != null) {
      if (data < p.data) {
        p = p.left;
      } else if (data > p.data) {
        p = p.right;
      } else {
        splay(p);
        return p;
      }
    }

    return null;
  }

    /* =======================================================
                            DELETE
       ======================================================= */

  public void remove(int data) {
    if (root == null) return;

    Node p = root;

    while (true) {
      if (data < p.data && p.left != null) {
        p = p.left;
      } else if (data > p.data && p.right != null) {
        p = p.right;
      } else {
        break;
      }
    }

    splay(p);

    if (p.data != data) return;

    Node leftSub = p.left;
    Node rightSub = p.right;

    if (leftSub != null) leftSub.parent = null;
    if (rightSub != null) rightSub.parent = null;

    if (leftSub != null && rightSub != null) {
      root = rightSub;
      splay(rightSub);

      root.left = leftSub;
      leftSub.parent = root;

    } else if (leftSub == null) {
      root = rightSub;

    } else {
      root = leftSub;
    }
  }

    /* =======================================================
                        VALIDATION
       ======================================================= */

  public boolean isValidSplayTree() {
    return isValidBST(root, Integer.MIN_VALUE, Integer.MAX_VALUE)
      && checkParents(root, null);
  }

  private boolean isValidBST(Node node, int min, int max) {
    if (node == null) return true;

    if (node.data <= min || node.data >= max)
      return false;

    return isValidBST(node.left, min, node.data)
      && isValidBST(node.right, node.data, max);
  }

  private boolean checkParents(Node node, Node expectedParent) {
    if (node == null) return true;

    if (node.parent != expectedParent)
      return false;

    return checkParents(node.left, node)
      && checkParents(node.right, node);
  }

    /* =======================================================
                        PRINT TREE
       ======================================================= */

  public void printTree() {
    printTree(root, "", true);
  }

  private void printTree(Node node, String indent, boolean last) {
    if (node != null) {
      System.out.print(indent);

      if (last) {
        System.out.print("R----");
        indent += "     ";
      } else {
        System.out.print("L----");
        indent += "|    ";
      }

      System.out.println(node.data);

      printTree(node.left, indent, false);
      printTree(node.right, indent, true);
    }
  }

    /* =======================================================
                        PREORDER
       ======================================================= */

  public List<Node> inOrder() {
    List<Node> result = new ArrayList<>();
    inOrder(root, result);
    return result;
  }

  private void inOrder(Node node, List<Node> list) {
    if (node == null) {
      return;
    }

    inOrder(node.left, list);
    list.add(node);
    inOrder(node.right, list);
  }

  public int countNodes() {
    return countNodes(root);
  }

  private int countNodes(Node node) {
    if (node == null) {
      return 0;
    }
    return 1 + countNodes(node.left) + countNodes(node.right);
  }
}
