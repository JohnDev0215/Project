package com.android.car.settings.wifi;

import android.car.drivingstate.CarUxRestrictions;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.wifi.SoftApConfiguration;
import android.text.TextUtils;
import android.util.Log;
import com.android.car.settings.R;
import com.android.car.settings.common.FragmentController;
import com.android.car.settings.common.ValidatedEditTextPreference;
import java.util.UUID;

/* JADX INFO: loaded from: classes5.dex */
public class WifiTetherPasswordPreferenceController extends WifiTetherBasePreferenceController<ValidatedEditTextPreference> {
    private static final int HOTSPOT_PASSWORD_MAX_LENGTH = 63;
    private static final int HOTSPOT_PASSWORD_MIN_LENGTH = 8;
    protected static final String KEY_SAVED_PASSWORD = "com.android.car.settings.wifi.SAVED_PASSWORD";
    private static final ValidatedEditTextPreference.Validator PASSWORD_VALIDATOR = new ValidatedEditTextPreference.Validator() { // from class: com.android.car.settings.wifi.-$$Lambda$WifiTetherPasswordPreferenceController$uknLkjhbI6OoDc6RFJrNdapJnng
        @Override // com.android.car.settings.common.ValidatedEditTextPreference.Validator
        public final boolean isTextValid(String str) {
            return WifiTetherPasswordPreferenceController.lambda$static$0(str);
        }
    };
    protected static final String SHARED_PREFERENCE_PATH = "com.android.car.settings.wifi.WifiTetherPreferenceController";
    private static final int SHARED_SECURITY_TYPE_UNSET = -1;
    private static final String TAG = "CarWifiTetherApPwdPref";
    private String mPassword;
    private int mSecurityType;
    private final SharedPreferences mSharedPreferences;

    static /* synthetic */ boolean lambda$static$0(String value) {
        return value.length() >= 8 && value.length() <= 63;
    }

    public WifiTetherPasswordPreferenceController(Context context, String preferenceKey, FragmentController fragmentController, CarUxRestrictions uxRestrictions) {
        super(context, preferenceKey, fragmentController, uxRestrictions);
        this.mSharedPreferences = getContext().getSharedPreferences(SHARED_PREFERENCE_PATH, 0);
    }

    @Override // com.android.car.settings.common.PreferenceController
    protected Class<ValidatedEditTextPreference> getPreferenceType() {
        return ValidatedEditTextPreference.class;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.android.car.settings.wifi.WifiTetherBasePreferenceController, com.android.car.settings.common.PreferenceController
    protected void onCreateInternal() {
        super.onCreateInternal();
        ((ValidatedEditTextPreference) getPreference()).setValidator(PASSWORD_VALIDATOR);
        this.mSecurityType = getCarSoftApConfig().getSecurityType();
        syncPassword();
    }

    @Override // com.android.car.settings.wifi.WifiTetherBasePreferenceController, com.android.car.settings.common.PreferenceController
    protected void onStartInternal() {
        int newSecurityType = this.mSharedPreferences.getInt("com.android.car.settings.wifi.KEY_SECURITY_TYPE", -1);
        if (this.mSecurityType != newSecurityType && newSecurityType != -1) {
            this.mSecurityType = newSecurityType;
            syncPassword();
            updateApSecurity(this.mPassword);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.android.car.settings.common.PreferenceController
    public boolean handlePreferenceChanged(ValidatedEditTextPreference preference, Object newValue) {
        if (!newValue.toString().equals(this.mPassword)) {
            String string = newValue.toString();
            this.mPassword = string;
            updateApSecurity(string);
            refreshUi();
            return true;
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.android.car.settings.wifi.WifiTetherBasePreferenceController, com.android.car.settings.common.PreferenceController
    public void updateState(ValidatedEditTextPreference preference) {
        super.updateState(preference);
        updatePasswordDisplay();
        if (TextUtils.isEmpty(this.mPassword)) {
            preference.setSummaryInputType(1);
        } else {
            preference.setSummaryInputType(129);
        }
    }

    @Override // com.android.car.settings.wifi.WifiTetherBasePreferenceController
    protected String getSummary() {
        return this.mPassword;
    }

    @Override // com.android.car.settings.wifi.WifiTetherBasePreferenceController
    protected String getDefaultSummary() {
        return getContext().getString(R.string.default_password_summary);
    }

    private void syncPassword() {
        this.mPassword = getSyncedPassword();
        refreshUi();
    }

    private String getSyncedPassword() {
        if (isOpenOweHotspot(this.mSecurityType)) {
            return null;
        }
        String passphrase = getCarSoftApConfig().getPassphrase();
        if (!TextUtils.isEmpty(passphrase)) {
            return passphrase;
        }
        if (!TextUtils.isEmpty(this.mSharedPreferences.getString(KEY_SAVED_PASSWORD, null))) {
            return this.mSharedPreferences.getString(KEY_SAVED_PASSWORD, null);
        }
        return generateRandomPassword();
    }

    private static String generateRandomPassword() {
        String randomUUID = UUID.randomUUID().toString();
        return randomUUID.substring(0, 8) + randomUUID.substring(9, 13);
    }

    private void updateApSecurity(String password) {
        String passwordOrNullIfOpen;
        if (isOpenOweHotspot(this.mSecurityType)) {
            passwordOrNullIfOpen = null;
            Log.w(TAG, "Setting password on an open network!");
        } else {
            passwordOrNullIfOpen = password;
        }
        SoftApConfiguration config = new SoftApConfiguration.Builder(getCarSoftApConfig()).setPassphrase(passwordOrNullIfOpen, this.mSecurityType).build();
        setCarSoftApConfig(config);
        if (!TextUtils.isEmpty(password)) {
            this.mSharedPreferences.edit().putString(KEY_SAVED_PASSWORD, password).commit();
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    private void updatePasswordDisplay() {
        ((ValidatedEditTextPreference) getPreference()).setText(this.mPassword);
        ((ValidatedEditTextPreference) getPreference()).setVisible(!isOpenOweHotspot(this.mSecurityType));
        ((ValidatedEditTextPreference) getPreference()).setSummary(getSummary());
    }

    private boolean isOpenOweHotspot(int security) {
        return security == 0 || security == 4 || security == 5;
    }
}
