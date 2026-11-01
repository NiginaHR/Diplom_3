import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pages.LoginPage;
import pages.MainPage;
import pages.PasswordRecoveryPage;
import pages.RegisterPage;
import utils.ApiCreate;

import static org.junit.jupiter.api.Assertions.assertTrue;


@Epic("Тестирование страницы авторизации")
@Feature("Авторизация")
public class AuthorizationTest extends BaseTest {

    private String uniqueEmail;
    private final String password = "123456!";


    @BeforeEach
    public void setUpAuth() {
        uniqueEmail = "stiven" + System.currentTimeMillis() + "@mail.ru";
        ApiCreate.createUser("Stiven", uniqueEmail, password);
    }

    @Story("Авторизация через форму регистрации")
    @Description("Проверка авторизации через кнопку 'Войти'")
    @Test
    public void testAuthorizationThroughRegistration() {
        driver.get(BaseTest.BASE_URL + "/register");
        new RegisterPage(driver).clickLoginLinkOnRegisterPage();

        wait.until(ExpectedConditions.urlContains("/login"));
        new LoginPage(driver)
                .enterEmail(uniqueEmail)
                .enterPassword(password)
                .clickLoginButton();

        wait.until(ExpectedConditions.not(ExpectedConditions.urlContains("/login")));

        assertTrue(new MainPage(driver).isOrderButtonDisplayed());
    }

    @Story("Авторизация через форму ЛК")
    @Description("Проверка авторизации через форму ЛК'")
    @Test
    public void testAuthorizationThroughProfile() {
        driver.get(BaseTest.BASE_URL);
        new MainPage(driver).clickProfile();

        wait.until(ExpectedConditions.urlContains("/login"));
        new LoginPage(driver)
                .enterEmail(uniqueEmail)
                .enterPassword(password)
                .clickLoginButton();
        wait.until(ExpectedConditions.not(ExpectedConditions.urlContains("/login")));

        assertTrue(new MainPage(driver).isOrderButtonDisplayed());
    }

    @Story("Вход на главной странице")
    @Description("Проверка авторизации на главной странице'")
    @Test
    public void testAuthorizationThroughMain() {
        driver.get(BaseTest.BASE_URL);
        new MainPage(driver).clickLogin();

        wait.until(ExpectedConditions.urlContains("/login"));

        new LoginPage(driver)
                .enterEmail(uniqueEmail)
                .enterPassword(password)
                .clickLoginButton();
        wait.until(ExpectedConditions.not(ExpectedConditions.urlContains("/login")));

        assertTrue(new MainPage(driver).isOrderButtonDisplayed());
    }

    @Story("Авторизация через страницу восстановления пароля")
    @Description("Проверка авторизации страницу восстановления пароля")
    @Test
    public void testAuthorizationThroughPasswordRecovery() {
        driver.get(BaseTest.BASE_URL+ "/forgot-password");
        new PasswordRecoveryPage(driver).clickLogin();

        wait.until(ExpectedConditions.urlContains("/login"));

        new LoginPage(driver)
                .enterEmail(uniqueEmail)
                .enterPassword(password)
                .clickLoginButton();
        wait.until(ExpectedConditions.not(ExpectedConditions.urlContains("/login")));

        assertTrue(new MainPage(driver).isOrderButtonDisplayed());
    }

    @AfterEach
    public void tearDownAuth() {
        if (uniqueEmail != null) {
            ApiCreate.deleteUser(uniqueEmail, password);
        }
    }
}
