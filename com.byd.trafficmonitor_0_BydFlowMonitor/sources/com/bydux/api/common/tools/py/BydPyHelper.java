package com.bydux.api.common.tools.py;

import android.text.TextUtils;
import com.bydux.api.common.tools.log.BydLogHelper;
import e.a.a.d;
import e.a.a.f.a;
import e.a.a.f.b;
import e.a.a.f.c;

/* JADX INFO: loaded from: classes.dex */
public class BydPyHelper {
    private static int MAX_MULTI_PY = 0;
    private static final String TAG = "BydPyHelper";
    private static b format;

    static {
        b bVar = new b();
        format = bVar;
        MAX_MULTI_PY = 2;
        bVar.e(a.f4983b);
        format.f(c.f4989b);
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
        int i2 = 0;
        while (i2 < charArray.length) {
            char c2 = charArray[i2];
            int i3 = i2 + 1;
            strArr[i3] = String.valueOf(c2);
            if (c2 > 128) {
                try {
                    String[] strArrC = d.c(c2, format);
                    if (strArrC != null && strArrC.length > 0) {
                        if (i2 == 0) {
                            firstNamePy = getFirstNamePy(String.valueOf(c2));
                        }
                        String str2 = null;
                        int i4 = 0;
                        for (int i5 = 0; i5 < strArrC.length && i4 < MAX_MULTI_PY; i5++) {
                            if (!strArrC[i5].equals(str2)) {
                                strArr[i3] = strArr[i3] + "|" + strArrC[i5];
                                str2 = strArrC[i5];
                                i4++;
                            }
                        }
                    }
                } catch (Exception e2) {
                    BydLogHelper.logE(TAG, "getContactPinyin", e2.getMessage());
                    e2.printStackTrace();
                }
            } else if (i2 == 0 && ((c2 >= 'A' && c2 <= 'Z') || (c2 >= 'a' && c2 <= 'z'))) {
                firstNamePy = String.valueOf(c2).toLowerCase();
            }
            i2 = i3;
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
        int i2 = 0;
        while (i2 < charArray.length) {
            char c2 = charArray[i2];
            int i3 = i2 + 1;
            strArr[i3] = String.valueOf(c2);
            if (c2 > 128) {
                try {
                    String[] strArrC = d.c(c2, format);
                    if (strArrC != null && strArrC.length > 0) {
                        if (i2 == 0 && strArrC[0].length() > 0) {
                            strSubstring = strArrC[0].substring(0, 1);
                        }
                        String str2 = null;
                        int i4 = 0;
                        for (int i5 = 0; i5 < strArrC.length && i4 < MAX_MULTI_PY; i5++) {
                            if (!strArrC[i5].equals(str2)) {
                                strArr[i3] = strArr[i3] + "|" + strArrC[i5];
                                str2 = strArrC[i5];
                                i4++;
                            }
                        }
                    }
                } catch (Exception e2) {
                    BydLogHelper.logE(TAG, "getPinyin", e2.getMessage());
                    e2.printStackTrace();
                }
            } else if (i2 == 0 && ((c2 >= 'A' && c2 <= 'Z') || (c2 >= 'a' && c2 <= 'z'))) {
                strSubstring = String.valueOf(c2).toLowerCase();
            }
            i2 = i3;
        }
        strArr[0] = strSubstring;
        return strArr;
    }
}
