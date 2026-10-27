package com.android.car.developeroptions.network;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.os.PersistableBundle;
import android.os.UserHandle;
import android.os.UserManager;
import android.provider.Telephony;
import android.telephony.CarrierConfigManager;
import android.telephony.SubscriptionInfo;
import android.telephony.SubscriptionManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.widget.Toast;
import androidx.fragment.app.FragmentActivity;
import androidx.preference.Preference;
import androidx.preference.PreferenceGroup;
import com.android.car.developeroptions.R;
import com.android.car.developeroptions.RestrictedSettingsFragment;
import com.android.internal.telephony.PhoneConstants;
import com.android.internal.telephony.uicc.UiccController;
import com.android.settingslib.RestrictedLockUtils;
import java.util.ArrayList;
import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
public class ApnSettings extends RestrictedSettingsFragment implements Preference.OnPreferenceChangeListener {
    private static final String[] CARRIERS_PROJECTION = {"_id", "name", "apn", "type", "mvno_type", "mvno_match_data", "edited"};
    private static final Uri DEFAULTAPN_URI = Uri.parse("content://telephony/carriers/restore");
    private static final Uri PREFERAPN_URI = Uri.parse("content://telephony/carriers/preferapn");
    private static boolean mRestoreDefaultApnMode;
    private boolean mAllowAddingApns;
    private boolean mHideImsApn;
    private boolean mHidePresetApnDetails;
    private IntentFilter mIntentFilter;
    private String mMvnoMatchData;
    private String mMvnoType;
    private final BroadcastReceiver mReceiver;
    private RestoreApnProcessHandler mRestoreApnProcessHandler;
    private RestoreApnUiHandler mRestoreApnUiHandler;
    private HandlerThread mRestoreDefaultApnThread;
    private String mSelectedKey;
    private int mSubId;
    private SubscriptionInfo mSubscriptionInfo;
    private boolean mUnavailable;
    private UserManager mUserManager;

    @Override // com.android.car.developeroptions.SettingsPreferenceFragment, com.android.car.developeroptions.DialogCreatable
    public int getDialogMetricsCategory(int i) {
        return i == 1001 ? 579 : 0;
    }

    @Override // com.android.settingslib.core.instrumentation.Instrumentable
    public int getMetricsCategory() {
        return 12;
    }

    public ApnSettings() {
        super("no_config_mobile_networks");
        this.mReceiver = new BroadcastReceiver() { // from class: com.android.car.developeroptions.network.ApnSettings.1
            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context, Intent intent) {
                if (intent.getAction().equals("android.intent.action.ANY_DATA_STATE")) {
                    if (AnonymousClass3.$SwitchMap$com$android$internal$telephony$PhoneConstants$DataState[ApnSettings.getMobileDataState(intent).ordinal()] != 1) {
                        return;
                    }
                    if (!ApnSettings.mRestoreDefaultApnMode) {
                        ApnSettings.this.fillList();
                        return;
                    } else {
                        ApnSettings.this.showDialog(1001);
                        return;
                    }
                }
                if (!intent.getAction().equals("android.telephony.action.SUBSCRIPTION_CARRIER_IDENTITY_CHANGED") || ApnSettings.mRestoreDefaultApnMode) {
                    return;
                }
                int intExtra = intent.getIntExtra("android.telephony.extra.SUBSCRIPTION_ID", -1);
                if (intExtra != ApnSettings.this.mSubId) {
                    ApnSettings.this.mSubId = intExtra;
                    ApnSettings apnSettings = ApnSettings.this;
                    apnSettings.mSubscriptionInfo = apnSettings.getSubscriptionInfo(apnSettings.mSubId);
                }
                ApnSettings.this.fillList();
            }
        };
    }

    /* JADX INFO: renamed from: com.android.car.developeroptions.network.ApnSettings$3, reason: invalid class name */
    static /* synthetic */ class AnonymousClass3 {
        static final /* synthetic */ int[] $SwitchMap$com$android$internal$telephony$PhoneConstants$DataState;

        static {
            int[] iArr = new int[PhoneConstants.DataState.values().length];
            $SwitchMap$com$android$internal$telephony$PhoneConstants$DataState = iArr;
            try {
                iArr[PhoneConstants.DataState.CONNECTED.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static PhoneConstants.DataState getMobileDataState(Intent intent) {
        String stringExtra = intent.getStringExtra("state");
        if (stringExtra != null) {
            return Enum.valueOf(PhoneConstants.DataState.class, stringExtra);
        }
        return PhoneConstants.DataState.DISCONNECTED;
    }

    @Override // com.android.car.developeroptions.RestrictedSettingsFragment, com.android.car.developeroptions.SettingsPreferenceFragment, com.android.settingslib.core.lifecycle.ObservablePreferenceFragment, androidx.preference.PreferenceFragmentCompat, androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        FragmentActivity activity = getActivity();
        this.mSubId = activity.getIntent().getIntExtra("sub_id", -1);
        IntentFilter intentFilter = new IntentFilter("android.intent.action.ANY_DATA_STATE");
        this.mIntentFilter = intentFilter;
        intentFilter.addAction("android.telephony.action.SUBSCRIPTION_CARRIER_IDENTITY_CHANGED");
        setIfOnlyAvailableForAdmins(true);
        this.mSubscriptionInfo = getSubscriptionInfo(this.mSubId);
        UiccController.getInstance();
        PersistableBundle configForSubId = ((CarrierConfigManager) getSystemService("carrier_config")).getConfigForSubId(this.mSubId);
        this.mHideImsApn = configForSubId.getBoolean("hide_ims_apn_bool");
        boolean z = configForSubId.getBoolean("allow_adding_apns_bool");
        this.mAllowAddingApns = z;
        if (z && ApnEditor.hasAllApns(configForSubId.getStringArray("read_only_apn_types_string_array"))) {
            Log.d("ApnSettings", "not allowing adding APN because all APN types are read only");
            this.mAllowAddingApns = false;
        }
        this.mHidePresetApnDetails = configForSubId.getBoolean("hide_preset_apn_details_bool");
        this.mUserManager = UserManager.get(activity);
    }

    @Override // com.android.car.developeroptions.RestrictedSettingsFragment, com.android.car.developeroptions.SettingsPreferenceFragment, androidx.fragment.app.Fragment
    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        getEmptyTextView().setText(R.string.apn_settings_not_available);
        boolean zIsUiRestricted = isUiRestricted();
        this.mUnavailable = zIsUiRestricted;
        setHasOptionsMenu(!zIsUiRestricted);
        if (this.mUnavailable) {
            addPreferencesFromResource(R.xml.placeholder_prefs);
        } else {
            addPreferencesFromResource(R.xml.apn_settings);
        }
    }

    @Override // com.android.car.developeroptions.RestrictedSettingsFragment, com.android.car.developeroptions.SettingsPreferenceFragment, com.android.car.developeroptions.core.InstrumentedPreferenceFragment, com.android.settingslib.core.lifecycle.ObservablePreferenceFragment, androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        if (this.mUnavailable) {
            return;
        }
        getActivity().registerReceiver(this.mReceiver, this.mIntentFilter);
        if (mRestoreDefaultApnMode) {
            return;
        }
        fillList();
    }

    @Override // com.android.settingslib.core.lifecycle.ObservablePreferenceFragment, androidx.fragment.app.Fragment
    public void onPause() {
        super.onPause();
        if (this.mUnavailable) {
            return;
        }
        getActivity().unregisterReceiver(this.mReceiver);
    }

    @Override // com.android.car.developeroptions.RestrictedSettingsFragment, com.android.settingslib.core.lifecycle.ObservablePreferenceFragment, androidx.fragment.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        HandlerThread handlerThread = this.mRestoreDefaultApnThread;
        if (handlerThread != null) {
            handlerThread.quit();
        }
    }

    @Override // com.android.car.developeroptions.RestrictedSettingsFragment
    public RestrictedLockUtils.EnforcedAdmin getRestrictionEnforcedAdmin() {
        UserHandle userHandleOf = UserHandle.of(this.mUserManager.getUserHandle());
        if (!this.mUserManager.hasUserRestriction("no_config_mobile_networks", userHandleOf) || this.mUserManager.hasBaseUserRestriction("no_config_mobile_networks", userHandleOf)) {
            return null;
        }
        return RestrictedLockUtils.EnforcedAdmin.MULTIPLE_ENFORCED_ADMIN;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public SubscriptionInfo getSubscriptionInfo(int i) {
        return SubscriptionManager.from(getActivity()).getActiveSubscriptionInfo(i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void fillList() {
        SubscriptionInfo subscriptionInfo = this.mSubscriptionInfo;
        int subscriptionId = subscriptionInfo != null ? subscriptionInfo.getSubscriptionId() : -1;
        Uri uriWithAppendedPath = Uri.withAppendedPath(Telephony.Carriers.SIM_APN_URI, String.valueOf(subscriptionId));
        StringBuilder sb = new StringBuilder("NOT (type='ia' AND (apn=\"\" OR apn IS NULL)) AND user_visible!=0");
        if (this.mHideImsApn) {
            sb.append(" AND NOT (type='ims')");
        }
        Cursor cursorQuery = getContentResolver().query(uriWithAppendedPath, CARRIERS_PROJECTION, sb.toString(), null, "name ASC");
        if (cursorQuery != null) {
            PreferenceGroup preferenceGroup = (PreferenceGroup) findPreference("apn_list");
            preferenceGroup.removeAll();
            ArrayList arrayList = new ArrayList();
            ArrayList arrayList2 = new ArrayList();
            this.mSelectedKey = getSelectedApnKey();
            cursorQuery.moveToFirst();
            while (!cursorQuery.isAfterLast()) {
                boolean z = true;
                String string = cursorQuery.getString(1);
                String string2 = cursorQuery.getString(2);
                String string3 = cursorQuery.getString(0);
                String string4 = cursorQuery.getString(3);
                int i = cursorQuery.getInt(6);
                this.mMvnoType = cursorQuery.getString(4);
                this.mMvnoMatchData = cursorQuery.getString(5);
                ApnPreference apnPreference = new ApnPreference(getPrefContext());
                apnPreference.setKey(string3);
                apnPreference.setTitle(string);
                apnPreference.setPersistent(false);
                apnPreference.setOnPreferenceChangeListener(this);
                apnPreference.setSubId(subscriptionId);
                if (this.mHidePresetApnDetails && i == 0) {
                    apnPreference.setHideDetails();
                } else {
                    apnPreference.setSummary(string2);
                }
                if (string4 != null && string4.equals("mms")) {
                    z = false;
                }
                apnPreference.setSelectable(z);
                if (z) {
                    String str = this.mSelectedKey;
                    if (str != null && str.equals(string3)) {
                        apnPreference.setChecked();
                    }
                    arrayList.add(apnPreference);
                } else {
                    arrayList2.add(apnPreference);
                }
                cursorQuery.moveToNext();
            }
            cursorQuery.close();
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                preferenceGroup.addPreference((Preference) it.next());
            }
            Iterator it2 = arrayList2.iterator();
            while (it2.hasNext()) {
                preferenceGroup.addPreference((Preference) it2.next());
            }
        }
    }

    @Override // com.android.settingslib.core.lifecycle.ObservablePreferenceFragment, androidx.fragment.app.Fragment
    public void onCreateOptionsMenu(Menu menu, MenuInflater menuInflater) {
        if (!this.mUnavailable) {
            if (this.mAllowAddingApns) {
                menu.add(0, 1, 0, getResources().getString(R.string.menu_new)).setIcon(R.drawable.ic_menu_add).setShowAsAction(1);
            }
            menu.add(0, 2, 0, getResources().getString(R.string.menu_restore)).setIcon(android.R.drawable.ic_menu_upload);
        }
        super.onCreateOptionsMenu(menu, menuInflater);
    }

    @Override // com.android.settingslib.core.lifecycle.ObservablePreferenceFragment, androidx.fragment.app.Fragment
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        int itemId = menuItem.getItemId();
        if (itemId == 1) {
            addNewApn();
            return true;
        }
        if (itemId == 2) {
            restoreDefaultApn();
            return true;
        }
        return super.onOptionsItemSelected(menuItem);
    }

    private void addNewApn() {
        Intent intent = new Intent("android.intent.action.INSERT", Telephony.Carriers.CONTENT_URI);
        SubscriptionInfo subscriptionInfo = this.mSubscriptionInfo;
        intent.putExtra("sub_id", subscriptionInfo != null ? subscriptionInfo.getSubscriptionId() : -1);
        if (!TextUtils.isEmpty(this.mMvnoType) && !TextUtils.isEmpty(this.mMvnoMatchData)) {
            intent.putExtra("mvno_type", this.mMvnoType);
            intent.putExtra("mvno_match_data", this.mMvnoMatchData);
        }
        startActivity(intent);
    }

    @Override // androidx.preference.Preference.OnPreferenceChangeListener
    public boolean onPreferenceChange(Preference preference, Object obj) {
        Log.d("ApnSettings", "onPreferenceChange(): Preference - " + preference + ", newValue - " + obj + ", newValue type - " + obj.getClass());
        if (!(obj instanceof String)) {
            return true;
        }
        setSelectedApnKey((String) obj);
        return true;
    }

    private void setSelectedApnKey(String str) {
        this.mSelectedKey = str;
        ContentResolver contentResolver = getContentResolver();
        ContentValues contentValues = new ContentValues();
        contentValues.put("apn_id", this.mSelectedKey);
        contentResolver.update(getUriForCurrSubId(PREFERAPN_URI), contentValues, null, null);
    }

    private String getSelectedApnKey() {
        String string;
        Cursor cursorQuery = getContentResolver().query(getUriForCurrSubId(PREFERAPN_URI), new String[]{"_id"}, null, null, "name ASC");
        if (cursorQuery.getCount() > 0) {
            cursorQuery.moveToFirst();
            string = cursorQuery.getString(0);
        } else {
            string = null;
        }
        cursorQuery.close();
        return string;
    }

    private boolean restoreDefaultApn() {
        showDialog(1001);
        mRestoreDefaultApnMode = true;
        if (this.mRestoreApnUiHandler == null) {
            this.mRestoreApnUiHandler = new RestoreApnUiHandler();
        }
        if (this.mRestoreApnProcessHandler == null || this.mRestoreDefaultApnThread == null) {
            HandlerThread handlerThread = new HandlerThread("Restore default APN Handler: Process Thread");
            this.mRestoreDefaultApnThread = handlerThread;
            handlerThread.start();
            this.mRestoreApnProcessHandler = new RestoreApnProcessHandler(this.mRestoreDefaultApnThread.getLooper(), this.mRestoreApnUiHandler);
        }
        this.mRestoreApnProcessHandler.sendEmptyMessage(1);
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Uri getUriForCurrSubId(Uri uri) {
        SubscriptionInfo subscriptionInfo = this.mSubscriptionInfo;
        int subscriptionId = subscriptionInfo != null ? subscriptionInfo.getSubscriptionId() : -1;
        if (!SubscriptionManager.isValidSubscriptionId(subscriptionId)) {
            return uri;
        }
        return Uri.withAppendedPath(uri, "subId/" + String.valueOf(subscriptionId));
    }

    private class RestoreApnUiHandler extends Handler {
        private RestoreApnUiHandler() {
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            if (message.what != 2) {
                return;
            }
            FragmentActivity activity = ApnSettings.this.getActivity();
            if (activity == null) {
                boolean unused = ApnSettings.mRestoreDefaultApnMode = false;
                return;
            }
            ApnSettings.this.fillList();
            ApnSettings.this.getPreferenceScreen().setEnabled(true);
            boolean unused2 = ApnSettings.mRestoreDefaultApnMode = false;
            ApnSettings.this.removeDialog(1001);
            Toast.makeText(activity, ApnSettings.this.getResources().getString(R.string.restore_default_apn_completed), 1).show();
        }
    }

    private class RestoreApnProcessHandler extends Handler {
        private Handler mRestoreApnUiHandler;

        public RestoreApnProcessHandler(Looper looper, Handler handler) {
            super(looper);
            this.mRestoreApnUiHandler = handler;
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            if (message.what != 1) {
                return;
            }
            ApnSettings.this.getContentResolver().delete(ApnSettings.this.getUriForCurrSubId(ApnSettings.DEFAULTAPN_URI), null, null);
            this.mRestoreApnUiHandler.sendEmptyMessage(2);
        }
    }

    @Override // com.android.car.developeroptions.SettingsPreferenceFragment, com.android.car.developeroptions.DialogCreatable
    public Dialog onCreateDialog(int i) {
        if (i != 1001) {
            return null;
        }
        ProgressDialog progressDialog = new ProgressDialog(this, getActivity()) { // from class: com.android.car.developeroptions.network.ApnSettings.2
            @Override // android.app.Dialog
            public boolean onTouchEvent(MotionEvent motionEvent) {
                return true;
            }
        };
        progressDialog.setMessage(getResources().getString(R.string.restore_default_apn));
        progressDialog.setCancelable(false);
        return progressDialog;
    }
}
