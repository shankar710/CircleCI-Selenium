package com.TestCase.App;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.gargoylesoftware.htmlunit.javascript.host.media.webkitMediaStream;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import utilities.PropertyUtils;
import utilities.Wrapper;

public class ZohoTest {
	static WebDriver driver;
	static PropertyUtils property2;
	static Wrapper owrap;
	static ExtentReports oReport;
	static ExtentTest ologger;
	static ExtentTest Child;

	@BeforeSuite
	public static void StartReport() {
		oReport = new ExtentReports("./Reports.html");
		oReport.addSystemInfo("OS", "Windows 8");
		oReport.addSystemInfo("Host Name", "5947");
		oReport.addSystemInfo("Environment", "Shankar -QA");
		oReport.addSystemInfo("Report Name", "Test Mission Report");
		oReport.assignProject("Mission Zoho");

		// owrap=new Wrapper(driver);
	}

	@BeforeTest
	public static void zohocall() throws IOException {
		// System.setProperty("webdriver.chrome.driver","./SampleCodes/drivers/chromedriver.exe");
		System.setProperty("webdriver.chrome.driver", "Driver/chromedriver.exe");
		driver = new ChromeDriver();

		owrap = new Wrapper(driver);
		ologger = oReport.startTest("Launch App");
		ologger.assignCategory("Pre-Requiste");

		property2 = new PropertyUtils(
				"./ObjectRepository/EnvironmentVariable.properties");
		String urlvalue1 = property2.getpropertyvalue("urlvalue");

		driver.get(urlvalue1);
		driver.manage().window().maximize();

		attachPOC("AppLaunch1", LogStatus.PASS, "App Launched");
		InfoStatus(LogStatus.INFO, "Invoke app successfull");

		oReport.endTest(ologger);

	}

	@Test(priority = 1)
	public static void login() throws InterruptedException, IOException {
		ologger = oReport.startTest("Login App");
		ologger.assignCategory("Login to App");

		// owrap=new Wrapper(driver);

		By login = By.xpath("//a[text()='LOGIN']");
		owrap.click(login);

		String uid = property2.getpropertyvalue("login");
		String pwd = property2.getpropertyvalue("pwd");
		WebElement username = driver.findElement(By.id("lid"));
		username.sendKeys(uid);
		WebElement password = driver.findElement(By.id("pwd"));
		password.sendKeys(pwd);
		WebElement signin = driver.findElement(By.id("signin_submit"));
		signin.click();
		Thread.sleep(3000);

		InfoStatus(LogStatus.INFO, "Login Page displays with no error");
		InfoStatus(LogStatus.INFO, "SShankar User credential is used");
		attachPOC("AppLogin", LogStatus.PASS, "App Loggged in successfully");
		oReport.endTest(ologger);

	}

	@Test(priority = 2)
	public static void clicksettings() throws InterruptedException, IOException {

		ologger = oReport.startTest("Navigate to settings");

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		WebElement settings = driver.findElement(By
				.xpath("//span[contains(text(),'Settings')]"));
		settings.click();
		InfoStatus(LogStatus.INFO, "Navigated to settings");

		// call propertyfile for settings

		WebElement company = driver.findElement(By
				.xpath("//a[text()='Company']"));
		company.click();
		Thread.sleep(1000);
		InfoStatus(LogStatus.INFO, "Navigated to Company");

		WebElement depart = driver.findElement(By
				.xpath("//a[text()='Department(s)']"));
		depart.click();
		Thread.sleep(1000);
		InfoStatus(LogStatus.INFO, "Navigated to Department");

		WebElement portal = driver.findElement(By
				.xpath("//a[text()='Portal Settings']"));
		portal.click();
		Thread.sleep(1000);
		InfoStatus(LogStatus.INFO, "Navigated to Portal Settings");

		WebElement blockedip = driver.findElement(By
				.xpath("//a[text()='Blocked IPs']"));
		blockedip.click();
		InfoStatus(LogStatus.INFO, "Navigated to Blocked IPs");

		WebElement websites = driver.findElement(By
				.xpath("//a[text()='Websites']"));
		websites.click();
		InfoStatus(LogStatus.INFO, "Navigated to Websites");

		WebElement auto = driver.findElement(By
				.xpath("//a[text()='Automation']"));
		auto.click();
		InfoStatus(LogStatus.INFO, "Navigated to Automation");

		WebElement temp = driver.findElement(By
				.xpath("//a[text()='Templates']"));
		temp.click();
		InfoStatus(LogStatus.INFO, "Navigated to Templates");

		WebElement lead = driver.findElement(By
				.xpath("//a[text()='Lead Scoring']"));
		lead.click();
		InfoStatus(LogStatus.INFO, "Navigated to Lead Scoring");

		WebElement inte = driver.findElement(By
				.xpath("//a[text()='Integrations']"));
		inte.click();
		Thread.sleep(2000);
		InfoStatus(LogStatus.INFO, "Navigated to Integrations");

		attachPOC("SettingsPage", LogStatus.PASS,
				"Navigated to settings successfully");
		oReport.endTest(ologger);
	}

	public static WebElement clickelement(WebElement oweb) {

		WebElement ele = null;
		WebDriverWait owait = new WebDriverWait(driver, 10);
		ele = owait.until(ExpectedConditions.elementToBeClickable(oweb));
		return ele;

	}

	@Test(priority = 3)
	public static void addoperator() throws IOException, InterruptedException {
		ologger = oReport.startTest("Add Operator");
		ologger.assignCategory("Adding Operator Details");

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		WebElement operator = driver.findElement(By.id("setting_sm_user"));
		operator.click();

		WebElement addoperator = driver.findElement(By.id("buttonuseradd"));
		addoperator.click();
		String emailid = property2.getpropertyvalue("myemail");
		System.out.println(emailid);

		WebElement email = driver
				.findElement(By
						.xpath("//input[@type='text'][@placeholder='Enter the email address']"));
		email.sendKeys(emailid);
		InfoStatus(LogStatus.INFO, "Navigated to Add operator screen");

		WebElement save = driver.findElement(By.xpath("//div[text()='Save']"));
		save.click();

		InfoStatus(LogStatus.INFO, "Email id added is :" + emailid);
		Thread.sleep(3000);
		attachPOC("AddOperator", LogStatus.PASS,
				"Operator id added successfully");
		oReport.endTest(ologger);

	}

	@Test(enabled = false)
	public static void deloperator() {

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		WebElement delete1 = driver.findElement(By
				.xpath("//em[text()='Delete']"));
		delete1.click();
		WebElement delete2 = driver.findElement(By
				.xpath("//span[text()='Delete']"));
		delete2.click();

	}

	@Test(priority = 4, dataProvider = "SearchProvider")
	public static void addtriger(String rulenames) throws IOException,
			InterruptedException {

		ologger = oReport.startTest("Add Trigger with name :" + rulenames);
		ologger.assignCategory("Add trigger Rules");

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		WebElement aut = driver.findElement(By.id("setting_sm_automation"));
		aut.click();
		boolean btstatus = owrap.Iselementdisplayed(By
				.xpath("//*[@documentclick='addTriggers']"));
		System.out.println(btstatus);
		if (btstatus) {
			System.out.println("First rule is to be added");
			WebElement addnew = driver.findElement(By
					.xpath("//*[@documentclick='addTriggers']"));
			addnew.click();
		} else {
			System.out.println("New rule is to be added");
			WebElement addnew2 = driver.findElement(By.id("addrule"));
			addnew2.click();

		}

		InfoStatus(LogStatus.INFO, "Add rule page is displayed for rule id:"
				+ rulenames);

		WebElement rulename = driver.findElement(By
				.xpath("//*[@data-type='ruletitle']"));
		rulename.sendKeys(rulenames);

		WebElement drop1 = driver.findElement(By.id("triggerevent_0_div"));
		drop1.click();
		WebElement drop1V = driver.findElement(By
				.xpath("//*[@id='triggerevent_00']/li[@val='1']"));
		drop1V.click();

		WebElement drop2 = driver.findElement(By.id("prior0_col1_div"));
		drop2.click();
		driver.findElement(By.className("asign-srctxbx")).sendKeys(
				"visitor source");

		WebElement drop2V = driver.findElement(By
				.xpath("//li[@title='Visitor Source']"));
		drop2V.click();

		WebElement drop2v2 = driver.findElement(By.id("prior0_col3"));
		Actions act = new Actions(driver);
		act.moveToElement(drop2v2);
		act.click();
		act.sendKeys("Referral");
		act.build().perform();

		driver.findElement(By.xpath("//*[@id='col3_div']/ul/li[@val='2']"))
				.click();

		WebElement drop3 = driver.findElement(By.id("action_0_div"));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,700)");

		drop3.click();
		WebElement drop3v = driver.findElement(By
				.xpath("//*[@id='action_01']/li[@val='8']"));
		drop3v.click();
		WebElement drop3v2 = driver.findElement(By
				.xpath("//*[@triggertype='8']"));
		drop3v2.click();
		drop3v2.sendKeys("20");
		InfoStatus(LogStatus.INFO, "All the details is filled for rule id :"
				+ rulenames);

		WebElement save = driver.findElement(By
				.xpath("//*[@documentclick='handleRuleUpdate']"));
		save.click();
		Thread.sleep(2000);
		attachPOC("AddTrigger" + rulenames, LogStatus.PASS,
				"TriggerRule added successfully for rule id :" + rulenames);

		verifyRule(rulenames);
		ologger.appendChild(Child);
		oReport.endTest(ologger);

	}

	public static void verifyRule(String name) throws IOException {
		Child = oReport.startTest("Verify Trigger Rule " + name);
		Child.assignCategory("Verify the Rule Added");

		WebElement ele = driver
				.findElement(By
						.xpath("//*[@id='rulelist']/div[1]//*[@class='w24p rule_dtl']/div[1]"));
		String rslt = ele.getAttribute("innerText");
		System.out.println(rslt);

		WebElement ele2 = driver
				.findElement(By
						.xpath("//*[@id='rulelist']/div[1]//*[@class='w24p rule_dtl']"));
		String rslt2 = ele2.getAttribute("innerText");
		System.out.println(rslt2);

		List<WebElement> check2 = new ArrayList<>();
		List<String> allList = new ArrayList<>();
		WebDriverWait owait = new WebDriverWait(driver, 10);
		owait.until(ExpectedConditions.numberOfElementsToBeMoreThan(
				By.xpath("//*[@id='rulelist']/div[1]//*[@class='w24p rule_dtl']/div"),
				2));
		check2 = driver
				.findElements(By
						.xpath("//*[@id='rulelist']/div[1]//*[@class='w24p rule_dtl']/div"));

		System.out.println(check2.size());

		for (int i = 0; i < check2.size(); i++) {
			String test = check2.get(i).getText();
			System.out.println(test);
			allList.add(test);

			if (i == 0) {
				try {
					Assert.assertEquals(test, "TestRule4");
				} catch (AssertionError e) {
					// TODO: handle exception
					attachPOChild("RULE not added" + name, LogStatus.FAIL,
							"Exception is" + e);

				}

			}
			if (i == 1) {
				Assert.assertEquals(
						test.contains("Glow button after")
								&& test.contains("20"), true,
						"Trigger type is validated");
			}
			if (i == 2) {
				Assert.assertTrue(test.contains("Modified by you"));
				System.out.println("Created by current User");
			}
		}
		System.out.println(allList);

	}

	public static void attachPOC(String POCname, LogStatus status, String msg)
			throws IOException {
		String path = owrap.screenshot(POCname);
		System.out.println(path);
		String htmlTag = ologger.addScreenCapture(path);
		ologger.log(status, msg + htmlTag);
	}

	public static void attachPOChild(String POCname, LogStatus status,
			String msg) throws IOException {
		String path = owrap.screenshot(POCname);
		System.out.println(path);
		String htmlTag = Child.addScreenCapture(path);
		Child.log(status, msg + htmlTag);
	}

	public static void InfoStatus(LogStatus status, String msg)
			throws IOException {
		ologger.log(status, msg);
	}

	@DataProvider(name = "SearchProvider")
	public Object[][] getDataFromDataprovider() {
		return new Object[][] { { "TestRule1" }, { "TestRule2" },

		};

	}

	@AfterSuite
	public void reportflush() {
		oReport.flush();
	}

}
