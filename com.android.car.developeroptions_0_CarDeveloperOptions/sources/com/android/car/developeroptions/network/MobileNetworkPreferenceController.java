package com.android.car.developeroptions.network;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.UserHandle;
import android.os.UserManager;
import android.provider.Settings;
import android.telephony.PhoneStateListener;
import android.telephony.ServiceState;
import android.telephony.TelephonyManager;
import android.util.FeatureFlagUtils;
import androidx.preference.Preference;
import androidx.preference.PreferenceScreen;
import com.android.car.developeroptions.core.PreferenceControllerMixin;
import com.android.car.developeroptions.network.telephony.MobileNetworkActivity;
import com.android.settingslib.RestrictedLockUtilsInternal;
import com.android.settingslib.RestrictedPreference;
import com.android.settingslib.Utils;
import com.android.settingslib.core.AbstractPreferenceController;
import com.android.settingslib.core.lifecycle.LifecycleObserver;
import com.android.settingslib.core.lifecycle.events.OnStart;
import com.android.settingslib.core.lifecycle.events.OnStop;

/* JADX INFO: loaded from: classes.dex */
public class MobileNetworkPreferenceController extends AbstractPreferenceController implements PreferenceControllerMixin, LifecycleObserver, OnStart, OnStop {
    static final String KEY_MOBILE_NETWORK_SETTINGS = "mobile_network_settings";
    static final String MOBILE_NETWORK_CLASS = "com.android.phone.MobileNetworkSettings";
    static final String MOBILE_NETWORK_PACKAGE = "com.android.phone";
    private BroadcastReceiver mAirplanModeChangedReceiver;
    private final boolean mIsSecondaryUser;
    PhoneStateListener mPhoneStateListener;
    private Preference mPreference;
    private final TelephonyManager mTelephonyManager;
    private final UserManager mUserManager;

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public String getPreferenceKey() {
        return KEY_MOBILE_NETWORK_SETTINGS;
    }

    public MobileNetworkPreferenceController(Context context) {
        super(context);
        this.mUserManager = (UserManager) context.getSystemService("user");
        this.mTelephonyManager = (TelephonyManager) context.getSystemService("phone");
        this.mIsSecondaryUser = !this.mUserManager.isAdminUser();
        this.mAirplanModeChangedReceiver = new BroadcastReceiver() { // from class: com.android.car.developeroptions.network.MobileNetworkPreferenceController.1
            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context2, Intent intent) {
                MobileNetworkPreferenceController mobileNetworkPreferenceController = MobileNetworkPreferenceController.this;
                mobileNetworkPreferenceController.updateState(mobileNetworkPreferenceController.mPreference);
            }
        };
    }

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public boolean isAvailable() {
        return (isUserRestricted() || Utils.isWifiOnly(this.mContext)) ? false : true;
    }

    public boolean isUserRestricted() {
        return this.mIsSecondaryUser || RestrictedLockUtilsInternal.hasBaseUserRestriction(this.mContext, "no_config_mobile_networks", UserHandle.myUserId());
    }

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public void displayPreference(PreferenceScreen preferenceScreen) {
        super.displayPreference(preferenceScreen);
        this.mPreference = preferenceScreen.findPreference(getPreferenceKey());
    }

    @Override // com.android.settingslib.core.lifecycle.events.OnStart
    public void onStart() {
        if (isAvailable()) {
            if (this.mPhoneStateListener == null) {
                this.mPhoneStateListener = new PhoneStateListener() { // from class: com.android.car.developeroptions.network.MobileNetworkPreferenceController.2
                    @Override // android.telephony.PhoneStateListener
                    public void onServiceStateChanged(ServiceState serviceState) {
                        MobileNetworkPreferenceController mobileNetworkPreferenceController = MobileNetworkPreferenceController.this;
                        mobileNetworkPreferenceController.updateState(mobileNetworkPreferenceController.mPreference);
                    }
                };
            }
            this.mTelephonyManager.listen(this.mPhoneStateListener, 1);
        }
        BroadcastReceiver broadcastReceiver = this.mAirplanModeChangedReceiver;
        if (broadcastReceiver != null) {
            this.mContext.registerReceiver(broadcastReceiver, new IntentFilter("android.intent.action.AIRPLANE_MODE"));
        }
    }

    @Override // com.android.settingslib.core.lifecycle.events.OnStop
    public void onStop() {
        PhoneStateListener phoneStateListener = this.mPhoneStateListener;
        if (phoneStateListener != null) {
            this.mTelephonyManager.listen(phoneStateListener, 0);
        }
        BroadcastReceiver broadcastReceiver = this.mAirplanModeChangedReceiver;
        if (broadcastReceiver != null) {
            this.mContext.unregisterReceiver(broadcastReceiver);
        }
    }

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public void updateState(Preference preference) {
        super.updateState(preference);
        if ((preference instanceof RestrictedPreference) && ((RestrictedPreference) preference).isDisabledByAdmin()) {
            return;
        }
        preference.setEnabled(Settings.Global.getInt(this.mContext.getContentResolver(), "airplane_mode_on", 0) == 0);
    }

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public boolean handlePreferenceTreeClick(Preference preference) {
        if (!KEY_MOBILE_NETWORK_SETTINGS.equals(preference.getKey())) {
            return false;
        }
        if (FeatureFlagUtils.isEnabled(this.mContext, "settings_mobile_network_v2")) {
            this.mContext.startActivity(new Intent(this.mContext, (Class<?>) MobileNetworkActivity.class));
            return true;
        }
        Intent intent = new Intent("android.intent.action.MAIN");
        intent.setComponent(new ComponentName(MOBILE_NETWORK_PACKAGE, MOBILE_NETWORK_CLASS));
        this.mContext.startActivity(intent);
        return true;
    }

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public CharSequence getSummary() {
        return this.mTelephonyManager.getNetworkOperatorName();
    }
}
