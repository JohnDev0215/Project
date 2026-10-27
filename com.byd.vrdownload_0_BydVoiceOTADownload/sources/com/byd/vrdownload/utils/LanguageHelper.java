package com.byd.vrdownload.utils;

import com.byd.vrdownload.model.LanguageUpdate;

/* JADX INFO: loaded from: classes.dex */
public class LanguageHelper {
    public static boolean compareVersion(String str, String str2) {
        LogUtils.i("compareVersion", "oldVersion==" + str);
        LogUtils.i("compareVersion", "newVersion==" + str2);
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
                            if (str8.substring(str8.length() - 1).compareTo(str5.substring(str5.length() - 1)) > 0) {
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

    public static LanguageUpdate compareVersion(LanguageUpdate languageUpdate, LanguageUpdate languageUpdate2) {
        String latestPkgVersion = languageUpdate.getLatestPkgVersion();
        String latestPkgVersion2 = languageUpdate2.getLatestPkgVersion();
        LogUtils.i("compareVersion", "oldVersion==" + latestPkgVersion);
        LogUtils.i("compareVersion", "newVersion==" + latestPkgVersion2);
        try {
            if (latestPkgVersion.contains(".")) {
                String[] strArrSplit = latestPkgVersion.split("\\.");
                String str = strArrSplit[1];
                String str2 = strArrSplit.length > 2 ? strArrSplit[2] : "";
                String str3 = strArrSplit.length > 3 ? strArrSplit[3] : "";
                if (latestPkgVersion2.contains(".")) {
                    String[] strArrSplit2 = latestPkgVersion2.split("\\.");
                    String str4 = strArrSplit2[1];
                    String str5 = strArrSplit2.length > 2 ? strArrSplit2[2] : "";
                    String str6 = strArrSplit2.length > 3 ? strArrSplit2[3] : "";
                    if (Integer.valueOf(str5).intValue() > Integer.valueOf(str2).intValue()) {
                        return languageUpdate2;
                    }
                    if (Integer.valueOf(str5) == Integer.valueOf(str2)) {
                        int iIntValue = Integer.valueOf(str3.substring(0, str3.length() - 1)).intValue();
                        int iIntValue2 = Integer.valueOf(str6.substring(0, str6.length() - 1)).intValue();
                        if (iIntValue2 > iIntValue) {
                            return languageUpdate2;
                        }
                        if (iIntValue2 == iIntValue) {
                            if (str6.substring(str6.length() - 1).compareTo(str3.substring(str3.length() - 1)) > 0) {
                                return languageUpdate2;
                            }
                        }
                    }
                }
            }
        } catch (Exception unused) {
        }
        return languageUpdate;
    }
}
