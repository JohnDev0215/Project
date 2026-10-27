package com.amazon.podcast.save;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
@Dao
public interface SaveOperationsDoa {
    @Query("DELETE FROM save_operations WHERE operationId IN (:ids)")
    void deleteIds(final List<String> ids);

    @Query("SELECT * FROM save_operations WHERE isProcessed == 0")
    List<SaveOperations> getUnProcessed();

    @Insert(onConflict = 1)
    void insert(SaveOperations save);

    @Query("UPDATE save_operations SET isProcessed = 1 WHERE operationId IN (:ids)")
    void updateToProcessing(final List<String> ids);
}
