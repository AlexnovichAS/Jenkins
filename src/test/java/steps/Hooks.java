package steps;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.qameta.allure.Allure;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import ru.sberbank.managers.DriverManager;
import ru.sberbank.managers.InitManager;
import ru.sberbank.managers.TestPropManager;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static ru.sberbank.utils.PropConst.BASE_URL;

public class Hooks {

    private final DriverManager driverManager = DriverManager.getDriverManager();

    @Before
    public void before() {
        InitManager.initFramework();
        driverManager.getDriver().get(TestPropManager.getTestPropManager().getProperty(BASE_URL));
    }

    @After
    public void after(Scenario scenario) {
            if (scenario.isFailed()) {
                Allure.addAttachment(
                        "failureScreenshot",
                        "image/png",
                        addScreenshot(),
                        "png"
                );
            }
        InitManager.quitFramework();
    }
        private static InputStream addScreenshot() {
            byte[] screenshot = ((TakesScreenshot) DriverManager.getDriverManager().getDriver()).getScreenshotAs(OutputType.BYTES);
            return new ByteArrayInputStream(screenshot);
        }
}
