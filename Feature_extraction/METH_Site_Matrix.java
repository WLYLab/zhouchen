package Fuse;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class METH_Site_Matrix {

	@SuppressWarnings("unused")
	public void NPPS(int seq_len,int char_num,int n,String format,BufferedWriter bw, String[][] Str_index_Sp_A, String[][] Str_index_Sp_AnA, double[][] probability_index_Sp_A, double[][] probability_index_Sp_AnA, double[][] probability_index_Sn_A, 
			double[][] probability_index_Sn_AnA,ArrayList<String> titlelist,ArrayList<String> seqlist,int m,int Ntnum)
		    throws IOException
		  {
		      
		    if (format.equals("arff"))
		    {
		      bw.write("@relation npps");
		      bw.newLine();

		   
		     int featureNum = seq_len - (n + 1);
		      for (int i = 1; i <= featureNum; i++)
		      {
		        bw.write("@attribute Feature" + i + " real");
		        bw.newLine();
		      }
		      bw.write("@attribute class ");
		     String  attributeClass = "0";
		      for (int i = 0; i < 1; i++) {
		        attributeClass = attributeClass + "," + (i + 1);
		      }
		      bw.write("{" + attributeClass + "}");
		      bw.newLine();
		      bw.write("@data");
		      bw.newLine();
		      for (int q = 0; q < titlelist.size(); q++)	  
	          {
	            String title = (String)titlelist.get(m+Ntnum*q);
	            String line = (String)seqlist.get(m+Ntnum*q);
		        
		    	StringBuilder sb = new StringBuilder();
		        for (int i = 0; i < line.length() - (n + 1); i++)
		        {
		            String cA = line.substring(i + n + 1, i + n + 2);
		        	String cA_A = line.substring(i, i + 1) + line.substring(i + n + 1, i + n + 2);
		          double pA_A_n = 0.0D;double pA_A_p = 0.0D;
		          double pA_n = 0.0D;double pA_p = 0.0D;
		          for (int j = 0; j < char_num; j++) {
		            if (cA.equals(Str_index_Sp_A[(i + (n + 1))][j]))
		            {
		              pA_n = probability_index_Sn_A[(i + (n + 1))][j];
		              pA_p = probability_index_Sp_A[(i + (n + 1))][j];
		              break;
		            }
		          }
		          for (int j = 0; j < char_num * char_num; j++) {
		            if (cA_A.equals(Str_index_Sp_AnA[i][j]))
		            {
		              pA_A_n = probability_index_Sn_AnA[i][j];
		              pA_A_p = probability_index_Sp_AnA[i][j];
		              break;
		            }
		          }
		          if ((Math.abs(pA_A_p / pA_p - pA_A_n / pA_n) < 1.0E-006D) || (Double.isNaN(pA_A_p / pA_p - pA_A_n / pA_n))) {
		            sb.append("0.0,");
		          } else {
		            sb.append(pA_A_p / pA_p - pA_A_n / pA_n + ",");
		          }
		        }
		        if (title.equals("0")) {
		          sb.append("0");
		        } else if (title.equals("1")) {
		          sb.append("1");
		        } else {
		          sb.append("-1");
		        }
		        bw.write(sb + "\r\n");
		      }
		      bw.flush();
		      bw.close();
		     
		    }
//		    else
//		    {
//		      while (br.ready())
//		      {
//		        int featureNum;
//		        int i;
//		        String attributeClass;
//		
//		        String title;
//		        String line;
//		        StringBuilder sb;
//		
//		        String cA;
//		        String cA_A;
//		        double pA_A_n;
//		        double pA_A_p;
//		        double pA_n;
//		        double pA_p;
//		        int j;
//		
//		        title = br.readLine();
//		        line = br.readLine();
//		        
//		        sb = new StringBuilder();
//		        if (title.split("\\|")[1].equals("0")) {
//		          sb.append("0");
//		        } else if (title.split("\\|")[1].equals("1")) {
//		          sb.append("1");
//		        } else {
//		          sb.append("-1 ");
//		        }
//		        int libsvm_index = 1;
//		        for (i = 0; i < line.length() - (n + 1); i++)
//		        {
//		          cA = line.substring(i + n + 1, i + n + 2);
//		          cA_A = line.substring(i, i + 1) + line.substring(i + n + 1, i + n + 2);
//		          pA_A_n = 0.0D;pA_A_p = 0.0D;
//		         pA_n = 0.0D;pA_p = 0.0D;
//		          for (j = 0; j < char_num; j++) {
//		            if (cA.equals(Str_index_Sp_A[(i + (n + 1))][j]))
//		            {
//		              pA_n = probability_index_Sn_A[(i + (n + 1))][j];
//		              pA_p = probability_index_Sp_A[(i + (n + 1))][j];
//		              break;
//		            }
//		          }
//		          for (j = 0; j < char_num * char_num; j++) {
//		            if (cA_A.equals(Str_index_Sp_AnA[i][j]))
//		            {
//		              pA_A_n = probability_index_Sn_AnA[i][j];
//		              pA_A_p = probability_index_Sp_AnA[i][j];
//		              break;
//		            }
//		          }
//		          if ((Math.abs(pA_A_p / pA_p - pA_A_n / pA_n) < 1.0E-006D) || (Double.isNaN(pA_A_p / pA_p - pA_A_n / pA_n)))
//		          {
//		            sb.append(libsvm_index + ":" + 0.0D + " ");
//		            libsvm_index++;
//		          }
//		          else
//		          {
//		            sb.append(libsvm_index + ":" + (pA_A_p / pA_p - pA_A_n / pA_n) + " ");
//		            libsvm_index++;
//		          }
//		        }
//		        bw.write(sb + "\r\n");
//		      }
//		      bw.flush();
//		      bw.close();
//		      br.close();
//		    }
		  }
	//BPB
	public void BPB(int seq_len,int char_num,int n,String format, BufferedWriter bw, String[][] Str_index_Sp_A, String[][] Str_index_Sn_A, 
			double[][] probability_index_Sp_A,  double[][] probability_index_Sn_A,ArrayList<String> titlelist,ArrayList<String> seqlist,int m,int Ntnum)
		    throws IOException
		  {
		 if (format.equals("arff"))
	      {
	        bw.write("@relation bpb");
	        bw.newLine();
	        
	        int  featureNum = seq_len * 2;
	        for (int i = 1; i <= featureNum; i++)
	        {
	          bw.write("@attribute Feature" + i + " real");
	          bw.newLine();
	        }
	        bw.write("@attribute class ");
	        String attributeClass = "0";
	        for (int i = 0; i < 1; i++) {
	          attributeClass = attributeClass + "," + (i + 1);
	        }
	        bw.write("{" + attributeClass + "}");
	        bw.newLine();
	        bw.write("@data");
	        bw.newLine();
	        for (int q = 0; q < titlelist.size(); q++)	  
	          {
	            String title = (String)titlelist.get(m+Ntnum*q);
	            String line = (String)seqlist.get(m+Ntnum*q);
	        
	          
	        	StringBuilder sb = new StringBuilder();
	          for (int i = 0; i < line.length(); i++)
	          {
	        	  String cA = line.substring(i, i + 1);
	        	  double pA_p = 0.0D;
	            for (int j = 0; j < 20; j++) {
	              if (cA.equals(Str_index_Sp_A[i][j]))
	              {
	                pA_p = probability_index_Sp_A[i][j];
	                break;
	              }
	            }
	            sb.append(pA_p + ",");
	          }
	          for (int i = 0; i < line.length(); i++)
	          {
	        	  String cA = line.substring(i, i + 1);
	            double pA_n = 0.0D;
	            for (int j = 0; j < 20; j++) {
	              if (cA.equals(Str_index_Sn_A[i][j]))
	              {
	                pA_n = probability_index_Sn_A[i][j];
	                break;
	              }
	            }
	            sb.append(pA_n + ",");
	          }
	          if (title.equals("0")) {
	            sb.append("0");
	          } else if (title.equals("1")) {
	            sb.append("1");
	          } else {
	            sb.append("0");
	          }
	          bw.write(sb + "\r\n");
	        }
	        bw.flush();
	        bw.close();
	       
	      }
	      
	    }
	   
	  
	public void NPPS1(int seq_len,int char_num,int n,String format,BufferedWriter bw, 
			ArrayList<String> titlelist,ArrayList<String> seqlist,int m,int Ntnum,int n_gap)
		    throws IOException
		  {
		  String[][] Str_index_Sp_A = new String[seq_len][20];  
		  double[][] Count_index_Sp_A = new double[seq_len][20];
		  String[][] Str_index_Sp_AnA = new String[seq_len - (n_gap + 1)][400];                     
		  double[][] Count_index_Sp_AnA = new double[seq_len - (n_gap + 1)][400];	          
		  double[][] probability_index_Sp_A = new double[seq_len][20];
		  double[][] probability_index_Sp_AnA = new double[seq_len - (n_gap + 1)][400];     
		  String[][] Str_index_Sn_A = new String[seq_len][20];   
		  double[][] Count_index_Sn_A = new double[seq_len][20];          
		  String[][] Str_index_Sn_AnA = new String[seq_len - (n_gap + 1)][400];	          
		  double[][] Count_index_Sn_AnA = new double[seq_len - (n_gap + 1)][400];	          
		  double[][] probability_index_Sn_A = new double[seq_len][20];
		  double[][] probability_index_Sn_AnA = new double[seq_len - (n_gap + 1)][400];
		  
	      {   String[] AA_1gram = { "A", "R", "N", "D", "C", "Q", "E", "G", "H", "I", "L", 
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
	          Arrays.fill(Str_index_Sp_A, AA_1gram);        
	          Arrays.fill(Str_index_Sp_AnA, AA_2gram);          	  
	          Arrays.fill(Str_index_Sn_A, AA_1gram);	                     
	          Arrays.fill(Str_index_Sn_AnA, AA_2gram);     
	         
	  }
		  int num_p = 0;
          int num_n = 0;
          for (int q = 0; q < titlelist.size(); q++)	  
          {
            String title = (String)titlelist.get(q);
            String line = (String)seqlist.get(m+Ntnum*q);
           // System.out.println(m+Ntnum*q);
            if (title.equals("0"))
            {
              num_p++;
              for (int i = 0; i < line.length(); i++)
              {
                String cString = line.substring(i, i + 1);
                for (int j = 0; j < 20; j++) {
                  if (cString.equals(Str_index_Sp_A[i][j]))
                  {
                    Count_index_Sp_A[i][j] += 1;
                    break;
                  }
                }
                if (i <= line.length() - 1 - (n_gap + 1))
                {
                  String cString2 = line.substring(i, i + 1) + line.substring(i + n_gap + 1, i + n_gap+ 2);
                  //System.out.println(cString2);
                  for (int k = 0; k < 400; k++) {
                    if (cString2.equals(Str_index_Sp_AnA[i][k]))
                    {
                      Count_index_Sp_AnA[i][k] += 1;
                      break;
                    }
                  }
                }
              }
            }
            else
            {
              num_n++;
              for (int i = 0; i < line.length(); i++)
              {
                String cString = line.substring(i, i + 1);
                for (int j = 0; j < 20; j++) {
                  if (cString.equals(Str_index_Sn_A[i][j]))
                  {
                    Count_index_Sn_A[i][j] += 1;
                    break;
                  }
                }
                if (i <= line.length() - 1 - (n_gap + 1))
                {
                  String cString2 = line.substring(i, i + 1) + line.substring(i + n_gap + 1, i + n_gap + 2);
                  for (int k = 0; k < 400; k++) {
                    if (cString2.equals(Str_index_Sn_AnA[i][k]))
                    {
                      Count_index_Sn_AnA[i][k] += 1;
                      break;
                    }
                  }
                }
              }
            }
          }
          for (int i = 0; i < seq_len; i++) {
            for (int j = 0; j < 20; j++)
            {
              probability_index_Sp_A[i][j] = (Count_index_Sp_A[i][j] / num_p);
              probability_index_Sn_A[i][j] = (Count_index_Sn_A[i][j] / num_n);
            }
          }
          for (int i = 0; i < seq_len - (n_gap + 1); i++) {
            for (int j = 0; j < 400; j++)
            {
              probability_index_Sp_AnA[i][j] = (Count_index_Sp_AnA[i][j] / num_p);
              probability_index_Sn_AnA[i][j] = (Count_index_Sn_AnA[i][j] / num_n);
            }
          }  
		    if (format.equals("arff"))
		    {
		      bw.write("@relation npps");
		      bw.newLine();

		   
		     int featureNum = seq_len - (n + 1);
		      for (int i = 1; i <= featureNum; i++)
		      {
		        bw.write("@attribute Feature" + i + " real");
		        bw.newLine();
		      }
		      bw.write("@attribute class ");
		     String  attributeClass = "0";
		      for (int i = 0; i < 1; i++) {
		        attributeClass = attributeClass + "," + (i + 1);
		      }
		      bw.write("{" + attributeClass + "}");
		      bw.newLine();
		      bw.write("@data");
		      bw.newLine();
		      for (int q = 0; q < titlelist.size(); q++)	  
	          {
	            String title = (String)titlelist.get(q);
	            String line = (String)seqlist.get(m+Ntnum*q);
		        
		    	StringBuilder sb = new StringBuilder();
		        for (int i = 0; i < line.length() - (n + 1); i++)
		        {
		            String cA = line.substring(i + n + 1, i + n + 2);
		        	String cA_A = line.substring(i, i + 1) + line.substring(i + n + 1, i + n + 2);
		          double pA_A_n = 0.0D;double pA_A_p = 0.0D;
		          double pA_n = 0.0D;double pA_p = 0.0D;
		          for (int j = 0; j < char_num; j++) {
		            if (cA.equals(Str_index_Sp_A[(i + (n + 1))][j]))
		            {
		              pA_n = probability_index_Sn_A[(i + (n + 1))][j];
		              pA_p = probability_index_Sp_A[(i + (n + 1))][j];
		              break;
		            }
		          }
		          for (int j = 0; j < char_num * char_num; j++) {
		            if (cA_A.equals(Str_index_Sp_AnA[i][j]))
		            {
		              pA_A_n = probability_index_Sn_AnA[i][j];
		              pA_A_p = probability_index_Sp_AnA[i][j];
		              break;
		            }
		          }
		          if ((Math.abs(pA_A_p / pA_p - pA_A_n / pA_n) < 1.0E-006D) || (Double.isNaN(pA_A_p / pA_p - pA_A_n / pA_n))) {
		            sb.append("0.0,");
		          } else {
		            sb.append(pA_A_p / pA_p - pA_A_n / pA_n + ",");
		          }
		        }
		        if (title.equals("0")) {
		          sb.append("0");
		        } else if (title.equals("1")) {
		          sb.append("1");
		        } else {
		          sb.append("-1");
		        }
		        bw.write(sb + "\r\n");
		        
		      }
		      bw.flush();
		      bw.close();
		      
		     
		    }
		  }
		  
	//BPB
		public void BPB1(int seq_len,int char_num,int n,String format, BufferedWriter bw, 
				ArrayList<String> titlelist,ArrayList<String> seqlist,int m,int Ntnum,int n_gap)
			    throws IOException
			  {
			String[][] Str_index_Sp_A = new String[seq_len][20];  
			  double[][] Count_index_Sp_A = new double[seq_len][20];
			  String[][] Str_index_Sp_AnA = new String[seq_len - (n_gap + 1)][400];                     
			  double[][] Count_index_Sp_AnA = new double[seq_len - (n_gap + 1)][400];	          
			  double[][] probability_index_Sp_A = new double[seq_len][20];
			  double[][] probability_index_Sp_AnA = new double[seq_len - (n_gap + 1)][400];     
			  String[][] Str_index_Sn_A = new String[seq_len][20];   
			  double[][] Count_index_Sn_A = new double[seq_len][20];          
			  String[][] Str_index_Sn_AnA = new String[seq_len - (n_gap + 1)][400];	          
			  double[][] Count_index_Sn_AnA = new double[seq_len - (n_gap + 1)][400];	          
			  double[][] probability_index_Sn_A = new double[seq_len][20];
			  double[][] probability_index_Sn_AnA = new double[seq_len - (n_gap + 1)][400];
			  
		      {   String[] AA_1gram = { "A", "R", "N", "D", "C", "Q", "E", "G", "H", "I", "L", 
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
		          Arrays.fill(Str_index_Sp_A, AA_1gram);        
		          Arrays.fill(Str_index_Sp_AnA, AA_2gram);          	  
		          Arrays.fill(Str_index_Sn_A, AA_1gram);	                     
		          Arrays.fill(Str_index_Sn_AnA, AA_2gram);     
		         
		  }
			  int num_p = 0;
	          int num_n = 0;
	          for (int q = 0; q < titlelist.size(); q++)	  
	          {
	            String title = (String)titlelist.get(q);
	            String line = (String)seqlist.get(m+Ntnum*q);
	            if (title.equals("0"))
	            {
	              num_p++;
	              for (int i = 0; i < line.length(); i++)
	              {
	                String cString = line.substring(i, i + 1);
	                for (int j = 0; j < 20; j++) {
	                  if (cString.equals(Str_index_Sp_A[i][j]))
	                  {
	                    Count_index_Sp_A[i][j] += 1;
	                    break;
	                  }
	                }
	                if (i <= line.length() - 1 - (n_gap + 1))
	                {
	                  String cString2 = line.substring(i, i + 1) + line.substring(i + n_gap + 1, i + n_gap+ 2);
	                  //System.out.println(cString2);
	                  for (int k = 0; k < 400; k++) {
	                    if (cString2.equals(Str_index_Sp_AnA[i][k]))
	                    {
	                      Count_index_Sp_AnA[i][k] += 1;
	                      break;
	                    }
	                  }
	                }
	              }
	            }
	            else
	            {
	              num_n++;
	              for (int i = 0; i < line.length(); i++)
	              {
	                String cString = line.substring(i, i + 1);
	                for (int j = 0; j < 20; j++) {
	                  if (cString.equals(Str_index_Sn_A[i][j]))
	                  {
	                    Count_index_Sn_A[i][j] += 1;
	                    break;
	                  }
	                }
	                if (i <= line.length() - 1 - (n_gap + 1))
	                {
	                  String cString2 = line.substring(i, i + 1) + line.substring(i + n_gap + 1, i + n_gap + 2);
	                  for (int k = 0; k < 400; k++) {
	                    if (cString2.equals(Str_index_Sn_AnA[i][k]))
	                    {
	                      Count_index_Sn_AnA[i][k] += 1;
	                      break;
	                    }
	                  }
	                }
	              }
	            }
	          }
	          for (int i = 0; i < seq_len; i++) {
	            for (int j = 0; j < 20; j++)
	            {
	              probability_index_Sp_A[i][j] = (Count_index_Sp_A[i][j] / num_p);
	              probability_index_Sn_A[i][j] = (Count_index_Sn_A[i][j] / num_n);
	            }
	          }
	          for (int i = 0; i < seq_len - (n_gap + 1); i++) {
	            for (int j = 0; j < 400; j++)
	            {
	              probability_index_Sp_AnA[i][j] = (Count_index_Sp_AnA[i][j] / num_p);
	              probability_index_Sn_AnA[i][j] = (Count_index_Sn_AnA[i][j] / num_n);
	            }
	          }  
			 if (format.equals("arff"))
		      {
		        bw.write("@relation bpb");
		        bw.newLine();
		        
		        int  featureNum = seq_len * 2;
		        for (int i = 1; i <= featureNum; i++)
		        {
		          bw.write("@attribute Feature" + i + " real");
		          bw.newLine();
		        }
		        bw.write("@attribute class ");
		        String attributeClass = "0";
		        for (int i = 0; i < 1; i++) {
		          attributeClass = attributeClass + "," + (i + 1);
		        }
		        bw.write("{" + attributeClass + "}");
		        bw.newLine();
		        bw.write("@data");
		        bw.newLine();
		        for (int q = 0; q < titlelist.size(); q++)	  
		          {
		            String title = (String)titlelist.get(q);
		            String line = (String)seqlist.get(m+Ntnum*q);
		        
		          
		        	StringBuilder sb = new StringBuilder();
		          for (int i = 0; i < line.length(); i++)
		          {
		        	  String cA = line.substring(i, i + 1);
		        	  double pA_p = 0.0D;
		            for (int j = 0; j < 20; j++) {
		              if (cA.equals(Str_index_Sp_A[i][j]))
		              {
		                pA_p = probability_index_Sp_A[i][j];
		                break;
		              }
		            }
		            sb.append(pA_p + ",");
		          }
		          for (int i = 0; i < line.length(); i++)
		          {
		        	  String cA = line.substring(i, i + 1);
		            double pA_n = 0.0D;
		            for (int j = 0; j < 20; j++) {
		              if (cA.equals(Str_index_Sn_A[i][j]))
		              {
		                pA_n = probability_index_Sn_A[i][j];
		                break;
		              }
		            }
		            sb.append(pA_n + ",");
		          }
		          if (title.equals("0")) {
		            sb.append("0");
		          } else if (title.equals("1")) {
		            sb.append("1");
		          } else {
		            sb.append("0");
		          }
		          bw.write(sb + "\r\n");
		        }
		        bw.flush();
		        bw.close();
		       
		      }
		      
		    }
		   
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
