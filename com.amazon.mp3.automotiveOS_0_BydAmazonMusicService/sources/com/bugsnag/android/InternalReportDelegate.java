package com.bugsnag.android;

import android.content.Context;
import android.os.Build;
import android.os.storage.StorageManager;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.bugsnag.android.FileStore;
import com.bugsnag.android.internal.BackgroundTaskService;
import com.bugsnag.android.internal.ImmutableConfig;
import com.bugsnag.android.internal.JsonHelper;
import com.bugsnag.android.internal.TaskType;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.RejectedExecutionException;

/* JADX INFO: loaded from: classes5.dex */
class InternalReportDelegate implements FileStore.Delegate {
    final Context appContext;
    final AppDataCollector appDataCollector;
    final BackgroundTaskService backgroundTaskService;
    final ImmutableConfig config;
    final DeviceDataCollector deviceDataCollector;
    final Logger logger;
    final Notifier notifier;
    final SessionTracker sessionTracker;

    @Nullable
    final StorageManager storageManager;

    InternalReportDelegate(Context context, Logger logger, ImmutableConfig immutableConfig, @Nullable StorageManager storageManager, AppDataCollector appDataCollector, DeviceDataCollector deviceDataCollector, SessionTracker sessionTracker, Notifier notifier, BackgroundTaskService backgroundTaskService) {
        this.logger = logger;
        this.config = immutableConfig;
        this.storageManager = storageManager;
        this.appDataCollector = appDataCollector;
        this.deviceDataCollector = deviceDataCollector;
        this.appContext = context;
        this.sessionTracker = sessionTracker;
        this.notifier = notifier;
        this.backgroundTaskService = backgroundTaskService;
    }

    @Override // com.bugsnag.android.FileStore.Delegate
    public void onErrorIOFailure(Exception exc, File file, String str) {
        Event event = new Event(exc, this.config, SeverityReason.newInstance("unhandledException"), this.logger);
        event.setContext(str);
        event.addMetadata("BugsnagDiagnostics", "canRead", Boolean.valueOf(file.canRead()));
        event.addMetadata("BugsnagDiagnostics", "canWrite", Boolean.valueOf(file.canWrite()));
        event.addMetadata("BugsnagDiagnostics", "exists", Boolean.valueOf(file.exists()));
        event.addMetadata("BugsnagDiagnostics", "usableSpace", Long.valueOf(this.appContext.getCacheDir().getUsableSpace()));
        event.addMetadata("BugsnagDiagnostics", "filename", file.getName());
        event.addMetadata("BugsnagDiagnostics", "fileLength", Long.valueOf(file.length()));
        recordStorageCacheBehavior(event);
        reportInternalBugsnagError(event);
    }

    void recordStorageCacheBehavior(Event event) {
        if (this.storageManager == null || Build.VERSION.SDK_INT < 26) {
            return;
        }
        File file = new File(this.appContext.getCacheDir(), "bugsnag-errors");
        try {
            boolean zIsCacheBehaviorTombstone = this.storageManager.isCacheBehaviorTombstone(file);
            boolean zIsCacheBehaviorGroup = this.storageManager.isCacheBehaviorGroup(file);
            event.addMetadata("BugsnagDiagnostics", "cacheTombstone", Boolean.valueOf(zIsCacheBehaviorTombstone));
            event.addMetadata("BugsnagDiagnostics", "cacheGroup", Boolean.valueOf(zIsCacheBehaviorGroup));
        } catch (IOException e) {
            this.logger.w("Failed to record cache behaviour, skipping diagnostics", e);
        }
    }

    void reportInternalBugsnagError(@NonNull Event event) {
        event.setApp(this.appDataCollector.generateAppWithState());
        event.setDevice(this.deviceDataCollector.generateDeviceWithState(new Date().getTime()));
        event.addMetadata("BugsnagDiagnostics", "notifierName", this.notifier.getName());
        event.addMetadata("BugsnagDiagnostics", "notifierVersion", this.notifier.getVersion());
        event.addMetadata("BugsnagDiagnostics", "apiKey", this.config.getApiKey());
        final EventPayload eventPayload = new EventPayload(null, event, this.notifier, this.config);
        try {
            this.backgroundTaskService.submitTask(TaskType.INTERNAL_REPORT, new Runnable() { // from class: com.bugsnag.android.InternalReportDelegate.1
                @Override // java.lang.Runnable
                public void run() {
                    try {
                        InternalReportDelegate.this.logger.d("InternalReportDelegate - sending internal event");
                        Delivery delivery = InternalReportDelegate.this.config.getDelivery();
                        DeliveryParams errorApiDeliveryParams = InternalReportDelegate.this.config.getErrorApiDeliveryParams(eventPayload);
                        if (delivery instanceof DefaultDelivery) {
                            Map<String, String> headers = errorApiDeliveryParams.getHeaders();
                            headers.put("Bugsnag-Internal-Error", "bugsnag-android");
                            headers.remove("Bugsnag-Api-Key");
                            ((DefaultDelivery) delivery).deliver(errorApiDeliveryParams.getEndpoint(), JsonHelper.INSTANCE.serialize(eventPayload), headers);
                        }
                    } catch (Exception e) {
                        InternalReportDelegate.this.logger.w("Failed to report internal event to Bugsnag", e);
                    }
                }
            });
        } catch (RejectedExecutionException unused) {
        }
    }
}
