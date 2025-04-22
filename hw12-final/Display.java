import java.awt.*;
import javax.swing.*;
import java.util.*;
import java.awt.event.*; // 

class Display extends JComponent implements MouseListener, MouseMotionListener, ActionListener {
  public static int size = 0;
  public void actionPerformed(ActionEvent e) { // [2] 
    System.out.println( "Kihrun: " + e.getActionCommand() ); // [3] 
    // System.out.println( current ); 
    // delete current if not null from circles
    if (e.getActionCommand().equals("Delete")) 
      circles.remove(current);
    else { // you can test for Add too 
      int x = Integer.parseInt(two.x.getText()); 
      int y = Integer.parseInt(two.y.getText()); 
      int r = Integer.parseInt(two.r.getText()); 
      circles.add(new Circle( x, // (int) (Math.random() * 300 + 20),
                              y, // (int) (Math.random() * 300 + 20),
                              r, // (int) (Math.random() *  30 + 30),
                              new Color( (float) (Math.random() * 0.4 + 0.6),
                                         (float) (Math.random() * 0.4 + 0.6),
                                         (float) (Math.random() * 0.4 + 0.6)),
                              ++Display.size));      
    } 
    // System.out.println( circles.size() );
    this.repaint();
  }

  public void mouseMoved   (MouseEvent e) { }
  public void mouseDragged(MouseEvent e) {
    // System.out.println( e.getX() + ", " + e.getY() );
    if (current != null) {
      this.current.moveTo( e.getX(), e.getY() );
      repaint();
    }
  }
  public void mouseEntered (MouseEvent e) { }
  public void mouseExited  (MouseEvent e) { }
  Circle current;
  public void mousePressed (MouseEvent e) {
    System.out.println( "Mouse pressed..." );
    int x = e.getX(), y = e.getY();
    for (Circle r : this.circles) {
      if (r.contains(x, y)) {
        this.current = r;
        break;
      }
    }
    System.out.println( current );
  }
  public void mouseReleased(MouseEvent e) { }
  public void mouseClicked (MouseEvent e) { }
  ArrayList<Circle> circles = new ArrayList<Circle>();
  int count = 0; 
  Two two; 
  public Display(Two two) {
    this.two = two;
    for (int i = 0; i < 10; i++) {
      this.circles.add(new Circle( (int) (Math.random() * 300 + 20),
                                   (int) (Math.random() * 300 + 20),
                                   (int) (Math.random() *  30 + 30),
                                   new Color( (float) (Math.random() * 0.4 + 0.6),
                                              (float) (Math.random() * 0.4 + 0.6),
                                              (float) (Math.random() * 0.4 + 0.6)),
                                   ++Display.size ));
    }
    this.addMouseListener(this);
    this.addMouseMotionListener(this);
  }
  public void paintComponent(Graphics g) {
    for (Circle c : circles) {
      c.draw(g);
    }
  }
}
