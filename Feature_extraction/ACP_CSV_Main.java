
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class ACP_CSV_Main
{
// private static String systemPath="C:\\Users\\Administrator\\eclipse-workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\ACPred-FL\\ACPred_FL";
 private static String systemPath = "C:\\Users\\Administrator\\Desktop\\ACP\\新建文件夹";
  //private static String systemPath = "/mnt/tomcat/webapps/ACPred-FL/ACPred_FL";
  static int Ntnum =11;
  static int num =0;
  static String input ="/ACP164.txt";//   /train/train.txt    /test/test.txt
  static int x = 0;
  static int y = 0;
  static String n;
  static double[] a = new double[63];
  static double[] b = new double[400];
  static ArrayList<String> list = new ArrayList<String>();
  static ArrayList<String> list_CTD = new ArrayList<String>();
  static ArrayList<String> listGap = new ArrayList<String>();
  static ArrayList<String> listExamp = new ArrayList<String>();
  static ArrayList<String> list_title = new ArrayList<String>();
  static ArrayList<String> NT = new ArrayList<String>();
  static ArrayList<String> CT = new ArrayList<String>();
  static ArrayList<String> NTCT = new ArrayList<String>();
  static String Pall = "ACDEFGHIKLMNPQRSTVWY";
  static String[][] hyd = { { "R", "K", "E", "D", "Q", "N", " ", " " }, { "G", "A", "S", "T", "P", "H", "Y", " " }, { "C", "V", "L", "I", "M", "F", "W", " " } };
  static String[][] nor = { { "G", "A", "S", "C", "T", "P", "D", " " }, { "N", "V", "E", "Q", "I", "L", " ", " " }, { "M", "H", "K", "F", "R", "Y", "W", " " } };
  static String[][] pol = { { "L", "I", "F", "W", "C", "M", "V", "Y" }, { "P", "A", "T", "G", "S", " ", " ", " " }, { "H", "Q", "R", "K", "N", "E", "D", " " } };
  
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
        list.add(line1);
        for (int i=1;i<=Ntnum;i++) {
        str1 = line1.substring(0, i);
        NT.add(str1);
       // System.out.println(str1);
        str2 = line1.substring(line1.length()-i, line1.length());
        CT.add(str2);
        str=str1+str2;
        NTCT.add(str);
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
  
  public void bufferW_arff(int a, BufferedWriter bw2)
  {
    try
    {
      bw2.write("@relation Protein");
      bw2.newLine();
      for (int i = 1; i <= a; i++)
      {
        bw2.write("@attribute Feature" + i + " real");
        bw2.newLine();
      }
      bw2.write("@attribute class ");
      String attributeClass = "0";
      for (int i = 0; i < 1; i++) {
        attributeClass = attributeClass + "," + (i + 1);
      }
      bw2.write("{" + attributeClass + "}");
      bw2.newLine();
      bw2.write("@data");
      bw2.newLine();
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
  }
  
  public void dealListCTD(String line1)
  {
    try
    {
      String str = "";
      str = line1;
      String[] dictionary = new String[line1.length()];
      for (int i = 0; i < line1.length(); i++)
      {
        dictionary[i] = line1.substring(i, i + 1);
        str = dictionary[i];
        list_CTD.add(str);
      }
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
  }
  
  public void dealListGgap(String line1,int g)
  {
    try
    {int t=g+1;
    int m=t+1;
      String str = "";
      str = line1;
      String[] dictionary = new String[line1.length()];
      for (int i = 0; i < line1.length() - t; i++)
      {
        dictionary[i] = (line1.substring(i, i + 1) + line1.substring(i + t, i + m));
        str = dictionary[i];
        //System.out.println(str);
        listGap.add(str);
      }
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
  }
  
  public void dealS(double[] f, BufferedWriter bw)
  {
    String h = Arrays.toString(f);
    String s = h.replaceAll(".0]", "");
    String p = s.replaceAll("0.0,", "0,");
    String m = p.replaceAll("]", "");
    String d1 = m.replaceAll(" ", "");
    try
    {
      bw.write(d1, 1, d1.length() - 1);
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
  }
  
  public void CTD_C(ArrayList<String> allNumber)
  {
    for (int i = 0; i < allNumber.size(); i++) {
      for (int m = 0; m < 3; m++) {
        for (int j = 0; j <= 7; j++)
        {
          if (((String)allNumber.get(i)).equals(hyd[m][j])) {
            a[m] += 1.0D;
          }
          if (((String)allNumber.get(i)).toString().equals(nor[m][j])) {
            a[(m + 3)] += 1.0D;
          }
          if (((String)allNumber.get(i)).toString().equals(pol[m][j])) {
            a[(m + 6)] += 1.0D;
          }
        }
      }
    }
    for (int n = 0; n <= 8; n++)
    {
      double b = a[n];
      double c = b / allNumber.size() * 100.0D;
      a[n] = c;
    }
  }
  
  public void CTD_T(ArrayList<String> allNumber)
  {
    for (int i = 0; i < allNumber.size() - 1; i++) {
      for (int m = 0; m <= 7; m++) {
        for (int j = 0; j <= 7; j++)
        {
          if (((((String)allNumber.get(i)).toString() + (String)allNumber.get(i + 1)).toString().equals(hyd[0][m] + hyd[1][j])) || ((((String)allNumber.get(i)).toString() + (String)allNumber.get(i + 1)).toString().equals(hyd[1][m] + hyd[0][j]))) {
            a[54] += 1.0D;
          }
          if (((((String)allNumber.get(i)).toString() + (String)allNumber.get(i + 1)).toString().equals(hyd[0][m] + hyd[2][j])) || ((((String)allNumber.get(i)).toString() + (String)allNumber.get(i + 1)).toString().equals(hyd[2][m] + hyd[0][j]))) {
            a[55] += 1.0D;
          }
          if (((((String)allNumber.get(i)).toString() + (String)allNumber.get(i + 1)).toString().equals(hyd[1][m] + hyd[2][j])) || ((((String)allNumber.get(i)).toString() + (String)allNumber.get(i + 1)).toString().equals(hyd[2][m] + hyd[1][j]))) {
            a[56] += 1.0D;
          }
          if (((((String)allNumber.get(i)).toString() + (String)allNumber.get(i + 1)).toString().equals(nor[0][m] + nor[1][j])) || ((((String)allNumber.get(i)).toString() + (String)allNumber.get(i + 1)).toString().equals(nor[1][m] + nor[0][j]))) {
            a[57] += 1.0D;
          }
          if (((((String)allNumber.get(i)).toString() + (String)allNumber.get(i + 1)).toString().equals(nor[0][m] + nor[2][j])) || ((((String)allNumber.get(i)).toString() + (String)allNumber.get(i + 1)).toString().equals(nor[2][m] + nor[0][j]))) {
            a[58] += 1.0D;
          }
          if (((((String)allNumber.get(i)).toString() + (String)allNumber.get(i + 1)).toString().equals(nor[1][m] + nor[2][j])) || ((((String)allNumber.get(i)).toString() + (String)allNumber.get(i + 1)).toString().equals(nor[2][m] + nor[1][j]))) {
            a[59] += 1.0D;
          }
          if (((((String)allNumber.get(i)).toString() + (String)allNumber.get(i + 1)).toString().equals(pol[0][m] + pol[1][j])) || ((((String)allNumber.get(i)).toString() + (String)allNumber.get(i + 1)).toString().equals(pol[1][m] + pol[0][j]))) {
            a[60] += 1.0D;
          }
          if (((((String)allNumber.get(i)).toString() + (String)allNumber.get(i + 1)).toString().equals(pol[0][m] + pol[2][j])) || ((((String)allNumber.get(i)).toString() + (String)allNumber.get(i + 1)).toString().equals(pol[2][m] + pol[0][j]))) {
            a[61] += 1.0D;
          }
          if (((((String)allNumber.get(i)).toString() + (String)allNumber.get(i + 1)).toString().equals(pol[1][m] + pol[2][j])) || ((((String)allNumber.get(i)).toString() + (String)allNumber.get(i + 1)).toString().equals(pol[2][m] + pol[1][j]))) {
            a[62] += 1.0D;
          }
        }
      }
    }
    for (int n = 54; n <= 62; n++)
    {
      double b = a[n];
      double c = b / (allNumber.size() - 1) * 100.0D;
      a[n] = c;
    }
  }
  
  public void CTD_D1(ArrayList<String> allNumber)//CTD的改进
  {
    double featureNum = allNumber.size();
    
    double i0 = Math.ceil(featureNum / 4.0D) - 1.0D;
    double i1 = Math.ceil(featureNum / 2.0D) - 1.0D;
    double i2 = Math.ceil(featureNum / 4.0D * 3.0D) - 1.0D;
    for (int i = 0; i < featureNum; i++)
    {
      if (i == 0) {
        for (int m = 0; m < 3; m++) {
          for (int j = 0; j <= 7; j++)
          {
            if (((String)allNumber.get(i)).toString().equals(hyd[m][j])) {
              a[(m + 9)] += 1.0D;
            }
            if (((String)allNumber.get(i)).toString().equals(nor[m][j])) {
              a[(m + 12)] += 1.0D;
            }
            if (((String)allNumber.get(i)).toString().equals(pol[m][j])) {
              a[(m + 15)] += 1.0D;
            }
          }
        }
      }
      if ((i <= i0) && (i > 0)) {
        for (int m = 0; m < 3; m++) {
          for (int j = 0; j <= 7; j++)
          {
            if (((String)allNumber.get(i)).toString().equals(hyd[m][j])) {
              a[(m + 18)] += 1.0D;
            }
            if (((String)allNumber.get(i)).toString().equals(nor[m][j])) {
              a[(m + 21)] += 1.0D;
            }
            if (((String)allNumber.get(i)).toString().equals(pol[m][j])) {
              a[(m + 24)] += 1.0D;
            }
          }
        }
      }
      if ((i <= i1) && (i > i0)) {
        for (int m = 0; m < 3; m++) {
          for (int j = 0; j <= 7; j++)
          {
            if (((String)allNumber.get(i)).toString().equals(hyd[m][j])) {
              a[(m + 27)] += 1.0D;
            }
            if (((String)allNumber.get(i)).toString().equals(nor[m][j])) {
              a[(m + 30)] += 1.0D;
            }
            if (((String)allNumber.get(i)).toString().equals(pol[m][j])) {
              a[(m + 33)] += 1.0D;
            }
          }
        }
      }
      if ((i <= i2) && (i > i1)) {
        for (int m = 0; m < 3; m++) {
          for (int j = 0; j <= 7; j++)
          {
            if (((String)allNumber.get(i)).toString().equals(hyd[m][j])) {
              a[(m + 36)] += 1.0D;
            }
            if (((String)allNumber.get(i)).toString().equals(nor[m][j])) {
              a[(m + 39)] += 1.0D;
            }
            if (((String)allNumber.get(i)).toString().equals(pol[m][j])) {
              a[(m + 42)] += 1.0D;
            }
          }
        }
      }
      if ((i <= featureNum) && (i > i2)) {
        for (int m = 0; m < 3; m++) {
          for (int j = 0; j <= 7; j++)
          {
            if (((String)allNumber.get(i)).toString().equals(hyd[m][j])) {
              a[(m + 45)] += 1.0D;
            }
            if (((String)allNumber.get(i)).toString().equals(nor[m][j])) {
              a[(m + 48)] += 1.0D;
            }
            if (((String)allNumber.get(i)).toString().equals(pol[m][j])) {
              a[(m + 51)] += 1.0D;
            }
          }
        }
      }
    }
    for (int n = 9; n <= 53; n++)
    {
      double b = a[n];
      double c = b / featureNum * 100.0D;
      a[n] = c;
    }
  }
  
  
  public void CTD_D(ArrayList<String> allNumber)
  {
    double featureNum = allNumber.size();
    
    double i0 = Math.ceil(featureNum / 4.0D) - 1.0D;
    double i1 = Math.ceil(featureNum / 2.0D) - 1.0D;
    double i2 = Math.ceil(featureNum / 4.0D * 3.0D) - 1.0D;
    for (int i = 0; i < featureNum; i++)
    {
      if (i == 0) {
        for (int m = 0; m < 3; m++) {
          for (int j = 0; j <= 7; j++)
          {
            if (((String)allNumber.get(i)).toString().equals(hyd[m][j])) {
              a[(m + 9)] += 1.0D;
            }
            if (((String)allNumber.get(i)).toString().equals(nor[m][j])) {
              a[(m + 12)] += 1.0D;
            }
            if (((String)allNumber.get(i)).toString().equals(pol[m][j])) {
              a[(m + 15)] += 1.0D;
            }
          }
        }
      }
      if (i <= i0) {
        for (int m = 0; m < 3; m++) {
          for (int j = 0; j <= 7; j++)
          {
            if (((String)allNumber.get(i)).toString().equals(hyd[m][j])) {
              a[(m + 18)] += 1.0D;
            }
            if (((String)allNumber.get(i)).toString().equals(nor[m][j])) {
              a[(m + 21)] += 1.0D;
            }
            if (((String)allNumber.get(i)).toString().equals(pol[m][j])) {
              a[(m + 24)] += 1.0D;
            }
          }
        }
      }
      if (i <= i1) {
        for (int m = 0; m < 3; m++) {
          for (int j = 0; j <= 7; j++)
          {
            if (((String)allNumber.get(i)).toString().equals(hyd[m][j])) {
              a[(m + 27)] += 1.0D;
            }
            if (((String)allNumber.get(i)).toString().equals(nor[m][j])) {
              a[(m + 30)] += 1.0D;
            }
            if (((String)allNumber.get(i)).toString().equals(pol[m][j])) {
              a[(m + 33)] += 1.0D;
            }
          }
        }
      }
      if (i <= i2) {
        for (int m = 0; m < 3; m++) {
          for (int j = 0; j <= 7; j++)
          {
            if (((String)allNumber.get(i)).toString().equals(hyd[m][j])) {
              a[(m + 36)] += 1.0D;
            }
            if (((String)allNumber.get(i)).toString().equals(nor[m][j])) {
              a[(m + 39)] += 1.0D;
            }
            if (((String)allNumber.get(i)).toString().equals(pol[m][j])) {
              a[(m + 42)] += 1.0D;
            }
          }
        }
      }
      if (i <= featureNum) {
        for (int m = 0; m < 3; m++) {
          for (int j = 0; j <= 7; j++)
          {
            if (((String)allNumber.get(i)).toString().equals(hyd[m][j])) {
              a[(m + 45)] += 1.0D;
            }
            if (((String)allNumber.get(i)).toString().equals(nor[m][j])) {
              a[(m + 48)] += 1.0D;
            }
            if (((String)allNumber.get(i)).toString().equals(pol[m][j])) {
              a[(m + 51)] += 1.0D;
            }
          }
        }
      }
    }
    for (int n = 9; n <= 53; n++)
    {
      double b = a[n];
      double c = b / featureNum * 100.0D;
      a[n] = c;
    }
  }
  public void cleara(double[] a, int x)
  {
    for (int n = 0; n < x; n++) {
      a[n] = 0.0D;
    }
  }
  
  public static String TwentyBitSequenceFeature(String Sequence)
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
  
  
  
  public static String Bit21Feature(String Seq)
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
  
  
  
  public static String OverlappingPropertiesFeatures(String Sequence)
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
  
  public void exampleList(String line1)
  {
    try
    {
      String str = "";
      str = line1;
      String[] dictionary = new String[line1.length()];
      for (int i = 0; i < line1.length(); i++) {
        dictionary[i] = line1.substring(i, i + 1);
      }
      for (int i = 0; i < line1.length(); i++) {
        for (int m = 0; m < line1.length(); m++)
        {
          str = dictionary[i] + dictionary[m];
          listExamp.add(str);
        }
      }
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
  }
  
  public void G_gap(ArrayList<String> allNumber1, ArrayList<String> allNumber2)
  {
    for (int i = 0; i < allNumber1.size(); i++) {
      for (int j = 0; j < allNumber2.size(); j++) {
        if (((String)allNumber1.get(i)).toString().equals(((String)allNumber2.get(j)).toString()))
        {
          y += 1;
          b[j] += 1.0D;
        }
      }
    }
    for (int n = 0; n < allNumber2.size(); n++)
    {
      double d = b[n];
      double c = d / allNumber1.size();
      b[n] = c;
    }
  }
  
  public static void main(String[] args)
    throws IOException
  {
    try
    {

      ACP_CSV_Main rl = new ACP_CSV_Main();
 
     
      rl.exampleList(Pall);
      
      rl.initList(systemPath + input);
      
//      for (int m = 35; m < 39; m++) {
//    	  BufferedWriter bw4 = new BufferedWriter(new FileWriter(systemPath + "/GAP"+m+".arff"));
//    	  rl.bufferW_arff(400, bw4);
//      for (int i = 0; i < list.size(); i++)
//      {
//    	   rl.dealListGgap((String)list.get(i),(m-35));      
//           rl.G_gap(listGap, listExamp);     
//           listGap.clear();
//           rl.dealS(b, bw4);
//           bw4.write("," + (String)list_title.get(i));
//           bw4.newLine();
//           rl.cleara(b, 400);
//           bw4.flush();
//           }
//      bw4.close();
//      }
      
     
      for (int m = 34; m < 35; m++)
      { BufferedWriter bw5 = new BufferedWriter(new FileWriter(systemPath + "/CTD.csv"));
//      rl.bufferW_arff(63, bw5);
      for (int i = 0; i < list.size(); i++)
      {
           rl.dealListCTD((String)list.get(i));
           rl.CTD_C(list_CTD);
           rl.CTD_T(list_CTD);
          if(m==34) {
           rl.CTD_D(list_CTD);}
          else {
              rl.CTD_D1(list_CTD);
              }
           list_CTD.clear();
           rl.dealS(a, bw5);
//           bw5.write("," + (String)list_title.get(i));
           bw5.newLine();
           rl.cleara(a, 63);
           bw5.flush();
      }
      bw5.close();
      
    }
      
      for (int m = num; m<Ntnum; m++) {
    	  BufferedWriter bw1 = new BufferedWriter(new FileWriter(systemPath + "/bit20nt"+(m+1)+".arff")); 
    	  rl.bufferW_arff((m+1)*20, bw1);
    	  BufferedWriter bw2 = new BufferedWriter(new FileWriter(systemPath + "/bit21nt"+(m+1+Ntnum)+".arff")); 
    	  rl.bufferW_arff((m+1)*21, bw2);
    	  BufferedWriter bw3 = new BufferedWriter(new FileWriter(systemPath + "/OVnt"+(m+1+Ntnum*2)+".arff")); 
    	  rl.bufferW_arff((m+1)*10, bw3);
//    	  BufferedWriter bw6 = new BufferedWriter(new FileWriter(systemPath + "/bit20ct"+(m+1)+".arff")); 
//    	  rl.bufferW_arff((m+1)*20, bw6);
//    	  BufferedWriter bw7 = new BufferedWriter(new FileWriter(systemPath + "/bit21ct"+(m+1)+".arff")); 
//    	  rl.bufferW_arff((m+1)*21, bw7);
//    	  BufferedWriter bw8 = new BufferedWriter(new FileWriter(systemPath + "/OVct"+(m+1)+".arff")); 
//    	  rl.bufferW_arff((m+1)*10, bw8);
//    	  BufferedWriter bw9 = new BufferedWriter(new FileWriter(systemPath + "/bit20ntct"+(m+1)+".arff")); 
//    	  rl.bufferW_arff((m+1)*40, bw9);
//    	  BufferedWriter bw10 = new BufferedWriter(new FileWriter(systemPath + "/bit21ntct"+(m+1+Ntnum)+".arff")); 
//    	  rl.bufferW_arff((m+1)*42, bw10);
//    	  BufferedWriter bw11 = new BufferedWriter(new FileWriter(systemPath + "/OVntct"+(m+1+Ntnum*2)+".arff")); 
//    	  rl.bufferW_arff((m+1)*20, bw11);
    	 // System.out.println(NT.size()/Ntnum);
      for (int i = 0; i < (NT.size()/Ntnum); i++)
    	  
      {
    	 // System.out.println((String)NT.get(m+Ntnum*i));
        String TwentyBitSequenceFeaturesNT = TwentyBitSequenceFeature(((String)NT.get(m+Ntnum*i)).toUpperCase());
        bw1.write(TwentyBitSequenceFeaturesNT);
        bw1.write((String)list_title.get(i));
        bw1.newLine();
        bw1.flush();
        //System.out.println((String)NTCT.get(m+Ntnum*i));
        
        String Bit21FeatureNT = Bit21Feature(((String)NT.get(m+Ntnum*i)).toUpperCase());
        bw2.write(Bit21FeatureNT);
        bw2.write((String)list_title.get(i));      
        bw2.newLine();
        bw2.flush();
        
        String OverlappingPropertiesFeaturesNT = OverlappingPropertiesFeatures(((String)NT.get(m+Ntnum*i)).toUpperCase());
        bw3.write(OverlappingPropertiesFeaturesNT);
        bw3.write((String)list_title.get(i));
        bw3.newLine();
        bw3.flush();
//        
//        String TwentyBitSequenceFeaturesCT = TwentyBitSequenceFeature(((String)CT.get(m+Ntnum*i)).toUpperCase());
//        bw6.write(TwentyBitSequenceFeaturesCT);
//        bw6.write((String)list_title.get(i));
//        bw6.newLine();
//        bw6.flush();
//        
//        String Bit21FeatureCT = Bit21Feature(((String)CT.get(m+Ntnum*i)).toUpperCase());
//        bw7.write(Bit21FeatureCT);
//        bw7.write((String)list_title.get(i));      
//        bw7.newLine();
//        bw7.flush();
//        
//        String OverlappingPropertiesFeaturesCT = OverlappingPropertiesFeatures(((String)CT.get(m+Ntnum*i)).toUpperCase());
//        bw8.write(OverlappingPropertiesFeaturesCT);
//        bw8.write((String)list_title.get(i));
//        bw8.newLine();
//        bw8.flush();
       
//        String TwentyBitSequenceFeaturesNTCT = TwentyBitSequenceFeature(((String)NTCT.get(m+Ntnum*i)).toUpperCase());
//        bw9.write(TwentyBitSequenceFeaturesNTCT);
//        bw9.write((String)list_title.get(i));
//        bw9.newLine();
//        bw9.flush();
//        
//        String Bit21FeatureNTCT = Bit21Feature(((String)NTCT.get(m+Ntnum*i)).toUpperCase());
//        bw10.write(Bit21FeatureNTCT);
//        bw10.write((String)list_title.get(i));      
//        bw10.newLine();
//        bw10.flush();
//        
//        String OverlappingPropertiesFeaturesNTCT = OverlappingPropertiesFeatures(((String)NTCT.get(m+Ntnum*i)).toUpperCase());
//        bw11.write(OverlappingPropertiesFeaturesNTCT);
//        bw11.write((String)list_title.get(i));
//        bw11.newLine();
//        bw11.flush();
        
      
      }
      bw1.close();
      bw2.close();
      bw3.close();
//      bw6.close();
//      bw7.close();
//      bw8.close();
//      bw9.close();
//      bw10.close();
//      bw11.close();
      }
      listExamp.clear();
      list.clear();
      list_title.clear();
      NT.clear();
      
     
    }
    catch (Exception ex)
    {
      ex.getLocalizedMessage();
    }
  }
}
