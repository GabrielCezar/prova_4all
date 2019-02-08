package utils;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.TestName;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

public abstract class TestBase implements IScreenshotTaker {
    private RemoteWebDriver webDriver;
    private DateFormat dateFormat;
    private Integer screenshotCounter;
    private String screenshotPath;

    @Rule
    public TestName testName = new TestName();

    public void close() {
        this.webDriver.close();
        webDriver.quit();
    }

    public TestBase() {

        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        // webDriver.manage().window().maximize();
        this.webDriver = new ChromeDriver(); // temporário
        this.webDriver.manage().window().maximize();
        this.screenshotCounter = 1;
        this.screenshotPath = "c:/test-report/";
        this.dateFormat = new SimpleDateFormat("dd.MM.yyyy-HH.mm.ss");
    }

    public RemoteWebDriver getWebDriver() {
        return this.webDriver;
    }

    public void openUrl(String url) {
        this.webDriver.navigate().to(url);
    }

    public void screenshot() {
        try {
            Thread.sleep(300);
            File screenshot = ((TakesScreenshot) webDriver).getScreenshotAs(OutputType.FILE);

            String filePath = this.screenshotPath;
            filePath += testName.getMethodName() + "-";
            filePath += getFormatedDate();
            filePath += ".png";

            FileUtils.copyFile(screenshot, new File(filePath));
        } catch (Exception e) {

        }
    }

    public void log(String message) {
        // código pra log;;; fazer ainda
        String log = String.format("[%s] %s", getFormatedDate(), message);
    }

    private String getFormatedDate() {
        return dateFormat.format(new Date());
    }
}