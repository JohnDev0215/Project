package com.android.car.developeroptions.network.telephony.gsm;

import android.content.Context;
import android.content.IntentFilter;
import android.os.Bundle;
import android.telephony.ServiceState;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import androidx.preference.Preference;
import androidx.preference.PreferenceScreen;
import com.android.car.developeroptions.R;
import com.android.car.developeroptions.core.SubSettingLauncher;
import com.android.car.developeroptions.network.telephony.MobileNetworkUtils;
import com.android.car.developeroptions.network.telephony.NetworkSelectSettings;
import com.android.car.developeroptions.network.telephony.TelephonyBasePreferenceController;
import com.android.car.developeroptions.network.telephony.gsm.AutoSelectPreferenceController;
import com.android.car.developeroptions.slices.SliceBackgroundWorker;

/* JADX INFO: loaded from: classes.dex */
public class OpenNetworkSelectPagePreferenceController extends TelephonyBasePreferenceController implements AutoSelectPreferenceController.OnNetworkSelectModeListener {
    private Preference mPreference;
    private int mSubId;
    private TelephonyManager mTelephonyManager;

    @Override // com.android.car.developeroptions.network.telephony.TelephonyBasePreferenceController, com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ void copy() {
        super.copy();
    }

    @Override // com.android.car.developeroptions.network.telephony.TelephonyBasePreferenceController, com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ Class<? extends SliceBackgroundWorker> getBackgroundWorkerClass() {
        return super.getBackgroundWorkerClass();
    }

    @Override // com.android.car.developeroptions.network.telephony.TelephonyBasePreferenceController, com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ IntentFilter getIntentFilter() {
        return super.getIntentFilter();
    }

    @Override // com.android.car.developeroptions.network.telephony.TelephonyBasePreferenceController, com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ boolean hasAsyncUpdate() {
        return super.hasAsyncUpdate();
    }

    @Override // com.android.car.developeroptions.network.telephony.TelephonyBasePreferenceController, com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ boolean isCopyableSlice() {
        return super.isCopyableSlice();
    }

    @Override // com.android.car.developeroptions.network.telephony.TelephonyBasePreferenceController, com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ boolean isSliceable() {
        return super.isSliceable();
    }

    @Override // com.android.car.developeroptions.network.telephony.TelephonyBasePreferenceController, com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ boolean useDynamicSliceSummary() {
        return super.useDynamicSliceSummary();
    }

    public OpenNetworkSelectPagePreferenceController(Context context, String str) {
        super(context, str);
        this.mTelephonyManager = (TelephonyManager) context.getSystemService(TelephonyManager.class);
        this.mSubId = -1;
    }

    @Override // com.android.car.developeroptions.network.telephony.TelephonyBasePreferenceController, com.android.car.developeroptions.network.telephony.TelephonyAvailabilityCallback
    public int getAvailabilityStatus(int i) {
        return MobileNetworkUtils.shouldDisplayNetworkSelectOptions(this.mContext, i) ? 0 : 2;
    }

    @Override // com.android.car.developeroptions.core.BasePreferenceController, com.android.settingslib.core.AbstractPreferenceController
    public void displayPreference(PreferenceScreen preferenceScreen) {
        super.displayPreference(preferenceScreen);
        this.mPreference = preferenceScreen.findPreference(getPreferenceKey());
    }

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public void updateState(Preference preference) {
        super.updateState(preference);
        preference.setEnabled(this.mTelephonyManager.getNetworkSelectionMode() != 1);
    }

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public CharSequence getSummary() {
        ServiceState serviceState = this.mTelephonyManager.getServiceState();
        if (serviceState != null && serviceState.getState() == 0) {
            return this.mTelephonyManager.getNetworkOperatorName();
        }
        return this.mContext.getString(R.string.network_disconnected);
    }

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public boolean handlePreferenceTreeClick(Preference preference) {
        if (!TextUtils.equals(preference.getKey(), getPreferenceKey())) {
            return false;
        }
        Bundle bundle = new Bundle();
        bundle.putInt("android.provider.extra.SUB_ID", this.mSubId);
        SubSettingLauncher subSettingLauncher = new SubSettingLauncher(this.mContext);
        subSettingLauncher.setDestination(NetworkSelectSettings.class.getName());
        subSettingLauncher.setSourceMetricsCategory(1581);
        subSettingLauncher.setTitleRes(R.string.choose_network_title);
        subSettingLauncher.setArguments(bundle);
        subSettingLauncher.launch();
        return true;
    }

    public OpenNetworkSelectPagePreferenceController init(int i) {
        this.mSubId = i;
        this.mTelephonyManager = TelephonyManager.from(this.mContext).createForSubscriptionId(this.mSubId);
        return this;
    }

    @Override // com.android.car.developeroptions.network.telephony.gsm.AutoSelectPreferenceController.OnNetworkSelectModeListener
    public void onNetworkSelectModeChanged() {
        updateState(this.mPreference);
    }
}
