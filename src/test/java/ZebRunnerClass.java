

import org.testng.annotations.Test;
import java.net.MalformedURLException;
import java.net.URL;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.BeforeTest;

public class ZebRunnerClass {
    public RemoteWebDriver driver = null;
//    String username = "rishabhps";
//    String accessKey = "Qzln3pCCV7YvCYeQ7EmksXD0hSxR5STjBAK3Vh0gilJpIOllR1";

    String username = System.getenv("LT_USERNAME") == null ? "veenadevi" : System.getenv("LT_USERNAME");
    String authkey = System.getenv("LT_ACCESS_KEY") == null ? "I5ZgCAndy1vvQ1LGRjDl071RVaSt6y7nb4CMemPpu1FN8SjUb9" : System.getenv("LT_ACCESS_KEY");
    String hub = "@hub.lambdatest.com/wd/hub";
    @BeforeTest
    public void setUp() throws Exception {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("build", "your build name");
        capabilities.setCapability("name", "your test name");
        capabilities.setCapability("platform", "Windows 10");
        capabilities.setCapability("browserName", "Chrome");
        capabilities.setCapability("version","97.0");
       // String hubRUL="https://rishabhps:Qzln3pCCV7YvCYeQ7EmksXD0hSxR5STjBAK3Vh0gilJpIOllR1@hub.lambdatest.com/wd/hub";
        try {
            driver = new RemoteWebDriver(new URL("https://" + username + ":" + authkey + hub), capabilities);
            //driver = new RemoteWebDriver(new URL("https://rishabhps:Qzln3pCCV7YvCYeQ7EmksXD0hSxR5STjBAK3Vh0gilJpIOllR1@hub.lambdatest.com/wd/hub"), capabilities);
        } catch (MalformedURLException e) {
            System.out.println("Incorrect grid URL");
        }
    }
    @Test(enabled = true)
    public void testScript() throws Exception {
        try {
            driver.get("https://www.lambdatest.com");
            driver.findElement(By.xpath("//button[normalize-space()='Resources']")).click();
            driver.findElement(By.linkText("Blog")).click();
            System.out.println("blog page Title"+driver.getTitle());
            driver.quit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}