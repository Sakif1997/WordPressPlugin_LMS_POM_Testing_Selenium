package TestCases;

import org.testng.annotations.Test;

import Browser.BrowserSetup;
import Utilites.PluginInstallation;
import Utilites.WordpressLogin;

public class PluginActivation extends BrowserSetup{
	WordpressLogin login = new WordpressLogin();
	PluginInstallation plug = new PluginInstallation();
	WordpressLogin wplog = new WordpressLogin();

	@Test(description = "Scenario 01:\r\n"
			+ "1. Log in to your WordPress site.\r\n"
			+ "2. Check whether the “Academy LMS” Plugin is Active or not.\r\n"
			+ "3. If Active, navigate to the Plugin. Otherwise, Install the Plugin and Activate it. \r\n"
			+ "4. Validate whether the Academy LMS is visible or not on the Admin Dashboard menu.")
	public void InstallToActivate() throws InterruptedException{
		getDriver().get("http://localhost/wordpress-6.3.1/wordpress/wp-admin");
		Thread.sleep(2000);
		login.WordPressLogin();
		plug.Installation();
		Thread.sleep(2000);
		wplog.logout();
	}

}
