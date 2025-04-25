
public class Testing {
   public static void main(String[] args) {

      RedBlackTree t = new RedBlackTree();
      RedBlackTree.Node a = new RedBlackTree.Node();
      RedBlackTree.Node b = new RedBlackTree.Node();
      RedBlackTree.Node c = new RedBlackTree.Node();
      RedBlackTree.Node d = new RedBlackTree.Node();
      RedBlackTree.Node e = new RedBlackTree.Node();
      RedBlackTree.Node f = new RedBlackTree.Node();
      f.data = 70; f.left = null; f.right = null; f.color = RedBlackTree.RED;   f.parent = e;
      e.data = 30; e.left = null; e.right =    f; e.color = RedBlackTree.BLACK; e.parent = c;
      d.data = 90; d.left = null; d.right = null; d.color = RedBlackTree.BLACK; d.parent = c;
      c.data = 80; c.left =    e; c.right =    d; c.color = RedBlackTree.RED;   c.parent = a;
      b.data = 10; b.left = null; b.right = null; b.color = RedBlackTree.BLACK; b.parent = a;
      a.data = 20; a.left =    b; a.right =    c; a.color = RedBlackTree.BLACK; a.parent = null;
      t.root = a;
      t.root.accept(new Painter()); 
      /* 
      System.out.println("Adding 40: ");
      t.add(40);
      t.root.accept(new Painter()); 
       */
   }
}