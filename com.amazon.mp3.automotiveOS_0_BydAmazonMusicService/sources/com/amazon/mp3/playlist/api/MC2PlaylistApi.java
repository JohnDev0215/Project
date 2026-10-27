package com.amazon.mp3.playlist.api;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.text.TextUtils;
import com.amazon.identity.auth.device.utils.AccountConstants;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.account.details.AccountDetailUtil;
import com.amazon.mp3.download.helper.UdoPlaylistDownloadHelper;
import com.amazon.mp3.environment.Environment;
import com.amazon.mp3.featuregate.Feature;
import com.amazon.mp3.library.item.Playlist;
import com.amazon.mp3.library.provider.MediaProvider;
import com.amazon.mp3.library.provider.source.cirrus.CirrusDatabase;
import com.amazon.mp3.library.provider.source.cirrus.CirrusMediaSource;
import com.amazon.mp3.library.provider.source.cirrus.CirrusPlaylistScratch;
import com.amazon.mp3.library.service.sync.PlaylistSyncHandler;
import com.amazon.mp3.library.service.sync.PlaylistSyncHelper;
import com.amazon.mp3.library.service.sync.PlaylistUnificationCallback;
import com.amazon.mp3.library.service.sync.SyncService;
import com.amazon.mp3.library.util.EditPlaylistMetricsLogger;
import com.amazon.mp3.library.util.PlaylistUtil;
import com.amazon.mp3.net.AbstractHttpClient;
import com.amazon.mp3.net.playlist.MusicPlaylistExceptions;
import com.amazon.mp3.net.playlist.MusicPlaylistRequest;
import com.amazon.mp3.net.playlist.PlaylistErrorValidatorKt;
import com.amazon.mp3.net.service.ServiceException;
import com.amazon.mp3.playlist.CatalogPlaylist;
import com.amazon.mp3.playlist.EntityPlaylist;
import com.amazon.mp3.playlist.PlaylistConfigurationStorage;
import com.amazon.mp3.playlist.PlaylistRequestUtil;
import com.amazon.mp3.playlist.PlaylistSeedEntityType;
import com.amazon.mp3.playlist.SharedUserPlaylist;
import com.amazon.mp3.playlist.api.track.PlaylistServiceTrack;
import com.amazon.mp3.util.ChildUserUtil;
import com.amazon.mp3.util.DbUtil;
import com.amazon.mp3.util.Log;
import com.amazon.mp3.util.UserSubscriptionUtil;
import com.amazon.music.curate.provider.EntityType;
import com.amazon.music.curate.skyfire.bootstrap.ContextMappingConstants;
import com.amazon.music.inappmessaging.internal.model.Splash;
import com.amazon.music.metrics.mts.event.types.uiinteraction.EntityIdType;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes3.dex */
public class MC2PlaylistApi {
    private static final String TAG = "MC2PlaylistApi";

    private String getPlaylistVersionForNewPlaylist(List<PlaylistServiceTrack> trackList) {
        return (trackList == null || trackList.size() <= 0) ? "1:0" : "0";
    }

    public long createPlaylist(final Context context, final String name, final boolean createdInOfflineView) throws ServiceException, AbstractHttpClient.HttpClientException {
        return createPlaylist(context, name, (List<PlaylistServiceTrack>) null, createdInOfflineView, (PlaylistUnificationCallback) null);
    }

    public long createPlaylist(final Context context, final String name, final boolean createdInOfflineView, final PlaylistUnificationCallback callback) throws ServiceException, AbstractHttpClient.HttpClientException {
        return createPlaylist(context, name, (List<PlaylistServiceTrack>) null, createdInOfflineView, callback);
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x002a A[Catch: JSONException -> 0x00f1, TryCatch #0 {JSONException -> 0x00f1, blocks: (B:9:0x0010, B:11:0x0016, B:13:0x001e, B:15:0x0023, B:17:0x002a, B:18:0x002e, B:20:0x0034, B:21:0x0042, B:23:0x0067, B:26:0x009a, B:28:0x00b2, B:27:0x00a2), top: B:50:0x0010 }] */
    /* JADX WARN: Removed duplicated region for block: B:23:0x0067 A[Catch: JSONException -> 0x00f1, TRY_LEAVE, TryCatch #0 {JSONException -> 0x00f1, blocks: (B:9:0x0010, B:11:0x0016, B:13:0x001e, B:15:0x0023, B:17:0x002a, B:18:0x002e, B:20:0x0034, B:21:0x0042, B:23:0x0067, B:26:0x009a, B:28:0x00b2, B:27:0x00a2), top: B:50:0x0010 }] */
    /* JADX WARN: Removed duplicated region for block: B:45:0x00fb  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public long createPlaylist(final android.content.Context r10, final java.lang.String r11, final java.util.List<com.amazon.mp3.playlist.api.track.PlaylistServiceTrack> r12, final boolean r13, final com.amazon.mp3.library.service.sync.PlaylistUnificationCallback r14) throws com.amazon.mp3.net.service.ServiceException, com.amazon.mp3.net.AbstractHttpClient.HttpClientException {
        /*
            Method dump skipped, instruction units count: 278
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.mp3.playlist.api.MC2PlaylistApi.createPlaylist(android.content.Context, java.lang.String, java.util.List, boolean, com.amazon.mp3.library.service.sync.PlaylistUnificationCallback):long");
    }

    public Uri createPlaylist(final Context context, final String name, final boolean createdInOfflineView, final PlaylistUnificationCallback callback, final boolean createAsPublicPlaylist) throws ServiceException, AbstractHttpClient.HttpClientException {
        if (name == null || name.length() == 0) {
            Log.error(TAG, "Failed to create playlist: Playlist title can't be null or empty");
        }
        long j = -1;
        try {
            String string = MusicPlaylistRequest.CreatePlaylist.execute(new JSONObject().put("title", name).put("tracks", new JSONArray()).put("visibility", createAsPublicPlaylist ? "PUBLIC" : "PRIVATE")).getString("playlistId");
            int i = createAsPublicPlaylist ? 1 : 0;
            if (!TextUtils.isEmpty(string)) {
                ContentValues contentValues = new ContentValues();
                contentValues.put("_id", Long.valueOf(PlaylistRequestUtil.generatePlaylistId(string)));
                contentValues.put("name", name);
                contentValues.put(AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE, "vnd.android.cursor.item/vnd.amazonmp3.udoplaylist");
                contentValues.put("visibility", Integer.valueOf(i));
                contentValues.put("luid", string);
                try {
                    contentValues.put("version", getPlaylistVersionForNewPlaylist(null));
                    if (createdInOfflineView) {
                        contentValues.put("download_state", (Integer) 0);
                    } else {
                        contentValues.put("download_state", Integer.valueOf(new UdoPlaylistDownloadHelper(context).getDownloadState(string)));
                    }
                    long jReplace = CirrusDatabase.getWritableDatabase(context).replace("Playlist", null, contentValues);
                    try {
                        if (callback != null) {
                            if (jReplace != -1) {
                                callback.onSuccess(jReplace);
                            } else {
                                callback.onError();
                            }
                        }
                        contentUri = jReplace != -1 ? MediaProvider.UdoPlaylists.getContentUri("cirrus", jReplace) : null;
                        j = jReplace;
                    } catch (JSONException e) {
                        e = e;
                        j = jReplace;
                        Log.error(TAG, "JSONException while creating playlist", e);
                        if (callback != null) {
                            callback.onError();
                        }
                    }
                } catch (JSONException e2) {
                    e = e2;
                }
            }
        } catch (JSONException e3) {
            e = e3;
        }
        Log.debug(TAG, "createPlaylist returning result: %d", Long.valueOf(j));
        return contentUri;
    }

    public long createPlaylistWithCatalogTrack(final Context context, final String name, final List<PlaylistServiceTrack> trackList, final PlaylistUnificationCallback callback) throws ServiceException, JSONException, AbstractHttpClient.HttpClientException {
        long jReplace;
        JSONObject jSONObjectExecute = MusicPlaylistRequest.CreatePlaylist.execute(new JSONObject().put("title", name).put("tracks", new JSONArray()));
        String string = jSONObjectExecute.getString("playlistId");
        String string2 = jSONObjectExecute.getString("version");
        if (TextUtils.isEmpty(string) || TextUtils.isEmpty(string2)) {
            jReplace = -1;
        } else {
            ContentValues contentValues = new ContentValues();
            contentValues.put("_id", Long.valueOf(PlaylistRequestUtil.generatePlaylistId(string)));
            contentValues.put("name", name);
            contentValues.put(AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE, "vnd.android.cursor.item/vnd.amazonmp3.udoplaylist");
            contentValues.put("luid", string);
            contentValues.put("version", getPlaylistVersionForNewPlaylist(trackList));
            contentValues.put("download_state", Integer.valueOf(new UdoPlaylistDownloadHelper(context).getDownloadState(string)));
            jReplace = CirrusDatabase.getWritableDatabase(context).replace("Playlist", null, contentValues);
            appendTracksToPlaylist(context, string, string2, trackList, callback);
            if (trackList != null && trackList.size() > 0) {
                PlaylistRequestUtil.waitForPlaylistSync(context);
            }
        }
        if (callback != null) {
            if (jReplace != -1) {
                callback.onSuccess(jReplace);
            } else {
                callback.onError();
            }
        }
        return jReplace;
    }

    public boolean renamePlaylist(Context context, String luid, String name) throws ServiceException, AbstractHttpClient.HttpClientException {
        if (TextUtils.isEmpty(luid)) {
            Log.error(TAG, "Failed to rename playlist: objectId cannot be null");
            return false;
        }
        if (TextUtils.isEmpty(name)) {
            Log.error(TAG, "Failed to rename playlist: playlist title cannot be null");
            return false;
        }
        long jUpdatePlaylistDB = -1;
        try {
            String string = MusicPlaylistRequest.RenamePlaylist.execute(new JSONObject().put("title", name).put("playlistId", luid)).getString("version");
            Log.verbose(TAG, "new version of the playlist {%s | %s}: %s", name, luid, string);
            ContentValues contentValues = new ContentValues();
            contentValues.put("name", name);
            contentValues.put("version", string);
            contentValues.put("dateLastModified", Long.valueOf(getCurrentTimestamp()));
            jUpdatePlaylistDB = updatePlaylistDB(context, luid, contentValues);
            new EditPlaylistMetricsLogger(context).sendOnlinePlaylistEdited(EntityIdType.CD_OBJECT_ID.getMetricsValue(), luid);
        } catch (JSONException e) {
            Log.error(TAG, "JSONException parsing renamePlaylist response!", e);
        }
        return jUpdatePlaylistDB >= 0;
    }

    private long getCurrentTimestamp() {
        return Calendar.getInstance().getTimeInMillis() / 1000;
    }

    public boolean deletePlaylists(Context context, List<String> playlistLuids) throws ServiceException, AbstractHttpClient.FailedException, AbstractHttpClient.CanceledException, AbstractHttpClient.IncompleteResultException, AbstractHttpClient.UnexpectedHttpStatusException {
        JSONObject jSONObject;
        if (playlistLuids == null || playlistLuids.size() == 0) {
            Log.error(TAG, "Failed to delete playlists: playlist ids cannot be null");
            return false;
        }
        int size = playlistLuids.size();
        int maximumNumberOfPlaylistsPerRequest = PlaylistConfigurationStorage.get().getMaximumNumberOfPlaylistsPerRequest();
        boolean z = true;
        int i = 0;
        while (i < size) {
            JSONArray jSONArray = new JSONArray();
            int i2 = 0;
            while (i2 < maximumNumberOfPlaylistsPerRequest && i < size) {
                jSONArray.put(playlistLuids.get(i));
                i2++;
                i++;
            }
            try {
                JSONObject jSONObjectPut = new JSONObject().put("playlistIds", jSONArray);
                if (ChildUserUtil.INSTANCE.isChildUser(context)) {
                    jSONObjectPut.put("featureSet", getFeatureSetForRemovePlaylist());
                }
                jSONObject = MusicPlaylistRequest.RemovePlaylists.execute(jSONObjectPut).getJSONObject("playlistsToExceptions");
            } catch (JSONException e) {
                Log.error(TAG, "Exception while deleting playlists", e);
            }
            if (jSONObject != null && jSONObject.length() > 0) {
                Log.error(TAG, "Exceptions found while deleting playlists");
                parsePlaylistsToExceptions(jSONObject, jSONArray);
                z = false;
            }
        }
        return z;
    }

    private JSONArray getFeatureSetForRemovePlaylist() {
        JSONArray jSONArray = new JSONArray();
        jSONArray.put("OMIT_CONTENT_UNFOLLOWED_EVENT");
        return jSONArray;
    }

    private JSONArray getFeatureSetForAddPlaylist() {
        JSONArray jSONArray = new JSONArray();
        jSONArray.put("OMIT_CONTENT_FOLLOWED_EVENT");
        return jSONArray;
    }

    private void parsePlaylistsToExceptions(JSONObject playlistsToExceptions, JSONArray ids) {
        int length = ids.length();
        for (int i = 0; i < length; i++) {
            try {
                String string = ids.getString(i);
                JSONObject jSONObject = (JSONObject) playlistsToExceptions.get(string);
                Log.warning(TAG, "luid = %s,  message = %s, type = %s", string, jSONObject.get("message"), jSONObject.get("__type"));
            } catch (JSONException e) {
                Log.error(TAG, e.getMessage());
                return;
            }
        }
    }

    public JSONObject getPersonalizedPlaylists(JSONObject arguments) throws ServiceException, AbstractHttpClient.FailedException, AbstractHttpClient.CanceledException, AbstractHttpClient.IncompleteResultException, AbstractHttpClient.UnexpectedHttpStatusException {
        return MusicPlaylistRequest.GetPersonalizedPlaylists.execute(arguments);
    }

    public boolean deletePlaylist(Context context, String luid) throws ServiceException, AbstractHttpClient.HttpClientException {
        if (TextUtils.isEmpty(luid)) {
            Log.error(TAG, "Failed to delete playlist: playlist objectId cannot be null");
            return false;
        }
        ArrayList arrayList = new ArrayList();
        arrayList.add(luid);
        boolean zDeletePlaylists = deletePlaylists(context, arrayList);
        if (zDeletePlaylists) {
            deleteUdoPlaylistFromDb(context, luid);
        }
        return zDeletePlaylists;
    }

    public boolean updatePlaylist(Context context, Uri playlistUri) throws ServiceException, AbstractHttpClient.HttpClientException {
        boolean zMatchCloud = CirrusMediaSource.matchCloud(playlistUri);
        if (zMatchCloud) {
            try {
                if (!handleUpdatePlaylistRequest(context, playlistUri)) {
                    return false;
                }
            } catch (JSONException e) {
                Log.error(TAG, "JSONException while updating playlist.", e);
                return false;
            }
        }
        String lastPathSegment = playlistUri.getLastPathSegment();
        String strStringFromCursor = DbUtil.stringFromCursor(CirrusDatabase.getReadOnlyDatabase(context).query("Playlist", new String[]{"luid"}, "_id=?", new String[]{lastPathSegment}, null, null, null));
        CirrusPlaylistScratch.postToDb(context, Long.parseLong(lastPathSegment), zMatchCloud ? "cirrus" : "cirrus-local");
        EditPlaylistMetricsLogger editPlaylistMetricsLogger = new EditPlaylistMetricsLogger(context);
        if (zMatchCloud) {
            PlaylistUtil.setUpdatedSinceLastSync(context, Long.parseLong(lastPathSegment), false);
            editPlaylistMetricsLogger.sendOnlinePlaylistEdited(EntityIdType.CD_OBJECT_ID.getMetricsValue(), strStringFromCursor);
        } else {
            PlaylistUtil.setUpdatedSinceLastSync(context, Long.parseLong(lastPathSegment), true);
            editPlaylistMetricsLogger.sendOfflinePlaylistEdited(EntityType.USER_PLAYLIST.name(), EntityIdType.CD_OBJECT_ID.getMetricsValue(), strStringFromCursor);
        }
        context.getContentResolver().notifyChange(zMatchCloud ? CirrusMediaSource.NOTIFICATION_URI : CirrusMediaSource.NOTIFICATION_URI_LOCAL, null);
        PlaylistUtil.refreshPlaylistArt(MediaProvider.UdoPlaylists.getContentUri(zMatchCloud ? "cirrus" : "cirrus-local", Long.parseLong(lastPathSegment)));
        return true;
    }

    public boolean postPlaylistVisibilityUpdate(final Context context, final Uri playlistUri, final MediaProvider.Playlists.VisibilityState visibilityState) throws ServiceException, AbstractHttpClient.HttpClientException {
        if (CirrusMediaSource.matchCloud(playlistUri)) {
            try {
                if (!handleUpdatePlaylistRequest(context, playlistUri, true, visibilityState)) {
                    return false;
                }
                context.getContentResolver().notifyChange(CirrusMediaSource.NOTIFICATION_URI, null);
            } catch (JSONException e) {
                Log.error(TAG, "JSONException while updating playlist.", e);
                return false;
            }
        }
        return true;
    }

    private boolean handleUpdatePlaylistRequest(final Context context, final Uri playlistUri) throws JSONException, ServiceException, AbstractHttpClient.FailedException, AbstractHttpClient.CanceledException, AbstractHttpClient.IncompleteResultException, AbstractHttpClient.UnexpectedHttpStatusException {
        return handleUpdatePlaylistRequest(context, playlistUri, false, null);
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x0069  */
    /* JADX WARN: Removed duplicated region for block: B:61:0x0253  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private boolean handleUpdatePlaylistRequest(final android.content.Context r26, final android.net.Uri r27, final boolean r28, final com.amazon.mp3.library.provider.MediaProvider.Playlists.VisibilityState r29) throws com.amazon.mp3.net.service.ServiceException, org.json.JSONException, com.amazon.mp3.net.AbstractHttpClient.FailedException, com.amazon.mp3.net.AbstractHttpClient.CanceledException, com.amazon.mp3.net.AbstractHttpClient.IncompleteResultException, com.amazon.mp3.net.AbstractHttpClient.UnexpectedHttpStatusException {
        /*
            Method dump skipped, instruction units count: 643
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.mp3.playlist.api.MC2PlaylistApi.handleUpdatePlaylistRequest(android.content.Context, android.net.Uri, boolean, com.amazon.mp3.library.provider.MediaProvider$Playlists$VisibilityState):boolean");
    }

    private boolean ignoreMyLikesUpdateFailure(final Context context, final String playlistId, final JSONObject e) {
        if (e == null || !"com.amazon.musicplaylist.model#PlaylistVersionException".equals(e.optString("__type"))) {
            return false;
        }
        return !PlaylistUtil.canLikePlaylistTracks(context, MediaProvider.UdoPlaylists.getContentUriFromLuid(context, "cirrus", playlistId));
    }

    private int updatePlaylistDB(final Context context, final String luid, final ContentValues values) {
        return CirrusDatabase.getWritableDatabase(context).update("Playlist", values, "luid=?", new String[]{luid});
    }

    public Collection<PlaylistChangeEntry> getChangedPlaylistsSinceCheckpoint(Context context, PlaylistSyncHandler syncHandler, final boolean forceUpdatePlaylists) throws ServiceException, AbstractHttpClient.FailedException, AbstractHttpClient.CanceledException, AbstractHttpClient.IncompleteResultException, AbstractHttpClient.UnexpectedHttpStatusException {
        return getChangedPlaylistsSinceCheckpoint(context, syncHandler, forceUpdatePlaylists, false);
    }

    public Collection<PlaylistChangeEntry> getChangedPlaylistsSinceCheckpoint(Context context, PlaylistSyncHandler syncHandler, final boolean forceUpdatePlaylists, final boolean performFullSync) throws ServiceException, AbstractHttpClient.FailedException, AbstractHttpClient.CanceledException, AbstractHttpClient.IncompleteResultException, AbstractHttpClient.UnexpectedHttpStatusException {
        JSONObject jSONObjectExecute;
        PlaylistConfigurationStorage playlistConfigurationStorage = PlaylistConfigurationStorage.get();
        if (performFullSync) {
            playlistConfigurationStorage.removePlaylistCheckpoint();
        }
        String playlistCheckpoint = playlistConfigurationStorage.getPlaylistCheckpoint();
        HashMap map = new HashMap();
        String str = null;
        while (true) {
            try {
                HashMap map2 = new HashMap();
                JSONObject jSONObject = new JSONObject();
                if (!TextUtils.isEmpty(playlistCheckpoint)) {
                    jSONObject.put("playlistCheckpoint", playlistCheckpoint);
                }
                if (!TextUtils.isEmpty(str)) {
                    jSONObject.put("pageToken", str);
                }
                if (AccountDetailUtil.get(context).isUserInHawkfireMarketplace()) {
                    jSONObject.put("optIntoSubscriptionContent", true);
                }
                if (UserSubscriptionUtil.getUserSubscription().isKatana()) {
                    jSONObject.put(ContextMappingConstants.CONTENT_ENCODING, true);
                }
                jSONObject.put("optIntoSharedPlaylists", true);
                JSONArray jSONArray = new JSONArray();
                jSONArray.put("LIKES");
                jSONArray.put("SUPPORT_MIXED_ID_TYPES");
                if (AmazonApplication.getCapabilities().isEntityPlaylistSupported()) {
                    jSONArray.put("ENTITY_PLAYLISTS");
                }
                jSONObject.put("featureSet", jSONArray);
                jSONObjectExecute = MusicPlaylistRequest.GetChangedPlaylistsSinceCheckpoint.execute(jSONObject);
                validatePlaylistResponse(jSONObjectExecute);
                JSONArray jSONArray2 = jSONObjectExecute.getJSONArray("updates");
                if (jSONArray2 == null) {
                    Log.warning(TAG, "no updates found in the response");
                    break;
                }
                int length = jSONArray2.length();
                for (int i = 0; i < length; i++) {
                    PlaylistChangeEntry playlistChangeEntryFromJSON = PlaylistChangeEntry.fromJSON(jSONArray2.getJSONObject(i));
                    map2.put(playlistChangeEntryFromJSON.getPlaylistId(), playlistChangeEntryFromJSON);
                }
                map.putAll(map2);
                if (playlistCheckpoint == null && !forceUpdatePlaylists) {
                    dedupeUnchangedOrRemovedPlaylists(map2);
                }
                syncHandler.onGetChangedPlaylistsSinceCheckpointPageResponseReceived(map2.values(), forceUpdatePlaylists);
                String strOptString = jSONObjectExecute.optString("nextPageToken", null);
                if (strOptString == null || "null".equals(strOptString)) {
                    break;
                }
                str = strOptString;
            } catch (MusicPlaylistExceptions.InvalidCheckpointException e) {
                Log.debug(TAG, "Invalid checkpoint calling getChangedPlaylistsSinceCheckpoint response, attempting to retry with no checkpoint (full playlist sync)", e);
                playlistConfigurationStorage.removePlaylistCheckpoint();
                return getChangedPlaylistsSinceCheckpoint(context, syncHandler, forceUpdatePlaylists);
            } catch (JSONException e2) {
                Log.error(TAG, "Error while parsing getChangedPlaylistsSinceCheckpoint response", e2);
            }
        }
        if (playlistCheckpoint == null && !forceUpdatePlaylists) {
            deleteUnchangedOrRemovedPlaylists(context, map);
        }
        playlistConfigurationStorage.setPlaylistCheckpoint(jSONObjectExecute.getString("playlistCheckpoint"));
        return map.values();
    }

    protected void dedupeUnchangedOrRemovedPlaylists(Map<String, PlaylistChangeEntry> currentChangedPlaylists) {
        Cursor cursorQuery = MediaProvider.getInstance().query(MediaProvider.Playlists.getFilterContentUri("cirrus", "").buildUpon().appendQueryParameter("include_smart_playlists", "false").build(), new String[]{"luid", "version"}, null, null, null);
        try {
            int columnIndex = cursorQuery.getColumnIndex("luid");
            int columnIndex2 = cursorQuery.getColumnIndex("version");
            while (cursorQuery.moveToNext()) {
                String string = cursorQuery.getString(columnIndex);
                String string2 = cursorQuery.getString(columnIndex2);
                if (currentChangedPlaylists.containsKey(string) && string2.equals(currentChangedPlaylists.get(string).getVersion())) {
                    currentChangedPlaylists.remove(string);
                }
            }
        } finally {
            DbUtil.closeCursor(cursorQuery);
        }
    }

    private void deleteUnchangedOrRemovedPlaylists(Context context, Map<String, PlaylistChangeEntry> changedPlaylists) {
        Cursor cursorQuery = MediaProvider.getInstance().query(MediaProvider.Playlists.getFilterContentUri("cirrus", "").buildUpon().appendQueryParameter("include_smart_playlists", "false").build(), new String[]{AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE, "luid", "asin"}, null, null, null);
        ArrayList arrayList = new ArrayList();
        try {
            int columnIndex = cursorQuery.getColumnIndex(AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE);
            int columnIndex2 = cursorQuery.getColumnIndex("luid");
            int columnIndex3 = cursorQuery.getColumnIndex("asin");
            while (cursorQuery.moveToNext()) {
                String string = cursorQuery.getString(columnIndex2);
                if (!changedPlaylists.containsKey(string)) {
                    Playlist playlist = new Playlist();
                    playlist.setType(cursorQuery.getString(columnIndex));
                    playlist.setLuid(string);
                    playlist.setAsin(cursorQuery.getString(columnIndex3));
                    arrayList.add(playlist);
                }
            }
            DbUtil.closeCursor(cursorQuery);
            if (arrayList.isEmpty()) {
                Log.debug(TAG, "deleteUnchangedOrRemovedPlaylists(), no playlistsToDelete found.");
                return;
            }
            Log.debug(TAG, "deleteUnchangedOrRemovedPlaylists(),  playlistsToDelete found: " + arrayList.size());
            removeNonexistentPlaylistsFromFullSync(context, arrayList);
        } catch (Throwable th) {
            DbUtil.closeCursor(cursorQuery);
            throw th;
        }
    }

    private void removeNonexistentPlaylistsFromFullSync(Context context, List<Playlist> playlistsToDelete) {
        for (Playlist playlist : playlistsToDelete) {
            if (playlist.getType().equals("vnd.android.cursor.item/vnd.amazonmp3.udoplaylist")) {
                deleteUdoPlaylistFromDb(context, playlist.getLuid());
            } else if (playlist.getType().equals("vnd.android.cursor.item/vnd.amazonmp3.primeplaylist")) {
                deletePrimePlaylistFromDb(context, playlist);
            }
        }
        if (playlistsToDelete.size() > 0) {
            context.getContentResolver().notifyChange(CirrusMediaSource.NOTIFICATION_URI, null);
        }
    }

    private void deleteUdoPlaylistFromDb(Context context, String luid) {
        SQLiteDatabase writableDatabase = CirrusDatabase.getWritableDatabase(context);
        writableDatabase.delete("Playlist", "luid=?", new String[]{luid});
        writableDatabase.delete("PlaylistTrack", "udo_playlist_id=?", new String[]{String.valueOf(PlaylistRequestUtil.generatePlaylistId(luid))});
        new EditPlaylistMetricsLogger(context).sendOnlinePlaylistEdited(EntityIdType.CD_OBJECT_ID.getMetricsValue(), luid);
    }

    private void deletePrimePlaylistFromDb(Context context, Playlist playlist) {
        CirrusDatabase.getWritableDatabase(context).delete("PrimePlaylists", "luid=? OR asin=?", new String[]{playlist.getLuid(), playlist.getAsin()});
    }

    public void getConfiguration(Context context) throws ServiceException, AbstractHttpClient.FailedException, AbstractHttpClient.CanceledException, AbstractHttpClient.IncompleteResultException, AbstractHttpClient.UnexpectedHttpStatusException {
        PlaylistConfigurationStorage playlistConfigurationStorage = PlaylistConfigurationStorage.get();
        JSONObject jSONObjectExecute = MusicPlaylistRequest.GetConfiguration.execute();
        try {
            int i = jSONObjectExecute.getInt("maximumNumberOfPlaylistsAllowed");
            playlistConfigurationStorage.setMaximumNumberOfPlaylistsAllowed(i);
            int i2 = jSONObjectExecute.getInt("maximumNumberOfPlaylistsPerRequest");
            playlistConfigurationStorage.setMaximumNumberOfPlaylistsPerRequest(i2);
            int i3 = jSONObjectExecute.getInt("maximumNumberOfTracksInPlaylist");
            playlistConfigurationStorage.setMaximumNumberOfTracksInPlaylist(i3);
            int i4 = jSONObjectExecute.getInt("maximumNumberOfTracksPerRequest");
            playlistConfigurationStorage.setMaximumNumberOfTracksPerRequest(i4);
            int i5 = jSONObjectExecute.getInt("maximumPlaylistTitleLength");
            playlistConfigurationStorage.setMaximumPlaylistTitleLength(i5);
            playlistConfigurationStorage.setConfigurationsCheckTime();
            Log.verbose(TAG, "maximumNumberOfPlaylistsAllowed = %d, maximumNumberOfPlaylistsPerRequest = %d, maximumNumberOfTracksInPlaylist = %d, maximumNumberOfTracksPerRequest = %d, maximumPlaylistTitleLength = %d", Integer.valueOf(i), Integer.valueOf(i2), Integer.valueOf(i3), Integer.valueOf(i4), Integer.valueOf(i5));
        } catch (JSONException e) {
            Log.error(TAG, e.getMessage());
        }
    }

    public CatalogPlaylist getPrimePlaylistByAsin(String playlistAsin) throws ServiceException, AbstractHttpClient.FailedException, AbstractHttpClient.CanceledException, AbstractHttpClient.IncompleteResultException, AbstractHttpClient.UnexpectedHttpStatusException {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("asin", playlistAsin).put(Splash.PARAMS_MUSIC_TERRITORY, AccountDetailUtil.getMusicTerritoryOfResidence());
            if (UserSubscriptionUtil.getUserSubscription().isKatana()) {
                jSONObject.put(ContextMappingConstants.CONTENT_ENCODING, true);
            }
            Context applicationContext = AmazonApplication.getApplication().getApplicationContext();
            if (ChildUserUtil.INSTANCE.isChildUser(applicationContext)) {
                addRequestParamsForMerlock(jSONObject, applicationContext);
            }
            return CatalogPlaylist.fromJSON(playlistAsin, AccountDetailUtil.get().getHomeMarketPlaceId(), MusicPlaylistRequest.GetCatalogPlaylistByAsin.execute(jSONObject));
        } catch (JSONException e) {
            Log.error(TAG, e.getMessage(), e);
            return null;
        }
    }

    public CatalogPlaylist getPlaylistByKey(final String playlistKey) throws ServiceException, AbstractHttpClient.FailedException, AbstractHttpClient.CanceledException, AbstractHttpClient.IncompleteResultException, AbstractHttpClient.UnexpectedHttpStatusException {
        CatalogPlaylist catalogPlaylistFromJSON;
        try {
            JSONArray jSONArray = new JSONArray();
            jSONArray.put(playlistKey);
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("playlistIds", jSONArray);
            jSONObject.put("featureSet", getFeatureSet(false));
            if (UserSubscriptionUtil.getUserSubscription().isKatana()) {
                jSONObject.put(ContextMappingConstants.CONTENT_ENCODING, true);
            }
            Context applicationContext = AmazonApplication.getApplication().getApplicationContext();
            if (ChildUserUtil.INSTANCE.isChildUser(applicationContext)) {
                addRequestParamsForMerlock(jSONObject, applicationContext);
            }
            JSONObject jSONObjectExecute = MusicPlaylistRequest.GetPlaylistsByIdV2.execute(jSONObject);
            if (jSONObjectExecute == null) {
                Log.error(TAG, "GetPlaylistsByIdV2 response is null");
                return null;
            }
            PlaylistErrorValidatorKt.validatePlaylistErrors(jSONObjectExecute, playlistKey);
            JSONArray jSONArray2 = jSONObjectExecute.getJSONArray("playlists");
            if (jSONArray2 != null && jSONArray2.getJSONObject(0) != null) {
                JSONObject jSONObject2 = jSONArray2.getJSONObject(0);
                String homeMarketPlaceId = AccountDetailUtil.get().getHomeMarketPlaceId();
                EntityPlaylist.Companion companion = EntityPlaylist.INSTANCE;
                if (companion.isEntityPlaylist(jSONObject2)) {
                    catalogPlaylistFromJSON = companion.fromJSON(playlistKey, homeMarketPlaceId, jSONObject2);
                } else {
                    catalogPlaylistFromJSON = SharedUserPlaylist.fromJSON(playlistKey, homeMarketPlaceId, jSONObject2);
                }
                return catalogPlaylistFromJSON;
            }
            Log.error(TAG, "GetPlaylistsByIdV2 response is empty");
            return null;
        } catch (JSONException e) {
            Log.error(TAG, e.getMessage(), e);
            return null;
        }
    }

    public CatalogPlaylist getEntityPlaylistBySeed(final String entityId, final PlaylistSeedEntityType entityType) throws ServiceException, AbstractHttpClient.FailedException, AbstractHttpClient.CanceledException, AbstractHttpClient.IncompleteResultException, AbstractHttpClient.UnexpectedHttpStatusException {
        try {
            String modelEntityType = getModelEntityType(entityType);
            String entityIdFieldName = getEntityIdFieldName(entityType);
            if (modelEntityType != null && entityIdFieldName != null) {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put(entityIdFieldName, entityId);
                jSONObject.put("__type", modelEntityType);
                JSONObject jSONObject2 = new JSONObject();
                jSONObject2.put("seedEntity", jSONObject);
                jSONObject2.put("featureSet", getFeatureSet(false));
                jSONObject2.put(Splash.PARAMS_MUSIC_TERRITORY, AccountDetailUtil.getMusicTerritoryOfResidence());
                if (UserSubscriptionUtil.getUserSubscription().isKatana()) {
                    jSONObject2.put(ContextMappingConstants.CONTENT_ENCODING, true);
                }
                JSONObject jSONObjectExecute = MusicPlaylistRequest.GetPlaylistBySeedEntity.execute(jSONObject2);
                if (jSONObjectExecute == null) {
                    Log.error(TAG, "GetPlaylistBySeedEntity response is null");
                    return null;
                }
                JSONObject jSONObject3 = jSONObjectExecute.getJSONObject(Environment.PLAYLIST_PATH);
                if (jSONObject3 != null) {
                    return EntityPlaylist.INSTANCE.fromJSON(jSONObject3);
                }
                Log.error(TAG, "GetPlaylistBySeedEntity response is empty");
                return null;
            }
            Log.error(TAG, "Unsupported entity type provided");
            return null;
        } catch (JSONException e) {
            Log.error(TAG, e.getMessage(), e);
            return null;
        }
    }

    public void addRequestParamsForMerlock(JSONObject requestParams, Context context) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("hasExplicitLanguage", !AmazonApplication.getCapabilities().isExplicitLanguageFilterEnabled());
        requestParams.put("allowedParentalControls", jSONObject);
        requestParams.put("musicRequestIdentityContextToken", ChildUserUtil.INSTANCE.getMusicRequestIdentityContextToken(context));
    }

    public String followPlaylistByAsin(Context context, String playlistAsin) throws ServiceException, AbstractHttpClient.FailedException, AbstractHttpClient.CanceledException, AbstractHttpClient.IncompleteResultException, AbstractHttpClient.UnexpectedHttpStatusException {
        try {
            JSONObject jSONObject = new JSONObject();
            String homeMarketPlaceId = AccountDetailUtil.get().getHomeMarketPlaceId();
            jSONObject.put("asin", playlistAsin).put("marketplaceId", homeMarketPlaceId).put("contentSubscriptionMode", UserSubscriptionUtil.getSubscription().requests().requestedContentSubscriptionModeForMuPS());
            if (ChildUserUtil.INSTANCE.isChildUser(context)) {
                jSONObject.put("featureSet", getFeatureSetForAddPlaylist());
            }
            String string = MusicPlaylistRequest.AddPlaylistToLibraryByAsin.execute(jSONObject).getString("playlistId");
            String str = TAG;
            Object[] objArr = new Object[3];
            objArr[0] = playlistAsin;
            objArr[1] = homeMarketPlaceId;
            objArr[2] = string == null ? "null" : string;
            Log.verbose(str, "playlistAsin = %s, pfm = %s, playlistId = %s", objArr);
            return string;
        } catch (JSONException e) {
            Log.error(TAG, e.getMessage());
            return null;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x0045  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean appendTracksToPlaylist(android.content.Context r11, android.net.Uri r12, java.util.Collection<com.amazon.mp3.playlist.api.track.PlaylistServiceTrack> r13) throws com.amazon.mp3.net.service.ServiceException, com.amazon.mp3.net.AbstractHttpClient.HttpClientException {
        /*
            r10 = this;
            android.database.sqlite.SQLiteDatabase r0 = com.amazon.mp3.library.provider.source.cirrus.CirrusDatabase.getReadOnlyDatabase(r11)
            java.lang.String r8 = "luid"
            java.lang.String r9 = "version"
            java.lang.String[] r2 = new java.lang.String[]{r8, r9}
            r1 = 1
            java.lang.String[] r4 = new java.lang.String[r1]
            long r5 = com.amazon.mp3.library.util.PlaylistUtil.getPlaylistId(r12)
            java.lang.String r12 = java.lang.String.valueOf(r5)
            r1 = 0
            r4[r1] = r12
            java.lang.String r1 = "Playlist"
            java.lang.String r3 = "_id=?"
            r5 = 0
            r6 = 0
            r7 = 0
            android.database.Cursor r12 = r0.query(r1, r2, r3, r4, r5, r6, r7)
            r0 = 0
            if (r12 == 0) goto L45
            boolean r1 = r12.moveToFirst()     // Catch: java.lang.Throwable -> L40
            if (r1 == 0) goto L45
            int r0 = r12.getColumnIndexOrThrow(r8)     // Catch: java.lang.Throwable -> L40
            java.lang.String r0 = r12.getString(r0)     // Catch: java.lang.Throwable -> L40
            int r1 = r12.getColumnIndexOrThrow(r9)     // Catch: java.lang.Throwable -> L40
            java.lang.String r1 = r12.getString(r1)     // Catch: java.lang.Throwable -> L40
            goto L46
        L40:
            r11 = move-exception
            com.amazon.mp3.util.DbUtil.closeCursor(r12)
            throw r11
        L45:
            r1 = r0
        L46:
            com.amazon.mp3.util.DbUtil.closeCursor(r12)
            boolean r11 = r10.appendTracksToPlaylist(r11, r0, r1, r13)
            return r11
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.mp3.playlist.api.MC2PlaylistApi.appendTracksToPlaylist(android.content.Context, android.net.Uri, java.util.Collection):boolean");
    }

    public JSONArray getFeatureSet(boolean isOmitEntityFeatureNeeded) {
        JSONArray jSONArray = new JSONArray();
        jSONArray.put("SUPPORT_MIXED_ID_TYPES");
        if (AmazonApplication.getCapabilities().isEntityPlaylistSupported() && isOmitEntityFeatureNeeded) {
            jSONArray.put("ENTITY_PLAYLISTS");
        }
        if (Feature.playlists_sync_include_follow_count.isEnabled()) {
            jSONArray.put("INCLUDE_FOLLOWER_COUNT");
        }
        return jSONArray;
    }

    private boolean appendTracksToPlaylist(final Context context, final String playlistLuid, final String version, final Collection<PlaylistServiceTrack> tracks) throws ServiceException, AbstractHttpClient.HttpClientException {
        return appendTracksToPlaylist(context, playlistLuid, version, tracks, null);
    }

    private boolean appendTracksToPlaylist(final Context context, final String playlistLuid, final String version, final Collection<PlaylistServiceTrack> tracks, final PlaylistUnificationCallback unificationCallback) throws ServiceException, AbstractHttpClient.HttpClientException {
        if (playlistLuid == null) {
            throw new RuntimeException("unable to resolve playlist LUID");
        }
        try {
            JSONObject jSONObjectPut = new JSONObject().put("playlistId", playlistLuid).put("version", version);
            if (ChildUserUtil.INSTANCE.isChildUser(context)) {
                jSONObjectPut.put("featureSet", getFeatureSet(true));
            } else {
                jSONObjectPut.put("featureSet", getFeatureSet(false));
            }
            JSONArray jSONArray = new JSONArray();
            Iterator<PlaylistServiceTrack> it = tracks.iterator();
            while (it.hasNext()) {
                jSONArray.put(it.next().getJSON());
            }
            jSONObjectPut.put("tracks", jSONArray);
            Log.verbose(TAG, "new track appended to playlist {%s}: v = %s", playlistLuid, MusicPlaylistRequest.AppendTracksToPlaylist.execute(jSONObjectPut).getString("version"));
            HashSet hashSet = new HashSet();
            hashSet.add(playlistLuid);
            PlaylistSyncHelper.syncUpdatedPlaylist(context, hashSet, unificationCallback);
            new EditPlaylistMetricsLogger(context).sendOnlinePlaylistEdited();
            SyncService.startSync(context, 19);
            return true;
        } catch (ServiceException e) {
            Log.error(TAG, "serviceException to AppendTracksToPlaylist for playlist " + playlistLuid, e);
            return false;
        } catch (JSONException e2) {
            Log.error(TAG, "JSONException parsing AppendTracksToPlaylist response!", e2);
            return false;
        }
    }

    private void validatePlaylistResponse(JSONObject response) throws JSONException, MusicPlaylistExceptions.InvalidCheckpointException {
        if (response.optString("__type").equals("com.amazon.musicplaylist.model#InvalidCheckpointException")) {
            throw new MusicPlaylistExceptions.InvalidCheckpointException();
        }
    }

    /* JADX INFO: renamed from: com.amazon.mp3.playlist.api.MC2PlaylistApi$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$mp3$playlist$PlaylistSeedEntityType;

        static {
            int[] iArr = new int[PlaylistSeedEntityType.values().length];
            $SwitchMap$com$amazon$mp3$playlist$PlaylistSeedEntityType = iArr;
            try {
                iArr[PlaylistSeedEntityType.TRACK.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$mp3$playlist$PlaylistSeedEntityType[PlaylistSeedEntityType.ALBUM.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$mp3$playlist$PlaylistSeedEntityType[PlaylistSeedEntityType.ARTIST.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$amazon$mp3$playlist$PlaylistSeedEntityType[PlaylistSeedEntityType.PLAYLIST.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$amazon$mp3$playlist$PlaylistSeedEntityType[PlaylistSeedEntityType.GENRE.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
        }
    }

    private static String getModelEntityType(final PlaylistSeedEntityType entityType) {
        int i = AnonymousClass1.$SwitchMap$com$amazon$mp3$playlist$PlaylistSeedEntityType[entityType.ordinal()];
        if (i == 1) {
            return "com.amazon.musicplaylist.model#TrackSeedEntity";
        }
        if (i == 2) {
            return "com.amazon.musicplaylist.model#AlbumSeedEntity";
        }
        if (i == 3) {
            return "com.amazon.musicplaylist.model#ArtistSeedEntity";
        }
        if (i == 4) {
            return "com.amazon.musicplaylist.model#PlaylistSeedEntity";
        }
        if (i != 5) {
            return null;
        }
        return "com.amazon.musicplaylist.model#GenreSeedEntity";
    }

    private static String getEntityIdFieldName(PlaylistSeedEntityType entityType) {
        int i = AnonymousClass1.$SwitchMap$com$amazon$mp3$playlist$PlaylistSeedEntityType[entityType.ordinal()];
        if (i == 1 || i == 2 || i == 3) {
            return "asin";
        }
        if (i == 4) {
            return "id";
        }
        if (i != 5) {
            return null;
        }
        return "tagId";
    }
}
