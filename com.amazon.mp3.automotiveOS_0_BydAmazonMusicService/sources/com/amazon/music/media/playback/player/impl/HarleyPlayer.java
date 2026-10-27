package com.amazon.music.media.playback.player.impl;

import android.content.Context;
import android.net.Uri;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amazon.digitalmusicplayback.ClientInfo;
import com.amazon.digitalmusicplayback.CustomerBenefitFlags;
import com.amazon.digitalmusicplayback.DMLSConfig;
import com.amazon.digitalmusicplayback.DownloadCoordinator;
import com.amazon.digitalmusicplayback.DownloadCoordinatorConfig;
import com.amazon.digitalmusicplayback.DownloadCoordinatorListener;
import com.amazon.digitalmusicplayback.EqualizerBand;
import com.amazon.digitalmusicplayback.EqualizerSettings;
import com.amazon.digitalmusicplayback.MTSAttributes;
import com.amazon.digitalmusicplayback.StreamingStatusResponse;
import com.amazon.music.curate.skyfire.bootstrap.ContextMappingConstants;
import com.amazon.music.find.utils.PageUriUtils;
import com.amazon.music.inappmessaging.internal.model.Splash;
import com.amazon.music.media.playback.ChangeReason;
import com.amazon.music.media.playback.ControlSource;
import com.amazon.music.media.playback.CuePoints;
import com.amazon.music.media.playback.MediaCollectionId;
import com.amazon.music.media.playback.MediaCollectionInfo;
import com.amazon.music.media.playback.MediaItem;
import com.amazon.music.media.playback.MediaItemId;
import com.amazon.music.media.playback.MediaLink;
import com.amazon.music.media.playback.OnFrameChangedListener;
import com.amazon.music.media.playback.OnPlaybackMetricsListener;
import com.amazon.music.media.playback.OnPrebufferChangedListener;
import com.amazon.music.media.playback.PlayStatus;
import com.amazon.music.media.playback.Playback;
import com.amazon.music.media.playback.PlaybackAttributes;
import com.amazon.music.media.playback.PlaybackException;
import com.amazon.music.media.playback.PlayerCapabilities;
import com.amazon.music.media.playback.audioeffects.AudioEffectsControl;
import com.amazon.music.media.playback.audioeffects.BasicAudioEffectsController;
import com.amazon.music.media.playback.audioeffects.EqualizerBandControl;
import com.amazon.music.media.playback.audioeffects.GainControl;
import com.amazon.music.media.playback.concurrency.ConcurrencyException;
import com.amazon.music.media.playback.concurrency.ConcurrencyProvider;
import com.amazon.music.media.playback.concurrency.ConcurrentDeviceInfo;
import com.amazon.music.media.playback.config.BitRateSelection;
import com.amazon.music.media.playback.config.ClientIdentification;
import com.amazon.music.media.playback.config.PlaybackConfig;
import com.amazon.music.media.playback.config.PlayerAuthentication;
import com.amazon.music.media.playback.metrics.MetricsEvent;
import com.amazon.music.media.playback.metrics.MetricsInfo;
import com.amazon.music.media.playback.metrics.PlaybackEvent;
import com.amazon.music.media.playback.metrics.PlaybackEventType;
import com.amazon.music.media.playback.metrics.mts.MTSEventFactory;
import com.amazon.music.media.playback.metrics.mts.MTSMetricsSender;
import com.amazon.music.media.playback.player.AbstractPlayer;
import com.amazon.music.media.playback.player.PlayRequest;
import com.amazon.music.media.playback.player.PlayRequestStatus;
import com.amazon.music.media.playback.player.impl.HarleyWrapper;
import com.amazon.music.media.playback.sequencer.impl.StationMediaItem;
import com.amazon.music.media.playback.util.Clock;
import com.amazon.music.media.playback.util.ObjectUtils;
import com.amazon.music.media.playback.util.StrictMode;
import com.amazon.music.media.playback.util.StringUtils;
import com.amazon.music.metrics.mts.MTSEvent;
import com.amazon.music.metrics.mts.event.EventTimeConverter;
import com.amazon.music.metrics.mts.event.definition.playback.AdStreamingInitiatedEvent;
import com.amazon.music.metrics.mts.event.definition.playback.AdStreamingTerminatedEvent;
import com.amazon.music.metrics.mts.event.definition.playback.GenericPlaybackInitiatedEvent;
import com.amazon.music.metrics.mts.event.definition.playback.SportStreamingInitiatedEvent;
import com.amazon.music.metrics.mts.event.definition.playback.SportStreamingInitiationFailedEvent;
import com.amazon.music.metrics.mts.event.definition.playback.SportStreamingRequiredRebufferingEvent;
import com.amazon.music.metrics.mts.event.definition.playback.SportStreamingTerminatedEvent;
import com.amazon.music.metrics.mts.event.definition.playback.StreamingTerminatedEvent;
import com.amazon.music.metrics.mts.event.types.TerminationReason;
import com.amazon.music.metrics.util.StringUtil;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.ListIterator;
import java.util.concurrent.TimeUnit;
import org.slf4j.Logger;

/* JADX INFO: loaded from: classes4.dex */
public class HarleyPlayer extends AbstractPlayer {
    private static final long MANY_MILLISECONDS;
    private static final long MANY_SECONDS;
    private BasicAudioEffectsController audioEffectsControl;
    private final GainControl bassController;
    private BitRateSelection bitRateSelectionForOTA;
    private BitRateSelection bitRateSelectionForWifi;
    private ClientIdentification clientIdentification;
    private ConcurrencyProvider concurrencyProvider;
    private String deviceToTerminate;
    private DownloadCoordinator downloadCoordinator;
    private final HarleyWrapper<PlayRequest> engine;
    private final HarleyWrapper.HarleyCallback<PlayRequest> harleyCallback;
    private PlayerAuthentication playerAuthentication;
    private boolean setStreamOnWifiOnly;
    private final GainControl trebleController;

    static {
        TimeUnit timeUnit = TimeUnit.DAYS;
        MANY_SECONDS = timeUnit.toSeconds(365L);
        MANY_MILLISECONDS = timeUnit.toMillis(365L);
    }

    public HarleyPlayer(@NonNull Context context, @Nullable String str) {
        this(context);
        if (str != null) {
            this.engine.setMricTokenForChildUser(str);
        }
    }

    public HarleyPlayer(@NonNull Context context) {
        super(context, "HARLEY", null, true, true, false);
        HarleyWrapper.HarleyCallback<PlayRequest> harleyCallback = new HarleyWrapper.HarleyCallback<PlayRequest>() { // from class: com.amazon.music.media.playback.player.impl.HarleyPlayer.2
            @Override // com.amazon.music.media.playback.player.impl.HarleyWrapper.HarleyCallback
            public Uri getUri(PlayRequest playRequest) {
                MediaItemId primaryId;
                if (playRequest == null || (primaryId = getPrimaryId(playRequest.getMediaItem())) == null) {
                    return null;
                }
                return primaryId.getUri();
            }

            private MediaItemId getPrimaryId(MediaItem mediaItem) {
                MediaItemId mediaItemId;
                if (mediaItem.getOverriddenPrimaryId() == null) {
                    if (HarleyPlayer.this.hasLocalFile(mediaItem)) {
                        return mediaItem.getMediaItemId(MediaItemId.Type.LOCAL_URI);
                    }
                    if (mediaItem.isOwned() && !HarleyPlayer.this.isDownloadedByAsin(mediaItem) && (mediaItemId = mediaItem.getMediaItemId(MediaItemId.Type.OWNED)) != null) {
                        return mediaItemId;
                    }
                    MediaItemId.Type type = MediaItemId.Type.ASIN;
                    MediaItemId mediaItemId2 = mediaItem.getMediaItemId(type);
                    if (mediaItemId2 != null) {
                        return mediaItem.isOffSetPlaybackEnabled() ? new MediaItemId(type, CuePoints.createOffsetAsin(mediaItemId2.getId(), mediaItem.getOffSetCuePoints())) : mediaItemId2;
                    }
                    MediaItemId mediaItemId3 = mediaItem.getMediaItemId(MediaItemId.Type.OWNED);
                    return mediaItemId3 != null ? mediaItemId3 : mediaItem.getMediaItemId();
                }
                return mediaItem.getOverriddenPrimaryId();
            }

            @Override // com.amazon.music.media.playback.player.impl.HarleyWrapper.HarleyCallback
            public void onAmountBufferedChanged(PlayRequest playRequest, long j) {
                playRequest.setAmountBuffered(j);
            }

            @Override // com.amazon.music.media.playback.player.impl.HarleyWrapper.HarleyCallback
            public void onStatusChanged() {
                HarleyPlayer.this.updatePlayRequestStatus();
                HarleyPlayer.this.updatePlayStatus(null, null);
            }

            @Override // com.amazon.music.media.playback.player.impl.HarleyWrapper.HarleyCallback
            public void playbackAttributesChanged() {
                PlayRequest currentPlayRequest;
                PlaybackAttributes bestAvailablePlaybackAttributes = HarleyPlayer.this.engine.getBestAvailablePlaybackAttributes();
                if (!PlaybackAttributes.UNKNOWN.equals(bestAvailablePlaybackAttributes) && (currentPlayRequest = HarleyPlayer.this.getCurrentPlayRequest()) != null) {
                    currentPlayRequest.getMediaItem().setBestAvailablePlaybackAttributes(bestAvailablePlaybackAttributes);
                }
                HarleyPlayer.this.notifyPlaybackAttributesChanged();
            }

            @Override // com.amazon.music.media.playback.player.impl.HarleyWrapper.HarleyCallback
            public void onTrackChanged() {
                HarleyPlayer.this.updateCurrentIndex(null, ChangeReason.MEDIA_ITEM_END, null);
                if (HarleyPlayer.this.isLoopMediaItem()) {
                    HarleyPlayer.this.addMissingLoopItems();
                    HarleyPlayer.this.notifyRepeatingMediaItem();
                }
            }

            @Override // com.amazon.music.media.playback.player.impl.HarleyWrapper.HarleyCallback
            public MediaItem getMediaItem(PlayRequest playRequest) {
                if (playRequest == null) {
                    return null;
                }
                return playRequest.getMediaItem();
            }

            @Override // com.amazon.music.media.playback.player.impl.HarleyWrapper.HarleyCallback
            public void onError(PlayRequest playRequest, PlaybackException playbackException) {
                onError(playRequest, playbackException, ChangeReason.ERROR);
            }

            private void onError(PlayRequest playRequest, PlaybackException playbackException, ChangeReason changeReason) {
                ((AbstractPlayer) HarleyPlayer.this).logger.warn("onError(" + playRequest + ", " + changeReason + ") - current request: " + HarleyPlayer.this.getCurrentPlayRequest(), (Throwable) playbackException);
                if (playRequest != HarleyPlayer.this.getCurrentPlayRequest()) {
                    return;
                }
                if (playRequest != null) {
                    HarleyPlayer.this.setPlayRequestStatus(playRequest, playRequest.getPlayRequestStatus().onError(), playRequest.getPositionTracker().getCurrentPosition(), Clock.now(), -1.0f, playbackException, changeReason, null);
                }
                HarleyPlayer.this.removeAllPlayRequests();
                HarleyPlayer.this.setPlayStatus(PlayStatus.USER_PAUSED, playbackException, changeReason, null, Clock.now());
            }

            @Override // com.amazon.music.media.playback.player.impl.HarleyWrapper.HarleyCallback
            public void onPositionChanged(long j) {
                PlayRequest currentPlayRequest = HarleyPlayer.this.getCurrentPlayRequest();
                if (currentPlayRequest != null) {
                    currentPlayRequest.getPositionTracker().setPosition(j);
                }
            }

            @Override // com.amazon.music.media.playback.player.impl.HarleyWrapper.HarleyCallback
            public void onInactivityThresholdSecondsReceived(long j) {
                long millis;
                PlaybackConfig playbackConfig = HarleyPlayer.this.getPlaybackConfig();
                if (playbackConfig != null && playbackConfig.isUsingGhostListeningThresholdFromServer()) {
                    if (j <= 0 || j >= HarleyPlayer.MANY_SECONDS) {
                        millis = HarleyPlayer.MANY_MILLISECONDS;
                    } else {
                        millis = TimeUnit.SECONDS.toMillis(j);
                    }
                    playbackConfig.setGhostListeningTimeoutMillis(millis);
                }
            }

            @Override // com.amazon.music.media.playback.player.impl.HarleyWrapper.HarleyCallback
            public void onMaximumConcurrencyReached(List<StreamingStatusResponse> list) {
                if (HarleyPlayer.this.deviceToTerminate != null) {
                    ((AbstractPlayer) HarleyPlayer.this).logger.info("We alreaady have a device to terminate, resume the playback without raising an error");
                    HarleyPlayer.this.engine.play(HarleyPlayer.this.deviceToTerminate);
                    HarleyPlayer.this.deviceToTerminate = null;
                    return;
                }
                ArrayList arrayList = new ArrayList();
                for (StreamingStatusResponse streamingStatusResponse : list) {
                    if (!StringUtils.isEmpty(streamingStatusResponse.getDeviceName())) {
                        arrayList.add(new ConcurrentDeviceInfo(streamingStatusResponse.getDeviceName(), streamingStatusResponse.getElapsedTime()));
                    }
                }
                if (arrayList.isEmpty()) {
                    arrayList.add(new ConcurrentDeviceInfo(null, 0L));
                }
                onError(HarleyPlayer.this.getCurrentPlayRequest(), new ConcurrencyException(arrayList, false), ChangeReason.RESOLVING_CONCURRENCY);
            }

            @Override // com.amazon.music.media.playback.player.impl.HarleyWrapper.HarleyCallback
            public void onStreamingInitiated(PlayRequest playRequest, MTSAttributes mTSAttributes) {
                if (!playRequest.getMetricsHandler().sentStreamingInitiated() || HarleyPlayer.this.isLoopMediaItem() || ObjectUtils.isAdvertisement(playRequest.getMediaItem())) {
                    MTSMetricsSender metricsSender = playRequest.getMetricsSender();
                    MTSEventFactory mTSEventFactory = HarleyPlayer.this.getPlaybackConfig().getMTSEventFactory();
                    MetricsInfo metricsInfo = playRequest.getMetricsHandler().getMetricsInfo();
                    metricsInfo.set(PlaybackEvent.PROPERTY_LOCAL, Boolean.valueOf(mTSAttributes.getLocalFile()));
                    MTSEvent mTSEventCreateStreamingInitiatedEvent = mTSEventFactory.createStreamingInitiatedEvent(metricsInfo, null, HarleyPlayer.this.getInitialPlaybackDelay(metricsInfo, mTSAttributes.getInitialPlaybackDelay(), "onStreamingInitiated"), HarleyPlayer.this.getPlaybackStartTime(metricsInfo, mTSAttributes.getInitialPlaybackDelay()), null);
                    if (mTSEventCreateStreamingInitiatedEvent == null) {
                        return;
                    }
                    mTSEventCreateStreamingInitiatedEvent.addAttribute("streamOrDRMTech", mTSAttributes.getStreamOrDRMTech());
                    mTSEventCreateStreamingInitiatedEvent.addAttribute(PageUriUtils.SOURCE_QUERY_PARAM, mTSAttributes.getNetworkSource());
                    if (mTSEventCreateStreamingInitiatedEvent instanceof SportStreamingInitiatedEvent) {
                        mTSEventCreateStreamingInitiatedEvent.addAttribute("cacheHitStatus", mTSAttributes.getCacheHitStatus());
                        mTSEventCreateStreamingInitiatedEvent.addAttribute("bitrates", mTSAttributes.getBitrateDescription());
                        mTSEventCreateStreamingInitiatedEvent.addAttribute("mediaPlayerName", mTSAttributes.getMediaPlayerName());
                    }
                    if (mTSEventCreateStreamingInitiatedEvent instanceof AdStreamingInitiatedEvent) {
                        String initiationReason = mTSAttributes.getInitiationReason();
                        if ("trackFinished".equalsIgnoreCase(initiationReason)) {
                            initiationReason = "playbackThresholdReached";
                        }
                        mTSEventCreateStreamingInitiatedEvent.addAttribute("initiationReason", initiationReason);
                    } else if (mTSEventCreateStreamingInitiatedEvent instanceof GenericPlaybackInitiatedEvent) {
                        MediaItem mediaItem = (MediaItem) metricsInfo.get(PlaybackEvent.PROPERTY_MEDIA_ITEM);
                        if (ObjectUtils.isInterlude(mediaItem)) {
                            HarleyPlayer.this.updateEventForInterlude(mTSEventCreateStreamingInitiatedEvent, mediaItem);
                        }
                    }
                    playRequest.getMetricsHandler().setSentStreamingInitiated(true);
                    metricsSender.sendMetricsEvent(mTSEventCreateStreamingInitiatedEvent, metricsInfo);
                }
            }

            @Override // com.amazon.music.media.playback.player.impl.HarleyWrapper.HarleyCallback
            public void onTrackFinished(PlayRequest playRequest, long j, MTSAttributes mTSAttributes, boolean z) {
                MTSEvent mTSEventCreateTrackStreamedEvent;
                MTSMetricsSender metricsSender = playRequest.getMetricsSender();
                MTSEventFactory mTSEventFactory = HarleyPlayer.this.getPlaybackConfig().getMTSEventFactory();
                MetricsInfo metricsInfo = playRequest.getMetricsHandler().getMetricsInfo();
                metricsInfo.set(PlaybackEvent.PROPERTY_LOCAL, Boolean.valueOf(mTSAttributes.getLocalFile()));
                if (z && (mTSEventCreateTrackStreamedEvent = mTSEventFactory.createTrackStreamedEvent(metricsInfo, j, HarleyPlayer.this.getTerminationReason(mTSAttributes))) != null) {
                    mTSEventCreateTrackStreamedEvent.addAttribute("bitrates", mTSAttributes.getBitrateDescription());
                    HarleyPlayer.this.addSubstituteAsin(mTSEventCreateTrackStreamedEvent, mTSAttributes);
                    metricsSender.sendMetricsEvent(mTSEventCreateTrackStreamedEvent);
                }
                playRequest.getMetricsHandler().resetMaxPosition();
                if (HarleyPlayer.this.isLoopMediaItem()) {
                    return;
                }
                playRequest.getMetricsHandler().setSentStreamingInitiated(false);
            }

            @Override // com.amazon.music.media.playback.player.impl.HarleyWrapper.HarleyCallback
            public void onStreamingInitiationFailed(PlayRequest playRequest, MTSAttributes mTSAttributes) {
                MTSMetricsSender metricsSender = playRequest.getMetricsSender();
                MTSEventFactory mTSEventFactory = HarleyPlayer.this.getPlaybackConfig().getMTSEventFactory();
                MetricsInfo metricsInfo = playRequest.getMetricsHandler().getMetricsInfo();
                metricsInfo.set(PlaybackEvent.PROPERTY_LOCAL, Boolean.valueOf(mTSAttributes.getLocalFile()));
                MTSEvent mTSEventCreateStreamingInitiationFailedEvent = mTSEventFactory.createStreamingInitiationFailedEvent(metricsInfo, null, null, null);
                if (mTSEventCreateStreamingInitiationFailedEvent == null) {
                    return;
                }
                mTSEventCreateStreamingInitiationFailedEvent.addAttribute("streamOrDRMTech", mTSAttributes.getStreamOrDRMTech());
                mTSEventCreateStreamingInitiationFailedEvent.addAttribute(PageUriUtils.SOURCE_QUERY_PARAM, mTSAttributes.getNetworkSource());
                if (mTSEventCreateStreamingInitiationFailedEvent instanceof SportStreamingInitiationFailedEvent) {
                    mTSEventCreateStreamingInitiationFailedEvent.addAttribute("mediaPlayerName", mTSAttributes.getMediaPlayerName());
                }
                metricsSender.sendMetricsEvent(mTSEventCreateStreamingInitiationFailedEvent);
            }

            @Override // com.amazon.music.media.playback.player.impl.HarleyWrapper.HarleyCallback
            public void onPlaybackTerminated(PlayRequest playRequest, long j, MTSAttributes mTSAttributes) {
                long jConvertToRoundedSeconds;
                PlaybackConfig playbackConfig = HarleyPlayer.this.getPlaybackConfig();
                if (playbackConfig == null) {
                    ((AbstractPlayer) HarleyPlayer.this).logger.debug("PlaybackConfig was null after streaming terminated.");
                    return;
                }
                MTSMetricsSender metricsSender = playRequest.getMetricsSender();
                MTSEventFactory mTSEventFactory = playbackConfig.getMTSEventFactory();
                MetricsInfo metricsInfo = playRequest.getMetricsHandler().getMetricsInfo();
                metricsInfo.set(PlaybackEvent.PROPERTY_LOCAL, Boolean.valueOf(mTSAttributes.getLocalFile()));
                MTSEvent mTSEventCreateStreamingTerminatedEvent = mTSEventFactory.createStreamingTerminatedEvent(metricsInfo, (MediaLink) null, HarleyPlayer.this.getInitialPlaybackDelay(metricsInfo, mTSAttributes.getInitialPlaybackDelay(), "onStreamingTerminated"), HarleyPlayer.this.getPlaybackStartTime(metricsInfo, mTSAttributes.getInitialPlaybackDelay()), TimeUnit.SECONDS.toMillis(mTSAttributes.getDurationS().longValue()), mTSAttributes.getRebufferCount(), mTSAttributes.getTransferSpeedBPS() == null ? 0 : mTSAttributes.getTransferSpeedBPS().intValue(), 0.0f, HarleyPlayer.this.getTerminationReason(mTSAttributes), (PlaybackException) null, j);
                if (mTSEventCreateStreamingTerminatedEvent == null) {
                    return;
                }
                mTSEventCreateStreamingTerminatedEvent.addAttribute("cacheHitStatus", mTSAttributes.getCacheHitStatus());
                mTSEventCreateStreamingTerminatedEvent.addAttribute("streamOrDRMTech", mTSAttributes.getStreamOrDRMTech());
                mTSEventCreateStreamingTerminatedEvent.addAttribute("bitrates", mTSAttributes.getBitrateDescription());
                mTSEventCreateStreamingTerminatedEvent.addAttribute(PageUriUtils.SOURCE_QUERY_PARAM, mTSAttributes.getNetworkSource());
                mTSEventCreateStreamingTerminatedEvent.addAttribute("cdnCacheHitStatus", mTSAttributes.getCdnCacheHitStatus());
                if (mTSEventCreateStreamingTerminatedEvent instanceof SportStreamingTerminatedEvent) {
                    mTSEventCreateStreamingTerminatedEvent.addAttribute("mediaPlayerName", mTSAttributes.getMediaPlayerName());
                } else if (!(mTSEventCreateStreamingTerminatedEvent instanceof AdStreamingTerminatedEvent)) {
                    HarleyPlayer.this.addSubstituteAsin(mTSEventCreateStreamingTerminatedEvent, mTSAttributes);
                    if (mTSEventCreateStreamingTerminatedEvent instanceof StreamingTerminatedEvent) {
                        String cdnType = mTSAttributes.getCdnType();
                        if (!StringUtils.isEmpty(cdnType)) {
                            ((StreamingTerminatedEvent) mTSEventCreateStreamingTerminatedEvent).setCdnType(cdnType);
                        }
                    }
                    MediaItem mediaItem = (MediaItem) metricsInfo.get(PlaybackEvent.PROPERTY_MEDIA_ITEM);
                    if (ObjectUtils.isInterlude(mediaItem)) {
                        HarleyPlayer.this.updateEventForInterlude(mTSEventCreateStreamingTerminatedEvent, mediaItem);
                        if ("0".equalsIgnoreCase(mTSEventCreateStreamingTerminatedEvent.getEventAttributes().get("trackProgress"))) {
                            mTSEventCreateStreamingTerminatedEvent.addAttribute("trackProgress", mTSAttributes.getDurationS());
                        }
                    }
                } else {
                    String terminationReason = mTSAttributes.getTerminationReason();
                    if ("trackFinished".equalsIgnoreCase(terminationReason)) {
                        terminationReason = "contentFinished";
                    }
                    mTSEventCreateStreamingTerminatedEvent.addAttribute("terminationReason", terminationReason);
                    long positionMillis = Playback.getInstance().getPlaybackController(ControlSource.EXTERNAL).getPositionMillis();
                    long durationMillis = playRequest.getMediaItem().getDurationMillis();
                    EventTimeConverter eventTimeConverter = new EventTimeConverter();
                    if (positionMillis == 0) {
                        jConvertToRoundedSeconds = eventTimeConverter.convertToRoundedSeconds(durationMillis);
                    } else {
                        jConvertToRoundedSeconds = eventTimeConverter.convertToRoundedSeconds(positionMillis);
                    }
                    mTSEventCreateStreamingTerminatedEvent.addAttribute("entityProgressSeconds", jConvertToRoundedSeconds);
                }
                metricsSender.sendMetricsEvent(mTSEventCreateStreamingTerminatedEvent, metricsInfo);
            }

            @Override // com.amazon.music.media.playback.player.impl.HarleyWrapper.HarleyCallback
            public void onStreamingRequiredRebuffering(PlayRequest playRequest, float f, MTSAttributes mTSAttributes) {
                MTSMetricsSender metricsSender = playRequest.getMetricsSender();
                MTSEventFactory mTSEventFactory = HarleyPlayer.this.getPlaybackConfig().getMTSEventFactory();
                MetricsInfo metricsInfo = playRequest.getMetricsHandler().getMetricsInfo();
                metricsInfo.set(PlaybackEvent.PROPERTY_LOCAL, Boolean.valueOf(mTSAttributes.getLocalFile()));
                MTSEvent mTSEventCreateStreamingRequiredRebufferingEvent = mTSEventFactory.createStreamingRequiredRebufferingEvent(metricsInfo, null, mTSAttributes.getDurationMS().longValue(), mTSAttributes.getLoadTimeMilliseconds(), mTSAttributes.getTransferSpeedBPS() == null ? 0 : mTSAttributes.getTransferSpeedBPS().intValue(), 0.0f);
                if (mTSEventCreateStreamingRequiredRebufferingEvent == null) {
                    return;
                }
                mTSEventCreateStreamingRequiredRebufferingEvent.addAttribute("cacheHitStatus", mTSAttributes.getCacheHitStatus());
                mTSEventCreateStreamingRequiredRebufferingEvent.addAttribute("streamOrDRMTech", mTSAttributes.getStreamOrDRMTech());
                mTSEventCreateStreamingRequiredRebufferingEvent.addAttribute("bitrates", mTSAttributes.getBitrateDescription());
                mTSEventCreateStreamingRequiredRebufferingEvent.addAttribute(PageUriUtils.SOURCE_QUERY_PARAM, mTSAttributes.getNetworkSource());
                if (!(mTSEventCreateStreamingRequiredRebufferingEvent instanceof SportStreamingRequiredRebufferingEvent)) {
                    HarleyPlayer.this.addSubstituteAsin(mTSEventCreateStreamingRequiredRebufferingEvent, mTSAttributes);
                } else {
                    mTSEventCreateStreamingRequiredRebufferingEvent.addAttribute("mediaPlayerName", mTSAttributes.getMediaPlayerName());
                }
                metricsSender.sendMetricsEvent(mTSEventCreateStreamingRequiredRebufferingEvent);
            }

            @Override // com.amazon.music.media.playback.player.impl.HarleyWrapper.HarleyCallback
            public void onDurationChanged(PlayRequest playRequest, long j) {
                MediaItem mediaItem = playRequest.getMediaItem();
                if (mediaItem != null) {
                    mediaItem.setDurationMillis(j);
                    HarleyPlayer.this.notifyMetadataChanged(mediaItem);
                }
            }
        };
        this.harleyCallback = harleyCallback;
        GainControl gainControl = new GainControl() { // from class: com.amazon.music.media.playback.player.impl.HarleyPlayer.3
            @Override // com.amazon.music.media.playback.audioeffects.GainControl
            public int getMinGainMillibels() {
                if (HarleyPlayer.this.engine.getBassSettings() != null) {
                    return HarleyPlayer.this.engine.getBassSettings().getGainLowerLimit();
                }
                return Integer.MIN_VALUE;
            }

            @Override // com.amazon.music.media.playback.audioeffects.GainControl
            public int getMaxGainMillibels() {
                if (HarleyPlayer.this.engine.getBassSettings() != null) {
                    return HarleyPlayer.this.engine.getBassSettings().getGainUpperLimit();
                }
                return Integer.MIN_VALUE;
            }

            @Override // com.amazon.music.media.playback.audioeffects.GainControl
            public int getGainMillibels() {
                if (HarleyPlayer.this.engine.getBassSettings() != null) {
                    return HarleyPlayer.this.engine.getBassSettings().getGainMillibel();
                }
                return Integer.MIN_VALUE;
            }

            @Override // com.amazon.music.media.playback.audioeffects.GainControl
            public void setGainMillibels(int i) {
                HarleyPlayer.this.engine.setBassGainMillibels(i);
            }
        };
        this.bassController = gainControl;
        GainControl gainControl2 = new GainControl() { // from class: com.amazon.music.media.playback.player.impl.HarleyPlayer.4
            @Override // com.amazon.music.media.playback.audioeffects.GainControl
            public int getMinGainMillibels() {
                if (HarleyPlayer.this.engine.getTrebleSettings() != null) {
                    return HarleyPlayer.this.engine.getTrebleSettings().getGainLowerLimit();
                }
                return Integer.MIN_VALUE;
            }

            @Override // com.amazon.music.media.playback.audioeffects.GainControl
            public int getMaxGainMillibels() {
                if (HarleyPlayer.this.engine.getTrebleSettings() != null) {
                    return HarleyPlayer.this.engine.getTrebleSettings().getGainUpperLimit();
                }
                return Integer.MIN_VALUE;
            }

            @Override // com.amazon.music.media.playback.audioeffects.GainControl
            public int getGainMillibels() {
                if (HarleyPlayer.this.engine.getTrebleSettings() != null) {
                    return HarleyPlayer.this.engine.getTrebleSettings().getGainMillibel();
                }
                return Integer.MIN_VALUE;
            }

            @Override // com.amazon.music.media.playback.audioeffects.GainControl
            public void setGainMillibels(int i) {
                HarleyPlayer.this.engine.setTrebleGainMillibels(i);
            }
        };
        this.trebleController = gainControl2;
        this.engine = new HarleyWrapper<>(context, harleyCallback);
        this.audioEffectsControl = new BasicAudioEffectsController(gainControl, gainControl2, getEqualizerBandControlList());
    }

    @Override // com.amazon.music.media.playback.player.AbstractPlayer, com.amazon.music.media.playback.player.Player, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        this.logger.info("close()");
        this.engine.close();
    }

    @Override // com.amazon.music.media.playback.player.AbstractPlayer
    protected void onCurrentVolumeChanged(float f) {
        this.engine.setVolume(f);
    }

    @Override // com.amazon.music.media.playback.player.Player
    public void seek(long j, ChangeReason changeReason, ControlSource controlSource) {
        if (getCurrentPlayRequest() != null) {
            setPlayRequestStatus(getCurrentPlayRequest(), getCurrentPlayRequest().getPlayRequestStatus().onSeek(), getPositionMillis(), Clock.now(), -1.0f, null, changeReason, controlSource);
            getCurrentPlayRequest().getPositionTracker().pause(j);
            if (changeReason == ChangeReason.RESTART) {
                setHarleyQueue(getCurrentIndex());
            } else {
                this.engine.seekTo(j);
            }
            updatePlayStatus(changeReason, controlSource);
        }
    }

    @Override // com.amazon.music.media.playback.player.AbstractPlayer
    protected void onConnect() {
        this.logger.info("onConnect()");
        onConfigurationChanged();
        this.engine.setIgnoreTrackStreamed(false);
    }

    @Override // com.amazon.music.media.playback.player.AbstractPlayer
    protected void onDisconnect() {
        this.logger.info("onDisconnect()");
        this.engine.setIgnoreTrackStreamed(true);
    }

    @Override // com.amazon.music.media.playback.player.AbstractPlayer
    protected void doPlay(ChangeReason changeReason, ControlSource controlSource, long j) {
        this.logger.info("doPlay() - currentRequest: " + getCurrentPlayRequest());
        PlayRequest currentPlayRequest = getCurrentPlayRequest();
        if (currentPlayRequest != null) {
            setPlayRequestStatus(currentPlayRequest, currentPlayRequest.getPlayRequestStatus().hasStarted() ? PlayRequestStatus.BUFFERING : PlayRequestStatus.PREPARING, getPositionMillis(), Clock.now(), -1.0f, null, changeReason, controlSource);
            this.engine.play(this.deviceToTerminate);
            this.deviceToTerminate = null;
            updatePlayStatus(changeReason, controlSource);
            return;
        }
        setPlayStatus(PlayStatus.LOADING, null, changeReason, controlSource, j);
    }

    @Override // com.amazon.music.media.playback.player.AbstractPlayer
    protected void doPause(boolean z, ChangeReason changeReason, ControlSource controlSource) {
        this.logger.info("doPause(" + z + ")");
        if (!z) {
            this.engine.pause();
        }
        PlayRequest currentPlayRequest = getCurrentPlayRequest();
        if (currentPlayRequest == null || !currentPlayRequest.getPlayRequestStatus().shouldBePlaying()) {
            return;
        }
        setPlayRequestStatus(currentPlayRequest, currentPlayRequest.getPlayRequestStatus().onPause(), getPositionMillis(), Clock.now(), -1.0f, null, changeReason, controlSource);
    }

    @Override // com.amazon.music.media.playback.player.AbstractPlayer, com.amazon.music.media.playback.player.Player
    @NonNull
    public PlaybackAttributes getCurrentPlaybackAttributes() {
        return this.engine.getCurrentPlaybackAttributes();
    }

    @Override // com.amazon.music.media.playback.player.AbstractPlayer, com.amazon.music.media.playback.player.Player
    public boolean isStreaming() {
        return !this.engine.isDownloaded();
    }

    @Override // com.amazon.music.media.playback.player.AbstractPlayer, com.amazon.music.media.playback.player.Player
    @Nullable
    public PlayerCapabilities getPlayerCapabilities() {
        return this.engine.getPlayerCapabilities();
    }

    public void setCustomerBenefits(EnumSet<CustomerBenefitFlags> enumSet) {
        this.engine.setCustomerBenefits(enumSet);
    }

    @Override // com.amazon.music.media.playback.player.AbstractPlayer
    protected void onConfigurationChanged() {
        this.logger.info("onConfigurationChanged()");
        super.onConfigurationChanged();
        PlaybackConfig playbackConfig = getPlaybackConfig();
        BitRateSelection bitRateSelectionForWifi = getBitRateSelectionForWifi();
        if (this.bitRateSelectionForWifi != bitRateSelectionForWifi && bitRateSelectionForWifi != null) {
            this.bitRateSelectionForWifi = bitRateSelectionForWifi;
            this.engine.setBitRateSelectionForWifi(bitRateSelectionForWifi);
        }
        BitRateSelection bitRateSelectionForOTA = getBitRateSelectionForOTA();
        if (this.bitRateSelectionForOTA != bitRateSelectionForOTA && bitRateSelectionForOTA != null) {
            this.bitRateSelectionForOTA = bitRateSelectionForOTA;
            this.engine.setBitRateSelectionForOTA(bitRateSelectionForOTA);
        }
        boolean zIsSpatialEnabled = playbackConfig.isSpatialEnabled();
        if (zIsSpatialEnabled != this.engine.threeDMode()) {
            this.engine.setThreeDMode(zIsSpatialEnabled);
        }
        boolean zIsLoudnessNormalizationEnabled = playbackConfig.isLoudnessNormalizationEnabled();
        if (zIsLoudnessNormalizationEnabled != this.engine.isLoudnessNormalizationEnabled()) {
            this.engine.setLoudnessNormalizationEnabled(zIsLoudnessNormalizationEnabled);
        }
        if (this.setStreamOnWifiOnly != playbackConfig.isStreamOnWifiOnly()) {
            boolean zIsStreamOnWifiOnly = playbackConfig.isStreamOnWifiOnly();
            this.setStreamOnWifiOnly = zIsStreamOnWifiOnly;
            this.engine.setStreamOnWifiOnly(zIsStreamOnWifiOnly);
        }
        PlayerAuthentication playerAuthentication = playbackConfig.getPlayerAuthentication();
        if (this.playerAuthentication != playerAuthentication) {
            this.playerAuthentication = playerAuthentication;
            this.engine.setPlayerAuthentication(playerAuthentication);
        }
        boolean zIsHarleyDebugModeEnabled = playbackConfig.isHarleyDebugModeEnabled();
        boolean z = zIsHarleyDebugModeEnabled != this.engine.isHarleyDebugModeEnabled();
        ClientIdentification clientIdentification = playbackConfig.getClientIdentification();
        if (clientIdentification != null) {
            if (this.clientIdentification != clientIdentification || z) {
                updateConfiguration(clientIdentification, zIsHarleyDebugModeEnabled);
            }
        }
    }

    @Override // com.amazon.music.media.playback.player.AbstractPlayer
    protected void onQueueChanged(int i, int i2, int i3, int i4, int i5, ChangeReason changeReason, ControlSource controlSource) {
        PlayRequest playRequest;
        this.logger.info("onQueueChanged(" + i + ", " + i2 + ", " + i3 + ", " + i4 + ", " + i5 + ", " + changeReason + ", " + controlSource + ")");
        PlayRequest currentPlayRequest = getCurrentPlayRequest();
        PlayRequest currentRequest = this.engine.getCurrentRequest();
        if (currentPlayRequest == currentRequest || currentRequest == null) {
            playRequest = currentPlayRequest;
        } else {
            playRequest = currentPlayRequest;
            setPlayRequestStatus(currentRequest, PlayRequestStatus.TERMINATED, currentRequest.getPositionTracker().getCurrentPosition(), Clock.now(), -1.0f, null, changeReason, controlSource);
        }
        if (playRequest == null) {
            return;
        }
        int iIndexOf = this.engine.getRequestQueue().indexOf(getCurrentPlayRequest());
        if (iIndexOf < 0) {
            setHarleyQueue(i2);
            iIndexOf = 0;
        } else if ((i5 > 0 || i4 > 0) && i2 < i3 + i5 && !isLoopMediaItem()) {
            int iMax = Math.max(i2, i3 - 1);
            int numPlayRequests = (getNumPlayRequests() - iMax) - 1;
            ArrayList arrayList = new ArrayList(numPlayRequests);
            for (int i6 = 1; i6 <= numPlayRequests; i6++) {
                arrayList.add(getPlayRequest(iMax + i6));
            }
            if (iMax > getNumPlayRequests()) {
                iMax = getNumPlayRequests() - 1;
            }
            this.engine.addToQueue(getPlayRequest(iMax), arrayList);
        }
        if (iIndexOf > 0) {
            this.engine.skipTo(getCurrentPlayRequest());
        }
        if (updateCurrentIndex(null, changeReason, controlSource)) {
            return;
        }
        updatePlayRequestStatus();
        updatePlayStatus(changeReason, controlSource);
    }

    private void setHarleyQueue(int i) {
        ArrayList arrayList;
        int i2 = 0;
        if (isLoopMediaItem()) {
            arrayList = new ArrayList(5);
            while (i2 < 5) {
                arrayList.add(getCurrentPlayRequest());
                i2++;
            }
        } else {
            int numPlayRequests = getNumPlayRequests() - i;
            ArrayList arrayList2 = new ArrayList(numPlayRequests);
            while (i2 < numPlayRequests) {
                arrayList2.add(getPlayRequest(i + i2));
                i2++;
            }
            arrayList = arrayList2;
        }
        this.engine.setQueue(arrayList, getPlayStatus().shouldBePlaying());
    }

    @Override // com.amazon.music.media.playback.player.AbstractPlayer, com.amazon.music.media.playback.player.Player
    public void setLoopMediaItem(boolean z) {
        ArrayList arrayList;
        if (isLoopMediaItem() != z) {
            this.logger.info("setLoopMediaItem(" + z + ")");
            super.setLoopMediaItem(z);
            PlayRequest currentRequest = this.engine.getCurrentRequest();
            if (currentRequest == null) {
                return;
            }
            if (z) {
                arrayList = new ArrayList(4);
                for (int i = 1; i < 5; i++) {
                    arrayList.add(currentRequest);
                }
            } else {
                int numPlayRequests = getNumPlayRequests();
                ArrayList arrayList2 = new ArrayList(Math.max(0, (getNumPlayRequests() - getCurrentIndex()) - 1));
                for (int currentIndex = getCurrentIndex() + 1; currentIndex < numPlayRequests; currentIndex++) {
                    arrayList2.add(getPlayRequest(currentIndex));
                }
                arrayList = arrayList2;
            }
            this.engine.addToQueue(1, arrayList);
        }
    }

    @Override // com.amazon.music.media.playback.player.AbstractPlayer, com.amazon.music.media.playback.player.Player
    public AudioEffectsControl getAudioEffectsController() {
        return this.audioEffectsControl;
    }

    @Override // com.amazon.music.media.playback.player.AbstractPlayer, com.amazon.music.media.playback.player.Player
    public ConcurrencyProvider getConcurrencyProvider() {
        if (this.concurrencyProvider == null) {
            this.concurrencyProvider = new ConcurrencyProvider() { // from class: com.amazon.music.media.playback.player.impl.HarleyPlayer.1
                @Override // com.amazon.music.media.playback.concurrency.ConcurrencyProvider
                public boolean shouldUpdateInBackground() {
                    return false;
                }

                @Override // com.amazon.music.media.playback.concurrency.ConcurrencyProvider
                public List<ConcurrentDeviceInfo> updatePlayStatus(int i, MediaItem mediaItem, long j, String str) throws IOException {
                    if (str == null) {
                        return null;
                    }
                    HarleyPlayer.this.deviceToTerminate = str;
                    return null;
                }
            };
        }
        return this.concurrencyProvider;
    }

    @Override // com.amazon.music.media.playback.player.AbstractPlayer, com.amazon.music.media.playback.player.Player
    public void setPlaybackSpeed(double d) {
        HarleyWrapper<PlayRequest> harleyWrapper = this.engine;
        if (harleyWrapper != null) {
            harleyWrapper.setPlaybackSpeed(d);
        }
    }

    @Override // com.amazon.music.media.playback.player.AbstractPlayer, com.amazon.music.media.playback.player.Player
    public void setPlaybackMetricsListener(OnPlaybackMetricsListener onPlaybackMetricsListener) {
        HarleyWrapper<PlayRequest> harleyWrapper = this.engine;
        if (harleyWrapper != null) {
            harleyWrapper.setPlaybackMetricsListener(onPlaybackMetricsListener);
        }
    }

    @Override // com.amazon.music.media.playback.player.AbstractPlayer, com.amazon.music.media.playback.player.Player
    public void setOnFrameChangedListener(OnFrameChangedListener onFrameChangedListener) {
        HarleyWrapper<PlayRequest> harleyWrapper = this.engine;
        if (harleyWrapper != null) {
            harleyWrapper.setOnFrameChangedListener(onFrameChangedListener);
        }
    }

    @Override // com.amazon.music.media.playback.player.AbstractPlayer, com.amazon.music.media.playback.player.Player
    public void setOnPrebufferChangedListener(OnPrebufferChangedListener onPrebufferChangedListener) {
        HarleyWrapper<PlayRequest> harleyWrapper = this.engine;
        if (harleyWrapper != null) {
            harleyWrapper.setOnPrebufferChangedListener(onPrebufferChangedListener);
        }
    }

    public void setPrioritizeDownloadsPlayback(boolean z) {
        HarleyWrapper<PlayRequest> harleyWrapper = this.engine;
        if (harleyWrapper != null) {
            harleyWrapper.setPrioritizeDownloadsPlayback(z);
        }
    }

    public File getCrashDirectory() {
        return this.engine.getCrashDirectory();
    }

    @Override // com.amazon.music.media.playback.player.Player
    public boolean canPlay(Uri uri) {
        return this.engine.canPlay(uri);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addMissingLoopItems() {
        int size;
        PlayRequest currentPlayRequest = getCurrentPlayRequest();
        if (currentPlayRequest != null && (size = this.engine.getRequestQueue().size()) < 5) {
            ArrayList arrayList = new ArrayList(5 - size);
            for (int i = size; i < 5; i++) {
                arrayList.add(currentPlayRequest);
            }
            this.engine.addToQueue(size, arrayList);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean updateCurrentIndex(PlaybackException playbackException, ChangeReason changeReason, ControlSource controlSource) {
        int iIndexOf = indexOf(this.engine.getCurrentRequest());
        if (iIndexOf < 0) {
            iIndexOf = getNumPlayRequests();
        }
        int i = iIndexOf;
        if (i == getCurrentIndex()) {
            return false;
        }
        setCurrentIndex(i, playbackException, changeReason, Clock.now());
        updatePlayRequestStatus();
        updatePlayStatus(changeReason, controlSource);
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updatePlayRequestStatus() {
        PlayRequestStatus playRequestStatusOnPause;
        PlayRequest currentPlayRequest = getCurrentPlayRequest();
        if (currentPlayRequest == null) {
            return;
        }
        int i = AnonymousClass6.$SwitchMap$com$amazon$music$media$playback$player$impl$HarleyWrapper$Status[this.engine.getStatus().ordinal()];
        if (i == 1) {
            playRequestStatusOnPause = PlayRequestStatus.RENDERING;
        } else if (i == 2 || i == 3) {
            playRequestStatusOnPause = currentPlayRequest.getPlayRequestStatus().onPause();
        } else if (i == 4) {
            playRequestStatusOnPause = currentPlayRequest.getPlayRequestStatus().onShouldPlay().onBuffering();
        } else {
            StrictMode.crashIfStrict("Unknown status: " + this.engine.getStatus());
            return;
        }
        setPlayRequestStatus(currentPlayRequest, playRequestStatusOnPause, currentPlayRequest.getPositionTracker().getCurrentPosition(), Clock.now(), -1.0f, null, null, null);
    }

    /* JADX INFO: renamed from: com.amazon.music.media.playback.player.impl.HarleyPlayer$6, reason: invalid class name */
    static /* synthetic */ class AnonymousClass6 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$music$media$playback$player$impl$HarleyWrapper$Status;

        static {
            int[] iArr = new int[HarleyWrapper.Status.values().length];
            $SwitchMap$com$amazon$music$media$playback$player$impl$HarleyWrapper$Status = iArr;
            try {
                iArr[HarleyWrapper.Status.RENDERING.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$music$media$playback$player$impl$HarleyWrapper$Status[HarleyWrapper.Status.PAUSED.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$music$media$playback$player$impl$HarleyWrapper$Status[HarleyWrapper.Status.UNSTARTED.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$amazon$music$media$playback$player$impl$HarleyWrapper$Status[HarleyWrapper.Status.PENDING.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    private void updateConfiguration(@NonNull ClientIdentification clientIdentification, boolean z) {
        this.clientIdentification = clientIdentification;
        DMLSConfig dMLSConfigCreateDMLSConfig = createDMLSConfig(clientIdentification);
        ClientInfo clientInfoCreateClientInfo = createClientInfo(clientIdentification);
        PlaybackConfig playbackConfig = getPlaybackConfig();
        try {
            this.engine.updateConfiguration(dMLSConfigCreateDMLSConfig, clientInfoCreateClientInfo, playbackConfig.getStorageDir(), playbackConfig.getCacheDir(), Boolean.valueOf(z));
        } catch (PlaybackException e) {
            StrictMode.crashIfStrict(e);
        }
    }

    private DMLSConfig createDMLSConfig(@NonNull ClientIdentification clientIdentification) {
        return new DMLSConfig(clientIdentification.getCustomerID(), clientIdentification.getDeviceTypeID(), clientIdentification.getDeviceID(), clientIdentification.getAppVersion(), "", clientIdentification.getTerritoryID(), clientIdentification.useGammaEndpoints());
    }

    private ClientInfo createClientInfo(@NonNull ClientIdentification clientIdentification) {
        return new ClientInfo(clientIdentification.getAppVersion(), clientIdentification.getOsVersion(), "", "");
    }

    private long getTimePlaybackExpected(MetricsInfo metricsInfo) {
        List<MetricsEvent> eventsSnapshot = metricsInfo.getEventsSnapshot();
        ListIterator<MetricsEvent> listIterator = eventsSnapshot.listIterator(eventsSnapshot.size());
        while (listIterator.hasPrevious()) {
            MetricsEvent metricsEventPrevious = listIterator.previous();
            if (metricsEventPrevious.getEventType() == PlaybackEventType.EVENT_PLAYBACK_EXPECTED) {
                return metricsEventPrevious.getEventTimeMillis();
            }
        }
        return 0L;
    }

    private long getTimeMediaItemKnown(MetricsInfo metricsInfo) {
        for (MetricsEvent metricsEvent : metricsInfo.getEventsSnapshot()) {
            if (metricsEvent.getEventType() == PlaybackEventType.EVENT_MEDIA_ITEM_KNOWN) {
                return metricsEvent.getEventTimeMillis();
            }
        }
        return 0L;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public long getInitialPlaybackDelay(MetricsInfo metricsInfo, long j, String str) {
        long timeMediaItemKnown = getTimeMediaItemKnown(metricsInfo);
        long timePlaybackExpected = getTimePlaybackExpected(metricsInfo);
        long j2 = 0;
        if (timeMediaItemKnown != 0 && timePlaybackExpected != 0 && timePlaybackExpected <= timeMediaItemKnown) {
            j2 = timeMediaItemKnown - timePlaybackExpected;
        }
        long j3 = j2 + j;
        this.logger.debug(str + " - timeKnown: " + timeMediaItemKnown + ", timeExpected: " + timePlaybackExpected);
        this.logger.debug(str + " - loadItemDelay: " + j2 + ", harleyIPD: " + j + ", totalIPD: " + j3);
        this.logger.debug(metricsInfo.toString());
        return j3;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public long getPlaybackStartTime(MetricsInfo metricsInfo, long j) {
        long timeMediaItemKnown = getTimeMediaItemKnown(metricsInfo);
        long timePlaybackExpected = getTimePlaybackExpected(metricsInfo);
        if (timeMediaItemKnown == 0 || timePlaybackExpected == 0) {
            return 0L;
        }
        long jMax = Math.max(timeMediaItemKnown, timePlaybackExpected);
        Logger logger = this.logger;
        StringBuilder sb = new StringBuilder();
        sb.append("playbackStartTime: ");
        long j2 = jMax + j;
        sb.append(j2);
        sb.append(" - requestedStartTime: ");
        sb.append(jMax);
        sb.append(", harleyIpd: ");
        sb.append(j);
        logger.debug(sb.toString());
        this.logger.debug("now-playbackStartTime=" + (Clock.now() - j2));
        return j2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public TerminationReason getTerminationReason(MTSAttributes mTSAttributes) {
        TerminationReason terminationReason = TerminationReason.USER_STOP;
        String terminationReason2 = mTSAttributes.getTerminationReason();
        if (StringUtil.isNullOrEmpty(terminationReason2)) {
            this.logger.warn(String.format("Can't parse the Termination Reason from the MTS Attributes: attribute it is null or empty. Defaulting to %s", terminationReason.getMetricsValue()));
            return terminationReason;
        }
        for (TerminationReason terminationReason3 : TerminationReason.values()) {
            if (terminationReason3.getMetricsValue().equals(terminationReason2)) {
                return terminationReason3;
            }
        }
        this.logger.warn(String.format("Can't parse the Termination Reason from the MTS Attributes: value %s is invalid. Defaulting to %s", terminationReason2, terminationReason.getMetricsValue()));
        return terminationReason;
    }

    @NonNull
    private List<EqualizerBandControl> getEqualizerBandControlList() {
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < this.engine.getEqualizerBandCount(); i++) {
            arrayList.add(getEqualizerBandControl(i));
        }
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean hasLocalFile(MediaItem mediaItem) {
        MediaItemId mediaItemId;
        if (mediaItem.isOwned() && (mediaItemId = mediaItem.getMediaItemId(MediaItemId.Type.LOCAL_URI)) != null) {
            return new File(mediaItemId.getUri().getPath()).exists();
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean isDownloadedByAsin(MediaItem mediaItem) {
        MediaItemId mediaItemId;
        return (this.downloadCoordinator == null || (mediaItemId = mediaItem.getMediaItemId(MediaItemId.Type.ASIN)) == null || this.downloadCoordinator.isAvailableOffline(mediaItemId.getUri().toString()) == null) ? false : true;
    }

    private EqualizerBandControl getEqualizerBandControl(final int i) {
        return new EqualizerBandControl() { // from class: com.amazon.music.media.playback.player.impl.HarleyPlayer.5
            @Override // com.amazon.music.media.playback.audioeffects.EqualizerBandControl
            public int getBandIndex() {
                EqualizerBand band = getBand(i);
                if (band != null) {
                    return band.getBandIndex();
                }
                return Integer.MIN_VALUE;
            }

            @Override // com.amazon.music.media.playback.audioeffects.EqualizerBandControl
            public int getFrequencyHz() {
                EqualizerBand band = getBand(i);
                if (band != null) {
                    return band.getFrequency();
                }
                return Integer.MIN_VALUE;
            }

            @Override // com.amazon.music.media.playback.audioeffects.EqualizerBandControl
            public float getWidthHz() {
                EqualizerBand band = getBand(i);
                if (band != null) {
                    return band.getBandWidth();
                }
                return -2.1474836E9f;
            }

            @Override // com.amazon.music.media.playback.audioeffects.EqualizerBandControl, com.amazon.music.media.playback.audioeffects.GainControl
            public int getMinGainMillibels() {
                if (HarleyPlayer.this.engine.getEqualizerSettings() != null) {
                    return HarleyPlayer.this.engine.getEqualizerSettings().getGainLowerLimit();
                }
                return Integer.MIN_VALUE;
            }

            @Override // com.amazon.music.media.playback.audioeffects.EqualizerBandControl, com.amazon.music.media.playback.audioeffects.GainControl
            public int getMaxGainMillibels() {
                if (HarleyPlayer.this.engine.getEqualizerSettings() != null) {
                    return HarleyPlayer.this.engine.getEqualizerSettings().getGainUpperLimit();
                }
                return Integer.MIN_VALUE;
            }

            @Override // com.amazon.music.media.playback.audioeffects.EqualizerBandControl, com.amazon.music.media.playback.audioeffects.GainControl
            public int getGainMillibels() {
                EqualizerBand band = getBand(i);
                if (band != null) {
                    return band.getGain();
                }
                return Integer.MIN_VALUE;
            }

            @Override // com.amazon.music.media.playback.audioeffects.EqualizerBandControl, com.amazon.music.media.playback.audioeffects.GainControl
            public void setGainMillibels(int i2) {
                HarleyPlayer.this.engine.setEqualizerBandGain(i, i2);
            }

            @Nullable
            private EqualizerBand getBand(int i2) {
                EqualizerSettings equalizerSettings = HarleyPlayer.this.engine.getEqualizerSettings();
                if (equalizerSettings == null || i2 < 0 || i2 >= equalizerSettings.getEqualizerBandList().size()) {
                    return null;
                }
                return equalizerSettings.getEqualizerBandList().get(i2);
            }
        };
    }

    public DownloadCoordinator getDownloadCoordinator(DownloadCoordinatorListener downloadCoordinatorListener, DownloadCoordinatorConfig downloadCoordinatorConfig) {
        DownloadCoordinator downloadCoordinator = this.engine.getDownloadCoordinator(downloadCoordinatorListener, downloadCoordinatorConfig);
        this.downloadCoordinator = downloadCoordinator;
        return downloadCoordinator;
    }

    @Override // com.amazon.music.media.playback.player.AbstractPlayer, com.amazon.music.media.playback.player.Player
    public void clearData() {
        this.engine.clearData();
    }

    public void clearCache() {
        this.engine.clearCache();
    }

    public String getVersion() {
        return this.engine.getVersion();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addSubstituteAsin(MTSEvent mTSEvent, MTSAttributes mTSAttributes) {
        String substituteAsin = mTSAttributes.getSubstituteAsin();
        if (StringUtils.isEmpty(substituteAsin)) {
            return;
        }
        mTSEvent.addAttribute("substituteAsin", substituteAsin);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateEventForInterlude(MTSEvent mTSEvent, MediaItem mediaItem) {
        mTSEvent.addAttribute(Splash.PARAMS_DEVICE_ID, this.clientIdentification.getDeviceID());
        mTSEvent.addAttribute(Splash.PARAMS_DEVICE_TYPE, this.clientIdentification.getDeviceTypeID());
        mTSEvent.addAttribute("linkedAmznAccountID", this.clientIdentification.getCustomerID());
        mTSEvent.addAttribute("customerServiceTier", this.clientIdentification.getCustomerServiceTier());
        mTSEvent.addAttribute("playbackEntityType", "interlude");
        mTSEvent.addAttribute("playbackExperienceMode", getPlaybackConfig().getPlaybackExperienceMode().getLabel());
        MediaCollectionInfo sourceMediaCollectionInfo = mediaItem.getSourceMediaCollectionInfo();
        if (!(mediaItem instanceof StationMediaItem)) {
            MediaCollectionId.Type type = MediaCollectionId.Type.CUSTOM;
            if (sourceMediaCollectionInfo.getId(type) != null) {
                mTSEvent.addAttribute(ContextMappingConstants.ENTITY_ID, sourceMediaCollectionInfo.getId(type).getId());
            }
            mTSEvent.addAttribute(ContextMappingConstants.ENTITY_ID_TYPE, "INTERLUDE_ID");
        }
        if (sourceMediaCollectionInfo != null) {
            MediaCollectionId.Type type2 = MediaCollectionId.Type.STATION_INSTANCE_ID;
            if (sourceMediaCollectionInfo.getId(type2) != null) {
                mTSEvent.addAttribute("selectionSourceSubType", sourceMediaCollectionInfo.getId(type2).getId());
            }
        }
        if ("trackFinished".equalsIgnoreCase(mTSEvent.getEventAttributes().get("terminationReason"))) {
            mTSEvent.addAttribute("terminationReason", "entityFinished");
        }
    }

    public void nativeCrash() {
        this.engine.nativeCrash();
    }
}
