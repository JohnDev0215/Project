package com.android.car.settings.wifi.details;

import android.car.drivingstate.CarUxRestrictions;
import android.content.Context;
import android.net.LinkProperties;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiInfo;
import androidx.preference.Preference;
import com.android.car.settings.common.FragmentController;
import com.android.car.settings.common.PreferenceController;
import com.android.car.settings.wifi.WifiUtil;
import com.android.car.settings.wifi.details.WifiInfoProvider;
import com.android.settingslib.wifi.AccessPoint;

/* JADX INFO: loaded from: classes5.dex */
public abstract class WifiDetailsBasePreferenceController<V extends Preference> extends PreferenceController<V> implements WifiInfoProvider.Listener {
    private AccessPoint mAccessPoint;
    private WifiInfoProvider mWifiInfoProvider;

    public WifiDetailsBasePreferenceController(Context context, String preferenceKey, FragmentController fragmentController, CarUxRestrictions uxRestrictions) {
        super(context, preferenceKey, fragmentController, uxRestrictions);
    }

    public WifiDetailsBasePreferenceController init(AccessPoint accessPoint, WifiInfoProvider wifiInfoProvider) {
        this.mAccessPoint = accessPoint;
        this.mWifiInfoProvider = wifiInfoProvider;
        return this;
    }

    protected AccessPoint getAccessPoint() {
        return this.mAccessPoint;
    }

    protected WifiInfoProvider getWifiInfoProvider() {
        return this.mWifiInfoProvider;
    }

    @Override // com.android.car.settings.common.PreferenceController
    protected void onStartInternal() {
        this.mWifiInfoProvider.addListener(this);
    }

    @Override // com.android.car.settings.common.PreferenceController
    protected void onStopInternal() {
        this.mWifiInfoProvider.removeListener(this);
    }

    @Override // com.android.car.settings.wifi.details.WifiInfoProvider.Listener
    public void onWifiConfigurationChanged(WifiConfiguration wifiConfiguration, NetworkInfo networkInfo, WifiInfo wifiInfo) {
        refreshUi();
    }

    @Override // com.android.car.settings.wifi.details.WifiInfoProvider.Listener
    public void onLinkPropertiesChanged(Network network, LinkProperties lp) {
        refreshUi();
    }

    @Override // com.android.car.settings.wifi.details.WifiInfoProvider.Listener
    public void onCapabilitiesChanged(Network network, NetworkCapabilities nc) {
    }

    @Override // com.android.car.settings.wifi.details.WifiInfoProvider.Listener
    public void onWifiChanged(NetworkInfo networkInfo, WifiInfo wifiInfo) {
        getPreference().setEnabled(true);
        refreshUi();
    }

    @Override // com.android.car.settings.wifi.details.WifiInfoProvider.Listener
    public void onLost(Network network) {
        getPreference().setEnabled(false);
        refreshUi();
    }

    @Override // com.android.car.settings.common.PreferenceController
    protected int getAvailabilityStatus() {
        if (WifiUtil.isWifiAvailable(getContext())) {
            return !getAccessPoint().isActive() ? 1 : 0;
        }
        return 2;
    }
}
