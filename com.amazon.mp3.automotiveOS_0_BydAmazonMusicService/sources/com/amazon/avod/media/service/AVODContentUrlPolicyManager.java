package com.amazon.avod.media.service;

import com.amazon.avod.content.ContentException;
import com.amazon.avod.content.ContentSession;
import com.amazon.avod.content.ContentSessionContext;
import com.amazon.avod.content.ContentSessionType;
import com.amazon.avod.content.config.PlaybackResourcesV2Config;
import com.amazon.avod.content.urlvending.AudioVideoUrls;
import com.amazon.avod.content.urlvending.ContentUrlFetcher;
import com.amazon.avod.event.PlaybackEventTransport;
import com.amazon.avod.media.TimeSpan;
import com.amazon.avod.media.contentcache.VideoCacheManager;
import com.amazon.avod.media.framework.error.MediaException;
import com.amazon.avod.media.playback.VideoSpecification;
import com.amazon.avod.media.playback.support.RendererScheme;
import com.amazon.avod.playback.PlaybackEventReporter;
import com.amazon.avod.playback.event.PlaybackRightsValidatedEvent;
import com.amazon.avod.playback.event.playback.RetriablePlaybackErrorEvent;
import com.amazon.avod.playback.renderer.RendererSchemeType;
import com.amazon.avod.pmet.WhisperCacheMetrics$CacheEviction;
import com.amazon.avod.util.DLog;
import com.amazon.video.sdk.player.PlaybackEnvelope;
import com.amazon.video.sdk.player.PlaybackEnvelopeValidator;
import com.google.common.base.Preconditions;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public class AVODContentUrlPolicyManager implements ContentUrlFetcher {
    private final VideoCacheManager mCacheManager;
    private final PlaybackEventTransport mEventBus;
    private final PlaybackEnvelopeValidator mPlaybackEnvelopeValidator;
    private final PlaybackResourcesV2Config mPlaybackResourcesV2Config;
    private final ContentUrlFetcher mUrlFetcher;
    private final AtomicBoolean mPlaybackLaunched = new AtomicBoolean(false);
    private final AtomicBoolean mHaveValidated = new AtomicBoolean(false);

    public AVODContentUrlPolicyManager(@Nonnull ContentUrlFetcher contentUrlFetcher, @Nonnull PlaybackEventTransport playbackEventTransport, @Nonnull VideoCacheManager videoCacheManager, @Nonnull PlaybackEnvelopeValidator playbackEnvelopeValidator, @Nonnull PlaybackResourcesV2Config playbackResourcesV2Config) {
        this.mUrlFetcher = (ContentUrlFetcher) Preconditions.checkNotNull(contentUrlFetcher, "urlFetcher");
        this.mEventBus = (PlaybackEventTransport) Preconditions.checkNotNull(playbackEventTransport, "eventBus");
        this.mCacheManager = (VideoCacheManager) Preconditions.checkNotNull(videoCacheManager, "cacheManager");
        this.mPlaybackEnvelopeValidator = (PlaybackEnvelopeValidator) Preconditions.checkNotNull(playbackEnvelopeValidator, "playbackEnvelopeValidator");
        this.mPlaybackResourcesV2Config = (PlaybackResourcesV2Config) Preconditions.checkNotNull(playbackResourcesV2Config, "playbackResourcesV2Config");
    }

    public void notifyPlaybackLaunched() {
        this.mPlaybackLaunched.set(true);
    }

    @Override // com.amazon.avod.content.urlvending.ContentUrlFetcher
    @Nonnull
    public AudioVideoUrls getAudioVideoUrls(@Nonnull VideoSpecification videoSpecification, @Nonnull Map<String, String> map, @Nonnull ContentSessionType contentSessionType, @Nullable String str, @Nullable PlaybackEventReporter playbackEventReporter, @Nullable RendererSchemeType rendererSchemeType, @Nonnull RendererScheme rendererScheme, boolean z) throws ContentException {
        if (contentSessionType == ContentSessionType.STREAMING && !videoSpecification.isTrailer()) {
            return getWithProperSessionType(videoSpecification, map, str, playbackEventReporter, rendererSchemeType, rendererScheme, z);
        }
        return this.mUrlFetcher.getAudioVideoUrls(videoSpecification, map, contentSessionType, str, playbackEventReporter, rendererSchemeType, rendererScheme, z);
    }

    private AudioVideoUrls getWithProperSessionType(VideoSpecification videoSpecification, Map<String, String> map, String str, PlaybackEventReporter playbackEventReporter, RendererSchemeType rendererSchemeType, @Nonnull RendererScheme rendererScheme, boolean z) throws ContentException {
        ContentSessionType contentSessionType;
        if (videoSpecification.isLiveStream() || this.mPlaybackLaunched.get()) {
            contentSessionType = ContentSessionType.STREAMING;
        } else {
            contentSessionType = ContentSessionType.CONTENT_CACHE;
        }
        try {
            return makeServiceCall(videoSpecification, map, contentSessionType, str, playbackEventReporter, rendererSchemeType, rendererScheme, z);
        } catch (ContentException e) {
            if (isLoadShedFailure(e) && this.mPlaybackLaunched.get()) {
                DLog.warnf("Detected service call load-shed error after playback launched; retrying: %s", e);
                return makeServiceCall(videoSpecification, map, ContentSessionType.STREAMING, str, playbackEventReporter, rendererSchemeType, rendererScheme, z);
            }
            throw e;
        }
    }

    private boolean isLoadShedFailure(@Nonnull ContentException contentException) {
        return contentException.getErrorCode() == ContentException.ContentError.SERVICE_CALL_LOAD_SHED;
    }

    private AudioVideoUrls makeServiceCall(@Nonnull VideoSpecification videoSpecification, @Nonnull Map<String, String> map, @Nonnull ContentSessionType contentSessionType, @Nonnull String str, @Nonnull PlaybackEventReporter playbackEventReporter, @Nullable RendererSchemeType rendererSchemeType, @Nonnull RendererScheme rendererScheme, boolean z) throws ContentException {
        AudioVideoUrls audioVideoUrls = this.mUrlFetcher.getAudioVideoUrls(videoSpecification, map, contentSessionType, str, playbackEventReporter, rendererSchemeType, rendererScheme, z);
        if (isValidationSufficient(videoSpecification, contentSessionType, z) && !this.mHaveValidated.getAndSet(true)) {
            this.mEventBus.postEvent(new PlaybackRightsValidatedEvent(audioVideoUrls.getContentUrls().get(0).getSessionId(), audioVideoUrls.getSessionToken()));
        }
        return audioVideoUrls;
    }

    public void validate(ContentSession contentSession, Map<String, String> map, String str, PlaybackEventReporter playbackEventReporter, RendererSchemeType rendererSchemeType, @Nonnull RendererScheme rendererScheme) throws MediaException {
        Preconditions.checkState(this.mPlaybackLaunched.get(), "validate should not be called before notifyPlaybackLaunched.");
        if (this.mHaveValidated.get()) {
            return;
        }
        ContentSessionContext context = contentSession.getContext();
        ContentSessionType sessionType = context.getSessionType();
        VideoSpecification specification = context.getSpecification();
        boolean zIsStreamingSession = ContentSessionType.isStreamingSession(sessionType);
        boolean z = true;
        boolean z2 = !specification.isTrailer();
        boolean zShouldCallPrsV2Service = this.mPlaybackResourcesV2Config.shouldCallPrsV2Service(specification.getPlaybackEnvelope(), specification.getContentType());
        if (specification.isLiveStream() && sessionType != ContentSessionType.LIVE_CACHE && !zShouldCallPrsV2Service) {
            z = false;
        }
        if (zIsStreamingSession && z2 && z) {
            PlaybackEnvelope playbackEnvelopeValidate = specification.getPlaybackEnvelope() != null ? this.mPlaybackEnvelopeValidator.validate(specification.getPlaybackEnvelope()) : null;
            if (playbackEnvelopeValidate != null) {
                specification = VideoSpecification.newBuilder(specification).setPlaybackEnvelope(playbackEnvelopeValidate).build();
            }
            handleContentRepublication(context, makeServiceCall(specification, map, ContentSessionType.STREAMING, str, playbackEventReporter, rendererSchemeType, rendererScheme, true).getEncodeId());
        }
    }

    private void handleContentRepublication(ContentSessionContext contentSessionContext, String str) {
        VideoSpecification specification = contentSessionContext.getSpecification();
        String encodeId = contentSessionContext.getAudioVideoUrls().getEncodeId();
        if (encodeId == null || str == null || str.equals(encodeId)) {
            return;
        }
        Locale locale = Locale.US;
        String str2 = String.format(locale, "Triggering eviction of cached content due to encodeId change (old=%s, new=%s)", encodeId, str);
        DLog.logf(str2);
        this.mCacheManager.evict(specification, WhisperCacheMetrics$CacheEviction.CONTENT_REPUBLISHED, str2);
        this.mEventBus.postEvent(new RetriablePlaybackErrorEvent(TimeSpan.ZERO, new ContentException(ContentException.ContentError.SERVICE_ERROR, String.format(locale, "Detected different encodeIDs at time of playback, Content has been republished. new encodeId:%s, old encodeId:%s", str, encodeId))));
    }

    private boolean isValidationSufficient(@Nonnull VideoSpecification videoSpecification, @Nonnull ContentSessionType contentSessionType, boolean z) {
        if (contentSessionType != ContentSessionType.STREAMING) {
            return false;
        }
        if (this.mPlaybackResourcesV2Config.shouldCallPrsV2Service(videoSpecification.getPlaybackEnvelope(), videoSpecification.getContentType())) {
            return z;
        }
        return true;
    }
}
