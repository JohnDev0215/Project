package com.amazon.mp3.playback.service.streaming;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import com.amazon.mp3.util.Log;
import com.amazon.mp3.util.SQLiteOpenHelper;

/* JADX INFO: loaded from: classes3.dex */
class CacheDatabase {
    private static final String LOGTAG = "CacheDatabase";
    private static SQLiteDatabase sInstance;
    private static OpenHelper sOpenHelper;

    CacheDatabase() {
    }

    public static SQLiteDatabase getWritableDatabase(Context context) {
        synchronized (CacheDatabase.class) {
            if (sInstance == null) {
                OpenHelper openHelper = new OpenHelper(context.getApplicationContext());
                sOpenHelper = openHelper;
                sInstance = openHelper.getWritableDatabase();
            }
        }
        return sInstance;
    }

    public static void reset(Context context) {
        getWritableDatabase(context.getApplicationContext()).delete("CachedItems", null, null);
    }

    private static class OpenHelper extends SQLiteOpenHelper {
        public OpenHelper(Context context) {
            super(context.getApplicationContext(), "PlaybackStreamCache.db", null, 2);
        }

        @Override // com.amazon.mp3.util.SQLiteOpenHelper
        public void onCreate(SQLiteDatabase db) {
            db.execSQL("CREATE TABLE IF NOT EXISTS CachedItems ( _id INTEGER PRIMARY KEY AUTOINCREMENT, content_uri TEXT UNIQUE, state INTEGER, local_path TEXT, size INTEGER, timestamp INTEGER, downsampled_bitrate INTEGER);");
        }

        @Override // com.amazon.mp3.util.SQLiteOpenHelper
        public int onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            if (oldVersion == 1 && newVersion == 2) {
                Log.debug(CacheDatabase.LOGTAG, "Executing SQL upgrade: %s", "ALTER TABLE CachedItems ADD downsampled_bitrate INTEGER;");
                db.execSQL("ALTER TABLE CachedItems ADD downsampled_bitrate INTEGER;");
            }
            return newVersion;
        }
    }
}
