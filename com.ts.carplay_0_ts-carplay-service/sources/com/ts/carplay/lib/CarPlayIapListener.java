package com.ts.carplay.lib;

import com.ts.carplay.common.util.LogEncrypt;
import com.ts.carplay.common.util.LogUtil;
import com.ts.carplay.lib.connect.CarPlayConnect;
import com.ts.carplay.lib.connect.CarPlayIapSessionManager;
import com.ts.carplay.lib.iap2manager.CarPlayLocationDataManager;
import com.ts.carplay.lib.iap2manager.CarPlayMediaDataManager;
import com.ts.carplay.lib.iap2manager.CarPlayOobManager;
import com.ts.carplay.linkadapter.iap.data.CommunicationsUpdate;
import com.ts.carplay.linkadapter.iap.data.IapSessionStatus;
import com.ts.carplay.linkadapter.iap.data.MediaLibraryUpdate;
import com.ts.carplay.linkadapter.iap.data.PowerUpdate;
import java.util.Locale;

/* JADX INFO: loaded from: classes.dex */
public class CarPlayIapListener {
    private static final String TAG = "CarPlayIapListener";
    private CarPlayManager mCarPlayManager;
    private CarPlayMediaDataManager mCarPlayMediaDataManager;
    private CarPlayLocationDataManager mCarplayLocationDm;
    private int mIapSessionType;
    private CarPlayOobManager mOobManager;
    private String mDeviceId = "";
    private CarPlayConnect mCarPlayConnect = CarPlayConnect.getInstance();

    public CarPlayIapListener(CarPlayManager manager, int iapSessionType) {
        this.mOobManager = null;
        this.mIapSessionType = -1;
        this.mCarPlayManager = manager;
        this.mOobManager = manager.getOobManager();
        this.mCarPlayMediaDataManager = this.mCarPlayManager.getCarPlayMediaDataManager();
        this.mCarplayLocationDm = this.mCarPlayManager.getCarPlayLocationDataManager();
        this.mIapSessionType = iapSessionType;
    }

    public void setDeviceId(String deviceId) {
        LogUtil.debug(TAG, "setDeviceId deviceId = " + LogEncrypt.MacAddressEncrypt(deviceId));
        this.mDeviceId = deviceId;
    }

    public int onIapSessionStatusChanged(IapSessionStatus status) {
        LogUtil.info(TAG, "onIapSessionStatusChanged status = " + status);
        if (status != IapSessionStatus.IapSessionIapVersion1 && status != IapSessionStatus.IapSessionIapVersion2) {
            if (status == IapSessionStatus.IapSessionAuthenticated) {
                CarPlayIapSessionManager.getInstance().notifyIap2Result(this.mIapSessionType, 0);
            } else if (status == IapSessionStatus.IapSessionAuthenticationFailed) {
                CarPlayIapSessionManager.getInstance().notifyIap2Result(this.mIapSessionType, 1);
            } else if (status == IapSessionStatus.IapSessionConnected) {
                CarPlayIapSessionManager.getInstance().notifyIap2Result(this.mIapSessionType, 0);
            } else if (status == IapSessionStatus.IapSessionConnectionFailed) {
                CarPlayIapSessionManager.getInstance().notifyIap2Result(this.mIapSessionType, 1);
            } else if (status != IapSessionStatus.IapSessionDisconnecting) {
                if (status == IapSessionStatus.IapSessionDisconnected) {
                    CarPlayIapSessionManager.getInstance().notifyIap2Destory(this.mIapSessionType);
                } else if (status != IapSessionStatus.IapSessionDown) {
                    IapSessionStatus iapSessionStatus = IapSessionStatus.IapSessionReset;
                }
            }
        }
        return 0;
    }

    public int onDeviceInformationUpdate(String deviceName) {
        LogUtil.debug(TAG, "onDeviceInformationUpdate mDeviceId:" + LogEncrypt.MacAddressEncrypt(this.mDeviceId) + " deviceName:" + deviceName + " iapType:" + this.mIapSessionType);
        if (this.mCarPlayConnect.getIapGetNameFlg()) {
            this.mCarPlayManager.onUsbDeviceNameUpdate(this.mDeviceId, deviceName);
            return 0;
        }
        if (this.mIapSessionType == 4) {
            LogUtil.debug(TAG, "do not update name in iap over wifi");
            return 0;
        }
        this.mCarPlayManager.onDeviceFriendlyNameUpdate(this.mDeviceId, deviceName);
        return 0;
    }

    public int onDeviceUuidUpdate(String uuid) {
        LogUtil.info(TAG, "onDeviceUuidUpdate uuid = " + LogEncrypt.MacAddressEncrypt(uuid));
        return 0;
    }

    public int onWirelessCarPlayUpdate(boolean wirelessCarPlayAvailable) {
        LogUtil.info(TAG, "onWirelessCarPlayUpdate wirelessCpAva = " + wirelessCarPlayAvailable);
        CarPlayConnect.getInstance().onCarPlayCapbilityUpdate(wirelessCarPlayAvailable);
        LogUtil.info(TAG, "onWirelessCarPlayUpdate finish ");
        return 0;
    }

    public int onDeviceTransportIdentifierNotification(String btTransportIdentifier, String usbTransportIdentifier) {
        LogUtil.debug(TAG, "onDeviceTransportIdentifierNotification btTransportID = " + LogEncrypt.MacAddressEncrypt(btTransportIdentifier) + ", usbTransportID = " + usbTransportIdentifier + ", mIapSessionType = " + this.mIapSessionType);
        if (btTransportIdentifier != null && usbTransportIdentifier != null) {
            String btMac = btTransportIdentifier.toUpperCase(Locale.US);
            int i = this.mIapSessionType;
            if (i == 2) {
                this.mCarPlayManager.mergeDeviceForWiFi(this.mDeviceId, btMac, usbTransportIdentifier);
                return 0;
            }
            if (i == 1) {
                this.mCarPlayManager.setDeviceBtOrUsbAddr(btMac, usbTransportIdentifier);
                this.mCarPlayManager.mergeDeviceForUsb(this.mDeviceId, btMac, usbTransportIdentifier);
                return 0;
            }
            return 0;
        }
        return 0;
    }

    public int onPowerUpdate(PowerUpdate update) {
        LogUtil.info(TAG, "onPowerUpdate update = " + update);
        return 0;
    }

    public int onBluetoothConnectionUpdate(int connectedBtProfiles) {
        LogUtil.info(TAG, "onBluetoothConnectionUpdate connectedBtProfiles = " + connectedBtProfiles);
        return 0;
    }

    public int onMediaLibraryUpdate(MediaLibraryUpdate update) {
        LogUtil.info(TAG, "onMediaLibraryUpdate");
        CarPlayMediaDataManager carPlayMediaDataManager = this.mCarPlayMediaDataManager;
        if (carPlayMediaDataManager != null) {
            carPlayMediaDataManager.changeMediaLibraryUpdate(update);
            return 0;
        }
        return 0;
    }

    public int onMediaPlaylistUpdate(String plPid, String[] pids) {
        LogUtil.info(TAG, "onMediaPlaylistUpdate");
        CarPlayMediaDataManager carPlayMediaDataManager = this.mCarPlayMediaDataManager;
        if (carPlayMediaDataManager != null) {
            carPlayMediaDataManager.changePlayList(plPid, pids);
            return 0;
        }
        return 0;
    }

    public int onPlaybackListUpdate(boolean isAvailable, String[] pids) {
        LogUtil.info(TAG, "onPlaybackListUpdate");
        CarPlayMediaDataManager carPlayMediaDataManager = this.mCarPlayMediaDataManager;
        if (carPlayMediaDataManager != null) {
            carPlayMediaDataManager.changePlaybackList(isAvailable, pids);
            return 0;
        }
        return 0;
    }

    public int onArtworkUpdate(String artworkFilePath) {
        LogUtil.info(TAG, "onArtworkUpdate");
        CarPlayMediaDataManager carPlayMediaDataManager = this.mCarPlayMediaDataManager;
        if (carPlayMediaDataManager != null) {
            carPlayMediaDataManager.changeArtwork(artworkFilePath);
            return 0;
        }
        return 0;
    }

    public int onUsbDeviceModeAudioInformation(int sampleRate) {
        LogUtil.info(TAG, "onUsbDeviceModeAudioInformation sampleRate = " + sampleRate);
        return 0;
    }

    public int onCommunicationsUpdate(CommunicationsUpdate update) {
        LogUtil.info(TAG, "onCommunicationsUpdate update = " + update);
        return 0;
    }

    public int onStartLocationInformation(int sentenceType) {
        LogUtil.info(TAG, "onStartLocationInformation sentenceType = " + sentenceType);
        CarPlayLocationDataManager carPlayLocationDataManager = this.mCarplayLocationDm;
        if (carPlayLocationDataManager != null) {
            carPlayLocationDataManager.locationEventHandle(1, sentenceType);
            return 0;
        }
        return -1;
    }

    public int onStopLocationInformation() {
        LogUtil.info(TAG, "onStopLocationInformation");
        CarPlayLocationDataManager carPlayLocationDataManager = this.mCarplayLocationDm;
        if (carPlayLocationDataManager == null) {
            return -1;
        }
        carPlayLocationDataManager.locationEventHandle(2, -1);
        LogUtil.info(TAG, "onStopLocationInformation");
        return 0;
    }

    public int onRequestAccessoryWiFiConfigurationInformation() {
        LogUtil.info(TAG, "onRequestAccessoryWiFiConfigurationInformation : ");
        CarPlayIapSessionManager.getInstance().iapReqWifiInfo();
        return 0;
    }

    public int onStartEapSession(int eapId, int sessionId) {
        return 0;
    }

    public int onReceiveEapData(int sessionId, byte[] data) {
        return 0;
    }

    public int onStopEapSession(int sessionId) {
        return 0;
    }

    public int onStartBluetoothPairing(short btTransComId, byte[] macAddress) {
        LogUtil.debug(TAG, "onStartBluetoothPairing btTransComId: " + ((int) btTransComId) + ", macAddress: " + macAddress);
        CarPlayOobManager carPlayOobManager = this.mOobManager;
        if (carPlayOobManager != null) {
            return carPlayOobManager.onStartBluetoothPairing(btTransComId, macAddress);
        }
        return -1;
    }

    public int onStopBluetoothPairing(int btTransComId, int state) {
        LogUtil.info(TAG, "onStopOobBtPairing btTransComId: " + btTransComId + ", state: " + state);
        CarPlayOobManager carPlayOobManager = this.mOobManager;
        if (carPlayOobManager != null) {
            return carPlayOobManager.onStopBluetoothPairing(btTransComId, state);
        }
        return -1;
    }
}
