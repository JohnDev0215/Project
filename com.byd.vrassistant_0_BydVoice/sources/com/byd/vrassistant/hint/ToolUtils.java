package com.byd.vrassistant.hint;

import android.util.Log;

/* JADX INFO: loaded from: classes.dex */
public class ToolUtils {
    private static String TAG = "Tools";

    public static boolean compareNewVersionIsHigher(String str, String str2) {
        Log.i("NEW_compare", "oldVersion==" + str);
        Log.i("NEW_compare", "newVersion==" + str2);
        try {
            if (str.contains(".")) {
                String[] strArrSplit = str.split("\\.");
                String str3 = strArrSplit[1];
                String str4 = strArrSplit.length > 2 ? strArrSplit[2] : "";
                String str5 = strArrSplit.length > 3 ? strArrSplit[3] : "";
                if (str2.contains(".")) {
                    String[] strArrSplit2 = str2.split("\\.");
                    String str6 = strArrSplit2[1];
                    String str7 = strArrSplit2.length > 2 ? strArrSplit2[2] : "";
                    String str8 = strArrSplit2.length > 3 ? strArrSplit2[3] : "";
                    if (Integer.valueOf(str7).intValue() > Integer.valueOf(str4).intValue()) {
                        return true;
                    }
                    if (Integer.valueOf(str7) == Integer.valueOf(str4)) {
                        int iIntValue = Integer.valueOf(str5.substring(0, str5.length() - 1)).intValue();
                        int iIntValue2 = Integer.valueOf(str8.substring(0, str8.length() - 1)).intValue();
                        if (iIntValue2 > iIntValue) {
                            return true;
                        }
                        if (iIntValue2 == iIntValue) {
                            if (str8.substring(str8.length() - 1).compareTo(str5.substring(str5.length() - 1)) >= 0) {
                                return true;
                            }
                        }
                    }
                }
            }
        } catch (Exception unused) {
        }
        return false;
    }
}
