package com.android.car.developeroptions.security;

import android.app.admin.DevicePolicyManager;
import android.content.Context;
import android.os.UserHandle;
import android.os.UserManager;
import android.os.storage.StorageManager;
import android.text.TextUtils;
import androidx.preference.Preference;
import androidx.preference.PreferenceScreen;
import com.android.car.developeroptions.R;
import com.android.car.developeroptions.Utils;
import com.android.car.developeroptions.core.PreferenceControllerMixin;
import com.android.car.developeroptions.core.SubSettingLauncher;
import com.android.car.developeroptions.overlay.FeatureFactory;
import com.android.car.developeroptions.password.ChooseLockGeneric;
import com.android.car.developeroptions.security.screenlock.ScreenLockSettings;
import com.android.car.developeroptions.widget.GearPreference;
import com.android.internal.widget.LockPatternUtils;
import com.android.settingslib.RestrictedLockUtils;
import com.android.settingslib.RestrictedLockUtilsInternal;
import com.android.settingslib.RestrictedPreference;
import com.android.settingslib.core.AbstractPreferenceController;

/* JADX INFO: loaded from: classes.dex */
public class ChangeScreenLockPreferenceController extends AbstractPreferenceController implements PreferenceControllerMixin, GearPreference.OnGearClickListener {
    protected final SecuritySettings mHost;
    protected final LockPatternUtils mLockPatternUtils;
    protected RestrictedPreference mPreference;
    protected final int mProfileChallengeUserId;
    protected final UserManager mUm;
    protected final int mUserId;

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public String getPreferenceKey() {
        return "unlock_set_or_change";
    }

    public ChangeScreenLockPreferenceController(Context context, SecuritySettings securitySettings) {
        super(context);
        this.mUserId = UserHandle.myUserId();
        this.mUm = (UserManager) context.getSystemService("user");
        this.mLockPatternUtils = FeatureFactory.getFactory(context).getSecurityFeatureProvider().getLockPatternUtils(context);
        this.mHost = securitySettings;
        this.mProfileChallengeUserId = Utils.getManagedProfileId(this.mUm, this.mUserId);
    }

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public boolean isAvailable() {
        return this.mContext.getResources().getBoolean(R.bool.config_show_unlock_set_or_change);
    }

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public void displayPreference(PreferenceScreen preferenceScreen) {
        super.displayPreference(preferenceScreen);
        this.mPreference = (RestrictedPreference) preferenceScreen.findPreference(getPreferenceKey());
    }

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public void updateState(Preference preference) {
        RestrictedPreference restrictedPreference = this.mPreference;
        if (restrictedPreference != null && (restrictedPreference instanceof GearPreference)) {
            if (this.mLockPatternUtils.isSecure(this.mUserId)) {
                ((GearPreference) this.mPreference).setOnGearClickListener(this);
            } else {
                ((GearPreference) this.mPreference).setOnGearClickListener(null);
            }
        }
        updateSummary(preference, this.mUserId);
        disableIfPasswordQualityManaged(this.mUserId);
        if (this.mLockPatternUtils.isSeparateProfileChallengeEnabled(this.mProfileChallengeUserId)) {
            return;
        }
        disableIfPasswordQualityManaged(this.mProfileChallengeUserId);
    }

    @Override // com.android.car.developeroptions.widget.GearPreference.OnGearClickListener
    public void onGearClick(GearPreference gearPreference) {
        if (TextUtils.equals(gearPreference.getKey(), getPreferenceKey())) {
            SubSettingLauncher subSettingLauncher = new SubSettingLauncher(this.mContext);
            subSettingLauncher.setDestination(ScreenLockSettings.class.getName());
            subSettingLauncher.setSourceMetricsCategory(this.mHost.getMetricsCategory());
            subSettingLauncher.launch();
        }
    }

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public boolean handlePreferenceTreeClick(Preference preference) {
        if (!TextUtils.equals(preference.getKey(), getPreferenceKey())) {
            return super.handlePreferenceTreeClick(preference);
        }
        int i = this.mProfileChallengeUserId;
        if (i != -10000 && !this.mLockPatternUtils.isSeparateProfileChallengeEnabled(i) && StorageManager.isFileEncryptedNativeOnly() && Utils.startQuietModeDialogIfNecessary(this.mContext, this.mUm, this.mProfileChallengeUserId)) {
            return false;
        }
        SubSettingLauncher subSettingLauncher = new SubSettingLauncher(this.mContext);
        subSettingLauncher.setDestination(ChooseLockGeneric.ChooseLockGenericFragment.class.getName());
        subSettingLauncher.setTitleRes(R.string.lock_settings_picker_title);
        subSettingLauncher.setSourceMetricsCategory(this.mHost.getMetricsCategory());
        subSettingLauncher.launch();
        return true;
    }

    protected void updateSummary(Preference preference, int i) {
        if (!this.mLockPatternUtils.isSecure(i)) {
            if (i == this.mProfileChallengeUserId || this.mLockPatternUtils.isLockScreenDisabled(i)) {
                preference.setSummary(R.string.unlock_set_unlock_mode_off);
            } else {
                preference.setSummary(R.string.unlock_set_unlock_mode_none);
            }
        } else {
            int keyguardStoredPasswordQuality = this.mLockPatternUtils.getKeyguardStoredPasswordQuality(i);
            if (keyguardStoredPasswordQuality == 65536) {
                preference.setSummary(R.string.unlock_set_unlock_mode_pattern);
            } else if (keyguardStoredPasswordQuality == 131072 || keyguardStoredPasswordQuality == 196608) {
                preference.setSummary(R.string.unlock_set_unlock_mode_pin);
            } else if (keyguardStoredPasswordQuality == 262144 || keyguardStoredPasswordQuality == 327680 || keyguardStoredPasswordQuality == 393216 || keyguardStoredPasswordQuality == 524288) {
                preference.setSummary(R.string.unlock_set_unlock_mode_password);
            }
        }
        this.mPreference.setEnabled(true);
    }

    void disableIfPasswordQualityManaged(int i) {
        RestrictedLockUtils.EnforcedAdmin enforcedAdminCheckIfPasswordQualityIsSet = RestrictedLockUtilsInternal.checkIfPasswordQualityIsSet(this.mContext, i);
        DevicePolicyManager devicePolicyManager = (DevicePolicyManager) this.mContext.getSystemService("device_policy");
        if (enforcedAdminCheckIfPasswordQualityIsSet == null || devicePolicyManager.getPasswordQuality(enforcedAdminCheckIfPasswordQualityIsSet.component, i) != 524288) {
            return;
        }
        this.mPreference.setDisabledByAdmin(enforcedAdminCheckIfPasswordQualityIsSet);
    }
}
