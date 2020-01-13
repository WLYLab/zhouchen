package Test;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.URL;
import java.net.URLDecoder;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public final class FileToZip
{
  public static boolean fileToZip(String sourceFilePath, String zipFilePath, String fileName)
  {
    boolean flag = false;
    File sourceFile = new File(sourceFilePath);
    FileInputStream fis = null;
    BufferedInputStream bis = null;
    FileOutputStream fos = null;
    ZipOutputStream zos = null;
    if (!sourceFile.exists()) {
      System.out.println("��ѹ�����ļ�Ŀ¼��" + sourceFilePath + "������.");
    } else {
      try
      {
        File zipFile = new File(zipFilePath + "/" + fileName + ".zip");
        


        File[] sourceFiles = sourceFile.listFiles();
        if ((sourceFiles == null) || (sourceFiles.length < 1))
        {
          System.out.println("��ѹ�����ļ�Ŀ¼��" + sourceFilePath + "���治�����ļ�������ѹ��.");
        }
        else
        {
          fos = new FileOutputStream(zipFile);
          zos = new ZipOutputStream(new BufferedOutputStream(fos));
          byte[] bufs = new byte[10240];
          for (int i = 0; i < sourceFiles.length; i++)
          {
            ZipEntry zipEntry = new ZipEntry(sourceFiles[i].getName());
            zos.putNextEntry(zipEntry);
            
            fis = new FileInputStream(sourceFiles[i]);
            bis = new BufferedInputStream(fis, 10240);
            int read = 0;
            while ((read = bis.read(bufs, 0, 10240)) != -1) {
              zos.write(bufs, 0, read);
            }
            flag = true;
          }
        }
      }
      catch (FileNotFoundException e)
      {
        e.printStackTrace();
        throw new RuntimeException(e);
      }
      catch (IOException e)
      {
        e.printStackTrace();
        throw new RuntimeException(e);
      }
      finally
      {
        try
        {
          if (bis != null) {
            bis.close();
          }
          if (zos != null) {
            zos.close();
          }
        }
        catch (IOException e)
        {
          e.printStackTrace();
          throw new RuntimeException(e);
        }
      }
    }
    return flag;
  }
  
  public static String getRealPath()
  {
    String realPath = FileToZip.class.getClassLoader().getResource("")
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
  
  public static void main(String[] args)
  {
//  	String intputFile = "C:\\Users\\Administrator\\eclipse-workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\FeaPlus1-modified3\\Result_file\\1567972251";
	String intputFile = args[0];
  	String Format =args[1];
  	String Format2 =args[2];
	String Format3 =args[3];
//  	String Format ="temp/RF";
//  	String Format2 ="MRMD";
    String sourceFilePath1 = intputFile + "/" + Format+"/" + Format2+"/" + Format3;
    String fileName1 = "";
    String zipFilePath = intputFile;
if(Format.equals("csv1")||Format.equals("arff")||Format.equals("tsv")) {
      fileName1 = "Feature_Pool";
}
else {fileName1= "FeatureOP_"+Format2;}

    boolean flag = fileToZip(sourceFilePath1, zipFilePath, fileName1);
    if (flag) {
      System.out.println("�ļ�����ɹ�!");
    } else {
      System.out.println("�ļ����ʧ��!");
    }
  }
}
