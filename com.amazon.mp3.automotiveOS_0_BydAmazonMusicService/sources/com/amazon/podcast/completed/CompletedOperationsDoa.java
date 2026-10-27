package com.amazon.podcast.completed;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
@Dao
public interface CompletedOperationsDoa {
    @Query("DELETE FROM completed_operations WHERE operationId IN (:ids)")
    void deleteIds(final List<String> ids);

    @Query("SELECT * FROM completed_operations WHERE isProcessed == 0")
    List<CompletedOperations> getUnProcessed();

    @Insert(onConflict = 1)
    void insert(CompletedOperations completed);

    @Query("UPDATE completed_operations SET isProcessed = 1 WHERE operationId IN (:ids)")
    void updateToProcessing(final List<String> ids);
}
