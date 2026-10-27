package com.ts.carplay.lib.ts;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import com.ts.android.carplay.CarPlayClient;
import com.ts.carplay.common.util.LogEncrypt;
import com.ts.carplay.common.util.LogUtil;
import com.ts.carplay.lib.CarPlayManager;
import com.ts.carplay.lib.common.CarPlayStartInfo;
import com.ts.carplay.lib.common.CpConst;
import com.ts.carplay.lib.connect.CarPlayConnect;
import com.ts.carplay.lib.resourcemanager.CarPlayResourceManager;

/* JADX INFO: loaded from: classes.dex */
public class CarPlayStackEventHandler {
    private static final int BIT_MASK_0XFF = 255;
    private static final int CP_RESOURCE_ENTITY_ACCESSORY = 2;
    private static final int CP_RESOURCE_ENTITY_CONTROLLER = 1;
    private static final int CP_RESOURCE_ENTITY_NONE = 0;
    private static final int CP_RESOURCE_ENTITY_WAITING = 3;
    private static final int CP_SURFACE_STATE_DESTORYED = 3;
    private static final int CP_SURFACE_STATE_DESTORYED_NEED_CONFIRM = 4;
    private static final int CP_SURFACE_STATE_IDLE = 0;
    private static final int CP_SURFACE_STATE_REQUESTUI_CALLED = 1;
    private static final int CP_SURFACE_STATE_SHOW_STARTED = 2;
    public static final int NATIVE_VIEW_CONFIRM_WAIT_TIME = 3000;
    public static final int SURFACE_DESTORY_CONFIRM_WAIT_TIME = 3000;
    public static final int SURFACE_FILLED_CONFIRM_WAIT_TIME = 4000;
    public static final int SURFACE_WAITING_WAIT_TIME = 3000;
    private static final String TAG = "CarPlayStackEventHandler";
    private CarPlayManager mCarPlayManager;
    private HandlerThread mHandlerThread;
    private int mScreenCurrentEntity = 0;
    public StackEventHandler mStackEventHandler;

    public CarPlayStackEventHandler(CarPlayManager manager) {
        this.mCarPlayManager = manager;
        createStackEventHandler();
    }

    public void deviceStatusChanged(int status, String serialNum) {
        LogUtil.info(TAG, "deviceStatusChanged: status:" + status);
        if (status != 1 && status == 2) {
            CarPlayConnect.getInstance().onSessionClosed();
        }
    }

    public void deviceIap2SessionActived() {
        LogUtil.info(TAG, "deviceIap2SessionActived");
    }

    public void setInputMode(int mode) {
        LogUtil.info(TAG, "setInputMode mode:" + mode);
    }

    public void clientConnected(CarPlayClient client) {
        LogUtil.info(TAG, "clientConnected client = " + client.toString());
        if (client.mBluetoothMac != null) {
            StringBuffer btMacStr = new StringBuffer("");
            for (int i = 0; i < client.mBluetoothMac.length; i++) {
                String strTemp = Integer.toHexString(client.mBluetoothMac[i] & 255);
                if (strTemp.length() < 2) {
                    btMacStr.append(0);
                }
                btMacStr.append(strTemp);
                if (i != client.mBluetoothMac.length - 1) {
                    btMacStr.append(":");
                }
            }
            LogUtil.info(TAG, "clientConnected btMacStr = " + LogEncrypt.MacAddressEncrypt(btMacStr.toString()));
        }
    }

    public void clientDisconnect() {
        CarPlayResourceManager.getInstance().releaseCurrentAudio();
    }

    public int videoResourceRequest() {
        LogUtil.info(TAG, "videoResourceRequest");
        if (this.mScreenCurrentEntity != 1) {
            LogUtil.warning(TAG, "videoResourceRequest: Entity isnt controller.");
            return CpConst.CarPlayResourceStatus.CARPLAY_RESOURCE_STATUS_BUSY.ordinal();
        }
        return CarPlayResourceManager.getInstance().videoResourceRequest();
    }

    public int audioResourceRequest(int requestType, int sampleRate) {
        LogUtil.info(TAG, "resourceRequest: sampleRate:" + sampleRate + " requestType:" + requestType);
        return CarPlayResourceManager.getInstance().audioResourceRequest(requestType, sampleRate);
    }

    public void resourceChanged(int[] data) {
        if (data == null) {
            LogUtil.warning(TAG, "resourceChanged data is null");
            return;
        }
        if (data.length != 4) {
            LogUtil.warning(TAG, "resourceChanged data length is invalid.");
            return;
        }
        int screenEntity = data[0];
        int audioEntity = data[1];
        int perScreenEntity = data[2];
        int perAudioEntity = data[3];
        CarPlayResourceManager.getInstance().resourceChanged(screenEntity, audioEntity, perScreenEntity, perAudioEntity);
        this.mScreenCurrentEntity = screenEntity;
    }

    public void appStateChanged(int phoneEntity, int tbtEntity, int speechEntity, int speechMode) {
        LogUtil.info(TAG, "appStateChanged: phoneEntity:" + phoneEntity + " tbtEntity:" + tbtEntity + " speechEntity" + speechEntity + " speechMode:" + speechMode);
        CarPlayResourceManager.getInstance().appStateChanged(phoneEntity, tbtEntity, speechEntity, speechMode);
    }

    public void nativeUiLaunch() {
        this.mCarPlayManager.releaseScreenAndToHome();
    }

    public void duckAudio(int durationMs, int volume) {
        LogUtil.info(TAG, "duckAudio: durationMs:" + durationMs + " volume:" + volume);
        CarPlayResourceManager.getInstance().duckAudio(durationMs, volume);
    }

    public void unduckAudio(int durationMs) {
        LogUtil.info(TAG, "unduckAudio: durationMs:" + durationMs);
        CarPlayResourceManager.getInstance().unduckAudio(durationMs);
    }

    public void notification(int notify, int param1, int param2, int param3) {
        LogUtil.info(TAG, "notification: notify:" + notify + " param1:" + param1 + " param2:" + param2 + " param3:" + param3);
        CarPlayResourceManager.getInstance().notification(notify, param1, param2, param3);
    }

    public void onCpClientMacAddrRecv(byte[] clientMac) {
        if (clientMac == null) {
            LogUtil.info(TAG, "onCpClientMacAddrRecv, mac error, need return.");
            return;
        }
        StringBuffer btMacStr = new StringBuffer("");
        for (int i = 0; i < clientMac.length; i++) {
            String strTemp = Integer.toHexString(clientMac[i] & 255);
            if (strTemp.length() < 2) {
                btMacStr.append(0);
            }
            btMacStr.append(strTemp);
            if (i != clientMac.length - 1) {
                btMacStr.append(":");
            }
        }
        String addr = btMacStr.toString();
        this.mCarPlayManager.notifyCarPlayClientWifiAddr(addr);
    }

    public boolean isResourceInBorrowed(CpConst.CarPlayResource resourceType) {
        return CarPlayResourceManager.getInstance().isResourceBorrowed(resourceType);
    }

    public CarPlayStartInfo getLastResourceState(CpConst.CarPlayResource resourceType) {
        return CarPlayResourceManager.getInstance().getLastResourceState(resourceType);
    }

    private void createStackEventHandler() {
        HandlerThread handlerThread = new HandlerThread(TAG);
        this.mHandlerThread = handlerThread;
        handlerThread.start();
        this.mStackEventHandler = new StackEventHandler(this, this.mHandlerThread.getLooper());
    }

    public StackEventHandler getStackEventHandler() {
        return this.mStackEventHandler;
    }

    public class StackEventHandler extends Handler {
        private CarPlayStackEventHandler mCarPlayStackEventHandler;

        public StackEventHandler(CarPlayStackEventHandler cn, Looper looper) {
            super(looper);
            this.mCarPlayStackEventHandler = cn;
        }

        @Override // android.os.Handler
        public void handleMessage(Message msg) {
            if (this.mCarPlayStackEventHandler == null) {
            }
            switch (msg.what) {
                case 0:
                    this.mCarPlayStackEventHandler.deviceStatusChanged(msg.arg1, null);
                    break;
                case 1:
                    this.mCarPlayStackEventHandler.setInputMode(msg.arg1);
                    break;
                case 2:
                case 5:
                default:
                    LogUtil.error(CarPlayStackEventHandler.TAG, "handleMessage() Unknown event type: " + msg.what);
                    break;
                case 3:
                    this.mCarPlayStackEventHandler.videoResourceRequest();
                    break;
                case 4:
                    this.mCarPlayStackEventHandler.resourceChanged((int[]) msg.obj);
                    break;
                case 6:
                    this.mCarPlayStackEventHandler.nativeUiLaunch();
                    break;
                case 7:
                    this.mCarPlayStackEventHandler.duckAudio(msg.arg1, msg.arg2);
                    break;
                case 8:
                    this.mCarPlayStackEventHandler.unduckAudio(msg.arg1);
                    break;
                case 9:
                    int[] data = (int[]) msg.obj;
                    this.mCarPlayStackEventHandler.notification(msg.arg1, data[0], data[1], data[2]);
                    break;
                case 10:
                    CarPlayClient client = (CarPlayClient) msg.obj;
                    this.mCarPlayStackEventHandler.clientConnected(client);
                    break;
            }
        }
    }
}
