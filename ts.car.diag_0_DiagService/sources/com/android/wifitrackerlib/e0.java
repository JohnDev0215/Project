package com.android.wifitrackerlib;

import android.content.Context;
import android.content.pm.PackageManager;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.net.NetworkKey;
import android.net.NetworkScoreManager;
import android.net.ScoredNetwork;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiNetworkScoreCache;
import android.os.UserHandle;
import android.provider.Settings;
import android.telephony.SubscriptionInfo;
import android.telephony.SubscriptionManager;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.text.format.DateUtils;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.StringJoiner;
import java.util.function.ToIntFunction;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes3.dex */
class e0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static NetworkScoreManager f1459a;

    private static String a(@NonNull Context context) {
        if (f1459a == null) {
            f1459a = (NetworkScoreManager) context.getSystemService(NetworkScoreManager.class);
        }
        return f1459a.getActiveScorerPackage();
    }

    static String b(Context context, String str) {
        try {
            String string = Settings.Global.getString(context.getContentResolver(), "use_open_wifi_package");
            if (!TextUtils.isEmpty(string) && TextUtils.equals(str, a(context))) {
                str = string;
            }
            return context.getPackageManager().getApplicationInfoAsUser(str, 0, UserHandle.getUserId(-2)).loadLabel(context.getPackageManager()).toString();
        } catch (PackageManager.NameNotFoundException unused) {
            return "";
        }
    }

    static String c(@NonNull Context context, @NonNull WifiEntry wifiEntry) {
        return (context == null || wifiEntry == null || !wifiEntry.a() || wifiEntry.w()) ? "" : context.getString(R$string.auto_connect_disable);
    }

    static int d(@NonNull WifiNetworkScoreCache wifiNetworkScoreCache, @NonNull List<ScanResult> list) {
        int iCalculateBadge;
        int i = 0;
        int i2 = 0;
        for (ScanResult scanResult : list) {
            ScoredNetwork scoredNetwork = wifiNetworkScoreCache.getScoredNetwork(scanResult);
            if (scoredNetwork != null && (iCalculateBadge = scoredNetwork.calculateBadge(scanResult.level)) != 0) {
                i++;
                i2 += iCalculateBadge;
            }
        }
        if (i == 0) {
            return 0;
        }
        return v(i2 / i);
    }

    @Nullable
    static ScanResult e(@NonNull List<ScanResult> list) {
        if (list.isEmpty()) {
            return null;
        }
        return (ScanResult) Collections.max(list, Comparator.comparingInt(new ToIntFunction() { // from class: com.android.wifitrackerlib.u
            @Override // java.util.function.ToIntFunction
            public final int applyAsInt(Object obj) {
                return ((ScanResult) obj).level;
            }
        }));
    }

    @Nullable
    static String f(@NonNull Context context, int i) {
        TelephonyManager telephonyManager;
        TelephonyManager telephonyManagerCreateForSubscriptionId;
        CharSequence simCarrierIdName;
        if (i == -1 || (telephonyManager = (TelephonyManager) context.getSystemService("phone")) == null || (telephonyManagerCreateForSubscriptionId = telephonyManager.createForSubscriptionId(i)) == null || (simCarrierIdName = telephonyManagerCreateForSubscriptionId.getSimCarrierIdName()) == null) {
            return null;
        }
        return simCarrierIdName.toString();
    }

    static String g(Context context, NetworkCapabilities networkCapabilities) {
        if (context != null && networkCapabilities != null) {
            if (networkCapabilities.hasCapability(17)) {
                return context.getString(context.getResources().getIdentifier("network_available_sign_in", "string", "android"));
            }
            if (networkCapabilities.hasCapability(24)) {
                return context.getString(R$string.wifi_limited_connection);
            }
            if (!networkCapabilities.hasCapability(16)) {
                return networkCapabilities.isPrivateDnsBroken() ? context.getString(R$string.private_dns_broken) : context.getString(R$string.wifi_connected_no_internet);
            }
        }
        return "";
    }

    static String h(Context context, WifiEntry wifiEntry) {
        if (context != null && wifiEntry != null) {
            WifiConfiguration wifiConfigurationU = wifiEntry.u();
            if (wifiConfigurationU == null) {
                return null;
            }
            if (wifiConfigurationU.hasNoInternetAccess()) {
                return context.getString(wifiConfigurationU.getNetworkSelectionStatus().getNetworkSelectionStatus() == 2 ? R$string.wifi_no_internet_no_reconnect : R$string.wifi_no_internet);
            }
            if (wifiConfigurationU.getNetworkSelectionStatus().getNetworkSelectionStatus() != 0) {
                int networkSelectionDisableReason = wifiConfigurationU.getNetworkSelectionStatus().getNetworkSelectionDisableReason();
                if (networkSelectionDisableReason == 1) {
                    return context.getString(R$string.wifi_disabled_generic);
                }
                if (networkSelectionDisableReason == 2) {
                    return context.getString(R$string.wifi_disabled_password_failure);
                }
                if (networkSelectionDisableReason == 3) {
                    return context.getString(R$string.wifi_disabled_network_failure);
                }
                if (networkSelectionDisableReason == 8) {
                    return context.getString(R$string.wifi_check_password_try_again);
                }
            } else if (wifiEntry.k() != -1 && wifiConfigurationU.getRecentFailureReason() == 17) {
                return context.getString(R$string.wifi_ap_unable_to_handle_new_sta);
            }
        }
        return "";
    }

    static String i(@NonNull Context context, @Nullable WifiEntry wifiEntry) {
        return (context == null || wifiEntry == null) ? "" : (wifiEntry.b() || wifiEntry.l() == 1) ? wifiEntry.l() == 1 ? context.getString(R$string.wifi_metered_label) : wifiEntry.l() == 2 ? context.getString(R$string.wifi_unmetered_label) : wifiEntry.x() ? context.getString(R$string.wifi_metered_label) : "" : "";
    }

    static String j(Context context, NetworkInfo networkInfo) {
        NetworkInfo.DetailedState detailedState;
        if (context == null || networkInfo == null || (detailedState = networkInfo.getDetailedState()) == null) {
            return "";
        }
        String[] stringArray = context.getResources().getStringArray(R$array.wifi_status);
        int iOrdinal = detailedState.ordinal();
        return iOrdinal >= stringArray.length ? "" : stringArray[iOrdinal];
    }

    static String k(WifiConfiguration wifiConfiguration) {
        if (wifiConfiguration == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        WifiConfiguration.NetworkSelectionStatus networkSelectionStatus = wifiConfiguration.getNetworkSelectionStatus();
        if (networkSelectionStatus.getNetworkSelectionStatus() != 0) {
            sb.append(" (" + networkSelectionStatus.getNetworkStatusString());
            if (networkSelectionStatus.getDisableTime() > 0) {
                sb.append(" " + DateUtils.formatElapsedTime((System.currentTimeMillis() - networkSelectionStatus.getDisableTime()) / 1000));
            }
            sb.append(")");
        }
        int maxNetworkSelectionDisableReason = WifiConfiguration.NetworkSelectionStatus.getMaxNetworkSelectionDisableReason();
        for (int i = 0; i <= maxNetworkSelectionDisableReason; i++) {
            int disableReasonCounter = networkSelectionStatus.getDisableReasonCounter(i);
            if (disableReasonCounter != 0) {
                sb.append(" ");
                sb.append(WifiConfiguration.NetworkSelectionStatus.getNetworkSelectionDisableReasonString(i));
                sb.append("=");
                sb.append(disableReasonCounter);
            }
        }
        return sb.toString();
    }

    static int l(@NonNull WifiConfiguration wifiConfiguration) {
        if (wifiConfiguration.allowedKeyManagement.get(8)) {
            return 5;
        }
        if (wifiConfiguration.allowedKeyManagement.get(1)) {
            return 2;
        }
        if (wifiConfiguration.allowedKeyManagement.get(10)) {
            return 6;
        }
        if (wifiConfiguration.allowedKeyManagement.get(2) || wifiConfiguration.allowedKeyManagement.get(3)) {
            return 3;
        }
        if (wifiConfiguration.allowedKeyManagement.get(9)) {
            return 4;
        }
        return wifiConfiguration.wepKeys[0] != null ? 1 : 0;
    }

    static List<Integer> m(@NonNull ScanResult scanResult) {
        ArrayList arrayList = new ArrayList();
        String str = scanResult.capabilities;
        if (str == null) {
            arrayList.add(0);
        } else if (str.contains("PSK") && scanResult.capabilities.contains("SAE")) {
            arrayList.add(2);
            arrayList.add(5);
        } else if (scanResult.capabilities.contains("OWE_TRANSITION")) {
            arrayList.add(0);
            arrayList.add(4);
        } else if (scanResult.capabilities.contains("OWE")) {
            arrayList.add(4);
        } else if (scanResult.capabilities.contains("WEP")) {
            arrayList.add(1);
        } else if (scanResult.capabilities.contains("SAE")) {
            arrayList.add(5);
        } else if (scanResult.capabilities.contains("PSK")) {
            arrayList.add(2);
        } else if (scanResult.capabilities.contains("EAP_SUITE_B_192")) {
            arrayList.add(6);
        } else if (scanResult.capabilities.contains("EAP")) {
            arrayList.add(3);
        } else {
            arrayList.add(0);
        }
        return arrayList;
    }

    static String n(@NonNull Context context, @NonNull WifiEntry wifiEntry) {
        if (context == null || wifiEntry == null) {
            return "";
        }
        int iQ = wifiEntry.q();
        return iQ != 5 ? iQ != 10 ? iQ != 20 ? iQ != 30 ? "" : context.getString(R$string.speed_label_very_fast) : context.getString(R$string.speed_label_fast) : context.getString(R$string.speed_label_okay) : context.getString(R$string.speed_label_slow);
    }

    static int o(@NonNull WifiNetworkScoreCache wifiNetworkScoreCache, @NonNull WifiInfo wifiInfo) {
        ScoredNetwork scoredNetwork = wifiNetworkScoreCache.getScoredNetwork(NetworkKey.createFromWifiInfo(wifiInfo));
        if (scoredNetwork == null) {
            return 0;
        }
        return v(scoredNetwork.calculateBadge(wifiInfo.getRssi()));
    }

    static int p(@NonNull Context context, @NonNull WifiConfiguration wifiConfiguration) {
        SubscriptionManager subscriptionManager;
        int subscriptionId = -1;
        if (wifiConfiguration.carrierId == -1 || (subscriptionManager = (SubscriptionManager) context.getSystemService("telephony_subscription_service")) == null) {
            return -1;
        }
        List<SubscriptionInfo> activeSubscriptionInfoList = subscriptionManager.getActiveSubscriptionInfoList();
        if (activeSubscriptionInfoList != null && !activeSubscriptionInfoList.isEmpty()) {
            int defaultDataSubscriptionId = SubscriptionManager.getDefaultDataSubscriptionId();
            for (SubscriptionInfo subscriptionInfo : activeSubscriptionInfoList) {
                if (subscriptionInfo.getCarrierId() == wifiConfiguration.carrierId && (subscriptionId = subscriptionInfo.getSubscriptionId()) == defaultDataSubscriptionId) {
                    break;
                }
            }
        }
        return subscriptionId;
    }

    static String q(@NonNull WifiEntry wifiEntry) {
        if (!BaseWifiTracker.g() || wifiEntry == null) {
            return "";
        }
        StringJoiner stringJoiner = new StringJoiner(" ");
        String strV = wifiEntry.v();
        if (!TextUtils.isEmpty(strV)) {
            stringJoiner.add(strV);
        }
        String strM = wifiEntry.m();
        if (!TextUtils.isEmpty(strM)) {
            stringJoiner.add(strM);
        }
        String strO = wifiEntry.o();
        if (!TextUtils.isEmpty(strO)) {
            stringJoiner.add(strO);
        }
        String strN = wifiEntry.n();
        if (!TextUtils.isEmpty(strN)) {
            stringJoiner.add(strN);
        }
        return stringJoiner.toString();
    }

    static /* synthetic */ boolean s(ScanResult scanResult) {
        return !TextUtils.isEmpty(scanResult.SSID);
    }

    /* JADX WARN: Removed duplicated region for block: B:125:0x0260  */
    /* JADX WARN: Removed duplicated region for block: B:137:0x0246 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:54:0x014c  */
    /* JADX WARN: Removed duplicated region for block: B:57:0x015a  */
    /* JADX WARN: Removed duplicated region for block: B:60:0x016a  */
    /* JADX WARN: Removed duplicated region for block: B:64:0x0178  */
    /* JADX WARN: Removed duplicated region for block: B:67:0x0186 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:71:0x0196  */
    /* JADX WARN: Removed duplicated region for block: B:74:0x01a4  */
    /* JADX WARN: Removed duplicated region for block: B:76:0x01a7  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    static java.util.Map<java.lang.String, java.util.List<android.net.wifi.ScanResult>> u(@androidx.annotation.NonNull java.util.List<android.net.wifi.ScanResult> r26, boolean r27, @androidx.annotation.Nullable java.util.Map<java.lang.String, android.net.wifi.WifiConfiguration> r28, boolean r29, boolean r30, boolean r31) {
        /*
            Method dump skipped, instruction units count: 667
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.wifitrackerlib.e0.u(java.util.List, boolean, java.util.Map, boolean, boolean, boolean):java.util.Map");
    }

    private static int v(int i) {
        if (i == 0) {
            return 0;
        }
        if (i < 7) {
            return 5;
        }
        if (i < 15) {
            return 10;
        }
        return i < 25 ? 20 : 30;
    }
}
