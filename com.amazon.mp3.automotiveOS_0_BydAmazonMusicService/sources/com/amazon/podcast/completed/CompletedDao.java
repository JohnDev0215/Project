package com.amazon.podcast.completed;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
@Dao
public interface CompletedDao {
    @Query("SELECT * FROM completions WHERE id = :id")
    Completed get(final String id);

    @Query("SELECT * FROM completions WHERE completed = :completed ORDER BY updatedTime DESC")
    List<Completed> getAll(final boolean completed);

    @Query("SELECT * FROM completions WHERE completed = :completed AND podcastId = :podcastId ORDER BY updatedTime DESC")
    List<Completed> getAll(final boolean completed, final String podcastId);

    @Query("SELECT * FROM completions WHERE completed = :completed AND podcastId = :podcastId AND seasonNumber = :seasonNumber ORDER BY updatedTime DESC")
    List<Completed> getAll(final boolean completed, final String podcastId, final String seasonNumber);

    @Query("SELECT * FROM completions WHERE completed = :completed ORDER BY updatedTime DESC")
    LiveData<List<Completed>> getAllLiveData(final boolean completed);

    @Query("SELECT * FROM completions WHERE completed = :completed AND podcastId = :podcastId ORDER BY updatedTime DESC")
    LiveData<List<Completed>> getAllLiveData(final boolean completed, final String podcastId);

    @Query("SELECT * FROM completions WHERE completed = :completed AND podcastId = :podcastId AND seasonNumber = :seasonNumber ORDER BY updatedTime DESC")
    LiveData<List<Completed>> getAllLiveData(final boolean completed, final String podcastId, final String seasonNumber);

    @Query("SELECT * FROM completions WHERE id = :id")
    LiveData<Completed> getLiveData(final String id);

    @Insert(onConflict = 1)
    void insert(Completed completed);
}
