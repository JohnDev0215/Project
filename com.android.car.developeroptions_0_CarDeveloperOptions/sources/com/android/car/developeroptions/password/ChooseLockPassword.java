package com.android.car.developeroptions.password;

import android.app.admin.PasswordMetrics;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Insets;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Parcelable;
import android.text.Editable;
import android.text.Selection;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.util.Pair;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.android.car.developeroptions.R;
import com.android.car.developeroptions.SettingsActivity;
import com.android.car.developeroptions.SetupWizardUtils;
import com.android.car.developeroptions.Utils;
import com.android.car.developeroptions.core.InstrumentedFragment;
import com.android.car.developeroptions.intelligence.LogProto$SettingsLog;
import com.android.car.developeroptions.notification.RedactionInterstitial;
import com.android.car.developeroptions.password.SaveChosenLockWorkerBase;
import com.android.car.developeroptions.widget.ImeAwareEditText;
import com.android.internal.annotations.VisibleForTesting;
import com.android.internal.widget.LockPatternUtils;
import com.android.internal.widget.LockscreenCredential;
import com.android.internal.widget.PasswordValidationError;
import com.android.internal.widget.TextViewInputDisabler;
import com.google.android.setupcompat.template.FooterBarMixin;
import com.google.android.setupcompat.template.FooterButton;
import com.google.android.setupdesign.GlifLayout;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class ChooseLockPassword extends SettingsActivity {
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
            Intent intent = new Intent(context, (Class<?>) ChooseLockPassword.class);
            this.mIntent = intent;
            intent.putExtra("confirm_credentials", false);
            this.mIntent.putExtra("extra_require_password", false);
            this.mIntent.putExtra("has_challenge", false);
        }

        public IntentBuilder setPasswordQuality(int i) {
            this.mIntent.putExtra("lockscreen.password_type", i);
            return this;
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

        public IntentBuilder setPassword(LockscreenCredential lockscreenCredential) {
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

        public IntentBuilder setRequestedMinComplexity(int i) {
            this.mIntent.putExtra("requested_min_complexity", i);
            return this;
        }

        public Intent build() {
            return this.mIntent;
        }
    }

    @Override // com.android.car.developeroptions.SettingsActivity
    protected boolean isValidFragment(String str) {
        return ChooseLockPasswordFragment.class.getName().equals(str);
    }

    Class<? extends Fragment> getFragmentClass() {
        return ChooseLockPasswordFragment.class;
    }

    @Override // com.android.car.developeroptions.SettingsActivity, com.android.car.developeroptions.core.SettingsBaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        boolean booleanExtra = getIntent().getBooleanExtra("for_fingerprint", false);
        boolean booleanExtra2 = getIntent().getBooleanExtra("for_face", false);
        CharSequence text = getText(R.string.lockpassword_choose_your_screen_lock_header);
        if (booleanExtra) {
            text = getText(R.string.lockpassword_choose_your_password_header_for_fingerprint);
        } else if (booleanExtra2) {
            text = getText(R.string.lockpassword_choose_your_password_header_for_face);
        }
        setTitle(text);
        ((LinearLayout) findViewById(R.id.content_parent)).setFitsSystemWindows(false);
    }

    public static class ChooseLockPasswordFragment extends InstrumentedFragment implements TextView.OnEditorActionListener, TextWatcher, SaveChosenLockWorkerBase.Listener {
        private long mChallenge;
        private ChooseLockSettingsHelper mChooseLockSettingsHelper;
        private LockscreenCredential mChosenPassword;
        private LockscreenCredential mCurrentCredential;
        private LockscreenCredential mFirstPassword;
        protected boolean mForFace;
        protected boolean mForFingerprint;
        private boolean mHasChallenge;
        protected boolean mIsAlphaMode;
        private GlifLayout mLayout;
        private LockPatternUtils mLockPatternUtils;
        private TextView mMessage;
        private PasswordMetrics mMinMetrics;
        private FooterButton mNextButton;
        private ImeAwareEditText mPasswordEntry;
        private TextViewInputDisabler mPasswordEntryInputDisabler;
        private byte[] mPasswordHistoryHashFactor;
        private PasswordRequirementAdapter mPasswordRequirementAdapter;
        private RecyclerView mPasswordRestrictionView;
        private boolean mPasswordReused;
        private SaveAndFinishWorker mSaveAndFinishWorker;
        protected FooterButton mSkipOrClearButton;
        private TextChangedHandler mTextChangedHandler;
        protected int mUserId;
        private List<PasswordValidationError> mValidationErrors;
        private int mMinComplexity = 0;
        private int mRequestedQuality = 131072;
        protected Stage mUiStage = Stage.Introduction;

        @Override // android.text.TextWatcher
        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        @Override // com.android.settingslib.core.instrumentation.Instrumentable
        public int getMetricsCategory() {
            return 28;
        }

        @Override // android.text.TextWatcher
        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        protected int toVisibility(boolean z) {
            return z ? 0 : 8;
        }

        protected enum Stage {
            Introduction(R.string.lockpassword_choose_your_screen_lock_header, R.string.lockpassword_choose_your_password_header_for_fingerprint, R.string.lockpassword_choose_your_password_header_for_face, R.string.lockpassword_choose_your_screen_lock_header, R.string.lockpassword_choose_your_pin_header_for_fingerprint, R.string.lockpassword_choose_your_pin_header_for_face, R.string.lockpassword_choose_your_password_message, R.string.lock_settings_picker_biometrics_added_security_message, R.string.lockpassword_choose_your_pin_message, R.string.lock_settings_picker_biometrics_added_security_message, R.string.next_label),
            NeedToConfirm(R.string.lockpassword_confirm_your_password_header, R.string.lockpassword_confirm_your_password_header, R.string.lockpassword_confirm_your_password_header, R.string.lockpassword_confirm_your_pin_header, R.string.lockpassword_confirm_your_pin_header, R.string.lockpassword_confirm_your_pin_header, 0, 0, 0, 0, R.string.lockpassword_confirm_label),
            ConfirmWrong(R.string.lockpassword_confirm_passwords_dont_match, R.string.lockpassword_confirm_passwords_dont_match, R.string.lockpassword_confirm_passwords_dont_match, R.string.lockpassword_confirm_pins_dont_match, R.string.lockpassword_confirm_pins_dont_match, R.string.lockpassword_confirm_pins_dont_match, 0, 0, 0, 0, R.string.lockpassword_confirm_label);

            public final int alphaHint;
            public final int alphaHintForFace;
            public final int alphaHintForFingerprint;
            public final int alphaMessage;
            public final int alphaMessageForBiometrics;
            public final int buttonText;
            public final int numericHint;
            public final int numericHintForFace;
            public final int numericHintForFingerprint;
            public final int numericMessage;
            public final int numericMessageForBiometrics;

            Stage(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, int i10, int i11) {
                this.alphaHint = i;
                this.alphaHintForFingerprint = i2;
                this.alphaHintForFace = i3;
                this.numericHint = i4;
                this.numericHintForFingerprint = i5;
                this.numericHintForFace = i6;
                this.alphaMessage = i7;
                this.alphaMessageForBiometrics = i8;
                this.numericMessage = i9;
                this.numericMessageForBiometrics = i10;
                this.buttonText = i11;
            }

            public int getHint(boolean z, int i) {
                if (z) {
                    if (i == 1) {
                        return this.alphaHintForFingerprint;
                    }
                    if (i == 2) {
                        return this.alphaHintForFace;
                    }
                    return this.alphaHint;
                }
                if (i == 1) {
                    return this.numericHintForFingerprint;
                }
                if (i == 2) {
                    return this.numericHintForFace;
                }
                return this.numericHint;
            }

            public int getMessage(boolean z, int i) {
                return z ? i != 0 ? this.alphaMessageForBiometrics : this.alphaMessage : i != 0 ? this.numericMessageForBiometrics : this.numericMessage;
            }
        }

        @Override // com.android.settingslib.core.lifecycle.ObservableFragment, androidx.fragment.app.Fragment
        public void onCreate(Bundle bundle) {
            super.onCreate(bundle);
            this.mLockPatternUtils = new LockPatternUtils(getActivity());
            Intent intent = getActivity().getIntent();
            if (!(getActivity() instanceof ChooseLockPassword)) {
                throw new SecurityException("Fragment contained in wrong activity");
            }
            this.mUserId = Utils.getUserIdFromBundle(getActivity(), intent.getExtras());
            this.mForFingerprint = intent.getBooleanExtra("for_fingerprint", false);
            this.mForFace = intent.getBooleanExtra("for_face", false);
            this.mMinComplexity = intent.getIntExtra("requested_min_complexity", 0);
            this.mRequestedQuality = intent.getIntExtra("lockscreen.password_type", 131072);
            this.mMinMetrics = this.mLockPatternUtils.getRequestedPasswordMetrics(this.mUserId);
            this.mChooseLockSettingsHelper = new ChooseLockSettingsHelper(getActivity());
            if (intent.getBooleanExtra("for_cred_req_boot", false)) {
                SaveAndFinishWorker saveAndFinishWorker = new SaveAndFinishWorker();
                boolean booleanExtra = getActivity().getIntent().getBooleanExtra("extra_require_password", true);
                LockscreenCredential lockscreenCredential = (LockscreenCredential) intent.getParcelableExtra("password");
                saveAndFinishWorker.setBlocking(true);
                saveAndFinishWorker.setListener(this);
                saveAndFinishWorker.start(this.mChooseLockSettingsHelper.utils(), booleanExtra, false, 0L, lockscreenCredential, lockscreenCredential, this.mUserId);
            }
            this.mTextChangedHandler = new TextChangedHandler();
        }

        @Override // androidx.fragment.app.Fragment
        public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
            return layoutInflater.inflate(R.layout.choose_lock_password, viewGroup, false);
        }

        @Override // androidx.fragment.app.Fragment
        public void onViewCreated(View view, Bundle bundle) {
            super.onViewCreated(view, bundle);
            this.mLayout = (GlifLayout) view;
            ((ViewGroup) view.findViewById(R.id.password_container)).setOpticalInsets(Insets.NONE);
            FooterBarMixin footerBarMixin = (FooterBarMixin) this.mLayout.getMixin(FooterBarMixin.class);
            FooterButton.Builder builder = new FooterButton.Builder(getActivity());
            builder.setText(R.string.lockpassword_clear_label);
            builder.setListener(new View.OnClickListener() { // from class: com.android.car.developeroptions.password.-$$Lambda$dKS34aoIzTgGcReTpKRnHJ2GiQE
                @Override // android.view.View.OnClickListener
                public final void onClick(View view2) {
                    this.f$0.onSkipOrClearButtonClick(view2);
                }
            });
            builder.setButtonType(7);
            builder.setTheme(R.style.SudGlifButton_Secondary);
            footerBarMixin.setSecondaryButton(builder.build());
            FooterButton.Builder builder2 = new FooterButton.Builder(getActivity());
            builder2.setText(R.string.next_label);
            builder2.setListener(new View.OnClickListener() { // from class: com.android.car.developeroptions.password.-$$Lambda$jQiV6tvC2VaWh3XRp1HrzqWXjjI
                @Override // android.view.View.OnClickListener
                public final void onClick(View view2) {
                    this.f$0.onNextButtonClick(view2);
                }
            });
            builder2.setButtonType(5);
            builder2.setTheme(R.style.SudGlifButton_Primary);
            footerBarMixin.setPrimaryButton(builder2.build());
            this.mSkipOrClearButton = footerBarMixin.getSecondaryButton();
            this.mNextButton = footerBarMixin.getPrimaryButton();
            this.mMessage = (TextView) view.findViewById(R.id.message);
            if (this.mForFingerprint) {
                this.mLayout.setIcon(getActivity().getDrawable(R.drawable.ic_fingerprint_header));
            } else if (this.mForFace) {
                this.mLayout.setIcon(getActivity().getDrawable(R.drawable.ic_face_header));
            }
            int i = this.mRequestedQuality;
            this.mIsAlphaMode = 262144 == i || 327680 == i || 393216 == i;
            setupPasswordRequirementsView(view);
            this.mPasswordRestrictionView.setLayoutManager(new LinearLayoutManager(getActivity()));
            ImeAwareEditText imeAwareEditText = (ImeAwareEditText) view.findViewById(R.id.password_entry);
            this.mPasswordEntry = imeAwareEditText;
            imeAwareEditText.setOnEditorActionListener(this);
            this.mPasswordEntry.addTextChangedListener(this);
            this.mPasswordEntry.requestFocus();
            this.mPasswordEntryInputDisabler = new TextViewInputDisabler(this.mPasswordEntry);
            FragmentActivity activity = getActivity();
            int inputType = this.mPasswordEntry.getInputType();
            ImeAwareEditText imeAwareEditText2 = this.mPasswordEntry;
            if (!this.mIsAlphaMode) {
                inputType = 18;
            }
            imeAwareEditText2.setInputType(inputType);
            this.mPasswordEntry.setTypeface(Typeface.create(getContext().getString(android.R.string.config_customAdbPublicKeyConfirmationComponent), 0));
            Intent intent = getActivity().getIntent();
            boolean booleanExtra = intent.getBooleanExtra("confirm_credentials", true);
            this.mCurrentCredential = intent.getParcelableExtra("password");
            this.mHasChallenge = intent.getBooleanExtra("has_challenge", false);
            this.mChallenge = intent.getLongExtra("challenge", 0L);
            if (bundle == null) {
                updateStage(Stage.Introduction);
                if (booleanExtra) {
                    this.mChooseLockSettingsHelper.launchConfirmationActivity(58, getString(R.string.unlock_set_unlock_launch_picker_title), true, this.mUserId);
                }
            } else {
                this.mFirstPassword = bundle.getParcelable("first_password");
                String string = bundle.getString("ui_stage");
                if (string != null) {
                    Stage stageValueOf = Stage.valueOf(string);
                    this.mUiStage = stageValueOf;
                    updateStage(stageValueOf);
                }
                if (this.mCurrentCredential == null) {
                    this.mCurrentCredential = bundle.getParcelable("current_credential");
                }
                this.mSaveAndFinishWorker = (SaveAndFinishWorker) getFragmentManager().findFragmentByTag("save_and_finish_worker");
            }
            if (activity instanceof SettingsActivity) {
                int hint = Stage.Introduction.getHint(this.mIsAlphaMode, getStageType());
                ((SettingsActivity) activity).setTitle(hint);
                this.mLayout.setHeaderText(hint);
            }
        }

        private int getStageType() {
            if (this.mForFingerprint) {
                return 1;
            }
            return this.mForFace ? 2 : 0;
        }

        private void setupPasswordRequirementsView(View view) {
            RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.password_requirements_view);
            this.mPasswordRestrictionView = recyclerView;
            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
            PasswordRequirementAdapter passwordRequirementAdapter = new PasswordRequirementAdapter();
            this.mPasswordRequirementAdapter = passwordRequirementAdapter;
            this.mPasswordRestrictionView.setAdapter(passwordRequirementAdapter);
        }

        @Override // com.android.car.developeroptions.core.InstrumentedFragment, com.android.settingslib.core.lifecycle.ObservableFragment, androidx.fragment.app.Fragment
        public void onResume() {
            super.onResume();
            updateStage(this.mUiStage);
            SaveAndFinishWorker saveAndFinishWorker = this.mSaveAndFinishWorker;
            if (saveAndFinishWorker != null) {
                saveAndFinishWorker.setListener(this);
            } else {
                this.mPasswordEntry.requestFocus();
                this.mPasswordEntry.scheduleShowSoftInput();
            }
        }

        @Override // com.android.settingslib.core.lifecycle.ObservableFragment, androidx.fragment.app.Fragment
        public void onPause() {
            SaveAndFinishWorker saveAndFinishWorker = this.mSaveAndFinishWorker;
            if (saveAndFinishWorker != null) {
                saveAndFinishWorker.setListener(null);
            }
            super.onPause();
        }

        @Override // com.android.settingslib.core.lifecycle.ObservableFragment, androidx.fragment.app.Fragment
        public void onSaveInstanceState(Bundle bundle) {
            super.onSaveInstanceState(bundle);
            bundle.putString("ui_stage", this.mUiStage.name());
            bundle.putParcelable("first_password", this.mFirstPassword);
            bundle.putParcelable("current_credential", this.mCurrentCredential);
        }

        @Override // androidx.fragment.app.Fragment
        public void onActivityResult(int i, int i2, Intent intent) {
            super.onActivityResult(i, i2, intent);
            if (i != 58) {
                return;
            }
            if (i2 != -1) {
                getActivity().setResult(1);
                getActivity().finish();
            } else {
                this.mCurrentCredential = intent.getParcelableExtra("password");
            }
        }

        protected Intent getRedactionInterstitialIntent(Context context) {
            return RedactionInterstitial.createStartIntent(context, this.mUserId);
        }

        protected void updateStage(Stage stage) {
            Stage stage2 = this.mUiStage;
            this.mUiStage = stage;
            updateUi();
            if (stage2 != stage) {
                GlifLayout glifLayout = this.mLayout;
                glifLayout.announceForAccessibility(glifLayout.getHeaderText());
            }
        }

        @VisibleForTesting
        boolean validatePassword(LockscreenCredential lockscreenCredential) {
            byte[] credential = lockscreenCredential.getCredential();
            List<PasswordValidationError> listValidatePassword = PasswordMetrics.validatePassword(this.mMinMetrics, this.mMinComplexity, !this.mIsAlphaMode, credential);
            this.mValidationErrors = listValidatePassword;
            if (listValidatePassword.isEmpty()) {
                this.mPasswordReused = this.mLockPatternUtils.checkPasswordHistory(credential, getPasswordHistoryHashFactor(), this.mUserId);
            } else {
                this.mPasswordReused = false;
            }
            return this.mValidationErrors.isEmpty() && !this.mPasswordReused;
        }

        private byte[] getPasswordHistoryHashFactor() {
            if (this.mPasswordHistoryHashFactor == null) {
                LockPatternUtils lockPatternUtils = this.mLockPatternUtils;
                LockscreenCredential lockscreenCredentialCreateNone = this.mCurrentCredential;
                if (lockscreenCredentialCreateNone == null) {
                    lockscreenCredentialCreateNone = LockscreenCredential.createNone();
                }
                this.mPasswordHistoryHashFactor = lockPatternUtils.getPasswordHistoryHashFactor(lockscreenCredentialCreateNone, this.mUserId);
            }
            return this.mPasswordHistoryHashFactor;
        }

        public void handleNext() {
            if (this.mSaveAndFinishWorker != null) {
                return;
            }
            Editable text = this.mPasswordEntry.getText();
            if (TextUtils.isEmpty(text)) {
                return;
            }
            LockscreenCredential lockscreenCredentialCreatePassword = this.mIsAlphaMode ? LockscreenCredential.createPassword(text) : LockscreenCredential.createPin(text);
            this.mChosenPassword = lockscreenCredentialCreatePassword;
            Stage stage = this.mUiStage;
            if (stage == Stage.Introduction) {
                if (validatePassword(lockscreenCredentialCreatePassword)) {
                    this.mFirstPassword = this.mChosenPassword;
                    this.mPasswordEntry.setText("");
                    updateStage(Stage.NeedToConfirm);
                    return;
                }
                this.mChosenPassword.zeroize();
                return;
            }
            if (stage == Stage.NeedToConfirm) {
                if (lockscreenCredentialCreatePassword.equals(this.mFirstPassword)) {
                    startSaveAndFinish();
                    return;
                }
                Editable text2 = this.mPasswordEntry.getText();
                if (text2 != null) {
                    Selection.setSelection(text2, 0, text2.length());
                }
                updateStage(Stage.ConfirmWrong);
                this.mChosenPassword.zeroize();
            }
        }

        protected void setNextEnabled(boolean z) {
            this.mNextButton.setEnabled(z);
        }

        protected void setNextText(int i) {
            this.mNextButton.setText(getActivity(), i);
        }

        protected void onSkipOrClearButtonClick(View view) {
            this.mPasswordEntry.setText("");
        }

        protected void onNextButtonClick(View view) {
            handleNext();
        }

        @Override // android.widget.TextView.OnEditorActionListener
        public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
            if (i != 0 && i != 6 && i != 5) {
                return false;
            }
            handleNext();
            return true;
        }

        String[] convertErrorCodeToMessages() {
            ArrayList arrayList = new ArrayList();
            for (PasswordValidationError passwordValidationError : this.mValidationErrors) {
                switch (passwordValidationError.errorCode) {
                    case 2:
                        arrayList.add(getString(R.string.lockpassword_illegal_character));
                        break;
                    case 3:
                        Resources resources = getResources();
                        int i = this.mIsAlphaMode ? R.plurals.lockpassword_password_too_short : R.plurals.lockpassword_pin_too_short;
                        int i2 = passwordValidationError.requirement;
                        arrayList.add(resources.getQuantityString(i, i2, Integer.valueOf(i2)));
                        break;
                    case 4:
                        Resources resources2 = getResources();
                        int i3 = this.mIsAlphaMode ? R.plurals.lockpassword_password_too_long : R.plurals.lockpassword_pin_too_long;
                        int i4 = passwordValidationError.requirement;
                        arrayList.add(resources2.getQuantityString(i3, i4 + 1, Integer.valueOf(i4 + 1)));
                        break;
                    case 5:
                        arrayList.add(getString(R.string.lockpassword_pin_no_sequential_digits));
                        break;
                    case LogProto$SettingsLog.TIMESTAMP_FIELD_NUMBER /* 6 */:
                        Resources resources3 = getResources();
                        int i5 = passwordValidationError.requirement;
                        arrayList.add(resources3.getQuantityString(R.plurals.lockpassword_password_requires_letters, i5, Integer.valueOf(i5)));
                        break;
                    case 7:
                        Resources resources4 = getResources();
                        int i6 = passwordValidationError.requirement;
                        arrayList.add(resources4.getQuantityString(R.plurals.lockpassword_password_requires_uppercase, i6, Integer.valueOf(i6)));
                        break;
                    case 8:
                        Resources resources5 = getResources();
                        int i7 = passwordValidationError.requirement;
                        arrayList.add(resources5.getQuantityString(R.plurals.lockpassword_password_requires_lowercase, i7, Integer.valueOf(i7)));
                        break;
                    case 9:
                        Resources resources6 = getResources();
                        int i8 = passwordValidationError.requirement;
                        arrayList.add(resources6.getQuantityString(R.plurals.lockpassword_password_requires_numeric, i8, Integer.valueOf(i8)));
                        break;
                    case 10:
                        Resources resources7 = getResources();
                        int i9 = passwordValidationError.requirement;
                        arrayList.add(resources7.getQuantityString(R.plurals.lockpassword_password_requires_symbols, i9, Integer.valueOf(i9)));
                        break;
                    case 11:
                        Resources resources8 = getResources();
                        int i10 = passwordValidationError.requirement;
                        arrayList.add(resources8.getQuantityString(R.plurals.lockpassword_password_requires_nonletter, i10, Integer.valueOf(i10)));
                        break;
                    case 12:
                        Resources resources9 = getResources();
                        int i11 = passwordValidationError.requirement;
                        arrayList.add(resources9.getQuantityString(R.plurals.lockpassword_password_requires_nonnumerical, i11, Integer.valueOf(i11)));
                        break;
                    default:
                        Log.wtf("ChooseLockPassword", "unknown error validating password: " + passwordValidationError);
                        break;
                }
            }
            if (this.mPasswordReused) {
                arrayList.add(getString(this.mIsAlphaMode ? R.string.lockpassword_password_recently_used : R.string.lockpassword_pin_recently_used));
            }
            return (String[]) arrayList.toArray(new String[0]);
        }

        protected void updateUi() {
            LockscreenCredential lockscreenCredentialCreatePinOrNone;
            boolean z = this.mSaveAndFinishWorker == null;
            if (this.mIsAlphaMode) {
                lockscreenCredentialCreatePinOrNone = LockscreenCredential.createPasswordOrNone(this.mPasswordEntry.getText());
            } else {
                lockscreenCredentialCreatePinOrNone = LockscreenCredential.createPinOrNone(this.mPasswordEntry.getText());
            }
            int size = lockscreenCredentialCreatePinOrNone.size();
            if (this.mUiStage == Stage.Introduction) {
                this.mPasswordRestrictionView.setVisibility(0);
                boolean zValidatePassword = validatePassword(lockscreenCredentialCreatePinOrNone);
                this.mPasswordRequirementAdapter.setRequirements(convertErrorCodeToMessages());
                setNextEnabled(zValidatePassword);
            } else {
                this.mPasswordRestrictionView.setVisibility(8);
                setHeaderText(getString(this.mUiStage.getHint(this.mIsAlphaMode, getStageType())));
                setNextEnabled(z && size >= 4);
                this.mSkipOrClearButton.setVisibility(toVisibility(z && size > 0));
            }
            int message = this.mUiStage.getMessage(this.mIsAlphaMode, getStageType());
            if (message != 0) {
                this.mMessage.setVisibility(0);
                this.mMessage.setText(message);
            } else {
                this.mMessage.setVisibility(4);
            }
            setNextText(this.mUiStage.buttonText);
            this.mPasswordEntryInputDisabler.setInputEnabled(z);
            lockscreenCredentialCreatePinOrNone.zeroize();
        }

        private void setHeaderText(String str) {
            if (TextUtils.isEmpty(this.mLayout.getHeaderText()) || !this.mLayout.getHeaderText().toString().equals(str)) {
                this.mLayout.setHeaderText(str);
            }
        }

        @Override // android.text.TextWatcher
        public void afterTextChanged(Editable editable) {
            if (this.mUiStage == Stage.ConfirmWrong) {
                this.mUiStage = Stage.NeedToConfirm;
            }
            this.mTextChangedHandler.notifyAfterTextChanged();
        }

        private void startSaveAndFinish() {
            if (this.mSaveAndFinishWorker != null) {
                Log.w("ChooseLockPassword", "startSaveAndFinish with an existing SaveAndFinishWorker.");
                return;
            }
            this.mPasswordEntryInputDisabler.setInputEnabled(false);
            setNextEnabled(false);
            SaveAndFinishWorker saveAndFinishWorker = new SaveAndFinishWorker();
            this.mSaveAndFinishWorker = saveAndFinishWorker;
            saveAndFinishWorker.setListener(this);
            FragmentTransaction fragmentTransactionBeginTransaction = getFragmentManager().beginTransaction();
            fragmentTransactionBeginTransaction.add(this.mSaveAndFinishWorker, "save_and_finish_worker");
            fragmentTransactionBeginTransaction.commit();
            getFragmentManager().executePendingTransactions();
            this.mSaveAndFinishWorker.start(this.mLockPatternUtils, getActivity().getIntent().getBooleanExtra("extra_require_password", true), this.mHasChallenge, this.mChallenge, this.mChosenPassword, this.mCurrentCredential, this.mUserId);
        }

        @Override // com.android.car.developeroptions.password.SaveChosenLockWorkerBase.Listener
        public void onChosenLockSaveFinished(boolean z, Intent intent) {
            Intent redactionInterstitialIntent;
            getActivity().setResult(1, intent);
            LockscreenCredential lockscreenCredential = this.mChosenPassword;
            if (lockscreenCredential != null) {
                lockscreenCredential.zeroize();
            }
            LockscreenCredential lockscreenCredential2 = this.mCurrentCredential;
            if (lockscreenCredential2 != null) {
                lockscreenCredential2.zeroize();
            }
            LockscreenCredential lockscreenCredential3 = this.mFirstPassword;
            if (lockscreenCredential3 != null) {
                lockscreenCredential3.zeroize();
            }
            this.mPasswordEntry.setText("");
            if (!z && (redactionInterstitialIntent = getRedactionInterstitialIntent(getActivity())) != null) {
                startActivity(redactionInterstitialIntent);
            }
            getActivity().finish();
        }

        class TextChangedHandler extends Handler {
            TextChangedHandler() {
            }

            /* JADX INFO: Access modifiers changed from: private */
            public void notifyAfterTextChanged() {
                removeMessages(1);
                sendEmptyMessageDelayed(1, 100L);
            }

            @Override // android.os.Handler
            public void handleMessage(Message message) {
                if (ChooseLockPasswordFragment.this.getActivity() != null && message.what == 1) {
                    ChooseLockPasswordFragment.this.updateUi();
                }
            }
        }
    }

    public static class SaveAndFinishWorker extends SaveChosenLockWorkerBase {
        private LockscreenCredential mChosenPassword;
        private LockscreenCredential mCurrentCredential;

        public void start(LockPatternUtils lockPatternUtils, boolean z, boolean z2, long j, LockscreenCredential lockscreenCredential, LockscreenCredential lockscreenCredential2, int i) {
            prepare(lockPatternUtils, z, z2, j, i);
            this.mChosenPassword = lockscreenCredential;
            if (lockscreenCredential2 == null) {
                lockscreenCredential2 = LockscreenCredential.createNone();
            }
            this.mCurrentCredential = lockscreenCredential2;
            this.mUserId = i;
            start();
        }

        @Override // com.android.car.developeroptions.password.SaveChosenLockWorkerBase
        protected Pair<Boolean, Intent> saveAndVerifyInBackground() {
            boolean lockCredential = this.mUtils.setLockCredential(this.mChosenPassword, this.mCurrentCredential, this.mUserId);
            Intent intent = null;
            byte[] bArrVerifyCredential = null;
            intent = null;
            if (lockCredential && this.mHasChallenge) {
                try {
                    bArrVerifyCredential = this.mUtils.verifyCredential(this.mChosenPassword, this.mChallenge, this.mUserId);
                } catch (LockPatternUtils.RequestThrottledException unused) {
                }
                if (bArrVerifyCredential == null) {
                    Log.e("ChooseLockPassword", "critical: no token returned for known good password.");
                }
                Intent intent2 = new Intent();
                intent2.putExtra("hw_auth_token", bArrVerifyCredential);
                intent = intent2;
            }
            return Pair.create(Boolean.valueOf(lockCredential), intent);
        }
    }
}
