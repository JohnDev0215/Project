package com.ts.carplay.app.service.manager;

import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.hardware.bydauto.setting.AbsBYDAutoSettingListener;
import android.hardware.bydauto.setting.BYDAutoSettingDevice;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.RemoteException;
import android.provider.Settings;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import com.ts.car.core.ServiceProvider;
import com.ts.carplay.app.service.common.IModel;
import com.ts.carplay.app.service.common.IModelFactory;
import com.ts.carplay.app.service.common.IProxy;
import com.ts.carplay.app.service.common.IProxyFactory;
import com.ts.carplay.app.service.model.common.ModelFactory;
import com.ts.carplay.app.service.proxy.common.ProxyFactory;
import com.ts.carplay.utils.LogUtil;
import com.ts.lib.car.Car;
import com.ts.lib.car.CarConnectionCallback;
import com.ts.lib.car.CarNotConnectedException;
import com.ts.lib.car.VehicleVendorProperty;
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
import ts.car.service.carplay.ICarPlayAppListener;
import ts.car.service.carplay.ICarPlayAppManager;
import ts.car.service.carplay.ICarPlayServiceListener;
import ts.car.service.carplay.ICarPlayServiceManager;
import ts.car.service.carplay.ICarPlayStateListener;
import ts.car.service.carplay.IRouteGuidanceListener;
import ts.car.service.carplay.data.RouteGuidanceInfo;
import ts.car.service.carplay.data.config.CarPlayConfig;
import ts.car.service.carplay.data.config.LogEncrypt;

/* JADX INFO: loaded from: classes.dex */
public class CarPlayRemoteManager {
    private static final String APPLICATION_CLASS_NAME = "com.ts.carplay.app.ui.display.view.CarPlayDisplayActivity";
    private static final String APPLICATION_PACKAGE_NAME = "com.ts.carplay.app";
    private static final long CARPLAY_SERVICE_BIND_MAX_RETRY = 20;
    private static final int LANDSCAPE_MODE = 1;
    private static final int LASTUSERMODE_VIEW_SIZE = 1;
    private static final int MSG_HIDELASTMODE_VIEW = 1;
    private static final int MSG_INITLASTMODE_VIEW = 0;
    private static final int SCREEN_ANGLE_0 = 1;
    private static final int SCREEN_ANGLE_0_TO_90 = 2;
    private static final int SCREEN_ANGLE_90 = 2;
    private static final int SCREEN_ANGLE_90_TO_0 = 1;
    private static final int SCREEN_ANGLE_INVALID = 0;
    private static final int SCREEN_ROTATION_STATUS_INVALID = 0;
    private static final int SCREEN_ROTATION_STATUS_LAND_TO_PORT = 1;
    private static final int SCREEN_ROTATION_STATUS_PORT_TO_LAND = 2;
    private static final int SCREEN_ROTATION_STATUS_STOP = 3;
    private static final int SENSOR_ORIENTATION_0 = 0;
    private static final int SENSOR_ORIENTATION_90 = 3;
    private static final int SENSOR_ORIENTATION_INVALID = -1;
    private static final String SENSOR_ORIENTATION_VALUE = "sensor_orientation_value";
    private static final String TAG = "CarPlayRemoteManager";
    private static CarPlayRemoteManager mCarPlayRemoteManager = null;
    private Car mCarClient;
    private CarPlayAppListener mCarPlayAppListener;
    private CarPlayStateListener mCarPlayStateListener;
    private CarVendorSettingMgr mCarVendorSettingMgr;
    private CarVendorXcallManager mCarVendorXcallManager;
    private CarVendorXcallMgr mCarVendorXcallMgr;
    private Context mContext;
    private View mLastmodeView;
    private IModel[] mModels;
    private WindowManager.LayoutParams mParams;
    private IProxy[] mProxies;
    private IRouteGuidanceListener mRouteGuidanceListener;
    private WindowManager mWindowManager;
    private ICarPlayAppManager mCarPlayAppManager = null;
    private Vector<ICarPlayRemoteManagerListener> mListeners = new Vector<>();
    private boolean mIsViewAdd = false;
    private boolean mIsDeviceProvisioned = false;
    private boolean mIsInit = false;
    private int mCurrentScreenRotationStatus = 0;
    private boolean isVideoShow = false;
    private ICarPlayServiceManager mCarPlayServiceManager = null;
    private int mCurrentScreenAngle = 0;
    private BYDAutoSettingDevice mBydAutoSettingDevice = null;
    private final AbsBYDAutoSettingListener mAbsBydAutoSettingListener = new AbsBYDAutoSettingListener() { // from class: com.ts.carplay.app.service.manager.CarPlayRemoteManager.1
        @Override // android.hardware.bydauto.setting.AbsBYDAutoSettingListener
        public void onScreenAngleChanged(int angle) {
            if (CarPlayRemoteManager.this.mCurrentScreenAngle != angle) {
                if (CarPlayRemoteManager.this.isVideoShow && angle == 2) {
                    LogUtil.debug("AbsBYDAutoSettingListener", "onScreenAngleChanged preScreenAngle: " + CarPlayRemoteManager.this.mCurrentScreenAngle);
                    CarPlayRemoteManager.this.setScreenRotationS(1);
                }
                CarPlayRemoteManager.this.mCurrentScreenAngle = angle;
                LogUtil.debug("AbsBYDAutoSettingListener", "onScreenAngleChanged ScreenAngle: " + CarPlayRemoteManager.this.mCurrentScreenAngle);
            }
        }
    };
    private final IServiceConnectionListener mAppConnectionListener = new AnonymousClass2();
    private final IServiceConnectionListener mServiceConnectionListener = new AnonymousClass3();
    CarVendorSettingManager.OnCarSettingEventListener settingEventListener = new CarVendorSettingManager.OnCarSettingEventListener() { // from class: com.ts.carplay.app.service.manager.CarPlayRemoteManager.5
        @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager.OnCarSettingEventListener
        public void onSettingEventChange(SettingEventChangeValue value) {
            int rotationStatus = value.getScreenRotationStatus();
            if (CarPlayRemoteManager.this.mCurrentScreenRotationStatus != rotationStatus) {
                if (CarPlayRemoteManager.this.isVideoShow && rotationStatus == 1) {
                    LogUtil.debug(CarPlayRemoteManager.TAG, "onSettingEventChange preRotationStatus: " + CarPlayRemoteManager.this.mCurrentScreenRotationStatus);
                    CarPlayRemoteManager.this.setScreenRotationS(1);
                }
                CarPlayRemoteManager.this.mCurrentScreenRotationStatus = rotationStatus;
                LogUtil.debug(CarPlayRemoteManager.TAG, "onSettingEventChange screenRotationStatus: " + CarPlayRemoteManager.this.mCurrentScreenRotationStatus);
            }
        }

        @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager.OnCarSettingEventListener
        public void onSettingErrorEvent(int propertyId, int zone) {
        }
    };
    private CarVendorXcallManager.OnCarXcallEventListener mCarXcallEventListener = new AnonymousClass6();
    private ICarPlayServiceListener mCarPlayServiceListener = new AnonymousClass8();
    private Handler mMainHandler = new Handler(Looper.getMainLooper()) { // from class: com.ts.carplay.app.service.manager.CarPlayRemoteManager.9
        @Override // android.os.Handler
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            int i = msg.what;
            if (i != 0) {
                if (i == 1 && CarPlayRemoteManager.this.mWindowManager != null) {
                    try {
                        if (CarPlayRemoteManager.this.mLastmodeView.getWindowToken() != null) {
                            CarPlayRemoteManager.this.mWindowManager.removeViewImmediate(CarPlayRemoteManager.this.mLastmodeView);
                            CarPlayRemoteManager.this.mIsViewAdd = false;
                            return;
                        }
                        return;
                    } catch (IllegalArgumentException e) {
                        LogUtil.warning(CarPlayRemoteManager.TAG, "view not found");
                        return;
                    }
                }
                return;
            }
            LogUtil.warning(CarPlayRemoteManager.TAG, "move to main thread MSG_INITLASTMODE_VIEW.");
            if (CarPlayRemoteManager.this.mWindowManager != null && !CarPlayRemoteManager.this.mIsViewAdd) {
                CarPlayRemoteManager.this.mWindowManager.addView(CarPlayRemoteManager.this.mLastmodeView, CarPlayRemoteManager.this.mParams);
                CarPlayRemoteManager.this.mIsViewAdd = true;
            }
        }
    };

    /* JADX INFO: renamed from: com.ts.carplay.app.service.manager.CarPlayRemoteManager$2, reason: invalid class name */
    class AnonymousClass2 implements IServiceConnectionListener {
        AnonymousClass2() {
        }

        public void onServiceConnected(IPlatformService iPlatformService) {
            if (CarPlayRemoteManager.this.mCarPlayAppManager != null) {
                LogUtil.debug(CarPlayRemoteManager.TAG, "registerCarPlayStateListener.");
                try {
                    CarPlayRemoteManager.this.mCarPlayAppManager.registerCarPlayStateListener(CarPlayRemoteManager.this.mCarPlayStateListener);
                    CarPlayRemoteManager.this.mCarPlayAppManager.registerCarPlayAppListener(CarPlayRemoteManager.this.mCarPlayAppListener);
                } catch (RemoteException remoteException) {
                    remoteException.printStackTrace();
                }
            }
            CarPlayRemoteManager.this.mMainHandler.post(new Runnable() { // from class: com.ts.carplay.app.service.manager.-$$Lambda$CarPlayRemoteManager$2$ISNqL0feJ2lPuEzoQTjwdj6xPCA
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$onServiceConnected$0$CarPlayRemoteManager$2();
                }
            });
        }

        public /* synthetic */ void lambda$onServiceConnected$0$CarPlayRemoteManager$2() {
            CarPlayRemoteManager.this.delayInit();
        }

        public void onServiceDisconnected(IPlatformService iPlatformService) {
            if (CarPlayRemoteManager.this.mCarPlayAppManager != null) {
                LogUtil.debug(CarPlayRemoteManager.TAG, "unregisterCarPlayStateListener.");
                try {
                    CarPlayRemoteManager.this.mCarPlayAppManager.unregisterCarPlayStateListener(CarPlayRemoteManager.this.mCarPlayStateListener);
                    CarPlayRemoteManager.this.mCarPlayAppManager.unregisterCarPlayAppListener(CarPlayRemoteManager.this.mCarPlayAppListener);
                } catch (RemoteException remoteException) {
                    remoteException.printStackTrace();
                }
            }
        }
    }

    /* JADX INFO: renamed from: com.ts.carplay.app.service.manager.CarPlayRemoteManager$3, reason: invalid class name */
    class AnonymousClass3 implements IServiceConnectionListener {
        AnonymousClass3() {
        }

        public void onServiceConnected(IPlatformService iPlatformService) {
            if (CarPlayRemoteManager.this.mCarPlayServiceManager != null) {
                LogUtil.debug(CarPlayRemoteManager.TAG, "registerCarPlayServiceListener.");
                try {
                    CarPlayRemoteManager.this.mCarPlayServiceManager.registerCarPlayServiceListener(CarPlayRemoteManager.this.mCarPlayServiceListener);
                } catch (RemoteException ex) {
                    ex.printStackTrace();
                }
            } else {
                LogUtil.debug(CarPlayRemoteManager.TAG, "mCarPlayServiceManager is null.");
            }
            CarPlayRemoteManager.this.mMainHandler.post(new Runnable() { // from class: com.ts.carplay.app.service.manager.-$$Lambda$CarPlayRemoteManager$3$Yh7k1oyjcnPpAzS4r4fefQIS144
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$onServiceConnected$0$CarPlayRemoteManager$3();
                }
            });
        }

        public /* synthetic */ void lambda$onServiceConnected$0$CarPlayRemoteManager$3() {
            CarPlayRemoteManager.this.delayInit();
        }

        public void onServiceDisconnected(IPlatformService iPlatformService) {
            CarPlayRemoteManager.this.notifyCarPlayRemoteManagerListener(new Consumer() { // from class: com.ts.carplay.app.service.manager.-$$Lambda$CarPlayRemoteManager$3$7ESv_l1O9slCdndxtUP2ckae5oM
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    ((ICarPlayRemoteManagerListener) obj).onServiceStateChanged(false);
                }
            });
            if (CarPlayRemoteManager.this.mCarPlayServiceManager != null) {
                LogUtil.debug(CarPlayRemoteManager.TAG, "unregisterCarPlayServiceListener.");
                try {
                    CarPlayRemoteManager.this.mCarPlayServiceManager.unregisterCarPlayServiceListener(CarPlayRemoteManager.this.mCarPlayServiceListener);
                } catch (RemoteException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

    public CarPlayRemoteManager() {
        this.mModels = null;
        this.mProxies = null;
        this.mCarPlayStateListener = new CarPlayStateListener();
        this.mCarPlayAppListener = new CarPlayAppListener();
        this.mRouteGuidanceListener = new CarPlayRouteGuidanceListener();
        LogUtil.info(TAG, "CarPlayRemoteManager start");
        IModelFactory modeFactory = new ModelFactory();
        this.mModels = modeFactory.createModes();
        IProxyFactory proxyFactory = new ProxyFactory();
        this.mProxies = proxyFactory.createProxies();
    }

    public static synchronized CarPlayRemoteManager getInstance() {
        if (mCarPlayRemoteManager == null) {
            mCarPlayRemoteManager = new CarPlayRemoteManager();
        }
        return mCarPlayRemoteManager;
    }

    public void init(Context context) {
        LogUtil.debug(TAG, "init start.");
        this.mContext = context;
        ICarPlayServiceManager iCarPlayServiceManager = (ICarPlayServiceManager) ServiceProvider.get(ICarPlayServiceManager.class, context);
        this.mCarPlayServiceManager = iCarPlayServiceManager;
        if (iCarPlayServiceManager != null) {
            iCarPlayServiceManager.registerConnectionListener(this.mServiceConnectionListener);
        }
        ICarPlayAppManager iCarPlayAppManager = (ICarPlayAppManager) ServiceProvider.get(ICarPlayAppManager.class, context);
        this.mCarPlayAppManager = iCarPlayAppManager;
        if (iCarPlayAppManager != null) {
            LogUtil.debug(TAG, "registerConnectionListener: mAppConnectionListener Connected:" + this.mCarPlayAppManager.isServiceConnected());
            this.mCarPlayAppManager.registerConnectionListener(this.mAppConnectionListener);
        }
        BYDAutoSettingDevice bYDAutoSettingDevice = BYDAutoSettingDevice.getInstance(context);
        this.mBydAutoSettingDevice = bYDAutoSettingDevice;
        if (bYDAutoSettingDevice != null) {
            bYDAutoSettingDevice.registerListener(this.mAbsBydAutoSettingListener);
        }
        delayInit();
        connectXcallService();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void delayInit() {
        ICarPlayServiceManager iCarPlayServiceManager;
        ICarPlayAppManager iCarPlayAppManager;
        LogUtil.info(TAG, "delayInit start mIsInit:" + this.mIsInit);
        if (!this.mIsInit && (iCarPlayAppManager = this.mCarPlayAppManager) != null && this.mCarPlayServiceManager != null && iCarPlayAppManager.isServiceConnected() && this.mCarPlayServiceManager.isServiceConnected()) {
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
            this.mWindowManager = (WindowManager) this.mContext.getSystemService("window");
            this.mLastmodeView = new LinearLayout(this.mContext);
            initView();
            this.mIsInit = true;
        }
        if (this.mIsInit && (iCarPlayServiceManager = this.mCarPlayServiceManager) != null && iCarPlayServiceManager.isServiceConnected()) {
            notifyCarPlayRemoteManagerListener(new Consumer() { // from class: com.ts.carplay.app.service.manager.-$$Lambda$CarPlayRemoteManager$lyHmMZuur6Npa04Pgja8XXZgN3o
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    ((ICarPlayRemoteManagerListener) obj).onServiceStateChanged(true);
                }
            });
            try {
                final int state = this.mCarPlayServiceManager.getCarPlaySessionStatus();
                notifyCarPlayRemoteManagerListener(new Consumer() { // from class: com.ts.carplay.app.service.manager.-$$Lambda$CarPlayRemoteManager$CpWyFE42wLhMOfk3Wq6TV3Ox9Tc
                    @Override // java.util.function.Consumer
                    public final void accept(Object obj) {
                        ((ICarPlayRemoteManagerListener) obj).onCarPlaySessionStateChanged(state);
                    }
                });
            } catch (RemoteException exception) {
                exception.printStackTrace();
            }
        }
        LogUtil.info(TAG, "delayInit end mIsInit:" + this.mIsInit);
    }

    private void connectXcallService() {
        LogUtil.info(TAG, "connectXcallService XcallService init...");
        Car carCreateCar = Car.createCar(this.mContext, new CarConnectionCallback() { // from class: com.ts.carplay.app.service.manager.CarPlayRemoteManager.4
            @Override // com.ts.lib.car.CarConnectionCallback
            public void onConnected(Car car) {
                try {
                    if (car != null) {
                        CarPlayRemoteManager.this.mCarVendorXcallManager = (CarVendorXcallManager) car.getCarManager(Car.VENDOR_XCALL_SERVICE);
                        CarPlayRemoteManager.this.mCarVendorXcallMgr = (CarVendorXcallMgr) car.getCarManager(Car.VENDOR_XCALL_SERVICE);
                        CarPlayRemoteManager.this.mCarVendorSettingMgr = (CarVendorSettingMgr) car.getCarManager(Car.VENDOR_SETTING_SERVICE);
                        StringBuilder sb = new StringBuilder();
                        sb.append("mCarVendorSettingMgr ");
                        sb.append(CarPlayRemoteManager.this.mCarVendorSettingMgr == null);
                        LogUtil.debug(CarPlayRemoteManager.TAG, sb.toString());
                        if (CarPlayRemoteManager.this.mCarVendorSettingMgr != null) {
                            CarPlayRemoteManager.this.mCarVendorSettingMgr.addCarSettingEventListener(CarPlayRemoteManager.this.settingEventListener);
                        }
                        if (CarPlayRemoteManager.this.mCarVendorXcallManager != null) {
                            CarPlayRemoteManager.this.mCarVendorXcallManager.addCarXcallEventListener(CarPlayRemoteManager.this.mCarXcallEventListener);
                            return;
                        }
                        return;
                    }
                    LogUtil.info(CarPlayRemoteManager.TAG, "connectXcallService car is null!");
                } catch (CarNotConnectedException exception) {
                    exception.printStackTrace();
                }
            }

            @Override // com.ts.lib.car.CarConnectionCallback
            public void onDisconnected(Car car) {
                if (CarPlayRemoteManager.this.mCarClient != null) {
                    CarPlayRemoteManager.this.mCarClient = null;
                }
            }
        });
        this.mCarClient = carCreateCar;
        if (carCreateCar != null) {
            carCreateCar.connect();
        }
    }

    public boolean isNeedChangeToLandSpace() throws Settings.SettingNotFoundException {
        int screenRotation = this.mContext.getResources().getConfiguration().orientation;
        int screenAngle = BYDAutoSettingDevice.getInstance(this.mContext).getScreenAngle();
        int sensorRotation = -1;
        try {
            sensorRotation = Settings.System.getInt(this.mContext.getContentResolver(), SENSOR_ORIENTATION_VALUE);
        } catch (Settings.SettingNotFoundException e) {
            LogUtil.error(TAG, "isNeedChangeToLandSpace:Unable to obtain SensorRotation settings");
        }
        boolean needChangeToLandSpace = true;
        if (screenRotation != 1 && this.mCurrentScreenRotationStatus != 1 && screenAngle != 2 && this.mCurrentScreenAngle != 2 && sensorRotation != 3) {
            needChangeToLandSpace = false;
        }
        LogUtil.info(TAG, "isNeedChangeToLandSpace screenRotation = " + screenRotation + " mCurrentScreenRotationStatus " + this.mCurrentScreenRotationStatus + " screenAngle " + screenAngle + " mCurrentScreenAngle " + this.mCurrentScreenAngle + " sensorRotation " + sensorRotation + " needChangeToLandSpace " + needChangeToLandSpace);
        return needChangeToLandSpace;
    }

    public void uninit(Context context) {
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
        ICarPlayServiceManager iCarPlayServiceManager = this.mCarPlayServiceManager;
        if (iCarPlayServiceManager != null) {
            iCarPlayServiceManager.unregisterConnectionListener(this.mServiceConnectionListener);
            try {
                this.mCarPlayServiceManager.unregisterCarPlayServiceListener(this.mCarPlayServiceListener);
            } catch (RemoteException ex) {
                ex.printStackTrace();
            }
            this.mCarPlayServiceManager = null;
        }
        ICarPlayAppManager iCarPlayAppManager = this.mCarPlayAppManager;
        if (iCarPlayAppManager != null) {
            iCarPlayAppManager.unregisterConnectionListener(this.mAppConnectionListener);
            try {
                this.mCarPlayAppManager.unregisterCarPlayAppListener(this.mCarPlayAppListener);
                this.mCarPlayAppManager.unregisterCarPlayStateListener(this.mCarPlayStateListener);
            } catch (RemoteException ex2) {
                ex2.printStackTrace();
            }
            this.mCarPlayAppManager = null;
        }
        BYDAutoSettingDevice bYDAutoSettingDevice = this.mBydAutoSettingDevice;
        if (bYDAutoSettingDevice != null) {
            bYDAutoSettingDevice.unregisterListener(this.mAbsBydAutoSettingListener);
            this.mBydAutoSettingDevice = null;
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
        } catch (CarNotConnectedException exception) {
            exception.printStackTrace();
        }
        if (this.mCarVendorXcallMgr != null) {
            this.mCarVendorXcallMgr = null;
        }
        if (this.mCarVendorSettingMgr != null) {
            this.mCarVendorSettingMgr = null;
        }
        Car car = this.mCarClient;
        if (car != null) {
            car.disconnect();
            this.mCarClient = null;
        }
    }

    public void addCarPlayRemoteManagerListener(ICarPlayRemoteManagerListener listener) {
        if (listener == null) {
            LogUtil.warning(TAG, "addCarPlayRemoteManagerListener listener is null.");
            return;
        }
        synchronized (this.mListeners) {
            if (!this.mListeners.contains(listener)) {
                this.mListeners.add(listener);
            }
        }
    }

    public void registerRouteGuidanceListener() {
        if (this.mCarPlayAppManager != null) {
            try {
                LogUtil.debug(TAG, "registerRouteGuidanceListener.");
                this.mCarPlayAppManager.registerRouteGuidanceListener(this.mRouteGuidanceListener);
                return;
            } catch (RemoteException remoteException) {
                remoteException.printStackTrace();
                return;
            }
        }
        LogUtil.warning(TAG, "registerRouteGuidanceListener mCarPlayAppManager is null.");
    }

    public void unregisterRouteGuidanceListener() {
        if (this.mCarPlayAppManager != null) {
            try {
                LogUtil.debug(TAG, "unregisterRouteGuidanceListener.");
                this.mCarPlayAppManager.unregisterRouteGuidanceListener(this.mRouteGuidanceListener);
                return;
            } catch (RemoteException remoteException) {
                remoteException.printStackTrace();
                return;
            }
        }
        LogUtil.warning(TAG, "unregisterRouteGuidanceListener mCarPlayAppManager is null.");
    }

    public void requestSiri(int siriAction, long siriTriggerTimestamp) {
        LogUtil.info(TAG, "requestSiri siriAction:" + siriAction + " siriTriggerTimestamp:" + siriTriggerTimestamp);
        ICarPlayAppManager iCarPlayAppManager = this.mCarPlayAppManager;
        if (iCarPlayAppManager != null) {
            try {
                iCarPlayAppManager.requestSiri(siriAction, siriTriggerTimestamp);
            } catch (RemoteException ex) {
                ex.printStackTrace();
            }
        }
    }

    public void removeCarPlayRemoteManagerListener(ICarPlayRemoteManagerListener listener) {
        if (listener == null) {
            LogUtil.warning(TAG, "removeCarPlayRemoteManagerListener listener is null.");
            return;
        }
        synchronized (this.mListeners) {
            if (this.mListeners.contains(listener)) {
                this.mListeners.remove(listener);
            }
        }
    }

    public void setNightMode(boolean nightMode) {
        if (this.mCarPlayServiceManager != null) {
            try {
                LogUtil.debug(TAG, "setNightMode nightMode: " + nightMode);
                this.mCarPlayServiceManager.setNightMode(nightMode);
                return;
            } catch (RemoteException remoteException) {
                remoteException.printStackTrace();
                return;
            }
        }
        LogUtil.warning(TAG, "setNightMode mCarPlayServiceManager is null.");
    }

    public void setUiAppearanceMode(String uuid, int uiMode, int uiSetting) {
        if (this.mCarPlayAppManager != null) {
            try {
                LogUtil.debug(TAG, "setUiAppearanceMode uuid: " + LogEncrypt.MacAddressEncrypt(uuid) + " uiMode:" + uiMode + " uiSetting:" + uiSetting);
                this.mCarPlayAppManager.setUiAppearanceMode(uuid, uiMode, uiSetting);
                return;
            } catch (RemoteException remoteException) {
                remoteException.printStackTrace();
                return;
            }
        }
        LogUtil.warning(TAG, "setUiAppearanceMode mCarPlayAppManager is null.");
    }

    public void setMapAppearanceMode(String uuid, int mapMode, int mapSetting) {
        if (this.mCarPlayAppManager != null) {
            try {
                LogUtil.debug(TAG, "setMapAppearanceMode uuid: " + LogEncrypt.MacAddressEncrypt(uuid) + " mapMode:" + mapMode + " mapSetting:" + mapSetting);
                this.mCarPlayAppManager.setMapAppearanceMode(uuid, mapMode, mapSetting);
                return;
            } catch (RemoteException remoteException) {
                remoteException.printStackTrace();
                return;
            }
        }
        LogUtil.warning(TAG, "setUiAppearanceMode mCarPlayAppManager is null.");
    }

    public void setNativeAppState(int appState, boolean status, int speechMode) {
        if (this.mCarPlayServiceManager != null) {
            try {
                LogUtil.info(TAG, "setNativeAppState appState: " + appState + " status: " + status + " speechMode:" + speechMode);
                this.mCarPlayServiceManager.setNativeAppState(appState, status, speechMode);
            } catch (RemoteException remoteException) {
                remoteException.printStackTrace();
            }
        }
    }

    public void sendCarPlayKey(int keyCode, int keyAction) {
        ICarPlayServiceManager iCarPlayServiceManager = this.mCarPlayServiceManager;
        if (iCarPlayServiceManager != null) {
            try {
                iCarPlayServiceManager.sendCarPlayKey(keyCode, keyAction);
                return;
            } catch (RemoteException ex) {
                ex.printStackTrace();
                return;
            }
        }
        LogUtil.warning(TAG, "sendCarPlayKey mCarPlayServiceManager is null");
    }

    public void setCarPlayConfig(CarPlayConfig carPlayConfig) {
        ICarPlayServiceManager iCarPlayServiceManager = this.mCarPlayServiceManager;
        if (iCarPlayServiceManager != null) {
            try {
                iCarPlayServiceManager.setCarPlayConfig(carPlayConfig);
                return;
            } catch (RemoteException ex) {
                ex.printStackTrace();
                return;
            }
        }
        LogUtil.warning(TAG, "setCarPlayConfig mCarPlayServiceManager is null");
    }

    public void changeVideoFocus(int priority, boolean backgroundSupport, String borrowId, int action) {
        ICarPlayServiceManager iCarPlayServiceManager = this.mCarPlayServiceManager;
        if (iCarPlayServiceManager != null) {
            try {
                iCarPlayServiceManager.changeVideoFocus(priority, backgroundSupport, borrowId, action);
                return;
            } catch (RemoteException ex) {
                ex.printStackTrace();
                return;
            }
        }
        LogUtil.warning(TAG, "changeVideoFocus mCarPlayServiceManager is null");
    }

    public int getCarPlaySessionStatus() {
        ICarPlayServiceManager iCarPlayServiceManager = this.mCarPlayServiceManager;
        if (iCarPlayServiceManager != null) {
            try {
                return iCarPlayServiceManager.getCarPlaySessionStatus();
            } catch (RemoteException ex) {
                ex.printStackTrace();
                return 0;
            }
        }
        LogUtil.warning(TAG, "getCarPlaySessionStatus mCarPlayServiceManager is null");
        return 0;
    }

    public void setLastUserMode(int resource, boolean mode) {
        ICarPlayServiceManager iCarPlayServiceManager = this.mCarPlayServiceManager;
        if (iCarPlayServiceManager != null) {
            try {
                iCarPlayServiceManager.setLastUserMode(resource, mode);
                return;
            } catch (RemoteException ex) {
                ex.printStackTrace();
                return;
            }
        }
        LogUtil.warning(TAG, "setLastUserMode mCarPlayServiceManager is null");
    }

    public boolean getLastUserMode(int resource) {
        ICarPlayServiceManager iCarPlayServiceManager = this.mCarPlayServiceManager;
        if (iCarPlayServiceManager != null) {
            try {
                return iCarPlayServiceManager.getLastUserMode(resource);
            } catch (RemoteException ex) {
                ex.printStackTrace();
                return false;
            }
        }
        LogUtil.warning(TAG, "getLastUserMode mCarPlayServiceManager is null");
        return false;
    }

    public void setDeviceProvisioned(boolean isDeviceProvisioned) {
        this.mIsDeviceProvisioned = isDeviceProvisioned;
    }

    private boolean isDeviceProvisioned() {
        return this.mIsDeviceProvisioned;
    }

    public void setEnhancedSiriInfo(List<String> voiceModelSupportedLanguages, String voiceModelCurrentLanguage, int supportedSiriTriggerZones) {
        LogUtil.info(TAG, "setEnhancedSiriInfo");
        ICarPlayAppManager iCarPlayAppManager = this.mCarPlayAppManager;
        if (iCarPlayAppManager == null) {
            LogUtil.info(TAG, "mCarPlayAppManager = null");
            return;
        }
        try {
            iCarPlayAppManager.setEnhancedSiriInfo(voiceModelSupportedLanguages, voiceModelCurrentLanguage, supportedSiriTriggerZones);
        } catch (RemoteException ex) {
            ex.printStackTrace();
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

    /* JADX INFO: renamed from: com.ts.carplay.app.service.manager.CarPlayRemoteManager$6, reason: invalid class name */
    class AnonymousClass6 implements CarVendorXcallManager.OnCarXcallEventListener {
        AnonymousClass6() {
        }

        @Override // com.ts.lib.car.hardware.xcall.CarVendorXcallManager.OnCarXcallEventListener
        public void onXcallEventChange(XcallEventChangeValue value) {
            final int callState = value.getCallStatusR();
            LogUtil.info(CarPlayRemoteManager.TAG, "OnCarXcallEventListener onXcallEventChange callState: " + callState);
            CarPlayRemoteManager.this.notifyCarPlayRemoteManagerListener(new Consumer() { // from class: com.ts.carplay.app.service.manager.-$$Lambda$CarPlayRemoteManager$6$wCy99YlQWn_uEd50lMC4LP54Mh0
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    ((ICarPlayRemoteManagerListener) obj).onEcallStatusChanged(callState);
                }
            });
        }

        @Override // com.ts.lib.car.hardware.xcall.CarVendorXcallManager.OnCarXcallEventListener
        public void onXcallErrorEvent(int propertyId, int zone) {
        }
    }

    private void initView() {
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams(1, 1, 2010, 262184, -2);
        this.mParams = layoutParams;
        layoutParams.gravity = 51;
        this.mLastmodeView.setLayoutParams(this.mParams);
        this.mLastmodeView.setOnTouchListener(new AnonymousClass7());
    }

    /* JADX INFO: renamed from: com.ts.carplay.app.service.manager.CarPlayRemoteManager$7, reason: invalid class name */
    class AnonymousClass7 implements View.OnTouchListener {
        AnonymousClass7() {
        }

        @Override // android.view.View.OnTouchListener
        public boolean onTouch(View v, MotionEvent event) {
            if (CarPlayRemoteManager.this.mWindowManager != null) {
                try {
                    if (CarPlayRemoteManager.this.mLastmodeView.getWindowToken() != null) {
                        CarPlayRemoteManager.this.mWindowManager.removeViewImmediate(CarPlayRemoteManager.this.mLastmodeView);
                        CarPlayRemoteManager.this.mIsViewAdd = false;
                    }
                } catch (IllegalArgumentException e) {
                    LogUtil.warning(CarPlayRemoteManager.TAG, "view not found");
                }
            }
            CarPlayRemoteManager.this.mMainHandler.post(new Runnable() { // from class: com.ts.carplay.app.service.manager.-$$Lambda$CarPlayRemoteManager$7$yi4OdtCohlG4fpMS03U1xvu_3mw
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$onTouch$0$CarPlayRemoteManager$7();
                }
            });
            return false;
        }

        public /* synthetic */ void lambda$onTouch$0$CarPlayRemoteManager$7() {
            if (CarPlayRemoteManager.this.mCarPlayServiceManager != null) {
                try {
                    CarPlayRemoteManager.this.mCarPlayServiceManager.setLastUserMode(1, false);
                } catch (RemoteException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    class CarPlayStateListener implements ICarPlayStateListener {
        private CarPlayStateListener() {
        }

        public void onAppStateChanged(final int appState, final boolean enabled, final int speechMode) {
            CarPlayRemoteManager.this.notifyCarPlayRemoteManagerListener(new Consumer() { // from class: com.ts.carplay.app.service.manager.-$$Lambda$CarPlayRemoteManager$CarPlayStateListener$HVqjuHrM6Cz27Sm6hhbPR3BjwSs
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    ((ICarPlayRemoteManagerListener) obj).onAppStatesChanged(appState, enabled, speechMode);
                }
            });
        }

        public void onSessionStateChanged(final int status) {
            CarPlayRemoteManager.this.notifyCarPlayRemoteManagerListener(new Consumer() { // from class: com.ts.carplay.app.service.manager.-$$Lambda$CarPlayRemoteManager$CarPlayStateListener$sGqpTEZjauw2c488lm05FkfCV6E
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    ((ICarPlayRemoteManagerListener) obj).onCarPlaySessionStateChanged(status);
                }
            });
            if (status == 2) {
                CarPlayRemoteManager.this.mMainHandler.sendEmptyMessage(1);
            } else if (status == 4) {
                CarPlayRemoteManager.this.mMainHandler.sendEmptyMessage(0);
            }
        }

        public void onSessionConnectStateChanged(int status, boolean isOldConnection, String clientAddr) {
            LogUtil.info(CarPlayRemoteManager.TAG, "onSessionConnectStateChanged status:" + status + " isOldConnection:" + isOldConnection + " clientaddr:" + LogEncrypt.MacAddressEncrypt(clientAddr));
        }

        public void onCarPlaySessionConnectStateChanged(int status, boolean isOldConnection, String clientAddr, boolean isUserConnect) {
            LogUtil.info(CarPlayRemoteManager.TAG, "onCarPlaySessionConnectStateChanged status:" + status + " isOldConnection:" + isOldConnection + " clientaddr:" + LogEncrypt.MacAddressEncrypt(clientAddr) + " isUserConnect:" + isUserConnect);
        }

        public void onCarPlayMediaFocusStateChanged(final boolean isCarPlayMedia) {
            CarPlayRemoteManager.this.notifyCarPlayRemoteManagerListener(new Consumer() { // from class: com.ts.carplay.app.service.manager.-$$Lambda$CarPlayRemoteManager$CarPlayStateListener$lAJUCcO8ipTHfnD39G9UrqaQC_o
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    ((ICarPlayRemoteManagerListener) obj).onCarPlayMediaFocusState(isCarPlayMedia);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    class CarPlayAppListener implements ICarPlayAppListener {
        private CarPlayAppListener() {
        }

        public void onEnhancedSiriParametersChanged(final int voiceActivationMode, final String voiceModelLanguage) {
            LogUtil.warning(CarPlayRemoteManager.TAG, "onEnhancedSiriParametersChanged voiceActivationMode:" + voiceActivationMode + " voiceModelLanguage:" + voiceModelLanguage);
            CarPlayRemoteManager.this.notifyCarPlayRemoteManagerListener(new Consumer() { // from class: com.ts.carplay.app.service.manager.-$$Lambda$CarPlayRemoteManager$CarPlayAppListener$tXuqLYSyK4_SccW-ZSideFAF_WA
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    ((ICarPlayRemoteManagerListener) obj).onEnhancedSiriParametersChanged(voiceActivationMode, voiceModelLanguage);
                }
            });
        }

        public void onCarPlayDisplayStatusChanged(final boolean isShow) {
            LogUtil.warning(CarPlayRemoteManager.TAG, "onCarPlayDisplayStatusChanged isShow:" + isShow);
            CarPlayRemoteManager.this.isVideoShow = isShow;
            CarPlayRemoteManager.this.notifyCarPlayRemoteManagerListener(new Consumer() { // from class: com.ts.carplay.app.service.manager.-$$Lambda$CarPlayRemoteManager$CarPlayAppListener$fC_SF3yde2iB2dQ0c26JzvG2zOs
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    ((ICarPlayRemoteManagerListener) obj).onCarPlayDisplayStatusChanged(isShow);
                }
            });
        }

        public void onSuggestUi(final int url) {
            LogUtil.info(CarPlayRemoteManager.TAG, "onSuggestUi url:" + url);
            CarPlayRemoteManager.this.notifyCarPlayRemoteManagerListener(new Consumer() { // from class: com.ts.carplay.app.service.manager.-$$Lambda$CarPlayRemoteManager$CarPlayAppListener$AOfIHSN3vPkN-F5HdpUsnpfXYHI
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    ((ICarPlayRemoteManagerListener) obj).onSuggestUi(url);
                }
            });
        }

        public void onAuxInUpdateState(final int state) {
            LogUtil.info(CarPlayRemoteManager.TAG, "onAuxInUpdateState state:" + state);
            CarPlayRemoteManager.this.notifyCarPlayRemoteManagerListener(new Consumer() { // from class: com.ts.carplay.app.service.manager.-$$Lambda$CarPlayRemoteManager$CarPlayAppListener$qshXY2FZ9LtC-n7jvJZlEmdn6Uw
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    ((ICarPlayRemoteManagerListener) obj).onAuxInUpdateState(state);
                }
            });
        }

        public void onAltRequestSetup() {
            LogUtil.info(CarPlayRemoteManager.TAG, "onAltRequestSetup");
            CarPlayRemoteManager.this.notifyCarPlayRemoteManagerListener(new Consumer() { // from class: com.ts.carplay.app.service.manager.-$$Lambda$CarPlayRemoteManager$CarPlayAppListener$h93vyuRkwf80oj8wPflN8QonS0Q
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    ((ICarPlayRemoteManagerListener) obj).onAltRequestSetup();
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    class CarPlayRouteGuidanceListener implements IRouteGuidanceListener {
        private CarPlayRouteGuidanceListener() {
        }

        public void onRouteGuidanceInfoChanged(final RouteGuidanceInfo routeGuidanceInfo) {
            CarPlayRemoteManager.this.notifyCarPlayRemoteManagerListener(new Consumer() { // from class: com.ts.carplay.app.service.manager.-$$Lambda$CarPlayRemoteManager$CarPlayRouteGuidanceListener$w2Ej7a9bn0_Yr45j7u3ey4Dn0H4
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    ((ICarPlayRemoteManagerListener) obj).onRouteGuidanceUpdate(routeGuidanceInfo);
                }
            });
        }
    }

    public String getBluetoothMacAddress() {
        if (this.mCarPlayServiceManager != null) {
            try {
                LogUtil.debug(TAG, "getBluetoothMacAddress enter");
                return this.mCarPlayServiceManager.getBluetoothMacAddress();
            } catch (RemoteException remoteException) {
                remoteException.printStackTrace();
                return null;
            }
        }
        LogUtil.warning(TAG, "getBluetoothMacAddress mCarPlayServiceManager is null.");
        return null;
    }

    public void updateViewArea(String uuid, int index, int[] adjacentViewAreas, int animationDurationMillis) {
        if (this.mCarPlayServiceManager != null) {
            LogUtil.info(TAG, "updateViewArea index：" + index + ",uuid" + LogEncrypt.MacAddressEncrypt(uuid) + ",array:" + adjacentViewAreas.toString() + ",animationDurationMillis:" + animationDurationMillis);
            try {
                this.mCarPlayServiceManager.updateViewArea(uuid, index, adjacentViewAreas, animationDurationMillis);
            } catch (RemoteException ex) {
                ex.printStackTrace();
            }
        }
    }

    public void notifyUiConfigurationChanged(final Configuration newConfig) {
        LogUtil.debug(TAG, "notifyUiConfigurationChanged newConfig" + newConfig);
        notifyCarPlayRemoteManagerListener(new Consumer() { // from class: com.ts.carplay.app.service.manager.-$$Lambda$CarPlayRemoteManager$vA0CdPPPZDynQDkfjsg49KrkIwU
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                ((ICarPlayRemoteManagerListener) obj).onConfigurationChanged(newConfig);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void notifyCarPlayRemoteManagerListener(Consumer<ICarPlayRemoteManagerListener> c) {
        List<ICarPlayRemoteManagerListener> list;
        synchronized (this.mListeners) {
            list = new ArrayList<>(this.mListeners);
        }
        for (ICarPlayRemoteManagerListener listener : list) {
            c.accept(listener);
        }
    }

    /* JADX INFO: renamed from: com.ts.carplay.app.service.manager.CarPlayRemoteManager$8, reason: invalid class name */
    class AnonymousClass8 implements ICarPlayServiceListener {
        AnonymousClass8() {
        }

        public void onAppStateChanged(final int appState, final int entity, final int speechMode) {
            CarPlayRemoteManager.this.notifyCarPlayRemoteManagerListener(new Consumer() { // from class: com.ts.carplay.app.service.manager.-$$Lambda$CarPlayRemoteManager$8$wMFFv950ssDItdgQ0PVIbAFsXoA
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    ((ICarPlayRemoteManagerListener) obj).onAppStatesChanged(appState, entity, speechMode);
                }
            });
        }

        public void onRequestVideoFocusChanged(int requestType) {
            LogUtil.warning(CarPlayRemoteManager.TAG, "onRequestVideoFocusChanged enter");
            if (requestType == 0) {
                Intent intent = new Intent();
                intent.setFlags(VehicleVendorProperty.VehiclePropertyGroup.SYSTEM);
                try {
                    intent.setComponent(new ComponentName(CarPlayRemoteManager.APPLICATION_PACKAGE_NAME, CarPlayRemoteManager.APPLICATION_CLASS_NAME));
                    intent.setAction("DeviceAsk");
                    CarPlayRemoteManager.this.mContext.startActivity(intent);
                } catch (ActivityNotFoundException e) {
                    LogUtil.warning(CarPlayRemoteManager.TAG, "requestVideoFocusChange unable to start activity intent:" + intent);
                }
            }
        }
    }
}
