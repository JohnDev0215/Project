package com.blankj.utilcode.util;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.webkit.WebView;

/* JADX INFO: loaded from: classes4.dex */
public final class PhoneUtils {
    private PhoneUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    public static boolean isPhone() {
        return getTelephonyManager().getPhoneType() != 0;
    }

    public static String getDeviceId() {
        if (Build.VERSION.SDK_INT >= 29) {
            return "";
        }
        TelephonyManager telephonyManager = getTelephonyManager();
        String deviceId = telephonyManager.getDeviceId();
        if (!TextUtils.isEmpty(deviceId)) {
            return deviceId;
        }
        if (Build.VERSION.SDK_INT < 26) {
            return "";
        }
        String imei = telephonyManager.getImei();
        if (!TextUtils.isEmpty(imei)) {
            return imei;
        }
        String meid = telephonyManager.getMeid();
        return TextUtils.isEmpty(meid) ? "" : meid;
    }

    public static String getSerial() {
        return Build.VERSION.SDK_INT >= 26 ? Build.getSerial() : Build.SERIAL;
    }

    public static String getIMEI() {
        return getImeiOrMeid(true);
    }

    public static String getMEID() {
        return getImeiOrMeid(false);
    }

    /* JADX WARN: Removed duplicated region for block: B:56:0x00c0 A[PHI: r2
  0x00c0: PHI (r2v9 java.lang.String) = (r2v7 java.lang.String), (r2v7 java.lang.String), (r2v11 java.lang.String), (r2v11 java.lang.String) binds: [B:52:0x00b7, B:54:0x00bd, B:44:0x00a5, B:46:0x00ab] A[DONT_GENERATE, DONT_INLINE]] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.lang.String getImeiOrMeid(boolean r12) {
        /*
            Method dump skipped, instruction units count: 223
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.blankj.utilcode.util.PhoneUtils.getImeiOrMeid(boolean):java.lang.String");
    }

    private static String getMinOne(String str, String str2) {
        boolean zIsEmpty = TextUtils.isEmpty(str);
        boolean zIsEmpty2 = TextUtils.isEmpty(str2);
        return (zIsEmpty && zIsEmpty2) ? "" : (zIsEmpty || zIsEmpty2) ? !zIsEmpty ? str : str2 : str.compareTo(str2) <= 0 ? str : str2;
    }

    private static String getSystemPropertyByReflect(String str) {
        try {
            Class<?> cls = Class.forName("android.os.SystemProperties");
            return (String) cls.getMethod("get", String.class, String.class).invoke(cls, str, "");
        } catch (Exception unused) {
            return "";
        }
    }

    public static String getIMSI() {
        return getTelephonyManager().getSubscriberId();
    }

    public static int getPhoneType() {
        return getTelephonyManager().getPhoneType();
    }

    public static boolean isSimCardReady() {
        return getTelephonyManager().getSimState() == 5;
    }

    public static String getSimOperatorName() {
        return getTelephonyManager().getSimOperatorName();
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    public static String getSimOperatorByMnc() {
        String simOperator = getTelephonyManager().getSimOperator();
        if (simOperator == null) {
            return "";
        }
        byte b = -1;
        int iHashCode = simOperator.hashCode();
        if (iHashCode != 49679479) {
            if (iHashCode != 49679502) {
                if (iHashCode != 49679532) {
                    switch (iHashCode) {
                        case 49679470:
                            if (simOperator.equals("46000")) {
                                b = 0;
                            }
                            break;
                        case 49679471:
                            if (simOperator.equals("46001")) {
                                b = 4;
                            }
                            break;
                        case 49679472:
                            if (simOperator.equals("46002")) {
                                b = 1;
                            }
                            break;
                        case 49679473:
                            if (simOperator.equals("46003")) {
                                b = 7;
                            }
                            break;
                        default:
                            switch (iHashCode) {
                                case 49679475:
                                    if (simOperator.equals("46005")) {
                                        b = 8;
                                    }
                                    break;
                                case 49679476:
                                    if (simOperator.equals("46006")) {
                                        b = 5;
                                    }
                                    break;
                                case 49679477:
                                    if (simOperator.equals("46007")) {
                                        b = 2;
                                    }
                                    break;
                            }
                            break;
                    }
                } else if (simOperator.equals("46020")) {
                    b = 3;
                }
            } else if (simOperator.equals("46011")) {
                b = 9;
            }
        } else if (simOperator.equals("46009")) {
            b = 6;
        }
        switch (b) {
            case 0:
            case 1:
            case 2:
            case 3:
                return "中国移动";
            case 4:
            case 5:
            case 6:
                return "中国联通";
            case 7:
            case 8:
            case 9:
                return "中国电信";
            default:
                return simOperator;
        }
    }

    public static boolean dial(String str) {
        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse(WebView.SCHEME_TEL + str));
        if (!isIntentAvailable(intent)) {
            return false;
        }
        Utils.getApp().startActivity(intent.addFlags(268435456));
        return true;
    }

    public static boolean call(String str) {
        Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse(WebView.SCHEME_TEL + str));
        if (!isIntentAvailable(intent)) {
            return false;
        }
        Utils.getApp().startActivity(intent.addFlags(268435456));
        return true;
    }

    public static boolean sendSms(String str, String str2) {
        Intent intent = new Intent(Intent.ACTION_SENDTO, Uri.parse("smsto:" + str));
        if (!isIntentAvailable(intent)) {
            return false;
        }
        intent.putExtra("sms_body", str2);
        Utils.getApp().startActivity(intent.addFlags(268435456));
        return true;
    }

    private static TelephonyManager getTelephonyManager() {
        return (TelephonyManager) Utils.getApp().getSystemService("phone");
    }

    private static boolean isIntentAvailable(Intent intent) {
        return Utils.getApp().getPackageManager().queryIntentActivities(intent, 65536).size() > 0;
    }
}
