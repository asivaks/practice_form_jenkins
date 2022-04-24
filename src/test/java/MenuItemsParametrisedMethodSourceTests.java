import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

public class MenuItemsParametrisedMethodSourceTests {


    MenuPage menuPage = new MenuPage();

    @BeforeAll
    static void setUp() {
//        Configuration.holdBrowserOpen = true;
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.browserSize = "1920x1080";
        SelenideLogger.addListener("allure", new AllureSelenide());

    }

    static Stream<Arguments> checkLeftMenu() {
        return Stream.of(
                Arguments.of("Elements", List.of("Text Box", "Check Box", "Radio Button", "Web Tables")),
                        Arguments.of("Alerts, Frame & Windows", List.of( "Browser Windows", "Alerts", "Frames")),
                        Arguments.of("Interactions", List.of())
        );
    }

    @MethodSource("checkLeftMenu")
    @ParameterizedTest
    void checkLeftMenu(String menuItem, List<String> menuSubItems) {
        //ACTIONS
        menuPage.openPage()
                .checkMenuItem(menuItem)
        ;
        menuSubItems.forEach((menuSubItem) -> {
            menuPage.checkMenuSubItem(menuItem, menuSubItem);
        });
        //$(".left-pannel").$(byText("Elements")).closest(".element-group").$(byText("Text Box")).should(exist);
        //$(".left-pannel").$(byText("Forms")).closest(".element-group").$(byText("Practice Form")).should(exist);

    }


    static Stream<Arguments> checkMenu() {
        return Stream.of(
                Arguments.of("menuItem1", List.of("menuSubItem1-1", "menuSubItem1-2", "menuSubItem1-3")),
                        Arguments.of("menuItem2", List.of("menuSubItem2-1", "menuSubItem2-2", "menuSubItem2-3"))
        );
    }
    @Disabled
    @MethodSource("checkLeftMenu")
    @ParameterizedTest
    void checkMenu(String menuItem, List<String> menuSubItems) {
        //ACTIONS
        menuPage.openPage()
                .checkMenuItem(menuItem)
        ;
        menuSubItems.forEach((menuSubItem) -> {
            menuPage.checkMenuSubItem(menuItem, menuSubItem);
        });
    }
}











