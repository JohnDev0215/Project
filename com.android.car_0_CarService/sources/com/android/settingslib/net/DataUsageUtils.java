package com.android.settingslib.net;

import android.content.Context;
import android.net.NetworkTemplate;
import android.telephony.SubscriptionInfo;
import android.telephony.SubscriptionManager;
import android.telephony.TelephonyManager;
import android.util.Log;
import com.android.internal.util.ArrayUtils;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public class DataUsageUtils {
    private static final String TAG = "DataUsageUtils";

    public static NetworkTemplate getMobileTemplate(Context context, int subId) {
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(TelephonyManager.class);
        int mobileDefaultSubId = telephonyManager.getSubscriptionId();
        SubscriptionManager subscriptionManager = (SubscriptionManager) context.getSystemService(SubscriptionManager.class);
        List<SubscriptionInfo> subInfoList = subscriptionManager.getAvailableSubscriptionInfoList();
        if (subInfoList == null) {
            Log.i(TAG, "Subscription is not inited: " + subId);
            return getMobileTemplateForSubId(telephonyManager, mobileDefaultSubId);
        }
        for (SubscriptionInfo subInfo : subInfoList) {
            if (subInfo != null && subInfo.getSubscriptionId() == subId) {
                return getNormalizedMobileTemplate(telephonyManager, subId);
            }
        }
        Log.i(TAG, "Subscription is not active: " + subId);
        return getMobileTemplateForSubId(telephonyManager, mobileDefaultSubId);
    }

    private static NetworkTemplate getNormalizedMobileTemplate(TelephonyManager telephonyManager, int subId) {
        NetworkTemplate mobileTemplate = getMobileTemplateForSubId(telephonyManager, subId);
        String[] mergedSubscriberIds = telephonyManager.createForSubscriptionId(subId).getMergedImsisFromGroup();
        if (ArrayUtils.isEmpty(mergedSubscriberIds)) {
            Log.i(TAG, "mergedSubscriberIds is null.");
            return mobileTemplate;
        }
        return NetworkTemplate.normalize(mobileTemplate, mergedSubscriberIds);
    }

    private static NetworkTemplate getMobileTemplateForSubId(TelephonyManager telephonyManager, int subId) {
        return NetworkTemplate.buildTemplateMobileAll(telephonyManager.getSubscriberId(subId));
    }
}
