package com.amazon.avod.media.events.dao;

import java.util.List;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public interface MediaEventDAO {
    @Nonnull
    List<MediaEventRecord> backfillAppInstanceId(@Nonnull String str);

    int clear();

    long fastGetDBSize(long j, boolean z);

    long getLatestId();

    @Nonnull
    List<MediaEventRecord> getNonNullAppInstanceId();

    @Nonnull
    List<MediaEventRecord> getOldestEvents(long j);

    @Nonnull
    MediaEventRecord insert(@Nonnull MediaEventRecord mediaEventRecord);

    int remove(@Nonnull String str, @Nonnull List<Long> list);
}
