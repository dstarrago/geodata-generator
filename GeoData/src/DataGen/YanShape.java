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
public class YanShape extends PrototypeShape {

  public YanShape(double x, double y, int w, int h) {
    super (x, y, w, h);
    createShape();
  }

    protected void createShape() {
      Point2D.Double point = start;
      p = new GeneralPath(GeneralPath.WIND_EVEN_ODD);
      p.moveTo(point.x, point.y + dim.height / 2);
      p.curveTo(point.x, point.y + dim.height / 2,
              point.x, point.y,
              point.x + dim.width / 2, point.y);
      p.curveTo(point.x + dim.width / 2, point.y,
              point.x + dim.width, point.y,
              point.x + dim.width, point.y + dim.height / 2);

      p.curveTo(point.x + dim.width, point.y + dim.height / 2,
              point.x + dim.width, point.y + dim.height / 4,
              point.x + 3 * dim.width / 4, point.y + dim.height / 4);
      p.curveTo(point.x + 3 * dim.width / 4, point.y + dim.height / 4,
              point.x + dim.width / 2, point.y + dim.height / 4,
              point.x + dim.width / 2, point.y + dim.height / 2);

      p.curveTo(point.x + dim.width / 2, point.y + dim.height / 2,
              point.x + dim.width / 2, point.y + 3 * dim.height / 4,
              point.x + dim.width / 4, point.y + 3 * dim.height / 4);
      p.curveTo(point.x + dim.width / 4, point.y + 3 * dim.height / 4,
              point.x, point.y + 3 * dim.height / 4,
              point.x, point.y + dim.height / 2);
      p.closePath();

      GeneralPath p1 = new GeneralPath(GeneralPath.WIND_EVEN_ODD);
      p1.moveTo(point.x, point.y + dim.height / 2);
      p1.curveTo(point.x, point.y + dim.height / 2,
              point.x, point.y + dim.height,
              point.x + dim.width / 2, point.y + dim.height);
      p1.curveTo(point.x + dim.width / 2, point.y + dim.height,
              point.x + dim.width, point.y + dim.height,
              point.x + dim.width, point.y + dim.height / 2);
      p1.lineTo(point.x + dim.width, point.y + dim.height);
      p1.lineTo(point.x, point.y + dim.height);
      p1.closePath();

      p.append(new Ellipse2D.Double(point.x + 3 * dim.width / 16, point.y + 7 * dim.height / 16,
              dim.width / 8, dim.height / 8) , false);
      p.append(new Ellipse2D.Double(point.x + 11 * dim.width / 16, point.y + 7 * dim.height / 16,
              dim.width / 8, dim.height / 8) , false);
      p.append(p1, false);
    }
}
