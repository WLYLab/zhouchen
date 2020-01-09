package Fuse;


public class METH_CTD {

	static double[] a = new double[63];
	static String[][] hyd = { { "R", "K", "E", "D", "Q", "N", " ", " " }, { "G", "A", "S", "T", "P", "H", "Y", " " }, { "C", "V", "L", "I", "M", "F", "W", " " } };
	static String[][] nor = { { "G", "A", "S", "C", "T", "P", "D", " " }, { "N", "V", "E", "Q", "I", "L", " ", " " }, { "M", "H", "K", "F", "R", "Y", "W", " " } };
	static String[][] pol = { { "L", "I", "F", "W", "C", "M", "V", "Y" }, { "P", "A", "T", "G", "S", " ", " ", " " }, { "H", "Q", "R", "K", "N", "E", "D", " " } };
	/*把一行序列处理成
	 */
//	public void dealListCTD(String line1)
//	  {
//	    try
//	    {
//	      String str = "";
//	      str = line1;
//	      String[] dictionary = new String[line1.length()];
//	      for (int i = 0; i < line1.length(); i++)
//	      {
//	        dictionary[i] = line1.substring(i, i + 1);
//	        str = dictionary[i];
//	        list_CTD.add(str);
//	      }
//	    }
//	    catch (Exception e)
//	    {
//	      e.printStackTrace();
//	    }
//	  }
	//清空数组
	 public void cleara()
	  {
	    for (int n = 0; n < 63; n++) {
	      a[n] = 0.0D;
	    }
	  }
	//计算每种属性每个组在每条序列的概率
	public  String  CTD_C(String sequence)
		  {
		String str="";
		String  CTD_C = null;
		for (int i = 0; i < sequence.length(); i++) {
			   str = sequence.substring(i, i + 1);
		      for (int m = 0; m < 3; m++) {
		        for (int j = 0; j <= 7; j++)
		        {
		          if (str.equals(hyd[m][j])) {
		            a[m] += 1.0D;
		          }
		          if (str.equals(nor[m][j])) {
		            a[(m + 3)] += 1.0D;
		          }
		          if (str.equals(pol[m][j])) {
		            a[(m + 6)] += 1.0D;
		          }
		        }
		      }
		    }
		StringBuffer sb = new StringBuffer();
	    for (int i = 0; i <=8; i++) {

	    	  double c = a[i] / sequence.length() ;//* 100.0D
	    	  sb.append(c+",");
	        
	      
	    }
	    CTD_C = sb.toString();
	    return  CTD_C;
//		    for (int n = 0; n <= 8; n++)
//		    {
//		      double b = a[n];
//		      double c = b / allNumber.size() * 100.0D;
//		      a[n] = c;
//		    }
		  }
		  
		//计算每行序列相邻的两个来自于同一组的不同属性（G1G2，G3G2，G1G3）
	public  String  CTD_T(String sequence)
		  {
		String str="";
		String  CTD_T = null;
		for (int i = 0; i < sequence.length()-1; i++) {
			 str = sequence.substring(i, i + 1)+sequence.substring(i+1, i + 2);
		      for (int m = 0; m <= 7; m++) {
		        for (int j = 0; j <= 7; j++)
		        {
		          if (( str.toString().equals(hyd[0][m] + hyd[1][j])) || ( str.toString().equals(hyd[1][m] + hyd[0][j]))) {
		            a[54] += 1.0D;
		          }
		          if (( str.toString().equals(hyd[0][m] + hyd[2][j])) || ( str.toString().equals(hyd[2][m] + hyd[0][j]))) {
		            a[55] += 1.0D;
		          }
		          if (( str.toString().equals(hyd[1][m] + hyd[2][j])) || ( str.toString().equals(hyd[2][m] + hyd[1][j]))) {
		            a[56] += 1.0D;
		          }
		          if ((str.toString().equals(nor[0][m] + nor[1][j])) || (str.toString().equals(nor[1][m] + nor[0][j]))) {
		            a[57] += 1.0D;
		          }
		          if ((str.toString().equals(nor[0][m] + nor[2][j])) || (str.toString().equals(nor[2][m] + nor[0][j]))) {
		            a[58] += 1.0D;
		          }
		          if ((str.toString().equals(nor[1][m] + nor[2][j])) || (str.toString().equals(nor[2][m] + nor[1][j]))) {
		            a[59] += 1.0D;
		          }
		          if ((str.toString().equals(pol[0][m] + pol[1][j])) || (str.toString().equals(pol[1][m] + pol[0][j]))) {
		            a[60] += 1.0D;
		          }
		          if ((str.toString().equals(pol[0][m] + pol[2][j])) || (str.toString().equals(pol[2][m] + pol[0][j]))) {
		            a[61] += 1.0D;
		          }
		          if ((str.toString().equals(pol[1][m] + pol[2][j])) || (str.toString().equals(pol[2][m] + pol[1][j]))) {
		            a[62] += 1.0D;
		          }
		        }
		      }
		    }
		StringBuffer sb = new StringBuffer();
	    for (int i = 54; i <= 62; i++) {

	    	  double c = a[i] / (sequence.length()-1) ;//* 100.0D
	    	  sb.append(c+",");
	        
	      
	    }
	    CTD_T = sb.toString();
	    return  CTD_T;
//		    for (int n = 54; n <= 62; n++)
//		    {
//		      double b = a[n];
//		      double c = b / (allNumber.size() - 1) * 100.0D;
//		      a[n] = c;
//		    }
		  }	
	
	
	//计算每行序列的（1,1~25%，25%~50%，50%~75%,75%~100%）时每个组的每种属性占序列的百分比 45
	public  String CTD_D1(String sequence)//CTD的改进
	  {
	    double featureNum = sequence.length();
	    
	    double i0 = Math.ceil(featureNum / 4.0D) - 1.0D;
	    double i1 = Math.ceil(featureNum / 2.0D) - 1.0D;
	    double i2 = Math.ceil(featureNum / 4.0D * 3.0D) - 1.0D;
	    String str="";
		String  CTD_D1 = null;
	    for (int i = 0; i < featureNum; i++)
	    {str = sequence.substring(i, i + 1);
	      if (i == 0) {
	        for (int m = 0; m < 3; m++) {
	          for (int j = 0; j <= 7; j++)
	          {
	            if (str.toString().equals(hyd[m][j])) {
	              a[(m + 9)] += 1.0D;
	            }
	            if (str.toString().equals(nor[m][j])) {
	              a[(m + 12)] += 1.0D;
	            }
	            if (str.toString().equals(pol[m][j])) {
	              a[(m + 15)] += 1.0D;
	            }
	          }
	        }
	      }
	      if ((i <= i0) && (i > 0)) {
	        for (int m = 0; m < 3; m++) {
	          for (int j = 0; j <= 7; j++)
	          {
	            if (str.toString().equals(hyd[m][j])) {
	              a[(m + 18)] += 1.0D;
	            }
	            if (str.toString().equals(nor[m][j])) {
	              a[(m + 21)] += 1.0D;
	            }
	            if (str.toString().equals(pol[m][j])) {
	              a[(m + 24)] += 1.0D;
	            }
	          }
	        }
	      }
	      if ((i <= i1) && (i > i0)) {
	        for (int m = 0; m < 3; m++) {
	          for (int j = 0; j <= 7; j++)
	          {
	            if (str.toString().equals(hyd[m][j])) {
	              a[(m + 27)] += 1.0D;
	            }
	            if (str.toString().equals(nor[m][j])) {
	              a[(m + 30)] += 1.0D;
	            }
	            if (str.toString().equals(pol[m][j])) {
	              a[(m + 33)] += 1.0D;
	            }
	          }
	        }
	      }
	      if ((i <= i2) && (i > i1)) {
	        for (int m = 0; m < 3; m++) {
	          for (int j = 0; j <= 7; j++)
	          {
	            if (str.toString().equals(hyd[m][j])) {
	              a[(m + 36)] += 1.0D;
	            }
	            if (str.toString().equals(nor[m][j])) {
	              a[(m + 39)] += 1.0D;
	            }
	            if (str.toString().equals(pol[m][j])) {
	              a[(m + 42)] += 1.0D;
	            }
	          }
	        }
	      }
	      if ((i <= featureNum) && (i > i2)) {
	        for (int m = 0; m < 3; m++) {
	          for (int j = 0; j <= 7; j++)
	          {
	            if (str.toString().equals(hyd[m][j])) {
	              a[(m + 45)] += 1.0D;
	            }
	            if (str.toString().equals(nor[m][j])) {
	              a[(m + 48)] += 1.0D;
	            }
	            if (str.toString().equals(pol[m][j])) {
	              a[(m + 51)] += 1.0D;
	            }
	          }
	        }
	      }
	    }
	    StringBuffer sb = new StringBuffer();
	    for (int i = 9; i <= 53; i++) {

	    	  double c = a[i] / sequence.length() ;//* 100.0D
	    	  sb.append(c+",");
	        
	      
	    }
	    CTD_D1 = sb.toString();
	    return  CTD_D1;
	    
//	    for (int n = 9; n <= 53; n++)
//	    {
//	      double b = a[n];
//	      double c = b / featureNum * 100.0D;
//	      a[n] = c;
//	    }
	  }
	  
	//计算每行序列的（1,25%，50%，75%,100%）时每个组的每种属性占序列的百分比
	public String CTD_D(String sequence)//CTD的改进
	  {
        double featureNum = sequence.length();
	    
	    double i0 = Math.ceil(featureNum / 4.0D) - 1.0D;
	    double i1 = Math.ceil(featureNum / 2.0D) - 1.0D;
	    double i2 = Math.ceil(featureNum / 4.0D * 3.0D) - 1.0D;
	    String str="";
		String  CTD_D = null;
	    for (int i = 0; i < featureNum; i++)
	    {str = sequence.substring(i, i + 1);
	    
	      if (i == 0) {
	        for (int m = 0; m < 3; m++) {
	          for (int j = 0; j <= 7; j++)
	          {
	            if (str.equals(hyd[m][j])) {
	              a[(m + 9)] += 1.0D;
	            }
	            if (str.equals(nor[m][j])) {
	              a[(m + 12)] += 1.0D;
	            }
	            if (str.equals(pol[m][j])) {
	              a[(m + 15)] += 1.0D;
	            }
	          }
	        }
	      }
	      if (i <= i0) {
	        for (int m = 0; m < 3; m++) {
	          for (int j = 0; j <= 7; j++)
	          {
	            if (str.equals(hyd[m][j])) {
	              a[(m + 18)] += 1.0D;
	            }
	            if (str.equals(nor[m][j])) {
	              a[(m + 21)] += 1.0D;
	            }
	            if (str.equals(pol[m][j])) {
	              a[(m + 24)] += 1.0D;
	            }
	          }
	        }
	      }
	      if (i <= i1) {
	        for (int m = 0; m < 3; m++) {
	          for (int j = 0; j <= 7; j++)
	          {
	            if (str.equals(hyd[m][j])) {
	              a[(m + 27)] += 1.0D;
	            }
	            if (str.equals(nor[m][j])) {
	              a[(m + 30)] += 1.0D;
	            }
	            if (str.equals(pol[m][j])) {
	              a[(m + 33)] += 1.0D;
	            }
	          }
	        }
	      }
	      if (i <= i2) {
	        for (int m = 0; m < 3; m++) {
	          for (int j = 0; j <= 7; j++)
	          {
	            if (str.equals(hyd[m][j])) {
	              a[(m + 36)] += 1.0D;
	            }
	            if (str.equals(nor[m][j])) {
	              a[(m + 39)] += 1.0D;
	            }
	            if (str.equals(pol[m][j])) {
	              a[(m + 42)] += 1.0D;
	            }
	          }
	        }
	      }
	      if (i <= featureNum) {
	        for (int m = 0; m < 3; m++) {
	          for (int j = 0; j <= 7; j++)
	          {
	            if (str.equals(hyd[m][j])) {
	              a[(m + 45)] += 1.0D;
	            }
	            if (str.equals(nor[m][j])) {
	              a[(m + 48)] += 1.0D;
	            }
	            if (str.equals(pol[m][j])) {
	              a[(m + 51)] += 1.0D;
	            }
	          }
	        }
	      }
	    }
	    StringBuffer sb = new StringBuffer();
	    for (int i = 9; i <= 53; i++) {

	    	  double c = a[i] / sequence.length() ;//* 100.0D
	    	  sb.append(c+",");
	        
	      
	    }
	    CTD_D = sb.toString();
	    return  CTD_D;
//	    for (int n = 9; n <= 53; n++)
//	    {
//	      double b = a[n];
//	      double c = b / featureNum * 100.0D;
//	      a[n] = c;
//	    }
	  }
	
	

	public static void main(String[] args) {
	
	}

}
