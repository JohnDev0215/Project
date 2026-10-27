package com.bydux.api.common.tools.py;

import android.text.TextUtils;
import com.byd.systemsettings.system.BydPowerManagerDevice;
import com.bydux.api.common.tools.log.BydLogHelper;
import g7.d;
import h7.a;
import h7.b;
import h7.c;

/* JADX INFO: loaded from: classes2.dex */
public class BydPyHelper {
    private static int MAX_MULTI_PY = 0;
    private static final String TAG = "BydPyHelper";
    private static b format;

    static {
        b bVar = new b();
        format = bVar;
        MAX_MULTI_PY = 2;
        bVar.e(a.f5445b);
        format.f(c.f5451b);
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
        String firstNamePy = BydPowerManagerDevice.SEPARATOR_FREQ_BANDS;
        int i8 = 0;
        while (i8 < charArray.length) {
            char c8 = charArray[i8];
            int i9 = i8 + 1;
            strArr[i9] = String.valueOf(c8);
            if (c8 > 128) {
                try {
                    String[] strArrC = d.c(c8, format);
                    if (strArrC != null && strArrC.length > 0) {
                        if (i8 == 0) {
                            firstNamePy = getFirstNamePy(String.valueOf(c8));
                        }
                        String str2 = null;
                        int i10 = 0;
                        for (int i11 = 0; i11 < strArrC.length && i10 < MAX_MULTI_PY; i11++) {
                            if (!strArrC[i11].equals(str2)) {
                                strArr[i9] = strArr[i9] + "|" + strArrC[i11];
                                str2 = strArrC[i11];
                                i10++;
                            }
                        }
                    }
                } catch (Exception e8) {
                    BydLogHelper.logE(TAG, "getContactPinyin", e8.getMessage());
                    e8.printStackTrace();
                }
            } else if (i8 == 0 && ((c8 >= 'A' && c8 <= 'Z') || (c8 >= 'a' && c8 <= 'z'))) {
                firstNamePy = String.valueOf(c8).toLowerCase();
            }
            i8 = i9;
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
        String strSubstring = BydPowerManagerDevice.SEPARATOR_FREQ_BANDS;
        int i8 = 0;
        while (i8 < charArray.length) {
            char c8 = charArray[i8];
            int i9 = i8 + 1;
            strArr[i9] = String.valueOf(c8);
            if (c8 > 128) {
                try {
                    String[] strArrC = d.c(c8, format);
                    if (strArrC != null && strArrC.length > 0) {
                        if (i8 == 0 && strArrC[0].length() > 0) {
                            strSubstring = strArrC[0].substring(0, 1);
                        }
                        String str2 = null;
                        int i10 = 0;
                        for (int i11 = 0; i11 < strArrC.length && i10 < MAX_MULTI_PY; i11++) {
                            if (!strArrC[i11].equals(str2)) {
                                strArr[i9] = strArr[i9] + "|" + strArrC[i11];
                                str2 = strArrC[i11];
                                i10++;
                            }
                        }
                    }
                } catch (Exception e8) {
                    BydLogHelper.logE(TAG, "getPinyin", e8.getMessage());
                    e8.printStackTrace();
                }
            } else if (i8 == 0 && ((c8 >= 'A' && c8 <= 'Z') || (c8 >= 'a' && c8 <= 'z'))) {
                strSubstring = String.valueOf(c8).toLowerCase();
            }
            i8 = i9;
        }
        strArr[0] = strSubstring;
        return strArr;
    }
}
