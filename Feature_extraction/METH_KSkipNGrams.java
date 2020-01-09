package PEP;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

public class METH_KSkipNGrams
{
//  public static void main(String[] args)
//  {
//    int Gram_n = 2;
//    String inPath = args[0];
//    String outPath = args[1];
//    if (args.length > 2) {
//      Gram_n = Integer.parseInt(args[2]);
//    }
//    try
//    {
//      BufferedReader br = new BufferedReader(new FileReader(inPath));
//      BufferedWriter bwFeatures = new BufferedWriter(new FileWriter(outPath));
//      
//      int featureNum = (int)Math.pow(20.0D, Gram_n);
//      
//      bwFeatures.write("@relation Protein");
//      bwFeatures.newLine();
//      for (int i = 1; i <= featureNum; i++)
//      {
//        bwFeatures.write("@attribute Feature" + i + " real");
//        bwFeatures.newLine();
//      }
//      bwFeatures.write("@attribute class ");
//      String attributeClass = "0";
//      for (int i = 0; i < 1; i++) {
//        attributeClass = attributeClass + "," + (i + 1);
//      }
//      bwFeatures.write("{" + attributeClass + "}");
//      bwFeatures.newLine();
//      bwFeatures.write("@data");
//      bwFeatures.newLine();
//      while (br.ready())
//      {
//        String title = br.readLine();
//        String line = br.readLine();
//        
//        int Skip_k = line.length() - 1;
//        
//        String Fskip = KSkipNGramsFeature(line.toUpperCase(), Skip_k, Gram_n);
//        bwFeatures.write(Fskip);
//        if (title.split("\\|")[1].equals("1")) {
//          bwFeatures.write("0");
//        } else {
//          bwFeatures.write("1");
//        }
//        bwFeatures.newLine();
//        bwFeatures.flush();
//      }
//      bwFeatures.close();
//      br.close();
//    }
//    catch (Exception ex)
//    {
//      ex.getLocalizedMessage();
//    }
//  }
  
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
       //   StringBuffer sb = new StringBuffer();
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
      //    StringBuffer sb = new StringBuffer();
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
}
