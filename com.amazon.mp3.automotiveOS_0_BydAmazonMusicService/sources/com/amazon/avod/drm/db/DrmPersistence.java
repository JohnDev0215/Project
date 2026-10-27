package com.amazon.avod.drm.db;

import java.util.Set;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public interface DrmPersistence {
    @Nonnull
    Set<DrmPersistenceInfo> getAllRecords();

    @Nonnull
    Set<DrmPersistenceInfo> getLicenseRecordsFromDrmPersistence(@Nonnull String str);

    void upsertToDrmPersistence(@Nonnull String str, @Nonnull DrmRecord drmRecord);
}
