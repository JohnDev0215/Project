package com.android.car.developeroptions.network.telephony;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Looper;
import android.os.PersistableBundle;
import android.telecom.PhoneAccountHandle;
import android.telecom.TelecomManager;
import android.telephony.CarrierConfigManager;
import android.telephony.PhoneStateListener;
import android.telephony.SubscriptionManager;
import android.telephony.TelephonyManager;
import androidx.preference.Preference;
import androidx.preference.PreferenceScreen;
import com.android.car.developeroptions.R;
import com.android.car.developeroptions.slices.SliceBackgroundWorker;
import com.android.ims.ImsManager;
import com.android.settingslib.core.lifecycle.LifecycleObserver;
import com.android.settingslib.core.lifecycle.events.OnStart;
import com.android.settingslib.core.lifecycle.events.OnStop;

/* JADX INFO: loaded from: classes.dex */
public class WifiCallingPreferenceController extends TelephonyBasePreferenceController implements LifecycleObserver, OnStart, OnStop {
    static final String KEY_PREFERENCE_CATEGORY = "calling_category";
    CarrierConfigManager mCarrierConfigManager;
    private boolean mEditableWfcRoamingMode;
    ImsManager mImsManager;
    private PhoneCallStateListener mPhoneStateListener;
    private Preference mPreference;
    PhoneAccountHandle mSimCallManager;
    private TelephonyManager mTelephonyManager;
    private boolean mUseWfcHomeModeForRoaming;

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

    public WifiCallingPreferenceController(Context context, String str) {
        super(context, str);
        this.mCarrierConfigManager = (CarrierConfigManager) context.getSystemService(CarrierConfigManager.class);
        this.mTelephonyManager = (TelephonyManager) context.getSystemService(TelephonyManager.class);
        this.mSimCallManager = ((TelecomManager) context.getSystemService(TelecomManager.class)).getSimCallManager();
        this.mPhoneStateListener = new PhoneCallStateListener(Looper.getMainLooper());
        this.mEditableWfcRoamingMode = true;
        this.mUseWfcHomeModeForRoaming = false;
    }

    @Override // com.android.car.developeroptions.network.telephony.TelephonyBasePreferenceController, com.android.car.developeroptions.network.telephony.TelephonyAvailabilityCallback
    public int getAvailabilityStatus(int i) {
        return (i == -1 || !MobileNetworkUtils.isWifiCallingEnabled(this.mContext, SubscriptionManager.getPhoneId(i))) ? 3 : 0;
    }

    @Override // com.android.settingslib.core.lifecycle.events.OnStart
    public void onStart() {
        this.mPhoneStateListener.register(this.mSubId);
    }

    @Override // com.android.settingslib.core.lifecycle.events.OnStop
    public void onStop() {
        this.mPhoneStateListener.unregister();
    }

    @Override // com.android.car.developeroptions.core.BasePreferenceController, com.android.settingslib.core.AbstractPreferenceController
    public void displayPreference(PreferenceScreen preferenceScreen) {
        Preference preferenceFindPreference;
        super.displayPreference(preferenceScreen);
        Preference preferenceFindPreference2 = preferenceScreen.findPreference(getPreferenceKey());
        this.mPreference = preferenceFindPreference2;
        Intent intent = preferenceFindPreference2.getIntent();
        if (intent != null) {
            intent.putExtra("android.provider.extra.SUB_ID", this.mSubId);
        }
        if (isAvailable() || (preferenceFindPreference = preferenceScreen.findPreference(KEY_PREFERENCE_CATEGORY)) == null) {
            return;
        }
        preferenceFindPreference.setVisible(false);
    }

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public void updateState(Preference preference) {
        super.updateState(preference);
        PhoneAccountHandle phoneAccountHandle = this.mSimCallManager;
        if (phoneAccountHandle != null) {
            Intent intentBuildPhoneAccountConfigureIntent = MobileNetworkUtils.buildPhoneAccountConfigureIntent(this.mContext, phoneAccountHandle);
            PackageManager packageManager = this.mContext.getPackageManager();
            preference.setTitle(packageManager.queryIntentActivities(intentBuildPhoneAccountConfigureIntent, 0).get(0).loadLabel(packageManager));
            preference.setSummary((CharSequence) null);
            preference.setIntent(intentBuildPhoneAccountConfigureIntent);
        } else {
            preference.setTitle(SubscriptionManager.getResourcesForSubId(this.mContext, this.mSubId).getString(R.string.wifi_calling_settings_title));
            int i = android.R.string.permlab_receiveSms;
            if (this.mImsManager.isWfcEnabledByUser()) {
                boolean z = this.mEditableWfcRoamingMode && !this.mUseWfcHomeModeForRoaming;
                int wfcMode = this.mImsManager.getWfcMode(this.mTelephonyManager.isNetworkRoaming() && z);
                if (wfcMode == 0) {
                    i = android.R.string.permlab_mediaLocation;
                } else if (wfcMode == 1) {
                    i = android.R.string.permlab_manageProfileAndDeviceOwners;
                } else if (wfcMode == 2) {
                    i = android.R.string.permlab_modifyAudioSettings;
                }
            }
            preference.setSummary(i);
        }
        preference.setEnabled(this.mTelephonyManager.getCallState(this.mSubId) == 0);
    }

    public void init(int i) {
        PersistableBundle configForSubId;
        this.mSubId = i;
        this.mTelephonyManager = TelephonyManager.from(this.mContext).createForSubscriptionId(this.mSubId);
        this.mImsManager = ImsManager.getInstance(this.mContext, SubscriptionManager.getPhoneId(this.mSubId));
        CarrierConfigManager carrierConfigManager = this.mCarrierConfigManager;
        if (carrierConfigManager == null || (configForSubId = carrierConfigManager.getConfigForSubId(this.mSubId)) == null) {
            return;
        }
        this.mEditableWfcRoamingMode = configForSubId.getBoolean("editable_wfc_roaming_mode_bool");
        this.mUseWfcHomeModeForRoaming = configForSubId.getBoolean("use_wfc_home_network_mode_in_roaming_network_bool");
    }

    private class PhoneCallStateListener extends PhoneStateListener {
        public PhoneCallStateListener(Looper looper) {
            super(looper);
        }

        @Override // android.telephony.PhoneStateListener
        public void onCallStateChanged(int i, String str) {
            WifiCallingPreferenceController wifiCallingPreferenceController = WifiCallingPreferenceController.this;
            wifiCallingPreferenceController.updateState(wifiCallingPreferenceController.mPreference);
        }

        public void register(int i) {
            ((PhoneStateListener) this).mSubId = Integer.valueOf(i);
            WifiCallingPreferenceController.this.mTelephonyManager.listen(this, 32);
        }

        public void unregister() {
            WifiCallingPreferenceController.this.mTelephonyManager.listen(this, 0);
        }
    }
}
