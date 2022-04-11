package pageObject;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class CalendarComponent {
    SelenideElement dateOfBirthInput = $("#dateOfBirthInput");
    SelenideElement monthInput = $(".react-datepicker__month-select");
    SelenideElement yearInput = $(".react-datepicker__year-select");
    SelenideElement dayInput = $(".react-datepicker__month");

    public void setDate(String day, String month, String year) {
        dateOfBirthInput.click();
        monthInput.selectOption(month);
        yearInput.selectOption(year);

        //will also work
        //$("[aria-label$='Choose Saturday, December 2nd, 2000']").click();

        //"02"->"2"
        dayInput.$(byText(Integer.toString(
                                Integer.parseInt(day)
                        )
                )
        ).click();
    };
}
