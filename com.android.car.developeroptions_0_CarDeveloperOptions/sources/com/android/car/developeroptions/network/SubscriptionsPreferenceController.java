package com.android.car.developeroptions.network;

import android.content.Context;
import android.content.Intent;
import android.telephony.SubscriptionInfo;
import android.telephony.SubscriptionManager;
import android.telephony.TelephonyManager;
import androidx.collection.ArrayMap;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;
import androidx.preference.Preference;
import androidx.preference.PreferenceGroup;
import androidx.preference.PreferenceScreen;
import com.android.car.developeroptions.R;
import com.android.car.developeroptions.network.SubscriptionsChangeListener;
import com.android.car.developeroptions.network.telephony.MobileNetworkActivity;
import com.android.settingslib.core.AbstractPreferenceController;
import java.util.Iterator;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public class SubscriptionsPreferenceController extends AbstractPreferenceController implements LifecycleObserver, SubscriptionsChangeListener.SubscriptionsChangeListenerClient {
    private SubscriptionManager mManager;
    private PreferenceGroup mPreferenceGroup;
    private String mPreferenceGroupKey;
    private int mStartOrder;
    private Map<Integer, Preference> mSubscriptionPreferences;
    private SubscriptionsChangeListener mSubscriptionsListener;
    private UpdateListener mUpdateListener;

    public interface UpdateListener {
        void onChildrenUpdated();
    }

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public String getPreferenceKey() {
        return null;
    }

    public SubscriptionsPreferenceController(Context context, Lifecycle lifecycle, UpdateListener updateListener, String str, int i) {
        super(context);
        this.mUpdateListener = updateListener;
        this.mPreferenceGroupKey = str;
        this.mStartOrder = i;
        this.mManager = (SubscriptionManager) context.getSystemService(SubscriptionManager.class);
        this.mSubscriptionPreferences = new ArrayMap();
        this.mSubscriptionsListener = new SubscriptionsChangeListener(context, this);
        lifecycle.addObserver(this);
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    public void onResume() {
        this.mSubscriptionsListener.start();
        update();
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    public void onPause() {
        this.mSubscriptionsListener.stop();
    }

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public void displayPreference(PreferenceScreen preferenceScreen) {
        this.mPreferenceGroup = (PreferenceGroup) preferenceScreen.findPreference(this.mPreferenceGroupKey);
        update();
    }

    private void update() {
        if (this.mPreferenceGroup == null) {
            return;
        }
        if (!isAvailable()) {
            Iterator<Preference> it = this.mSubscriptionPreferences.values().iterator();
            while (it.hasNext()) {
                this.mPreferenceGroup.removePreference(it.next());
            }
            this.mSubscriptionPreferences.clear();
            this.mUpdateListener.onChildrenUpdated();
            return;
        }
        Map<Integer, Preference> map = this.mSubscriptionPreferences;
        this.mSubscriptionPreferences = new ArrayMap();
        int i = this.mStartOrder;
        for (SubscriptionInfo subscriptionInfo : SubscriptionUtil.getActiveSubscriptions(this.mManager)) {
            final int subscriptionId = subscriptionInfo.getSubscriptionId();
            Preference preferenceRemove = map.remove(Integer.valueOf(subscriptionId));
            if (preferenceRemove == null) {
                preferenceRemove = new Preference(this.mPreferenceGroup.getContext());
                this.mPreferenceGroup.addPreference(preferenceRemove);
            }
            preferenceRemove.setTitle(subscriptionInfo.getDisplayName());
            preferenceRemove.setSummary(getSummary(subscriptionId));
            preferenceRemove.setIcon(R.drawable.ic_network_cell);
            preferenceRemove.setOrder(i);
            preferenceRemove.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() { // from class: com.android.car.developeroptions.network.-$$Lambda$SubscriptionsPreferenceController$6s93Mg4MbTy9IVyguc9ix6SKX-4
                @Override // androidx.preference.Preference.OnPreferenceClickListener
                public final boolean onPreferenceClick(Preference preference) {
                    return this.f$0.lambda$update$0$SubscriptionsPreferenceController(subscriptionId, preference);
                }
            });
            this.mSubscriptionPreferences.put(Integer.valueOf(subscriptionId), preferenceRemove);
            i++;
        }
        Iterator<Preference> it2 = map.values().iterator();
        while (it2.hasNext()) {
            this.mPreferenceGroup.removePreference(it2.next());
        }
        this.mUpdateListener.onChildrenUpdated();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: lambda$update$0, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ boolean lambda$update$0$SubscriptionsPreferenceController(int i, Preference preference) {
        Intent intent = new Intent(this.mContext, (Class<?>) MobileNetworkActivity.class);
        intent.putExtra("android.provider.extra.SUB_ID", i);
        this.mContext.startActivity(intent);
        return true;
    }

    protected String getSummary(int i) {
        String string;
        int defaultVoiceSubscriptionId = SubscriptionManager.getDefaultVoiceSubscriptionId();
        int defaultSmsSubscriptionId = SubscriptionManager.getDefaultSmsSubscriptionId();
        int defaultDataSubscriptionId = SubscriptionManager.getDefaultDataSubscriptionId();
        String string2 = null;
        if (i == defaultVoiceSubscriptionId && i == defaultSmsSubscriptionId) {
            string = this.mContext.getString(R.string.default_for_calls_and_sms);
        } else if (i == defaultVoiceSubscriptionId) {
            string = this.mContext.getString(R.string.default_for_calls);
        } else {
            string = i == defaultSmsSubscriptionId ? this.mContext.getString(R.string.default_for_sms) : null;
        }
        if (i == defaultDataSubscriptionId) {
            TelephonyManager telephonyManagerCreateForSubscriptionId = ((TelephonyManager) this.mContext.getSystemService(TelephonyManager.class)).createForSubscriptionId(i);
            if (telephonyManagerCreateForSubscriptionId.getDataState() == 2) {
                string2 = this.mContext.getString(R.string.mobile_data_active);
            } else if (!telephonyManagerCreateForSubscriptionId.isDataEnabled()) {
                string2 = this.mContext.getString(R.string.mobile_data_off);
            } else {
                string2 = this.mContext.getString(R.string.default_for_mobile_data);
            }
        }
        return (string == null || string2 == null) ? string != null ? string : string2 != null ? string2 : this.mContext.getString(R.string.subscription_available) : String.join(System.lineSeparator(), string, string2);
    }

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public boolean isAvailable() {
        return !this.mSubscriptionsListener.isAirplaneModeOn() && SubscriptionUtil.getActiveSubscriptions(this.mManager).size() >= 2;
    }

    @Override // com.android.car.developeroptions.network.SubscriptionsChangeListener.SubscriptionsChangeListenerClient
    public void onAirplaneModeChanged(boolean z) {
        update();
    }

    @Override // com.android.car.developeroptions.network.SubscriptionsChangeListener.SubscriptionsChangeListenerClient
    public void onSubscriptionsChanged() {
        update();
    }
}
