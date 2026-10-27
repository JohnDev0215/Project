package com.amazon.avod.userdownload;

import com.amazon.avod.download.DownloadLicenseManager;
import com.amazon.avod.drm.BaseDrmSystem;
import com.amazon.avod.drm.db.DrmStoredRights;
import com.amazon.avod.playback.drm.DrmScheme;
import com.amazon.avod.playback.renderer.RendererSchemeType;
import com.amazon.avod.threading.ThreadUtils;
import com.amazon.avod.util.DLog;
import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public class PlaybackDownloadPlayreadyManager implements DownloadLicenseManager {
    private final BaseDrmSystem mDrmSystem;

    public PlaybackDownloadPlayreadyManager(BaseDrmSystem baseDrmSystem) {
        this.mDrmSystem = (BaseDrmSystem) Preconditions.checkNotNull(baseDrmSystem, "drmSystem");
    }

    @Override // com.amazon.avod.download.DownloadLicenseManager
    public void removeLicense(@Nonnull String str, @Nullable String str2, @Nonnull DrmScheme drmScheme, @Nonnull RendererSchemeType rendererSchemeType) {
        Preconditions.checkNotNull(str, "drmAssetId");
        Preconditions.checkNotNull(drmScheme, "drmScheme");
        Preconditions.checkNotNull(rendererSchemeType, "rendererSchemeType");
        this.mDrmSystem.deleteLicense(str, str2, drmScheme, rendererSchemeType);
    }

    @Override // com.amazon.avod.download.DownloadLicenseManager
    @Nullable
    public DrmStoredRights getLicenseState(@Nonnull String str) {
        DLog.enterf("DWNLD_SDK Querying drmAssetId %s", str);
        Preconditions.checkArgument(!Strings.isNullOrEmpty(str), "Bad drmAssetId argument");
        ThreadUtils.throwIfCalledFromUI();
        DrmStoredRights licenseStatus = this.mDrmSystem.getLicenseStatus(str);
        DLog.logf("DWNLD_SDK license status for drmAssetId %s : %s", str, licenseStatus);
        return licenseStatus;
    }
}
