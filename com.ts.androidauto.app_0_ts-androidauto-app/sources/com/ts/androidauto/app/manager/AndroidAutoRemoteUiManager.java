package com.ts.androidauto.app.manager;

import android.content.Context;
import android.os.RemoteException;
import android.text.TextUtils;
import android.view.Surface;
import com.ts.androidauto.app.common.IModel;
import com.ts.androidauto.app.common.IModelFactory;
import com.ts.androidauto.app.common.IProxy;
import com.ts.androidauto.app.common.IProxyFactory;
import com.ts.androidauto.app.common.LogUtil;
import com.ts.androidauto.app.model.common.ModelFactory;
import com.ts.androidauto.app.proxy.common.ProxyFactory;
import com.ts.androidauto.fsdk.common.LogEncrypt;
import com.ts.car.core.ServiceProvider;
import com.ts.lib.car.Car;
import com.ts.lib.car.CarConnectionCallback;
import com.ts.lib.car.CarNotConnectedException;
import com.ts.lib.car.hardware.setting.CarVendorSettingManager;
import com.ts.lib.car.hardware.setting.CarVendorSettingMgr;
import com.ts.lib.car.hardware.setting.SettingEventChangeValue;
import com.ts.lib.car.hardware.xcall.CarVendorXcallManager;
import com.ts.lib.car.hardware.xcall.CarVendorXcallMgr;
import com.ts.lib.car.hardware.xcall.XcallEventChangeValue;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.function.Consumer;
import ts.car.service.IPlatformService;
import ts.car.service.IServiceConnectionListener;
import ts.car.service.androidauto.IAndroidAutoAppListener;
import ts.car.service.androidauto.IAndroidAutoAppManager;
import ts.car.service.androidauto.IAndroidAutoDeviceListManager;
import ts.car.service.androidauto.IAndroidAutoServiceListener;
import ts.car.service.androidauto.IAndroidAutoServiceManager;
import ts.car.service.androidauto.IAndroidAutoSessionStatusListener;
import ts.car.service.androidauto.IBatteryStatusListener;
import ts.car.service.androidauto.common.VehicleConst;
import ts.car.service.androidauto.data.ConnectRequest;
import ts.car.service.androidauto.data.UiConfigData;
import ts.car.service.androidauto.data.VehicleData;

/* JADX INFO: loaded from: classes2.dex */
public class AndroidAutoRemoteUiManager {
    private static final int LANDSCAPE_MODE = 1;
    private static final int SCREEN_ROTATION_STATUS_INVALID = 0;
    private static final int SCREEN_ROTATION_STATUS_LAND_TO_PORT = 1;
    private static final int SCREEN_ROTATION_STATUS_PORT_TO_LAND = 2;
    private static final int SCREEN_ROTATION_STATUS_STOP = 3;
    private static final String TAG = "AAP" + AndroidAutoRemoteUiManager.class.getSimpleName();
    private static AndroidAutoRemoteUiManager mAndroidAutoRemoteUiManager = null;
    private Car mCarClient;
    private CarVendorSettingMgr mCarVendorSettingMgr;
    private CarVendorXcallManager mCarVendorXcallManager;
    private CarVendorXcallMgr mCarVendorXcallMgr;
    private Context mContext;
    private IModel[] mModels;
    private IProxy[] mProxies;
    private IAndroidAutoServiceManager mAndroidAutoServiceManager = null;
    private IAndroidAutoAppManager mAndroidAutoAppManager = null;
    private IAndroidAutoDeviceListManager mAndroidAutoDeviceListManager = null;
    private Vector<IAndroidAutoRemoteUiManagerListener> mListeners = new Vector<>();
    private boolean mIsAndroidAutoServiceConnected = false;
    private boolean mIsAndroidAutoAppConnected = false;
    private boolean mIsAndroidAutoDeviceListConnected = false;
    private int mCurrentScreenRotationStatus = 0;
    private int mVideoFocusStatus = 0;
    CarVendorSettingManager.OnCarSettingEventListener settingEventListener = new CarVendorSettingManager.OnCarSettingEventListener() { // from class: com.ts.androidauto.app.manager.AndroidAutoRemoteUiManager.2
        @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager.OnCarSettingEventListener
        public void onSettingEventChange(SettingEventChangeValue value) {
            int rotationStatus = value.getScreenRotationStatus();
            if (AndroidAutoRemoteUiManager.this.mCurrentScreenRotationStatus != rotationStatus) {
                if (AndroidAutoRemoteUiManager.this.isVideoShow() && rotationStatus == 1) {
                    LogUtil.debug(AndroidAutoRemoteUiManager.TAG, "onSettingEventChange preRotationStatus: " + AndroidAutoRemoteUiManager.this.mCurrentScreenRotationStatus);
                    AndroidAutoRemoteUiManager.this.setScreenRotationS(1);
                }
                AndroidAutoRemoteUiManager.this.mCurrentScreenRotationStatus = rotationStatus;
                LogUtil.debug(AndroidAutoRemoteUiManager.TAG, "onSettingEventChange screenRotationStatus: " + AndroidAutoRemoteUiManager.this.mCurrentScreenRotationStatus);
            }
        }

        @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager.OnCarSettingEventListener
        public void onSettingErrorEvent(int propertyId, int zone) {
        }
    };
    private CarVendorXcallManager.OnCarXcallEventListener mCarXcallEventListener = new AnonymousClass3();
    private final IServiceConnectionListener mServiceConnectionListener = new AnonymousClass4();
    private final IServiceConnectionListener mAppConnectionListener = new IServiceConnectionListener() { // from class: com.ts.androidauto.app.manager.AndroidAutoRemoteUiManager.5
        public void onServiceConnected(IPlatformService service) {
            LogUtil.debug(AndroidAutoRemoteUiManager.TAG, "mAppConnectionListener onServiceConnected.");
            if (AndroidAutoRemoteUiManager.this.mAndroidAutoAppManager != null) {
                try {
                    AndroidAutoRemoteUiManager.this.mAndroidAutoAppManager.registerBatteryStatusListener(AndroidAutoRemoteUiManager.this.mBatteryStatusListener);
                    AndroidAutoRemoteUiManager.this.mAndroidAutoAppManager.registerAndroidAutoAppListener(AndroidAutoRemoteUiManager.this.mAndroidAutoAppListener);
                } catch (RemoteException ex) {
                    ex.printStackTrace();
                }
            } else {
                LogUtil.debug(AndroidAutoRemoteUiManager.TAG, "mAndroidAutoAppManager is null.");
            }
            AndroidAutoRemoteUiManager.this.mIsAndroidAutoAppConnected = true;
        }

        public void onServiceDisconnected(IPlatformService service) {
            LogUtil.debug(AndroidAutoRemoteUiManager.TAG, "mAppConnectionListener onServiceDisconnected.");
            AndroidAutoRemoteUiManager.this.mIsAndroidAutoAppConnected = false;
        }
    };
    private final IServiceConnectionListener mDeviceListConnectionListener = new IServiceConnectionListener() { // from class: com.ts.androidauto.app.manager.AndroidAutoRemoteUiManager.6
        public void onServiceConnected(IPlatformService service) {
            LogUtil.debug(AndroidAutoRemoteUiManager.TAG, "mDeviceListConnectionListener onServiceConnected.");
            AndroidAutoRemoteUiManager.this.mIsAndroidAutoDeviceListConnected = true;
        }

        public void onServiceDisconnected(IPlatformService service) {
            LogUtil.debug(AndroidAutoRemoteUiManager.TAG, "mDeviceListConnectionListener onServiceDisconnected.");
            AndroidAutoRemoteUiManager.this.mIsAndroidAutoDeviceListConnected = false;
        }
    };
    private IAndroidAutoAppListener mAndroidAutoAppListener = new AnonymousClass7();
    private IAndroidAutoServiceListener mAndroidAutoServiceListener = new AnonymousClass8();
    private IAndroidAutoSessionStatusListener mAndroidAutoSessionStatusListener = new AnonymousClass9();
    private IBatteryStatusListener mBatteryStatusListener = new AnonymousClass10();

    private AndroidAutoRemoteUiManager() {
        this.mModels = null;
        this.mProxies = null;
        LogUtil.info(TAG, "AndroidAutoRemoteUiManager start");
        IModelFactory modeFactory = new ModelFactory();
        this.mModels = modeFactory.createModes();
        IProxyFactory proxyFactory = new ProxyFactory();
        this.mProxies = proxyFactory.createProxies();
    }

    public static synchronized AndroidAutoRemoteUiManager getInstance() {
        if (mAndroidAutoRemoteUiManager == null) {
            mAndroidAutoRemoteUiManager = new AndroidAutoRemoteUiManager();
        }
        return mAndroidAutoRemoteUiManager;
    }

    public void init(Context context) {
        this.mContext = context;
        initLinkManager();
        IModel[] iModelArr = this.mModels;
        if (iModelArr != null) {
            for (IModel model : iModelArr) {
                model.init(this.mContext);
            }
        }
        IProxy[] iProxyArr = this.mProxies;
        if (iProxyArr != null) {
            for (IProxy proxy : iProxyArr) {
                proxy.init(this.mContext);
            }
        }
        connectXcallService();
    }

    private void connectXcallService() {
        LogUtil.info(TAG, "connectXcallService XcallService init...");
        Car carCreateCar = Car.createCar(this.mContext, new CarConnectionCallback() { // from class: com.ts.androidauto.app.manager.AndroidAutoRemoteUiManager.1
            @Override // com.ts.lib.car.CarConnectionCallback
            public void onConnected(Car car) {
                try {
                    if (car != null) {
                        AndroidAutoRemoteUiManager.this.mCarVendorXcallManager = (CarVendorXcallManager) car.getCarManager(Car.VENDOR_XCALL_SERVICE);
                        AndroidAutoRemoteUiManager.this.mCarVendorXcallMgr = (CarVendorXcallMgr) car.getCarManager(Car.VENDOR_XCALL_SERVICE);
                        AndroidAutoRemoteUiManager.this.mCarVendorSettingMgr = (CarVendorSettingMgr) car.getCarManager(Car.VENDOR_SETTING_SERVICE);
                        String str = AndroidAutoRemoteUiManager.TAG;
                        StringBuilder sb = new StringBuilder();
                        sb.append("mCarVendorSettingMgr ");
                        sb.append(AndroidAutoRemoteUiManager.this.mCarVendorSettingMgr == null);
                        LogUtil.debug(str, sb.toString());
                        if (AndroidAutoRemoteUiManager.this.mCarVendorSettingMgr != null) {
                            AndroidAutoRemoteUiManager.this.mCarVendorSettingMgr.addCarSettingEventListener(AndroidAutoRemoteUiManager.this.settingEventListener);
                        }
                        if (AndroidAutoRemoteUiManager.this.mCarVendorXcallManager != null) {
                            AndroidAutoRemoteUiManager.this.mCarVendorXcallManager.addCarXcallEventListener(AndroidAutoRemoteUiManager.this.mCarXcallEventListener);
                            return;
                        }
                        return;
                    }
                    LogUtil.info(AndroidAutoRemoteUiManager.TAG, "connectXcallService car is null!");
                } catch (CarNotConnectedException exception) {
                    exception.printStackTrace();
                }
            }

            @Override // com.ts.lib.car.CarConnectionCallback
            public void onDisconnected(Car car) {
                if (AndroidAutoRemoteUiManager.this.mCarClient != null) {
                    AndroidAutoRemoteUiManager.this.mCarClient = null;
                }
            }
        });
        this.mCarClient = carCreateCar;
        if (carCreateCar != null) {
            carCreateCar.connect();
        }
    }

    public void uninit() {
        IModel[] iModelArr = this.mModels;
        if (iModelArr != null) {
            for (IModel model : iModelArr) {
                model.uninit();
            }
        }
        IProxy[] iProxyArr = this.mProxies;
        if (iProxyArr != null) {
            for (IProxy proxy : iProxyArr) {
                proxy.uninit();
            }
        }
        IAndroidAutoServiceManager iAndroidAutoServiceManager = this.mAndroidAutoServiceManager;
        if (iAndroidAutoServiceManager != null) {
            if (this.mIsAndroidAutoServiceConnected) {
                try {
                    iAndroidAutoServiceManager.unregisterAndroidAutoSessionStatusListener(this.mAndroidAutoSessionStatusListener);
                    this.mAndroidAutoServiceManager.unregisterAndroidAutoServiceListener(this.mAndroidAutoServiceListener);
                } catch (RemoteException ex) {
                    ex.printStackTrace();
                }
            }
            this.mAndroidAutoServiceManager.unregisterConnectionListener(this.mServiceConnectionListener);
            this.mAndroidAutoServiceManager = null;
        }
        IAndroidAutoAppManager iAndroidAutoAppManager = this.mAndroidAutoAppManager;
        if (iAndroidAutoAppManager != null) {
            if (this.mIsAndroidAutoAppConnected) {
                try {
                    iAndroidAutoAppManager.unregisterBatteryStatusListener(this.mBatteryStatusListener);
                    this.mAndroidAutoAppManager.unregisterAndroidAutoAppListener(this.mAndroidAutoAppListener);
                } catch (RemoteException ex2) {
                    ex2.printStackTrace();
                }
            }
            this.mAndroidAutoAppManager.unregisterConnectionListener(this.mAppConnectionListener);
            this.mAndroidAutoAppManager = null;
        }
        IAndroidAutoDeviceListManager iAndroidAutoDeviceListManager = this.mAndroidAutoDeviceListManager;
        if (iAndroidAutoDeviceListManager != null) {
            iAndroidAutoDeviceListManager.unregisterConnectionListener(this.mDeviceListConnectionListener);
            this.mAndroidAutoDeviceListManager = null;
        }
        try {
            if (this.mCarVendorSettingMgr != null) {
                this.mCarVendorSettingMgr.removeCarSettingEventListener(this.settingEventListener);
                this.mCarVendorSettingMgr = null;
            }
            if (this.mCarVendorXcallManager != null) {
                this.mCarVendorXcallManager.removeCarXcallEventListener(this.mCarXcallEventListener);
                this.mCarVendorXcallManager = null;
            }
            if (this.mCarVendorSettingMgr != null) {
                this.mCarVendorSettingMgr = null;
            }
        } catch (CarNotConnectedException exception) {
            exception.printStackTrace();
        }
        if (this.mCarVendorXcallMgr != null) {
            this.mCarVendorXcallMgr = null;
        }
        Car car = this.mCarClient;
        if (car != null) {
            car.disconnect();
            this.mCarClient = null;
        }
    }

    private synchronized void initLinkManager() {
        this.mAndroidAutoServiceManager = (IAndroidAutoServiceManager) ServiceProvider.get(IAndroidAutoServiceManager.class, this.mContext);
        this.mAndroidAutoAppManager = (IAndroidAutoAppManager) ServiceProvider.get(IAndroidAutoAppManager.class, this.mContext);
        this.mAndroidAutoDeviceListManager = (IAndroidAutoDeviceListManager) ServiceProvider.get(IAndroidAutoDeviceListManager.class, this.mContext);
        if (this.mAndroidAutoServiceManager != null) {
            this.mAndroidAutoServiceManager.registerConnectionListener(this.mServiceConnectionListener);
        }
        if (this.mAndroidAutoAppManager != null) {
            this.mAndroidAutoAppManager.registerConnectionListener(this.mAppConnectionListener);
        }
        if (this.mAndroidAutoDeviceListManager != null) {
            this.mAndroidAutoDeviceListManager.registerConnectionListener(this.mDeviceListConnectionListener);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isVideoShow() {
        return this.mVideoFocusStatus == 1;
    }

    public boolean isNeedChangeToLandSpace() {
        int screenRotation = this.mContext.getResources().getConfiguration().orientation;
        boolean needChangeToLandSpace = true;
        if (screenRotation != 1 && this.mCurrentScreenRotationStatus != 1) {
            needChangeToLandSpace = false;
        }
        LogUtil.info(TAG, "isNeedChangeToLandSpace screenRotation = " + screenRotation + " mCurrentScreenRotationStatus " + this.mCurrentScreenRotationStatus);
        return needChangeToLandSpace;
    }

    /* JADX INFO: renamed from: com.ts.androidauto.app.manager.AndroidAutoRemoteUiManager$3, reason: invalid class name */
    class AnonymousClass3 implements CarVendorXcallManager.OnCarXcallEventListener {
        AnonymousClass3() {
        }

        @Override // com.ts.lib.car.hardware.xcall.CarVendorXcallManager.OnCarXcallEventListener
        public void onXcallEventChange(XcallEventChangeValue value) {
            final int callState = value.getCallStatusR();
            AndroidAutoRemoteUiManager.this.notifyAndroidAutoRemoteUiManagerListener(new Consumer() { // from class: com.ts.androidauto.app.manager.-$$Lambda$AndroidAutoRemoteUiManager$3$CVa50ot7MArv80iUn7oIsPVWFdk
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    ((IAndroidAutoRemoteUiManagerListener) obj).onEcallStatusChanged(callState);
                }
            });
            LogUtil.info(AndroidAutoRemoteUiManager.TAG, "OnCarXcallEventListener onXcallEventChange callState: " + callState);
        }

        @Override // com.ts.lib.car.hardware.xcall.CarVendorXcallManager.OnCarXcallEventListener
        public void onXcallErrorEvent(int propertyId, int zone) {
        }
    }

    public int getEcallStatus() {
        int currentEcallState = 0;
        try {
            if (this.mCarVendorXcallMgr == null) {
                return 0;
            }
            currentEcallState = this.mCarVendorXcallMgr.getCallStatusR();
            LogUtil.info(TAG, "getEcallStatus currentEcallState " + currentEcallState);
            return currentEcallState;
        } catch (CarNotConnectedException exception) {
            exception.printStackTrace();
            return currentEcallState;
        }
    }

    public void setScreenRotationS(int orientation) {
        try {
            if (this.mCarVendorSettingMgr != null && isNeedChangeToLandSpace()) {
                LogUtil.debug(TAG, "setScreenRotationS orientation: " + orientation);
                this.mCarVendorSettingMgr.setScreenRotationS(orientation);
            }
        } catch (CarNotConnectedException exception) {
            exception.printStackTrace();
        }
    }

    /* JADX INFO: renamed from: com.ts.androidauto.app.manager.AndroidAutoRemoteUiManager$4, reason: invalid class name */
    class AnonymousClass4 implements IServiceConnectionListener {
        AnonymousClass4() {
        }

        public void onServiceConnected(IPlatformService service) {
            LogUtil.debug(AndroidAutoRemoteUiManager.TAG, "mServiceConnectionListener onServiceConnected.");
            if (AndroidAutoRemoteUiManager.this.mAndroidAutoServiceManager == null) {
                LogUtil.debug(AndroidAutoRemoteUiManager.TAG, "mAndroidAutoServiceManager is null.");
            } else {
                try {
                    AndroidAutoRemoteUiManager.this.mAndroidAutoServiceManager.registerAndroidAutoSessionStatusListener(AndroidAutoRemoteUiManager.this.mAndroidAutoSessionStatusListener);
                    AndroidAutoRemoteUiManager.this.mAndroidAutoServiceManager.registerAndroidAutoServiceListener(AndroidAutoRemoteUiManager.this.mAndroidAutoServiceListener);
                } catch (RemoteException ex) {
                    ex.printStackTrace();
                }
            }
            AndroidAutoRemoteUiManager.this.mIsAndroidAutoServiceConnected = true;
            AndroidAutoRemoteUiManager.this.notifyAndroidAutoRemoteUiManagerListener(new Consumer() { // from class: com.ts.androidauto.app.manager.-$$Lambda$AndroidAutoRemoteUiManager$4$x6yUcb7qHz0iBEWA39S_5pshhQU
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    ((IAndroidAutoRemoteUiManagerListener) obj).onServiceStateChanged(true);
                }
            });
        }

        public void onServiceDisconnected(IPlatformService service) {
            LogUtil.debug(AndroidAutoRemoteUiManager.TAG, "mServiceConnectionListener onServiceDisconnected.");
            AndroidAutoRemoteUiManager.this.mIsAndroidAutoServiceConnected = false;
            AndroidAutoRemoteUiManager.this.notifyAndroidAutoRemoteUiManagerListener(new Consumer() { // from class: com.ts.androidauto.app.manager.-$$Lambda$AndroidAutoRemoteUiManager$4$dx7j-ShzYnr9TQmQsohaDKgBVk4
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    ((IAndroidAutoRemoteUiManagerListener) obj).onServiceStateChanged(false);
                }
            });
        }
    }

    /* JADX INFO: renamed from: com.ts.androidauto.app.manager.AndroidAutoRemoteUiManager$7, reason: invalid class name */
    class AnonymousClass7 implements IAndroidAutoAppListener {
        AnonymousClass7() {
        }

        public void onLastAvailableDeviceChanged(final String deviceId, final int capability) {
            LogUtil.debug(AndroidAutoRemoteUiManager.TAG, "onLastAvailableDeviceChanged deviceId: " + LogEncrypt.MacAddressEncrypt(deviceId) + ",capability:" + capability);
            AndroidAutoRemoteUiManager.this.notifyAndroidAutoRemoteUiManagerListener(new Consumer() { // from class: com.ts.androidauto.app.manager.-$$Lambda$AndroidAutoRemoteUiManager$7$aHNt5XfJP6fW5OT_DgXg1ZQ4Ito
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    ((IAndroidAutoRemoteUiManagerListener) obj).onLastAvailableDeviceChanged(deviceId, capability);
                }
            });
        }

        public void onVideoFocusStatusChanged(int requestType) {
            LogUtil.debug(AndroidAutoRemoteUiManager.TAG, "onVideoFocusStatusChanged: " + requestType);
            AndroidAutoRemoteUiManager.this.mVideoFocusStatus = requestType;
        }

        public void onProjectionBluetoothAddressChanged(String address) {
            LogUtil.debug(AndroidAutoRemoteUiManager.TAG, "onProjectionBluetoothAddressChanged address:" + LogEncrypt.MacAddressEncrypt(address));
        }
    }

    /* JADX INFO: renamed from: com.ts.androidauto.app.manager.AndroidAutoRemoteUiManager$8, reason: invalid class name */
    class AnonymousClass8 implements IAndroidAutoServiceListener {
        AnonymousClass8() {
        }

        public void onRequestClusterVideoFocus(final int requestType) {
            LogUtil.debug(AndroidAutoRemoteUiManager.TAG, "onRequestClusterVideoFocus requestType:" + requestType);
            AndroidAutoRemoteUiManager.this.notifyAndroidAutoRemoteUiManagerListener(new Consumer() { // from class: com.ts.androidauto.app.manager.-$$Lambda$AndroidAutoRemoteUiManager$8$8t3P6zPuvOjJDgtyU6Bg6KJfcv0
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    ((IAndroidAutoRemoteUiManagerListener) obj).onRequestClusterVideoFocus(requestType);
                }
            });
        }

        public void onClusterVideoUpdateUiConfigReply(final boolean replyOk) {
            LogUtil.debug(AndroidAutoRemoteUiManager.TAG, "onClusterVideoUpdateUiConfigReply replyOk:" + replyOk);
            AndroidAutoRemoteUiManager.this.notifyAndroidAutoRemoteUiManagerListener(new Consumer() { // from class: com.ts.androidauto.app.manager.-$$Lambda$AndroidAutoRemoteUiManager$8$IfFjUo7Qi6VH_0c6G_zlprsJsWg
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    ((IAndroidAutoRemoteUiManagerListener) obj).onClusterVideoUpdateUiConfigReply(replyOk);
                }
            });
        }
    }

    /* JADX INFO: renamed from: com.ts.androidauto.app.manager.AndroidAutoRemoteUiManager$9, reason: invalid class name */
    class AnonymousClass9 implements IAndroidAutoSessionStatusListener {
        AnonymousClass9() {
        }

        public void onSessionStatusChanged(final int linkStatus, final int errorCode, final String message) {
            LogUtil.debug(AndroidAutoRemoteUiManager.TAG, "onSessionStatusChanged: " + linkStatus);
            AndroidAutoRemoteUiManager.this.notifyAndroidAutoRemoteUiManagerListener(new Consumer() { // from class: com.ts.androidauto.app.manager.-$$Lambda$AndroidAutoRemoteUiManager$9$em_UF-LwtDqllPmM57kJ2lGoftU
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    ((IAndroidAutoRemoteUiManagerListener) obj).onStatusChanged(linkStatus);
                }
            });
            AndroidAutoRemoteUiManager.this.notifyAndroidAutoRemoteUiManagerListener(new Consumer() { // from class: com.ts.androidauto.app.manager.-$$Lambda$AndroidAutoRemoteUiManager$9$395SKVVGKIbbmYa1o09WVJdDJ9A
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    ((IAndroidAutoRemoteUiManagerListener) obj).onErrorCodeNotification(errorCode, message);
                }
            });
        }

        public void onProjectionAppStatesChanged(int appType, final boolean enabled) {
            LogUtil.info(AndroidAutoRemoteUiManager.TAG, "onProjectionAppStatesChanged appType:" + appType + ",enabled:" + enabled);
            if (appType == 0) {
                AndroidAutoRemoteUiManager.this.notifyAndroidAutoRemoteUiManagerListener(new Consumer() { // from class: com.ts.androidauto.app.manager.-$$Lambda$AndroidAutoRemoteUiManager$9$KqvSjJfChRsXdWvA4LyAgG-LqyY
                    @Override // java.util.function.Consumer
                    public final void accept(Object obj) {
                        ((IAndroidAutoRemoteUiManagerListener) obj).onNavigationFocusChanged(enabled);
                    }
                });
            } else if (appType == 1) {
                AndroidAutoRemoteUiManager.this.notifyAndroidAutoRemoteUiManagerListener(new Consumer() { // from class: com.ts.androidauto.app.manager.-$$Lambda$AndroidAutoRemoteUiManager$9$FeJoJ29ablafxH_qS75__BLs-gY
                    @Override // java.util.function.Consumer
                    public final void accept(Object obj) {
                        ((IAndroidAutoRemoteUiManagerListener) obj).onVoiceSessionStatusChanged(enabled);
                    }
                });
            }
        }
    }

    /* JADX INFO: renamed from: com.ts.androidauto.app.manager.AndroidAutoRemoteUiManager$10, reason: invalid class name */
    class AnonymousClass10 implements IBatteryStatusListener {
        AnonymousClass10() {
        }

        public void onBatteryStatusChanged(final int batteryLevel, final int timeRemainingS, final boolean batteryCritical) {
            LogUtil.debug(AndroidAutoRemoteUiManager.TAG, "onBatteryStatusNotification batteryLevel: " + batteryLevel + " timeRemainingS: " + timeRemainingS + " batteryCritical: " + batteryCritical);
            AndroidAutoRemoteUiManager.this.notifyAndroidAutoRemoteUiManagerListener(new Consumer() { // from class: com.ts.androidauto.app.manager.-$$Lambda$AndroidAutoRemoteUiManager$10$2UcUtcZTP8AK7yxirgSIR7dUT3U
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    ((IAndroidAutoRemoteUiManagerListener) obj).onBatteryStatusNotification(batteryLevel, timeRemainingS, batteryCritical);
                }
            });
        }
    }

    public void addAndroidAutoRemoteUiManagerListener(IAndroidAutoRemoteUiManagerListener listener) {
        if (listener == null) {
            LogUtil.warning(TAG, "addAndroidAutoRemoteUiManagerListener listener is null.");
            return;
        }
        synchronized (this.mListeners) {
            if (!this.mListeners.contains(listener)) {
                this.mListeners.add(listener);
            }
        }
    }

    public void removeAndroidAutoRemoteUiManagerListener(IAndroidAutoRemoteUiManagerListener listener) {
        if (listener == null) {
            LogUtil.warning(TAG, "removeAndroidAutoRemoteUiManagerListener listener is null.");
            return;
        }
        synchronized (this.mListeners) {
            if (this.mListeners.contains(listener)) {
                this.mListeners.remove(listener);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void notifyAndroidAutoRemoteUiManagerListener(Consumer<IAndroidAutoRemoteUiManagerListener> c) {
        List<IAndroidAutoRemoteUiManagerListener> list;
        synchronized (this.mListeners) {
            list = new ArrayList<>(this.mListeners);
        }
        for (IAndroidAutoRemoteUiManagerListener listener : list) {
            c.accept(listener);
        }
    }

    public synchronized boolean showCluster(Surface surface, int width, int height) {
        LogUtil.info(TAG, "show cluster surface");
        if (this.mAndroidAutoServiceManager != null && this.mIsAndroidAutoServiceConnected) {
            LogUtil.info(TAG, "HMI show cluster surface success");
            try {
                this.mAndroidAutoServiceManager.setClusterSurface(surface, width, height);
            } catch (RemoteException ex) {
                ex.printStackTrace();
            }
            return true;
        }
        return false;
    }

    public synchronized boolean hideCluster() {
        LogUtil.debug(TAG, "hide cluster view");
        if (this.mAndroidAutoServiceManager == null || !this.mIsAndroidAutoServiceConnected) {
            return false;
        }
        try {
            this.mAndroidAutoServiceManager.setClusterSurface((Surface) null, 0, 0);
        } catch (RemoteException ex) {
            ex.printStackTrace();
        }
        return true;
    }

    public void sendKeyEvent(VehicleConst.AapHardkeyEvent keyCode, int keyAction) {
        LogUtil.info(TAG, "sendKeyEvent keyCode: " + keyCode + " keyAction: " + keyAction);
        IAndroidAutoAppManager iAndroidAutoAppManager = this.mAndroidAutoAppManager;
        if (iAndroidAutoAppManager != null && this.mIsAndroidAutoAppConnected) {
            try {
                iAndroidAutoAppManager.sendKeyEvent(keyCode, keyAction);
            } catch (RemoteException ex) {
                ex.printStackTrace();
            }
        }
    }

    public void sendKeyLongPressEvent(VehicleConst.AapHardkeyEvent keyCode) {
        LogUtil.info(TAG, "sendKeyLongPressEvent keyCode: " + keyCode);
        IAndroidAutoServiceManager iAndroidAutoServiceManager = this.mAndroidAutoServiceManager;
        if (iAndroidAutoServiceManager != null) {
            try {
                iAndroidAutoServiceManager.sendKeyLongPressEvent(keyCode);
            } catch (RemoteException ex) {
                ex.printStackTrace();
            }
        }
    }

    public void sendVehicleInfo(VehicleData vehicleInfo) {
        LogUtil.info(TAG, "sendVehicleInfo vehicleInfo=" + vehicleInfo);
        IAndroidAutoServiceManager iAndroidAutoServiceManager = this.mAndroidAutoServiceManager;
        if (iAndroidAutoServiceManager != null && this.mIsAndroidAutoServiceConnected) {
            try {
                iAndroidAutoServiceManager.setVehicleInfo(vehicleInfo);
            } catch (RemoteException ex) {
                ex.printStackTrace();
            }
        }
    }

    public void sendNativeNaviStatus(final boolean useNativeNavigation) {
        LogUtil.info(TAG, "sendNativeNaviStatus useNativeNavigation: " + useNativeNavigation);
        IAndroidAutoServiceManager iAndroidAutoServiceManager = this.mAndroidAutoServiceManager;
        if (iAndroidAutoServiceManager != null && this.mIsAndroidAutoServiceConnected) {
            try {
                iAndroidAutoServiceManager.setNativeAppStates(0, useNativeNavigation);
                notifyAndroidAutoRemoteUiManagerListener(new Consumer() { // from class: com.ts.androidauto.app.manager.-$$Lambda$AndroidAutoRemoteUiManager$kej8NDS3n4WkLq8UFqQ7_hn1YVw
                    @Override // java.util.function.Consumer
                    public final void accept(Object obj) {
                        ((IAndroidAutoRemoteUiManagerListener) obj).onNavigationFocusChanged(useNativeNavigation);
                    }
                });
            } catch (RemoteException ex) {
                ex.printStackTrace();
            }
        }
    }

    public void sendNativePhoneCallStatus(int status) {
        LogUtil.info(TAG, "sendNativePhoneCallStatus status: " + status);
        IAndroidAutoServiceManager iAndroidAutoServiceManager = this.mAndroidAutoServiceManager;
        if (iAndroidAutoServiceManager != null && this.mIsAndroidAutoServiceConnected) {
            try {
                iAndroidAutoServiceManager.setNativePhoneCallStatus(status);
            } catch (RemoteException ex) {
                ex.printStackTrace();
            }
        }
    }

    public List<String> getAvailableDevices() {
        List<String> availableDeviceList = new ArrayList<>();
        IAndroidAutoAppManager iAndroidAutoAppManager = this.mAndroidAutoAppManager;
        if (iAndroidAutoAppManager != null && this.mIsAndroidAutoAppConnected) {
            try {
                return iAndroidAutoAppManager.getAvailableDevices();
            } catch (RemoteException ex) {
                ex.printStackTrace();
                return availableDeviceList;
            }
        }
        return availableDeviceList;
    }

    public void connectAndroidAuto(String deviceId) {
        if (this.mAndroidAutoDeviceListManager != null && this.mIsAndroidAutoDeviceListConnected) {
            try {
                LogUtil.debug(TAG, "connectAndroidAuto deviceId: " + deviceId);
                if (!TextUtils.isEmpty(deviceId)) {
                    ConnectRequest connectRequest = new ConnectRequest();
                    connectRequest.setDeviceId(deviceId);
                    connectRequest.setConnectType(0);
                    connectRequest.setUserRequest(true);
                    connectRequest.setForceProjection(true);
                    this.mAndroidAutoDeviceListManager.connectAndroidAuto(connectRequest);
                }
            } catch (RemoteException ex) {
                ex.printStackTrace();
            }
        }
    }

    public int getAndroidAutoSessionStatus() {
        int status = 1;
        IAndroidAutoServiceManager iAndroidAutoServiceManager = this.mAndroidAutoServiceManager;
        if (iAndroidAutoServiceManager != null && this.mIsAndroidAutoServiceConnected) {
            try {
                status = iAndroidAutoServiceManager.getAndroidAutoSessionStatus();
            } catch (RemoteException ex) {
                ex.printStackTrace();
            }
        }
        LogUtil.info(TAG, "getAndroidAutoSessionStatus status: " + status);
        return status;
    }

    public void setClusterUiConfig(UiConfigData uiConfig) {
        LogUtil.info(TAG, "setClusterUiConfig UiConfigData: " + uiConfig);
        IAndroidAutoServiceManager iAndroidAutoServiceManager = this.mAndroidAutoServiceManager;
        if (iAndroidAutoServiceManager != null && this.mIsAndroidAutoServiceConnected) {
            try {
                iAndroidAutoServiceManager.setClusterUiConfig(uiConfig);
            } catch (RemoteException ex) {
                ex.printStackTrace();
            }
        }
    }
}
