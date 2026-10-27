package com.android.car.developeroptions.password;

import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.util.Pair;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import com.android.car.developeroptions.R;
import com.android.car.developeroptions.SettingsActivity;
import com.android.car.developeroptions.SetupWizardUtils;
import com.android.car.developeroptions.Utils;
import com.android.car.developeroptions.core.InstrumentedFragment;
import com.android.car.developeroptions.notification.RedactionInterstitial;
import com.android.car.developeroptions.password.SaveChosenLockWorkerBase;
import com.android.internal.annotations.VisibleForTesting;
import com.android.internal.widget.LockPatternUtils;
import com.android.internal.widget.LockPatternView;
import com.android.internal.widget.LockscreenCredential;
import com.google.android.collect.Lists;
import com.google.android.setupcompat.template.FooterBarMixin;
import com.google.android.setupcompat.template.FooterButton;
import com.google.android.setupdesign.GlifLayout;
import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class ChooseLockPattern extends SettingsActivity {
    @Override // com.android.car.developeroptions.SettingsActivity, android.app.Activity
    public Intent getIntent() {
        Intent intent = new Intent(super.getIntent());
        intent.putExtra(":settings:show_fragment", getFragmentClass().getName());
        return intent;
    }

    @Override // com.android.car.developeroptions.SettingsActivity, android.app.Activity, android.view.ContextThemeWrapper
    protected void onApplyThemeResource(Resources.Theme theme, int i, boolean z) {
        super.onApplyThemeResource(theme, SetupWizardUtils.getTheme(getIntent()), z);
    }

    public static class IntentBuilder {
        private final Intent mIntent;

        public IntentBuilder(Context context) {
            Intent intent = new Intent(context, (Class<?>) ChooseLockPattern.class);
            this.mIntent = intent;
            intent.putExtra("extra_require_password", false);
            this.mIntent.putExtra("confirm_credentials", false);
            this.mIntent.putExtra("has_challenge", false);
        }

        public IntentBuilder setUserId(int i) {
            this.mIntent.putExtra("android.intent.extra.USER_ID", i);
            return this;
        }

        public IntentBuilder setChallenge(long j) {
            this.mIntent.putExtra("has_challenge", true);
            this.mIntent.putExtra("challenge", j);
            return this;
        }

        public IntentBuilder setPattern(LockscreenCredential lockscreenCredential) {
            this.mIntent.putExtra("password", (Parcelable) lockscreenCredential);
            return this;
        }

        public IntentBuilder setForFingerprint(boolean z) {
            this.mIntent.putExtra("for_fingerprint", z);
            return this;
        }

        public IntentBuilder setForFace(boolean z) {
            this.mIntent.putExtra("for_face", z);
            return this;
        }

        public Intent build() {
            return this.mIntent;
        }
    }

    @Override // com.android.car.developeroptions.SettingsActivity
    protected boolean isValidFragment(String str) {
        return ChooseLockPatternFragment.class.getName().equals(str);
    }

    Class<? extends Fragment> getFragmentClass() {
        return ChooseLockPatternFragment.class;
    }

    @Override // com.android.car.developeroptions.SettingsActivity, com.android.car.developeroptions.core.SettingsBaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setTitle(getIntent().getBooleanExtra("for_fingerprint", false) ? R.string.lockpassword_choose_your_pattern_header_for_fingerprint : getIntent().getBooleanExtra("for_face", false) ? R.string.lockpassword_choose_your_pattern_header_for_face : R.string.lockpassword_choose_your_screen_lock_header);
        ((LinearLayout) findViewById(R.id.content_parent)).setFitsSystemWindows(false);
    }

    @Override // android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        return super.onKeyDown(i, keyEvent);
    }

    public static class ChooseLockPatternFragment extends InstrumentedFragment implements SaveChosenLockWorkerBase.Listener {
        private long mChallenge;
        private ChooseLockSettingsHelper mChooseLockSettingsHelper;

        @VisibleForTesting
        protected LockscreenCredential mChosenPattern;
        private LockscreenCredential mCurrentCredential;
        private ColorStateList mDefaultHeaderColorList;
        protected TextView mFooterText;
        protected boolean mForFace;
        protected boolean mForFingerprint;
        private boolean mHasChallenge;
        protected TextView mHeaderText;
        protected LockPatternView mLockPatternView;
        protected TextView mMessageText;
        private FooterButton mNextButton;
        private SaveAndFinishWorker mSaveAndFinishWorker;
        protected FooterButton mSkipOrClearButton;
        private ScrollView mTitleHeaderScrollView;
        protected TextView mTitleText;
        protected int mUserId;
        private final List<LockPatternView.Cell> mAnimatePattern = Collections.unmodifiableList(Lists.newArrayList(new LockPatternView.Cell[]{LockPatternView.Cell.of(0, 0), LockPatternView.Cell.of(0, 1), LockPatternView.Cell.of(1, 1), LockPatternView.Cell.of(2, 1)}));
        protected LockPatternView.OnPatternListener mChooseNewLockPatternListener = new LockPatternView.OnPatternListener() { // from class: com.android.car.developeroptions.password.ChooseLockPattern.ChooseLockPatternFragment.1
            public void onPatternCellAdded(List<LockPatternView.Cell> list) {
            }

            public void onPatternStart() {
                ChooseLockPatternFragment chooseLockPatternFragment = ChooseLockPatternFragment.this;
                chooseLockPatternFragment.mLockPatternView.removeCallbacks(chooseLockPatternFragment.mClearPatternRunnable);
                patternInProgress();
            }

            public void onPatternCleared() {
                ChooseLockPatternFragment chooseLockPatternFragment = ChooseLockPatternFragment.this;
                chooseLockPatternFragment.mLockPatternView.removeCallbacks(chooseLockPatternFragment.mClearPatternRunnable);
            }

            public void onPatternDetected(List<LockPatternView.Cell> list) {
                if (ChooseLockPatternFragment.this.mUiStage != Stage.NeedToConfirm && ChooseLockPatternFragment.this.mUiStage != Stage.ConfirmWrong) {
                    if (ChooseLockPatternFragment.this.mUiStage != Stage.Introduction && ChooseLockPatternFragment.this.mUiStage != Stage.ChoiceTooShort) {
                        throw new IllegalStateException("Unexpected stage " + ChooseLockPatternFragment.this.mUiStage + " when entering the pattern.");
                    }
                    if (list.size() < 4) {
                        ChooseLockPatternFragment.this.updateStage(Stage.ChoiceTooShort);
                        return;
                    }
                    ChooseLockPatternFragment.this.mChosenPattern = LockscreenCredential.createPattern(list);
                    ChooseLockPatternFragment.this.updateStage(Stage.FirstChoiceValid);
                    return;
                }
                if (ChooseLockPatternFragment.this.mChosenPattern == null) {
                    throw new IllegalStateException("null chosen pattern in stage 'need to confirm");
                }
                LockscreenCredential lockscreenCredentialCreatePattern = LockscreenCredential.createPattern(list);
                try {
                    if (ChooseLockPatternFragment.this.mChosenPattern.equals(lockscreenCredentialCreatePattern)) {
                        ChooseLockPatternFragment.this.updateStage(Stage.ChoiceConfirmed);
                    } else {
                        ChooseLockPatternFragment.this.updateStage(Stage.ConfirmWrong);
                    }
                    if (lockscreenCredentialCreatePattern != null) {
                        lockscreenCredentialCreatePattern.close();
                    }
                } catch (Throwable th) {
                    if (lockscreenCredentialCreatePattern != null) {
                        try {
                            lockscreenCredentialCreatePattern.close();
                        } catch (Throwable th2) {
                            th.addSuppressed(th2);
                        }
                    }
                    throw th;
                }
            }

            private void patternInProgress() {
                ChooseLockPatternFragment.this.mHeaderText.setText(R.string.lockpattern_recording_inprogress);
                if (ChooseLockPatternFragment.this.mDefaultHeaderColorList != null) {
                    ChooseLockPatternFragment chooseLockPatternFragment = ChooseLockPatternFragment.this;
                    chooseLockPatternFragment.mHeaderText.setTextColor(chooseLockPatternFragment.mDefaultHeaderColorList);
                }
                ChooseLockPatternFragment.this.mFooterText.setText("");
                ChooseLockPatternFragment.this.mNextButton.setEnabled(false);
                if (ChooseLockPatternFragment.this.mTitleHeaderScrollView != null) {
                    ChooseLockPatternFragment.this.mTitleHeaderScrollView.post(new Runnable() { // from class: com.android.car.developeroptions.password.ChooseLockPattern.ChooseLockPatternFragment.1.1
                        @Override // java.lang.Runnable
                        public void run() {
                            ChooseLockPatternFragment.this.mTitleHeaderScrollView.fullScroll(130);
                        }
                    });
                }
            }
        };
        private Stage mUiStage = Stage.Introduction;
        private Runnable mClearPatternRunnable = new Runnable() { // from class: com.android.car.developeroptions.password.ChooseLockPattern.ChooseLockPatternFragment.2
            @Override // java.lang.Runnable
            public void run() {
                ChooseLockPatternFragment.this.mLockPatternView.clearPattern();
            }
        };

        @Override // com.android.settingslib.core.instrumentation.Instrumentable
        public int getMetricsCategory() {
            return 29;
        }

        @Override // androidx.fragment.app.Fragment
        public void onActivityResult(int i, int i2, Intent intent) {
            super.onActivityResult(i, i2, intent);
            if (i != 55) {
                return;
            }
            if (i2 != -1) {
                getActivity().setResult(1);
                getActivity().finish();
            } else {
                this.mCurrentCredential = intent.getParcelableExtra("password");
            }
            updateStage(Stage.Introduction);
        }

        protected void setRightButtonEnabled(boolean z) {
            this.mNextButton.setEnabled(z);
        }

        protected void setRightButtonText(int i) {
            this.mNextButton.setText(getActivity(), i);
        }

        enum LeftButtonMode {
            Retry(R.string.lockpattern_retry_button_text, true),
            RetryDisabled(R.string.lockpattern_retry_button_text, false),
            Gone(-1, false);

            final boolean enabled;
            final int text;

            LeftButtonMode(int i, boolean z) {
                this.text = i;
                this.enabled = z;
            }
        }

        enum RightButtonMode {
            Continue(R.string.next_label, true),
            ContinueDisabled(R.string.next_label, false),
            Confirm(R.string.lockpattern_confirm_button_text, true),
            ConfirmDisabled(R.string.lockpattern_confirm_button_text, false),
            Ok(android.R.string.ok, true);

            final boolean enabled;
            final int text;

            RightButtonMode(int i, boolean z) {
                this.text = i;
                this.enabled = z;
            }
        }

        protected enum Stage {
            Introduction(R.string.lock_settings_picker_biometrics_added_security_message, R.string.lockpassword_choose_your_pattern_message, R.string.lockpattern_recording_intro_header, LeftButtonMode.Gone, RightButtonMode.ContinueDisabled, -1, true),
            HelpScreen(-1, -1, R.string.lockpattern_settings_help_how_to_record, LeftButtonMode.Gone, RightButtonMode.Ok, -1, false),
            ChoiceTooShort(R.string.lock_settings_picker_biometrics_added_security_message, R.string.lockpassword_choose_your_pattern_message, R.string.lockpattern_recording_incorrect_too_short, LeftButtonMode.Retry, RightButtonMode.ContinueDisabled, -1, true),
            FirstChoiceValid(R.string.lock_settings_picker_biometrics_added_security_message, R.string.lockpassword_choose_your_pattern_message, R.string.lockpattern_pattern_entered_header, LeftButtonMode.Retry, RightButtonMode.Continue, -1, false),
            NeedToConfirm(-1, -1, R.string.lockpattern_need_to_confirm, LeftButtonMode.Gone, RightButtonMode.ConfirmDisabled, -1, true),
            ConfirmWrong(-1, -1, R.string.lockpattern_need_to_unlock_wrong, LeftButtonMode.Gone, RightButtonMode.ConfirmDisabled, -1, true),
            ChoiceConfirmed(-1, -1, R.string.lockpattern_pattern_confirmed_header, LeftButtonMode.Gone, RightButtonMode.Confirm, -1, false);

            final int footerMessage;
            final int headerMessage;
            final LeftButtonMode leftMode;
            final int message;
            final int messageForBiometrics;
            final boolean patternEnabled;
            final RightButtonMode rightMode;

            Stage(int i, int i2, int i3, LeftButtonMode leftButtonMode, RightButtonMode rightButtonMode, int i4, boolean z) {
                this.headerMessage = i3;
                this.messageForBiometrics = i;
                this.message = i2;
                this.leftMode = leftButtonMode;
                this.rightMode = rightButtonMode;
                this.footerMessage = i4;
                this.patternEnabled = z;
            }
        }

        @Override // com.android.settingslib.core.lifecycle.ObservableFragment, androidx.fragment.app.Fragment
        public void onCreate(Bundle bundle) {
            super.onCreate(bundle);
            this.mChooseLockSettingsHelper = new ChooseLockSettingsHelper(getActivity());
            if (!(getActivity() instanceof ChooseLockPattern)) {
                throw new SecurityException("Fragment contained in wrong activity");
            }
            Intent intent = getActivity().getIntent();
            this.mUserId = Utils.getUserIdFromBundle(getActivity(), intent.getExtras());
            if (intent.getBooleanExtra("for_cred_req_boot", false)) {
                SaveAndFinishWorker saveAndFinishWorker = new SaveAndFinishWorker();
                boolean booleanExtra = getActivity().getIntent().getBooleanExtra("extra_require_password", true);
                LockscreenCredential lockscreenCredential = (LockscreenCredential) intent.getParcelableExtra("password");
                saveAndFinishWorker.setBlocking(true);
                saveAndFinishWorker.setListener(this);
                saveAndFinishWorker.start(this.mChooseLockSettingsHelper.utils(), booleanExtra, false, 0L, lockscreenCredential, lockscreenCredential, this.mUserId);
            }
            this.mForFingerprint = intent.getBooleanExtra("for_fingerprint", false);
            this.mForFace = intent.getBooleanExtra("for_face", false);
        }

        @Override // androidx.fragment.app.Fragment
        public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
            GlifLayout glifLayout = (GlifLayout) layoutInflater.inflate(R.layout.choose_lock_pattern, viewGroup, false);
            glifLayout.setHeaderText(getActivity().getTitle());
            if (getResources().getBoolean(R.bool.config_lock_pattern_minimal_ui)) {
                View viewFindViewById = glifLayout.findViewById(R.id.sud_layout_icon);
                if (viewFindViewById != null) {
                    viewFindViewById.setVisibility(8);
                }
            } else if (this.mForFingerprint) {
                glifLayout.setIcon(getActivity().getDrawable(R.drawable.ic_fingerprint_header));
            } else if (this.mForFace) {
                glifLayout.setIcon(getActivity().getDrawable(R.drawable.ic_face_header));
            }
            FooterBarMixin footerBarMixin = (FooterBarMixin) glifLayout.getMixin(FooterBarMixin.class);
            FooterButton.Builder builder = new FooterButton.Builder(getActivity());
            builder.setText(R.string.lockpattern_tutorial_cancel_label);
            builder.setListener(new View.OnClickListener() { // from class: com.android.car.developeroptions.password.-$$Lambda$J4i5oze9pg2pokUP8KeAw4rnUIE
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    this.f$0.onSkipOrClearButtonClick(view);
                }
            });
            builder.setButtonType(0);
            builder.setTheme(R.style.SudGlifButton_Secondary);
            footerBarMixin.setSecondaryButton(builder.build());
            FooterButton.Builder builder2 = new FooterButton.Builder(getActivity());
            builder2.setText(R.string.lockpattern_tutorial_continue_label);
            builder2.setListener(new View.OnClickListener() { // from class: com.android.car.developeroptions.password.-$$Lambda$2z9ew1S67NBW2kXf1-ZqDckTXVI
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    this.f$0.onNextButtonClick(view);
                }
            });
            builder2.setButtonType(5);
            builder2.setTheme(R.style.SudGlifButton_Primary);
            footerBarMixin.setPrimaryButton(builder2.build());
            this.mSkipOrClearButton = footerBarMixin.getSecondaryButton();
            this.mNextButton = footerBarMixin.getPrimaryButton();
            return glifLayout;
        }

        @Override // androidx.fragment.app.Fragment
        public void onViewCreated(View view, Bundle bundle) {
            super.onViewCreated(view, bundle);
            this.mTitleText = (TextView) view.findViewById(R.id.suc_layout_title);
            TextView textView = (TextView) view.findViewById(R.id.headerText);
            this.mHeaderText = textView;
            this.mDefaultHeaderColorList = textView.getTextColors();
            this.mMessageText = (TextView) view.findViewById(R.id.message);
            LockPatternView lockPatternViewFindViewById = view.findViewById(R.id.lockPattern);
            this.mLockPatternView = lockPatternViewFindViewById;
            lockPatternViewFindViewById.setOnPatternListener(this.mChooseNewLockPatternListener);
            this.mLockPatternView.setTactileFeedbackEnabled(this.mChooseLockSettingsHelper.utils().isTactileFeedbackEnabled());
            this.mLockPatternView.setFadePattern(false);
            this.mFooterText = (TextView) view.findViewById(R.id.footerText);
            this.mTitleHeaderScrollView = (ScrollView) view.findViewById(R.id.scroll_layout_title_header);
            view.findViewById(R.id.topLayout).setDefaultTouchRecepient(this.mLockPatternView);
            boolean booleanExtra = getActivity().getIntent().getBooleanExtra("confirm_credentials", true);
            Intent intent = getActivity().getIntent();
            this.mCurrentCredential = intent.getParcelableExtra("password");
            this.mHasChallenge = intent.getBooleanExtra("has_challenge", false);
            this.mChallenge = intent.getLongExtra("challenge", 0L);
            if (bundle != null) {
                this.mChosenPattern = bundle.getParcelable("chosenPattern");
                if (this.mCurrentCredential == null) {
                    this.mCurrentCredential = bundle.getParcelable("currentPattern");
                }
                updateStage(Stage.values()[bundle.getInt("uiStage")]);
                this.mSaveAndFinishWorker = (SaveAndFinishWorker) getFragmentManager().findFragmentByTag("save_and_finish_worker");
                return;
            }
            if (booleanExtra) {
                updateStage(Stage.NeedToConfirm);
                if (this.mChooseLockSettingsHelper.launchConfirmationActivity(55, getString(R.string.unlock_set_unlock_launch_picker_title), true, this.mUserId)) {
                    return;
                }
                updateStage(Stage.Introduction);
                return;
            }
            updateStage(Stage.Introduction);
        }

        @Override // com.android.car.developeroptions.core.InstrumentedFragment, com.android.settingslib.core.lifecycle.ObservableFragment, androidx.fragment.app.Fragment
        public void onResume() {
            super.onResume();
            updateStage(this.mUiStage);
            if (this.mSaveAndFinishWorker != null) {
                setRightButtonEnabled(false);
                this.mSaveAndFinishWorker.setListener(this);
            }
        }

        @Override // com.android.settingslib.core.lifecycle.ObservableFragment, androidx.fragment.app.Fragment
        public void onPause() {
            super.onPause();
            SaveAndFinishWorker saveAndFinishWorker = this.mSaveAndFinishWorker;
            if (saveAndFinishWorker != null) {
                saveAndFinishWorker.setListener(null);
            }
        }

        protected Intent getRedactionInterstitialIntent(Context context) {
            return RedactionInterstitial.createStartIntent(context, this.mUserId);
        }

        public void handleLeftButton() {
            if (this.mUiStage.leftMode == LeftButtonMode.Retry) {
                this.mChosenPattern.zeroize();
                this.mChosenPattern = null;
                this.mLockPatternView.clearPattern();
                updateStage(Stage.Introduction);
                return;
            }
            throw new IllegalStateException("left footer button pressed, but stage of " + this.mUiStage + " doesn't make sense");
        }

        public void handleRightButton() {
            Stage stage = this.mUiStage;
            RightButtonMode rightButtonMode = stage.rightMode;
            if (rightButtonMode == RightButtonMode.Continue) {
                if (stage != Stage.FirstChoiceValid) {
                    throw new IllegalStateException("expected ui stage " + Stage.FirstChoiceValid + " when button is " + RightButtonMode.Continue);
                }
                updateStage(Stage.NeedToConfirm);
                return;
            }
            if (rightButtonMode == RightButtonMode.Confirm) {
                if (stage != Stage.ChoiceConfirmed) {
                    throw new IllegalStateException("expected ui stage " + Stage.ChoiceConfirmed + " when button is " + RightButtonMode.Confirm);
                }
                startSaveAndFinish();
                return;
            }
            if (rightButtonMode == RightButtonMode.Ok) {
                if (stage != Stage.HelpScreen) {
                    throw new IllegalStateException("Help screen is only mode with ok button, but stage is " + this.mUiStage);
                }
                this.mLockPatternView.clearPattern();
                this.mLockPatternView.setDisplayMode(LockPatternView.DisplayMode.Correct);
                updateStage(Stage.Introduction);
            }
        }

        protected void onSkipOrClearButtonClick(View view) {
            handleLeftButton();
        }

        protected void onNextButtonClick(View view) {
            handleRightButton();
        }

        @Override // com.android.settingslib.core.lifecycle.ObservableFragment, androidx.fragment.app.Fragment
        public void onSaveInstanceState(Bundle bundle) {
            super.onSaveInstanceState(bundle);
            bundle.putInt("uiStage", this.mUiStage.ordinal());
            LockscreenCredential lockscreenCredential = this.mChosenPattern;
            if (lockscreenCredential != null) {
                bundle.putParcelable("chosenPattern", lockscreenCredential);
            }
            LockscreenCredential lockscreenCredential2 = this.mCurrentCredential;
            if (lockscreenCredential2 != null) {
                bundle.putParcelable("currentPattern", lockscreenCredential2);
            }
        }

        protected void updateStage(Stage stage) {
            Stage stage2 = this.mUiStage;
            this.mUiStage = stage;
            boolean z = false;
            if (stage == Stage.ChoiceTooShort) {
                this.mHeaderText.setText(getResources().getString(stage.headerMessage, 4));
            } else {
                this.mHeaderText.setText(stage.headerMessage);
            }
            boolean z2 = this.mForFingerprint || this.mForFace;
            int i = z2 ? stage.messageForBiometrics : stage.message;
            if (i == -1) {
                this.mMessageText.setText("");
            } else {
                this.mMessageText.setText(i);
            }
            int i2 = stage.footerMessage;
            if (i2 == -1) {
                this.mFooterText.setText("");
            } else {
                this.mFooterText.setText(i2);
            }
            if (stage == Stage.ConfirmWrong || stage == Stage.ChoiceTooShort) {
                TypedValue typedValue = new TypedValue();
                getActivity().getTheme().resolveAttribute(R.attr.colorError, typedValue, true);
                this.mHeaderText.setTextColor(typedValue.data);
            } else {
                ColorStateList colorStateList = this.mDefaultHeaderColorList;
                if (colorStateList != null) {
                    this.mHeaderText.setTextColor(colorStateList);
                }
                if (stage == Stage.NeedToConfirm && z2) {
                    this.mHeaderText.setText("");
                    this.mTitleText.setText(R.string.lockpassword_draw_your_pattern_again_header);
                }
            }
            updateFooterLeftButton(stage);
            setRightButtonText(stage.rightMode.text);
            setRightButtonEnabled(stage.rightMode.enabled);
            if (stage.patternEnabled) {
                this.mLockPatternView.enableInput();
            } else {
                this.mLockPatternView.disableInput();
            }
            this.mLockPatternView.setDisplayMode(LockPatternView.DisplayMode.Correct);
            int i3 = AnonymousClass1.$SwitchMap$com$android$car$developeroptions$password$ChooseLockPattern$ChooseLockPatternFragment$Stage[this.mUiStage.ordinal()];
            if (i3 == 1) {
                this.mLockPatternView.clearPattern();
            } else if (i3 != 2) {
                if (i3 == 3) {
                    this.mLockPatternView.setDisplayMode(LockPatternView.DisplayMode.Wrong);
                    postClearPatternRunnable();
                } else if (i3 == 5) {
                    this.mLockPatternView.clearPattern();
                } else if (i3 == 6) {
                    this.mLockPatternView.setDisplayMode(LockPatternView.DisplayMode.Wrong);
                    postClearPatternRunnable();
                }
                z = true;
            } else {
                this.mLockPatternView.setPattern(LockPatternView.DisplayMode.Animate, this.mAnimatePattern);
            }
            if (stage2 != stage || z) {
                TextView textView = this.mHeaderText;
                textView.announceForAccessibility(textView.getText());
            }
        }

        protected void updateFooterLeftButton(Stage stage) {
            if (stage.leftMode == LeftButtonMode.Gone) {
                this.mSkipOrClearButton.setVisibility(8);
                return;
            }
            this.mSkipOrClearButton.setVisibility(0);
            this.mSkipOrClearButton.setText(getActivity(), stage.leftMode.text);
            this.mSkipOrClearButton.setEnabled(stage.leftMode.enabled);
        }

        private void postClearPatternRunnable() {
            this.mLockPatternView.removeCallbacks(this.mClearPatternRunnable);
            this.mLockPatternView.postDelayed(this.mClearPatternRunnable, 2000L);
        }

        private void startSaveAndFinish() {
            if (this.mSaveAndFinishWorker != null) {
                Log.w("ChooseLockPattern", "startSaveAndFinish with an existing SaveAndFinishWorker.");
                return;
            }
            setRightButtonEnabled(false);
            SaveAndFinishWorker saveAndFinishWorker = new SaveAndFinishWorker();
            this.mSaveAndFinishWorker = saveAndFinishWorker;
            saveAndFinishWorker.setListener(this);
            FragmentTransaction fragmentTransactionBeginTransaction = getFragmentManager().beginTransaction();
            fragmentTransactionBeginTransaction.add(this.mSaveAndFinishWorker, "save_and_finish_worker");
            fragmentTransactionBeginTransaction.commit();
            getFragmentManager().executePendingTransactions();
            this.mSaveAndFinishWorker.start(this.mChooseLockSettingsHelper.utils(), getActivity().getIntent().getBooleanExtra("extra_require_password", true), this.mHasChallenge, this.mChallenge, this.mChosenPattern, this.mCurrentCredential, this.mUserId);
        }

        @Override // com.android.car.developeroptions.password.SaveChosenLockWorkerBase.Listener
        public void onChosenLockSaveFinished(boolean z, Intent intent) {
            Intent redactionInterstitialIntent;
            getActivity().setResult(1, intent);
            LockscreenCredential lockscreenCredential = this.mCurrentCredential;
            if (lockscreenCredential != null) {
                lockscreenCredential.zeroize();
            }
            if (!z && (redactionInterstitialIntent = getRedactionInterstitialIntent(getActivity())) != null) {
                startActivity(redactionInterstitialIntent);
            }
            getActivity().finish();
        }
    }

    /* JADX INFO: renamed from: com.android.car.developeroptions.password.ChooseLockPattern$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$android$car$developeroptions$password$ChooseLockPattern$ChooseLockPatternFragment$Stage;

        static {
            int[] iArr = new int[ChooseLockPatternFragment.Stage.values().length];
            $SwitchMap$com$android$car$developeroptions$password$ChooseLockPattern$ChooseLockPatternFragment$Stage = iArr;
            try {
                iArr[ChooseLockPatternFragment.Stage.Introduction.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$android$car$developeroptions$password$ChooseLockPattern$ChooseLockPatternFragment$Stage[ChooseLockPatternFragment.Stage.HelpScreen.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$android$car$developeroptions$password$ChooseLockPattern$ChooseLockPatternFragment$Stage[ChooseLockPatternFragment.Stage.ChoiceTooShort.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$android$car$developeroptions$password$ChooseLockPattern$ChooseLockPatternFragment$Stage[ChooseLockPatternFragment.Stage.FirstChoiceValid.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$android$car$developeroptions$password$ChooseLockPattern$ChooseLockPatternFragment$Stage[ChooseLockPatternFragment.Stage.NeedToConfirm.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$android$car$developeroptions$password$ChooseLockPattern$ChooseLockPatternFragment$Stage[ChooseLockPatternFragment.Stage.ConfirmWrong.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$com$android$car$developeroptions$password$ChooseLockPattern$ChooseLockPatternFragment$Stage[ChooseLockPatternFragment.Stage.ChoiceConfirmed.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
        }
    }

    public static class SaveAndFinishWorker extends SaveChosenLockWorkerBase {
        private LockscreenCredential mChosenPattern;
        private LockscreenCredential mCurrentCredential;
        private boolean mLockVirgin;

        public void start(LockPatternUtils lockPatternUtils, boolean z, boolean z2, long j, LockscreenCredential lockscreenCredential, LockscreenCredential lockscreenCredential2, int i) {
            prepare(lockPatternUtils, z, z2, j, i);
            if (lockscreenCredential2 == null) {
                lockscreenCredential2 = LockscreenCredential.createNone();
            }
            this.mCurrentCredential = lockscreenCredential2;
            this.mChosenPattern = lockscreenCredential;
            this.mUserId = i;
            this.mLockVirgin = !this.mUtils.isPatternEverChosen(i);
            start();
        }

        @Override // com.android.car.developeroptions.password.SaveChosenLockWorkerBase
        protected Pair<Boolean, Intent> saveAndVerifyInBackground() {
            int i = this.mUserId;
            boolean lockCredential = this.mUtils.setLockCredential(this.mChosenPattern, this.mCurrentCredential, i);
            Intent intent = null;
            byte[] bArrVerifyCredential = null;
            intent = null;
            if (lockCredential && this.mHasChallenge) {
                try {
                    bArrVerifyCredential = this.mUtils.verifyCredential(this.mChosenPattern, this.mChallenge, i);
                } catch (LockPatternUtils.RequestThrottledException unused) {
                }
                if (bArrVerifyCredential == null) {
                    Log.e("ChooseLockPattern", "critical: no token returned for known good pattern");
                }
                Intent intent2 = new Intent();
                intent2.putExtra("hw_auth_token", bArrVerifyCredential);
                intent = intent2;
            }
            return Pair.create(Boolean.valueOf(lockCredential), intent);
        }

        @Override // com.android.car.developeroptions.password.SaveChosenLockWorkerBase
        protected void finish(Intent intent) {
            if (this.mLockVirgin) {
                this.mUtils.setVisiblePatternEnabled(true, this.mUserId);
            }
            super.finish(intent);
        }
    }
}
