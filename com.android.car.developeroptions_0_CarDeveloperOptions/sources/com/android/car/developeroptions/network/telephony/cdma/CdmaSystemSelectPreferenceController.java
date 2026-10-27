package com.android.car.developeroptions.network.telephony.cdma;

import android.content.ContentResolver;
import android.content.Context;
import android.content.IntentFilter;
import android.provider.Settings;
import androidx.preference.ListPreference;
import androidx.preference.Preference;
import com.android.car.developeroptions.slices.SliceBackgroundWorker;
import com.android.internal.telephony.Phone;

/* JADX INFO: loaded from: classes.dex */
public class CdmaSystemSelectPreferenceController extends CdmaBasePreferenceController implements Preference.OnPreferenceChangeListener {
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

    public CdmaSystemSelectPreferenceController(Context context, String str) {
        super(context, str);
    }

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public void updateState(Preference preference) {
        super.updateState(preference);
        ListPreference listPreference = (ListPreference) preference;
        listPreference.setVisible(getAvailabilityStatus() == 0);
        int cdmaRoamingMode = this.mTelephonyManager.getCdmaRoamingMode();
        if (cdmaRoamingMode != -1) {
            if (cdmaRoamingMode == 0 || cdmaRoamingMode == 2) {
                listPreference.setValue(Integer.toString(cdmaRoamingMode));
            } else {
                resetCdmaRoamingModeToDefault();
            }
        }
        ContentResolver contentResolver = this.mContext.getContentResolver();
        StringBuilder sb = new StringBuilder();
        sb.append("preferred_network_mode");
        sb.append(((CdmaBasePreferenceController) this).mSubId);
        listPreference.setEnabled(Settings.Global.getInt(contentResolver, sb.toString(), Phone.PREFERRED_NT_MODE) != 9);
    }

    @Override // androidx.preference.Preference.OnPreferenceChangeListener
    public boolean onPreferenceChange(Preference preference, Object obj) {
        int i = Integer.parseInt((String) obj);
        if (!this.mTelephonyManager.setCdmaRoamingMode(i)) {
            return false;
        }
        Settings.Global.putInt(this.mContext.getContentResolver(), "roaming_settings", i);
        return true;
    }

    private void resetCdmaRoamingModeToDefault() {
        ((ListPreference) this.mPreference).setValue(Integer.toString(2));
        Settings.Global.putInt(this.mContext.getContentResolver(), "roaming_settings", 2);
        this.mTelephonyManager.setCdmaRoamingMode(2);
    }
}
