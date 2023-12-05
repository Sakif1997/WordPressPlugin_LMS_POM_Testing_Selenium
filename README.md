# WordPressPlugin_LMS_POM_Testing_Selenium


# WordPress plug-in testing

WordPress plugins are designed to enhance the functionality and performance of your WordPress website. To elevate functionality and performance through my automation tests, ensuring smooth plugin installation, activation, and configuration. Dive into the versatile toolkit provided by the plugin, customizing appearance and behavior to perfectly align with your website's design and preferences.

Plugin info: https://wordpress.org/plugins/academy/

## Target/goals
### Scenario 01:   
1. Log in to your WordPress site.  
2. Check whether the “Academy LMS” Plugin is Active or not.  
3. If Active, navigate to the Plugin. Otherwise, Install the Plugin and Activate it.     
4. Validate whether the Academy LMS is visible or not on the Admin Dashboard menu.  
### Scenario 02:
Navigate to the Academy LMS menu -> Add ones Mode.  
5. From Add-ons All -> On the free tools(Quizes, Multi Instructors,Migration ToolWebhooks) click to active those tools.  
6. Validate Active tools lists  
7. Validate Deactivate tools list  
8. Navigate Active tools from Menu  
### Scenario 03:  
Courses Menu Validation  
9. From All Courses - Add new Course form validaton  
10. From All Quiz - Add new Quiz form validation  
11. From Announcements - Add new Announcement form validation  
12. From Webhooks -Add new Wehbook form validation  

## Prerequisites
Install Xampp: https://www.apachefriends.org/download.html  

Open Xampp to start Apache and MySQL  

Download Wordpress file: https://wordpress.org/download/  

## Test workflow

### Test file information
The following instructions will help you navigate those testing pages. We will create some packages. At the package level, there is a list of classes where you can create methods, use methods for particular pages, and run and test the testing pages separately

1. Set Environment
i) pom.xml [dependencies set]  
ii) BrowserSetup[create separate package ]  

2. Page Object Model: create methods, using methods for separate page and create test cases of those pages  
i) Methods[package name: Utilites]  
ii) Seperate Page objects[package name: Utilites]  
iii) TestCases [package name: TestCases ]  

3. Create Allure report   
i) pom.xml [dependencies set for allure report]  
ii) Testng.xml [to run all test file togather]  

Package Visualization:  
![package](https://github.com/Sakif1997/WordPressPlugin_LMS_POM_Testing_Selenium/assets/45315685/44edb701-3e51-4689-96a1-1152e2a96e34)



##  Set Environment
Create a Maven Project  
Set pom.xml file   
pom.xml file Code:  
Set Under Dependencies  
```ruby
      <!-- https://mvnrepository.com/artifact/org.testng/testng -->
<dependency>
    <groupId>org.testng</groupId>
    <artifactId>testng</artifactId>
    <version>7.6.1</version>
    <scope>test</scope>
</dependency>
<!-- https://mvnrepository.com/artifact/org.seleniumhq.selenium/selenium-java -->
<dependency>
    <groupId>org.seleniumhq.selenium</groupId>
    <artifactId>selenium-java</artifactId>
    <version>4.6.0</version>
</dependency>
<!-- https://mvnrepository.com/artifact/io.github.bonigarcia/webdrivermanager -->
<dependency>
    <groupId>io.github.bonigarcia</groupId>
    <artifactId>webdrivermanager</artifactId>
    <version>5.3.0</version>
</dependency>
<dependency>
			<groupId>io.qameta.allure</groupId>
			<artifactId>allure-testng</artifactId>
			<version>2.19.0</version>
</dependency>
```

Create Some class under Packages 
One of the package will hold BorwserSetup in which we run the automation Code  
Inside BorwserSetup Class:
It will hold three Driver(Chrome, Firefox, and Edge), use according to your preferences 
I prefer to use the Edge Driver (Edge Browser) to run my code.
```ruby
package Wppool.AssignmentWP;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BrowserSetup {
	private static String BrowserName = System.getProperty("browser", "Edge");
	private static final ThreadLocal<WebDriver> DRIVER_LOCAL = new ThreadLocal<>();
	public static WebDriver getDriver() {
		return DRIVER_LOCAL.get();
	}
	public static void setDriver(WebDriver driver) {
		BrowserSetup.DRIVER_LOCAL.set(driver);
	}
	public static WebDriver getBrowser(String BrowserName) {
		switch (BrowserName.toLowerCase()) {
		case "chrome":
			ChromeOptions option1 = new ChromeOptions();
			option1.addArguments("--remote-allow-origins=*");
			WebDriverManager.chromedriver().setup();
			return new ChromeDriver(option1);
		case "edge":
			EdgeOptions option2 = new EdgeOptions();
			option2.addArguments("--remote-allow-origins=*");
			WebDriverManager.edgedriver().setup();
			return new EdgeDriver(option2);
		case "firefox":
			FirefoxOptions option3 = new FirefoxOptions();
			option3.addArguments("--remote-allow-origins=*");
			WebDriverManager.firefoxdriver().setup();
			return new FirefoxDriver(option3);
		default:
			throw new RuntimeException("Browser Not found");
		}
	}
	@BeforeSuite
	public static synchronized void setBrowser() {
		WebDriver webDriver = getBrowser(BrowserName);
		webDriver.manage().window().maximize();
		setDriver(webDriver);
	}
	@AfterSuite
	public static synchronized void quitBrowser() {
		getDriver().quit();
	}
}

```

## Page Object model  
### Methods
Create a package which include classes like method and other testing Page  
Here's a brief overview of some key methods in my customized class:
* getElement(By locator):Retrieves a WebElement based on the provided locator.  
* clickElement(By locator):Clicks on a WebElement identified by the given locator.  
* FieldValue(By locator, String text):Enters text into a WebElement identified by * the given locator.  
* WaitElementVisible(By locator):Waits for a WebElement to be visible within a specified time frame.  
* clickWaitElement(By locator):Waits for a WebElement to be visible and then clicks on it.  
* Hover(By locator):Hovers over a WebElement using the Actions class.  
* ScrollUp() / ScrollDown():Scrolls the web page either up or down using JavaScriptExecutor.  
* DropDownSelectElement(By locator, int serialnumber):Selects a dropdown option by index.  
* dropDownSelectByVisibleText(By dropdownLocator, String text):Selects a dropdown option by visible text.  
* DropDownSelectByVisibleText(By locator, String text):Selects a dropdown option by visible text using the Select class.  
* clickDropdownOption(By dropdownLocator):Clicks on a dropdown to open it and then presses the Enter key using Actions class.  
* takeScreenshot(String name):Captures a screenshot and attaches it to the Allure report.  
```ruby
package Utilites;
import java.io.ByteArrayInputStream;
import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import static Browser.BrowserSetup.getDriver;
import io.qameta.allure.Allure;

public class Methods {
	public WebElement getElement(By locator) {
		//import static Wppool.AssignmentWP.BrowserSetup.getDriver;
		return getDriver().findElement(locator);//driver = getDriver();
	}
	public void clickElement(By locator) {
		getElement(locator).click();
	}
	public void FieldValue(By locator,String text) {
		getElement(locator).sendKeys(text);
	}
	public void WaitElementVisible(By locator) {
		WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(20));
		wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}
	public void clickWaitElement(By locator) {
		WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(20));
		WebElement waitelement = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		waitelement.click();
	}
	public void Hover(By locator) {
		Actions action = new Actions(getDriver());
		//action.moveToElement(driver.findElement(locator)).perform();
		action.moveToElement(getElement(locator)).perform();;
	}
	public void ScrollUp() {
		JavascriptExecutor js =(JavascriptExecutor)getDriver();
		js.executeScript("window.scrollTo(document.body.scrollHeight,0)");
	}
	public void ScrollDown() {
		JavascriptExecutor js = (JavascriptExecutor)getDriver();
		js.executeScript("window.scrollTo(0,document.body.scrollHeight)");
	}
	public void DropDownSelectElement(By locator, int serialnumber) {
		Select	select = new Select(getElement(locator));
		select.selectByIndex(serialnumber);//
	}
	public void dropDownSelectByVisibleText(By dropdownLocator, String text) {
	    WebElement dropdown = getElement(dropdownLocator);
	    dropdown.click();  // Click on the div to open the dropdown

	    // Locate the option based on its text
	    By optionLocator = By.xpath(".//div[text()='" + text + "']");
	    WebElement option = dropdown.findElement(optionLocator);
	    option.click();
	}
	public void DropDownSelectByVisibleText(By locator, String text) {
		WebElement dropDownField = getElement(locator);
		Select select = new Select(dropDownField);
		select.selectByVisibleText(text);
	}

    public void clickDropdownOption(By dropdownLocator) {
        // Click on the div to open the dropdown
        WebElement dropdown = getDriver().findElement(dropdownLocator);
        dropdown.click();

        Actions action = new Actions(getDriver()); 
        action.keyDown(Keys.ENTER);
        action.build().perform();
    }
	
	//for allure report
		public void takeScreenshot(String name) {
			Allure.addAttachment(name, new ByteArrayInputStream(((TakesScreenshot)getDriver()).getScreenshotAs(OutputType.BYTES)));
		}
}
```
### Scenario Replication Classes  
### Targeted Scenario:  Scenario 01:  
1. Log in to your WordPress site.
2. Check whether the “Academy LMS” Plugin is Active or not.
3. If Active, navigate to the Plugin. Otherwise, Install the Plugin and Activate it.
Validate whether the Academy LMS is visible or not on the Admin Dashboard menu
4. Login to Dashbord

Login to Dashbord page objects and there intented xpaths
Overview of the sleecing path of the login page image:  
![login fields paths](https://github.com/Sakif1997/Daraz-AutomationTesting-PageObjectModeling/assets/45315685/472d4c39-0201-473e-858e-a0dfe5d5a4e2)  


Login to Dashbord class:  
```ruby
package Utilites;
import org.openqa.selenium.By;
public class WordpressLogin extends Methods {
	public By USERNAME = By.xpath("//input[@id='user_login']");
	public By PASSWORD = By.xpath("//input[@id='user_pass']");
	public By LOGINBUTTON = By.xpath("//input[@id='wp-submit']");
	public By DASHBORD = By.xpath("//h1[normalize-space()='Dashboard']");
	public By SAKIF_Hover = By.xpath("//body/div[@id='wpwrap']/div[@id='wpcontent']/div[@id='wpadminbar']/div[@id='wp-toolbar']/ul[@id='wp-admin-bar-top-secondary']/li[@id='wp-admin-bar-my-account']/a[1]");
	public By LOGOUT =By.xpath("//a[@class='ab-item'][normalize-space()='Log Out']");
	public void WordPressLogin() throws InterruptedException{
		FieldValue(USERNAME, "Sakif");
		Thread.sleep(2000);
		FieldValue(PASSWORD, "sakif@006");
		Thread.sleep(2000);
		clickElement(LOGINBUTTON);
		WaitElementVisible(DASHBORD);
		Thread.sleep(2000);
	}
	public void logout() throws InterruptedException{
		Hover(SAKIF_Hover);
		clickWaitElement(LOGOUT);
	}
}
```
Login to Dashbord testSuite:  
```ruby
package TestCases;
import org.testng.annotations.Test;
import Browser.BrowserSetup;
import Utilites.WordpressLogin;

public class WordPressLoginToDashbord extends BrowserSetup{
	WordpressLogin login = new WordpressLogin();
	@Test
	public void WPlogin() throws InterruptedException{
		getDriver().get("http://localhost/wordpress-6.3.1/wordpress/wp-admin");
		Thread.sleep(2000);
		login.WordPressLogin();
	}
}

```
Replication of three, four, and five scenarios(2,3, and 4):  
2. Check whether the “Academy LMS” Plugin is Active or not.  
3. If Active, navigate to the Plugin. Otherwise, Install the Plugin and Activate it.  
4. Validate whether the Academy LMS is visible or not on the Admin Dashboard menu.  
To replicate those scenario:  Fields xpath:  
![00](https://github.com/Sakif1997/WordPressPlugin_LMS_POM_Testing_Selenium/assets/45315685/0e95cbc9-da8e-4841-9311-62699f9d5212)

The Installation method is the main functionality of this class. It performs the following steps:
* Waits for the Dashboard to be visible.
* Hovers over the Plugins menu option.
* Clicks on the "Installed Plugins" option.
* Waits for the "Plugins" page to be visible.
* Clicks on the "Add New" plugin option.
* Waits for the "Add Plugins" page to be visible.
* Enters "Academy LMS" in the search field.
* Waits for the Academy LMS plugin to be visible.
* Clicks the "Install" button.
* Waits for the "Activate" button to be visible.
* Takes a screenshot of the plugin installation.
* Clicks the "Activate" button.
* Waits for the "Plugin activated" message to be visible.
* Takes a screenshot of the activation.
Video of execution:  
https://drive.google.com/file/d/1P8kfqOOP_t2YC3_KFUFb890xo6bymc-Y/view?usp=drive_link

Plugin install to activation
```ruby
package Utilites;
import org.openqa.selenium.By;

public class PluginInstallation extends Methods{
	public By Dashbord_Visible = By.xpath("//h1[normalize-space()='Dashboard']");
	public By plugin_Hover = By.xpath("//div[@class='wp-menu-image dashicons-before dashicons-admin-plugins']");
	public By pluginInstalloption_v_c = By.xpath("//a[normalize-space()='Installed Plugins']");
	public By PluginsPage_V = By.xpath("//h1[normalize-space()='Plugins']");
	public By AddNewPlugin_c = By.xpath("//a[@class='page-title-action']");
	public By AddpluginPage_v = By.xpath("//h1[normalize-space()='Add Plugins']");
	public By searchPluginField = By.xpath("//input[@id='search-plugins']");
	public By VisiblePlugin_w = By.xpath("//a[contains(text(),'Academy LMS – eLearning and online course solution')]");
	public By InstallButton_c = By.xpath("//a[@aria-label='Install Academy LMS – eLearning and online course solution for WordPress 1.9.8 now']");
	public By ActiveButton_w_c =  By.xpath("//a[normalize-space()='Activate']");
	public By pluginActivated_w = By.xpath("//p[normalize-space()='Plugin activated.']");
	public void Installation() throws InterruptedException{
		WaitElementVisible(Dashbord_Visible);
		Hover(plugin_Hover);
		clickWaitElement(pluginInstalloption_v_c);
		WaitElementVisible(PluginsPage_V);
		clickElement(AddNewPlugin_c);
		WaitElementVisible(AddpluginPage_v);
		FieldValue(searchPluginField, "Academy LMS");
		WaitElementVisible(VisiblePlugin_w);
		clickElement(InstallButton_c);
		WaitElementVisible(ActiveButton_w_c);
		takeScreenshot("LMS Plugin Installation");
		clickElement(ActiveButton_w_c);
		WaitElementVisible(pluginActivated_w);
		takeScreenshot("Activation of plugin");
	}
}
```
Plugin install to activation TestSuite[Run as TestNG]
```ruby
package TestCases;
import org.testng.annotations.Test;
import Browser.BrowserSetup;
import Utilites.PluginInstallation;
import Utilites.WordpressLogin;

public class PluginActivation extends BrowserSetup{
	WordpressLogin login = new WordpressLogin();
	PluginInstallation plug = new PluginInstallation();
	WordpressLogin wplog = new WordpressLogin();

	@Test(description = "Scenario 01:\r\n"
			+ "1. Log in to your WordPress site.\r\n"
			+ "2. Check whether the “Academy LMS” Plugin is Active or not.\r\n"
			+ "3. If Active, navigate to the Plugin. Otherwise, Install the Plugin and Activate it. \r\n"
			+ "4. Validate whether the Academy LMS is visible or not on the Admin Dashboard menu.")
	public void InstallToActivate() throws InterruptedException{
		getDriver().get("http://localhost/wordpress-6.3.1/wordpress/wp-admin");
		Thread.sleep(2000);
		login.WordPressLogin();
		plug.Installation();
		Thread.sleep(2000);
		wplog.logout();
	}
}
```

Test Run Result:  
![s1](https://github.com/Sakif1997/WordPressPlugin_LMS_POM_Testing_Selenium/assets/45315685/ade68ca7-d929-4017-8189-dbb80ed44506)


### Targeted Scenario:  Scenario 02:   
Navigate to the Academy LMS menu -> Add ones Mode.  
5. From Add-ons All -> On the free tools(Quizes, Multi Instructors,Migration ToolWebhooks) click to active those tools.  
6. Validate Active tools lists  
7. Validate Deactivate tools list  
8. Navigate Active tools from Menu  
To replicate those scenario: Fields xpath:
![01](https://github.com/Sakif1997/WordPressPlugin_LMS_POM_Testing_Selenium/assets/45315685/f0e10ef2-5340-4567-98c6-5911e1ed9535)  
The ToolsAdd method is the main functionality of this class. It performs the following steps:
* Hovers over the LMS menu.  
* Clicks on the "Add-ons" option.  
* Waits for the "Add-ons" page to be visible.  
* Clicks on various tools such as Quiz Tool, Multi-Instructor, Migration Tool, and Webhook.  
* Takes a screenshot after adding the free tools.
Scrolls up.  
Video of execution:
https://drive.google.com/file/d/1ymQpjRYlfjfuIE6bxfnSw61GUia3Bsge/view?usp=drive_link

Addons Class:  
```ruby
package TestingMethodsAndPages;
import org.openqa.selenium.By;
public class SetupSettings extends Methods {
	public By DARKMODESETTINGSMENU = "//div[normalize-space()='WP Dark Mode']"
	public By WaitDMsettingsVisible = "//a[contains(text(),'Settings')]"
	public By WaitSettingPage ="//h2[normalize-space()='WP Dark Mode Settings']"
	public By EnableBackendDarkMode = "(//div[@class='wp-dark-mode-ignore'])[2]"
	public By DASHBORD = "//div[normalize-space()='Dashboard']"
	public By WaitDashbord = "//h1[normalize-space()='Dashboard']"
	public By VALIDATEMODEBUTTON = "//p[@class='dark wp-dark-mode-ignore']"
	public void setUpSetting() throws InterruptedException{
		Hover(DARKMODESETTINGSMENU
		WaitElementVisible(WaitDMsettingsVisible
		Thread.sleep(2000
		clickElement(WaitDMsettingsVisible
		WaitElementVisible(WaitSettingPage
		clickElement(EnableBackendDarkMode
		Thread.sleep(2000
		clickElement(DASHBORD
		WaitElementVisible(WaitDashbord
		clickElement(VALIDATEMODEBUTTON
		//screenshot
		Thread.sleep(2000
		clickElement(VALIDATEMODEBUTTON
		//screenshot
		Thread.sleep(2000
	}

}
```
TestSuite[Run as TestNG]  
```ruby
package TestCases;
import org.testng.annotations.Test;
import Browser.BrowserSetup;
import Utilites.AddTool;
import Utilites.WordpressLogin;

public class LMSToolActivation extends BrowserSetup{
	AddTool addons = new AddTool();
	WordPressLoginToDashbord wplogin = new WordPressLoginToDashbord();
	WordpressLogin wplog = new WordpressLogin();

	@Test(description = "Scenario 02:\r\n"
			+ "Navigate to the Academy LMS menu -> Add ones Mode.\r\n"
			+ "5.From Add-ons All -> On the free tools(Quizes, Multi Instructors,Migration ToolWebhooks) click to active those tools.\r\n"
			+ "6.Validate Active tools lists\r\n"
			+ "7. Validate Deactivate tools list\r\n"
			+ "8. Navigate Active tools from Menu")
	public void Addons() throws InterruptedException{
		wplogin.WPlogin();
		addons.ToolsAdd();
		Thread.sleep(12000);
		wplog.logout();

	}
}
```
Test Run Result:  
![s2](https://github.com/Sakif1997/WordPressPlugin_LMS_POM_Testing_Selenium/assets/45315685/d9384227-09a4-4c82-856e-0e8a5a57f8f6)

### Targeted Scenario:  Scenario 03:   

Courses Menu Validation
9. From All Courses - Add new Course form validaton
10. From All Quiz - Add new Quiz form validation
11. From Announcements - Add new Announcement form validation
12. From Webhooks -Add new Wehbook form validation

#### Replicate Scenario 03: 9. From All Courses - Add new Course form validaton
To replicate those scenario: Fields xpath:  
![02](https://github.com/Sakif1997/WordPressPlugin_LMS_POM_Testing_Selenium/assets/45315685/2066c179-dc19-40be-b6e0-a9f5041a364b)  
The addCourse method is the main functionality of this class. It performs the following steps:  
* Hovers over the LMS menu.  
* Clicks on the "Courses" option.  
* Clicks on "Add New Course."  
* Waits for the "Add New Course" page to be visible.  
* Enters a course title in the specified field.  
* Enters a course description using the Actions class and simulating keyboard input.  
* Takes a screenshot after entering course details.  
* Scrolls up.  
* Clicks the "Publish" button.  
* Waits for a confirmation toast message to be visible.  
* Takes a screenshot after publishing the course.  
Video of execution:  
https://drive.google.com/file/d/1bxxzH13cuMxcAsoOHeaR-JDC-a_X3I3t/view?usp=drive_link  

Add Course class:
```ruby
package Utilites;
import static Browser.BrowserSetup.getDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;

public class AddCourses extends Methods{
	public By MenuLMS_H = By.xpath("//div[@class='wp-menu-image svg']");
	public By Course_w_c= By.xpath("//a[normalize-space()='Courses']");
	public By AddnewCourse_w_c= By.xpath("//span[normalize-space()='Add New Course']");
	public By CourseTitle_w_f= By.xpath("//input[@placeholder='Add course title here']");
	public By CourseDescription_f= By.xpath("//div[@class='is-root-container is-layout-flow wp-block-post-content block-editor-block-list__layout']");
	//public By DifficultyLevel_c= By.xpath("//input[@id='beginner']");
	//scroll up
	public By Publish_c = By.xpath("//button[@type='submit']");
	public By ConfirmationTostMessage_w = By.xpath("//div[@class='academy-toasts']");
	//public By = By.xpath("");
	public void addCourse() throws InterruptedException{
		Hover(MenuLMS_H);
		WaitElementVisible(Course_w_c);
		clickElement(Course_w_c);
		WaitElementVisible(AddnewCourse_w_c);
		clickElement(AddnewCourse_w_c);
		WaitElementVisible(CourseTitle_w_f);
		FieldValue(CourseTitle_w_f, "English");
		clickElement(CourseDescription_f);
		Thread.sleep(2000);
		Actions action = new Actions(getDriver()); 
		action.sendKeys("English is a language that started in Anglo-Saxon England."
				+ " It is originally from Anglo-Frisian and Old Saxon dialects. English is now used as a global language. There are about 375 million native");
		action.keyUp(Keys.CONTROL);
		action.build().perform();
		Thread.sleep(2000);
		takeScreenshot("COurse Creation");
		ScrollUp();
		clickElement(Publish_c);
		WaitElementVisible(ConfirmationTostMessage_w);
		Thread.sleep(2000);
		takeScreenshot("COurse Creation");
	}
}
```

TestSuite[Run as TestNG]:
```ruby
package TestCases;
import org.testng.annotations.Test;
import Browser.BrowserSetup;
import Utilites.AddCourses;
import Utilites.WordpressLogin;

public class CustomAddNewCourse extends BrowserSetup{
	AddCourses add = new AddCourses();
	WordPressLoginToDashbord wplogin = new WordPressLoginToDashbord();
	WordpressLogin wplog = new WordpressLogin();
	@Test(description = "Scenario 03:\r\n"
			+ "Courses Menu Validation\r\n"
			+ "9. From All Courses - Add new Course form validaton")
	public void NewCourse() throws InterruptedException{
		wplogin.WPlogin();
		add.addCourse();
		Thread.sleep(10000);
		wplog.logout();
	}
}
```
Test Run Result:
![s3Addcourse](https://github.com/Sakif1997/WordPressPlugin_LMS_POM_Testing_Selenium/assets/45315685/0f5bfe00-1492-4fe8-bd53-2fe44502fdae)

#### Replicate Scenario 03: 10. From All Quiz - Add new Quiz form validation   
To replicate those scenario: Fields xpath:  
![quiz](https://github.com/Sakif1997/WordPressPlugin_LMS_POM_Testing_Selenium/assets/45315685/6751ff47-498d-4b2b-b15d-47a8d98fcb61)  
The SetupQuiz method is the primary functionality of this class. It performs the following steps:  
* Hovers over the LMS menu.  
* Clicks on the "Quizzes" option.  
* Clicks on "Add New Quiz."  
* Waits for the quiz page to be visible.  
* Enters a quiz title and summary.  
* Takes a screenshot of the question summary page.  
* Clicks the "Save & Next" button.  
* Clicks the "Add Question" button.  
* Enters a question title and sets up true/false answer options.  
* Takes a screenshot of the question formation page.  
* Scrolls down.  
* Clicks the "Add Question" button.  
* Clicks the "Save & Next" button.  
* Hides options and finishes the quiz setup.  
* Takes a screenshot after the quiz is added.  

Video of execution:
https://drive.google.com/file/d/1_QeND-e_K3x1zUjGi6weYQS7LwJWy0FW/view?usp=drive_link

Add quiz functionality class:
```ruby
package Utilites;
import static Browser.BrowserSetup.getDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;

public class QuizTool extends Methods{
	public By MenuLMS_H = By.xpath("//div[@class='wp-menu-image svg']");
	public By QuizTool_w_c= By.xpath("//a[normalize-space()='Quizzes']");
	public By AddnewQuiz_w_c= By.xpath("//span[normalize-space()='Add New Quiz']");
	public By QuizPage_w = By.xpath("//h3[normalize-space()='Quiz']");
	public By QuizTitle_f= By.xpath("//input[@id='title']");
	public By QuizSummary_f= By.xpath("//textarea[@id='content']");
	public By SaveNextButton_c= By.xpath("//span[normalize-space()='Save & Next']");
	public By AddQuestion_W_c= By.xpath("//span[normalize-space()='Add Question']");
	public By QuestionTitle_w_f= By.xpath("//input[@id='questionTitle']");
	public By AnswerTypeTrueFalse_c =By.xpath("//div[contains(@class,'academy-react-select__value-container academy-react-select__value-container--has-value css-hlgwow')]//div[contains(@class,'academy-react-select__input-container css-19bb58m')]");
	public By AnswerRadioButton_c = By.xpath("(//input[contains(@type,'radio')])[1]");
	public By AddQuestionButton_c = By.xpath("//button[@type='submit']");
	public By SaveAndNext = By.xpath("//button[contains(@class,'academy-btn academy-btn--md academy-btn--preset-purple academy-btn--border-rounded')]");
	public By HideOption_c= By.xpath("//div[@id='timelimit']//span[@class='academy-slider']");
	public By Finish_c= By.xpath("//button[contains(@class,'academy-btn academy-btn--md academy-btn--preset-purple academy-btn--border-rounded')]");
	
	public void SetupQuiz() throws InterruptedException{
		Hover(MenuLMS_H);
		WaitElementVisible(QuizTool_w_c);
		clickElement(QuizTool_w_c);
		WaitElementVisible(AddnewQuiz_w_c);
		clickElement(AddnewQuiz_w_c);
		Thread.sleep(2000);
		WaitElementVisible(QuizPage_w);
		FieldValue(QuizTitle_f, "Basic Questions");
		Thread.sleep(2000);
		FieldValue(QuizSummary_f, "General Knowledge");
		Thread.sleep(2000);
		takeScreenshot("Question Summary Page");
		clickElement(SaveNextButton_c);
		
		WaitElementVisible(AddQuestion_W_c);
		clickElement(AddQuestion_W_c);

		WaitElementVisible(QuestionTitle_w_f);
		FieldValue(QuestionTitle_w_f, "Software is easily maintainable if you form a good and professional QA team.");
		clickElement(AnswerTypeTrueFalse_c);
		Thread.sleep(2000);
		Actions action = new Actions(getDriver());
		action.keyDown(Keys.ARROW_DOWN);
		action.keyUp(Keys.CONTROL);
		action.keyDown(Keys.ENTER);
		action.build().perform();
		clickElement(AnswerTypeTrueFalse_c);
		action.keyDown(Keys.ARROW_UP);
		action.keyUp(Keys.CONTROL);
	    action.keyDown(Keys.ENTER);
	    action.build().perform();	
	    clickElement(AnswerTypeTrueFalse_c);
		Thread.sleep(2000);
	    action.keyDown(Keys.ENTER);
	    action.build().perform();	
	    
		clickWaitElement(AnswerRadioButton_c);
		takeScreenshot("Question Formation page");
		ScrollDown();
		clickElement(AddQuestionButton_c);
		Thread.sleep(4000);
		clickWaitElement(SaveAndNext);
		Thread.sleep(2000);
		
		clickWaitElement(HideOption_c);;
		clickElement(HideOption_c);
		clickElement(Finish_c);
		Thread.sleep(2000);
		takeScreenshot("Quiz Added");
	}
}
```
testSuite[Run as TestNG]
```ruby
package TestCases;
import org.testng.annotations.Test;
import Browser.BrowserSetup;
import Utilites.QuizTool;
import Utilites.WordpressLogin;

public class AddQuizValidation extends BrowserSetup{
	WordPressLoginToDashbord wplogin = new WordPressLoginToDashbord();
	WordpressLogin wplog = new WordpressLogin();
	QuizTool qz =new QuizTool();
	@Test(description = "Scenario 3"
			+ "10. From All Quiz - Add new Quiz form validation")
	public void CreateQuiz() throws InterruptedException{
		wplogin.WPlogin();
		qz.SetupQuiz();
		Thread.sleep(10000);
		wplog.logout();
	}
}
```
Test Run Result:
![q](https://github.com/Sakif1997/WordPressPlugin_LMS_POM_Testing_Selenium/assets/45315685/622d120d-db80-4062-964d-aeb2d9ac3a7d)

#### Replicate Scenario 03: 11. From Announcements - Add new Announcement form validation    
To replicate those scenario: Fields xpath:  
![03](https://github.com/Sakif1997/WordPressPlugin_LMS_POM_Testing_Selenium/assets/45315685/f770ca37-6f7e-4f50-b181-e6bdab553b63)  
The createAnnouncement method is the primary functionality of this class. It performs the following steps:  
* Hovers over the LMS menu.  
* Clicks on the "Announcements" option.  
* Clicks on "Add Announcement."  
* Selects a field (probably for language selection).  
* Enters a title for the announcement.  
* Enters content for the announcement.  
* Takes a screenshot of the announcement formation.  
* Scrolls down.  
* Clicks the "Create Announcement" button.  
* Takes a screenshot after the announcement is created.  

Video of execution:  
https://drive.google.com/file/d/1KVlKMJWbDC95j0PLp3sgi1gV-pBvWNTw/view?usp=drive_link

Announcement functionality class:
```ruby
package Utilites;
import static Browser.BrowserSetup.getDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;

public class AnnouncementTool extends Methods{
	public By MenuLMS_H = By.xpath("//div[@class='wp-menu-image svg']");
	public By Announcement_c= By.xpath("//a[normalize-space()='Announcements']");
	public By AddAnouncement_w_c = By.xpath("//span[normalize-space()='Add Announcement']");
	public By SelectField_c_D= By.xpath("//div[@class='academy-react-select__value-container academy-react-select__value-container--is-multi css-hlgwow']//div[@class='academy-react-select__input-container css-19bb58m']");
	//public By English =By.cssSelector(".academy-react-select__menu-list.academy-react-select__menu-list--is-multi.css-q9ycoz");
	public By Title_F = By.xpath("//input[@id='title']");
	//public By ContentFormat_c= By.xpath("(//*[name()='svg'])[5]");
	public By Content_c_f= By.xpath("//div[@class='is-root-container is-layout-flow wp-block-post-content block-editor-block-list__layout']");
	//public By ContentValue_f= By.xpath("(//p[@id='block-f9aaf37a-a28a-4c61-b6f8-5f3e63580ff7'])[1]");
	public By CreateAnnouncement= By.xpath("//button[@type='submit']");
	//public By = By.xpath("");
	//public By = By.xpath("");
	public void createAnnouncement() throws InterruptedException{
		Hover(MenuLMS_H);
		clickElement(Announcement_c);
		WaitElementVisible(AddAnouncement_w_c);
		clickElement(AddAnouncement_w_c);
		clickElement(SelectField_c_D);
		Thread.sleep(2000);
        Actions action = new Actions(getDriver()); 
        action.keyDown(Keys.ENTER);
        action.build().perform();	
		FieldValue(Title_F, "English Quiz");
		
		clickElement(Content_c_f);
		Thread.sleep(2000);
		action.sendKeys("English Quiz");
		action.keyUp(Keys.CONTROL);
		action.build().perform();
		
		takeScreenshot("Announcement Formation");
		Thread.sleep(2000);
		ScrollDown();
		clickElement(CreateAnnouncement);
		Thread.sleep(2000);
		takeScreenshot("Announcement created");
	}
}
```
testSuite[Run as TestNG]
```ruby
package TestCases;
import org.testng.annotations.Test;
import Browser.BrowserSetup;
import Utilites.AnnouncementTool;
import Utilites.WordpressLogin;

public class AnnouncementValidation extends BrowserSetup{
	WordPressLoginToDashbord wplogin = new WordPressLoginToDashbord();
	AnnouncementTool ann = new AnnouncementTool();
	WordpressLogin wplog = new WordpressLogin();
	@Test(description = "Scenario 03"
			+ "11. From Announcements - Add new Announcement form validation")
	public void AddAnn() throws InterruptedException{
		wplogin.WPlogin();
		ann.createAnnouncement();
		Thread.sleep(10000);
		wplog.logout();
	}
}
```
Test Run Result:  
![s4Announcement](https://github.com/Sakif1997/WordPressPlugin_LMS_POM_Testing_Selenium/assets/45315685/44a3b1e1-60e9-4ed8-a585-e1778d300f5a)

#### Replicate Scenario 03:   12. From Webhooks -Add new Wehbook form validation 
To replicate those scenario: Fields xpath:  
![04](https://github.com/Sakif1997/WordPressPlugin_LMS_POM_Testing_Selenium/assets/45315685/f4ad6ae4-9aec-47c5-8c66-ee2483f1e530)  
The CreateWebhook method is the primary functionality of this class. It performs the following steps:  
* Hovers over the LMS menu.  
* Clicks on the "Webhooks" option.  
* Clicks on "Add New Webhook."  
* Waits for the webhook title field to be visible.  
* Enters a title for the webhook.  
* Selects stats and events options using the Actions class.  
* Enters a URL for the webhook.  
* Scrolls down.  
* Takes a screenshot of the webhook form.  
* Clicks the "Add Webhook" button.  
* Waits for the confirmation of the webhook being added.  

Video of execution:
https://drive.google.com/file/d/1atBEunpKm0NKjLzhORfr1XD7zKnafNLy/view?usp=drive_link
Add quiz functionality class:
```ruby
package Utilites;
import static Browser.BrowserSetup.getDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;

public class WehbookTool extends Methods{
	public By MenuLMS_H = By.xpath("//div[@class='wp-menu-image svg']");
	public By Wehbook_wc =By.xpath("//a[normalize-space()='Webhooks']");
	public By AddWehbook_w_c=By.xpath("//span[normalize-space()='Add New Webhook']");
	public By WehbookTitle_w_f=By.xpath("//input[@placeholder='Add hooks title here']");
	public By WehbookStats_c_enterkey =By.xpath("//div[@class='academy-react-select__value-container academy-react-select__value-container--has-value css-hlgwow']//div[@class='academy-react-select__input-container css-19bb58m']");
	public By Events=By.xpath("//div[@class='academy-react-select__value-container academy-react-select__value-container--is-multi css-hlgwow']//div[@class='academy-react-select__input-container css-19bb58m']");
	public By Url_f=By.xpath("//div[@class='academy-hook-form__delivery']//div[@class='form-body']");
	public By addWehbookButton=By.xpath("//span[normalize-space()='Add Webhook']");
	public By addedtovisiblepage_w=By.xpath("//h3[normalize-space()='Webhooks']");

	public void CreateWehbook() throws InterruptedException{
		Hover(MenuLMS_H);
		clickWaitElement(Wehbook_wc);
		clickWaitElement(AddWehbook_w_c);
		WaitElementVisible(WehbookTitle_w_f);
		FieldValue(WehbookTitle_w_f, "English Literature");
		clickElement(WehbookStats_c_enterkey);
        Actions action = new Actions(getDriver()); 
		action.keyDown(Keys.ARROW_DOWN);
		action.keyUp(Keys.CONTROL);
		action.keyDown(Keys.ENTER);
		action.build().perform();
		Thread.sleep(4000);
		clickElement(Events);
		action.keyDown(Keys.ENTER);
	    action.build().perform();
	    clickElement(Url_f);
		action.sendKeys("https://ebook.com.bd/storage/books/March2021/kFR3wJVHgz4rTxOOFRbL.pdf");
		action.keyUp(Keys.CONTROL);
		action.build().perform();
	    Thread.sleep(4000);
	    ScrollDown();
	    takeScreenshot("ebook form");
	    clickElement(addWehbookButton);
	    WaitElementVisible(addedtovisiblepage_w);
	}
}
```
testSuite[Run as TestNG]
```ruby
package TestCases;
import org.testng.annotations.Test;
import Browser.BrowserSetup;
import Utilites.WehbookTool;
import Utilites.WordpressLogin;

public class AddWehbookValidation extends BrowserSetup{
	WehbookTool weh = new WehbookTool();
	WordpressLogin wplog = new WordpressLogin();
	WordPressLoginToDashbord wplogin = new WordPressLoginToDashbord();
	@Test(description = "Scenario03:"
			+ "12. From Webhooks -Add new Wehbook form validation\r\n"
			+ "")
	public void wehbookpublish() throws InterruptedException{
		wplogin.WPlogin();
		weh.CreateWehbook();
		Thread.sleep(10000);
		wplog.logout();
	}
}
```
Test Run Result:
![Wehbook](https://github.com/Sakif1997/WordPressPlugin_LMS_POM_Testing_Selenium/assets/45315685/8d3da97d-9f5a-4b4f-83aa-dcf3e15f8d24)


## Automation of Scenario 1 to 12 Togather

Execution video for reference:
https://drive.google.com/file/d/1I3m3x_QYwvMZND0W-3VaBbodxcVRiJli/view?usp=drive_link
To run the whole test scenario togather:    
By convert the whole test run files to TestNG  
-instruction image     
![cnvt](https://github.com/Sakif1997/Daraz-AutomationTesting-PageObjectModeling/assets/45315685/c4c7161e-1404-4e97-ac12-e3f657f2d92c)  

Now you can ovserve a Testng.xml file under pom.xml file and run that file to see whole testing procedure togather  
![allureTestingxml](https://github.com/Sakif1997/WordPressPlugin_LMS_POM_Testing_Selenium/assets/45315685/46b4c2ab-0166-4e84-9435-e898bfe3ce01)


## Allure Report Creation

To create an allure report, 
first set dependency in the pom.xml file.
<dependency>
		<groupId>io.qameta.allure</groupId>
		<artifactId>allure-testng</artifactId>
		<version>2.19.0</version>
</dependency>
2. then run the testing.xml file   
3. then refresh the whole package and see a "allure-results" file created under Maven Dependencies  
-after runing the testng.xml file and refresh the whole package allure reasult appear  
4. To get allure report open the whole package terminal    
5. then write in terminal to clean previous files>  
```ruby
allure generate ./allure-result --clean  
```  
6. then write in terminal to create allure report>  
```ruby
 allure open ./allure-report  
 ```

7. terminal gives us http to show us an allure report file directory
Terminal image(5,6,7):  
![allure report](https://github.com/Sakif1997/WordPressPlugin_LMS_POM_Testing_Selenium/assets/45315685/f122acb4-8b89-4011-97d4-83774b1fadb1)


8. Create some methods for allure report (like allure ScreenShot) which is added already
9. method add:
```ruby
public void takeScreenshot(String name) {
		Allure.addAttachment(name, new ByteArrayInputStream(((TakesScreenshot)getDriver()).getScreenshotAs(OutputType.BYTES)));
	}
```
### Allure report file
Allure report Visualization:  
https://drive.google.com/file/d/1wWFWFGPFUHatwMov6mpZ5hNzXYcTI9hp/view?usp=drive_link  

report overview:  
![Allur1](https://github.com/Sakif1997/WordPressPlugin_LMS_POM_Testing_Selenium/assets/45315685/15c8d8ce-34f1-4ddd-bbf5-4e5d2682b984)
  
![Allur1](https://github.com/Sakif1997/WordPressPlugin_LMS_POM_Testing_Selenium/assets/45315685/15c8d8ce-34f1-4ddd-bbf5-4e5d2682b984)  

![allur3](https://github.com/Sakif1997/WordPressPlugin_LMS_POM_Testing_Selenium/assets/45315685/ad759d91-93bd-479b-97c5-f73b281cd6ba)
  
  
Scenario 01:  
![AllurS1](https://github.com/Sakif1997/WordPressPlugin_LMS_POM_Testing_Selenium/assets/45315685/4410cfe1-588e-4dad-b7b1-c1f0f0307fd1)

Scenario 02:  
![AllurS2](https://github.com/Sakif1997/WordPressPlugin_LMS_POM_Testing_Selenium/assets/45315685/b60c2534-ea32-4f24-a1e0-a4dfabeb1879)

Scenario 03:
![AllurS4](https://github.com/Sakif1997/WordPressPlugin_LMS_POM_Testing_Selenium/assets/45315685/33fd17c9-3e37-4f3f-b070-768c716c4756)

![AllureS5](https://github.com/Sakif1997/WordPressPlugin_LMS_POM_Testing_Selenium/assets/45315685/1487a3c4-f2dc-4929-8fe2-16f1acfbcabf)  

![AllurS6](https://github.com/Sakif1997/WordPressPlugin_LMS_POM_Testing_Selenium/assets/45315685/3e92f2ec-719b-4c83-8c5f-fe7e6e7a67bb)
