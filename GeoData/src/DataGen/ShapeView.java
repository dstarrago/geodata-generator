/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package DataGen;

import javax.swing.*;
import java.awt.*;                                   // For Graphics
import javax.swing.event.MouseInputAdapter;
import java.awt.event.MouseEvent;

/**
 *
 * @author Danel
 */
public class ShapeView extends JComponent /*implements*/ /*Observer,*/ /*ProblemParams*/ {

  private ShapeMain theApp;
  private int leftMargin = 400;
  private int topMargin = 200;
  private int x, y;

  public ShapeView(ShapeMain theApp) {
    this.theApp = theApp;
    MouseHandler handler = new MouseHandler();    // Create the listener
    addMouseMotionListener(handler);         // as well as movement
  }

/*
    // Method called by Observable object when it changes
  public void update(Observable o, Object rectangle) {
    // Code to respond to changes in the model...
    repaint();
  }
*/
  @Override
  public void paint(Graphics g) {
    Graphics2D g2D = (Graphics2D)g;                // Get a Java 2D device context
    if (theApp != null && theApp.concept != null) {
      theApp.concept.draw(g, leftMargin, topMargin);
      theApp.cview.draw(g, leftMargin, topMargin);
      g2D.setPaint(Color.BLACK);
      g2D.drawString(theApp.concept.getClass(x, y), leftMargin, 20);
      g2D.drawString(String.valueOf(theApp.cview.attrX(x - leftMargin)), leftMargin, 30);
      g2D.drawString(String.valueOf(theApp.cview.attrY(y - topMargin)), leftMargin + 20, 30);
    }
  }

class MouseHandler extends MouseInputAdapter {

    @Override
  public void mouseMoved(MouseEvent e) {
    if (theApp != null && theApp.concept != null) {
      x = e.getX();
      y = e.getY();
      repaint();
    }
    }
}
/*
private void Problem(Graphics2D g2D, PrototypeShape dc, int posX, int posY) {
  g2D.setPaint(Color.WHITE);
  g2D.fillRect(posX, posY, boardWidth, boardHeight);
  g2D.setPaint(Color.LIGHT_GRAY);
  g2D.fill(dc.atLocation(posX, posY));
}
*/
}