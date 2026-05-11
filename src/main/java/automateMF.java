import java.time.format.DateTimeFormatter;
import java.time.LocalDate;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;

public class automateMF {
   public static void main(String[] args) {
      // Set the path to the EdgeDriver executable
      System.setProperty("webdriver.edge.driver", "C:\\Users\\MalodeS\\OneDrive - Vodafone Group\\Documents\\GIG project\\UI_Automation\\driver\\msedgedriver.exe");

      // Initialize the EdgeDriver
      WebDriver driver = new EdgeDriver();

      System.out.println("-----------------Maruti Suzuki India Ltd.---------------");
      driver.get("https://www.moneycontrol.com/india/stockpricequote/auto-carsjeeps/marutisuzukiindia/MS24");
      WebElement stock1_opnprice = driver.findElement(By.xpath("//*[@class='nseopn bseopn']"));
      System.out.println("opening price: " + stock1_opnprice.getText());
      WebElement stock1_highprice = driver.findElement(By.xpath("//*[@class='nseHP bseHP']"));
      System.out.println("high price: " + stock1_highprice.getText());
      WebElement stock1_lowprice = driver.findElement(By.xpath("//*[@class='nseLP bseLP']"));
      System.out.println("low price: " + stock1_lowprice.getText());

       System.out.println("-----------------NTPC Ltd.---------------");
       driver.get("https://www.moneycontrol.com/india/stockpricequote/power-generationdistribution/ntpc/NTP");
       WebElement stock2_opnprice = driver.findElement(By.xpath("//*[@class='nseopn bseopn']"));
       System.out.println("opening price: " + stock2_opnprice.getText());
       WebElement stock2_highprice = driver.findElement(By.xpath("//*[@class='nseHP bseHP']"));
       System.out.println("high price: " + stock2_highprice.getText());
       WebElement stock2_lowprice = driver.findElement(By.xpath("//*[@class='nseLP bseLP']"));
       System.out.println("low price: " + stock2_lowprice.getText());


       System.out.println("-----------------Mangalore Refinery and Petrochemicals Ltd.---------------");
       driver.get("https://www.moneycontrol.com/india/stockpricequote/refineries/mangalorerefinerypetrochemicals/MRP");
       WebElement stock3_opnprice = driver.findElement(By.xpath("//*[@class='nseopn bseopn']"));
       System.out.println("opening price: " + stock3_opnprice.getText());
       WebElement stock3_highprice = driver.findElement(By.xpath("//*[@class='nseHP bseHP']"));
       System.out.println("high price: " + stock3_highprice.getText());
       WebElement stock3_lowprice = driver.findElement(By.xpath("//*[@class='nseLP bseLP']"));
       System.out.println("low price: " + stock3_lowprice.getText());


       System.out.println("-----------------Orkla India Ltd.---------------");
       driver.get("https://www.moneycontrol.com/india/stockpricequote/consumer-food/orklaindia/OIL02");
       WebElement stock4_opnprice = driver.findElement(By.xpath("//*[@class='nseopn bseopn']"));
       System.out.println("opening price: " + stock4_opnprice.getText());
       WebElement stock4_highprice = driver.findElement(By.xpath("//*[@class='nseHP bseHP']"));
       System.out.println("high price: " + stock4_highprice.getText());
       WebElement stock4_lowprice = driver.findElement(By.xpath("//*[@class='nseLP bseLP']"));
       System.out.println("low price: " + stock4_lowprice.getText());


       System.out.println("-----------------Tata Motors Ltd.---------------");
       driver.get("https://www.moneycontrol.com/india/stockpricequote/automobile-truckslcvs/tatamotors/TML02");
       WebElement stock5_opnprice = driver.findElement(By.xpath("//*[@class='nseopn bseopn']"));
       System.out.println("opening price: " + stock5_opnprice.getText());
       WebElement stock5_highprice = driver.findElement(By.xpath("//*[@class='nseHP bseHP']"));
       System.out.println("high price: " + stock5_highprice.getText());
       WebElement stock5_lowprice = driver.findElement(By.xpath("//*[@class='nseLP bseLP']"));
       System.out.println("low price: " + stock5_lowprice.getText());


      LocalDate today = LocalDate.now();
      System.out.println(today);

      driver.quit();
   }
}
