package com.android.car.settings.network;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.telephony.SubscriptionInfo;
import android.telephony.SubscriptionManager;
import androidx.lifecycle.DefaultLifecycleObserver;
import androidx.lifecycle.LifecycleOwner;
import com.android.car.settings.common.Logger;
import com.android.internal.util.CollectionUtils;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/* JADX INFO: loaded from: classes5.dex */
public class MobileNetworkUpdateManager implements DefaultLifecycleObserver {
    private static final Logger LOG = new Logger((Class<?>) MobileNetworkUpdateManager.class);
    static final int SUB_ID_NULL = Integer.MIN_VALUE;
    private int mCurSubscriptionId;
    private final List<MobileNetworkUpdateListener> mListeners = new ArrayList();
    private final SubscriptionManager.OnSubscriptionsChangedListener mOnSubscriptionsChangeListener = new SubscriptionManager.OnSubscriptionsChangedListener() { // from class: com.android.car.settings.network.MobileNetworkUpdateManager.1
        @Override // android.telephony.SubscriptionManager.OnSubscriptionsChangedListener
        public void onSubscriptionsChanged() {
            if (!Objects.equals(MobileNetworkUpdateManager.this.mSubscriptionInfos, MobileNetworkUpdateManager.this.mSubscriptionManager.getActiveSubscriptionInfoList(true))) {
                MobileNetworkUpdateManager.this.updateSubscriptions(false);
            }
        }
    };
    private final PhoneChangeReceiver mPhoneChangeReceiver;
    private List<SubscriptionInfo> mSubscriptionInfos;
    private final SubscriptionManager mSubscriptionManager;

    public interface MobileNetworkUpdateListener {
        void onMobileNetworkUpdated(int i);
    }

    public MobileNetworkUpdateManager(Context context, int subId) {
        this.mCurSubscriptionId = subId;
        SubscriptionManager subscriptionManager = (SubscriptionManager) context.getSystemService(SubscriptionManager.class);
        this.mSubscriptionManager = subscriptionManager;
        this.mSubscriptionInfos = subscriptionManager.getActiveSubscriptionInfoList();
        this.mPhoneChangeReceiver = new PhoneChangeReceiver(context, new PhoneChangeReceiver.OnChangeAction() { // from class: com.android.car.settings.network.-$$Lambda$MobileNetworkUpdateManager$JxQhfpIviPaLlA3Rs5R0Fo5aUIY
            @Override // com.android.car.settings.network.MobileNetworkUpdateManager.PhoneChangeReceiver.OnChangeAction
            public final void onReceive() {
                this.f$0.lambda$new$0$MobileNetworkUpdateManager();
            }
        });
    }

    public /* synthetic */ void lambda$new$0$MobileNetworkUpdateManager() {
        if (this.mCurSubscriptionId != Integer.MIN_VALUE) {
            LOG.d("Radio change (i.e. CDMA->GSM) received for valid subscription id: " + this.mCurSubscriptionId);
            updateReceived(this.mCurSubscriptionId);
        }
    }

    public void registerListener(MobileNetworkUpdateListener listener) {
        this.mListeners.add(listener);
    }

    public void unregisterListener(MobileNetworkUpdateListener listener) {
        this.mListeners.remove(listener);
    }

    @Override // androidx.lifecycle.DefaultLifecycleObserver, androidx.lifecycle.FullLifecycleObserver
    public void onCreate(LifecycleOwner owner) {
        updateSubscriptions(true);
    }

    @Override // androidx.lifecycle.DefaultLifecycleObserver, androidx.lifecycle.FullLifecycleObserver
    public final void onStart(LifecycleOwner owner) {
        this.mPhoneChangeReceiver.register();
        this.mSubscriptionManager.addOnSubscriptionsChangedListener(this.mOnSubscriptionsChangeListener);
    }

    @Override // androidx.lifecycle.DefaultLifecycleObserver, androidx.lifecycle.FullLifecycleObserver
    public final void onStop(LifecycleOwner owner) {
        this.mPhoneChangeReceiver.unregister();
        this.mSubscriptionManager.removeOnSubscriptionsChangedListener(this.mOnSubscriptionsChangeListener);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateSubscriptions(boolean forceRefresh) {
        LOG.d("updateSubscriptions called");
        this.mSubscriptionInfos = this.mSubscriptionManager.getActiveSubscriptionInfoList();
        int subId = getSubscriptionId();
        if (forceRefresh || this.mCurSubscriptionId != subId) {
            LOG.d("updateSubscriptions updated subscription id! prev: " + this.mCurSubscriptionId + " new: " + subId);
            this.mCurSubscriptionId = subId;
            updateReceived(subId);
        }
    }

    private void updateReceived(int subId) {
        for (MobileNetworkUpdateListener listener : this.mListeners) {
            listener.onMobileNetworkUpdated(subId);
        }
    }

    private int getSubscriptionId() {
        SubscriptionInfo subscription = getSubscription();
        if (subscription != null) {
            return subscription.getSubscriptionId();
        }
        return -1;
    }

    private SubscriptionInfo getSubscription() {
        if (this.mCurSubscriptionId != Integer.MIN_VALUE) {
            for (SubscriptionInfo subscriptionInfo : this.mSubscriptionManager.getSelectableSubscriptionInfoList()) {
                if (subscriptionInfo.getSubscriptionId() == this.mCurSubscriptionId) {
                    return subscriptionInfo;
                }
            }
        }
        if (CollectionUtils.isEmpty(this.mSubscriptionInfos)) {
            return null;
        }
        return this.mSubscriptionInfos.get(0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    static class PhoneChangeReceiver extends BroadcastReceiver {
        private static final IntentFilter RADIO_TECHNOLOGY_CHANGED_FILTER = new IntentFilter("android.intent.action.RADIO_TECHNOLOGY");
        private Context mContext;
        private OnChangeAction mOnChangeAction;

        /* JADX INFO: Access modifiers changed from: private */
        interface OnChangeAction {
            void onReceive();
        }

        PhoneChangeReceiver(Context context, OnChangeAction onChangeAction) {
            this.mContext = context;
            this.mOnChangeAction = onChangeAction;
        }

        void register() {
            this.mContext.registerReceiver(this, RADIO_TECHNOLOGY_CHANGED_FILTER);
        }

        void unregister() {
            this.mContext.unregisterReceiver(this);
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (!isInitialStickyBroadcast()) {
                this.mOnChangeAction.onReceive();
            }
        }
    }
}
