package com.amazon.avod.download;

import com.amazon.avod.drm.db.DrmStoredRights;
import com.amazon.avod.playback.drm.DrmScheme;
import com.amazon.avod.playback.renderer.RendererSchemeType;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public interface DownloadLicenseManager {
    @Nullable
    DrmStoredRights getLicenseState(@Nonnull String str);

    void removeLicense(@Nonnull String str, @Nullable String str2, @Nonnull DrmScheme drmScheme, @Nonnull RendererSchemeType rendererSchemeType);
}
