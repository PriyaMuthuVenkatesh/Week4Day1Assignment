package week4.day1;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class AmazonAssignment {

	public static void main(String[] args) throws IOException, InterruptedException {

		// chromesetup
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));
		
		//open webpage
		driver.get("https://www.amazon.in/");
		
		//search oneplus 9 pro
		driver.findElement(By.xpath("//input[@id='twotabsearchtextbox']")).sendKeys("oneplus 9 pro");
		driver.findElement(By.xpath("//input[@type='submit']")).click();
		
		//print price of the first product
		String price = driver.findElement(By.xpath("(//span[@class='a-price-whole'])[1]")).getText();
		System.out.println(price);
		
		//print customer rating for the first product
		String customerRating = driver.findElement(By.xpath("(//span[@class='a-size-base s-underline-text'])[1]")).getText();
		System.out.println(customerRating);
		
		//click first product with text link
		driver.findElement(By.xpath("(//span[@class='a-size-medium a-color-base a-text-normal'])[1]")).click();
		Thread.sleep(3000);
		
		//switch to next window
		Set<String> windowHandles = driver.getWindowHandles();
		List<String> window= new ArrayList<String>(windowHandles);
		driver.switchTo().window(window.get(1));
		
		//print the title
		System.out.println(driver.getTitle());
		
		//take screenshot of the product
		
		File source = driver.getScreenshotAs(OutputType.FILE);
		File destination= new File("./amazonProductScreenshot.png");
		FileUtils.copyFile(source, destination);
		
		//click add to cart
		driver.findElement(By.xpath("//input[@value='Add to Cart']")).click();
		Thread.sleep(5000);
		String cartPrice = driver.findElement(By.xpath("//span[@id='attach-accessory-cart-subtotal']")).getText();
		System.out.println(cartPrice);
		
		if(cartPrice.contains(price))
		{
			System.out.println("Correct price displayed");
		}
		else
			System.out.println("Incorrect price");
		
		//close the browser
		driver.quit();

	}

}
