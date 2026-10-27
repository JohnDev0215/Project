package com.amazon.podcast.biteBookmark;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import java.util.List;
import java.util.Set;

/* JADX INFO: loaded from: classes5.dex */
@Dao
public interface BiteBookmarksDao {
    @Query("SELECT * FROM bite_bookmarks WHERE id = :id")
    BiteBookmark get(final String id);

    @Query("SELECT * FROM bite_bookmarks WHERE progressMilliseconds > :lowerBoundProgressMilliseconds AND (((progressMilliseconds) * 100) / totalDurationMilliseconds) < :upperBoundProgressPercentage ORDER BY updatedTime DESC")
    LiveData<List<BiteBookmark>> getAll(final Long lowerBoundProgressMilliseconds, final Integer upperBoundProgressPercentage);

    @Query("SELECT * FROM bite_bookmarks WHERE id IN (:ids)")
    LiveData<List<BiteBookmark>> getAll(final Set<String> ids);

    @Query("SELECT * FROM bite_bookmarks WHERE id IN (:ids)")
    List<BiteBookmark> getAllList(final Set<String> ids);

    @Query("SELECT * FROM bite_bookmarks WHERE id = :id AND progressMilliseconds > :lowerBoundProgressMilliseconds AND (((progressMilliseconds) * 100) / totalDurationMilliseconds) < :upperBoundProgressPercentage")
    BiteBookmark getData(final String id, final Long lowerBoundProgressMilliseconds, final Integer upperBoundProgressPercentage);

    @Query("SELECT * FROM bite_bookmarks WHERE id = :id")
    LiveData<BiteBookmark> getLiveData(final String id);

    @Insert(onConflict = 1)
    void insert(BiteBookmark biteBookmark);
}
