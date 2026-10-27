package com.amazon.avod.media.service;

import com.amazon.avod.content.ContentException;
import com.amazon.avod.content.ContentSessionType;
import com.amazon.avod.content.config.PlaybackResourcesV2Config;
import com.amazon.avod.content.urlvending.AudioVideoUrls;
import com.amazon.avod.content.urlvending.ContentUrlFetcher;
import com.amazon.avod.media.ads.AdvertisingIdCollector;
import com.amazon.avod.media.framework.profiling.MediaProfiler;
import com.amazon.avod.media.playback.QOSCommunicationService;
import com.amazon.avod.media.playback.VideoSpecification;
import com.amazon.avod.media.playback.support.PlaybackSupportEvaluator;
import com.amazon.avod.media.playback.support.RendererScheme;
import com.amazon.avod.playback.PlaybackEventReporter;
import com.amazon.avod.playback.renderer.RendererSchemeType;
import com.google.common.base.Preconditions;
import java.util.Map;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public class AVODContentUrlFetcher implements ContentUrlFetcher {
    private final AVODServiceConfig mAVODServiceConfig;
    private final GetPlaybackResourcesServiceClient mGetPlaybackResourcesServiceClient;
    private final GetPlaybackResourcesV2ServiceClient mGetPlaybackResourcesV2ServiceClient;
    private final MediaProfiler mMediaProfiler;
    private final PlaybackResourcesV2Config mPlaybackResourcesV2Config;

    public AVODContentUrlFetcher(MediaProfiler mediaProfiler, PlaybackSupportEvaluator playbackSupportEvaluator, AdvertisingIdCollector advertisingIdCollector, @Nonnull QOSCommunicationService qOSCommunicationService) {
        this(mediaProfiler, new GetPlaybackResourcesServiceClient(playbackSupportEvaluator, advertisingIdCollector, qOSCommunicationService), AVODServiceConfig.getInstance(), new GetPlaybackResourcesV2ServiceClient(advertisingIdCollector, playbackSupportEvaluator), PlaybackResourcesV2Config.getInstance());
    }

    AVODContentUrlFetcher(MediaProfiler mediaProfiler, GetPlaybackResourcesServiceClient getPlaybackResourcesServiceClient, @Nonnull AVODServiceConfig aVODServiceConfig, @Nonnull GetPlaybackResourcesV2ServiceClient getPlaybackResourcesV2ServiceClient, @Nonnull PlaybackResourcesV2Config playbackResourcesV2Config) {
        this.mMediaProfiler = mediaProfiler;
        this.mGetPlaybackResourcesServiceClient = getPlaybackResourcesServiceClient;
        this.mAVODServiceConfig = (AVODServiceConfig) Preconditions.checkNotNull(aVODServiceConfig, "avodServiceConfig");
        this.mGetPlaybackResourcesV2ServiceClient = (GetPlaybackResourcesV2ServiceClient) Preconditions.checkNotNull(getPlaybackResourcesV2ServiceClient, "getPlaybackResourcesV2ServiceClient");
        this.mPlaybackResourcesV2Config = (PlaybackResourcesV2Config) Preconditions.checkNotNull(playbackResourcesV2Config, "playbackResourcesV2Config");
    }

    @Override // com.amazon.avod.content.urlvending.ContentUrlFetcher
    @Nonnull
    public AudioVideoUrls getAudioVideoUrls(@Nonnull VideoSpecification videoSpecification, @Nonnull Map<String, String> map, @Nonnull ContentSessionType contentSessionType, @Nullable String str, @Nullable PlaybackEventReporter playbackEventReporter, @Nullable RendererSchemeType rendererSchemeType, @Nonnull RendererScheme rendererScheme, boolean z) throws ContentException {
        if (this.mAVODServiceConfig.isContentUrlOverrideEnabled()) {
            return this.mAVODServiceConfig.getOverrideContentUrl();
        }
        return getPlaybackResourcesContentUrlSet(videoSpecification, map, contentSessionType, str, playbackEventReporter, rendererSchemeType, rendererScheme, z);
    }

    /* JADX INFO: renamed from: com.amazon.avod.media.service.AVODContentUrlFetcher$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$avod$content$ContentSessionType;

        static {
            int[] iArr = new int[ContentSessionType.values().length];
            $SwitchMap$com$amazon$avod$content$ContentSessionType = iArr;
            try {
                iArr[ContentSessionType.STREAMING.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$avod$content$ContentSessionType[ContentSessionType.LIVE_CACHE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$avod$content$ContentSessionType[ContentSessionType.CONTENT_CACHE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$amazon$avod$content$ContentSessionType[ContentSessionType.STREAMING_CONTINUOUS.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$amazon$avod$content$ContentSessionType[ContentSessionType.DOWNLOAD.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$amazon$avod$content$ContentSessionType[ContentSessionType.PROGRESSIVE_PLAYBACK.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x0037  */
    /* JADX WARN: Removed duplicated region for block: B:13:0x0039  */
    /* JADX WARN: Removed duplicated region for block: B:16:0x0043  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x0067  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0094  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private com.amazon.avod.content.urlvending.AudioVideoUrls getPlaybackResourcesContentUrlSet(@javax.annotation.Nonnull com.amazon.avod.media.playback.VideoSpecification r22, @javax.annotation.Nonnull java.util.Map<java.lang.String, java.lang.String> r23, @javax.annotation.Nonnull com.amazon.avod.content.ContentSessionType r24, @javax.annotation.Nullable java.lang.String r25, @javax.annotation.Nullable com.amazon.avod.playback.PlaybackEventReporter r26, @javax.annotation.Nullable com.amazon.avod.playback.renderer.RendererSchemeType r27, @javax.annotation.Nonnull com.amazon.avod.media.playback.support.RendererScheme r28, boolean r29) throws com.amazon.avod.content.ContentException {
        /*
            Method dump skipped, instruction units count: 218
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.avod.media.service.AVODContentUrlFetcher.getPlaybackResourcesContentUrlSet(com.amazon.avod.media.playback.VideoSpecification, java.util.Map, com.amazon.avod.content.ContentSessionType, java.lang.String, com.amazon.avod.playback.PlaybackEventReporter, com.amazon.avod.playback.renderer.RendererSchemeType, com.amazon.avod.media.playback.support.RendererScheme, boolean):com.amazon.avod.content.urlvending.AudioVideoUrls");
    }
}
