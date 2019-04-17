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
public class EllipseShape extends PrototypeShape {

  public EllipseShape(double x, double y, int w, int h) {
    super (x, y, w, h);
    createShape();
  }

  protected void createShape() {
    Point2D.Double point = start;
    p = new GeneralPath(GeneralPath.WIND_EVEN_ODD);
    p.moveTo(point.x + dim.width / 2, point.y);
    p.curveTo(point.x + dim.width / 2, point.y,
              point.x  + dim.width / 5, point.y,
              point.x  + dim.width / 5, point.y + dim.height / 2);
    p.curveTo(point.x  + dim.width / 5, point.y + dim.height / 2,
              point.x  + dim.width / 5, point.y + dim.height,
              point.x + dim.width / 2, point.y + dim.height);
    p.curveTo(point.x + dim.width / 2, point.y + dim.height,
              point.x  + 4 * dim.width / 5, point.y + dim.height,
              point.x  + 4 * dim.width / 5, point.y + dim.height / 2);
    p.curveTo(point.x  + 4 * dim.width / 5, point.y + dim.height / 2,
              point.x  + 4 * dim.width / 5, point.y,
              point.x  + dim.width / 2, point.y);
    p.closePath();
  }

}
