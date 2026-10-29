import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pages.LoginPage;
import pages.MainPage;
import pages.ProfilePage;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ProfileTest extends BaseTest {

    @BeforeEach
    public void setUpProfile() {
        driver.get("https://qa-stellarburgers.education-services.ru/login");
        new LoginPage(driver)
                .enterEmail(uniqueEmail)
                .enterPassword(password)
                .clickLoginButton();

        wait.until(ExpectedConditions.not(ExpectedConditions.urlContains("/login")));
        wait.until(ExpectedConditions.urlContains("/"));
    }

    @Test
    public void testTransitionToProfile() {
        new MainPage(driver).clickProfile();
        wait.until(ExpectedConditions.urlContains("/profile"));

        assertTrue(new ProfilePage(driver).isProfileLoaded());
    }

    @Test
    public void testTransitionToConstructor() {
        new MainPage(driver).clickProfile();
        wait.until(ExpectedConditions.urlContains("/profile"));

        new ProfilePage(driver).clickConstructor();

        wait.until(ExpectedConditions.urlToBe("https://qa-stellarburgers.education-services.ru/"));
        assertTrue(new MainPage(driver).isOrderButtonDisplayed());
    }

    @Test
    public void testTransitionToLogoStellarBurgers() {
        new MainPage(driver).clickProfile();
        wait.until(ExpectedConditions.urlContains("/profile"));

        new ProfilePage(driver).clickLogo();

        wait.until(ExpectedConditions.urlToBe("https://qa-stellarburgers.education-services.ru/"));
        assertTrue(new MainPage(driver).isOrderButtonDisplayed());
    }

    @Test
    public void testLogOut() {
        new MainPage(driver).clickProfile();
        wait.until(ExpectedConditions.urlContains("/profile"));

        new ProfilePage(driver).clickLogout();
        wait.until(ExpectedConditions.urlToBe("https://qa-stellarburgers.education-services.ru/login"));
    }
}