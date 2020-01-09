package Fuse;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.Arrays;

public class basis {
	/*处理arff头文件
	 */
	  public void bufferW_arff1(int a, BufferedWriter bw2)
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
	/*处理arff头文件
	 */
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
	  
	  /*读取结果
		 */
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
	  
	  /*清空数组
		 */
	  public void cleara(double[] a, int x)
	  {
	    for (int n = 0; n < x; n++) {
	      a[n] = 0.0D;
	    }
	  }
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
