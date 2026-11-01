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
import pages.ProfilePage;
import utils.ApiCreate;

import static org.junit.jupiter.api.Assertions.assertTrue;


@Epic("Тестирование ЛК")
@Feature("Проверка действий в ЛК")
public class ProfileTest extends BaseTest {

    private String uniqueEmail;
    private final String password = "123456!";

    @BeforeEach
    public void setUpProfile() {
        uniqueEmail = "stiven" + System.currentTimeMillis() + "@mail.ru";
        ApiCreate.createUser("Stiven", uniqueEmail, password);

        driver.get(BaseTest.BASE_URL + "/login");
        new LoginPage(driver)
                .enterEmail(uniqueEmail)
                .enterPassword(password)
                .clickLoginButton();

        wait.until(ExpectedConditions.not(ExpectedConditions.urlContains("/login")));
        wait.until(ExpectedConditions.urlContains("/"));
    }

    @Story("Переход в ЛК")
    @Description("Проверка перехода в ЛК")
    @Test
    public void testTransitionToProfile() {
        new MainPage(driver).clickProfile();
        wait.until(ExpectedConditions.urlContains("/profile"));

        assertTrue(new ProfilePage(driver).isProfileLoaded());
    }

    @Story("Переход в Конструктор из ЛК")
    @Description("Проверка перехода в Конструктор из ЛК")
    @Test
    public void testTransitionToConstructor() {
        new MainPage(driver).clickProfile();
        wait.until(ExpectedConditions.urlContains("/profile"));

        new ProfilePage(driver).clickConstructor();

        wait.until(ExpectedConditions.urlToBe(BaseTest.BASE_URL + "/"));
        assertTrue(new MainPage(driver).isOrderButtonDisplayed());
    }

    @Story("Переход по логотипу из ЛК")
    @Description("Проверка перехода в Конструктор по логотипу из ЛК")
    @Test
    public void testTransitionToLogoStellarBurgers() {
        new MainPage(driver).clickProfile();
        wait.until(ExpectedConditions.urlContains("/profile"));

        new ProfilePage(driver).clickLogo();

        wait.until(ExpectedConditions.urlToBe(BaseTest.BASE_URL + "/"));
        assertTrue(new MainPage(driver).isOrderButtonDisplayed());
    }


    @Story("Выход из ЛК")
    @Description("Проверка выхода из ЛК")
    @Test
    public void testLogOut() {
        new MainPage(driver).clickProfile();
        wait.until(ExpectedConditions.urlContains("/profile"));

        new ProfilePage(driver).clickLogout();
        wait.until(ExpectedConditions.urlToBe(BaseTest.BASE_URL + "/login"));
        assertTrue(new LoginPage(driver).isLoginButtonDisplayed());
    }

    @AfterEach
    public void tearDownAuth() {
        if (uniqueEmail != null) {
            ApiCreate.deleteUser(uniqueEmail, password);
        }
    }
}
