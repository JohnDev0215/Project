package com.ts.carplay.linkadapter.device;

import com.ts.carplay.common.util.LogUtil;
import com.ts.carplay.linkadapter.device.data.LinkDevice;

/* JADX INFO: loaded from: classes.dex */
public class DeviceManager {
    private static final String TAG = "DeviceManager";
    private static volatile DeviceManager mInstance;
    private LinkDevice mDevice = new LinkDevice();

    private DeviceManager() {
    }

    public static DeviceManager getInstance() {
        if (mInstance == null) {
            synchronized (DeviceManager.class) {
                if (mInstance == null) {
                    mInstance = new DeviceManager();
                }
            }
        }
        return mInstance;
    }

    public LinkDevice getDeviceByDeviceId(String deviceId) {
        if (this.mDevice != null) {
            LogUtil.debug(TAG, "getDeviceByDeviceId: mDeviceID: " + this.mDevice.getDeviceId() + "in deviceId: " + deviceId);
            if (this.mDevice.getDeviceId() != null && this.mDevice.getDeviceId().equals(deviceId)) {
                LogUtil.debug(TAG, "getDeviceByDeviceId: " + deviceId);
                return this.mDevice;
            }
            LogUtil.warning(TAG, "getDeviceByDeviceId in deviceID is not my memory");
            return null;
        }
        LogUtil.warning(TAG, "getDeviceByDeviceId mDevice is NULL");
        return null;
    }

    public void saveDevice(LinkDevice device) {
        if (device == null) {
            LogUtil.warning(TAG, "saveDevice error ");
            return;
        }
        this.mDevice.copy(device);
        LogUtil.debug(TAG, "saveDevice: " + this.mDevice);
    }

    public LinkDevice getDevice() {
        return this.mDevice;
    }

    public void saveConnectStatus(LinkDevice device, int status) {
        LinkDevice linkDevice = this.mDevice;
        if (linkDevice == null || !linkDevice.equals(device)) {
            LogUtil.warning(TAG, "saveConnectStatus error:  ");
            return;
        }
        LogUtil.debug(TAG, "saveConnectStatus ok:  " + this.mDevice + " status : " + status);
        this.mDevice.setStatus(status);
    }

    public int getConnectStatus(LinkDevice device) {
        LinkDevice linkDevice = this.mDevice;
        if (linkDevice == null || !linkDevice.equals(device)) {
            LogUtil.warning(TAG, "getConnectStatus error:  ");
            return 0;
        }
        LogUtil.debug(TAG, "getConnectStatus mDevice:" + this.mDevice + " status : " + this.mDevice.getStatus());
        return this.mDevice.getStatus();
    }
}
