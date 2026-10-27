package com.android.car.settings.wifi;

import android.car.drivingstate.CarUxRestrictions;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.wifi.WifiManager;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.preference.ListPreference;
import com.android.car.settings.R;
import com.android.car.settings.common.FragmentController;
import com.android.car.settings.common.PreferenceController;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes5.dex */
public class NetworkSecurityPreferenceController extends PreferenceController<ListPreference> {
    public static final String ACTION_SECURITY_CHANGE = "com.android.car.settings.wifi.SecurityChangeAction";
    public static final String KEY_SECURITY_TYPE = "security_type";
    protected static final String SHARED_SECURITY_TYPE = "com.android.car.settings.wifi.SHARED_SECURITY_TYPE";
    private CharSequence[] mSecurityTypeIds;
    private CharSequence[] mSecurityTypeNames;
    private int mSelectedSecurityType;
    private final SharedPreferences mSharedPreferences;
    private static final Map<Integer, Integer> SECURITY_TYPE_TO_DESC_RES = createSecurityTypeDescMap();
    private static final List<Integer> SECURITY_TYPES = Arrays.asList(0, 1, 2, 3, 5, 4, 6);

    public NetworkSecurityPreferenceController(Context context, String preferenceKey, FragmentController fragmentController, CarUxRestrictions uxRestrictions) {
        super(context, preferenceKey, fragmentController, uxRestrictions);
        this.mSharedPreferences = getContext().getSharedPreferences("com.android.car.settings.wifi.NetworkPasswordPreferenceController", 0);
    }

    @Override // com.android.car.settings.common.PreferenceController
    protected Class<ListPreference> getPreferenceType() {
        return ListPreference.class;
    }

    @Override // com.android.car.settings.common.PreferenceController
    protected void onCreateInternal() {
        List<String> securityTypeNamesList = new ArrayList<>();
        List<String> securityTypeIdsList = new ArrayList<>();
        WifiManager wifiManager = (WifiManager) getContext().getSystemService(WifiManager.class);
        securityTypeNamesList.add(getContext().getString(SECURITY_TYPE_TO_DESC_RES.get(0).intValue()));
        securityTypeIdsList.add(Integer.toString(0));
        if (wifiManager.isEnhancedOpenSupported()) {
            securityTypeNamesList.add(getContext().getString(SECURITY_TYPE_TO_DESC_RES.get(4).intValue()));
            securityTypeIdsList.add(Integer.toString(4));
        }
        securityTypeNamesList.add(getContext().getString(SECURITY_TYPE_TO_DESC_RES.get(1).intValue()));
        securityTypeIdsList.add(Integer.toString(1));
        securityTypeNamesList.add(getContext().getString(SECURITY_TYPE_TO_DESC_RES.get(2).intValue()));
        securityTypeIdsList.add(Integer.toString(2));
        if (wifiManager.isWpa3SaeSupported()) {
            securityTypeNamesList.add(getContext().getString(SECURITY_TYPE_TO_DESC_RES.get(5).intValue()));
            securityTypeIdsList.add(Integer.toString(5));
        }
        securityTypeNamesList.add(getContext().getString(SECURITY_TYPE_TO_DESC_RES.get(3).intValue()));
        securityTypeIdsList.add(Integer.toString(3));
        if (wifiManager.isWpa3SuiteBSupported()) {
            securityTypeNamesList.add(getContext().getString(SECURITY_TYPE_TO_DESC_RES.get(6).intValue()));
            securityTypeIdsList.add(Integer.toString(6));
        }
        this.mSelectedSecurityType = 0;
        CharSequence[] charSequenceArr = new CharSequence[securityTypeNamesList.size()];
        this.mSecurityTypeNames = charSequenceArr;
        this.mSecurityTypeNames = (CharSequence[]) securityTypeNamesList.toArray(charSequenceArr);
        CharSequence[] charSequenceArr2 = new CharSequence[securityTypeIdsList.size()];
        this.mSecurityTypeIds = charSequenceArr2;
        this.mSecurityTypeIds = (CharSequence[]) securityTypeIdsList.toArray(charSequenceArr2);
        getPreference().setEntries(this.mSecurityTypeNames);
        getPreference().setEntryValues(this.mSecurityTypeIds);
        getPreference().setDefaultValue(Integer.toString(0));
        this.mSharedPreferences.edit().putInt(SHARED_SECURITY_TYPE, 0).commit();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.android.car.settings.common.PreferenceController
    public void updateState(ListPreference preference) {
        preference.setSummary(SECURITY_TYPE_TO_DESC_RES.get(Integer.valueOf(this.mSelectedSecurityType)).intValue());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.android.car.settings.common.PreferenceController
    public boolean handlePreferenceChanged(ListPreference preference, Object newValue) {
        int i = Integer.parseInt(newValue.toString());
        this.mSelectedSecurityType = i;
        notifySecurityChange(i);
        refreshUi();
        return true;
    }

    private void notifySecurityChange(int securityType) {
        this.mSharedPreferences.edit().putInt(SHARED_SECURITY_TYPE, securityType).commit();
        Intent intent = new Intent(ACTION_SECURITY_CHANGE);
        intent.putExtra(KEY_SECURITY_TYPE, securityType);
        LocalBroadcastManager.getInstance(getContext()).sendBroadcastSync(intent);
    }

    private static Map<Integer, Integer> createSecurityTypeDescMap() {
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, Integer.valueOf(R.string.wifi_security_none));
        map.put(1, Integer.valueOf(R.string.wifi_security_wep));
        map.put(2, Integer.valueOf(R.string.wifi_security_psk_generic));
        map.put(3, Integer.valueOf(R.string.wifi_security_eap));
        map.put(5, Integer.valueOf(R.string.wifi_security_sae));
        map.put(4, Integer.valueOf(R.string.wifi_security_owe));
        map.put(6, Integer.valueOf(R.string.wifi_security_eap_suiteb));
        return map;
    }
}
