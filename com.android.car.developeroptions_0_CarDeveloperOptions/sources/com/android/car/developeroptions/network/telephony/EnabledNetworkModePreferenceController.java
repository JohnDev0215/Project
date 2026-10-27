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
public class EnabledNetworkModePreferenceController extends TelephonyBasePreferenceController implements Preference.OnPreferenceChangeListener {
    private CarrierConfigManager mCarrierConfigManager;
    private boolean mIsGlobalCdma;
    boolean mShow4GForLTE;
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

    public EnabledNetworkModePreferenceController(Context context, String str) {
        super(context, str);
        this.mCarrierConfigManager = (CarrierConfigManager) context.getSystemService(CarrierConfigManager.class);
    }

    @Override // com.android.car.developeroptions.network.telephony.TelephonyBasePreferenceController, com.android.car.developeroptions.network.telephony.TelephonyAvailabilityCallback
    public int getAvailabilityStatus(int i) {
        PersistableBundle configForSubId = this.mCarrierConfigManager.getConfigForSubId(i);
        TelephonyManager telephonyManagerCreateForSubscriptionId = TelephonyManager.from(this.mContext).createForSubscriptionId(i);
        return i != -1 && configForSubId != null && !configForSubId.getBoolean("hide_carrier_network_settings_bool") && ((!configForSubId.getBoolean("hide_preferred_network_type_bool") || telephonyManagerCreateForSubscriptionId.getServiceState().getRoaming() || telephonyManagerCreateForSubscriptionId.getServiceState().getDataRegState() != 0) && !configForSubId.getBoolean("world_phone_bool")) ? 0 : 2;
    }

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public void updateState(Preference preference) {
        super.updateState(preference);
        ListPreference listPreference = (ListPreference) preference;
        int preferredNetworkMode = getPreferredNetworkMode();
        updatePreferenceEntries(listPreference);
        updatePreferenceValueAndSummary(listPreference, preferredNetworkMode);
    }

    @Override // androidx.preference.Preference.OnPreferenceChangeListener
    public boolean onPreferenceChange(Preference preference, Object obj) {
        int i = Integer.parseInt((String) obj);
        if (!this.mTelephonyManager.setPreferredNetworkType(this.mSubId, i)) {
            return false;
        }
        Settings.Global.putInt(this.mContext.getContentResolver(), "preferred_network_mode" + this.mSubId, i);
        updatePreferenceValueAndSummary((ListPreference) preference, i);
        return true;
    }

    public void init(int i) {
        this.mSubId = i;
        PersistableBundle configForSubId = this.mCarrierConfigManager.getConfigForSubId(i);
        TelephonyManager telephonyManagerCreateForSubscriptionId = TelephonyManager.from(this.mContext).createForSubscriptionId(this.mSubId);
        this.mTelephonyManager = telephonyManagerCreateForSubscriptionId;
        this.mIsGlobalCdma = telephonyManagerCreateForSubscriptionId.isLteCdmaEvdoGsmWcdmaEnabled() && configForSubId.getBoolean("show_cdma_choices_bool");
        this.mShow4GForLTE = configForSubId != null ? configForSubId.getBoolean("show_4g_for_lte_data_icon_bool") : false;
    }

    private int getPreferredNetworkMode() {
        return Settings.Global.getInt(this.mContext.getContentResolver(), "preferred_network_mode" + this.mSubId, Phone.PREFERRED_NT_MODE);
    }

    private void updatePreferenceEntries(ListPreference listPreference) {
        int phoneType = this.mTelephonyManager.getPhoneType();
        PersistableBundle configForSubId = this.mCarrierConfigManager.getConfigForSubId(this.mSubId);
        if (phoneType == 2) {
            int i = Settings.Global.getInt(this.mContext.getContentResolver(), "lte_service_forced" + this.mSubId, 0);
            int i2 = Settings.Global.getInt(this.mContext.getContentResolver(), "preferred_network_mode" + this.mSubId, Phone.PREFERRED_NT_MODE);
            if (this.mTelephonyManager.isLteCdmaEvdoGsmWcdmaEnabled()) {
                if (i == 0) {
                    listPreference.setEntries(R.array.enabled_networks_cdma_choices);
                    listPreference.setEntryValues(R.array.enabled_networks_cdma_values);
                } else {
                    switch (i2) {
                        case 4:
                        case 5:
                        case LogProto$SettingsLog.TIMESTAMP_FIELD_NUMBER /* 6 */:
                            listPreference.setEntries(R.array.enabled_networks_cdma_no_lte_choices);
                            listPreference.setEntryValues(R.array.enabled_networks_cdma_no_lte_values);
                            break;
                        case 7:
                        case 8:
                        case 10:
                        case 11:
                            listPreference.setEntries(R.array.enabled_networks_cdma_only_lte_choices);
                            listPreference.setEntryValues(R.array.enabled_networks_cdma_only_lte_values);
                            break;
                        case 9:
                        default:
                            listPreference.setEntries(R.array.enabled_networks_cdma_choices);
                            listPreference.setEntryValues(R.array.enabled_networks_cdma_values);
                            break;
                    }
                }
            }
        } else if (phoneType == 1) {
            if (MobileNetworkUtils.isTdscdmaSupported(this.mContext, this.mSubId)) {
                listPreference.setEntries(R.array.enabled_networks_tdscdma_choices);
                listPreference.setEntryValues(R.array.enabled_networks_tdscdma_values);
            } else if (configForSubId != null && !configForSubId.getBoolean("prefer_2g_bool") && !configForSubId.getBoolean("lte_enabled_bool")) {
                listPreference.setEntries(R.array.enabled_networks_except_gsm_lte_choices);
                listPreference.setEntryValues(R.array.enabled_networks_except_gsm_lte_values);
            } else if (configForSubId != null && !configForSubId.getBoolean("prefer_2g_bool")) {
                listPreference.setEntries(this.mShow4GForLTE ? R.array.enabled_networks_except_gsm_4g_choices : R.array.enabled_networks_except_gsm_choices);
                listPreference.setEntryValues(R.array.enabled_networks_except_gsm_values);
            } else if (configForSubId != null && !configForSubId.getBoolean("lte_enabled_bool")) {
                listPreference.setEntries(R.array.enabled_networks_except_lte_choices);
                listPreference.setEntryValues(R.array.enabled_networks_except_lte_values);
            } else if (this.mIsGlobalCdma) {
                listPreference.setEntries(R.array.enabled_networks_cdma_choices);
                listPreference.setEntryValues(R.array.enabled_networks_cdma_values);
            } else {
                listPreference.setEntries(this.mShow4GForLTE ? R.array.enabled_networks_4g_choices : R.array.enabled_networks_choices);
                listPreference.setEntryValues(R.array.enabled_networks_values);
            }
        }
        if (MobileNetworkUtils.isWorldMode(this.mContext, this.mSubId)) {
            listPreference.setEntries(R.array.preferred_network_mode_choices_world_mode);
            listPreference.setEntryValues(R.array.preferred_network_mode_values_world_mode);
        }
    }

    private void updatePreferenceValueAndSummary(ListPreference listPreference, int i) {
        listPreference.setValue(Integer.toString(i));
        int i2 = R.string.network_4G;
        switch (i) {
            case 0:
            case 2:
            case 3:
                if (!this.mIsGlobalCdma) {
                    listPreference.setValue(Integer.toString(0));
                    listPreference.setSummary(R.string.network_3G);
                    return;
                } else {
                    listPreference.setValue(Integer.toString(10));
                    listPreference.setSummary(R.string.network_global);
                    return;
                }
            case 1:
                if (!this.mIsGlobalCdma) {
                    listPreference.setValue(Integer.toString(1));
                    listPreference.setSummary(R.string.network_2G);
                    return;
                } else {
                    listPreference.setValue(Integer.toString(10));
                    listPreference.setSummary(R.string.network_global);
                    return;
                }
            case 4:
            case LogProto$SettingsLog.TIMESTAMP_FIELD_NUMBER /* 6 */:
            case 7:
                listPreference.setValue(Integer.toString(4));
                listPreference.setSummary(R.string.network_3G);
                return;
            case 5:
                listPreference.setValue(Integer.toString(5));
                listPreference.setSummary(R.string.network_1x);
                return;
            case 8:
                if (MobileNetworkUtils.isWorldMode(this.mContext, this.mSubId)) {
                    listPreference.setSummary(R.string.preferred_network_mode_lte_cdma_summary);
                    return;
                } else {
                    listPreference.setValue(Integer.toString(8));
                    listPreference.setSummary(R.string.network_lte);
                    return;
                }
            case 9:
                if (MobileNetworkUtils.isWorldMode(this.mContext, this.mSubId)) {
                    listPreference.setSummary(R.string.preferred_network_mode_lte_gsm_umts_summary);
                    return;
                }
                break;
            case 10:
            case 15:
            case 17:
            case 19:
            case 20:
            case 22:
                if (MobileNetworkUtils.isTdscdmaSupported(this.mContext, this.mSubId)) {
                    listPreference.setValue(Integer.toString(22));
                    listPreference.setSummary(R.string.network_lte);
                    return;
                }
                listPreference.setValue(Integer.toString(10));
                if (this.mTelephonyManager.getPhoneType() == 2 || this.mIsGlobalCdma || MobileNetworkUtils.isWorldMode(this.mContext, this.mSubId)) {
                    listPreference.setSummary(R.string.network_global);
                    return;
                }
                if (!this.mShow4GForLTE) {
                    i2 = R.string.network_lte;
                }
                listPreference.setSummary(i2);
                return;
            case 11:
            case 12:
                break;
            case 13:
                listPreference.setValue(Integer.toString(13));
                listPreference.setSummary(R.string.network_3G);
                return;
            case 14:
            case 16:
            case 18:
                listPreference.setValue(Integer.toString(18));
                listPreference.setSummary(R.string.network_3G);
                return;
            case 21:
                listPreference.setValue(Integer.toString(21));
                listPreference.setSummary(R.string.network_3G);
                return;
            default:
                listPreference.setSummary(this.mContext.getString(R.string.mobile_network_mode_error, Integer.valueOf(i)));
                return;
        }
        if (!this.mIsGlobalCdma) {
            listPreference.setValue(Integer.toString(9));
            if (!this.mShow4GForLTE) {
                i2 = R.string.network_lte;
            }
            listPreference.setSummary(i2);
            return;
        }
        listPreference.setValue(Integer.toString(10));
        listPreference.setSummary(R.string.network_global);
    }
}
