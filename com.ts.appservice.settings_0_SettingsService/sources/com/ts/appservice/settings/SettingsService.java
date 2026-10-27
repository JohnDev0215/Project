package com.ts.appservice.settings;

import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.SystemProperties;
import com.ts.appservice.settings.impl.vradapter.VrAdapter;
import com.ts.lib.settings.common.utilities.LogUtil;
import com.ts.lib.settings.vradapter.ILocalVrSettingsService;

/* JADX INFO: loaded from: classes.dex */
public class SettingsService extends Service {
    private static final String BUILD_TYPE_PROP = "ro.build.type";
    public static final String BYD_VR_CLASS = "com.byd.vrassistant.service.LocalVRSettingService";
    public static final String BYD_VR_PKG = "com.byd.vrassistant";
    public static final String BYD_XF_VR_CLASS = "com.iflytek.auto.speechclient.sdk.vradapter.VrSettingsService";
    public static final String BYD_XF_VR_PKG = "com.byd.vrassistant.xf";
    private static final int MSG_BIND_SERVICE = 100;
    private static final int MSG_RETRY_BIND_SERVICE = 101;
    private static final int RETRY_MAX = 10;
    private static final String TAG = "SettingsService";
    private static final int TIME_BIND_SERVICE = 8000;
    private static final int TIME_RETRY_BIND_SERVICE = 2000;
    private String mBuildType;
    private Context mContext;
    private ILocalVrSettingsService mLocalVrSettingsService;
    private PackageManager mPackageManager;
    private RetryHandler mRetryHandler;
    private SettingsAdapterServiceImpl mSettingsAdapterServiceImpl;
    private VrAdapter mVrAdapter;
    private boolean mConnectionState = false;
    private int mConnectCount = 0;
    private final ServiceConnection connection = new ServiceConnection() { // from class: com.ts.appservice.settings.SettingsService.1
        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName name, IBinder service) {
            LogUtil.info(SettingsService.TAG, "LocalVrSettingsService onServiceConnected");
            SettingsService.this.mLocalVrSettingsService = ILocalVrSettingsService.Stub.asInterface(service);
            SettingsService.this.mVrAdapter.initLocalVr(SettingsService.this.mLocalVrSettingsService);
            SettingsService.this.mConnectionState = true;
        }

        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName name) {
            LogUtil.info(SettingsService.TAG, "LocalVrSettingsService onServiceDisconnected");
            SettingsService.this.mLocalVrSettingsService = null;
            SettingsService.this.mVrAdapter.destroyLocalVr();
            SettingsService.this.mConnectionState = false;
            SettingsService.this.reBindService();
        }
    };

    private final class RetryHandler extends Handler {
        private RetryHandler(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            int i = msg.what;
            if (i == 100) {
                SettingsService.this.bindServiceInitVr();
            } else if (i == 101) {
                SettingsService.this.reBindService();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void bindServiceInitVr() {
        LogUtil.error(TAG, "bindServiceInitVr");
        bindService();
        if (this.mVrAdapter == null) {
            this.mVrAdapter = new VrAdapter(this.mContext);
        }
        this.mVrAdapter.init();
    }

    private void bindService() {
        if (!this.mConnectionState) {
            Intent intent = new Intent();
            this.mConnectCount++;
            boolean neuVrInstallStatus = isAppInstalled(BYD_VR_PKG);
            boolean xfVrInstallStatus = isAppInstalled(BYD_XF_VR_PKG);
            if (neuVrInstallStatus) {
                LogUtil.warning(TAG, "bindService to bind neu vr");
                intent.setComponent(new ComponentName(BYD_VR_PKG, BYD_VR_CLASS));
                bindService(intent, this.connection, 1);
            } else if (xfVrInstallStatus) {
                LogUtil.warning(TAG, "bindService to bind xf vr");
                intent.setComponent(new ComponentName(BYD_XF_VR_PKG, BYD_XF_VR_CLASS));
                bindService(intent, this.connection, 1);
            } else {
                LogUtil.error(TAG, "bindService to bind neu vr because of not vr install");
                intent.setComponent(new ComponentName(BYD_VR_PKG, BYD_VR_CLASS));
                bindService(intent, this.connection, 1);
            }
            if (this.mRetryHandler.hasMessages(101)) {
                this.mRetryHandler.removeMessages(101);
            }
            this.mRetryHandler.sendEmptyMessageDelayed(101, 2000L);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void reBindService() {
        LogUtil.error(TAG, "reBindService: " + this.mConnectionState + "ConnectCount:" + this.mConnectCount);
        if (!this.mConnectionState && this.mConnectCount != 10) {
            bindService();
        }
    }

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        LogUtil.error(TAG, "settingsservice start");
        this.mContext = getApplicationContext();
        if (this.mSettingsAdapterServiceImpl == null) {
            this.mSettingsAdapterServiceImpl = new SettingsAdapterServiceImpl(this.mContext);
        }
        this.mSettingsAdapterServiceImpl.init();
        this.mBuildType = SystemProperties.get(BUILD_TYPE_PROP);
        LogUtil.error(TAG, "ro.build.type : " + this.mBuildType);
        this.mPackageManager = this.mContext.getPackageManager();
        if (this.mRetryHandler == null) {
            this.mRetryHandler = new RetryHandler(this.mContext.getMainLooper());
        }
        this.mRetryHandler.sendEmptyMessageDelayed(100, 8000L);
        LogUtil.error(TAG, "settingsservice end");
    }

    private boolean isAppInstalled(String packageName) {
        try {
            PackageInfo packageInfo = this.mPackageManager.getPackageInfo(packageName, 1);
            LogUtil.warning(TAG, "isAppInstalled packageName " + packageName + " return true " + packageInfo.packageName);
            return true;
        } catch (PackageManager.NameNotFoundException e) {
            LogUtil.warning(TAG, "isAppInstalled packageName " + packageName + " not install return false");
            return false;
        }
    }

    @Override // android.app.Service, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration newConfig) {
        LogUtil.debug(TAG, "onConfigurationChanged newConfig: " + newConfig.locale);
        super.onConfigurationChanged(newConfig);
        this.mSettingsAdapterServiceImpl.onConfigurationChanged(newConfig);
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    @Override // android.app.Service
    public void onDestroy() {
        super.onDestroy();
        this.mSettingsAdapterServiceImpl.destory();
        this.mVrAdapter.destroy();
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        LogUtil.info(TAG, "onBind");
        return this.mSettingsAdapterServiceImpl;
    }
}
