package com.amazon.avod.media.service.cache;

import com.amazon.atvplaybackdevice.types.VideoMaterialType;
import com.amazon.avod.content.ContentException;
import com.amazon.avod.media.service.PlaybackResourcesValidator;
import com.amazon.avod.media.service.cache.PlayerResourcesCacheBase;
import com.amazon.avod.perf.Profiler;
import com.amazon.avod.perf.TraceKey;
import com.amazon.avod.playback.LifecycleProfiler;
import com.amazon.avod.playback.LifecycleProfilerFactory;
import com.amazon.avod.playbackresource.PlaybackResourceConfig;
import com.amazon.avod.playbackresourcev2.PlaybackResourcesV2;
import com.amazon.avod.playbackresourcev2.PlaybackResourcesV2Wrapper;
import com.amazon.avod.pmet.LifecycleProfilerMetrics;
import com.amazon.avod.pmet.PmetLifecycleProfilerReporter;
import com.amazon.avod.provider.module.impl.cache.CacheFactory;
import com.amazon.avod.prs.GetCombinedPlaybackResources;
import com.amazon.avod.prs.GetPlaybackResourcesFactory;
import com.amazon.avod.prs.GetPlaybackResourcesPlayerRequest;
import com.amazon.avod.prs.GetPlaybackResourcesV2PlayerRequest;
import com.amazon.avod.prs.PRSV2ResourceRequest;
import com.amazon.avod.util.DLog;
import com.amazon.bolthttp.BoltException;
import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.collect.ImmutableMap;
import java.util.Collections;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class PlayerResourcesCacheV2 extends PlayerResourcesCacheBase<PRSV2ResourceRequest, PlaybackResourcesV2, PlaybackResourcesV2Wrapper> {

    public static class SingletonHolder {
        private static final PlayerResourcesCacheV2 INSTANCE = new PlayerResourcesCacheV2(CacheFactory.generateMemoryCache(new PlayerResourcesCacheBase.PlayerResourcesCacheConfig(PlaybackResourceConfig.getInstance().getPlayerResourcesCacheSize(), PlaybackResourceConfig.getInstance().getPlayerResourcesCacheToLiveTimeMillis()), new PlaybackResourcesCacheLoader(PlayerResourcesCacheV2.getItemLoaderMap())), new PlaybackResourcesValidator());
    }

    public static PlayerResourcesCacheV2 getInstance() {
        return SingletonHolder.INSTANCE;
    }

    PlayerResourcesCacheV2(@Nonnull LoadingCache<PRSV2ResourceRequest, Optional<PlaybackResourcesV2Wrapper>> loadingCache, @Nonnull PlaybackResourcesValidator playbackResourcesValidator) {
        super(loadingCache, playbackResourcesValidator);
    }

    @Nonnull
    public PlaybackResourcesV2 getValidatedResources(@Nonnull PRSV2ResourceRequest pRSV2ResourceRequest) throws ContentException {
        Preconditions.checkNotNull(pRSV2ResourceRequest, "request");
        try {
            return this.mValidator.validatePlayerResourcesV2(pRSV2ResourceRequest.getTitleId(), getResponse(pRSV2ResourceRequest));
        } catch (ContentException e) {
            this.mCache.invalidate(pRSV2ResourceRequest);
            throw e;
        }
    }

    static class LoadPlayerResources implements PlayerResourcesCacheBase.PlayerResourcesLoader<PRSV2ResourceRequest, PlaybackResourcesV2Wrapper> {
        private final GetCombinedPlaybackResources mGetCombinedPlaybackResources;
        private final LifecycleProfiler mLifecycleProfiler = LifecycleProfilerFactory.createLifecycleProfiler();

        LoadPlayerResources(@Nonnull GetCombinedPlaybackResources getCombinedPlaybackResources) {
            this.mGetCombinedPlaybackResources = (GetCombinedPlaybackResources) Preconditions.checkNotNull(getCombinedPlaybackResources, "getCombinedPlaybackResources");
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r0v11 */
        /* JADX WARN: Type inference failed for: r0v12 */
        /* JADX WARN: Type inference failed for: r0v2, types: [com.amazon.avod.perf.TraceKey] */
        /* JADX WARN: Type inference failed for: r0v8, types: [com.amazon.avod.playback.LifecycleProfiler] */
        @Override // com.google.common.base.Function
        @Nonnull
        public Optional<PlaybackResourcesV2Wrapper> apply(@Nonnull PRSV2ResourceRequest pRSV2ResourceRequest) {
            Optional<PlaybackResourcesV2Wrapper> optionalAbsent;
            PmetLifecycleProfilerReporter pmetLifecycleProfilerReporter;
            Preconditions.checkNotNull(pRSV2ResourceRequest, "request");
            TraceKey traceKeyBeginTrace = Profiler.beginTrace(Profiler.TraceLevel.INFO, "%s:getPlaybackResourcesV2", getClass().getSimpleName());
            LifecycleProfiler lifecycleProfiler = this.mLifecycleProfiler;
            LifecycleProfilerMetrics lifecycleProfilerMetrics = LifecycleProfilerMetrics.FETCH_PLAYBACK_RESOURCES;
            lifecycleProfiler.start(lifecycleProfilerMetrics);
            try {
                try {
                    optionalAbsent = Optional.of(this.mGetCombinedPlaybackResources.get(pRSV2ResourceRequest));
                    Profiler.endTrace(traceKeyBeginTrace);
                    this.mLifecycleProfiler.end(lifecycleProfilerMetrics);
                    LifecycleProfiler lifecycleProfiler2 = this.mLifecycleProfiler;
                    pmetLifecycleProfilerReporter = new PmetLifecycleProfilerReporter();
                    traceKeyBeginTrace = lifecycleProfiler2;
                } catch (BoltException | InterruptedException | ExecutionException | TimeoutException e) {
                    DLog.exceptionf(e, "PlayerResourcesCacheV2: exception making call to PRS for %s", pRSV2ResourceRequest.getTitleId());
                    optionalAbsent = Optional.absent();
                    Profiler.endTrace(traceKeyBeginTrace);
                    this.mLifecycleProfiler.end(LifecycleProfilerMetrics.FETCH_PLAYBACK_RESOURCES);
                    LifecycleProfiler lifecycleProfiler3 = this.mLifecycleProfiler;
                    pmetLifecycleProfilerReporter = new PmetLifecycleProfilerReporter();
                    traceKeyBeginTrace = lifecycleProfiler3;
                }
                traceKeyBeginTrace.reportMetric(Collections.singletonList(pmetLifecycleProfilerReporter));
                return optionalAbsent;
            } catch (Throwable th) {
                Profiler.endTrace(traceKeyBeginTrace);
                this.mLifecycleProfiler.end(LifecycleProfilerMetrics.FETCH_PLAYBACK_RESOURCES);
                this.mLifecycleProfiler.reportMetric(Collections.singletonList(new PmetLifecycleProfilerReporter()));
                throw th;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static ImmutableMap<VideoMaterialType, PlayerResourcesCacheBase.PlayerResourcesLoader> getItemLoaderMap() {
        LoadPlayerResources loadPlayerResources = new LoadPlayerResources(GetPlaybackResourcesFactory.createNonCachingInstance(new GetPlaybackResourcesPlayerRequest(), new GetPlaybackResourcesV2PlayerRequest()));
        PlayerResourcesCacheBase.NoopLocalPlayerResources noopLocalPlayerResources = new PlayerResourcesCacheBase.NoopLocalPlayerResources();
        return ImmutableMap.builder().put(VideoMaterialType.Feature, noopLocalPlayerResources).put(VideoMaterialType.Trailer, noopLocalPlayerResources).put(VideoMaterialType.LiveStreaming, loadPlayerResources).put(VideoMaterialType.External, noopLocalPlayerResources).put(VideoMaterialType.ValueAdded, noopLocalPlayerResources).build();
    }

    private static class PlaybackResourcesCacheLoader extends CacheLoader<PRSV2ResourceRequest, Optional<PlaybackResourcesV2Wrapper>> {
        private final ImmutableMap<VideoMaterialType, PlayerResourcesCacheBase.PlayerResourcesLoader> mLoadRequestMap;

        private PlaybackResourcesCacheLoader(@Nonnull ImmutableMap<VideoMaterialType, PlayerResourcesCacheBase.PlayerResourcesLoader> immutableMap) {
            this.mLoadRequestMap = (ImmutableMap) Preconditions.checkNotNull(immutableMap, "loadRequestMap");
        }

        @Override // com.google.common.cache.CacheLoader
        public Optional<PlaybackResourcesV2Wrapper> load(@Nonnull PRSV2ResourceRequest pRSV2ResourceRequest) {
            Preconditions.checkNotNull(pRSV2ResourceRequest, "request");
            return (Optional) this.mLoadRequestMap.get(pRSV2ResourceRequest.getVideoMaterialType()).apply(pRSV2ResourceRequest);
        }
    }
}
