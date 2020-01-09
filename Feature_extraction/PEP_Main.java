package PEP;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.ArrayList;

public class PEP_Main
{
  static int Ntnum = 5;//NT取几位
  static int countnum = 0;
  static int Ntstart = 1;//NT第几位开始
  static int m;
  static ArrayList<String> list = new ArrayList();
  static ArrayList<String> list_title = new ArrayList();
  static ArrayList<String> NT = new ArrayList();
  static ArrayList<String> CT = new ArrayList();
  static ArrayList<String> NTCT = new ArrayList();
  
  public static String getRealPath()
  {
    String realPath = PEP_Main.class.getClassLoader().getResource("")
      .getFile();
    File file = new File(realPath);
    realPath = file.getAbsolutePath();
    try
    {
      realPath = URLDecoder.decode(realPath, "utf-8");
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
    return realPath;
  }
  
  public void Site_P(String name, int c, ArrayList<String> instead, basis rbs1, Site_Prediction rsp1, int m, String systemPath1)
  {
    try
    {
      BufferedWriter bw = new BufferedWriter(new FileWriter(systemPath1 + "Bit20" + name + "(" + (m + 1) + ")" + countnum + ".arff"));
      rbs1.bufferW_arff(Ntstart * 20 * c, bw);
      for (int i = 0; i < instead.size() / Ntnum; i++)
      {
        String TwentyBitSequenceFeaturesNT = rsp1.TwentyBitSequenceFeature(((String)instead.get(m + Ntnum * i)).toUpperCase());
        bw.write(TwentyBitSequenceFeaturesNT);
        bw.write((String)list_title.get(i));
        bw.newLine();
        bw.flush();
      }
      countnum += 1;
      bw.close();
      
      BufferedWriter bw1 = new BufferedWriter(new FileWriter(systemPath1 + "Bit21" + name + "(" + (m + 1) + ")" + countnum + ".arff"));
      rbs1.bufferW_arff(Ntstart * 21 * c, bw1);
      for (int i = 0; i < instead.size() / Ntnum; i++)
      {
        String Bit21Feature = rsp1.Bit21Feature(((String)instead.get(m + Ntnum * i)).toUpperCase());
        bw1.write(Bit21Feature);
        bw1.write((String)list_title.get(i));
        bw1.newLine();
        bw1.flush();
      }
      countnum += 1;
      bw1.close();
      
      BufferedWriter bw2 = new BufferedWriter(new FileWriter(systemPath1 + "OV" + name + "(" + (m + 1) + ")" + countnum + ".arff"));
      rbs1.bufferW_arff(Ntstart * 10 * c, bw2);
      for (int i = 0; i < instead.size() / Ntnum; i++)
      {
        String OverlappingPropertiesFeatures = rsp1.OverlappingPropertiesFeatures(((String)instead.get(m + Ntnum * i)).toUpperCase());
        bw2.write(OverlappingPropertiesFeatures);
        bw2.write((String)list_title.get(i));
        bw2.newLine();
        bw2.flush();
      }
      countnum += 1;
      bw2.close();
      
      BufferedWriter bw3 = new BufferedWriter(new FileWriter(systemPath1 + "IT" + name + "(" + (m + 1) + ")" + countnum + ".arff"));
      rbs1.bufferW_arff(3, bw3);
      for (int i = 0; i < instead.size() / Ntnum; i++)
      {
        String InformationTheoryFeatures = "";
        if (m == 0) {
          InformationTheoryFeatures = rsp1.InformationTheoryFeatures(((String)instead.get(m + Ntnum * i)).toUpperCase() + ((String)instead.get(m + Ntnum * i)).toUpperCase());
        } else {
          InformationTheoryFeatures = rsp1.InformationTheoryFeatures(((String)instead.get(m + Ntnum * i)).toUpperCase());
        }
        bw3.write(InformationTheoryFeatures);
        bw3.write((String)list_title.get(i));
        bw3.newLine();
        bw3.flush();
      }
      countnum += 1;
      bw3.close();
      
      BufferedWriter bw4 = new BufferedWriter(new FileWriter(systemPath1 + "KSkipN" + name + "(" + (m + 1) + ")" + countnum + ".arff"));
      rbs1.bufferW_arff(400, bw4);
      for (int i = 0; i < instead.size() / Ntnum; i++)
      {
        String KSkipN = "";
        if (m == 0) {
          KSkipN = (String)instead.get(m + Ntnum * i) + (String)instead.get(m + Ntnum * i);
        } else {
          KSkipN = (String)instead.get(m + Ntnum * i);
        }
        int Skip_k = 0;
        int feaType = 2;
        String KSkipNGramsFeature = rsp1.KSkipNGramsFeature(KSkipN, Skip_k, feaType);
        bw4.write(KSkipNGramsFeature);
        bw4.write((String)list_title.get(i));
        bw4.newLine();
        bw4.flush();
      }
      countnum += 1;
      bw4.close();
      

      BufferedWriter bw5 = new BufferedWriter(new FileWriter(systemPath1 + "Ngram" + name + "(" + (m + 1) + ")" + countnum + ".arff"));
      rbs1.bufferW_arff(20, bw5);
      for (int i = 0; i < instead.size() / Ntnum; i++)
      {
        String ngram = (String)instead.get(m + Ntnum * i);
        String ngram_n = rsp1.ngram_p(ngram);
        bw5.write(ngram_n);
        bw5.write((String)list_title.get(i));
        bw5.newLine();
        bw5.flush();
      }
      countnum += 1;
      bw5.close();
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
  }
  
  public void initList(String fileName)
  {
    try
    {
      BufferedReader br = new BufferedReader(new FileReader(fileName));
      while (br.ready())
      {
        String str1 = "";
        String str2 = "";
        String str = "";
        
        String title = br.readLine();
        if(title.isEmpty()) {title = br.readLine();}else {continue;}
        String line1 = br.readLine(); if(line1.isEmpty()) {line1 = br.readLine();}else {continue;}
        list.add(line1.trim());
        for (int i = Ntstart; i < Ntnum + Ntstart; i++)
        {
          str1 = line1.substring(0, i);
          NT.add(str1.trim());
          
          str2 = line1.substring(line1.length() - i, line1.length());
          CT.add(str2.trim());
          
          str = str1 + str2;
          NTCT.add(str.trim());
        }
        if ((title.startsWith(">N")) && (!title.substring(title.length() - 2, title.length() - 1).equals("|"))) {
          list_title.add("1");
        } else if ((title.startsWith(">P")) && (!title.substring(title.length() - 2, title.length() - 1).equals("|"))) {
          list_title.add("0");
        } else if (!title.split("\\|")[1].equals("1")) {
        	   list_title.add("0");
        }
        else if (title.split("\\|")[1].equals("0")) {
            list_title.add("0");
          } else if (!title.split("\\|")[1].equals("kghg")) {
            list_title.add(title.split("\\|")[1]);
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
  
  public static void main(String[] args)
    throws IOException
  {
    String[] Peptides = { "AAP", "AB", "AC", "AI", 
      "AV", "CP", "QS", 
      "SB" };
    






//    String intputFile = "C:\\Users\\Administrator\\eclipse-workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\PEPred-Suite\\program/1.txt";
//    String Pep = "all";
//    String systemPath1 = "C:\\Users\\Administrator\\eclipse-workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\PEPred-Suite\\program";
    String intputFile = args[0];
    String Pep = args[1];
    String systemPath1 = "/mnt/tomcat/webapps/PEPred-Suite/program/";
    
//    String intputFile = "C:\\Users\\Administrator\\Desktop\\新建文件夹\\fasta.txt";
//    String Pep ="AAP";
//    String systemPath1 = "C:\\Users\\Administrator\\Desktop\\新建文件夹\\";
    


    countnum = 10;
    basis rbs = new basis();
    PEP_Main rm = new PEP_Main();
    
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
    for (int ol = 0; ol < Peptides.length; ol++)
    {
      String systemPath = "";
      if (Pep.equals("all"))
      {
        systemPath = systemPath1 + "/" + Peptides[ol] + "_DataSet/arff//";
      }
      else
      {
        systemPath = systemPath1 + "/" + Pep + "_DataSet/arff//";ol += 7;
      }
      rm.initList(intputFile);
      


      METH_Ngram rngm = new METH_Ngram();
      for (int m = 1; m < 2; m++)
      {
        BufferedWriter bwngm = new BufferedWriter(new FileWriter(systemPath + "/Ngram(N=1)" + m + ".arff"));
        rbs.bufferW_arff(20, bwngm);
        for (int i = 0; i < list.size(); i++)
        {
          String c = (String)list.get(i);
          String ngram_p = rngm.ngram_p(c);
          System.out.println(list_title.size());
          bwngm.write(ngram_p + (String)list_title.get(i));
          bwngm.newLine();
          rngm.cleara();
        }
        bwngm.close();
      }
      for (int m = 2; m < 3; m++)
      {
        BufferedWriter bwngm = new BufferedWriter(new FileWriter(systemPath + "/Ngram(N=2)" + m + ".arff"));
        rbs.bufferW_arff(20, bwngm);
        for (int i = 0; i < list.size(); i++)
        {
          String c = (String)list.get(i);
          String ngram_n = rngm.ngram_n(c);
          bwngm.write(ngram_n + (String)list_title.get(i));
          bwngm.newLine();
          rngm.cleara();
        }
        bwngm.close();
      }
      METH_CTD rctd = new METH_CTD();
      for (int m = 3; m < 4; m++)
      {
        BufferedWriter bwctd = new BufferedWriter(new FileWriter(systemPath + "/CTD" + m + ".arff"));
        rbs.bufferW_arff(63, bwctd);
        for (int i = 0; i < list.size(); i++)
        {
          String c = (String)list.get(i);
          String CTD_C = rctd.CTD_C(c);
          String CTD_T = rctd.CTD_T(c);
          String CTD_D = "";
          if (m == 50) {
            CTD_D = rctd.CTD_D(c);
          } else {
            CTD_D = rctd.CTD_D1(c);
          }
          String result = CTD_C + CTD_T + CTD_D;
          
          bwctd.write(result);
          bwctd.write((String)list_title.get(i));
          bwctd.newLine();
          rctd.cleara();
          bwctd.flush();
        }
        bwctd.close();
      }
      for (int m = 4; m < 8; m++)
      {
        METH_GGAP rgap = new METH_GGAP();
        BufferedWriter bwgap = new BufferedWriter(new FileWriter(systemPath + "/GAP" + m + ".arff"));
        rbs.bufferW_arff(400, bwgap);
        for (int i = 0; i < list.size(); i++)
        {
          String GAP = rgap.G_gap((String)list.get(i), AA_2gram, m - 4);
          bwgap.write(GAP);
          bwgap.write((String)list_title.get(i));
          bwgap.newLine();
          rgap.cleara();
          bwgap.flush();
        }
        bwgap.close();
      }
      METH_400D r400D = new METH_400D();
      for (int m = 8; m < 9; m++)
      {
        BufferedWriter bw400D = new BufferedWriter(new FileWriter(systemPath + "/400D" + m + ".arff"));
        rbs.bufferW_arff(400, bw400D);
        for (int i = 0; i < list.size(); i++)
        {
          String c = (String)list.get(i);
          int Skip_k = c.length() - 1;
          int feaType = 2;
          String Fskip = r400D.AdaptiveSkipNGramFeatures(c, Skip_k, feaType);
          bw400D.write(Fskip + (String)list_title.get(i));
          bw400D.newLine();
        }
        bw400D.close();
      }
      for (int m = 9; m < 10; m++)
      {
        BufferedWriter bw188D = new BufferedWriter(new FileWriter(systemPath + "/188D" + m + ".arff"));
        rbs.bufferW_arff(188, bw188D);
        for (int i = 0; i < list.size(); i++)
        {
          String c = (String)list.get(i);
          METH_188D r188D = new METH_188D(c.toUpperCase());
          bw188D.write(r188D.run() + (String)list_title.get(i));
          bw188D.newLine();
        }
        bw188D.close();
      }
      Site_Prediction rsp = new Site_Prediction();
      for (int m = 0; m < 5; m++)
      {
        rm.Site_P("NT", m + 1, NT, rbs, rsp, m, systemPath);
        rm.Site_P("CT", m + 1, CT, rbs, rsp, m, systemPath);
        rm.Site_P("NTCT", 2 * (m + 1), NTCT, rbs, rsp, m, systemPath);
      }
      NT.clear();
      list_title.clear();
      CT.clear();
      NTCT.clear();
      list.clear();
      

      countnum = 10;
      m = 0;
    }
  }
}
