package TestCases;

import org.testng.annotations.Test;

import Browser.BrowserSetup;
import Utilites.QuizTool;
import Utilites.WordpressLogin;

public class AddQuizValidation extends BrowserSetup{
	WordPressLoginToDashbord wplogin = new WordPressLoginToDashbord();
	WordpressLogin wplog = new WordpressLogin();
	QuizTool qz =new QuizTool();
	@Test(description = "Scenario 3"
			+ "10. From All Quiz - Add new Quiz form validation")
	public void CreateQuiz() throws InterruptedException{
		wplogin.WPlogin();
		qz.SetupQuiz();
		Thread.sleep(10000);
		wplog.logout();
	}

}
