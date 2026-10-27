package com.amazon.mp3.download.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.amazon.mp3.library.provider.source.cirrus.CirrusDatabase;
import com.amazon.mp3.library.provider.source.cirrus.CirrusMediaSource;
import com.amazon.mp3.library.provider.source.cirrus.dbutils.LocalTrackUtil;
import com.amazon.mp3.library.provider.source.cirrus.dbutils.PrimePlaylistTracksUtil;
import com.amazon.mp3.util.DbUtil;
import com.amazon.mp3.util.Log;
import com.amazon.music.destination.parser.ParserUtil;
import com.amazon.music.downloads.Item;
import com.amazon.music.externalstorage.StorageLocationFileManager;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes3.dex */
public class PrimePlaylistTrackDownloadHelper extends TrackDownloadHelper {
    private static final String[] PRIME_LOCAL_STORE_PROJECTION = {"_id", "asin", "title", "sort_title", "album", "album_asin", "duration", "album_artist", "artist", "artist_asin", "album_artist_asin", "track_num", "fileSizeKilobytes", "lyrics_state", ParserUtil.GENRE_SEGMENT_NAME, "duration", "catalog_status", "match_hash", "download_state", "album_art_url_large", "album_art_url_thumbnail", "artwork_id", "track_source"};
    private static final String TAG = "PrimePlaylistTrackDownloadHelper";

    public PrimePlaylistTrackDownloadHelper(final Context context) {
        super(context);
    }

    public PrimePlaylistTrackDownloadHelper(final Context context, StorageLocationFileManager storageLocationFileManager) {
        super(context, storageLocationFileManager);
    }

    @Override // com.amazon.mp3.download.helper.TrackDownloadHelper
    protected void saveTrack(SQLiteDatabase db, String luid, String asin, String destinationFile) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(asin);
        Cursor cursorQuery = null;
        try {
            try {
                DbUtil.WhereClause whereClause = new DbUtil.WhereClause("asin", arrayList);
                cursorQuery = db.query("PrimePlaylistTracks", PRIME_LOCAL_STORE_PROJECTION, whereClause.getClause(), whereClause.getArgs(), null, null, null);
            } catch (Exception e) {
                Log.error(TAG, "Error saving prime track to libary", e);
            }
            if (cursorQuery != null && cursorQuery.moveToFirst()) {
                Log.verbose(TAG, "Track [%s] is downloaded", asin);
                PrimePlaylistTracksUtil.markPrimePlaylistTracksAsLocal(db, asin);
                storePrimePlaylistTrackLocalUri(db, asin, destinationFile);
                return;
            }
            Log.warning(TAG, "Track [%s] is not found", asin);
        } finally {
            DbUtil.closeCursor(null);
        }
    }

    private void storePrimePlaylistTrackLocalUri(SQLiteDatabase db, String asin, String destinationFile) {
        if (new LocalTrackUtil(db).insertPrimePlaylistTrackLocalUri(asin, destinationFile) < 0) {
            Log.warning(TAG, "Database insert failed for: %s, %s", asin, destinationFile);
            return;
        }
        Log.debug(TAG, "Stored the local path in the local tracks table: " + destinationFile);
    }

    @Override // com.amazon.mp3.download.helper.TrackDownloadHelper
    protected void updateLocalTracksTable(SQLiteDatabase db, String requestId, Item item) {
        String asin = item.getAsin();
        String destinationUri = item.getDestinationUri();
        String str = TAG;
        Log.debug(str, "Storing the following in the local tracks table %s, %s", asin, destinationUri);
        if (asin == null || destinationUri == null) {
            Log.debug(str, "Could not insert local tracks row for: %s, %s", asin, destinationUri);
        } else {
            storePrimePlaylistTrackLocalUri(db, asin, destinationUri);
        }
    }

    @Override // com.amazon.mp3.download.helper.TrackDownloadHelper
    protected int updateTrackDownloadState(SQLiteDatabase db, int newState, Item item) {
        Log.debug(TAG, "Updating a subscription Track %s status to %s", item.getAsin(), Integer.valueOf(newState));
        ContentValues contentValues = new ContentValues();
        contentValues.put("download_state", Integer.valueOf(newState));
        int iUpdate = db.update("PrimePlaylistTracks", contentValues, "asin = ? ", new String[]{item.getAsin()});
        this.mContext.getContentResolver().notifyChange(CirrusMediaSource.NOTIFICATION_URI, null);
        return iUpdate;
    }

    @Override // com.amazon.mp3.download.helper.TrackDownloadHelper
    public boolean isTrackDownloaded(final String asin) {
        if (asin == null) {
            Log.error(TAG, "ASIN is null");
            return false;
        }
        int iIntFromCursor = DbUtil.intFromCursor(CirrusDatabase.getReadOnlyDatabase(this.mContext).query("PrimePlaylistTracks", new String[]{"COUNT(*)"}, "asin =? AND download_state =? AND track_source =? ", new String[]{asin, Integer.toString(0), Integer.toString(1)}, null, null, null));
        Log.verbose(TAG, "Found " + iIntFromCursor + " of downloads for " + asin);
        return iIntFromCursor > 0;
    }
}
