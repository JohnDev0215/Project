package com.android.car.settings.security;

import android.content.Context;
import android.os.Bundle;
import android.os.UserHandle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import com.android.car.settings.R;
import com.android.car.settings.common.BaseFragment;
import com.android.car.settings.security.ConfirmLockLockoutHelper;
import com.android.car.settings.security.PinPadView;
import com.android.internal.widget.LockscreenCredential;
import com.android.internal.widget.TextViewInputDisabler;

/* JADX INFO: loaded from: classes5.dex */
public class ConfirmLockPinPasswordFragment extends BaseFragment {
    private static final String EXTRA_IS_PIN = "extra_is_pin";
    private static final String FRAGMENT_TAG_CHECK_LOCK_WORKER = "check_lock_worker";
    private CheckLockListener mCheckLockListener;
    private CheckLockWorker mCheckLockWorker;
    private ConfirmLockLockoutHelper mConfirmLockLockoutHelper;
    private LockscreenCredential mEnteredPassword;
    private InputMethodManager mImm;
    private boolean mIsPin;
    private TextView mMsgView;
    private TextViewInputDisabler mPasswordEntryInputDisabler;
    private EditText mPasswordField;
    private PinPadView mPinPad;
    private int mUserId;

    public static ConfirmLockPinPasswordFragment newPinInstance() {
        ConfirmLockPinPasswordFragment patternFragment = new ConfirmLockPinPasswordFragment();
        Bundle bundle = new Bundle();
        bundle.putBoolean(EXTRA_IS_PIN, true);
        patternFragment.setArguments(bundle);
        return patternFragment;
    }

    public static ConfirmLockPinPasswordFragment newPasswordInstance() {
        ConfirmLockPinPasswordFragment patternFragment = new ConfirmLockPinPasswordFragment();
        Bundle bundle = new Bundle();
        bundle.putBoolean(EXTRA_IS_PIN, false);
        patternFragment.setArguments(bundle);
        return patternFragment;
    }

    @Override // com.android.car.settings.common.BaseFragment
    protected int getLayoutId() {
        return this.mIsPin ? R.layout.confirm_lock_pin : R.layout.confirm_lock_password;
    }

    @Override // com.android.car.settings.common.BaseFragment
    protected int getTitleId() {
        return R.string.security_settings_title;
    }

    @Override // com.android.car.settings.common.BaseFragment, androidx.fragment.app.Fragment
    public void onAttach(Context context) {
        super.onAttach(context);
        if (getActivity() instanceof CheckLockListener) {
            this.mCheckLockListener = (CheckLockListener) getActivity();
            this.mUserId = UserHandle.myUserId();
            this.mConfirmLockLockoutHelper = ConfirmLockLockoutHelper.getInstance(requireContext(), this.mUserId);
            this.mImm = (InputMethodManager) requireContext().getSystemService(InputMethodManager.class);
            return;
        }
        throw new RuntimeException("The activity must implement CheckLockListener");
    }

    @Override // androidx.fragment.app.Fragment
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = getArguments();
        if (args != null) {
            this.mIsPin = args.getBoolean(EXTRA_IS_PIN);
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.mPasswordField = (EditText) view.findViewById(R.id.password_entry);
        this.mPasswordEntryInputDisabler = new TextViewInputDisabler(this.mPasswordField);
        this.mMsgView = (TextView) view.findViewById(R.id.message);
        this.mConfirmLockLockoutHelper.setConfirmLockUIController(new ConfirmLockLockoutHelper.ConfirmLockUIController() { // from class: com.android.car.settings.security.ConfirmLockPinPasswordFragment.1
            @Override // com.android.car.settings.security.ConfirmLockLockoutHelper.ConfirmLockUIController
            public void setErrorText(String text) {
                ConfirmLockPinPasswordFragment.this.mMsgView.setText(text);
            }

            @Override // com.android.car.settings.security.ConfirmLockLockoutHelper.ConfirmLockUIController
            public void refreshUI(boolean isLockedOut) {
                if (ConfirmLockPinPasswordFragment.this.mIsPin) {
                    ConfirmLockPinPasswordFragment.this.updatePinEntry(isLockedOut);
                } else {
                    ConfirmLockPinPasswordFragment.this.updatePasswordEntry(isLockedOut);
                }
            }
        });
        if (this.mIsPin) {
            initPinView(view);
        } else {
            initPasswordView();
        }
        if (savedInstanceState != null) {
            this.mCheckLockWorker = (CheckLockWorker) getFragmentManager().findFragmentByTag(FRAGMENT_TAG_CHECK_LOCK_WORKER);
        }
    }

    @Override // com.android.car.settings.common.BaseFragment, androidx.fragment.app.Fragment
    public void onStart() {
        super.onStart();
        CheckLockWorker checkLockWorker = this.mCheckLockWorker;
        if (checkLockWorker != null) {
            checkLockWorker.setListener(new $$Lambda$IhK6Bu3cYWNUS1qQQjvwEsfSWJw(this));
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        this.mConfirmLockLockoutHelper.onResumeUI();
    }

    @Override // androidx.fragment.app.Fragment
    public void onPause() {
        super.onPause();
        this.mConfirmLockLockoutHelper.onPauseUI();
    }

    @Override // androidx.fragment.app.Fragment
    public void onStop() {
        super.onStop();
        CheckLockWorker checkLockWorker = this.mCheckLockWorker;
        if (checkLockWorker != null) {
            checkLockWorker.setListener(null);
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        this.mPasswordField.setText((CharSequence) null);
        PasswordHelper.zeroizeCredentials(this.mEnteredPassword);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void initCheckLockWorker() {
        if (this.mCheckLockWorker == null) {
            CheckLockWorker checkLockWorker = new CheckLockWorker();
            this.mCheckLockWorker = checkLockWorker;
            checkLockWorker.setListener(new $$Lambda$IhK6Bu3cYWNUS1qQQjvwEsfSWJw(this));
            getFragmentManager().beginTransaction().add(this.mCheckLockWorker, FRAGMENT_TAG_CHECK_LOCK_WORKER).commitNow();
        }
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
        PinPadView.PinPadClickListener pinPadClickListener = new PinPadView.PinPadClickListener() { // from class: com.android.car.settings.security.ConfirmLockPinPasswordFragment.2
            @Override // com.android.car.settings.security.PinPadView.PinPadClickListener
            public void onDigitKeyClick(String digit) {
                ConfirmLockPinPasswordFragment.this.clearError();
                ConfirmLockPinPasswordFragment.this.mPasswordField.append(digit);
            }

            @Override // com.android.car.settings.security.PinPadView.PinPadClickListener
            public void onBackspaceClick() {
                ConfirmLockPinPasswordFragment.this.clearError();
                if (!TextUtils.isEmpty(ConfirmLockPinPasswordFragment.this.mPasswordField.getText())) {
                    ConfirmLockPinPasswordFragment.this.mPasswordField.getText().delete(ConfirmLockPinPasswordFragment.this.mPasswordField.getSelectionEnd() - 1, ConfirmLockPinPasswordFragment.this.mPasswordField.getSelectionEnd());
                }
            }

            @Override // com.android.car.settings.security.PinPadView.PinPadClickListener
            public void onEnterKeyClick() {
                ConfirmLockPinPasswordFragment confirmLockPinPasswordFragment = ConfirmLockPinPasswordFragment.this;
                confirmLockPinPasswordFragment.mEnteredPassword = confirmLockPinPasswordFragment.getEnteredPassword();
                if (!ConfirmLockPinPasswordFragment.this.mEnteredPassword.isNone()) {
                    ConfirmLockPinPasswordFragment.this.initCheckLockWorker();
                    ConfirmLockPinPasswordFragment.this.mPinPad.setEnabled(false);
                    ConfirmLockPinPasswordFragment.this.mCheckLockWorker.checkPinPassword(ConfirmLockPinPasswordFragment.this.mUserId, ConfirmLockPinPasswordFragment.this.mEnteredPassword);
                }
            }
        };
        this.mPinPad.setPinPadClickListener(pinPadClickListener);
    }

    private void initPasswordView() {
        this.mPasswordField.setOnEditorActionListener(new TextView.OnEditorActionListener() { // from class: com.android.car.settings.security.-$$Lambda$ConfirmLockPinPasswordFragment$FZhME7_fSAlpqrquXVHMNly3KOM
            @Override // android.widget.TextView.OnEditorActionListener
            public final boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                return this.f$0.lambda$initPasswordView$0$ConfirmLockPinPasswordFragment(textView, i, keyEvent);
            }
        });
        this.mPasswordField.addTextChangedListener(new TextWatcher() { // from class: com.android.car.settings.security.ConfirmLockPinPasswordFragment.3
            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable s) {
                ConfirmLockPinPasswordFragment.this.clearError();
            }
        });
    }

    public /* synthetic */ boolean lambda$initPasswordView$0$ConfirmLockPinPasswordFragment(TextView textView, int actionId, KeyEvent keyEvent) {
        if (actionId == 0 || actionId == 6 || actionId == 5) {
            initCheckLockWorker();
            if (!this.mCheckLockWorker.isCheckInProgress()) {
                LockscreenCredential enteredPassword = getEnteredPassword();
                this.mEnteredPassword = enteredPassword;
                this.mCheckLockWorker.checkPinPassword(this.mUserId, enteredPassword);
                return true;
            }
            return true;
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearError() {
        if (!TextUtils.isEmpty(this.mMsgView.getText())) {
            this.mMsgView.setText("");
        }
    }

    private void hideKeyboard() {
        View currentFocus = getActivity().getCurrentFocus();
        if (currentFocus == null) {
            currentFocus = getActivity().getWindow().getDecorView();
        }
        if (currentFocus != null) {
            InputMethodManager inputMethodManager = (InputMethodManager) currentFocus.getContext().getSystemService("input_method");
            inputMethodManager.hideSoftInputFromWindow(currentFocus.getWindowToken(), 0);
        }
    }

    void onCheckCompleted(boolean lockMatched, int timeoutMs) {
        if (lockMatched) {
            this.mCheckLockListener.onLockVerified(this.mEnteredPassword);
        } else if (timeoutMs > 0) {
            this.mConfirmLockLockoutHelper.onCheckCompletedWithTimeout(timeoutMs);
        } else {
            this.mMsgView.setText(this.mIsPin ? R.string.lockscreen_wrong_pin : R.string.lockscreen_wrong_password);
            if (this.mIsPin) {
                this.mPinPad.setEnabled(true);
            }
        }
        if (!this.mIsPin) {
            hideKeyboard();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updatePasswordEntry(boolean isLockedOut) {
        this.mPasswordField.setEnabled(!isLockedOut);
        this.mPasswordEntryInputDisabler.setInputEnabled(!isLockedOut);
        if (isLockedOut) {
            InputMethodManager inputMethodManager = this.mImm;
            if (inputMethodManager != null) {
                inputMethodManager.hideSoftInputFromWindow(this.mPasswordField.getWindowToken(), 0);
                return;
            }
            return;
        }
        this.mPasswordField.requestFocus();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updatePinEntry(boolean isLockedOut) {
        this.mPinPad.setEnabled(!isLockedOut);
        if (isLockedOut) {
            InputMethodManager inputMethodManager = this.mImm;
            if (inputMethodManager != null) {
                inputMethodManager.hideSoftInputFromWindow(this.mPinPad.getWindowToken(), 0);
                return;
            }
            return;
        }
        this.mPinPad.requestFocus();
    }
}
