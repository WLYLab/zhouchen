
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
public class Deal_Txttofasta {
	private static String systemPath="D:\\99server\\Python\\ten\\TT2000\\deal2000/";
	
	  public static void main(String[] args) throws IOException {  
		//int featureNum=400;    
//		  String pattern =  "[^ACDEFGHIKLMNPQRSTVWY\n]";
//			Pattern r = Pattern.compile(pattern);
//			Matcher m;

		
		BufferedWriter bw5=new BufferedWriter(new FileWriter(systemPath+"/3.txt"),5*1024*1024);//systemPath+"/6.arff"				
        BufferedReader br=new BufferedReader(new FileReader(systemPath+"/ACP82.txt"));
		
		String line1;
		String line2;
		int x=1;
		while((line1=br.readLine())!=null )
		{ 
//			if(line1.length()<11) {continue;}
//			m = r.matcher(line1);
////			boolean rs = m.matches();
////		    System.out.println(rs);
//		
//			if(m.matches()) {continue; }
//			else {x++;}
			line2 = line1;
			bw5.write(">"+x+"|0");
			bw5.newLine();
//			bw5.write(">"+x+"|0");
//			bw5.newLine();
			bw5.write(line2);
			bw5.newLine();
			x++;
			
			
			
		}
		br.close();
		BufferedReader br3=new BufferedReader(new FileReader(systemPath+"/ACP1550.txt"));
		
		String line3;
		String line5;
		int i=1;
		while((line3=br3.readLine())!=null )
		{  
//			if(line3.length()<11) {continue;}
//			m = r.matcher(line3);
//			if(m.matches()) {continue; }
//			else {i++;}
			line5 = line3;
			bw5.write(">"+i+"|1");
			bw5.newLine();
			bw5.write(line5);
			bw5.newLine();
			i++;
				
			
		}
		br3.close();
		bw5.close();
}
}