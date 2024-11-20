package org.example;

import com.thoughtworks.gauge.BeforeScenario;
import io.appium.java_client.android.AndroidBatteryInfo;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URL;
import java.util.HashMap;

public class HookImp {

    private Logger logger = LoggerFactory.getLogger(getClass());
    protected static AndroidDriver androidDriver;
    URL hubUrl;

    @BeforeScenario
    public void beforeScenario(){
        try {

            System.out.println("hubURL: " + System.getenv("hubURL"));
            System.out.println("platform: " + System.getenv("platform"));
            System.out.println("udid: " + System.getenv("udid"));
            System.out.println("sessionId: " + System.getenv("sessionId"));
            System.out.println("appiumVersion: " + System.getenv("appiumVersion"));
            
            hubUrl = new URL(System.getenv("hubURL"));
            logger.info("----------BeforeScenario--------------");
            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setCapability("platformName", System.getenv("platform"));
            capabilities.setCapability("udid", System.getenv("udid"));
            capabilities.setCapability("automationName", "UiAutomator2");

            capabilities.setCapability("appPackage","com.gratis.android");
            capabilities.setCapability("appActivity", "com.app.gratis.ui.splash.SplashActivity");

            HashMap<String, Object> deviceParkOptions = new HashMap<>();
            deviceParkOptions.put("sessionId", System.getenv("sessionId"));
            deviceParkOptions.put("appiumVersion", System.getenv("appiumVersion"));
            capabilities.setCapability("dp:options", deviceParkOptions);

            androidDriver = new AndroidDriver(hubUrl, capabilities);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }
}
