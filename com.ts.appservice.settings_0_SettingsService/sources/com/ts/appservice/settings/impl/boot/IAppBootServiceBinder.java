package com.ts.appservice.settings.impl.boot;

import android.app.ActivityManager;
import android.app.ActivityTaskManager;
import android.car.Car;
import android.car.media.CarAudioManager;
import android.car.media.CarEnhancedAudioManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.media.AudioFocusInfo;
import android.os.Build;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import com.ts.appservice.settings.impl.caradapter.CarAdapterServiceManager;
import com.ts.lib.caradapter.CarAdapterManager;
import com.ts.lib.caradapter.body.CarBodyManager;
import com.ts.lib.caradapter.body.CarBodyPacket;
import com.ts.lib.caradapter.body.OnBodyChangedListener;
import com.ts.lib.settings.boot.BootApp;
import com.ts.lib.settings.boot.IAppBootSettingsService;
import com.ts.lib.settings.common.utilities.LogUtil;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.ToIntFunction;

/* JADX INFO: loaded from: classes.dex */
public class IAppBootServiceBinder extends IAppBootSettingsService.Stub {
    public static final String ACTIVITY_CLASS_NAME = "android.app.ActivityManager";
    private static final String BYD_MUSIC_PACKAGE_NAME = "com.byd.localmusic";
    public static final String FORCE_STOP_METHOD_NAME = "forceStopPackage";
    private static final int MAX_CREATE_CAR_MS = 200000;
    private static final int MAX_RECENT_TASKS = 30;
    private static final int MESSAGE_KILL_BACKGROUD = 1;
    private static final int MESSAGE_KILL_BACKGROUD_DELAY_TIME = 600;
    private static final int MESSAGE_SERVICE_AUTO_RESTART = 5;
    private static final int MESSAGE_SERVICE_CAR_ADAPTER = 6;
    private static final int MESSAGE_SERVICE_CREATE_CAR = 4;
    private static final int MESSAGE_SERVICE_DISCONNECTED = 2;
    public static final int POWER_LEVEL_ACC = 2;
    public static final int POWER_LEVEL_INVALID = 0;
    public static final int POWER_LEVEL_OFF = 1;
    public static final int POWER_LEVEL_ON = 3;
    private static final int RANDOM_RETRY_INTERVAL = 500;
    private static final int RETRY_INTERVAL = 500;
    private static final String TAG = IAppBootServiceBinder.class.getSimpleName();
    private String mActiveAudioPackageName;
    private ActivityManager mActivityManager;
    private Car mCar;
    private boolean mCarAdapterBinderState;
    private CarAdapterManager mCarAdapterManager;
    private CarAdapterServiceManager mCarAdapterServiceManager;
    private CarAudioManager mCarAudioManager;
    private CarBodyManager mCarBodyManager;
    private CarEnhancedAudioManager mCarEnhancedAudioManager;
    private boolean mClearMusicRecentTaskStatus;
    private Context mContext;
    private Handler mHandler;
    private HandlerThread mHandlerThread;
    private boolean mIsCarBinding;
    private boolean mManualDisconnected;
    private final List<AppInfo> mAppBootList = new ArrayList();
    private int mPowerLevel = 0;
    private CarEnhancedAudioManager.CarFocusCallback mCarFocusCallback = new CarEnhancedAudioManager.CarFocusCallback() { // from class: com.ts.appservice.settings.impl.boot.IAppBootServiceBinder.2
        public void onFocusChanged(int audioZoneId, List<AudioFocusInfo> focusHolders) {
            super.onFocusChanged(audioZoneId, focusHolders);
            LogUtil.debug(IAppBootServiceBinder.TAG, "focus change focus holder list : " + focusHolders.toString());
            for (AudioFocusInfo audioFocusInfo : focusHolders) {
                LogUtil.debug(IAppBootServiceBinder.TAG, "focus change focus holder usage package name: " + audioFocusInfo.getPackageName());
                IAppBootServiceBinder.this.mActiveAudioPackageName = audioFocusInfo.getPackageName();
            }
        }
    };
    private Car.CarServiceLifecycleListener mCarServiceLifecycleListener = new Car.CarServiceLifecycleListener() { // from class: com.ts.appservice.settings.impl.boot.-$$Lambda$IAppBootServiceBinder$2ECE0zRLtUXtYq84_bif6IRYsTM
        @Override // android.car.Car.CarServiceLifecycleListener
        public final void onLifecycleChanged(Car car, boolean z) {
            this.f$0.lambda$new$0$IAppBootServiceBinder(car, z);
        }
    };
    private OnBodyChangedListener mCarBodyChangedListener = new OnBodyChangedListener() { // from class: com.ts.appservice.settings.impl.boot.IAppBootServiceBinder.3
        public void onBodyChanged(int id, int areaId, CarBodyPacket packet) {
            if (id == 557862927) {
                IAppBootServiceBinder.this.mPowerLevel = packet.getmPowerLevel();
                LogUtil.info(IAppBootServiceBinder.TAG, "PowerLevel is " + IAppBootServiceBinder.this.mPowerLevel);
                if (IAppBootServiceBinder.this.mPowerLevel == 1) {
                    if (IAppBootServiceBinder.this.mHandler.hasMessages(1)) {
                        IAppBootServiceBinder.this.mHandler.removeMessages(1);
                    }
                    IAppBootServiceBinder.this.mHandler.sendEmptyMessageDelayed(1, 600L);
                } else if (IAppBootServiceBinder.this.mHandler.hasMessages(1)) {
                    IAppBootServiceBinder.this.mHandler.removeMessages(1);
                }
            }
        }

        public void onBinderStateChanged(boolean binderState) {
        }
    };

    public IAppBootServiceBinder(Context context) {
        this.mContext = context;
        HandlerThread handlerThread = new HandlerThread("AppBoot");
        this.mHandlerThread = handlerThread;
        handlerThread.start();
        Handler handler = new Handler(this.mHandlerThread.getLooper()) { // from class: com.ts.appservice.settings.impl.boot.IAppBootServiceBinder.1
            @Override // android.os.Handler
            public void handleMessage(Message msg) {
                IAppBootServiceBinder.this.dequeueMessage(msg);
            }
        };
        this.mHandler = handler;
        handler.sendEmptyMessage(5);
        this.mHandler.sendEmptyMessage(6);
        this.mActivityManager = (ActivityManager) this.mContext.getSystemService("activity");
    }

    public /* synthetic */ void lambda$new$0$IAppBootServiceBinder(Car car, boolean ready) {
        LogUtil.info(TAG, "CarServiceLifecycleListener ready is = " + ready);
        if (ready) {
            synchronized (this) {
                this.mCar = car;
                this.mIsCarBinding = false;
            }
            this.mCarAudioManager = (CarAudioManager) car.getCarManager("audio");
            this.mCarEnhancedAudioManager = (CarEnhancedAudioManager) this.mCar.getCarManager("enhanced_audio");
            List<Integer> zoneIdList = this.mCarAudioManager.getAudioZoneIds();
            int[] zoneIdArray = new int[0];
            if (Build.VERSION.SDK_INT >= 24) {
                zoneIdArray = zoneIdList.stream().mapToInt(new ToIntFunction() { // from class: com.ts.appservice.settings.impl.boot.-$$Lambda$UV1wDVoVlbcxpr8zevj_aMFtUGw
                    @Override // java.util.function.ToIntFunction
                    public final int applyAsInt(Object obj) {
                        return ((Integer) obj).intValue();
                    }
                }).toArray();
            }
            this.mCarEnhancedAudioManager.registerCarFocusCallback(this.mCarFocusCallback, zoneIdArray);
            LogUtil.info(TAG, "CarServiceLifecycleListener registerCarFocusCallback end ");
            return;
        }
        synchronized (this) {
            this.mCar = null;
            this.mIsCarBinding = false;
        }
        this.mCarEnhancedAudioManager = null;
        this.mHandler.sendEmptyMessage(2);
    }

    private void killBackgroundRecentTask() {
        LogUtil.info(TAG, "killBackgroundRecentTask start");
        if (this.mActivityManager == null) {
            this.mActivityManager = (ActivityManager) this.mContext.getSystemService("activity");
        }
        List<ActivityManager.RecentTaskInfo> recentTask = this.mActivityManager.getRecentTasks(30, 2);
        if (recentTask != null && recentTask.size() > 0) {
            for (ActivityManager.RecentTaskInfo info : recentTask) {
                if (info != null && !checkIsWhiteList(info.baseIntent.getComponent().getPackageName())) {
                    LogUtil.info(TAG, "killBackgroundRecentTask : " + info.baseIntent.getComponent().getPackageName());
                    if (info.baseIntent.getComponent().getPackageName().equals(BYD_MUSIC_PACKAGE_NAME)) {
                        this.mClearMusicRecentTaskStatus = true;
                    }
                    try {
                        if (Build.VERSION.SDK_INT >= 12) {
                            ActivityTaskManager.getService().removeTask(info.persistentId);
                        }
                    } catch (Exception ex) {
                        LogUtil.error(TAG, "removeTask err ", ex);
                    }
                }
            }
        }
        LogUtil.info(TAG, "killBackgroundRecentTask end");
    }

    private void killActiveMusicPackage() {
        String str;
        if (!this.mClearMusicRecentTaskStatus && (str = this.mActiveAudioPackageName) != null && str.equals(BYD_MUSIC_PACKAGE_NAME)) {
            LogUtil.info(TAG, "killActiveMusicPackage start to kill local music");
            setPackageForceStop(BYD_MUSIC_PACKAGE_NAME);
        }
        this.mClearMusicRecentTaskStatus = false;
    }

    private boolean checkIsWhiteList(String packageName) {
        ArrayList<String> whiteLists;
        if (packageName != null && (whiteLists = AccAviraConfigManager.getInstance().getWhiteLists()) != null) {
            for (String pkg : whiteLists) {
                if (packageName.contains(pkg)) {
                    return true;
                }
            }
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void dequeueMessage(Message msg) {
        int i = msg.what;
        if (i == 1) {
            if (Build.VERSION.SDK_INT >= 11) {
                killBackgroundRecentTask();
            }
            killActiveMusicPackage();
            return;
        }
        if (i == 2) {
            if (!this.mManualDisconnected) {
                int randTime = (int) (Math.random() * 500.0d);
                this.mHandler.sendEmptyMessageDelayed(5, randTime);
                return;
            }
            return;
        }
        if (i == 4) {
            synchronized (this) {
                if (this.mIsCarBinding) {
                    Car car = Car.createCar(this.mContext, this.mHandler, 200000L, this.mCarServiceLifecycleListener);
                    if (car != null) {
                        synchronized (this) {
                            this.mCar = car;
                            this.mIsCarBinding = false;
                        }
                        return;
                    } else {
                        int randTime2 = (int) (Math.random() * 500.0d);
                        this.mHandler.sendEmptyMessageDelayed(5, randTime2);
                        return;
                    }
                }
                return;
            }
        }
        if (i == 5) {
            if (this.mIsCarBinding || this.mManualDisconnected) {
                return;
            }
            bindService();
            return;
        }
        if (i == 6) {
            if (this.mHandler.hasMessages(6)) {
                this.mHandler.removeMessages(6);
            }
            CarAdapterServiceManager carAdapterServiceManager = CarAdapterServiceManager.getInstance(this.mContext);
            this.mCarAdapterServiceManager = carAdapterServiceManager;
            this.mCarAdapterBinderState = carAdapterServiceManager.getCarAdapterBinderState();
            LogUtil.debug(TAG, "mCarAdapterBinderState is " + this.mCarAdapterBinderState);
            if (this.mCarAdapterBinderState) {
                CarAdapterManager carAdapterManager = CarAdapterManager.getInstance(this.mContext);
                this.mCarAdapterManager = carAdapterManager;
                CarBodyManager carBodyManager = (CarBodyManager) carAdapterManager.getCarAdapterManager("body");
                this.mCarBodyManager = carBodyManager;
                carBodyManager.subscribeCarBodyCallback(this.mCarBodyChangedListener);
                return;
            }
            this.mHandler.sendEmptyMessageDelayed(6, 500L);
            return;
        }
        LogUtil.debug(TAG, "unrecognized handler what:" + msg.what);
    }

    public boolean bindService() {
        synchronized (this) {
            if (this.mCar != null && this.mCar.isConnected()) {
                return true;
            }
            if (this.mIsCarBinding) {
                return false;
            }
            this.mIsCarBinding = true;
            this.mManualDisconnected = false;
            this.mHandler.sendEmptyMessage(4);
            return true;
        }
    }

    public boolean unbindService() {
        synchronized (this) {
            if (this.mCar != null && this.mCar.isConnected()) {
                if (this.mIsCarBinding) {
                    return false;
                }
                this.mManualDisconnected = true;
                return true;
            }
            return true;
        }
    }

    public List<BootApp> getStartupAppList() {
        LogUtil.info(TAG, "getStartupAppList");
        getAllBootStartUserApp();
        List<BootApp> list = new ArrayList<>();
        if (this.mAppBootList.size() > 0) {
            for (AppInfo app : this.mAppBootList) {
                list.add(new BootApp(app.getPackageName(), app.mReceiverEnable));
            }
        } else {
            LogUtil.error(TAG, "getStartupAppList List Size is 0");
        }
        return list;
    }

    public boolean getStartupAppEnable(String pkg) {
        LogUtil.info(TAG, "getStartupAppEnable pkg " + pkg);
        if (this.mAppBootList.size() <= 0) {
            return false;
        }
        for (AppInfo app : this.mAppBootList) {
            if (app.getPackageName().equals(pkg)) {
                return app.isReceiverEnable();
            }
        }
        return false;
    }

    public int setStartupAppEnable(String pkg, boolean enable) {
        LogUtil.info(TAG, "setStartupAppEnable pkg " + pkg + " enable : " + enable);
        if (this.mAppBootList.size() <= 0) {
            return -1;
        }
        for (AppInfo app : this.mAppBootList) {
            if (app.getPackageName().equals(pkg)) {
                if (enable) {
                    enableAutoStart(app);
                } else {
                    disableAutoStart(app);
                }
            }
        }
        return 1;
    }

    private void disableAutoStart(AppInfo appInfo) {
        LogUtil.info(TAG, "disableAutoStart appInfo" + appInfo.toString());
        if (appInfo.isReceiverEnable()) {
            PackageManager packageManager = this.mContext.getPackageManager();
            List<BootReceiverComponentInfo> componentNamesList = appInfo.getBootComponentList();
            for (int i = 0; i < componentNamesList.size(); i++) {
                BootReceiverComponentInfo componentInfo = componentNamesList.get(i);
                packageManager.setComponentEnabledSetting(componentInfo.getComponentName(), 2, 1);
            }
            setPackageForceStop(appInfo.getPackageName());
            appInfo.setReceiverEnable(false);
        }
    }

    private void enableAutoStart(AppInfo appInfo) {
        LogUtil.info(TAG, "enableAutoStart appInfo" + appInfo.toString());
        if (!appInfo.isReceiverEnable()) {
            PackageManager packageManager = this.mContext.getPackageManager();
            List<BootReceiverComponentInfo> componentNamesList = appInfo.getBootComponentList();
            for (int i = 0; i < componentNamesList.size(); i++) {
                BootReceiverComponentInfo componentInfo = componentNamesList.get(i);
                if (!componentInfo.isComponentEnable()) {
                    packageManager.setComponentEnabledSetting(componentInfo.getComponentName(), 1, 1);
                }
            }
            appInfo.setReceiverEnable(true);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    private void getAllBootStartUserApp() {
        LogUtil.info(TAG, "getAllBootStartUserApp");
        PackageManager pm = this.mContext.getPackageManager();
        List<ResolveInfo> receiversList = pm.queryBroadcastReceivers(new Intent("android.intent.action.BOOT_COMPLETED"), 512);
        Map<String, AppInfo> appMap = null;
        int i = 1;
        if (receiversList != null && receiversList.size() > 0) {
            appMap = new HashMap<>();
            for (ResolveInfo resolveinfo : receiversList) {
                ApplicationInfo appInfo = resolveinfo.activityInfo.applicationInfo;
                String packageName = appInfo.packageName;
                LogUtil.error(TAG, "getAllBootStartUserApp packageName : " + packageName);
                if ((appInfo.flags & i) != 0) {
                    LogUtil.error(TAG, "getAllBootStartUserApp ignored packageName : " + packageName);
                } else {
                    ArrayList arrayList = new ArrayList();
                    BootReceiverComponentInfo bootReceiverComponentInfo = new BootReceiverComponentInfo();
                    AppInfo app = new AppInfo();
                    ComponentName componentName = new ComponentName(packageName, resolveinfo.activityInfo.name);
                    boolean z = pm.getComponentEnabledSetting(componentName) != 2 ? i : 0;
                    bootReceiverComponentInfo.setComponentName(componentName);
                    bootReceiverComponentInfo.setComponentEnable(z);
                    LogUtil.info(TAG, "getAllBootStartUserApp packageName : " + packageName + "componentEnable  " + z);
                    if (appMap.containsKey(packageName)) {
                        appMap.get(packageName).getBootComponentList().add(bootReceiverComponentInfo);
                    } else {
                        app.setPackageName(packageName);
                        arrayList.add(bootReceiverComponentInfo);
                        app.setBootComponentList(arrayList);
                        appMap.put(packageName, app);
                    }
                    i = 1;
                }
            }
        }
        if (appMap != null && appMap.size() > 0) {
            for (Map.Entry<String, AppInfo> stringAppInfoEntry : appMap.entrySet()) {
                AppInfo app2 = stringAppInfoEntry.getValue();
                if (app2 != null && app2.getBootComponentList() != null && app2.getBootComponentList().size() > 0) {
                    boolean receiverEnable = true;
                    for (int i2 = 0; i2 < app2.getBootComponentList().size(); i2++) {
                        receiverEnable = receiverEnable && app2.getBootComponentList().get(i2).isComponentEnable();
                    }
                    app2.setReceiverEnable(receiverEnable);
                    LogUtil.info(TAG, "getAllBootStartUserApp app : " + app2.toString());
                    this.mAppBootList.add(app2);
                }
            }
        }
    }

    private void setPackageForceStop(String pkg) {
        LogUtil.info(TAG, "ForceStop app------>" + pkg);
        try {
            Method method = Class.forName(ACTIVITY_CLASS_NAME).getMethod(FORCE_STOP_METHOD_NAME, String.class);
            if (this.mActivityManager == null) {
                this.mActivityManager = (ActivityManager) this.mContext.getSystemService("activity");
            }
            method.invoke(this.mActivityManager, pkg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public class AppInfo {
        private List<BootReceiverComponentInfo> mBootComponentList;
        private String mPackageName;
        private boolean mReceiverEnable;

        public AppInfo() {
        }

        public String getPackageName() {
            return this.mPackageName;
        }

        public void setPackageName(String packageName) {
            this.mPackageName = packageName;
        }

        public boolean isReceiverEnable() {
            return this.mReceiverEnable;
        }

        public void setReceiverEnable(boolean receiverEnable) {
            this.mReceiverEnable = receiverEnable;
        }

        public List<BootReceiverComponentInfo> getBootComponentList() {
            return this.mBootComponentList;
        }

        public void setBootComponentList(List<BootReceiverComponentInfo> bootComponentList) {
            this.mBootComponentList = bootComponentList;
        }

        public String toString() {
            return "mPackageName:" + this.mPackageName + " ,mReceiverEnable:" + this.mReceiverEnable;
        }
    }

    public static class BootReceiverComponentInfo {
        private boolean ComponentEnable;
        private ComponentName componentName;

        public ComponentName getComponentName() {
            return this.componentName;
        }

        public void setComponentName(ComponentName componentName) {
            this.componentName = componentName;
        }

        public boolean isComponentEnable() {
            return this.ComponentEnable;
        }

        public void setComponentEnable(boolean componentEnable) {
            this.ComponentEnable = componentEnable;
        }
    }
}
