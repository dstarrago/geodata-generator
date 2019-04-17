/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package DataGen;

import java.awt.geom.*;

/**
 *
 * @author Danel
 */
public class LinealShape extends PrototypeShape {

  public LinealShape(double x, double y, int w, int h) {
    super (x, y, w, h);
    createShape();
  }

  protected void createShape() {
    Point2D.Double point = start;
    p = new GeneralPath(GeneralPath.WIND_EVEN_ODD);
    p.moveTo(point.x + 6* dim.width / 7, point.y);
    p.lineTo(point.x, point.y);
    p.lineTo(point.x, point.y + dim.height);
    p.lineTo(point.x + dim.width / 7, point.y + dim.height);
    p.closePath();
  }
}
