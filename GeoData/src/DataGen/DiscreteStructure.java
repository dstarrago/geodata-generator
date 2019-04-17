/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package DataGen;

import weka.core.FastVector;
import weka.core.Attribute;

/**
 *
 * @author Danel
 */
public class DiscreteStructure extends DataStructure {

  /**
   *  Vectors holding data structures
   */
  public FastVector attr1Vals = new FastVector();
  public FastVector attr2Vals = new FastVector();

  public DiscreteStructure(ConceptView conceptView) {
    super (conceptView);
    for (int i = 0; i < conceptView.width(); i++)
      attr1Vals.addElement(String.valueOf(i));
    for (int i = 0; i < conceptView.height(); i++)
      attr2Vals.addElement(String.valueOf(i));
    attrs.addElement(new Attribute("attr1", attr1Vals));
    attrs.addElement(new Attribute("attr2", attr2Vals));
    attrs.addElement(new Attribute("class", classVals));
  }

}
