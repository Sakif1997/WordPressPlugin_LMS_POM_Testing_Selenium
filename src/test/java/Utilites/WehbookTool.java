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
