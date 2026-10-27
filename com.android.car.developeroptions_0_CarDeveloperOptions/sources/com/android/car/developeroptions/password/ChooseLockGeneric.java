package com.android.car.developeroptions.password;

import android.app.Dialog;
import android.app.admin.DevicePolicyManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.UserInfo;
import android.hardware.face.Face;
import android.hardware.face.FaceManager;
import android.hardware.fingerprint.Fingerprint;
import android.hardware.fingerprint.FingerprintManager;
import android.os.Bundle;
import android.os.Parcelable;
import android.os.UserHandle;
import android.os.UserManager;
import android.os.storage.StorageManager;
import android.text.TextUtils;
import android.util.EventLog;
import android.util.Log;
import android.view.accessibility.AccessibilityManager;
import android.widget.TextView;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.preference.Preference;
import androidx.preference.PreferenceScreen;
import com.android.car.developeroptions.EncryptionInterstitial;
import com.android.car.developeroptions.R;
import com.android.car.developeroptions.SettingsActivity;
import com.android.car.developeroptions.SettingsPreferenceFragment;
import com.android.car.developeroptions.Utils;
import com.android.car.developeroptions.biometrics.fingerprint.FingerprintEnrollFindSensor;
import com.android.car.developeroptions.core.instrumentation.InstrumentedDialogFragment;
import com.android.car.developeroptions.intelligence.LogProto$SettingsLog;
import com.android.car.developeroptions.password.ChooseLockPassword;
import com.android.car.developeroptions.password.ChooseLockPattern;
import com.android.internal.widget.LockPatternUtils;
import com.android.internal.widget.LockscreenCredential;
import com.android.settingslib.RestrictedLockUtils;
import com.android.settingslib.RestrictedLockUtilsInternal;
import com.android.settingslib.RestrictedPreference;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class ChooseLockGeneric extends SettingsActivity {

    public static class InternalActivity extends ChooseLockGeneric {
    }

    @Override // com.android.car.developeroptions.SettingsActivity, android.app.Activity
    public Intent getIntent() {
        Intent intent = new Intent(super.getIntent());
        intent.putExtra(":settings:show_fragment", getFragmentClass().getName());
        return intent;
    }

    @Override // com.android.car.developeroptions.SettingsActivity
    protected boolean isValidFragment(String str) {
        return ChooseLockGenericFragment.class.getName().equals(str);
    }

    Class<? extends Fragment> getFragmentClass() {
        return ChooseLockGenericFragment.class;
    }

    public static class ChooseLockGenericFragment extends SettingsPreferenceFragment {
        static final int CHOOSE_LOCK_BEFORE_FINGERPRINT_REQUEST = 103;
        static final int CHOOSE_LOCK_REQUEST = 102;
        static final int CONFIRM_EXISTING_REQUEST = 100;
        static final int ENABLE_ENCRYPTION_REQUEST = 101;
        static final int SKIP_FINGERPRINT_REQUEST = 104;
        private long mChallenge;
        private ChooseLockSettingsHelper mChooseLockSettingsHelper;
        private ChooseLockGenericController mController;
        private DevicePolicyManager mDPM;
        private FaceManager mFaceManager;
        private FingerprintManager mFingerprintManager;
        private LockPatternUtils mLockPatternUtils;
        private ManagedLockPasswordProvider mManagedPasswordProvider;
        private int mRequestedMinComplexity;
        private int mUserId;
        private UserManager mUserManager;
        private LockscreenCredential mUserPassword;
        private boolean mHasChallenge = false;
        private boolean mPasswordConfirmed = false;
        private boolean mWaitingForConfirmation = false;
        private boolean mForChangeCredRequiredForBoot = false;
        private boolean mIsSetNewPassword = false;
        private String mCallerAppName = null;
        protected boolean mForFingerprint = false;
        protected boolean mForFace = false;

        protected boolean canRunBeforeDeviceProvisioned() {
            return false;
        }

        @Override // com.android.car.developeroptions.support.actionbar.HelpResourceProvider
        public int getHelpResource() {
            return R.string.help_url_choose_lockscreen;
        }

        @Override // com.android.settingslib.core.instrumentation.Instrumentable
        public int getMetricsCategory() {
            return 27;
        }

        @Override // com.android.car.developeroptions.SettingsPreferenceFragment, com.android.settingslib.core.lifecycle.ObservablePreferenceFragment, androidx.preference.PreferenceFragmentCompat, androidx.fragment.app.Fragment
        public void onCreate(Bundle bundle) {
            super.onCreate(bundle);
            FragmentActivity activity = getActivity();
            if (!Utils.isDeviceProvisioned(activity) && !canRunBeforeDeviceProvisioned()) {
                Log.i("ChooseLockGenericFragment", "Refusing to start because device is not provisioned");
                activity.finish();
                return;
            }
            String action = getActivity().getIntent().getAction();
            this.mFingerprintManager = Utils.getFingerprintManagerOrNull(getActivity());
            this.mFaceManager = Utils.getFaceManagerOrNull(getActivity());
            this.mDPM = (DevicePolicyManager) getSystemService("device_policy");
            this.mChooseLockSettingsHelper = new ChooseLockSettingsHelper(getActivity());
            this.mLockPatternUtils = new LockPatternUtils(getActivity());
            this.mIsSetNewPassword = "android.app.action.SET_NEW_PARENT_PROFILE_PASSWORD".equals(action) || "android.app.action.SET_NEW_PASSWORD".equals(action);
            boolean booleanExtra = getActivity().getIntent().getBooleanExtra("confirm_credentials", true);
            if (getActivity() instanceof InternalActivity) {
                this.mPasswordConfirmed = !booleanExtra;
                this.mUserPassword = getActivity().getIntent().getParcelableExtra("password");
            }
            this.mHasChallenge = getActivity().getIntent().getBooleanExtra("has_challenge", false);
            this.mChallenge = getActivity().getIntent().getLongExtra("challenge", 0L);
            this.mForFingerprint = getActivity().getIntent().getBooleanExtra("for_fingerprint", false);
            this.mForFace = getActivity().getIntent().getBooleanExtra("for_face", false);
            this.mRequestedMinComplexity = getActivity().getIntent().getIntExtra("requested_min_complexity", 0);
            this.mCallerAppName = getActivity().getIntent().getStringExtra("caller_app_name");
            this.mForChangeCredRequiredForBoot = getArguments() != null && getArguments().getBoolean("for_cred_req_boot");
            this.mUserManager = UserManager.get(getActivity());
            if (bundle != null) {
                this.mPasswordConfirmed = bundle.getBoolean("password_confirmed");
                this.mWaitingForConfirmation = bundle.getBoolean("waiting_for_confirmation");
                if (this.mUserPassword == null) {
                    this.mUserPassword = bundle.getParcelable("password");
                }
            }
            this.mUserId = Utils.getSecureTargetUser(getActivity().getActivityToken(), UserManager.get(getActivity()), getArguments(), getActivity().getIntent().getExtras()).getIdentifier();
            this.mController = new ChooseLockGenericController(getContext(), this.mUserId, this.mRequestedMinComplexity, this.mLockPatternUtils);
            if ("android.app.action.SET_NEW_PASSWORD".equals(action) && UserManager.get(getActivity()).isManagedProfile(this.mUserId) && this.mLockPatternUtils.isSeparateProfileChallengeEnabled(this.mUserId)) {
                getActivity().setTitle(R.string.lock_settings_picker_title_profile);
            }
            this.mManagedPasswordProvider = ManagedLockPasswordProvider.get(getActivity(), this.mUserId);
            if (this.mPasswordConfirmed) {
                updatePreferencesOrFinish(bundle != null);
                if (this.mForChangeCredRequiredForBoot) {
                    maybeEnableEncryption(this.mLockPatternUtils.getKeyguardStoredPasswordQuality(this.mUserId), false);
                }
            } else if (!this.mWaitingForConfirmation) {
                ChooseLockSettingsHelper chooseLockSettingsHelper = new ChooseLockSettingsHelper(getActivity(), this);
                if (((UserManager.get(getActivity()).isManagedProfile(this.mUserId) && !this.mLockPatternUtils.isSeparateProfileChallengeEnabled(this.mUserId)) && !this.mIsSetNewPassword) || !chooseLockSettingsHelper.launchConfirmationActivity(CONFIRM_EXISTING_REQUEST, getString(R.string.unlock_set_unlock_launch_picker_title), true, this.mUserId)) {
                    this.mPasswordConfirmed = true;
                    updatePreferencesOrFinish(bundle != null);
                } else {
                    this.mWaitingForConfirmation = true;
                }
            }
            addHeaderView();
        }

        protected Class<? extends InternalActivity> getInternalActivityClass() {
            return InternalActivity.class;
        }

        protected void addHeaderView() {
            if (this.mForFingerprint) {
                setHeaderView(R.layout.choose_lock_generic_fingerprint_header);
                if (this.mIsSetNewPassword) {
                    ((TextView) getHeaderView().findViewById(R.id.fingerprint_header_description)).setText(R.string.fingerprint_unlock_title);
                    return;
                }
                return;
            }
            if (this.mForFace) {
                setHeaderView(R.layout.choose_lock_generic_face_header);
                if (this.mIsSetNewPassword) {
                    ((TextView) getHeaderView().findViewById(R.id.face_header_description)).setText(R.string.face_unlock_title);
                }
            }
        }

        @Override // androidx.preference.PreferenceFragmentCompat, androidx.preference.PreferenceManager.OnPreferenceTreeClickListener
        public boolean onPreferenceTreeClick(Preference preference) {
            String key = preference.getKey();
            if (!isUnlockMethodSecure(key) && this.mLockPatternUtils.isSecure(this.mUserId)) {
                showFactoryResetProtectionWarningDialog(key);
                return true;
            }
            if ("unlock_skip_fingerprint".equals(key) || "unlock_skip_face".equals(key)) {
                Intent intent = new Intent(getActivity(), getInternalActivityClass());
                intent.setAction(getIntent().getAction());
                intent.putExtra("android.intent.extra.USER_ID", this.mUserId);
                intent.putExtra("confirm_credentials", !this.mPasswordConfirmed);
                intent.putExtra("requested_min_complexity", this.mRequestedMinComplexity);
                intent.putExtra("caller_app_name", this.mCallerAppName);
                LockscreenCredential lockscreenCredential = this.mUserPassword;
                if (lockscreenCredential != null) {
                    intent.putExtra("password", (Parcelable) lockscreenCredential);
                }
                startActivityForResult(intent, SKIP_FINGERPRINT_REQUEST);
                return true;
            }
            return setUnlockMethod(key);
        }

        private void maybeEnableEncryption(int i, boolean z) {
            DevicePolicyManager devicePolicyManager = (DevicePolicyManager) getSystemService("device_policy");
            if (UserManager.get(getActivity()).isAdminUser() && this.mUserId == UserHandle.myUserId() && LockPatternUtils.isDeviceEncryptionEnabled() && !LockPatternUtils.isFileEncryptionEnabled() && !devicePolicyManager.getDoNotAskCredentialsOnBoot()) {
                Intent intentForUnlockMethod = getIntentForUnlockMethod(i);
                intentForUnlockMethod.putExtra("for_cred_req_boot", this.mForChangeCredRequiredForBoot);
                Intent encryptionInterstitialIntent = getEncryptionInterstitialIntent(getActivity(), i, this.mLockPatternUtils.isCredentialRequiredToDecrypt(!AccessibilityManager.getInstance(r0).isEnabled()), intentForUnlockMethod);
                encryptionInterstitialIntent.putExtra("for_fingerprint", this.mForFingerprint);
                encryptionInterstitialIntent.putExtra("for_face", this.mForFace);
                startActivityForResult(encryptionInterstitialIntent, (this.mIsSetNewPassword && this.mHasChallenge) ? CHOOSE_LOCK_BEFORE_FINGERPRINT_REQUEST : ENABLE_ENCRYPTION_REQUEST);
                return;
            }
            if (this.mForChangeCredRequiredForBoot) {
                finish();
            } else {
                updateUnlockMethodAndFinish(i, z, false);
            }
        }

        @Override // androidx.fragment.app.Fragment
        public void onActivityResult(int i, int i2, Intent intent) {
            super.onActivityResult(i, i2, intent);
            this.mWaitingForConfirmation = false;
            if (i == CONFIRM_EXISTING_REQUEST && i2 == -1) {
                this.mPasswordConfirmed = true;
                this.mUserPassword = intent != null ? (LockscreenCredential) intent.getParcelableExtra("password") : null;
                updatePreferencesOrFinish(false);
                if (this.mForChangeCredRequiredForBoot) {
                    LockscreenCredential lockscreenCredential = this.mUserPassword;
                    if (lockscreenCredential != null && !lockscreenCredential.isNone()) {
                        maybeEnableEncryption(this.mLockPatternUtils.getKeyguardStoredPasswordQuality(this.mUserId), false);
                    } else {
                        finish();
                    }
                }
            } else if (i == CHOOSE_LOCK_REQUEST || i == ENABLE_ENCRYPTION_REQUEST) {
                if (i2 != 0 || this.mForChangeCredRequiredForBoot) {
                    getActivity().setResult(i2, intent);
                    finish();
                } else if (getIntent().getIntExtra("lockscreen.password_type", -1) != -1) {
                    getActivity().setResult(0, intent);
                    finish();
                }
            } else if (i == CHOOSE_LOCK_BEFORE_FINGERPRINT_REQUEST && i2 == 1) {
                Intent findSensorIntent = getFindSensorIntent(getActivity());
                if (intent != null) {
                    findSensorIntent.putExtras(intent.getExtras());
                }
                findSensorIntent.putExtra("android.intent.extra.USER_ID", this.mUserId);
                startActivity(findSensorIntent);
                finish();
            } else if (i == SKIP_FINGERPRINT_REQUEST) {
                if (i2 != 0) {
                    FragmentActivity activity = getActivity();
                    if (i2 == 1) {
                        i2 = -1;
                    }
                    activity.setResult(i2, intent);
                    finish();
                }
            } else {
                if (i == 501) {
                    return;
                }
                getActivity().setResult(0);
                finish();
            }
            if (i == 0 && this.mForChangeCredRequiredForBoot) {
                finish();
            }
        }

        protected Intent getFindSensorIntent(Context context) {
            return new Intent(context, (Class<?>) FingerprintEnrollFindSensor.class);
        }

        @Override // com.android.car.developeroptions.SettingsPreferenceFragment, com.android.settingslib.core.lifecycle.ObservablePreferenceFragment, androidx.preference.PreferenceFragmentCompat, androidx.fragment.app.Fragment
        public void onSaveInstanceState(Bundle bundle) {
            super.onSaveInstanceState(bundle);
            bundle.putBoolean("password_confirmed", this.mPasswordConfirmed);
            bundle.putBoolean("waiting_for_confirmation", this.mWaitingForConfirmation);
            LockscreenCredential lockscreenCredential = this.mUserPassword;
            if (lockscreenCredential != null) {
                bundle.putParcelable("password", lockscreenCredential);
            }
        }

        void updatePreferencesOrFinish(boolean z) {
            int intExtra;
            Intent intent = getActivity().getIntent();
            if (StorageManager.isFileEncryptedNativeOrEmulated()) {
                intExtra = intent.getIntExtra("lockscreen.password_type", -1);
            } else {
                Log.i("ChooseLockGenericFragment", "Ignoring PASSWORD_TYPE_KEY because device is not file encrypted");
                intExtra = -1;
            }
            if (intExtra != -1) {
                if (z) {
                    return;
                }
                updateUnlockMethodAndFinish(intExtra, false, true);
                return;
            }
            int iUpgradeQuality = this.mController.upgradeQuality(intent.getIntExtra("minimum_quality", -1));
            boolean booleanExtra = intent.getBooleanExtra("hide_disabled_prefs", false);
            PreferenceScreen preferenceScreen = getPreferenceScreen();
            if (preferenceScreen != null) {
                preferenceScreen.removeAll();
            }
            addPreferences();
            disableUnusablePreferences(iUpgradeQuality, booleanExtra);
            updatePreferenceText();
            updateCurrentPreference();
            updatePreferenceSummaryIfNeeded();
        }

        protected void addPreferences() {
            addPreferencesFromResource(R.xml.security_settings_picker);
            Preference preferenceFindPreference = findPreference("lock_settings_footer");
            if (!TextUtils.isEmpty(this.mCallerAppName)) {
                preferenceFindPreference.setVisible(true);
                preferenceFindPreference.setTitle(getFooterString());
            } else {
                preferenceFindPreference.setVisible(false);
            }
            findPreference(ScreenLockType.NONE.preferenceKey).setViewId(R.id.lock_none);
            findPreference("unlock_skip_fingerprint").setViewId(R.id.lock_none);
            findPreference("unlock_skip_face").setViewId(R.id.lock_none);
            findPreference(ScreenLockType.PIN.preferenceKey).setViewId(R.id.lock_pin);
            findPreference(ScreenLockType.PASSWORD.preferenceKey).setViewId(R.id.lock_password);
        }

        private String getFooterString() {
            int i = this.mRequestedMinComplexity;
            return getResources().getString(i != 65536 ? i != 196608 ? i != 327680 ? R.string.unlock_footer_none_complexity_requested : R.string.unlock_footer_high_complexity_requested : R.string.unlock_footer_medium_complexity_requested : R.string.unlock_footer_low_complexity_requested, this.mCallerAppName);
        }

        private void updatePreferenceText() {
            if (this.mForFingerprint) {
                setPreferenceTitle(ScreenLockType.PATTERN, R.string.fingerprint_unlock_set_unlock_pattern);
                setPreferenceTitle(ScreenLockType.PIN, R.string.fingerprint_unlock_set_unlock_pin);
                setPreferenceTitle(ScreenLockType.PASSWORD, R.string.fingerprint_unlock_set_unlock_password);
            } else if (this.mForFace) {
                setPreferenceTitle(ScreenLockType.PATTERN, R.string.face_unlock_set_unlock_pattern);
                setPreferenceTitle(ScreenLockType.PIN, R.string.face_unlock_set_unlock_pin);
                setPreferenceTitle(ScreenLockType.PASSWORD, R.string.face_unlock_set_unlock_password);
            }
            if (this.mManagedPasswordProvider.isSettingManagedPasswordSupported()) {
                setPreferenceTitle(ScreenLockType.MANAGED, this.mManagedPasswordProvider.getPickerOptionTitle(this.mForFingerprint));
            } else {
                removePreference(ScreenLockType.MANAGED.preferenceKey);
            }
            if (!this.mForFingerprint || !this.mIsSetNewPassword) {
                removePreference("unlock_skip_fingerprint");
            }
            if (this.mForFace && this.mIsSetNewPassword) {
                return;
            }
            removePreference("unlock_skip_face");
        }

        private void setPreferenceTitle(ScreenLockType screenLockType, int i) {
            Preference preferenceFindPreference = findPreference(screenLockType.preferenceKey);
            if (preferenceFindPreference != null) {
                preferenceFindPreference.setTitle(i);
            }
        }

        private void setPreferenceTitle(ScreenLockType screenLockType, CharSequence charSequence) {
            Preference preferenceFindPreference = findPreference(screenLockType.preferenceKey);
            if (preferenceFindPreference != null) {
                preferenceFindPreference.setTitle(charSequence);
            }
        }

        private void setPreferenceSummary(ScreenLockType screenLockType, int i) {
            Preference preferenceFindPreference = findPreference(screenLockType.preferenceKey);
            if (preferenceFindPreference != null) {
                preferenceFindPreference.setSummary(i);
            }
        }

        private void updateCurrentPreference() {
            Preference preferenceFindPreference = findPreference(getKeyForCurrent());
            if (preferenceFindPreference != null) {
                preferenceFindPreference.setSummary(R.string.current_screen_lock);
            }
        }

        private String getKeyForCurrent() {
            int credentialOwnerProfile = UserManager.get(getContext()).getCredentialOwnerProfile(this.mUserId);
            if (this.mLockPatternUtils.isLockScreenDisabled(credentialOwnerProfile)) {
                return ScreenLockType.NONE.preferenceKey;
            }
            ScreenLockType screenLockTypeFromQuality = ScreenLockType.fromQuality(this.mLockPatternUtils.getKeyguardStoredPasswordQuality(credentialOwnerProfile));
            if (screenLockTypeFromQuality != null) {
                return screenLockTypeFromQuality.preferenceKey;
            }
            return null;
        }

        protected void disableUnusablePreferences(int i, boolean z) {
            disableUnusablePreferencesImpl(i, z);
        }

        protected void disableUnusablePreferencesImpl(int i, boolean z) {
            PreferenceScreen preferenceScreen = getPreferenceScreen();
            int passwordQuality = this.mDPM.getPasswordQuality(null, this.mUserId);
            RestrictedLockUtils.EnforcedAdmin enforcedAdminCheckIfPasswordQualityIsSet = RestrictedLockUtilsInternal.checkIfPasswordQualityIsSet(getActivity(), this.mUserId);
            for (ScreenLockType screenLockType : ScreenLockType.values()) {
                Preference preferenceFindPreference = findPreference(screenLockType.preferenceKey);
                if (preferenceFindPreference instanceof RestrictedPreference) {
                    boolean zIsScreenLockVisible = this.mController.isScreenLockVisible(screenLockType);
                    boolean zIsScreenLockEnabled = this.mController.isScreenLockEnabled(screenLockType, i);
                    boolean zIsScreenLockDisabledByAdmin = this.mController.isScreenLockDisabledByAdmin(screenLockType, passwordQuality);
                    if (z) {
                        zIsScreenLockVisible = zIsScreenLockVisible && zIsScreenLockEnabled;
                    }
                    if (!zIsScreenLockVisible) {
                        preferenceScreen.removePreference(preferenceFindPreference);
                    } else if (zIsScreenLockDisabledByAdmin && enforcedAdminCheckIfPasswordQualityIsSet != null) {
                        ((RestrictedPreference) preferenceFindPreference).setDisabledByAdmin(enforcedAdminCheckIfPasswordQualityIsSet);
                    } else if (!zIsScreenLockEnabled) {
                        ((RestrictedPreference) preferenceFindPreference).setDisabledByAdmin(null);
                        preferenceFindPreference.setSummary(R.string.unlock_set_unlock_disabled_summary);
                        preferenceFindPreference.setEnabled(false);
                    } else {
                        ((RestrictedPreference) preferenceFindPreference).setDisabledByAdmin(null);
                    }
                }
            }
        }

        private void updatePreferenceSummaryIfNeeded() {
            if (!StorageManager.isBlockEncrypted() || StorageManager.isNonDefaultBlockEncrypted() || AccessibilityManager.getInstance(getActivity()).getEnabledAccessibilityServiceList(-1).isEmpty()) {
                return;
            }
            setPreferenceSummary(ScreenLockType.PATTERN, R.string.secure_lock_encryption_warning);
            setPreferenceSummary(ScreenLockType.PIN, R.string.secure_lock_encryption_warning);
            setPreferenceSummary(ScreenLockType.PASSWORD, R.string.secure_lock_encryption_warning);
            setPreferenceSummary(ScreenLockType.MANAGED, R.string.secure_lock_encryption_warning);
        }

        protected Intent getLockManagedPasswordIntent(LockscreenCredential lockscreenCredential) {
            return this.mManagedPasswordProvider.createIntent(false, lockscreenCredential);
        }

        protected Intent getLockPasswordIntent(int i) {
            ChooseLockPassword.IntentBuilder intentBuilder = new ChooseLockPassword.IntentBuilder(getContext());
            intentBuilder.setPasswordQuality(i);
            intentBuilder.setRequestedMinComplexity(this.mRequestedMinComplexity);
            intentBuilder.setForFingerprint(this.mForFingerprint);
            intentBuilder.setForFace(this.mForFace);
            intentBuilder.setUserId(this.mUserId);
            if (this.mHasChallenge) {
                intentBuilder.setChallenge(this.mChallenge);
            }
            LockscreenCredential lockscreenCredential = this.mUserPassword;
            if (lockscreenCredential != null) {
                intentBuilder.setPassword(lockscreenCredential);
            }
            return intentBuilder.build();
        }

        protected Intent getLockPatternIntent() {
            ChooseLockPattern.IntentBuilder intentBuilder = new ChooseLockPattern.IntentBuilder(getContext());
            intentBuilder.setForFingerprint(this.mForFingerprint);
            intentBuilder.setForFace(this.mForFace);
            intentBuilder.setUserId(this.mUserId);
            if (this.mHasChallenge) {
                intentBuilder.setChallenge(this.mChallenge);
            }
            LockscreenCredential lockscreenCredential = this.mUserPassword;
            if (lockscreenCredential != null) {
                intentBuilder.setPattern(lockscreenCredential);
            }
            return intentBuilder.build();
        }

        protected Intent getEncryptionInterstitialIntent(Context context, int i, boolean z, Intent intent) {
            return EncryptionInterstitial.createStartIntent(context, i, z, intent);
        }

        private class RemovalTracker {
            boolean mFaceDone;
            boolean mFingerprintDone;

            private RemovalTracker() {
            }

            /* synthetic */ RemovalTracker(ChooseLockGenericFragment chooseLockGenericFragment, AnonymousClass1 anonymousClass1) {
                this();
            }

            void onFingerprintDone() {
                this.mFingerprintDone = true;
                if (1 == 0 || !this.mFaceDone) {
                    return;
                }
                ChooseLockGenericFragment.this.finish();
            }

            void onFaceDone() {
                this.mFaceDone = true;
                if (!this.mFingerprintDone || 1 == 0) {
                    return;
                }
                ChooseLockGenericFragment.this.finish();
            }
        }

        void updateUnlockMethodAndFinish(int i, boolean z, boolean z2) {
            if (!this.mPasswordConfirmed) {
                throw new IllegalStateException("Tried to update password without confirming it");
            }
            int iUpgradeQuality = this.mController.upgradeQuality(i);
            Intent intentForUnlockMethod = getIntentForUnlockMethod(iUpgradeQuality);
            if (intentForUnlockMethod != null) {
                if (getIntent().getBooleanExtra("show_options_button", false)) {
                    intentForUnlockMethod.putExtra("show_options_button", z2);
                }
                intentForUnlockMethod.putExtra("choose_lock_generic_extras", getIntent().getExtras());
                startActivityForResult(intentForUnlockMethod, (this.mIsSetNewPassword && this.mHasChallenge) ? CHOOSE_LOCK_BEFORE_FINGERPRINT_REQUEST : CHOOSE_LOCK_REQUEST);
                return;
            }
            if (iUpgradeQuality == 0) {
                this.mChooseLockSettingsHelper.utils().setLockCredential(LockscreenCredential.createNone(), this.mUserPassword, this.mUserId);
                this.mChooseLockSettingsHelper.utils().setLockScreenDisabled(z, this.mUserId);
                getActivity().setResult(-1);
                removeAllBiometricsForUserAndFinish(this.mUserId);
                return;
            }
            removeAllBiometricsForUserAndFinish(this.mUserId);
        }

        private void removeAllBiometricsForUserAndFinish(int i) {
            RemovalTracker removalTracker = new RemovalTracker(this, null);
            removeAllFingerprintForUserAndFinish(i, removalTracker);
            removeAllFaceForUserAndFinish(i, removalTracker);
        }

        private Intent getIntentForUnlockMethod(int i) {
            if (i >= 524288) {
                return getLockManagedPasswordIntent(this.mUserPassword);
            }
            if (i >= 131072) {
                return getLockPasswordIntent(i);
            }
            if (i == 65536) {
                return getLockPatternIntent();
            }
            return null;
        }

        private void removeAllFingerprintForUserAndFinish(final int i, final RemovalTracker removalTracker) {
            FingerprintManager fingerprintManager = this.mFingerprintManager;
            if (fingerprintManager != null && fingerprintManager.isHardwareDetected()) {
                if (this.mFingerprintManager.hasEnrolledFingerprints(i)) {
                    this.mFingerprintManager.setActiveUser(i);
                    this.mFingerprintManager.remove(new Fingerprint((CharSequence) null, i, 0, 0L), i, new FingerprintManager.RemovalCallback() { // from class: com.android.car.developeroptions.password.ChooseLockGeneric.ChooseLockGenericFragment.1
                        public void onRemovalError(Fingerprint fingerprint, int i2, CharSequence charSequence) {
                            Log.e("ChooseLockGenericFragment", String.format("Can't remove fingerprint %d in group %d. Reason: %s", Integer.valueOf(fingerprint.getBiometricId()), Integer.valueOf(fingerprint.getGroupId()), charSequence));
                        }

                        public void onRemovalSucceeded(Fingerprint fingerprint, int i2) {
                            if (i2 == 0) {
                                ChooseLockGenericFragment.this.removeManagedProfileFingerprintsAndFinishIfNecessary(i, removalTracker);
                            }
                        }
                    });
                    return;
                }
                removeManagedProfileFingerprintsAndFinishIfNecessary(i, removalTracker);
                return;
            }
            removalTracker.onFingerprintDone();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void removeManagedProfileFingerprintsAndFinishIfNecessary(int i, RemovalTracker removalTracker) {
            FingerprintManager fingerprintManager = this.mFingerprintManager;
            if (fingerprintManager != null && fingerprintManager.isHardwareDetected()) {
                this.mFingerprintManager.setActiveUser(UserHandle.myUserId());
            }
            boolean z = false;
            if (!this.mUserManager.getUserInfo(i).isManagedProfile()) {
                List profiles = this.mUserManager.getProfiles(i);
                int size = profiles.size();
                int i2 = 0;
                while (true) {
                    if (i2 >= size) {
                        break;
                    }
                    UserInfo userInfo = (UserInfo) profiles.get(i2);
                    if (userInfo.isManagedProfile() && !this.mLockPatternUtils.isSeparateProfileChallengeEnabled(userInfo.id)) {
                        removeAllFingerprintForUserAndFinish(userInfo.id, removalTracker);
                        z = true;
                        break;
                    }
                    i2++;
                }
            }
            if (z) {
                return;
            }
            removalTracker.onFingerprintDone();
        }

        private void removeAllFaceForUserAndFinish(final int i, final RemovalTracker removalTracker) {
            FaceManager faceManager = this.mFaceManager;
            if (faceManager != null && faceManager.isHardwareDetected()) {
                if (this.mFaceManager.hasEnrolledTemplates(i)) {
                    this.mFaceManager.setActiveUser(i);
                    this.mFaceManager.remove(new Face((CharSequence) null, 0, 0L), i, new FaceManager.RemovalCallback() { // from class: com.android.car.developeroptions.password.ChooseLockGeneric.ChooseLockGenericFragment.2
                        public void onRemovalError(Face face, int i2, CharSequence charSequence) {
                            Log.e("ChooseLockGenericFragment", String.format("Can't remove face %d. Reason: %s", Integer.valueOf(face.getBiometricId()), charSequence));
                        }

                        public void onRemovalSucceeded(Face face, int i2) {
                            if (i2 == 0) {
                                ChooseLockGenericFragment.this.removeManagedProfileFacesAndFinishIfNecessary(i, removalTracker);
                            }
                        }
                    });
                    return;
                }
                removeManagedProfileFacesAndFinishIfNecessary(i, removalTracker);
                return;
            }
            removalTracker.onFaceDone();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void removeManagedProfileFacesAndFinishIfNecessary(int i, RemovalTracker removalTracker) {
            FaceManager faceManager = this.mFaceManager;
            if (faceManager != null && faceManager.isHardwareDetected()) {
                this.mFaceManager.setActiveUser(UserHandle.myUserId());
            }
            boolean z = false;
            if (!this.mUserManager.getUserInfo(i).isManagedProfile()) {
                List profiles = this.mUserManager.getProfiles(i);
                int size = profiles.size();
                int i2 = 0;
                while (true) {
                    if (i2 >= size) {
                        break;
                    }
                    UserInfo userInfo = (UserInfo) profiles.get(i2);
                    if (userInfo.isManagedProfile() && !this.mLockPatternUtils.isSeparateProfileChallengeEnabled(userInfo.id)) {
                        removeAllFaceForUserAndFinish(userInfo.id, removalTracker);
                        z = true;
                        break;
                    }
                    i2++;
                }
            }
            if (z) {
                return;
            }
            removalTracker.onFaceDone();
        }

        @Override // com.android.settingslib.core.lifecycle.ObservablePreferenceFragment, androidx.fragment.app.Fragment
        public void onDestroy() {
            super.onDestroy();
        }

        private int getResIdForFactoryResetProtectionWarningTitle() {
            return UserManager.get(getActivity()).isManagedProfile(this.mUserId) ? R.string.unlock_disable_frp_warning_title_profile : R.string.unlock_disable_frp_warning_title;
        }

        private int getResIdForFactoryResetProtectionWarningMessage() {
            FingerprintManager fingerprintManager = this.mFingerprintManager;
            boolean zHasEnrolledFingerprints = (fingerprintManager == null || !fingerprintManager.isHardwareDetected()) ? false : this.mFingerprintManager.hasEnrolledFingerprints(this.mUserId);
            boolean zIsManagedProfile = UserManager.get(getActivity()).isManagedProfile(this.mUserId);
            int keyguardStoredPasswordQuality = this.mLockPatternUtils.getKeyguardStoredPasswordQuality(this.mUserId);
            return keyguardStoredPasswordQuality != 65536 ? (keyguardStoredPasswordQuality == 131072 || keyguardStoredPasswordQuality == 196608) ? (zHasEnrolledFingerprints && zIsManagedProfile) ? R.string.unlock_disable_frp_warning_content_pin_fingerprint_profile : (!zHasEnrolledFingerprints || zIsManagedProfile) ? zIsManagedProfile ? R.string.unlock_disable_frp_warning_content_pin_profile : R.string.unlock_disable_frp_warning_content_pin : R.string.unlock_disable_frp_warning_content_pin_fingerprint : (keyguardStoredPasswordQuality == 262144 || keyguardStoredPasswordQuality == 327680 || keyguardStoredPasswordQuality == 393216 || keyguardStoredPasswordQuality == 524288) ? (zHasEnrolledFingerprints && zIsManagedProfile) ? R.string.unlock_disable_frp_warning_content_password_fingerprint_profile : (!zHasEnrolledFingerprints || zIsManagedProfile) ? zIsManagedProfile ? R.string.unlock_disable_frp_warning_content_password_profile : R.string.unlock_disable_frp_warning_content_password : R.string.unlock_disable_frp_warning_content_password_fingerprint : (zHasEnrolledFingerprints && zIsManagedProfile) ? R.string.unlock_disable_frp_warning_content_unknown_fingerprint_profile : (!zHasEnrolledFingerprints || zIsManagedProfile) ? zIsManagedProfile ? R.string.unlock_disable_frp_warning_content_unknown_profile : R.string.unlock_disable_frp_warning_content_unknown : R.string.unlock_disable_frp_warning_content_unknown_fingerprint : (zHasEnrolledFingerprints && zIsManagedProfile) ? R.string.unlock_disable_frp_warning_content_pattern_fingerprint_profile : (!zHasEnrolledFingerprints || zIsManagedProfile) ? zIsManagedProfile ? R.string.unlock_disable_frp_warning_content_pattern_profile : R.string.unlock_disable_frp_warning_content_pattern : R.string.unlock_disable_frp_warning_content_pattern_fingerprint;
        }

        private boolean isUnlockMethodSecure(String str) {
            return (ScreenLockType.SWIPE.preferenceKey.equals(str) || ScreenLockType.NONE.preferenceKey.equals(str)) ? false : true;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean setUnlockMethod(String str) {
            EventLog.writeEvent(90200, str);
            ScreenLockType screenLockTypeFromKey = ScreenLockType.fromKey(str);
            if (screenLockTypeFromKey != null) {
                switch (AnonymousClass1.$SwitchMap$com$android$car$developeroptions$password$ScreenLockType[screenLockTypeFromKey.ordinal()]) {
                    case 1:
                    case 2:
                        updateUnlockMethodAndFinish(screenLockTypeFromKey.defaultQuality, screenLockTypeFromKey == ScreenLockType.NONE, false);
                        break;
                    case 3:
                    case 4:
                    case 5:
                    case LogProto$SettingsLog.TIMESTAMP_FIELD_NUMBER /* 6 */:
                        maybeEnableEncryption(screenLockTypeFromKey.defaultQuality, false);
                        break;
                }
                return true;
            }
            Log.e("ChooseLockGenericFragment", "Encountered unknown unlock method to set: " + str);
            return false;
        }

        private void showFactoryResetProtectionWarningDialog(String str) {
            FactoryResetProtectionWarningDialog.newInstance(getResIdForFactoryResetProtectionWarningTitle(), getResIdForFactoryResetProtectionWarningMessage(), str).show(getChildFragmentManager(), "frp_warning_dialog");
        }

        public static class FactoryResetProtectionWarningDialog extends InstrumentedDialogFragment {
            @Override // com.android.settingslib.core.instrumentation.Instrumentable
            public int getMetricsCategory() {
                return 528;
            }

            public static FactoryResetProtectionWarningDialog newInstance(int i, int i2, String str) {
                FactoryResetProtectionWarningDialog factoryResetProtectionWarningDialog = new FactoryResetProtectionWarningDialog();
                Bundle bundle = new Bundle();
                bundle.putInt("titleRes", i);
                bundle.putInt("messageRes", i2);
                bundle.putString("unlockMethodToSet", str);
                factoryResetProtectionWarningDialog.setArguments(bundle);
                return factoryResetProtectionWarningDialog;
            }

            @Override // androidx.fragment.app.DialogFragment
            public void show(FragmentManager fragmentManager, String str) {
                if (fragmentManager.findFragmentByTag(str) == null) {
                    super.show(fragmentManager, str);
                }
            }

            @Override // androidx.fragment.app.DialogFragment
            public Dialog onCreateDialog(Bundle bundle) {
                final Bundle arguments = getArguments();
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle(arguments.getInt("titleRes"));
                builder.setMessage(arguments.getInt("messageRes"));
                builder.setPositiveButton(R.string.unlock_disable_frp_warning_ok, new DialogInterface.OnClickListener() { // from class: com.android.car.developeroptions.password.-$$Lambda$ChooseLockGeneric$ChooseLockGenericFragment$FactoryResetProtectionWarningDialog$Zz1LJiXw_X2LNWLIu-iMjPvcVvY
                    @Override // android.content.DialogInterface.OnClickListener
                    public final void onClick(DialogInterface dialogInterface, int i) {
                        this.f$0.lambda$onCreateDialog$0$ChooseLockGeneric$ChooseLockGenericFragment$FactoryResetProtectionWarningDialog(arguments, dialogInterface, i);
                    }
                });
                builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() { // from class: com.android.car.developeroptions.password.-$$Lambda$ChooseLockGeneric$ChooseLockGenericFragment$FactoryResetProtectionWarningDialog$qJs0Ik_POG9CrkDY-eErSyyTTow
                    @Override // android.content.DialogInterface.OnClickListener
                    public final void onClick(DialogInterface dialogInterface, int i) {
                        this.f$0.lambda$onCreateDialog$1$ChooseLockGeneric$ChooseLockGenericFragment$FactoryResetProtectionWarningDialog(dialogInterface, i);
                    }
                });
                return builder.create();
            }

            /* JADX INFO: Access modifiers changed from: private */
            /* JADX INFO: renamed from: lambda$onCreateDialog$0, reason: merged with bridge method [inline-methods] */
            public /* synthetic */ void lambda$onCreateDialog$0$ChooseLockGeneric$ChooseLockGenericFragment$FactoryResetProtectionWarningDialog(Bundle bundle, DialogInterface dialogInterface, int i) {
                ((ChooseLockGenericFragment) getParentFragment()).setUnlockMethod(bundle.getString("unlockMethodToSet"));
            }

            /* JADX INFO: Access modifiers changed from: private */
            /* JADX INFO: renamed from: lambda$onCreateDialog$1, reason: merged with bridge method [inline-methods] */
            public /* synthetic */ void lambda$onCreateDialog$1$ChooseLockGeneric$ChooseLockGenericFragment$FactoryResetProtectionWarningDialog(DialogInterface dialogInterface, int i) {
                dismiss();
            }
        }
    }

    /* JADX INFO: renamed from: com.android.car.developeroptions.password.ChooseLockGeneric$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$android$car$developeroptions$password$ScreenLockType;

        static {
            int[] iArr = new int[ScreenLockType.values().length];
            $SwitchMap$com$android$car$developeroptions$password$ScreenLockType = iArr;
            try {
                iArr[ScreenLockType.NONE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$android$car$developeroptions$password$ScreenLockType[ScreenLockType.SWIPE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$android$car$developeroptions$password$ScreenLockType[ScreenLockType.PATTERN.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$android$car$developeroptions$password$ScreenLockType[ScreenLockType.PIN.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$android$car$developeroptions$password$ScreenLockType[ScreenLockType.PASSWORD.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$android$car$developeroptions$password$ScreenLockType[ScreenLockType.MANAGED.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
        }
    }
}
