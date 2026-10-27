package com.ts.avm;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Configuration;
import android.database.ContentObserver;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Message;
import android.os.RemoteCallbackList;
import android.os.RemoteException;
import android.os.SystemProperties;
import android.provider.Settings;
import android.text.TextUtils;
import android.view.WindowManager;
import com.ts.avm.IAvmServiceInterface;
import com.ts.avm.bean.AvmCallInfoBean;
import com.ts.avm.bean.CmdBean;
import com.ts.avm.manager.AvmCallManager;
import com.ts.avm.manager.AvmCommandManagerListener;
import com.ts.avm.manager.AvmManager;
import com.ts.avm.manager.AvmStateManagerListener;
import com.ts.avm.utils.LogManager;
import com.ts.avm.utils.usb.UsbHelper;
import com.ts.avm.utils.wrappers.CmdPool;
import java.util.List;
import ts.auth.AuthSdk;

/* JADX INFO: loaded from: classes.dex */
public class AvmAndroidService extends Service {
    private static final String AUTH_CONFIG_PATH = "/vehicleconfig";
    private static final String AVM_PICTURE_SAVE_PATH = "/data/vendor/nfs/nfs_log/qnx_dump/calibration_capture/";
    private static final int GET_NAVI_STATE = 5;
    private static final String PERSIST_CAR_SENSOR_RADAR = "persist.car.sensor.radar";
    private static final String PROPERTY_OF_SN_NAME = "persist.vendor.bosch.cfg.diag.sn";
    private static final int PULL_AVM_PICTURE_2_USB = 2;
    private static final int PULL_AVM_PICTURE_VALUE = 0;
    private static final int REQUEST_AUTH = 1;
    private static final int START_LISTEN_FDR = 3;
    private static final int START_LISTEN_NAVI_STATE = 4;
    private static final String SYSTEM_FDR_ACTION = "android.intent.action.MASTER_CLEAR";
    private static final String SYSTEM_FDR_ACTION_NOTIFY = "android.intent.action.MASTER_CLEAR_NOTIFICATION";
    private static final String SYSTEM_RESET_ACTION = "android.intent.action.FACTORY_RESET";
    private static final String TAG = AvmAndroidService.class.getSimpleName();
    private AuthSdk mAuthSdk;
    private HandlerThread mHandlerThread;
    private AvmManager mManager;
    private UsbHelper mUsbHelper;
    private Handler mHandler;
    private ContentObserver mContentObserver = new ContentObserver(this.mHandler) { // from class: com.ts.avm.AvmAndroidService.2
        @Override // android.database.ContentObserver
        public void onChange(boolean selfChange) {
            AvmAndroidService.this.getNaviState();
        }
    };
    private ServiceBinder mServiceBinder = ServiceBinder.getInstance();

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        return this.mServiceBinder;
    }

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        initHandler();
        this.mManager = AvmManager.getInstance();
        ExtraTaskCommandListener listener = new ExtraTaskCommandListener();
        this.mManager.registerExtraAvmCommandListener(listener);
        this.mUsbHelper = UsbHelper.getInstance(this);
        this.mAuthSdk = new AuthSdk();
        requestAuth();
        startListenFdr();
        getScreenSwitchStatus();
        registerNaviStateListener();
    }

    private void registerNaviStateListener() {
        doAction(4);
    }

    private void getScreenSwitchStatus() {
        LogManager.logd(TAG, "getScreenSwitchStatus: isLandscape:" + isHorizontalScreen());
        if (isHorizontalScreen()) {
            syncScreenOrientation2qnx(true);
        } else {
            syncScreenOrientation2qnx(false);
        }
    }

    private boolean isHorizontalScreen() {
        int angle = ((WindowManager) getSystemService("window")).getDefaultDisplay().getRotation();
        LogManager.logd(TAG, "angle: " + angle);
        if (angle == 0) {
            return true;
        }
        return false;
    }

    public class FactoryResetReceiver extends BroadcastReceiver {
        public FactoryResetReceiver() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            LogManager.logi(AvmAndroidService.TAG, "FactoryResetReceiver: action: " + action);
            if ((action.equals(AvmAndroidService.SYSTEM_FDR_ACTION) || action.equals(AvmAndroidService.SYSTEM_RESET_ACTION) || action.equals(AvmAndroidService.SYSTEM_FDR_ACTION_NOTIFY)) && AvmAndroidService.this.mManager != null) {
                CmdBean cmdBean = new CmdBean();
                cmdBean.setId(CmdPool.SID_FDR);
                cmdBean.setSrcId(9);
                cmdBean.setTargetId(7);
                cmdBean.setValue(new int[]{0, 0, 0, 1});
                AvmAndroidService.this.mManager.sendCommand(cmdBean);
                LogManager.logi(AvmAndroidService.TAG, "FDR SEND OK");
            }
        }
    }

    @Override // android.app.Service, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        LogManager.logi(TAG, "onConfigurationChanged: " + newConfig.orientation);
        checkConfiguration(newConfig);
    }

    private void checkConfiguration(Configuration newConfig) {
        LogManager.logd(TAG, "checkConfiguration: " + newConfig.orientation);
        if (newConfig.orientation == 2) {
            syncScreenOrientation2qnx(true);
        }
        if (newConfig.orientation == 1) {
            syncScreenOrientation2qnx(false);
        }
    }

    private void syncScreenOrientation2qnx(boolean isLandScape) {
        LogManager.logd(TAG, "syncScreenOrientation2qnx isLandScape:" + isLandScape);
        CmdBean cmdBean = new CmdBean();
        if (isLandScape) {
            cmdBean.setId(33554432);
            cmdBean.setSrcId(9);
            cmdBean.setTargetId(7);
            cmdBean.setValue(new int[]{0, 0, 0, 2});
            AvmManager avmManager = this.mManager;
            if (avmManager == null) {
                LogManager.loge(TAG, "checkConfiguration: mManager is null");
                return;
            } else {
                avmManager.sendCommand(cmdBean);
                return;
            }
        }
        cmdBean.setId(33554432);
        cmdBean.setSrcId(9);
        cmdBean.setTargetId(7);
        cmdBean.setValue(new int[]{0, 0, 0, 3});
        AvmManager avmManager2 = this.mManager;
        if (avmManager2 == null) {
            LogManager.loge(TAG, "checkConfiguration: mManager is null");
        } else {
            avmManager2.sendCommand(cmdBean);
        }
    }

    private void startListenFdr() {
        doAction(3);
    }

    private void requestAuth() {
        doAction(1);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void requestAuth(String configPath) {
        if (this.mAuthSdk != null) {
            LogManager.logi(TAG, "requestAuth: configPath:" + configPath);
            this.mAuthSdk.Init(configPath);
            if (this.mAuthSdk.getStatus() != 0) {
                String sn = SystemProperties.get(PROPERTY_OF_SN_NAME);
                if (!TextUtils.isEmpty(sn)) {
                    LogManager.logd(TAG, "requestAuth: sn is valid start onlineAuth");
                    this.mAuthSdk.onlineAuth(sn);
                    return;
                } else {
                    LogManager.loge(TAG, "requestAuth: sn is empty");
                    return;
                }
            }
            LogManager.logi(TAG, "requestAuth: has been authed");
        }
    }

    @Override // android.app.Service
    public void onDestroy() {
        super.onDestroy();
        if (this.mHandler != null) {
            this.mHandler = null;
        }
        HandlerThread handlerThread = this.mHandlerThread;
        if (handlerThread != null) {
            handlerThread.quitSafely();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void doAction(int action) {
        Handler handler = this.mHandler;
        if (handler != null) {
            handler.sendEmptyMessage(action);
        } else {
            LogManager.loge(TAG, "doAction: mHandler is null");
        }
    }

    private void initHandler() {
        HandlerThread handlerThread = new HandlerThread("ExternWork");
        this.mHandlerThread = handlerThread;
        handlerThread.start();
        this.mHandler = new Handler(this.mHandlerThread.getLooper()) { // from class: com.ts.avm.AvmAndroidService.1
            @Override // android.os.Handler
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                LogManager.logi(AvmAndroidService.TAG, "mHandler: what = " + msg.what);
                int i = msg.what;
                if (i == 1) {
                    AvmAndroidService.this.requestAuth(AvmAndroidService.AUTH_CONFIG_PATH);
                    return;
                }
                if (i == 2) {
                    if (AvmAndroidService.this.mUsbHelper != null) {
                        AvmAndroidService.this.mUsbHelper.pullDataFromDevice2Usb(AvmAndroidService.AVM_PICTURE_SAVE_PATH);
                        return;
                    } else {
                        LogManager.loge(AvmAndroidService.TAG, "PULL_AVM_PICTURE_2_USB: mUsbHelper is null");
                        return;
                    }
                }
                if (i == 3) {
                    AvmAndroidService.this.startListenFdrReal();
                } else if (i == 4) {
                    AvmAndroidService.this.startListenNaviStateReal();
                } else if (i == 5) {
                    AvmAndroidService.this.getNaviState();
                }
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void startListenNaviStateReal() {
        LogManager.logd(TAG, "startListenNaviStateReal");
        getContentResolver().registerContentObserver(Settings.System.getUriFor("mapType"), true, this.mContentObserver);
        getContentResolver().registerContentObserver(Settings.System.getUriFor("naviState"), true, this.mContentObserver);
        getContentResolver().registerContentObserver(Settings.System.getUriFor("isForeground"), true, this.mContentObserver);
        getContentResolver().registerContentObserver(Settings.System.getUriFor("mapSource"), true, this.mContentObserver);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void getNaviState() {
        ContentResolver resolver = getApplicationContext().getContentResolver();
        if (resolver != null) {
            int mapType = Settings.System.getInt(resolver, "mapType", -1);
            int naviState = Settings.System.getInt(resolver, "naviState", -1);
            int isForeground = Settings.System.getInt(resolver, "isForeground", -1);
            String mapSource = Settings.System.getString(resolver, "mapSource");
            LogManager.logd(TAG, "getNaviState: mapType:" + mapType + " naviState:" + naviState + " isForeground:" + isForeground + " mapSource:" + mapSource);
            if (mapType == -1 || naviState == -1 || isForeground == -1 || TextUtils.isEmpty(mapSource)) {
                return;
            }
            this.mManager.sendNaviState2Qnx(mapType, naviState, isForeground, mapSource);
            return;
        }
        LogManager.logi(TAG, "getNaviState: resolver is null");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void startListenFdrReal() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(SYSTEM_FDR_ACTION);
        intentFilter.addAction(SYSTEM_RESET_ACTION);
        intentFilter.addAction(SYSTEM_FDR_ACTION_NOTIFY);
        registerReceiver(new FactoryResetReceiver(), intentFilter);
    }

    public class ExtraTaskCommandListener implements AvmCommandManagerListener {
        public ExtraTaskCommandListener() {
        }

        @Override // com.ts.avm.manager.AvmCommandManagerListener
        public void onCommandBeanCallback(CmdBean cmd) {
            if (cmd != null) {
                LogManager.logd(AvmAndroidService.TAG, "onCommandBeanCallback_Extra: sid:" + cmd.getId() + " value[0]:" + cmd.getValue()[0]);
                if (cmd.getId() == 67109999 && cmd.getValue()[0] == 0) {
                    AvmAndroidService.this.doAction(2);
                }
                if (cmd.getId() == 77108765) {
                    AvmAndroidService.this.doAction(5);
                }
                if (cmd.getId() == 129) {
                    int radarValue = cmd.getValue()[0];
                    AvmAndroidService.this.setRadarTone(radarValue);
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setRadarTone(int value) {
        LogManager.loge(TAG, "setRadarTone value : " + value);
        SystemProperties.set(PERSIST_CAR_SENSOR_RADAR, value + "");
    }

    public static class ServiceBinder extends IAvmServiceInterface.Stub {
        private static volatile ServiceBinder mBinderService = null;
        private AvmCallManager mAvmCallManager;
        private RemoteCallbackList<IAvmCallListener> mIAvmCallListenerList;
        private RemoteCallbackList<IAvmServiceCommandListener> mIAvmServiceCommandListenerList;
        private RemoteCallbackList<IAvmServiceListener> mIAvmServiceListenerList;
        private ServiceCommandListenerManager mServiceCommandListenerManager;
        private ServiceListenerManager mServiceListenerManager;
        private final AvmManager manager;

        private ServiceBinder() {
            this.mServiceListenerManager = null;
            this.mServiceCommandListenerManager = null;
            this.mIAvmServiceListenerList = null;
            this.mIAvmServiceCommandListenerList = null;
            this.mIAvmCallListenerList = null;
            this.mIAvmServiceListenerList = new RemoteCallbackList<>();
            this.mIAvmServiceCommandListenerList = new RemoteCallbackList<>();
            this.mIAvmCallListenerList = new RemoteCallbackList<>();
            this.mServiceListenerManager = new ServiceListenerManager(this.mIAvmServiceListenerList);
            this.mServiceCommandListenerManager = new ServiceCommandListenerManager(this.mIAvmServiceCommandListenerList);
            AvmManager avmManager = AvmManager.getInstance();
            this.manager = avmManager;
            avmManager.registerAvmStatusListener(this.mServiceListenerManager);
            this.manager.registerAvmCommandListener(this.mServiceCommandListenerManager);
            this.manager.init();
            AvmCallManager avmCallManager = AvmCallManager.getInstance();
            this.mAvmCallManager = avmCallManager;
            avmCallManager.registerAvmCallActionListener(new AvmCallListener(this.mIAvmCallListenerList));
        }

        public static ServiceBinder getInstance() {
            synchronized (ServiceBinder.class) {
                if (mBinderService == null) {
                    mBinderService = new ServiceBinder();
                }
            }
            return mBinderService;
        }

        @Override // com.ts.avm.IAvmServiceInterface
        public int getAvmStatus() throws RemoteException {
            return this.manager.getCurrentState();
        }

        @Override // com.ts.avm.IAvmServiceInterface
        public int registerAvmStatusListener(IAvmServiceListener listener) throws RemoteException {
            if (listener == null) {
                LogManager.loge(AvmAndroidService.TAG, "registerAvmStatusListener: null pointer error.");
                return -1;
            }
            boolean resultBoolean = this.mIAvmServiceListenerList.register(listener);
            if (!resultBoolean) {
                LogManager.loge(AvmAndroidService.TAG, "registerAvmStatusListener: register failed.");
                return -1;
            }
            return 0;
        }

        @Override // com.ts.avm.IAvmServiceInterface
        public int unregisterAvmStatusListener(IAvmServiceListener listener) throws RemoteException {
            if (listener == null) {
                LogManager.loge(AvmAndroidService.TAG, "unregisterAvmStatusListener: null pointer error.");
                return -1;
            }
            boolean resultBoolean = this.mIAvmServiceListenerList.unregister(listener);
            if (!resultBoolean) {
                LogManager.loge(AvmAndroidService.TAG, "unregisterAvmStatusListener: unregister failed.");
                return -1;
            }
            return 0;
        }

        @Override // com.ts.avm.IAvmServiceInterface
        public void startAvm() throws RemoteException {
            this.manager.start();
        }

        @Override // com.ts.avm.IAvmServiceInterface
        public void stopAvm() throws RemoteException {
            this.manager.stop();
        }

        @Override // com.ts.avm.IAvmServiceInterface
        public void sendCommand(CmdBean cmd) throws RemoteException {
            this.manager.sendCommand(cmd);
        }

        @Override // com.ts.avm.IAvmServiceInterface
        public int registerCommandListener(IAvmServiceCommandListener listener) throws RemoteException {
            if (listener == null) {
                LogManager.loge(AvmAndroidService.TAG, "registerAvmStatusListener: null pointer error.");
                return -1;
            }
            boolean resultBoolean = this.mIAvmServiceCommandListenerList.register(listener);
            if (!resultBoolean) {
                LogManager.loge(AvmAndroidService.TAG, "registerAvmStatusListener: register failed.");
                return -1;
            }
            return 0;
        }

        @Override // com.ts.avm.IAvmServiceInterface
        public int sendCallInfo(String phoneNum, String contactName, String address, int callTime, int callType, int callState, boolean isMute) throws RemoteException {
            AvmCallManager avmCallManager = this.mAvmCallManager;
            if (avmCallManager == null) {
                LogManager.loge(AvmAndroidService.TAG, "sendCallInfo mAvmCallManager is null");
                return -1;
            }
            avmCallManager.sendCallInfo(phoneNum, contactName, address, callTime, callType, callState, isMute);
            return 0;
        }

        @Override // com.ts.avm.IAvmServiceInterface
        public int sendMultiCallInfo(List<AvmCallInfoBean> list) throws RemoteException {
            LogManager.logd(AvmAndroidService.TAG, "sendMultiCallInfo called");
            AvmCallManager avmCallManager = this.mAvmCallManager;
            if (avmCallManager == null) {
                LogManager.loge(AvmAndroidService.TAG, "sendCallInfo mAvmCallManager is null");
                return -1;
            }
            avmCallManager.sendMultiCallInfo(list);
            return 0;
        }

        @Override // com.ts.avm.IAvmServiceInterface
        public int registerCallStatusListener(IAvmCallListener listener) {
            LogManager.logd(AvmAndroidService.TAG, "registerCallStatusListener: called");
            if (listener == null) {
                LogManager.loge(AvmAndroidService.TAG, "registerCallStatusListener: null pointer error.");
                return -1;
            }
            boolean resultBoolean = this.mIAvmCallListenerList.register(listener);
            if (!resultBoolean) {
                LogManager.loge(AvmAndroidService.TAG, "registerAvmStatusListener: register failed.");
                return -1;
            }
            return 0;
        }

        @Override // com.ts.avm.IAvmServiceInterface
        public int unregisterCallStatusListener(IAvmCallListener listener) {
            LogManager.logd(AvmAndroidService.TAG, "unregisterCallStatusListener: called");
            if (listener == null) {
                LogManager.loge(AvmAndroidService.TAG, "unregisterCallStatusListener: null pointer error.");
                return -1;
            }
            boolean resultBoolean = this.mIAvmCallListenerList.unregister(listener);
            if (!resultBoolean) {
                LogManager.loge(AvmAndroidService.TAG, "unregisterCallStatusListener: register failed.");
                return -1;
            }
            return 0;
        }
    }

    public static class AvmCallListener implements AvmCallManager.IAvmCallActionListener {
        private final RemoteCallbackList<IAvmCallListener> mRemoteCallbackList;

        public AvmCallListener(RemoteCallbackList<IAvmCallListener> remoteCallbackList) {
            this.mRemoteCallbackList = remoteCallbackList;
        }

        @Override // com.ts.avm.manager.AvmCallManager.IAvmCallActionListener
        public void onAvmActionCallback(String phonenum, int calltype, int action) {
            synchronized (this.mRemoteCallbackList) {
                try {
                    try {
                        int registeredListenerNumber = this.mRemoteCallbackList.beginBroadcast();
                        for (int i = 0; i < registeredListenerNumber; i++) {
                            IAvmCallListener avmCallListener = (IAvmCallListener) this.mRemoteCallbackList.getBroadcastItem(i);
                            if (avmCallListener != null) {
                                avmCallListener.onAvmCallStatusChanged(phonenum, calltype, action);
                            }
                        }
                        try {
                            this.mRemoteCallbackList.finishBroadcast();
                        } catch (IllegalStateException e) {
                            ex = e;
                            ex.printStackTrace();
                        }
                    } finally {
                    }
                } catch (RemoteException | IllegalStateException ex) {
                    ex.printStackTrace();
                    try {
                        this.mRemoteCallbackList.finishBroadcast();
                    } catch (IllegalStateException e2) {
                        ex = e2;
                        ex.printStackTrace();
                    }
                }
            }
        }
    }

    public static class ServiceCommandListenerManager implements AvmCommandManagerListener {
        private final RemoteCallbackList<IAvmServiceCommandListener> mRemoteCallbackList;

        public ServiceCommandListenerManager(RemoteCallbackList<IAvmServiceCommandListener> remoteCallbackList) {
            this.mRemoteCallbackList = remoteCallbackList;
        }

        @Override // com.ts.avm.manager.AvmCommandManagerListener
        public void onCommandBeanCallback(CmdBean cmd) {
            synchronized (this.mRemoteCallbackList) {
                try {
                    try {
                        int registeredListenerNumber = this.mRemoteCallbackList.beginBroadcast();
                        for (int i = 0; i < registeredListenerNumber; i++) {
                            IAvmServiceCommandListener iAvmServiceListener = (IAvmServiceCommandListener) this.mRemoteCallbackList.getBroadcastItem(i);
                            if (iAvmServiceListener != null) {
                                iAvmServiceListener.onCommandReceived(cmd);
                            }
                        }
                        try {
                            this.mRemoteCallbackList.finishBroadcast();
                        } catch (IllegalStateException e) {
                            ex = e;
                            ex.printStackTrace();
                        }
                    } finally {
                    }
                } catch (RemoteException | IllegalStateException ex) {
                    ex.printStackTrace();
                    try {
                        this.mRemoteCallbackList.finishBroadcast();
                    } catch (IllegalStateException e2) {
                        ex = e2;
                        ex.printStackTrace();
                    }
                }
            }
        }
    }

    public static class ServiceListenerManager implements AvmStateManagerListener {
        private final RemoteCallbackList<IAvmServiceListener> mRemoteCallbackList;

        public ServiceListenerManager(RemoteCallbackList<IAvmServiceListener> remoteCallbackList) {
            this.mRemoteCallbackList = remoteCallbackList;
        }

        @Override // com.ts.avm.manager.AvmStateManagerListener
        public void onAvmStatusChanged(int code, String message) {
            synchronized (this.mRemoteCallbackList) {
                try {
                    try {
                        int registeredListenerNumber = this.mRemoteCallbackList.beginBroadcast();
                        for (int i = 0; i < registeredListenerNumber; i++) {
                            IAvmServiceListener iAvmServiceListener = (IAvmServiceListener) this.mRemoteCallbackList.getBroadcastItem(i);
                            if (iAvmServiceListener != null) {
                                iAvmServiceListener.onAvmServiceStatusChanged(code, message);
                            }
                        }
                        try {
                            this.mRemoteCallbackList.finishBroadcast();
                        } catch (IllegalStateException e) {
                            ex = e;
                            ex.printStackTrace();
                        }
                    } finally {
                    }
                } catch (RemoteException | IllegalStateException ex) {
                    ex.printStackTrace();
                    try {
                        this.mRemoteCallbackList.finishBroadcast();
                    } catch (IllegalStateException e2) {
                        ex = e2;
                        ex.printStackTrace();
                    }
                }
            }
        }
    }
}
