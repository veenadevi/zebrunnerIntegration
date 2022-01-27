
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

import org.openqa.selenium.*;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class LTPlayGround_Checkbox_TestClass {
    public RemoteWebDriver driver = null;


    String username = System.getenv("LT_USERNAME") == null ? "" : System.getenv("LT_USERNAME");
    String authkey = System.getenv("LT_ACCESS_KEY") == null ? "" : System.getenv("LT_ACCESS_KEY");
    String hub = "@hub.lambdatest.com/wd/hub";
    @BeforeTest
    public void setUp() throws Exception {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("build", "ZebRunner_Demo_Build_1");
        capabilities.setCapability("name", "LTPlayGround_SimpleForm");
        capabilities.setCapability("platform", "Windows 11");
    	capabilities.setCapability("browserName", "Firefox");
    	capabilities.setCapability("version","95.0");
    	capabilities.setCapability("console","true");

        try {
            driver = new RemoteWebDriver(new URL("https://" + username + ":" + authkey + hub), capabilities);
            //WebDriverManager.chromedriver().setup();
            //driver=new ChromeDriver();

            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        }
        catch (MalformedURLException e) {
            System.out.println("Incorrect grid URL");
        }
        catch ( Exception e) {
            System.out.println("Incorrect grid URL");
        }

    }


    @Test(enabled = true)
    public void testCheckBoxTest() throws Exception {
        try {
            Reporter.log("launching testSampleFormDemo");
            driver.get("https://www.lambdatest.com/selenium-playground/checkbox-demo");
           
            File source = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
   	     	FileHandler.copy(source, new File("CheckBox LT_PlayGround"));
   	     	
   	     	WebElement checkBox=driver.findElement(By.id("isAgeSelected"));
   	     	Assert.assertTrue(checkBox.isSelected());

            Reporter.log("testCheckBoxTest TestCase Completed");

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }



    @AfterMethod
    public void teardown(ITestResult result ){
        if(result.isSuccess()){
            ((JavascriptExecutor) driver).executeScript("lambda-status=passed");
        }
        else{
            ((JavascriptExecutor) driver).executeScript("lambda-status=failed");
        }
        driver.quit();

    }
}