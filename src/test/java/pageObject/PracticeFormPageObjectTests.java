package pageObject;

import com.codeborne.selenide.Configuration;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.Month;
import java.time.format.TextStyle;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

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
        System.out.println("dateOfBirth= " + dateOfBirth);
        final String dateOfBirthDay = String.valueOf(calendar.get(Calendar.DAY_OF_MONTH));
        System.out.println("dateOfBirthDay= " + dateOfBirthDay);
                                //starts from 0
        final int bdMonth = calendar.get(Calendar.MONTH); //needed only to get dateOfBirthMonthFull
        System.out.println("bdMonth= " + bdMonth + "(starts from 0)");
                                                  //starts from 1
        final String dateOfBirthMonthFull = Month.of(bdMonth+1).getDisplayName(TextStyle.FULL, Locale.US);  //"December"
        System.out.println("dateOfBirthMonthFull= " + dateOfBirthMonthFull);
        final String dateOfBirthYear = String.valueOf(calendar.get(Calendar.YEAR)); //"2000"

        //should be like this "02 December,2000"
        String dateOfBirthDay2Digits = (calendar.get(Calendar.DAY_OF_MONTH) > 10)
                ? dateOfBirthDay
                : "0" + dateOfBirthDay;
        final String dateOfBirthExpect = dateOfBirthDay2Digits + " " + dateOfBirthMonthFull + "," + dateOfBirthYear;
        System.out.println("dateOfBirthExpect= "+ dateOfBirthExpect);

        final String subjects = "Maths";

        // TODO: 11.04.2022 change to enum like Gender {Sports, Reading, Music}
        final String hobbies = "Sports";
        final String imgPath = "image.jpeg";
        final String currAddr = faker.address().fullAddress();
        final String state = "NCR";
        final String city = "Delhi";

        final String resultsOkMessage =  "Thanks for submitting the form";

        //actions
        practiceFormPage.openPage()
                        .setFirstName(firstName)
                        .setLastName(lastName)
                        .setEmail(email)
                        .setGender(gender)
                        .setPhone(phone)
                        .setDateOfBirthByClicking(dateOfBirthDay, dateOfBirthMonthFull, dateOfBirthYear)
                        .setSubject(subjects)
                        .setHobby(hobbies)
                        .setPicture(imgPath)
                        .setCurrAddress(currAddr)
                        .setState(state)
                        .setCity(city)
                        .submitForm()
                        //ASSERTS
                        .checkTableHeader(resultsOkMessage)
                        .checkTableResult(firstName + ' ' + lastName)
                        .checkTableResult(gender.toString())
                        .checkTableResult(dateOfBirthExpect)
                        .checkTableResult(subjects)
                        .checkTableResult(hobbies)
                        .checkTableResult(imgPath)
                        .checkTableResult(currAddr)
                        .checkTableResult(state + ' ' + city);
    }
}
