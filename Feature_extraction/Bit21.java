package Test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

public class Bit21
{
	
  public static void main(String[] args)
  {
    String inPath = args[0];
    String outPath = args[1];
    try
    {
      BufferedReader br = new BufferedReader(new FileReader(inPath));
      BufferedWriter bwFeatures = new BufferedWriter(new FileWriter(outPath));
      
      BufferedReader br_first = new BufferedReader(new FileReader(inPath));
      br_first.readLine();
      String first_seq = br_first.readLine();
      int seq_num = first_seq.length();
      
      int featureNum = seq_num * 21;
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
        
        String Bit21Feature = Bit21Feature(line.toUpperCase());
        bwFeatures.write(Bit21Feature);
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
  
  public static String Bit21Feature(String Seq)
  {
    String[][] PropertyClass = new String[7][3];
    String[] property = { "ACFGHILMNPQSTVWY", "DE", "KR", "CFILMVW", "AGHPSTY", "DEKNQR", "ACDGPST", "EILNQV", "FHKMRWY", "CFILMVWY", "AGPST", "DEHKNQR", "ADGST", "CEILNPQV", "FHKMRWY", "DGNPS", "AEHKLMQR", "CFITVWY", "ACFGILVW", "HMPSTY", "DEKNRQ" };
    int m = 0;
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
}
