package com.android.phone;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceScreen;
import android.view.MenuItem;

/* JADX INFO: loaded from: classes.dex */
public class CdmaCallOptions extends PreferenceActivity {
    private CdmaVoicePrivacySwitchPreference mButtonVoicePrivacy;

    @Override // android.preference.PreferenceActivity, android.app.Activity
    protected void onCreate(Bundle bundle) {
        PersistableBundle carrierConfig;
        super.onCreate(bundle);
        addPreferencesFromResource(R.xml.cdma_call_privacy);
        SubscriptionInfoHelper subscriptionInfoHelper = new SubscriptionInfoHelper(this, getIntent());
        subscriptionInfoHelper.setActionBarTitle(getActionBar(), getResources(), R.string.labelCdmaMore_with_label);
        CdmaVoicePrivacySwitchPreference cdmaVoicePrivacySwitchPreference = (CdmaVoicePrivacySwitchPreference) findPreference("button_voice_privacy_key");
        this.mButtonVoicePrivacy = cdmaVoicePrivacySwitchPreference;
        cdmaVoicePrivacySwitchPreference.setPhone(subscriptionInfoHelper.getPhone());
        if (subscriptionInfoHelper.hasSubId()) {
            carrierConfig = PhoneGlobals.getInstance().getCarrierConfigForSubId(subscriptionInfoHelper.getSubId());
        } else {
            carrierConfig = PhoneGlobals.getInstance().getCarrierConfig();
        }
        if (subscriptionInfoHelper.getPhone().getPhoneType() != 2 || carrierConfig.getBoolean("voice_privacy_disable_ui_bool")) {
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

    @Override // android.preference.PreferenceActivity
    public boolean onPreferenceTreeClick(PreferenceScreen preferenceScreen, Preference preference) {
        return preference.getKey().equals("button_voice_privacy_key");
    }
}
