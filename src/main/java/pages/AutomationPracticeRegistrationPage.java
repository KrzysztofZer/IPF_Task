package pages;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class AutomationPracticeRegistrationPage {
    @FindBy (id = "uniform-id_gender1")
    private WebElement manRadioButton;

    @FindBy (id = "uniform-id_gender2")
    private WebElement womanRadioButton;

    @FindBy (id = "customer_firstname")
    private WebElement firstNameField;

    @FindBy (id = "customer_lastname")
    private WebElement lastNameField;

    @FindBy (id = "passwd")
    private WebElement passwordField;

    @FindBy (id = "days")
    private WebElement birthDaySelector;

    @FindBy (id = "months")
    private WebElement birthMonthSelector;

    @FindBy (id = "years")
    private WebElement birthYearSelector;

    @FindBy (id = "address1")
    private WebElement firstAddressField;

    @FindBy (id = "address2")
    private WebElement secondAddressField;

    @FindBy (id = "city")
    private WebElement cityField;

    @FindBy (id = "id_state")
    private WebElement stateSelector;

    @FindBy (id = "postcode")
    private WebElement postCodeField;

    @FindBy (id = "phone_mobile")
    private WebElement mobilePhoneNumberField;

    @FindBy (id = "alias")
    private WebElement addressAliasField;

    @FindBy (id = "submitAccount")
    private WebElement registerBtn;

    @FindBy (className = "info-account")
    private WebElement infoAccount;


    private WebDriver driver;

    public AutomationPracticeRegistrationPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void fillPersonalInfo(boolean man, String firstName, String lastName, String password, String dayOfBirth,
                                 String monthOfBirth, String yearOfBirth){
        selectGender(man);
        firstNameField.sendKeys(firstName);
        lastNameField.sendKeys(lastName);
        passwordField.sendKeys(password);
        fillDateOfBirth(dayOfBirth, monthOfBirth, yearOfBirth);
    }

    private void fillDateOfBirth(String day, String month, String year) {
        Select select = new Select(birthDaySelector);
        select.selectByValue(day);
        select = new Select(birthMonthSelector);
        select.selectByValue(month);
        select = new Select(birthYearSelector);
        select.selectByValue(year);
    }

    public void fillAddress(String firstPartAddress, String secondPartAddress,
                            String city, String state, String zipCode, String mobilePhone, String addressAlias){
        firstAddressField.sendKeys(firstPartAddress);
        secondAddressField.sendKeys(secondPartAddress);
        cityField.sendKeys(city);
        Select select = new Select(stateSelector);
        select.selectByVisibleText(state);
        postCodeField.sendKeys(zipCode);
        mobilePhoneNumberField.sendKeys(mobilePhone);
        addressAliasField.clear();
        addressAliasField.sendKeys(addressAlias);
    }

    private void selectGender(boolean man){
        if (man)
            manRadioButton.click();
        else
            womanRadioButton.click();
    }

    public void clickRegisterButton(){
        registerBtn.click();
        Assert.assertEquals(infoAccount.getText(),
                "Welcome to your account. Here you can manage all of your personal information and orders.");
    }
}
