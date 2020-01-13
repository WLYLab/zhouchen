

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class MRMR_ReSort {
	
	 public void initList(String fileName)
	  {
	    try
	    {
	      @SuppressWarnings("resource")
		BufferedReader br = new BufferedReader(new FileReader(fileName));
	      while (br.ready())
	      {
	       
	       
	        String line1 = br.readLine();
	        if(line1.startsWith("@")) {continue;}
	        listsample.add(line1);
	       
	        
	    
	        
	      }
	    }
	    catch (Exception e)
	    {
	      e.printStackTrace();
	    }
	  }
	  public void bufferW_arff(int a, BufferedWriter bw2)
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
	  static int all = 40;//������
	 static ArrayList<String> list = new ArrayList<String>();
	 static ArrayList<String> listsample = new ArrayList<String>();	                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                           
	  private static String systemPath="C:\\Users\\Administrator\\Desktop\\Tagyi";
	    public static void main(String[] args) throws IOException {  
		// TODO Auto-generated method stub
	    	MRMR_ReSort rs=new MRMR_ReSort();
	    	rs.initList(systemPath+"/FT.arff");//arff�ļ�
		BufferedReader br=new BufferedReader(new FileReader(systemPath+"/change1.arff"));//mrmr������ļ�
		
      String line;
	
		while((line=br.readLine())!=null)
	      {
	      
	
	        String[] a=line.split("\\ ");
	        list.add(a[2]);
	        //System.out.println(a[2]);
	        //rs.changesort( a[2]+".txt", a[0]+".txt");
	    	
			
	      }
		for(int x=1;x<=all;x++) {
		BufferedWriter bw=new BufferedWriter(new FileWriter(systemPath+"/FT1/arff/FT"+x+".arff"),5*1024*1024);//systemPath+"/6.arff"
		
		rs.bufferW_arff(x, bw);
		
	
		for(int m=0;m<listsample.size();m++)
	      {	String str="";
	         String str1="";
		     str1=listsample.get(m);
			String[] b=str1.split("\\,");
			//System.out.println(b.length);
	      for(int i=0;i<x;i++)
	      {
	    	  int listnum=Integer.parseInt(list.get(i))-1;
	    	  System.out.println(listnum+1);
	    	  str=str+b[listnum]+",";
	      }
	      str=str+b[b.length-1];
	      bw.write(str);
	      bw.newLine();
	      }
		
		bw.close();
		}
		
		br.close();

	}

}
