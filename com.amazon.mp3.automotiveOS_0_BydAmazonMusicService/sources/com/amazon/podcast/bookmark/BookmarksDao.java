package com.amazon.podcast.bookmark;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import java.util.List;
import java.util.Set;

/* JADX INFO: loaded from: classes5.dex */
@Dao
public interface BookmarksDao {
    @Query("SELECT * FROM bookmarks WHERE id = :id")
    Bookmark get(final String id);

    @Query("SELECT * FROM bookmarks WHERE progressMilliseconds > :lowerBoundProgressMilliseconds AND (((progressMilliseconds) * 100) / totalDurationMilliseconds) < :upperBoundProgressPercentage ORDER BY updatedTime DESC")
    LiveData<List<Bookmark>> getAll(final Long lowerBoundProgressMilliseconds, final Integer upperBoundProgressPercentage);

    @Query("SELECT * FROM bookmarks WHERE id IN (:ids)")
    LiveData<List<Bookmark>> getAll(final Set<String> ids);

    @Query("SELECT * FROM bookmarks WHERE id IN (:ids)")
    List<Bookmark> getAllList(final Set<String> ids);

    @Query("SELECT * FROM bookmarks WHERE progressMilliseconds > :lowerBoundProgressMilliseconds AND (((progressMilliseconds) * 100) / totalDurationMilliseconds) < :upperBoundProgressPercentage AND updatedTime >= :uptoNDaysInMilliseconds GROUP BY podcastId HAVING MAX(updatedTime) ORDER BY updatedTime DESC")
    LiveData<List<Bookmark>> getAllUniquePodcastEntries(final Long lowerBoundProgressMilliseconds, final Integer upperBoundProgressPercentage, final Long uptoNDaysInMilliseconds);

    @Query("SELECT * FROM bookmarks WHERE id = :id AND progressMilliseconds > :lowerBoundProgressMilliseconds AND (((progressMilliseconds) * 100) / totalDurationMilliseconds) < :upperBoundProgressPercentage")
    Bookmark getData(final String id, final Long lowerBoundProgressMilliseconds, final Integer upperBoundProgressPercentage);

    @Query("SELECT * FROM bookmarks WHERE id = :id")
    LiveData<Bookmark> getLiveData(final String id);

    @Insert(onConflict = 1)
    void insert(Bookmark bookmark);
}
