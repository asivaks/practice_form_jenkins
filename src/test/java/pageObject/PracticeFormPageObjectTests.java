package pageObject;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.Month;
import java.time.format.TextStyle;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class PracticeFormPageObjectTests {

    PracticeFormPage practiceFormPage = new PracticeFormPage();
    Faker faker = new Faker(); //https://github.com/DiUS/java-faker
    Calendar calendar = Calendar.getInstance();  //get day, month etc from date  https://www.baeldung.com/java-year-month-day

    @BeforeAll
    static void setUp() {
        Configuration.holdBrowserOpen = true;
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.browserSize = "1920x1080";
        //Configuration.browserSize = "1920x900";  //move Submit button outside display area
    }


    @Test
    void fillFormTestDateClick() {
        //VARIABLES
        //final String firstName = "Alex";
        final String firstName = faker.name().firstName();
        final String lastName = faker.name().lastName();
        final String email = faker.internet().emailAddress();
        // enum Gender declared in Gender.java
        final Gender gender = Gender.values()[new Random().nextInt(Gender.values().length)];
                                      //"1234567890";
        final String phone = faker.number().digits(10);

        Date dateOfBirth = faker.date().birthday();
        calendar.setTime(dateOfBirth);
        final String dateOfBirthDay = String.valueOf(calendar.get(Calendar.DAY_OF_MONTH));
        System.out.println("dateOfBirthDay= " + dateOfBirthDay);
                                //starts from 0
        final int bdMonth = calendar.get(Calendar.MONTH); //needed only to get dateOfBirthMonthFull
                                                  //starts from 1
        final String dateOfBirthMonthFull = Month.of(bdMonth+1).getDisplayName(TextStyle.FULL, Locale.US);  //"December"
        final String dateOfBirthYear = String.valueOf(calendar.get(Calendar.YEAR)); //"2000"

        //should be like this "02 December,2000"
        String dateOfBirthDay2Digits = (calendar.get(Calendar.DAY_OF_MONTH) > 10)
                ? dateOfBirthDay
                : "0" + dateOfBirthDay;
        final String dateOfBirthExpect = dateOfBirthDay2Digits + " " + dateOfBirthMonthFull + "," + dateOfBirthYear;
        System.out.println(dateOfBirthExpect);

        final String subjects = "Maths";

        // TODO: 11.04.2022 change to enum like Gender {Sports, Reading, Music}
        final String hobbies = "Sports";
        final String imgPath = "image.jpeg";
        final String currAddr = faker.address().fullAddress();
        final String state = "NCR";
        final String city = "Delhi";

        final String resultsOkMessage =  "Thanks for submitting the form";

        //locators
        SelenideElement resultsHeaderLocator = $("#example-modal-sizes-title-lg");
        SelenideElement resultsTableLocator = $(".table-responsive");

        //actions
        practiceFormPage.openPage()
                        .setFirstName(firstName)
                        .setLastName(lastName)
                        .setEmail(email)
                        .setGender(gender)
                        .setPhone(phone)
                        .setDateOfBirthByTyping(dateOfBirthDay, dateOfBirthMonthFull, dateOfBirthYear)
                        .setSubject(subjects)
                        .setHobby(hobbies)
                        .setPicture(imgPath)
                        .setCurrAddress(currAddr)
                        .setState(state)
                        .setCity(city);

        //If browser window would be to short scroll will fail
        //$("[id='submit']").scrollIntoView(true);

        //DOES NOT WORK
        //$("[id=currentAddress]").sendKeys((Keys.COMMAND + "-"));
        //$("[id=currentAddress]").sendKeys((Keys.COMMAND + Keys.SUBTRACT));

        // TODO: 11.04.2022 Fix this  $("#submit").isDisplayed() == true while button is outside the screen
        if (!$("#submit").isDisplayed()) {
            Selenide.zoom(0.75);
        }

        $("#submit").click();

        //ASSERTS
        resultsHeaderLocator.shouldHave(text(resultsOkMessage));
        resultsTableLocator.shouldHave(
                text(firstName + ' ' + lastName),
                text(email),
                text(gender.toString()),
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
