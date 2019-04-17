/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package DataGen;

import java.awt.*;                                   // For Graphics

/**
 * Representa un concepto en dos dimensiones, por ejemplo, un dibujo.
 * @author Danel
 */
public abstract class Concept {

  /**
   * Dimensiones del dominio
   */
  protected int width;
  protected int height;
  /**
   * Nombre del concepto
   */
  protected String name;

  public Concept(String name, int width, int height) {
    this.name = name;
    this.width = width;
    this.height = height;
  }

  public int getWidth() {
    return width;
  }

  public int getHeight() {
    return height;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  /**
   * Representa gráficamente el concepto
   * @param g Objeto gráfico donde se realizará el dibujo
   * @param x Coordenada x del lienzo donde se colocará el dibujo
   * @param y Coordenada y del lienzo donde se colocará el dibujo
   */
  abstract public void draw(Graphics g, int x, int y);

  /**
   * Devuelve la clase que corresponde al punto que se pasa como parámetro
   * @param x Coordenada x del punto
   * @param y Coordenada y del punto
   * @return
   */
  abstract public String getClass(double x, double y);

  /**
   * Devuelve el nombre de las clases
   * @param classIndex Número de la clase: 1 representa la primera clase, 2 la segunda.
   * @return el nombre de la clase número classIndex.
   */
  abstract public String className(int classIndex);
  
}
