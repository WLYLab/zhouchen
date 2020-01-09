package Test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Random;

import weka.classifiers.Evaluation;
import weka.classifiers.bayes.NaiveBayes;
import weka.classifiers.functions.LibSVM;
import weka.classifiers.trees.J48;
import weka.classifiers.trees.RandomForest;
import weka.core.Instances;
import weka.core.converters.ArffLoader;



public class Train_FT
{


  static ArrayList<String> sublist = new ArrayList<String>();
  static int RF=0;
  static String SVMc="";
  static String SVMg="";
  static String RFI="";
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
    String realPath = Train_FT.class.getClassLoader().getResource("")
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
  
  public void Train(int x, String t, String systemPath,int n, BufferedWriter bw2, String meth,int fold,String DDmeth)
  {
    try
    {

 
       
       
       if(meth.equals("SVM")){
   		LibSVM classifier1 = new LibSVM();
   		 String[] options1=weka.core.Utils.splitOptions("-B -C "+SVMc+" -G "+SVMg );
   		  classifier1.setOptions(options1);
   		 File inputFile1 = new File(systemPath + "/temp/"+meth+"/"+DDmeth+"//FT" + x + ".arff");
   		
   	      System.out.println(systemPath + "/temp/"+meth+"/"+DDmeth+"//FT" + x + ".arff");
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
   	     
   		}
   	if(meth.equals("RF")){
   		  RandomForest classifier1 = new RandomForest();
   		  String[] options1=weka.core.Utils.splitOptions("-I "+RFI );
   		  classifier1.setOptions(options1);
   		 File inputFile1 = new File(systemPath + "/temp/"+meth+"/"+DDmeth+"//FT" + x + ".arff");
   		System.out.println(systemPath + "/temp/"+meth+"/"+DDmeth+"//FT" + x + ".arff");
//	      System.out.println(systemPath + "//arff//" + t + ".arff");
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
   	     
   		}
   	
   	if(meth.equals("J48")){
   		J48 classifier1 = new J48();
 		  String[] options1=weka.core.Utils.splitOptions("-I "+RFI );
 		  classifier1.setOptions(options1);
 		 File inputFile1 = new File(systemPath + "/temp/"+meth+"/"+DDmeth+"//FT" + x + ".arff");
    		
//	      System.out.println(systemPath + "//arff//" + t + ".arff");
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
 	     
 		}
   	if(meth.equals("NB")){
   		NaiveBayes classifier1 = new NaiveBayes();
// 		  String[] options1=weka.core.Utils.splitOptions("-I "+RFI );
// 		  classifier1.setOptions(options1);
   	 File inputFile1 = new File(systemPath + "/temp/"+meth+"/"+DDmeth+"//FT" + x + ".arff");
		
//    System.out.println(systemPath + "//arff//" + t + ".arff");
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
 	     
 		}

      
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
  
 
  
  
  public static void main(String[] args)
    throws Exception
  {

	  String intputFile = args[0];
	  String methmethnum= args[1];
//	  String intputFile = "C:\\Users\\Administrator\\eclipse-workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\FeaPlus1\\Result_file\\1563867809";
		


	  BufferedReader br = new BufferedReader(new FileReader(intputFile+"/Des.txt"));
	    
	    
	    int linenum=1;
	    String line1;
	    String[] name=null;
	    String[] meth=null;
	    String[] canshu_a=null;
	    String DDem="";
	    String DDmeth="";
	    String DDnum="";
	    int fold=1;
	    while (br.ready())     	
	      {
	    	
	   line1 = br.readLine();
	    if(linenum==1&&!line1.isEmpty()) {name=line1.trim().split(",");}
	    if(linenum==9&&!line1.isEmpty()) {meth=line1.trim().split(",");}
	    if(linenum==10&&!line1.isEmpty()) { canshu_a=line1.trim().split(",");}
	    if(linenum==11&&!line1.isEmpty()) { fold=Integer.parseInt(line1.trim());}
	    if(linenum==12&&!line1.isEmpty()) {  DDem=line1.trim();}
        if(linenum==13&&!line1.isEmpty()) {  DDmeth=line1.trim().split(",")[0];  DDnum=line1.trim().split(",")[1];}
	    linenum++;
	      }
	    SVMc=canshu_a[0];
	    SVMg=canshu_a[1];
	    RFI= canshu_a[2];
	    br.close();
	    int n=0;
	     if(DDnum.equals("all")) {n=name.length;}else {n=Integer.parseInt(DDnum);if(n>name.length) {n=name.length;}}
    
    
   
    try
    {
      Train_FT rl = new Train_FT();
    

//      for(int methnum=0;methnum<meth.length;methnum++) {
      BufferedWriter bwf=new BufferedWriter(new FileWriter(intputFile+"/temp/"+methmethnum+"/Result_"+DDmeth+".txt"),5*1024*1024);
      for (int m = 1; m < n + 1; m++) {
//    	  System.out.print(name[(m - 1)]);
        rl.Train(m, name[(m - 1)], intputFile,n,bwf,methmethnum,fold,DDmeth);
//        System.out.print(name[(m - 1)]);
      }
      bwf.close();
      
   
    
     
      
   
      
      
      
      
	
      BufferedWriter bw1 = new BufferedWriter(new FileWriter(intputFile+"/temp/"+"/Result_"+methmethnum+".csv"), 5242880);
      bw1.write("Training Model (10-fold cross validation");
		bw1.newLine();
		bw1.write("Fea,SE,SP,ACC,MCC,ROC Area,PRC Area,TP,TN,FP,FN,F-Measure");
		bw1.newLine();
		
	    rl.initList(intputFile+"/temp/"+methmethnum+"/Result_"+DDmeth+".txt");
	    
		for (int i = 0; i < sublist.size(); i++) {
			

	    	bw1.write("FT"+(i+1)+"_"+methmethnum+","+sublist.get(i));
	     	
			bw1.newLine();
		}
		sublist.clear();
	    bw1.close();
//    }
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
  }
}
