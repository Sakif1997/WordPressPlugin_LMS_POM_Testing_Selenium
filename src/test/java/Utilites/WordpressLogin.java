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
