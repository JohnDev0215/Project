package com.amazon.mp3.prime;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.text.TextUtils;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.activity.settings.SettingsUtil;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.download.ImageCacheUri;
import com.amazon.mp3.download.MusicDownloader;
import com.amazon.mp3.library.cache.image.loader.ImageLoaderFactory;
import com.amazon.mp3.library.provider.MediaProvider;
import com.amazon.mp3.library.provider.source.cirrus.CirrusDatabase;
import com.amazon.mp3.library.provider.source.cirrus.CirrusDatabaseUtil;
import com.amazon.mp3.library.provider.source.cirrus.CirrusMediaSource;
import com.amazon.mp3.library.service.sync.UnreferencedPrimePlaylistTracksHelper;
import com.amazon.mp3.net.AbstractHttpClient;
import com.amazon.mp3.net.service.ServiceException;
import com.amazon.mp3.playlist.CatalogPlaylist;
import com.amazon.mp3.playlist.CatalogPlaylistTrack;
import com.amazon.mp3.playlist.PlaylistSeedEntityType;
import com.amazon.mp3.playlist.api.MC2PlaylistApi;
import com.amazon.mp3.playlist.db.PrimePlaylistDatabase;
import com.amazon.mp3.playlist.db.PrimePlaylistDatabaseManager;
import com.amazon.mp3.util.BauhausToastUtils;
import com.amazon.mp3.util.ContentAccessUtil;
import com.amazon.mp3.util.DbUtil;
import com.amazon.mp3.util.EligibilityUtil;
import com.amazon.mp3.util.Log;
import com.amazon.music.downloads.notification.NotificationInfo;
import com.amazon.music.find.utils.PageUriUtils;
import com.amazon.music.platform.playback.capabilities.UpsellReason;
import com.google.android.gms.cast.MediaTrack;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;

/* JADX INFO: loaded from: classes4.dex */
public class PrimePlaylistUtil {
    private static final String TAG = "PrimePlaylistUtil";
    private static final Pattern asinRegex = Pattern.compile("^(([A-Za-z0-9]{10})|(PP:?([A-Z0-9]){8}-?([A-Z0-9]){4}-?([A-Z0-9]){4}-?([A-Z0-9]){4}-?([A-Z0-9]){12}))$");

    public static void removeAllPrimePlaylists(Context context, boolean fromCirrus) {
        String str;
        String str2;
        String str3;
        HashSet hashSet;
        Cursor cursorQuery;
        String str4;
        String str5;
        Throwable th;
        Throwable th2;
        String str6;
        StringBuilder sb;
        Log.info(TAG, "delete prime playlists from local database");
        SQLiteDatabase writableDatabase = CirrusDatabase.getWritableDatabase(context);
        writableDatabase.beginTransaction();
        HashSet hashSet2 = new HashSet();
        if (fromCirrus) {
            try {
                str2 = "Failed to close cursor for : ";
                str3 = "Failed to close database : ";
                str = "DELETE FROM PrimePlaylists";
                hashSet = hashSet2;
                try {
                    cursorQuery = writableDatabase.query(true, "PrimePlaylists", new String[]{"luid"}, "is_following = 1", null, null, null, null, null);
                } catch (Throwable th3) {
                    th = th3;
                    str5 = str2;
                    str4 = str3;
                    cursorQuery = null;
                    try {
                        Log.error(TAG, "Error while removing prime Playlists due to: " + th.getMessage());
                        try {
                            cursorQuery.close();
                        } catch (Throwable th4) {
                            Log.error(TAG, str5 + th4.getMessage());
                        }
                        try {
                            writableDatabase.endTransaction();
                        } catch (Throwable th5) {
                            th2 = th5;
                            str6 = TAG;
                            sb = new StringBuilder();
                            sb.append(str4);
                            sb.append(th2.getMessage());
                            Log.error(str6, sb.toString());
                        }
                    } finally {
                    }
                }
            } catch (Throwable th6) {
                th = th6;
                hashSet = hashSet2;
                str5 = "Failed to close cursor for : ";
                str4 = "Failed to close database : ";
            }
            try {
                int columnIndex = cursorQuery.getColumnIndex("luid");
                while (cursorQuery.moveToNext()) {
                    hashSet.add(cursorQuery.getString(columnIndex));
                }
            } catch (Throwable th7) {
                th = th7;
                str5 = str2;
                str4 = str3;
                Log.error(TAG, "Error while removing prime Playlists due to: " + th.getMessage());
                cursorQuery.close();
                writableDatabase.endTransaction();
            }
        } else {
            str = "DELETE FROM PrimePlaylists";
            str2 = "Failed to close cursor for : ";
            str3 = "Failed to close database : ";
            hashSet = hashSet2;
            cursorQuery = null;
        }
        writableDatabase.execSQL("DELETE FROM PrimePlaylistsCacheTimes");
        writableDatabase.execSQL("DELETE FROM PrimePlaylistToTrack");
        writableDatabase.execSQL("DELETE FROM PrimePlaylistTracks");
        writableDatabase.execSQL(str);
        writableDatabase.setTransactionSuccessful();
        try {
            cursorQuery.close();
        } catch (Throwable th8) {
            Log.error(TAG, str2 + th8.getMessage());
        }
        try {
            writableDatabase.endTransaction();
        } catch (Throwable th9) {
            th2 = th9;
            str6 = TAG;
            sb = new StringBuilder();
            str4 = str3;
            sb.append(str4);
            sb.append(th2.getMessage());
            Log.error(str6, sb.toString());
        }
        if (fromCirrus) {
            unfollowPrimePlaylistsFromServer(context, hashSet);
        }
        context.getContentResolver().notifyChange(CirrusMediaSource.NOTIFICATION_URI, null);
        CirrusDatabaseUtil.updateAllCachedTrackCounts(context);
    }

    private static boolean unfollowPrimePlaylistsFromServer(final Context context, Set<String> luids) {
        try {
            if (new MC2PlaylistApi().deletePlaylists(context, new ArrayList(luids))) {
                return true;
            }
            mergeLuidsBackToCache(context, luids);
            return false;
        } catch (Exception e) {
            Log.error(TAG, "Unfollow All Prime Playlists failed with exception: " + e.getMessage());
            return false;
        }
    }

    public static CatalogPlaylist followPrimePlaylist(final Context context, final PrimePlaylistDatabase db, final int source, final String primePlaylistAsin) throws ServiceException, AbstractHttpClient.FailedException, AbstractHttpClient.CanceledException, AbstractHttpClient.IncompleteResultException, AbstractHttpClient.UnexpectedHttpStatusException {
        return followPrimePlaylist(context, db, source, primePlaylistAsin, true);
    }

    public static CatalogPlaylist followPrimePlaylist(final Context context, final PrimePlaylistDatabase db, final int source, final String primePlaylistAsin, final boolean shouldShowSuccessfulUserInterface) throws ServiceException, AbstractHttpClient.FailedException, AbstractHttpClient.CanceledException, AbstractHttpClient.IncompleteResultException, AbstractHttpClient.UnexpectedHttpStatusException {
        String strFollowPlaylistByAsin = new MC2PlaylistApi().followPlaylistByAsin(context, primePlaylistAsin);
        CatalogPlaylist catalogPlaylistFetchPrimePlaylist = fetchPrimePlaylist(db, source, primePlaylistAsin);
        db.follow(primePlaylistAsin, strFollowPlaylistByAsin);
        String strConvertToSourceId = MediaProvider.CirrusBaseColumns.Source.convertToSourceId(source);
        ContentResolver contentResolver = context.getContentResolver();
        contentResolver.notifyChange(MediaProvider.Playlists.getFilterContentUri(strConvertToSourceId, ""), null);
        contentResolver.notifyChange(MediaProvider.Playlists.getContentUri(strConvertToSourceId), null);
        if (shouldShowSuccessfulUserInterface) {
            showSuccessfulFollowPrimePlaylistToast(context);
        }
        Log.debug(TAG, "Successfully added %s with %s", primePlaylistAsin, strFollowPlaylistByAsin);
        return catalogPlaylistFetchPrimePlaylist;
    }

    static void showSuccessfulFollowPrimePlaylistToast(final Context context) {
        BauhausToastUtils.showDefaultToast(context, context.getString(R.string.dmusic_follow_toast_message), 0);
    }

    public static CatalogPlaylist fetchPrimePlaylist(final PrimePlaylistDatabase db, final int source, final String primePlaylistAsin) throws ServiceException, AbstractHttpClient.FailedException, AbstractHttpClient.CanceledException, AbstractHttpClient.IncompleteResultException, AbstractHttpClient.UnexpectedHttpStatusException {
        return fetchPrimePlaylist(db, source, primePlaylistAsin, new MC2PlaylistApi());
    }

    protected static CatalogPlaylist fetchPrimePlaylist(final PrimePlaylistDatabase db, final int source, final String primePlaylistAsin, final MC2PlaylistApi api) throws ServiceException, AbstractHttpClient.FailedException, AbstractHttpClient.CanceledException, AbstractHttpClient.IncompleteResultException, AbstractHttpClient.UnexpectedHttpStatusException {
        CatalogPlaylist playlistByKey;
        CatalogPlaylist catalogPlaylistCheckCacheAndGetPlaylist = checkCacheAndGetPlaylist(db, primePlaylistAsin, source);
        if (catalogPlaylistCheckCacheAndGetPlaylist == null) {
            if (isAsin(primePlaylistAsin)) {
                playlistByKey = api.getPrimePlaylistByAsin(primePlaylistAsin);
            } else {
                playlistByKey = api.getPlaylistByKey(primePlaylistAsin);
            }
            catalogPlaylistCheckCacheAndGetPlaylist = playlistByKey;
            if (catalogPlaylistCheckCacheAndGetPlaylist == null) {
                throw new AbstractHttpClient.IncompleteResultException();
            }
            CatalogPlaylist playlistByLuid = db.getPlaylistByLuid(catalogPlaylistCheckCacheAndGetPlaylist.getLuid(), source);
            if (playlistByLuid != null) {
                if (source == 1) {
                    Log.debug(TAG, "Requested local CatalogPlaylist with asin " + primePlaylistAsin + " already saved in library with different asin.  Returning cached data with luid %s, asin %s, version %s, and followed state %s", playlistByLuid.getLuid(), playlistByLuid.getAsin(), playlistByLuid.getVersion(), Boolean.valueOf(playlistByLuid.isFollowed()));
                    return playlistByLuid;
                }
                if (!catalogPlaylistCheckCacheAndGetPlaylist.isFollowed()) {
                    catalogPlaylistCheckCacheAndGetPlaylist.setFollowed(playlistByLuid.isFollowed());
                }
                if (TextUtils.isEmpty(catalogPlaylistCheckCacheAndGetPlaylist.getVersion())) {
                    catalogPlaylistCheckCacheAndGetPlaylist.setVersion(playlistByLuid.getVersion());
                }
            }
            Log.debug(TAG, "Save Prime Playlist to db with luid %s asin %s, version %s, and followed state %s", catalogPlaylistCheckCacheAndGetPlaylist.getLuid(), primePlaylistAsin, catalogPlaylistCheckCacheAndGetPlaylist.getVersion(), Boolean.valueOf(catalogPlaylistCheckCacheAndGetPlaylist.isFollowed()));
            db.save(catalogPlaylistCheckCacheAndGetPlaylist, source, true);
            db.markForExpiry(primePlaylistAsin);
        } else {
            Log.debug(TAG, "Prime Playlist already in db with asin: " + primePlaylistAsin);
        }
        return catalogPlaylistCheckCacheAndGetPlaylist;
    }

    private static CatalogPlaylist checkCacheAndGetPlaylist(final PrimePlaylistDatabase db, final String primePlaylistAsin, final int source) {
        CatalogPlaylist playlist = db.getPlaylist(primePlaylistAsin, source);
        if (playlist == null) {
            return null;
        }
        if (playlist.isFollowed() || db.cacheContainsPrimePlaylist(playlist.getAsin())) {
            return playlist;
        }
        return null;
    }

    public static CatalogPlaylist fetchSharedUserPlaylist(PrimePlaylistDatabase db, int source, String playlistKey) throws ServiceException, AbstractHttpClient.FailedException, AbstractHttpClient.CanceledException, AbstractHttpClient.IncompleteResultException, AbstractHttpClient.UnexpectedHttpStatusException {
        MC2PlaylistApi mC2PlaylistApi = new MC2PlaylistApi();
        CatalogPlaylist playlist = db.getPlaylist(playlistKey, source);
        if (playlist == null) {
            playlist = mC2PlaylistApi.getPlaylistByKey(playlistKey);
            if (playlist == null) {
                throw new AbstractHttpClient.IncompleteResultException();
            }
            if (!playlistKey.equals(playlist.getAsin())) {
                Log.warning(TAG, "fetchSharedUserPlaylist has different asin=" + playlist.getAsin());
                playlist.setAsin(playlistKey);
            }
            db.save(playlist, source, true);
            db.markForExpiry(playlistKey);
        }
        return playlist;
    }

    public static CatalogPlaylist fetchEntityPlaylist(PrimePlaylistDatabase db, int source, String entityId, PlaylistSeedEntityType entityType) throws ServiceException, AbstractHttpClient.FailedException, AbstractHttpClient.CanceledException, AbstractHttpClient.IncompleteResultException, AbstractHttpClient.UnexpectedHttpStatusException {
        return fetchEntityPlaylist(db, source, entityId, entityType, new MC2PlaylistApi());
    }

    public static CatalogPlaylist fetchEntityPlaylist(PrimePlaylistDatabase db, int source, String entityId, PlaylistSeedEntityType entityType, MC2PlaylistApi api) throws ServiceException, AbstractHttpClient.FailedException, AbstractHttpClient.CanceledException, AbstractHttpClient.IncompleteResultException, AbstractHttpClient.UnexpectedHttpStatusException {
        CatalogPlaylist entityPlaylistBySeed = api.getEntityPlaylistBySeed(entityId, entityType);
        if (entityPlaylistBySeed == null) {
            throw new AbstractHttpClient.IncompleteResultException();
        }
        db.save(entityPlaylistBySeed, source, true);
        db.markForExpiry(entityPlaylistBySeed.getAsin());
        return entityPlaylistBySeed;
    }

    public static boolean retryFailedUnfollowPrimePlaylists(final Context context) {
        Set<String> andClearCachedUnfollowLuids = getAndClearCachedUnfollowLuids(context);
        if (andClearCachedUnfollowLuids == null || andClearCachedUnfollowLuids.isEmpty()) {
            return true;
        }
        return unfollowPrimePlaylistsFromServer(context, andClearCachedUnfollowLuids);
    }

    private static synchronized Set<String> getAndClearCachedUnfollowLuids(final Context context) {
        SharedPreferences prefs = SettingsUtil.getPrefs(context);
        String primeCachedUnfollowLuidsKey = getPrimeCachedUnfollowLuidsKey(context);
        Set<String> stringSet = prefs.getStringSet(primeCachedUnfollowLuidsKey, null);
        if (stringSet != null && !stringSet.isEmpty()) {
            SharedPreferences.Editor editorEdit = prefs.edit();
            editorEdit.remove(primeCachedUnfollowLuidsKey);
            editorEdit.apply();
            return stringSet;
        }
        return null;
    }

    private static String getPrimeCachedUnfollowLuidsKey(final Context context) {
        return context.getString(R.string.setting_key_prime_cached_unfollow_luids_key);
    }

    private static synchronized void mergeLuidsBackToCache(final Context context, Set<String> luids) {
        if (luids != null) {
            if (!luids.isEmpty()) {
                Set<String> andClearCachedUnfollowLuids = getAndClearCachedUnfollowLuids(context);
                if (andClearCachedUnfollowLuids != null && !andClearCachedUnfollowLuids.isEmpty()) {
                    luids.addAll(andClearCachedUnfollowLuids);
                }
                String primeCachedUnfollowLuidsKey = getPrimeCachedUnfollowLuidsKey(context);
                SharedPreferences.Editor editorEdit = SettingsUtil.getPrefs(context).edit();
                editorEdit.putStringSet(primeCachedUnfollowLuidsKey, luids);
                editorEdit.apply();
            }
        }
    }

    public static Uri createOrUpdateLocalPrimePlaylist(final Context context, final Uri playlistUri) {
        Uri contentUri;
        Uri contentUri2;
        String catalogPlaylistId = MediaProvider.Playlists.getCatalogPlaylistId(playlistUri);
        CatalogPlaylist playlist = getPlaylist(catalogPlaylistId, "cirrus");
        String str = "localPrimePlaylist-" + playlist.getLuid();
        ContentValues contentValues = new ContentValues();
        contentValues.put("luid", str);
        contentValues.put("asin", catalogPlaylistId);
        contentValues.put("version", playlist.getVersion());
        contentValues.put("title", playlist.getTitle());
        contentValues.put(MediaTrack.ROLE_DESCRIPTION, playlist.getDescription());
        contentValues.put(PageUriUtils.SOURCE_QUERY_PARAM, (Integer) 1);
        contentValues.put("is_following", Boolean.valueOf(playlist.isFollowed()));
        contentValues.put("art_url", playlist.getBannerArtUrl());
        contentValues.put("thumbnail_art_url", playlist.getThumbnailArtUrl());
        contentValues.put("is_prime", Boolean.valueOf(playlist.isPrime()));
        contentValues.put("is_nightwing", Boolean.valueOf(playlist.isFree()));
        contentValues.put("is_nightwing_on_demand", Boolean.valueOf(playlist.isFreeOnDemand()));
        contentValues.put("is_sonic_rush", Boolean.valueOf(playlist.isSonicRush()));
        contentValues.put("is_sonic_rush_on_demand", Boolean.valueOf(playlist.isSonicRushOnDemand()));
        contentValues.put("is_sonic_rush_golden", Boolean.valueOf(playlist.isSonicRushGolden()));
        if (MediaProvider.SharedUserPlaylists.isSharedUserPlaylist(playlistUri)) {
            contentValues.put("is_shared_user_playlist", Boolean.valueOf(playlist.isSharedUserPlaylist()));
            contentUri = MediaProvider.SharedUserPlaylists.getContentUri("cirrus-local");
            contentUri2 = MediaProvider.SharedUserPlaylists.getContentUri("cirrus-local", catalogPlaylistId);
        } else {
            contentUri = MediaProvider.PrimePlaylists.getContentUri("cirrus-local");
            contentUri2 = MediaProvider.PrimePlaylists.getContentUri("cirrus-local", catalogPlaylistId);
        }
        Log.debug(TAG, "Create or update Local Playlist in PrimePlaylist table: " + catalogPlaylistId);
        insertOrUpdateLocalPrimePlaylist(context, contentUri, str, contentValues);
        return contentUri2;
    }

    private static void insertOrUpdateLocalPrimePlaylist(final Context context, final Uri contentUri, final String localLuid, final ContentValues values) {
        ContentResolver contentResolver = context.getContentResolver();
        long playlistIdByCloudId = new PrimePlaylistDatabaseManager().getPlaylistIdByCloudId(localLuid);
        if (playlistIdByCloudId != -1) {
            String str = TAG;
            Log.debug(str, "Playlist already exists, updating it: " + localLuid);
            Log.debug(str, String.format(" %s rows were updated on updating prime playlist: %s", Integer.valueOf(contentResolver.update(contentUri, values, "_id=?", new String[]{String.valueOf(playlistIdByCloudId)})), localLuid));
            return;
        }
        contentResolver.insert(contentUri, values);
    }

    public static void updateLocalPrimePlaylistVersion(Context context, String primePlaylistLuid, String version) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("version", version);
        CirrusDatabase.getWritableDatabase(context).update("PrimePlaylists", contentValues, "luid = ?", new String[]{"localPrimePlaylist-" + primePlaylistLuid});
        Log.debug(TAG, "Updated PrimePlaylistsTable for Offline playlist - minor version update: " + primePlaylistLuid);
    }

    public static CatalogPlaylist getPlaylist(String asin, String source) {
        return new PrimePlaylistDatabaseManager().getPlaylist(asin, PrimePlaylistsTable.translateSource(source));
    }

    public static void deleteUnreferencedPrimePlaylistTracks(Context context) {
        new UnreferencedPrimePlaylistTracksHelper().deleteUnreferencedPrimePlaylistTracks(context, CirrusDatabase.getWritableDatabase(context));
    }

    public static void cancelPrimePlaylistDownload(Context context, final String asin) {
        Uri contentUri = MediaProvider.PrimePlaylists.Tracks.getContentUri("cirrus", asin);
        MusicDownloader.getInstance(context).cancel(contentUri.toString());
        Log.verbose(TAG, "Cancelling the whole prime playlist download: %s", contentUri.toString());
    }

    public static void deleteOfflinePrimePlaylist(Context context, String localPrimePlaylistLuid) {
        deleteOfflinePrimePlaylist(context, localPrimePlaylistLuid, null);
    }

    public static void deleteOfflinePrimePlaylist(Context context, String localPrimePlaylistLuid, List<String> trackAsins) {
        if (!TextUtils.isEmpty(localPrimePlaylistLuid)) {
            PrimePlaylistDatabaseManager primePlaylistDatabaseManager = new PrimePlaylistDatabaseManager(context);
            primePlaylistDatabaseManager.delete(primePlaylistDatabaseManager.getPlaylistIdByCloudId(localPrimePlaylistLuid));
            long playlistIdByCloudId = primePlaylistDatabaseManager.getPlaylistIdByCloudId(localPrimePlaylistLuid.replace("localPrimePlaylist-", ""));
            resetPrimePlaylistDownloadState(context, playlistIdByCloudId);
            deleteUnreferencedPrimePlaylistTracks(context);
            resetPrimePlaylistTracksDownloadState(context, playlistIdByCloudId, trackAsins);
            SettingsUtil.setHasOfflinePrimePlaylists(context, primePlaylistDatabaseManager.hasPrimePlaylists("cirrus-local"));
            Log.verbose(TAG, "removing offline prime playlist: %s", localPrimePlaylistLuid);
            context.getContentResolver().notifyChange(CirrusMediaSource.NOTIFICATION_URI_LOCAL, null);
            return;
        }
        throw new IllegalStateException("prime playlist luid null or empty!");
    }

    private static void resetPrimePlaylistDownloadState(Context context, final long playlistId) {
        SQLiteDatabase writableDatabase = CirrusDatabase.getWritableDatabase(context);
        String[] strArr = {String.valueOf(playlistId)};
        ContentValues contentValues = new ContentValues();
        contentValues.put("download_state", (Integer) 5);
        writableDatabase.update("PrimePlaylists", contentValues, "_id = ?", strArr);
    }

    private static void resetPrimePlaylistTracksDownloadState(Context context, final long cloudPlaylistId, List<String> trackAsins) {
        ArrayList arrayList = new ArrayList();
        if (trackAsins == null) {
            PrimePlaylistDatabaseManager primePlaylistDatabaseManager = new PrimePlaylistDatabaseManager();
            Iterator<CatalogPlaylistTrack> it = primePlaylistDatabaseManager.getPlaylist(cloudPlaylistId, PrimePlaylistsTable.translateSource("cirrus"), (String[]) null).getTracks().iterator();
            while (it.hasNext()) {
                String asin = it.next().getAsin();
                if (primePlaylistDatabaseManager.getTrack(asin, 1) == null) {
                    arrayList.add(asin);
                }
            }
            trackAsins = arrayList;
        }
        if (trackAsins.isEmpty()) {
            return;
        }
        SQLiteDatabase writableDatabase = CirrusDatabase.getWritableDatabase(context);
        ContentValues contentValues = new ContentValues();
        contentValues.put("download_state", (Integer) 5);
        DbUtil.WhereClause whereClause = new DbUtil.WhereClause("asin", trackAsins);
        writableDatabase.update("PrimePlaylistTracks", contentValues, whereClause.getClause(), whereClause.getArgs());
    }

    /* JADX WARN: Removed duplicated region for block: B:8:0x0015  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static boolean hasUnfinishedPrimePlaylistDownloads(android.content.Context r2) {
        /*
            android.database.sqlite.SQLiteDatabase r2 = com.amazon.mp3.library.provider.source.cirrus.CirrusDatabase.getReadOnlyDatabase(r2)
            r0 = 0
            java.lang.String r1 = "SELECT luid,download_state FROM PrimePlaylists WHERE download_state!=0 AND download_state!=2 AND download_state!=5 AND download_state!=-1"
            android.database.Cursor r0 = r2.rawQuery(r1, r0)     // Catch: java.lang.Throwable -> L1a
            if (r0 == 0) goto L15
            boolean r2 = r0.moveToFirst()     // Catch: java.lang.Throwable -> L1a
            if (r2 == 0) goto L15
            r2 = 1
            goto L16
        L15:
            r2 = 0
        L16:
            com.amazon.mp3.util.DbUtil.closeCursor(r0)
            return r2
        L1a:
            r2 = move-exception
            com.amazon.mp3.util.DbUtil.closeCursor(r0)
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.mp3.prime.PrimePlaylistUtil.hasUnfinishedPrimePlaylistDownloads(android.content.Context):boolean");
    }

    public static boolean isAnyPrimePlaylistTrackMissing(Context context) {
        return getNumberOfMissingPrimePlaylistTracks(context) > 0;
    }

    public static List<CatalogPlaylist> getPrimePlaylistsWithMissingTracks(Context context) {
        ArrayList arrayList = new ArrayList();
        Cursor allLocalPrimePlaylists = getAllLocalPrimePlaylists(context);
        while (allLocalPrimePlaylists != null) {
            try {
                try {
                    if (!allLocalPrimePlaylists.moveToNext()) {
                        break;
                    }
                    String string = allLocalPrimePlaylists.getString(allLocalPrimePlaylists.getColumnIndex("asin"));
                    Cursor cursorQuery = null;
                    try {
                        cursorQuery = context.getContentResolver().query(MediaProvider.PrimePlaylists.getContentUri("cirrus-local", string), null, null, null, null);
                        if (cursorQuery.moveToNext() && ContentAccessUtil.getCatalogContentUnavailableReason(AmazonApplication.getLibraryItemFactory().getTrack(cursorQuery), ContentAccessUtil.ContentAccessOperation.PLAY_OFFLINE) == ContentUnavailableReason.FILE_DOES_NOT_EXIST_ON_DISK) {
                            arrayList.add(AmazonApplication.getLibraryItemFactory().getPrimePlaylist(MediaProvider.PrimePlaylists.getContentUri("cirrus-local", string)));
                        }
                        DbUtil.closeCursor(cursorQuery);
                    } finally {
                    }
                } finally {
                    DbUtil.closeCursor(allLocalPrimePlaylists);
                }
            } catch (Exception e) {
                Log.error(TAG, e.getMessage());
            }
        }
        return arrayList;
    }

    public static List<CatalogPlaylist> getAllPrimePlaylists(final Context context) {
        ArrayList arrayList = new ArrayList();
        Cursor allLocalPrimePlaylists = getAllLocalPrimePlaylists(context);
        while (allLocalPrimePlaylists != null) {
            try {
                try {
                    if (!allLocalPrimePlaylists.moveToNext()) {
                        break;
                    }
                    String string = allLocalPrimePlaylists.getString(allLocalPrimePlaylists.getColumnIndex("asin"));
                    Cursor cursorQuery = null;
                    try {
                        cursorQuery = context.getContentResolver().query(MediaProvider.PrimePlaylists.getContentUri("cirrus-local", string), null, null, null, null);
                        if (cursorQuery != null && cursorQuery.moveToNext() && AmazonApplication.getLibraryItemFactory().getTrack(cursorQuery).getDownloadState() != 5) {
                            arrayList.add(AmazonApplication.getLibraryItemFactory().getPrimePlaylist(MediaProvider.PrimePlaylists.getContentUri("cirrus-local", string)));
                        }
                        DbUtil.closeCursor(cursorQuery);
                    } finally {
                    }
                } finally {
                    DbUtil.closeCursor(allLocalPrimePlaylists);
                }
            } catch (Exception e) {
                Log.error(TAG, e.getMessage());
            }
        }
        return arrayList;
    }

    public static List<CatalogPlaylist> getKatanaQualityDownloadedPrimePlaylists(Context context) {
        ArrayList arrayList = new ArrayList();
        Cursor allLocalPrimePlaylists = getAllLocalPrimePlaylists(context);
        while (allLocalPrimePlaylists != null) {
            try {
                try {
                    if (!allLocalPrimePlaylists.moveToNext()) {
                        break;
                    }
                    String string = allLocalPrimePlaylists.getString(allLocalPrimePlaylists.getColumnIndex("asin"));
                    Cursor cursorQuery = null;
                    try {
                        cursorQuery = context.getContentResolver().query(MediaProvider.PrimePlaylists.getContentUri("cirrus-local", string), null, null, null, null);
                        while (true) {
                            if (cursorQuery == null || !cursorQuery.moveToNext()) {
                                break;
                            }
                            if (ContentAccessUtil.getCatalogContentUnavailableReason(AmazonApplication.getLibraryItemFactory().getTrack(cursorQuery), ContentAccessUtil.ContentAccessOperation.PLAY_OFFLINE) == ContentUnavailableReason.CUSTOMER_HAS_NO_KATANA_SUBSCRIPTION) {
                                arrayList.add(AmazonApplication.getLibraryItemFactory().getPrimePlaylist(MediaProvider.PrimePlaylists.getContentUri("cirrus-local", string)));
                                break;
                            }
                        }
                        DbUtil.closeCursor(cursorQuery);
                    } finally {
                    }
                } catch (Exception e) {
                    Log.error(TAG, e.getMessage());
                }
            } finally {
                DbUtil.closeCursor(allLocalPrimePlaylists);
            }
        }
        return arrayList;
    }

    public static Cursor getAllLocalPrimePlaylists(Context context) {
        return context.getContentResolver().query(MediaProvider.PrimePlaylists.getContentUri("cirrus-local"), null, null, null, null);
    }

    public static int getNumberOfMissingPrimePlaylistTracks(final Context context) {
        Iterator<CatalogPlaylist> it = getPrimePlaylistsWithMissingTracks(context).iterator();
        int trackCount = 0;
        while (it.hasNext()) {
            trackCount += it.next().getTrackCount();
        }
        Log.debug(TAG, trackCount + " missing Prime Playlist Tracks found.");
        return trackCount;
    }

    public static void downloadPrimePlaylist(final Context context, final CatalogPlaylist catalogPlaylist, final boolean userInitiated, final boolean forceRefresh) {
        String asin = catalogPlaylist.getAsin();
        UpsellReason upsell = new EligibilityUtil().getUpsell(catalogPlaylist);
        if (upsell == UpsellReason.NotDownloadable) {
            Log.error(TAG, "Cannot initiate download of Catalog playlist asin" + asin + " reason: " + upsell);
            return;
        }
        Uri contentUri = MediaProvider.PrimePlaylists.Tracks.getContentUri("cirrus", asin);
        MusicDownloader.getInstance(context).download(contentUri.toString(), contentUri, new NotificationInfo(catalogPlaylist.getTitle(), null, ImageCacheUri.generateUri(ImageLoaderFactory.ItemType.PRIME_PLAYLIST_THUMBNAIL.intValue(), asin).toString()), false, userInitiated, null, forceRefresh);
    }

    public static boolean getExistingFollowedState(final PrimePlaylistDatabase db, final String luid, final int sourceId) {
        CatalogPlaylist playlistByLuid = db.getPlaylistByLuid(luid, sourceId);
        if (playlistByLuid != null) {
            return playlistByLuid.isFollowed();
        }
        return false;
    }

    public static boolean isLocalPrimePlaylistUpdatable(final PrimePlaylistDatabase db, final CatalogPlaylist cloudPlaylist) {
        if (cloudPlaylist == null || cloudPlaylist.getLuid() == null) {
            return false;
        }
        if (db.getPlaylistByLuid("localPrimePlaylist-" + cloudPlaylist.getLuid(), 1) == null) {
            return false;
        }
        return !cloudPlaylist.equalsPlaylist(r3);
    }

    private static String getCloudLuidFromLocalPrimePlaylist(final String localLuid) {
        return localLuid.replace("localPrimePlaylist-", "");
    }

    private static Cursor getAllLocalCatalogPlaylists(final SQLiteDatabase db) {
        return db.query("PrimePlaylists", new String[]{"asin", "luid"}, "source = 1", null, null, null, null);
    }

    public static int updateAllLocalPlaylists(final Context context, final SQLiteDatabase db, final PrimePlaylistDatabase dbHandler, final Set<String> excludePlaylistIds) {
        Log.debug(TAG, "Triggering local catalog playlists update if out of sync with cloud playlist");
        Cursor allLocalCatalogPlaylists = getAllLocalCatalogPlaylists(db);
        int i = 0;
        if (allLocalCatalogPlaylists == null) {
            return 0;
        }
        int columnIndex = allLocalCatalogPlaylists.getColumnIndex("luid");
        while (allLocalCatalogPlaylists.moveToNext()) {
            try {
                try {
                    String cloudLuidFromLocalPrimePlaylist = getCloudLuidFromLocalPrimePlaylist(allLocalCatalogPlaylists.getString(columnIndex));
                    if (excludePlaylistIds == null || !excludePlaylistIds.contains(cloudLuidFromLocalPrimePlaylist)) {
                        updateLocalPlaylists(context, cloudLuidFromLocalPrimePlaylist, dbHandler);
                        i++;
                    }
                } catch (Exception e) {
                    Log.debug(TAG, "Unable to finish syncing local playlists, exception thrown: " + e);
                }
            } finally {
                DbUtil.closeCursor(allLocalCatalogPlaylists);
            }
        }
        return i;
    }

    public static String getAsin(final PrimePlaylistDatabase db, final String luid, final int sourceId) {
        return db.getPlaylistByLuid(luid, sourceId).getAsin();
    }

    public static void updateLocalPlaylists(final Context context, final String cloudPlaylistLuid, final PrimePlaylistDatabase dbHandler) {
        updateLocalPlaylists(context, cloudPlaylistLuid, dbHandler, false);
    }

    public static void updateLocalPlaylists(final Context context, final String cloudPlaylistLuid, final PrimePlaylistDatabase dbHandler, final boolean forceRefresh) {
        try {
            CatalogPlaylist playlistByLuid = dbHandler.getPlaylistByLuid(cloudPlaylistLuid, 0);
            if (playlistByLuid != null && isLocalPrimePlaylistUpdatable(dbHandler, playlistByLuid)) {
                Log.debug(TAG, "Local catalog playlist is out of sync with the cloud copy, triggering re-download: " + cloudPlaylistLuid);
                downloadPrimePlaylist(context, playlistByLuid, false, forceRefresh);
            } else {
                Log.debug(TAG, "Local catalog playlist is uptodate: " + cloudPlaylistLuid);
            }
        } catch (Exception e) {
            Log.debug(TAG, "Unable to finish syncing local playlists, exception thrown: " + e);
        }
    }

    public static boolean isAsin(String asin) {
        return asin != null && asinRegex.matcher(asin).find();
    }
}
