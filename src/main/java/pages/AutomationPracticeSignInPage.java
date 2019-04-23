package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AutomationPracticeSignInPage {
    @FindBy(id = "email_create")
    private WebElement emailField;

    @FindBy(id = "SubmitCreate")
    private WebElement createAnAccountBtn;

    @FindBy(id = "create_account_error")
    private WebElement errorAlert;

    @FindBy(xpath = "//h3[@class='page-subheading' and contains(text(), 'Your personal information')]")
    private WebElement ifRegScreen;

    @FindBy(id = "email")
    private WebElement loginEmailField;

    @FindBy(id = "passwd")
    private WebElement loginPasswordField;

    @FindBy(id = "SubmitLogin")
    private WebElement signInBtn;

    @FindBy (className = "info-account")
    private WebElement infoAccount;


    private WebDriver driver;

    public AutomationPracticeSignInPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void validateEmailAddress(String [] invalidEmails){
        for (String x: invalidEmails) {
            emailField.clear();
            emailField.sendKeys(x);
            createAnAccountBtn.click();
            // Necessary cause alert is displayed for a moment after input new email and confirm
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            waitTillPresent(By.id("create_account_error"));
        }
    }
    public void clickCreateAnAccount(String email){
        emailField.clear();
        emailField.sendKeys(email);
        createAnAccountBtn.click();
        waitTillPresent(By.xpath("//h3[@class='page-subheading' and contains(text(), 'Your personal information')]"));
    }

    private void waitTillPresent(By by){
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.presenceOfElementLocated(by));
    }

    public void loginIntoService(String email,String password){
        loginEmailField.sendKeys(email);
        loginPasswordField.sendKeys(password);
        signInBtn.click();
        Assert.assertEquals(infoAccount.getText(),
                "Welcome to your account. Here you can manage all of your personal information and orders.");
    }
}
