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
