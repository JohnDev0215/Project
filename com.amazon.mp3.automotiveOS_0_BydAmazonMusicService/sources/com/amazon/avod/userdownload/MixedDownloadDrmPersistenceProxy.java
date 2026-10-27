package com.amazon.avod.userdownload;

import com.amazon.avod.drm.db.DrmPersistence;
import com.amazon.avod.drm.db.DrmPersistenceInfo;
import com.amazon.avod.drm.db.DrmRecord;
import com.amazon.avod.userdownload.internal.PlaybackDownloadManager;
import com.google.common.base.Preconditions;
import java.util.HashSet;
import java.util.Set;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class MixedDownloadDrmPersistenceProxy implements DrmPersistence {
    private final boolean mEnablePlayerSdkDownloads;
    private final DrmPersistence mLegacyDrmPersistence;
    private final PlaybackDownloadManager mPlaybackDrmPersistence;

    public MixedDownloadDrmPersistenceProxy(@Nonnull DrmPersistence drmPersistence, @Nonnull PlaybackDownloadManager playbackDownloadManager) {
        this(drmPersistence, playbackDownloadManager, PlayerSdkDownloadConfig.getInstance().enablePlayerSdkDownloads());
    }

    public MixedDownloadDrmPersistenceProxy(@Nonnull DrmPersistence drmPersistence, @Nonnull PlaybackDownloadManager playbackDownloadManager, @Nonnull boolean z) {
        this.mLegacyDrmPersistence = (DrmPersistence) Preconditions.checkNotNull(drmPersistence, "legacyDrmPersistence");
        this.mPlaybackDrmPersistence = (PlaybackDownloadManager) Preconditions.checkNotNull(playbackDownloadManager, "playbackDownloadManager");
        this.mEnablePlayerSdkDownloads = z;
    }

    @Override // com.amazon.avod.drm.db.DrmPersistence
    public void upsertToDrmPersistence(@Nonnull String str, @Nonnull DrmRecord drmRecord) {
        this.mLegacyDrmPersistence.upsertToDrmPersistence(str, drmRecord);
        if (this.mEnablePlayerSdkDownloads) {
            this.mPlaybackDrmPersistence.upsertToDrmPersistence(str, drmRecord);
        }
    }

    @Override // com.amazon.avod.drm.db.DrmPersistence
    @Nonnull
    public Set<DrmPersistenceInfo> getLicenseRecordsFromDrmPersistence(@Nonnull String str) {
        if (!this.mEnablePlayerSdkDownloads) {
            return this.mLegacyDrmPersistence.getLicenseRecordsFromDrmPersistence(str);
        }
        Set<DrmPersistenceInfo> licenseRecordsFromDrmPersistence = this.mLegacyDrmPersistence.getLicenseRecordsFromDrmPersistence(str);
        return !licenseRecordsFromDrmPersistence.isEmpty() ? licenseRecordsFromDrmPersistence : this.mPlaybackDrmPersistence.getLicenseRecordsFromDrmPersistence(str);
    }

    @Override // com.amazon.avod.drm.db.DrmPersistence
    @Nonnull
    public Set<DrmPersistenceInfo> getAllRecords() {
        if (!this.mEnablePlayerSdkDownloads) {
            return this.mLegacyDrmPersistence.getAllRecords();
        }
        HashSet hashSet = new HashSet();
        hashSet.addAll(this.mLegacyDrmPersistence.getAllRecords());
        hashSet.addAll(this.mPlaybackDrmPersistence.getAllRecords());
        return hashSet;
    }
}
