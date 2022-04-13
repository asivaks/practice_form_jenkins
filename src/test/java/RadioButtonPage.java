import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.selected;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.executeJavaScript;


public class RadioButtonPage
{

    //LACATORS
    SelenideElement capchionLocator = $(".mb-3");
    SelenideElement headerLocator = $(".main-header");
    //SelenideElement likeOptionInput

    final String capchionText = "Do you like the site?";
    final String headerText = "Radio Button";


    //ACTIONS
    public RadioButtonPage openPage() {

        open("/radio-button");
        capchionLocator.shouldHave(text(capchionText));
        headerLocator.shouldHave(text(headerText));

        //remove header & footer
        executeJavaScript("$('fixedban').remove()");
        executeJavaScript("$('footer').remove()");
        return this;
    }

    public RadioButtonPage setLikeOption(String optionSelected) {

        //<div class="custom-control custom-radio custom-control-inline">
        //<input type="radio" id="yesRadio" name="like" class="custom-control-input">   click will be intercepted by following, click that, than check if selected this
        //<label class="custom-control-label" for="yesRadio">Yes</label>                click this
        //</div>

        SelenideElement labelOverRadioButtonLocator = $(byXpath("//*[@class='custom-control-label' and text()='" + optionSelected + "']"));
        SelenideElement wholeBodyLocator = $("[class=body-height]");

        final String resultText = "You have selected";

        //$(byXpath("//*[@class='custom-control-label' and text()='Yes']")).click()  xpath only, css can not work with text
        labelOverRadioButtonLocator.parent().$("[type=radio]").shouldNotBe(selected);
        wholeBodyLocator.shouldNotHave((text(resultText)));
        labelOverRadioButtonLocator.click();
        labelOverRadioButtonLocator.parent().$("[type=radio]").shouldBe(selected);
        wholeBodyLocator.$(byText(resultText)).shouldHave(text(optionSelected));

        return this;
    }

    public RadioButtonPage checkLikeOptionNotSelected(String optionNotSelected) {
        SelenideElement labelOverRadioButtonLocator = $(byXpath("//*[@class='custom-control-label' and text()='" + optionNotSelected + "']"));
        labelOverRadioButtonLocator.parent().$("[type=radio]").shouldNotBe(selected);
        return this;
    }
}
