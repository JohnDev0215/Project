package com.amazon.avod.media.playback.support;

import android.media.MediaCodecInfo;
import android.os.SystemProperties;
import android.util.Pair;
import com.amazon.avod.media.VideoResolution;
import com.amazon.avod.media.framework.MediaSystemSharedContext;
import com.amazon.avod.media.playback.ContentType;
import com.amazon.avod.media.playback.MediaCodecEnumerator;
import com.amazon.avod.media.playback.MediaDefaultConfiguration;
import com.amazon.avod.media.service.PlaybackResourceServiceConstants$HdrFormat;
import com.amazon.avod.playback.capability.DeviceCapability;
import com.amazon.avod.playbackclient.config.PlaybackConfig;
import com.amazon.avod.util.DLog;
import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import com.google.common.collect.ImmutableList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public class DefaultDeviceCapabilityDetector implements DeviceCapabilityDetector {
    static final DeviceCapability DEFAULT_DEVICE_CAPABILITY_INSTANCE = new DeviceCapability(2, DeviceCapability.DEFAULT_CORE_FREQUENCY_IN_MHZ, true, true);
    private static final Pattern DISPLAY_SIZE_PATTERN = Pattern.compile("([0-9]+)x([0-9]+)");
    private static final String DISPLAY_SIZE_SYSTEM_PROPERTY_KEY = "sys.display-size";
    private static final String RO_BUILD_VERSION_NUMBER = "ro.build.version.number";
    private static final String SOFTWARE_DECODER_PREFIX = "OMX.google.";
    private static final String UNKNOWN = "unknown";
    protected final MediaCodecEnumerator mCodecEnumerator;
    private final long mFirmwareVersionNum;
    protected final MediaDefaultConfiguration mMediaConfig;
    private final PlaybackConfig mPlaybackConfig;
    private final RendererSchemeResolverConfig mRendererConfig;
    private final MediaSystemSharedContext mSharedContext;

    @Override // com.amazon.avod.media.playback.support.DeviceCapabilityDetector
    public int getHighestSustainableBitrate() {
        return Integer.MAX_VALUE;
    }

    @Override // com.amazon.avod.media.playback.support.DeviceCapabilityDetector
    public boolean isDolbyDigitalPlusSupported() {
        return true;
    }

    @Override // com.amazon.avod.media.playback.support.DeviceCapabilityDetector
    public boolean isDolbyDigitalPlusWithAtmosSupported() {
        return true;
    }

    @Override // com.amazon.avod.media.playback.support.DeviceCapabilityDetector
    public boolean isHdSupported() {
        return true;
    }

    @Override // com.amazon.avod.media.playback.support.DeviceCapabilityDetector
    public boolean isHdrSupported() {
        return false;
    }

    protected boolean isHevcDisabledByFirmwareVersionLow(long j, long j2) {
        return j < j2;
    }

    @Override // com.amazon.avod.media.playback.support.DeviceCapabilityDetector
    public boolean isMultiKeyDecryptionSupported(@Nonnull ContentType contentType) {
        return false;
    }

    public DefaultDeviceCapabilityDetector(@Nonnull MediaSystemSharedContext mediaSystemSharedContext) {
        this(mediaSystemSharedContext, MediaDefaultConfiguration.getInstance(), MediaCodecEnumerator.getInstance(), PlaybackConfig.getInstance(), RendererSchemeResolverConfig.getInstance());
    }

    DefaultDeviceCapabilityDetector(@Nonnull MediaSystemSharedContext mediaSystemSharedContext, @Nonnull MediaDefaultConfiguration mediaDefaultConfiguration, @Nonnull MediaCodecEnumerator mediaCodecEnumerator, @Nonnull PlaybackConfig playbackConfig, @Nonnull RendererSchemeResolverConfig rendererSchemeResolverConfig) {
        this.mSharedContext = (MediaSystemSharedContext) Preconditions.checkNotNull(mediaSystemSharedContext, "sharedContext");
        this.mMediaConfig = (MediaDefaultConfiguration) Preconditions.checkNotNull(mediaDefaultConfiguration, "mediaConfig");
        this.mCodecEnumerator = (MediaCodecEnumerator) Preconditions.checkNotNull(mediaCodecEnumerator, "codecEnumerator");
        this.mPlaybackConfig = (PlaybackConfig) Preconditions.checkNotNull(playbackConfig, "playbackConfig");
        this.mRendererConfig = (RendererSchemeResolverConfig) Preconditions.checkNotNull(rendererSchemeResolverConfig, "rendererSchemeResolverConfig");
        this.mFirmwareVersionNum = getFirmwareVersionNum();
    }

    @Override // com.amazon.avod.media.playback.support.DeviceCapabilityDetector
    @Nonnull
    public PlaybackSupportInfo getPlaybackSupportInfo() {
        return PlaybackSupportInfo.SUPPORTED_HARDWARE;
    }

    @Override // com.amazon.avod.media.playback.support.DeviceCapabilityDetector
    public DeviceCapability getDeviceCapability() {
        return DEFAULT_DEVICE_CAPABILITY_INSTANCE;
    }

    @Override // com.amazon.avod.media.playback.support.DeviceCapabilityDetector
    public boolean isUhdSupported() {
        return isDisplayUhdCapable();
    }

    public boolean isDisplayUhdCapable() {
        return !this.mRendererConfig.shouldConsiderDisplayResolutionForUHDCapabilityDetection() || validateSystemPropertySupportsUhd(SystemProperties.get(DISPLAY_SIZE_SYSTEM_PROPERTY_KEY, null)) || DisplayModeAndToneMappingEvaluator.isDisplayUhdCapable(this.mSharedContext.getAppContext());
    }

    boolean validateSystemPropertySupportsUhd(@Nullable String str) {
        if (Strings.isNullOrEmpty(str)) {
            DLog.logf("Uhd video not supported; system property %s not found", DISPLAY_SIZE_SYSTEM_PROPERTY_KEY);
            return false;
        }
        Matcher matcher = DISPLAY_SIZE_PATTERN.matcher(str);
        if (!matcher.matches()) {
            DLog.logf("Uhd video not supported; system property %s = %s not readable as screen resolution", DISPLAY_SIZE_SYSTEM_PROPERTY_KEY, str);
            return false;
        }
        VideoResolution videoResolution = new VideoResolution(Integer.parseInt(matcher.group(1)), Integer.parseInt(matcher.group(2)));
        if (videoResolution.getResolutionBand() == VideoResolution.ResolutionBand.ULTRA_HD) {
            return true;
        }
        DLog.logf("Uhd video not supported; system property %s = %s declares insufficient screen resolution %s", DISPLAY_SIZE_SYSTEM_PROPERTY_KEY, str, videoResolution);
        return false;
    }

    @Override // com.amazon.avod.media.playback.support.DeviceCapabilityDetector
    public boolean isHevcSupported() {
        if (isHevcDisabledByFirmwareVersionLow(this.mFirmwareVersionNum, this.mMediaConfig.getMinimumFirmwareVersionNumEnableHEVC())) {
            return false;
        }
        if (this.mRendererConfig.isChromeOsDetectionEnabled() && this.mRendererConfig.isChromeOs(this.mSharedContext.getAppContext())) {
            DLog.warnf("HEVC is disabled as the underlying platform is running ChromeOS.");
            return false;
        }
        Pair<String, MediaCodecInfo> supportedCodec = this.mCodecEnumerator.getSupportedCodec(this.mMediaConfig.getHevcMimeTypes());
        if (supportedCodec == null) {
            DLog.logf("No HEVC decoder found");
            return false;
        }
        MediaCodecInfo mediaCodecInfo = (MediaCodecInfo) supportedCodec.second;
        if (mediaCodecInfo.getName().startsWith(SOFTWARE_DECODER_PREFIX) && this.mPlaybackConfig.isAmazonDevice()) {
            DLog.warnf("Software HEVC decoder detected %s but AIV.Play does not support software decoding.", mediaCodecInfo.getName());
            return false;
        }
        DLog.devf("HEVC decoder detected %s", mediaCodecInfo.getName());
        return true;
    }

    @Override // com.amazon.avod.media.playback.support.DeviceCapabilityDetector
    public boolean isAv1Supported() {
        Pair<String, MediaCodecInfo> supportedCodec = this.mCodecEnumerator.getSupportedCodec(this.mMediaConfig.getAv1MimeTypes());
        if (supportedCodec == null) {
            DLog.logf("No AV1 decoder found");
            return false;
        }
        DLog.devf("AV1 decoder detected %s", ((MediaCodecInfo) supportedCodec.second).getName());
        return true;
    }

    @Override // com.amazon.avod.media.playback.support.DeviceCapabilityDetector
    @Nonnull
    public List<String> getSupportedAudioCodecs() {
        return this.mMediaConfig.getDefaultSupportedAudioCodecs();
    }

    private long getFirmwareVersionNum() {
        try {
            return Long.parseLong(SystemProperties.get(RO_BUILD_VERSION_NUMBER, "unknown"));
        } catch (NumberFormatException unused) {
            DLog.warnf("Firmware version number is not numeric");
            return -1L;
        }
    }

    @Override // com.amazon.avod.media.playback.support.DeviceCapabilityDetector
    @Nonnull
    public ImmutableList<PlaybackResourceServiceConstants$HdrFormat> getSupportedHdrFormats() {
        return ImmutableList.of(PlaybackResourceServiceConstants$HdrFormat.None);
    }
}
