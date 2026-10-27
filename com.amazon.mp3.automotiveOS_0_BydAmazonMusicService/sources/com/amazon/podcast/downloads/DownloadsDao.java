package com.amazon.podcast.downloads;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
@Dao
public interface DownloadsDao {
    @Query("DELETE FROM downloads WHERE id = :id")
    void delete(final String id);

    @Query("DELETE FROM downloads WHERE state = :state")
    void deleteAllContainingState(final String state);

    @Query("SELECT * FROM downloads WHERE id = :id")
    Download get(final String id);

    @Query("SELECT * FROM downloads ORDER BY updatedTime DESC")
    List<Download> getAll();

    @Query("SELECT * FROM downloads WHERE podcastId = :podcastId ORDER BY updatedTime DESC")
    List<Download> getAll(final String podcastId);

    @Query("SELECT * FROM downloads WHERE podcastId = :podcastId AND seasonNumber = :seasonNumber ORDER BY updatedTime DESC")
    List<Download> getAll(final String podcastId, final String seasonNumber);

    @Query("SELECT * FROM downloads ORDER BY updatedTime DESC")
    LiveData<List<Download>> getAllLiveData();

    @Query("SELECT * FROM downloads WHERE podcastId = :podcastId ORDER BY updatedTime DESC")
    LiveData<List<Download>> getAllLiveData(final String podcastId);

    @Query("SELECT * FROM downloads WHERE podcastId = :podcastId AND seasonNumber = :seasonNumber ORDER BY updatedTime DESC")
    LiveData<List<Download>> getAllLiveData(final String podcastId, final String seasonNumber);

    @Query("SELECT * FROM downloads WHERE id = :id")
    LiveData<Download> getLiveData(final String id);

    @Query("SELECT * FROM downloads WHERE updatedTime < :updateTime ORDER BY updatedTime DESC LIMIT 1")
    Download getNext(final long updateTime);

    @Query("SELECT * FROM downloads WHERE updatedTime > :updateTime ORDER BY updatedTime ASC LIMIT 1")
    Download getPrevious(final long updateTime);

    @Insert(onConflict = 1)
    void insert(Download download);
}
