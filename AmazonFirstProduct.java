package week4.Day1;
import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class AmazonFirstProduct {

	
		public static void main(String[] args) throws InterruptedException, IOException {
			// TODO Auto-generated method stub
			WebDriverManager.chromedriver().setup();
			ChromeDriver driver = new ChromeDriver();
			driver.manage().window().maximize();

			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
			driver.get("https://www.amazon.in/");

			driver.findElement(By.id("twotabsearchtextbox")).sendKeys("Oneplus 9 pro");
			driver.findElement(By.id("nav-search-submit-button")).click();

			String PriceOfProduct = driver.findElement(By.xpath("//span[@class='a-price-whole'][1]")).getText();
			System.out.println("The Price of Product : " + PriceOfProduct);
			
			//String noOfCustRate = driver.findElement(By.xpath("(//a[@class='a-link-normal s-underline-text s-underline-link-text s-link-style'])[2]")).getText();
			WebElement noOfCustRate = driver.findElement(By.xpath("(//span[contains(@class,'s-underline-text')])[1]"));
			System.out.println("The No of Cust Rate : " + noOfCustRate.getText());
			
			/* String text =
			 * driver.findElement(By.xpath("//span[text()='4.1 out of 5 stars']")).getText()
			 * ; System.out.println(text);*/
			
			driver.findElement(By.xpath("//img[@class='s-image']")).click();
			
		 File source = driver.getScreenshotAs(OutputType.FILE);
		 File destination = new File ("./onePlusProduct.png");
		 FileUtils.copyFile(source, destination);
		 
			Thread.sleep(3000);
			String parentWindow = driver.getWindowHandle();

			// get the number of windows
			Set<String> windowHandles = driver.getWindowHandles();

			// Convert set to list
			List<String> windows = new ArrayList<String>(windowHandles);
			driver.switchTo().window(windows.get(1));

			driver.findElement(By.xpath("//input[@title='Add to Shopping Cart']")).click();
			Thread.sleep(2000);
			String subTotalOfItem= driver.findElement(By.xpath("//span[@id='attach-accessory-cart-subtotal']")).getText() ;
			  
			System.out.println("The SubTotal : " + subTotalOfItem);
			
			if (subTotalOfItem.equalsIgnoreCase("₹49,999.00"))
				System.out.println("The subTotal is Matched");
		
			else
				System.out.println("The Subtotal is Not Matched");
//			WebElement webElement = driver.findElement(By.xpath("//i[@class='a-icon a-icon-cart']"));
//			driver.switchTo().frame(webElement);
//			driver.findElement(By.name("submit.add-to-cart")).click();

	}

}
