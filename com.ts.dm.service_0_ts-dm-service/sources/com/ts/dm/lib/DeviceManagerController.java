package com.ts.dm.lib;

import android.content.Context;
import com.ts.car.sdk.dm.DmConsumer;
import com.ts.car.sdk.dm.aidl.ICheckResultCallback;
import com.ts.car.sdk.dm.aidl.IDeviceManagementListener;
import com.ts.car.sdk.dm.data.ConnectRequest;
import com.ts.car.sdk.dm.data.DmDevice;
import com.ts.dm.lib.config.Config;
import com.ts.dm.lib.config.DeviceManagerConfig;
import com.ts.dm.lib.connectionmanager.ConnectionManager;
import com.ts.dm.lib.connectionmanager.DmsListenerManager;
import com.ts.dm.lib.devicemanager.DeviceManager;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class DeviceManagerController implements IDeviceManagerController, DeviceManager.OnDeviceChangedListener {
    public static final String TAG = "DeviceManagerController";
    private ConnectionManager mConnectionManager;
    private DeviceManager mDeviceManager;
    private DmsListenerManager mDmsListenerManager = DmsListenerManager.getInstance();

    public DeviceManagerController(Context context, int dbVersion, Config config) {
        DeviceManagerConfig.init(config);
        DeviceManager deviceManager = new DeviceManager(context, dbVersion);
        this.mDeviceManager = deviceManager;
        this.mConnectionManager = new ConnectionManager(context, deviceManager);
        this.mDeviceManager.addOnDeviceChangedListener(this);
    }

    @Override // com.ts.dm.lib.IDeviceManagerController
    public void registerListener(IDeviceManagementListener listener) {
        this.mDmsListenerManager.registerListener(listener);
    }

    @Override // com.ts.dm.lib.IDeviceManagerController
    public void unregisterListener(IDeviceManagementListener listener) {
        this.mDmsListenerManager.unregisterListener(listener);
    }

    @Override // com.ts.dm.lib.IDeviceManagerController
    public int connect(String deviceId, int type) {
        return this.mConnectionManager.connect(deviceId, type);
    }

    @Override // com.ts.dm.lib.IDeviceManagerController
    public int connect(ConnectRequest connectRequest) {
        return this.mConnectionManager.connect(connectRequest);
    }

    @Override // com.ts.dm.lib.IDeviceManagerController
    public int disconnect(String deviceId, int type) {
        return this.mConnectionManager.disconnect(deviceId, type);
    }

    @Override // com.ts.dm.lib.IDeviceManagerController
    public void checkCarplayWirelessCapability(String mac, ICheckResultCallback callback) {
        this.mConnectionManager.checkCarplayWirelessCapability(mac, callback);
    }

    @Override // com.ts.dm.lib.IDeviceManagerController
    public List<DmDevice> getDeviceList() {
        return this.mDeviceManager.getDeviceList();
    }

    @Override // com.ts.dm.lib.IDeviceManagerController
    public List<DmDevice> getOnlineDeviceList() {
        return this.mDeviceManager.getOnlineDeviceList();
    }

    @Override // com.ts.dm.lib.IDeviceManagerController
    public boolean deleteDevice(String deviceId) {
        DmDevice device = this.mDeviceManager.getDeviceById(deviceId);
        if (device == null) {
            return false;
        }
        return deleteDevice(device);
    }

    private boolean deleteDevice(DmDevice device) {
        device.setDelete(true);
        this.mConnectionManager.disconnectProjection(device.getDeviceUuid());
        return this.mDeviceManager.deleteDevice(device);
    }

    @Override // com.ts.dm.lib.IDeviceManagerController
    public boolean cancelWirelessCarPlay(String mac) {
        return this.mDeviceManager.cancelWirelessCarPlay(mac);
    }

    @Override // com.ts.dm.lib.devicemanager.DeviceManager.OnDeviceChangedListener
    public void onDeviceStatusChanged(final DmDevice device) {
        this.mDmsListenerManager.broadcastListener(new DmConsumer.ReConsumer() { // from class: com.ts.dm.lib.-$$Lambda$DeviceManagerController$EWVZKqxmlnbR9XQYUhLHClplUbk
            public final void accept(Object obj) {
                ((IDeviceManagementListener) obj).onDeviceChanged(device);
            }
        });
    }

    @Override // com.ts.dm.lib.devicemanager.DeviceManager.OnDeviceChangedListener
    public void onDeviceProjectionRequested(final boolean supported, final ConnectRequest request) {
        this.mDmsListenerManager.broadcastListener(new DmConsumer.ReConsumer() { // from class: com.ts.dm.lib.-$$Lambda$DeviceManagerController$BRSUycKs_Sr2DZyMsNSUcvrTCDo
            public final void accept(Object obj) {
                ((IDeviceManagementListener) obj).onDeviceProjectionRequested(supported, request);
            }
        });
    }

    @Override // com.ts.dm.lib.devicemanager.DeviceManager.OnDeviceChangedListener
    public void onDeviceDetachedOverUsb(final DmDevice device) {
        this.mDmsListenerManager.broadcastListener(new DmConsumer.ReConsumer() { // from class: com.ts.dm.lib.-$$Lambda$DeviceManagerController$PLpT_bNFLg5_7aGYSfR6LJfhBDM
            public final void accept(Object obj) {
                ((IDeviceManagementListener) obj).onDeviceDetachedOverUsb(device);
            }
        });
    }

    private void trimToSize() {
        List<DmDevice> deleteList = this.mDeviceManager.trimToSize(DeviceManagerConfig.getDeviceListMaxSize());
        if (deleteList != null && !deleteList.isEmpty()) {
            for (DmDevice d : deleteList) {
                deleteDevice(d);
            }
        }
    }
}
