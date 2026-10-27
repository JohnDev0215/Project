package com.amazon.mp3.service.metrics.mts;

import com.amazon.mp3.util.UserSubscriptionUtil;

/* JADX INFO: loaded from: classes4.dex */
public class ContentSubscriptionModeHelper {
    public static String retrieveContentSubscriptionMode() {
        return UserSubscriptionUtil.getSubscription().tracking().contentSubscriptionMode();
    }

    public static String retrieveSubContentSubscriptionMode() {
        return UserSubscriptionUtil.getSubscription().tracking().subContentSubscriptionMode();
    }
}
