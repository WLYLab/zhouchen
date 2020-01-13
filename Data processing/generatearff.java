


import weka.core.Attribute;
import weka.core.DenseInstance;
import weka.core.Instances;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
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
public class generatearff {
	private static String systemPath="C:\\Users\\Administrator\\Desktop\\Train\\";
	static ArrayList<String> list5 = new ArrayList<String>();
	static ArrayList<String> list6 = new ArrayList<String>();
	static ArrayList<String> list3 = new ArrayList<String>();
	static ArrayList<String> list4 = new ArrayList<String>();
	static ArrayList<String> list_title = new ArrayList<String>();
	    public void initList_class(String fileName)
		  {
		    try
		    {
		      @SuppressWarnings("resource")
			BufferedReader br = new BufferedReader(new FileReader(fileName));
		      while (br.ready())
		      {
		       
		        String title = br.readLine();
		        String line1 = br.readLine();
		      
		    
		        if (title.split("\\|")[1].equals("1")) {
		          list_title.add("1");
		        } else {
		          list_title.add("0");
		        }
		      }
		    }
		    catch (Exception e)
		    {
		      e.printStackTrace();
		    }
		  }
	 
	  @SuppressWarnings("unused")
	public static void main(String[] args) throws IOException {  

		BufferedReader br3=new BufferedReader(new FileReader(systemPath+"/FTc/FT28.arff"),5*1024*1024);//特征1
		BufferedReader br4=new BufferedReader(new FileReader(systemPath+"/FTp/FT77.arff"),5*1024*1024);//特征2
//		BufferedReader br5=new BufferedReader(new FileReader(systemPath+"//pcpse43.arff"),5*1024*1024);//特征3
//		BufferedReader br6=new BufferedReader(new FileReader(systemPath+"//scpse44.arff"),5*1024*1024);//特征3
		String line3;
		String line4;
		generatearff rl=new generatearff();
//		rl.initList_class(systemPath+"/ACP500.txt");
		int feature=0;
		while((line3=br3.readLine())!=null) 
		{   
			if(line3.startsWith("@")||line3.startsWith(" ")) {
				if(line3.startsWith("@attribute")) 
				{feature++;}
				continue;
			  }
			else if (line3.isEmpty()){continue;}
			else {line4=line3.substring(0, line3.length()-1);
				list3.add(line4);
				//System.out.println(line4);
				}
		}
		br3.close();
		
	
		String line5;
		String line51;
		while((line5=br4.readLine())!=null) 
		{   
			if(line5.startsWith("@")||line5.startsWith(" ")) {
				if(line5.startsWith("@attribute")) 
				{feature++;}
				continue;
			  }
			else if (line5.isEmpty()){continue;}
			else {
				line51=line5.substring(0, line5.length());
				list4.add(line51);
				//System.out.println(line5);
				}
		}

		br4.close();
		
//		String line6;
//		String line61;
//		while((line6=br5.readLine())!=null) 
//		{   
//			if(line6.startsWith("@")|line6.startsWith(" ")) {
//				if(line6.startsWith("@attribute")) 
//				{feature++;}
//				continue;
//			  }
//			else if (line6.isEmpty()){continue;}
//			else {
//				line61=line6.substring(0, line6.length()-1);
//				list5.add(line61);
//				//System.out.println(line6);
//				}
//		}
//		br5.close();
//		
//		
//		String line7;
//		String line71;
//		while((line7=br6.readLine())!=null) 
//		{   
//			if(line7.startsWith("@")|line7.startsWith(" ")) {
//				if(line7.startsWith("@attribute")) 
//				{feature++;}
//				continue;
//			  }
//			else if (line7.isEmpty()){continue;}
//			else {
//				line71=line7.substring(0, line7.length()-1);
//				list6.add(line71);
//				//System.out.println(line6);
//				}
//		}
//		br6.close();
		
		BufferedWriter bw5=new BufferedWriter(new FileWriter(systemPath+"/rong1.arff"),5*1024*1024);//systemPath+"/6.arff"
		bw5.write("@relation Cpp");
	    int filenum=2;
		bw5.newLine();
		for (int i = 1; i <= feature-filenum; i++) {
			bw5.write("@attribute Feature" + i + " real");
			bw5.newLine();	
		}
	
		bw5.write("@attribute class ");
		bw5.write("{" + "0"+ "," +"1" + "}");
		bw5.newLine();
		bw5.write("@data");
		bw5.newLine();
	
		for(int i=0;i<list3.size();i++) {
			//System.out.println(list1.get(i).toString()+list2.get(i).toString()+list3.get(i).toString());
//			String all=list1.get(i).toString()+list2.get(i).toString()+list3.get(i).toString()+list4.get(i).toString()+list_title.get(i).toString();
			String all=list3.get(i).toString()+list4.get(i).toString();
			bw5.write( all);
			bw5.newLine();
		}
	
		
		
		bw5.close();
		}
}
//}
