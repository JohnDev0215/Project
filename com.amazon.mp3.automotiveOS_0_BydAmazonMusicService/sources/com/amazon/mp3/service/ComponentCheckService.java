package com.amazon.mp3.service;

import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.activity.IntentProxyActivity;
import com.amazon.mp3.activity.settings.SettingsUtil;
import com.amazon.mp3.amp.widget.PlayerWidgetProvider;
import com.amazon.mp3.client.activity.LauncherActivity;
import com.amazon.mp3.download.service.AutoDownloadPurchaseReceiver;
import com.amazon.mp3.service.metrics.InstallSource;
import com.amazon.mp3.store.receiver.FirstBootReceiver;
import com.amazon.mp3.util.FileUtil;
import com.amazon.mp3.util.Log;
import com.amazon.mp3.util.StorageInfo;
import com.amazon.music.config.ConfigManager;

/* JADX INFO: loaded from: classes4.dex */
public class ComponentCheckService extends Service {
    private static final String TAG = ComponentCheckService.class.getSimpleName();
    private int mStartId;

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        return null;
    }

    public static void startOnApplicationStarted(Context context) {
        start(context, "com.amazon.mp3.service.ACTION_RUN_STARTUP_CHECK");
    }

    public static void start(Context context, String callingAction) {
        if (AmazonApplication.isApplicationBackground()) {
            Log.debug(TAG, "Not starting ComponentCheckService in the background");
            return;
        }
        Intent intent = new Intent("com.amazon.mp3.service.ACTION_CHECK_COMPONENTS");
        intent.putExtra("com.amazon.mp3.EXTRA_CALLING_ACTION", callingAction);
        intent.setClass(context, ComponentCheckService.class);
        ServiceUtil.startService(context, intent);
    }

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int flags, int startId) {
        super.onStartCommand(intent, flags, startId);
        return 2;
    }

    @Override // android.app.Service
    public void onStart(Intent intent, int startId) {
        super.onStart(intent, startId);
        StorageInfo.showNotificationIfDiskFull(this);
        this.mStartId = startId;
        if (intent != null && "com.amazon.mp3.service.ACTION_CHECK_COMPONENTS".equals(intent.getAction())) {
            String stringExtra = intent.getStringExtra("com.amazon.mp3.EXTRA_CALLING_ACTION");
            if (stringExtra != null) {
                new Worker(stringExtra).start();
                return;
            }
            return;
        }
        stopSelf(startId);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onBootCompleted(String action) {
        checkAndSetComponentEnabledStates(action);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onLocaleChanged() {
        FileUtil.deleteFile(ConfigManager.getCachedRemoteConfigFile(getApplicationContext()));
        checkAndSetComponentEnabledStates();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onRunStartupCheck() {
        checkAndSetComponentEnabledStates();
    }

    private void checkAndSetComponentEnabledStates() {
        checkAndSetComponentEnabledStates(null);
    }

    private void checkAndSetComponentEnabledStates(String action) {
        if ("android.intent.action.BOOT_COMPLETED".equals(action)) {
            setComponentEnabled(FirstBootReceiver.class, false);
        }
        if (InstallSource.isAppPreloaded(getApplicationContext())) {
            setAppEnabledForPreload(true);
        } else {
            setComponentEnabled(FirstBootReceiver.class, false);
        }
        setComponentEnabled(AutoDownloadPurchaseReceiver.class, SettingsUtil.getCirrusDownloadPreference(this));
    }

    private void setAppEnabledForPreload(boolean enabled) {
        setComponentEnabled(LauncherActivity.class, enabled);
        setComponentEnabled(IntentProxyActivity.class, enabled);
        setComponentEnabled(PlayerWidgetProvider.class, enabled);
    }

    private void setComponentEnabled(Class<?> cls, boolean enabled) {
        getPackageManager().setComponentEnabledSetting(new ComponentName(this, cls), enabled ? 1 : 2, 1);
    }

    private class Worker extends Thread {
        final String mAction;

        public Worker(String action) {
            this.mAction = action;
            setPriority(1);
            setName("LocalService.Worker");
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            synchronized (ComponentCheckService.class) {
                String str = this.mAction;
                try {
                    if ("android.intent.action.CONFIGURATION_CHANGED".equals(str) || "android.intent.action.LOCALE_CHANGED".equals(str)) {
                        ComponentCheckService.this.onLocaleChanged();
                    } else if ("android.intent.action.BOOT_COMPLETED".equals(str)) {
                        ComponentCheckService.this.onBootCompleted(this.mAction);
                    } else if ("com.amazon.mp3.service.ACTION_RUN_STARTUP_CHECK".equals(str)) {
                        ComponentCheckService.this.onRunStartupCheck();
                    }
                } finally {
                    ComponentCheckService componentCheckService = ComponentCheckService.this;
                    componentCheckService.stopSelf(componentCheckService.mStartId);
                }
            }
        }
    }
}
