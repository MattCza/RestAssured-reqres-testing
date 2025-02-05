package utility;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtility {

    XSSFWorkbook workbook;
    XSSFSheet sheet;

    public ExcelUtility(String excelPath, String sheetName) {
        try {
            workbook = new XSSFWorkbook(excelPath);
            sheet = workbook.getSheet(sheetName);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public int getRowCount() {
        int rowCount = 0;
        rowCount = sheet.getPhysicalNumberOfRows();

        return rowCount;
    }

    public Object getCellData(int rowNumber, int columnNumber) {
        DataFormatter formatter = new DataFormatter();
        return formatter.formatCellValue(sheet.getRow(rowNumber).getCell(columnNumber));
    }


}
