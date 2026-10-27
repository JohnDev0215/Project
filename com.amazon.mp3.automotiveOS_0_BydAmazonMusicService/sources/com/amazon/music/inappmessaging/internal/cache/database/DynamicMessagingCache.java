package com.amazon.music.inappmessaging.internal.cache.database;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import com.amazon.music.inappmessaging.internal.cache.converters.DynamicMessageConverter;
import com.amazon.music.inappmessaging.internal.cache.converters.TriggerConverter;
import com.amazon.music.inappmessaging.internal.cache.dao.DynamicMessagingDao;
import com.amazon.music.inappmessaging.internal.cache.entity.DynamicMessagesPerTrigger;

/* JADX INFO: loaded from: classes4.dex */
@TypeConverters({TriggerConverter.class, DynamicMessageConverter.class})
@Database(entities = {DynamicMessagesPerTrigger.class}, exportSchema = false, version = 1)
public abstract class DynamicMessagingCache extends RoomDatabase {
    private static final String DATABASE_NAME = "dynMsgCache.db";
    private static volatile DynamicMessagingCache INSTANCE;

    public abstract DynamicMessagingDao dynamicMessagingDao();

    public static DynamicMessagingCache getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (DynamicMessagingCache.class) {
                if (INSTANCE == null) {
                    INSTANCE = (DynamicMessagingCache) Room.databaseBuilder(context.getApplicationContext(), DynamicMessagingCache.class, DATABASE_NAME).build();
                }
            }
        }
        return INSTANCE;
    }
}
