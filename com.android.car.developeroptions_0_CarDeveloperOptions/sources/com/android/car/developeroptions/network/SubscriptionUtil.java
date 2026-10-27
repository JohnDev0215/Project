package com.android.car.developeroptions.network;

import android.telephony.SubscriptionInfo;
import android.telephony.SubscriptionManager;
import android.text.TextUtils;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class SubscriptionUtil {
    private static List<SubscriptionInfo> sActiveResultsForTesting;
    private static List<SubscriptionInfo> sAvailableResultsForTesting;

    public static void setAvailableSubscriptionsForTesting(List<SubscriptionInfo> list) {
        sAvailableResultsForTesting = list;
    }

    public static void setActiveSubscriptionsForTesting(List<SubscriptionInfo> list) {
        sActiveResultsForTesting = list;
    }

    public static List<SubscriptionInfo> getActiveSubscriptions(SubscriptionManager subscriptionManager) {
        List<SubscriptionInfo> list = sActiveResultsForTesting;
        if (list != null) {
            return list;
        }
        List<SubscriptionInfo> activeSubscriptionInfoList = subscriptionManager.getActiveSubscriptionInfoList(true);
        return activeSubscriptionInfoList == null ? new ArrayList() : activeSubscriptionInfoList;
    }

    public static List<SubscriptionInfo> getAvailableSubscriptions(SubscriptionManager subscriptionManager) {
        List<SubscriptionInfo> list = sAvailableResultsForTesting;
        if (list != null) {
            return list;
        }
        List<SubscriptionInfo> selectableSubscriptionInfoList = subscriptionManager.getSelectableSubscriptionInfoList();
        if (selectableSubscriptionInfoList == null) {
            selectableSubscriptionInfoList = new ArrayList<>();
        }
        Iterator<SubscriptionInfo> it = selectableSubscriptionInfoList.iterator();
        while (it.hasNext()) {
            if (TextUtils.isEmpty(it.next().getMncString())) {
                it.remove();
            }
        }
        return selectableSubscriptionInfoList;
    }
}
