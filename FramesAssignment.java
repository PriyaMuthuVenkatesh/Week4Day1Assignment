package week4.day1;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;

public class FramesAssignment {

	public static void main(String[] args) {

		// chromesetup
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

		// open url
		driver.get("https://chercher.tech/practice/frames-example-selenium-webdriver");

		// switch to frame1 and enter topic
		driver.switchTo().frame("frame1");
		driver.findElement(By.xpath("//b[@id='topic']/following-sibling::input")).sendKeys("Testing");
		System.out.println("Switched to frame1 and entered topic");

		// switch to innerframe
		driver.switchTo().frame("frame3");
		driver.findElement(By.xpath("//input[@id='a']")).click();
		System.out.println("switched to inner frame and clicked inner frame checkbox");

		// come out of frame
		driver.switchTo().defaultContent();

		// switch to frame2 to select dropdown
		driver.switchTo().frame("frame2");
		WebElement element = driver.findElement(By.id("animals"));
		Select animalDropDown = new Select(element);
		animalDropDown.selectByVisibleText("Baby Cat");
		System.out.println("Switched to frame2 and select Baby cat from animal dropdown");

	}

}
