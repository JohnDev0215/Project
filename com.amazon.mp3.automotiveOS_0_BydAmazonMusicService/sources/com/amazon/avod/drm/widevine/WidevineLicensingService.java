package com.amazon.avod.drm.widevine;

import com.amazon.avod.drm.event.LicenseAcquisitionEventReporter;
import com.amazon.avod.media.framework.error.DrmLicensingException;
import com.amazon.avod.media.playback.ContentType;
import com.amazon.avod.media.playback.support.ConsumptionType;
import com.amazon.avod.media.playback.support.DrmFramework;
import com.amazon.avod.media.playback.support.ResetObserver;
import com.amazon.avod.playback.renderer.RendererSchemeType;
import com.amazon.video.sdk.player.PlaybackEnvelope;
import java.util.Map;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public interface WidevineLicensingService extends ResetObserver {
    @Nonnull
    WidevineLicenseResponse getLicenseResponse(@Nonnull DrmFramework drmFramework, @Nonnull String str, @Nonnull String str2, @Nonnull ConsumptionType consumptionType, @Nullable String str3, @Nonnull ContentType contentType, boolean z, @Nonnull RendererSchemeType rendererSchemeType, boolean z2, @Nullable String str4, boolean z3, @Nonnull LicenseAcquisitionEventReporter licenseAcquisitionEventReporter, @Nonnull Map<String, String> map, @Nullable PlaybackEnvelope playbackEnvelope) throws DrmLicensingException;
}
