package steps;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.ru.И;
import ru.sberbank.managers.PageManager;


public class MortgagesSecondaryHousingPageSteps {

    private final PageManager pageManager = PageManager.getPageManager();

    @И("^Проверяем что открылась страница (.+)$")
    public void checkOpenInsurancePage(String nameTitle) {
        pageManager.getMortgagesSecondaryHousingPage().checkOpenInsurancePage(nameTitle);
    }

    @И("^Заполняем поля формы:$")
    public void fillFields(DataTable mapFieldsAndValue) {
        mapFieldsAndValue.asMap(String.class, String.class).forEach((key, value) ->
                pageManager.getMortgagesSecondaryHousingPage().fillField((String) key, (String) value));
    }

    @И("Проставляем услуги, снижающие ставку по кредиту:(false|true)")
    public void processTicks(DataTable mapFieldsAndValue1) {
        mapFieldsAndValue1.asMap(String.class, String.class).forEach((key, value) ->
                pageManager.getMortgagesSecondaryHousingPage().processTicks((String) key, (String) value));
    }

    @И("^Проверяем значения полей на странице:$")
    public void checkFieldValues(DataTable mapFieldsAndValue2) {
        mapFieldsAndValue2.asMap(String.class, String.class).forEach((key, value) ->
                pageManager.getMortgagesSecondaryHousingPage().checkFieldValues((String) key, (String) value));
    }
}
