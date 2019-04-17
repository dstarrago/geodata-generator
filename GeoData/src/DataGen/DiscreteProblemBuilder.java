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
public class DiscreteProblemBuilder extends ProblemBuilder {

  public DiscreteProblemBuilder(ConceptView conceptView) {
    super (conceptView);
    structure = new DiscreteStructure(cv);
  }

  public Instances getProblem(String name, int size) {
    Instances data = new Instances(name, structure.attrs, 1);
    for (int i = 0; i < size; i++) {
      int x = (int)(Math.random() * cv.concept().width);
      int y = (int)(Math.random() * cv.concept().height);
      data.add(getInstance((int)cv.attrX(x), (int)cv.attrY(y), cv.concept().getClass(x, y)));
    }
    return data;
  }

}
