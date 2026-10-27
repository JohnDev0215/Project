package com.amazon.mp3.library.provider.source.cirrus.dbvalueupdate;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.amazon.mp3.library.db.CursorSelector;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: DBUpdateUtil.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000b\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\r\u0010\u000eJ\u0010\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002H\u0002J\u000e\u0010\u0006\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002J\u000e\u0010\u0007\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002J\u000e\u0010\b\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002J\u000e\u0010\t\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002J\u000e\u0010\n\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002J\u000e\u0010\u000b\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002J\u000e\u0010\f\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002¨\u0006\u000f"}, d2 = {"Lcom/amazon/mp3/library/provider/source/cirrus/dbvalueupdate/DBUpdateUtil;", "", "Landroid/database/sqlite/SQLiteDatabase;", "db", "Landroid/database/Cursor;", "getPrimePlaylistCursorForDownloadedState", "getDownloadedDRMTracksCursor", "getNotDownloadedLocalTracksCursor", "getDownloadedPrimePlaylistTracksCursor", "getPreWidevineCloudTracksCursor", "getDownloadedPrimePlaylistCursor", "getPreWidevinePrimePlaylistTracksCursor", "getPrimePlaylistTracksCursorNotDownloadedAndNotInitated", "<init>", "()V", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final class DBUpdateUtil {
    public static final DBUpdateUtil INSTANCE = new DBUpdateUtil();

    private DBUpdateUtil() {
    }

    private final Cursor getPrimePlaylistCursorForDownloadedState(SQLiteDatabase db) {
        Cursor properCursor = CursorSelector.getProperCursor(db.rawQuery("Select * from PrimePlaylistTracks where download_state = 0 OR download_state = 3 OR download_state = 4", null));
        Intrinsics.checkNotNullExpressionValue(properCursor, "getProperCursor(cursor)");
        return properCursor;
    }

    public final Cursor getDownloadedDRMTracksCursor(SQLiteDatabase db) {
        Intrinsics.checkNotNullParameter(db, "db");
        Cursor properCursor = CursorSelector.getProperCursor(db.rawQuery("\n                SELECT *\n                FROM Track\n                WHERE (download_state = 0 OR download_state = 3 OR download_state = 4)\n                    AND source = 0\n                    AND NOT (ownership_status = '150')\n                 ", null));
        Intrinsics.checkNotNullExpressionValue(properCursor, "getProperCursor(cursor)");
        return properCursor;
    }

    public final Cursor getNotDownloadedLocalTracksCursor(SQLiteDatabase db) {
        Intrinsics.checkNotNullParameter(db, "db");
        Cursor properCursor = CursorSelector.getProperCursor(db.rawQuery("Select * from Track where download_state != 0 and source == 1", null));
        Intrinsics.checkNotNullExpressionValue(properCursor, "getProperCursor(cursor)");
        return properCursor;
    }

    public final Cursor getDownloadedPrimePlaylistTracksCursor(SQLiteDatabase db) {
        Intrinsics.checkNotNullParameter(db, "db");
        return getPrimePlaylistCursorForDownloadedState(db);
    }

    public final Cursor getPreWidevineCloudTracksCursor(SQLiteDatabase db) {
        Intrinsics.checkNotNullParameter(db, "db");
        Cursor properCursor = CursorSelector.getProperCursor(db.rawQuery("\n                SELECT *\n                FROM Track\n                WHERE download_state = 6\n                    AND source = 0\n                    AND NOT (ownership_status = '150')\n                 ", null));
        Intrinsics.checkNotNullExpressionValue(properCursor, "getProperCursor(cursor)");
        return properCursor;
    }

    public final Cursor getDownloadedPrimePlaylistCursor(SQLiteDatabase db) {
        Intrinsics.checkNotNullParameter(db, "db");
        Cursor properCursor = CursorSelector.getProperCursor(db.rawQuery("Select * from PrimePlaylists where download_state = 3 OR download_state = 4 ", null));
        Intrinsics.checkNotNullExpressionValue(properCursor, "getProperCursor(cursor)");
        return properCursor;
    }

    public final Cursor getPreWidevinePrimePlaylistTracksCursor(SQLiteDatabase db) {
        Intrinsics.checkNotNullParameter(db, "db");
        Cursor properCursor = CursorSelector.getProperCursor(db.rawQuery("\n            SELECT *\n            FROM PrimePlaylistTracks\n            WHERE download_state = 6\n             ", null));
        Intrinsics.checkNotNullExpressionValue(properCursor, "getProperCursor(cursor)");
        return properCursor;
    }

    public final Cursor getPrimePlaylistTracksCursorNotDownloadedAndNotInitated(SQLiteDatabase db) {
        Intrinsics.checkNotNullParameter(db, "db");
        Cursor properCursor = CursorSelector.getProperCursor(db.rawQuery("\n                SELECT *\n                FROM PrimePlaylistTracks\n                WHERE download_state != 0\n                    AND download_state != 5\n                 ", null));
        Intrinsics.checkNotNullExpressionValue(properCursor, "getProperCursor(cursor)");
        return properCursor;
    }
}
