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
