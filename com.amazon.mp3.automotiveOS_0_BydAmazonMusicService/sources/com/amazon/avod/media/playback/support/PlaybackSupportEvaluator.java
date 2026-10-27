package com.amazon.avod.media.playback.support;

import com.amazon.avod.media.playback.ContentType;
import com.amazon.avod.media.service.PlaybackResourceServiceConstants$HdrFormat;
import com.amazon.avod.playback.renderer.RendererSchemeType;
import com.google.common.collect.ImmutableList;
import java.util.List;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public interface PlaybackSupportEvaluator {
    @Nonnull
    List<String> getDeviceBitrateAdaptationsOverride(@Nullable RendererSchemeType rendererSchemeType);

    @Nonnull
    List<String> getDeviceVideoCodecOverride(@Nullable RendererSchemeType rendererSchemeType);

    @Nonnull
    PlaybackSupportInfo getPlaybackSupportInfo(@Nullable RendererSchemeType rendererSchemeType);

    @Nonnull
    ImmutableList<PlaybackResourceServiceConstants$HdrFormat> getSupportedHdrFormats(@Nullable RendererSchemeType rendererSchemeType);

    boolean isAv1Supported(@Nullable RendererSchemeType rendererSchemeType);

    boolean isDolbyDigitalPlusSupported(@Nullable RendererSchemeType rendererSchemeType);

    boolean isHdSupported(@Nullable RendererSchemeType rendererSchemeType);

    boolean isHdrSupported(@Nullable RendererSchemeType rendererSchemeType);

    boolean isHevcSupported(@Nullable RendererSchemeType rendererSchemeType);

    boolean isMultiKeyDecryptionSupported(@Nullable RendererSchemeType rendererSchemeType, @Nonnull ContentType contentType);

    boolean isUhdSupported(@Nullable RendererSchemeType rendererSchemeType);
}
