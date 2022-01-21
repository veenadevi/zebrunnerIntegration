
import org.testng.Assert;
import org.testng.annotations.Test;
import java.net.MalformedURLException;
import java.net.URL;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.BeforeTest;

public class DemoPlayGroundClass {
    public RemoteWebDriver driver = null;
//    String username = "";
//    String accessKey = "";

    String username = System.getenv("LT_USERNAME") == null ? "" : System.getenv("LT_USERNAME");
    String authkey = System.getenv("LT_ACCESS_KEY") == null ? "" : System.getenv("LT_ACCESS_KEY");
    String hub = "@hub.lambdatest.com/wd/hub";
    @BeforeTest
    public void setUp() throws Exception {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("build", "ZebRunner_Demo_Build");
        capabilities.setCapability("name", "SampleRun1");
        capabilities.setCapability("platform", "Windows 10");
        capabilities.setCapability("browserName", "Chrome");
        capabilities.setCapability("version","97.0");

        try {
            driver = new RemoteWebDriver(new URL("https://" + username + ":" + authkey + hub), capabilities);

        } catch (MalformedURLException e) {
            System.out.println("Incorrect grid URL");
        }
    }
    @Test(enabled = true)
    public void testScript() throws Exception {
        try {
            driver.get("https://www.lambdatest.com/selenium-playground/");
            driver.findElement(By.linkText("Simple Form Demo")).click();
            String userMessage="New Mesasge";
            driver.findElement(By.id("user-message")).sendKeys(userMessage);
            driver.findElement(By.id("showInput")).click();
            String printedText=driver.findElement(By.id("message")).getText();
            System.out.println("blog page Title"+driver.getTitle());
            Assert.assertTrue(userMessage==(printedText));
            driver.quit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}