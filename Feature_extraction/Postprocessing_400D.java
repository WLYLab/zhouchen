package Test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

public class Postprocessing_400D //����99��������400D��188D���

	{
	private static String systemPath="C:\\Users\\Administrator\\Desktop";
	
	  public static void main(String[] args) throws IOException {  
  
   
		BufferedWriter bw5=new BufferedWriter(new FileWriter(systemPath+"/400D391.arff"),5*1024*1024);//����ļ�
		BufferedReader br3=new BufferedReader(new FileReader(systemPath+"/400D39.arff"));//�����ļ�
		String line1;
		int i=1;
		int g=205;//������
		while((line1=br3.readLine())!=null)
		{  
			if(line1.startsWith("@"))
			{
				if(line1.startsWith("@attribute class "))
				{
					String line5 = line1.replaceAll("positive", "0");
					String t = line5.replaceAll("negative", "1");
					bw5.write(t);
				    bw5.newLine();
				}
				else 
				{
					bw5.write(line1);
					bw5.newLine();
					}
				
			}
		   
			else {
				if(i<=g)
				{
					String line5 = line1.replaceAll("positive", "0");//��ǩֵ
			        bw5.write(line5);
			        i++;
			     }
				else 
				{
					String line5 = line1.replaceAll("positive", "1");//��ǩֵ
				    bw5.write(line5);	
			        i++;
				}
			    bw5.newLine();
			    bw5.flush();
		        }
		}
		

		br3.close();

		bw5.close();
}
}

