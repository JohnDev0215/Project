package com.amazon.podcast.biteBookmark;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
@Dao
public interface BiteBookmarkOperationsDoa {
    @Query("DELETE FROM bite_bookmark_operations WHERE operationId IN (:ids)")
    void deleteIds(final List<String> ids);

    @Query("SELECT * FROM bite_bookmark_operations WHERE isProcessed == 0")
    List<BiteBookmarkOperations> getUnProcessed();

    @Insert(onConflict = 1)
    void insert(BiteBookmarkOperations bookmark);

    @Query("UPDATE bite_bookmark_operations SET isProcessed = 1 WHERE operationId IN (:ids)")
    void updateToProcessing(final List<String> ids);
}
