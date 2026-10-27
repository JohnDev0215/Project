package com.android.car.settings.bluetooth;

import android.content.Context;
import com.android.car.ui.preference.CarUiSwitchPreference;
import com.android.settingslib.bluetooth.CachedBluetoothDevice;
import com.android.settingslib.bluetooth.LocalBluetoothProfile;
import com.android.settingslib.bluetooth.PanProfile;

/* JADX INFO: loaded from: classes5.dex */
public class BluetoothDeviceProfilePreference extends CarUiSwitchPreference {
    private final CachedBluetoothDevice mCachedDevice;
    private final CachedBluetoothDevice.Callback mDeviceCallback;
    private final LocalBluetoothProfile mProfile;

    public BluetoothDeviceProfilePreference(Context context, LocalBluetoothProfile profile, CachedBluetoothDevice cachedDevice) {
        super(context);
        this.mDeviceCallback = new CachedBluetoothDevice.Callback() { // from class: com.android.car.settings.bluetooth.-$$Lambda$BluetoothDeviceProfilePreference$Rc4OCKUYmvGkqy6bAadxESt9qaQ
            @Override // com.android.settingslib.bluetooth.CachedBluetoothDevice.Callback
            public final void onDeviceAttributesChanged() {
                this.f$0.refreshUi();
            }
        };
        this.mProfile = profile;
        this.mCachedDevice = cachedDevice;
        setKey(profile.toString());
        setTitle(profile.getNameResource(cachedDevice.getDevice()));
    }

    public LocalBluetoothProfile getProfile() {
        return this.mProfile;
    }

    public CachedBluetoothDevice getCachedDevice() {
        return this.mCachedDevice;
    }

    @Override // androidx.preference.Preference
    public void onAttached() {
        super.onAttached();
        this.mCachedDevice.registerCallback(this.mDeviceCallback);
        refreshUi();
    }

    @Override // androidx.preference.Preference
    public void onDetached() {
        super.onDetached();
        this.mCachedDevice.unregisterCallback(this.mDeviceCallback);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void refreshUi() {
        setEnabled(!this.mCachedDevice.isBusy());
        LocalBluetoothProfile localBluetoothProfile = this.mProfile;
        if (localBluetoothProfile instanceof PanProfile) {
            setChecked(localBluetoothProfile.getConnectionStatus(this.mCachedDevice.getDevice()) == 2);
        } else {
            setChecked(localBluetoothProfile.isEnabled(this.mCachedDevice.getDevice()));
        }
    }
}
