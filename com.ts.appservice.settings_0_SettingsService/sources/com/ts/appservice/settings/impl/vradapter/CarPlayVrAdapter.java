package com.ts.appservice.settings.impl.vradapter;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.SystemClock;
import com.ts.appservice.settings.impl.qnx.SettingsQnxService;
import com.ts.avm.sdk.AvmManager;
import com.ts.avm.sdk.IAvmServiceConnectionListener;
import com.ts.car.core.ServiceProvider;
import com.ts.lib.car.Car;
import com.ts.lib.car.CarConnectionCallback;
import com.ts.lib.car.CarNotConnectedException;
import com.ts.lib.car.hardware.xcall.CarVendorXcallManager;
import com.ts.lib.car.hardware.xcall.CarVendorXcallMgr;
import com.ts.lib.car.hardware.xcall.XcallEventChangeValue;
import com.ts.lib.settings.common.utilities.LogUtil;
import ts.car.service.IPlatformService;
import ts.car.service.IServiceConnectionListener;
import ts.car.service.carplay.ICarPlayAppManager;
import ts.car.service.carplay.ICarPlayStateListener;

/* JADX INFO: loaded from: classes.dex */
public class CarPlayVrAdapter {
    private static final String ACTION_ECALL_STATE_CHANGE = "com.byd.system.ecall.ACTION_EVENT_CHANGE";
    public static final int CPAA_CONNECTED_STATUS = 1;
    public static final int CPAA_NOT_CONNECTED_STATUS = 0;
    private static final String ECALL_WIDGET_EXTRA_STATE = "isShow";
    private static final int ECALL_WIDGET_EXTRA_STATE_HIDEN = 0;
    private static final int ECALL_WIDGET_EXTRA_STATE_SHOWN = 1;
    private static final String TAG = CarPlayVrAdapter.class.getSimpleName();
    private static final int XCALL_STATUS_CALL_CONNECTED = 6;
    private static final int XCALL_STATUS_CALL_CONNECTING = 5;
    private static final int XCALL_STATUS_CALL_DATA_SENDING = 7;
    private static final int XCALL_STATUS_CALL_END = 8;
    private static final int XCALL_STATUS_CALL_FAILED = 4;
    private static final int XCALL_STATUS_CALL_FAILED_RECONNECTION = 3;
    private static final int XCALL_STATUS_CALL_START = 1;
    private static final int XCALL_STATUS_E_CALL_PRECALL = 10;
    private static final int XCALL_STATUS_INCOMING_CALL = 2;
    private static final int XCALL_STATUS_INVALID = 0;
    private static volatile CarPlayVrAdapter mCarPlayVrAdapter;
    private Car mCarClient;
    private ICarPlayAppManager mCarPlayAppManager;
    private CarVendorXcallManager mCarVendorXcallManager;
    private CarVendorXcallMgr mCarVendorXcallMgr;
    private Context mContext;
    private SettingsQnxService mSettingsQnxService;
    private boolean mIsConnected = false;
    private int mSessionState = -1;
    private boolean mSiriState = false;
    private boolean mIsAvm = false;
    private boolean mIsEcallActive = false;
    private ICarPlayStateListener mCarPlayStateListener = new ICarPlayStateListener() { // from class: com.ts.appservice.settings.impl.vradapter.CarPlayVrAdapter.1
        public void onSessionStateChanged(int status) {
            LogUtil.info(CarPlayVrAdapter.TAG, "onSessionStateChanged status:" + status + " old status : " + CarPlayVrAdapter.this.mSessionState);
            CarPlayVrAdapter.this.mSessionState = status;
            if (CarPlayVrAdapter.this.mSessionState == 2) {
                CarPlayVrAdapter.this.mSettingsQnxService.setCpaaConnectState(1);
                return;
            }
            int aaSessionStatus = AndroidAutoVrAdapter.getInstance(CarPlayVrAdapter.this.mContext).getAndroidAutoSessionStatus();
            LogUtil.info(CarPlayVrAdapter.TAG, "onSessionStateChanged to get getAndroidAutoSessionStatus status:" + aaSessionStatus);
            if (aaSessionStatus != 4) {
                CarPlayVrAdapter.this.mSettingsQnxService.setCpaaConnectState(0);
            }
        }

        public void onAppStateChanged(int appState, boolean enabled, int speechMode) {
            LogUtil.info(CarPlayVrAdapter.TAG, "onAppStateChanged appState:" + appState + "enabled: " + enabled);
            if (appState == 2) {
                CarPlayVrAdapter.this.mSiriState = enabled;
            }
        }
    };
    private final IServiceConnectionListener mServiceConnectionListener = new IServiceConnectionListener() { // from class: com.ts.appservice.settings.impl.vradapter.CarPlayVrAdapter.2
        public void onServiceConnected(IPlatformService service) {
            CarPlayVrAdapter.this.mIsConnected = true;
            LogUtil.error(CarPlayVrAdapter.TAG, "carPlay Service connect");
            try {
                if (CarPlayVrAdapter.this.mCarPlayAppManager != null) {
                    CarPlayVrAdapter.this.mCarPlayAppManager.registerCarPlayStateListener(CarPlayVrAdapter.this.mCarPlayStateListener);
                } else {
                    LogUtil.error(CarPlayVrAdapter.TAG, "mCarPlayAppManager null");
                }
                CarPlayVrAdapter.this.mSessionState = CarPlayVrAdapter.this.getCarPlaySessionStatus();
                LogUtil.warning(CarPlayVrAdapter.TAG, "mServiceConnectionListener onServiceConnected to getCarPlaySessionStatus:" + CarPlayVrAdapter.this.mSessionState);
                if (CarPlayVrAdapter.this.mSessionState == 2) {
                    CarPlayVrAdapter.this.mSettingsQnxService.setCpaaConnectState(1);
                }
            } catch (Exception ex) {
                LogUtil.error(CarPlayVrAdapter.TAG, "registerCarPlayStateListener error:", ex);
            }
        }

        public void onServiceDisconnected(IPlatformService service) {
            CarPlayVrAdapter.this.mIsConnected = false;
            LogUtil.error(CarPlayVrAdapter.TAG, "carPlay Service Disconnect");
            try {
                if (CarPlayVrAdapter.this.mCarPlayAppManager != null) {
                    CarPlayVrAdapter.this.mCarPlayAppManager.unregisterCarPlayStateListener(CarPlayVrAdapter.this.mCarPlayStateListener);
                } else {
                    LogUtil.error(CarPlayVrAdapter.TAG, "mCarPlayAppManager null");
                }
            } catch (Exception ex) {
                LogUtil.error(CarPlayVrAdapter.TAG, "unregisterCarPlayStateListener error:", ex);
            }
        }
    };
    private CarVendorXcallManager.OnCarXcallEventListener mCarXcallEventListener = new CarVendorXcallManager.OnCarXcallEventListener() { // from class: com.ts.appservice.settings.impl.vradapter.CarPlayVrAdapter.4
        public void onXcallEventChange(XcallEventChangeValue value) {
            int callState = value.getCallStatusR();
            CarPlayVrAdapter.this.convertCallStatus(callState);
            LogUtil.info(CarPlayVrAdapter.TAG, "onXcallEventChange callState:" + callState);
        }

        public void onXcallErrorEvent(int propertyId, int zone) {
            LogUtil.info(CarPlayVrAdapter.TAG, "onXcallEventChange onXcallErrorEvent");
        }
    };
    private IAvmServiceConnectionListener mAvmConnection = new IAvmServiceConnectionListener() { // from class: com.ts.appservice.settings.impl.vradapter.CarPlayVrAdapter.5
        @Override // com.ts.avm.sdk.IAvmServiceConnectionListener
        public void onServiceConnected() {
            LogUtil.info(CarPlayVrAdapter.TAG, "mAvmConnection.onServiceConnected()");
            int ret = AvmManager.getInstance().registerAvmStatusListener(CarPlayVrAdapter.this.mAvmStatusListener);
            int avmWorkState = AvmManager.getInstance().getAvmWorkState();
            LogUtil.info(CarPlayVrAdapter.TAG, "mAvmConnection ret=" + ret + ", avmWorkState=" + avmWorkState);
            CarPlayVrAdapter.this.mIsAvm = 2 == avmWorkState;
        }

        @Override // com.ts.avm.sdk.IAvmServiceConnectionListener
        public void onServiceDisConnected() {
            LogUtil.info(CarPlayVrAdapter.TAG, "mAvmConnection.onServiceDisConnected()");
            AvmManager.getInstance().unregisterAvmStatusListener(CarPlayVrAdapter.this.mAvmStatusListener);
        }

        @Override // com.ts.avm.sdk.IAvmServiceConnectionListener
        public void onError(int i, String s) {
            LogUtil.info(CarPlayVrAdapter.TAG, "mAvmConnection.onError()");
        }
    };
    private AvmManager.AvmStatusListener mAvmStatusListener = new AvmManager.AvmStatusListener() { // from class: com.ts.appservice.settings.impl.vradapter.CarPlayVrAdapter.6
        @Override // com.ts.avm.sdk.AvmManager.AvmStatusListener
        public void onStatusChanged(int code, String message) {
            LogUtil.info(CarPlayVrAdapter.TAG, "mAvmStatusListener.onStatusChanged() code=" + code + ", message=" + message + " // code != 2 means background");
            CarPlayVrAdapter.this.mIsAvm = 2 == code;
        }
    };
    BroadcastReceiver mEcallWidgetStateReceiver = new BroadcastReceiver() { // from class: com.ts.appservice.settings.impl.vradapter.CarPlayVrAdapter.7
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            try {
                String action = intent.getAction();
                int state = intent.getIntExtra(CarPlayVrAdapter.ECALL_WIDGET_EXTRA_STATE, 0);
                if (action == null) {
                    return;
                }
                if (!CarPlayVrAdapter.ACTION_ECALL_STATE_CHANGE.equals(action) || 1 != state) {
                    CarPlayVrAdapter.this.mIsEcallActive = false;
                } else {
                    CarPlayVrAdapter.this.mIsEcallActive = true;
                }
                LogUtil.info(CarPlayVrAdapter.TAG, "mEcallWidgetStateReceiver.onReceive(), action=" + action + ", state=" + state + ", mIsEcallActive:" + CarPlayVrAdapter.this.mIsEcallActive);
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
    };

    public static CarPlayVrAdapter getInstance(Context context) {
        LogUtil.error(TAG, "CarPlayVrAdapter getInstance called");
        if (mCarPlayVrAdapter == null) {
            synchronized (CarPlayVrAdapter.class) {
                if (mCarPlayVrAdapter == null) {
                    mCarPlayVrAdapter = new CarPlayVrAdapter(context);
                }
            }
        }
        return mCarPlayVrAdapter;
    }

    public CarPlayVrAdapter(Context context) {
        this.mCarPlayAppManager = null;
        this.mContext = context;
        this.mSettingsQnxService = SettingsQnxService.getInstance(context);
        ICarPlayAppManager iCarPlayAppManager = (ICarPlayAppManager) ServiceProvider.get(ICarPlayAppManager.class, context);
        this.mCarPlayAppManager = iCarPlayAppManager;
        if (iCarPlayAppManager != null) {
            iCarPlayAppManager.registerConnectionListener(this.mServiceConnectionListener);
        } else {
            LogUtil.error(TAG, "mCarPlayAppManager is null");
        }
        registerEcallStateReceiver(context);
        initAvm();
        initXcallManager();
    }

    private void initXcallManager() {
        LogUtil.debug(TAG, "initXcallManager mContext:" + this.mContext);
        Car carCreateCar = Car.createCar(this.mContext, new CarConnectionCallback() { // from class: com.ts.appservice.settings.impl.vradapter.CarPlayVrAdapter.3
            public void onConnected(Car car) {
                LogUtil.debug(CarPlayVrAdapter.TAG, "initXcallManager onConnected");
                try {
                    if (car == null) {
                        LogUtil.info(CarPlayVrAdapter.TAG, "connectXcallService car is null!");
                    } else {
                        CarPlayVrAdapter.this.mCarVendorXcallManager = (CarVendorXcallManager) car.getCarManager("xcall");
                        CarPlayVrAdapter.this.mCarVendorXcallMgr = (CarVendorXcallMgr) car.getCarManager("xcall");
                        if (CarPlayVrAdapter.this.mCarVendorXcallManager != null) {
                            CarPlayVrAdapter.this.mCarVendorXcallManager.addCarXcallEventListener(CarPlayVrAdapter.this.mCarXcallEventListener);
                        }
                    }
                } catch (CarNotConnectedException exception) {
                    exception.printStackTrace();
                }
            }

            public void onDisconnected(Car car) {
                if (CarPlayVrAdapter.this.mCarClient != null) {
                    LogUtil.error(CarPlayVrAdapter.TAG, "onDisconnected");
                    CarPlayVrAdapter.this.mCarClient = null;
                    CarPlayVrAdapter.this.mCarVendorXcallManager = null;
                    CarPlayVrAdapter.this.mCarVendorXcallMgr = null;
                }
            }
        });
        this.mCarClient = carCreateCar;
        carCreateCar.connect();
        LogUtil.error(TAG, "initXcall end car:" + this.mCarClient);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:6:0x000b  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void convertCallStatus(int r4) {
        /*
            r3 = this;
            r0 = 10
            if (r4 == r0) goto Lb
            switch(r4) {
                case 1: goto Lb;
                case 2: goto Lb;
                case 3: goto Lb;
                case 4: goto Lb;
                case 5: goto Lb;
                case 6: goto Lb;
                case 7: goto Lb;
                default: goto L7;
            }
        L7:
            r0 = 0
            r3.mIsEcallActive = r0
            goto Lf
        Lb:
            r0 = 1
            r3.mIsEcallActive = r0
        Lf:
            java.lang.String r0 = com.ts.appservice.settings.impl.vradapter.CarPlayVrAdapter.TAG
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "convertCallStatus ebCallStates is "
            r1.append(r2)
            r1.append(r4)
            java.lang.String r2 = " mIsEcallActive: "
            r1.append(r2)
            boolean r2 = r3.mIsEcallActive
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            com.ts.lib.settings.common.utilities.LogUtil.debug(r0, r1)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ts.appservice.settings.impl.vradapter.CarPlayVrAdapter.convertCallStatus(int):void");
    }

    public void requestSiri(int value) {
        LogUtil.info(TAG, "requestSiri value:" + value);
        if (this.mCarPlayAppManager == null || !this.mIsConnected) {
            LogUtil.error(TAG, "mCarPlayAppManager is not connect");
            return;
        }
        if (this.mIsEcallActive) {
            LogUtil.error(TAG, "mIsEcallActive is true,do not requestSiri");
            return;
        }
        int avmWorkState = AvmManager.getInstance().getAvmWorkState();
        LogUtil.error(TAG, "avmWorkState:" + avmWorkState + "mIsAvm: " + this.mIsAvm);
        boolean z = 2 == avmWorkState;
        this.mIsAvm = z;
        if (z) {
            LogUtil.error(TAG, "avm is foreground");
            return;
        }
        try {
            this.mCarPlayAppManager.requestSiri(value, SystemClock.uptimeMillis());
        } catch (Exception ex) {
            LogUtil.error(TAG, "requestSiri error: ", ex);
        }
    }

    public int getCarPlaySessionStatus() {
        LogUtil.info(TAG, "getCarPlaySessionStatus ");
        ICarPlayAppManager iCarPlayAppManager = this.mCarPlayAppManager;
        if (iCarPlayAppManager == null || !this.mIsConnected) {
            LogUtil.error(TAG, "mCarPlayAppManager is not connect");
            return -1;
        }
        try {
            return iCarPlayAppManager.getCarPlaySessionStatus();
        } catch (Exception ex) {
            LogUtil.error(TAG, "getCarPlaySessionStatus error: ", ex);
            return -1;
        }
    }

    public boolean isSiriActive() {
        return this.mSiriState;
    }

    public boolean isCarPlayConnected() {
        return this.mIsConnected;
    }

    private void initAvm() {
        LogUtil.info(TAG, "initAvm()");
        AvmManager.getInstance().init(this.mContext, this.mAvmConnection);
    }

    private void uninitAvm() {
        LogUtil.info(TAG, "uninitAvm()");
        AvmManager.getInstance().deinit(this.mAvmConnection);
    }

    private void registerEcallStateReceiver(Context context) {
        IntentFilter filter = new IntentFilter();
        filter.addAction(ACTION_ECALL_STATE_CHANGE);
        context.registerReceiver(this.mEcallWidgetStateReceiver, filter);
    }

    public void destroy() {
        Context context = this.mContext;
        if (context != null) {
            context.unregisterReceiver(this.mEcallWidgetStateReceiver);
        }
    }
}
