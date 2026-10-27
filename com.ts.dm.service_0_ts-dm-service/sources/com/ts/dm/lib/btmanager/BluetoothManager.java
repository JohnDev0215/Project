package com.ts.dm.lib.btmanager;

import android.content.Context;
import com.ts.car.sdk.dm.data.DmDevice;
import com.ts.dm.lib.devicemanager.DeviceManager;

/* JADX INFO: loaded from: classes.dex */
public class BluetoothManager {
    private static final String TAG = "BluetoothManager";
    private DeviceManager mDeviceManager;

    public BluetoothManager(Context context, DeviceManager deviceManager) {
        this.mDeviceManager = deviceManager;
    }

    public int connectProfile(String deviceId, int type) {
        DmDevice device = this.mDeviceManager.getDeviceById(deviceId);
        if (device == null) {
            return -1;
        }
        if (type == 5) {
            int result = convertResultCode(this.mDeviceManager.connectProfile(device.getMacAddress(), 1));
            return result;
        }
        if (type != 6) {
            return -1;
        }
        int result2 = convertResultCode(this.mDeviceManager.connectProfile(device.getMacAddress(), 2));
        return result2;
    }

    public int disconnectProfile(String deviceId, int type) {
        DmDevice device = this.mDeviceManager.getDeviceById(deviceId);
        if (device == null) {
            return -1;
        }
        if (type == 5) {
            int result = convertResultCode(this.mDeviceManager.disconnectProfile(device.getMacAddress(), 1));
            return result;
        }
        if (type != 6) {
            return -1;
        }
        int result2 = convertResultCode(this.mDeviceManager.disconnectProfile(device.getMacAddress(), 2));
        return result2;
    }

    private int convertResultCode(int code) {
        if (code == 1) {
            return 0;
        }
        return -1;
    }
}
