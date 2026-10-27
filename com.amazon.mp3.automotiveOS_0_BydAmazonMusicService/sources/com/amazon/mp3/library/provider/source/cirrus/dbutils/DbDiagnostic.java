package com.amazon.mp3.library.provider.source.cirrus.dbutils;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.amazon.mp3.util.DbUtil;
import com.amazon.mp3.util.Log;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/* JADX INFO: loaded from: classes3.dex */
public class DbDiagnostic {
    private static final String TAG = "DbDiagnostic";
    private final SQLiteDatabase db;

    public DbDiagnostic(final SQLiteDatabase db) {
        this.db = db;
    }

    public boolean performDiagnosticAndFixFoundIssues() {
        Log.debug(TAG, "Performing DB diagnostic");
        try {
            try {
                this.db.beginTransaction();
                LocalTrackUriForLibraryTracks localTrackUriForLibraryTracks = new LocalTrackUriForLibraryTracks(this.db);
                if (localTrackUriForLibraryTracks.containsNullLuids()) {
                    fixBrokenNullLuidRecordsInLocalTrackUri();
                }
                if (localTrackUriForLibraryTracks.containsUnmatchedTempPrimeLuids()) {
                    fixUnmatchedPrimeLuidsInLocalTrackUri(localTrackUriForLibraryTracks);
                }
                if (new PlaylistTrackUtil(this.db).doesPlaylistTrackTableContainUnmatchedLuids()) {
                    fixPlaylistTrackTableUnmatchedLuids();
                }
                fixDownloadedTracksWithoutLocalUri();
                this.db.setTransactionSuccessful();
                this.db.endTransaction();
                return true;
            } catch (Exception e) {
                Log.error(TAG, "DB diagnostic failed", e);
                this.db.endTransaction();
                return false;
            }
        } catch (Throwable th) {
            this.db.endTransaction();
            throw th;
        }
    }

    public List<ContentValues> getDataToFixBrokenNullLuidRecordsInLocalTrackUri() {
        ArrayList arrayList = new ArrayList();
        Cursor cursorQuery = this.db.query("Track AS t INNER JOIN LocalTrackUri AS l ON t.asin = l.track_asin", new String[]{"luid", "asin"}, "l.track_luid IS NULL  AND t.download_state =?  AND t.luid IS NOT NULL  AND t.luid NOT LIKE 'local-%' AND t.luid NOT LIKE 'prime-%'", new String[]{Integer.toString(0)}, null, null, null);
        try {
            if (cursorQuery.moveToFirst()) {
                int columnIndex = cursorQuery.getColumnIndex("asin");
                int columnIndex2 = cursorQuery.getColumnIndex("luid");
                while (!cursorQuery.isAfterLast()) {
                    while (!cursorQuery.isAfterLast()) {
                        String string = cursorQuery.getString(columnIndex);
                        String string2 = cursorQuery.getString(columnIndex2);
                        Log.debug(TAG, "Found a matching Null luid record: " + string + ", luid: " + string2);
                        ContentValues contentValues = new ContentValues();
                        contentValues.put("asin", string);
                        contentValues.put("luid", string2);
                        arrayList.add(contentValues);
                        cursorQuery.moveToNext();
                    }
                }
                cursorQuery.close();
                return arrayList;
            }
            cursorQuery.close();
            return arrayList;
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

    private void fixBrokenNullLuidRecordsInLocalTrackUri() {
        new LocalTrackUriForLibraryTracks(this.db).updateNullOrTempPrimeLuids(getDataToFixBrokenNullLuidRecordsInLocalTrackUri());
    }

    private void fixDownloadedTracksWithoutLocalUri() {
        Set<String> downloadedPrimePlaylistTracksAsinsWithoutLocalUri = PrimePlaylistTracksUtil.getDownloadedPrimePlaylistTracksAsinsWithoutLocalUri(this.db);
        for (String str : downloadedPrimePlaylistTracksAsinsWithoutLocalUri) {
            Log.debug(TAG, "Fixing downloaded Prime Playlists track without local_uri: " + downloadedPrimePlaylistTracksAsinsWithoutLocalUri);
            PrimePlaylistTracksUtil.markPrimePlaylistTracksAsCloud(this.db, str);
        }
    }

    public int fixUnmatchedPrimeLuidsInLocalTrackUri(final LocalTrackUriForLibraryTracks localTrackUriForLibraryTracks) throws Throwable {
        Cursor unmatchedTempPrimeLuids;
        LocalTrackUtil localTrackUtil = new LocalTrackUtil(this.db);
        int i = 0;
        try {
            unmatchedTempPrimeLuids = localTrackUriForLibraryTracks.getUnmatchedTempPrimeLuids();
        } catch (Exception e) {
            e = e;
            Log.error(TAG, e.getMessage(), e);
            return i;
        }
        try {
            int columnIndex = unmatchedTempPrimeLuids.getColumnIndex("track_asin");
            int columnIndex2 = unmatchedTempPrimeLuids.getColumnIndex("track_luid");
            int iUpdate = 0;
            while (unmatchedTempPrimeLuids.moveToNext()) {
                try {
                    String string = unmatchedTempPrimeLuids.getString(columnIndex);
                    String string2 = unmatchedTempPrimeLuids.getString(columnIndex2);
                    String correspondingLuidFromTrack = localTrackUtil.getCorrespondingLuidFromTrack(string2, string);
                    if (correspondingLuidFromTrack != null && !correspondingLuidFromTrack.equals(string2)) {
                        ContentValues contentValues = new ContentValues();
                        contentValues.put("track_luid", correspondingLuidFromTrack);
                        String[] strArr = {string, string2};
                        Log.debug(TAG, "Unmatched temp prime luid found, updating old luid " + string2 + " to " + correspondingLuidFromTrack);
                        iUpdate += this.db.update("LocalTrackUri", contentValues, "track_asin=? AND track_luid=? AND local_uri NOT LIKE '%PrimePlaylist%'", strArr);
                    }
                    Log.debug(TAG, "Updated " + iUpdate + " unmatched temp prime luids");
                } catch (Throwable th) {
                    th = th;
                    i = iUpdate;
                    if (unmatchedTempPrimeLuids != null) {
                        try {
                            unmatchedTempPrimeLuids.close();
                        } catch (Throwable th2) {
                            th.addSuppressed(th2);
                        }
                    }
                    throw th;
                }
            }
            try {
                unmatchedTempPrimeLuids.close();
                return iUpdate;
            } catch (Exception e2) {
                e = e2;
                i = iUpdate;
                Log.error(TAG, e.getMessage(), e);
                return i;
            }
        } catch (Throwable th3) {
            th = th3;
        }
    }

    public int fixPlaylistTrackTableUnmatchedLuids() throws Throwable {
        String[] strArr = {"PlaylistTrack._id", "PlaylistTrack.track_luid"};
        int i = 0;
        Cursor cursor = null;
        try {
            try {
                this.db.beginTransactionNonExclusive();
                Cursor cursorQuery = this.db.query("PlaylistTrack LEFT OUTER JOIN Track ON PlaylistTrack.track_luid = Track.luid", strArr, "Track.luid is NULL AND PlaylistTrack.track_luid LIKE 'prime-%'", null, null, null, null);
                int iUpdate = 0;
                while (cursorQuery != null) {
                    try {
                        if (!cursorQuery.moveToNext()) {
                            break;
                        }
                        int i2 = cursorQuery.getInt(cursorQuery.getColumnIndex("_id"));
                        String string = cursorQuery.getString(cursorQuery.getColumnIndex("track_luid"));
                        String strSubstring = string.substring(6);
                        String correspondingLuidFromTrack = new LocalTrackUtil(this.db).getCorrespondingLuidFromTrack(null, strSubstring);
                        if (correspondingLuidFromTrack == null) {
                            Log.info(TAG, "could not find luid for " + string + "\n will skip over asin: " + strSubstring);
                        } else {
                            String[] strArr2 = {Integer.toString(i2)};
                            ContentValues contentValues = new ContentValues();
                            contentValues.put("track_luid", correspondingLuidFromTrack);
                            iUpdate += this.db.update("PlaylistTrack", contentValues, "PlaylistTrack._id = ? ", strArr2);
                        }
                    } catch (Exception e) {
                        e = e;
                        cursor = cursorQuery;
                        i = iUpdate;
                        Log.error(TAG, e.getMessage(), e);
                        DbUtil.closeCursor(cursor);
                        this.db.endTransaction();
                        return i;
                    } catch (Throwable th) {
                        th = th;
                        cursor = cursorQuery;
                        DbUtil.closeCursor(cursor);
                        this.db.endTransaction();
                        throw th;
                    }
                }
                this.db.setTransactionSuccessful();
                DbUtil.closeCursor(cursorQuery);
                this.db.endTransaction();
                return iUpdate;
            } catch (Throwable th2) {
                th = th2;
            }
        } catch (Exception e2) {
            e = e2;
        }
    }
}
