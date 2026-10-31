import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pages.RegisterPage;
import utils.ApiCreate;

import static org.junit.jupiter.api.Assertions.assertTrue;

@Epic("Тестирование создания профиля")
@Feature("Регистрация пользователя")

public class CreateProfileTest extends BaseTest {
    private String createdEmail;
    private final String password = "123456!";



    @Story("Успешная регистрация")
    @Description("Проверка успешной регистрации")
    @Test
    public void testRegisterSuccessful() {
        driver.get(BaseTest.BASE_URL + "/register");
        createdEmail = "upiter" + System.currentTimeMillis() + "@mail.ru";

        new RegisterPage(driver)
                .enterName("Чарли")
                .enterEmail(createdEmail)
                .enterPassword(password)
                .clickRegisterButton();

        wait.until(ExpectedConditions.urlContains("/login"));
        assertTrue(driver.getCurrentUrl().contains("/login"));
        assertTrue(new RegisterPage(driver).isLoginButtonDisplayed());
    }

    @Story("Неспешная регистрация")
    @Description("Проверка неуспешной регистрации - неверный пароль")
    @Test
    public void testRegisterUnsuccessful() {
        driver.get(BASE_URL + "/register");
        createdEmail = "upiter" + System.currentTimeMillis() + "@mail.ru";

        new RegisterPage(driver)
                .enterName("Чарли")
                .enterEmail(createdEmail)
                .enterPassword("12345")
                .clickRegisterButton();

        wait.until(ExpectedConditions.urlContains("/register"));

        assertTrue(new RegisterPage(driver).isRegisterButtonDisplayed());
    }
    @AfterEach
    public void tearDownAuth() {
        if (createdEmail != null) {
            ApiCreate.deleteUser(createdEmail, password);
        }
    }
}
