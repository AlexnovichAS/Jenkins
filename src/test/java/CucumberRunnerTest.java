import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"ru.sberbank.utils.AllureListener"},
        glue = {"steps"},
        features = {"src/test/resources/"},
        tags = {"@firstTest"}
)
public class CucumberRunnerTest {
}
