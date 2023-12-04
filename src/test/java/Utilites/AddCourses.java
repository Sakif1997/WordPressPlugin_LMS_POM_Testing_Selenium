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
