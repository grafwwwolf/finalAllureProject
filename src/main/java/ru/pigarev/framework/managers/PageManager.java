package ru.pigarev.framework.managers;

import ru.pigarev.framework.pages.DepositsPage;
import ru.pigarev.framework.pages.HomePage;

public class PageManager {

    private static PageManager pageManager = null;

    private HomePage homePage;
    private DepositsPage depositsPage;

    private PageManager() {
    }

    public static PageManager getInstance() {
        if (pageManager == null) {
            pageManager = new PageManager();
        }
        return pageManager;
    }

    public HomePage getHomePage() {
        if (homePage == null) {
            homePage = new HomePage();
        }
        return homePage;
    }

    public DepositsPage getDepositsPage() {
        if (depositsPage == null) {
            depositsPage = new DepositsPage();
        }
        return depositsPage;
    }

}
