package com.amazon.avod.playback.session.workflow.tasks;

import com.amazon.avod.content.ContentSessionType;
import com.amazon.avod.content.urlvending.AudioVideoUrls;
import com.amazon.avod.media.contentcache.CacheRecord;
import com.amazon.avod.media.framework.MediaSystemSharedDependencies;
import com.amazon.avod.media.playback.PlaybackDataSource;
import com.amazon.avod.media.playback.VideoSpecification;
import com.amazon.avod.media.service.cache.ForwardingPlayerResourcesCache;
import com.amazon.avod.playback.player.PlaybackConfig;
import com.amazon.avod.playback.session.PlaybackSessionContext;
import com.amazon.avod.playback.session.PlaybackSessionResources;
import com.amazon.video.sdk.player.PlaybackEnvelopeValidator;
import com.google.common.base.Preconditions;
import com.google.common.eventbus.EventBus;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public class PrepareStreamingResources extends SimpleTask {
    private final PlaybackConfig mPlaybackConfig;
    private final PlaybackEnvelopeValidator mPlaybackEnvelopeValidator;
    private final PlaybackSessionContext mPlaybackSessionContext;
    private final PlaybackSessionResources mPlaybackSessionResources;
    private final ForwardingPlayerResourcesCache mPlayerResourcesCache;

    public PrepareStreamingResources(@Nonnull EventBus eventBus, @Nonnull ExceptionCallback exceptionCallback, @Nonnull PlaybackSessionResources playbackSessionResources, @Nonnull PlaybackSessionContext playbackSessionContext) {
        this(eventBus, exceptionCallback, playbackSessionResources, playbackSessionContext, ForwardingPlayerResourcesCache.getInstance(), playbackSessionResources.getPlaybackConfig(), MediaSystemSharedDependencies.getInstance().getPlaybackEnvelopeValidator());
    }

    public PrepareStreamingResources(@Nonnull EventBus eventBus, @Nonnull ExceptionCallback exceptionCallback, @Nonnull PlaybackSessionResources playbackSessionResources, @Nonnull PlaybackSessionContext playbackSessionContext, @Nonnull ForwardingPlayerResourcesCache forwardingPlayerResourcesCache, @Nonnull PlaybackConfig playbackConfig, @Nonnull PlaybackEnvelopeValidator playbackEnvelopeValidator) {
        super(eventBus, exceptionCallback);
        this.mPlaybackSessionResources = (PlaybackSessionResources) Preconditions.checkNotNull(playbackSessionResources, "playbackSessionResources");
        this.mPlaybackSessionContext = (PlaybackSessionContext) Preconditions.checkNotNull(playbackSessionContext, "playbackSessionContext");
        this.mPlayerResourcesCache = (ForwardingPlayerResourcesCache) Preconditions.checkNotNull(forwardingPlayerResourcesCache, "playerResourcesCache");
        this.mPlaybackConfig = (PlaybackConfig) Preconditions.checkNotNull(playbackConfig, "playbackConfig");
        this.mPlaybackEnvelopeValidator = (PlaybackEnvelopeValidator) Preconditions.checkNotNull(playbackEnvelopeValidator, "playbackEnvelopeValidator");
    }

    /* JADX WARN: Removed duplicated region for block: B:29:0x00bb  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x0104 A[PHI: r7
  0x0104: PHI (r7v3 com.amazon.avod.content.urlvending.AudioVideoUrls) = 
  (r7v2 com.amazon.avod.content.urlvending.AudioVideoUrls)
  (r7v2 com.amazon.avod.content.urlvending.AudioVideoUrls)
  (r7v8 com.amazon.avod.content.urlvending.AudioVideoUrls)
 binds: [B:33:0x00db, B:34:0x00dd, B:36:0x00ec] A[DONT_GENERATE, DONT_INLINE]] */
    @Override // com.amazon.avod.playback.session.workflow.scheduler.BaseTask
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    protected void execute() throws com.amazon.avod.media.framework.error.MediaException {
        /*
            Method dump skipped, instruction units count: 362
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.avod.playback.session.workflow.tasks.PrepareStreamingResources.execute():void");
    }

    PlaybackDataSource detectDataSource(@Nullable CacheRecord cacheRecord, @Nullable AudioVideoUrls audioVideoUrls, @Nonnull VideoSpecification videoSpecification, boolean z) {
        if (z && cacheRecord == null) {
            return PlaybackDataSource.SESSION_PRESTARTED;
        }
        if (cacheRecord == null || audioVideoUrls == null) {
            return PlaybackDataSource.STREAMING;
        }
        if (cacheRecord.getSessionType() == ContentSessionType.LIVE_CACHE) {
            if (z) {
                return PlaybackDataSource.FULLY_CACHED_SESSION_PRESTARTED;
            }
            return PlaybackDataSource.FULLY_CACHED;
        }
        if (!cacheRecord.beginsAtTimestamp(videoSpecification.getStartTime())) {
            return PlaybackDataSource.STREAMING;
        }
        if (cacheRecord.getCacheState() == CacheRecord.RecordState.FULLY_CACHED) {
            return PlaybackDataSource.FULLY_CACHED;
        }
        return PlaybackDataSource.PARTIALLY_CACHED;
    }
}
