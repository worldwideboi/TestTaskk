package utils;

import org.apache.commons.lang3.RandomStringUtils;

public class RandomDataCreation {
    public static String generateRandomScreenName() {
        return "user" + RandomStringUtils.randomNumeric(10);
    }

    public static String generateRandomLogin() {
        String randomLetters = RandomStringUtils.randomAlphabetic(13);
        String randomDigits = RandomStringUtils.randomNumeric(4);
        return randomLetters + randomDigits;
    }

    public static String generateRandomPassword() {
        String randomLetters = RandomStringUtils.randomAlphabetic(10);
        String randomDigits = RandomStringUtils.randomNumeric(4);
        return randomLetters + randomDigits;
    }
}
