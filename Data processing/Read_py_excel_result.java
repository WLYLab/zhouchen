



import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import jxl.Sheet;  
import jxl.Workbook;  
import jxl.read.biff.BiffException; 

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;


public class Read_py_excel_result {
	private static String systemPath1="D:\\99server\\Python\\ten\\new\\csv/";
	static ArrayList<String> listFT = new ArrayList<String>();
	static ArrayList<String> list = new ArrayList<String>();
	static int num=114;
	public void dealnum(  HSSFSheet sheet,String line ,int h) {
        try {
        	  String[] str=line.trim().split("\\s+");
        	  HSSFRow row = sheet.createRow(h);
        	  for(int i=0;i<str.length;i++)
        	  {//System.out.println(str[i]);
        	  HSSFCell nameCell = row.createCell(i);
              nameCell.setCellValue(str[i]);

        	  }
            

        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
    }
	public void dealnum1(  HSSFSheet sheet,String line ,int h) {
        try {
        	  String[] str=line.trim().split(",");
        	  HSSFRow row = sheet.createRow(h);
        	  for(int i=0;i<str.length;i++)
        	  {//System.out.println(str[i]);
        	  HSSFCell nameCell = row.createCell(i);
              nameCell.setCellValue(str[i]);

        	  }
            

        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
    }
	 @SuppressWarnings("resource")
		public void initList(String fileName) {
	        try {
	       
	        	BufferedReader br = new BufferedReader(new FileReader(fileName));
	            while (br.ready())
	            {String line1;
	             line1 = br.readLine();  
	             System.out.println(line1);
	              list.add(line1 );
	            }
	       
	             
	            

	        } catch (Exception e) {
	            // TODO: handle exception
	            e.printStackTrace();
	        }
	    }
	 public void initList1(String fileName,int i) {
	        try {
	       
	        	BufferedReader br = new BufferedReader(new FileReader(fileName));
	        	String line = br.readLine();
                     for(int x=1;x<i;x++) {
	             String line1 = br.readLine();         
	              listFT.add(line1 );
//	              System.out.println(line1);
	            }
	       
	             
	            

	        } catch (Exception e) {
	            // TODO: handle exception
	            e.printStackTrace();
	        }
	    }
	
	 
	    // 去读Excel的方法readExcel，该方法的入口参数为一个File对象  
	    public void readExcel(File file,String t) {  
	        try {  
	            // 创建输入流，读取Excel  
	            InputStream is = new FileInputStream(file.getAbsolutePath());  
	            // jxl提供的Workbook类  
	            Workbook wb = Workbook.getWorkbook(is);  
	            // Excel的页签数量  
	            int sheet_size = wb.getNumberOfSheets();  
	            for (int index = 0; index < sheet_size; index++) {  
	                // 每个页签创建一个Sheet对象  
	                Sheet sheet = wb.getSheet(index);  
	                // sheet.getRows()返回该页的总行数  
	                for (int i = 1; i < sheet.getRows(); i++) {  
	                	String cello="";
	                    // sheet.getColumns()返回该页的总列数  
	                    for (int j = 0; j < sheet.getColumns(); j++) {  
	                        String cellinfo = sheet.getCell(j, i).getContents();  
	                        cello=  cello+cellinfo+",";
	                    }  
	                    System.out.println(cello);
	                    listFT.add(cello+","+t);
	                } 
	              
	               
	            }  
	        } catch (FileNotFoundException e) {  
	            e.printStackTrace();  
	        } catch (BiffException e) {  
	            e.printStackTrace();  
	        } catch (IOException e) {  
	            e.printStackTrace();  
	        }  
	    }  
	    public void readExcelarff(File file,String name) {  
	        try {  
	            // 创建输入流，读取Excel  
	            InputStream is = new FileInputStream(file.getAbsolutePath());  
	            // jxl提供的Workbook类  
	            Workbook wb = Workbook.getWorkbook(is);  
	            // Excel的页签数量  
	            int sheet_size = wb.getNumberOfSheets();  
	            for (int index = 0; index < sheet_size; index++) {  
	                // 每个页签创建一个Sheet对象  
	                Sheet sheet = wb.getSheet(index);  
	                // sheet.getRows()返回该页的总行数  
	                for (int i = 1; i < sheet.getRows(); i++) {  
	                	String cello="";
	                    // sheet.getColumns()返回该页的总列数  
	                    for (int j = 0; j < sheet.getColumns(); j++) {  
	                        String cellinfo = sheet.getCell(j, i).getContents();  
	                        cello=  cello+cellinfo+",";
	                    }  
	                    cello=cello+name;
	                    System.out.println(cello);
	                    listFT.add(cello);
	                } 
	              
	               
	            }  
	        } catch (FileNotFoundException e) {  
	            e.printStackTrace();  
	        } catch (BiffException e) {  
	            e.printStackTrace();  
	        } catch (IOException e) {  
	            e.printStackTrace();  
	        }  
	    }  

    public static void main(String[] args) {
    	Read_py_excel_result r1=new Read_py_excel_result();
    	
    	
    	String[] Peptides={"AAC(N=1)","AAC(N=2)","188D","400D","CKSAAGP","CKSAAP","CTD","CTDC","CTDD",
    			"CTDT","CTriad","DDE","DPC","GAAC","GDPC","GTPC","KSCTriad",
    			"TPC","AACNT(1)","AACNT(2)","AACNT(3)","AACNT(4)","AACNT(5)",
    			"AACNT(6)","AACNT(7)","AACNT(8)","AACNT(9)","AACNT(10)","AAINDEXNT(1)",
    			"AAINDEXNT(2)","AAINDEXNT(3)","AAINDEXNT(4)","AAINDEXNT(5)","AAINDEXNT(6)",
    			"AAINDEXNT(7)","AAINDEXNT(8)","AAINDEXNT(9)","AAINDEXNT(10)","BIT20NT(1)",
    			"BIT20NT(2)","BIT20NT(3)","BIT20NT(4)","BIT20NT(5)","BIT20NT(6)","BIT20NT(7)",
    			"BIT20NT(8)","BIT20NT(9)","BIT20NT(10)","BIT21NT(1)","BIT21NT(2)","BIT21NT(3)",
    			"BIT21NT(4)","BIT21NT(5)","BIT21NT(6)","BIT21NT(7)","BIT21NT(8)","BIT21NT(9)",
    			"BIT21NT(10)","BLOSUM62NT(1)","BLOSUM62NT(2)","BLOSUM62NT(3)","BLOSUM62NT(4)",
    			"BLOSUM62NT(5)","BLOSUM62NT(6)","BLOSUM62NT(7)","BLOSUM62NT(8)","BLOSUM62NT(9)","BLOSUM62NT(10)",
    			"ITNT(1)","ITNT(2)","ITNT(3)","ITNT(4)","ITNT(5)","ITNT(6)","ITNT(7)","ITNT(8)","ITNT(9)",
    			"ITNT(10)","OLPNT(1)","OLPNT(2)","OLPNT(3)","OLPNT(4)","OLPNT(5)","OLPNT(6)","OLPNT(7)",
    			"OLPNT(8)","OLPNT(9)","OLPNT(10)","ZSCALENT(1)","ZSCALENT(2)","ZSCALENT(3)","ZSCALENT(4)",
    			"ZSCALENT(5)","ZSCALENT(6)","ZSCALENT(7)","ZSCALENT(8)","ZSCALENT(9)","ZSCALENT(10)",
    			"EAACNT(5)","EAACNT(6)","EAACNT(7)","EAACNT(8)","EAACNT(9)","EAACNT(10)","EGAACNT(5)",
    			"EGAACNT(6)","EGAACNT(7)","EGAACNT(8)","EGAACNT(9)","EGAACNT(10)","GAP(g=0)","GAP(g=1)"
    			,"GAP(g=2)","GAP(g=3)"};
    	 //1  创建工作薄
        // 先创建工作簿对象
//    	File file = new File(systemPath1+"/cross_validation_RF_FT1.xls");  
//    	r1.readExcel(file); 
        HSSFWorkbook workbook2003 = new HSSFWorkbook();
    	
  		//int Peptidenum=Peptides.length;
    	listFT.add(" ,特征集,样本个数,分类器,Accuracy,Precision,Recall,SN,SP,Gm,F_measure,F_score,MCC,ROC曲线面积,tp,fn,fp,tn" );
  		for(int h=1;h<=114;h++) {//Peptides.length
 
//       r1.initList1(systemPath1+"/cross_validation_RF_FT"+h+".xls",2);
//  			 File file = new File(systemPath1+"/cross_validation_RF_FT"+h+".xls");  
             File file = new File(systemPath1+"/cross_validation_RF_"+Peptides[h-1]+".xls");  
//   	   r1.readExcelarff(file,Peptides[h-1]); 
   	r1.readExcel(file,Peptides[h-1]); 
  		}
        // 创建工作表对象并命名
        HSSFSheet sheet = workbook2003.createSheet("1");
       // System.out.println(list.size());
      
//        for(int t=0;t<listFT.size();t++)
//        {//System.out.println(list.get(i).toString());
//        	r1.dealnum1(sheet, listFT.get(t).toString(),t);
//        }
//      
//        for(int i=0;i<list.size();i++)
//        {//System.out.println(list.get(i).toString());
//        	r1.dealnum(sheet, list.get(i).toString(),listFT.size()+i);
//        }
        for(int t=0;t<listFT.size();t++)
          {//System.out.println(list.get(i).toString());
          	r1.dealnum1(sheet, listFT.get(t).toString(),t);
          }
        list.clear();
        listFT.clear();
//        // 创建行
//        HSSFRow row = sheet.createRow(0);
//
//        HSSFCell nameCell = row.createCell(0);
//        nameCell.setCellValue("编号");
//
//        HSSFCell Cell = row.createCell(1);
//        Cell.setCellValue("姓名");
//  		}
        
        // 生成文件
        File filek = new File(systemPath1+"/a.xls");
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(filek);
            workbook2003.write(fos);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}