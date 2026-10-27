package com.android.car.developeroptions.network;

import android.content.Context;
import android.content.Intent;
import android.telephony.SubscriptionInfo;
import android.telephony.SubscriptionManager;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;
import androidx.preference.Preference;
import androidx.preference.PreferenceScreen;
import com.android.car.developeroptions.R;
import com.android.car.developeroptions.core.PreferenceControllerMixin;
import com.android.car.developeroptions.network.SubscriptionsChangeListener;
import com.android.car.developeroptions.network.telephony.MobileNetworkActivity;
import com.android.car.developeroptions.network.telephony.MobileNetworkUtils;
import com.android.car.developeroptions.widget.AddPreference;
import com.android.settingslib.Utils;
import com.android.settingslib.core.AbstractPreferenceController;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class MobileNetworkSummaryController extends AbstractPreferenceController implements SubscriptionsChangeListener.SubscriptionsChangeListenerClient, LifecycleObserver, PreferenceControllerMixin {
    private SubscriptionsChangeListener mChangeListener;
    private AddPreference mPreference;
    private SubscriptionManager mSubscriptionManager;

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public String getPreferenceKey() {
        return "mobile_network_list";
    }

    public MobileNetworkSummaryController(Context context, Lifecycle lifecycle) {
        super(context);
        this.mSubscriptionManager = (SubscriptionManager) context.getSystemService(SubscriptionManager.class);
        if (lifecycle != null) {
            this.mChangeListener = new SubscriptionsChangeListener(context, this);
            lifecycle.addObserver(this);
        }
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
        this.mPreference = (AddPreference) preferenceScreen.findPreference(getPreferenceKey());
    }

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public CharSequence getSummary() {
        List<SubscriptionInfo> availableSubscriptions = SubscriptionUtil.getAvailableSubscriptions(this.mSubscriptionManager);
        if (availableSubscriptions.isEmpty()) {
            if (MobileNetworkUtils.showEuiccSettings(this.mContext)) {
                return this.mContext.getResources().getString(R.string.mobile_network_summary_add_a_network);
            }
            return null;
        }
        if (availableSubscriptions.size() == 1) {
            return availableSubscriptions.get(0).getDisplayName();
        }
        int size = availableSubscriptions.size();
        return this.mContext.getResources().getQuantityString(R.plurals.mobile_network_summary_count, size, Integer.valueOf(size));
    }

    private void startAddSimFlow() {
        Intent intent = new Intent("android.telephony.euicc.action.PROVISION_EMBEDDED_SUBSCRIPTION");
        intent.putExtra("android.telephony.euicc.extra.FORCE_PROVISION", true);
        this.mContext.startActivity(intent);
    }

    private void update() {
        AddPreference addPreference = this.mPreference;
        if (addPreference == null) {
            return;
        }
        refreshSummary(addPreference);
        this.mPreference.setOnPreferenceClickListener(null);
        this.mPreference.setOnAddClickListener(null);
        this.mPreference.setFragment(null);
        this.mPreference.setEnabled(!this.mChangeListener.isAirplaneModeOn());
        List<SubscriptionInfo> availableSubscriptions = SubscriptionUtil.getAvailableSubscriptions(this.mSubscriptionManager);
        if (availableSubscriptions.isEmpty()) {
            if (MobileNetworkUtils.showEuiccSettings(this.mContext)) {
                this.mPreference.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() { // from class: com.android.car.developeroptions.network.-$$Lambda$MobileNetworkSummaryController$mrsBpKGrHUOn8h8tW2Bst_gDwf8
                    @Override // androidx.preference.Preference.OnPreferenceClickListener
                    public final boolean onPreferenceClick(Preference preference) {
                        return this.f$0.lambda$update$0$MobileNetworkSummaryController(preference);
                    }
                });
                return;
            }
            return;
        }
        if (MobileNetworkUtils.showEuiccSettings(this.mContext)) {
            this.mPreference.setAddWidgetEnabled(!this.mChangeListener.isAirplaneModeOn());
            this.mPreference.setOnAddClickListener(new AddPreference.OnAddClickListener() { // from class: com.android.car.developeroptions.network.-$$Lambda$MobileNetworkSummaryController$T-yEKcDGi7uEEbDGXHMfpYwd2yM
                @Override // com.android.car.developeroptions.widget.AddPreference.OnAddClickListener
                public final void onAddClick(AddPreference addPreference2) {
                    this.f$0.lambda$update$1$MobileNetworkSummaryController(addPreference2);
                }
            });
        }
        if (availableSubscriptions.size() == 1) {
            this.mPreference.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() { // from class: com.android.car.developeroptions.network.-$$Lambda$MobileNetworkSummaryController$Qqvl2d0rVle4sfGLhHpueMRngVs
                @Override // androidx.preference.Preference.OnPreferenceClickListener
                public final boolean onPreferenceClick(Preference preference) {
                    return this.f$0.lambda$update$2$MobileNetworkSummaryController(preference);
                }
            });
        } else {
            this.mPreference.setFragment(MobileNetworkListFragment.class.getCanonicalName());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: lambda$update$0, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ boolean lambda$update$0$MobileNetworkSummaryController(Preference preference) {
        startAddSimFlow();
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: lambda$update$1, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void lambda$update$1$MobileNetworkSummaryController(AddPreference addPreference) {
        startAddSimFlow();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: lambda$update$2, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ boolean lambda$update$2$MobileNetworkSummaryController(Preference preference) {
        this.mContext.startActivity(new Intent(this.mContext, (Class<?>) MobileNetworkActivity.class));
        return true;
    }

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public boolean isAvailable() {
        return !Utils.isWifiOnly(this.mContext);
    }

    @Override // com.android.car.developeroptions.network.SubscriptionsChangeListener.SubscriptionsChangeListenerClient
    public void onAirplaneModeChanged(boolean z) {
        update();
    }

    @Override // com.android.car.developeroptions.network.SubscriptionsChangeListener.SubscriptionsChangeListenerClient
    public void onSubscriptionsChanged() {
        refreshSummary(this.mPreference);
        update();
    }
}
