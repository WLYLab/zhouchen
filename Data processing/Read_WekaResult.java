


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;



public class Read_WekaResult {//读取weka程序跑出的结果方便统计
    static ArrayList<String> sublist = new ArrayList<String>();
    static int n=13;//一个结果的行数
    static int g=0;
    private static String systemPath1="C:\\Users\\Administrator\\Desktop\\Peptides_optim2\\";
	  /**
     * 将文件内容以>和|分开按需要输出饿顺序读取存放到List里面,
     * 
     * @param fileName
     *            文件名
     */
 
    @SuppressWarnings("resource")
    public void initList(String fileName) {
        try {
       
        	BufferedReader br = new BufferedReader(new FileReader(fileName));
            while (br.ready())
            {String str = "";
            String line1;
            String strnum = "";
            String str0 = "";
        	String str1 = "";
            String str2 = "";
            String str21 = "";
            String str3 = "";
            String strACC = "";
            for(int i=1;i<=(n+g);i++)
              { 
            	
             line1 = br.readLine();
             if(i==(0+g)){ strnum=line1+",";}//按空格切分不管几个空格
            
             if(i==(4+g)){str1=line1.trim().split("\\s+")[0];}//按空格切分不管几个空格
            // System.out.println(str1);
             if(i==(5+g)){str1=str1+","+line1.trim().split("\\s+")[0]+",";}
             //System.out.println(str1);
             if(i==(11+g)){str2=line1.split("\\s+")[1];str21=line1.split("\\s+")[2];}
             if(i==(12+g)){str2=str2+","+line1.split("\\s+")[2]+","+line1.split("\\s+")[1]+","+str21+",";}
             //System.out.println(line1.split("\\ ")[1]);
             if(i==(6+g)){str0=line1.split("\\s+")[2]+",";
             str3=line1.split("\\s+")[7].trim()+","+line1.split("\\s+")[8]+","+line1.split("\\s+")[9].trim()+",";
             strACC= line1.split("\\s+")[6];
             
             }
              }
              str=str1+str0+str3+str2+strACC ;
              
              sublist.add(str);
//              String canshu=strnum+" "+strACC;
//              listcanshu.add(canshu);
         //     System.out.println(canshu);
            }
       
             
            

        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
    }
    
    public static void main(String[] args) throws Exception 
	{
//		//BufferedWriter bw=new BufferedWriter(new FileWriter(systemPath+"/6.arff"),5*1024*1024);
//    	String[] Peptides={ "Anti-angiogenic_Peptides", "Anti-bacterial_Peptides", "Anti-cancer_Peptides", "Anti-inflammatory_Peptides",
//  				"Anti-viral_Peptides", "Cell-penetrating_Peptides", "Quorum-Sensing_Peptides", 
//  			      "Surface-Binding_Peptides" , "Streptavidin-Binding_Peptides", "Signal_Peptides"};
//  		//int Peptidenum=Peptides.length;
//  		for(int h=0;h<Peptides.length-2;h++) {//Peptides.length
//  			String systemPath=systemPath1+"/"+Peptides[h]+"/fastadata//";
//    	String systemPath="C:\\Users\\Administrator\\Desktop\\Peptides_optim2\\CPP924\\fastadata";
//		BufferedWriter bw2=new BufferedWriter(new FileWriter(systemPath+"/Resultarff.csv"),5*1024*1024);//systemPath+"/6.arff
//    	String []Peptides={ "Anti-angiogenic_Peptides", "Anti-bacterial_Peptides", 
//  				"Anti-viral_Peptides", "Quorum-Sensing_Peptides" , "Surface-Binding_Peptides"
//  			       };//"Streptavidin-Binding_Peptides", "Signal_Peptides"
    	String []Peptides={ "Anti-angiogenic_Peptides", "Anti-bacterial_Peptides", "Anti-cancer_Peptides", "Anti-inflammatory_Peptides",
  				"Anti-viral_Peptides", "Cell-penetrating_Peptides", "Quorum-Sensing_Peptides", 
  			      "Surface-Binding_Peptides"  };
  		for(int h=2;h<Peptides.length-5;h++) {
  			String systemPath=systemPath1+"/"+Peptides[h]+"/New/";//修改
    	BufferedWriter bw1=new BufferedWriter(new FileWriter(systemPath+"/Result_arfftt.csv"),5*1024*1024);//systemPath+"/6.arff 
			Read_WekaResult rl=new Read_WekaResult();
	

			System.out.println(Peptides[h]);
//		    rl.initList(systemPath+"/result_art.txt");
			
		    rl.initList(systemPath+"/Result_arfftt.txt");
		    bw1.write("Training Model (10-fold cross validation");
			bw1.newLine();
			bw1.write("Fea,SE,SP,ACC,MCC,ROC Area,PRC Area,TP,TN,FP,FN,F-Measure");
			bw1.newLine();
			for (int i = 0; i < sublist.size(); i++) {
				int m=i+1;
	
		    	bw1.write("Fea"+(i+1)+","+sublist.get(i));
		     	
				bw1.newLine();
			}
			sublist.clear();
		    bw1.close();
		
		
  		}
	}
}
