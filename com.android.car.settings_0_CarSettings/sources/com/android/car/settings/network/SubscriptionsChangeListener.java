package com.android.car.settings.network;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.telephony.SubscriptionManager;

/* JADX INFO: loaded from: classes5.dex */
public class SubscriptionsChangeListener {
    private static final IntentFilter RADIO_TECH_CHANGED_FILTER = new IntentFilter("android.intent.action.RADIO_TECHNOLOGY");
    private final Context mContext;
    private final SubscriptionManager mSubscriptionManager;
    private final SubscriptionsChangeAction mSubscriptionsChangeAction;
    private final SubscriptionManager.OnSubscriptionsChangedListener mSubscriptionsChangedListener = new SubscriptionManager.OnSubscriptionsChangedListener() { // from class: com.android.car.settings.network.SubscriptionsChangeListener.1
        @Override // android.telephony.SubscriptionManager.OnSubscriptionsChangedListener
        public void onSubscriptionsChanged() {
            SubscriptionsChangeListener.this.subscriptionsChangedCallback();
        }
    };
    private final BroadcastReceiver mRadioTechChangeReceiver = new BroadcastReceiver() { // from class: com.android.car.settings.network.SubscriptionsChangeListener.2
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (!isInitialStickyBroadcast()) {
                SubscriptionsChangeListener.this.subscriptionsChangedCallback();
            }
        }
    };

    public interface SubscriptionsChangeAction {
        void onSubscriptionsChanged();
    }

    public SubscriptionsChangeListener(Context context, SubscriptionsChangeAction action) {
        this.mContext = context;
        this.mSubscriptionManager = (SubscriptionManager) context.getSystemService(SubscriptionManager.class);
        this.mSubscriptionsChangeAction = action;
    }

    public void start() {
        this.mSubscriptionManager.addOnSubscriptionsChangedListener(this.mSubscriptionsChangedListener);
        this.mContext.registerReceiver(this.mRadioTechChangeReceiver, RADIO_TECH_CHANGED_FILTER);
    }

    public void stop() {
        this.mSubscriptionManager.removeOnSubscriptionsChangedListener(this.mSubscriptionsChangedListener);
        this.mContext.unregisterReceiver(this.mRadioTechChangeReceiver);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void subscriptionsChangedCallback() {
        SubscriptionsChangeAction subscriptionsChangeAction = this.mSubscriptionsChangeAction;
        if (subscriptionsChangeAction != null) {
            subscriptionsChangeAction.onSubscriptionsChanged();
        }
    }
}
