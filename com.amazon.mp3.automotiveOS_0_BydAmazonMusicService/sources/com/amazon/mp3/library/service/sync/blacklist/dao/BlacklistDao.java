package com.amazon.mp3.library.service.sync.blacklist.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import com.amazon.mp3.library.service.sync.blacklist.entity.LocalUri;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
@Dao
public interface BlacklistDao {
    @Query("SELECT * FROM local_uris")
    List<LocalUri> getAllLocalUris();

    @Insert(onConflict = 1)
    void insert(LocalUri localUri);
}
