package com.amazon.podcast.save;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
@Dao
public interface SavesDao {
    @Query("SELECT * FROM saves WHERE id = :id")
    Save get(final String id);

    @Query("SELECT * FROM saves WHERE saved = :status ORDER BY updatedTime DESC")
    List<Save> getAll(final boolean status);

    @Query("SELECT * FROM saves WHERE saved = :status ORDER BY updatedTime DESC")
    LiveData<List<Save>> getAllLiveData(final boolean status);

    @Query("SELECT * FROM saves WHERE id = :id")
    LiveData<Save> getLiveData(final String id);

    @Insert(onConflict = 1)
    void insert(Save save);
}
