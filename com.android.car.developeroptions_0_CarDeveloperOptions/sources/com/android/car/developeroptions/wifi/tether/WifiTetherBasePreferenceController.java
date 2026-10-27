package com.android.car.developeroptions.wifi.tether;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.wifi.WifiManager;
import androidx.preference.Preference;
import androidx.preference.PreferenceScreen;
import com.android.car.developeroptions.core.PreferenceControllerMixin;
import com.android.settingslib.core.AbstractPreferenceController;

/* JADX INFO: loaded from: classes.dex */
public abstract class WifiTetherBasePreferenceController extends AbstractPreferenceController implements PreferenceControllerMixin, Preference.OnPreferenceChangeListener {
    protected final ConnectivityManager mCm;
    protected final OnTetherConfigUpdateListener mListener;
    protected Preference mPreference;
    protected final WifiManager mWifiManager;
    protected final String[] mWifiRegexs;

    public interface OnTetherConfigUpdateListener {
        void onTetherConfigUpdated();
    }

    public abstract void updateDisplay();

    public WifiTetherBasePreferenceController(Context context, OnTetherConfigUpdateListener onTetherConfigUpdateListener) {
        super(context);
        this.mListener = onTetherConfigUpdateListener;
        this.mWifiManager = (WifiManager) context.getSystemService("wifi");
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
        this.mCm = connectivityManager;
        this.mWifiRegexs = connectivityManager.getTetherableWifiRegexs();
    }

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public boolean isAvailable() {
        String[] strArr;
        return (this.mWifiManager == null || (strArr = this.mWifiRegexs) == null || strArr.length <= 0) ? false : true;
    }

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public void displayPreference(PreferenceScreen preferenceScreen) {
        super.displayPreference(preferenceScreen);
        this.mPreference = preferenceScreen.findPreference(getPreferenceKey());
        updateDisplay();
    }
}
