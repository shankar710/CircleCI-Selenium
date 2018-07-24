package utilities;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Wrapper {
	
	private  WebDriver driver;
	         WebDriverWait wait;
	
	public Wrapper(WebDriver driver) {
		this.driver = driver;
		//this.wait= new WebDriverWait(driver, 10);
	}
	
	public WebElement waitunitil(By locator,int timeout) {
		WebDriverWait oWait = new WebDriverWait(driver, timeout);
		WebElement ele = null ;
		 ele=oWait.until(ExpectedConditions.presenceOfElementLocated(locator));
		 return ele;
		
	}
	
	public void entertext(By locator,String text) {
		WebElement ele ;
		ele=waitunitil(locator, 10);
		//ele.click();
		//ele.clear();
		ele.sendKeys(text);
		
	}

	public void click(By locator) {
		WebElement ele=waitunitil(locator, 10);
		ele.click();
		
	}

	public void mouseOver(By locator) {
		WebElement ele =waitunitil(locator, 10);
		Actions oAct = new Actions(driver);
		oAct.moveToElement(ele);
	}
	
	public String screenshot(String Filename) throws IOException{
		File srcFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		String path = "./screenshots/"+Filename+".png";
		FileUtils.copyFile(srcFile, new File(path));
		return path;
		
	}
	public void waitforPageLoad(int time) {
		driver.manage().timeouts().pageLoadTimeout(time, TimeUnit.SECONDS);
	}
	public  void waitForPageToBeReady() 
	{
	    JavascriptExecutor js = (JavascriptExecutor)driver;

	    //This loop will rotate for 100 times to check If page Is ready after every 1 second.
	    //You can replace your if you wants to Increase or decrease wait time.
	    for (int i=0; i<400; i++)
	    { 
	        try 
	        {
	            Thread.sleep(1000);
	        }catch (InterruptedException e) {} 
	        //To check page ready state.

	        if (js.executeScript("return document.readyState").toString().equals("complete"))
	        { 
	            break; 
	        }   
	      }
	 }
	public boolean Iselementdisplayed(By locator) {
		boolean result = false;
		try {
			WebElement element = driver.findElement(locator);
			if (element.isDisplayed()) {
				result = true;
			}
		} catch (Exception e) {
			result = false;
		}
		return result;
	}
}
