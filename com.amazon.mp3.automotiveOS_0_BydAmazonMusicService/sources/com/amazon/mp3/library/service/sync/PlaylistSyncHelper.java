package com.amazon.mp3.library.service.sync;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import com.amazon.cirrus.libraryservice.v3.type.SelectableAttribute;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.library.item.MusicTrack;
import com.amazon.mp3.library.item.Playlist;
import com.amazon.mp3.library.provider.MediaProvider;
import com.amazon.mp3.library.provider.source.cirrus.CirrusDatabase;
import com.amazon.mp3.library.util.EditPlaylistMetricsLogger;
import com.amazon.mp3.library.util.PlaylistUtil;
import com.amazon.mp3.net.AbstractHttpClient;
import com.amazon.mp3.net.playlist.MusicPlaylistRequest;
import com.amazon.mp3.net.service.ServiceException;
import com.amazon.mp3.playlist.api.MC2PlaylistApi;
import com.amazon.mp3.playlist.api.track.PlaylistServiceTrack;
import com.amazon.mp3.playlist.db.PrimePlaylistDatabaseManager;
import com.amazon.mp3.prime.PrimePlaylistUtil;
import com.amazon.mp3.util.ChildUserUtil;
import com.amazon.mp3.util.ConnectivityUtil;
import com.amazon.mp3.util.DbUtil;
import com.amazon.mp3.util.IdGenerator;
import com.amazon.mp3.util.Log;
import com.amazon.mp3.util.UserSubscriptionUtil;
import com.amazon.music.curate.skyfire.bootstrap.ContextMappingConstants;
import com.amazon.music.media.playback.util.ThreadUtils;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes3.dex */
public class PlaylistSyncHelper {
    private static final String TAG = "PlaylistSyncHelper";
    private static PlaylistDatabaseCallback playlistDatabaseCallback;

    public static void syncDeletedPlaylists(Context context, Set<String> playlistIds) {
        PrimePlaylistDatabaseManager primePlaylistDatabaseManager = new PrimePlaylistDatabaseManager(context);
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        for (String str : playlistIds) {
            long playlistIdByCloudId = primePlaylistDatabaseManager.getPlaylistIdByCloudId(str);
            if (playlistIdByCloudId != -1) {
                primePlaylistDatabaseManager.delete(playlistIdByCloudId);
                long playlistIdByCloudId2 = primePlaylistDatabaseManager.getPlaylistIdByCloudId("localPrimePlaylist-" + str);
                if (playlistIdByCloudId2 != -1) {
                    primePlaylistDatabaseManager.delete(playlistIdByCloudId2);
                }
            } else {
                arrayList.add(str);
                arrayList2.add(String.valueOf(IdGenerator.generatePlaylistId(str)));
            }
        }
        if (arrayList.isEmpty()) {
            return;
        }
        SQLiteDatabase writableDatabase = CirrusDatabase.getWritableDatabase(context);
        writableDatabase.beginTransaction();
        try {
            new EditPlaylistMetricsLogger(context).sendCloudAndOfflinePlaylistEdited();
            DbUtil.WhereClause whereClause = new DbUtil.WhereClause("luid", new ArrayList(arrayList));
            writableDatabase.delete("Playlist", whereClause.getClause(), whereClause.getArgs());
            DbUtil.WhereClause whereClause2 = new DbUtil.WhereClause("udo_playlist_id", new ArrayList(arrayList2));
            writableDatabase.delete("PlaylistTrack", whereClause2.getClause(), whereClause2.getArgs());
            DbUtil.WhereClause whereClause3 = new DbUtil.WhereClause("udo_playlist_id", new ArrayList(arrayList2));
            writableDatabase.delete("PlaylistTrackScratch", whereClause3.getClause(), whereClause3.getArgs());
            writableDatabase.setTransactionSuccessful();
        } finally {
            writableDatabase.endTransaction();
        }
    }

    public static void syncUpdatedPlaylist(final Context context, final Set<String> playlistIds, final boolean forceUpdate) throws JSONException, ServiceException, AbstractHttpClient.HttpClientException {
        syncUpdatedPlaylist(context, playlistIds, null, null, forceUpdate);
    }

    public static void syncUpdatedPlaylist(final Context context, final Set<String> playlistIds, final PlaylistDatabaseCallback databaseCallback) throws JSONException, ServiceException, AbstractHttpClient.HttpClientException {
        syncUpdatedPlaylist(context, playlistIds, databaseCallback, null);
    }

    public static void syncUpdatedPlaylist(final Context context, final Set<String> playlistIds, final PlaylistUnificationCallback unificationCallback) throws JSONException, ServiceException, AbstractHttpClient.HttpClientException {
        syncUpdatedPlaylist(context, playlistIds, null, unificationCallback);
    }

    public static void syncUpdatedPlaylist(final Context context, final Set<String> playlistIds, final PlaylistDatabaseCallback databaseCallback, final PlaylistUnificationCallback unificationCallback) throws ServiceException, JSONException, AbstractHttpClient.HttpClientException {
        syncUpdatedPlaylist(context, playlistIds, databaseCallback, unificationCallback, false);
    }

    public static void syncUpdatedPlaylist(final Context context, final Set<String> playlistIds, final PlaylistDatabaseCallback databaseCallback, final PlaylistUnificationCallback unificationCallback, final boolean forceUpdate) throws JSONException, ServiceException, AbstractHttpClient.HttpClientException {
        JSONObject jSONObject;
        String string;
        boolean z;
        JSONObject jSONObject2;
        String str;
        MC2PlaylistApi mC2PlaylistApi = new MC2PlaylistApi();
        JSONArray jSONArray = new JSONArray();
        Iterator<String> it = playlistIds.iterator();
        while (it.hasNext()) {
            jSONArray.put(it.next());
        }
        JSONArray jSONArray2 = new JSONArray();
        int i = 0;
        for (String str2 : getSelectableValues()) {
            jSONArray2.put(str2);
        }
        ArrayList arrayList = new ArrayList();
        JSONArray featureSet = mC2PlaylistApi.getFeatureSet(false);
        JSONObject jSONObject3 = new JSONObject();
        jSONObject3.put("featureSet", featureSet);
        jSONObject3.put("playlistIds", jSONArray);
        jSONObject3.put("requestedMetadata", jSONArray2);
        boolean z2 = true;
        if (UserSubscriptionUtil.getUserSubscription().isKatana()) {
            jSONObject3.put(ContextMappingConstants.CONTENT_ENCODING, true);
        }
        if (ChildUserUtil.INSTANCE.isChildUser(context)) {
            addRequestParamsForMerlock(jSONObject3, context);
        }
        String str3 = null;
        JSONObject jSONObject4 = null;
        while (true) {
            String str4 = TAG;
            Log.info(str4, "GetPlaylistsByIdV2 Details start");
            JSONObject jSONObjectExecute = MusicPlaylistRequest.GetPlaylistsByIdV2.execute(jSONObject3);
            JSONArray jSONArray3 = jSONObjectExecute.getJSONArray("playlists");
            Log.info(str4, "GetPlaylistsByIdV2 Details end");
            jSONObject = jSONObject4;
            int i2 = i;
            string = str3;
            while (i2 < jSONArray3.length()) {
                JSONObject jSONObject5 = jSONArray3.getJSONObject(i2);
                JSONObject jSONObject6 = jSONObject5.getJSONObject("metadata");
                String string2 = jSONObject6.getString("playlistId");
                String strOptString = jSONObject6.optString("title");
                Integer numValueOf = Integer.valueOf(jSONObject6.optInt("totalTrackCount"));
                if (string2.equals(string)) {
                    z = z2;
                } else {
                    String str5 = TAG;
                    Object[] objArr = new Object[3];
                    objArr[i] = strOptString;
                    z = true;
                    objArr[1] = string2;
                    objArr[2] = numValueOf;
                    Log.info(str5, "Prepare to save playlist %s - %s with track count %s into db", objArr);
                    if (arrayList.size() > 0) {
                        str = "playlistId";
                        jSONObject2 = jSONObject6;
                        AsyncTask.THREAD_POOL_EXECUTOR.execute(updateCloudAndLocalPlaylist(context, string, jSONObject, arrayList, null, unificationCallback, forceUpdate));
                    } else {
                        jSONObject2 = jSONObject6;
                        str = "playlistId";
                    }
                    arrayList = new ArrayList();
                    string = jSONObject2.getString(str);
                    jSONObject = jSONObject5;
                }
                arrayList.add(jSONObject5.getJSONArray("tracks"));
                i2++;
                z2 = z;
                i = 0;
            }
            boolean z3 = z2;
            jSONObjectExecute.getBoolean("resyncLastPlaylistOnPreviousPage");
            String strOptString2 = jSONObjectExecute.optString("nextPageToken", null);
            if (strOptString2 != null && !"null".equals(strOptString2)) {
                jSONObject3.put("pageToken", strOptString2);
            }
            if (strOptString2 == null || "null".equals(strOptString2)) {
                break;
            }
            str3 = string;
            jSONObject4 = jSONObject;
            z2 = z3;
            i = 0;
        }
        AsyncTask.THREAD_POOL_EXECUTOR.execute(updateCloudAndLocalPlaylist(context, string, jSONObject, arrayList, databaseCallback, unificationCallback, forceUpdate));
    }

    public static void addRequestParamsForMerlock(JSONObject requestParams, Context context) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("hasExplicitLanguage", !AmazonApplication.getCapabilities().isExplicitLanguageFilterEnabled());
        requestParams.put("allowedParentalControls", jSONObject);
        requestParams.put("musicRequestIdentityContextToken", ChildUserUtil.INSTANCE.getMusicRequestIdentityContextToken(context));
    }

    public static void syncLocalPlaylists(final Context context, final Set<String> excludePlaylistIds) {
        int iUpdateAllLocalPlaylists = PrimePlaylistUtil.updateAllLocalPlaylists(context, CirrusDatabase.getWritableDatabase(context), new PrimePlaylistDatabaseManager(context), excludePlaylistIds);
        Log.debug(TAG, "Number of updated local playlists after sync: " + iUpdateAllLocalPlaylists);
    }

    public static void syncUnifiedPlaylistsToCloud(final Context context) {
        if (ConnectivityUtil.hasAnyInternetConnection(context)) {
            ThreadUtils.postInBackground(new Runnable() { // from class: com.amazon.mp3.library.service.sync.PlaylistSyncHelper$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    PlaylistSyncHelper.lambda$syncUnifiedPlaylistsToCloud$0(context);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$syncUnifiedPlaylistsToCloud$0(Context context) {
        try {
            syncNewDevicePlaylistsToCloud(context);
            syncUpdatedUnifiedPlaylistsToCloud(context);
            Log.debug(TAG, "syncing unified playlists to cloud if updated");
        } catch (Exception e) {
            Log.error(TAG, e.getMessage());
        }
    }

    public static void autodownloadUnfinishedUnifiedPlaylists(final Context context) {
        if (ConnectivityUtil.isWifiRequiredForDownloads(context) && !ConnectivityUtil.isWifiConnected(context)) {
            Log.warning(TAG, "Wifi not connected");
        }
        Cursor cursorQuery = null;
        try {
            try {
                cursorQuery = CirrusDatabase.getReadOnlyDatabase(context).query("Playlist INNER JOIN PlaylistTrack ON Playlist._id = PlaylistTrack.udo_playlist_id LEFT OUTER JOIN LocalTrackUri ON PlaylistTrack.track_luid = LocalTrackUri.track_luid", new String[]{"PlaylistTrack.udo_playlist_id", "PlaylistTrack.track_luid", "LocalTrackUri.local_uri"}, "Playlist.download_state = 0", null, "PlaylistTrack.udo_playlist_id", null, null);
                if (cursorQuery != null && cursorQuery.getCount() > 0) {
                    int columnIndex = cursorQuery.getColumnIndex("udo_playlist_id");
                    int columnIndex2 = cursorQuery.getColumnIndex("track_luid");
                    int columnIndex3 = cursorQuery.getColumnIndex("local_uri");
                    while (cursorQuery.moveToNext()) {
                        long j = cursorQuery.getLong(columnIndex);
                        String string = cursorQuery.getString(columnIndex2);
                        String string2 = cursorQuery.getString(columnIndex3);
                        boolean zAllCloudPlaylistTracksDownloaded = PlaylistUpdater.allCloudPlaylistTracksDownloaded(j);
                        Log.debug(TAG, "Unified playlist [%d] : trackLuid = [%s], localUri = [%s], allCloudPlaylistTracksDownloaded = [%s]", Long.valueOf(j), string, string2, Boolean.valueOf(zAllCloudPlaylistTracksDownloaded));
                        if (!zAllCloudPlaylistTracksDownloaded) {
                            PlaylistUpdater.downloadUpdatedUnifiedPlaylist(context, j, "cirrus");
                        }
                    }
                }
            } catch (Exception e) {
                Log.error(TAG, "Error occurred when trying to resume downloading unfinished unified playlists", e);
            }
        } finally {
            DbUtil.closeCursor(cursorQuery);
        }
    }

    public static void syncNewDevicePlaylistsToCloud(final Context context) {
        if (ConnectivityUtil.hasAnyInternetConnection(context)) {
            Cursor newDevicePlaylistsSinceLastSyncCursor = PlaylistUtil.getNewDevicePlaylistsSinceLastSyncCursor(context);
            if (newDevicePlaylistsSinceLastSyncCursor != null) {
                try {
                    if (newDevicePlaylistsSinceLastSyncCursor.getCount() > 0) {
                        int columnIndex = newDevicePlaylistsSinceLastSyncCursor.getColumnIndex("_id");
                        while (newDevicePlaylistsSinceLastSyncCursor.moveToNext()) {
                            unifyNewDevicePlaylistWithTracks(context, newDevicePlaylistsSinceLastSyncCursor.getLong(columnIndex));
                        }
                    }
                } finally {
                    DbUtil.closeCursor(newDevicePlaylistsSinceLastSyncCursor);
                }
            }
        }
    }

    private static void syncUpdatedUnifiedPlaylistsToCloud(final Context context) {
        if (ConnectivityUtil.hasAnyInternetConnection(context)) {
            Cursor updatedPlaylistsSinceLastSyncCursor = PlaylistUtil.getUpdatedPlaylistsSinceLastSyncCursor(context);
            if (updatedPlaylistsSinceLastSyncCursor != null) {
                try {
                    if (updatedPlaylistsSinceLastSyncCursor.getCount() > 0) {
                        int columnIndex = updatedPlaylistsSinceLastSyncCursor.getColumnIndex("_id");
                        while (updatedPlaylistsSinceLastSyncCursor.moveToNext()) {
                            updateUnifiedPlaylistWithTracks(context, updatedPlaylistsSinceLastSyncCursor.getLong(columnIndex));
                        }
                    }
                } finally {
                    DbUtil.closeCursor(updatedPlaylistsSinceLastSyncCursor);
                }
            }
        }
    }

    public static void unifyNewDevicePlaylistWithTracks(final Context context, final long devicePlaylistId) {
        long jCreatePlaylist;
        if (ConnectivityUtil.hasAnyInternetConnection(context)) {
            Playlist playlist = AmazonApplication.getLibraryItemFactory().getPlaylist(MediaProvider.UdoPlaylists.getContentUri("cirrus-local", devicePlaylistId));
            if (playlist == null) {
                Log.debug(TAG, "No device playlist found for %s", Long.valueOf(devicePlaylistId));
                return;
            }
            String name = playlist.getName();
            try {
            } catch (AbstractHttpClient.HttpClientException e) {
                Log.error(TAG, "Encountered HttpClientException when unifying playlist: " + devicePlaylistId, e);
                jCreatePlaylist = -1;
            } catch (ServiceException e2) {
                Log.error(TAG, "Encountered ServiceException when unifying playlist: " + devicePlaylistId, e2);
                jCreatePlaylist = -1;
            } catch (JSONException e3) {
                Log.error(TAG, "Encountered JSONException when unifying playlist: " + devicePlaylistId, e3);
                jCreatePlaylist = -1;
            }
            if (!PlaylistUtil.isUpdatedSinceLastSync(context, devicePlaylistId)) {
                Log.debug(TAG, "Playlist %s has been sync'd to cloud", Long.valueOf(devicePlaylistId));
                return;
            }
            PlaylistUtil.setUpdatedSinceLastSync(context, devicePlaylistId, false);
            final List<PlaylistServiceTrack> unifiedPlaylistTracks = PlaylistUtil.getUnifiedPlaylistTracks(context, devicePlaylistId);
            if (!unifiedPlaylistTracks.isEmpty()) {
                jCreatePlaylist = new MC2PlaylistApi().createPlaylistWithCatalogTrack(context, name, unifiedPlaylistTracks, new PlaylistUnificationCallback() { // from class: com.amazon.mp3.library.service.sync.PlaylistSyncHelper.1
                    @Override // com.amazon.mp3.library.service.sync.PlaylistUnificationCallback
                    public void onSuccess(long cloudPlaylistId) throws Throwable {
                        PlaylistSyncHelper.replaceDevicePlaylistWithUnifiedPlaylist(context, devicePlaylistId, cloudPlaylistId);
                        Log.debug(PlaylistSyncHelper.TAG, "Unified playlist is created, devicePlaylistId = %s, cloudDevicePlaylistId = %s, tracks size = %s", Long.valueOf(devicePlaylistId), Long.valueOf(cloudPlaylistId), Integer.valueOf(unifiedPlaylistTracks.size()));
                    }

                    @Override // com.amazon.mp3.library.service.sync.PlaylistUnificationCallback
                    public void onError() {
                        PlaylistUtil.setUpdatedSinceLastSync(context, devicePlaylistId, true);
                        Log.error(PlaylistSyncHelper.TAG, "Failed to create a unified playlist for %s", Long.valueOf(devicePlaylistId));
                    }
                });
            } else {
                jCreatePlaylist = new MC2PlaylistApi().createPlaylist(context, name, true, new PlaylistUnificationCallback() { // from class: com.amazon.mp3.library.service.sync.PlaylistSyncHelper.2
                    @Override // com.amazon.mp3.library.service.sync.PlaylistUnificationCallback
                    public void onSuccess(long cloudPlaylistId) throws Throwable {
                        PlaylistSyncHelper.replaceDevicePlaylistWithUnifiedPlaylist(context, devicePlaylistId, cloudPlaylistId);
                        Log.debug(PlaylistSyncHelper.TAG, "Empty unified playlist is created, devicePlaylistId = %s, cloudDevicePlaylistId = %s", Long.valueOf(devicePlaylistId), Long.valueOf(cloudPlaylistId));
                    }

                    @Override // com.amazon.mp3.library.service.sync.PlaylistUnificationCallback
                    public void onError() {
                        PlaylistUtil.setUpdatedSinceLastSync(context, devicePlaylistId, true);
                        Log.error(PlaylistSyncHelper.TAG, "Failed to create an empty unified playlist for %s", Long.valueOf(devicePlaylistId));
                    }
                });
            }
            if (jCreatePlaylist == -1) {
                PlaylistUtil.setUpdatedSinceLastSync(context, devicePlaylistId, true);
                Log.error(TAG, "Not able to unified playlist for %s", Long.valueOf(devicePlaylistId));
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:41:0x014c  */
    /* JADX WARN: Removed duplicated region for block: B:60:0x01b5  */
    /* JADX WARN: Removed duplicated region for block: B:86:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static void replaceDevicePlaylistWithUnifiedPlaylist(final android.content.Context r22, final long r23, final long r25) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 446
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.mp3.library.service.sync.PlaylistSyncHelper.replaceDevicePlaylistWithUnifiedPlaylist(android.content.Context, long, long):void");
    }

    private static void updateLocalTrackUriForLibraryTrack(final SQLiteDatabase db, final long cloudPlaylistId, final MusicTrack libraryTrack, final String trackId) {
        if (libraryTrack == null) {
            return;
        }
        String[] strArr = {libraryTrack.getAsin()};
        ContentValues contentValues = new ContentValues();
        contentValues.put("track_luid", trackId);
        db.update("LocalTrackUri", contentValues, "track_luid = ?", strArr);
        String str = TAG;
        Log.debug(str, "LibraryPlaylistServiceTrack: updated LocalTrackUris: replacing track_luid %s with %s", libraryTrack.getAsin(), trackId);
        String[] strArr2 = {libraryTrack.getAsin(), String.valueOf(cloudPlaylistId)};
        ContentValues contentValues2 = new ContentValues();
        contentValues2.put("track_luid", trackId);
        db.update("PlaylistTrack", contentValues2, "track_luid = ? AND udo_playlist_id = ?", strArr2);
        Log.debug(str, "LibraryPlaylistServiceTrack: updated PlaylistTrack: replacing track_luid %s with %s", libraryTrack.getAsin(), trackId);
    }

    private static void updateLocalTrackUriForCatalogTrack(final SQLiteDatabase db, final long cloudPlaylistId, final String trackLuid, final String trackAsin, final String trackId) {
        String str = "prime-" + trackAsin;
        String[] strArr = {trackId, str};
        if (!StringUtils.isNotBlank(trackLuid) || trackLuid.contains(trackAsin)) {
            trackLuid = trackAsin;
        }
        ContentValues contentValues = new ContentValues();
        contentValues.put("track_luid", trackLuid);
        db.update("LocalTrackUri", contentValues, "track_luid = ? OR track_luid = ? ", strArr);
        String str2 = TAG;
        Log.debug(str2, "CatalogPlaylistServiceTrack: updated LocalTrackUris: replacing track_luid %s with %s", trackId, trackLuid);
        String[] strArr2 = {trackId, str, String.valueOf(cloudPlaylistId)};
        ContentValues contentValues2 = new ContentValues();
        contentValues2.put("track_luid", trackLuid);
        db.update("PlaylistTrack", contentValues2, "(track_luid = ? OR track_luid = ?)  AND udo_playlist_id = ?", strArr2);
        Log.debug(str2, "CatalogPlaylistServiceTrack: Updated PlaylistTrack: replacing track_luid %s with %s", trackId, trackLuid);
    }

    public static void registerCallback(final PlaylistDatabaseCallback databaseCallback) {
        playlistDatabaseCallback = databaseCallback;
    }

    public static void deregisterCallback() {
        playlistDatabaseCallback = null;
    }

    public static void updateUnifiedPlaylistWithTracks(final Context context, final long playlistId) {
        if (ConnectivityUtil.hasAnyInternetConnection(context)) {
            if (AmazonApplication.getLibraryItemFactory().getPlaylist(MediaProvider.UdoPlaylists.getContentUri("cirrus", playlistId)) == null) {
                Log.debug(TAG, "No cloud playlist found for %s", Long.valueOf(playlistId));
                return;
            }
            try {
                List<PlaylistServiceTrack> unifiedPlaylistTracks = PlaylistUtil.getUnifiedPlaylistTracks(context, playlistId);
                updateDevicePlaylistTracksInDatabase(context, playlistId, unifiedPlaylistTracks);
                if (new MC2PlaylistApi().updatePlaylist(context, MediaProvider.UdoPlaylists.getContentUri("cirrus", playlistId))) {
                    PlaylistUtil.setUpdatedSinceLastSync(context, playlistId, false);
                }
                Log.debug(TAG, "Created unified cloud playlist %s, tracks size = %s", Long.valueOf(playlistId), Integer.valueOf(unifiedPlaylistTracks.size()));
            } catch (AbstractHttpClient.HttpClientException e) {
                Log.error(TAG, "Encountered HttpClientException while updating unified playlist: " + playlistId, e);
            } catch (ServiceException e2) {
                Log.error(TAG, "Encountered ServiceException while updating unified playlist: " + playlistId, e2);
            }
        }
    }

    public static void updateDevicePlaylistTracksInDatabase(final Context context, final long playlistId, final List<PlaylistServiceTrack> tracks) {
        SQLiteDatabase writableDatabase = CirrusDatabase.getWritableDatabase(context);
        ArrayList arrayList = new ArrayList();
        Iterator<PlaylistServiceTrack> it = tracks.iterator();
        while (it.hasNext()) {
            arrayList.add(it.next().getId());
        }
        writableDatabase.beginTransaction();
        try {
            try {
                String[] strArr = {String.valueOf(playlistId)};
                writableDatabase.delete("PlaylistTrack", "udo_playlist_id=? ", strArr);
                writableDatabase.delete("PlaylistTrackScratch", "udo_playlist_id=? ", strArr);
                int i = 0;
                for (PlaylistServiceTrack playlistServiceTrack : tracks) {
                    ContentValues contentValues = new ContentValues();
                    contentValues.put("track_luid", playlistServiceTrack.getId());
                    contentValues.put("udo", Integer.valueOf(i));
                    contentValues.put("udo_playlist_id", Long.valueOf(playlistId));
                    writableDatabase.insert("PlaylistTrack", null, contentValues);
                    writableDatabase.insert("PlaylistTrackScratch", null, contentValues);
                    i++;
                }
                ContentValues contentValues2 = new ContentValues(1);
                contentValues2.put("download_state", (Integer) 0);
                writableDatabase.update("Playlist", contentValues2, "_id = ?", new String[]{String.valueOf(playlistId)});
                writableDatabase.setTransactionSuccessful();
            } catch (Exception unused) {
                Log.error(TAG, "Failed to update playlist tracks for playlist %s", Long.valueOf(playlistId));
            }
        } finally {
            writableDatabase.endTransaction();
        }
    }

    public static void setPlaylistVersionToClear(final Context context) {
        SQLiteDatabase writableDatabase = CirrusDatabase.getWritableDatabase(context);
        ContentValues contentValues = new ContentValues();
        contentValues.put("version", "clearVersion");
        writableDatabase.beginTransaction();
        try {
            writableDatabase.update("Playlist", contentValues, null, null);
            writableDatabase.setTransactionSuccessful();
        } finally {
            writableDatabase.endTransaction();
        }
    }

    private static String[] getSelectableValues() {
        return (String[]) ArrayUtils.removeElements(SelectableAttribute.values(), "unauthorizedDownloadCount");
    }

    private static Runnable updateCloudAndLocalPlaylist(final Context context, final String playlistId, final JSONObject playlist, final List<JSONArray> playlistTracks, final PlaylistDatabaseCallback databaseCallback, final PlaylistUnificationCallback unificationCallback, final boolean forceUpdate) {
        return new Runnable() { // from class: com.amazon.mp3.library.service.sync.PlaylistSyncHelper.3
            @Override // java.lang.Runnable
            public void run() {
                try {
                    new PlaylistUpdater(context, playlist, playlistTracks).update(databaseCallback, unificationCallback, forceUpdate);
                } catch (Throwable th) {
                    Log.error(PlaylistSyncHelper.TAG, "Failed to update playlists", th);
                }
            }
        };
    }
}
