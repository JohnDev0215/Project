package com.amazon.avod.content.smoothstream.downloading;

import com.amazon.avod.content.ContentSessionType;
import com.amazon.avod.content.config.SmoothStreamingPlaybackConfig;
import com.amazon.avod.content.smoothstream.SmoothStreamingURI;
import com.amazon.avod.media.downloadservice.DownloadRequestPriority;
import com.amazon.avod.media.downloadservice.PriorityTier;
import com.amazon.avod.util.DataUnit;
import com.google.common.base.Preconditions;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public class SmoothStreamingRequestPrioritizationUtils {
    private final PriorityTier mLiveCachePriorityTier;

    public SmoothStreamingRequestPrioritizationUtils(@Nonnull SmoothStreamingPlaybackConfig smoothStreamingPlaybackConfig) {
        this(smoothStreamingPlaybackConfig.getLiveCacheRequestPriorityTier());
    }

    SmoothStreamingRequestPrioritizationUtils(@Nonnull PriorityTier priorityTier) {
        this.mLiveCachePriorityTier = (PriorityTier) Preconditions.checkNotNull(priorityTier, "liveCachePriorityTier");
    }

    @Nonnull
    public DownloadRequestPriority computeFragmentPriority(@Nonnull ContentSessionType contentSessionType, @Nonnull SmoothStreamingURI smoothStreamingURI, long j) {
        return computePriority(contentSessionType, smoothStreamingURI, j, (int) DataUnit.BITS.toBytes(TimeUnit.NANOSECONDS.toSeconds(smoothStreamingURI.getDurationInNanos()) * ((long) smoothStreamingURI.getQualityLevel().getBitrate())));
    }

    @Nonnull
    public DownloadRequestPriority computeManifestPriority(@Nonnull ContentSessionType contentSessionType) {
        return computePriority(contentSessionType, null, 0L, 1);
    }

    @Nonnull
    DownloadRequestPriority computePriority(@Nonnull ContentSessionType contentSessionType, @Nullable SmoothStreamingURI smoothStreamingURI, long j, int i) {
        PriorityTier priorityTier;
        long jAbs = 0;
        if (smoothStreamingURI != null && !smoothStreamingURI.isInitFragment() && !smoothStreamingURI.isPsshFragment()) {
            long presentationTimeInNanos = smoothStreamingURI.getPresentationTimeInNanos();
            if (j > smoothStreamingURI.getDurationInNanos() + smoothStreamingURI.getPresentationTimeInNanos()) {
                jAbs = Long.MAX_VALUE;
            } else if (j <= presentationTimeInNanos) {
                jAbs = Math.abs(presentationTimeInNanos - j);
            }
        }
        int i2 = AnonymousClass1.$SwitchMap$com$amazon$avod$content$ContentSessionType[contentSessionType.ordinal()];
        if (i2 == 1 || i2 == 2) {
            priorityTier = PriorityTier.PRIMARY;
        } else if (i2 == 3) {
            priorityTier = this.mLiveCachePriorityTier;
        } else {
            priorityTier = PriorityTier.BACKGROUND;
        }
        return new DownloadRequestPriority(priorityTier, i, jAbs);
    }

    /* JADX INFO: renamed from: com.amazon.avod.content.smoothstream.downloading.SmoothStreamingRequestPrioritizationUtils$1, reason: invalid class name */
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
                $SwitchMap$com$amazon$avod$content$ContentSessionType[ContentSessionType.PROGRESSIVE_PLAYBACK.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$avod$content$ContentSessionType[ContentSessionType.LIVE_CACHE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$amazon$avod$content$ContentSessionType[ContentSessionType.DOWNLOAD.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$amazon$avod$content$ContentSessionType[ContentSessionType.CONTENT_CACHE.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$amazon$avod$content$ContentSessionType[ContentSessionType.STREAMING_CONTINUOUS.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
        }
    }
}
