





import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;



/**
 * Generates an weka.core.Instances object with different attribute types.
 *
 * @author FracPete (fracpete at waikato dot ac dot nz)
 * @version $Revision$
 */
public class Sample_Frequency
{   static int x=9;
	static double[] a=new double[x];
	static double[] b=new double[x];
	static int seqlength=70;
	static int interval=10;
	static int fastai=1;
	 public void cleara(double[]  a)
	  {
	    for (int n = 0; n < 9; n++) {
	      a[n] = 0.0D;
	    }
	  }

	private static String systemPath="C:\\Users\\Administrator\\Desktop\\ACP\\1/";
	  public static void main(String[] args) throws IOException
	  {  
		  String pattern =  "[^ACDEFGHIKLMNPQRSTVWY\n]";
			Pattern r = Pattern.compile(pattern);
			Matcher m;

			Sample_Frequency rsm=new Sample_Frequency();
//	
//		
//		String []Peptides={ "Anti-angiogenic_Peptides", "Anti-bacterial_Peptides", "Anti-cancer_Peptides", "Anti-inflammatory_Peptides",
//				"Anti-viral_Peptides", "Cell-penetrating_Peptides", "Quorum-Sensing_Peptides" ,"Surface-Binding_Peptides" , 
//			      "Streptavidin-Binding_Peptides", "Signal_Peptides"};
//		//int Peptidenum=Peptides.length;
//		for(int n=9;n<Peptides.length;n++) {//Peptides.length
			String systemPath1=systemPath+"/";
			
	    BufferedWriter bw1=new BufferedWriter(new FileWriter(systemPath1+"/error.txt"),5*1024*1024);//输出错误数据
		BufferedWriter bw2=new BufferedWriter(new FileWriter(systemPath1+"/count.csv"),5*1024*1024);//输出fasta序列长度分布	
		BufferedWriter bw3=new BufferedWriter(new FileWriter(systemPath1+"/fasta1.txt"),5*1024*1024);//输出融合数据	
		BufferedReader br3=new BufferedReader(new FileReader(systemPath1+"/ATB.txt"));//输入fasta正例
		BufferedReader br4=new BufferedReader(new FileReader(systemPath1+"/neg.txt"));//输入fasta负例
		String line1;
		int posmin=30;
		while((line1=br3.readLine())!=null)
		{  
			int featureNum=line1.length();
			
			if(line1.startsWith(">")||line1.startsWith(" ")) 
	    	{
				
	    		continue;
	        }
			else {
				String seq = line1.toString().toUpperCase();
				m = r.matcher( seq);
				if(m.find()&&line1.length()>=10) {
					bw1.write(">"+fastai+"|0");
				    bw1.newLine();
				    fastai++;
				    bw1.write(seq);
			        bw1.newLine();
				}
				else if(line1.length()>=10) {
					bw3.write(">"+fastai+"|0");
				    bw3.newLine();
				    fastai++;
				    bw3.write(seq);
			        bw3.newLine();
			    }
				else {
					bw1.write(">"+fastai+"|0");
				    bw1.newLine();
				    fastai++;
				    bw1.write(seq);
			        bw1.newLine();
			    }
			    if(posmin>featureNum)
			    { posmin=featureNum;}
			    }
			if(featureNum<10) 
	    	{
	    		a[0]++;
	        }
			if(featureNum>=10&&featureNum<20) 
	    	{
	    		a[1]++;
	        }
			if(featureNum>=20&&featureNum<30) 
	    	{
	    		a[2]++;
	        }
			if(featureNum>=30&&featureNum<40) 
	    	{
	    		a[3]++;
	        }
			if(featureNum>=40&&featureNum<50) 
	    	{
	    		a[4]++;
	        }
			if(featureNum>=50&&featureNum<60) 
	    	{
	    		a[5]++;
	        }
		    if(featureNum>=60&&featureNum<70) 
	    	{
	    		a[6]++;
	        }
		    	 	 
		    if(featureNum>=70) 
		    {
		    	a[7]++;
		    }
		    	
		    a[8]++;
		   
		  }
		 String h=Arrays.toString(a);
		 h=h.substring(1, h.length()-1);
		 fastai=1;
		 int negmin=30;
		 while((line1=br4.readLine())!=null)
			{  
				int featureNum=line1.length();
				if(line1.startsWith(">")) 
		    	{
					
		    		continue;
		        }	
				else {
					String seq = line1.toString().toUpperCase();
					m = r.matcher( seq);
					if(m.find()&&line1.length()>=10) {
						bw1.write(">"+fastai+"|1");
					    bw1.newLine();
					    fastai++;
					    bw1.write(seq);
				        bw1.newLine();
					}
					else if(line1.length()>=10) {
						bw3.write(">"+fastai+"|1");
					    bw3.newLine();
					    fastai++;
					    bw3.write(seq);
				        bw3.newLine();
				    }
					else {
						bw1.write(">"+fastai+"|1");
					    bw1.newLine();
					    fastai++;
					    bw1.write(seq);
				        bw1.newLine();
				    }
					if(negmin>featureNum)
				    { negmin=featureNum;}
				}
				
				if(featureNum<10) 
		    	{
		    		b[0]++;
		        }
				if(featureNum>=10&&featureNum<20) 
		    	{
		    		b[1]++;
		        }
				if(featureNum>=20&&featureNum<30) 
		    	{
		    		b[2]++;
		        }
				if(featureNum>=30&&featureNum<40) 
		    	{
		    		b[3]++;
		        }
				if(featureNum>=40&&featureNum<50) 
		    	{
		    		b[4]++;
		        }
				if(featureNum>=50&&featureNum<60) 
		    	{
		    		b[5]++;
		        }
			    if(featureNum>=60&&featureNum<70) 
		    	{
		    		b[6]++;
		        }
			    	 	 
			    if(featureNum>=70) 
			    {
			    	b[7]++;
			    }
			    	
			    b[8]++;
			   
			  }
			 String j=Arrays.toString(b);
				j=j.substring(1, j.length()-1);
				String title="   ";
				for(int k=0;k<seqlength;k=k+interval)
				{
					 title=title+","+"\"["+k+","+(k+interval)+")\"";
				}
				title=title+",>"+seqlength+",Total,MIN";
		     bw2.write(title);
		     bw2.newLine();
	         bw2.write("pos,");
		     bw2.write(h+","+posmin);
		     bw2.newLine();
		     bw2.write("neg,");
		     bw2.write(j+","+negmin);
		     bw2.flush();
	         br3.close();
	         br4.close();
             bw2.close();
             bw3.close();
             bw1.close();
             rsm.cleara(a);
             rsm.cleara(b);
             fastai=1;
//		}
	}
	 
      
}
