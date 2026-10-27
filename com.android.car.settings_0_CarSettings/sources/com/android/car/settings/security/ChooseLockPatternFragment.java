package com.android.car.settings.security;

import android.os.Bundle;
import android.os.UserHandle;
import android.view.View;
import android.widget.TextView;
import com.android.car.settings.R;
import com.android.car.settings.common.BaseFragment;
import com.android.car.settings.common.Logger;
import com.android.car.ui.toolbar.MenuItem;
import com.android.internal.widget.LockPatternView;
import com.android.internal.widget.LockscreenCredential;
import com.google.android.collect.Lists;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public class ChooseLockPatternFragment extends BaseFragment {
    private static final String FRAGMENT_TAG_SAVE_PATTERN_WORKER = "save_pattern_worker";
    private static final int ID_EMPTY_MESSAGE = -1;
    private static final Logger LOG = new Logger((Class<?>) ChooseLockPatternFragment.class);
    private static final String STATE_CHOSEN_PATTERN = "state_chosen_pattern";
    private static final String STATE_UI_STAGE = "state_ui_stage";
    private LockscreenCredential mChosenPattern;
    private LockscreenCredential mCurrentCredential;
    private LockPatternView mLockPatternView;
    private TextView mMessageText;
    private MenuItem mPrimaryButton;
    private SaveLockWorker mSaveLockWorker;
    private MenuItem mSecondaryButton;
    private int mUserId;
    private int mWrongPatternClearTimeOut;
    private final List<LockPatternView.Cell> mAnimatePattern = Collections.unmodifiableList(Lists.newArrayList(new LockPatternView.Cell[]{LockPatternView.Cell.of(0, 0), LockPatternView.Cell.of(0, 1), LockPatternView.Cell.of(1, 1), LockPatternView.Cell.of(2, 1)}));
    private Stage mUiStage = Stage.Introduction;
    private Runnable mClearPatternRunnable = new Runnable() { // from class: com.android.car.settings.security.-$$Lambda$ChooseLockPatternFragment$nc9n6UhTZuLdmL17KSbJsJPxqeg
        @Override // java.lang.Runnable
        public final void run() {
            this.f$0.lambda$new$0$ChooseLockPatternFragment();
        }
    };
    private final LockPatternView.OnPatternListener mChooseNewLockPatternListener = new LockPatternView.OnPatternListener() { // from class: com.android.car.settings.security.ChooseLockPatternFragment.1
        public void onPatternStart() {
            ChooseLockPatternFragment.this.mLockPatternView.removeCallbacks(ChooseLockPatternFragment.this.mClearPatternRunnable);
            ChooseLockPatternFragment.this.updateUIWhenPatternInProgress();
        }

        public void onPatternCleared() {
            ChooseLockPatternFragment.this.mLockPatternView.removeCallbacks(ChooseLockPatternFragment.this.mClearPatternRunnable);
        }

        public void onPatternDetected(List<LockPatternView.Cell> pattern) {
            int i = AnonymousClass2.$SwitchMap$com$android$car$settings$security$ChooseLockPatternFragment$Stage[ChooseLockPatternFragment.this.mUiStage.ordinal()];
            if (i == 1 || i == 2) {
                handlePatternEntered(pattern);
                return;
            }
            if (i != 3 && i != 4) {
                throw new IllegalStateException("Unexpected stage " + ChooseLockPatternFragment.this.mUiStage + " when entering the pattern.");
            }
            handleConfirmPattern(pattern);
        }

        public void onPatternCellAdded(List<LockPatternView.Cell> pattern) {
        }

        private void handleConfirmPattern(List<LockPatternView.Cell> pattern) {
            if (ChooseLockPatternFragment.this.mChosenPattern == null) {
                throw new IllegalStateException("null chosen pattern in stage 'need to confirm");
            }
            LockscreenCredential credential = LockscreenCredential.createPattern(pattern);
            try {
                if (ChooseLockPatternFragment.this.mChosenPattern.equals(credential)) {
                    ChooseLockPatternFragment.this.updateStage(Stage.ChoiceConfirmed);
                } else {
                    ChooseLockPatternFragment.this.updateStage(Stage.ConfirmWrong);
                }
                if (credential != null) {
                    credential.close();
                }
            } catch (Throwable th) {
                if (credential != null) {
                    try {
                        credential.close();
                    } catch (Throwable th2) {
                        th.addSuppressed(th2);
                    }
                }
                throw th;
            }
        }

        private void handlePatternEntered(List<LockPatternView.Cell> pattern) {
            if (pattern.size() < 4) {
                ChooseLockPatternFragment.this.updateStage(Stage.ChoiceTooShort);
                return;
            }
            ChooseLockPatternFragment.this.mChosenPattern = LockscreenCredential.createPattern(pattern);
            ChooseLockPatternFragment.this.updateStage(Stage.FirstChoiceValid);
        }
    };

    public /* synthetic */ void lambda$new$0$ChooseLockPatternFragment() {
        this.mLockPatternView.clearPattern();
    }

    public static ChooseLockPatternFragment newInstance() {
        ChooseLockPatternFragment patternFragment = new ChooseLockPatternFragment();
        return patternFragment;
    }

    @Override // com.android.car.settings.common.BaseFragment
    public List<MenuItem> getToolbarMenuItems() {
        return Arrays.asList(this.mPrimaryButton, this.mSecondaryButton);
    }

    @Override // com.android.car.settings.common.BaseFragment
    protected int getLayoutId() {
        return R.layout.choose_lock_pattern;
    }

    @Override // com.android.car.settings.common.BaseFragment
    protected int getTitleId() {
        return R.string.security_lock_pattern;
    }

    @Override // androidx.fragment.app.Fragment
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mWrongPatternClearTimeOut = getResources().getInteger(R.integer.clear_content_timeout_ms);
        this.mUserId = UserHandle.myUserId();
        Bundle args = getArguments();
        if (args != null) {
            LockscreenCredential parcelable = args.getParcelable(PasswordHelper.EXTRA_CURRENT_SCREEN_LOCK);
            this.mCurrentCredential = parcelable;
            if (parcelable != null) {
                this.mCurrentCredential = parcelable.duplicate();
            }
        }
        if (savedInstanceState != null) {
            this.mUiStage = Stage.values()[savedInstanceState.getInt(STATE_UI_STAGE)];
            this.mChosenPattern = savedInstanceState.getParcelable(STATE_CHOSEN_PATTERN);
        }
        this.mPrimaryButton = new MenuItem.Builder(getContext()).setOnClickListener(new MenuItem.OnClickListener() { // from class: com.android.car.settings.security.-$$Lambda$ChooseLockPatternFragment$zCY8LqnKUjk-VxmJNyX2GKAYhfU
            @Override // com.android.car.ui.toolbar.MenuItem.OnClickListener
            public final void onClick(MenuItem menuItem) {
                this.f$0.lambda$onCreate$1$ChooseLockPatternFragment(menuItem);
            }
        }).build();
        this.mSecondaryButton = new MenuItem.Builder(getContext()).setOnClickListener(new MenuItem.OnClickListener() { // from class: com.android.car.settings.security.-$$Lambda$ChooseLockPatternFragment$pteRr58SSitt36qsCbca-mFjaQg
            @Override // com.android.car.ui.toolbar.MenuItem.OnClickListener
            public final void onClick(MenuItem menuItem) {
                this.f$0.lambda$onCreate$2$ChooseLockPatternFragment(menuItem);
            }
        }).build();
    }

    public /* synthetic */ void lambda$onCreate$1$ChooseLockPatternFragment(MenuItem i) {
        handlePrimaryButtonClick();
    }

    public /* synthetic */ void lambda$onCreate$2$ChooseLockPatternFragment(MenuItem i) {
        handleSecondaryButtonClick();
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        TextView textView = (TextView) view.findViewById(R.id.description_text);
        this.mMessageText = textView;
        textView.setText(getString(R.string.choose_lock_pattern_message));
        LockPatternView lockPatternViewFindViewById = view.findViewById(R.id.lockPattern);
        this.mLockPatternView = lockPatternViewFindViewById;
        lockPatternViewFindViewById.setVisibility(0);
        this.mLockPatternView.setEnabled(true);
        this.mLockPatternView.setFadePattern(false);
        this.mLockPatternView.clearPattern();
        this.mLockPatternView.setOnPatternListener(this.mChooseNewLockPatternListener);
        if (savedInstanceState != null) {
            this.mSaveLockWorker = (SaveLockWorker) getFragmentManager().findFragmentByTag(FRAGMENT_TAG_SAVE_PATTERN_WORKER);
        }
    }

    @Override // com.android.car.settings.common.BaseFragment, androidx.fragment.app.Fragment
    public void onStart() {
        super.onStart();
        updateStage(this.mUiStage);
        if (this.mSaveLockWorker != null) {
            setPrimaryButtonEnabled(true);
            this.mSaveLockWorker.setListener(new $$Lambda$SYjii9RTym20N39J7M61X9AAwp4(this));
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(STATE_UI_STAGE, this.mUiStage.ordinal());
        outState.putParcelable(STATE_CHOSEN_PATTERN, this.mChosenPattern);
    }

    @Override // androidx.fragment.app.Fragment
    public void onStop() {
        super.onStop();
        SaveLockWorker saveLockWorker = this.mSaveLockWorker;
        if (saveLockWorker != null) {
            saveLockWorker.setListener(null);
        }
        getToolbar().getProgressBar().setVisible(false);
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        this.mLockPatternView.clearPattern();
        PasswordHelper.zeroizeCredentials(this.mChosenPattern, this.mCurrentCredential);
    }

    protected void updateStage(Stage stage) {
        this.mUiStage = stage;
        this.mMessageText.setText(stage.mMessageId);
        if (stage.mSecondaryButtonState == SecondaryButtonState.Gone) {
            setSecondaryButtonVisible(false);
        } else {
            setSecondaryButtonVisible(true);
            setSecondaryButtonText(stage.mSecondaryButtonState.mTextResId);
            setSecondaryButtonEnabled(stage.mSecondaryButtonState.mEnabled);
        }
        setPrimaryButtonText(stage.mPrimaryButtonState.mText);
        setPrimaryButtonEnabled(stage.mPrimaryButtonState.mEnabled);
        if (stage.mPatternEnabled) {
            this.mLockPatternView.enableInput();
        } else {
            this.mLockPatternView.disableInput();
        }
        this.mLockPatternView.setDisplayMode(LockPatternView.DisplayMode.Correct);
        int i = AnonymousClass2.$SwitchMap$com$android$car$settings$security$ChooseLockPatternFragment$Stage[this.mUiStage.ordinal()];
        if (i == 1) {
            this.mLockPatternView.clearPattern();
            return;
        }
        if (i == 2) {
            this.mLockPatternView.setDisplayMode(LockPatternView.DisplayMode.Wrong);
            postClearPatternRunnable();
        } else if (i == 3) {
            this.mLockPatternView.setDisplayMode(LockPatternView.DisplayMode.Wrong);
            postClearPatternRunnable();
        } else if (i == 4) {
            this.mLockPatternView.clearPattern();
        } else if (i == 5) {
            this.mLockPatternView.setPattern(LockPatternView.DisplayMode.Animate, this.mAnimatePattern);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateUIWhenPatternInProgress() {
        this.mMessageText.setText(R.string.lockpattern_recording_inprogress);
        setPrimaryButtonEnabled(false);
        setSecondaryButtonEnabled(false);
    }

    private void postClearPatternRunnable() {
        this.mLockPatternView.removeCallbacks(this.mClearPatternRunnable);
        this.mLockPatternView.postDelayed(this.mClearPatternRunnable, this.mWrongPatternClearTimeOut);
    }

    private void setPrimaryButtonEnabled(boolean enabled) {
        this.mPrimaryButton.setEnabled(enabled);
    }

    private void setPrimaryButtonText(int textId) {
        this.mPrimaryButton.setTitle(textId);
    }

    private void setSecondaryButtonVisible(boolean visible) {
        this.mSecondaryButton.setVisible(visible);
    }

    private void setSecondaryButtonEnabled(boolean enabled) {
        this.mSecondaryButton.setEnabled(enabled);
    }

    private void setSecondaryButtonText(int textId) {
        this.mSecondaryButton.setTitle(textId);
    }

    private void handlePrimaryButtonClick() {
        int i = AnonymousClass2.$SwitchMap$com$android$car$settings$security$ChooseLockPatternFragment$PrimaryButtonState[this.mUiStage.mPrimaryButtonState.ordinal()];
        if (i == 1) {
            if (this.mUiStage != Stage.FirstChoiceValid) {
                throw new IllegalStateException("expected ui stage " + Stage.FirstChoiceValid + " when button is " + PrimaryButtonState.Continue);
            }
            updateStage(Stage.NeedToConfirm);
            return;
        }
        if (i == 2) {
            if (this.mUiStage != Stage.ChoiceConfirmed) {
                throw new IllegalStateException("expected ui stage " + Stage.ChoiceConfirmed + " when button is " + PrimaryButtonState.Confirm);
            }
            startSaveAndFinish();
            return;
        }
        if (i == 3) {
            if (this.mUiStage != Stage.SaveFailure) {
                throw new IllegalStateException("expected ui stage " + Stage.SaveFailure + " when button is " + PrimaryButtonState.Retry);
            }
            startSaveAndFinish();
            return;
        }
        if (i == 4) {
            if (this.mUiStage != Stage.HelpScreen) {
                throw new IllegalStateException("Help screen is only mode with ok button, but stage is " + this.mUiStage);
            }
            this.mLockPatternView.clearPattern();
            this.mLockPatternView.setDisplayMode(LockPatternView.DisplayMode.Correct);
            updateStage(Stage.Introduction);
        }
    }

    /* JADX INFO: renamed from: com.android.car.settings.security.ChooseLockPatternFragment$2, reason: invalid class name */
    static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] $SwitchMap$com$android$car$settings$security$ChooseLockPatternFragment$PrimaryButtonState;
        static final /* synthetic */ int[] $SwitchMap$com$android$car$settings$security$ChooseLockPatternFragment$SecondaryButtonState;
        static final /* synthetic */ int[] $SwitchMap$com$android$car$settings$security$ChooseLockPatternFragment$Stage;

        static {
            int[] iArr = new int[SecondaryButtonState.values().length];
            $SwitchMap$com$android$car$settings$security$ChooseLockPatternFragment$SecondaryButtonState = iArr;
            try {
                iArr[SecondaryButtonState.Retry.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$com$android$car$settings$security$ChooseLockPatternFragment$SecondaryButtonState[SecondaryButtonState.Cancel.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            int[] iArr2 = new int[PrimaryButtonState.values().length];
            $SwitchMap$com$android$car$settings$security$ChooseLockPatternFragment$PrimaryButtonState = iArr2;
            try {
                iArr2[PrimaryButtonState.Continue.ordinal()] = 1;
            } catch (NoSuchFieldError e3) {
            }
            try {
                $SwitchMap$com$android$car$settings$security$ChooseLockPatternFragment$PrimaryButtonState[PrimaryButtonState.Confirm.ordinal()] = 2;
            } catch (NoSuchFieldError e4) {
            }
            try {
                $SwitchMap$com$android$car$settings$security$ChooseLockPatternFragment$PrimaryButtonState[PrimaryButtonState.Retry.ordinal()] = 3;
            } catch (NoSuchFieldError e5) {
            }
            try {
                $SwitchMap$com$android$car$settings$security$ChooseLockPatternFragment$PrimaryButtonState[PrimaryButtonState.Ok.ordinal()] = 4;
            } catch (NoSuchFieldError e6) {
            }
            int[] iArr3 = new int[Stage.values().length];
            $SwitchMap$com$android$car$settings$security$ChooseLockPatternFragment$Stage = iArr3;
            try {
                iArr3[Stage.Introduction.ordinal()] = 1;
            } catch (NoSuchFieldError e7) {
            }
            try {
                $SwitchMap$com$android$car$settings$security$ChooseLockPatternFragment$Stage[Stage.ChoiceTooShort.ordinal()] = 2;
            } catch (NoSuchFieldError e8) {
            }
            try {
                $SwitchMap$com$android$car$settings$security$ChooseLockPatternFragment$Stage[Stage.ConfirmWrong.ordinal()] = 3;
            } catch (NoSuchFieldError e9) {
            }
            try {
                $SwitchMap$com$android$car$settings$security$ChooseLockPatternFragment$Stage[Stage.NeedToConfirm.ordinal()] = 4;
            } catch (NoSuchFieldError e10) {
            }
            try {
                $SwitchMap$com$android$car$settings$security$ChooseLockPatternFragment$Stage[Stage.HelpScreen.ordinal()] = 5;
            } catch (NoSuchFieldError e11) {
            }
            try {
                $SwitchMap$com$android$car$settings$security$ChooseLockPatternFragment$Stage[Stage.FirstChoiceValid.ordinal()] = 6;
            } catch (NoSuchFieldError e12) {
            }
            try {
                $SwitchMap$com$android$car$settings$security$ChooseLockPatternFragment$Stage[Stage.ChoiceConfirmed.ordinal()] = 7;
            } catch (NoSuchFieldError e13) {
            }
        }
    }

    private void handleSecondaryButtonClick() {
        int i = AnonymousClass2.$SwitchMap$com$android$car$settings$security$ChooseLockPatternFragment$SecondaryButtonState[this.mUiStage.mSecondaryButtonState.ordinal()];
        if (i == 1) {
            this.mChosenPattern = null;
            this.mLockPatternView.clearPattern();
            updateStage(Stage.Introduction);
        } else {
            if (i == 2) {
                getFragmentHost().goBack();
                return;
            }
            throw new IllegalStateException("secondary footer button pressed, but stage of " + this.mUiStage + " doesn't make sense");
        }
    }

    void onChosenLockSaveFinished(boolean isSaveSuccessful) {
        getToolbar().getProgressBar().setVisible(false);
        if (isSaveSuccessful) {
            onComplete();
        } else {
            updateStage(Stage.SaveFailure);
        }
    }

    private void startSaveAndFinish() {
        SaveLockWorker saveLockWorker = this.mSaveLockWorker;
        if (saveLockWorker != null && !saveLockWorker.isFinished()) {
            LOG.v("startSaveAndFinish with a running SavePatternWorker.");
            return;
        }
        setPrimaryButtonEnabled(false);
        if (this.mSaveLockWorker == null) {
            SaveLockWorker saveLockWorker2 = new SaveLockWorker();
            this.mSaveLockWorker = saveLockWorker2;
            saveLockWorker2.setListener(new $$Lambda$SYjii9RTym20N39J7M61X9AAwp4(this));
            getFragmentManager().beginTransaction().add(this.mSaveLockWorker, FRAGMENT_TAG_SAVE_PATTERN_WORKER).commitNow();
        }
        this.mSaveLockWorker.start(this.mUserId, this.mChosenPattern, this.mCurrentCredential);
        getToolbar().getProgressBar().setVisible(true);
    }

    void onComplete() {
        LockscreenCredential lockscreenCredential = this.mCurrentCredential;
        if (lockscreenCredential != null) {
            lockscreenCredential.zeroize();
        }
        getActivity().finish();
    }

    enum Stage {
        Introduction(R.string.lockpattern_recording_intro_header, SecondaryButtonState.Cancel, PrimaryButtonState.ContinueDisabled, true),
        HelpScreen(R.string.lockpattern_settings_help_how_to_record, SecondaryButtonState.Gone, PrimaryButtonState.Ok, false),
        ChoiceTooShort(R.string.lockpattern_recording_incorrect_too_short, SecondaryButtonState.Retry, PrimaryButtonState.ContinueDisabled, true),
        FirstChoiceValid(R.string.lockpattern_recording_intro_header, SecondaryButtonState.Retry, PrimaryButtonState.Continue, false),
        NeedToConfirm(R.string.lockpattern_need_to_confirm, SecondaryButtonState.Cancel, PrimaryButtonState.ConfirmDisabled, true),
        ConfirmWrong(R.string.lockpattern_pattern_wrong, SecondaryButtonState.Cancel, PrimaryButtonState.ConfirmDisabled, true),
        ChoiceConfirmed(R.string.lockpattern_pattern_confirmed, SecondaryButtonState.Cancel, PrimaryButtonState.Confirm, false),
        SaveFailure(R.string.error_saving_lockpattern, SecondaryButtonState.Cancel, PrimaryButtonState.Retry, false);

        final int mMessageId;
        final boolean mPatternEnabled;
        final PrimaryButtonState mPrimaryButtonState;
        final SecondaryButtonState mSecondaryButtonState;

        Stage(int messageId, SecondaryButtonState secondaryButtonState, PrimaryButtonState primaryButtonState, boolean patternEnabled) {
            this.mMessageId = messageId;
            this.mSecondaryButtonState = secondaryButtonState;
            this.mPrimaryButtonState = primaryButtonState;
            this.mPatternEnabled = patternEnabled;
        }
    }

    enum PrimaryButtonState {
        Continue(R.string.continue_button_text, true),
        ContinueDisabled(R.string.continue_button_text, false),
        Confirm(R.string.lockpattern_confirm_button_text, true),
        ConfirmDisabled(R.string.lockpattern_confirm_button_text, false),
        Retry(R.string.lockscreen_retry_button_text, true),
        Ok(R.string.okay, true);

        final boolean mEnabled;
        final int mText;

        PrimaryButtonState(int text, boolean enabled) {
            this.mText = text;
            this.mEnabled = enabled;
        }
    }

    enum SecondaryButtonState {
        Cancel(R.string.lockpattern_cancel_button_text, true),
        CancelDisabled(R.string.lockpattern_cancel_button_text, false),
        Retry(R.string.lockpattern_retry_button_text, true),
        RetryDisabled(R.string.lockpattern_retry_button_text, false),
        Gone(-1, false);

        final boolean mEnabled;
        final int mTextResId;

        SecondaryButtonState(int textId, boolean enabled) {
            this.mTextResId = textId;
            this.mEnabled = enabled;
        }
    }
}
