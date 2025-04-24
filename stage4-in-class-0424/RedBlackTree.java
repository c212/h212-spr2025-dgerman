
import java.util.*;

public class RedBlackTree {
   Node root;
   static final int BLACK = 1, RED = 0;
   public RedBlackTree() { root = null; }
   public void add(Comparable obj) {
      Node newNode = new Node(); newNode.data = obj;
      newNode.left = null; newNode.right = null;
      if (root == null) { root = newNode; }
      else { root.addNode(newNode); }
      fixAfterAdd(newNode);
   }
   static class Node {
      public void accept(Visitor v) { v.visitNode(this); }
      public String toString() { return (this.color == BLACK) ? (" " + this.data + " ") : ("[" + this.data + "]"); }
      public Comparable data;
      public Node left, right, parent;
      public int color;
      public Node() {}
      public void setLeftChild(Node child) { left = child; if (child != null) { child.parent = this; } }
      public void setRightChild(Node child) { right = child; if (child != null) { child.parent = this; } }
      public void addNode(Node newNode) {
         int comp = newNode.data.compareTo(data);
         if (comp < 0)      if (left  == null) { left  = newNode; left.parent  = this; } else left.addNode(newNode);
         else if (comp > 0) if (right == null) { right = newNode; right.parent = this; } else right.addNode(newNode);
      }
   }
   private void replaceWith(Node toBeReplaced, Node replacement) {
      if (toBeReplaced.parent == null) { replacement.parent = null; root = replacement; }
      else if (toBeReplaced == toBeReplaced.parent.left) toBeReplaced.parent.setLeftChild(replacement);
      else toBeReplaced.parent.setRightChild(replacement);
   }
   private void fixAfterAdd(Node newNode) {
      if (newNode.parent == null) newNode.color = BLACK;
      else { newNode.color = RED; if (newNode.parent.color == RED) { fixDoubleRed(newNode); } }
   }
   private void fixDoubleRed(Node child) {
      Node parent = child.parent, grandParent = parent.parent;
      if (grandParent == null) { parent.color = BLACK; return; }
      Node n1, n2, n3, t1, t2, t3, t4;
      if (parent == grandParent.left) {
         n3 = grandParent; t4 = grandParent.right;
         if (child == parent.left) { n1 = child; n2 = parent; t1 = child.left; t2 = child.right; t3 = parent.right; }
         else { n1 = parent; n2 = child; t1 = parent.left; t2 = child.left; t3 = child.right; }
      } else {
         n1 = grandParent; t1 = grandParent.left;
         if (child == parent.left) { n2 = child; n3 = parent; t2 = child.left; t3 = child.right; t4 = parent.right; }
         else { n2 = parent; n3 = child; t2 = parent.left; t3 = child.left; t4 = child.right; }
      }
      replaceWith(grandParent, n2);
      n1.setLeftChild(t1); n1.setRightChild(t2);
      n2.setLeftChild(n1); n2.setRightChild(n3);
      n3.setLeftChild(t3); n3.setRightChild(t4);
      n2.color = grandParent.color - 1;
      n1.color = BLACK; n3.color = BLACK;
      if (n2 == root) root.color = BLACK;
      else if (n2.color == RED && n2.parent.color == RED) fixDoubleRed(n2);
   }
} // --