

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Separation_posneg {
	  private static String systemPath="C:\\Users\\Administrator\\Desktop\\Peptides_optim2/";
	  static ArrayList<String> list_pos = new ArrayList<String>();
	  static ArrayList<String> list_neg = new ArrayList<String>();
	  static ArrayList<String> list_all = new ArrayList<String>();
	  public static void main(String[] args) throws IOException
	  {  
		  String []Peptides={ "Anti-angiogenic_Peptides", "Anti-bacterial_Peptides", "Anti-cancer_Peptides", "Anti-inflammatory_Peptides", 
				"Anti-viral_Peptides", "Cell-penetrating_Peptides", "Quorum-Sensing_Peptides", "Surface-Binding_Peptides",  };
//		  String []Peptides={ "Anti-angiogenic_Peptides", "Anti-cancer_Peptides", "Anti-inflammatory_Peptides", 
//					"Anti-viral_Peptides", "Quorum-Sensing_Peptides", "Cell-penetrating_Peptides" };
		//int Peptidenum=Peptides.length;
//	BufferedWriter bw3=new BufferedWriter(new FileWriter(systemPath+"/posall.txt"),5*1024*1024);
		for(int n=0;n<Peptides.length;n++) {//Peptides.length
			String systemPath1=systemPath+"/"+Peptides[n];
	String[] AA_1gram = { "A", "R", "N", "D", "C", "Q", "E", "G", "H", "I", "L", 
		      "K", "M", "F", "P", "S", "T", "W", "Y", "V" };
//	BufferedReader br1=new BufferedReader(new FileReader(systemPath1+"/itfa.txt"));//正例
	BufferedReader br2=new BufferedReader(new FileReader(systemPath1+"/fasta1000.txt"));//fast输入文件
	BufferedWriter bw1=new BufferedWriter(new FileWriter(systemPath1+"/pos1.txt"),5*1024*1024);//输出正例
	BufferedWriter bw2=new BufferedWriter(new FileWriter(systemPath1+"/neg1.txt"),5*1024*1024);//输出负例

	//单独
	while (br2.ready())
    { 
	  String newline="";
	  String title= br2.readLine();
      String line1= br2.readLine();
      if(title.split("\\|")[1].equals("0")) {
    	  list_pos.add(title);
    	  list_all.add(title);
      for(int t=0;t<line1.length();t++) {
    	  String d=line1.substring(t, t+1);
    	  for(int k=0;k<20;k++) {
    		  if(d.equals(AA_1gram[k])) {
    			  newline= newline+d;
    		  }
    	  }
      }
      list_pos.add(newline);
      list_all.add(newline);
   
      }
      if(title.split("\\|")[1].equals("1")){
    	  list_neg.add(title);
//    	  System.out.println(title);
      for(int t=0;t<line1.length();t++) {
    	  String d=line1.substring(t, t+1);
    	  for(int k=0;k<20;k++) {
    		  if(d.equals(AA_1gram[k])) {
    			  newline= newline+d;
    		  }
    		  
    	  }
      }
      list_neg.add(newline);
   
      }
      }
	
	  for(int i=0;i<list_pos.size();i++) {
      bw1.write(list_pos.get(i).toString());
	  bw1.newLine();
	 } 
	  for(int i=0;i<list_neg.size();i++) {
      bw2.write(list_neg.get(i).toString());
	  bw2.newLine();
	  
	 } 
	
	
	//分开
//	int tp=0;
//	int tn=0;
//	while (br1.ready())
//    {
//	  String title= br1.readLine();
//      String line1= br1.readLine();
//      if(title.split("\\|")[1].equals("0")) {
//    	  if(tp!=1) {
//    	  for(int i=0;i<list_pos.size();i=i+2) {bw1.write(list_pos.get(i).toString());
//    	  bw1.newLine();
//    	  bw1.write(list_pos.get(i+1).toString());
//    	  bw1.newLine();
//    	 } tp++;}
//      bw1.write(title);
//      bw1.newLine();
//      bw1.write(line1);
//      bw1.newLine();
//      }
//      if(title.split("\\|")[1].equals("1")) {
//    	  if(tn!=1) {
//    	  for(int i=0;i<list_neg.size();i=i+2) {
//    		  bw1.write(list_neg.get(i).toString());
//    		  bw1.newLine();
//    	  bw1.write(list_neg.get(i+1).toString());
//    	  bw1.newLine();
//    	  }tn++;}
//      bw1.write(title);
//      bw1.newLine();
//      bw1.write(line1);
//      bw1.newLine();
//      }
//      }
	
	
	 list_neg.clear();
	 list_pos.clear();

	  br2.close();
      bw1.close();
      bw2.close();
//      br1.close();
		}
//		 for(int i=0;i<list_all.size();i++) {
//		      bw3.write(list_all.get(i).toString());
//			  bw3.newLine();
//			  
//			 } 
//		 bw3.close();
	  }
}
