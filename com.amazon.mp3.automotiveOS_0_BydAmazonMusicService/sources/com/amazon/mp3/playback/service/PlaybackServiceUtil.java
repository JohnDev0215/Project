package com.amazon.mp3.playback.service;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.net.wifi.WifiManager;
import com.amazon.mp3.activity.settings.SettingsUtil;
import com.amazon.mp3.library.provider.MediaProvider;
import com.amazon.mp3.library.provider.source.cirrus.CirrusDatabase;
import com.amazon.mp3.net.AbstractHttpClient;
import com.amazon.mp3.net.dmls.ContentId;
import com.amazon.mp3.net.dmls.ContentResponse;
import com.amazon.mp3.net.dmls.DMLSApiProvider;
import com.amazon.mp3.net.dmls.DMLSExceptions;
import com.amazon.mp3.net.dmls.IdentifierType;
import com.amazon.mp3.playback.activity.StreamingNetworkPreferenceActivity;
import com.amazon.mp3.playback.service.player.factory.StreamingResponseCache;
import com.amazon.mp3.util.ConnectivityUtil;
import com.amazon.mp3.util.DbUtil;
import com.amazon.mp3.util.Log;
import com.amazon.mp3.util.StringUtil;
import com.amazonaws.util.StringUtils;
import java.util.HashSet;
import java.util.List;
import org.json.JSONException;

/* JADX INFO: loaded from: classes3.dex */
public class PlaybackServiceUtil {
    private static final String TAG = "PlaybackServiceUtil";

    public static String getDeluxeContentUriFromLuid(final String luid) throws JSONException, AbstractHttpClient.HttpClientException, DMLSExceptions.DMLSException {
        synchronized (luid.intern()) {
            StreamingResponseCache cache = StreamingResponseCache.getCache();
            ContentId contentIdFromLuid = getContentIdFromLuid(luid);
            ContentResponse response = cache.getResponse(contentIdFromLuid);
            if (response == null) {
                response = DMLSApiProvider.get().retrieveGetStreamUrlResponse(contentIdFromLuid);
                cache.putResponse(contentIdFromLuid, response);
            } else {
                DMLSExceptions.validateContentResponse(response);
            }
            List<String> urlList = response.getUrlList();
            if (urlList != null && urlList.size() != 0) {
                String str = urlList.get(0);
                if (!StringUtils.isBlank(str)) {
                    return str;
                }
            }
            Log.error(TAG, "No streaming uri returned from DMLS for deluxe content luid " + luid);
            return null;
        }
    }

    public static ContentId getContentIdFromLuid(final String luid) throws DMLSExceptions.InvalidContentIdException {
        return new ContentId(IdentifierType.COID, luid);
    }

    public static Uri getLocalUri(Context context, Uri contentUri) {
        Uri uriForLocalPrimePlaylistTrack = MediaProvider.Tracks.isPrimeAdditionalTrack(contentUri) ? getUriForLocalPrimePlaylistTrack(context, MediaProvider.Tracks.getPrimeAdditionalTrackAsin(contentUri)) : null;
        return uriForLocalPrimePlaylistTrack == null ? getUriForLocalTrack(context, CirrusDatabase.getReadOnlyDatabase(context), contentUri) : uriForLocalPrimePlaylistTrack;
    }

    public static String getLuidFromContentUri(final Uri contentUri) {
        return contentUri.getLastPathSegment();
    }

    public static Uri getContentUriForTrackPath(Context context, String filepath) throws Throwable {
        Cursor cursorQuery;
        Cursor cursor = null;
        try {
            cursorQuery = context.getContentResolver().query(MediaProvider.Tracks.getContentUri("cirrus-local"), new String[]{"luid", "local_uri"}, "local_uri=?", new String[]{filepath}, null);
            if (cursorQuery != null) {
                try {
                    try {
                        if (cursorQuery.moveToFirst()) {
                            Uri contentUri = MediaProvider.Tracks.getContentUri("cirrus-local", cursorQuery.getString(cursorQuery.getColumnIndexOrThrow("luid")));
                            DbUtil.closeCursor(cursorQuery);
                            return contentUri;
                        }
                    } catch (Throwable th) {
                        th = th;
                        cursor = cursorQuery;
                        DbUtil.closeCursor(cursor);
                        throw th;
                    }
                } catch (SQLiteException e) {
                    e = e;
                    Log.warning(TAG, "SQLite exception caught: %s", e.getMessage());
                }
            }
        } catch (SQLiteException e2) {
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

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v0 */
    /* JADX WARN: Type inference failed for: r1v1, types: [android.database.Cursor] */
    /* JADX WARN: Type inference failed for: r1v2 */
    public static Uri getUriForLocalPrimePlaylistTrack(Context context, final String asin) throws Throwable {
        Cursor cursorQuery;
        String string;
        ?? r1 = 0;
        if (StringUtil.isNullOrWhiteSpaces(asin)) {
            return null;
        }
        String[] strArr = {"LocalTrackUri.local_uri"};
        String[] strArr2 = {asin};
        SQLiteQueryBuilder sQLiteQueryBuilder = new SQLiteQueryBuilder();
        sQLiteQueryBuilder.setTables("PrimePlaylistTracks LEFT JOIN LocalTrackUri ON PrimePlaylistTracks.asin = LocalTrackUri.track_asin");
        try {
            try {
                cursorQuery = sQLiteQueryBuilder.query(CirrusDatabase.getReadOnlyDatabase(context), strArr, "LocalTrackUri.track_asin=?", strArr2, null, null, null);
                if (cursorQuery != null) {
                    try {
                        if (cursorQuery.moveToFirst() && (string = cursorQuery.getString(cursorQuery.getColumnIndexOrThrow("local_uri"))) != null && string.length() > 0) {
                            Uri uri = Uri.parse(string);
                            DbUtil.closeCursor(cursorQuery);
                            return uri;
                        }
                    } catch (SQLiteException e) {
                        e = e;
                        Log.warning(TAG, "SQLite exception caught: %s", e.getMessage());
                    }
                }
            } catch (SQLiteException e2) {
                e = e2;
                cursorQuery = null;
            } catch (Throwable th) {
                th = th;
                DbUtil.closeCursor(r1);
                throw th;
            }
            DbUtil.closeCursor(cursorQuery);
            return null;
        } catch (Throwable th2) {
            th = th2;
            r1 = context;
            DbUtil.closeCursor(r1);
            throw th;
        }
    }

    public static boolean isStreamingRestricted(Context context, final StreamingNetworkPreferenceActivity.Type type) {
        return (ConnectivityUtil.isWifiOnlyDevice() || isStreamingAllowed(context, type)) ? false : true;
    }

    private static boolean isStreamingAllowed(final Context context, final StreamingNetworkPreferenceActivity.Type type) {
        String streamingNetworkPreference = SettingsUtil.getStreamingNetworkPreference(context, type);
        if ("ALL_NETWORKS".equals(streamingNetworkPreference) || "WIFI_OR_3G4G".equals(streamingNetworkPreference)) {
            return true;
        }
        if ("WIFI_ONLY".equals(streamingNetworkPreference)) {
            WifiManager.WifiLock wifiLockCreateAndAcquireWifiLock = null;
            try {
                wifiLockCreateAndAcquireWifiLock = ConnectivityUtil.createAndAcquireWifiLock(context);
                return ConnectivityUtil.isWifiConnected();
            } finally {
                ConnectivityUtil.releaseWifiLock(wifiLockCreateAndAcquireWifiLock);
            }
        }
        throw new IllegalStateException("Invalid streaming network value");
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x0031 A[Catch: SQLiteException -> 0x0060, all -> 0x0099, TRY_ENTER, TryCatch #2 {SQLiteException -> 0x0060, blocks: (B:5:0x0019, B:7:0x001f, B:12:0x0031, B:14:0x004f, B:16:0x0055), top: B:42:0x0019 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static android.net.Uri getUriForLocalTrack(android.content.Context r13, android.database.sqlite.SQLiteDatabase r14, android.net.Uri r15) throws java.lang.Throwable {
        /*
            java.lang.String r0 = "luid"
            java.lang.String r1 = "local_uri"
            r2 = 0
            r3 = 1
            r4 = 0
            java.lang.String[] r7 = new java.lang.String[]{r0, r1}     // Catch: java.lang.Throwable -> L63 android.database.sqlite.SQLiteException -> L65
            android.content.ContentResolver r5 = r13.getContentResolver()     // Catch: java.lang.Throwable -> L63 android.database.sqlite.SQLiteException -> L65
            r8 = 0
            r9 = 0
            r10 = 0
            r6 = r15
            android.database.Cursor r13 = r5.query(r6, r7, r8, r9, r10)     // Catch: java.lang.Throwable -> L63 android.database.sqlite.SQLiteException -> L65
            if (r13 == 0) goto L31
            boolean r5 = r13.moveToFirst()     // Catch: android.database.sqlite.SQLiteException -> L60 java.lang.Throwable -> L99
            if (r5 == 0) goto L31
            int r15 = r13.getColumnIndexOrThrow(r1)     // Catch: android.database.sqlite.SQLiteException -> L60 java.lang.Throwable -> L99
            java.lang.String r15 = r13.getString(r15)     // Catch: android.database.sqlite.SQLiteException -> L60 java.lang.Throwable -> L99
            int r0 = r13.getColumnIndexOrThrow(r0)     // Catch: android.database.sqlite.SQLiteException -> L2f java.lang.Throwable -> L99
            r13.getString(r0)     // Catch: android.database.sqlite.SQLiteException -> L2f java.lang.Throwable -> L99
            goto L77
        L2f:
            r0 = move-exception
            goto L68
        L31:
            com.amazon.mp3.util.DbUtil.closeCursor(r13)     // Catch: android.database.sqlite.SQLiteException -> L60 java.lang.Throwable -> L99
            java.lang.String r15 = r15.getLastPathSegment()     // Catch: android.database.sqlite.SQLiteException -> L60 java.lang.Throwable -> L99
            java.lang.String[] r7 = new java.lang.String[]{r1}     // Catch: android.database.sqlite.SQLiteException -> L60 java.lang.Throwable -> L99
            java.lang.String[] r9 = new java.lang.String[r3]     // Catch: android.database.sqlite.SQLiteException -> L60 java.lang.Throwable -> L99
            r9[r2] = r15     // Catch: android.database.sqlite.SQLiteException -> L60 java.lang.Throwable -> L99
            java.lang.String r6 = "LocalTrackUri"
            java.lang.String r8 = "track_luid=?"
            r10 = 0
            r11 = 0
            r12 = 0
            r5 = r14
            android.database.Cursor r13 = r5.query(r6, r7, r8, r9, r10, r11, r12)     // Catch: android.database.sqlite.SQLiteException -> L60 java.lang.Throwable -> L99
            if (r13 == 0) goto L5e
            boolean r15 = r13.moveToFirst()     // Catch: android.database.sqlite.SQLiteException -> L60 java.lang.Throwable -> L99
            if (r15 == 0) goto L5e
            int r15 = r13.getColumnIndexOrThrow(r1)     // Catch: android.database.sqlite.SQLiteException -> L60 java.lang.Throwable -> L99
            java.lang.String r15 = r13.getString(r15)     // Catch: android.database.sqlite.SQLiteException -> L60 java.lang.Throwable -> L99
            goto L77
        L5e:
            r15 = r4
            goto L77
        L60:
            r0 = move-exception
            r15 = r4
            goto L68
        L63:
            r14 = move-exception
            goto L9b
        L65:
            r0 = move-exception
            r13 = r4
            r15 = r13
        L68:
            java.lang.String r1 = com.amazon.mp3.playback.service.PlaybackServiceUtil.TAG     // Catch: java.lang.Throwable -> L99
            java.lang.String r5 = "SQLite exception caught: %s"
            java.lang.Object[] r3 = new java.lang.Object[r3]     // Catch: java.lang.Throwable -> L99
            java.lang.String r0 = r0.getMessage()     // Catch: java.lang.Throwable -> L99
            r3[r2] = r0     // Catch: java.lang.Throwable -> L99
            com.amazon.mp3.util.Log.warning(r1, r5, r3)     // Catch: java.lang.Throwable -> L99
        L77:
            com.amazon.mp3.util.DbUtil.closeCursor(r13)
            if (r15 == 0) goto L98
            int r13 = r15.length()
            if (r13 <= 0) goto L98
            java.io.File r13 = new java.io.File
            r13.<init>(r15)
            boolean r13 = r13.exists()
            if (r13 == 0) goto L98
            boolean r13 = localAndRemoteContentOwnershipMatch(r14, r15)
            if (r13 == 0) goto L98
            android.net.Uri r13 = android.net.Uri.parse(r15)
            return r13
        L98:
            return r4
        L99:
            r14 = move-exception
            r4 = r13
        L9b:
            com.amazon.mp3.util.DbUtil.closeCursor(r4)
            throw r14
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.mp3.playback.service.PlaybackServiceUtil.getUriForLocalTrack(android.content.Context, android.database.sqlite.SQLiteDatabase, android.net.Uri):android.net.Uri");
    }

    private static boolean localAndRemoteContentOwnershipMatch(final SQLiteDatabase db, final String localUri) {
        SQLiteQueryBuilder sQLiteQueryBuilder = new SQLiteQueryBuilder();
        sQLiteQueryBuilder.setTables("Track LEFT OUTER JOIN LocalTrackUri ON Track.luid=LocalTrackUri.track_luid");
        boolean z = false;
        Cursor cursorQuery = sQLiteQueryBuilder.query(db, new String[]{"LocalTrackUri.local_uri", "Track.ownership_status"}, "LocalTrackUri.local_uri=?", new String[]{localUri}, null, null, null);
        if (cursorQuery == null) {
            return false;
        }
        if (cursorQuery.moveToFirst()) {
            HashSet hashSet = new HashSet();
            do {
                hashSet.add(Integer.valueOf(cursorQuery.getInt(cursorQuery.getColumnIndex("ownership_status"))));
            } while (cursorQuery.moveToNext());
            z = hashSet.size() == 1;
        }
        cursorQuery.close();
        return z;
    }
}
