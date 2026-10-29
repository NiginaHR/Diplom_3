import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pages.LoginPage;
import pages.MainPage;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ConstructorTest extends BaseTest {

    @BeforeEach
    public void setUpConstructor() {
        driver.get("https://qa-stellarburgers.education-services.ru/login");
        new LoginPage(driver)
                .enterEmail(uniqueEmail)
                .enterPassword(password)
                .clickLoginButton();

        wait.until(ExpectedConditions.not(ExpectedConditions.urlContains("/login")));
        wait.until(ExpectedConditions.urlContains("/"));
    }

    @Test
    public void testFillingsSection() {
        MainPage mainPage = new MainPage(driver);
        mainPage.clickFillingsTab();

        assertTrue(mainPage.isFillingsSectionVisible());
    }

    @Test
    public void testSaucesSection() {
        MainPage mainPage = new MainPage(driver);
        mainPage.clickSaucesTab();

        assertTrue(mainPage.isSaucesSectionVisible());
    }

    @Test
    public void testBunsSection() {
        MainPage mainPage = new MainPage(driver);
        mainPage.clickSaucesTab();

        assertTrue(mainPage.isSaucesSectionVisible());

        mainPage.clickBunsTab();
        assertTrue(mainPage.isBunsSectionVisible());
    }
}