package com.amazon.avod.playback.renderer.mediacodec;

import android.content.Context;
import com.amazon.avod.drm.SoftwarePlayReadyNativeLibrary;
import com.amazon.avod.media.drm.SoftwarePlayReadyDrmSession;
import com.amazon.avod.media.framework.MediaSystemSharedContext;
import com.amazon.avod.media.framework.config.profiles.ThirdPartyProfileName;
import com.amazon.avod.media.framework.libraries.LoadableNativeLibrary;
import com.amazon.avod.media.framework.libraries.PlaybackNativeLibrariesLoader;
import com.amazon.avod.media.framework.platform.FileSystem;
import com.amazon.avod.media.framework.profiling.MediaProfiler;
import com.amazon.avod.media.playback.support.AvailabilityCode;
import com.amazon.avod.media.playback.support.AvailabilityStatus;
import com.amazon.avod.media.playback.support.DeviceCapabilityDetector;
import com.amazon.avod.media.playback.support.DrmFramework;
import com.amazon.avod.media.playback.support.MediaCodecDeviceCapabilityDetector;
import com.amazon.avod.media.playback.support.RendererScheme;
import com.amazon.avod.media.playback.support.RendererSchemeOverrides;
import com.amazon.avod.media.playback.support.RendererSchemeResolverConfig;
import com.amazon.avod.media.playback.support.VideoRenderer;
import com.amazon.avod.playback.renderer.RendererSchemeType;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableSet;
import com.visualon.OSMPUtils.voMimeTypes;
import java.util.Set;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class MediaCodecSoftwarePlayReadyRendererScheme implements RendererScheme {
    private AvailabilityStatus mAvailabilityStatus;

    @Override // com.amazon.avod.media.playback.support.RendererScheme
    @Nonnull
    public AvailabilityStatus isAvailable(@Nonnull Context context) {
        AvailabilityStatus availabilityStatusIsAvailable = MediaCodecRendererSchemeApi17.INSTANCE.isAvailable(context);
        this.mAvailabilityStatus = availabilityStatusIsAvailable;
        return availabilityStatusIsAvailable;
    }

    @Override // com.amazon.avod.media.playback.support.RendererScheme
    public void initialize(@Nonnull MediaSystemSharedContext mediaSystemSharedContext, @Nonnull MediaProfiler mediaProfiler, @Nonnull FileSystem fileSystem, @Nonnull PlaybackNativeLibrariesLoader playbackNativeLibrariesLoader, @Nonnull RendererSchemeOverrides rendererSchemeOverrides) {
        MediaCodecRendererSchemeApi17.INSTANCE.initialize(mediaSystemSharedContext, mediaProfiler, fileSystem, playbackNativeLibrariesLoader, rendererSchemeOverrides);
    }

    @Override // com.amazon.avod.media.playback.support.RendererScheme
    @Nonnull
    public Set<LoadableNativeLibrary> getNativeLibraries() {
        return MediaCodecRendererSchemeApi17.INSTANCE.getNativeLibraries();
    }

    @Override // com.amazon.avod.media.playback.support.RendererScheme
    @Nonnull
    public DrmFramework getDrmFramework() {
        return MediaCodecRendererSchemeApi17.INSTANCE.getDrmFramework();
    }

    @Override // com.amazon.avod.media.playback.support.RendererScheme
    @Nonnull
    public DeviceCapabilityDetector getCapabilityDetector() {
        return MediaCodecRendererSchemeApi17.INSTANCE.getCapabilityDetector();
    }

    @Override // com.amazon.avod.media.playback.support.RendererScheme
    @Nonnull
    public VideoRenderer createRenderer() {
        return MediaCodecRendererSchemeApi17.INSTANCE.createRenderer();
    }

    @Override // com.amazon.avod.media.playback.support.RendererScheme
    @Nonnull
    public RendererSchemeType getSchemeType() {
        return MediaCodecRendererSchemeApi17.INSTANCE.getSchemeType();
    }

    @Override // com.amazon.avod.media.playback.support.RendererScheme
    @Nonnull
    public AvailabilityStatus getAvailabilityStatus() {
        Preconditions.checkState(this.mAvailabilityStatus != null, "Must call isAvailable() before calling getAvailabilityStatus()");
        return this.mAvailabilityStatus;
    }

    @Override // com.amazon.avod.media.playback.support.ResetObserver
    public void notifyReset() {
        MediaCodecRendererSchemeApi17.INSTANCE.notifyReset();
    }

    private static class MediaCodecRendererSchemeApi17 implements RendererScheme {
        private static final MediaCodecRendererSchemeApi17 INSTANCE = new MediaCodecRendererSchemeApi17();
        private static final RendererSchemeType mRendererSchemeType = RendererSchemeType.MEDIACODEC_SOFTWAREPLAYREADY;
        private AvailabilityStatus mAvailabilityStatus;
        private MediaCodecDeviceCapabilityDetector mCapabilityDetector;
        private MediaSystemSharedContext mContext;
        private SoftwarePlayReadyDrmSession mDrmFramework;
        private final Set<LoadableNativeLibrary> mNativeLibraries = ImmutableSet.of(new SoftwarePlayReadyNativeLibrary());
        private final RendererSchemeResolverConfig mRendererSchemeResolverConfig = RendererSchemeResolverConfig.getInstance();

        @Override // com.amazon.avod.media.playback.support.ResetObserver
        public void notifyReset() {
        }

        private MediaCodecRendererSchemeApi17() {
        }

        @Override // com.amazon.avod.media.playback.support.RendererScheme
        @Nonnull
        public AvailabilityStatus isAvailable(@Nonnull Context context) {
            if (!MediaCodecSanityEvaluator.evaluateDecoderSanity(voMimeTypes.VOAUDIO_AAC, false, false, context)) {
                AvailabilityStatus availabilityStatus = new AvailabilityStatus(AvailabilityCode.AUDIO_CODEC_UNSUPPORTED);
                this.mAvailabilityStatus = availabilityStatus;
                return availabilityStatus;
            }
            if (!MediaCodecSanityEvaluator.evaluateDecoderSanity("video/avc", false, false, context)) {
                AvailabilityStatus availabilityStatus2 = new AvailabilityStatus(AvailabilityCode.VIDEO_AVC_CODEC_UNSUPPORTED);
                this.mAvailabilityStatus = availabilityStatus2;
                return availabilityStatus2;
            }
            if (this.mRendererSchemeResolverConfig.isHevcEvaluationForMediaCodecSoftwarePlayreadyEnabled() && !MediaCodecSanityEvaluator.evaluateDecoderSanity("video/hevc", false, false, context)) {
                AvailabilityStatus availabilityStatus3 = new AvailabilityStatus(AvailabilityCode.VIDEO_HEVC_CODEC_UNSUPPORTED);
                this.mAvailabilityStatus = availabilityStatus3;
                return availabilityStatus3;
            }
            AvailabilityStatus availabilityStatus4 = new AvailabilityStatus(AvailabilityCode.AVAILABLE, ThirdPartyProfileName.SD_MEDIACODEC_SOFTWARE_PLAYREADY, this.mRendererSchemeResolverConfig.isAutoSchemeResolutionEnabled());
            this.mAvailabilityStatus = availabilityStatus4;
            return availabilityStatus4;
        }

        @Override // com.amazon.avod.media.playback.support.RendererScheme
        public void initialize(@Nonnull MediaSystemSharedContext mediaSystemSharedContext, @Nonnull MediaProfiler mediaProfiler, @Nonnull FileSystem fileSystem, @Nonnull PlaybackNativeLibrariesLoader playbackNativeLibrariesLoader, @Nonnull RendererSchemeOverrides rendererSchemeOverrides) {
            this.mContext = (MediaSystemSharedContext) Preconditions.checkNotNull(mediaSystemSharedContext, "context");
            this.mDrmFramework = new SoftwarePlayReadyDrmSession(mediaSystemSharedContext.getAppContext(), fileSystem, playbackNativeLibrariesLoader);
            this.mCapabilityDetector = new MediaCodecDeviceCapabilityDetector(mediaSystemSharedContext, this.mDrmFramework);
            this.mDrmFramework.initialize();
        }

        @Override // com.amazon.avod.media.playback.support.RendererScheme
        @Nonnull
        public Set<LoadableNativeLibrary> getNativeLibraries() {
            return this.mNativeLibraries;
        }

        @Override // com.amazon.avod.media.playback.support.RendererScheme
        @Nonnull
        public DrmFramework getDrmFramework() {
            return this.mDrmFramework;
        }

        @Override // com.amazon.avod.media.playback.support.RendererScheme
        @Nonnull
        public DeviceCapabilityDetector getCapabilityDetector() {
            return this.mCapabilityDetector;
        }

        @Override // com.amazon.avod.media.playback.support.RendererScheme
        @Nonnull
        public VideoRenderer createRenderer() {
            return new MediaCodecRenderer(this.mContext, this.mDrmFramework, this.mCapabilityDetector);
        }

        @Override // com.amazon.avod.media.playback.support.RendererScheme
        @Nonnull
        public RendererSchemeType getSchemeType() {
            return mRendererSchemeType;
        }

        @Override // com.amazon.avod.media.playback.support.RendererScheme
        @Nonnull
        public AvailabilityStatus getAvailabilityStatus() {
            Preconditions.checkState(this.mAvailabilityStatus != null, "Must call isAvailable() before calling getAvailabilityStatus()");
            return this.mAvailabilityStatus;
        }
    }
}
