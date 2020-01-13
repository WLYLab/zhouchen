


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
public class generatecsv {
	private static String systemPath="D:\\99server\\Python\\ten\\new\\fin/TT/";
	static ArrayList<String> list7 = new ArrayList<String>();
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
//		        String line1 = br.readLine();
		      
		    
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
//		String name="F6F1B2z5";
		String[] name_arff= {"TT36","TT107","AAC(N=2)","CTDC","188D","ZSCALENT(10)"};
		for(int nami=0;nami<1;nami++) {
			
			String name="126";
			int a1=Integer.parseInt( name.substring(2, 3));
//			int b1=Integer.parseInt( name.substring(3, 4));
//			int c1=Integer.parseInt( name.substring(4, 5));
//			int d1=Integer.parseInt( name.substring(0, 1));
		BufferedReader br3=new BufferedReader(new FileReader(systemPath+""+name_arff[nami]+".csv"),5*1024*1024);//特征1
		BufferedReader br4=new BufferedReader(new FileReader(systemPath+""+name_arff[nami+1]+".csv"),5*1024*1024);//特征2
		BufferedReader br5=new BufferedReader(new FileReader(systemPath+""+name_arff[nami+a1-1]+".csv"),5*1024*1024);//特征3
//		BufferedReader br6=new BufferedReader(new FileReader(systemPath+""+name_arff[nami+b1-1]+".csv"),5*1024*1024);//特征4
//		BufferedReader br7=new BufferedReader(new FileReader(systemPath+""+name_arff[nami+c1-1]+".csv"),5*1024*1024);//特征5
		BufferedReader brc=new BufferedReader(new FileReader(systemPath+"//TT36class.csv"),5*1024*1024);//类
		String line3;

		generatecsv rl=new generatecsv();
		int feature=0;
		int a=0;
		while((line3=br3.readLine())!=null) 
		{   

				list3.add(line3+",");
				a=line3.split(",").length;
				
//				System.out.println(feature);
				
		}
		br3.close();
		
		int b=0;
		String line4;
		while((line4=br4.readLine())!=null) 
		{   

			list4.add(line4+",");
			b=line4.split(",").length;
		}

		br4.close();
		
		
		int c=0;
		String line5;
		while((line5=br5.readLine())!=null) 
		{   

			list5.add(line5+",");
			c=line5.split(",").length;
		}
		br5.close();
		
		int d=0;
//		String line6;
//		while((line6=br6.readLine())!=null) 
//		{   
//
//			list6.add(line6+",");
//			d=line6.split(",").length;
//		}
//		br6.close();
		
		int e=0;
//		String line7;
//		while((line7=br7.readLine())!=null) 
//		{   
//
//			list7.add(line7+",");
//			e=line7.split(",").length;
//		}
//		br7.close();
		
		feature=a+b+c+d+e;

		String linec;
		while((linec=brc.readLine())!=null) 
		{   
			list_title.add(linec);
		}
		brc.close();
		
		BufferedWriter bw5=new BufferedWriter(new FileWriter(systemPath+"/"+name+".arff"),5*1024*1024);//systemPath+"/6.arff"
		bw5.write("@relation Cpp");
	   
		bw5.newLine();
		for (int i = 1; i <= feature; i++) {
			bw5.write("@attribute Feature" + i + " real");
			bw5.newLine();	
		}
	
		bw5.write("@attribute class ");
		bw5.write("{" + "0"+ "," +"1" + "}");
		bw5.newLine();
		bw5.write("@data");
		bw5.newLine();
		for(int i=0;i<list3.size();i++) {
			
//			String all=list3.get(i).toString()+list4.get(i).toString()+list5.get(i).toString()+list6.get(i).toString()+list_title.get(i).toString();
     
//			String all=list3.get(i).toString()+list4.get(i).toString()+list5.get(i).toString()+list6.get(i).toString()+list7.get(i).toString()+list_title.get(i).toString();
			String all=list3.get(i).toString()+list4.get(i).toString()+list5.get(i).toString()+list_title.get(i).toString();

//			String all=list3.get(i).toString()+list4.get(i).toString()+list_title.get(i).toString();
//			String all=list3.get(i).toString()+list4.get(i).toString()+list5.get(i).toString();
			bw5.write( all);
			bw5.newLine();
		}

		bw5.close();
		
		
		BufferedWriter bw6=new BufferedWriter(new FileWriter(systemPath+"/"+name+".csv"),5*1024*1024);//systemPath+"/6.arff"
		
		for(int i=0;i<list3.size();i++) {
			
//			String all2=list3.get(i).toString()+list4.get(i).toString()+list5.get(i).toString()+list6.get(i).toString().substring(0, list6.get(i).length()-1);
//			String all2=list3.get(i).toString()+list4.get(i).toString()+list5.get(i).toString()+list6.get(i).toString()+list7.get(i).toString().substring(0, list7.get(i).length()-1);
			String all2=list3.get(i).toString()+list4.get(i).toString()+list5.get(i).toString().substring(0, list5.get(i).length()-1);
				     
			bw6.write( all2);
			bw6.newLine();
		}

		bw6.close();
		
        BufferedWriter bw7=new BufferedWriter(new FileWriter(systemPath+"/"+name+"class.csv"),5*1024*1024);//systemPath+"/6.arff"
		
		for(int i=0;i<list_title.size();i++) {
			
			
			bw7.write(list_title.get(i).toString());
			bw7.newLine();
		}

		bw7.close();
		}
		list3.clear();
		list4.clear();
		list5.clear();
		list6.clear();
		list_title.clear();
   }
	  
}
//}
