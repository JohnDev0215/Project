package com.amazon.mp3.library.service.sync.blacklist.database;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import com.amazon.mp3.library.service.sync.blacklist.dao.BlacklistDao;
import com.amazon.mp3.library.service.sync.blacklist.entity.LocalUri;

/* JADX INFO: loaded from: classes3.dex */
@Database(entities = {LocalUri.class}, exportSchema = false, version = 1)
public abstract class BlacklistDatabase extends RoomDatabase {
    private static volatile BlacklistDatabase INSTANCE;

    public abstract BlacklistDao blacklistDao();

    public static BlacklistDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (BlacklistDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = (BlacklistDatabase) Room.databaseBuilder(context.getApplicationContext(), BlacklistDatabase.class, "sync_blacklist.db").build();
                }
            }
        }
        return INSTANCE;
    }
}
