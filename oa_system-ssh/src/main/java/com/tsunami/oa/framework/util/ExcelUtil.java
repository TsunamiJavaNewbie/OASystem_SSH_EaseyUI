package com.tsunami.oa.framework.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.struts2.ServletActionContext;

public class ExcelUtil {
	
	/**
	 * ����excel�ļ�
	 * @param strings
	 * @param lists
	 */
	public static void export(String[] strings,List<?> lists ) {
		Workbook wb = new HSSFWorkbook();
		Sheet sheet = wb.createSheet("new sheet");
		Row row = sheet.createRow(0);
		
		/**
		 * ������ͷ
		 */
		for (int i = 0; i < strings.length; i++) {
			Cell cell = row.createCell(i);
			cell.setCellValue(strings[i]);
		}
		
		/**
		 * ��������
		 */
		for (int i = 0; i < lists.size(); i++) {
			Map<String, String> map = (Map<String, String>) lists.get(i);
			
			Set<String> keys = map.keySet();
			row = sheet.createRow(i+1);
			
			Iterator<String> it = map.keySet().iterator();
			int x=0;
			while (it.hasNext()) {
				String key = it.next();
				Object val = map.get(key);
				if(null!=val){
					Cell cell = row.createCell(x);
					cell.setCellValue(val.toString());
				}else{
					row.createCell(x).setCellValue("");
				}
				x++;
			}
		}
		
		FileOutputStream fos;
		try {
			String filename="workbook.xls";
			
			//String path = ServletActionContext.getServletContext().getRealPath("/uploads");
			String path = "G:/EclipseProjects/oa_system-ssh/uploads";
			fos = new FileOutputStream(path+"/"+filename);
			wb.write(fos);
			fos.close();
			
			HttpServletResponse response = ServletActionContext.getResponse();
			// ����������������صķ�����ȡ����Դ
			// ����������Դ��ֱ���������URLEncoder.encode(realPath, "utf-8"))
			response.setHeader("content-disposition", "attachment;filename=" + URLEncoder.encode(filename, "utf-8"));
			// ��ȡ�������ص���Դ
			FileInputStream fis = new FileInputStream(path + "/" + filename);
			int len = 0;
			byte[] buf = new byte[1024];
			while ((len = fis.read(buf)) != -1) {
				response.getOutputStream().write(buf, 0, len);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	


}
