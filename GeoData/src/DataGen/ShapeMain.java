/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package DataGen;

import java.awt.*;
import java.awt.event.*;
//import java.io.*;
//import weka.core.converters.ArffSaver;

/**
 *
 * @author Danel
 */
public class ShapeMain {

  private ShapeView view;                        // The view of the sketch
  private static ShapeFrame window;              // The application window
  private static ShapeMain theApp;                 // The application object
  public Concept concept;
  public ConceptView cview;

  /**
   *  Objects to write meta-dataset files in Arff format
   */
  //private ArffSaver dataSaver;
  //public String dataName = "roughness";

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
      theApp = new ShapeMain();                      // Create the application object
      //theApp.init();                                // ... and initialize it
      //theApp.generateProblemsFolds();
      //theApp.generateProblems();
      //theApp.generateProblemSet();
      new DataGen();
    }

  public void init() {
    //concept = new YinYanConcept(500, 500);
    concept = new MosaicConcept(3, 500, 500, 4, 4);
    //concept = new EllipseConcept(500, 500);
    //concept = new RhombusConcept(500, 500);
    //concept = new SinuousConcept(500, 500);
    //concept = new LinealConcept(500, 500);
    cview = new ConceptView(concept, 50); //1, 2, 4, 5, 10, 20, 25, 50, 100
    //cview = new ConceptView(concept, 50, .75, .7); //1, 2, 4, 5, 10, 20, 25, 50, 100
    window = new ShapeFrame("Artificial Data Sets Modeler");    // Create the app window
    Toolkit theKit = window.getToolkit();          // Get the window toolkit
    Dimension wndSize = theKit.getScreenSize();    // Get screen size

    // Set the position to screen center & size to 1/2 screen size
    window.setBounds(0, 0,        // Position
                     wndSize.width, wndSize.height);   // Size

    window.addWindowListener(new WindowHandler()); // Add window listener

    view = new ShapeView(this);              // Create the view
    BorderLayout border = new BorderLayout(2, 2);        // Create a layout manager
    Container content = window.getContentPane();   // Get the content pane
    content.setLayout(border);                    // Set the container layout mgr
    content.add(view, BorderLayout.CENTER);
    window.setVisible(true);
    //view.repaint();
  }
  
    // Return a reference to the application window
  public ShapeFrame getWindow() {
     return window;
  }

  // Return a reference to the view
  public ShapeView getView() {
     return view;
  }

/**
 * Create and configure the objects in charge of save datasets to files.
 *
 * @param saveMode
 */
  /**
  private void saveProblem(String problemName, String foldName, RoughnessProblem p) {
    File dataFile = new File(MeasureAnalyzer.DataPath + "/" + problemName, foldName);
    try {
      dataSaver = new ArffSaver();
      dataSaver.setFile(dataFile);
      dataSaver.setDestination(dataFile);
      dataSaver.setInstances(p.data);
      dataSaver.setRetrieval(ArffSaver.BATCH);
      dataSaver.writeBatch();
      } catch (Exception e) {
           System.err.println(e.getMessage());
     }
  }
   */
/**
  public void generateProblemSet() {
    RoughnessProblem p;
    String problemName;
    for (int i = 0; i < 12; i++) {
      problemName = String.format("%s-%02d", dataName, i);
      for (int j = 0; j < ProblemParams.experimentReps; j++) {
        p = new RoughnessProblem(theApp, i, ProblemParams.problemSize);
        String pName = String.format("%s-%02d.dat", problemName, j);
        saveProblem(problemName, pName, p);
      }
    }
  }

  public void generateProblems() {
    RoughnessProblem p;
    String problemName;
    for (int i = 0; i < 6; i++) {
      problemName = dataName + i;
      p = new RoughnessProblem(theApp, i, ProblemParams.problemSize);
      saveProblem(problemName, problemName + ".dat", p);
    }
  }

  public void generateProblemsFolds() {
    int folds = (ProblemParams.problemSize <= 100)? 5 : 10;
    RoughnessProblem p;
    String problemName;
    for (int i = 0; i < 6; i++) {
      problemName = dataName + i;
      for (int j = 0; j < folds; j++) {
        p = new RoughnessProblem(theApp, i, ProblemParams.problemSize);
        saveProblem(problemName, problemName + "-" + folds + "-" + j + "tst.dat", p);
        p = new RoughnessProblem(theApp, i, ProblemParams.problemSize);
        saveProblem(problemName, problemName + "-" + folds + "-" + j + "tra.dat", p);
      }
    }
  }
*/
// Handler class for window events
  class WindowHandler extends WindowAdapter {
    // Handler for window closing event
    public void windowClosing(WindowEvent e) {
      // Code to be added here later...
    }
  }

}
