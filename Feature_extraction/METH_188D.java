package Fuse;
import java.io.PrintStream;
import java.util.Vector;
@SuppressWarnings("unchecked")
public class METH_188D
{
  private String sequence;
 
  private Vector feature = new Vector();
  private String AminoAcids = "ACDEFGHIKLMNPQRSTVWY";
  
  public  METH_188D(String sequence)
  {
    setSequence(sequence);
  }
  
  public int getSequenceLength()
  {
    return this.sequence.length();
  }
  
  public String getSequence()
  {
    return this.sequence;
  }
  
  public String setSequence(String sequence)
  {
    this.sequence = sequence;
    sequence.toUpperCase();
    return this.sequence;
  }
  
  
public void composition()
  {
    double[] composition = new double[20];
    for (int i = 0; i < this.sequence.length(); i++)
    {
      int index = this.AminoAcids.indexOf(this.sequence.charAt(i));
      if (index >= 0) {
        composition[index] += 1.0D;
      }
    }
    for (int i = 0; i < 20; i++) {
      this.feature.add(Double.valueOf(composition[i] / this.sequence.length() * 100.0D));
    }
  }
  
  private int findNumberOfOccurance(String pattern, String s)
  {
    char a = pattern.charAt(0);
    char b = pattern.charAt(1);
    int numberOfOccurance = 0;
    
    int position = s.indexOf(String.valueOf(a) + String.valueOf(b));
    while (position >= 0)
    {
      position = s.indexOf(String.valueOf(a) + String.valueOf(b), position + 1);
      numberOfOccurance++;
    }
    position = s.indexOf(String.valueOf(b) + String.valueOf(a));
    while (position >= 0)
    {
      position = s.indexOf(String.valueOf(b) + String.valueOf(a), position + 1);
      numberOfOccurance++;
    }
    if (a == b) {
      numberOfOccurance /= 2;
    }
    return numberOfOccurance;
  }
  
  private void calcPvalue(double[] p, char[] transeq, char classLabel, double current_nG)
  {
    int np = 0;
    for (int i = 0; i < this.sequence.length(); i++) {
      if (transeq[i] == classLabel)
      {
        np++;
        if (np == 1) {
          p[0] = (i + 1);
        }
        if (np == (int)(0.25D * current_nG)) {
          p[1] = (i + 1);
        }
        if (np == (int)(0.5D * current_nG)) {
          p[2] = (i + 1);
        }
        if (np == (int)(0.75D * current_nG)) {
          p[3] = (i + 1);
        }
        if (np == current_nG) {
          p[4] = (i + 1);
        }
      }
    }
  }
  
  public void templete(String... classString)
  {
    char[] tranSeq = this.sequence.toCharArray();
    int numberOfClass = classString.length;
    

    double[] nG = new double[numberOfClass];
    for (int i = 0; i < this.sequence.length(); i++) {
      for (int j = 0; j < numberOfClass; j++) {
        if (classString[j].indexOf(this.sequence.charAt(i)) >= 0)
        {
          tranSeq[i] = Integer.toString(j).charAt(0);
          nG[j] += 1.0D;
          break;
        }
      }
    }
    for (int i = 0; i < numberOfClass; i++) {
      this.feature.add(Double.valueOf(nG[i] * 1.0D / this.sequence.length() * 100.0D));
    }
    double[] nF = new double['È'];
    int counterOfnF = 0;
    for (int i = 0; i < numberOfClass - 1; i++) {
      for (int j = i + 1; j < numberOfClass; j++) {
        nF[(counterOfnF++)] = findNumberOfOccurance(
          String.valueOf(i) + String.valueOf(j), 
          String.copyValueOf(tranSeq));
      }
    }
    for (int i = 0; i < counterOfnF; i++) {
      this.feature.add(Double.valueOf(nF[i] * 1.0D / (this.sequence.length() - 1) * 100.0D));
    }
    for (int i = 0; i < numberOfClass; i++)
    {
      double[] pi = new double[5];
      calcPvalue(pi, tranSeq, Integer.toString(i).charAt(0), nG[i]);
      for (int j = 0; j < 5; j++) {
        this.feature.add(Double.valueOf(pi[j] * 1.0D / this.sequence.length() * 100.0D));
      }
    }
  }
  
  public void performCalcuation()
  {
    if (this.sequence.length() < 0)
    {
      System.out.println("Empty Sequence.");
      return;
    }
    composition();
    





    templete(new String[] { "RKEDQN", "GASTPHY", "CVLIMFW" });
    





    templete(new String[] { "GASCTPD", "NVEQIL", "MHKFRYW" });
    





    templete(new String[] { "LIFWCMVY", "PATGS", "HQRKNED" });
    





    templete(new String[] { "GASDT", "CPNVEQIL", "KMHFRYW" });
    





    templete(new String[] { "KR", "ANCQGHILMFPSTWYV", "DE" });
    





    templete(new String[] { "GQDNAHR", "KTSEC", "ILMFPWYV" });
    





    templete(new String[] { "EALMQKRH", "VIYCWFT", "GNPSD" });
    





    templete(new String[] { "ALFCGIVW", "RKQEND", "MPSTHY" });
  }
  
  public Vector getFeature()
  {
    return this.feature;
  }
  
  public String run()
  {
    performCalcuation();
    Vector results = getFeature();
    StringBuffer sb = new StringBuffer();
    for (int i = 0; i < results.size(); i++) {
      sb.append(results.get(i).toString() + ",");
    }
    return sb.toString();
  }

}
