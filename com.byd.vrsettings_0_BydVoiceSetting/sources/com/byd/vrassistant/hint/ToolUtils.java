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
                int length = strArrSplit.length;
                String str4 = com.chad.library.BuildConfig.FLAVOR;
                String str5 = length > 2 ? strArrSplit[2] : com.chad.library.BuildConfig.FLAVOR;
                String str6 = strArrSplit.length > 3 ? strArrSplit[3] : com.chad.library.BuildConfig.FLAVOR;
                if (str2.contains(".")) {
                    String[] strArrSplit2 = str2.split("\\.");
                    String str7 = strArrSplit2[1];
                    String str8 = strArrSplit2.length > 2 ? strArrSplit2[2] : com.chad.library.BuildConfig.FLAVOR;
                    if (strArrSplit2.length > 3) {
                        str4 = strArrSplit2[3];
                    }
                    if (Integer.valueOf(str8).intValue() > Integer.valueOf(str5).intValue()) {
                        return true;
                    }
                    if (Integer.valueOf(str8) == Integer.valueOf(str5)) {
                        int iIntValue = Integer.valueOf(str6.substring(0, str6.length() - 1)).intValue();
                        int iIntValue2 = Integer.valueOf(str4.substring(0, str4.length() - 1)).intValue();
                        if (iIntValue2 > iIntValue) {
                            return true;
                        }
                        if (iIntValue2 == iIntValue) {
                            if (str4.substring(str4.length() - 1).compareTo(str6.substring(str6.length() - 1)) >= 0) {
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
