package ru.sberbank.pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ru.sberbank.managers.DriverManager;

import java.util.List;

public class MortgagesSecondaryHousingPage extends BasePage {

    @FindBy(xpath = "//div[contains(@class,'kit-col_lg-bottom')]/h1")
    private WebElement title;

    @FindBy(xpath = "//iframe[contains(@sandbox,'allow-forms') and @title='Основной контент']")
    private WebElement iframeMainContent;

    @FindBy(xpath = "//div[contains(@class,'dc-input__input-container')]/label")
    private List<WebElement> fieldsToFill;

    @FindBy(xpath = "//div[contains(@data-e2e-id,'discounts-block')]//span[text()='Своя ставка']")
    private WebElement ownRate;

    @FindBy(xpath = "//div[contains(@data-e2e-id,'discounts-block')]//span[contains(text(),'Скидка') and contains(text(),'при покупке недвижимости')]")
    private WebElement propertyPurchaseDiscount;

    @FindBy(xpath = "//div[contains(@data-e2e-id,'discounts-block')]//span[text()='Страхование жизни и здоровья']")
    private WebElement lifeAndHealthInsurance;

    @FindBy(xpath = "//div[contains(@data-e2e-id,'discounts-block')]//span[text()='Электронная регистрация сделки']")
    private WebElement electronicTransactionRegistration;

    @FindBy(xpath = "//div[contains(@data-test-id,'main-results-block')]//span[text()='Ежемесячный платеж']/following-sibling::span")
    private WebElement monthlyPayment;

    @FindBy(xpath = "//div[contains(@data-test-id,'main-results-block')]//div[contains(@class,'hint-target')]//span[text()='Процентная ставка']/following-sibling::span/span")
    private WebElement interestRate;

    @FindBy(xpath = "//div[contains(@data-test-id,'main-results-block')]//span[text()='Сумма кредита']/following-sibling::span/span")
    private WebElement creditAmount;

    @FindBy(xpath = "//div[contains(@data-test-id,'main-results-block')]//span[text()='Налоговый вычет']/following-sibling::span/span")
    private WebElement taxDeduction;

    @FindBy(xpath = "//div[contains(@class,'ppr-container--inline')]//span[text()='Необходимый доход']/following-sibling::span/span")
    private WebElement necessaryIncome;


    /**
     * Проверка открытия страницы
     *
     * @return InsurancePage - т.е. остаемся на этой странице
     */
    public MortgagesSecondaryHousingPage checkOpenInsurancePage(String namePage) {
        waitUtilElementToBeVisible(title);
        Assert.assertEquals("Заголовок: " + namePage + " отсутствует/не соответствует требуемому",
                namePage, title.getText());
        DriverManager.getDriverManager().getDriver().switchTo().frame(iframeMainContent);
        return this;
    }

    /**
     * Метод заполнения полей
     *
     * @param nameField - имя веб элемента, поля ввода
     * @param value     - значение вводимое в поле
     * @return MortgagesSecondaryHousingPage - т.е. остаемся на этой странице
     */
    public MortgagesSecondaryHousingPage fillField(String nameField, String value) {
        checkPageIsReady();
        WebElement element;
        for (WebElement webElement : fieldsToFill) {
            if (webElement.getText().trim().equalsIgnoreCase(nameField)) {
                element = webElement.findElement(By.xpath("./../input"));
                fillInputField(element, value);
                return this;
            }
        }
        Assert.fail("Поле с наименованием '" + nameField + "' отсутствует на странице" + "'Ипотека на вторичное жильё от'");
        return this;
    }

    /**
     * Управление чекбоксами при расчете ипотеки
     *
     * @param nameCheckbox - имя веб элемента
     * @param value        - значение проверяемое в поле
     * @return MortgagesSecondaryHousingPage - т.е. остаемся на этой странице
     */
    public MortgagesSecondaryHousingPage processTicks(String nameCheckbox, String value) {
        WebElement element = null;
        switch (nameCheckbox) {
            case "Своя ставка":
                element = ownRate.findElement(By.xpath("./../..//input"));
                if (!element.getAttribute("aria-checked").equals(value)) {
                    scrollElementInCenter(element);
                    elementClickJs(element);
                }
                break;
            case "Скидка 0,3% при покупке недвижимости на Домклик":
                element = propertyPurchaseDiscount.findElement(By.xpath("./../..//input"));
                if (!element.getAttribute("aria-checked").equals(value)) {
                    scrollElementInCenter(element);
                    elementClickJs(element);
                }
                break;
            case "Страхование жизни и здоровья":
                element = lifeAndHealthInsurance.findElement(By.xpath("./../..//input"));
                if (!element.getAttribute("aria-checked").equals(value)) {
                    scrollElementInCenter(element);
                    elementClickJs(element);
                }
                break;
            case "Электронная регистрация сделки":
                element = electronicTransactionRegistration.findElement(By.xpath("./../..//input"));
                if (!element.getAttribute("aria-checked").equals(value)) {
                    scrollElementInCenter(element);
                    elementClickJs(element);
                }
                break;
            default:
                Assert.fail("Чекбокс с наименованием '" + nameCheckbox + "' отсутствует на странице " +
                        "'Ипотека на вторичное жильё от'");

        }
        element = element.findElement(By.xpath("./../..//input"));
        Assert.assertEquals(value, element.getAttribute("aria-checked"),
                "Проверка чекбокса '" + nameCheckbox + "' была не пройдена");
        return this;
    }

    /**
     * Проверка ошибки относящаяся к конкретному полю на форме
     *
     * @param nameField - имя веб элемента
     * @param value     - значение проверяемое в поле
     * @return RegistrationFormPage - т.е. остаемся на этой странице
     */
    public MortgagesSecondaryHousingPage checkFieldValues(String nameField, String value) {
        checkPageIsReady();
        WebElement element = null;
        switch (nameField) {
            case "Ежемесячный платеж":
                element = monthlyPayment;
                scrollElementInCenter(element);
                waitUtilElementToBeVisible(element);
                break;
            case "Процентная ставка":
                element = interestRate;
                scrollElementInCenter(element);
                waitUtilElementToBeVisible(element);
                break;
            case "Сумма кредита":
                element = creditAmount;
                scrollElementInCenter(element);
                waitUtilElementToBeVisible(element);
                break;
            case "Налоговый вычет":
                waitUtilElementToBeClickable(taxDeduction);
                element = taxDeduction;
                scrollElementInCenter(element);
                waitUtilElementToBeVisible(element);
                break;
            case "Необходимый доход":
                element = necessaryIncome;
                scrollElementInCenter(element);
                waitUtilElementToBeVisible(element);
                break;
            default:
                Assert.fail("Поле с наименованием '" + nameField + "' отсутствует на странице " +
                        "'Ипотека на вторичное жильё от'");

        }
        Assert.assertEquals(value, getResultReplaceAndTrim(element),
                "Проверка значения поля: '" + nameField + "' не пройдена");
        return this;
    }
}
