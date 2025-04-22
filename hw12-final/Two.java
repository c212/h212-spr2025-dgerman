import java.awt.*;
import java.awt.event.*; 
import javax.swing.*;

class Two extends JFrame { 
  JTextField x = new JTextField(),
             y = new JTextField(),
             r = new JTextField();
  Two() {
    JLabel xL = new JLabel("x: "),
           yL = new JLabel("y: "),
           rL = new JLabel("r: ");
    x.setPreferredSize(new Dimension(40,20));
    y.setPreferredSize(new Dimension(40,20));
    r.setPreferredSize(new Dimension(40,20));
    JButton add = new JButton("Add"),
            delete = new JButton("Delete");
    JPanel panel = new JPanel();
    panel.add(xL);
    panel.add(x );
    panel.add(yL);
    panel.add(y ); 
    panel.add(rL);
    panel.add(r );
    panel.add(add);
    panel.add(delete);
    Display display = new Display(this);
    delete.addActionListener(display); // [1] 
    add.addActionListener(display); 
    this.setLayout(new BorderLayout());
    this.add(panel, BorderLayout.NORTH);
    this.add(display, BorderLayout.CENTER);
    this.setSize(400, 400);
    this.setVisible(true);
    this.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
  }
  public static void main(String[] args) {
    Two frame = new Two();
  }
}
