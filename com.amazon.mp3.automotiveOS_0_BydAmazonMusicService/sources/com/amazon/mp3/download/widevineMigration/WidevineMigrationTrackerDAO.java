package com.amazon.mp3.download.widevineMigration;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.amazon.identity.auth.device.utils.AccountConstants;
import com.amazon.mp3.util.Log;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.io.CloseableKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: WidevineMigrationTrackerDAO.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\b\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0006\u0010\u000f\u001a\u00020\u0010J\u0014\u0010\u0011\u001a\u00020\u00102\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00140\u0013J\u0014\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00140\u00132\u0006\u0010\u0016\u001a\u00020\u0017J\u0014\u0010\u0018\u001a\u00020\u00102\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u00140\u0013J\u0006\u0010\u001a\u001a\u00020\u0010J\u0006\u0010\u001b\u001a\u00020\u0010R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0006X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0006X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0006X\u0082D¢\u0006\u0002\n\u0000R\u0016\u0010\n\u001a\n \u000b*\u0004\u0018\u00010\u00060\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0006X\u0082D¢\u0006\u0002\n\u0000R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000e¨\u0006\u001c"}, d2 = {"Lcom/amazon/mp3/download/widevineMigration/WidevineMigrationTrackerDAO;", "", "db", "Landroid/database/sqlite/SQLiteDatabase;", "(Landroid/database/sqlite/SQLiteDatabase;)V", "ASIN", "", "ID", "LUID", "TABLE_NAME", "TAG", "kotlin.jvm.PlatformType", "TYPE", "getDb", "()Landroid/database/sqlite/SQLiteDatabase;", "clearAll", "", "delete", "migratedTracks", "", "Lcom/amazon/mp3/download/widevineMigration/WidevineMigrationRequiredTrack;", "getTracksByType", AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE, "", "insert", "migrationTracks", "onCreate", "onUpgrade", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class WidevineMigrationTrackerDAO {
    private final String ASIN;
    private final String ID;
    private final String LUID;
    private final String TABLE_NAME;
    private final String TAG;
    private final String TYPE;
    private final SQLiteDatabase db;

    public WidevineMigrationTrackerDAO(SQLiteDatabase db) {
        Intrinsics.checkNotNullParameter(db, "db");
        this.db = db;
        this.TAG = WidevineMigrationTrackerDAO.class.getSimpleName();
        this.TABLE_NAME = "PreWidevineTracks";
        this.ASIN = "asin";
        this.LUID = "luid";
        this.TYPE = AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE;
        this.ID = "_id";
    }

    public final SQLiteDatabase getDb() {
        return this.db;
    }

    public final void onCreate() {
        Log.debug(this.TAG, "createTable");
        this.db.beginTransaction();
        this.db.execSQL("CREATE TABLE IF NOT EXISTS " + this.TABLE_NAME + " ( " + this.ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + this.ASIN + " TEXT , " + this.LUID + " TEXT , " + this.TYPE + " TEXT NOT NULL );");
        this.db.setTransactionSuccessful();
        this.db.endTransaction();
    }

    public final void onUpgrade() {
        this.db.execSQL(Intrinsics.stringPlus("DROP TABLE IF EXISTS ", this.TABLE_NAME));
        onCreate();
    }

    public final void insert(List<WidevineMigrationRequiredTrack> migrationTracks) {
        Intrinsics.checkNotNullParameter(migrationTracks, "migrationTracks");
        this.db.beginTransaction();
        ContentValues contentValues = new ContentValues(3);
        for (WidevineMigrationRequiredTrack widevineMigrationRequiredTrack : migrationTracks) {
            Log.debug(this.TAG, "Insert %s %s %s", widevineMigrationRequiredTrack.getAsin(), widevineMigrationRequiredTrack.getLuid(), Integer.valueOf(widevineMigrationRequiredTrack.getType()));
            contentValues.put(this.ASIN, widevineMigrationRequiredTrack.getAsin());
            contentValues.put(this.LUID, widevineMigrationRequiredTrack.getLuid());
            contentValues.put(this.TYPE, Integer.valueOf(widevineMigrationRequiredTrack.getType()));
            getDb().insert(this.TABLE_NAME, null, contentValues);
        }
        this.db.setTransactionSuccessful();
        this.db.endTransaction();
    }

    public final List<WidevineMigrationRequiredTrack> getTracksByType(int type) {
        Cursor cursorRawQuery = this.db.rawQuery("\n                SELECT *\n                FROM " + this.TABLE_NAME + "\n                WHERE " + this.TYPE + " = " + type + "\n                 ", null);
        try {
            ArrayList arrayList = new ArrayList();
            if (cursorRawQuery.moveToFirst()) {
                while (!cursorRawQuery.isAfterLast()) {
                    String luid = cursorRawQuery.getString(cursorRawQuery.getColumnIndex(this.LUID));
                    String asin = cursorRawQuery.getString(cursorRawQuery.getColumnIndex(this.ASIN));
                    Intrinsics.checkNotNullExpressionValue(asin, "asin");
                    Intrinsics.checkNotNullExpressionValue(luid, "luid");
                    arrayList.add(new WidevineMigrationRequiredTrack(asin, luid, type));
                    cursorRawQuery.moveToNext();
                }
            }
            CloseableKt.closeFinally(cursorRawQuery, null);
            return arrayList;
        } finally {
        }
    }

    public final void clearAll() {
        this.db.rawQuery(Intrinsics.stringPlus("DELETE FROM ", this.TABLE_NAME), null);
    }

    public final void delete(List<WidevineMigrationRequiredTrack> migratedTracks) {
        Intrinsics.checkNotNullParameter(migratedTracks, "migratedTracks");
        this.db.beginTransaction();
        for (WidevineMigrationRequiredTrack widevineMigrationRequiredTrack : migratedTracks) {
            Log.debug(this.TAG, "Delete Track %s %s %s", widevineMigrationRequiredTrack.getAsin(), widevineMigrationRequiredTrack.getLuid(), Integer.valueOf(widevineMigrationRequiredTrack.getType()));
            getDb().delete(this.TABLE_NAME, this.ASIN + " = ? AND " + this.LUID + " = ? ", new String[]{widevineMigrationRequiredTrack.getAsin(), widevineMigrationRequiredTrack.getLuid()});
        }
        this.db.setTransactionSuccessful();
        this.db.endTransaction();
    }
}
