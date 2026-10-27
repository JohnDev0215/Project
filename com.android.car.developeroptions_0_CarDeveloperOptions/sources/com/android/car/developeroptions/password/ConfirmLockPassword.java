package com.android.car.developeroptions.password;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Parcelable;
import android.os.SystemClock;
import android.os.UserManager;
import android.text.Editable;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import com.android.car.developeroptions.R;
import com.android.car.developeroptions.password.ConfirmDeviceCredentialBaseActivity;
import com.android.car.developeroptions.password.ConfirmLockPassword;
import com.android.car.developeroptions.password.CredentialCheckResultTracker;
import com.android.car.developeroptions.widget.ImeAwareEditText;
import com.android.internal.widget.LockPatternChecker;
import com.android.internal.widget.LockscreenCredential;
import com.android.internal.widget.TextViewInputDisabler;
import com.android.settingslib.animation.AppearAnimationUtils;
import com.android.settingslib.animation.DisappearAnimationUtils;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public class ConfirmLockPassword extends ConfirmDeviceCredentialBaseActivity {
    private static final int[] DETAIL_TEXTS = {R.string.lockpassword_confirm_your_pin_generic, R.string.lockpassword_confirm_your_password_generic, R.string.lockpassword_confirm_your_pin_generic_profile, R.string.lockpassword_confirm_your_password_generic_profile, R.string.lockpassword_strong_auth_required_device_pin, R.string.lockpassword_strong_auth_required_device_password, R.string.lockpassword_strong_auth_required_work_pin, R.string.lockpassword_strong_auth_required_work_password};

    public static class InternalActivity extends ConfirmLockPassword {
    }

    @Override // com.android.car.developeroptions.SettingsActivity, android.app.Activity
    public Intent getIntent() {
        Intent intent = new Intent(super.getIntent());
        intent.putExtra(":settings:show_fragment", ConfirmLockPasswordFragment.class.getName());
        return intent;
    }

    @Override // com.android.car.developeroptions.SettingsActivity
    protected boolean isValidFragment(String str) {
        return ConfirmLockPasswordFragment.class.getName().equals(str);
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public void onWindowFocusChanged(boolean z) {
        super.onWindowFocusChanged(z);
        Fragment fragmentFindFragmentById = getSupportFragmentManager().findFragmentById(R.id.main_content);
        if (fragmentFindFragmentById == null || !(fragmentFindFragmentById instanceof ConfirmLockPasswordFragment)) {
            return;
        }
        ((ConfirmLockPasswordFragment) fragmentFindFragmentById).onWindowFocusChanged(z);
    }

    public static class ConfirmLockPasswordFragment extends ConfirmDeviceCredentialBaseFragment implements View.OnClickListener, TextView.OnEditorActionListener, CredentialCheckResultTracker.Listener {
        private AppearAnimationUtils mAppearAnimationUtils;
        private CountDownTimer mCountdownTimer;
        private CredentialCheckResultTracker mCredentialCheckResultTracker;
        private TextView mDetailsTextView;
        private DisappearAnimationUtils mDisappearAnimationUtils;
        private boolean mDisappearing = false;
        private TextView mHeaderTextView;
        private InputMethodManager mImm;
        private boolean mIsAlpha;
        private ImeAwareEditText mPasswordEntry;
        private TextViewInputDisabler mPasswordEntryInputDisabler;
        private AsyncTask<?, ?, ?> mPendingLockCheck;

        @Override // com.android.settingslib.core.instrumentation.Instrumentable
        public int getMetricsCategory() {
            return 30;
        }

        @Override // androidx.fragment.app.Fragment
        public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
            int keyguardStoredPasswordQuality = this.mLockPatternUtils.getKeyguardStoredPasswordQuality(this.mEffectiveUserId);
            View viewInflate = layoutInflater.inflate(((ConfirmLockPassword) getActivity()).getConfirmCredentialTheme() == ConfirmDeviceCredentialBaseActivity.ConfirmCredentialTheme.NORMAL ? R.layout.confirm_lock_password_normal : R.layout.confirm_lock_password, viewGroup, false);
            ImeAwareEditText imeAwareEditText = (ImeAwareEditText) viewInflate.findViewById(R.id.password_entry);
            this.mPasswordEntry = imeAwareEditText;
            imeAwareEditText.setOnEditorActionListener(this);
            this.mPasswordEntry.requestFocus();
            this.mPasswordEntryInputDisabler = new TextViewInputDisabler(this.mPasswordEntry);
            TextView textView = (TextView) viewInflate.findViewById(R.id.headerText);
            this.mHeaderTextView = textView;
            if (textView == null) {
                this.mHeaderTextView = (TextView) viewInflate.findViewById(R.id.suc_layout_title);
            }
            this.mDetailsTextView = (TextView) viewInflate.findViewById(R.id.detailsText);
            this.mErrorTextView = (TextView) viewInflate.findViewById(R.id.errorText);
            this.mIsAlpha = 262144 == keyguardStoredPasswordQuality || 327680 == keyguardStoredPasswordQuality || 393216 == keyguardStoredPasswordQuality || 524288 == keyguardStoredPasswordQuality;
            this.mImm = (InputMethodManager) getActivity().getSystemService("input_method");
            Intent intent = getActivity().getIntent();
            if (intent != null) {
                CharSequence charSequenceExtra = intent.getCharSequenceExtra("com.android.car.developeroptions.ConfirmCredentials.header");
                CharSequence charSequenceExtra2 = intent.getCharSequenceExtra("com.android.car.developeroptions.ConfirmCredentials.details");
                if (TextUtils.isEmpty(charSequenceExtra)) {
                    charSequenceExtra = getString(getDefaultHeader());
                }
                if (TextUtils.isEmpty(charSequenceExtra2)) {
                    charSequenceExtra2 = getString(getDefaultDetails());
                }
                this.mHeaderTextView.setText(charSequenceExtra);
                this.mDetailsTextView.setText(charSequenceExtra2);
            }
            int inputType = this.mPasswordEntry.getInputType();
            ImeAwareEditText imeAwareEditText2 = this.mPasswordEntry;
            if (!this.mIsAlpha) {
                inputType = 18;
            }
            imeAwareEditText2.setInputType(inputType);
            this.mPasswordEntry.setTypeface(Typeface.create(getContext().getString(android.R.string.config_customAdbPublicKeyConfirmationComponent), 0));
            this.mAppearAnimationUtils = new AppearAnimationUtils(getContext(), 220L, 2.0f, 1.0f, AnimationUtils.loadInterpolator(getContext(), android.R.interpolator.linear_out_slow_in));
            this.mDisappearAnimationUtils = new DisappearAnimationUtils(getContext(), 110L, 1.0f, 0.5f, AnimationUtils.loadInterpolator(getContext(), android.R.interpolator.fast_out_linear_in));
            setAccessibilityTitle(this.mHeaderTextView.getText());
            CredentialCheckResultTracker credentialCheckResultTracker = (CredentialCheckResultTracker) getFragmentManager().findFragmentByTag("check_lock_result");
            this.mCredentialCheckResultTracker = credentialCheckResultTracker;
            if (credentialCheckResultTracker == null) {
                this.mCredentialCheckResultTracker = new CredentialCheckResultTracker();
                FragmentTransaction fragmentTransactionBeginTransaction = getFragmentManager().beginTransaction();
                fragmentTransactionBeginTransaction.add(this.mCredentialCheckResultTracker, "check_lock_result");
                fragmentTransactionBeginTransaction.commit();
            }
            return viewInflate;
        }

        private int getDefaultHeader() {
            return this.mFrp ? this.mIsAlpha ? R.string.lockpassword_confirm_your_password_header_frp : R.string.lockpassword_confirm_your_pin_header_frp : this.mIsAlpha ? R.string.lockpassword_confirm_your_password_header : R.string.lockpassword_confirm_your_pin_header;
        }

        private int getDefaultDetails() {
            if (this.mFrp) {
                return this.mIsAlpha ? R.string.lockpassword_confirm_your_password_details_frp : R.string.lockpassword_confirm_your_pin_details_frp;
            }
            return ConfirmLockPassword.DETAIL_TEXTS[((isStrongAuthRequired() ? 1 : 0) << 2) + ((UserManager.get(getActivity()).isManagedProfile(this.mEffectiveUserId) ? 1 : 0) << 1) + (this.mIsAlpha ? 1 : 0)];
        }

        private int getErrorMessage() {
            return this.mIsAlpha ? R.string.lockpassword_invalid_password : R.string.lockpassword_invalid_pin;
        }

        @Override // com.android.car.developeroptions.password.ConfirmDeviceCredentialBaseFragment
        protected int getLastTryErrorMessage(int i) {
            if (i == 1) {
                return this.mIsAlpha ? R.string.lock_last_password_attempt_before_wipe_device : R.string.lock_last_pin_attempt_before_wipe_device;
            }
            if (i == 2) {
                return this.mIsAlpha ? R.string.lock_last_password_attempt_before_wipe_profile : R.string.lock_last_pin_attempt_before_wipe_profile;
            }
            if (i == 3) {
                return this.mIsAlpha ? R.string.lock_last_password_attempt_before_wipe_user : R.string.lock_last_pin_attempt_before_wipe_user;
            }
            throw new IllegalArgumentException("Unrecognized user type:" + i);
        }

        @Override // com.android.car.developeroptions.password.ConfirmDeviceCredentialBaseFragment
        public void prepareEnterAnimation() {
            super.prepareEnterAnimation();
            this.mHeaderTextView.setAlpha(0.0f);
            this.mDetailsTextView.setAlpha(0.0f);
            this.mCancelButton.setAlpha(0.0f);
            this.mPasswordEntry.setAlpha(0.0f);
            this.mErrorTextView.setAlpha(0.0f);
        }

        private View[] getActiveViews() {
            ArrayList arrayList = new ArrayList();
            arrayList.add(this.mHeaderTextView);
            arrayList.add(this.mDetailsTextView);
            if (this.mCancelButton.getVisibility() == 0) {
                arrayList.add(this.mCancelButton);
            }
            arrayList.add(this.mPasswordEntry);
            arrayList.add(this.mErrorTextView);
            return (View[]) arrayList.toArray(new View[0]);
        }

        @Override // com.android.car.developeroptions.password.ConfirmDeviceCredentialBaseFragment
        public void startEnterAnimation() {
            super.startEnterAnimation();
            this.mAppearAnimationUtils.startAnimation(getActiveViews(), new $$Lambda$ConfirmLockPassword$ConfirmLockPasswordFragment$YlJJyZAp5Sij75QLwkn6i6AcNF0(this));
        }

        @Override // com.android.car.developeroptions.password.ConfirmDeviceCredentialBaseFragment, com.android.settingslib.core.lifecycle.ObservableFragment, androidx.fragment.app.Fragment
        public void onPause() {
            super.onPause();
            CountDownTimer countDownTimer = this.mCountdownTimer;
            if (countDownTimer != null) {
                countDownTimer.cancel();
                this.mCountdownTimer = null;
            }
            this.mCredentialCheckResultTracker.setListener(null);
        }

        @Override // com.android.car.developeroptions.password.ConfirmDeviceCredentialBaseFragment, com.android.car.developeroptions.core.InstrumentedFragment, com.android.settingslib.core.lifecycle.ObservableFragment, androidx.fragment.app.Fragment
        public void onResume() {
            super.onResume();
            long lockoutAttemptDeadline = this.mLockPatternUtils.getLockoutAttemptDeadline(this.mEffectiveUserId);
            if (lockoutAttemptDeadline != 0) {
                this.mCredentialCheckResultTracker.clearResult();
                handleAttemptLockout(lockoutAttemptDeadline);
            } else {
                updatePasswordEntry();
                this.mErrorTextView.setText("");
                updateErrorMessage(this.mLockPatternUtils.getCurrentFailedPasswordAttempts(this.mEffectiveUserId));
            }
            this.mCredentialCheckResultTracker.setListener(this);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void updatePasswordEntry() {
            boolean z = this.mLockPatternUtils.getLockoutAttemptDeadline(this.mEffectiveUserId) != 0;
            this.mPasswordEntry.setEnabled(!z);
            this.mPasswordEntryInputDisabler.setInputEnabled(!z);
            if (z) {
                this.mImm.hideSoftInputFromWindow(this.mPasswordEntry.getWindowToken(), 0);
            } else {
                this.mPasswordEntry.scheduleShowSoftInput();
            }
        }

        public void onWindowFocusChanged(boolean z) {
            if (z) {
                this.mPasswordEntry.post(new $$Lambda$ConfirmLockPassword$ConfirmLockPasswordFragment$YlJJyZAp5Sij75QLwkn6i6AcNF0(this));
            }
        }

        private void handleNext() {
            LockscreenCredential lockscreenCredentialCreatePin;
            if (this.mPendingLockCheck != null || this.mDisappearing) {
                return;
            }
            Editable text = this.mPasswordEntry.getText();
            if (TextUtils.isEmpty(text)) {
                return;
            }
            if (this.mIsAlpha) {
                lockscreenCredentialCreatePin = LockscreenCredential.createPassword(text);
            } else {
                lockscreenCredentialCreatePin = LockscreenCredential.createPin(text);
            }
            this.mPasswordEntryInputDisabler.setInputEnabled(false);
            boolean booleanExtra = getActivity().getIntent().getBooleanExtra("has_challenge", false);
            Intent intent = new Intent();
            if (booleanExtra) {
                if (isInternalActivity()) {
                    startVerifyPassword(lockscreenCredentialCreatePin, intent);
                    return;
                } else {
                    this.mCredentialCheckResultTracker.setResult(false, intent, 0, this.mEffectiveUserId);
                    return;
                }
            }
            startCheckPassword(lockscreenCredentialCreatePin, intent);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean isInternalActivity() {
            return getActivity() instanceof InternalActivity;
        }

        private void startVerifyPassword(LockscreenCredential lockscreenCredential, final Intent intent) {
            AsyncTask<?, ?, ?> asyncTaskVerifyTiedProfileChallenge;
            long longExtra = getActivity().getIntent().getLongExtra("challenge", 0L);
            final int i = this.mEffectiveUserId;
            int i2 = this.mUserId;
            LockPatternChecker.OnVerifyCallback onVerifyCallback = new LockPatternChecker.OnVerifyCallback() { // from class: com.android.car.developeroptions.password.ConfirmLockPassword.ConfirmLockPasswordFragment.1
                public void onVerified(byte[] bArr, int i3) {
                    boolean z;
                    ConfirmLockPasswordFragment.this.mPendingLockCheck = null;
                    if (bArr != null) {
                        z = true;
                        if (ConfirmLockPasswordFragment.this.mReturnCredentials) {
                            intent.putExtra("hw_auth_token", bArr);
                        }
                    } else {
                        z = false;
                    }
                    ConfirmLockPasswordFragment.this.mCredentialCheckResultTracker.setResult(z, intent, i3, i);
                }
            };
            if (i == i2) {
                asyncTaskVerifyTiedProfileChallenge = LockPatternChecker.verifyCredential(this.mLockPatternUtils, lockscreenCredential, longExtra, i2, onVerifyCallback);
            } else {
                asyncTaskVerifyTiedProfileChallenge = LockPatternChecker.verifyTiedProfileChallenge(this.mLockPatternUtils, lockscreenCredential, longExtra, i2, onVerifyCallback);
            }
            this.mPendingLockCheck = asyncTaskVerifyTiedProfileChallenge;
        }

        private void startCheckPassword(final LockscreenCredential lockscreenCredential, final Intent intent) {
            final int i = this.mEffectiveUserId;
            this.mPendingLockCheck = LockPatternChecker.checkCredential(this.mLockPatternUtils, lockscreenCredential, i, new LockPatternChecker.OnCheckCallback() { // from class: com.android.car.developeroptions.password.ConfirmLockPassword.ConfirmLockPasswordFragment.2
                public void onChecked(boolean z, int i2) {
                    ConfirmLockPasswordFragment.this.mPendingLockCheck = null;
                    if (z && ConfirmLockPasswordFragment.this.isInternalActivity()) {
                        ConfirmLockPasswordFragment confirmLockPasswordFragment = ConfirmLockPasswordFragment.this;
                        if (confirmLockPasswordFragment.mReturnCredentials) {
                            intent.putExtra("type", confirmLockPasswordFragment.mIsAlpha ? 0 : 3);
                            intent.putExtra("password", (Parcelable) lockscreenCredential);
                        }
                    }
                    ConfirmLockPasswordFragment.this.mCredentialCheckResultTracker.setResult(z, intent, i2, i);
                }
            });
        }

        private void startDisappearAnimation(final Intent intent) {
            if (this.mDisappearing) {
                return;
            }
            this.mDisappearing = true;
            final ConfirmLockPassword confirmLockPassword = (ConfirmLockPassword) getActivity();
            if (confirmLockPassword == null || confirmLockPassword.isFinishing()) {
                return;
            }
            if (confirmLockPassword.getConfirmCredentialTheme() == ConfirmDeviceCredentialBaseActivity.ConfirmCredentialTheme.DARK) {
                this.mDisappearAnimationUtils.startAnimation(getActiveViews(), new Runnable() { // from class: com.android.car.developeroptions.password.-$$Lambda$ConfirmLockPassword$ConfirmLockPasswordFragment$hnnIKbSBrMdLl-p6ylApuftMNTs
                    @Override // java.lang.Runnable
                    public final void run() {
                        ConfirmLockPassword.ConfirmLockPasswordFragment.lambda$startDisappearAnimation$0(confirmLockPassword, intent);
                    }
                });
            } else {
                confirmLockPassword.setResult(-1, intent);
                confirmLockPassword.finish();
            }
        }

        static /* synthetic */ void lambda$startDisappearAnimation$0(ConfirmLockPassword confirmLockPassword, Intent intent) {
            confirmLockPassword.setResult(-1, intent);
            confirmLockPassword.finish();
            confirmLockPassword.overridePendingTransition(R.anim.confirm_credential_close_enter, R.anim.confirm_credential_close_exit);
        }

        private void onPasswordChecked(boolean z, Intent intent, int i, int i2, boolean z2) {
            this.mPasswordEntryInputDisabler.setInputEnabled(true);
            if (z) {
                if (z2) {
                    ConfirmDeviceCredentialUtils.reportSuccessfulAttempt(this.mLockPatternUtils, this.mUserManager, this.mEffectiveUserId);
                }
                startDisappearAnimation(intent);
                ConfirmDeviceCredentialUtils.checkForPendingIntent(getActivity());
                return;
            }
            if (i > 0) {
                refreshLockScreen();
                handleAttemptLockout(this.mLockPatternUtils.setLockoutAttemptDeadline(i2, i));
            } else {
                showError(getErrorMessage(), 3000L);
            }
            if (z2) {
                reportFailedAttempt();
            }
        }

        @Override // com.android.car.developeroptions.password.CredentialCheckResultTracker.Listener
        public void onCredentialChecked(boolean z, Intent intent, int i, int i2, boolean z2) {
            onPasswordChecked(z, intent, i, i2, z2);
        }

        @Override // com.android.car.developeroptions.password.ConfirmDeviceCredentialBaseFragment
        protected void onShowError() {
            this.mPasswordEntry.setText((CharSequence) null);
        }

        private void handleAttemptLockout(long j) {
            this.mCountdownTimer = new CountDownTimer(j - SystemClock.elapsedRealtime(), 1000L) { // from class: com.android.car.developeroptions.password.ConfirmLockPassword.ConfirmLockPasswordFragment.3
                @Override // android.os.CountDownTimer
                public void onTick(long j2) {
                    ConfirmLockPasswordFragment confirmLockPasswordFragment = ConfirmLockPasswordFragment.this;
                    confirmLockPasswordFragment.showError(confirmLockPasswordFragment.getString(R.string.lockpattern_too_many_failed_confirmation_attempts, Integer.valueOf((int) (j2 / 1000))), 0L);
                }

                @Override // android.os.CountDownTimer
                public void onFinish() {
                    ConfirmLockPasswordFragment.this.updatePasswordEntry();
                    ConfirmLockPasswordFragment.this.mErrorTextView.setText("");
                    ConfirmLockPasswordFragment confirmLockPasswordFragment = ConfirmLockPasswordFragment.this;
                    confirmLockPasswordFragment.updateErrorMessage(confirmLockPasswordFragment.mLockPatternUtils.getCurrentFailedPasswordAttempts(confirmLockPasswordFragment.mEffectiveUserId));
                }
            }.start();
            updatePasswordEntry();
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            int id = view.getId();
            if (id == R.id.cancel_button) {
                getActivity().setResult(0);
                getActivity().finish();
            } else {
                if (id != R.id.next_button) {
                    return;
                }
                handleNext();
            }
        }

        @Override // android.widget.TextView.OnEditorActionListener
        public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
            if (i != 0 && i != 6 && i != 5) {
                return false;
            }
            handleNext();
            return true;
        }
    }
}
