package com.amazon.avod.playback.renderer.mediacodec;

import com.amazon.avod.media.framework.MediaSystemSharedContext;
import com.amazon.avod.media.playback.android.AndroidVideoSurface;
import com.amazon.avod.media.playback.avsync.MediaClock;
import com.amazon.avod.media.playback.pipeline.AsyncMediaPipeline;
import com.amazon.avod.media.playback.pipeline.MediaPipeline;
import com.amazon.avod.media.playback.pipeline.MediaPipelineContext;
import com.amazon.avod.media.playback.pipeline.SyncMediaPipeline;
import com.amazon.avod.media.playback.source.MediaSource;
import com.amazon.avod.media.playback.support.MediaCodecDeviceCapabilityDetector;
import com.amazon.avod.media.playback.util.VideoConfig;
import java.util.concurrent.ScheduledExecutorService;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
class MediaPipelineFactory {
    private static final MediaPipelineFactory INSTANCE = new MediaPipelineFactory();

    private MediaPipelineFactory() {
    }

    @Nonnull
    public static MediaPipelineFactory getInstance() {
        return INSTANCE;
    }

    @Nonnull
    public MediaPipeline newMediaPipeline(@Nonnull MediaSource mediaSource, @Nonnull MediaSystemSharedContext mediaSystemSharedContext, @Nonnull MediaClock mediaClock, @Nonnull MediaPipelineContext mediaPipelineContext, @Nonnull AndroidVideoSurface androidVideoSurface, boolean z, @Nonnull VideoConfig videoConfig, @Nullable Integer num, @Nonnull ScheduledExecutorService scheduledExecutorService, boolean z2, @Nonnull MediaCodecDeviceCapabilityDetector mediaCodecDeviceCapabilityDetector) {
        if (z2) {
            return new AsyncMediaPipeline(mediaSource, mediaSystemSharedContext, mediaClock, mediaPipelineContext, androidVideoSurface, z, videoConfig, num, scheduledExecutorService, mediaCodecDeviceCapabilityDetector);
        }
        return new SyncMediaPipeline(mediaSource, mediaSystemSharedContext, mediaClock, mediaPipelineContext, androidVideoSurface, z, videoConfig, num, mediaCodecDeviceCapabilityDetector);
    }
}
