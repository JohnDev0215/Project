package com.android.car.developeroptions.wifi.calling;

import android.R;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.telephony.CarrierConfigManager;
import android.telephony.PhoneStateListener;
import android.telephony.SubscriptionManager;
import android.telephony.TelephonyManager;
import android.telephony.ims.ProvisioningManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;
import android.widget.TextView;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.FragmentActivity;
import androidx.preference.Preference;
import androidx.preference.PreferenceScreen;
import com.android.car.developeroptions.SettingsActivity;
import com.android.car.developeroptions.SettingsPreferenceFragment;
import com.android.car.developeroptions.Utils;
import com.android.car.developeroptions.core.SubSettingLauncher;
import com.android.car.developeroptions.widget.SwitchBar;
import com.android.ims.ImsException;
import com.android.ims.ImsManager;
import com.android.internal.annotations.VisibleForTesting;

/* JADX INFO: loaded from: classes.dex */
public class WifiCallingSettingsForSub extends SettingsPreferenceFragment implements SwitchBar.OnSwitchChangeListener, Preference.OnPreferenceChangeListener {

    @VisibleForTesting
    static final int REQUEST_CHECK_WFC_DISCLAIMER = 2;

    @VisibleForTesting
    static final int REQUEST_CHECK_WFC_EMERGENCY_ADDRESS = 1;
    private ListWithEntrySummaryPreference mButtonWfcMode;
    private ListWithEntrySummaryPreference mButtonWfcRoamingMode;
    private TextView mEmptyView;
    private ImsManager mImsManager;
    private IntentFilter mIntentFilter;
    private Switch mSwitch;
    private SwitchBar mSwitchBar;
    private TelephonyManager mTelephonyManager;
    private Preference mUpdateAddress;
    private boolean mValidListener = false;
    private boolean mEditableWfcMode = true;
    private boolean mEditableWfcRoamingMode = true;
    private boolean mUseWfcHomeModeForRoaming = false;
    private int mSubId = -1;
    private final PhoneStateListener mPhoneStateListener = new PhoneStateListener() { // from class: com.android.car.developeroptions.wifi.calling.WifiCallingSettingsForSub.1
        @Override // android.telephony.PhoneStateListener
        public void onCallStateChanged(int i, String str) {
            boolean z;
            boolean z2;
            PersistableBundle configForSubId;
            SettingsActivity settingsActivity = (SettingsActivity) WifiCallingSettingsForSub.this.getActivity();
            boolean zIsNonTtyOrTtyOnVolteEnabled = WifiCallingSettingsForSub.this.mImsManager.isNonTtyOrTtyOnVolteEnabled();
            boolean z3 = WifiCallingSettingsForSub.this.mSwitchBar.isChecked() && zIsNonTtyOrTtyOnVolteEnabled;
            WifiCallingSettingsForSub.this.mSwitchBar.setEnabled(i == 0 && zIsNonTtyOrTtyOnVolteEnabled);
            CarrierConfigManager carrierConfigManager = (CarrierConfigManager) settingsActivity.getSystemService("carrier_config");
            if (carrierConfigManager == null || (configForSubId = carrierConfigManager.getConfigForSubId(((PhoneStateListener) this).mSubId.intValue())) == null) {
                z = true;
                z2 = false;
            } else {
                z = configForSubId.getBoolean("editable_wfc_mode_bool");
                z2 = configForSubId.getBoolean("editable_wfc_roaming_mode_bool");
            }
            Preference preferenceFindPreference = WifiCallingSettingsForSub.this.getPreferenceScreen().findPreference("wifi_calling_mode");
            if (preferenceFindPreference != null) {
                preferenceFindPreference.setEnabled(z3 && z && i == 0);
            }
            Preference preferenceFindPreference2 = WifiCallingSettingsForSub.this.getPreferenceScreen().findPreference("wifi_calling_roaming_mode");
            if (preferenceFindPreference2 != null) {
                preferenceFindPreference2.setEnabled(z3 && z2 && i == 0);
            }
        }
    };
    private final Preference.OnPreferenceClickListener mUpdateAddressListener = new Preference.OnPreferenceClickListener() { // from class: com.android.car.developeroptions.wifi.calling.-$$Lambda$WifiCallingSettingsForSub$bRcad9WG4P-WN_eJMRSj04erk0U
        @Override // androidx.preference.Preference.OnPreferenceClickListener
        public final boolean onPreferenceClick(Preference preference) {
            return this.f$0.lambda$new$0$WifiCallingSettingsForSub(preference);
        }
    };
    private final ProvisioningManager.Callback mProvisioningCallback = new ProvisioningManager.Callback() { // from class: com.android.car.developeroptions.wifi.calling.WifiCallingSettingsForSub.2
        public void onProvisioningIntChanged(int i, int i2) {
            if (i == 28 || i == 10) {
                WifiCallingSettingsForSub.this.updateBody();
            }
        }
    };
    private BroadcastReceiver mIntentReceiver = new BroadcastReceiver() { // from class: com.android.car.developeroptions.wifi.calling.WifiCallingSettingsForSub.3
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals("android.telephony.ims.action.WFC_IMS_REGISTRATION_ERROR")) {
                setResultCode(0);
                WifiCallingSettingsForSub.this.showAlert(intent);
            }
        }
    };

    @Override // com.android.car.developeroptions.support.actionbar.HelpResourceProvider
    public int getHelpResource() {
        return 0;
    }

    @Override // com.android.settingslib.core.instrumentation.Instrumentable
    public int getMetricsCategory() {
        return 1230;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: lambda$new$0, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ boolean lambda$new$0$WifiCallingSettingsForSub(Preference preference) {
        Intent carrierActivityIntent = getCarrierActivityIntent();
        if (carrierActivityIntent != null) {
            carrierActivityIntent.putExtra("EXTRA_LAUNCH_CARRIER_APP", 1);
            startActivity(carrierActivityIntent);
        }
        return true;
    }

    @Override // com.android.car.developeroptions.SettingsPreferenceFragment, androidx.fragment.app.Fragment
    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        TextView textView = (TextView) getView().findViewById(R.id.empty);
        this.mEmptyView = textView;
        setEmptyView(textView);
        Resources resourcesForSubId = getResourcesForSubId();
        this.mEmptyView.setText(resourcesForSubId.getString(com.android.car.developeroptions.R.string.wifi_calling_off_explanation, resourcesForSubId.getString(com.android.car.developeroptions.R.string.wifi_calling_off_explanation_2)));
        SwitchBar switchBar = (SwitchBar) getView().findViewById(com.android.car.developeroptions.R.id.switch_bar);
        this.mSwitchBar = switchBar;
        switchBar.show();
        this.mSwitch = this.mSwitchBar.getSwitch();
    }

    @Override // androidx.preference.PreferenceFragmentCompat, androidx.fragment.app.Fragment
    public void onDestroyView() {
        super.onDestroyView();
        this.mSwitchBar.hide();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showAlert(Intent intent) {
        FragmentActivity activity = getActivity();
        CharSequence charSequenceExtra = intent.getCharSequenceExtra("android.telephony.ims.extra.WFC_REGISTRATION_FAILURE_TITLE");
        CharSequence charSequenceExtra2 = intent.getCharSequenceExtra("android.telephony.ims.extra.WFC_REGISTRATION_FAILURE_MESSAGE");
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setMessage(charSequenceExtra2);
        builder.setTitle(charSequenceExtra);
        builder.setIcon(R.drawable.ic_dialog_alert);
        builder.setPositiveButton(R.string.ok, (DialogInterface.OnClickListener) null);
        builder.create().show();
    }

    @VisibleForTesting
    ImsManager getImsManager() {
        return ImsManager.getInstance(getActivity(), SubscriptionManager.getPhoneId(this.mSubId));
    }

    @Override // com.android.car.developeroptions.SettingsPreferenceFragment, com.android.settingslib.core.lifecycle.ObservablePreferenceFragment, androidx.preference.PreferenceFragmentCompat, androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        addPreferencesFromResource(com.android.car.developeroptions.R.xml.wifi_calling_settings);
        if (getArguments() != null && getArguments().containsKey("subId")) {
            this.mSubId = getArguments().getInt("subId");
        } else if (bundle != null) {
            this.mSubId = bundle.getInt("subId", -1);
        }
        this.mImsManager = getImsManager();
        this.mTelephonyManager = ((TelephonyManager) getSystemService("phone")).createForSubscriptionId(this.mSubId);
        ListWithEntrySummaryPreference listWithEntrySummaryPreference = (ListWithEntrySummaryPreference) findPreference("wifi_calling_mode");
        this.mButtonWfcMode = listWithEntrySummaryPreference;
        listWithEntrySummaryPreference.setOnPreferenceChangeListener(this);
        ListWithEntrySummaryPreference listWithEntrySummaryPreference2 = (ListWithEntrySummaryPreference) findPreference("wifi_calling_roaming_mode");
        this.mButtonWfcRoamingMode = listWithEntrySummaryPreference2;
        listWithEntrySummaryPreference2.setOnPreferenceChangeListener(this);
        Preference preferenceFindPreference = findPreference("emergency_address_key");
        this.mUpdateAddress = preferenceFindPreference;
        preferenceFindPreference.setOnPreferenceClickListener(this.mUpdateAddressListener);
        IntentFilter intentFilter = new IntentFilter();
        this.mIntentFilter = intentFilter;
        intentFilter.addAction("android.telephony.ims.action.WFC_IMS_REGISTRATION_ERROR");
    }

    @Override // com.android.car.developeroptions.SettingsPreferenceFragment, com.android.settingslib.core.lifecycle.ObservablePreferenceFragment, androidx.preference.PreferenceFragmentCompat, androidx.fragment.app.Fragment
    public void onSaveInstanceState(Bundle bundle) {
        bundle.putInt("subId", this.mSubId);
        super.onSaveInstanceState(bundle);
    }

    @Override // com.android.car.developeroptions.SettingsPreferenceFragment, androidx.preference.PreferenceFragmentCompat, androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View viewInflate = layoutInflater.inflate(com.android.car.developeroptions.R.layout.wifi_calling_settings_preferences, viewGroup, false);
        ViewGroup viewGroup2 = (ViewGroup) viewInflate.findViewById(com.android.car.developeroptions.R.id.prefs_container);
        Utils.prepareCustomPreferencesList(viewGroup, viewInflate, viewGroup2, false);
        viewGroup2.addView(super.onCreateView(layoutInflater, viewGroup2, bundle));
        return viewInflate;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateBody() {
        boolean z;
        PersistableBundle configForSubId;
        if (!this.mImsManager.isWfcProvisionedOnDevice()) {
            finish();
            return;
        }
        CarrierConfigManager carrierConfigManager = (CarrierConfigManager) getSystemService("carrier_config");
        if (carrierConfigManager == null || (configForSubId = carrierConfigManager.getConfigForSubId(this.mSubId)) == null) {
            z = true;
        } else {
            this.mEditableWfcMode = configForSubId.getBoolean("editable_wfc_mode_bool");
            this.mEditableWfcRoamingMode = configForSubId.getBoolean("editable_wfc_roaming_mode_bool");
            this.mUseWfcHomeModeForRoaming = configForSubId.getBoolean("use_wfc_home_network_mode_in_roaming_network_bool", false);
            z = configForSubId.getBoolean("carrier_wfc_supports_wifi_only_bool", true);
        }
        if (!z) {
            this.mButtonWfcMode.setEntries(com.android.car.developeroptions.R.array.wifi_calling_mode_choices_without_wifi_only);
            this.mButtonWfcMode.setEntryValues(com.android.car.developeroptions.R.array.wifi_calling_mode_values_without_wifi_only);
            this.mButtonWfcMode.setEntrySummaries(com.android.car.developeroptions.R.array.wifi_calling_mode_summaries_without_wifi_only);
            this.mButtonWfcRoamingMode.setEntries(com.android.car.developeroptions.R.array.wifi_calling_mode_choices_v2_without_wifi_only);
            this.mButtonWfcRoamingMode.setEntryValues(com.android.car.developeroptions.R.array.wifi_calling_mode_values_without_wifi_only);
            this.mButtonWfcRoamingMode.setEntrySummaries(com.android.car.developeroptions.R.array.wifi_calling_mode_summaries_without_wifi_only);
        }
        boolean z2 = this.mImsManager.isWfcEnabledByUser() && this.mImsManager.isNonTtyOrTtyOnVolteEnabled();
        this.mSwitch.setChecked(z2);
        int wfcMode = this.mImsManager.getWfcMode(false);
        int wfcMode2 = this.mImsManager.getWfcMode(true);
        this.mButtonWfcMode.setValue(Integer.toString(wfcMode));
        this.mButtonWfcRoamingMode.setValue(Integer.toString(wfcMode2));
        updateButtonWfcMode(z2, wfcMode, wfcMode2);
    }

    @Override // com.android.car.developeroptions.SettingsPreferenceFragment, com.android.car.developeroptions.core.InstrumentedPreferenceFragment, com.android.settingslib.core.lifecycle.ObservablePreferenceFragment, androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        FragmentActivity activity = getActivity();
        updateBody();
        if (this.mImsManager.isWfcEnabledByPlatform()) {
            this.mTelephonyManager.listen(this.mPhoneStateListener, 32);
            this.mSwitchBar.addOnSwitchChangeListener(this);
            this.mValidListener = true;
        }
        activity.registerReceiver(this.mIntentReceiver, this.mIntentFilter);
        Intent intent = getActivity().getIntent();
        if (intent.getBooleanExtra("alertShow", false)) {
            showAlert(intent);
        }
        try {
            this.mImsManager.getConfigInterface().addConfigCallback(this.mProvisioningCallback);
        } catch (ImsException unused) {
            Log.w("WifiCallingForSub", "onResume: Unable to register callback for provisioning changes.");
        }
    }

    @Override // com.android.settingslib.core.lifecycle.ObservablePreferenceFragment, androidx.fragment.app.Fragment
    public void onPause() {
        super.onPause();
        FragmentActivity activity = getActivity();
        if (this.mValidListener) {
            this.mValidListener = false;
            ((TelephonyManager) getSystemService("phone")).listen(this.mPhoneStateListener, 0);
            this.mSwitchBar.removeOnSwitchChangeListener(this);
        }
        activity.unregisterReceiver(this.mIntentReceiver);
        try {
            this.mImsManager.getConfigInterface().removeConfigCallback(this.mProvisioningCallback.getBinder());
        } catch (ImsException unused) {
            Log.w("WifiCallingForSub", "onPause: Unable to remove callback for provisioning changes");
        }
    }

    @Override // com.android.car.developeroptions.widget.SwitchBar.OnSwitchChangeListener
    public void onSwitchChanged(Switch r3, boolean z) {
        Log.d("WifiCallingForSub", "onSwitchChanged(" + z + ")");
        if (!z) {
            updateWfcMode(false);
            return;
        }
        FragmentActivity activity = getActivity();
        Bundle bundle = new Bundle();
        bundle.putInt("EXTRA_SUB_ID", this.mSubId);
        SubSettingLauncher subSettingLauncher = new SubSettingLauncher(activity);
        subSettingLauncher.setDestination(WifiCallingDisclaimerFragment.class.getName());
        subSettingLauncher.setArguments(bundle);
        subSettingLauncher.setTitleRes(com.android.car.developeroptions.R.string.wifi_calling_settings_title);
        subSettingLauncher.setSourceMetricsCategory(getMetricsCategory());
        subSettingLauncher.setResultListener(this, 2);
        subSettingLauncher.launch();
    }

    private Intent getCarrierActivityIntent() {
        PersistableBundle configForSubId;
        ComponentName componentNameUnflattenFromString;
        CarrierConfigManager carrierConfigManager = (CarrierConfigManager) getActivity().getSystemService(CarrierConfigManager.class);
        if (carrierConfigManager == null || (configForSubId = carrierConfigManager.getConfigForSubId(this.mSubId)) == null) {
            return null;
        }
        String string = configForSubId.getString("wfc_emergency_address_carrier_app_string");
        if (TextUtils.isEmpty(string) || (componentNameUnflattenFromString = ComponentName.unflattenFromString(string)) == null) {
            return null;
        }
        Intent intent = new Intent();
        intent.setComponent(componentNameUnflattenFromString);
        intent.putExtra("android.telephony.extra.SUBSCRIPTION_INDEX", this.mSubId);
        return intent;
    }

    private void updateWfcMode(boolean z) {
        Log.i("WifiCallingForSub", "updateWfcMode(" + z + ")");
        this.mImsManager.setWfcSetting(z);
        int wfcMode = this.mImsManager.getWfcMode(false);
        updateButtonWfcMode(z, wfcMode, this.mImsManager.getWfcMode(true));
        if (z) {
            this.mMetricsFeatureProvider.action(getActivity(), getMetricsCategory(), wfcMode);
        } else {
            this.mMetricsFeatureProvider.action(getActivity(), getMetricsCategory(), -1);
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        getActivity();
        Log.d("WifiCallingForSub", "WFC activity request = " + i + " result = " + i2);
        if (i == 1) {
            if (i2 == -1) {
                updateWfcMode(true);
            }
        } else {
            if (i != 2) {
                Log.e("WifiCallingForSub", "Unexpected request: " + i);
                return;
            }
            if (i2 == -1) {
                Intent carrierActivityIntent = getCarrierActivityIntent();
                if (carrierActivityIntent != null) {
                    carrierActivityIntent.putExtra("EXTRA_LAUNCH_CARRIER_APP", 0);
                    startActivityForResult(carrierActivityIntent, 1);
                } else {
                    updateWfcMode(true);
                }
            }
        }
    }

    private void updateButtonWfcMode(boolean z, int i, int i2) {
        this.mButtonWfcMode.setSummary(getWfcModeSummary(i));
        this.mButtonWfcMode.setEnabled(z && this.mEditableWfcMode);
        this.mButtonWfcRoamingMode.setEnabled(z && this.mEditableWfcRoamingMode);
        PreferenceScreen preferenceScreen = getPreferenceScreen();
        boolean z2 = getCarrierActivityIntent() != null;
        if (z) {
            if (this.mEditableWfcMode) {
                preferenceScreen.addPreference(this.mButtonWfcMode);
            } else {
                preferenceScreen.removePreference(this.mButtonWfcMode);
            }
            if (this.mEditableWfcRoamingMode && !this.mUseWfcHomeModeForRoaming) {
                preferenceScreen.addPreference(this.mButtonWfcRoamingMode);
            } else {
                preferenceScreen.removePreference(this.mButtonWfcRoamingMode);
            }
            if (z2) {
                preferenceScreen.addPreference(this.mUpdateAddress);
                return;
            } else {
                preferenceScreen.removePreference(this.mUpdateAddress);
                return;
            }
        }
        preferenceScreen.removePreference(this.mButtonWfcMode);
        preferenceScreen.removePreference(this.mButtonWfcRoamingMode);
        preferenceScreen.removePreference(this.mUpdateAddress);
    }

    @Override // androidx.preference.Preference.OnPreferenceChangeListener
    public boolean onPreferenceChange(Preference preference, Object obj) {
        if (preference == this.mButtonWfcMode) {
            Log.d("WifiCallingForSub", "onPreferenceChange mButtonWfcMode " + obj);
            String str = (String) obj;
            this.mButtonWfcMode.setValue(str);
            int iIntValue = Integer.valueOf(str).intValue();
            if (iIntValue != this.mImsManager.getWfcMode(false)) {
                this.mImsManager.setWfcMode(iIntValue, false);
                this.mButtonWfcMode.setSummary(getWfcModeSummary(iIntValue));
                this.mMetricsFeatureProvider.action(getActivity(), getMetricsCategory(), iIntValue);
                if (this.mUseWfcHomeModeForRoaming) {
                    this.mImsManager.setWfcMode(iIntValue, true);
                }
            }
        } else {
            ListWithEntrySummaryPreference listWithEntrySummaryPreference = this.mButtonWfcRoamingMode;
            if (preference == listWithEntrySummaryPreference) {
                String str2 = (String) obj;
                listWithEntrySummaryPreference.setValue(str2);
                int iIntValue2 = Integer.valueOf(str2).intValue();
                if (iIntValue2 != this.mImsManager.getWfcMode(true)) {
                    this.mImsManager.setWfcMode(iIntValue2, true);
                    this.mMetricsFeatureProvider.action(getActivity(), getMetricsCategory(), iIntValue2);
                }
            }
        }
        return true;
    }

    private int getWfcModeSummary(int i) {
        if (this.mImsManager.isWfcEnabledByUser()) {
            if (i == 0) {
                return R.string.permlab_mediaLocation;
            }
            if (i == 1) {
                return R.string.permlab_manageProfileAndDeviceOwners;
            }
            if (i == 2) {
                return R.string.permlab_modifyAudioSettings;
            }
            Log.e("WifiCallingForSub", "Unexpected WFC mode value: " + i);
        }
        return R.string.permlab_receiveSms;
    }

    @VisibleForTesting
    Resources getResourcesForSubId() {
        return SubscriptionManager.getResourcesForSubId(getContext(), this.mSubId, false);
    }
}
