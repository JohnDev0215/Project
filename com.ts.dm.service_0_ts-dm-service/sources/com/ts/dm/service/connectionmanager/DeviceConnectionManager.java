package com.ts.dm.service.connectionmanager;

import com.ts.car.sdk.dm.aidl.IDeviceManagementListener;
import com.ts.car.sdk.dm.data.ConnectRequest;
import com.ts.car.sdk.dm.data.DmDevice;
import com.ts.dm.service.DeviceManagerService;
import com.ts.dm.service.data.ConnectionInfoBean;
import com.ts.dm.service.util.LogUtil;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class DeviceConnectionManager {
    private static final String TAG = "DeviceConnectionManager";
    private DeviceManagerService.DeviceManagerBinder mBinder;
    private final IDeviceManagementListener mDeviceManagerListener;
    private IDeviceConnectionManagerListener mListener;

    public DeviceConnectionManager(DeviceManagerService.DeviceManagerBinder binder) {
        IDeviceManagementListener.Stub stub = new IDeviceManagementListener.Stub() { // from class: com.ts.dm.service.connectionmanager.DeviceConnectionManager.1
            public void onDeviceChanged(DmDevice device) {
                if (device != null) {
                    DeviceConnectionManager.this.deviceChanged(device);
                }
            }

            public void onDeviceProjectionRequested(boolean supported, ConnectRequest request) {
                if (request != null && request.getDevice() != null) {
                    DeviceConnectionManager.this.deviceProjectionRequested(supported, request);
                }
            }

            public void onDeviceDetachedOverUsb(DmDevice device) {
                if (device != null) {
                    DeviceConnectionManager.this.deviceDetached(device);
                }
            }
        };
        this.mDeviceManagerListener = stub;
        this.mBinder = binder;
        if (binder != null) {
            binder.registerListener(stub);
        }
    }

    public void registerListener(IDeviceConnectionManagerListener listener) {
        this.mListener = listener;
    }

    public boolean connect(ConnectionInfoBean infoBean) {
        LogUtil.info(TAG, "connect request: " + infoBean);
        DeviceManagerService.DeviceManagerBinder deviceManagerBinder = this.mBinder;
        if (deviceManagerBinder == null) {
            return false;
        }
        int result = deviceManagerBinder.connectByRequest(infoBean.getConnectRequest());
        return result == 0;
    }

    public List<DmDevice> getOnlineDeviceList() {
        LogUtil.info(TAG, "getOnlineDeviceList");
        DeviceManagerService.DeviceManagerBinder deviceManagerBinder = this.mBinder;
        if (deviceManagerBinder == null) {
            return null;
        }
        return deviceManagerBinder.getOnlineDeviceList();
    }

    public boolean isCapabilityInvalid(int capability, ConnectionInfoBean infoBean) {
        boolean ret = false;
        if (capability == 1 && (infoBean.getConnectRequest().getDevice().getCarPlayProjectionState() == 0 || infoBean.getConnectRequest().getDevice().getCarPlayProjectionState() == 1)) {
            ret = true;
        } else if (capability == 2 && (infoBean.getConnectRequest().getDevice().getAndroidAutoProjectionState() == 0 || infoBean.getConnectRequest().getDevice().getAndroidAutoProjectionState() == 1)) {
            ret = true;
        } else if (capability == 3 && (infoBean.getConnectRequest().getDevice().getCarlifeProjectionState() == 0 || infoBean.getConnectRequest().getDevice().getCarlifeProjectionState() == 1)) {
            ret = true;
        } else if (capability == 4 && (infoBean.getConnectRequest().getDevice().getHicarProjectionState() == 0 || infoBean.getConnectRequest().getDevice().getHicarProjectionState() == 1)) {
            ret = true;
        }
        LogUtil.debug(TAG, "isCapabilityInvalid ret: " + ret);
        return ret;
    }

    public boolean isTransitionState(ConnectionInfoBean infoBean, ConnectionInfoBean newInfoBean) {
        boolean ret = false;
        int capabilityType = infoBean.getConnectRequest().getCapabilityType();
        if (capabilityType == 1) {
            ret = newInfoBean.getConnectRequest().getDevice().getCarPlayProjectionState() == 3 || newInfoBean.getConnectRequest().getDevice().getCarPlayProjectionState() == 5;
        } else if (capabilityType == 2) {
            ret = newInfoBean.getConnectRequest().getDevice().getAndroidAutoProjectionState() == 3 || newInfoBean.getConnectRequest().getDevice().getAndroidAutoProjectionState() == 5;
        } else if (capabilityType == 3) {
            ret = newInfoBean.getConnectRequest().getDevice().getCarlifeProjectionState() == 3 || newInfoBean.getConnectRequest().getDevice().getCarlifeProjectionState() == 5;
        } else if (capabilityType == 4) {
            ret = newInfoBean.getConnectRequest().getDevice().getHicarProjectionState() == 3 || newInfoBean.getConnectRequest().getDevice().getHicarProjectionState() == 5;
        }
        LogUtil.debug(TAG, "isTransitionState ret: " + ret);
        return ret;
    }

    public boolean isStateChanged(ConnectionInfoBean infoBean, ConnectionInfoBean newInfoBean) {
        boolean ret = false;
        int capabilityType = infoBean.getConnectRequest().getCapabilityType();
        if (capabilityType == 1) {
            ret = infoBean.getConnectRequest().getDevice().getCarPlayProjectionState() != newInfoBean.getConnectRequest().getDevice().getCarPlayProjectionState();
        } else if (capabilityType == 2) {
            ret = infoBean.getConnectRequest().getDevice().getAndroidAutoProjectionState() != newInfoBean.getConnectRequest().getDevice().getAndroidAutoProjectionState();
        } else if (capabilityType == 3) {
            ret = infoBean.getConnectRequest().getDevice().getCarlifeProjectionState() != newInfoBean.getConnectRequest().getDevice().getCarlifeProjectionState();
        } else if (capabilityType == 4) {
            ret = infoBean.getConnectRequest().getDevice().getHicarProjectionState() != newInfoBean.getConnectRequest().getDevice().getHicarProjectionState();
        }
        LogUtil.debug(TAG, "isStateChanged ret: " + ret);
        return ret;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void deviceProjectionRequested(boolean supported, ConnectRequest request) {
        IDeviceConnectionManagerListener iDeviceConnectionManagerListener = this.mListener;
        if (iDeviceConnectionManagerListener == null) {
            return;
        }
        iDeviceConnectionManagerListener.onDeviceProjectionRequested(supported, createConnectionInfo(request));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void deviceDetached(DmDevice device) {
        IDeviceConnectionManagerListener iDeviceConnectionManagerListener = this.mListener;
        if (iDeviceConnectionManagerListener == null) {
            return;
        }
        iDeviceConnectionManagerListener.onDeviceDetached(createConnectionInfo(device));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void deviceChanged(DmDevice device) {
        IDeviceConnectionManagerListener iDeviceConnectionManagerListener = this.mListener;
        if (iDeviceConnectionManagerListener == null) {
            return;
        }
        iDeviceConnectionManagerListener.onDeviceChanged(createConnectionInfo(device));
    }

    private ConnectionInfoBean createConnectionInfo(DmDevice device) {
        ConnectRequest connectRequest = new ConnectRequest();
        connectRequest.setDevice(createDevice(device));
        return new ConnectionInfoBean.Builder().setConnectRequest(connectRequest).build();
    }

    private ConnectionInfoBean createConnectionInfo(ConnectRequest request) {
        ConnectRequest connectRequest = new ConnectRequest();
        connectRequest.setDevice(createDevice(request.getDevice()));
        connectRequest.setConnectType(request.getConnectionType());
        connectRequest.setCapabilityType(request.getCapabilityType());
        return new ConnectionInfoBean.Builder().setConnectRequest(connectRequest).build();
    }

    private DmDevice createDevice(DmDevice device) {
        DmDevice dmDevice = new DmDevice(device.getDeviceUuid());
        dmDevice.setMacAddress(device.getMacAddress());
        dmDevice.setUsbSerialNumber(device.getUsbSerialNumber());
        dmDevice.setCarPlayProjectionState(device.getCarPlayProjectionState());
        dmDevice.setAndroidAutoProjectionState(device.getAndroidAutoProjectionState());
        dmDevice.setCarlifeProjectionState(device.getCarlifeProjectionState());
        dmDevice.setHicarProjectionState(device.getHicarProjectionState());
        return dmDevice;
    }
}
