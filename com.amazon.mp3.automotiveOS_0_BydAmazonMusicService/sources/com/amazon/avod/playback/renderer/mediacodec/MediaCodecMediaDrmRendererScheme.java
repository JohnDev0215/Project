package com.amazon.avod.playback.renderer.mediacodec;

import android.content.Context;
import com.amazon.avod.media.drm.MediaDrmSanityEvaluator;
import com.amazon.avod.media.drm.MediaDrmSession;
import com.amazon.avod.media.framework.MediaSystemSharedContext;
import com.amazon.avod.media.framework.libraries.LoadableNativeLibrary;
import com.amazon.avod.media.framework.libraries.PlaybackNativeLibrariesLoader;
import com.amazon.avod.media.framework.platform.FileSystem;
import com.amazon.avod.media.framework.profiling.MediaProfiler;
import com.amazon.avod.media.playback.MediaDefaultConfiguration;
import com.amazon.avod.media.playback.support.AvailabilityCode;
import com.amazon.avod.media.playback.support.AvailabilityStatus;
import com.amazon.avod.media.playback.support.DeviceCapabilityDetector;
import com.amazon.avod.media.playback.support.DrmFramework;
import com.amazon.avod.media.playback.support.MediaCodecDeviceCapabilityDetector;
import com.amazon.avod.media.playback.support.RendererScheme;
import com.amazon.avod.media.playback.support.RendererSchemeOverrides;
import com.amazon.avod.media.playback.support.RendererSchemeResolverConfig;
import com.amazon.avod.media.playback.support.VideoRenderer;
import com.amazon.avod.playback.drm.DrmScheme;
import com.amazon.avod.playback.renderer.RendererSchemeType;
import com.amazon.avod.util.DLog;
import com.google.common.base.Preconditions;
import com.visualon.OSMPUtils.voMimeTypes;
import java.util.Collections;
import java.util.Set;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public class MediaCodecMediaDrmRendererScheme implements RendererScheme {
    private static final RendererSchemeType mRendererSchemeType = RendererSchemeType.MEDIACODEC_MEDIADRM;
    private AvailabilityStatus mAvailabilityStatus;
    private MediaCodecDeviceCapabilityDetector mCapabilityDetector;
    private MediaSystemSharedContext mContext;
    private MediaDrmSession mDrmFramework;
    private final MediaDefaultConfiguration mMediaConfig;
    private final Set<LoadableNativeLibrary> mNativeLibraries;
    private final RendererSchemeResolverConfig mRendererSchemeResolverConfig;
    private DrmScheme mSupportedDrmScheme;

    @Override // com.amazon.avod.media.playback.support.ResetObserver
    public void notifyReset() {
    }

    public MediaCodecMediaDrmRendererScheme() {
        this(null, MediaDefaultConfiguration.getInstance(), RendererSchemeResolverConfig.getInstance());
    }

    public MediaCodecMediaDrmRendererScheme(@Nullable DrmScheme drmScheme, @Nonnull MediaDefaultConfiguration mediaDefaultConfiguration, @Nonnull RendererSchemeResolverConfig rendererSchemeResolverConfig) {
        this.mNativeLibraries = Collections.emptySet();
        this.mSupportedDrmScheme = drmScheme;
        this.mMediaConfig = (MediaDefaultConfiguration) Preconditions.checkNotNull(mediaDefaultConfiguration, "mediaConfig");
        this.mRendererSchemeResolverConfig = (RendererSchemeResolverConfig) Preconditions.checkNotNull(rendererSchemeResolverConfig, "rendererSchemeResolverConfig");
    }

    @Override // com.amazon.avod.media.playback.support.RendererScheme
    @Nonnull
    public AvailabilityStatus isAvailable(@Nonnull Context context) {
        if (this.mSupportedDrmScheme == null) {
            if (this.mRendererSchemeResolverConfig.isAutoSchemeResolutionEnabled()) {
                this.mSupportedDrmScheme = DrmScheme.WIDEVINE;
            } else {
                this.mSupportedDrmScheme = this.mMediaConfig.isWidevineEnabled() ? DrmScheme.WIDEVINE : DrmScheme.PLAYREADY;
            }
        }
        AvailabilityStatus availabilityStatusIsAvailableForDrmScheme = isAvailableForDrmScheme(this.mSupportedDrmScheme, context);
        this.mAvailabilityStatus = availabilityStatusIsAvailableForDrmScheme;
        return availabilityStatusIsAvailableForDrmScheme;
    }

    @Override // com.amazon.avod.media.playback.support.RendererScheme
    public void initialize(@Nonnull MediaSystemSharedContext mediaSystemSharedContext, @Nonnull MediaProfiler mediaProfiler, @Nonnull FileSystem fileSystem, @Nonnull PlaybackNativeLibrariesLoader playbackNativeLibrariesLoader, @Nonnull RendererSchemeOverrides rendererSchemeOverrides) {
        this.mContext = (MediaSystemSharedContext) Preconditions.checkNotNull(mediaSystemSharedContext, "context");
        MediaDrmSession mediaDrmSession = new MediaDrmSession(rendererSchemeOverrides.getForceWidevine() & (!rendererSchemeOverrides.getForceMediaCodecSwPlayReady()));
        this.mDrmFramework = mediaDrmSession;
        mediaDrmSession.preLoadMediaDrm();
        this.mCapabilityDetector = new MediaCodecDeviceCapabilityDetector(mediaSystemSharedContext, this.mDrmFramework);
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

    @Nonnull
    private AvailabilityStatus isAvailableForDrmScheme(@Nonnull DrmScheme drmScheme, @Nonnull Context context) {
        Preconditions.checkNotNull(drmScheme, "drmScheme");
        if (!MediaCodecSanityEvaluator.evaluateDecoderSanity(voMimeTypes.VOAUDIO_AAC, false, false, context)) {
            DLog.warnf("AAC decoder not supported by MediaCodec!");
            return new AvailabilityStatus(AvailabilityCode.AUDIO_CODEC_UNSUPPORTED);
        }
        if (!MediaCodecSanityEvaluator.evaluateDecoderSanity("video/avc", true, true, context)) {
            DLog.warnf("AVC decoder not supported by MediaCodec!");
            return new AvailabilityStatus(AvailabilityCode.VIDEO_AVC_CODEC_UNSUPPORTED);
        }
        AvailabilityStatus availabilityStatusEvaluateDrmSanity = MediaDrmSanityEvaluator.evaluateDrmSanity(drmScheme, context);
        return (availabilityStatusEvaluateDrmSanity.isAvailable() && this.mRendererSchemeResolverConfig.isAutoSchemeResolutionEnabled()) ? getProfiledAvailabilityStatus(context, availabilityStatusEvaluateDrmSanity.getMaxHdcpLevel()) : availabilityStatusEvaluateDrmSanity;
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x003e  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x004e  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x0055  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x0062  */
    @javax.annotation.Nonnull
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private com.amazon.avod.media.playback.support.AvailabilityStatus getProfiledAvailabilityStatus(@javax.annotation.Nonnull android.content.Context r7, int r8) throws java.lang.Throwable {
        /*
            r6 = this;
            r0 = 2
            if (r8 >= r0) goto L10
            java.lang.String r7 = "MediaDrm HDCP level: %s not sufficient for HD playback!"
            com.amazon.avod.util.DLog.warnf(r7)
            com.amazon.avod.media.playback.support.AvailabilityStatus r7 = new com.amazon.avod.media.playback.support.AvailabilityStatus
            com.amazon.avod.media.playback.support.AvailabilityCode r8 = com.amazon.avod.media.playback.support.AvailabilityCode.HDCP_INSUFFICIENT_SECURITY_LEVEL
            r7.<init>(r8)
            return r7
        L10:
            java.lang.String r0 = "video/hevc"
            r1 = 1
            boolean r0 = com.amazon.avod.playback.renderer.mediacodec.MediaCodecSanityEvaluator.evaluateDecoderSanity(r0, r1, r1, r7)
            com.amazon.avod.media.playback.support.RendererSchemeResolverConfig r2 = r6.mRendererSchemeResolverConfig
            boolean r2 = r2.isDisplayToneMappingEvaluationEnabled()
            r3 = 5
            r4 = 0
            if (r2 == 0) goto L30
            if (r8 < r3) goto L35
            com.google.common.collect.ImmutableList r2 = com.amazon.avod.media.playback.support.DisplayModeAndToneMappingEvaluator.getSupportedHdrFormats(r7)
            boolean r2 = r2.isEmpty()
            if (r2 != 0) goto L35
            r2 = r1
            goto L36
        L30:
            java.lang.String r2 = "Display tone mapping evaluation is disabled by config, HDR = None."
            com.amazon.avod.util.DLog.warnf(r2)
        L35:
            r2 = r4
        L36:
            com.amazon.avod.media.playback.support.RendererSchemeResolverConfig r5 = r6.mRendererSchemeResolverConfig
            boolean r5 = r5.isDisplayResolutionEvaluationEnabled()
            if (r5 == 0) goto L4e
            boolean r7 = com.amazon.avod.media.playback.support.DisplayModeAndToneMappingEvaluator.isDisplayUhdCapable(r7)
            if (r7 == 0) goto L53
            boolean r7 = com.amazon.avod.media.playback.support.DisplayModeAndToneMappingEvaluator.isDecoderUhdCapable()
            if (r7 == 0) goto L53
            if (r8 < r3) goto L53
            r4 = r1
            goto L53
        L4e:
            java.lang.String r7 = "Display resolution evaluation is disabled by config, UHD = false."
            com.amazon.avod.util.DLog.warnf(r7)
        L53:
            if (r0 == 0) goto L62
            if (r2 == 0) goto L5f
            if (r4 == 0) goto L5c
            com.amazon.avod.media.framework.config.profiles.ThirdPartyProfileName r7 = com.amazon.avod.media.framework.config.profiles.ThirdPartyProfileName.UHD_HDR
            goto L64
        L5c:
            com.amazon.avod.media.framework.config.profiles.ThirdPartyProfileName r7 = com.amazon.avod.media.framework.config.profiles.ThirdPartyProfileName.HDR
            goto L64
        L5f:
            com.amazon.avod.media.framework.config.profiles.ThirdPartyProfileName r7 = com.amazon.avod.media.framework.config.profiles.ThirdPartyProfileName.HD_HEVC
            goto L64
        L62:
            com.amazon.avod.media.framework.config.profiles.ThirdPartyProfileName r7 = com.amazon.avod.media.framework.config.profiles.ThirdPartyProfileName.HD
        L64:
            com.amazon.avod.media.playback.support.AvailabilityStatus r0 = new com.amazon.avod.media.playback.support.AvailabilityStatus
            com.amazon.avod.media.playback.support.AvailabilityCode r2 = com.amazon.avod.media.playback.support.AvailabilityCode.AVAILABLE
            r0.<init>(r2, r7, r8, r1)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.avod.playback.renderer.mediacodec.MediaCodecMediaDrmRendererScheme.getProfiledAvailabilityStatus(android.content.Context, int):com.amazon.avod.media.playback.support.AvailabilityStatus");
    }
}
