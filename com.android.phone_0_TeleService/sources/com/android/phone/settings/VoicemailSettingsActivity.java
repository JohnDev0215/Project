package com.android.phone.settings;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncResult;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.PersistableBundle;
import android.os.UserManager;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceScreen;
import android.telecom.PhoneAccountHandle;
import android.text.BidiFormatter;
import android.text.TextDirectionHeuristics;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ListAdapter;
import android.widget.Toast;
import com.android.internal.telephony.CallForwardInfo;
import com.android.internal.telephony.Phone;
import com.android.phone.EditPhoneNumberPreference;
import com.android.phone.PhoneGlobals;
import com.android.phone.PhoneUtils;
import com.android.phone.R;
import com.android.phone.SubscriptionInfoHelper;
import com.android.phone.settings.VoicemailProviderListPreference;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public class VoicemailSettingsActivity extends PreferenceActivity implements DialogInterface.OnClickListener, Preference.OnPreferenceChangeListener, EditPhoneNumberPreference.OnDialogClosedListener, EditPhoneNumberPreference.GetDefaultNumberListener {
    private static final String LOG_TAG = VoicemailSettingsActivity.class.getSimpleName();
    private boolean mForeground;
    private CallForwardInfo[] mNewFwdSettings;
    private String mNewVMNumber;
    private String mOldVmNumber;
    private Phone mPhone;
    private SubscriptionInfoHelper mSubscriptionInfoHelper;
    private Preference mVoicemailNotificationPreference;
    private VoicemailProviderListPreference mVoicemailProviders;
    private PreferenceScreen mVoicemailSettings;
    private CallForwardInfo[] mForwardingReadResults = null;
    private Map<Integer, AsyncResult> mForwardingChangeResults = null;
    private Collection<Integer> mExpectedChangeResultReasons = null;
    private AsyncResult mVoicemailChangeResult = null;
    private String mPreviousVMProviderKey = null;
    private int mCurrentDialogId = 0;
    private boolean mVMProviderSettingsForced = false;
    private boolean mChangingVMorFwdDueToProviderChange = false;
    private boolean mVMChangeCompletedSuccessfully = false;
    private boolean mFwdChangesRequireRollback = false;
    private int mVMOrFwdSetError = 0;
    private boolean mShowVoicemailPreference = false;
    private EditPhoneNumberPreference mSubMenuVoicemailSettings = null;
    private final Handler mGetOptionComplete = new Handler() { // from class: com.android.phone.settings.VoicemailSettingsActivity.1
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            AsyncResult asyncResult = (AsyncResult) message.obj;
            if (message.what != 502) {
                return;
            }
            VoicemailSettingsActivity.this.handleForwardingSettingsReadResult(asyncResult, message.arg1);
        }
    };
    private final Handler mSetOptionComplete = new Handler() { // from class: com.android.phone.settings.VoicemailSettingsActivity.2
        /* JADX WARN: Removed duplicated region for block: B:27:0x00d7  */
        /* JADX WARN: Removed duplicated region for block: B:35:? A[RETURN, SYNTHETIC] */
        @Override // android.os.Handler
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public void handleMessage(android.os.Message r7) {
            /*
                Method dump skipped, instruction units count: 236
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: com.android.phone.settings.VoicemailSettingsActivity.AnonymousClass2.handleMessage(android.os.Message):void");
        }
    };
    private final Handler mRevertOptionComplete = new Handler() { // from class: com.android.phone.settings.VoicemailSettingsActivity.3
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            AsyncResult asyncResult = (AsyncResult) message.obj;
            int i = message.what;
            if (i == 500) {
                VoicemailSettingsActivity.this.mVoicemailChangeResult = asyncResult;
            } else if (i == 501) {
                VoicemailSettingsActivity.this.mForwardingChangeResults.put(Integer.valueOf(message.arg1), asyncResult);
                Throwable th = asyncResult.exception;
            }
            if (!(VoicemailSettingsActivity.this.mVMChangeCompletedSuccessfully && VoicemailSettingsActivity.this.mVoicemailChangeResult == null) && (!VoicemailSettingsActivity.this.mFwdChangesRequireRollback || VoicemailSettingsActivity.this.isForwardingCompleted())) {
                VoicemailSettingsActivity.this.dismissDialogSafely(603);
                VoicemailSettingsActivity.this.onRevertDone();
            }
        }
    };

    @Override // android.preference.PreferenceActivity, android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        boolean z = false;
        if (!((UserManager) getApplicationContext().getSystemService(UserManager.class)).isPrimaryUser()) {
            Toast.makeText(this, R.string.voice_number_setting_primary_user_only, 0).show();
            finish();
            return;
        }
        if (bundle == null && TextUtils.equals(getIntent().getAction(), "com.android.phone.CallFeaturesSetting.ADD_VOICEMAIL")) {
            z = true;
        }
        this.mShowVoicemailPreference = z;
        PhoneAccountHandle phoneAccountHandle = (PhoneAccountHandle) getIntent().getParcelableExtra("android.telephony.extra.PHONE_ACCOUNT_HANDLE");
        if (phoneAccountHandle != null) {
            getIntent().putExtra("com.android.phone.settings.SubscriptionInfoHelper.SubscriptionId", PhoneUtils.getSubIdForPhoneAccountHandle(phoneAccountHandle));
        }
        SubscriptionInfoHelper subscriptionInfoHelper = new SubscriptionInfoHelper(this, getIntent());
        this.mSubscriptionInfoHelper = subscriptionInfoHelper;
        subscriptionInfoHelper.setActionBarTitle(getActionBar(), getResources(), R.string.voicemail_settings_with_label);
        this.mPhone = this.mSubscriptionInfoHelper.getPhone();
        addPreferencesFromResource(R.xml.voicemail_settings);
        this.mVoicemailNotificationPreference = findPreference(getString(R.string.voicemail_notifications_key));
        Intent intent = new Intent("android.settings.CHANNEL_NOTIFICATION_SETTINGS");
        intent.putExtra("android.provider.extra.CHANNEL_ID", "voiceMail");
        intent.putExtra("android.provider.extra.APP_PACKAGE", this.mPhone.getContext().getPackageName());
        this.mVoicemailNotificationPreference.setIntent(intent);
    }

    @Override // android.app.Activity
    protected void onResume() {
        super.onResume();
        this.mForeground = true;
        getPreferenceScreen();
        if (this.mSubMenuVoicemailSettings == null) {
            this.mSubMenuVoicemailSettings = (EditPhoneNumberPreference) findPreference("button_voicemail_key");
        }
        EditPhoneNumberPreference editPhoneNumberPreference = this.mSubMenuVoicemailSettings;
        if (editPhoneNumberPreference != null) {
            editPhoneNumberPreference.setParentActivity(this, 1, this);
            this.mSubMenuVoicemailSettings.setDialogOnClosedListener(this);
            this.mSubMenuVoicemailSettings.setDialogTitle(R.string.voicemail_settings_number_label);
            if (!getBooleanCarrierConfig("editable_voicemail_number_setting_bool")) {
                this.mSubMenuVoicemailSettings.setEnabled(false);
            }
        }
        VoicemailProviderListPreference voicemailProviderListPreference = (VoicemailProviderListPreference) findPreference("button_voicemail_provider_key");
        this.mVoicemailProviders = voicemailProviderListPreference;
        voicemailProviderListPreference.init(this.mPhone, getIntent());
        this.mVoicemailProviders.setOnPreferenceChangeListener(this);
        this.mPreviousVMProviderKey = this.mVoicemailProviders.getValue();
        this.mVoicemailSettings = (PreferenceScreen) findPreference("button_voicemail_setting_key");
        maybeHidePublicSettings();
        updateVMPreferenceWidgets(this.mVoicemailProviders.getValue());
        if (this.mShowVoicemailPreference) {
            if (this.mVoicemailProviders.hasMoreThanOneVoicemailProvider()) {
                simulatePreferenceClick(this.mVoicemailProviders);
            } else {
                onPreferenceChange(this.mVoicemailProviders, "");
                this.mVoicemailProviders.setValue("");
            }
            this.mShowVoicemailPreference = false;
        }
        updateVoiceNumberField();
        this.mVMProviderSettingsForced = false;
    }

    private void maybeHidePublicSettings() {
        if (getIntent().getBooleanExtra("android.telephony.extra.HIDE_PUBLIC_SETTINGS", false)) {
            getPreferenceScreen().removePreference(this.mVoicemailNotificationPreference);
        }
    }

    @Override // android.app.Activity
    public void onPause() {
        super.onPause();
        this.mForeground = false;
    }

    @Override // android.preference.PreferenceActivity, android.app.Activity
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() == 16908332) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(menuItem);
    }

    @Override // android.preference.PreferenceActivity
    public boolean onPreferenceTreeClick(PreferenceScreen preferenceScreen, Preference preference) {
        if (preference == this.mSubMenuVoicemailSettings) {
            return true;
        }
        if (preference.getKey().equals(this.mVoicemailSettings.getKey())) {
            Dialog dialog = ((PreferenceScreen) preference).getDialog();
            if (dialog != null) {
                dialog.getActionBar().setDisplayHomeAsUpEnabled(false);
            }
            EditPhoneNumberPreference editPhoneNumberPreference = (EditPhoneNumberPreference) findPreference("button_voicemail_key");
            this.mSubMenuVoicemailSettings = editPhoneNumberPreference;
            editPhoneNumberPreference.setParentActivity(this, 1, this);
            this.mSubMenuVoicemailSettings.setDialogOnClosedListener(this);
            this.mSubMenuVoicemailSettings.setDialogTitle(R.string.voicemail_settings_number_label);
            updateVoiceNumberField();
            if (preference.getIntent() != null) {
                startActivityForResult(preference.getIntent(), 2);
                return true;
            }
            this.mPreviousVMProviderKey = "";
            this.mVMProviderSettingsForced = false;
        }
        return false;
    }

    @Override // android.preference.Preference.OnPreferenceChangeListener
    public boolean onPreferenceChange(Preference preference, Object obj) {
        if (preference == this.mVoicemailProviders) {
            String str = (String) obj;
            if (this.mPreviousVMProviderKey.equals(str)) {
                return true;
            }
            updateVMPreferenceWidgets(str);
            VoicemailProviderSettings voicemailProviderSettingsLoad = VoicemailProviderSettingsUtil.load(this, str);
            if (voicemailProviderSettingsLoad == null) {
                Log.w(LOG_TAG, "Saved preferences not found - invoking config");
                this.mVMProviderSettingsForced = true;
                simulatePreferenceClick(this.mVoicemailSettings);
            } else {
                this.mChangingVMorFwdDueToProviderChange = true;
                saveVoiceMailAndForwardingNumber(str, voicemailProviderSettingsLoad);
            }
        }
        return true;
    }

    @Override // com.android.phone.EditPhoneNumberPreference.GetDefaultNumberListener
    public String onGetDefaultNumber(EditPhoneNumberPreference editPhoneNumberPreference) {
        if (editPhoneNumberPreference == this.mSubMenuVoicemailSettings) {
            updateVoiceNumberField();
            return null;
        }
        String voiceMailNumber = this.mPhone.getVoiceMailNumber();
        if (TextUtils.isEmpty(voiceMailNumber)) {
            return null;
        }
        return getString(R.string.voicemail_abbreviated) + " " + voiceMailNumber;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:6:0x000d A[PHI: r1
  0x000d: PHI (r1v4 ??) = (r1v5 ?? I:??[int, float, boolean, short, byte, char, OBJECT, ARRAY]), (r1v0 ??), (r1v6 ??), (r1v7 ??) binds: [B:5:0x000b, B:7:0x000f, B:16:0x0041, B:18:0x0047] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Type inference failed for: r1v0, types: [android.database.Cursor] */
    /* JADX WARN: Type inference failed for: r1v3, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r1v4 */
    /* JADX WARN: Type inference failed for: r1v5 */
    /* JADX WARN: Type inference failed for: r1v6 */
    /* JADX WARN: Type inference failed for: r1v7 */
    /* JADX WARN: Type inference failed for: r1v8 */
    /* JADX WARN: Type inference failed for: r1v9 */
    @Override // android.preference.PreferenceActivity, android.app.Activity
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    protected void onActivityResult(int r10, int r11, android.content.Intent r12) {
        /*
            r9 = this;
            r0 = -1
            r1 = 0
            r2 = 0
            r3 = 1
            r4 = 2
            if (r10 != r4) goto L71
            boolean r10 = r9.mVMProviderSettingsForced
            r9.mVMProviderSettingsForced = r2
            if (r11 == r0) goto Lf
        Ld:
            r2 = r3
            goto L4a
        Lf:
            if (r12 != 0) goto L12
            goto Ld
        L12:
            java.lang.String r11 = "com.android.phone.Signout"
            boolean r11 = r12.getBooleanExtra(r11, r2)
            if (r11 == 0) goto L3b
            if (r10 == 0) goto L20
            r9.switchToPreviousVoicemailProvider()
            goto L3a
        L20:
            com.android.phone.settings.VoicemailProviderListPreference r10 = r9.mVoicemailProviders
            java.lang.String r10 = r10.getKey()
            android.content.Intent r11 = new android.content.Intent
            java.lang.String r12 = "com.android.phone.CallFeaturesSetting.ADD_VOICEMAIL"
            r11.<init>(r12)
            java.lang.String r12 = "com.android.phone.ProviderToIgnore"
            r11.putExtra(r12, r10)
            r10 = 67108864(0x4000000, float:1.5046328E-36)
            r11.setFlags(r10)
            r9.startActivity(r11)
        L3a:
            return
        L3b:
            java.lang.String r11 = "com.android.phone.VoicemailNumber"
            java.lang.String r1 = r12.getStringExtra(r11)
            if (r1 == 0) goto Ld
            int r11 = r1.length()
            if (r11 != 0) goto L4a
            goto Ld
        L4a:
            if (r2 == 0) goto L52
            if (r10 == 0) goto L51
            r9.switchToPreviousVoicemailProvider()
        L51:
            return
        L52:
            r9.mChangingVMorFwdDueToProviderChange = r10
            java.lang.String r10 = "com.android.phone.ForwardingNumber"
            java.lang.String r10 = r12.getStringExtra(r10)
            r11 = 20
            java.lang.String r0 = "com.android.phone.ForwardingNumberTime"
            int r11 = r12.getIntExtra(r0, r11)
            com.android.phone.settings.VoicemailProviderListPreference r12 = r9.mVoicemailProviders
            java.lang.String r12 = r12.getKey()
            com.android.phone.settings.VoicemailProviderSettings r0 = new com.android.phone.settings.VoicemailProviderSettings
            r0.<init>(r1, r10, r11)
            r9.saveVoiceMailAndForwardingNumber(r12, r0)
            return
        L71:
            if (r10 != r3) goto Lbc
            if (r11 == r0) goto L76
            return
        L76:
            android.content.ContentResolver r3 = r9.getContentResolver()     // Catch: java.lang.Throwable -> Lb5
            android.net.Uri r4 = r12.getData()     // Catch: java.lang.Throwable -> Lb5
            java.lang.String r10 = "data1"
            java.lang.String[] r5 = new java.lang.String[]{r10}     // Catch: java.lang.Throwable -> Lb5
            r6 = 0
            r7 = 0
            r8 = 0
            android.database.Cursor r1 = r3.query(r4, r5, r6, r7, r8)     // Catch: java.lang.Throwable -> Lb5
            if (r1 == 0) goto Laf
            boolean r10 = r1.moveToFirst()     // Catch: java.lang.Throwable -> Lb5
            if (r10 != 0) goto L94
            goto Laf
        L94:
            com.android.phone.EditPhoneNumberPreference r10 = r9.mSubMenuVoicemailSettings     // Catch: java.lang.Throwable -> Lb5
            if (r10 == 0) goto La2
            com.android.phone.EditPhoneNumberPreference r9 = r9.mSubMenuVoicemailSettings     // Catch: java.lang.Throwable -> Lb5
            java.lang.String r10 = r1.getString(r2)     // Catch: java.lang.Throwable -> Lb5
            r9.onPickActivityResult(r10)     // Catch: java.lang.Throwable -> Lb5
            goto La9
        La2:
            java.lang.String r9 = com.android.phone.settings.VoicemailSettingsActivity.LOG_TAG     // Catch: java.lang.Throwable -> Lb5
            java.lang.String r10 = "VoicemailSettingsActivity destroyed while setting contacts."
            android.util.Log.w(r9, r10)     // Catch: java.lang.Throwable -> Lb5
        La9:
            if (r1 == 0) goto Lae
            r1.close()
        Lae:
            return
        Laf:
            if (r1 == 0) goto Lb4
            r1.close()
        Lb4:
            return
        Lb5:
            r9 = move-exception
            if (r1 == 0) goto Lbb
            r1.close()
        Lbb:
            throw r9
        Lbc:
            super.onActivityResult(r10, r11, r12)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.phone.settings.VoicemailSettingsActivity.onActivityResult(int, int, android.content.Intent):void");
    }

    private void simulatePreferenceClick(Preference preference) {
        ListAdapter rootAdapter = getPreferenceScreen().getRootAdapter();
        for (int i = 0; i < rootAdapter.getCount(); i++) {
            if (rootAdapter.getItem(i) == preference) {
                getPreferenceScreen().onItemClick(getListView(), null, i, rootAdapter.getItemId(i));
                return;
            }
        }
    }

    private boolean getBooleanCarrierConfig(String str) {
        PersistableBundle carrierConfigForSubId = PhoneGlobals.getInstance().getCarrierConfigForSubId(this.mPhone.getSubId());
        if (carrierConfigForSubId == null) {
            carrierConfigForSubId = PhoneGlobals.getInstance().getCarrierConfig();
        }
        return carrierConfigForSubId.getBoolean(str);
    }

    @Override // android.app.Activity
    protected void onPrepareDialog(int i, Dialog dialog) {
        super.onPrepareDialog(i, dialog);
        this.mCurrentDialogId = i;
    }

    @Override // android.app.Activity
    protected Dialog onCreateDialog(int i) {
        return VoicemailDialogUtil.getDialog(this, i);
    }

    @Override // com.android.phone.EditPhoneNumberPreference.OnDialogClosedListener
    public void onDialogClosed(EditPhoneNumberPreference editPhoneNumberPreference, int i) {
        if (i != -2 && editPhoneNumberPreference == this.mSubMenuVoicemailSettings) {
            saveVoiceMailAndForwardingNumber(this.mVoicemailProviders.getKey(), new VoicemailProviderSettings(this.mSubMenuVoicemailSettings.getPhoneNumber(), VoicemailProviderSettings.NO_FORWARDING));
        }
    }

    private void showDialogIfForeground(int i) {
        if (this.mForeground) {
            showDialog(i);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void dismissDialogSafely(int i) {
        try {
            dismissDialog(i);
        } catch (IllegalArgumentException unused) {
        }
    }

    @Override // android.content.DialogInterface.OnClickListener
    public void onClick(DialogInterface dialogInterface, int i) {
        dialogInterface.dismiss();
        if (i != -2) {
            if (i == -1) {
                if (this.mCurrentDialogId == 502) {
                    setVoicemailNumberWithCarrier();
                    return;
                } else {
                    finish();
                    return;
                }
            }
        } else if (this.mCurrentDialogId == 502) {
            switchToPreviousVoicemailProvider();
        }
        if ("com.android.phone.CallFeaturesSetting.ADD_VOICEMAIL".equals(getIntent() != null ? getIntent().getAction() : null)) {
            finish();
        }
    }

    private void saveVoiceMailAndForwardingNumber(String str, VoicemailProviderSettings voicemailProviderSettings) {
        String voicemailNumber = voicemailProviderSettings.getVoicemailNumber();
        this.mNewVMNumber = voicemailNumber;
        if (voicemailNumber == null) {
            voicemailNumber = "";
        }
        this.mNewVMNumber = voicemailNumber;
        this.mNewFwdSettings = voicemailProviderSettings.getForwardingSettings();
        if (this.mPhone.getPhoneType() == 2) {
            this.mNewFwdSettings = VoicemailProviderSettings.NO_FORWARDING;
        }
        if (this.mNewVMNumber.equals(this.mOldVmNumber) && this.mNewFwdSettings == VoicemailProviderSettings.NO_FORWARDING) {
            showDialogIfForeground(400);
            return;
        }
        VoicemailProviderSettingsUtil.save(this, str, voicemailProviderSettings);
        this.mVMChangeCompletedSuccessfully = false;
        this.mFwdChangesRequireRollback = false;
        this.mVMOrFwdSetError = 0;
        if (this.mNewFwdSettings == VoicemailProviderSettings.NO_FORWARDING || str.equals(this.mPreviousVMProviderKey)) {
            setVoicemailNumberWithCarrier();
            return;
        }
        this.mForwardingReadResults = new CallForwardInfo[VoicemailProviderSettings.FORWARDING_SETTINGS_REASONS.length];
        for (int i = 0; i < this.mForwardingReadResults.length; i++) {
            this.mPhone.getCallForwardingOption(VoicemailProviderSettings.FORWARDING_SETTINGS_REASONS[i], 1, this.mGetOptionComplete.obtainMessage(502, i, 0));
        }
        showDialogIfForeground(602);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleForwardingSettingsReadResult(AsyncResult asyncResult, int i) {
        Throwable th = asyncResult.exception;
        if (th == null) {
            th = null;
        }
        Object obj = asyncResult.userObj;
        if (obj instanceof Throwable) {
            th = (Throwable) obj;
        }
        CallForwardInfo[] callForwardInfoArr = this.mForwardingReadResults;
        if (callForwardInfoArr == null) {
            return;
        }
        if (th != null) {
            this.mForwardingReadResults = null;
            dismissDialogSafely(602);
            showDialogIfForeground(502);
            return;
        }
        callForwardInfoArr[i] = CallForwardInfoUtil.getCallForwardInfo((CallForwardInfo[]) asyncResult.result, VoicemailProviderSettings.FORWARDING_SETTINGS_REASONS[i]);
        boolean z = false;
        int i2 = 0;
        while (true) {
            CallForwardInfo[] callForwardInfoArr2 = this.mForwardingReadResults;
            if (i2 >= callForwardInfoArr2.length) {
                z = true;
                break;
            } else if (callForwardInfoArr2[i2] == null) {
                break;
            } else {
                i2++;
            }
        }
        if (z) {
            dismissDialogSafely(602);
            if (this.mPreviousVMProviderKey.equals("")) {
                VoicemailProviderSettingsUtil.save(this.mPhone.getContext(), "", new VoicemailProviderSettings(this.mOldVmNumber, this.mForwardingReadResults));
            }
            saveVoiceMailAndForwardingNumberStage2();
        }
    }

    private void resetForwardingChangeState() {
        this.mForwardingChangeResults = new HashMap();
        this.mExpectedChangeResultReasons = new HashSet();
    }

    private void saveVoiceMailAndForwardingNumberStage2() {
        this.mForwardingChangeResults = null;
        this.mVoicemailChangeResult = null;
        resetForwardingChangeState();
        int i = 0;
        while (true) {
            CallForwardInfo[] callForwardInfoArr = this.mNewFwdSettings;
            if (i < callForwardInfoArr.length) {
                CallForwardInfo callForwardInfo = callForwardInfoArr[i];
                if (CallForwardInfoUtil.isUpdateRequired(CallForwardInfoUtil.infoForReason(this.mForwardingReadResults, callForwardInfo.reason), callForwardInfo)) {
                    this.mExpectedChangeResultReasons.add(Integer.valueOf(i));
                    CallForwardInfoUtil.setCallForwardingOption(this.mPhone, callForwardInfo, this.mSetOptionComplete.obtainMessage(501, callForwardInfo.reason, 0));
                }
                i++;
            } else {
                showDialogIfForeground(601);
                return;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setVoicemailNumberWithCarrier() {
        this.mVoicemailChangeResult = null;
        Phone phone = this.mPhone;
        phone.setVoiceMailNumber(phone.getVoiceMailAlphaTag().toString(), this.mNewVMNumber, Message.obtain(this.mSetOptionComplete, 500));
    }

    private void switchToPreviousVoicemailProvider() {
        String str = LOG_TAG;
        if (this.mPreviousVMProviderKey == null) {
            return;
        }
        if (this.mVMChangeCompletedSuccessfully || this.mFwdChangesRequireRollback) {
            showDialogIfForeground(603);
            VoicemailProviderSettings voicemailProviderSettingsLoad = VoicemailProviderSettingsUtil.load(this, this.mPreviousVMProviderKey);
            if (voicemailProviderSettingsLoad == null) {
                Log.e(str, "VoicemailProviderSettings for the key \"" + this.mPreviousVMProviderKey + "\" is null but should be loaded.");
                return;
            }
            if (this.mVMChangeCompletedSuccessfully) {
                this.mNewVMNumber = voicemailProviderSettingsLoad.getVoicemailNumber();
                Log.i(str, "VM change is already completed successfully.Have to revert VM back to " + this.mNewVMNumber + " again.");
                Phone phone = this.mPhone;
                phone.setVoiceMailNumber(phone.getVoiceMailAlphaTag().toString(), this.mNewVMNumber, Message.obtain(this.mRevertOptionComplete, 500));
            }
            if (this.mFwdChangesRequireRollback) {
                Log.i(str, "Requested to rollback forwarding changes.");
                CallForwardInfo[] forwardingSettings = voicemailProviderSettingsLoad.getForwardingSettings();
                if (forwardingSettings != null) {
                    Map<Integer, AsyncResult> map = this.mForwardingChangeResults;
                    resetForwardingChangeState();
                    for (int i = 0; i < forwardingSettings.length; i++) {
                        CallForwardInfo callForwardInfo = forwardingSettings[i];
                        AsyncResult asyncResult = map.get(Integer.valueOf(callForwardInfo.reason));
                        if (asyncResult != null && asyncResult.exception == null) {
                            this.mExpectedChangeResultReasons.add(Integer.valueOf(callForwardInfo.reason));
                            CallForwardInfoUtil.setCallForwardingOption(this.mPhone, callForwardInfo, this.mRevertOptionComplete.obtainMessage(501, i, 0));
                        }
                    }
                    return;
                }
                return;
            }
            return;
        }
        onRevertDone();
    }

    private void updateVMPreferenceWidgets(String str) {
        VoicemailProviderListPreference.VoicemailProvider voicemailProvider = this.mVoicemailProviders.getVoicemailProvider(str);
        if (voicemailProvider == null) {
            this.mVoicemailProviders.setSummary(getString(R.string.sum_voicemail_choose_provider));
            this.mVoicemailSettings.setEnabled(false);
            this.mVoicemailSettings.setIntent(null);
        } else {
            this.mVoicemailProviders.setSummary(voicemailProvider.name);
            this.mVoicemailSettings.setEnabled(true);
            this.mVoicemailSettings.setIntent(voicemailProvider.intent);
        }
    }

    private void updateVoiceNumberField() {
        String voiceMailNumber = this.mPhone.getVoiceMailNumber();
        this.mOldVmNumber = voiceMailNumber;
        if (TextUtils.isEmpty(voiceMailNumber)) {
            this.mSubMenuVoicemailSettings.setPhoneNumber("");
            this.mSubMenuVoicemailSettings.setSummary(getString(R.string.voicemail_number_not_set));
        } else {
            this.mSubMenuVoicemailSettings.setPhoneNumber(this.mOldVmNumber);
            this.mSubMenuVoicemailSettings.setSummary(BidiFormatter.getInstance().unicodeWrap(this.mOldVmNumber, TextDirectionHeuristics.LTR));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleSetVmOrFwdMessage() {
        if (!isFwdChangeSuccess()) {
            handleVmOrFwdSetError(501);
        } else if (!isVmChangeSuccess()) {
            handleVmOrFwdSetError(500);
        } else {
            handleVmAndFwdSetSuccess(600);
        }
    }

    private void handleVmOrFwdSetError(int i) {
        if (this.mChangingVMorFwdDueToProviderChange) {
            this.mVMOrFwdSetError = i;
            this.mChangingVMorFwdDueToProviderChange = false;
            switchToPreviousVoicemailProvider();
        } else {
            this.mChangingVMorFwdDueToProviderChange = false;
            showDialogIfForeground(i);
            updateVoiceNumberField();
        }
    }

    private void handleVmAndFwdSetSuccess(int i) {
        this.mPreviousVMProviderKey = this.mVoicemailProviders.getKey();
        this.mChangingVMorFwdDueToProviderChange = false;
        showDialogIfForeground(i);
        updateVoiceNumberField();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onRevertDone() {
        updateVMPreferenceWidgets(this.mPreviousVMProviderKey);
        updateVoiceNumberField();
        int i = this.mVMOrFwdSetError;
        if (i != 0) {
            showDialogIfForeground(i);
            this.mVMOrFwdSetError = 0;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isForwardingCompleted() {
        if (this.mForwardingChangeResults == null) {
            return true;
        }
        Iterator<Integer> it = this.mExpectedChangeResultReasons.iterator();
        while (it.hasNext()) {
            if (this.mForwardingChangeResults.get(it.next()) == null) {
                return false;
            }
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isFwdChangeSuccess() {
        Map<Integer, AsyncResult> map = this.mForwardingChangeResults;
        if (map == null) {
            return true;
        }
        Iterator<AsyncResult> it = map.values().iterator();
        while (it.hasNext()) {
            Throwable th = it.next().exception;
            if (th != null) {
                String message = th.getMessage();
                if (message == null) {
                    message = "";
                }
                Log.w(LOG_TAG, "Failed to change forwarding setting. Reason: " + message);
                return false;
            }
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isVmChangeSuccess() {
        Throwable th = this.mVoicemailChangeResult.exception;
        if (th == null) {
            return true;
        }
        String message = th.getMessage();
        if (message == null) {
            message = "";
        }
        Log.w(LOG_TAG, "Failed to change voicemail. Reason: " + message);
        return false;
    }
}
