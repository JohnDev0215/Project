package com.android.car.developeroptions.network;

import android.content.Context;
import android.content.Intent;
import android.telephony.SubscriptionInfo;
import android.telephony.SubscriptionManager;
import android.util.ArrayMap;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;
import androidx.preference.Preference;
import androidx.preference.PreferenceScreen;
import com.android.car.developeroptions.R;
import com.android.car.developeroptions.network.SubscriptionsChangeListener;
import com.android.car.developeroptions.network.telephony.MobileNetworkActivity;
import com.android.car.developeroptions.network.telephony.MobileNetworkUtils;
import com.android.internal.annotations.VisibleForTesting;
import com.android.settingslib.core.AbstractPreferenceController;
import java.util.Iterator;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public class MobileNetworkListController extends AbstractPreferenceController implements LifecycleObserver, SubscriptionsChangeListener.SubscriptionsChangeListenerClient {

    @VisibleForTesting
    static final String KEY_ADD_MORE = "add_more";
    private SubscriptionsChangeListener mChangeListener;
    private PreferenceScreen mPreferenceScreen;
    private Map<Integer, Preference> mPreferences;
    private SubscriptionManager mSubscriptionManager;

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public String getPreferenceKey() {
        return null;
    }

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public boolean isAvailable() {
        return true;
    }

    @Override // com.android.car.developeroptions.network.SubscriptionsChangeListener.SubscriptionsChangeListenerClient
    public void onAirplaneModeChanged(boolean z) {
    }

    public MobileNetworkListController(Context context, Lifecycle lifecycle) {
        super(context);
        this.mSubscriptionManager = (SubscriptionManager) context.getSystemService(SubscriptionManager.class);
        this.mChangeListener = new SubscriptionsChangeListener(context, this);
        this.mPreferences = new ArrayMap();
        lifecycle.addObserver(this);
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    public void onResume() {
        this.mChangeListener.start();
        update();
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    public void onPause() {
        this.mChangeListener.stop();
    }

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public void displayPreference(PreferenceScreen preferenceScreen) {
        super.displayPreference(preferenceScreen);
        this.mPreferenceScreen = preferenceScreen;
        preferenceScreen.findPreference(KEY_ADD_MORE).setVisible(MobileNetworkUtils.showEuiccSettings(this.mContext));
        update();
    }

    private void update() {
        if (this.mPreferenceScreen == null) {
            return;
        }
        Map<Integer, Preference> map = this.mPreferences;
        this.mPreferences = new ArrayMap();
        for (final SubscriptionInfo subscriptionInfo : SubscriptionUtil.getAvailableSubscriptions(this.mSubscriptionManager)) {
            int subscriptionId = subscriptionInfo.getSubscriptionId();
            Preference preferenceRemove = map.remove(Integer.valueOf(subscriptionId));
            if (preferenceRemove == null) {
                preferenceRemove = new Preference(this.mPreferenceScreen.getContext());
                this.mPreferenceScreen.addPreference(preferenceRemove);
            }
            preferenceRemove.setTitle(subscriptionInfo.getDisplayName());
            if (subscriptionInfo.isEmbedded()) {
                if (this.mSubscriptionManager.isActiveSubscriptionId(subscriptionId)) {
                    preferenceRemove.setSummary(R.string.mobile_network_active_esim);
                } else {
                    preferenceRemove.setSummary(R.string.mobile_network_inactive_esim);
                }
            } else if (this.mSubscriptionManager.isActiveSubscriptionId(subscriptionId)) {
                preferenceRemove.setSummary(R.string.mobile_network_active_sim);
            } else {
                preferenceRemove.setSummary(R.string.mobile_network_inactive_sim);
            }
            preferenceRemove.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() { // from class: com.android.car.developeroptions.network.-$$Lambda$MobileNetworkListController$qqfJsyBCqFvKEHZK3SmVJtwWzjA
                @Override // androidx.preference.Preference.OnPreferenceClickListener
                public final boolean onPreferenceClick(Preference preference) {
                    return this.f$0.lambda$update$0$MobileNetworkListController(subscriptionInfo, preference);
                }
            });
            this.mPreferences.put(Integer.valueOf(subscriptionId), preferenceRemove);
        }
        Iterator<Preference> it = map.values().iterator();
        while (it.hasNext()) {
            this.mPreferenceScreen.removePreference(it.next());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: lambda$update$0, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ boolean lambda$update$0$MobileNetworkListController(SubscriptionInfo subscriptionInfo, Preference preference) {
        Intent intent = new Intent(this.mContext, (Class<?>) MobileNetworkActivity.class);
        intent.putExtra("android.provider.extra.SUB_ID", subscriptionInfo.getSubscriptionId());
        this.mContext.startActivity(intent);
        return true;
    }

    @Override // com.android.car.developeroptions.network.SubscriptionsChangeListener.SubscriptionsChangeListenerClient
    public void onSubscriptionsChanged() {
        update();
    }
}
