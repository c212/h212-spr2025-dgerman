import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.awt.event.*;

public class Painter implements Visitor {
  public void visitNode(RedBlackTree.Node node) {
    JFrame f = new JFrame("Your RBT.");
    f.setVisible(true); 
    f.setSize(600, 200);    
    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    Circle root = this.parse(node, 300, 30, 300); 
    Screen s = new Screen(root); 
    f.add(s);
  }
  Circle parse(RedBlackTree.Node node, int x, int y, int dx) {
    Circle left, right; 
    if (node.left == null) left = null; 
    else left  = this.parse(node.left , x - dx/2, y + 30, dx/2);
    if (node.right == null) right = null; 
    else right = this.parse(node.right, x + dx/2, y + 30, dx/2);
    return new Circle(x, y, node.data, node.color, left, right);    
  }
}

class Screen extends JComponent implements MouseMotionListener, MouseListener {
  Circle current;
  public void mouseMoved(MouseEvent e) {

  }
  public void mouseDragged(MouseEvent e) { 
    // System.out.println("Lara D. (" + e.getX() + ", " + e.getY() + ")");  
    int x = e.getX(), y = e.getY();
    current.moveTo(x, y);
    repaint();
  }
  public void mouseEntered(MouseEvent e) { }
  public void mouseExited(MouseEvent e) { }
  public void mousePressed(MouseEvent e) { 
    if (current != null) return; 
    System.out.println(circles);
    int x = e.getX(), y = e.getY();
    for (Circle c: circles)
      if (c.contains(x, y))
        current = c;
  }
  public void mouseReleased(MouseEvent e) { 
    this.current = null;  
  }
  public void mouseClicked(MouseEvent e) { }
  Circle root; 
  ArrayList<Circle> circles = new ArrayList<Circle>();
  Screen(Circle root) {
    this.addMouseMotionListener(this);
    this.root = root;
    addTo(circles, root);
    // current = circles.get(0);
  }
  public void paintComponent(Graphics g) {
    for (Circle c : this.circles) {
      if (c.left  != null) g.drawLine(c.x, c.y, c.left.x , c.left.y );
      if (c.right != null) g.drawLine(c.x, c.y, c.right.x, c.right.y);
    }
    for (Circle c : this.circles)
      c.draw(g);
  }
  public void addTo(ArrayList<Circle> circles, Circle root) {
    if (root != null) {
      this.circles.add(root); 
      if (root.left != null) addTo(circles, root.left);
      if (root.right != null) addTo(circles, root.right);
    }
  }
}

class Circle {
  int x, y;
  Comparable data;
  int color; 
  int radius = 20; 
  Circle left, right;
  Circle(int x, int y, Comparable data, int c, Circle left, Circle right) {
    this.x = x;
    this.y = y;
    this.data = data;
    this.color = c;
    this.left = left; 
    this.right = right;
  }
  public void draw(Graphics g) {    
    if (this.color == RedBlackTree.RED) g.setColor(Color.RED);
    else g.setColor(Color.BLACK); 
    g.fillOval(x - radius, y - radius, 2 * radius, 2 * radius);
    g.setColor(Color.WHITE);
    g.drawOval(x - radius, y - radius, 2 * radius, 2 * radius);
    g.drawString(data.toString(), x - 7, y + 7);
  }
  public void moveTo(int x, int y) {
    this.x = x;
    this.y = y;
  }
  public boolean contains(int x, int y) {
      return Math.sqrt(Math.pow((this.x - x), 2) + Math.pow((this.y - y), 2)) <= this.radius ;
  }
}
