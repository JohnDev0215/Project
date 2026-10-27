package com.amazon.music.curate.data.database;

import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.SharedSQLiteStatement;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.amazon.music.curate.model.Track;
import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
public final class TrackDao_Impl implements TrackDao {
    private final BadgeConverter __badgeConverter = new BadgeConverter();
    private final RoomDatabase __db;
    private final EntityInsertionAdapter<Track> __insertionAdapterOfTrack;
    private final SharedSQLiteStatement __preparedStmtOfDeleteAllTracks;

    public TrackDao_Impl(RoomDatabase __db) {
        this.__db = __db;
        this.__insertionAdapterOfTrack = new EntityInsertionAdapter<Track>(__db) { // from class: com.amazon.music.curate.data.database.TrackDao_Impl.1
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "INSERT OR REPLACE INTO `Track` (`asin`,`title`,`artist_name`,`artwork_url`,`badges`,`artist_asin`,`album_asin`,`album_name`,`is_in_library`,`duration`,`marketplace`,`entry_id`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
            }

            @Override // androidx.room.EntityInsertionAdapter
            public void bind(SupportSQLiteStatement supportSQLiteStatement, Track track) {
                if (track.getAsin() == null) {
                    supportSQLiteStatement.bindNull(1);
                } else {
                    supportSQLiteStatement.bindString(1, track.getAsin());
                }
                if (track.getTitle() == null) {
                    supportSQLiteStatement.bindNull(2);
                } else {
                    supportSQLiteStatement.bindString(2, track.getTitle());
                }
                if (track.getArtistName() == null) {
                    supportSQLiteStatement.bindNull(3);
                } else {
                    supportSQLiteStatement.bindString(3, track.getArtistName());
                }
                if (track.getAlbumImageUrl() == null) {
                    supportSQLiteStatement.bindNull(4);
                } else {
                    supportSQLiteStatement.bindString(4, track.getAlbumImageUrl());
                }
                String string = TrackDao_Impl.this.__badgeConverter.toString(track.getBadges());
                if (string == null) {
                    supportSQLiteStatement.bindNull(5);
                } else {
                    supportSQLiteStatement.bindString(5, string);
                }
                if (track.getArtistAsin() == null) {
                    supportSQLiteStatement.bindNull(6);
                } else {
                    supportSQLiteStatement.bindString(6, track.getArtistAsin());
                }
                if (track.getAlbumAsin() == null) {
                    supportSQLiteStatement.bindNull(7);
                } else {
                    supportSQLiteStatement.bindString(7, track.getAlbumAsin());
                }
                if (track.getAlbumName() == null) {
                    supportSQLiteStatement.bindNull(8);
                } else {
                    supportSQLiteStatement.bindString(8, track.getAlbumName());
                }
                supportSQLiteStatement.bindLong(9, track.isInLibrary() ? 1L : 0L);
                supportSQLiteStatement.bindLong(10, track.getDuration());
                if (track.getMarketplace() == null) {
                    supportSQLiteStatement.bindNull(11);
                } else {
                    supportSQLiteStatement.bindString(11, track.getMarketplace());
                }
                if (track.getEntryId() == null) {
                    supportSQLiteStatement.bindNull(12);
                } else {
                    supportSQLiteStatement.bindString(12, track.getEntryId());
                }
            }
        };
        this.__preparedStmtOfDeleteAllTracks = new SharedSQLiteStatement(__db) { // from class: com.amazon.music.curate.data.database.TrackDao_Impl.2
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "Delete from Track";
            }
        };
    }

    @Override // com.amazon.music.curate.data.database.TrackDao
    public void putTracks(final List<Track> tracks) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__insertionAdapterOfTrack.insert(tracks);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // com.amazon.music.curate.data.database.TrackDao
    public void deleteAllTracks() {
        this.__db.assertNotSuspendingTransaction();
        SupportSQLiteStatement supportSQLiteStatementAcquire = this.__preparedStmtOfDeleteAllTracks.acquire();
        this.__db.beginTransaction();
        try {
            supportSQLiteStatementAcquire.executeUpdateDelete();
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
            this.__preparedStmtOfDeleteAllTracks.release(supportSQLiteStatementAcquire);
        }
    }

    public static List<Class<?>> getRequiredConverters() {
        return Collections.emptyList();
    }
}
