import org.junit.jupiter.api.Test;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pages.LoginPage;
import pages.MainPage;
import pages.PasswordRecoveryPage;
import pages.RegisterPage;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class AuthorizationTest extends BaseTest {

    @Test
    public void testAuthorizationThroughRegistration() {
        driver.get("https://qa-stellarburgers.education-services.ru/register");
        new RegisterPage(driver).clickLoginLinkOnRegisterPage();

        wait.until(ExpectedConditions.urlContains("/login"));
        new LoginPage(driver)
                .enterEmail(uniqueEmail)
                .enterPassword(password)
                .clickLoginButton();

        wait.until(ExpectedConditions.not(ExpectedConditions.urlContains("/login")));

        wait.until(ExpectedConditions.urlContains("/"));
        assertTrue(new MainPage(driver).isOrderButtonDisplayed());
    }

    @Test
    public void testAuthorizationThroughProfile() {
        driver.get("https://qa-stellarburgers.education-services.ru/");
        new MainPage(driver).clickProfile();

        wait.until(ExpectedConditions.urlContains("/login"));
        new LoginPage(driver)
                .enterEmail(uniqueEmail)
                .enterPassword(password)
                .clickLoginButton();
        wait.until(ExpectedConditions.not(ExpectedConditions.urlContains("/login")));

        wait.until(ExpectedConditions.urlContains("/"));
        assertTrue(new MainPage(driver).isOrderButtonDisplayed());
    }

    @Test
    public void testAuthorizationThroughMain() {
        driver.get("https://qa-stellarburgers.education-services.ru");
        new MainPage(driver).clickLogin();

        wait.until(ExpectedConditions.urlContains("/login"));

        new LoginPage(driver)
                .enterEmail(uniqueEmail)
                .enterPassword(password)
                .clickLoginButton();
        wait.until(ExpectedConditions.not(ExpectedConditions.urlContains("/login")));

        wait.until(ExpectedConditions.urlContains("/"));
        assertTrue(new MainPage(driver).isOrderButtonDisplayed());
    }

    @Test
    public void testAuthorizationThroughPasswordRecovery() {
        driver.get("https://qa-stellarburgers.education-services.ru/forgot-password");
        new PasswordRecoveryPage(driver).clickLogin();

        wait.until(ExpectedConditions.urlContains("/login"));

        new LoginPage(driver)
                .enterEmail(uniqueEmail)
                .enterPassword(password)
                .clickLoginButton();
        wait.until(ExpectedConditions.not(ExpectedConditions.urlContains("/login")));

        wait.until(ExpectedConditions.urlContains("/"));
        assertTrue(new MainPage(driver).isOrderButtonDisplayed());
    }
}