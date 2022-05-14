import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

public class LinksAnnotatedStepTests extends TestBase{

    /*
//  will pass
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

    @Disabled
    @MethodSource("linksStream")
    @ParameterizedTest
    void checkLink(String linkName, String linkUrl) {
        LinksPage linksPage = new LinksPage();

        linksPage.openMainPage()
                .openCategoryPage()
                .openLinksPage()
                .followLink(linkName)
                .checkIfHere(linkUrl)
                .closeWebDriver();

    }
}
