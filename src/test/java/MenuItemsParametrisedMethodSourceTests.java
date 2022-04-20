import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

public class MenuItemsParametrisedMethodSourceTests {


    MenuPage menuPage = new MenuPage();

    @BeforeAll
    static void setUp() {
        Configuration.holdBrowserOpen = true;
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.browserSize = "1920x1080";

    }

    static Stream<Arguments> checkLeftMenu() {
        return Stream.of(
                Arguments.of("Elements", List.of("Text Box", "Check Box", "Radio Button", "Web Tables")),
                        Arguments.of("Alerts, Frame & Windows", List.of( "Browser Windows", "Alerts", "Frames"))
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
}











