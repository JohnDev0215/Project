package cerence.ark.assistant.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.Process;
import cerence.ark.assistant.common.Logger;
import cerence.ark.assistant.common.Scheduler;
import cerence.ark.assistant.dm.NativeDialogManager;
import cerence.ark.assistant.framework.data.Config;
import cerence.ark.assistant.library.Utils;

/* JADX INFO: loaded from: classes.dex */
public final class SpeechService extends Service {
    public static final String ACTION_SET_CLOUD_SERVER = "cerence.ark.assistant.action.ACTION_SET_CLOUD_SERVER";
    public static final String KEY_CLOUD_SERVER = "key_cloud_server";
    private static final int NOTIFICATION_ID = 22;
    private static final String TAG = "SpeechService";
    private AssistantImpl mAssistant;
    private boolean mIsForegroundService = false;
    private final ILifecycle mLifecycle = new AnonymousClass1();

    public interface ILifecycle {
        void onDestroy();

        void onInitialize(Config config);
    }

    /* JADX INFO: renamed from: cerence.ark.assistant.service.SpeechService$1, reason: invalid class name */
    class AnonymousClass1 implements ILifecycle {
        AnonymousClass1() {
        }

        @Override // cerence.ark.assistant.service.SpeechService.ILifecycle
        public void onInitialize(final Config config) {
            Scheduler.runOnUiThread(new Runnable() { // from class: cerence.ark.assistant.service.SpeechService$1$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.m65xd78cc4a8(config);
                }
            });
        }

        /* JADX INFO: renamed from: lambda$onInitialize$0$cerence-ark-assistant-service-SpeechService$1, reason: not valid java name */
        /* synthetic */ void m65xd78cc4a8(Config config) {
            SpeechService.this.mIsForegroundService = false;
            if (config == null) {
                Logger.error(SpeechService.TAG, String.format("%s: config is null", "onInitialize"));
            } else if (config.getForegroundServiceNotification() != null) {
                SpeechService.this.mIsForegroundService = true;
                SpeechService.this.startForeground(22, config.getForegroundServiceNotification());
            }
        }

        @Override // cerence.ark.assistant.service.SpeechService.ILifecycle
        public void onDestroy() {
            Scheduler.runOnUiThread(new Runnable() { // from class: cerence.ark.assistant.service.SpeechService$1$$ExternalSyntheticLambda1
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.m64lambda$onDestroy$1$cerencearkassistantserviceSpeechService$1();
                }
            });
        }

        /* JADX INFO: renamed from: lambda$onDestroy$1$cerence-ark-assistant-service-SpeechService$1, reason: not valid java name */
        /* synthetic */ void m64lambda$onDestroy$1$cerencearkassistantserviceSpeechService$1() {
            if (SpeechService.this.mIsForegroundService) {
                SpeechService.this.stopForeground(true);
            }
        }
    }

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        int iMyPid = Process.myPid();
        Utils.init(getApplicationContext());
        Utils.setSpeechServicePid(iMyPid);
        Logger.debug(TAG, String.format("onCreate: sdk_version = %s, pid = %s ", "CA-SDK-ANDROID_Branch22cw24__2025_cw08A#20250221.2002#929254f9f", Integer.valueOf(iMyPid)));
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int i, int i2) {
        String str = TAG;
        Logger.debug(str, "onStartCommand");
        if (intent != null && ACTION_SET_CLOUD_SERVER.equals(intent.getAction())) {
            int intExtra = intent.getIntExtra(KEY_CLOUD_SERVER, 0);
            Logger.debug(str, String.format("%s: setCloudServer: %s, cloudServer = %s", "onStartCommand", NativeDialogManager.setCloudServer(intExtra).name(), Integer.valueOf(intExtra)));
        }
        return super.onStartCommand(intent, i, i2);
    }

    @Override // android.app.Service
    public void onDestroy() {
        super.onDestroy();
        Logger.debug(TAG, "onDestroy");
        AssistantImpl assistantImpl = this.mAssistant;
        if (assistantImpl != null) {
            assistantImpl.destroy(null);
            this.mAssistant = null;
        }
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        Logger.debug(TAG, "onBind");
        AssistantImpl assistantImpl = new AssistantImpl(this.mLifecycle);
        this.mAssistant = assistantImpl;
        return assistantImpl;
    }

    @Override // android.app.Service
    public boolean onUnbind(Intent intent) {
        Logger.debug(TAG, "onUnbind");
        stopSelf();
        return super.onUnbind(intent);
    }

    @Override // android.app.Service
    public void onTaskRemoved(Intent intent) {
        super.onTaskRemoved(intent);
        Logger.debug(TAG, "onTaskRemoved");
    }
}
