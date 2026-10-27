package com.amazon.video.sdk.content;

import com.amazon.avod.media.TimeSpan;
import com.amazon.avod.media.contentcache.CacheRequest;
import com.amazon.avod.media.contentcache.VideoCacheManager;
import com.amazon.avod.util.DLog;
import com.amazon.video.sdk.PlayerSdkConfig;
import com.amazon.video.sdk.player.ContentConfig;
import com.amazon.video.sdk.player.util.VideoSpecUtil;
import com.amazon.video.sdk.stream.util.MultiTrackAudioUtils;
import com.google.common.collect.ImmutableList;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: LocalContentManagerImpl.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(bv = {}, d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0000\u0018\u0000 \u001a2\u00020\u0001:\u0001\u001aB3\u0012\u0006\u0010\n\u001a\u00020\t\u0012\u0006\u0010\r\u001a\u00020\f\u0012\u0006\u0010\u0010\u001a\u00020\u000f\u0012\b\b\u0002\u0010\u0013\u001a\u00020\u0012\u0012\b\b\u0002\u0010\u0016\u001a\u00020\u0015¢\u0006\u0004\b\u0018\u0010\u0019J\b\u0010\u0003\u001a\u00020\u0002H\u0002J\u0016\u0010\b\u001a\u00020\u00072\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004H\u0016R\u0014\u0010\n\u001a\u00020\t8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\n\u0010\u000bR\u0014\u0010\r\u001a\u00020\f8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\r\u0010\u000eR\u0014\u0010\u0010\u001a\u00020\u000f8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0010\u0010\u0011R\u0014\u0010\u0013\u001a\u00020\u00128\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0013\u0010\u0014R\u0014\u0010\u0016\u001a\u00020\u00158\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0016\u0010\u0017¨\u0006\u001b"}, d2 = {"Lcom/amazon/video/sdk/content/LocalContentManagerImpl;", "Lcom/amazon/video/sdk/content/LocalContentManager;", "Lcom/amazon/avod/media/TimeSpan;", "getCacheDuration", "", "Lcom/amazon/video/sdk/content/CacheContentConfig;", "cacheContentConfigList", "", "cache", "Lcom/amazon/video/sdk/PlayerSdkConfig;", "sdkConfig", "Lcom/amazon/video/sdk/PlayerSdkConfig;", "Lcom/amazon/video/sdk/content/LocalContentManagerConfig;", "config", "Lcom/amazon/video/sdk/content/LocalContentManagerConfig;", "Lcom/amazon/avod/media/contentcache/VideoCacheManager;", "videoCacheManager", "Lcom/amazon/avod/media/contentcache/VideoCacheManager;", "Lcom/amazon/video/sdk/player/util/VideoSpecUtil;", "videoSpecUtil", "Lcom/amazon/video/sdk/player/util/VideoSpecUtil;", "Lcom/amazon/video/sdk/stream/util/MultiTrackAudioUtils;", "mtaUtils", "Lcom/amazon/video/sdk/stream/util/MultiTrackAudioUtils;", "<init>", "(Lcom/amazon/video/sdk/PlayerSdkConfig;Lcom/amazon/video/sdk/content/LocalContentManagerConfig;Lcom/amazon/avod/media/contentcache/VideoCacheManager;Lcom/amazon/video/sdk/player/util/VideoSpecUtil;Lcom/amazon/video/sdk/stream/util/MultiTrackAudioUtils;)V", "Companion", "AmazonAndroidVideoPlayer_release"}, k = 1, mv = {1, 6, 0})
public final class LocalContentManagerImpl implements LocalContentManager {
    private static final TimeSpan MIN_CACHE_DURATION_TIMESPAN = TimeSpan.fromSeconds(8.0d);
    private final LocalContentManagerConfig config;
    private final MultiTrackAudioUtils mtaUtils;
    private final PlayerSdkConfig sdkConfig;
    private final VideoCacheManager videoCacheManager;
    private final VideoSpecUtil videoSpecUtil;

    public LocalContentManagerImpl(PlayerSdkConfig sdkConfig, LocalContentManagerConfig config, VideoCacheManager videoCacheManager, VideoSpecUtil videoSpecUtil, MultiTrackAudioUtils mtaUtils) {
        Intrinsics.checkNotNullParameter(sdkConfig, "sdkConfig");
        Intrinsics.checkNotNullParameter(config, "config");
        Intrinsics.checkNotNullParameter(videoCacheManager, "videoCacheManager");
        Intrinsics.checkNotNullParameter(videoSpecUtil, "videoSpecUtil");
        Intrinsics.checkNotNullParameter(mtaUtils, "mtaUtils");
        this.sdkConfig = sdkConfig;
        this.config = config;
        this.videoCacheManager = videoCacheManager;
        this.videoSpecUtil = videoSpecUtil;
        this.mtaUtils = mtaUtils;
    }

    public /* synthetic */ LocalContentManagerImpl(PlayerSdkConfig playerSdkConfig, LocalContentManagerConfig localContentManagerConfig, VideoCacheManager videoCacheManager, VideoSpecUtil videoSpecUtil, MultiTrackAudioUtils multiTrackAudioUtils, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(playerSdkConfig, localContentManagerConfig, videoCacheManager, (i & 8) != 0 ? new VideoSpecUtil() : videoSpecUtil, (i & 16) != 0 ? new MultiTrackAudioUtils() : multiTrackAudioUtils);
    }

    @Override // com.amazon.video.sdk.content.LocalContentManager
    public void cache(List<? extends CacheContentConfig> cacheContentConfigList) {
        Intrinsics.checkNotNullParameter(cacheContentConfigList, "cacheContentConfigList");
        DLog.devf("LocalContentManager: Caching content using Player SDK");
        ArrayList arrayList = new ArrayList();
        for (CacheContentConfig cacheContentConfig : cacheContentConfigList) {
            ContentConfig contentConfig = cacheContentConfig.getContentConfig();
            ImmutableList<String> immutableListGenerateAudioTrackIdsFromStreamMatchers = this.mtaUtils.generateAudioTrackIdsFromStreamMatchers(cacheContentConfig.getAudioStreamMatchers());
            CacheRequest cacheRequest = CacheRequest.newBuilder().setVideoSpecification(this.videoSpecUtil.createVideoSpec$AmazonAndroidVideoPlayer_release(contentConfig, getCacheDuration(), immutableListGenerateAudioTrackIdsFromStreamMatchers, immutableListGenerateAudioTrackIdsFromStreamMatchers.size() > 0 ? immutableListGenerateAudioTrackIdsFromStreamMatchers.get(0) : null, null, this.sdkConfig.getEPrivacyConsentDataProvider().getEPrivacyConsentData())).setVideoOptions(this.videoSpecUtil.createVideoOptions$AmazonAndroidVideoPlayer_release(contentConfig)).setIsEntitledToWatch(true).build();
            Intrinsics.checkNotNullExpressionValue(cacheRequest, "cacheRequest");
            arrayList.add(cacheRequest);
        }
        if (arrayList.size() == 1) {
            this.videoCacheManager.cacheTitle((CacheRequest) arrayList.get(0));
        } else {
            this.videoCacheManager.cacheTitleList(ImmutableList.copyOf((Collection) arrayList));
        }
    }

    private final TimeSpan getCacheDuration() {
        if (this.config.getCacheDuration() != null) {
            TimeSpan cacheDuration = this.config.getCacheDuration();
            Intrinsics.checkNotNull(cacheDuration);
            if (cacheDuration.compareTo(MIN_CACHE_DURATION_TIMESPAN) > 0) {
                TimeSpan cacheDuration2 = this.config.getCacheDuration();
                Intrinsics.checkNotNull(cacheDuration2);
                return cacheDuration2;
            }
        }
        TimeSpan timeSpan = MIN_CACHE_DURATION_TIMESPAN;
        Intrinsics.checkNotNullExpressionValue(timeSpan, "{\n            MIN_CACHE_…RATION_TIMESPAN\n        }");
        return timeSpan;
    }
}
