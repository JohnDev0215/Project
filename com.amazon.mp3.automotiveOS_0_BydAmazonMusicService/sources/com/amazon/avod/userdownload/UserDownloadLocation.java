package com.amazon.avod.userdownload;

import com.amazon.avod.acos.StorageHelper;
import com.amazon.avod.metrics.pmet.MetricParameter;
import com.google.common.base.Preconditions;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public enum UserDownloadLocation implements MetricParameter {
    INTERNAL_STORAGE(StorageHelper.StorageLocation.INTERNAL_STORAGE, "INTERNAL_STORAGE", "INTERNAL"),
    SD_CARD(StorageHelper.StorageLocation.SD_CARD, "SD_CARD", "SD");

    private final String mPersistenceName;
    private final String mShortName;
    private final StorageHelper.StorageLocation mStorageLocation;

    UserDownloadLocation(@Nonnull StorageHelper.StorageLocation storageLocation, @Nonnull String str, @Nonnull String str2) {
        this.mStorageLocation = (StorageHelper.StorageLocation) Preconditions.checkNotNull(storageLocation, "location");
        this.mPersistenceName = (String) Preconditions.checkNotNull(str, "persistenceName");
        this.mShortName = (String) Preconditions.checkNotNull(str2, "shortName");
    }

    @Nonnull
    public StorageHelper.StorageLocation getStorageLocation() {
        return this.mStorageLocation;
    }

    @Nonnull
    public String getPersistenceName() {
        return this.mPersistenceName;
    }

    @Nonnull
    public String getShortName() {
        return this.mShortName;
    }

    @Nonnull
    public static UserDownloadLocation fromPersistenceName(@Nonnull String str) {
        Preconditions.checkNotNull(str, "persistenceName");
        for (UserDownloadLocation userDownloadLocation : values()) {
            if (userDownloadLocation.getPersistenceName().equals(str)) {
                return userDownloadLocation;
            }
        }
        throw new IllegalArgumentException("Unsupported download location: " + str);
    }

    @Override // com.amazon.avod.metrics.pmet.MetricParameter
    @Nonnull
    public String toReportableString() {
        return getPersistenceName();
    }
}
