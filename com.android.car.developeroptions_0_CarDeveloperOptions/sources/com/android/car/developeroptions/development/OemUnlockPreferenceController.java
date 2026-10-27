package com.android.car.developeroptions.development;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.UserHandle;
import android.os.UserManager;
import android.service.oemlock.OemLockManager;
import android.telephony.TelephonyManager;
import android.util.Log;
import androidx.preference.Preference;
import androidx.preference.PreferenceScreen;
import com.android.car.developeroptions.R;
import com.android.car.developeroptions.core.PreferenceControllerMixin;
import com.android.car.developeroptions.password.ChooseLockSettingsHelper;
import com.android.settingslib.RestrictedSwitchPreference;
import com.android.settingslib.development.DeveloperOptionsPreferenceController;

/* JADX INFO: loaded from: classes.dex */
public class OemUnlockPreferenceController extends DeveloperOptionsPreferenceController implements Preference.OnPreferenceChangeListener, PreferenceControllerMixin, OnActivityResultListener {
    private final ChooseLockSettingsHelper mChooseLockSettingsHelper;
    private final DevelopmentSettingsDashboardFragment mFragment;
    private final OemLockManager mOemLockManager;
    private RestrictedSwitchPreference mPreference;
    private final TelephonyManager mTelephonyManager;
    private final UserManager mUserManager;

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public String getPreferenceKey() {
        return "oem_unlock_enable";
    }

    public OemUnlockPreferenceController(Context context, Activity activity, DevelopmentSettingsDashboardFragment developmentSettingsDashboardFragment) {
        super(context);
        if (context.getPackageManager().hasSystemFeature("android.hardware.telephony.carrierlock")) {
            this.mOemLockManager = (OemLockManager) context.getSystemService("oem_lock");
        } else {
            this.mOemLockManager = null;
            Log.i("OemUnlockPreferenceController", "Missing FEATURE_TELEPHONY_CARRIERLOCK, OemUnlock Preference Controller disabled.");
        }
        this.mUserManager = (UserManager) context.getSystemService("user");
        this.mTelephonyManager = (TelephonyManager) context.getSystemService("phone");
        this.mFragment = developmentSettingsDashboardFragment;
        if (activity != null || developmentSettingsDashboardFragment != null) {
            this.mChooseLockSettingsHelper = new ChooseLockSettingsHelper(activity, this.mFragment);
        } else {
            this.mChooseLockSettingsHelper = null;
        }
    }

    @Override // com.android.settingslib.development.DeveloperOptionsPreferenceController, com.android.settingslib.core.AbstractPreferenceController
    public boolean isAvailable() {
        return this.mOemLockManager != null;
    }

    @Override // com.android.settingslib.development.DeveloperOptionsPreferenceController, com.android.settingslib.core.AbstractPreferenceController
    public void displayPreference(PreferenceScreen preferenceScreen) {
        super.displayPreference(preferenceScreen);
        this.mPreference = (RestrictedSwitchPreference) preferenceScreen.findPreference(getPreferenceKey());
    }

    @Override // androidx.preference.Preference.OnPreferenceChangeListener
    public boolean onPreferenceChange(Preference preference, Object obj) {
        if (((Boolean) obj).booleanValue()) {
            if (showKeyguardConfirmation(this.mContext.getResources(), 0)) {
                return true;
            }
            confirmEnableOemUnlock();
            return true;
        }
        this.mOemLockManager.setOemUnlockAllowedByUser(false);
        OemLockInfoDialog.show(this.mFragment);
        return true;
    }

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public void updateState(Preference preference) {
        super.updateState(preference);
        this.mPreference.setChecked(isOemUnlockedAllowed());
        updateOemUnlockSettingDescription();
        this.mPreference.setDisabledByAdmin(null);
        this.mPreference.setEnabled(enableOemUnlockPreference());
        if (this.mPreference.isEnabled()) {
            this.mPreference.checkRestrictionAndSetDisabled("no_factory_reset");
        }
    }

    @Override // com.android.car.developeroptions.development.OnActivityResultListener
    public boolean onActivityResult(int i, int i2, Intent intent) {
        if (i != 0) {
            return false;
        }
        if (i2 != -1) {
            return true;
        }
        if (this.mPreference.isChecked()) {
            confirmEnableOemUnlock();
            return true;
        }
        this.mOemLockManager.setOemUnlockAllowedByUser(false);
        return true;
    }

    @Override // com.android.settingslib.development.DeveloperOptionsPreferenceController
    protected void onDeveloperOptionsSwitchEnabled() {
        handleDeveloperOptionsToggled();
    }

    public void onOemUnlockConfirmed() {
        this.mOemLockManager.setOemUnlockAllowedByUser(true);
    }

    public void onOemUnlockDismissed() {
        RestrictedSwitchPreference restrictedSwitchPreference = this.mPreference;
        if (restrictedSwitchPreference == null) {
            return;
        }
        updateState(restrictedSwitchPreference);
    }

    private void handleDeveloperOptionsToggled() {
        this.mPreference.setEnabled(enableOemUnlockPreference());
        if (this.mPreference.isEnabled()) {
            this.mPreference.checkRestrictionAndSetDisabled("no_factory_reset");
        }
    }

    private void updateOemUnlockSettingDescription() {
        int i;
        if (isBootloaderUnlocked()) {
            i = R.string.oem_unlock_enable_disabled_summary_bootloader_unlocked;
        } else if (isSimLockedDevice()) {
            i = R.string.oem_unlock_enable_disabled_summary_sim_locked_device;
        } else {
            i = !isOemUnlockAllowedByUserAndCarrier() ? R.string.oem_unlock_enable_disabled_summary_connectivity_or_locked : R.string.oem_unlock_enable_summary;
        }
        this.mPreference.setSummary(this.mContext.getResources().getString(i));
    }

    private boolean isSimLockedDevice() {
        int phoneCount = this.mTelephonyManager.getPhoneCount();
        for (int i = 0; i < phoneCount; i++) {
            if (this.mTelephonyManager.getAllowedCarriers(i).size() > 0) {
                return true;
            }
        }
        return false;
    }

    boolean isBootloaderUnlocked() {
        return this.mOemLockManager.isDeviceOemUnlocked();
    }

    private boolean enableOemUnlockPreference() {
        return !isBootloaderUnlocked() && isOemUnlockAllowedByUserAndCarrier();
    }

    boolean showKeyguardConfirmation(Resources resources, int i) {
        return this.mChooseLockSettingsHelper.launchConfirmationActivity(i, resources.getString(R.string.oem_unlock_enable));
    }

    void confirmEnableOemUnlock() {
        EnableOemUnlockSettingWarningDialog.show(this.mFragment);
    }

    boolean isOemUnlockAllowedByUserAndCarrier() {
        return this.mOemLockManager.isOemUnlockAllowedByCarrier() && !this.mUserManager.hasBaseUserRestriction("no_factory_reset", UserHandle.of(UserHandle.myUserId()));
    }

    boolean isOemUnlockedAllowed() {
        return this.mOemLockManager.isOemUnlockAllowed();
    }
}
