package com.amazon.music.media.playback.metrics.mts;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amazon.music.media.playback.ChangeReason;
import com.amazon.music.media.playback.ControlSource;
import com.amazon.music.media.playback.MediaCollectionId;
import com.amazon.music.media.playback.MediaCollectionInfo;
import com.amazon.music.media.playback.MediaCollectionType;
import com.amazon.music.media.playback.MediaItem;
import com.amazon.music.media.playback.MediaItemId;
import com.amazon.music.media.playback.MediaLink;
import com.amazon.music.media.playback.PlaybackController;
import com.amazon.music.media.playback.PlaybackException;
import com.amazon.music.media.playback.config.BitRateSelection;
import com.amazon.music.media.playback.config.NetworkProvider;
import com.amazon.music.media.playback.config.PlaybackConfig;
import com.amazon.music.media.playback.metrics.MetricsInfo;
import com.amazon.music.media.playback.metrics.MetricsProperty;
import com.amazon.music.media.playback.metrics.PlaybackEvent;
import com.amazon.music.media.playback.util.ObjectUtils;
import com.amazon.music.media.playback.util.StrictMode;
import com.amazon.music.media.playback.util.StringUtils;
import com.amazon.music.metrics.mts.MTSEvent;
import com.amazon.music.metrics.mts.event.definition.playback.AdStreamedEvent;
import com.amazon.music.metrics.mts.event.definition.playback.AdStreamingInitiatedEvent;
import com.amazon.music.metrics.mts.event.definition.playback.AdStreamingRequiredRebufferingEvent;
import com.amazon.music.metrics.mts.event.definition.playback.AdStreamingTerminatedEvent;
import com.amazon.music.metrics.mts.event.definition.playback.AudioVideoToggleMetricsInfo;
import com.amazon.music.metrics.mts.event.definition.playback.GenericPlaybackCompletedEvent;
import com.amazon.music.metrics.mts.event.definition.playback.GenericPlaybackInitiatedEvent;
import com.amazon.music.metrics.mts.event.definition.playback.GenericPlaybackResumedEvent;
import com.amazon.music.metrics.mts.event.definition.playback.GenericPlaybackStateEvent;
import com.amazon.music.metrics.mts.event.definition.playback.GenericPlaybackTerminatedEvent;
import com.amazon.music.metrics.mts.event.definition.playback.GhostListeningInterruptedEvent;
import com.amazon.music.metrics.mts.event.definition.playback.LocalPlaybackTerminatedEvent;
import com.amazon.music.metrics.mts.event.definition.playback.SportStreamingInitiatedEvent;
import com.amazon.music.metrics.mts.event.definition.playback.SportStreamingInitiationFailedEvent;
import com.amazon.music.metrics.mts.event.definition.playback.SportStreamingRequiredRebufferingEvent;
import com.amazon.music.metrics.mts.event.definition.playback.SportStreamingTerminatedEvent;
import com.amazon.music.metrics.mts.event.definition.playback.StreamingInitiatedEvent;
import com.amazon.music.metrics.mts.event.definition.playback.StreamingRequiredRebufferingEvent;
import com.amazon.music.metrics.mts.event.definition.playback.StreamingTerminatedEvent;
import com.amazon.music.metrics.mts.event.definition.playback.TrackPlaybackInfo;
import com.amazon.music.metrics.mts.event.definition.playback.TrackPlayedLocallyEvent;
import com.amazon.music.metrics.mts.event.definition.playback.TrackStreamedEvent;
import com.amazon.music.metrics.mts.event.definition.subDevice.SubDeviceConnectedEvent;
import com.amazon.music.metrics.mts.event.definition.subDevice.SubDeviceDisconnectedEvent;
import com.amazon.music.metrics.mts.event.exception.CirrusInvalidDataException;
import com.amazon.music.metrics.mts.event.types.BitRate;
import com.amazon.music.metrics.mts.event.types.CacheHitStatus;
import com.amazon.music.metrics.mts.event.types.DirectedPlayStatus;
import com.amazon.music.metrics.mts.event.types.EntityIdType;
import com.amazon.music.metrics.mts.event.types.EntityType;
import com.amazon.music.metrics.mts.event.types.InitiationReason;
import com.amazon.music.metrics.mts.event.types.MediaPlayerType;
import com.amazon.music.metrics.mts.event.types.MediaType;
import com.amazon.music.metrics.mts.event.types.PlaybackEntityType;
import com.amazon.music.metrics.mts.event.types.PlaybackInitiationInfo;
import com.amazon.music.metrics.mts.event.types.PlaybackPageType;
import com.amazon.music.metrics.mts.event.types.PlaybackSourceType;
import com.amazon.music.metrics.mts.event.types.ResourceType;
import com.amazon.music.metrics.mts.event.types.ResumeReason;
import com.amazon.music.metrics.mts.event.types.SelectionSourceInfo;
import com.amazon.music.metrics.mts.event.types.SelectionSourceType;
import com.amazon.music.metrics.mts.event.types.TerminationReason;
import com.amazon.music.platform.data.subscription.FreeSubscriptionTracking;
import com.amazon.music.platform.data.subscription.NoSubscriptionTracking;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import org.apache.commons.lang3.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: loaded from: classes4.dex */
public class DefaultMTSEventFactory implements MTSEventFactory {
    private static final Logger logger = LoggerFactory.getLogger(DefaultMTSEventFactory.class.getSimpleName());
    private final PlaybackConfig config;
    private NetworkProvider networkProvider = NetworkProvider.UNKNOWN;

    protected long getInitialPlaybackDelayMillis(MetricsInfo metricsInfo, MediaItem mediaItem, long j, long j2) {
        return j;
    }

    public DefaultMTSEventFactory(PlaybackConfig playbackConfig) {
        this.config = (PlaybackConfig) Validate.notNull(playbackConfig);
    }

    public NetworkProvider getNetworkProvider() {
        return this.networkProvider;
    }

    public void setNetworkProvider(@NonNull NetworkProvider networkProvider) {
        this.networkProvider = (NetworkProvider) Validate.notNull(networkProvider);
    }

    public PlaybackController getPlaybackController() {
        return this.config.getPlayback().getPlaybackController(ControlSource.NONE);
    }

    @Override // com.amazon.music.media.playback.metrics.mts.MTSEventFactory
    public MTSEvent createStreamingInitiatedEvent(MetricsInfo metricsInfo, @Nullable MediaLink mediaLink, long j, long j2, @Nullable ChangeReason changeReason) {
        String str;
        try {
            MediaItem mediaItem = (MediaItem) metricsInfo.get(PlaybackEvent.PROPERTY_MEDIA_ITEM);
            MediaItemId mediaItemId = mediaItem.getMediaItemId(MediaItemId.Type.PID);
            updateDirectedPlay(mediaItem, metricsInfo);
            updateIsCustomerSelectedEntity(mediaItem, metricsInfo);
            long initialPlaybackDelayMillis = getInitialPlaybackDelayMillis(metricsInfo, mediaItem, j, j2);
            if (ObjectUtils.isAdvertisement(mediaItem)) {
                return createAdStreamingInitiatedEvent(mediaItem, metricsInfo, mediaLink, initialPlaybackDelayMillis, changeReason);
            }
            if (mediaItem.getType().isSports()) {
                return createSportStreamingInitiatedEvent(mediaItem, metricsInfo, mediaLink, initialPlaybackDelayMillis, changeReason);
            }
            if (mediaItemId == null && !ObjectUtils.isInterlude(mediaItem)) {
                if (((Boolean) metricsInfo.get(PlaybackEvent.PROPERTY_LOCAL)).booleanValue()) {
                    return null;
                }
                return createNonSportStreamingInitiatedEvent(mediaItem, metricsInfo, mediaLink, initialPlaybackDelayMillis, changeReason);
            }
            ResourceType resourceType = ResourceType.UNLIMITED_MUSIC;
            PlaybackSourceType playbackSourceType = PlaybackSourceType.STREAM;
            PlaybackEntityType playbackEntityType = PlaybackEntityType.VIDEO;
            mediaItem.getToggleMetricsInfo();
            return createGenericPlaybackInitiatedEvent(mediaItem, initialPlaybackDelayMillis, resourceType, playbackSourceType, playbackEntityType, null);
        } catch (Exception e) {
            if (StringUtils.shouldRedact()) {
                str = "Exception in createStreamingInitiatedEvent()";
            } else {
                str = "Exception in createStreamingInitiatedEvent(" + metricsInfo + ")";
            }
            logger.error(str, (Throwable) e);
            return null;
        }
    }

    @Override // com.amazon.music.media.playback.metrics.mts.MTSEventFactory
    public MTSEvent createStreamingInitiationFailedEvent(MetricsInfo metricsInfo, @Nullable MediaLink mediaLink, ChangeReason changeReason, PlaybackException playbackException) {
        String str;
        try {
            MediaItem mediaItem = (MediaItem) metricsInfo.get(PlaybackEvent.PROPERTY_MEDIA_ITEM);
            updateDirectedPlay(mediaItem, metricsInfo);
            if (mediaItem.getType().isSports()) {
                return createSportStreamingInitiationFailedEvent(mediaItem, metricsInfo, mediaLink, changeReason, playbackException);
            }
            return null;
        } catch (Exception e) {
            if (StringUtils.shouldRedact()) {
                str = "Exception in createStreamingInitiatedEvent()";
            } else {
                str = "Exception in createStreamingInitiatedEvent(" + metricsInfo + ")";
            }
            logger.error(str, (Throwable) e);
            return null;
        }
    }

    @Override // com.amazon.music.media.playback.metrics.mts.MTSEventFactory
    public MTSEvent createStreamingTerminatedEvent(MetricsInfo metricsInfo, @Nullable MediaLink mediaLink, long j, long j2, long j3, int i, long j4, float f, ChangeReason changeReason, @Nullable PlaybackException playbackException, long j5) {
        return createStreamingTerminatedEvent(metricsInfo, mediaLink, j, j2, j3, i, j4, f, getTerminationReason(changeReason), playbackException, j5);
    }

    @Override // com.amazon.music.media.playback.metrics.mts.MTSEventFactory
    public MTSEvent createStreamingTerminatedEvent(MetricsInfo metricsInfo, @Nullable MediaLink mediaLink, long j, long j2, long j3, int i, long j4, float f, TerminationReason terminationReason, @Nullable PlaybackException playbackException, long j5) {
        String str;
        try {
            MediaItem mediaItem = (MediaItem) metricsInfo.get(PlaybackEvent.PROPERTY_MEDIA_ITEM);
            MediaItemId mediaItemId = mediaItem.getMediaItemId(MediaItemId.Type.PID);
            updateDirectedPlay(mediaItem, metricsInfo);
            updateIsCustomerSelectedEntity(mediaItem, metricsInfo);
            long initialPlaybackDelayMillis = getInitialPlaybackDelayMillis(metricsInfo, mediaItem, j, j2);
            if (ObjectUtils.isAdvertisement(mediaItem)) {
                return createAdStreamingTerminatedEvent(mediaItem, metricsInfo, mediaLink, initialPlaybackDelayMillis, j3, i, j4, f, terminationReason);
            }
            if (mediaItem.getType().isSports()) {
                return createSportStreamingTerminatedEvent(mediaItem, metricsInfo, mediaLink, initialPlaybackDelayMillis, j3, i, j4, f, terminationReason);
            }
            if (((Boolean) metricsInfo.get(PlaybackEvent.PROPERTY_LOCAL)).booleanValue()) {
                return createLocalPlaybackTerminatedEvent(mediaItem, metricsInfo, mediaLink, j, j3, terminationReason);
            }
            if (mediaItemId == null && !ObjectUtils.isInterlude(mediaItem)) {
                return createNonSportStreamingTerminatedEvent(mediaItem, metricsInfo, mediaLink, initialPlaybackDelayMillis, j3, i, j4, f, terminationReason, j5);
            }
            ResourceType resourceType = ResourceType.UNLIMITED_MUSIC;
            PlaybackSourceType playbackSourceType = PlaybackSourceType.STREAM;
            PlaybackEntityType playbackEntityType = PlaybackEntityType.VIDEO;
            mediaItem.getToggleMetricsInfo();
            return createGenericPlaybackTerminatedEvent(mediaItem, playbackException, j3, terminationReason, resourceType, playbackSourceType, playbackEntityType, null, metricsInfo, j5);
        } catch (Exception e) {
            if (StringUtils.shouldRedact()) {
                str = "Exception in createStreamingTerminatedEvent(terminationReason:" + terminationReason + ")";
            } else {
                str = "Exception in createStreamingTerminatedEvent(" + metricsInfo + ", mediaLink: " + mediaLink + ", terminationReason:" + terminationReason + ")";
            }
            logger.error(str, (Throwable) e);
            return null;
        }
    }

    @Override // com.amazon.music.media.playback.metrics.mts.MTSEventFactory
    public MTSEvent createTrackStreamedEvent(MetricsInfo metricsInfo, long j, ChangeReason changeReason) {
        return createTrackStreamedEvent(metricsInfo, j, getTerminationReason(changeReason));
    }

    @Override // com.amazon.music.media.playback.metrics.mts.MTSEventFactory
    public MTSEvent createTrackStreamedEvent(MetricsInfo metricsInfo, long j, TerminationReason terminationReason) {
        String str;
        try {
            MediaItem mediaItem = (MediaItem) metricsInfo.get(PlaybackEvent.PROPERTY_MEDIA_ITEM);
            MediaItemId mediaItemId = mediaItem.getMediaItemId(MediaItemId.Type.PID);
            updateDirectedPlay(mediaItem, metricsInfo);
            updateIsCustomerSelectedEntity(mediaItem, metricsInfo);
            if (ObjectUtils.isAdvertisement(mediaItem)) {
                return createAdStreamedEvent(mediaItem, metricsInfo, j, terminationReason);
            }
            if (mediaItem.getType().isSports()) {
                return null;
            }
            if (((Boolean) metricsInfo.get(PlaybackEvent.PROPERTY_LOCAL)).booleanValue()) {
                return createTrackPlayedLocallyEvent(mediaItem, metricsInfo, j, terminationReason);
            }
            if (mediaItemId != null) {
                ResourceType resourceType = ResourceType.UNLIMITED_MUSIC;
                PlaybackSourceType playbackSourceType = PlaybackSourceType.STREAM;
                PlaybackEntityType playbackEntityType = PlaybackEntityType.VIDEO;
                mediaItem.getToggleMetricsInfo();
                return createGenericPlaybackCompletedEvent(mediaItem, j, resourceType, playbackSourceType, playbackEntityType, null);
            }
            return createNonSportTrackStreamedEvent(mediaItem, metricsInfo, j, terminationReason);
        } catch (Exception e) {
            if (StringUtils.shouldRedact()) {
                str = "Exception in createTrackStreamedEvent(maxPosition: " + j + ", terminationReason: " + terminationReason + ")";
            } else {
                str = "Exception in createTrackStreamedEvent(" + metricsInfo + ", maxPosition: " + j + ", terminationReason: " + terminationReason + ")";
            }
            logger.error(str, (Throwable) e);
            return null;
        }
    }

    @Override // com.amazon.music.media.playback.metrics.mts.MTSEventFactory
    public MTSEvent createStreamingRequiredRebufferingEvent(MetricsInfo metricsInfo, MediaLink mediaLink, long j, long j2, long j3, float f) {
        String str;
        try {
            MediaItem mediaItem = (MediaItem) metricsInfo.get(PlaybackEvent.PROPERTY_MEDIA_ITEM);
            updateDirectedPlay(mediaItem, metricsInfo);
            if (ObjectUtils.isAdvertisement(mediaItem)) {
                return createAdStreamingRequiredRebufferingEvent(mediaItem, metricsInfo, mediaLink, j, j2, j3, f);
            }
            if (mediaItem.getType().isSports()) {
                return createSportStreamingRequiredRebufferingEvent(mediaItem, metricsInfo, mediaLink, j, j2, j3, f);
            }
            if (((Boolean) metricsInfo.get(PlaybackEvent.PROPERTY_LOCAL)).booleanValue()) {
                return null;
            }
            return createNonSportStreamingRequiredRebufferingEvent(mediaItem, metricsInfo, mediaLink, j, j2, j3, f);
        } catch (Exception e) {
            if (StringUtils.shouldRedact()) {
                str = "Exception in createStreamingRequiredRebufferingEvent(duration: " + j + ", position: " + j2 + ")";
            } else {
                str = "Exception in createStreamingRequiredRebufferingEvent(" + metricsInfo + ", duration: " + j + ", position: " + j2 + ")";
            }
            logger.error(str, (Throwable) e);
            return null;
        }
    }

    @Override // com.amazon.music.media.playback.metrics.mts.MTSEventFactory
    public MTSEvent createGhostListeningInterruptedEvent(MetricsInfo metricsInfo, long j, long j2) {
        String str;
        try {
            MediaItem mediaItem = (MediaItem) metricsInfo.get(PlaybackEvent.PROPERTY_MEDIA_ITEM);
            GhostListeningInterruptedEvent ghostListeningInterruptedEvent = new GhostListeningInterruptedEvent(j, j2, getSelectionSourceInfo(mediaItem));
            ghostListeningInterruptedEvent.setMetricsContext(mediaItem.getMetricsContext());
            return ghostListeningInterruptedEvent;
        } catch (Exception e) {
            if (StringUtils.shouldRedact()) {
                str = "Exception in createGhostListeningInterruptedEvent()";
            } else {
                str = "Exception in createGhostListeningInterruptedEvent(" + metricsInfo + ")";
            }
            logger.error(str, (Throwable) e);
            return null;
        }
    }

    @Override // com.amazon.music.media.playback.metrics.mts.MTSEventFactory
    public MTSEvent createStreamingResumedEvent(MetricsInfo metricsInfo, @Nullable MediaLink mediaLink, long j, long j2, long j3, @Nullable ChangeReason changeReason) {
        String str;
        try {
            MediaItem mediaItem = (MediaItem) metricsInfo.get(PlaybackEvent.PROPERTY_MEDIA_ITEM);
            MediaItemId mediaItemId = mediaItem.getMediaItemId(MediaItemId.Type.PID);
            updateDirectedPlay(mediaItem, metricsInfo);
            long initialPlaybackDelayMillis = getInitialPlaybackDelayMillis(metricsInfo, mediaItem, j, j2);
            if (mediaItemId == null) {
                return null;
            }
            PlaybackSourceType playbackSourceType = PlaybackSourceType.STREAM;
            PlaybackEntityType playbackEntityType = PlaybackEntityType.VIDEO;
            mediaItem.getToggleMetricsInfo();
            return createGenericPlaybackResumedEvent(mediaItem, initialPlaybackDelayMillis, j3, changeReason, playbackSourceType, playbackEntityType, null);
        } catch (Exception e) {
            if (StringUtils.shouldRedact()) {
                str = "Exception in createStreamingResumedEvent()";
            } else {
                str = "Exception in createStreamingResumedEvent(" + metricsInfo + ")";
            }
            logger.error(str, (Throwable) e);
            return null;
        }
    }

    @Override // com.amazon.music.media.playback.metrics.mts.MTSEventFactory
    public MTSEvent createPlaybackStateEvent(MetricsInfo metricsInfo, @Nullable MediaLink mediaLink, long j, boolean z) {
        String str;
        try {
            MediaItem mediaItem = (MediaItem) metricsInfo.get(PlaybackEvent.PROPERTY_MEDIA_ITEM);
            MediaItemId mediaItemId = mediaItem.getMediaItemId(MediaItemId.Type.PID);
            updateDirectedPlay(mediaItem, metricsInfo);
            if (mediaItemId == null) {
                return null;
            }
            PlaybackSourceType playbackSourceType = PlaybackSourceType.STREAM;
            PlaybackEntityType playbackEntityType = PlaybackEntityType.VIDEO;
            mediaItem.getToggleMetricsInfo();
            return createGenericPlaybackStateEvent(mediaItem, j, z, playbackSourceType, playbackEntityType, null);
        } catch (Exception e) {
            if (StringUtils.shouldRedact()) {
                str = "Exception in createPlaybackStateEvent()";
            } else {
                str = "Exception in createPlaybackStateEvent(" + metricsInfo + ")";
            }
            logger.error(str, (Throwable) e);
            return null;
        }
    }

    protected MTSEvent createNonSportStreamingInitiatedEvent(MediaItem mediaItem, MetricsInfo metricsInfo, @Nullable MediaLink mediaLink, long j, @Nullable ChangeReason changeReason) throws CirrusInvalidDataException {
        StreamingInitiatedEvent streamingInitiatedEvent = new StreamingInitiatedEvent(getTrackPlaybackInfo(mediaItem), getSelectionSourceInfo(mediaItem), getNetworkProvider().getMetricsName(), isExplicitFilterOn(), ObjectUtils.getIsOnDemandQueue(mediaItem), isShuffled());
        streamingInitiatedEvent.setMetricsContext(mediaItem.getMetricsContext());
        updateEventWithPlaybackInstanceId(streamingInitiatedEvent);
        updateEventWithToggleMetricsInfo(streamingInitiatedEvent, mediaItem);
        updateEventWithCustomerSelectedEntity(streamingInitiatedEvent, metricsInfo);
        return streamingInitiatedEvent;
    }

    protected MTSEvent createNonSportStreamingTerminatedEvent(MediaItem mediaItem, MetricsInfo metricsInfo, @Nullable MediaLink mediaLink, long j, long j2, int i, long j3, float f, TerminationReason terminationReason, long j4) throws CirrusInvalidDataException {
        StreamingTerminatedEvent streamingTerminatedEvent = new StreamingTerminatedEvent(getTrackPlaybackInfo(mediaItem), terminationReason, getSelectionSourceInfo(mediaItem), getMediaPlayerType(mediaLink), j2, mediaItem.getDurationMillis(), j, getPlaybackInitiationInfo(metricsInfo), i, getResourceType(mediaItem), j3, getCacheHitStatus(mediaLink, f), getNetworkProvider().getMetricsName(), getBitRate(mediaLink), isExplicitFilterOn(), ObjectUtils.getIsOnDemandQueue(mediaItem));
        streamingTerminatedEvent.setMetricsContext(mediaItem.getMetricsContext());
        updateEventWithPlaybackInstanceId(streamingTerminatedEvent);
        updateEventWithToggleMetricsInfo(streamingTerminatedEvent, mediaItem);
        updateEventWithTrackProgress(streamingTerminatedEvent, j4);
        updateEventWithCustomerSelectedEntity(streamingTerminatedEvent, metricsInfo);
        return streamingTerminatedEvent;
    }

    protected MTSEvent createAdStreamingTerminatedEvent(MediaItem mediaItem, MetricsInfo metricsInfo, @Nullable MediaLink mediaLink, long j, long j2, int i, long j3, float f, TerminationReason terminationReason) throws CirrusInvalidDataException {
        TerminationReason terminationReason2;
        String str;
        try {
            terminationReason2 = terminationReason;
            try {
                AdStreamingTerminatedEvent adStreamingTerminatedEvent = new AdStreamingTerminatedEvent(getTrackPlaybackInfo(mediaItem), terminationReason, getSelectionSourceInfo(mediaItem), getMediaPlayerType(mediaLink), j2, mediaItem.getDurationMillis(), j, getPlaybackInitiationInfo(metricsInfo), i, getResourceType(mediaItem), j3, getCacheHitStatus(mediaLink, f), getNetworkProvider().getMetricsName(), getBitRate(mediaLink), isExplicitFilterOn());
                adStreamingTerminatedEvent.setMetricsContext(mediaItem.getMetricsContext());
                updateEventWithPlaybackInstanceId(adStreamingTerminatedEvent);
                return adStreamingTerminatedEvent;
            } catch (Exception e) {
                e = e;
                if (StringUtils.shouldRedact()) {
                    str = "Exception in createStreamingTerminatedEvent(terminationReason:" + terminationReason2 + ")";
                } else {
                    str = "Exception in createStreamingTerminatedEvent(" + metricsInfo + ", mediaLink: " + mediaLink + ", terminationReason:" + terminationReason2 + ")";
                }
                logger.error(str, (Throwable) e);
                return null;
            }
        } catch (Exception e2) {
            e = e2;
            terminationReason2 = terminationReason;
        }
    }

    protected MTSEvent createLocalPlaybackTerminatedEvent(MediaItem mediaItem, MetricsInfo metricsInfo, @Nullable MediaLink mediaLink, long j, long j2, TerminationReason terminationReason) throws CirrusInvalidDataException {
        LocalPlaybackTerminatedEvent localPlaybackTerminatedEvent = new LocalPlaybackTerminatedEvent(getTrackPlaybackInfo(mediaItem), terminationReason, getSelectionSourceInfo(mediaItem), getMediaPlayerType(mediaLink), j2, mediaItem.getDurationMillis(), j, getPlaybackInitiationInfo(metricsInfo), isExplicitFilterOn(), ObjectUtils.getIsOnDemandQueue(mediaItem));
        localPlaybackTerminatedEvent.setMetricsContext(mediaItem.getMetricsContext());
        updateEventWithPlaybackInstanceId(localPlaybackTerminatedEvent);
        updateEventWithCustomerSelectedEntity(localPlaybackTerminatedEvent, metricsInfo);
        return localPlaybackTerminatedEvent;
    }

    protected MTSEvent createNonSportTrackStreamedEvent(MediaItem mediaItem, MetricsInfo metricsInfo, long j, TerminationReason terminationReason) throws CirrusInvalidDataException {
        TrackStreamedEvent trackStreamedEvent = new TrackStreamedEvent(getTrackPlaybackInfo(mediaItem), j, getSelectionSourceInfo(mediaItem), isExplicitFilterOn(), terminationReason, ObjectUtils.getIsOnDemandQueue(mediaItem), isShuffled());
        trackStreamedEvent.setMetricsContext(mediaItem.getMetricsContext());
        updateEventWithCustomerSelectedEntity(trackStreamedEvent, metricsInfo);
        unsetCustomerSelectedEntityInMetricsInfo(metricsInfo);
        return trackStreamedEvent;
    }

    protected MTSEvent createAdStreamedEvent(MediaItem mediaItem, MetricsInfo metricsInfo, long j, TerminationReason terminationReason) throws CirrusInvalidDataException {
        String str;
        try {
            AdStreamedEvent adStreamedEvent = new AdStreamedEvent(getTrackPlaybackInfo(mediaItem), j, getSelectionSourceInfo(mediaItem), isExplicitFilterOn());
            adStreamedEvent.setMetricsContext(mediaItem.getMetricsContext());
            updateEventWithPlaybackInstanceId(adStreamedEvent);
            return adStreamedEvent;
        } catch (Exception e) {
            if (StringUtils.shouldRedact()) {
                str = "Exception in createTrackStreamedEvent(maxPosition: " + j + ", terminationReason: " + terminationReason + ")";
            } else {
                str = "Exception in createTrackStreamedEvent(" + metricsInfo + ", maxPosition: " + j + ", terminationReason: " + terminationReason + ")";
            }
            logger.error(str, (Throwable) e);
            return null;
        }
    }

    protected MTSEvent createTrackPlayedLocallyEvent(MediaItem mediaItem, MetricsInfo metricsInfo, long j, TerminationReason terminationReason) throws CirrusInvalidDataException {
        TrackPlayedLocallyEvent trackPlayedLocallyEvent = new TrackPlayedLocallyEvent(getTrackPlaybackInfo(mediaItem), j, getSelectionSourceInfo(mediaItem), terminationReason, ObjectUtils.getIsOnDemandQueue(mediaItem), isShuffled());
        trackPlayedLocallyEvent.setMetricsContext(mediaItem.getMetricsContext());
        updateEventWithCustomerSelectedEntity(trackPlayedLocallyEvent, metricsInfo);
        unsetCustomerSelectedEntityInMetricsInfo(metricsInfo);
        return trackPlayedLocallyEvent;
    }

    protected MTSEvent createNonSportStreamingRequiredRebufferingEvent(MediaItem mediaItem, MetricsInfo metricsInfo, MediaLink mediaLink, long j, long j2, long j3, float f) throws CirrusInvalidDataException {
        StreamingRequiredRebufferingEvent streamingRequiredRebufferingEvent = new StreamingRequiredRebufferingEvent(getTrackPlaybackInfo(mediaItem), getMediaPlayerType(mediaLink), getNetworkProvider().getMetricsName(), j2, j, getPercentage(j2, mediaItem.getDurationMillis()), 0, 0, getCacheHitStatus(mediaLink, f), getBitRate(mediaLink), getSelectionSourceInfo(mediaItem), j3);
        streamingRequiredRebufferingEvent.setMetricsContext(mediaItem.getMetricsContext());
        updateEventWithPlaybackInstanceId(streamingRequiredRebufferingEvent);
        return streamingRequiredRebufferingEvent;
    }

    protected MTSEvent createAdStreamingRequiredRebufferingEvent(MediaItem mediaItem, MetricsInfo metricsInfo, MediaLink mediaLink, long j, long j2, long j3, float f) throws CirrusInvalidDataException {
        String str;
        try {
            try {
                AdStreamingRequiredRebufferingEvent adStreamingRequiredRebufferingEvent = new AdStreamingRequiredRebufferingEvent(getTrackPlaybackInfo(mediaItem), getMediaPlayerType(mediaLink), getNetworkProvider().getMetricsName(), j2, j, getPercentage(j2, mediaItem.getDurationMillis()), 0, 0, getCacheHitStatus(mediaLink, f), getBitRate(mediaLink), getSelectionSourceInfo(mediaItem), j3);
                adStreamingRequiredRebufferingEvent.setMetricsContext(mediaItem.getMetricsContext());
                updateEventWithPlaybackInstanceId(adStreamingRequiredRebufferingEvent);
                return adStreamingRequiredRebufferingEvent;
            } catch (Exception e) {
                e = e;
                if (StringUtils.shouldRedact()) {
                    str = "Exception in createStreamingRequiredRebufferingEvent(duration: " + j + ", position: " + j2 + ")";
                } else {
                    str = "Exception in createStreamingRequiredRebufferingEvent(" + metricsInfo + ", duration: " + j + ", position: " + j2 + ")";
                }
                logger.error(str, (Throwable) e);
                return null;
            }
        } catch (Exception e2) {
            e = e2;
        }
    }

    protected MTSEvent createSportStreamingInitiationFailedEvent(MediaItem mediaItem, MetricsInfo metricsInfo, @Nullable MediaLink mediaLink, ChangeReason changeReason, PlaybackException playbackException) throws CirrusInvalidDataException {
        SportStreamingInitiationFailedEvent sportStreamingInitiationFailedEvent = new SportStreamingInitiationFailedEvent(getEntityId(mediaItem), getEntityIdType(mediaItem), getEntityType(mediaItem), mediaItem.getMediaItemId().getId(), mediaItem.getType().isLive() ? MediaType.LIVE : MediaType.ONDEMAND, getNetworkProvider().getMetricsName(), getContentSubscriptionMode(mediaItem), getBitRate(mediaLink), getErrorMessage(playbackException), InitiationReason.USER_START, getSelectionSourceInfo(mediaItem), getMediaPlayerType(mediaLink), getMediaPlayerName());
        sportStreamingInitiationFailedEvent.setMetricsContext(mediaItem.getMetricsContext());
        return sportStreamingInitiationFailedEvent;
    }

    protected MTSEvent createSportStreamingInitiatedEvent(MediaItem mediaItem, MetricsInfo metricsInfo, @Nullable MediaLink mediaLink, long j, @Nullable ChangeReason changeReason) throws CirrusInvalidDataException {
        SportStreamingInitiatedEvent sportStreamingInitiatedEvent = new SportStreamingInitiatedEvent(getEntityId(mediaItem), getEntityIdType(mediaItem), getEntityType(mediaItem), mediaItem.getMediaItemId().getId(), mediaItem.getType().isLive() ? MediaType.LIVE : MediaType.ONDEMAND, getNetworkProvider().getMetricsName(), getContentSubscriptionMode(mediaItem), j, InitiationReason.USER_START, getBitRate(mediaLink), getMediaPlayerType(mediaLink), getMediaPlayerName());
        sportStreamingInitiatedEvent.setMetricsContext(mediaItem.getMetricsContext());
        return sportStreamingInitiatedEvent;
    }

    protected MTSEvent createAdStreamingInitiatedEvent(MediaItem mediaItem, MetricsInfo metricsInfo, @Nullable MediaLink mediaLink, long j, @Nullable ChangeReason changeReason) throws CirrusInvalidDataException {
        String str;
        try {
            TrackPlaybackInfo trackPlaybackInfo = getTrackPlaybackInfo(mediaItem);
            SelectionSourceInfo selectionSourceInfo = getSelectionSourceInfo(mediaItem);
            String metricsName = getNetworkProvider().getMetricsName();
            boolean zIsExplicitFilterOn = isExplicitFilterOn();
            if (changeReason == null) {
                changeReason = ChangeReason.NEW_SOURCE;
            }
            AdStreamingInitiatedEvent adStreamingInitiatedEvent = new AdStreamingInitiatedEvent(trackPlaybackInfo, selectionSourceInfo, metricsName, zIsExplicitFilterOn, j, changeReason.name());
            adStreamingInitiatedEvent.setMetricsContext(mediaItem.getMetricsContext());
            updateEventWithPlaybackInstanceId(adStreamingInitiatedEvent);
            return adStreamingInitiatedEvent;
        } catch (Exception e) {
            if (StringUtils.shouldRedact()) {
                str = "Exception in createStreamingInitiatedEvent()";
            } else {
                str = "Exception in createStreamingInitiatedEvent(" + metricsInfo + ")";
            }
            logger.error(str, (Throwable) e);
            return null;
        }
    }

    protected MTSEvent createSportStreamingTerminatedEvent(MediaItem mediaItem, MetricsInfo metricsInfo, @Nullable MediaLink mediaLink, long j, long j2, int i, long j3, float f, TerminationReason terminationReason) throws CirrusInvalidDataException {
        SportStreamingTerminatedEvent sportStreamingTerminatedEvent = new SportStreamingTerminatedEvent(getEntityId(mediaItem), getEntityIdType(mediaItem), getEntityType(mediaItem), mediaItem.getMediaItemId().getId(), mediaItem.getType().isLive() ? MediaType.LIVE : MediaType.ONDEMAND, getNetworkProvider().getMetricsName(), getContentSubscriptionMode(mediaItem), getBitRate(mediaLink), j3, getMediaPlayerType(mediaLink), getCacheHitStatus(mediaLink, f), terminationReason, TimeUnit.MILLISECONDS.toSeconds(j2), i, getMediaPlayerName());
        sportStreamingTerminatedEvent.setMetricsContext(mediaItem.getMetricsContext());
        return sportStreamingTerminatedEvent;
    }

    protected MTSEvent createSportStreamingRequiredRebufferingEvent(MediaItem mediaItem, MetricsInfo metricsInfo, MediaLink mediaLink, long j, long j2, long j3, float f) throws CirrusInvalidDataException {
        SportStreamingRequiredRebufferingEvent sportStreamingRequiredRebufferingEvent = new SportStreamingRequiredRebufferingEvent(getEntityId(mediaItem), getEntityIdType(mediaItem), getEntityType(mediaItem), mediaItem.getMediaItemId().getId(), mediaItem.getType().isLive() ? MediaType.LIVE : MediaType.ONDEMAND, getNetworkProvider().getMetricsName(), getContentSubscriptionMode(mediaItem), getBitRate(mediaLink), j, j2, j3, getMediaPlayerType(mediaLink), getCacheHitStatus(mediaLink, f), getMediaPlayerName());
        sportStreamingRequiredRebufferingEvent.setMetricsContext(mediaItem.getMetricsContext());
        return sportStreamingRequiredRebufferingEvent;
    }

    protected MTSEvent createGenericPlaybackInitiatedEvent(MediaItem mediaItem, long j, ResourceType resourceType, PlaybackSourceType playbackSourceType, PlaybackEntityType playbackEntityType, AudioVideoToggleMetricsInfo audioVideoToggleMetricsInfo) {
        GenericPlaybackInitiatedEvent genericPlaybackInitiatedEvent = new GenericPlaybackInitiatedEvent(audioVideoToggleMetricsInfo, getTrackPlaybackInfo(mediaItem), getSelectionSourceInfo(mediaItem), getNetworkProvider().getMetricsName(), mediaItem.isVoiceInitiated(), j, isExplicitFilterOn(), isShuffled(), getMediaPlayerName(), resourceType, playbackSourceType, playbackEntityType);
        genericPlaybackInitiatedEvent.setMetricsContext(mediaItem.getMetricsContext());
        updateEventWithVideoPlaybackInstanceId(genericPlaybackInitiatedEvent);
        return genericPlaybackInitiatedEvent;
    }

    protected MTSEvent createGenericPlaybackTerminatedEvent(MediaItem mediaItem, @Nullable PlaybackException playbackException, long j, TerminationReason terminationReason, ResourceType resourceType, PlaybackSourceType playbackSourceType, PlaybackEntityType playbackEntityType, AudioVideoToggleMetricsInfo audioVideoToggleMetricsInfo, MetricsInfo metricsInfo, long j2) {
        GenericPlaybackTerminatedEvent genericPlaybackTerminatedEvent = new GenericPlaybackTerminatedEvent(audioVideoToggleMetricsInfo, getTrackPlaybackInfo(mediaItem), getSelectionSourceInfo(mediaItem), getNetworkProvider().getMetricsName(), isExplicitFilterOn(), mediaItem.getDurationMillis(), j, playbackEntityType, playbackSourceType, getErrorMessage(playbackException), terminationReason, resourceType, (PlaybackPageType) metricsInfo.get(PlaybackEvent.PROPERTY_PAGE_TYPE), getMediaPlayerName(), mediaItem.isVoiceInitiated(), isShuffled(), j2);
        genericPlaybackTerminatedEvent.setMetricsContext(mediaItem.getMetricsContext());
        updateEventWithVideoPlaybackInstanceId(genericPlaybackTerminatedEvent);
        return genericPlaybackTerminatedEvent;
    }

    protected MTSEvent createGenericPlaybackCompletedEvent(MediaItem mediaItem, long j, ResourceType resourceType, PlaybackSourceType playbackSourceType, PlaybackEntityType playbackEntityType, AudioVideoToggleMetricsInfo audioVideoToggleMetricsInfo) {
        GenericPlaybackCompletedEvent genericPlaybackCompletedEvent = new GenericPlaybackCompletedEvent(audioVideoToggleMetricsInfo, getTrackPlaybackInfo(mediaItem), getSelectionSourceInfo(mediaItem), j, mediaItem.getDurationMillis(), getNetworkProvider().getMetricsName(), getMediaPlayerName(), j, mediaItem.isVoiceInitiated(), isExplicitFilterOn(), isShuffled(), resourceType, playbackSourceType, playbackEntityType);
        genericPlaybackCompletedEvent.setMetricsContext(mediaItem.getMetricsContext());
        updateEventWithVideoPlaybackInstanceId(genericPlaybackCompletedEvent);
        this.config.setVideoPlaybackInstanceId(UUID.randomUUID());
        return genericPlaybackCompletedEvent;
    }

    protected MTSEvent createGenericPlaybackResumedEvent(MediaItem mediaItem, long j, long j2, ChangeReason changeReason, PlaybackSourceType playbackSourceType, PlaybackEntityType playbackEntityType, AudioVideoToggleMetricsInfo audioVideoToggleMetricsInfo) {
        GenericPlaybackResumedEvent genericPlaybackResumedEvent = new GenericPlaybackResumedEvent(audioVideoToggleMetricsInfo, getTrackPlaybackInfo(mediaItem), getResumeReason(changeReason), j, j2, playbackSourceType, playbackEntityType);
        genericPlaybackResumedEvent.setMetricsContext(mediaItem.getMetricsContext());
        updateEventWithVideoPlaybackInstanceId(genericPlaybackResumedEvent);
        return genericPlaybackResumedEvent;
    }

    protected MTSEvent createGenericPlaybackStateEvent(MediaItem mediaItem, long j, boolean z, PlaybackSourceType playbackSourceType, PlaybackEntityType playbackEntityType, AudioVideoToggleMetricsInfo audioVideoToggleMetricsInfo) {
        GenericPlaybackStateEvent genericPlaybackStateEvent = new GenericPlaybackStateEvent(audioVideoToggleMetricsInfo, getTrackPlaybackInfo(mediaItem).getAsin(), z, j, playbackSourceType, playbackEntityType);
        genericPlaybackStateEvent.setMetricsContext(mediaItem.getMetricsContext());
        updateEventWithVideoPlaybackInstanceId(genericPlaybackStateEvent);
        return genericPlaybackStateEvent;
    }

    public TrackPlaybackInfo getTrackPlaybackInfo(MediaItem mediaItem) {
        MediaItemId mediaItemId = mediaItem.getMediaItemId(MediaItemId.Type.ASIN);
        String id = mediaItemId == null ? null : mediaItemId.getId();
        MediaItemId mediaItemId2 = mediaItem.getMediaItemId(MediaItemId.Type.OWNED);
        String id2 = mediaItemId2 == null ? null : mediaItemId2.getId();
        MediaCollectionInfo mediaCollectionInfo = mediaItem.getMediaCollectionInfo(null, MediaCollectionId.Type.MPQS);
        MediaCollectionType type = mediaCollectionInfo == null ? null : mediaCollectionInfo.getType();
        TrackPlaybackInfo trackPlaybackInfo = new TrackPlaybackInfo(id, id2, getContentSubscriptionMode(mediaItem), type == null ? null : type.getMetricsName(), type != null ? type.getMetricsName() : null);
        trackPlaybackInfo.setSubContentSubscriptionMode(getSubContentSubscriptionMode(mediaItem));
        if (ObjectUtils.isAdvertisement(mediaItem)) {
            if (getContentSubscriptionMode(mediaItem) != null) {
                trackPlaybackInfo.setContentSubscriptionMode(getContentSubscriptionMode(mediaItem));
            } else {
                trackPlaybackInfo.setContentSubscriptionMode(new FreeSubscriptionTracking().contentSubscriptionMode());
            }
        }
        return trackPlaybackInfo;
    }

    public String getContentSubscriptionMode(MediaItem mediaItem) {
        if (mediaItem.getMediaAccessInfo().isOwned()) {
            return new NoSubscriptionTracking().contentSubscriptionMode();
        }
        return this.config.getMediaAccessPolicy().getContentSubscriptionMode();
    }

    public String getSubContentSubscriptionMode(MediaItem mediaItem) {
        if (mediaItem.getMediaAccessInfo().isOwned()) {
            return new NoSubscriptionTracking().subContentSubscriptionMode();
        }
        return this.config.getMediaAccessPolicy().getSubContentSubscriptionMode();
    }

    public TerminationReason getTerminationReason(@Nullable ChangeReason changeReason) {
        if (changeReason == null) {
            logger.error("changeReason is null, default TerminationReason to USER_STOP");
            return TerminationReason.USER_STOP;
        }
        switch (AnonymousClass1.$SwitchMap$com$amazon$music$media$playback$ChangeReason[changeReason.ordinal()]) {
            case 1:
                break;
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
                break;
            case 7:
                break;
            case 8:
            case 9:
                break;
            case 10:
            case 11:
                break;
            case 12:
                break;
            case 13:
            case 14:
            case 15:
                break;
            case 16:
                break;
            case 17:
            case 18:
                break;
            case 19:
                break;
            case 20:
            case 21:
                break;
            case 22:
                break;
            case 23:
                break;
            case 24:
                break;
            default:
                StrictMode.crashIfStrict(changeReason + " is not recognized as a valid termination reason.");
                break;
        }
        return TerminationReason.USER_STOP;
    }

    private TerminationReason getTerminationReasonForToggle() {
        MediaItem currentMediaItem = getPlaybackController().getCurrentMediaItem();
        if (currentMediaItem != null && currentMediaItem.getMediaItemId(MediaItemId.Type.PID) != null) {
            return TerminationReason.TOGGLE_AUDIO_VIDEO;
        }
        return TerminationReason.TOGGLE_VIDEO_AUDIO;
    }

    @Override // com.amazon.music.media.playback.metrics.mts.MTSEventFactory
    public MTSEvent createSubDeviceConnectedEvent(String str, boolean z) {
        if (z) {
            this.config.setPlaybackInstanceId(UUID.randomUUID());
        }
        return new SubDeviceConnectedEvent(str, this.config.getPlaybackInstanceId().toString());
    }

    @Override // com.amazon.music.media.playback.metrics.mts.MTSEventFactory
    public MTSEvent createSubDeviceDisconnectedEvent(String str, boolean z) {
        SubDeviceDisconnectedEvent subDeviceDisconnectedEvent = new SubDeviceDisconnectedEvent(str, this.config.getPlaybackInstanceId().toString());
        if (z) {
            this.config.setPlaybackInstanceId(UUID.randomUUID());
        }
        return subDeviceDisconnectedEvent;
    }

    public PlaybackInitiationInfo getPlaybackInitiationInfo(MetricsInfo metricsInfo) {
        return new PlaybackInitiationInfo(((Boolean) metricsInfo.get(PlaybackEvent.PROPERTY_DIRECTED_PLAY)).booleanValue() ? DirectedPlayStatus.DIRECTED : DirectedPlayStatus.NOT_DIRECTED, getPlaybackController().isShuffled(), (PlaybackPageType) metricsInfo.get(PlaybackEvent.PROPERTY_PAGE_TYPE));
    }

    public SelectionSourceInfo getSelectionSourceInfo(MediaItem mediaItem) {
        String strEmptyToNull = StringUtils.emptyToNull(mediaItem.getSessionId());
        MediaCollectionInfo sourceMediaCollectionInfo = mediaItem.getSourceMediaCollectionInfo();
        String id = null;
        if (sourceMediaCollectionInfo == null) {
            StrictMode.crashIfStrict(mediaItem + " has no MediaCollectionInfo!");
            return new SelectionSourceInfo(SelectionSourceType.SONGS, null, strEmptyToNull);
        }
        SelectionSourceType selectionSourceTypeFromMediaCollectionType = ObjectUtils.getSelectionSourceTypeFromMediaCollectionType(mediaItem.getSourceMediaCollectionInfo().getType());
        MediaCollectionId id2 = sourceMediaCollectionInfo.getId(MediaCollectionId.Type.SELECTION_SOURCE_ID);
        if (id2 == null) {
            id2 = sourceMediaCollectionInfo.getId();
        }
        if (id2 != null) {
            id = id2.getId();
        } else if (selectionSourceTypeFromMediaCollectionType != SelectionSourceType.SONGS) {
            StrictMode.crashIfStrict(mediaItem + " has no collectionId!");
        }
        return new SelectionSourceInfo(selectionSourceTypeFromMediaCollectionType, id, strEmptyToNull);
    }

    public ResourceType getResourceType(MediaItem mediaItem) {
        return ResourceType.CLOUD_PLAYER;
    }

    public CacheHitStatus getCacheHitStatus(MediaLink mediaLink, float f) {
        if (mediaLink == null) {
            return CacheHitStatus.UNKNOWN;
        }
        if (mediaLink.getType() == MediaLink.Type.LOCAL) {
            return CacheHitStatus.LOCAL;
        }
        if (f == 0.0f) {
            return CacheHitStatus.NONE;
        }
        if (f == 1.0f) {
            return CacheHitStatus.FULL;
        }
        if (f > 0.0f && f < 1.0f) {
            return CacheHitStatus.PARTIAL;
        }
        return CacheHitStatus.UNKNOWN;
    }

    public MediaPlayerType getMediaPlayerType(MediaLink mediaLink) {
        if (mediaLink != null) {
            String metricsName = mediaLink.getType().getMetricsName();
            for (MediaPlayerType mediaPlayerType : MediaPlayerType.values()) {
                if (mediaPlayerType.getMetricsValue().equals(metricsName)) {
                    return mediaPlayerType;
                }
            }
            StrictMode.crashIfStrict("Unknown MediaPlayerType for " + mediaLink);
        }
        return MediaPlayerType.UNKNOWN;
    }

    public String getMediaPlayerName() {
        return getPlaybackController().getMediaPlayerName();
    }

    public BitRate getBitRate(MediaLink mediaLink) {
        BitRateSelection bitRateSelection = this.config.getBitRateSelection();
        BitRateSelection bitRateSelection2 = mediaLink == null ? BitRateSelection.AUTO : mediaLink.getBitRateSelection();
        int i = AnonymousClass1.$SwitchMap$com$amazon$music$media$playback$config$BitRateSelection[bitRateSelection2.ordinal()];
        if (i == 1) {
            return bitRateSelection == bitRateSelection2 ? BitRate.HIGH : BitRate.AUTO_HIGH;
        }
        if (i == 2) {
            return bitRateSelection == bitRateSelection2 ? BitRate.MEDIUM : BitRate.AUTO_MEDIUM;
        }
        if (i != 3) {
            return BitRate.UNKNOWN;
        }
        return bitRateSelection == bitRateSelection2 ? BitRate.LOW : BitRate.AUTO_LOW;
    }

    /* JADX INFO: renamed from: com.amazon.music.media.playback.metrics.mts.DefaultMTSEventFactory$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$music$media$playback$ChangeReason;
        static final /* synthetic */ int[] $SwitchMap$com$amazon$music$media$playback$config$BitRateSelection;

        static {
            int[] iArr = new int[BitRateSelection.values().length];
            $SwitchMap$com$amazon$music$media$playback$config$BitRateSelection = iArr;
            try {
                iArr[BitRateSelection.HIGH.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$music$media$playback$config$BitRateSelection[BitRateSelection.MEDIUM.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$music$media$playback$config$BitRateSelection[BitRateSelection.LOW.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$amazon$music$media$playback$config$BitRateSelection[BitRateSelection.AUTO.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            int[] iArr2 = new int[ChangeReason.values().length];
            $SwitchMap$com$amazon$music$media$playback$ChangeReason = iArr2;
            try {
                iArr2[ChangeReason.GHOST_LISTENING.ordinal()] = 1;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$amazon$music$media$playback$ChangeReason[ChangeReason.NEW_SOURCE.ordinal()] = 2;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$com$amazon$music$media$playback$ChangeReason[ChangeReason.PLAY_PAUSE.ordinal()] = 3;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$com$amazon$music$media$playback$ChangeReason[ChangeReason.INDEX_REQUESTED.ordinal()] = 4;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                $SwitchMap$com$amazon$music$media$playback$ChangeReason[ChangeReason.SOURCE_MODIFIED.ordinal()] = 5;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                $SwitchMap$com$amazon$music$media$playback$ChangeReason[ChangeReason.SWITCHING_PLAYERS.ordinal()] = 6;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                $SwitchMap$com$amazon$music$media$playback$ChangeReason[ChangeReason.TOGGLE.ordinal()] = 7;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                $SwitchMap$com$amazon$music$media$playback$ChangeReason[ChangeReason.NEXT.ordinal()] = 8;
            } catch (NoSuchFieldError unused12) {
            }
            try {
                $SwitchMap$com$amazon$music$media$playback$ChangeReason[ChangeReason.THUMBS_DOWN.ordinal()] = 9;
            } catch (NoSuchFieldError unused13) {
            }
            try {
                $SwitchMap$com$amazon$music$media$playback$ChangeReason[ChangeReason.PREVIOUS.ordinal()] = 10;
            } catch (NoSuchFieldError unused14) {
            }
            try {
                $SwitchMap$com$amazon$music$media$playback$ChangeReason[ChangeReason.RESTART.ordinal()] = 11;
            } catch (NoSuchFieldError unused15) {
            }
            try {
                $SwitchMap$com$amazon$music$media$playback$ChangeReason[ChangeReason.LYRICS_SEEK.ordinal()] = 12;
            } catch (NoSuchFieldError unused16) {
            }
            try {
                $SwitchMap$com$amazon$music$media$playback$ChangeReason[ChangeReason.TIME_SEEK.ordinal()] = 13;
            } catch (NoSuchFieldError unused17) {
            }
            try {
                $SwitchMap$com$amazon$music$media$playback$ChangeReason[ChangeReason.FAST_FORWARD.ordinal()] = 14;
            } catch (NoSuchFieldError unused18) {
            }
            try {
                $SwitchMap$com$amazon$music$media$playback$ChangeReason[ChangeReason.REWIND.ordinal()] = 15;
            } catch (NoSuchFieldError unused19) {
            }
            try {
                $SwitchMap$com$amazon$music$media$playback$ChangeReason[ChangeReason.MEDIA_ITEM_END.ordinal()] = 16;
            } catch (NoSuchFieldError unused20) {
            }
            try {
                $SwitchMap$com$amazon$music$media$playback$ChangeReason[ChangeReason.ERROR.ordinal()] = 17;
            } catch (NoSuchFieldError unused21) {
            }
            try {
                $SwitchMap$com$amazon$music$media$playback$ChangeReason[ChangeReason.TOO_MUCH_SILENCE.ordinal()] = 18;
            } catch (NoSuchFieldError unused22) {
            }
            try {
                $SwitchMap$com$amazon$music$media$playback$ChangeReason[ChangeReason.RESOLVING_CONCURRENCY.ordinal()] = 19;
            } catch (NoSuchFieldError unused23) {
            }
            try {
                $SwitchMap$com$amazon$music$media$playback$ChangeReason[ChangeReason.SYSTEM_PAUSE.ordinal()] = 20;
            } catch (NoSuchFieldError unused24) {
            }
            try {
                $SwitchMap$com$amazon$music$media$playback$ChangeReason[ChangeReason.SYSTEM_STOP.ordinal()] = 21;
            } catch (NoSuchFieldError unused25) {
            }
            try {
                $SwitchMap$com$amazon$music$media$playback$ChangeReason[ChangeReason.EXPLICIT_FILTER_ENABLED.ordinal()] = 22;
            } catch (NoSuchFieldError unused26) {
            }
            try {
                $SwitchMap$com$amazon$music$media$playback$ChangeReason[ChangeReason.EXPLICIT_FILTER_DISABLED.ordinal()] = 23;
            } catch (NoSuchFieldError unused27) {
            }
            try {
                $SwitchMap$com$amazon$music$media$playback$ChangeReason[ChangeReason.UNAUTHORIZED.ordinal()] = 24;
            } catch (NoSuchFieldError unused28) {
            }
            try {
                $SwitchMap$com$amazon$music$media$playback$ChangeReason[ChangeReason.SYSTEM_RESUME.ordinal()] = 25;
            } catch (NoSuchFieldError unused29) {
            }
        }
    }

    public String getEntityId(MediaItem mediaItem) {
        return mediaItem.getSourceMediaCollectionInfo().getId().getId();
    }

    public EntityIdType getEntityIdType(MediaItem mediaItem) {
        String metricsName = mediaItem.getSourceMediaCollectionInfo().getType().getMetricsName();
        for (EntityIdType entityIdType : EntityIdType.values()) {
            if (entityIdType.getMetricsValue().equals(metricsName)) {
                return entityIdType;
            }
        }
        StrictMode.crashIfStrict(metricsName + " doesn't match any EntityIdType");
        return EntityIdType.PROGRAM_ID;
    }

    public EntityType getEntityType(MediaItem mediaItem) {
        String metricsName = mediaItem.getSourceMediaCollectionInfo().getType().getMetricsName();
        try {
            return EntityType.valueOf(metricsName);
        } catch (Exception e) {
            StrictMode.crashIfStrict("No EntityType for: " + metricsName, e);
            return null;
        }
    }

    private String getErrorMessage(PlaybackException playbackException) {
        return (playbackException == null || playbackException.getPlaybackError() == null) ? "" : playbackException.getPlaybackError().name();
    }

    private boolean isExplicitFilterOn() {
        return !this.config.getMediaAccessPolicy().isExplicitAllowed();
    }

    private int getPercentage(long j, long j2) {
        if (j <= 0) {
            return 0;
        }
        if (j >= j2) {
            return 100;
        }
        return (int) ((j * 100) / j2);
    }

    private void updateDirectedPlay(MediaItem mediaItem, MetricsInfo metricsInfo) {
        if (mediaItem.isDirectedPlay()) {
            metricsInfo.set(PlaybackEvent.PROPERTY_DIRECTED_PLAY, Boolean.TRUE);
            mediaItem.setDirectedPlay(false);
        }
    }

    private void updateIsCustomerSelectedEntity(MediaItem mediaItem, MetricsInfo metricsInfo) {
        if (mediaItem.isCustomerSelectedEntity()) {
            metricsInfo.set(PlaybackEvent.PROPERTY_CUSTOMER_SELECTED_ENTITY, Boolean.TRUE);
            mediaItem.setCustomerSelectedEntity(false);
        }
    }

    private void updateEventWithPlaybackInstanceId(MTSEvent mTSEvent) {
        mTSEvent.addAttribute("playbackInstanceId", this.config.getPlaybackInstanceId().toString());
    }

    private void updateEventWithVideoPlaybackInstanceId(MTSEvent mTSEvent) {
        mTSEvent.addAttribute("playbackInstanceId", this.config.getVideoPlaybackInstanceId().toString());
    }

    private void updateEventWithToggleMetricsInfo(MTSEvent mTSEvent, MediaItem mediaItem) {
        mediaItem.getToggleMetricsInfo();
    }

    private void updateEventWithCustomerSelectedEntity(MTSEvent mTSEvent, MetricsInfo metricsInfo) {
        MetricsProperty<Boolean> metricsProperty = PlaybackEvent.PROPERTY_CUSTOMER_SELECTED_ENTITY;
        mTSEvent.addAttribute(metricsProperty.getName(), metricsInfo.get(metricsProperty));
    }

    private void unsetCustomerSelectedEntityInMetricsInfo(MetricsInfo metricsInfo) {
        metricsInfo.set(PlaybackEvent.PROPERTY_CUSTOMER_SELECTED_ENTITY, Boolean.FALSE);
    }

    private void updateEventWithTrackProgress(StreamingTerminatedEvent streamingTerminatedEvent, long j) {
        streamingTerminatedEvent.addAttribute("trackProgress", TimeUnit.MILLISECONDS.toSeconds(j));
    }

    private boolean isShuffled() {
        if (getPlaybackController() == null) {
            return false;
        }
        return getPlaybackController().isShuffled();
    }

    private ResumeReason getResumeReason(ChangeReason changeReason) {
        int i = AnonymousClass1.$SwitchMap$com$amazon$music$media$playback$ChangeReason[changeReason.ordinal()];
        if (i == 3) {
            return ResumeReason.USER_START;
        }
        if (i == 25) {
            return ResumeReason.SYSTEM_START;
        }
        switch (i) {
            case 13:
            case 14:
            case 15:
                return ResumeReason.TRACK_SCRUB;
            default:
                return ResumeReason.UNKNOWN_START;
        }
    }
}
