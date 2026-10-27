package com.android.car.settings.network;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.telephony.SubscriptionInfo;
import android.telephony.SubscriptionManager;
import android.telephony.TelephonyManager;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public final class NetworkUtils {
    private NetworkUtils() {
    }

    public static boolean hasMobileNetwork(ConnectivityManager connectivityManager) {
        Network[] networks = connectivityManager.getAllNetworks();
        for (Network network : networks) {
            NetworkCapabilities capabilities = connectivityManager.getNetworkCapabilities(network);
            if (capabilities.hasTransport(0)) {
                return true;
            }
        }
        return false;
    }

    public static boolean hasSim(TelephonyManager telephonyManager) {
        int simState = telephonyManager.getSimState();
        return (simState == 1 || simState == 0) ? false : true;
    }

    public static void setMobileDataEnabled(Context context, int subId, boolean enabled, boolean disableOtherSubscriptions) {
        List<SubscriptionInfo> subInfoList;
        TelephonyManager telephonyManager = ((TelephonyManager) context.getSystemService(TelephonyManager.class)).createForSubscriptionId(subId);
        SubscriptionManager subscriptionManager = (SubscriptionManager) context.getSystemService(SubscriptionManager.class);
        telephonyManager.setDataEnabled(enabled);
        if (disableOtherSubscriptions && (subInfoList = subscriptionManager.getActiveSubscriptionInfoList()) != null) {
            for (SubscriptionInfo subInfo : subInfoList) {
                if (subInfo.getSubscriptionId() != subId && !subInfo.isOpportunistic()) {
                    ((TelephonyManager) context.getSystemService(TelephonyManager.class)).createForSubscriptionId(subInfo.getSubscriptionId()).setDataEnabled(false);
                }
            }
        }
    }
}
