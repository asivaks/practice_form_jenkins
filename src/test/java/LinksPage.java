import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverConditions.url;

public class LinksPage {

    @Step("Open main page")
    public LinksPage openMainPage() {
        open("");
        return this;
    }

    @Step("Open category page")
    public LinksPage openCategoryPage() {
        $(byText("Elements")).closest(".top-card").click();
        return this;
    }

    @Step("Open Links page")
    public LinksPage openLinksPage() {
        $(byText("Links")).closest(".btn").click();
        return this;
    }

    @Step("Follow link {linkName}")
    public LinksPage followLink(String linkName) {
        //$(linkText(linkName)).click();
        $("#linkWrapper").$$("a").find(Condition.matchText(linkName)).click();
        return this;
    }

    @Step("Check if we end up here {linkUrl}")
    public LinksPage checkIfHere(String linkUrl) {
        switchTo().window(1);
        webdriver().shouldHave(url(linkUrl));
        return this;
    }

    @Step("Close Web Driver")
    public void closeWebDriver () {
        Selenide.closeWebDriver();
    }

}
