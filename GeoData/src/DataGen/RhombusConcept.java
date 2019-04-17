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
public class RhombusConcept extends Concept {

  private RhombusShape rhombus;
  public static String POS = "POS";
  public static String NEG = "NEG";

  public RhombusConcept(int width, int height) {
    super ("Rombo", width, height);
    rhombus = new RhombusShape(0, 0, width, height);
  }

  public void draw(Graphics g, int x, int y) {
    Graphics2D g2D = (Graphics2D)g;                // Get a Java 2D device context
    g2D.setPaint(Color.LIGHT_GRAY);
    g2D.fill(rhombus.atLocation(x, y));
  }

  public String getClass(double x, double y) {
    if (rhombus.contains(new Point2D.Double(x, y))) return POS;
    return NEG;
  }

  public String className(int classIndex) {
    if (classIndex == 1) return POS;
    return NEG;
  }

}
