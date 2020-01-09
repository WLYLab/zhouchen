package Fuse;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;



public class Fuse_Main {
	
//	 private static String systemPath1= "C:\\Users\\Administrator\\Desktop\\ACP_Fu";
	  //private static String systemPath = "/mnt/tomcat/webapps/ACPred-FL/ACPred_FL";
	  static int Ntnum =10;//NT取几位
	  static int countnum =0;//平衡数据集计数
	  static int Ntstart =1;//NT第几位开始
	  static int m;
	  //static int num =0;//第几位开始
//	  static String input ="/ACP6.txt";//输入文件
	  static ArrayList<String> list = new ArrayList<String>();//原序列
	  static ArrayList<String> list_title = new ArrayList<String>();
	  static ArrayList<String> NT = new ArrayList<String>();
	  static ArrayList<String> CT = new ArrayList<String>();
	  static ArrayList<String> NTCT = new ArrayList<String>();
	  /*矩阵定义
		 */
//	  static int n_gap=0;//跳过几个氨基酸
//	  static int seq_len=5;//跳过几个氨基酸
//	  static String[][] Str_index_Sp_A = new String[seq_len][20];  
//	  static double[][] Count_index_Sp_A = new double[seq_len][20];
//	  static String[][] Str_index_Sp_AnA = new String[seq_len - (n_gap + 1)][400];                     
//	  static double[][] Count_index_Sp_AnA = new double[seq_len - (n_gap + 1)][400];	          
//	  static double[][] probability_index_Sp_A = new double[seq_len][20];
//	  static double[][] probability_index_Sp_AnA = new double[seq_len - (n_gap + 1)][400];     
//	  static String[][] Str_index_Sn_A = new String[seq_len][20];   
//	  static double[][] Count_index_Sn_A = new double[seq_len][20];          
//	  static String[][] Str_index_Sn_AnA = new String[seq_len - (n_gap + 1)][400];	          
//	  static double[][] Count_index_Sn_AnA = new double[seq_len - (n_gap + 1)][400];	          
//	  static double[][] probability_index_Sn_A = new double[seq_len][20];
//	  static double[][] probability_index_Sn_AnA = new double[seq_len - (n_gap + 1)][400];
	  
	  /*读入处理site
		 */ 
	  public void Site_P(String name,int c, ArrayList<String> instead,basis rbs1,Site_Prediction rsp1,int m,String systemPath1)
	  //单 （NT）：1 NT  2 NTCT
	  {
	      try { 
	    	  
	    	  BufferedWriter bw= new BufferedWriter(new FileWriter(systemPath1 +"BIT20"+"("+name+(m+1)+")"+".csv")); 
//	    	  rbs1.bufferW_arff((Ntstart)*20*c, bw);
	    	  for (int i = 0; i < (instead.size()/Ntnum); i++)	  
	          {
	        	 // System.out.println((String)NT.get(m+Ntnum*i));
	            String TwentyBitSequenceFeaturesNT = rsp1.TwentyBitSequenceFeature(((String)instead.get(m+Ntnum*i)).toUpperCase());
	            bw.write(TwentyBitSequenceFeaturesNT.substring(0, TwentyBitSequenceFeaturesNT.length()-1));
//	            bw.write((String)list_title.get(i));
	            bw.newLine();
	            bw.flush();
	            //System.out.println((String)NTCT.get(m+Ntnum*i));        
	           
	          }
	    	  countnum++;
	          bw.close();
	          
	          BufferedWriter bw1= new BufferedWriter(new FileWriter(systemPath1 +"BIT21"+"(+name"+(m+1)+")"+".csv")); 
//	    	  rbs1.bufferW_arff((Ntstart)*21*c, bw1);
	    	  for (int i = 0; i < (instead.size()/Ntnum); i++)	  
	          {
	        	 // System.out.println((String)NT.get(m+Ntnum*i));
	            String Bit21Feature = rsp1.Bit21Feature(((String)instead.get(m+Ntnum*i)).toUpperCase());
	            bw1.write(Bit21Feature.substring(0, Bit21Feature.length()-1));
//	            bw1.write((String)list_title.get(i));
	            bw1.newLine();
	            bw1.flush();
	            //System.out.println((String)NTCT.get(m+Ntnum*i));        
	           
	          }
	    	  countnum++;
	          bw1.close();
	          
	          BufferedWriter bw2= new BufferedWriter(new FileWriter(systemPath1 +"OLP"+"("+name+(m+1)+")"+".csv")); 
//	    	  rbs1.bufferW_arff((Ntstart)*10*c, bw2);
	    	  for (int i = 0; i < (instead.size()/Ntnum); i++)	  
	          {
	        	 // System.out.println((String)NT.get(m+Ntnum*i));
	            String OverlappingPropertiesFeatures = rsp1.OverlappingPropertiesFeatures(((String)instead.get(m+Ntnum*i)).toUpperCase());
	            bw2.write(OverlappingPropertiesFeatures.substring(0, OverlappingPropertiesFeatures.length()-1));
//	            bw2.write((String)list_title.get(i));
	            bw2.newLine();
	            bw2.flush();
	            //System.out.println((String)NTCT.get(m+Ntnum*i));        
	           
	          }
	    	  countnum++;
	          bw2.close();
	          
	          BufferedWriter bw3= new BufferedWriter(new FileWriter(systemPath1 +"IT"+"("+name+(m+1)+")"+".csv")); 
//	    	  rbs1.bufferW_arff(3, bw3);
	    	  for (int i = 0; i < (instead.size()/Ntnum); i++)	  
	          {
	    		  String InformationTheoryFeatures="";
		    		if(m==0) {
	             InformationTheoryFeatures = rsp1.InformationTheoryFeatures(((String)instead.get(m+Ntnum*i)).toUpperCase()+((String)instead.get(m+Ntnum*i)).toUpperCase());}
		    		else{
			             InformationTheoryFeatures = rsp1.InformationTheoryFeatures(((String)instead.get(m+Ntnum*i)).toUpperCase());}
	            bw3.write(InformationTheoryFeatures.substring(0, InformationTheoryFeatures.length()-1));
//	            bw3.write((String)list_title.get(i));
	            bw3.newLine();
	            bw3.flush();
	            //System.out.println((String)NTCT.get(m+Ntnum*i));        
	           
	          }
	    	  countnum++;
	          bw3.close();
	          
//	          BufferedWriter bw4= new BufferedWriter(new FileWriter(systemPath1 +"KSkipN"+name+"("+(m+1)+")"+countnum+".arff")); 
//	    	  rbs1.bufferW_arff(400, bw4);
//	    	  for (int i = 0; i < (instead.size()/Ntnum); i++)	  
//	          { String KSkipN="";
//	    		if(m==0)
//	    		{KSkipN=(String)instead.get(m+Ntnum*i)+(String)instead.get(m+Ntnum*i);
////	    		System.out.println(KSkipN);
//	    		}
//	    		else
//		    	{KSkipN=(String)instead.get(m+Ntnum*i);
////		    	System.out.println(Ntnum);
//		    	}
//		    	int Skip_k = 0;
//		    	int feaType =2;
//	            String  KSkipNGramsFeature = rsp1. KSkipNGramsFeature(KSkipN,Skip_k, feaType);
//	            bw4.write( KSkipNGramsFeature);
//	            bw4.write((String)list_title.get(i));
//	            bw4.newLine();
//	            bw4.flush();
//	            //System.out.println((String)NTCT.get(m+Ntnum*i));        
//	           
//	          }
//	    	  countnum++;
//	          bw4.close();
	          
	          
	          BufferedWriter bw5= new BufferedWriter(new FileWriter(systemPath1 +"AAC"+"("+name+(m+1)+")"+".csv"
	          		)); 
//	    	  rbs1.bufferW_arff(20, bw5);
	    	  for (int i = 0; i < (instead.size()/Ntnum); i++)	  
	          {
	    		String ngram=(String)instead.get(m+Ntnum*i);;
	    		String ngram_n = rsp1.ngram_p(ngram);
	            bw5.write( ngram_n.substring(0, ngram_n.length()-1));
//	            bw5.write((String)list_title.get(i));
	            bw5.newLine();
	            bw5.flush();
	          }
	    	  countnum++;
	          bw5.close();
	          
	          
	    	} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	  
	  }
	  
	  /*读入fasta格式
		 */
	  public void initList(String fileName)
	  {
	    try
	    {
	      @SuppressWarnings("resource")
		BufferedReader br = new BufferedReader(new FileReader(fileName));
	      while (br.ready())
	      {
	        String str1 = "";
	        String str2 = "";
	        String str = "";
	        String title = br.readLine();
	        String line1 = br.readLine();
	        list.add(line1.trim());
	        for (int i=Ntstart;i<Ntnum+Ntstart;i++) {
	        String line2=line1+line1;	
	        str1 = line2.substring(0, i);
	        NT.add(str1.trim());
//	        System.out.println(str1);
	        str2 = line2.substring(line2.length()-i, line2.length());
	        CT.add(str2.trim());
//	        System.out.println(str2);
	        str=str1+str2;
	        NTCT.add(str.trim());
	      //System.out.println(str);
	        }
	    
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
//	  /*处理矩阵的值
//		 */
//	  public void Deal_Matrix(ArrayList<String> titlelist,ArrayList<String> seqlist,int m,int seq_len) 
//	  {
//		  int num_p = 0;
//          int num_n = 0;
//          for (int q = 0; q < titlelist.size(); q++)	  
//          {
//            String title = (String)titlelist.get(m+Ntnum*q);
//            String line = (String)seqlist.get(m+Ntnum*q);
//            if (title.equals("0"))
//            {
//              num_p++;
//              for (int i = 0; i < line.length(); i++)
//              {
//                String cString = line.substring(i, i + 1);
//                for (int j = 0; j < 20; j++) {
//                  if (cString.equals(Str_index_Sp_A[i][j]))
//                  {
//                    Count_index_Sp_A[i][j] += 1;
//                    break;
//                  }
//                }
//                if (i <= line.length() - 1 - (n_gap + 1))
//                {
//                  String cString2 = line.substring(i, i + 1) + line.substring(i + n_gap + 1, i + n_gap+ 2);
//                  //System.out.println(cString2);
//                  for (int k = 0; k < 400; k++) {
//                    if (cString2.equals(Str_index_Sp_AnA[i][k]))
//                    {
//                      Count_index_Sp_AnA[i][k] += 1;
//                      break;
//                    }
//                  }
//                }
//              }
//            }
//            else
//            {
//              num_n++;
//              for (int i = 0; i < line.length(); i++)
//              {
//                String cString = line.substring(i, i + 1);
//                for (int j = 0; j < 20; j++) {
//                  if (cString.equals(Str_index_Sn_A[i][j]))
//                  {
//                    Count_index_Sn_A[i][j] += 1;
//                    break;
//                  }
//                }
//                if (i <= line.length() - 1 - (n_gap + 1))
//                {
//                  String cString2 = line.substring(i, i + 1) + line.substring(i + n_gap + 1, i + n_gap + 2);
//                  for (int k = 0; k < 400; k++) {
//                    if (cString2.equals(Str_index_Sn_AnA[i][k]))
//                    {
//                      Count_index_Sn_AnA[i][k] += 1;
//                      break;
//                    }
//                  }
//                }
//              }
//            }
//          }
//          for (int i = 0; i < seq_len; i++) {
//            for (int j = 0; j < 20; j++)
//            {
//              probability_index_Sp_A[i][j] = (Count_index_Sp_A[i][j] / num_p);
//              probability_index_Sn_A[i][j] = (Count_index_Sn_A[i][j] / num_n);
//            }
//          }
//          for (int i = 0; i < seq_len - (n_gap + 1); i++) {
//            for (int j = 0; j < 400; j++)
//            {
//              probability_index_Sp_AnA[i][j] = (Count_index_Sp_AnA[i][j] / num_p);
//              probability_index_Sn_AnA[i][j] = (Count_index_Sn_AnA[i][j] / num_n);
//            }
//          }
//         
//	  }
	  
	  public static String getRealPath() {
	        String realPath = Fuse_Main.class.getClassLoader().getResource("")
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
	  /*主函数
		 */
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
//		String []Peptides={ "Anti-angiogenic_Peptides", "Anti-bacterial_Peptides", "Anti-cancer_Peptides", "Anti-inflammatory_Peptides",
//  				"Anti-viral_Peptides", "Cell-penetrating_Peptides", "Quorum-Sensing_Peptides", 
//  			      "Surface-Binding_Peptides"  };//"Streptavidin-Binding_Peptides", "Signal_Peptides"
////  		//int Peptidenum=Peptides.length;
////		String []Peptides={ "Anti-angiogenic_Peptides", "Anti-cancer_Peptides", "Anti-inflammatory_Peptides",
////  				"Anti-viral_Peptides", "Quorum-Sensing_Peptides" 
////  			       };//"Streptavidin-Binding_Peptides", "Signal_Peptides"
////		String []Peptides={ "Anti-bacterial_Peptides", "Cell-penetrating_Peptides", "Signal_Peptides"
////				, "Surface-Binding_Peptides"  };
//  		for(int n=2;n<Peptides.length-5;n++) {//Peptides.length
//  			String systemPath0=systemPath1+"/"+Peptides[n]+"/";
		
		String systemPath1=getRealPath()+"/";
		String input = args[0];
  			String systemPath=systemPath1+"/csv/";
  			countnum =10;//平衡数据集计数
		basis rbs =new basis();
		Fuse_Main rm =new Fuse_Main();		
		rm.initList(input);
		 String[] AA_1gram = { "A", "R", "N", "D", "C", "Q", "E", "G", "H", "I", "L", 
			      "K", "M", "F", "P", "S", "T", "W", "Y", "V" };
		 String[] AA_2gram = new String[400];
	      int AA2 = 0;
	      for (int i = 0; i < 20; i++) {
	        for (int j = 0; j < 20; j++)
	        {
	          AA_2gram[AA2] = AA_1gram[i].concat(AA_1gram[j]);
	          AA2++;
	        }
	      }
	      /*直接基于序列的方法
			读入fasta格式
			 */	  
	        METH_Ngram rngm=new METH_Ngram();
    	  for (int m=1 ; m < 2; m++)
	      { 
    		  BufferedWriter bwngm = new BufferedWriter(new FileWriter(systemPath + "/AAC(N=1)"+".csv"));
//	      rbs.bufferW_arff(20, bwngm);
	      for (int i = 0; i < list.size(); i++)
	      {
	    	  String c=list.get(i);    	  
	    	  String ngram_p = rngm.ngram_p(c);
	    	  bwngm.write(ngram_p.substring(0, ngram_p.length()-1));
//	    	  bwngm.write(ngram_p+ (String)list_title.get(i));
	    	  bwngm.newLine();
	    	  rngm.cleara();
	      }
	      bwngm.close();    	  
	      }
	      
    	  for (int m = 2; m < 3; m++)
	      { 
    		  BufferedWriter bwngm = new BufferedWriter(new FileWriter(systemPath + "/AAC(N=2)"+".csv"));
//	      rbs.bufferW_arff(20, bwngm);
	      for (int i = 0; i < list.size(); i++)
	      {
	    	  String c=list.get(i);    	  
	    	  String ngram_n = rngm.ngram_n(c);
//	    	  bwngm.write(ngram_n+ (String)list_title.get(i));
	    	  bwngm.write(ngram_n.substring(0, ngram_n.length()-1));
	    	  bwngm.newLine();
	    	  rngm.cleara();
	      }
	      bwngm.close();    	  
	      }
	      
	       
		//CTD形成arff
		METH_CTD rctd=new METH_CTD();
		 for (int m = 3; m < 4; m++)
		      { BufferedWriter bwctd = new BufferedWriter(new FileWriter(systemPath + "/CTD"+".csv"));
//		      rbs.bufferW_arff(63, bwctd);
		      for (int i = 0; i < list.size(); i++)
		      {
		    	  String c=list.get(i);
		    	  String CTD_C = rctd.CTD_C(c);
		    	  String CTD_T = rctd.CTD_T(c);
		    	  String CTD_D ="";
		          if(m==50) {
		        	   CTD_D = rctd.CTD_D(c);}
		          else {
		        	   CTD_D = rctd.CTD_D1(c);
		              }
		          String result= CTD_C+CTD_T+CTD_D; 

		           bwctd.write( result.substring(0, result.length()-1));
//		           bwctd.write((String)list_title.get(i));
		           bwctd.newLine();
		           rctd.cleara();
		           bwctd.flush();
		      }
		      bwctd.close();
		      
		    }

	      
		//GGAP形成arff
		 for (int m = 4; m < 8; m++) {
				METH_GGAP rgap = new METH_GGAP();
		    	  BufferedWriter bwgap = new BufferedWriter(new FileWriter(systemPath + "/GAP(g="+(m-3)+").csv"));
//		    	  rbs.bufferW_arff(400, bwgap);
		      for (int i = 0; i < list.size(); i++)
		      {
		    	   //rgap.dealListGgap((String)list.get(i),m);      
		    	  String GAP=rgap.G_gap((String)list.get(i), AA_2gram, m-4);     
		    	  bwgap.write(GAP.substring(0, GAP.length()-1));
//		           bwgap.write((String)list_title.get(i));
		           bwgap.newLine();
		           rgap.cleara();
		           bwgap.flush();
		           }
		      bwgap.close();
		 }
		 
	      METH_400D r400D=new METH_400D();
    	  for (int m = 8; m < 9; m++)
	      { BufferedWriter bw400D = new BufferedWriter(new FileWriter(systemPath + "/400D"+".csv"));
//	      rbs.bufferW_arff(400, bw400D);
	      for (int i = 0; i < list.size(); i++)
	      {
	    	  String c=list.get(i);
	    	  int Skip_k = c.length() - 1;
	    	  int feaType =2;
	    	  String Fskip = r400D.AdaptiveSkipNGramFeatures(c,Skip_k, feaType);
//	    	  bw400D.write(Fskip+ (String)list_title.get(i));
	    	  bw400D.write(Fskip.substring(0, Fskip.length()-1));
	    	  bw400D.newLine();
	      }
	      bw400D.close();  
	       } 
	      
	      	    	 
    	  for (int m = 9; m < 10; m++)
	      { BufferedWriter bw188D = new BufferedWriter(new FileWriter(systemPath + "/188D"+".csv"));
//	      rbs.bufferW_arff(188, bw188D);
	      for (int i = 0; i < list.size(); i++)
	      {
	    	  String c=list.get(i);
	    	  METH_188D r188D=new METH_188D(c.toUpperCase());
	    	  String D188 = r188D.run();
//	    	  bw188D.write(r188D.run()+ (String)list_title.get(i));
	    	  bw188D.write(D188.substring(0, D188.length()-1) );
	    	  bw188D.newLine();
	      }
	      bw188D.close();  
	    	  
	      }
	      
//	        METH_KSkipNGrams rksn=new METH_KSkipNGrams();
//	        int t=11;
//    	  for (int m = 10; m < t+1; m++)
//	      { int Skip_k = m-t+1;
//    		  BufferedWriter bw8000D = new BufferedWriter(new FileWriter(systemPath + "/KSkipNGram("+Skip_k+")"+m+".arff"));
//	      rbs.bufferW_arff(8000, bw8000D);
//	      for (int i = 0; i < list.size(); i++)
//	      {
//	    	  String c=list.get(i);
//	    	  
//	    	  int feaType =3;
//	    	  String Fskip1 = rksn.KSkipNGramsFeature(c,Skip_k, feaType);
//	    	  bw8000D.write(Fskip1+ (String)list_title.get(i));
//	    	  bw8000D.newLine();
//	      }
//	      bw8000D.close();    	  
//	      }     
	      /*基于平衡序列的方法
			矩阵
			 */
//	      
	      
	      
	      Site_Prediction rsp = new Site_Prediction();
	      for(int m=0;m<Ntnum;m++) {
	      rm. Site_P( "NT",m+1, NT, rbs, rsp, m,systemPath);
//	      rm. Site_P( "CT",m+1, CT, rbs, rsp, m,systemPath);
//	      rm. Site_P( "NTCT",2*(m+1), NTCT, rbs, rsp, m,systemPath);
	      }
//	    
	      //矩阵建立和定义
//	      {   
//	          Arrays.fill(Str_index_Sp_A, AA_1gram);        
//	          Arrays.fill(Str_index_Sp_AnA, AA_2gram);          	  
//	          Arrays.fill(Str_index_Sn_A, AA_1gram);	                     
//	          Arrays.fill(Str_index_Sn_AnA, AA_2gram);     
//	         
//	  }
//	    
//	      METH_Site_Matrix rMatrix =new METH_Site_Matrix();
//	      for(int m=1;m<5;m++) {
//	    	  int n_gap=0;//跳过几个氨基酸
//	      BufferedWriter bwnpps1 = new BufferedWriter(new FileWriter(systemPath + "/NPPSNT"+(m+12+m*6)+".arff"));
//		  rMatrix.NPPS1( (m+Ntstart) , 20 , n_gap , "arff", bwnpps1,list_title,NT,m,Ntnum,0);
//	      BufferedWriter bwnpps2 = new BufferedWriter(new FileWriter(systemPath + "/BPSNT"+(m+13+m*6)+".arff"));
//		  rMatrix.BPB1( (m+Ntstart) , 20 , n_gap , "arff",  bwnpps2,list_title,NT,m,Ntnum,0);
//		  BufferedWriter bwnpps3 = new BufferedWriter(new FileWriter(systemPath + "/NPPSCT"+(m+14+m*6)+".arff"));
//		  rMatrix.NPPS1( (m+Ntstart) , 20 , n_gap , "arff", bwnpps3,list_title,CT,m,Ntnum,0);
//	      BufferedWriter bwnpps4 = new BufferedWriter(new FileWriter(systemPath + "/BPSCT"+(m+15+m*6)+".arff"));
//		  rMatrix.BPB1( (m+Ntstart) , 20 , n_gap , "arff",  bwnpps4,list_title,CT,m,Ntnum,0);
//		  BufferedWriter bwnpps5 = new BufferedWriter(new FileWriter(systemPath + "/NPPSNTCT"+(m+16+m*6)+".arff"));
//		  rMatrix.NPPS1( (m*2+Ntstart*2) , 20 , n_gap , "arff", bwnpps5,list_title,NTCT,m,Ntnum,0);
//	      BufferedWriter bwnpps6 = new BufferedWriter(new FileWriter(systemPath + "/BPSNTCT"+(m+17+m*6)+".arff"));
//		  rMatrix.BPB1( (m*2+Ntstart*2) , 20 , n_gap , "arff",  bwnpps6,list_title,NTCT,m,Ntnum,0);
//		  }
//	     
//      
               NT.clear();
               list_title.clear();
	           CT.clear();
	           NTCT.clear();
	           list.clear();
//	      
	      

  		}
	}

//}
