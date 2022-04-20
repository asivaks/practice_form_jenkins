import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.$;

public class MenuPage {

    //LOCATORS + TEXT CONSTS
    SelenideElement headerLocator = $(".main-header");
    final String headerText = "Select Menu";

    SelenideElement menuLocator = $(".left-pannel");
    final String subMenuCSSselector = ".element-group";

    public MenuPage openPage() {

        open("/select-menu");
        headerLocator.shouldHave(text(headerText));

        //remove header & footer
        executeJavaScript("$('fixedban').remove()");
        executeJavaScript("$('footer').remove()");
        return this;
    }

    public MenuPage checkMenuItem(String menuItem) {
        //menuLocator.$(byXpath("//*[@class='header-text' and text()='"+ menuItem + "']")).should(exist);   //somehow fails
        //$(byXpath("//*[@class='left-pannel']//*[@class='header-text' and text()='Elements']")).should(exist);
        $(byXpath("//*[@class='left-pannel']//*[@class='header-text' and text()='"+menuItem+"']")).should(exist);
        return this;
    }

    public MenuPage checkMenuSubItem(String menuItem, String menuSubItem) {

        //$(".left-pannel").$(byXpath("//*[@class='header-text' and text()='Elements']"));      //somehow fails
        //$(".left-pannel").$(".header-text");                                                  //ok

        //$(byXpath("//*[@class='left-pannel']")).$(byXpath("//*[@class='header-text']"));                             //somehow fails
        //$(byXpath("//*[@class='left-pannel']")).$(byXpath("//*[@class='header-text' and text()='Elements']"));       //somehow fails
        //$(byXpath("//*[@class='left-pannel']//*[@class='header-text' and text()='Elements']"));                      //ok
        //$(byXpath("//*[@class='left-pannel']//*[@class='header-text' and text()='Elements']//*[text()='Text Box']")).closest(".element-group"); //ok
        //$(".left-pannel").$(byText("Elements")).closest(".element-group").shouldHave(text("Text Box"));

        //$(".left-pannel").$(byText("Elements")).closest(".element-group").$(byText("Text Box")).should(exist);
        menuLocator.$(byText(menuItem)).closest(subMenuCSSselector).$(byText(menuSubItem)).should(exist);

        return this;
    }
}
