package com.amazon.music.curate.data.database;

import android.database.Cursor;
import androidx.lifecycle.LiveData;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.amazon.mp3.configuration.IntlConfiguration;
import com.amazon.music.curate.model.PlaylistTrack;
import com.amazon.music.curate.model.Track;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;

/* JADX INFO: loaded from: classes4.dex */
public final class PlaylistTrackDao_Impl extends PlaylistTrackDao {
    private final BadgeConverter __badgeConverter = new BadgeConverter();
    private final RoomDatabase __db;
    private final EntityInsertionAdapter<PlaylistTrack> __insertionAdapterOfPlaylistTrack;
    private final SharedSQLiteStatement __preparedStmtOfDeletePlaylistTrack;
    private final SharedSQLiteStatement __preparedStmtOfDeletePlaylistTracks;

    public PlaylistTrackDao_Impl(RoomDatabase __db) {
        this.__db = __db;
        this.__insertionAdapterOfPlaylistTrack = new EntityInsertionAdapter<PlaylistTrack>(__db) { // from class: com.amazon.music.curate.data.database.PlaylistTrackDao_Impl.1
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "INSERT OR REPLACE INTO `PlaylistTrack` (`playlist_id`,`track_asin`,`position_in_playlist`) VALUES (?,?,?)";
            }

            @Override // androidx.room.EntityInsertionAdapter
            public void bind(SupportSQLiteStatement stmt, PlaylistTrack value) {
                if (value.getPlaylistId() == null) {
                    stmt.bindNull(1);
                } else {
                    stmt.bindString(1, value.getPlaylistId());
                }
                if (value.getTrackAsin() == null) {
                    stmt.bindNull(2);
                } else {
                    stmt.bindString(2, value.getTrackAsin());
                }
                stmt.bindLong(3, value.getPositionInPlaylist());
            }
        };
        this.__preparedStmtOfDeletePlaylistTracks = new SharedSQLiteStatement(__db) { // from class: com.amazon.music.curate.data.database.PlaylistTrackDao_Impl.2
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "Delete from PlaylistTrack where PlaylistTrack.playlist_id = (?)";
            }
        };
        this.__preparedStmtOfDeletePlaylistTrack = new SharedSQLiteStatement(__db) { // from class: com.amazon.music.curate.data.database.PlaylistTrackDao_Impl.3
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "Delete from PlaylistTrack where playlist_id = ? and position_in_playlist = (select position_in_playlist from PlaylistTrack where playlist_id = ? and track_asin = ? and position_in_playlist >= ? limit 1)";
            }
        };
    }

    @Override // com.amazon.music.curate.data.database.PlaylistTrackDao
    public void putPlaylistTracks(final List<PlaylistTrack> playlistTracks) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__insertionAdapterOfPlaylistTrack.insert(playlistTracks);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // com.amazon.music.curate.data.database.PlaylistTrackDao
    public void removePlaylistTrackAndUpdateIndices(final String playlistId, final String trackId, final int index) {
        this.__db.beginTransaction();
        try {
            super.removePlaylistTrackAndUpdateIndices(playlistId, trackId, index);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // com.amazon.music.curate.data.database.PlaylistTrackDao
    public void deletePlaylistTracks(final String playlistId) {
        this.__db.assertNotSuspendingTransaction();
        SupportSQLiteStatement supportSQLiteStatementAcquire = this.__preparedStmtOfDeletePlaylistTracks.acquire();
        if (playlistId == null) {
            supportSQLiteStatementAcquire.bindNull(1);
        } else {
            supportSQLiteStatementAcquire.bindString(1, playlistId);
        }
        this.__db.beginTransaction();
        try {
            supportSQLiteStatementAcquire.executeUpdateDelete();
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
            this.__preparedStmtOfDeletePlaylistTracks.release(supportSQLiteStatementAcquire);
        }
    }

    @Override // com.amazon.music.curate.data.database.PlaylistTrackDao
    public void deletePlaylistTrack(final String playlistId, final String trackId, final int index) {
        this.__db.assertNotSuspendingTransaction();
        SupportSQLiteStatement supportSQLiteStatementAcquire = this.__preparedStmtOfDeletePlaylistTrack.acquire();
        if (playlistId == null) {
            supportSQLiteStatementAcquire.bindNull(1);
        } else {
            supportSQLiteStatementAcquire.bindString(1, playlistId);
        }
        if (playlistId == null) {
            supportSQLiteStatementAcquire.bindNull(2);
        } else {
            supportSQLiteStatementAcquire.bindString(2, playlistId);
        }
        if (trackId == null) {
            supportSQLiteStatementAcquire.bindNull(3);
        } else {
            supportSQLiteStatementAcquire.bindString(3, trackId);
        }
        supportSQLiteStatementAcquire.bindLong(4, index);
        this.__db.beginTransaction();
        try {
            supportSQLiteStatementAcquire.executeUpdateDelete();
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
            this.__preparedStmtOfDeletePlaylistTrack.release(supportSQLiteStatementAcquire);
        }
    }

    @Override // com.amazon.music.curate.data.database.PlaylistTrackDao
    public List<Track> getTracksForPlaylist(final String playlistId, final int limit, final int offset) throws Throwable {
        RoomSQLiteQuery roomSQLiteQuery;
        int columnIndexOrThrow;
        int columnIndexOrThrow2;
        int columnIndexOrThrow3;
        int columnIndexOrThrow4;
        int columnIndexOrThrow5;
        int columnIndexOrThrow6;
        int columnIndexOrThrow7;
        int columnIndexOrThrow8;
        int columnIndexOrThrow9;
        int columnIndexOrThrow10;
        int columnIndexOrThrow11;
        int columnIndexOrThrow12;
        String string;
        int i;
        RoomSQLiteQuery roomSQLiteQueryAcquire = RoomSQLiteQuery.acquire("Select * from Track inner join PlaylistTrack on Track.asin = PlaylistTrack.track_asin where PlaylistTrack.playlist_id = (?) order by PlaylistTrack.position_in_playlist limit (?) offset (?)", 3);
        if (playlistId == null) {
            roomSQLiteQueryAcquire.bindNull(1);
        } else {
            roomSQLiteQueryAcquire.bindString(1, playlistId);
        }
        roomSQLiteQueryAcquire.bindLong(2, limit);
        roomSQLiteQueryAcquire.bindLong(3, offset);
        this.__db.assertNotSuspendingTransaction();
        Cursor cursorQuery = DBUtil.query(this.__db, roomSQLiteQueryAcquire, false, null);
        try {
            columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(cursorQuery, "asin");
            columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "title");
            columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "artist_name");
            columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "artwork_url");
            columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "badges");
            columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "artist_asin");
            columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "album_asin");
            columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "album_name");
            columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "is_in_library");
            columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "duration");
            columnIndexOrThrow11 = CursorUtil.getColumnIndexOrThrow(cursorQuery, IntlConfiguration.MARKETPLACE);
            columnIndexOrThrow12 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "entry_id");
            roomSQLiteQuery = roomSQLiteQueryAcquire;
        } catch (Throwable th) {
            th = th;
            roomSQLiteQuery = roomSQLiteQueryAcquire;
        }
        try {
            ArrayList arrayList = new ArrayList(cursorQuery.getCount());
            while (cursorQuery.moveToNext()) {
                String string2 = cursorQuery.isNull(columnIndexOrThrow) ? null : cursorQuery.getString(columnIndexOrThrow);
                String string3 = cursorQuery.isNull(columnIndexOrThrow2) ? null : cursorQuery.getString(columnIndexOrThrow2);
                String string4 = cursorQuery.isNull(columnIndexOrThrow3) ? null : cursorQuery.getString(columnIndexOrThrow3);
                String string5 = cursorQuery.isNull(columnIndexOrThrow4) ? null : cursorQuery.getString(columnIndexOrThrow4);
                if (cursorQuery.isNull(columnIndexOrThrow5)) {
                    i = columnIndexOrThrow;
                    string = null;
                } else {
                    string = cursorQuery.getString(columnIndexOrThrow5);
                    i = columnIndexOrThrow;
                }
                arrayList.add(new Track(string2, string3, string4, string5, this.__badgeConverter.toBadges(string), cursorQuery.isNull(columnIndexOrThrow6) ? null : cursorQuery.getString(columnIndexOrThrow6), cursorQuery.isNull(columnIndexOrThrow7) ? null : cursorQuery.getString(columnIndexOrThrow7), cursorQuery.isNull(columnIndexOrThrow8) ? null : cursorQuery.getString(columnIndexOrThrow8), cursorQuery.getInt(columnIndexOrThrow9) != 0, cursorQuery.getInt(columnIndexOrThrow10), cursorQuery.isNull(columnIndexOrThrow11) ? null : cursorQuery.getString(columnIndexOrThrow11), cursorQuery.isNull(columnIndexOrThrow12) ? null : cursorQuery.getString(columnIndexOrThrow12)));
                columnIndexOrThrow = i;
            }
            cursorQuery.close();
            roomSQLiteQuery.release();
            return arrayList;
        } catch (Throwable th2) {
            th = th2;
            cursorQuery.close();
            roomSQLiteQuery.release();
            throw th;
        }
    }

    @Override // com.amazon.music.curate.data.database.PlaylistTrackDao
    public LiveData<List<Track>> getPlaylistTracksLiveData(final String playlistId) {
        final RoomSQLiteQuery roomSQLiteQueryAcquire = RoomSQLiteQuery.acquire("Select * from Track inner join PlaylistTrack on Track.asin = PlaylistTrack.track_asin where PlaylistTrack.playlist_id = (?) order by PlaylistTrack.position_in_playlist ", 1);
        if (playlistId == null) {
            roomSQLiteQueryAcquire.bindNull(1);
        } else {
            roomSQLiteQueryAcquire.bindString(1, playlistId);
        }
        return this.__db.getInvalidationTracker().createLiveData(new String[]{"Track", "PlaylistTrack"}, false, new Callable<List<Track>>() { // from class: com.amazon.music.curate.data.database.PlaylistTrackDao_Impl.4
            @Override // java.util.concurrent.Callable
            public List<Track> call() throws Exception {
                String string;
                int i;
                Cursor cursorQuery = DBUtil.query(PlaylistTrackDao_Impl.this.__db, roomSQLiteQueryAcquire, false, null);
                try {
                    int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(cursorQuery, "asin");
                    int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "title");
                    int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "artist_name");
                    int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "artwork_url");
                    int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "badges");
                    int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "artist_asin");
                    int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "album_asin");
                    int columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "album_name");
                    int columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "is_in_library");
                    int columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "duration");
                    int columnIndexOrThrow11 = CursorUtil.getColumnIndexOrThrow(cursorQuery, IntlConfiguration.MARKETPLACE);
                    int columnIndexOrThrow12 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "entry_id");
                    ArrayList arrayList = new ArrayList(cursorQuery.getCount());
                    while (cursorQuery.moveToNext()) {
                        String string2 = cursorQuery.isNull(columnIndexOrThrow) ? null : cursorQuery.getString(columnIndexOrThrow);
                        String string3 = cursorQuery.isNull(columnIndexOrThrow2) ? null : cursorQuery.getString(columnIndexOrThrow2);
                        String string4 = cursorQuery.isNull(columnIndexOrThrow3) ? null : cursorQuery.getString(columnIndexOrThrow3);
                        String string5 = cursorQuery.isNull(columnIndexOrThrow4) ? null : cursorQuery.getString(columnIndexOrThrow4);
                        if (cursorQuery.isNull(columnIndexOrThrow5)) {
                            i = columnIndexOrThrow;
                            string = null;
                        } else {
                            string = cursorQuery.getString(columnIndexOrThrow5);
                            i = columnIndexOrThrow;
                        }
                        arrayList.add(new Track(string2, string3, string4, string5, PlaylistTrackDao_Impl.this.__badgeConverter.toBadges(string), cursorQuery.isNull(columnIndexOrThrow6) ? null : cursorQuery.getString(columnIndexOrThrow6), cursorQuery.isNull(columnIndexOrThrow7) ? null : cursorQuery.getString(columnIndexOrThrow7), cursorQuery.isNull(columnIndexOrThrow8) ? null : cursorQuery.getString(columnIndexOrThrow8), cursorQuery.getInt(columnIndexOrThrow9) != 0, cursorQuery.getInt(columnIndexOrThrow10), cursorQuery.isNull(columnIndexOrThrow11) ? null : cursorQuery.getString(columnIndexOrThrow11), cursorQuery.isNull(columnIndexOrThrow12) ? null : cursorQuery.getString(columnIndexOrThrow12)));
                        columnIndexOrThrow = i;
                    }
                    return arrayList;
                } finally {
                    cursorQuery.close();
                }
            }

            protected void finalize() {
                roomSQLiteQueryAcquire.release();
            }
        });
    }

    public static List<Class<?>> getRequiredConverters() {
        return Collections.emptyList();
    }
}
