package com.github.mattnicee7.util;

import lombok.experimental.UtilityClass;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

@UtilityClass
public class CpfChecker {

    // https://github.com/mattnicee7/MattLib/blob/master/misc/src/main/java/com/github/mattnicee7/mattlib/document/impl/CPFChecker.java

    private static final Pattern ONLY_NUMBERS_PATTERN = Pattern.compile("(\\d{11})");
    private static final Pattern SEPARATE_NUMBERS_PATTERN = Pattern.compile("(\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2})");

    public boolean check(String string) {
        if (!ONLY_NUMBERS_PATTERN.matcher(string).matches() && !SEPARATE_NUMBERS_PATTERN.matcher(string).matches())
            return false;

        final List<Integer> verificationCodes = new ArrayList<>();
        final List<Integer> cpfCode = new ArrayList<>();
        final String[] cpfFullSplit = string.replace("-", "")
                .replace(".", "")
                .split("");

        for (int i = 0; i < 9; i++)
            cpfCode.add(Integer.parseInt(cpfFullSplit[i]));

        for (int i = 9; i < 11; i++)
            verificationCodes.add(Integer.parseInt(cpfFullSplit[i]));

        return verificationCodesMatches(cpfCode, verificationCodes);
    }

    private int getVerificationCode(int multiplier, List<Integer> cpfCode) {
        double result = 0.0;
        for (Integer code : cpfCode) {
            result += (code * multiplier--);
        }

        int verificationCode = (result % 11 < 2) ? 0 : (int) Math.round(11 - (result % 11));
        cpfCode.add(verificationCode);

        return verificationCode;
    }

    private boolean verificationCodesMatches(List<Integer> cpfCode, List<Integer> verificationCodes) {
        return getVerificationCode(10, cpfCode) == verificationCodes.get(0) &&
                getVerificationCode(11, cpfCode) == verificationCodes.get(1);
    }

}
