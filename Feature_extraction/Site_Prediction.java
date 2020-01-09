package Fuse;

import java.util.ArrayList;
import java.util.Arrays;

public class Site_Prediction {
	
	static String[] AA_1gram = { "A", "R", "N", "D", "C", "Q", "E", "G", "H", "I", "L", 
		      "K", "M", "F", "P", "S", "T", "W", "Y", "V" };
	/*ngram_p
	 */
	public  String  ngram_p(String sequence)
	{
	double[] a = new double[20];
	String str="";
	String  ngram = null;
	for (int i = 0; i < sequence.length(); i++) {
		   str = sequence.substring(i, i + 1);
	    for (int m = 0; m < 20; m++) {
	     if(str.equals(AA_1gram[m])) {a[m]++;}
	    }
	  }
	StringBuffer sb = new StringBuffer();
	for (int i = 0; i < 20; i++) {

		  double c = a[i] / sequence.length() ;//* 100.0D
		  a[i]=0;
		  sb.append(c+",");
	  

	}
	ngram = sb.toString();
	return  ngram;

	}
	/*Bit20  20
	 */
	public String TwentyBitSequenceFeature(String Sequence)
	  {
	    String Seq = Sequence;
	    String TwentyBitSequenceFeatures = null;
	    String[] AA = { "A", "C", "D", "E", "F", "G", "H", "I", "K", "L", "M", 
	      "N", "P", "Q", "R", "S", "T", "V", "W", "Y" };
	    String[] twentyBitArrary = new String[20];
	    for (int i = 0; i < 20; i++)
	    {
	      StringBuffer sb = new StringBuffer();
	      for (int j = 0; j < 20; j++) {
	        if (i == j) {
	          sb.append("1,");
	        } else {
	          sb.append("0,");
	        }
	      }
	      twentyBitArrary[i] = sb.toString();
	    }
	    StringBuffer sb = new StringBuffer();
	    for (int i = 0; i < Seq.length(); i++) {
	      for (int j = 0; j < 20; j++) {
	        if (String.valueOf(Seq.charAt(i)).equals(AA[j]))
	        {
	          sb.append(twentyBitArrary[j]);
	          break;
	        }
	      }
	    }
	    TwentyBitSequenceFeatures = sb.toString();
	    return TwentyBitSequenceFeatures;
	  }
	  
	
	/*Bit21  21
	 */  
	  public String Bit21Feature(String Seq)
	  {
	    //String[][] PropertyClass = new String[7][3];
	    String[] property = { "ACFGHILMNPQSTVWY", "DE", "KR", "CFILMVW", "AGHPSTY", "DEKNQR", "ACDGPST", "EILNQV", "FHKMRWY", "CFILMVWY", "AGPST", "DEHKNQR", "ADGST", "CEILNPQV", "FHKMRWY", "DGNPS", "AEHKLMQR", "CFITVWY", "ACFGILVW", "HMPSTY", "DEKNRQ" };
	   // int m = 0;
	    StringBuffer sb = new StringBuffer();
	    for (int k = 0; k < Seq.length(); k++) {
	      for (int i = 0; i < 21; i++) {
	        if (property[i].contains(String.valueOf(Seq.charAt(k)))) {
	          sb.append("1,");
	        } else {
	          sb.append("0,");
	        }
	      }
	    }
	    String Bit21Feature = sb.toString();
	    return Bit21Feature;
	  }
	  
	  
		
		/* Overlapping   10
		 */ 
	  public String OverlappingPropertiesFeatures(String Sequence)
	  {
	    String Seq = Sequence;
	    String OverlappingPropertiesFeatures = null;
	    String[] PropertyClass = { "NQSDECTKRHYW", "KHR", "DE", "KHRDE", "AGCTIVLKHFYWM", "IVL", "FYWH", "PNDTCAGSV", "ASGC", "P" };
	    
	    StringBuffer sb = new StringBuffer();
	    for (int j = 0; j < Seq.length(); j++) {
	      for (int i = 0; i < 10; i++) {
	        if (PropertyClass[i].contains(String.valueOf(Seq.charAt(j)))) {
	          sb.append("1,");
	        } else {
	          sb.append("0,");
	        }
	      }
	    }
	    OverlappingPropertiesFeatures = sb.toString();
	    
	    return OverlappingPropertiesFeatures;
	  }
	  
	  
	  /* InformationTheoryFeatures
		 */ 
	  public  String InformationTheoryFeatures(String Sequence)
	  {
	    String InformationTheoryFeatures = null;
	    String[] AA = { "A", "C", "D", "E", "F", "G", "H", "I", "K", "L", "M", 
	      "N", "P", "Q", "R", "S", "T", "V", "W", "Y" };
	    
	    int[] CountAA = new int[20];
	    StringBuffer sb = new StringBuffer();
	    for (int i = 0; i < Sequence.length(); i++) {
	      for (int j = 0; j < 20; j++) {
	        if (AA[j].equals(String.valueOf(Sequence.charAt(i))))
	        {
	          CountAA[j] += 1;
	          break;
	        }
	      }
	    }
	    float temp_SE = 0.0F;
	    float temp_RSE = 0.0F;
	    for (int j = 0; j < 20; j++) {
	      if (CountAA[j] != 0)
	      {
	        temp_SE = (float)(temp_SE - 1.0D * CountAA[j] / Sequence.length() * (Math.log(1.0D * CountAA[j] / Sequence.length()) / Math.log(2.0D)));
	        temp_RSE = (float)(temp_RSE + 1.0D * CountAA[j] / Sequence.length() * (Math.log(1.0D * CountAA[j] / Sequence.length() * (Sequence.length() - 1) / 2.0D) / Math.log(2.0D)));
	      }
	    }
	    sb.append(temp_SE + ",");
	    sb.append(temp_RSE + ",");
	    sb.append(temp_SE - temp_RSE + ",");
	    
	    InformationTheoryFeatures = sb.toString();
	    
	    return InformationTheoryFeatures;
	  }
	
	  
	  /* KSkipNGramsFeature   400
		 */
	  public  String KSkipNGramsFeature(String Seq, int Skip_k, int feaType)
	  {
	    int k = Skip_k;
	    int n = feaType;
	    String KskipNgrams = null;
	    String[] AA_1gram = { "A", "R", "N", "D", "C", "Q", "E", "G", "H", "I", "L", 
	      "K", "M", "F", "P", "S", "T", "W", "Y", "V" };
	    String[] AA_2gram = new String[400];
	    int[] AA_2gram_counts = new int[400];
	    String[] AA_3gram = new String[8000];
	    int[] AA_3gram_counts = new int[8000];
	    int m = 0;
	    if (n == 2)
	    {
	      int i = 0;
	      int j = 0;
	      for (i = 0; i < 20; i++) {
	        for (j = 0; j < 20; j++)
	        {
	          AA_2gram[m] = AA_1gram[i].concat(AA_1gram[j]);
	          m++;
	        }
	      }
	      i = 0;
	      char[] seq = Seq.toCharArray();
	      int TotalCounts = 0;
	      for (int h = 0; h <= k; h++) {
	        for (i = 0; i < Seq.length(); i++)
	        {
	          //StringBuffer sb = new StringBuffer();
	          String grams = null;
	          if (i + 1 + h > Seq.length() - 1) {
	            break;
	          }
	          grams = String.valueOf(seq[i]) + String.valueOf(seq[(i + 1 + h)]);
	          for (m = 0; m < 400; m++) {
	            if (grams.equals(AA_2gram[m]))
	            {
	              AA_2gram_counts[m] += 1;
	              break;
	            }
	          }
	          TotalCounts++;
	        }
	      }
	      StringBuffer sb = new StringBuffer();
	      for (m = 0; m < 400; m++) {
	        sb.append(String.valueOf(1.0D * AA_2gram_counts[m] / TotalCounts) + ",");
	      }
	      KskipNgrams = sb.toString();
	    }
	    if (n == 3)
	    {
	      int i = 0;
	      int j = 0;
	      int g = 0;
	      for (g = 0; g < 20; g++) {
	        for (i = 0; i < 20; i++) {
	          for (j = 0; j < 20; j++)
	          {
	            AA_3gram[m] = AA_1gram[i].concat(AA_1gram[j]).concat(AA_1gram[g]);
	            m++;
	          }
	        }
	      }
	      i = 0;
	      char[] seq = Seq.toCharArray();
	      int TotalCounts = 0;
	      for (int h = 0; h <= k; h++) {
	        for (i = 0; i < Seq.length(); i++)
	        {
	          //StringBuffer sb = new StringBuffer();
	          String grams = null;
	          if (i + 2 + 2 * h > Seq.length() - 1) {
	            break;
	          }
	          grams = String.valueOf(seq[i]) + String.valueOf(seq[(i + 1 + h)]) + String.valueOf(seq[(i + 2 + 2 * h)]);
	          for (m = 0; m < 8000; m++) {
	            if (grams.equals(AA_3gram[m]))
	            {
	              AA_3gram_counts[m] += 1;
	              break;
	            }
	          }
	          TotalCounts++;
	        }
	      }
	      StringBuffer sb = new StringBuffer();
	      for (m = 0; m < 8000; m++) {
	        sb.append(String.valueOf(1.0D * AA_3gram_counts[m] / TotalCounts) + ",");
	      }
	      KskipNgrams = sb.toString();
	    }
	    return KskipNgrams;
	  }
	  

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
