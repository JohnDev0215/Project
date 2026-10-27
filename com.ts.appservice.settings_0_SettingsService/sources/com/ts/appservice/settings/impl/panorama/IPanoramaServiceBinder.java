package com.ts.appservice.settings.impl.panorama;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Configuration;
import android.hardware.camera2.CameraDevice;
import android.hardware.camera2.CameraManager;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.os.RemoteException;
import com.ts.appservice.settings.impl.qnx.SettingsQnxService;
import com.ts.avm.sdk.AvmManager;
import com.ts.avm.sdk.IAvmServiceConnectionListener;
import com.ts.lib.settings.common.utilities.LogUtil;
import com.ts.lib.settings.panorama.IPanoramaSettingsCallback;
import com.ts.lib.settings.panorama.IPanoramaSettingsService;

/* JADX INFO: loaded from: classes.dex */
public class IPanoramaServiceBinder extends IPanoramaSettingsService.Stub {
    private static final int CAMERA_AVAILABLE = 0;
    private static final int CAMERA_ERROR = 2;
    private static final int CAMERA_UNAVAILABLE = 1;
    private static final int CLOSE_CAMERA_DELAY = 2000;
    private static final int DISPLAYLMODE_WIDGET = 3;
    private static final int DISPLAY_MODELFULL_SCREEN = 2;
    private static final int MSG_CLOSE_CAMERA_DEVICE = 1;
    private static final int MSG_CLOSE_FIRST_CAMERA_DEVICE = 3;
    private static final int MSG_CLOSE_ZERO_CAMERA_DEVICE = 2;
    private static final int PANORAMA_FOCUS_OFF = 0;
    private static final int PANORAMA_FOCUS_ON = 1;
    private static final int PANORAMA_OUTPUT_SIGNAL_CVBS = 0;
    private static final int PANORAMA_OUTPUT_SIGNAL_LVDS = 1;
    private static final int PANORAMA_ROTATION_HORIZONTAL = 1;
    private static final int PANORAMA_ROTATION_VERTICAL = 2;
    private static final int RADAR_FLOATING = 15;
    private static final String TAG = IPanoramaServiceBinder.class.getSimpleName();
    private AvmManager mAvmManager;
    private Handler mBackgroundHandler;
    private HandlerThread mBackgroundThread;
    private CameraDevice mCameraDevice;
    private CameraDevice mCameraIdFirstDevice;
    private CameraDevice mCameraIdZeroDevice;
    private CameraManager mCameraManager;
    private Context mContext;
    private int mOrientationState;
    private PanoramaCallback mPanoramaCallback;
    private SettingsQnxService mSettingsQnxService;
    private final AvmManager.AvmStatusListener mAvmStatusListener = new AvmManager.AvmStatusListener() { // from class: com.ts.appservice.settings.impl.panorama.IPanoramaServiceBinder.2
        @Override // com.ts.avm.sdk.AvmManager.AvmStatusListener
        public void onStatusChanged(int code, String message) {
            LogUtil.debug(IPanoramaServiceBinder.TAG, "onStatusChanged code :" + code + "message :" + message);
            if (2 == code) {
                IPanoramaServiceBinder.this.mPanoramaCallback.onPanoWorkStateChanged(1);
            } else {
                IPanoramaServiceBinder.this.mPanoramaCallback.onPanoWorkStateChanged(0);
            }
        }
    };
    private final AvmManager.AvmViewStateListener mAvmViewStateListener = new AvmManager.AvmViewStateListener() { // from class: com.ts.appservice.settings.impl.panorama.IPanoramaServiceBinder.3
        @Override // com.ts.avm.sdk.AvmManager.AvmViewStateListener
        public void onAvmViewStateChanged(int mode) {
            LogUtil.debug(IPanoramaServiceBinder.TAG, "onAvmViewStateChanged mode :" + mode);
            IPanoramaServiceBinder.this.mPanoramaCallback.onPanoOutputStateChanged(mode);
            if (mode != 15 && mode != 0) {
                IPanoramaServiceBinder.this.mPanoramaCallback.onDisplayModeChanged(2);
            } else if (mode == 15) {
                IPanoramaServiceBinder.this.mPanoramaCallback.onDisplayModeChanged(3);
            }
        }
    };
    private final IAvmServiceConnectionListener mAvmServiceConnectionListener = new IAvmServiceConnectionListener() { // from class: com.ts.appservice.settings.impl.panorama.IPanoramaServiceBinder.4
        @Override // com.ts.avm.sdk.IAvmServiceConnectionListener
        public void onServiceConnected() {
            LogUtil.info(IPanoramaServiceBinder.TAG, "AvmManager onServiceConnected ");
        }

        @Override // com.ts.avm.sdk.IAvmServiceConnectionListener
        public void onServiceDisConnected() {
            LogUtil.info(IPanoramaServiceBinder.TAG, "AvmManager onServiceDisConnected ");
        }

        @Override // com.ts.avm.sdk.IAvmServiceConnectionListener
        public void onError(int code, String message) {
        }
    };
    private CameraDevice.StateCallback stateCallback = new CameraDevice.StateCallback() { // from class: com.ts.appservice.settings.impl.panorama.IPanoramaServiceBinder.5
        @Override // android.hardware.camera2.CameraDevice.StateCallback
        public void onOpened(CameraDevice cameraDevice) {
            if (cameraDevice.getId().equals("0")) {
                IPanoramaServiceBinder.this.mCameraIdZeroDevice = cameraDevice;
                IPanoramaServiceBinder.this.mBackgroundHandler.sendEmptyMessageDelayed(2, 2000L);
            } else if (cameraDevice.getId().equals("1")) {
                IPanoramaServiceBinder.this.mCameraIdFirstDevice = cameraDevice;
                IPanoramaServiceBinder.this.mBackgroundHandler.sendEmptyMessageDelayed(3, 2000L);
            } else {
                IPanoramaServiceBinder.this.mCameraDevice = cameraDevice;
                IPanoramaServiceBinder.this.mBackgroundHandler.sendEmptyMessageDelayed(1, 2000L);
            }
            LogUtil.info(IPanoramaServiceBinder.TAG, "onOpened cameraDevice" + cameraDevice.getId());
            IPanoramaServiceBinder.this.mPanoramaCallback.onCameraStateChanged(Integer.parseInt(cameraDevice.getId()), 0);
        }

        @Override // android.hardware.camera2.CameraDevice.StateCallback
        public void onDisconnected(CameraDevice cameraDevice) {
            LogUtil.info(IPanoramaServiceBinder.TAG, "onDisconnected cameraDevice" + cameraDevice.getId());
            if (cameraDevice.getId().equals("0")) {
                IPanoramaServiceBinder.this.mCameraIdZeroDevice = null;
            } else if (cameraDevice.getId().equals("1")) {
                IPanoramaServiceBinder.this.mCameraIdFirstDevice = null;
            } else {
                IPanoramaServiceBinder.this.mCameraDevice = null;
            }
            IPanoramaServiceBinder.this.mPanoramaCallback.onCameraStateChanged(Integer.parseInt(cameraDevice.getId()), 1);
            cameraDevice.close();
        }

        @Override // android.hardware.camera2.CameraDevice.StateCallback
        public void onError(CameraDevice cameraDevice, int i) {
            LogUtil.info(IPanoramaServiceBinder.TAG, "onError cameraDevice" + cameraDevice.getId());
            if (cameraDevice.getId().equals("0")) {
                IPanoramaServiceBinder.this.mCameraIdZeroDevice = null;
            } else if (cameraDevice.getId().equals("1")) {
                IPanoramaServiceBinder.this.mCameraIdFirstDevice = null;
            } else {
                IPanoramaServiceBinder.this.mCameraDevice = null;
            }
            IPanoramaServiceBinder.this.mPanoramaCallback.onCameraStateChanged(Integer.parseInt(cameraDevice.getId()), 2);
            cameraDevice.close();
        }

        @Override // android.hardware.camera2.CameraDevice.StateCallback
        public void onClosed(CameraDevice cameraDevice) {
            super.onClosed(cameraDevice);
            LogUtil.info(IPanoramaServiceBinder.TAG, "onClosed cameraDevice" + cameraDevice.getId());
        }
    };

    public IPanoramaServiceBinder(Context context) {
        this.mContext = context;
        this.mPanoramaCallback = new PanoramaCallback(this.mContext);
        this.mCameraManager = (CameraManager) this.mContext.getSystemService("camera");
        startBackgroundThread();
        this.mSettingsQnxService = SettingsQnxService.getInstance(this.mContext);
        registerBroadcast();
        AvmManager avmManager = AvmManager.getInstance();
        this.mAvmManager = avmManager;
        avmManager.registerAvmStatusListener(this.mAvmStatusListener);
        this.mAvmManager.registerAvmViewStatusListener(this.mAvmViewStateListener);
        this.mAvmManager.init(this.mContext, this.mAvmServiceConnectionListener);
        LogUtil.info(TAG, "IPanoramaServiceBinder init end");
    }

    private void registerBroadcast() {
        BroadcastReceiver receiver = new BroadcastReceiver() { // from class: com.ts.appservice.settings.impl.panorama.IPanoramaServiceBinder.1
            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context, Intent intent) {
                String action = intent.getAction();
                if (((action.hashCode() == 1696449983 && action.equals("com.ts.test.callback")) ? (byte) 0 : (byte) -1) == 0) {
                    IPanoramaServiceBinder.this.mPanoramaCallback.onPanoOutputStateChanged(1);
                    IPanoramaServiceBinder.this.mPanoramaCallback.onPanoWorkStateChanged(1);
                    IPanoramaServiceBinder.this.mPanoramaCallback.onPanOutputSignalChanged(1);
                    IPanoramaServiceBinder.this.mPanoramaCallback.onBackLineConfigChanged(1);
                    IPanoramaServiceBinder.this.mPanoramaCallback.onPanoramaOnlineStateChanged(1);
                    IPanoramaServiceBinder.this.mPanoramaCallback.onRFCameraSwitchStateChanged(1);
                    IPanoramaServiceBinder.this.mPanoramaCallback.onPanoRotationChanged(1);
                    IPanoramaServiceBinder.this.mPanoramaCallback.onLVDSStateChanged(1);
                    IPanoramaServiceBinder.this.mPanoramaCallback.onDisplayModeChanged(1);
                    IPanoramaServiceBinder.this.mPanoramaCallback.onEmergencyButtonStateChanged(1);
                    IPanoramaServiceBinder.this.mPanoramaCallback.onACUStateChanged(1);
                    IPanoramaServiceBinder.this.mPanoramaCallback.onCameraUseChange("hahha", true);
                    IPanoramaServiceBinder.this.mPanoramaCallback.onCarInfoChanged(1);
                    IPanoramaServiceBinder.this.mPanoramaCallback.onPanoramaTransparenceChanged(1);
                    IPanoramaServiceBinder.this.mPanoramaCallback.onPanoAPAStateChanged(1);
                }
            }
        };
        IntentFilter filter = new IntentFilter();
        filter.addAction("com.ts.test.callback");
        this.mContext.registerReceiver(receiver, filter);
    }

    public void onConfigurationChanged(Configuration newConfig) {
        int orientationState = newConfig.orientation;
        LogUtil.info(TAG, "onConfigurationChanged orientationState :" + orientationState);
        if (orientationState != this.mOrientationState) {
            this.mPanoramaCallback.onPanoRotationChanged(orientationState == 2 ? 1 : 2);
        }
        setScreenOrientationState(orientationState);
        this.mOrientationState = orientationState;
    }

    private void setScreenOrientationState(int state) {
        if (state == 2) {
            this.mSettingsQnxService.setScreenOrientationState(0);
        } else if (state == 1) {
            this.mSettingsQnxService.setScreenOrientationState(1);
        }
    }

    public int getPanoOutputSignal() {
        LogUtil.debug(TAG, "getPanoOutputSignal value ");
        return 1;
    }

    public int getPanoWorkState() {
        int avmWorkState = this.mAvmManager.getAvmWorkState();
        LogUtil.debug(TAG, "getPanoWorkState value :" + avmWorkState);
        return avmWorkState == 2 ? 1 : 0;
    }

    public int getBackLineConfig() {
        int backLineConfig = this.mAvmManager.getBackLineConfig();
        LogUtil.debug(TAG, "getBackLineConfig value :" + backLineConfig);
        return backLineConfig;
    }

    public int getPanoOutputState() {
        int avmViewMode = this.mAvmManager.getAvmViewMode();
        LogUtil.debug(TAG, "getPanoOutputState value :" + avmViewMode);
        return avmViewMode;
    }

    public int getPanoRotation() {
        int orientationValue = this.mContext.getResources().getConfiguration().orientation;
        LogUtil.debug(TAG, "getPanoRotation value :" + orientationValue);
        return orientationValue == 2 ? 1 : 2;
    }

    public int getDisplayMode() {
        int avmDisplayMode = this.mAvmManager.getAvmViewMode();
        LogUtil.debug(TAG, "getDisplayMode value :" + avmDisplayMode);
        if (avmDisplayMode != 15 && avmDisplayMode != 0) {
            return 2;
        }
        if (avmDisplayMode == 15) {
            return 3;
        }
        return -1;
    }

    public boolean isValideCamera(int cameraId) {
        LogUtil.debug(TAG, "isValideCamera value :");
        return false;
    }

    public boolean isPanoramaSetting() {
        LogUtil.debug(TAG, "isPanoramaSetting value ");
        return false;
    }

    public String getCameraUsePackageName() {
        LogUtil.debug(TAG, "getCameraUsePackageName value ");
        return null;
    }

    public void requestCameraState(int cameraId) {
        LogUtil.debug(TAG, "getCameraUsePackageName value ");
        checkCameraIdEnable(cameraId);
    }

    public void subscribePanoramaCallback(IPanoramaSettingsCallback callback) throws RemoteException {
        if (callback != null) {
            this.mPanoramaCallback.register(callback);
        }
    }

    public void unsubscribePanoramaCallback(IPanoramaSettingsCallback callback) throws RemoteException {
        if (callback != null) {
            this.mPanoramaCallback.unregister(callback);
        }
    }

    public void destoryPanoramaBinder() {
        PanoramaCallback panoramaCallback = this.mPanoramaCallback;
        if (panoramaCallback != null) {
            panoramaCallback.kill();
        }
        AvmManager.AvmStatusListener avmStatusListener = this.mAvmStatusListener;
        if (avmStatusListener != null) {
            this.mAvmManager.unregisterAvmStatusListener(avmStatusListener);
        }
        AvmManager.AvmViewStateListener avmViewStateListener = this.mAvmViewStateListener;
        if (avmViewStateListener != null) {
            this.mAvmManager.unregisterAvmViewStatusListener(avmViewStateListener);
        }
        this.mAvmManager.deinit(this.mAvmServiceConnectionListener);
    }

    private void checkCameraIdEnable(int cameraId) {
        try {
            this.mCameraManager.openCamera(String.valueOf(cameraId), this.stateCallback, this.mBackgroundHandler);
        } catch (Exception e) {
            LogUtil.error(TAG, "cameraDevice cameraId  " + cameraId + "is error");
            this.mPanoramaCallback.onCameraStateChanged(cameraId, 1);
        }
    }

    private void startBackgroundThread() {
        HandlerThread handlerThread = new HandlerThread("CameraBackground");
        this.mBackgroundThread = handlerThread;
        handlerThread.start();
        this.mBackgroundHandler = new Handler(this.mBackgroundThread.getLooper()) { // from class: com.ts.appservice.settings.impl.panorama.IPanoramaServiceBinder.6
            @Override // android.os.Handler
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                int i = msg.what;
                if (i == 1) {
                    LogUtil.debug(IPanoramaServiceBinder.TAG, "mBackgroundHandler receive close other camera device");
                    if (IPanoramaServiceBinder.this.mCameraDevice != null) {
                        IPanoramaServiceBinder.this.mCameraDevice.close();
                        IPanoramaServiceBinder.this.mCameraDevice = null;
                        return;
                    }
                    return;
                }
                if (i == 2) {
                    LogUtil.debug(IPanoramaServiceBinder.TAG, "mBackgroundHandler receive close zero camera device");
                    if (IPanoramaServiceBinder.this.mCameraIdZeroDevice != null) {
                        IPanoramaServiceBinder.this.mCameraIdZeroDevice.close();
                        IPanoramaServiceBinder.this.mCameraIdZeroDevice = null;
                        return;
                    }
                    return;
                }
                if (i == 3) {
                    LogUtil.debug(IPanoramaServiceBinder.TAG, "mBackgroundHandler receive close first camera device");
                    if (IPanoramaServiceBinder.this.mCameraIdFirstDevice != null) {
                        IPanoramaServiceBinder.this.mCameraIdFirstDevice.close();
                        IPanoramaServiceBinder.this.mCameraIdFirstDevice = null;
                    }
                }
            }
        };
    }
}
