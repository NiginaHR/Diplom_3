package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class ProfilePage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By profileText = By.xpath("//*[contains(text(), 'Профиль')]");
    private final By constructorLink = By.xpath("//a[.//p[contains(text(), 'Конструктор')]]");
    private final By logoLink = By.xpath("//div[contains(@class, 'AppHeader_header__logo__2D0X2')]//a");
    private final By logoutButton = By.xpath("//button[contains(text(), 'Выход')]");

    public ProfilePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public boolean isProfileLoaded() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(profileText)).isDisplayed();
    }

    @Step("Нажатие кнопки 'Конструктор'")
    public void clickConstructor() {
        wait.until(ExpectedConditions.elementToBeClickable(constructorLink)).click();
    }

    @Step("Нажатие на логотип 'Stellar Burgers'")
    public void clickLogo() {
        wait.until(ExpectedConditions.elementToBeClickable(logoLink)).click();
    }

    @Step("Нажатие кнопки 'Выход'")
    public void clickLogout() {
        wait.until(ExpectedConditions.elementToBeClickable(logoutButton)).click();
    }
}