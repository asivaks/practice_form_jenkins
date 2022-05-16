import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

public class RadioButtonParametrisedTests extends TestBase{

    RadioButtonPage radioButtonPage = new RadioButtonPage();


    /*
//  moved to TestBase

    @BeforeAll
    static void setUp() {
        //Configuration.holdBrowserOpen = true;
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.browserSize = "1920x1080";
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

     */

    @ValueSource(strings = {
            "Yes",
            "Impressive"
    })
    //@DisplayName("Parametrized test example")  name is set in ParameterizedTest annotation
    @ParameterizedTest(name = "Radio button parametrized test, {0} selected")
    void RadioButtonSelectLikeOptionTest(String optionSelected) {
        //ACTIONS
        radioButtonPage.openPage()
                       .setLikeOption(optionSelected);
    }


    @CsvSource({
            "Yes, Impressive",
            "Impressive, Yes"
    })
    @ParameterizedTest(name = "Radio button parametrized test, {0} selected, {1} not selected")
    void RadioButtonSelectLikeOptionFullTest(String optionSelected, String optionNotSelected) {
        //ACTIONS
        radioButtonPage.openPage()
                       .setLikeOption(optionSelected)
                       .checkLikeOptionNotSelected(optionNotSelected);
    }
}
