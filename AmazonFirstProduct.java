package week4.Day1;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

//Week4 Day1 Assignments - 3
public class AmazonFirstProduct {

	public static void main(String[] args) throws InterruptedException, IOException {
		// TODO Auto-generated method stub
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		// 1. Launch URL
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		driver.get("https://www.amazon.in/");
		// 2.search as oneplus 9 pro
		driver.findElement(By.id("twotabsearchtextbox")).sendKeys("Oneplus 9 pro");
		driver.findElement(By.id("nav-search-submit-button")).click();
		// 3.Get the price of the first product
		String PriceOfProduct = driver.findElement(By.xpath("//span[@class='a-price-whole'][1]")).getText();
		System.out.println("The Price of Product : " + PriceOfProduct);
		// 4. Print the number of customer ratings for the first displayed product
		WebElement noOfCustRate = driver.findElement(By.xpath("(//span[contains(@class,'s-underline-text')])[1]"));
		System.out.println("The No of Cust Rate : " + noOfCustRate.getText());
		// 5. Click the first text link of the first image
		driver.findElement(By.xpath("//img[@class='s-image']")).click();
		Thread.sleep(3000);
		// 6. Take a screen shot of the product displayed
		File source = driver.getScreenshotAs(OutputType.FILE);
		File destination = new File("./onePlusProduct.png");
		FileUtils.copyFile(source, destination);

		Thread.sleep(3000);
		String parentWindow = driver.getWindowHandle();

		// get the number of windows
		Set<String> windowHandles = driver.getWindowHandles();

		// Convert set to list
		List<String> windows = new ArrayList<String>(windowHandles);
		driver.switchTo().window(windows.get(1));
		// 7. Click 'Add to Cart' button
		driver.findElement(By.xpath("//input[@title='Add to Shopping Cart']")).click();

		// 8. Get the cart subtotal and verify if it is correct.
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		WebElement elesubTotalOfItem = driver
				.findElement(By.xpath("(//span[@id='attach-accessory-cart-subtotal'])[1]"));
		wait.until(ExpectedConditions.visibilityOf(elesubTotalOfItem));
		String subTotalOfItem = elesubTotalOfItem.getText();

		System.out.println("The SubTotal : " + subTotalOfItem);

		if (subTotalOfItem.equalsIgnoreCase("₹49,999.00")) {
			System.out.println("The subTotal is Matched");
		} else {
			System.out.println("The Subtotal is Not Matched");
		}
		driver.quit();
	}

}
