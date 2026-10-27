package com.ts.carplay.lib.iap2manager;

import android.os.Looper;
import android.text.TextUtils;
import com.ts.carplay.common.util.LogEncrypt;
import com.ts.carplay.common.util.LogUtil;
import com.ts.carplay.lib.CarPlayManager;

/* JADX INFO: loaded from: classes.dex */
public class CarPlayOobManager {
    private static final int BYTE_VALUE_MASK = 255;
    private static final int SEND_OOB_ACCESSORY_DELAY = 1000;
    private static final int SUCCESS = 0;
    private static final String TAG = "CarPlayOobManager";
    private static final String UUID_IAP_ACCESSARY = "00000000-deca-fade-deca-deafdecacaff";
    private short mBtTransComId;
    private CarPlayManager mCarPlayManager;
    private String mOobMacAddress;

    public CarPlayOobManager(CarPlayManager manager, Looper looper) {
        LogUtil.debug(TAG, "CarPlayOobManager Constructor.");
        this.mCarPlayManager = manager;
    }

    public void setOobPairName(String devName) {
        LogUtil.info(TAG, "setOobPairName, devName=" + devName);
    }

    public int onStartBluetoothPairing(short btTransComId, byte[] macAddress) {
        if (macAddress == null || macAddress.length == 0) {
            LogUtil.info(TAG, "onStartBluetoothPairing already pair");
            return -1;
        }
        String address = macAddressByteToString(macAddress);
        LogUtil.debug(TAG, "onStartBluetoothPairing btTransComId: " + ((int) btTransComId) + ", macAddress: " + LogEncrypt.MacAddressEncrypt(address));
        int btPairingStatus = -1;
        CarPlayManager carPlayManager = this.mCarPlayManager;
        if (carPlayManager != null) {
            if (!carPlayManager.isBluetoothEnabled()) {
                if (this.mCarPlayManager.enableBluetooth()) {
                    LogUtil.info(TAG, "onStartBluetoothPairing enable bluetooth first");
                    this.mBtTransComId = btTransComId;
                    this.mOobMacAddress = address;
                    return 0;
                }
                sendBluetoothPairingStatus(address, false, "local open bluetooth failed");
                return -1;
            }
            if (!getLocalOobData()) {
                sendBluetoothPairingStatus(address, false, "get oob data failed");
            } else {
                this.mBtTransComId = btTransComId;
                this.mOobMacAddress = address;
                btPairingStatus = 0;
            }
        }
        LogUtil.debug(TAG, "onStartBluetoothPairing btPairingStatus: " + btPairingStatus);
        return btPairingStatus;
    }

    private String macAddressByteToString(byte[] macAddress) {
        StringBuffer btMacStr = new StringBuffer("");
        for (int i = 0; i < macAddress.length; i++) {
            int macV = macAddress[i] & 255;
            String hexMac = Integer.toHexString(macV);
            if (hexMac.length() < 2) {
                btMacStr.append(0);
            }
            btMacStr.append(hexMac);
            if (i != macAddress.length - 1) {
                btMacStr.append(":");
            }
        }
        return btMacStr.toString();
    }

    private boolean getLocalOobData() {
        return this.mCarPlayManager.getLocalOobData();
    }

    public void onReceiveOobData(boolean success) {
        if (success) {
            onSendBluetoothPairingAccessoryInformation();
        } else {
            sendBluetoothPairingStatus(this.mOobMacAddress, false, "get oob data null");
        }
    }

    private int onSendBluetoothPairingAccessoryInformation() {
        CarPlayManager carPlayManager = this.mCarPlayManager;
        int result = carPlayManager.onSendBluetoothPairingAccessoryInformation(16, this.mBtTransComId, carPlayManager.getPairingDataP192(), this.mCarPlayManager.getPairingDataP256());
        LogUtil.debug(TAG, "onSendBluetoothPairingAccessoryInformation btMacStr: " + LogEncrypt.MacAddressEncrypt(this.mOobMacAddress) + " result:" + result);
        return result;
    }

    public void handleBluetoothEnabled(boolean enable) {
        LogUtil.debug(TAG, "handleBluetoothEnabled, enable:" + enable);
        if (this.mBtTransComId == -1 || TextUtils.isEmpty(this.mOobMacAddress)) {
            LogUtil.debug(TAG, "handleBluetoothEnabled, no oob pair is waiting");
        } else if (!enable) {
            LogUtil.debug(TAG, "handleBluetoothEnabled, bluetooth has closed during oob paire.");
            sendBluetoothPairingStatus(this.mOobMacAddress, false, "bluetooth has closed during oob pair");
        }
    }

    public void onSdpServerUuidChanged(String uuid) {
        LogUtil.debug(TAG, "onSdpServerUuidChanged, uuid:" + LogEncrypt.MacAddressEncrypt(uuid));
        if (uuid != null && uuid.equals(UUID_IAP_ACCESSARY)) {
            if (this.mBtTransComId == -1 || TextUtils.isEmpty(this.mOobMacAddress)) {
                LogUtil.debug(TAG, "onSdpServerUuidChanged, no oob pair is waiting");
            }
        }
    }

    public void sendBluetoothPairingStatus(String address, boolean isSuccess, String reason) {
        LogUtil.debug(TAG, "sendBluetoothPairingStatus address:" + LogEncrypt.MacAddressEncrypt(address) + " isSuccess:" + isSuccess + " reason:" + reason);
        String str = this.mOobMacAddress;
        if (str == null || !str.equalsIgnoreCase(address)) {
            return;
        }
        int result = this.mCarPlayManager.onSendBluetoothPairingStatus(16, this.mBtTransComId, isSuccess, reason);
        LogUtil.debug(TAG, "sendBluetoothPairingStatus result:" + result);
    }

    public String getBluetoothOobMacAddress() {
        return this.mOobMacAddress;
    }

    public int onStopBluetoothPairing(int btTransComId, int state) {
        LogUtil.info(TAG, "onStopOobBtPairing btTransComId: " + btTransComId + ", state: " + state);
        this.mBtTransComId = (short) -1;
        this.mOobMacAddress = null;
        return 0;
    }
}
