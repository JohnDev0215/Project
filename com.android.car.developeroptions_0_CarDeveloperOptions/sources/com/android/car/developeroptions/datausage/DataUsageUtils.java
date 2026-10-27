package com.android.car.developeroptions.datausage;

import android.R;
import android.app.usage.NetworkStats;
import android.app.usage.NetworkStatsManager;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkTemplate;
import android.os.RemoteException;
import android.telephony.SubscriptionInfo;
import android.telephony.SubscriptionManager;
import android.telephony.TelephonyManager;
import android.text.BidiFormatter;
import android.text.format.Formatter;
import android.util.Log;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class DataUsageUtils {
    public static CharSequence formatDataUsage(Context context, long j) {
        Formatter.BytesResult bytes = Formatter.formatBytes(context.getResources(), j, 8);
        return BidiFormatter.getInstance().unicodeWrap(context.getString(R.string.default_audio_route_name_external_device, bytes.value, bytes.units));
    }

    public static boolean hasEthernet(Context context) {
        if (!ConnectivityManager.from(context).isNetworkSupported(9)) {
            return false;
        }
        TelephonyManager telephonyManagerFrom = TelephonyManager.from(context);
        try {
            NetworkStats.Bucket bucketQuerySummaryForUser = ((NetworkStatsManager) context.getSystemService(NetworkStatsManager.class)).querySummaryForUser(9, telephonyManagerFrom.getSubscriberId(), 0L, System.currentTimeMillis());
            if (bucketQuerySummaryForUser == null) {
                return false;
            }
            if (bucketQuerySummaryForUser.getRxBytes() <= 0) {
                if (bucketQuerySummaryForUser.getTxBytes() <= 0) {
                    return false;
                }
            }
            return true;
        } catch (RemoteException e) {
            Log.e("DataUsageUtils", "Exception querying network detail.", e);
            return false;
        }
    }

    public static boolean hasMobileData(Context context) {
        ConnectivityManager connectivityManagerFrom = ConnectivityManager.from(context);
        return connectivityManagerFrom != null && connectivityManagerFrom.isNetworkSupported(0);
    }

    public static boolean hasReadyMobileRadio(Context context) {
        List activeSubscriptionInfoList = SubscriptionManager.from(context).getActiveSubscriptionInfoList(true);
        if (activeSubscriptionInfoList == null) {
            return false;
        }
        TelephonyManager telephonyManagerFrom = TelephonyManager.from(context);
        Iterator it = activeSubscriptionInfoList.iterator();
        boolean z = true;
        while (it.hasNext()) {
            z &= telephonyManagerFrom.getSimState(((SubscriptionInfo) it.next()).getSimSlotIndex()) == 5;
        }
        return ConnectivityManager.from(context).isNetworkSupported(0) && z;
    }

    public static boolean hasWifiRadio(Context context) {
        ConnectivityManager connectivityManagerFrom = ConnectivityManager.from(context);
        return connectivityManagerFrom != null && connectivityManagerFrom.isNetworkSupported(1);
    }

    public static boolean hasSim(Context context) {
        int simState = ((TelephonyManager) context.getSystemService(TelephonyManager.class)).getSimState();
        return (simState == 1 || simState == 0) ? false : true;
    }

    public static int getDefaultSubscriptionId(Context context) {
        SubscriptionManager subscriptionManagerFrom = SubscriptionManager.from(context);
        if (subscriptionManagerFrom == null) {
            return -1;
        }
        SubscriptionInfo defaultDataSubscriptionInfo = subscriptionManagerFrom.getDefaultDataSubscriptionInfo();
        if (defaultDataSubscriptionInfo == null) {
            List<SubscriptionInfo> allSubscriptionInfoList = subscriptionManagerFrom.getAllSubscriptionInfoList();
            if (allSubscriptionInfoList.size() == 0) {
                return -1;
            }
            defaultDataSubscriptionInfo = allSubscriptionInfoList.get(0);
        }
        return defaultDataSubscriptionInfo.getSubscriptionId();
    }

    static NetworkTemplate getDefaultTemplate(Context context, int i) {
        if (hasMobileData(context) && i != -1) {
            TelephonyManager telephonyManagerFrom = TelephonyManager.from(context);
            return NetworkTemplate.normalize(NetworkTemplate.buildTemplateMobileAll(telephonyManagerFrom.getSubscriberId(i)), telephonyManagerFrom.getMergedSubscriberIds());
        }
        if (hasWifiRadio(context)) {
            return NetworkTemplate.buildTemplateWifiWildcard();
        }
        return NetworkTemplate.buildTemplateEthernet();
    }
}
