/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package DataGen;

import weka.core.Attribute;

/**
 *
 * @author Danel
 */
public class RealAttrStructure extends DataStructure {

  public RealAttrStructure(ConceptView conceptView) {
    super (conceptView);
    attrs.addElement(new Attribute("attr1"));
    attrs.addElement(new Attribute("attr2"));
    attrs.addElement(new Attribute("class", classVals));
  }

}
