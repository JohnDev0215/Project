package com.android.car.developeroptions.network.telephony;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.PersistableBundle;
import android.telephony.CarrierConfigManager;
import androidx.preference.Preference;
import com.android.car.developeroptions.slices.SliceBackgroundWorker;

/* JADX INFO: loaded from: classes.dex */
public class CarrierPreferenceController extends TelephonyBasePreferenceController {
    CarrierConfigManager mCarrierConfigManager;

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

    public CarrierPreferenceController(Context context, String str) {
        super(context, str);
        this.mCarrierConfigManager = new CarrierConfigManager(context);
    }

    public void init(int i) {
        this.mSubId = i;
    }

    @Override // com.android.car.developeroptions.network.telephony.TelephonyBasePreferenceController, com.android.car.developeroptions.network.telephony.TelephonyAvailabilityCallback
    public int getAvailabilityStatus(int i) {
        PersistableBundle configForSubId = this.mCarrierConfigManager.getConfigForSubId(i);
        return (configForSubId != null && configForSubId.getBoolean("carrier_settings_enable_bool") && (MobileNetworkUtils.isCdmaOptions(this.mContext, i) || MobileNetworkUtils.isGsmOptions(this.mContext, i))) ? 0 : 2;
    }

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public boolean handlePreferenceTreeClick(Preference preference) {
        if (!getPreferenceKey().equals(preference.getKey())) {
            return false;
        }
        Intent carrierSettingsActivityIntent = getCarrierSettingsActivityIntent(this.mSubId);
        if (carrierSettingsActivityIntent == null) {
            return true;
        }
        this.mContext.startActivity(carrierSettingsActivityIntent);
        return true;
    }

    private Intent getCarrierSettingsActivityIntent(int i) {
        PersistableBundle configForSubId = this.mCarrierConfigManager.getConfigForSubId(i);
        ComponentName componentNameUnflattenFromString = ComponentName.unflattenFromString(configForSubId != null ? configForSubId.getString("carrier_settings_activity_component_name_string", "") : "");
        if (componentNameUnflattenFromString == null) {
            return null;
        }
        Intent intent = new Intent("android.intent.action.MAIN");
        intent.setComponent(componentNameUnflattenFromString);
        intent.setFlags(268435456);
        intent.putExtra("android.telephony.extra.SUBSCRIPTION_INDEX", i);
        if (this.mContext.getPackageManager().resolveActivity(intent, 0) != null) {
            return intent;
        }
        return null;
    }
}
