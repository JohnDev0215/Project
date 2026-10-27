package com.amazon.mp3.library.provider.source.cirrus;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.text.TextUtils;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.download.MusicDownloader;
import com.amazon.mp3.library.item.MusicTrack;
import com.amazon.mp3.library.provider.DefaultUriMatcher;
import com.amazon.mp3.library.provider.MediaProvider;
import com.amazon.mp3.library.service.sync.SyncService;
import com.amazon.mp3.library.util.PlaylistUtil;
import com.amazon.mp3.net.AbstractHttpClient;
import com.amazon.mp3.net.cirrus.CirrusExceptions;
import com.amazon.mp3.net.cirrus.CirrusV3Request;
import com.amazon.mp3.net.service.ServiceException;
import com.amazon.mp3.prime.ContentCatalogStatus;
import com.amazon.mp3.prime.ContentOwnershipStatus;
import com.amazon.mp3.prime.cta.AddRemovePrimeManager;
import com.amazon.mp3.util.DbUtil;
import com.amazon.mp3.util.IdGenerator;
import com.amazon.mp3.util.Log;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes3.dex */
public class TrackDeleteRequestUtil {
    private static final String[] QUERY_PROJECTION = {"luid", "asin", "prime_status", "ownership_status"};
    private static final String TAG = "TrackDeleteRequestUtil";

    public static int deleteTracks(Context context, Uri uri) throws JSONException, AbstractHttpClient.HttpClientException, CirrusExceptions.CirrusException {
        int i;
        int i2;
        int i3;
        String str = TAG;
        Log.info(str, "delete tracks");
        int size = 0;
        Log.verbose(str, "uri=%s", uri.toString());
        Context context2 = context == null ? AmazonApplication.getContext() : context;
        int iMatch = DefaultUriMatcher.match(uri);
        if (iMatch == 1) {
            throw new IllegalArgumentException("Attempting to delete all of your songs! Unsupported request.");
        }
        Cursor cursorQuery = null;
        try {
            try {
                cursorQuery = context2.getContentResolver().query(uri, QUERY_PROJECTION, null, null, null);
                if (cursorQuery != null && cursorQuery.moveToNext()) {
                    AddRemovePrimeManager addRemovePrimeManager = AddRemovePrimeManager.getInstance(context2);
                    ArrayList arrayList = new ArrayList();
                    ArrayList arrayList2 = new ArrayList();
                    ArrayList arrayList3 = new ArrayList();
                    int columnIndexOrThrow = cursorQuery.getColumnIndexOrThrow("luid");
                    int columnIndexOrThrow2 = cursorQuery.getColumnIndexOrThrow("asin");
                    int columnIndexOrThrow3 = cursorQuery.getColumnIndexOrThrow("ownership_status");
                    int columnIndexOrThrow4 = cursorQuery.getColumnIndexOrThrow("prime_status");
                    String str2 = (iMatch == 28 || iMatch == 33) ? "primeplaylist:" : "";
                    while (true) {
                        String string = cursorQuery.getString(columnIndexOrThrow);
                        String string2 = cursorQuery.getString(columnIndexOrThrow2);
                        int i4 = cursorQuery.getInt(columnIndexOrThrow3);
                        int i5 = cursorQuery.getInt(columnIndexOrThrow4);
                        if (TextUtils.isEmpty(string) || IdGenerator.isPrimeLuid(string)) {
                            i = columnIndexOrThrow;
                            i2 = columnIndexOrThrow2;
                            i3 = columnIndexOrThrow3;
                            if (!TextUtils.isEmpty(string)) {
                                arrayList2.add(string);
                            }
                            arrayList3.add(string2);
                        } else {
                            i = columnIndexOrThrow;
                            String str3 = TAG;
                            i2 = columnIndexOrThrow2;
                            StringBuilder sb = new StringBuilder();
                            i3 = columnIndexOrThrow3;
                            sb.append("deleteTracks: ");
                            sb.append(string2);
                            sb.append(" ownership status: ");
                            sb.append(ContentOwnershipStatus.fromValue(i4));
                            sb.append(" catalog status: ");
                            sb.append(ContentCatalogStatus.fromValue(i5));
                            Log.warning(str3, sb.toString());
                            arrayList.add(string);
                        }
                        MusicDownloader musicDownloader = MusicDownloader.getInstance(context2);
                        if (!TextUtils.isEmpty(string2)) {
                            string = str2 + string2;
                        }
                        musicDownloader.cancel(string);
                        if (arrayList.size() == 500) {
                            size += arrayList.size();
                            deleteTracks(context2, arrayList);
                        }
                        if (!cursorQuery.moveToNext()) {
                            break;
                        }
                        columnIndexOrThrow = i;
                        columnIndexOrThrow2 = i2;
                        columnIndexOrThrow3 = i3;
                    }
                    if (arrayList.size() > 0) {
                        size += arrayList.size();
                        deleteTracks(context2, arrayList);
                    }
                    updateTracksInDB(context2, arrayList2);
                    Iterator it = arrayList3.iterator();
                    while (it.hasNext()) {
                        addRemovePrimeManager.queueTrackToRemove((String) it.next());
                    }
                    if (arrayList2.size() > 0) {
                        removeAdded(context2, arrayList2);
                    }
                    updatePlaylistsAndDeletes(context2);
                } else {
                    Log.warning(str, "failed to delete tracks - cursor possibly empty");
                }
            } catch (AbstractHttpClient.CanceledException | AbstractHttpClient.FailedException | AbstractHttpClient.IncompleteResultException | AbstractHttpClient.UnexpectedHttpStatusException | ServiceException | JSONException e) {
                Log.error(TAG, "Exception when attempting to delete tracks: ", e);
            }
            DbUtil.closeCursor(cursorQuery);
            return size;
        } catch (Throwable th) {
            DbUtil.closeCursor(null);
            throw th;
        }
    }

    private static void deleteTracks(Context context, List<String> luidsToDelete) throws ServiceException, JSONException, AbstractHttpClient.FailedException, AbstractHttpClient.CanceledException, AbstractHttpClient.IncompleteResultException, AbstractHttpClient.UnexpectedHttpStatusException {
        deleteFromCirrus(luidsToDelete);
        updateTracksInDB(context, luidsToDelete);
        luidsToDelete.clear();
    }

    private static void updateTracksInDB(Context context, List<String> trackLuidsToDelete) {
        SQLiteDatabase writableDatabase = CirrusDatabase.getWritableDatabase(AmazonApplication.getContext());
        writableDatabase.beginTransaction();
        try {
            copyAsCatalogTrackAndReplaceLibraryWithCatalogInPlaylist(trackLuidsToDelete, writableDatabase);
            writableDatabase.setTransactionSuccessful();
            writableDatabase.endTransaction();
            context.getContentResolver().notifyChange(CirrusMediaSource.NOTIFICATION_URI, null);
            CirrusDatabaseUtil.updateAllCachedTrackCounts(context);
        } catch (Throwable th) {
            writableDatabase.endTransaction();
            throw th;
        }
    }

    private static void removeAdded(Context context, List<String> luids) {
        StringBuilder sb = new StringBuilder();
        DbUtil.WhereClause whereClause = new DbUtil.WhereClause("luid", luids);
        sb.append(whereClause.getClause());
        sb.append(" AND ");
        sb.append("ownership_status");
        sb.append("=");
        sb.append(ContentOwnershipStatus.ADDED.getValue());
        SQLiteDatabase writableDatabase = CirrusDatabase.getWritableDatabase(context);
        writableDatabase.beginTransaction();
        try {
            writableDatabase.delete("Track", sb.toString(), whereClause.getArgs());
            writableDatabase.setTransactionSuccessful();
            writableDatabase.endTransaction();
            context.getContentResolver().notifyChange(CirrusMediaSource.NOTIFICATION_URI, null);
        } catch (Throwable th) {
            writableDatabase.endTransaction();
            throw th;
        }
    }

    private static void copyAsCatalogTrackAndReplaceLibraryWithCatalogInPlaylist(List<String> trackLuidsToDelete, SQLiteDatabase db) {
        int i;
        int i2;
        DbUtil.WhereClause whereClause = new DbUtil.WhereClause("luid", new ArrayList(trackLuidsToDelete));
        String strApplyBinaryOperator = DbUtil.applyBinaryOperator(DbUtil.applyBinaryOperator(whereClause.getClause(), "AND", DbUtil.applyBinaryOperator("prime_status<175 AND ownership_status<200", "OR", "prime_status>400")), "AND", "source=0");
        Cursor cursorQuery = db.query("Track", null, strApplyBinaryOperator, whereClause.getArgs(), null, null, null);
        ContentValues contentValues = new ContentValues();
        String str = "playlist_track_status";
        contentValues.put("playlist_track_status", Integer.valueOf(MusicTrack.PlaylistTrackStatus.CATALOG_TRACK_NOT_IN_LIBRARY.getValue()));
        String str2 = "ownership_status";
        contentValues.put("ownership_status", Integer.valueOf(ContentOwnershipStatus.NOT_IN_LIBRARY.getValue()));
        try {
            db.beginTransaction();
            ArrayList arrayList = new ArrayList();
            int columnIndex = cursorQuery.getColumnIndex("luid");
            int columnIndex2 = cursorQuery.getColumnIndex("asin");
            int columnIndex3 = cursorQuery.getColumnIndex("purchased");
            while (cursorQuery.moveToNext()) {
                String string = cursorQuery.getString(columnIndex);
                String string2 = cursorQuery.getString(columnIndex2);
                if (cursorQuery.isNull(columnIndex3)) {
                    i = columnIndex;
                    i2 = 0;
                } else {
                    i2 = cursorQuery.getInt(columnIndex3);
                    i = columnIndex;
                }
                if (i2 == 1) {
                    arrayList.add(string);
                    columnIndex = i;
                } else {
                    ContentValues contentValues2 = new ContentValues();
                    ContentValues contentValues3 = new ContentValues();
                    DatabaseUtils.cursorRowToContentValues(cursorQuery, contentValues2);
                    int i3 = columnIndex2;
                    contentValues2.remove("_id");
                    contentValues2.put("luid", string2);
                    contentValues3.put("track_luid", string2);
                    contentValues2.put(str, Integer.valueOf(MusicTrack.PlaylistTrackStatus.CATALOG_TRACK_NOT_IN_LIBRARY.getValue()));
                    contentValues2.put(str2, Integer.valueOf(ContentOwnershipStatus.NOT_IN_LIBRARY.getValue()));
                    String str3 = str;
                    String str4 = str2;
                    int i4 = columnIndex3;
                    if (db.update("PlaylistTrack", contentValues3, "track_luid =?", new String[]{string}) > 0 && db.update("Track", contentValues, "luid =?", new String[]{string2}) == 0) {
                        db.insert("Track", null, contentValues2);
                    }
                    columnIndex = i;
                    columnIndex3 = i4;
                    columnIndex2 = i3;
                    str = str3;
                    str2 = str4;
                }
            }
            if (!arrayList.isEmpty()) {
                deleteFromPlaylistTracksTable(arrayList, db);
                deleteFromTracksTable(arrayList, db);
            }
            db.delete("Track", strApplyBinaryOperator, whereClause.getArgs());
            db.setTransactionSuccessful();
        } finally {
            cursorQuery.close();
            db.endTransaction();
        }
    }

    private static void deleteFromPlaylistTracksTable(List<String> trackLuidsToDelete, SQLiteDatabase db) {
        DbUtil.WhereClause whereClause = new DbUtil.WhereClause("track_luid", new ArrayList(trackLuidsToDelete));
        db.delete("PlaylistTrack", whereClause.getClause(), whereClause.getArgs());
    }

    private static void deleteFromTracksTable(List<String> trackLuidsToDelete, SQLiteDatabase db) {
        DbUtil.WhereClause whereClause = new DbUtil.WhereClause("luid", new ArrayList(trackLuidsToDelete));
        db.delete("Track", DbUtil.applyBinaryOperator(DbUtil.applyBinaryOperator(whereClause.getClause(), "AND", DbUtil.applyBinaryOperator("prime_status<175 AND ownership_status<200", "OR", "prime_status>400")), "AND", "source=0"), whereClause.getArgs());
    }

    private static void deleteFromCirrus(List<String> trackIdsToDelete) throws ServiceException, JSONException, AbstractHttpClient.FailedException, AbstractHttpClient.CanceledException, AbstractHttpClient.IncompleteResultException, AbstractHttpClient.UnexpectedHttpStatusException {
        JSONObject jSONObject = new JSONObject();
        JSONArray jSONArray = new JSONArray();
        Iterator<String> it = trackIdsToDelete.iterator();
        while (it.hasNext()) {
            jSONArray.put(it.next());
        }
        jSONObject.put("trackIdList", jSONArray);
        CirrusV3Request.DeleteTracks.execute(jSONObject);
        Log.info(TAG, "deleteTracks called");
    }

    private static void updatePlaylistsAndDeletes(Context context) {
        PlaylistUtil.refreshPlaylistArt(MediaProvider.SmartPlaylists.getContentUri("cirrus", 2L));
        PlaylistUtil.refreshPlaylistArt(MediaProvider.SmartPlaylists.getContentUri("cirrus", 1L));
        SyncService.startSync(context, 19);
    }
}
