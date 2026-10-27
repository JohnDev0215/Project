package com.amazon.mp3.library.provider.source.cirrus.dbutils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.amazon.mp3.library.provider.source.cirrus.CirrusDatabase;
import com.amazon.mp3.library.util.PlaylistUtil;
import com.amazon.mp3.library.util.TrackUtil;
import com.amazon.mp3.playlist.api.track.CatalogPlaylistServiceTrack;
import com.amazon.mp3.playlist.api.track.LibraryPlaylistServiceTrack;
import com.amazon.mp3.playlist.api.track.PlaylistServiceTrack;
import com.amazon.mp3.prime.ContentOwnershipStatus;
import com.amazon.mp3.util.DbUtil;
import com.amazon.mp3.util.Log;
import java.text.MessageFormat;
import java.util.Iterator;
import java.util.List;
import org.apache.commons.lang3.StringUtils;

/* JADX INFO: loaded from: classes3.dex */
public class PlaylistTrackUtil {
    private static final String TAG = "PlaylistTrackUtil";
    private final SQLiteDatabase mDb;

    public PlaylistTrackUtil(final SQLiteDatabase db) {
        this.mDb = db;
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x0029  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean doesPlaylistTrackTableContainUnmatchedLuids() {
        /*
            r9 = this;
            java.lang.String r0 = "track_luid"
            java.lang.String[] r3 = new java.lang.String[]{r0}
            android.database.sqlite.SQLiteDatabase r1 = r9.mDb
            java.lang.String r2 = "PlaylistTrack LEFT OUTER JOIN Track ON PlaylistTrack.track_luid = Track.luid"
            java.lang.String r4 = "Track.luid is NULL AND PlaylistTrack.track_luid LIKE 'prime-%'"
            r5 = 0
            r6 = 0
            r7 = 0
            r8 = 0
            android.database.Cursor r0 = r1.query(r2, r3, r4, r5, r6, r7, r8)
            if (r0 == 0) goto L29
            boolean r1 = r0.moveToNext()     // Catch: java.lang.Throwable -> L1f
            if (r1 == 0) goto L29
            r1 = 1
            goto L2a
        L1f:
            r1 = move-exception
            r0.close()     // Catch: java.lang.Throwable -> L24
            goto L28
        L24:
            r0 = move-exception
            r1.addSuppressed(r0)
        L28:
            throw r1
        L29:
            r1 = 0
        L2a:
            if (r0 == 0) goto L2f
            r0.close()
        L2f:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.mp3.library.provider.source.cirrus.dbutils.PlaylistTrackUtil.doesPlaylistTrackTableContainUnmatchedLuids():boolean");
    }

    public static boolean deleteDownloadedPlaylist(final Context context, final long playlistId) {
        SQLiteDatabase writableDatabase = CirrusDatabase.getWritableDatabase(context);
        try {
            writableDatabase.beginTransaction();
            if (PlaylistUtil.isLocalPlaylist(playlistId)) {
                Log.debug(TAG, "Deleted device-only playlist %d", Long.valueOf(playlistId));
                writableDatabase.delete("Playlist", "_id=?", new String[]{String.valueOf(playlistId)});
            } else {
                List<PlaylistServiceTrack> unifiedPlaylistTracks = PlaylistUtil.getUnifiedPlaylistTracks(context, playlistId);
                ContentValues contentValues = new ContentValues(1);
                contentValues.put("download_state", (Integer) 5);
                Log.debug(TAG, "Disunified playlist %s", Long.valueOf(playlistId));
                writableDatabase.update("Playlist", contentValues, "_id=?", new String[]{String.valueOf(playlistId)});
                if (!unifiedPlaylistTracks.isEmpty()) {
                    Iterator<PlaylistServiceTrack> it = unifiedPlaylistTracks.iterator();
                    while (it.hasNext()) {
                        TrackUtil.deleteUserPlaylistTrackFromDeviceIfPossible(context, it.next().getId());
                    }
                }
            }
            writableDatabase.setTransactionSuccessful();
            Log.debug(TAG, "Successfully deleted downloaded playlist");
            return true;
        } catch (Exception e) {
            Log.error(TAG, "Failed to delete downloaded playlist", e);
            return false;
        } finally {
            writableDatabase.endTransaction();
        }
    }

    public static boolean isTrackInAnyDownloadedUserPlaylists(final Context context, final String trackLuid) {
        String strFindObjectIdByTrackLocalLuid = TrackUtil.findObjectIdByTrackLocalLuid(context, trackLuid);
        if (StringUtils.isBlank(strFindObjectIdByTrackLocalLuid)) {
            return false;
        }
        SQLiteDatabase readOnlyDatabase = CirrusDatabase.getReadOnlyDatabase(context);
        String[] strArr = {"p._id"};
        String[] strArr2 = {String.valueOf(0), trackLuid, strFindObjectIdByTrackLocalLuid};
        Cursor cursorQuery = null;
        try {
            try {
                cursorQuery = readOnlyDatabase.query("Playlist p INNER JOIN PlaylistTrack pt ON p._id = pt.udo_playlist_id", strArr, "p.download_state = ? and (pt.track_luid = ? OR pt.track_luid = ?)", strArr2, "udo_playlist_id", null, null);
                if (cursorQuery != null && cursorQuery.getCount() > 0) {
                    Log.debug(TAG, "track %s exists in other downloaded playlists", trackLuid);
                    return true;
                }
            } catch (IllegalArgumentException e) {
                Log.error(TAG, MessageFormat.format("Unable to query playlists for track {0}", trackLuid), e);
            }
            Log.debug(TAG, "track %s doesn't exist in any downloaded playlists", trackLuid);
            return false;
        } finally {
            DbUtil.closeCursor(cursorQuery);
        }
    }

    public static PlaylistServiceTrack getDownloadedPlaylistServiceTrack(final Context context, final String asin) {
        int i;
        Cursor cursorQuery = CirrusDatabase.getReadOnlyDatabase(context).query("Track", new String[]{"Track.luid", "Track.ownership_status"}, "Track.source = ? and Track.asin = ?", new String[]{Integer.toString(0), asin}, null, null, null);
        try {
            if (cursorQuery != null) {
                if (cursorQuery.moveToNext()) {
                    int columnIndex = cursorQuery.getColumnIndex("ownership_status");
                    if ((columnIndex == -1 || (i = cursorQuery.getInt(columnIndex)) == ContentOwnershipStatus.UNKNOWN.getValue() || i >= 300) ? false : true) {
                        int columnIndex2 = cursorQuery.getColumnIndex("luid");
                        String string = columnIndex2 != -1 ? cursorQuery.getString(columnIndex2) : null;
                        Log.debug(TAG, "Track to be added to playlist: asin = %s, luid = %s", asin, string);
                        if (StringUtils.isNotBlank(string)) {
                            return string.startsWith("prime-") ? new CatalogPlaylistServiceTrack(asin) : new LibraryPlaylistServiceTrack(string);
                        }
                    }
                }
            }
            return new CatalogPlaylistServiceTrack(asin);
        } catch (Exception e) {
            Log.error(TAG, "failed to get track id for asin " + asin, e);
            return null;
        } finally {
            DbUtil.closeCursor(cursorQuery);
        }
    }
}
