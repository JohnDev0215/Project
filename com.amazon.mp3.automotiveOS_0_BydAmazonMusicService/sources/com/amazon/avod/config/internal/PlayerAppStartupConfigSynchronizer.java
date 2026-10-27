package com.amazon.avod.config.internal;

import com.amazon.avod.connectivity.ConnectivityChangeListener;
import com.amazon.avod.connectivity.DetailedNetworkInfo;
import com.amazon.avod.connectivity.NetworkConnectionManager;
import com.amazon.avod.core.InitializationException;
import com.amazon.avod.http.RequestBuildException;
import com.amazon.avod.perf.Profiler;
import com.amazon.avod.playback.core.PlayerInitializationErrorCode;
import com.amazon.avod.threading.ScheduledExecutorBuilder;
import com.amazon.avod.util.AppStartConfig;
import com.amazon.avod.util.DLog;
import com.amazon.avod.util.InitializationLatch;
import com.amazon.bolthttp.BoltException;
import com.google.common.base.Preconditions;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class PlayerAppStartupConfigSynchronizer {
    private volatile boolean mHasInternetAccess;
    private final InitializationLatch mInitializationLatch;
    private final NetworkConnectionManager mNetworkConnectionManager;
    private final PlaybackAppStartupConfigRefreshTask mPlaybackAppStartupConfigRefreshTask;
    private final PlayerAppStartupConfigFetcher mPlayerAppStartupConfigFetcher;
    private final PlayerAppStartupConfigPersistence mPlayerAppStartupConfigPersistence;
    private final ScheduledThreadPoolExecutor mScheduledExecutorService;

    public static PlayerAppStartupConfigSynchronizer getInstance() {
        return SingletonHolder.INSTANCE;
    }

    private static class SingletonHolder {
        private static final PlayerAppStartupConfigSynchronizer INSTANCE = new PlayerAppStartupConfigSynchronizer();

        private SingletonHolder() {
        }
    }

    private PlayerAppStartupConfigSynchronizer() {
        this(NetworkConnectionManager.getInstance(), new PlayerAppStartupConfigFetcher(), new PlayerAppStartupConfigPersistence(), ScheduledExecutorBuilder.newBuilder("PlayerAppStartupConfigSynchronizer", new String[0]).withFixedThreadPoolSize(1).withProfilerTraceLevel(Profiler.TraceLevel.DEBUG).build());
    }

    PlayerAppStartupConfigSynchronizer(@Nonnull NetworkConnectionManager networkConnectionManager, @Nonnull PlayerAppStartupConfigFetcher playerAppStartupConfigFetcher, @Nonnull PlayerAppStartupConfigPersistence playerAppStartupConfigPersistence, @Nonnull ScheduledThreadPoolExecutor scheduledThreadPoolExecutor) {
        this.mInitializationLatch = new InitializationLatch(this);
        this.mPlaybackAppStartupConfigRefreshTask = new PlaybackAppStartupConfigRefreshTask();
        this.mNetworkConnectionManager = (NetworkConnectionManager) Preconditions.checkNotNull(networkConnectionManager, "networkConnectionManager");
        this.mPlayerAppStartupConfigFetcher = (PlayerAppStartupConfigFetcher) Preconditions.checkNotNull(playerAppStartupConfigFetcher, "mPlayerAppStartupConfigFetcher");
        this.mPlayerAppStartupConfigPersistence = (PlayerAppStartupConfigPersistence) Preconditions.checkNotNull(playerAppStartupConfigPersistence, "playerAppStartupConfigPersistence");
        this.mScheduledExecutorService = (ScheduledThreadPoolExecutor) Preconditions.checkNotNull(scheduledThreadPoolExecutor, "scheduledThreadPoolExecutor");
    }

    public void initialize() throws InitializationException {
        this.mInitializationLatch.start(350L, TimeUnit.SECONDS);
        this.mNetworkConnectionManager.registerListener(new ConnectionChangeListener());
        this.mHasInternetAccess = this.mNetworkConnectionManager.getNetworkInfoSync().hasFullNetworkAccess();
        Future futureSubmit = this.mScheduledExecutorService.submit(this.mPlaybackAppStartupConfigRefreshTask);
        AppStartConfig appStartConfig = AppStartConfig.getInstance();
        if (appStartConfig.isFirstAppStartForSignedStatus(true)) {
            try {
                futureSubmit.get();
                appStartConfig.markFirstAppStartForSignedStatus(true);
            } catch (InterruptedException | ExecutionException e) {
                DLog.exceptionf(e, "Failed to load PlayerAppStartupConfig", new Object[0]);
                throw new InitializationException(PlayerInitializationErrorCode.SERVER_CONFIG_FETCH_ERROR, "Failed to load PlayerAppStartupConfig");
            }
        }
        this.mInitializationLatch.complete();
    }

    private class PlaybackAppStartupConfigRefreshTask implements Callable {
        private PlaybackAppStartupConfigRefreshTask() {
        }

        @Override // java.util.concurrent.Callable
        public Void call() throws BoltException, RequestBuildException {
            if (PlayerAppStartupConfigSynchronizer.this.mHasInternetAccess) {
                PlayerAppStartupConfigResponse playerAppStartupConfigResponseFetch = PlayerAppStartupConfigSynchronizer.this.mPlayerAppStartupConfigFetcher.fetch();
                PlayerAppStartupConfigSynchronizer.this.mPlayerAppStartupConfigPersistence.saveConfig(playerAppStartupConfigResponseFetch);
                DLog.logf("Successfully saved playback app startup config");
                PlayerAppStartupConfigSynchronizer.this.mScheduledExecutorService.schedule(PlayerAppStartupConfigSynchronizer.this.mPlaybackAppStartupConfigRefreshTask, AppStartupConfigResponseUtil.parseExpiryTimeMillis(playerAppStartupConfigResponseFetch.customerConfig), TimeUnit.MILLISECONDS);
                return null;
            }
            DLog.logf("Stop loading playback app startup configuration because device is offline or inactive");
            return null;
        }
    }

    @SuppressFBWarnings(justification = "We ignore the result of the callable as we only care about it on the first call", value = {"RV_RETURN_VALUE_IGNORED_BAD_PRACTICE"})
    private class ConnectionChangeListener implements ConnectivityChangeListener {
        private ConnectionChangeListener() {
        }

        @Override // com.amazon.avod.connectivity.ConnectivityChangeListener
        public void onConnectionChange(@Nonnull DetailedNetworkInfo detailedNetworkInfo, @Nonnull DetailedNetworkInfo detailedNetworkInfo2) {
            if (detailedNetworkInfo2.hasFullNetworkAccess()) {
                PlayerAppStartupConfigSynchronizer.this.mHasInternetAccess = true;
                PlayerAppStartupConfigSynchronizer.this.mScheduledExecutorService.submit(PlayerAppStartupConfigSynchronizer.this.mPlaybackAppStartupConfigRefreshTask);
            } else {
                PlayerAppStartupConfigSynchronizer.this.mHasInternetAccess = false;
            }
        }
    }
}
