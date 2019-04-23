import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class MyDriver {
    private static String OS = System.getProperty("os.name").toLowerCase();

    static public WebDriver getWebDriver(){
        String path = String.format("%s\\src\\main\\java\\ChromeDrivers\\", System.getProperty("user.dir"));
        // Check OS
        if (isWindows()) {
            path = path+"chromedriver.exe";
        } else if (isMac()) {
            path = path+"chromedriverMAC";
        } else if (isUnix()) {
            path = path+"chromedriver";
        } else {
            throw new Error("Unknow Operation System, pls use Windows, Linux or MacOS");
        }
        System.setProperty("webdriver.chrome.driver", path);

        // Create Chrome Driver
        ChromeDriver chromeDriver = new ChromeDriver();
        // Page will wait 10 sec until element will be present
        chromeDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        // Maximize window
        chromeDriver.manage().window().maximize();
        return chromeDriver;
    }

    private static boolean isWindows() {
        return (OS.contains("win"));
    }

    private static boolean isMac() {
        return (OS.contains("mac"));
    }

    private static boolean isUnix() {
        return (OS.contains("nix") || OS.contains("nux") || OS.contains("aix"));
    }
}
