package com.android.car.settings.wifi;

import android.car.drivingstate.CarUxRestrictions;
import android.content.Context;
import androidx.preference.Preference;
import com.android.car.settings.common.FragmentController;
import com.android.car.settings.common.PreferenceController;
import com.android.car.settings.wifi.CarWifiManager;

/* JADX INFO: loaded from: classes5.dex */
public abstract class WifiBasePreferenceController<V extends Preference> extends PreferenceController<V> implements CarWifiManager.Listener {
    private CarWifiManager mCarWifiManager;

    public WifiBasePreferenceController(Context context, String preferenceKey, FragmentController fragmentController, CarUxRestrictions uxRestrictions) {
        super(context, preferenceKey, fragmentController, uxRestrictions);
    }

    @Override // com.android.car.settings.common.PreferenceController
    protected void onCreateInternal() {
        this.mCarWifiManager = new CarWifiManager(getContext());
    }

    @Override // com.android.car.settings.common.PreferenceController
    protected void onStartInternal() {
        this.mCarWifiManager.addListener(this);
        this.mCarWifiManager.start();
    }

    @Override // com.android.car.settings.common.PreferenceController
    protected void onStopInternal() {
        this.mCarWifiManager.removeListener(this);
        this.mCarWifiManager.stop();
    }

    @Override // com.android.car.settings.common.PreferenceController
    protected void onDestroyInternal() {
        this.mCarWifiManager.destroy();
    }

    @Override // com.android.car.settings.common.PreferenceController
    public int getAvailabilityStatus() {
        return WifiUtil.isWifiAvailable(getContext()) ? 0 : 2;
    }

    public void onAccessPointsChanged() {
    }

    protected CarWifiManager getCarWifiManager() {
        return this.mCarWifiManager;
    }
}
