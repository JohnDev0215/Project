package com.amazon.avod.content.smoothstream.streamstate.checkpointpolicy;

import com.amazon.avod.content.ContentSessionContext;
import com.amazon.avod.content.ContentSessionType;
import com.amazon.avod.content.config.SmoothStreamingPlaybackConfig;
import com.amazon.avod.content.downloading.ContentViewPersistence;
import com.amazon.avod.content.smoothstream.manifest.StreamIndex;
import com.amazon.avod.content.smoothstream.quality.defaults.DefaultQualityConfiguration;
import com.amazon.avod.content.smoothstream.storage.SmoothStreamingContentStore;
import com.google.common.base.Preconditions;
import java.util.concurrent.ExecutorService;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class CheckpointPolicyFactory {
    private final SmoothStreamingPlaybackConfig mConfig;
    private final SmoothStreamingContentStore mContentStore;
    private final DefaultQualityConfiguration mDefaultQualityConfiguration;
    private final ExecutorService mExecutor;

    public CheckpointPolicyFactory(SmoothStreamingContentStore smoothStreamingContentStore, ExecutorService executorService, SmoothStreamingPlaybackConfig smoothStreamingPlaybackConfig, DefaultQualityConfiguration defaultQualityConfiguration) {
        this.mConfig = smoothStreamingPlaybackConfig;
        this.mContentStore = smoothStreamingContentStore;
        this.mExecutor = executorService;
        this.mDefaultQualityConfiguration = defaultQualityConfiguration;
    }

    public CheckpointPolicy newCheckpointPolicy(@Nonnull ContentSessionContext contentSessionContext, @Nonnull StreamIndex streamIndex, @Nonnull ContentViewPersistence contentViewPersistence) {
        Preconditions.checkNotNull(contentSessionContext, "context");
        Preconditions.checkNotNull(streamIndex, "streamIndex");
        Preconditions.checkNotNull(contentViewPersistence, "contentViewPersistence");
        switch (AnonymousClass1.$SwitchMap$com$amazon$avod$content$ContentSessionType[contentSessionContext.getSessionType().ordinal()]) {
            case 1:
            case 2:
                StatFileCheckpointPolicy statFileCheckpointPolicy = new StatFileCheckpointPolicy(this.mContentStore, this.mExecutor, this.mDefaultQualityConfiguration);
                statFileCheckpointPolicy.setCheckpointModulus(this.mConfig.getSaveStatusInterval());
                statFileCheckpointPolicy.initialize(contentSessionContext, streamIndex);
                return statFileCheckpointPolicy;
            case 3:
            case 4:
            case 5:
                if (contentSessionContext.getSpecification().isLiveStream()) {
                    return new LiveRefreshCheckpointPolicy(contentViewPersistence);
                }
                return new NoopCheckpointPolicy();
            case 6:
                return new NoopCheckpointPolicy();
            default:
                throw new IllegalArgumentException("Invalid sessionType!");
        }
    }

    /* JADX INFO: renamed from: com.amazon.avod.content.smoothstream.streamstate.checkpointpolicy.CheckpointPolicyFactory$1, reason: invalid class name */
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
                $SwitchMap$com$amazon$avod$content$ContentSessionType[ContentSessionType.PROGRESSIVE_PLAYBACK.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$avod$content$ContentSessionType[ContentSessionType.LIVE_CACHE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$amazon$avod$content$ContentSessionType[ContentSessionType.STREAMING.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$amazon$avod$content$ContentSessionType[ContentSessionType.STREAMING_CONTINUOUS.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$amazon$avod$content$ContentSessionType[ContentSessionType.CONTENT_CACHE.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
        }
    }
}
