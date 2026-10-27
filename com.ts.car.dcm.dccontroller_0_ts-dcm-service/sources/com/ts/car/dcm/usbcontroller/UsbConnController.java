package com.ts.car.dcm.usbcontroller;

import android.content.Context;
import android.hardware.usb.UsbDevice;
import android.os.HandlerThread;
import android.os.Message;
import android.util.Log;
import com.ts.car.dcm.common.controller.IControllerCallback;
import com.ts.car.dcm.common.controller.IUsbControllerCallback;
import com.ts.car.dcm.common.controller.UsbConnectionController;
import com.ts.car.dcm.common.data.LinkDevice;
import com.ts.car.dcm.common.data.LogEncrypt;

/* JADX INFO: loaded from: classes.dex */
public class UsbConnController extends UsbConnectionController {
    protected static final boolean DBG = true;
    protected static final String TAG = UsbConnController.class.getSimpleName();
    private IUsbControllerCallback mConnControllerCallback;
    private HandlerThread mHandlerThread;
    private UsbDeviceMonitor mUsbDeviceMonitor;
    private Object mControllerCallbackLock = new Object();
    private boolean mIsSendDeviceChanged = false;

    public UsbConnController() {
        if (this.mHandlerThread == null) {
            HandlerThread handlerThread = new HandlerThread("UsbHandlerThread");
            this.mHandlerThread = handlerThread;
            handlerThread.start();
        }
        this.mUsbDeviceMonitor = new UsbDeviceMonitor(this, this.mHandlerThread.getLooper());
    }

    @Override // com.ts.car.dcm.common.controller.ConnectionController
    public void init(Context context, int platformCapability) {
        this.mUsbDeviceMonitor.init(context, platformCapability);
    }

    @Override // com.ts.car.dcm.common.controller.ConnectionController
    public int getControllerType() {
        return 1;
    }

    @Override // com.ts.car.dcm.common.controller.ConnectionController
    public void addControllerListener(IControllerCallback listener) {
        synchronized (this.mControllerCallbackLock) {
            Log.e(TAG, "addControllerListener, start monitor.");
            this.mConnControllerCallback = (IUsbControllerCallback) listener;
            Message msg = this.mUsbDeviceMonitor.obtainMessage(0);
            this.mUsbDeviceMonitor.sendMessage(msg);
        }
    }

    @Override // com.ts.car.dcm.common.controller.ConnectionController
    public void removeControllerListener(IControllerCallback listener) {
        synchronized (this.mControllerCallbackLock) {
            if (this.mConnControllerCallback == ((IUsbControllerCallback) listener)) {
                this.mConnControllerCallback = null;
                Message msg = this.mUsbDeviceMonitor.obtainMessage(24);
                this.mUsbDeviceMonitor.sendMessage(msg);
            }
        }
    }

    @Override // com.ts.car.dcm.common.controller.ConnectionController
    public void acceptDevice(String str, boolean z) {
        Message messageObtainMessage = this.mUsbDeviceMonitor.obtainMessage(20);
        messageObtainMessage.arg1 = z ? 1 : 0;
        messageObtainMessage.obj = str;
        this.mUsbDeviceMonitor.sendMessage(messageObtainMessage);
    }

    @Override // com.ts.car.dcm.common.controller.UsbConnectionController
    public boolean connectUsbDevice(String devId) {
        Message msg = this.mUsbDeviceMonitor.obtainMessage(16);
        msg.obj = devId;
        this.mUsbDeviceMonitor.sendMessage(msg);
        return true;
    }

    @Override // com.ts.car.dcm.common.controller.UsbConnectionController
    public boolean disconnectUsbDevice(String devId) {
        Message msg = this.mUsbDeviceMonitor.obtainMessage(15);
        msg.obj = devId;
        this.mUsbDeviceMonitor.sendMessage(msg);
        return true;
    }

    @Override // com.ts.car.dcm.common.controller.UsbConnectionController
    public UsbDevice getUsbConnectedDevices(String devId) {
        return this.mUsbDeviceMonitor.getUsbDevice(devId);
    }

    @Override // com.ts.car.dcm.common.controller.UsbConnectionController
    public boolean doUsbRoleSwitch(String devId, int mode, int service) {
        Message msg = this.mUsbDeviceMonitor.obtainMessage(18);
        msg.obj = devId;
        msg.arg1 = service;
        msg.arg2 = mode;
        this.mUsbDeviceMonitor.sendMessage(msg);
        return true;
    }

    @Override // com.ts.car.dcm.common.controller.UsbConnectionController
    public boolean enableUsbAoA(String devId, int mode, int flag) {
        Message msg = this.mUsbDeviceMonitor.obtainMessage(19);
        msg.obj = devId;
        msg.arg1 = mode;
        msg.arg2 = flag;
        this.mUsbDeviceMonitor.sendMessage(msg);
        return true;
    }

    @Override // com.ts.car.dcm.common.controller.UsbConnectionController
    public boolean enableUsbAoA(String devId, String serialNumber, int mode, int flag) {
        Message msg = this.mUsbDeviceMonitor.obtainMessage(30);
        msg.obj = devId + " " + serialNumber;
        msg.arg1 = mode;
        msg.arg2 = flag;
        this.mUsbDeviceMonitor.sendMessage(msg);
        return true;
    }

    @Override // com.ts.car.dcm.common.controller.UsbConnectionController
    public boolean resetUsbBus(String devId) {
        Message msg = this.mUsbDeviceMonitor.obtainMessage(21);
        msg.obj = devId;
        this.mUsbDeviceMonitor.sendMessage(msg);
        return true;
    }

    @Override // com.ts.car.dcm.common.controller.UsbConnectionController
    public boolean removeUsbDevice(String devId) {
        Message msg = this.mUsbDeviceMonitor.obtainMessage(17);
        msg.obj = devId;
        this.mUsbDeviceMonitor.sendMessage(msg);
        return true;
    }

    @Override // com.ts.car.dcm.common.controller.UsbConnectionController
    public boolean deleteUsbConnection(String devId) {
        return this.mUsbDeviceMonitor.deleteUsbConnection(devId);
    }

    @Override // com.ts.car.dcm.common.controller.ConnectionController
    public void setDeviceCapability(String devId, int capability) {
        Message msg = this.mUsbDeviceMonitor.obtainMessage(22);
        msg.obj = devId;
        msg.arg1 = capability;
        this.mUsbDeviceMonitor.sendMessage(msg);
    }

    @Override // com.ts.car.dcm.common.controller.ConnectionController
    public void setCapability(int capability) {
        this.mIsSendDeviceChanged = true;
        this.mUsbDeviceMonitor.updateCapability(capability);
    }

    @Override // com.ts.car.dcm.common.controller.UsbConnectionController
    public void updateCarlifeSupportStatus(int deviceType, boolean flag) {
        this.mUsbDeviceMonitor.updateCarlifeSupportStatus(deviceType, flag);
    }

    public void notifyUsbDeviceAttach(LinkDevice linkDevice) {
        Log.d(TAG, "notifyUsbDeviceAttach, serial:" + LogEncrypt.IpAddressEncrypt(linkDevice.getId()));
        synchronized (this.mControllerCallbackLock) {
            if (this.mConnControllerCallback != null) {
                this.mConnControllerCallback.onDeviceAttach(linkDevice);
            } else {
                Log.e(TAG, "notifyUsbDeviceAttach, client not register.");
            }
        }
    }

    public void notifyUsbDeviceDetach(String serial, int conntype) {
        Log.d(TAG, "notifyUsbDeviceDetach, serial:" + LogEncrypt.IpAddressEncrypt(serial));
        synchronized (this.mControllerCallbackLock) {
            if (this.mConnControllerCallback != null) {
                this.mConnControllerCallback.onDeviceDetach(serial, conntype);
            } else {
                Log.e(TAG, "notifyUsbDeviceDetach, client not register.");
            }
        }
    }

    public void notifyUsbDeviceStatusChange(LinkDevice linkDevice, int reason) {
        Log.d(TAG, "notifyUsbDeviceStatusChange, serial:" + LogEncrypt.IpAddressEncrypt(linkDevice.getId()) + ", reason:" + reason + ", mIsSendDeviceChanged:" + this.mIsSendDeviceChanged);
        synchronized (this.mControllerCallbackLock) {
            if (this.mConnControllerCallback != null && this.mIsSendDeviceChanged) {
                this.mConnControllerCallback.onDeviceChanged(linkDevice, reason);
            } else {
                Log.e(TAG, "notifyUsbDeviceStatusChange, client not register.");
            }
        }
    }

    public void notifyDevRoleSwitchStarting(String serial, int mode) {
        Log.d(TAG, "notifyDevRoleSwitchStarting, serial:" + LogEncrypt.IpAddressEncrypt(serial) + ", mode:" + mode);
        synchronized (this.mControllerCallbackLock) {
            if (this.mConnControllerCallback != null) {
                this.mConnControllerCallback.onRoleSwitchStarting(serial, mode);
            } else {
                Log.e(TAG, "notifyDevRoleSwitchStarting, client not register.");
            }
        }
    }

    public void notifyDevRoleSwitchDone(LinkDevice linkDevice, int mode, boolean result) {
        Log.d(TAG, "notifyDevRoleSwitchDone, serial:" + LogEncrypt.IpAddressEncrypt(linkDevice.getId()) + ", mode:" + mode + ", result:" + result);
        synchronized (this.mControllerCallbackLock) {
            if (this.mConnControllerCallback != null) {
                this.mConnControllerCallback.onRoleSwitchFinished(linkDevice, mode, result);
            } else {
                Log.e(TAG, "notifyDevRoleSwitchDone, client not register.");
            }
        }
    }

    public void notifyDevResetStarting(String serial) {
        Log.d(TAG, "notifyDevResetStarting, serial:" + LogEncrypt.IpAddressEncrypt(serial));
        synchronized (this.mControllerCallbackLock) {
            if (this.mConnControllerCallback != null) {
                this.mConnControllerCallback.onResetUsbBusStarting(serial);
            } else {
                Log.e(TAG, "notifyDevResetStarting, client not register.");
            }
        }
    }

    public void notifyDevResetDone(String serial, boolean result) {
        Log.d(TAG, "notifyDevResetDone, serial:" + LogEncrypt.IpAddressEncrypt(serial) + ", result:" + result);
        synchronized (this.mControllerCallbackLock) {
            if (this.mConnControllerCallback != null) {
                this.mConnControllerCallback.onResetUsbFinished(serial, result);
            } else {
                Log.e(TAG, "notifyDevResetDone, client not register.");
            }
        }
    }

    public void notifyDevAoAStarting(String serial, int mode) {
        Log.d(TAG, "notifyDevAoAStarting, serial:" + LogEncrypt.IpAddressEncrypt(serial) + ", mode:" + mode);
        synchronized (this.mControllerCallbackLock) {
            if (this.mConnControllerCallback != null) {
                this.mConnControllerCallback.onUsbAoAStarting(serial, mode);
            } else {
                Log.e(TAG, "notifyDevAoAStarting, client not register.");
            }
        }
    }

    public void notifyDevAoAdone(LinkDevice linkDevice, int mode, boolean result) {
        Log.d(TAG, "notifyDevAoAdone, serial:" + LogEncrypt.IpAddressEncrypt(linkDevice.getId()) + ", mode:" + mode + ", result:" + result);
        synchronized (this.mControllerCallbackLock) {
            if (this.mConnControllerCallback != null) {
                this.mConnControllerCallback.onUsbAoAFinished(linkDevice, mode, result);
            } else {
                Log.e(TAG, "notifyDevAoAdone, client not register.");
            }
        }
    }

    public String getUsbConfig(String configName) {
        synchronized (this.mControllerCallbackLock) {
            if (this.mConnControllerCallback != null) {
                return this.mConnControllerCallback.onGetConfig(configName);
            }
            Log.e(TAG, "getUsbConfig, client not register.");
            return null;
        }
    }

    public void notifyAbnormalDeviceAttach(String deviceId, int abnormalType, int connType) {
        Log.d(TAG, "notifyAbnormalDeviceAttach, deviceId:" + LogEncrypt.IpAddressEncrypt(deviceId) + ", abnormalType:" + abnormalType + ", connType:" + connType);
        synchronized (this.mControllerCallbackLock) {
            if (this.mConnControllerCallback != null) {
                this.mConnControllerCallback.onAbnormalDeviceAttach(deviceId, abnormalType, connType);
            } else {
                Log.e(TAG, "notifyAbnormalDeviceAttach, client not register.");
            }
        }
    }

    public void notifyAbnormalDeviceDetach(String deviceId) {
        Log.d(TAG, "notifyAbnormalDeviceDetach, deviceId:" + LogEncrypt.IpAddressEncrypt(deviceId));
        synchronized (this.mControllerCallbackLock) {
            if (this.mConnControllerCallback != null) {
                this.mConnControllerCallback.onAbnormalDeviceDetach(deviceId);
            } else {
                Log.e(TAG, "notifyAbnormalDeviceDetach, client not register.");
            }
        }
    }
}
