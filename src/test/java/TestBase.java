import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import config.CredentialsConfig;
import helpers.Attach;
import io.qameta.allure.selenide.AllureSelenide;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.remote.DesiredCapabilities;

import static com.codeborne.selenide.Selenide.executeJavaScript;

public class TestBase {
    @BeforeAll
    static void setUp() {
        CredentialsConfig config = ConfigFactory.create(CredentialsConfig.class);

        //Configuration.holdBrowserOpen = true;
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.browserSize = "1920x1080";
        SelenideLogger.addListener("allure", new AllureSelenide());
        //Configuration.remote = "https://user1:1234@selenoid.autotests.cloud/wd/hub";
        String remoteString = "https://" + config.remoteUser() + ":" + config.remotePassword() + "@" + config.remoteUrl();
        System.out.println("Connecting to " + remoteString);
        Configuration.remote = remoteString;

        //enable video
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("enableVNC", true);
        capabilities.setCapability("enableVideo", true);
        Configuration.browserCapabilities = capabilities;
    }

    @AfterEach
    void addAttachments() {
        System.out.println("AfterEach");
        Attach.screenshotAs("Last screenshot");
        Attach.pageSource();
        Attach.browserConsoleLogs();
        Attach.addVideo();

        Selenide.closeWebDriver();
    }


}
