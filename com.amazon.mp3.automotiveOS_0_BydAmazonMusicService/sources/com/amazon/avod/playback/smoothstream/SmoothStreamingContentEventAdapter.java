package com.amazon.avod.playback.smoothstream;

import androidx.core.app.NotificationCompat;
import com.amazon.avod.content.ContentSessionContext;
import com.amazon.avod.content.config.SmoothStreamingPlaybackConfig;
import com.amazon.avod.content.event.ContentEventSessionStarting;
import com.amazon.avod.content.event.ContentFpsEvent;
import com.amazon.avod.content.event.FatalContentEventError;
import com.amazon.avod.content.event.LiveStreamingRestartEvent;
import com.amazon.avod.content.smoothstream.EventMessage;
import com.amazon.avod.content.urlvending.ClosePlayerEvent;
import com.amazon.avod.event.PlaybackEventTransport;
import com.amazon.avod.media.TimeSpan;
import com.amazon.avod.media.framework.event.EventDispatcher;
import com.amazon.avod.playback.PlaybackRestartEvent;
import com.amazon.avod.playback.event.PlaybackMetadataAvailableEvent;
import com.amazon.avod.playback.event.playback.FatalPlaybackErrorEvent;
import com.amazon.avod.playback.event.playback.PlaybackCompletedEvent;
import com.google.common.base.Preconditions;
import com.google.common.eventbus.Subscribe;
import java.util.Locale;
import javax.annotation.Nonnull;
import javax.annotation.concurrent.NotThreadSafe;

/* JADX INFO: loaded from: classes2.dex */
@NotThreadSafe
public class SmoothStreamingContentEventAdapter {
    private EventDispatcher mContentEventDispatcher;
    private Double mContentFps;
    private ContentSessionContext mContext;
    private final PlaybackEventTransport mEventTransport;
    private boolean mHasPostedMetadataEvent;
    private final ManifestRetryContentEventAdapter mManifestRetryContentEventAdapter;
    private final SmoothStreamingPlaybackConfig mSmoothStreamingPlaybackConfig;

    public SmoothStreamingContentEventAdapter(@Nonnull PlaybackEventTransport playbackEventTransport) {
        this(playbackEventTransport, SmoothStreamingPlaybackConfig.INSTANCE);
    }

    SmoothStreamingContentEventAdapter(@Nonnull PlaybackEventTransport playbackEventTransport, @Nonnull SmoothStreamingPlaybackConfig smoothStreamingPlaybackConfig) {
        this.mEventTransport = (PlaybackEventTransport) Preconditions.checkNotNull(playbackEventTransport, "eventTransport");
        this.mSmoothStreamingPlaybackConfig = (SmoothStreamingPlaybackConfig) Preconditions.checkNotNull(smoothStreamingPlaybackConfig, "playbackConfig");
        this.mManifestRetryContentEventAdapter = new ManifestRetryContentEventAdapter(playbackEventTransport);
    }

    public void initialize(EventDispatcher eventDispatcher) {
        this.mContentEventDispatcher = eventDispatcher;
        eventDispatcher.registerEventBusHandler(this);
        if (this.mSmoothStreamingPlaybackConfig.isPlayerRestartForManifestDownloadFailureAllowed()) {
            this.mContentEventDispatcher.registerEventBusHandler(this.mManifestRetryContentEventAdapter);
        }
        this.mContext = null;
        this.mContentFps = null;
        this.mHasPostedMetadataEvent = false;
    }

    public void shutdown() {
        EventDispatcher eventDispatcher = this.mContentEventDispatcher;
        if (eventDispatcher != null) {
            eventDispatcher.unregisterEventBusHandler(this);
            this.mContentEventDispatcher.unregisterEventBusHandler(this.mManifestRetryContentEventAdapter);
        }
    }

    @Subscribe
    public void handleContentEventSessionStarting(ContentEventSessionStarting contentEventSessionStarting) {
        this.mContext = contentEventSessionStarting.getContext();
        trySendMetadataEvent();
    }

    @Subscribe
    public void handleContentFpsEvent(ContentFpsEvent contentFpsEvent) {
        this.mContentFps = Double.valueOf(contentFpsEvent.getFramesPerSecond());
        trySendMetadataEvent();
    }

    @Subscribe
    public void handleFatalContentDownloadError(FatalContentEventError fatalContentEventError) {
        this.mEventTransport.postEvent(new FatalPlaybackErrorEvent(TimeSpan.now(), fatalContentEventError.getException(), null, fatalContentEventError.getException().getStatusCode(), fatalContentEventError.getConsumptionId()));
    }

    @Subscribe
    public void handleLiveStreamRestartEvent(@Nonnull LiveStreamingRestartEvent liveStreamingRestartEvent) {
        PlaybackRestartEvent playbackRestartEventNewLiveOriginFailoverEvent;
        Preconditions.checkNotNull(liveStreamingRestartEvent, NotificationCompat.CATEGORY_EVENT);
        int type = liveStreamingRestartEvent.getType();
        if (type == 1) {
            playbackRestartEventNewLiveOriginFailoverEvent = PlaybackRestartEvent.newLiveOriginFailoverEvent(liveStreamingRestartEvent.getContentUrlSetId(), liveStreamingRestartEvent.getAudioVideoUrls(), liveStreamingRestartEvent.getRestartTimeMillis(), liveStreamingRestartEvent.shouldOverridePreviousRestart(), liveStreamingRestartEvent.getContentSessionUUID());
        } else if (type == 3) {
            playbackRestartEventNewLiveOriginFailoverEvent = PlaybackRestartEvent.newLiveKeyRotationEvent(liveStreamingRestartEvent.getRestartTimeMillis());
        } else {
            throw new IllegalArgumentException(String.format(Locale.US, "Invalid event type: %d", Integer.valueOf(type)));
        }
        this.mEventTransport.postEvent(playbackRestartEventNewLiveOriginFailoverEvent);
    }

    @Subscribe
    public void handleClosePlayerEvent(@Nonnull ClosePlayerEvent closePlayerEvent) {
        Preconditions.checkNotNull(closePlayerEvent, "closePlayerEvent");
        this.mEventTransport.postEvent(new PlaybackCompletedEvent(TimeSpan.ZERO, true, null));
    }

    @Subscribe
    public void handleFragmentEventMessageEvent(@Nonnull EventMessage eventMessage) {
        this.mEventTransport.postEvent(Preconditions.checkNotNull(eventMessage, NotificationCompat.CATEGORY_EVENT));
    }

    private void trySendMetadataEvent() {
        ContentSessionContext contentSessionContext;
        if (this.mHasPostedMetadataEvent || (contentSessionContext = this.mContext) == null || this.mContentFps == null) {
            return;
        }
        com.amazon.avod.content.smoothstream.manifest.QualityLevel[] sortedQualityLevels = contentSessionContext.getManifest().getVideoStream().getSortedQualityLevels(this.mContext.getState().getConsumptionHead(this.mContext.getManifest().getVideoStream().getIndex()));
        com.amazon.avod.content.smoothstream.manifest.VideoQualityLevel[] videoQualityLevelArr = new com.amazon.avod.content.smoothstream.manifest.VideoQualityLevel[sortedQualityLevels.length];
        for (int i = 0; i < sortedQualityLevels.length; i++) {
            videoQualityLevelArr[i] = (com.amazon.avod.content.smoothstream.manifest.VideoQualityLevel) sortedQualityLevels[i];
        }
        this.mEventTransport.postEvent(new PlaybackMetadataAvailableEvent(this.mContext.getAvailableResolutions(), this.mContentFps.doubleValue(), videoQualityLevelArr));
        this.mHasPostedMetadataEvent = true;
    }
}
