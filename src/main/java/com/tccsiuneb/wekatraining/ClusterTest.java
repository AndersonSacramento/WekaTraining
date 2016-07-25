package com.tccsiuneb.wekatraining;

import weka.clusterers.ClusterEvaluation;
import weka.clusterers.Clusterer;
import weka.clusterers.HierarchicalClusterer;
import java.io.BufferedReader;
import weka.core.Instances;
import java.io.FileReader;
import java.io.IOException;

public class ClusterTest{

    public static void main(String []args) throws IOException{
	 BufferedReader reader = new BufferedReader(
						    new FileReader("example_leaf.arff"));
	 Instances data = new Instances(reader);
	 String[] options = new String[2];
	 options[0] = "-N";                 // max. iterations
	 options[1] = "13";
	 HierarchicalClusterer clusterer = new HierarchicalClusterer();   // new instance of clusterer
	 try {
	     clusterer.setOptions(options);     // set the options
	     clusterer.buildClusterer(data);    // build the clusterer
	     ClusterEvaluation eval = new ClusterEvaluation();
	     eval.setClusterer(clusterer);                                   // the cluster to evaluate
	     eval.evaluateClusterer(data);                                // data to evaluate the clusterer on
	     System.out.println("# of clusters: " + eval.getNumClusters());  // output # of clusters
	     System.out.println(eval.clusterResultsToString());
	     double[] assignments = eval.getClusterAssignments();
	     int i = 0;
	     for (double clusterNum : assignments) {
		 System.out.printf("Instance %d -> Cluster %f \n", i, clusterNum);
		 i++;
	     }
	     
	 } catch (Exception e) {
	     System.out.println(e);
	 }
    }

}
