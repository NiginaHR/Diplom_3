package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class MainPage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By loginButton = By.xpath("//button[contains(text(), 'Войти')] | //a[contains(text(), 'Войти')]");
    private final By orderButton = By.xpath("//button[contains(text(), 'Оформить заказ')]");
    private final By profileLink = By.xpath("//a[contains(@href, '/account')]");

    private final By bunsTab = By.xpath("//div[contains(@class, 'tab_tab__') and contains(., 'Булки')]");
    private final By fillingsTab = By.xpath("//div[contains(@class, 'tab_tab__') and contains(., 'Начинки')]");
    private final By saucesTab = By.xpath("//div[contains(@class, 'tab_tab__') and contains(., 'Соусы')]");

    private final By bunsSection = By.xpath("//h2[contains(text(), 'Булки')]");
    private final By fillingsSection = By.xpath("//h2[contains(text(), 'Начинки')]");
    private final By saucesSection = By.xpath("//h2[contains(text(), 'Соусы')]");

    public MainPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Step("Нажатие кнопки 'Войти'")
    public void clickLogin() {
        wait.until(ExpectedConditions.elementToBeClickable(loginButton)).click();
    }

    @Step("Нажатие кнопки 'Личный кабинет'")
    public void clickProfile() {
        wait.until(ExpectedConditions.elementToBeClickable(profileLink)).click();
    }

    @Step("Нажатие на вкладку 'Булки'")
    public void clickBunsTab() {
        wait.until(ExpectedConditions.elementToBeClickable(bunsTab)).click();
    }

    @Step("Нажатие на вкладку 'Начинки'")
    public void clickFillingsTab() {
        wait.until(ExpectedConditions.elementToBeClickable(fillingsTab)).click();
    }

    @Step("Нажатие на вкладку 'Соусы'")
    public void clickSaucesTab() {
        wait.until(ExpectedConditions.elementToBeClickable(saucesTab)).click();
    }

    public boolean isOrderButtonDisplayed() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(orderButton)).isDisplayed();
    }

    public boolean isBunsSectionVisible() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(bunsSection)).isDisplayed();
    }

    public boolean isFillingsSectionVisible() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(fillingsSection)).isDisplayed();
    }

    public boolean isSaucesSectionVisible() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(saucesSection)).isDisplayed();
    }
}