package com.byd.vrassistant.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.telephony.TelephonyManager;

/* JADX INFO: loaded from: classes2.dex */
public class PhoneUtils {
    public static final int NETWORKTYPE_2G = 2;
    public static final int NETWORKTYPE_3G = 3;
    public static final int NETWORKTYPE_4G = 4;
    public static final int NETWORKTYPE_5G = 5;
    public static final int NETWORKTYPE_MOBILE = 6;
    public static final int NETWORKTYPE_NONE = 0;
    public static final int NETWORKTYPE_WIFI = 1;
    private static final String TAG = "NEU_PhoneUtils";

    public static boolean hasSimCard(Context context) {
        int simState = ((TelephonyManager) context.getSystemService("phone")).getSimState();
        return (simState == 0 || simState == 1) ? false : true;
    }

    public static int getNetWorkType(Context context) {
        NetworkInfo activeNetworkInfo;
        NetworkInfo.State state;
        RLog.i(TAG, "getNetWorkType: start");
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
        if (connectivityManager != null && (activeNetworkInfo = connectivityManager.getActiveNetworkInfo()) != null && activeNetworkInfo.isAvailable()) {
            NetworkInfo networkInfo = connectivityManager.getNetworkInfo(1);
            if (networkInfo != null && (state = networkInfo.getState()) != null && (state == NetworkInfo.State.CONNECTED || state == NetworkInfo.State.CONNECTING)) {
                return 1;
            }
            NetworkInfo networkInfo2 = connectivityManager.getNetworkInfo(0);
            if (networkInfo2 != null) {
                RLog.i(TAG, "getNetWorkType: 运营商2g、3g、4g");
                NetworkInfo.State state2 = networkInfo2.getState();
                String subtypeName = networkInfo2.getSubtypeName();
                activeNetworkInfo.getSubtype();
                activeNetworkInfo.getSubtypeName();
                if (state2 != null && (state2 == NetworkInfo.State.CONNECTED || state2 == NetworkInfo.State.CONNECTING)) {
                    switch (activeNetworkInfo.getSubtype()) {
                        case 1:
                        case 2:
                        case 4:
                        case 7:
                        case 11:
                        case 16:
                            return 2;
                        case 3:
                        case 5:
                        case 6:
                        case 8:
                        case 9:
                        case 10:
                        case 12:
                        case 14:
                        case 15:
                        case 18:
                            return 3;
                        case 13:
                            return 4;
                        case 17:
                        default:
                            return (subtypeName.equalsIgnoreCase("TD-SCDMA") || subtypeName.equalsIgnoreCase("WCDMA") || subtypeName.equalsIgnoreCase("CDMA2000")) ? 3 : 6;
                    }
                }
            }
        }
        return 0;
    }
}
