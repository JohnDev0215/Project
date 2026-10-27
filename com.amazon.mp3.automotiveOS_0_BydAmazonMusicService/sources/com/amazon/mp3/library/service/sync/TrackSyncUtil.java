package com.amazon.mp3.library.service.sync;

import UpsellInterface.v1_0.UpsellType;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.text.TextUtils;
import androidx.core.app.NotificationCompat;
import com.amazon.mp3.configuration.IntlConfiguration;
import com.amazon.mp3.download.MusicDownloader;
import com.amazon.mp3.download.generator.LocalLuidGenerator;
import com.amazon.mp3.library.adapter.TrackListAdapter;
import com.amazon.mp3.library.item.MusicTrack;
import com.amazon.mp3.library.item.TrackStatus;
import com.amazon.mp3.library.item.factory.CirrusSourceLibraryItemFactory;
import com.amazon.mp3.library.provider.MediaProvider;
import com.amazon.mp3.library.provider.source.cirrus.dbutils.LocalTrackUtil;
import com.amazon.mp3.library.util.TrackUtil;
import com.amazon.mp3.prime.ContentCatalogStatus;
import com.amazon.mp3.prime.ContentOwnershipStatus;
import com.amazon.mp3.prime.PrimePlaylistUtil;
import com.amazon.mp3.prime.browse.metadata.CatalogStatusTiers;
import com.amazon.mp3.service.metrics.MetricsLogger;
import com.amazon.mp3.util.DbUtil;
import com.amazon.mp3.util.Log;
import com.amazon.music.destination.parser.ParserUtil;
import com.amazon.music.find.utils.PageUriUtils;
import com.amazon.music.metrics.mts.event.definition.flex.FlexEvent;
import com.google.android.gms.common.util.CollectionUtils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes3.dex */
public class TrackSyncUtil {
    private static final String TAG = "TrackSyncUtil";
    private static final String[] TRACK_LOCAL_STORE_PROJECTION = {"title", "track_num", "disc_num", "duration", "size", "album", "album_id", "album_artist", "album_artist_id", "artist", "artist_id", ParserUtil.GENRE_SEGMENT_NAME, "genre_id", "match_hash", "sort_title", "sort_album", "sort_artist", "sort_album_artist", "lyrics_state", "prime_status", "ownership_status", "asin", "album_asin", "artist_asin", "is_explicit", IntlConfiguration.MARKETPLACE, "content_encoding", "playlist_track_status", "catalog_status_tiers", "album_playmode_eligibility", "playmode_eligibility"};

    public static void insertLocalTrackByLuid(SQLiteDatabase db, String luid, String localUri) {
        ContentValues contentValues = new ContentValues();
        Cursor cursorQuery = null;
        try {
            cursorQuery = db.query("Track", TRACK_LOCAL_STORE_PROJECTION, "luid=?", new String[]{luid}, null, null, null);
            if (cursorQuery != null && cursorQuery.moveToFirst()) {
                DatabaseUtils.cursorRowToContentValues(cursorQuery, contentValues);
            }
            DbUtil.closeCursor(cursorQuery);
            if (contentValues.size() > 0) {
                insertLocalTrack(db, contentValues, localUri);
            }
        } catch (Throwable th) {
            DbUtil.closeCursor(cursorQuery);
            throw th;
        }
    }

    public static void insertLocalTrackByAsin(SQLiteDatabase db, String asin, String localUri) {
        ContentValues contentValues = new ContentValues();
        try {
            Cursor cursorQuery = db.query("Track", TRACK_LOCAL_STORE_PROJECTION, "asin=?", new String[]{asin}, null, null, null);
            int columnIndex = cursorQuery.getColumnIndex("playlist_track_status");
            int columnIndex2 = cursorQuery.getColumnIndex("ownership_status");
            int value = MusicTrack.PlaylistTrackStatus.NOT_CATALOG_TRACK.getValue();
            int value2 = ContentOwnershipStatus.NOT_IN_LIBRARY.getValue();
            if (cursorQuery.moveToFirst()) {
                DatabaseUtils.cursorRowToContentValues(cursorQuery, contentValues);
                do {
                    if (value < MusicTrack.PlaylistTrackStatus.CATALOG_TRACK_NOT_IN_LIBRARY.getValue()) {
                        value = cursorQuery.getInt(columnIndex);
                    }
                    value2 = cursorQuery.getInt(columnIndex2);
                    if (!cursorQuery.moveToNext()) {
                        break;
                    }
                } while (value2 > 300);
            }
            if (value2 < 300) {
                contentValues.put("ownership_status", Integer.valueOf(value2));
                contentValues.put("playlist_track_status", Integer.valueOf(MusicTrack.PlaylistTrackStatus.NOT_CATALOG_TRACK.getValue()));
            } else if (value > MusicTrack.PlaylistTrackStatus.NOT_CATALOG_TRACK.getValue()) {
                contentValues.put("ownership_status", Integer.valueOf(ContentOwnershipStatus.NOT_IN_LIBRARY.getValue()));
                contentValues.put("playlist_track_status", Integer.valueOf(value));
            }
            DbUtil.closeCursor(cursorQuery);
            if (contentValues.size() > 0) {
                insertLocalTrack(db, contentValues, localUri);
            }
        } catch (Throwable th) {
            DbUtil.closeCursor(null);
            throw th;
        }
    }

    private static void insertLocalTrack(SQLiteDatabase db, ContentValues values, String localUri) {
        String asString = values.getAsString("asin");
        String existingLocalTrackLuid = getExistingLocalTrackLuid(db, localUri);
        if (TextUtils.isEmpty(existingLocalTrackLuid) && !TextUtils.isEmpty(asString)) {
            existingLocalTrackLuid = getExistingLocalTrackLuidForAsin(db, asString);
        }
        if (!TextUtils.isEmpty(existingLocalTrackLuid)) {
            db.delete("Track", "luid=?", new String[]{existingLocalTrackLuid});
            new LocalTrackUtil(db).deleteTrackLocalUrisByLuids(new String[]{existingLocalTrackLuid});
        } else {
            existingLocalTrackLuid = LocalLuidGenerator.generateLocalTrackLuid(asString, values.getAsString(IntlConfiguration.MARKETPLACE), localUri);
            checkLocalLuidCollision(db, existingLocalTrackLuid, localUri);
        }
        values.put("luid", existingLocalTrackLuid);
        values.put(PageUriUtils.SOURCE_QUERY_PARAM, (Integer) 1);
        values.put(NotificationCompat.CATEGORY_STATUS, TrackStatus.AVAILABLE.toString());
        values.put("date_created", Long.valueOf(System.currentTimeMillis()));
        values.put("download_state", (Integer) 0);
        db.insertWithOnConflict("Track", "_id", values, 5);
        values.clear();
        values.put("track_luid", existingLocalTrackLuid);
        values.put("local_uri", localUri);
        db.insertWithOnConflict("LocalTrackUri", null, values, 5);
    }

    private static void checkLocalLuidCollision(SQLiteDatabase db, String newLocalLuid, String newLocalUri) {
        if (db == null || TextUtils.isEmpty(newLocalLuid) || TextUtils.isEmpty(newLocalUri)) {
            return;
        }
        Cursor cursorQuery = db.query("LocalTrackUri", new String[]{"track_luid", "track_asin", "local_uri"}, "track_luid=?", new String[]{newLocalLuid}, null, null, null);
        try {
            if (cursorQuery.moveToFirst()) {
                String string = cursorQuery.getString(cursorQuery.getColumnIndex("local_uri"));
                if (!TextUtils.isEmpty(string) && !newLocalUri.equals(string)) {
                    Log.error(TAG, "WARNING: Same localLuid already present and pointing to a different track, this may cause offline issues! newLocalLuid = " + newLocalLuid + " newLocalUri = " + newLocalUri + " previousLocalUri = " + string);
                    MetricsLogger.sendEvent(FlexEvent.builder("flexEventLocalLuidCollision").build());
                }
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
    }

    public static boolean parseTrackLocalUri(final String localUri, final Map<String, String> values) {
        Matcher matcher = Pattern.compile(".*/((([A-Z0-9]+)_\\(disc_(.*)\\)_([0-9]+)_.*)\\.mp3)").matcher(localUri);
        if (!matcher.matches() || matcher.groupCount() <= 0) {
            return false;
        }
        String strGroup = matcher.group(1);
        Log.verbose(TAG, "file: " + strGroup);
        values.put("fileName", strGroup);
        String strGroup2 = matcher.group(3);
        String strGroup3 = matcher.group(4);
        String strGroup4 = matcher.group(5);
        values.put("asin", strGroup2);
        values.put("disc_num", strGroup3);
        values.put("track_num", strGroup4);
        return true;
    }

    public static String getAsinFromTrackLocalUri(final String localUri) {
        HashMap map = new HashMap();
        if (parseTrackLocalUri(localUri, map)) {
            return (String) map.get("asin");
        }
        return null;
    }

    public static boolean updateDownloadedContentMetadata(final Context context, final SQLiteDatabase database) {
        return updateDownloadedContentMetadata(context, database, context.getContentResolver().query(MediaProvider.Tracks.getFilterContentUri("cirrus-local", ""), null, TrackListAdapter.getDefaultSelection(), TrackListAdapter.getDefaultSelectionArgs(), null));
    }

    private static boolean updateDownloadedContentMetadata(final Context context, final SQLiteDatabase database, final Cursor offlineCursor) {
        if (offlineCursor == null) {
            return false;
        }
        try {
            Log.debug(TAG, "Number of downloaded tracks updated with their online metadata " + offlineCursor.getCount());
            offlineCursor.moveToFirst();
            while (!offlineCursor.isAfterLast()) {
                Cursor onlineCursor = getOnlineCursor(context, offlineCursor);
                if (shouldRedownloadPurchasedTrack(onlineCursor, offlineCursor)) {
                    String string = onlineCursor.getString(onlineCursor.getColumnIndex("asin"));
                    Uri uriForAsin = new CirrusSourceLibraryItemFactory(context).getUriForAsin(string);
                    Log.debug(TAG, "Redownloading purchased track: " + string);
                    MusicDownloader.getInstance(context).redownload(uriForAsin.toString(), uriForAsin, null, false, false);
                } else {
                    database.insertWithOnConflict("Track", "_id", getUpdatedContentValues(onlineCursor, offlineCursor), 5);
                }
                offlineCursor.moveToNext();
            }
            DbUtil.closeCursor(offlineCursor);
            return true;
        } catch (Throwable th) {
            DbUtil.closeCursor(offlineCursor);
            throw th;
        }
    }

    private static boolean shouldRedownloadPurchasedTrack(final Cursor onlineCursor, final Cursor offlineCursor) {
        if (onlineCursor == null || !onlineCursor.moveToFirst()) {
            return false;
        }
        int i = onlineCursor.getInt(onlineCursor.getColumnIndex("ownership_status"));
        return ContentOwnershipStatus.fromValue(i).isOwned() && i != offlineCursor.getInt(offlineCursor.getColumnIndex("ownership_status"));
    }

    public static void updateDownloadedContentMetadata(final Context context, final SQLiteDatabase db, final Set<String> updatedAsins) {
        if (CollectionUtils.isEmpty(updatedAsins)) {
            return;
        }
        DbUtil.WhereClause whereClause = new DbUtil.WhereClause("asin", new ArrayList(updatedAsins));
        updateDownloadedContentMetadata(context, db, context.getContentResolver().query(MediaProvider.Tracks.getFilterContentUri("cirrus-local", ""), null, whereClause.getClause(), whereClause.getArgs(), null));
    }

    private static Cursor getOnlineCursor(final Context context, final Cursor offlineCursor) {
        String str;
        long j = offlineCursor.getLong(offlineCursor.getColumnIndex("match_hash"));
        String string = offlineCursor.getString(offlineCursor.getColumnIndex("asin"));
        if (TextUtils.isEmpty(string)) {
            str = TrackListAdapter.getDefaultSelection() + " AND match_hash=" + j;
        } else {
            str = TrackListAdapter.getDefaultSelection() + " AND (match_hash=" + j + " OR asin='" + string + "')";
        }
        return context.getContentResolver().query(MediaProvider.Tracks.getFilterContentUri("cirrus", ""), null, str, TrackListAdapter.getDefaultSelectionArgs(), null);
    }

    private static ContentValues getUpdatedContentValues(final Cursor onlineCursor, final Cursor offlineCursor) {
        ContentValues contentValues = new ContentValues();
        DatabaseUtils.cursorRowToContentValues(offlineCursor, contentValues);
        if (onlineCursor == null) {
            Log.error(TAG, "Online cursor is null");
            return contentValues;
        }
        try {
            onlineCursor.moveToFirst();
            if (!onlineCursor.isAfterLast()) {
                contentValues.put("is_explicit", Integer.valueOf(onlineCursor.getInt(onlineCursor.getColumnIndex("is_explicit"))));
                contentValues.put("prime_status", Integer.valueOf(onlineCursor.getInt(onlineCursor.getColumnIndex("prime_status"))));
                contentValues.put("catalog_status_tiers", Integer.valueOf(onlineCursor.getInt(onlineCursor.getColumnIndex("catalog_status_tiers"))));
            }
            return contentValues;
        } finally {
            DbUtil.closeCursor(onlineCursor);
        }
    }

    public static ContentCatalogStatus mapCatalogStatusForLibrarySync(String primeStatus, String hawkfireStatus, boolean isSonicRush) {
        if (UpsellType.PRIME.equals(primeStatus)) {
            return ContentCatalogStatus.PRIME;
        }
        if (!"NOT_PRIME".equals(primeStatus)) {
            return ContentCatalogStatus.NON_CATALOG;
        }
        if (isSonicRush) {
            return ContentCatalogStatus.SONIC_RUSH;
        }
        if ("true".equals(hawkfireStatus)) {
            return ContentCatalogStatus.HAWKFIRE;
        }
        return ContentCatalogStatus.PREVIOUSLY_CATALOG;
    }

    public static ContentCatalogStatus mapCatalogStatusForLibrarySync(boolean isPrime, boolean isUnlimited, boolean isSonicRush) {
        return mapCatalogStatusForLibrarySync(isPrime ? UpsellType.PRIME : "NOT_PRIME", isUnlimited ? "true" : "", isSonicRush);
    }

    public static ContentOwnershipStatus mapOwnershipStatusForLibrarySync(String status, String primeStatus) {
        if (UpsellType.PRIME.equals(primeStatus)) {
            if (TrackStatus.RECYCLED.toString().equals(status)) {
                return ContentOwnershipStatus.NOT_IN_LIBRARY;
            }
            return ContentOwnershipStatus.ADDED;
        }
        if ("NOT_PRIME".equals(primeStatus)) {
            return ContentOwnershipStatus.ADDED;
        }
        return ContentOwnershipStatus.OWNED;
    }

    static String getExistingLocalTrackLuid(final SQLiteDatabase db, final String localUri) {
        return TrackUtil.getTrackLuidByLocalFileLocation(db, localUri, 1);
    }

    public static String getExistingLocalTrackLuidForAsin(SQLiteDatabase sQLiteDatabase, String str, String[] strArr) {
        Cursor cursor = null;
        String string = null;
        try {
            Cursor cursorQuery = sQLiteDatabase.query("Track", new String[]{"luid"}, str, strArr, null, null, null);
            while (cursorQuery.moveToNext()) {
                try {
                    string = cursorQuery.getString(cursorQuery.getColumnIndex("luid"));
                    if (PrimePlaylistUtil.isAsin(string)) {
                        DbUtil.closeCursor(cursorQuery);
                        return string;
                    }
                } catch (Throwable th) {
                    th = th;
                    cursor = cursorQuery;
                    DbUtil.closeCursor(cursor);
                    throw th;
                }
            }
            DbUtil.closeCursor(cursorQuery);
            return string;
        } catch (Throwable th2) {
            th = th2;
        }
    }

    public static String getExistingLocalTrackLuidForAsin(final SQLiteDatabase db, final String asin) {
        return getExistingLocalTrackLuidForAsin(db, "source=? AND asin=? AND ownership_status< ?", new String[]{String.valueOf(1), asin, String.valueOf(300)});
    }

    public static CatalogStatusTiers convertToCatalogStatusTiers(String primeStatus, String hawkfireStatus, JSONObject eligibility) {
        boolean z;
        boolean zOptBoolean;
        if (eligibility != null) {
            boolean zOptBoolean2 = eligibility.optBoolean("isSonicRush", false);
            zOptBoolean = eligibility.optBoolean("isSonicRushOnDemandPlayable", false);
            z = zOptBoolean2;
        } else {
            z = false;
            zOptBoolean = false;
        }
        return new CatalogStatusTiers(UpsellType.PRIME.equals(primeStatus), "true".equals(hawkfireStatus), false, false, z, zOptBoolean);
    }
}
