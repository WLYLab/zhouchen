

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import weka.classifiers.Classifier;
import weka.classifiers.Evaluation;
import weka.classifiers.bayes.NaiveBayes;
import weka.core.OptionHandler;
import weka.core.SerializationHelper;
import weka.core.Utils;
import weka.classifiers.functions.LibSVM;
import weka.classifiers.trees.J48;
import weka.classifiers.trees.RandomForest;
import weka.core.Instances;
import weka.core.converters.ArffLoader;

@SuppressWarnings("unused")
public class WekaTraining_ACP
{  
//	static int x=0;//特征数
	private static String systemPath= "C:\\Users\\Administrator\\Desktop\\Peptides_optim2\\Anti-cancer_Peptides\\New//";
	static int n=40;//特征数
    static String[][] a=new String[100000][(n+1)];
    static String[][] ap=new String[100000][(n+1)];
    static int[] b=new int[1];
    static ArrayList<String> list = new ArrayList<String>();
    static ArrayList<String> canshu = new ArrayList<String>();
    public void BufferW_arff(int a,BufferedWriter bw2) {
   	 try {
			  
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
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
   }
    public void Train(String t,String systemPath, BufferedWriter bw2,int x) {//,String t,String f) {//int x,BufferedWriter bw1
        try {  
        	
       //svm 参数
       // LibSVM classifier1 = new LibSVM();
        RandomForest classifier1= new  RandomForest();//分类器：NB  J48 Logistic  SVM   RF
        File inputFile1 = new File(systemPath+"//arff//"+t+x+".arff");//训练语料文件
        ArffLoader atf1 = new ArffLoader();
        atf1.setFile(inputFile1);
        Instances instancesTrain1 = atf1.getDataSet(); // 读入训练文件
        instancesTrain1.setClassIndex(instancesTrain1.numAttributes()-1);        
        //svm 参数
//        String[] options1=weka.core.Utils.splitOptions(canshu.get(x));
//		classifier1.setOptions(options1);//设置训练器参数
        int sum=instancesTrain1.numInstances();//测试语料实例数
        classifier1.buildClassifier(instancesTrain1);     
        Evaluation eval1 = new Evaluation(instancesTrain1);
        eval1.crossValidateModel(classifier1, instancesTrain1, 10, new Random(1));

     
      SerializationHelper.write((systemPath+"//Modelarff//"+t+x+".model"), classifier1);//建立模型
//      bw2.write(canshu.get(x-1));
//      bw2.newLine();
      bw2.write(eval1.toClassDetailsString());
      bw2.newLine();
      bw2.write(eval1.toMatrixString());
      bw2.newLine();
//      System.out.println( eval1.toClassDetailsString());
//      System.out.println( eval1.toMatrixString());
//        c++;
//        System.out.println(c);
    
      //输出roc曲线参数
      BufferedWriter bw=new BufferedWriter(new FileWriter(systemPath+"//ROCarff//"+t+x+".csv"),5*1024*1024);       
 		    
		    {
	           for(int i = 0;i<sum;i++)//测试分类结果
	        	   
	   		 {    String[] a3=eval1.predictions().get(i).toString().split("\\ ");
	        	   a[i][x-1]=a3[2];
	   		       ap[i][x-1]=a3[5];
	   		       a[i][n]=a3[1];
	   		       ap[i][n]=a3[1];
	   		       String csv=a3[1]+","+a3[2]+","+a3[5];
	   		       bw.write(csv);
	   		       bw.newLine();
//	   		    System.out.println(eval1.predictions().get(i).toString());
	   		 }
	           b[0]=sum;
 		    }
 	bw.close();	    
// 	x++;
// 	
 	
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        };
}
    public void Train_ID(String t,String systemPath, BufferedWriter bw2,int x) {//,String t,String f) {//int x,BufferedWriter bw1
        try {  
        	
        	  File inputFile1 = new File(systemPath+"/arffTT/"+t+x+".arff");//测试语料文件
      	   	 ArffLoader atf1 = new ArffLoader();
      	   	 atf1.setFile(inputFile1);
      	   	 Instances instancesTest1 = atf1.getDataSet(); // 读入测试文件    
      	   
              Classifier classifier1=(Classifier)weka.core.SerializationHelper.read(systemPath+"/Modelarff/"+t+x+".model");
      	     instancesTest1.setClassIndex(instancesTest1.numAttributes()-1); //设置分类属性所在行号（第一行为0号）,instancesTest.numAttributes()可以取得属性总数 
      	     int sum=instancesTest1.numInstances();//测试语料实例数
      	     Evaluation eval1 = new Evaluation(instancesTest1);
      	     eval1.evaluateModel(classifier1, instancesTest1);

     
//      SerializationHelper.write((systemPath+"//test///Modelarff//"+t+".model"), classifier1);//建立模型
////      bw2.write(canshu.get(x-1));
//      bw2.newLine();
      bw2.write(eval1.toClassDetailsString());
      bw2.newLine();
      bw2.write(eval1.toMatrixString());
      bw2.newLine();
//      System.out.println( eval1.toClassDetailsString());
//      System.out.println( eval1.toMatrixString());
//        c++;
//        System.out.println(c);
    
      //输出roc曲线参数
//      BufferedWriter bw=new BufferedWriter(new FileWriter(systemPath+"/ROCarfft//"+t+x+".csv"),5*1024*1024);       
 		    
		    {
	           for(int i = 0;i<sum;i++)//测试分类结果
	        	   
	   		 {    String[] a3=eval1.predictions().get(i).toString().split("\\ ");
	        	   a[i][x-1]=a3[2];
	   		       ap[i][x-1]=a3[5];
	   		       a[i][n]=a3[1];
	   		       ap[i][n]=a3[1];
	   		       String csv=a3[1]+","+a3[2]+","+a3[5];
//	   		       bw.write(csv);
//	   		       bw.newLine();
//	   		    System.out.println(eval1.predictions().get(i).toString());
	   		 }
	           b[0]=sum;
 		    }
// 	bw.close();	    
// 	x++;
// 	
 	
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        };
}
    
   
    
    /** 数组读出处理
     * 
     * @param a
     *            数组
     */
    
    public void DealS(String f[][], BufferedWriter bw,int c) {
        try {      	
		        for(int j=0;j<c;j++)
		        	for(int i=0;i<=n;i++){
		        	if(i==n) 
		        	{String y=a[j][i];
		        		bw.write(y,0,y.length()-2);
		        		bw.newLine();	        		
		        	}
		        	else
		            {bw.write(a[j][i]+",");}

	    }
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    public void DealSp(String f[][], BufferedWriter bw,int c) {
        try {      	
		        for(int j=0;j<c;j++)
		        	for(int i=0;i<=n;i++){
		        	if(i==n) 
		        	{String y=ap[j][i];
		        		bw.write(y,0,y.length()-2);
		        		bw.newLine();	        		
		        	}
		        	else
		            {bw.write(ap[j][i]+",");}

	    }
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
	  /*读入参数
		 */
	  public void initcanshu(String fileName)
	  {
	    try
	    {
	      @SuppressWarnings("resource")
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
	public static void main(String[] args) throws Exception {
		


		try
		{    


			
//			
//			String[] name_bla={"AAC","AAINDEX","BIT20","BIT21","BLOSUM62","IT","OLP","ZSCALE"};
//		    	String[] name_bla2={"EAAC","EGAAC"};
//		        String[] name_com={"AAC(N=1)","AAC(N=2)","188D","400D","CKSAAGP","CKSAAP","CTD","CTDC",
//		     		   "CTDD","CTDT","CTriad","DDE","DPC","GAAC"
//		     		   ,"GDPC","GTPC","KSCTriad","TPC"};
			WekaTraining_ACP rl=new WekaTraining_ACP();
		
//			//训练集
//			rl.initcanshu(systemPath+"/RFOP_arff/canshu_arff.txt");
//			BufferedWriter bw2=new BufferedWriter(new FileWriter(systemPath+"/Result_arff.txt"),5*1024*1024);
//		    BufferedWriter bw=new BufferedWriter(new FileWriter(systemPath+"/FTc.arff"),5*1024*1024); 	  
//		    BufferedWriter bw0=new BufferedWriter(new FileWriter(systemPath+"/FTp.arff"),5*1024*1024);
//			rl.BufferW_arff(n, bw);
//			rl.BufferW_arff(n, bw0);
//			 for(int m=1;m<=11;m++)
//			 {
//				 {rl.Train("bit20nt",systemPath,bw2,m);}
//			 }
//			 for(int m=12;m<=22;m++)
//			 {
//				 {rl.Train("bit21nt",systemPath,bw2,m);}
//			 }
//			 for(int m=23;m<=33;m++)
//			 {
//				 {rl.Train("OVnt",systemPath,bw2,m);}
//			 }
//			 rl.Train("CTD",systemPath,bw2,34);
//			 for(int m=35;m<=38;m++)
//			 {
//				 {rl.Train("GAP",systemPath,bw2,m);}
//			 }
//			 rl.Train("400D",systemPath,bw2,39);
//			 rl.Train("Ngram(N=1)",systemPath,bw2,40);
//		    rl.DealS(a, bw,b[0]);
//		    rl.DealSp(ap, bw0,b[0]);
////		    canshu.clear();
//		    bw0.close();
//		    bw.close();
//		    bw2.close();
		    
			
			
			  
		    BufferedWriter bw0=new BufferedWriter(new FileWriter(systemPath+"/ACP.arff"),5*1024*1024);
			
//			 for(int m=1;m<=11;m++)
//			 {
//				 {bw0.write("\"bit20nt"+m+"\",");}
//			 };
//			 for(int m=12;m<=22;m++)
//			 {
//				 {bw0.write("\"bit21nt"+m+"\",");}
//			 }
//			 for(int m=23;m<=33;m++)
//			 {
//				 {bw0.write("\"OVnt"+m+"\",");}
//			 }
//			 bw0.write("\"CTD34");
//			 for(int m=35;m<=38;m++)
//			 {
//				 {bw0.write("\"GAP"+m+"\",");}
//			 }
//			 bw0.write("\"400D39"+"\",");
//			 bw0.write("\"Ngram(N=1)40"+"\"");
				String []meth={ "Bit20", "Bit21", "OV", "IT","KSkipN", "Ngram"};
				String []cut={ "NT", "CT", "NTCT"};
			 
			   for(int m=1;m<100;m++)
			    {
			    	
			    	if(m<=1)
			    	{ bw0.write("\"Ngram(N=1)"+m+"\",");}
			     	else if(m<=2)
			       	{ bw0.write("\"Ngram(N=2)"+m+"\",");}
			      	 else if(m<=3)
			    	{ bw0.write("\"CTD"+m+"\",");}
			      	else if(m<=7)
			    	{ bw0.write("\"GAP"+m+"\",");}
			     	else if(m<=8)
			       	{ bw0.write("\"400D"+m+"\",");}
			      	 else if(m<=9)
			    	{ bw0.write("\"188D"+m+"\",");}
	
			     	else if(m<=99)
			       	{int c=(m+2)%6;//方法
			       	 int c1=((m+2)/6-2)%3;//NT CT NT CT
			       	 int c3=((m+2)-12)/18+1;//
			       	 
			       	bw0.write("\""+meth[c]+cut[c1]+"("+c3+")"+m+"\",");
			     		}	
//			    	rl.Train(m,"FT",systemPath,bw2);
	            }
		   
		    bw0.close();
		   
//			//测试集
//		    BufferedWriter bw2=new BufferedWriter(new FileWriter(systemPath+"/Result_arff_ID.txt"),5*1024*1024);
//		    BufferedWriter bw=new BufferedWriter(new FileWriter(systemPath+"/TTc.arff"),5*1024*1024); 	  
//		    BufferedWriter bw0=new BufferedWriter(new FileWriter(systemPath+"/TTp.arff"),5*1024*1024);
//			rl.BufferW_arff(n, bw);
//			rl.BufferW_arff(n, bw0);
//			 for(int m=0;m<name_com.length;m++)
//			 {
//				 {rl.Train_ID(name_com[m],systemPath,bw2);}
//			 }
//			 for(int m=0;m<name_bla.length;m++)
//				 for(int n1=1;n1<11;n1++)
//			 {
//				 {rl.Train_ID(name_bla[m]+"NT("+n1+")",systemPath,bw2);}
//			 }
//			 for(int m=0;m<name_bla2.length;m++)
//				 for(int n1=5;n1<11;n1++)
//			 {
//				 {rl.Train_ID(name_bla2[m]+"NT("+n1+")",systemPath,bw2);}
//			 }
//			 
//			 for(int n1=0;n1<4;n1++)
//			 {
//				 {rl.Train_ID("GAP(g="+n1+")",systemPath,bw2);}
//			 }	    
//		    rl.DealS(a, bw,b[0]);
//		    rl.DealSp(ap, bw0,b[0]);
//		    canshu.clear();
//		    bw0.close();
//		    bw.close();
//		    bw2.close();
		   
		   
		
		} catch (IOException e)
		    {
	        	// TODO Auto-generated catch block
		        e.printStackTrace();
	        }
  		
	}
	
}
