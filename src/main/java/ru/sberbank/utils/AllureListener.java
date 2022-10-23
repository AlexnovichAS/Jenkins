package ru.sberbank.utils;

import io.cucumber.plugin.event.EventPublisher;
import io.cucumber.plugin.event.TestStepFinished;
import io.qameta.allure.Allure;
import io.qameta.allure.cucumber5jvm.AllureCucumber5Jvm;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import ru.sberbank.managers.DriverManager;

import static io.cucumber.plugin.event.Status.FAILED;

public class AllureListener extends AllureCucumber5Jvm {
/*
    public static byte[] getScreenshot() {
        return ((TakesScreenshot) DriverManager.getDriverManager().getDriver()).getScreenshotAs(OutputType.BYTES);
    }

    public void setEventPublisher(EventPublisher publisher) {
        publisher.registerHandlerFor(TestStepFinished.class, event -> {
            if (event.getResult().getStatus().is(FAILED)) {
                Allure.getLifecycle().addAttachment("screen", "image/png", "png", getScreenshot());
            }
        });
        super.setEventPublisher(publisher);
    }*/
}
