package com.amazon.mp3.library.service.sync;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Environment;
import androidx.annotation.VisibleForTesting;
import com.amazon.mp3.activity.GrantPermissionActivity;
import com.amazon.mp3.activity.settings.SettingsUtil;
import com.amazon.mp3.library.item.AbstractItem;
import com.amazon.mp3.library.item.MusicTrack;
import com.amazon.mp3.library.item.factory.CirrusSourceLibraryItemFactory;
import com.amazon.mp3.library.provider.MediaProvider;
import com.amazon.mp3.library.provider.source.cirrus.CirrusMediaSource;
import com.amazon.mp3.library.provider.source.local.LocalMediaSource;
import com.amazon.mp3.library.service.sync.SyncOperation;
import com.amazon.mp3.library.service.sync.blacklist.database.BlacklistDatabase;
import com.amazon.mp3.library.service.sync.blacklist.entity.LocalUri;
import com.amazon.mp3.library.util.PlaylistUtil;
import com.amazon.mp3.lyrics.item.LyricsStatus;
import com.amazon.mp3.playlist.PlaylistConfigurationStorage;
import com.amazon.mp3.prime.ContentCatalogStatus;
import com.amazon.mp3.prime.ContentOwnershipStatus;
import com.amazon.mp3.util.ContentEncodingDecoder;
import com.amazon.mp3.util.DbUtil;
import com.amazon.mp3.util.Log;
import com.amazon.mp3.util.Profiler;
import com.amazon.music.externalstorage.StorageLocation;
import com.amazon.music.externalstorage.StorageLocationPreference;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes3.dex */
public class LocalTrackSyncOperation extends TrackSyncOperation {
    private Set<String> blacklistSet;
    int mProgress;
    int mTotalCount;
    public static final String[] PERMISSIONS_FOR_LOCAL_SYNC = {"android.permission.READ_EXTERNAL_STORAGE"};
    private static final String TAG = LocalTrackSyncOperation.class.getSimpleName();
    private static final String FOLDER_SEARCH_PATH = "%" + Environment.DIRECTORY_MUSIC + "%";
    private static final String[] LOCAL_STORE_PROJECTION = {"_id", "luid", "title", "album", "duration", "album_id", "album_artist", "album_artist_id", "artist", "artist_id", "album_id", "track_num", "disc_num", "date_created", "local_uri", "size", "album_artist"};
    private static final String[] LOCAL_GENRE_PROJECTION = {"_id", "name"};
    private static final String[] COUNT_PROJECTION = {"COUNT(*)"};

    int syncLocalPlaylists() {
        return 0;
    }

    LocalTrackSyncOperation(Context context, SQLiteDatabase db) {
        super(context, db);
        this.mTotalCount = Integer.MIN_VALUE;
        this.mProgress = 0;
    }

    @Override // com.amazon.mp3.library.service.sync.SyncOperation
    int sync() throws SyncOperation.AccountStatusException {
        if (!GrantPermissionActivity.areAllGranted(getContext(), PERMISSIONS_FOR_LOCAL_SYNC)) {
            Log.info(TAG, "Unable to continue local sync as permission is not granted");
            return 0;
        }
        Profiler.begin("Local track sync operation");
        this.blacklistSet = new HashSet();
        Iterator<LocalUri> it = BlacklistDatabase.getInstance(getContext()).blacklistDao().getAllLocalUris().iterator();
        while (it.hasNext()) {
            this.blacklistSet.add(it.next().getLocalUri());
        }
        int iSyncLocalTracks = syncLocalTracks(false);
        if (iSyncLocalTracks == 0) {
            iSyncLocalTracks = syncLocalPlaylists();
        }
        if (iSyncLocalTracks == 0 && (iSyncLocalTracks = syncDeletedTracks()) == 1) {
            iSyncLocalTracks = syncLocalTracks(true);
        }
        if (this.mProgress > 0) {
            PlaylistUtil.refreshPlaylistArt(MediaProvider.SmartPlaylists.getContentUri("cirrus-local", 2L));
        }
        if (iSyncLocalTracks == 0 && this.mProgress > 0) {
            detectSideloadedContent();
        }
        if (iSyncLocalTracks == 0 && PlaylistConfigurationStorage.get().shouldMigrateAndUnifyDevicePlaylists()) {
            PlaylistUtil.migrateAndUnifyDevicePlaylists(this.mContext);
        }
        Profiler.end();
        return iSyncLocalTracks;
    }

    @Override // com.amazon.mp3.library.service.sync.SyncOperation
    int getProgress() {
        int i = this.mTotalCount;
        if (i == Integer.MIN_VALUE) {
            return -1;
        }
        return (this.mProgress * 100) / i;
    }

    @Override // com.amazon.mp3.library.service.sync.SyncOperation
    int getUpdatedResourceCount() {
        return this.mProgress;
    }

    @Override // com.amazon.mp3.library.service.sync.SyncOperation
    Uri getContentUri() {
        return CirrusMediaSource.NOTIFICATION_URI_LOCAL;
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x004d  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    int syncDeletedTracks() throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 334
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.mp3.library.service.sync.LocalTrackSyncOperation.syncDeletedTracks():int");
    }

    /* JADX WARN: Removed duplicated region for block: B:123:0x03ae A[LOOP:1: B:11:0x007d->B:123:0x03ae, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:131:0x03a4 A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    boolean syncLocalTracksForCursor(final android.database.Cursor r43) {
        /*
            Method dump skipped, instruction units count: 974
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.mp3.library.service.sync.LocalTrackSyncOperation.syncLocalTracksForCursor(android.database.Cursor):boolean");
    }

    @VisibleForTesting
    void updateValuesFromMatchingTrack(ContentValues values, String localUri, int matchHash) {
        MusicTrack track = getTrack(localUri, matchHash);
        if (track == null) {
            Log.verbose(TAG, String.format(Locale.US, "A matching Cloud Track was not found: matchHash %s, localUri: %s, values: %s", Integer.valueOf(matchHash), localUri, values));
        } else {
            updateValues(track, values);
        }
    }

    @VisibleForTesting
    MusicTrack getTrack(final String localUri, final int matchHash) {
        CirrusSourceLibraryItemFactory cirrusSourceLibraryItemFactory = new CirrusSourceLibraryItemFactory(getContext());
        String asinFromTrackLocalUri = TrackSyncUtil.getAsinFromTrackLocalUri(localUri);
        if (asinFromTrackLocalUri != null) {
            AbstractItem item = cirrusSourceLibraryItemFactory.getItem(cirrusSourceLibraryItemFactory.getUriForAsin(asinFromTrackLocalUri));
            if (item == null || !(item instanceof MusicTrack)) {
                return null;
            }
            return (MusicTrack) item;
        }
        return cirrusSourceLibraryItemFactory.getTrack("cirrus", matchHash);
    }

    @VisibleForTesting
    void updateValues(final MusicTrack track, final ContentValues values) {
        if (track.hasLyrics()) {
            values.put("lyrics_state", LyricsStatus.AVAILABLE.toString());
        } else {
            values.put("lyrics_state", LyricsStatus.NO_LYRICS.toString());
        }
        if (track.isAllOwned()) {
            values.put("ownership_status", Integer.valueOf(track.getOwnershipStatus().getValue()));
            values.put("prime_status", Integer.valueOf(track.getContentCatalogStatus().getValue()));
        } else {
            values.put("ownership_status", Integer.valueOf(ContentOwnershipStatus.OWNED.getValue()));
            values.put("prime_status", Integer.valueOf(ContentCatalogStatus.NON_CATALOG.getValue()));
        }
        values.put("asin", track.getAsin());
        values.put("album_asin", track.getAlbumAsin());
        values.put("artist_asin", track.getArtistAsin());
        values.put("is_explicit", Boolean.valueOf(track.isExplicit()));
        values.put("content_encoding", ContentEncodingDecoder.contentEncodingToString(track.getContentEncodings()));
        values.put("download_state", (Integer) 0);
        values.put("catalog_status_tiers", Integer.valueOf(track.getCatalogStatusTiers().serialize()));
    }

    private Map<Long, String> createTrackIdToGenreMap() throws Throwable {
        HashMap map = new HashMap();
        HashMap map2 = new HashMap();
        ContentResolver contentResolver = getContext().getContentResolver();
        Cursor cursor = null;
        try {
            Cursor cursorQuery = contentResolver.query(LocalMediaSource.BASE_GENRE_URI, LOCAL_GENRE_PROJECTION, null, null, null);
            try {
                if (cursorQuery == null) {
                    Log.error(TAG, "The cursor is null in createTrackIdToGenreMap");
                    DbUtil.closeCursor(cursorQuery);
                    return map;
                }
                int columnIndex = cursorQuery.getColumnIndex("_id");
                int columnIndex2 = cursorQuery.getColumnIndex("name");
                cursorQuery.moveToFirst();
                while (!cursorQuery.isAfterLast()) {
                    map2.put(cursorQuery.getString(columnIndex), cursorQuery.getString(columnIndex2));
                    cursorQuery.moveToNext();
                }
                DbUtil.closeCursor(cursorQuery);
                String[] strArr = {"_id"};
                for (Map.Entry entry : map2.entrySet()) {
                    String str = (String) entry.getKey();
                    if (str != null) {
                        try {
                            Cursor cursorQuery2 = contentResolver.query(MediaProvider.Genres.getLocalContentUri(str), strArr, null, null, null);
                            if (cursorQuery2 == null) {
                                Log.error(TAG, "The genreCursor is null in createTrackIdToGenreMap");
                                DbUtil.closeCursor(cursorQuery2);
                                return map;
                            }
                            try {
                                int columnIndex3 = cursorQuery2.getColumnIndex("_id");
                                while (cursorQuery2.moveToNext()) {
                                    long j = cursorQuery2.getLong(columnIndex3);
                                    if (!map.containsKey(Long.valueOf(j))) {
                                        map.put(Long.valueOf(j), (String) entry.getValue());
                                    }
                                }
                                DbUtil.closeCursor(cursorQuery2);
                            } catch (Throwable th) {
                                th = th;
                                cursor = cursorQuery2;
                            }
                            th = th;
                            cursor = cursorQuery2;
                        } catch (Throwable th2) {
                            th = th2;
                        }
                        DbUtil.closeCursor(cursor);
                        throw th;
                    }
                }
                return map;
            } catch (Throwable th3) {
                th = th3;
                cursor = cursorQuery;
                DbUtil.closeCursor(cursor);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
        }
    }

    private Cursor getLocalTrackSyncCursor(final long syncTime) {
        if (StorageLocation.SDCARD.equals(new StorageLocationPreference(getContext()).getPreferredStorageLocation())) {
            return getContext().getContentResolver().query(LocalMediaSource.LOCAL_TRACKS_URI, LOCAL_STORE_PROJECTION, "date_modified >= " + TimeUnit.MILLISECONDS.toSeconds(syncTime) + " AND _data IS NOT NULL", null, "date_modified");
        }
        return getContext().getContentResolver().query(LocalMediaSource.LOCAL_TRACKS_URI, LOCAL_STORE_PROJECTION, "_data LIKE ?  AND date_modified >= " + TimeUnit.MILLISECONDS.toSeconds(syncTime) + " AND _data IS NOT NULL", new String[]{FOLDER_SEARCH_PATH}, "date_modified");
    }

    int syncLocalTracks(final boolean syncAll) {
        long jCurrentTimeMillis = System.currentTimeMillis();
        Cursor localTrackSyncCursor = getLocalTrackSyncCursor(getSyncPivotTime(syncAll, jCurrentTimeMillis));
        if (localTrackSyncCursor == null) {
            return 0;
        }
        try {
            boolean zSyncLocalTracksForCursor = true;
            if (localTrackSyncCursor.moveToFirst()) {
                SettingsUtil.setHasTracksDevice(getContext(), true);
                zSyncLocalTracksForCursor = syncLocalTracksForCursor(localTrackSyncCursor);
            } else if (syncAll) {
                SettingsUtil.setHasTracksDevice(getContext(), false);
            }
            if (zSyncLocalTracksForCursor) {
                SettingsUtil.setLastLocalUpdatedTime(getContext(), jCurrentTimeMillis + 1);
            }
            return 0;
        } finally {
            DbUtil.closeCursor(localTrackSyncCursor);
        }
    }

    private long getSyncPivotTime(boolean syncAll, long currentTime) {
        long lastLocalUpdatedTime = SettingsUtil.getLastLocalUpdatedTime(getContext());
        if (!syncAll && currentTime >= lastLocalUpdatedTime) {
            return lastLocalUpdatedTime;
        }
        SettingsUtil.setLastLocalUpdatedTime(getContext(), -1L);
        return -1L;
    }

    private void detectSideloadedContent() {
        SQLiteDatabase database = getDatabase();
        String[] strArr = COUNT_PROJECTION;
        DbUtil.intFromCursor(database.query("Track", strArr, "luid LIKE 'local%'", null, null, null, null));
        DbUtil.intFromCursor(database.query("Track", strArr, "download_state=0", null, null, null, null));
    }
}
