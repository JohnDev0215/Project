package com.android.car.developeroptions.password;

import android.app.KeyguardManager;
import android.os.Bundle;
import android.os.UserManager;
import android.util.Log;
import android.view.MenuItem;
import android.widget.LinearLayout;
import androidx.fragment.app.Fragment;
import com.android.car.developeroptions.R;
import com.android.car.developeroptions.SettingsActivity;
import com.android.car.developeroptions.SetupWizardUtils;
import com.android.car.developeroptions.Utils;
import com.android.car.developeroptions.password.ConfirmLockPassword;
import com.android.car.developeroptions.password.ConfirmLockPattern;

/* JADX INFO: loaded from: classes.dex */
public abstract class ConfirmDeviceCredentialBaseActivity extends SettingsActivity {
    private ConfirmCredentialTheme mConfirmCredentialTheme;
    private boolean mEnterAnimationPending;
    private boolean mFirstTimeVisible = true;
    private boolean mIsKeyguardLocked = false;
    private boolean mRestoring;

    enum ConfirmCredentialTheme {
        NORMAL,
        DARK,
        WORK
    }

    private boolean isInternalActivity() {
        return (this instanceof ConfirmLockPassword.InternalActivity) || (this instanceof ConfirmLockPattern.InternalActivity);
    }

    @Override // com.android.car.developeroptions.SettingsActivity, com.android.car.developeroptions.core.SettingsBaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle bundle) {
        boolean zIsKeyguardLocked;
        try {
            if (UserManager.get(this).isManagedProfile(Utils.getCredentialOwnerUserId(this, Utils.getUserIdFromBundle(this, getIntent().getExtras(), isInternalActivity())))) {
                setTheme(R.style.Theme_ConfirmDeviceCredentialsWork);
                this.mConfirmCredentialTheme = ConfirmCredentialTheme.WORK;
            } else if (getIntent().getBooleanExtra("com.android.car.developeroptions.ConfirmCredentials.darkTheme", false)) {
                setTheme(R.style.Theme_ConfirmDeviceCredentialsDark);
                this.mConfirmCredentialTheme = ConfirmCredentialTheme.DARK;
            } else {
                setTheme(SetupWizardUtils.getTheme(getIntent()));
                this.mConfirmCredentialTheme = ConfirmCredentialTheme.NORMAL;
            }
            super.onCreate(bundle);
            if (this.mConfirmCredentialTheme == ConfirmCredentialTheme.NORMAL) {
                ((LinearLayout) findViewById(R.id.content_parent)).setFitsSystemWindows(false);
            }
            getWindow().addFlags(8192);
            if (bundle == null) {
                zIsKeyguardLocked = ((KeyguardManager) getSystemService(KeyguardManager.class)).isKeyguardLocked();
            } else {
                zIsKeyguardLocked = bundle.getBoolean("STATE_IS_KEYGUARD_LOCKED", false);
            }
            this.mIsKeyguardLocked = zIsKeyguardLocked;
            if (zIsKeyguardLocked && getIntent().getBooleanExtra("com.android.car.developeroptions.ConfirmCredentials.showWhenLocked", false)) {
                getWindow().addFlags(524288);
            }
            setTitle(getIntent().getStringExtra("com.android.car.developeroptions.ConfirmCredentials.title"));
            if (getActionBar() != null) {
                getActionBar().setDisplayHomeAsUpEnabled(true);
                getActionBar().setHomeButtonEnabled(true);
            }
            this.mRestoring = bundle != null;
        } catch (SecurityException e) {
            Log.e("ConfirmDeviceCredentialBaseActivity", "Invalid user Id supplied", e);
            finish();
        }
    }

    @Override // com.android.car.developeroptions.SettingsActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putBoolean("STATE_IS_KEYGUARD_LOCKED", this.mIsKeyguardLocked);
    }

    @Override // android.app.Activity
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() == 16908332) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(menuItem);
    }

    @Override // com.android.car.developeroptions.SettingsActivity, com.android.car.developeroptions.core.SettingsBaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        if (isChangingConfigurations() || this.mRestoring || this.mConfirmCredentialTheme != ConfirmCredentialTheme.DARK || !this.mFirstTimeVisible) {
            return;
        }
        this.mFirstTimeVisible = false;
        prepareEnterAnimation();
        this.mEnterAnimationPending = true;
    }

    private ConfirmDeviceCredentialBaseFragment getFragment() {
        Fragment fragmentFindFragmentById = getSupportFragmentManager().findFragmentById(R.id.main_content);
        if (fragmentFindFragmentById == null || !(fragmentFindFragmentById instanceof ConfirmDeviceCredentialBaseFragment)) {
            return null;
        }
        return (ConfirmDeviceCredentialBaseFragment) fragmentFindFragmentById;
    }

    @Override // android.app.Activity
    public void onEnterAnimationComplete() {
        super.onEnterAnimationComplete();
        if (this.mEnterAnimationPending) {
            startEnterAnimation();
            this.mEnterAnimationPending = false;
        }
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onStop() {
        super.onStop();
    }

    @Override // android.app.Activity
    public void finish() {
        super.finish();
        if (getIntent().getBooleanExtra("com.android.car.developeroptions.ConfirmCredentials.useFadeAnimation", false)) {
            overridePendingTransition(0, R.anim.confirm_credential_biometric_transition_exit);
        }
    }

    public void prepareEnterAnimation() {
        getFragment().prepareEnterAnimation();
    }

    public void startEnterAnimation() {
        getFragment().startEnterAnimation();
    }

    public ConfirmCredentialTheme getConfirmCredentialTheme() {
        return this.mConfirmCredentialTheme;
    }
}
