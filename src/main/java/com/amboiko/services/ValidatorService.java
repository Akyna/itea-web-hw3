package com.amboiko.services;

import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class ValidatorService {
    public static final String EMAIL_REGEXP = "^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,4}$";
    public static final String PASSWORD_REGEXP = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,}$";

    public static String getFieldError(String data, String regExp, String messageError) {
        String result;
        result = checkIsNotEmpty(data);
        if (!result.isEmpty()) {
            return result;
        }
        Pattern pattern = Pattern.compile(regExp);
        Matcher matcher = pattern.matcher(data);
        boolean matchFound = matcher.find();
        if (!matchFound) {
            return messageError;
        }

        return "";
    }

    public static String getRetypePasswordError(String password, String data) {
        if (!password.equals(data)) {
            return "Passwords do not match";
        }

        return "";
    }

    public static String checkIsNotEmpty(String data) {
        if (data == null) {
            return "Must be filled";
        }

        if (data.isBlank()) {
            return "Must be not blank";
        }

        return "";
    }

    public static String trimInput(String field) {
        if (field != null) {
            return field.trim();
        }

        return null;
    }

    public static boolean validateFields(String[] strings) {
        return Arrays.stream(strings).allMatch(String::isEmpty);
    }
}
