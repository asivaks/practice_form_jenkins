import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.io.File;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class PracticeFormTests {
    @BeforeAll
    static void setUp() {
//        System.out.println("### Before All");
        Configuration.holdBrowserOpen = true;
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.browserSize = "1920x1080";
    }


    @Test
    void fillFormTestDateType() {
        //VARIABLES
        final String firstName = "Alex";
        final String lastName = "Kosh";
        final String email = "alex@mail.xxx";
        final String gender = "Male";
        final String phone = "1234567890";
        final String dateOfBirthType = "03 Apr 2000";
        final String dateOfBirthExpect = "03 April,2000";
        final String subjects = "Maths";
        final String hobbies = "Sports";
        final String imgPath = "image.jpeg";
        final String currAddr = "Street name, House 2, Apartment 222";
        final String state = "NCR";
        final String city = "Delhi";

        open("/automation-practice-form");

        //remove header & footer
        executeJavaScript("$('fixedban').remove()");
        executeJavaScript("$('footer').remove()");

        $("#firstName").setValue(firstName);
        $("#lastName").setValue(lastName);
        $("#userEmail").setValue(email);
        $("#genterWrapper").$(byText(gender)).click();
        $("#userNumber").setValue(phone);

        //Does not work
        //$("[id=dateOfBirthInput]").clear();

        //DATE OF BIRTH BLOCK START
        $("#dateOfBirthInput").sendKeys((Keys.COMMAND + "a"));
        $("#dateOfBirthInput").sendKeys(Keys.SPACE);
        $("#dateOfBirthInput").setValue(dateOfBirthType).pressEnter();
        //DATE OF BIRTH BLOCK END

        //<div class="subjects-auto-complete__control css-yk16xz-control"><div class="subjects-auto-complete__value-container subjects-auto-complete__value-container--is-multi css-1hwfws3"><div class="subjects-auto-complete__placeholder css-1wa3eu0-placeholder"></div><div class="css-1g6gooi"><div class="subjects-auto-complete__input" style="display: inline-block;"><input autocapitalize="none" autocomplete="off" autocorrect="off" id="subjectsInput" spellcheck="false" tabindex="0" type="text" aria-autocomplete="list" value="" style="box-sizing: content-box; width: 2px; background: 0px center; border: 0px; font-size: inherit; opacity: 1; outline: 0px; padding: 0px; color: inherit;"><div style="position: absolute; top: 0px; left: 0px; visibility: hidden; height: 0px; overflow: scroll; white-space: pre; font-size: 16px; font-family: -apple-system, &quot;system-ui&quot;, &quot;Segoe UI&quot;, Roboto, &quot;Helvetica Neue&quot;, Arial, &quot;Noto Sans&quot;, sans-serif, &quot;Apple Color Emoji&quot;, &quot;Segoe UI Emoji&quot;, &quot;Segoe UI Symbol&quot;, &quot;Noto Color Emoji&quot;; font-weight: 400; font-style: normal; letter-spacing: normal; text-transform: none;"></div></div></div></div><div class="subjects-auto-complete__indicators css-1wy0on6"><span class="subjects-auto-complete__indicator-separator css-1okebmr-indicatorSeparator"></span></div></div>
        $("#subjectsInput").setValue(subjects).pressEnter();

        //If browser window would be to short scroll will fail
        //$("[id='submit']").scrollIntoView(true);

        $("#hobbiesWrapper").$(byText(hobbies)).click();

        $("#uploadPicture").uploadFromClasspath(imgPath);
        //src/test/resources/image.jpeg
        //$("#uploadPicture").uploadFile(new File(fullImgPath);

        $("#currentAddress").setValue(currAddr);

        $("#state").click();
        $("#stateCity-wrapper").$(byText(state)).click();
        $("#city").click();
        $("#stateCity-wrapper").$(byText(city)).click();

        //DOES NOT WORK
        //$("[id=currentAddress]").sendKeys((Keys.COMMAND + "-"));
        //$("[id=currentAddress]").sendKeys((Keys.COMMAND + Keys.SUBTRACT));
        Selenide.zoom(0.75);
        $("#submit").click();

        //ASSERTS
        $("#example-modal-sizes-title-lg").shouldHave(text("Thanks for submitting the form"));
        $(".table-responsive").shouldHave(
                text(firstName + ' ' + lastName),
                text(email),
                text(gender),
                text(phone),
                text(dateOfBirthExpect),
                text(subjects),
                text(hobbies),
                text(imgPath),
                text(currAddr),
                text(state + ' ' + city)
        );
    }


    @Test
    void fillFormTestDateClick() {
        //VARIABLES
        final String firstName = "Alex";
        final String lastName = "Kosh";
        final String fullNameExpect = firstName + ' ' + lastName;
        final String email = "alex@mail.xxx";
        final String gender = "Male";
        final String phone = "1234567890";
        final String dateOfBirthDay = "02" ; //as string
        final String dateOfBirthMonthFull = "December";
        final String dateOfBirthYear = "2000";  //as string
        //final String dateOfBirthExpect = "02 December,2000";
        final String dateOfBirthExpect = dateOfBirthDay + " " + dateOfBirthMonthFull + "," + dateOfBirthYear;
        final String subjects = "Maths";
        final String hobbies = "Sports";
        final String imgPath = "image.jpeg";
        final String currAddr = "Street name, House 2, Apartment 222";
        final String state = "NCR";
        final String city = "Delhi";

        open("/automation-practice-form");

        //remove header & footer
        executeJavaScript("$('fixedban').remove()");
        executeJavaScript("$('footer').remove()");

        $("#firstName").setValue(firstName);
        $("#lastName").setValue(lastName);
        $("#userEmail").setValue(email);
        $("#genterWrapper").$(byText(gender)).click();
        $("#userNumber").setValue(phone);

        //Does not work
        //$("[id=dateOfBirthInput]").clear();

        //DATE OF BIRTH BLOCK START
        $("#dateOfBirthInput").click();
        $(".react-datepicker__month-select").selectOption(dateOfBirthMonthFull);
        $(".react-datepicker__year-select").selectOption(dateOfBirthYear);

        //will also work
        //$("[aria-label$='Choose Saturday, December 2nd, 2000']").click();

        //"02"->"2"
        $(".react-datepicker__month").$(byText(Integer.toString(
                                Integer.parseInt(dateOfBirthDay)
                        )
                )
        ).click();
        //DATE OF BIRTH BLOCK END

        //<div class="subjects-auto-complete__control css-yk16xz-control"><div class="subjects-auto-complete__value-container subjects-auto-complete__value-container--is-multi css-1hwfws3"><div class="subjects-auto-complete__placeholder css-1wa3eu0-placeholder"></div><div class="css-1g6gooi"><div class="subjects-auto-complete__input" style="display: inline-block;"><input autocapitalize="none" autocomplete="off" autocorrect="off" id="subjectsInput" spellcheck="false" tabindex="0" type="text" aria-autocomplete="list" value="" style="box-sizing: content-box; width: 2px; background: 0px center; border: 0px; font-size: inherit; opacity: 1; outline: 0px; padding: 0px; color: inherit;"><div style="position: absolute; top: 0px; left: 0px; visibility: hidden; height: 0px; overflow: scroll; white-space: pre; font-size: 16px; font-family: -apple-system, &quot;system-ui&quot;, &quot;Segoe UI&quot;, Roboto, &quot;Helvetica Neue&quot;, Arial, &quot;Noto Sans&quot;, sans-serif, &quot;Apple Color Emoji&quot;, &quot;Segoe UI Emoji&quot;, &quot;Segoe UI Symbol&quot;, &quot;Noto Color Emoji&quot;; font-weight: 400; font-style: normal; letter-spacing: normal; text-transform: none;"></div></div></div></div><div class="subjects-auto-complete__indicators css-1wy0on6"><span class="subjects-auto-complete__indicator-separator css-1okebmr-indicatorSeparator"></span></div></div>
        $("#subjectsInput").setValue(subjects).pressEnter();

        //If browser window would be to short scroll will fail
        //$("[id='submit'").scrollIntoView(true);

        $("#hobbiesWrapper").$(byText(hobbies)).click();
        $("#uploadPicture").uploadFromClasspath(imgPath);
        $("#currentAddress").setValue(currAddr);

        $("#state").click();
        $("#stateCity-wrapper").$(byText(state)).click();
        $("#city").click();
        $("#stateCity-wrapper").$(byText(city)).click();

        $("#submit").click();

        //ASSERTS
        $("#example-modal-sizes-title-lg").shouldHave(text("Thanks for submitting the form"));
        $(".table-responsive").shouldHave(
                text(fullNameExpect),
                text(email),
                text(gender),
                text(phone),
                text(dateOfBirthExpect),
                text(subjects),
                text(hobbies),
                text(imgPath),
                text(currAddr),
                text(state + ' ' + city)
        );

    }

}
