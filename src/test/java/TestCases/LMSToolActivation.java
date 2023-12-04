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
