package com.amazon.mp3.download.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.download.MusicDownloader;
import com.amazon.mp3.library.item.MusicTrack;
import com.amazon.mp3.library.item.Playlist;
import com.amazon.mp3.library.item.factory.CirrusSourceLibraryItemFactory;
import com.amazon.mp3.library.provider.MediaProvider;
import com.amazon.mp3.library.provider.ProviderSourceFactory;
import com.amazon.mp3.library.provider.source.cirrus.CirrusDatabase;
import com.amazon.mp3.library.provider.source.cirrus.CirrusMediaSource;
import com.amazon.mp3.library.provider.source.cirrus.CirrusPlaylistScratch;
import com.amazon.mp3.library.util.PlaylistUtil;
import com.amazon.mp3.library.util.TrackUtil;
import com.amazon.mp3.net.AbstractHttpClient;
import com.amazon.mp3.net.service.ServiceException;
import com.amazon.mp3.playlist.api.MC2PlaylistApi;
import com.amazon.mp3.playlist.api.track.LibraryPlaylistServiceTrack;
import com.amazon.mp3.playlist.api.track.PlaylistServiceTrack;
import com.amazon.mp3.prime.ContentOwnershipStatus;
import com.amazon.mp3.util.DbUtil;
import com.amazon.mp3.util.Log;
import com.amazon.music.find.utils.PageUriUtils;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public class UdoPlaylistDownloadHelper {
    private static final String TAG = "UdoPlaylistDownloadHelper";
    private final Context mContext;

    public UdoPlaylistDownloadHelper(Context context) {
        this.mContext = context.getApplicationContext();
    }

    public void updateDownloadState(Uri playlistUri, int downloadState) {
        SQLiteDatabase writableDatabase = CirrusDatabase.getWritableDatabase(this.mContext);
        ContentValues contentValues = new ContentValues(1);
        contentValues.put("download_state", Integer.valueOf(downloadState));
        long playlistId = PlaylistUtil.getPlaylistId(playlistUri);
        writableDatabase.update("Playlist", contentValues, "_id = ?", new String[]{String.valueOf(playlistId)});
        if (downloadState == 0) {
            updatePlaylistTrackInLocalTrackUri(writableDatabase, playlistId);
        }
    }

    private void updatePlaylistTrackInLocalTrackUri(final SQLiteDatabase db, final long playlistId) {
        MusicTrack cloudTrackForLuid;
        List<PlaylistServiceTrack> unifiedPlaylistTracks = PlaylistUtil.getUnifiedPlaylistTracks(this.mContext, playlistId);
        CirrusSourceLibraryItemFactory cirrusSourceLibraryItemFactory = new CirrusSourceLibraryItemFactory(this.mContext);
        for (PlaylistServiceTrack playlistServiceTrack : unifiedPlaylistTracks) {
            if ((playlistServiceTrack instanceof LibraryPlaylistServiceTrack) && (cloudTrackForLuid = cirrusSourceLibraryItemFactory.getCloudTrackForLuid(playlistServiceTrack.getId())) != null) {
                try {
                    Cursor cursorQuery = db.query("LocalTrackUri", new String[]{"track_luid", "track_asin", "local_uri"}, "track_luid=?", new String[]{cloudTrackForLuid.getAsin()}, null, null, null);
                    try {
                        if (cursorQuery.moveToFirst()) {
                            String string = cursorQuery.getString(cursorQuery.getColumnIndex("local_uri"));
                            ContentValues contentValues = new ContentValues(3);
                            contentValues.put("track_asin", cloudTrackForLuid.getAsin());
                            contentValues.put("track_luid", cloudTrackForLuid.getLuid());
                            contentValues.put("local_uri", string);
                            db.insertWithOnConflict("LocalTrackUri", null, contentValues, 5);
                        }
                        cursorQuery.close();
                    } catch (Throwable th) {
                        if (cursorQuery != null) {
                            try {
                                cursorQuery.close();
                            } catch (Throwable th2) {
                                th.addSuppressed(th2);
                            }
                        }
                        throw th;
                    }
                } catch (Exception e) {
                    Log.error(TAG, "failed to update playlist track in LocalTrackUri", e);
                }
            }
        }
    }

    public void createLocalPlaylist(final Uri playlistUri) {
        if (playlistUri == null) {
            return;
        }
        Playlist playlist = AmazonApplication.getLibraryItemFactory().getPlaylist(playlistUri);
        if (playlist != null && playlist.getDownloadState() == 0) {
            Log.debug("This playlist has been unified : %s", playlistUri.toString());
            return;
        }
        if (MediaProvider.UdoPlaylists.isUdoPlaylist(playlistUri)) {
            String playlistName = PlaylistUtil.getPlaylistName(this.mContext, playlistUri);
            boolean zCanRenamePlaylist = PlaylistUtil.canRenamePlaylist(this.mContext, playlistUri);
            if (playlistName != null) {
                insertLocalTracks(playlistUri, PlaylistUtil.createLocalPlaylist(this.mContext, "cirrus-local", playlistName, zCanRenamePlaylist, true));
                Log.debug(TAG, "Created local playlist %s for uri %s", playlistName, playlistUri);
            } else {
                Log.warning(TAG, "Failed to get playlist name for uri %s", playlistUri);
            }
        }
    }

    public Uri findLocalTrackByTrackFileLocationUri(final SQLiteDatabase readOnlyDb, final String trackLocalUri) {
        Cursor cursorQuery = readOnlyDb.query("LocalTrackUri INNER JOIN Track ON luid = track_luid", new String[]{"track_luid", PageUriUtils.SOURCE_QUERY_PARAM}, "local_uri =? ", new String[]{trackLocalUri}, null, null, null);
        if (cursorQuery != null) {
            try {
                if (cursorQuery.moveToFirst()) {
                    String string = cursorQuery.getString(0);
                    if (string == null) {
                        Log.debug(TAG, "Track luid is null for " + trackLocalUri);
                        cursorQuery.close();
                        return null;
                    }
                    Uri contentUri = MediaProvider.Tracks.getContentUri(MediaProvider.CirrusBaseColumns.Source.convertToSourceId(cursorQuery.getInt(1)), string);
                    cursorQuery.close();
                    return contentUri;
                }
            } catch (Throwable th) {
                if (cursorQuery != null) {
                    try {
                        cursorQuery.close();
                    } catch (Throwable th2) {
                        th.addSuppressed(th2);
                    }
                }
                throw th;
            }
        }
        Log.debug(TAG, "Track luid not found for " + trackLocalUri);
        if (cursorQuery != null) {
            cursorQuery.close();
        }
        return null;
    }

    private void insertLocalTracks(Uri remotePlaylistUri, Uri localPlaylistUri) {
        long playlistId = PlaylistUtil.getPlaylistId(localPlaylistUri);
        Cursor tracksCursor = getTracksCursor(PlaylistUtil.getPlaylistId(remotePlaylistUri));
        if (tracksCursor != null) {
            try {
                if (tracksCursor.getCount() > 0) {
                    insert(tracksCursor, localPlaylistUri, Long.valueOf(playlistId));
                    update(playlistId);
                }
            } finally {
                DbUtil.closeCursor(tracksCursor);
            }
        }
    }

    private Cursor getTracksCursor(long remotePlaylistId) {
        return CirrusDatabase.getReadOnlyDatabase(this.mContext).query("Playlist p INNER JOIN PlaylistTrack pt ON p._id = pt.udo_playlist_id INNER JOIN Track t ON pt.track_luid = t.luid INNER JOIN LocalTrackUri l1 on l1.track_luid = t.luid", new String[]{"l1.local_uri", "t.ownership_status", "t.asin"}, "t.source = ? and p._id = ?", new String[]{"0", String.valueOf(remotePlaylistId)}, null, null, null);
    }

    private boolean update(long localPlaylistId) {
        try {
            return new MC2PlaylistApi().updatePlaylist(this.mContext, MediaProvider.UdoPlaylists.getContentUri("cirrus-local", localPlaylistId));
        } catch (AbstractHttpClient.HttpClientException e) {
            Log.error(TAG, "Encountered HttpClientException while updating playlist", e);
            return false;
        } catch (ServiceException e2) {
            Log.error(TAG, "Encountered ServiceException while updating playlist", e2);
            return false;
        }
    }

    public int getDownloadState(String playlistId) {
        SQLiteDatabase readOnlyDatabase = CirrusDatabase.getReadOnlyDatabase(AmazonApplication.getContext());
        SQLiteQueryBuilder sQLiteQueryBuilder = new SQLiteQueryBuilder();
        sQLiteQueryBuilder.setTables("Playlist");
        Cursor cursorQuery = sQLiteQueryBuilder.query(readOnlyDatabase, new String[]{"download_state"}, "luid=? ", new String[]{playlistId}, null, null, null);
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

    private void insert(Cursor cursor, Uri localPlaylistUri, Long localPlaylistId) {
        int columnIndex = cursor.getColumnIndex("local_uri");
        int columnIndex2 = cursor.getColumnIndex("asin");
        int columnIndex3 = cursor.getColumnIndex("ownership_status");
        ArrayList arrayList = new ArrayList();
        CirrusMediaSource cirrusMediaSource = (CirrusMediaSource) ProviderSourceFactory.getInstance(this.mContext).getSource("cirrus-local");
        CirrusPlaylistScratch.create(this.mContext, localPlaylistId.longValue());
        int nextUdo = CirrusPlaylistScratch.getNextUdo(this.mContext, localPlaylistId.longValue());
        while (cursor.moveToNext()) {
            String string = cursor.getString(columnIndex);
            String string2 = cursor.getString(columnIndex2);
            boolean z = !ContentOwnershipStatus.fromValue(cursor.getInt(columnIndex3)).isOwned();
            if (!z) {
                string2 = string;
            }
            if (MusicDownloader.getInstance(this.mContext).isTrackAvailableOffline(string2, z)) {
                String localTrackLuidByLocalFileLocation = TrackUtil.getLocalTrackLuidByLocalFileLocation(CirrusDatabase.getReadOnlyDatabase(this.mContext), string);
                if (localTrackLuidByLocalFileLocation != null) {
                    ContentValues contentValues = new ContentValues();
                    contentValues.put("udo_playlist_id", localPlaylistId);
                    contentValues.put("track_luid", localTrackLuidByLocalFileLocation);
                    contentValues.put("udo", Integer.valueOf(nextUdo));
                    arrayList.add(contentValues);
                    nextUdo++;
                } else {
                    Log.debug(TAG, "Track luid is null for " + string);
                }
            }
            if (arrayList.size() > 100 || cursor.isLast() || cursor.isAfterLast()) {
                cirrusMediaSource.bulkInsert(localPlaylistUri, (ContentValues[]) arrayList.toArray(new ContentValues[arrayList.size()]));
                arrayList.clear();
            }
        }
    }
}
