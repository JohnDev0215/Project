package com.amazon.avod.content.downloading;

import com.amazon.avod.content.ContentSessionContext;
import com.amazon.avod.content.ContentSessionType;
import com.amazon.avod.content.config.SmoothStreamingPlaybackConfig;
import com.amazon.avod.content.smoothstream.manifest.AudioQualityLevel;
import com.amazon.avod.content.smoothstream.manifest.StreamIndex;
import com.amazon.avod.content.smoothstream.manifest.VideoQualityLevel;
import com.amazon.avod.content.smoothstream.quality.defaults.DefaultQualityConfigurationBase;
import com.amazon.avod.media.MediaQuality;
import com.amazon.avod.media.VideoResolution;
import com.amazon.avod.media.playback.internal.config.MediaQualityConfig;
import com.google.common.base.Preconditions;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class DefaultContentQualityConfiguration extends DefaultQualityConfigurationBase {
    private final SmoothStreamingPlaybackConfig mPlaybackConfig;
    private final MediaQualityConfig mQualityConfig;

    public DefaultContentQualityConfiguration() {
        this(MediaQualityConfig.INSTANCE, SmoothStreamingPlaybackConfig.INSTANCE);
    }

    DefaultContentQualityConfiguration(@Nonnull MediaQualityConfig mediaQualityConfig, @Nonnull SmoothStreamingPlaybackConfig smoothStreamingPlaybackConfig) {
        this.mQualityConfig = (MediaQualityConfig) Preconditions.checkNotNull(mediaQualityConfig, "qualityConfig");
        this.mPlaybackConfig = (SmoothStreamingPlaybackConfig) Preconditions.checkNotNull(smoothStreamingPlaybackConfig, "playbackConfig");
    }

    @Override // com.amazon.avod.content.smoothstream.quality.defaults.DefaultQualityConfiguration
    public AudioQualityLevel getAudioQuality(ContentSessionContext contentSessionContext, StreamIndex streamIndex) {
        return contentSessionContext.getStreamSelections().getSelectedAudioQualityLevelForStreamIndex(streamIndex);
    }

    @Override // com.amazon.avod.content.smoothstream.quality.defaults.DefaultQualityConfiguration
    public VideoQualityLevel getVideoQuality(ContentSessionContext contentSessionContext) {
        ContentSessionType sessionType = contentSessionContext.getSessionType();
        MediaQuality mediaQuality = contentSessionContext.getState().getMediaQuality();
        String fourCC = contentSessionContext.getStreamSelections().getVideoStream().getFourCC();
        StreamIndex videoStream = contentSessionContext.getStreamSelections().getVideoStream();
        return videoStream.getClosestQualityLevelWithResolutionCap(contentSessionContext.getState().getConsumptionHead(videoStream.getIndex()), getVideoBitrate(sessionType, mediaQuality, contentSessionContext.getMaxResolution(), fourCC), contentSessionContext.getAvailableResolutions()[r1.length - 1]);
    }

    /* JADX INFO: renamed from: com.amazon.avod.content.downloading.DefaultContentQualityConfiguration$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$avod$content$ContentSessionType;

        static {
            int[] iArr = new int[ContentSessionType.values().length];
            $SwitchMap$com$amazon$avod$content$ContentSessionType = iArr;
            try {
                iArr[ContentSessionType.CONTENT_CACHE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$avod$content$ContentSessionType[ContentSessionType.DOWNLOAD.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$avod$content$ContentSessionType[ContentSessionType.PROGRESSIVE_PLAYBACK.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$amazon$avod$content$ContentSessionType[ContentSessionType.LIVE_CACHE.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$amazon$avod$content$ContentSessionType[ContentSessionType.STREAMING.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$amazon$avod$content$ContentSessionType[ContentSessionType.STREAMING_CONTINUOUS.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
        }
    }

    private int getVideoBitrate(ContentSessionType contentSessionType, MediaQuality mediaQuality, VideoResolution videoResolution, String str) {
        int i = AnonymousClass1.$SwitchMap$com$amazon$avod$content$ContentSessionType[contentSessionType.ordinal()];
        if (i == 1) {
            return this.mQualityConfig.getVideoCachingBitrateCap(mediaQuality, videoResolution, str);
        }
        if (i == 2 || i == 3) {
            return this.mQualityConfig.getVideoDownloadBitrateCap(mediaQuality, videoResolution, str);
        }
        return this.mPlaybackConfig.getMaxVideoBitrateWhenBufferEmpty();
    }
}
