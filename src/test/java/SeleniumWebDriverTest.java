import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import pages.AutomationPracticeMainPage;
import pages.AutomationPracticeRegistrationPage;
import pages.AutomationPracticeSignInPage;
import pages.AutomationPracticeStorePage;

import java.util.Random;


public class SeleniumWebDriverTest {
    private WebDriver driver;
    private AutomationPracticeMainPage appmp;
    private AutomationPracticeSignInPage appsip;
    private AutomationPracticeRegistrationPage aprp;
    private AutomationPracticeStorePage apsp;
    private String email = String.format("Krzysztof%d@ipf.net", new Random().nextInt(100000)+1);

    @BeforeMethod
    public void before (){
        System.out.println("Selenium tests:");
        driver = MyDriver.getWebDriver();
        appmp = new AutomationPracticeMainPage(driver);
        appsip = new AutomationPracticeSignInPage(driver);
        aprp = new AutomationPracticeRegistrationPage(driver);
        apsp = new AutomationPracticeStorePage(driver);
        driver.get("http://automationpractice.com/index.php");
    }

    @Test (priority = 1)
    public void scenariusz1 (){
        appmp.clickSignIn();
        String[] invalidEmails = new String[]{"", "testowymail@ipf", "testowymail.ipf", "testowy@mail@ipf.com",
                " @ipf.com"} ;
        appsip.validateEmailAddress(invalidEmails);
        appsip.clickCreateAnAccount(email);
        aprp.fillPersonalInfo(true,"Krzysztof", "Zerman", "zaq1@WSX", "11",
                "12", "1980");
        aprp.fillAddress("E 38th st", "155",
                "Miami", "Florida","33140","123123123", "Home");
        aprp.clickRegisterButton();
    }

    @Test (priority = 2)
    public void scenariusz2 (){
        // I just check here if user can login with passes from registration
        appmp.clickSignIn();
        appsip.loginIntoService(email, "zaq1@WSX");
    }
    @Test (priority = 3)
    public void scenariusz3 (){
        appmp.clickSignIn();
        appsip.loginIntoService(email, "zaq1@WSX");
        apsp.clickTshirts();
        apsp.addFirstProductToBusket("3", "L");
    }

    @AfterMethod
    public void after(){
        driver.quit();
    }
}
