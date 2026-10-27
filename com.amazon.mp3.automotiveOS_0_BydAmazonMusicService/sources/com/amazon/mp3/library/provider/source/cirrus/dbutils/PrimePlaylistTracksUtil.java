package com.amazon.mp3.library.provider.source.cirrus.dbutils;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import java.util.HashSet;
import java.util.Set;
import org.apache.commons.lang3.Validate;

/* JADX INFO: loaded from: classes3.dex */
public final class PrimePlaylistTracksUtil {
    public static int markPrimePlaylistTracksAsLocal(final SQLiteDatabase db, final String asin) {
        Validate.notNull(db);
        Validate.notBlank(asin);
        return updatePrimePlaylistTrackDownloadState(db, asin, 1, 0);
    }

    public static int markPrimePlaylistTracksAsCloud(final SQLiteDatabase db, final String asin) {
        Validate.notNull(db);
        Validate.notBlank(asin);
        return updatePrimePlaylistTrackDownloadState(db, asin, 0, 5);
    }

    private static int updatePrimePlaylistTrackDownloadState(final SQLiteDatabase db, final String asin, final int source, final int downloadState) {
        Validate.notNull(db);
        Validate.notBlank(asin);
        Validate.isTrue(source == 1 || source == 0);
        Validate.isTrue(downloadState == 0 || downloadState == 5);
        ContentValues contentValues = new ContentValues();
        contentValues.put("track_source", Integer.valueOf(source));
        contentValues.put("download_state", Integer.valueOf(downloadState));
        return db.update("PrimePlaylistTracks", contentValues, "asin =? ", new String[]{asin});
    }

    private static Cursor getDownloadedPrimePlaylistTracksWithoutLocalUri(final SQLiteDatabase db) {
        return db.query("PrimePlaylistTracks", new String[]{"PrimePlaylistTracks.asin"}, "download_state=? AND track_source=? AND asin NOT IN (select track_asin FROM LocalTrackUri WHERE track_luid IS NULL )", new String[]{String.valueOf(0), String.valueOf(1)}, null, null, null);
    }

    static Set<String> getDownloadedPrimePlaylistTracksAsinsWithoutLocalUri(final SQLiteDatabase db) {
        Validate.notNull(db);
        HashSet hashSet = new HashSet();
        Cursor downloadedPrimePlaylistTracksWithoutLocalUri = getDownloadedPrimePlaylistTracksWithoutLocalUri(db);
        try {
            if (downloadedPrimePlaylistTracksWithoutLocalUri.moveToFirst()) {
                while (!downloadedPrimePlaylistTracksWithoutLocalUri.isAfterLast()) {
                    hashSet.add(downloadedPrimePlaylistTracksWithoutLocalUri.getString(0));
                    downloadedPrimePlaylistTracksWithoutLocalUri.moveToNext();
                }
                downloadedPrimePlaylistTracksWithoutLocalUri.close();
                return hashSet;
            }
            downloadedPrimePlaylistTracksWithoutLocalUri.close();
            return hashSet;
        } catch (Throwable th) {
            if (downloadedPrimePlaylistTracksWithoutLocalUri != null) {
                try {
                    downloadedPrimePlaylistTracksWithoutLocalUri.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
            }
            throw th;
        }
    }
}
