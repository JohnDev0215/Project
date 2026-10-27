package com.amazon.avod.media.events;

import amazon.android.config.ConfigurationValue;
import com.amazon.avod.connectivity.ConnectionChangeListener;
import com.amazon.avod.connectivity.DetailedNetworkInfo;
import com.amazon.avod.connectivity.NetworkConnectionManager;
import com.amazon.avod.media.events.MediaEventDispatcher;
import com.amazon.avod.media.events.clientapi.BatchConfig;
import com.amazon.avod.media.events.clientapi.InvalidRequestException;
import com.amazon.avod.media.events.clientapi.MediaReport;
import com.amazon.avod.media.events.clientapi.MediaReportClient;
import com.amazon.avod.media.events.clientapi.RetryConfig;
import com.amazon.avod.media.events.clientapi.ServerResponseException;
import com.amazon.avod.media.events.clientapi.UpdateConfig;
import com.amazon.avod.media.framework.config.MediaConfigBase;
import com.amazon.avod.perf.Profiler;
import com.amazon.avod.threading.ScheduledExecutorBuilder;
import com.amazon.avod.util.DLog;
import com.google.common.base.Preconditions;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import org.json.JSONException;

/* JADX INFO: loaded from: classes2.dex */
public class MediaReportDispatcherServices {
    private final AloysiusConfig mAloysiusConfig;
    private boolean mAreConfigsCached;
    private int mDefaultRetryDelayTimeInSeconds;
    private final MediaReportDispatcherServicesConfig mDispatcherConfig;
    private final ScheduledExecutorService mExecutorService;
    private boolean mIsAloysiusEnabled;
    private int mMaxReportsInMemory;
    private final MediaReportClient mMediaReportClient;
    private final NetworkListener mNetworkListener;
    private final NetworkConnectionManager mNetworkManager;
    private final ConcurrentLinkedQueue<Runnable> mPendingTasks;
    private RetryConfig mRetryConfig;

    public interface ExecutionCallback {
        void onComplete(@Nullable BatchConfig batchConfig);
    }

    public MediaReportDispatcherServices(@Nonnull MediaReportClient mediaReportClient, @Nonnull MediaReportDispatcherServicesConfig mediaReportDispatcherServicesConfig, @Nonnull NetworkConnectionManager networkConnectionManager) {
        this(mediaReportClient, ScheduledExecutorBuilder.newBuilderFor(MediaReportDispatcherServices.class, new String[0]).withProfilerTraceLevel(Profiler.TraceLevel.DEBUG).withFixedThreadPoolSize(mediaReportDispatcherServicesConfig.getNumberOfThreads()).build(), mediaReportDispatcherServicesConfig, networkConnectionManager, AloysiusConfig.getInstance());
    }

    public MediaReportDispatcherServices(@Nonnull MediaReportClient mediaReportClient, @Nonnull ScheduledExecutorService scheduledExecutorService, @Nonnull MediaReportDispatcherServicesConfig mediaReportDispatcherServicesConfig, @Nonnull NetworkConnectionManager networkConnectionManager, @Nonnull AloysiusConfig aloysiusConfig) {
        this.mAreConfigsCached = false;
        this.mMediaReportClient = (MediaReportClient) Preconditions.checkNotNull(mediaReportClient, "mediaReportClient");
        this.mExecutorService = (ScheduledExecutorService) Preconditions.checkNotNull(scheduledExecutorService, "scheduledExecutorService");
        this.mDispatcherConfig = (MediaReportDispatcherServicesConfig) Preconditions.checkNotNull(mediaReportDispatcherServicesConfig, "configs");
        this.mNetworkManager = (NetworkConnectionManager) Preconditions.checkNotNull(networkConnectionManager, "networkManager");
        this.mPendingTasks = new ConcurrentLinkedQueue<>();
        this.mAloysiusConfig = (AloysiusConfig) Preconditions.checkNotNull(aloysiusConfig, "aloysiusConfig");
        this.mNetworkListener = new NetworkListener();
    }

    public void dispatch(@Nonnull String str, @Nonnull MediaEventDispatcher.IRecordInflator iRecordInflator, @Nullable ExecutionCallback executionCallback, @Nullable ExecutionCallback executionCallback2) {
        Preconditions.checkNotNull(str, "appInstanceId");
        Preconditions.checkNotNull(iRecordInflator, "recordInflator");
        this.mExecutorService.schedule(createTask(str, iRecordInflator, executionCallback, executionCallback2, 0), 0L, TimeUnit.SECONDS);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void cacheConfigs() {
        if (this.mAreConfigsCached) {
            return;
        }
        this.mIsAloysiusEnabled = this.mAloysiusConfig.isAloysiusEnabled();
        this.mDefaultRetryDelayTimeInSeconds = this.mDispatcherConfig.getDefaultRetryDelayTimeInSeconds();
        this.mMaxReportsInMemory = this.mDispatcherConfig.getMaxReportsInMemory();
        this.mAreConfigsCached = true;
    }

    private Runnable createTask(final String str, @Nonnull final MediaEventDispatcher.IRecordInflator iRecordInflator, @Nullable final ExecutionCallback executionCallback, @Nullable final ExecutionCallback executionCallback2, final int i) {
        return new Runnable() { // from class: com.amazon.avod.media.events.MediaReportDispatcherServices.1
            @Override // java.lang.Runnable
            public void run() {
                MediaReportDispatcherServices.this.cacheConfigs();
                if (MediaReportDispatcherServices.this.mIsAloysiusEnabled) {
                    if (!MediaReportDispatcherServices.this.mNetworkManager.hasDataConnection()) {
                        MediaReportDispatcherServices.this.addPendingTask(this);
                        return;
                    }
                    try {
                        List<String> listInflate = iRecordInflator.inflate();
                        if (listInflate == null) {
                            ExecutionCallback executionCallback3 = executionCallback2;
                            if (executionCallback3 != null) {
                                executionCallback3.onComplete(null);
                                return;
                            }
                            return;
                        }
                        UpdateConfig updateConfigSubmitReport = MediaReportDispatcherServices.this.mMediaReportClient.submitReport(new MediaReport(str, System.currentTimeMillis(), listInflate));
                        ExecutionCallback executionCallback4 = executionCallback;
                        if (executionCallback4 != null) {
                            executionCallback4.onComplete(updateConfigSubmitReport.getBatching());
                        }
                        if (updateConfigSubmitReport.getRetry() != null) {
                            MediaReportDispatcherServices.this.mRetryConfig = updateConfigSubmitReport.getRetry();
                            return;
                        }
                        return;
                    } catch (InvalidRequestException e) {
                        DLog.errorf("aloysius invalid request error, message: %s - root cause: %s", e.getMessage(), e.getCause());
                        Object responseObject = e.getResponseObject();
                        UpdateConfig updateConfig = responseObject instanceof UpdateConfig ? (UpdateConfig) responseObject : null;
                        BatchConfig batching = updateConfig != null ? updateConfig.getBatching() : null;
                        ExecutionCallback executionCallback5 = executionCallback2;
                        if (executionCallback5 != null) {
                            executionCallback5.onComplete(batching);
                        }
                        if (updateConfig == null || updateConfig.getRetry() == null) {
                            return;
                        }
                        MediaReportDispatcherServices.this.mRetryConfig = updateConfig.getRetry();
                        return;
                    } catch (ServerResponseException e2) {
                        DLog.warnf("aloysius server response error, message: %s", e2.getMessage());
                        MediaReportDispatcherServices mediaReportDispatcherServices = MediaReportDispatcherServices.this;
                        mediaReportDispatcherServices.scheduleTask(str, iRecordInflator, executionCallback, executionCallback2, mediaReportDispatcherServices.calculateDelay(i));
                        return;
                    } catch (JSONException e3) {
                        DLog.errorf("Invalid JSON in Aloysius record, dropping record: %s - root cause: %s", e3.getMessage(), e3.getCause());
                        ExecutionCallback executionCallback6 = executionCallback2;
                        if (executionCallback6 != null) {
                            executionCallback6.onComplete(null);
                            return;
                        }
                        return;
                    }
                }
                MediaReportDispatcherServices mediaReportDispatcherServices2 = MediaReportDispatcherServices.this;
                mediaReportDispatcherServices2.scheduleTask(str, iRecordInflator, executionCallback, executionCallback2, mediaReportDispatcherServices2.calculateDelay(i));
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void scheduleTask(String str, @Nonnull MediaEventDispatcher.IRecordInflator iRecordInflator, @Nullable ExecutionCallback executionCallback, @Nullable ExecutionCallback executionCallback2, int i) {
        DLog.logf("scheduling aloysius dispatch task in %d seconds, record id: %s", Integer.valueOf(i), Long.valueOf(iRecordInflator.getRecordId()));
        this.mExecutorService.schedule(createTask(str, iRecordInflator, executionCallback, executionCallback2, i), i, TimeUnit.SECONDS);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addPendingTask(Runnable runnable) {
        if (this.mPendingTasks.isEmpty()) {
            this.mNetworkManager.registerListener((ConnectionChangeListener) this.mNetworkListener);
        }
        this.mPendingTasks.add(runnable);
        int i = this.mMaxReportsInMemory;
        DLog.logf("aloysius: removing in-queue reports");
        while (this.mPendingTasks.size() > i) {
            this.mPendingTasks.poll();
        }
        DLog.logf("aloysius: total of in memory reports: %d", Integer.valueOf(this.mPendingTasks.size()));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int calculateDelay(int i) {
        RetryConfig retryConfig = this.mRetryConfig;
        if (retryConfig == null) {
            return this.mDefaultRetryDelayTimeInSeconds;
        }
        return Math.min((i + retryConfig.getJitter().intValue()) * this.mRetryConfig.getModifier().intValue(), this.mRetryConfig.getMax().intValue());
    }

    public void setRetryConfig(RetryConfig retryConfig) {
        this.mRetryConfig = retryConfig;
    }

    public static class MediaReportDispatcherServicesConfig extends MediaConfigBase {
        private final ConfigurationValue<Integer> mNumberOfThreads = newIntConfigValue("playback.aloysius.dispatcherservices.numberOfThreads", 1);
        private final ConfigurationValue<Integer> mDefaultRetryDelayTimeInSeconds = newIntConfigValue("playback.aloysius.dispatcherservices.defaultRetryDelayTimeInSeconds", 120);
        private final ConfigurationValue<Integer> mMaxReportsInMemory = newIntConfigValue("playback.aloysius.dispatcherservices.maxReportsInMemory", 30);

        public int getNumberOfThreads() {
            return this.mNumberOfThreads.getValue().intValue();
        }

        public int getDefaultRetryDelayTimeInSeconds() {
            return this.mDefaultRetryDelayTimeInSeconds.getValue().intValue();
        }

        public int getMaxReportsInMemory() {
            return this.mMaxReportsInMemory.getValue().intValue();
        }
    }

    private class NetworkListener extends ConnectionChangeListener {
        private NetworkListener() {
        }

        @Override // com.amazon.avod.connectivity.ConnectivityChangeListener
        public void onConnectionChange(DetailedNetworkInfo detailedNetworkInfo, DetailedNetworkInfo detailedNetworkInfo2) {
            if (detailedNetworkInfo2.hasNoNetworkAccess()) {
                return;
            }
            MediaReportDispatcherServices.this.mExecutorService.submit(new Runnable() { // from class: com.amazon.avod.media.events.MediaReportDispatcherServices.NetworkListener.1
                @Override // java.lang.Runnable
                public void run() {
                    DLog.logf("network is back, number of pending tasks: %d", Integer.valueOf(MediaReportDispatcherServices.this.mPendingTasks.size()));
                    Iterator it = MediaReportDispatcherServices.this.mPendingTasks.iterator();
                    while (it.hasNext()) {
                        MediaReportDispatcherServices.this.mExecutorService.schedule((Runnable) it.next(), 0L, TimeUnit.SECONDS);
                        it.remove();
                    }
                    if (MediaReportDispatcherServices.this.mPendingTasks.isEmpty()) {
                        MediaReportDispatcherServices.this.mNetworkManager.unregisterListener(MediaReportDispatcherServices.this.mNetworkListener);
                    }
                }
            });
        }
    }
}
