package com.amazon.avod.playback.renderer.visualon;

import android.os.Build;
import com.amazon.avod.content.ContentException;
import com.amazon.avod.content.config.SmoothStreamingDeviceCapabilityConfig;
import com.amazon.avod.content.smoothstream.quality.capability.RuntimeDeviceCapabilityBasedBitrateResolver;
import com.amazon.avod.media.framework.libraries.PlaybackNativeLibrariesLoader;
import com.amazon.avod.media.playback.ContentType;
import com.amazon.avod.media.playback.support.DeviceCapabilityDetector;
import com.amazon.avod.media.playback.support.PlaybackSupportInfo;
import com.amazon.avod.media.service.PlaybackResourceServiceConstants$HdrFormat;
import com.amazon.avod.playback.capability.DeviceCapability;
import com.amazon.avod.util.DLog;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import java.util.List;
import javax.annotation.Nonnull;
import javax.inject.Provider;

/* JADX INFO: loaded from: classes2.dex */
public class VisualOnDeviceCapabilityDetector implements DeviceCapabilityDetector {
    private static final int MINIMUM_SUPPORTED_SDK_VERSION = 15;
    private final RuntimeDeviceCapabilityBasedBitrateResolver mBitrateResolver;
    private final Provider<DeviceCapability> mDeviceCapabilityProvider;
    private final VisualOnDeviceCapabilityConfig mVisualOnConfig;

    @Override // com.amazon.avod.media.playback.support.DeviceCapabilityDetector
    public boolean isAv1Supported() {
        return false;
    }

    @Override // com.amazon.avod.media.playback.support.DeviceCapabilityDetector
    public boolean isDolbyDigitalPlusSupported() {
        return false;
    }

    @Override // com.amazon.avod.media.playback.support.DeviceCapabilityDetector
    public boolean isDolbyDigitalPlusWithAtmosSupported() {
        return false;
    }

    @Override // com.amazon.avod.media.playback.support.DeviceCapabilityDetector
    public boolean isHdSupported() {
        return false;
    }

    @Override // com.amazon.avod.media.playback.support.DeviceCapabilityDetector
    public boolean isHdrSupported() {
        return false;
    }

    @Override // com.amazon.avod.media.playback.support.DeviceCapabilityDetector
    public boolean isMultiKeyDecryptionSupported(@Nonnull ContentType contentType) {
        return false;
    }

    @Override // com.amazon.avod.media.playback.support.DeviceCapabilityDetector
    public boolean isUhdSupported() {
        return false;
    }

    public VisualOnDeviceCapabilityDetector(PlaybackNativeLibrariesLoader playbackNativeLibrariesLoader) {
        this(new RuntimeDeviceCapabilityBasedBitrateResolver(SmoothStreamingDeviceCapabilityConfig.INSTANCE), VisualOnDeviceCapabilityConfig.INSTANCE, new VisualOnDeviceCapabilityProvider(playbackNativeLibrariesLoader));
    }

    VisualOnDeviceCapabilityDetector(@Nonnull RuntimeDeviceCapabilityBasedBitrateResolver runtimeDeviceCapabilityBasedBitrateResolver, @Nonnull VisualOnDeviceCapabilityConfig visualOnDeviceCapabilityConfig, @Nonnull Provider<DeviceCapability> provider) {
        this.mBitrateResolver = (RuntimeDeviceCapabilityBasedBitrateResolver) Preconditions.checkNotNull(runtimeDeviceCapabilityBasedBitrateResolver);
        this.mVisualOnConfig = (VisualOnDeviceCapabilityConfig) Preconditions.checkNotNull(visualOnDeviceCapabilityConfig);
        this.mDeviceCapabilityProvider = (Provider) Preconditions.checkNotNull(provider);
    }

    @Override // com.amazon.avod.media.playback.support.DeviceCapabilityDetector
    @Nonnull
    public PlaybackSupportInfo getPlaybackSupportInfo() {
        return getPlaybackSupportInfo(Build.VERSION.SDK_INT);
    }

    @SuppressFBWarnings(justification = "Findbug detected wrong here", value = {"RCN_REDUNDANT_NULLCHECK_OF_NONNULL_VALUE"})
    PlaybackSupportInfo getPlaybackSupportInfo(int i) {
        if (i < 15) {
            return PlaybackSupportInfo.NOT_SUPPORTED_PLATFORM_OS;
        }
        if (!this.mVisualOnConfig.isSupportedArchitecture(VisualOnCpuArchitecture.INSTANCE)) {
            return PlaybackSupportInfo.NOT_SUPPORTED_HARDWARE_ARCHITECTURE;
        }
        if (this.mVisualOnConfig.isDeviceBlackListed(getDeviceTypeInfo())) {
            return PlaybackSupportInfo.NOT_SUPPORTED_BLACK_LISTED;
        }
        if (getDeviceCapability() == null) {
            return PlaybackSupportInfo.NOT_SUPPORTED_NATIVE_LIB_LOAD_TIMEOUT;
        }
        if (isLimitedCapabilityDevice()) {
            return PlaybackSupportInfo.NOT_SUPPORTED_LIMITED_CAPABILITIES;
        }
        if (getHighestSustainableBitrate() < this.mBitrateResolver.getLowestPlaybackBitrate()) {
            return PlaybackSupportInfo.SUPPORTED_SOFTWARE_PERFORMANCE_DEGRADATION;
        }
        return PlaybackSupportInfo.SUPPORTED_SOFTWARE;
    }

    @Nonnull
    private String getDeviceTypeInfo() {
        return Build.MANUFACTURER + " " + Build.MODEL;
    }

    private boolean isLimitedCapabilityDevice() {
        return (getDeviceCapability().getNumberOfCores() == 1) && (getDeviceCapability().getCoreFrequency() < this.mVisualOnConfig.getLimitedCapabilityCoreFrequency());
    }

    @Override // com.amazon.avod.media.playback.support.DeviceCapabilityDetector
    public int getHighestSustainableBitrate() {
        try {
            return this.mBitrateResolver.getHighestSustainableBitrate(getDeviceCapability());
        } catch (ContentException e) {
            DLog.errorf("Getting the sustainable bitrate failed with: %s", e);
            return Integer.MAX_VALUE;
        }
    }

    @Override // com.amazon.avod.media.playback.support.DeviceCapabilityDetector
    public DeviceCapability getDeviceCapability() {
        return this.mDeviceCapabilityProvider.get();
    }

    @Override // com.amazon.avod.media.playback.support.DeviceCapabilityDetector
    public boolean isHevcSupported() {
        DeviceCapability deviceCapability = getDeviceCapability();
        if (deviceCapability != null) {
            return deviceCapability.getNeonSupport();
        }
        return false;
    }

    @Override // com.amazon.avod.media.playback.support.DeviceCapabilityDetector
    @Nonnull
    public List<String> getSupportedAudioCodecs() {
        return this.mVisualOnConfig.getDefaultSupportedAudioCodecs();
    }

    @Override // com.amazon.avod.media.playback.support.DeviceCapabilityDetector
    @Nonnull
    public ImmutableList<PlaybackResourceServiceConstants$HdrFormat> getSupportedHdrFormats() {
        return ImmutableList.of(PlaybackResourceServiceConstants$HdrFormat.None);
    }
}
