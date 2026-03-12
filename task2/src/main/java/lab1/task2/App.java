package lab1.task2;

import lab1.task2.datastructures.SplayTree;

public class App {
  public static void main(String[] args) {
    SplayTree tree = new SplayTree();
    tree.insert(10);
    tree.insert(20);
    tree.insert(5);
    tree.insert(7);

    tree.printTree();

    System.out.println("Is valid Splay Tree: " + tree.isValidSplayTree());
  }
}
