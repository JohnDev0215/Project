package com.amazon.avod.media.playback.support;

import com.amazon.avod.media.TimeSpan;
import com.amazon.avod.media.framework.error.InvalidRendererTimestampException;
import com.amazon.avod.media.framework.error.MediaException;
import com.amazon.avod.media.playback.VideoRenderingSettings;
import com.amazon.avod.media.playback.util.AudioConfig;
import com.amazon.avod.media.playback.util.VideoConfig;
import com.amazon.avod.media.playback.util.VideoRegion;
import com.amazon.avod.playback.PlaybackException;
import com.amazon.avod.playback.PlaybackZoomLevel;
import com.amazon.avod.playback.drm.DrmScheme;
import com.amazon.avod.playback.sampling.SampleHolder;
import com.amazon.avod.playback.sampling.SampleType;
import com.amazon.avod.qahooks.PlaybackQAEvent;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public interface VideoRenderer {

    public static class RendererStatus {
        public static final String RENDERER_NOT_REUSED = "RendererNotReused";
        public static final String RENDERER_REUSED = "RendererReused";
        public static final String RENDERER_REUSE_NOT_SUPPORTED = "RendererReuseNotSupported";
    }

    void addListener(@Nullable RendererPerformanceEventListener rendererPerformanceEventListener);

    void checkRendererError(boolean z) throws MediaException;

    int configureCodecs(@Nonnull VideoConfig videoConfig, @Nonnull AudioConfig audioConfig, boolean z, @Nonnull TimeSpan timeSpan) throws PlaybackException;

    void configureDecryption(byte[] bArr, @Nullable byte[] bArr2, @Nullable byte[] bArr3, @Nullable String str, @Nonnull DrmScheme drmScheme) throws PlaybackException;

    void configureOutputDisplay(@Nonnull VideoRenderingSettings videoRenderingSettings) throws PlaybackException;

    void dispose(boolean z);

    void flush() throws PlaybackException;

    int getBitrate(SampleType sampleType);

    @Nonnull
    String getCodecInfo();

    long getLastRenderedTimeStampInNanos() throws InvalidRendererTimestampException;

    long getLastTimeStampPassedToRendererInNanos(SampleType sampleType);

    @Nonnull
    String getRendererDebugInfo();

    @Nonnull
    RendererPerformanceData getRendererPerformanceData();

    @Nullable
    SampleType getRequiredNextSampleType();

    @Nonnull
    VideoRegion getVideoRegion();

    boolean hasSpaceForMoreSamples(SampleType sampleType);

    boolean haveStreamsReachedEnd();

    int initialize(String str, String str2, boolean z) throws PlaybackException;

    boolean isOutOfSamples();

    boolean needsMoreSamplesToContinuePlaying();

    boolean needsMoreSamplesToStartPlaying();

    void pause() throws PlaybackException;

    void recreateAudioTrack() throws PlaybackException;

    void recreateVideoTrack();

    void resume() throws PlaybackException;

    void setHdrStatus(boolean z);

    void setPlaybackSpeed(float f);

    void setVideoRegion(@Nonnull VideoRegion videoRegion);

    void setVolume(float f);

    void setZoomLevel(PlaybackZoomLevel playbackZoomLevel, boolean z) throws PlaybackException;

    void submitSample(SampleHolder sampleHolder) throws PlaybackException;

    public static class RendererStatusFlags {
        public static final int AIV_PLAY_RELEASED = 2;
        public static final int FLAGS_NONE = 0;
        public static final int NEW_RENDERER_INSTANCE_CREATED = 8;
        public static final int OMX_CLIENT_POWERED_DOWN = 4;
        public static final int RENDERER_REUSE_NOT_SUPPORTED = 1;

        public static String getReportingString(int i) {
            return (i & 1) != 0 ? RendererStatus.RENDERER_REUSE_NOT_SUPPORTED : ((i & 8) == 0 && (i & 2) == 0 && (i & 4) == 0) ? RendererStatus.RENDERER_REUSED : RendererStatus.RENDERER_NOT_REUSED;
        }

        public static PlaybackQAEvent getQAEvent(int i) {
            if ((i & 1) != 0) {
                return PlaybackQAEvent.RENDERER_REUSE_NOT_SUPPORTED;
            }
            if ((i & 8) != 0 || (i & 2) != 0 || (i & 4) != 0) {
                return PlaybackQAEvent.RENDERER_NOT_REUSED;
            }
            return PlaybackQAEvent.RENDERER_REUSED;
        }
    }

    @Nonnull
    default RendererCapabilities getRendererCapabilities() {
        return RendererCapabilities.NONE;
    }
}
