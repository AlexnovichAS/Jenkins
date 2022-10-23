package sberbank.pages;

//import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.sberbank.managers.DriverManager;
import ru.sberbank.managers.PageManager;
import ru.sberbank.managers.TestPropManager;

import java.util.concurrent.TimeUnit;

import static ru.sberbank.utils.PropConst.*;

/**
 * Базовый класс всех страничек
 *
 * @author Алехнович Александр
 */

public class BasePage {

    /**
     * Менеджер WebDriver
     *
     * @author Алехнович Александр
     * @see DriverManager#getDriverManager()
     */
    protected final DriverManager driverManager = DriverManager.getDriverManager();

    /**
     * Менеджер страничек
     *
     * @author Алехнович Александр
     * @see PageManager
     */
    protected PageManager pageManager = PageManager.getPageManager();


    /**
     * Менеджер properties
     *
     * @author Алехнович Александр
     * @see TestPropManager#getTestPropManager()
     */
    private static final TestPropManager props = TestPropManager.getTestPropManager();


    /**
     * Объект для имитации реального поведения мыши или клавиатуры
     *
     * @author Алехнович Александр
     * @see Actions
     */
    protected Actions actions = new Actions(driverManager.getDriver());


    /**
     * Объект для выполнения любого js кода
     *
     * @author Алехнович Александр
     * @see JavascriptExecutor
     */
    protected JavascriptExecutor js = (JavascriptExecutor) driverManager.getDriver();


    /**
     * Объект явного ожидания
     * При применении будет ожидать заданного состояния 10 секунд с интервалом в 1 секунду
     *
     * @author Алехнович Александр
     * @see WebDriverWait
     */
    protected WebDriverWait wait = new WebDriverWait(driverManager.getDriver(), Integer.parseInt(props.getProperty(EXPLICITLY_WAITE)),
            Integer.parseInt(props.getProperty(EXPLICITLY_WAITE_SLEEP)));


    /**
     * Конструктор позволяющий инициализировать все странички и их элементы помеченные аннотацией {@link FindBy}
     * Подробнее можно просмотреть в класс {@link PageFactory}
     *
     * @author Алехнович Александр
     * @see FindBy
     * @see PageFactory
     * @see PageFactory#initElements(WebDriver, Object)
     */
    public BasePage() {
        PageFactory.initElements(driverManager.getDriver(), this);
    }


    /**
     * Функция позволяющая производить scroll до любого элемента с помощью js
     *
     * @param element - веб-элемент странички
     * @author Алехнович Александр
     * @see JavascriptExecutor
     */
    protected WebElement scrollToElementJs(WebElement element) {
        js.executeScript("arguments[0].scrollIntoView(true);", element);
        return element;
    }


    /**
     * Клик по элементу на js коде
     *
     * @param element - веб элемент на который нужно кликнуть
     * @author Алехнович Александр
     */
    public void elementClickJs(WebElement element) {
        js.executeScript("arguments[0].click();", element);
    }

    /**
     * Функция позволяющая производить scroll до любого элемента с помощью js со смещение
     * Смещение задается количеством пикселей по вертикали и горизонтали, т.е. смещение до точки (x, y)
     *
     * @param element - веб-элемент странички
     * @param x       - параметр координаты по горизонтали
     * @param y       - параметр координаты по вертикали
     * @author Алехнович Александр
     * @see JavascriptExecutor
     */
    public WebElement scrollWithOffset(WebElement element, int x, int y) {
        String code = "window.scroll(" + (element.getLocation().x + x) + ","
                + (element.getLocation().y + y) + ");";
        ((JavascriptExecutor) driverManager.getDriver()).executeScript(code, element, x, y);
        return element;
    }

    public void scrollElementInCenter(WebElement element) {
        js.executeScript("arguments[0].scrollIntoView({block: 'center'});", element);
    }

    /**
     * Проверяет наличие элемента на странице
     *
     * @param element - веб элемент который нужно найти
     * @author Алехнович Александр
     */
    public boolean isDisplayedElement(WebElement element) {
        try {
            driverManager.getDriver().manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
            element.isDisplayed();
            return true;
        } catch (StaleElementReferenceException | NoSuchElementException e) {
            return false;
        } finally {
            driverManager.getDriver().manage().timeouts().implicitlyWait(Integer.parseInt(props.getProperty(IMPLICITLY_WAIT)), TimeUnit.SECONDS);
        }
    }

    /**
     * Явное ожидание состояния clickable элемента
     *
     * @param element - веб-элемент который требует проверки clickable
     * @return WebElement - возвращаем тот же веб элемент что был передан в функцию
     * @author Алехнович Александр
     * @see WebDriverWait
     * @see org.openqa.selenium.support.ui.FluentWait
     * @see org.openqa.selenium.support.ui.Wait
     * @see ExpectedConditions
     */
    protected WebElement waitUtilElementToBeClickable(WebElement element) {
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    /**
     * Явное ожидание того что элемент станет видемым
     *
     * @param element - веб элемент который мы ожидаем что будет  виден на странице
     * @author Алехнович Александр
     */
    protected WebElement waitUtilElementToBeVisible(WebElement element) {
        return wait.until(ExpectedConditions.visibilityOf(element));
    }

    /**
     * Функция позволяющая производить scroll до любого элемента с помощью Actions
     *
     * @param element - веб элемент до которого нужно проскролить
     * @author Алехнович Александр
     */
    public WebElement scrollToElementActions(WebElement element) {
        actions.moveToElement(element).build().perform();
        return element;
    }

    protected String getResultReplaceAndTrim(WebElement element) {
        return element.getText().replaceAll("₽", "").trim();
    }

    /**
     * Общий метод по заполнения полей ввода
     *
     * @param field - веб-элемент поле ввода
     * @param value - значение вводимое в поле
     */
    protected void fillInputField(WebElement field, String value) {
        String[] valueSplit = value.split("");
        scrollElementInCenter(field);
        waitUtilElementToBeClickable(field);
        field.sendKeys(Keys.CONTROL + "a");
        for (String s : valueSplit) {
            field.sendKeys(s);
        }
        wait.until(ExpectedConditions.attributeContains(field, "value", value));
        field.sendKeys(Keys.BACK_SPACE);
        field.sendKeys(Keys.BACK_SPACE);
        field.sendKeys(valueSplit[valueSplit.length - 2]);
        field.sendKeys(valueSplit[valueSplit.length - 1]);
//        Assertions.assertEquals(value, field.getAttribute("value"),
//                "Поле: " + field + " было заполнено некорректно");
    }

    public void checkPageIsReady() {
        String prevState = driverManager.getDriver().getPageSource();
        for (int i=0; i<25; i++){
            try {
                Thread.sleep(100);
            } catch (InterruptedException ignore) {

            }
            if (prevState.equals(driverManager.getDriver().getPageSource())){
                break;
            }
        }
    }
}
