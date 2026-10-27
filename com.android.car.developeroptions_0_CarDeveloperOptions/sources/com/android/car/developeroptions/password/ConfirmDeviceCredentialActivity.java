package com.android.car.developeroptions.password;

import android.app.admin.DevicePolicyManager;
import android.app.trust.TrustManager;
import android.content.Intent;
import android.hardware.biometrics.BiometricManager;
import android.hardware.biometrics.BiometricPrompt;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.UserHandle;
import android.os.UserManager;
import android.util.Log;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;
import com.android.car.developeroptions.R;
import com.android.car.developeroptions.Utils;
import com.android.internal.widget.LockPatternUtils;
import java.util.concurrent.Executor;

/* JADX INFO: loaded from: classes.dex */
public class ConfirmDeviceCredentialActivity extends FragmentActivity {
    public static final String TAG = ConfirmDeviceCredentialActivity.class.getSimpleName();
    private BiometricFragment mBiometricFragment;
    private BiometricManager mBiometricManager;
    private ChooseLockSettingsHelper mChooseLockSettingsHelper;
    private int mCredentialMode;
    private String mDetails;
    private DevicePolicyManager mDevicePolicyManager;
    private boolean mGoingToBackground;
    private LockPatternUtils mLockPatternUtils;
    private String mTitle;
    private TrustManager mTrustManager;
    private int mUserId;
    private UserManager mUserManager;
    private Handler mHandler = new Handler(Looper.getMainLooper());
    private Executor mExecutor = new Executor() { // from class: com.android.car.developeroptions.password.-$$Lambda$ConfirmDeviceCredentialActivity$Vgu-bVVoROokpLsLyNWQG4EZ5uw
        @Override // java.util.concurrent.Executor
        public final void execute(Runnable runnable) {
            this.f$0.lambda$new$0$ConfirmDeviceCredentialActivity(runnable);
        }
    };
    private BiometricPrompt.AuthenticationCallback mAuthenticationCallback = new BiometricPrompt.AuthenticationCallback() { // from class: com.android.car.developeroptions.password.ConfirmDeviceCredentialActivity.1
        @Override // android.hardware.biometrics.BiometricPrompt.AuthenticationCallback
        public void onAuthenticationError(int i, CharSequence charSequence) {
            if (ConfirmDeviceCredentialActivity.this.mGoingToBackground) {
                return;
            }
            if (i != 10 && i != 5) {
                ConfirmDeviceCredentialActivity.this.showConfirmCredentials();
            } else {
                ConfirmDeviceCredentialActivity.this.finish();
            }
        }

        @Override // android.hardware.biometrics.BiometricPrompt.AuthenticationCallback
        public void onAuthenticationSucceeded(BiometricPrompt.AuthenticationResult authenticationResult) {
            ConfirmDeviceCredentialActivity.this.mTrustManager.setDeviceLockedForUser(ConfirmDeviceCredentialActivity.this.mUserId, false);
            ConfirmDeviceCredentialUtils.reportSuccessfulAttempt(ConfirmDeviceCredentialActivity.this.mLockPatternUtils, ConfirmDeviceCredentialActivity.this.mUserManager, ConfirmDeviceCredentialActivity.this.mUserId);
            ConfirmDeviceCredentialUtils.checkForPendingIntent(ConfirmDeviceCredentialActivity.this);
            ConfirmDeviceCredentialActivity.this.setResult(-1);
            ConfirmDeviceCredentialActivity.this.finish();
        }
    };

    public static class InternalActivity extends ConfirmDeviceCredentialActivity {
    }

    public static Intent createIntent(CharSequence charSequence, CharSequence charSequence2) {
        Intent intent = new Intent();
        intent.setClassName("com.android.car.developeroptions", ConfirmDeviceCredentialActivity.class.getName());
        intent.putExtra("android.app.extra.TITLE", charSequence);
        intent.putExtra("android.app.extra.DESCRIPTION", charSequence2);
        return intent;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: lambda$new$0, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void lambda$new$0$ConfirmDeviceCredentialActivity(Runnable runnable) {
        this.mHandler.post(runnable);
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle bundle) {
        String str = TAG;
        super.onCreate(bundle);
        this.mBiometricManager = (BiometricManager) getSystemService(BiometricManager.class);
        this.mDevicePolicyManager = (DevicePolicyManager) getSystemService(DevicePolicyManager.class);
        this.mUserManager = UserManager.get(this);
        this.mTrustManager = (TrustManager) getSystemService(TrustManager.class);
        this.mLockPatternUtils = new LockPatternUtils(this);
        Intent intent = getIntent();
        this.mTitle = intent.getStringExtra("android.app.extra.TITLE");
        this.mDetails = intent.getStringExtra("android.app.extra.DESCRIPTION");
        String stringExtra = intent.getStringExtra("android.app.extra.ALTERNATE_BUTTON_LABEL");
        boolean zEquals = "android.app.action.CONFIRM_FRP_CREDENTIAL".equals(intent.getAction());
        this.mUserId = UserHandle.myUserId();
        if (isInternalActivity()) {
            try {
                this.mUserId = Utils.getUserIdFromBundle(this, intent.getExtras());
            } catch (SecurityException e) {
                Log.e(str, "Invalid intent extra", e);
            }
        }
        int credentialOwnerProfile = this.mUserManager.getCredentialOwnerProfile(this.mUserId);
        boolean zIsManagedProfile = UserManager.get(this).isManagedProfile(this.mUserId);
        if (this.mTitle == null && zIsManagedProfile) {
            this.mTitle = getTitleFromOrganizationName(this.mUserId);
        }
        this.mChooseLockSettingsHelper = new ChooseLockSettingsHelper(this);
        LockPatternUtils lockPatternUtils = new LockPatternUtils(this);
        Bundle bundle2 = new Bundle();
        this.mTitle = bundle2.getString("title");
        this.mDetails = bundle2.getString("subtitle");
        bundle2.putString("title", this.mTitle);
        bundle2.putString("description", this.mDetails);
        boolean zLaunchFrpConfirmationActivity = false;
        boolean z = true;
        if (zEquals) {
            z = false;
            zLaunchFrpConfirmationActivity = this.mChooseLockSettingsHelper.launchFrpConfirmationActivity(0, this.mTitle, this.mDetails, stringExtra);
        } else if (zIsManagedProfile && isInternalActivity() && !lockPatternUtils.isSeparateProfileChallengeEnabled(this.mUserId)) {
            this.mCredentialMode = 2;
            if (isBiometricAllowed(credentialOwnerProfile, this.mUserId)) {
                showBiometricPrompt(bundle2);
            } else {
                showConfirmCredentials();
                z = false;
                zLaunchFrpConfirmationActivity = true;
            }
        } else {
            this.mCredentialMode = 1;
            if (isBiometricAllowed(credentialOwnerProfile, this.mUserId)) {
                showBiometricPrompt(bundle2);
            } else {
                showConfirmCredentials();
                z = false;
                zLaunchFrpConfirmationActivity = true;
            }
        }
        if (zLaunchFrpConfirmationActivity) {
            finish();
        } else {
            if (z) {
                return;
            }
            Log.d(str, "No pattern, password or PIN set.");
            setResult(-1);
            finish();
        }
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onStart() {
        super.onStart();
        setVisible(true);
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onPause() {
        super.onPause();
        if (!isChangingConfigurations()) {
            this.mGoingToBackground = true;
            BiometricFragment biometricFragment = this.mBiometricFragment;
            if (biometricFragment != null) {
                biometricFragment.cancel();
            }
            finish();
            return;
        }
        this.mGoingToBackground = false;
    }

    private boolean isStrongAuthRequired(int i) {
        return (this.mLockPatternUtils.isBiometricAllowedForUser(i) && this.mUserManager.isUserUnlocked(this.mUserId)) ? false : true;
    }

    private boolean isBiometricDisabledByAdmin(int i) {
        return (this.mDevicePolicyManager.getKeyguardDisabledFeatures(null, i) & 416) != 0;
    }

    private boolean isBiometricAllowed(int i, int i2) {
        return (isStrongAuthRequired(i) || isBiometricDisabledByAdmin(i) || this.mLockPatternUtils.hasPendingEscrowToken(i2)) ? false : true;
    }

    private void showBiometricPrompt(Bundle bundle) {
        boolean z;
        this.mBiometricManager.setActiveUser(this.mUserId);
        BiometricFragment biometricFragment = (BiometricFragment) getSupportFragmentManager().findFragmentByTag("fragment");
        this.mBiometricFragment = biometricFragment;
        if (biometricFragment == null) {
            this.mBiometricFragment = BiometricFragment.newInstance(bundle);
            z = true;
        } else {
            z = false;
        }
        this.mBiometricFragment.setCallbacks(this.mExecutor, this.mAuthenticationCallback);
        this.mBiometricFragment.setUser(this.mUserId);
        if (z) {
            FragmentTransaction fragmentTransactionBeginTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransactionBeginTransaction.add(this.mBiometricFragment, "fragment");
            fragmentTransactionBeginTransaction.commit();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showConfirmCredentials() {
        boolean zLaunchConfirmationActivity;
        int i = this.mCredentialMode;
        if (i == 2) {
            zLaunchConfirmationActivity = this.mChooseLockSettingsHelper.launchConfirmationActivityWithExternalAndChallenge(0, null, this.mTitle, this.mDetails, true, 0L, this.mUserId);
        } else {
            zLaunchConfirmationActivity = i == 1 ? this.mChooseLockSettingsHelper.launchConfirmationActivity(0, null, this.mTitle, this.mDetails, false, true, this.mUserId) : false;
        }
        if (!zLaunchConfirmationActivity) {
            Log.d(TAG, "No pin/pattern/pass set");
            setResult(-1);
        }
        finish();
    }

    @Override // android.app.Activity
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.confirm_credential_biometric_transition_enter, 0);
    }

    private boolean isInternalActivity() {
        return this instanceof InternalActivity;
    }

    private String getTitleFromOrganizationName(int i) {
        DevicePolicyManager devicePolicyManager = (DevicePolicyManager) getSystemService("device_policy");
        CharSequence organizationNameForUser = devicePolicyManager != null ? devicePolicyManager.getOrganizationNameForUser(i) : null;
        if (organizationNameForUser != null) {
            return organizationNameForUser.toString();
        }
        return null;
    }
}
