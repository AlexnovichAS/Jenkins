package steps;

import io.cucumber.java.ru.И;
import ru.sberbank.managers.PageManager;

public class StartPageSteps {
    private final PageManager pageManager = PageManager.getPageManager();

    @И("^Закрываем сообщения cookies$")
    public void closeCookiesDialog() {
        pageManager.getStartPage().closeCookiesDialog();
    }

    @И("^Выбираем (.+) в главном меню$")
    public void selectBaseMenu(String nameMenu) {
        pageManager.getStartPage().selectBaseMenu(nameMenu);
    }

    @И("^Выбираем (.+) в подменю главного меню$")
    public void selectSubMenu(String nameSubMenu) {
        pageManager.getStartPage().selectSubMenu(nameSubMenu);
    }
}
