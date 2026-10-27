package com.amazon.podcast.downloads;

import androidx.room.Dao;
import androidx.room.Query;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
@Dao
public interface DownloadOperationsDao {
    @Query("DELETE FROM download_operations WHERE operationId IN (:ids)")
    void deleteIds(final List<String> ids);

    @Query("SELECT * FROM download_operations WHERE isProcessed == 0")
    List<DownloadOperations> getUnProcessed();

    @Query("UPDATE download_operations SET isProcessed = 1 WHERE operationId IN (:ids)")
    void updateToProcessing(final List<String> ids);
}
