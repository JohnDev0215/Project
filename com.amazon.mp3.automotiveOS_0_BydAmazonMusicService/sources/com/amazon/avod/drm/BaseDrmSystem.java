package com.amazon.avod.drm;

import com.amazon.avod.drm.db.DrmPersistence;
import com.amazon.avod.drm.db.DrmStoredRights;
import com.amazon.avod.drm.db.DrmStoredRightsWithId;
import com.amazon.avod.drm.event.LicenseAcquisitionEventReporter;
import com.amazon.avod.media.framework.error.DrmLicensingException;
import com.amazon.avod.media.framework.error.MediaException;
import com.amazon.avod.media.playback.ContentType;
import com.amazon.avod.media.playback.support.ConsumptionType;
import com.amazon.avod.media.playback.support.ResetObserver;
import com.amazon.avod.playback.drm.DrmScheme;
import com.amazon.avod.playback.renderer.RendererSchemeType;
import com.amazon.video.sdk.player.PlaybackEnvelope;
import java.util.Map;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public interface BaseDrmSystem {
    @Nonnull
    DrmStoredRightsWithId acquireLicense(@Nonnull String str, @Nonnull String str2, @Nonnull Map<String, String> map, @Nonnull DrmScheme drmScheme, @Nonnull RendererSchemeType rendererSchemeType, @Nonnull ConsumptionType consumptionType, @Nonnull ContentType contentType, @Nonnull LicenseAcquisitionEventReporter licenseAcquisitionEventReporter, boolean z, @Nullable String str3, boolean z2, @Nullable String str4, @Nullable String str5, @Nullable PlaybackEnvelope playbackEnvelope) throws DrmLicensingException;

    void addResetObserver(ResetObserver resetObserver);

    void deleteAllLicenses();

    void deleteLicense(@Nonnull String str, @Nullable String str2, @Nonnull DrmScheme drmScheme, @Nonnull RendererSchemeType rendererSchemeType);

    @Nullable
    DrmStoredRights getLicenseStatus(@Nonnull String str);

    @Nonnull
    MediaException handlePossibleAutoResetException(@Nonnull MediaException mediaException);

    void initialize(@Nonnull DrmPersistence drmPersistence);

    void initializePlayback(@Nonnull DrmScheme drmScheme, @Nonnull RendererSchemeType rendererSchemeType) throws DrmLicensingException;

    boolean isLicensingEnabled(@Nullable DrmScheme drmScheme, @Nullable RendererSchemeType rendererSchemeType);

    boolean isMultiSessionDrmSupported();

    boolean isPlaybackActive();

    boolean shutdownDrmSession(@Nonnull DrmScheme drmScheme, @Nonnull RendererSchemeType rendererSchemeType);

    void shutdownPlayback(@Nonnull String str, @Nonnull String str2, @Nonnull ConsumptionType consumptionType, @Nonnull DrmScheme drmScheme, @Nonnull RendererSchemeType rendererSchemeType);
}
