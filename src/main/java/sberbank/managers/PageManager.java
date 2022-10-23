package sberbank.managers;


import ru.sberbank.pages.BasePage;
import ru.sberbank.pages.MortgagesSecondaryHousingPage;
import ru.sberbank.pages.StartPage;

/**
 * Класс для управления страничками
 *
 * @author Алехнович Александр
 */
public class PageManager {

    /**
     * Менеджер страничек
     *
     * @author Алехнович Александр
     */
    private static PageManager pageManager;

    /**
     * Стартовая страница
     *
     * @author Алехнович Александр
     */
    private StartPage startPage;

    /**
     * Стартовая страничка
     *
     * @author Алехнович Александр
     */
    private MortgagesSecondaryHousingPage mortgagesSecondaryHousingPage;

    /**
     * Базовый класс всех страничек
     *
     * @author Алехнович Александр
     */
    private BasePage basePage;

    /**
     * Конструктор специально был объявлен как private (singleton паттерн)
     *
     * @author Алехнович Александр
     * @see PageManager#getPageManager()
     */
    private PageManager() {
    }

    /**
     * Ленивая инициализация PageManager
     *
     * @return PageManager
     * @author Алехнович Александр
     */
    public static PageManager getPageManager() {
        if (pageManager == null) {
            pageManager = new PageManager();
        }
        return pageManager;
    }

    /**
     * Ленивая инициализация {@link StartPage}
     *
     * @return SearсhResultsPage
     * @author Алехнович Александр
     */
    public StartPage getStartPage() {
        if (startPage == null) {
            startPage = new StartPage();
        }
        return startPage;
    }

    /**
     * Ленивая инициализация {@link MortgagesSecondaryHousingPage}
     *
     * @return StartSearchPage
     * @author Алехнович Александр
     */
    public MortgagesSecondaryHousingPage getMortgagesSecondaryHousingPage() {
        if (mortgagesSecondaryHousingPage == null) {
            mortgagesSecondaryHousingPage = new MortgagesSecondaryHousingPage();
        }
        return mortgagesSecondaryHousingPage;
    }
}
