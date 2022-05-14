import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverConditions.url;
import static io.qameta.allure.Allure.step;

public class LinksLambdaStepTests extends TestBase{
//    LinksPage linksPage = new LinksPage();

    /*
//  will fail with
//  java.lang.IllegalStateException: No webdriver is bound to current thread: 13. You need to call open(url) first.
//  moved to TestBase
    @BeforeAll
    static void setUp() {
        //Configuration.holdBrowserOpen = true;
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.browserSize = "1920x1080";
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

     */

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
        step("Open main page", () -> {
                    open("");
                });

        step("Open category page", () -> {
                    $(byText("Elements")).closest(".top-card").click();
                });
        step("Open Links page", () -> {
                    $(byText("Links")).closest(".btn").click();
                });
        //$(linkText(linkName)).click();
        step("Follow link" + linkName, () -> {
                    $("#linkWrapper").$$("a").find(Condition.matchText(linkName)).click();
                });
        step("Check if we end up here:" + linkUrl, () -> {
            switchTo().window(1);
            webdriver().shouldHave(url(linkUrl));
        });

        Selenide.closeWebDriver();

    }
}
