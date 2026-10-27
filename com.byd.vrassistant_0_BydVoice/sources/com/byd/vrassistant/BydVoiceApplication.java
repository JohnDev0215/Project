package com.byd.vrassistant;

import android.app.ActivityManager;
import android.app.Application;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Process;
import android.util.Log;
import androidx.lifecycle.ViewModelStore;
import androidx.lifecycle.ViewModelStoreOwner;
import cerence.ark.assistant.api.ArkAssistant;
import com.byd.common.countrycode.CountryCodeUtil;
import com.byd.vrassistant.bridge.BridgeHelper;
import com.byd.vrassistant.bridge.NaviStatusImpl;
import com.byd.vrassistant.bridge.SystemSettingImpl;
import com.byd.vrassistant.bridge.VrAreaStatusImpl;
import com.byd.vrassistant.bridge.VrOperationImpl;
import com.byd.vrassistant.bridge.VrStatusImpl;
import com.byd.vrassistant.bridge.VrUiStatusImpl;
import com.byd.vrassistant.utils.CrashHandler;
import com.byd.vrassistant.utils.RLog;
import com.byd.vrassistant.utils.VoiceServiceUtils;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.logging.Level;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Logger;

/* JADX INFO: loaded from: classes.dex */
public class BydVoiceApplication extends Application implements ViewModelStoreOwner {
    public static int LOCAL_VR_STATE = -1;
    private static final String TAG = "NEU_BydVoice";
    private static BydVoiceApplication sApplication;
    private ViewModelStore mAppViewModelStore;

    @Override // android.app.Application
    public void onCreate() {
        super.onCreate();
        RLog.i(TAG, "BydVoiceApplication onCreate");
        ContextHolder.application = this;
        BridgeHelper.setVrOperation(new VrOperationImpl());
        BridgeHelper.setVrStatus(new VrStatusImpl());
        BridgeHelper.setVrAreaStatus(new VrAreaStatusImpl());
        BridgeHelper.setVrUiStatus(new VrUiStatusImpl());
        BridgeHelper.setNaviStatus(new NaviStatusImpl());
        BridgeHelper.setSystemSetting(new SystemSettingImpl());
        sApplication = this;
        CountryCodeUtil.getInstance().initCountryData(this);
        this.mAppViewModelStore = new ViewModelStore();
        String processName = getProcessName(this, Process.myPid());
        if (processName != null && processName.equalsIgnoreCase(getPackageName())) {
            hookWebView();
            getPackageName();
            new Intent();
            startForegroundService(new Intent(this, (Class<?>) NeuVoiceService.class));
        }
        CrashHandler.getInstance().init(getApplicationContext());
        EventBus.builder().throwSubscriberException(false).logger(new Logger() { // from class: com.byd.vrassistant.BydVoiceApplication.1
            @Override // org.greenrobot.eventbus.Logger
            public void log(Level level, String str) {
            }

            @Override // org.greenrobot.eventbus.Logger
            public void log(Level level, String str, Throwable th) {
                if (level == Level.SEVERE) {
                    Log.e(BydVoiceApplication.TAG, "EventBus: msg=" + str + ",Throwable=" + th);
                }
            }
        }).installDefaultEventBus();
    }

    public static boolean isApplicationForeground(List<String> list) {
        ComponentName componentName;
        List<ActivityManager.RunningTaskInfo> runningTasks = ((ActivityManager) getContext().getSystemService("activity")).getRunningTasks(1);
        AtomicBoolean atomicBoolean = new AtomicBoolean(false);
        if (!runningTasks.isEmpty() && (componentName = runningTasks.get(0).topActivity) != null) {
            RLog.e(TAG, "topActivity.getPackageName()=" + componentName.getPackageName());
            atomicBoolean.set(list.contains(componentName.getPackageName()));
        }
        return atomicBoolean.get();
    }

    public static boolean isActivityTopAndRun(String str) {
        ComponentName componentName;
        List<ActivityManager.RunningTaskInfo> runningTasks = ((ActivityManager) getContext().getSystemService("activity")).getRunningTasks(50);
        AtomicBoolean atomicBoolean = new AtomicBoolean(false);
        if (!runningTasks.isEmpty() && (componentName = runningTasks.get(0).topActivity) != null) {
            RLog.e(TAG, "baseActivity.getclassname()=" + componentName.getClassName());
            if (componentName.getClassName().contains(str)) {
                atomicBoolean.set(true);
            }
        }
        return atomicBoolean.get();
    }

    public static boolean isActivityAlreadyRun(String str) {
        List<ActivityManager.RunningTaskInfo> runningTasks = ((ActivityManager) getContext().getSystemService("activity")).getRunningTasks(50);
        int i = 0;
        AtomicBoolean atomicBoolean = new AtomicBoolean(false);
        if (!runningTasks.isEmpty()) {
            while (true) {
                if (i >= runningTasks.size()) {
                    break;
                }
                ComponentName componentName = runningTasks.get(i).topActivity;
                if (componentName != null) {
                    RLog.e(TAG, "baseActivity.getclassname()=" + componentName.getClassName());
                    if (componentName.getClassName().contains(str)) {
                        atomicBoolean.set(true);
                        break;
                    }
                }
                i++;
            }
        }
        return atomicBoolean.get();
    }

    public static void hookWebView() {
        try {
            Class<?> cls = Class.forName("android.webkit.WebViewFactory");
            Field declaredField = cls.getDeclaredField("sProviderInstance");
            declaredField.setAccessible(true);
            Object objInvoke = declaredField.get(null);
            if (objInvoke != null) {
                RLog.i(TAG, "sProviderInstance isn't null");
                return;
            }
            Method declaredMethod = cls.getDeclaredMethod("getProviderClass", new Class[0]);
            declaredMethod.setAccessible(true);
            Class cls2 = (Class) declaredMethod.invoke(cls, new Object[0]);
            Class<?> cls3 = Class.forName("android.webkit.WebViewDelegate");
            Constructor<?> declaredConstructor = cls3.getDeclaredConstructor(new Class[0]);
            declaredConstructor.setAccessible(true);
            Field declaredField2 = cls.getDeclaredField("CHROMIUM_WEBVIEW_FACTORY_METHOD");
            declaredField2.setAccessible(true);
            String str = (String) declaredField2.get(null);
            if (str == null) {
                str = "create";
            }
            Method method = cls2.getMethod(str, cls3);
            if (method != null) {
                objInvoke = method.invoke(null, declaredConstructor.newInstance(new Object[0]));
            }
            if (objInvoke != null) {
                declaredField.set("sProviderInstance", objInvoke);
                RLog.i(TAG, "Hook success!");
            } else {
                RLog.i(TAG, "Hook failed!");
            }
        } catch (Throwable th) {
            RLog.i(TAG, th);
        }
    }

    public static BydVoiceApplication getContext() {
        return sApplication;
    }

    @Override // android.app.Application, android.content.ComponentCallbacks2
    public void onTrimMemory(int i) {
        RLog.i(TAG, "BydApplication onTrimMemory");
        super.onTrimMemory(i);
        ArkAssistant.get().requestTrimMemory();
    }

    private String getProcessName(Context context, int i) {
        List<ActivityManager.RunningAppProcessInfo> runningAppProcesses = ((ActivityManager) context.getSystemService("activity")).getRunningAppProcesses();
        if (runningAppProcesses == null) {
            return null;
        }
        for (ActivityManager.RunningAppProcessInfo runningAppProcessInfo : runningAppProcesses) {
            if (runningAppProcessInfo.pid == i) {
                return runningAppProcessInfo.processName;
            }
        }
        return null;
    }

    @Override // android.app.Application, android.content.ComponentCallbacks
    public void onLowMemory() {
        super.onLowMemory();
        VoiceServiceUtils.onLowMemory();
    }

    @Override // androidx.lifecycle.ViewModelStoreOwner
    public ViewModelStore getViewModelStore() {
        return this.mAppViewModelStore;
    }
}
