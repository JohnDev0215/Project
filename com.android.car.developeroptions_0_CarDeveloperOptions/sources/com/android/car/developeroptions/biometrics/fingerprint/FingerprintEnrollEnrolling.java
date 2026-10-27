package com.android.car.developeroptions.biometrics.fingerprint;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Animatable2;
import android.graphics.drawable.AnimatedVectorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.media.AudioAttributes;
import android.os.Bundle;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.view.animation.Interpolator;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.appcompat.app.AlertDialog;
import com.android.car.developeroptions.R;
import com.android.car.developeroptions.biometrics.BiometricEnrollBase;
import com.android.car.developeroptions.biometrics.BiometricEnrollSidecar;
import com.android.car.developeroptions.biometrics.BiometricErrorDialog;
import com.android.car.developeroptions.biometrics.BiometricsEnrollEnrolling;
import com.android.car.developeroptions.core.instrumentation.InstrumentedDialogFragment;
import com.google.android.setupcompat.template.FooterBarMixin;
import com.google.android.setupcompat.template.FooterButton;

/* JADX INFO: loaded from: classes.dex */
public class FingerprintEnrollEnrolling extends BiometricsEnrollEnrolling {
    private boolean mAnimationCancelled;
    private TextView mErrorText;
    private Interpolator mFastOutLinearInInterpolator;
    private Interpolator mFastOutSlowInInterpolator;
    private AnimatedVectorDrawable mIconAnimationDrawable;
    private AnimatedVectorDrawable mIconBackgroundBlinksDrawable;
    private int mIconTouchCount;
    private Interpolator mLinearOutSlowInInterpolator;
    private ObjectAnimator mProgressAnim;
    private ProgressBar mProgressBar;
    private TextView mRepeatMessage;
    private boolean mRestoring;
    private TextView mStartMessage;
    private Vibrator mVibrator;
    private static final VibrationEffect VIBRATE_EFFECT_ERROR = VibrationEffect.createWaveform(new long[]{0, 5, 55, 60}, -1);
    private static final AudioAttributes FINGERPRINT_ENROLLING_SONFICATION_ATTRIBUTES = new AudioAttributes.Builder().setContentType(4).setUsage(13).build();
    private final Animator.AnimatorListener mProgressAnimationListener = new Animator.AnimatorListener() { // from class: com.android.car.developeroptions.biometrics.fingerprint.FingerprintEnrollEnrolling.2
        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationCancel(Animator animator) {
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationRepeat(Animator animator) {
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animator) {
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            if (FingerprintEnrollEnrolling.this.mProgressBar.getProgress() >= 10000) {
                FingerprintEnrollEnrolling.this.mProgressBar.postDelayed(FingerprintEnrollEnrolling.this.mDelayedFinishRunnable, 250L);
            }
        }
    };
    private final Runnable mDelayedFinishRunnable = new Runnable() { // from class: com.android.car.developeroptions.biometrics.fingerprint.FingerprintEnrollEnrolling.3
        @Override // java.lang.Runnable
        public void run() {
            FingerprintEnrollEnrolling fingerprintEnrollEnrolling = FingerprintEnrollEnrolling.this;
            fingerprintEnrollEnrolling.launchFinish(((BiometricEnrollBase) fingerprintEnrollEnrolling).mToken);
        }
    };
    private final Animatable2.AnimationCallback mIconAnimationCallback = new Animatable2.AnimationCallback() { // from class: com.android.car.developeroptions.biometrics.fingerprint.FingerprintEnrollEnrolling.4
        @Override // android.graphics.drawable.Animatable2.AnimationCallback
        public void onAnimationEnd(Drawable drawable) {
            if (FingerprintEnrollEnrolling.this.mAnimationCancelled) {
                return;
            }
            FingerprintEnrollEnrolling.this.mProgressBar.post(new Runnable() { // from class: com.android.car.developeroptions.biometrics.fingerprint.FingerprintEnrollEnrolling.4.1
                @Override // java.lang.Runnable
                public void run() {
                    FingerprintEnrollEnrolling.this.startIconAnimation();
                }
            });
        }
    };
    private final Runnable mShowDialogRunnable = new Runnable() { // from class: com.android.car.developeroptions.biometrics.fingerprint.FingerprintEnrollEnrolling.5
        @Override // java.lang.Runnable
        public void run() {
            FingerprintEnrollEnrolling.this.showIconTouchDialog();
        }
    };
    private final Runnable mTouchAgainRunnable = new Runnable() { // from class: com.android.car.developeroptions.biometrics.fingerprint.FingerprintEnrollEnrolling.6
        @Override // java.lang.Runnable
        public void run() {
            FingerprintEnrollEnrolling fingerprintEnrollEnrolling = FingerprintEnrollEnrolling.this;
            fingerprintEnrollEnrolling.showError(fingerprintEnrollEnrolling.getString(R.string.security_settings_fingerprint_enroll_lift_touch_again));
        }
    };

    @Override // com.android.settingslib.core.instrumentation.Instrumentable
    public int getMetricsCategory() {
        return 240;
    }

    @Override // com.android.car.developeroptions.biometrics.BiometricsEnrollEnrolling
    protected boolean shouldStartAutomatically() {
        return true;
    }

    static /* synthetic */ int access$008(FingerprintEnrollEnrolling fingerprintEnrollEnrolling) {
        int i = fingerprintEnrollEnrolling.mIconTouchCount;
        fingerprintEnrollEnrolling.mIconTouchCount = i + 1;
        return i;
    }

    public static class FingerprintErrorDialog extends BiometricErrorDialog {
        @Override // com.android.settingslib.core.instrumentation.Instrumentable
        public int getMetricsCategory() {
            return 569;
        }

        @Override // com.android.car.developeroptions.biometrics.BiometricErrorDialog
        public int getOkButtonTextResId() {
            return R.string.security_settings_fingerprint_enroll_dialog_ok;
        }

        @Override // com.android.car.developeroptions.biometrics.BiometricErrorDialog
        public int getTitleResId() {
            return R.string.security_settings_fingerprint_enroll_error_dialog_title;
        }

        static FingerprintErrorDialog newInstance(CharSequence charSequence, int i) {
            FingerprintErrorDialog fingerprintErrorDialog = new FingerprintErrorDialog();
            Bundle bundle = new Bundle();
            bundle.putCharSequence("error_msg", charSequence);
            bundle.putInt("error_id", i);
            fingerprintErrorDialog.setArguments(bundle);
            return fingerprintErrorDialog;
        }
    }

    @Override // com.android.car.developeroptions.biometrics.BiometricEnrollBase, com.android.car.developeroptions.core.InstrumentedActivity, com.android.settingslib.core.lifecycle.ObservableActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.fingerprint_enroll_enrolling);
        setHeaderText(R.string.security_settings_fingerprint_enroll_repeat_title);
        this.mStartMessage = (TextView) findViewById(R.id.start_message);
        this.mRepeatMessage = (TextView) findViewById(R.id.repeat_message);
        this.mErrorText = (TextView) findViewById(R.id.error_text);
        this.mProgressBar = (ProgressBar) findViewById(R.id.fingerprint_progress_bar);
        this.mVibrator = (Vibrator) getSystemService(Vibrator.class);
        FooterBarMixin footerBarMixin = (FooterBarMixin) getLayout().getMixin(FooterBarMixin.class);
        this.mFooterBarMixin = footerBarMixin;
        FooterButton.Builder builder = new FooterButton.Builder(this);
        builder.setText(R.string.security_settings_fingerprint_enroll_enrolling_skip);
        builder.setListener(new View.OnClickListener() { // from class: com.android.car.developeroptions.biometrics.fingerprint.-$$Lambda$FingerprintEnrollEnrolling$ZVZ11cWOavbBIKfbKgvgmWrJ9fE
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.onSkipButtonClick(view);
            }
        });
        builder.setButtonType(7);
        builder.setTheme(R.style.SudGlifButton_Secondary);
        footerBarMixin.setSecondaryButton(builder.build());
        LayerDrawable layerDrawable = (LayerDrawable) this.mProgressBar.getBackground();
        this.mIconAnimationDrawable = (AnimatedVectorDrawable) layerDrawable.findDrawableByLayerId(R.id.fingerprint_animation);
        this.mIconBackgroundBlinksDrawable = (AnimatedVectorDrawable) layerDrawable.findDrawableByLayerId(R.id.fingerprint_background);
        this.mIconAnimationDrawable.registerAnimationCallback(this.mIconAnimationCallback);
        this.mFastOutSlowInInterpolator = AnimationUtils.loadInterpolator(this, android.R.interpolator.fast_out_slow_in);
        this.mLinearOutSlowInInterpolator = AnimationUtils.loadInterpolator(this, android.R.interpolator.linear_out_slow_in);
        this.mFastOutLinearInInterpolator = AnimationUtils.loadInterpolator(this, android.R.interpolator.fast_out_linear_in);
        this.mProgressBar.setOnTouchListener(new View.OnTouchListener() { // from class: com.android.car.developeroptions.biometrics.fingerprint.FingerprintEnrollEnrolling.1
            @Override // android.view.View.OnTouchListener
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getActionMasked() == 0) {
                    FingerprintEnrollEnrolling.access$008(FingerprintEnrollEnrolling.this);
                    if (FingerprintEnrollEnrolling.this.mIconTouchCount == 3) {
                        FingerprintEnrollEnrolling.this.showIconTouchDialog();
                    } else {
                        FingerprintEnrollEnrolling.this.mProgressBar.postDelayed(FingerprintEnrollEnrolling.this.mShowDialogRunnable, 500L);
                    }
                } else if (motionEvent.getActionMasked() == 3 || motionEvent.getActionMasked() == 1) {
                    FingerprintEnrollEnrolling.this.mProgressBar.removeCallbacks(FingerprintEnrollEnrolling.this.mShowDialogRunnable);
                }
                return true;
            }
        });
        this.mRestoring = bundle != null;
    }

    @Override // com.android.car.developeroptions.biometrics.BiometricsEnrollEnrolling
    protected BiometricEnrollSidecar getSidecar() {
        return new FingerprintEnrollSidecar();
    }

    @Override // com.android.car.developeroptions.biometrics.BiometricsEnrollEnrolling, com.android.settingslib.core.lifecycle.ObservableActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onStart() {
        super.onStart();
        updateProgress(false);
        updateDescription();
        if (this.mRestoring) {
            startIconAnimation();
        }
    }

    @Override // android.app.Activity
    public void onEnterAnimationComplete() {
        super.onEnterAnimationComplete();
        this.mAnimationCancelled = false;
        startIconAnimation();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void startIconAnimation() {
        this.mIconAnimationDrawable.start();
    }

    private void stopIconAnimation() {
        this.mAnimationCancelled = true;
        this.mIconAnimationDrawable.stop();
    }

    @Override // com.android.car.developeroptions.biometrics.BiometricsEnrollEnrolling, com.android.settingslib.core.lifecycle.ObservableActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onStop() {
        super.onStop();
        stopIconAnimation();
    }

    private void animateProgress(int i) {
        ObjectAnimator objectAnimator = this.mProgressAnim;
        if (objectAnimator != null) {
            objectAnimator.cancel();
        }
        ProgressBar progressBar = this.mProgressBar;
        ObjectAnimator objectAnimatorOfInt = ObjectAnimator.ofInt(progressBar, "progress", progressBar.getProgress(), i);
        objectAnimatorOfInt.addListener(this.mProgressAnimationListener);
        objectAnimatorOfInt.setInterpolator(this.mFastOutSlowInInterpolator);
        objectAnimatorOfInt.setDuration(250L);
        objectAnimatorOfInt.start();
        this.mProgressAnim = objectAnimatorOfInt;
    }

    private void animateFlash() {
        this.mIconBackgroundBlinksDrawable.start();
    }

    @Override // com.android.car.developeroptions.biometrics.BiometricsEnrollEnrolling
    protected Intent getFinishIntent() {
        return new Intent(this, (Class<?>) FingerprintEnrollFinish.class);
    }

    private void updateDescription() {
        if (this.mSidecar.getEnrollmentSteps() == -1) {
            this.mStartMessage.setVisibility(0);
            this.mRepeatMessage.setVisibility(4);
        } else {
            this.mStartMessage.setVisibility(4);
            this.mRepeatMessage.setVisibility(0);
        }
    }

    @Override // com.android.car.developeroptions.biometrics.BiometricEnrollSidecar.Listener
    public void onEnrollmentHelp(int i, CharSequence charSequence) {
        if (TextUtils.isEmpty(charSequence)) {
            return;
        }
        this.mErrorText.removeCallbacks(this.mTouchAgainRunnable);
        showError(charSequence);
    }

    @Override // com.android.car.developeroptions.biometrics.BiometricEnrollSidecar.Listener
    public void onEnrollmentError(int i, CharSequence charSequence) {
        showErrorDialog(getText(i != 3 ? R.string.security_settings_fingerprint_enroll_error_generic_dialog_message : R.string.security_settings_fingerprint_enroll_error_timeout_dialog_message), i);
        stopIconAnimation();
        this.mErrorText.removeCallbacks(this.mTouchAgainRunnable);
    }

    @Override // com.android.car.developeroptions.biometrics.BiometricEnrollSidecar.Listener
    public void onEnrollmentProgressChange(int i, int i2) {
        updateProgress(true);
        updateDescription();
        clearError();
        animateFlash();
        this.mErrorText.removeCallbacks(this.mTouchAgainRunnable);
        this.mErrorText.postDelayed(this.mTouchAgainRunnable, 2500L);
    }

    private void updateProgress(boolean z) {
        int progress = getProgress(this.mSidecar.getEnrollmentSteps(), this.mSidecar.getEnrollmentRemaining());
        if (z) {
            animateProgress(progress);
            return;
        }
        this.mProgressBar.setProgress(progress);
        if (progress >= 10000) {
            this.mDelayedFinishRunnable.run();
        }
    }

    private int getProgress(int i, int i2) {
        if (i == -1) {
            return 0;
        }
        int i3 = i + 1;
        return (Math.max(0, i3 - i2) * 10000) / i3;
    }

    private void showErrorDialog(CharSequence charSequence, int i) {
        FingerprintErrorDialog.newInstance(charSequence, i).show(getSupportFragmentManager(), FingerprintErrorDialog.class.getName());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showIconTouchDialog() {
        this.mIconTouchCount = 0;
        new IconTouchDialog().show(getSupportFragmentManager(), null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showError(CharSequence charSequence) {
        this.mErrorText.setText(charSequence);
        if (this.mErrorText.getVisibility() == 4) {
            this.mErrorText.setVisibility(0);
            this.mErrorText.setTranslationY(getResources().getDimensionPixelSize(R.dimen.fingerprint_error_text_appear_distance));
            this.mErrorText.setAlpha(0.0f);
            this.mErrorText.animate().alpha(1.0f).translationY(0.0f).setDuration(200L).setInterpolator(this.mLinearOutSlowInInterpolator).start();
        } else {
            this.mErrorText.animate().cancel();
            this.mErrorText.setAlpha(1.0f);
            this.mErrorText.setTranslationY(0.0f);
        }
        if (isResumed()) {
            this.mVibrator.vibrate(VIBRATE_EFFECT_ERROR, FINGERPRINT_ENROLLING_SONFICATION_ATTRIBUTES);
        }
    }

    private void clearError() {
        if (this.mErrorText.getVisibility() == 0) {
            this.mErrorText.animate().alpha(0.0f).translationY(getResources().getDimensionPixelSize(R.dimen.fingerprint_error_text_disappear_distance)).setDuration(100L).setInterpolator(this.mFastOutLinearInInterpolator).withEndAction(new Runnable() { // from class: com.android.car.developeroptions.biometrics.fingerprint.-$$Lambda$FingerprintEnrollEnrolling$SKHLvaBhOTwPRXWjRcQXyrIQ4ec
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$clearError$0$FingerprintEnrollEnrolling();
                }
            }).start();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: lambda$clearError$0, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void lambda$clearError$0$FingerprintEnrollEnrolling() {
        this.mErrorText.setVisibility(4);
    }

    public static class IconTouchDialog extends InstrumentedDialogFragment {
        @Override // com.android.settingslib.core.instrumentation.Instrumentable
        public int getMetricsCategory() {
            return 568;
        }

        @Override // androidx.fragment.app.DialogFragment
        public Dialog onCreateDialog(Bundle bundle) {
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setTitle(R.string.security_settings_fingerprint_enroll_touch_dialog_title);
            builder.setMessage(R.string.security_settings_fingerprint_enroll_touch_dialog_message);
            builder.setPositiveButton(R.string.security_settings_fingerprint_enroll_dialog_ok, new DialogInterface.OnClickListener(this) { // from class: com.android.car.developeroptions.biometrics.fingerprint.FingerprintEnrollEnrolling.IconTouchDialog.1
                @Override // android.content.DialogInterface.OnClickListener
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                }
            });
            return builder.create();
        }
    }
}
