/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package DataGen;

import java.awt.*;                                   // For Graphics
import java.awt.geom.*;

/**
 * Clase que define una región (prototipo) de un concepto.
 *
 * @author Danel
 */
public abstract class PrototypeShape {

  protected Point2D.Double start;                           // Start point for shape
  protected Dimension dim;
  public GeneralPath p;                                 // shape path

  protected PrototypeShape(double x, double y, int w, int h) {
    start = new Point2D.Double(x, y);                      // store start point
    dim = new Dimension(w, h);
  }

  Shape atLocation(double x, double y) {
    start.setLocation(x, y);                              // Store new start
    p.reset();                                            // Erase current path
    createShape();                                         // create new path
    return p;                                             // Return the path
  }

  protected abstract void createShape();

  public boolean contains(Point2D pt) {
    return p.contains(pt);
  }
}
