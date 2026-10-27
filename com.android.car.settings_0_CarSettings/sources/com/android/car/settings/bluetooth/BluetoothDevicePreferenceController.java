package com.android.car.settings.bluetooth;

import android.car.drivingstate.CarUxRestrictions;
import android.content.Context;
import androidx.preference.Preference;
import com.android.car.settings.common.FragmentController;
import com.android.settingslib.bluetooth.CachedBluetoothDevice;

/* JADX INFO: loaded from: classes5.dex */
public abstract class BluetoothDevicePreferenceController<V extends Preference> extends BluetoothPreferenceController<V> {
    private CachedBluetoothDevice mCachedDevice;
    private final CachedBluetoothDevice.Callback mDeviceCallback;

    public BluetoothDevicePreferenceController(Context context, String preferenceKey, FragmentController fragmentController, CarUxRestrictions uxRestrictions) {
        super(context, preferenceKey, fragmentController, uxRestrictions);
        this.mDeviceCallback = new CachedBluetoothDevice.Callback() { // from class: com.android.car.settings.bluetooth.-$$Lambda$Bisb7lU06WDXBwigrqekwsIOaqI
            @Override // com.android.settingslib.bluetooth.CachedBluetoothDevice.Callback
            public final void onDeviceAttributesChanged() {
                this.f$0.refreshUi();
            }
        };
    }

    public void setCachedDevice(CachedBluetoothDevice device) {
        this.mCachedDevice = device;
    }

    public CachedBluetoothDevice getCachedDevice() {
        return this.mCachedDevice;
    }

    @Override // com.android.car.settings.common.PreferenceController
    protected void checkInitialized() {
        if (this.mCachedDevice == null) {
            throw new IllegalStateException("Must be initialized with a CachedBluetoothDevice");
        }
    }

    @Override // com.android.car.settings.bluetooth.BluetoothPreferenceController, com.android.car.settings.common.PreferenceController
    protected int getAvailabilityStatus() {
        int availabilityStatus = super.getAvailabilityStatus();
        if (availabilityStatus == 0) {
            return getUserManager().hasUserRestriction("no_config_bluetooth") ? 3 : 0;
        }
        return availabilityStatus;
    }

    @Override // com.android.car.settings.bluetooth.BluetoothPreferenceController, com.android.car.settings.common.PreferenceController
    protected void onStartInternal() {
        super.onStartInternal();
        this.mCachedDevice.registerCallback(this.mDeviceCallback);
    }

    @Override // com.android.car.settings.bluetooth.BluetoothPreferenceController, com.android.car.settings.common.PreferenceController
    protected void onStopInternal() {
        super.onStopInternal();
        this.mCachedDevice.unregisterCallback(this.mDeviceCallback);
    }
}
