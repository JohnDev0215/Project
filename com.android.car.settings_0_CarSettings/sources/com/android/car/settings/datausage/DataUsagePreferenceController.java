package com.android.car.settings.datausage;

import android.car.drivingstate.CarUxRestrictions;
import android.content.Context;
import android.telephony.SubscriptionManager;
import androidx.preference.Preference;
import com.android.car.settings.common.FragmentController;
import com.android.car.settings.common.PreferenceController;

/* JADX INFO: loaded from: classes5.dex */
public class DataUsagePreferenceController extends PreferenceController<Preference> {
    private int mSubId;
    private SubscriptionManager mSubscriptionManager;

    public DataUsagePreferenceController(Context context, String preferenceKey, FragmentController fragmentController, CarUxRestrictions uxRestrictions) {
        super(context, preferenceKey, fragmentController, uxRestrictions);
        this.mSubId = Integer.MIN_VALUE;
        this.mSubscriptionManager = (SubscriptionManager) context.getSystemService(SubscriptionManager.class);
    }

    @Override // com.android.car.settings.common.PreferenceController
    protected Class<Preference> getPreferenceType() {
        return Preference.class;
    }

    @Override // com.android.car.settings.common.PreferenceController
    protected boolean handlePreferenceClicked(Preference preference) {
        int subId = this.mSubId;
        if (subId == Integer.MIN_VALUE) {
            subId = getDefaultSubId();
        }
        AppDataUsageFragment appDataUsageFragment = AppDataUsageFragment.newInstance(subId);
        getFragmentController().launchFragment(appDataUsageFragment);
        return true;
    }

    public void setSubId(int subId) {
        this.mSubId = subId;
    }

    private int getDefaultSubId() {
        return DataUsageUtils.getDefaultSubscriptionId(this.mSubscriptionManager);
    }
}
