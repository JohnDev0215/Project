package com.android.server.telecom.settings;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.preference.PreferenceScreen;
import android.preference.SwitchPreference;
import android.telecom.Log;
import android.telephony.CarrierConfigManager;
import android.telephony.SubscriptionManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.android.server.telecom.R;

/* JADX INFO: loaded from: classes.dex */
public class EnhancedCallBlockingFragment extends PreferenceFragment implements Preference.OnPreferenceChangeListener {
    private boolean mIsCombiningRestrictedAndUnknownOption = false;

    @Override // android.preference.PreferenceFragment, android.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        addPreferencesFromResource(R.xml.enhanced_call_blocking_settings);
        maybeConfigureCallBlockingOptions();
        setOnPreferenceChangeListener("block_numbers_not_in_contacts_setting");
        setOnPreferenceChangeListener("block_private_number_calls_setting");
        setOnPreferenceChangeListener("block_payphone_calls_setting");
        setOnPreferenceChangeListener("block_unknown_calls_setting");
        if (showPayPhoneBlocking()) {
            return;
        }
        getPreferenceScreen().removePreference(getPreferenceScreen().findPreference("block_payphone_calls_setting"));
    }

    private boolean showPayPhoneBlocking() {
        PersistableBundle configForSubId;
        CarrierConfigManager carrierConfigManager = (CarrierConfigManager) getContext().getSystemService("carrier_config");
        if (carrierConfigManager == null || (configForSubId = carrierConfigManager.getConfigForSubId(SubscriptionManager.getDefaultVoiceSubscriptionId())) == null) {
            return false;
        }
        return configForSubId.getBoolean("show_blocking_pay_phone_option_bool");
    }

    private void maybeConfigureCallBlockingOptions() {
        PreferenceScreen preferenceScreen = getPreferenceScreen();
        if (!getResources().getBoolean(R.bool.show_option_to_block_callers_not_in_contacts)) {
            preferenceScreen.removePreference(findPreference("block_numbers_not_in_contacts_setting"));
            Log.i(this, "onCreate: removed block not in contacts preference.", new Object[0]);
        }
        boolean z = getResources().getBoolean(R.bool.combine_options_to_block_restricted_and_unknown_callers);
        this.mIsCombiningRestrictedAndUnknownOption = z;
        if (z) {
            preferenceScreen.removePreference(findPreference("block_private_number_calls_setting"));
            Log.i(this, "onCreate: removed block restricted preference.", new Object[0]);
        }
    }

    private void setOnPreferenceChangeListener(String str) {
        SwitchPreference switchPreference = (SwitchPreference) findPreference(str);
        if (switchPreference != null) {
            switchPreference.setOnPreferenceChangeListener(this);
        }
    }

    @Override // android.app.Fragment
    public void onResume() {
        super.onResume();
        updateEnhancedBlockPref("block_numbers_not_in_contacts_setting");
        updateEnhancedBlockPref("block_private_number_calls_setting");
        if (showPayPhoneBlocking()) {
            updateEnhancedBlockPref("block_payphone_calls_setting");
        }
        updateEnhancedBlockPref("block_unknown_calls_setting");
    }

    private void updateEnhancedBlockPref(String str) {
        SwitchPreference switchPreference = (SwitchPreference) findPreference(str);
        if (switchPreference != null) {
            switchPreference.setChecked(BlockedNumbersUtil.getEnhancedBlockSetting(getActivity(), str));
        }
    }

    @Override // android.preference.Preference.OnPreferenceChangeListener
    public boolean onPreferenceChange(Preference preference, Object obj) {
        if (this.mIsCombiningRestrictedAndUnknownOption && preference.getKey().equals("block_unknown_calls_setting")) {
            Boolean bool = (Boolean) obj;
            Log.i(this, "onPreferenceChange: changing %s and %s to %b", new Object[]{preference.getKey(), "block_private_number_calls_setting", Boolean.valueOf(bool.booleanValue())});
            BlockedNumbersUtil.setEnhancedBlockSetting(getActivity(), "block_private_number_calls_setting", bool.booleanValue());
        }
        BlockedNumbersUtil.setEnhancedBlockSetting(getActivity(), preference.getKey(), ((Boolean) obj).booleanValue());
        return true;
    }

    @Override // android.preference.PreferenceFragment, android.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return layoutInflater.inflate(R.xml.layout_customized_listview, viewGroup, false);
    }
}
