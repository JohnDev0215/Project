package com.amazon.avod.drm;

import com.amazon.avod.connectivity.NetworkConnectionManager;
import com.amazon.avod.content.ContentSession;
import com.amazon.avod.drm.event.DefaultLicenseAcquisitionEventReporter;
import com.amazon.avod.identity.IdentityShim;
import com.amazon.avod.media.error.DrmErrorCode;
import com.amazon.avod.media.framework.MediaSystemSharedDependencies;
import com.amazon.avod.media.framework.error.MediaException;
import com.amazon.avod.media.playback.ContentType;
import com.amazon.avod.media.playback.VideoSpecification;
import com.amazon.avod.media.playback.support.ConsumptionType;
import com.amazon.avod.playback.drm.DrmScheme;
import com.amazon.avod.playback.renderer.RendererSchemeType;
import com.amazon.avod.userdownload.PVDownloadManagerPlayerShim;
import com.amazon.avod.userdownload.PVDownloadPlayerShim;
import com.amazon.avod.userdownload.PVDownloadsPlayerShim;
import com.amazon.avod.util.DLog;
import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableMap;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.NotThreadSafe;

/* JADX INFO: loaded from: classes2.dex */
@NotThreadSafe
class DrmDecryptionContext implements DecryptionContext {
    private final BaseDrmSystem mBaseDrmSystem;
    private final ConsumptionType mConsumptionType;
    private final ContentSession mContentSession;
    private final DrmScheme mDrmScheme;
    private final String mEncryptionHeader;
    private final IdentityShim mIdentity;
    private final boolean mIsDash;
    private boolean mIsInitialized;
    private final NetworkConnectionManager mNetworkConnectionManager;
    private final String mOfflineKeyId;
    private final RendererSchemeType mRendererSchemeType;
    private final PVDownloadManagerPlayerShim mUserDownloadManager;
    private final VideoSpecification mVideoSpecification;

    public DrmDecryptionContext(@Nonnull BaseDrmSystem baseDrmSystem, @Nonnull ConsumptionType consumptionType, @Nonnull VideoSpecification videoSpecification, @Nonnull String str, @Nullable String str2, boolean z, @Nonnull DrmScheme drmScheme, @Nonnull RendererSchemeType rendererSchemeType, @Nullable ContentSession contentSession) {
        this(baseDrmSystem, consumptionType, videoSpecification, NetworkConnectionManager.getInstance(), str, MediaSystemSharedDependencies.getInstance().getIdentityShim(), PVDownloadsPlayerShim.getInstance().getPlayerDownloadManagerProxy(), str2, z, drmScheme, rendererSchemeType, contentSession);
    }

    protected DrmDecryptionContext(@Nonnull BaseDrmSystem baseDrmSystem, @Nonnull ConsumptionType consumptionType, @Nonnull VideoSpecification videoSpecification, @Nonnull NetworkConnectionManager networkConnectionManager, @Nonnull String str, @Nonnull IdentityShim identityShim, @Nonnull PVDownloadManagerPlayerShim pVDownloadManagerPlayerShim, @Nullable String str2, boolean z, @Nonnull DrmScheme drmScheme, @Nonnull RendererSchemeType rendererSchemeType, @Nullable ContentSession contentSession) {
        this.mIsInitialized = false;
        this.mBaseDrmSystem = (BaseDrmSystem) Preconditions.checkNotNull(baseDrmSystem, "baseDrmSystem");
        this.mConsumptionType = (ConsumptionType) Preconditions.checkNotNull(consumptionType, "consumptionType");
        this.mVideoSpecification = (VideoSpecification) Preconditions.checkNotNull(videoSpecification, "videoSpecification");
        this.mNetworkConnectionManager = (NetworkConnectionManager) Preconditions.checkNotNull(networkConnectionManager, "networkConnectionManager");
        this.mEncryptionHeader = (String) Preconditions.checkNotNull(str, "encryptionHeader");
        this.mIdentity = (IdentityShim) Preconditions.checkNotNull(identityShim, "identity");
        this.mUserDownloadManager = (PVDownloadManagerPlayerShim) Preconditions.checkNotNull(pVDownloadManagerPlayerShim, "userDownloadManager");
        this.mOfflineKeyId = str2;
        this.mIsDash = z;
        this.mDrmScheme = (DrmScheme) Preconditions.checkNotNull(drmScheme, "drmScheme");
        this.mRendererSchemeType = (RendererSchemeType) Preconditions.checkNotNull(rendererSchemeType, "rendererSchemeType");
        this.mContentSession = contentSession;
    }

    @Override // com.amazon.avod.drm.DecryptionContext
    public void initialize() throws MediaException {
        this.mBaseDrmSystem.initializePlayback(this.mDrmScheme, this.mRendererSchemeType);
        if (this.mConsumptionType == ConsumptionType.Download) {
            Optional<PVDownloadPlayerShim> offlineDownloadIfPresent = this.mUserDownloadManager.getOfflineDownloadIfPresent(this.mVideoSpecification, this.mIdentity.getPlaybackSessionContext());
            if (offlineDownloadIfPresent.isPresent()) {
                this.mUserDownloadManager.consumeRightIfNeeded(offlineDownloadIfPresent.get());
            }
        }
        this.mIsInitialized = true;
    }

    @Override // com.amazon.avod.drm.DecryptionContext
    public void shutdown() {
        this.mBaseDrmSystem.shutdownPlayback(this.mVideoSpecification.getTitleId(), this.mEncryptionHeader, this.mConsumptionType, this.mDrmScheme, this.mRendererSchemeType);
        this.mIsInitialized = false;
    }

    @Override // com.amazon.avod.drm.DecryptionContext
    public void repairDecryptionInitializationFailure(MediaException mediaException) throws MediaException {
        if (mediaException.getErrorCode().getExternalCode() == DrmErrorCode.PLAYREADY_LICENSENOTFOUND && this.mNetworkConnectionManager.hasDataConnection()) {
            DLog.warnf("Expected license for %s was missing! Reacquiring.", this.mVideoSpecification.getTitleId());
            reacquireLicense();
            return;
        }
        throw mediaException;
    }

    @Override // com.amazon.avod.drm.DecryptionContext
    @Nonnull
    public DrmScheme getDrmScheme() {
        return this.mDrmScheme;
    }

    private void reacquireLicense() throws MediaException {
        boolean z = this.mIsInitialized;
        if (z) {
            shutdown();
        }
        BaseDrmSystem baseDrmSystem = this.mBaseDrmSystem;
        String titleId = this.mVideoSpecification.getTitleId();
        String str = this.mEncryptionHeader;
        ImmutableMap immutableMapOf = ImmutableMap.of();
        DrmScheme drmScheme = this.mDrmScheme;
        RendererSchemeType rendererSchemeType = this.mRendererSchemeType;
        ConsumptionType consumptionType = this.mConsumptionType;
        ContentType contentType = this.mVideoSpecification.getContentType();
        DefaultLicenseAcquisitionEventReporter defaultLicenseAcquisitionEventReporter = DefaultLicenseAcquisitionEventReporter.INSTANCE;
        boolean z2 = this.mIsDash;
        String str2 = this.mOfflineKeyId;
        ContentSession contentSession = this.mContentSession;
        baseDrmSystem.acquireLicense(titleId, str, immutableMapOf, drmScheme, rendererSchemeType, consumptionType, contentType, defaultLicenseAcquisitionEventReporter, z2, str2, true, contentSession != null ? contentSession.getConsumptionId() : null, this.mVideoSpecification.getPlaybackToken(), this.mVideoSpecification.getPlaybackEnvelope());
        if (z) {
            initialize();
        }
    }
}
