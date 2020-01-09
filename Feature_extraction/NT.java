package Test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class NT {
	public static String reverse(String str){  
        return new StringBuilder(str).reverse().toString();  
    }
	private static String systemPath="f:"; 
	public static void main(String[] args) throws IOException 
	{   NT_CT a=new NT_CT();
	    int g=9;//取几位数
	    int h=164;//文件名字
		// TODO Auto-generated method stub
		BufferedWriter bw1=new BufferedWriter(new FileWriter(systemPath+"NT"+g+"_"+h+".txt"),5*1024*1024);
		BufferedWriter bw2=new BufferedWriter(new FileWriter(systemPath+"NT"+(g+1)+"_"+h+".txt"),5*1024*1024);
		BufferedWriter bw3=new BufferedWriter(new FileWriter(systemPath+"NT"+(g+2)+"_"+h+".txt"),5*1024*1024);///ACP"+h+
		BufferedReader br3=new BufferedReader(new FileReader(systemPath+"acp_"+h+".txt"));//ACP"+h+"
		String line0;
		
		while((line0=br3.readLine())!=null)
		{  int i=line0.length();
			if(line0.startsWith(">")||line0.startsWith(" "))
			{ bw1.write(line0);
		    bw1.newLine();
		    bw2.write(line0);
		    bw2.newLine();
		    bw3.write(line0);
		    bw3.newLine();
		    continue;
			
			}
		
			String line1=line0.substring(0,g);
			String line2=line0.substring(0,(g+1));
			String line3=line0.substring(0,(g+2));
		    bw1.write(line1);
		    bw1.newLine();
		    bw2.write(line2);
		    bw2.newLine();
		    bw3.write(line3);
		    bw3.newLine();
			
			bw2.flush();
	   }
		bw1.close();
		bw2.close();
		bw3.close();
		br3.close();

    }
}