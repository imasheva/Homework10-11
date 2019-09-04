import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.asserts.SoftAssert;

import java.lang.reflect.Method;

public class BaseUICrossBrowser {

    static WebDriver driver;
    WebDriverWait wait; //we don't use static permission
    IronSpiderPage ironSpiderPage; //
   static SoftAssert softAssert;

    @BeforeMethod
    @Parameters("browser")
    public void setup(@Optional("chrome") String browser, Method method)throws Exception {

        if(browser.equalsIgnoreCase("firefox")){
            System.setProperty("webdriver.gecko.driver", "geckodriver");
            driver = new FirefoxDriver();
        }
        else if(browser.equalsIgnoreCase("chrome")){
            System.setProperty("webdriver.chrome.driver", "chromedriver");
            driver = new ChromeDriver();
            driver.get("chrome://settings/clearBrowserData");
        }
        else if(browser.equalsIgnoreCase("IE")){
            System.setProperty("webdriver.ie.driver", "IEDriverServe");
            driver = new InternetExplorerDriver();
            driver.manage().deleteAllCookies();
        }
        else{
            System.setProperty("webdriver.chrome.driver", "chromedriver");
            driver = new ChromeDriver();
            driver.get("chrome://settings/clearBrowserData");
        }

        wait = new WebDriverWait(driver, 10);
        ironSpiderPage = new IronSpiderPage(driver, wait);   // --->> Initialize after drivers/ driver-chrome, wait
        driver.manage().window().maximize();
        driver.get(Data.mainUrl);
    }
    public static void checkAssertExpectedUrl(String expectedUrl) {
        String findedUrl = driver.getCurrentUrl();
        Assert.assertEquals(findedUrl, expectedUrl);
    }
    //Soft Assert *New add into test
    public static void checkSoftAssertEquals(String currentUrl, String expectedUrl){
        softAssert.assertEquals(currentUrl, expectedUrl);
    }

    public static void checkAssertTitle(String expectedTitle) {
        String findedTitle = driver.getTitle();
        Assert.assertEquals(findedTitle, expectedTitle);
    }

//Universalnyi checker words dlya webelementa

    public static boolean checkIsContains(WebElement webElement, String words) {
        if (webElement.getText().contains(words)) {
            return true;
        }

        Assert.fail("Can't find " + words);
        return false;
    }

    public static boolean checkIsDisplayed(WebElement webElement) {
        if (webElement.isDisplayed()) {
            return true;
        }
        Assert.fail("Can't find ");
        return false;
    }

    public boolean checkIsSelected(WebElement webElement) {

        if (webElement.isSelected()) {
            System.out.println(webElement.getText() + " already Selected");
            return false;
        } else {
            return true;
        }
    }

    @AfterMethod
    public void afterActions() {
        driver.quit();
    }

}
