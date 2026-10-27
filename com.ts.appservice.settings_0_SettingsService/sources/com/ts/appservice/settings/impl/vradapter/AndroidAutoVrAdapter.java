package com.ts.appservice.settings.impl.vradapter;

import android.content.Context;
import com.ts.appservice.settings.impl.qnx.SettingsQnxService;
import com.ts.car.core.ServiceProvider;
import com.ts.lib.settings.common.utilities.LogUtil;
import ts.car.service.IPlatformService;
import ts.car.service.IServiceConnectionListener;
import ts.car.service.androidauto.IAndroidAutoAppManager;
import ts.car.service.androidauto.IAndroidAutoSessionStatusListener;
import ts.car.service.androidauto.common.VehicleConst;

/* JADX INFO: loaded from: classes.dex */
public class AndroidAutoVrAdapter {
    private static final String TAG = AndroidAutoVrAdapter.class.getSimpleName();
    private static volatile AndroidAutoVrAdapter mAndroidAutoVrAdapter;
    private IAndroidAutoAppManager mAndroidAutoAppManager;
    private Context mContext;
    private SettingsQnxService mSettingsQnxService;
    private boolean mIsConnected = false;
    private int mLinkStatus = -1;
    private boolean mIsAaVrActive = false;
    private IAndroidAutoSessionStatusListener mAndroidAutoSessionStatusListener = null;
    private final IServiceConnectionListener mServiceConnectionListener = new IServiceConnectionListener() { // from class: com.ts.appservice.settings.impl.vradapter.AndroidAutoVrAdapter.1
        public void onServiceConnected(IPlatformService service) {
            LogUtil.info(AndroidAutoVrAdapter.TAG, "AndroidAuto connect");
            AndroidAutoVrAdapter.this.mIsConnected = true;
            AndroidAutoVrAdapter.this.initAaStatusListener();
            try {
                if (AndroidAutoVrAdapter.this.mAndroidAutoAppManager == null || AndroidAutoVrAdapter.this.mAndroidAutoSessionStatusListener == null) {
                    LogUtil.error(AndroidAutoVrAdapter.TAG, "mAndroidAutoAppManager null");
                } else {
                    AndroidAutoVrAdapter.this.mAndroidAutoAppManager.registerAndroidAutoSessionStatusListener(AndroidAutoVrAdapter.this.mAndroidAutoSessionStatusListener);
                }
                AndroidAutoVrAdapter.this.mLinkStatus = AndroidAutoVrAdapter.this.getAndroidAutoSessionStatus();
                LogUtil.info(AndroidAutoVrAdapter.TAG, "mServiceConnectionListener  onServiceConnected to get linkStatus:" + AndroidAutoVrAdapter.this.mLinkStatus);
                if (AndroidAutoVrAdapter.this.mLinkStatus == 4) {
                    AndroidAutoVrAdapter.this.mSettingsQnxService.setCpaaConnectState(1);
                }
            } catch (Exception ex) {
                LogUtil.error(AndroidAutoVrAdapter.TAG, "registerAndroidAutoSessionStatusListener ex", ex);
            }
        }

        public void onServiceDisconnected(IPlatformService service) {
            LogUtil.info(AndroidAutoVrAdapter.TAG, "AndroidAuto Disconnected");
            AndroidAutoVrAdapter.this.mIsConnected = false;
            AndroidAutoVrAdapter.this.mAndroidAutoSessionStatusListener = null;
        }
    };

    public static AndroidAutoVrAdapter getInstance(Context context) {
        LogUtil.error(TAG, "AndroidAutoVrAdapter getInstance called");
        if (mAndroidAutoVrAdapter == null) {
            synchronized (AndroidAutoVrAdapter.class) {
                if (mAndroidAutoVrAdapter == null) {
                    mAndroidAutoVrAdapter = new AndroidAutoVrAdapter(context);
                }
            }
        }
        return mAndroidAutoVrAdapter;
    }

    public AndroidAutoVrAdapter(Context context) {
        this.mAndroidAutoAppManager = null;
        this.mContext = context;
        this.mSettingsQnxService = SettingsQnxService.getInstance(context);
        IAndroidAutoAppManager iAndroidAutoAppManager = (IAndroidAutoAppManager) ServiceProvider.get(IAndroidAutoAppManager.class, context);
        this.mAndroidAutoAppManager = iAndroidAutoAppManager;
        if (iAndroidAutoAppManager != null) {
            iAndroidAutoAppManager.registerConnectionListener(this.mServiceConnectionListener);
        } else {
            LogUtil.error(TAG, "IAndroidAutoAppManager is null");
        }
        initAaStatusListener();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void initAaStatusListener() {
        if (this.mAndroidAutoSessionStatusListener == null) {
            this.mAndroidAutoSessionStatusListener = new IAndroidAutoSessionStatusListener() { // from class: com.ts.appservice.settings.impl.vradapter.AndroidAutoVrAdapter.2
                public void onSessionStatusChanged(int linkStatus, int errorCode, String message) {
                    LogUtil.info(AndroidAutoVrAdapter.TAG, "onSessionStatusChanged linkStatus:" + linkStatus + " old linkStatus : " + AndroidAutoVrAdapter.this.mLinkStatus);
                    AndroidAutoVrAdapter.this.mLinkStatus = linkStatus;
                    if (AndroidAutoVrAdapter.this.mLinkStatus == 4) {
                        AndroidAutoVrAdapter.this.mSettingsQnxService.setCpaaConnectState(1);
                        return;
                    }
                    int carPlaySessionStatus = CarPlayVrAdapter.getInstance(AndroidAutoVrAdapter.this.mContext).getCarPlaySessionStatus();
                    LogUtil.info(AndroidAutoVrAdapter.TAG, "onSessionStatusChanged to getCarPlaySessionStatus status:" + carPlaySessionStatus);
                    if (carPlaySessionStatus != 2) {
                        AndroidAutoVrAdapter.this.mSettingsQnxService.setCpaaConnectState(0);
                    }
                }

                public void onProjectionAppStatesChanged(int appType, boolean enabled) {
                    LogUtil.info(AndroidAutoVrAdapter.TAG, "onProjectionAppStatesChanged appType:" + appType + "enabled : " + enabled);
                    if (appType == 1) {
                        AndroidAutoVrAdapter.this.mIsAaVrActive = enabled;
                    }
                }
            };
        }
    }

    public int getAndroidAutoSessionStatus() {
        LogUtil.info(TAG, "getAndroidAutoSessionStatus ");
        IAndroidAutoAppManager iAndroidAutoAppManager = this.mAndroidAutoAppManager;
        if (iAndroidAutoAppManager == null || !this.mIsConnected) {
            LogUtil.error(TAG, "mAndroidAutoAppManager is not connect");
            return -1;
        }
        try {
            return iAndroidAutoAppManager.getAndroidAutoSessionStatus();
        } catch (Exception ex) {
            LogUtil.error(TAG, "getAndroidAutoSessionStatus error: ", ex);
            return -1;
        }
    }

    public void sendKeyLongPressEvent(VehicleConst.AapHardkeyEvent keyCode) {
        LogUtil.info(TAG, "sendKeyLongPressEvent keyCode" + keyCode.toString());
        IAndroidAutoAppManager iAndroidAutoAppManager = this.mAndroidAutoAppManager;
        if (iAndroidAutoAppManager == null || !this.mIsConnected) {
            LogUtil.error(TAG, "mAndroidAutoAppManager is not connect");
            return;
        }
        try {
            iAndroidAutoAppManager.sendKeyLongPressEvent(keyCode);
        } catch (Exception ex) {
            LogUtil.error(TAG, "sendKeyLongPressEvent error: ", ex);
        }
    }

    private void updateAndroidAutoAppState() {
        try {
            if (this.mAndroidAutoAppManager != null && this.mIsConnected) {
                boolean appState = this.mAndroidAutoAppManager.getAndroidAutoAppStatus(1);
                LogUtil.info(TAG, "updateAndroidAutoAppState appState:" + appState + ", mIsAaVrActive:" + this.mIsAaVrActive);
                if (appState != this.mIsAaVrActive) {
                    this.mIsAaVrActive = appState;
                }
                return;
            }
            LogUtil.error(TAG, "mAndroidAutoAppManager is not connect");
        } catch (Exception ex) {
            LogUtil.error(TAG, "updateAndroidAutoAppState error: ", ex);
        }
    }

    public void sendKeyEvent(VehicleConst.AapHardkeyEvent keyCode, int keyAction) {
        LogUtil.info(TAG, "sendKeyEvent ：action" + keyAction);
        IAndroidAutoAppManager iAndroidAutoAppManager = this.mAndroidAutoAppManager;
        if (iAndroidAutoAppManager == null || !this.mIsConnected) {
            LogUtil.error(TAG, "mAndroidAutoAppManager is not connect");
            return;
        }
        try {
            iAndroidAutoAppManager.sendKeyEvent(keyCode, keyAction);
        } catch (Exception ex) {
            LogUtil.error(TAG, "sendKeyEvent error: ", ex);
        }
    }

    public boolean isAaVrActive() {
        updateAndroidAutoAppState();
        return this.mIsAaVrActive;
    }
}
