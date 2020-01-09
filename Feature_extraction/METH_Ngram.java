package Fuse;


public class METH_Ngram
{
	static double[] a = new double[20];
	static String[] AA_1gram = { "A", "R", "N", "D", "C", "Q", "E", "G", "H", "I", "L", 
		      "K", "M", "F", "P", "S", "T", "W", "Y", "V" };
	  public void cleara()
	  {
	    for (int n = 0; n < 20; n++) {
	      a[n] = 0.0D;
	    }
	  }
	public  String  ngram_p(String sequence)
{
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
//  for (int n = 0; n <= 8; n++)
//  {
//    double b = a[n];
//    double c = b / allNumber.size() * 100.0D;
//    a[n] = c;
//  }
}
	public  String  ngram_n(String sequence)
	{
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

		  double c = a[i]  ;//* 100.0D
		  sb.append(c+",");
	  

	}
	ngram = sb.toString();
	return  ngram;
	//  for (int n = 0; n <= 8; n++)
	//  {
//	    double b = a[n];
//	    double c = b / allNumber.size() * 100.0D;
//	    a[n] = c;
	//  }
	}
}
