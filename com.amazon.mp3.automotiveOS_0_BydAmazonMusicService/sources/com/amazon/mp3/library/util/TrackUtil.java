package com.amazon.mp3.library.util;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import androidx.annotation.Nullable;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.download.generator.LocalLuidGenerator;
import com.amazon.mp3.library.adapter.TrackListAdapter;
import com.amazon.mp3.library.item.MusicTrack;
import com.amazon.mp3.library.item.factory.CirrusSourceLibraryItemFactory;
import com.amazon.mp3.library.provider.MediaProvider;
import com.amazon.mp3.library.provider.source.cirrus.CirrusDatabase;
import com.amazon.mp3.library.provider.source.local.DeleteUtil;
import com.amazon.mp3.prime.ContentCatalogStatus;
import com.amazon.mp3.prime.ContentOwnershipStatus;
import com.amazon.mp3.prime.ContentUnavailableReason;
import com.amazon.mp3.prime.PrimePlaylistUtil;
import com.amazon.mp3.util.ContentAccessUtil;
import com.amazon.mp3.util.DbUtil;
import com.amazon.mp3.util.IdGenerator;
import com.amazon.mp3.util.Log;
import com.amazon.music.find.utils.PageUriUtils;
import com.amazon.music.views.library.metadata.TrackMetadata;
import com.amazon.music.views.library.providers.ContentEnabilityProvider;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;

/* JADX INFO: loaded from: classes3.dex */
public class TrackUtil {
    private static final String TAG = "TrackUtil";

    public static Uri findLocalTrackContentUriByAsin(final Context context, final String asin) {
        return findLocalTrackContentUri(context, "asin = ?", new String[]{asin});
    }

    public static Uri findLocalTrackContentUriByLuid(final Context context, final String luid) {
        return findLocalTrackContentUri(context, "luid = ?", new String[]{luid});
    }

    public static Uri findLocalTrackByTrackFileLocationUri(final SQLiteDatabase readOnlyDb, final String trackLocalUri) {
        String str = TAG;
        Log.verbose(str, "findLocalTrackByTrackFileLocationUri: " + trackLocalUri);
        Cursor cursorQuery = readOnlyDb.query("LocalTrackUri INNER JOIN Track ON luid = track_luid", new String[]{"track_luid", PageUriUtils.SOURCE_QUERY_PARAM}, "local_uri =? ", new String[]{trackLocalUri}, null, null, null);
        if (cursorQuery != null) {
            try {
                if (cursorQuery.moveToFirst()) {
                    String string = cursorQuery.getString(0);
                    if (string == null) {
                        Log.debug(str, "Track luid is null for " + trackLocalUri);
                        cursorQuery.close();
                        return null;
                    }
                    Uri contentUri = MediaProvider.Tracks.getContentUri(MediaProvider.CirrusBaseColumns.Source.convertToSourceId(cursorQuery.getInt(1)), string);
                    Log.verbose(str, "findLocalTrackByTrackFileLocationUri: " + trackLocalUri + ", trackUri: " + contentUri);
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
        Log.debug(str, "Track luid not found for " + trackLocalUri);
        if (cursorQuery != null) {
            cursorQuery.close();
        }
        return null;
    }

    public static String getTrackLuid(Context context, Uri trackUri) {
        Validate.notNull(trackUri);
        Cursor cursorQuery = context.getContentResolver().query(trackUri, new String[]{"luid"}, null, null, null);
        if (cursorQuery != null) {
            try {
                if (cursorQuery.moveToFirst()) {
                    String string = cursorQuery.getString(cursorQuery.getColumnIndexOrThrow("luid"));
                    cursorQuery.close();
                    return string;
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
        Log.verbose(TAG, "The track is not found: " + trackUri);
        if (cursorQuery == null) {
            return null;
        }
        cursorQuery.close();
        return null;
    }

    @Nullable
    private static Uri findLocalTrackContentUri(final Context context, final String selection, final String[] selectionArgs) {
        String[] strArr = {"_id", PageUriUtils.SOURCE_QUERY_PARAM};
        Cursor cursorQuery = context.getContentResolver().query(MediaProvider.Tracks.getContentUri("cirrus-local"), strArr, selection, selectionArgs, null);
        if (cursorQuery != null) {
            try {
                if (cursorQuery.moveToFirst()) {
                    Uri contentUri = MediaProvider.Tracks.getContentUri(MediaProvider.CirrusBaseColumns.Source.convertToSourceId(cursorQuery.getInt(cursorQuery.getColumnIndex(PageUriUtils.SOURCE_QUERY_PARAM))), cursorQuery.getLong(cursorQuery.getColumnIndex("_id")));
                    cursorQuery.close();
                    return contentUri;
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
        if (cursorQuery == null) {
            return null;
        }
        cursorQuery.close();
        return null;
    }

    @Nullable
    public static Uri findLocalOwnedTrackContentUriByAsin(final Context context, final String asin) {
        Validate.notNull(asin);
        String str = TAG;
        Log.verbose(str, "findLocalOwnedTrackContentUriByAsin: " + asin);
        Cursor cursorQuery = CirrusDatabase.getReadOnlyDatabase(context).query("Track", new String[]{"_id", PageUriUtils.SOURCE_QUERY_PARAM, "asin", "luid", "title", "download_state", "ownership_status"}, "asin=? AND ownership_status=? AND source=?", new String[]{asin, String.valueOf(ContentOwnershipStatus.OWNED.getValue()), String.valueOf(1)}, null, null, null);
        if (cursorQuery != null) {
            try {
                if (cursorQuery.moveToFirst()) {
                    long j = cursorQuery.getLong(cursorQuery.getColumnIndex("_id"));
                    String strConvertToSourceId = MediaProvider.CirrusBaseColumns.Source.convertToSourceId(cursorQuery.getInt(cursorQuery.getColumnIndex(PageUriUtils.SOURCE_QUERY_PARAM)));
                    Log.verbose(str, "findLocalOwnedTrackContentUriByAsin found: " + j + ", asin: " + cursorQuery.getString(cursorQuery.getColumnIndex("asin")) + ", luid: " + cursorQuery.getString(cursorQuery.getColumnIndex("luid")) + ", title: " + cursorQuery.getString(cursorQuery.getColumnIndex("title")) + ", downloadState: " + cursorQuery.getInt(cursorQuery.getColumnIndex("download_state")) + ", ownership: " + cursorQuery.getInt(cursorQuery.getColumnIndex("ownership_status")));
                    Uri contentUri = MediaProvider.Tracks.getContentUri(strConvertToSourceId, j);
                    cursorQuery.close();
                    return contentUri;
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
        if (cursorQuery == null) {
            return null;
        }
        cursorQuery.close();
        return null;
    }

    @Nullable
    public static Uri findLocalDownloadedCatalogTrackContentUriByAsin(final Context context, final String asin) {
        Validate.notNull(asin);
        String str = TAG;
        Log.verbose(str, "findLocalDownloadedCatalogTrackContentUriByAsin: " + asin);
        Cursor cursorQuery = CirrusDatabase.getReadOnlyDatabase(context).query("Track", new String[]{"_id", PageUriUtils.SOURCE_QUERY_PARAM, "asin", "luid", "title", "download_state", "ownership_status"}, "asin=? AND prime_status=? OR prime_status=? AND download_state=? AND source=?", new String[]{asin, String.valueOf(ContentCatalogStatus.PRIME.getValue()), String.valueOf(ContentCatalogStatus.HAWKFIRE.getValue()), String.valueOf(0), String.valueOf(1)}, null, null, null);
        if (cursorQuery != null) {
            try {
                if (cursorQuery.moveToFirst()) {
                    long j = cursorQuery.getLong(cursorQuery.getColumnIndex("_id"));
                    String strConvertToSourceId = MediaProvider.CirrusBaseColumns.Source.convertToSourceId(cursorQuery.getInt(cursorQuery.getColumnIndex(PageUriUtils.SOURCE_QUERY_PARAM)));
                    Log.verbose(str, "found: " + j + ", asin: " + cursorQuery.getString(cursorQuery.getColumnIndex("asin")) + ", luid: " + cursorQuery.getString(cursorQuery.getColumnIndex("luid")) + ", title: " + cursorQuery.getString(cursorQuery.getColumnIndex("title")) + ", downloadState: " + cursorQuery.getInt(cursorQuery.getColumnIndex("download_state")) + ", ownership: " + cursorQuery.getInt(cursorQuery.getColumnIndex("ownership_status")));
                    Uri contentUri = MediaProvider.Tracks.getContentUri(strConvertToSourceId, j);
                    cursorQuery.close();
                    return contentUri;
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
        if (cursorQuery == null) {
            return null;
        }
        cursorQuery.close();
        return null;
    }

    public static String getTrackLuidByLocalFileLocation(final SQLiteDatabase db, final String trackLocalUri, int source) {
        Cursor cursorQuery = db.query("LocalTrackUri INNER JOIN Track ON luid = track_luid", new String[]{"track_luid"}, "local_uri =?  AND source =? ", new String[]{trackLocalUri, Integer.toString(source)}, null, null, null);
        if (cursorQuery != null) {
            try {
                if (cursorQuery.moveToFirst()) {
                    String string = cursorQuery.getString(0);
                    cursorQuery.close();
                    return string;
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

    public static List<MusicTrack> getMissingSDCardTracks(final Context context, final String source) {
        Cursor missingSDCardCursor = getMissingSDCardCursor(context, source);
        ArrayList arrayList = new ArrayList();
        while (missingSDCardCursor != null) {
            try {
                if (!missingSDCardCursor.moveToNext()) {
                    break;
                }
                MusicTrack track = AmazonApplication.getLibraryItemFactory().getTrack(missingSDCardCursor);
                if (ContentAccessUtil.getCatalogContentUnavailableReason(track, ContentAccessUtil.ContentAccessOperation.PLAY_OFFLINE) == ContentUnavailableReason.FILE_DOES_NOT_EXIST_ON_DISK) {
                    Log.debug(TAG, "Adding a missing SD card track to the list: " + track);
                    arrayList.add(track);
                }
            } finally {
                DbUtil.closeCursor(missingSDCardCursor);
            }
        }
        return arrayList;
    }

    public static List<MusicTrack> getKatanaQualityDownloadedTracks(final Context context, final String source) {
        Cursor cursorQuery = context.getContentResolver().query(MediaProvider.Tracks.getFilterContentUri(source, ""), TrackListAdapter.getDefaultProjection(), "local_uri IS NOT NULL AND asin IS NOT NULL", null, null);
        ArrayList arrayList = new ArrayList();
        while (cursorQuery != null) {
            try {
                if (!cursorQuery.moveToNext()) {
                    break;
                }
                MusicTrack track = AmazonApplication.getLibraryItemFactory().getTrack(cursorQuery);
                if (ContentAccessUtil.getCatalogContentUnavailableReason(track, ContentAccessUtil.ContentAccessOperation.PLAY_OFFLINE) == ContentUnavailableReason.CUSTOMER_HAS_NO_KATANA_SUBSCRIPTION) {
                    Log.debug(TAG, "Adding a katana downloaded track to the list: " + track);
                    arrayList.add(track);
                }
            } finally {
                DbUtil.closeCursor(cursorQuery);
            }
        }
        return arrayList;
    }

    public static List<MusicTrack> getAllTracks(final Context context, final String source) {
        Cursor cursorQuery = context.getContentResolver().query(MediaProvider.Tracks.getFilterContentUri(source, ""), TrackListAdapter.getDefaultProjection(), "local_uri IS NOT NULL AND asin IS NOT NULL AND download_state != 5", null, null);
        ArrayList arrayList = new ArrayList();
        while (cursorQuery != null) {
            try {
                if (!cursorQuery.moveToNext()) {
                    break;
                }
                MusicTrack track = AmazonApplication.getLibraryItemFactory().getTrack(cursorQuery);
                if (track.getDownloadState() != 5) {
                    arrayList.add(track);
                }
            } finally {
                DbUtil.closeCursor(cursorQuery);
            }
        }
        return arrayList;
    }

    public static boolean isSDCardMissingContent(final Context context, final String source) {
        Cursor missingSDCardCursor = getMissingSDCardCursor(context, source);
        while (missingSDCardCursor != null) {
            try {
                try {
                    if (!missingSDCardCursor.moveToNext()) {
                        break;
                    }
                    MusicTrack track = AmazonApplication.getLibraryItemFactory().getTrack(missingSDCardCursor);
                    if (ContentAccessUtil.getCatalogContentUnavailableReason(track, ContentAccessUtil.ContentAccessOperation.PLAY_OFFLINE) == ContentUnavailableReason.FILE_DOES_NOT_EXIST_ON_DISK) {
                        Log.debug(TAG, "Found a missing SD card track " + track);
                        return true;
                    }
                } catch (Exception e) {
                    Log.error(TAG, e.getMessage());
                }
            } finally {
                DbUtil.closeCursor(missingSDCardCursor);
            }
        }
        DbUtil.closeCursor(missingSDCardCursor);
        Log.debug(TAG, "Checked all " + missingSDCardCursor.getCount() + " tracks. No missing SD Card content found.");
        return false;
    }

    private static Cursor getMissingSDCardCursor(final Context context, final String source) {
        return context.getContentResolver().query(MediaProvider.Tracks.getFilterContentUri(source, ""), TrackListAdapter.getDefaultProjection(), TrackListAdapter.getDefaultSelection() + " AND local_uri IS NOT NULL AND asin IS NOT NULL", new String[]{String.valueOf(300)}, null);
    }

    public static String getLocalTrackLuidByLocalFileLocation(final SQLiteDatabase db, final String trackLocalUri) {
        Cursor cursorQuery = db.query("LocalTrackUri INNER JOIN Track ON luid = track_luid", new String[]{"track_luid"}, "local_uri =?  AND source =? ", new String[]{trackLocalUri, Integer.toString(1)}, null, null, null);
        if (cursorQuery != null) {
            try {
                if (cursorQuery.moveToFirst()) {
                    String string = cursorQuery.getString(0);
                    cursorQuery.close();
                    return string;
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

    public static boolean containsAtLeastOneAvailableTrack(final Cursor cursor, final boolean isRemote) {
        if (cursor != null && cursor.moveToFirst()) {
            MusicTrack musicTrack = new MusicTrack(AmazonApplication.getLibraryItemFactory(), null);
            do {
                AmazonApplication.getLibraryItemFactory().populateTrack(cursor, musicTrack);
                if (isRemote ? musicTrack.isAvailable() : musicTrack.isAvailableOffline()) {
                    return true;
                }
            } while (cursor.moveToNext());
        }
        return false;
    }

    public static boolean containsAtLeastOneAvailableTrack(List<TrackMetadata> trackMetadataList, Boolean isRemote, final ContentEnabilityProvider contentEnabilityProvider) {
        boolean zIsAvailable;
        if (trackMetadataList == null) {
            return false;
        }
        for (TrackMetadata trackMetadata : trackMetadataList) {
            MusicTrack trackIncludingPrimePlaylistTrack = AmazonApplication.getLibraryItemFactory().getTrackIncludingPrimePlaylistTrack(trackMetadata.getUri());
            if (trackIncludingPrimePlaylistTrack != null) {
                Boolean isOwned = trackMetadata.getIsOwned();
                if (isRemote.booleanValue()) {
                    zIsAvailable = trackIncludingPrimePlaylistTrack.isAvailable();
                } else {
                    zIsAvailable = trackIncludingPrimePlaylistTrack.isAvailableOffline() || (isOwned != null && isOwned.booleanValue());
                }
                if (zIsAvailable) {
                    return true;
                }
            }
        }
        return false;
    }

    public static String getTrackLuidByMatchHash(final Context context, final String source, final long matchHash) {
        int columnIndex;
        Cursor cursorQuery = CirrusDatabase.getReadOnlyDatabase(context).query("Track", new String[]{"Track.luid"}, "Track.source = ? and Track.match_hash = ?", new String[]{source, Long.toString(matchHash)}, null, null, null);
        if (cursorQuery != null) {
            try {
                if (cursorQuery.moveToNext() && (columnIndex = cursorQuery.getColumnIndex("luid")) != -1) {
                    return cursorQuery.getString(columnIndex);
                }
            } finally {
                DbUtil.closeCursor(cursorQuery);
            }
        }
        DbUtil.closeCursor(cursorQuery);
        return null;
    }

    public static void deleteUserPlaylistTrackFromDeviceIfPossible(final Context context, final String trackId) throws Throwable {
        MusicTrack track;
        String lastPathSegment;
        String trackLuidByMatchHash;
        if (PrimePlaylistUtil.isAsin(trackId)) {
            track = AmazonApplication.getLibraryItemFactory().getTrackForAsin(trackId);
        } else {
            track = AmazonApplication.getLibraryItemFactory().getTrack(MediaProvider.Tracks.getContentUri("cirrus", trackId));
        }
        if (track != null) {
            trackLuidByMatchHash = getTrackLuidByMatchHash(context, Integer.toString(1), track.getMatchHash());
            Log.debug(TAG, "Local track luid = [%s] found for in library track [%s]", trackLuidByMatchHash, trackId);
        } else {
            String str = TAG;
            Log.debug(str, "Track [%s] not found in library", trackId);
            Uri uriFindLocalTrackUriByAsin = findLocalTrackUriByAsin(context, trackId);
            if (uriFindLocalTrackUriByAsin != null && MediaProvider.Tracks.isValidTrackUri(uriFindLocalTrackUriByAsin)) {
                lastPathSegment = uriFindLocalTrackUriByAsin.getLastPathSegment();
                Log.debug(str, "Local track luid = [%s] found for not-in-library track [%s]", lastPathSegment, trackId);
            } else {
                lastPathSegment = null;
                Log.debug(str, "Local track not found for not-in-library track [%s]", trackId);
            }
            trackLuidByMatchHash = lastPathSegment;
        }
        if (StringUtils.isNotBlank(trackLuidByMatchHash)) {
            DeleteUtil.deleteTracksFromDevice(context, MediaProvider.Tracks.getContentUri("cirrus-local", trackLuidByMatchHash));
            Log.debug(TAG, "track %s is deletable, downloadedTrackLuid = %s", trackId, trackLuidByMatchHash);
        }
    }

    public static final String findObjectIdByTrackLocalLuid(final Context context, final String luid) {
        if (StringUtils.isBlank(luid) || !LocalLuidGenerator.isLocalLuid(luid)) {
            Log.debug(TAG, "%s is not a local track luid", luid);
            return luid;
        }
        Cursor cursor = null;
        try {
            try {
                Cursor cursorQuery = CirrusDatabase.getReadOnlyDatabase(context).query("LocalTrackUri INNER JOIN Track ON luid = track_luid", new String[]{"Track.asin"}, "track_luid =? ", new String[]{luid}, null, null, null);
                if (cursorQuery != null) {
                    try {
                        if (cursorQuery.moveToFirst()) {
                            String string = cursorQuery.getString(cursorQuery.getColumnIndex("asin"));
                            if (StringUtils.isBlank(string)) {
                                Log.debug(TAG, "Track [%s] is sideloaded", luid);
                                DbUtil.closeCursor(cursorQuery);
                                return null;
                            }
                            MusicTrack trackForAsin = new CirrusSourceLibraryItemFactory(context).getTrackForAsin(string);
                            String luid2 = trackForAsin != null ? trackForAsin.getLuid() : string;
                            Log.debug(TAG, "cloud track found for localLuid = %s, cloudTrackLuid = %s, asin = %s", luid, luid2, string);
                            DbUtil.closeCursor(cursorQuery);
                            return luid2;
                        }
                    } catch (Exception e) {
                        e = e;
                        cursor = cursorQuery;
                        Log.error(TAG, "No cloud track is found for " + luid, e);
                        DbUtil.closeCursor(cursor);
                    } catch (Throwable th) {
                        th = th;
                        cursor = cursorQuery;
                        DbUtil.closeCursor(cursor);
                        throw th;
                    }
                }
                DbUtil.closeCursor(cursorQuery);
            } catch (Exception e2) {
                e = e2;
            }
            return luid;
        } catch (Throwable th2) {
            th = th2;
        }
    }

    public static final String findAsinByTrackLocalLuid(final Context context, final String luid) {
        String strSubstring;
        String strReplace;
        Cursor cursorQuery;
        if (StringUtils.isBlank(luid)) {
            Log.warning(TAG, "Can't find asin for null luid ");
            return "";
        }
        Cursor cursor = null;
        if (IdGenerator.isPrimeLuid(luid)) {
            strSubstring = luid.replace("prime-", "");
        } else {
            strSubstring = (IdGenerator.isLocalLuid(luid) && (strReplace = luid.replace("local-", "")) != null && strReplace.contains("-")) ? strReplace.substring(strReplace.indexOf("-") + 1) : null;
        }
        if (strSubstring != null && strSubstring.matches(".*[^0-9\\-].*?")) {
            return strSubstring;
        }
        try {
            cursorQuery = CirrusDatabase.getReadOnlyDatabase(context).query("LocalTrackUri INNER JOIN Track ON luid = track_luid", new String[]{"Track.asin"}, "track_luid =? ", new String[]{luid}, null, null, null);
            if (cursorQuery != null) {
                try {
                    try {
                        if (cursorQuery.moveToFirst()) {
                            String string = cursorQuery.getString(cursorQuery.getColumnIndex("asin"));
                            DbUtil.closeCursor(cursorQuery);
                            return string;
                        }
                    } catch (Throwable th) {
                        th = th;
                        cursor = cursorQuery;
                        DbUtil.closeCursor(cursor);
                        throw th;
                    }
                } catch (Exception e) {
                    e = e;
                    Log.error(TAG, "Can't find asin found for track: " + luid, e);
                }
            }
        } catch (Exception e2) {
            e = e2;
            cursorQuery = null;
        } catch (Throwable th2) {
            th = th2;
            DbUtil.closeCursor(cursor);
            throw th;
        }
        DbUtil.closeCursor(cursorQuery);
        return null;
    }

    public static final boolean localTrackRemovedFromOfflineLibrary(final Context context, final String luid) {
        if (!StringUtils.isBlank(luid) && LocalLuidGenerator.isLocalLuid(luid)) {
            SQLiteDatabase readOnlyDatabase = CirrusDatabase.getReadOnlyDatabase(context);
            Cursor cursorQuery = null;
            try {
                try {
                    cursorQuery = readOnlyDatabase.query("Track", new String[]{"Track.asin", "Track.playlist_track_status"}, "luid =? ", new String[]{luid}, null, null, null);
                    if (cursorQuery != null && cursorQuery.moveToFirst() && MusicTrack.PlaylistTrackStatus.LOCAL_TRACK_IN_PLAYLIST_NOT_IN_OFFLINE_LIBRARY.getValue() == cursorQuery.getInt(cursorQuery.getColumnIndex("playlist_track_status"))) {
                        Log.debug(TAG, "Track [%s] was removed from library", luid);
                        return true;
                    }
                } catch (Exception e) {
                    Log.error(TAG, "Error occurred when querying downloadedTracksDeletedFromLibrary for track: " + luid, e);
                }
            } finally {
                DbUtil.closeCursor(cursorQuery);
            }
        }
        return false;
    }

    public static Uri findLocalTrackUriByAsin(final Context context, final String asin) throws Throwable {
        Cursor cursorQuery;
        Throwable th;
        try {
            cursorQuery = CirrusDatabase.getReadOnlyDatabase(context).query("Track", new String[]{"luid", PageUriUtils.SOURCE_QUERY_PARAM}, "source = 1 AND luid NOT NULL  AND asin = ?", new String[]{asin}, null, null, null);
            if (cursorQuery != null) {
                try {
                    try {
                        if (cursorQuery.moveToFirst()) {
                            Uri contentUri = MediaProvider.Tracks.getContentUri(MediaProvider.CirrusBaseColumns.Source.convertToSourceId(1), cursorQuery.getString(cursorQuery.getColumnIndex("luid")));
                            DbUtil.closeCursor(cursorQuery);
                            return contentUri;
                        }
                    } catch (Throwable th2) {
                        th = th2;
                        DbUtil.closeCursor(cursorQuery);
                        throw th;
                    }
                } catch (Exception e) {
                    e = e;
                    Log.error(TAG, "Error occurred when getting local track Uri for asin = [" + asin + "] ", e);
                }
            }
        } catch (Exception e2) {
            e = e2;
            cursorQuery = null;
        } catch (Throwable th3) {
            cursorQuery = null;
            th = th3;
            DbUtil.closeCursor(cursorQuery);
            throw th;
        }
        DbUtil.closeCursor(cursorQuery);
        return null;
    }

    public static String getTrackLuidByLocalAsin(final SQLiteDatabase db, final String asin, int source) {
        if (asin == null) {
            return null;
        }
        Cursor cursorQuery = db.query("LocalTrackUri INNER JOIN Track ON luid = track_luid", new String[]{"track_luid"}, "track_asin =?  AND source =? ", new String[]{asin, Integer.toString(source)}, null, null, null);
        if (cursorQuery != null) {
            try {
                if (cursorQuery.moveToFirst()) {
                    String string = cursorQuery.getString(0);
                    cursorQuery.close();
                    return string;
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
        Log.debug(TAG, "Track luid not found for " + asin);
        if (cursorQuery != null) {
            cursorQuery.close();
        }
        return null;
    }
}
