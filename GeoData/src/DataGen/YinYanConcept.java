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
public class YinYanConcept extends Concept {

  private YinShape yin;
  private YanShape yan;
  public static String YIN = "YIN";
  public static String YAN = "YAN";
  public static String NON = "NON";

  public YinYanConcept(int width, int height) {
    super ("YinYan", width, height);
    yin = new YinShape(0, 0, width, height);
    yan = new YanShape(0, 0, width, height);
  }

  public void draw(Graphics g, int x, int y) {
    Graphics2D g2D = (Graphics2D)g;                // Get a Java 2D device context
    g2D.setPaint(Color.LIGHT_GRAY);
    g2D.fill(yin.atLocation(x, y));
    //g2D.setPaint(Color.ORANGE);
    //g2D.fill(yan.atLocation(x, y));
  }
  
  public String getClass(double x, double y) {
    if (yin.contains(new Point2D.Double(x, y))) return YIN;
    if (yan.contains(new Point2D.Double(x, y))) return YAN;
    return NON;
  }

  public String className(int classIndex) {
    if (classIndex == 1) return YIN;
    if (classIndex == 2) return YAN;
    return NON;
  }

}
