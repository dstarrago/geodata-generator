/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package DataGen;

import java.awt.*;                                   // For Graphics
import java.awt.geom.*;

/**
 * Define una región de concepto cuya forma depende de un parámetro (type).
 * El parámetro puede variar entre 0 y 5. Cuando el parámetro es cero la forma
 * del concepto es un rectángulo. Si el parámetro es uno, el rectángulo tendrá
 * esquinas ligeramente redondeadas. Parámetros mayores generarán rectángulos
 * con esquinas más redondeadas. Si el parámetro es cinco generará una elipse.
 *
 * @author Danel
 */
public class ParametricShape extends PrototypeShape {

  private int type;
  private Dimension stepDim;

  public ParametricShape(int type, double x, double y, int w, int h) {
    super (x, y, w, h);
    this.type = type;
    stepDim = new Dimension(w / 10, h / 10);
    createShape();
  }

    protected void createShape() {
      Point2D.Double point = start;
      p = new GeneralPath(GeneralPath.WIND_EVEN_ODD);
      p.moveTo(point.x + type * stepDim.width, point.y);
      p.lineTo(point.x + (10 - type) * stepDim.width, point.y);
      p.curveTo(point.x + (10 - type) * stepDim.width, point.y,
              point.x + dim.width, point.y,
              point.x + dim.width, point.y + type * stepDim.height);
      p.lineTo(point.x + dim.width, point.y + (10 - type) * stepDim.height);
      p.curveTo(point.x + dim.width, point.y + (10 - type) * stepDim.height,
              point.x + dim.width, point.y + dim.height,
              point.x + (10 - type) * stepDim.width, point.y + dim.height);
      p.lineTo(point.x + type * stepDim.width, point.y + dim.height);
      p.curveTo(point.x + type * stepDim.width, point.y + dim.height,
              point.x, point.y + dim.height,
              point.x, point.y + (10 - type) * stepDim.height);
      p.lineTo(point.x, point.y + type * stepDim.height);
      p.curveTo(point.x, point.y + type * stepDim.height,
              point.x, point.y,
              point.x + type * stepDim.width, point.y);
      p.closePath();
    }

  public int type() {
    return type;
  }
  
}
