package com.amazon.avod.userdownload.internal;

import com.amazon.avod.content.urlvending.ContentUrl;
import com.amazon.avod.core.AsinResolver;
import com.amazon.avod.download.DownloadLicenseManager;
import com.amazon.avod.drm.db.DrmPersistenceInfo;
import com.amazon.avod.drm.db.DrmRecord;
import com.amazon.avod.drm.db.DrmStoredRights;
import com.amazon.avod.media.download.error.DownloadExecutionErrorCode;
import com.amazon.avod.media.download.internal.PlaybackDownloadQueueConfig;
import com.amazon.avod.userdownload.PlaybackDownload;
import com.amazon.avod.userdownload.PlaybackDownloadEventReporter;
import com.amazon.avod.userdownload.filter.PlaybackDownloadFilter;
import com.amazon.avod.userdownload.reporting.LicenseOperationCause;
import com.amazon.avod.userdownload.reporting.RightsOperationCause;
import com.amazon.avod.userdownload.rights.RightsManager;
import com.amazon.avod.util.DLog;
import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import com.google.common.base.Supplier;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Iterables;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public class PlaybackDownloadLicenseHelper {
    private final PlaybackDownloadQueueConfig mConfig;
    private final PlaybackDownloadEventReporter mDownloadEventReporter;
    private final DownloadLicenseManager mDownloadLicenseManager;
    private final RightsManager mRightsManager;

    public static class Factory {
        private final Supplier<PlaybackDownloadSharedComponents> mDownloadSharedComponentsSupplier;

        public Factory(@Nonnull Supplier<PlaybackDownloadSharedComponents> supplier) {
            this.mDownloadSharedComponentsSupplier = (Supplier) Preconditions.checkNotNull(supplier, "sharedComponentsSupplier");
        }

        @Nonnull
        public PlaybackDownloadLicenseHelper createHelper(@Nonnull DownloadLicenseManager downloadLicenseManager, @Nonnull RightsManager rightsManager) {
            return new PlaybackDownloadLicenseHelper(this.mDownloadSharedComponentsSupplier.get().getQueueConfig(), downloadLicenseManager, this.mDownloadSharedComponentsSupplier.get().getPlaybackDownloadEventReporter(), rightsManager);
        }
    }

    PlaybackDownloadLicenseHelper(@Nonnull PlaybackDownloadQueueConfig playbackDownloadQueueConfig, @Nonnull DownloadLicenseManager downloadLicenseManager, @Nonnull PlaybackDownloadEventReporter playbackDownloadEventReporter, @Nonnull RightsManager rightsManager) {
        this.mConfig = (PlaybackDownloadQueueConfig) Preconditions.checkNotNull(playbackDownloadQueueConfig, "config");
        this.mDownloadEventReporter = (PlaybackDownloadEventReporter) Preconditions.checkNotNull(playbackDownloadEventReporter, "playbackDownloadEventReporter");
        this.mDownloadLicenseManager = (DownloadLicenseManager) Preconditions.checkNotNull(downloadLicenseManager, "downloadLicenseManager");
        this.mRightsManager = (RightsManager) Preconditions.checkNotNull(rightsManager, "rightsManager");
    }

    @Nullable
    public DrmStoredRights getLicenseState(String str) {
        return this.mDownloadLicenseManager.getLicenseState(str);
    }

    @Nullable
    public static DownloadExecutionErrorCode checkRefreshLicensePrerequisites(@Nonnull PlaybackDownload playbackDownload) {
        if (!playbackDownload.getDrmAssetId().isPresent()) {
            DLog.devf("DWNLD_SDK Can't refresh license for %s - no DRM asset ID", playbackDownload);
            return DownloadExecutionErrorCode.MISSING_ASSET_ID;
        }
        if (!playbackDownload.getDrmRecord().isPresent()) {
            DLog.devf("DWNLD_SDK Can't refresh license for %s - no DRM record", playbackDownload);
            return DownloadExecutionErrorCode.MISSING_DRM_RECORD;
        }
        if (playbackDownload.getUrl().isPresent()) {
            return null;
        }
        DLog.devf("DWNLD_SDK Can't refresh license for %s - no manifest URL; can't determine isDash", playbackDownload);
        return DownloadExecutionErrorCode.MISSING_DASH_METADATA;
    }

    public void releaseRights(@Nonnull PlaybackDownload playbackDownload, @Nonnull ImmutableSet<PlaybackDownload> immutableSet, @Nonnull LicenseOperationCause licenseOperationCause, @Nonnull RightsOperationCause rightsOperationCause) {
        Preconditions.checkNotNull(playbackDownload, "download");
        Preconditions.checkNotNull(immutableSet, "allDownloads");
        this.mDownloadEventReporter.reportOperationWithCause(playbackDownload, rightsOperationCause);
        DrmRecord drmRecordOrNull = playbackDownload.getDrmRecord().orNull();
        if (drmRecordOrNull == null) {
            this.mDownloadEventReporter.reportOperationWithCause(playbackDownload, LicenseOperationCause.LICENSE_REMOVED_MISSING_DRM_RECORD);
            return;
        }
        String strOrNull = playbackDownload.getDrmAssetId().orNull();
        Preconditions.checkState(strOrNull != null, "Inconsistent state - PlaybackDownload had drmRecord, but w/o drmAssetId");
        if (strOrNull != null) {
            if (convertToDrmPersistenceInfo(immutableSet, strOrNull).size() > 1) {
                return;
            }
            this.mDownloadLicenseManager.removeLicense(strOrNull, drmRecordOrNull.getOfflineKeyId(), drmRecordOrNull.getDrmScheme(), drmRecordOrNull.getRendererSchemeType());
            this.mDownloadEventReporter.reportOperationWithCause(playbackDownload, licenseOperationCause);
        }
    }

    public void consumeRightIfNeeded(@Nonnull PlaybackDownload playbackDownload) {
        this.mRightsManager.consumeDownloadRight(getTitleId(playbackDownload), playbackDownload.getPlaybackSessionContext());
    }

    @Nonnull
    public ImmutableSet<DrmPersistenceInfo> convertToDrmPersistenceInfo(@Nonnull Iterable<PlaybackDownload> iterable) {
        Preconditions.checkNotNull(iterable, "allDownloads");
        ImmutableSet.Builder builder = ImmutableSet.builder();
        for (PlaybackDownload playbackDownload : iterable) {
            if (playbackDownload.getDrmAssetId().isPresent()) {
                builder.add(convertToDrmPersistenceInfo(playbackDownload));
            }
        }
        return builder.build();
    }

    @Nonnull
    public ImmutableSet<DrmPersistenceInfo> convertToDrmPersistenceInfo(@Nonnull Iterable<PlaybackDownload> iterable, @Nonnull String str) {
        Preconditions.checkNotNull(iterable, "allDownloads");
        return convertToDrmPersistenceInfo(Iterables.filter(iterable, PlaybackDownloadFilter.newDrmAssetIdFilter(str)));
    }

    @SuppressFBWarnings({"NP_NULL_ON_SOME_PATH_FROM_RETURN_VALUE"})
    @Nonnull
    private DrmPersistenceInfo convertToDrmPersistenceInfo(@Nonnull PlaybackDownload playbackDownload) {
        String strOrNull = playbackDownload.getDrmAssetId().orNull();
        Preconditions.checkArgument(strOrNull != null, "Can't convert download without a drmAssetId to a DrmPersistenceInfo object");
        return new DrmPersistenceInfo(playbackDownload.getAsin(), isUrlDash(playbackDownload.getUrl()), strOrNull, playbackDownload.getDrmRecord(), playbackDownload.getPlayerSDKPlaybackToken(), playbackDownload.getPlaybackSessionContext());
    }

    private static Optional<Boolean> isUrlDash(Optional<String> optional) {
        return Optional.fromNullable(optional.isPresent() ? Boolean.valueOf(ContentUrl.isDashUrl(optional.get())) : null);
    }

    private static String getTitleId(@Nonnull PlaybackDownload playbackDownload) {
        if (!AsinResolver.isAsin(playbackDownload.getAsin())) {
            return playbackDownload.getAsin();
        }
        return playbackDownload.getAsin();
    }
}
