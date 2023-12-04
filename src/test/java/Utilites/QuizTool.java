package Utilites;

import static Browser.BrowserSetup.getDriver;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;

public class QuizTool extends Methods{
	public By MenuLMS_H = By.xpath("//div[@class='wp-menu-image svg']");
	public By QuizTool_w_c= By.xpath("//a[normalize-space()='Quizzes']");
	public By AddnewQuiz_w_c= By.xpath("//span[normalize-space()='Add New Quiz']");
	public By QuizPage_w = By.xpath("//h3[normalize-space()='Quiz']");
	public By QuizTitle_f= By.xpath("//input[@id='title']");
	public By QuizSummary_f= By.xpath("//textarea[@id='content']");
	public By SaveNextButton_c= By.xpath("//span[normalize-space()='Save & Next']");
	public By AddQuestion_W_c= By.xpath("//span[normalize-space()='Add Question']");
	public By QuestionTitle_w_f= By.xpath("//input[@id='questionTitle']");
	public By AnswerTypeTrueFalse_c =By.xpath("//div[contains(@class,'academy-react-select__value-container academy-react-select__value-container--has-value css-hlgwow')]//div[contains(@class,'academy-react-select__input-container css-19bb58m')]");
	public By AnswerRadioButton_c = By.xpath("(//input[contains(@type,'radio')])[1]");
	public By AddQuestionButton_c = By.xpath("//button[@type='submit']");
	public By SaveAndNext = By.xpath("//button[contains(@class,'academy-btn academy-btn--md academy-btn--preset-purple academy-btn--border-rounded')]");
	public By HideOption_c= By.xpath("//div[@id='timelimit']//span[@class='academy-slider']");
	public By Finish_c= By.xpath("//button[contains(@class,'academy-btn academy-btn--md academy-btn--preset-purple academy-btn--border-rounded')]");
	
	public void SetupQuiz() throws InterruptedException{
		Hover(MenuLMS_H);
		WaitElementVisible(QuizTool_w_c);
		clickElement(QuizTool_w_c);
		WaitElementVisible(AddnewQuiz_w_c);
		clickElement(AddnewQuiz_w_c);
		Thread.sleep(2000);
		WaitElementVisible(QuizPage_w);
		FieldValue(QuizTitle_f, "Basic Questions");
		Thread.sleep(2000);
		FieldValue(QuizSummary_f, "General Knowledge");
		Thread.sleep(2000);
		takeScreenshot("Question Summary Page");
		clickElement(SaveNextButton_c);
		
		WaitElementVisible(AddQuestion_W_c);
		clickElement(AddQuestion_W_c);
		
		WaitElementVisible(QuestionTitle_w_f);
		FieldValue(QuestionTitle_w_f, "Software is easily maintainable if you form a good and professional QA team.");
		clickElement(AnswerTypeTrueFalse_c);
		Thread.sleep(2000);
		Actions action = new Actions(getDriver());
		action.keyDown(Keys.ARROW_DOWN);
		action.keyUp(Keys.CONTROL);
		action.keyDown(Keys.ENTER);
		action.build().perform();
		clickElement(AnswerTypeTrueFalse_c);
		action.keyDown(Keys.ARROW_UP);
		action.keyUp(Keys.CONTROL);
	    action.keyDown(Keys.ENTER);
	    action.build().perform();	
	    clickElement(AnswerTypeTrueFalse_c);
		Thread.sleep(2000);
	    action.keyDown(Keys.ENTER);
	    action.build().perform();	
	    
		clickWaitElement(AnswerRadioButton_c);
		takeScreenshot("Question Formation page");
		ScrollDown();
		clickElement(AddQuestionButton_c);
		Thread.sleep(4000);
		clickWaitElement(SaveAndNext);
		Thread.sleep(2000);
		
		clickWaitElement(HideOption_c);;
		clickElement(HideOption_c);
		clickElement(Finish_c);
		Thread.sleep(2000);
		takeScreenshot("Quiz Added");
	}
	
}
