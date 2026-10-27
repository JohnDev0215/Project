package com.amazon.podcast.bookmark;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
@Dao
public interface BookmarkOperationsDoa {
    @Query("DELETE FROM bookmark_operations WHERE operationId IN (:ids)")
    void deleteIds(final List<String> ids);

    @Query("SELECT * FROM bookmark_operations WHERE isProcessed == 0")
    List<BookmarkOperations> getUnProcessed();

    @Insert(onConflict = 1)
    void insert(BookmarkOperations bookmark);

    @Query("UPDATE bookmark_operations SET isProcessed = 1 WHERE operationId IN (:ids)")
    void updateToProcessing(final List<String> ids);
}
