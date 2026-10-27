package com.android.phone;

import android.app.ActionBar;
import android.app.Dialog;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.preference.Preference;
import android.preference.PreferenceScreen;
import android.view.MenuItem;
import com.android.internal.telephony.Phone;
import com.android.phone.settings.SuppServicesUiUtil;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public class GsmUmtsAdditionalCallOptions extends TimeConsumingPreferenceActivity {
    private CLIRListPreference mCLIRButton;
    private CallWaitingSwitchPreference mCWButton;
    private Phone mPhone;
    private SubscriptionInfoHelper mSubscriptionInfoHelper;
    private final ArrayList<Preference> mPreferences = new ArrayList<>();
    private int mInitIndex = 0;
    private boolean mShowCLIRButton = true;
    private boolean mShowCWButton = true;
    private boolean mCLIROverUtPrecautions = false;
    private boolean mCWOverUtPrecautions = false;

    @Override // android.preference.PreferenceActivity, android.app.Activity
    protected void onCreate(Bundle bundle) {
        PersistableBundle carrierConfig;
        CLIRListPreference cLIRListPreference;
        CallWaitingSwitchPreference callWaitingSwitchPreference;
        super.onCreate(bundle);
        addPreferencesFromResource(R.xml.gsm_umts_additional_options);
        SubscriptionInfoHelper subscriptionInfoHelper = new SubscriptionInfoHelper(this, getIntent());
        this.mSubscriptionInfoHelper = subscriptionInfoHelper;
        subscriptionInfoHelper.setActionBarTitle(getActionBar(), getResources(), R.string.additional_gsm_call_settings_with_label);
        this.mPhone = this.mSubscriptionInfoHelper.getPhone();
        PreferenceScreen preferenceScreen = getPreferenceScreen();
        this.mCLIRButton = (CLIRListPreference) preferenceScreen.findPreference("button_clir_key");
        this.mCWButton = (CallWaitingSwitchPreference) preferenceScreen.findPreference("button_cw_key");
        if (this.mSubscriptionInfoHelper.hasSubId()) {
            carrierConfig = PhoneGlobals.getInstance().getCarrierConfigForSubId(this.mSubscriptionInfoHelper.getSubId());
        } else {
            carrierConfig = PhoneGlobals.getInstance().getCarrierConfig();
        }
        if (carrierConfig != null) {
            this.mShowCLIRButton = carrierConfig.getBoolean("additional_settings_caller_id_visibility_bool");
            this.mShowCWButton = carrierConfig.getBoolean("additional_settings_call_waiting_visibility_bool");
            this.mCLIROverUtPrecautions = this.mShowCLIRButton && carrierConfig.getBoolean("caller_id_over_ut_warning_bool");
            this.mCWOverUtPrecautions = this.mShowCWButton && carrierConfig.getBoolean("call_waiting_over_ut_warning_bool");
        }
        boolean zIsSsOverUtPrecautions = SuppServicesUiUtil.isSsOverUtPrecautions(this, this.mPhone);
        CLIRListPreference cLIRListPreference2 = this.mCLIRButton;
        if (cLIRListPreference2 != null) {
            if (this.mShowCLIRButton) {
                if (this.mCLIROverUtPrecautions && zIsSsOverUtPrecautions) {
                    cLIRListPreference2.setEnabled(false);
                } else {
                    this.mPreferences.add(this.mCLIRButton);
                }
            } else {
                preferenceScreen.removePreference(cLIRListPreference2);
            }
        }
        CallWaitingSwitchPreference callWaitingSwitchPreference2 = this.mCWButton;
        if (callWaitingSwitchPreference2 != null) {
            if (this.mShowCWButton) {
                if (this.mCWOverUtPrecautions && zIsSsOverUtPrecautions) {
                    callWaitingSwitchPreference2.setEnabled(false);
                } else {
                    this.mPreferences.add(this.mCWButton);
                }
            } else {
                preferenceScreen.removePreference(callWaitingSwitchPreference2);
            }
        }
        if (this.mPreferences.size() != 0) {
            if (bundle == null) {
                doPreferenceInit(this.mInitIndex);
            } else {
                this.mInitIndex = this.mPreferences.size();
                if (this.mShowCWButton && (callWaitingSwitchPreference = this.mCWButton) != null && callWaitingSwitchPreference.isEnabled()) {
                    this.mCWButton.init(this, true, this.mPhone);
                }
                if (this.mShowCLIRButton && (cLIRListPreference = this.mCLIRButton) != null && cLIRListPreference.isEnabled()) {
                    this.mCLIRButton.init(this, true, this.mPhone);
                    int[] intArray = bundle.getIntArray(this.mCLIRButton.getKey());
                    if (intArray != null) {
                        this.mCLIRButton.handleGetCLIRResult(intArray);
                    } else {
                        this.mCLIRButton.init(this, false, this.mPhone);
                    }
                }
            }
        }
        ActionBar actionBar = getActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override // com.android.phone.TimeConsumingPreferenceActivity, android.app.Activity
    public void onResume() {
        CallWaitingSwitchPreference callWaitingSwitchPreference;
        CLIRListPreference cLIRListPreference;
        super.onResume();
        int size = this.mPreferences.size();
        boolean zIsSsOverUtPrecautions = SuppServicesUiUtil.isSsOverUtPrecautions(this, this.mPhone);
        dismissWarningDialog();
        if (this.mShowCLIRButton && this.mCLIROverUtPrecautions && (cLIRListPreference = this.mCLIRButton) != null) {
            if (zIsSsOverUtPrecautions) {
                showWarningDialog(201);
                if (this.mCLIRButton.isEnabled()) {
                    if (this.mPreferences.contains(this.mCLIRButton)) {
                        this.mPreferences.remove(this.mCLIRButton);
                    }
                    this.mCLIRButton.setEnabled(false);
                }
            } else if (!this.mPreferences.contains(cLIRListPreference)) {
                this.mCLIRButton.setEnabled(true);
                this.mPreferences.add(this.mCLIRButton);
            }
        }
        if (this.mShowCWButton && this.mCWOverUtPrecautions && (callWaitingSwitchPreference = this.mCWButton) != null) {
            if (zIsSsOverUtPrecautions) {
                showWarningDialog(202);
                if (this.mCWButton.isEnabled()) {
                    if (this.mPreferences.contains(this.mCWButton)) {
                        this.mPreferences.remove(this.mCWButton);
                    }
                    this.mCWButton.setEnabled(false);
                }
            } else if (!this.mPreferences.contains(callWaitingSwitchPreference)) {
                this.mCWButton.setEnabled(true);
                this.mPreferences.add(this.mCWButton);
            }
        }
        if (size < this.mPreferences.size()) {
            this.mInitIndex = size;
            doPreferenceInit(size);
        }
    }

    @Override // android.preference.PreferenceActivity, android.app.Activity
    protected void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        if (this.mShowCLIRButton) {
            CLIRListPreference cLIRListPreference = this.mCLIRButton;
            if (cLIRListPreference.clirArray != null) {
                bundle.putIntArray(cLIRListPreference.getKey(), this.mCLIRButton.clirArray);
            }
        }
    }

    @Override // com.android.phone.TimeConsumingPreferenceActivity, com.android.phone.TimeConsumingPreferenceListener
    public void onFinished(Preference preference, boolean z) {
        if (this.mInitIndex < this.mPreferences.size() - 1 && !isFinishing()) {
            int i = this.mInitIndex + 1;
            this.mInitIndex = i;
            doPreferenceInit(i);
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

    private void doPreferenceInit(int i) {
        if (this.mPreferences.size() > i) {
            Preference preference = this.mPreferences.get(i);
            if (preference instanceof CallWaitingSwitchPreference) {
                ((CallWaitingSwitchPreference) preference).init(this, false, this.mPhone);
            } else if (preference instanceof CLIRListPreference) {
                ((CLIRListPreference) preference).init(this, false, this.mPhone);
            }
        }
    }

    @Override // com.android.phone.TimeConsumingPreferenceActivity, android.app.Activity
    protected Dialog onCreateDialog(int i) {
        if (i == 201) {
            return SuppServicesUiUtil.showBlockingSuppServicesDialog(this, this.mPhone, "button_cw_key");
        }
        if (i == 202) {
            return SuppServicesUiUtil.showBlockingSuppServicesDialog(this, this.mPhone, "button_clir_key");
        }
        return super.onCreateDialog(i);
    }

    private void showWarningDialog(int i) {
        showDialog(i);
    }

    private void dismissWarningDialog() {
        dismissDialogSafely(201);
        dismissDialogSafely(202);
    }
}
