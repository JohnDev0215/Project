package com.android.systemui;

import android.app.ActivityThread;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ApplicationInfo;
import android.content.res.Configuration;
import android.hardware.bydauto.properties.CarSettings;
import android.os.Process;
import android.os.SystemProperties;
import android.os.UserHandle;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.Log;
import android.util.TimingsTraceLog;
import com.android.systemui.statusbar.policy.CarController;
import com.android.systemui.util.SystemUIException;
import com.byd.card.bean.Constant;
import com.byd.card.bean.MessageEvent;
import com.byd.card.floatcard.Executors;
import com.bydux.api.common.tools.log.BydLogHelper;
import com.bydux.api.common.views.application.BydApplication;
import com.bydux.api.common.views.skin.loader.SkinManager;
import com.tencent.mmkv.MMKV;
import java.util.HashMap;
import java.util.Map;
import org.greenrobot.eventbus.EventBus;

/* JADX INFO: loaded from: classes.dex */
public class SystemUIApplication extends BydApplication implements SysUiServiceProvider {
    private static final boolean DEBUG = true;
    public static final String TAG = "SystemUIApplication";
    public static SystemUIApplication mSystemUIApplication;
    private boolean mBootCompleted;
    private boolean mFloatCardServiceStarted;
    private SystemUI[] mServices;
    private boolean mServicesStarted;
    private final Map<Class<?>, Object> mComponents = new HashMap();
    private Runnable mCheckBootCompletedRunnable = new Runnable() { // from class: com.android.systemui.SystemUIApplication.3
        @Override // java.lang.Runnable
        public void run() {
            BydLogHelper.logW(SystemUIApplication.TAG, "mCheckBootCompletedRunnable mBootCompleted : " + SystemUIApplication.this.mBootCompleted);
            if (SystemUIApplication.this.mBootCompleted) {
                return;
            }
            if ("1".equals(SystemProperties.get("sys.boot_completed"))) {
                SystemUIApplication.this.mBootCompleted = true;
                for (SystemUI systemUI : SystemUIApplication.this.mServices) {
                    systemUI.onBootCompleted();
                }
                return;
            }
            Executors.MAIN_EXECUTOR.getHandler().postDelayed(SystemUIApplication.this.mCheckBootCompletedRunnable, 2000L);
        }
    };

    @Override // com.bydux.api.common.views.application.BydApplication
    protected boolean onSkinEnable() {
        return true;
    }

    public static SystemUIApplication getInstance() {
        return mSystemUIApplication;
    }

    @Override // android.content.ContextWrapper
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        BydLogHelper.logD(TAG, "attachBaseContext");
    }

    @Override // com.bydux.api.common.views.application.BydApplication, com.bydux.api.common.views.skin.listener.ILoaderListener
    public void onChangeSkinStart() {
        super.onChangeSkinStart();
    }

    @Override // com.bydux.api.common.views.application.BydApplication, com.bydux.api.common.views.skin.listener.ILoaderListener
    public void onChangeSkinSuccess() {
        if ("1".equals(SystemProperties.get("sys.boot_completed"))) {
            EventBus.getDefault().post(new MessageEvent(Constant.EventBusMessage.ACTION_CHANGE_SKIN_SUCCESS));
        }
    }

    private void setThemeResource() {
        Executors.MODEL_EXECUTOR.execute(new Runnable() { // from class: com.android.systemui.SystemUIApplication.1
            @Override // java.lang.Runnable
            public void run() {
                int i = CarSettings.UserTableData.getInt(SystemUIApplication.this.getContentResolver(), CarSettings.UserTableData.BYD_APP_THEME);
                BydLogHelper.logE("ban", "onChange BYD_APP_THEME uuid / " + i);
                if (i == 100000010 || i == 0) {
                    SystemUIApplication.this.sendBroadcast("", 1);
                    Settings.System.putString(SystemUIApplication.this.getContentResolver(), "bydux_skin_path", "");
                    return;
                }
                if (i == 100000011) {
                    SystemUIApplication.this.sendBroadcast(Constant.SkinPath.SKIN_PATH_1, 2);
                    Settings.System.putString(SystemUIApplication.this.getContentResolver(), "bydux_skin_path", Constant.SkinPath.SKIN_PATH_1);
                    return;
                }
                if (i == 100000012) {
                    SystemUIApplication.this.sendBroadcast(Constant.SkinPath.SKIN_PATH_2, 2);
                    Settings.System.putString(SystemUIApplication.this.getContentResolver(), "bydux_skin_path", Constant.SkinPath.SKIN_PATH_2);
                    return;
                }
                if (i == 100000013) {
                    SystemUIApplication.this.sendBroadcast(Constant.SkinPath.SKIN_PATH_3, 2);
                    Settings.System.putString(SystemUIApplication.this.getContentResolver(), "bydux_skin_path", Constant.SkinPath.SKIN_PATH_3);
                } else if (i == 100000014) {
                    SystemUIApplication.this.sendBroadcast(Constant.SkinPath.SKIN_PATH_4, 2);
                    Settings.System.putString(SystemUIApplication.this.getContentResolver(), "bydux_skin_path", Constant.SkinPath.SKIN_PATH_4);
                } else if (i == 100000015) {
                    SystemUIApplication.this.sendBroadcast(Constant.SkinPath.SKIN_PATH_5, 2);
                    Settings.System.putString(SystemUIApplication.this.getContentResolver(), "bydux_skin_path", Constant.SkinPath.SKIN_PATH_5);
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendBroadcast(String path, int mode) {
        Intent intent = new Intent(SkinManager.ACTION_BYDUI_SKIN_CHANGED);
        intent.putExtra(SkinManager.FIELD_DAY_NIGHT_MODE, mode);
        intent.putExtra("bydux_skin_path", path);
        sendBroadcast(intent);
    }

    @Override // com.bydux.api.common.views.application.BydApplication, android.app.Application
    public void onCreate() {
        Log.w(TAG, "onCreate version : 1.18.2-202507181507-uke-pa-mr");
        super.onCreate();
        mSystemUIApplication = this;
        String strCurrentProcessName = ActivityThread.currentProcessName();
        Log.i(TAG, "onCreate processName:" + strCurrentProcessName);
        setTheme(R.style.Theme_SystemUI);
        SystemUIFactory.createFromConfig(this);
        Dependency.initDependencies(this);
        MMKV.initialize(this);
        if (Process.myUserHandle().equals(UserHandle.SYSTEM)) {
            if (TextUtils.equals("com.android.systemui:screenshot", strCurrentProcessName)) {
                BydLogHelper.logD("SYSTEM-process:screenshot");
                return;
            }
            IntentFilter intentFilter = new IntentFilter("android.intent.action.BOOT_COMPLETED");
            intentFilter.setPriority(1000);
            registerReceiver(new BroadcastReceiver() { // from class: com.android.systemui.SystemUIApplication.2
                @Override // android.content.BroadcastReceiver
                public void onReceive(Context context, Intent intent) {
                    BydLogHelper.logW(SystemUIApplication.TAG, "ACTION_BOOT_COMPLETED");
                    SystemProperties.set("sys.sysui.boot.receive", "1");
                    CarController carController = (CarController) Dependency.get(CarController.class);
                    carController.initCarplay(false);
                    carController.initAndroidAuto(false);
                    Settings.Global.putInt(context.getContentResolver(), "navibar_mode_on_screensaver", 1);
                    BydLogHelper.logW(SystemUIApplication.TAG, "ACTION_BOOT_COMPLETED mBootCompleted:" + SystemUIApplication.this.mBootCompleted);
                    if (SystemUIApplication.this.mBootCompleted) {
                        return;
                    }
                    BydLogHelper.logD(SystemUIApplication.TAG, "BOOT_COMPLETED received");
                    SystemUIApplication.this.unregisterReceiver(this);
                    SystemUIApplication.this.mBootCompleted = true;
                    BydLogHelper.logW(SystemUIApplication.TAG, "ACTION_BOOT_COMPLETED mServicesStarted:" + SystemUIApplication.this.mServicesStarted);
                    if (SystemUIApplication.this.mServicesStarted) {
                        Executors.MAIN_EXECUTOR.getHandler().removeCallbacks(SystemUIApplication.this.mCheckBootCompletedRunnable);
                        int length = SystemUIApplication.this.mServices.length;
                        for (SystemUI systemUI : SystemUIApplication.this.mServices) {
                            systemUI.onBootCompleted();
                        }
                    }
                }
            }, intentFilter);
            return;
        }
        String strCurrentProcessName2 = ActivityThread.currentProcessName();
        ApplicationInfo applicationInfo = getApplicationInfo();
        if (strCurrentProcessName2 != null && strCurrentProcessName2.startsWith(applicationInfo.processName + ":")) {
            BydLogHelper.logD("sub-process:" + strCurrentProcessName2);
        } else {
            startServicesIfNeeded();
        }
    }

    public void startServicesIfNeeded() {
        startServicesIfNeeded(getResources().getStringArray(R.array.config_systemUIServiceComponents));
    }

    private void startServicesIfNeeded(String[] services) {
        BydLogHelper.logW(TAG, "startServicesIfNeeded mServicesStarted : " + this.mServicesStarted);
        if (this.mServicesStarted) {
            return;
        }
        this.mServices = new SystemUI[services.length];
        if (!this.mBootCompleted && "1".equals(SystemProperties.get("sys.boot_completed"))) {
            this.mBootCompleted = true;
            BydLogHelper.logD(TAG, "BOOT_COMPLETED was already sent");
        }
        TimingsTraceLog timingsTraceLog = new TimingsTraceLog("SystemUIBootTiming", 4096L);
        timingsTraceLog.traceBegin("StartServices");
        int length = services.length;
        for (int i = 0; i < length; i++) {
            String str = services[i];
            BydLogHelper.logD(TAG, "loading: " + str);
            timingsTraceLog.traceBegin("StartServices: " + str);
            long jCurrentTimeMillis = System.currentTimeMillis();
            try {
                Class<?> cls = Class.forName(str);
                this.mServices[i] = (SystemUI) cls.newInstance();
                this.mServices[i].mContext = this;
                this.mServices[i].mComponents = this.mComponents;
                BydLogHelper.logD(TAG, "running: " + this.mServices[i]);
                this.mServices[i].start();
                timingsTraceLog.traceEnd();
                long jCurrentTimeMillis2 = System.currentTimeMillis() - jCurrentTimeMillis;
                if (jCurrentTimeMillis2 > 1000) {
                    BydLogHelper.logD(TAG, "Initialization of " + cls.getName() + " took " + jCurrentTimeMillis2 + " ms");
                }
                if (this.mBootCompleted) {
                    this.mServices[i].onBootCompleted();
                }
            } catch (ClassNotFoundException | IllegalAccessException | InstantiationException e) {
                throw new SystemUIException(e);
            }
        }
        BydLogHelper.logW(TAG, "startServicesIfNeeded mBootCompleted : " + this.mBootCompleted);
        if (!this.mBootCompleted) {
            Executors.MAIN_EXECUTOR.getHandler().postDelayed(this.mCheckBootCompletedRunnable, 2000L);
        }
        timingsTraceLog.traceEnd();
        this.mServicesStarted = true;
    }

    @Override // com.bydux.api.common.views.application.BydApplication, android.app.Application, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (this.mServicesStarted) {
            SystemUI[] systemUIArr = this.mServices;
            int length = systemUIArr.length;
            for (SystemUI systemUI : systemUIArr) {
                if (systemUI != null) {
                    systemUI.onConfigurationChanged(newConfig);
                }
            }
        }
    }

    @Override // com.bydux.api.common.views.application.BydApplication, android.app.Application
    public void onTerminate() {
        super.onTerminate();
        BydLogHelper.logE(TAG, "onTerminate");
    }

    @Override // com.android.systemui.SysUiServiceProvider
    public <T> T getComponent(Class<T> cls) {
        return (T) this.mComponents.get(cls);
    }

    public SystemUI[] getServices() {
        return this.mServices;
    }
}
