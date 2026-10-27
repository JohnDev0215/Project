package com.amazon.mp3.playlist.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.text.TextUtils;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.activity.settings.SettingsUtil;
import com.amazon.mp3.library.item.MusicTrack;
import com.amazon.mp3.library.provider.source.cirrus.CirrusDatabase;
import com.amazon.mp3.library.provider.source.cirrus.CirrusMediaSource;
import com.amazon.mp3.library.provider.source.cirrus.dbutils.LocalRepositoryEvents;
import com.amazon.mp3.library.provider.source.cirrus.dbutils.PlaylistUpdateEvent;
import com.amazon.mp3.library.provider.source.cirrus.dbutils.PlaylistUpdateEventType;
import com.amazon.mp3.playlist.CatalogPlaylist;
import com.amazon.mp3.playlist.CatalogPlaylistTrack;
import com.amazon.mp3.playlist.EntityPlaylist;
import com.amazon.mp3.playlist.SharedUserPlaylist;
import com.amazon.mp3.prime.ContentCatalogStatus;
import com.amazon.mp3.prime.ContentOwnershipStatus;
import com.amazon.mp3.prime.PrimeCache;
import com.amazon.mp3.prime.PrimePlaylistUtil;
import com.amazon.mp3.prime.PrimePlaylistsTable;
import com.amazon.mp3.recentlyplayed.RecentlyPlayedItem;
import com.amazon.mp3.recentlyplayed.RecentlyPlayedManager;
import com.amazon.mp3.util.Clock;
import com.amazon.mp3.util.DbUtil;
import com.amazon.mp3.util.IdGenerator;
import com.amazon.mp3.util.Log;
import com.amazon.mp3.util.MatchHashUtil;
import com.amazon.mp3.util.StringUtil;
import com.amazon.music.skyfire.models.TetheredMessageKey;
import com.google.android.gms.cast.HlsSegmentFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes3.dex */
public class PrimePlaylistDatabaseManager implements PrimePlaylistDatabase {
    private static final String TAG = "PrimePlaylistDatabaseManager";
    private PrimePlaylistDatabaseCache mCache;
    private final Context mContext;
    private SQLiteDatabase mDb;

    private synchronized SQLiteDatabase demandDb() {
        if (this.mDb == null) {
            this.mDb = CirrusDatabase.getWritableDatabase(this.mContext);
        }
        return this.mDb;
    }

    public PrimePlaylistDatabaseManager() {
        this(AmazonApplication.getContext());
    }

    public PrimePlaylistDatabaseManager(final Context context) {
        this(context, 900000);
    }

    public PrimePlaylistDatabaseManager(final Context context, final int cacheDuration) {
        this.mContext = context.getApplicationContext();
        this.mCache = new PrimePlaylistDatabaseCache(cacheDuration);
    }

    @Override // com.amazon.mp3.playlist.db.PrimePlaylistDatabase
    public CatalogPlaylist getPlaylist(String asin, int source) {
        return getPlaylist(asin, source, (String[]) null);
    }

    public CatalogPlaylist getPlaylist(String asin, int source, String[] projection) {
        return getPlaylist(getPlaylistId(asin, source), source, projection);
    }

    @Override // com.amazon.mp3.playlist.db.PrimePlaylistDatabase
    public CatalogPlaylist getPlaylistByLuid(final String luid, final int sourceId) {
        return getPlaylist(getPlaylistIdByCloudId(luid), sourceId, (String[]) null);
    }

    public CatalogPlaylist getPlaylist(long playlistId, int source, String[] projection) {
        Cursor cursorQuery = demandDb().query("PrimePlaylists", projection, "_id = ?", new String[]{String.valueOf(playlistId)}, null, null, null);
        CatalogPlaylist catalogPlaylistFromCursor = null;
        try {
            try {
                if (cursorQuery.moveToFirst()) {
                    catalogPlaylistFromCursor = CatalogPlaylist.fromCursor(cursorQuery);
                }
            } catch (IllegalArgumentException e) {
                Log.error(TAG, "Unable to retrieve Playlist due to invalid Cursor Columns!", e);
            }
            return catalogPlaylistFromCursor;
        } finally {
            DbUtil.closeCursor(cursorQuery);
        }
    }

    @Override // com.amazon.mp3.playlist.db.PrimePlaylistDatabase
    public void save(JSONObject playlist, int source, boolean updateTracks, boolean followed) throws Throwable {
        CatalogPlaylist catalogPlaylistFromJSON;
        EntityPlaylist.Companion companion = EntityPlaylist.INSTANCE;
        if (companion.isEntityPlaylist(playlist)) {
            catalogPlaylistFromJSON = companion.fromJSON(playlist);
        } else if (SharedUserPlaylist.isSharedUserPlaylist(playlist)) {
            catalogPlaylistFromJSON = SharedUserPlaylist.fromJSON(playlist);
        } else {
            catalogPlaylistFromJSON = CatalogPlaylist.fromJSON(playlist);
        }
        Log.debug(TAG, "Preparing to insert prime playlist: %s", catalogPlaylistFromJSON.toString());
        catalogPlaylistFromJSON.setFollowed(followed);
        save(catalogPlaylistFromJSON, source, updateTracks);
    }

    @Override // com.amazon.mp3.playlist.db.PrimePlaylistDatabase
    public void save(CatalogPlaylist playlist, int source, boolean updateTracks) throws Throwable {
        int playlistId;
        playlist.setSource(PrimePlaylistsTable.translateSource(source));
        if (!playlist.isSharedUserPlaylist() && !playlist.isEntityPlaylist()) {
            playlist.setPrimeStatusFromTracks(this.mContext);
            playlist.updateTrackVideoMetadata(this.mContext);
        }
        SQLiteDatabase sQLiteDatabaseDemandDb = demandDb();
        ContentValues contentValues = playlist.toContentValues();
        int primePlaylistUpdateType = getPrimePlaylistUpdateType(playlist);
        boolean z = primePlaylistUpdateType == 2;
        if (z) {
            setNew(playlist, true);
        }
        String asin = playlist.getAsin();
        try {
            DbUtil.beginTransaction(sQLiteDatabaseDemandDb);
            int playlistId2 = getPlaylistId(asin);
            if (!z && playlistId2 == -1) {
                sQLiteDatabaseDemandDb.insert("PrimePlaylists", null, contentValues);
                Log.debug(TAG, "Inserting new prime playlist to DB: asin = %s,  title = %s", asin, playlist.getTitle());
            } else if (z) {
                sQLiteDatabaseDemandDb.update("PrimePlaylists", contentValues, "luid = ?", new String[]{playlist.getLuid()});
                Log.debug(TAG, "Updating Online playlist - major version update: " + playlist.getLuid());
            } else {
                sQLiteDatabaseDemandDb.update("PrimePlaylists", contentValues, "_id = ?", new String[]{String.valueOf(playlistId2)});
                if (primePlaylistUpdateType == 1) {
                    Log.debug(TAG, "Updated PrimePlaylistsTable for Online playlist - minor version update: " + playlist.getLuid());
                    PrimePlaylistUtil.updateLocalPrimePlaylistVersion(this.mContext, playlist.getLuid(), playlist.getVersion());
                } else {
                    Log.debug(TAG, "Updated PrimePlaylistsTable though update was not a major or minor version change. Doing nothing further to sync playlist: " + playlist.getLuid());
                }
            }
            SettingsUtil.setHasFollowedPrimePlaylists(this.mContext, true);
            sQLiteDatabaseDemandDb.setTransactionSuccessful();
            try {
                if (playlist.isFollowed()) {
                    setExpiry(asin, false);
                }
            } catch (Exception e) {
                Log.error(TAG, "Error while trying to set the playlist expiry state:" + asin, e);
            }
            if (updateTracks && (playlistId = getPlaylistId(asin, source)) != -1) {
                playlist.setDatabaseId(playlistId);
                saveTracks(playlist);
            }
            if (updateTracks && primePlaylistUpdateType == 1) {
                updateLocalTracksPrimeStatus(playlist);
            }
        } finally {
            sQLiteDatabaseDemandDb.endTransaction();
        }
    }

    private void updateLocalTracksPrimeStatus(CatalogPlaylist playlist) {
        SQLiteDatabase sQLiteDatabaseDemandDb = demandDb();
        sQLiteDatabaseDemandDb.beginTransaction();
        try {
            ContentValues contentValues = new ContentValues();
            String[] strArr = new String[1];
            for (CatalogPlaylistTrack catalogPlaylistTrack : playlist.getTracks()) {
                strArr[0] = String.valueOf(MatchHashUtil.generatePrimePlaylistTrackMatchHash(catalogPlaylistTrack));
                contentValues.put("catalog_status", Integer.valueOf(catalogPlaylistTrack.getCatalogStatus().getValue()));
                sQLiteDatabaseDemandDb.update("PrimePlaylistTracks", contentValues, "match_hash = ?", strArr);
            }
            sQLiteDatabaseDemandDb.setTransactionSuccessful();
        } finally {
            sQLiteDatabaseDemandDb.endTransaction();
        }
    }

    protected int getPrimePlaylistUpdateType(CatalogPlaylist playlist) {
        String asin = playlist.getAsin();
        String luid = playlist.getLuid();
        String version = playlist.getVersion();
        Cursor cursorQuery = demandDb().query("PrimePlaylists", new String[]{"asin", "version"}, DbUtil.applyBinaryOperator("luid IS NOT NULL AND luid!=\"\" AND is_following=1 AND luid=?", "AND", "source=?"), new String[]{luid, String.valueOf(0)}, null, null, null);
        try {
            if (cursorQuery.moveToFirst()) {
                String string = cursorQuery.getString(cursorQuery.getColumnIndex("asin"));
                if (asin.equals(string) && !playlist.isSharedUserPlaylist() && !playlist.isEntityPlaylist()) {
                    String string2 = cursorQuery.getString(cursorQuery.getColumnIndex("version"));
                    if (!version.equals(string2)) {
                        Log.verbose(TAG, "Prime playlist minor update: luid = %s,  oldVersion = %s, newVersion = %s", luid, string2, version);
                        return 1;
                    }
                }
                Log.verbose(TAG, "Prime playlist updated: luid = %s,  oldAsin = %s, newAsin = %s", luid, string, asin);
                return 2;
            }
            return 0;
        } finally {
            cursorQuery.close();
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:50:0x012e  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void saveTracks(com.amazon.mp3.playlist.CatalogPlaylist r23) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 309
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.mp3.playlist.db.PrimePlaylistDatabaseManager.saveTracks(com.amazon.mp3.playlist.CatalogPlaylist):void");
    }

    @Override // com.amazon.mp3.playlist.db.PrimePlaylistDatabase
    public void follow(final String asin, final String luid) {
        updateFollow(asin, luid, true);
    }

    public void unfollow(final String asin) {
        updateFollow(asin, null, false);
    }

    @Override // com.amazon.mp3.playlist.db.PrimePlaylistDatabase
    public void markForExpiry(String asin) {
        setExpiry(asin, true);
    }

    private void setExpiry(final String asin, boolean shouldExpire) {
        if (shouldExpire) {
            this.mCache.insertAsin(asin, true, null);
        } else {
            this.mCache.removeAsin(asin);
        }
    }

    @Override // com.amazon.mp3.playlist.db.PrimePlaylistDatabase
    public boolean cacheContainsPrimePlaylist(final String asin) {
        return this.mCache.containsAsin(asin);
    }

    private void updateFollow(final String asin, final String luid, boolean setFollow) {
        SQLiteDatabase sQLiteDatabaseDemandDb = demandDb();
        setExpiry(asin, !setFollow);
        int playlistId = getPlaylistId(asin, 0);
        if (playlistId != -1) {
            String[] strArr = {String.valueOf(playlistId)};
            ContentValues contentValues = new ContentValues();
            String str = setFollow ? "1" : "0";
            contentValues.put("is_following", str);
            if (setFollow) {
                contentValues.put("luid", luid);
                long jCurrentTimeSeconds = Clock.currentTimeSeconds();
                contentValues.put("created_date", Long.valueOf(jCurrentTimeSeconds));
                contentValues.put("last_modified_date", Long.valueOf(jCurrentTimeSeconds));
            }
            LocalRepositoryEvents.INSTANCE.sendEvent(new PlaylistUpdateEvent(setFollow ? PlaylistUpdateEventType.FOLLOWED : PlaylistUpdateEventType.UNFOLLOWED));
            Log.verbose(TAG, "updating follow state for prime playlist: %s, %s", asin, str);
            try {
                DbUtil.beginTransaction(sQLiteDatabaseDemandDb);
                sQLiteDatabaseDemandDb.update("PrimePlaylists", contentValues, "_id = ?", strArr);
                sQLiteDatabaseDemandDb.setTransactionSuccessful();
            } finally {
                sQLiteDatabaseDemandDb.endTransaction();
            }
        }
    }

    @Override // com.amazon.mp3.playlist.db.PrimePlaylistDatabase
    public void delete(final long playlistId) {
        if (playlistId == -1) {
            Log.warning(TAG, "unable to delete unknown playlist Id");
            return;
        }
        ArrayList arrayList = new ArrayList();
        arrayList.add(Long.valueOf(playlistId));
        SQLiteDatabase sQLiteDatabaseDemandDb = demandDb();
        try {
            DbUtil.beginTransaction(sQLiteDatabaseDemandDb);
            delete(arrayList);
            sQLiteDatabaseDemandDb.setTransactionSuccessful();
        } finally {
            sQLiteDatabaseDemandDb.endTransaction();
        }
    }

    private void delete(final List<Long> playlistIds) {
        if (playlistIds.isEmpty()) {
            Log.debug(TAG, "delete called with empty playlist id list");
            return;
        }
        demandDb();
        String strJoin = TextUtils.join(",", playlistIds);
        int iDelete = this.mDb.delete("PrimePlaylists", "_id in (" + strJoin + ")", null);
        String str = TAG;
        Log.debug(str, iDelete + "rows deleted from PrimePlaylistsTable: " + strJoin);
        Log.debug(str, this.mDb.delete("PrimePlaylistToTrack", "playlist_id in (" + strJoin + ")", null) + "rows deleted from PrimePlaylistsToTracksTable: " + strJoin);
    }

    public int getPlaylistId(String asin, int source) {
        Cursor cursorQuery = demandDb().query("PrimePlaylists", new String[]{"_id"}, DbUtil.applyBinaryOperator("asin=?", "AND", "source=?"), new String[]{asin, String.valueOf(source)}, null, null, null);
        try {
            if (cursorQuery.moveToNext()) {
                return cursorQuery.getInt(cursorQuery.getColumnIndex("_id"));
            }
            return -1;
        } finally {
            cursorQuery.close();
        }
    }

    public int getPlaylistId(String asin) {
        Cursor cursorQuery = demandDb().query("PrimePlaylists", new String[]{"_id"}, "asin=?", new String[]{asin}, null, null, null);
        try {
            if (cursorQuery.moveToNext()) {
                return cursorQuery.getInt(cursorQuery.getColumnIndex("_id"));
            }
            return -1;
        } finally {
            cursorQuery.close();
        }
    }

    @Override // com.amazon.mp3.playlist.db.PrimePlaylistDatabase
    public long getPlaylistIdByCloudId(String luid) {
        Cursor cursorQuery = demandDb().query("PrimePlaylists", new String[]{"_id"}, "luid=?", new String[]{luid}, null, null, null);
        try {
            if (cursorQuery.moveToFirst()) {
                return cursorQuery.getLong(cursorQuery.getColumnIndex("_id"));
            }
            return -1L;
        } finally {
            cursorQuery.close();
        }
    }

    public CatalogPlaylistTrack getTrack(String asin, int source) {
        return getTrack(asin, source, null);
    }

    public CatalogPlaylistTrack getTrack(String asin, int source, String[] projection) throws Throwable {
        Cursor cursorQuery;
        Throwable th;
        try {
            cursorQuery = demandDb().query("PrimePlaylistTracks", projection, "asin = ?", new String[]{asin}, null, null, null);
        } catch (Throwable th2) {
            cursorQuery = null;
            th = th2;
        }
        try {
            if (!cursorQuery.moveToFirst()) {
                DbUtil.closeCursor(cursorQuery);
                return null;
            }
            CatalogPlaylistTrack catalogPlaylistTrackFromCursor = CatalogPlaylistTrack.fromCursor(cursorQuery);
            DbUtil.closeCursor(cursorQuery);
            return catalogPlaylistTrackFromCursor;
        } catch (Throwable th3) {
            th = th3;
            DbUtil.closeCursor(cursorQuery);
            throw th;
        }
    }

    @Override // com.amazon.mp3.playlist.db.PrimePlaylistDatabase
    public CatalogPlaylistTrack getTrackByArtworkId(String artworkId, int source) {
        return getTrackByArtworkId(artworkId, source, null);
    }

    public CatalogPlaylistTrack getTrackByArtworkId(String artworkId, int source, String[] projection) throws Throwable {
        Cursor cursorQuery;
        Throwable th;
        try {
            cursorQuery = demandDb().query("PrimePlaylistTracks", projection, "artwork_id = ? ", new String[]{artworkId}, null, null, null);
        } catch (Throwable th2) {
            cursorQuery = null;
            th = th2;
        }
        try {
            if (!cursorQuery.moveToFirst()) {
                DbUtil.closeCursor(cursorQuery);
                return null;
            }
            CatalogPlaylistTrack catalogPlaylistTrackFromCursor = CatalogPlaylistTrack.fromCursor(cursorQuery);
            DbUtil.closeCursor(cursorQuery);
            return catalogPlaylistTrackFromCursor;
        } catch (Throwable th3) {
            th = th3;
            DbUtil.closeCursor(cursorQuery);
            throw th;
        }
    }

    private boolean findTrackInLibrary(final String asin) {
        String[] strArr = {asin};
        Cursor cursorQuery = null;
        try {
            cursorQuery = demandDb().query("Track", new String[]{"_id"}, "asin = ?", strArr, null, null, null);
            return cursorQuery.moveToFirst();
        } finally {
            DbUtil.closeCursor(cursorQuery);
        }
    }

    public void addTrackToLibrary(final String asin) {
        ContentValues contentValues = new ContentValues();
        CatalogPlaylistTrack track = getTrack(asin, 0);
        SQLiteDatabase sQLiteDatabaseDemandDb = demandDb();
        DbUtil.beginTransaction(sQLiteDatabaseDemandDb);
        try {
            try {
                ContentCatalogStatus catalogStatus = track.getCatalogStatus();
                if (catalogStatus == null) {
                    catalogStatus = ContentCatalogStatus.PRIME;
                }
                contentValues.put("prime_status", Integer.valueOf(catalogStatus.getValue()));
                contentValues.put("ownership_status", Integer.valueOf(ContentOwnershipStatus.ADDED.getValue()));
                contentValues.put("asin", track.getAsin());
                contentValues.put("luid", IdGenerator.generatePrimeLuid(track.getAsin()));
                String artistName = track.getArtistName();
                String title = track.getTitle();
                contentValues.put("artist", artistName);
                contentValues.put("sort_artist", StringUtil.normalizeArtistName(artistName));
                contentValues.put("title", title);
                contentValues.put("sort_title", StringUtil.normalizeTrackName(title));
                contentValues.put("album_asin", track.getAlbumAsin());
                String albumName = track.getAlbumName();
                String albumAsin = track.getAlbumAsin();
                String albumArtistName = track.getAlbumArtistName();
                contentValues.put("album", albumName);
                contentValues.put("sort_album", StringUtil.normalizeAlbumName(albumName));
                contentValues.put("album_artist", albumArtistName);
                contentValues.put("sort_album_artist", StringUtil.normalizeArtistName(albumArtistName));
                contentValues.put("track_num", String.valueOf(track.getTrackNumber()));
                contentValues.put("duration", Integer.valueOf(track.getDurationSeconds()));
                contentValues.put("extension", HlsSegmentFormat.MP3);
                contentValues.put("album_id", Long.valueOf(IdGenerator.generateAlbumId(albumAsin, albumName)));
                long jCurrentTimeMillis = System.currentTimeMillis();
                contentValues.put("date_created", Long.valueOf(jCurrentTimeMillis));
                contentValues.put("date_updated", Long.valueOf(jCurrentTimeMillis));
                if (findTrackInLibrary(asin)) {
                    sQLiteDatabaseDemandDb.update("Track", contentValues, "asin = ? AND luid != ?", new String[]{asin, asin});
                } else {
                    sQLiteDatabaseDemandDb.insertOrThrow("Track", "_id", contentValues);
                }
                ContentValues contentValues2 = new ContentValues();
                contentValues2.put("playlist_track_status", Integer.valueOf(MusicTrack.PlaylistTrackStatus.CATALOG_TRACK_IN_LIBRARY.getValue()));
                sQLiteDatabaseDemandDb.update("Track", contentValues2, "luid =?", new String[]{asin});
                new ContentValues().put("playlist_track_status", Integer.valueOf(MusicTrack.PlaylistTrackStatus.NOT_CATALOG_TRACK.getValue()));
                sQLiteDatabaseDemandDb.update("Track", contentValues2, "luid IS NOT NULL AND asin =?  AND source=1", new String[]{asin});
                sQLiteDatabaseDemandDb.setTransactionSuccessful();
            } catch (Exception e) {
                Log.error(TAG, "Failed to add song to library", e);
            }
        } finally {
            sQLiteDatabaseDemandDb.endTransaction();
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:8:0x002e  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean hasPrimePlaylists(java.lang.String r11) {
        /*
            r10 = this;
            android.database.sqlite.SQLiteDatabase r0 = r10.demandDb()
            r8 = 1
            java.lang.String[] r4 = new java.lang.String[r8]
            int r11 = com.amazon.mp3.prime.PrimePlaylistsTable.translateSource(r11)
            java.lang.String r11 = java.lang.Integer.toString(r11)
            r9 = 0
            r4[r9] = r11
            r11 = 0
            java.lang.String r1 = "PrimePlaylists"
            java.lang.String r2 = "asin"
            java.lang.String[] r2 = new java.lang.String[]{r2}     // Catch: java.lang.Throwable -> L33
            java.lang.String r3 = "source = ? "
            r5 = 0
            r6 = 0
            r7 = 0
            android.database.Cursor r11 = r0.query(r1, r2, r3, r4, r5, r6, r7)     // Catch: java.lang.Throwable -> L33
            if (r11 == 0) goto L2e
            boolean r0 = r11.moveToFirst()     // Catch: java.lang.Throwable -> L33
            if (r0 == 0) goto L2e
            goto L2f
        L2e:
            r8 = r9
        L2f:
            com.amazon.mp3.util.DbUtil.closeCursor(r11)
            return r8
        L33:
            r0 = move-exception
            com.amazon.mp3.util.DbUtil.closeCursor(r11)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.mp3.playlist.db.PrimePlaylistDatabaseManager.hasPrimePlaylists(java.lang.String):boolean");
    }

    @Override // com.amazon.mp3.playlist.db.PrimePlaylistDatabase
    public void setNew(final CatalogPlaylist catalogPlaylist, boolean isNew) {
        catalogPlaylist.setNew(isNew);
        SQLiteDatabase sQLiteDatabaseDemandDb = demandDb();
        String[] strArr = {catalogPlaylist.getLuid()};
        ContentValues contentValues = new ContentValues();
        contentValues.put("is_new", Integer.valueOf(isNew ? Integer.parseInt("1") : Integer.parseInt("0")));
        try {
            DbUtil.beginTransaction(sQLiteDatabaseDemandDb);
            sQLiteDatabaseDemandDb.update("PrimePlaylists", contentValues, "luid = ?", strArr);
            if (!isNew) {
                ContentValues contentValues2 = new ContentValues();
                contentValues2.put("is_new", "0");
                sQLiteDatabaseDemandDb.update("PrimePlaylistToTrack", contentValues2, "playlist_id = ?", new String[]{String.valueOf(catalogPlaylist.getDatabaseId())});
                Log.debug(TAG, "Update track status to new for playlist: " + catalogPlaylist.getLuid());
            }
            sQLiteDatabaseDemandDb.setTransactionSuccessful();
            sQLiteDatabaseDemandDb.endTransaction();
            this.mContext.getContentResolver().notifyChange(CirrusMediaSource.NOTIFICATION_URI, null);
        } catch (Throwable th) {
            sQLiteDatabaseDemandDb.endTransaction();
            throw th;
        }
    }

    public void removeExpiringBrowsePlaylists() {
        String str = "timestamp < " + (System.currentTimeMillis() - ((long) this.mCache.getCacheItemMaxLifetime()));
        String str2 = "asin in (" + SQLiteQueryBuilder.buildQueryString(false, "PrimePlaylistsCacheTimes", new String[]{"asin"}, str, null, null, null, null) + ") AND is_following=0";
        ArrayList arrayList = new ArrayList();
        Set<String> uniqueAsinsFromRecentlyPlayedItems = getUniqueAsinsFromRecentlyPlayedItems(RecentlyPlayedManager.getInstance(this.mContext).queryRecentItems());
        demandDb();
        Cursor cursorQuery = this.mDb.query("PrimePlaylists", new String[]{"_id", "asin"}, str2, null, null, null, null);
        while (cursorQuery.moveToNext()) {
            try {
                if (!uniqueAsinsFromRecentlyPlayedItems.contains(cursorQuery.getString(1))) {
                    arrayList.add(Long.valueOf(cursorQuery.getLong(0)));
                }
            } catch (Throwable th) {
                cursorQuery.close();
                throw th;
            }
        }
        cursorQuery.close();
        this.mDb.beginTransaction();
        try {
            if (!arrayList.isEmpty()) {
                Log.debug(TAG, "We have expired playlists, deleting them");
                delete(arrayList);
            }
            this.mCache.remove(str, null);
            this.mDb.setTransactionSuccessful();
        } finally {
            this.mDb.endTransaction();
        }
    }

    private Set<String> getUniqueAsinsFromRecentlyPlayedItems(final List<RecentlyPlayedItem> recentlyPlayedItems) {
        HashSet hashSet = new HashSet();
        Iterator<RecentlyPlayedItem> it = recentlyPlayedItems.iterator();
        while (it.hasNext()) {
            hashSet.add(it.next().getCollectionAsin());
        }
        return hashSet;
    }

    public void clearAllBrowsePlaylists() {
        demandDb();
        try {
            DbUtil.beginTransaction(this.mDb);
            this.mDb.delete("PrimePlaylists", "is_following=0", null);
            this.mDb.delete("PrimePlaylistsCacheTimes", null, null);
            this.mDb.setTransactionSuccessful();
        } finally {
            this.mDb.endTransaction();
        }
    }

    private class PrimePlaylistDatabaseCache implements PrimeCache {
        private int mDuration;

        public PrimePlaylistDatabaseCache(int cacheDuration) {
            this.mDuration = cacheDuration;
        }

        @Override // com.amazon.mp3.prime.PrimeCache
        public int getCacheItemMaxLifetime() {
            return this.mDuration;
        }

        /* JADX WARN: Removed duplicated region for block: B:8:0x0027  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public boolean containsAsin(java.lang.String r11) {
            /*
                r10 = this;
                r0 = 1
                java.lang.String[] r5 = new java.lang.String[r0]
                r9 = 0
                r5[r9] = r11
                java.lang.String r11 = "asin"
                java.lang.String[] r3 = new java.lang.String[]{r11}
                r11 = 0
                com.amazon.mp3.playlist.db.PrimePlaylistDatabaseManager r1 = com.amazon.mp3.playlist.db.PrimePlaylistDatabaseManager.this     // Catch: java.lang.Throwable -> L2c
                android.database.sqlite.SQLiteDatabase r1 = com.amazon.mp3.playlist.db.PrimePlaylistDatabaseManager.access$000(r1)     // Catch: java.lang.Throwable -> L2c
                java.lang.String r2 = "PrimePlaylistsCacheTimes"
                java.lang.String r4 = "asin = ?"
                r6 = 0
                r7 = 0
                r8 = 0
                android.database.Cursor r11 = r1.query(r2, r3, r4, r5, r6, r7, r8)     // Catch: java.lang.Throwable -> L2c
                if (r11 == 0) goto L27
                int r1 = r11.getCount()     // Catch: java.lang.Throwable -> L2c
                if (r1 <= 0) goto L27
                goto L28
            L27:
                r0 = r9
            L28:
                com.amazon.mp3.util.DbUtil.closeCursor(r11)
                return r0
            L2c:
                r0 = move-exception
                com.amazon.mp3.util.DbUtil.closeCursor(r11)
                throw r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.amazon.mp3.playlist.db.PrimePlaylistDatabaseManager.PrimePlaylistDatabaseCache.containsAsin(java.lang.String):boolean");
        }

        public void insertAsin(String asin, boolean hasPrimeContent, String artworkUrl) {
            ContentValues contentValues = new ContentValues();
            contentValues.put("asin", asin);
            contentValues.put(TetheredMessageKey.timestamp, Long.valueOf(System.currentTimeMillis()));
            DbUtil.beginTransaction(PrimePlaylistDatabaseManager.this.mDb);
            try {
                PrimePlaylistDatabaseManager.this.mDb.insertWithOnConflict("PrimePlaylistsCacheTimes", null, contentValues, 5);
                PrimePlaylistDatabaseManager.this.mDb.setTransactionSuccessful();
            } finally {
                PrimePlaylistDatabaseManager.this.mDb.endTransaction();
            }
        }

        protected int remove(String selection, String[] args) {
            return PrimePlaylistDatabaseManager.this.mDb.delete("PrimePlaylistsCacheTimes", selection, args);
        }

        public void removeAsin(String asin) {
            String[] strArr = {asin};
            DbUtil.beginTransaction(PrimePlaylistDatabaseManager.this.mDb);
            try {
                PrimePlaylistDatabaseManager.this.mDb.delete("PrimePlaylistsCacheTimes", "asin = ?", strArr);
                PrimePlaylistDatabaseManager.this.mDb.setTransactionSuccessful();
            } finally {
                PrimePlaylistDatabaseManager.this.mDb.endTransaction();
            }
        }
    }
}
