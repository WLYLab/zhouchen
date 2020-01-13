
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
import weka.classifiers.functions.LibSVM;
import weka.classifiers.trees.J48;
import weka.core.Instances;
import weka.core.converters.ArffLoader;

@SuppressWarnings("unused")
public class Integrate_10foldcross_Mvote//集成十折交叉的多数投票
{
	 static String input ="/AIP2000.csv";//输入文件
	 private static String systemPath1= "C:\\Users\\Administrator\\Desktop\\Peptides_optim2\\Anti-inflammatory_Peptides\\1\\Test1000";
	public static void main(String[] args) throws Exception {
		BufferedWriter bw1=new BufferedWriter(new FileWriter(systemPath1+"/Result_AIP.csv"),5*1024*1024);//systemPath+"/6.arff 
		bw1.write("Training Model (10-fold cross validation");
		bw1.newLine();
		bw1.write("置信度,SE,SP,ACC,MCC,TP,TN,FP,FN,F-Measure");
		bw1.newLine();
		
		  for(double YZ=0.3;YZ<=0.5;YZ=YZ+0.02) {
			  BufferedReader br = new BufferedReader(new FileReader(systemPath1+input));
		  double r1=0;
		  double r2=0;
		  double r3=0;
		  double r4=0;
	      while (br.ready())
	      {
	      
	        String line1 = br.readLine();
	        String []line=line1.split(",");
//	        System.out.println(line[0]);
		   		  if(line[0].equals("0")) 
		   		  {
		   		  if(Double.parseDouble(line[2])<=YZ)//如果预测值和答案值相等（测试语料中的分类列提供的须为正确答案，结果才有意义）
		   			{r1++;}//正确值加1
		   		  else//如果预测值和答案值相等（测试语料中的分类列提供的须为正确答案，结果才有意义）
		   			{r2++;}//正确值加1
		   		  }
		   			//System.out.println(a+"   "+b+"   "+c);
		   			else
		   		  {
		   			if(Double.parseDouble(line[2])<=YZ)//如果预测值和答案值相等（测试语料中的分类列提供的须为正确答案，结果才有意义）
		   			{r4++;}//正确值加
		   			else//如果预测值和答案值相等（测试语料中的分类列提供的须为正确答案，结果才有意义）
		   			{r3++;}//正确值加1
		   			
		   		  }
	      }
	      br.close();
	      bw1.write(1-YZ+","+(r1/(r1+r2))+","+(r3/(r3+r4))+","+((r1+r3)/(r1+r2+r3+r4))+","+((r3*r1-r2*r4)/(Math.sqrt((r1+r2)*(r1+r4)*(r3+r2)*(r3+r4))))
	    		  +","+(r1)+","+(r3)+","+(r4)+","+(r2)+","+(2*r1/(2*r1+r2+r4)));
			bw1.newLine();
//	        System.out.println("");
//	   		System.out.println("SVM classification TP:"+(r1));
//	   		System.out.println("SVM classification TN:"+(r3));
//	   		System.out.println("SVM classification FP:"+(r4));
//	   		System.out.println("SVM classification FN:"+(r2));
//	   		System.out.println("SVM classification SN:"+(r1/(r1+r2)));
//	   		System.out.println("SVM classification SP:"+(r3/(r3+r4)));
//	   		System.out.println("SVM classification ACC:"+((r1+r3)/(r1+r2+r3+r4)));
//	   		System.out.println("SVM classification F度量:"+(2*r1/(2*r1+r2+r4)));
//	   		System.out.println("SVM classification MCC:"+((r3*r1-r2*r4)/(Math.sqrt((r1+r2)*(r1+r4)*(r3+r2)*(r3+r4)))));

	    }
		 
		  bw1.close();
	}
}

	