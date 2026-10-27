package com.android.car.developeroptions.network.telephony.cdma;

import android.content.Context;
import android.telephony.TelephonyManager;
import android.util.AttributeSet;
import androidx.preference.ListPreference;

/* JADX INFO: loaded from: classes.dex */
public class CdmaListPreference extends ListPreference {
    private TelephonyManager mTelephonyManager;

    public CdmaListPreference(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    @Override // androidx.preference.DialogPreference, androidx.preference.Preference
    protected void onClick() {
        TelephonyManager telephonyManager = this.mTelephonyManager;
        if (telephonyManager == null || !telephonyManager.getEmergencyCallbackMode()) {
            super.onClick();
        }
    }

    public void setSubId(int i) {
        this.mTelephonyManager = TelephonyManager.from(getContext()).createForSubscriptionId(i);
    }
}
