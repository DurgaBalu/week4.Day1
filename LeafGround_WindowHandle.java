package week4.Day1;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

//Week4 Day1 Assignments - 1.2
public class LeafGround_WindowHandle {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));

		driver.get("http://www.leafground.com/pages/Window.html");
		// Get Current Window as Parent(Main) Window 
		String parentWindow = driver.getWindowHandle();

		driver.findElement(By.id("home")).click();
		System.out.println("1. Click button to open home page in New Window - Completed");

		driver.findElement(By.xpath("//button[text()='Open Multiple Windows']")).click();
		Set<String> CountOfOpenWindow = driver.getWindowHandles();
		List<String> getCountOfOpenWindow = new ArrayList<String>(CountOfOpenWindow);
		System.out.println("2. Find the number of opened windows : " + getCountOfOpenWindow.size());

		driver.findElement(By.xpath("//button[text()='Do not close me ']")).click();
		Set<String> dntCloseMe = driver.getWindowHandles();
		List<String> getCountdntCloseMe = new ArrayList<String>(dntCloseMe);
		System.out.println("Find the number of opened windows : " + getCountdntCloseMe.size());
		
		System.out.println("3. Close all except this window - Completed");

		for (String childWindow : getCountdntCloseMe) {
			if (!childWindow.equals(parentWindow)) {
				Thread.sleep(2000);
				driver.switchTo().window(childWindow);
				Thread.sleep(2000);
				driver.close();
			}
		}
		driver.switchTo().window(parentWindow);
		System.out.println("4. Wait for 2 new Windows to open - completed" + "");
		driver.findElement(By.xpath("//button[text()='Wait for 5 seconds']")).click();
		Set<String> waitSeconds = driver.getWindowHandles();
		List<String> getCountOfWaitSeconds = new ArrayList<String>(waitSeconds);

		for (String childWindow1 : getCountOfWaitSeconds) {
			System.out.println("Title of Current windows : " + driver.switchTo().window(childWindow1).getTitle());
		}
		driver.quit();

	}
}
