package com.android.car.developeroptions.network.telephony;

import android.content.Context;
import android.content.IntentFilter;
import android.os.PersistableBundle;
import android.provider.Settings;
import android.telephony.CarrierConfigManager;
import android.telephony.TelephonyManager;
import androidx.preference.ListPreference;
import androidx.preference.Preference;
import com.android.car.developeroptions.R;
import com.android.car.developeroptions.intelligence.LogProto$SettingsLog;
import com.android.car.developeroptions.slices.SliceBackgroundWorker;
import com.android.internal.telephony.Phone;

/* JADX INFO: loaded from: classes.dex */
public class PreferredNetworkModePreferenceController extends TelephonyBasePreferenceController implements Preference.OnPreferenceChangeListener {
    private CarrierConfigManager mCarrierConfigManager;
    private boolean mIsGlobalCdma;
    private PersistableBundle mPersistableBundle;
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

    public PreferredNetworkModePreferenceController(Context context, String str) {
        super(context, str);
        this.mCarrierConfigManager = (CarrierConfigManager) context.getSystemService(CarrierConfigManager.class);
    }

    @Override // com.android.car.developeroptions.network.telephony.TelephonyBasePreferenceController, com.android.car.developeroptions.network.telephony.TelephonyAvailabilityCallback
    public int getAvailabilityStatus(int i) {
        PersistableBundle configForSubId = this.mCarrierConfigManager.getConfigForSubId(i);
        TelephonyManager telephonyManagerCreateForSubscriptionId = TelephonyManager.from(this.mContext).createForSubscriptionId(i);
        return i != -1 && configForSubId != null && !configForSubId.getBoolean("hide_carrier_network_settings_bool") && ((!configForSubId.getBoolean("hide_preferred_network_type_bool") || telephonyManagerCreateForSubscriptionId.getServiceState().getRoaming() || telephonyManagerCreateForSubscriptionId.getServiceState().getDataRegState() != 0) && configForSubId.getBoolean("world_phone_bool")) ? 0 : 2;
    }

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public void updateState(Preference preference) {
        super.updateState(preference);
        ListPreference listPreference = (ListPreference) preference;
        int preferredNetworkMode = getPreferredNetworkMode();
        listPreference.setValue(Integer.toString(preferredNetworkMode));
        listPreference.setSummary(getPreferredNetworkModeSummaryResId(preferredNetworkMode));
    }

    @Override // androidx.preference.Preference.OnPreferenceChangeListener
    public boolean onPreferenceChange(Preference preference, Object obj) {
        int i = Integer.parseInt((String) obj);
        if (!this.mTelephonyManager.setPreferredNetworkType(this.mSubId, i)) {
            return false;
        }
        Settings.Global.putInt(this.mContext.getContentResolver(), "preferred_network_mode" + this.mSubId, i);
        return true;
    }

    public void init(int i) {
        this.mSubId = i;
        PersistableBundle configForSubId = this.mCarrierConfigManager.getConfigForSubId(i);
        TelephonyManager telephonyManagerCreateForSubscriptionId = TelephonyManager.from(this.mContext).createForSubscriptionId(this.mSubId);
        this.mTelephonyManager = telephonyManagerCreateForSubscriptionId;
        this.mIsGlobalCdma = telephonyManagerCreateForSubscriptionId.isLteCdmaEvdoGsmWcdmaEnabled() && configForSubId.getBoolean("show_cdma_choices_bool");
    }

    private int getPreferredNetworkMode() {
        return Settings.Global.getInt(this.mContext.getContentResolver(), "preferred_network_mode" + this.mSubId, Phone.PREFERRED_NT_MODE);
    }

    private int getPreferredNetworkModeSummaryResId(int i) {
        switch (i) {
            case 0:
                return R.string.preferred_network_mode_wcdma_perf_summary;
            case 1:
                return R.string.preferred_network_mode_gsm_only_summary;
            case 2:
                return R.string.preferred_network_mode_wcdma_only_summary;
            case 3:
                return R.string.preferred_network_mode_gsm_wcdma_summary;
            case 4:
                return this.mTelephonyManager.isLteCdmaEvdoGsmWcdmaEnabled() ? R.string.preferred_network_mode_cdma_summary : R.string.preferred_network_mode_cdma_evdo_summary;
            case 5:
                return R.string.preferred_network_mode_cdma_only_summary;
            case LogProto$SettingsLog.TIMESTAMP_FIELD_NUMBER /* 6 */:
                return R.string.preferred_network_mode_evdo_only_summary;
            case 7:
                return R.string.preferred_network_mode_cdma_evdo_gsm_wcdma_summary;
            case 8:
                return R.string.preferred_network_mode_lte_cdma_evdo_summary;
            case 9:
                return R.string.preferred_network_mode_lte_gsm_wcdma_summary;
            case 10:
                return (this.mTelephonyManager.getPhoneType() == 2 || this.mIsGlobalCdma || MobileNetworkUtils.isWorldMode(this.mContext, this.mSubId)) ? R.string.preferred_network_mode_global_summary : R.string.preferred_network_mode_lte_summary;
            case 11:
                return R.string.preferred_network_mode_lte_summary;
            case 12:
                return R.string.preferred_network_mode_lte_wcdma_summary;
            case 13:
                return R.string.preferred_network_mode_tdscdma_summary;
            case 14:
                return R.string.preferred_network_mode_tdscdma_wcdma_summary;
            case 15:
                return R.string.preferred_network_mode_lte_tdscdma_summary;
            case 16:
                return R.string.preferred_network_mode_tdscdma_gsm_summary;
            case 17:
                return R.string.preferred_network_mode_lte_tdscdma_gsm_summary;
            case 18:
                return R.string.preferred_network_mode_tdscdma_gsm_wcdma_summary;
            case 19:
                return R.string.preferred_network_mode_lte_tdscdma_wcdma_summary;
            case 20:
                return R.string.preferred_network_mode_lte_tdscdma_gsm_wcdma_summary;
            case 21:
                return R.string.preferred_network_mode_tdscdma_cdma_evdo_gsm_wcdma_summary;
            case 22:
                return R.string.preferred_network_mode_lte_tdscdma_cdma_evdo_gsm_wcdma_summary;
            default:
                return R.string.preferred_network_mode_global_summary;
        }
    }
}
