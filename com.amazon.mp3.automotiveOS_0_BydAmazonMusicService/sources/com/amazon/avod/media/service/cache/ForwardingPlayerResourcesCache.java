package com.amazon.avod.media.service.cache;

import com.amazon.avod.content.ContentException;
import com.amazon.avod.content.ContentSessionType;
import com.amazon.avod.content.config.PlaybackResourcesV2Config;
import com.amazon.avod.content.urlvending.AudioVideoUrls;
import com.amazon.avod.media.playback.ContentType;
import com.amazon.avod.media.playback.VideoSpecification;
import com.amazon.avod.media.playback.support.ConsumptionType;
import com.amazon.avod.media.service.prsv2.GlobalParamsCreator;
import com.amazon.avod.media.service.prsv2.LivePlaybackUrlsParamsCreator;
import com.amazon.avod.media.service.prsv2.PlaybackDataParamsCreator;
import com.amazon.avod.media.service.prsv2.PlaybackResourceV2ParamsCreator;
import com.amazon.avod.media.service.prsv2.RapidRecapPlaybackUrlsParamsCreator;
import com.amazon.avod.playbackclient.utils.VideoMaterialTypeUtils;
import com.amazon.avod.playbackresource.PlaybackResources;
import com.amazon.avod.playbackresourcev2.PlaybackResourcesV2;
import com.amazon.avod.prs.PRSV2ResourceRequest;
import com.amazon.avod.util.DLog;
import com.amazon.video.sdk.player.PlaybackEnvelope;
import com.google.common.base.Preconditions;
import java.util.Map;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public class ForwardingPlayerResourcesCache {
    private final PlayerResourcesCache mCacheV1;
    private final PlayerResourcesCacheV2 mCacheV2;
    private final PlaybackResourcesV2Config mPlaybackResourcesV2Config;
    private final boolean mShouldRequestRapidRecapResourcesFromPRS;

    public static class SingletonHolder {
        private static final ForwardingPlayerResourcesCache INSTANCE = new ForwardingPlayerResourcesCache(PlayerResourcesCache.getInstance(), PlayerResourcesCacheV2.getInstance(), PlaybackResourcesV2Config.getInstance());
    }

    public static ForwardingPlayerResourcesCache getInstance() {
        return SingletonHolder.INSTANCE;
    }

    ForwardingPlayerResourcesCache(@Nonnull PlayerResourcesCache playerResourcesCache, @Nonnull PlayerResourcesCacheV2 playerResourcesCacheV2, @Nonnull PlaybackResourcesV2Config playbackResourcesV2Config) {
        this.mCacheV1 = (PlayerResourcesCache) Preconditions.checkNotNull(playerResourcesCache, "cacheV1");
        this.mCacheV2 = (PlayerResourcesCacheV2) Preconditions.checkNotNull(playerResourcesCacheV2, "cacheV2");
        PlaybackResourcesV2Config playbackResourcesV2Config2 = (PlaybackResourcesV2Config) Preconditions.checkNotNull(playbackResourcesV2Config, "playbackResourcesV2Config");
        this.mPlaybackResourcesV2Config = playbackResourcesV2Config2;
        this.mShouldRequestRapidRecapResourcesFromPRS = playbackResourcesV2Config2.shouldRequestRapidRecapResourcesFromPRS();
    }

    @Nonnull
    public AudioVideoUrls getAudioVideoUrls(@Nonnull VideoSpecification videoSpecification, @Nullable PlaybackEnvelope playbackEnvelope, @Nonnull Map<String, String> map, @Nonnull ContentSessionType contentSessionType, boolean z) throws ContentException {
        Preconditions.checkNotNull(videoSpecification, "videoSpec");
        Preconditions.checkNotNull(map, "sessionContext");
        Preconditions.checkNotNull(contentSessionType, "contentSessionType");
        ContentType contentType = videoSpecification.getContentType();
        if (this.mPlaybackResourcesV2Config.shouldCallPrsV2Service(playbackEnvelope, contentType)) {
            PlaybackResourceV2ParamsCreator.Builder builderPlaybackDataParamsCreator = new PlaybackResourceV2ParamsCreator.Builder().globalParamsCreator(new GlobalParamsCreator(playbackEnvelope)).livePlaybackUrlsParamsCreator(new LivePlaybackUrlsParamsCreator(videoSpecification)).playbackDataParamsCreator(new PlaybackDataParamsCreator());
            if (this.mShouldRequestRapidRecapResourcesFromPRS) {
                builderPlaybackDataParamsCreator.rapidReplayPlaybackUrlsParamsCreator(new RapidRecapPlaybackUrlsParamsCreator(videoSpecification));
            }
            PlaybackResourcesV2 validatedResources = this.mCacheV2.getValidatedResources(new PRSV2ResourceRequest.Builder().titleId(videoSpecification.getTitleId()).videoMaterialType(VideoMaterialTypeUtils.fromPlayersContentType(contentType)).consumptionType(ConsumptionType.Streaming).params(builderPlaybackDataParamsCreator.build().create()).sessionContext(map).forValidation(false).build());
            DLog.logf("PlayerLifecycleWorkflow - getting playback resources from PlayerResourcesCacheV2");
            return validatedResources.getAudioVideoUrls().get();
        }
        PlaybackResources validatedResources2 = this.mCacheV1.getValidatedResources(new PlayerResourcesCacheRequest(videoSpecification, com.amazon.atvplaybackdevice.types.ConsumptionType.Streaming, map, contentSessionType, z));
        DLog.logf("PlayerLifecycleWorkflow - getting playback resources from PlayerResourcesCache");
        return validatedResources2.getAudioVideoUrls().get();
    }

    public void clear() {
        this.mCacheV1.clear();
        this.mCacheV2.clear();
    }
}
