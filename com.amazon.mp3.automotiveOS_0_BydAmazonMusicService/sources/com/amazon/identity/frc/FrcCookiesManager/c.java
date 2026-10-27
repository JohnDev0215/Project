package com.amazon.identity.frc.FrcCookiesManager;

import android.content.Context;
import android.telephony.TelephonyManager;
import android.util.Log;
import androidx.autofill.HintConstants;

/* JADX INFO: compiled from: TelephonyManagerUtil.java */
/* JADX INFO: loaded from: classes3.dex */
public final class c {
    private static final String TAG = "com.amazon.identity.frc.FrcCookiesManager.c";

    private c() {
    }

    public static String d(Context context) {
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(HintConstants.AUTOFILL_HINT_PHONE);
        if (telephonyManager == null) {
            Log.e(TAG, "TelephonyManager returned is null");
            telephonyManager = null;
        }
        if (telephonyManager == null) {
            return null;
        }
        return telephonyManager.getNetworkOperatorName();
    }
}
