package resources;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Base {
	
	public  WebDriver driver;
	public  Properties prop;
	
	public WebDriver initializeDriver( ) throws IOException {
		
			prop = new Properties ();
		//connecting file
			FileInputStream fis = new FileInputStream("C:\\Users\\Rani\\eclipse-workspace\\MavenProject\\src\\main\\java\\resources\\Data.properties"); 
		//loading file
		prop.load(fis);
		String browserName = prop.getProperty("browser");
		
		if (browserName.equals("chrome")) {
			
			WebDriverManager.chromedriver().setup();
			
			//System.setProperty("webdriver.chrome.driver", "F:\\QA Automation\\Selenium\\chromedriver.exe");
			 driver = new ChromeDriver();
			
		}
		
		else if (browserName.equals("firefox")) {
			
			WebDriverManager.firefoxdriver().setup();
			
			//System.setProperty("webdriver.geko.driver", "F:\\QA Automation\\Selenium\\geckodriver.exe");
			 driver = new FirefoxDriver();
			
		}
		
		else if (browserName.equals("IE")) {
			
			
		}
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		
		return driver;
		
	}
	
	public void getScreenshot(String result) throws IOException {
		// Web Driver take the screenshot as a file and save in virtually in src
		// variable.
		File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		// copy the file from src and put in your local machine with the name of failed
		// test class
		FileUtils.copyFile(srcFile, new File("F://screenshotTest//" + result + "screenshot.png"));
	}
}
