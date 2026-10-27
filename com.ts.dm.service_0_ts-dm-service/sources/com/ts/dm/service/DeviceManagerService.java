package com.ts.dm.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.Debug;
import android.os.IBinder;
import com.ts.car.sdk.dm.aidl.ICheckResultCallback;
import com.ts.car.sdk.dm.aidl.IDeviceManagementListener;
import com.ts.car.sdk.dm.aidl.IDeviceManagementService;
import com.ts.car.sdk.dm.data.ConnectRequest;
import com.ts.car.sdk.dm.data.DmDevice;
import com.ts.dm.lib.IDeviceManagerController;
import com.ts.dm.lib.config.Config;
import com.ts.dm.service.util.LogUtil;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class DeviceManagerService extends Service {
    static final String ACCESS_DM_STATE_PERMISSION = "car.permission.ACCESS_DM_STATE";
    static final String CHANGE_DM_STATE_PERMISSION = "car.permission.CHANGE_DM_STATE";
    private static final String TAG = "DeviceManagerService";
    private DeviceManagerBinder mDeviceManagerBinder = new DeviceManagerBinder();
    private IDeviceManagerController mDeviceManagerController;

    @Override // android.app.Service
    public void onCreate() {
        LogUtil.info(TAG, "onCreate.");
        this.mDeviceManagerController = IDeviceManagerController.getDefaultInstance(this, Integer.parseInt(getString(R.string.DatabaseVersion)), new Config.Builder().setDeviceListMaxSize(Integer.parseInt(getString(R.string.DeviceListMaxSize))).enableIndexOfSameDeviceName(getResources().getBoolean(R.bool.enableIndexOfSameDeviceName)).enableCarPlay(getResources().getBoolean(R.bool.enableCarPlay)).enableAndroidAuto(getResources().getBoolean(R.bool.enableAndroidAuto)).build());
        ConnectionPopupHmi.getInstance().init(this, this.mDeviceManagerBinder);
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        LogUtil.info(TAG, "onBind start.");
        return this.mDeviceManagerBinder;
    }

    @Override // android.app.Service
    public void onStart(Intent intent, int startId) {
        super.onStart(intent, startId);
        LogUtil.info(TAG, "onStart.");
    }

    public class DeviceManagerBinder extends IDeviceManagementService.Stub {
        public DeviceManagerBinder() {
        }

        public void registerListener(IDeviceManagementListener listener) {
            checkPermission("car.permission.ACCESS_DM_STATE");
            DeviceManagerService.this.mDeviceManagerController.registerListener(listener);
        }

        public void unregisterListener(IDeviceManagementListener listener) {
            checkPermission("car.permission.ACCESS_DM_STATE");
            DeviceManagerService.this.mDeviceManagerController.unregisterListener(listener);
        }

        public List<DmDevice> getDeviceList() {
            checkPermission("car.permission.ACCESS_DM_STATE");
            List<DmDevice> result = DeviceManagerService.this.mDeviceManagerController.getDeviceList();
            LogUtil.info(DeviceManagerService.TAG, "getDeviceList: " + result);
            return result;
        }

        public List<DmDevice> getOnlineDeviceList() {
            checkPermission("car.permission.ACCESS_DM_STATE");
            List<DmDevice> result = DeviceManagerService.this.mDeviceManagerController.getOnlineDeviceList();
            LogUtil.info(DeviceManagerService.TAG, "getOnlineDeviceList: " + result);
            return result;
        }

        public boolean deleteDevice(String deviceId) {
            checkPermission("car.permission.CHANGE_DM_STATE");
            return DeviceManagerService.this.mDeviceManagerController.deleteDevice(deviceId);
        }

        public int connect(String deviceId, int type) {
            checkPermission("car.permission.CHANGE_DM_STATE");
            return DeviceManagerService.this.mDeviceManagerController.connect(deviceId, type);
        }

        public int connectByRequest(ConnectRequest connectRequest) {
            checkPermission("car.permission.CHANGE_DM_STATE");
            return DeviceManagerService.this.mDeviceManagerController.connect(connectRequest);
        }

        public int disconnect(String deviceId, int type) {
            checkPermission("car.permission.CHANGE_DM_STATE");
            return DeviceManagerService.this.mDeviceManagerController.disconnect(deviceId, type);
        }

        public void checkCarPlayWirelessCapability(String mac, ICheckResultCallback callback) {
            checkPermission("car.permission.ACCESS_DM_STATE");
            DeviceManagerService.this.mDeviceManagerController.checkCarplayWirelessCapability(mac, callback);
        }

        public boolean cancelWirelessCarPlay(String mac) {
            checkPermission("car.permission.CHANGE_DM_STATE");
            return DeviceManagerService.this.mDeviceManagerController.cancelWirelessCarPlay(mac);
        }

        private void checkPermission(String permission) {
            if (Binder.getCallingUid() != 1000 && DeviceManagerService.this.getBaseContext().checkCallingOrSelfPermission(permission) != 0) {
                LogUtil.warning(DeviceManagerService.TAG, "Caller needs permission '" + permission + "' to call " + Debug.getCaller());
                throw new SecurityException("Access denied to process: " + Binder.getCallingPid() + ", must have permission " + permission);
            }
        }
    }
}
