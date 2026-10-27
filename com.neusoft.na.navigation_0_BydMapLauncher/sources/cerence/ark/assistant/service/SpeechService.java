package cerence.ark.assistant.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.Process;
import cerence.ark.assistant.common.Logger;
import cerence.ark.assistant.common.Scheduler;
import cerence.ark.assistant.framework.data.Config;
import cerence.ark.assistant.library.Utils;

/* JADX INFO: loaded from: classes2.dex */
public final class SpeechService extends Service {
    private static final int NOTIFICATION_ID = 22;
    private static final String TAG = "SpeechService";
    private AssistantImpl mAssistant;
    private InternalLifecycle mInternalLifecycle;

    public interface ILifecycle {
        void onDestroy();

        void onInitialize(Config config);
    }

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        this.mInternalLifecycle = new InternalLifecycle(this);
        this.mAssistant = new AssistantImpl(this.mInternalLifecycle);
        int iMyPid = Process.myPid();
        Utils.init(getApplicationContext());
        Utils.setSpeechServicePid(iMyPid);
        Logger.info(TAG, Utils.format("onCreate: sdk_version = %s, pid = %s ", BuildConfig.SDK_VERSION, Integer.valueOf(iMyPid)));
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int i, int i2) {
        Logger.info(TAG, "onStartCommand");
        return super.onStartCommand(intent, i, i2);
    }

    @Override // android.app.Service
    public void onDestroy() {
        super.onDestroy();
        Logger.info(TAG, "onDestroy");
        this.mInternalLifecycle.onDestroy();
        this.mAssistant.destroy(null);
        this.mAssistant = null;
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        Logger.info(TAG, "onBind");
        return this.mAssistant;
    }

    @Override // android.app.Service
    public boolean onUnbind(Intent intent) {
        Logger.info(TAG, "onUnbind");
        return super.onUnbind(intent);
    }

    @Override // android.app.Service
    public void onTaskRemoved(Intent intent) {
        super.onTaskRemoved(intent);
        Logger.info(TAG, "onTaskRemoved");
    }

    /* JADX INFO: Access modifiers changed from: private */
    static class InternalLifecycle implements ILifecycle {
        private static final String TAG = "InternalLifecycle";
        private boolean mIsForegroundService = false;
        private Service mService;

        public InternalLifecycle(Service service) {
            this.mService = service;
        }

        @Override // cerence.ark.assistant.service.SpeechService.ILifecycle
        public void onInitialize(final Config config) {
            Scheduler.runOnUiThread(new Runnable() { // from class: cerence.ark.assistant.service.SpeechService$InternalLifecycle$$ExternalSyntheticLambda1
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.m91xf143bb84(config);
                }
            });
        }

        /* JADX INFO: renamed from: lambda$onInitialize$0$cerence-ark-assistant-service-SpeechService$InternalLifecycle, reason: not valid java name */
        /* synthetic */ void m91xf143bb84(Config config) {
            if (config != null) {
                if (config.getForegroundServiceNotification() != null) {
                    Service service = this.mService;
                    if (service != null) {
                        this.mIsForegroundService = true;
                        service.startForeground(22, config.getForegroundServiceNotification());
                        Logger.info(TAG, "onInitialize: startForeground");
                        return;
                    }
                    Logger.warn(TAG, Utils.format("%s: service is null", "onInitialize"));
                    return;
                }
                return;
            }
            Logger.error(TAG, Utils.format("%s: config is null", "onInitialize"));
        }

        @Override // cerence.ark.assistant.service.SpeechService.ILifecycle
        public void onDestroy() {
            Scheduler.runOnUiThread(new Runnable() { // from class: cerence.ark.assistant.service.SpeechService$InternalLifecycle$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.m90x915163b7();
                }
            });
        }

        /* JADX INFO: renamed from: lambda$onDestroy$1$cerence-ark-assistant-service-SpeechService$InternalLifecycle, reason: not valid java name */
        /* synthetic */ void m90x915163b7() {
            Service service;
            if (this.mIsForegroundService && (service = this.mService) != null) {
                service.stopForeground(true);
                Logger.info(TAG, "onDestroy: stopForeground");
            }
            this.mIsForegroundService = false;
            this.mService = null;
        }
    }
}
