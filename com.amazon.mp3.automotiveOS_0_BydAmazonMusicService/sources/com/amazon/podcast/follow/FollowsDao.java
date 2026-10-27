package com.amazon.podcast.follow;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
@Dao
public interface FollowsDao {
    @Query("SELECT * FROM follows WHERE id = :id")
    Follow get(final String id);

    @Query("SELECT * FROM follows WHERE followed = :status ORDER BY updatedTime DESC")
    LiveData<List<Follow>> getAll(final boolean status);

    @Query("SELECT * FROM follows ORDER BY updatedTime DESC")
    LiveData<List<Follow>> getAllEntries();

    @Query("SELECT * FROM follows WHERE id = :id")
    LiveData<Follow> getLiveData(final String id);

    @Insert(onConflict = 1)
    void insert(Follow follow);
}
