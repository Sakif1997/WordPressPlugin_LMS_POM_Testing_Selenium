package TestCases;

import org.testng.annotations.Test;

import Browser.BrowserSetup;
import Utilites.WordpressLogin;

public class WordPressLoginToDashbord extends BrowserSetup{
	WordpressLogin login = new WordpressLogin();
	@Test
	public void WPlogin() throws InterruptedException{
		getDriver().get("http://localhost/wordpress-6.3.1/wordpress/wp-admin");
		Thread.sleep(2000);
		login.WordPressLogin();
	}
}
