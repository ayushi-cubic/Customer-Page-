package com.example.utils;

import java.util.Random;

import org.apache.commons.lang3.RandomStringUtils;

import com.github.javafaker.Faker;

/**
 * Utility class for generating random test data
 * Used to create realistic and varied test data for form fields
 */
public class RandomDataGenerator {

    private static final Faker faker = new Faker();
    private static final Random random = new Random();

    /**
     * Generate random alphanumeric string
     * @param length Length of the string
     * @return Random alphanumeric string
     */
    public static String generateAlphanumeric(int length) {
        return RandomStringUtils.randomAlphanumeric(length);
    }

    /**
     * Generate random alphabetic string
     * @param length Length of the string
     * @return Random alphabetic string
     */
    public static String generateAlphabetic(int length) {
        return RandomStringUtils.randomAlphabetic(length);
    }

    /**
     * Generate random numeric string
     * @param length Length of the string
     * @return Random numeric string
     */
    public static String generateNumeric(int length) {
        return RandomStringUtils.randomNumeric(length);
    }

    /**
     * Generate random customer number (alphanumeric, 8 characters)
     * @return Random customer number
     */
    public static String generateCustomerNumber() {
        return "CUST" + generateAlphanumeric(8).toUpperCase();
    }

    /**
     * Generate random customer name
     * @return Random full name
     */
    public static String generateCustomerName() {
        return faker.name().fullName();
    }

    /**
     * Generate random father name
     * @return Random male name
     */
    public static String generateFatherName() {
        return faker.name().firstName() + " " + faker.name().lastName();
    }

    /**
     * Generate random phone number (10 digits)
     * @return Random 10-digit phone number
     */
    public static String generatePhoneNumber() {
        return generateNumeric(10);
    }

    /**
     * Generate random mobile number (10 digits starting with 7-9)
     * @return Random 10-digit mobile number
     */
    public static String generateMobileNumber() {
        String firstDigit = String.valueOf(random.nextInt(3) + 7); // 7, 8, or 9
        return firstDigit + generateNumeric(9);
    }

    /**
     * Generate random email address
     * @return Random email address
     */
    public static String generateEmail() {
        return faker.internet().emailAddress();
    }

    /**
     * Generate random Aadhaar number (12 digits starting with 2-9)
     * @return Random 12-digit Aadhaar number
     */
    public static String generateAadhaarNumber() {
        String firstDigit = String.valueOf(random.nextInt(8) + 2); // 2 to 9
        return firstDigit + generateNumeric(11);
    }

    /**
     * Generate random address line
     * @return Random street address
     */
    public static String generateAddress() {
        return faker.address().streetAddress();
    }

    /**
     * Generate random secondary address
     * @return Random secondary address
     */
    public static String generateSecondaryAddress() {
        return faker.address().secondaryAddress();
    }

    /**
     * Generate random remarks or comments
     * @return Random sentence
     */
    public static String generateRemarks() {
        return faker.lorem().sentence(10);
    }

    /**
     * Generate random text with specified word count
     * @param wordCount Number of words
     * @return Random text
     */
    public static String generateRandomText(int wordCount) {
        return faker.lorem().sentence(wordCount);
    }

    /**
     * Select random index from dropdown (1 to maxOptions)
     * @param maxOptions Maximum number of options in dropdown
     * @return Random index (1-based)
     */
    public static int generateRandomDropdownIndex(int maxOptions) {
        return random.nextInt(maxOptions) + 1;
    }

    /**
     * Generate random boolean value
     * @return Random boolean
     */
    public static boolean generateRandomBoolean() {
        return random.nextBoolean();
    }
}
