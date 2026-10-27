package com.android.phone;

import android.app.ActionBar;
import android.app.Dialog;
import android.os.AsyncResult;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.PersistableBundle;
import android.preference.Preference;
import android.preference.PreferenceScreen;
import android.telephony.CarrierConfigManager;
import android.telephony.SubscriptionManager;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;
import com.android.internal.telephony.CommandException;
import com.android.internal.telephony.Phone;
import com.android.phone.settings.fdn.EditPinPreference;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public class GsmUmtsCallBarringOptions extends TimeConsumingPreferenceActivity implements EditPinPreference.OnPinEnteredListener {
    private CallBarringEditPreference mButtonBAIC;
    private CallBarringEditPreference mButtonBAICr;
    private CallBarringEditPreference mButtonBAOC;
    private CallBarringEditPreference mButtonBAOIC;
    private CallBarringEditPreference mButtonBAOICxH;
    private EditPinPreference mButtonChangePW;
    private CallBarringDeselectAllPreference mButtonDisableAll;
    private boolean mFirstResume;
    private Bundle mIcicle;
    private String mNewPassword;
    private String mOldPassword;
    private Phone mPhone;
    private Dialog mProgressDialog;
    private int mPwChangeDialogStrId;
    private int mPwChangeState;
    private SubscriptionInfoHelper mSubscriptionInfoHelper;
    private ArrayList<CallBarringEditPreference> mPreferences = new ArrayList<>();
    private int mInitIndex = 0;
    private Handler mHandler = new Handler() { // from class: com.android.phone.GsmUmtsCallBarringOptions.1
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            AsyncResult asyncResult = (AsyncResult) message.obj;
            int i = message.what;
            if (i == 100) {
                GsmUmtsCallBarringOptions gsmUmtsCallBarringOptions = GsmUmtsCallBarringOptions.this;
                gsmUmtsCallBarringOptions.onFinished(gsmUmtsCallBarringOptions.mButtonChangePW, false);
                if (asyncResult.exception != null) {
                    GsmUmtsCallBarringOptions gsmUmtsCallBarringOptions2 = GsmUmtsCallBarringOptions.this;
                    gsmUmtsCallBarringOptions2.onException(gsmUmtsCallBarringOptions2.mButtonChangePW, (CommandException) asyncResult.exception);
                    GsmUmtsCallBarringOptions.this.mButtonChangePW.setEnabled(true);
                } else if (!(asyncResult.userObj instanceof Throwable)) {
                    GsmUmtsCallBarringOptions.this.displayMessage(R.string.call_barring_change_pwd_success);
                } else {
                    GsmUmtsCallBarringOptions gsmUmtsCallBarringOptions3 = GsmUmtsCallBarringOptions.this;
                    gsmUmtsCallBarringOptions3.onError(gsmUmtsCallBarringOptions3.mButtonChangePW, 400);
                }
                GsmUmtsCallBarringOptions.this.resetPwChangeState();
                return;
            }
            if (i != 200) {
                return;
            }
            GsmUmtsCallBarringOptions gsmUmtsCallBarringOptions4 = GsmUmtsCallBarringOptions.this;
            gsmUmtsCallBarringOptions4.onFinished(gsmUmtsCallBarringOptions4.mButtonDisableAll, false);
            if (asyncResult.exception != null) {
                GsmUmtsCallBarringOptions gsmUmtsCallBarringOptions5 = GsmUmtsCallBarringOptions.this;
                gsmUmtsCallBarringOptions5.onException(gsmUmtsCallBarringOptions5.mButtonDisableAll, (CommandException) asyncResult.exception);
                GsmUmtsCallBarringOptions.this.mButtonDisableAll.setEnabled(true);
            } else if (!(asyncResult.userObj instanceof Throwable)) {
                GsmUmtsCallBarringOptions.this.displayMessage(R.string.call_barring_deactivate_success);
                GsmUmtsCallBarringOptions.this.resetCallBarringPrefState(false);
            } else {
                GsmUmtsCallBarringOptions gsmUmtsCallBarringOptions6 = GsmUmtsCallBarringOptions.this;
                gsmUmtsCallBarringOptions6.onError(gsmUmtsCallBarringOptions6.mButtonDisableAll, 400);
            }
        }
    };

    @Override // com.android.phone.settings.fdn.EditPinPreference.OnPinEnteredListener
    public void onPinEntered(EditPinPreference editPinPreference, boolean z) {
        if (editPinPreference == this.mButtonChangePW) {
            updatePWChangeState(z);
        } else if (editPinPreference == this.mButtonDisableAll) {
            disableAllBarring(z);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void displayMessage(int i) {
        Toast.makeText(this, getString(i), 0).show();
    }

    private void disableAllBarring(boolean z) {
        if (z) {
            String text = this.mButtonDisableAll.getText();
            if (!validatePassword(text)) {
                this.mButtonDisableAll.setText("");
                displayMessage(R.string.call_barring_right_pwd_number);
            } else {
                this.mButtonDisableAll.setText("");
                this.mPhone.setCallBarring("AB", false, text, this.mHandler.obtainMessage(200), 0);
                onStarted(this.mButtonDisableAll, false);
            }
        }
    }

    private void updatePWChangeState(boolean z) {
        if (!z) {
            resetPwChangeState();
            return;
        }
        int i = this.mPwChangeState;
        if (i == 0) {
            this.mOldPassword = this.mButtonChangePW.getText();
            this.mButtonChangePW.setText("");
            if (validatePassword(this.mOldPassword)) {
                this.mPwChangeState = 1;
                displayPwChangeDialog();
                return;
            } else {
                displayPwChangeDialog(R.string.call_barring_right_pwd_number, true);
                return;
            }
        }
        if (i == 1) {
            this.mNewPassword = this.mButtonChangePW.getText();
            this.mButtonChangePW.setText("");
            if (validatePassword(this.mNewPassword)) {
                this.mPwChangeState = 2;
                displayPwChangeDialog();
                return;
            } else {
                displayPwChangeDialog(R.string.call_barring_right_pwd_number, true);
                return;
            }
        }
        if (i != 2) {
            return;
        }
        if (!this.mNewPassword.equals(this.mButtonChangePW.getText())) {
            this.mPwChangeState = 1;
            this.mButtonChangePW.setText("");
            displayPwChangeDialog(R.string.call_barring_pwd_not_match, true);
        } else {
            this.mButtonChangePW.setText("");
            this.mPhone.changeCallBarringPassword("AB", this.mOldPassword, this.mNewPassword, this.mHandler.obtainMessage(100));
            onStarted(this.mButtonChangePW, false);
        }
    }

    private void displayPwChangeDialog() {
        displayPwChangeDialog(0, true);
    }

    private void displayPwChangeDialog(int i, boolean z) {
        int i2 = this.mPwChangeState;
        int i3 = i2 != 0 ? i2 != 1 ? i2 != 2 ? 0 : R.string.call_barring_confirm_pwd : R.string.call_barring_new_pwd : R.string.call_barring_old_pwd;
        if (i != 0) {
            this.mButtonChangePW.setDialogMessage(((Object) getText(i3)) + "\n" + ((Object) getText(i)));
        } else {
            this.mButtonChangePW.setDialogMessage(i3);
        }
        if (z) {
            this.mButtonChangePW.showPinDialog();
        }
        this.mPwChangeDialogStrId = i;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void resetPwChangeState() {
        this.mPwChangeState = 0;
        displayPwChangeDialog(0, false);
        this.mOldPassword = "";
        this.mNewPassword = "";
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void resetCallBarringPrefState(boolean z) {
        for (CallBarringEditPreference callBarringEditPreference : this.mPreferences) {
            callBarringEditPreference.mIsActivated = z;
            callBarringEditPreference.updateSummaryText();
        }
    }

    private boolean validatePassword(String str) {
        return str != null && str.length() == 4;
    }

    @Override // android.preference.PreferenceActivity, android.app.Activity
    protected void onCreate(Bundle bundle) {
        PersistableBundle config;
        boolean z;
        boolean z2;
        super.onCreate(bundle);
        addPreferencesFromResource(R.xml.callbarring_options);
        SubscriptionInfoHelper subscriptionInfoHelper = new SubscriptionInfoHelper(this, getIntent());
        this.mSubscriptionInfoHelper = subscriptionInfoHelper;
        Phone phone = subscriptionInfoHelper.getPhone();
        this.mPhone = phone;
        CarrierConfigManager carrierConfigManager = (CarrierConfigManager) phone.getContext().getSystemService("carrier_config");
        if (this.mSubscriptionInfoHelper.hasSubId()) {
            config = carrierConfigManager.getConfigForSubId(this.mSubscriptionInfoHelper.getSubId());
        } else {
            config = carrierConfigManager.getConfig();
        }
        if (config != null) {
            z2 = config.getBoolean("call_barring_supports_password_change_bool", true);
            z = config.getBoolean("call_barring_supports_deactivate_all_bool", true);
        } else {
            Log.w("GsmUmtsCallBarringOptions", "Couldn't access CarrierConfig bundle");
            z = true;
            z2 = true;
        }
        PreferenceScreen preferenceScreen = getPreferenceScreen();
        this.mButtonBAOC = (CallBarringEditPreference) preferenceScreen.findPreference("button_baoc_key");
        this.mButtonBAOIC = (CallBarringEditPreference) preferenceScreen.findPreference("button_baoic_key");
        this.mButtonBAOICxH = (CallBarringEditPreference) preferenceScreen.findPreference("button_baoicxh_key");
        this.mButtonBAIC = (CallBarringEditPreference) preferenceScreen.findPreference("button_baic_key");
        this.mButtonBAICr = (CallBarringEditPreference) preferenceScreen.findPreference("button_baicr_key");
        this.mButtonDisableAll = (CallBarringDeselectAllPreference) preferenceScreen.findPreference("button_ba_all_key");
        this.mButtonChangePW = (EditPinPreference) preferenceScreen.findPreference("button_change_pw_key");
        if (!z) {
            preferenceScreen.removePreference(this.mButtonDisableAll);
        }
        if (!z2) {
            preferenceScreen.removePreference(this.mButtonChangePW);
        }
        this.mButtonBAOC.setOnPinEnteredListener(this);
        this.mButtonBAOIC.setOnPinEnteredListener(this);
        this.mButtonBAOICxH.setOnPinEnteredListener(this);
        this.mButtonBAIC.setOnPinEnteredListener(this);
        this.mButtonBAICr.setOnPinEnteredListener(this);
        this.mButtonDisableAll.setOnPinEnteredListener(this);
        this.mButtonChangePW.setOnPinEnteredListener(this);
        this.mPreferences.add(this.mButtonBAOC);
        this.mPreferences.add(this.mButtonBAOIC);
        this.mPreferences.add(this.mButtonBAOICxH);
        this.mPreferences.add(this.mButtonBAIC);
        this.mPreferences.add(this.mButtonBAICr);
        if (TelephonyManager.from(this).getSimState(SubscriptionManager.getSlotIndex(this.mPhone.getSubId())) == 5) {
            this.mButtonDisableAll.setEnabled(true);
            this.mButtonChangePW.setEnabled(true);
        } else {
            this.mButtonDisableAll.setEnabled(false);
            this.mButtonChangePW.setEnabled(false);
            this.mButtonChangePW.setSummary(R.string.call_barring_change_pwd_description_disabled);
        }
        this.mFirstResume = true;
        this.mIcicle = bundle;
        ActionBar actionBar = getActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        Bundle bundle2 = this.mIcicle;
        if (bundle2 == null || bundle2.getBoolean("PROGRESS_SHOWING")) {
            return;
        }
        this.mInitIndex = this.mPreferences.size();
        for (CallBarringEditPreference callBarringEditPreference : this.mPreferences) {
            Bundle bundle3 = (Bundle) this.mIcicle.getParcelable(callBarringEditPreference.getKey());
            if (bundle3 != null) {
                callBarringEditPreference.handleCallBarringResult(bundle3.getBoolean("toggle"));
                callBarringEditPreference.init(this, true, this.mPhone);
                callBarringEditPreference.setEnabled(bundle3.getBoolean("PREFERENCE_ENABLED", callBarringEditPreference.isEnabled()));
            }
        }
        this.mPwChangeState = this.mIcicle.getInt("pin_change_state_key");
        this.mOldPassword = this.mIcicle.getString("old_pw_key");
        this.mNewPassword = this.mIcicle.getString("new_pw_key");
        displayPwChangeDialog(this.mIcicle.getInt("dialog_message_key", this.mPwChangeDialogStrId), false);
        this.mButtonChangePW.setText(this.mIcicle.getString("dialog_pw_enter_key"));
    }

    @Override // com.android.phone.TimeConsumingPreferenceActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        if (this.mFirstResume) {
            Bundle bundle = this.mIcicle;
            if (bundle == null || bundle.getBoolean("PROGRESS_SHOWING")) {
                resetPwChangeState();
                this.mPreferences.get(this.mInitIndex).init(this, false, this.mPhone);
                removeDialog(200);
            }
            this.mFirstResume = false;
            this.mIcicle = null;
        }
    }

    @Override // android.preference.PreferenceActivity, android.app.Activity
    protected void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        for (CallBarringEditPreference callBarringEditPreference : this.mPreferences) {
            Bundle bundle2 = new Bundle();
            bundle2.putBoolean("toggle", callBarringEditPreference.mIsActivated);
            bundle2.putBoolean("PREFERENCE_ENABLED", callBarringEditPreference.isEnabled());
            bundle.putParcelable(callBarringEditPreference.getKey(), bundle2);
        }
        bundle.putInt("pin_change_state_key", this.mPwChangeState);
        bundle.putString("old_pw_key", this.mOldPassword);
        bundle.putString("new_pw_key", this.mNewPassword);
        bundle.putInt("dialog_message_key", this.mPwChangeDialogStrId);
        bundle.putString("dialog_pw_enter_key", this.mButtonChangePW.getText());
        Dialog dialog = this.mProgressDialog;
        bundle.putBoolean("PROGRESS_SHOWING", dialog != null && dialog.isShowing());
    }

    @Override // com.android.phone.TimeConsumingPreferenceActivity, com.android.phone.TimeConsumingPreferenceListener
    public void onFinished(Preference preference, boolean z) {
        if (this.mInitIndex < this.mPreferences.size() - 1 && !isFinishing()) {
            int i = this.mInitIndex + 1;
            this.mInitIndex = i;
            this.mPreferences.get(i).init(this, false, this.mPhone);
        }
        super.onFinished(preference, z);
    }

    @Override // android.preference.PreferenceActivity, android.app.Activity
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() == 16908332) {
            CallFeaturesSetting.goUpToTopLevelSetting(this, this.mSubscriptionInfoHelper);
            return true;
        }
        return super.onOptionsItemSelected(menuItem);
    }

    @Override // android.app.Activity
    protected void onPrepareDialog(int i, Dialog dialog, Bundle bundle) {
        super.onPrepareDialog(i, dialog, bundle);
        if (i == 100 || i == 200) {
            this.mProgressDialog = dialog;
        }
    }
}
