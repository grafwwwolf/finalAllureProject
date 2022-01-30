package ru.pigarev.basetests;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import ru.pigarev.framework.managers.DriverManager;
import ru.pigarev.framework.managers.InitManager;
import ru.pigarev.framework.managers.PageManager;
import ru.pigarev.framework.managers.TestPropManager;
import ru.pigarev.framework.utils.PropConst;
import ru.pigarev.framework.utils.models.Deposit;

public class BaseTests {
    private final DriverManager driverManager = DriverManager.getInstance();
    private final TestPropManager propManager = TestPropManager.getInstance();
    protected PageManager pageManager = PageManager.getInstance();
    protected static Deposit rubDeposit = new Deposit(PropConst.RUB_CURRENCY, PropConst.RUB_CURRENCY_ABB, "300000", 6, "50000",
            true, "15478,55", "250000", "565478,55");
    protected static Deposit usdDeposit = new Deposit(PropConst.USD_CURRENCY, PropConst.USD_CURRENCY_ABB, "500000", 12, "20000",
            true, "920,60", "220000", "720920,60");

    @BeforeAll
    public static void beforeAll() {
        InitManager.initFramework();
    }

    @BeforeEach
    public void beforeEach() {
        driverManager.getDriver().get(propManager.getProperty(PropConst.BASE_URL));
    }

    @AfterEach
    public void afterEach() {

    }

    @AfterAll
    public static void afterAll() {
        InitManager.quitFramework();
        System.out.println("@AfterClass -> afterAll()\n");
    }
}
