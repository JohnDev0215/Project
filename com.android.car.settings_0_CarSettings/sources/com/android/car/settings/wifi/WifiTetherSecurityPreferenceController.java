package com.android.car.settings.wifi;

import android.car.drivingstate.CarUxRestrictions;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.wifi.SoftApCapability;
import android.net.wifi.SoftApConfiguration;
import android.net.wifi.WifiManager;
import android.os.Handler;
import android.os.HandlerExecutor;
import android.util.Log;
import androidx.preference.ListPreference;
import com.android.car.settings.R;
import com.android.car.settings.common.FragmentController;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes5.dex */
public class WifiTetherSecurityPreferenceController extends WifiTetherBasePreferenceController<ListPreference> {
    protected static final String KEY_SECURITY_TYPE = "com.android.car.settings.wifi.KEY_SECURITY_TYPE";
    private static final String TAG = "WifiTetherSecurityPreferenceController";
    final Context mContext;
    private boolean mOweSapSupported;
    private boolean mSaeSapSupported;
    private boolean mSecurityCapaFetched;
    private String[] mSecurityEntries;
    private int mSecurityType;
    private String[] mSecurityValues;
    private final SharedPreferences mSharedPreferences;
    private WifiManager.SoftApCallback mSoftApCallback;
    private WifiManager mWifiManager;

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public void updateDisplay() {
        SoftApConfiguration config = getCarSoftApConfig();
        if (config == null) {
            this.mSecurityType = 1;
        } else if (config.getSecurityType() == 0) {
            this.mSecurityType = 0;
        } else if (this.mOweSapSupported && config.getSecurityType() == 4) {
            this.mSecurityType = 4;
        } else if (this.mOweSapSupported && config.getSecurityType() == 5) {
            this.mSecurityType = 5;
        } else if (this.mSaeSapSupported && config.getSecurityType() == 2) {
            this.mSecurityType = 2;
        } else if (this.mSaeSapSupported && config.getSecurityType() == 3) {
            this.mSecurityType = 3;
        } else {
            this.mSecurityType = 1;
        }
        ((ListPreference) getPreference()).setEntries(this.mSecurityEntries);
        ((ListPreference) getPreference()).setEntryValues(this.mSecurityValues);
        ((ListPreference) getPreference()).setValue(String.valueOf(this.mSecurityType));
    }

    public WifiTetherSecurityPreferenceController(Context context, String preferenceKey, FragmentController fragmentController, CarUxRestrictions uxRestrictions) {
        super(context, preferenceKey, fragmentController, uxRestrictions);
        this.mSoftApCallback = new WifiManager.SoftApCallback() { // from class: com.android.car.settings.wifi.WifiTetherSecurityPreferenceController.1
            public void onCapabilityChanged(SoftApCapability capability) {
                if (WifiTetherSecurityPreferenceController.this.mSecurityCapaFetched) {
                    return;
                }
                ArrayList<String> securityEntries = new ArrayList<>();
                ArrayList<String> securityValues = new ArrayList<>();
                WifiTetherSecurityPreferenceController.this.mSecurityCapaFetched = true;
                if (capability.areFeaturesSupported(4L)) {
                    WifiTetherSecurityPreferenceController.this.mSaeSapSupported = true;
                }
                if (capability.areFeaturesSupported(8L)) {
                    WifiTetherSecurityPreferenceController.this.mOweSapSupported = true;
                }
                if (WifiTetherSecurityPreferenceController.this.mSaeSapSupported) {
                    securityValues.add(String.valueOf(2));
                    securityEntries.add(WifiTetherSecurityPreferenceController.this.mContext.getString(R.string.wifi_security_sae));
                }
                securityValues.add(String.valueOf(1));
                securityEntries.add(WifiTetherSecurityPreferenceController.this.mContext.getString(R.string.wifi_security_wpa2));
                if (WifiTetherSecurityPreferenceController.this.mOweSapSupported) {
                    securityValues.add(String.valueOf(4));
                    securityEntries.add(WifiTetherSecurityPreferenceController.this.mContext.getString(R.string.wifi_security_owe));
                }
                securityValues.add(String.valueOf(0));
                securityEntries.add(WifiTetherSecurityPreferenceController.this.mContext.getString(R.string.wifi_security_none));
                WifiTetherSecurityPreferenceController.this.mSecurityEntries = (String[]) securityEntries.toArray(new String[securityEntries.size()]);
                WifiTetherSecurityPreferenceController.this.mSecurityValues = (String[]) securityValues.toArray(new String[securityValues.size()]);
                WifiTetherSecurityPreferenceController.this.updateDisplay();
                Log.i(WifiTetherSecurityPreferenceController.TAG, "Updated supported SoftAp AKMs");
            }
        };
        this.mSharedPreferences = getContext().getSharedPreferences("com.android.car.settings.wifi.WifiTetherPreferenceController", 0);
        this.mContext = context;
        WifiManager wifiManager = (WifiManager) context.getSystemService("wifi");
        this.mWifiManager = wifiManager;
        wifiManager.registerSoftApCallback(new HandlerExecutor(new Handler()), this.mSoftApCallback);
    }

    @Override // com.android.car.settings.common.PreferenceController
    protected Class<ListPreference> getPreferenceType() {
        return ListPreference.class;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.android.car.settings.wifi.WifiTetherBasePreferenceController, com.android.car.settings.common.PreferenceController
    protected void onCreateInternal() {
        super.onCreateInternal();
        this.mSecurityType = getCarSoftApConfig().getSecurityType();
        ((ListPreference) getPreference()).setEntries(this.mSecurityEntries);
        ((ListPreference) getPreference()).setEntryValues(this.mSecurityValues);
        ((ListPreference) getPreference()).setValue(String.valueOf(this.mSecurityType));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.android.car.settings.common.PreferenceController
    public boolean handlePreferenceChanged(ListPreference preference, Object newValue) {
        this.mSecurityType = Integer.parseInt(newValue.toString());
        this.mSharedPreferences.edit().putInt(KEY_SECURITY_TYPE, this.mSecurityType).commit();
        refreshUi();
        return true;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.android.car.settings.wifi.WifiTetherBasePreferenceController, com.android.car.settings.common.PreferenceController
    public void updateState(ListPreference preference) {
        super.updateState(preference);
        preference.setValue(Integer.toString(this.mSecurityType));
    }

    @Override // com.android.car.settings.wifi.WifiTetherBasePreferenceController
    protected String getSummary() {
        int stringResId = R.string.wifi_security_wpa2;
        int i = this.mSecurityType;
        if (i == 0) {
            stringResId = R.string.wifi_security_none;
        } else if (i == 2 || i == 3) {
            stringResId = R.string.wifi_security_sae;
        } else if (i == 4 || i == 5) {
            stringResId = R.string.wifi_security_owe;
        }
        return getContext().getString(stringResId);
    }

    @Override // com.android.car.settings.wifi.WifiTetherBasePreferenceController
    protected String getDefaultSummary() {
        return null;
    }
}
