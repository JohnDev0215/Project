package com.android.car.developeroptions.security;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import androidx.preference.Preference;
import com.android.car.developeroptions.R;
import com.android.car.developeroptions.Utils;
import com.android.car.developeroptions.core.SubSettingLauncher;
import com.android.car.developeroptions.password.ChooseLockGeneric;

/* JADX INFO: loaded from: classes.dex */
public class ChangeProfileScreenLockPreferenceController extends ChangeScreenLockPreferenceController {
    @Override // com.android.car.developeroptions.security.ChangeScreenLockPreferenceController, com.android.settingslib.core.AbstractPreferenceController
    public String getPreferenceKey() {
        return "unlock_set_or_change_profile";
    }

    public ChangeProfileScreenLockPreferenceController(Context context, SecuritySettings securitySettings) {
        super(context, securitySettings);
    }

    @Override // com.android.car.developeroptions.security.ChangeScreenLockPreferenceController, com.android.settingslib.core.AbstractPreferenceController
    public boolean isAvailable() {
        int keyguardStoredPasswordQuality;
        int i = this.mProfileChallengeUserId;
        if (i == -10000 || !this.mLockPatternUtils.isSeparateProfileChallengeAllowed(i)) {
            return false;
        }
        return !this.mLockPatternUtils.isSecure(this.mProfileChallengeUserId) || (keyguardStoredPasswordQuality = this.mLockPatternUtils.getKeyguardStoredPasswordQuality(this.mProfileChallengeUserId)) == 65536 || keyguardStoredPasswordQuality == 131072 || keyguardStoredPasswordQuality == 196608 || keyguardStoredPasswordQuality == 262144 || keyguardStoredPasswordQuality == 327680 || keyguardStoredPasswordQuality == 393216 || keyguardStoredPasswordQuality == 524288;
    }

    @Override // com.android.car.developeroptions.security.ChangeScreenLockPreferenceController, com.android.settingslib.core.AbstractPreferenceController
    public boolean handlePreferenceTreeClick(Preference preference) {
        if (!TextUtils.equals(preference.getKey(), getPreferenceKey()) || Utils.startQuietModeDialogIfNecessary(this.mContext, this.mUm, this.mProfileChallengeUserId)) {
            return false;
        }
        Bundle bundle = new Bundle();
        bundle.putInt("android.intent.extra.USER_ID", this.mProfileChallengeUserId);
        SubSettingLauncher subSettingLauncher = new SubSettingLauncher(this.mContext);
        subSettingLauncher.setDestination(ChooseLockGeneric.ChooseLockGenericFragment.class.getName());
        subSettingLauncher.setTitleRes(R.string.lock_settings_picker_title_profile);
        subSettingLauncher.setSourceMetricsCategory(this.mHost.getMetricsCategory());
        subSettingLauncher.setArguments(bundle);
        subSettingLauncher.launch();
        return true;
    }

    @Override // com.android.car.developeroptions.security.ChangeScreenLockPreferenceController, com.android.settingslib.core.AbstractPreferenceController
    public void updateState(Preference preference) {
        updateSummary(preference, this.mProfileChallengeUserId);
        if (!this.mLockPatternUtils.isSeparateProfileChallengeEnabled(this.mProfileChallengeUserId)) {
            this.mPreference.setSummary(this.mContext.getString(R.string.lock_settings_profile_unified_summary));
            this.mPreference.setEnabled(false);
            return;
        }
        disableIfPasswordQualityManaged(this.mProfileChallengeUserId);
    }
}
