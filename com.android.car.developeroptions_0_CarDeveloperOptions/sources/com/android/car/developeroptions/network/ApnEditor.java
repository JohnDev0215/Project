package com.android.car.developeroptions.network;

import android.app.Dialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.provider.Telephony;
import android.telephony.CarrierConfigManager;
import android.telephony.ServiceState;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import androidx.appcompat.app.AlertDialog;
import androidx.preference.EditTextPreference;
import androidx.preference.ListPreference;
import androidx.preference.MultiSelectListPreference;
import androidx.preference.Preference;
import androidx.preference.SwitchPreference;
import com.android.car.developeroptions.R;
import com.android.car.developeroptions.SettingsPreferenceFragment;
import com.android.car.developeroptions.core.instrumentation.InstrumentedDialogFragment;
import com.android.internal.telephony.PhoneConstants;
import com.android.internal.util.ArrayUtils;
import com.android.settingslib.utils.ThreadUtils;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/* JADX INFO: loaded from: classes.dex */
public class ApnEditor extends SettingsPreferenceFragment implements Preference.OnPreferenceChangeListener, View.OnKeyListener {
    static final int APN_INDEX = 2;
    static final int CARRIER_ENABLED_INDEX = 17;
    static final int MCC_INDEX = 9;
    static final int MNC_INDEX = 10;
    static final int NAME_INDEX = 1;
    static String sNotSet;
    EditTextPreference mApn;
    ApnData mApnData;
    EditTextPreference mApnType;
    ListPreference mAuthType;
    private int mBearerInitialVal = 0;
    MultiSelectListPreference mBearerMulti;
    SwitchPreference mCarrierEnabled;
    private Uri mCarrierUri;
    private String mCurMcc;
    private String mCurMnc;
    EditTextPreference mMcc;
    EditTextPreference mMmsPort;
    EditTextPreference mMmsProxy;
    EditTextPreference mMmsc;
    EditTextPreference mMnc;
    EditTextPreference mMvnoMatchData;
    private String mMvnoMatchDataStr;
    ListPreference mMvnoType;
    private String mMvnoTypeStr;
    EditTextPreference mName;
    private boolean mNewApn;
    EditTextPreference mPassword;
    EditTextPreference mPort;
    ListPreference mProtocol;
    EditTextPreference mProxy;
    private boolean mReadOnlyApn;
    private String[] mReadOnlyApnFields;
    private String[] mReadOnlyApnTypes;
    ListPreference mRoamingProtocol;
    EditTextPreference mServer;
    private int mSubId;
    private TelephonyManager mTelephonyManager;
    EditTextPreference mUser;
    private static final String TAG = ApnEditor.class.getSimpleName();
    private static final String[] sProjection = {"_id", "name", "apn", "proxy", "port", "user", "server", "password", "mmsc", "mcc", "mnc", "numeric", "mmsproxy", "mmsport", "authtype", "type", "protocol", "carrier_enabled", "bearer", "bearer_bitmask", "roaming_protocol", "mvno_type", "mvno_match_data", "edited", "user_editable"};

    @Override // com.android.settingslib.core.instrumentation.Instrumentable
    public int getMetricsCategory() {
        return 13;
    }

    @Override // com.android.car.developeroptions.SettingsPreferenceFragment, com.android.settingslib.core.lifecycle.ObservablePreferenceFragment, androidx.preference.PreferenceFragmentCompat, androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        PersistableBundle configForSubId;
        String str = TAG;
        super.onCreate(bundle);
        addPreferencesFromResource(R.xml.apn_editor);
        sNotSet = getResources().getString(R.string.apn_not_set);
        this.mName = (EditTextPreference) findPreference("apn_name");
        this.mApn = (EditTextPreference) findPreference("apn_apn");
        this.mProxy = (EditTextPreference) findPreference("apn_http_proxy");
        this.mPort = (EditTextPreference) findPreference("apn_http_port");
        this.mUser = (EditTextPreference) findPreference("apn_user");
        this.mServer = (EditTextPreference) findPreference("apn_server");
        this.mPassword = (EditTextPreference) findPreference("apn_password");
        this.mMmsProxy = (EditTextPreference) findPreference("apn_mms_proxy");
        this.mMmsPort = (EditTextPreference) findPreference("apn_mms_port");
        this.mMmsc = (EditTextPreference) findPreference("apn_mmsc");
        this.mMcc = (EditTextPreference) findPreference("apn_mcc");
        this.mMnc = (EditTextPreference) findPreference("apn_mnc");
        this.mApnType = (EditTextPreference) findPreference("apn_type");
        this.mAuthType = (ListPreference) findPreference("auth_type");
        this.mProtocol = (ListPreference) findPreference("apn_protocol");
        this.mRoamingProtocol = (ListPreference) findPreference("apn_roaming_protocol");
        this.mCarrierEnabled = (SwitchPreference) findPreference("carrier_enabled");
        this.mBearerMulti = (MultiSelectListPreference) findPreference("bearer_multi");
        this.mMvnoType = (ListPreference) findPreference("mvno_type");
        this.mMvnoMatchData = (EditTextPreference) findPreference("mvno_match_data");
        Intent intent = getIntent();
        String action = intent.getAction();
        if (TextUtils.isEmpty(action)) {
            finish();
            return;
        }
        this.mSubId = intent.getIntExtra("sub_id", -1);
        this.mReadOnlyApn = false;
        Uri data = null;
        this.mReadOnlyApnTypes = null;
        this.mReadOnlyApnFields = null;
        CarrierConfigManager carrierConfigManager = (CarrierConfigManager) getSystemService("carrier_config");
        if (carrierConfigManager != null && (configForSubId = carrierConfigManager.getConfigForSubId(this.mSubId)) != null) {
            String[] stringArray = configForSubId.getStringArray("read_only_apn_types_string_array");
            this.mReadOnlyApnTypes = stringArray;
            if (!ArrayUtils.isEmpty(stringArray)) {
                for (String str2 : this.mReadOnlyApnTypes) {
                    Log.d(str, "onCreate: read only APN type: " + str2);
                }
            }
            this.mReadOnlyApnFields = configForSubId.getStringArray("read_only_apn_fields_string_array");
        }
        if (action.equals("android.intent.action.EDIT")) {
            data = intent.getData();
            if (!data.isPathPrefixMatch(Telephony.Carriers.CONTENT_URI)) {
                Log.e(str, "Edit request not for carrier table. Uri: " + data);
                finish();
                return;
            }
        } else if (action.equals("android.intent.action.INSERT")) {
            Uri data2 = intent.getData();
            this.mCarrierUri = data2;
            if (!data2.isPathPrefixMatch(Telephony.Carriers.CONTENT_URI)) {
                Log.e(str, "Insert request not for carrier table. Uri: " + this.mCarrierUri);
                finish();
                return;
            }
            this.mNewApn = true;
            this.mMvnoTypeStr = intent.getStringExtra("mvno_type");
            this.mMvnoMatchDataStr = intent.getStringExtra("mvno_match_data");
        } else {
            finish();
            return;
        }
        if (data != null) {
            this.mApnData = getApnDataFromUri(data);
        } else {
            this.mApnData = new ApnData(sProjection.length);
        }
        this.mTelephonyManager = (TelephonyManager) getSystemService("phone");
        boolean z = this.mApnData.getInteger(23, 1).intValue() == 1;
        Log.d(str, "onCreate: EDITED " + z);
        if (!z && (this.mApnData.getInteger(24, 1).intValue() == 0 || apnTypesMatch(this.mReadOnlyApnTypes, this.mApnData.getString(15)))) {
            Log.d(str, "onCreate: apnTypesMatch; read-only APN");
            this.mReadOnlyApn = true;
            disableAllFields();
        } else if (!ArrayUtils.isEmpty(this.mReadOnlyApnFields)) {
            disableFields(this.mReadOnlyApnFields);
        }
        for (int i = 0; i < getPreferenceScreen().getPreferenceCount(); i++) {
            getPreferenceScreen().getPreference(i).setOnPreferenceChangeListener(this);
        }
        fillUI(bundle == null);
    }

    static String formatInteger(String str) {
        try {
            return String.format(getCorrectDigitsFormat(str), Integer.valueOf(Integer.parseInt(str)));
        } catch (NumberFormatException unused) {
            return str;
        }
    }

    static String getCorrectDigitsFormat(String str) {
        return str.length() == 2 ? "%02d" : "%03d";
    }

    static boolean hasAllApns(String[] strArr) {
        String str = TAG;
        if (ArrayUtils.isEmpty(strArr)) {
            return false;
        }
        List listAsList = Arrays.asList(strArr);
        if (listAsList.contains("*")) {
            Log.d(str, "hasAllApns: true because apnList.contains(PhoneConstants.APN_TYPE_ALL)");
            return true;
        }
        for (String str2 : PhoneConstants.APN_TYPES) {
            if (!listAsList.contains(str2)) {
                return false;
            }
        }
        Log.d(str, "hasAllApns: true");
        return true;
    }

    private boolean apnTypesMatch(String[] strArr, String str) {
        String str2 = TAG;
        if (ArrayUtils.isEmpty(strArr)) {
            return false;
        }
        if (hasAllApns(strArr) || TextUtils.isEmpty(str)) {
            return true;
        }
        List listAsList = Arrays.asList(strArr);
        for (String str3 : str.split(",")) {
            if (listAsList.contains(str3.trim())) {
                Log.d(str2, "apnTypesMatch: true because match found for " + str3.trim());
                return true;
            }
        }
        Log.d(str2, "apnTypesMatch: false");
        return false;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Removed duplicated region for block: B:68:0x00f4  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private androidx.preference.Preference getPreferenceFromFieldName(java.lang.String r2) {
        /*
            Method dump skipped, instruction units count: 442
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.car.developeroptions.network.ApnEditor.getPreferenceFromFieldName(java.lang.String):androidx.preference.Preference");
    }

    private void disableFields(String[] strArr) {
        for (String str : strArr) {
            Preference preferenceFromFieldName = getPreferenceFromFieldName(str);
            if (preferenceFromFieldName != null) {
                preferenceFromFieldName.setEnabled(false);
            }
        }
    }

    private void disableAllFields() {
        this.mName.setEnabled(false);
        this.mApn.setEnabled(false);
        this.mProxy.setEnabled(false);
        this.mPort.setEnabled(false);
        this.mUser.setEnabled(false);
        this.mServer.setEnabled(false);
        this.mPassword.setEnabled(false);
        this.mMmsProxy.setEnabled(false);
        this.mMmsPort.setEnabled(false);
        this.mMmsc.setEnabled(false);
        this.mMcc.setEnabled(false);
        this.mMnc.setEnabled(false);
        this.mApnType.setEnabled(false);
        this.mAuthType.setEnabled(false);
        this.mProtocol.setEnabled(false);
        this.mRoamingProtocol.setEnabled(false);
        this.mCarrierEnabled.setEnabled(false);
        this.mBearerMulti.setEnabled(false);
        this.mMvnoType.setEnabled(false);
        this.mMvnoMatchData.setEnabled(false);
    }

    void fillUI(boolean z) {
        String str;
        String simOperator;
        if (z) {
            this.mName.setText(this.mApnData.getString(1));
            this.mApn.setText(this.mApnData.getString(2));
            this.mProxy.setText(this.mApnData.getString(3));
            this.mPort.setText(this.mApnData.getString(4));
            this.mUser.setText(this.mApnData.getString(5));
            this.mServer.setText(this.mApnData.getString(6));
            this.mPassword.setText(this.mApnData.getString(7));
            this.mMmsProxy.setText(this.mApnData.getString(12));
            this.mMmsPort.setText(this.mApnData.getString(13));
            this.mMmsc.setText(this.mApnData.getString(8));
            this.mMcc.setText(this.mApnData.getString(MCC_INDEX));
            this.mMnc.setText(this.mApnData.getString(MNC_INDEX));
            this.mApnType.setText(this.mApnData.getString(15));
            if (this.mNewApn && (simOperator = this.mTelephonyManager.getSimOperator(this.mSubId)) != null && simOperator.length() > 4) {
                String strSubstring = simOperator.substring(0, 3);
                String strSubstring2 = simOperator.substring(3);
                this.mMcc.setText(strSubstring);
                this.mMnc.setText(strSubstring2);
                this.mCurMnc = strSubstring2;
                this.mCurMcc = strSubstring;
            }
            int iIntValue = this.mApnData.getInteger(14, -1).intValue();
            if (iIntValue != -1) {
                this.mAuthType.setValueIndex(iIntValue);
            } else {
                this.mAuthType.setValue(null);
            }
            this.mProtocol.setValue(this.mApnData.getString(16));
            this.mRoamingProtocol.setValue(this.mApnData.getString(20));
            this.mCarrierEnabled.setChecked(this.mApnData.getInteger(CARRIER_ENABLED_INDEX, 1).intValue() == 1);
            this.mBearerInitialVal = this.mApnData.getInteger(18, 0).intValue();
            HashSet hashSet = new HashSet();
            int iIntValue2 = this.mApnData.getInteger(19, 0).intValue();
            if (iIntValue2 != 0) {
                int i = 1;
                while (iIntValue2 != 0) {
                    if ((iIntValue2 & 1) == 1) {
                        hashSet.add("" + i);
                    }
                    iIntValue2 >>= 1;
                    i++;
                }
            } else if (this.mBearerInitialVal == 0) {
                hashSet.add("0");
            }
            if (this.mBearerInitialVal != 0) {
                if (!hashSet.contains("" + this.mBearerInitialVal)) {
                    hashSet.add("" + this.mBearerInitialVal);
                }
            }
            this.mBearerMulti.setValues(hashSet);
            this.mMvnoType.setValue(this.mApnData.getString(21));
            this.mMvnoMatchData.setEnabled(false);
            this.mMvnoMatchData.setText(this.mApnData.getString(22));
            if (this.mNewApn && (str = this.mMvnoTypeStr) != null && this.mMvnoMatchDataStr != null) {
                this.mMvnoType.setValue(str);
                this.mMvnoMatchData.setText(this.mMvnoMatchDataStr);
            }
        }
        EditTextPreference editTextPreference = this.mName;
        editTextPreference.setSummary(checkNull(editTextPreference.getText()));
        EditTextPreference editTextPreference2 = this.mApn;
        editTextPreference2.setSummary(checkNull(editTextPreference2.getText()));
        EditTextPreference editTextPreference3 = this.mProxy;
        editTextPreference3.setSummary(checkNull(editTextPreference3.getText()));
        EditTextPreference editTextPreference4 = this.mPort;
        editTextPreference4.setSummary(checkNull(editTextPreference4.getText()));
        EditTextPreference editTextPreference5 = this.mUser;
        editTextPreference5.setSummary(checkNull(editTextPreference5.getText()));
        EditTextPreference editTextPreference6 = this.mServer;
        editTextPreference6.setSummary(checkNull(editTextPreference6.getText()));
        EditTextPreference editTextPreference7 = this.mPassword;
        editTextPreference7.setSummary(starify(editTextPreference7.getText()));
        EditTextPreference editTextPreference8 = this.mMmsProxy;
        editTextPreference8.setSummary(checkNull(editTextPreference8.getText()));
        EditTextPreference editTextPreference9 = this.mMmsPort;
        editTextPreference9.setSummary(checkNull(editTextPreference9.getText()));
        EditTextPreference editTextPreference10 = this.mMmsc;
        editTextPreference10.setSummary(checkNull(editTextPreference10.getText()));
        EditTextPreference editTextPreference11 = this.mMcc;
        editTextPreference11.setSummary(formatInteger(checkNull(editTextPreference11.getText())));
        EditTextPreference editTextPreference12 = this.mMnc;
        editTextPreference12.setSummary(formatInteger(checkNull(editTextPreference12.getText())));
        EditTextPreference editTextPreference13 = this.mApnType;
        editTextPreference13.setSummary(checkNull(editTextPreference13.getText()));
        String value = this.mAuthType.getValue();
        if (value != null) {
            int i2 = Integer.parseInt(value);
            this.mAuthType.setValueIndex(i2);
            this.mAuthType.setSummary(getResources().getStringArray(R.array.apn_auth_entries)[i2]);
        } else {
            this.mAuthType.setSummary(sNotSet);
        }
        ListPreference listPreference = this.mProtocol;
        listPreference.setSummary(checkNull(protocolDescription(listPreference.getValue(), this.mProtocol)));
        ListPreference listPreference2 = this.mRoamingProtocol;
        listPreference2.setSummary(checkNull(protocolDescription(listPreference2.getValue(), this.mRoamingProtocol)));
        MultiSelectListPreference multiSelectListPreference = this.mBearerMulti;
        multiSelectListPreference.setSummary(checkNull(bearerMultiDescription(multiSelectListPreference.getValues())));
        ListPreference listPreference3 = this.mMvnoType;
        listPreference3.setSummary(checkNull(mvnoDescription(listPreference3.getValue())));
        EditTextPreference editTextPreference14 = this.mMvnoMatchData;
        editTextPreference14.setSummary(checkNull(editTextPreference14.getText()));
        if (getResources().getBoolean(R.bool.config_allow_edit_carrier_enabled)) {
            this.mCarrierEnabled.setEnabled(true);
        } else {
            this.mCarrierEnabled.setEnabled(false);
        }
    }

    private String protocolDescription(String str, ListPreference listPreference) {
        int iFindIndexOfValue = listPreference.findIndexOfValue(str);
        if (iFindIndexOfValue == -1) {
            return null;
        }
        try {
            return getResources().getStringArray(R.array.apn_protocol_entries)[iFindIndexOfValue];
        } catch (ArrayIndexOutOfBoundsException unused) {
            return null;
        }
    }

    private String bearerMultiDescription(Set<String> set) {
        String[] stringArray = getResources().getStringArray(R.array.bearer_entries);
        StringBuilder sb = new StringBuilder();
        Iterator<String> it = set.iterator();
        boolean z = true;
        while (it.hasNext()) {
            int iFindIndexOfValue = this.mBearerMulti.findIndexOfValue(it.next());
            if (z) {
                try {
                    sb.append(stringArray[iFindIndexOfValue]);
                    z = false;
                } catch (ArrayIndexOutOfBoundsException unused) {
                }
            } else {
                sb.append(", " + stringArray[iFindIndexOfValue]);
            }
        }
        String string = sb.toString();
        if (TextUtils.isEmpty(string)) {
            return null;
        }
        return string;
    }

    private String mvnoDescription(String str) {
        String[] strArr;
        int iFindIndexOfValue = this.mMvnoType.findIndexOfValue(str);
        String value = this.mMvnoType.getValue();
        if (iFindIndexOfValue == -1) {
            return null;
        }
        String[] stringArray = getResources().getStringArray(R.array.mvno_type_entries);
        boolean z = false;
        boolean z2 = this.mReadOnlyApn || ((strArr = this.mReadOnlyApnFields) != null && Arrays.asList(strArr).contains("mvno_match_data"));
        EditTextPreference editTextPreference = this.mMvnoMatchData;
        if (!z2 && iFindIndexOfValue != 0) {
            z = true;
        }
        editTextPreference.setEnabled(z);
        if (str != null && !str.equals(value)) {
            if (stringArray[iFindIndexOfValue].equals("SPN")) {
                this.mMvnoMatchData.setText(this.mTelephonyManager.getSimOperatorName());
            } else if (stringArray[iFindIndexOfValue].equals("IMSI")) {
                String simOperator = this.mTelephonyManager.getSimOperator(this.mSubId);
                this.mMvnoMatchData.setText(simOperator + "x");
            } else if (stringArray[iFindIndexOfValue].equals("GID")) {
                this.mMvnoMatchData.setText(this.mTelephonyManager.getGroupIdLevel1());
            }
        }
        try {
            return stringArray[iFindIndexOfValue];
        } catch (ArrayIndexOutOfBoundsException unused) {
            return null;
        }
    }

    @Override // androidx.preference.Preference.OnPreferenceChangeListener
    public boolean onPreferenceChange(Preference preference, Object obj) {
        String key = preference.getKey();
        if ("auth_type".equals(key)) {
            try {
                int i = Integer.parseInt((String) obj);
                this.mAuthType.setValueIndex(i);
                this.mAuthType.setSummary(getResources().getStringArray(R.array.apn_auth_entries)[i]);
                return true;
            } catch (NumberFormatException unused) {
                return false;
            }
        }
        if ("apn_protocol".equals(key)) {
            String str = (String) obj;
            String strProtocolDescription = protocolDescription(str, this.mProtocol);
            if (strProtocolDescription == null) {
                return false;
            }
            this.mProtocol.setSummary(strProtocolDescription);
            this.mProtocol.setValue(str);
            return true;
        }
        if ("apn_roaming_protocol".equals(key)) {
            String str2 = (String) obj;
            String strProtocolDescription2 = protocolDescription(str2, this.mRoamingProtocol);
            if (strProtocolDescription2 == null) {
                return false;
            }
            this.mRoamingProtocol.setSummary(strProtocolDescription2);
            this.mRoamingProtocol.setValue(str2);
            return true;
        }
        if ("bearer_multi".equals(key)) {
            Set<String> set = (Set) obj;
            String strBearerMultiDescription = bearerMultiDescription(set);
            if (strBearerMultiDescription == null) {
                return false;
            }
            this.mBearerMulti.setValues(set);
            this.mBearerMulti.setSummary(strBearerMultiDescription);
            return true;
        }
        if ("mvno_type".equals(key)) {
            String str3 = (String) obj;
            String strMvnoDescription = mvnoDescription(str3);
            if (strMvnoDescription == null) {
                return false;
            }
            this.mMvnoType.setValue(str3);
            this.mMvnoType.setSummary(strMvnoDescription);
            return true;
        }
        if ("apn_password".equals(key)) {
            this.mPassword.setSummary(starify(obj != null ? String.valueOf(obj) : ""));
            return true;
        }
        if ("carrier_enabled".equals(key)) {
            return true;
        }
        preference.setSummary(checkNull(obj != null ? String.valueOf(obj) : null));
        return true;
    }

    @Override // com.android.settingslib.core.lifecycle.ObservablePreferenceFragment, androidx.fragment.app.Fragment
    public void onCreateOptionsMenu(Menu menu, MenuInflater menuInflater) {
        super.onCreateOptionsMenu(menu, menuInflater);
        if (!this.mNewApn && !this.mReadOnlyApn) {
            menu.add(0, 1, 0, R.string.menu_delete).setIcon(R.drawable.ic_delete);
        }
        menu.add(0, 2, 0, R.string.menu_save).setIcon(android.R.drawable.ic_menu_save);
        menu.add(0, 3, 0, R.string.menu_cancel).setIcon(android.R.drawable.ic_menu_close_clear_cancel);
    }

    @Override // com.android.settingslib.core.lifecycle.ObservablePreferenceFragment, androidx.fragment.app.Fragment
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        int itemId = menuItem.getItemId();
        if (itemId == 1) {
            deleteApn();
            finish();
            return true;
        }
        if (itemId == 2) {
            if (validateAndSaveApnData()) {
                finish();
            }
            return true;
        }
        if (itemId == 3) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(menuItem);
    }

    @Override // androidx.preference.PreferenceFragmentCompat, androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        view.setOnKeyListener(this);
        view.setFocusableInTouchMode(true);
        view.requestFocus();
    }

    @Override // android.view.View.OnKeyListener
    public boolean onKey(View view, int i, KeyEvent keyEvent) {
        if (keyEvent.getAction() != 0 || i != 4) {
            return false;
        }
        if (!validateAndSaveApnData()) {
            return true;
        }
        finish();
        return true;
    }

    boolean setStringValueAndCheckIfDiff(ContentValues contentValues, String str, String str2, boolean z, int i) {
        String string = this.mApnData.getString(i);
        boolean z2 = z || (!(TextUtils.isEmpty(str2) && TextUtils.isEmpty(string)) && (str2 == null || !str2.equals(string)));
        if (z2 && str2 != null) {
            contentValues.put(str, str2);
        }
        return z2;
    }

    boolean setIntValueAndCheckIfDiff(ContentValues contentValues, String str, int i, boolean z, int i2) {
        boolean z2 = z || i != this.mApnData.getInteger(i2).intValue();
        if (z2) {
            contentValues.put(str, Integer.valueOf(i));
        }
        return z2;
    }

    boolean validateAndSaveApnData() {
        int i;
        int i2;
        if (this.mReadOnlyApn) {
            return true;
        }
        String strCheckNotSet = checkNotSet(this.mName.getText());
        String strCheckNotSet2 = checkNotSet(this.mApn.getText());
        String strCheckNotSet3 = checkNotSet(this.mMcc.getText());
        String strCheckNotSet4 = checkNotSet(this.mMnc.getText());
        if (validateApnData() != null) {
            showError();
            return false;
        }
        ContentValues contentValues = new ContentValues();
        boolean stringValueAndCheckIfDiff = setStringValueAndCheckIfDiff(contentValues, "mmsc", checkNotSet(this.mMmsc.getText()), setStringValueAndCheckIfDiff(contentValues, "password", checkNotSet(this.mPassword.getText()), setStringValueAndCheckIfDiff(contentValues, "server", checkNotSet(this.mServer.getText()), setStringValueAndCheckIfDiff(contentValues, "user", checkNotSet(this.mUser.getText()), setStringValueAndCheckIfDiff(contentValues, "mmsport", checkNotSet(this.mMmsPort.getText()), setStringValueAndCheckIfDiff(contentValues, "mmsproxy", checkNotSet(this.mMmsProxy.getText()), setStringValueAndCheckIfDiff(contentValues, "port", checkNotSet(this.mPort.getText()), setStringValueAndCheckIfDiff(contentValues, "proxy", checkNotSet(this.mProxy.getText()), setStringValueAndCheckIfDiff(contentValues, "apn", strCheckNotSet2, setStringValueAndCheckIfDiff(contentValues, "name", strCheckNotSet, this.mNewApn, 1), 2), 3), 4), 12), 13), 5), 6), 7), 8);
        String value = this.mAuthType.getValue();
        if (value != null) {
            stringValueAndCheckIfDiff = setIntValueAndCheckIfDiff(contentValues, "authtype", Integer.parseInt(value), stringValueAndCheckIfDiff, 14);
        }
        boolean stringValueAndCheckIfDiff2 = setStringValueAndCheckIfDiff(contentValues, "mnc", strCheckNotSet4, setStringValueAndCheckIfDiff(contentValues, "mcc", strCheckNotSet3, setStringValueAndCheckIfDiff(contentValues, "type", checkNotSet(getUserEnteredApnType()), setStringValueAndCheckIfDiff(contentValues, "roaming_protocol", checkNotSet(this.mRoamingProtocol.getValue()), setStringValueAndCheckIfDiff(contentValues, "protocol", checkNotSet(this.mProtocol.getValue()), stringValueAndCheckIfDiff, 16), 20), 15), MCC_INDEX), MNC_INDEX);
        contentValues.put("numeric", strCheckNotSet3 + strCheckNotSet4);
        String str = this.mCurMnc;
        if (str != null && this.mCurMcc != null && str.equals(strCheckNotSet4) && this.mCurMcc.equals(strCheckNotSet3)) {
            contentValues.put("current", (Integer) 1);
        }
        Iterator<String> it = this.mBearerMulti.getValues().iterator();
        int bitmaskForTech = 0;
        while (true) {
            if (!it.hasNext()) {
                i = bitmaskForTech;
                break;
            }
            String next = it.next();
            if (Integer.parseInt(next) == 0) {
                i = 0;
                break;
            }
            bitmaskForTech |= ServiceState.getBitmaskForTech(Integer.parseInt(next));
        }
        boolean intValueAndCheckIfDiff = setIntValueAndCheckIfDiff(contentValues, "carrier_enabled", this.mCarrierEnabled.isChecked() ? 1 : 0, setStringValueAndCheckIfDiff(contentValues, "mvno_match_data", checkNotSet(this.mMvnoMatchData.getText()), setStringValueAndCheckIfDiff(contentValues, "mvno_type", checkNotSet(this.mMvnoType.getValue()), setIntValueAndCheckIfDiff(contentValues, "bearer", (i == 0 || (i2 = this.mBearerInitialVal) == 0 || !ServiceState.bitmaskHasTech(i, i2)) ? 0 : this.mBearerInitialVal, setIntValueAndCheckIfDiff(contentValues, "bearer_bitmask", i, stringValueAndCheckIfDiff2, 19), 18), 21), 22), CARRIER_ENABLED_INDEX);
        contentValues.put("edited", (Integer) 1);
        if (intValueAndCheckIfDiff) {
            updateApnDataToDatabase(this.mApnData.getUri() == null ? this.mCarrierUri : this.mApnData.getUri(), contentValues);
        }
        return true;
    }

    private void updateApnDataToDatabase(final Uri uri, final ContentValues contentValues) {
        ThreadUtils.postOnBackgroundThread(new Runnable() { // from class: com.android.car.developeroptions.network.-$$Lambda$ApnEditor$buZVdaAd8cX-ykgwA8-Lvqd7zCY
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$updateApnDataToDatabase$0$ApnEditor(uri, contentValues);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: lambda$updateApnDataToDatabase$0, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void lambda$updateApnDataToDatabase$0$ApnEditor(Uri uri, ContentValues contentValues) {
        if (uri.equals(this.mCarrierUri)) {
            if (getContentResolver().insert(this.mCarrierUri, contentValues) == null) {
                Log.e(TAG, "Can't add a new apn to database " + this.mCarrierUri);
                return;
            }
            return;
        }
        getContentResolver().update(uri, contentValues, null, null);
    }

    String validateApnData() {
        String string;
        String strCheckNotSet = checkNotSet(this.mName.getText());
        String strCheckNotSet2 = checkNotSet(this.mApn.getText());
        String strCheckNotSet3 = checkNotSet(this.mMcc.getText());
        String strCheckNotSet4 = checkNotSet(this.mMnc.getText());
        if (TextUtils.isEmpty(strCheckNotSet)) {
            string = getResources().getString(R.string.error_name_empty);
        } else if (TextUtils.isEmpty(strCheckNotSet2)) {
            string = getResources().getString(R.string.error_apn_empty);
        } else if (strCheckNotSet3 == null || strCheckNotSet3.length() != 3) {
            string = getResources().getString(R.string.error_mcc_not3);
        } else {
            string = (strCheckNotSet4 == null || (strCheckNotSet4.length() & 65534) != 2) ? getResources().getString(R.string.error_mnc_not23) : null;
        }
        if (string != null || ArrayUtils.isEmpty(this.mReadOnlyApnTypes) || !apnTypesMatch(this.mReadOnlyApnTypes, getUserEnteredApnType())) {
            return string;
        }
        StringBuilder sb = new StringBuilder();
        for (String str : this.mReadOnlyApnTypes) {
            sb.append(str);
            sb.append(", ");
            Log.d(TAG, "validateApnData: appending type: " + str);
        }
        if (sb.length() >= 2) {
            sb.delete(sb.length() - 2, sb.length());
        }
        return String.format(getResources().getString(R.string.error_adding_apn_type), sb);
    }

    void showError() {
        ErrorDialog.showError(this);
    }

    private void deleteApn() {
        if (this.mApnData.getUri() != null) {
            getContentResolver().delete(this.mApnData.getUri(), null, null);
            this.mApnData = new ApnData(sProjection.length);
        }
    }

    private String starify(String str) {
        if (str == null || str.length() == 0) {
            return sNotSet;
        }
        int length = str.length();
        char[] cArr = new char[length];
        for (int i = 0; i < length; i++) {
            cArr[i] = '*';
        }
        return new String(cArr);
    }

    private String checkNull(String str) {
        return TextUtils.isEmpty(str) ? sNotSet : str;
    }

    private String checkNotSet(String str) {
        if (sNotSet.equals(str)) {
            return null;
        }
        return str;
    }

    private String getUserEnteredApnType() {
        String text = this.mApnType.getText();
        if (text != null) {
            text = text.trim();
        }
        if ((!TextUtils.isEmpty(text) && !"*".equals(text)) || ArrayUtils.isEmpty(this.mReadOnlyApnTypes)) {
            return text;
        }
        StringBuilder sb = new StringBuilder();
        List listAsList = Arrays.asList(this.mReadOnlyApnTypes);
        boolean z = true;
        for (String str : PhoneConstants.APN_TYPES) {
            if (!listAsList.contains(str) && !str.equals("ia") && !str.equals("emergency") && !str.equals("mcx")) {
                if (z) {
                    z = false;
                } else {
                    sb.append(",");
                }
                sb.append(str);
            }
        }
        String string = sb.toString();
        Log.d(TAG, "getUserEnteredApnType: changed apn type to editable apn types: " + string);
        return string;
    }

    public static class ErrorDialog extends InstrumentedDialogFragment {
        @Override // com.android.settingslib.core.instrumentation.Instrumentable
        public int getMetricsCategory() {
            return 530;
        }

        public static void showError(ApnEditor apnEditor) {
            ErrorDialog errorDialog = new ErrorDialog();
            errorDialog.setTargetFragment(apnEditor, 0);
            errorDialog.show(apnEditor.getFragmentManager(), "error");
        }

        @Override // androidx.fragment.app.DialogFragment
        public Dialog onCreateDialog(Bundle bundle) {
            String strValidateApnData = ((ApnEditor) getTargetFragment()).validateApnData();
            AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
            builder.setTitle(R.string.error_title);
            builder.setPositiveButton(android.R.string.ok, (DialogInterface.OnClickListener) null);
            builder.setMessage(strValidateApnData);
            return builder.create();
        }
    }

    ApnData getApnDataFromUri(Uri uri) {
        ApnData apnData;
        Cursor cursorQuery = getContentResolver().query(uri, sProjection, null, null, null);
        if (cursorQuery != null) {
            try {
                cursorQuery.moveToFirst();
                apnData = new ApnData(uri, cursorQuery);
            } catch (Throwable th) {
                if (cursorQuery != null) {
                    try {
                        cursorQuery.close();
                    } catch (Throwable th2) {
                        th.addSuppressed(th2);
                    }
                }
                throw th;
            }
        } else {
            apnData = null;
        }
        if (cursorQuery != null) {
            cursorQuery.close();
        }
        if (apnData == null) {
            Log.d(TAG, "Can't get apnData from Uri " + uri);
        }
        return apnData;
    }

    static class ApnData {
        Object[] mData;
        Uri mUri;

        ApnData(int i) {
            this.mData = new Object[i];
        }

        ApnData(Uri uri, Cursor cursor) {
            this.mUri = uri;
            this.mData = new Object[cursor.getColumnCount()];
            for (int i = 0; i < this.mData.length; i++) {
                int type = cursor.getType(i);
                if (type == 1) {
                    this.mData[i] = Integer.valueOf(cursor.getInt(i));
                } else if (type == 2) {
                    this.mData[i] = Float.valueOf(cursor.getFloat(i));
                } else if (type == 3) {
                    this.mData[i] = cursor.getString(i);
                } else if (type == 4) {
                    this.mData[i] = cursor.getBlob(i);
                } else {
                    this.mData[i] = null;
                }
            }
        }

        Uri getUri() {
            return this.mUri;
        }

        Integer getInteger(int i) {
            return (Integer) this.mData[i];
        }

        Integer getInteger(int i, Integer num) {
            Integer integer = getInteger(i);
            return integer == null ? num : integer;
        }

        String getString(int i) {
            return (String) this.mData[i];
        }
    }
}
