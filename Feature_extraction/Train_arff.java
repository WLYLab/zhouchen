package Test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Random;

import weka.classifiers.Evaluation;
import weka.classifiers.bayes.NaiveBayes;
import weka.classifiers.evaluation.Prediction;
import weka.classifiers.functions.LibSVM;
import weka.classifiers.trees.J48;
import weka.classifiers.trees.RandomForest;
import weka.core.Instances;
import weka.core.converters.ArffLoader;



public class Train_arff
{

  static String[][] a = new String[100000][100];
  static String[][] c = new String[100000][100];
  static int[] b = new int[1];
  static int RF=0;
  static String SVMc="";
  static String SVMg="";
  static String RFI="";
  static ArrayList<String> canshu = new ArrayList<String>();
  static ArrayList<String> sublist = new ArrayList<String>();
  public void initList(String fileName) {
      try {
    	  int g=0;
      	BufferedReader br = new BufferedReader(new FileReader(fileName));
          while (br.ready())
          {String str = "";
          String line1;
          String strnum = "";
          String str0 = "";
      	String str1 = "";
          String str2 = "";
          String str21 = "";
          String str3 = "";
          String strACC = "";
          for(int i=1;i<=(13+g);i++)
            { 
          	
           line1 = br.readLine();
           if(i==(0+g)){ strnum=line1+",";}//按空格切分不管几个空格
          
           if(i==(4+g)){str1=line1.trim().split("\\s+")[0];}//按空格切分不管几个空格
          // System.out.println(str1);
           if(i==(5+g)){str1=str1+","+line1.trim().split("\\s+")[0]+",";}
           //System.out.println(str1);
           if(i==(11+g)){str2=line1.split("\\s+")[1];str21=line1.split("\\s+")[2];}
           if(i==(12+g)){str2=str2+","+line1.split("\\s+")[2]+","+line1.split("\\s+")[1]+","+str21+",";}
           //System.out.println(line1.split("\\ ")[1]);
           if(i==(6+g)){str0=line1.split("\\s+")[2]+",";
           str3=line1.split("\\s+")[7].trim()+","+line1.split("\\s+")[8]+","+line1.split("\\s+")[9].trim()+",";
           strACC= line1.split("\\s+")[6];
           
           }
            }
            str=str1+str0+str3+str2+strACC ;
            
            sublist.add(str);
//            String canshu=strnum+" "+strACC;
//            listcanshu.add(canshu);
       //     System.out.println(canshu);
          }
     
           
          br.close();

      } catch (Exception e) {
          // TODO: handle exception
          e.printStackTrace();
      }
  }
  public void BufferW_arff(int a, BufferedWriter bw2)
  {
    try
    {
      bw2.write("@relation Protein");
      bw2.newLine();
      for (int i = 1; i <= a; i++)
      {
        bw2.write("@attribute Feature" + i + " real");
        bw2.newLine();
      }
      bw2.write("@attribute class ");
      String attributeClass = "0";
      for (int i = 0; i < 1; i++) {
        attributeClass = attributeClass + "," + (i + 1);
      }
      bw2.write("{" + attributeClass + "}");
      bw2.newLine();
      bw2.write("@data");
      bw2.newLine();
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
  }
  
  public static String getRealPath()
  {
    String realPath = Train_arff.class.getClassLoader().getResource("")
      .getFile();
    File file = new File(realPath);
    realPath = file.getAbsolutePath();
    try
    {
      realPath = URLDecoder.decode(realPath, "utf-8");
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
    return realPath;
  }
  
  public void Train(int x, String t, String systemPath,int n, BufferedWriter bw2, String meth,int fold)
  {
	  
	  
    try
    {
    	 
    	if(meth.equals("SVM")){
    		LibSVM classifier1 = new LibSVM();
    		 String[] options1=weka.core.Utils.splitOptions("-B -C "+SVMc+" -G "+SVMg );
    		  classifier1.setOptions(options1);
    		  File inputFile1 = new File(systemPath + "/arff/" + t + ".arff");
    	      System.out.println(systemPath + "/arff/" + t + ".arff");
    	      ArffLoader atf1 = new ArffLoader();
    	      atf1.setFile(inputFile1);
    	      Instances instancesTrain1 = atf1.getDataSet();
    	      instancesTrain1.setClassIndex(instancesTrain1.numAttributes() - 1);
    	      int sum = instancesTrain1.numInstances();
    		  classifier1.buildClassifier(instancesTrain1);
    	      Evaluation eval1 = new Evaluation(instancesTrain1);
    	      eval1.crossValidateModel(classifier1, instancesTrain1, fold, new Random(1L), new Object[0]);
    	      bw2.write(eval1.toClassDetailsString());
    	      bw2.newLine();
    	      bw2.write(eval1.toMatrixString());
    	      bw2.newLine();
    	      for (int i = 0; i < sum; i++)
    	      {
    	        String[] a3 = ((Prediction)eval1.predictions().get(i)).toString().split("\\ ");
//    	        System.out.print(eval1.predictions().get(i).toString());
    	        a[i][(x - 1)] = a3[2];
    	        c[i][(x - 1)] = a3[5];
    	        c[i][n] = a3[1];
    	        a[i][n] = a3[1];
    	       
    	      }
    	      b[0] = sum;
    		}
    	if(meth.equals("RF")){
    		  RandomForest classifier2 = new RandomForest();
    		  String[] options1=weka.core.Utils.splitOptions("-I "+RFI );
    		  classifier2.setOptions(options1);
    		  File inputFile1 = new File(systemPath + "/arff/" + t + ".arff");
    	      System.out.println(systemPath + "/arff/" + t + ".arff");
    	      ArffLoader atf1 = new ArffLoader();
    	      atf1.setFile(inputFile1);
    	      Instances instancesTrain1 = atf1.getDataSet();
    	      instancesTrain1.setClassIndex(instancesTrain1.numAttributes() - 1);
    	      int sum = instancesTrain1.numInstances();
    		  classifier2.buildClassifier(instancesTrain1);
    	      Evaluation eval1 = new Evaluation(instancesTrain1);
    	      eval1.crossValidateModel(classifier2, instancesTrain1, fold, new Random(1L), new Object[0]);
    	      bw2.write(eval1.toClassDetailsString());
    	      bw2.newLine();
    	      bw2.write(eval1.toMatrixString());
    	      bw2.newLine();
    	      for (int i = 0; i < sum; i++)
    	      {
    	        String[] a3 = ((Prediction)eval1.predictions().get(i)).toString().split("\\ ");
    	        a[i][(x - 1)] = a3[2];
    	        c[i][(x - 1)] = a3[5];
    	        c[i][n] = a3[1];
    	        a[i][n] = a3[1];
    	       
    	      }
    	      b[0] = sum;
    		}
    	
    	if(meth.equals("J48")){
    		J48 classifier3 = new J48();
  		  String[] options1=weka.core.Utils.splitOptions("-I "+RFI );
  		  classifier3.setOptions(options1);
  		  File inputFile1 = new File(systemPath + "/arff/" + t + ".arff");
//  	      System.out.println(systemPath + "//arff//" + t + ".arff");
  	      ArffLoader atf1 = new ArffLoader();
  	      atf1.setFile(inputFile1);
  	      Instances instancesTrain1 = atf1.getDataSet();
  	      instancesTrain1.setClassIndex(instancesTrain1.numAttributes() - 1);
  	      int sum = instancesTrain1.numInstances();
  		  classifier3.buildClassifier(instancesTrain1);
  	      Evaluation eval1 = new Evaluation(instancesTrain1);
  	      eval1.crossValidateModel(classifier3, instancesTrain1, fold, new Random(1L), new Object[0]);
  	      bw2.write(eval1.toClassDetailsString());
  	      bw2.newLine();
  	      bw2.write(eval1.toMatrixString());
  	      bw2.newLine();
  	      for (int i = 0; i < sum; i++)
  	      {
  	        String[] a3 = ((Prediction)eval1.predictions().get(i)).toString().split("\\ ");
  	        a[i][(x - 1)] = a3[2];
  	        c[i][(x - 1)] = a3[5];
  	        c[i][n] = a3[1];
  	        a[i][n] = a3[1];
  	       
  	      }
  	      b[0] = sum;
  		}
    	if(meth.equals("NB")){
    		NaiveBayes classifier4 = new NaiveBayes();
//  		  String[] options1=weka.core.Utils.splitOptions("-I "+RFI );
//  		  classifier1.setOptions(options1);
  		  File inputFile1 = new File(systemPath + "/arff/" + t + ".arff");
//  	      System.out.println(systemPath + "//arff//" + t + ".arff");
  	      ArffLoader atf1 = new ArffLoader();
  	      atf1.setFile(inputFile1);
  	      Instances instancesTrain1 = atf1.getDataSet();
  	      instancesTrain1.setClassIndex(instancesTrain1.numAttributes() - 1);
  	      int sum = instancesTrain1.numInstances();
  		  classifier4.buildClassifier(instancesTrain1);
  	      Evaluation eval1 = new Evaluation(instancesTrain1);
  	      eval1.crossValidateModel(classifier4, instancesTrain1, fold, new Random(1L), new Object[0]);
  	      bw2.write(eval1.toClassDetailsString());
  	      bw2.newLine();
  	      bw2.write(eval1.toMatrixString());
  	      bw2.newLine();
  	      for (int i = 0; i < sum; i++)
  	      {
  	        String[] a3 = ((Prediction)eval1.predictions().get(i)).toString().split("\\ ");
  	        a[i][(x - 1)] = a3[2];
  	        c[i][(x - 1)] = a3[5];
  	        c[i][n] = a3[1];
  	        a[i][n] = a3[1];
  	       
  	      }
  	      b[0] = sum;
  		}
    	 
//    	LibSVM classifier1 = new LibSVM();
//    	 J48 classifier1 = new J48();
//    	classifier1 = new RandomForest();
//    	 String[] options1=weka.core.Utils.splitOptions("-I ");
    	//options[0]="-K";//Use a kernel estimator for numeric attributes rather than a normal distribution.

         //options[0]="-D";//Use supervised discretization to convert numeric attributes to nominal ones.
//   	  classifier1.setOptions(options1);
//      NaiveBayes classifier1 = new NaiveBayes();
       
//   	 Classifier cfs = null;
//   	cfs = new IBk();
//
//  
//  String[] options=weka.core.Utils.splitOptions("-S 0 -K 2 -D 3 -G 0.0 -R 0.0 -N 0.5 -M 40.0 -C 1.0 -E 0.0010 -P 0.1 -B 0");
//  ((NaiveBayes) cfs).setOptions(options);
       
      
      
   
//      sublist.add(eval1.toClassDetailsString());
//      sublist.add(eval1.toMatrixString());
      
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
  }
  
  public void DealS(String[][] f, BufferedWriter bw, int c,int n)
  {
    try
    {
      for (int j = 0; j < c; j++) {
        for (int i = 0; i <= n; i++) {
          if (i == n)
          {
            String y = f[j][i];
            bw.write(y.substring(0, y.length() - 2));
            bw.newLine();
          }
          else
          {
            bw.write(f[j][i] + ",");
          }
        }
      }
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
  }
  
  public void DealS2(String[][] f, BufferedWriter bw, int c,int n)
  {
    try
    {
      for (int j = 0; j < c; j++) {
        for (int i = 0; i <= n; i++) {
          if (i == n)
          {
            bw.write(f[j][i]);
            bw.newLine();
          }
          else if (i == 0)
          {
            String y = f[j][i];
            
            bw.write(y.substring(0, y.length() - 2) + ",");
          }
          else
          {
            bw.write(f[j][i] + ",");
          }
        }
      }
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
  }
  
  public void initcanshu(String fileName)
  {
    try
    {
      BufferedReader br = new BufferedReader(new FileReader(fileName));
      while (br.ready())
      {
        String line1 = br.readLine();
        canshu.add(line1);
      }
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
  }
  
  public static void main(String[] args)
    throws Exception
  {

	  String intputFile = args[0];
	  String methmethnum = args[1];
//	  String methmethnum= "RF";
// 	 String intputFile = "C:\\Users\\Administrator\\eclipse-workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\FeaPlus1-modified3\\Result_file\\1567965734";



    BufferedReader br = new BufferedReader(new FileReader(intputFile+"/Des.txt"));
    
    
    int linenum=1;
    String line1;
    String[] name=null;
    String[] meth=null;
    String[] canshu_a=null;
    int fold=1;
    while (br.ready())     	
      {
    	
   line1 = br.readLine();
    if(linenum==1&&!line1.isEmpty()) {name=line1.trim().split(",");}
    if(linenum==9&&!line1.isEmpty()) {meth=line1.trim().split(",");}
    if(linenum==10&&!line1.isEmpty()) { canshu_a=line1.trim().split(",");}
    if(linenum==11&&!line1.isEmpty()) { fold=Integer.parseInt(line1.trim());}
    linenum++;
      }
    SVMc=canshu_a[0];
    SVMg=canshu_a[1];
    RFI= canshu_a[2];
    br.close();
    int n=name.length;
   
//    for(int methnum=0;methnum<meth.length;methnum++)
    {
    BufferedWriter bw = new BufferedWriter(new FileWriter(intputFile + "/temp/"+methmethnum+"/FTc.arff"), 5242880);
    BufferedWriter bw2 = new BufferedWriter(new FileWriter(intputFile + "/temp/"+methmethnum+"/FTc.csv"), 5242880);
    BufferedWriter bw3 = new BufferedWriter(new FileWriter(intputFile + "/temp/"+methmethnum+"/FTp.arff"), 5242880);
    BufferedWriter bw4 = new BufferedWriter(new FileWriter(intputFile + "/temp/"+methmethnum+"/FTp.csv"), 5242880);
//    BufferedWriter bw3 = new BufferedWriter(new FileWriter(intputFile + "/temp/FT.cmd"), 5242880);
   
   
      Train_arff rl = new Train_arff();
      rl.BufferW_arff(n, bw);
      rl.BufferW_arff(n, bw3);
      BufferedWriter bwf=new BufferedWriter(new FileWriter(intputFile+"/temp/"+methmethnum+"/Result_In.txt"),5*1024*1024);
      for (int m = 1; m < n + 1; m++) {
//    	  System.out.print(name[(m - 1)]);
        rl.Train(m, name[(m - 1)], intputFile,n,bwf,methmethnum,fold);
//        System.out.print(name[(m - 1)]);
      }
      bwf.close();
      bw2.write("class,");
      for (int i = 1; i < n; i++) {
        bw2.write("fea" + i + ",");
      }
      bw2.write("fea"+n);
      bw2.newLine();
      rl.DealS(a, bw, b[0],n);
      rl.DealS2(a, bw2, b[0],n);
      
      canshu.clear();
      
      bw.close();
      bw2.close();
      
      bw4.write("class,");
      for (int i = 1; i < n; i++) {
        bw4.write("fea" + i + ",");
      }
      bw4.write("fea"+n);
      bw4.newLine();
      rl.DealS(c, bw3, b[0],n);
      rl.DealS2(c, bw4, b[0],n);
      
      canshu.clear();
      
      bw3.close();
      bw4.close();
      
      

      
      BufferedWriter bw1 = new BufferedWriter(new FileWriter(intputFile + "/temp/"+"Result_In_"+methmethnum+".csv"), 5242880);


	
//	    rl.initList(systemPath+"/result_art.txt");
		
	    rl.initList(intputFile +"/temp/"+methmethnum+"/Result_In.txt");
	    bw1.write("Training Model (10-fold cross validation");
		bw1.newLine();
		bw1.write("Fea,SE,SP,ACC,MCC,ROC Area,PRC Area,TP,TN,FP,FN,F-Measure");
		bw1.newLine();
		for (int i = 0; i < sublist.size(); i++) {
			

	    	bw1.write(name[i]+"_"+methmethnum+","+sublist.get(i));
	    	
			bw1.newLine();
		}
		sublist.clear();
	    bw1.close();
	    
    }
   
   name=null;
   meth=null;
   canshu_a=null;
    
  }
////BufferedWriter bw3 = new BufferedWriter(new FileWriter(intputFile1 + "/FT.cmd"), 5242880); 
////windows
////bw3.write("@echo off");
////bw3.newLine();
////bw3.write(intputFile1.substring(0, 2).toUpperCase());
////bw3.newLine();
////bw3.write("cd " + intputFile1);
////bw3.newLine();
////bw3.write(intputFile1 + "/mRMR/mrmr_win32.exe -i " + intputFile + "/temp/FTc.csv -n"+n+">" + intputFile + "/temp/FT.mrmrout");
////bw3.newLine();
////bw3.write("exit");
////bw3.close();
//
////linux
////
////bw3.write("cd " + intputFile1);
////bw3.newLine();
////bw3.write(intputFile1 + "./mrmr -i " + intputFile + "/temp/FTc.csv -n"+n+">" + intputFile + "/temp/FT.mrmrout");
////bw3.newLine();
////bw3.write("exit");
////bw3.close();
    
    
   
  
}
