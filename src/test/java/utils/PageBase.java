package utils;

import org.openqa.selenium.remote.RemoteWebDriver;

public abstract class PageBase {
    protected RemoteWebDriver remoteWebDriver;
    protected Boolean passed;
    private IScreenshotTaker screenshotTaker;

    public PageBase(RemoteWebDriver remoteWebDriver) {
        this.remoteWebDriver = remoteWebDriver;
    }

    public Boolean getPassed() {
        return passed;
    }

    public void setScreenshotTaker(IScreenshotTaker screenshotTaker) {
        this.screenshotTaker = screenshotTaker;
    }

    public PageBase screenshot() {
        this.screenshotTaker.screenshot();
        return this;
    }
}