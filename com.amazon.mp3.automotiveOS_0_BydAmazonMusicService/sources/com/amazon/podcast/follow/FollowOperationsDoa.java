package com.amazon.podcast.follow;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
@Dao
public interface FollowOperationsDoa {
    @Query("DELETE FROM follow_operations WHERE operationId IN (:ids)")
    void deleteIds(final List<String> ids);

    @Query("SELECT * FROM follow_operations WHERE isProcessed == 0")
    List<FollowOperations> getUnProcessed();

    @Insert(onConflict = 1)
    void insert(FollowOperations follow);

    @Query("UPDATE follow_operations SET isProcessed = 1 WHERE operationId IN (:ids)")
    void updateToProcessing(final List<String> ids);
}
