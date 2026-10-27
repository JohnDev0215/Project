package com.amazon.mp3.prime;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.text.TextUtils;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.amazon.mp3.library.item.MusicTrack;
import com.amazon.mp3.library.provider.source.cirrus.CirrusDatabase;
import com.amazon.mp3.lyrics.item.LyricsStatus;
import com.amazon.mp3.playlist.CatalogPlaylistTrack;
import com.amazon.mp3.prime.PrimePlaylistTracksTable;
import com.amazon.mp3.prime.PrimeTracksCache;
import com.amazon.mp3.prime.browse.metadata.PrimeTrack;
import com.amazon.mp3.recentlyplayed.RecentlyPlayedDatabase;
import com.amazon.mp3.util.ContentEncodingDecoder;
import com.amazon.mp3.util.DbUtil;
import com.amazon.mp3.util.IdGenerator;
import com.amazon.mp3.util.Log;
import com.amazon.mp3.util.MatchHashUtil;
import com.amazon.mp3.util.StringUtil;
import com.amazon.music.find.utils.PageUriUtils;
import com.amazon.music.platform.data.eligibility.PlaymodeEligibility;
import com.amazon.music.skyfire.models.TetheredMessageKey;
import com.google.android.gms.cast.HlsSegmentFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* JADX INFO: loaded from: classes4.dex */
public class CTAPrimeCache implements PrimeCache {
    private static final String TAG = "CTAPrimeCache";
    private int mCacheDuration;
    private final Context mContext;
    private final AlbumMetadataChangeListener mListener;
    private final SQLiteDatabase mReadOnlyDb;
    private final SQLiteDatabase mWritableDb;

    public interface AlbumMetadataChangeListener {
        void onAlbumAsinChanged(String albumAsin);

        void onAlbumIdChanged(long albumId);
    }

    public CTAPrimeCache(Context context) {
        this(context, 86400000);
    }

    public CTAPrimeCache(Context context, int cacheDuration) {
        this(context, CirrusDatabase.getReadOnlyDatabase(context), CirrusDatabase.getWritableDatabase(context), cacheDuration, null);
    }

    public CTAPrimeCache(Context context, int cacheDuration, AlbumMetadataChangeListener listener) {
        this(context, CirrusDatabase.getReadOnlyDatabase(context), CirrusDatabase.getWritableDatabase(context), cacheDuration, listener);
    }

    public CTAPrimeCache(Context context, SQLiteDatabase readOnlyDb, SQLiteDatabase writableDb, int cacheDuration, AlbumMetadataChangeListener listener) {
        this.mContext = context;
        this.mReadOnlyDb = readOnlyDb;
        this.mWritableDb = writableDb;
        this.mCacheDuration = cacheDuration;
        this.mListener = listener;
    }

    public boolean containsAsin(String asin) {
        Cursor cursorQuery = this.mReadOnlyDb.query("CacheTimes", new String[]{TetheredMessageKey.timestamp}, "asin = ? AND timestamp > ?", new String[]{asin, String.valueOf(System.currentTimeMillis() - ((long) this.mCacheDuration))}, null, null, null);
        if (cursorQuery == null) {
            return false;
        }
        boolean zMoveToFirst = cursorQuery.moveToFirst();
        cursorQuery.close();
        return zMoveToFirst;
    }

    public void insertAsin(String str, boolean z, String str2) {
        this.mWritableDb.execSQL("INSERT OR REPLACE INTO CacheTimes (asin, timestamp, has_prime_content, artwork_url, ui_state) VALUES (?, " + String.valueOf(System.currentTimeMillis()) + ", " + String.valueOf(z ? 1 : 0) + ", ?, (SELECT ui_state FROM CacheTimes WHERE asin = ?))", new Object[]{str, str2, str});
    }

    public int getNonLibPrimeTrackCount(String asinColumn, String asin) {
        return (int) DatabaseUtils.queryNumEntries(this.mReadOnlyDb, "Track", asinColumn + "=? AND prime_status>? AND ownership_status>? AND " + PageUriUtils.SOURCE_QUERY_PARAM + "=0", new String[]{asin, String.valueOf(ContentCatalogStatus.HAWKFIRE_OR_NOT_BOUNDARY), String.valueOf(300)});
    }

    public long clearExpiredTracks(String commonColumn) {
        long jCurrentTimeMillis = System.currentTimeMillis();
        DbUtil.WhereClause albumAsinsToRetainClause = getAlbumAsinsToRetainClause();
        String cacheTimeSelection = getCacheTimeSelection(jCurrentTimeMillis, albumAsinsToRetainClause);
        String[] cacheTimeSelectionArgs = getCacheTimeSelectionArgs(albumAsinsToRetainClause);
        String tracksSelection = getTracksSelection(cacheTimeSelection, commonColumn);
        String[] tracksSelectionArgs = getTracksSelectionArgs(albumAsinsToRetainClause);
        this.mWritableDb.beginTransaction();
        try {
            this.mWritableDb.delete("Track", tracksSelection, tracksSelectionArgs);
            this.mWritableDb.delete("CacheTimes", cacheTimeSelection, cacheTimeSelectionArgs);
            this.mWritableDb.setTransactionSuccessful();
            this.mWritableDb.endTransaction();
            Cursor cursorQuery = this.mReadOnlyDb.query("CacheTimes", new String[]{TetheredMessageKey.timestamp}, null, null, null, null, "timestamp ASC", "1");
            long j = jCurrentTimeMillis + ((long) this.mCacheDuration);
            try {
                if (cursorQuery.getCount() > 0) {
                    cursorQuery.moveToFirst();
                    j = Long.parseLong(cursorQuery.getString(0));
                }
                return j;
            } finally {
                cursorQuery.close();
            }
        } catch (Throwable th) {
            this.mWritableDb.endTransaction();
            throw th;
        }
    }

    private DbUtil.WhereClause getAlbumAsinsToRetainClause() {
        List<String> alexaRecentlyPlayedAlbumAsins = RecentlyPlayedDatabase.getInstance(this.mContext).getAlexaRecentlyPlayedAlbumAsins();
        return new DbUtil.WhereClause("asin", alexaRecentlyPlayedAlbumAsins, alexaRecentlyPlayedAlbumAsins.size(), true);
    }

    private String getCacheTimeSelection(final long currentTime, final DbUtil.WhereClause albumAsinsToRetainClause) {
        return DbUtil.applyBinaryOperator("timestamp <= " + String.valueOf(currentTime - ((long) this.mCacheDuration)) + " or " + TetheredMessageKey.timestamp + " > " + currentTime, "AND", albumAsinsToRetainClause.getClause());
    }

    private String[] getCacheTimeSelectionArgs(final DbUtil.WhereClause albumAsinsToRetainClause) {
        return albumAsinsToRetainClause.getArgs();
    }

    private String getTracksSelection(final String cacheTimeSelection, final String commonColumn) {
        return "prime_status > ? AND ownership_status = ? AND playlist_track_status = ? AND " + commonColumn + " in (" + SQLiteQueryBuilder.buildQueryString(false, "CacheTimes", new String[]{"asin"}, cacheTimeSelection, null, null, null, null) + ")";
    }

    private String[] getTracksSelectionArgs(final DbUtil.WhereClause albumAsinsToRetainClause) {
        return DbUtil.mergeColumnArrays(new String[]{String.valueOf(ContentCatalogStatus.HAWKFIRE_OR_NOT_BOUNDARY), String.valueOf(ContentOwnershipStatus.NOT_IN_LIBRARY.getValue()), String.valueOf(MusicTrack.PlaylistTrackStatus.NOT_CATALOG_TRACK.getValue())}, albumAsinsToRetainClause.getArgs());
    }

    public void insertNonPrimeTracks(String albumAsin, List<PrimeTrack> tracks) {
        synchronized (CTAPrimeCache.class) {
            StringBuilder sb = new StringBuilder(tracks.size());
            if (!tracks.isEmpty()) {
                Iterator<PrimeTrack> it = tracks.iterator();
                while (it.hasNext()) {
                    String asin = it.next().getAsin();
                    if (!TextUtils.isEmpty(asin)) {
                        sb.append(asin);
                        sb.append(',');
                    }
                }
                int length = sb.length();
                if (length > 0) {
                    sb.deleteCharAt(length - 1);
                }
            }
            ContentValues contentValues = new ContentValues();
            contentValues.put("asin", albumAsin);
            contentValues.put("has_prime_content", "0");
            contentValues.put(TetheredMessageKey.timestamp, String.valueOf(System.currentTimeMillis()));
            contentValues.put("track_asins", sb.toString());
            contentValues.put("ui_state", Integer.valueOf(PrimeTracksCache.PrimeUIState.SHOW.getValue()));
            this.mWritableDb.insertWithOnConflict("CacheTimes", null, contentValues, 5);
        }
    }

    public ContentValues updateContentValuesForSubstitutedTrack(PrimeTrack track, ContentValues values) {
        ContentValues trackContentValues = getTrackContentValues(track, values);
        trackContentValues.remove("_id");
        trackContentValues.remove("luid");
        trackContentValues.remove("order_id");
        trackContentValues.remove("date_purchased");
        trackContentValues.remove("purchased");
        trackContentValues.remove("download_state");
        trackContentValues.remove("match_hash");
        trackContentValues.remove("remote_uri");
        trackContentValues.remove("order_id");
        trackContentValues.remove("uploaded");
        trackContentValues.remove("size");
        trackContentValues.remove("prime_status");
        trackContentValues.remove("ownership_status");
        trackContentValues.remove("luid_hash");
        long jGenerateAlbumId = IdGenerator.generateAlbumId(track.getAlbumAsin(), track.getAlbum());
        long jGenerateArtistId = IdGenerator.generateArtistId(track.getArtist());
        long jGenerateArtistId2 = IdGenerator.generateArtistId(track.getAlbumArtist());
        trackContentValues.put("album_id", Long.valueOf(jGenerateAlbumId));
        trackContentValues.put("album_art_id", Long.valueOf(jGenerateAlbumId));
        trackContentValues.put("album_artist_id", Long.valueOf(jGenerateArtistId2));
        trackContentValues.put("artist_id", Long.valueOf(jGenerateArtistId));
        trackContentValues.put("match_hash", Integer.valueOf(createTrackMatchHash(values)));
        return trackContentValues;
    }

    public int insertPrimeTracks(String albumAsin, String albumName, @Nullable String albumArtistName, List<PrimeTrack> tracks) {
        return insertPrimeTracks(albumAsin, albumName, albumArtistName, tracks, Collections.emptyMap());
    }

    /* JADX WARN: Removed duplicated region for block: B:104:0x0200 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:39:0x016a  */
    /* JADX WARN: Removed duplicated region for block: B:46:0x0189  */
    /* JADX WARN: Removed duplicated region for block: B:67:0x01f6 A[Catch: all -> 0x031b, TryCatch #1 {, blocks: (B:4:0x000d, B:6:0x0014, B:7:0x002a, B:9:0x002c, B:12:0x0044, B:13:0x006d, B:14:0x009c, B:16:0x00a2, B:18:0x00ae, B:19:0x00b2, B:20:0x00ba, B:21:0x00ee, B:23:0x00f4, B:25:0x0102, B:27:0x011d, B:30:0x013e, B:36:0x0155, B:37:0x0160, B:41:0x016d, B:43:0x0173, B:45:0x0183, B:48:0x018c, B:50:0x019a, B:52:0x01ac, B:53:0x01b1, B:56:0x01c8, B:58:0x01ce, B:59:0x01d4, B:62:0x01e5, B:64:0x01eb, B:65:0x01f0, B:67:0x01f6, B:34:0x014b, B:70:0x020e, B:72:0x021b, B:74:0x0236, B:75:0x0295, B:77:0x029c, B:82:0x02df, B:84:0x02e6, B:85:0x02eb, B:86:0x02ec, B:90:0x02f8, B:91:0x0319, B:73:0x021f, B:78:0x02a6, B:80:0x02ba, B:81:0x02d7), top: B:98:0x000d, inners: #0 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public int insertPrimeTracks(java.lang.String r26, java.lang.String r27, @androidx.annotation.Nullable java.lang.String r28, java.util.List<com.amazon.mp3.prime.browse.metadata.PrimeTrack> r29, java.util.Map<java.lang.String, java.lang.String> r30) {
        /*
            Method dump skipped, instruction units count: 798
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.mp3.prime.CTAPrimeCache.insertPrimeTracks(java.lang.String, java.lang.String, java.lang.String, java.util.List, java.util.Map):int");
    }

    private void updateTracks(Map<PrimeTrack, List<String>> tracksAndTheirColumnsToUpdate, Map<String, String> substitutedAsins) {
        AlbumMetadataChangeListener albumMetadataChangeListener;
        for (Map.Entry<PrimeTrack, List<String>> entry : tracksAndTheirColumnsToUpdate.entrySet()) {
            PrimeTrack key = entry.getKey();
            List<String> value = entry.getValue();
            ArrayList arrayList = new ArrayList();
            arrayList.add(0, key.getAsin());
            DbUtil.WhereClause whereClause = new DbUtil.WhereClause("asin", arrayList);
            try {
                DbUtil.beginTransaction(this.mWritableDb);
                ContentValues contentValuesForTrack = getContentValuesForTrack(key, value);
                if (substitutedAsins.containsKey(key.getAsin())) {
                    contentValuesForTrack = updateContentValuesForSubstitutedTrack(key, contentValuesForTrack);
                }
                if (this.mWritableDb.update("Track", contentValuesForTrack, whereClause.getClause(), whereClause.getArgs()) == -1) {
                    Set<String> setKeySet = contentValuesForTrack.keySet();
                    StringBuilder sb = new StringBuilder();
                    sb.append("Failed to update ");
                    for (String str : setKeySet) {
                        sb.append(str + ": " + contentValuesForTrack.get(str) + ", ");
                    }
                    sb.append("for trackAsin: " + key.getAsin());
                    Log.error(TAG, sb.toString());
                } else {
                    this.mWritableDb.setTransactionSuccessful();
                    if (contentValuesForTrack.containsKey("album_id") && (albumMetadataChangeListener = this.mListener) != null) {
                        albumMetadataChangeListener.onAlbumIdChanged(contentValuesForTrack.getAsLong("album_id").longValue());
                    }
                }
            } finally {
                this.mWritableDb.endTransaction();
            }
        }
    }

    private ContentValues getContentValuesForTrack(PrimeTrack track, List<String> columnsToUpdate) {
        ContentValues contentValues = new ContentValues();
        String album = track.getAlbum();
        for (String str : columnsToUpdate) {
            str.hashCode();
            switch (str) {
                case "popularity":
                    contentValues.put(str, track.getPopularity());
                    break;
                case "album_art_id":
                case "album_id":
                    contentValues.put(str, Long.valueOf(IdGenerator.generateAlbumId(track.getAlbumAsin(), album)));
                    break;
                case "playmode_eligibility":
                    contentValues.put(str, PlaymodeEligibility.INSTANCE.getPlaymodeEligibilityString(track.getPlaymodeEligibility()));
                    break;
                case "sort_album":
                    contentValues.put(str, StringUtil.normalizeAlbumName(album));
                    break;
                case "album":
                    contentValues.put(str, album);
                    break;
                case "disc_num":
                    contentValues.put(str, Integer.valueOf(track.getDiscNum()));
                    break;
                case "track_num":
                    contentValues.put(str, Integer.valueOf(track.getTrackNum()));
                    break;
                case "match_hash":
                    contentValues.put(str, Integer.valueOf(createTrackMatchHash(track)));
                    break;
                case "content_encoding":
                    contentValues.put(str, ContentEncodingDecoder.contentEncodingToString(track.getContentEncoding()));
                    break;
                case "album_playmode_eligibility":
                    contentValues.put(str, PlaymodeEligibility.INSTANCE.getPlaymodeEligibilityString(track.getAlbumPlaymodeEligibility()));
                    break;
                default:
                    Log.error(TAG, "Failed to update column " + str + " as the corresponding the corresponding accessor is not specified");
                    break;
            }
        }
        return contentValues;
    }

    private ContentValues getTrackContentValues(PrimeTrack track, ContentValues defaultValues) {
        if (defaultValues == null) {
            defaultValues = new ContentValues();
        }
        defaultValues.put("asin", track.getAsin());
        defaultValues.put("artist_asin", track.getArtistAsin());
        String artist = track.getArtist();
        if (artist == null) {
            artist = track.getAlbumArtist();
        }
        if (artist == null) {
            Log.warning(TAG, "The artist and albumArtist for the following track are null. ASIN: " + track.getAsin());
        }
        defaultValues.put("artist_id", Long.valueOf(IdGenerator.generateArtistId(artist)));
        defaultValues.put("artist", artist);
        defaultValues.put("sort_artist", StringUtil.normalizeArtistName(artist));
        defaultValues.put("title", track.getTitle());
        defaultValues.put("track_num", Integer.valueOf(track.getTrackNum()));
        defaultValues.put("disc_num", Integer.valueOf(track.getDiscNum()));
        defaultValues.put("size", Integer.valueOf(track.getSize()));
        defaultValues.put("duration", track.getDuration());
        defaultValues.put("sort_title", StringUtil.normalizeTrackName(track.getTitle()));
        defaultValues.put("prime_status", Integer.valueOf(track.getCatalogStatus().getValue()));
        defaultValues.put("is_explicit", Boolean.valueOf(track.getIsExplicit()));
        defaultValues.put("popularity", track.getPopularity());
        defaultValues.put("content_encoding", ContentEncodingDecoder.contentEncodingToString(track.getContentEncoding()));
        String album = track.getAlbum();
        String albumAsin = track.getAlbumAsin();
        if (!TextUtils.isEmpty(track.getAlbumArtist())) {
            artist = track.getAlbumArtist();
        }
        putIfNotExists(defaultValues, "album_artist", artist);
        putIfNotExists(defaultValues, "sort_album_artist", StringUtil.normalizeArtistName(artist));
        putIfNotExists(defaultValues, "album_artist_id", IdGenerator.generateArtistId(artist));
        putIfNotExists(defaultValues, "album", album);
        putIfNotExists(defaultValues, "sort_album", StringUtil.normalizeAlbumName(album));
        putIfNotExists(defaultValues, "album_asin", track.getAlbumAsin());
        putIfNotExists(defaultValues, "album_id", IdGenerator.generateAlbumId(albumAsin, album));
        LyricsStatus lyricsStatus = track.getLyricsStatus();
        LyricsStatus lyricsStatus2 = LyricsStatus.UNKNOWN;
        if (lyricsStatus == lyricsStatus2) {
            putIfNotExists(defaultValues, "lyrics_state", lyricsStatus2.toString());
        } else {
            defaultValues.put("lyrics_state", track.getLyricsStatus().toString());
        }
        defaultValues.put("extension", HlsSegmentFormat.MP3);
        defaultValues.put("asset_qualities", CatalogPlaylistTrack.getAssetQualitiesString(track.getAssetQualities()));
        PrimePlaylistTracksTable.AssetType assetType = track.getAssetType();
        if (assetType != null) {
            defaultValues.put("asset_type", assetType.toString());
        } else {
            defaultValues.put("asset_type", PrimePlaylistTracksTable.AssetType.AUDIO.toString());
        }
        PlaymodeEligibility.Companion companion = PlaymodeEligibility.INSTANCE;
        defaultValues.put("playmode_eligibility", companion.getPlaymodeEligibilityString(track.getPlaymodeEligibility()));
        defaultValues.put("album_playmode_eligibility", companion.getPlaymodeEligibilityString(track.getAlbumPlaymodeEligibility()));
        defaultValues.put("catalog_status_tiers", Integer.valueOf(track.getCatalogStatusTiers().serialize()));
        defaultValues.put("match_hash", Integer.valueOf(createTrackMatchHash(defaultValues)));
        return defaultValues;
    }

    private static void putIfNotExists(ContentValues values, String key, String value) {
        if (values.containsKey(key)) {
            return;
        }
        values.put(key, value);
    }

    private static void putIfNotExists(ContentValues values, String key, long value) {
        if (values.containsKey(key)) {
            return;
        }
        values.put(key, Long.valueOf(value));
    }

    private int createTrackMatchHash(final PrimeTrack track) {
        String title = track.getTitle();
        String artist = track.getArtist();
        return MatchHashUtil.generateMatchHashForNotNormalizedMetadata(this.mContext, title, track.getAlbum(), artist, track.getAlbumArtist(), track.getTrackNum(), track.getDiscNum());
    }

    @VisibleForTesting
    int createTrackMatchHash(ContentValues values) {
        String asString = values.getAsString("title");
        String asString2 = values.getAsString("artist");
        return MatchHashUtil.generateMatchHashForNotNormalizedMetadata(this.mContext, asString, values.getAsString("album"), asString2, values.getAsString("album_artist"), values.getAsInteger("track_num").intValue(), values.getAsInteger("disc_num").intValue());
    }

    public PrimeTracksCache.PrimeUIState getPrimeUIState(String asin) {
        Cursor cursorQuery = this.mReadOnlyDb.query("CacheTimes", new String[]{"ui_state"}, "asin = ?", new String[]{asin}, null, null, null);
        PrimeTracksCache.PrimeUIState primeUIStateFromInt = PrimeTracksCache.PrimeUIState.HIDE;
        if (cursorQuery.moveToFirst()) {
            primeUIStateFromInt = PrimeTracksCache.PrimeUIState.fromInt(cursorQuery.getInt(cursorQuery.getColumnIndexOrThrow("ui_state")));
        }
        cursorQuery.close();
        return primeUIStateFromInt;
    }

    public boolean hasPrimeContent(String asin) {
        boolean z = false;
        Cursor cursorQuery = this.mReadOnlyDb.query("CacheTimes", new String[]{"has_prime_content"}, "asin = ?", new String[]{asin}, null, null, null);
        if (cursorQuery.moveToFirst()) {
            z = cursorQuery.getInt(cursorQuery.getColumnIndexOrThrow("has_prime_content")) == 1;
        }
        cursorQuery.close();
        return z;
    }

    public void setPrimeUIState(String asin, PrimeTracksCache.PrimeUIState uiState) {
        this.mWritableDb.execSQL("INSERT OR REPLACE INTO CacheTimes (asin, ui_state, timestamp, has_prime_content, artwork_url) VALUES (?, " + uiState.getValue() + ", (SELECT " + TetheredMessageKey.timestamp + " FROM CacheTimes WHERE asin = ?), (SELECT has_prime_content FROM CacheTimes WHERE asin = ?), (SELECT artwork_url FROM CacheTimes WHERE asin = ?))", new Object[]{asin, asin, asin, asin});
    }

    public void updateTrackStatus(List<String> asins, ContentCatalogStatus contentCatalogStatus, ContentOwnershipStatus contentOwnershipStatus) {
        ContentValues contentValues = new ContentValues();
        if (contentCatalogStatus != null) {
            contentValues.put("prime_status", Integer.valueOf(contentCatalogStatus.getValue()));
        }
        if (contentOwnershipStatus != null) {
            contentValues.put("ownership_status", Integer.valueOf(contentOwnershipStatus.getValue()));
        }
        DbUtil.WhereClause whereClause = new DbUtil.WhereClause("asin", new ArrayList(asins), asins.size());
        int iUpdate = this.mWritableDb.update("Track", contentValues, DbUtil.applyBinaryOperator("ownership_status>300", "AND", whereClause.getClause()), whereClause.getArgs());
        String str = TAG;
        Object[] objArr = new Object[3];
        objArr[0] = Integer.valueOf(iUpdate);
        objArr[1] = contentCatalogStatus == null ? "no change" : contentCatalogStatus.toString();
        objArr[2] = contentOwnershipStatus != null ? contentOwnershipStatus.toString() : "no change";
        Log.debug(str, "updated %d rows with prime status: %s, ownnership status: %s", objArr);
    }

    public void removeNonPrimeAndNotInLibraryTracks(String asinColumn, List<String> nonPrimeTrackAsins) {
        String[] strArr = {String.valueOf(300), String.valueOf(MusicTrack.PlaylistTrackStatus.NOT_CATALOG_TRACK.getValue())};
        DbUtil.WhereClause whereClause = new DbUtil.WhereClause(asinColumn, new ArrayList(nonPrimeTrackAsins), nonPrimeTrackAsins.size());
        Log.debug(TAG, "deleted %d rows with non prime tracks: %s", Integer.valueOf(this.mWritableDb.delete("Track", DbUtil.applyBinaryOperator("ownership_status>? AND playlist_track_status=?", "AND", whereClause.getClause()), DbUtil.mergeColumnArrays(strArr, whereClause.getArgs()))), nonPrimeTrackAsins.toArray());
    }

    @Override // com.amazon.mp3.prime.PrimeCache
    public int getCacheItemMaxLifetime() {
        return this.mCacheDuration;
    }

    /*  JADX ERROR: JadxRuntimeException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxRuntimeException: Can't find top splitter block for handler:B:21:0x0059
        	at jadx.core.utils.BlockUtils.getTopSplitterForHandler(BlockUtils.java:1182)
        	at jadx.core.dex.visitors.regions.maker.ExcHandlersRegionMaker.collectHandlerRegions(ExcHandlersRegionMaker.java:53)
        	at jadx.core.dex.visitors.regions.maker.ExcHandlersRegionMaker.process(ExcHandlersRegionMaker.java:38)
        	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:27)
        */
    @androidx.annotation.Nullable
    public java.lang.String getArtworkUrlForAsin(@androidx.annotation.Nullable java.lang.String r12) {
        /*
            r11 = this;
            boolean r0 = android.text.TextUtils.isEmpty(r12)
            r1 = 0
            if (r0 == 0) goto L1e
            java.lang.String r0 = com.amazon.mp3.prime.CTAPrimeCache.TAG
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "getArtworkUrlForAsin failed since asin = "
            r2.append(r3)
            r2.append(r12)
            java.lang.String r12 = r2.toString()
            com.amazon.mp3.util.Log.error(r0, r12)
            return r1
        L1e:
            java.lang.String r0 = "artwork_url"
            java.lang.String[] r4 = new java.lang.String[]{r0}
            r0 = 1
            java.lang.String[] r6 = new java.lang.String[r0]
            r0 = 0
            r6[r0] = r12
            monitor-enter(r11)     // Catch: java.lang.Throwable -> L5c
            android.database.sqlite.SQLiteDatabase r2 = r11.mReadOnlyDb     // Catch: java.lang.Throwable -> L59
            java.lang.String r3 = "CacheTimes"
            java.lang.String r5 = "asin = ?"
            r7 = 0
            r8 = 0
            r9 = 0
            java.lang.String r10 = "1"
            android.database.Cursor r12 = r2.query(r3, r4, r5, r6, r7, r8, r9, r10)     // Catch: java.lang.Throwable -> L59
            if (r12 == 0) goto L51
            boolean r0 = r12.moveToFirst()     // Catch: java.lang.Throwable -> L56
            if (r0 == 0) goto L51
            java.lang.String r0 = "artwork_url"
            int r0 = r12.getColumnIndexOrThrow(r0)     // Catch: java.lang.Throwable -> L56
            java.lang.String r0 = r12.getString(r0)     // Catch: java.lang.Throwable -> L56
            monitor-exit(r11)     // Catch: java.lang.Throwable -> L56
            com.amazon.mp3.util.DbUtil.closeCursor(r12)
            return r0
        L51:
            monitor-exit(r11)     // Catch: java.lang.Throwable -> L56
            com.amazon.mp3.util.DbUtil.closeCursor(r12)
            return r1
        L56:
            r0 = move-exception
            r1 = r12
            goto L5a
        L59:
            r0 = move-exception
        L5a:
            monitor-exit(r11)     // Catch: java.lang.Throwable -> L59
            throw r0     // Catch: java.lang.Throwable -> L5c
        L5c:
            r12 = move-exception
            com.amazon.mp3.util.DbUtil.closeCursor(r1)
            throw r12
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.mp3.prime.CTAPrimeCache.getArtworkUrlForAsin(java.lang.String):java.lang.String");
    }

    public String getArtworkUrlForAlbumId(String albumId) throws Throwable {
        Cursor cursorQuery;
        Throwable th;
        try {
            cursorQuery = this.mReadOnlyDb.query("Track", new String[]{"album_asin"}, "album_id=? AND album_asin IS NOT NULL", new String[]{albumId}, null, null, null, "1");
            if (cursorQuery != null) {
                try {
                    if (cursorQuery.moveToFirst()) {
                        String artworkUrlForAsin = getArtworkUrlForAsin(cursorQuery.getString(cursorQuery.getColumnIndexOrThrow("album_asin")));
                        DbUtil.closeCursor(cursorQuery);
                        return artworkUrlForAsin;
                    }
                } catch (Throwable th2) {
                    th = th2;
                    DbUtil.closeCursor(cursorQuery);
                    throw th;
                }
            }
            DbUtil.closeCursor(cursorQuery);
            return null;
        } catch (Throwable th3) {
            cursorQuery = null;
            th = th3;
        }
    }

    public void clearAll() {
        String[] strArr = {String.valueOf(ContentCatalogStatus.HAWKFIRE_OR_NOT_BOUNDARY), String.valueOf(ContentOwnershipStatus.NOT_IN_LIBRARY.getValue()), String.valueOf(MusicTrack.PlaylistTrackStatus.NOT_CATALOG_TRACK.getValue())};
        this.mWritableDb.beginTransaction();
        try {
            this.mWritableDb.delete("Track", "prime_status>? and ownership_status=? and playlist_track_status=?", strArr);
            PrimeCacheTimesTable.resetPrimeCacheTimeTable(this.mWritableDb);
            this.mWritableDb.setTransactionSuccessful();
        } finally {
            this.mWritableDb.endTransaction();
        }
    }
}
