package Fuse;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;


public class METH_400D
{ 
//private static String systemPath = "C:\\Users\\Administrator\\Desktop\\Peptides\\test/";
//  public static void main(String[] args)
//  {
//    try
//    {
//     
//        BufferedReader br = new BufferedReader(new FileReader(systemPath+"/fasta.txt"));
//        BufferedWriter bw = new BufferedWriter(new FileWriter(systemPath+"/fasta_400D.arff"));
//
//        int num = 0;
//        int Skip_k = 0;
//        int feaType = 2;
//        StringBuffer sb = new StringBuffer();
//        while (br.ready())
//        {
//          String line = br.readLine();
//          
//          if (line.length() > 0)
//          {
//            if (!line.contains(">")) {
//              sb.append(line.trim());
//            }
//            if (line.contains(">"))
//            {
//              num++;
//              if (num >= 2)
//              {
//                String seq = sb.toString().trim();
//                
//                sb = new StringBuffer();
//                Skip_k = seq.length() - 1;
//                String Fskip = AdaptiveSkipNGramFeatures(seq, Skip_k, feaType);
//                bw.write(Fskip.trim() + "positive");
//                bw.newLine();
//                bw.flush();
//              }
//              System.out.println(num);
//            }
//          }
//        }
//        Skip_k = sb.toString().trim().length() - 1;
//        
//        String Fskip = AdaptiveSkipNGramFeatures(sb.toString(), Skip_k, feaType);
//        bw.write(Fskip.trim() + "positive");
//        bw.newLine();
//        bw.flush();
//        
//
//
//        br.close();
//        bw.close();
//        
//        
//      
//    }
//    catch (Exception ex)
//    {
//      ex.getMessage();
//    }
//  }
  
  public String AdaptiveSkipNGramFeatures(String Seq, int Skip_k, int feaType)
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
         // StringBuffer sb = new StringBuffer();
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
          StringBuffer sb = new StringBuffer();
          String grams = null;
          if (i + 2 + 2 * h > Seq.length() - 1) {
            break;
          }
          //grams = seq[i] + seq[(i + 1 + h)] + seq[(i + 2 + 2 * h)];
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
}
