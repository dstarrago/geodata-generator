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
public class EllipseConcept extends Concept {


  private EllipseShape ellipse;
  public static String POS = "POS";
  public static String NEG = "NEG";

  public EllipseConcept(int width, int height) {
    super ("Elipse", width, height);
    ellipse = new EllipseShape(0, 0, width, height);
  }

  public void draw(Graphics g, int x, int y) {
    Graphics2D g2D = (Graphics2D)g;                // Get a Java 2D device context
    g2D.setPaint(Color.LIGHT_GRAY);
    g2D.fill(ellipse.atLocation(x, y));
  }

  public String getClass(double x, double y) {
    if (ellipse.contains(new Point2D.Double(x, y))) return POS;
    return NEG;
  }

  public String className(int classIndex) {
    if (classIndex == 1) return POS;
    return NEG;
  }

}
