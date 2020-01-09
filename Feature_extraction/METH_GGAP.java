package Fuse;


public class METH_GGAP {
	static double[] b = new double[400];
	 /*清空数组
	 */
  public void cleara()
  {
    for (int n = 0; n < 400; n++) {
      b[n] = 0.0D;
    }
  }
	/* GAP两序列对比
	 */  
	  public String G_gap(String line1, String[] a ,int g)
	  {       String  GAP = null;
		      int t=g+1;
		      int m=t+1;
		      String str = "";
		      str = line1.toUpperCase();
		     
		      for (int i = 0; i < line1.length()-t; i++)
		      {
		    	String str1= str.substring(i, i + 1) + str.substring(i + t, i + m);
		      
		    	//System.out.println(str1);
		        for (int j = 0; j < a.length; j++) {
		        if (str1.equals(a[j]))
		        {
		          
		          b[j] += 1.0D;
		        }
		        }
		      }		    

		StringBuffer sb = new StringBuffer();
	    for (int n = 0; n < a.length; n++)
	    {
	      double d = b[n];
	      double c = d / (line1.length()-t);
	      sb.append(c+",");
	    }
	    GAP = sb.toString();
	    return  GAP;
	  }
	  

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//for (int m = 46; m < 50; m++) {
			METH_GGAP rgap = new METH_GGAP();
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
  
//		    //GGAP形成arff
		
//				 for (int m = 0; m < 4; m++) {
//						METH_GGAP rgap = new METH_GGAP();
//				    	  BufferedWriter bw4 = new BufferedWriter(new FileWriter(systemPath + "/GAP"+(m)+".arff"));
//				    	  rbs.bufferW_arff(400, bw4);
//				      for (int i = 0; i < list.size(); i++)
//				      {
//				    	   //rgap.dealListGgap((String)list.get(i),m);      
//				    	  String GAP=rgap.G_gap((String)list.get(i), AA_2gram, m);     
//				    	   bw4.write(GAP);
//				           bw4.write((String)list_title.get(i));
//				           bw4.newLine();
//				           rgap.cleara();
//				           bw4.flush();
//				           }
//				      bw4.close();
//				 }
	}
}
