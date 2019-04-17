/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package DataGen;

import weka.core.FastVector;

/**
 *
 * @author Danel
 */
public class DataStructure {

  ConceptView conceptView;
  /**
   *  Vectors holding data structures
   */
  public FastVector attrs = new FastVector();
  public FastVector classVals = new FastVector();

  public DataStructure(ConceptView conceptView) {
    this.conceptView = conceptView;
    classVals.addElement(conceptView.concept().className(1));
    classVals.addElement(conceptView.concept().className(2));
  }

}
