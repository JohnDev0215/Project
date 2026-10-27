package com.android.car.developeroptions.bluetooth;

import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.util.Log;
import androidx.preference.Preference;
import com.android.car.developeroptions.connecteddevice.DevicePreferenceCallback;
import com.android.car.developeroptions.dashboard.DashboardFragment;
import com.android.settingslib.bluetooth.CachedBluetoothDevice;

/* JADX INFO: loaded from: classes.dex */
public class SavedBluetoothDeviceUpdater extends BluetoothDeviceUpdater implements Preference.OnPreferenceClickListener {
    public SavedBluetoothDeviceUpdater(Context context, DashboardFragment dashboardFragment, DevicePreferenceCallback devicePreferenceCallback) {
        super(context, dashboardFragment, devicePreferenceCallback);
    }

    @Override // com.android.car.developeroptions.bluetooth.BluetoothDeviceUpdater
    public boolean isFilterMatched(CachedBluetoothDevice cachedBluetoothDevice) {
        BluetoothDevice device = cachedBluetoothDevice.getDevice();
        Log.d("SavedBluetoothDeviceUpdater", "isFilterMatched() device name : " + cachedBluetoothDevice.getName() + ", is connected : " + device.isConnected() + ", is profile connected : " + cachedBluetoothDevice.isConnected());
        return device.getBondState() == 12 && !device.isConnected();
    }

    @Override // androidx.preference.Preference.OnPreferenceClickListener
    public boolean onPreferenceClick(Preference preference) {
        ((BluetoothDevicePreference) preference).getBluetoothDevice().connect(true);
        return true;
    }
}
