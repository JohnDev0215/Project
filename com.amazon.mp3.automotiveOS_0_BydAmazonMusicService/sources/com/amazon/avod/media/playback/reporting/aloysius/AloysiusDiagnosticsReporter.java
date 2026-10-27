package com.amazon.avod.media.playback.reporting.aloysius;

import androidx.annotation.VisibleForTesting;
import com.amazon.avod.connectivity.NetworkConnectionManager;
import com.amazon.avod.content.event.ContentManagementEventBus;
import com.amazon.avod.content.urlvending.QoeUpdateEvent;
import com.amazon.avod.event.PlaybackEventTransport;
import com.amazon.avod.media.events.MediaEventQueue;
import com.amazon.avod.media.events.MediaEventReporter;
import com.amazon.avod.media.playback.reporting.aloysius.AloysiusDiagnosticsEvent;
import com.amazon.avod.playback.event.playback.AdChapteringEvent;
import com.amazon.avod.playback.event.playback.AdPlayHeadOutOfBoundsEvent;
import com.amazon.avod.playback.event.playback.AdSkippableEvent;
import com.amazon.avod.playback.event.playback.AdSkippedEvent;
import com.amazon.avod.playback.event.playback.BookmarkTimestampEvent;
import com.amazon.avod.playback.event.playback.BufferStartEvent;
import com.amazon.avod.playback.event.playback.HighFrameRateQualityEvent;
import com.amazon.avod.playback.event.playback.PlaybackStopEvent;
import com.amazon.avod.playback.event.playback.PresentationEvent;
import com.amazon.avod.qos.reporter.AloysiusDiagnosticEvent;
import com.amazon.avod.qos.reporter.AloysiusDiagnosticsState;
import com.amazon.avod.util.DLog;
import com.google.common.base.Preconditions;
import com.google.common.eventbus.Subscribe;
import java.util.Locale;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public class AloysiusDiagnosticsReporter implements MediaEventReporter {
    private final ContentManagementEventBus mEventBus;
    private final PlaybackEventTransport mEventTransport;
    private final MediaEventQueue mMediaEventQueue;
    private final NetworkConnectionManager mNetworkConnectionManager;

    public AloysiusDiagnosticsReporter(@Nonnull MediaEventQueue mediaEventQueue, @Nullable ContentManagementEventBus contentManagementEventBus, @Nullable PlaybackEventTransport playbackEventTransport) {
        this(mediaEventQueue, contentManagementEventBus, playbackEventTransport, NetworkConnectionManager.getInstance());
    }

    @VisibleForTesting
    public AloysiusDiagnosticsReporter(@Nonnull MediaEventQueue mediaEventQueue, @Nullable ContentManagementEventBus contentManagementEventBus, @Nullable PlaybackEventTransport playbackEventTransport, @Nonnull NetworkConnectionManager networkConnectionManager) {
        this.mMediaEventQueue = (MediaEventQueue) Preconditions.checkNotNull(mediaEventQueue, "mediaEventQueue");
        this.mEventBus = contentManagementEventBus;
        this.mEventTransport = playbackEventTransport;
        this.mNetworkConnectionManager = (NetworkConnectionManager) Preconditions.checkNotNull(networkConnectionManager, "connectionManager");
    }

    @Override // com.amazon.avod.media.events.MediaEventReporter
    public void initialize() {
        ContentManagementEventBus contentManagementEventBus = this.mEventBus;
        if (contentManagementEventBus != null) {
            contentManagementEventBus.registerEventBusHandler(this);
        }
        PlaybackEventTransport playbackEventTransport = this.mEventTransport;
        if (playbackEventTransport != null) {
            playbackEventTransport.registerEventBusHandler(this);
        }
    }

    @Override // com.amazon.avod.media.events.MediaEventReporter
    public void terminate() {
        ContentManagementEventBus contentManagementEventBus = this.mEventBus;
        if (contentManagementEventBus != null) {
            contentManagementEventBus.unregisterEventBusHandler(this);
        }
        PlaybackEventTransport playbackEventTransport = this.mEventTransport;
        if (playbackEventTransport != null) {
            playbackEventTransport.unregisterEventBusHandler(this);
        }
    }

    @Subscribe
    public void handleDiagnosticsEvent(AloysiusDiagnosticEvent aloysiusDiagnosticEvent) {
        reportDiagnosticEvent(aloysiusDiagnosticEvent);
    }

    @Subscribe
    public void handlePlaybackStopEvent(@Nonnull PlaybackStopEvent playbackStopEvent) {
        reportDiagnosticEvent(new AloysiusDiagnosticEvent("RendererScheme", String.format(Locale.US, "%s|%s", playbackStopEvent.getRendererScheme().toString(), playbackStopEvent.getDrmScheme()), AloysiusDiagnosticsState.Discrete));
    }

    @Subscribe
    public void handleBufferStartEvent(BufferStartEvent bufferStartEvent) {
        reportDiagnosticEvent(new AloysiusDiagnosticEvent("NetworkConnectionStatus", this.mNetworkConnectionManager.getNetworkInfo().getNetworkState().name(), AloysiusDiagnosticsState.Discrete));
    }

    @Subscribe
    public void handleQoeUpdateEvent(QoeUpdateEvent qoeUpdateEvent) {
        reportDiagnosticEvent(new AloysiusDiagnosticEvent("QoeValue", qoeUpdateEvent.getValueStr(), AloysiusDiagnosticsState.Discrete));
        DLog.logf("QoeEvaluator report: " + qoeUpdateEvent.getValueStr());
    }

    @Subscribe
    public void handleHighFrameRateQualityEvent(HighFrameRateQualityEvent highFrameRateQualityEvent) {
        DLog.devf("HFRQuality report DiagnosticEvent %s", highFrameRateQualityEvent.getDescription());
        reportDiagnosticEvent(new AloysiusDiagnosticEvent("HighFrameRateQuality", highFrameRateQualityEvent.getDescription(), AloysiusDiagnosticsState.Discrete));
    }

    @Subscribe
    public void handleAdChapteringEvent(AdChapteringEvent adChapteringEvent) {
        DLog.devf("AdChaptering report DiagnosticEvent %s", adChapteringEvent.getChapteringNote());
        reportDiagnosticEvent(new AloysiusDiagnosticEvent("AdChapteringOccurred", adChapteringEvent.getChapteringNote(), AloysiusDiagnosticsState.Discrete));
    }

    public void handleAdSkippableEvent(AdSkippableEvent adSkippableEvent) {
        Locale locale = Locale.US;
        throw null;
    }

    public void handleAdSkipEvent(AdSkippedEvent adSkippedEvent) {
        Locale locale = Locale.US;
        throw null;
    }

    public void handleBookmarkTimestampEvent(@Nonnull BookmarkTimestampEvent bookmarkTimestampEvent) {
        Preconditions.checkNotNull(bookmarkTimestampEvent, "BookmarkTimestampEvent");
        throw null;
    }

    public void handleAdPlayHeadOutOfBoundsEvent(AdPlayHeadOutOfBoundsEvent adPlayHeadOutOfBoundsEvent) {
        Locale locale = Locale.US;
        throw null;
    }

    public void handlePresentationEvent(PresentationEvent presentationEvent) {
        String str = String.format(Locale.US, "PresentationEvent DiagnosticEvent: Presentation Type: %s", presentationEvent.getPresentationType().toString());
        DLog.devf(str);
        reportDiagnosticEvent(new AloysiusDiagnosticEvent("PresentationType", str, AloysiusDiagnosticsState.Discrete));
    }

    private void reportDiagnosticEvent(@Nonnull AloysiusDiagnosticEvent aloysiusDiagnosticEvent) {
        AloysiusDiagnosticsEvent.Builder builder = new AloysiusDiagnosticsEvent.Builder();
        builder.diagValue(aloysiusDiagnosticEvent.getValue());
        builder.diagLabel(aloysiusDiagnosticEvent.getLabel());
        builder.diagState(aloysiusDiagnosticEvent.getState());
        this.mMediaEventQueue.add(builder.build());
    }
}
