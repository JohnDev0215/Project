package com.amazon.avod.media.service.playbackoptimizationservice;

import com.amazon.avod.connectivity.ConnectionChangeListener;
import com.amazon.avod.connectivity.DetailedNetworkInfo;
import com.amazon.avod.connectivity.NetworkConnectionManager;
import com.amazon.avod.dash.config.miyagi.HeuristicsConfigStore;
import com.amazon.avod.dash.config.miyagi.SharedPreferencesHeuristicsConfigStore;
import com.amazon.avod.media.framework.MediaComponent;
import com.amazon.avod.media.playback.reporting.EventReporterFactory;
import com.amazon.avod.media.service.AVODServiceConfig;
import com.amazon.avod.perf.Profiler;
import com.amazon.avod.threading.ScheduledExecutorBuilder;
import com.amazon.avod.util.AppVisibilityTracker;
import com.amazon.avod.util.ApplicationVisibility;
import com.amazon.avod.util.DLog;
import com.google.common.base.Preconditions;
import java.util.Map;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nonnull;
import javax.annotation.concurrent.ThreadSafe;

/* JADX INFO: loaded from: classes2.dex */
@ThreadSafe
public class DefaultHeuristicsConfigurationSynchronizer implements MediaComponent {
    private final AVODServiceConfig mAVODServiceConfig;
    private final AppVisibilityTracker mAppVisibilityTracker;
    private DefaultHeuristicsConfigurationLoadingTask mDefaultHeuristicsConfigurationLoadingTask;
    private final DefaultHeuristicsSettingsFetcher mDefaultHeuristicsSettingsFetcher;
    private volatile boolean mHasInternetAccess;
    private final HeuristicsConfigStore mHeuristicsConfigStore;
    private volatile boolean mIsApplicationActive;
    private volatile boolean mIsInitialized;
    private final NetworkConnectionManager mNetworkConnectionManager;
    private final ScheduledThreadPoolExecutor mScheduledExecutorService;

    public DefaultHeuristicsConfigurationSynchronizer(EventReporterFactory eventReporterFactory) {
        this(NetworkConnectionManager.getInstance(), AppVisibilityTracker.getInstance(), SharedPreferencesHeuristicsConfigStore.getInstance(), new DefaultHeuristicsSettingsFetcher(eventReporterFactory.newStandaloneEventReporter(null)), ScheduledExecutorBuilder.newBuilder("HeuristicsConfigurationScheduler", new String[0]).withFixedThreadPoolSize(1).withProfilerTraceLevel(Profiler.TraceLevel.DEBUG).build(), AVODServiceConfig.getInstance());
    }

    DefaultHeuristicsConfigurationSynchronizer(@Nonnull NetworkConnectionManager networkConnectionManager, @Nonnull AppVisibilityTracker appVisibilityTracker, @Nonnull HeuristicsConfigStore heuristicsConfigStore, @Nonnull DefaultHeuristicsSettingsFetcher defaultHeuristicsSettingsFetcher, @Nonnull ScheduledThreadPoolExecutor scheduledThreadPoolExecutor, @Nonnull AVODServiceConfig aVODServiceConfig) {
        this.mNetworkConnectionManager = (NetworkConnectionManager) Preconditions.checkNotNull(networkConnectionManager, "networkConnectionManager");
        this.mAppVisibilityTracker = (AppVisibilityTracker) Preconditions.checkNotNull(appVisibilityTracker, "applicationVisibilityTracker");
        this.mHeuristicsConfigStore = (HeuristicsConfigStore) Preconditions.checkNotNull(heuristicsConfigStore, "heuristicsConfigStore");
        this.mDefaultHeuristicsSettingsFetcher = (DefaultHeuristicsSettingsFetcher) Preconditions.checkNotNull(defaultHeuristicsSettingsFetcher, "defaultHeuristicsSettingsFetcher");
        this.mScheduledExecutorService = (ScheduledThreadPoolExecutor) Preconditions.checkNotNull(scheduledThreadPoolExecutor, "scheduledThreadPoolExecutor");
        this.mAVODServiceConfig = (AVODServiceConfig) Preconditions.checkNotNull(aVODServiceConfig, "avodServiceConfig");
    }

    @Override // com.amazon.avod.media.framework.MediaComponent
    public void initialize() {
        Preconditions.checkState(!this.mIsInitialized, "Cannot initialize twice");
        this.mDefaultHeuristicsConfigurationLoadingTask = new DefaultHeuristicsConfigurationLoadingTask();
        this.mIsApplicationActive = this.mAppVisibilityTracker.getApplicationVisibility().isDeviceActive();
        this.mHasInternetAccess = this.mNetworkConnectionManager.hasDataConnection();
        this.mIsInitialized = true;
        this.mNetworkConnectionManager.registerListener((ConnectionChangeListener) new PrivateConnectionChangeListener());
        this.mAppVisibilityTracker.addAppVisibilityListenerWithImmediateCallback(new PrivateApplicationVisibilityListener());
        this.mScheduledExecutorService.submit(this.mDefaultHeuristicsConfigurationLoadingTask);
    }

    public boolean isInitialized() {
        return this.mIsInitialized;
    }

    private class DefaultHeuristicsConfigurationLoadingTask implements Runnable {
        private DefaultHeuristicsConfigurationLoadingTask() {
        }

        @Override // java.lang.Runnable
        public void run() {
            if (DefaultHeuristicsConfigurationSynchronizer.this.mAVODServiceConfig.isMiyagiEnabled()) {
                if (DefaultHeuristicsConfigurationSynchronizer.this.mHasInternetAccess && DefaultHeuristicsConfigurationSynchronizer.this.mIsApplicationActive) {
                    long timeToLiveMillis = DefaultHeuristicsConfigurationSynchronizer.this.mHeuristicsConfigStore.getTimeToLiveMillis();
                    if (timeToLiveMillis > 0) {
                        if (DefaultHeuristicsConfigurationSynchronizer.this.mScheduledExecutorService.getQueue().isEmpty()) {
                            DefaultHeuristicsConfigurationSynchronizer.this.mScheduledExecutorService.schedule(DefaultHeuristicsConfigurationSynchronizer.this.mDefaultHeuristicsConfigurationLoadingTask, timeToLiveMillis, TimeUnit.MILLISECONDS);
                            return;
                        }
                        return;
                    }
                    Map<String, String> playbackSettings = DefaultHeuristicsConfigurationSynchronizer.this.mDefaultHeuristicsSettingsFetcher.getPlaybackSettings();
                    if (playbackSettings != null) {
                        DefaultHeuristicsConfigurationSynchronizer.this.mHeuristicsConfigStore.reload(playbackSettings);
                        DefaultHeuristicsConfigurationSynchronizer.this.mScheduledExecutorService.schedule(DefaultHeuristicsConfigurationSynchronizer.this.mDefaultHeuristicsConfigurationLoadingTask, DefaultHeuristicsConfigurationSynchronizer.this.mHeuristicsConfigStore.getTimeToLiveMillis(), TimeUnit.MILLISECONDS);
                        return;
                    }
                    DLog.warnf("There is an error while getting playback settings. Skip updating");
                    return;
                }
                DLog.logf("Stop loading heuristics configuration because device is offline or inactive");
            }
        }
    }

    private class PrivateConnectionChangeListener extends ConnectionChangeListener {
        private PrivateConnectionChangeListener() {
        }

        @Override // com.amazon.avod.connectivity.ConnectivityChangeListener
        public void onConnectionChange(DetailedNetworkInfo detailedNetworkInfo, DetailedNetworkInfo detailedNetworkInfo2) {
            Preconditions.checkState(DefaultHeuristicsConfigurationSynchronizer.this.mIsInitialized, "onConnectionChange should not be called before initialization");
            if (detailedNetworkInfo2.hasFullNetworkAccess()) {
                DefaultHeuristicsConfigurationSynchronizer.this.mHasInternetAccess = true;
                DefaultHeuristicsConfigurationSynchronizer.this.mScheduledExecutorService.submit(DefaultHeuristicsConfigurationSynchronizer.this.mDefaultHeuristicsConfigurationLoadingTask);
            } else {
                DefaultHeuristicsConfigurationSynchronizer.this.mHasInternetAccess = false;
            }
        }
    }

    private class PrivateApplicationVisibilityListener implements AppVisibilityTracker.ApplicationVisibilityListener {
        private PrivateApplicationVisibilityListener() {
        }

        @Override // com.amazon.avod.util.AppVisibilityTracker.ApplicationVisibilityListener
        public void onApplicationVisibilityChanged(@Nonnull ApplicationVisibility applicationVisibility, @Nonnull ApplicationVisibility applicationVisibility2) {
            Preconditions.checkState(DefaultHeuristicsConfigurationSynchronizer.this.mIsInitialized, "onApplicationStateChanged should not be called before initialization");
            if (applicationVisibility2.isDeviceActive()) {
                DefaultHeuristicsConfigurationSynchronizer.this.mIsApplicationActive = true;
                DefaultHeuristicsConfigurationSynchronizer.this.mScheduledExecutorService.submit(DefaultHeuristicsConfigurationSynchronizer.this.mDefaultHeuristicsConfigurationLoadingTask);
            } else {
                DefaultHeuristicsConfigurationSynchronizer.this.mIsApplicationActive = false;
            }
        }
    }
}
