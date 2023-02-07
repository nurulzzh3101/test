package com.latihan.test.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FormatUtil {

    private FormatUtil(){

    }

    public static boolean isPasswordFormat(String password){
        String regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[!@#$%^&*.,/?;:']).{8,}$";
        return Pattern.matches(regexp, password);
    }

    public static boolean isMobilePhoneNumberFormat(String str) {
        if (str.length() >= 10 && str.length() <= 15 && str.startsWith("+62")) {
            return true;
        }
        else if (str.length() >=10 && str.length() <=14 && str.startsWith("62")){
            return true;
        }
        else if (str.length() >=10 && str.length() <=13 && str.startsWith("0")){
            return true;
        }
        else{
            return false;
        }
    }

    public static boolean isIdCardNumberFormat(String str) {
        if (str == null) return false;
        String regex = "^\\d{0,16}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(str);
        return matcher.matches();
    }

    public static boolean isEmailFormat(String str) {
        String regex = "^[\\w-.]+@[\\w-.]+[\\w-]{2,4}$";

        Pattern pattern = Pattern.compile(regex);
        return pattern.matcher(str).matches();
    }
}
