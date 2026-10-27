package com.android.server.telecom;

import android.content.ComponentName;
import android.content.Context;
import android.net.Uri;
import android.telecom.PhoneAccount;
import android.telecom.PhoneAccountHandle;
import android.telephony.TelephonyManager;
import com.android.internal.annotations.VisibleForTesting;

/* JADX INFO: loaded from: classes.dex */
public final class TelephonyUtil {
    private static final PhoneAccountHandle DEFAULT_EMERGENCY_PHONE_ACCOUNT_HANDLE = new PhoneAccountHandle(new ComponentName("com.android.phone", "com.android.services.telephony.TelephonyConnectionService"), "E");

    @VisibleForTesting
    public static PhoneAccount getDefaultEmergencyPhoneAccount() {
        return PhoneAccount.builder(DEFAULT_EMERGENCY_PHONE_ACCOUNT_HANDLE, "E").setCapabilities(22).setIsEnabled(true).build();
    }

    static boolean isPstnComponentName(ComponentName componentName) {
        return new ComponentName("com.android.phone", "com.android.services.telephony.TelephonyConnectionService").equals(componentName);
    }

    public static boolean shouldProcessAsEmergency(Context context, Uri uri) {
        try {
            TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
            if (uri != null) {
                return telephonyManager.isEmergencyNumber(uri.getSchemeSpecificPart());
            }
            return false;
        } catch (IllegalStateException unused) {
            return false;
        }
    }
}
