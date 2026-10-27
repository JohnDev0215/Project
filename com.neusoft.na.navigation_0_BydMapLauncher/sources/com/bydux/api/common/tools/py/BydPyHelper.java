package com.bydux.api.common.tools.py;

import android.text.TextUtils;
import cerence.ark.assistant.core.dcc.DccConstant;
import com.bydux.api.common.tools.log.BydLogHelper;
import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;

/* JADX INFO: loaded from: classes2.dex */
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

    public static String[] getContactPinyin(String content) {
        BydLogHelper.logV(TAG, "getContactPinyin", "content=" + content);
        if (TextUtils.isEmpty(content)) {
            return null;
        }
        char[] charArray = content.replace("|", "").toCharArray();
        if (charArray.length == 0) {
            return null;
        }
        String[] strArr = new String[charArray.length + 1];
        String firstNamePy = DccConstant.DCC_SEPARATOR;
        int i = 0;
        while (i < charArray.length) {
            char c = charArray[i];
            int i2 = i + 1;
            strArr[i2] = String.valueOf(c);
            if (c > 128) {
                try {
                    String[] hanyuPinyinStringArray = PinyinHelper.toHanyuPinyinStringArray(c, format);
                    if (hanyuPinyinStringArray != null && hanyuPinyinStringArray.length > 0) {
                        if (i == 0) {
                            firstNamePy = getFirstNamePy(String.valueOf(c));
                        }
                        String str = null;
                        int i3 = 0;
                        for (int i4 = 0; i4 < hanyuPinyinStringArray.length && i3 < MAX_MULTI_PY; i4++) {
                            if (!hanyuPinyinStringArray[i4].equals(str)) {
                                strArr[i2] = strArr[i2] + "|" + hanyuPinyinStringArray[i4];
                                str = hanyuPinyinStringArray[i4];
                                i3++;
                            }
                        }
                    }
                } catch (Exception e) {
                    BydLogHelper.logE(TAG, "getContactPinyin", e.getMessage());
                    e.printStackTrace();
                }
            } else if (i == 0 && ((c >= 'A' && c <= 'Z') || (c >= 'a' && c <= 'z'))) {
                firstNamePy = String.valueOf(c).toLowerCase();
            }
            i = i2;
        }
        strArr[0] = firstNamePy;
        return strArr;
    }

    public static String[] getPinyin(String content) {
        BydLogHelper.logV(TAG, "getPinyin", "content=" + content);
        if (TextUtils.isEmpty(content)) {
            return null;
        }
        char[] charArray = content.replace("|", "").toCharArray();
        if (charArray.length == 0) {
            return null;
        }
        String[] strArr = new String[charArray.length + 1];
        String strSubstring = DccConstant.DCC_SEPARATOR;
        int i = 0;
        while (i < charArray.length) {
            char c = charArray[i];
            int i2 = i + 1;
            strArr[i2] = String.valueOf(c);
            if (c > 128) {
                try {
                    String[] hanyuPinyinStringArray = PinyinHelper.toHanyuPinyinStringArray(c, format);
                    if (hanyuPinyinStringArray != null && hanyuPinyinStringArray.length > 0) {
                        if (i == 0 && hanyuPinyinStringArray[0].length() > 0) {
                            strSubstring = hanyuPinyinStringArray[0].substring(0, 1);
                        }
                        String str = null;
                        int i3 = 0;
                        for (int i4 = 0; i4 < hanyuPinyinStringArray.length && i3 < MAX_MULTI_PY; i4++) {
                            if (!hanyuPinyinStringArray[i4].equals(str)) {
                                strArr[i2] = strArr[i2] + "|" + hanyuPinyinStringArray[i4];
                                str = hanyuPinyinStringArray[i4];
                                i3++;
                            }
                        }
                    }
                } catch (Exception e) {
                    BydLogHelper.logE(TAG, "getPinyin", e.getMessage());
                    e.printStackTrace();
                }
            } else if (i == 0 && ((c >= 'A' && c <= 'Z') || (c >= 'a' && c <= 'z'))) {
                strSubstring = String.valueOf(c).toLowerCase();
            }
            i = i2;
        }
        strArr[0] = strSubstring;
        return strArr;
    }

    private static String getFirstNamePy(String firstName) {
        if (firstName == null) {
            return null;
        }
        String str = "q";
        if (!firstName.equals("覃")) {
            if (!firstName.equals("曾")) {
                if (!firstName.equals("解")) {
                    if (firstName.equals("重")) {
                        return "c";
                    }
                    if (firstName.equals("区")) {
                        return "o";
                    }
                    if (!firstName.equals("仇")) {
                        if (firstName.equals("秘")) {
                            return "b";
                        }
                        if (!firstName.equals("冼")) {
                            str = "s";
                            if (!firstName.equals("单") && !firstName.equals("折")) {
                                if (firstName.equals("朴")) {
                                    return "p";
                                }
                                if (!firstName.equals("翟") && !firstName.equals("查")) {
                                    return null;
                                }
                            }
                        }
                    }
                }
                return "x";
            }
            return "z";
        }
        return str;
    }
}
