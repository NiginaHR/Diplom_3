import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.ApiCreate;

import java.time.Duration;

public class BaseTest {
    protected WebDriver driver;
    protected WebDriverWait wait;
    protected String uniqueEmail;
    protected String password;

    @BeforeEach
    public void setUp() {
        String browser = System.getProperty("browser", "chrome");

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--window-size=1920,1080");

        if ("yandex".equalsIgnoreCase(browser)) {
            options.setBinary("C:\\Program Files\\Yandex\\YandexBrowser\\Application\\browser.exe");
        }

        this.driver = new ChromeDriver(options);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        this.uniqueEmail = "stiven" + System.currentTimeMillis() + "@mail.ru";
        this.password = "123456!";

        ApiCreate.createUser("Stiven", uniqueEmail, password);
    }

    @AfterEach
    public void tearDown() {
        ApiCreate.deleteUser(uniqueEmail, password);
        driver.quit();
    }
}