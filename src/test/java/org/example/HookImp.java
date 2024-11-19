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
            hubUrl = new URL("https://devicepark-appium-gw-service.testinium.io/wd/hub");
            logger.info("----------BeforeScenario--------------");
            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setCapability("platformName", "ANDROID");
            capabilities.setCapability("udid", "RZ8M83DJXXB");
            capabilities.setCapability("automationName", "UiAutomator2");

            capabilities.setCapability("appPackage","com.gratis.android");
            capabilities.setCapability("appActivity", "com.app.gratis.ui.splash.SplashActivity");

            HashMap<String, Object> deviceParkOptions = new HashMap<>();
            deviceParkOptions.put("sessionId", "3ec7c273-f575-4777-a1a6-ab3d19f90f71");
            deviceParkOptions.put("appiumVersion", "2.5.4");
            capabilities.setCapability("dp:options", deviceParkOptions);

            androidDriver = new AndroidDriver(hubUrl, capabilities);

            AndroidBatteryInfo info= androidDriver.getBatteryInfo();
            logger.info(String.valueOf(info.getLevel()));
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }
}