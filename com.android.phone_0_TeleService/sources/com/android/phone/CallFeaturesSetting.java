package com.android.phone;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.os.UserManager;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceScreen;
import android.preference.SwitchPreference;
import android.provider.Settings;
import android.telecom.PhoneAccountHandle;
import android.telecom.TelecomManager;
import android.telephony.PhoneStateListener;
import android.telephony.SubscriptionManager;
import android.telephony.TelephonyManager;
import android.telephony.ims.ProvisioningManager;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;
import com.android.ims.ImsException;
import com.android.ims.ImsManager;
import com.android.internal.telephony.Phone;
import com.android.phone.settings.PhoneAccountSettingsFragment;
import com.android.phone.settings.SuppServicesUiUtil;
import com.android.phone.settings.VoicemailSettingsActivity;
import com.android.phone.settings.fdn.FdnSetting;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class CallFeaturesSetting extends PreferenceActivity implements Preference.OnPreferenceChangeListener {
    private SwitchPreference mButtonAutoRetry;
    private Preference mButtonWifiCalling;
    private SwitchPreference mEnableVideoCalling;
    private ImsManager mImsMgr;
    private Phone mPhone;
    private SubscriptionInfoHelper mSubscriptionInfoHelper;
    private TelecomManager mTelecomManager;
    private PreferenceScreen mVoicemailSettingsScreen;
    private final BroadcastReceiver mReceiver = new BroadcastReceiver() { // from class: com.android.phone.CallFeaturesSetting.1
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            CallFeaturesSetting.log("onReceive: " + intent.getAction());
            if ("android.intent.action.AIRPLANE_MODE".equals(intent.getAction())) {
                CallFeaturesSetting.log("ACTION_AIRPLANE_MODE_CHANGED");
                CallFeaturesSetting.this.handleAirplaneModeChange(intent.getBooleanExtra("state", false));
            }
        }
    };
    private final PhoneStateListener mPhoneStateListener = new PhoneStateListener() { // from class: com.android.phone.CallFeaturesSetting.3
        @Override // android.telephony.PhoneStateListener
        public void onCallStateChanged(int i, String str) {
            boolean z = i == 0;
            if (CallFeaturesSetting.this.mEnableVideoCalling != null) {
                CallFeaturesSetting.this.mEnableVideoCalling.setEnabled(z);
            }
            if (CallFeaturesSetting.this.mButtonWifiCalling != null) {
                CallFeaturesSetting.this.mButtonWifiCalling.setEnabled(z);
            }
        }
    };
    private final ProvisioningManager.Callback mProvisioningCallback = new ProvisioningManager.Callback() { // from class: com.android.phone.CallFeaturesSetting.4
        public void onProvisioningIntChanged(int i, int i2) {
            if (i == 28 || i == 10 || i == 11) {
                CallFeaturesSetting.this.updateVtWfc();
            }
        }
    };

    /* JADX INFO: Access modifiers changed from: private */
    public void handleAirplaneModeChange(boolean z) {
        PersistableBundle carrierConfig;
        if (this.mSubscriptionInfoHelper.hasSubId()) {
            carrierConfig = PhoneGlobals.getInstance().getCarrierConfigForSubId(this.mSubscriptionInfoHelper.getSubId());
        } else {
            carrierConfig = PhoneGlobals.getInstance().getCarrierConfig();
        }
        if (carrierConfig == null || !carrierConfig.getBoolean("disable_supplementary_services_in_airplane_mode_bool")) {
            return;
        }
        PreferenceScreen preferenceScreen = getPreferenceScreen();
        Preference preferenceFindPreference = preferenceScreen.findPreference("call_forwarding_key");
        Preference preferenceFindPreference2 = preferenceScreen.findPreference("call_barring_key");
        Preference preferenceFindPreference3 = preferenceScreen.findPreference("additional_gsm_call_settings_key");
        if (preferenceFindPreference != null) {
            preferenceFindPreference.setEnabled(!z);
        }
        if (preferenceFindPreference2 != null) {
            preferenceFindPreference2.setEnabled(!z);
        }
        if (preferenceFindPreference3 != null) {
            preferenceFindPreference3.setEnabled(!z);
        }
    }

    @Override // android.preference.PreferenceActivity
    public boolean onPreferenceTreeClick(PreferenceScreen preferenceScreen, Preference preference) {
        if (preference == this.mButtonAutoRetry) {
            Settings.Global.putInt(this.mPhone.getContext().getContentResolver(), "call_auto_retry", this.mButtonAutoRetry.isChecked() ? 1 : 0);
            return true;
        }
        if (preference == preferenceScreen.findPreference("call_forwarding_key")) {
            return doSsOverUtPrecautions(preference);
        }
        if (preference == preferenceScreen.findPreference("call_barring_key")) {
            return doSsOverUtPrecautions(preference);
        }
        return false;
    }

    private boolean doSsOverUtPrecautions(Preference preference) {
        PersistableBundle carrierConfig;
        Phone phone;
        if (this.mSubscriptionInfoHelper.hasSubId()) {
            carrierConfig = PhoneGlobals.getInstance().getCarrierConfigForSubId(this.mSubscriptionInfoHelper.getSubId());
        } else {
            carrierConfig = PhoneGlobals.getInstance().getCarrierConfig();
        }
        String str = preference.getKey().equals("call_forwarding_key") ? "call_forwarding_over_ut_warning_bool" : "call_barring_over_ut_warning_bool";
        if (carrierConfig == null || !carrierConfig.getBoolean(str) || (phone = this.mPhone) == null || !SuppServicesUiUtil.isSsOverUtPrecautions(this, phone)) {
            return false;
        }
        SuppServicesUiUtil.showBlockingSuppServicesDialog(this, this.mPhone, preference.getKey()).show();
        return true;
    }

    @Override // android.preference.Preference.OnPreferenceChangeListener
    public boolean onPreferenceChange(Preference preference, Object obj) {
        if (preference != this.mEnableVideoCalling) {
            return true;
        }
        if (this.mImsMgr.isEnhanced4gLteModeSettingEnabledByUser()) {
            this.mImsMgr.setVtSetting(((Boolean) obj).booleanValue());
            return true;
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(getResourcesForSubId().getString(R.string.enable_video_calling_dialog_msg)).setNeutralButton(getResourcesForSubId().getString(R.string.enable_video_calling_dialog_settings), new DialogInterface.OnClickListener() { // from class: com.android.phone.CallFeaturesSetting.2
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent intent = new Intent("android.intent.action.MAIN");
                intent.setComponent(new ComponentName(CallFeaturesSetting.this.getString(R.string.mobile_network_settings_package), CallFeaturesSetting.this.getString(R.string.mobile_network_settings_class)));
                CallFeaturesSetting.this.startActivity(intent);
            }
        }).setPositiveButton(android.R.string.ok, (DialogInterface.OnClickListener) null).show();
        return false;
    }

    @Override // android.preference.PreferenceActivity, android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (!((UserManager) getSystemService("user")).isAdminUser()) {
            Toast.makeText(this, R.string.call_settings_admin_user_only, 0).show();
            finish();
            return;
        }
        SubscriptionInfoHelper subscriptionInfoHelper = new SubscriptionInfoHelper(this, getIntent());
        this.mSubscriptionInfoHelper = subscriptionInfoHelper;
        this.mPhone = subscriptionInfoHelper.getPhone();
        this.mSubscriptionInfoHelper.setActionBarTitle(getActionBar(), getResourcesForSubId(), R.string.call_settings_with_label);
        this.mTelecomManager = (TelecomManager) getSystemService(TelecomManager.class);
    }

    private void updateImsManager(Phone phone) {
        log("updateImsManager :: phone.getContext()=" + phone.getContext() + " phone.getPhoneId()=" + phone.getPhoneId());
        ImsManager imsManager = ImsManager.getInstance(phone.getContext(), phone.getPhoneId());
        this.mImsMgr = imsManager;
        if (imsManager == null) {
            log("updateImsManager :: Could not get ImsManager instance!");
            return;
        }
        log("updateImsManager :: mImsMgr=" + this.mImsMgr);
    }

    private void listenPhoneState(boolean z) {
        ((TelephonyManager) getSystemService(TelephonyManager.class)).createForSubscriptionId(this.mPhone.getSubId()).listen(this.mPhoneStateListener, z ? 32 : 0);
    }

    @Override // android.app.Activity
    protected void onPause() {
        super.onPause();
        listenPhoneState(false);
        unregisterReceiver(this.mReceiver);
        try {
            if (this.mImsMgr != null) {
                this.mImsMgr.getConfigInterface().removeConfigCallback(this.mProvisioningCallback.getBinder());
            }
        } catch (ImsException unused) {
            Log.w("CallFeaturesSetting", "onPause: Unable to remove callback for provisioning changes");
        }
    }

    @Override // android.app.Activity
    protected void onResume() {
        super.onResume();
        updateImsManager(this.mPhone);
        listenPhoneState(true);
        PreferenceScreen preferenceScreen = getPreferenceScreen();
        if (preferenceScreen != null) {
            preferenceScreen.removeAll();
        }
        addPreferencesFromResource(R.xml.call_feature_setting);
        if (((TelephonyManager) getSystemService(TelephonyManager.class)).createForSubscriptionId(this.mPhone.getSubId()).isMultiSimEnabled()) {
            getPreferenceScreen().removePreference(findPreference("phone_account_settings_preference_screen"));
        }
        PreferenceScreen preferenceScreen2 = getPreferenceScreen();
        PreferenceScreen preferenceScreen3 = (PreferenceScreen) findPreference("button_voicemail_category_key");
        this.mVoicemailSettingsScreen = preferenceScreen3;
        preferenceScreen3.setIntent(this.mSubscriptionInfoHelper.getIntent(VoicemailSettingsActivity.class));
        maybeHideVoicemailSettings();
        this.mButtonAutoRetry = (SwitchPreference) findPreference("button_auto_retry_key");
        this.mEnableVideoCalling = (SwitchPreference) findPreference("button_enable_video_calling");
        this.mButtonWifiCalling = findPreference(getResourcesForSubId().getString(R.string.wifi_calling_settings_key));
        PersistableBundle carrierConfigForSubId = PhoneGlobals.getInstance().getCarrierConfigForSubId(this.mPhone.getSubId());
        if (carrierConfigForSubId.getBoolean("auto_retry_enabled_bool")) {
            this.mButtonAutoRetry.setOnPreferenceChangeListener(this);
            this.mButtonAutoRetry.setChecked(Settings.Global.getInt(getContentResolver(), "call_auto_retry", 0) != 0);
        } else {
            preferenceScreen2.removePreference(this.mButtonAutoRetry);
            this.mButtonAutoRetry = null;
        }
        Preference preferenceFindPreference = preferenceScreen2.findPreference("button_cdma_more_expand_key");
        Preference preferenceFindPreference2 = preferenceScreen2.findPreference("button_gsm_more_expand_key");
        Preference preferenceFindPreference3 = preferenceScreen2.findPreference("button_fdn_key");
        preferenceFindPreference3.setIntent(this.mSubscriptionInfoHelper.getIntent(FdnSetting.class));
        if (carrierConfigForSubId.getBoolean("world_phone_bool")) {
            preferenceFindPreference.setIntent(this.mSubscriptionInfoHelper.getIntent(CdmaCallOptions.class));
            preferenceFindPreference2.setIntent(this.mSubscriptionInfoHelper.getIntent(GsmUmtsCallOptions.class));
        } else {
            preferenceScreen2.removePreference(preferenceFindPreference);
            preferenceScreen2.removePreference(preferenceFindPreference2);
            int phoneType = this.mPhone.getPhoneType();
            if (carrierConfigForSubId.getBoolean("hide_carrier_network_settings_bool")) {
                preferenceScreen2.removePreference(preferenceFindPreference3);
            } else if (phoneType == 2) {
                preferenceScreen2.removePreference(preferenceFindPreference3);
                if (!carrierConfigForSubId.getBoolean("voice_privacy_disable_ui_bool")) {
                    addPreferencesFromResource(R.xml.cdma_call_privacy);
                    ((CdmaVoicePrivacySwitchPreference) findPreference("button_voice_privacy_key")).setPhone(this.mPhone);
                }
            } else if (phoneType == 1) {
                if (this.mPhone.getIccCard() == null || !this.mPhone.getIccCard().getIccFdnAvailable()) {
                    preferenceScreen2.removePreference(preferenceFindPreference3);
                }
                if (carrierConfigForSubId.getBoolean("additional_call_setting_bool")) {
                    addPreferencesFromResource(R.xml.gsm_umts_call_options);
                    GsmUmtsCallOptions.init(preferenceScreen2, this.mSubscriptionInfoHelper);
                }
            } else {
                throw new IllegalStateException("Unexpected phone type: " + phoneType);
            }
        }
        updateVtWfc();
        try {
            if (this.mImsMgr != null) {
                this.mImsMgr.getConfigInterface().addConfigCallback(this.mProvisioningCallback);
            }
        } catch (ImsException unused) {
            Log.w("CallFeaturesSetting", "onResume: Unable to register callback for provisioning changes.");
        }
        registerReceiver(this.mReceiver, new IntentFilter("android.intent.action.AIRPLANE_MODE"));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateVtWfc() {
        PreferenceScreen preferenceScreen = getPreferenceScreen();
        TelephonyManager telephonyManagerCreateForSubscriptionId = ((TelephonyManager) getSystemService(TelephonyManager.class)).createForSubscriptionId(this.mPhone.getSubId());
        PersistableBundle carrierConfigForSubId = PhoneGlobals.getInstance().getCarrierConfigForSubId(this.mPhone.getSubId());
        boolean z = false;
        boolean z2 = carrierConfigForSubId.getBoolean("use_wfc_home_network_mode_in_roaming_network_bool", false);
        if (this.mImsMgr.isVtEnabledByPlatform() && this.mImsMgr.isVtProvisionedOnDevice() && (carrierConfigForSubId.getBoolean("ignore_data_enabled_changed_for_video_calls") || this.mPhone.getDataEnabledSettings().isDataEnabled())) {
            this.mEnableVideoCalling.setChecked(this.mImsMgr.isEnhanced4gLteModeSettingEnabledByUser() ? this.mImsMgr.isVtEnabledByUser() : false);
            this.mEnableVideoCalling.setOnPreferenceChangeListener(this);
            preferenceScreen.addPreference(this.mEnableVideoCalling);
        } else {
            preferenceScreen.removePreference(this.mEnableVideoCalling);
        }
        PhoneAccountHandle simCallManagerForSubscription = this.mTelecomManager.getSimCallManagerForSubscription(this.mPhone.getSubId());
        if (simCallManagerForSubscription != null) {
            Intent intentBuildPhoneAccountConfigureIntent = PhoneAccountSettingsFragment.buildPhoneAccountConfigureIntent(this, simCallManagerForSubscription);
            if (intentBuildPhoneAccountConfigureIntent != null) {
                PackageManager packageManager = this.mPhone.getContext().getPackageManager();
                List<ResolveInfo> listQueryIntentActivities = packageManager.queryIntentActivities(intentBuildPhoneAccountConfigureIntent, 0);
                if (!listQueryIntentActivities.isEmpty()) {
                    this.mButtonWifiCalling.setTitle(listQueryIntentActivities.get(0).loadLabel(packageManager));
                    this.mButtonWifiCalling.setSummary((CharSequence) null);
                    this.mButtonWifiCalling.setIntent(intentBuildPhoneAccountConfigureIntent);
                    preferenceScreen.addPreference(this.mButtonWifiCalling);
                } else {
                    preferenceScreen.removePreference(this.mButtonWifiCalling);
                }
            } else {
                preferenceScreen.removePreference(this.mButtonWifiCalling);
            }
        } else if (!this.mImsMgr.isWfcEnabledByPlatform() || !this.mImsMgr.isWfcProvisionedOnDevice()) {
            preferenceScreen.removePreference(this.mButtonWifiCalling);
        } else {
            this.mButtonWifiCalling.setTitle(getResourcesForSubId().getString(R.string.wifi_calling));
            int i = android.R.string.permlab_receiveSms;
            if (this.mImsMgr.isWfcEnabledByUser()) {
                boolean zIsNetworkRoaming = telephonyManagerCreateForSubscriptionId.isNetworkRoaming();
                ImsManager imsManager = this.mImsMgr;
                if (zIsNetworkRoaming && !z2) {
                    z = true;
                }
                int wfcMode = imsManager.getWfcMode(z);
                if (wfcMode == 0) {
                    i = android.R.string.permlab_mediaLocation;
                } else if (wfcMode == 1) {
                    i = android.R.string.permlab_manageProfileAndDeviceOwners;
                } else if (wfcMode == 2) {
                    i = android.R.string.permlab_modifyAudioSettings;
                }
            }
            this.mButtonWifiCalling.setSummary(getResourcesForSubId().getString(i));
            Intent intent = this.mButtonWifiCalling.getIntent();
            if (intent != null) {
                intent.putExtra("android.provider.extra.SUB_ID", this.mPhone.getSubId());
            }
            preferenceScreen.addPreference(this.mButtonWifiCalling);
        }
        try {
            if (this.mImsMgr.getImsServiceState() != 2) {
                log("Feature state not ready so remove vt and wfc settings for  phone =" + this.mPhone.getPhoneId());
                preferenceScreen.removePreference(this.mButtonWifiCalling);
                preferenceScreen.removePreference(this.mEnableVideoCalling);
            }
        } catch (ImsException e) {
            log("Exception when trying to get ImsServiceStatus: " + e);
            preferenceScreen.removePreference(this.mButtonWifiCalling);
            preferenceScreen.removePreference(this.mEnableVideoCalling);
        }
    }

    private void maybeHideVoicemailSettings() {
        String defaultDialerPackage = ((TelecomManager) getSystemService(TelecomManager.class)).getDefaultDialerPackage();
        if (defaultDialerPackage == null) {
            return;
        }
        try {
            Bundle bundle = getPackageManager().getApplicationInfo(defaultDialerPackage, 128).metaData;
            if (bundle != null && bundle.getBoolean("android.telephony.HIDE_VOICEMAIL_SETTINGS_MENU", false)) {
                getPreferenceScreen().removePreference(this.mVoicemailSettingsScreen);
            }
        } catch (PackageManager.NameNotFoundException unused) {
        }
    }

    @Override // android.preference.PreferenceActivity, android.app.Activity
    protected void onNewIntent(Intent intent) {
        setIntent(intent);
        SubscriptionInfoHelper subscriptionInfoHelper = new SubscriptionInfoHelper(this, getIntent());
        this.mSubscriptionInfoHelper = subscriptionInfoHelper;
        this.mPhone = subscriptionInfoHelper.getPhone();
        this.mSubscriptionInfoHelper.setActionBarTitle(getActionBar(), getResourcesForSubId(), R.string.call_settings_with_label);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void log(String str) {
        Log.d("CallFeaturesSetting", str);
    }

    @Override // android.preference.PreferenceActivity, android.app.Activity
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() == 16908332) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(menuItem);
    }

    public static void goUpToTopLevelSetting(Activity activity, SubscriptionInfoHelper subscriptionInfoHelper) {
        Intent intent = subscriptionInfoHelper.getIntent(CallFeaturesSetting.class);
        intent.setAction("android.intent.action.MAIN");
        intent.addFlags(67108864);
        activity.startActivity(intent);
        activity.finish();
    }

    private Resources getResourcesForSubId() {
        Phone phone = this.mPhone;
        if (phone != null) {
            return SubscriptionManager.getResourcesForSubId(phone.getContext(), this.mPhone.getSubId());
        }
        return getResources();
    }
}
