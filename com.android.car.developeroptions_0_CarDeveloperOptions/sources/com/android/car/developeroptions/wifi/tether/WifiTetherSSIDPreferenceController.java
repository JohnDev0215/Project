package com.android.car.developeroptions.wifi.tether;

import android.content.Context;
import android.content.Intent;
import android.net.wifi.SoftApConfiguration;
import android.util.Log;
import android.view.View;
import androidx.preference.EditTextPreference;
import androidx.preference.Preference;
import com.android.car.developeroptions.widget.ValidatedEditTextPreference;
import com.android.car.developeroptions.wifi.dpp.WifiDppUtils;
import com.android.car.developeroptions.wifi.tether.WifiTetherBasePreferenceController;

/* JADX INFO: loaded from: classes.dex */
public class WifiTetherSSIDPreferenceController extends WifiTetherBasePreferenceController implements ValidatedEditTextPreference.Validator {
    static final String DEFAULT_SSID = "AndroidAP";
    private String mSSID;
    private WifiDeviceNameTextValidator mWifiDeviceNameTextValidator;

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public String getPreferenceKey() {
        return "wifi_tether_network_name";
    }

    public WifiTetherSSIDPreferenceController(Context context, WifiTetherBasePreferenceController.OnTetherConfigUpdateListener onTetherConfigUpdateListener) {
        super(context, onTetherConfigUpdateListener);
        this.mWifiDeviceNameTextValidator = new WifiDeviceNameTextValidator();
    }

    @Override // com.android.car.developeroptions.wifi.tether.WifiTetherBasePreferenceController
    public void updateDisplay() {
        SoftApConfiguration softApConfiguration = this.mWifiManager.getSoftApConfiguration();
        if (softApConfiguration != null) {
            this.mSSID = softApConfiguration.getSsid();
        } else {
            this.mSSID = DEFAULT_SSID;
        }
        ((ValidatedEditTextPreference) this.mPreference).setValidator(this);
        if (this.mWifiManager.isWifiApEnabled() && softApConfiguration != null) {
            final Intent hotspotConfiguratorIntentOrNull = WifiDppUtils.getHotspotConfiguratorIntentOrNull(this.mContext, softApConfiguration);
            if (hotspotConfiguratorIntentOrNull == null) {
                Log.e("WifiTetherSsidPref", "Invalid security to share hotspot");
                ((WifiTetherSsidPreference) this.mPreference).setButtonVisible(false);
            } else {
                ((WifiTetherSsidPreference) this.mPreference).setButtonOnClickListener(new View.OnClickListener() { // from class: com.android.car.developeroptions.wifi.tether.-$$Lambda$WifiTetherSSIDPreferenceController$H4QI8syxIG4tRBkoC9qPwmsW07Q
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        this.f$0.lambda$updateDisplay$0$WifiTetherSSIDPreferenceController(hotspotConfiguratorIntentOrNull, view);
                    }
                });
                ((WifiTetherSsidPreference) this.mPreference).setButtonVisible(true);
            }
        } else {
            ((WifiTetherSsidPreference) this.mPreference).setButtonVisible(false);
        }
        updateSsidDisplay((EditTextPreference) this.mPreference);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: lambda$updateDisplay$0, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void lambda$updateDisplay$0$WifiTetherSSIDPreferenceController(Intent intent, View view) {
        shareHotspotNetwork(intent);
    }

    @Override // androidx.preference.Preference.OnPreferenceChangeListener
    public boolean onPreferenceChange(Preference preference, Object obj) {
        this.mSSID = (String) obj;
        updateSsidDisplay((EditTextPreference) preference);
        this.mListener.onTetherConfigUpdated();
        return true;
    }

    @Override // com.android.car.developeroptions.widget.ValidatedEditTextPreference.Validator
    public boolean isTextValid(String str) {
        return this.mWifiDeviceNameTextValidator.isTextValid(str);
    }

    public String getSSID() {
        return this.mSSID;
    }

    private void updateSsidDisplay(EditTextPreference editTextPreference) {
        editTextPreference.setText(this.mSSID);
        editTextPreference.setSummary(this.mSSID);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: lambda$shareHotspotNetwork$1, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void lambda$shareHotspotNetwork$1$WifiTetherSSIDPreferenceController(Intent intent) {
        this.mContext.startActivity(intent);
    }

    private void shareHotspotNetwork(final Intent intent) {
        WifiDppUtils.showLockScreen(this.mContext, new Runnable() { // from class: com.android.car.developeroptions.wifi.tether.-$$Lambda$WifiTetherSSIDPreferenceController$Ky70qaV8s3F4z74vrVI8OpOJT8A
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$shareHotspotNetwork$1$WifiTetherSSIDPreferenceController(intent);
            }
        });
    }

    boolean isQrCodeButtonAvailable() {
        return ((WifiTetherSsidPreference) this.mPreference).isQrCodeButtonAvailable();
    }
}
