/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package DataGen;

import weka.core.Instances;

/**
 *
 * @author Danel
 */
public class ContinuousProblemBuilder extends ProblemBuilder {

  public ContinuousProblemBuilder(ConceptView conceptView) {
    super (conceptView);
    structure = new RealAttrStructure(cv);
  }
  
  public Instances getProblem(String name, int size) {
    Instances data = new Instances(name, structure.attrs, 1);
    for (int i = 0; i < size; i++) {
      double x = Math.random() * cv.concept().width;
      double y = Math.random() * cv.concept().height;
      data.add(getInstance(cv.attrX(x), cv.attrY(y), cv.concept().getClass(x, y)));
    }
    return data;
  }

}
