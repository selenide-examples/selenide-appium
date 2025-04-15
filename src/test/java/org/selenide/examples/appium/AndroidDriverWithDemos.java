package org.selenide.examples.appium;

import com.codeborne.selenide.WebDriverProvider;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import static org.apache.commons.io.FileUtils.copyInputStreamToFile;

public class AndroidDriverWithDemos implements WebDriverProvider {
    @Override
    public WebDriver createDriver(Capabilities capabilities) {
        File app = downloadApk();

        UiAutomator2Options options = new UiAutomator2Options();
        options.merge(capabilities);
        options.setApp(app.getAbsolutePath());
        options.setAppPackage("io.appium.android.apis");
        options.setAppActivity(".ApiDemos");

        try {
            return new AndroidDriver(new URL("http://127.0.0.1:4723"), options);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    private File downloadApk() {
        File apk = new File("build/ApiDemos-debug.apk");
        if (!apk.exists()) {
            String url = "https://github.com/appium/sample-code/blob/master/sample-code/apps/ApiDemos/bin/ApiDemos-debug.apk?raw=true";
            try (InputStream in = new URL(url).openStream()) {
                copyInputStreamToFile(in, apk);
            }
            catch (IOException e) {
                throw new AssertionError("Failed to download apk", e);
            }
        }
        return apk;
    }
}
