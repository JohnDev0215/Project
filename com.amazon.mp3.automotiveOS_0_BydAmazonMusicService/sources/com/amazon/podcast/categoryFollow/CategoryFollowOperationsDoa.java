package com.amazon.podcast.categoryFollow;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
@Dao
public interface CategoryFollowOperationsDoa {
    @Query("DELETE FROM category_follow_operations WHERE operationId IN (:ids)")
    void deleteIds(final List<String> ids);

    @Query("SELECT * FROM category_follow_operations WHERE isProcessed == 0")
    List<CategoryFollowOperations> getUnProcessed();

    @Insert(onConflict = 1)
    void insert(CategoryFollowOperations operation);

    @Query("UPDATE category_follow_operations SET isProcessed = 1 WHERE operationId IN (:ids)")
    void updateToProcessing(final List<String> ids);
}
