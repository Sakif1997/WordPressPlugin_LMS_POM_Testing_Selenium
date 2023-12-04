package TestCases;

import org.testng.annotations.Test;

import Browser.BrowserSetup;
import Utilites.AddCourses;
import Utilites.WordpressLogin;

public class CustomAddNewCourse extends BrowserSetup{
	AddCourses add = new AddCourses();
	WordPressLoginToDashbord wplogin = new WordPressLoginToDashbord();
	WordpressLogin wplog = new WordpressLogin();
	@Test(description = "Scenario 03:\r\n"
			+ "Courses Menu Validation\r\n"
			+ "9. From All Courses - Add new Course form validaton")
	public void NewCourse() throws InterruptedException{
		wplogin.WPlogin();
		add.addCourse();
		Thread.sleep(10000);
		wplog.logout();

	}

}
