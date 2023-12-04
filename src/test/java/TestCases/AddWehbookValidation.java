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
