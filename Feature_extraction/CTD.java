package Test;


import weka.core.Attribute;
import weka.core.DenseInstance;
import weka.core.Instances;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * Generates an weka.core.Instances object with different attribute types.
 *
 * @author FracPete (fracpete at waikato dot ac dot nz)
 * @version $Revision$
 */
public class CTD
{     
	  private static String systemPath="f:"; 
	  public static void main(String[] args) throws IOException 
	  {  
    
        final int x=126;//attribute!!
		BufferedWriter bw5=new BufferedWriter(new FileWriter(systemPath+"/haha.arff"),5*1024*1024);//systemPath+"/6.arff"
		BufferedReader br3=new BufferedReader(new FileReader(systemPath+"/test.txt"));
		int g=1;//g-gaps:g=?
		int t=g+1;
		String line1; 
		while((line1=br3.readLine())!=null)
		{  
			float[] a=new float[x];//i
			if(line1.startsWith(">")||line1.startsWith(" "))
			{
			continue;
			}
			
			int featureNum=line1.length();
		   
		   for (int i = 1; i <= featureNum-t; i++)


		   {
			String line5 = line1.substring(i-1,i)+line1.substring(i+g,i+t);
		
			System.out.println(line5);
			{	
				BufferedReader br4=new BufferedReader(new FileReader(systemPath+"/example126.txt"));
				
				for (int j = 0; j <=x-1; j++)
				{ 
					String  line2=br4.readLine(); 
					//System.out.println(line2);
			        {
			        	if (line5.equals(line2)) 
			               {  
			        	      a[j]++;
			        	      //bw5.write("1,");
			                  //System.out.println(0);
		                    }
			         
			        	else
			               {
				              //System.out.println(1);
			        	     // bw5.write("0,");
				          continue;
			                }	
			        	
			         } //System.out.println(a[j]);
			         
			    }
				
			}
			
			 bw5.flush();//System.out.println(a[j]);		
		}
		   for (int m = 0; m <=x-1; m++)
		    {
			   float b=a[m];
			   float c=b/(featureNum-t);
			   a[m]=c;
			  
			  // System.out.println(a[m]);
		    }
		    // List list = Arrays.asList(a);
		    // String[] arr = (String[])list.toArray(new String[a.length]);
		
		   //  bw5.write(str);
		   /* for (int j = 1; j <= 125; j++)
		     a[j]=a[j]/featureNum;*/
		   String h=Arrays.toString(a); 
		     String s=h.replaceAll(".0]","" );
		     String p=s.replaceAll("0.0,","0," );
		     String m=p.replaceAll("]","" );	    
		     String b=m.replaceAll(" ","" );
		     bw5.write(b,1,b.length()-1);
             //for (int j = 1; j <= 2; j++){    System.out.println(a[j]);  }
             //System.out.println("一行对比结束");
		  
		    bw5.newLine();
		 
		}
		bw5.close();

		
      }
	
}

