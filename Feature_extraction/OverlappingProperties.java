package Test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

public class OverlappingProperties
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
      
      int featureNum = seq_num * 10;
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
        
        String OverlappingPropertiesFeatures = OverlappingPropertiesFeatures(line.toUpperCase());
        bwFeatures.write(OverlappingPropertiesFeatures);
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
}
