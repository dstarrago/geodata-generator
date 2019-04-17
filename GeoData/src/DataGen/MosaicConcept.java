/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package DataGen;

import java.awt.*;                                   // For Graphics
import java.awt.geom.*;

/**
 *
 * @author Danel
 */
public class MosaicConcept extends Concept {

  private ParametricMosaicShape mosaic;
  public static String POS = "POS";
  public static String NEG = "NEG";

  public MosaicConcept(int type, int width, int height, int repX, int repY) {
    super ("Mosaic", width, height);
    mosaic = new ParametricMosaicShape(type, 0, 0, width, height, repX, repY);
  }

  public String getClass(double x, double y) {
    if (mosaic.contains(new Point2D.Double(x, y))) return POS;
    return NEG;
  }

  public void draw(Graphics g, int x, int y) {
    Graphics2D g2D = (Graphics2D)g;                // Get a Java 2D device context
    g2D.setPaint(Color.LIGHT_GRAY);
    g2D.fill(mosaic.atLocation(x, y));
  }

  public String className(int classIndex) {
    if (classIndex == 1) return POS;
    return NEG;
  }

 public int roundness() {
   return mosaic.type();
 }

 public int repX() {
   return mosaic.repX();
 }

 public int repY() {
   return mosaic.repY();
 }
 
}
