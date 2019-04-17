/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package DataGen;

import java.io.*;
import weka.core.converters.ArffSaver;
import weka.core.Instances;
import java.util.Random;
import metalearningexperimenter.DataCharacterizer;


/**
 *
 * @author Danel
 */
public class DataGen {

  public final int problemDimension = 500;
  public final int problemSize = 5000;
  //public final int[] granularity = new int[] {1, 2, 4, 5, 10, 20, 25, 50};
  public final int[] granularity = new int[] {50};
  private Concept concept;

  private ArffSaver dataSaver;

  public DataGen() {
    //concept = new YinYanConcept(problemDimension, problemDimension);
    concept = new MosaicConcept(3, 500, 500, 4, 4);
    //concept = new EllipseConcept(500, 500);
    //concept = new RhombusConcept(500, 500);
    //concept = new SinuousConcept(500, 500);
    //concept = new LinealConcept(500, 500);
    /**
    for (int i = 0; i < 16; i++) {
      concept = new MosaicConcept(i, 500, 500, 4, 4);
      concept.setName(String.format("%s eps99 %02d", concept.name, i));
      generateProblemSet();
    }
     */
   generateProblemSet();
  }

  private void saveProblem(String problemName, String foldName, Instances o) {
    File dataFile = new File(DataCharacterizer.DataPath + "/" + problemName, foldName);
    try {
      dataSaver = new ArffSaver();
      dataSaver.setFile(dataFile);
      dataSaver.setDestination(dataFile);
      dataSaver.setInstances(o);
      dataSaver.setRetrieval(ArffSaver.BATCH);
      dataSaver.writeBatch();
      } catch (Exception e) {
           System.err.println(e.getMessage());
     }
  }

  public void generateProblemSet() {
    String problemName;
    for (int i = 0; i < granularity.length; i++) {
      problemName = String.format("%s-gran%02d", concept.getName(), granularity[i]);
      //ConceptView cv = new ConceptView(concept, granularity[i], .75, .7);
      ConceptView cv = new ConceptView(concept, granularity[i]);    // isograno
      ProblemBuilder pb = new ContinuousProblemBuilder(cv);
      //ProblemBuilder pb = new DiscreteProblemBuilder(cv);
      Instances o = pb.getProblem(problemName, problemSize);
      o.setClassIndex(o.numAttributes()-1);
      String pName = String.format("%s.dat", problemName);
      saveProblem(problemName, pName, o);
      crossValidate(problemName, o, 10);
    }
  }

private void crossValidate(String name, Instances data, int Folds) {
  Random random = new Random(1);
  data.randomize(random);
  data.stratify(Folds);
  for (int i = 0; i < Folds; i++) {
    Instances train = data.trainCV(Folds, i, random);
    Instances test  = data.testCV(Folds, i);
    String pName = String.format("%s-%02d-%01dtra.dat", name, Folds, i+1);
    saveProblem(name, pName, train);
    pName = String.format("%s-%02d-%01dtst.dat", name, Folds, i+1);
    saveProblem(name, pName, test);
  }

}

}
