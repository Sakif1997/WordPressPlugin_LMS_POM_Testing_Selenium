package Utilites;

import org.openqa.selenium.By;

public class AddTool extends Methods{
	public By MenuLMS_H = By.xpath("//div[@class='wp-menu-image svg']");
	public By AddOns_w_c= By.xpath("//a[normalize-space()='Add-ons']");
	public By AddonsPage_w= By.xpath("//h3[normalize-space()='Add-ons']");
	public By QuizTool_c =By.xpath("//div[@class='academy-dashboard-addon-card academy-dashboard-addon-card--0']//span[@class='academy-slider']");
	public By MultiInstructor_c = By.xpath("//div[@class='academy-dashboard-addon-card academy-dashboard-addon-card--1']//span[@class='academy-slider']");
	public By MigrationTool_c =By.xpath("//div[@class='academy-dashboard-addon-card academy-dashboard-addon-card--14']//span[@class='academy-slider']");
	public By Wehbook_c = By.xpath("//div[@class='academy-dashboard-addon-card academy-dashboard-addon-card--15']//span[@class='academy-slider']");
	public void ToolsAdd() throws InterruptedException{
		Hover(MenuLMS_H);
		WaitElementVisible(AddOns_w_c);
		clickElement(AddOns_w_c);
		Thread.sleep(2000);
		WaitElementVisible(AddonsPage_w);
		Thread.sleep(2000);
		clickElement(QuizTool_c);
		Thread.sleep(2000);
		clickElement(MultiInstructor_c);
		Thread.sleep(2000);
		takeScreenshot("Free Tools addition");
		clickElement(MigrationTool_c);
		Thread.sleep(2000);
		clickElement(Wehbook_c);
		Thread.sleep(2000);
		takeScreenshot("Free Tools addition");
		ScrollUp();
		Thread.sleep(2000);


		
		
	}
}
