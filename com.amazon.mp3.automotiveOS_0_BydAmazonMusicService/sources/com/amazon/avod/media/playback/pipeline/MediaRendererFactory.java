package com.amazon.avod.media.playback.pipeline;

import com.amazon.avod.media.framework.MediaSystemSharedContext;
import com.amazon.avod.media.playback.MimeTypes;
import com.amazon.avod.media.playback.render.AudioRenderer;
import com.amazon.avod.media.playback.render.MediaRenderer;
import com.amazon.avod.media.playback.render.VideoRenderer;
import com.amazon.avod.media.playback.support.MediaCodecDeviceCapabilityDetector;
import com.amazon.avod.playback.PlaybackException;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
class MediaRendererFactory {
    private static final MediaRendererFactory INSTANCE = new MediaRendererFactory();

    @Nonnull
    public static MediaRendererFactory getInstance() {
        return INSTANCE;
    }

    private MediaRendererFactory() {
    }

    @Nonnull
    public MediaRenderer newMediaRenderer(@Nonnull String str, @Nonnull MediaSystemSharedContext mediaSystemSharedContext, @Nonnull MediaPipelineContext mediaPipelineContext, @Nonnull MediaCodecDeviceCapabilityDetector mediaCodecDeviceCapabilityDetector) throws PlaybackException {
        if (MimeTypes.isAudio(str)) {
            return new AudioRenderer(mediaPipelineContext, mediaCodecDeviceCapabilityDetector);
        }
        if (MimeTypes.isVideo(str)) {
            return new VideoRenderer(mediaSystemSharedContext, mediaPipelineContext);
        }
        throw new PlaybackException(PlaybackException.PlaybackError.INTERNAL_FATAL_ERROR, String.format("Unsupported mimeType %s", str));
    }
}
