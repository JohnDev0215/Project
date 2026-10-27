package com.amazon.mp3.download.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.text.TextUtils;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.activity.settings.SettingsUtil;
import com.amazon.mp3.download.MusicDownloader;
import com.amazon.mp3.library.cache.image.CacheManager;
import com.amazon.mp3.library.cache.image.loader.ImageLoaderFactory;
import com.amazon.mp3.library.provider.MediaProvider;
import com.amazon.mp3.library.provider.ProviderSourceFactory;
import com.amazon.mp3.library.provider.source.cirrus.CirrusDatabase;
import com.amazon.mp3.library.provider.source.cirrus.CirrusMediaSource;
import com.amazon.mp3.playlist.CatalogPlaylist;
import com.amazon.mp3.playlist.db.PrimePlaylistDatabaseManager;
import com.amazon.mp3.prime.PrimePlaylistUtil;
import com.amazon.mp3.prime.PrimePlaylistsTable;
import com.amazon.mp3.util.DbUtil;
import com.amazon.mp3.util.Log;
import com.amazon.mp3.util.ScreenUtil;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes3.dex */
public class PrimePlaylistDownloadHelper {
    private static final String[] DEFAULT_PROJECTION = {"PrimePlaylistTracks.asin", "PrimePlaylistTracks.download_state"};
    private static final String[] DEFAULT_SELECTION_ARGS = {Integer.toString(400), Integer.toString(PrimePlaylistsTable.translateSource("cirrus"))};
    private static final String TAG = "PrimePlaylistDownloadHelper";
    private final Context mContext;

    public PrimePlaylistDownloadHelper(Context context) {
        this.mContext = context.getApplicationContext();
    }

    public void createLocalPlaylist(Uri playlistUri) {
        String catalogPlaylistId = MediaProvider.Playlists.getCatalogPlaylistId(playlistUri);
        CatalogPlaylist playlist = new PrimePlaylistDatabaseManager().getPlaylist(catalogPlaylistId, PrimePlaylistsTable.translateSource("cirrus"));
        if (playlist != null && playlist.isFollowed()) {
            insertLocalTracks(catalogPlaylistId, PrimePlaylistUtil.createOrUpdateLocalPrimePlaylist(this.mContext, playlistUri));
            CacheManager.getInstance().get(ImageLoaderFactory.ItemType.PRIME_PLAYLIST_BANNER, "cirrus-local", ScreenUtil.getDeviceWidth(), catalogPlaylistId);
            CacheManager.getInstance().get(ImageLoaderFactory.ItemType.PRIME_PLAYLIST_THUMBNAIL, "cirrus-local", CacheManager.GRID_VIEW_IMAGE_SIZE, catalogPlaylistId);
        } else {
            if (playlist == null) {
                Log.error(TAG, "failed to create local playlist because playlist is null: " + playlistUri);
                return;
            }
            if (playlist.isFollowed()) {
                return;
            }
            Log.error(TAG, "failed to create local playlist because playlist is not followed: " + playlistUri);
        }
    }

    public void setPendingTracksToFailed(Uri playlistUri) {
        SQLiteDatabase readOnlyDatabase = CirrusDatabase.getReadOnlyDatabase(AmazonApplication.getContext());
        SQLiteQueryBuilder sQLiteQueryBuilder = new SQLiteQueryBuilder();
        sQLiteQueryBuilder.setTables("PrimePlaylists LEFT JOIN PrimePlaylistToTrack ON PrimePlaylists._id=PrimePlaylistToTrack.playlist_id LEFT JOIN PrimePlaylistTracks ON  PrimePlaylistToTrack.track_asin=PrimePlaylistTracks.asin");
        Cursor cursorQuery = sQLiteQueryBuilder.query(readOnlyDatabase, DEFAULT_PROJECTION, new StringBuilder(DbUtil.applyBinaryOperator("PrimePlaylistTracks.download_state NOT IN (" + Integer.toString(0) + ", " + Integer.toString(2) + ')', "AND", "PrimePlaylists.asin = ? AND PrimePlaylistTracks.catalog_status < ? AND source = ?")).toString(), DbUtil.mergeColumnArrays(new String[]{MediaProvider.PrimePlaylists.getPlaylistAsin(playlistUri)}, DEFAULT_SELECTION_ARGS), null, null, null);
        if (cursorQuery == null) {
            return;
        }
        try {
            StringBuilder sb = new StringBuilder();
            sb.append("PrimePlaylistTracks");
            sb.append('.');
            sb.append("asin IN (");
            int columnIndex = cursorQuery.getColumnIndex("asin");
            int columnIndex2 = cursorQuery.getColumnIndex("download_state");
            while (cursorQuery.moveToNext()) {
                sb.append('\'');
                sb.append(cursorQuery.getString(columnIndex));
                sb.append('\'');
                if (!cursorQuery.isLast()) {
                    sb.append(", ");
                }
                Log.verbose(TAG, "reset download state: asin = %s, downloadstate = %d", cursorQuery.getString(columnIndex), Integer.valueOf(cursorQuery.getInt(columnIndex2)));
            }
            sb.append(')');
            ContentValues contentValues = new ContentValues();
            contentValues.put("download_state", (Integer) 2);
            readOnlyDatabase.update("PrimePlaylistTracks", contentValues, sb.toString(), null);
        } finally {
            DbUtil.closeCursor(cursorQuery);
        }
    }

    public void updateDownloadState(Uri playlistUri, int downloadState) {
        if (downloadState == 0 && !allTracksDownloaded(playlistUri)) {
            downloadState = 5;
        }
        SQLiteDatabase writableDatabase = CirrusDatabase.getWritableDatabase(this.mContext);
        ContentValues contentValues = new ContentValues(1);
        contentValues.put("download_state", Integer.valueOf(downloadState));
        writableDatabase.update("PrimePlaylists", contentValues, "asin = ?", new String[]{MediaProvider.Playlists.getCatalogPlaylistId(playlistUri)});
    }

    public int getDownloadState(String playlistAsin) {
        SQLiteDatabase readOnlyDatabase = CirrusDatabase.getReadOnlyDatabase(AmazonApplication.getContext());
        SQLiteQueryBuilder sQLiteQueryBuilder = new SQLiteQueryBuilder();
        sQLiteQueryBuilder.setTables("PrimePlaylists");
        Cursor cursorQuery = sQLiteQueryBuilder.query(readOnlyDatabase, new String[]{"download_state"}, "asin=? ", new String[]{playlistAsin}, null, null, null);
        if (cursorQuery == null) {
            return 5;
        }
        try {
            if (cursorQuery.moveToFirst()) {
                return cursorQuery.getInt(cursorQuery.getColumnIndex("download_state"));
            }
            return 5;
        } finally {
            DbUtil.closeCursor(cursorQuery);
        }
    }

    private boolean allTracksDownloaded(Uri playlistUri) {
        SQLiteDatabase readOnlyDatabase = CirrusDatabase.getReadOnlyDatabase(AmazonApplication.getContext());
        SQLiteQueryBuilder sQLiteQueryBuilder = new SQLiteQueryBuilder();
        sQLiteQueryBuilder.setTables("PrimePlaylists LEFT JOIN PrimePlaylistToTrack ON PrimePlaylists._id=PrimePlaylistToTrack.playlist_id LEFT JOIN PrimePlaylistTracks ON  PrimePlaylistToTrack.track_asin=PrimePlaylistTracks.asin");
        Cursor cursorQuery = sQLiteQueryBuilder.query(readOnlyDatabase, DEFAULT_PROJECTION, "PrimePlaylists.asin = ? AND PrimePlaylistTracks.catalog_status < ? AND source = ?", DbUtil.mergeColumnArrays(new String[]{MediaProvider.PrimePlaylists.getPlaylistAsin(playlistUri)}, DEFAULT_SELECTION_ARGS), null, null, "PrimePlaylistTracks.download_state DESC");
        if (cursorQuery == null) {
            return false;
        }
        try {
            int columnIndex = cursorQuery.getColumnIndex("download_state");
            while (cursorQuery.moveToNext()) {
                if (cursorQuery.getInt(columnIndex) != 0) {
                    return false;
                }
            }
            return true;
        } finally {
            DbUtil.closeCursor(cursorQuery);
        }
    }

    protected void insertLocalTracks(String playlistId, Uri localPlaylistUri) {
        long playlistId2 = new PrimePlaylistDatabaseManager().getPlaylistId(playlistId, PrimePlaylistsTable.translateSource("cirrus-local"));
        long playlistId3 = new PrimePlaylistDatabaseManager().getPlaylistId(playlistId, PrimePlaylistsTable.translateSource("cirrus"));
        deleteExistingTrackOrder(playlistId2);
        Cursor tracksCursor = getTracksCursor(playlistId3);
        if (tracksCursor != null) {
            try {
                if (tracksCursor.getCount() > 0) {
                    insert(tracksCursor, localPlaylistUri, Long.valueOf(playlistId2));
                }
            } catch (Throwable th) {
                DbUtil.closeCursor(tracksCursor);
                throw th;
            }
        }
        DbUtil.closeCursor(tracksCursor);
        save(playlistId2);
    }

    private void deleteExistingTrackOrder(final long localPlaylistId) {
        String str = TAG;
        Log.debug(str, "Delete existing track order for playlist " + localPlaylistId);
        Log.debug(str, "Rows deleted:" + CirrusDatabase.getWritableDatabase(this.mContext).delete("PrimePlaylistToTrack", "playlist_id=?", new String[]{Long.toString(localPlaylistId)}));
    }

    protected Cursor getTracksCursor(long remotePlaylistId) {
        return CirrusDatabase.getReadOnlyDatabase(this.mContext).query("PrimePlaylists p INNER JOIN PrimePlaylistToTrack pt ON p._id = pt.playlist_id INNER JOIN PrimePlaylistTracks t ON pt.track_asin = t.asin INNER JOIN LocalTrackUri l on l.track_asin = t.asin", new String[]{"local_uri", "t.duration", "t.asin", "t.track_source", "pt.order_num"}, "p._id = ? ", new String[]{String.valueOf(remotePlaylistId)}, null, null, null);
    }

    protected void insert(Cursor cursor, Uri localPlaylistUri, Long localPlaylistId) {
        int i;
        int columnIndex = cursor.getColumnIndex("asin");
        int columnIndex2 = cursor.getColumnIndex("duration");
        int columnIndex3 = cursor.getColumnIndex("order_num");
        ArrayList arrayList = new ArrayList();
        CirrusMediaSource cirrusMediaSource = (CirrusMediaSource) ProviderSourceFactory.getInstance(this.mContext).getSource("cirrus-local");
        long j = 0;
        int i2 = 0;
        while (cursor.moveToNext()) {
            String string = cursor.getString(columnIndex);
            int i3 = cursor.getInt(columnIndex3);
            boolean zIsTrackAvailableOffline = MusicDownloader.getInstance(this.mContext).isTrackAvailableOffline(string, true);
            if (TextUtils.isEmpty(string) || !zIsTrackAvailableOffline) {
                i = columnIndex;
            } else {
                ContentValues contentValues = new ContentValues();
                i = columnIndex;
                contentValues.put("playlist_id", localPlaylistId);
                contentValues.put("track_asin", string);
                contentValues.put("order_num", Integer.valueOf(i3));
                j += cursor.getLong(columnIndex2);
                arrayList.add(contentValues);
                i2++;
            }
            if (arrayList.size() > 100 || cursor.isLast() || cursor.isAfterLast()) {
                cirrusMediaSource.bulkInsert(localPlaylistUri, (ContentValues[]) arrayList.toArray(new ContentValues[arrayList.size()]));
                arrayList.clear();
            }
            columnIndex = i;
        }
        if (i2 > 0) {
            ContentValues contentValues2 = new ContentValues();
            contentValues2.put("track_count", Integer.valueOf(i2));
            contentValues2.put("duration", Long.valueOf(j));
            CirrusDatabase.getWritableDatabase(this.mContext).update("PrimePlaylists", contentValues2, "_id=?", new String[]{Long.toString(localPlaylistId.longValue())});
            Log.debug(TAG, "Inserted tracks for Local Playlist: " + localPlaylistUri);
            return;
        }
        Log.debug(TAG, "No tracks were inserted for Local Playlist: " + localPlaylistUri);
        CirrusDatabase.getWritableDatabase(this.mContext).delete("PrimePlaylists", "_id=?", new String[]{Long.toString(localPlaylistId.longValue())});
    }

    protected void save(long localPlaylistId) {
        SettingsUtil.setHasOfflinePrimePlaylists(this.mContext, true);
    }
}
