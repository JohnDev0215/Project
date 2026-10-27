package com.amazon.mp3.library.util;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Handler;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.WorkerThread;
import androidx.fragment.app.FragmentActivity;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.activity.NetworkErrorDialogActivity;
import com.amazon.mp3.activity.settings.SettingsUtil;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.library.activity.AddToPlaylistActivity;
import com.amazon.mp3.library.activity.AddToPlaylistTabActivity;
import com.amazon.mp3.library.activity.LibraryActivityFactory;
import com.amazon.mp3.library.adapter.TrackListAdapter;
import com.amazon.mp3.library.cache.image.CacheManager;
import com.amazon.mp3.library.cache.image.loader.ImageLoaderFactory;
import com.amazon.mp3.library.item.DeluxeContentUtil;
import com.amazon.mp3.library.item.MusicTrack;
import com.amazon.mp3.library.item.Playlist;
import com.amazon.mp3.library.item.factory.LibraryItemFactory;
import com.amazon.mp3.library.provider.MediaProvider;
import com.amazon.mp3.library.provider.source.cirrus.CirrusDatabase;
import com.amazon.mp3.library.provider.source.cirrus.CirrusMediaSource;
import com.amazon.mp3.library.provider.source.cirrus.CirrusPlaylistScratch;
import com.amazon.mp3.library.provider.source.cirrus.dbupgrade.DatabaseUpgradeHelper;
import com.amazon.mp3.library.provider.source.cirrus.dbutils.PlaylistTrackUtil;
import com.amazon.mp3.library.service.sync.PlaylistSyncHelper;
import com.amazon.mp3.library.util.PlaylistUnificationMetrics;
import com.amazon.mp3.playlist.PlaylistConfigurationStorage;
import com.amazon.mp3.playlist.api.track.PlaylistServiceTrack;
import com.amazon.mp3.playlist.support.PlaylistPrivacyOperationSupportManager;
import com.amazon.mp3.prime.ContentOwnershipStatus;
import com.amazon.mp3.util.Clock;
import com.amazon.mp3.util.ConnectivityUtil;
import com.amazon.mp3.util.DbUtil;
import com.amazon.mp3.util.Log;
import com.amazon.mp3.util.Profiler;
import com.amazon.mp3.util.ThreadUtil;
import com.amazon.mp3.util.extensions.ActivityKt;
import com.google.android.gms.cast.MediaTrack;
import java.io.File;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;

/* JADX INFO: loaded from: classes3.dex */
public class PlaylistUtil {
    private static final String TAG = "PlaylistUtil";
    private static final String[] COLUMNS_FOR_PLAYLIST_PERSONALIZATION_KEY = {"Track.asin"};
    private static Uri sPlaylistBeingEdited = null;
    private static final String[] CREATE_UDO_PLAYLIST_PROJECTION = {"_id", "luid", "duration", "udo_playlist_track_id", "udo"};
    private static final String[] CREATE_SMART_PLAYLIST_PROJECTION = {"_id", "luid", "duration"};

    public static boolean canDownloadUnifiedPlaylist(final int downloadState) {
        return downloadState == 0 || downloadState == 4 || downloadState == 1;
    }

    public static void setPlaylistBeingEdited(Uri playlistUri) {
        sPlaylistBeingEdited = playlistUri;
    }

    public static Uri getPlaylistBeingEdited() {
        return sPlaylistBeingEdited;
    }

    public static boolean canAddTracksToPlaylist(Playlist playlist, Activity activity) {
        if (playlist.getLuid() == null) {
            return false;
        }
        if (MediaProvider.UdoPlaylists.isUdoPlaylist(playlist.getContentUri())) {
            return !(!canLikePlaylistTracks(activity, r2));
        }
        return false;
    }

    public static void refreshPlaylistArt(Uri playlistUri) {
        if (MediaProvider.Playlists.isPlaylist(playlistUri)) {
            CacheManager cacheManager = CacheManager.getInstance();
            String source = MediaProvider.getSource(playlistUri);
            boolean zIsSmartPlaylist = MediaProvider.SmartPlaylists.isSmartPlaylist(playlistUri);
            String string = Long.toString(getPlaylistId(playlistUri));
            if (zIsSmartPlaylist) {
                cacheManager.refreshArtwork(ImageLoaderFactory.ItemType.PLAYLIST_SMART, source, CacheManager.MAX_UNSCALED_SIZE, string);
                return;
            }
            ImageLoaderFactory.ItemType itemType = ImageLoaderFactory.ItemType.PLAYLIST_UDO;
            int i = CacheManager.MAX_UNSCALED_SIZE;
            cacheManager.refreshArtwork(itemType, source, i, string);
            cacheManager.refreshArtwork(ImageLoaderFactory.ItemType.PLAYLIST_UDO_BANNER, source, i, string);
            cacheManager.refreshArtwork(ImageLoaderFactory.ItemType.PLAYLIST_UDO_THUMBNAIL, source, i, string);
        }
    }

    public static Uri createLocalPlaylist(final Context context, final String sourceId, final String name, final boolean canRename, final boolean uniqueName) {
        if (!"cirrus-local".equals(sourceId)) {
            throw new IllegalArgumentException("PlaylistUtil - createUdoPlaylist - sourceId unknown: " + sourceId);
        }
        ContentResolver contentResolver = context.getContentResolver();
        if (uniqueName) {
            int i = 0;
            String str = name;
            while (!isUniqueUdoPlaylistName(context, sourceId, str)) {
                i++;
                str = name + " (" + i + ")";
                if (i > 10000) {
                    break;
                }
            }
            name = str;
        }
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("canRename", Boolean.valueOf(canRename));
        long jCurrentTimeSeconds = Clock.currentTimeSeconds();
        contentValues.put("dateCreated", Long.valueOf(jCurrentTimeSeconds));
        contentValues.put("dateLastModified", Long.valueOf(jCurrentTimeSeconds));
        return contentResolver.insert(MediaProvider.UdoPlaylists.getContentUri(sourceId), contentValues);
    }

    public static boolean allCloudPlaylistTracksDownloaded(final Uri playlistTracksContentUri) {
        if (playlistTracksContentUri == null) {
            Log.error(TAG, "playlistTracksContentUri can't be null");
            return false;
        }
        LibraryItemFactory libraryItemFactory = AmazonApplication.getLibraryItemFactory();
        Cursor cursorQuery = MediaProvider.getInstance().query(playlistTracksContentUri, libraryItemFactory.getTrackProjection(), null, null, null);
        try {
            if (cursorQuery != null) {
                if (cursorQuery.moveToFirst()) {
                    int i = 0;
                    while (!cursorQuery.isAfterLast()) {
                        MusicTrack track = libraryItemFactory.getTrack(cursorQuery);
                        if (!(track.getDownloadState() == 0)) {
                            Log.debug(TAG, "track {%s} is not downloaded in playlist [%s]", track.getAsin(), playlistTracksContentUri.toString());
                            return false;
                        }
                        i++;
                        cursorQuery.moveToNext();
                    }
                    Log.debug(TAG, "numDownloaded = %d for playlistTracks: [%s]", Integer.valueOf(i), playlistTracksContentUri.toString());
                    return i > 0;
                }
            }
            Log.warning(TAG, "No tracks found in Playlist {%s} ", playlistTracksContentUri.toString());
            return true;
        } catch (Exception e) {
            Log.error(TAG, "Failed to get allCloudPlaylistTracksDownloaded for playlist " + playlistTracksContentUri.toString(), e);
            return false;
        } finally {
            DbUtil.closeCursor(cursorQuery);
        }
    }

    public static boolean isPlaylistDownloaded(final SQLiteDatabase readOnlyDb, final String playlistName, final String contentItemType) {
        boolean z = false;
        Cursor cursorQuery = readOnlyDb.query("Playlist", new String[]{"_id", "name"}, "( download_state =? OR luid LIKE ?  ) AND type = ? AND name = ?", new String[]{Integer.toString(0), "localPlaylist%", contentItemType, playlistName}, null, null, null, null);
        if (cursorQuery != null) {
            try {
                if (cursorQuery.getCount() > 0) {
                    z = true;
                }
            } catch (Throwable th) {
                try {
                    cursorQuery.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
                throw th;
            }
        }
        if (cursorQuery != null) {
            cursorQuery.close();
        }
        return z;
    }

    public static boolean isCatalogPlaylistDownloaded(final SQLiteDatabase readOnlyDb, final String playlistAsin) {
        boolean z = false;
        Cursor cursorQuery = readOnlyDb.query("PrimePlaylists", new String[]{"_id", "title"}, "download_state =? AND asin = ?", new String[]{Integer.toString(0), playlistAsin}, null, null, null, null);
        if (cursorQuery != null) {
            try {
                if (cursorQuery.getCount() > 0) {
                    z = true;
                }
            } catch (Throwable th) {
                try {
                    cursorQuery.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
                throw th;
            }
        }
        if (cursorQuery != null) {
            cursorQuery.close();
        }
        return z;
    }

    public static boolean isUniqueUdoPlaylistName(final Context context, final String sourceId, final String name) {
        Cursor cursorQuery = context.getContentResolver().query(MediaProvider.UdoPlaylists.getContentUri(sourceId), new String[]{"name"}, "name=?", new String[]{name}, null);
        if (cursorQuery == null) {
            return false;
        }
        boolean zMoveToFirst = true ^ cursorQuery.moveToFirst();
        cursorQuery.close();
        return zMoveToFirst;
    }

    public static Uri getEditPlaylistUri(Uri playlistUri) {
        if (MediaProvider.Playlists.isPlaylist(playlistUri)) {
            return playlistUri.buildUpon().appendQueryParameter("edit", "true").build();
        }
        throw new IllegalArgumentException("specified URI is not a playlist");
    }

    public static Uri getAddToPlaylistUri(Uri playlistUri) {
        return getAddToPlaylistUri(playlistUri, false);
    }

    public static Uri getAddToPlaylistUri(Uri playlistUri, boolean mutated) {
        if (MediaProvider.Playlists.isPlaylist(playlistUri)) {
            return playlistUri.buildUpon().appendQueryParameter("addTo", "true").appendQueryParameter("mutated", mutated ? "true" : "false").build();
        }
        throw new IllegalArgumentException("specified URI is not a playlist");
    }

    public static boolean moveUdoPlaylistTrack(Context context, Uri playlistUri, int from, int to) {
        String source = MediaProvider.getSource(playlistUri);
        long playlistId = getPlaylistId(playlistUri);
        if ("cirrus-local".equals(source) || "cirrus".equals(source)) {
            return CirrusPlaylistScratch.moveItem(context, playlistId, from, to);
        }
        throw new UnsupportedOperationException("Unknown source: " + source);
    }

    public static long getPlaylistId(Uri uri) {
        List<String> pathSegments = uri.getPathSegments();
        if (pathSegments.size() < 5) {
            throw new IllegalArgumentException("Invalid playlist uri specified");
        }
        return Long.parseLong(pathSegments.get(4));
    }

    public static String getPlaylistLuid(Context context, Uri uri) {
        ThreadUtil.dumpStacktraceOnMainThread();
        Cursor cursorQuery = context.getContentResolver().query(getPlaylistContentUri(uri), null, null, null, null);
        String string = null;
        if (cursorQuery != null) {
            try {
                if (cursorQuery.moveToFirst()) {
                    string = cursorQuery.getString(cursorQuery.getColumnIndex("luid"));
                }
            } finally {
                DbUtil.closeCursor(cursorQuery);
            }
        }
        return string;
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x001f  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static boolean isPlaylistValid(android.content.Context r6, android.net.Uri r7) {
        /*
            android.net.Uri r1 = getPlaylistContentUri(r7)
            android.content.ContentResolver r0 = r6.getContentResolver()
            r2 = 0
            r3 = 0
            r4 = 0
            r5 = 0
            android.database.Cursor r6 = r0.query(r1, r2, r3, r4, r5)
            if (r6 == 0) goto L1f
            int r7 = r6.getCount()     // Catch: java.lang.Throwable -> L1a
            if (r7 <= 0) goto L1f
            r7 = 1
            goto L20
        L1a:
            r7 = move-exception
            com.amazon.mp3.util.DbUtil.closeCursor(r6)
            throw r7
        L1f:
            r7 = 0
        L20:
            com.amazon.mp3.util.DbUtil.closeCursor(r6)
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.mp3.library.util.PlaylistUtil.isPlaylistValid(android.content.Context, android.net.Uri):boolean");
    }

    public static String getPlaylistName(Context context, Uri uri) {
        Cursor cursorQuery = context.getContentResolver().query(getPlaylistContentUri(uri), new String[]{"name"}, null, null, null);
        if (cursorQuery != null) {
            try {
                if (cursorQuery.moveToFirst()) {
                    return cursorQuery.getString(cursorQuery.getColumnIndexOrThrow("name"));
                }
            } finally {
                DbUtil.closeCursor(cursorQuery);
            }
        }
        DbUtil.closeCursor(cursorQuery);
        return null;
    }

    private static Uri getPlaylistContentUri(Uri uri) {
        List<String> pathSegments = uri.getPathSegments();
        String str = pathSegments.get(1);
        String str2 = pathSegments.get(3);
        return MediaProvider.Playlists.getContentUri(str).buildUpon().appendEncodedPath(str2).appendEncodedPath(pathSegments.get(4)).build();
    }

    public static int getPlaylistCount(Context context, Uri uri) {
        try {
            Profiler.begin("Getting playlist count");
            Cursor cursorQuery = context.getContentResolver().query(uri, null, null, null, null);
            int i = 0;
            if (cursorQuery != null && cursorQuery.moveToFirst()) {
                i = cursorQuery.getInt(cursorQuery.getColumnIndex("track_count"));
            }
            DbUtil.closeCursor(cursorQuery);
            return i;
        } finally {
            Profiler.end();
        }
    }

    public static boolean hasPlaylistReachLimit(Context context, Uri uri) {
        return "cirrus".equals(MediaProvider.getSource(uri)) && getPlaylistCount(context, uri) >= PlaylistConfigurationStorage.get().getMaximumNumberOfTracksInPlaylist();
    }

    public static Uri withAppendedScratchArgument(Uri uri) {
        return uri.buildUpon().appendQueryParameter("scratch", "true").build();
    }

    public static int insertUdoPlaylistTracks(Context context, String sourceId, long playlistId, Uri trackContentUri, String sortOrder) {
        return insertUdoPlaylistTracks(context, sourceId, playlistId, trackContentUri, sortOrder, false);
    }

    private static int insertUdoPlaylistTracks(Context context, String sourceId, long playlistId, Uri trackContentUri, String sortOrder, boolean capPlaylistSize) {
        int nextUdo;
        int playlistCount;
        int i;
        int i2;
        int i3;
        String str = TAG;
        Log.verbose(str, "insertUdoPlaylistTracks: playlistId: " + playlistId + ", sourceId: " + sourceId + ", trackContentUri: " + trackContentUri + ", sortOrder: " + sortOrder + ", capPlaylistSize: " + capPlaylistSize);
        ContentResolver contentResolver = context.getApplicationContext().getContentResolver();
        Uri contentUri = MediaProvider.UdoPlaylists.getContentUri(sourceId, playlistId);
        int maximumNumberOfTracksInPlaylist = Integer.MAX_VALUE;
        if ("cirrus".equals(sourceId) || "cirrus-local".equals(sourceId)) {
            contentUri = withAppendedScratchArgument(contentUri);
            Log.verbose(str, "playlistUri: " + contentUri);
            if (capPlaylistSize) {
                maximumNumberOfTracksInPlaylist = PlaylistConfigurationStorage.get().getMaximumNumberOfTracksInPlaylist();
            }
        }
        Uri uri = contentUri;
        int i4 = maximumNumberOfTracksInPlaylist;
        Cursor cursorQuery = contentResolver.query(trackContentUri, null, null, null, sortOrder);
        if (cursorQuery == null) {
            try {
                Log.verbose(str, "The cursor is null for " + trackContentUri);
            } finally {
                DbUtil.closeCursor(cursorQuery);
            }
        }
        int i5 = 0;
        if (cursorQuery != null) {
            int count = cursorQuery.getCount();
            Log.verbose(str, "Found tracks: " + count);
            if (count == 0) {
                return 0;
            }
            if ("cirrus-local".equals(sourceId) || "cirrus".equals(sourceId)) {
                nextUdo = CirrusPlaylistScratch.getNextUdo(context, playlistId);
                playlistCount = CirrusPlaylistScratch.getPlaylistCount(context, playlistId);
            } else {
                playlistCount = 0;
                nextUdo = 0;
            }
            Log.verbose(str, "playlistCount: " + playlistCount + ", sizeCap: " + i4);
            if (playlistCount >= i4) {
                return 0;
            }
            ArrayList arrayList = new ArrayList();
            ArrayList arrayList2 = new ArrayList();
            int columnIndexOrThrow = cursorQuery.getColumnIndexOrThrow("luid");
            int columnIndexOrThrow2 = cursorQuery.getColumnIndexOrThrow("asin");
            int columnIndexOrThrow3 = cursorQuery.getColumnIndexOrThrow("purchased");
            int columnIndexOrThrow4 = cursorQuery.getColumnIndexOrThrow("download_state");
            int i6 = playlistCount;
            int columnIndexOrThrow5 = cursorQuery.getColumnIndexOrThrow("ownership_status");
            int i7 = nextUdo;
            boolean z = count == 1;
            int i8 = i7;
            int iBulkInsert = 0;
            while (cursorQuery.moveToNext()) {
                String string = cursorQuery.getString(columnIndexOrThrow2);
                String string2 = cursorQuery.getString(columnIndexOrThrow);
                if (cursorQuery.isNull(columnIndexOrThrow3)) {
                    i = columnIndexOrThrow;
                    i2 = 0;
                } else {
                    i = columnIndexOrThrow;
                    i2 = cursorQuery.getInt(columnIndexOrThrow3);
                }
                int i9 = !cursorQuery.isNull(columnIndexOrThrow4) ? cursorQuery.getInt(columnIndexOrThrow4) : 5;
                int i10 = columnIndexOrThrow5;
                int i11 = columnIndexOrThrow4;
                int i12 = !cursorQuery.isNull(columnIndexOrThrow5) ? cursorQuery.getInt(columnIndexOrThrow5) : ContentOwnershipStatus.NOT_IN_LIBRARY.getValue();
                ContentValues contentValues = new ContentValues();
                int i13 = columnIndexOrThrow2;
                if (string == null || string.isEmpty() || "cirrus-local".equals(sourceId) || i12 < ContentOwnershipStatus.NOT_IN_LIBRARY.getValue() || i2 == 1) {
                    i3 = columnIndexOrThrow3;
                    Log.verbose(TAG, "Use luid for playlist track luid: %s, sourceId = %s, downloadState = %d, ownershipStatus = %d", string2, sourceId, Integer.valueOf(i9), Integer.valueOf(i12));
                } else {
                    i3 = columnIndexOrThrow3;
                    Log.verbose(TAG, "Use asin for playlist track luid: %s, sourceId = %s, downloadState = %d, ownershipStatus = %d", string, sourceId, Integer.valueOf(i9), Integer.valueOf(i12));
                    if (string2 != null) {
                        arrayList2.add(string2);
                    } else {
                        copyNullLuidTrackToCatalogOnly(context, cursorQuery);
                    }
                    string2 = string;
                }
                contentValues.put("track_luid", string2);
                int i14 = i8 + 1;
                contentValues.put("udo", Integer.valueOf(i8));
                int i15 = i6 + 1;
                if (z) {
                    Log.verbose(TAG, "inserting " + uri + ", " + contentValues);
                    if (contentResolver.insert(uri, contentValues) != null) {
                        iBulkInsert++;
                    }
                } else {
                    Log.verbose(TAG, "Bulk adding " + contentValues);
                    arrayList.add(contentValues);
                    if (arrayList.size() > 100 || cursorQuery.isLast() || cursorQuery.isAfterLast() || i15 >= i4) {
                        iBulkInsert += contentResolver.bulkInsert(uri, (ContentValues[]) arrayList.toArray(new ContentValues[arrayList.size()]));
                        arrayList.clear();
                    }
                }
                if (i15 >= i4) {
                    break;
                }
                columnIndexOrThrow5 = i10;
                columnIndexOrThrow4 = i11;
                i8 = i14;
                i6 = i15;
                columnIndexOrThrow2 = i13;
                columnIndexOrThrow3 = i3;
                columnIndexOrThrow = i;
            }
            i5 = iBulkInsert;
            copyLibraryTracksToCatalogOnly(context, arrayList2);
        }
        return i5;
    }

    public static int insertUdoPlaylistTracksFromLuidList(Context context, String sourceId, long playlistId, List<String> luids) {
        Uri contentUri = MediaProvider.UdoPlaylists.getContentUri(sourceId, playlistId);
        if (!MediaProvider.UdoPlaylists.isUdoPlaylist(contentUri)) {
            throw new UnsupportedOperationException("Playlist is not a UdoPlaylist");
        }
        Uri uriWithAppendedScratchArgument = withAppendedScratchArgument(contentUri);
        ContentResolver contentResolver = context.getApplicationContext().getContentResolver();
        ArrayList arrayList = new ArrayList();
        int nextUdo = CirrusPlaylistScratch.getNextUdo(context, playlistId);
        List<String> strippedLuidList = getStrippedLuidList(context, playlistId, luids);
        copyLibraryTracksToCatalogOnly(context, strippedLuidList);
        int iBulkInsert = 0;
        int i = 0;
        for (String str : getMixedLuidListFromLuids(context, strippedLuidList)) {
            i++;
            ContentValues contentValues = new ContentValues();
            contentValues.put("track_luid", str);
            int i2 = nextUdo + 1;
            contentValues.put("udo", Integer.valueOf(nextUdo));
            arrayList.add(contentValues);
            if (i == luids.size() || i == 100) {
                iBulkInsert += contentResolver.bulkInsert(uriWithAppendedScratchArgument, (ContentValues[]) arrayList.toArray(new ContentValues[arrayList.size()]));
                arrayList.clear();
            }
            nextUdo = i2;
        }
        return iBulkInsert;
    }

    private static List<String> getStrippedLuidList(Context context, final long playlistId, final List<String> luids) {
        ArrayList arrayList = new ArrayList();
        HashSet hashSet = new HashSet();
        Cursor cursorQuery = CirrusDatabase.getReadOnlyDatabase(context).query("PlaylistTrackScratch", new String[]{"track_luid"}, "udo_playlist_id =?", new String[]{String.valueOf(playlistId)}, null, null, null);
        while (cursorQuery.moveToNext()) {
            hashSet.add(cursorQuery.getString(cursorQuery.getColumnIndex("track_luid")));
        }
        cursorQuery.close();
        for (String str : luids) {
            if (!hashSet.contains(str)) {
                arrayList.add(str);
            }
        }
        return arrayList;
    }

    private static List<String> getMixedLuidListFromLuids(Context context, final List<String> luidList) {
        SQLiteDatabase readOnlyDatabase = CirrusDatabase.getReadOnlyDatabase(context);
        ArrayList arrayList = new ArrayList();
        DbUtil.WhereClause whereClause = new DbUtil.WhereClause("luid", luidList, luidList.size());
        Cursor cursorQuery = readOnlyDatabase.query("Track", new String[]{"luid", "asin"}, whereClause.getClause(), whereClause.getArgs(), null, null, null);
        try {
            int columnIndex = cursorQuery.getColumnIndex("asin");
            int columnIndex2 = cursorQuery.getColumnIndex("luid");
            while (cursorQuery.moveToNext()) {
                String string = cursorQuery.getString(columnIndex);
                String string2 = cursorQuery.getString(columnIndex2);
                if (string != null && !string.isEmpty()) {
                    arrayList.add(string);
                } else {
                    arrayList.add(string2);
                }
            }
            return arrayList;
        } finally {
            cursorQuery.close();
        }
    }

    public static void copyNullLuidTrackToCatalogOnly(final Context context, final Cursor cursor) {
        SQLiteDatabase writableDatabase = CirrusDatabase.getWritableDatabase(context);
        writableDatabase.beginTransaction();
        try {
            try {
                ContentValues contentValues = new ContentValues();
                DatabaseUtils.cursorRowToContentValues(cursor, contentValues);
                String asString = contentValues.getAsString("asin");
                contentValues.remove("_id");
                contentValues.put("luid", asString);
                contentValues.put("ownership_status", Integer.valueOf(ContentOwnershipStatus.NOT_IN_LIBRARY.getValue()));
                contentValues.put("playlist_track_status", Integer.valueOf(MusicTrack.PlaylistTrackStatus.CATALOG_TRACK_NOT_IN_LIBRARY.getValue()));
                writableDatabase.insertWithOnConflict("Track", null, contentValues, 4);
                writableDatabase.setTransactionSuccessful();
            } catch (Exception e) {
                Log.error(TAG, e.getMessage());
            }
        } finally {
            writableDatabase.endTransaction();
        }
    }

    private static void copyLibraryTracksToCatalogOnly(Context context, final List<String> luidList) {
        SQLiteDatabase writableDatabase = CirrusDatabase.getWritableDatabase(context);
        DbUtil.WhereClause whereClause = new DbUtil.WhereClause("luid", luidList, luidList.size());
        Cursor cursorQuery = writableDatabase.query("Track", null, DbUtil.applyBinaryOperator(whereClause.getClause(), "AND", "source = 0"), whereClause.getArgs(), null, null, null);
        int columnIndex = cursorQuery.getColumnIndex("asin");
        int columnIndex2 = cursorQuery.getColumnIndex("playlist_track_status");
        writableDatabase.beginTransaction();
        while (cursorQuery.moveToNext()) {
            try {
                ContentValues contentValues = new ContentValues();
                String string = cursorQuery.getString(columnIndex);
                int i = cursorQuery.getInt(columnIndex2);
                if (string != null && !string.isEmpty() && i <= MusicTrack.PlaylistTrackStatus.NOT_CATALOG_TRACK.getValue()) {
                    DatabaseUtils.cursorRowToContentValues(cursorQuery, contentValues);
                    contentValues.remove("_id");
                    contentValues.put("luid", string);
                    contentValues.put("ownership_status", Integer.valueOf(ContentOwnershipStatus.NOT_IN_LIBRARY.getValue()));
                    contentValues.put("playlist_track_status", Integer.valueOf(MusicTrack.PlaylistTrackStatus.CATALOG_TRACK_IN_LIBRARY.getValue()));
                    writableDatabase.insertWithOnConflict("Track", null, contentValues, 4);
                }
            } finally {
                cursorQuery.close();
                writableDatabase.endTransaction();
            }
        }
        writableDatabase.setTransactionSuccessful();
    }

    public static String calculateSongsDuration(final Context context, final int songsCount, final int songsTotalSeconds) {
        return String.format(context.getString(R.string.dmusic_library_songs_time), String.format(AmazonApplication.getContext().getResources().getQuantityText(R.plurals.dmusic_library_songs_count, songsCount).toString(), Integer.valueOf(songsCount)), calculatePlaylistDurationOnly(context, songsTotalSeconds));
    }

    public static String calculateVideosDuration(final Context context, final int videosCount, final int videosTotalSeconds) {
        return String.format(context.getString(R.string.dmusic_library_songs_time), String.format(context.getResources().getQuantityString(R.plurals.dmusic_library_videos_count, videosCount), Integer.valueOf(videosCount)), calculatePlaylistDurationOnly(context, videosTotalSeconds));
    }

    public static boolean isRecentlyAddedPlaylist(final Uri contentUri) {
        return contentUri != null && MediaProvider.isSmartPlaylist(contentUri) && 3 == MediaProvider.SmartPlaylists.getPlaylistId(contentUri);
    }

    public static boolean isRecentlyAddedToDevicePlaylist(final Uri contentUri) {
        return contentUri != null && MediaProvider.SmartPlaylists.isSmartPlaylist(contentUri) && 2 == MediaProvider.SmartPlaylists.getPlaylistId(contentUri);
    }

    public static String calculatePlaylistDurationOnly(final Context context, final int songsTotalSeconds) {
        if (songsTotalSeconds <= 3540) {
            return calculateMinutes(songsTotalSeconds);
        }
        int i = songsTotalSeconds / 3600;
        String string = context.getString(R.string.dmusic_library_songs_duration_hours);
        return String.format(string, Integer.valueOf(i)) + " " + calculateMinutes(songsTotalSeconds - (i * 3600));
    }

    private static final String calculateMinutes(final int seconds) {
        int i = (seconds + 59) / 60;
        return String.format(AmazonApplication.getContext().getResources().getQuantityText(R.plurals.dmusic_library_songs_duration_minutes, i).toString(), Integer.valueOf(i));
    }

    public static final class PlaylistTrack {
        public long mPlaylistTrackId;
        public long mUdo;

        public PlaylistTrack(long playlistTrackId, long udo) {
            this.mPlaylistTrackId = playlistTrackId;
            this.mUdo = udo;
        }
    }

    public static final class PlaylistTrackInfo {
        public Hashtable<String, PlaylistTrack> mCachePlaylist;
        public int mCalcDuration;
        public int mTrackCount;

        public PlaylistTrackInfo(Hashtable<String, PlaylistTrack> cachePlaylist, int trackCount, int calcDuration) {
            this.mCachePlaylist = cachePlaylist;
            this.mTrackCount = trackCount;
            this.mCalcDuration = calcDuration;
        }
    }

    public static PlaylistTrackInfo createPlaylistInfo(final Context context, final Uri playlistUri, final boolean isCirrus) {
        if (MediaProvider.SmartPlaylists.isSmartPlaylist(playlistUri)) {
            return createSmartPlaylistInfo(context, playlistUri, isCirrus);
        }
        if (MediaProvider.UdoPlaylists.isUdoPlaylist(playlistUri)) {
            return createUdoPlaylistInfo(context, playlistUri, isCirrus);
        }
        return null;
    }

    /* JADX WARN: Removed duplicated region for block: B:24:0x007e  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static com.amazon.mp3.library.util.PlaylistUtil.PlaylistTrackInfo createSmartPlaylistInfo(final android.content.Context r11, final android.net.Uri r12, final boolean r13) {
        /*
            java.util.Hashtable r0 = new java.util.Hashtable
            r0.<init>()
            android.content.ContentResolver r1 = r11.getContentResolver()
            java.lang.String r11 = r12.getLastPathSegment()
            java.lang.String r2 = "tracks"
            boolean r11 = r2.equals(r11)
            if (r11 != 0) goto L24
            android.net.Uri$Builder r11 = r12.buildUpon()
            android.net.Uri$Builder r11 = r11.appendEncodedPath(r2)
            android.net.Uri r11 = r11.build()
            r2 = r11
            goto L25
        L24:
            r2 = r12
        L25:
            java.lang.String[] r3 = com.amazon.mp3.library.util.PlaylistUtil.CREATE_SMART_PLAYLIST_PROJECTION
            java.lang.String r4 = getSelection(r12)
            java.lang.String[] r5 = getSelectionArgs(r12)
            r6 = 0
            android.database.Cursor r11 = r1.query(r2, r3, r4, r5, r6)
            r12 = 0
            if (r11 == 0) goto L7e
            boolean r1 = r11.moveToFirst()     // Catch: java.lang.Throwable -> L79
            if (r1 == 0) goto L7e
            java.lang.String r1 = "luid"
            int r1 = r11.getColumnIndex(r1)     // Catch: java.lang.Throwable -> L79
            java.lang.String r2 = "_id"
            int r2 = r11.getColumnIndex(r2)     // Catch: java.lang.Throwable -> L79
            java.lang.String r3 = "duration"
            int r3 = r11.getColumnIndex(r3)     // Catch: java.lang.Throwable -> L79
            r4 = r12
        L50:
            int r12 = r12 + 1
            int r5 = r11.getInt(r3)     // Catch: java.lang.Throwable -> L79
            int r4 = r4 + r5
            java.lang.String r5 = r11.getString(r1)     // Catch: java.lang.Throwable -> L79
            if (r5 != 0) goto L5e
            goto L6c
        L5e:
            long r6 = r11.getLong(r2)     // Catch: java.lang.Throwable -> L79
            com.amazon.mp3.library.util.PlaylistUtil$PlaylistTrack r8 = new com.amazon.mp3.library.util.PlaylistUtil$PlaylistTrack     // Catch: java.lang.Throwable -> L79
            r9 = -1
            r8.<init>(r6, r9)     // Catch: java.lang.Throwable -> L79
            r0.put(r5, r8)     // Catch: java.lang.Throwable -> L79
        L6c:
            boolean r5 = r11.moveToNext()     // Catch: java.lang.Throwable -> L79
            if (r5 != 0) goto L50
            if (r13 != 0) goto L77
            int r13 = r4 / 1000
            goto L7f
        L77:
            r13 = r4
            goto L7f
        L79:
            r12 = move-exception
            com.amazon.mp3.util.DbUtil.closeCursor(r11)
            throw r12
        L7e:
            r13 = r12
        L7f:
            com.amazon.mp3.util.DbUtil.closeCursor(r11)
            com.amazon.mp3.library.util.PlaylistUtil$PlaylistTrackInfo r11 = new com.amazon.mp3.library.util.PlaylistUtil$PlaylistTrackInfo
            r11.<init>(r0, r12, r13)
            return r11
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.mp3.library.util.PlaylistUtil.createSmartPlaylistInfo(android.content.Context, android.net.Uri, boolean):com.amazon.mp3.library.util.PlaylistUtil$PlaylistTrackInfo");
    }

    public static String getSelection(final Uri playlistUri) {
        if (isRecentlyAddedPlaylist(playlistUri)) {
            return null;
        }
        return TrackListAdapter.getPlaylistSelection();
    }

    public static String[] getSelectionArgs(final Uri playlistUri) {
        if (isRecentlyAddedPlaylist(playlistUri)) {
            return null;
        }
        return TrackListAdapter.getPlaylistSelectionArgs();
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x0078  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static com.amazon.mp3.library.util.PlaylistUtil.PlaylistTrackInfo createUdoPlaylistInfo(final android.content.Context r12, final android.net.Uri r13, final boolean r14) {
        /*
            java.util.Hashtable r0 = new java.util.Hashtable
            r0.<init>()
            android.content.ContentResolver r1 = r12.getContentResolver()
            android.net.Uri$Builder r12 = r13.buildUpon()
            java.lang.String r13 = "tracks"
            android.net.Uri$Builder r12 = r12.appendEncodedPath(r13)
            android.net.Uri r2 = r12.build()
            java.lang.String[] r3 = com.amazon.mp3.library.util.PlaylistUtil.CREATE_UDO_PLAYLIST_PROJECTION
            java.lang.String r4 = com.amazon.mp3.library.adapter.TrackListAdapter.getPlaylistSelection()
            java.lang.String[] r5 = com.amazon.mp3.library.adapter.TrackListAdapter.getPlaylistSelectionArgs()
            r6 = 0
            android.database.Cursor r12 = r1.query(r2, r3, r4, r5, r6)
            r13 = 0
            if (r12 == 0) goto L78
            boolean r1 = r12.moveToFirst()     // Catch: java.lang.Throwable -> L73
            if (r1 == 0) goto L78
            java.lang.String r1 = "luid"
            int r1 = r12.getColumnIndex(r1)     // Catch: java.lang.Throwable -> L73
            java.lang.String r2 = "udo_playlist_track_id"
            int r2 = r12.getColumnIndex(r2)     // Catch: java.lang.Throwable -> L73
            java.lang.String r3 = "udo"
            int r3 = r12.getColumnIndex(r3)     // Catch: java.lang.Throwable -> L73
            java.lang.String r4 = "duration"
            int r4 = r12.getColumnIndex(r4)     // Catch: java.lang.Throwable -> L73
            r5 = r13
        L4b:
            int r6 = r12.getInt(r4)     // Catch: java.lang.Throwable -> L73
            int r13 = r13 + r6
            java.lang.String r6 = r12.getString(r1)     // Catch: java.lang.Throwable -> L73
            long r7 = r12.getLong(r3)     // Catch: java.lang.Throwable -> L73
            long r9 = r12.getLong(r2)     // Catch: java.lang.Throwable -> L73
            com.amazon.mp3.library.util.PlaylistUtil$PlaylistTrack r11 = new com.amazon.mp3.library.util.PlaylistUtil$PlaylistTrack     // Catch: java.lang.Throwable -> L73
            r11.<init>(r9, r7)     // Catch: java.lang.Throwable -> L73
            r0.put(r6, r11)     // Catch: java.lang.Throwable -> L73
            int r5 = r5 + 1
            boolean r6 = r12.moveToNext()     // Catch: java.lang.Throwable -> L73
            if (r6 != 0) goto L4b
            if (r14 != 0) goto L70
            int r13 = r13 / 1000
        L70:
            r14 = r13
            r13 = r5
            goto L79
        L73:
            r13 = move-exception
            com.amazon.mp3.util.DbUtil.closeCursor(r12)
            throw r13
        L78:
            r14 = r13
        L79:
            com.amazon.mp3.util.DbUtil.closeCursor(r12)
            com.amazon.mp3.library.util.PlaylistUtil$PlaylistTrackInfo r12 = new com.amazon.mp3.library.util.PlaylistUtil$PlaylistTrackInfo
            r12.<init>(r0, r13, r14)
            return r12
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.mp3.library.util.PlaylistUtil.createUdoPlaylistInfo(android.content.Context, android.net.Uri, boolean):com.amazon.mp3.library.util.PlaylistUtil$PlaylistTrackInfo");
    }

    public static boolean deletePlaylistTrack(final Context context, final Uri playlistUri, final Uri contentUri, final long playlistId, final long playlistTrackId, final long udo) {
        ContentResolver contentResolver = context.getContentResolver();
        Uri uriBuild = MediaProvider.UdoPlaylistTracks.getContentUri(MediaProvider.getSource(contentUri), playlistId, playlistTrackId).buildUpon().appendQueryParameter("udo", String.valueOf(udo)).build();
        if (CirrusMediaSource.match(uriBuild)) {
            uriBuild = withAppendedScratchArgument(uriBuild);
        }
        return contentResolver.delete(uriBuild, null, null) > 0;
    }

    public static void clearMutatedFlag(Context context) {
        udpateMutatedFlag(context, false);
    }

    public static void setMutatedFlag(Context context) {
        udpateMutatedFlag(context, true);
    }

    private static String getMutatedString(Context context) {
        return context.getString(R.string.setting_key_playlist_mutated);
    }

    private static void udpateMutatedFlag(Context context, boolean flag) {
        SharedPreferences.Editor editorEdit = SettingsUtil.getPrefs(context).edit();
        editorEdit.putBoolean(getMutatedString(context), flag);
        editorEdit.apply();
    }

    public static boolean getMutatedFlag(Context context) {
        return SettingsUtil.getPrefs(context).getBoolean(getMutatedString(context), false);
    }

    public static Set<Uri> getPlaylistTrackUrisFromTrack(Context c, Uri playlistUri, Uri trackUri) {
        String lastPathSegment;
        HashSet hashSet = new HashSet();
        if (playlistUri != null && trackUri != null) {
            String str = "true".equals(playlistUri.getQueryParameter("scratch")) ? "PlaylistTrackScratch" : "PlaylistTrack";
            if (MediaProvider.Tracks.isPrimeAdditionalTrack(trackUri)) {
                lastPathSegment = TrackUtil.getTrackLuid(c, trackUri);
            } else {
                lastPathSegment = trackUri.getLastPathSegment();
            }
            if (StringUtils.isBlank(lastPathSegment)) {
                Log.error(TAG, "track {%s} not found in playlist {%s}", playlistUri.toString(), trackUri.toString());
                return hashSet;
            }
            String strFindObjectIdByTrackLocalLuid = TrackUtil.findObjectIdByTrackLocalLuid(c, lastPathSegment);
            String strFindAsinByTrackLocalLuid = TrackUtil.findAsinByTrackLocalLuid(c, lastPathSegment);
            Log.debug(TAG, "track luid = {%s}, objectId = {%s}, asin = {%s} being removed from playlist {%s}", lastPathSegment, strFindObjectIdByTrackLocalLuid, strFindAsinByTrackLocalLuid, playlistUri.toString());
            StringBuilder sb = new StringBuilder();
            sb.append("track_luid IN ('");
            sb.append(lastPathSegment);
            sb.append("', '");
            sb.append(strFindObjectIdByTrackLocalLuid);
            sb.append("', '");
            if (!StringUtils.isNotBlank(strFindAsinByTrackLocalLuid)) {
                strFindAsinByTrackLocalLuid = "";
            }
            sb.append(strFindAsinByTrackLocalLuid);
            sb.append("') ");
            Cursor cursorQuery = CirrusDatabase.getReadOnlyDatabase(c).query(str, new String[]{"_id"}, sb.toString(), null, null, null, null);
            if (cursorQuery != null) {
                try {
                    if (cursorQuery.moveToFirst()) {
                        String source = MediaProvider.getSource(playlistUri);
                        long playlistId = MediaProvider.Playlists.getPlaylistId(playlistUri);
                        int columnIndex = cursorQuery.getColumnIndex("_id");
                        do {
                            hashSet.add(withAppendedScratchArgument(MediaProvider.UdoPlaylistTracks.getContentUri(source, playlistId, cursorQuery.getLong(columnIndex))));
                        } while (cursorQuery.moveToNext());
                    }
                } finally {
                    DbUtil.closeCursor(cursorQuery);
                }
            }
        }
        return hashSet;
    }

    @NonNull
    public static Set<String> getUdoPlaylistTracksLuids(Context context, final Uri playlistUri) {
        Validate.isTrue(MediaProvider.UdoPlaylists.isUdoPlaylist(playlistUri));
        Uri contentUri = MediaProvider.UdoPlaylistTracks.getContentUri(MediaProvider.getSource(playlistUri), MediaProvider.Playlists.getPlaylistId(playlistUri));
        HashSet hashSet = new HashSet();
        Cursor cursorQuery = context.getContentResolver().query(contentUri, new String[]{"PlaylistTrack.track_luid"}, null, null, null);
        if (cursorQuery != null) {
            try {
                if (cursorQuery.moveToFirst()) {
                    while (!cursorQuery.isAfterLast()) {
                        hashSet.add(cursorQuery.getString(0));
                        cursorQuery.moveToNext();
                    }
                    cursorQuery.close();
                    return hashSet;
                }
            } catch (Throwable th) {
                try {
                    cursorQuery.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
                throw th;
            }
        }
        if (cursorQuery != null) {
            cursorQuery.close();
        }
        return hashSet;
    }

    @NonNull
    @WorkerThread
    public static boolean hasAsinsInUdoPlaylist(Context context, final Uri playlistUri) {
        if (!MediaProvider.UdoPlaylists.isUdoPlaylist(playlistUri)) {
            return false;
        }
        Cursor cursorQuery = context.getContentResolver().query(MediaProvider.UdoPlaylistTracks.getContentUri(MediaProvider.getSource(playlistUri), MediaProvider.Playlists.getPlaylistId(playlistUri)), new String[]{"Track.asin"}, null, null, null);
        if (cursorQuery != null) {
            try {
                if (cursorQuery.moveToFirst()) {
                    while (!cursorQuery.isAfterLast()) {
                        if (TextUtils.isEmpty(cursorQuery.getString(0))) {
                            cursorQuery.moveToNext();
                        } else {
                            cursorQuery.close();
                            return true;
                        }
                    }
                    cursorQuery.close();
                    return false;
                }
            } catch (Throwable th) {
                try {
                    cursorQuery.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
                throw th;
            }
        }
        if (cursorQuery != null) {
            cursorQuery.close();
        }
        return false;
    }

    public static void startEditPlaylist(final Activity parentActivity, final Uri contentUri, final boolean skipEditPlaylist) {
        if (ActivityKt.isAvailable(parentActivity)) {
            if (!ConnectivityUtil.hasAnyInternetConnection() && "cirrus".equals(MediaProvider.getSource(contentUri))) {
                parentActivity.startActivity(NetworkErrorDialogActivity.getStartIntent(parentActivity));
            } else {
                final Handler handler = new Handler();
                new Thread() { // from class: com.amazon.mp3.library.util.PlaylistUtil.1
                    @Override // java.lang.Thread, java.lang.Runnable
                    public void run() {
                        if (ActivityKt.isAvailable(parentActivity)) {
                            CirrusPlaylistScratch.create(parentActivity, PlaylistUtil.getPlaylistId(contentUri));
                            handler.post(new Runnable() { // from class: com.amazon.mp3.library.util.PlaylistUtil.1.1
                                @Override // java.lang.Runnable
                                public void run() {
                                    if (ActivityKt.isAvailable(parentActivity)) {
                                        AnonymousClass1 anonymousClass1 = AnonymousClass1.this;
                                        if (skipEditPlaylist) {
                                            parentActivity.startActivityForResult(LibraryActivityFactory.getIntentForContentUri(parentActivity, PlaylistUtil.getAddToPlaylistUri(contentUri)), AddToPlaylistTabActivity.REQUEST_CODE);
                                        } else {
                                            Activity activity = parentActivity;
                                            activity.startActivityForResult(LibraryActivityFactory.getIntentForContentUri(activity, PlaylistUtil.getEditPlaylistUri(contentUri)), AddToPlaylistActivity.REQUEST_CODE);
                                        }
                                    }
                                }
                            });
                        }
                    }
                }.start();
            }
        }
    }

    public static boolean playlistContainsTrack(Context context, Uri playlistUri, Uri trackUri) {
        boolean z = false;
        if (playlistUri != null && trackUri != null) {
            ContentResolver contentResolver = context.getContentResolver();
            Cursor cursorQuery = contentResolver.query(trackUri, new String[]{"luid", "asin"}, null, null, null);
            if (cursorQuery != null) {
                try {
                    if (cursorQuery.moveToFirst()) {
                        int columnIndex = cursorQuery.getColumnIndex("luid");
                        int columnIndex2 = cursorQuery.getColumnIndex("asin");
                        String string = cursorQuery.getString(columnIndex);
                        String string2 = cursorQuery.getString(columnIndex2);
                        cursorQuery.close();
                        Uri uriBuild = playlistUri.buildUpon().appendEncodedPath("tracks").build();
                        ArrayList arrayList = new ArrayList();
                        arrayList.add(string);
                        arrayList.add(string2);
                        DbUtil.WhereClause whereClause = new DbUtil.WhereClause("luid", arrayList, arrayList.size());
                        cursorQuery = contentResolver.query(uriBuild, new String[]{"luid"}, whereClause.getClause(), whereClause.getArgs(), null);
                        if (cursorQuery != null) {
                            try {
                                if (cursorQuery.getCount() > 0) {
                                    z = true;
                                }
                            } catch (Throwable th) {
                                throw th;
                            }
                        }
                        if (cursorQuery != null) {
                            cursorQuery.close();
                        }
                        return z;
                    }
                } finally {
                    try {
                        cursorQuery.close();
                    } catch (Throwable th2) {
                        th.addSuppressed(th2);
                    }
                }
            }
            if (cursorQuery != null) {
                cursorQuery.close();
            }
        }
        return false;
    }

    public static boolean isContentPlaylistAddable(String extension) {
        return !DeluxeContentUtil.isDeluxeContent(extension);
    }

    public static int getPlaylistTracksCount(final Context context, final long playlistId, final String source) {
        SQLiteDatabase readOnlyDatabase = CirrusDatabase.getReadOnlyDatabase(context);
        String str = "cirrus".equals(source) ? "Track.source =? " : "Track.download_state =? ";
        Cursor cursorQuery = null;
        try {
            try {
                cursorQuery = readOnlyDatabase.query("Track INNER JOIN PlaylistTrack ON PlaylistTrack.track_luid = Track.luid LEFT OUTER JOIN LocalTrackUri ON PlaylistTrack.track_luid = LocalTrackUri.track_luid", new String[]{"Track.luid"}, "PlaylistTrack.udo_playlist_id=? AND " + str, new String[]{Long.toString(playlistId), "0"}, null, null, null);
                if (cursorQuery != null) {
                    return cursorQuery.getCount();
                }
            } catch (Exception e) {
                Log.error(TAG, "Error occurred when getPlaylistTracksCount for playlist id = [" + playlistId + "] ", e);
            }
            return 0;
        } finally {
            DbUtil.closeCursor(cursorQuery);
        }
    }

    public static List<String> getPlaylistLuidsOrderedByModifiedDate(final Context context, final List<String> playlistLuids) {
        Cursor cursorQuery = CirrusDatabase.getReadOnlyDatabase(context).query("Playlist", null, "luid IN (" + ((String) playlistLuids.stream().map(new Function() { // from class: com.amazon.mp3.library.util.PlaylistUtil$$ExternalSyntheticLambda0
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return PlaylistUtil.lambda$getPlaylistLuidsOrderedByModifiedDate$0((String) obj);
            }
        }).collect(Collectors.joining(", "))) + ")", null, null, null, "dateLastModified");
        ArrayList arrayList = new ArrayList();
        while (cursorQuery.moveToNext()) {
            try {
                arrayList.add(cursorQuery.getString(cursorQuery.getColumnIndex("luid")));
            } finally {
                DbUtil.closeCursor(cursorQuery);
            }
        }
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ String lambda$getPlaylistLuidsOrderedByModifiedDate$0(String str) {
        return "'" + str + "'";
    }

    public static boolean maxNumberOfPlaylistsReached(Context context) {
        return getNumberOfUdoPlaylistsBySourceId(context, "cirrus") + getNumberOfFollowedPrimePlaylists(context, "cirrus") >= PlaylistConfigurationStorage.get().getMaximumNumberOfPlaylistsAllowed();
    }

    public static boolean hasUserPlaylist(Context context) {
        if (hasUdoPlaylists(context, "cirrus")) {
            return true;
        }
        return hasUdoPlaylists(context, "cirrus-local");
    }

    private static int getNumberOfUdoPlaylistsBySourceId(Context context, String sourceId) {
        ContentResolver contentResolver = context.getContentResolver();
        Cursor cursorQuery = null;
        try {
            cursorQuery = contentResolver.query(MediaProvider.UdoPlaylists.getContentUri(sourceId), new String[]{"_id"}, null, null, null);
            return cursorQuery == null ? 0 : cursorQuery.getCount();
        } finally {
            DbUtil.closeCursor(cursorQuery);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0037  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static boolean hasUdoPlaylists(android.content.Context r6, java.lang.String r7) {
        /*
            android.content.ContentResolver r0 = r6.getContentResolver()
            r6 = 0
            android.net.Uri r1 = com.amazon.mp3.library.provider.MediaProvider.UdoPlaylists.getContentUri(r7)     // Catch: java.lang.Throwable -> L39
            java.lang.String r7 = "_id"
            java.lang.String[] r2 = new java.lang.String[]{r7}     // Catch: java.lang.Throwable -> L39
            r3 = 0
            r4 = 0
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L39
            r7.<init>()     // Catch: java.lang.Throwable -> L39
            java.lang.String r5 = com.amazon.mp3.library.provider.source.cirrus.CirrusMediaSource.getUdoPlaylistSortOrder()     // Catch: java.lang.Throwable -> L39
            r7.append(r5)     // Catch: java.lang.Throwable -> L39
            java.lang.String r5 = " LIMIT 1"
            r7.append(r5)     // Catch: java.lang.Throwable -> L39
            java.lang.String r5 = r7.toString()     // Catch: java.lang.Throwable -> L39
            android.database.Cursor r6 = r0.query(r1, r2, r3, r4, r5)     // Catch: java.lang.Throwable -> L39
            if (r6 == 0) goto L37
            int r7 = r6.getCount()     // Catch: java.lang.Throwable -> L39
            if (r7 <= 0) goto L37
            r7 = 1
        L33:
            com.amazon.mp3.util.DbUtil.closeCursor(r6)
            return r7
        L37:
            r7 = 0
            goto L33
        L39:
            r7 = move-exception
            com.amazon.mp3.util.DbUtil.closeCursor(r6)
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.mp3.library.util.PlaylistUtil.hasUdoPlaylists(android.content.Context, java.lang.String):boolean");
    }

    public static List<Long> getAlbumIds(Uri uri) {
        Cursor cursorQuery = MediaProvider.getInstance().query(uri, new String[]{"album_id"}, null, null, null);
        HashSet hashSet = new HashSet();
        if (cursorQuery != null) {
            if (cursorQuery.moveToFirst()) {
                do {
                    hashSet.add(Long.valueOf(cursorQuery.getLong(0)));
                } while (cursorQuery.moveToNext());
            }
            cursorQuery.close();
        }
        return new ArrayList(hashSet);
    }

    private static int getNumberOfFollowedPrimePlaylists(Context context, String sourceId) {
        ContentResolver contentResolver = context.getContentResolver();
        Cursor cursorQuery = null;
        try {
            cursorQuery = contentResolver.query(MediaProvider.PrimePlaylists.getContentUri(sourceId), new String[]{"_id"}, "is_following=?", new String[]{"1"}, null);
            return cursorQuery == null ? 0 : cursorQuery.getCount();
        } finally {
            DbUtil.closeCursor(cursorQuery);
        }
    }

    public static void logPlaylistDetails(final ContentResolver contentResolver) {
        new Thread(new Runnable() { // from class: com.amazon.mp3.library.util.PlaylistUtil.2
            @Override // java.lang.Runnable
            public void run() {
                PlaylistUtil.logPlaylistListDetailsBySource(contentResolver, "cirrus");
                PlaylistUtil.logPlaylistListDetailsBySource(contentResolver, "cirrus-local");
            }
        }).start();
    }

    static void logPlaylistListDetailsBySource(final ContentResolver contentResolver, final String source) {
        Cursor playlistListCursor = getPlaylistListCursor(contentResolver, source);
        try {
            if (playlistListCursor == null) {
                Log.error(TAG, "Playlist cursor is null");
                if (playlistListCursor != null) {
                    playlistListCursor.close();
                    return;
                }
                return;
            }
            Log.info(TAG, "Playlist Details: " + source);
            playlistListCursor.moveToFirst();
            while (!playlistListCursor.isAfterLast()) {
                try {
                    String string = playlistListCursor.getString(playlistListCursor.getColumnIndex("name"));
                    if (!TextUtils.isEmpty(string)) {
                        Log.info(TAG, "%s - %s - %s", string, playlistListCursor.getString(playlistListCursor.getColumnIndex("luid")), playlistListCursor.getString(playlistListCursor.getColumnIndex("track_count")));
                    }
                } catch (Exception e) {
                    Log.error(TAG, "Unable to get playlist detail", e);
                }
                playlistListCursor.moveToNext();
            }
            playlistListCursor.close();
        } catch (Throwable th) {
            if (playlistListCursor != null) {
                try {
                    playlistListCursor.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
            }
            throw th;
        }
    }

    private static Cursor getPlaylistListCursor(final ContentResolver contentResolver, final String source) {
        if (contentResolver == null) {
            return null;
        }
        Uri.Builder builderAppendQueryParameter = MediaProvider.Playlists.getFilterContentUri(source, "").buildUpon().appendQueryParameter("include_playlist_headers", "true");
        MediaProvider.SmartPlaylists.setSmartPlaylistsToQuery(builderAppendQueryParameter, getSmartPlaylistEnumSet());
        return contentResolver.query(builderAppendQueryParameter.build(), getPlaylistListProjection(), null, null, null);
    }

    private static EnumSet<MediaProvider.SmartPlaylists.AvailableSmartPlaylists> getSmartPlaylistEnumSet() {
        EnumSet<MediaProvider.SmartPlaylists.AvailableSmartPlaylists> enumSetOf = EnumSet.of(MediaProvider.SmartPlaylists.AvailableSmartPlaylists.LATEST_PURCHASES, MediaProvider.SmartPlaylists.AvailableSmartPlaylists.LATESET_UPLOADS);
        if (AmazonApplication.getCapabilities().isRecentTracksSyncSupported()) {
            enumSetOf.add(MediaProvider.SmartPlaylists.AvailableSmartPlaylists.RECENTLY_PLAYED_TRACKS);
        }
        return enumSetOf;
    }

    private static String[] getPlaylistListProjection() {
        return new String[]{"name", "luid", "track_count"};
    }

    public static void setPlaylistVisibility(final FragmentActivity activity, final Uri contentUri, final MediaProvider.Playlists.VisibilityState state) {
        new PlaylistPrivacyOperationSupportManager(activity).updatePlaylistVisibilityWithConfirmationDialogIfNeeded(contentUri, state);
    }

    public static MediaProvider.Playlists.VisibilityState getPlaylistVisibilityStateFromUri(final Context context, final Uri uri) {
        MediaProvider.Playlists.VisibilityState visibilityStateFromInt = MediaProvider.Playlists.VisibilityState.NO_STATE;
        if (uri == null) {
            return visibilityStateFromInt;
        }
        try {
            Cursor cursorQuery = CirrusDatabase.getReadOnlyDatabase(context).query("Playlist", new String[]{"visibility"}, "_id = ?", new String[]{String.valueOf(getPlaylistId(uri))}, null, null, null);
            if (cursorQuery != null) {
                try {
                    if (cursorQuery.moveToFirst()) {
                        visibilityStateFromInt = MediaProvider.Playlists.VisibilityState.fromInt(cursorQuery.getInt(cursorQuery.getColumnIndexOrThrow("visibility")));
                    }
                } finally {
                }
            }
            if (cursorQuery != null) {
                cursorQuery.close();
            }
        } catch (IllegalArgumentException unused) {
        }
        return visibilityStateFromInt;
    }

    public static String getPlaylistVersionFromUri(final Context context, final Uri uri) {
        String string = "";
        if (uri == null) {
            return "";
        }
        try {
            Cursor cursorQuery = CirrusDatabase.getReadOnlyDatabase(context).query("Playlist", new String[]{"version"}, "_id = ?", new String[]{String.valueOf(getPlaylistId(uri))}, null, null, null);
            if (cursorQuery != null) {
                try {
                    if (cursorQuery.moveToFirst()) {
                        string = cursorQuery.getString(cursorQuery.getColumnIndexOrThrow("version"));
                    }
                } finally {
                }
            }
            if (cursorQuery != null) {
                cursorQuery.close();
            }
        } catch (IllegalArgumentException unused) {
        }
        return string;
    }

    public static boolean hasAnyUdoPrivatePlaylistWithMinFollowerCount(final Context context, final int minFollowerCount) {
        return hasUdoPlaylistWithMinFollowerCount(context, "*", minFollowerCount, String.valueOf(MediaProvider.Playlists.VisibilityState.PRIVATE.getInt()));
    }

    public static boolean hasUdoPlaylistWithMinFollowerCount(final Context context, final String playlistId, final int minFollowerCount, final String visibility) {
        String str;
        SQLiteDatabase readOnlyDatabase = CirrusDatabase.getReadOnlyDatabase(context);
        ArrayList arrayList = new ArrayList();
        arrayList.add(String.valueOf(minFollowerCount));
        String str2 = "numberOfFollowers >= ? ";
        if (!"*".equals(visibility)) {
            str2 = "numberOfFollowers >= ?  AND visibility = ?";
            arrayList.add(visibility);
        }
        if ("*".equals(playlistId)) {
            str = str2;
        } else {
            arrayList.add(playlistId);
            str = str2 + " AND _id = ?";
        }
        Cursor cursorQuery = null;
        try {
            cursorQuery = readOnlyDatabase.query("Playlist", new String[]{"numberOfFollowers", "_id", "visibility"}, str, (String[]) arrayList.toArray(new String[arrayList.size()]), null, null, null, "1");
            if (cursorQuery != null) {
                if (cursorQuery.getCount() > 0) {
                    cursorQuery.close();
                    return true;
                }
            }
            if (cursorQuery != null) {
                cursorQuery.close();
            }
            return false;
        } catch (Exception unused) {
            if (cursorQuery != null) {
                cursorQuery.close();
            }
            return false;
        } catch (Throwable th) {
            if (cursorQuery != null) {
                cursorQuery.close();
            }
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v0, types: [android.database.sqlite.SQLiteDatabase] */
    /* JADX WARN: Type inference failed for: r0v1 */
    /* JADX WARN: Type inference failed for: r0v2 */
    /* JADX WARN: Type inference failed for: r0v3 */
    /* JADX WARN: Type inference failed for: r0v4 */
    /* JADX WARN: Type inference failed for: r0v5, types: [android.database.Cursor] */
    /* JADX WARN: Type inference failed for: r0v6, types: [android.database.Cursor] */
    /* JADX WARN: Type inference failed for: r0v7 */
    /* JADX WARN: Type inference failed for: r0v8 */
    /* JADX WARN: Type inference failed for: r0v9 */
    /* JADX WARN: Type inference failed for: r9v2 */
    /* JADX WARN: Type inference failed for: r9v3, types: [android.database.Cursor] */
    /* JADX WARN: Type inference failed for: r9v4 */
    @Nullable
    public static String getPlaylistDescriptionFromUri(Context context, Uri uri) throws Throwable {
        ?? readOnlyDatabase = CirrusDatabase.getReadOnlyDatabase(context);
        String[] strArr = {String.valueOf(getPlaylistId(uri))};
        ?? r9 = 0;
        string = null;
        string = null;
        String string = null;
        try {
            try {
                readOnlyDatabase = readOnlyDatabase.query("Playlist", new String[]{MediaTrack.ROLE_DESCRIPTION}, "_id = ?", strArr, null, null, null);
            } catch (IllegalArgumentException e) {
                e = e;
                readOnlyDatabase = 0;
            } catch (Throwable th) {
                th = th;
                DbUtil.closeCursor(r9);
                throw th;
            }
            if (readOnlyDatabase != 0) {
                try {
                    boolean zMoveToFirst = readOnlyDatabase.moveToFirst();
                    readOnlyDatabase = readOnlyDatabase;
                    if (zMoveToFirst) {
                        string = readOnlyDatabase.getString(readOnlyDatabase.getColumnIndexOrThrow(MediaTrack.ROLE_DESCRIPTION));
                        readOnlyDatabase = readOnlyDatabase;
                    }
                } catch (IllegalArgumentException e2) {
                    e = e2;
                    Log.error(TAG, "Unable to get playlist description", e);
                    readOnlyDatabase = readOnlyDatabase;
                }
            }
            DbUtil.closeCursor(readOnlyDatabase);
            return string;
        } catch (Throwable th2) {
            th = th2;
            r9 = readOnlyDatabase;
            DbUtil.closeCursor(r9);
            throw th;
        }
    }

    public static boolean canRemovePlaylist(final Context context, final Uri uri) {
        return getPlaylistFlag(context, uri, "canRemove", 1);
    }

    public static boolean canRenamePlaylist(final Context context, final Uri uri) {
        return getPlaylistFlag(context, uri, "canRename", 1);
    }

    public static boolean canLikePlaylistTracks(final Context context, final Uri uri) {
        return getPlaylistFlag(context, uri, "canLikeTracks", 1);
    }

    public static boolean canDownloadPlaylist(final Uri uri) {
        return !isRecentlyAddedPlaylist(uri);
    }

    private static boolean getPlaylistFlag(final Context context, final Uri uri, final String columnName, final int defaultValue) {
        SQLiteDatabase readOnlyDatabase = CirrusDatabase.getReadOnlyDatabase(context);
        String[] strArr = {String.valueOf(getPlaylistId(uri))};
        Cursor cursorQuery = null;
        try {
            try {
                cursorQuery = readOnlyDatabase.query("Playlist", new String[]{columnName}, "_id = ?", strArr, null, null, null);
                if (cursorQuery != null && cursorQuery.moveToFirst()) {
                    defaultValue = cursorQuery.getInt(cursorQuery.getColumnIndexOrThrow(columnName));
                }
            } catch (IllegalArgumentException e) {
                Log.error(TAG, MessageFormat.format("Unable to get playlist column {0}", columnName), e);
            }
            return 1 == defaultValue;
        } finally {
            DbUtil.closeCursor(cursorQuery);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v0, types: [android.database.sqlite.SQLiteDatabase] */
    /* JADX WARN: Type inference failed for: r0v1 */
    /* JADX WARN: Type inference failed for: r0v2 */
    /* JADX WARN: Type inference failed for: r0v3 */
    /* JADX WARN: Type inference failed for: r0v4 */
    /* JADX WARN: Type inference failed for: r0v5, types: [android.database.Cursor] */
    /* JADX WARN: Type inference failed for: r0v6, types: [android.database.Cursor] */
    /* JADX WARN: Type inference failed for: r0v7 */
    /* JADX WARN: Type inference failed for: r0v8 */
    /* JADX WARN: Type inference failed for: r0v9 */
    /* JADX WARN: Type inference failed for: r9v1 */
    /* JADX WARN: Type inference failed for: r9v2, types: [android.database.Cursor] */
    /* JADX WARN: Type inference failed for: r9v3 */
    @Nullable
    public static String getPlaylistThumbnailArtUrl(Context context, String str) throws Throwable {
        ?? readOnlyDatabase = CirrusDatabase.getReadOnlyDatabase(context);
        String[] strArr = {str};
        ?? r9 = 0;
        string = null;
        string = null;
        String string = null;
        try {
            try {
                readOnlyDatabase = readOnlyDatabase.query("Playlist", new String[]{"thumbnailArtUrl"}, "_id = ?", strArr, null, null, null);
            } catch (IllegalArgumentException e) {
                e = e;
                readOnlyDatabase = 0;
            } catch (Throwable th) {
                th = th;
                DbUtil.closeCursor(r9);
                throw th;
            }
            if (readOnlyDatabase != 0) {
                try {
                    boolean zMoveToFirst = readOnlyDatabase.moveToFirst();
                    readOnlyDatabase = readOnlyDatabase;
                    if (zMoveToFirst) {
                        string = readOnlyDatabase.getString(readOnlyDatabase.getColumnIndexOrThrow("thumbnailArtUrl"));
                        readOnlyDatabase = readOnlyDatabase;
                    }
                } catch (IllegalArgumentException e2) {
                    e = e2;
                    Log.error(TAG, "Unable to get playlist thumbnail art url", e);
                    readOnlyDatabase = readOnlyDatabase;
                }
            }
            DbUtil.closeCursor(readOnlyDatabase);
            return string;
        } catch (Throwable th2) {
            th = th2;
            r9 = readOnlyDatabase;
            DbUtil.closeCursor(r9);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v0, types: [android.database.sqlite.SQLiteDatabase] */
    /* JADX WARN: Type inference failed for: r0v1 */
    /* JADX WARN: Type inference failed for: r0v2 */
    /* JADX WARN: Type inference failed for: r0v3 */
    /* JADX WARN: Type inference failed for: r0v4 */
    /* JADX WARN: Type inference failed for: r0v5, types: [android.database.Cursor] */
    /* JADX WARN: Type inference failed for: r0v6, types: [android.database.Cursor] */
    /* JADX WARN: Type inference failed for: r0v7 */
    /* JADX WARN: Type inference failed for: r0v8 */
    /* JADX WARN: Type inference failed for: r0v9 */
    /* JADX WARN: Type inference failed for: r9v1 */
    /* JADX WARN: Type inference failed for: r9v2, types: [android.database.Cursor] */
    /* JADX WARN: Type inference failed for: r9v3 */
    @Nullable
    public static String getPlaylistBannerArtUrl(Context context, String str) throws Throwable {
        ?? readOnlyDatabase = CirrusDatabase.getReadOnlyDatabase(context);
        String[] strArr = {str};
        ?? r9 = 0;
        string = null;
        string = null;
        String string = null;
        try {
            try {
                readOnlyDatabase = readOnlyDatabase.query("Playlist", new String[]{"bannerArtUrl"}, "_id = ?", strArr, null, null, null);
            } catch (IllegalArgumentException e) {
                e = e;
                readOnlyDatabase = 0;
            } catch (Throwable th) {
                th = th;
                DbUtil.closeCursor(r9);
                throw th;
            }
            if (readOnlyDatabase != 0) {
                try {
                    boolean zMoveToFirst = readOnlyDatabase.moveToFirst();
                    readOnlyDatabase = readOnlyDatabase;
                    if (zMoveToFirst) {
                        string = readOnlyDatabase.getString(readOnlyDatabase.getColumnIndexOrThrow("bannerArtUrl"));
                        readOnlyDatabase = readOnlyDatabase;
                    }
                } catch (IllegalArgumentException e2) {
                    e = e2;
                    Log.error(TAG, "Unable to get playlist thumbnail art url", e);
                    readOnlyDatabase = readOnlyDatabase;
                }
            }
            DbUtil.closeCursor(readOnlyDatabase);
            return string;
        } catch (Throwable th2) {
            th = th2;
            r9 = readOnlyDatabase;
            DbUtil.closeCursor(r9);
            throw th;
        }
    }

    public static boolean isUpdatedSinceLastSync(final Context context, final long playlistId) {
        int i;
        Cursor cursorQuery = null;
        try {
            try {
                cursorQuery = CirrusDatabase.getReadOnlyDatabase(context).query("Playlist", new String[]{"updatedSinceLastSync"}, "_id = ?", new String[]{Long.toString(playlistId)}, null, null, null);
                i = (cursorQuery == null || !cursorQuery.moveToFirst()) ? 0 : cursorQuery.getInt(cursorQuery.getColumnIndexOrThrow("updatedSinceLastSync"));
            } catch (IllegalArgumentException e) {
                Log.error(TAG, "Unable to get updatedSinceLastSync for playlist " + playlistId, e);
                DbUtil.closeCursor(cursorQuery);
                i = 0;
            }
            return i == Integer.parseInt("1");
        } finally {
            DbUtil.closeCursor(cursorQuery);
        }
    }

    public static boolean isUserPlaylistDownloaded(final long playlistId) {
        ThreadUtil.dumpStacktraceOnMainThread();
        Playlist playlist = AmazonApplication.getLibraryItemFactory().getPlaylist(MediaProvider.UdoPlaylists.getContentUri("cirrus", playlistId));
        return playlist != null && playlist.getDownloadState() == 0;
    }

    public static boolean isLocalPlaylist(final long playlistId) {
        Playlist playlist = AmazonApplication.getLibraryItemFactory().getPlaylist(MediaProvider.UdoPlaylists.getContentUri("cirrus", playlistId));
        return playlist.getLuid() != null && playlist.getLuid().startsWith("localPlaylist");
    }

    public static void setUpdatedSinceLastSync(final Context context, final long playlistId, final boolean updated) {
        SQLiteDatabase writableDatabase = CirrusDatabase.getWritableDatabase(context);
        ContentValues contentValues = new ContentValues(1);
        contentValues.put("updatedSinceLastSync", updated ? "1" : "0");
        String[] strArr = {String.valueOf(playlistId)};
        try {
            try {
                writableDatabase.beginTransaction();
                writableDatabase.update("Playlist", contentValues, "_id = ?", strArr);
                writableDatabase.setTransactionSuccessful();
                Log.debug(TAG, "Update playlist updated_since_last_sync, playlistId = %d, updated = %s", Long.valueOf(playlistId), Boolean.valueOf(updated));
            } catch (Exception e) {
                Log.error(TAG, "Update playlist updated_since_last_sync", e);
            }
        } finally {
            writableDatabase.endTransaction();
        }
    }

    public static Cursor getNewDevicePlaylistsSinceLastSyncCursor(final Context context) {
        return CirrusDatabase.getReadOnlyDatabase(context).query("Playlist", new String[]{"_id"}, "Playlist.source =? AND Playlist.updatedSinceLastSync = ? AND (Playlist.version = '1' OR Playlist.version = 'clearVersion' OR Playlist.version = '0' OR Playlist.version = '1:0' )", new String[]{Integer.toString(1), "1"}, null, null, null);
    }

    public static Cursor getUpdatedPlaylistsSinceLastSyncCursor(final Context context) {
        return CirrusDatabase.getReadOnlyDatabase(context).query("Playlist", new String[]{"_id"}, "Playlist.source =? AND Playlist.updatedSinceLastSync = ? AND (Playlist.version != '1' AND Playlist.version != 'clearVersion' AND Playlist.version != '0' AND Playlist.version != '1:0' )", new String[]{Integer.toString(0), "1"}, null, null, null);
    }

    public static Cursor getUnifiedPlaylistTracksCursor(final Context context, final long playlistId) {
        return CirrusDatabase.getReadOnlyDatabase(context).query("Playlist INNER JOIN PlaylistTrack ON Playlist._id = PlaylistTrack.udo_playlist_id INNER JOIN Track ON PlaylistTrack.track_luid = Track.luid LEFT OUTER JOIN LocalTrackUri on LocalTrackUri.track_luid = Track.luid", new String[]{"Track.asin"}, "Playlist._id = ? and Playlist.download_state = 0", new String[]{String.valueOf(playlistId)}, null, null, "udo");
    }

    public static List<PlaylistServiceTrack> getUnifiedPlaylistTracks(final Context context, final long playlistId) {
        ArrayList arrayList = new ArrayList();
        Cursor unifiedPlaylistTracksCursor = getUnifiedPlaylistTracksCursor(context, playlistId);
        if (unifiedPlaylistTracksCursor != null) {
            try {
                if (unifiedPlaylistTracksCursor.getCount() > 0) {
                    int columnIndex = unifiedPlaylistTracksCursor.getColumnIndex("asin");
                    while (unifiedPlaylistTracksCursor.moveToNext()) {
                        String string = unifiedPlaylistTracksCursor.getString(columnIndex);
                        if (StringUtils.isNotBlank(string)) {
                            arrayList.add(PlaylistTrackUtil.getDownloadedPlaylistServiceTrack(context, string));
                        }
                    }
                }
            } finally {
                DbUtil.closeCursor(unifiedPlaylistTracksCursor);
            }
        }
        return arrayList;
    }

    public static void migrateAndUnifyDevicePlaylists(final Context context) {
        boolean zUpdateDeviceOnlyPlaylists;
        boolean z;
        if (SettingsUtil.getLastLocalUpdatedTime(context) <= 0) {
            Log.debug(TAG, "Can't migrate and unify playlists until local sync is done");
            return;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Auto migrating device playlists for unification: ");
        String str = TAG;
        sb.append(str);
        Profiler.begin(sb.toString());
        Set<Playlist> playlists = getPlaylists(context, "cirrus-local");
        if (playlists.isEmpty()) {
            Log.debug(str, "No device playlists are found, , quit user playlists auto migration process");
            PlaylistUnificationMetrics.INSTANCE.sendDeviceLibraryPlaylistsCountEvent(0);
            PlaylistConfigurationStorage.get().setMigrateAndUnifyDevicePlaylists(false);
            return;
        }
        Set<Playlist> uniqueDevicePlaylists = getUniqueDevicePlaylists(context, playlists);
        if (uniqueDevicePlaylists.isEmpty()) {
            Log.debug(str, "No unique device playlists are found");
            PlaylistConfigurationStorage.get().setMigrateAndUnifyDevicePlaylists(false);
            return;
        }
        SQLiteDatabase writableDatabase = CirrusDatabase.getWritableDatabase(context);
        if (backupPreUnificationDatabase(context, writableDatabase) != null) {
            Log.debug(str, "Backed up pre-unification database");
        }
        HashSet hashSet = new HashSet();
        for (Playlist playlist : playlists) {
            if (!uniqueDevicePlaylists.contains(playlist)) {
                hashSet.add(playlist);
            }
        }
        HashSet hashSet2 = new HashSet();
        HashMap map = new HashMap();
        try {
            try {
                Set<Playlist> playlists2 = getPlaylists(context, "cirrus");
                for (Playlist playlist2 : uniqueDevicePlaylists) {
                    Iterator<Playlist> it = playlists2.iterator();
                    while (true) {
                        if (!it.hasNext()) {
                            z = false;
                            break;
                        }
                        Playlist next = it.next();
                        if (identicalPlaylists(context, playlist2, next)) {
                            map.put(next.getId(), playlist2);
                            Log.debug(TAG, "Found matched playlists, cloud : [%s], device: [%s]", next.getId(), playlist2.getId());
                            z = true;
                            break;
                        } else if (samePlaylistName(playlist2, next)) {
                            hashSet.add(playlist2);
                        }
                    }
                    if (!z && !hashSet.contains(playlist2)) {
                        String id = playlist2.getId();
                        Cursor playlistTracksCursorWithoutSideloaded = null;
                        try {
                            try {
                                playlistTracksCursorWithoutSideloaded = getPlaylistTracksCursorWithoutSideloaded(context, "cirrus-local", Long.valueOf(id).longValue());
                                if (playlistTracksCursorWithoutSideloaded != null && playlistTracksCursorWithoutSideloaded.getCount() > 2) {
                                    hashSet2.add(playlist2);
                                    Log.debug(TAG, "device playlist %s is unique and has more than 2 tracks", id);
                                } else {
                                    Log.debug(TAG, "device playlist %s has less than 2 tracks, so we are not going to migrate it", playlist2.getId());
                                    hashSet.add(playlist2);
                                }
                            } finally {
                                DbUtil.closeCursor(playlistTracksCursorWithoutSideloaded);
                            }
                        } catch (Exception e) {
                            Log.error(TAG, "Error occurred when migrating and unifying device playlists", e);
                        }
                    }
                }
                writableDatabase.beginTransaction();
                try {
                    try {
                        zUpdateDeviceOnlyPlaylists = updateDeviceOnlyPlaylists(writableDatabase, hashSet2);
                    } finally {
                        writableDatabase.endTransaction();
                    }
                } catch (Exception unused) {
                    zUpdateDeviceOnlyPlaylists = false;
                }
                try {
                    updateMatchedDevicePlaylists(writableDatabase, map);
                    hideUnmigrateableDevicePlaylists(writableDatabase, hashSet);
                    zUpdateDeviceOnlyPlaylists = cleanupSideloadedTracksFromPlaylists(writableDatabase);
                    PlaylistUnificationMetrics.Companion companion = PlaylistUnificationMetrics.INSTANCE;
                    companion.sendDeviceLibraryPlaylistsCountEvent(playlists.size());
                    companion.sendAutoMigratedUserPlaylistsCountEvent(hashSet2.size() + map.size(), hashSet.size());
                    writableDatabase.setTransactionSuccessful();
                } catch (Exception unused2) {
                    Log.error(TAG, "Error occurred when updating device playlists for migration");
                }
                if (zUpdateDeviceOnlyPlaylists) {
                    PlaylistConfigurationStorage.get().setMigrateAndUnifyDevicePlaylists(false);
                    PlaylistSyncHelper.syncUnifiedPlaylistsToCloud(context);
                }
            } catch (Exception e2) {
                Log.error(TAG, "Failed to migrate device playlists", e2);
            }
        } finally {
            Profiler.end();
        }
    }

    private static boolean updateDeviceOnlyPlaylists(final SQLiteDatabase db, final Set<Playlist> deviceOnlyPlaylists) {
        try {
            Iterator<Playlist> it = deviceOnlyPlaylists.iterator();
            while (it.hasNext()) {
                String[] strArr = {String.valueOf(it.next().getId())};
                ContentValues contentValues = new ContentValues();
                contentValues.put("version", "1:0");
                contentValues.put("download_state", (Integer) 0);
                contentValues.put("updatedSinceLastSync", "1");
                db.update("Playlist", contentValues, "_id=?", strArr);
            }
            return true;
        } catch (Exception unused) {
            Log.error(TAG, "Error occurred when updating device only playlists for migration");
            return false;
        }
    }

    private static boolean updateMatchedDevicePlaylists(final SQLiteDatabase db, final Map<String, Playlist> devicePlaylistWithCloudMatched) {
        try {
            for (String str : devicePlaylistWithCloudMatched.keySet()) {
                ContentValues contentValues = new ContentValues();
                contentValues.put("download_state", (Integer) 0);
                contentValues.put("updatedSinceLastSync", "0");
                db.update("Playlist", contentValues, "_id = ?", new String[]{str});
                String[] strArr = {devicePlaylistWithCloudMatched.get(str).getId()};
                ContentValues contentValues2 = new ContentValues();
                contentValues2.put("download_state", (Integer) (-1));
                contentValues2.put("updatedSinceLastSync", "0");
                db.update("Playlist", contentValues2, "_id = ?", strArr);
            }
            return true;
        } catch (Exception unused) {
            Log.error(TAG, "Error occurred when updating matched playlists for migration");
            return false;
        }
    }

    private static boolean hideUnmigrateableDevicePlaylists(final SQLiteDatabase db, final Set<Playlist> unmigratablePlaylists) {
        try {
            Iterator<Playlist> it = unmigratablePlaylists.iterator();
            while (it.hasNext()) {
                String[] strArr = {it.next().getId()};
                ContentValues contentValues = new ContentValues(2);
                contentValues.put("download_state", (Integer) (-1));
                contentValues.put("updatedSinceLastSync", "0");
                db.update("Playlist", contentValues, "_id = ?", strArr);
            }
            return true;
        } catch (Exception unused) {
            Log.error(TAG, "Error occurred when updating duplicate device playlists for migration");
            return false;
        }
    }

    private static boolean cleanupSideloadedTracksFromPlaylists(final SQLiteDatabase db) {
        Cursor cursorQuery = null;
        try {
            cursorQuery = db.query("Playlist p INNER JOIN PlaylistTrack pt ON p._id = pt.udo_playlist_id INNER JOIN Track t ON pt.track_luid = t.luid INNER JOIN LocalTrackUri l1 on l1.track_luid = t.luid", new String[]{"t.asin", "t.title", "pt.udo_playlist_id", "pt.track_luid"}, "t.source = 1 and t.asin is null", null, null, null, null);
            if (cursorQuery != null) {
                while (cursorQuery.moveToNext()) {
                    long j = cursorQuery.getLong(cursorQuery.getColumnIndex("udo_playlist_id"));
                    String string = cursorQuery.getString(cursorQuery.getColumnIndex("track_luid"));
                    String string2 = cursorQuery.getString(cursorQuery.getColumnIndex("title"));
                    db.delete("PlaylistTrack", "track_luid =? ", new String[]{string});
                    Log.debug(TAG, "Removing sideloaded track [%s] from playlist [%d]", string2, Long.valueOf(j));
                }
            }
            return true;
        } catch (Exception e) {
            Log.error(TAG, "Error occurred when cleaning up sideloaded songs from playlists", e);
            return false;
        } finally {
            DbUtil.closeCursor(cursorQuery);
        }
    }

    public static File backupPreUnificationDatabase(final Context context, final SQLiteDatabase db) {
        try {
            DatabaseUpgradeHelper databaseUpgradeHelper = new DatabaseUpgradeHelper(context);
            File file = new File(db.getPath());
            File backupFile = getBackupFile(file);
            if (backupFile.exists() && backupFile.canRead()) {
                Log.verbose(TAG, "Pre-unification database backup is available: " + backupFile.getAbsolutePath());
                return backupFile;
            }
            File fileBackupDb = databaseUpgradeHelper.backupDb(file.getParent(), "CirrusMediaSource.db", "CirrusMediaSource_PrePlaylistUnification.db");
            Log.verbose(TAG, "Pre-unification database is backed up: " + fileBackupDb.getAbsolutePath());
            return fileBackupDb;
        } catch (IOException e) {
            Log.error(TAG, "Pre-unification database Backup failed: " + e.getMessage(), e);
            return null;
        }
    }

    private static File getBackupFile(final File dbPath) {
        return new File(dbPath.getParent(), "CirrusMediaSource_PrePlaylistUnification.db");
    }

    public static Set<Playlist> getUniqueDevicePlaylists(final Context context, final Set<Playlist> allDevicePlaylists) {
        boolean z;
        HashSet hashSet = new HashSet();
        try {
            try {
            } catch (Exception e) {
                Log.debug(TAG, "Failed to get unique device playlists", e);
            }
            if (allDevicePlaylists.isEmpty()) {
                Log.debug(TAG, "No device playlists are found");
                return Collections.EMPTY_SET;
            }
            for (Playlist playlist : allDevicePlaylists) {
                Iterator it = hashSet.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        z = true;
                        break;
                    }
                    Playlist playlist2 = (Playlist) it.next();
                    String str = TAG;
                    Log.debug(str, "Checking device playlist %s and %s", playlist.toString(), playlist2.toString());
                    if (identicalPlaylists(context, playlist, playlist2)) {
                        Log.debug(str, "device playlist %s and %s are identical", playlist.toString(), playlist2.toString());
                        z = false;
                        break;
                    }
                }
                if (z) {
                    hashSet.add(playlist);
                    Log.debug(TAG, "device playlist %s is unique", playlist.toString());
                }
            }
            return hashSet;
        } finally {
            DbUtil.closeCursor(null);
        }
    }

    public static boolean identicalPlaylists(final Context context, final Playlist first, final Playlist second) {
        if (first == null || second == null) {
            return false;
        }
        String name = first.getName();
        String name2 = second.getName();
        if (!samePlaylistName(first, second)) {
            Log.debug(TAG, "Names are not matched for playlists [%s] and [%s]", name, name2);
            return false;
        }
        Log.debug(TAG, "Names are matched for playlists [%s] and [%s]", name, name2);
        return containSamePlaylistTracks(context, first, second);
    }

    private static boolean samePlaylistName(final Playlist first, final Playlist second) {
        String name = first.getName();
        String name2 = second.getName();
        String strNormalizeDownloadedPlaylistName = normalizeDownloadedPlaylistName(name);
        return strNormalizeDownloadedPlaylistName != null && strNormalizeDownloadedPlaylistName.equals(normalizeDownloadedPlaylistName(name2));
    }

    private static final String normalizeDownloadedPlaylistName(final String original) {
        Matcher matcher = Pattern.compile("\\(\\d+\\)").matcher(original);
        return matcher.find() ? matcher.replaceFirst("").trim() : original;
    }

    public static boolean containSamePlaylistTracks(final Context context, final Playlist first, final Playlist second) throws Throwable {
        Cursor cursor;
        if (first == null || second == null) {
            return false;
        }
        Cursor playlistTracksCursorWithoutSideloaded = null;
        try {
            Cursor playlistTracksCursorWithoutSideloaded2 = getPlaylistTracksCursorWithoutSideloaded(context, first.getSource(), Long.valueOf(first.getId()).longValue());
            if (playlistTracksCursorWithoutSideloaded2 == null) {
                DbUtil.closeCursor(playlistTracksCursorWithoutSideloaded2);
                DbUtil.closeCursor(null);
                return false;
            }
            try {
                playlistTracksCursorWithoutSideloaded = getPlaylistTracksCursorWithoutSideloaded(context, second.getSource(), Long.valueOf(second.getId()).longValue());
                if (playlistTracksCursorWithoutSideloaded == null) {
                    DbUtil.closeCursor(playlistTracksCursorWithoutSideloaded2);
                    DbUtil.closeCursor(playlistTracksCursorWithoutSideloaded);
                    return false;
                }
                if (playlistTracksCursorWithoutSideloaded2.getCount() != playlistTracksCursorWithoutSideloaded.getCount()) {
                    DbUtil.closeCursor(playlistTracksCursorWithoutSideloaded2);
                    DbUtil.closeCursor(playlistTracksCursorWithoutSideloaded);
                    return false;
                }
                while (playlistTracksCursorWithoutSideloaded2.moveToNext()) {
                    String string = playlistTracksCursorWithoutSideloaded2.getString(playlistTracksCursorWithoutSideloaded2.getColumnIndex("track_luid"));
                    String string2 = playlistTracksCursorWithoutSideloaded2.getString(playlistTracksCursorWithoutSideloaded.getColumnIndex("track_luid"));
                    String string3 = playlistTracksCursorWithoutSideloaded2.getString(playlistTracksCursorWithoutSideloaded2.getColumnIndex("asin"));
                    String string4 = playlistTracksCursorWithoutSideloaded2.getString(playlistTracksCursorWithoutSideloaded.getColumnIndex("asin"));
                    Log.debug(TAG, "The first track has luid = %s, asin = %s;  the second track has luid = %s, asin = %s", string, string3, string2, string2);
                    if (string == null || string2 == null || !string.equals(string2)) {
                        if (string3 == null || !string3.equals(string4)) {
                            DbUtil.closeCursor(playlistTracksCursorWithoutSideloaded2);
                            DbUtil.closeCursor(playlistTracksCursorWithoutSideloaded);
                            return false;
                        }
                    }
                }
                DbUtil.closeCursor(playlistTracksCursorWithoutSideloaded2);
                DbUtil.closeCursor(playlistTracksCursorWithoutSideloaded);
                Log.debug(TAG, "playlists [%s] and [%s] contain the same tracks", first.getName(), second.getName());
                return true;
            } catch (Exception e) {
                e = e;
                cursor = playlistTracksCursorWithoutSideloaded;
                playlistTracksCursorWithoutSideloaded = playlistTracksCursorWithoutSideloaded2;
                try {
                    Log.error(TAG, "Failed to query playlist tracks", e);
                    DbUtil.closeCursor(playlistTracksCursorWithoutSideloaded);
                    DbUtil.closeCursor(cursor);
                    return false;
                } catch (Throwable th) {
                    th = th;
                    DbUtil.closeCursor(playlistTracksCursorWithoutSideloaded);
                    DbUtil.closeCursor(cursor);
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
                cursor = playlistTracksCursorWithoutSideloaded;
                playlistTracksCursorWithoutSideloaded = playlistTracksCursorWithoutSideloaded2;
                DbUtil.closeCursor(playlistTracksCursorWithoutSideloaded);
                DbUtil.closeCursor(cursor);
                throw th;
            }
        } catch (Exception e2) {
            e = e2;
            cursor = null;
        } catch (Throwable th3) {
            th = th3;
            cursor = null;
        }
    }

    private static Cursor getPlaylistTracksCursorWithoutSideloaded(final Context context, final String source, final long playlistId) {
        return context.getContentResolver().query(MediaProvider.UdoPlaylistTracks.getContentUri(source, playlistId), new String[]{"udo_playlist_id", "PlaylistTrack.track_luid", "udo", "asin"}, "Track.asin is not null ", null, "udo");
    }

    public static Set<Playlist> getPlaylists(Context context, String str) {
        Cursor cursorQuery = CirrusDatabase.getReadOnlyDatabase(context).query("Playlist", new String[]{"_id", "luid", "name"}, "source = ? ", new String[]{String.valueOf(!"cirrus".equals(str) ? 1 : 0)}, null, null, null);
        HashSet hashSet = new HashSet();
        if (cursorQuery != null) {
            try {
                if (cursorQuery.getCount() != 0) {
                    while (cursorQuery.moveToNext()) {
                        int columnIndex = cursorQuery.getColumnIndex("_id");
                        int columnIndex2 = cursorQuery.getColumnIndex("luid");
                        int columnIndex3 = cursorQuery.getColumnIndex("name");
                        long j = cursorQuery.getLong(columnIndex);
                        String string = cursorQuery.getString(columnIndex2);
                        String string2 = cursorQuery.getString(columnIndex3);
                        Playlist playlist = new Playlist(AmazonApplication.getLibraryItemFactory(), MediaProvider.UdoPlaylists.getContentUri(str, j));
                        playlist.setId(Long.toString(j));
                        playlist.setLuid(string);
                        playlist.setName(string2);
                        hashSet.add(playlist);
                    }
                    return hashSet;
                }
            } finally {
                DbUtil.closeCursor(cursorQuery);
            }
        }
        return Collections.EMPTY_SET;
    }
}
