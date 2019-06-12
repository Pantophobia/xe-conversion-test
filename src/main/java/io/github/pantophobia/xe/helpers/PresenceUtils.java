package io.github.pantophobia.xe.helpers;

import org.openqa.selenium.WebElement;

public class PresenceUtils {

    public static boolean isPresent(WebElement element) {
        try {
            return element.isDisplayed();
        } catch (Exception ex) {
            return false;
        }
    }
}
