import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PruebasPhantomjsIT
{
    private static WebDriver driver=null;

    @Test
    public void tituloIndexTest()
    {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setJavascriptEnabled(true);
        caps.setCapability(PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY,"/usr/bin/phantomjs");
        caps.setCapability(PhantomJSDriverService.PHANTOMJS_CLI_ARGS, new String[] {"--web-security=no", "--ignore-ssl-errors=yes"});
        driver = new PhantomJSDriver(caps);
        driver.navigate().to("http://localhost:8080/Baloncesto/");
        assertEquals("Votacion mejor jugador liga ACB", driver.getTitle(), "El titulo no es correcto");
        System.out.println(driver.getTitle());
        driver.close();
        driver.quit();
    }

    @Test
  public void reiniciarVotos() {
    DesiredCapabilities caps = new DesiredCapabilities();
    caps.setJavascriptEnabled(true);
    caps.setCapability(PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY,"/usr/bin/phantomjs");
    caps.setCapability(PhantomJSDriverService.PHANTOMJS_CLI_ARGS, new String[] {"--web-security=no", "--ignore-ssl-errors=yes"});
    driver = new PhantomJSDriver(caps);
    driver.navigate().to("http://localhost:8080/Baloncesto/");
    driver.findElement(By.cssSelector("input:nth-child(3)")).click();
    driver.findElement(By.linkText("Ir al comienzo")).click();
    driver.findElement(By.cssSelector("input:nth-child(4)")).click();
    assertEquals("1", driver.findElement(By.xpath("//tr[2]/td[2]")).getText(), "Valor diferente de 0");
    assertEquals("1", driver.findElement(By.xpath("//tr[3]/td[2]")).getText(), "Valor diferente de 0");
    assertEquals("1", driver.findElement(By.xpath("//tr[4]/td[2]")).getText(), "Valor diferente de 0");
    assertEquals("1", driver.findElement(By.xpath("//tr[5]/td[2]")).getText(), "Valor diferente de 0");
    driver.close();
    driver.quit();  
   
  }
}
