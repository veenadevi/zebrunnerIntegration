import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.*;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class LTPlayGround_InputForm_TestClass {


    public RemoteWebDriver driver = null;


    String username = System.getenv("LT_USERNAME") == null ? "" : System.getenv("LT_USERNAME");
    String authkey = System.getenv("LT_ACCESS_KEY") == null ? "" : System.getenv("LT_ACCESS_KEY");
    String hub = "@hub.lambdatest.com/wd/hub";


    @BeforeTest
    public void setUp() throws Exception {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("build", "Demo_Build_2");
        capabilities.setCapability("name", "LTPlayGround_InputForm");
        capabilities.setCapability("platform", "MacOS Monterey");
        capabilities.setCapability("browserName", "Safari");
        capabilities.setCapability("version","15.0");
        capabilities.setCapability("console","error");

        try {
          driver = new RemoteWebDriver(new URL("https://" + username + ":" + authkey + hub), capabilities);
//           WebDriverManager.chromedriver().setup();
//            driver=new ChromeDriver();
//
//            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        }
//        catch (MalformedURLException e) {
//            Assert.assertTrue(false,e.getMessage());
//            System.out.println("Incorrect grid URL");
//        }
        catch ( Exception e) {
            Assert.assertTrue(false,e.getMessage());
           e.printStackTrace();
        }

    }
    @Test(enabled = true)
    public void testInputFormSubmit() throws Exception {

            try {
                Reporter.log("launching testSampleFormDemo");
                driver.get("https://www.lambdatest.com/selenium-playground/input-form-demo");
                driver.findElement(By.id("name")).sendKeys("LTUser");

                driver.findElement(By.id("company")).sendKeys("LT");
                
                driver.findElement(By.id("inputEmail4")).sendKeys("LTUser@lambdatest.com");
                
                driver.findElement(By.id("inputPassword4")).sendKeys("1234");
                driver.findElement(By.id("websitename")).sendKeys("https://www.lambdatest.com/");
                Select selectCountry = new Select(driver.findElement(By.name("country")));
                selectCountry.selectByValue("US");

                driver.findElement(By.id("inputCity")).sendKeys("New York");

                driver.findElement(By.id("inputState")).sendKeys("New York");


                driver.findElement(By.id("inputAddress1")).sendKeys("StreetAddress");
                driver.findElement(By.id("inputAddress2")).sendKeys("StreetAddress2");
                
                driver.findElement(By.id("inputZip")).sendKeys("100052");
                driver.findElement(By.cssSelector("button[type='submit']")).click();

                Thread.sleep(2000);

                String successMessage = driver.findElement(By.className("success-msg hidden")).getText();

                System.out.println("successMessage  "+successMessage);
                
                Assert.assertEquals(successMessage, "Thanks for contacting us, we will get back to you shortly.", "Invalid Message");
               

                Reporter.log("testSampleFormDemo TestCase Completed");

            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

    @AfterMethod
    public void teardown(ITestResult result){
        if(result.isSuccess()){
            ((JavascriptExecutor) driver).executeScript("lambda-status=passed");
        }
       else{
            ((JavascriptExecutor) driver).executeScript("lambda-status=failed");
        }
     driver.quit();

    }
    }
