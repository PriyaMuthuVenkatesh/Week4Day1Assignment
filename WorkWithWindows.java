package week4.day1;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class WorkWithWindows {

	public static void main(String[] args) {
		// chromedriver setup
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		// driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

		// open url
		driver.get("http://leafground.com/pages/Window.html");

		// get parent windowhandle
		String parentWindow = driver.getWindowHandle();

		// get the title of parent window
		System.out.println("parent window title is " + driver.getTitle());

		// click open home page button
		driver.findElement(By.xpath("//button[@id='home']")).click();

		// switch to child window
		Set<String> windowHandles = driver.getWindowHandles();
		List<String> window = new ArrayList<String>(windowHandles);
		driver.switchTo().window(window.get(1));

		// print title of the window
		System.out.println("Child window title on clicking home page button is " + driver.getTitle());

		// close current window
		driver.close();

		// switch to parent window
		driver.switchTo().window(parentWindow);

		// open multiple windows button
		driver.findElement(By.xpath("//button[contains(text(),'Multiple')]")).click();

		// switch to child windows
		windowHandles = driver.getWindowHandles();
		window = new ArrayList<String>(windowHandles);
		for (int i = 1; i < window.size(); i++) {
			driver.switchTo().window(window.get(i));
			System.out.println("Child window title on clicking open multiple window button is " + driver.getTitle());
			driver.close();
		}

		// switch to parent window
		driver.switchTo().window(parentWindow);

		// open do not close me button
		driver.findElement(By.xpath("(//button[@id='color'])[1]")).click();

		// switch to child window
		windowHandles = driver.getWindowHandles();
		window = new ArrayList<String>(windowHandles);
		for (int i = 1; i < window.size(); i++) {
			driver.switchTo().window(window.get(i));
			System.out.println("Child window title on clicking do not close me button is " + driver.getTitle());
			driver.close();

		}

		// switch to parent window
		driver.switchTo().window(parentWindow);
		System.out.println("switched to parent window :" + driver.getTitle());

		// click on wait for 5 sec
		driver.findElement(By.xpath("(//button[@id='color'])[2]")).click();

		// switch to child window
		windowHandles = driver.getWindowHandles();
		window = new ArrayList<String>(windowHandles);
		for (int i = 1; i < window.size(); i++) {
			driver.switchTo().window(window.get(i));
			System.out.println("Child window title on clicking wait for 5 seconds button is :" + driver.getTitle());
			driver.close();
		}

		// switch to parent window
		driver.switchTo().window(parentWindow);
		System.out.println("Switched to parent window :" + driver.getTitle());

	}

}
