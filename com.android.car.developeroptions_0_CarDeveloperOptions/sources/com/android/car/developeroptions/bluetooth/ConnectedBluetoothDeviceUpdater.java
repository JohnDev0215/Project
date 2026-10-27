package com.android.car.developeroptions.bluetooth;

import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.media.AudioManager;
import android.util.Log;
import androidx.preference.Preference;
import com.android.car.developeroptions.connecteddevice.DevicePreferenceCallback;
import com.android.car.developeroptions.dashboard.DashboardFragment;
import com.android.settingslib.bluetooth.CachedBluetoothDevice;

/* JADX INFO: loaded from: classes.dex */
public class ConnectedBluetoothDeviceUpdater extends BluetoothDeviceUpdater {
    private final AudioManager mAudioManager;

    public ConnectedBluetoothDeviceUpdater(Context context, DashboardFragment dashboardFragment, DevicePreferenceCallback devicePreferenceCallback) {
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
            Log.d("ConnBluetoothDeviceUpdater", "isFilterMatched() current audio profile : " + i);
            if (cachedBluetoothDevice.isConnectedHearingAidDevice()) {
                return false;
            }
            if (i == 1) {
                zIsConnectedHfpDevice = cachedBluetoothDevice.isConnectedHfpDevice();
            } else {
                if (i == 2) {
                    zIsConnectedHfpDevice = cachedBluetoothDevice.isConnectedA2dpDevice();
                }
                Log.d("ConnBluetoothDeviceUpdater", "isFilterMatched() device : " + cachedBluetoothDevice.getName() + ", isFilterMatched : " + z);
            }
            z = !zIsConnectedHfpDevice;
            Log.d("ConnBluetoothDeviceUpdater", "isFilterMatched() device : " + cachedBluetoothDevice.getName() + ", isFilterMatched : " + z);
        }
        return z;
    }

    @Override // com.android.car.developeroptions.bluetooth.BluetoothDeviceUpdater
    protected void addPreference(CachedBluetoothDevice cachedBluetoothDevice) {
        super.addPreference(cachedBluetoothDevice);
        BluetoothDevice device = cachedBluetoothDevice.getDevice();
        if (this.mPreferenceMap.containsKey(device)) {
            BluetoothDevicePreference bluetoothDevicePreference = (BluetoothDevicePreference) this.mPreferenceMap.get(device);
            bluetoothDevicePreference.setOnGearClickListener(null);
            bluetoothDevicePreference.hideSecondTarget(true);
            bluetoothDevicePreference.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() { // from class: com.android.car.developeroptions.bluetooth.-$$Lambda$ConnectedBluetoothDeviceUpdater$_o-Xmf5vf3tMQI3OADy03lXRxso
                @Override // androidx.preference.Preference.OnPreferenceClickListener
                public final boolean onPreferenceClick(Preference preference) {
                    return this.f$0.lambda$addPreference$0$ConnectedBluetoothDeviceUpdater(preference);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: lambda$addPreference$0, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ boolean lambda$addPreference$0$ConnectedBluetoothDeviceUpdater(Preference preference) {
        lambda$new$0$BluetoothDeviceUpdater(preference);
        return true;
    }
}
