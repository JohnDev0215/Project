package com.amazon.mp3.recentlyplayed;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabaseLockedException;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.text.TextUtils;
import com.amazon.mp3.library.db.ColumnType;
import com.amazon.mp3.library.provider.source.cirrus.CirrusMediaSource;
import com.amazon.mp3.prime.PrimeTracksCache;
import com.amazon.mp3.recentlyplayed.RecentlyPlayedItem;
import com.amazon.mp3.util.DbUtil;
import com.amazon.mp3.util.Log;
import com.amazon.mp3.util.StringUtil;
import com.amazon.mp3.util.extensions.SQLiteDatabaseExtensions;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
public class RecentlyPlayedDatabase {
    private static final String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS recently_played ( _id INTEGER PRIMARY KEY AUTOINCREMENT, content_uri TEXT, local_uri TEXT, provider_state TEXT, title TEXT, collection_id TEXT, collection_source TEXT, collection_type TEXT, track_luid TEXT, track_asin TEXT, album_art_uri TEXT, shuffle_on INTEGER, last_played_time INTEGER, synced INTEGER, collection_asin TEXT, is_explicit INTEGER DEFAULT 0, is_remote INTEGER DEFAULT 0, " + PrimeTracksCache.getCreatePrimeColumnStatement() + ", catalog_status_tiers INTEGER DEFAULT 0, content_encoding TEXT )";
    private static final String TAG = "RecentlyPlayedDatabase";
    private static RecentlyPlayedDatabase sInstance;
    private final Context mContext;
    private final SQLiteDatabase mDb;

    private RecentlyPlayedDatabase(Context context) {
        Context applicationContext = context.getApplicationContext();
        this.mContext = applicationContext;
        this.mDb = new OpenHelper(applicationContext).getWritableDatabase();
    }

    public static RecentlyPlayedDatabase getInstance(final Context context) {
        if (sInstance == null) {
            synchronized (RecentlyPlayedDatabase.class) {
                if (sInstance == null) {
                    sInstance = new RecentlyPlayedDatabase(context);
                }
            }
        }
        return sInstance;
    }

    public SQLiteDatabase getDb() {
        return this.mDb;
    }

    public void createAlexaTable() {
        try {
            getDb().beginTransaction();
            getDb().execSQL("CREATE TABLE IF NOT EXISTS alexa_recently_played ( _id INTEGER PRIMARY KEY AUTOINCREMENT, alexa_asin TEXT, alexa_luid TEXT, alexa_album_asin TEXT )");
            getDb().setTransactionSuccessful();
        } finally {
            getDb().endTransaction();
        }
    }

    public static synchronized void close() {
        SQLiteDatabase sQLiteDatabase;
        RecentlyPlayedDatabase recentlyPlayedDatabase = sInstance;
        if (recentlyPlayedDatabase != null && (sQLiteDatabase = recentlyPlayedDatabase.mDb) != null) {
            sQLiteDatabase.close();
            Log.info(TAG, "Closed the connection for RecentlyPlayedDatabase");
            sInstance = null;
        }
    }

    public Cursor queryByLastPlayedTimeDesc() {
        return getDb().query("recently_played", null, null, null, null, null, "last_played_time DESC", Integer.toString(50));
    }

    public Cursor queryStationsItemsByLastPlayedTime() {
        return getDb().query("recently_played", null, "collection_type=?", new String[]{RecentlyPlayedItem.Type.STATION.toString()}, null, null, "last_played_time DESC");
    }

    public Cursor queryMostRecentTrack() {
        return getDb().query("recently_played_tracks", null, null, null, null, null, "last_played_time DESC", "1");
    }

    public long insertRecentlyPlayed(final ContentValues values, final boolean dedupeOnCollectionId, final boolean dedupeOnSource, final String dedupeCollectionType) {
        SQLiteDatabase db = getDb();
        String asString = values.getAsString("content_uri");
        try {
            db.beginTransaction();
            try {
                if (!TextUtils.isEmpty(asString)) {
                    String asString2 = values.getAsString("collection_type");
                    String asString3 = values.getAsString("collection_id");
                    StringBuffer stringBuffer = new StringBuffer();
                    stringBuffer.append("(");
                    stringBuffer.append("collection_type");
                    stringBuffer.append("=?");
                    ArrayList arrayList = new ArrayList();
                    arrayList.add(asString2);
                    if (!StringUtil.isNullOrWhiteSpaces(dedupeCollectionType)) {
                        stringBuffer.append(" OR ");
                        stringBuffer.append("collection_type");
                        stringBuffer.append("=?");
                        arrayList.add(dedupeCollectionType);
                    }
                    stringBuffer.append(")");
                    if (!dedupeOnCollectionId) {
                        stringBuffer.append(" AND ");
                        stringBuffer.append("collection_id");
                        stringBuffer.append("=?");
                        arrayList.add(asString3);
                    }
                    if (!dedupeOnSource) {
                        stringBuffer.append(" AND ");
                        stringBuffer.append("collection_source");
                        stringBuffer.append("=?");
                        arrayList.add(values.getAsString("collection_source"));
                    }
                    db.delete("recently_played", stringBuffer.toString(), (String[]) arrayList.toArray(new String[arrayList.size()]));
                }
                long jInsert = db.insert("recently_played", null, values);
                db.setTransactionSuccessful();
                return jInsert;
            } finally {
                db.endTransaction();
                this.mContext.getContentResolver().notifyChange(CirrusMediaSource.NOTIFICATION_URI, null);
            }
        } catch (SQLiteDatabaseLockedException e) {
            Log.error(TAG, "insertRecentlyPlayed failed with exception: " + e);
            return -1L;
        }
    }

    public void set(List<ContentValues> values) {
        SQLiteDatabase db = getDb();
        try {
            db.beginTransaction();
            clear();
            Iterator<ContentValues> it = values.iterator();
            while (it.hasNext()) {
                insertRecentlyPlayed(it.next(), false, false, null);
            }
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
        }
    }

    public void clear() {
        getDb().delete("recently_played", null, null);
    }

    public void clearRecentTracks() {
        getDb().delete("recently_played_tracks", null, null);
    }

    public void prune(int maxNumber) {
        if (maxNumber < 0) {
            throw new IllegalArgumentException();
        }
        getDb().delete("recently_played", "_id NOT IN (SELECT _id FROM recently_played ORDER BY last_played_time DESC LIMIT " + maxNumber + ") AND _id NOT IN (SELECT _id FROM recently_played WHERE collection_type=? ORDER BY last_played_time DESC LIMIT 50)", new String[]{RecentlyPlayedItem.Type.STATION.toString()});
    }

    public void pruneRecentTracks(final int maxNumber) {
        if (maxNumber < 0) {
            throw new IllegalArgumentException();
        }
        getDb().delete("recently_played_tracks", "_uid NOT IN (SELECT _uid FROM recently_played_tracks ORDER BY last_played_time DESC LIMIT 250)", null);
    }

    public void bulkInsertRecentTracks(final List<ContentValues> values) {
        SQLiteDatabase db = getDb();
        try {
            db.beginTransaction();
            try {
                try {
                    db.delete("recently_played_tracks", "_uid NOT IN (SELECT _uid FROM recently_played_tracks WHERE recent_track_asin IS NULL AND recent_track_source=?)", new String[]{"cirrus-local"});
                    Iterator<ContentValues> it = values.iterator();
                    while (it.hasNext()) {
                        db.insert("recently_played_tracks", null, it.next());
                    }
                    db.setTransactionSuccessful();
                } finally {
                    db.endTransaction();
                    this.mContext.getContentResolver().notifyChange(CirrusMediaSource.NOTIFICATION_URI, null);
                }
            } catch (Exception e) {
                Log.error(TAG, "insertRecentTrack failed with exception: " + e);
            }
        } catch (SQLiteDatabaseLockedException e2) {
            Log.error(TAG, "bulkInsertRecentTracks failed with exception: " + e2);
        }
    }

    public long insertRecentTrack(final ContentValues values) {
        Intent intent;
        SQLiteDatabase db = getDb();
        long jInsert = -1;
        try {
            db.beginTransaction();
            try {
                try {
                    dedupeMostRecentTrack(values);
                    jInsert = db.insert("recently_played_tracks", null, values);
                    db.delete("recently_played_tracks", "_uid NOT IN (SELECT _uid FROM recently_played_tracks ORDER BY last_played_time DESC LIMIT 250)", null);
                    db.setTransactionSuccessful();
                    db.endTransaction();
                    this.mContext.getContentResolver().notifyChange(CirrusMediaSource.NOTIFICATION_URI, null);
                    intent = new Intent();
                } catch (Throwable th) {
                    db.endTransaction();
                    this.mContext.getContentResolver().notifyChange(CirrusMediaSource.NOTIFICATION_URI, null);
                    Intent intent2 = new Intent();
                    intent2.setAction("com.amazon.mp3.action.sync_updated");
                    this.mContext.sendBroadcast(intent2);
                    throw th;
                }
            } catch (Exception e) {
                Log.error(TAG, "insertRecentTrack failed with exception: " + e);
                db.endTransaction();
                this.mContext.getContentResolver().notifyChange(CirrusMediaSource.NOTIFICATION_URI, null);
                intent = new Intent();
            }
            intent.setAction("com.amazon.mp3.action.sync_updated");
            this.mContext.sendBroadcast(intent);
            return jInsert;
        } catch (SQLiteDatabaseLockedException e2) {
            Log.error(TAG, "bulkInsertRecentTracks failed with exception: " + e2);
            return -1L;
        }
    }

    public void dedupeMostRecentTrack(final ContentValues values) {
        Cursor cursorQueryMostRecentTrack = queryMostRecentTrack();
        if (cursorQueryMostRecentTrack != null) {
            try {
                if (cursorQueryMostRecentTrack.moveToFirst()) {
                    String string = cursorQueryMostRecentTrack.getString(cursorQueryMostRecentTrack.getColumnIndexOrThrow("recent_track_luid"));
                    String string2 = cursorQueryMostRecentTrack.getString(cursorQueryMostRecentTrack.getColumnIndexOrThrow("recent_track_asin"));
                    long j = cursorQueryMostRecentTrack.getLong(cursorQueryMostRecentTrack.getColumnIndexOrThrow("last_played_time"));
                    boolean z = true;
                    if ((StringUtil.isNullOrWhiteSpaces(string) || !string.equals(values.getAsString("recent_track_luid"))) && (StringUtil.isNullOrWhiteSpaces(string2) || !string2.equals(values.getAsString("recent_track_asin")))) {
                        z = false;
                    }
                    if (z) {
                        deleteRecentTrack(string, string2, j);
                    }
                }
            } finally {
                DbUtil.closeCursor(cursorQueryMostRecentTrack);
            }
        }
    }

    public void deleteRecentTrack(final String trackLuid, final String trackAsin, final long lastPlayedTime) {
        StringBuffer stringBuffer = new StringBuffer();
        ArrayList arrayList = new ArrayList();
        stringBuffer.append("(");
        if (!StringUtil.isNullOrWhiteSpaces(trackLuid)) {
            stringBuffer.append("recent_track_luid");
            stringBuffer.append("=?");
            stringBuffer.append(" AND ");
            arrayList.add(trackLuid);
        }
        if (!StringUtil.isNullOrWhiteSpaces(trackAsin)) {
            stringBuffer.append("recent_track_asin");
            stringBuffer.append("=?");
            stringBuffer.append(" AND ");
            arrayList.add(trackAsin);
        }
        stringBuffer.append("last_played_time");
        stringBuffer.append("=?");
        stringBuffer.append(")");
        arrayList.add(Long.toString(lastPlayedTime));
        getDb().delete("recently_played_tracks", stringBuffer.toString(), (String[]) arrayList.toArray(new String[arrayList.size()]));
    }

    public long insertAlexaRecentItem(ContentValues values) {
        SQLiteDatabase db = getDb();
        db.beginTransaction();
        long j = -1;
        try {
            if (!isNewAlexaTrackDuplicateOfLast(db, values)) {
                long jInsert = db.insert("alexa_recently_played", null, values);
                db.delete("alexa_recently_played", "_id NOT IN (SELECT _id FROM alexa_recently_played ORDER BY _id DESC LIMIT 50)", null);
                j = jInsert;
            }
            db.setTransactionSuccessful();
            return j;
        } finally {
            db.endTransaction();
        }
    }

    private static boolean isNewAlexaTrackDuplicateOfLast(final SQLiteDatabase db, final ContentValues values) {
        Cursor cursorQuery = db.query("alexa_recently_played", null, null, null, null, null, "_id DESC", "1");
        boolean z = false;
        if (cursorQuery != null) {
            try {
                if (cursorQuery.moveToFirst()) {
                    Iterator<String> it = values.keySet().iterator();
                    while (true) {
                        if (!it.hasNext()) {
                            z = true;
                            break;
                        }
                        String next = it.next();
                        if (!TextUtils.equals(cursorQuery.getString(cursorQuery.getColumnIndexOrThrow(next)), values.getAsString(next))) {
                            break;
                        }
                    }
                }
            } finally {
                cursorQuery.close();
            }
        }
        return z;
    }

    public List<String> getAlexaRecentlyPlayedAlbumAsins() {
        ArrayList arrayList = new ArrayList();
        try {
            Cursor cursorQuery = getDb().query("alexa_recently_played", new String[]{"alexa_album_asin"}, "alexa_album_asin IS NOT NULL", null, null, null, null);
            if (cursorQuery != null) {
                while (cursorQuery.moveToNext()) {
                    try {
                        arrayList.add(cursorQuery.getString(cursorQuery.getColumnIndexOrThrow("alexa_album_asin")));
                    } finally {
                        cursorQuery.close();
                    }
                }
            }
            return arrayList;
        } catch (SQLiteException e) {
            Log.error(TAG, "Received an SQLite exception when trying to get getAlexaRecentlyPlayedAlbumAsins. Creating the table in-case it does not exist Ex: ", e);
            createAlexaTable();
            return arrayList;
        }
    }

    public void clearAlexaRecentlyPlayed() {
        try {
            getDb().delete("alexa_recently_played", null, null);
        } catch (SQLiteException e) {
            Log.warning(TAG, "Got SQLiteException trying to clear the Alexa Recently Played table.  Probably tried to clear it before creating it.", e);
        }
    }

    private static class OpenHelper extends SQLiteOpenHelper {
        public OpenHelper(Context context) {
            super(context, "recently_played.db", (SQLiteDatabase.CursorFactory) null, 18);
        }

        @Override // android.database.sqlite.SQLiteOpenHelper
        public void onCreate(SQLiteDatabase db) {
            db.beginTransaction();
            try {
                db.execSQL(RecentlyPlayedDatabase.CREATE_TABLE);
                db.execSQL("CREATE TABLE IF NOT EXISTS alexa_recently_played ( _id INTEGER PRIMARY KEY AUTOINCREMENT, alexa_asin TEXT, alexa_luid TEXT, alexa_album_asin TEXT )");
                db.execSQL("CREATE TABLE IF NOT EXISTS recently_played_tracks ( _uid INTEGER PRIMARY KEY, recent_track_asin TEXT, recent_track_luid TEXT, recent_album_asin TEXT, album_art_uri TEXT, recent_track_source TEXT, is_remote INTEGER DEFAULT 0, last_played_time INTEGER, catalog_status_tiers INTEGER DEFAULT 0 )");
                db.setTransactionSuccessful();
            } finally {
                db.endTransaction();
            }
        }

        @Override // android.database.sqlite.SQLiteOpenHelper
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.beginTransaction();
            try {
                if (oldVersion <= 5) {
                    try {
                        db.execSQL("DROP TABLE recently_played");
                        db.execSQL(RecentlyPlayedDatabase.CREATE_TABLE);
                    } catch (SQLiteException e) {
                        DbUtil.reportDbUpgradeException(oldVersion, newVersion, RecentlyPlayedDatabase.class.getSimpleName(), e);
                    }
                }
                if (oldVersion == 6) {
                    SQLiteDatabaseExtensions.addColumn(db, "recently_played", "local_uri", ColumnType.TextType.INSTANCE);
                }
                if (oldVersion >= 6 && oldVersion <= 8) {
                    db.delete("recently_played", "collection_type=?", new String[]{RecentlyPlayedItem.Type.PRIME_BROWSE_SONGS.toString()});
                }
                if (oldVersion == 8) {
                    try {
                        SQLiteDatabaseExtensions.addColumn(db, "recently_played", "local_uri", ColumnType.TextType.INSTANCE);
                    } catch (SQLiteException unused) {
                    }
                }
                if (oldVersion == 9) {
                    db.delete("recently_played", "collection_type=?", new String[]{RecentlyPlayedItem.Type.PRIME_BROWSE_SONGS.toString()});
                }
                if (oldVersion <= 10) {
                    try {
                        SQLiteDatabaseExtensions.addColumn(db, "recently_played", "track_asin", ColumnType.TextType.INSTANCE);
                    } catch (SQLiteException unused2) {
                    }
                }
                if (oldVersion <= 11) {
                    db.execSQL("CREATE TABLE IF NOT EXISTS alexa_recently_played ( _id INTEGER PRIMARY KEY AUTOINCREMENT, alexa_asin TEXT, alexa_luid TEXT, alexa_album_asin TEXT )");
                }
                if (oldVersion <= 12) {
                    db.execSQL("DROP TABLE recently_played");
                    db.execSQL("DROP TABLE alexa_recently_played");
                    db.execSQL(RecentlyPlayedDatabase.CREATE_TABLE);
                    db.execSQL("CREATE TABLE IF NOT EXISTS alexa_recently_played ( _id INTEGER PRIMARY KEY AUTOINCREMENT, alexa_asin TEXT, alexa_luid TEXT, alexa_album_asin TEXT )");
                }
                if (oldVersion <= 13) {
                    db.execSQL("DROP TABLE recently_played");
                    db.execSQL(RecentlyPlayedDatabase.CREATE_TABLE);
                }
                if (oldVersion <= 14) {
                    db.execSQL("CREATE TABLE IF NOT EXISTS recently_played_tracks ( _uid INTEGER PRIMARY KEY, recent_track_asin TEXT, recent_track_luid TEXT, recent_album_asin TEXT, album_art_uri TEXT, recent_track_source TEXT, is_remote INTEGER DEFAULT 0, last_played_time INTEGER, catalog_status_tiers INTEGER DEFAULT 0 )");
                }
                if (oldVersion <= 16) {
                    db.execSQL("DROP TABLE recently_played_tracks");
                    db.execSQL("DROP TABLE recently_played");
                    db.execSQL(RecentlyPlayedDatabase.CREATE_TABLE);
                    db.execSQL("CREATE TABLE IF NOT EXISTS recently_played_tracks ( _uid INTEGER PRIMARY KEY, recent_track_asin TEXT, recent_track_luid TEXT, recent_album_asin TEXT, album_art_uri TEXT, recent_track_source TEXT, is_remote INTEGER DEFAULT 0, last_played_time INTEGER, catalog_status_tiers INTEGER DEFAULT 0 )");
                }
                if (oldVersion <= 17) {
                    try {
                        SQLiteDatabaseExtensions.addColumn(db, "recently_played", "content_encoding", ColumnType.TextType.INSTANCE);
                    } catch (SQLiteException unused3) {
                    }
                }
                db.setTransactionSuccessful();
            } finally {
                db.endTransaction();
            }
        }
    }
}
