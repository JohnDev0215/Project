package com.amazon.avod.media.playback.support;

import com.amazon.avod.media.playback.ContentType;
import com.amazon.avod.media.service.PlaybackResourceServiceConstants$HdrFormat;
import com.amazon.avod.playback.capability.DeviceCapability;
import com.google.common.collect.ImmutableList;
import java.util.List;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public interface DeviceCapabilityDetector {
    @Nullable
    DeviceCapability getDeviceCapability();

    int getHighestSustainableBitrate();

    @Nonnull
    PlaybackSupportInfo getPlaybackSupportInfo();

    @Nonnull
    List<String> getSupportedAudioCodecs();

    @Nonnull
    ImmutableList<PlaybackResourceServiceConstants$HdrFormat> getSupportedHdrFormats();

    boolean isAv1Supported();

    boolean isDolbyDigitalPlusSupported();

    boolean isDolbyDigitalPlusWithAtmosSupported();

    boolean isHdSupported();

    boolean isHdrSupported();

    boolean isHevcSupported();

    boolean isMultiKeyDecryptionSupported(@Nonnull ContentType contentType);

    boolean isUhdSupported();
}
