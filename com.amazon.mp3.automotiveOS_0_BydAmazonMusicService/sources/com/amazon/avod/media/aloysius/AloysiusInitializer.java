package com.amazon.avod.media.aloysius;

import androidx.annotation.VisibleForTesting;
import com.amazon.avod.connectivity.ConnectionChangeListener;
import com.amazon.avod.connectivity.ConnectionEventListener;
import com.amazon.avod.connectivity.NetworkConnectionManager;
import com.amazon.avod.experiments.MobileWeblab;
import com.amazon.avod.experiments.PlaybackWeblabs;
import com.amazon.avod.experiments.WeblabTreatment;
import com.amazon.avod.media.events.AloysiusBootstrapLoader;
import com.amazon.avod.media.events.AloysiusConfig;
import com.amazon.avod.media.events.MediaEventDispatcher;
import com.amazon.avod.media.events.MediaEventQueue;
import com.amazon.avod.media.events.MediaEventReporter;
import com.amazon.avod.media.playback.reporting.aloysius.AloysiusDownloadReporter;
import com.amazon.avod.media.playback.reporting.aloysius.AloysiusInteractionReporter;
import com.amazon.avod.media.playback.reporting.aloysius.AloysiusInterfaceReporter;
import com.amazon.avod.media.playback.reporting.aloysius.AloysiusInterfaceReporterImpl;
import com.amazon.avod.media.playback.reporting.aloysius.AloysiusPlaybackStateTracker;
import com.amazon.avod.media.playback.reporting.aloysius.AloysiusRemoteReporter;
import com.amazon.avod.util.BetaConfigProvider;
import com.amazon.avod.util.InitializationLatch;
import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class AloysiusInitializer {
    private final AloysiusBootstrapLoader mAloysiusBootstrapLoader;
    private final AloysiusConfig mAloysiusConfig;
    private final AloysiusDownloadReporter mAloysiusDownloadReporter;
    private final AloysiusInteractionReporter mAloysiusInteractionReporter;
    private final AloysiusInterfaceReporter mAloysiusInterfaceReporter;
    private MobileWeblab mAloysiusRefactorWeblab;
    private final AloysiusRemoteReporter mAloysiusRemoteReporter;
    private AloysiusConnectionEventListener mConnectionEventListener;
    private InitializationLatch mInitializationLatch;
    private final MediaEventDispatcher mMediaEventDispatcher;
    private final MediaEventQueue mMediaEventQueue;

    public AloysiusInitializer(@Nonnull AloysiusBootstrapLoader aloysiusBootstrapLoader, @Nonnull MediaEventDispatcher mediaEventDispatcher, @Nonnull MediaEventQueue mediaEventQueue) {
        this(aloysiusBootstrapLoader, mediaEventDispatcher, mediaEventQueue, AloysiusConfig.getInstance(), createAloysiusInterfaceReporter(mediaEventQueue), createAloysiusInteractionReporter(mediaEventQueue), createAloysiusRemoteReporter(mediaEventQueue), createAloysiusDownloadReporter(mediaEventQueue), PlaybackWeblabs.getPlaybackWeblabs().get("AV_ANDROID_ALOYSIUS_287513"));
    }

    @VisibleForTesting
    public AloysiusInitializer(@Nonnull AloysiusBootstrapLoader aloysiusBootstrapLoader, @Nonnull MediaEventDispatcher mediaEventDispatcher, @Nonnull MediaEventQueue mediaEventQueue, @Nonnull AloysiusConfig aloysiusConfig, @Nonnull AloysiusInterfaceReporter aloysiusInterfaceReporter, @Nonnull AloysiusInteractionReporter aloysiusInteractionReporter, @Nonnull AloysiusRemoteReporter aloysiusRemoteReporter, @Nonnull AloysiusDownloadReporter aloysiusDownloadReporter, @Nonnull MobileWeblab mobileWeblab) {
        this.mInitializationLatch = new InitializationLatch(this);
        this.mAloysiusBootstrapLoader = (AloysiusBootstrapLoader) Preconditions.checkNotNull(aloysiusBootstrapLoader, "aloysiusBootstrapLoader");
        this.mMediaEventDispatcher = (MediaEventDispatcher) Preconditions.checkNotNull(mediaEventDispatcher, "mediaEventDispatcher");
        this.mMediaEventQueue = (MediaEventQueue) Preconditions.checkNotNull(mediaEventQueue, "eventQueue");
        this.mAloysiusConfig = (AloysiusConfig) Preconditions.checkNotNull(aloysiusConfig, "aloysiusConfig");
        this.mAloysiusRefactorWeblab = (MobileWeblab) Preconditions.checkNotNull(mobileWeblab, "aloysiusRefactorWeblab");
        this.mAloysiusInterfaceReporter = (AloysiusInterfaceReporter) Preconditions.checkNotNull(aloysiusInterfaceReporter, "aloysiusInterfaceReporter");
        this.mAloysiusInteractionReporter = (AloysiusInteractionReporter) Preconditions.checkNotNull(aloysiusInteractionReporter, "aloysiusInteractionReporter");
        this.mAloysiusRemoteReporter = (AloysiusRemoteReporter) Preconditions.checkNotNull(aloysiusRemoteReporter, "aloysiusRemoteReporter");
        this.mAloysiusDownloadReporter = (AloysiusDownloadReporter) Preconditions.checkNotNull(aloysiusDownloadReporter, "aloysiusDownloadReporter");
        if (BetaConfigProvider.getInstance().provideBetaConfig().isBeta()) {
            return;
        }
        this.mAloysiusRefactorWeblab.trigger();
    }

    public void initialize() {
        if (!NetworkConnectionManager.getInstance().getCachedNetworkInfo().hasFullNetworkAccess()) {
            this.mConnectionEventListener = new AloysiusConnectionEventListener(this, this.mInitializationLatch);
            NetworkConnectionManager.getInstance().registerListener((ConnectionChangeListener) this.mConnectionEventListener);
            return;
        }
        if (shouldReturnT1ForAloysiusRefactorWeblab()) {
            this.mInitializationLatch.start(30L, TimeUnit.SECONDS);
            if (this.mAloysiusConfig.isAloysiusEnabled()) {
                this.mAloysiusBootstrapLoader.initialize();
                ArrayList arrayListNewArrayList = Lists.newArrayList();
                arrayListNewArrayList.add(this.mAloysiusInterfaceReporter);
                arrayListNewArrayList.add(this.mAloysiusInteractionReporter);
                arrayListNewArrayList.add(this.mAloysiusRemoteReporter);
                arrayListNewArrayList.add(this.mAloysiusDownloadReporter);
                Iterator it = arrayListNewArrayList.iterator();
                while (it.hasNext()) {
                    ((MediaEventReporter) it.next()).initialize();
                }
            }
            this.mInitializationLatch.complete();
            if (this.mConnectionEventListener != null) {
                NetworkConnectionManager.getInstance().unregisterListener(this.mConnectionEventListener);
                this.mConnectionEventListener = null;
            }
        }
    }

    @Nonnull
    public Optional<AloysiusDownloadReporter> getAloysiusDownloadReporter() {
        if (!shouldReturnT1ForAloysiusRefactorWeblab() || !this.mInitializationLatch.isInitialized()) {
            return Optional.absent();
        }
        return Optional.of(this.mAloysiusDownloadReporter);
    }

    @Nonnull
    private static AloysiusInterfaceReporter createAloysiusInterfaceReporter(@Nonnull MediaEventQueue mediaEventQueue) {
        return new AloysiusInterfaceReporterImpl(mediaEventQueue, true);
    }

    @Nonnull
    private static AloysiusInteractionReporter createAloysiusInteractionReporter(@Nonnull MediaEventQueue mediaEventQueue) {
        return new AloysiusInteractionReporter(mediaEventQueue, (AloysiusPlaybackStateTracker) null);
    }

    @Nonnull
    private static AloysiusRemoteReporter createAloysiusRemoteReporter(@Nonnull MediaEventQueue mediaEventQueue) {
        return new AloysiusRemoteReporter(mediaEventQueue);
    }

    @Nonnull
    private static AloysiusDownloadReporter createAloysiusDownloadReporter(@Nonnull MediaEventQueue mediaEventQueue) {
        return new AloysiusDownloadReporter(mediaEventQueue);
    }

    private static class AloysiusConnectionEventListener extends ConnectionEventListener {
        private InitializationLatch mInitializationLatch;
        private AloysiusInitializer mInitializer;

        @Override // com.amazon.avod.connectivity.ConnectionEventListener
        protected void onDisconnected() {
        }

        AloysiusConnectionEventListener(@Nonnull AloysiusInitializer aloysiusInitializer, @Nonnull InitializationLatch initializationLatch) {
            this.mInitializer = (AloysiusInitializer) Preconditions.checkNotNull(aloysiusInitializer, "initializer");
            this.mInitializationLatch = (InitializationLatch) Preconditions.checkNotNull(initializationLatch, "initializationLatch");
        }

        @Override // com.amazon.avod.connectivity.ConnectionEventListener
        protected void onWifiConnected() {
            initializeIfNecessary();
        }

        @Override // com.amazon.avod.connectivity.ConnectionEventListener
        protected void onWanConnected() {
            initializeIfNecessary();
        }

        private void initializeIfNecessary() {
            if (this.mInitializationLatch.isInitialized()) {
                return;
            }
            this.mInitializer.initialize();
        }
    }

    private boolean shouldReturnT1ForAloysiusRefactorWeblab() {
        return this.mAloysiusRefactorWeblab.getCurrentTreatment().equals(WeblabTreatment.T1) || BetaConfigProvider.getInstance().provideBetaConfig().isBeta();
    }
}
