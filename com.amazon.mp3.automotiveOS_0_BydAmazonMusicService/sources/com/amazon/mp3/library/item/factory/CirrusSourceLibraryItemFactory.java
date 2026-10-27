package com.amazon.mp3.library.item.factory;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.text.TextUtils;
import androidx.annotation.Nullable;
import com.amazon.identity.auth.device.utils.AccountConstants;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.catalog.fragment.datasource.DBQueryUtil;
import com.amazon.mp3.configuration.IntlConfiguration;
import com.amazon.mp3.library.item.AbstractItem;
import com.amazon.mp3.library.item.Album;
import com.amazon.mp3.library.item.Artist;
import com.amazon.mp3.library.item.DeluxeContentUtil;
import com.amazon.mp3.library.item.Genre;
import com.amazon.mp3.library.item.ItemWrapper$ItemType;
import com.amazon.mp3.library.item.MarketplaceDependentItem;
import com.amazon.mp3.library.item.MusicTrack;
import com.amazon.mp3.library.item.Playlist;
import com.amazon.mp3.library.item.TrackType;
import com.amazon.mp3.library.item.factory.LibraryItemFactory;
import com.amazon.mp3.library.provider.MediaProvider;
import com.amazon.mp3.library.provider.source.cirrus.CirrusDatabase;
import com.amazon.mp3.library.provider.source.cirrus.CirrusMediaSource;
import com.amazon.mp3.library.util.PlaylistUtil;
import com.amazon.mp3.playlist.CatalogPlaylist;
import com.amazon.mp3.playlist.CatalogPlaylistTrack;
import com.amazon.mp3.playlist.db.PrimePlaylistDatabaseManager;
import com.amazon.mp3.prime.PrimePlaylistsTable;
import com.amazon.mp3.prime.browse.metadata.CatalogStatusTiers;
import com.amazon.mp3.util.ContentEncodingDecoder;
import com.amazon.mp3.util.DbUtil;
import com.amazon.mp3.util.EligibilityConverterUtil;
import com.amazon.mp3.util.IdGenerator;
import com.amazon.mp3.util.Log;
import com.amazon.mp3.util.Profiler;
import com.amazon.music.find.data.database.SqliteLibrarySearchDao;
import com.amazon.music.find.utils.PageUriUtils;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.apache.commons.lang3.Validate;

/* JADX INFO: loaded from: classes3.dex */
public class CirrusSourceLibraryItemFactory implements LibraryItemFactory {
    private static final String TAG = "CirrusSourceLibraryItemFactory";
    private final Context context;
    private final ContentResolver mContentResolver;

    @Deprecated
    public CirrusSourceLibraryItemFactory() {
        this(AmazonApplication.getContext());
    }

    public CirrusSourceLibraryItemFactory(Context context) {
        this.context = context;
        this.mContentResolver = context.getContentResolver();
    }

    @Override // com.amazon.mp3.library.item.factory.LibraryItemFactory
    public AbstractItem getItem(Uri contentUri) {
        try {
            Profiler.begin("Get item from content uri");
            AbstractItem genre = null;
            if (MediaProvider.Tracks.isTrack(contentUri)) {
                genre = getTrackIncludingPrimePlaylistTrack(contentUri);
            } else if (MediaProvider.Albums.isAlbum(contentUri)) {
                genre = getAlbum(contentUri);
            } else if (MediaProvider.Artists.isArtist(contentUri)) {
                genre = getArtist(contentUri);
            } else if (MediaProvider.PrimePlaylists.isPrimePlaylist(contentUri)) {
                genre = getPrimePlaylist(contentUri);
            } else if (MediaProvider.SharedUserPlaylists.isSharedUserPlaylist(contentUri)) {
                genre = getSharedUserPlaylist(contentUri);
            } else if (MediaProvider.Playlists.isPlaylist(contentUri)) {
                genre = getPlaylist(contentUri);
            } else if (MediaProvider.Genres.isGenre(contentUri)) {
                genre = getGenre(contentUri);
            }
            return genre;
        } finally {
            Profiler.end();
        }
    }

    @Override // com.amazon.mp3.library.item.factory.LibraryItemFactory
    public AbstractItem getCollectionItem(final Uri contentUri) {
        return getItem(MediaProvider.Tracks.getSanitizedUri(contentUri));
    }

    private static abstract class ItemUriIterator<T> implements LibraryItemFactory.CursorIterator<T> {
        Cursor mCirrusCursor;
        Collection<String> mCirrusIds;
        Uri mCirrusUri;
        final ContentResolver mContentResolver;
        String mKeyField;
        Cursor mLocalCursor;
        Collection<String> mLocalIds;
        Uri mLocalUri;
        T mNextItem = null;
        String[] mProjection;
        Collection<String> mSubsetIds;

        protected abstract T advanceToNextItem(Cursor c);

        @Override // java.lang.Iterable
        public Iterator<T> iterator() {
            return this;
        }

        private void advanceLocalCursor() {
            Collection<String> collection;
            DbUtil.closeCursor(this.mLocalCursor);
            this.mLocalCursor = null;
            if (this.mLocalUri == null || (collection = this.mLocalIds) == null || collection.isEmpty()) {
                return;
            }
            DbUtil.WhereClause whereClause = new DbUtil.WhereClause(this.mKeyField, this.mLocalIds, 100);
            this.mLocalCursor = this.mContentResolver.query(this.mLocalUri, this.mProjection, whereClause.getClause(), whereClause.getArgs(), null);
        }

        private void advanceCirrusCursor() {
            Collection<String> collection;
            DbUtil.closeCursor(this.mCirrusCursor);
            this.mCirrusCursor = null;
            if (this.mCirrusUri == null || (collection = this.mCirrusIds) == null || collection.isEmpty()) {
                return;
            }
            DbUtil.WhereClause whereClause = new DbUtil.WhereClause(this.mKeyField, this.mCirrusIds, 100);
            this.mCirrusCursor = this.mContentResolver.query(this.mCirrusUri, this.mProjection, whereClause.getClause(), whereClause.getArgs(), null);
        }

        public ItemUriIterator(Context context, Uri localUri, Uri cirrusUri, String keyField, String[] projection, Collection<String> subsetIds) {
            this.mLocalIds = null;
            this.mCirrusIds = null;
            this.mLocalCursor = null;
            this.mCirrusCursor = null;
            this.mSubsetIds = null;
            this.mProjection = projection;
            this.mKeyField = keyField;
            this.mLocalUri = localUri;
            this.mCirrusUri = cirrusUri;
            ContentResolver contentResolver = context.getContentResolver();
            this.mContentResolver = contentResolver;
            if (localUri != null) {
                if (subsetIds != null && subsetIds.size() < 400) {
                    this.mLocalIds = new HashSet(subsetIds);
                    advanceLocalCursor();
                } else {
                    this.mLocalCursor = contentResolver.query(this.mLocalUri, this.mProjection, null, null, null);
                }
            }
            if (cirrusUri != null) {
                if (subsetIds != null && subsetIds.size() < 400) {
                    this.mCirrusIds = new HashSet(subsetIds);
                    advanceCirrusCursor();
                } else {
                    this.mCirrusCursor = contentResolver.query(this.mCirrusUri, this.mProjection, null, null, null);
                }
            }
            if (subsetIds != null) {
                this.mSubsetIds = new HashSet(subsetIds);
            }
        }

        private T queryNextItem() {
            Cursor cursor;
            Cursor cursor2;
            while (hasMoreItems() && this.mNextItem == null && (cursor2 = this.mLocalCursor) != null) {
                if (cursor2.getCount() == 0 || this.mLocalCursor.isAfterLast()) {
                    advanceLocalCursor();
                } else {
                    this.mNextItem = advanceToNextItem(this.mLocalCursor);
                }
            }
            while (hasMoreItems() && this.mNextItem == null && (cursor = this.mCirrusCursor) != null) {
                if (cursor.getCount() == 0 || this.mCirrusCursor.isAfterLast()) {
                    advanceCirrusCursor();
                } else {
                    this.mNextItem = advanceToNextItem(this.mCirrusCursor);
                }
            }
            return this.mNextItem;
        }

        protected boolean hasMoreItems() {
            Collection<String> collection = this.mSubsetIds;
            return collection == null || collection.size() > 0;
        }

        protected boolean acceptId(String id) {
            Collection<String> collection;
            Collection<String> collection2 = this.mSubsetIds;
            boolean z = collection2 == null || collection2.contains(id);
            if (z && (collection = this.mSubsetIds) != null) {
                collection.remove(id);
            }
            return z;
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            if (queryNextItem() != null) {
                return true;
            }
            close();
            return false;
        }

        @Override // java.util.Iterator
        public T next() {
            T tQueryNextItem = queryNextItem();
            this.mNextItem = null;
            return tQueryNextItem;
        }

        @Override // java.util.Iterator
        public void remove() {
            throw new UnsupportedOperationException();
        }

        @Override // java.io.Closeable, java.lang.AutoCloseable
        public void close() {
            DbUtil.closeCursor(this.mLocalCursor);
            DbUtil.closeCursor(this.mCirrusCursor);
            this.mCirrusCursor = null;
            this.mLocalCursor = null;
        }
    }

    public boolean hasTracksInCirrusLibrary() {
        Uri contentUri = MediaProvider.Tracks.getContentUri("cirrus");
        Cursor cursorQuery = this.mContentResolver.query(contentUri, getTrackProjection(), "ownership_status< ?", new String[]{String.valueOf(300)}, CirrusMediaSource.getTrackSortOrder() + " LIMIT 1");
        if (cursorQuery != null) {
            try {
                if (cursorQuery.getCount() > 0) {
                    cursorQuery.close();
                    cursorQuery.close();
                    return true;
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
        if (cursorQuery != null) {
            cursorQuery.close();
        }
        if (cursorQuery != null) {
            cursorQuery.close();
        }
        return false;
    }

    @Override // com.amazon.mp3.library.item.factory.LibraryItemFactory
    public LibraryItemFactory.CursorIterator<MusicTrack> getTracks(String source, Collection<String> luids) {
        Uri contentUri = ("cirrus-local".equals(source) || "merged".equals(source)) ? MediaProvider.Tracks.getContentUri("cirrus-local") : null;
        Uri contentUri2 = ("cirrus".equals(source) || "merged".equals(source)) ? MediaProvider.Tracks.getContentUri("cirrus") : null;
        final boolean zEquals = "merged".equals(source);
        return new ItemUriIterator<MusicTrack>(this.context, contentUri, contentUri2, zEquals ? "match_hash" : "luid", getTrackProjection(), luids) { // from class: com.amazon.mp3.library.item.factory.CirrusSourceLibraryItemFactory.1
            /* JADX INFO: Access modifiers changed from: protected */
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.amazon.mp3.library.item.factory.CirrusSourceLibraryItemFactory.ItemUriIterator
            public MusicTrack advanceToNextItem(Cursor c) {
                try {
                    Profiler.begin("Advancing to next track item");
                    while (hasMoreItems() && c.moveToNext()) {
                        MusicTrack track = CirrusSourceLibraryItemFactory.this.getTrack(c);
                        if (acceptId(zEquals ? Long.toString(track.getMatchHash()) : track.getLuid())) {
                            return track;
                        }
                    }
                    Profiler.end();
                    return null;
                } finally {
                    Profiler.end();
                }
            }
        };
    }

    private boolean isCatalogPlaylistTrack(Cursor trackCursor) {
        int columnIndex = trackCursor.getColumnIndex("track_type");
        return columnIndex >= 0 && trackCursor.getInt(columnIndex) == TrackType.CATALOG_PLAYLIST_TRACK.getTypeId();
    }

    @Override // com.amazon.mp3.library.item.factory.LibraryItemFactory
    public MusicTrack getTrack(Cursor trackCursor) {
        return populateTrack(trackCursor, new MusicTrack(this, null));
    }

    @Override // com.amazon.mp3.library.item.factory.LibraryItemFactory
    public MusicTrack getTrackForAsin(String asin) {
        Validate.notEmpty("The asin should not be null or empty", asin, new Object[0]);
        AbstractItem item = getItem(getUriForAsin(asin));
        if (item instanceof MusicTrack) {
            return (MusicTrack) item;
        }
        return null;
    }

    @Nullable
    public MusicTrack getCloudTrackForLuid(final String luid) {
        return AmazonApplication.getLibraryItemFactory().getTrack(MediaProvider.Tracks.getContentUri("cirrus", luid));
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:101:0x0285 A[Catch: all -> 0x0532, TryCatch #0 {all -> 0x0532, blocks: (B:3:0x0023, B:5:0x0033, B:7:0x003d, B:9:0x0046, B:11:0x0054, B:13:0x005b, B:15:0x0065, B:17:0x006f, B:19:0x007f, B:21:0x0086, B:48:0x0130, B:50:0x0151, B:52:0x0165, B:54:0x016c, B:56:0x017c, B:58:0x0183, B:60:0x0193, B:62:0x019c, B:64:0x01ab, B:66:0x01b2, B:68:0x01c2, B:70:0x01c9, B:72:0x01d8, B:74:0x01df, B:76:0x01ef, B:78:0x01f8, B:80:0x0207, B:82:0x0210, B:84:0x0220, B:86:0x0229, B:88:0x023a, B:90:0x0243, B:92:0x0254, B:94:0x025d, B:99:0x027c, B:101:0x0285, B:103:0x0296, B:105:0x029f, B:107:0x02b1, B:109:0x02ba, B:111:0x02cc, B:113:0x02d5, B:115:0x02e7, B:117:0x02f0, B:119:0x0301, B:121:0x030a, B:123:0x031c, B:125:0x0325, B:127:0x0338, B:129:0x0341, B:131:0x034b, B:141:0x0385, B:143:0x038e, B:150:0x03ad, B:152:0x03b7, B:154:0x03cf, B:156:0x03d8, B:158:0x03e4, B:160:0x03ec, B:161:0x03f1, B:163:0x03f7, B:165:0x0400, B:169:0x0410, B:170:0x0413, B:172:0x041c, B:173:0x042d, B:175:0x0436, B:176:0x0443, B:178:0x044c, B:179:0x045d, B:181:0x0466, B:183:0x0472, B:185:0x0478, B:186:0x047f, B:188:0x0488, B:189:0x0495, B:191:0x049f, B:192:0x04ad, B:194:0x04b6, B:195:0x04c3, B:197:0x04cc, B:201:0x04dc, B:203:0x04e1, B:205:0x04ea, B:206:0x04fb, B:208:0x0504, B:209:0x0515, B:211:0x0521, B:216:0x052b, B:153:0x03ca, B:144:0x0394, B:146:0x03a4, B:149:0x03aa, B:148:0x03a8, B:132:0x035d, B:134:0x0366, B:135:0x036c, B:137:0x037c, B:140:0x0382, B:139:0x0380, B:126:0x032b, B:122:0x030f, B:118:0x02f4, B:114:0x02da, B:110:0x02bf, B:106:0x02a4, B:102:0x0289, B:95:0x0265, B:97:0x0275, B:98:0x0279, B:91:0x0247, B:87:0x022d, B:83:0x0215, B:79:0x01fc, B:75:0x01e4, B:71:0x01cd, B:67:0x01b7, B:63:0x01a0, B:59:0x0188, B:55:0x0171, B:51:0x0156, B:23:0x009f, B:26:0x00ab, B:36:0x00d4, B:38:0x00da, B:43:0x00fe, B:44:0x0103, B:47:0x0111, B:30:0x00b6, B:35:0x00d0, B:31:0x00c0, B:18:0x0074), top: B:222:0x0023 }] */
    /* JADX WARN: Removed duplicated region for block: B:102:0x0289 A[Catch: all -> 0x0532, TryCatch #0 {all -> 0x0532, blocks: (B:3:0x0023, B:5:0x0033, B:7:0x003d, B:9:0x0046, B:11:0x0054, B:13:0x005b, B:15:0x0065, B:17:0x006f, B:19:0x007f, B:21:0x0086, B:48:0x0130, B:50:0x0151, B:52:0x0165, B:54:0x016c, B:56:0x017c, B:58:0x0183, B:60:0x0193, B:62:0x019c, B:64:0x01ab, B:66:0x01b2, B:68:0x01c2, B:70:0x01c9, B:72:0x01d8, B:74:0x01df, B:76:0x01ef, B:78:0x01f8, B:80:0x0207, B:82:0x0210, B:84:0x0220, B:86:0x0229, B:88:0x023a, B:90:0x0243, B:92:0x0254, B:94:0x025d, B:99:0x027c, B:101:0x0285, B:103:0x0296, B:105:0x029f, B:107:0x02b1, B:109:0x02ba, B:111:0x02cc, B:113:0x02d5, B:115:0x02e7, B:117:0x02f0, B:119:0x0301, B:121:0x030a, B:123:0x031c, B:125:0x0325, B:127:0x0338, B:129:0x0341, B:131:0x034b, B:141:0x0385, B:143:0x038e, B:150:0x03ad, B:152:0x03b7, B:154:0x03cf, B:156:0x03d8, B:158:0x03e4, B:160:0x03ec, B:161:0x03f1, B:163:0x03f7, B:165:0x0400, B:169:0x0410, B:170:0x0413, B:172:0x041c, B:173:0x042d, B:175:0x0436, B:176:0x0443, B:178:0x044c, B:179:0x045d, B:181:0x0466, B:183:0x0472, B:185:0x0478, B:186:0x047f, B:188:0x0488, B:189:0x0495, B:191:0x049f, B:192:0x04ad, B:194:0x04b6, B:195:0x04c3, B:197:0x04cc, B:201:0x04dc, B:203:0x04e1, B:205:0x04ea, B:206:0x04fb, B:208:0x0504, B:209:0x0515, B:211:0x0521, B:216:0x052b, B:153:0x03ca, B:144:0x0394, B:146:0x03a4, B:149:0x03aa, B:148:0x03a8, B:132:0x035d, B:134:0x0366, B:135:0x036c, B:137:0x037c, B:140:0x0382, B:139:0x0380, B:126:0x032b, B:122:0x030f, B:118:0x02f4, B:114:0x02da, B:110:0x02bf, B:106:0x02a4, B:102:0x0289, B:95:0x0265, B:97:0x0275, B:98:0x0279, B:91:0x0247, B:87:0x022d, B:83:0x0215, B:79:0x01fc, B:75:0x01e4, B:71:0x01cd, B:67:0x01b7, B:63:0x01a0, B:59:0x0188, B:55:0x0171, B:51:0x0156, B:23:0x009f, B:26:0x00ab, B:36:0x00d4, B:38:0x00da, B:43:0x00fe, B:44:0x0103, B:47:0x0111, B:30:0x00b6, B:35:0x00d0, B:31:0x00c0, B:18:0x0074), top: B:222:0x0023 }] */
    /* JADX WARN: Removed duplicated region for block: B:105:0x029f A[Catch: all -> 0x0532, TryCatch #0 {all -> 0x0532, blocks: (B:3:0x0023, B:5:0x0033, B:7:0x003d, B:9:0x0046, B:11:0x0054, B:13:0x005b, B:15:0x0065, B:17:0x006f, B:19:0x007f, B:21:0x0086, B:48:0x0130, B:50:0x0151, B:52:0x0165, B:54:0x016c, B:56:0x017c, B:58:0x0183, B:60:0x0193, B:62:0x019c, B:64:0x01ab, B:66:0x01b2, B:68:0x01c2, B:70:0x01c9, B:72:0x01d8, B:74:0x01df, B:76:0x01ef, B:78:0x01f8, B:80:0x0207, B:82:0x0210, B:84:0x0220, B:86:0x0229, B:88:0x023a, B:90:0x0243, B:92:0x0254, B:94:0x025d, B:99:0x027c, B:101:0x0285, B:103:0x0296, B:105:0x029f, B:107:0x02b1, B:109:0x02ba, B:111:0x02cc, B:113:0x02d5, B:115:0x02e7, B:117:0x02f0, B:119:0x0301, B:121:0x030a, B:123:0x031c, B:125:0x0325, B:127:0x0338, B:129:0x0341, B:131:0x034b, B:141:0x0385, B:143:0x038e, B:150:0x03ad, B:152:0x03b7, B:154:0x03cf, B:156:0x03d8, B:158:0x03e4, B:160:0x03ec, B:161:0x03f1, B:163:0x03f7, B:165:0x0400, B:169:0x0410, B:170:0x0413, B:172:0x041c, B:173:0x042d, B:175:0x0436, B:176:0x0443, B:178:0x044c, B:179:0x045d, B:181:0x0466, B:183:0x0472, B:185:0x0478, B:186:0x047f, B:188:0x0488, B:189:0x0495, B:191:0x049f, B:192:0x04ad, B:194:0x04b6, B:195:0x04c3, B:197:0x04cc, B:201:0x04dc, B:203:0x04e1, B:205:0x04ea, B:206:0x04fb, B:208:0x0504, B:209:0x0515, B:211:0x0521, B:216:0x052b, B:153:0x03ca, B:144:0x0394, B:146:0x03a4, B:149:0x03aa, B:148:0x03a8, B:132:0x035d, B:134:0x0366, B:135:0x036c, B:137:0x037c, B:140:0x0382, B:139:0x0380, B:126:0x032b, B:122:0x030f, B:118:0x02f4, B:114:0x02da, B:110:0x02bf, B:106:0x02a4, B:102:0x0289, B:95:0x0265, B:97:0x0275, B:98:0x0279, B:91:0x0247, B:87:0x022d, B:83:0x0215, B:79:0x01fc, B:75:0x01e4, B:71:0x01cd, B:67:0x01b7, B:63:0x01a0, B:59:0x0188, B:55:0x0171, B:51:0x0156, B:23:0x009f, B:26:0x00ab, B:36:0x00d4, B:38:0x00da, B:43:0x00fe, B:44:0x0103, B:47:0x0111, B:30:0x00b6, B:35:0x00d0, B:31:0x00c0, B:18:0x0074), top: B:222:0x0023 }] */
    /* JADX WARN: Removed duplicated region for block: B:106:0x02a4 A[Catch: all -> 0x0532, TryCatch #0 {all -> 0x0532, blocks: (B:3:0x0023, B:5:0x0033, B:7:0x003d, B:9:0x0046, B:11:0x0054, B:13:0x005b, B:15:0x0065, B:17:0x006f, B:19:0x007f, B:21:0x0086, B:48:0x0130, B:50:0x0151, B:52:0x0165, B:54:0x016c, B:56:0x017c, B:58:0x0183, B:60:0x0193, B:62:0x019c, B:64:0x01ab, B:66:0x01b2, B:68:0x01c2, B:70:0x01c9, B:72:0x01d8, B:74:0x01df, B:76:0x01ef, B:78:0x01f8, B:80:0x0207, B:82:0x0210, B:84:0x0220, B:86:0x0229, B:88:0x023a, B:90:0x0243, B:92:0x0254, B:94:0x025d, B:99:0x027c, B:101:0x0285, B:103:0x0296, B:105:0x029f, B:107:0x02b1, B:109:0x02ba, B:111:0x02cc, B:113:0x02d5, B:115:0x02e7, B:117:0x02f0, B:119:0x0301, B:121:0x030a, B:123:0x031c, B:125:0x0325, B:127:0x0338, B:129:0x0341, B:131:0x034b, B:141:0x0385, B:143:0x038e, B:150:0x03ad, B:152:0x03b7, B:154:0x03cf, B:156:0x03d8, B:158:0x03e4, B:160:0x03ec, B:161:0x03f1, B:163:0x03f7, B:165:0x0400, B:169:0x0410, B:170:0x0413, B:172:0x041c, B:173:0x042d, B:175:0x0436, B:176:0x0443, B:178:0x044c, B:179:0x045d, B:181:0x0466, B:183:0x0472, B:185:0x0478, B:186:0x047f, B:188:0x0488, B:189:0x0495, B:191:0x049f, B:192:0x04ad, B:194:0x04b6, B:195:0x04c3, B:197:0x04cc, B:201:0x04dc, B:203:0x04e1, B:205:0x04ea, B:206:0x04fb, B:208:0x0504, B:209:0x0515, B:211:0x0521, B:216:0x052b, B:153:0x03ca, B:144:0x0394, B:146:0x03a4, B:149:0x03aa, B:148:0x03a8, B:132:0x035d, B:134:0x0366, B:135:0x036c, B:137:0x037c, B:140:0x0382, B:139:0x0380, B:126:0x032b, B:122:0x030f, B:118:0x02f4, B:114:0x02da, B:110:0x02bf, B:106:0x02a4, B:102:0x0289, B:95:0x0265, B:97:0x0275, B:98:0x0279, B:91:0x0247, B:87:0x022d, B:83:0x0215, B:79:0x01fc, B:75:0x01e4, B:71:0x01cd, B:67:0x01b7, B:63:0x01a0, B:59:0x0188, B:55:0x0171, B:51:0x0156, B:23:0x009f, B:26:0x00ab, B:36:0x00d4, B:38:0x00da, B:43:0x00fe, B:44:0x0103, B:47:0x0111, B:30:0x00b6, B:35:0x00d0, B:31:0x00c0, B:18:0x0074), top: B:222:0x0023 }] */
    /* JADX WARN: Removed duplicated region for block: B:109:0x02ba A[Catch: all -> 0x0532, TryCatch #0 {all -> 0x0532, blocks: (B:3:0x0023, B:5:0x0033, B:7:0x003d, B:9:0x0046, B:11:0x0054, B:13:0x005b, B:15:0x0065, B:17:0x006f, B:19:0x007f, B:21:0x0086, B:48:0x0130, B:50:0x0151, B:52:0x0165, B:54:0x016c, B:56:0x017c, B:58:0x0183, B:60:0x0193, B:62:0x019c, B:64:0x01ab, B:66:0x01b2, B:68:0x01c2, B:70:0x01c9, B:72:0x01d8, B:74:0x01df, B:76:0x01ef, B:78:0x01f8, B:80:0x0207, B:82:0x0210, B:84:0x0220, B:86:0x0229, B:88:0x023a, B:90:0x0243, B:92:0x0254, B:94:0x025d, B:99:0x027c, B:101:0x0285, B:103:0x0296, B:105:0x029f, B:107:0x02b1, B:109:0x02ba, B:111:0x02cc, B:113:0x02d5, B:115:0x02e7, B:117:0x02f0, B:119:0x0301, B:121:0x030a, B:123:0x031c, B:125:0x0325, B:127:0x0338, B:129:0x0341, B:131:0x034b, B:141:0x0385, B:143:0x038e, B:150:0x03ad, B:152:0x03b7, B:154:0x03cf, B:156:0x03d8, B:158:0x03e4, B:160:0x03ec, B:161:0x03f1, B:163:0x03f7, B:165:0x0400, B:169:0x0410, B:170:0x0413, B:172:0x041c, B:173:0x042d, B:175:0x0436, B:176:0x0443, B:178:0x044c, B:179:0x045d, B:181:0x0466, B:183:0x0472, B:185:0x0478, B:186:0x047f, B:188:0x0488, B:189:0x0495, B:191:0x049f, B:192:0x04ad, B:194:0x04b6, B:195:0x04c3, B:197:0x04cc, B:201:0x04dc, B:203:0x04e1, B:205:0x04ea, B:206:0x04fb, B:208:0x0504, B:209:0x0515, B:211:0x0521, B:216:0x052b, B:153:0x03ca, B:144:0x0394, B:146:0x03a4, B:149:0x03aa, B:148:0x03a8, B:132:0x035d, B:134:0x0366, B:135:0x036c, B:137:0x037c, B:140:0x0382, B:139:0x0380, B:126:0x032b, B:122:0x030f, B:118:0x02f4, B:114:0x02da, B:110:0x02bf, B:106:0x02a4, B:102:0x0289, B:95:0x0265, B:97:0x0275, B:98:0x0279, B:91:0x0247, B:87:0x022d, B:83:0x0215, B:79:0x01fc, B:75:0x01e4, B:71:0x01cd, B:67:0x01b7, B:63:0x01a0, B:59:0x0188, B:55:0x0171, B:51:0x0156, B:23:0x009f, B:26:0x00ab, B:36:0x00d4, B:38:0x00da, B:43:0x00fe, B:44:0x0103, B:47:0x0111, B:30:0x00b6, B:35:0x00d0, B:31:0x00c0, B:18:0x0074), top: B:222:0x0023 }] */
    /* JADX WARN: Removed duplicated region for block: B:110:0x02bf A[Catch: all -> 0x0532, TryCatch #0 {all -> 0x0532, blocks: (B:3:0x0023, B:5:0x0033, B:7:0x003d, B:9:0x0046, B:11:0x0054, B:13:0x005b, B:15:0x0065, B:17:0x006f, B:19:0x007f, B:21:0x0086, B:48:0x0130, B:50:0x0151, B:52:0x0165, B:54:0x016c, B:56:0x017c, B:58:0x0183, B:60:0x0193, B:62:0x019c, B:64:0x01ab, B:66:0x01b2, B:68:0x01c2, B:70:0x01c9, B:72:0x01d8, B:74:0x01df, B:76:0x01ef, B:78:0x01f8, B:80:0x0207, B:82:0x0210, B:84:0x0220, B:86:0x0229, B:88:0x023a, B:90:0x0243, B:92:0x0254, B:94:0x025d, B:99:0x027c, B:101:0x0285, B:103:0x0296, B:105:0x029f, B:107:0x02b1, B:109:0x02ba, B:111:0x02cc, B:113:0x02d5, B:115:0x02e7, B:117:0x02f0, B:119:0x0301, B:121:0x030a, B:123:0x031c, B:125:0x0325, B:127:0x0338, B:129:0x0341, B:131:0x034b, B:141:0x0385, B:143:0x038e, B:150:0x03ad, B:152:0x03b7, B:154:0x03cf, B:156:0x03d8, B:158:0x03e4, B:160:0x03ec, B:161:0x03f1, B:163:0x03f7, B:165:0x0400, B:169:0x0410, B:170:0x0413, B:172:0x041c, B:173:0x042d, B:175:0x0436, B:176:0x0443, B:178:0x044c, B:179:0x045d, B:181:0x0466, B:183:0x0472, B:185:0x0478, B:186:0x047f, B:188:0x0488, B:189:0x0495, B:191:0x049f, B:192:0x04ad, B:194:0x04b6, B:195:0x04c3, B:197:0x04cc, B:201:0x04dc, B:203:0x04e1, B:205:0x04ea, B:206:0x04fb, B:208:0x0504, B:209:0x0515, B:211:0x0521, B:216:0x052b, B:153:0x03ca, B:144:0x0394, B:146:0x03a4, B:149:0x03aa, B:148:0x03a8, B:132:0x035d, B:134:0x0366, B:135:0x036c, B:137:0x037c, B:140:0x0382, B:139:0x0380, B:126:0x032b, B:122:0x030f, B:118:0x02f4, B:114:0x02da, B:110:0x02bf, B:106:0x02a4, B:102:0x0289, B:95:0x0265, B:97:0x0275, B:98:0x0279, B:91:0x0247, B:87:0x022d, B:83:0x0215, B:79:0x01fc, B:75:0x01e4, B:71:0x01cd, B:67:0x01b7, B:63:0x01a0, B:59:0x0188, B:55:0x0171, B:51:0x0156, B:23:0x009f, B:26:0x00ab, B:36:0x00d4, B:38:0x00da, B:43:0x00fe, B:44:0x0103, B:47:0x0111, B:30:0x00b6, B:35:0x00d0, B:31:0x00c0, B:18:0x0074), top: B:222:0x0023 }] */
    /* JADX WARN: Removed duplicated region for block: B:113:0x02d5 A[Catch: all -> 0x0532, TryCatch #0 {all -> 0x0532, blocks: (B:3:0x0023, B:5:0x0033, B:7:0x003d, B:9:0x0046, B:11:0x0054, B:13:0x005b, B:15:0x0065, B:17:0x006f, B:19:0x007f, B:21:0x0086, B:48:0x0130, B:50:0x0151, B:52:0x0165, B:54:0x016c, B:56:0x017c, B:58:0x0183, B:60:0x0193, B:62:0x019c, B:64:0x01ab, B:66:0x01b2, B:68:0x01c2, B:70:0x01c9, B:72:0x01d8, B:74:0x01df, B:76:0x01ef, B:78:0x01f8, B:80:0x0207, B:82:0x0210, B:84:0x0220, B:86:0x0229, B:88:0x023a, B:90:0x0243, B:92:0x0254, B:94:0x025d, B:99:0x027c, B:101:0x0285, B:103:0x0296, B:105:0x029f, B:107:0x02b1, B:109:0x02ba, B:111:0x02cc, B:113:0x02d5, B:115:0x02e7, B:117:0x02f0, B:119:0x0301, B:121:0x030a, B:123:0x031c, B:125:0x0325, B:127:0x0338, B:129:0x0341, B:131:0x034b, B:141:0x0385, B:143:0x038e, B:150:0x03ad, B:152:0x03b7, B:154:0x03cf, B:156:0x03d8, B:158:0x03e4, B:160:0x03ec, B:161:0x03f1, B:163:0x03f7, B:165:0x0400, B:169:0x0410, B:170:0x0413, B:172:0x041c, B:173:0x042d, B:175:0x0436, B:176:0x0443, B:178:0x044c, B:179:0x045d, B:181:0x0466, B:183:0x0472, B:185:0x0478, B:186:0x047f, B:188:0x0488, B:189:0x0495, B:191:0x049f, B:192:0x04ad, B:194:0x04b6, B:195:0x04c3, B:197:0x04cc, B:201:0x04dc, B:203:0x04e1, B:205:0x04ea, B:206:0x04fb, B:208:0x0504, B:209:0x0515, B:211:0x0521, B:216:0x052b, B:153:0x03ca, B:144:0x0394, B:146:0x03a4, B:149:0x03aa, B:148:0x03a8, B:132:0x035d, B:134:0x0366, B:135:0x036c, B:137:0x037c, B:140:0x0382, B:139:0x0380, B:126:0x032b, B:122:0x030f, B:118:0x02f4, B:114:0x02da, B:110:0x02bf, B:106:0x02a4, B:102:0x0289, B:95:0x0265, B:97:0x0275, B:98:0x0279, B:91:0x0247, B:87:0x022d, B:83:0x0215, B:79:0x01fc, B:75:0x01e4, B:71:0x01cd, B:67:0x01b7, B:63:0x01a0, B:59:0x0188, B:55:0x0171, B:51:0x0156, B:23:0x009f, B:26:0x00ab, B:36:0x00d4, B:38:0x00da, B:43:0x00fe, B:44:0x0103, B:47:0x0111, B:30:0x00b6, B:35:0x00d0, B:31:0x00c0, B:18:0x0074), top: B:222:0x0023 }] */
    /* JADX WARN: Removed duplicated region for block: B:114:0x02da A[Catch: all -> 0x0532, TryCatch #0 {all -> 0x0532, blocks: (B:3:0x0023, B:5:0x0033, B:7:0x003d, B:9:0x0046, B:11:0x0054, B:13:0x005b, B:15:0x0065, B:17:0x006f, B:19:0x007f, B:21:0x0086, B:48:0x0130, B:50:0x0151, B:52:0x0165, B:54:0x016c, B:56:0x017c, B:58:0x0183, B:60:0x0193, B:62:0x019c, B:64:0x01ab, B:66:0x01b2, B:68:0x01c2, B:70:0x01c9, B:72:0x01d8, B:74:0x01df, B:76:0x01ef, B:78:0x01f8, B:80:0x0207, B:82:0x0210, B:84:0x0220, B:86:0x0229, B:88:0x023a, B:90:0x0243, B:92:0x0254, B:94:0x025d, B:99:0x027c, B:101:0x0285, B:103:0x0296, B:105:0x029f, B:107:0x02b1, B:109:0x02ba, B:111:0x02cc, B:113:0x02d5, B:115:0x02e7, B:117:0x02f0, B:119:0x0301, B:121:0x030a, B:123:0x031c, B:125:0x0325, B:127:0x0338, B:129:0x0341, B:131:0x034b, B:141:0x0385, B:143:0x038e, B:150:0x03ad, B:152:0x03b7, B:154:0x03cf, B:156:0x03d8, B:158:0x03e4, B:160:0x03ec, B:161:0x03f1, B:163:0x03f7, B:165:0x0400, B:169:0x0410, B:170:0x0413, B:172:0x041c, B:173:0x042d, B:175:0x0436, B:176:0x0443, B:178:0x044c, B:179:0x045d, B:181:0x0466, B:183:0x0472, B:185:0x0478, B:186:0x047f, B:188:0x0488, B:189:0x0495, B:191:0x049f, B:192:0x04ad, B:194:0x04b6, B:195:0x04c3, B:197:0x04cc, B:201:0x04dc, B:203:0x04e1, B:205:0x04ea, B:206:0x04fb, B:208:0x0504, B:209:0x0515, B:211:0x0521, B:216:0x052b, B:153:0x03ca, B:144:0x0394, B:146:0x03a4, B:149:0x03aa, B:148:0x03a8, B:132:0x035d, B:134:0x0366, B:135:0x036c, B:137:0x037c, B:140:0x0382, B:139:0x0380, B:126:0x032b, B:122:0x030f, B:118:0x02f4, B:114:0x02da, B:110:0x02bf, B:106:0x02a4, B:102:0x0289, B:95:0x0265, B:97:0x0275, B:98:0x0279, B:91:0x0247, B:87:0x022d, B:83:0x0215, B:79:0x01fc, B:75:0x01e4, B:71:0x01cd, B:67:0x01b7, B:63:0x01a0, B:59:0x0188, B:55:0x0171, B:51:0x0156, B:23:0x009f, B:26:0x00ab, B:36:0x00d4, B:38:0x00da, B:43:0x00fe, B:44:0x0103, B:47:0x0111, B:30:0x00b6, B:35:0x00d0, B:31:0x00c0, B:18:0x0074), top: B:222:0x0023 }] */
    /* JADX WARN: Removed duplicated region for block: B:117:0x02f0 A[Catch: all -> 0x0532, TryCatch #0 {all -> 0x0532, blocks: (B:3:0x0023, B:5:0x0033, B:7:0x003d, B:9:0x0046, B:11:0x0054, B:13:0x005b, B:15:0x0065, B:17:0x006f, B:19:0x007f, B:21:0x0086, B:48:0x0130, B:50:0x0151, B:52:0x0165, B:54:0x016c, B:56:0x017c, B:58:0x0183, B:60:0x0193, B:62:0x019c, B:64:0x01ab, B:66:0x01b2, B:68:0x01c2, B:70:0x01c9, B:72:0x01d8, B:74:0x01df, B:76:0x01ef, B:78:0x01f8, B:80:0x0207, B:82:0x0210, B:84:0x0220, B:86:0x0229, B:88:0x023a, B:90:0x0243, B:92:0x0254, B:94:0x025d, B:99:0x027c, B:101:0x0285, B:103:0x0296, B:105:0x029f, B:107:0x02b1, B:109:0x02ba, B:111:0x02cc, B:113:0x02d5, B:115:0x02e7, B:117:0x02f0, B:119:0x0301, B:121:0x030a, B:123:0x031c, B:125:0x0325, B:127:0x0338, B:129:0x0341, B:131:0x034b, B:141:0x0385, B:143:0x038e, B:150:0x03ad, B:152:0x03b7, B:154:0x03cf, B:156:0x03d8, B:158:0x03e4, B:160:0x03ec, B:161:0x03f1, B:163:0x03f7, B:165:0x0400, B:169:0x0410, B:170:0x0413, B:172:0x041c, B:173:0x042d, B:175:0x0436, B:176:0x0443, B:178:0x044c, B:179:0x045d, B:181:0x0466, B:183:0x0472, B:185:0x0478, B:186:0x047f, B:188:0x0488, B:189:0x0495, B:191:0x049f, B:192:0x04ad, B:194:0x04b6, B:195:0x04c3, B:197:0x04cc, B:201:0x04dc, B:203:0x04e1, B:205:0x04ea, B:206:0x04fb, B:208:0x0504, B:209:0x0515, B:211:0x0521, B:216:0x052b, B:153:0x03ca, B:144:0x0394, B:146:0x03a4, B:149:0x03aa, B:148:0x03a8, B:132:0x035d, B:134:0x0366, B:135:0x036c, B:137:0x037c, B:140:0x0382, B:139:0x0380, B:126:0x032b, B:122:0x030f, B:118:0x02f4, B:114:0x02da, B:110:0x02bf, B:106:0x02a4, B:102:0x0289, B:95:0x0265, B:97:0x0275, B:98:0x0279, B:91:0x0247, B:87:0x022d, B:83:0x0215, B:79:0x01fc, B:75:0x01e4, B:71:0x01cd, B:67:0x01b7, B:63:0x01a0, B:59:0x0188, B:55:0x0171, B:51:0x0156, B:23:0x009f, B:26:0x00ab, B:36:0x00d4, B:38:0x00da, B:43:0x00fe, B:44:0x0103, B:47:0x0111, B:30:0x00b6, B:35:0x00d0, B:31:0x00c0, B:18:0x0074), top: B:222:0x0023 }] */
    /* JADX WARN: Removed duplicated region for block: B:118:0x02f4 A[Catch: all -> 0x0532, TryCatch #0 {all -> 0x0532, blocks: (B:3:0x0023, B:5:0x0033, B:7:0x003d, B:9:0x0046, B:11:0x0054, B:13:0x005b, B:15:0x0065, B:17:0x006f, B:19:0x007f, B:21:0x0086, B:48:0x0130, B:50:0x0151, B:52:0x0165, B:54:0x016c, B:56:0x017c, B:58:0x0183, B:60:0x0193, B:62:0x019c, B:64:0x01ab, B:66:0x01b2, B:68:0x01c2, B:70:0x01c9, B:72:0x01d8, B:74:0x01df, B:76:0x01ef, B:78:0x01f8, B:80:0x0207, B:82:0x0210, B:84:0x0220, B:86:0x0229, B:88:0x023a, B:90:0x0243, B:92:0x0254, B:94:0x025d, B:99:0x027c, B:101:0x0285, B:103:0x0296, B:105:0x029f, B:107:0x02b1, B:109:0x02ba, B:111:0x02cc, B:113:0x02d5, B:115:0x02e7, B:117:0x02f0, B:119:0x0301, B:121:0x030a, B:123:0x031c, B:125:0x0325, B:127:0x0338, B:129:0x0341, B:131:0x034b, B:141:0x0385, B:143:0x038e, B:150:0x03ad, B:152:0x03b7, B:154:0x03cf, B:156:0x03d8, B:158:0x03e4, B:160:0x03ec, B:161:0x03f1, B:163:0x03f7, B:165:0x0400, B:169:0x0410, B:170:0x0413, B:172:0x041c, B:173:0x042d, B:175:0x0436, B:176:0x0443, B:178:0x044c, B:179:0x045d, B:181:0x0466, B:183:0x0472, B:185:0x0478, B:186:0x047f, B:188:0x0488, B:189:0x0495, B:191:0x049f, B:192:0x04ad, B:194:0x04b6, B:195:0x04c3, B:197:0x04cc, B:201:0x04dc, B:203:0x04e1, B:205:0x04ea, B:206:0x04fb, B:208:0x0504, B:209:0x0515, B:211:0x0521, B:216:0x052b, B:153:0x03ca, B:144:0x0394, B:146:0x03a4, B:149:0x03aa, B:148:0x03a8, B:132:0x035d, B:134:0x0366, B:135:0x036c, B:137:0x037c, B:140:0x0382, B:139:0x0380, B:126:0x032b, B:122:0x030f, B:118:0x02f4, B:114:0x02da, B:110:0x02bf, B:106:0x02a4, B:102:0x0289, B:95:0x0265, B:97:0x0275, B:98:0x0279, B:91:0x0247, B:87:0x022d, B:83:0x0215, B:79:0x01fc, B:75:0x01e4, B:71:0x01cd, B:67:0x01b7, B:63:0x01a0, B:59:0x0188, B:55:0x0171, B:51:0x0156, B:23:0x009f, B:26:0x00ab, B:36:0x00d4, B:38:0x00da, B:43:0x00fe, B:44:0x0103, B:47:0x0111, B:30:0x00b6, B:35:0x00d0, B:31:0x00c0, B:18:0x0074), top: B:222:0x0023 }] */
    /* JADX WARN: Removed duplicated region for block: B:121:0x030a A[Catch: all -> 0x0532, TryCatch #0 {all -> 0x0532, blocks: (B:3:0x0023, B:5:0x0033, B:7:0x003d, B:9:0x0046, B:11:0x0054, B:13:0x005b, B:15:0x0065, B:17:0x006f, B:19:0x007f, B:21:0x0086, B:48:0x0130, B:50:0x0151, B:52:0x0165, B:54:0x016c, B:56:0x017c, B:58:0x0183, B:60:0x0193, B:62:0x019c, B:64:0x01ab, B:66:0x01b2, B:68:0x01c2, B:70:0x01c9, B:72:0x01d8, B:74:0x01df, B:76:0x01ef, B:78:0x01f8, B:80:0x0207, B:82:0x0210, B:84:0x0220, B:86:0x0229, B:88:0x023a, B:90:0x0243, B:92:0x0254, B:94:0x025d, B:99:0x027c, B:101:0x0285, B:103:0x0296, B:105:0x029f, B:107:0x02b1, B:109:0x02ba, B:111:0x02cc, B:113:0x02d5, B:115:0x02e7, B:117:0x02f0, B:119:0x0301, B:121:0x030a, B:123:0x031c, B:125:0x0325, B:127:0x0338, B:129:0x0341, B:131:0x034b, B:141:0x0385, B:143:0x038e, B:150:0x03ad, B:152:0x03b7, B:154:0x03cf, B:156:0x03d8, B:158:0x03e4, B:160:0x03ec, B:161:0x03f1, B:163:0x03f7, B:165:0x0400, B:169:0x0410, B:170:0x0413, B:172:0x041c, B:173:0x042d, B:175:0x0436, B:176:0x0443, B:178:0x044c, B:179:0x045d, B:181:0x0466, B:183:0x0472, B:185:0x0478, B:186:0x047f, B:188:0x0488, B:189:0x0495, B:191:0x049f, B:192:0x04ad, B:194:0x04b6, B:195:0x04c3, B:197:0x04cc, B:201:0x04dc, B:203:0x04e1, B:205:0x04ea, B:206:0x04fb, B:208:0x0504, B:209:0x0515, B:211:0x0521, B:216:0x052b, B:153:0x03ca, B:144:0x0394, B:146:0x03a4, B:149:0x03aa, B:148:0x03a8, B:132:0x035d, B:134:0x0366, B:135:0x036c, B:137:0x037c, B:140:0x0382, B:139:0x0380, B:126:0x032b, B:122:0x030f, B:118:0x02f4, B:114:0x02da, B:110:0x02bf, B:106:0x02a4, B:102:0x0289, B:95:0x0265, B:97:0x0275, B:98:0x0279, B:91:0x0247, B:87:0x022d, B:83:0x0215, B:79:0x01fc, B:75:0x01e4, B:71:0x01cd, B:67:0x01b7, B:63:0x01a0, B:59:0x0188, B:55:0x0171, B:51:0x0156, B:23:0x009f, B:26:0x00ab, B:36:0x00d4, B:38:0x00da, B:43:0x00fe, B:44:0x0103, B:47:0x0111, B:30:0x00b6, B:35:0x00d0, B:31:0x00c0, B:18:0x0074), top: B:222:0x0023 }] */
    /* JADX WARN: Removed duplicated region for block: B:122:0x030f A[Catch: all -> 0x0532, TryCatch #0 {all -> 0x0532, blocks: (B:3:0x0023, B:5:0x0033, B:7:0x003d, B:9:0x0046, B:11:0x0054, B:13:0x005b, B:15:0x0065, B:17:0x006f, B:19:0x007f, B:21:0x0086, B:48:0x0130, B:50:0x0151, B:52:0x0165, B:54:0x016c, B:56:0x017c, B:58:0x0183, B:60:0x0193, B:62:0x019c, B:64:0x01ab, B:66:0x01b2, B:68:0x01c2, B:70:0x01c9, B:72:0x01d8, B:74:0x01df, B:76:0x01ef, B:78:0x01f8, B:80:0x0207, B:82:0x0210, B:84:0x0220, B:86:0x0229, B:88:0x023a, B:90:0x0243, B:92:0x0254, B:94:0x025d, B:99:0x027c, B:101:0x0285, B:103:0x0296, B:105:0x029f, B:107:0x02b1, B:109:0x02ba, B:111:0x02cc, B:113:0x02d5, B:115:0x02e7, B:117:0x02f0, B:119:0x0301, B:121:0x030a, B:123:0x031c, B:125:0x0325, B:127:0x0338, B:129:0x0341, B:131:0x034b, B:141:0x0385, B:143:0x038e, B:150:0x03ad, B:152:0x03b7, B:154:0x03cf, B:156:0x03d8, B:158:0x03e4, B:160:0x03ec, B:161:0x03f1, B:163:0x03f7, B:165:0x0400, B:169:0x0410, B:170:0x0413, B:172:0x041c, B:173:0x042d, B:175:0x0436, B:176:0x0443, B:178:0x044c, B:179:0x045d, B:181:0x0466, B:183:0x0472, B:185:0x0478, B:186:0x047f, B:188:0x0488, B:189:0x0495, B:191:0x049f, B:192:0x04ad, B:194:0x04b6, B:195:0x04c3, B:197:0x04cc, B:201:0x04dc, B:203:0x04e1, B:205:0x04ea, B:206:0x04fb, B:208:0x0504, B:209:0x0515, B:211:0x0521, B:216:0x052b, B:153:0x03ca, B:144:0x0394, B:146:0x03a4, B:149:0x03aa, B:148:0x03a8, B:132:0x035d, B:134:0x0366, B:135:0x036c, B:137:0x037c, B:140:0x0382, B:139:0x0380, B:126:0x032b, B:122:0x030f, B:118:0x02f4, B:114:0x02da, B:110:0x02bf, B:106:0x02a4, B:102:0x0289, B:95:0x0265, B:97:0x0275, B:98:0x0279, B:91:0x0247, B:87:0x022d, B:83:0x0215, B:79:0x01fc, B:75:0x01e4, B:71:0x01cd, B:67:0x01b7, B:63:0x01a0, B:59:0x0188, B:55:0x0171, B:51:0x0156, B:23:0x009f, B:26:0x00ab, B:36:0x00d4, B:38:0x00da, B:43:0x00fe, B:44:0x0103, B:47:0x0111, B:30:0x00b6, B:35:0x00d0, B:31:0x00c0, B:18:0x0074), top: B:222:0x0023 }] */
    /* JADX WARN: Removed duplicated region for block: B:125:0x0325 A[Catch: all -> 0x0532, TryCatch #0 {all -> 0x0532, blocks: (B:3:0x0023, B:5:0x0033, B:7:0x003d, B:9:0x0046, B:11:0x0054, B:13:0x005b, B:15:0x0065, B:17:0x006f, B:19:0x007f, B:21:0x0086, B:48:0x0130, B:50:0x0151, B:52:0x0165, B:54:0x016c, B:56:0x017c, B:58:0x0183, B:60:0x0193, B:62:0x019c, B:64:0x01ab, B:66:0x01b2, B:68:0x01c2, B:70:0x01c9, B:72:0x01d8, B:74:0x01df, B:76:0x01ef, B:78:0x01f8, B:80:0x0207, B:82:0x0210, B:84:0x0220, B:86:0x0229, B:88:0x023a, B:90:0x0243, B:92:0x0254, B:94:0x025d, B:99:0x027c, B:101:0x0285, B:103:0x0296, B:105:0x029f, B:107:0x02b1, B:109:0x02ba, B:111:0x02cc, B:113:0x02d5, B:115:0x02e7, B:117:0x02f0, B:119:0x0301, B:121:0x030a, B:123:0x031c, B:125:0x0325, B:127:0x0338, B:129:0x0341, B:131:0x034b, B:141:0x0385, B:143:0x038e, B:150:0x03ad, B:152:0x03b7, B:154:0x03cf, B:156:0x03d8, B:158:0x03e4, B:160:0x03ec, B:161:0x03f1, B:163:0x03f7, B:165:0x0400, B:169:0x0410, B:170:0x0413, B:172:0x041c, B:173:0x042d, B:175:0x0436, B:176:0x0443, B:178:0x044c, B:179:0x045d, B:181:0x0466, B:183:0x0472, B:185:0x0478, B:186:0x047f, B:188:0x0488, B:189:0x0495, B:191:0x049f, B:192:0x04ad, B:194:0x04b6, B:195:0x04c3, B:197:0x04cc, B:201:0x04dc, B:203:0x04e1, B:205:0x04ea, B:206:0x04fb, B:208:0x0504, B:209:0x0515, B:211:0x0521, B:216:0x052b, B:153:0x03ca, B:144:0x0394, B:146:0x03a4, B:149:0x03aa, B:148:0x03a8, B:132:0x035d, B:134:0x0366, B:135:0x036c, B:137:0x037c, B:140:0x0382, B:139:0x0380, B:126:0x032b, B:122:0x030f, B:118:0x02f4, B:114:0x02da, B:110:0x02bf, B:106:0x02a4, B:102:0x0289, B:95:0x0265, B:97:0x0275, B:98:0x0279, B:91:0x0247, B:87:0x022d, B:83:0x0215, B:79:0x01fc, B:75:0x01e4, B:71:0x01cd, B:67:0x01b7, B:63:0x01a0, B:59:0x0188, B:55:0x0171, B:51:0x0156, B:23:0x009f, B:26:0x00ab, B:36:0x00d4, B:38:0x00da, B:43:0x00fe, B:44:0x0103, B:47:0x0111, B:30:0x00b6, B:35:0x00d0, B:31:0x00c0, B:18:0x0074), top: B:222:0x0023 }] */
    /* JADX WARN: Removed duplicated region for block: B:126:0x032b A[Catch: all -> 0x0532, TryCatch #0 {all -> 0x0532, blocks: (B:3:0x0023, B:5:0x0033, B:7:0x003d, B:9:0x0046, B:11:0x0054, B:13:0x005b, B:15:0x0065, B:17:0x006f, B:19:0x007f, B:21:0x0086, B:48:0x0130, B:50:0x0151, B:52:0x0165, B:54:0x016c, B:56:0x017c, B:58:0x0183, B:60:0x0193, B:62:0x019c, B:64:0x01ab, B:66:0x01b2, B:68:0x01c2, B:70:0x01c9, B:72:0x01d8, B:74:0x01df, B:76:0x01ef, B:78:0x01f8, B:80:0x0207, B:82:0x0210, B:84:0x0220, B:86:0x0229, B:88:0x023a, B:90:0x0243, B:92:0x0254, B:94:0x025d, B:99:0x027c, B:101:0x0285, B:103:0x0296, B:105:0x029f, B:107:0x02b1, B:109:0x02ba, B:111:0x02cc, B:113:0x02d5, B:115:0x02e7, B:117:0x02f0, B:119:0x0301, B:121:0x030a, B:123:0x031c, B:125:0x0325, B:127:0x0338, B:129:0x0341, B:131:0x034b, B:141:0x0385, B:143:0x038e, B:150:0x03ad, B:152:0x03b7, B:154:0x03cf, B:156:0x03d8, B:158:0x03e4, B:160:0x03ec, B:161:0x03f1, B:163:0x03f7, B:165:0x0400, B:169:0x0410, B:170:0x0413, B:172:0x041c, B:173:0x042d, B:175:0x0436, B:176:0x0443, B:178:0x044c, B:179:0x045d, B:181:0x0466, B:183:0x0472, B:185:0x0478, B:186:0x047f, B:188:0x0488, B:189:0x0495, B:191:0x049f, B:192:0x04ad, B:194:0x04b6, B:195:0x04c3, B:197:0x04cc, B:201:0x04dc, B:203:0x04e1, B:205:0x04ea, B:206:0x04fb, B:208:0x0504, B:209:0x0515, B:211:0x0521, B:216:0x052b, B:153:0x03ca, B:144:0x0394, B:146:0x03a4, B:149:0x03aa, B:148:0x03a8, B:132:0x035d, B:134:0x0366, B:135:0x036c, B:137:0x037c, B:140:0x0382, B:139:0x0380, B:126:0x032b, B:122:0x030f, B:118:0x02f4, B:114:0x02da, B:110:0x02bf, B:106:0x02a4, B:102:0x0289, B:95:0x0265, B:97:0x0275, B:98:0x0279, B:91:0x0247, B:87:0x022d, B:83:0x0215, B:79:0x01fc, B:75:0x01e4, B:71:0x01cd, B:67:0x01b7, B:63:0x01a0, B:59:0x0188, B:55:0x0171, B:51:0x0156, B:23:0x009f, B:26:0x00ab, B:36:0x00d4, B:38:0x00da, B:43:0x00fe, B:44:0x0103, B:47:0x0111, B:30:0x00b6, B:35:0x00d0, B:31:0x00c0, B:18:0x0074), top: B:222:0x0023 }] */
    /* JADX WARN: Removed duplicated region for block: B:132:0x035d A[Catch: all -> 0x0532, TryCatch #0 {all -> 0x0532, blocks: (B:3:0x0023, B:5:0x0033, B:7:0x003d, B:9:0x0046, B:11:0x0054, B:13:0x005b, B:15:0x0065, B:17:0x006f, B:19:0x007f, B:21:0x0086, B:48:0x0130, B:50:0x0151, B:52:0x0165, B:54:0x016c, B:56:0x017c, B:58:0x0183, B:60:0x0193, B:62:0x019c, B:64:0x01ab, B:66:0x01b2, B:68:0x01c2, B:70:0x01c9, B:72:0x01d8, B:74:0x01df, B:76:0x01ef, B:78:0x01f8, B:80:0x0207, B:82:0x0210, B:84:0x0220, B:86:0x0229, B:88:0x023a, B:90:0x0243, B:92:0x0254, B:94:0x025d, B:99:0x027c, B:101:0x0285, B:103:0x0296, B:105:0x029f, B:107:0x02b1, B:109:0x02ba, B:111:0x02cc, B:113:0x02d5, B:115:0x02e7, B:117:0x02f0, B:119:0x0301, B:121:0x030a, B:123:0x031c, B:125:0x0325, B:127:0x0338, B:129:0x0341, B:131:0x034b, B:141:0x0385, B:143:0x038e, B:150:0x03ad, B:152:0x03b7, B:154:0x03cf, B:156:0x03d8, B:158:0x03e4, B:160:0x03ec, B:161:0x03f1, B:163:0x03f7, B:165:0x0400, B:169:0x0410, B:170:0x0413, B:172:0x041c, B:173:0x042d, B:175:0x0436, B:176:0x0443, B:178:0x044c, B:179:0x045d, B:181:0x0466, B:183:0x0472, B:185:0x0478, B:186:0x047f, B:188:0x0488, B:189:0x0495, B:191:0x049f, B:192:0x04ad, B:194:0x04b6, B:195:0x04c3, B:197:0x04cc, B:201:0x04dc, B:203:0x04e1, B:205:0x04ea, B:206:0x04fb, B:208:0x0504, B:209:0x0515, B:211:0x0521, B:216:0x052b, B:153:0x03ca, B:144:0x0394, B:146:0x03a4, B:149:0x03aa, B:148:0x03a8, B:132:0x035d, B:134:0x0366, B:135:0x036c, B:137:0x037c, B:140:0x0382, B:139:0x0380, B:126:0x032b, B:122:0x030f, B:118:0x02f4, B:114:0x02da, B:110:0x02bf, B:106:0x02a4, B:102:0x0289, B:95:0x0265, B:97:0x0275, B:98:0x0279, B:91:0x0247, B:87:0x022d, B:83:0x0215, B:79:0x01fc, B:75:0x01e4, B:71:0x01cd, B:67:0x01b7, B:63:0x01a0, B:59:0x0188, B:55:0x0171, B:51:0x0156, B:23:0x009f, B:26:0x00ab, B:36:0x00d4, B:38:0x00da, B:43:0x00fe, B:44:0x0103, B:47:0x0111, B:30:0x00b6, B:35:0x00d0, B:31:0x00c0, B:18:0x0074), top: B:222:0x0023 }] */
    /* JADX WARN: Removed duplicated region for block: B:134:0x0366 A[Catch: all -> 0x0532, TryCatch #0 {all -> 0x0532, blocks: (B:3:0x0023, B:5:0x0033, B:7:0x003d, B:9:0x0046, B:11:0x0054, B:13:0x005b, B:15:0x0065, B:17:0x006f, B:19:0x007f, B:21:0x0086, B:48:0x0130, B:50:0x0151, B:52:0x0165, B:54:0x016c, B:56:0x017c, B:58:0x0183, B:60:0x0193, B:62:0x019c, B:64:0x01ab, B:66:0x01b2, B:68:0x01c2, B:70:0x01c9, B:72:0x01d8, B:74:0x01df, B:76:0x01ef, B:78:0x01f8, B:80:0x0207, B:82:0x0210, B:84:0x0220, B:86:0x0229, B:88:0x023a, B:90:0x0243, B:92:0x0254, B:94:0x025d, B:99:0x027c, B:101:0x0285, B:103:0x0296, B:105:0x029f, B:107:0x02b1, B:109:0x02ba, B:111:0x02cc, B:113:0x02d5, B:115:0x02e7, B:117:0x02f0, B:119:0x0301, B:121:0x030a, B:123:0x031c, B:125:0x0325, B:127:0x0338, B:129:0x0341, B:131:0x034b, B:141:0x0385, B:143:0x038e, B:150:0x03ad, B:152:0x03b7, B:154:0x03cf, B:156:0x03d8, B:158:0x03e4, B:160:0x03ec, B:161:0x03f1, B:163:0x03f7, B:165:0x0400, B:169:0x0410, B:170:0x0413, B:172:0x041c, B:173:0x042d, B:175:0x0436, B:176:0x0443, B:178:0x044c, B:179:0x045d, B:181:0x0466, B:183:0x0472, B:185:0x0478, B:186:0x047f, B:188:0x0488, B:189:0x0495, B:191:0x049f, B:192:0x04ad, B:194:0x04b6, B:195:0x04c3, B:197:0x04cc, B:201:0x04dc, B:203:0x04e1, B:205:0x04ea, B:206:0x04fb, B:208:0x0504, B:209:0x0515, B:211:0x0521, B:216:0x052b, B:153:0x03ca, B:144:0x0394, B:146:0x03a4, B:149:0x03aa, B:148:0x03a8, B:132:0x035d, B:134:0x0366, B:135:0x036c, B:137:0x037c, B:140:0x0382, B:139:0x0380, B:126:0x032b, B:122:0x030f, B:118:0x02f4, B:114:0x02da, B:110:0x02bf, B:106:0x02a4, B:102:0x0289, B:95:0x0265, B:97:0x0275, B:98:0x0279, B:91:0x0247, B:87:0x022d, B:83:0x0215, B:79:0x01fc, B:75:0x01e4, B:71:0x01cd, B:67:0x01b7, B:63:0x01a0, B:59:0x0188, B:55:0x0171, B:51:0x0156, B:23:0x009f, B:26:0x00ab, B:36:0x00d4, B:38:0x00da, B:43:0x00fe, B:44:0x0103, B:47:0x0111, B:30:0x00b6, B:35:0x00d0, B:31:0x00c0, B:18:0x0074), top: B:222:0x0023 }] */
    /* JADX WARN: Removed duplicated region for block: B:135:0x036c A[Catch: all -> 0x0532, TryCatch #0 {all -> 0x0532, blocks: (B:3:0x0023, B:5:0x0033, B:7:0x003d, B:9:0x0046, B:11:0x0054, B:13:0x005b, B:15:0x0065, B:17:0x006f, B:19:0x007f, B:21:0x0086, B:48:0x0130, B:50:0x0151, B:52:0x0165, B:54:0x016c, B:56:0x017c, B:58:0x0183, B:60:0x0193, B:62:0x019c, B:64:0x01ab, B:66:0x01b2, B:68:0x01c2, B:70:0x01c9, B:72:0x01d8, B:74:0x01df, B:76:0x01ef, B:78:0x01f8, B:80:0x0207, B:82:0x0210, B:84:0x0220, B:86:0x0229, B:88:0x023a, B:90:0x0243, B:92:0x0254, B:94:0x025d, B:99:0x027c, B:101:0x0285, B:103:0x0296, B:105:0x029f, B:107:0x02b1, B:109:0x02ba, B:111:0x02cc, B:113:0x02d5, B:115:0x02e7, B:117:0x02f0, B:119:0x0301, B:121:0x030a, B:123:0x031c, B:125:0x0325, B:127:0x0338, B:129:0x0341, B:131:0x034b, B:141:0x0385, B:143:0x038e, B:150:0x03ad, B:152:0x03b7, B:154:0x03cf, B:156:0x03d8, B:158:0x03e4, B:160:0x03ec, B:161:0x03f1, B:163:0x03f7, B:165:0x0400, B:169:0x0410, B:170:0x0413, B:172:0x041c, B:173:0x042d, B:175:0x0436, B:176:0x0443, B:178:0x044c, B:179:0x045d, B:181:0x0466, B:183:0x0472, B:185:0x0478, B:186:0x047f, B:188:0x0488, B:189:0x0495, B:191:0x049f, B:192:0x04ad, B:194:0x04b6, B:195:0x04c3, B:197:0x04cc, B:201:0x04dc, B:203:0x04e1, B:205:0x04ea, B:206:0x04fb, B:208:0x0504, B:209:0x0515, B:211:0x0521, B:216:0x052b, B:153:0x03ca, B:144:0x0394, B:146:0x03a4, B:149:0x03aa, B:148:0x03a8, B:132:0x035d, B:134:0x0366, B:135:0x036c, B:137:0x037c, B:140:0x0382, B:139:0x0380, B:126:0x032b, B:122:0x030f, B:118:0x02f4, B:114:0x02da, B:110:0x02bf, B:106:0x02a4, B:102:0x0289, B:95:0x0265, B:97:0x0275, B:98:0x0279, B:91:0x0247, B:87:0x022d, B:83:0x0215, B:79:0x01fc, B:75:0x01e4, B:71:0x01cd, B:67:0x01b7, B:63:0x01a0, B:59:0x0188, B:55:0x0171, B:51:0x0156, B:23:0x009f, B:26:0x00ab, B:36:0x00d4, B:38:0x00da, B:43:0x00fe, B:44:0x0103, B:47:0x0111, B:30:0x00b6, B:35:0x00d0, B:31:0x00c0, B:18:0x0074), top: B:222:0x0023 }] */
    /* JADX WARN: Removed duplicated region for block: B:143:0x038e A[Catch: all -> 0x0532, TryCatch #0 {all -> 0x0532, blocks: (B:3:0x0023, B:5:0x0033, B:7:0x003d, B:9:0x0046, B:11:0x0054, B:13:0x005b, B:15:0x0065, B:17:0x006f, B:19:0x007f, B:21:0x0086, B:48:0x0130, B:50:0x0151, B:52:0x0165, B:54:0x016c, B:56:0x017c, B:58:0x0183, B:60:0x0193, B:62:0x019c, B:64:0x01ab, B:66:0x01b2, B:68:0x01c2, B:70:0x01c9, B:72:0x01d8, B:74:0x01df, B:76:0x01ef, B:78:0x01f8, B:80:0x0207, B:82:0x0210, B:84:0x0220, B:86:0x0229, B:88:0x023a, B:90:0x0243, B:92:0x0254, B:94:0x025d, B:99:0x027c, B:101:0x0285, B:103:0x0296, B:105:0x029f, B:107:0x02b1, B:109:0x02ba, B:111:0x02cc, B:113:0x02d5, B:115:0x02e7, B:117:0x02f0, B:119:0x0301, B:121:0x030a, B:123:0x031c, B:125:0x0325, B:127:0x0338, B:129:0x0341, B:131:0x034b, B:141:0x0385, B:143:0x038e, B:150:0x03ad, B:152:0x03b7, B:154:0x03cf, B:156:0x03d8, B:158:0x03e4, B:160:0x03ec, B:161:0x03f1, B:163:0x03f7, B:165:0x0400, B:169:0x0410, B:170:0x0413, B:172:0x041c, B:173:0x042d, B:175:0x0436, B:176:0x0443, B:178:0x044c, B:179:0x045d, B:181:0x0466, B:183:0x0472, B:185:0x0478, B:186:0x047f, B:188:0x0488, B:189:0x0495, B:191:0x049f, B:192:0x04ad, B:194:0x04b6, B:195:0x04c3, B:197:0x04cc, B:201:0x04dc, B:203:0x04e1, B:205:0x04ea, B:206:0x04fb, B:208:0x0504, B:209:0x0515, B:211:0x0521, B:216:0x052b, B:153:0x03ca, B:144:0x0394, B:146:0x03a4, B:149:0x03aa, B:148:0x03a8, B:132:0x035d, B:134:0x0366, B:135:0x036c, B:137:0x037c, B:140:0x0382, B:139:0x0380, B:126:0x032b, B:122:0x030f, B:118:0x02f4, B:114:0x02da, B:110:0x02bf, B:106:0x02a4, B:102:0x0289, B:95:0x0265, B:97:0x0275, B:98:0x0279, B:91:0x0247, B:87:0x022d, B:83:0x0215, B:79:0x01fc, B:75:0x01e4, B:71:0x01cd, B:67:0x01b7, B:63:0x01a0, B:59:0x0188, B:55:0x0171, B:51:0x0156, B:23:0x009f, B:26:0x00ab, B:36:0x00d4, B:38:0x00da, B:43:0x00fe, B:44:0x0103, B:47:0x0111, B:30:0x00b6, B:35:0x00d0, B:31:0x00c0, B:18:0x0074), top: B:222:0x0023 }] */
    /* JADX WARN: Removed duplicated region for block: B:144:0x0394 A[Catch: all -> 0x0532, TryCatch #0 {all -> 0x0532, blocks: (B:3:0x0023, B:5:0x0033, B:7:0x003d, B:9:0x0046, B:11:0x0054, B:13:0x005b, B:15:0x0065, B:17:0x006f, B:19:0x007f, B:21:0x0086, B:48:0x0130, B:50:0x0151, B:52:0x0165, B:54:0x016c, B:56:0x017c, B:58:0x0183, B:60:0x0193, B:62:0x019c, B:64:0x01ab, B:66:0x01b2, B:68:0x01c2, B:70:0x01c9, B:72:0x01d8, B:74:0x01df, B:76:0x01ef, B:78:0x01f8, B:80:0x0207, B:82:0x0210, B:84:0x0220, B:86:0x0229, B:88:0x023a, B:90:0x0243, B:92:0x0254, B:94:0x025d, B:99:0x027c, B:101:0x0285, B:103:0x0296, B:105:0x029f, B:107:0x02b1, B:109:0x02ba, B:111:0x02cc, B:113:0x02d5, B:115:0x02e7, B:117:0x02f0, B:119:0x0301, B:121:0x030a, B:123:0x031c, B:125:0x0325, B:127:0x0338, B:129:0x0341, B:131:0x034b, B:141:0x0385, B:143:0x038e, B:150:0x03ad, B:152:0x03b7, B:154:0x03cf, B:156:0x03d8, B:158:0x03e4, B:160:0x03ec, B:161:0x03f1, B:163:0x03f7, B:165:0x0400, B:169:0x0410, B:170:0x0413, B:172:0x041c, B:173:0x042d, B:175:0x0436, B:176:0x0443, B:178:0x044c, B:179:0x045d, B:181:0x0466, B:183:0x0472, B:185:0x0478, B:186:0x047f, B:188:0x0488, B:189:0x0495, B:191:0x049f, B:192:0x04ad, B:194:0x04b6, B:195:0x04c3, B:197:0x04cc, B:201:0x04dc, B:203:0x04e1, B:205:0x04ea, B:206:0x04fb, B:208:0x0504, B:209:0x0515, B:211:0x0521, B:216:0x052b, B:153:0x03ca, B:144:0x0394, B:146:0x03a4, B:149:0x03aa, B:148:0x03a8, B:132:0x035d, B:134:0x0366, B:135:0x036c, B:137:0x037c, B:140:0x0382, B:139:0x0380, B:126:0x032b, B:122:0x030f, B:118:0x02f4, B:114:0x02da, B:110:0x02bf, B:106:0x02a4, B:102:0x0289, B:95:0x0265, B:97:0x0275, B:98:0x0279, B:91:0x0247, B:87:0x022d, B:83:0x0215, B:79:0x01fc, B:75:0x01e4, B:71:0x01cd, B:67:0x01b7, B:63:0x01a0, B:59:0x0188, B:55:0x0171, B:51:0x0156, B:23:0x009f, B:26:0x00ab, B:36:0x00d4, B:38:0x00da, B:43:0x00fe, B:44:0x0103, B:47:0x0111, B:30:0x00b6, B:35:0x00d0, B:31:0x00c0, B:18:0x0074), top: B:222:0x0023 }] */
    /* JADX WARN: Removed duplicated region for block: B:152:0x03b7 A[Catch: all -> 0x0532, TryCatch #0 {all -> 0x0532, blocks: (B:3:0x0023, B:5:0x0033, B:7:0x003d, B:9:0x0046, B:11:0x0054, B:13:0x005b, B:15:0x0065, B:17:0x006f, B:19:0x007f, B:21:0x0086, B:48:0x0130, B:50:0x0151, B:52:0x0165, B:54:0x016c, B:56:0x017c, B:58:0x0183, B:60:0x0193, B:62:0x019c, B:64:0x01ab, B:66:0x01b2, B:68:0x01c2, B:70:0x01c9, B:72:0x01d8, B:74:0x01df, B:76:0x01ef, B:78:0x01f8, B:80:0x0207, B:82:0x0210, B:84:0x0220, B:86:0x0229, B:88:0x023a, B:90:0x0243, B:92:0x0254, B:94:0x025d, B:99:0x027c, B:101:0x0285, B:103:0x0296, B:105:0x029f, B:107:0x02b1, B:109:0x02ba, B:111:0x02cc, B:113:0x02d5, B:115:0x02e7, B:117:0x02f0, B:119:0x0301, B:121:0x030a, B:123:0x031c, B:125:0x0325, B:127:0x0338, B:129:0x0341, B:131:0x034b, B:141:0x0385, B:143:0x038e, B:150:0x03ad, B:152:0x03b7, B:154:0x03cf, B:156:0x03d8, B:158:0x03e4, B:160:0x03ec, B:161:0x03f1, B:163:0x03f7, B:165:0x0400, B:169:0x0410, B:170:0x0413, B:172:0x041c, B:173:0x042d, B:175:0x0436, B:176:0x0443, B:178:0x044c, B:179:0x045d, B:181:0x0466, B:183:0x0472, B:185:0x0478, B:186:0x047f, B:188:0x0488, B:189:0x0495, B:191:0x049f, B:192:0x04ad, B:194:0x04b6, B:195:0x04c3, B:197:0x04cc, B:201:0x04dc, B:203:0x04e1, B:205:0x04ea, B:206:0x04fb, B:208:0x0504, B:209:0x0515, B:211:0x0521, B:216:0x052b, B:153:0x03ca, B:144:0x0394, B:146:0x03a4, B:149:0x03aa, B:148:0x03a8, B:132:0x035d, B:134:0x0366, B:135:0x036c, B:137:0x037c, B:140:0x0382, B:139:0x0380, B:126:0x032b, B:122:0x030f, B:118:0x02f4, B:114:0x02da, B:110:0x02bf, B:106:0x02a4, B:102:0x0289, B:95:0x0265, B:97:0x0275, B:98:0x0279, B:91:0x0247, B:87:0x022d, B:83:0x0215, B:79:0x01fc, B:75:0x01e4, B:71:0x01cd, B:67:0x01b7, B:63:0x01a0, B:59:0x0188, B:55:0x0171, B:51:0x0156, B:23:0x009f, B:26:0x00ab, B:36:0x00d4, B:38:0x00da, B:43:0x00fe, B:44:0x0103, B:47:0x0111, B:30:0x00b6, B:35:0x00d0, B:31:0x00c0, B:18:0x0074), top: B:222:0x0023 }] */
    /* JADX WARN: Removed duplicated region for block: B:153:0x03ca A[Catch: all -> 0x0532, TryCatch #0 {all -> 0x0532, blocks: (B:3:0x0023, B:5:0x0033, B:7:0x003d, B:9:0x0046, B:11:0x0054, B:13:0x005b, B:15:0x0065, B:17:0x006f, B:19:0x007f, B:21:0x0086, B:48:0x0130, B:50:0x0151, B:52:0x0165, B:54:0x016c, B:56:0x017c, B:58:0x0183, B:60:0x0193, B:62:0x019c, B:64:0x01ab, B:66:0x01b2, B:68:0x01c2, B:70:0x01c9, B:72:0x01d8, B:74:0x01df, B:76:0x01ef, B:78:0x01f8, B:80:0x0207, B:82:0x0210, B:84:0x0220, B:86:0x0229, B:88:0x023a, B:90:0x0243, B:92:0x0254, B:94:0x025d, B:99:0x027c, B:101:0x0285, B:103:0x0296, B:105:0x029f, B:107:0x02b1, B:109:0x02ba, B:111:0x02cc, B:113:0x02d5, B:115:0x02e7, B:117:0x02f0, B:119:0x0301, B:121:0x030a, B:123:0x031c, B:125:0x0325, B:127:0x0338, B:129:0x0341, B:131:0x034b, B:141:0x0385, B:143:0x038e, B:150:0x03ad, B:152:0x03b7, B:154:0x03cf, B:156:0x03d8, B:158:0x03e4, B:160:0x03ec, B:161:0x03f1, B:163:0x03f7, B:165:0x0400, B:169:0x0410, B:170:0x0413, B:172:0x041c, B:173:0x042d, B:175:0x0436, B:176:0x0443, B:178:0x044c, B:179:0x045d, B:181:0x0466, B:183:0x0472, B:185:0x0478, B:186:0x047f, B:188:0x0488, B:189:0x0495, B:191:0x049f, B:192:0x04ad, B:194:0x04b6, B:195:0x04c3, B:197:0x04cc, B:201:0x04dc, B:203:0x04e1, B:205:0x04ea, B:206:0x04fb, B:208:0x0504, B:209:0x0515, B:211:0x0521, B:216:0x052b, B:153:0x03ca, B:144:0x0394, B:146:0x03a4, B:149:0x03aa, B:148:0x03a8, B:132:0x035d, B:134:0x0366, B:135:0x036c, B:137:0x037c, B:140:0x0382, B:139:0x0380, B:126:0x032b, B:122:0x030f, B:118:0x02f4, B:114:0x02da, B:110:0x02bf, B:106:0x02a4, B:102:0x0289, B:95:0x0265, B:97:0x0275, B:98:0x0279, B:91:0x0247, B:87:0x022d, B:83:0x0215, B:79:0x01fc, B:75:0x01e4, B:71:0x01cd, B:67:0x01b7, B:63:0x01a0, B:59:0x0188, B:55:0x0171, B:51:0x0156, B:23:0x009f, B:26:0x00ab, B:36:0x00d4, B:38:0x00da, B:43:0x00fe, B:44:0x0103, B:47:0x0111, B:30:0x00b6, B:35:0x00d0, B:31:0x00c0, B:18:0x0074), top: B:222:0x0023 }] */
    /* JADX WARN: Removed duplicated region for block: B:156:0x03d8 A[Catch: all -> 0x0532, TryCatch #0 {all -> 0x0532, blocks: (B:3:0x0023, B:5:0x0033, B:7:0x003d, B:9:0x0046, B:11:0x0054, B:13:0x005b, B:15:0x0065, B:17:0x006f, B:19:0x007f, B:21:0x0086, B:48:0x0130, B:50:0x0151, B:52:0x0165, B:54:0x016c, B:56:0x017c, B:58:0x0183, B:60:0x0193, B:62:0x019c, B:64:0x01ab, B:66:0x01b2, B:68:0x01c2, B:70:0x01c9, B:72:0x01d8, B:74:0x01df, B:76:0x01ef, B:78:0x01f8, B:80:0x0207, B:82:0x0210, B:84:0x0220, B:86:0x0229, B:88:0x023a, B:90:0x0243, B:92:0x0254, B:94:0x025d, B:99:0x027c, B:101:0x0285, B:103:0x0296, B:105:0x029f, B:107:0x02b1, B:109:0x02ba, B:111:0x02cc, B:113:0x02d5, B:115:0x02e7, B:117:0x02f0, B:119:0x0301, B:121:0x030a, B:123:0x031c, B:125:0x0325, B:127:0x0338, B:129:0x0341, B:131:0x034b, B:141:0x0385, B:143:0x038e, B:150:0x03ad, B:152:0x03b7, B:154:0x03cf, B:156:0x03d8, B:158:0x03e4, B:160:0x03ec, B:161:0x03f1, B:163:0x03f7, B:165:0x0400, B:169:0x0410, B:170:0x0413, B:172:0x041c, B:173:0x042d, B:175:0x0436, B:176:0x0443, B:178:0x044c, B:179:0x045d, B:181:0x0466, B:183:0x0472, B:185:0x0478, B:186:0x047f, B:188:0x0488, B:189:0x0495, B:191:0x049f, B:192:0x04ad, B:194:0x04b6, B:195:0x04c3, B:197:0x04cc, B:201:0x04dc, B:203:0x04e1, B:205:0x04ea, B:206:0x04fb, B:208:0x0504, B:209:0x0515, B:211:0x0521, B:216:0x052b, B:153:0x03ca, B:144:0x0394, B:146:0x03a4, B:149:0x03aa, B:148:0x03a8, B:132:0x035d, B:134:0x0366, B:135:0x036c, B:137:0x037c, B:140:0x0382, B:139:0x0380, B:126:0x032b, B:122:0x030f, B:118:0x02f4, B:114:0x02da, B:110:0x02bf, B:106:0x02a4, B:102:0x0289, B:95:0x0265, B:97:0x0275, B:98:0x0279, B:91:0x0247, B:87:0x022d, B:83:0x0215, B:79:0x01fc, B:75:0x01e4, B:71:0x01cd, B:67:0x01b7, B:63:0x01a0, B:59:0x0188, B:55:0x0171, B:51:0x0156, B:23:0x009f, B:26:0x00ab, B:36:0x00d4, B:38:0x00da, B:43:0x00fe, B:44:0x0103, B:47:0x0111, B:30:0x00b6, B:35:0x00d0, B:31:0x00c0, B:18:0x0074), top: B:222:0x0023 }] */
    /* JADX WARN: Removed duplicated region for block: B:162:0x03f6  */
    /* JADX WARN: Removed duplicated region for block: B:165:0x0400 A[Catch: all -> 0x0532, TryCatch #0 {all -> 0x0532, blocks: (B:3:0x0023, B:5:0x0033, B:7:0x003d, B:9:0x0046, B:11:0x0054, B:13:0x005b, B:15:0x0065, B:17:0x006f, B:19:0x007f, B:21:0x0086, B:48:0x0130, B:50:0x0151, B:52:0x0165, B:54:0x016c, B:56:0x017c, B:58:0x0183, B:60:0x0193, B:62:0x019c, B:64:0x01ab, B:66:0x01b2, B:68:0x01c2, B:70:0x01c9, B:72:0x01d8, B:74:0x01df, B:76:0x01ef, B:78:0x01f8, B:80:0x0207, B:82:0x0210, B:84:0x0220, B:86:0x0229, B:88:0x023a, B:90:0x0243, B:92:0x0254, B:94:0x025d, B:99:0x027c, B:101:0x0285, B:103:0x0296, B:105:0x029f, B:107:0x02b1, B:109:0x02ba, B:111:0x02cc, B:113:0x02d5, B:115:0x02e7, B:117:0x02f0, B:119:0x0301, B:121:0x030a, B:123:0x031c, B:125:0x0325, B:127:0x0338, B:129:0x0341, B:131:0x034b, B:141:0x0385, B:143:0x038e, B:150:0x03ad, B:152:0x03b7, B:154:0x03cf, B:156:0x03d8, B:158:0x03e4, B:160:0x03ec, B:161:0x03f1, B:163:0x03f7, B:165:0x0400, B:169:0x0410, B:170:0x0413, B:172:0x041c, B:173:0x042d, B:175:0x0436, B:176:0x0443, B:178:0x044c, B:179:0x045d, B:181:0x0466, B:183:0x0472, B:185:0x0478, B:186:0x047f, B:188:0x0488, B:189:0x0495, B:191:0x049f, B:192:0x04ad, B:194:0x04b6, B:195:0x04c3, B:197:0x04cc, B:201:0x04dc, B:203:0x04e1, B:205:0x04ea, B:206:0x04fb, B:208:0x0504, B:209:0x0515, B:211:0x0521, B:216:0x052b, B:153:0x03ca, B:144:0x0394, B:146:0x03a4, B:149:0x03aa, B:148:0x03a8, B:132:0x035d, B:134:0x0366, B:135:0x036c, B:137:0x037c, B:140:0x0382, B:139:0x0380, B:126:0x032b, B:122:0x030f, B:118:0x02f4, B:114:0x02da, B:110:0x02bf, B:106:0x02a4, B:102:0x0289, B:95:0x0265, B:97:0x0275, B:98:0x0279, B:91:0x0247, B:87:0x022d, B:83:0x0215, B:79:0x01fc, B:75:0x01e4, B:71:0x01cd, B:67:0x01b7, B:63:0x01a0, B:59:0x0188, B:55:0x0171, B:51:0x0156, B:23:0x009f, B:26:0x00ab, B:36:0x00d4, B:38:0x00da, B:43:0x00fe, B:44:0x0103, B:47:0x0111, B:30:0x00b6, B:35:0x00d0, B:31:0x00c0, B:18:0x0074), top: B:222:0x0023 }] */
    /* JADX WARN: Removed duplicated region for block: B:172:0x041c A[Catch: all -> 0x0532, TryCatch #0 {all -> 0x0532, blocks: (B:3:0x0023, B:5:0x0033, B:7:0x003d, B:9:0x0046, B:11:0x0054, B:13:0x005b, B:15:0x0065, B:17:0x006f, B:19:0x007f, B:21:0x0086, B:48:0x0130, B:50:0x0151, B:52:0x0165, B:54:0x016c, B:56:0x017c, B:58:0x0183, B:60:0x0193, B:62:0x019c, B:64:0x01ab, B:66:0x01b2, B:68:0x01c2, B:70:0x01c9, B:72:0x01d8, B:74:0x01df, B:76:0x01ef, B:78:0x01f8, B:80:0x0207, B:82:0x0210, B:84:0x0220, B:86:0x0229, B:88:0x023a, B:90:0x0243, B:92:0x0254, B:94:0x025d, B:99:0x027c, B:101:0x0285, B:103:0x0296, B:105:0x029f, B:107:0x02b1, B:109:0x02ba, B:111:0x02cc, B:113:0x02d5, B:115:0x02e7, B:117:0x02f0, B:119:0x0301, B:121:0x030a, B:123:0x031c, B:125:0x0325, B:127:0x0338, B:129:0x0341, B:131:0x034b, B:141:0x0385, B:143:0x038e, B:150:0x03ad, B:152:0x03b7, B:154:0x03cf, B:156:0x03d8, B:158:0x03e4, B:160:0x03ec, B:161:0x03f1, B:163:0x03f7, B:165:0x0400, B:169:0x0410, B:170:0x0413, B:172:0x041c, B:173:0x042d, B:175:0x0436, B:176:0x0443, B:178:0x044c, B:179:0x045d, B:181:0x0466, B:183:0x0472, B:185:0x0478, B:186:0x047f, B:188:0x0488, B:189:0x0495, B:191:0x049f, B:192:0x04ad, B:194:0x04b6, B:195:0x04c3, B:197:0x04cc, B:201:0x04dc, B:203:0x04e1, B:205:0x04ea, B:206:0x04fb, B:208:0x0504, B:209:0x0515, B:211:0x0521, B:216:0x052b, B:153:0x03ca, B:144:0x0394, B:146:0x03a4, B:149:0x03aa, B:148:0x03a8, B:132:0x035d, B:134:0x0366, B:135:0x036c, B:137:0x037c, B:140:0x0382, B:139:0x0380, B:126:0x032b, B:122:0x030f, B:118:0x02f4, B:114:0x02da, B:110:0x02bf, B:106:0x02a4, B:102:0x0289, B:95:0x0265, B:97:0x0275, B:98:0x0279, B:91:0x0247, B:87:0x022d, B:83:0x0215, B:79:0x01fc, B:75:0x01e4, B:71:0x01cd, B:67:0x01b7, B:63:0x01a0, B:59:0x0188, B:55:0x0171, B:51:0x0156, B:23:0x009f, B:26:0x00ab, B:36:0x00d4, B:38:0x00da, B:43:0x00fe, B:44:0x0103, B:47:0x0111, B:30:0x00b6, B:35:0x00d0, B:31:0x00c0, B:18:0x0074), top: B:222:0x0023 }] */
    /* JADX WARN: Removed duplicated region for block: B:175:0x0436 A[Catch: all -> 0x0532, TryCatch #0 {all -> 0x0532, blocks: (B:3:0x0023, B:5:0x0033, B:7:0x003d, B:9:0x0046, B:11:0x0054, B:13:0x005b, B:15:0x0065, B:17:0x006f, B:19:0x007f, B:21:0x0086, B:48:0x0130, B:50:0x0151, B:52:0x0165, B:54:0x016c, B:56:0x017c, B:58:0x0183, B:60:0x0193, B:62:0x019c, B:64:0x01ab, B:66:0x01b2, B:68:0x01c2, B:70:0x01c9, B:72:0x01d8, B:74:0x01df, B:76:0x01ef, B:78:0x01f8, B:80:0x0207, B:82:0x0210, B:84:0x0220, B:86:0x0229, B:88:0x023a, B:90:0x0243, B:92:0x0254, B:94:0x025d, B:99:0x027c, B:101:0x0285, B:103:0x0296, B:105:0x029f, B:107:0x02b1, B:109:0x02ba, B:111:0x02cc, B:113:0x02d5, B:115:0x02e7, B:117:0x02f0, B:119:0x0301, B:121:0x030a, B:123:0x031c, B:125:0x0325, B:127:0x0338, B:129:0x0341, B:131:0x034b, B:141:0x0385, B:143:0x038e, B:150:0x03ad, B:152:0x03b7, B:154:0x03cf, B:156:0x03d8, B:158:0x03e4, B:160:0x03ec, B:161:0x03f1, B:163:0x03f7, B:165:0x0400, B:169:0x0410, B:170:0x0413, B:172:0x041c, B:173:0x042d, B:175:0x0436, B:176:0x0443, B:178:0x044c, B:179:0x045d, B:181:0x0466, B:183:0x0472, B:185:0x0478, B:186:0x047f, B:188:0x0488, B:189:0x0495, B:191:0x049f, B:192:0x04ad, B:194:0x04b6, B:195:0x04c3, B:197:0x04cc, B:201:0x04dc, B:203:0x04e1, B:205:0x04ea, B:206:0x04fb, B:208:0x0504, B:209:0x0515, B:211:0x0521, B:216:0x052b, B:153:0x03ca, B:144:0x0394, B:146:0x03a4, B:149:0x03aa, B:148:0x03a8, B:132:0x035d, B:134:0x0366, B:135:0x036c, B:137:0x037c, B:140:0x0382, B:139:0x0380, B:126:0x032b, B:122:0x030f, B:118:0x02f4, B:114:0x02da, B:110:0x02bf, B:106:0x02a4, B:102:0x0289, B:95:0x0265, B:97:0x0275, B:98:0x0279, B:91:0x0247, B:87:0x022d, B:83:0x0215, B:79:0x01fc, B:75:0x01e4, B:71:0x01cd, B:67:0x01b7, B:63:0x01a0, B:59:0x0188, B:55:0x0171, B:51:0x0156, B:23:0x009f, B:26:0x00ab, B:36:0x00d4, B:38:0x00da, B:43:0x00fe, B:44:0x0103, B:47:0x0111, B:30:0x00b6, B:35:0x00d0, B:31:0x00c0, B:18:0x0074), top: B:222:0x0023 }] */
    /* JADX WARN: Removed duplicated region for block: B:178:0x044c A[Catch: all -> 0x0532, TryCatch #0 {all -> 0x0532, blocks: (B:3:0x0023, B:5:0x0033, B:7:0x003d, B:9:0x0046, B:11:0x0054, B:13:0x005b, B:15:0x0065, B:17:0x006f, B:19:0x007f, B:21:0x0086, B:48:0x0130, B:50:0x0151, B:52:0x0165, B:54:0x016c, B:56:0x017c, B:58:0x0183, B:60:0x0193, B:62:0x019c, B:64:0x01ab, B:66:0x01b2, B:68:0x01c2, B:70:0x01c9, B:72:0x01d8, B:74:0x01df, B:76:0x01ef, B:78:0x01f8, B:80:0x0207, B:82:0x0210, B:84:0x0220, B:86:0x0229, B:88:0x023a, B:90:0x0243, B:92:0x0254, B:94:0x025d, B:99:0x027c, B:101:0x0285, B:103:0x0296, B:105:0x029f, B:107:0x02b1, B:109:0x02ba, B:111:0x02cc, B:113:0x02d5, B:115:0x02e7, B:117:0x02f0, B:119:0x0301, B:121:0x030a, B:123:0x031c, B:125:0x0325, B:127:0x0338, B:129:0x0341, B:131:0x034b, B:141:0x0385, B:143:0x038e, B:150:0x03ad, B:152:0x03b7, B:154:0x03cf, B:156:0x03d8, B:158:0x03e4, B:160:0x03ec, B:161:0x03f1, B:163:0x03f7, B:165:0x0400, B:169:0x0410, B:170:0x0413, B:172:0x041c, B:173:0x042d, B:175:0x0436, B:176:0x0443, B:178:0x044c, B:179:0x045d, B:181:0x0466, B:183:0x0472, B:185:0x0478, B:186:0x047f, B:188:0x0488, B:189:0x0495, B:191:0x049f, B:192:0x04ad, B:194:0x04b6, B:195:0x04c3, B:197:0x04cc, B:201:0x04dc, B:203:0x04e1, B:205:0x04ea, B:206:0x04fb, B:208:0x0504, B:209:0x0515, B:211:0x0521, B:216:0x052b, B:153:0x03ca, B:144:0x0394, B:146:0x03a4, B:149:0x03aa, B:148:0x03a8, B:132:0x035d, B:134:0x0366, B:135:0x036c, B:137:0x037c, B:140:0x0382, B:139:0x0380, B:126:0x032b, B:122:0x030f, B:118:0x02f4, B:114:0x02da, B:110:0x02bf, B:106:0x02a4, B:102:0x0289, B:95:0x0265, B:97:0x0275, B:98:0x0279, B:91:0x0247, B:87:0x022d, B:83:0x0215, B:79:0x01fc, B:75:0x01e4, B:71:0x01cd, B:67:0x01b7, B:63:0x01a0, B:59:0x0188, B:55:0x0171, B:51:0x0156, B:23:0x009f, B:26:0x00ab, B:36:0x00d4, B:38:0x00da, B:43:0x00fe, B:44:0x0103, B:47:0x0111, B:30:0x00b6, B:35:0x00d0, B:31:0x00c0, B:18:0x0074), top: B:222:0x0023 }] */
    /* JADX WARN: Removed duplicated region for block: B:188:0x0488 A[Catch: all -> 0x0532, TryCatch #0 {all -> 0x0532, blocks: (B:3:0x0023, B:5:0x0033, B:7:0x003d, B:9:0x0046, B:11:0x0054, B:13:0x005b, B:15:0x0065, B:17:0x006f, B:19:0x007f, B:21:0x0086, B:48:0x0130, B:50:0x0151, B:52:0x0165, B:54:0x016c, B:56:0x017c, B:58:0x0183, B:60:0x0193, B:62:0x019c, B:64:0x01ab, B:66:0x01b2, B:68:0x01c2, B:70:0x01c9, B:72:0x01d8, B:74:0x01df, B:76:0x01ef, B:78:0x01f8, B:80:0x0207, B:82:0x0210, B:84:0x0220, B:86:0x0229, B:88:0x023a, B:90:0x0243, B:92:0x0254, B:94:0x025d, B:99:0x027c, B:101:0x0285, B:103:0x0296, B:105:0x029f, B:107:0x02b1, B:109:0x02ba, B:111:0x02cc, B:113:0x02d5, B:115:0x02e7, B:117:0x02f0, B:119:0x0301, B:121:0x030a, B:123:0x031c, B:125:0x0325, B:127:0x0338, B:129:0x0341, B:131:0x034b, B:141:0x0385, B:143:0x038e, B:150:0x03ad, B:152:0x03b7, B:154:0x03cf, B:156:0x03d8, B:158:0x03e4, B:160:0x03ec, B:161:0x03f1, B:163:0x03f7, B:165:0x0400, B:169:0x0410, B:170:0x0413, B:172:0x041c, B:173:0x042d, B:175:0x0436, B:176:0x0443, B:178:0x044c, B:179:0x045d, B:181:0x0466, B:183:0x0472, B:185:0x0478, B:186:0x047f, B:188:0x0488, B:189:0x0495, B:191:0x049f, B:192:0x04ad, B:194:0x04b6, B:195:0x04c3, B:197:0x04cc, B:201:0x04dc, B:203:0x04e1, B:205:0x04ea, B:206:0x04fb, B:208:0x0504, B:209:0x0515, B:211:0x0521, B:216:0x052b, B:153:0x03ca, B:144:0x0394, B:146:0x03a4, B:149:0x03aa, B:148:0x03a8, B:132:0x035d, B:134:0x0366, B:135:0x036c, B:137:0x037c, B:140:0x0382, B:139:0x0380, B:126:0x032b, B:122:0x030f, B:118:0x02f4, B:114:0x02da, B:110:0x02bf, B:106:0x02a4, B:102:0x0289, B:95:0x0265, B:97:0x0275, B:98:0x0279, B:91:0x0247, B:87:0x022d, B:83:0x0215, B:79:0x01fc, B:75:0x01e4, B:71:0x01cd, B:67:0x01b7, B:63:0x01a0, B:59:0x0188, B:55:0x0171, B:51:0x0156, B:23:0x009f, B:26:0x00ab, B:36:0x00d4, B:38:0x00da, B:43:0x00fe, B:44:0x0103, B:47:0x0111, B:30:0x00b6, B:35:0x00d0, B:31:0x00c0, B:18:0x0074), top: B:222:0x0023 }] */
    /* JADX WARN: Removed duplicated region for block: B:191:0x049f A[Catch: all -> 0x0532, TryCatch #0 {all -> 0x0532, blocks: (B:3:0x0023, B:5:0x0033, B:7:0x003d, B:9:0x0046, B:11:0x0054, B:13:0x005b, B:15:0x0065, B:17:0x006f, B:19:0x007f, B:21:0x0086, B:48:0x0130, B:50:0x0151, B:52:0x0165, B:54:0x016c, B:56:0x017c, B:58:0x0183, B:60:0x0193, B:62:0x019c, B:64:0x01ab, B:66:0x01b2, B:68:0x01c2, B:70:0x01c9, B:72:0x01d8, B:74:0x01df, B:76:0x01ef, B:78:0x01f8, B:80:0x0207, B:82:0x0210, B:84:0x0220, B:86:0x0229, B:88:0x023a, B:90:0x0243, B:92:0x0254, B:94:0x025d, B:99:0x027c, B:101:0x0285, B:103:0x0296, B:105:0x029f, B:107:0x02b1, B:109:0x02ba, B:111:0x02cc, B:113:0x02d5, B:115:0x02e7, B:117:0x02f0, B:119:0x0301, B:121:0x030a, B:123:0x031c, B:125:0x0325, B:127:0x0338, B:129:0x0341, B:131:0x034b, B:141:0x0385, B:143:0x038e, B:150:0x03ad, B:152:0x03b7, B:154:0x03cf, B:156:0x03d8, B:158:0x03e4, B:160:0x03ec, B:161:0x03f1, B:163:0x03f7, B:165:0x0400, B:169:0x0410, B:170:0x0413, B:172:0x041c, B:173:0x042d, B:175:0x0436, B:176:0x0443, B:178:0x044c, B:179:0x045d, B:181:0x0466, B:183:0x0472, B:185:0x0478, B:186:0x047f, B:188:0x0488, B:189:0x0495, B:191:0x049f, B:192:0x04ad, B:194:0x04b6, B:195:0x04c3, B:197:0x04cc, B:201:0x04dc, B:203:0x04e1, B:205:0x04ea, B:206:0x04fb, B:208:0x0504, B:209:0x0515, B:211:0x0521, B:216:0x052b, B:153:0x03ca, B:144:0x0394, B:146:0x03a4, B:149:0x03aa, B:148:0x03a8, B:132:0x035d, B:134:0x0366, B:135:0x036c, B:137:0x037c, B:140:0x0382, B:139:0x0380, B:126:0x032b, B:122:0x030f, B:118:0x02f4, B:114:0x02da, B:110:0x02bf, B:106:0x02a4, B:102:0x0289, B:95:0x0265, B:97:0x0275, B:98:0x0279, B:91:0x0247, B:87:0x022d, B:83:0x0215, B:79:0x01fc, B:75:0x01e4, B:71:0x01cd, B:67:0x01b7, B:63:0x01a0, B:59:0x0188, B:55:0x0171, B:51:0x0156, B:23:0x009f, B:26:0x00ab, B:36:0x00d4, B:38:0x00da, B:43:0x00fe, B:44:0x0103, B:47:0x0111, B:30:0x00b6, B:35:0x00d0, B:31:0x00c0, B:18:0x0074), top: B:222:0x0023 }] */
    /* JADX WARN: Removed duplicated region for block: B:194:0x04b6 A[Catch: all -> 0x0532, TryCatch #0 {all -> 0x0532, blocks: (B:3:0x0023, B:5:0x0033, B:7:0x003d, B:9:0x0046, B:11:0x0054, B:13:0x005b, B:15:0x0065, B:17:0x006f, B:19:0x007f, B:21:0x0086, B:48:0x0130, B:50:0x0151, B:52:0x0165, B:54:0x016c, B:56:0x017c, B:58:0x0183, B:60:0x0193, B:62:0x019c, B:64:0x01ab, B:66:0x01b2, B:68:0x01c2, B:70:0x01c9, B:72:0x01d8, B:74:0x01df, B:76:0x01ef, B:78:0x01f8, B:80:0x0207, B:82:0x0210, B:84:0x0220, B:86:0x0229, B:88:0x023a, B:90:0x0243, B:92:0x0254, B:94:0x025d, B:99:0x027c, B:101:0x0285, B:103:0x0296, B:105:0x029f, B:107:0x02b1, B:109:0x02ba, B:111:0x02cc, B:113:0x02d5, B:115:0x02e7, B:117:0x02f0, B:119:0x0301, B:121:0x030a, B:123:0x031c, B:125:0x0325, B:127:0x0338, B:129:0x0341, B:131:0x034b, B:141:0x0385, B:143:0x038e, B:150:0x03ad, B:152:0x03b7, B:154:0x03cf, B:156:0x03d8, B:158:0x03e4, B:160:0x03ec, B:161:0x03f1, B:163:0x03f7, B:165:0x0400, B:169:0x0410, B:170:0x0413, B:172:0x041c, B:173:0x042d, B:175:0x0436, B:176:0x0443, B:178:0x044c, B:179:0x045d, B:181:0x0466, B:183:0x0472, B:185:0x0478, B:186:0x047f, B:188:0x0488, B:189:0x0495, B:191:0x049f, B:192:0x04ad, B:194:0x04b6, B:195:0x04c3, B:197:0x04cc, B:201:0x04dc, B:203:0x04e1, B:205:0x04ea, B:206:0x04fb, B:208:0x0504, B:209:0x0515, B:211:0x0521, B:216:0x052b, B:153:0x03ca, B:144:0x0394, B:146:0x03a4, B:149:0x03aa, B:148:0x03a8, B:132:0x035d, B:134:0x0366, B:135:0x036c, B:137:0x037c, B:140:0x0382, B:139:0x0380, B:126:0x032b, B:122:0x030f, B:118:0x02f4, B:114:0x02da, B:110:0x02bf, B:106:0x02a4, B:102:0x0289, B:95:0x0265, B:97:0x0275, B:98:0x0279, B:91:0x0247, B:87:0x022d, B:83:0x0215, B:79:0x01fc, B:75:0x01e4, B:71:0x01cd, B:67:0x01b7, B:63:0x01a0, B:59:0x0188, B:55:0x0171, B:51:0x0156, B:23:0x009f, B:26:0x00ab, B:36:0x00d4, B:38:0x00da, B:43:0x00fe, B:44:0x0103, B:47:0x0111, B:30:0x00b6, B:35:0x00d0, B:31:0x00c0, B:18:0x0074), top: B:222:0x0023 }] */
    /* JADX WARN: Removed duplicated region for block: B:197:0x04cc A[Catch: all -> 0x0532, TryCatch #0 {all -> 0x0532, blocks: (B:3:0x0023, B:5:0x0033, B:7:0x003d, B:9:0x0046, B:11:0x0054, B:13:0x005b, B:15:0x0065, B:17:0x006f, B:19:0x007f, B:21:0x0086, B:48:0x0130, B:50:0x0151, B:52:0x0165, B:54:0x016c, B:56:0x017c, B:58:0x0183, B:60:0x0193, B:62:0x019c, B:64:0x01ab, B:66:0x01b2, B:68:0x01c2, B:70:0x01c9, B:72:0x01d8, B:74:0x01df, B:76:0x01ef, B:78:0x01f8, B:80:0x0207, B:82:0x0210, B:84:0x0220, B:86:0x0229, B:88:0x023a, B:90:0x0243, B:92:0x0254, B:94:0x025d, B:99:0x027c, B:101:0x0285, B:103:0x0296, B:105:0x029f, B:107:0x02b1, B:109:0x02ba, B:111:0x02cc, B:113:0x02d5, B:115:0x02e7, B:117:0x02f0, B:119:0x0301, B:121:0x030a, B:123:0x031c, B:125:0x0325, B:127:0x0338, B:129:0x0341, B:131:0x034b, B:141:0x0385, B:143:0x038e, B:150:0x03ad, B:152:0x03b7, B:154:0x03cf, B:156:0x03d8, B:158:0x03e4, B:160:0x03ec, B:161:0x03f1, B:163:0x03f7, B:165:0x0400, B:169:0x0410, B:170:0x0413, B:172:0x041c, B:173:0x042d, B:175:0x0436, B:176:0x0443, B:178:0x044c, B:179:0x045d, B:181:0x0466, B:183:0x0472, B:185:0x0478, B:186:0x047f, B:188:0x0488, B:189:0x0495, B:191:0x049f, B:192:0x04ad, B:194:0x04b6, B:195:0x04c3, B:197:0x04cc, B:201:0x04dc, B:203:0x04e1, B:205:0x04ea, B:206:0x04fb, B:208:0x0504, B:209:0x0515, B:211:0x0521, B:216:0x052b, B:153:0x03ca, B:144:0x0394, B:146:0x03a4, B:149:0x03aa, B:148:0x03a8, B:132:0x035d, B:134:0x0366, B:135:0x036c, B:137:0x037c, B:140:0x0382, B:139:0x0380, B:126:0x032b, B:122:0x030f, B:118:0x02f4, B:114:0x02da, B:110:0x02bf, B:106:0x02a4, B:102:0x0289, B:95:0x0265, B:97:0x0275, B:98:0x0279, B:91:0x0247, B:87:0x022d, B:83:0x0215, B:79:0x01fc, B:75:0x01e4, B:71:0x01cd, B:67:0x01b7, B:63:0x01a0, B:59:0x0188, B:55:0x0171, B:51:0x0156, B:23:0x009f, B:26:0x00ab, B:36:0x00d4, B:38:0x00da, B:43:0x00fe, B:44:0x0103, B:47:0x0111, B:30:0x00b6, B:35:0x00d0, B:31:0x00c0, B:18:0x0074), top: B:222:0x0023 }] */
    /* JADX WARN: Removed duplicated region for block: B:202:0x04e0  */
    /* JADX WARN: Removed duplicated region for block: B:205:0x04ea A[Catch: all -> 0x0532, TryCatch #0 {all -> 0x0532, blocks: (B:3:0x0023, B:5:0x0033, B:7:0x003d, B:9:0x0046, B:11:0x0054, B:13:0x005b, B:15:0x0065, B:17:0x006f, B:19:0x007f, B:21:0x0086, B:48:0x0130, B:50:0x0151, B:52:0x0165, B:54:0x016c, B:56:0x017c, B:58:0x0183, B:60:0x0193, B:62:0x019c, B:64:0x01ab, B:66:0x01b2, B:68:0x01c2, B:70:0x01c9, B:72:0x01d8, B:74:0x01df, B:76:0x01ef, B:78:0x01f8, B:80:0x0207, B:82:0x0210, B:84:0x0220, B:86:0x0229, B:88:0x023a, B:90:0x0243, B:92:0x0254, B:94:0x025d, B:99:0x027c, B:101:0x0285, B:103:0x0296, B:105:0x029f, B:107:0x02b1, B:109:0x02ba, B:111:0x02cc, B:113:0x02d5, B:115:0x02e7, B:117:0x02f0, B:119:0x0301, B:121:0x030a, B:123:0x031c, B:125:0x0325, B:127:0x0338, B:129:0x0341, B:131:0x034b, B:141:0x0385, B:143:0x038e, B:150:0x03ad, B:152:0x03b7, B:154:0x03cf, B:156:0x03d8, B:158:0x03e4, B:160:0x03ec, B:161:0x03f1, B:163:0x03f7, B:165:0x0400, B:169:0x0410, B:170:0x0413, B:172:0x041c, B:173:0x042d, B:175:0x0436, B:176:0x0443, B:178:0x044c, B:179:0x045d, B:181:0x0466, B:183:0x0472, B:185:0x0478, B:186:0x047f, B:188:0x0488, B:189:0x0495, B:191:0x049f, B:192:0x04ad, B:194:0x04b6, B:195:0x04c3, B:197:0x04cc, B:201:0x04dc, B:203:0x04e1, B:205:0x04ea, B:206:0x04fb, B:208:0x0504, B:209:0x0515, B:211:0x0521, B:216:0x052b, B:153:0x03ca, B:144:0x0394, B:146:0x03a4, B:149:0x03aa, B:148:0x03a8, B:132:0x035d, B:134:0x0366, B:135:0x036c, B:137:0x037c, B:140:0x0382, B:139:0x0380, B:126:0x032b, B:122:0x030f, B:118:0x02f4, B:114:0x02da, B:110:0x02bf, B:106:0x02a4, B:102:0x0289, B:95:0x0265, B:97:0x0275, B:98:0x0279, B:91:0x0247, B:87:0x022d, B:83:0x0215, B:79:0x01fc, B:75:0x01e4, B:71:0x01cd, B:67:0x01b7, B:63:0x01a0, B:59:0x0188, B:55:0x0171, B:51:0x0156, B:23:0x009f, B:26:0x00ab, B:36:0x00d4, B:38:0x00da, B:43:0x00fe, B:44:0x0103, B:47:0x0111, B:30:0x00b6, B:35:0x00d0, B:31:0x00c0, B:18:0x0074), top: B:222:0x0023 }] */
    /* JADX WARN: Removed duplicated region for block: B:208:0x0504 A[Catch: all -> 0x0532, TryCatch #0 {all -> 0x0532, blocks: (B:3:0x0023, B:5:0x0033, B:7:0x003d, B:9:0x0046, B:11:0x0054, B:13:0x005b, B:15:0x0065, B:17:0x006f, B:19:0x007f, B:21:0x0086, B:48:0x0130, B:50:0x0151, B:52:0x0165, B:54:0x016c, B:56:0x017c, B:58:0x0183, B:60:0x0193, B:62:0x019c, B:64:0x01ab, B:66:0x01b2, B:68:0x01c2, B:70:0x01c9, B:72:0x01d8, B:74:0x01df, B:76:0x01ef, B:78:0x01f8, B:80:0x0207, B:82:0x0210, B:84:0x0220, B:86:0x0229, B:88:0x023a, B:90:0x0243, B:92:0x0254, B:94:0x025d, B:99:0x027c, B:101:0x0285, B:103:0x0296, B:105:0x029f, B:107:0x02b1, B:109:0x02ba, B:111:0x02cc, B:113:0x02d5, B:115:0x02e7, B:117:0x02f0, B:119:0x0301, B:121:0x030a, B:123:0x031c, B:125:0x0325, B:127:0x0338, B:129:0x0341, B:131:0x034b, B:141:0x0385, B:143:0x038e, B:150:0x03ad, B:152:0x03b7, B:154:0x03cf, B:156:0x03d8, B:158:0x03e4, B:160:0x03ec, B:161:0x03f1, B:163:0x03f7, B:165:0x0400, B:169:0x0410, B:170:0x0413, B:172:0x041c, B:173:0x042d, B:175:0x0436, B:176:0x0443, B:178:0x044c, B:179:0x045d, B:181:0x0466, B:183:0x0472, B:185:0x0478, B:186:0x047f, B:188:0x0488, B:189:0x0495, B:191:0x049f, B:192:0x04ad, B:194:0x04b6, B:195:0x04c3, B:197:0x04cc, B:201:0x04dc, B:203:0x04e1, B:205:0x04ea, B:206:0x04fb, B:208:0x0504, B:209:0x0515, B:211:0x0521, B:216:0x052b, B:153:0x03ca, B:144:0x0394, B:146:0x03a4, B:149:0x03aa, B:148:0x03a8, B:132:0x035d, B:134:0x0366, B:135:0x036c, B:137:0x037c, B:140:0x0382, B:139:0x0380, B:126:0x032b, B:122:0x030f, B:118:0x02f4, B:114:0x02da, B:110:0x02bf, B:106:0x02a4, B:102:0x0289, B:95:0x0265, B:97:0x0275, B:98:0x0279, B:91:0x0247, B:87:0x022d, B:83:0x0215, B:79:0x01fc, B:75:0x01e4, B:71:0x01cd, B:67:0x01b7, B:63:0x01a0, B:59:0x0188, B:55:0x0171, B:51:0x0156, B:23:0x009f, B:26:0x00ab, B:36:0x00d4, B:38:0x00da, B:43:0x00fe, B:44:0x0103, B:47:0x0111, B:30:0x00b6, B:35:0x00d0, B:31:0x00c0, B:18:0x0074), top: B:222:0x0023 }] */
    /* JADX WARN: Removed duplicated region for block: B:215:0x052a  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x0151 A[Catch: all -> 0x0532, TryCatch #0 {all -> 0x0532, blocks: (B:3:0x0023, B:5:0x0033, B:7:0x003d, B:9:0x0046, B:11:0x0054, B:13:0x005b, B:15:0x0065, B:17:0x006f, B:19:0x007f, B:21:0x0086, B:48:0x0130, B:50:0x0151, B:52:0x0165, B:54:0x016c, B:56:0x017c, B:58:0x0183, B:60:0x0193, B:62:0x019c, B:64:0x01ab, B:66:0x01b2, B:68:0x01c2, B:70:0x01c9, B:72:0x01d8, B:74:0x01df, B:76:0x01ef, B:78:0x01f8, B:80:0x0207, B:82:0x0210, B:84:0x0220, B:86:0x0229, B:88:0x023a, B:90:0x0243, B:92:0x0254, B:94:0x025d, B:99:0x027c, B:101:0x0285, B:103:0x0296, B:105:0x029f, B:107:0x02b1, B:109:0x02ba, B:111:0x02cc, B:113:0x02d5, B:115:0x02e7, B:117:0x02f0, B:119:0x0301, B:121:0x030a, B:123:0x031c, B:125:0x0325, B:127:0x0338, B:129:0x0341, B:131:0x034b, B:141:0x0385, B:143:0x038e, B:150:0x03ad, B:152:0x03b7, B:154:0x03cf, B:156:0x03d8, B:158:0x03e4, B:160:0x03ec, B:161:0x03f1, B:163:0x03f7, B:165:0x0400, B:169:0x0410, B:170:0x0413, B:172:0x041c, B:173:0x042d, B:175:0x0436, B:176:0x0443, B:178:0x044c, B:179:0x045d, B:181:0x0466, B:183:0x0472, B:185:0x0478, B:186:0x047f, B:188:0x0488, B:189:0x0495, B:191:0x049f, B:192:0x04ad, B:194:0x04b6, B:195:0x04c3, B:197:0x04cc, B:201:0x04dc, B:203:0x04e1, B:205:0x04ea, B:206:0x04fb, B:208:0x0504, B:209:0x0515, B:211:0x0521, B:216:0x052b, B:153:0x03ca, B:144:0x0394, B:146:0x03a4, B:149:0x03aa, B:148:0x03a8, B:132:0x035d, B:134:0x0366, B:135:0x036c, B:137:0x037c, B:140:0x0382, B:139:0x0380, B:126:0x032b, B:122:0x030f, B:118:0x02f4, B:114:0x02da, B:110:0x02bf, B:106:0x02a4, B:102:0x0289, B:95:0x0265, B:97:0x0275, B:98:0x0279, B:91:0x0247, B:87:0x022d, B:83:0x0215, B:79:0x01fc, B:75:0x01e4, B:71:0x01cd, B:67:0x01b7, B:63:0x01a0, B:59:0x0188, B:55:0x0171, B:51:0x0156, B:23:0x009f, B:26:0x00ab, B:36:0x00d4, B:38:0x00da, B:43:0x00fe, B:44:0x0103, B:47:0x0111, B:30:0x00b6, B:35:0x00d0, B:31:0x00c0, B:18:0x0074), top: B:222:0x0023 }] */
    /* JADX WARN: Removed duplicated region for block: B:51:0x0156 A[Catch: all -> 0x0532, TryCatch #0 {all -> 0x0532, blocks: (B:3:0x0023, B:5:0x0033, B:7:0x003d, B:9:0x0046, B:11:0x0054, B:13:0x005b, B:15:0x0065, B:17:0x006f, B:19:0x007f, B:21:0x0086, B:48:0x0130, B:50:0x0151, B:52:0x0165, B:54:0x016c, B:56:0x017c, B:58:0x0183, B:60:0x0193, B:62:0x019c, B:64:0x01ab, B:66:0x01b2, B:68:0x01c2, B:70:0x01c9, B:72:0x01d8, B:74:0x01df, B:76:0x01ef, B:78:0x01f8, B:80:0x0207, B:82:0x0210, B:84:0x0220, B:86:0x0229, B:88:0x023a, B:90:0x0243, B:92:0x0254, B:94:0x025d, B:99:0x027c, B:101:0x0285, B:103:0x0296, B:105:0x029f, B:107:0x02b1, B:109:0x02ba, B:111:0x02cc, B:113:0x02d5, B:115:0x02e7, B:117:0x02f0, B:119:0x0301, B:121:0x030a, B:123:0x031c, B:125:0x0325, B:127:0x0338, B:129:0x0341, B:131:0x034b, B:141:0x0385, B:143:0x038e, B:150:0x03ad, B:152:0x03b7, B:154:0x03cf, B:156:0x03d8, B:158:0x03e4, B:160:0x03ec, B:161:0x03f1, B:163:0x03f7, B:165:0x0400, B:169:0x0410, B:170:0x0413, B:172:0x041c, B:173:0x042d, B:175:0x0436, B:176:0x0443, B:178:0x044c, B:179:0x045d, B:181:0x0466, B:183:0x0472, B:185:0x0478, B:186:0x047f, B:188:0x0488, B:189:0x0495, B:191:0x049f, B:192:0x04ad, B:194:0x04b6, B:195:0x04c3, B:197:0x04cc, B:201:0x04dc, B:203:0x04e1, B:205:0x04ea, B:206:0x04fb, B:208:0x0504, B:209:0x0515, B:211:0x0521, B:216:0x052b, B:153:0x03ca, B:144:0x0394, B:146:0x03a4, B:149:0x03aa, B:148:0x03a8, B:132:0x035d, B:134:0x0366, B:135:0x036c, B:137:0x037c, B:140:0x0382, B:139:0x0380, B:126:0x032b, B:122:0x030f, B:118:0x02f4, B:114:0x02da, B:110:0x02bf, B:106:0x02a4, B:102:0x0289, B:95:0x0265, B:97:0x0275, B:98:0x0279, B:91:0x0247, B:87:0x022d, B:83:0x0215, B:79:0x01fc, B:75:0x01e4, B:71:0x01cd, B:67:0x01b7, B:63:0x01a0, B:59:0x0188, B:55:0x0171, B:51:0x0156, B:23:0x009f, B:26:0x00ab, B:36:0x00d4, B:38:0x00da, B:43:0x00fe, B:44:0x0103, B:47:0x0111, B:30:0x00b6, B:35:0x00d0, B:31:0x00c0, B:18:0x0074), top: B:222:0x0023 }] */
    /* JADX WARN: Removed duplicated region for block: B:54:0x016c A[Catch: all -> 0x0532, TryCatch #0 {all -> 0x0532, blocks: (B:3:0x0023, B:5:0x0033, B:7:0x003d, B:9:0x0046, B:11:0x0054, B:13:0x005b, B:15:0x0065, B:17:0x006f, B:19:0x007f, B:21:0x0086, B:48:0x0130, B:50:0x0151, B:52:0x0165, B:54:0x016c, B:56:0x017c, B:58:0x0183, B:60:0x0193, B:62:0x019c, B:64:0x01ab, B:66:0x01b2, B:68:0x01c2, B:70:0x01c9, B:72:0x01d8, B:74:0x01df, B:76:0x01ef, B:78:0x01f8, B:80:0x0207, B:82:0x0210, B:84:0x0220, B:86:0x0229, B:88:0x023a, B:90:0x0243, B:92:0x0254, B:94:0x025d, B:99:0x027c, B:101:0x0285, B:103:0x0296, B:105:0x029f, B:107:0x02b1, B:109:0x02ba, B:111:0x02cc, B:113:0x02d5, B:115:0x02e7, B:117:0x02f0, B:119:0x0301, B:121:0x030a, B:123:0x031c, B:125:0x0325, B:127:0x0338, B:129:0x0341, B:131:0x034b, B:141:0x0385, B:143:0x038e, B:150:0x03ad, B:152:0x03b7, B:154:0x03cf, B:156:0x03d8, B:158:0x03e4, B:160:0x03ec, B:161:0x03f1, B:163:0x03f7, B:165:0x0400, B:169:0x0410, B:170:0x0413, B:172:0x041c, B:173:0x042d, B:175:0x0436, B:176:0x0443, B:178:0x044c, B:179:0x045d, B:181:0x0466, B:183:0x0472, B:185:0x0478, B:186:0x047f, B:188:0x0488, B:189:0x0495, B:191:0x049f, B:192:0x04ad, B:194:0x04b6, B:195:0x04c3, B:197:0x04cc, B:201:0x04dc, B:203:0x04e1, B:205:0x04ea, B:206:0x04fb, B:208:0x0504, B:209:0x0515, B:211:0x0521, B:216:0x052b, B:153:0x03ca, B:144:0x0394, B:146:0x03a4, B:149:0x03aa, B:148:0x03a8, B:132:0x035d, B:134:0x0366, B:135:0x036c, B:137:0x037c, B:140:0x0382, B:139:0x0380, B:126:0x032b, B:122:0x030f, B:118:0x02f4, B:114:0x02da, B:110:0x02bf, B:106:0x02a4, B:102:0x0289, B:95:0x0265, B:97:0x0275, B:98:0x0279, B:91:0x0247, B:87:0x022d, B:83:0x0215, B:79:0x01fc, B:75:0x01e4, B:71:0x01cd, B:67:0x01b7, B:63:0x01a0, B:59:0x0188, B:55:0x0171, B:51:0x0156, B:23:0x009f, B:26:0x00ab, B:36:0x00d4, B:38:0x00da, B:43:0x00fe, B:44:0x0103, B:47:0x0111, B:30:0x00b6, B:35:0x00d0, B:31:0x00c0, B:18:0x0074), top: B:222:0x0023 }] */
    /* JADX WARN: Removed duplicated region for block: B:55:0x0171 A[Catch: all -> 0x0532, TryCatch #0 {all -> 0x0532, blocks: (B:3:0x0023, B:5:0x0033, B:7:0x003d, B:9:0x0046, B:11:0x0054, B:13:0x005b, B:15:0x0065, B:17:0x006f, B:19:0x007f, B:21:0x0086, B:48:0x0130, B:50:0x0151, B:52:0x0165, B:54:0x016c, B:56:0x017c, B:58:0x0183, B:60:0x0193, B:62:0x019c, B:64:0x01ab, B:66:0x01b2, B:68:0x01c2, B:70:0x01c9, B:72:0x01d8, B:74:0x01df, B:76:0x01ef, B:78:0x01f8, B:80:0x0207, B:82:0x0210, B:84:0x0220, B:86:0x0229, B:88:0x023a, B:90:0x0243, B:92:0x0254, B:94:0x025d, B:99:0x027c, B:101:0x0285, B:103:0x0296, B:105:0x029f, B:107:0x02b1, B:109:0x02ba, B:111:0x02cc, B:113:0x02d5, B:115:0x02e7, B:117:0x02f0, B:119:0x0301, B:121:0x030a, B:123:0x031c, B:125:0x0325, B:127:0x0338, B:129:0x0341, B:131:0x034b, B:141:0x0385, B:143:0x038e, B:150:0x03ad, B:152:0x03b7, B:154:0x03cf, B:156:0x03d8, B:158:0x03e4, B:160:0x03ec, B:161:0x03f1, B:163:0x03f7, B:165:0x0400, B:169:0x0410, B:170:0x0413, B:172:0x041c, B:173:0x042d, B:175:0x0436, B:176:0x0443, B:178:0x044c, B:179:0x045d, B:181:0x0466, B:183:0x0472, B:185:0x0478, B:186:0x047f, B:188:0x0488, B:189:0x0495, B:191:0x049f, B:192:0x04ad, B:194:0x04b6, B:195:0x04c3, B:197:0x04cc, B:201:0x04dc, B:203:0x04e1, B:205:0x04ea, B:206:0x04fb, B:208:0x0504, B:209:0x0515, B:211:0x0521, B:216:0x052b, B:153:0x03ca, B:144:0x0394, B:146:0x03a4, B:149:0x03aa, B:148:0x03a8, B:132:0x035d, B:134:0x0366, B:135:0x036c, B:137:0x037c, B:140:0x0382, B:139:0x0380, B:126:0x032b, B:122:0x030f, B:118:0x02f4, B:114:0x02da, B:110:0x02bf, B:106:0x02a4, B:102:0x0289, B:95:0x0265, B:97:0x0275, B:98:0x0279, B:91:0x0247, B:87:0x022d, B:83:0x0215, B:79:0x01fc, B:75:0x01e4, B:71:0x01cd, B:67:0x01b7, B:63:0x01a0, B:59:0x0188, B:55:0x0171, B:51:0x0156, B:23:0x009f, B:26:0x00ab, B:36:0x00d4, B:38:0x00da, B:43:0x00fe, B:44:0x0103, B:47:0x0111, B:30:0x00b6, B:35:0x00d0, B:31:0x00c0, B:18:0x0074), top: B:222:0x0023 }] */
    /* JADX WARN: Removed duplicated region for block: B:58:0x0183 A[Catch: all -> 0x0532, TryCatch #0 {all -> 0x0532, blocks: (B:3:0x0023, B:5:0x0033, B:7:0x003d, B:9:0x0046, B:11:0x0054, B:13:0x005b, B:15:0x0065, B:17:0x006f, B:19:0x007f, B:21:0x0086, B:48:0x0130, B:50:0x0151, B:52:0x0165, B:54:0x016c, B:56:0x017c, B:58:0x0183, B:60:0x0193, B:62:0x019c, B:64:0x01ab, B:66:0x01b2, B:68:0x01c2, B:70:0x01c9, B:72:0x01d8, B:74:0x01df, B:76:0x01ef, B:78:0x01f8, B:80:0x0207, B:82:0x0210, B:84:0x0220, B:86:0x0229, B:88:0x023a, B:90:0x0243, B:92:0x0254, B:94:0x025d, B:99:0x027c, B:101:0x0285, B:103:0x0296, B:105:0x029f, B:107:0x02b1, B:109:0x02ba, B:111:0x02cc, B:113:0x02d5, B:115:0x02e7, B:117:0x02f0, B:119:0x0301, B:121:0x030a, B:123:0x031c, B:125:0x0325, B:127:0x0338, B:129:0x0341, B:131:0x034b, B:141:0x0385, B:143:0x038e, B:150:0x03ad, B:152:0x03b7, B:154:0x03cf, B:156:0x03d8, B:158:0x03e4, B:160:0x03ec, B:161:0x03f1, B:163:0x03f7, B:165:0x0400, B:169:0x0410, B:170:0x0413, B:172:0x041c, B:173:0x042d, B:175:0x0436, B:176:0x0443, B:178:0x044c, B:179:0x045d, B:181:0x0466, B:183:0x0472, B:185:0x0478, B:186:0x047f, B:188:0x0488, B:189:0x0495, B:191:0x049f, B:192:0x04ad, B:194:0x04b6, B:195:0x04c3, B:197:0x04cc, B:201:0x04dc, B:203:0x04e1, B:205:0x04ea, B:206:0x04fb, B:208:0x0504, B:209:0x0515, B:211:0x0521, B:216:0x052b, B:153:0x03ca, B:144:0x0394, B:146:0x03a4, B:149:0x03aa, B:148:0x03a8, B:132:0x035d, B:134:0x0366, B:135:0x036c, B:137:0x037c, B:140:0x0382, B:139:0x0380, B:126:0x032b, B:122:0x030f, B:118:0x02f4, B:114:0x02da, B:110:0x02bf, B:106:0x02a4, B:102:0x0289, B:95:0x0265, B:97:0x0275, B:98:0x0279, B:91:0x0247, B:87:0x022d, B:83:0x0215, B:79:0x01fc, B:75:0x01e4, B:71:0x01cd, B:67:0x01b7, B:63:0x01a0, B:59:0x0188, B:55:0x0171, B:51:0x0156, B:23:0x009f, B:26:0x00ab, B:36:0x00d4, B:38:0x00da, B:43:0x00fe, B:44:0x0103, B:47:0x0111, B:30:0x00b6, B:35:0x00d0, B:31:0x00c0, B:18:0x0074), top: B:222:0x0023 }] */
    /* JADX WARN: Removed duplicated region for block: B:59:0x0188 A[Catch: all -> 0x0532, TryCatch #0 {all -> 0x0532, blocks: (B:3:0x0023, B:5:0x0033, B:7:0x003d, B:9:0x0046, B:11:0x0054, B:13:0x005b, B:15:0x0065, B:17:0x006f, B:19:0x007f, B:21:0x0086, B:48:0x0130, B:50:0x0151, B:52:0x0165, B:54:0x016c, B:56:0x017c, B:58:0x0183, B:60:0x0193, B:62:0x019c, B:64:0x01ab, B:66:0x01b2, B:68:0x01c2, B:70:0x01c9, B:72:0x01d8, B:74:0x01df, B:76:0x01ef, B:78:0x01f8, B:80:0x0207, B:82:0x0210, B:84:0x0220, B:86:0x0229, B:88:0x023a, B:90:0x0243, B:92:0x0254, B:94:0x025d, B:99:0x027c, B:101:0x0285, B:103:0x0296, B:105:0x029f, B:107:0x02b1, B:109:0x02ba, B:111:0x02cc, B:113:0x02d5, B:115:0x02e7, B:117:0x02f0, B:119:0x0301, B:121:0x030a, B:123:0x031c, B:125:0x0325, B:127:0x0338, B:129:0x0341, B:131:0x034b, B:141:0x0385, B:143:0x038e, B:150:0x03ad, B:152:0x03b7, B:154:0x03cf, B:156:0x03d8, B:158:0x03e4, B:160:0x03ec, B:161:0x03f1, B:163:0x03f7, B:165:0x0400, B:169:0x0410, B:170:0x0413, B:172:0x041c, B:173:0x042d, B:175:0x0436, B:176:0x0443, B:178:0x044c, B:179:0x045d, B:181:0x0466, B:183:0x0472, B:185:0x0478, B:186:0x047f, B:188:0x0488, B:189:0x0495, B:191:0x049f, B:192:0x04ad, B:194:0x04b6, B:195:0x04c3, B:197:0x04cc, B:201:0x04dc, B:203:0x04e1, B:205:0x04ea, B:206:0x04fb, B:208:0x0504, B:209:0x0515, B:211:0x0521, B:216:0x052b, B:153:0x03ca, B:144:0x0394, B:146:0x03a4, B:149:0x03aa, B:148:0x03a8, B:132:0x035d, B:134:0x0366, B:135:0x036c, B:137:0x037c, B:140:0x0382, B:139:0x0380, B:126:0x032b, B:122:0x030f, B:118:0x02f4, B:114:0x02da, B:110:0x02bf, B:106:0x02a4, B:102:0x0289, B:95:0x0265, B:97:0x0275, B:98:0x0279, B:91:0x0247, B:87:0x022d, B:83:0x0215, B:79:0x01fc, B:75:0x01e4, B:71:0x01cd, B:67:0x01b7, B:63:0x01a0, B:59:0x0188, B:55:0x0171, B:51:0x0156, B:23:0x009f, B:26:0x00ab, B:36:0x00d4, B:38:0x00da, B:43:0x00fe, B:44:0x0103, B:47:0x0111, B:30:0x00b6, B:35:0x00d0, B:31:0x00c0, B:18:0x0074), top: B:222:0x0023 }] */
    /* JADX WARN: Removed duplicated region for block: B:62:0x019c A[Catch: all -> 0x0532, TryCatch #0 {all -> 0x0532, blocks: (B:3:0x0023, B:5:0x0033, B:7:0x003d, B:9:0x0046, B:11:0x0054, B:13:0x005b, B:15:0x0065, B:17:0x006f, B:19:0x007f, B:21:0x0086, B:48:0x0130, B:50:0x0151, B:52:0x0165, B:54:0x016c, B:56:0x017c, B:58:0x0183, B:60:0x0193, B:62:0x019c, B:64:0x01ab, B:66:0x01b2, B:68:0x01c2, B:70:0x01c9, B:72:0x01d8, B:74:0x01df, B:76:0x01ef, B:78:0x01f8, B:80:0x0207, B:82:0x0210, B:84:0x0220, B:86:0x0229, B:88:0x023a, B:90:0x0243, B:92:0x0254, B:94:0x025d, B:99:0x027c, B:101:0x0285, B:103:0x0296, B:105:0x029f, B:107:0x02b1, B:109:0x02ba, B:111:0x02cc, B:113:0x02d5, B:115:0x02e7, B:117:0x02f0, B:119:0x0301, B:121:0x030a, B:123:0x031c, B:125:0x0325, B:127:0x0338, B:129:0x0341, B:131:0x034b, B:141:0x0385, B:143:0x038e, B:150:0x03ad, B:152:0x03b7, B:154:0x03cf, B:156:0x03d8, B:158:0x03e4, B:160:0x03ec, B:161:0x03f1, B:163:0x03f7, B:165:0x0400, B:169:0x0410, B:170:0x0413, B:172:0x041c, B:173:0x042d, B:175:0x0436, B:176:0x0443, B:178:0x044c, B:179:0x045d, B:181:0x0466, B:183:0x0472, B:185:0x0478, B:186:0x047f, B:188:0x0488, B:189:0x0495, B:191:0x049f, B:192:0x04ad, B:194:0x04b6, B:195:0x04c3, B:197:0x04cc, B:201:0x04dc, B:203:0x04e1, B:205:0x04ea, B:206:0x04fb, B:208:0x0504, B:209:0x0515, B:211:0x0521, B:216:0x052b, B:153:0x03ca, B:144:0x0394, B:146:0x03a4, B:149:0x03aa, B:148:0x03a8, B:132:0x035d, B:134:0x0366, B:135:0x036c, B:137:0x037c, B:140:0x0382, B:139:0x0380, B:126:0x032b, B:122:0x030f, B:118:0x02f4, B:114:0x02da, B:110:0x02bf, B:106:0x02a4, B:102:0x0289, B:95:0x0265, B:97:0x0275, B:98:0x0279, B:91:0x0247, B:87:0x022d, B:83:0x0215, B:79:0x01fc, B:75:0x01e4, B:71:0x01cd, B:67:0x01b7, B:63:0x01a0, B:59:0x0188, B:55:0x0171, B:51:0x0156, B:23:0x009f, B:26:0x00ab, B:36:0x00d4, B:38:0x00da, B:43:0x00fe, B:44:0x0103, B:47:0x0111, B:30:0x00b6, B:35:0x00d0, B:31:0x00c0, B:18:0x0074), top: B:222:0x0023 }] */
    /* JADX WARN: Removed duplicated region for block: B:63:0x01a0 A[Catch: all -> 0x0532, TryCatch #0 {all -> 0x0532, blocks: (B:3:0x0023, B:5:0x0033, B:7:0x003d, B:9:0x0046, B:11:0x0054, B:13:0x005b, B:15:0x0065, B:17:0x006f, B:19:0x007f, B:21:0x0086, B:48:0x0130, B:50:0x0151, B:52:0x0165, B:54:0x016c, B:56:0x017c, B:58:0x0183, B:60:0x0193, B:62:0x019c, B:64:0x01ab, B:66:0x01b2, B:68:0x01c2, B:70:0x01c9, B:72:0x01d8, B:74:0x01df, B:76:0x01ef, B:78:0x01f8, B:80:0x0207, B:82:0x0210, B:84:0x0220, B:86:0x0229, B:88:0x023a, B:90:0x0243, B:92:0x0254, B:94:0x025d, B:99:0x027c, B:101:0x0285, B:103:0x0296, B:105:0x029f, B:107:0x02b1, B:109:0x02ba, B:111:0x02cc, B:113:0x02d5, B:115:0x02e7, B:117:0x02f0, B:119:0x0301, B:121:0x030a, B:123:0x031c, B:125:0x0325, B:127:0x0338, B:129:0x0341, B:131:0x034b, B:141:0x0385, B:143:0x038e, B:150:0x03ad, B:152:0x03b7, B:154:0x03cf, B:156:0x03d8, B:158:0x03e4, B:160:0x03ec, B:161:0x03f1, B:163:0x03f7, B:165:0x0400, B:169:0x0410, B:170:0x0413, B:172:0x041c, B:173:0x042d, B:175:0x0436, B:176:0x0443, B:178:0x044c, B:179:0x045d, B:181:0x0466, B:183:0x0472, B:185:0x0478, B:186:0x047f, B:188:0x0488, B:189:0x0495, B:191:0x049f, B:192:0x04ad, B:194:0x04b6, B:195:0x04c3, B:197:0x04cc, B:201:0x04dc, B:203:0x04e1, B:205:0x04ea, B:206:0x04fb, B:208:0x0504, B:209:0x0515, B:211:0x0521, B:216:0x052b, B:153:0x03ca, B:144:0x0394, B:146:0x03a4, B:149:0x03aa, B:148:0x03a8, B:132:0x035d, B:134:0x0366, B:135:0x036c, B:137:0x037c, B:140:0x0382, B:139:0x0380, B:126:0x032b, B:122:0x030f, B:118:0x02f4, B:114:0x02da, B:110:0x02bf, B:106:0x02a4, B:102:0x0289, B:95:0x0265, B:97:0x0275, B:98:0x0279, B:91:0x0247, B:87:0x022d, B:83:0x0215, B:79:0x01fc, B:75:0x01e4, B:71:0x01cd, B:67:0x01b7, B:63:0x01a0, B:59:0x0188, B:55:0x0171, B:51:0x0156, B:23:0x009f, B:26:0x00ab, B:36:0x00d4, B:38:0x00da, B:43:0x00fe, B:44:0x0103, B:47:0x0111, B:30:0x00b6, B:35:0x00d0, B:31:0x00c0, B:18:0x0074), top: B:222:0x0023 }] */
    /* JADX WARN: Removed duplicated region for block: B:66:0x01b2 A[Catch: all -> 0x0532, TryCatch #0 {all -> 0x0532, blocks: (B:3:0x0023, B:5:0x0033, B:7:0x003d, B:9:0x0046, B:11:0x0054, B:13:0x005b, B:15:0x0065, B:17:0x006f, B:19:0x007f, B:21:0x0086, B:48:0x0130, B:50:0x0151, B:52:0x0165, B:54:0x016c, B:56:0x017c, B:58:0x0183, B:60:0x0193, B:62:0x019c, B:64:0x01ab, B:66:0x01b2, B:68:0x01c2, B:70:0x01c9, B:72:0x01d8, B:74:0x01df, B:76:0x01ef, B:78:0x01f8, B:80:0x0207, B:82:0x0210, B:84:0x0220, B:86:0x0229, B:88:0x023a, B:90:0x0243, B:92:0x0254, B:94:0x025d, B:99:0x027c, B:101:0x0285, B:103:0x0296, B:105:0x029f, B:107:0x02b1, B:109:0x02ba, B:111:0x02cc, B:113:0x02d5, B:115:0x02e7, B:117:0x02f0, B:119:0x0301, B:121:0x030a, B:123:0x031c, B:125:0x0325, B:127:0x0338, B:129:0x0341, B:131:0x034b, B:141:0x0385, B:143:0x038e, B:150:0x03ad, B:152:0x03b7, B:154:0x03cf, B:156:0x03d8, B:158:0x03e4, B:160:0x03ec, B:161:0x03f1, B:163:0x03f7, B:165:0x0400, B:169:0x0410, B:170:0x0413, B:172:0x041c, B:173:0x042d, B:175:0x0436, B:176:0x0443, B:178:0x044c, B:179:0x045d, B:181:0x0466, B:183:0x0472, B:185:0x0478, B:186:0x047f, B:188:0x0488, B:189:0x0495, B:191:0x049f, B:192:0x04ad, B:194:0x04b6, B:195:0x04c3, B:197:0x04cc, B:201:0x04dc, B:203:0x04e1, B:205:0x04ea, B:206:0x04fb, B:208:0x0504, B:209:0x0515, B:211:0x0521, B:216:0x052b, B:153:0x03ca, B:144:0x0394, B:146:0x03a4, B:149:0x03aa, B:148:0x03a8, B:132:0x035d, B:134:0x0366, B:135:0x036c, B:137:0x037c, B:140:0x0382, B:139:0x0380, B:126:0x032b, B:122:0x030f, B:118:0x02f4, B:114:0x02da, B:110:0x02bf, B:106:0x02a4, B:102:0x0289, B:95:0x0265, B:97:0x0275, B:98:0x0279, B:91:0x0247, B:87:0x022d, B:83:0x0215, B:79:0x01fc, B:75:0x01e4, B:71:0x01cd, B:67:0x01b7, B:63:0x01a0, B:59:0x0188, B:55:0x0171, B:51:0x0156, B:23:0x009f, B:26:0x00ab, B:36:0x00d4, B:38:0x00da, B:43:0x00fe, B:44:0x0103, B:47:0x0111, B:30:0x00b6, B:35:0x00d0, B:31:0x00c0, B:18:0x0074), top: B:222:0x0023 }] */
    /* JADX WARN: Removed duplicated region for block: B:67:0x01b7 A[Catch: all -> 0x0532, TryCatch #0 {all -> 0x0532, blocks: (B:3:0x0023, B:5:0x0033, B:7:0x003d, B:9:0x0046, B:11:0x0054, B:13:0x005b, B:15:0x0065, B:17:0x006f, B:19:0x007f, B:21:0x0086, B:48:0x0130, B:50:0x0151, B:52:0x0165, B:54:0x016c, B:56:0x017c, B:58:0x0183, B:60:0x0193, B:62:0x019c, B:64:0x01ab, B:66:0x01b2, B:68:0x01c2, B:70:0x01c9, B:72:0x01d8, B:74:0x01df, B:76:0x01ef, B:78:0x01f8, B:80:0x0207, B:82:0x0210, B:84:0x0220, B:86:0x0229, B:88:0x023a, B:90:0x0243, B:92:0x0254, B:94:0x025d, B:99:0x027c, B:101:0x0285, B:103:0x0296, B:105:0x029f, B:107:0x02b1, B:109:0x02ba, B:111:0x02cc, B:113:0x02d5, B:115:0x02e7, B:117:0x02f0, B:119:0x0301, B:121:0x030a, B:123:0x031c, B:125:0x0325, B:127:0x0338, B:129:0x0341, B:131:0x034b, B:141:0x0385, B:143:0x038e, B:150:0x03ad, B:152:0x03b7, B:154:0x03cf, B:156:0x03d8, B:158:0x03e4, B:160:0x03ec, B:161:0x03f1, B:163:0x03f7, B:165:0x0400, B:169:0x0410, B:170:0x0413, B:172:0x041c, B:173:0x042d, B:175:0x0436, B:176:0x0443, B:178:0x044c, B:179:0x045d, B:181:0x0466, B:183:0x0472, B:185:0x0478, B:186:0x047f, B:188:0x0488, B:189:0x0495, B:191:0x049f, B:192:0x04ad, B:194:0x04b6, B:195:0x04c3, B:197:0x04cc, B:201:0x04dc, B:203:0x04e1, B:205:0x04ea, B:206:0x04fb, B:208:0x0504, B:209:0x0515, B:211:0x0521, B:216:0x052b, B:153:0x03ca, B:144:0x0394, B:146:0x03a4, B:149:0x03aa, B:148:0x03a8, B:132:0x035d, B:134:0x0366, B:135:0x036c, B:137:0x037c, B:140:0x0382, B:139:0x0380, B:126:0x032b, B:122:0x030f, B:118:0x02f4, B:114:0x02da, B:110:0x02bf, B:106:0x02a4, B:102:0x0289, B:95:0x0265, B:97:0x0275, B:98:0x0279, B:91:0x0247, B:87:0x022d, B:83:0x0215, B:79:0x01fc, B:75:0x01e4, B:71:0x01cd, B:67:0x01b7, B:63:0x01a0, B:59:0x0188, B:55:0x0171, B:51:0x0156, B:23:0x009f, B:26:0x00ab, B:36:0x00d4, B:38:0x00da, B:43:0x00fe, B:44:0x0103, B:47:0x0111, B:30:0x00b6, B:35:0x00d0, B:31:0x00c0, B:18:0x0074), top: B:222:0x0023 }] */
    /* JADX WARN: Removed duplicated region for block: B:70:0x01c9 A[Catch: all -> 0x0532, TryCatch #0 {all -> 0x0532, blocks: (B:3:0x0023, B:5:0x0033, B:7:0x003d, B:9:0x0046, B:11:0x0054, B:13:0x005b, B:15:0x0065, B:17:0x006f, B:19:0x007f, B:21:0x0086, B:48:0x0130, B:50:0x0151, B:52:0x0165, B:54:0x016c, B:56:0x017c, B:58:0x0183, B:60:0x0193, B:62:0x019c, B:64:0x01ab, B:66:0x01b2, B:68:0x01c2, B:70:0x01c9, B:72:0x01d8, B:74:0x01df, B:76:0x01ef, B:78:0x01f8, B:80:0x0207, B:82:0x0210, B:84:0x0220, B:86:0x0229, B:88:0x023a, B:90:0x0243, B:92:0x0254, B:94:0x025d, B:99:0x027c, B:101:0x0285, B:103:0x0296, B:105:0x029f, B:107:0x02b1, B:109:0x02ba, B:111:0x02cc, B:113:0x02d5, B:115:0x02e7, B:117:0x02f0, B:119:0x0301, B:121:0x030a, B:123:0x031c, B:125:0x0325, B:127:0x0338, B:129:0x0341, B:131:0x034b, B:141:0x0385, B:143:0x038e, B:150:0x03ad, B:152:0x03b7, B:154:0x03cf, B:156:0x03d8, B:158:0x03e4, B:160:0x03ec, B:161:0x03f1, B:163:0x03f7, B:165:0x0400, B:169:0x0410, B:170:0x0413, B:172:0x041c, B:173:0x042d, B:175:0x0436, B:176:0x0443, B:178:0x044c, B:179:0x045d, B:181:0x0466, B:183:0x0472, B:185:0x0478, B:186:0x047f, B:188:0x0488, B:189:0x0495, B:191:0x049f, B:192:0x04ad, B:194:0x04b6, B:195:0x04c3, B:197:0x04cc, B:201:0x04dc, B:203:0x04e1, B:205:0x04ea, B:206:0x04fb, B:208:0x0504, B:209:0x0515, B:211:0x0521, B:216:0x052b, B:153:0x03ca, B:144:0x0394, B:146:0x03a4, B:149:0x03aa, B:148:0x03a8, B:132:0x035d, B:134:0x0366, B:135:0x036c, B:137:0x037c, B:140:0x0382, B:139:0x0380, B:126:0x032b, B:122:0x030f, B:118:0x02f4, B:114:0x02da, B:110:0x02bf, B:106:0x02a4, B:102:0x0289, B:95:0x0265, B:97:0x0275, B:98:0x0279, B:91:0x0247, B:87:0x022d, B:83:0x0215, B:79:0x01fc, B:75:0x01e4, B:71:0x01cd, B:67:0x01b7, B:63:0x01a0, B:59:0x0188, B:55:0x0171, B:51:0x0156, B:23:0x009f, B:26:0x00ab, B:36:0x00d4, B:38:0x00da, B:43:0x00fe, B:44:0x0103, B:47:0x0111, B:30:0x00b6, B:35:0x00d0, B:31:0x00c0, B:18:0x0074), top: B:222:0x0023 }] */
    /* JADX WARN: Removed duplicated region for block: B:71:0x01cd A[Catch: all -> 0x0532, TryCatch #0 {all -> 0x0532, blocks: (B:3:0x0023, B:5:0x0033, B:7:0x003d, B:9:0x0046, B:11:0x0054, B:13:0x005b, B:15:0x0065, B:17:0x006f, B:19:0x007f, B:21:0x0086, B:48:0x0130, B:50:0x0151, B:52:0x0165, B:54:0x016c, B:56:0x017c, B:58:0x0183, B:60:0x0193, B:62:0x019c, B:64:0x01ab, B:66:0x01b2, B:68:0x01c2, B:70:0x01c9, B:72:0x01d8, B:74:0x01df, B:76:0x01ef, B:78:0x01f8, B:80:0x0207, B:82:0x0210, B:84:0x0220, B:86:0x0229, B:88:0x023a, B:90:0x0243, B:92:0x0254, B:94:0x025d, B:99:0x027c, B:101:0x0285, B:103:0x0296, B:105:0x029f, B:107:0x02b1, B:109:0x02ba, B:111:0x02cc, B:113:0x02d5, B:115:0x02e7, B:117:0x02f0, B:119:0x0301, B:121:0x030a, B:123:0x031c, B:125:0x0325, B:127:0x0338, B:129:0x0341, B:131:0x034b, B:141:0x0385, B:143:0x038e, B:150:0x03ad, B:152:0x03b7, B:154:0x03cf, B:156:0x03d8, B:158:0x03e4, B:160:0x03ec, B:161:0x03f1, B:163:0x03f7, B:165:0x0400, B:169:0x0410, B:170:0x0413, B:172:0x041c, B:173:0x042d, B:175:0x0436, B:176:0x0443, B:178:0x044c, B:179:0x045d, B:181:0x0466, B:183:0x0472, B:185:0x0478, B:186:0x047f, B:188:0x0488, B:189:0x0495, B:191:0x049f, B:192:0x04ad, B:194:0x04b6, B:195:0x04c3, B:197:0x04cc, B:201:0x04dc, B:203:0x04e1, B:205:0x04ea, B:206:0x04fb, B:208:0x0504, B:209:0x0515, B:211:0x0521, B:216:0x052b, B:153:0x03ca, B:144:0x0394, B:146:0x03a4, B:149:0x03aa, B:148:0x03a8, B:132:0x035d, B:134:0x0366, B:135:0x036c, B:137:0x037c, B:140:0x0382, B:139:0x0380, B:126:0x032b, B:122:0x030f, B:118:0x02f4, B:114:0x02da, B:110:0x02bf, B:106:0x02a4, B:102:0x0289, B:95:0x0265, B:97:0x0275, B:98:0x0279, B:91:0x0247, B:87:0x022d, B:83:0x0215, B:79:0x01fc, B:75:0x01e4, B:71:0x01cd, B:67:0x01b7, B:63:0x01a0, B:59:0x0188, B:55:0x0171, B:51:0x0156, B:23:0x009f, B:26:0x00ab, B:36:0x00d4, B:38:0x00da, B:43:0x00fe, B:44:0x0103, B:47:0x0111, B:30:0x00b6, B:35:0x00d0, B:31:0x00c0, B:18:0x0074), top: B:222:0x0023 }] */
    /* JADX WARN: Removed duplicated region for block: B:74:0x01df A[Catch: all -> 0x0532, TryCatch #0 {all -> 0x0532, blocks: (B:3:0x0023, B:5:0x0033, B:7:0x003d, B:9:0x0046, B:11:0x0054, B:13:0x005b, B:15:0x0065, B:17:0x006f, B:19:0x007f, B:21:0x0086, B:48:0x0130, B:50:0x0151, B:52:0x0165, B:54:0x016c, B:56:0x017c, B:58:0x0183, B:60:0x0193, B:62:0x019c, B:64:0x01ab, B:66:0x01b2, B:68:0x01c2, B:70:0x01c9, B:72:0x01d8, B:74:0x01df, B:76:0x01ef, B:78:0x01f8, B:80:0x0207, B:82:0x0210, B:84:0x0220, B:86:0x0229, B:88:0x023a, B:90:0x0243, B:92:0x0254, B:94:0x025d, B:99:0x027c, B:101:0x0285, B:103:0x0296, B:105:0x029f, B:107:0x02b1, B:109:0x02ba, B:111:0x02cc, B:113:0x02d5, B:115:0x02e7, B:117:0x02f0, B:119:0x0301, B:121:0x030a, B:123:0x031c, B:125:0x0325, B:127:0x0338, B:129:0x0341, B:131:0x034b, B:141:0x0385, B:143:0x038e, B:150:0x03ad, B:152:0x03b7, B:154:0x03cf, B:156:0x03d8, B:158:0x03e4, B:160:0x03ec, B:161:0x03f1, B:163:0x03f7, B:165:0x0400, B:169:0x0410, B:170:0x0413, B:172:0x041c, B:173:0x042d, B:175:0x0436, B:176:0x0443, B:178:0x044c, B:179:0x045d, B:181:0x0466, B:183:0x0472, B:185:0x0478, B:186:0x047f, B:188:0x0488, B:189:0x0495, B:191:0x049f, B:192:0x04ad, B:194:0x04b6, B:195:0x04c3, B:197:0x04cc, B:201:0x04dc, B:203:0x04e1, B:205:0x04ea, B:206:0x04fb, B:208:0x0504, B:209:0x0515, B:211:0x0521, B:216:0x052b, B:153:0x03ca, B:144:0x0394, B:146:0x03a4, B:149:0x03aa, B:148:0x03a8, B:132:0x035d, B:134:0x0366, B:135:0x036c, B:137:0x037c, B:140:0x0382, B:139:0x0380, B:126:0x032b, B:122:0x030f, B:118:0x02f4, B:114:0x02da, B:110:0x02bf, B:106:0x02a4, B:102:0x0289, B:95:0x0265, B:97:0x0275, B:98:0x0279, B:91:0x0247, B:87:0x022d, B:83:0x0215, B:79:0x01fc, B:75:0x01e4, B:71:0x01cd, B:67:0x01b7, B:63:0x01a0, B:59:0x0188, B:55:0x0171, B:51:0x0156, B:23:0x009f, B:26:0x00ab, B:36:0x00d4, B:38:0x00da, B:43:0x00fe, B:44:0x0103, B:47:0x0111, B:30:0x00b6, B:35:0x00d0, B:31:0x00c0, B:18:0x0074), top: B:222:0x0023 }] */
    /* JADX WARN: Removed duplicated region for block: B:75:0x01e4 A[Catch: all -> 0x0532, TryCatch #0 {all -> 0x0532, blocks: (B:3:0x0023, B:5:0x0033, B:7:0x003d, B:9:0x0046, B:11:0x0054, B:13:0x005b, B:15:0x0065, B:17:0x006f, B:19:0x007f, B:21:0x0086, B:48:0x0130, B:50:0x0151, B:52:0x0165, B:54:0x016c, B:56:0x017c, B:58:0x0183, B:60:0x0193, B:62:0x019c, B:64:0x01ab, B:66:0x01b2, B:68:0x01c2, B:70:0x01c9, B:72:0x01d8, B:74:0x01df, B:76:0x01ef, B:78:0x01f8, B:80:0x0207, B:82:0x0210, B:84:0x0220, B:86:0x0229, B:88:0x023a, B:90:0x0243, B:92:0x0254, B:94:0x025d, B:99:0x027c, B:101:0x0285, B:103:0x0296, B:105:0x029f, B:107:0x02b1, B:109:0x02ba, B:111:0x02cc, B:113:0x02d5, B:115:0x02e7, B:117:0x02f0, B:119:0x0301, B:121:0x030a, B:123:0x031c, B:125:0x0325, B:127:0x0338, B:129:0x0341, B:131:0x034b, B:141:0x0385, B:143:0x038e, B:150:0x03ad, B:152:0x03b7, B:154:0x03cf, B:156:0x03d8, B:158:0x03e4, B:160:0x03ec, B:161:0x03f1, B:163:0x03f7, B:165:0x0400, B:169:0x0410, B:170:0x0413, B:172:0x041c, B:173:0x042d, B:175:0x0436, B:176:0x0443, B:178:0x044c, B:179:0x045d, B:181:0x0466, B:183:0x0472, B:185:0x0478, B:186:0x047f, B:188:0x0488, B:189:0x0495, B:191:0x049f, B:192:0x04ad, B:194:0x04b6, B:195:0x04c3, B:197:0x04cc, B:201:0x04dc, B:203:0x04e1, B:205:0x04ea, B:206:0x04fb, B:208:0x0504, B:209:0x0515, B:211:0x0521, B:216:0x052b, B:153:0x03ca, B:144:0x0394, B:146:0x03a4, B:149:0x03aa, B:148:0x03a8, B:132:0x035d, B:134:0x0366, B:135:0x036c, B:137:0x037c, B:140:0x0382, B:139:0x0380, B:126:0x032b, B:122:0x030f, B:118:0x02f4, B:114:0x02da, B:110:0x02bf, B:106:0x02a4, B:102:0x0289, B:95:0x0265, B:97:0x0275, B:98:0x0279, B:91:0x0247, B:87:0x022d, B:83:0x0215, B:79:0x01fc, B:75:0x01e4, B:71:0x01cd, B:67:0x01b7, B:63:0x01a0, B:59:0x0188, B:55:0x0171, B:51:0x0156, B:23:0x009f, B:26:0x00ab, B:36:0x00d4, B:38:0x00da, B:43:0x00fe, B:44:0x0103, B:47:0x0111, B:30:0x00b6, B:35:0x00d0, B:31:0x00c0, B:18:0x0074), top: B:222:0x0023 }] */
    /* JADX WARN: Removed duplicated region for block: B:78:0x01f8 A[Catch: all -> 0x0532, TryCatch #0 {all -> 0x0532, blocks: (B:3:0x0023, B:5:0x0033, B:7:0x003d, B:9:0x0046, B:11:0x0054, B:13:0x005b, B:15:0x0065, B:17:0x006f, B:19:0x007f, B:21:0x0086, B:48:0x0130, B:50:0x0151, B:52:0x0165, B:54:0x016c, B:56:0x017c, B:58:0x0183, B:60:0x0193, B:62:0x019c, B:64:0x01ab, B:66:0x01b2, B:68:0x01c2, B:70:0x01c9, B:72:0x01d8, B:74:0x01df, B:76:0x01ef, B:78:0x01f8, B:80:0x0207, B:82:0x0210, B:84:0x0220, B:86:0x0229, B:88:0x023a, B:90:0x0243, B:92:0x0254, B:94:0x025d, B:99:0x027c, B:101:0x0285, B:103:0x0296, B:105:0x029f, B:107:0x02b1, B:109:0x02ba, B:111:0x02cc, B:113:0x02d5, B:115:0x02e7, B:117:0x02f0, B:119:0x0301, B:121:0x030a, B:123:0x031c, B:125:0x0325, B:127:0x0338, B:129:0x0341, B:131:0x034b, B:141:0x0385, B:143:0x038e, B:150:0x03ad, B:152:0x03b7, B:154:0x03cf, B:156:0x03d8, B:158:0x03e4, B:160:0x03ec, B:161:0x03f1, B:163:0x03f7, B:165:0x0400, B:169:0x0410, B:170:0x0413, B:172:0x041c, B:173:0x042d, B:175:0x0436, B:176:0x0443, B:178:0x044c, B:179:0x045d, B:181:0x0466, B:183:0x0472, B:185:0x0478, B:186:0x047f, B:188:0x0488, B:189:0x0495, B:191:0x049f, B:192:0x04ad, B:194:0x04b6, B:195:0x04c3, B:197:0x04cc, B:201:0x04dc, B:203:0x04e1, B:205:0x04ea, B:206:0x04fb, B:208:0x0504, B:209:0x0515, B:211:0x0521, B:216:0x052b, B:153:0x03ca, B:144:0x0394, B:146:0x03a4, B:149:0x03aa, B:148:0x03a8, B:132:0x035d, B:134:0x0366, B:135:0x036c, B:137:0x037c, B:140:0x0382, B:139:0x0380, B:126:0x032b, B:122:0x030f, B:118:0x02f4, B:114:0x02da, B:110:0x02bf, B:106:0x02a4, B:102:0x0289, B:95:0x0265, B:97:0x0275, B:98:0x0279, B:91:0x0247, B:87:0x022d, B:83:0x0215, B:79:0x01fc, B:75:0x01e4, B:71:0x01cd, B:67:0x01b7, B:63:0x01a0, B:59:0x0188, B:55:0x0171, B:51:0x0156, B:23:0x009f, B:26:0x00ab, B:36:0x00d4, B:38:0x00da, B:43:0x00fe, B:44:0x0103, B:47:0x0111, B:30:0x00b6, B:35:0x00d0, B:31:0x00c0, B:18:0x0074), top: B:222:0x0023 }] */
    /* JADX WARN: Removed duplicated region for block: B:79:0x01fc A[Catch: all -> 0x0532, TryCatch #0 {all -> 0x0532, blocks: (B:3:0x0023, B:5:0x0033, B:7:0x003d, B:9:0x0046, B:11:0x0054, B:13:0x005b, B:15:0x0065, B:17:0x006f, B:19:0x007f, B:21:0x0086, B:48:0x0130, B:50:0x0151, B:52:0x0165, B:54:0x016c, B:56:0x017c, B:58:0x0183, B:60:0x0193, B:62:0x019c, B:64:0x01ab, B:66:0x01b2, B:68:0x01c2, B:70:0x01c9, B:72:0x01d8, B:74:0x01df, B:76:0x01ef, B:78:0x01f8, B:80:0x0207, B:82:0x0210, B:84:0x0220, B:86:0x0229, B:88:0x023a, B:90:0x0243, B:92:0x0254, B:94:0x025d, B:99:0x027c, B:101:0x0285, B:103:0x0296, B:105:0x029f, B:107:0x02b1, B:109:0x02ba, B:111:0x02cc, B:113:0x02d5, B:115:0x02e7, B:117:0x02f0, B:119:0x0301, B:121:0x030a, B:123:0x031c, B:125:0x0325, B:127:0x0338, B:129:0x0341, B:131:0x034b, B:141:0x0385, B:143:0x038e, B:150:0x03ad, B:152:0x03b7, B:154:0x03cf, B:156:0x03d8, B:158:0x03e4, B:160:0x03ec, B:161:0x03f1, B:163:0x03f7, B:165:0x0400, B:169:0x0410, B:170:0x0413, B:172:0x041c, B:173:0x042d, B:175:0x0436, B:176:0x0443, B:178:0x044c, B:179:0x045d, B:181:0x0466, B:183:0x0472, B:185:0x0478, B:186:0x047f, B:188:0x0488, B:189:0x0495, B:191:0x049f, B:192:0x04ad, B:194:0x04b6, B:195:0x04c3, B:197:0x04cc, B:201:0x04dc, B:203:0x04e1, B:205:0x04ea, B:206:0x04fb, B:208:0x0504, B:209:0x0515, B:211:0x0521, B:216:0x052b, B:153:0x03ca, B:144:0x0394, B:146:0x03a4, B:149:0x03aa, B:148:0x03a8, B:132:0x035d, B:134:0x0366, B:135:0x036c, B:137:0x037c, B:140:0x0382, B:139:0x0380, B:126:0x032b, B:122:0x030f, B:118:0x02f4, B:114:0x02da, B:110:0x02bf, B:106:0x02a4, B:102:0x0289, B:95:0x0265, B:97:0x0275, B:98:0x0279, B:91:0x0247, B:87:0x022d, B:83:0x0215, B:79:0x01fc, B:75:0x01e4, B:71:0x01cd, B:67:0x01b7, B:63:0x01a0, B:59:0x0188, B:55:0x0171, B:51:0x0156, B:23:0x009f, B:26:0x00ab, B:36:0x00d4, B:38:0x00da, B:43:0x00fe, B:44:0x0103, B:47:0x0111, B:30:0x00b6, B:35:0x00d0, B:31:0x00c0, B:18:0x0074), top: B:222:0x0023 }] */
    /* JADX WARN: Removed duplicated region for block: B:82:0x0210 A[Catch: all -> 0x0532, TryCatch #0 {all -> 0x0532, blocks: (B:3:0x0023, B:5:0x0033, B:7:0x003d, B:9:0x0046, B:11:0x0054, B:13:0x005b, B:15:0x0065, B:17:0x006f, B:19:0x007f, B:21:0x0086, B:48:0x0130, B:50:0x0151, B:52:0x0165, B:54:0x016c, B:56:0x017c, B:58:0x0183, B:60:0x0193, B:62:0x019c, B:64:0x01ab, B:66:0x01b2, B:68:0x01c2, B:70:0x01c9, B:72:0x01d8, B:74:0x01df, B:76:0x01ef, B:78:0x01f8, B:80:0x0207, B:82:0x0210, B:84:0x0220, B:86:0x0229, B:88:0x023a, B:90:0x0243, B:92:0x0254, B:94:0x025d, B:99:0x027c, B:101:0x0285, B:103:0x0296, B:105:0x029f, B:107:0x02b1, B:109:0x02ba, B:111:0x02cc, B:113:0x02d5, B:115:0x02e7, B:117:0x02f0, B:119:0x0301, B:121:0x030a, B:123:0x031c, B:125:0x0325, B:127:0x0338, B:129:0x0341, B:131:0x034b, B:141:0x0385, B:143:0x038e, B:150:0x03ad, B:152:0x03b7, B:154:0x03cf, B:156:0x03d8, B:158:0x03e4, B:160:0x03ec, B:161:0x03f1, B:163:0x03f7, B:165:0x0400, B:169:0x0410, B:170:0x0413, B:172:0x041c, B:173:0x042d, B:175:0x0436, B:176:0x0443, B:178:0x044c, B:179:0x045d, B:181:0x0466, B:183:0x0472, B:185:0x0478, B:186:0x047f, B:188:0x0488, B:189:0x0495, B:191:0x049f, B:192:0x04ad, B:194:0x04b6, B:195:0x04c3, B:197:0x04cc, B:201:0x04dc, B:203:0x04e1, B:205:0x04ea, B:206:0x04fb, B:208:0x0504, B:209:0x0515, B:211:0x0521, B:216:0x052b, B:153:0x03ca, B:144:0x0394, B:146:0x03a4, B:149:0x03aa, B:148:0x03a8, B:132:0x035d, B:134:0x0366, B:135:0x036c, B:137:0x037c, B:140:0x0382, B:139:0x0380, B:126:0x032b, B:122:0x030f, B:118:0x02f4, B:114:0x02da, B:110:0x02bf, B:106:0x02a4, B:102:0x0289, B:95:0x0265, B:97:0x0275, B:98:0x0279, B:91:0x0247, B:87:0x022d, B:83:0x0215, B:79:0x01fc, B:75:0x01e4, B:71:0x01cd, B:67:0x01b7, B:63:0x01a0, B:59:0x0188, B:55:0x0171, B:51:0x0156, B:23:0x009f, B:26:0x00ab, B:36:0x00d4, B:38:0x00da, B:43:0x00fe, B:44:0x0103, B:47:0x0111, B:30:0x00b6, B:35:0x00d0, B:31:0x00c0, B:18:0x0074), top: B:222:0x0023 }] */
    /* JADX WARN: Removed duplicated region for block: B:83:0x0215 A[Catch: all -> 0x0532, TryCatch #0 {all -> 0x0532, blocks: (B:3:0x0023, B:5:0x0033, B:7:0x003d, B:9:0x0046, B:11:0x0054, B:13:0x005b, B:15:0x0065, B:17:0x006f, B:19:0x007f, B:21:0x0086, B:48:0x0130, B:50:0x0151, B:52:0x0165, B:54:0x016c, B:56:0x017c, B:58:0x0183, B:60:0x0193, B:62:0x019c, B:64:0x01ab, B:66:0x01b2, B:68:0x01c2, B:70:0x01c9, B:72:0x01d8, B:74:0x01df, B:76:0x01ef, B:78:0x01f8, B:80:0x0207, B:82:0x0210, B:84:0x0220, B:86:0x0229, B:88:0x023a, B:90:0x0243, B:92:0x0254, B:94:0x025d, B:99:0x027c, B:101:0x0285, B:103:0x0296, B:105:0x029f, B:107:0x02b1, B:109:0x02ba, B:111:0x02cc, B:113:0x02d5, B:115:0x02e7, B:117:0x02f0, B:119:0x0301, B:121:0x030a, B:123:0x031c, B:125:0x0325, B:127:0x0338, B:129:0x0341, B:131:0x034b, B:141:0x0385, B:143:0x038e, B:150:0x03ad, B:152:0x03b7, B:154:0x03cf, B:156:0x03d8, B:158:0x03e4, B:160:0x03ec, B:161:0x03f1, B:163:0x03f7, B:165:0x0400, B:169:0x0410, B:170:0x0413, B:172:0x041c, B:173:0x042d, B:175:0x0436, B:176:0x0443, B:178:0x044c, B:179:0x045d, B:181:0x0466, B:183:0x0472, B:185:0x0478, B:186:0x047f, B:188:0x0488, B:189:0x0495, B:191:0x049f, B:192:0x04ad, B:194:0x04b6, B:195:0x04c3, B:197:0x04cc, B:201:0x04dc, B:203:0x04e1, B:205:0x04ea, B:206:0x04fb, B:208:0x0504, B:209:0x0515, B:211:0x0521, B:216:0x052b, B:153:0x03ca, B:144:0x0394, B:146:0x03a4, B:149:0x03aa, B:148:0x03a8, B:132:0x035d, B:134:0x0366, B:135:0x036c, B:137:0x037c, B:140:0x0382, B:139:0x0380, B:126:0x032b, B:122:0x030f, B:118:0x02f4, B:114:0x02da, B:110:0x02bf, B:106:0x02a4, B:102:0x0289, B:95:0x0265, B:97:0x0275, B:98:0x0279, B:91:0x0247, B:87:0x022d, B:83:0x0215, B:79:0x01fc, B:75:0x01e4, B:71:0x01cd, B:67:0x01b7, B:63:0x01a0, B:59:0x0188, B:55:0x0171, B:51:0x0156, B:23:0x009f, B:26:0x00ab, B:36:0x00d4, B:38:0x00da, B:43:0x00fe, B:44:0x0103, B:47:0x0111, B:30:0x00b6, B:35:0x00d0, B:31:0x00c0, B:18:0x0074), top: B:222:0x0023 }] */
    /* JADX WARN: Removed duplicated region for block: B:86:0x0229 A[Catch: all -> 0x0532, TryCatch #0 {all -> 0x0532, blocks: (B:3:0x0023, B:5:0x0033, B:7:0x003d, B:9:0x0046, B:11:0x0054, B:13:0x005b, B:15:0x0065, B:17:0x006f, B:19:0x007f, B:21:0x0086, B:48:0x0130, B:50:0x0151, B:52:0x0165, B:54:0x016c, B:56:0x017c, B:58:0x0183, B:60:0x0193, B:62:0x019c, B:64:0x01ab, B:66:0x01b2, B:68:0x01c2, B:70:0x01c9, B:72:0x01d8, B:74:0x01df, B:76:0x01ef, B:78:0x01f8, B:80:0x0207, B:82:0x0210, B:84:0x0220, B:86:0x0229, B:88:0x023a, B:90:0x0243, B:92:0x0254, B:94:0x025d, B:99:0x027c, B:101:0x0285, B:103:0x0296, B:105:0x029f, B:107:0x02b1, B:109:0x02ba, B:111:0x02cc, B:113:0x02d5, B:115:0x02e7, B:117:0x02f0, B:119:0x0301, B:121:0x030a, B:123:0x031c, B:125:0x0325, B:127:0x0338, B:129:0x0341, B:131:0x034b, B:141:0x0385, B:143:0x038e, B:150:0x03ad, B:152:0x03b7, B:154:0x03cf, B:156:0x03d8, B:158:0x03e4, B:160:0x03ec, B:161:0x03f1, B:163:0x03f7, B:165:0x0400, B:169:0x0410, B:170:0x0413, B:172:0x041c, B:173:0x042d, B:175:0x0436, B:176:0x0443, B:178:0x044c, B:179:0x045d, B:181:0x0466, B:183:0x0472, B:185:0x0478, B:186:0x047f, B:188:0x0488, B:189:0x0495, B:191:0x049f, B:192:0x04ad, B:194:0x04b6, B:195:0x04c3, B:197:0x04cc, B:201:0x04dc, B:203:0x04e1, B:205:0x04ea, B:206:0x04fb, B:208:0x0504, B:209:0x0515, B:211:0x0521, B:216:0x052b, B:153:0x03ca, B:144:0x0394, B:146:0x03a4, B:149:0x03aa, B:148:0x03a8, B:132:0x035d, B:134:0x0366, B:135:0x036c, B:137:0x037c, B:140:0x0382, B:139:0x0380, B:126:0x032b, B:122:0x030f, B:118:0x02f4, B:114:0x02da, B:110:0x02bf, B:106:0x02a4, B:102:0x0289, B:95:0x0265, B:97:0x0275, B:98:0x0279, B:91:0x0247, B:87:0x022d, B:83:0x0215, B:79:0x01fc, B:75:0x01e4, B:71:0x01cd, B:67:0x01b7, B:63:0x01a0, B:59:0x0188, B:55:0x0171, B:51:0x0156, B:23:0x009f, B:26:0x00ab, B:36:0x00d4, B:38:0x00da, B:43:0x00fe, B:44:0x0103, B:47:0x0111, B:30:0x00b6, B:35:0x00d0, B:31:0x00c0, B:18:0x0074), top: B:222:0x0023 }] */
    /* JADX WARN: Removed duplicated region for block: B:87:0x022d A[Catch: all -> 0x0532, TryCatch #0 {all -> 0x0532, blocks: (B:3:0x0023, B:5:0x0033, B:7:0x003d, B:9:0x0046, B:11:0x0054, B:13:0x005b, B:15:0x0065, B:17:0x006f, B:19:0x007f, B:21:0x0086, B:48:0x0130, B:50:0x0151, B:52:0x0165, B:54:0x016c, B:56:0x017c, B:58:0x0183, B:60:0x0193, B:62:0x019c, B:64:0x01ab, B:66:0x01b2, B:68:0x01c2, B:70:0x01c9, B:72:0x01d8, B:74:0x01df, B:76:0x01ef, B:78:0x01f8, B:80:0x0207, B:82:0x0210, B:84:0x0220, B:86:0x0229, B:88:0x023a, B:90:0x0243, B:92:0x0254, B:94:0x025d, B:99:0x027c, B:101:0x0285, B:103:0x0296, B:105:0x029f, B:107:0x02b1, B:109:0x02ba, B:111:0x02cc, B:113:0x02d5, B:115:0x02e7, B:117:0x02f0, B:119:0x0301, B:121:0x030a, B:123:0x031c, B:125:0x0325, B:127:0x0338, B:129:0x0341, B:131:0x034b, B:141:0x0385, B:143:0x038e, B:150:0x03ad, B:152:0x03b7, B:154:0x03cf, B:156:0x03d8, B:158:0x03e4, B:160:0x03ec, B:161:0x03f1, B:163:0x03f7, B:165:0x0400, B:169:0x0410, B:170:0x0413, B:172:0x041c, B:173:0x042d, B:175:0x0436, B:176:0x0443, B:178:0x044c, B:179:0x045d, B:181:0x0466, B:183:0x0472, B:185:0x0478, B:186:0x047f, B:188:0x0488, B:189:0x0495, B:191:0x049f, B:192:0x04ad, B:194:0x04b6, B:195:0x04c3, B:197:0x04cc, B:201:0x04dc, B:203:0x04e1, B:205:0x04ea, B:206:0x04fb, B:208:0x0504, B:209:0x0515, B:211:0x0521, B:216:0x052b, B:153:0x03ca, B:144:0x0394, B:146:0x03a4, B:149:0x03aa, B:148:0x03a8, B:132:0x035d, B:134:0x0366, B:135:0x036c, B:137:0x037c, B:140:0x0382, B:139:0x0380, B:126:0x032b, B:122:0x030f, B:118:0x02f4, B:114:0x02da, B:110:0x02bf, B:106:0x02a4, B:102:0x0289, B:95:0x0265, B:97:0x0275, B:98:0x0279, B:91:0x0247, B:87:0x022d, B:83:0x0215, B:79:0x01fc, B:75:0x01e4, B:71:0x01cd, B:67:0x01b7, B:63:0x01a0, B:59:0x0188, B:55:0x0171, B:51:0x0156, B:23:0x009f, B:26:0x00ab, B:36:0x00d4, B:38:0x00da, B:43:0x00fe, B:44:0x0103, B:47:0x0111, B:30:0x00b6, B:35:0x00d0, B:31:0x00c0, B:18:0x0074), top: B:222:0x0023 }] */
    /* JADX WARN: Removed duplicated region for block: B:90:0x0243 A[Catch: all -> 0x0532, TryCatch #0 {all -> 0x0532, blocks: (B:3:0x0023, B:5:0x0033, B:7:0x003d, B:9:0x0046, B:11:0x0054, B:13:0x005b, B:15:0x0065, B:17:0x006f, B:19:0x007f, B:21:0x0086, B:48:0x0130, B:50:0x0151, B:52:0x0165, B:54:0x016c, B:56:0x017c, B:58:0x0183, B:60:0x0193, B:62:0x019c, B:64:0x01ab, B:66:0x01b2, B:68:0x01c2, B:70:0x01c9, B:72:0x01d8, B:74:0x01df, B:76:0x01ef, B:78:0x01f8, B:80:0x0207, B:82:0x0210, B:84:0x0220, B:86:0x0229, B:88:0x023a, B:90:0x0243, B:92:0x0254, B:94:0x025d, B:99:0x027c, B:101:0x0285, B:103:0x0296, B:105:0x029f, B:107:0x02b1, B:109:0x02ba, B:111:0x02cc, B:113:0x02d5, B:115:0x02e7, B:117:0x02f0, B:119:0x0301, B:121:0x030a, B:123:0x031c, B:125:0x0325, B:127:0x0338, B:129:0x0341, B:131:0x034b, B:141:0x0385, B:143:0x038e, B:150:0x03ad, B:152:0x03b7, B:154:0x03cf, B:156:0x03d8, B:158:0x03e4, B:160:0x03ec, B:161:0x03f1, B:163:0x03f7, B:165:0x0400, B:169:0x0410, B:170:0x0413, B:172:0x041c, B:173:0x042d, B:175:0x0436, B:176:0x0443, B:178:0x044c, B:179:0x045d, B:181:0x0466, B:183:0x0472, B:185:0x0478, B:186:0x047f, B:188:0x0488, B:189:0x0495, B:191:0x049f, B:192:0x04ad, B:194:0x04b6, B:195:0x04c3, B:197:0x04cc, B:201:0x04dc, B:203:0x04e1, B:205:0x04ea, B:206:0x04fb, B:208:0x0504, B:209:0x0515, B:211:0x0521, B:216:0x052b, B:153:0x03ca, B:144:0x0394, B:146:0x03a4, B:149:0x03aa, B:148:0x03a8, B:132:0x035d, B:134:0x0366, B:135:0x036c, B:137:0x037c, B:140:0x0382, B:139:0x0380, B:126:0x032b, B:122:0x030f, B:118:0x02f4, B:114:0x02da, B:110:0x02bf, B:106:0x02a4, B:102:0x0289, B:95:0x0265, B:97:0x0275, B:98:0x0279, B:91:0x0247, B:87:0x022d, B:83:0x0215, B:79:0x01fc, B:75:0x01e4, B:71:0x01cd, B:67:0x01b7, B:63:0x01a0, B:59:0x0188, B:55:0x0171, B:51:0x0156, B:23:0x009f, B:26:0x00ab, B:36:0x00d4, B:38:0x00da, B:43:0x00fe, B:44:0x0103, B:47:0x0111, B:30:0x00b6, B:35:0x00d0, B:31:0x00c0, B:18:0x0074), top: B:222:0x0023 }] */
    /* JADX WARN: Removed duplicated region for block: B:91:0x0247 A[Catch: all -> 0x0532, TryCatch #0 {all -> 0x0532, blocks: (B:3:0x0023, B:5:0x0033, B:7:0x003d, B:9:0x0046, B:11:0x0054, B:13:0x005b, B:15:0x0065, B:17:0x006f, B:19:0x007f, B:21:0x0086, B:48:0x0130, B:50:0x0151, B:52:0x0165, B:54:0x016c, B:56:0x017c, B:58:0x0183, B:60:0x0193, B:62:0x019c, B:64:0x01ab, B:66:0x01b2, B:68:0x01c2, B:70:0x01c9, B:72:0x01d8, B:74:0x01df, B:76:0x01ef, B:78:0x01f8, B:80:0x0207, B:82:0x0210, B:84:0x0220, B:86:0x0229, B:88:0x023a, B:90:0x0243, B:92:0x0254, B:94:0x025d, B:99:0x027c, B:101:0x0285, B:103:0x0296, B:105:0x029f, B:107:0x02b1, B:109:0x02ba, B:111:0x02cc, B:113:0x02d5, B:115:0x02e7, B:117:0x02f0, B:119:0x0301, B:121:0x030a, B:123:0x031c, B:125:0x0325, B:127:0x0338, B:129:0x0341, B:131:0x034b, B:141:0x0385, B:143:0x038e, B:150:0x03ad, B:152:0x03b7, B:154:0x03cf, B:156:0x03d8, B:158:0x03e4, B:160:0x03ec, B:161:0x03f1, B:163:0x03f7, B:165:0x0400, B:169:0x0410, B:170:0x0413, B:172:0x041c, B:173:0x042d, B:175:0x0436, B:176:0x0443, B:178:0x044c, B:179:0x045d, B:181:0x0466, B:183:0x0472, B:185:0x0478, B:186:0x047f, B:188:0x0488, B:189:0x0495, B:191:0x049f, B:192:0x04ad, B:194:0x04b6, B:195:0x04c3, B:197:0x04cc, B:201:0x04dc, B:203:0x04e1, B:205:0x04ea, B:206:0x04fb, B:208:0x0504, B:209:0x0515, B:211:0x0521, B:216:0x052b, B:153:0x03ca, B:144:0x0394, B:146:0x03a4, B:149:0x03aa, B:148:0x03a8, B:132:0x035d, B:134:0x0366, B:135:0x036c, B:137:0x037c, B:140:0x0382, B:139:0x0380, B:126:0x032b, B:122:0x030f, B:118:0x02f4, B:114:0x02da, B:110:0x02bf, B:106:0x02a4, B:102:0x0289, B:95:0x0265, B:97:0x0275, B:98:0x0279, B:91:0x0247, B:87:0x022d, B:83:0x0215, B:79:0x01fc, B:75:0x01e4, B:71:0x01cd, B:67:0x01b7, B:63:0x01a0, B:59:0x0188, B:55:0x0171, B:51:0x0156, B:23:0x009f, B:26:0x00ab, B:36:0x00d4, B:38:0x00da, B:43:0x00fe, B:44:0x0103, B:47:0x0111, B:30:0x00b6, B:35:0x00d0, B:31:0x00c0, B:18:0x0074), top: B:222:0x0023 }] */
    /* JADX WARN: Removed duplicated region for block: B:94:0x025d A[Catch: all -> 0x0532, TryCatch #0 {all -> 0x0532, blocks: (B:3:0x0023, B:5:0x0033, B:7:0x003d, B:9:0x0046, B:11:0x0054, B:13:0x005b, B:15:0x0065, B:17:0x006f, B:19:0x007f, B:21:0x0086, B:48:0x0130, B:50:0x0151, B:52:0x0165, B:54:0x016c, B:56:0x017c, B:58:0x0183, B:60:0x0193, B:62:0x019c, B:64:0x01ab, B:66:0x01b2, B:68:0x01c2, B:70:0x01c9, B:72:0x01d8, B:74:0x01df, B:76:0x01ef, B:78:0x01f8, B:80:0x0207, B:82:0x0210, B:84:0x0220, B:86:0x0229, B:88:0x023a, B:90:0x0243, B:92:0x0254, B:94:0x025d, B:99:0x027c, B:101:0x0285, B:103:0x0296, B:105:0x029f, B:107:0x02b1, B:109:0x02ba, B:111:0x02cc, B:113:0x02d5, B:115:0x02e7, B:117:0x02f0, B:119:0x0301, B:121:0x030a, B:123:0x031c, B:125:0x0325, B:127:0x0338, B:129:0x0341, B:131:0x034b, B:141:0x0385, B:143:0x038e, B:150:0x03ad, B:152:0x03b7, B:154:0x03cf, B:156:0x03d8, B:158:0x03e4, B:160:0x03ec, B:161:0x03f1, B:163:0x03f7, B:165:0x0400, B:169:0x0410, B:170:0x0413, B:172:0x041c, B:173:0x042d, B:175:0x0436, B:176:0x0443, B:178:0x044c, B:179:0x045d, B:181:0x0466, B:183:0x0472, B:185:0x0478, B:186:0x047f, B:188:0x0488, B:189:0x0495, B:191:0x049f, B:192:0x04ad, B:194:0x04b6, B:195:0x04c3, B:197:0x04cc, B:201:0x04dc, B:203:0x04e1, B:205:0x04ea, B:206:0x04fb, B:208:0x0504, B:209:0x0515, B:211:0x0521, B:216:0x052b, B:153:0x03ca, B:144:0x0394, B:146:0x03a4, B:149:0x03aa, B:148:0x03a8, B:132:0x035d, B:134:0x0366, B:135:0x036c, B:137:0x037c, B:140:0x0382, B:139:0x0380, B:126:0x032b, B:122:0x030f, B:118:0x02f4, B:114:0x02da, B:110:0x02bf, B:106:0x02a4, B:102:0x0289, B:95:0x0265, B:97:0x0275, B:98:0x0279, B:91:0x0247, B:87:0x022d, B:83:0x0215, B:79:0x01fc, B:75:0x01e4, B:71:0x01cd, B:67:0x01b7, B:63:0x01a0, B:59:0x0188, B:55:0x0171, B:51:0x0156, B:23:0x009f, B:26:0x00ab, B:36:0x00d4, B:38:0x00da, B:43:0x00fe, B:44:0x0103, B:47:0x0111, B:30:0x00b6, B:35:0x00d0, B:31:0x00c0, B:18:0x0074), top: B:222:0x0023 }] */
    /* JADX WARN: Removed duplicated region for block: B:95:0x0265 A[Catch: all -> 0x0532, TryCatch #0 {all -> 0x0532, blocks: (B:3:0x0023, B:5:0x0033, B:7:0x003d, B:9:0x0046, B:11:0x0054, B:13:0x005b, B:15:0x0065, B:17:0x006f, B:19:0x007f, B:21:0x0086, B:48:0x0130, B:50:0x0151, B:52:0x0165, B:54:0x016c, B:56:0x017c, B:58:0x0183, B:60:0x0193, B:62:0x019c, B:64:0x01ab, B:66:0x01b2, B:68:0x01c2, B:70:0x01c9, B:72:0x01d8, B:74:0x01df, B:76:0x01ef, B:78:0x01f8, B:80:0x0207, B:82:0x0210, B:84:0x0220, B:86:0x0229, B:88:0x023a, B:90:0x0243, B:92:0x0254, B:94:0x025d, B:99:0x027c, B:101:0x0285, B:103:0x0296, B:105:0x029f, B:107:0x02b1, B:109:0x02ba, B:111:0x02cc, B:113:0x02d5, B:115:0x02e7, B:117:0x02f0, B:119:0x0301, B:121:0x030a, B:123:0x031c, B:125:0x0325, B:127:0x0338, B:129:0x0341, B:131:0x034b, B:141:0x0385, B:143:0x038e, B:150:0x03ad, B:152:0x03b7, B:154:0x03cf, B:156:0x03d8, B:158:0x03e4, B:160:0x03ec, B:161:0x03f1, B:163:0x03f7, B:165:0x0400, B:169:0x0410, B:170:0x0413, B:172:0x041c, B:173:0x042d, B:175:0x0436, B:176:0x0443, B:178:0x044c, B:179:0x045d, B:181:0x0466, B:183:0x0472, B:185:0x0478, B:186:0x047f, B:188:0x0488, B:189:0x0495, B:191:0x049f, B:192:0x04ad, B:194:0x04b6, B:195:0x04c3, B:197:0x04cc, B:201:0x04dc, B:203:0x04e1, B:205:0x04ea, B:206:0x04fb, B:208:0x0504, B:209:0x0515, B:211:0x0521, B:216:0x052b, B:153:0x03ca, B:144:0x0394, B:146:0x03a4, B:149:0x03aa, B:148:0x03a8, B:132:0x035d, B:134:0x0366, B:135:0x036c, B:137:0x037c, B:140:0x0382, B:139:0x0380, B:126:0x032b, B:122:0x030f, B:118:0x02f4, B:114:0x02da, B:110:0x02bf, B:106:0x02a4, B:102:0x0289, B:95:0x0265, B:97:0x0275, B:98:0x0279, B:91:0x0247, B:87:0x022d, B:83:0x0215, B:79:0x01fc, B:75:0x01e4, B:71:0x01cd, B:67:0x01b7, B:63:0x01a0, B:59:0x0188, B:55:0x0171, B:51:0x0156, B:23:0x009f, B:26:0x00ab, B:36:0x00d4, B:38:0x00da, B:43:0x00fe, B:44:0x0103, B:47:0x0111, B:30:0x00b6, B:35:0x00d0, B:31:0x00c0, B:18:0x0074), top: B:222:0x0023 }] */
    @Override // com.amazon.mp3.library.item.factory.LibraryItemFactory
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public com.amazon.mp3.library.item.MusicTrack populateTrack(android.database.Cursor r22, com.amazon.mp3.library.item.MusicTrack r23) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 1335
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.mp3.library.item.factory.CirrusSourceLibraryItemFactory.populateTrack(android.database.Cursor, com.amazon.mp3.library.item.MusicTrack):com.amazon.mp3.library.item.MusicTrack");
    }

    @Override // com.amazon.mp3.library.item.factory.LibraryItemFactory
    public String[] getTrackProjection() {
        String[] strArr = {"_id", "luid", PageUriUtils.SOURCE_QUERY_PARAM, "match_hash", "title", "sort_title", "asin", "album_id", "album_asin", "album_art_id", "album_artist_id", "artist_id", "artist_asin", "track_num", "disc_num", "local_uri", "remote_uri", "artist", "album", "album_art_id", "album_artist", "size", "extension", "duration", "download_state", IntlConfiguration.MARKETPLACE, "prime_status", "ownership_status", "is_explicit", "popularity", "content_encoding", "playlist_track_status", "asset_qualities", "asset_type", "date_updated", "date_created", "is_exclusion_tagged", "playmode_eligibility", "catalog_status_tiers"};
        if (!AmazonApplication.getCapabilities().isLyricsSupported()) {
            return strArr;
        }
        ArrayList arrayList = new ArrayList();
        Collections.addAll(arrayList, strArr);
        arrayList.add("lyrics_state");
        return (String[]) arrayList.toArray(strArr);
    }

    @Override // com.amazon.mp3.library.item.factory.LibraryItemFactory
    public String[] getAlbumTrackSortProjection() {
        String[] strArr = {"_id", "luid", PageUriUtils.SOURCE_QUERY_PARAM, "match_hash", "title", "sort_album", "asin", "album_id", "album_asin", "album_art_id", "album_artist_id", "artist_id", "artist_asin", "track_num", "disc_num", "local_uri", "remote_uri", "artist", "album", "album_art_id", "album_artist", "size", "extension", "duration", "download_state", IntlConfiguration.MARKETPLACE, "prime_status", "ownership_status", "is_explicit", "content_encoding"};
        if (!AmazonApplication.getCapabilities().isLyricsSupported()) {
            return strArr;
        }
        ArrayList arrayList = new ArrayList();
        Collections.addAll(arrayList, strArr);
        arrayList.add("lyrics_state");
        return (String[]) arrayList.toArray(strArr);
    }

    @Override // com.amazon.mp3.library.item.factory.LibraryItemFactory
    public MusicTrack getTrack(Uri contentUri) {
        return getTrack(contentUri, Boolean.FALSE);
    }

    @Override // com.amazon.mp3.library.item.factory.LibraryItemFactory
    public MusicTrack getTrackIncludingPrimePlaylistTrack(Uri contentUri) {
        MusicTrack track = getTrack(contentUri);
        return (track == null && MediaProvider.Tracks.isPrimeAdditionalTrack(contentUri)) ? getTrack(contentUri, Boolean.TRUE) : track;
    }

    private MusicTrack getTrack(Uri uri, Boolean bool) {
        try {
            Profiler.begin("Get track from content uri");
            Cursor cursor = null;
            MusicTrack track = null;
            if (!MediaProvider.Tracks.isTrack(uri)) {
                return null;
            }
            try {
                Cursor primePlaylistTrackCursor = bool.booleanValue() ? getPrimePlaylistTrackCursor(uri, this.context) : this.mContentResolver.query(uri, getTrackProjection(), null, null, null);
                if (primePlaylistTrackCursor == null) {
                    DbUtil.closeCursor(primePlaylistTrackCursor);
                    return null;
                }
                while (primePlaylistTrackCursor.moveToNext()) {
                    try {
                        track = getTrack(primePlaylistTrackCursor);
                        if (track.getAsin() != null && !track.getAsin().equals(track.getLuid())) {
                            DbUtil.closeCursor(primePlaylistTrackCursor);
                            return track;
                        }
                    } catch (Throwable th) {
                        cursor = primePlaylistTrackCursor;
                        th = th;
                        DbUtil.closeCursor(cursor);
                        throw th;
                    }
                }
                DbUtil.closeCursor(primePlaylistTrackCursor);
                return track;
            } catch (Throwable th2) {
                th = th2;
            }
        } finally {
            Profiler.end();
        }
    }

    @Override // com.amazon.mp3.library.item.factory.LibraryItemFactory
    public Cursor getPrimePlaylistTracksCursor(final Uri[] contentUris, Context context) {
        SQLiteDatabase readOnlyDatabase = CirrusDatabase.getReadOnlyDatabase(context);
        DbUtil.WhereClause whereClause = new DbUtil.WhereClause("PrimePlaylistTracks.asin", (List) ((Stream) Arrays.stream(contentUris).sequential()).map(new Function() { // from class: com.amazon.mp3.library.item.factory.CirrusSourceLibraryItemFactory$$ExternalSyntheticLambda0
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return MediaProvider.Tracks.getPrimeAdditionalTrackAsin((Uri) obj);
            }
        }).collect(Collectors.toList()), contentUris.length);
        SQLiteQueryBuilder sQLiteQueryBuilder = new SQLiteQueryBuilder();
        sQLiteQueryBuilder.setTables("PrimePlaylistToTrack LEFT JOIN PrimePlaylistTracks ON PrimePlaylistToTrack.track_asin=PrimePlaylistTracks.asin LEFT JOIN Track ON PrimePlaylistToTrack.track_asin=Track.asin LEFT JOIN LocalTrackUri ON (LocalTrackUri.track_luid IS NULL OR LocalTrackUri.track_luid='') AND PrimePlaylistTracks.asin=LocalTrackUri.track_asin");
        return sQLiteQueryBuilder.query(readOnlyDatabase, CirrusMediaSource.PRIME_PLAYLISTS_TRACK_PROJECTION, whereClause.getClause(), whereClause.getArgs(), null, null, null, String.valueOf(contentUris.length));
    }

    private Cursor getPrimePlaylistTrackCursor(final Uri uri, final Context context) {
        return getPrimePlaylistTracksCursor(new Uri[]{uri}, context);
    }

    @Override // com.amazon.mp3.library.item.factory.LibraryItemFactory
    public MusicTrack getTrack(String str, long j) {
        try {
            Profiler.begin("Get track from source / match hash");
            Cursor cursor = null;
            track = null;
            track = null;
            MusicTrack track = null;
            if (TextUtils.isEmpty(str)) {
                return null;
            }
            try {
                Cursor cursorQuery = this.mContentResolver.query(MediaProvider.Tracks.getContentUri(str), getTrackProjection(), "match_hash = ?", new String[]{Long.toString(j)}, null);
                if (cursorQuery != null) {
                    try {
                        if (cursorQuery.getCount() == 1 && cursorQuery.moveToFirst()) {
                            track = getTrack(cursorQuery);
                        }
                    } catch (Throwable th) {
                        th = th;
                        cursor = cursorQuery;
                        DbUtil.closeCursor(cursor);
                        throw th;
                    }
                }
                DbUtil.closeCursor(cursorQuery);
                return track;
            } catch (Throwable th2) {
                th = th2;
            }
        } finally {
            Profiler.end();
        }
    }

    @Override // com.amazon.mp3.library.item.factory.LibraryItemFactory
    public Album getAlbum(Cursor albumCursor) {
        return populateAlbum(albumCursor, new Album(this, null));
    }

    @Override // com.amazon.mp3.library.item.factory.LibraryItemFactory
    public Album populateAlbum(Cursor albumCursor, Album album) {
        Uri contentUri;
        try {
            Profiler.begin("Get album from cursor");
            long j = albumCursor.getLong(albumCursor.getColumnIndex("track_count"));
            if (j != 0) {
                long j2 = albumCursor.getLong(albumCursor.getColumnIndex("_id"));
                if (albumCursor.getInt(albumCursor.getColumnIndex(PageUriUtils.SOURCE_QUERY_PARAM)) == 1) {
                    contentUri = MediaProvider.Albums.getContentUri("cirrus-local", j2);
                } else {
                    contentUri = MediaProvider.Albums.getContentUri("cirrus", j2);
                }
                album.setContentUri(contentUri);
                album.setId(Long.toString(j2));
                album.setAsin(albumCursor.getString(albumCursor.getColumnIndex("album_asin")));
                album.setArtistAsin(albumCursor.getString(albumCursor.getColumnIndex("artist_asin")));
                album.setTitle(albumCursor.getString(albumCursor.getColumnIndex("title")));
                album.setSortTitle(albumCursor.getString(albumCursor.getColumnIndex("sort_title")));
                album.setTrackCount(j);
                album.setArtistName(albumCursor.getString(albumCursor.getColumnIndex("artist")));
                album.setSortArtistName(albumCursor.getString(albumCursor.getColumnIndex("sort_artist")));
                album.setArtistId(albumCursor.getLong(albumCursor.getColumnIndex("artist_id")));
                album.setAlbumArtId(albumCursor.getLong(albumCursor.getColumnIndex("album_art_id")));
                album.setDownloadState(albumCursor.getInt(albumCursor.getColumnIndex("download_state")));
                album.setMarketplace(albumCursor.getString(albumCursor.getColumnIndex(IntlConfiguration.MARKETPLACE)));
                album.setPlaymodeEligibility(EligibilityConverterUtil.getPlaymodeEligibilityFromJson(albumCursor.getString(albumCursor.getColumnIndex("playmode_eligibility"))));
                int i = albumCursor.getInt(albumCursor.getColumnIndex(SqliteLibrarySearchDao.CONTENT_OWNERSHIP_STATUS_MIN));
                int i2 = albumCursor.getInt(albumCursor.getColumnIndex(SqliteLibrarySearchDao.CONTENT_OWNERSHIP_STATUS_MAX));
                int i3 = albumCursor.getInt(albumCursor.getColumnIndex(SqliteLibrarySearchDao.CONTENT_CATALOG_STATUS_MIN));
                int i4 = albumCursor.getInt(albumCursor.getColumnIndex("content_catalog_status_max_non_previous"));
                int i5 = albumCursor.getInt(albumCursor.getColumnIndex(SqliteLibrarySearchDao.CONTENT_CATALOG_STATUS_MAX));
                album.setMinContentOwnershipStatusValue(i);
                album.setMaxContentOwnershipStatusValue(i2);
                album.setMinContentCatalogStatusValue(i3);
                album.setMaxNonPreviousContentCatalogStatusValue(i4);
                album.setMaxContentCatalogStatusValue(i5);
                if (AmazonApplication.getCapabilities().isAlbumBadgingInLibrarySupported()) {
                    album.setContentEncodings(ContentEncodingDecoder.getAggregatedContentEncoding(albumCursor.getString(albumCursor.getColumnIndex("content_encoding"))));
                }
                return album;
            }
            return null;
        } finally {
            Profiler.end();
        }
    }

    @Override // com.amazon.mp3.library.item.factory.LibraryItemFactory
    public String[] getAlbumProjection() {
        return new String[]{PageUriUtils.SOURCE_QUERY_PARAM, "_id", "title", "album_asin", "artist_asin", "sort_title", "track_count", "artist", "sort_artist", "artist_id", "album_art_id", "download_status", "download_state", IntlConfiguration.MARKETPLACE, SqliteLibrarySearchDao.CONTENT_CATALOG_STATUS_MIN, SqliteLibrarySearchDao.CONTENT_OWNERSHIP_STATUS_MIN, "content_catalog_status_max_non_previous", SqliteLibrarySearchDao.CONTENT_CATALOG_STATUS_MAX, SqliteLibrarySearchDao.CONTENT_OWNERSHIP_STATUS_MAX, "content_encoding", "playmode_eligibility"};
    }

    public String getAlbumSelection() {
        return DeluxeContentUtil.getNonDeluxeSelection();
    }

    public String[] getAlbumSelectionArgs() {
        return DeluxeContentUtil.getNonDeluxeSelectionArgs();
    }

    public boolean isAlbumAvailableOffline(Uri contentUri) {
        if (contentUri == null) {
            return false;
        }
        if (!contentUri.toString().contains("cirrus-local")) {
            long albumId = MediaProvider.Albums.getAlbumId(contentUri);
            if (albumId == Long.MIN_VALUE) {
                return false;
            }
            contentUri = MediaProvider.Albums.getContentUri("cirrus-local", albumId);
        }
        return getAlbum(contentUri) != null;
    }

    public boolean isArtistAvailableOffline(Uri contentUri) {
        if (contentUri == null) {
            return false;
        }
        if (!contentUri.toString().contains("cirrus-local")) {
            long artistId = MediaProvider.Artists.getArtistId(contentUri);
            if (artistId == Long.MIN_VALUE) {
                return false;
            }
            contentUri = MediaProvider.Artists.getContentUri("cirrus-local", artistId);
        }
        return getArtist(contentUri) != null;
    }

    /* JADX WARN: Removed duplicated region for block: B:18:0x0040  */
    @Override // com.amazon.mp3.library.item.factory.LibraryItemFactory
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public com.amazon.mp3.library.item.Album getAlbum(android.net.Uri r9) {
        /*
            r8 = this;
            java.lang.String r0 = "Get album from content uri"
            com.amazon.mp3.util.Profiler.begin(r0)     // Catch: java.lang.Throwable -> L53
            boolean r0 = com.amazon.mp3.library.provider.MediaProvider.Albums.isAlbum(r9)     // Catch: java.lang.Throwable -> L53
            r1 = 0
            if (r0 != 0) goto L10
            com.amazon.mp3.util.Profiler.end()
            return r1
        L10:
            com.amazon.mp3.playback.state.ContentPlaybackUtils r0 = com.amazon.mp3.playback.state.ContentPlaybackUtils.INSTANCE     // Catch: java.lang.Throwable -> L53
            android.net.Uri r3 = r0.removeTracksFromUriIfNeeded(r9)     // Catch: java.lang.Throwable -> L53
            java.lang.String[] r4 = r8.getAlbumProjection()     // Catch: java.lang.Throwable -> L53
            android.content.ContentResolver r2 = r8.mContentResolver     // Catch: java.lang.Throwable -> L4e
            java.lang.String r5 = r8.getAlbumSelection()     // Catch: java.lang.Throwable -> L4e
            java.lang.String[] r6 = r8.getAlbumSelectionArgs()     // Catch: java.lang.Throwable -> L4e
            r7 = 0
            android.database.Cursor r9 = r2.query(r3, r4, r5, r6, r7)     // Catch: java.lang.Throwable -> L4e
            if (r9 == 0) goto L40
            int r0 = r9.getCount()     // Catch: java.lang.Throwable -> L3d
            r2 = 1
            if (r0 != r2) goto L40
            boolean r0 = r9.moveToFirst()     // Catch: java.lang.Throwable -> L3d
            if (r0 == 0) goto L40
            com.amazon.mp3.library.item.Album r0 = r8.getAlbum(r9)     // Catch: java.lang.Throwable -> L3d
            goto L41
        L3d:
            r0 = move-exception
            r1 = r9
            goto L4f
        L40:
            r0 = r1
        L41:
            com.amazon.mp3.util.DbUtil.closeCursor(r9)     // Catch: java.lang.Throwable -> L53
            if (r0 == 0) goto L4a
            com.amazon.mp3.util.Profiler.end()
            return r0
        L4a:
            com.amazon.mp3.util.Profiler.end()
            return r1
        L4e:
            r0 = move-exception
        L4f:
            com.amazon.mp3.util.DbUtil.closeCursor(r1)     // Catch: java.lang.Throwable -> L53
            throw r0     // Catch: java.lang.Throwable -> L53
        L53:
            r9 = move-exception
            com.amazon.mp3.util.Profiler.end()
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.mp3.library.item.factory.CirrusSourceLibraryItemFactory.getAlbum(android.net.Uri):com.amazon.mp3.library.item.Album");
    }

    public Album getAlbum(String source, long albumId) {
        return getAlbum(MediaProvider.Albums.getContentUri(source, albumId));
    }

    @Override // com.amazon.mp3.library.item.factory.LibraryItemFactory
    public Artist getArtist(Cursor artistCursor) {
        return populateArtist(artistCursor, new Artist(this, null));
    }

    @Override // com.amazon.mp3.library.item.factory.LibraryItemFactory
    public Artist populateArtist(Cursor artistCursor, Artist artist) {
        Uri contentUri;
        try {
            Profiler.begin("Get artist from cursor");
            String string = artistCursor.getString(artistCursor.getColumnIndex("name"));
            artist.setName(string);
            artist.setSortName(artistCursor.getString(artistCursor.getColumnIndex("sort_name")));
            artist.setAsin(artistCursor.getString(artistCursor.getColumnIndex("artist_asin")));
            if (AmazonApplication.getCapabilities().isTrueLikesAndFollowsEnabled()) {
                long jGenerateArtistId = IdGenerator.generateArtistId(string);
                artist.setId(Long.toString(jGenerateArtistId));
                artist.setContentUri(MediaProvider.Artists.getContentUri("cirrus", jGenerateArtistId));
                int columnIndex = artistCursor.getColumnIndex("follow_status");
                if (columnIndex > -1) {
                    artist.setFollowStatus(artistCursor.getInt(columnIndex));
                }
                int columnIndex2 = artistCursor.getColumnIndex("date_created");
                if (columnIndex2 > -1) {
                    artist.setDateFollowed(artistCursor.getLong(columnIndex2));
                }
            } else {
                long j = artistCursor.getLong(artistCursor.getColumnIndex("_id"));
                artist.setId(Long.toString(j));
                if (artistCursor.getInt(artistCursor.getColumnIndex(PageUriUtils.SOURCE_QUERY_PARAM)) == 1) {
                    contentUri = MediaProvider.Artists.getContentUri("cirrus-local", j);
                } else {
                    contentUri = MediaProvider.Artists.getContentUri("cirrus", j);
                }
                artist.setContentUri(contentUri);
                artist.setDownloadState(artistCursor.getInt(artistCursor.getColumnIndex("download_state")));
                artist.setMarketplace(artistCursor.getString(artistCursor.getColumnIndex(IntlConfiguration.MARKETPLACE)));
                artist.setMinContentOwnershipStatusValue(artistCursor.getInt(artistCursor.getColumnIndex(SqliteLibrarySearchDao.CONTENT_OWNERSHIP_STATUS_MIN)));
                artist.setMaxContentOwnershipStatusValue(artistCursor.getInt(artistCursor.getColumnIndex(SqliteLibrarySearchDao.CONTENT_OWNERSHIP_STATUS_MAX)));
                artist.setMinContentCatalogStatusValue(artistCursor.getInt(artistCursor.getColumnIndex(SqliteLibrarySearchDao.CONTENT_CATALOG_STATUS_MIN)));
                artist.setMaxNonPreviousContentCatalogStatusValue(artistCursor.getInt(artistCursor.getColumnIndex("content_catalog_status_max_non_previous")));
                artist.setMaxContentCatalogStatusValue(artistCursor.getInt(artistCursor.getColumnIndex(SqliteLibrarySearchDao.CONTENT_CATALOG_STATUS_MAX)));
                artist.setAlbumCount(artistCursor.getLong(artistCursor.getColumnIndex("album_count")));
                artist.setTrackCount(artistCursor.getLong(artistCursor.getColumnIndex("track_count")));
            }
            return artist;
        } catch (Exception e) {
            Log.debug(TAG, "Error parsing the artists cause by ", e);
            return artist;
        } finally {
            Profiler.end();
        }
    }

    public String[] getArtistProjection() {
        return new String[]{PageUriUtils.SOURCE_QUERY_PARAM, "Track._id", "artist as name", "sort_artist as sort_name", "count(distinct album_id) as album_count", "artist_asin as artist_asin", "count(distinct match_hash) as track_count", "download_state", IntlConfiguration.MARKETPLACE, "min(ownership_status) as content_ownership_status_min", "max(ownership_status) as content_ownership_status_max", "min(prime_status) as content_catalog_status_min", CirrusMediaSource.MAX_NON_PREVIOUS_QUERY + "content_catalog_status_max_non_previous", "max(prime_status) as content_catalog_status_max"};
    }

    public String getArtistSelection() {
        return DeluxeContentUtil.getNonDeluxeSelection();
    }

    public String[] getArtistSelectionArgs() {
        return DeluxeContentUtil.getNonDeluxeSelectionArgs();
    }

    @Override // com.amazon.mp3.library.item.factory.LibraryItemFactory
    public Artist getArtist(Uri uri) {
        try {
            Profiler.begin("Get artist from contentUri");
            Cursor cursor = null;
            artist = null;
            artist = null;
            Artist artist = null;
            if (!MediaProvider.Artists.isArtist(uri)) {
                return null;
            }
            try {
                Cursor cursorQuery = this.mContentResolver.query(uri, getArtistProjection(), getArtistSelection(), getArtistSelectionArgs(), null);
                if (cursorQuery != null) {
                    try {
                        if (cursorQuery.getCount() == 1 && cursorQuery.moveToFirst()) {
                            artist = getArtist(cursorQuery);
                        }
                    } catch (Throwable th) {
                        th = th;
                        cursor = cursorQuery;
                        DbUtil.closeCursor(cursor);
                        throw th;
                    }
                }
                DbUtil.closeCursor(cursorQuery);
                return artist;
            } catch (Throwable th2) {
                th = th2;
            }
        } finally {
            Profiler.end();
        }
    }

    @Override // com.amazon.mp3.library.item.factory.LibraryItemFactory
    public Playlist getPlaylist(Cursor playlistCursor, String source) {
        return populatePlaylist(playlistCursor, new Playlist(this, null), source);
    }

    @Override // com.amazon.mp3.library.item.factory.LibraryItemFactory
    public Playlist populatePlaylist(Cursor playlistCursor, Playlist playlist, String playlistSource) {
        Uri contentUri;
        try {
            Profiler.begin("Getting playlist for cursor");
            long j = playlistCursor.getLong(playlistCursor.getColumnIndex("_id"));
            playlist.setId(Long.toString(j));
            playlist.setName(playlistCursor.getString(playlistCursor.getColumnIndex("name")));
            playlist.setType(playlistCursor.getString(playlistCursor.getColumnIndex(AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE)));
            playlist.setDownloadState(playlistCursor.getInt(playlistCursor.getColumnIndex("download_state")));
            playlist.setLastTouchedDate(playlistCursor.getLong(playlistCursor.getColumnIndex("dateLastTouched")));
            playlist.setLastModifiedDate(playlistCursor.getDouble(playlistCursor.getColumnIndex("dateLastModified")));
            playlist.setCreatedDate(playlistCursor.getLong(playlistCursor.getColumnIndex("dateCreated")));
            playlist.setVisibilityFromCursor(playlistCursor);
            int columnIndex = playlistCursor.getColumnIndex("luid");
            if (columnIndex >= 0) {
                playlist.setLuid(playlistCursor.getString(columnIndex));
            }
            int columnIndex2 = playlistCursor.getColumnIndex("version");
            if (columnIndex2 >= 0) {
                playlist.setVersion(playlistCursor.getString(columnIndex2));
            }
            int columnIndex3 = playlistCursor.getColumnIndex("asin");
            if (columnIndex3 >= 0) {
                playlist.setAsin(playlistCursor.getString(columnIndex3));
            }
            playlist.setTrackCount(playlistCursor.getInt(playlistCursor.getColumnIndex("track_count")));
            String str = playlistCursor.getInt(playlistCursor.getColumnIndex(PageUriUtils.SOURCE_QUERY_PARAM)) == 1 ? "cirrus-local" : "cirrus";
            if (playlistSource == null) {
                playlistSource = str;
            }
            playlist.setSource(playlistSource);
            String type = playlist.getType();
            if ("vnd.android.cursor.item/vnd.amazonmp3.primeplaylist".equals(type)) {
                contentUri = MediaProvider.PrimePlaylists.getContentUri(playlist.getSource(), playlist.getAsin());
            } else if ("vnd.android.cursor.item/vnd.amazonmp3.smartplaylist".equals(type)) {
                contentUri = MediaProvider.SmartPlaylists.getContentUri(playlist.getSource(), j);
            } else {
                contentUri = MediaProvider.UdoPlaylists.getContentUri(playlist.getSource(), j);
            }
            playlist.setContentUri(contentUri);
            boolean z = "vnd.android.cursor.item/vnd.amazonmp3.smartplaylist".equals(type) || "vnd.android.cursor.item/vnd.amazonmp3.udoplaylist".equals(type);
            boolean z2 = z || playlistCursor.getInt(playlistCursor.getColumnIndex("is_prime")) == Integer.parseInt("1");
            int columnIndex4 = playlistCursor.getColumnIndex("is_sonic_rush");
            boolean z3 = columnIndex4 != -1 && playlistCursor.getInt(columnIndex4) == 1;
            int columnIndex5 = playlistCursor.getColumnIndex("is_sonic_rush_on_demand");
            boolean z4 = columnIndex5 != -1 && playlistCursor.getInt(columnIndex5) == 1;
            int columnIndex6 = playlistCursor.getColumnIndex("is_sonic_rush_golden");
            playlist.setCatalogStatusTiers(new CatalogStatusTiers(z2, !z2, z, false, z3 || z, z4, columnIndex6 != -1 && playlistCursor.getInt(columnIndex6) == 1));
            int columnIndex7 = playlistCursor.getColumnIndex("playmode_eligibility");
            if (columnIndex7 != -1) {
                playlist.setPlaymodeEligibility(EligibilityConverterUtil.getPlaymodeEligibilityFromJson(playlistCursor.getString(columnIndex7)));
            }
            int columnIndex8 = playlistCursor.getColumnIndex("shared_id");
            if (columnIndex8 >= 0) {
                playlist.setSharedId(playlistCursor.getString(columnIndex8));
            }
            int columnIndex9 = playlistCursor.getColumnIndex("numberOfFollowers");
            if (columnIndex9 >= 0) {
                playlist.setNumberOfFollowers(playlistCursor.getInt(columnIndex9));
            }
            return playlist;
        } finally {
            Profiler.end();
        }
    }

    @Override // com.amazon.mp3.library.item.factory.LibraryItemFactory
    public Playlist getPlaylist(Uri uri) {
        try {
            Profiler.begin("Getting playlist for content uri");
            Cursor cursor = null;
            playlist = null;
            playlist = null;
            Playlist playlist = null;
            if (!MediaProvider.Playlists.isPlaylist(uri)) {
                return null;
            }
            try {
                Cursor cursorQuery = this.mContentResolver.query(uri, null, null, null, null);
                if (cursorQuery != null) {
                    try {
                        if (cursorQuery.getCount() == 1 && cursorQuery.moveToFirst()) {
                            playlist = new Playlist(this, uri);
                            playlist.setId(Long.toString(MediaProvider.Playlists.getPlaylistId(uri)));
                            int columnIndex = cursorQuery.getColumnIndex("name");
                            if (columnIndex >= 0) {
                                playlist.setName(cursorQuery.getString(columnIndex));
                            }
                            int columnIndex2 = cursorQuery.getColumnIndex(AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE);
                            if (columnIndex2 >= 0) {
                                playlist.setType(cursorQuery.getString(columnIndex2));
                            }
                            int columnIndex3 = cursorQuery.getColumnIndex("download_state");
                            if (columnIndex3 >= 0) {
                                playlist.setDownloadState(cursorQuery.getInt(columnIndex3));
                            }
                            int columnIndex4 = cursorQuery.getColumnIndex("luid");
                            if (columnIndex4 >= 0) {
                                playlist.setLuid(cursorQuery.getString(columnIndex4));
                            }
                            int columnIndex5 = cursorQuery.getColumnIndex("version");
                            if (columnIndex5 >= 0) {
                                playlist.setVersion(cursorQuery.getString(columnIndex5));
                            }
                            if (cursorQuery.getColumnIndex("track_count") >= 0) {
                                playlist.setTrackCount(cursorQuery.getInt(r14));
                            }
                            int columnIndex6 = cursorQuery.getColumnIndex(SqliteLibrarySearchDao.CONTENT_OWNERSHIP_STATUS_MAX);
                            if (columnIndex6 >= 0) {
                                playlist.setMaxContentOwnershipStatusValue(cursorQuery.getInt(columnIndex6));
                            }
                            int columnIndex7 = cursorQuery.getColumnIndex(SqliteLibrarySearchDao.CONTENT_OWNERSHIP_STATUS_MIN);
                            if (columnIndex7 >= 0) {
                                playlist.setMinContentOwnershipStatusValue(cursorQuery.getInt(columnIndex7));
                            }
                            int columnIndex8 = cursorQuery.getColumnIndex("content_catalog_status_max_non_previous");
                            if (columnIndex8 >= 0) {
                                playlist.setMaxNonPreviousContentCatalogStatusValue(cursorQuery.getInt(columnIndex8));
                            }
                            int columnIndex9 = cursorQuery.getColumnIndex(SqliteLibrarySearchDao.CONTENT_CATALOG_STATUS_MAX);
                            if (columnIndex9 >= 0) {
                                playlist.setMaxContentCatalogStatusValue(cursorQuery.getInt(columnIndex9));
                            }
                            playlist.setVisibilityFromCursor(cursorQuery);
                            CatalogStatusTiers catalogStatusTiers = playlist.getCatalogStatusTiers();
                            int columnIndex10 = cursorQuery.getColumnIndex("is_sonic_rush");
                            boolean z = columnIndex10 != -1 && cursorQuery.getInt(columnIndex10) == 1;
                            int columnIndex11 = cursorQuery.getColumnIndex("is_sonic_rush_on_demand");
                            boolean z2 = columnIndex11 != -1 && cursorQuery.getInt(columnIndex11) == 1;
                            int columnIndex12 = cursorQuery.getColumnIndex("is_sonic_rush_golden");
                            playlist.setCatalogStatusTiers(new CatalogStatusTiers(catalogStatusTiers.getIsPrime(), catalogStatusTiers.getIsMusicSubscription(), false, false, z, z2, columnIndex12 != -1 && cursorQuery.getInt(columnIndex12) == 1));
                            int columnIndex13 = cursorQuery.getColumnIndex("playmode_eligibility");
                            if (columnIndex13 != -1) {
                                playlist.setPlaymodeEligibility(EligibilityConverterUtil.getPlaymodeEligibilityFromJson(cursorQuery.getString(columnIndex13)));
                            }
                            int columnIndex14 = cursorQuery.getColumnIndex("numberOfFollowers");
                            if (columnIndex14 >= 0) {
                                playlist.setNumberOfFollowers(cursorQuery.getInt(columnIndex14));
                            }
                            int columnIndex15 = cursorQuery.getColumnIndex("shared_id");
                            if (columnIndex15 >= 0) {
                                playlist.setSharedId(cursorQuery.getString(columnIndex15));
                            }
                        }
                    } catch (Throwable th) {
                        th = th;
                        cursor = cursorQuery;
                        DbUtil.closeCursor(cursor);
                        throw th;
                    }
                }
                DbUtil.closeCursor(cursorQuery);
                return playlist;
            } catch (Throwable th2) {
                th = th2;
            }
        } finally {
            Profiler.end();
        }
    }

    @Override // com.amazon.mp3.library.item.factory.LibraryItemFactory
    public CatalogPlaylist getCatalogPlaylist(Uri contentUri) {
        try {
            AbstractItem item = getItem(contentUri);
            if (item instanceof CatalogPlaylist) {
                return (CatalogPlaylist) item;
            }
            return null;
        } catch (Exception e) {
            Log.debug(TAG, "Error retrieving CatalogPlaylist for uri " + contentUri + "\n" + e);
            return null;
        }
    }

    @Override // com.amazon.mp3.library.item.factory.LibraryItemFactory
    public CatalogPlaylist getPrimePlaylist(Uri contentUri) {
        return new PrimePlaylistDatabaseManager().getPlaylist(MediaProvider.PrimePlaylists.getPlaylistAsin(contentUri), PrimePlaylistsTable.translateSource(MediaProvider.getSource(contentUri)));
    }

    @Override // com.amazon.mp3.library.item.factory.LibraryItemFactory
    public CatalogPlaylist getSharedUserPlaylist(Uri contentUri) {
        return new PrimePlaylistDatabaseManager().getPlaylist(MediaProvider.SharedUserPlaylists.getPlaylistKey(contentUri), PrimePlaylistsTable.translateSource(MediaProvider.getSource(contentUri)));
    }

    @Override // com.amazon.mp3.library.item.factory.LibraryItemFactory
    public void updateCounts(Album album) {
        Profiler.begin("Updating album counts");
        Cursor cursorQuery = null;
        try {
            cursorQuery = CirrusDatabase.getWritableDatabase(this.context).query("Track", new String[]{"count(distinct match_hash) as track_count"}, DbUtil.applyBinaryOperator(DbUtil.applyBinaryOperator("album_id = " + album.getId(), "AND", getAlbumSelection()), "AND", "(source = " + MediaProvider.CirrusBaseColumns.Source.convertToSource(album.getSource()) + ")"), getAlbumSelectionArgs(), null, null, null);
            if (cursorQuery != null && cursorQuery.getCount() == 1 && cursorQuery.moveToNext()) {
                album.setTrackCount(cursorQuery.getInt(cursorQuery.getColumnIndex("track_count")));
            }
            DbUtil.closeCursor(cursorQuery);
            Profiler.end();
        } catch (Throwable th) {
            DbUtil.closeCursor(cursorQuery);
            throw th;
        }
    }

    @Override // com.amazon.mp3.library.item.factory.LibraryItemFactory
    public void updateCounts(Artist artist) {
        Profiler.begin("Updating artist counts");
        String[] strArr = {"count(distinct match_hash) as track_count", "count(distinct album_id) as album_count"};
        String strApplyBinaryOperator = DbUtil.applyBinaryOperator("(album_artist_id = " + artist.getId() + " or artist_id = " + artist.getId() + ")", "AND", getArtistSelection());
        StringBuilder sb = new StringBuilder();
        sb.append("(source = ");
        sb.append(MediaProvider.CirrusBaseColumns.Source.convertToSource(artist.getSource()));
        sb.append(")");
        String strApplyBinaryOperator2 = DbUtil.applyBinaryOperator(strApplyBinaryOperator, "AND", sb.toString());
        Cursor cursorQuery = null;
        try {
            cursorQuery = CirrusDatabase.getWritableDatabase(this.context).query("Track", strArr, strApplyBinaryOperator2, getArtistSelectionArgs(), null, null, null);
            if (cursorQuery != null && cursorQuery.getCount() == 1 && cursorQuery.moveToNext()) {
                int i = cursorQuery.getInt(cursorQuery.getColumnIndex("track_count"));
                int i2 = cursorQuery.getInt(cursorQuery.getColumnIndex("album_count"));
                artist.setTrackCount(i);
                artist.setAlbumCount(i2);
            }
            DbUtil.closeCursor(cursorQuery);
            Profiler.end();
        } catch (Throwable th) {
            DbUtil.closeCursor(cursorQuery);
            throw th;
        }
    }

    private Cursor getMarketplaceDependentItemCursor(MarketplaceDependentItem item, String source, String[] projection) {
        return getMarketplaceDependentItemCursor(item, source, projection, null);
    }

    private Cursor getMarketplaceDependentItemCursor(MarketplaceDependentItem item, String source, String[] projection, String sortOrder) {
        Uri contentUri = item.getContentUri(source);
        if (contentUri == null) {
            return null;
        }
        return this.mContentResolver.query(contentUri, projection, null, null, sortOrder);
    }

    @Override // com.amazon.mp3.library.item.factory.LibraryItemFactory
    public AbstractItem updateOptionalInfo(AbstractItem item) {
        return MarketplaceDependentItem.class.isInstance(item) ? updateOptionalInfo((MarketplaceDependentItem) item) : item;
    }

    public MarketplaceDependentItem updateOptionalInfo(MarketplaceDependentItem item) {
        String[] strArr;
        String string;
        String string2;
        Profiler.begin("Updating optional marketplace info");
        boolean zIsAlbum = MediaProvider.Albums.isAlbum(item.getContentUri());
        boolean zIsArtist = MediaProvider.Artists.isArtist(item.getContentUri());
        if (zIsAlbum) {
            strArr = new String[]{"album_asin", "artist_asin", IntlConfiguration.MARKETPLACE};
        } else if (zIsArtist) {
            strArr = new String[]{"artist_asin", IntlConfiguration.MARKETPLACE};
        } else {
            strArr = new String[]{IntlConfiguration.MARKETPLACE};
        }
        Cursor marketplaceDependentItemCursor = null;
        try {
            marketplaceDependentItemCursor = getMarketplaceDependentItemCursor(item, item.getSource(), strArr);
            if (marketplaceDependentItemCursor != null) {
                int columnIndex = marketplaceDependentItemCursor.getColumnIndex(IntlConfiguration.MARKETPLACE);
                int columnIndex2 = marketplaceDependentItemCursor.getColumnIndex("album_asin");
                int columnIndex3 = marketplaceDependentItemCursor.getColumnIndex("artist_asin");
                boolean z = false;
                boolean z2 = false;
                boolean z3 = false;
                while (marketplaceDependentItemCursor.moveToNext()) {
                    if (!z && (string2 = marketplaceDependentItemCursor.getString(columnIndex)) != null && string2.length() > 0) {
                        item.setMarketplace(string2);
                        z = true;
                    }
                    if (zIsArtist && !z2) {
                        String string3 = marketplaceDependentItemCursor.getString(columnIndex3);
                        if ((item instanceof Artist) && string3 != null && string3.length() > 0) {
                            ((Artist) item).setAsin(string3);
                            z2 = true;
                        }
                    }
                    if (zIsAlbum) {
                        if (!z3 && (string = marketplaceDependentItemCursor.getString(columnIndex2)) != null && string.length() > 0) {
                            item.setAsin(string);
                            z3 = true;
                        }
                        if (!z2) {
                            String string4 = marketplaceDependentItemCursor.getString(columnIndex3);
                            if ((item instanceof Album) && string4 != null && string4.length() > 0) {
                                ((Album) item).setArtistAsin(string4);
                                z2 = true;
                            }
                        }
                    }
                    if (z && (!zIsAlbum || (z3 && z2))) {
                        break;
                    }
                }
            }
            DbUtil.closeCursor(marketplaceDependentItemCursor);
            Profiler.end();
            return item;
        } catch (Throwable th) {
            DbUtil.closeCursor(marketplaceDependentItemCursor);
            throw th;
        }
    }

    @Override // com.amazon.mp3.library.item.factory.LibraryItemFactory
    public Collection<MusicTrack> getTracksForAlbum(Album album) {
        return getTracksForAlbum(album, null);
    }

    @Override // com.amazon.mp3.library.item.factory.LibraryItemFactory
    public Collection<MusicTrack> getTracksForAlbum(Album album, String sortOrder) {
        ArrayList arrayList = new ArrayList();
        Cursor marketplaceDependentItemCursor = null;
        try {
            marketplaceDependentItemCursor = getMarketplaceDependentItemCursor(album, album.getSource(), new String[]{"luid"}, sortOrder);
            if (marketplaceDependentItemCursor != null) {
                while (marketplaceDependentItemCursor.moveToNext()) {
                    MusicTrack track = getTrack(MediaProvider.Tracks.getContentUri(album.getSource(), marketplaceDependentItemCursor.getString(marketplaceDependentItemCursor.getColumnIndex("luid"))));
                    if (track != null) {
                        arrayList.add(track);
                    }
                }
            }
            return arrayList;
        } finally {
            if (marketplaceDependentItemCursor != null) {
                marketplaceDependentItemCursor.close();
            }
        }
    }

    @Override // com.amazon.mp3.library.item.factory.LibraryItemFactory
    public Collection<MusicTrack> getTracksForPlaylist(Playlist playlist) {
        Cursor cursorBuildUserTrackCursor;
        List<MusicTrack> arrayList = new ArrayList<>();
        Uri contentUri = playlist.getContentUri();
        Uri uriBuild = contentUri.buildUpon().appendEncodedPath("tracks").build();
        if (playlist.isCatalog()) {
            cursorBuildUserTrackCursor = buildCatalogTrackCursor(uriBuild, contentUri);
        } else {
            cursorBuildUserTrackCursor = buildUserTrackCursor(uriBuild, contentUri);
        }
        if (cursorBuildUserTrackCursor != null) {
            try {
                arrayList = DBQueryUtil.INSTANCE.convertTracksCursorToTracks(cursorBuildUserTrackCursor, playlist);
            } finally {
                cursorBuildUserTrackCursor.close();
            }
        }
        if (cursorBuildUserTrackCursor != null) {
        }
        return arrayList;
    }

    private final Cursor buildUserTrackCursor(Uri contentUri, Uri playlistUri) {
        return DBQueryUtil.INSTANCE.runTrackQuery(this.mContentResolver, contentUri, getTrackProjection(), PlaylistUtil.getSelection(playlistUri), PlaylistUtil.getSelectionArgs(playlistUri), "udo");
    }

    private final Cursor buildCatalogTrackCursor(Uri contentUri, Uri playlistUri) {
        return DBQueryUtil.INSTANCE.runTrackQuery(this.mContentResolver, contentUri, getTrackProjection(), null, null, "order_num");
    }

    @Override // com.amazon.mp3.library.item.factory.LibraryItemFactory
    public Collection<CatalogPlaylistTrack> getTracksForPrimePlaylist(CatalogPlaylist playlist) {
        ArrayList arrayList = new ArrayList();
        Cursor cursorQuery = this.mContentResolver.query(MediaProvider.PrimePlaylists.getContentUri(playlist.getSource(), playlist.getAsin()), CatalogPlaylistTrack.DEFAULT_PROJECTION, null, null, "order_num");
        if (cursorQuery == null) {
            return arrayList;
        }
        while (cursorQuery.moveToNext()) {
            try {
                arrayList.add(CatalogPlaylistTrack.fromCursor(cursorQuery));
            } finally {
                DbUtil.closeCursor(cursorQuery);
            }
        }
        return arrayList;
    }

    @Override // com.amazon.mp3.library.item.factory.LibraryItemFactory
    public Album getAlbumForTrack(MusicTrack track) {
        return getAlbum(MediaProvider.Albums.getContentUri(track.getSource(), track.getAlbumId()));
    }

    @Override // com.amazon.mp3.library.item.factory.LibraryItemFactory
    public Artist getArtistForAlbum(Album album) {
        return getArtist(MediaProvider.Artists.getContentUri(album.getSource(), album.getArtistId()));
    }

    @Override // com.amazon.mp3.library.item.factory.LibraryItemFactory
    public Uri getUriForAsin(String str) {
        Cursor cursor = null;
        uriForCid = null;
        Uri uriForCid = null;
        if (str == null) {
            return null;
        }
        try {
            Cursor cursorQuery = this.mContentResolver.query(MediaProvider.Tracks.getContentUri("cirrus"), new String[]{"luid", "album_id", "artist_id", "asin", "album_asin", "artist_asin"}, "asin=? OR album_asin=? OR artist_asin=?", new String[]{str, str, str}, null);
            if (cursorQuery != null) {
                while (cursorQuery.moveToNext() && uriForCid == null) {
                    try {
                        if (str.equals(cursorQuery.getString(cursorQuery.getColumnIndex("asin")))) {
                            uriForCid = ItemWrapper$ItemType.SONG.getUriForCid(cursorQuery.getString(cursorQuery.getColumnIndex("luid")));
                        } else if (str.equals(cursorQuery.getString(cursorQuery.getColumnIndex("album_asin")))) {
                            uriForCid = ItemWrapper$ItemType.ALBUM.getUriForCid(cursorQuery.getString(cursorQuery.getColumnIndex("album_id")));
                        } else if (str.equals(cursorQuery.getString(cursorQuery.getColumnIndex("artist_asin")))) {
                            uriForCid = ItemWrapper$ItemType.ARTIST.getUriForCid(cursorQuery.getString(cursorQuery.getColumnIndex("artist_id")));
                        }
                    } catch (Throwable th) {
                        th = th;
                        cursor = cursorQuery;
                        DbUtil.closeCursor(cursor);
                        throw th;
                    }
                }
            }
            DbUtil.closeCursor(cursorQuery);
            return uriForCid;
        } catch (Throwable th2) {
            th = th2;
        }
    }

    @Override // com.amazon.mp3.library.item.factory.LibraryItemFactory
    public Genre getGenre(Uri uri) {
        try {
            Profiler.begin("Get genre from contentUri");
            Cursor cursor = null;
            genre = null;
            genre = null;
            Genre genre = null;
            if (!MediaProvider.Genres.isGenre(uri)) {
                return null;
            }
            try {
                Cursor cursorQuery = this.mContentResolver.query(uri, getGenreProjection(), null, null, null);
                if (cursorQuery != null) {
                    try {
                        if (cursorQuery.getCount() == 1 && cursorQuery.moveToFirst()) {
                            genre = getGenre(cursorQuery);
                        }
                    } catch (Throwable th) {
                        th = th;
                        cursor = cursorQuery;
                        DbUtil.closeCursor(cursor);
                        throw th;
                    }
                }
                DbUtil.closeCursor(cursorQuery);
                return genre;
            } catch (Throwable th2) {
                th = th2;
            }
        } finally {
            Profiler.end();
        }
    }

    @Override // com.amazon.mp3.library.item.factory.LibraryItemFactory
    public String[] getGenreProjection() {
        return new String[]{"_id", "name", "track_count", "album_count", "download_state", "download_status", PageUriUtils.SOURCE_QUERY_PARAM, SqliteLibrarySearchDao.CONTENT_OWNERSHIP_STATUS_MIN, SqliteLibrarySearchDao.CONTENT_OWNERSHIP_STATUS_MAX, SqliteLibrarySearchDao.CONTENT_CATALOG_STATUS_MIN, "content_catalog_status_max_non_previous", SqliteLibrarySearchDao.CONTENT_CATALOG_STATUS_MAX};
    }

    public Genre getGenre(Cursor genreCursor) {
        return populateGenre(genreCursor, new Genre(this, null));
    }

    @Override // com.amazon.mp3.library.item.factory.LibraryItemFactory
    public Genre populateGenre(Cursor genreCursor, Genre genre) {
        Uri contentUri;
        try {
            Profiler.begin("Get genre from cursor");
            long j = genreCursor.getLong(genreCursor.getColumnIndex("_id"));
            if (genreCursor.getInt(genreCursor.getColumnIndex(PageUriUtils.SOURCE_QUERY_PARAM)) == 1) {
                contentUri = MediaProvider.Genres.getContentUri("cirrus-local", j);
            } else {
                contentUri = MediaProvider.Genres.getContentUri("cirrus", j);
            }
            genre.setContentUri(contentUri);
            genre.setId(Long.toString(j));
            genre.setName(genreCursor.getString(genreCursor.getColumnIndex("name")));
            genre.setTrackCount(genreCursor.getLong(genreCursor.getColumnIndex("track_count")));
            genre.setAlbumCount(genreCursor.getLong(genreCursor.getColumnIndex("album_count")));
            genre.setDownloadState(genreCursor.getInt(genreCursor.getColumnIndex("download_state")));
            genre.setMinContentOwnershipStatusValue(genreCursor.getInt(genreCursor.getColumnIndex(SqliteLibrarySearchDao.CONTENT_OWNERSHIP_STATUS_MIN)));
            genre.setMaxContentOwnershipStatusValue(genreCursor.getInt(genreCursor.getColumnIndex(SqliteLibrarySearchDao.CONTENT_OWNERSHIP_STATUS_MAX)));
            genre.setMinContentCatalogStatusValue(genreCursor.getInt(genreCursor.getColumnIndex(SqliteLibrarySearchDao.CONTENT_CATALOG_STATUS_MIN)));
            genre.setMaxNonPreviousContentCatalogStatusValue(genreCursor.getInt(genreCursor.getColumnIndex("content_catalog_status_max_non_previous")));
            genre.setMaxContentCatalogStatusValue(genreCursor.getInt(genreCursor.getColumnIndex(SqliteLibrarySearchDao.CONTENT_CATALOG_STATUS_MAX)));
            return genre;
        } finally {
            Profiler.end();
        }
    }

    @Override // com.amazon.mp3.library.item.factory.LibraryItemFactory
    public void updateCounts(Genre genre) {
        Profiler.begin("Updating genre counts");
        Cursor cursorRawQuery = null;
        try {
            cursorRawQuery = CirrusDatabase.getWritableDatabase(this.context).rawQuery("select count(distinct match_hash) as track_count, count(distinct album_id) as album_count from Track where genre_id = " + genre.getId() + " AND (" + PageUriUtils.SOURCE_QUERY_PARAM + " = " + MediaProvider.CirrusBaseColumns.Source.convertToSource(genre.getSource()) + ")", null);
            if (cursorRawQuery != null && cursorRawQuery.getCount() == 1 && cursorRawQuery.moveToNext()) {
                int i = cursorRawQuery.getInt(cursorRawQuery.getColumnIndex("track_count"));
                int i2 = cursorRawQuery.getInt(cursorRawQuery.getColumnIndex("album_count"));
                genre.setTrackCount(i);
                genre.setAlbumCount(i2);
            }
            DbUtil.closeCursor(cursorRawQuery);
            Profiler.end();
        } catch (Throwable th) {
            DbUtil.closeCursor(cursorRawQuery);
            throw th;
        }
    }
}
