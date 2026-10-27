package com.ts.car.dcm.wirelesscontroller;

import android.content.Context;
import android.net.wifi.SoftApConfiguration;
import android.util.Log;
import com.ts.car.dcm.common.controller.IControllerCallback;
import com.ts.car.dcm.common.controller.IWirelessControllerCallback;
import com.ts.car.dcm.common.controller.WirelessConnectionController;
import com.ts.car.dcm.common.data.LinkDevice;
import com.ts.car.dcm.common.data.LogEncrypt;
import ts.car.dcm.common.data.BtOobInfo;
import ts.car.dcm.common.data.WifiApConfiguration;

/* JADX INFO: loaded from: classes.dex */
public class WirelessConnController extends WirelessConnectionController {
    private static final String TAG = WirelessConnController.class.getSimpleName();
    private IWirelessControllerCallback mConnControllerCallback;
    private final Object mConnControllerCallbackLock = new Object();
    private boolean mIsSendDeviceChanged = false;
    private WirelessDeviceManager mDeviceManager = new WirelessDeviceManager(this);

    @Override // com.ts.car.dcm.common.controller.ConnectionController
    public int getControllerType() {
        return 0;
    }

    @Override // com.ts.car.dcm.common.controller.ConnectionController
    public synchronized void init(Context context, int capability) {
        Log.d(TAG, "WirelessConnController init.");
        this.mDeviceManager.init(context, capability);
    }

    @Override // com.ts.car.dcm.common.controller.ConnectionController
    public void addControllerListener(IControllerCallback listener) {
        Log.d(TAG, "addConnControllerListener.");
        synchronized (this.mConnControllerCallbackLock) {
            this.mConnControllerCallback = (IWirelessControllerCallback) listener;
        }
        this.mDeviceManager.startController();
    }

    @Override // com.ts.car.dcm.common.controller.ConnectionController
    public void removeControllerListener(IControllerCallback listener) {
        Log.d(TAG, "removeConnControllerListener.");
        synchronized (this.mConnControllerCallbackLock) {
            this.mConnControllerCallback = null;
        }
        this.mDeviceManager.stopController();
    }

    @Override // com.ts.car.dcm.common.controller.ConnectionController
    public void setCapability(int capabilitys) {
        this.mIsSendDeviceChanged = true;
        this.mDeviceManager.setCapability(capabilitys);
    }

    @Override // com.ts.car.dcm.common.controller.ConnectionController
    public void acceptDevice(String devid, boolean success) {
        this.mDeviceManager.acceptDevice(devid, success);
    }

    @Override // com.ts.car.dcm.common.controller.ConnectionController
    public void setDeviceCapability(String devid, int capability) {
        this.mDeviceManager.setDeviceCapability(devid, capability);
    }

    @Override // com.ts.car.dcm.common.controller.WirelessConnectionController
    public boolean deleteBtDevice(String address) {
        return this.mDeviceManager.deleteBtDevice(address);
    }

    @Override // com.ts.car.dcm.common.controller.WirelessConnectionController
    public boolean deleteBtConnection(String address) {
        return this.mDeviceManager.deleteBtConnection(address);
    }

    @Override // com.ts.car.dcm.common.controller.WirelessConnectionController
    public boolean disconnectBtDevice(String address) {
        return this.mDeviceManager.disconnectBtDevice(address);
    }

    @Override // com.ts.car.dcm.common.controller.WirelessConnectionController
    public boolean connectBtDevices(String address) {
        return this.mDeviceManager.connectBtDevices(address);
    }

    @Override // com.ts.car.dcm.common.controller.WirelessConnectionController
    public boolean disconnectOtherBtDevices(String devId) {
        return this.mDeviceManager.disconnectOtherBtDevices(devId);
    }

    @Override // com.ts.car.dcm.common.controller.WirelessConnectionController
    public boolean connectBtProfile(String mac, int profile) {
        return this.mDeviceManager.connectBtProfile(mac, profile);
    }

    @Override // com.ts.car.dcm.common.controller.WirelessConnectionController
    public boolean disconnectBtProfile(String mac, int profile) {
        return this.mDeviceManager.disconnectBtProfile(mac, profile);
    }

    @Override // com.ts.car.dcm.common.controller.WirelessConnectionController
    public boolean isBluetoothEnabled() {
        return this.mDeviceManager.isBluetoothEnabled();
    }

    @Override // com.ts.car.dcm.common.controller.WirelessConnectionController
    public boolean enableBluetooth(boolean flag) {
        Log.d(TAG, "enableBluetooth:" + flag);
        if (flag) {
            boolean result = this.mDeviceManager.enableBluetooth();
            return result;
        }
        boolean result2 = this.mDeviceManager.disableBluetooth();
        return result2;
    }

    @Override // com.ts.car.dcm.common.controller.WirelessConnectionController
    public BtOobInfo getBtOobInfo() {
        return this.mDeviceManager.getBtOobInfo();
    }

    @Override // com.ts.car.dcm.common.controller.WirelessConnectionController
    public boolean setBtOobInfo(String name, String linkkey, String mac, int linkKeyType) {
        return this.mDeviceManager.setBtOobInfo(name, linkkey, mac, linkKeyType);
    }

    @Override // com.ts.car.dcm.common.controller.WirelessConnectionController
    public boolean enableWifiAp(boolean enable, WifiApConfiguration config) {
        return this.mDeviceManager.enableWifiAp(enable, config);
    }

    @Override // com.ts.car.dcm.common.controller.WirelessConnectionController
    public boolean removeWifiDevice(String mac) {
        return this.mDeviceManager.deleteWifiDevice(mac);
    }

    @Override // com.ts.car.dcm.common.controller.WirelessConnectionController
    public boolean reattachWifiDevice(String mac) {
        return this.mDeviceManager.reattachWifiDevice(mac);
    }

    @Override // com.ts.car.dcm.common.controller.WirelessConnectionController
    public LinkDevice getWifiLinkDeviceByIpaddr(String ipAddr) {
        return this.mDeviceManager.getWifiLinkDeviceByIpaddr(ipAddr);
    }

    @Override // com.ts.car.dcm.common.controller.WirelessConnectionController
    public boolean requestBluetoothPairing(String mac) {
        return this.mDeviceManager.requestBluetoothPairing(mac);
    }

    public void notifyWirelessDeviceAttach(LinkDevice linkDevice) {
        synchronized (this.mConnControllerCallbackLock) {
            if (this.mConnControllerCallback != null) {
                this.mConnControllerCallback.onDeviceAttach(linkDevice);
            } else {
                Log.e(TAG, "notifyWirelessDeviceAttach, Dccontroller do not register callback.");
            }
        }
    }

    public void notifyWirelessDeviceDetach(String address, int conntype) {
        synchronized (this.mConnControllerCallbackLock) {
            if (this.mConnControllerCallback != null) {
                this.mConnControllerCallback.onDeviceDetach(address, conntype);
            } else {
                Log.e(TAG, "notifyWirelessDeviceDetach, Dccontroller do not register callback.");
            }
        }
    }

    public void notifyWirelssDeviceChange(LinkDevice linkDevice, int reason) {
        Log.d(TAG, "notifyWirelssDeviceChange, devId:" + LogEncrypt.IpAddressEncrypt(linkDevice.getId()) + ", reason:" + reason + ", mIsSendDeviceChanged:" + this.mIsSendDeviceChanged);
        synchronized (this.mConnControllerCallbackLock) {
            if (this.mConnControllerCallback != null && this.mIsSendDeviceChanged) {
                this.mConnControllerCallback.onDeviceChanged(linkDevice, reason);
            } else {
                Log.e(TAG, "notifyWirelssDeviceChange, Dccontroller do not register callback.");
            }
        }
    }

    public void notifyBtDeviceDeleted(String devId, int status) {
        synchronized (this.mConnControllerCallbackLock) {
            if (this.mConnControllerCallback != null) {
                this.mConnControllerCallback.onBtDeleted(devId, status);
            } else {
                Log.e(TAG, "notifyBtDeviceDeleted, Dccontroller do not register callback.");
            }
        }
    }

    public void notifyBtDevcieDisconnected(String device, int status) {
        synchronized (this.mConnControllerCallbackLock) {
            if (this.mConnControllerCallback != null) {
                this.mConnControllerCallback.onBtDisconnected(device, status);
            } else {
                Log.e(TAG, "notifyBtDevcieDisconnected, Dccontroller do not register callback.");
            }
        }
    }

    public void notifyBtDevcieConnected(String device, int status) {
        synchronized (this.mConnControllerCallbackLock) {
            if (this.mConnControllerCallback != null) {
                this.mConnControllerCallback.onBtConnected(device, status);
            } else {
                Log.e(TAG, "notifyBtDevcieConnected, Dccontroller do not register callback.");
            }
        }
    }

    public void notifyOtherBtDeviceDisconnected(String address, int status) {
        synchronized (this.mConnControllerCallbackLock) {
            if (this.mConnControllerCallback != null) {
                this.mConnControllerCallback.onDisconnectedOtherBtDevices(address, status);
            } else {
                Log.e(TAG, "notifyOtherBtDeviceDisconnected, Dccontroller do not register callback.");
            }
        }
    }

    public void notifyBtProfileChange(String devid, int profile, int connstate) {
        synchronized (this.mConnControllerCallbackLock) {
            if (this.mConnControllerCallback != null) {
                this.mConnControllerCallback.onBtProfileChanged(devid, profile, connstate);
            } else {
                Log.e(TAG, "notifyBtProfileChange, Dccontroller do not register callback.");
            }
        }
    }

    public void notifyBtCallStateChange(String devid, boolean inCall) {
        synchronized (this.mConnControllerCallbackLock) {
            if (this.mConnControllerCallback != null) {
                this.mConnControllerCallback.onBtCallStateChanged(devid, inCall);
            } else {
                Log.e(TAG, "notifyBtCallStateChange, Dccontroller do not register callback.");
            }
        }
    }

    public void notifyBtAudioStateChange(String devid, boolean inAudioPlay) {
        synchronized (this.mConnControllerCallbackLock) {
            if (this.mConnControllerCallback != null) {
                this.mConnControllerCallback.onBtAudioStateChanged(devid, inAudioPlay);
            } else {
                Log.e(TAG, "notifyBtAudioStateChange, Dccontroller do not register callback.");
            }
        }
    }

    public void notifyBtStateChange(int state) {
        synchronized (this.mConnControllerCallbackLock) {
            if (this.mConnControllerCallback != null) {
                this.mConnControllerCallback.onBtStateChanged(state);
            } else {
                Log.e(TAG, "notifyBtStateChange, Dccontroller do not register callback.");
            }
        }
    }

    public void notifyBtOobPairComplete(String devId, int status) {
        synchronized (this.mConnControllerCallbackLock) {
            if (this.mConnControllerCallback != null) {
                this.mConnControllerCallback.notifyBtOobPairComplete(devId, status);
            } else {
                Log.e(TAG, "notifyBtOobPairComplete, Dccontroller do not register callback.");
            }
        }
    }

    public void notifyBtPairingChanged(String devId, int status) {
        synchronized (this.mConnControllerCallbackLock) {
            if (this.mConnControllerCallback != null) {
                this.mConnControllerCallback.onBtPairStateChanged(devId, status);
            } else {
                Log.e(TAG, "notifyBtPairingChanged, Dccontroller do not register callback.");
            }
        }
    }

    public void notifyWifiApInfo(SoftApConfiguration config, int status) {
        synchronized (this.mConnControllerCallbackLock) {
            if (this.mConnControllerCallback != null) {
                this.mConnControllerCallback.onWifiApInfo(config, status);
            } else {
                Log.e(TAG, "notifyWifiApInfo, Dccontroller do not register callback.");
            }
        }
    }

    public void notifyWifiConnectStatus(String mac, int status) {
        synchronized (this.mConnControllerCallbackLock) {
            if (this.mConnControllerCallback != null) {
                this.mConnControllerCallback.onWifiConnectStatus(mac, status);
            } else {
                Log.e(TAG, "notifyWifiConnectStatus, Dccontroller do not register callback.");
            }
        }
    }

    public String getWirelessConfig(String key) {
        synchronized (this.mConnControllerCallbackLock) {
            if (this.mConnControllerCallback != null) {
                return this.mConnControllerCallback.onGetConfig(key);
            }
            Log.e(TAG, "getWirelessConfig, Dccontroller do not register callback.");
            return null;
        }
    }

    public void checkWirelessCarplayCapability(String devId) {
        synchronized (this.mConnControllerCallbackLock) {
            if (this.mConnControllerCallback != null) {
                this.mConnControllerCallback.onCheckCpwlCapability(devId);
            } else {
                Log.e(TAG, "checkWirelessCarplayCapability, Dccontroller do not register callback.");
            }
        }
    }
}
