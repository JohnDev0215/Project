package com.ts.carplay.lib.connect;

import com.ts.android.carplay.CarPlayClient;
import com.ts.carplay.common.util.LogUtil;
import com.ts.carplay.lib.CarPlayManager;
import com.ts.carplay.lib.transport.CarPlayTransport;
import com.ts.carplay.lib.ts.CarPlayStack;
import com.ts.carplay.linkadapter.device.data.LinkDevice;
import com.ts.carplay.linkadapter.iap.data.WiredAttributes;
import com.ts.carplay.linkadapter.iap.data.WirelessAttributes;

/* JADX INFO: loaded from: classes.dex */
public class CarPlayConnect {
    public static final int BLUETOOTH_SESSION_INFO_COMPLETED = 16;
    public static final int CHECK_CARPLAY_START_SESSION = 24;
    public static final int CONNECT = 1;
    public static final int CONNECT_NO_IAP_STATE = 20;
    public static final int DEVICE_CONNECT = 7;
    public static final int DEVICE_DISCONNECT = 8;
    public static final int DISABLE_BLUETOOTH = 12;
    public static final int DISCONNECT = 2;
    public static final int EXIT_CARPLAY = 13;
    public static final int IAP2_AUTH_FAILED = 6;
    public static final int IAP2_AUTH_SUCCESS = 5;
    public static final int IAP_SESSION_SEND_NOWPLAYING = 16;
    public static final int IAP_SESSION_TYPE_BT = 2;
    public static final int IAP_SESSION_TYPE_UNKNOWN = 0;
    public static final int IAP_SESSION_TYPE_USB = 1;
    public static final int IAP_SESSION_TYPE_USB_DEVICE = 64;
    public static final int IAP_SESSION_TYPE_USB_GET_NAME = 32;
    public static final int IAP_SESSION_TYPE_USB_SAME = 8;
    public static final int IAP_SESSION_TYPE_WIFI = 4;
    public static final int REQ_UPDATE_WIRELESS_CAPABILITY = 14;
    public static final int ROLESEITCH_FAILED = 4;
    public static final int ROLESWITCH_SUCCESS = 3;
    public static final int SEND_WIFI_CONFIG_INFO = 23;
    public static final int SESSION_CLOSED = 11;
    public static final int SESSION_FAILED = 10;
    public static final int SESSION_STARTED = 9;
    public static final int SLEEP_FOR_RECONNECT = 100;
    public static final int START_SESSION = 17;
    private static final String TAG = "CarPlayConnect";
    public static final int WIRED_ATTRIBUTES_CHANGE = 21;
    public static final int WIRELESS_ATTRIBUTES_CHANGE = 22;
    public static final int WIRELESS_CAPABILITY_FALSE = 19;
    public static final int WIRELESS_CAPABILITY_TRUE = 18;
    static CarPlayConnect sCarPlayConnect = null;
    private CarPlayManager mCarPlayManager;
    CarPlayTransport mCarPlayTransport;
    private CarPlaySameDevConnect mSameDeviceConnect;
    private CarPlayUsbConnect mUsbConnect;
    private CarPlayUsbDeviceConnect mUsbDeviceConnect;
    private CarPlayWiFiCapUpdate mWiFiCapUpdate;
    private CarPlayWiFiConnect mWiFiConnect;

    public CarPlayConnect(CarPlayManager manager) {
        if (sCarPlayConnect != null) {
            throw new IllegalStateException("sCarPlayConnect was already created, use getInstance instead.");
        }
        CarPlayTransport carPlayTransport = new CarPlayTransport();
        this.mCarPlayTransport = carPlayTransport;
        carPlayTransport.init(manager);
        this.mUsbConnect = new CarPlayUsbConnect(manager);
        CarPlayWiFiCapUpdate carPlayWiFiCapUpdate = new CarPlayWiFiCapUpdate(manager);
        this.mWiFiCapUpdate = carPlayWiFiCapUpdate;
        this.mWiFiConnect = new CarPlayWiFiConnect(manager, carPlayWiFiCapUpdate);
        this.mSameDeviceConnect = new CarPlaySameDevConnect(manager);
        this.mUsbDeviceConnect = new CarPlayUsbDeviceConnect(manager);
        this.mCarPlayManager = manager;
        sCarPlayConnect = this;
    }

    public static CarPlayConnect getInstance() {
        CarPlayConnect carPlayConnect = sCarPlayConnect;
        if (carPlayConnect == null) {
            throw new IllegalStateException("Attempted to retrieve a CarPlayConnect before construct was called.");
        }
        return carPlayConnect;
    }

    public boolean isConnected() {
        return this.mUsbConnect.isConnected() || this.mWiFiConnect.isConnected();
    }

    public boolean isUsbConnected() {
        if (this.mUsbConnect.isConnected()) {
            return true;
        }
        return false;
    }

    public boolean isWiFiConnected() {
        if (this.mWiFiConnect.isConnected()) {
            return true;
        }
        return false;
    }

    public boolean isWiFiCapActived() {
        if (this.mWiFiCapUpdate.isActived()) {
            return true;
        }
        return false;
    }

    public boolean isHasWirelessConn() {
        return this.mWiFiConnect.isHasWirelessConn();
    }

    public boolean isWiFiActived() {
        if (this.mWiFiConnect.isActived()) {
            return true;
        }
        return false;
    }

    public int connect(LinkDevice device) {
        LogUtil.info(TAG, "connect " + device);
        if (device.getLinkType() == 1) {
            this.mWiFiCapUpdate.disconnect(device);
            this.mWiFiConnect.disconnect(device);
            return this.mUsbConnect.connect(device);
        }
        CarPlayWiFiConnect carPlayWiFiConnect = this.mWiFiConnect;
        if (carPlayWiFiConnect != null) {
            if (carPlayWiFiConnect.isActived()) {
                LogUtil.warning(TAG, "connect wireless CarPlay has actived");
                return 0;
            }
            LogUtil.debug(TAG, "connect wireless and no need to reconnect iap session" + device);
            return this.mWiFiConnect.connect_with_no_iap(device);
        }
        LogUtil.warning(TAG, "connect mWiFiConnect is null.");
        return -1;
    }

    public void sameDeviceConnect(LinkDevice device, int reason) {
        LogUtil.info(TAG, "sameDeviceConnect " + device);
        if (this.mWiFiConnect.isConnected()) {
            if (reason == 1) {
                this.mSameDeviceConnect.connect(device);
            } else {
                this.mSameDeviceConnect.disconnect(device);
            }
        }
    }

    public void usbDeviceModeConnect(LinkDevice device) {
        LogUtil.info(TAG, "usbDeviceConnect " + device);
        this.mUsbDeviceConnect.connect(device);
    }

    public void usbDeviceModeDisconnect(LinkDevice device) {
        LogUtil.info(TAG, "usbDeviceConnect " + device);
        this.mUsbDeviceConnect.disconnect(device);
    }

    public int getCarplayCapbility(LinkDevice device, int reason) {
        LogUtil.info(TAG, "getCarplayCapbility " + device + "reason=" + reason);
        this.mWiFiCapUpdate.getCarplayCapbility(device, reason);
        return 0;
    }

    public int disconnect(LinkDevice device) {
        if (this.mUsbConnect.isActived()) {
            this.mSameDeviceConnect.disconnectIap();
            return this.mUsbConnect.disconnect(device);
        }
        if (this.mWiFiConnect.isActived()) {
            this.mWiFiConnect.disconnect(device);
            this.mWiFiCapUpdate.disconnect(device);
            return disconnectIapSameDevice();
        }
        return 0;
    }

    public void disableBluetooth() {
        LogUtil.info(TAG, "disableBluetooth");
        if (this.mWiFiConnect.isActived()) {
            this.mWiFiConnect.disableBluetooth();
        }
    }

    public void iap2AuthResult(int transportMode, int resultCode) {
        LogUtil.info(TAG, "iap2AuthResult transportMode = " + transportMode + " resultCode = " + resultCode);
        if (this.mSameDeviceConnect.isActived()) {
            LogUtil.info(TAG, "SameDeviceConnect started iap auth");
            this.mSameDeviceConnect.iap2AuthResult(resultCode);
            return;
        }
        if (this.mUsbDeviceConnect.isActived()) {
            LogUtil.info(TAG, "UsbDeviceConnect started iap auth");
            this.mUsbDeviceConnect.iap2AuthResult(resultCode);
            return;
        }
        if (this.mUsbConnect.isActived() && transportMode == 1) {
            this.mUsbConnect.iap2AuthResult(resultCode);
            return;
        }
        if (this.mWiFiConnect.isActived() && transportMode == 2) {
            this.mWiFiConnect.iap2AuthResult(resultCode);
            return;
        }
        if (this.mWiFiConnect.isActived() && transportMode == 4) {
            this.mWiFiConnect.iap2AuthResult(resultCode);
        } else if (this.mWiFiCapUpdate.isActived() && transportMode == 2) {
            this.mWiFiCapUpdate.iap2AuthResult(resultCode);
        }
    }

    public void onRoleSwitchFinished(LinkDevice device, boolean success) {
        if (this.mUsbConnect.isActived()) {
            this.mUsbConnect.onRoleSwitchFinished(device, success);
        } else {
            this.mSameDeviceConnect.onRoleSwitchFinished(device, success);
        }
    }

    public void updateSerialNumber(int devHandle, String serialNumber) {
        if (this.mWiFiConnect.isWirelessHandle(devHandle)) {
            this.mWiFiConnect.updateSerialNumber(serialNumber);
            this.mCarPlayManager.updateSerialNumberExternal(serialNumber, this.mWiFiConnect.getLinkDevice());
        } else {
            LogUtil.info(TAG, "This is not for wirelss connecting, do not notify to upper layer");
        }
    }

    public void onSessionEstablished(boolean needShow) {
        if (this.mUsbConnect.isActived()) {
            this.mUsbConnect.onSessionEstablished(needShow);
        } else if (this.mWiFiConnect.isActived()) {
            this.mWiFiConnect.onSessionEstablished(needShow);
        } else if (CarPlayStack.getInstance().getCarPlayClient() != null) {
            CarPlayStack.getInstance().stopCarplayClient();
        }
    }

    public void checkCarPlayStartSession() {
        this.mWiFiConnect.checkCarPlayStartSession();
    }

    public LinkDevice getLinkDevice() {
        LogUtil.info(TAG, "getLinkDevice ");
        if (this.mSameDeviceConnect.isActived()) {
            return this.mSameDeviceConnect.getLinkDevice();
        }
        if (this.mWiFiConnect.isActived()) {
            return this.mWiFiConnect.getLinkDevice();
        }
        if (this.mUsbConnect.isActived()) {
            return this.mUsbConnect.getLinkDevice();
        }
        if (this.mWiFiCapUpdate.isActived()) {
            return this.mWiFiCapUpdate.getLinkDevice();
        }
        return null;
    }

    public LinkDevice getWiFiConnectLinkDevice() {
        if (this.mWiFiConnect.isActived() && !this.mWiFiConnect.isConnected()) {
            return this.mWiFiConnect.getLinkDevice();
        }
        return null;
    }

    public void onSessionEstablishFailed() {
        if (this.mUsbConnect.isActived()) {
            this.mSameDeviceConnect.disconnectIap();
            this.mUsbConnect.onSessionEstablishFailed();
        } else if (this.mWiFiConnect.isActived()) {
            this.mWiFiConnect.onSessionEstablishFailed();
        }
    }

    public void onSessionClosed() {
        LogUtil.info(TAG, "SESSION_CLOSED goto disconnect");
        if (this.mUsbConnect.isActived()) {
            LogUtil.info(TAG, "USB SESSION_CLOSED goto disconnect");
            this.mSameDeviceConnect.disconnectIap();
            this.mUsbConnect.onSessionClosed();
        } else if (this.mWiFiConnect.isActived()) {
            LogUtil.info(TAG, "WIFI ESSION_CLOSED goto disconnect");
            disconnectIapSameDevice();
            this.mWiFiConnect.onSessionClosed();
        }
    }

    public void onCarPlayClientConnected(CarPlayClient client) {
        LogUtil.info(TAG, "onCarPlayClientConnected, client =" + client);
        if (this.mUsbConnect.isActived()) {
            this.mUsbConnect.onUsbClientConnected(client);
        } else if (this.mWiFiConnect.isActived()) {
            this.mWiFiConnect.onWifiClientConnected(client);
        }
    }

    public void onCarPlayCapbilityUpdate(boolean flag) {
        LogUtil.info(TAG, "onCarPlayCapbilityUpdate");
        this.mWiFiCapUpdate.onCarPlayCapbilityUpdate(flag);
    }

    public boolean onSppRetryConnected(String btAddrMac) {
        return this.mWiFiCapUpdate.onSppRetryConnected(btAddrMac);
    }

    public void onSessionDestroy(int clientType) {
        if (clientType == 18) {
            this.mWiFiCapUpdate.onSessionDestroy();
            this.mWiFiConnect.onSessionDestroy();
        }
    }

    public void closeRfSocketConnected() {
        LogUtil.debug(TAG, "closeRfSocketConnected");
        this.mWiFiCapUpdate.closeRfSocketConnected();
    }

    public void onRfCommDisconnected(String rfcommMac) {
        this.mWiFiCapUpdate.onRfCommDisconnected(rfcommMac);
    }

    public void checkUsbDeviceName(LinkDevice device) {
        this.mSameDeviceConnect.checkUsbDeviceName(device);
    }

    public void disconnectIapSession(String devId) {
        this.mSameDeviceConnect.disconnectIap();
    }

    public int disconnectIapSameDevice() {
        this.mSameDeviceConnect.disconnectIapSameDevice();
        return 0;
    }

    public boolean getIapGetNameFlg() {
        return this.mSameDeviceConnect.getIapGetNameFlg();
    }

    public void closeRfCommConnection() {
        this.mWiFiCapUpdate.closeRfCommConnection();
    }

    public boolean closeRfCommConnectionByMac(String mac) {
        return this.mWiFiCapUpdate.closeRfCommConnectionByMac(mac);
    }

    public void onWiredAttributesChanged(WiredAttributes wired) {
        this.mUsbConnect.onWiredAttributesChanged(wired);
    }

    public void onWirelessAttributesChanged(WirelessAttributes wireless) {
        this.mWiFiConnect.onWirelessAttributesChanged(wireless);
    }

    public void onRequestAccessoryWiFiConfigurationInformation(int clientType) {
        if (clientType == 18) {
            this.mWiFiConnect.onRequestAccessoryWiFiConfigurationInformation();
        }
    }
}
