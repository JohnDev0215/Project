package com.android.car.settings.network;

import android.car.drivingstate.CarUxRestrictions;
import android.content.Context;
import android.telephony.SubscriptionInfo;
import android.telephony.SubscriptionManager;
import android.telephony.TelephonyManager;
import androidx.preference.Preference;
import androidx.preference.PreferenceGroup;
import com.android.car.settings.R;
import com.android.car.settings.common.FragmentController;
import com.android.car.settings.common.PreferenceController;
import com.android.car.settings.network.SubscriptionsChangeListener;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public class MobileNetworkListPreferenceController extends PreferenceController<PreferenceGroup> implements SubscriptionsChangeListener.SubscriptionsChangeAction {
    private final SubscriptionManager mSubscriptionManager;
    private final SubscriptionsChangeListener mSubscriptionsChangeListener;
    private final TelephonyManager mTelephonyManager;

    public MobileNetworkListPreferenceController(Context context, String preferenceKey, FragmentController fragmentController, CarUxRestrictions uxRestrictions) {
        super(context, preferenceKey, fragmentController, uxRestrictions);
        this.mSubscriptionsChangeListener = new SubscriptionsChangeListener(context, this);
        this.mSubscriptionManager = (SubscriptionManager) context.getSystemService(SubscriptionManager.class);
        this.mTelephonyManager = (TelephonyManager) context.getSystemService(TelephonyManager.class);
    }

    @Override // com.android.car.settings.common.PreferenceController
    protected Class<PreferenceGroup> getPreferenceType() {
        return PreferenceGroup.class;
    }

    @Override // com.android.car.settings.common.PreferenceController
    protected void onStartInternal() {
        this.mSubscriptionsChangeListener.start();
    }

    @Override // com.android.car.settings.common.PreferenceController
    protected void onStopInternal() {
        this.mSubscriptionsChangeListener.stop();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.android.car.settings.common.PreferenceController
    public void updateState(PreferenceGroup preferenceGroup) {
        preferenceGroup.removeAll();
        List<SubscriptionInfo> subscriptions = SubscriptionUtils.getAvailableSubscriptions(this.mSubscriptionManager, this.mTelephonyManager);
        for (SubscriptionInfo info : subscriptions) {
            preferenceGroup.addPreference(createPreference(info));
        }
    }

    @Override // com.android.car.settings.network.SubscriptionsChangeListener.SubscriptionsChangeAction
    public void onSubscriptionsChanged() {
        refreshUi();
    }

    private Preference createPreference(final SubscriptionInfo info) {
        Preference preference = new Preference(getContext());
        preference.setTitle(info.getDisplayName());
        preference.setKey(Integer.toString(info.getSubscriptionId()));
        boolean isEsim = info.isEmbedded();
        if (this.mSubscriptionManager.isActiveSubscriptionId(info.getSubscriptionId())) {
            preference.setSummary(isEsim ? R.string.mobile_network_active_esim : R.string.mobile_network_active_sim);
        } else {
            preference.setSummary(isEsim ? R.string.mobile_network_inactive_esim : R.string.mobile_network_inactive_sim);
        }
        preference.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() { // from class: com.android.car.settings.network.-$$Lambda$MobileNetworkListPreferenceController$woURrzaID_vaJoOPfam_m5T-bDM
            @Override // androidx.preference.Preference.OnPreferenceClickListener
            public final boolean onPreferenceClick(Preference preference2) {
                return this.f$0.lambda$createPreference$0$MobileNetworkListPreferenceController(info, preference2);
            }
        });
        return preference;
    }

    public /* synthetic */ boolean lambda$createPreference$0$MobileNetworkListPreferenceController(SubscriptionInfo info, Preference pref) {
        MobileNetworkFragment fragment = MobileNetworkFragment.newInstance(info.getSubscriptionId());
        getFragmentController().launchFragment(fragment);
        return true;
    }
}
