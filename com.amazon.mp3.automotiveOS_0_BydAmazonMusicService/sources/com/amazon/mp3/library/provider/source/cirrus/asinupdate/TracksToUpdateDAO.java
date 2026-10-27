package com.amazon.mp3.library.provider.source.cirrus.asinupdate;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteConstraintException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Pair;
import com.amazon.mp3.util.Log;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.io.CloseableKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: TracksToUpdateDAO.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\f\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010!\u001a\u00020\u0018¢\u0006\u0004\b\"\u0010#J\u0006\u0010\u0003\u001a\u00020\u0002J\u001f\u0010\n\u001a\u00020\u00072\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u0004H\u0000¢\u0006\u0004\b\b\u0010\tJ\u0017\u0010\u000f\u001a\u00020\f2\u0006\u0010\u000b\u001a\u00020\u0004H\u0000¢\u0006\u0004\b\r\u0010\u000eJ!\u0010\u0014\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00040\u00110\u0010H\u0000¢\u0006\u0004\b\u0012\u0010\u0013R\u001c\u0010\u0016\u001a\n \u0015*\u0004\u0018\u00010\u00040\u00048\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0016\u0010\u0017R\u0017\u0010\u0019\u001a\u00020\u00188\u0006¢\u0006\f\n\u0004\b\u0019\u0010\u001a\u001a\u0004\b\u001b\u0010\u001cR\u0014\u0010\u001d\u001a\u00020\u00048\u0002X\u0082D¢\u0006\u0006\n\u0004\b\u001d\u0010\u0017R\u0014\u0010\u001e\u001a\u00020\u00048\u0002X\u0082D¢\u0006\u0006\n\u0004\b\u001e\u0010\u0017R\u0014\u0010\u001f\u001a\u00020\u00048\u0002X\u0082D¢\u0006\u0006\n\u0004\b\u001f\u0010\u0017R\u0014\u0010 \u001a\u00020\u00048\u0002X\u0082D¢\u0006\u0006\n\u0004\b \u0010\u0017¨\u0006$"}, d2 = {"Lcom/amazon/mp3/library/provider/source/cirrus/asinupdate/TracksToUpdateDAO;", "", "", "createTable", "", "newAsin", "oldAsin", "", "insertForAsinSubstitution$DigitalMusicAndroid3P_automotiveProdRelease", "(Ljava/lang/String;Ljava/lang/String;)J", "insertForAsinSubstitution", "downloadedASin", "", "deleteUpdateAsin$DigitalMusicAndroid3P_automotiveProdRelease", "(Ljava/lang/String;)I", "deleteUpdateAsin", "", "Landroid/util/Pair;", "getAllUpdatedAsin$DigitalMusicAndroid3P_automotiveProdRelease", "()Ljava/util/List;", "getAllUpdatedAsin", "kotlin.jvm.PlatformType", "TAG", "Ljava/lang/String;", "Landroid/database/sqlite/SQLiteDatabase;", "mDatabase", "Landroid/database/sqlite/SQLiteDatabase;", "getMDatabase", "()Landroid/database/sqlite/SQLiteDatabase;", "TABLE_NAME", "NEW_ASIN", "OLD_ASIN", "ID", "database", "<init>", "(Landroid/database/sqlite/SQLiteDatabase;)V", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final class TracksToUpdateDAO {
    private final String ID;
    private final String NEW_ASIN;
    private final String OLD_ASIN;
    private final String TABLE_NAME;
    private final String TAG;
    private final SQLiteDatabase mDatabase;

    public TracksToUpdateDAO(SQLiteDatabase database) {
        Intrinsics.checkNotNullParameter(database, "database");
        this.TAG = TracksToUpdateDAO.class.getSimpleName();
        this.mDatabase = database;
        this.TABLE_NAME = "TracksToUpdate";
        this.NEW_ASIN = "newasin";
        this.OLD_ASIN = "oldasin";
        this.ID = "_id";
    }

    public final void createTable() {
        this.mDatabase.beginTransaction();
        this.mDatabase.execSQL("CREATE TABLE IF NOT EXISTS " + this.TABLE_NAME + " ( " + this.ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + this.NEW_ASIN + " TEXT NOT NULL UNIQUE, " + this.OLD_ASIN + " TEXT NOT NULL UNIQUE );");
        this.mDatabase.setTransactionSuccessful();
        this.mDatabase.endTransaction();
    }

    public final long insertForAsinSubstitution$DigitalMusicAndroid3P_automotiveProdRelease(String newAsin, String oldAsin) {
        Intrinsics.checkNotNullParameter(newAsin, "newAsin");
        Intrinsics.checkNotNullParameter(oldAsin, "oldAsin");
        try {
            ContentValues contentValues = new ContentValues();
            contentValues.put(this.NEW_ASIN, newAsin);
            contentValues.put(this.OLD_ASIN, oldAsin);
            return this.mDatabase.insertOrThrow(this.TABLE_NAME, null, contentValues);
        } catch (SQLiteConstraintException e) {
            Log.error(this.TAG, "receive duplicate asin from service new asin : " + newAsin + " old asin : " + oldAsin, e);
            return -1L;
        }
    }

    public final int deleteUpdateAsin$DigitalMusicAndroid3P_automotiveProdRelease(String downloadedASin) {
        Intrinsics.checkNotNullParameter(downloadedASin, "downloadedASin");
        return this.mDatabase.delete(this.TABLE_NAME, Intrinsics.stringPlus(this.NEW_ASIN, "=?"), new String[]{downloadedASin});
    }

    public final List<Pair<String, String>> getAllUpdatedAsin$DigitalMusicAndroid3P_automotiveProdRelease() {
        Cursor cursorRawQuery = this.mDatabase.rawQuery(Intrinsics.stringPlus("Select *  from ", this.TABLE_NAME), null);
        ArrayList arrayList = new ArrayList();
        while (cursorRawQuery.moveToNext()) {
            try {
                arrayList.add(new Pair(cursorRawQuery.getString(cursorRawQuery.getColumnIndex(this.NEW_ASIN)), cursorRawQuery.getString(cursorRawQuery.getColumnIndex(this.OLD_ASIN))));
            } finally {
            }
        }
        Unit unit = Unit.INSTANCE;
        CloseableKt.closeFinally(cursorRawQuery, null);
        return arrayList;
    }
}
