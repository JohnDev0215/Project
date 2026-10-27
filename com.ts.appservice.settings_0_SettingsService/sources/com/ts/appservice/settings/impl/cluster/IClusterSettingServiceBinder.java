package com.ts.appservice.settings.impl.cluster;

import android.app.UiModeManager;
import android.content.ContentResolver;
import android.content.Context;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.os.RemoteException;
import android.provider.Settings;
import com.ts.appservice.settings.impl.caradapter.CarAdapterServiceManager;
import com.ts.appservice.settings.impl.qnx.SettingsQnxListener;
import com.ts.appservice.settings.impl.qnx.SettingsQnxService;
import com.ts.lib.caradapter.CarAdapterManager;
import com.ts.lib.caradapter.setting.SettingAdapterManager;
import com.ts.lib.settings.cluster.IClusterSettingsCallback;
import com.ts.lib.settings.cluster.IClusterSettingsService;
import com.ts.lib.settings.common.utilities.LogUtil;

/* JADX INFO: loaded from: classes.dex */
public class IClusterSettingServiceBinder extends IClusterSettingsService.Stub {
    private static final int DAY_MODE_VALUE = 2;
    private static final int MESSAGE_SERVICE_CAR_ADAPTER = 2;
    public static final int MSG_BYD_APP_THEME = 3;
    private static final int MSG_SCREEN_ANGLE = 1;
    private static final int NIGHT_MODE_VALUE = 1;
    private static final int RANDOM_RETRY_INTERVAL = 500;
    private static final int UI_MODE_NIGHT_NO = 19;
    private static final int UI_MODE_NIGHT_YES = 35;
    private boolean mCarAdapterBinderState;
    private CarAdapterManager mCarAdapterManager;
    private CarAdapterServiceManager mCarAdapterServiceManager;
    private ClusterSettingCallback mClusterSettingCallback;
    private ContentResolver mContentResolver;
    private Context mContext;
    private int mDayNightModeValue;
    private int mINSAdasCenterDisplayValue;
    private int mINSCenterDisplayValue;
    private ScreenAngleContentObserver mScreenAngleContentObserver;
    private Handler mScreenAngleHandler;
    private HandlerThread mScreenAngleThread;
    private SettingAdapterManager mSettingAdapterManager;
    private SettingsQnxListener mSettingsQnxListener = new SettingsQnxListener() { // from class: com.ts.appservice.settings.impl.cluster.IClusterSettingServiceBinder.1
        @Override // com.ts.appservice.settings.impl.qnx.SettingsQnxListener
        public void onQnxMessageChanged(int key, int value) {
            if (key == 1) {
                IClusterSettingServiceBinder.this.mClusterSettingCallback.onDayNightModeChanged(value);
            } else if (key == 3) {
                IClusterSettingServiceBinder.this.mClusterSettingCallback.onINSCenterDisplayChanged(value);
            } else if (key == 4) {
                IClusterSettingServiceBinder.this.mClusterSettingCallback.onAdasCenterDisplayChanged(value);
            }
        }
    };
    private SettingsQnxService mSettingsQnxService;
    private UiModeManager mUiModeManager;
    private VersatileThemeContentObserver mVersatileThemeContentObserver;
    private static final String TAG = IClusterSettingServiceBinder.class.getSimpleName();
    public static final Uri CONTENT_URI = Uri.parse("content://carsettings/settings");

    public IClusterSettingServiceBinder(Context context) {
        this.mContext = context;
        startScreenAngleThread();
        initContentObserver();
        this.mClusterSettingCallback = new ClusterSettingCallback(this.mContext);
        this.mUiModeManager = (UiModeManager) this.mContext.getSystemService("uimode");
        SettingsQnxService settingsQnxService = SettingsQnxService.getInstance(this.mContext);
        this.mSettingsQnxService = settingsQnxService;
        settingsQnxService.subscribeClusterCallback(this.mSettingsQnxListener);
        this.mScreenAngleHandler.sendEmptyMessage(2);
    }

    private void startScreenAngleThread() {
        HandlerThread handlerThread = new HandlerThread("ScreenAngle");
        this.mScreenAngleThread = handlerThread;
        handlerThread.start();
        this.mScreenAngleHandler = new Handler(this.mScreenAngleThread.getLooper()) { // from class: com.ts.appservice.settings.impl.cluster.IClusterSettingServiceBinder.2
            @Override // android.os.Handler
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                int i = msg.what;
                if (i == 1) {
                    if (IClusterSettingServiceBinder.this.mSettingAdapterManager != null) {
                        int angle = msg.arg1;
                        IClusterSettingServiceBinder.this.mSettingAdapterManager.setScreenAngleValueS(angle);
                        return;
                    }
                    return;
                }
                if (i != 2) {
                    if (i == 3) {
                        int versatileTheme = msg.arg1;
                        LogUtil.info(IClusterSettingServiceBinder.TAG, "byd app them mointor :" + versatileTheme);
                        IClusterSettingServiceBinder.this.mSettingsQnxService.setVersatileThemeState(versatileTheme);
                        return;
                    }
                    return;
                }
                if (IClusterSettingServiceBinder.this.mScreenAngleHandler.hasMessages(2)) {
                    IClusterSettingServiceBinder.this.mScreenAngleHandler.removeMessages(2);
                }
                IClusterSettingServiceBinder iClusterSettingServiceBinder = IClusterSettingServiceBinder.this;
                iClusterSettingServiceBinder.mCarAdapterServiceManager = CarAdapterServiceManager.getInstance(iClusterSettingServiceBinder.mContext.getApplicationContext());
                IClusterSettingServiceBinder iClusterSettingServiceBinder2 = IClusterSettingServiceBinder.this;
                iClusterSettingServiceBinder2.mCarAdapterBinderState = iClusterSettingServiceBinder2.mCarAdapterServiceManager.getCarAdapterBinderState();
                LogUtil.info(IClusterSettingServiceBinder.TAG, "mCarAdapterBinderState is " + IClusterSettingServiceBinder.this.mCarAdapterBinderState);
                if (!IClusterSettingServiceBinder.this.mCarAdapterBinderState) {
                    IClusterSettingServiceBinder.this.mScreenAngleHandler.sendEmptyMessageDelayed(2, 500L);
                    return;
                }
                IClusterSettingServiceBinder iClusterSettingServiceBinder3 = IClusterSettingServiceBinder.this;
                iClusterSettingServiceBinder3.mCarAdapterManager = CarAdapterManager.getInstance(iClusterSettingServiceBinder3.mContext.getApplicationContext());
                IClusterSettingServiceBinder iClusterSettingServiceBinder4 = IClusterSettingServiceBinder.this;
                iClusterSettingServiceBinder4.mSettingAdapterManager = (SettingAdapterManager) iClusterSettingServiceBinder4.mCarAdapterManager.getCarAdapterManager("setting");
            }
        };
    }

    private void initContentObserver() {
        this.mScreenAngleContentObserver = new ScreenAngleContentObserver(this.mContext, this.mScreenAngleHandler);
        this.mVersatileThemeContentObserver = new VersatileThemeContentObserver(this.mContext, this.mScreenAngleHandler);
        ContentResolver contentResolver = this.mContext.getContentResolver();
        this.mContentResolver = contentResolver;
        contentResolver.registerContentObserver(Settings.System.getUriFor("orientation_angle"), false, this.mScreenAngleContentObserver);
        this.mContentResolver.registerContentObserver(CONTENT_URI, true, this.mVersatileThemeContentObserver);
    }

    public int setINSAdasCenterDisplay(int value) {
        LogUtil.debug(TAG, "setINSAdasCenterDisplay value :" + value);
        return this.mSettingsQnxService.setINSAdasCenterDisplay(value);
    }

    public int getINSAdasCenterDisplay() {
        this.mINSAdasCenterDisplayValue = this.mSettingsQnxService.getInsAdasCenterDisplay();
        LogUtil.debug(TAG, "getINSAdasCenterDisplay value : " + this.mINSAdasCenterDisplayValue);
        return this.mINSAdasCenterDisplayValue;
    }

    public int setINSCenterDisplay(int value) {
        LogUtil.debug(TAG, "setINSCenterDisplay value :" + value);
        return this.mSettingsQnxService.setINSCenterDisplay(value);
    }

    public int getINSCenterDisplay() {
        this.mINSCenterDisplayValue = this.mSettingsQnxService.getInsCenterDisplay();
        LogUtil.debug(TAG, "getINSCenterDisplay value : " + this.mINSCenterDisplayValue);
        return this.mINSCenterDisplayValue;
    }

    public int setDayNightMode(int mode) {
        LogUtil.debug(TAG, "setDayNightMode mode :" + mode);
        return this.mSettingsQnxService.setDayNightMode(mode);
    }

    public int getDayNightMode() {
        this.mDayNightModeValue = this.mSettingsQnxService.getDayNightMode();
        LogUtil.debug(TAG, "getDayNightMode value : " + this.mDayNightModeValue);
        return this.mDayNightModeValue;
    }

    public int setTimeFormat(int value) {
        LogUtil.debug(TAG, "setTimeFormat value :" + value);
        return this.mSettingsQnxService.setTimeFormat(value);
    }

    public int setSpeedUpCountState(int state) {
        LogUtil.debug(TAG, "setSpeedUpCountState state :" + state);
        return this.mSettingsQnxService.setSpeedUpCountState(state);
    }

    public int setOverseasMapSupplier(int state) {
        LogUtil.debug(TAG, "setOverseasMapSupplier state :" + state);
        return this.mSettingsQnxService.setOverseasMapSupplier(state);
    }

    public void subscribeClusterCallback(IClusterSettingsCallback callback) throws RemoteException {
        if (callback != null) {
            this.mClusterSettingCallback.register(callback);
        }
    }

    public void unsubscribeClusterCallback(IClusterSettingsCallback callback) throws RemoteException {
        if (callback != null) {
            this.mClusterSettingCallback.unregister(callback);
        }
    }

    public void destoryClusterSettingBinder() {
        ClusterSettingCallback clusterSettingCallback = this.mClusterSettingCallback;
        if (clusterSettingCallback != null) {
            clusterSettingCallback.kill();
        }
        if (this.mSettingAdapterManager != null) {
            this.mSettingAdapterManager = null;
        }
    }

    public void onConfigurationChanged(Configuration newConfig) {
        int uiMode = newConfig.uiMode;
        int nightMode = this.mUiModeManager.getNightMode();
        LogUtil.debug(TAG, "current uiMode is " + uiMode + "nightMode is " + nightMode);
        if (nightMode == 0) {
            if (uiMode == 19) {
                setDayNightMode(2);
            } else if (uiMode == 35) {
                setDayNightMode(1);
            }
        }
    }
}
