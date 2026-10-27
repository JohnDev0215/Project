package com.android.phone;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceScreen;
import android.provider.Settings;
import android.view.MenuItem;

/* JADX INFO: loaded from: classes.dex */
public class GsmUmtsCallOptions extends PreferenceActivity {
    @Override // android.preference.PreferenceActivity, android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        addPreferencesFromResource(R.xml.gsm_umts_call_options);
        SubscriptionInfoHelper subscriptionInfoHelper = new SubscriptionInfoHelper(this, getIntent());
        subscriptionInfoHelper.setActionBarTitle(getActionBar(), getResources(), R.string.labelGsmMore_with_label);
        init(getPreferenceScreen(), subscriptionInfoHelper);
        if (subscriptionInfoHelper.getPhone().getPhoneType() != 1) {
            getPreferenceScreen().setEnabled(false);
        }
    }

    @Override // android.preference.PreferenceActivity, android.app.Activity
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() == 16908332) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(menuItem);
    }

    public static void init(PreferenceScreen preferenceScreen, SubscriptionInfoHelper subscriptionInfoHelper) {
        PersistableBundle carrierConfig;
        if (subscriptionInfoHelper.hasSubId()) {
            carrierConfig = PhoneGlobals.getInstance().getCarrierConfigForSubId(subscriptionInfoHelper.getSubId());
        } else {
            carrierConfig = PhoneGlobals.getInstance().getCarrierConfig();
        }
        boolean z = true;
        if (carrierConfig != null && carrierConfig.getBoolean("disable_supplementary_services_in_airplane_mode_bool") && 1 == Settings.Global.getInt(subscriptionInfoHelper.getPhone().getContext().getContentResolver(), "airplane_mode_on", 0)) {
            z = false;
        }
        Preference preferenceFindPreference = preferenceScreen.findPreference("call_forwarding_key");
        if (preferenceFindPreference != null) {
            if (carrierConfig != null && carrierConfig.getBoolean("call_forwarding_visibility_bool")) {
                preferenceFindPreference.setIntent(subscriptionInfoHelper.getIntent(GsmUmtsCallForwardOptions.class));
                preferenceFindPreference.setEnabled(z);
            } else {
                preferenceScreen.removePreference(preferenceFindPreference);
            }
        }
        Preference preferenceFindPreference2 = preferenceScreen.findPreference("additional_gsm_call_settings_key");
        if (preferenceFindPreference2 != null) {
            if (carrierConfig != null && (carrierConfig.getBoolean("additional_settings_call_waiting_visibility_bool") || carrierConfig.getBoolean("additional_settings_caller_id_visibility_bool"))) {
                preferenceFindPreference2.setIntent(subscriptionInfoHelper.getIntent(GsmUmtsAdditionalCallOptions.class));
                preferenceFindPreference2.setEnabled(z);
            } else {
                preferenceScreen.removePreference(preferenceFindPreference2);
            }
        }
        Preference preferenceFindPreference3 = preferenceScreen.findPreference("call_barring_key");
        if (preferenceFindPreference3 != null) {
            if (carrierConfig != null && carrierConfig.getBoolean("call_barring_visibility_bool")) {
                preferenceFindPreference3.setIntent(subscriptionInfoHelper.getIntent(GsmUmtsCallBarringOptions.class));
                preferenceFindPreference3.setEnabled(z);
            } else {
                preferenceScreen.removePreference(preferenceFindPreference3);
            }
        }
    }
}
