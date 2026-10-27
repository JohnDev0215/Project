package com.amazon.avod.media.contentcache.internal;

import com.amazon.avod.content.ContentSessionType;
import com.amazon.avod.download.Downloadable;
import com.amazon.avod.media.playback.VideoSpecification;
import java.util.Date;
import java.util.List;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public interface CachedContentPersistence {

    public enum PriorityOrder {
        MAX,
        MIN
    }

    boolean deleteRecord(WritableCacheRecord writableCacheRecord);

    @Nonnull
    List<WritableCacheRecord> getAllRecords();

    @Nonnull
    List<WritableCacheRecord> getAllRecordsOfTypeDownloadedBefore(@Nonnull Date date, @Nonnull ContentSessionType contentSessionType);

    int getNumRecordsByState(Downloadable.DownloadableState downloadableState);

    @Nullable
    WritableCacheRecord getRecordByState(Downloadable.DownloadableState downloadableState, PriorityOrder priorityOrder);

    @Nullable
    WritableCacheRecord getRecordForSpec(VideoSpecification videoSpecification);

    @Nonnull
    List<WritableCacheRecord> getRecordsIgnoringStartTime(VideoSpecification videoSpecification);

    long getTotalCacheFilesizeKb();

    int getTotalRecords();

    boolean putRecord(WritableCacheRecord writableCacheRecord);

    boolean putRecordBatch(List<WritableCacheRecord> list);
}
