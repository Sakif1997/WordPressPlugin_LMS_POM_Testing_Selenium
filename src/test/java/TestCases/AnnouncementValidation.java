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
