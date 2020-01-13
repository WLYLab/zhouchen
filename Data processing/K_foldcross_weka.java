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
import weka.classifiers.bayes.NaiveBayes;
import weka.core.OptionHandler;
import weka.core.SerializationHelper;
import weka.core.Utils;
import weka.classifiers.trees.J48;
import weka.classifiers.trees.RandomForest;
import weka.core.Instances;
import weka.core.converters.ArffLoader;
import weka.filters.Filter;
import weka.filters.supervised.attribute.AddClassification;

@SuppressWarnings("unused")
public class K_foldcross_weka//集成十折交叉的多数投票
{	//static int y=500;//样本数
	private static String systemPath="C:\\Users\\Administrator\\Desktop\\CPP";
	private static  String str="FT";
	private static int num=46;
	public static void main(String[] args) throws Exception {
		
		//BufferedReader br3=new BufferedReader(new FileReader(systemPath+"/FTSVM.txt"));
		//BufferedWriter bw1=new BufferedWriter(new FileWriter(systemPath+"FT"+x+".txt"),5*1024*1024);
		      //LibSVM classifier1 = new LibSVM();
		     
//		      NaiveBayes  classifier1= new  NaiveBayes ();
//		      String[] g=new String[100];
//		  	  String line3;
//		  	  int m=1;
//		  	while((line3=br3.readLine())!=null)
//			{
//				g[m]=line3;
//				m++;
//			}
		     
//		int c=0;
		for(int x=1;x<=num;x++)
			
		{    
			//double[][] a = new double[y][x];
		    //double[][] b = new double[y][2];
			   RandomForest classifier1= new  RandomForest();
	        //  NaiveBayes  classifier1= new  NaiveBayes ();
	           File inputFile1 = new File(systemPath+"/all//FT462//"+str+x+".arff");//训练语料文件
	           ArffLoader atf1 = new ArffLoader();
	           atf1.setFile(inputFile1);
	           Instances instancesTrain1 = atf1.getDataSet(); // 读入训练文件
	           //double r1=0.0f;
	           //inputFile = new File("D://Program Files//Weka-3-8//data//NT2-test-Bit20.arff");//测试语料文件
	           //Instances instancesTest = atf.getDataSet(); // 读入测试文件     
	           //instancesTest.setClassIndex(40);
	           //instancesTrain1.setClassIndex(instancesTrain1.numAttributes()-1);
               //double sum=instancesTrain1.numInstances();//测试语料实例数
	           
               instancesTrain1.setClassIndex(instancesTrain1.numAttributes()-1);
//	   		   String[] options1=weka.core.Utils.splitOptions(g[x]);
//	   		   classifier1.setOptions(options1);
               classifier1.buildClassifier(instancesTrain1);     
	           Evaluation eval1 = new Evaluation(instancesTrain1);
	           eval1.crossValidateModel(classifier1, instancesTrain1, 10, new Random(1));

	        
//	           SerializationHelper.write((systemPath+"/train//ModelFT//"+str+x+".model"), classifier1);
//	           c++;
//	           System.out.println(c);
//		   		 
		   		//bw1.write(eval1.toClassDetailsString());
	           System.out.println( eval1.toClassDetailsString());
	           System.out.println( eval1.toMatrixString());
	           System.out.println("    ");
//	           Object[] preds=eval1.predictions().toArray();
//	           for(Object pred : preds) {           
//	        	    System.out.println(pred);
//	        	}
		  	}
		 
   		 
		}
	   		
	     
	       
//	   		System.out.println(eval3.areaUnderPRC(0));
	    }

