/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package DataGen;

import weka.core.DenseInstance;
import weka.core.Instance;
import weka.core.Instances;

/**
 *
 * @author Danel
 */
public abstract class ProblemBuilder {

  protected ConceptView cv;
  protected DataStructure structure;

  public ProblemBuilder(ConceptView conceptView) {
    cv = conceptView;
  }

  public abstract Instances getProblem(String name, int size);

  protected Instance getInstance(double attr1, double attr2, String className) {
    double[] dataCode = new double[3];
    dataCode[0] = attr1;
    dataCode[1] = attr2;
    dataCode[2] = structure.classVals.indexOf(className);
    return new DenseInstance(1, dataCode);
  }

}
