package com.android.car.developeroptions.network.telephony.cdma;

import android.content.Context;
import android.content.IntentFilter;
import android.os.SystemProperties;
import android.provider.Settings;
import android.text.TextUtils;
import androidx.preference.ListPreference;
import androidx.preference.Preference;
import com.android.car.developeroptions.network.telephony.MobileNetworkUtils;
import com.android.car.developeroptions.slices.SliceBackgroundWorker;

/* JADX INFO: loaded from: classes.dex */
public class CdmaSubscriptionPreferenceController extends CdmaBasePreferenceController implements Preference.OnPreferenceChangeListener {
    private static final String TYPE_NV = "NV";
    private static final String TYPE_RUIM = "RUIM";
    ListPreference mPreference;

    @Override // com.android.car.developeroptions.network.telephony.cdma.CdmaBasePreferenceController, com.android.car.developeroptions.network.telephony.TelephonyBasePreferenceController, com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ void copy() {
        super.copy();
    }

    @Override // com.android.car.developeroptions.network.telephony.cdma.CdmaBasePreferenceController, com.android.car.developeroptions.network.telephony.TelephonyBasePreferenceController, com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ Class<? extends SliceBackgroundWorker> getBackgroundWorkerClass() {
        return super.getBackgroundWorkerClass();
    }

    @Override // com.android.car.developeroptions.network.telephony.cdma.CdmaBasePreferenceController, com.android.car.developeroptions.network.telephony.TelephonyBasePreferenceController, com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ IntentFilter getIntentFilter() {
        return super.getIntentFilter();
    }

    @Override // com.android.car.developeroptions.network.telephony.cdma.CdmaBasePreferenceController, com.android.car.developeroptions.network.telephony.TelephonyBasePreferenceController, com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ boolean hasAsyncUpdate() {
        return super.hasAsyncUpdate();
    }

    @Override // com.android.car.developeroptions.network.telephony.cdma.CdmaBasePreferenceController, com.android.car.developeroptions.network.telephony.TelephonyBasePreferenceController, com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ boolean isCopyableSlice() {
        return super.isCopyableSlice();
    }

    @Override // com.android.car.developeroptions.network.telephony.cdma.CdmaBasePreferenceController, com.android.car.developeroptions.network.telephony.TelephonyBasePreferenceController, com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ boolean isSliceable() {
        return super.isSliceable();
    }

    @Override // com.android.car.developeroptions.network.telephony.cdma.CdmaBasePreferenceController, com.android.car.developeroptions.network.telephony.TelephonyBasePreferenceController, com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ boolean useDynamicSliceSummary() {
        return super.useDynamicSliceSummary();
    }

    public CdmaSubscriptionPreferenceController(Context context, String str) {
        super(context, str);
    }

    @Override // com.android.car.developeroptions.network.telephony.cdma.CdmaBasePreferenceController, com.android.car.developeroptions.network.telephony.TelephonyBasePreferenceController, com.android.car.developeroptions.network.telephony.TelephonyAvailabilityCallback
    public int getAvailabilityStatus(int i) {
        return (MobileNetworkUtils.isCdmaOptions(this.mContext, i) && deviceSupportsNvAndRuim()) ? 0 : 2;
    }

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public void updateState(Preference preference) {
        super.updateState(preference);
        ListPreference listPreference = (ListPreference) preference;
        listPreference.setVisible(getAvailabilityStatus() == 0);
        int i = Settings.Global.getInt(this.mContext.getContentResolver(), "subscription_mode", 0);
        if (i != -1) {
            listPreference.setValue(Integer.toString(i));
        }
    }

    @Override // androidx.preference.Preference.OnPreferenceChangeListener
    public boolean onPreferenceChange(Preference preference, Object obj) {
        int i = Integer.parseInt((String) obj);
        if (!this.mTelephonyManager.setCdmaSubscriptionMode(i)) {
            return false;
        }
        Settings.Global.putInt(this.mContext.getContentResolver(), "subscription_mode", i);
        return true;
    }

    boolean deviceSupportsNvAndRuim() {
        boolean z;
        boolean z2;
        String str = SystemProperties.get("ril.subscription.types");
        if (TextUtils.isEmpty(str)) {
            z = false;
            z2 = false;
        } else {
            z = false;
            z2 = false;
            for (String str2 : str.split(",")) {
                String strTrim = str2.trim();
                if (strTrim.equalsIgnoreCase(TYPE_NV)) {
                    z = true;
                } else if (strTrim.equalsIgnoreCase(TYPE_RUIM)) {
                    z2 = true;
                }
            }
        }
        return z && z2;
    }
}
