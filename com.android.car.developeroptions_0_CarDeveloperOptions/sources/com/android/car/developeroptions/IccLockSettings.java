package com.android.car.developeroptions;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Resources;
import android.os.AsyncResult;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.telephony.SubscriptionInfo;
import android.telephony.SubscriptionManager;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowInsets;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;
import androidx.preference.Preference;
import androidx.preference.SwitchPreference;
import com.android.car.developeroptions.EditPinPreference;
import com.android.internal.telephony.CommandException;
import com.android.internal.telephony.Phone;
import com.android.internal.telephony.PhoneFactory;

/* JADX INFO: loaded from: classes.dex */
public class IccLockSettings extends SettingsPreferenceFragment implements EditPinPreference.OnPinEnteredListener {
    private String mError;
    private String mNewPin;
    private String mOldPin;
    private Phone mPhone;
    private String mPin;
    private EditPinPreference mPinDialog;
    private SwitchPreference mPinToggle;
    private Resources mRes;
    private TabHost mTabHost;
    private boolean mToState;
    private int mDialogState = 0;
    private Handler mHandler = new Handler() { // from class: com.android.car.developeroptions.IccLockSettings.1
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            AsyncResult asyncResult = (AsyncResult) message.obj;
            switch (message.what) {
                case 100:
                    IccLockSettings.this.iccLockChanged(asyncResult.exception == null, message.arg1, asyncResult.exception);
                    break;
                case 101:
                    IccLockSettings.this.iccPinChanged(asyncResult.exception == null, message.arg1);
                    break;
                case 102:
                    IccLockSettings.this.updatePreferences();
                    break;
            }
        }
    };
    private final BroadcastReceiver mSimStateReceiver = new BroadcastReceiver() { // from class: com.android.car.developeroptions.IccLockSettings.2
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if ("android.intent.action.SIM_STATE_CHANGED".equals(intent.getAction())) {
                IccLockSettings.this.mHandler.sendMessage(IccLockSettings.this.mHandler.obtainMessage(102));
            }
        }
    };
    private TabHost.OnTabChangeListener mTabListener = new TabHost.OnTabChangeListener() { // from class: com.android.car.developeroptions.IccLockSettings.4
        @Override // android.widget.TabHost.OnTabChangeListener
        public void onTabChanged(String str) {
            SubscriptionInfo activeSubscriptionInfoForSimSlotIndex = SubscriptionManager.from(IccLockSettings.this.getActivity().getBaseContext()).getActiveSubscriptionInfoForSimSlotIndex(Integer.parseInt(str));
            IccLockSettings.this.mPhone = activeSubscriptionInfoForSimSlotIndex == null ? null : PhoneFactory.getPhone(SubscriptionManager.getPhoneId(activeSubscriptionInfoForSimSlotIndex.getSubscriptionId()));
            IccLockSettings.this.updatePreferences();
        }
    };
    private TabHost.TabContentFactory mEmptyTabContent = new TabHost.TabContentFactory() { // from class: com.android.car.developeroptions.IccLockSettings.5
        @Override // android.widget.TabHost.TabContentFactory
        public View createTabContent(String str) {
            return new View(IccLockSettings.this.mTabHost.getContext());
        }
    };

    @Override // com.android.car.developeroptions.support.actionbar.HelpResourceProvider
    public int getHelpResource() {
        return R.string.help_url_icc_lock;
    }

    @Override // com.android.settingslib.core.instrumentation.Instrumentable
    public int getMetricsCategory() {
        return 56;
    }

    @Override // com.android.car.developeroptions.SettingsPreferenceFragment, com.android.settingslib.core.lifecycle.ObservablePreferenceFragment, androidx.preference.PreferenceFragmentCompat, androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (Utils.isMonkeyRunning()) {
            finish();
            return;
        }
        addPreferencesFromResource(R.xml.sim_lock_settings);
        this.mPinDialog = (EditPinPreference) findPreference("sim_pin");
        this.mPinToggle = (SwitchPreference) findPreference("sim_toggle");
        if (bundle != null && bundle.containsKey("dialogState")) {
            this.mDialogState = bundle.getInt("dialogState");
            this.mPin = bundle.getString("dialogPin");
            this.mError = bundle.getString("dialogError");
            this.mToState = bundle.getBoolean("enableState");
            int i = this.mDialogState;
            if (i == 3) {
                this.mOldPin = bundle.getString("oldPinCode");
            } else if (i == 4) {
                this.mOldPin = bundle.getString("oldPinCode");
                this.mNewPin = bundle.getString("newPinCode");
            }
        }
        this.mPinDialog.setOnPinEnteredListener(this);
        getPreferenceScreen().setPersistent(false);
        this.mRes = getResources();
    }

    @Override // com.android.car.developeroptions.SettingsPreferenceFragment, androidx.preference.PreferenceFragmentCompat, androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        CharSequence displayName;
        int simCount = ((TelephonyManager) getContext().getSystemService("phone")).getSimCount();
        if (simCount > 1) {
            View viewInflate = layoutInflater.inflate(R.layout.icc_lock_tabs, viewGroup, false);
            ViewGroup viewGroup2 = (ViewGroup) viewInflate.findViewById(R.id.prefs_container);
            Utils.prepareCustomPreferencesList(viewGroup, viewInflate, viewGroup2, false);
            viewGroup2.addView(super.onCreateView(layoutInflater, viewGroup2, bundle));
            this.mTabHost = (TabHost) viewInflate.findViewById(android.R.id.tabhost);
            this.mTabHost.setup();
            this.mTabHost.setOnTabChangedListener(this.mTabListener);
            this.mTabHost.clearAllTabs();
            SubscriptionManager subscriptionManagerFrom = SubscriptionManager.from(getContext());
            for (int i = 0; i < simCount; i++) {
                SubscriptionInfo activeSubscriptionInfoForSimSlotIndex = subscriptionManagerFrom.getActiveSubscriptionInfoForSimSlotIndex(i);
                TabHost tabHost = this.mTabHost;
                String strValueOf = String.valueOf(i);
                if (activeSubscriptionInfoForSimSlotIndex == null) {
                    displayName = getContext().getString(R.string.sim_editor_title, Integer.valueOf(i + 1));
                } else {
                    displayName = activeSubscriptionInfoForSimSlotIndex.getDisplayName();
                }
                tabHost.addTab(buildTabSpec(strValueOf, String.valueOf(displayName)));
            }
            SubscriptionInfo activeSubscriptionInfoForSimSlotIndex2 = subscriptionManagerFrom.getActiveSubscriptionInfoForSimSlotIndex(0);
            this.mPhone = activeSubscriptionInfoForSimSlotIndex2 == null ? null : PhoneFactory.getPhone(SubscriptionManager.getPhoneId(activeSubscriptionInfoForSimSlotIndex2.getSubscriptionId()));
            if (bundle != null && bundle.containsKey("currentTab")) {
                this.mTabHost.setCurrentTabByTag(bundle.getString("currentTab"));
            }
            return viewInflate;
        }
        this.mPhone = PhoneFactory.getDefaultPhone();
        return super.onCreateView(layoutInflater, viewGroup, bundle);
    }

    @Override // androidx.preference.PreferenceFragmentCompat, androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        updatePreferences();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updatePreferences() {
        EditPinPreference editPinPreference = this.mPinDialog;
        if (editPinPreference != null) {
            editPinPreference.setEnabled(this.mPhone != null);
        }
        SwitchPreference switchPreference = this.mPinToggle;
        if (switchPreference != null) {
            switchPreference.setEnabled(this.mPhone != null);
            Phone phone = this.mPhone;
            if (phone != null) {
                this.mPinToggle.setChecked(phone.getIccCard().getIccLockEnabled());
            }
        }
    }

    @Override // com.android.car.developeroptions.SettingsPreferenceFragment, com.android.car.developeroptions.core.InstrumentedPreferenceFragment, com.android.settingslib.core.lifecycle.ObservablePreferenceFragment, androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        getContext().registerReceiver(this.mSimStateReceiver, new IntentFilter("android.intent.action.SIM_STATE_CHANGED"));
        if (this.mDialogState != 0) {
            showPinDialog();
        } else {
            resetDialogState();
        }
    }

    @Override // com.android.settingslib.core.lifecycle.ObservablePreferenceFragment, androidx.fragment.app.Fragment
    public void onPause() {
        super.onPause();
        getContext().unregisterReceiver(this.mSimStateReceiver);
    }

    @Override // com.android.car.developeroptions.SettingsPreferenceFragment, com.android.settingslib.core.lifecycle.ObservablePreferenceFragment, androidx.preference.PreferenceFragmentCompat, androidx.fragment.app.Fragment
    public void onSaveInstanceState(Bundle bundle) {
        if (this.mPinDialog.isDialogOpen()) {
            bundle.putInt("dialogState", this.mDialogState);
            bundle.putString("dialogPin", this.mPinDialog.getEditText().getText().toString());
            bundle.putString("dialogError", this.mError);
            bundle.putBoolean("enableState", this.mToState);
            int i = this.mDialogState;
            if (i == 3) {
                bundle.putString("oldPinCode", this.mOldPin);
            } else if (i == 4) {
                bundle.putString("oldPinCode", this.mOldPin);
                bundle.putString("newPinCode", this.mNewPin);
            }
        } else {
            super.onSaveInstanceState(bundle);
        }
        TabHost tabHost = this.mTabHost;
        if (tabHost != null) {
            bundle.putString("currentTab", tabHost.getCurrentTabTag());
        }
    }

    private void showPinDialog() {
        if (this.mDialogState == 0) {
            return;
        }
        setDialogValues();
        this.mPinDialog.showPinDialog();
        EditText editText = this.mPinDialog.getEditText();
        if (TextUtils.isEmpty(this.mPin) || editText == null) {
            return;
        }
        editText.setSelection(this.mPin.length());
    }

    private void setDialogValues() {
        String string;
        String string2;
        this.mPinDialog.setText(this.mPin);
        int i = this.mDialogState;
        if (i == 1) {
            string = this.mRes.getString(R.string.sim_enter_pin);
            EditPinPreference editPinPreference = this.mPinDialog;
            if (this.mToState) {
                string2 = this.mRes.getString(R.string.sim_enable_sim_lock);
            } else {
                string2 = this.mRes.getString(R.string.sim_disable_sim_lock);
            }
            editPinPreference.setDialogTitle(string2);
        } else if (i == 2) {
            string = this.mRes.getString(R.string.sim_enter_old);
            this.mPinDialog.setDialogTitle(this.mRes.getString(R.string.sim_change_pin));
        } else if (i == 3) {
            string = this.mRes.getString(R.string.sim_enter_new);
            this.mPinDialog.setDialogTitle(this.mRes.getString(R.string.sim_change_pin));
        } else if (i != 4) {
            string = "";
        } else {
            string = this.mRes.getString(R.string.sim_reenter_new);
            this.mPinDialog.setDialogTitle(this.mRes.getString(R.string.sim_change_pin));
        }
        if (this.mError != null) {
            string = this.mError + "\n" + string;
            this.mError = null;
        }
        this.mPinDialog.setDialogMessage(string);
    }

    @Override // com.android.car.developeroptions.EditPinPreference.OnPinEnteredListener
    public void onPinEntered(EditPinPreference editPinPreference, boolean z) {
        if (!z) {
            resetDialogState();
            return;
        }
        String text = editPinPreference.getText();
        this.mPin = text;
        if (!reasonablePin(text)) {
            this.mError = this.mRes.getString(R.string.sim_bad_pin);
            showPinDialog();
            return;
        }
        int i = this.mDialogState;
        if (i == 1) {
            tryChangeIccLockState();
            return;
        }
        if (i == 2) {
            this.mOldPin = this.mPin;
            this.mDialogState = 3;
            this.mError = null;
            this.mPin = null;
            showPinDialog();
            return;
        }
        if (i == 3) {
            this.mNewPin = this.mPin;
            this.mDialogState = 4;
            this.mPin = null;
            showPinDialog();
            return;
        }
        if (i != 4) {
            return;
        }
        if (!this.mPin.equals(this.mNewPin)) {
            this.mError = this.mRes.getString(R.string.sim_pins_dont_match);
            this.mDialogState = 3;
            this.mPin = null;
            showPinDialog();
            return;
        }
        this.mError = null;
        tryChangePin();
    }

    @Override // androidx.preference.PreferenceFragmentCompat, androidx.preference.PreferenceManager.OnPreferenceTreeClickListener
    public boolean onPreferenceTreeClick(Preference preference) {
        SwitchPreference switchPreference = this.mPinToggle;
        if (preference == switchPreference) {
            boolean zIsChecked = switchPreference.isChecked();
            this.mToState = zIsChecked;
            this.mPinToggle.setChecked(!zIsChecked);
            this.mDialogState = 1;
            showPinDialog();
        } else if (preference == this.mPinDialog) {
            this.mDialogState = 2;
            return false;
        }
        return true;
    }

    private void tryChangeIccLockState() {
        this.mPhone.getIccCard().setIccLockEnabled(this.mToState, this.mPin, Message.obtain(this.mHandler, 100));
        this.mPinToggle.setEnabled(false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void iccLockChanged(boolean z, int i, Throwable th) {
        if (z) {
            this.mPinToggle.setChecked(this.mToState);
        } else if (th instanceof CommandException) {
            if (((CommandException) th).getCommandError() == CommandException.Error.PASSWORD_INCORRECT) {
                createCustomTextToast(getPinPasswordErrorMessage(i));
            } else if (this.mToState) {
                Toast.makeText(getContext(), this.mRes.getString(R.string.sim_pin_enable_failed), 1).show();
            } else {
                Toast.makeText(getContext(), this.mRes.getString(R.string.sim_pin_disable_failed), 1).show();
            }
        }
        this.mPinToggle.setEnabled(true);
        resetDialogState();
    }

    private void createCustomTextToast(CharSequence charSequence) {
        final View viewInflate = ((LayoutInflater) getSystemService("layout_inflater")).inflate(android.R.layout.resolver_list_with_default, (ViewGroup) null);
        ((TextView) viewInflate.findViewById(android.R.id.message)).setText(charSequence);
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        int absoluteGravity = Gravity.getAbsoluteGravity(getContext().getResources().getInteger(android.R.integer.config_longPressOnPowerDurationMs), viewInflate.getContext().getResources().getConfiguration().getLayoutDirection());
        layoutParams.gravity = absoluteGravity;
        if ((absoluteGravity & 7) == 7) {
            layoutParams.horizontalWeight = 1.0f;
        }
        if ((absoluteGravity & 112) == 112) {
            layoutParams.verticalWeight = 1.0f;
        }
        layoutParams.y = getContext().getResources().getDimensionPixelSize(android.R.dimen.notification_2025_icon_circle_size);
        layoutParams.height = -2;
        layoutParams.width = -2;
        layoutParams.format = -3;
        layoutParams.windowAnimations = android.R.style.Animation.Toast;
        layoutParams.type = 2014;
        layoutParams.setFitInsetsTypes(layoutParams.getFitInsetsTypes() & (~WindowInsets.Type.statusBars()));
        layoutParams.setTitle(charSequence);
        layoutParams.flags = 152;
        final WindowManager windowManager = (WindowManager) getSystemService("window");
        windowManager.addView(viewInflate, layoutParams);
        this.mHandler.postDelayed(new Runnable(this) { // from class: com.android.car.developeroptions.IccLockSettings.3
            @Override // java.lang.Runnable
            public void run() {
                windowManager.removeViewImmediate(viewInflate);
            }
        }, 7000L);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void iccPinChanged(boolean z, int i) {
        if (!z) {
            createCustomTextToast(getPinPasswordErrorMessage(i));
        } else {
            Toast.makeText(getContext(), this.mRes.getString(R.string.sim_change_succeeded), 0).show();
        }
        resetDialogState();
    }

    private void tryChangePin() {
        this.mPhone.getIccCard().changeIccLockPassword(this.mOldPin, this.mNewPin, Message.obtain(this.mHandler, 101));
    }

    private String getPinPasswordErrorMessage(int i) {
        String string;
        if (i == 0) {
            string = this.mRes.getString(R.string.wrong_pin_code_pukked);
        } else if (i > 0) {
            string = this.mRes.getQuantityString(R.plurals.wrong_pin_code, i, Integer.valueOf(i));
        } else {
            string = this.mRes.getString(R.string.pin_failed);
        }
        Log.d("IccLockSettings", "getPinPasswordErrorMessage: attemptsRemaining=" + i + " displayMessage=" + string);
        return string;
    }

    private boolean reasonablePin(String str) {
        return str != null && str.length() >= 4 && str.length() <= 8;
    }

    private void resetDialogState() {
        this.mError = null;
        this.mDialogState = 2;
        this.mPin = "";
        setDialogValues();
        this.mDialogState = 0;
    }

    private TabHost.TabSpec buildTabSpec(String str, String str2) {
        return this.mTabHost.newTabSpec(str).setIndicator(str2).setContent(this.mEmptyTabContent);
    }
}
