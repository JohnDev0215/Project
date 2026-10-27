package com.amazon.avod.media.playback.reporting.aloysius;

import android.content.Context;
import com.amazon.avod.content.event.ContentManagementEventBus;
import com.amazon.avod.event.AdEventTransport;
import com.amazon.avod.event.PlaybackEventTransport;
import com.amazon.avod.experiments.MobileWeblab;
import com.amazon.avod.experiments.PlaybackWeblabs;
import com.amazon.avod.experiments.WeblabTreatment;
import com.amazon.avod.media.TimeSpan;
import com.amazon.avod.media.events.AloysiusBootstrapLoader;
import com.amazon.avod.media.events.AloysiusConfig;
import com.amazon.avod.media.events.MediaEventDispatcher;
import com.amazon.avod.media.events.MediaEventProducer;
import com.amazon.avod.media.events.MediaEventQueue;
import com.amazon.avod.media.events.MediaEventReporter;
import com.amazon.avod.media.events.PeriodicMediaEventScheduler;
import com.amazon.avod.media.framework.config.MediaConfigBase;
import com.amazon.avod.media.framework.config.TimeConfigurationValue;
import com.amazon.avod.media.playback.VideoPlayer;
import com.amazon.avod.playback.PlaybackExperienceController;
import com.amazon.avod.playback.session.ConsumptionIdProvider;
import com.amazon.avod.util.BetaConfigProvider;
import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.inject.Provider;

/* JADX INFO: loaded from: classes2.dex */
public class PlaybackMediaEventReporters {
    private final AloysiusAdIdentifierReporter mAloysiusAdIdentifierReporter;
    private final AloysiusBootstrapLoader mAloysiusBootstrapLoader;

    @Nonnull
    private final AloysiusConfig mAloysiusConfig;
    private final AloysiusContentReporter mAloysiusContentReporter;
    private final AloysiusDeviceReporter mAloysiusDeviceReporter;
    private final AloysiusFailoverReporter mAloysiusFailoverReporter;
    private final AloysiusInteractionReporter mAloysiusInteractionReporter;
    private final AloysiusInterfaceReporter mAloysiusInterfaceReporter;
    private final AloysiusPlayerHeuristicReporter mAloysiusPlayerHeuristicReporter;
    private final MobileWeblab mAloysiusRefactorWeblab;
    private final AloysiusTimedTextReporter mAloysiusTimedTextReporter;
    private final AloysiusDiagnosticsReporter mDiagnosticsReporter;
    private final List<MediaEventReporter> mEventReporters;
    private boolean mIsAloysiusEnabled;

    @Nonnull
    private final MediaEventDispatcher mMediaEventDispatcher;
    private final PeriodicMediaEventScheduler mPeriodicMediaEventScheduler;
    private final AloysiusPlaybackReporter mPlaybackReporter;
    private final PlaybackReportersConfig mPlaybackReportersConfig;

    public PlaybackMediaEventReporters(@Nonnull AloysiusConfig aloysiusConfig, @Nonnull List<MediaEventReporter> list, @Nonnull Set<MediaEventProducer> set, @Nonnull MediaEventDispatcher mediaEventDispatcher, @Nonnull PeriodicMediaEventScheduler periodicMediaEventScheduler, @Nonnull AloysiusBootstrapLoader aloysiusBootstrapLoader, @Nonnull AloysiusInterfaceReporter aloysiusInterfaceReporter, @Nonnull AloysiusTimedTextReporter aloysiusTimedTextReporter, @Nonnull AloysiusPlaybackReporter aloysiusPlaybackReporter, @Nonnull AloysiusDiagnosticsReporter aloysiusDiagnosticsReporter, @Nonnull AloysiusInteractionReporter aloysiusInteractionReporter, @Nonnull AloysiusAdIdentifierReporter aloysiusAdIdentifierReporter, @Nonnull AloysiusFailoverReporter aloysiusFailoverReporter, @Nonnull AloysiusDeviceReporter aloysiusDeviceReporter, @Nonnull AloysiusPlayerHeuristicReporter aloysiusPlayerHeuristicReporter, @Nonnull AloysiusContentReporter aloysiusContentReporter) {
        this(aloysiusConfig, PlaybackReportersConfig.getInstance(), list, set, mediaEventDispatcher, periodicMediaEventScheduler, aloysiusBootstrapLoader, aloysiusInterfaceReporter, aloysiusTimedTextReporter, aloysiusPlaybackReporter, aloysiusDiagnosticsReporter, aloysiusInteractionReporter, aloysiusAdIdentifierReporter, aloysiusFailoverReporter, aloysiusDeviceReporter, aloysiusPlayerHeuristicReporter, PlaybackWeblabs.getPlaybackWeblabs().get("AV_ANDROID_ALOYSIUS_287513"), aloysiusContentReporter);
    }

    public PlaybackMediaEventReporters(@Nonnull AloysiusConfig aloysiusConfig, @Nonnull PlaybackReportersConfig playbackReportersConfig, @Nonnull List<MediaEventReporter> list, @Nonnull Set<MediaEventProducer> set, @Nonnull MediaEventDispatcher mediaEventDispatcher, @Nonnull PeriodicMediaEventScheduler periodicMediaEventScheduler, @Nonnull AloysiusBootstrapLoader aloysiusBootstrapLoader, @Nonnull AloysiusInterfaceReporter aloysiusInterfaceReporter, @Nonnull AloysiusTimedTextReporter aloysiusTimedTextReporter, @Nonnull AloysiusPlaybackReporter aloysiusPlaybackReporter, @Nonnull AloysiusDiagnosticsReporter aloysiusDiagnosticsReporter, @Nonnull AloysiusInteractionReporter aloysiusInteractionReporter, @Nonnull AloysiusAdIdentifierReporter aloysiusAdIdentifierReporter, @Nonnull AloysiusFailoverReporter aloysiusFailoverReporter, @Nonnull AloysiusDeviceReporter aloysiusDeviceReporter, @Nonnull AloysiusPlayerHeuristicReporter aloysiusPlayerHeuristicReporter, @Nonnull MobileWeblab mobileWeblab, @Nonnull AloysiusContentReporter aloysiusContentReporter) {
        AloysiusConfig aloysiusConfig2 = (AloysiusConfig) Preconditions.checkNotNull(aloysiusConfig, "aloysiusConfig");
        this.mAloysiusConfig = aloysiusConfig2;
        this.mIsAloysiusEnabled = isAloysiusEnabled(aloysiusConfig2, false);
        this.mPlaybackReportersConfig = (PlaybackReportersConfig) Preconditions.checkNotNull(playbackReportersConfig, "playbackReportersConfig");
        this.mEventReporters = (List) Preconditions.checkNotNull(list, "eventReporters");
        this.mMediaEventDispatcher = (MediaEventDispatcher) Preconditions.checkNotNull(mediaEventDispatcher, "mediaEventDispatcher");
        this.mPeriodicMediaEventScheduler = (PeriodicMediaEventScheduler) Preconditions.checkNotNull(periodicMediaEventScheduler, "periodicMediaEventScheduler");
        this.mAloysiusBootstrapLoader = (AloysiusBootstrapLoader) Preconditions.checkNotNull(aloysiusBootstrapLoader, "aloysiusBootstrapLoader");
        this.mAloysiusInterfaceReporter = (AloysiusInterfaceReporter) Preconditions.checkNotNull(aloysiusInterfaceReporter, "aloysiusInterfaceReporter");
        this.mAloysiusTimedTextReporter = (AloysiusTimedTextReporter) Preconditions.checkNotNull(aloysiusTimedTextReporter, "aloysiusTimedTextReporter");
        this.mPlaybackReporter = (AloysiusPlaybackReporter) Preconditions.checkNotNull(aloysiusPlaybackReporter, "playbackReporter");
        this.mDiagnosticsReporter = (AloysiusDiagnosticsReporter) Preconditions.checkNotNull(aloysiusDiagnosticsReporter, "diagnosticsReporter");
        this.mAloysiusInteractionReporter = (AloysiusInteractionReporter) Preconditions.checkNotNull(aloysiusInteractionReporter, "aloysiusInteractionReporter");
        Preconditions.checkNotNull(set, "mediaEventProducers");
        this.mAloysiusAdIdentifierReporter = (AloysiusAdIdentifierReporter) Preconditions.checkNotNull(aloysiusAdIdentifierReporter, "aloysiusAdIdentifierReporter");
        this.mAloysiusFailoverReporter = (AloysiusFailoverReporter) Preconditions.checkNotNull(aloysiusFailoverReporter, "aloysiusFailoverReporter");
        this.mAloysiusPlayerHeuristicReporter = (AloysiusPlayerHeuristicReporter) Preconditions.checkNotNull(aloysiusPlayerHeuristicReporter, "heuristicReporter");
        this.mAloysiusDeviceReporter = (AloysiusDeviceReporter) Preconditions.checkNotNull(aloysiusDeviceReporter, "aloysiusDeviceReporter");
        this.mAloysiusRefactorWeblab = (MobileWeblab) Preconditions.checkNotNull(mobileWeblab, "aloysiusRefactorWeblab");
        this.mAloysiusContentReporter = (AloysiusContentReporter) Preconditions.checkNotNull(aloysiusContentReporter, "aloysiusContentReporter");
    }

    private boolean isAloysiusEnabled(AloysiusConfig aloysiusConfig, boolean z) {
        return (!aloysiusConfig.isLiveContentOnlyEnabled() || z) && aloysiusConfig.isAloysiusEnabled();
    }

    @Nonnull
    public AloysiusInterfaceReporter getAloysiusInterfaceReporter() {
        return this.mAloysiusInterfaceReporter;
    }

    @Nonnull
    public AloysiusTimedTextReporter getAloysiusTimedTextReporter() {
        return this.mAloysiusTimedTextReporter;
    }

    @Nonnull
    public AloysiusInteractionReporter getAloysiusInteractionReporter() {
        return this.mAloysiusInteractionReporter;
    }

    @Nonnull
    public AloysiusPlaybackReporter getPlaybackReporter() {
        return this.mPlaybackReporter;
    }

    @Nonnull
    public AloysiusContentReporter getContentReporter() {
        return this.mAloysiusContentReporter;
    }

    @Nonnull
    public AloysiusDiagnosticsReporter getDiagnosticsReporter() {
        return this.mDiagnosticsReporter;
    }

    @Nonnull
    public AloysiusAdIdentifierReporter getAloysiusAdIdentifierReporter() {
        return this.mAloysiusAdIdentifierReporter;
    }

    @Nonnull
    public AloysiusFailoverReporter getAloysiusFailoverReporter() {
        return this.mAloysiusFailoverReporter;
    }

    @Nonnull
    public AloysiusDeviceReporter getAloysiusDeviceReporter() {
        return this.mAloysiusDeviceReporter;
    }

    @Nonnull
    public AloysiusPlayerHeuristicReporter getAloysiusPlayerHeuristicReporter() {
        return this.mAloysiusPlayerHeuristicReporter;
    }

    public void initialize(@Nullable VideoPlayer videoPlayer, @Nullable PlaybackExperienceController playbackExperienceController, boolean z) {
        this.mIsAloysiusEnabled = isAloysiusEnabled(this.mAloysiusConfig, z);
        AloysiusPlaybackReporter aloysiusPlaybackReporter = this.mPlaybackReporter;
        if (aloysiusPlaybackReporter != null) {
            aloysiusPlaybackReporter.initializeVideoPlayback(videoPlayer, playbackExperienceController, z);
        }
        if (this.mIsAloysiusEnabled) {
            if (shouldReturnT1ForAloysiusRefactorWeblab()) {
                setCyclicReportingPaused(false);
                Iterator<MediaEventReporter> it = this.mEventReporters.iterator();
                while (it.hasNext()) {
                    it.next().initialize();
                }
                return;
            }
            this.mAloysiusBootstrapLoader.initialize();
            setCyclicReportingPaused(false);
            Iterator<MediaEventReporter> it2 = this.mEventReporters.iterator();
            while (it2.hasNext()) {
                it2.next().initialize();
            }
        }
    }

    public void terminate() {
        if (this.mIsAloysiusEnabled) {
            setCyclicReportingPaused(true);
            Iterator<MediaEventReporter> it = this.mEventReporters.iterator();
            while (it.hasNext()) {
                it.next().terminate();
            }
            this.mMediaEventDispatcher.flush();
        }
    }

    public void setCyclicReportingPaused(boolean z) {
        if (z) {
            this.mPeriodicMediaEventScheduler.stop();
        } else {
            this.mPeriodicMediaEventScheduler.start(this.mPlaybackReportersConfig.getHeartBeatInterval().getTotalMilliseconds());
        }
    }

    private boolean shouldReturnT1ForAloysiusRefactorWeblab() {
        return this.mAloysiusRefactorWeblab.getCurrentTreatment().equals(WeblabTreatment.T1) || BetaConfigProvider.getInstance().provideBetaConfig().isBeta();
    }

    public static class Factory {
        protected final AloysiusBootstrapLoader mAloysiusBootstrapLoader;
        protected final AloysiusConfig mAloysiusConfig;
        protected final AloysiusDeviceReporter mAloysiusDeviceReporter;
        protected final AloysiusPlaybackStateTracker mAloysiusPlaybackStateTracker;
        protected final Context mContext;
        protected final MediaEventQueue mEventQueue;
        protected final MediaEventDispatcher mMediaEventDispatcher;
        protected final Provider<PeriodicMediaEventScheduler> mPeriodicSchedulerProvider;
        protected final Set<MediaEventProducer> mProducers;

        public Factory(@Nonnull MediaEventQueue mediaEventQueue, @Nonnull MediaEventDispatcher mediaEventDispatcher, @Nonnull Provider<PeriodicMediaEventScheduler> provider, @Nonnull Set<MediaEventProducer> set, @Nonnull AloysiusBootstrapLoader aloysiusBootstrapLoader, @Nonnull Context context, @Nonnull AloysiusDeviceReporter aloysiusDeviceReporter) {
            this(AloysiusConfig.getInstance(), mediaEventQueue, mediaEventDispatcher, provider, set, aloysiusBootstrapLoader, context, AloysiusPlaybackStateTracker.getInstance(), aloysiusDeviceReporter);
        }

        Factory(@Nonnull AloysiusConfig aloysiusConfig, @Nonnull MediaEventQueue mediaEventQueue, @Nonnull MediaEventDispatcher mediaEventDispatcher, @Nonnull Provider<PeriodicMediaEventScheduler> provider, @Nonnull Set<MediaEventProducer> set, @Nonnull AloysiusBootstrapLoader aloysiusBootstrapLoader, @Nonnull Context context, @Nonnull AloysiusPlaybackStateTracker aloysiusPlaybackStateTracker, @Nonnull AloysiusDeviceReporter aloysiusDeviceReporter) {
            this.mAloysiusConfig = (AloysiusConfig) Preconditions.checkNotNull(aloysiusConfig, "aloysiusConfig");
            this.mEventQueue = (MediaEventQueue) Preconditions.checkNotNull(mediaEventQueue, "eventQueue");
            this.mMediaEventDispatcher = (MediaEventDispatcher) Preconditions.checkNotNull(mediaEventDispatcher, "mediaEventDispatcher");
            this.mPeriodicSchedulerProvider = (Provider) Preconditions.checkNotNull(provider, "periodicSchedulerProvider");
            this.mProducers = (Set) Preconditions.checkNotNull(set, "producers");
            this.mAloysiusBootstrapLoader = (AloysiusBootstrapLoader) Preconditions.checkNotNull(aloysiusBootstrapLoader, "aloysiusBootstrapLoader");
            this.mContext = (Context) Preconditions.checkNotNull(context, "context");
            this.mAloysiusPlaybackStateTracker = (AloysiusPlaybackStateTracker) Preconditions.checkNotNull(aloysiusPlaybackStateTracker, "aloysiusBootstrapLoader");
            this.mAloysiusDeviceReporter = (AloysiusDeviceReporter) Preconditions.checkNotNull(aloysiusDeviceReporter, "aloysiusDeviceReporter");
        }

        @Nonnull
        public PlaybackMediaEventReporters create(@Nullable ContentManagementEventBus contentManagementEventBus, @Nullable PlaybackEventTransport playbackEventTransport, @Nullable AdEventTransport adEventTransport, @Nonnull ConsumptionIdProvider consumptionIdProvider) {
            return create(contentManagementEventBus, playbackEventTransport, adEventTransport, consumptionIdProvider, true);
        }

        @Nonnull
        public PlaybackMediaEventReporters create(@Nullable ContentManagementEventBus contentManagementEventBus, @Nullable PlaybackEventTransport playbackEventTransport, @Nullable AdEventTransport adEventTransport, @Nonnull ConsumptionIdProvider consumptionIdProvider, boolean z) {
            LinkedList linkedListNewLinkedList = Lists.newLinkedList();
            linkedListNewLinkedList.add(new AloysiusAcquisitionReporter((MediaEventQueue) Preconditions.checkNotNull(this.mEventQueue, "eventQueue"), contentManagementEventBus, adEventTransport, consumptionIdProvider));
            PeriodicMediaEventScheduler periodicMediaEventScheduler = this.mPeriodicSchedulerProvider.get();
            AloysiusPlaybackReporter aloysiusPlaybackReporter = new AloysiusPlaybackReporter(this.mEventQueue, contentManagementEventBus, playbackEventTransport, adEventTransport, consumptionIdProvider, this.mAloysiusPlaybackStateTracker);
            linkedListNewLinkedList.add(aloysiusPlaybackReporter);
            periodicMediaEventScheduler.addMediaEventProducer(aloysiusPlaybackReporter);
            AloysiusInterfaceReporterImpl aloysiusInterfaceReporterImpl = new AloysiusInterfaceReporterImpl(this.mEventQueue, z);
            linkedListNewLinkedList.add(aloysiusInterfaceReporterImpl);
            AloysiusTimedTextReporter aloysiusTimedTextReporter = new AloysiusTimedTextReporter(this.mEventQueue);
            linkedListNewLinkedList.add(aloysiusTimedTextReporter);
            AloysiusInteractionReporter aloysiusInteractionReporter = new AloysiusInteractionReporter(this.mEventQueue, this.mAloysiusPlaybackStateTracker);
            linkedListNewLinkedList.add(aloysiusInteractionReporter);
            AloysiusContentReporter aloysiusContentReporter = new AloysiusContentReporter(this.mEventQueue);
            if (playbackEventTransport != null) {
                linkedListNewLinkedList.add(new AloysiusAudioTrackReporter(this.mEventQueue, playbackEventTransport));
                linkedListNewLinkedList.add(new AloysiusDisplayReporter(this.mEventQueue, this.mContext));
                linkedListNewLinkedList.add(aloysiusContentReporter);
            }
            AloysiusAdIdentifierReporterImpl aloysiusAdIdentifierReporterImpl = new AloysiusAdIdentifierReporterImpl(this.mEventQueue);
            AloysiusFailoverReporter aloysiusFailoverReporter = new AloysiusFailoverReporter(this.mEventQueue, contentManagementEventBus);
            linkedListNewLinkedList.add(aloysiusAdIdentifierReporterImpl);
            linkedListNewLinkedList.add(new AloysiusErrorEventReporter(this.mEventQueue, playbackEventTransport, adEventTransport, contentManagementEventBus, consumptionIdProvider));
            linkedListNewLinkedList.add(aloysiusFailoverReporter);
            AloysiusDiagnosticsReporter aloysiusDiagnosticsReporter = new AloysiusDiagnosticsReporter(this.mEventQueue, contentManagementEventBus, playbackEventTransport);
            linkedListNewLinkedList.add(aloysiusDiagnosticsReporter);
            AloysiusPlayerHeuristicReporter aloysiusPlayerHeuristicReporter = new AloysiusPlayerHeuristicReporter(this.mEventQueue);
            linkedListNewLinkedList.add(aloysiusPlayerHeuristicReporter);
            return new PlaybackMediaEventReporters(this.mAloysiusConfig, linkedListNewLinkedList, this.mProducers, this.mMediaEventDispatcher, periodicMediaEventScheduler, this.mAloysiusBootstrapLoader, aloysiusInterfaceReporterImpl, aloysiusTimedTextReporter, aloysiusPlaybackReporter, aloysiusDiagnosticsReporter, aloysiusInteractionReporter, aloysiusAdIdentifierReporterImpl, aloysiusFailoverReporter, this.mAloysiusDeviceReporter, aloysiusPlayerHeuristicReporter, aloysiusContentReporter);
        }

        @Nonnull
        public AloysiusErrorEventReporter createErrorEventReporter() {
            return new AloysiusErrorEventReporter(this.mEventQueue, null, null, null, null);
        }

        @Nonnull
        public AloysiusInteractionReporter createInteractionReporter() {
            return new AloysiusInteractionReporter(this.mEventQueue, this.mAloysiusPlaybackStateTracker);
        }

        @Nonnull
        public AloysiusDisplayReporter createAloysiusDisplayReporter() {
            return new AloysiusDisplayReporter(this.mEventQueue, this.mContext);
        }

        @Nonnull
        public AloysiusDiagnosticsReporter createAloysiusDiagnosticsReporter() {
            return new AloysiusDiagnosticsReporter(this.mEventQueue, null, null);
        }
    }

    static class PlaybackReportersConfig extends MediaConfigBase {
        private final TimeConfigurationValue mHeartBeatInterval;

        private static class InstanceHolder {
            private static final PlaybackReportersConfig mConfig = new PlaybackReportersConfig();

            private InstanceHolder() {
            }
        }

        public static PlaybackReportersConfig getInstance() {
            return InstanceHolder.mConfig;
        }

        private PlaybackReportersConfig() {
            this.mHeartBeatInterval = newTimeConfigurationValue("playback.aloysius.heartbeat.interval", TimeSpan.fromSeconds(10.0d), TimeUnit.SECONDS);
        }

        public TimeSpan getHeartBeatInterval() {
            return this.mHeartBeatInterval.getValue();
        }
    }
}
