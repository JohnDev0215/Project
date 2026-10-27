package com.android.car.developeroptions.security;

import android.app.admin.DevicePolicyManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.UserHandle;
import android.os.UserManager;
import androidx.preference.Preference;
import androidx.preference.PreferenceScreen;
import com.android.car.developeroptions.R;
import com.android.car.developeroptions.Utils;
import com.android.car.developeroptions.core.PreferenceControllerMixin;
import com.android.car.developeroptions.core.SubSettingLauncher;
import com.android.car.developeroptions.overlay.FeatureFactory;
import com.android.car.developeroptions.password.ChooseLockGeneric;
import com.android.car.developeroptions.password.ChooseLockSettingsHelper;
import com.android.internal.widget.LockPatternUtils;
import com.android.internal.widget.LockscreenCredential;
import com.android.settingslib.RestrictedLockUtilsInternal;
import com.android.settingslib.RestrictedSwitchPreference;
import com.android.settingslib.core.AbstractPreferenceController;

/* JADX INFO: loaded from: classes.dex */
public class LockUnificationPreferenceController extends AbstractPreferenceController implements PreferenceControllerMixin, Preference.OnPreferenceChangeListener {
    private static final int MY_USER_ID = UserHandle.myUserId();
    private LockscreenCredential mCurrentDevicePassword;
    private LockscreenCredential mCurrentProfilePassword;
    private final DevicePolicyManager mDpm;
    private final SecuritySettings mHost;
    private boolean mKeepDeviceLock;
    private final LockPatternUtils mLockPatternUtils;
    private final int mProfileUserId;
    private final UserManager mUm;
    private RestrictedSwitchPreference mUnifyProfile;

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public String getPreferenceKey() {
        return "unification";
    }

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public void displayPreference(PreferenceScreen preferenceScreen) {
        super.displayPreference(preferenceScreen);
        this.mUnifyProfile = (RestrictedSwitchPreference) preferenceScreen.findPreference("unification");
    }

    public LockUnificationPreferenceController(Context context, SecuritySettings securitySettings) {
        super(context);
        this.mHost = securitySettings;
        this.mUm = (UserManager) context.getSystemService(UserManager.class);
        this.mDpm = (DevicePolicyManager) context.getSystemService(DevicePolicyManager.class);
        this.mLockPatternUtils = FeatureFactory.getFactory(context).getSecurityFeatureProvider().getLockPatternUtils(context);
        this.mProfileUserId = Utils.getManagedProfileId(this.mUm, MY_USER_ID);
        this.mCurrentDevicePassword = LockscreenCredential.createNone();
        this.mCurrentProfilePassword = LockscreenCredential.createNone();
    }

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public boolean isAvailable() {
        int i = this.mProfileUserId;
        return i != -10000 && this.mLockPatternUtils.isSeparateProfileChallengeAllowed(i);
    }

    @Override // androidx.preference.Preference.OnPreferenceChangeListener
    public boolean onPreferenceChange(Preference preference, Object obj) {
        if (Utils.startQuietModeDialogIfNecessary(this.mContext, this.mUm, this.mProfileUserId)) {
            return false;
        }
        if (((Boolean) obj).booleanValue()) {
            boolean z = this.mLockPatternUtils.getKeyguardStoredPasswordQuality(this.mProfileUserId) < 65536 || !this.mDpm.isProfileActivePasswordSufficientForParent(this.mProfileUserId);
            this.mKeepDeviceLock = z;
            UnificationConfirmationDialog.newInstance(!z).show(this.mHost);
        } else {
            if (!new ChooseLockSettingsHelper(this.mHost.getActivity(), this.mHost).launchConfirmationActivity(130, this.mContext.getString(R.string.unlock_set_unlock_launch_picker_title), true, MY_USER_ID)) {
                ununifyLocks();
            }
        }
        return true;
    }

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public void updateState(Preference preference) {
        if (this.mUnifyProfile != null) {
            boolean zIsSeparateProfileChallengeEnabled = this.mLockPatternUtils.isSeparateProfileChallengeEnabled(this.mProfileUserId);
            this.mUnifyProfile.setChecked(!zIsSeparateProfileChallengeEnabled);
            if (zIsSeparateProfileChallengeEnabled) {
                this.mUnifyProfile.setDisabledByAdmin(RestrictedLockUtilsInternal.checkIfRestrictionEnforced(this.mContext, "no_unified_password", this.mProfileUserId));
            }
        }
    }

    public boolean handleActivityResult(int i, int i2, Intent intent) {
        if (i == 130 && i2 == -1) {
            ununifyLocks();
            return true;
        }
        if (i == 128 && i2 == -1) {
            this.mCurrentDevicePassword = intent.getParcelableExtra("password");
            launchConfirmProfileLock();
            return true;
        }
        if (i != 129 || i2 != -1) {
            return false;
        }
        this.mCurrentProfilePassword = intent.getParcelableExtra("password");
        unifyLocks();
        return true;
    }

    private void ununifyLocks() {
        Bundle bundle = new Bundle();
        bundle.putInt("android.intent.extra.USER_ID", this.mProfileUserId);
        SubSettingLauncher subSettingLauncher = new SubSettingLauncher(this.mContext);
        subSettingLauncher.setDestination(ChooseLockGeneric.ChooseLockGenericFragment.class.getName());
        subSettingLauncher.setTitleRes(R.string.lock_settings_picker_title_profile);
        subSettingLauncher.setSourceMetricsCategory(this.mHost.getMetricsCategory());
        subSettingLauncher.setArguments(bundle);
        subSettingLauncher.launch();
    }

    private void launchConfirmDeviceAndProfileLock() {
        if (new ChooseLockSettingsHelper(this.mHost.getActivity(), this.mHost).launchConfirmationActivity(128, this.mContext.getString(R.string.unlock_set_unlock_launch_picker_title), true, MY_USER_ID)) {
            return;
        }
        launchConfirmProfileLock();
    }

    private void launchConfirmProfileLock() {
        if (new ChooseLockSettingsHelper(this.mHost.getActivity(), this.mHost).launchConfirmationActivity(129, this.mContext.getString(R.string.unlock_set_unlock_launch_picker_title_profile), true, this.mProfileUserId)) {
            return;
        }
        unifyLocks();
    }

    void startUnification() {
        if (this.mKeepDeviceLock) {
            launchConfirmProfileLock();
        } else {
            launchConfirmDeviceAndProfileLock();
        }
    }

    private void unifyLocks() {
        if (this.mKeepDeviceLock) {
            unifyKeepingDeviceLock();
            promptForNewDeviceLock();
        } else {
            unifyKeepingWorkLock();
        }
        this.mCurrentDevicePassword = null;
        this.mCurrentProfilePassword = null;
    }

    private void unifyKeepingWorkLock() {
        this.mLockPatternUtils.setLockCredential(this.mCurrentProfilePassword, this.mCurrentDevicePassword, MY_USER_ID);
        this.mLockPatternUtils.setSeparateProfileChallengeEnabled(this.mProfileUserId, false, this.mCurrentProfilePassword);
        this.mLockPatternUtils.setVisiblePatternEnabled(this.mLockPatternUtils.isVisiblePatternEnabled(this.mProfileUserId), MY_USER_ID);
    }

    private void unifyKeepingDeviceLock() {
        this.mLockPatternUtils.setSeparateProfileChallengeEnabled(this.mProfileUserId, false, this.mCurrentProfilePassword);
    }

    private void promptForNewDeviceLock() {
        SubSettingLauncher subSettingLauncher = new SubSettingLauncher(this.mContext);
        subSettingLauncher.setDestination(ChooseLockGeneric.ChooseLockGenericFragment.class.getName());
        subSettingLauncher.setTitleRes(R.string.lock_settings_picker_title);
        subSettingLauncher.setSourceMetricsCategory(this.mHost.getMetricsCategory());
        subSettingLauncher.launch();
    }
}
