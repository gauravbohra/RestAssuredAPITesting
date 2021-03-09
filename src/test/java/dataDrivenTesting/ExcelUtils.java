package dataDrivenTesting;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtils {

	public static FileOutputStream fout;
	public static FileInputStream fin;
	public static XSSFWorkbook wbook;
	public static XSSFSheet sheet;
	public static XSSFRow row;
	public static XSSFCell cell;

	public static int getRowCount(String fileName, String sheetName) {
		int rowCount = 0;
		try {
			fin = new FileInputStream(fileName);
			wbook = new XSSFWorkbook(fin);
			sheet = wbook.getSheet(sheetName);
			rowCount = sheet.getLastRowNum();
			wbook.close();
			fin.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return rowCount;

	}

	public static int getCellCount(String fileName, String sheetName, int rowNumber) {
		int cellCount = 0;
		try {
			fin = new FileInputStream(fileName);
			wbook = new XSSFWorkbook(fin);
			sheet = wbook.getSheet(sheetName);
			row = sheet.getRow(rowNumber);
			cellCount = row.getLastCellNum();
			wbook.close();
			fin.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return cellCount;

	}

	public static String getCellData(String fileName, String sheetName, int rowNumber, int columnNumber) {
		String cellData = "";
		try {
			fin = new FileInputStream(fileName);
			wbook = new XSSFWorkbook(fin);
			sheet = wbook.getSheet(sheetName);
			row = sheet.getRow(rowNumber);
			cell = row.getCell(columnNumber);
			try {
				DataFormatter formatter = new DataFormatter();
				cellData = formatter.formatCellValue(cell);
			} catch (Exception e) {
				cellData = "";
			}
			wbook.close();
			fin.close();
			return cellData;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return cellData;
	}

	public static void setCellData(String fileName, String sheetName, int rowNumber, int columnNumber,
			String cellValue) {
		try {
			fin = new FileInputStream(fileName);
			wbook = new XSSFWorkbook(fin);
			sheet = wbook.getSheet(sheetName);
			row = sheet.getRow(rowNumber);
			cell = row.createCell(columnNumber);
			cell.setCellValue(cellValue);
			fout = new FileOutputStream(fileName);
			wbook.write(fout);
			wbook.close();
			fout.close();
			fin.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
