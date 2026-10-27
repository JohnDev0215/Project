package com.android.car.settings.network;

import android.car.drivingstate.CarUxRestrictions;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.UserManager;
import android.telephony.SubscriptionInfo;
import android.telephony.SubscriptionManager;
import android.telephony.TelephonyManager;
import androidx.preference.Preference;
import com.android.car.settings.R;
import com.android.car.settings.common.CarSettingActivities;
import com.android.car.settings.common.FragmentController;
import com.android.car.settings.common.PreferenceController;
import com.android.car.settings.network.SubscriptionsChangeListener;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public class MobileNetworkEntryPreferenceController extends PreferenceController<Preference> implements SubscriptionsChangeListener.SubscriptionsChangeAction {
    private final SubscriptionsChangeListener mChangeListener;
    private final ConnectivityManager mConnectivityManager;
    private final SubscriptionManager mSubscriptionManager;
    private final TelephonyManager mTelephonyManager;
    private final UserManager mUserManager;

    public MobileNetworkEntryPreferenceController(Context context, String preferenceKey, FragmentController fragmentController, CarUxRestrictions uxRestrictions) {
        super(context, preferenceKey, fragmentController, uxRestrictions);
        this.mUserManager = UserManager.get(context);
        this.mChangeListener = new SubscriptionsChangeListener(context, this);
        this.mSubscriptionManager = (SubscriptionManager) context.getSystemService(SubscriptionManager.class);
        this.mConnectivityManager = (ConnectivityManager) context.getSystemService(ConnectivityManager.class);
        this.mTelephonyManager = (TelephonyManager) context.getSystemService(TelephonyManager.class);
    }

    @Override // com.android.car.settings.common.PreferenceController
    protected Class<Preference> getPreferenceType() {
        return Preference.class;
    }

    @Override // com.android.car.settings.common.PreferenceController
    protected void onStartInternal() {
        this.mChangeListener.start();
    }

    @Override // com.android.car.settings.common.PreferenceController
    protected void onStopInternal() {
        this.mChangeListener.stop();
    }

    @Override // com.android.car.settings.common.PreferenceController
    protected int getAvailabilityStatus() {
        if (!NetworkUtils.hasMobileNetwork(this.mConnectivityManager)) {
            return 2;
        }
        boolean isNotAdmin = !this.mUserManager.isAdminUser();
        boolean hasRestriction = this.mUserManager.hasUserRestriction("no_config_mobile_networks");
        if (isNotAdmin || hasRestriction) {
            return 3;
        }
        return 0;
    }

    @Override // com.android.car.settings.common.PreferenceController
    protected void updateState(Preference preference) {
        List<SubscriptionInfo> subs = SubscriptionUtils.getAvailableSubscriptions(this.mSubscriptionManager, this.mTelephonyManager);
        preference.setEnabled(!subs.isEmpty());
        preference.setSummary(getSummary(subs));
    }

    @Override // com.android.car.settings.common.PreferenceController
    protected boolean handlePreferenceClicked(Preference preference) {
        List<SubscriptionInfo> subs = SubscriptionUtils.getAvailableSubscriptions(this.mSubscriptionManager, this.mTelephonyManager);
        if (subs.isEmpty()) {
            return true;
        }
        if (subs.size() == 1) {
            Intent intent = new Intent(getContext(), (Class<?>) CarSettingActivities.MobileNetworkActivity.class);
            intent.putExtra("network_sub_id", subs.get(0).getSubscriptionId());
            getContext().startActivity(intent);
        } else {
            getContext().startActivity(new Intent(getContext(), (Class<?>) CarSettingActivities.MobileNetworkListActivity.class));
        }
        return true;
    }

    @Override // com.android.car.settings.network.SubscriptionsChangeListener.SubscriptionsChangeAction
    public void onSubscriptionsChanged() {
        refreshUi();
    }

    private CharSequence getSummary(List<SubscriptionInfo> subs) {
        int count = subs.size();
        if (subs.isEmpty()) {
            return null;
        }
        if (count == 1) {
            return subs.get(0).getDisplayName();
        }
        return getContext().getResources().getQuantityString(R.plurals.mobile_network_summary_count, count, Integer.valueOf(count));
    }
}
