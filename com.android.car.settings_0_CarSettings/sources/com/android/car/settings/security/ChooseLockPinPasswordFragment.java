package com.android.car.settings.security;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.UserHandle;
import android.text.Editable;
import android.text.Selection;
import android.text.Spannable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import com.android.car.settings.R;
import com.android.car.settings.common.BaseFragment;
import com.android.car.settings.common.Logger;
import com.android.car.settings.security.PinPadView;
import com.android.car.ui.toolbar.MenuItem;
import com.android.car.ui.toolbar.ProgressBarController;
import com.android.internal.widget.LockscreenCredential;
import com.android.internal.widget.TextViewInputDisabler;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/* JADX INFO: loaded from: classes5.dex */
public class ChooseLockPinPasswordFragment extends BaseFragment {
    private static final String EXTRA_IS_PIN = "extra_is_pin";
    private static final String FRAGMENT_TAG_SAVE_PASSWORD_WORKER = "save_password_worker";
    private static final String LOCK_OPTIONS_DIALOG_TAG = "lock_options_dialog_tag";
    private static final Logger LOG = new Logger((Class<?>) ChooseLockPinPasswordFragment.class);
    private static final String STATE_FIRST_ENTRY = "state_first_entry";
    private static final String STATE_UI_STAGE = "state_ui_stage";
    private LockscreenCredential mCurrentEntry;
    private LockscreenCredential mExistingCredential;
    private LockscreenCredential mFirstEntry;
    private TextView mHintMessage;
    private boolean mIsAlphaMode;
    private boolean mIsPin;
    private TextViewInputDisabler mPasswordEntryInputDisabler;
    private EditText mPasswordField;
    private PasswordHelper mPasswordHelper;
    private PinPadView mPinPad;
    private MenuItem mPrimaryButton;
    private ProgressBarController mProgressBar;
    private SaveLockWorker mSaveLockWorker;
    private MenuItem mSecondaryButton;
    private int mUserId;
    private Stage mUiStage = Stage.Introduction;
    private int mErrorCode = 0;
    private TextChangedHandler mTextChangedHandler = new TextChangedHandler();

    public static ChooseLockPinPasswordFragment newPasswordInstance() {
        ChooseLockPinPasswordFragment passwordFragment = new ChooseLockPinPasswordFragment();
        Bundle bundle = new Bundle();
        bundle.putBoolean(EXTRA_IS_PIN, false);
        passwordFragment.setArguments(bundle);
        return passwordFragment;
    }

    public static ChooseLockPinPasswordFragment newPinInstance() {
        ChooseLockPinPasswordFragment passwordFragment = new ChooseLockPinPasswordFragment();
        Bundle bundle = new Bundle();
        bundle.putBoolean(EXTRA_IS_PIN, true);
        passwordFragment.setArguments(bundle);
        return passwordFragment;
    }

    @Override // com.android.car.settings.common.BaseFragment
    public List<MenuItem> getToolbarMenuItems() {
        return Arrays.asList(this.mPrimaryButton, this.mSecondaryButton);
    }

    @Override // com.android.car.settings.common.BaseFragment
    protected int getLayoutId() {
        return this.mIsPin ? R.layout.choose_lock_pin : R.layout.choose_lock_password;
    }

    @Override // com.android.car.settings.common.BaseFragment
    protected int getTitleId() {
        return this.mIsPin ? R.string.security_lock_pin : R.string.security_lock_password;
    }

    @Override // androidx.fragment.app.Fragment
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mUserId = UserHandle.myUserId();
        Bundle args = getArguments();
        if (args != null) {
            this.mIsPin = args.getBoolean(EXTRA_IS_PIN);
            LockscreenCredential parcelable = args.getParcelable(PasswordHelper.EXTRA_CURRENT_SCREEN_LOCK);
            this.mExistingCredential = parcelable;
            if (parcelable != null) {
                this.mExistingCredential = parcelable.duplicate();
            }
        }
        PasswordHelper passwordHelper = new PasswordHelper(this.mIsPin);
        this.mPasswordHelper = passwordHelper;
        int passwordQuality = passwordHelper.getPasswordQuality();
        this.mIsAlphaMode = 262144 == passwordQuality || 327680 == passwordQuality || 393216 == passwordQuality;
        if (savedInstanceState != null) {
            this.mUiStage = Stage.values()[savedInstanceState.getInt(STATE_UI_STAGE)];
            this.mFirstEntry = savedInstanceState.getParcelable(STATE_FIRST_ENTRY);
        }
        this.mPrimaryButton = new MenuItem.Builder(getContext()).setOnClickListener(new MenuItem.OnClickListener() { // from class: com.android.car.settings.security.-$$Lambda$ChooseLockPinPasswordFragment$0eANgwrWvQraqb581bqNK2HpL-Y
            @Override // com.android.car.ui.toolbar.MenuItem.OnClickListener
            public final void onClick(MenuItem menuItem) {
                this.f$0.lambda$onCreate$0$ChooseLockPinPasswordFragment(menuItem);
            }
        }).build();
        this.mSecondaryButton = new MenuItem.Builder(getContext()).setOnClickListener(new MenuItem.OnClickListener() { // from class: com.android.car.settings.security.-$$Lambda$ChooseLockPinPasswordFragment$TNGuhbuPw1xmtte109dMeJpWaXc
            @Override // com.android.car.ui.toolbar.MenuItem.OnClickListener
            public final void onClick(MenuItem menuItem) {
                this.f$0.lambda$onCreate$1$ChooseLockPinPasswordFragment(menuItem);
            }
        }).build();
    }

    public /* synthetic */ void lambda$onCreate$0$ChooseLockPinPasswordFragment(MenuItem i) {
        handlePrimaryButtonClick();
    }

    public /* synthetic */ void lambda$onCreate$1$ChooseLockPinPasswordFragment(MenuItem i) {
        handleSecondaryButtonClick();
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        EditText editText = (EditText) view.findViewById(R.id.password_entry);
        this.mPasswordField = editText;
        editText.setOnEditorActionListener(new TextView.OnEditorActionListener() { // from class: com.android.car.settings.security.-$$Lambda$ChooseLockPinPasswordFragment$hic3j3iH_YGNgous1JC3JbzaaXM
            @Override // android.widget.TextView.OnEditorActionListener
            public final boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                return this.f$0.lambda$onViewCreated$2$ChooseLockPinPasswordFragment(textView, i, keyEvent);
            }
        });
        this.mPasswordField.addTextChangedListener(new TextWatcher() { // from class: com.android.car.settings.security.ChooseLockPinPasswordFragment.1
            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable s) {
                if (ChooseLockPinPasswordFragment.this.mUiStage != Stage.ConfirmWrong) {
                    if (ChooseLockPinPasswordFragment.this.mUiStage == Stage.PasswordInvalid) {
                        ChooseLockPinPasswordFragment.this.mUiStage = Stage.Introduction;
                    }
                } else {
                    ChooseLockPinPasswordFragment.this.mUiStage = Stage.NeedToConfirm;
                }
                if (!ChooseLockPinPasswordFragment.this.isResumed()) {
                    return;
                }
                ChooseLockPinPasswordFragment.this.mTextChangedHandler.notifyAfterTextChanged();
            }
        });
        this.mPasswordEntryInputDisabler = new TextViewInputDisabler(this.mPasswordField);
        this.mHintMessage = (TextView) view.findViewById(R.id.hint_text);
        if (this.mIsPin) {
            initPinView(view);
        } else {
            this.mPasswordField.requestFocus();
            InputMethodManager imm = (InputMethodManager) getContext().getSystemService("input_method");
            if (imm != null) {
                imm.showSoftInput(this.mPasswordField, 1);
            }
        }
        if (savedInstanceState != null) {
            this.mSaveLockWorker = (SaveLockWorker) getFragmentManager().findFragmentByTag(FRAGMENT_TAG_SAVE_PASSWORD_WORKER);
        }
    }

    public /* synthetic */ boolean lambda$onViewCreated$2$ChooseLockPinPasswordFragment(TextView textView, int actionId, KeyEvent keyEvent) {
        if (actionId == 0 || actionId == 6 || actionId == 5) {
            handlePrimaryButtonClick();
            return true;
        }
        return false;
    }

    @Override // com.android.car.settings.common.BaseFragment, androidx.fragment.app.Fragment
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        this.mProgressBar = getToolbar().getProgressBar();
    }

    @Override // com.android.car.settings.common.BaseFragment, androidx.fragment.app.Fragment
    public void onStart() {
        super.onStart();
        updateStage(this.mUiStage);
        SaveLockWorker saveLockWorker = this.mSaveLockWorker;
        if (saveLockWorker != null) {
            saveLockWorker.setListener(new $$Lambda$c7cvNnFjLPehJOSQLs5uqIaQC7s(this));
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(STATE_UI_STAGE, this.mUiStage.ordinal());
        outState.putParcelable(STATE_FIRST_ENTRY, this.mFirstEntry);
    }

    @Override // androidx.fragment.app.Fragment
    public void onStop() {
        super.onStop();
        SaveLockWorker saveLockWorker = this.mSaveLockWorker;
        if (saveLockWorker != null) {
            saveLockWorker.setListener(null);
        }
        this.mProgressBar.setVisible(false);
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        this.mPasswordField.setText((CharSequence) null);
        PasswordHelper.zeroizeCredentials(this.mCurrentEntry, this.mExistingCredential, this.mFirstEntry);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void appendToPasswordEntry(String text) {
        this.mPasswordField.append(text);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public LockscreenCredential getEnteredPassword() {
        if (this.mIsPin) {
            return LockscreenCredential.createPinOrNone(this.mPasswordField.getText());
        }
        return LockscreenCredential.createPasswordOrNone(this.mPasswordField.getText());
    }

    private void initPinView(View view) {
        this.mPinPad = (PinPadView) view.findViewById(R.id.pin_pad);
        PinPadView.PinPadClickListener pinPadClickListener = new PinPadView.PinPadClickListener() { // from class: com.android.car.settings.security.ChooseLockPinPasswordFragment.2
            @Override // com.android.car.settings.security.PinPadView.PinPadClickListener
            public void onDigitKeyClick(String digit) {
                ChooseLockPinPasswordFragment.this.appendToPasswordEntry(digit);
            }

            @Override // com.android.car.settings.security.PinPadView.PinPadClickListener
            public void onBackspaceClick() {
                LockscreenCredential pin = ChooseLockPinPasswordFragment.this.getEnteredPassword();
                if (pin.size() > 0) {
                    ChooseLockPinPasswordFragment.this.mPasswordField.getText().delete(ChooseLockPinPasswordFragment.this.mPasswordField.getSelectionEnd() - 1, ChooseLockPinPasswordFragment.this.mPasswordField.getSelectionEnd());
                }
                pin.zeroize();
            }

            @Override // com.android.car.settings.security.PinPadView.PinPadClickListener
            public void onEnterKeyClick() {
                ChooseLockPinPasswordFragment.this.handlePrimaryButtonClick();
            }
        };
        this.mPinPad.setPinPadClickListener(pinPadClickListener);
    }

    private boolean shouldEnableSubmit() {
        SaveLockWorker saveLockWorker;
        return getEnteredPassword().size() >= 4 && ((saveLockWorker = this.mSaveLockWorker) == null || saveLockWorker.isFinished());
    }

    private void updateSubmitButtonsState() {
        boolean enabled = shouldEnableSubmit();
        this.mPrimaryButton.setEnabled(enabled);
        if (this.mIsPin) {
            this.mPinPad.setEnterKeyEnabled(enabled);
        }
    }

    private void setPrimaryButtonText(int textId) {
        this.mPrimaryButton.setTitle(textId);
    }

    private void setSecondaryButtonEnabled(boolean enabled) {
        this.mSecondaryButton.setEnabled(enabled);
    }

    private void setSecondaryButtonText(int textId) {
        this.mSecondaryButton.setTitle(textId);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handlePrimaryButtonClick() {
        if (!shouldEnableSubmit()) {
            return;
        }
        this.mCurrentEntry = getEnteredPassword();
        int i = AnonymousClass3.$SwitchMap$com$android$car$settings$security$ChooseLockPinPasswordFragment$Stage[this.mUiStage.ordinal()];
        if (i == 1) {
            int iValidate = this.mPasswordHelper.validate(this.mCurrentEntry);
            this.mErrorCode = iValidate;
            if (iValidate == 0) {
                this.mFirstEntry = this.mCurrentEntry;
                this.mPasswordField.setText("");
                updateStage(Stage.NeedToConfirm);
                return;
            } else {
                updateStage(Stage.PasswordInvalid);
                this.mCurrentEntry.zeroize();
                return;
            }
        }
        if (i == 2 || i == 3) {
            if (Objects.equals(this.mFirstEntry, this.mCurrentEntry)) {
                startSaveAndFinish();
                return;
            }
            CharSequence tmp = this.mPasswordField.getText();
            if (tmp != null) {
                Selection.setSelection((Spannable) tmp, 0, tmp.length());
            }
            updateStage(Stage.ConfirmWrong);
            this.mCurrentEntry.zeroize();
        }
    }

    /* JADX INFO: renamed from: com.android.car.settings.security.ChooseLockPinPasswordFragment$3, reason: invalid class name */
    static /* synthetic */ class AnonymousClass3 {
        static final /* synthetic */ int[] $SwitchMap$com$android$car$settings$security$ChooseLockPinPasswordFragment$Stage;

        static {
            int[] iArr = new int[Stage.values().length];
            $SwitchMap$com$android$car$settings$security$ChooseLockPinPasswordFragment$Stage = iArr;
            try {
                iArr[Stage.Introduction.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$com$android$car$settings$security$ChooseLockPinPasswordFragment$Stage[Stage.NeedToConfirm.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                $SwitchMap$com$android$car$settings$security$ChooseLockPinPasswordFragment$Stage[Stage.SaveFailure.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                $SwitchMap$com$android$car$settings$security$ChooseLockPinPasswordFragment$Stage[Stage.PasswordInvalid.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                $SwitchMap$com$android$car$settings$security$ChooseLockPinPasswordFragment$Stage[Stage.ConfirmWrong.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
        }
    }

    private void handleSecondaryButtonClick() {
        if (this.mSaveLockWorker != null) {
            return;
        }
        if (this.mUiStage.secondaryButtonText == R.string.lockpassword_clear_label) {
            this.mPasswordField.setText("");
            Stage stage = Stage.Introduction;
            this.mUiStage = stage;
            setSecondaryButtonText(stage.secondaryButtonText);
            return;
        }
        getFragmentHost().goBack();
    }

    void onChosenLockSaveFinished(boolean isSaveSuccessful) {
        this.mProgressBar.setVisible(false);
        if (isSaveSuccessful) {
            onComplete();
        } else {
            updateStage(Stage.SaveFailure);
        }
    }

    private void startSaveAndFinish() {
        SaveLockWorker saveLockWorker = this.mSaveLockWorker;
        if (saveLockWorker != null && !saveLockWorker.isFinished()) {
            LOG.v("startSaveAndFinish with a running SaveAndFinishWorker.");
            return;
        }
        this.mPasswordEntryInputDisabler.setInputEnabled(false);
        if (this.mSaveLockWorker == null) {
            SaveLockWorker saveLockWorker2 = new SaveLockWorker();
            this.mSaveLockWorker = saveLockWorker2;
            saveLockWorker2.setListener(new $$Lambda$c7cvNnFjLPehJOSQLs5uqIaQC7s(this));
            getFragmentManager().beginTransaction().add(this.mSaveLockWorker, FRAGMENT_TAG_SAVE_PASSWORD_WORKER).commitNow();
        }
        this.mSaveLockWorker.start(this.mUserId, this.mCurrentEntry, this.mExistingCredential);
        this.mProgressBar.setVisible(true);
        updateSubmitButtonsState();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:27:0x0057  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void updateUi() {
        /*
            r4 = this;
            r4.updateSubmitButtonsState()
            com.android.car.settings.security.SaveLockWorker r0 = r4.mSaveLockWorker
            r1 = 1
            if (r0 == 0) goto L11
            boolean r0 = r0.isFinished()
            if (r0 == 0) goto Lf
            goto L11
        Lf:
            r0 = 0
            goto L12
        L11:
            r0 = r1
        L12:
            com.android.car.settings.security.ChooseLockPinPasswordFragment$Stage r2 = r4.mUiStage
            com.android.car.settings.security.ChooseLockPinPasswordFragment$Stage r3 = com.android.car.settings.security.ChooseLockPinPasswordFragment.Stage.Introduction
            if (r2 == r3) goto L1b
            r4.setSecondaryButtonEnabled(r0)
        L1b:
            boolean r2 = r4.mIsPin
            if (r2 == 0) goto L28
            com.android.car.settings.security.PinPadView r2 = r4.mPinPad
            com.android.car.settings.security.ChooseLockPinPasswordFragment$Stage r3 = r4.mUiStage
            int r3 = r3.enterKeyIcon
            r2.setEnterKeyIcon(r3)
        L28:
            int[] r2 = com.android.car.settings.security.ChooseLockPinPasswordFragment.AnonymousClass3.$SwitchMap$com$android$car$settings$security$ChooseLockPinPasswordFragment$Stage
            com.android.car.settings.security.ChooseLockPinPasswordFragment$Stage r3 = r4.mUiStage
            int r3 = r3.ordinal()
            r2 = r2[r3]
            if (r2 == r1) goto L67
            r1 = 2
            if (r2 == r1) goto L67
            r1 = 3
            if (r2 == r1) goto L57
            r1 = 4
            if (r2 == r1) goto L41
            r1 = 5
            if (r2 == r1) goto L57
            goto L7f
        L41:
            com.android.car.settings.security.PasswordHelper r1 = r4.mPasswordHelper
            android.content.Context r2 = r4.getContext()
            int r3 = r4.mErrorCode
            java.util.List r1 = r1.convertErrorCodeToMessages(r2, r3)
            java.lang.String r2 = " "
            java.lang.String r2 = java.lang.String.join(r2, r1)
            r4.setError(r2)
            goto L7f
        L57:
            com.android.car.settings.security.ChooseLockPinPasswordFragment$Stage r1 = r4.mUiStage
            boolean r2 = r4.mIsAlphaMode
            int r1 = r1.getHint(r2)
            java.lang.String r1 = r4.getString(r1)
            r4.setError(r1)
            goto L7f
        L67:
            android.widget.EditText r1 = r4.mPasswordField
            r2 = 0
            r1.setError(r2)
            android.widget.TextView r1 = r4.mHintMessage
            com.android.car.settings.security.ChooseLockPinPasswordFragment$Stage r2 = r4.mUiStage
            boolean r3 = r4.mIsAlphaMode
            int r2 = r2.getHint(r3)
            java.lang.String r2 = r4.getString(r2)
            r1.setText(r2)
        L7f:
            com.android.car.settings.security.ChooseLockPinPasswordFragment$Stage r1 = r4.mUiStage
            int r1 = r1.primaryButtonText
            r4.setPrimaryButtonText(r1)
            com.android.car.settings.security.ChooseLockPinPasswordFragment$Stage r1 = r4.mUiStage
            int r1 = r1.secondaryButtonText
            r4.setSecondaryButtonText(r1)
            com.android.internal.widget.TextViewInputDisabler r1 = r4.mPasswordEntryInputDisabler
            r1.setInputEnabled(r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.car.settings.security.ChooseLockPinPasswordFragment.updateUi():void");
    }

    private void setError(String message) {
        this.mHintMessage.setText(message);
    }

    void updateStage(Stage stage) {
        this.mUiStage = stage;
        updateUi();
    }

    void onComplete() {
        LockscreenCredential lockscreenCredential = this.mCurrentEntry;
        if (lockscreenCredential != null) {
            lockscreenCredential.zeroize();
        }
        LockscreenCredential lockscreenCredential2 = this.mExistingCredential;
        if (lockscreenCredential2 != null) {
            lockscreenCredential2.zeroize();
        }
        LockscreenCredential lockscreenCredential3 = this.mFirstEntry;
        if (lockscreenCredential3 != null) {
            lockscreenCredential3.zeroize();
        }
        this.mPasswordField.setText("");
        getActivity().finish();
    }

    enum Stage {
        Introduction(R.string.choose_lock_password_hints, R.string.choose_lock_pin_hints, R.string.continue_button_text, R.string.lockpassword_cancel_label, R.drawable.ic_arrow_forward),
        PasswordInvalid(R.string.lockpassword_invalid_password, R.string.lockpin_invalid_pin, R.string.continue_button_text, R.string.lockpassword_clear_label, R.drawable.ic_arrow_forward),
        NeedToConfirm(R.string.confirm_your_password_header, R.string.confirm_your_pin_header, R.string.lockpassword_confirm_label, R.string.lockpassword_cancel_label, R.drawable.ic_check),
        ConfirmWrong(R.string.confirm_passwords_dont_match, R.string.confirm_pins_dont_match, R.string.continue_button_text, R.string.lockpassword_cancel_label, R.drawable.ic_check),
        SaveFailure(R.string.error_saving_password, R.string.error_saving_lockpin, R.string.lockscreen_retry_button_text, R.string.lockpassword_cancel_label, R.drawable.ic_check);

        public final int alphaHint;
        public final int enterKeyIcon;
        public final int numericHint;
        public final int primaryButtonText;
        public final int secondaryButtonText;

        Stage(int hintInAlpha, int hintInNumeric, int primaryButtonText, int secondaryButtonText, int enterKeyIcon) {
            this.alphaHint = hintInAlpha;
            this.numericHint = hintInNumeric;
            this.primaryButtonText = primaryButtonText;
            this.secondaryButtonText = secondaryButtonText;
            this.enterKeyIcon = enterKeyIcon;
        }

        public int getHint(boolean isAlpha) {
            if (isAlpha) {
                return this.alphaHint;
            }
            return this.numericHint;
        }
    }

    private class TextChangedHandler extends Handler {
        private static final int DELAY_IN_MILLISECOND = 100;
        private static final int ON_TEXT_CHANGED = 1;

        private TextChangedHandler() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void notifyAfterTextChanged() {
            removeMessages(1);
            sendEmptyMessageDelayed(1, 100L);
        }

        @Override // android.os.Handler
        public void handleMessage(Message msg) {
            if (msg.what == 1) {
                ChooseLockPinPasswordFragment.this.mErrorCode = 0;
                ChooseLockPinPasswordFragment.this.updateUi();
            }
        }
    }
}
