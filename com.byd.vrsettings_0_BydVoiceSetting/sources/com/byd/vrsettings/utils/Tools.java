package com.byd.vrsettings.utils;

import android.content.Context;
import android.content.pm.PackageManager;
import android.icu.math.BigDecimal;
import android.icu.text.DecimalFormat;
import android.os.StatFs;
import com.byd.vrsettings.BydVoiceSettingApplication;
import com.byd.vrsettings.service.OtaDataManager;
import com.chad.library.BuildConfig;
import java.io.File;
import java.util.UUID;

/* JADX INFO: loaded from: classes.dex */
public class Tools {
    private static String TAG = "Tools";

    public static String percentString(long j, long j2) {
        if (j2 == 0) {
            throw new RuntimeException("Divisor cannot be zero");
        }
        return new DecimalFormat("#%").format(new BigDecimal(j / j2).setScale(2, 4).doubleValue());
    }

    public static int percentInt(long j, long j2) {
        if (j2 == 0) {
            return 0;
        }
        return (int) (new BigDecimal(j / j2).setScale(2, 4).doubleValue() * 100.0d);
    }

    public static String getUUID() {
        return UUID.randomUUID().toString().replace("-", BuildConfig.FLAVOR);
    }

    public static String getVersionName() {
        try {
            return BydVoiceSettingApplication.getContext().getPackageManager().getPackageInfo(BydVoiceSettingApplication.getContext().getPackageName(), 0).versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return BuildConfig.FLAVOR;
        }
    }

    public static long getVersionCode(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).getLongVersionCode();
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return 0L;
        }
    }

    public static boolean compareVersion(String str, String str2) {
        LogUtils.i("compareVersion", "oldVersion==" + str);
        LogUtils.i("compareVersion", "newVersion==" + str2);
        try {
            if (str.contains(".")) {
                String[] strArrSplit = str.split("\\.");
                String str3 = strArrSplit[1];
                int length = strArrSplit.length;
                String str4 = BuildConfig.FLAVOR;
                String str5 = length > 2 ? strArrSplit[2] : BuildConfig.FLAVOR;
                String str6 = strArrSplit.length > 3 ? strArrSplit[3] : BuildConfig.FLAVOR;
                if (str2.contains(".")) {
                    String[] strArrSplit2 = str2.split("\\.");
                    String str7 = strArrSplit2[1];
                    String str8 = strArrSplit2.length > 2 ? strArrSplit2[2] : BuildConfig.FLAVOR;
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
                            if (str4.substring(str4.length() - 1).compareTo(str6.substring(str6.length() - 1)) > 0) {
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

    public static boolean compareNewVersionIsHigher(String str, String str2) {
        LogUtils.i("compareNewLetterOfAndEquallOld", "oldVersion==" + str);
        LogUtils.i("compareNewLetterOfAndEquallOld", "newVersion==" + str2);
        try {
            if (str.contains(".")) {
                String[] strArrSplit = str.split("\\.");
                String str3 = strArrSplit[1];
                int length = strArrSplit.length;
                String str4 = BuildConfig.FLAVOR;
                String str5 = length > 2 ? strArrSplit[2] : BuildConfig.FLAVOR;
                String str6 = strArrSplit.length > 3 ? strArrSplit[3] : BuildConfig.FLAVOR;
                if (str2.contains(".")) {
                    String[] strArrSplit2 = str2.split("\\.");
                    String str7 = strArrSplit2[1];
                    String str8 = strArrSplit2.length > 2 ? strArrSplit2[2] : BuildConfig.FLAVOR;
                    if (strArrSplit2.length > 3) {
                        str4 = strArrSplit2[3];
                    }
                    if (Integer.valueOf(str7).intValue() > Integer.valueOf(str3).intValue()) {
                        return true;
                    }
                    if (Integer.valueOf(str7).intValue() < Integer.valueOf(str3).intValue()) {
                        return false;
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

    public static String getDataSize(String str) {
        if (str == null) {
            return BuildConfig.FLAVOR;
        }
        long jLongValue = Long.valueOf(str).longValue();
        if (jLongValue < 0) {
            jLongValue = 0;
        }
        DecimalFormat decimalFormat = new DecimalFormat("####.0");
        if (jLongValue < 1024) {
            return jLongValue + "B";
        }
        if (jLongValue < 1048576) {
            return ((int) (jLongValue / 1024.0f)) + "K";
        }
        if (jLongValue < 1073741824) {
            return ((int) ((jLongValue / 1024.0f) / 1024.0f)) + "M";
        }
        return jLongValue < 0 ? decimalFormat.format(((jLongValue / 1024.0f) / 1024.0f) / 1024.0f) + "G" : "size: error";
    }

    public static String getDataSizeValue(String str) {
        if (str == null) {
            return BuildConfig.FLAVOR;
        }
        BigDecimal bigDecimal = new BigDecimal(str);
        BigDecimal bigDecimal2 = new BigDecimal(1024.0d);
        BigDecimal bigDecimal3 = new BigDecimal(1048576.0d);
        BigDecimal bigDecimal4 = new BigDecimal(1.073741824E9d);
        BigDecimal bigDecimal5 = new BigDecimal(1.099511627776E12d);
        if (bigDecimal.compareTo(bigDecimal2) == -1) {
            return bigDecimal.toString() + "B";
        }
        if (bigDecimal.compareTo(bigDecimal3) == -1) {
            return bigDecimal.divide(bigDecimal2).toString() + "K";
        }
        if (bigDecimal.compareTo(bigDecimal4) == -1) {
            return bigDecimal.divide(bigDecimal3).toString() + "M";
        }
        return bigDecimal.compareTo(bigDecimal5) == -1 ? bigDecimal.divide(bigDecimal4).toString() + "G" : "error";
    }

    public static long getAvailableSize() {
        String str;
        if (OtaDataManager.isSpaceSimple) {
            return 1024L;
        }
        if (!new File("/navi/BYDAssistant_CR/").exists()) {
            RLog.e(TAG, "navi is not exist");
        }
        if (new File("/vr_ext/BYDAssistant_CR/").exists()) {
            str = "/vr_ext";
        } else {
            RLog.e(TAG, "vr_ext is not exist");
            str = "/data";
        }
        RLog.e(TAG, "storage_path：".concat(str));
        File file = new File(str);
        if (!file.exists()) {
            RLog.e(TAG, "!path.exists()");
            return 0L;
        }
        StatFs statFs = new StatFs(file.getPath());
        return statFs.getAvailableBlocksLong() * statFs.getBlockSizeLong();
    }

    public static String getBaseProfilePath() {
        return new File("/vr_ext").exists() ? "/vr_ext" : BuildConfig.FLAVOR;
    }
}
