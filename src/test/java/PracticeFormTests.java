import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class PracticeFormTests {
    @BeforeAll
    static void setUP() {
//        System.out.println("### Before All");
        Configuration.holdBrowserOpen = true;
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.browserSize = "1920x1080";
    }

    @Test
    void fillFormTest() {
        final String firstName = "Alex";
        final String lastName = "Kosh";
        final String email = "alex@mail.xxx";
        final String phone = "1234567890";
        final String sobjects = "some subjects";
        final String currAddr = "Current Address";
        final String state = "NCR";
        final String city = "Delphi";

        open("/automation-practice-form");

        $("[id=firstName]").setValue(firstName);
        $("[id=lastName]").setValue(lastName);
        $("[id=userEmail]").setValue(email);
        // TODO: 02.04.2022 GENDER
        $("[id=userNumber]").setValue(phone);

        // TODO: 02.04.2022 Date of Birth

        // TODO: 02.04.2022 Subjects
        //<div class="subjects-auto-complete__control css-yk16xz-control"><div class="subjects-auto-complete__value-container subjects-auto-complete__value-container--is-multi css-1hwfws3"><div class="subjects-auto-complete__placeholder css-1wa3eu0-placeholder"></div><div class="css-1g6gooi"><div class="subjects-auto-complete__input" style="display: inline-block;"><input autocapitalize="none" autocomplete="off" autocorrect="off" id="subjectsInput" spellcheck="false" tabindex="0" type="text" aria-autocomplete="list" value="" style="box-sizing: content-box; width: 2px; background: 0px center; border: 0px; font-size: inherit; opacity: 1; outline: 0px; padding: 0px; color: inherit;"><div style="position: absolute; top: 0px; left: 0px; visibility: hidden; height: 0px; overflow: scroll; white-space: pre; font-size: 16px; font-family: -apple-system, &quot;system-ui&quot;, &quot;Segoe UI&quot;, Roboto, &quot;Helvetica Neue&quot;, Arial, &quot;Noto Sans&quot;, sans-serif, &quot;Apple Color Emoji&quot;, &quot;Segoe UI Emoji&quot;, &quot;Segoe UI Symbol&quot;, &quot;Noto Color Emoji&quot;; font-weight: 400; font-style: normal; letter-spacing: normal; text-transform: none;"></div></div></div></div><div class="subjects-auto-complete__indicators css-1wy0on6"><span class="subjects-auto-complete__indicator-separator css-1okebmr-indicatorSeparator"></span></div></div>

        // TODO: 02.04.2022 Hobbies

        // TODO: 02.04.2022 Picture

        $("[id=currentAddress]").setValue(currAddr);

        // TODO: 02.04.2022 State

        // TODO: 02.04.2022 City

        $("[id=submit]").click();

    }
}
