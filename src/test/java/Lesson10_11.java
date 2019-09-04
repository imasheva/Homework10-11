import org.openqa.selenium.WebElement;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class Lesson10_11 extends ParentClassBaseUI {


    SoftAssert softAssert;

    @Test
    public void test1() {


       /* softAssert = new SoftAssert();
        softAssert.assertEquals("currentUrl", "expectedUrl");
*/

        Assert.assertEquals("currentUrl", "expectedUrl");

        WebElement element = driver.findElement(Locators.WEB_TEXT_FILEDS);
        boolean elementIsDisplayed = element.isDisplayed();

        Assert.assertTrue(elementIsDisplayed, "IS NOT displayed"); //true
        WebElement checkBox = driver.findElement(Locators.CHECK_BOX_YELLOW);//checkbox

        if(checkBox.isSelected()){
            element.click();
        }else {
            Assert.fail("IS NOT selected, can not go to next");
        }
    }
}





