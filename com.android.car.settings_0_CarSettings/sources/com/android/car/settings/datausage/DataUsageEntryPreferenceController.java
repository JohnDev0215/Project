package com.android.car.settings.datausage;

import android.car.drivingstate.CarUxRestrictions;
import android.content.Context;
import android.net.ConnectivityManager;
import android.telephony.SubscriptionManager;
import android.telephony.SubscriptionPlan;
import androidx.preference.Preference;
import com.android.car.settings.common.FragmentController;
import com.android.car.settings.common.PreferenceController;
import com.android.car.settings.network.NetworkUtils;

/* JADX INFO: loaded from: classes5.dex */
public class DataUsageEntryPreferenceController extends PreferenceController<Preference> {
    public DataUsageEntryPreferenceController(Context context, String preferenceKey, FragmentController fragmentController, CarUxRestrictions uxRestrictions) {
        super(context, preferenceKey, fragmentController, uxRestrictions);
    }

    @Override // com.android.car.settings.common.PreferenceController
    protected Class<Preference> getPreferenceType() {
        return Preference.class;
    }

    @Override // com.android.car.settings.common.PreferenceController
    protected int getAvailabilityStatus() {
        if (!NetworkUtils.hasMobileNetwork((ConnectivityManager) getContext().getSystemService(ConnectivityManager.class))) {
            return 2;
        }
        return 0;
    }

    @Override // com.android.car.settings.common.PreferenceController
    protected void updateState(Preference preference) {
        preference.setSummary(formatUsedData());
    }

    private CharSequence formatUsedData() {
        SubscriptionPlan defaultPlan;
        SubscriptionManager subscriptionManager = (SubscriptionManager) getContext().getSystemService(SubscriptionManager.class);
        int defaultSubId = SubscriptionManager.getDefaultSubscriptionId();
        if (defaultSubId == -1 || (defaultPlan = DataUsageUtils.getPrimaryPlan(subscriptionManager, defaultSubId)) == null) {
            return null;
        }
        return DataUsageUtils.bytesToIecUnits(getContext(), defaultPlan.getDataUsageBytes());
    }
}
