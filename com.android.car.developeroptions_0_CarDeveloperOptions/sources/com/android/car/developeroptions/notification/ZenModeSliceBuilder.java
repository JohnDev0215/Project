package com.android.car.developeroptions.notification;

import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

/* JADX INFO: loaded from: classes.dex */
public class ZenModeSliceBuilder {
    public static final IntentFilter INTENT_FILTER;

    static {
        IntentFilter intentFilter = new IntentFilter();
        INTENT_FILTER = intentFilter;
        intentFilter.addAction("android.app.action.NOTIFICATION_POLICY_CHANGED");
        INTENT_FILTER.addAction("android.app.action.INTERRUPTION_FILTER_CHANGED");
        INTENT_FILTER.addAction("android.app.action.INTERRUPTION_FILTER_CHANGED_INTERNAL");
    }

    public static void handleUriChange(Context context, Intent intent) {
        NotificationManager.from(context).setZenMode(intent.getBooleanExtra("android.app.slice.extra.TOGGLE_STATE", false) ? 1 : 0, null, "ZenModeSliceBuilder");
    }
}
