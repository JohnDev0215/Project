package com.amazon.avod.media.events.dao;

import java.util.List;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public interface MediaReportDAO {
    void clear();

    void clearLegacyDatabase();

    @Nonnull
    List<Long> getAllIds();

    @Nullable
    MediaReportRecord getById(long j);

    @Nonnull
    MediaReportRecord insert(@Nonnull MediaReportRecord mediaReportRecord);

    boolean remove(long j);
}
