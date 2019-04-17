/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package DataGen;

import java.awt.*;                                   // For Graphics

/**
 *
 * @author Danel
 */
public class ConceptView {

  private double shiftX;
  private double shiftY;
  private Concept concept;
  private int granularity;

  public ConceptView(Concept concept, int granularity) {
    this.concept = concept;
    this.granularity = granularity;
  }

  public ConceptView(Concept concept, int granularity, double GPshiftX, double GPshiftY) {
      this (concept, granularity);
      if (Math.abs(GPshiftX) < 1) this.shiftX = GPshiftX * granularity;
      if (Math.abs(GPshiftY) < 1) this.shiftY = GPshiftY * granularity;
  }
  
  public int width() {
    int w = concept.width / granularity;
    if (shiftX > 0) w++;
    return w;
  }

  public int height() {
    int h = concept.height / granularity;
    if (shiftY > 0) h++;
    return h;
  }

  public double attrX(double x) {
    return (x - shiftX) / granularity;
  }

  public double attrY(double y) {
    return (y - shiftY) / granularity;
  }

  public void draw(Graphics g, int x, int y) {
    Graphics2D g2D = (Graphics2D)g;                // Get a Java 2D device context
    g2D.setPaint(Color.cyan);
    for (int i = (int)shiftX; i <= concept.width; i += granularity) {
      g2D.drawLine(x + i, y, x + i, y + concept.height);
      for (int j = (int)shiftY; j <= concept.height; j += granularity) {
        g2D.drawLine(x, y + j, x + concept.width, y + j);
      }
    }
  }

  public Concept concept() {
    return concept;
  }

}
