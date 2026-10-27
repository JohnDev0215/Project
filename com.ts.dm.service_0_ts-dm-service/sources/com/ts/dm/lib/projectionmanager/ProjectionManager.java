package com.ts.dm.lib.projectionmanager;

import android.content.Context;
import android.os.RemoteException;
import com.ts.car.sdk.dm.aidl.ICheckResultCallback;
import com.ts.car.sdk.dm.data.DmDevice;
import com.ts.dm.lib.connectionmanager.ConnectionStateManager;
import com.ts.dm.lib.devicemanager.DeviceManager;
import ts.car.dm.fsdk.interfaces.IDeviceCapabilityCheckCallback;
import ts.car.dm.fsdk.interfaces.ProjectionDevice;

/* JADX INFO: loaded from: classes.dex */
public class ProjectionManager {
    private static final String TAG = "ProjectionManager";
    private ConnectionStateManager mConnectionStateManager;
    private DeviceManager mDeviceManager;

    public ProjectionManager(Context context, DeviceManager deviceManager) {
        this.mDeviceManager = deviceManager;
        this.mConnectionStateManager = new ConnectionStateManager(deviceManager);
    }

    public int connectProjection(String deviceId, int capability, int connectionType) {
        ProjectionDevice device = getProjectionDevice(deviceId, capability);
        if (device == null) {
            return -1;
        }
        if (device.getDeviceStatus() == -1) {
            this.mDeviceManager.checkDeviceStatusChanged(device, capability);
            return -1;
        }
        return convertResultCode(device.connect(getConnectType(connectionType), true));
    }

    private ProjectionDevice getProjectionDevice(String deviceId, int type) {
        if (type == 1) {
            ProjectionDevice device = this.mDeviceManager.getProjectionDevice(deviceId, 1);
            return device;
        }
        if (type != 2) {
            return null;
        }
        ProjectionDevice device2 = this.mDeviceManager.getProjectionDevice(deviceId, 2);
        return device2;
    }

    private int getConnectType(int type) {
        if (type == 1) {
            return 1;
        }
        if (type == 2) {
            return 2;
        }
        return 0;
    }

    public int disconnectProjection(String deviceId, int type) {
        ProjectionDevice device = getProjectionDevice(deviceId, type);
        if (device == null) {
            return -1;
        }
        if (device.getDeviceStatus() < 2) {
            this.mDeviceManager.checkDeviceStatusChanged(device, type);
            return -1;
        }
        if (device.getDeviceStatus() == 2 || device.getDeviceStatus() == 5) {
            return -1;
        }
        return convertResultCode(device.disconnect());
    }

    public void checkCarplayWirelessCapability(String mac, final ICheckResultCallback callback) {
        this.mDeviceManager.checkCarplayWirelessCapability(mac, new IDeviceCapabilityCheckCallback() { // from class: com.ts.dm.lib.projectionmanager.ProjectionManager.1
            @Override // ts.car.dm.fsdk.interfaces.IDeviceCapabilityCheckCallback
            public void onCheckWirelessCarPlayCapability(boolean enabled) {
                try {
                    callback.callback(enabled);
                } catch (RemoteException ex) {
                    ex.printStackTrace();
                }
            }
        });
    }

    public DmDevice getActiveDevice() {
        return this.mConnectionStateManager.getActiveDevice();
    }

    private int convertResultCode(int code) {
        if (code == 1) {
            return 0;
        }
        return -1;
    }
}
