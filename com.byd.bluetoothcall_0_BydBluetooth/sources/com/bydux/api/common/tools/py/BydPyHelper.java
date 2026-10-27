package com.bydux.api.common.tools.py;

import android.text.TextUtils;
import com.bydux.api.common.tools.log.BydLogHelper;
import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;

/* JADX INFO: loaded from: classes.dex */
public class BydPyHelper {
    private static int MAX_MULTI_PY = 0;
    private static final String TAG = "BydPyHelper";
    private static HanyuPinyinOutputFormat format;

    static {
        HanyuPinyinOutputFormat hanyuPinyinOutputFormat = new HanyuPinyinOutputFormat();
        format = hanyuPinyinOutputFormat;
        MAX_MULTI_PY = 2;
        hanyuPinyinOutputFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        format.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
    }

    private BydPyHelper() {
    }

    public static String[] getContactPinyin(String str) {
        BydLogHelper.logV(TAG, "getContactPinyin", "content=" + str);
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        char[] charArray = str.replace("|", "").toCharArray();
        if (charArray.length == 0) {
            return null;
        }
        String[] strArr = new String[charArray.length + 1];
        String firstNamePy = "#";
        int i = 0;
        while (i < charArray.length) {
            char c2 = charArray[i];
            int i2 = i + 1;
            strArr[i2] = String.valueOf(c2);
            if (c2 > 128) {
                try {
                    String[] hanyuPinyinStringArray = PinyinHelper.toHanyuPinyinStringArray(c2, format);
                    if (hanyuPinyinStringArray != null && hanyuPinyinStringArray.length > 0) {
                        if (i == 0) {
                            firstNamePy = getFirstNamePy(String.valueOf(c2));
                        }
                        String str2 = null;
                        int i3 = 0;
                        for (int i4 = 0; i4 < hanyuPinyinStringArray.length && i3 < MAX_MULTI_PY; i4++) {
                            if (!hanyuPinyinStringArray[i4].equals(str2)) {
                                strArr[i2] = strArr[i2] + "|" + hanyuPinyinStringArray[i4];
                                str2 = hanyuPinyinStringArray[i4];
                                i3++;
                            }
                        }
                    }
                } catch (Exception e2) {
                    BydLogHelper.logE(TAG, "getContactPinyin", e2.getMessage());
                    e2.printStackTrace();
                }
            } else if (i == 0 && ((c2 >= 'A' && c2 <= 'Z') || (c2 >= 'a' && c2 <= 'z'))) {
                firstNamePy = String.valueOf(c2).toLowerCase();
            }
            i = i2;
        }
        strArr[0] = firstNamePy;
        return strArr;
    }

    private static String getFirstNamePy(String str) {
        if (str == null) {
            return null;
        }
        if (!str.equals("覃")) {
            if (!str.equals("曾")) {
                if (!str.equals("解")) {
                    if (str.equals("重")) {
                        return "c";
                    }
                    if (str.equals("区")) {
                        return "o";
                    }
                    if (!str.equals("仇")) {
                        if (str.equals("秘")) {
                            return "b";
                        }
                        if (!str.equals("冼")) {
                            if (str.equals("单") || str.equals("折")) {
                                return "s";
                            }
                            if (str.equals("朴")) {
                                return "p";
                            }
                            if (!str.equals("翟") && !str.equals("查")) {
                                return null;
                            }
                        }
                    }
                }
                return "x";
            }
            return "z";
        }
        return "q";
    }

    public static String[] getPinyin(String str) {
        BydLogHelper.logV(TAG, "getPinyin", "content=" + str);
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        char[] charArray = str.replace("|", "").toCharArray();
        if (charArray.length == 0) {
            return null;
        }
        String[] strArr = new String[charArray.length + 1];
        String strSubstring = "#";
        int i = 0;
        while (i < charArray.length) {
            char c2 = charArray[i];
            int i2 = i + 1;
            strArr[i2] = String.valueOf(c2);
            if (c2 > 128) {
                try {
                    String[] hanyuPinyinStringArray = PinyinHelper.toHanyuPinyinStringArray(c2, format);
                    if (hanyuPinyinStringArray != null && hanyuPinyinStringArray.length > 0) {
                        if (i == 0 && hanyuPinyinStringArray[0].length() > 0) {
                            strSubstring = hanyuPinyinStringArray[0].substring(0, 1);
                        }
                        String str2 = null;
                        int i3 = 0;
                        for (int i4 = 0; i4 < hanyuPinyinStringArray.length && i3 < MAX_MULTI_PY; i4++) {
                            if (!hanyuPinyinStringArray[i4].equals(str2)) {
                                strArr[i2] = strArr[i2] + "|" + hanyuPinyinStringArray[i4];
                                str2 = hanyuPinyinStringArray[i4];
                                i3++;
                            }
                        }
                    }
                } catch (Exception e2) {
                    BydLogHelper.logE(TAG, "getPinyin", e2.getMessage());
                    e2.printStackTrace();
                }
            } else if (i == 0 && ((c2 >= 'A' && c2 <= 'Z') || (c2 >= 'a' && c2 <= 'z'))) {
                strSubstring = String.valueOf(c2).toLowerCase();
            }
            i = i2;
        }
        strArr[0] = strSubstring;
        return strArr;
    }
}
