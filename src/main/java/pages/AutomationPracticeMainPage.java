package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AutomationPracticeMainPage {
    @FindBy(className = "login")
    private WebElement signInBtn;

    private WebDriver driver;

    public AutomationPracticeMainPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void clickSignIn(){
        signInBtn.click();
    }

}
