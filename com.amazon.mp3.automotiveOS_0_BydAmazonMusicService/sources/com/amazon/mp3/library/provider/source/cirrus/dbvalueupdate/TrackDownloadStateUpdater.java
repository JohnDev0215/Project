package com.amazon.mp3.library.provider.source.cirrus.dbvalueupdate;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import com.amazon.mp3.library.item.MusicTrack;
import com.amazon.mp3.util.Log;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: TrackDownloadStateUpdater.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001e\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u0007J\u001e\u0010\r\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\u000e\u001a\u00020\u00042\u0006\u0010\f\u001a\u00020\u0007J\u001e\u0010\u000f\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\u000e\u001a\u00020\u00042\u0006\u0010\f\u001a\u00020\u0007R\u0016\u0010\u0003\u001a\n \u0005*\u0004\u0018\u00010\u00040\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0010"}, d2 = {"Lcom/amazon/mp3/library/provider/source/cirrus/dbvalueupdate/TrackDownloadStateUpdater;", "", "()V", "TAG", "", "kotlin.jvm.PlatformType", "update", "", "db", "Landroid/database/sqlite/SQLiteDatabase;", "track", "Lcom/amazon/mp3/library/item/MusicTrack;", "newState", "updatePrimePlaylist", "asin", "updatePrimePlaylistTrack", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class TrackDownloadStateUpdater {
    public static final TrackDownloadStateUpdater INSTANCE = new TrackDownloadStateUpdater();
    private static final String TAG = TrackDownloadStateUpdater.class.getSimpleName();

    private TrackDownloadStateUpdater() {
    }

    public final int update(SQLiteDatabase db, MusicTrack track, int newState) {
        String[] strArr;
        String str;
        Intrinsics.checkNotNullParameter(db, "db");
        Intrinsics.checkNotNullParameter(track, "track");
        Log.debug(TAG, "Updating the Track with ASIN " + track + ".asin, luid " + track + ".luid to status " + newState);
        ContentValues contentValues = new ContentValues();
        contentValues.put("download_state", Integer.valueOf(newState));
        if (track.isAllOwned() && track.getLuid() != null) {
            strArr = new String[]{track.getLuid().toString()};
            str = "luid = ?";
        } else {
            strArr = new String[]{track.getAsin().toString()};
            str = "asin = ?";
        }
        return db.update("Track", contentValues, str, strArr);
    }

    public final int updatePrimePlaylistTrack(SQLiteDatabase db, String asin, int newState) {
        Intrinsics.checkNotNullParameter(db, "db");
        Intrinsics.checkNotNullParameter(asin, "asin");
        Log.debug(TAG, "Updating Prime Playlist Track with ASIN " + asin + " to status " + newState);
        ContentValues contentValues = new ContentValues();
        contentValues.put("download_state", Integer.valueOf(newState));
        return db.update("PrimePlaylistTracks", contentValues, "asin = ?", new String[]{asin});
    }

    public final int updatePrimePlaylist(SQLiteDatabase db, String asin, int newState) {
        Intrinsics.checkNotNullParameter(db, "db");
        Intrinsics.checkNotNullParameter(asin, "asin");
        Log.debug(TAG, "Updating Prime Playlist Track with ASIN " + asin + " to status " + newState);
        ContentValues contentValues = new ContentValues();
        contentValues.put("download_state", Integer.valueOf(newState));
        return db.update("PrimePlaylists", contentValues, "asin = ?", new String[]{asin});
    }
}
