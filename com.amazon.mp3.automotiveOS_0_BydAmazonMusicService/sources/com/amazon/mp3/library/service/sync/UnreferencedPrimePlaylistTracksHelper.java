package com.amazon.mp3.library.service.sync;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.amazon.mp3.download.MusicDownloader;
import com.amazon.mp3.external.api.uri.QuerySongByAsin;
import com.amazon.mp3.library.provider.source.cirrus.dbutils.LocalTrackUtil;
import com.amazon.mp3.library.provider.source.cirrus.dbutils.PrimePlaylistTracksUtil;
import com.amazon.mp3.prime.ContentCatalogStatus;
import com.amazon.mp3.prime.PrimePlaylistUtil;
import com.amazon.mp3.util.Log;
import com.amazon.music.find.utils.PageUriUtils;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/* JADX INFO: loaded from: classes3.dex */
public class UnreferencedPrimePlaylistTracksHelper {
    private static final String TAG = "UnreferencedPrimePlaylistTracksHelper";

    public void deleteUnreferencedPrimePlaylistTracks(final Context context, final SQLiteDatabase db) {
        if (PrimePlaylistUtil.hasUnfinishedPrimePlaylistDownloads(context)) {
            Log.verbose(TAG, "Ongoing prime playlist downloads found, return...");
            return;
        }
        db.beginTransaction();
        try {
            Map<String, UnreferrencedPrimePlaylistTrack> unreferencedTracks = getUnreferencedTracks(context, db);
            if (unreferencedTracks.isEmpty()) {
                Log.debug(TAG, "No unreferenced Prime Playlists tracks found.");
                return;
            }
            deleteLocalTracks(context, db, unreferencedTracks);
            markPrimePlaylistTrackAsNotDownloaded(db, unreferencedTracks);
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
        }
    }

    private void deleteLocalTracks(final Context context, final SQLiteDatabase db, final Map<String, UnreferrencedPrimePlaylistTrack> tracksToUpdate) {
        Iterator<Map.Entry<String, UnreferrencedPrimePlaylistTrack>> it = tracksToUpdate.entrySet().iterator();
        while (it.hasNext()) {
            deleteLocalTrack(context, db, it.next());
        }
    }

    private void markPrimePlaylistTrackAsNotDownloaded(final SQLiteDatabase db, final Map<String, UnreferrencedPrimePlaylistTrack> tracksToUpdate) {
        Iterator<String> it = tracksToUpdate.keySet().iterator();
        while (it.hasNext()) {
            PrimePlaylistTracksUtil.markPrimePlaylistTracksAsCloud(db, it.next());
        }
    }

    private void deleteLocalTrack(final Context context, final SQLiteDatabase db, final Map.Entry<String, UnreferrencedPrimePlaylistTrack> entry) {
        String key = entry.getKey();
        UnreferrencedPrimePlaylistTrack value = entry.getValue();
        String localUri = value.getLocalUri();
        boolean zIsPrime = value.isPrime();
        if (localUri != null) {
            if (!localUri.contains("PrimePlaylist") && !localUri.toLowerCase().contains("asin://")) {
                Log.debug(TAG, "found null luids in database with URI's that don't belong to /PrimePlaylist folder for track [%s | %s]", key, localUri);
            } else if (!zIsPrime) {
                Log.debug(TAG, "track [%s | %s] is not prime anymore", key, localUri);
            } else {
                new LocalTrackUtil(db).deleteTracksLocalUrisByUris(new String[]{localUri});
                deleteLocalTrackFile(context, key, localUri);
            }
        }
    }

    private void deleteLocalTrackFile(final Context context, final String trackAsin, final String localUri) {
        if (new QuerySongByAsin(context).findCollectionUriByAsin(trackAsin) != null) {
            return;
        }
        MusicDownloader.getInstance(context).removeDownload(trackAsin, null);
    }

    private Map<String, UnreferrencedPrimePlaylistTrack> getPrimePlaylistTracks(final SQLiteDatabase db, final int playlistSource) {
        HashMap map = new HashMap();
        Cursor primePlaylistTracksCursor = getPrimePlaylistTracksCursor(db, playlistSource);
        try {
            int columnIndex = primePlaylistTracksCursor.getColumnIndex("local_uri");
            int columnIndex2 = primePlaylistTracksCursor.getColumnIndex("asin");
            int columnIndex3 = primePlaylistTracksCursor.getColumnIndex("catalog_status");
            if (primePlaylistTracksCursor.moveToFirst()) {
                while (!primePlaylistTracksCursor.isAfterLast()) {
                    ContentCatalogStatus contentCatalogStatusFromValue = ContentCatalogStatus.fromValue(primePlaylistTracksCursor.getInt(columnIndex3));
                    String string = primePlaylistTracksCursor.getString(columnIndex2);
                    map.put(string, new UnreferrencedPrimePlaylistTrack(string, primePlaylistTracksCursor.getString(columnIndex), !contentCatalogStatusFromValue.isPreviouslyCatalog()));
                    primePlaylistTracksCursor.moveToNext();
                }
                primePlaylistTracksCursor.close();
                return map;
            }
            primePlaylistTracksCursor.close();
            return map;
        } catch (Throwable th) {
            if (primePlaylistTracksCursor != null) {
                try {
                    primePlaylistTracksCursor.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
            }
            throw th;
        }
    }

    private Cursor getPrimePlaylistTracksCursor(final SQLiteDatabase db, final int playlistSource) {
        return db.query("PrimePlaylistTracks LEFT JOIN LocalTrackUri ON PrimePlaylistTracks.asin=LocalTrackUri.track_asin LEFT JOIN PrimePlaylistToTrack ON PrimePlaylistToTrack.track_asin=PrimePlaylistTracks.asin LEFT JOIN PrimePlaylists ON PrimePlaylistToTrack.playlist_id=PrimePlaylists._id", new String[]{"PrimePlaylistTracks.asin", "track_source", "PrimePlaylistTracks.catalog_status", PageUriUtils.SOURCE_QUERY_PARAM, "local_uri"}, "source=? AND local_uri NOT NULL  AND track_luid IS NULL ", new String[]{String.valueOf(playlistSource)}, null, null, null);
    }

    private Map<String, UnreferrencedPrimePlaylistTrack> getUnreferencedTracks(final Context context, final SQLiteDatabase db) {
        Map<String, UnreferrencedPrimePlaylistTrack> primePlaylistTracks = getPrimePlaylistTracks(db, 1);
        Map<String, UnreferrencedPrimePlaylistTrack> primePlaylistTracks2 = getPrimePlaylistTracks(db, 0);
        HashMap map = new HashMap();
        for (Map.Entry<String, UnreferrencedPrimePlaylistTrack> entry : primePlaylistTracks2.entrySet()) {
            if (!primePlaylistTracks.containsKey(entry.getKey())) {
                map.put(entry.getKey(), entry.getValue());
                Log.verbose(TAG, "Track to update: " + entry);
            }
        }
        return map;
    }

    private static class UnreferrencedPrimePlaylistTrack {
        private final String mAsin;
        private final boolean mIsPrime;
        private final String mLocalUri;

        UnreferrencedPrimePlaylistTrack(String asin, String localUri, boolean isPrime) {
            this.mAsin = asin;
            this.mLocalUri = localUri;
            this.mIsPrime = isPrime;
        }

        String getLocalUri() {
            return this.mLocalUri;
        }

        boolean isPrime() {
            return this.mIsPrime;
        }

        public String toString() {
            return "UnreferrencedPrimePlaylistTrack{mAsin='" + this.mAsin + "', mLocalUri='" + this.mLocalUri + "', mIsPrime=" + this.mIsPrime + '}';
        }
    }
}
