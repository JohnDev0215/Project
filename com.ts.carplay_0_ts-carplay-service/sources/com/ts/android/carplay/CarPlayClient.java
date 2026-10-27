package com.ts.android.carplay;

import com.ts.android.carplay.AudioResource;
import com.ts.android.carplay.ScreenResource;

/* JADX INFO: loaded from: classes.dex */
public class CarPlayClient {
    public byte[] mBluetoothMac;
    public int mClientId;
    public String mName;
    private CarPlayNative mNative;
    public int mType;
    public byte[] mUsbMac;
    public byte[] mWifiMac;

    public CarPlayClient() {
        this.mNative = null;
        this.mNative = CarPlayNative.getInstance();
    }

    public void setClientId(int clientId) {
        this.mClientId = clientId;
    }

    public boolean isTypeChanged() {
        return false;
    }

    public void setBluetoothMac(byte[] btMac) {
        if (btMac != null) {
            this.mBluetoothMac = new byte[btMac.length];
            for (int i = 0; i < btMac.length; i++) {
                this.mBluetoothMac[i] = btMac[i];
            }
        }
    }

    public byte[] getBluetoothMac() {
        return this.mBluetoothMac;
    }

    public boolean isNameChanged() {
        return false;
    }

    public boolean isBluetoothMacChanged() {
        return false;
    }

    public boolean isUsbMacChanged() {
        return false;
    }

    public boolean isWifiMacChanged() {
        return false;
    }

    public int hashCode() {
        return 0;
    }

    public boolean equals(Object obj) {
        return false;
    }

    public int start(int mClientId, boolean isConnectedByIap) throws Exception {
        CarPlayNative carPlayNative = this.mNative;
        if (carPlayNative != null) {
            return carPlayNative.start(mClientId, isConnectedByIap);
        }
        throw new Exception("mNative is null");
    }

    public int stop() {
        CarPlayNative carPlayNative = this.mNative;
        if (carPlayNative != null) {
            return carPlayNative.stop();
        }
        return CarPlay.NOT_STARTED;
    }

    public int appLaunch(String carplayApp, CommandCompletionCallback callback) {
        CarPlayNative carPlayNative = this.mNative;
        if (carPlayNative != null) {
            return carPlayNative.appLaunch(carplayApp, callback);
        }
        return CarPlay.NOT_STARTED;
    }

    public int setRenderingSurface(Object surface, int screenType) {
        CarPlayNative carPlayNative = this.mNative;
        if (carPlayNative != null) {
            return carPlayNative.setSurface(surface, screenType);
        }
        return CarPlay.NOT_STARTED;
    }

    public int showUi(int screenType, int inUrl) {
        CarPlayNative carPlayNative = this.mNative;
        if (carPlayNative != null) {
            return carPlayNative.showUi(screenType, inUrl, 0);
        }
        return CarPlay.NOT_STARTED;
    }

    public int showUi(int screenType, int inUrl, int exElement) {
        if (this.mNative != null) {
            if (inUrl == 2 && exElement != 0) {
                return -3;
            }
            return this.mNative.showUi(screenType, inUrl, exElement);
        }
        return CarPlay.NOT_STARTED;
    }

    public int stopUi(int screenType) {
        CarPlayNative carPlayNative = this.mNative;
        if (carPlayNative != null) {
            return carPlayNative.stopUi(screenType);
        }
        return CarPlay.NOT_STARTED;
    }

    public int startAuxIn() {
        CarPlayNative carPlayNative = this.mNative;
        if (carPlayNative != null) {
            return carPlayNative.startAuxIn();
        }
        return CarPlay.NOT_STARTED;
    }

    public int stopAuxIn() {
        CarPlayNative carPlayNative = this.mNative;
        if (carPlayNative != null) {
            return carPlayNative.stopAuxIn();
        }
        return CarPlay.NOT_STARTED;
    }

    public int changeUiContext(int contextUrl) {
        CarPlayNative carPlayNative = this.mNative;
        if (carPlayNative != null) {
            return carPlayNative.changeUiContext(contextUrl);
        }
        return CarPlay.NOT_STARTED;
    }

    public int siriLaunch(int siriAction) {
        CarPlayNative carPlayNative = this.mNative;
        if (carPlayNative != null) {
            return carPlayNative.siriLaunch(siriAction, 0);
        }
        return CarPlay.NOT_STARTED;
    }

    public int siriLaunch(int siriAction, int latencyMs) {
        CarPlayNative carPlayNative = this.mNative;
        if (carPlayNative != null) {
            return carPlayNative.siriLaunch(siriAction, latencyMs);
        }
        return CarPlay.NOT_STARTED;
    }

    public int siriLaunch(int siriAction, long timestamp) {
        CarPlayNative carPlayNative = this.mNative;
        if (carPlayNative != null) {
            return carPlayNative.siriLaunch(siriAction, timestamp);
        }
        return CarPlay.NOT_STARTED;
    }

    public int setNightMode(boolean nightMode) {
        CarPlayNative carPlayNative = this.mNative;
        if (carPlayNative != null) {
            return carPlayNative.setNightMode(nightMode);
        }
        return CarPlay.NOT_STARTED;
    }

    public int setUiAppearanceMode(int screenType, int uiMode, int uiSetting) {
        CarPlayNative carPlayNative = this.mNative;
        if (carPlayNative != null) {
            return carPlayNative.setUiAppearanceMode(screenType, uiMode, uiSetting);
        }
        return CarPlay.NOT_STARTED;
    }

    public int setMapAppearanceMode(int screenType, int mapMode, int mapSetting) {
        CarPlayNative carPlayNative = this.mNative;
        if (carPlayNative != null) {
            return carPlayNative.setMapAppearanceMode(screenType, mapMode, mapSetting);
        }
        return CarPlay.NOT_STARTED;
    }

    public int changeMapZoomLevel(int screenType, int zoomDirection) {
        CarPlayNative carPlayNative = this.mNative;
        if (carPlayNative != null) {
            return carPlayNative.changeMapZoomLevel(screenType, zoomDirection);
        }
        return CarPlay.NOT_STARTED;
    }

    public int refreshClient() {
        return 0;
    }

    public int sendIap2Message(byte[] iapMessage) {
        CarPlayNative carPlayNative = this.mNative;
        if (carPlayNative != null) {
            return carPlayNative.sendIapMsg(iapMessage, iapMessage.length);
        }
        return CarPlay.NOT_STARTED;
    }

    public int sendIap2Message(byte[] iapMessage, int length) {
        CarPlayNative carPlayNative = this.mNative;
        if (carPlayNative != null) {
            return carPlayNative.sendIapMsg(iapMessage, length);
        }
        return CarPlay.NOT_STARTED;
    }

    public int updateViewArea(int animationDurationMilli, int viewAreaIndex) {
        return updateViewArea(null, animationDurationMilli, viewAreaIndex, null);
    }

    public int updateViewArea(String uuid, int animationDurationMilli, int viewAreaIndex, int[] adjacentAreas) {
        CarPlayNative carPlayNative = this.mNative;
        if (carPlayNative != null) {
            return carPlayNative.updateViewArea(uuid, animationDurationMilli, viewAreaIndex, adjacentAreas);
        }
        return CarPlay.NOT_STARTED;
    }

    public String toString() {
        return "";
    }

    public void rspAudioFocusReq(int audioType, boolean isSuccess) {
        CarPlayNative carPlayNative = this.mNative;
        if (carPlayNative != null) {
            carPlayNative.rspAudioFocusReq(audioType, isSuccess);
        }
    }

    public static final class StartInfo {
        public AudioResource.Change mAudioChange;
        public int mLimitedUI;
        public int mMapMode;
        public int mMapSetting;
        public int mNightMode;
        public boolean mPhoneActive;
        public ScreenResource.Change mScreenChange;
        public int mSpeechMode;
        public boolean mTbt;
        public int mUiMode;
        public int mUiSetting;

        public StartInfo() {
            this.mAudioChange = new AudioResource.Change();
            this.mScreenChange = new ScreenResource.Change();
        }

        public StartInfo(int nightMode, int limitedUI, boolean phoneActive, boolean turnByturnActive, int speechMode, AudioResource.Change mainAudioChange, ScreenResource.Change screenChange, int uiMode, int mapMode, int uiSetting, int mapSetting) {
            this.mNightMode = nightMode;
            this.mLimitedUI = limitedUI;
            this.mPhoneActive = phoneActive;
            this.mTbt = turnByturnActive;
            this.mSpeechMode = speechMode;
            this.mUiMode = uiMode;
            this.mMapMode = mapMode;
            this.mUiSetting = uiSetting;
            this.mMapSetting = mapSetting;
            this.mAudioChange = new AudioResource.Change(mainAudioChange.mTransferType, mainAudioChange.mTransferPriority, mainAudioChange.mTakeConstraint, mainAudioChange.mBorrowOrUnborrowConstraint, mainAudioChange.mBorrowID);
            this.mScreenChange = new ScreenResource.Change(screenChange.mTransferType, screenChange.mTransferPriority, screenChange.mTakeConstraint, screenChange.mBorrowOrUnborrowConstraint, screenChange.mBorrowID);
        }
    }
}
