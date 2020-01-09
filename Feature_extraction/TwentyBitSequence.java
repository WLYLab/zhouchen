package Test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;


public class TwentyBitSequence
{
	private static String systemPath="C:\\Users\\Administrator\\Desktop";;

  public static void main(String[] args)
  {
//	      String inPath = args[0];
//    String outPath =args[1];
    try
    {
      BufferedReader br = new BufferedReader(new FileReader(systemPath+"/ACP.txt"));
      BufferedWriter bwFeatures = new BufferedWriter(new FileWriter(systemPath+"/NT2.arff"));
      
      BufferedReader br_first = new BufferedReader(new FileReader(systemPath+"/ACP.txt"));
      br_first.readLine();
      String first_seq = br_first.readLine();
      int seq_num = first_seq.length();
      
      int featureNum = seq_num * 20;
      br_first.close();
      
      bwFeatures.write("@relation Protein");
      bwFeatures.newLine();
      for (int i = 1; i <= featureNum; i++)
      {
        bwFeatures.write("@attribute Feature" + i + " real");
        bwFeatures.newLine();
      }
      bwFeatures.write("@attribute class ");
      String attributeClass = "0";
      for (int i = 0; i < 1; i++) {
        attributeClass = attributeClass + "," + (i + 1);
      }
      bwFeatures.write("{" + attributeClass + "}");
      bwFeatures.newLine();
      bwFeatures.write("@data");
      bwFeatures.newLine();
      while (br.ready())
      { 
        String title = br.readLine();
        String line = br.readLine();
        //System.out.println(s);
        String TwentyBitSequenceFeatures = TwentyBitSequenceFeature(line.toUpperCase());
        bwFeatures.write(TwentyBitSequenceFeatures);
        if (title.split("\\|")[1].equals("1")) {
          bwFeatures.write("1");
        } else {
          bwFeatures.write("0");
        }
        bwFeatures.newLine();
        bwFeatures.flush();
      }
      bwFeatures.close();
      br.close();
    }
    catch (Exception ex)
    {
      ex.getLocalizedMessage();
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
}
