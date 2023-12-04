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
