package com.amazon.mp3.library.provider.source.cirrus.dbutils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteConstraintException;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;
import com.amazon.mp3.configuration.IntlConfiguration;
import com.amazon.mp3.download.MusicDownloader;
import com.amazon.mp3.library.item.MusicTrack;
import com.amazon.mp3.library.item.factory.CirrusSourceLibraryItemFactory;
import com.amazon.mp3.library.provider.source.cirrus.asinupdate.UpdateTracksAction;
import com.amazon.mp3.library.util.TrackUtil;
import com.amazon.mp3.prime.ContentCatalogStatus;
import com.amazon.mp3.prime.ContentOwnershipStatus;
import com.amazon.mp3.util.ContentEncodingDecoder;
import com.amazon.mp3.util.DbUtil;
import com.amazon.mp3.util.IdGenerator;
import com.amazon.mp3.util.Log;
import com.amazon.mp3.util.StringUtil;
import com.amazon.music.contentEncoding.ContentEncoding;
import com.amazon.music.find.utils.PageUriUtils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.Validate;

/* JADX INFO: loaded from: classes3.dex */
public class TrackDataUtil {
    private static final String TAG = "TrackDataUtil";
    private final SQLiteDatabase mDb;

    public enum Action {
        UPDATE,
        INSERT
    }

    public enum Version {
        MATCH,
        MISMATCH,
        DOES_NOT_EXIST
    }

    public TrackDataUtil(final SQLiteDatabase db) {
        this.mDb = db;
    }

    public int replaceAsin(String newAsin, String oldAsin, String marketPlace) {
        Validate.notNull(newAsin);
        Validate.notNull(oldAsin);
        Validate.notNull(marketPlace);
        ContentValues contentValues = new ContentValues();
        contentValues.put("asin", newAsin);
        return this.mDb.update("Track", contentValues, "asin=? AND luid IS NOT NULL AND marketplace=?", new String[]{oldAsin, marketPlace});
    }

    public int updateByAsin(final String asin, final String luidToReplace, final ContentValues values) {
        String[] strArr;
        Validate.notNull(asin, "The asin should not be null", new Object[0]);
        Validate.notNull(luidToReplace, "The luid should not be null", new Object[0]);
        Validate.notNull(values, "The values should not be null", new Object[0]);
        Validate.isTrue(values.size() > 0, "The values must not be empty", new Object[0]);
        Validate.isTrue(values.containsKey("luid"), "The values should provide a new LUID.", new Object[0]);
        Validate.notNull(values.getAsString("luid"), "The values should provide a non-null LUID.", new Object[0]);
        String str = TAG;
        Log.verbose(str, "New values for track: " + asin + ", luid: " + luidToReplace + ", values: " + values);
        String asString = values.getAsString(IntlConfiguration.MARKETPLACE);
        String str2 = "source=0 AND luid=? AND asin=?";
        if (StringUtil.isNullOrWhiteSpaces(asString)) {
            strArr = new String[]{luidToReplace, asin};
        } else {
            str2 = "source=0 AND luid=? AND asin=? AND (marketplace=? OR marketplace IS NULL)";
            strArr = new String[]{luidToReplace, asin, asString};
        }
        int iUpdate = this.mDb.update("Track", values, str2, strArr);
        if (iUpdate > 0) {
            String asString2 = values.getAsString("luid");
            Log.debug(str, "The new luid for " + asin + " is " + asString2);
            if (asString2 != null) {
                new LocalTrackUtil(this.mDb).updateTempPrimeLuid(asin, asString2);
            }
            if (!anyTracksWithNullLuid(asin)) {
                return iUpdate;
            }
            Log.debug(str, "Deleted " + deleteTracksWithNullLuid(asin) + " tracks with null LUID and asin: " + asin);
            return iUpdate;
        }
        return updateByAsinTracksWithNullLuid(asin, values);
    }

    int updateByAsinTracksWithNullLuid(final String asin, final ContentValues values) {
        String[] strArr;
        Validate.notNull(asin, "The asin should not be null", new Object[0]);
        Validate.notNull(values, "The values should not be null", new Object[0]);
        Validate.isTrue(values.size() > 0, "The values must not be empty", new Object[0]);
        Validate.isTrue(values.containsKey("luid"), "The values should provide a new LUID.", new Object[0]);
        String asString = values.getAsString("luid");
        Validate.notNull(asString, "The values should provide a non-null LUID.", new Object[0]);
        String str = TAG;
        Log.verbose(str, "updateByAsinTracksWithNullLuid: New values for track: " + asin + ", values: " + values);
        if (getTrackCountByLuid(asString) > 0) {
            deleteTracksWithNullLuid(asin);
            logTrackByLuid("Existing track with the same luid: ", asString);
            return updateByLuid(values, asString);
        }
        String asString2 = values.getAsString(IntlConfiguration.MARKETPLACE);
        String str2 = "source=0 AND luid is NULL AND asin=?";
        if (StringUtil.isNullOrWhiteSpaces(asString2)) {
            strArr = new String[]{asin};
        } else {
            str2 = "source=0 AND luid is NULL AND asin=? AND (marketplace=? OR marketplace IS NULL)";
            strArr = new String[]{asin, asString2};
        }
        try {
            int iUpdate = this.mDb.update("Track", values, str2, strArr);
            if (iUpdate > 0) {
                String asString3 = values.getAsString("luid");
                Log.debug(str, "The new luid for " + asin + " is " + asString3);
                if (asString3 != null) {
                    new LocalTrackUtil(this.mDb).updateTempPrimeLuid(asin, asString3);
                }
            }
            return iUpdate;
        } catch (SQLiteConstraintException e) {
            String str3 = String.format("Unable to update a Track with a null luid and asin: %s, error: %s", asin, e.getMessage());
            logTrackByLuid("Track with the duplicate luid: ", asString);
            Log.error(TAG, str3, e);
            return 0;
        }
    }

    boolean anyTracksWithNullLuid(final String asin) {
        Validate.notBlank(asin);
        return DatabaseUtils.queryNumEntries(this.mDb, "Track", "luid is NULL AND asin =?", new String[]{asin}) > 0;
    }

    int deleteTracksWithNullLuid(final String asin) {
        Validate.notBlank(asin);
        return this.mDb.delete("Track", "luid is NULL AND asin =?", new String[]{asin});
    }

    public Version checkTrackVersion(final ContentValues values) {
        String str;
        String[] strArr;
        String str2;
        String asString = values.getAsString("asin");
        String asString2 = values.getAsString("luid");
        if (TextUtils.isEmpty(asString)) {
            strArr = new String[]{asString2};
            str2 = "source=0 AND luid=?";
        } else {
            String asString3 = values.getAsString(IntlConfiguration.MARKETPLACE);
            if (StringUtil.isNullOrWhiteSpaces(asString3)) {
                str = "(source=0 AND (luid IS NULL OR luid =?) AND asin=?)";
                strArr = new String[]{asString2, IdGenerator.generatePrimeLuid(asString), asString};
            } else {
                str = "(source=0 AND (luid IS NULL OR luid =?) AND asin=? AND (marketplace=? OR marketplace IS NULL))";
                strArr = new String[]{asString2, IdGenerator.generatePrimeLuid(asString), asString, asString3};
            }
            str2 = "source=0 AND luid=? OR " + str;
        }
        Cursor cursorQuery = this.mDb.query("Track", new String[]{"date_updated"}, str2, strArr, null, null, null);
        if (cursorQuery != null) {
            try {
                if (cursorQuery.moveToFirst()) {
                    if (values.getAsLong("date_updated").longValue() == cursorQuery.getLong(cursorQuery.getColumnIndexOrThrow("date_updated"))) {
                        Version version = Version.MATCH;
                        cursorQuery.close();
                        return version;
                    }
                    Version version2 = Version.MISMATCH;
                    cursorQuery.close();
                    return version2;
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
        Version version3 = Version.DOES_NOT_EXIST;
        if (cursorQuery != null) {
            cursorQuery.close();
        }
        return version3;
    }

    public int getTrackCountByLuid(String luid) {
        Cursor cursorQuery = this.mDb.query("Track", new String[]{"_id"}, "luid =?", new String[]{luid}, null, null, null, null);
        try {
            if (cursorQuery == null) {
                Log.debug(TAG, "No tracks found for this luid: " + luid);
                if (cursorQuery != null) {
                    cursorQuery.close();
                }
                return 0;
            }
            int count = cursorQuery.getCount();
            cursorQuery.close();
            return count;
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

    public int updateByLuid(ContentValues values, String luid) {
        return this.mDb.update("Track", values, "luid=?", new String[]{luid});
    }

    public int updateTrackContentEncodingByAsins(final Map<String, List<ContentEncoding>> trackAsinToContentEncodings) {
        int iUpdate = 0;
        try {
            try {
                HashMap map = new HashMap();
                for (Map.Entry<String, List<ContentEncoding>> entry : trackAsinToContentEncodings.entrySet()) {
                    String strContentEncodingToString = ContentEncodingDecoder.contentEncodingToString(entry.getValue());
                    if (map.get(strContentEncodingToString) != null) {
                        ((List) map.get(strContentEncodingToString)).add(entry.getKey());
                    } else {
                        ArrayList arrayList = new ArrayList();
                        arrayList.add(entry.getKey());
                        map.put(strContentEncodingToString, arrayList);
                    }
                }
                this.mDb.beginTransaction();
                for (String str : map.keySet()) {
                    ContentValues contentValues = new ContentValues();
                    contentValues.put("content_encoding", str);
                    DbUtil.WhereClause whereClause = new DbUtil.WhereClause("asin", (List) map.get(str));
                    iUpdate += this.mDb.update("Track", contentValues, whereClause.getClause(), whereClause.getArgs());
                }
                this.mDb.setTransactionSuccessful();
            } catch (Exception e) {
                Log.error(TAG, "updateTrackContentEncodingByAsins failed with exception: " + e);
            }
            return iUpdate;
        } finally {
            this.mDb.endTransaction();
        }
    }

    public void logTrackByLuid(String msg, String luid) {
        String[] strArr = {"_id", "luid", "asin", IntlConfiguration.MARKETPLACE, "title", "artist", "album", PageUriUtils.SOURCE_QUERY_PARAM, "album_asin", "artist_asin", "track_num", "disc_num", "download_state", "prime_status", "ownership_status"};
        Cursor cursorQuery = this.mDb.query("Track", strArr, "luid =?", new String[]{luid}, null, null, null, null);
        try {
            if (cursorQuery == null) {
                Log.debug(TAG, "No tracks found for this luid: " + luid);
                if (cursorQuery != null) {
                    cursorQuery.close();
                    return;
                }
                return;
            }
            cursorQuery.moveToFirst();
            while (!cursorQuery.isAfterLast()) {
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < 15; i++) {
                    String str = strArr[i];
                    String string = cursorQuery.getString(cursorQuery.getColumnIndex(str));
                    sb.append(str);
                    sb.append(": ");
                    sb.append(string);
                    sb.append(", ");
                }
                Log.debug(TAG, msg + " Track: " + sb.toString());
                cursorQuery.moveToNext();
            }
            cursorQuery.close();
        } catch (Throwable th) {
            if (cursorQuery == null) {
                throw th;
            }
            try {
                cursorQuery.close();
                throw th;
            } catch (Throwable th2) {
                th.addSuppressed(th2);
                throw th;
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x0037  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public com.amazon.mp3.library.provider.source.cirrus.dbutils.TrackDataUtil.Action updateOrInsertTrack(final android.content.Context r7, final android.content.ContentValues r8, final boolean r9) {
        /*
            r6 = this;
            java.lang.String r0 = "luid"
            java.lang.String r0 = r8.getAsString(r0)
            java.lang.String r1 = "asin"
            java.lang.String r1 = r8.getAsString(r1)
            boolean r2 = com.amazon.mp3.library.service.sync.SyncFilter.shouldFilter(r8)
            r3 = 0
            if (r2 != 0) goto L63
            int[] r2 = com.amazon.mp3.library.provider.source.cirrus.dbutils.TrackDataUtil.AnonymousClass1.$SwitchMap$com$amazon$mp3$library$provider$source$cirrus$dbutils$TrackDataUtil$Version
            com.amazon.mp3.library.provider.source.cirrus.dbutils.TrackDataUtil$Version r4 = r6.checkTrackVersion(r8)
            int r4 = r4.ordinal()
            r2 = r2[r4]
            r4 = 1
            if (r2 == r4) goto L2b
            r5 = 2
            if (r2 == r5) goto L29
            r4 = 3
            if (r2 == r4) goto L35
            goto L34
        L29:
            r9 = r4
            goto L35
        L2b:
            android.database.sqlite.SQLiteDatabase r9 = r6.mDb
            java.lang.String r2 = "Track"
            java.lang.String r4 = "_id"
            r9.insert(r2, r4, r8)
        L34:
            r9 = r3
        L35:
            if (r9 == 0) goto L62
            com.amazon.mp3.library.item.factory.CirrusSourceLibraryItemFactory r2 = new com.amazon.mp3.library.item.factory.CirrusSourceLibraryItemFactory
            r2.<init>(r7)
            com.amazon.mp3.library.item.MusicTrack r2 = r2.getCloudTrackForLuid(r0)
            boolean r3 = r6.hasAsinChanged(r8, r0, r2)
            if (r3 == 0) goto L49
            r6.substituteAsinForDownloadedTracks(r7, r8, r0, r2)
        L49:
            int r7 = r6.updateByLuid(r8, r0)
            if (r7 != 0) goto L5d
            boolean r0 = android.text.TextUtils.isEmpty(r1)
            if (r0 != 0) goto L5d
            java.lang.String r7 = com.amazon.mp3.util.IdGenerator.generatePrimeLuid(r1)
            int r7 = r6.updateByAsin(r1, r7, r8)
        L5d:
            if (r7 <= 0) goto L62
            r6.updateLocalTracks(r8)
        L62:
            r3 = r9
        L63:
            if (r3 == 0) goto L68
            com.amazon.mp3.library.provider.source.cirrus.dbutils.TrackDataUtil$Action r7 = com.amazon.mp3.library.provider.source.cirrus.dbutils.TrackDataUtil.Action.UPDATE
            goto L6a
        L68:
            com.amazon.mp3.library.provider.source.cirrus.dbutils.TrackDataUtil$Action r7 = com.amazon.mp3.library.provider.source.cirrus.dbutils.TrackDataUtil.Action.INSERT
        L6a:
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.mp3.library.provider.source.cirrus.dbutils.TrackDataUtil.updateOrInsertTrack(android.content.Context, android.content.ContentValues, boolean):com.amazon.mp3.library.provider.source.cirrus.dbutils.TrackDataUtil$Action");
    }

    /* JADX INFO: renamed from: com.amazon.mp3.library.provider.source.cirrus.dbutils.TrackDataUtil$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$mp3$library$provider$source$cirrus$dbutils$TrackDataUtil$Version;

        static {
            int[] iArr = new int[Version.values().length];
            $SwitchMap$com$amazon$mp3$library$provider$source$cirrus$dbutils$TrackDataUtil$Version = iArr;
            try {
                iArr[Version.DOES_NOT_EXIST.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$mp3$library$provider$source$cirrus$dbutils$TrackDataUtil$Version[Version.MISMATCH.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$mp3$library$provider$source$cirrus$dbutils$TrackDataUtil$Version[Version.MATCH.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    private boolean hasAsinChanged(ContentValues values, String coid, MusicTrack oldTrack) {
        String asin = oldTrack == null ? null : oldTrack.getAsin();
        String asString = values.getAsString("asin");
        return (oldTrack == null || coid == null || asString == null || asString.equals(asin) || oldTrack.getOwnershipStatus().isOwned() || oldTrack.getLocalUri() == null) ? false : true;
    }

    private void substituteAsinForDownloadedTracks(Context context, ContentValues values, String coid, MusicTrack oldTrack) {
        String asin = oldTrack == null ? null : oldTrack.getAsin();
        String asString = values.getAsString("asin");
        String asString2 = values.getAsString(IntlConfiguration.MARKETPLACE);
        new LocalTrackUtil(this.mDb).replaceAsinByLuid(asString, coid, oldTrack);
        new TrackDataUtil(this.mDb).replaceAsin(asString, asin, asString2);
        new UpdateTracksAction(context, MusicDownloader.getInstance(context), new CirrusSourceLibraryItemFactory(context)).onDownloadedContentAsinChanged(asString, asin);
    }

    private void updateLocalTracks(final ContentValues values) {
        String asString = values.getAsString("asin");
        updateLocalTrackCatalogStatus(asString, values);
        ContentValues contentValues = new ContentValues(3);
        if (values.containsKey("is_explicit")) {
            contentValues.put("is_explicit", Integer.valueOf(values.getAsInteger("is_explicit").intValue()));
        }
        if (values.containsKey("content_encoding")) {
            contentValues.put("content_encoding", values.getAsString("content_encoding"));
        }
        if (values.containsKey("is_exclusion_tagged")) {
            contentValues.put("is_exclusion_tagged", Integer.valueOf(values.getAsInteger("is_exclusion_tagged").intValue()));
        }
        if (contentValues.size() > 0) {
            this.mDb.update("Track", contentValues, "source=? AND asin=?", new String[]{String.valueOf(1), asString});
        }
    }

    private void updateLocalTrackCatalogStatus(String asin, final ContentValues values) {
        ContentValues contentValues = new ContentValues(2);
        if (values.containsKey("prime_status")) {
            contentValues.put("prime_status", Integer.valueOf(values.getAsInteger("prime_status").intValue()));
        }
        if (values.containsKey("catalog_status_tiers")) {
            contentValues.put("catalog_status_tiers", Integer.valueOf(values.getAsInteger("catalog_status_tiers").intValue()));
        }
        this.mDb.update("Track", contentValues, "ownership_status > 200 AND source=? AND asin=?", new String[]{String.valueOf(1), asin});
    }

    public int removeTrack(final Context context, final String trackId) throws Throwable {
        int iDelete = this.mDb.delete("Track", DbUtil.applyBinaryOperator("luid=?", "AND", "prime_status =? OR purchased=1"), new String[]{trackId, String.valueOf(ContentCatalogStatus.NON_CATALOG.getValue())});
        TrackUtil.deleteUserPlaylistTrackFromDeviceIfPossible(context, trackId);
        if (iDelete != 0) {
            return iDelete;
        }
        ContentValues contentValues = new ContentValues();
        contentValues.putNull("luid");
        contentValues.put("ownership_status", Integer.valueOf(ContentOwnershipStatus.NOT_IN_LIBRARY.getValue()));
        return this.mDb.update("Track", contentValues, "luid=?", new String[]{trackId});
    }

    public int deleteTracksByLuid(String[] luids) {
        Validate.notEmpty(luids);
        Validate.noNullElements(luids);
        return this.mDb.delete("Track", "luid=?", luids);
    }
}
