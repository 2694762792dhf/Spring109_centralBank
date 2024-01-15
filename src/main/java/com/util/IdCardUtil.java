package com.util;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Component
public class IdCardUtil {

    // 身份证号码的正则表达式
    private static final String ID_CARD_REGEX =
            "(^[1-9]\\d{5}(18|19|20)\\d{2}(0[1-9]|1[0-2])(0[1-9]|[12][0-9]|3[01])\\d{3}([0-9]|X|x)$)";

    // 验证身份证号码的方法
    public static boolean isValidIdCard(String idCard) {
        return idCard.matches(ID_CARD_REGEX) && calculateCheckCode(idCard);
    }

    // 计算身份证号码的校验位
    private static boolean calculateCheckCode(String idCard) {
        char[] charArray = idCard.toCharArray();
        int[] coefficientArray = {7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2};
        char[] checkCodeArray = {'1', '0', 'X', '9', '8', '7', '6', '5', '4', '3', '2'};

        int sum = 0;
        for (int i = 0; i < 17; i++) {
            sum += Character.getNumericValue(charArray[i]) * coefficientArray[i];
        }

        int remainder = sum % 11;
        char checkCode = checkCodeArray[remainder];

        return charArray[17] == checkCode || (charArray[17] == 'x' && checkCode == 'X');
    }

//    public static void main(String[] args) {
//        String idCard = "152822200102074530"; // 替换为实际的身份证号码
//        if (isValidIdCard(idCard)) {
//            System.out.println("身份证号码合法！");
//        } else {
//            System.out.println("身份证号码不合法！");
//        }
//    }
}
