package com.amazon.avod.content.smoothstream.quality.timeout;

import com.amazon.avod.content.ContentSessionContext;
import com.amazon.avod.content.ContentSessionType;
import com.amazon.avod.content.config.SmoothStreamingPlaybackConfig;
import com.amazon.avod.content.smoothstream.manifest.StreamIndex;
import com.google.common.collect.Range;

/* JADX INFO: loaded from: classes2.dex */
public class FragmentTimeoutSelectorFactory {
    private final SmoothStreamingPlaybackConfig mPlaybackConfig;

    public FragmentTimeoutSelectorFactory(SmoothStreamingPlaybackConfig smoothStreamingPlaybackConfig) {
        this.mPlaybackConfig = smoothStreamingPlaybackConfig;
    }

    /* JADX INFO: renamed from: com.amazon.avod.content.smoothstream.quality.timeout.FragmentTimeoutSelectorFactory$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$avod$content$ContentSessionType;

        static {
            int[] iArr = new int[ContentSessionType.values().length];
            $SwitchMap$com$amazon$avod$content$ContentSessionType = iArr;
            try {
                iArr[ContentSessionType.DOWNLOAD.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$avod$content$ContentSessionType[ContentSessionType.CONTENT_CACHE.ordinal()] = 2;
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

    public FragmentTimeoutSelector newTimeoutSelector(ContentSessionContext contentSessionContext, StreamIndex streamIndex) {
        switch (AnonymousClass1.$SwitchMap$com$amazon$avod$content$ContentSessionType[contentSessionContext.getSessionType().ordinal()]) {
            case 1:
            case 2:
            case 3:
                long totalNanoSeconds = this.mPlaybackConfig.getDownloadFixedFragmentTimeout().getTotalNanoSeconds();
                return new BufferAwareTimeoutSelector(Range.closed(Long.valueOf(totalNanoSeconds), Long.valueOf(totalNanoSeconds)), 1.0d);
            case 4:
            case 5:
            case 6:
                BufferAwareTimeoutSelector bufferAwareTimeoutSelector = new BufferAwareTimeoutSelector(Range.closed(Long.valueOf(this.mPlaybackConfig.getStreamingMinFragmentTimeout().getTotalNanoSeconds()), Long.valueOf(this.mPlaybackConfig.getStreamingMaxFragmentTimeout().getTotalNanoSeconds())), this.mPlaybackConfig.getStreamingTimeoutScalingFactor());
                if (streamIndex.isVideo()) {
                    return new RetryQualityTrackingFragmentTimeoutSelector(bufferAwareTimeoutSelector);
                }
                return new RetryCountingFragmentTimeoutSelector(bufferAwareTimeoutSelector, this.mPlaybackConfig.getStreamingAudioMaxTriesBeforeRemovingTimeout());
            default:
                throw new IllegalArgumentException("Unrecognized SessionType!");
        }
    }
}
