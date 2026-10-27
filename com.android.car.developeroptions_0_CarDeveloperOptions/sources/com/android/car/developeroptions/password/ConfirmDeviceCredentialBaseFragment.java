package com.android.car.developeroptions.password;

import android.app.Dialog;
import android.app.admin.DevicePolicyManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.UserInfo;
import android.graphics.Point;
import android.graphics.PorterDuff;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.UserManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;
import com.android.car.developeroptions.R;
import com.android.car.developeroptions.Utils;
import com.android.car.developeroptions.core.InstrumentedFragment;
import com.android.car.developeroptions.password.ConfirmLockPassword;
import com.android.car.developeroptions.password.ConfirmLockPattern;
import com.android.internal.widget.LockPatternUtils;

/* JADX INFO: loaded from: classes.dex */
public abstract class ConfirmDeviceCredentialBaseFragment extends InstrumentedFragment {
    protected Button mCancelButton;
    protected DevicePolicyManager mDevicePolicyManager;
    protected int mEffectiveUserId;
    protected TextView mErrorTextView;
    protected boolean mFrp;
    private CharSequence mFrpAlternateButtonText;
    protected LockPatternUtils mLockPatternUtils;
    protected int mUserId;
    protected UserManager mUserManager;
    protected boolean mReturnCredentials = false;
    protected final Handler mHandler = new Handler();
    private final Runnable mResetErrorRunnable = new Runnable() { // from class: com.android.car.developeroptions.password.ConfirmDeviceCredentialBaseFragment.2
        @Override // java.lang.Runnable
        public void run() {
            ConfirmDeviceCredentialBaseFragment.this.mErrorTextView.setText("");
        }
    };

    protected abstract int getLastTryErrorMessage(int i);

    protected abstract void onShowError();

    public void prepareEnterAnimation() {
    }

    public void startEnterAnimation() {
    }

    private boolean isInternalActivity() {
        return (getActivity() instanceof ConfirmLockPassword.InternalActivity) || (getActivity() instanceof ConfirmLockPattern.InternalActivity);
    }

    @Override // com.android.settingslib.core.lifecycle.ObservableFragment, androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mFrpAlternateButtonText = getActivity().getIntent().getCharSequenceExtra("android.app.extra.ALTERNATE_BUTTON_LABEL");
        this.mReturnCredentials = getActivity().getIntent().getBooleanExtra("return_credentials", false);
        int userIdFromBundle = Utils.getUserIdFromBundle(getActivity(), getActivity().getIntent().getExtras(), isInternalActivity());
        this.mUserId = userIdFromBundle;
        this.mFrp = userIdFromBundle == -9999;
        UserManager userManager = UserManager.get(getActivity());
        this.mUserManager = userManager;
        this.mEffectiveUserId = userManager.getCredentialOwnerProfile(this.mUserId);
        this.mLockPatternUtils = new LockPatternUtils(getActivity());
        this.mDevicePolicyManager = (DevicePolicyManager) getActivity().getSystemService("device_policy");
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        this.mCancelButton = (Button) view.findViewById(R.id.cancelButton);
        int i = 0;
        boolean booleanExtra = getActivity().getIntent().getBooleanExtra("com.android.car.developeroptions.ConfirmCredentials.showCancelButton", false);
        final boolean z = this.mFrp && !TextUtils.isEmpty(this.mFrpAlternateButtonText);
        Button button = this.mCancelButton;
        if (!booleanExtra && !z) {
            i = 8;
        }
        button.setVisibility(i);
        if (z) {
            this.mCancelButton.setText(this.mFrpAlternateButtonText);
        }
        this.mCancelButton.setOnClickListener(new View.OnClickListener() { // from class: com.android.car.developeroptions.password.ConfirmDeviceCredentialBaseFragment.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                if (z) {
                    ConfirmDeviceCredentialBaseFragment.this.getActivity().setResult(1);
                }
                ConfirmDeviceCredentialBaseFragment.this.getActivity().finish();
            }
        });
        int credentialOwnerUserId = Utils.getCredentialOwnerUserId(getActivity(), Utils.getUserIdFromBundle(getActivity(), getActivity().getIntent().getExtras(), isInternalActivity()));
        if (this.mUserManager.isManagedProfile(credentialOwnerUserId)) {
            setWorkChallengeBackground(view, credentialOwnerUserId);
        }
    }

    protected boolean isStrongAuthRequired() {
        return (!this.mFrp && this.mLockPatternUtils.isBiometricAllowedForUser(this.mEffectiveUserId) && this.mUserManager.isUserUnlocked(this.mUserId)) ? false : true;
    }

    @Override // com.android.car.developeroptions.core.InstrumentedFragment, com.android.settingslib.core.lifecycle.ObservableFragment, androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        refreshLockScreen();
    }

    protected void refreshLockScreen() {
        updateErrorMessage(this.mLockPatternUtils.getCurrentFailedPasswordAttempts(this.mEffectiveUserId));
    }

    protected void setAccessibilityTitle(CharSequence charSequence) {
        Intent intent = getActivity().getIntent();
        if (intent != null) {
            CharSequence charSequenceExtra = intent.getCharSequenceExtra("com.android.car.developeroptions.ConfirmCredentials.title");
            if (charSequence == null) {
                return;
            }
            if (charSequenceExtra == null) {
                getActivity().setTitle(charSequence);
                return;
            }
            getActivity().setTitle(Utils.createAccessibleSequence(charSequenceExtra, charSequenceExtra + "," + charSequence));
        }
    }

    @Override // com.android.settingslib.core.lifecycle.ObservableFragment, androidx.fragment.app.Fragment
    public void onPause() {
        super.onPause();
    }

    private void setWorkChallengeBackground(View view, int i) {
        View viewFindViewById = getActivity().findViewById(R.id.main_content);
        if (viewFindViewById != null) {
            viewFindViewById.setPadding(0, 0, 0, 0);
        }
        view.setBackground(new ColorDrawable(this.mDevicePolicyManager.getOrganizationColorForUser(i)));
        ImageView imageView = (ImageView) view.findViewById(R.id.background_image);
        if (imageView != null) {
            Drawable drawable = getResources().getDrawable(R.drawable.work_challenge_background);
            drawable.setColorFilter(getResources().getColor(R.color.confirm_device_credential_transparent_black), PorterDuff.Mode.DARKEN);
            imageView.setImageDrawable(drawable);
            Point point = new Point();
            getActivity().getWindowManager().getDefaultDisplay().getSize(point);
            imageView.setLayoutParams(new FrameLayout.LayoutParams(-1, point.y));
        }
    }

    protected void reportFailedAttempt() {
        updateErrorMessage(this.mLockPatternUtils.getCurrentFailedPasswordAttempts(this.mEffectiveUserId) + 1);
        this.mLockPatternUtils.reportFailedPasswordAttempt(this.mEffectiveUserId);
    }

    protected void updateErrorMessage(int i) {
        int maximumFailedPasswordsForWipe = this.mLockPatternUtils.getMaximumFailedPasswordsForWipe(this.mEffectiveUserId);
        if (maximumFailedPasswordsForWipe <= 0 || i <= 0) {
            return;
        }
        if (this.mErrorTextView != null) {
            showError(getActivity().getString(R.string.lock_failed_attempts_before_wipe, new Object[]{Integer.valueOf(i), Integer.valueOf(maximumFailedPasswordsForWipe)}), 0L);
        }
        int i2 = maximumFailedPasswordsForWipe - i;
        if (i2 > 1) {
            return;
        }
        FragmentManager childFragmentManager = getChildFragmentManager();
        int userTypeForWipe = getUserTypeForWipe();
        if (i2 == 1) {
            LastTryDialog.show(childFragmentManager, getActivity().getString(R.string.lock_last_attempt_before_wipe_warning_title), getLastTryErrorMessage(userTypeForWipe), android.R.string.ok, false);
        } else {
            LastTryDialog.show(childFragmentManager, null, getWipeMessage(userTypeForWipe), R.string.lock_failed_attempts_now_wiping_dialog_dismiss, true);
        }
    }

    private int getUserTypeForWipe() {
        UserInfo userInfo = this.mUserManager.getUserInfo(this.mDevicePolicyManager.getProfileWithMinimumFailedPasswordsForWipe(this.mEffectiveUserId));
        if (userInfo == null || userInfo.isPrimary()) {
            return 1;
        }
        return userInfo.isManagedProfile() ? 2 : 3;
    }

    private int getWipeMessage(int i) {
        if (i == 1) {
            return R.string.lock_failed_attempts_now_wiping_device;
        }
        if (i == 2) {
            return R.string.lock_failed_attempts_now_wiping_profile;
        }
        if (i == 3) {
            return R.string.lock_failed_attempts_now_wiping_user;
        }
        throw new IllegalArgumentException("Unrecognized user type:" + i);
    }

    protected void showError(CharSequence charSequence, long j) {
        this.mErrorTextView.setText(charSequence);
        onShowError();
        this.mHandler.removeCallbacks(this.mResetErrorRunnable);
        if (j != 0) {
            this.mHandler.postDelayed(this.mResetErrorRunnable, j);
        }
    }

    protected void showError(int i, long j) {
        showError(getText(i), j);
    }

    public static class LastTryDialog extends DialogFragment {
        private static final String TAG = LastTryDialog.class.getSimpleName();

        static boolean show(FragmentManager fragmentManager, String str, int i, int i2, boolean z) {
            String str2 = TAG;
            LastTryDialog lastTryDialog = (LastTryDialog) fragmentManager.findFragmentByTag(str2);
            if (lastTryDialog != null && !lastTryDialog.isRemoving()) {
                return false;
            }
            Bundle bundle = new Bundle();
            bundle.putString("title", str);
            bundle.putInt("message", i);
            bundle.putInt("button", i2);
            bundle.putBoolean("dismiss", z);
            LastTryDialog lastTryDialog2 = new LastTryDialog();
            lastTryDialog2.setArguments(bundle);
            lastTryDialog2.show(fragmentManager, str2);
            fragmentManager.executePendingTransactions();
            return true;
        }

        @Override // androidx.fragment.app.DialogFragment
        public Dialog onCreateDialog(Bundle bundle) {
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setTitle(getArguments().getString("title"));
            builder.setMessage(getArguments().getInt("message"));
            builder.setPositiveButton(getArguments().getInt("button"), (DialogInterface.OnClickListener) null);
            AlertDialog alertDialogCreate = builder.create();
            alertDialogCreate.setCanceledOnTouchOutside(false);
            return alertDialogCreate;
        }

        @Override // androidx.fragment.app.DialogFragment, android.content.DialogInterface.OnDismissListener
        public void onDismiss(DialogInterface dialogInterface) {
            super.onDismiss(dialogInterface);
            if (getActivity() == null || !getArguments().getBoolean("dismiss")) {
                return;
            }
            getActivity().finish();
        }
    }
}
