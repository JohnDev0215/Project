package com.amazon.video.sdk.player.util;

import com.amazon.atvplaybackresource.ResponseTitleRendition;
import com.amazon.atvplaybackresource.SyeUrlResponse;
import com.amazon.avod.connectivity.NetworkConnectionManager;
import com.amazon.avod.connectivity.NetworkType;
import com.amazon.avod.content.urlvending.EPrivacyConsentData;
import com.amazon.avod.media.MediaQuality;
import com.amazon.avod.media.TimeSpan;
import com.amazon.avod.media.audioformat.AudioFormatProvider;
import com.amazon.avod.media.playback.VideoOptions;
import com.amazon.avod.media.playback.VideoSpecification;
import com.amazon.avod.playbackclient.config.PlaybackConfig;
import com.amazon.avod.playbackresource.PlaybackResources;
import com.amazon.avod.settings.StreamingConnectionSetting;
import com.amazon.video.sdk.player.ContentConfig;
import com.amazon.video.sdk.player.VideoTypeUtilsKt;
import com.google.common.base.Optional;
import com.google.common.collect.ImmutableList;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: VideoSpecUtil.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0010\u0018\u0000 \u00182\u00020\u0001:\u0001\u0018B\u0005¢\u0006\u0002\u0010\u0002J\u0015\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0010¢\u0006\u0002\b\u0007JO\u0010\b\u001a\u00020\t2\u0006\u0010\u0005\u001a\u00020\u00062\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u000b2\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000e0\r2\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u000e2\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u00112\u0006\u0010\u0012\u001a\u00020\u0013H\u0010¢\u0006\u0002\b\u0014J\b\u0010\u0015\u001a\u00020\u0016H\u0002J\u0010\u0010\u0017\u001a\u00020\u000b2\u0006\u0010\u0005\u001a\u00020\u0006H\u0002¨\u0006\u0019"}, d2 = {"Lcom/amazon/video/sdk/player/util/VideoSpecUtil;", "", "()V", "createVideoOptions", "Lcom/amazon/avod/media/playback/VideoOptions;", "content", "Lcom/amazon/video/sdk/player/ContentConfig;", "createVideoOptions$AmazonAndroidVideoPlayer_release", "createVideoSpec", "Lcom/amazon/avod/media/playback/VideoSpecification;", "duration", "Lcom/amazon/avod/media/TimeSpan;", "preferredAudioTrackIds", "Lcom/google/common/collect/ImmutableList;", "", "initialAudioTrackId", "playbackResources", "Lcom/amazon/avod/playbackresource/PlaybackResources;", "ePrivacyConsent", "Lcom/amazon/avod/content/urlvending/EPrivacyConsentData;", "createVideoSpec$AmazonAndroidVideoPlayer_release", "getMediaQuality", "Lcom/amazon/avod/media/MediaQuality;", "getStartTime", "Companion", "AmazonAndroidVideoPlayer_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public class VideoSpecUtil {
    private static final AudioFormatProvider audioFormatProvider;

    static {
        AudioFormatProvider audioFormatProvider2 = AudioFormatProvider.getInstance();
        Intrinsics.checkNotNullExpressionValue(audioFormatProvider2, "getInstance()");
        audioFormatProvider = audioFormatProvider2;
    }

    public VideoSpecification createVideoSpec$AmazonAndroidVideoPlayer_release(ContentConfig content, TimeSpan duration, ImmutableList<String> preferredAudioTrackIds, String initialAudioTrackId, PlaybackResources playbackResources, EPrivacyConsentData ePrivacyConsent) {
        Optional<String> optional;
        Intrinsics.checkNotNullParameter(content, "content");
        Intrinsics.checkNotNullParameter(preferredAudioTrackIds, "preferredAudioTrackIds");
        Intrinsics.checkNotNullParameter(ePrivacyConsent, "ePrivacyConsent");
        VideoSpecification.Builder ePrivacyConsent2 = VideoSpecification.newBuilder().setAudioFormat(audioFormatProvider.determineAudioFormat()).setContentType(VideoTypeUtilsKt.toPlayersContentType(content.getVideoType())).setMediaQuality(getMediaQuality()).setMimeType("video/aiv-asin").setStartTime(getStartTime(content)).setTitleId(content.getTitleId()).setDuration(duration).setPreferredAudioTrackIds(preferredAudioTrackIds).setPlaybackToken(content.getPlaybackToken()).setPlaybackEnvelope(content.getPlaybackEnvelope()).setAudioTrackIds(preferredAudioTrackIds).setPrimaryAudioTrackId(initialAudioTrackId).setDoNotUseStoredAssets(content.getDoNotUseStoredAssets()).setShowAds(content.getShowAds()).setEPrivacyConsent(ePrivacyConsent);
        if (playbackResources != null) {
            ePrivacyConsent2.setDuration(TimeSpan.fromMilliseconds(playbackResources.getAudioVideoUrls().get().getContentUrls().get(0).getDurationMillis()));
            Optional<SyeUrlResponse> syeUrls = playbackResources.getSyeUrls();
            Intrinsics.checkNotNullExpressionValue(syeUrls, "it.syeUrls");
            if (syeUrls.isPresent()) {
                ePrivacyConsent2.setSyeUrls(syeUrls.get());
            }
            Optional<ResponseTitleRendition> responseTitleRendition = playbackResources.getResponseTitleRendition();
            Intrinsics.checkNotNullExpressionValue(responseTitleRendition, "it.responseTitleRendition");
            ResponseTitleRendition responseTitleRenditionOrNull = responseTitleRendition.orNull();
            String strOrNull = null;
            if (responseTitleRenditionOrNull != null && (optional = responseTitleRenditionOrNull.contentId) != null) {
                strOrNull = optional.orNull();
            }
            if (strOrNull != null) {
                ePrivacyConsent2.setVCID(strOrNull);
            }
        }
        VideoSpecification videoSpecificationBuild = ePrivacyConsent2.build();
        Intrinsics.checkNotNullExpressionValue(videoSpecificationBuild, "specBuilder.build()");
        return videoSpecificationBuild;
    }

    public VideoOptions createVideoOptions$AmazonAndroidVideoPlayer_release(ContentConfig content) {
        Intrinsics.checkNotNullParameter(content, "content");
        VideoOptions videoOptionsBuild = VideoOptions.newBuilder().setSessionContext(content.getSessionContext()).build();
        Intrinsics.checkNotNullExpressionValue(videoOptionsBuild, "newBuilder()\n           …ext)\n            .build()");
        return videoOptionsBuild;
    }

    private final MediaQuality getMediaQuality() {
        PlaybackConfig playbackConfig = PlaybackConfig.getInstance();
        NetworkType networkType = NetworkConnectionManager.getInstance().getNetworkType();
        Intrinsics.checkNotNullExpressionValue(networkType, "getInstance().networkType");
        MediaQuality mediaQuality = playbackConfig.getStreamingQualityForNetwork(networkType).or(StreamingConnectionSetting.BEST).getMediaQuality();
        Intrinsics.checkNotNullExpressionValue(mediaQuality, "networkCappedQuality.or(…etting.BEST).mediaQuality");
        return mediaQuality;
    }

    private final TimeSpan getStartTime(ContentConfig content) {
        if (content.getVideoType().isLive$AmazonAndroidVideoPlayer_release() && content.getPosition() == null) {
            TimeSpan timeSpan = TimeSpan.MAX_VALUE;
            Intrinsics.checkNotNullExpressionValue(timeSpan, "{\n            TimeSpan.MAX_VALUE\n        }");
            return timeSpan;
        }
        if (content.getPosition() != null) {
            Long position = content.getPosition();
            Intrinsics.checkNotNull(position);
            if (position.longValue() >= 0) {
                Long position2 = content.getPosition();
                Intrinsics.checkNotNull(position2);
                TimeSpan timeSpanFromMilliseconds = TimeSpan.fromMilliseconds(position2.longValue());
                Intrinsics.checkNotNullExpressionValue(timeSpanFromMilliseconds, "{\n            TimeSpan.f…ent.position!!)\n        }");
                return timeSpanFromMilliseconds;
            }
        }
        TimeSpan timeSpanFromMilliseconds2 = TimeSpan.fromMilliseconds(0L);
        Intrinsics.checkNotNullExpressionValue(timeSpanFromMilliseconds2, "{\n            TimeSpan.f…Milliseconds(0)\n        }");
        return timeSpanFromMilliseconds2;
    }
}
