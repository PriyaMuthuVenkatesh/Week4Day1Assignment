package week4.day1;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class MergeContact {

	public static void main(String[] args) throws InterruptedException {
		
        // chromedriver setup
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		
		//open leaftaps url
		driver.get("http://leaftaps.com/opentaps/control/main");
		//get the parent window
		String parentWindow = driver.getWindowHandle();
		//login and navigate to merge contacts
		driver.findElement(By.id("username")).sendKeys("DemoSalesManager");
		driver.findElement(By.id("password")).sendKeys("crmsfa");
		driver.findElement(By.xpath("//input[@class='decorativeSubmit']")).click();
		driver.findElement(By.xpath("//a[contains(text(),'CRM/SFA')]")).click();
		driver.findElement(By.xpath("//a[text()='Contacts']")).click();
		driver.findElement(By.xpath("//a[text()='Merge Contacts']")).click();
		driver.findElement(By.xpath("//input[@name='partyIdFrom']/following-sibling::a")).click();
		Thread.sleep(3000);
		
		//get multiple windows
		Set<String> windowHandles = driver.getWindowHandles();
		List<String> window = new ArrayList<String>(windowHandles);
		
		//switch to 2nd window
		driver.switchTo().window(window.get(1));
		driver.findElement(By.xpath("(//div[@class='x-grid3-cell-inner x-grid3-col-partyId']/a)[1]")).click();
		Thread.sleep(3000);
		// switch to main window
		driver.switchTo().window(window.get(0));
		Thread.sleep(3000);
		driver.findElement(By.xpath("//input[@name='partyIdTo']/following-sibling::a")).click();
		Thread.sleep(3000);
		
		//switch to child window
		Set<String> windowHandles1 = driver.getWindowHandles();
		List<String> window1 = new ArrayList<String>(windowHandles1);
		driver.switchTo().window(window1.get(1));
		driver.findElement(By.xpath("(//div[@class='x-grid3-cell-inner x-grid3-col-partyId']/a)[2]")).click();
		Thread.sleep(3000);
		
		//switch to parent window
		driver.switchTo().window(parentWindow);
		driver.findElement(By.xpath("//a[@class='buttonDangerous']")).click();
		
		//capture the alert and accept
		Alert alert = driver.switchTo().alert();
		alert.accept();
		String title = driver.getTitle();
		
		//verify title
		if (title.contains("View Contact")) {
			System.out.println("Correct title is displayed: " + title);

		}

	}

}
