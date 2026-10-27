package com.android.settingslib.net;

import android.R;
import android.content.Context;
import android.telephony.SubscriptionManager;

/* JADX INFO: loaded from: classes3.dex */
public class SignalStrengthUtil {
    public static boolean shouldInflateSignalStrength(Context context, int subscriptionId) {
        return SubscriptionManager.getResourcesForSubId(context, subscriptionId).getBoolean(R.bool.config_cecQuerySadAtrac_userConfigurable);
    }
}
