package com.amazon.mp3.library.provider.source.nowplaying;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.text.TextUtils;
import java.util.HashSet;

/* JADX INFO: loaded from: classes3.dex */
public final class RecentItemsManager {
    private static RecentItemsManager sInstance;
    private static final HashSet<Integer> sRecentItemTypes;
    private Context mContext;
    private SQLiteDatabase mDb;

    static {
        HashSet<Integer> hashSet = new HashSet<>();
        sRecentItemTypes = hashSet;
        hashSet.add(16);
        hashSet.add(15);
        hashSet.add(9);
        hashSet.add(5);
        hashSet.add(26);
        hashSet.add(2);
        hashSet.add(26);
        hashSet.add(32);
        sInstance = null;
    }

    public static synchronized RecentItemsManager getInstance(Context context) {
        if (sInstance == null) {
            sInstance = new RecentItemsManager(context);
        }
        return sInstance;
    }

    private RecentItemsManager(Context context) {
        this.mContext = context;
    }

    private synchronized SQLiteDatabase demandDb() {
        if (this.mDb == null) {
            this.mDb = new OpenHelper(this.mContext).getWritableDatabase();
        }
        return this.mDb;
    }

    public void clearRecentItems() {
        demandDb().delete("Recent", null, null);
    }

    public Cursor queryRecentItems(String[] projection, String selection, String[] selectionArgs, String sortOrder, String limit) {
        if (TextUtils.isEmpty(sortOrder)) {
            sortOrder = "timestamp DESC";
        }
        return demandDb().query("Recent", projection, selection, selectionArgs, null, null, sortOrder, limit);
    }

    private static class OpenHelper extends SQLiteOpenHelper {
        public OpenHelper(Context context) {
            super(context, "NowPlaying.db", (SQLiteDatabase.CursorFactory) null, 7);
        }

        @Override // android.database.sqlite.SQLiteOpenHelper
        public void onCreate(SQLiteDatabase db) {
            db.execSQL("CREATE TABLE IF NOT EXISTS Recent ( _id INTEGER PRIMARY KEY AUTOINCREMENT, key TEXT, position INTEGER DEFAULT 0, timestamp INTEGER)");
        }

        @Override // android.database.sqlite.SQLiteOpenHelper
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            if (oldVersion == 3 && newVersion == 4) {
                db.execSQL("CREATE TABLE IF NOT EXISTS Recent ( _id INTEGER PRIMARY KEY AUTOINCREMENT, key TEXT, position INTEGER DEFAULT 0, timestamp INTEGER)");
                return;
            }
            if (oldVersion < 7) {
                db.beginTransaction();
                db.execSQL("DROP TABLE IF EXISTS NowPlaying;");
                db.execSQL("DROP TABLE IF EXISTS Recent;");
                db.execSQL("CREATE TABLE IF NOT EXISTS Recent ( _id INTEGER PRIMARY KEY AUTOINCREMENT, key TEXT, position INTEGER DEFAULT 0, timestamp INTEGER)");
                db.setTransactionSuccessful();
                db.endTransaction();
            }
        }
    }
}
