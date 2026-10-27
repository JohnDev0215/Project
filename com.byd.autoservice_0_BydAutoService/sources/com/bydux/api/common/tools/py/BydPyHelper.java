package com.bydux.api.common.tools.py;

import android.text.TextUtils;
import com.bydux.api.common.tools.log.BydLogHelper;
import y2.d;
import z2.a;
import z2.b;
import z2.c;

/* JADX INFO: loaded from: classes.dex */
public class BydPyHelper {
    private static int MAX_MULTI_PY = 0;
    private static final String TAG = "BydPyHelper";
    private static b format;

    static {
        b bVar = new b();
        format = bVar;
        MAX_MULTI_PY = 2;
        bVar.e(a.f6133b);
        format.f(c.f6139b);
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
        int i3 = 0;
        while (i3 < charArray.length) {
            char c3 = charArray[i3];
            int i4 = i3 + 1;
            strArr[i4] = String.valueOf(c3);
            if (c3 > 128) {
                try {
                    String[] strArrC = d.c(c3, format);
                    if (strArrC != null && strArrC.length > 0) {
                        if (i3 == 0) {
                            firstNamePy = getFirstNamePy(String.valueOf(c3));
                        }
                        String str2 = null;
                        int i5 = 0;
                        for (int i6 = 0; i6 < strArrC.length && i5 < MAX_MULTI_PY; i6++) {
                            if (!strArrC[i6].equals(str2)) {
                                strArr[i4] = strArr[i4] + "|" + strArrC[i6];
                                str2 = strArrC[i6];
                                i5++;
                            }
                        }
                    }
                } catch (Exception e3) {
                    BydLogHelper.logE(TAG, "getContactPinyin", e3.getMessage());
                    e3.printStackTrace();
                }
            } else if (i3 == 0 && ((c3 >= 'A' && c3 <= 'Z') || (c3 >= 'a' && c3 <= 'z'))) {
                firstNamePy = String.valueOf(c3).toLowerCase();
            }
            i3 = i4;
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
        int i3 = 0;
        while (i3 < charArray.length) {
            char c3 = charArray[i3];
            int i4 = i3 + 1;
            strArr[i4] = String.valueOf(c3);
            if (c3 > 128) {
                try {
                    String[] strArrC = d.c(c3, format);
                    if (strArrC != null && strArrC.length > 0) {
                        if (i3 == 0 && strArrC[0].length() > 0) {
                            strSubstring = strArrC[0].substring(0, 1);
                        }
                        String str2 = null;
                        int i5 = 0;
                        for (int i6 = 0; i6 < strArrC.length && i5 < MAX_MULTI_PY; i6++) {
                            if (!strArrC[i6].equals(str2)) {
                                strArr[i4] = strArr[i4] + "|" + strArrC[i6];
                                str2 = strArrC[i6];
                                i5++;
                            }
                        }
                    }
                } catch (Exception e3) {
                    BydLogHelper.logE(TAG, "getPinyin", e3.getMessage());
                    e3.printStackTrace();
                }
            } else if (i3 == 0 && ((c3 >= 'A' && c3 <= 'Z') || (c3 >= 'a' && c3 <= 'z'))) {
                strSubstring = String.valueOf(c3).toLowerCase();
            }
            i3 = i4;
        }
        strArr[0] = strSubstring;
        return strArr;
    }
}
