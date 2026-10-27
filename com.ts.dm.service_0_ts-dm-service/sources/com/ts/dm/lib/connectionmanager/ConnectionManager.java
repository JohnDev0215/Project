package com.ts.dm.lib.connectionmanager;

import android.content.Context;
import android.text.TextUtils;
import android.util.SparseIntArray;
import com.ts.car.sdk.dm.aidl.ICheckResultCallback;
import com.ts.car.sdk.dm.data.ConnectRequest;
import com.ts.car.sdk.dm.data.DmDevice;
import com.ts.dm.lib.btmanager.BluetoothManager;
import com.ts.dm.lib.devicemanager.DeviceManager;
import com.ts.dm.lib.projectionmanager.ProjectionManager;
import com.ts.dm.lib.util.Utils;
import java.util.function.Consumer;
import ts.car.dm.fsdk.interfaces.BluetoothDevice;

/* JADX INFO: loaded from: classes.dex */
public class ConnectionManager implements DeviceManager.OnDeviceChangedListener {
    public static final String TAG = "ConnectionManager";
    private BluetoothManager mBluetoothManager;
    private DeviceManager mDeviceManager;
    private ProjectionManager mProjectionManager;

    public ConnectionManager(Context context, DeviceManager deviceManager) {
        this.mDeviceManager = deviceManager;
        deviceManager.addOnDeviceChangedListener(this);
        this.mProjectionManager = new ProjectionManager(context, deviceManager);
        this.mBluetoothManager = new BluetoothManager(context, deviceManager);
    }

    public void checkCarplayWirelessCapability(String mac, ICheckResultCallback callback) {
        this.mProjectionManager.checkCarplayWirelessCapability(mac, callback);
    }

    public int connect(String deviceId, int type) {
        ConnectRequest connectRequest = new ConnectRequest();
        DmDevice dmDevice = new DmDevice(deviceId);
        connectRequest.setDevice(dmDevice);
        connectRequest.setCapabilityType(type);
        connectRequest.setConnectType(0);
        return connect(connectRequest);
    }

    public int connect(ConnectRequest connectRequest) {
        int capabilityType = connectRequest.getCapabilityType();
        if (capabilityType == 0) {
            return -1;
        }
        if (capabilityType == 5 || capabilityType == 6) {
            return this.mBluetoothManager.connectProfile(connectRequest.getDevice().getDeviceUuid(), capabilityType);
        }
        return this.mProjectionManager.connectProjection(connectRequest.getDevice().getDeviceUuid(), capabilityType, connectRequest.getConnectionType());
    }

    public int disconnect(String deviceId, int type) {
        if (type == 0) {
            return -1;
        }
        if (type == 5 || type == 6) {
            return this.mBluetoothManager.disconnectProfile(deviceId, type);
        }
        return this.mProjectionManager.disconnectProjection(deviceId, type);
    }

    public void disconnectProjection(String deviceId) {
        this.mProjectionManager.disconnectProjection(deviceId, 1);
        this.mProjectionManager.disconnectProjection(deviceId, 2);
        this.mProjectionManager.disconnectProjection(deviceId, 3);
        this.mProjectionManager.disconnectProjection(deviceId, 4);
    }

    @Override // com.ts.dm.lib.devicemanager.DeviceManager.OnDeviceChangedListener
    public void onDeviceStatusChanged(final DmDevice device) {
        if (isProjectionConnected(device)) {
            this.mDeviceManager.getDeviceList().forEach(new Consumer() { // from class: com.ts.dm.lib.connectionmanager.-$$Lambda$ConnectionManager$qQmHiNoGSJeEr-2Q3EFtBwvtQtQ
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    ConnectionManager.lambda$onDeviceStatusChanged$0(device, (DmDevice) obj);
                }
            });
        }
    }

    static /* synthetic */ void lambda$onDeviceStatusChanged$0(DmDevice device, DmDevice d) {
        String otherDeviceMacAddress = d.getMacAddress();
        if (!TextUtils.isEmpty(otherDeviceMacAddress)) {
            otherDeviceMacAddress.equals(device.getMacAddress());
        }
    }

    @Override // com.ts.dm.lib.devicemanager.DeviceManager.OnDeviceChangedListener
    public void onDeviceProfileStatusChanged(BluetoothDevice device, int profile) {
        DmDevice dmDevice = this.mProjectionManager.getActiveDevice();
        if (dmDevice != null && isConnectedBluetoothProfile(device) && !Utils.isSameDevice(device, dmDevice)) {
            isConnectedHfp(device);
        }
    }

    private boolean isProjectionConnected(DmDevice device) {
        return device.isCapabilityConnected(1) || device.isCapabilityConnected(2);
    }

    private boolean isConnectedBluetoothProfile(BluetoothDevice bluetoothDevice) {
        SparseIntArray profiles = bluetoothDevice.getProfiles();
        if (profiles != null && profiles.size() > 0) {
            for (int i = 0; i < profiles.size(); i++) {
                int profile = profiles.keyAt(i);
                int newState = Utils.convertBluetoothDeviceStatus(profiles.get(profile));
                if (newState == 4) {
                    return true;
                }
            }
            return false;
        }
        return false;
    }

    private boolean isConnectedHfp(BluetoothDevice bluetoothDevice) {
        SparseIntArray profiles = bluetoothDevice.getProfiles();
        if (profiles != null && profiles.size() > 0) {
            for (int i = 0; i < profiles.size(); i++) {
                int profile = profiles.keyAt(i);
                int newState = Utils.convertBluetoothDeviceStatus(profiles.get(profile));
                if (profile == 1 && newState == 4) {
                    return true;
                }
            }
            return false;
        }
        return false;
    }
}
