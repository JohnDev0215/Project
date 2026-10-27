package com.amazon.podcast.categoryFollow;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
@Dao
public interface CategoryFollowsDao {
    @Query("SELECT * FROM category_follows WHERE id = :id")
    CategoryFollow get(final String id);

    @Query("SELECT * FROM category_follows WHERE followed = :status ORDER BY updatedTime DESC")
    LiveData<List<CategoryFollow>> getAll(final boolean status);

    @Query("SELECT * FROM category_follows WHERE id = :id")
    LiveData<CategoryFollow> getLiveData(final String id);

    @Insert(onConflict = 1)
    void insert(CategoryFollow categoryFollow);
}
