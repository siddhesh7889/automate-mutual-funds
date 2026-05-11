import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.ss.util.CellRangeAddress;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;

import java.io.*;
import java.time.LocalDate;

public class automate_mutualfunds {

    public static void main(String[] args) {
        String filePath = "StockDetails.xlsx";
        Workbook workbook;
        Sheet sheet;

        try {
            // Check if the file exists
            File file = new File(filePath);
            if (file.exists()) {
                FileInputStream fis = new FileInputStream(file);
                workbook = new XSSFWorkbook(fis);
                sheet = workbook.getSheetAt(0);
                fis.close();
            } else {
                workbook = new XSSFWorkbook();
                sheet = workbook.createSheet("Stock Details");

                // Create the first row for stock names
                Row headerRow = sheet.createRow(0);
                headerRow.createCell(0).setCellValue("Stock Name");
            }

            // Get the current date
            LocalDate today = LocalDate.now();
            String currentDate = today.toString();

            // Find the next available column for appending data
            int lastColumn = sheet.getRow(0).getLastCellNum();
            if (lastColumn == -1) lastColumn = 1; // If no columns exist yet

            // Adjust the starting column to avoid overlap with existing merged regions
            for (int i = 0; i < sheet.getNumMergedRegions(); i++) {
                CellRangeAddress mergedRegion = sheet.getMergedRegion(i);
                if (mergedRegion.getFirstRow() == 0 && mergedRegion.getLastColumn() >= lastColumn) {
                    lastColumn = mergedRegion.getLastColumn() + 1; // Move to the next available column
                }
            }

            // Add merged region for the current date
            sheet.addMergedRegion(new CellRangeAddress(0, 0, lastColumn, lastColumn + 2));
            Row headerRow = sheet.getRow(0);
            Cell dateCell = headerRow.createCell(lastColumn);
            dateCell.setCellValue("Date : " + currentDate);

            // Style for the merged cell
            CellStyle style = workbook.createCellStyle();
            style.setAlignment(HorizontalAlignment.CENTER);
            dateCell.setCellStyle(style);

            // Add sub-headers for Opening Price, High Price, and Low Price
            Row subHeaderRow = sheet.getRow(1);
            if (subHeaderRow == null) {
                subHeaderRow = sheet.createRow(1);
            }
            subHeaderRow.createCell(lastColumn).setCellValue("Opening Price");
            subHeaderRow.createCell(lastColumn + 1).setCellValue("High Price");
            subHeaderRow.createCell(lastColumn + 2).setCellValue("Low Price");

            // Setup EdgeDriver
//            System.setProperty("webdriver.edge.driver", "C:\\Users\\MalodeS\\OneDrive - Vodafone Group\\Documents\\GIG project\\UI_Automation\\driver\\msedgedriver.exe");
//            WebDriver driver = new EdgeDriver();
            WebDriver driver = new ChromeDriver();

            // Stock URLs and names
            String[][] stocks = {
                    {"Maruti Suzuki India Ltd.", "https://www.moneycontrol.com/india/stockpricequote/auto-carsjeeps/marutisuzukiindia/MS24"},
                    {"NTPC Ltd.", "https://www.moneycontrol.com/india/stockpricequote/power-generationdistribution/ntpc/NTP"},
                    {"Mangalore Refinery and Petrochemicals Ltd.", "https://www.moneycontrol.com/india/stockpricequote/refineries/mangalorerefinerypetrochemicals/MRP"},
                    {"Orkla India Ltd.", "https://www.moneycontrol.com/india/stockpricequote/consumer-food/orklaindia/OIL02"},
                    {"Tata Motors Ltd.", "https://www.moneycontrol.com/india/stockpricequote/automobile-truckslcvs/tatamotors/TML02"}
            };

            // Fetch stock details and append to Excel
            for (int i = 0; i < stocks.length; i++) {
                Row row = sheet.getRow(i + 2); // Start from row 2 for stock data
                if (row == null) {
                    row = sheet.createRow(i + 2);
                    row.createCell(0).setCellValue(stocks[i][0]); // Add stock name
                }

                driver.get(stocks[i][1]);

                WebElement openingPrice = driver.findElement(By.xpath("//*[@class='nseopn bseopn']"));
                row.createCell(lastColumn).setCellValue(openingPrice.getText());
                System.out.println("Opening Price of " + stocks[i][0] + ": " + openingPrice.getText());

                WebElement highPrice = driver.findElement(By.xpath("//*[@class='nseHP bseHP']"));
                row.createCell(lastColumn + 1).setCellValue(highPrice.getText());
                System.out.println("High Price of " + stocks[i][0] + ": " + highPrice.getText());

                WebElement lowPrice = driver.findElement(By.xpath("//*[@class='nseLP bseLP']"));
                row.createCell(lastColumn + 2).setCellValue(lowPrice.getText());
                System.out.println("low Price of " + stocks[i][0] + ": " + lowPrice.getText());
            }

            // Close the browser
            driver.quit();

            // Write the updated workbook to the file
            FileOutputStream fos = new FileOutputStream(filePath);
            workbook.write(fos);
            fos.close();
            workbook.close();

            System.out.println("Stock details appended to Excel file successfully!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
