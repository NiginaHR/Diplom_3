import org.junit.jupiter.api.Test;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pages.RegisterPage;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CreateProfileTest extends BaseTest {

    @Test
    public void testRegisterSuccessful() {
        driver.get("https://qa-stellarburgers.education-services.ru/register");
        String email = "upiter" + System.currentTimeMillis() + "@mail.ru";

        new RegisterPage(driver)
                .enterName("Чарли")
                .enterEmail(email)
                .enterPassword("123456!")
                .clickRegisterButton();

        wait.until(ExpectedConditions.not(ExpectedConditions.urlContains("/register")));
    }

    @Test
    public void testRegisterUnsuccessful() {
        driver.get("https://qa-stellarburgers.education-services.ru/register");
        String email = "upiter" + System.currentTimeMillis() + "@mail.ru";

        new RegisterPage(driver)
                .enterName("Чарли")
                .enterEmail(email)
                .enterPassword("12345")
                .clickRegisterButton();
        wait.until(ExpectedConditions.urlContains("/register"));

        assertTrue(driver.getCurrentUrl().contains("/register"));
    }
}