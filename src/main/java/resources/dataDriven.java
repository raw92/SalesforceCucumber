package resources;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class dataDriven {

	// Identify testcases coloumn by scanning the entire 1st row
	// once coloumn is identified then scan entire testcase coloumn to identify
	// purcharse testcase row
	// after u grab purcharse testcase row = pull all the data of that row and
	// feed into test

	public static void main(String[] args) throws IOException {

	}

	public ArrayList<String> getData(String testCaseName) throws IOException {
		ArrayList<String> a = new ArrayList<String>();

		//Si otro quiere correrlo deberia guardar en el proyecto el archivo excel y apuntar al directorio del proyecto no local.
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "\\src\\main\\java\\resources\\datos.xlsx");
		
		XSSFWorkbook workbook = new XSSFWorkbook(fis);

		int sheets = workbook.getNumberOfSheets();

		for (int i = 0; i < sheets; i++) {

			String sheetName = workbook.getSheetName(i);

			if (sheetName.equalsIgnoreCase("data")) {
				XSSFSheet sheet = workbook.getSheetAt(i);

				// Identify testcases coloumn by scanning the entire 1st row

				Iterator<Row> rows = sheet.rowIterator();// sheet is collection of rows
				Row firstrow = rows.next();
				Iterator<Cell> ce = firstrow.cellIterator();// row is collection of cells

				int k = 0;
				int coloumn = 0;
				// Recorro las celdas para averiguar cual columna tiene Testcases
				while (ce.hasNext()) {
					Cell value = ce.next();
					if (value.getStringCellValue().equalsIgnoreCase("Testcases")) {
						coloumn = k;
					}
					k++;
				}
				//averiguo cual fila tiene Login que lo paso como parametro
				while (rows.hasNext()) {
					Row r = rows.next();
					if (r.getCell(coloumn).getStringCellValue().equalsIgnoreCase(testCaseName)) {
						
						//Capturo los datos de las celdas en un array para despues usarlos
						Iterator<Cell> cv = r.cellIterator();
						while (cv.hasNext()) {
							a.add(cv.next().getStringCellValue());
						}

					}
				}
			}
		}
		return a;
	}

	
	
	
	
	
	
	
}
