import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverConditions.url;

@Tag("tag_linksTests")
public class LinksTests {
//    LinksPage linksPage = new LinksPage();

    @BeforeAll
    static void setUp() {
        //Configuration.holdBrowserOpen = true;
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.browserSize = "1920x1080";
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    static Stream<Arguments> linksStream() {
        return Stream.of(
                Arguments.of("Home", "https://demoqa.com/"),
                        Arguments.of("Home[a-zA-Z\\d]{5}", "https://demoqa.com/"),
                        Arguments.of("FAIL", "https://fail.xxx/")
        );
    }

    @MethodSource("linksStream")
    @ParameterizedTest
    void checkLink(String linkName, String linkUrl) {
        open("");

        $(byText("Elements")).closest(".top-card").click();
        $(byText("Links")).closest(".btn").click();
        //$(linkText(linkName)).click();
        $("#linkWrapper").$$("a").find(Condition.matchText(linkName)).click();
        switchTo().window(1);
        webdriver().shouldHave(url(linkUrl));
        Selenide.closeWebDriver();

    }
}
