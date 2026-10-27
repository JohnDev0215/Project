package com.android.inputmethod.latin.setup;

import android.R;
import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.VideoView;
import com.android.inputmethod.compat.TextViewCompatUtils;
import com.android.inputmethod.compat.ViewCompatUtils;
import com.android.inputmethod.latin.settings.SettingsActivity;
import com.android.inputmethod.latin.utils.LeakGuardHandlerWrapper;
import com.android.inputmethod.latin.utils.UncachedInputMethodManagerUtils;
import java.util.ArrayList;
import java.util.Iterator;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public final class SetupWizardActivity extends Activity implements View.OnClickListener {
    private static final boolean ENABLE_WELCOME_VIDEO = true;
    private static final boolean FORCE_TO_SHOW_WELCOME_SCREEN = false;
    private static final String STATE_STEP = "step";
    private static final int STEP_1 = 1;
    private static final int STEP_2 = 2;
    private static final int STEP_3 = 3;
    private static final int STEP_BACK_FROM_IME_SETTINGS = 5;
    private static final int STEP_LAUNCHING_IME_SETTINGS = 4;
    private static final int STEP_WELCOME = 0;
    static final String TAG = "SetupWizardActivity";
    private TextView mActionFinish;
    private View mActionNext;
    private View mActionStart;
    private SettingsPoolingHandler mHandler;
    private InputMethodManager mImm;
    private boolean mNeedsToAdjustStepNumberToSystemState;
    private View mSetupScreen;
    private SetupStepGroup mSetupStepGroup;
    private View mSetupWizard;
    private TextView mStep1Bullet;
    private int mStepNumber;
    private ImageView mWelcomeImageView;
    private View mWelcomeScreen;
    private Uri mWelcomeVideoUri;
    private VideoView mWelcomeVideoView;

    private static boolean isInSetupSteps(final int stepNumber) {
        return stepNumber >= 1 && stepNumber <= 3;
    }

    private static final class SettingsPoolingHandler extends LeakGuardHandlerWrapper<SetupWizardActivity> {
        private static final long IME_SETTINGS_POLLING_INTERVAL = 200;
        private static final int MSG_POLLING_IME_SETTINGS = 0;
        private final InputMethodManager mImmInHandler;

        public SettingsPoolingHandler(@Nonnull final SetupWizardActivity ownerInstance, final InputMethodManager imm) {
            super(ownerInstance);
            this.mImmInHandler = imm;
        }

        @Override // android.os.Handler
        public void handleMessage(final Message msg) {
            SetupWizardActivity ownerInstance = getOwnerInstance();
            if (ownerInstance != null && msg.what == 0) {
                if (UncachedInputMethodManagerUtils.isThisImeEnabled(ownerInstance, this.mImmInHandler)) {
                    ownerInstance.invokeSetupWizardOfThisIme();
                } else {
                    startPollingImeSettings();
                }
            }
        }

        public void startPollingImeSettings() {
            sendMessageDelayed(obtainMessage(0), IME_SETTINGS_POLLING_INTERVAL);
        }

        public void cancelPollingImeSettings() {
            removeMessages(0);
        }
    }

    @Override // android.app.Activity
    protected void onCreate(final Bundle savedInstanceState) {
        setTheme(R.style.Theme.Translucent.NoTitleBar);
        super.onCreate(savedInstanceState);
        this.mImm = (InputMethodManager) getSystemService("input_method");
        this.mHandler = new SettingsPoolingHandler(this, this.mImm);
        setContentView(com.android.inputmethod.latin.R.layout.setup_wizard);
        this.mSetupWizard = findViewById(com.android.inputmethod.latin.R.id.setup_wizard);
        if (savedInstanceState == null) {
            this.mStepNumber = determineSetupStepNumberFromLauncher();
        } else {
            this.mStepNumber = savedInstanceState.getInt(STATE_STEP);
        }
        String string = getResources().getString(getApplicationInfo().labelRes);
        this.mWelcomeScreen = findViewById(com.android.inputmethod.latin.R.id.setup_welcome_screen);
        ((TextView) findViewById(com.android.inputmethod.latin.R.id.setup_welcome_title)).setText(getString(com.android.inputmethod.latin.R.string.setup_welcome_title, new Object[]{string}));
        this.mSetupScreen = findViewById(com.android.inputmethod.latin.R.id.setup_steps_screen);
        ((TextView) findViewById(com.android.inputmethod.latin.R.id.setup_title)).setText(getString(com.android.inputmethod.latin.R.string.setup_steps_title, new Object[]{string}));
        this.mSetupStepGroup = new SetupStepGroup((SetupStepIndicatorView) findViewById(com.android.inputmethod.latin.R.id.setup_step_indicator));
        TextView textView = (TextView) findViewById(com.android.inputmethod.latin.R.id.setup_step1_bullet);
        this.mStep1Bullet = textView;
        textView.setOnClickListener(this);
        SetupStep setupStep = new SetupStep(1, string, this.mStep1Bullet, findViewById(com.android.inputmethod.latin.R.id.setup_step1), com.android.inputmethod.latin.R.string.setup_step1_title, com.android.inputmethod.latin.R.string.setup_step1_instruction, com.android.inputmethod.latin.R.string.setup_step1_finished_instruction, com.android.inputmethod.latin.R.drawable.ic_setup_step1, com.android.inputmethod.latin.R.string.setup_step1_action);
        final SettingsPoolingHandler settingsPoolingHandler = this.mHandler;
        setupStep.setAction(new Runnable() { // from class: com.android.inputmethod.latin.setup.SetupWizardActivity.1
            @Override // java.lang.Runnable
            public void run() {
                SetupWizardActivity.this.invokeLanguageAndInputSettings();
                settingsPoolingHandler.startPollingImeSettings();
            }
        });
        this.mSetupStepGroup.addStep(setupStep);
        SetupStep setupStep2 = new SetupStep(2, string, (TextView) findViewById(com.android.inputmethod.latin.R.id.setup_step2_bullet), findViewById(com.android.inputmethod.latin.R.id.setup_step2), com.android.inputmethod.latin.R.string.setup_step2_title, com.android.inputmethod.latin.R.string.setup_step2_instruction, 0, com.android.inputmethod.latin.R.drawable.ic_setup_step2, com.android.inputmethod.latin.R.string.setup_step2_action);
        setupStep2.setAction(new Runnable() { // from class: com.android.inputmethod.latin.setup.SetupWizardActivity.2
            @Override // java.lang.Runnable
            public void run() {
                SetupWizardActivity.this.invokeInputMethodPicker();
            }
        });
        this.mSetupStepGroup.addStep(setupStep2);
        SetupStep setupStep3 = new SetupStep(3, string, (TextView) findViewById(com.android.inputmethod.latin.R.id.setup_step3_bullet), findViewById(com.android.inputmethod.latin.R.id.setup_step3), com.android.inputmethod.latin.R.string.setup_step3_title, com.android.inputmethod.latin.R.string.setup_step3_instruction, 0, com.android.inputmethod.latin.R.drawable.ic_setup_step3, com.android.inputmethod.latin.R.string.setup_step3_action);
        setupStep3.setAction(new Runnable() { // from class: com.android.inputmethod.latin.setup.SetupWizardActivity.3
            @Override // java.lang.Runnable
            public void run() {
                SetupWizardActivity.this.invokeSubtypeEnablerOfThisIme();
            }
        });
        this.mSetupStepGroup.addStep(setupStep3);
        this.mWelcomeVideoUri = new Uri.Builder().scheme("android.resource").authority(getPackageName()).path(Integer.toString(com.android.inputmethod.latin.R.raw.setup_welcome_video)).build();
        final VideoView videoView = (VideoView) findViewById(com.android.inputmethod.latin.R.id.setup_welcome_video);
        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() { // from class: com.android.inputmethod.latin.setup.SetupWizardActivity.4
            @Override // android.media.MediaPlayer.OnPreparedListener
            public void onPrepared(final MediaPlayer mp) {
                videoView.setBackgroundResource(0);
                mp.setLooping(true);
            }
        });
        videoView.setOnErrorListener(new MediaPlayer.OnErrorListener() { // from class: com.android.inputmethod.latin.setup.SetupWizardActivity.5
            @Override // android.media.MediaPlayer.OnErrorListener
            public boolean onError(final MediaPlayer mp, final int what, final int extra) {
                Log.e(SetupWizardActivity.TAG, "Playing welcome video causes error: what=" + what + " extra=" + extra);
                SetupWizardActivity.this.hideWelcomeVideoAndShowWelcomeImage();
                return true;
            }
        });
        this.mWelcomeVideoView = videoView;
        this.mWelcomeImageView = (ImageView) findViewById(com.android.inputmethod.latin.R.id.setup_welcome_image);
        View viewFindViewById = findViewById(com.android.inputmethod.latin.R.id.setup_start_label);
        this.mActionStart = viewFindViewById;
        viewFindViewById.setOnClickListener(this);
        View viewFindViewById2 = findViewById(com.android.inputmethod.latin.R.id.setup_next);
        this.mActionNext = viewFindViewById2;
        viewFindViewById2.setOnClickListener(this);
        TextView textView2 = (TextView) findViewById(com.android.inputmethod.latin.R.id.setup_finish);
        this.mActionFinish = textView2;
        TextViewCompatUtils.setCompoundDrawablesRelativeWithIntrinsicBounds(textView2, getResources().getDrawable(com.android.inputmethod.latin.R.drawable.ic_setup_finish), null, null, null);
        this.mActionFinish.setOnClickListener(this);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(final View v) {
        if (v == this.mActionFinish) {
            finish();
            return;
        }
        int iDetermineSetupStepNumber = determineSetupStepNumber();
        int i = 1;
        if (v != this.mActionStart) {
            if (v == this.mActionNext) {
                i = 1 + this.mStepNumber;
            } else if (v != this.mStep1Bullet || iDetermineSetupStepNumber != 2) {
                i = this.mStepNumber;
            }
        }
        if (this.mStepNumber != i) {
            this.mStepNumber = i;
            updateSetupStepView();
        }
    }

    void invokeSetupWizardOfThisIme() {
        Intent intent = new Intent();
        intent.setClass(this, SetupWizardActivity.class);
        intent.setFlags(606076928);
        startActivity(intent);
        this.mNeedsToAdjustStepNumberToSystemState = true;
    }

    private void invokeSettingsOfThisIme() {
        Intent intent = new Intent();
        intent.setClass(this, SettingsActivity.class);
        intent.setFlags(69206016);
        intent.putExtra(SettingsActivity.EXTRA_ENTRY_KEY, SettingsActivity.EXTRA_ENTRY_VALUE_APP_ICON);
        startActivity(intent);
    }

    void invokeLanguageAndInputSettings() {
        Intent intent = new Intent();
        intent.setAction("android.settings.INPUT_METHOD_SETTINGS");
        intent.addCategory("android.intent.category.DEFAULT");
        startActivity(intent);
        this.mNeedsToAdjustStepNumberToSystemState = true;
    }

    void invokeInputMethodPicker() {
        this.mImm.showInputMethodPicker();
        this.mNeedsToAdjustStepNumberToSystemState = true;
    }

    void invokeSubtypeEnablerOfThisIme() {
        InputMethodInfo inputMethodInfoOf = UncachedInputMethodManagerUtils.getInputMethodInfoOf(getPackageName(), this.mImm);
        if (inputMethodInfoOf == null) {
            return;
        }
        Intent intent = new Intent();
        intent.setAction("android.settings.INPUT_METHOD_SUBTYPE_SETTINGS");
        intent.addCategory("android.intent.category.DEFAULT");
        intent.putExtra("input_method_id", inputMethodInfoOf.getId());
        startActivity(intent);
    }

    private int determineSetupStepNumberFromLauncher() {
        int iDetermineSetupStepNumber = determineSetupStepNumber();
        if (iDetermineSetupStepNumber == 1) {
            return 0;
        }
        if (iDetermineSetupStepNumber == 3) {
            return 4;
        }
        return iDetermineSetupStepNumber;
    }

    private int determineSetupStepNumber() {
        this.mHandler.cancelPollingImeSettings();
        if (UncachedInputMethodManagerUtils.isThisImeEnabled(this, this.mImm)) {
            return !UncachedInputMethodManagerUtils.isThisImeCurrent(this, this.mImm) ? 2 : 3;
        }
        return 1;
    }

    @Override // android.app.Activity
    protected void onSaveInstanceState(final Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(STATE_STEP, this.mStepNumber);
    }

    @Override // android.app.Activity
    protected void onRestoreInstanceState(final Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        this.mStepNumber = savedInstanceState.getInt(STATE_STEP);
    }

    @Override // android.app.Activity
    protected void onRestart() {
        super.onRestart();
        if (isInSetupSteps(this.mStepNumber)) {
            this.mStepNumber = determineSetupStepNumber();
        }
    }

    @Override // android.app.Activity
    protected void onResume() {
        super.onResume();
        int i = this.mStepNumber;
        if (i == 4) {
            this.mSetupWizard.setVisibility(4);
            invokeSettingsOfThisIme();
            this.mStepNumber = 5;
        } else if (i == 5) {
            finish();
        } else {
            updateSetupStepView();
        }
    }

    @Override // android.app.Activity
    public void onBackPressed() {
        if (this.mStepNumber == 1) {
            this.mStepNumber = 0;
            updateSetupStepView();
        } else {
            super.onBackPressed();
        }
    }

    void hideWelcomeVideoAndShowWelcomeImage() {
        this.mWelcomeVideoView.setVisibility(8);
        this.mWelcomeImageView.setImageResource(com.android.inputmethod.latin.R.raw.setup_welcome_image);
        this.mWelcomeImageView.setVisibility(0);
    }

    private void showAndStartWelcomeVideo() {
        this.mWelcomeVideoView.setVisibility(0);
        this.mWelcomeVideoView.setVideoURI(this.mWelcomeVideoUri);
        this.mWelcomeVideoView.start();
    }

    private void hideAndStopWelcomeVideo() {
        this.mWelcomeVideoView.stopPlayback();
        this.mWelcomeVideoView.setVisibility(8);
    }

    @Override // android.app.Activity
    protected void onPause() {
        hideAndStopWelcomeVideo();
        super.onPause();
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public void onWindowFocusChanged(final boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus && this.mNeedsToAdjustStepNumberToSystemState) {
            this.mNeedsToAdjustStepNumberToSystemState = false;
            this.mStepNumber = determineSetupStepNumber();
            updateSetupStepView();
        }
    }

    private void updateSetupStepView() {
        this.mSetupWizard.setVisibility(0);
        boolean z = this.mStepNumber == 0;
        this.mWelcomeScreen.setVisibility(z ? 0 : 8);
        this.mSetupScreen.setVisibility(z ? 8 : 0);
        if (z) {
            showAndStartWelcomeVideo();
            return;
        }
        hideAndStopWelcomeVideo();
        boolean z2 = this.mStepNumber < determineSetupStepNumber();
        this.mSetupStepGroup.enableStep(this.mStepNumber, z2);
        this.mActionNext.setVisibility(z2 ? 0 : 8);
        this.mActionFinish.setVisibility(this.mStepNumber != 3 ? 8 : 0);
    }

    static final class SetupStep implements View.OnClickListener {
        private Runnable mAction;
        private final TextView mActionLabel;
        private final int mActivatedColor;
        private final TextView mBulletView;
        private final int mDeactivatedColor;
        private final String mFinishedInstruction;
        private final String mInstruction;
        public final int mStepNo;
        private final View mStepView;

        public SetupStep(final int stepNo, final String applicationName, final TextView bulletView, final View stepView, final int title, final int instruction, final int finishedInstruction, final int actionIcon, final int actionLabel) {
            this.mStepNo = stepNo;
            this.mStepView = stepView;
            this.mBulletView = bulletView;
            Resources resources = stepView.getResources();
            this.mActivatedColor = resources.getColor(com.android.inputmethod.latin.R.color.setup_text_action);
            this.mDeactivatedColor = resources.getColor(com.android.inputmethod.latin.R.color.setup_text_dark);
            ((TextView) stepView.findViewById(com.android.inputmethod.latin.R.id.setup_step_title)).setText(resources.getString(title, applicationName));
            this.mInstruction = instruction == 0 ? null : resources.getString(instruction, applicationName);
            this.mFinishedInstruction = finishedInstruction == 0 ? null : resources.getString(finishedInstruction, applicationName);
            TextView textView = (TextView) stepView.findViewById(com.android.inputmethod.latin.R.id.setup_step_action_label);
            this.mActionLabel = textView;
            textView.setText(resources.getString(actionLabel));
            if (actionIcon == 0) {
                int paddingEnd = ViewCompatUtils.getPaddingEnd(textView);
                ViewCompatUtils.setPaddingRelative(textView, paddingEnd, 0, paddingEnd, 0);
            } else {
                TextViewCompatUtils.setCompoundDrawablesRelativeWithIntrinsicBounds(textView, resources.getDrawable(actionIcon), null, null, null);
            }
        }

        public void setEnabled(final boolean enabled, final boolean isStepActionAlreadyDone) {
            this.mStepView.setVisibility(enabled ? 0 : 8);
            this.mBulletView.setTextColor(enabled ? this.mActivatedColor : this.mDeactivatedColor);
            ((TextView) this.mStepView.findViewById(com.android.inputmethod.latin.R.id.setup_step_instruction)).setText(isStepActionAlreadyDone ? this.mFinishedInstruction : this.mInstruction);
            this.mActionLabel.setVisibility(isStepActionAlreadyDone ? 8 : 0);
        }

        public void setAction(final Runnable action) {
            this.mActionLabel.setOnClickListener(this);
            this.mAction = action;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(final View v) {
            Runnable runnable;
            if (v != this.mActionLabel || (runnable = this.mAction) == null) {
                return;
            }
            runnable.run();
        }
    }

    static final class SetupStepGroup {
        private final ArrayList<SetupStep> mGroup = new ArrayList<>();
        private final SetupStepIndicatorView mIndicatorView;

        public SetupStepGroup(final SetupStepIndicatorView indicatorView) {
            this.mIndicatorView = indicatorView;
        }

        public void addStep(final SetupStep step) {
            this.mGroup.add(step);
        }

        public void enableStep(final int enableStepNo, final boolean isStepActionAlreadyDone) {
            Iterator<SetupStep> it = this.mGroup.iterator();
            while (true) {
                boolean z = true;
                if (it.hasNext()) {
                    SetupStep next = it.next();
                    if (next.mStepNo != enableStepNo) {
                        z = false;
                    }
                    next.setEnabled(z, isStepActionAlreadyDone);
                } else {
                    this.mIndicatorView.setIndicatorPosition(enableStepNo - 1, this.mGroup.size());
                    return;
                }
            }
        }
    }
}
