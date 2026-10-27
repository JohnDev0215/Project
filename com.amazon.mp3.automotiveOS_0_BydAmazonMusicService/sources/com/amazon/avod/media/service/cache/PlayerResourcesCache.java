package com.amazon.avod.media.service.cache;

import com.amazon.atvplaybackdevice.types.ConsumptionType;
import com.amazon.atvplaybackdevice.types.Resource;
import com.amazon.atvplaybackdevice.types.ResourceUsage;
import com.amazon.atvplaybackdevice.types.VideoMaterialType;
import com.amazon.avod.content.ContentException;
import com.amazon.avod.content.ContentSessionType;
import com.amazon.avod.media.service.PlaybackResourcesValidator;
import com.amazon.avod.media.service.PlayerResourcesAndParams;
import com.amazon.avod.media.service.cache.PlayerResourcesCacheBase;
import com.amazon.avod.perf.Profiler;
import com.amazon.avod.perf.TraceKey;
import com.amazon.avod.playback.LifecycleProfiler;
import com.amazon.avod.playback.LifecycleProfilerFactory;
import com.amazon.avod.playbackclient.utils.VideoMaterialTypeUtils;
import com.amazon.avod.playbackresource.PlaybackResourceConfig;
import com.amazon.avod.playbackresource.PlaybackResources;
import com.amazon.avod.playbackresource.PlaybackResourcesWrapper;
import com.amazon.avod.pmet.LifecycleProfilerMetrics;
import com.amazon.avod.pmet.PmetLifecycleProfilerReporter;
import com.amazon.avod.provider.module.impl.cache.CacheFactory;
import com.amazon.avod.prs.GetPlaybackResources;
import com.amazon.avod.prs.GetPlaybackResourcesFactory;
import com.amazon.avod.prs.GetPlaybackResourcesPlayerRequest;
import com.amazon.avod.util.DLog;
import com.amazon.avod.util.SyeConfig;
import com.amazon.bolthttp.BoltException;
import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import java.util.Collections;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class PlayerResourcesCache extends PlayerResourcesCacheBase<PlayerResourcesCacheRequest, PlaybackResources, PlaybackResourcesWrapper> {

    public static class SingletonHolder {
        private static final PlayerResourcesCache INSTANCE = new PlayerResourcesCache(CacheFactory.generateMemoryCache(new PlayerResourcesCacheBase.PlayerResourcesCacheConfig(PlaybackResourceConfig.getInstance().getPlayerResourcesCacheSize(), PlaybackResourceConfig.getInstance().getPlayerResourcesCacheToLiveTimeMillis()), new PlaybackResourcesCacheLoader(PlayerResourcesCache.getItemLoaderMap())), new PlaybackResourcesValidator());
    }

    public static PlayerResourcesCache getInstance() {
        return SingletonHolder.INSTANCE;
    }

    PlayerResourcesCache(@Nonnull LoadingCache<PlayerResourcesCacheRequest, Optional<PlaybackResourcesWrapper>> loadingCache, @Nonnull PlaybackResourcesValidator playbackResourcesValidator) {
        super(loadingCache, playbackResourcesValidator);
    }

    public Optional<PlaybackResources> getResources(@Nonnull PlayerResourcesCacheRequest playerResourcesCacheRequest) {
        Preconditions.checkNotNull(playerResourcesCacheRequest, "request");
        Optional<PlaybackResourcesWrapper> response = getResponse(playerResourcesCacheRequest);
        return !response.isPresent() ? Optional.absent() : response.get().getPlaybackResources();
    }

    @Nonnull
    public PlaybackResources getValidatedResources(@Nonnull PlayerResourcesCacheRequest playerResourcesCacheRequest) throws ContentException {
        Preconditions.checkNotNull(playerResourcesCacheRequest, "request");
        try {
            return this.mValidator.validatePlayerResources(playerResourcesCacheRequest.getTitleId(), getResponse(playerResourcesCacheRequest));
        } catch (ContentException e) {
            this.mCache.invalidate(playerResourcesCacheRequest);
            throw e;
        }
    }

    public Optional<PlaybackResources> getResourcesIfPresent(@Nonnull PlayerResourcesCacheRequest playerResourcesCacheRequest) {
        Preconditions.checkNotNull(playerResourcesCacheRequest, "request");
        Optional<PlaybackResourcesWrapper> ifPresent = getIfPresent(playerResourcesCacheRequest);
        if (ifPresent.isPresent()) {
            return ifPresent.get().getPlaybackResources();
        }
        return Optional.absent();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static ImmutableMap<VideoMaterialType, PlayerResourcesCacheBase.PlayerResourcesLoader> getItemLoaderMap() {
        LoadPlayerResources loadPlayerResources = new LoadPlayerResources(GetPlaybackResourcesFactory.createNonCachingInstance(new GetPlaybackResourcesPlayerRequest()));
        PlayerResourcesCacheBase.NoopLocalPlayerResources noopLocalPlayerResources = new PlayerResourcesCacheBase.NoopLocalPlayerResources();
        return ImmutableMap.builder().put(VideoMaterialType.Feature, loadPlayerResources).put(VideoMaterialType.Trailer, loadPlayerResources).put(VideoMaterialType.LiveStreaming, loadPlayerResources).put(VideoMaterialType.External, noopLocalPlayerResources).put(VideoMaterialType.ValueAdded, noopLocalPlayerResources).build();
    }

    static class LoadPlayerResources implements PlayerResourcesCacheBase.PlayerResourcesLoader<PlayerResourcesCacheRequest, PlaybackResourcesWrapper> {
        private static final ImmutableSet<Resource> BASE_DESIRED_RESOURCES = ImmutableSet.of(Resource.TrickplayUrls, Resource.SubtitlePresets, Resource.SubtitleUrls, Resource.ForcedNarratives, Resource.CuepointPlaylist, Resource.PlaybackUrls, Resource.PlaybackSettings);
        private final GetPlaybackResources mGetPlaybackResources;
        private final SyeConfig mSyeConfig = SyeConfig.getInstance();
        private final LifecycleProfiler mLifecycleProfiler = LifecycleProfilerFactory.createLifecycleProfiler();

        LoadPlayerResources(GetPlaybackResources getPlaybackResources) {
            this.mGetPlaybackResources = getPlaybackResources;
        }

        @Override // com.google.common.base.Function
        @Nonnull
        public Optional<PlaybackResourcesWrapper> apply(@Nonnull PlayerResourcesCacheRequest playerResourcesCacheRequest) {
            Optional<PlaybackResourcesWrapper> optionalAbsent;
            LifecycleProfiler lifecycleProfiler;
            PmetLifecycleProfilerReporter pmetLifecycleProfilerReporter;
            ResourceUsage resourceUsage;
            Preconditions.checkNotNull(playerResourcesCacheRequest, "request");
            TraceKey traceKeyBeginTrace = Profiler.beginTrace(Profiler.TraceLevel.INFO, "%s:getPlaybackResourcesFromNetwork", getClass().getSimpleName());
            LifecycleProfiler lifecycleProfiler2 = this.mLifecycleProfiler;
            LifecycleProfilerMetrics lifecycleProfilerMetrics = LifecycleProfilerMetrics.FETCH_PLAYBACK_RESOURCES;
            lifecycleProfiler2.start(lifecycleProfilerMetrics);
            try {
                try {
                    ConsumptionType consumptionType = playerResourcesCacheRequest.getConsumptionType();
                    String titleId = playerResourcesCacheRequest.getTitleId();
                    VideoMaterialType videoMaterialType = playerResourcesCacheRequest.getVideoMaterialType();
                    PlayerResourcesAndParams playerResourcesAndParams = new PlayerResourcesAndParams(titleId, VideoMaterialTypeUtils.toPlayersContentType(videoMaterialType), playerResourcesCacheRequest.getAudioTrackId(), playerResourcesCacheRequest.getPlaybackToken(), getDesiredResources(videoMaterialType), playerResourcesCacheRequest.shouldShowAds(), playerResourcesCacheRequest.getEPrivacyConsent());
                    if (consumptionType == ConsumptionType.Download) {
                        resourceUsage = ResourceUsage.ImmediateConsumption;
                    } else if (playerResourcesCacheRequest.getContentSessionType() == ContentSessionType.STREAMING && !VideoMaterialType.Trailer.equals(videoMaterialType)) {
                        resourceUsage = playerResourcesCacheRequest.hasPlaybackBeenInvoked() ? ResourceUsage.ImmediateConsumption : ResourceUsage.CacheResources;
                    } else {
                        resourceUsage = ContentSessionType.isCacheSession(playerResourcesCacheRequest.getContentSessionType()) ? ResourceUsage.CacheResources : ResourceUsage.ImmediateConsumption;
                    }
                    PlaybackResourcesWrapper playbackResourcesWrapper = this.mGetPlaybackResources.get(playerResourcesCacheRequest.getTitleId(), videoMaterialType, consumptionType, resourceUsage, playerResourcesAndParams.getDesiredResourcesSet(), playerResourcesAndParams, playerResourcesCacheRequest.getSessionContext(), null);
                    optionalAbsent = PlayerResourcesCache.isEntitled(playbackResourcesWrapper) ? Optional.of(playbackResourcesWrapper) : Optional.absent();
                    Profiler.endTrace(traceKeyBeginTrace);
                    this.mLifecycleProfiler.end(lifecycleProfilerMetrics);
                    lifecycleProfiler = this.mLifecycleProfiler;
                    pmetLifecycleProfilerReporter = new PmetLifecycleProfilerReporter();
                } catch (BoltException e) {
                    DLog.exceptionf(e, "Exception making getPlaybackResource call to cache for %s", playerResourcesCacheRequest.getTitleId());
                    optionalAbsent = Optional.absent();
                    Profiler.endTrace(traceKeyBeginTrace);
                    this.mLifecycleProfiler.end(LifecycleProfilerMetrics.FETCH_PLAYBACK_RESOURCES);
                    lifecycleProfiler = this.mLifecycleProfiler;
                    pmetLifecycleProfilerReporter = new PmetLifecycleProfilerReporter();
                }
                lifecycleProfiler.reportMetric(Collections.singletonList(pmetLifecycleProfilerReporter));
                return optionalAbsent;
            } catch (Throwable th) {
                Profiler.endTrace(traceKeyBeginTrace);
                this.mLifecycleProfiler.end(LifecycleProfilerMetrics.FETCH_PLAYBACK_RESOURCES);
                this.mLifecycleProfiler.reportMetric(Collections.singletonList(new PmetLifecycleProfilerReporter()));
                throw th;
            }
        }

        private ImmutableSet<Resource> getDesiredResources(@Nonnull VideoMaterialType videoMaterialType) {
            if (VideoMaterialTypeUtils.isLive(videoMaterialType) && this.mSyeConfig.isPlayerEnabled()) {
                return ImmutableSet.builder().addAll((Iterable) BASE_DESIRED_RESOURCES).add(Resource.SyeUrlsV2).build();
            }
            return BASE_DESIRED_RESOURCES;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean isEntitled(@Nonnull PlaybackResourcesWrapper playbackResourcesWrapper) {
        return playbackResourcesWrapper.getPlaybackResources().isPresent() && playbackResourcesWrapper.getPlaybackResources().get().isEntitled();
    }

    private static class PlaybackResourcesCacheLoader extends CacheLoader<PlayerResourcesCacheRequest, Optional<PlaybackResourcesWrapper>> {
        private final ImmutableMap<VideoMaterialType, PlayerResourcesCacheBase.PlayerResourcesLoader> mLoadRequestMap;

        private PlaybackResourcesCacheLoader(@Nonnull ImmutableMap<VideoMaterialType, PlayerResourcesCacheBase.PlayerResourcesLoader> immutableMap) {
            this.mLoadRequestMap = (ImmutableMap) Preconditions.checkNotNull(immutableMap, "loadRequestMap");
        }

        @Override // com.google.common.cache.CacheLoader
        public Optional<PlaybackResourcesWrapper> load(@Nonnull PlayerResourcesCacheRequest playerResourcesCacheRequest) {
            Preconditions.checkNotNull(playerResourcesCacheRequest, "request");
            return (Optional) this.mLoadRequestMap.get(playerResourcesCacheRequest.getVideoMaterialType()).apply(playerResourcesCacheRequest);
        }
    }
}
