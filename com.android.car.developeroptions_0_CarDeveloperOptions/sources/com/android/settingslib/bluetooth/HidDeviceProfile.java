package com.android.settingslib.bluetooth;

import android.R;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothClass;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothHidDevice;
import android.bluetooth.BluetoothProfile;
import android.content.Context;
import android.util.Log;

/* JADX INFO: loaded from: classes.dex */
public class HidDeviceProfile implements LocalBluetoothProfile {
    private final CachedBluetoothDeviceManager mDeviceManager;
    private boolean mIsProfileReady;
    private BluetoothHidDevice mService;

    @Override // com.android.settingslib.bluetooth.LocalBluetoothProfile
    public int getDrawableResource(BluetoothClass bluetoothClass) {
        return R.drawable.editbox_background_focus_yellow;
    }

    @Override // com.android.settingslib.bluetooth.LocalBluetoothProfile
    public int getProfileId() {
        return 19;
    }

    public String toString() {
        return "HID DEVICE";
    }

    HidDeviceProfile(Context context, CachedBluetoothDeviceManager cachedBluetoothDeviceManager, LocalBluetoothProfileManager localBluetoothProfileManager) {
        this.mDeviceManager = cachedBluetoothDeviceManager;
        BluetoothAdapter.getDefaultAdapter().getProfileProxy(context, new HidDeviceServiceListener(), 19);
    }

    private final class HidDeviceServiceListener implements BluetoothProfile.ServiceListener {
        private HidDeviceServiceListener() {
        }

        @Override // android.bluetooth.BluetoothProfile.ServiceListener
        public void onServiceConnected(int i, BluetoothProfile bluetoothProfile) {
            HidDeviceProfile.this.mService = (BluetoothHidDevice) bluetoothProfile;
            for (BluetoothDevice bluetoothDevice : HidDeviceProfile.this.mService.getConnectedDevices()) {
                CachedBluetoothDevice cachedBluetoothDeviceFindDevice = HidDeviceProfile.this.mDeviceManager.findDevice(bluetoothDevice);
                if (cachedBluetoothDeviceFindDevice == null) {
                    Log.w("HidDeviceProfile", "HidProfile found new device: " + bluetoothDevice);
                    cachedBluetoothDeviceFindDevice = HidDeviceProfile.this.mDeviceManager.addDevice(bluetoothDevice);
                }
                Log.d("HidDeviceProfile", "Connection status changed: " + cachedBluetoothDeviceFindDevice);
                cachedBluetoothDeviceFindDevice.onProfileStateChanged(HidDeviceProfile.this, 2);
                cachedBluetoothDeviceFindDevice.refresh();
            }
            HidDeviceProfile.this.mIsProfileReady = true;
        }

        @Override // android.bluetooth.BluetoothProfile.ServiceListener
        public void onServiceDisconnected(int i) {
            HidDeviceProfile.this.mIsProfileReady = false;
        }
    }

    @Override // com.android.settingslib.bluetooth.LocalBluetoothProfile
    public boolean isProfileReady() {
        return this.mIsProfileReady;
    }

    @Override // com.android.settingslib.bluetooth.LocalBluetoothProfile
    public int getConnectionStatus(BluetoothDevice bluetoothDevice) {
        BluetoothHidDevice bluetoothHidDevice = this.mService;
        if (bluetoothHidDevice == null) {
            return 0;
        }
        return bluetoothHidDevice.getConnectionState(bluetoothDevice);
    }

    @Override // com.android.settingslib.bluetooth.LocalBluetoothProfile
    public boolean setEnabled(BluetoothDevice bluetoothDevice, boolean z) {
        if (z) {
            return false;
        }
        return this.mService.setConnectionPolicy(bluetoothDevice, 0);
    }

    protected void finalize() {
        Log.d("HidDeviceProfile", "finalize()");
        if (this.mService != null) {
            try {
                BluetoothAdapter.getDefaultAdapter().closeProfileProxy(19, this.mService);
                this.mService = null;
            } catch (Throwable th) {
                Log.w("HidDeviceProfile", "Error cleaning up HID proxy", th);
            }
        }
    }
}
