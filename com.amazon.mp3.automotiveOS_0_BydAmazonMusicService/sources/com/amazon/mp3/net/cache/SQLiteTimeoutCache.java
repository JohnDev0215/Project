package com.amazon.mp3.net.cache;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.amazon.mp3.util.DbUtil;
import com.amazon.music.skyfire.models.TetheredMessageKey;

/* JADX INFO: loaded from: classes3.dex */
public class SQLiteTimeoutCache extends TimeoutCache<String, String> {
    private static final String[] CACHE_PROJECTION = {"value", TetheredMessageKey.timestamp};
    private Context mContext;
    private SQLiteDatabase mDb;
    private final String mDbFileName;
    private final String mDbTableName;

    public SQLiteTimeoutCache(Context context, String fileName, String dbName, boolean clearExisting, long cacheTimeout) {
        super(cacheTimeout);
        Context applicationContext = context.getApplicationContext();
        this.mContext = applicationContext;
        this.mDbFileName = fileName;
        this.mDbTableName = dbName;
        if (clearExisting) {
            applicationContext.deleteDatabase(fileName);
        }
    }

    public String getDbTableName() {
        return this.mDbTableName;
    }

    public String getDbFileName() {
        return this.mDbFileName;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.amazon.mp3.net.cache.TimeoutCache
    public void insert(String id, CacheData<String> data) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("key", id);
        contentValues.put("value", data.getData());
        contentValues.put(TetheredMessageKey.timestamp, Long.valueOf(data.getTimeStamp()));
        demandDb().insertWithOnConflict(this.mDbTableName, null, contentValues, 5);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.amazon.mp3.net.cache.TimeoutCache
    public CacheData<String> getCacheData(String id) throws Throwable {
        Cursor cursorQuery;
        Throwable th;
        try {
            cursorQuery = demandDb().query(getDbTableName(), CACHE_PROJECTION, "key = ?", new String[]{id}, null, null, null);
        } catch (Throwable th2) {
            cursorQuery = null;
            th = th2;
        }
        try {
            CacheData<String> fromCursor = cursorQuery.moveToFirst() ? getFromCursor(cursorQuery) : null;
            DbUtil.closeCursor(cursorQuery);
            return fromCursor;
        } catch (Throwable th3) {
            th = th3;
            DbUtil.closeCursor(cursorQuery);
            throw th;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.amazon.mp3.net.cache.TimeoutCache
    public void remove(String id) {
        demandDb().delete(getDbTableName(), "key = ?", new String[]{id});
    }

    private CacheData<String> getFromCursor(Cursor c) {
        return new CacheData<>(c.getString(c.getColumnIndex("value")), c.getLong(c.getColumnIndex(TetheredMessageKey.timestamp)));
    }

    private synchronized SQLiteDatabase demandDb() {
        if (this.mDb == null) {
            this.mDb = new OpenHelper(this.mContext, this).getWritableDatabase();
        }
        return this.mDb;
    }

    private static class OpenHelper extends SQLiteOpenHelper {
        private String mTableName;

        public OpenHelper(Context context, SQLiteTimeoutCache cache) {
            super(context, cache.getDbFileName(), (SQLiteDatabase.CursorFactory) null, 1);
            this.mTableName = cache.getDbTableName();
        }

        private String getCreateTableSQL() {
            return "CREATE TABLE IF NOT EXISTS " + this.mTableName + " ( key TEXT PRIMARY KEY, value TEXT, " + TetheredMessageKey.timestamp + " INTEGER)";
        }

        @Override // android.database.sqlite.SQLiteOpenHelper
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(getCreateTableSQL());
        }

        @Override // android.database.sqlite.SQLiteOpenHelper
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL(getCreateTableSQL());
        }
    }
}
