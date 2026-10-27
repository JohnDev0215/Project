package com.amazon.mp3.library.provider.source.nowplaying;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.os.SystemClock;
import android.provider.BaseColumns;
import android.util.Log;
import com.amazon.mp3.library.provider.source.nowplaying.PlayQueueException;
import com.google.android.gms.common.ConnectionResult;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes3.dex */
public class PlayQueue {
    private static final String TAG = "PlayQueue";
    private static volatile PlayQueue sInstance;
    private SQLiteDatabase mDb;
    private boolean mInitialized;
    private PlayQueueHelper mPlayQueueHelper;

    public static int getMaxSize() {
        return ConnectionResult.DRIVE_EXTERNAL_STORAGE_REQUIRED;
    }

    public static PlayQueue getInstance() {
        PlayQueue playQueue = sInstance;
        if (playQueue == null) {
            synchronized (PlayQueue.class) {
                playQueue = sInstance;
                if (sInstance == null) {
                    playQueue = new PlayQueue();
                    sInstance = playQueue;
                }
            }
        }
        return playQueue;
    }

    public void init(PlayQueueHelper playQueueHelper, SQLiteDatabase sQLiteDatabase) {
        this.mPlayQueueHelper = playQueueHelper;
        this.mDb = sQLiteDatabase;
        repairIndices();
        synchronized (this) {
            this.mInitialized = true;
            notifyAll();
        }
    }

    public void waitForInitialization(long j) {
        long jElapsedRealtime = SystemClock.elapsedRealtime() + j;
        synchronized (this) {
            while (this.mDb == null) {
                if (this.mInitialized) {
                    return;
                }
                long jElapsedRealtime2 = jElapsedRealtime - SystemClock.elapsedRealtime();
                if (jElapsedRealtime2 <= 0) {
                    return;
                }
                try {
                    wait(jElapsedRealtime2);
                } catch (InterruptedException unused) {
                    Thread.currentThread().interrupt();
                    return;
                }
            }
        }
    }

    public static class NowPlaying implements BaseColumns {
        private static final String CREATE_NOW_PLAYING_TABLE = String.format("CREATE TABLE IF NOT EXISTS %s (  %s INTEGER PRIMARY KEY AUTOINCREMENT, %s TEXT,  %s INTEGER, %s INTEGER, %s TEXT, %s TEXT, %s TEXT)", "NowPlaying", "_id", "uri", "udo", "shuffle", "luid", "asin", "catalog_playlist_id");

        public static void createTable(SQLiteDatabase sQLiteDatabase) {
            sQLiteDatabase.beginTransaction();
            sQLiteDatabase.execSQL(CREATE_NOW_PLAYING_TABLE);
            sQLiteDatabase.setTransactionSuccessful();
            sQLiteDatabase.endTransaction();
        }

        public static void deleteTable(SQLiteDatabase sQLiteDatabase) {
            sQLiteDatabase.beginTransaction();
            sQLiteDatabase.execSQL("DROP TABLE IF EXISTS NowPlaying");
            sQLiteDatabase.setTransactionSuccessful();
            sQLiteDatabase.endTransaction();
        }

        public static void createIndexes(SQLiteDatabase sQLiteDatabase) {
            sQLiteDatabase.beginTransaction();
            sQLiteDatabase.execSQL("CREATE INDEX IF NOT EXISTS PlayQueueAsinIndex ON NowPlaying(asin)");
            sQLiteDatabase.execSQL("CREATE INDEX IF NOT EXISTS PlayQueueLuidIndex ON NowPlaying(luid)");
            sQLiteDatabase.execSQL("CREATE INDEX IF NOT EXISTS PlayQueueUdoIndex ON NowPlaying(udo)");
            sQLiteDatabase.execSQL("CREATE INDEX IF NOT EXISTS PlayQueueShuffleIndex ON NowPlaying(shuffle)");
            sQLiteDatabase.setTransactionSuccessful();
            sQLiteDatabase.endTransaction();
        }

        public static void doUpgrade(Context context, SQLiteDatabase sQLiteDatabase, int i) {
            deleteTable(sQLiteDatabase);
            createTable(sQLiteDatabase);
        }
    }

    private void checkDb() throws Exception {
        if (this.mDb == null) {
            throw new PlayQueueException("mDb is null", PlayQueueException.Reason.DB_NULL);
        }
    }

    public Cursor query(String[] strArr, String str) {
        return this.mPlayQueueHelper.queryNowPlayingTracks(strArr, str);
    }

    public int size() throws Exception {
        checkDb();
        return (int) DatabaseUtils.queryNumEntries(this.mDb, "NowPlaying");
    }

    public void replace(Cursor cursor, Uri uri, int i, boolean z) throws Exception {
        clear();
        insertTracks(cursor, uri, 0, i, z);
    }

    public void replaceTrack(Cursor cursor, Uri uri, int i) throws Exception {
        checkDb();
        if (cursor == null || cursor.getCount() == 0 || uri == null || i < 0) {
            return;
        }
        if (this.mPlayQueueHelper == null) {
            throw new IllegalArgumentException("mPlayQueueHelper is null");
        }
        this.mDb.beginTransactionNonExclusive();
        try {
            cursor.moveToFirst();
            replaceTrackInDb(cursor, uri, i, getColumnFromUdoIndex("_id", i), getColumnFromUdoIndex("shuffle", i));
            this.mDb.setTransactionSuccessful();
        } finally {
            this.mDb.endTransaction();
            cursor.close();
        }
    }

    public void insert(Cursor cursor, Uri uri, int i) throws Exception {
        insertTracks(cursor, uri, i, -1, false);
    }

    public void insertTail(Cursor cursor, Uri uri) throws Exception {
        insertTracks(cursor, uri, size(), -1, false);
    }

    private void insertTracks(Cursor cursor, Uri uri, int i, int i2, boolean z) throws Exception {
        checkDb();
        if (this.mPlayQueueHelper == null) {
            throw new IllegalArgumentException("mPlayQueueHelper is null");
        }
        if (cursor == null || uri == null || i < 0) {
            throw new IllegalArgumentException("tracksUri = " + uri + ", insertionPoint = " + i);
        }
        if (cursor.getCount() <= 0) {
            throw new PlayQueueException("tracksCursor is null", PlayQueueException.Reason.TRACKS_NOT_AVAILABLE);
        }
        int count = cursor.getCount();
        int size = size();
        int i3 = 1500 - size;
        if (i3 <= 0) {
            throw new PlayQueueException("PlayQueue is full", PlayQueueException.Reason.PLAYQUEUE_FULL);
        }
        List<Integer> listGenerateShuffleList = generateShuffleList(count, i3, i2);
        int udoOffset = getUdoOffset(count, i3, i2);
        boolean z2 = i2 >= 0 && (z || this.mPlayQueueHelper.isUnlimitedCollection(uri));
        if (z) {
            cursor.moveToPosition(udoOffset);
        } else if (z2) {
            cursor.moveToPosition(i2);
        } else {
            cursor.moveToFirst();
        }
        if (count > i3) {
            count = i3;
        }
        splitForInsert(i, count, size);
        this.mDb.beginTransactionNonExclusive();
        for (int i4 = 0; i4 < count; i4++) {
            try {
                insertTrackToDb(cursor, uri, i4 + i, listGenerateShuffleList.get(i4).intValue() + i);
                cursor.moveToNext();
                if (z2 && cursor.isAfterLast()) {
                    cursor.moveToFirst();
                }
            } finally {
                this.mDb.endTransaction();
                cursor.close();
            }
        }
        this.mDb.setTransactionSuccessful();
    }

    private void insertTrackToDb(Cursor cursor, Uri uri, int i, int i2) throws Exception {
        checkDb();
        this.mDb.insert("NowPlaying", "_id", populateContentValues(cursor, uri, i, i2));
    }

    private void replaceTrackInDb(Cursor cursor, Uri uri, int i, int i2, int i3) throws Exception {
        checkDb();
        ContentValues contentValuesPopulateContentValues = populateContentValues(cursor, uri, i, i3);
        contentValuesPopulateContentValues.put("_id", Integer.valueOf(i2));
        this.mDb.replace("NowPlaying", "_id", contentValuesPopulateContentValues);
    }

    private ContentValues populateContentValues(Cursor cursor, Uri uri, int i, int i2) throws Exception {
        checkDb();
        int columnIndex = cursor.getColumnIndex("luid");
        int columnIndex2 = cursor.getColumnIndex("asin");
        ContentValues contentValues = new ContentValues();
        String string = cursor.getString(columnIndex);
        String string2 = cursor.getString(columnIndex2);
        String catalogPlaylistId = this.mPlayQueueHelper.getCatalogPlaylistId(uri);
        contentValues.put("uri", uri.toString());
        contentValues.put("luid", string);
        contentValues.put("udo", Integer.valueOf(i));
        contentValues.put("shuffle", Integer.valueOf(i2));
        contentValues.put("asin", string2);
        contentValues.put("catalog_playlist_id", catalogPlaylistId);
        return contentValues;
    }

    public void clear() throws Exception {
        checkDb();
        this.mDb.delete("NowPlaying", null, null);
    }

    public void remove(int i, boolean z) throws Exception {
        int columnFromUdoIndex;
        if (i < 0) {
            throw new IllegalArgumentException("trackPosition = " + i);
        }
        checkDb();
        if (z) {
            columnFromUdoIndex = i;
            i = getColumnFromShuffleIndex("udo", i);
        } else {
            columnFromUdoIndex = getColumnFromUdoIndex("shuffle", i);
        }
        if (i >= 0 && columnFromUdoIndex >= 0) {
            this.mDb.delete("NowPlaying", "udo = ?", new String[]{Long.toString(i)});
            shiftAfterRemove(i, columnFromUdoIndex, size());
            return;
        }
        Log.e(TAG, "Cannot remove entry from PlayQueue - udoValue = " + i + " shuffleValue = %d" + columnFromUdoIndex);
    }

    public void shift(int i, int i2, boolean z) throws Exception {
        int columnFromUdoIndex;
        String str;
        if (i == i2) {
            return;
        }
        int size = size();
        if (i < 0 || i >= size || i2 < 0 || i2 >= size) {
            throw new IllegalArgumentException("fromTrackPosition = " + i + " toTrackPosition = " + i2 + " size = " + size);
        }
        checkDb();
        if (z) {
            columnFromUdoIndex = getColumnFromShuffleIndex("_id", i);
            str = "shuffle";
        } else {
            columnFromUdoIndex = getColumnFromUdoIndex("_id", i);
            str = "udo";
        }
        if (columnFromUdoIndex < 0) {
            throw new IllegalStateException("cannot update track's positionfromId = " + columnFromUdoIndex);
        }
        if (i < i2) {
            shiftUp(i, i2, str, 1);
        } else {
            shiftDown(i, i2, str, 1);
        }
        this.mDb.execSQL(String.format("UPDATE %s SET %s = %s WHERE %s = ?", "NowPlaying", str, Integer.valueOf(i2), "_id"), new String[]{String.valueOf(columnFromUdoIndex)});
    }

    /* JADX WARN: Finally extract failed */
    public void shuffle(int i) throws Exception {
        checkDb();
        if (i < 0) {
            throw new IllegalArgumentException("trackPosition = " + i);
        }
        int size = size();
        List<Integer> listGenerateShuffleList = generateShuffleList(size, size, i);
        Cursor cursorInternalListQuery = internalListQuery(null, "udo");
        if (cursorInternalListQuery != null) {
            try {
                if (cursorInternalListQuery.moveToFirst()) {
                    this.mDb.beginTransactionNonExclusive();
                    try {
                        int columnIndex = cursorInternalListQuery.getColumnIndex("_id");
                        ContentValues contentValues = new ContentValues();
                        int i2 = 0;
                        do {
                            long j = cursorInternalListQuery.getLong(columnIndex);
                            if (j != -1) {
                                contentValues.put("shuffle", listGenerateShuffleList.get(i2));
                                this.mDb.update("NowPlaying", contentValues, "_id=?", new String[]{String.valueOf(j)});
                                i2++;
                            }
                        } while (cursorInternalListQuery.moveToNext());
                        this.mDb.setTransactionSuccessful();
                        this.mDb.endTransaction();
                    } catch (Throwable th) {
                        this.mDb.endTransaction();
                        throw th;
                    }
                }
            } finally {
                if (!cursorInternalListQuery.isClosed()) {
                    cursorInternalListQuery.close();
                }
            }
        }
        if (cursorInternalListQuery != null) {
        }
    }

    private Map<Long, Integer> generateRepairList(String str) throws Exception {
        checkDb();
        HashMap map = new HashMap();
        Cursor cursorInternalListQuery = internalListQuery(null, str);
        if (cursorInternalListQuery != null) {
            try {
                if (cursorInternalListQuery.moveToFirst()) {
                    int columnIndex = cursorInternalListQuery.getColumnIndex("_id");
                    int columnIndex2 = cursorInternalListQuery.getColumnIndex(str);
                    do {
                        if (cursorInternalListQuery.getInt(columnIndex2) != cursorInternalListQuery.getPosition()) {
                            map.put(Long.valueOf(cursorInternalListQuery.getLong(columnIndex)), Integer.valueOf(cursorInternalListQuery.getPosition()));
                        }
                    } while (cursorInternalListQuery.moveToNext());
                }
            } finally {
                if (!cursorInternalListQuery.isClosed()) {
                    cursorInternalListQuery.close();
                }
            }
        }
        if (cursorInternalListQuery != null) {
        }
        return map;
    }

    private void setIndices(Map<Long, Integer> map, String str) throws Exception {
        if (map.isEmpty()) {
            return;
        }
        checkDb();
        this.mDb.beginTransactionNonExclusive();
        try {
            ContentValues contentValues = new ContentValues();
            for (Map.Entry<Long, Integer> entry : map.entrySet()) {
                long jLongValue = entry.getKey().longValue();
                contentValues.put(str, entry.getValue());
                this.mDb.update("NowPlaying", contentValues, "_id=?", new String[]{String.valueOf(jLongValue)});
            }
            this.mDb.setTransactionSuccessful();
        } finally {
            this.mDb.endTransaction();
        }
    }

    public void repairIndices() {
        try {
            if (size() == 0) {
                return;
            }
            try {
                setIndices(generateRepairList("udo"), "udo");
                setIndices(generateRepairList("shuffle"), "shuffle");
            } catch (Exception e) {
                Log.e(TAG, "Error repairing indices - clearing queue instead.", e);
                clear();
            }
        } catch (Exception e2) {
            Log.e(TAG, "Error repairing indices.", e2);
        }
    }

    public int getUdoOffset(int i, int i2, int i3) {
        int i4;
        if (i < i2 || i3 <= (i4 = i2 / 2)) {
            return 0;
        }
        return Math.min(i - i2, i3 - i4);
    }

    private List<Integer> generateShuffleList(int i, int i2, int i3) {
        int iMin = Math.min(i, i2);
        int udoOffset = i3 - getUdoOffset(i, i2, i3);
        ArrayList arrayList = new ArrayList(iMin);
        if (udoOffset < 0 || udoOffset >= iMin) {
            arrayList.add(0);
        }
        for (int i4 = 1; i4 < iMin; i4++) {
            arrayList.add(Integer.valueOf(i4));
        }
        Collections.shuffle(arrayList);
        if (udoOffset >= 0 && udoOffset < iMin) {
            arrayList.add(udoOffset, 0);
        }
        return arrayList;
    }

    private Cursor internalListQuery(String[] strArr, String str) {
        return this.mDb.query("NowPlaying", strArr, null, null, null, null, str);
    }

    private int intFromCursor(Cursor cursor) {
        try {
            try {
                if (cursor.moveToFirst()) {
                    return cursor.getInt(0);
                }
            } catch (Exception unused) {
                Log.d(TAG, "Could not parse int", new Throwable());
            }
            return -1;
        } finally {
            cursor.close();
        }
    }

    private void splitForInsert(int i, int i2, int i3) {
        shiftDown(i3, i, "udo", i2);
        shiftDown(i3, i, "shuffle", i2);
    }

    private void shiftAfterRemove(int i, int i2, int i3) {
        shiftUp(i, i3, "udo", 1);
        shiftUp(i2, i3, "shuffle", 1);
    }

    private void shiftDown(int i, int i2, String str, int i3) {
        this.mDb.execSQL(String.format("UPDATE %s SET %s = %s + %d WHERE %s < ? AND %s >= ?", "NowPlaying", str, str, Integer.valueOf(i3), str, str), new String[]{String.valueOf(i), String.valueOf(i2)});
    }

    private void shiftUp(int i, int i2, String str, int i3) {
        this.mDb.execSQL(String.format("UPDATE %s SET %s = %s - %d WHERE %s > ? AND %s <= ?", "NowPlaying", str, str, Integer.valueOf(i3), str, str), new String[]{String.valueOf(i), String.valueOf(i2)});
    }

    public int getColumnFromUdoIndex(String str, int i) {
        SQLiteQueryBuilder sQLiteQueryBuilder = new SQLiteQueryBuilder();
        sQLiteQueryBuilder.setTables("NowPlaying");
        return intFromCursor(sQLiteQueryBuilder.query(this.mDb, new String[]{str}, "udo=?", new String[]{Integer.toString(i)}, null, null, null));
    }

    public int getColumnFromShuffleIndex(String str, int i) {
        SQLiteQueryBuilder sQLiteQueryBuilder = new SQLiteQueryBuilder();
        sQLiteQueryBuilder.setTables("NowPlaying");
        return intFromCursor(sQLiteQueryBuilder.query(this.mDb, new String[]{str}, "shuffle=?", new String[]{Integer.toString(i)}, null, null, null));
    }
}
