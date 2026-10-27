package net.sourceforge.pinyin4j;

import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

/* JADX INFO: loaded from: classes.dex */
public class PinyinHelper {
    private PinyinHelper() {
    }

    private static String[] convertToGwoyeuRomatzyhStringArray(char c2) {
        String[] unformattedHanyuPinyinStringArray = getUnformattedHanyuPinyinStringArray(c2);
        if (unformattedHanyuPinyinStringArray == null) {
            return null;
        }
        String[] strArr = new String[unformattedHanyuPinyinStringArray.length];
        for (int i = 0; i < unformattedHanyuPinyinStringArray.length; i++) {
            strArr[i] = GwoyeuRomatzyhTranslator.convertHanyuPinyinToGwoyeuRomatzyh(unformattedHanyuPinyinStringArray[i]);
        }
        return strArr;
    }

    private static String[] convertToTargetPinyinStringArray(char c2, PinyinRomanizationType pinyinRomanizationType) {
        String[] unformattedHanyuPinyinStringArray = getUnformattedHanyuPinyinStringArray(c2);
        if (unformattedHanyuPinyinStringArray == null) {
            return null;
        }
        String[] strArr = new String[unformattedHanyuPinyinStringArray.length];
        for (int i = 0; i < unformattedHanyuPinyinStringArray.length; i++) {
            strArr[i] = PinyinRomanizationTranslator.convertRomanizationSystem(unformattedHanyuPinyinStringArray[i], PinyinRomanizationType.HANYU_PINYIN, pinyinRomanizationType);
        }
        return strArr;
    }

    private static String getFirstHanyuPinyinString(char c2, HanyuPinyinOutputFormat hanyuPinyinOutputFormat) throws BadHanyuPinyinOutputFormatCombination {
        String[] formattedHanyuPinyinStringArray = getFormattedHanyuPinyinStringArray(c2, hanyuPinyinOutputFormat);
        if (formattedHanyuPinyinStringArray == null || formattedHanyuPinyinStringArray.length <= 0) {
            return null;
        }
        return formattedHanyuPinyinStringArray[0];
    }

    private static String[] getFormattedHanyuPinyinStringArray(char c2, HanyuPinyinOutputFormat hanyuPinyinOutputFormat) throws BadHanyuPinyinOutputFormatCombination {
        String[] unformattedHanyuPinyinStringArray = getUnformattedHanyuPinyinStringArray(c2);
        if (unformattedHanyuPinyinStringArray == null) {
            return null;
        }
        for (int i = 0; i < unformattedHanyuPinyinStringArray.length; i++) {
            unformattedHanyuPinyinStringArray[i] = PinyinFormatter.formatHanyuPinyin(unformattedHanyuPinyinStringArray[i], hanyuPinyinOutputFormat);
        }
        return unformattedHanyuPinyinStringArray;
    }

    private static String[] getUnformattedHanyuPinyinStringArray(char c2) {
        return ChineseToPinyinResource.getInstance().getHanyuPinyinStringArray(c2);
    }

    public static String[] toGwoyeuRomatzyhStringArray(char c2) {
        return convertToGwoyeuRomatzyhStringArray(c2);
    }

    public static String toHanyuPinyinString(String str, HanyuPinyinOutputFormat hanyuPinyinOutputFormat, String str2) throws BadHanyuPinyinOutputFormatCombination {
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < str.length(); i++) {
            String firstHanyuPinyinString = getFirstHanyuPinyinString(str.charAt(i), hanyuPinyinOutputFormat);
            if (firstHanyuPinyinString != null) {
                stringBuffer.append(firstHanyuPinyinString);
                if (i != str.length() - 1) {
                    stringBuffer.append(str2);
                }
            } else {
                stringBuffer.append(str.charAt(i));
            }
        }
        return stringBuffer.toString();
    }

    public static String[] toHanyuPinyinStringArray(char c2) {
        return getUnformattedHanyuPinyinStringArray(c2);
    }

    public static String[] toHanyuPinyinStringArray(char c2, HanyuPinyinOutputFormat hanyuPinyinOutputFormat) throws BadHanyuPinyinOutputFormatCombination {
        return getFormattedHanyuPinyinStringArray(c2, hanyuPinyinOutputFormat);
    }

    public static String[] toMPS2PinyinStringArray(char c2) {
        return convertToTargetPinyinStringArray(c2, PinyinRomanizationType.MPS2_PINYIN);
    }

    public static String[] toTongyongPinyinStringArray(char c2) {
        return convertToTargetPinyinStringArray(c2, PinyinRomanizationType.TONGYONG_PINYIN);
    }

    public static String[] toWadeGilesPinyinStringArray(char c2) {
        return convertToTargetPinyinStringArray(c2, PinyinRomanizationType.WADEGILES_PINYIN);
    }

    public static String[] toYalePinyinStringArray(char c2) {
        return convertToTargetPinyinStringArray(c2, PinyinRomanizationType.YALE_PINYIN);
    }
}
