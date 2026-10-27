package com.amazon.mp3.library.service.sync;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.download.ImageCacheUri;
import com.amazon.mp3.download.MusicDownloader;
import com.amazon.mp3.library.cache.image.loader.ImageLoaderFactory;
import com.amazon.mp3.library.item.Playlist;
import com.amazon.mp3.library.provider.MediaProvider;
import com.amazon.mp3.library.provider.source.cirrus.CirrusDatabase;
import com.amazon.mp3.library.provider.source.cirrus.CirrusMediaSource;
import com.amazon.mp3.library.util.PlaylistUtil;
import com.amazon.mp3.util.ConnectivityUtil;
import com.amazon.mp3.util.DbUtil;
import com.amazon.mp3.util.Log;
import com.amazon.mp3.util.UserSubscriptionUtil;
import com.amazon.music.downloads.notification.NotificationInfo;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes3.dex */
public class PlaylistUpdater {
    private static final String TAG = "PlaylistUpdater";
    private Context mContext;
    private JSONObject mPlaylist;
    private List<JSONArray> mTracks;

    public PlaylistUpdater(Context context, JSONObject playlist, List<JSONArray> tracks) {
        this.mContext = context.getApplicationContext();
        this.mPlaylist = playlist;
        this.mTracks = tracks;
    }

    public void update(final PlaylistDatabaseCallback callback, final PlaylistUnificationCallback unificationCallback, final boolean forceUpdate) throws Throwable {
        int iInsertOrUpdatePlaylist = 0;
        if (this.mPlaylist == null) {
            if (callback != null) {
                callback.onUpdated(0);
                return;
            }
            return;
        }
        try {
            Log.debug(TAG, "Call Playlist Updater");
            iInsertOrUpdatePlaylist = insertOrUpdatePlaylist(this.mContext, this.mPlaylist, this.mTracks, unificationCallback, forceUpdate);
        } catch (JSONException e) {
            Log.error(TAG, "Error parsing JSON to insert/update playlist updates", e);
            if (callback != null) {
                callback.onUpdated(0);
            }
        }
        if (iInsertOrUpdatePlaylist > 0) {
            this.mContext.getContentResolver().notifyChange(CirrusMediaSource.NOTIFICATION_URI, null);
        }
        if (callback != null) {
            callback.onUpdated(iInsertOrUpdatePlaylist);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:58:0x02c1  */
    /* JADX WARN: Removed duplicated region for block: B:62:0x02cc  */
    /* JADX WARN: Type inference failed for: r12v10 */
    /* JADX WARN: Type inference failed for: r12v11 */
    /* JADX WARN: Type inference failed for: r12v12 */
    /* JADX WARN: Type inference failed for: r12v13, types: [long] */
    /* JADX WARN: Type inference failed for: r12v14 */
    /* JADX WARN: Type inference failed for: r12v23 */
    /* JADX WARN: Type inference failed for: r12v24 */
    /* JADX WARN: Type inference failed for: r12v25 */
    /* JADX WARN: Type inference failed for: r12v26 */
    /* JADX WARN: Type inference failed for: r12v27 */
    /* JADX WARN: Type inference failed for: r12v29 */
    /* JADX WARN: Type inference failed for: r12v7, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r12v8 */
    /* JADX WARN: Type inference failed for: r12v9 */
    /* JADX WARN: Type inference failed for: r2v15, types: [android.database.Cursor] */
    /* JADX WARN: Type inference failed for: r2v16 */
    /* JADX WARN: Type inference failed for: r2v17 */
    /* JADX WARN: Type inference failed for: r2v18 */
    /* JADX WARN: Type inference failed for: r2v19 */
    /* JADX WARN: Type inference failed for: r2v20, types: [android.database.Cursor] */
    /* JADX WARN: Type inference failed for: r2v23, types: [android.database.Cursor] */
    /* JADX WARN: Type inference failed for: r2v27 */
    /* JADX WARN: Type inference failed for: r2v31 */
    /* JADX WARN: Type inference failed for: r2v32 */
    /* JADX WARN: Type inference failed for: r2v33 */
    /* JADX WARN: Type inference failed for: r2v34 */
    /* JADX WARN: Type inference failed for: r2v35 */
    /* JADX WARN: Type inference failed for: r2v36 */
    /* JADX WARN: Type inference failed for: r2v37 */
    /* JADX WARN: Type inference failed for: r32v0, types: [com.amazon.mp3.library.service.sync.PlaylistUnificationCallback] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:55:0x029c -> B:56:0x02b4). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static int insertOrUpdatePlaylist(android.content.Context r29, org.json.JSONObject r30, java.util.List<org.json.JSONArray> r31, com.amazon.mp3.library.service.sync.PlaylistUnificationCallback r32, boolean r33) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 907
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.mp3.library.service.sync.PlaylistUpdater.insertOrUpdatePlaylist(android.content.Context, org.json.JSONObject, java.util.List, com.amazon.mp3.library.service.sync.PlaylistUnificationCallback, boolean):int");
    }

    private static boolean samePlaylistVersion(final String previousVersion, final String currentVersion) {
        String[] strArrSplit = previousVersion.split(":", 2);
        String str = strArrSplit.length > 0 ? strArrSplit[0] : "0";
        String str2 = strArrSplit.length > 1 ? strArrSplit[1] : "0";
        String str3 = TAG;
        Log.info(str3, "Previous playlist version: %s : %s", str, str2);
        String[] strArrSplit2 = currentVersion.split(":", 2);
        String str4 = strArrSplit2.length > 0 ? strArrSplit2[0] : "0";
        String str5 = strArrSplit2.length > 1 ? strArrSplit2[1] : "0";
        Log.info(str3, "Current playlist version: %s : %s", str4, str5);
        return str.equals(str4) && str2.equals(str5);
    }

    private static int getPlaylistCurrentDownloadState(final Context context, final String luid) {
        Cursor cursorQuery = null;
        try {
            try {
                cursorQuery = CirrusDatabase.getReadOnlyDatabase(context).query("Playlist", new String[]{"download_state"}, "luid = ?", new String[]{luid}, null, null, null);
                if (cursorQuery != null && cursorQuery.moveToFirst()) {
                    int i = cursorQuery.getInt(cursorQuery.getColumnIndexOrThrow("download_state"));
                    Log.debug(TAG, "Playlist %s current download state = %s", luid, Integer.valueOf(i));
                    return i;
                }
            } catch (IllegalArgumentException e) {
                Log.error(TAG, MessageFormat.format("Unable to get playlist column {0}", "download_state"), e);
            }
            return 5;
        } finally {
            DbUtil.closeCursor(cursorQuery);
        }
    }

    private static int insertOrUpdateUdoPlaylistTracks(Context context, SQLiteDatabase db, List<JSONArray> tracks, long playlistId) {
        try {
            DbUtil.beginTransaction(db);
            db.delete("PlaylistTrack", "udo_playlist_id=?", new String[]{Long.toString(playlistId)});
            int iAppendUdoPlaylistTracks = appendUdoPlaylistTracks(context, db, tracks, playlistId);
            db.setTransactionSuccessful();
            return iAppendUdoPlaylistTracks;
        } finally {
            db.endTransaction();
        }
    }

    public static void downloadUpdatedUnifiedPlaylist(final Context context, final long playlistId, final String sourceId) {
        if ("cirrus-local".equals(sourceId)) {
            Log.debug(TAG, "Playlist [%s] is accessed in offline mode", Long.valueOf(playlistId));
            return;
        }
        Uri contentUri = MediaProvider.UdoPlaylists.getContentUri(sourceId, playlistId);
        Playlist playlist = AmazonApplication.getLibraryItemFactory().getPlaylist(contentUri);
        if ((!UserSubscriptionUtil.isNightwingOnly() || contentUri == null || playlist.isAllOwned()) ? false : AmazonApplication.getCapabilities().isFreeTierUserPlaylistEditingEnabled()) {
            Log.debug("cannot download playlist since shouldBlockPlaybackAndDownload is true for %s", contentUri.toString());
            return;
        }
        if (ConnectivityUtil.isWifiRequiredForDownloads(context) && !ConnectivityUtil.isWifiConnected(context)) {
            Log.warning("cannot download playlist [%s] due to download settings", contentUri.toString());
        }
        int downloadState = playlist.getDownloadState();
        Log.debug(TAG, "Checking unified playlist %d: current download state = %d", Long.valueOf(playlistId), Integer.valueOf(downloadState));
        PlaylistDownloadListener companion = PlaylistDownloadListener.INSTANCE.getInstance(context, playlist);
        if (PlaylistUtil.canDownloadUnifiedPlaylist(downloadState)) {
            if (downloadState != 0) {
                MusicDownloader.getInstance(context).registerDownloadListener(companion);
            } else {
                autoDownload(playlist);
            }
        }
    }

    public static void autoDownload(final Playlist playlist) {
        String id = playlist.getId();
        if (allCloudPlaylistTracksDownloaded(Long.valueOf(id).longValue())) {
            Log.debug(TAG, "All songs are downloaded in unified playlist %s", id);
            return;
        }
        NotificationInfo notificationInfo = new NotificationInfo(playlist.getName(), null, ImageCacheUri.generateUri(ImageLoaderFactory.ItemType.PLAYLIST_UDO.intValue(), id).toString());
        Uri contentUri = MediaProvider.UdoPlaylistTracks.getContentUri("cirrus", Long.valueOf(id).longValue());
        MusicDownloader.getInstance(AmazonApplication.getApplication()).download(contentUri.toString(), contentUri, notificationInfo, false, true);
        Log.debug(TAG, "Auto download unified playlist %s", id);
    }

    public static boolean allCloudPlaylistTracksDownloaded(final long playlistId) {
        return PlaylistUtil.allCloudPlaylistTracksDownloaded(MediaProvider.UdoPlaylistTracks.getContentUri("cirrus", playlistId));
    }

    /* JADX WARN: Removed duplicated region for block: B:37:0x0189  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x018f  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static int appendUdoPlaylistTracks(android.content.Context r28, android.database.sqlite.SQLiteDatabase r29, java.util.List<org.json.JSONArray> r30, long r31) {
        /*
            Method dump skipped, instruction units count: 746
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.mp3.library.service.sync.PlaylistUpdater.appendUdoPlaylistTracks(android.content.Context, android.database.sqlite.SQLiteDatabase, java.util.List, long):int");
    }

    private static List<ContentValues> tracksToContentValues(final List<JSONArray> tracks, final long playlistId) {
        String string;
        ArrayList arrayList = new ArrayList(tracks.isEmpty() ? 0 : tracks.get(0).length());
        int i = 0;
        for (JSONArray jSONArray : tracks) {
            for (int i2 = 0; i2 < jSONArray.length(); i2++) {
                try {
                    JSONObject jSONObject = jSONArray.getJSONObject(i2).getJSONObject("trackId");
                    if (jSONObject.has("objectId") || jSONObject.has("asin")) {
                        if (jSONObject.has("objectId")) {
                            string = jSONObject.getString("objectId");
                        } else {
                            string = jSONObject.getString("asin");
                        }
                        ContentValues contentValues = new ContentValues();
                        contentValues.put("track_luid", string);
                        int i3 = i + 1;
                        try {
                            contentValues.put("udo", Integer.valueOf(i));
                            contentValues.put("udo_playlist_id", Long.valueOf(playlistId));
                            arrayList.add(contentValues);
                            i = i3;
                        } catch (JSONException e) {
                            e = e;
                            i = i3;
                            Log.error(TAG, "Error parsing track JSON for a Playlist:", e);
                        }
                    }
                } catch (JSONException e2) {
                    e = e2;
                }
            }
        }
        return arrayList;
    }

    /* JADX WARN: Removed duplicated region for block: B:49:0x00f2  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x0285 A[Catch: JSONException -> 0x02a0, TRY_LEAVE, TryCatch #8 {JSONException -> 0x02a0, blocks: (B:56:0x027f, B:58:0x0285), top: B:115:0x027f }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static java.util.List<android.content.ContentValues> getCatalogTrackContentValues(android.content.Context r40, java.util.List<org.json.JSONArray> r41) {
        /*
            Method dump skipped, instruction units count: 801
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.mp3.library.service.sync.PlaylistUpdater.getCatalogTrackContentValues(android.content.Context, java.util.List):java.util.List");
    }
}
