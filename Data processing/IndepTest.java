package Test;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

import weka.classifiers.Classifier;
import weka.classifiers.Evaluation;
import weka.core.OptionHandler;
import weka.core.SerializationHelper;
import weka.core.Utils;
import weka.classifiers.trees.J48;
import weka.core.Instances;
import weka.core.converters.ArffLoader;
import weka.filters.Filter;
import weka.filters.supervised.attribute.AddClassification;

@SuppressWarnings("unused")
public class IndepTest//集成十折交叉的多数投票
{	static int y=164;//样本数
	private static String systemPath="C:\\Users\\Administrator\\Desktop\\CPP";
	private static  String str="GAP";
	private static int num=10;
	public static void main(String[] args) throws Exception {
		
	
		//BufferedWriter bw1=new BufferedWriter(new FileWriter(systemPath+"FT"+x+".txt"),5*1024*1024);
		 
		     
//		int c=0;
		for(int x=1;x<=num;x++)
		{ 
			double[][] a = new double[y][x];
		//double[][] b = new double[y][2];
	
	           File inputFile1 = new File(systemPath+"/10_9//"+str+x+".arff");//训练语料文件
	           ArffLoader atf1 = new ArffLoader();
	           atf1.setFile(inputFile1);
	           Instances instancesTest = atf1.getDataSet(); // 读入训练文件
	           Classifier classifier1=(Classifier)weka.core.SerializationHelper.read(systemPath+"/train//Modeltest//"+str+x+".model");
	           //Classifier classifier1=(LibSVM)weka.core.SerializationHelper.read("E://ModelbymRMR//"+x+".model");
	           //double r1=0.0f;
	           //inputFile = new File("D://Program Files//Weka-3-8//data//NT2-test-Bit20.arff");//测试语料文件
	           //Instances instancesTest = atf.getDataSet(); // 读入测试文件     
	           //instancesTest.setClassIndex(40);
	           instancesTest.setClassIndex(instancesTest.numAttributes()-1);
               double sum=instancesTest.numInstances();//测试语料实例数
	       
	           //classifier1.buildClassifier(instancesTest);     
	           Evaluation eval1 = new Evaluation(instancesTest);
	           eval1.evaluateModel(classifier1, instancesTest);

	           
	           
//	           System.out.println(c);
//		   		System.out.println(classifier1.classifyInstance(instancesTest.instance(98))); 
		   		//bw1.write(eval1.toClassDetailsString());
	           System.out.println( eval1.toClassDetailsString());
	           System.out.println( eval1.toMatrixString());
	           System.out.println("    ");
	           System.out.println("    ");
//	           Object[] preds=eval1.predictions().toArray();
//	           for(Object pred : preds) {
	           
//	        	    System.out.println(pred);
//	        	}
		  	}
		 
   		 
		}
	   		
	     
	       
//	   		System.out.println(eval3.areaUnderPRC(0));
	    }

