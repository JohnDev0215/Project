package com.amazon.avod.content.smoothstream.streamstate.retentionpolicy;

import com.amazon.avod.content.ContentSessionConfiguration;
import com.amazon.avod.content.ContentSessionContext;
import com.amazon.avod.content.ContentSessionType;
import com.amazon.avod.content.config.SmoothStreamingPlaybackConfig;
import com.amazon.avod.experiments.PlaybackWeblabs;
import com.amazon.avod.media.downloadservice.DownloadService;
import com.amazon.avod.util.DLog;
import com.google.common.base.Preconditions;
import java.util.Locale;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class RetentionPolicyFactory {
    private final DownloadService mDownloadService;
    private final ContentSessionConfiguration mPlaybackConfig;
    private final SmoothStreamingPlaybackConfig mSmoothStreamingPlaybackConfig;

    public RetentionPolicyFactory(@Nonnull ContentSessionConfiguration contentSessionConfiguration, @Nonnull DownloadService downloadService) {
        this(contentSessionConfiguration, downloadService, SmoothStreamingPlaybackConfig.INSTANCE);
    }

    private RetentionPolicyFactory(@Nonnull ContentSessionConfiguration contentSessionConfiguration, @Nonnull DownloadService downloadService, @Nonnull SmoothStreamingPlaybackConfig smoothStreamingPlaybackConfig) {
        this.mPlaybackConfig = (ContentSessionConfiguration) Preconditions.checkNotNull(contentSessionConfiguration, "playbackConfig");
        this.mDownloadService = (DownloadService) Preconditions.checkNotNull(downloadService, "downloadService");
        this.mSmoothStreamingPlaybackConfig = (SmoothStreamingPlaybackConfig) Preconditions.checkNotNull(smoothStreamingPlaybackConfig, "smoothStreamingPlaybackConfig");
    }

    @Nonnull
    public RetentionPolicy newRetentionPolicy(@Nonnull ContentSessionContext contentSessionContext) {
        switch (AnonymousClass1.$SwitchMap$com$amazon$avod$content$ContentSessionType[contentSessionContext.getSessionType().ordinal()]) {
            case 1:
            case 2:
                return new InfiniteCachePolicy();
            case 3:
                return new FixedWindowPolicy(contentSessionContext);
            case 4:
                TimeUnit timeUnit = TimeUnit.SECONDS;
                return new CompositeRetentionPolicy(new SlidingWindowPolicy(timeUnit.toNanos(this.mPlaybackConfig.getLiveCacheBufferLookBehindSeconds()), timeUnit.toNanos(this.mPlaybackConfig.getLiveCacheBufferLookAheadSeconds())), createRetentionPolicyForStreaming(contentSessionContext));
            case 5:
            case 6:
                return createRetentionPolicyForStreaming(contentSessionContext);
            default:
                throw new IllegalStateException(String.format(Locale.US, "Unrecognized ContentSessionType %s!", contentSessionContext.getSessionType()));
        }
    }

    @Nonnull
    private RetentionPolicy createRetentionPolicyForStreaming(@Nonnull ContentSessionContext contentSessionContext) {
        if (contentSessionContext.getSpecification().isLiveStream()) {
            LiveRetentionPolicy liveRetentionPolicy = LiveRetentionPolicy.INSTANCE.getLiveRetentionPolicy(this.mSmoothStreamingPlaybackConfig, PlaybackWeblabs.getPlaybackWeblabs().get("AIVPLAYERS_LIVE_SLIDING_WINDOW_POLICY_245364"));
            DLog.logf("RetentionPolicy: selecting %s for %s", liveRetentionPolicy.name(), contentSessionContext.getSpecification().getContentType());
            int i = AnonymousClass1.$SwitchMap$com$amazon$avod$content$smoothstream$streamstate$retentionpolicy$LiveRetentionPolicy[liveRetentionPolicy.ordinal()];
            if (i == 1) {
                return new DynamicSlidingWindowPolicy(contentSessionContext.getState(), contentSessionContext.getHeuristics());
            }
            if (i == 2) {
                return new AscendingLiveEdgeSlidingWindowPolicy(contentSessionContext.getState(), contentSessionContext.getHeuristics(), this.mDownloadService);
            }
            TimeUnit timeUnit = TimeUnit.SECONDS;
            return new SlidingWindowPolicy(timeUnit.toNanos(this.mPlaybackConfig.getBufferLookBehindSeconds()), timeUnit.toNanos(this.mPlaybackConfig.getLiveBufferLookAheadSeconds()));
        }
        DLog.logf("RetentionPolicy: selecting SlidingWindowPolicy for %s", contentSessionContext.getSpecification().getContentType());
        TimeUnit timeUnit2 = TimeUnit.SECONDS;
        return new SlidingWindowPolicy(timeUnit2.toNanos(this.mPlaybackConfig.getBufferLookBehindSeconds()), timeUnit2.toNanos(this.mPlaybackConfig.getBufferLookAheadSeconds()));
    }

    /* JADX INFO: renamed from: com.amazon.avod.content.smoothstream.streamstate.retentionpolicy.RetentionPolicyFactory$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$avod$content$ContentSessionType;
        static final /* synthetic */ int[] $SwitchMap$com$amazon$avod$content$smoothstream$streamstate$retentionpolicy$LiveRetentionPolicy;

        static {
            int[] iArr = new int[LiveRetentionPolicy.values().length];
            $SwitchMap$com$amazon$avod$content$smoothstream$streamstate$retentionpolicy$LiveRetentionPolicy = iArr;
            try {
                iArr[LiveRetentionPolicy.DYNAMIC_DURATION_SLIDING_WINDOW.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$avod$content$smoothstream$streamstate$retentionpolicy$LiveRetentionPolicy[LiveRetentionPolicy.ASCENDING_LIVE_EDGE_SLIDING_WINDOW.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$avod$content$smoothstream$streamstate$retentionpolicy$LiveRetentionPolicy[LiveRetentionPolicy.FIXED_DURATION_SLIDING_WINDOW.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            int[] iArr2 = new int[ContentSessionType.values().length];
            $SwitchMap$com$amazon$avod$content$ContentSessionType = iArr2;
            try {
                iArr2[ContentSessionType.DOWNLOAD.ordinal()] = 1;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$amazon$avod$content$ContentSessionType[ContentSessionType.PROGRESSIVE_PLAYBACK.ordinal()] = 2;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$amazon$avod$content$ContentSessionType[ContentSessionType.CONTENT_CACHE.ordinal()] = 3;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$com$amazon$avod$content$ContentSessionType[ContentSessionType.LIVE_CACHE.ordinal()] = 4;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$com$amazon$avod$content$ContentSessionType[ContentSessionType.STREAMING.ordinal()] = 5;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                $SwitchMap$com$amazon$avod$content$ContentSessionType[ContentSessionType.STREAMING_CONTINUOUS.ordinal()] = 6;
            } catch (NoSuchFieldError unused9) {
            }
        }
    }
}
