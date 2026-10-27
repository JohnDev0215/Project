package com.android.car.developeroptions.biometrics;

import android.content.Intent;
import android.os.Bundle;
import android.os.UserManager;
import android.os.storage.StorageManager;
import android.view.View;
import android.widget.TextView;
import com.android.car.developeroptions.R;
import com.android.car.developeroptions.password.ChooseLockGeneric;
import com.android.car.developeroptions.password.ChooseLockSettingsHelper;
import com.android.car.developeroptions.password.SetupChooseLockGeneric;
import com.google.android.setupcompat.util.WizardManagerHelper;
import com.google.android.setupdesign.span.LinkSpan;

/* JADX INFO: loaded from: classes.dex */
public abstract class BiometricEnrollIntroduction extends BiometricEnrollBase implements LinkSpan.OnClickListener {
    private boolean mBiometricUnlockDisabledByAdmin;
    private TextView mErrorText;
    private boolean mHasPassword;
    private UserManager mUserManager;

    protected abstract int checkMaxEnrolled();

    protected abstract long getChallenge();

    protected abstract int getConfirmLockTitleResId();

    protected abstract int getDescriptionResDisabledByAdmin();

    protected abstract Intent getEnrollingIntent();

    protected abstract TextView getErrorTextView();

    protected abstract String getExtraKeyForBiometric();

    protected abstract int getHeaderResDefault();

    protected abstract int getHeaderResDisabledByAdmin();

    protected abstract int getLayoutResource();

    protected abstract boolean isDisabledByAdmin();

    @Override // com.android.car.developeroptions.biometrics.BiometricEnrollBase, com.android.car.developeroptions.core.InstrumentedActivity, com.android.settingslib.core.lifecycle.ObservableActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mBiometricUnlockDisabledByAdmin = isDisabledByAdmin();
        setContentView(getLayoutResource());
        if (this.mBiometricUnlockDisabledByAdmin) {
            setHeaderText(getHeaderResDisabledByAdmin());
        } else {
            setHeaderText(getHeaderResDefault());
        }
        this.mErrorText = getErrorTextView();
        this.mUserManager = UserManager.get(this);
        updatePasswordQuality();
        if (!this.mHasPassword) {
            launchChooseLock();
        } else if (!this.mLaunchedConfirmLock || this.mToken == null) {
            launchConfirmLock(getConfirmLockTitleResId(), getChallenge());
        }
    }

    @Override // com.android.settingslib.core.lifecycle.ObservableActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onResume() {
        super.onResume();
        int iCheckMaxEnrolled = checkMaxEnrolled();
        if (iCheckMaxEnrolled == 0) {
            this.mErrorText.setText((CharSequence) null);
            getNextButton().setVisibility(0);
        } else {
            this.mErrorText.setText(iCheckMaxEnrolled);
            getNextButton().setText(getResources().getString(R.string.done));
            getNextButton().setVisibility(0);
        }
    }

    private void updatePasswordQuality() {
        this.mHasPassword = new ChooseLockSettingsHelper(this).utils().getActivePasswordQuality(this.mUserManager.getCredentialOwnerProfile(this.mUserId)) != 0;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void onNextButtonClick(View view) {
        if (checkMaxEnrolled() == 0) {
            launchNextEnrollingActivity(this.mToken);
        } else {
            setResult(1);
            finish();
        }
    }

    private void launchChooseLock() {
        Intent chooseLockIntent = getChooseLockIntent();
        long challenge = getChallenge();
        chooseLockIntent.putExtra("minimum_quality", 65536);
        chooseLockIntent.putExtra("hide_disabled_prefs", true);
        chooseLockIntent.putExtra("has_challenge", true);
        chooseLockIntent.putExtra("challenge", challenge);
        chooseLockIntent.putExtra(getExtraKeyForBiometric(), true);
        int i = this.mUserId;
        if (i != -10000) {
            chooseLockIntent.putExtra("android.intent.extra.USER_ID", i);
        }
        startActivityForResult(chooseLockIntent, 1);
    }

    private void launchNextEnrollingActivity(byte[] bArr) {
        Intent enrollingIntent = getEnrollingIntent();
        if (bArr != null) {
            enrollingIntent.putExtra("hw_auth_token", bArr);
        }
        int i = this.mUserId;
        if (i != -10000) {
            enrollingIntent.putExtra("android.intent.extra.USER_ID", i);
        }
        startActivityForResult(enrollingIntent, 2);
    }

    protected Intent getChooseLockIntent() {
        if (WizardManagerHelper.isAnySetupWizard(getIntent())) {
            Intent intent = new Intent(this, (Class<?>) SetupChooseLockGeneric.class);
            if (StorageManager.isFileEncryptedNativeOrEmulated()) {
                intent.putExtra("lockscreen.password_type", 131072);
                intent.putExtra("show_options_button", true);
            }
            WizardManagerHelper.copyWizardManagerExtras(getIntent(), intent);
            return intent;
        }
        return new Intent(this, (Class<?>) ChooseLockGeneric.class);
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    protected void onActivityResult(int i, int i2, Intent intent) {
        if (i == 2) {
            if (i2 == 1 || i2 == 2) {
                setResult(i2, intent);
                finish();
                return;
            }
        } else if (i == 1) {
            if (i2 == 1) {
                updatePasswordQuality();
                this.mToken = intent.getByteArrayExtra("hw_auth_token");
                overridePendingTransition(R.anim.sud_slide_next_in, R.anim.sud_slide_next_out);
                return;
            }
            setResult(i2, intent);
            finish();
        } else if (i == 4) {
            if (i2 == -1 && intent != null) {
                this.mToken = intent.getByteArrayExtra("hw_auth_token");
                overridePendingTransition(R.anim.sud_slide_next_in, R.anim.sud_slide_next_out);
            } else {
                setResult(i2, intent);
                finish();
            }
        } else if (i == 3) {
            overridePendingTransition(R.anim.sud_slide_back_in, R.anim.sud_slide_back_out);
        }
        super.onActivityResult(i, i2, intent);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void onCancelButtonClick(View view) {
        finish();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void onSkipButtonClick(View view) {
        setResult(2);
        finish();
    }

    @Override // com.android.car.developeroptions.biometrics.BiometricEnrollBase
    protected void initViews() {
        super.initViews();
        TextView textView = (TextView) findViewById(R.id.description_text);
        if (this.mBiometricUnlockDisabledByAdmin) {
            textView.setText(getDescriptionResDisabledByAdmin());
        }
    }
}
