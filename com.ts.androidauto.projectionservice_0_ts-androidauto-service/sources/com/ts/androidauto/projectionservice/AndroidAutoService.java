package com.ts.androidauto.projectionservice;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.hardware.usb.UsbDevice;
import android.os.Binder;
import android.os.Debug;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.RemoteCallbackList;
import android.os.RemoteException;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.Surface;
import com.ts.androidauto.aap.AapPhoneManager;
import com.ts.androidauto.aap.AapPhoneManagerListener;
import com.ts.androidauto.aap.common.ConnectionInfo;
import com.ts.androidauto.aap.common.StatusConst;
import com.ts.androidauto.aap.data.MediaMetadata;
import com.ts.androidauto.aap.data.NavigationPositionData;
import com.ts.androidauto.aap.data.NavigationStateData;
import com.ts.androidauto.fsdk.common.AndroidAutoConst;
import com.ts.androidauto.fsdk.common.BluetoothConst;
import com.ts.androidauto.fsdk.common.LogEncrypt;
import com.ts.androidauto.fsdk.common.LogUtil;
import com.ts.androidauto.fsdk.common.ResourceConst;
import com.ts.androidauto.fsdk.data.AndroidAutoDevice;
import com.ts.androidauto.sdk.aidl.IAndroidAutoAppCallback;
import com.ts.androidauto.sdk.aidl.IAndroidAutoDeviceListCallback;
import com.ts.androidauto.sdk.aidl.IAndroidAutoService;
import com.ts.androidauto.sdk.aidl.IAndroidAutoServiceCallback;
import com.ts.androidauto.sdk.aidl.IAndroidAutoSessionStatusCallback;
import com.ts.androidauto.sdk.aidl.IBatteryStatusCallback;
import com.ts.androidauto.sdk.aidl.IMediaDataCallback;
import com.ts.androidauto.sdk.aidl.INavigationDataCallback;
import com.ts.androidauto.sdk.aidl.data.ConnectRequest;
import com.ts.androidauto.sdk.aidl.data.UiConfig;
import com.ts.androidauto.sdk.aidl.data.VehicleInfo;
import com.ts.androidauto.service.LinkController;
import com.ts.androidauto.service.LinkControllerListener;
import com.ts.androidauto.service.common.Consumer;
import com.ts.androidauto.service.device.AutoConnectManager;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class AndroidAutoService extends Service {
    private static final String ACTION_ANDROID_AUTO_START_FINISH = "com.ts.androidauto.start.finish";
    private static final int DELAY_INIT_LINKCONTROLLER_MS = 5000;
    private static final int MSG_DELAY_INIT_LINKCONTROLLER = 1;
    static final String PERMISSION_APP = "car.permission.ANDROIDAUTO_APP";
    static final String PERMISSION_DEVICELIST = "car.permission.ANDROIDAUTO_DEVICE_LIST";
    static final String PERMISSION_SERVICE = "car.permission.ANDROIDAUTO";
    private static final String TAG = "AAPAndroidAutoService";
    private AapPhoneManager mAndroidAutoManager;
    private AutoConnectManager mAutoConnectManager;
    private String mErrorMessage;
    private Handler mHandler;
    private HandlerThread mHandlerThread;
    private LinkController mLinkController;
    private boolean mIsVehiclePowerStart = true;
    private AndroidAutoServiceImpl mLinkCommandBinder = new AndroidAutoServiceImpl(this, null);
    private RemoteCallbackList<IAndroidAutoServiceCallback> mIAndroidAutoServiceCallbackList = new RemoteCallbackList<>();
    private RemoteCallbackList<IAndroidAutoSessionStatusCallback> mIAndroidAutoSessionStatusCallbackList = new RemoteCallbackList<>();
    private RemoteCallbackList<IAndroidAutoAppCallback> mIAndroidAutoAppCallbackList = new RemoteCallbackList<>();
    private RemoteCallbackList<IBatteryStatusCallback> mIBatteryStatusCallbackList = new RemoteCallbackList<>();
    private RemoteCallbackList<IMediaDataCallback> mIMediaDataCallbackList = new RemoteCallbackList<>();
    private RemoteCallbackList<INavigationDataCallback> mINavigationDataCallbackList = new RemoteCallbackList<>();
    private RemoteCallbackList<IAndroidAutoDeviceListCallback> mAndroidAutoDeviceListCallbackList = new RemoteCallbackList<>();
    private Context mContext = null;
    private boolean mIsInit = false;
    private boolean mIsSetVehicleInfo = false;
    private boolean mIsUseNativeNavigation = false;
    private boolean mIsVrEnable = false;
    private boolean mIsStatusActivated = false;
    private AndroidAutoConst.LinkErrorCode mFailedError = AndroidAutoConst.LinkErrorCode.NO_ERROR_CODE;
    private LinkControllerListener linkControllerListener = new AnonymousClass1();
    private AapPhoneManagerListener mAapPhoneManagerListener = new AapPhoneManagerListener() { // from class: com.ts.androidauto.projectionservice.AndroidAutoService.2
        @Override // com.ts.androidauto.aap.AapPhoneManagerListener
        public void notifyStatusChanged(AndroidAutoDevice linkDevice, AndroidAutoConst.LinkStatus linkStatus, AndroidAutoConst.LinkErrorCode errorCode, String errorMessage) {
            LogUtil.info("AAPAndroidAutoService", "onStatusChanged linkStatusl:" + linkStatus + " errorCode:" + errorCode + " linkDevice:" + linkDevice);
            StringBuilder sb = new StringBuilder();
            sb.append("notifyStatusChanged isAutoConnect ? ");
            sb.append(linkDevice.isAutoConnect());
            LogUtil.info("AAPAndroidAutoService", sb.toString());
            if (!linkDevice.isAutoConnect() || (linkStatus != AndroidAutoConst.LinkStatus.LINK_STATUS_ACTIVATING && linkStatus != AndroidAutoConst.LinkStatus.LINK_STATUS_CONNECT_FAILED)) {
                LogUtil.info("AAPAndroidAutoService", "notifyStatusChanged linkStatus: " + linkStatus);
                LogUtil.info("AAPAndroidAutoService", "notifyStatusChanged mErrorMessage: " + AndroidAutoService.this.mErrorMessage);
                if (!TextUtils.isEmpty(AndroidAutoService.this.mErrorMessage) && linkStatus == AndroidAutoConst.LinkStatus.LINK_STATUS_CONNECT_FAILED && AndroidAutoService.this.mFailedError == AndroidAutoConst.LinkErrorCode.ERROR_WIFI_CONNECT_UNSOLICITED_MESSAGE) {
                    linkStatus = AndroidAutoConst.LinkStatus.LINK_STATUS_DEACTIVATED;
                }
                AndroidAutoService.this.mFailedError = errorCode;
                AndroidAutoService.this.mErrorMessage = errorMessage;
                LogUtil.info("AAPAndroidAutoService", "notifyStatusChanged linkStatus: " + linkStatus);
                AndroidAutoService.this.notifyCbStatusChanged(linkDevice, linkStatus, errorCode, errorMessage);
            }
            if (linkStatus == AndroidAutoConst.LinkStatus.LINK_STATUS_ACTIVATED) {
                if (!AndroidAutoService.this.mIsStatusActivated) {
                    AndroidAutoService.this.mIsStatusActivated = true;
                    Intent intent = new Intent("ts.androidauto.STATUS_CHANGED");
                    intent.putExtra("isActivated", true);
                    AndroidAutoService.this.mContext.sendBroadcast(intent);
                    return;
                }
                return;
            }
            if (AndroidAutoService.this.mIsStatusActivated) {
                AndroidAutoService.this.mIsStatusActivated = false;
                Intent intent2 = new Intent("ts.androidauto.STATUS_CHANGED");
                intent2.putExtra("isActivated", false);
                AndroidAutoService.this.mContext.sendBroadcast(intent2);
            }
        }

        @Override // com.ts.androidauto.aap.AapPhoneManagerListener
        public void notifyNaviFocusChanged(boolean useNativeNavigation) {
            LogUtil.info("AAPAndroidAutoService", "notifyNaviFocusChanged useNativeNavigation:" + useNativeNavigation);
            AndroidAutoService.this.notifyCbNavigationFocusChanged(useNativeNavigation);
        }

        @Override // com.ts.androidauto.aap.AapPhoneManagerListener
        public void onBatteryStatusNotification(int batteryLevel, int timeRemainingS, boolean batteryCritical) {
            LogUtil.info("AAPAndroidAutoService", "onBatteryStatusNotification batteryLevel:" + batteryLevel + " timeRemainingS:" + timeRemainingS + " batteryCritical:" + batteryCritical);
            AndroidAutoService.this.notifyCbBatteryStatusNotification(batteryLevel, timeRemainingS, batteryCritical);
        }

        @Override // com.ts.androidauto.aap.AapPhoneManagerListener
        public void notifyVoiceSessionNotification(int voiceSessionStatus) {
            LogUtil.info("AAPAndroidAutoService", "notifyVoiceSessionNotification voiceSessionStatus:" + voiceSessionStatus);
            AndroidAutoService.this.notifyCbVoiceSessionNotification(voiceSessionStatus);
        }

        @Override // com.ts.androidauto.aap.AapPhoneManagerListener
        public void notifyMdConnectRequest(AndroidAutoDevice linkDevice) {
            AndroidAutoService.this.mLinkController.userConnectProjectionFromMd(linkDevice);
        }

        @Override // com.ts.androidauto.aap.AapPhoneManagerListener
        public void notifyProfileChange(String address, String profile, BluetoothConst.ProfileStatus status) {
            AndroidAutoService.this.mAutoConnectManager.updateBtProfileStatusChanged(address, profile, status);
        }

        @Override // com.ts.androidauto.aap.AapPhoneManagerListener
        public void notifyMergeDevice(String deviceId, int mergeType, String mergeInfo) {
            if (AndroidAutoService.this.mLinkController != null) {
                AndroidAutoService.this.mLinkController.mergeDevice(deviceId, mergeType, mergeInfo);
            }
        }

        @Override // com.ts.androidauto.aap.AapPhoneManagerListener
        public void notifyRequestVideoFocus(int requestType) {
            AndroidAutoService.this.notifyCbRequestVideoFocus(requestType);
        }

        @Override // com.ts.androidauto.aap.AapPhoneManagerListener
        public void notifyRequestClusterVideoFocus(int requestType) {
            AndroidAutoService.this.notifyCbRequestClusterVideoFocus(requestType);
        }

        @Override // com.ts.androidauto.aap.AapPhoneManagerListener
        public void notifyProjectionBluetoothAddressChanged(String address) {
            AndroidAutoService.this.notifyCbProjectionBluetoothAddressChanged(address);
        }

        @Override // com.ts.androidauto.aap.AapPhoneManagerListener
        public void notifyUpdateProjectionCapability(String brAddr, int linkType, boolean flag) {
            if (AndroidAutoService.this.mLinkController != null) {
                AndroidAutoService.this.mLinkController.updateProjectionCapability(brAddr, linkType, flag);
            }
        }

        @Override // com.ts.androidauto.aap.AapPhoneManagerListener
        public void notifyWirelessAvailable(String btMac, boolean available) {
            AndroidAutoService.this.mAutoConnectManager.onWirelessAvailable(btMac, available);
        }

        @Override // com.ts.androidauto.aap.AapPhoneManagerListener
        public void notifyNavigationStatusChanged(int status) {
            AndroidAutoService.this.notifyCbNavigationStatusChanged(status);
        }

        @Override // com.ts.androidauto.aap.AapPhoneManagerListener
        public void notifyAutoConnectStatusChanged(AndroidAutoDevice linkDevice, AndroidAutoConst.LinkStatus linkStatus, AndroidAutoConst.LinkErrorCode errorCode) {
            AndroidAutoService.this.mAutoConnectManager.onCallbackStatusChanged(linkDevice, linkStatus, errorCode);
        }

        @Override // com.ts.androidauto.aap.AapPhoneManagerListener
        public void notifyUpdateConnectionStatus(AndroidAutoDevice linkDevice, AndroidAutoConst.LinkStatus linkStatus, AndroidAutoConst.LinkErrorCode errorCode) {
            if (AndroidAutoService.this.mLinkController != null) {
                AndroidAutoService.this.mLinkController.updateConnectionStatus(linkDevice, linkStatus, errorCode);
            }
        }

        @Override // com.ts.androidauto.aap.AapPhoneManagerListener
        public void notifyRequestAoaMode(AndroidAutoDevice linkDevice, String aoaSerialNumber) throws RemoteException {
            if (AndroidAutoService.this.mLinkController != null) {
                AndroidAutoService.this.mLinkController.requestAoaMode(linkDevice, aoaSerialNumber);
            }
        }

        @Override // com.ts.androidauto.aap.AapPhoneManagerListener
        public void notifyVideoFocusStatusChanged(int videoFocusStatus) {
            AndroidAutoService.this.notifyCbVideoFocusStatusChanged(videoFocusStatus);
        }

        @Override // com.ts.androidauto.aap.AapPhoneManagerListener
        public void notifyClusterVideoFocusStatusChanged(int requestType) {
            AndroidAutoService.this.notifyCbClusterVideoFocusStatusChanged(requestType);
        }

        @Override // com.ts.androidauto.aap.AapPhoneManagerListener
        public void notifyClusterVideoUpdateUiConfigReply(boolean replyOk) {
            AndroidAutoService.this.notifyCbClusterVideoUpdateUiConfigReply(replyOk);
        }

        @Override // com.ts.androidauto.aap.AapPhoneManagerListener
        public void notifyNavigationState(NavigationStateData navigationStateData) {
            AndroidAutoService.this.notifyCbNavigationState(navigationStateData);
        }

        @Override // com.ts.androidauto.aap.AapPhoneManagerListener
        public void notifyNavigationCurrentPosition(NavigationPositionData navigationPositionData) {
            AndroidAutoService.this.notifyCbNavigationCurrentPosition(navigationPositionData);
        }

        @Override // com.ts.androidauto.aap.AapPhoneManagerListener
        public void notifyMediaStatusChange(int mediaStatus) {
            AndroidAutoService.this.notifyCbMediaStatusChange(mediaStatus);
        }

        @Override // com.ts.androidauto.aap.AapPhoneManagerListener
        public void notifyMediaMetadataChange(MediaMetadata mediaMetadata) {
            AndroidAutoService.this.notifyCbMediaMetadataChange(mediaMetadata);
        }

        @Override // com.ts.androidauto.aap.AapPhoneManagerListener
        public void notifyMediaProgressChange(int progress) {
            AndroidAutoService.this.notifyCbMediaProgressChange(progress);
        }
    };

    void checkPermission(String permission) {
        if (Binder.getCallingUid() != 1000 && getBaseContext().checkCallingOrSelfPermission(permission) != 0) {
            LogUtil.warning("AAPAndroidAutoService", "Caller needs permission '" + permission + "' to call " + Debug.getCaller());
            throw new SecurityException("Access denied to process: " + Binder.getCallingPid() + ", must have permission " + permission);
        }
    }

    /* JADX INFO: renamed from: com.ts.androidauto.projectionservice.AndroidAutoService$1, reason: invalid class name */
    class AnonymousClass1 implements LinkControllerListener {
        AnonymousClass1() {
        }

        @Override // com.ts.androidauto.service.LinkControllerListener
        public void onRequestProjection(final com.ts.androidauto.sdk.aidl.data.AndroidAutoDevice device, final int capability) {
            if (AndroidAutoService.this.mHandler != null) {
                AndroidAutoService.this.mHandler.post(new Runnable() { // from class: com.ts.androidauto.projectionservice.-$$Lambda$AndroidAutoService$1$wYnhc3x92NycCdIKmCfTDuVdQhY
                    @Override // java.lang.Runnable
                    public final void run() {
                        this.f$0.lambda$onRequestProjection$0$AndroidAutoService$1(device, capability);
                    }
                });
            }
        }

        public /* synthetic */ void lambda$onRequestProjection$0$AndroidAutoService$1(com.ts.androidauto.sdk.aidl.data.AndroidAutoDevice device, int capability) {
            LogUtil.debug("AAPAndroidAutoService", "onRequestProjection device:" + device + ",capability:" + capability);
            AndroidAutoService.this.notifyRequestProjectionDevice(device, capability);
        }

        @Override // com.ts.androidauto.service.LinkControllerListener
        public void onLastAvailableDeviceChanged(final String deviceId, final int capability) {
            if (AndroidAutoService.this.mHandler != null) {
                AndroidAutoService.this.mHandler.post(new Runnable() { // from class: com.ts.androidauto.projectionservice.-$$Lambda$AndroidAutoService$1$HWpekp4Wtx11h7iNuvQqIz16IKM
                    @Override // java.lang.Runnable
                    public final void run() {
                        this.f$0.lambda$onLastAvailableDeviceChanged$1$AndroidAutoService$1(deviceId, capability);
                    }
                });
            }
        }

        public /* synthetic */ void lambda$onLastAvailableDeviceChanged$1$AndroidAutoService$1(String deviceId, int capability) {
            LogUtil.debug("AAPAndroidAutoService", "onLastAvailableDeviceChanged deviceId: " + LogEncrypt.MacAddressEncrypt(deviceId) + ",capability:" + capability);
            AndroidAutoService.this.notifyCbLastAvailableDeviceChanged(deviceId, capability);
        }

        @Override // com.ts.androidauto.service.LinkControllerListener
        public void disconnectProjection(final String devId, final int linkType, final int reason) {
            if (AndroidAutoService.this.mHandler != null) {
                AndroidAutoService.this.mHandler.post(new Runnable() { // from class: com.ts.androidauto.projectionservice.-$$Lambda$AndroidAutoService$1$yH24rX8GLlN-CnuTKXiiII8bZNQ
                    @Override // java.lang.Runnable
                    public final void run() {
                        this.f$0.lambda$disconnectProjection$2$AndroidAutoService$1(devId, linkType, reason);
                    }
                });
            }
        }

        public /* synthetic */ void lambda$disconnectProjection$2$AndroidAutoService$1(String devId, int linkType, int reason) {
            if (AndroidAutoService.this.mAndroidAutoManager != null) {
                AndroidAutoService.this.mAndroidAutoManager.disconnectProjection(devId, linkType, reason);
            }
        }

        @Override // com.ts.androidauto.service.LinkControllerListener
        public void connectProjection(final ConnectionInfo connectionInfo) {
            if (AndroidAutoService.this.mHandler != null) {
                AndroidAutoService.this.mHandler.post(new Runnable() { // from class: com.ts.androidauto.projectionservice.-$$Lambda$AndroidAutoService$1$NAzaW5pPSUagIByI9Yj0ZcoUZ8M
                    @Override // java.lang.Runnable
                    public final void run() {
                        this.f$0.lambda$connectProjection$3$AndroidAutoService$1(connectionInfo);
                    }
                });
            }
        }

        public /* synthetic */ void lambda$connectProjection$3$AndroidAutoService$1(ConnectionInfo connectionInfo) {
            if (AndroidAutoService.this.mAndroidAutoManager != null) {
                AndroidAutoService.this.mAndroidAutoManager.connectProjection(connectionInfo);
            }
        }

        @Override // com.ts.androidauto.service.LinkControllerListener
        public void onAaEnableUsbAoaFinished(final boolean result, final String deviceId, final UsbDevice usbDevice) {
            if (AndroidAutoService.this.mHandler != null) {
                AndroidAutoService.this.mHandler.post(new Runnable() { // from class: com.ts.androidauto.projectionservice.-$$Lambda$AndroidAutoService$1$r8SmI_LKXkuumwMKNvd8jeS85Ec
                    @Override // java.lang.Runnable
                    public final void run() {
                        this.f$0.lambda$onAaEnableUsbAoaFinished$4$AndroidAutoService$1(result, deviceId, usbDevice);
                    }
                });
            }
        }

        public /* synthetic */ void lambda$onAaEnableUsbAoaFinished$4$AndroidAutoService$1(boolean result, String deviceId, UsbDevice usbDevice) {
            if (AndroidAutoService.this.mAndroidAutoManager != null) {
                AndroidAutoService.this.mAndroidAutoManager.onAaEnableUsbAoaFinished(result, deviceId, usbDevice);
            }
        }

        @Override // com.ts.androidauto.service.LinkControllerListener
        public void setOtherProjectionActive(final boolean active) {
            if (AndroidAutoService.this.mHandler != null) {
                AndroidAutoService.this.mHandler.post(new Runnable() { // from class: com.ts.androidauto.projectionservice.-$$Lambda$AndroidAutoService$1$0VKTPFNp4S1AbFou1H3fYL_Mwj0
                    @Override // java.lang.Runnable
                    public final void run() {
                        this.f$0.lambda$setOtherProjectionActive$5$AndroidAutoService$1(active);
                    }
                });
            }
        }

        public /* synthetic */ void lambda$setOtherProjectionActive$5$AndroidAutoService$1(boolean active) {
            if (AndroidAutoService.this.mAndroidAutoManager != null) {
                AndroidAutoService.this.mAndroidAutoManager.setOtherProjectionActive(active);
            }
        }

        @Override // com.ts.androidauto.service.LinkControllerListener
        public void onDevicesMerged(final com.ts.androidauto.sdk.aidl.data.AndroidAutoDevice device) {
            if (AndroidAutoService.this.mHandler != null) {
                AndroidAutoService.this.mHandler.post(new Runnable() { // from class: com.ts.androidauto.projectionservice.-$$Lambda$AndroidAutoService$1$PUZ5-2AVk75By3t1cPVU2gbQIHA
                    @Override // java.lang.Runnable
                    public final void run() {
                        this.f$0.lambda$onDevicesMerged$7$AndroidAutoService$1(device);
                    }
                });
            }
        }

        public /* synthetic */ void lambda$onDevicesMerged$7$AndroidAutoService$1(final com.ts.androidauto.sdk.aidl.data.AndroidAutoDevice device) {
            AndroidAutoService.this.broadAndroidAutoDeviceList(new Consumer.ReConsumer() { // from class: com.ts.androidauto.projectionservice.-$$Lambda$AndroidAutoService$1$-vlQYKzRb0MFZ1fQaLdkI70KkjE
                @Override // com.ts.androidauto.service.common.Consumer.ReConsumer
                public final void accept(Object obj) throws RemoteException {
                    ((IAndroidAutoDeviceListCallback) obj).onDevicesMerged(device);
                }
            });
        }

        @Override // com.ts.androidauto.service.LinkControllerListener
        public void onDeviceStatusChanged(final com.ts.androidauto.sdk.aidl.data.AndroidAutoDevice device) {
            if (AndroidAutoService.this.mHandler != null) {
                AndroidAutoService.this.mHandler.post(new Runnable() { // from class: com.ts.androidauto.projectionservice.-$$Lambda$AndroidAutoService$1$iWwun8B4yCJh2_gDlRbXfRc_nIw
                    @Override // java.lang.Runnable
                    public final void run() {
                        this.f$0.lambda$onDeviceStatusChanged$9$AndroidAutoService$1(device);
                    }
                });
            }
        }

        public /* synthetic */ void lambda$onDeviceStatusChanged$9$AndroidAutoService$1(final com.ts.androidauto.sdk.aidl.data.AndroidAutoDevice device) {
            AndroidAutoService.this.broadAndroidAutoDeviceList(new Consumer.ReConsumer() { // from class: com.ts.androidauto.projectionservice.-$$Lambda$AndroidAutoService$1$7b4JG7FgfQGsb1ZX6ysYkkO9zlw
                @Override // com.ts.androidauto.service.common.Consumer.ReConsumer
                public final void accept(Object obj) throws RemoteException {
                    ((IAndroidAutoDeviceListCallback) obj).onDeviceStatusChanged(device);
                }
            });
        }

        @Override // com.ts.androidauto.service.LinkControllerListener
        public void onAndroidDetachedOverUsb(final com.ts.androidauto.sdk.aidl.data.AndroidAutoDevice device) {
            if (AndroidAutoService.this.mHandler != null) {
                AndroidAutoService.this.mHandler.post(new Runnable() { // from class: com.ts.androidauto.projectionservice.-$$Lambda$AndroidAutoService$1$WdilMpfBr3bfnQgOdmcA_UWjv4s
                    @Override // java.lang.Runnable
                    public final void run() {
                        this.f$0.lambda$onAndroidDetachedOverUsb$11$AndroidAutoService$1(device);
                    }
                });
            }
        }

        public /* synthetic */ void lambda$onAndroidDetachedOverUsb$11$AndroidAutoService$1(final com.ts.androidauto.sdk.aidl.data.AndroidAutoDevice device) {
            AndroidAutoService.this.broadAndroidAutoDeviceList(new Consumer.ReConsumer() { // from class: com.ts.androidauto.projectionservice.-$$Lambda$AndroidAutoService$1$Q5ckyhTWwMNTfcu0AnRhtyNSogc
                @Override // com.ts.androidauto.service.common.Consumer.ReConsumer
                public final void accept(Object obj) throws RemoteException {
                    ((IAndroidAutoDeviceListCallback) obj).onAndroidDetachedOverUsb(device);
                }
            });
        }
    }

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        this.mContext = this;
        LogUtil.info("AAPAndroidAutoService", "AndroidAutoService OnCreate:" + this.mContext);
        this.mLinkController = LinkController.getInstance();
        this.mAndroidAutoManager = new AapPhoneManager();
        this.mAutoConnectManager = AutoConnectManager.getInstance();
        HandlerThread handlerThread = new HandlerThread("AAPAndroidAutoService");
        this.mHandlerThread = handlerThread;
        handlerThread.start();
        MessageHandler messageHandler = new MessageHandler(this.mHandlerThread.getLooper());
        this.mHandler = messageHandler;
        messageHandler.sendEmptyMessageDelayed(1, 5000L);
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int flags, int startId) {
        LogUtil.info("AAPAndroidAutoService", "onStartCommand() ");
        int result = super.onStartCommand(intent, flags, startId);
        LogUtil.info("AAPAndroidAutoService", "super.onStartCommand flags : " + flags + " startId : " + startId + " result : " + result);
        return 1;
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        LogUtil.info("AAPAndroidAutoService", "onBind. ");
        return this.mLinkCommandBinder;
    }

    @Override // android.app.Service, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    @Override // android.app.Service
    public void onDestroy() {
        super.onDestroy();
        LogUtil.info("AAPAndroidAutoService", "onDestroy.");
        release();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void init() {
        if (!this.mIsInit) {
            LogUtil.info("AAPAndroidAutoService", "init.");
            this.mAutoConnectManager.init(this.mContext);
            this.mAndroidAutoManager.addAapPhoneManagerListener(this.mAapPhoneManagerListener);
            this.mAndroidAutoManager.init(this.mContext);
            this.mLinkController.init(this.mContext);
            this.mLinkController.registLinkControllerListener(this.linkControllerListener);
            this.mIsInit = true;
            sendBroadcast(new Intent(ACTION_ANDROID_AUTO_START_FINISH));
        }
    }

    private void release() {
        LogUtil.info("AAPAndroidAutoService", "release ");
        Handler handler = this.mHandler;
        if (handler != null) {
            handler.removeMessages(1);
        }
        AapPhoneManager aapPhoneManager = this.mAndroidAutoManager;
        if (aapPhoneManager != null) {
            AndroidAutoDevice device = aapPhoneManager.getActivitedDeviceExceptIpod();
            if (device != null) {
                LogUtil.info("AAPAndroidAutoService", "SmartProjection has connected device need to disconnect : " + device);
                this.mAndroidAutoManager.startDisConnect(device, StatusConst.ConnectErrorCode.RELEASE_APP);
            }
            this.mAndroidAutoManager.removeAapPhoneManagerListener(this.mAapPhoneManagerListener);
            this.mAndroidAutoManager.deInit();
        }
        LinkController linkController = this.mLinkController;
        if (linkController != null) {
            linkController.unRegistLinkControllerListener();
            this.mLinkController = null;
        }
        this.mIsInit = false;
    }

    private class MessageHandler extends Handler {
        public static final String TAG = "AAPAndroidAutoService";

        public MessageHandler(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 1) {
                AndroidAutoService.this.init();
            }
        }
    }

    private class AndroidAutoServiceImpl extends IAndroidAutoService.Stub {
        private AndroidAutoServiceImpl() {
        }

        /* synthetic */ AndroidAutoServiceImpl(AndroidAutoService x0, AnonymousClass1 x1) {
            this();
        }

        @Override // com.ts.androidauto.sdk.aidl.IAndroidAutoService
        public void addServiceCallback(IAndroidAutoServiceCallback iAndroidAutoServiceCallback) throws RemoteException {
            LogUtil.info("AAPAndroidAutoService", "addServiceCallback!");
            AndroidAutoService.this.checkPermission("car.permission.ANDROIDAUTO");
            if (AndroidAutoService.this.mIAndroidAutoServiceCallbackList != null) {
                synchronized (AndroidAutoService.this.mIAndroidAutoServiceCallbackList) {
                    AndroidAutoService.this.mIAndroidAutoServiceCallbackList.register(iAndroidAutoServiceCallback);
                }
            }
        }

        @Override // com.ts.androidauto.sdk.aidl.IAndroidAutoService
        public void removeServiceCallback(IAndroidAutoServiceCallback iAndroidAutoServiceCallback) throws RemoteException {
            LogUtil.info("AAPAndroidAutoService", "removeServiceCallback!");
            AndroidAutoService.this.checkPermission("car.permission.ANDROIDAUTO");
            if (AndroidAutoService.this.mIAndroidAutoServiceCallbackList != null) {
                synchronized (AndroidAutoService.this.mIAndroidAutoServiceCallbackList) {
                    AndroidAutoService.this.mIAndroidAutoServiceCallbackList.unregister(iAndroidAutoServiceCallback);
                }
            }
        }

        @Override // com.ts.androidauto.sdk.aidl.IAndroidAutoService
        public void addDeviceListCallback(IAndroidAutoDeviceListCallback iAndroidAutoDeviceListCallback) throws RemoteException {
            LogUtil.info("AAPAndroidAutoService", "addDeviceListCallback!");
            AndroidAutoService.this.checkPermission("car.permission.ANDROIDAUTO_DEVICE_LIST");
            if (AndroidAutoService.this.mAndroidAutoDeviceListCallbackList != null) {
                synchronized (AndroidAutoService.this.mAndroidAutoDeviceListCallbackList) {
                    AndroidAutoService.this.mAndroidAutoDeviceListCallbackList.register(iAndroidAutoDeviceListCallback);
                }
            }
        }

        @Override // com.ts.androidauto.sdk.aidl.IAndroidAutoService
        public void removeDeviceListCallback(IAndroidAutoDeviceListCallback iAndroidAutoDeviceListCallback) throws RemoteException {
            LogUtil.info("AAPAndroidAutoService", "removeServiceCallback!");
            AndroidAutoService.this.checkPermission("car.permission.ANDROIDAUTO_DEVICE_LIST");
            if (AndroidAutoService.this.mAndroidAutoDeviceListCallbackList != null) {
                synchronized (AndroidAutoService.this.mAndroidAutoDeviceListCallbackList) {
                    AndroidAutoService.this.mAndroidAutoDeviceListCallbackList.unregister(iAndroidAutoDeviceListCallback);
                }
            }
        }

        @Override // com.ts.androidauto.sdk.aidl.IAndroidAutoService
        public void addSessionStatusCallback(IAndroidAutoSessionStatusCallback iAndroidAutoSessionStatusCallback) throws RemoteException {
            LogUtil.info("AAPAndroidAutoService", "addSessionStatusCallback!");
            AndroidAutoService.this.checkPermission("car.permission.ANDROIDAUTO");
            if (AndroidAutoService.this.mIAndroidAutoSessionStatusCallbackList != null) {
                synchronized (AndroidAutoService.this.mIAndroidAutoSessionStatusCallbackList) {
                    AndroidAutoService.this.mIAndroidAutoSessionStatusCallbackList.register(iAndroidAutoSessionStatusCallback);
                }
            }
        }

        @Override // com.ts.androidauto.sdk.aidl.IAndroidAutoService
        public void removeSessionStatusCallback(IAndroidAutoSessionStatusCallback iAndroidAutoSessionStatusCallback) throws RemoteException {
            LogUtil.info("AAPAndroidAutoService", "removeSessionStatusCallback!");
            AndroidAutoService.this.checkPermission("car.permission.ANDROIDAUTO");
            if (AndroidAutoService.this.mIAndroidAutoSessionStatusCallbackList != null) {
                synchronized (AndroidAutoService.this.mIAndroidAutoSessionStatusCallbackList) {
                    AndroidAutoService.this.mIAndroidAutoSessionStatusCallbackList.unregister(iAndroidAutoSessionStatusCallback);
                }
            }
        }

        @Override // com.ts.androidauto.sdk.aidl.IAndroidAutoService
        public void addAppCallback(IAndroidAutoAppCallback iAndroidAutoAppCallback) throws RemoteException {
            LogUtil.info("AAPAndroidAutoService", "addAppCallback!");
            AndroidAutoService.this.checkPermission("car.permission.ANDROIDAUTO_APP");
            if (AndroidAutoService.this.mIAndroidAutoAppCallbackList != null) {
                synchronized (AndroidAutoService.this.mIAndroidAutoAppCallbackList) {
                    AndroidAutoService.this.mIAndroidAutoAppCallbackList.register(iAndroidAutoAppCallback);
                }
            }
        }

        @Override // com.ts.androidauto.sdk.aidl.IAndroidAutoService
        public void removeAppCallback(IAndroidAutoAppCallback iAndroidAutoAppCallback) throws RemoteException {
            LogUtil.info("AAPAndroidAutoService", "removeAppCallback!");
            AndroidAutoService.this.checkPermission("car.permission.ANDROIDAUTO_APP");
            if (AndroidAutoService.this.mIAndroidAutoAppCallbackList != null) {
                synchronized (AndroidAutoService.this.mIAndroidAutoAppCallbackList) {
                    AndroidAutoService.this.mIAndroidAutoAppCallbackList.unregister(iAndroidAutoAppCallback);
                }
            }
        }

        @Override // com.ts.androidauto.sdk.aidl.IAndroidAutoService
        public void addBatteryStatusCallback(IBatteryStatusCallback iBatteryStatusCallback) throws RemoteException {
            LogUtil.info("AAPAndroidAutoService", "addLinkCallback!");
            AndroidAutoService.this.checkPermission("car.permission.ANDROIDAUTO_APP");
            if (AndroidAutoService.this.mIBatteryStatusCallbackList != null) {
                synchronized (AndroidAutoService.this.mIBatteryStatusCallbackList) {
                    AndroidAutoService.this.mIBatteryStatusCallbackList.register(iBatteryStatusCallback);
                }
            }
        }

        @Override // com.ts.androidauto.sdk.aidl.IAndroidAutoService
        public void removeBatteryStatusCallback(IBatteryStatusCallback iBatteryStatusCallback) throws RemoteException {
            LogUtil.info("AAPAndroidAutoService", "addLinkCallback!");
            AndroidAutoService.this.checkPermission("car.permission.ANDROIDAUTO_APP");
            if (AndroidAutoService.this.mIBatteryStatusCallbackList != null) {
                synchronized (AndroidAutoService.this.mIBatteryStatusCallbackList) {
                    AndroidAutoService.this.mIBatteryStatusCallbackList.unregister(iBatteryStatusCallback);
                }
            }
        }

        @Override // com.ts.androidauto.sdk.aidl.IAndroidAutoService
        public void addMediaDataCallback(IMediaDataCallback iMediaDataCallback) throws RemoteException {
            LogUtil.info("AAPAndroidAutoService", "addLinkCallback!");
            AndroidAutoService.this.checkPermission("car.permission.ANDROIDAUTO_APP");
            if (AndroidAutoService.this.mIMediaDataCallbackList != null) {
                synchronized (AndroidAutoService.this.mIMediaDataCallbackList) {
                    AndroidAutoService.this.mIMediaDataCallbackList.register(iMediaDataCallback);
                }
            }
        }

        @Override // com.ts.androidauto.sdk.aidl.IAndroidAutoService
        public void removeMediaDataCallback(IMediaDataCallback iMediaDataCallback) throws RemoteException {
            LogUtil.info("AAPAndroidAutoService", "addLinkCallback!");
            AndroidAutoService.this.checkPermission("car.permission.ANDROIDAUTO_APP");
            if (AndroidAutoService.this.mIMediaDataCallbackList != null) {
                synchronized (AndroidAutoService.this.mIMediaDataCallbackList) {
                    AndroidAutoService.this.mIMediaDataCallbackList.unregister(iMediaDataCallback);
                }
            }
        }

        @Override // com.ts.androidauto.sdk.aidl.IAndroidAutoService
        public void addNavigationDataCallback(INavigationDataCallback iNavigationDataCallback) throws RemoteException {
            LogUtil.info("AAPAndroidAutoService", "addLinkCallback!");
            AndroidAutoService.this.checkPermission("car.permission.ANDROIDAUTO_APP");
            if (AndroidAutoService.this.mINavigationDataCallbackList != null) {
                synchronized (AndroidAutoService.this.mINavigationDataCallbackList) {
                    AndroidAutoService.this.mINavigationDataCallbackList.register(iNavigationDataCallback);
                }
            }
        }

        @Override // com.ts.androidauto.sdk.aidl.IAndroidAutoService
        public void removeNavigationDataCallback(INavigationDataCallback iNavigationDataCallback) throws RemoteException {
            LogUtil.info("AAPAndroidAutoService", "addLinkCallback!");
            AndroidAutoService.this.checkPermission("car.permission.ANDROIDAUTO_APP");
            if (AndroidAutoService.this.mINavigationDataCallbackList != null) {
                synchronized (AndroidAutoService.this.mINavigationDataCallbackList) {
                    AndroidAutoService.this.mINavigationDataCallbackList.unregister(iNavigationDataCallback);
                }
            }
        }

        @Override // com.ts.androidauto.sdk.aidl.IAndroidAutoService
        public void setSurface(Surface surface, int width, int height) throws RemoteException {
            LogUtil.info("AAPAndroidAutoService", "setSurface -->>" + surface + " width:" + width + " height:" + height);
            AndroidAutoService.this.checkPermission("car.permission.ANDROIDAUTO");
            if (AndroidAutoService.this.mIsVehiclePowerStart) {
                if (AndroidAutoService.this.mAndroidAutoManager != null) {
                    if (surface != null) {
                        AndroidAutoService.this.mAndroidAutoManager.show(surface, width, height);
                        return;
                    } else {
                        AndroidAutoService.this.mAndroidAutoManager.hide();
                        return;
                    }
                }
                return;
            }
            LogUtil.info("AAPAndroidAutoService", "vehicle power not start, so do nothing!");
        }

        @Override // com.ts.androidauto.sdk.aidl.IAndroidAutoService
        public void setClusterSurface(Surface surface, int width, int height) throws RemoteException {
            LogUtil.info("AAPAndroidAutoService", "setClusterSurface -->>" + surface + " width:" + width + " height:" + height);
            AndroidAutoService.this.checkPermission("car.permission.ANDROIDAUTO");
            if (AndroidAutoService.this.mIsVehiclePowerStart) {
                if (AndroidAutoService.this.mAndroidAutoManager != null) {
                    if (surface != null) {
                        AndroidAutoService.this.mAndroidAutoManager.showCluster(surface, width, height);
                        return;
                    } else {
                        AndroidAutoService.this.mAndroidAutoManager.hideCluster();
                        return;
                    }
                }
                return;
            }
            LogUtil.info("AAPAndroidAutoService", "vehicle power not start, so do nothing!");
        }

        @Override // com.ts.androidauto.sdk.aidl.IAndroidAutoService
        public void screenTouchEvent(MotionEvent event) throws RemoteException {
            AndroidAutoService.this.checkPermission("car.permission.ANDROIDAUTO");
            if (AndroidAutoService.this.mIsVehiclePowerStart) {
                if (AndroidAutoService.this.mAndroidAutoManager != null && event != null) {
                    AndroidAutoService.this.mAndroidAutoManager.screenTouchEvent(event);
                    return;
                } else {
                    LogUtil.info("AAPAndroidAutoService", "screenTouchEvent has no mAndroidAutoManager ! ");
                    return;
                }
            }
            LogUtil.info("AAPAndroidAutoService", "vehicle power not start, so do nothing!");
        }

        @Override // com.ts.androidauto.sdk.aidl.IAndroidAutoService
        public void sendKeyEvent(int keyCode, int keyAction) throws RemoteException {
            AndroidAutoService.this.checkPermission("car.permission.ANDROIDAUTO_APP");
            if (AndroidAutoService.this.mIsVehiclePowerStart) {
                if (AndroidAutoService.this.mAndroidAutoManager != null) {
                    AndroidAutoService.this.mAndroidAutoManager.sendKeyEvent(DataConvert.convertGalKeyCode(keyCode), keyAction);
                    LogUtil.info("AAPAndroidAutoService", "sendKeyEvent keyCode:" + keyCode + " keyAction:" + keyAction);
                    return;
                }
                LogUtil.info("AAPAndroidAutoService", "sendKeyEvent has no mAndroidAutoManager ! ");
                return;
            }
            LogUtil.info("AAPAndroidAutoService", "vehicle power not start, so do nothing!");
        }

        @Override // com.ts.androidauto.sdk.aidl.IAndroidAutoService
        public void sendKeyLongPressEvent(int keyCode) throws RemoteException {
            AndroidAutoService.this.checkPermission("car.permission.ANDROIDAUTO_APP");
            if (AndroidAutoService.this.mIsVehiclePowerStart) {
                if (AndroidAutoService.this.mAndroidAutoManager != null) {
                    AndroidAutoService.this.mAndroidAutoManager.sendKeyLongPressEvent(DataConvert.convertGalKeyCode(keyCode));
                    LogUtil.info("AAPAndroidAutoService", "sendKeyLongPressEvent keyCode: " + keyCode);
                    return;
                }
                LogUtil.info("AAPAndroidAutoService", "sendKeyLongPressEvent has no mAndroidAutoManager ! ");
                return;
            }
            LogUtil.info("AAPAndroidAutoService", "vehicle power not start, so do nothing!");
        }

        @Override // com.ts.androidauto.sdk.aidl.IAndroidAutoService
        public void setVehicleInfo(VehicleInfo vehicleInfo) {
            LogUtil.info("AAPAndroidAutoService", "sendVehicleInfo vehicleInfo=" + vehicleInfo + " alreadySend=" + AndroidAutoService.this.mIsSetVehicleInfo);
            AndroidAutoService.this.checkPermission("car.permission.ANDROIDAUTO");
            if (AndroidAutoService.this.mAndroidAutoManager != null && vehicleInfo != null && !AndroidAutoService.this.mIsSetVehicleInfo) {
                LogUtil.info("AAPAndroidAutoService", "sendVehicleInfo");
                if (AndroidAutoService.this.mHandler != null) {
                    AndroidAutoService.this.mHandler.removeMessages(1);
                }
                com.ts.androidauto.aap.common.VehicleInfo aapVehicleInfo = DataConvert.toLocalVehicleInfo(vehicleInfo);
                AndroidAutoService.this.mAndroidAutoManager.sendVehicleInfo(aapVehicleInfo);
                AndroidAutoService.this.mIsSetVehicleInfo = true;
                if (AndroidAutoService.this.mHandler != null) {
                    AndroidAutoService.this.mHandler.sendEmptyMessage(1);
                    return;
                }
                return;
            }
            LogUtil.info("AAPAndroidAutoService", "sendVehicleInfo has no mAndroidAutoManager ! ");
        }

        @Override // com.ts.androidauto.sdk.aidl.IAndroidAutoService
        public int getMusicStatus() throws RemoteException {
            LogUtil.info("AAPAndroidAutoService", "getMusicStatus mAndroidAutoManager : " + AndroidAutoService.this.mAndroidAutoManager);
            AndroidAutoService.this.checkPermission("car.permission.ANDROIDAUTO_APP");
            if (AndroidAutoService.this.mIsVehiclePowerStart) {
                if (AndroidAutoService.this.mAndroidAutoManager != null) {
                    return AndroidAutoService.this.mAndroidAutoManager.getMusicStatus();
                }
                return AndroidAutoConst.MusicStatus.MUSIC_STATUS_NOT_START.ordinal();
            }
            LogUtil.info("AAPAndroidAutoService", "vehicle power not start, so do nothing!");
            return AndroidAutoConst.MusicStatus.MUSIC_STATUS_NOT_START.ordinal();
        }

        @Override // com.ts.androidauto.sdk.aidl.IAndroidAutoService
        public void play() throws RemoteException {
            LogUtil.info("AAPAndroidAutoService", "play mAndroidAutoManager : " + AndroidAutoService.this.mAndroidAutoManager);
            AndroidAutoService.this.checkPermission("car.permission.ANDROIDAUTO_APP");
            if (AndroidAutoService.this.mIsVehiclePowerStart) {
                if (AndroidAutoService.this.mAndroidAutoManager != null) {
                    AndroidAutoService.this.mAndroidAutoManager.play();
                    return;
                }
                return;
            }
            LogUtil.info("AAPAndroidAutoService", "vehicle power not start, so do nothing!");
        }

        @Override // com.ts.androidauto.sdk.aidl.IAndroidAutoService
        public void pause() throws RemoteException {
            LogUtil.info("AAPAndroidAutoService", "pause mAndroidAutoManager : " + AndroidAutoService.this.mAndroidAutoManager);
            AndroidAutoService.this.checkPermission("car.permission.ANDROIDAUTO_APP");
            if (AndroidAutoService.this.mIsVehiclePowerStart) {
                if (AndroidAutoService.this.mAndroidAutoManager != null) {
                    AndroidAutoService.this.mAndroidAutoManager.pause();
                    return;
                }
                return;
            }
            LogUtil.info("AAPAndroidAutoService", "vehicle power not start, so do nothing!");
        }

        @Override // com.ts.androidauto.sdk.aidl.IAndroidAutoService
        public void next() throws RemoteException {
            LogUtil.info("AAPAndroidAutoService", "next mAndroidAutoManager : " + AndroidAutoService.this.mAndroidAutoManager);
            AndroidAutoService.this.checkPermission("car.permission.ANDROIDAUTO_APP");
            if (AndroidAutoService.this.mIsVehiclePowerStart) {
                if (AndroidAutoService.this.mAndroidAutoManager != null) {
                    AndroidAutoService.this.mAndroidAutoManager.next();
                    return;
                }
                return;
            }
            LogUtil.info("AAPAndroidAutoService", "vehicle power not start, so do nothing!");
        }

        @Override // com.ts.androidauto.sdk.aidl.IAndroidAutoService
        public void previous() throws RemoteException {
            LogUtil.info("AAPAndroidAutoService", "previous mAndroidAutoManager : " + AndroidAutoService.this.mAndroidAutoManager);
            AndroidAutoService.this.checkPermission("car.permission.ANDROIDAUTO_APP");
            if (AndroidAutoService.this.mIsVehiclePowerStart) {
                if (AndroidAutoService.this.mAndroidAutoManager != null) {
                    AndroidAutoService.this.mAndroidAutoManager.previous();
                    return;
                }
                return;
            }
            LogUtil.info("AAPAndroidAutoService", "vehicle power not start, so do nothing!");
        }

        @Override // com.ts.androidauto.sdk.aidl.IAndroidAutoService
        public int getAndroidAutoSessionStatus() throws RemoteException {
            AndroidAutoService.this.checkPermission("car.permission.ANDROIDAUTO_APP");
            int linkStatus = AndroidAutoConst.LinkStatus.LINK_STATUS_DEACTIVATED.ordinal();
            if (AndroidAutoService.this.mIsVehiclePowerStart) {
                if (AndroidAutoService.this.mAndroidAutoManager != null) {
                    linkStatus = AndroidAutoService.this.mAndroidAutoManager.getLinkStatus();
                }
                LogUtil.debug("AAPAndroidAutoService", "getLinkStatus:" + linkStatus);
                return linkStatus;
            }
            LogUtil.info("AAPAndroidAutoService", "vehicle power not start, so do nothing!");
            return linkStatus;
        }

        @Override // com.ts.androidauto.sdk.aidl.IAndroidAutoService
        public void requestUi(int mode) throws RemoteException {
            AndroidAutoService.this.checkPermission("car.permission.ANDROIDAUTO_APP");
            if (AndroidAutoService.this.mIsVehiclePowerStart) {
                if (AndroidAutoService.this.mAndroidAutoManager != null) {
                    AndroidAutoService.this.mAndroidAutoManager.requestUi(mode);
                    return;
                }
                return;
            }
            LogUtil.info("AAPAndroidAutoService", "vehicle power not start, so do nothing!");
        }

        @Override // com.ts.androidauto.sdk.aidl.IAndroidAutoService
        public void setNativeAppStates(int appState, boolean enabled) throws RemoteException {
            LogUtil.info("AAPAndroidAutoService", "setNativeAppStates enabled: " + enabled);
            AndroidAutoService.this.checkPermission("car.permission.ANDROIDAUTO");
            if (AndroidAutoService.this.mAndroidAutoManager != null) {
                AndroidAutoService.this.mAndroidAutoManager.sendNativeNaviStatus(enabled);
            }
        }

        @Override // com.ts.androidauto.sdk.aidl.IAndroidAutoService
        public String getLastAvailableDevice() throws RemoteException {
            LogUtil.info("AAPAndroidAutoService", "getLastAvailableDevice");
            AndroidAutoService.this.checkPermission("car.permission.ANDROIDAUTO_APP");
            if (AndroidAutoService.this.mLinkController != null) {
                return AndroidAutoService.this.mLinkController.getLastAvailableDevice();
            }
            return null;
        }

        @Override // com.ts.androidauto.sdk.aidl.IAndroidAutoService
        public int getLastAvailableCapability(String deviceId) throws RemoteException {
            LogUtil.info("AAPAndroidAutoService", "getLastAvailableCapability deviceId:" + deviceId);
            AndroidAutoService.this.checkPermission("car.permission.ANDROIDAUTO_APP");
            if (AndroidAutoService.this.mLinkController != null) {
                return AndroidAutoService.this.mLinkController.getLastAvailableCapability(deviceId);
            }
            return 0;
        }

        @Override // com.ts.androidauto.sdk.aidl.IAndroidAutoService
        public List<String> getAvailableDevices() throws RemoteException {
            LogUtil.info("AAPAndroidAutoService", "getAvailableDevices");
            AndroidAutoService.this.checkPermission("car.permission.ANDROIDAUTO_APP");
            ArrayList<String> availableDeviceList = new ArrayList<>();
            if (AndroidAutoService.this.mLinkController != null) {
                ArrayList<String> availableDeviceList2 = AndroidAutoService.this.mLinkController.getAvailableDevices();
                return availableDeviceList2;
            }
            return availableDeviceList;
        }

        @Override // com.ts.androidauto.sdk.aidl.IAndroidAutoService
        public int getVideoFocusStatus() throws RemoteException {
            LogUtil.info("AAPAndroidAutoService", "getVideoFocusStatus");
            AndroidAutoService.this.checkPermission("car.permission.ANDROIDAUTO_APP");
            if (AndroidAutoService.this.mAndroidAutoManager != null) {
                return AndroidAutoService.this.mAndroidAutoManager.getCurrVideoFocusStatus();
            }
            return ResourceConst.VideoFocusStatus.VIDEOFOCUS_STATUS_NATIVE.ordinal();
        }

        @Override // com.ts.androidauto.sdk.aidl.IAndroidAutoService
        public String getProjectionBluetoothAddress() throws RemoteException {
            AndroidAutoDevice device;
            LogUtil.info("AAPAndroidAutoService", "getProjectionBluetoothAddress");
            AndroidAutoService.this.checkPermission("car.permission.ANDROIDAUTO_APP");
            if (AndroidAutoService.this.mAndroidAutoManager != null && (device = AndroidAutoService.this.mAndroidAutoManager.getActivitedDeviceExceptIpod()) != null) {
                return device.getBtAddr();
            }
            return null;
        }

        @Override // com.ts.androidauto.sdk.aidl.IAndroidAutoService
        public boolean getAndroidAutoAppStatus(int appType) throws RemoteException {
            LogUtil.info("AAPAndroidAutoService", "getAndroidAutoAppStatus appType:" + appType);
            AndroidAutoService.this.checkPermission("car.permission.ANDROIDAUTO_APP");
            if (appType == AndroidAutoConst.AppType.APP_TYPE_ROUTE_GUIDANCE.ordinal()) {
                return AndroidAutoService.this.mIsUseNativeNavigation;
            }
            if (appType == AndroidAutoConst.AppType.APP_TYPE_VR.ordinal()) {
                return AndroidAutoService.this.mIsVrEnable;
            }
            return false;
        }

        @Override // com.ts.androidauto.sdk.aidl.IAndroidAutoService
        public com.ts.androidauto.sdk.aidl.data.MediaMetadata getMediaMetadata() throws RemoteException {
            LogUtil.info("AAPAndroidAutoService", "getMediaMetadata");
            AndroidAutoService.this.checkPermission("car.permission.ANDROIDAUTO_APP");
            if (AndroidAutoService.this.mAndroidAutoManager != null) {
                return DataConvert.dataToMediaMetadata(AndroidAutoService.this.mAndroidAutoManager.getMediaMetadata());
            }
            return null;
        }

        @Override // com.ts.androidauto.sdk.aidl.IAndroidAutoService
        public int getMediaProgress() throws RemoteException {
            LogUtil.info("AAPAndroidAutoService", "getMediaProgress");
            AndroidAutoService.this.checkPermission("car.permission.ANDROIDAUTO_APP");
            if (AndroidAutoService.this.mAndroidAutoManager != null) {
                return AndroidAutoService.this.mAndroidAutoManager.getMediaProgress();
            }
            return 0;
        }

        @Override // com.ts.androidauto.sdk.aidl.IAndroidAutoService
        public void setNativePhoneCallStatus(int status) throws RemoteException {
            LogUtil.info("AAPAndroidAutoService", "setNativePhoneCallStatus status:" + status);
            AndroidAutoService.this.checkPermission("car.permission.ANDROIDAUTO");
            if (AndroidAutoService.this.mAndroidAutoManager != null) {
                AndroidAutoService.this.mAndroidAutoManager.setNativePhoneCallStatus(status);
            }
        }

        @Override // com.ts.androidauto.sdk.aidl.IAndroidAutoService
        public void connectAndroidAuto(String devId, boolean isUserRequest) throws RemoteException {
            LogUtil.debug("AAPAndroidAutoService", "connectAndroidAuto, devId:" + LogEncrypt.MacAddressEncrypt(devId) + ", isUserRequest:" + isUserRequest);
            AndroidAutoService.this.checkPermission("car.permission.ANDROIDAUTO_DEVICE_LIST");
            if (AndroidAutoService.this.mLinkController != null) {
                AndroidAutoService.this.mLinkController.connectAndroidAuto(devId, isUserRequest);
            }
        }

        @Override // com.ts.androidauto.sdk.aidl.IAndroidAutoService
        public void connectAndroidAutoByRequest(ConnectRequest request) throws RemoteException {
            LogUtil.debug("AAPAndroidAutoService", "connectAndroidAuto, ConnectRequest:" + request);
            AndroidAutoService.this.checkPermission("car.permission.ANDROIDAUTO_DEVICE_LIST");
            if (AndroidAutoService.this.mLinkController != null) {
                AndroidAutoService.this.mLinkController.connectAndroidAuto(request);
            }
        }

        @Override // com.ts.androidauto.sdk.aidl.IAndroidAutoService
        public void disconnectAndroidAuto(String devId) throws RemoteException {
            LogUtil.debug("AAPAndroidAutoService", "disconnectAndroidAuto, devId:" + LogEncrypt.MacAddressEncrypt(devId));
            AndroidAutoService.this.checkPermission("car.permission.ANDROIDAUTO_DEVICE_LIST");
            if (AndroidAutoService.this.mLinkController != null) {
                AndroidAutoService.this.mLinkController.disconnectAndroidAuto(devId);
            }
        }

        @Override // com.ts.androidauto.sdk.aidl.IAndroidAutoService
        public void deleteAndroidAutoDevice(String devId) {
            LogUtil.debug("AAPAndroidAutoService", "deleteAndroidAutoDevice, devId:" + LogEncrypt.MacAddressEncrypt(devId));
            AndroidAutoService.this.checkPermission("car.permission.ANDROIDAUTO_DEVICE_LIST");
            if (AndroidAutoService.this.mLinkController != null) {
                AndroidAutoService.this.mLinkController.deleteAndroidAutoDevice(devId);
            }
        }

        @Override // com.ts.androidauto.sdk.aidl.IAndroidAutoService
        public com.ts.androidauto.sdk.aidl.data.AndroidAutoDevice getAndroidAutoDevice(String devId) {
            LogUtil.debug("AAPAndroidAutoService", "getAndroidAutoDevice, devId:" + LogEncrypt.MacAddressEncrypt(devId));
            AndroidAutoService.this.checkPermission("car.permission.ANDROIDAUTO_DEVICE_LIST");
            if (AndroidAutoService.this.mLinkController != null) {
                return AndroidAutoService.this.mLinkController.getAndroidAutoDevice(devId);
            }
            return null;
        }

        @Override // com.ts.androidauto.sdk.aidl.IAndroidAutoService
        public List<com.ts.androidauto.sdk.aidl.data.AndroidAutoDevice> getUsbAndroidAutoDeviceList() throws RemoteException {
            LogUtil.info("AAPAndroidAutoService", "getAndroidAutoDeviceList");
            AndroidAutoService.this.checkPermission("car.permission.ANDROIDAUTO_DEVICE_LIST");
            if (AndroidAutoService.this.mLinkController != null) {
                return AndroidAutoService.this.mLinkController.getUsbAndroidAutoDeviceList();
            }
            return null;
        }

        @Override // com.ts.androidauto.sdk.aidl.IAndroidAutoService
        public List<com.ts.androidauto.sdk.aidl.data.AndroidAutoDevice> getAndroidAutoDeviceList() throws RemoteException {
            LogUtil.info("AAPAndroidAutoService", "getAndroidAutoDeviceList");
            AndroidAutoService.this.checkPermission("car.permission.ANDROIDAUTO_DEVICE_LIST");
            if (AndroidAutoService.this.mLinkController != null) {
                return AndroidAutoService.this.mLinkController.getAndroidAutoDeviceList();
            }
            return null;
        }

        @Override // com.ts.androidauto.sdk.aidl.IAndroidAutoService
        public void setClusterUiConfig(UiConfig uiConfig) {
            LogUtil.info("AAPAndroidAutoService", "setClusterUiConfig uiConfig=" + uiConfig);
            AndroidAutoService.this.checkPermission("car.permission.ANDROIDAUTO");
            if (AndroidAutoService.this.mAndroidAutoManager != null && uiConfig != null) {
                LogUtil.info("AAPAndroidAutoService", "setClusterUiConfig");
                com.ts.androidauto.aap.common.UiConfig aapUiConfig = DataConvert.toLocalUiConfig(uiConfig);
                AndroidAutoService.this.mAndroidAutoManager.setClusterUiConfig(aapUiConfig);
                return;
            }
            LogUtil.info("AAPAndroidAutoService", "setClusterUiConfig has no mAndroidAutoManager ! ");
        }
    }

    public void notifyCbStatusChanged(AndroidAutoDevice linkDevice, AndroidAutoConst.LinkStatus linkStatus, AndroidAutoConst.LinkErrorCode errorCode, String errorMessage) {
        String str;
        String str2;
        LogUtil.info("AAPAndroidAutoService", "notifyCbStatusChanged linkStatus:" + linkStatus + " errorCode:" + errorCode + " errorMessage:" + errorMessage);
        int projectionConnectType = 0;
        RemoteCallbackList<IAndroidAutoSessionStatusCallback> remoteCallbackList = this.mIAndroidAutoSessionStatusCallbackList;
        if (remoteCallbackList != null) {
            synchronized (remoteCallbackList) {
                try {
                    try {
                        int size = this.mIAndroidAutoSessionStatusCallbackList.beginBroadcast();
                        StringBuilder sb = new StringBuilder();
                        sb.append("notifyCbStatusChanged size:");
                        sb.append(size);
                        sb.append(" errorCode:");
                        sb.append(errorCode == null);
                        LogUtil.info("AAPAndroidAutoService", sb.toString());
                        if (linkDevice != null && linkStatus != null) {
                            linkDevice.setStatus(linkStatus.ordinal());
                            projectionConnectType = linkDevice.getLinkType();
                            LogUtil.info("AAPAndroidAutoService", "notifyCbStatusChanged projectionConnectType:" + projectionConnectType);
                        }
                        for (int i = 0; i < size; i++) {
                            IAndroidAutoSessionStatusCallback sessionStatusCallback = (IAndroidAutoSessionStatusCallback) this.mIAndroidAutoSessionStatusCallbackList.getBroadcastItem(i);
                            if (sessionStatusCallback != null && errorCode != null && linkStatus != null) {
                                LogUtil.info("AAPAndroidAutoService", "notifyCbStatusChanged onSessionStatusChanged");
                                sessionStatusCallback.onSessionConnectStatusChanged(linkStatus.ordinal(), errorCode.ordinal(), errorMessage, projectionConnectType);
                                sessionStatusCallback.onSessionStatusChanged(linkStatus.ordinal(), errorCode.ordinal(), errorMessage);
                            }
                        }
                        try {
                            if (this.mIAndroidAutoSessionStatusCallbackList != null) {
                                this.mIAndroidAutoSessionStatusCallbackList.finishBroadcast();
                            }
                        } catch (Exception e) {
                            str = "AAPAndroidAutoService";
                            str2 = "notifyCbStatusChanged RemoteException: ex2";
                            LogUtil.info(str, str2);
                        }
                    } catch (RemoteException ex) {
                        LogUtil.info("AAPAndroidAutoService", "notifyCbStatusChanged RemoteException:");
                        ex.printStackTrace();
                        try {
                            if (this.mIAndroidAutoSessionStatusCallbackList != null) {
                                this.mIAndroidAutoSessionStatusCallbackList.finishBroadcast();
                            }
                        } catch (Exception e2) {
                            str = "AAPAndroidAutoService";
                            str2 = "notifyCbStatusChanged RemoteException: ex2";
                            LogUtil.info(str, str2);
                        }
                    }
                } finally {
                }
            }
        }
    }

    public void notifyCbNavigationFocusChanged(boolean useNativeNavigation) {
        String str;
        String str2;
        LogUtil.debug("AAPAndroidAutoService", "notifyCbNavigationFocusChanged useNativeNavigation:" + useNativeNavigation);
        this.mIsUseNativeNavigation = useNativeNavigation;
        RemoteCallbackList<IAndroidAutoSessionStatusCallback> remoteCallbackList = this.mIAndroidAutoSessionStatusCallbackList;
        if (remoteCallbackList != null) {
            synchronized (remoteCallbackList) {
                try {
                    try {
                        int size = this.mIAndroidAutoSessionStatusCallbackList.beginBroadcast();
                        LogUtil.debug("AAPAndroidAutoService", "notifyCbNavigationFocusChanged size:" + size);
                        for (int i = 0; i < size; i++) {
                            IAndroidAutoSessionStatusCallback androidAutoSessionStatusCallback = (IAndroidAutoSessionStatusCallback) this.mIAndroidAutoSessionStatusCallbackList.getBroadcastItem(i);
                            if (androidAutoSessionStatusCallback != null) {
                                androidAutoSessionStatusCallback.onProjectionAppStatesChanged(AndroidAutoConst.AppType.APP_TYPE_ROUTE_GUIDANCE.ordinal(), useNativeNavigation);
                            }
                        }
                        try {
                            if (this.mIAndroidAutoSessionStatusCallbackList != null) {
                                this.mIAndroidAutoSessionStatusCallbackList.finishBroadcast();
                            }
                        } catch (Exception e) {
                            str = "AAPAndroidAutoService";
                            str2 = "notifyCbNavigationFocusChanged RemoteException: ex2";
                            LogUtil.info(str, str2);
                        }
                    } catch (RemoteException ex) {
                        LogUtil.info("AAPAndroidAutoService", "notifyCbNavigationFocusChanged RemoteException:");
                        ex.printStackTrace();
                        try {
                            if (this.mIAndroidAutoSessionStatusCallbackList != null) {
                                this.mIAndroidAutoSessionStatusCallbackList.finishBroadcast();
                            }
                        } catch (Exception e2) {
                            str = "AAPAndroidAutoService";
                            str2 = "notifyCbNavigationFocusChanged RemoteException: ex2";
                            LogUtil.info(str, str2);
                        }
                    }
                } finally {
                }
            }
        }
    }

    public void notifyCbBatteryStatusNotification(int batteryLevel, int timeRemainingS, boolean batteryCritical) {
        String str;
        String str2;
        RemoteCallbackList<IBatteryStatusCallback> remoteCallbackList = this.mIBatteryStatusCallbackList;
        if (remoteCallbackList != null) {
            synchronized (remoteCallbackList) {
                try {
                    try {
                        int size = this.mIBatteryStatusCallbackList.beginBroadcast();
                        LogUtil.debug("AAPAndroidAutoService", "notifyCbBatteryStatusNotification size:" + size);
                        for (int i = 0; i < size; i++) {
                            IBatteryStatusCallback batteryStatusCallback = (IBatteryStatusCallback) this.mIBatteryStatusCallbackList.getBroadcastItem(i);
                            if (batteryStatusCallback != null) {
                                batteryStatusCallback.onBatteryStatusNotification(batteryLevel, timeRemainingS, batteryCritical);
                            }
                        }
                        try {
                            this.mIBatteryStatusCallbackList.finishBroadcast();
                        } catch (Exception e) {
                            str = "AAPAndroidAutoService";
                            str2 = "notifyCbBatteryStatusNotification RemoteException: ex2";
                            LogUtil.info(str, str2);
                        }
                    } catch (Exception ex) {
                        LogUtil.info("AAPAndroidAutoService", "notifyCbBatteryStatusNotification RemoteException:");
                        ex.printStackTrace();
                        try {
                            this.mIBatteryStatusCallbackList.finishBroadcast();
                        } catch (Exception e2) {
                            str = "AAPAndroidAutoService";
                            str2 = "notifyCbBatteryStatusNotification RemoteException: ex2";
                            LogUtil.info(str, str2);
                        }
                    }
                } finally {
                }
            }
        }
    }

    public void notifyCbVoiceSessionNotification(int voiceSessionStatus) {
        String str;
        String str2;
        if (voiceSessionStatus == AndroidAutoConst.VoiceSessionStatus.VOICE_RECOGNITION_STATE_STARTED.ordinal()) {
            this.mIsVrEnable = true;
        } else {
            this.mIsVrEnable = false;
        }
        RemoteCallbackList<IAndroidAutoSessionStatusCallback> remoteCallbackList = this.mIAndroidAutoSessionStatusCallbackList;
        if (remoteCallbackList != null) {
            synchronized (remoteCallbackList) {
                try {
                    try {
                        int size = this.mIAndroidAutoSessionStatusCallbackList.beginBroadcast();
                        LogUtil.debug("AAPAndroidAutoService", "notifyCbVoiceSessionNotification size:" + size);
                        for (int i = 0; i < size; i++) {
                            IAndroidAutoSessionStatusCallback sessionStatusCallback = (IAndroidAutoSessionStatusCallback) this.mIAndroidAutoSessionStatusCallbackList.getBroadcastItem(i);
                            if (sessionStatusCallback != null) {
                                sessionStatusCallback.onProjectionAppStatesChanged(AndroidAutoConst.AppType.APP_TYPE_VR.ordinal(), this.mIsVrEnable);
                            }
                        }
                        try {
                            this.mIAndroidAutoSessionStatusCallbackList.finishBroadcast();
                        } catch (Exception e) {
                            str = "AAPAndroidAutoService";
                            str2 = "notifyCbVoiceSessionNotification RemoteException: ex2";
                            LogUtil.info(str, str2);
                        }
                    } catch (Exception ex) {
                        LogUtil.info("AAPAndroidAutoService", "notifyCbVoiceSessionNotification RemoteException:");
                        ex.printStackTrace();
                        try {
                            this.mIAndroidAutoSessionStatusCallbackList.finishBroadcast();
                        } catch (Exception e2) {
                            str = "AAPAndroidAutoService";
                            str2 = "notifyCbVoiceSessionNotification RemoteException: ex2";
                            LogUtil.info(str, str2);
                        }
                    }
                } finally {
                }
            }
        }
    }

    public void notifyCbVideoFocusStatusChanged(int videoFocusStatus) {
        String str;
        String str2;
        LogUtil.debug("AAPAndroidAutoService", "notifyCbVideoFocusStatusChanged videoFocusStatus:" + videoFocusStatus);
        RemoteCallbackList<IAndroidAutoAppCallback> remoteCallbackList = this.mIAndroidAutoAppCallbackList;
        if (remoteCallbackList != null) {
            synchronized (remoteCallbackList) {
                try {
                    try {
                        int size = this.mIAndroidAutoAppCallbackList.beginBroadcast();
                        LogUtil.debug("AAPAndroidAutoService", "notifyCbVideoFocusStatusChanged size:" + size);
                        for (int i = 0; i < size; i++) {
                            IAndroidAutoAppCallback androidAutoAppCallback = (IAndroidAutoAppCallback) this.mIAndroidAutoAppCallbackList.getBroadcastItem(i);
                            if (androidAutoAppCallback != null) {
                                androidAutoAppCallback.onVideoFocusStatusChanged(videoFocusStatus);
                            }
                        }
                        try {
                            if (this.mIAndroidAutoAppCallbackList != null) {
                                this.mIAndroidAutoAppCallbackList.finishBroadcast();
                            }
                        } catch (Exception e) {
                            str = "AAPAndroidAutoService";
                            str2 = "notifyCbVideoFocusStatusChanged RemoteException: ex2";
                            LogUtil.info(str, str2);
                        }
                    } finally {
                    }
                } catch (RemoteException ex) {
                    LogUtil.info("AAPAndroidAutoService", "notifyCbVideoFocusStatusChanged RemoteException:");
                    ex.printStackTrace();
                    try {
                        if (this.mIAndroidAutoAppCallbackList != null) {
                            this.mIAndroidAutoAppCallbackList.finishBroadcast();
                        }
                    } catch (Exception e2) {
                        str = "AAPAndroidAutoService";
                        str2 = "notifyCbVideoFocusStatusChanged RemoteException: ex2";
                        LogUtil.info(str, str2);
                    }
                }
            }
        }
    }

    public void notifyCbClusterVideoFocusStatusChanged(int videoFocusStatus) {
        String str;
        String str2;
        LogUtil.debug("AAPAndroidAutoService", "notifyCbClusterVideoFocusStatusChanged videoFocusStatus:" + videoFocusStatus);
        RemoteCallbackList<IAndroidAutoAppCallback> remoteCallbackList = this.mIAndroidAutoAppCallbackList;
        if (remoteCallbackList != null) {
            synchronized (remoteCallbackList) {
                try {
                    try {
                        int size = this.mIAndroidAutoAppCallbackList.beginBroadcast();
                        LogUtil.debug("AAPAndroidAutoService", "notifyCbClusterVideoFocusStatusChanged size:" + size);
                        for (int i = 0; i < size; i++) {
                            IAndroidAutoAppCallback androidAutoAppCallback = (IAndroidAutoAppCallback) this.mIAndroidAutoAppCallbackList.getBroadcastItem(i);
                            if (androidAutoAppCallback != null) {
                                androidAutoAppCallback.onClusterVideoFocusStatusChanged(videoFocusStatus);
                            }
                        }
                        try {
                            if (this.mIAndroidAutoAppCallbackList != null) {
                                this.mIAndroidAutoAppCallbackList.finishBroadcast();
                            }
                        } catch (Exception e) {
                            str = "AAPAndroidAutoService";
                            str2 = "notifyCbClusterVideoFocusStatusChanged RemoteException: ex2";
                            LogUtil.info(str, str2);
                        }
                    } finally {
                    }
                } catch (RemoteException ex) {
                    LogUtil.info("AAPAndroidAutoService", "notifyCbClusterVideoFocusStatusChanged RemoteException:");
                    ex.printStackTrace();
                    try {
                        if (this.mIAndroidAutoAppCallbackList != null) {
                            this.mIAndroidAutoAppCallbackList.finishBroadcast();
                        }
                    } catch (Exception e2) {
                        str = "AAPAndroidAutoService";
                        str2 = "notifyCbClusterVideoFocusStatusChanged RemoteException: ex2";
                        LogUtil.info(str, str2);
                    }
                }
            }
        }
    }

    public void notifyCbRequestVideoFocus(int requestType) {
        String str;
        String str2;
        LogUtil.debug("AAPAndroidAutoService", "notifyCbRequestVideoFocus requestType:" + requestType);
        RemoteCallbackList<IAndroidAutoServiceCallback> remoteCallbackList = this.mIAndroidAutoServiceCallbackList;
        if (remoteCallbackList != null) {
            synchronized (remoteCallbackList) {
                try {
                    try {
                        int size = this.mIAndroidAutoServiceCallbackList.beginBroadcast();
                        LogUtil.debug("AAPAndroidAutoService", "notifyCbRequestVideoFocus size:" + size);
                        for (int i = 0; i < size; i++) {
                            IAndroidAutoServiceCallback androidAutoServiceCallback = (IAndroidAutoServiceCallback) this.mIAndroidAutoServiceCallbackList.getBroadcastItem(i);
                            if (androidAutoServiceCallback != null) {
                                androidAutoServiceCallback.onRequestVideoFocus(requestType);
                            }
                        }
                        try {
                            if (this.mIAndroidAutoServiceCallbackList != null) {
                                this.mIAndroidAutoServiceCallbackList.finishBroadcast();
                            }
                        } catch (Exception e) {
                            str = "AAPAndroidAutoService";
                            str2 = "notifyCbRequestVideoFocus RemoteException: ex2";
                            LogUtil.info(str, str2);
                        }
                    } finally {
                    }
                } catch (RemoteException ex) {
                    LogUtil.info("AAPAndroidAutoService", "notifyCbRequestVideoFocus RemoteException:");
                    ex.printStackTrace();
                    try {
                        if (this.mIAndroidAutoServiceCallbackList != null) {
                            this.mIAndroidAutoServiceCallbackList.finishBroadcast();
                        }
                    } catch (Exception e2) {
                        str = "AAPAndroidAutoService";
                        str2 = "notifyCbRequestVideoFocus RemoteException: ex2";
                        LogUtil.info(str, str2);
                    }
                }
            }
        }
    }

    public void notifyCbRequestClusterVideoFocus(int requestType) {
        String str;
        String str2;
        LogUtil.debug("AAPAndroidAutoService", "notifyCbRequestClusterVideoFocus requestType:" + requestType);
        RemoteCallbackList<IAndroidAutoServiceCallback> remoteCallbackList = this.mIAndroidAutoServiceCallbackList;
        if (remoteCallbackList != null) {
            synchronized (remoteCallbackList) {
                try {
                    try {
                        int size = this.mIAndroidAutoServiceCallbackList.beginBroadcast();
                        LogUtil.debug("AAPAndroidAutoService", "notifyCbRequestClusterVideoFocus size:" + size);
                        for (int i = 0; i < size; i++) {
                            IAndroidAutoServiceCallback androidAutoServiceCallback = (IAndroidAutoServiceCallback) this.mIAndroidAutoServiceCallbackList.getBroadcastItem(i);
                            if (androidAutoServiceCallback != null) {
                                androidAutoServiceCallback.onRequestClusterVideoFocus(requestType);
                            }
                        }
                        try {
                            if (this.mIAndroidAutoServiceCallbackList != null) {
                                this.mIAndroidAutoServiceCallbackList.finishBroadcast();
                            }
                        } catch (Exception e) {
                            str = "AAPAndroidAutoService";
                            str2 = "notifyCbRequestClusterVideoFocus RemoteException: ex2";
                            LogUtil.info(str, str2);
                        }
                    } finally {
                    }
                } catch (RemoteException ex) {
                    LogUtil.info("AAPAndroidAutoService", "notifyCbRequestClusterVideoFocus RemoteException:");
                    ex.printStackTrace();
                    try {
                        if (this.mIAndroidAutoServiceCallbackList != null) {
                            this.mIAndroidAutoServiceCallbackList.finishBroadcast();
                        }
                    } catch (Exception e2) {
                        str = "AAPAndroidAutoService";
                        str2 = "notifyCbRequestClusterVideoFocus RemoteException: ex2";
                        LogUtil.info(str, str2);
                    }
                }
            }
        }
    }

    public void notifyCbClusterVideoUpdateUiConfigReply(boolean replyOk) {
        String str;
        String str2;
        LogUtil.debug("AAPAndroidAutoService", "notifyCbClusterVideoUpdateUiConfigReply replyOk:" + replyOk);
        RemoteCallbackList<IAndroidAutoServiceCallback> remoteCallbackList = this.mIAndroidAutoServiceCallbackList;
        if (remoteCallbackList != null) {
            synchronized (remoteCallbackList) {
                try {
                    try {
                        int size = this.mIAndroidAutoServiceCallbackList.beginBroadcast();
                        LogUtil.debug("AAPAndroidAutoService", "notifyCbClusterVideoUpdateUiConfigReply size:" + size);
                        for (int i = 0; i < size; i++) {
                            IAndroidAutoServiceCallback androidAutoServiceCallback = (IAndroidAutoServiceCallback) this.mIAndroidAutoServiceCallbackList.getBroadcastItem(i);
                            if (androidAutoServiceCallback != null) {
                                androidAutoServiceCallback.onClusterVideoUpdateUiConfigReply(replyOk);
                            }
                        }
                        try {
                            if (this.mIAndroidAutoServiceCallbackList != null) {
                                this.mIAndroidAutoServiceCallbackList.finishBroadcast();
                            }
                        } catch (Exception e) {
                            str = "AAPAndroidAutoService";
                            str2 = "notifyCbClusterVideoUpdateUiConfigReply RemoteException: ex2";
                            LogUtil.info(str, str2);
                        }
                    } finally {
                    }
                } catch (RemoteException ex) {
                    LogUtil.info("AAPAndroidAutoService", "notifyCbClusterVideoUpdateUiConfigReply RemoteException:");
                    ex.printStackTrace();
                    try {
                        if (this.mIAndroidAutoServiceCallbackList != null) {
                            this.mIAndroidAutoServiceCallbackList.finishBroadcast();
                        }
                    } catch (Exception e2) {
                        str = "AAPAndroidAutoService";
                        str2 = "notifyCbClusterVideoUpdateUiConfigReply RemoteException: ex2";
                        LogUtil.info(str, str2);
                    }
                }
            }
        }
    }

    public void notifyCbNavigationStatusChanged(int status) {
        String str;
        String str2;
        RemoteCallbackList<INavigationDataCallback> remoteCallbackList = this.mINavigationDataCallbackList;
        if (remoteCallbackList != null) {
            synchronized (remoteCallbackList) {
                try {
                    try {
                        int size = this.mINavigationDataCallbackList.beginBroadcast();
                        LogUtil.debug("AAPAndroidAutoService", "notifyCbNavigationStatusChanged size:" + size);
                        for (int i = 0; i < size; i++) {
                            INavigationDataCallback navigationDataCallback = (INavigationDataCallback) this.mINavigationDataCallbackList.getBroadcastItem(i);
                            if (navigationDataCallback != null) {
                                navigationDataCallback.onNavigationStatusChanged(status);
                            }
                        }
                        try {
                            this.mINavigationDataCallbackList.finishBroadcast();
                        } catch (Exception e) {
                            str = "AAPAndroidAutoService";
                            str2 = "notifyCbNavigationStatusChanged RemoteException: ex2";
                            LogUtil.info(str, str2);
                        }
                    } catch (Exception ex) {
                        LogUtil.info("AAPAndroidAutoService", "notifyCbNavigationStatusChanged RemoteException:");
                        ex.printStackTrace();
                        try {
                            this.mINavigationDataCallbackList.finishBroadcast();
                        } catch (Exception e2) {
                            str = "AAPAndroidAutoService";
                            str2 = "notifyCbNavigationStatusChanged RemoteException: ex2";
                            LogUtil.info(str, str2);
                        }
                    }
                } finally {
                }
            }
        }
    }

    public void notifyCbNavigationState(NavigationStateData navigationStateData) {
        String str;
        String str2;
        RemoteCallbackList<INavigationDataCallback> remoteCallbackList = this.mINavigationDataCallbackList;
        if (remoteCallbackList != null) {
            synchronized (remoteCallbackList) {
                if (navigationStateData == null) {
                    return;
                }
                try {
                    try {
                        int size = this.mINavigationDataCallbackList.beginBroadcast();
                        LogUtil.debug("AAPAndroidAutoService", "notifyCbNavigationState size:" + size);
                        for (int i = 0; i < size; i++) {
                            INavigationDataCallback navigationDataCallback = (INavigationDataCallback) this.mINavigationDataCallbackList.getBroadcastItem(i);
                            if (navigationDataCallback != null) {
                                navigationDataCallback.onNavigationState(DataConvert.toSdkNavigationStateData(navigationStateData));
                            }
                        }
                        try {
                            this.mINavigationDataCallbackList.finishBroadcast();
                        } catch (Exception e) {
                            str = "AAPAndroidAutoService";
                            str2 = "notifyCbNavigationState RemoteException: ex2";
                            LogUtil.info(str, str2);
                        }
                    } finally {
                        try {
                            this.mINavigationDataCallbackList.finishBroadcast();
                        } catch (Exception e2) {
                            LogUtil.info("AAPAndroidAutoService", "notifyCbNavigationState RemoteException: ex2");
                        }
                    }
                } catch (Exception ex) {
                    LogUtil.info("AAPAndroidAutoService", "notifyCbNavigationState RemoteException:");
                    ex.printStackTrace();
                    try {
                        this.mINavigationDataCallbackList.finishBroadcast();
                    } catch (Exception e3) {
                        str = "AAPAndroidAutoService";
                        str2 = "notifyCbNavigationState RemoteException: ex2";
                        LogUtil.info(str, str2);
                    }
                }
                return;
            }
        }
    }

    public void notifyCbNavigationCurrentPosition(NavigationPositionData navigationPositionData) {
        String str;
        String str2;
        LogUtil.debug("AAPAndroidAutoService", "notifyCbNavigationCurrentPosition ");
        RemoteCallbackList<INavigationDataCallback> remoteCallbackList = this.mINavigationDataCallbackList;
        if (remoteCallbackList != null) {
            synchronized (remoteCallbackList) {
                try {
                    if (navigationPositionData == null) {
                        return;
                    }
                    try {
                        int size = this.mINavigationDataCallbackList.beginBroadcast();
                        LogUtil.debug("AAPAndroidAutoService", "notifyCbNavigationCurrentPosition size:" + size);
                        for (int i = 0; i < size; i++) {
                            INavigationDataCallback navigationDataCallback = (INavigationDataCallback) this.mINavigationDataCallbackList.getBroadcastItem(i);
                            if (navigationDataCallback != null) {
                                navigationDataCallback.onNavigationCurrentPosition(DataConvert.toSdkNavigationPositionData(navigationPositionData));
                            }
                        }
                        try {
                            this.mINavigationDataCallbackList.finishBroadcast();
                        } catch (Exception e) {
                            str = "AAPAndroidAutoService";
                            str2 = "notifyCbNavigationCurrentPosition RemoteException: ex2";
                            LogUtil.info(str, str2);
                        }
                    } catch (Exception ex) {
                        LogUtil.info("AAPAndroidAutoService", "notifyCbNavigationCurrentPosition RemoteException:");
                        ex.printStackTrace();
                        try {
                            this.mINavigationDataCallbackList.finishBroadcast();
                        } catch (Exception e2) {
                            str = "AAPAndroidAutoService";
                            str2 = "notifyCbNavigationCurrentPosition RemoteException: ex2";
                            LogUtil.info(str, str2);
                        }
                    }
                    return;
                } finally {
                    try {
                        this.mINavigationDataCallbackList.finishBroadcast();
                    } catch (Exception e3) {
                        LogUtil.info("AAPAndroidAutoService", "notifyCbNavigationCurrentPosition RemoteException: ex2");
                    }
                }
            }
        }
    }

    public void notifyCbProjectionBluetoothAddressChanged(String address) {
        String str;
        String str2;
        LogUtil.debug("AAPAndroidAutoService", "notifyCbProjectionBluetoothAddressChanged address:" + LogEncrypt.MacAddressEncrypt(address));
        RemoteCallbackList<IAndroidAutoAppCallback> remoteCallbackList = this.mIAndroidAutoAppCallbackList;
        if (remoteCallbackList != null) {
            synchronized (remoteCallbackList) {
                try {
                    try {
                        int size = this.mIAndroidAutoAppCallbackList.beginBroadcast();
                        LogUtil.debug("AAPAndroidAutoService", "notifyCbProjectionBluetoothAddressChanged size:" + size);
                        for (int i = 0; i < size; i++) {
                            IAndroidAutoAppCallback androidAutoAppCallback = (IAndroidAutoAppCallback) this.mIAndroidAutoAppCallbackList.getBroadcastItem(i);
                            if (androidAutoAppCallback != null) {
                                androidAutoAppCallback.onProjectionBluetoothAddressChanged(address);
                            }
                        }
                        try {
                            if (this.mIAndroidAutoAppCallbackList != null) {
                                this.mIAndroidAutoAppCallbackList.finishBroadcast();
                            }
                        } catch (Exception e) {
                            str = "AAPAndroidAutoService";
                            str2 = "notifyCbProjectionBluetoothAddressChanged RemoteException: ex2";
                            LogUtil.info(str, str2);
                        }
                    } finally {
                    }
                } catch (RemoteException ex) {
                    LogUtil.info("AAPAndroidAutoService", "notifyCbProjectionBluetoothAddressChanged RemoteException:");
                    ex.printStackTrace();
                    try {
                        if (this.mIAndroidAutoAppCallbackList != null) {
                            this.mIAndroidAutoAppCallbackList.finishBroadcast();
                        }
                    } catch (Exception e2) {
                        str = "AAPAndroidAutoService";
                        str2 = "notifyCbProjectionBluetoothAddressChanged RemoteException: ex2";
                        LogUtil.info(str, str2);
                    }
                }
            }
        }
    }

    public void notifyCbMediaStatusChange(int mediaStatus) {
        String str;
        String str2;
        LogUtil.debug("AAPAndroidAutoService", "notifyCbMediaStatusChange mediaStatus:" + mediaStatus);
        RemoteCallbackList<IMediaDataCallback> remoteCallbackList = this.mIMediaDataCallbackList;
        if (remoteCallbackList != null) {
            synchronized (remoteCallbackList) {
                try {
                    try {
                        int size = this.mIMediaDataCallbackList.beginBroadcast();
                        LogUtil.debug("AAPAndroidAutoService", "notifyCbMediaStatusChange size:" + size);
                        for (int i = 0; i < size; i++) {
                            IMediaDataCallback mediaDataCallback = (IMediaDataCallback) this.mIMediaDataCallbackList.getBroadcastItem(i);
                            if (mediaDataCallback != null) {
                                mediaDataCallback.onMusicStatusChanged(mediaStatus);
                            }
                        }
                        try {
                            this.mIMediaDataCallbackList.finishBroadcast();
                        } catch (Exception e) {
                            str = "AAPAndroidAutoService";
                            str2 = "notifyCbMediaStatusChange RemoteException: ex2";
                            LogUtil.info(str, str2);
                        }
                    } catch (Exception ex) {
                        LogUtil.info("AAPAndroidAutoService", "notifyCbMediaStatusChange RemoteException:");
                        ex.printStackTrace();
                        try {
                            this.mIMediaDataCallbackList.finishBroadcast();
                        } catch (Exception e2) {
                            str = "AAPAndroidAutoService";
                            str2 = "notifyCbMediaStatusChange RemoteException: ex2";
                            LogUtil.info(str, str2);
                        }
                    }
                } finally {
                }
            }
        }
    }

    public void notifyCbMediaMetadataChange(MediaMetadata mediaMetadata) {
        String str;
        String str2;
        LogUtil.debug("AAPAndroidAutoService", "notifyCbMediaMetadataChange mediaMetadata:" + mediaMetadata);
        RemoteCallbackList<IMediaDataCallback> remoteCallbackList = this.mIMediaDataCallbackList;
        if (remoteCallbackList != null && mediaMetadata != null) {
            synchronized (remoteCallbackList) {
                try {
                    try {
                        int size = this.mIMediaDataCallbackList.beginBroadcast();
                        LogUtil.debug("AAPAndroidAutoService", "notifyCbMediaMetadataChange size:" + size);
                        for (int i = 0; i < size; i++) {
                            IMediaDataCallback mediaDataCallback = (IMediaDataCallback) this.mIMediaDataCallbackList.getBroadcastItem(i);
                            if (mediaDataCallback != null) {
                                mediaDataCallback.onMediaMetadataChanged(DataConvert.dataToMediaMetadata(mediaMetadata));
                            }
                        }
                        try {
                            this.mIMediaDataCallbackList.finishBroadcast();
                        } catch (Exception e) {
                            str = "AAPAndroidAutoService";
                            str2 = "notifyCbMediaMetadataChange RemoteException: ex2";
                            LogUtil.info(str, str2);
                        }
                    } finally {
                    }
                } catch (Exception ex) {
                    LogUtil.info("AAPAndroidAutoService", "notifyCbMediaMetadataChange RemoteException:");
                    ex.printStackTrace();
                    try {
                        this.mIMediaDataCallbackList.finishBroadcast();
                    } catch (Exception e2) {
                        str = "AAPAndroidAutoService";
                        str2 = "notifyCbMediaMetadataChange RemoteException: ex2";
                        LogUtil.info(str, str2);
                    }
                }
            }
        }
    }

    public void notifyCbMediaProgressChange(int progress) {
        String str;
        String str2;
        RemoteCallbackList<IMediaDataCallback> remoteCallbackList = this.mIMediaDataCallbackList;
        if (remoteCallbackList != null) {
            synchronized (remoteCallbackList) {
                try {
                    try {
                        int size = this.mIMediaDataCallbackList.beginBroadcast();
                        LogUtil.debug("AAPAndroidAutoService", "notifyCbMediaProgressChange size:" + size + " progress: " + progress);
                        for (int i = 0; i < size; i++) {
                            IMediaDataCallback mediaDataCallback = (IMediaDataCallback) this.mIMediaDataCallbackList.getBroadcastItem(i);
                            if (mediaDataCallback != null) {
                                mediaDataCallback.onMediaProgressChanged(progress);
                            }
                        }
                        try {
                            this.mIMediaDataCallbackList.finishBroadcast();
                        } catch (Exception e) {
                            str = "AAPAndroidAutoService";
                            str2 = "notifyCbMediaProgressChange RemoteException: ex2";
                            LogUtil.info(str, str2);
                        }
                    } catch (Exception ex) {
                        LogUtil.info("AAPAndroidAutoService", "notifyCbMediaProgressChange RemoteException:");
                        ex.printStackTrace();
                        try {
                            this.mIMediaDataCallbackList.finishBroadcast();
                        } catch (Exception e2) {
                            str = "AAPAndroidAutoService";
                            str2 = "notifyCbMediaProgressChange RemoteException: ex2";
                            LogUtil.info(str, str2);
                        }
                    }
                } finally {
                }
            }
        }
    }

    public void notifyRequestProjectionDevice(com.ts.androidauto.sdk.aidl.data.AndroidAutoDevice device, int type) {
        String str;
        String str2;
        RemoteCallbackList<IAndroidAutoDeviceListCallback> remoteCallbackList = this.mAndroidAutoDeviceListCallbackList;
        if (remoteCallbackList != null) {
            synchronized (remoteCallbackList) {
                try {
                    try {
                        int size = this.mAndroidAutoDeviceListCallbackList.beginBroadcast();
                        LogUtil.debug("AAPAndroidAutoService", "onRequestProjection size:" + size);
                        for (int i = 0; i < size; i++) {
                            IAndroidAutoDeviceListCallback androidAutoDeviceListCallback = (IAndroidAutoDeviceListCallback) this.mAndroidAutoDeviceListCallbackList.getBroadcastItem(i);
                            if (androidAutoDeviceListCallback != null) {
                                androidAutoDeviceListCallback.onRequestProjection(device, type);
                            }
                        }
                        try {
                            if (this.mAndroidAutoDeviceListCallbackList != null) {
                                this.mAndroidAutoDeviceListCallbackList.finishBroadcast();
                            }
                        } catch (Exception e) {
                            str = "AAPAndroidAutoService";
                            str2 = "onRequestProjection RemoteException: ex2";
                            LogUtil.info(str, str2);
                        }
                    } catch (RemoteException ex) {
                        LogUtil.info("AAPAndroidAutoService", "onRequestProjection RemoteException:");
                        ex.printStackTrace();
                        try {
                            if (this.mAndroidAutoDeviceListCallbackList != null) {
                                this.mAndroidAutoDeviceListCallbackList.finishBroadcast();
                            }
                        } catch (Exception e2) {
                            str = "AAPAndroidAutoService";
                            str2 = "onRequestProjection RemoteException: ex2";
                            LogUtil.info(str, str2);
                        }
                    }
                } finally {
                }
            }
        }
    }

    public void notifyCbLastAvailableDeviceChanged(String deviceId, int capability) {
        String str;
        String str2;
        RemoteCallbackList<IAndroidAutoAppCallback> remoteCallbackList = this.mIAndroidAutoAppCallbackList;
        if (remoteCallbackList != null) {
            synchronized (remoteCallbackList) {
                try {
                    try {
                        int size = this.mIAndroidAutoAppCallbackList.beginBroadcast();
                        LogUtil.debug("AAPAndroidAutoService", "onLastAvailableDeviceChanged size:" + size);
                        for (int i = 0; i < size; i++) {
                            IAndroidAutoAppCallback androidAutoAppCallback = (IAndroidAutoAppCallback) this.mIAndroidAutoAppCallbackList.getBroadcastItem(i);
                            if (androidAutoAppCallback != null) {
                                androidAutoAppCallback.onLastAvailableDeviceChanged(deviceId, capability);
                            }
                        }
                        try {
                            if (this.mIAndroidAutoAppCallbackList != null) {
                                this.mIAndroidAutoAppCallbackList.finishBroadcast();
                            }
                        } catch (Exception e) {
                            str = "AAPAndroidAutoService";
                            str2 = "onLastAvailableDeviceChanged RemoteException: ex2";
                            LogUtil.info(str, str2);
                        }
                    } catch (RemoteException ex) {
                        LogUtil.info("AAPAndroidAutoService", "onLastAvailableDeviceChanged RemoteException:");
                        ex.printStackTrace();
                        try {
                            if (this.mIAndroidAutoAppCallbackList != null) {
                                this.mIAndroidAutoAppCallbackList.finishBroadcast();
                            }
                        } catch (Exception e2) {
                            str = "AAPAndroidAutoService";
                            str2 = "onLastAvailableDeviceChanged RemoteException: ex2";
                            LogUtil.info(str, str2);
                        }
                    }
                } finally {
                }
            }
        }
    }

    public void broadAndroidAutoDeviceList(Consumer.ReConsumer<IAndroidAutoDeviceListCallback> consumer) {
        RemoteCallbackList<IAndroidAutoDeviceListCallback> remoteCallbackList = this.mAndroidAutoDeviceListCallbackList;
        if (remoteCallbackList != null) {
            synchronized (remoteCallbackList) {
                try {
                    try {
                        int size = this.mAndroidAutoDeviceListCallbackList.beginBroadcast();
                        for (int i = 0; i < size; i++) {
                            IAndroidAutoDeviceListCallback androidAutoDeviceListCallback = (IAndroidAutoDeviceListCallback) this.mAndroidAutoDeviceListCallbackList.getBroadcastItem(i);
                            if (androidAutoDeviceListCallback != null) {
                                consumer.accept(androidAutoDeviceListCallback);
                            }
                        }
                        try {
                            if (this.mAndroidAutoDeviceListCallbackList != null) {
                                this.mAndroidAutoDeviceListCallbackList.finishBroadcast();
                            }
                        } catch (Exception e) {
                            ex = e;
                            ex.printStackTrace();
                        }
                    } finally {
                    }
                } catch (RemoteException ex) {
                    ex.printStackTrace();
                    try {
                        if (this.mAndroidAutoDeviceListCallbackList != null) {
                            this.mAndroidAutoDeviceListCallbackList.finishBroadcast();
                        }
                    } catch (Exception e2) {
                        ex = e2;
                        ex.printStackTrace();
                    }
                }
            }
        }
    }
}
