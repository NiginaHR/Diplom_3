import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import pages.MainPage;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Epic("Тестирование конструктора")
@Feature("Переключение вкладок")
public class ConstructorTest extends BaseTest {

    private MainPage mainPage;

    @BeforeEach
    public void setUpConstructor() {
        driver.get(BaseTest.BASE_URL);

        mainPage = new MainPage(driver);
    }
    @Story("Проверка вкладки 'Начинки'")
    @Description("Проверка перехода на вкладку 'Начинки'")
    @Test
    public void testFillingsSection() {
        mainPage.clickFillingsTab();

        assertTrue(mainPage.isFillingsSectionVisible());
    }
    @Story("Проверка вкладки 'Соусы'")
    @Description("Проверка перехода на вкладку 'Соусы'")
    @Test
    public void testSaucesSection() {
        mainPage.clickSaucesTab();

        assertTrue(mainPage.isSaucesSectionVisible());
    }
    @Story("Проверка вкладки 'Булки'")
    @Description("Проверка перехода на вкладку 'Булки'")
    @Test
    public void testBunsSection() {
        mainPage.clickSaucesTab();

        assertTrue(mainPage.isSaucesSectionVisible());

        mainPage.clickBunsTab();
        assertTrue(mainPage.isBunsSectionVisible());
    }
}