package Fea_WEB;
import java.io.BufferedInputStream;  
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;  
import java.io.FileInputStream;  
import java.io.FileNotFoundException;  
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.zip.ZipEntry;  
import java.util.zip.ZipOutputStream;
  
/** 
 * 将文件夹下面的文件 
 * 打包成zip压缩文件 
 *  
 * @author admin 
 * 
 */  
public final class csv_to_arff {  
	
	static ArrayList<String> list1 = new ArrayList<String>();
    private csv_to_arff(){}  
    
 
    public static String getRealPath() {
        String realPath = csv_to_arff.class.getClassLoader().getResource("")
                .getFile();
        java.io.File file = new java.io.File(realPath);
        realPath = file.getAbsolutePath();
        try {
            realPath = java.net.URLDecoder.decode(realPath, "utf-8");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return realPath;
    }  
    public static void main(String[] args) throws IOException{  
//    	String systemPath=getRealPath()+"/";
    	csv_to_arff rl=new csv_to_arff();
//    	String intputFile = "C:\\Users\\Administrator\\eclipse-workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\FeaPlus1\\Result_file\\3\\5";
    	 String intputFile = args[0];
    	String[] line_py = null;
  	  String[] linemeth_pynt = null;
  	  String[] cutm= null;
  	  String line1;
  
      BufferedReader br = new BufferedReader(new FileReader(intputFile+"/Read.txt"));
  	int linenum=0;
      while (br.ready())     	
        {
      	linenum++;
     line1 = br.readLine();
    
      if(linenum==2) {line_py=line1.trim().split(",");System.out.println(line1);}
      
      if(linenum==5) { linemeth_pynt=line1.trim().split(",");}
      if(linenum==6) {  cutm=line1.trim().split(":");}
        }
      br.close();
      String CLM=cutm[1].substring(0, 2);
      int CLN = Integer.parseInt(cutm[2].substring(0, 1));
      
      for(int h=0;h<linemeth_pynt.length;h++)
		      
		 { 
      if(linemeth_pynt[h].equals("AAINDEX")||linemeth_pynt[h].equals("BLOSUM62")||linemeth_pynt[h].equals("ZSCALE")) {
    		BufferedReader br3=new BufferedReader(new FileReader( intputFile+"/csv/"+linemeth_pynt[h]+"("+CLM+CLN+")"+"pos.csv"),5*1024*1024);//特征1
        	String line3;
    	
    		int feature=0;
    		while((line3=br3.readLine())!=null) 
    		{   
    			
    				list1.add(line3+",0");
    				String[] dic =  line3.split("\\,"); 
    				feature= dic.length+1;
    		}
    		br3.close();
    		
    		BufferedReader br4=new BufferedReader(new FileReader( intputFile+"/csv/"+linemeth_pynt[h]+"("+CLM+CLN+")"+"neg.csv"),5*1024*1024);//特征1
        	String line4;
    	
    		
    		while((line4=br4.readLine())!=null) 
    		{   
    			
    				list1.add(line4+",1");
    				
    				
    		}
    		br4.close();
    		
    		
    		BufferedWriter bw5=new BufferedWriter(new FileWriter(intputFile+"/arff/"+linemeth_pynt[h]+"("+CLM+CLN+")"+".arff"),5*1024*1024);//systemPath+"/6.arff"
    		bw5.write("@relation Cpp");
    		
    		bw5.newLine();
    		for (int i = 1; i < feature; i++) {
    			bw5.write("@attribute Feature" + i + " real");
    			bw5.newLine();	
    		}
    	
    		bw5.write("@attribute class ");
    		bw5.write("{" + "0"+ "," +"1" + "}");
    		bw5.newLine();
    		bw5.write("@data");
    		bw5.newLine();
    		bw5.newLine();
    		for(int i=0;i<list1.size();i++) {
    			bw5.write(list1.get(i));
    			bw5.newLine();
    		}
    		bw5.close();
          
    		list1.clear();
    	  
      }}
      
      
      
      for(int h=0;h<line_py.length;h++) {
    	BufferedReader br3=new BufferedReader(new FileReader( intputFile+"/csv/"+line_py[h]+"pos.csv"),5*1024*1024);//特征1
    	String line3;
	
		int feature=0;
		while((line3=br3.readLine())!=null) 
		{   
			
				list1.add(line3+",0");
				String[] dic =  line3.split("\\,"); 
				feature= dic.length+1;
		}
		br3.close();
		
		BufferedReader br4=new BufferedReader(new FileReader( intputFile+"/csv/"+line_py[h]+"neg.csv"),5*1024*1024);//特征1
    	String line4;
	
		
		while((line4=br4.readLine())!=null) 
		{   
			
				list1.add(line4+",1");
				
				
		}
		br4.close();
		
		
		BufferedWriter bw5=new BufferedWriter(new FileWriter(intputFile+"/arff/"+line_py[h]+".arff"),5*1024*1024);//systemPath+"/6.arff"
		bw5.write("@relation Cpp");
		
		bw5.newLine();
		for (int i = 1; i < feature; i++) {
			bw5.write("@attribute Feature" + i + " real");
			bw5.newLine();	
		}
	
		bw5.write("@attribute class ");
		bw5.write("{" + "0"+ "," +"1" + "}");
		bw5.newLine();
		bw5.write("@data");
		bw5.newLine();
		bw5.newLine();
		for(int i=0;i<list1.size();i++) {
			bw5.write(list1.get(i));
			bw5.newLine();
		}
		bw5.close();
      
		list1.clear();
    }
		
		
    }  
    
      
} 