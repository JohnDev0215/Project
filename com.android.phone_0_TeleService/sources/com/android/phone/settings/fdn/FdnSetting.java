package com.android.phone.settings.fdn;

import android.app.ActionBar;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.AsyncResult;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.preference.PreferenceActivity;
import android.preference.PreferenceScreen;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;
import com.android.internal.telephony.CommandException;
import com.android.internal.telephony.Phone;
import com.android.phone.CallFeaturesSetting;
import com.android.phone.R;
import com.android.phone.SubscriptionInfoHelper;
import com.android.phone.settings.fdn.EditPinPreference;

/* JADX INFO: loaded from: classes.dex */
public class FdnSetting extends PreferenceActivity implements EditPinPreference.OnPinEnteredListener, DialogInterface.OnCancelListener {
    private EditPinPreference mButtonChangePin2;
    private EditPinPreference mButtonEnableFDN;
    private final Handler mFDNHandler = new Handler() { // from class: com.android.phone.settings.fdn.FdnSetting.1
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            int i = message.what;
            if (i == 100) {
                CommandException commandException = ((AsyncResult) message.obj).exception;
                if (commandException != null) {
                    if (!(commandException instanceof CommandException)) {
                        FdnSetting.this.displayMessage(R.string.pin2_error_exception);
                    } else {
                        int i2 = message.arg1;
                        int i3 = AnonymousClass2.$SwitchMap$com$android$internal$telephony$CommandException$Error[commandException.getCommandError().ordinal()];
                        if (i3 == 1) {
                            FdnSetting.this.displayMessage(R.string.fdn_enable_puk2_requested, i2);
                            FdnSetting.this.resetPinChangeStateForPUK2();
                        } else if (i3 != 2) {
                            FdnSetting.this.displayMessage(R.string.fdn_failed, i2);
                        } else {
                            FdnSetting.this.displayMessage(R.string.pin2_invalid, i2);
                        }
                    }
                }
                FdnSetting.this.updateEnableFDN();
                return;
            }
            if (i != 200) {
                return;
            }
            AsyncResult asyncResult = (AsyncResult) message.obj;
            Throwable th = asyncResult.exception;
            if (th == null) {
                if (FdnSetting.this.mPinChangeState == 3) {
                    FdnSetting.this.displayMessage(R.string.pin2_unblocked);
                } else {
                    FdnSetting.this.displayMessage(R.string.pin2_changed);
                }
                FdnSetting.this.resetPinChangeState();
                return;
            }
            if (!(th instanceof CommandException)) {
                FdnSetting.this.displayMessage(R.string.pin2_error_exception);
                return;
            }
            int i4 = message.arg1;
            FdnSetting.this.log("Handle EVENT_PIN2_CHANGE_COMPLETE attemptsRemaining=" + i4);
            if (asyncResult.exception.getCommandError() != CommandException.Error.SIM_PUK2) {
                if (!FdnSetting.this.mIsPuk2Locked) {
                    FdnSetting.this.displayMessage(R.string.badPin2, i4);
                    FdnSetting.this.resetPinChangeState();
                    return;
                } else {
                    FdnSetting.this.displayMessage(R.string.badPuk2, i4);
                    FdnSetting.this.resetPinChangeStateForPUK2();
                    return;
                }
            }
            AlertDialog alertDialogCreate = new AlertDialog.Builder(FdnSetting.this).setMessage(R.string.puk2_requested).setCancelable(true).setOnCancelListener(FdnSetting.this).setNeutralButton(android.R.string.ok, new DialogInterface.OnClickListener() { // from class: com.android.phone.settings.fdn.FdnSetting.1.1
                @Override // android.content.DialogInterface.OnClickListener
                public void onClick(DialogInterface dialogInterface, int i5) {
                    FdnSetting.this.resetPinChangeStateForPUK2();
                    FdnSetting.this.displayPinChangeDialog(0, true);
                }
            }).create();
            alertDialogCreate.getWindow().addFlags(2);
            alertDialogCreate.show();
        }
    };
    private boolean mIsPuk2Locked;
    private String mNewPin;
    private String mOldPin;
    private Phone mPhone;
    private int mPinChangeState;
    private String mPuk2;
    private SubscriptionInfoHelper mSubscriptionInfoHelper;

    @Override // com.android.phone.settings.fdn.EditPinPreference.OnPinEnteredListener
    public void onPinEntered(EditPinPreference editPinPreference, boolean z) {
        if (editPinPreference == this.mButtonEnableFDN) {
            toggleFDNEnable(z);
        } else if (editPinPreference == this.mButtonChangePin2) {
            updatePINChangeState(z);
        }
    }

    private void toggleFDNEnable(boolean z) {
        if (z) {
            String text = this.mButtonEnableFDN.getText();
            if (validatePin(text, false)) {
                this.mPhone.getIccCard().setIccFdnEnabled(!this.mPhone.getIccCard().getIccFdnEnabled(), text, this.mFDNHandler.obtainMessage(100));
            } else {
                displayMessage(R.string.invalidPin2);
            }
            this.mButtonEnableFDN.setText("");
        }
    }

    private void updatePINChangeState(boolean z) {
        if (!z) {
            if (!this.mIsPuk2Locked) {
                resetPinChangeState();
                return;
            } else {
                resetPinChangeStateForPUK2();
                return;
            }
        }
        int i = this.mPinChangeState;
        if (i == 0) {
            this.mOldPin = this.mButtonChangePin2.getText();
            this.mButtonChangePin2.setText("");
            if (validatePin(this.mOldPin, false)) {
                this.mPinChangeState = 1;
                displayPinChangeDialog();
                return;
            } else {
                displayPinChangeDialog(R.string.invalidPin2, true);
                return;
            }
        }
        if (i == 1) {
            this.mNewPin = this.mButtonChangePin2.getText();
            this.mButtonChangePin2.setText("");
            if (validatePin(this.mNewPin, false)) {
                this.mPinChangeState = 2;
                displayPinChangeDialog();
                return;
            } else {
                displayPinChangeDialog(R.string.invalidPin2, true);
                return;
            }
        }
        if (i == 2) {
            if (!this.mNewPin.equals(this.mButtonChangePin2.getText())) {
                this.mPinChangeState = 1;
                this.mButtonChangePin2.setText("");
                displayPinChangeDialog(R.string.mismatchPin2, true);
                return;
            } else {
                this.mButtonChangePin2.setText("");
                this.mPhone.getIccCard().changeIccFdnPassword(this.mOldPin, this.mNewPin, this.mFDNHandler.obtainMessage(200));
                return;
            }
        }
        if (i == 3) {
            this.mPuk2 = this.mButtonChangePin2.getText();
            this.mButtonChangePin2.setText("");
            if (validatePin(this.mPuk2, true)) {
                this.mPinChangeState = 4;
                displayPinChangeDialog();
                return;
            } else {
                displayPinChangeDialog(R.string.invalidPuk2, true);
                return;
            }
        }
        if (i == 4) {
            this.mNewPin = this.mButtonChangePin2.getText();
            this.mButtonChangePin2.setText("");
            if (validatePin(this.mNewPin, false)) {
                this.mPinChangeState = 5;
                displayPinChangeDialog();
                return;
            } else {
                displayPinChangeDialog(R.string.invalidPin2, true);
                return;
            }
        }
        if (i != 5) {
            return;
        }
        if (!this.mNewPin.equals(this.mButtonChangePin2.getText())) {
            this.mPinChangeState = 4;
            this.mButtonChangePin2.setText("");
            displayPinChangeDialog(R.string.mismatchPin2, true);
        } else {
            this.mButtonChangePin2.setText("");
            this.mPhone.getIccCard().supplyPuk2(this.mPuk2, this.mNewPin, this.mFDNHandler.obtainMessage(200));
        }
    }

    /* JADX INFO: renamed from: com.android.phone.settings.fdn.FdnSetting$2, reason: invalid class name */
    static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] $SwitchMap$com$android$internal$telephony$CommandException$Error;

        static {
            int[] iArr = new int[CommandException.Error.values().length];
            $SwitchMap$com$android$internal$telephony$CommandException$Error = iArr;
            try {
                iArr[CommandException.Error.SIM_PUK2.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$android$internal$telephony$CommandException$Error[CommandException.Error.PASSWORD_INCORRECT.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    @Override // android.content.DialogInterface.OnCancelListener
    public void onCancel(DialogInterface dialogInterface) {
        resetPinChangeStateForPUK2();
        displayPinChangeDialog(0, true);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void displayMessage(int i, int i2) {
        String string = getString(i);
        if (i == R.string.badPin2 || i == R.string.badPuk2 || i == R.string.pin2_invalid) {
            if (i2 >= 0) {
                string = getString(i) + getString(R.string.pin2_attempts, new Object[]{Integer.valueOf(i2)});
            } else {
                string = getString(i);
            }
        }
        log("displayMessage: attemptsRemaining=" + i2 + " s=" + string);
        Toast.makeText(this, string, 0).show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void displayMessage(int i) {
        displayMessage(i, -1);
    }

    private final void displayPinChangeDialog() {
        displayPinChangeDialog(0, true);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:13:0x0014  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0018  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void displayPinChangeDialog(int r4, boolean r5) {
        /*
            r3 = this;
            int r0 = r3.mPinChangeState
            if (r0 == 0) goto L1c
            r1 = 1
            if (r0 == r1) goto L18
            r1 = 2
            if (r0 == r1) goto L14
            r1 = 4
            if (r0 == r1) goto L18
            r1 = 5
            if (r0 == r1) goto L14
            r0 = 2131624404(0x7f0e01d4, float:1.8875987E38)
            goto L1f
        L14:
            r0 = 2131624201(0x7f0e0109, float:1.8875575E38)
            goto L1f
        L18:
            r0 = 2131624486(0x7f0e0226, float:1.8876153E38)
            goto L1f
        L1c:
            r0 = 2131624509(0x7f0e023d, float:1.88762E38)
        L1f:
            if (r4 == 0) goto L43
            com.android.phone.settings.fdn.EditPinPreference r1 = r3.mButtonChangePin2
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.CharSequence r0 = r3.getText(r0)
            r2.append(r0)
            java.lang.String r0 = "\n"
            r2.append(r0)
            java.lang.CharSequence r4 = r3.getText(r4)
            r2.append(r4)
            java.lang.String r4 = r2.toString()
            r1.setDialogMessage(r4)
            goto L48
        L43:
            com.android.phone.settings.fdn.EditPinPreference r4 = r3.mButtonChangePin2
            r4.setDialogMessage(r0)
        L48:
            if (r5 == 0) goto L4f
            com.android.phone.settings.fdn.EditPinPreference r3 = r3.mButtonChangePin2
            r3.showPinDialog()
        L4f:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.phone.settings.fdn.FdnSetting.displayPinChangeDialog(int, boolean):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void resetPinChangeState() {
        this.mPinChangeState = 0;
        displayPinChangeDialog(0, false);
        this.mNewPin = "";
        this.mOldPin = "";
        this.mIsPuk2Locked = false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void resetPinChangeStateForPUK2() {
        this.mPinChangeState = 3;
        displayPinChangeDialog(0, false);
        this.mPuk2 = "";
        this.mNewPin = "";
        this.mOldPin = "";
        this.mIsPuk2Locked = true;
    }

    private boolean validatePin(String str, boolean z) {
        return str != null && str.length() >= (z ? 8 : 4) && str.length() <= 8;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateEnableFDN() {
        if (this.mPhone.getIccCard().getIccFdnEnabled()) {
            this.mButtonEnableFDN.setTitle(R.string.enable_fdn_ok);
            this.mButtonEnableFDN.setSummary(R.string.fdn_enabled);
            this.mButtonEnableFDN.setDialogTitle(R.string.disable_fdn);
        } else {
            this.mButtonEnableFDN.setTitle(R.string.disable_fdn_ok);
            this.mButtonEnableFDN.setSummary(R.string.fdn_disabled);
            this.mButtonEnableFDN.setDialogTitle(R.string.enable_fdn);
        }
    }

    private void updateChangePIN2() {
        if (this.mPhone.getIccCard().getIccPin2Blocked()) {
            resetPinChangeStateForPUK2();
        } else {
            resetPinChangeState();
        }
    }

    @Override // android.preference.PreferenceActivity, android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        SubscriptionInfoHelper subscriptionInfoHelper = new SubscriptionInfoHelper(this, getIntent());
        this.mSubscriptionInfoHelper = subscriptionInfoHelper;
        this.mPhone = subscriptionInfoHelper.getPhone();
        addPreferencesFromResource(R.xml.fdn_setting);
        PreferenceScreen preferenceScreen = getPreferenceScreen();
        this.mButtonEnableFDN = (EditPinPreference) preferenceScreen.findPreference("button_fdn_enable_key");
        this.mButtonChangePin2 = (EditPinPreference) preferenceScreen.findPreference("button_change_pin2_key");
        this.mButtonEnableFDN.setOnPinEnteredListener(this);
        updateEnableFDN();
        this.mButtonChangePin2.setOnPinEnteredListener(this);
        ((PreferenceScreen) preferenceScreen.findPreference("fdn_list_pref_screen_key")).setIntent(this.mSubscriptionInfoHelper.getIntent(FdnList.class));
        if (bundle == null) {
            resetPinChangeState();
        } else {
            this.mIsPuk2Locked = bundle.getBoolean("skip_old_pin_key");
            this.mPinChangeState = bundle.getInt("pin_change_state_key");
            this.mOldPin = bundle.getString("old_pin_key");
            this.mNewPin = bundle.getString("new_pin_key");
            this.mButtonChangePin2.setDialogMessage(bundle.getString("dialog_message_key"));
            this.mButtonChangePin2.setText(bundle.getString("dialog_pin_entry_key"));
        }
        ActionBar actionBar = getActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            this.mSubscriptionInfoHelper.setActionBarTitle(actionBar, getResources(), R.string.fdn_with_label);
        }
    }

    @Override // android.app.Activity
    protected void onResume() {
        super.onResume();
        this.mPhone = this.mSubscriptionInfoHelper.getPhone();
        updateEnableFDN();
        updateChangePIN2();
    }

    @Override // android.preference.PreferenceActivity, android.app.Activity
    protected void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putBoolean("skip_old_pin_key", this.mIsPuk2Locked);
        bundle.putInt("pin_change_state_key", this.mPinChangeState);
        bundle.putString("old_pin_key", this.mOldPin);
        bundle.putString("new_pin_key", this.mNewPin);
        bundle.putString("dialog_message_key", this.mButtonChangePin2.getDialogMessage().toString());
        bundle.putString("dialog_pin_entry_key", this.mButtonChangePin2.getText());
    }

    @Override // android.preference.PreferenceActivity, android.app.Activity
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() == 16908332) {
            CallFeaturesSetting.goUpToTopLevelSetting(this, this.mSubscriptionInfoHelper);
            return true;
        }
        return super.onOptionsItemSelected(menuItem);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void log(String str) {
        Log.d("PhoneGlobals", "FdnSetting: " + str);
    }
}
