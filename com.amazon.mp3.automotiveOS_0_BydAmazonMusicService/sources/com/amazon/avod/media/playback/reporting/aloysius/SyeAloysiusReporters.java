package com.amazon.avod.media.playback.reporting.aloysius;

import android.content.Context;
import com.amazon.avod.event.PlaybackEventTransport;
import com.amazon.avod.media.events.AloysiusBootstrapLoader;
import com.amazon.avod.media.events.AloysiusConfig;
import com.amazon.avod.media.events.MediaEventDispatcher;
import com.amazon.avod.media.events.MediaEventProducer;
import com.amazon.avod.media.events.MediaEventQueue;
import com.amazon.avod.media.events.MediaEventReporter;
import com.amazon.avod.media.events.PeriodicMediaEventScheduler;
import com.amazon.avod.media.playback.reporting.aloysius.PlaybackMediaEventReporters;
import com.google.common.collect.Lists;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import javax.annotation.Nonnull;
import javax.inject.Provider;

/* JADX INFO: loaded from: classes2.dex */
public class SyeAloysiusReporters extends PlaybackMediaEventReporters {

    public static class Factory extends PlaybackMediaEventReporters.Factory {
        public Factory(@Nonnull MediaEventQueue mediaEventQueue, @Nonnull MediaEventDispatcher mediaEventDispatcher, @Nonnull Provider<PeriodicMediaEventScheduler> provider, @Nonnull Set<MediaEventProducer> set, @Nonnull AloysiusBootstrapLoader aloysiusBootstrapLoader, @Nonnull Context context, @Nonnull AloysiusDeviceReporter aloysiusDeviceReporter) {
            super(mediaEventQueue, mediaEventDispatcher, provider, set, aloysiusBootstrapLoader, context, aloysiusDeviceReporter);
        }

        public PlaybackMediaEventReporters create(@Nonnull PlaybackEventTransport playbackEventTransport, @Nonnull String str) {
            LinkedList linkedListNewLinkedList = Lists.newLinkedList();
            AloysiusInterfaceReporterImpl aloysiusInterfaceReporterImpl = new AloysiusInterfaceReporterImpl(this.mEventQueue, true);
            AloysiusDisplayReporter aloysiusDisplayReporter = new AloysiusDisplayReporter(this.mEventQueue, this.mContext);
            AloysiusTimedTextReporter aloysiusTimedTextReporter = new AloysiusTimedTextReporter(this.mEventQueue);
            SyeAloysiusPlaybackReporter syeAloysiusPlaybackReporter = new SyeAloysiusPlaybackReporter(this.mEventQueue, playbackEventTransport, str);
            AloysiusInteractionReporter aloysiusInteractionReporter = new AloysiusInteractionReporter(this.mEventQueue, (AloysiusPlaybackStateTracker) null);
            AloysiusDiagnosticsReporter aloysiusDiagnosticsReporter = new AloysiusDiagnosticsReporter(this.mEventQueue, null, playbackEventTransport);
            AloysiusAudioTrackReporter aloysiusAudioTrackReporter = new AloysiusAudioTrackReporter(this.mEventQueue, playbackEventTransport);
            SyeAloysiusErrorEventReporter syeAloysiusErrorEventReporter = new SyeAloysiusErrorEventReporter(this.mEventQueue, playbackEventTransport);
            AloysiusAdIdentifierReporterImpl aloysiusAdIdentifierReporterImpl = new AloysiusAdIdentifierReporterImpl(this.mEventQueue);
            AloysiusFailoverReporter aloysiusFailoverReporter = new AloysiusFailoverReporter(this.mEventQueue, null);
            AloysiusPlayerHeuristicReporter aloysiusPlayerHeuristicReporter = new AloysiusPlayerHeuristicReporter(this.mEventQueue);
            AloysiusContentReporter aloysiusContentReporter = new AloysiusContentReporter(this.mEventQueue);
            linkedListNewLinkedList.add(aloysiusDisplayReporter);
            linkedListNewLinkedList.add(aloysiusInterfaceReporterImpl);
            linkedListNewLinkedList.add(aloysiusTimedTextReporter);
            linkedListNewLinkedList.add(syeAloysiusPlaybackReporter);
            linkedListNewLinkedList.add(aloysiusDiagnosticsReporter);
            linkedListNewLinkedList.add(aloysiusAudioTrackReporter);
            linkedListNewLinkedList.add(syeAloysiusErrorEventReporter);
            linkedListNewLinkedList.add(aloysiusAdIdentifierReporterImpl);
            linkedListNewLinkedList.add(aloysiusFailoverReporter);
            linkedListNewLinkedList.add(aloysiusPlayerHeuristicReporter);
            linkedListNewLinkedList.add(aloysiusContentReporter);
            PeriodicMediaEventScheduler periodicMediaEventScheduler = this.mPeriodicSchedulerProvider.get();
            periodicMediaEventScheduler.addMediaEventProducer(syeAloysiusPlaybackReporter);
            return new PlaybackMediaEventReporters(this.mAloysiusConfig, linkedListNewLinkedList, this.mProducers, this.mMediaEventDispatcher, periodicMediaEventScheduler, this.mAloysiusBootstrapLoader, aloysiusInterfaceReporterImpl, aloysiusTimedTextReporter, syeAloysiusPlaybackReporter, aloysiusDiagnosticsReporter, aloysiusInteractionReporter, aloysiusAdIdentifierReporterImpl, aloysiusFailoverReporter, this.mAloysiusDeviceReporter, aloysiusPlayerHeuristicReporter, aloysiusContentReporter);
        }
    }

    public SyeAloysiusReporters(@Nonnull AloysiusConfig aloysiusConfig, @Nonnull List<MediaEventReporter> list, @Nonnull Set<MediaEventProducer> set, @Nonnull MediaEventDispatcher mediaEventDispatcher, @Nonnull PeriodicMediaEventScheduler periodicMediaEventScheduler, @Nonnull AloysiusBootstrapLoader aloysiusBootstrapLoader, @Nonnull AloysiusInterfaceReporter aloysiusInterfaceReporter, @Nonnull AloysiusTimedTextReporter aloysiusTimedTextReporter, @Nonnull AloysiusPlaybackReporter aloysiusPlaybackReporter, @Nonnull AloysiusDiagnosticsReporter aloysiusDiagnosticsReporter, @Nonnull AloysiusInteractionReporter aloysiusInteractionReporter, @Nonnull AloysiusAdIdentifierReporter aloysiusAdIdentifierReporter, @Nonnull AloysiusFailoverReporter aloysiusFailoverReporter, @Nonnull AloysiusDeviceReporter aloysiusDeviceReporter, @Nonnull AloysiusPlayerHeuristicReporter aloysiusPlayerHeuristicReporter, @Nonnull AloysiusContentReporter aloysiusContentReporter) {
        super(aloysiusConfig, list, set, mediaEventDispatcher, periodicMediaEventScheduler, aloysiusBootstrapLoader, aloysiusInterfaceReporter, aloysiusTimedTextReporter, aloysiusPlaybackReporter, aloysiusDiagnosticsReporter, aloysiusInteractionReporter, aloysiusAdIdentifierReporter, aloysiusFailoverReporter, aloysiusDeviceReporter, aloysiusPlayerHeuristicReporter, aloysiusContentReporter);
    }
}
