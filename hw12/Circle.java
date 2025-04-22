import java.awt.*;

class Circle {
  int x, y, r, data;
  Color c;
  public Circle(int x, int y, int r, Color c, int data) {
    this.x = x;
    this.y = y;
    this.r = r; 
    this.c = c;
    this.data = data;
  }
  public void moveTo(int x, int y) {
    this.x = x;
    this.y = y;
  }
  public void draw(Graphics g) {
    g.setColor(c);
    g.fillOval(x - r, y - r, 2 * r, 2 * r);
    g.setColor(Color.BLACK);
    g.drawOval(x - r, y - r, 2 * r, 2 * r);
    g.drawString(data + "", x, y);
  }
  public boolean contains(int x, int y) {
    double d = Math.sqrt( Math.pow( this.x - x, 2 ) + Math.pow( this.y - y, 2 ) );
    return d <= r;
  }
  public String toString() {
      return "Circle " + data;
  }
}