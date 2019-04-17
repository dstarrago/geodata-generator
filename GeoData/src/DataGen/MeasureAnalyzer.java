/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package DataGen;

import java.io.*;
import java.util.Date;
import weka.classifiers.Evaluation;
import weka.core.DenseInstance;
import weka.core.Instances;
import weka.classifiers.Classifier;
import weka.classifiers.AbstractClassifier;
import weka.core.converters.ArffLoader;
import weka.core.converters.ArffSaver;
import metalearningexperimenter.Measures;

/**
 *
 * @author Danel
 */
public class MeasureAnalyzer {

  /**
   * Alternative saving modes
   */
  private final int smIncremental = 1;
  private final int smBatch = 2;

  private int perfAccuracy = 0;
  private int perfKappa = 1;
  private int perfAUC = 2;

  /**
   *  *************** Experiment settings ********************
   */

  /**
   *  Database path
   */
  //public static String DataPath = "C:/Documents and Settings/Danel/My Documents/Docs/SOFTCOMPUTING/Tesis/LAB/DATA/Roughness Size36";
  //public static String DataPath = "C:/Documents and Settings/Danel/My Documents/Docs/SOFTCOMPUTING/Tesis/LAB/DATA/Roughness Size16Disc";
  //public static String DataPath = "C:/Documents and Settings/Danel/My Documents/Docs/SOFTCOMPUTING/Tesis/LAB/DATA/Roughness Size16Cont";
  public static String DataPath = "C:/Documents and Settings/Danel/My Documents/Docs/SOFTCOMPUTING/Tesis/LAB/DATA/YinYan";

  /**
   *  Output directory
   */
  //public static String MetaDataPath = "C:/Documents and Settings/Danel/My Documents/Docs/SOFTCOMPUTING/Tesis/LAB/ANALYSIS/Roughness Size36Fixed";
  //public static String MetaDataPath = "C:/Documents and Settings/Danel/My Documents/Docs/SOFTCOMPUTING/Tesis/LAB/ANALYSIS/Roughness Size16FixedDisc";
  //public static String MetaDataPath = "C:/Documents and Settings/Danel/My Documents/Docs/SOFTCOMPUTING/Tesis/LAB/ANALYSIS/Roughness Size16FixedCont";
  public static String MetaDataPath = "C:/Documents and Settings/Danel/My Documents/Docs/SOFTCOMPUTING/Tesis/LAB/ANALYSIS/YinYan";

  /**
   *  Meta-dataset names
   */
  //static final String fileName = "MeasuresRoughness";
  //static final String fileName = "Roughness Size16Fixed";
  static final String fileName = "YinYan";

  /**
   * Saving mode
   */
  private int SaveMode = smIncremental;

  /**
   *  *************** End of experiment settings ********************
   */

  /**
   * Learning algorithms
   */
  private Classifier algorithm;
  String algorithmName = "weka.classifiers.trees.J48";

  /**
   *  Meta-datasets
   */
  private Instances mdsCharacter;

  /**
   *  Objects to write meta-dataset files in Arff format
   */
  private ArffSaver characterSaver;

  /**
   * Object to write log file.
   */
  private PrintWriter compLogWriter;

  /**
   * A counter for total compilation time
   */
  long compTime;

  Measures measures = new Measures();

  /**
   *
   */
  public final int patronRuleSupport = 5;

  /**
   * Constructor of the class
   */
  private MeasureAnalyzer() {
    beginCompilationLog();
    logMeasures();
    setupAlgorithm();
    setupMetaDatasets();
    if (SaveMode == smIncremental) setupSavers(ArffSaver.INCREMENTAL);
    processBaseDS();
    if (SaveMode == smBatch) saveMetaData();
    endCompilationLog();
  }

  private void setupAlgorithm() {
    try {
      algorithm = AbstractClassifier.forName(algorithmName, null);
      } catch (Exception e) {
           System.err.println(e.getMessage());
    }
  }

  /**
   * Set meta-attribute information
   */
  private void logMeasures() {
    compLogWriter.print("\n");
    compLogWriter.print("Data characterization measures: \n");
    for (int i = 1; i < measures.count(); i++) {
      compLogWriter.print(measures.name(i) + "\n");
    }
  }

  /**
   * Create empty meta-datasets with attribute information
   */
  private void setupMetaDatasets() {
    mdsCharacter = new Instances(fileName, measures.struct(), 1);
  }

/**
 * Create and configure the objects in charge of save datasets to files.
 *
 * @param saveMode
 */
  private void setupSavers(int saveMode) {
    /**
     * Set the file address for meta-datasets
     */
    File characterFile = new File(MetaDataPath, fileName + ".arff");
    /**
     * Try to create saver objects for every meta-dataset and configure it
     */
    try {
      characterSaver = new ArffSaver();
      characterSaver.setFile(characterFile);
      characterSaver.setDestination(characterFile);
      characterSaver.setInstances(mdsCharacter);
      characterSaver.setRetrieval(saveMode);
      } catch (Exception e) {
           System.err.println(e.getMessage());
     }
  }

  /**
   * Iterate through the directories containing databases processing each one
   */
  private void processBaseDS() {
    /**
     * Logging
     */
    compLogWriter.print("\n");
    compLogWriter.print("Processed datasets:\n");
    File data = new File(DataPath);
    File[] Directories = data.listFiles();
    for (int i = 0; i < Directories.length; i++)
      if (Directories[i].isDirectory())
        processDir(Directories[i]);
  }

  /**
   * Select the scheme of cross validation to apply.
   *
   * @param Dir
   */
  private void processDir(File Dir) {
    File[] datasets = Dir.listFiles();
    double[][] result = new double[datasets.length][];
    Instances baseDS = null;
    for (int i = 0; i < datasets.length; i++) {
      baseDS = getInstances(datasets[i]);
      result[i] = measures.extractMetaAttributes(baseDS);

      double[] performance = runExperiment(algorithm, baseDS);
      result[i][measures.indexOf(Measures.maTrainAccuracy)] = performance[perfAccuracy];
      result[i][measures.indexOf(Measures.maTrainKappa)] = performance[perfKappa];
      result[i][measures.indexOf(Measures.maTrainAUC)] = performance[perfAUC];
    }
    double[] avgResult = average(result);
    mdsCharacter.add(new DenseInstance(1, avgResult));
    mdsCharacter.lastInstance().setValue(measures.indexOf(Measures.maProblemID), Dir.getName());
    //mdsCharacter.lastInstance().setValue(measures.indexOf(Measures.maDataDescription), baseDS.toSummaryString());
    /**
     * If save mode is incremental then save the new meta instance.
     */
    if (SaveMode == smIncremental)
      try {
        characterSaver.writeIncremental(mdsCharacter.lastInstance());
        characterSaver.getWriter().flush();
        } catch (Exception e) {
             System.err.println(e.getMessage());
       }
}

  /**
   *  Lo mismo que averageResults
   *
   * @param data
   * @return
   */
  private double[] average(double[][] data) {
    double[] Average = new double[data[0].length];
    for (int i = 0; i < data[0].length; i++) {
      double S = 0;
      for (int j = 0; j < data.length; j++)
        S += data[j][i];
      Average[i] = (double) S / data.length;
    }
    return Average;
  }

  /**
   * Run a classifier on a train set to build a model and test it in a test set
   * for returning performance measures.
   *
   * @param scheme
   * @param train
   * @param test
   * @return
   */
  private double[] runExperiment(Classifier scheme, Instances trainInstances) {
    double[] Result = new double[3];
    try {
      scheme.buildClassifier(trainInstances);
      Evaluation trainEvaluation = new Evaluation(trainInstances);
      trainEvaluation.evaluateModel(scheme, trainInstances);
      Result[perfAccuracy] = trainEvaluation.pctCorrect();
      Result[perfKappa] = trainEvaluation.kappa();
      Result[perfAUC] = trainEvaluation.areaUnderROC(0);
      } catch (Exception e) {
           System.err.println(e.getMessage());
     }
   return Result;
  }

  /**
   * Open a dataset file in Keel format an return the dataset.
   *
   * @param f
   * @return
   */
  private Instances getInstances(File f) {
    Instances I = null;
    ArffLoader kl = new ArffLoader();
    try {
      kl.setSource(f);
      I = kl.getDataSet();
      I.setClassIndex(I.numAttributes() - 1);
      kl.reset();
    } catch (Exception e) {
      // For IOException or FileNotFoundException
      e.printStackTrace(System.err);
      System.exit(1);
    }
    return I;
  }

  /**
   * From the full name of an algorithm, return a short name that is the
   * last part of the full name.
   *
   * @param fullName
   * @return
   */
  private String algName(String fullName) {
	int index = fullName.lastIndexOf('.');
	return fullName.substring(index + 1);
  }

  /**
   *  Save meta-datsets in a batch.
   */
  private void saveMetaData() {
    setupSavers(ArffSaver.BATCH);
    try {
      characterSaver.writeBatch();
      } catch (Exception e) {
           System.err.println(e.getMessage());
     }
  }

  private void beginCompilationLog() {
    Date currentTime = new Date();
    compTime = currentTime.getTime();
    File metaDataFile = new File(MetaDataPath);
    if (!metaDataFile.exists()) metaDataFile.mkdir();
    File compLogFile = new File(metaDataFile, fileName + "_log.rtf");
    BufferedWriter writer;
    try {
      OutputStream output = new FileOutputStream(compLogFile);
      writer = new BufferedWriter(new OutputStreamWriter(output));
      compLogWriter = new PrintWriter(writer);
      compLogWriter.print("Dataset Characterization System for Data Complexity" + "\n");
      compLogWriter.print("\n");
      compLogWriter.print("Starting time: " + currentTime + "\n");
      compLogWriter.print("\n");
      compLogWriter.print("Base-datasets directory:\n");
      compLogWriter.print(DataPath + "\n");
      compLogWriter.print("\n");
      compLogWriter.print("Meta-datasets directory\n");
      compLogWriter.print(MetaDataPath + "\n");
      compLogWriter.print("\n");
    } catch (Exception e) {
         System.err.println(e.getMessage());
   }
  }

  private void endCompilationLog() {
    Date currentTime = new Date();
    compTime = (currentTime.getTime() - compTime) / 60000;
    compLogWriter.print("\n");
    compLogWriter.print("Ending time: " + currentTime + "\n");
    compLogWriter.print("\n");
    compLogWriter.print("Compilation time: " + compTime + " minutes \n");
    compLogWriter.flush();
  }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
      MeasureAnalyzer Exp1 = new MeasureAnalyzer();
    }


}
