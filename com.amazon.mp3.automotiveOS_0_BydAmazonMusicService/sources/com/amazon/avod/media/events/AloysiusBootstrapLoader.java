package com.amazon.avod.media.events;

import amazon.android.config.ConfigurationValue;
import com.amazon.avod.connectivity.NetworkConnectionManager;
import com.amazon.avod.media.downloadservice.exceptions.NoDataConnectionException;
import com.amazon.avod.media.events.clientapi.BootstrapConfig;
import com.amazon.avod.media.events.clientapi.InvalidRequestException;
import com.amazon.avod.media.events.clientapi.MediaReportClient;
import com.amazon.avod.media.events.clientapi.ServerResponseException;
import com.amazon.avod.media.framework.MediaComponent;
import com.amazon.avod.media.framework.config.MediaConfigBase;
import com.amazon.avod.util.DLog;
import com.google.common.base.Preconditions;
import java.util.concurrent.Callable;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class AloysiusBootstrapLoader implements MediaComponent {
    private final AloysiusConfig mAloysiusConfig;
    private String mAppInstanceId;
    private final AloysiusBootstrapLoaderConfig mConfig;
    private final ScheduledExecutorService mExecutorService;
    private int mInitAttempts;
    private final MediaEventDispatcher mMediaEventDispatcher;
    private final MediaReportClient mMediaReportClient;
    private final MediaReportDispatcherServices mMediaReportDispatcherServices;
    private ScheduledFuture<String> mScheduledTask;
    private Lock mTaskLock = new ReentrantLock();
    private final NetworkConnectionManager mNetworkConnectionManager = NetworkConnectionManager.getInstance();

    public AloysiusBootstrapLoader(@Nonnull MediaReportClient mediaReportClient, @Nonnull MediaEventDispatcher mediaEventDispatcher, @Nonnull MediaReportDispatcherServices mediaReportDispatcherServices, @Nonnull AloysiusConfig aloysiusConfig, @Nonnull AloysiusBootstrapLoaderConfig aloysiusBootstrapLoaderConfig, @Nonnull ScheduledExecutorService scheduledExecutorService) {
        this.mMediaReportClient = (MediaReportClient) Preconditions.checkNotNull(mediaReportClient);
        this.mMediaEventDispatcher = (MediaEventDispatcher) Preconditions.checkNotNull(mediaEventDispatcher);
        this.mMediaReportDispatcherServices = (MediaReportDispatcherServices) Preconditions.checkNotNull(mediaReportDispatcherServices);
        this.mAloysiusConfig = (AloysiusConfig) Preconditions.checkNotNull(aloysiusConfig, "aloysiusConfig");
        this.mExecutorService = (ScheduledExecutorService) Preconditions.checkNotNull(scheduledExecutorService);
        this.mConfig = (AloysiusBootstrapLoaderConfig) Preconditions.checkNotNull(aloysiusBootstrapLoaderConfig, "bootstrapLoaderConfig");
    }

    @Override // com.amazon.avod.media.framework.MediaComponent
    public void initialize() {
        scheduleTask(0, false);
    }

    private Callable<String> createBootstrapTask() {
        return new Callable<String>() { // from class: com.amazon.avod.media.events.AloysiusBootstrapLoader.1
            @Override // java.util.concurrent.Callable
            public String call() {
                if (AloysiusBootstrapLoader.this.mAloysiusConfig.isAloysiusEnabled()) {
                    if (AloysiusBootstrapLoader.this.shouldScheduleAnotherRetry()) {
                        try {
                            if (!AloysiusBootstrapLoader.this.mNetworkConnectionManager.getNetworkInfoSync().hasFullNetworkAccess()) {
                                throw new NoDataConnectionException("Data connection unavailable");
                            }
                            DLog.logf("aloysius bootstrap: calling endpoint");
                            BootstrapConfig bootstrapConfigBootstrap = AloysiusBootstrapLoader.this.mMediaReportClient.bootstrap();
                            AloysiusBootstrapLoader.this.finishStartupProcess(bootstrapConfigBootstrap);
                            DLog.logf("aloysius bootstrap: done!");
                            return bootstrapConfigBootstrap.getAppInstanceId();
                        } catch (NoDataConnectionException | InvalidRequestException | ServerResponseException | RuntimeException e) {
                            DLog.errorf("aloysius bootstrap: %s - %s", e.getMessage(), e.getCause());
                            AloysiusBootstrapLoader aloysiusBootstrapLoader = AloysiusBootstrapLoader.this;
                            aloysiusBootstrapLoader.scheduleTask(aloysiusBootstrapLoader.getNextRetryDelayInSeconds(), true);
                            return null;
                        }
                    }
                    DLog.logf("aloysius bootstrap: cancelling retry task, max attempts was achieved, attempts: %d", Integer.valueOf(AloysiusBootstrapLoader.this.mInitAttempts));
                    return null;
                }
                DLog.logf("aloysius bootstrap: cancelling retry task, aloysius is disabled");
                return null;
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void scheduleTask(int i, boolean z) {
        ScheduledFuture<String> scheduledFuture;
        this.mTaskLock.lock();
        try {
            if (this.mAppInstanceId == null && (z || (scheduledFuture = this.mScheduledTask) == null || scheduledFuture.isDone())) {
                DLog.logf("aloysius bootstrap: creating a new bootstrap task, delayInSeconds: %d", Integer.valueOf(i));
                this.mScheduledTask = this.mExecutorService.schedule(createBootstrapTask(), i, TimeUnit.SECONDS);
            }
        } finally {
            this.mTaskLock.unlock();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int getNextRetryDelayInSeconds() {
        int retryDelayInSeconds = this.mConfig.getRetryDelayInSeconds();
        int i = this.mInitAttempts + 1;
        this.mInitAttempts = i;
        return retryDelayInSeconds * Math.min(i, this.mConfig.getMaxRetries());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean shouldScheduleAnotherRetry() {
        return !this.mConfig.isDisableRetriesAfterReachMax().booleanValue() || this.mInitAttempts < this.mConfig.getMaxRetries();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void finishStartupProcess(@Nonnull BootstrapConfig bootstrapConfig) {
        Preconditions.checkNotNull(bootstrapConfig, "bootstrapConfig");
        DLog.logf("aloysius bootstrap: updating QoS APIs, appInstanceId: %s", bootstrapConfig.getAppInstanceId());
        this.mAppInstanceId = bootstrapConfig.getAppInstanceId();
        this.mMediaEventDispatcher.setBatchConfig(bootstrapConfig.getBatching());
        this.mMediaEventDispatcher.setEventConfig(bootstrapConfig.getEventConfig());
        this.mMediaReportDispatcherServices.setRetryConfig(bootstrapConfig.getRetry());
        this.mMediaEventDispatcher.bootstrap(bootstrapConfig.getAppInstanceId());
        this.mMediaEventDispatcher.initializeBatchDispatchers();
        DLog.logf("aloysius bootstrap: QoS APIs are configured!");
    }

    public static class AloysiusBootstrapLoaderConfig extends MediaConfigBase {
        private final ConfigurationValue<Boolean> mDisableRetriesAfterReachMax;
        private final ConfigurationValue<Integer> mMaxRetries;
        private final ConfigurationValue<Integer> mRetryDelayInSeconds;

        private AloysiusBootstrapLoaderConfig() {
            this.mRetryDelayInSeconds = newIntConfigValue("playback.aloysius.bootstrap.retryDelayInSeconds", 10);
            this.mMaxRetries = newIntConfigValue("playback.aloysius.bootstrap.maxRetries", 20);
            this.mDisableRetriesAfterReachMax = newBooleanConfigValue("playback.aloysius.bootstrap.disableRetriesAfterReachMax", true);
        }

        public int getRetryDelayInSeconds() {
            return this.mRetryDelayInSeconds.getValue().intValue();
        }

        public int getMaxRetries() {
            return this.mMaxRetries.getValue().intValue();
        }

        public Boolean isDisableRetriesAfterReachMax() {
            return this.mDisableRetriesAfterReachMax.getValue();
        }

        private static class InstanceHolder {
            private static final AloysiusBootstrapLoaderConfig mAloysiusConfig = new AloysiusBootstrapLoaderConfig();

            private InstanceHolder() {
            }
        }

        public static AloysiusBootstrapLoaderConfig getInstance() {
            return InstanceHolder.mAloysiusConfig;
        }
    }
}
