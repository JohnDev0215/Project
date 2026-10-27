package com.amazon.mp3.library.provider.source.cirrus;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteQueryBuilder;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.library.item.Playlist;
import com.amazon.mp3.library.provider.MediaProvider;
import com.amazon.mp3.library.service.sync.PlaylistUpdater;
import com.amazon.mp3.library.util.PlaylistUtil;
import com.amazon.mp3.library.util.TrackUtil;
import com.amazon.mp3.net.AbstractHttpClient;
import com.amazon.mp3.net.service.ServiceException;
import com.amazon.mp3.playlist.api.MC2PlaylistApi;
import com.amazon.mp3.util.DbUtil;
import com.amazon.mp3.util.Log;
import java.util.HashSet;
import java.util.Set;

/* JADX INFO: loaded from: classes3.dex */
public class CirrusPlaylistScratch {
    private static final String TAG = "CirrusPlaylistScratch";

    public static boolean post(Context context, String source, long playlistId) throws ServiceException, AbstractHttpClient.HttpClientException {
        return new MC2PlaylistApi().updatePlaylist(context, MediaProvider.UdoPlaylists.getContentUri(source, playlistId));
    }

    public static void postToDb(final Context context, final long playlistId, final String source) {
        postToDb(context, playlistId, source, true);
    }

    public static void postToDb(final Context context, final long playlistId, final String source, final boolean shouldStartAutoDownloadUnifiedPlaylist) {
        SQLiteDatabase writableDatabase = CirrusDatabase.getWritableDatabase(context);
        writableDatabase.beginTransaction();
        try {
            String[] strArr = {String.valueOf(playlistId)};
            if (PlaylistUtil.isUserPlaylistDownloaded(playlistId)) {
                for (String str : cleanupDownloadedPlaylistTracksFromDevice(context, playlistId)) {
                    TrackUtil.deleteUserPlaylistTrackFromDeviceIfPossible(context, str);
                    Log.debug(TAG, "Deleting playlist track, playlistId = {%s}, luid = {%s}", Long.valueOf(playlistId), str);
                }
            }
            writableDatabase.delete("PlaylistTrack", "udo_playlist_id=?", strArr);
            writableDatabase.execSQL("INSERT INTO PlaylistTrack (udo_playlist_id, track_luid, udo) SELECT udo_playlist_id, track_luid, udo FROM PlaylistTrackScratch WHERE udo_playlist_id=?", strArr);
            writableDatabase.setTransactionSuccessful();
            writableDatabase.endTransaction();
            boolean zIsUserPlaylistDownloaded = PlaylistUtil.isUserPlaylistDownloaded(playlistId);
            Playlist playlist = AmazonApplication.getLibraryItemFactory().getPlaylist(MediaProvider.UdoPlaylists.getContentUri("cirrus", playlistId));
            int downloadState = playlist == null ? 5 : playlist.getDownloadState();
            if (shouldStartAutoDownloadUnifiedPlaylist && "cirrus".equals(source) && PlaylistUtil.canDownloadUnifiedPlaylist(downloadState)) {
                Log.debug(TAG, "Auto download unified playlist if new tracks are being added to playlist %d, currently download state = %d", Long.valueOf(playlistId), Integer.valueOf(downloadState));
                PlaylistUpdater.downloadUpdatedUnifiedPlaylist(context, playlistId, source);
            } else {
                Log.debug(TAG, "success = %s, source = %s,  isUnified = %s, playlist %d", Boolean.TRUE, source, Boolean.valueOf(zIsUserPlaylistDownloaded), Long.valueOf(playlistId));
            }
        } catch (Throwable th) {
            writableDatabase.endTransaction();
            throw th;
        }
    }

    private static Set<String> cleanupDownloadedPlaylistTracksFromDevice(final Context context, final long playlistId) throws Throwable {
        Cursor cursorQuery;
        SQLiteDatabase readOnlyDatabase;
        HashSet hashSet = new HashSet();
        Cursor cursorQuery2 = null;
        try {
            readOnlyDatabase = CirrusDatabase.getReadOnlyDatabase(context);
            cursorQuery = readOnlyDatabase.query("PlaylistTrack", new String[]{"track_luid"}, "udo_playlist_id=? ", new String[]{String.valueOf(playlistId)}, null, null, null);
        } catch (Exception unused) {
            cursorQuery = null;
        } catch (Throwable th) {
            th = th;
            cursorQuery = null;
            DbUtil.closeCursor(cursorQuery2);
            DbUtil.closeCursor(cursorQuery);
            throw th;
        }
        try {
            try {
                HashSet<String> hashSet2 = new HashSet();
                if (cursorQuery != null) {
                    while (cursorQuery.moveToNext()) {
                        hashSet2.add(cursorQuery.getString(cursorQuery.getColumnIndex("track_luid")));
                    }
                }
                if (!hashSet2.isEmpty()) {
                    cursorQuery2 = readOnlyDatabase.query("PlaylistTrackScratch", new String[]{"track_luid"}, "udo_playlist_id=? ", new String[]{String.valueOf(playlistId)}, null, null, null);
                    HashSet hashSet3 = new HashSet();
                    if (cursorQuery2 != null) {
                        while (cursorQuery2.moveToNext()) {
                            hashSet3.add(cursorQuery2.getString(cursorQuery2.getColumnIndex("track_luid")));
                        }
                    }
                    for (String str : hashSet2) {
                        if (!hashSet3.contains(str)) {
                            hashSet.add(str);
                        }
                    }
                }
            } catch (Throwable th2) {
                th = th2;
                DbUtil.closeCursor(cursorQuery2);
                DbUtil.closeCursor(cursorQuery);
                throw th;
            }
        } catch (Exception unused2) {
            Log.debug(TAG, "Error occurred when deleting playlist tracks for playlistId = {%s}", Long.valueOf(playlistId));
        }
        DbUtil.closeCursor(cursorQuery2);
        DbUtil.closeCursor(cursorQuery);
        return hashSet;
    }

    public static void removeAll(SQLiteDatabase db) {
        if (db != null) {
            try {
                if (db.isOpen()) {
                    db.delete("PlaylistTrackScratch", null, null);
                }
            } catch (SQLiteException unused) {
                Log.warning(TAG, "An error occured while trying to remove the scratch table.");
            }
        }
    }

    public static void create(Context context, long playlistId) {
        if (context == null) {
            return;
        }
        delete(context, playlistId);
        CirrusDatabase.getWritableDatabase(context).execSQL("INSERT INTO PlaylistTrackScratch (udo_playlist_id, track_luid, udo) SELECT udo_playlist_id, track_luid, udo FROM PlaylistTrack WHERE udo_playlist_id=?", new String[]{String.valueOf(playlistId)});
        context.getContentResolver().notifyChange(CirrusMediaSource.NOTIFICATION_URI, null);
        context.getContentResolver().notifyChange(CirrusMediaSource.NOTIFICATION_URI_LOCAL, null);
    }

    public static int getPlaylistCount(Context context, long playlistId) {
        SQLiteQueryBuilder sQLiteQueryBuilder = new SQLiteQueryBuilder();
        sQLiteQueryBuilder.setTables("PlaylistTrackScratch");
        Cursor cursorQuery = sQLiteQueryBuilder.query(CirrusDatabase.getWritableDatabase(context), new String[]{"udo"}, "udo_playlist_id=?", new String[]{String.valueOf(playlistId)}, null, null, null, "1");
        int count = cursorQuery.getCount();
        DbUtil.closeCursor(cursorQuery);
        return count;
    }

    public static int getNextUdo(Context context, long playlistId) {
        SQLiteQueryBuilder sQLiteQueryBuilder = new SQLiteQueryBuilder();
        sQLiteQueryBuilder.setTables("PlaylistTrackScratch");
        Cursor cursorQuery = sQLiteQueryBuilder.query(CirrusDatabase.getWritableDatabase(context), new String[]{"udo"}, "udo_playlist_id=?", new String[]{String.valueOf(playlistId)}, null, null, "udo DESC", "1");
        try {
            int iIntFromCursor = DbUtil.intFromCursor(cursorQuery);
            if (iIntFromCursor >= 0) {
                return iIntFromCursor + 1;
            }
            return 0;
        } finally {
            DbUtil.closeCursor(cursorQuery);
        }
    }

    public static void delete(Context context, long playlistId) {
        CirrusDatabase.getWritableDatabase(context).delete("PlaylistTrackScratch", "udo_playlist_id=?", new String[]{String.valueOf(playlistId)});
    }

    public static boolean moveItem(Context context, long playlistId, int from, int to) {
        if (from == to) {
            return true;
        }
        SQLiteDatabase writableDatabase = CirrusDatabase.getWritableDatabase(context);
        DbUtil.beginTransaction(writableDatabase);
        try {
            writableDatabase.execSQL(" UPDATE PlaylistTrackScratch SET udo=? WHERE udo=? AND udo_playlist_id=?", new String[]{"-1", String.valueOf(from), String.valueOf(playlistId)});
            if (from < to) {
                writableDatabase.execSQL(" UPDATE PlaylistTrackScratch SET udo=udo-1 WHERE udo<=? AND udo>? AND udo_playlist_id=?", new String[]{String.valueOf(to), String.valueOf(from), String.valueOf(playlistId)});
            } else {
                writableDatabase.execSQL(" UPDATE PlaylistTrackScratch SET udo=udo+1 WHERE udo>=? AND udo<? AND udo_playlist_id=?", new String[]{String.valueOf(to), String.valueOf(from), String.valueOf(playlistId)});
            }
            writableDatabase.execSQL(" UPDATE PlaylistTrackScratch SET udo=? WHERE udo=? AND udo_playlist_id=?", new String[]{String.valueOf(to), "-1", String.valueOf(playlistId)});
            writableDatabase.setTransactionSuccessful();
            writableDatabase.endTransaction();
            context.getContentResolver().notifyChange(CirrusMediaSource.NOTIFICATION_URI, null);
            context.getContentResolver().notifyChange(CirrusMediaSource.NOTIFICATION_URI_LOCAL, null);
            return true;
        } catch (Throwable th) {
            writableDatabase.endTransaction();
            throw th;
        }
    }
}
