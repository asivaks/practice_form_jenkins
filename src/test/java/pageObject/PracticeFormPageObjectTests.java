package pageObject;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class PracticeFormPageObjectTests {

    PracticeFormPage practiceFormPage = new PracticeFormPage();

    @BeforeAll
    static void setUp() {
//        System.out.println("### Before All");
        Configuration.holdBrowserOpen = true;
        Configuration.baseUrl = "https://demoqa.com";
        //Configuration.browserSize = "1920x1080";
        Configuration.browserSize = "1920x900";
    }


    @Test
    void fillFormTestDateType() {
        //VARIABLES
        final String firstName = "Alex";
        final String lastName = "Kosh";
        final String email = "alex@mail.xxx";
        //final String gender = "Male";
        // enum Gender declared in Gender.java
        //final Gender gender = Gender.Male;
        final Gender gender = Gender.values()[new Random().nextInt(Gender.values().length)];
        final String phone = "1234567890";
        final String dateOfBirthType = "03 Apr 2000";
        final String dateOfBirthExpect = "03 April,2000";
        final String subjects = "Maths";
        final String hobbies = "Sports";
        final String imgPath = "image.jpeg";
        final String currAddr = "Street name, House 2, Apartment 222";
        final String state = "NCR";
        final String city = "Delhi";

        final String resultsOkMessage =  "Thanks for submitting the form";

        //locators
        SelenideElement resultsHeaderLocator = $("#example-modal-sizes-title-lg");
        SelenideElement resultsTableLocator = $(".table-responsive");


        practiceFormPage.openPage()
                        .setFirstName(firstName)
                        .setLastName(firstName)
                        .setEmail(email)
                        .setGender(gender)
                        .setPhone(phone)
                        .setDateOfBirthByTyping(dateOfBirthType)
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
        // TODO: 11.04.2022 Fix this 
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
