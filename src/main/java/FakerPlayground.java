import com.github.javafaker.Faker;
import java.time.Month;
import java.time.format.TextStyle;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class FakerPlayground {

    public static void main(String args[]) {
        Faker faker = new Faker();
        Calendar calendar = Calendar.getInstance();  //get day, month etc from date  https://www.baeldung.com/java-year-month-day

        Date bd = faker.date().birthday();
        calendar.setTime(bd);

        System.out.println("Full date: " + bd);
        int bdDay = calendar.get(Calendar.DAY_OF_MONTH);
        int bdMonth = calendar.get(Calendar.MONTH);
        int bdYear = calendar.get(Calendar.YEAR);
        String bdMonthShortName = Month.of(bdMonth).getDisplayName(TextStyle.SHORT, Locale.US);
        String bdMonthFullName = Month.of(bdMonth).getDisplayName(TextStyle.FULL, Locale.US);

        System.out.println("day: " + bdDay);
        System.out.println("month: " + bdMonth);
        System.out.println("year: " + bdYear);
        System.out.println(bdMonthShortName);
        System.out.println(bdMonthFullName);

    }
}
