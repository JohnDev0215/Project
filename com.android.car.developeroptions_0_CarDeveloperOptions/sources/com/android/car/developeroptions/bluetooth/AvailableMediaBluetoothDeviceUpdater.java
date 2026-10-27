package com.android.car.developeroptions.bluetooth;

import android.content.Context;
import android.media.AudioManager;
import android.util.Log;
import androidx.preference.Preference;
import com.android.car.developeroptions.connecteddevice.DevicePreferenceCallback;
import com.android.car.developeroptions.dashboard.DashboardFragment;
import com.android.settingslib.bluetooth.CachedBluetoothDevice;

/* JADX INFO: loaded from: classes.dex */
public class AvailableMediaBluetoothDeviceUpdater extends BluetoothDeviceUpdater implements Preference.OnPreferenceClickListener {
    private final AudioManager mAudioManager;

    public AvailableMediaBluetoothDeviceUpdater(Context context, DashboardFragment dashboardFragment, DevicePreferenceCallback devicePreferenceCallback) {
        super(context, dashboardFragment, devicePreferenceCallback);
        this.mAudioManager = (AudioManager) context.getSystemService("audio");
    }

    @Override // com.android.settingslib.bluetooth.BluetoothCallback
    public void onAudioModeChanged() {
        forceUpdate();
    }

    @Override // com.android.car.developeroptions.bluetooth.BluetoothDeviceUpdater
    public boolean isFilterMatched(CachedBluetoothDevice cachedBluetoothDevice) {
        boolean zIsConnectedHfpDevice;
        int mode = this.mAudioManager.getMode();
        int i = (mode == 1 || mode == 2 || mode == 3) ? 1 : 2;
        boolean z = false;
        if (isDeviceConnected(cachedBluetoothDevice)) {
            Log.d("AvailableMediaBluetoothDeviceUpdater", "isFilterMatched() current audio profile : " + i);
            if (cachedBluetoothDevice.isConnectedHearingAidDevice()) {
                return true;
            }
            if (i == 1) {
                zIsConnectedHfpDevice = cachedBluetoothDevice.isConnectedHfpDevice();
            } else {
                if (i == 2) {
                    zIsConnectedHfpDevice = cachedBluetoothDevice.isConnectedA2dpDevice();
                }
                Log.d("AvailableMediaBluetoothDeviceUpdater", "isFilterMatched() device : " + cachedBluetoothDevice.getName() + ", isFilterMatched : " + z);
            }
            z = zIsConnectedHfpDevice;
            Log.d("AvailableMediaBluetoothDeviceUpdater", "isFilterMatched() device : " + cachedBluetoothDevice.getName() + ", isFilterMatched : " + z);
        }
        return z;
    }

    @Override // androidx.preference.Preference.OnPreferenceClickListener
    public boolean onPreferenceClick(Preference preference) {
        return ((BluetoothDevicePreference) preference).getBluetoothDevice().setActive();
    }
}
