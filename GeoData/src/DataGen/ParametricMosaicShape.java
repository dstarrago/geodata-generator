/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package DataGen;

import java.awt.*;                                   // For Graphics
import java.awt.geom.*;

/**
 * Define una región de concepto con aspecto de mosaico cuya forma depende de un
 * parámetro que puede variar entre 0 y 35. Se utiliza como motivo de repetición
 * la clase ParametricShape.
 * Si el parámetro de la clase ParametricMosaicShape está entre 0 y 5 se utiliza
 * este mismo para crear el motivo (ParametricShape), si no el motivo se crea con
 * parámetro 5 (una elipse) y se aumenta su tamaño en proporción al parámetro de
 * la clase ParametricMosaicShape. La forma del mosaico se obtiene de la unión
 * de los motivos y de la resta de sus intersecciones.
 *
 * @author Danel
 */
public class ParametricMosaicShape extends PrototypeShape {

  /**
   * Tipo de mosaico
   */
  private int type;
  /**
   * Cantidad de repeticiones horizontales del motivo
   */
  private int repX;
  /**
   * Cantidad de repeticiones verticales del motivo
   */
  private int repY;
  private Dimension mosaicDim;
  private Dimension mosaicStep;

  public ParametricMosaicShape(int type, int x, int y, int w, int h, int repX, int repY) {
    super (x, y, w, h);
    this.type = type;
    this.repX = repX;
    this.repY = repY;
    mosaicDim = new Dimension(w / repX, h / repY);
    if (type < 6)
      mosaicStep = new Dimension(0, 0);
    else
      mosaicStep = new Dimension((type - 5) * mosaicDim.width / 10, (type - 5) * mosaicDim.height / 10);
    createShape();
  }

  protected void createShape() {
    Point2D.Double point = start;
    ParametricShape dc;
    int mosaicType = (type < 6)? type: 5;
    p = new GeneralPath(GeneralPath.WIND_EVEN_ODD);
    double x, y;
    int count = 0;
    for (float i = 0; i < repX; i++, count++)
      for (float j = 0; j < repY; j++, count++) {
        if (count % 2 == 0)
        {
          x = point.x + i * mosaicDim.width - (i/(repX - 1)) * mosaicStep.width;
          y = point.y + j * mosaicDim.height - (j/(repY - 1)) * mosaicStep.height;
          dc = new ParametricShape(mosaicType, x, y ,
                  mosaicDim.width + mosaicStep.width, mosaicDim.height + mosaicStep.height);
          p.append(dc.p, true);
        }
      }
  }

  public int type() {
    return this.type;
  }

  public int repX() {
    return repX;
  }

  public int repY() {
    return repY;
  }

}
