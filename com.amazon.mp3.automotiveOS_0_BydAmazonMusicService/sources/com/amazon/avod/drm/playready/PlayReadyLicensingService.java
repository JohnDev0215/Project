package com.amazon.avod.drm.playready;

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
public interface PlayReadyLicensingService extends ResetObserver {
    @Nonnull
    PlayReadyLicenseResponse getLicenseResponse(@Nonnull DrmFramework drmFramework, @Nonnull PlayReadyContentIdentifier playReadyContentIdentifier, @Nonnull ConsumptionType consumptionType, @Nullable String str, @Nonnull ContentType contentType, boolean z, boolean z2, @Nonnull RendererSchemeType rendererSchemeType, boolean z3, @Nullable String str2, boolean z4, @Nonnull LicenseAcquisitionEventReporter licenseAcquisitionEventReporter, @Nonnull Map<String, String> map, @Nullable PlaybackEnvelope playbackEnvelope) throws DrmLicensingException;

    void resetState();
}
