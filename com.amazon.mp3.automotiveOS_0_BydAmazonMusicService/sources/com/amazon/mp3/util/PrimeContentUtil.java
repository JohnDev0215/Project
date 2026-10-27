package com.amazon.mp3.util;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteConstraintException;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.text.TextUtils;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.library.item.CatalogContent;
import com.amazon.mp3.library.item.MusicTrack;
import com.amazon.mp3.library.provider.MediaProvider;
import com.amazon.mp3.library.provider.source.cirrus.CirrusDatabase;
import com.amazon.mp3.prime.ContentCatalogStatus;
import com.amazon.mp3.prime.ContentOwnershipStatus;
import com.amazon.mp3.prime.ContentUnavailableReason;
import com.amazon.mp3.prime.DeviceAuthorizationManager;
import com.amazon.mp3.prime.PrimePlaylistUtil;
import com.amazon.mp3.prime.browse.metadata.CatalogStatusTiers;
import com.amazon.mp3.prime.cta.AddRemovePrimeManager;
import com.amazon.mp3.util.ContentAccessUtil;
import com.amazon.mp3.util.DbUtil;
import com.amazon.music.find.utils.PageUriUtils;
import com.amazon.music.platform.data.eligibility.PlaymodeEligibility;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Map;

/* JADX INFO: loaded from: classes4.dex */
public class PrimeContentUtil {
    private static final String TAG = "PrimeContentUtil";

    public interface ContentAvailabilityStateListener {
        void resultReceived(boolean contentAvailable, ContentUnavailableReason reasonUnavailable);
    }

    public static boolean isCatalogNonAdded(ContentCatalogStatus catalogStatus, ContentOwnershipStatus ownershipStatus) {
        return (!catalogStatus.isHawkfire() || ownershipStatus.isOwned() || ownershipStatus.isInLibrary()) ? false : true;
    }

    public static boolean isTrackDownloadableCatalogContent(ContentCatalogStatus catalogStatus, ContentOwnershipStatus ownershipStatus) {
        return catalogStatus.isHawkfire() && !ownershipStatus.isOwned();
    }

    public static void removeAllPrimeContent(boolean fromCirrus) {
        Context context = AmazonApplication.getContext();
        PrimePlaylistUtil.removeAllPrimePlaylists(context, fromCirrus);
        AddRemovePrimeManager.getInstance(context).removeAllPrimeTracks(context, fromCirrus);
    }

    public static void tryToAutoResolveEnabilityProblem(final CatalogContent item, final ContentAvailabilityStateListener listener) {
        ContentAccessUtil.ContentAccessOperation contentAccessOperation;
        if ("cirrus-local".equals(item.getSource())) {
            contentAccessOperation = ContentAccessUtil.ContentAccessOperation.PLAY_OFFLINE;
        } else {
            contentAccessOperation = ContentAccessUtil.ContentAccessOperation.STREAM;
        }
        tryToAutoResolveEnabilityProblem(item, contentAccessOperation, listener);
    }

    public static void tryToAutoResolveEnabilityProblem(final CatalogContent item, final ContentAccessUtil.ContentAccessOperation operation, final ContentAvailabilityStateListener listener) {
        if (AmazonApplication.onMainThread()) {
            new AutoResolveEnabilityTask(item, operation, listener).execute(new Void[0]);
        } else {
            AmazonApplication.getDefaultHandler().post(new Runnable() { // from class: com.amazon.mp3.util.PrimeContentUtil.1
                @Override // java.lang.Runnable
                public void run() {
                    new AutoResolveEnabilityTask(item, operation, listener).execute(new Void[0]);
                }
            });
        }
    }

    private static class AutoResolveEnabilityTask extends AsyncTask<Void, Void, ContentUnavailableReason> {
        private final CatalogContent mItem;
        private final ContentAvailabilityStateListener mListener;
        private final ContentAccessUtil.ContentAccessOperation mOperation;

        public AutoResolveEnabilityTask(CatalogContent item, ContentAccessUtil.ContentAccessOperation operation, ContentAvailabilityStateListener listener) {
            this.mItem = item;
            this.mListener = listener;
            this.mOperation = operation;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public ContentUnavailableReason doInBackground(Void... params) {
            DeviceAuthorizationManager deviceAuthorizationManager = DeviceAuthorizationManager.getInstance();
            ContentAccessUtil.ContentAccessOperation contentAccessOperation = this.mOperation;
            ContentAccessUtil.ContentAccessOperation contentAccessOperation2 = ContentAccessUtil.ContentAccessOperation.PLAY_OFFLINE;
            if ((contentAccessOperation == contentAccessOperation2 || contentAccessOperation == ContentAccessUtil.ContentAccessOperation.DOWNLOAD) && deviceAuthorizationManager.shouldRefreshPrimeAuthorization()) {
                Log.info(PrimeContentUtil.TAG, "Device auth is stale, refreshing.");
                deviceAuthorizationManager.refresh();
            }
            ContentUnavailableReason catalogContentUnavailableReason = ContentAccessUtil.getCatalogContentUnavailableReason(this.mItem, this.mOperation);
            if (catalogContentUnavailableReason == ContentUnavailableReason.DEVICE_NOT_PRIME_AUTHORIZED) {
                Log.info(PrimeContentUtil.TAG, "Device not authorized for prime content, trying to force authorize.");
                if (!deviceAuthorizationManager.primeAuthorizeDevice()) {
                    Log.info(PrimeContentUtil.TAG, "Prime authorize device failed");
                } else {
                    catalogContentUnavailableReason = ContentAccessUtil.getCatalogContentUnavailableReason(this.mItem, this.mOperation);
                    Log.info(PrimeContentUtil.TAG, "Prime authorize device complete");
                }
            }
            if (this.mOperation == contentAccessOperation2 && catalogContentUnavailableReason == ContentUnavailableReason.TOKEN_EXPIRED) {
                return ConnectivityUtil.hasAnyInternetConnection() && ConnectivityUtil.isAvailable() ? ContentAccessUtil.getCatalogContentUnavailableReason(this.mItem, this.mOperation) : catalogContentUnavailableReason;
            }
            return catalogContentUnavailableReason;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public void onPostExecute(ContentUnavailableReason reasonUnavailable) {
            this.mListener.resultReceived(reasonUnavailable == null, reasonUnavailable);
        }
    }

    public static void checkOfflineAccess(final CatalogContent item, final ContentAvailabilityStateListener listener) {
        checkOfflineAccess(item, listener, ContentAccessUtil.ContentAccessOperation.DOWNLOAD);
    }

    private static void checkOfflineAccess(final CatalogContent item, final ContentAvailabilityStateListener listener, ContentAccessUtil.ContentAccessOperation operation) {
        if (ContentAccessUtil.getCatalogContentUnavailableReason(item, operation) != null || (item != null && item.isAnyCatalog() && DeviceAuthorizationManager.getInstance().shouldRefreshPrimeAuthorization())) {
            tryToAutoResolveEnabilityProblem(item, operation, listener);
        } else {
            listener.resultReceived(true, null);
        }
    }

    public static int addPrimeTracksInDb(SQLiteDatabase db, Collection<String> asins) {
        int iUpdatePrimeTrackStatusInDb = updatePrimeTrackStatusInDb(db, asins, null, ContentOwnershipStatus.ADDED);
        if (iUpdatePrimeTrackStatusInDb > 0) {
            try {
                ContentValues contentValues = new ContentValues();
                long jCurrentTimeMillis = System.currentTimeMillis();
                contentValues.put("date_created", Long.valueOf(jCurrentTimeMillis));
                contentValues.put("date_updated", Long.valueOf(jCurrentTimeMillis));
                DbUtil.WhereClause whereClause = new DbUtil.WhereClause("asin", new ArrayList(asins), asins.size());
                db.beginTransaction();
                db.update("Track", contentValues, whereClause.getClause(), whereClause.getArgs());
                ContentValues contentValues2 = new ContentValues();
                ContentValues contentValues3 = new ContentValues();
                ContentValues contentValues4 = new ContentValues();
                contentValues4.put("playlist_track_status", Integer.valueOf(MusicTrack.PlaylistTrackStatus.CATALOG_TRACK_IN_LIBRARY.getValue()));
                for (String str : asins) {
                    String strGeneratePrimeLuid = IdGenerator.generatePrimeLuid(str);
                    contentValues2.put("luid", strGeneratePrimeLuid);
                    contentValues3.put("catalog_status_tiers", Integer.valueOf(CatalogStatusTiers.INSTANCE.createFromSubscription(UserSubscriptionUtil.getSubscription().getType()).serialize()));
                    Log.verbose(TAG, "Adding a temp prime luid: " + strGeneratePrimeLuid);
                    try {
                        db.update("Track", contentValues2, "asin =? AND luid IS NULL", new String[]{str});
                        db.update("Track", contentValues4, "luid =?", new String[]{str});
                        db.update("Track", contentValues3, "asin =? ", new String[]{str});
                        ContentValues contentValues5 = new ContentValues();
                        contentValues5.put("playlist_track_status", Integer.valueOf(MusicTrack.PlaylistTrackStatus.NOT_CATALOG_TRACK.getValue()));
                        db.update("Track", contentValues5, "luid IS NOT NULL AND asin =?  AND source=1", new String[]{str});
                    } catch (SQLiteConstraintException unused) {
                        Log.error(TAG, "Duplicate asin exists in the table, skipping the update, asin: " + str);
                    }
                }
                db.setTransactionSuccessful();
            } finally {
                db.endTransaction();
            }
        }
        return iUpdatePrimeTrackStatusInDb;
    }

    public static int addPrimeTracksInDbForPlaylist(SQLiteDatabase db, Collection<String> asins) throws Throwable {
        int value;
        ContentValues contentValues = new ContentValues();
        long jCurrentTimeMillis = System.currentTimeMillis();
        contentValues.put("date_created", Long.valueOf(jCurrentTimeMillis));
        contentValues.put("date_updated", Long.valueOf(jCurrentTimeMillis));
        DbUtil.WhereClause whereClause = new DbUtil.WhereClause("asin", new ArrayList(asins), asins.size());
        DbUtil.applyBinaryOperator(whereClause.getClause(), "AND", "ownership_status<300");
        Cursor cursorQuery = db.query(true, "Track", null, DbUtil.applyBinaryOperator(DbUtil.applyBinaryOperator(whereClause.getClause(), "AND", "playlist_track_status=" + MusicTrack.PlaylistTrackStatus.NOT_CATALOG_TRACK.getValue()), "AND", "source = 0"), whereClause.getArgs(), null, null, null, null);
        try {
            db.beginTransaction();
            int columnIndex = cursorQuery.getColumnIndex("asin");
            int columnIndex2 = cursorQuery.getColumnIndex("ownership_status");
            int i = 0;
            while (cursorQuery.moveToNext()) {
                String string = cursorQuery.getString(columnIndex);
                ContentValues contentValues2 = new ContentValues();
                Boolean boolValueOf = Boolean.valueOf(cursorQuery.getInt(columnIndex2) < 300);
                DatabaseUtils.cursorRowToContentValues(cursorQuery, contentValues2);
                contentValues2.remove("_id");
                contentValues2.put("luid", string);
                contentValues2.put("ownership_status", Integer.valueOf(ContentOwnershipStatus.NOT_IN_LIBRARY.getValue()));
                if (boolValueOf.booleanValue()) {
                    value = MusicTrack.PlaylistTrackStatus.CATALOG_TRACK_IN_LIBRARY.getValue();
                } else {
                    value = MusicTrack.PlaylistTrackStatus.CATALOG_TRACK_NOT_IN_LIBRARY.getValue();
                }
                contentValues2.put("playlist_track_status", Integer.valueOf(value));
                try {
                    if (db.replace("Track", null, contentValues2) > -1) {
                        i++;
                    }
                } catch (Throwable th) {
                    th = th;
                    cursorQuery.close();
                    db.endTransaction();
                    throw th;
                }
            }
            db.setTransactionSuccessful();
            cursorQuery.close();
            db.endTransaction();
            return i;
        } catch (Throwable th2) {
            th = th2;
        }
    }

    public static int updatePrimeTrackStatusInDb(SQLiteDatabase db, Collection<String> asins, ContentCatalogStatus primeStatus, ContentOwnershipStatus ownershipStatus) {
        if (db == null || !db.isOpen() || asins == null || asins.isEmpty()) {
            return 0;
        }
        Log.verbose(TAG, "updatePrimeTrackStatusInDb: " + asins + ", primeStatus: " + primeStatus + ", ownershipStatus: " + ownershipStatus);
        ContentValues contentValues = new ContentValues();
        if (primeStatus != null) {
            contentValues.put("prime_status", Integer.valueOf(primeStatus.getValue()));
        }
        if (ownershipStatus != null) {
            contentValues.put("ownership_status", Integer.valueOf(ownershipStatus.getValue()));
        }
        DbUtil.WhereClause whereClause = new DbUtil.WhereClause("asin", new ArrayList(asins), asins.size());
        return db.update("Track", contentValues, whereClause.getClause() + " AND playlist_track_status=" + MusicTrack.PlaylistTrackStatus.NOT_CATALOG_TRACK.getValue(), whereClause.getArgs());
    }

    public static int markPrimeLibraryTracksAsNotDownloaded(SQLiteDatabase db, Collection<String> asins) {
        if (db == null || !db.isOpen() || asins == null || asins.isEmpty()) {
            return 0;
        }
        ContentValues contentValues = new ContentValues();
        contentValues.put("ownership_status", Integer.valueOf(ContentOwnershipStatus.NOT_IN_LIBRARY.getValue()));
        contentValues.put("download_state", (Integer) 5);
        DbUtil.WhereClause whereClause = new DbUtil.WhereClause("asin", new ArrayList(asins), asins.size());
        return db.update("Track", contentValues, whereClause.getClause(), whereClause.getArgs());
    }

    public static void copyAsCatalogTrackAndUpdatePlaylist(SQLiteDatabase db, Collection<String> asins) {
        if (db == null || !db.isOpen() || asins == null || asins.isEmpty()) {
            return;
        }
        DbUtil.WhereClause whereClause = new DbUtil.WhereClause("asin", new ArrayList(asins), asins.size());
        String str = whereClause.getClause() + " AND playlist_track_status=" + MusicTrack.PlaylistTrackStatus.NOT_CATALOG_TRACK.getValue() + " AND luid IS NOT NULL AND " + PageUriUtils.SOURCE_QUERY_PARAM + "=0";
        String[] args = whereClause.getArgs();
        String str2 = PageUriUtils.SOURCE_QUERY_PARAM;
        Cursor cursorQuery = db.query("Track", null, str, args, null, null, null);
        while (cursorQuery.moveToNext()) {
            try {
                ContentValues contentValues = new ContentValues();
                DatabaseUtils.cursorRowToContentValues(cursorQuery, contentValues);
                String asString = contentValues.getAsString("luid");
                String asString2 = contentValues.getAsString("asin");
                contentValues.remove("_id");
                contentValues.put("luid", asString2);
                MusicTrack.PlaylistTrackStatus playlistTrackStatus = MusicTrack.PlaylistTrackStatus.CATALOG_TRACK_NOT_IN_LIBRARY;
                contentValues.put("playlist_track_status", Integer.valueOf(playlistTrackStatus.getValue()));
                ContentValues contentValues2 = new ContentValues();
                contentValues2.put("playlist_track_status", Integer.valueOf(playlistTrackStatus.getValue()));
                String str3 = str2;
                db.update("Track", contentValues2, "asin=? AND playlist_track_status=" + MusicTrack.PlaylistTrackStatus.NOT_CATALOG_TRACK.getValue() + " AND luid IS NOT NULL AND " + str2 + "=1", new String[]{asString2});
                ContentValues contentValues3 = new ContentValues();
                String[] strArr = {asString};
                contentValues3.put("track_luid", asString2);
                if (db.update("PlaylistTrack", contentValues3, "track_luid =?", strArr) > 0) {
                    db.replace("Track", null, contentValues);
                } else {
                    db.update("Track", contentValues, "luid =?", new String[]{asString2});
                }
                str2 = str3;
            } finally {
                cursorQuery.close();
            }
        }
    }

    public static void loadLibraryInfoForTrack(Context context, MusicTrack trackWithAsin) {
        if (TextUtils.isEmpty(trackWithAsin.getAsin())) {
            Log.warning(TAG, "Cannot fetch library info for track without asin");
        } else {
            loadLibraryInfoForTracksMap(context, Collections.singletonMap(trackWithAsin.getAsin(), trackWithAsin));
        }
    }

    public static void loadLibraryInfoForTracksMap(Context context, Map<String, MusicTrack> asinTrackMap) {
        SQLiteDatabase readOnlyDatabase = CirrusDatabase.getReadOnlyDatabase(context);
        String[] strArr = {"asin", "luid", "album_art_id", "ownership_status", "prime_status", PageUriUtils.SOURCE_QUERY_PARAM, "catalog_status_tiers", "playmode_eligibility"};
        DbUtil.WhereClause whereClause = new DbUtil.WhereClause("asin", new HashSet(asinTrackMap.keySet()));
        Cursor cursorQuery = readOnlyDatabase.query("Track", strArr, whereClause.getClause(), whereClause.getArgs(), "asin", null, null);
        while (cursorQuery.moveToNext()) {
            String str = cursorQuery.getInt(cursorQuery.getColumnIndex(PageUriUtils.SOURCE_QUERY_PARAM)) == 1 ? "cirrus-local" : "cirrus";
            String string = cursorQuery.getString(cursorQuery.getColumnIndex("asin"));
            String string2 = cursorQuery.getString(cursorQuery.getColumnIndex("luid"));
            long j = cursorQuery.getLong(cursorQuery.getColumnIndex("album_art_id"));
            int i = cursorQuery.getInt(cursorQuery.getColumnIndex("ownership_status"));
            int i2 = cursorQuery.getInt(cursorQuery.getColumnIndex("prime_status"));
            CatalogStatusTiers catalogStatusTiersDeserialize = CatalogStatusTiers.INSTANCE.deserialize(cursorQuery.getInt(cursorQuery.getColumnIndex("catalog_status_tiers")));
            PlaymodeEligibility playmodeEligibilityFromJson = EligibilityConverterUtil.getPlaymodeEligibilityFromJson(cursorQuery.getString(cursorQuery.getColumnIndex("playmode_eligibility")));
            if (string != null) {
                MusicTrack musicTrack = asinTrackMap.get(string);
                String source = musicTrack.getSource();
                if (source == null || source.equals("cirrus")) {
                    musicTrack.setSource(str);
                    musicTrack.setLuid(string2);
                    musicTrack.setAlbumArtId(j);
                    musicTrack.setMaxContentOwnershipStatusValue(i);
                    musicTrack.setMinContentCatalogStatusValue(i2);
                    musicTrack.setMaxContentCatalogStatusValue(i2);
                    if (ContentCatalogStatus.fromValue(i2).isPreviouslyCatalog()) {
                        musicTrack.setMaxNonPreviousContentCatalogStatusValue(ContentCatalogStatus.UNKNOWN.getValue());
                    } else {
                        musicTrack.setMaxNonPreviousContentCatalogStatusValue(i2);
                    }
                    if (catalogStatusTiersDeserialize.isInCatalog()) {
                        musicTrack.setCatalogStatusTiers(catalogStatusTiersDeserialize);
                    }
                    musicTrack.setPlaymodeEligibility(playmodeEligibilityFromJson);
                }
                if (musicTrack.getLuid() != null) {
                    musicTrack.setContentUri(MediaProvider.Tracks.getContentUri(musicTrack.getSource(), musicTrack.getLuid()));
                }
            }
        }
        cursorQuery.close();
    }
}
