package com.android.car.developeroptions.wifi.tether;

import android.content.Context;
import android.net.wifi.SoftApConfiguration;
import android.text.TextUtils;
import androidx.preference.EditTextPreference;
import androidx.preference.Preference;
import com.android.car.developeroptions.R;
import com.android.car.developeroptions.widget.ValidatedEditTextPreference;
import com.android.car.developeroptions.wifi.WifiUtils;
import com.android.car.developeroptions.wifi.tether.WifiTetherBasePreferenceController;
import java.util.UUID;

/* JADX INFO: loaded from: classes.dex */
public class WifiTetherPasswordPreferenceController extends WifiTetherBasePreferenceController implements ValidatedEditTextPreference.Validator {
    private String mPassword;

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public String getPreferenceKey() {
        return "wifi_tether_network_password";
    }

    public WifiTetherPasswordPreferenceController(Context context, WifiTetherBasePreferenceController.OnTetherConfigUpdateListener onTetherConfigUpdateListener) {
        super(context, onTetherConfigUpdateListener);
    }

    @Override // com.android.car.developeroptions.wifi.tether.WifiTetherBasePreferenceController
    public void updateDisplay() {
        SoftApConfiguration softApConfiguration = this.mWifiManager.getSoftApConfiguration();
        if (softApConfiguration == null || (softApConfiguration.getSecurityType() == 1 && TextUtils.isEmpty(softApConfiguration.getPassphrase()))) {
            this.mPassword = generateRandomPassword();
        } else {
            this.mPassword = softApConfiguration.getPassphrase();
        }
        ((ValidatedEditTextPreference) this.mPreference).setValidator(this);
        ((ValidatedEditTextPreference) this.mPreference).setIsPassword(true);
        ((ValidatedEditTextPreference) this.mPreference).setIsSummaryPassword(true);
        updatePasswordDisplay((EditTextPreference) this.mPreference);
    }

    @Override // androidx.preference.Preference.OnPreferenceChangeListener
    public boolean onPreferenceChange(Preference preference, Object obj) {
        this.mPassword = (String) obj;
        updatePasswordDisplay((EditTextPreference) this.mPreference);
        this.mListener.onTetherConfigUpdated();
        return true;
    }

    public String getPasswordValidated(int i) {
        if (i == 0) {
            return null;
        }
        if (!isTextValid(this.mPassword)) {
            this.mPassword = generateRandomPassword();
            updatePasswordDisplay((EditTextPreference) this.mPreference);
        }
        return this.mPassword;
    }

    public void updateVisibility(int i) {
        this.mPreference.setVisible(i != 0);
    }

    @Override // com.android.car.developeroptions.widget.ValidatedEditTextPreference.Validator
    public boolean isTextValid(String str) {
        return WifiUtils.isHotspotPasswordValid(str);
    }

    private static String generateRandomPassword() {
        String string = UUID.randomUUID().toString();
        return string.substring(0, 8) + string.substring(9, 13);
    }

    private void updatePasswordDisplay(EditTextPreference editTextPreference) {
        ValidatedEditTextPreference validatedEditTextPreference = (ValidatedEditTextPreference) editTextPreference;
        validatedEditTextPreference.setText(this.mPassword);
        if (!TextUtils.isEmpty(this.mPassword)) {
            validatedEditTextPreference.setIsSummaryPassword(true);
            validatedEditTextPreference.setSummary(this.mPassword);
            validatedEditTextPreference.setVisible(true);
        } else {
            validatedEditTextPreference.setIsSummaryPassword(false);
            validatedEditTextPreference.setSummary(R.string.wifi_hotspot_no_password_subtext);
            validatedEditTextPreference.setVisible(false);
        }
    }
}
