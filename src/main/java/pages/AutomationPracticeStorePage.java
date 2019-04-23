package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.awt.*;
import java.util.List;

public class AutomationPracticeStorePage {
    @FindBy (xpath = "//ul[@class='sf-menu clearfix menu-content sf-js-enabled sf-arrows']/li[3]/a")
    private WebElement tshirtsBtn;

    @FindBy (xpath = "(//div[@class='product-image-container'])[1]")
    private WebElement firstProduct;

    @FindBy (xpath = "//li[contains(@id, 'thumbnail_')]")
    private List <WebElement> imagesToPreview;

    @FindBy (id = "bigpic")
    private WebElement mainImg;

    @FindBy (id = "quantity_wanted")
    private WebElement quanityField;

    @FindBy (id = "group_1")
    private WebElement sizeSelector;

    @FindBy (xpath = "//p[@id='add_to_cart']/button[@type='submit']")
    private WebElement confirmAddToCartBtn;

    @FindBy (id = "layer_cart_product_attributes")
    private WebElement checkSize;

    @FindBy (id = "layer_cart_product_quantity")
    private WebElement checkQuanity;

    @FindBy (xpath = "//a[@title='Proceed to checkout']")
    private WebElement proceedToCheckoutBtn;

    @FindBy (id = "cart_title")
    private WebElement cartTitle;

    @FindBy (xpath = "//iframe[@class='fancybox-iframe']")
    private WebElement iframe;

    private WebDriver driver;
    private WebDriverWait wait;
    public AutomationPracticeStorePage (WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, 10);
    }

    public void clickTshirts(){
        tshirtsBtn.click();
    }

    public void addFirstProductToBusket(String amount, String size){
        firstProduct.click();
        // Change frame
        driver.switchTo().frame(iframe);
        checkPreviewImages();
        quanityField.clear();
        quanityField.sendKeys(amount);
        Select select = new Select(sizeSelector);
        select.selectByVisibleText(size);
        confirmAddToCartBtn.click();
        // Back to default frame
        driver.switchTo().defaultContent();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("layer_cart_product_attributes")));
        wait.until(ExpectedConditions.visibilityOf(checkQuanity));
        Assert.assertTrue(checkSize.getText().contains(size));
        Assert.assertEquals(checkQuanity.getText(), amount);
        proceedToCheckoutBtn.click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("cart_title")));
        wait.until(ExpectedConditions.visibilityOf(cartTitle));
        Assert.assertTrue(cartTitle.getText().contains("SHOPPING-CART SUMMARY"));
    }

    private void checkPreviewImages(){
        wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(By.xpath("//img[contains(@id, 'thumb')]"), 1));
        // Robot here is needed to not disturb action below
        moveMouseFromTest();
        for (WebElement element: imagesToPreview) {
            String testOFImg = mainImg.getAttribute("src");
            Actions actions = new Actions(driver);
            actions.moveToElement(element).pause(4000).build().perform();
            Assert.assertNotEquals(testOFImg, mainImg.getAttribute("src"));
        }
    }

    private void moveMouseFromTest(){
        try {
            Robot robot = new Robot();
            robot.mouseMove(0,0);
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }

}
