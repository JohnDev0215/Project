package com.amazon.music.curate.data.database;

import android.database.Cursor;
import androidx.lifecycle.LiveData;
import androidx.paging.DataSource;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.paging.LimitOffsetDataSource;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.amazon.music.curate.model.Playlist;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;

/* JADX INFO: loaded from: classes4.dex */
public final class PlaylistDao_Impl implements PlaylistDao {
    private final RoomDatabase __db;
    private final EntityInsertionAdapter<Playlist> __insertionAdapterOfPlaylist;
    private final SharedSQLiteStatement __preparedStmtOfDeletePersonalizedPlaylists;
    private final SharedSQLiteStatement __preparedStmtOfDeletePlaylist;

    public PlaylistDao_Impl(RoomDatabase __db) {
        this.__db = __db;
        this.__insertionAdapterOfPlaylist = new EntityInsertionAdapter<Playlist>(__db) { // from class: com.amazon.music.curate.data.database.PlaylistDao_Impl.1
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "INSERT OR REPLACE INTO `Playlist` (`id`,`title`,`artwork_url`,`banner_artwork_url`,`num_songs`,`duration_seconds`,`version`,`expiry_time`,`is_personalized`,`is_followed`,`is_shareable`,`is_prime`,`is_music_unlimited`,`is_nightwing`,`is_nightwing_on_demand`,`can_rename`,`can_edit_content`,`can_remove`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            }

            @Override // androidx.room.EntityInsertionAdapter
            public void bind(SupportSQLiteStatement supportSQLiteStatement, Playlist playlist) {
                if (playlist.getId() == null) {
                    supportSQLiteStatement.bindNull(1);
                } else {
                    supportSQLiteStatement.bindString(1, playlist.getId());
                }
                if (playlist.getTitle() == null) {
                    supportSQLiteStatement.bindNull(2);
                } else {
                    supportSQLiteStatement.bindString(2, playlist.getTitle());
                }
                if (playlist.getArtworkUrl() == null) {
                    supportSQLiteStatement.bindNull(3);
                } else {
                    supportSQLiteStatement.bindString(3, playlist.getArtworkUrl());
                }
                if (playlist.getBannerArtworkUrl() == null) {
                    supportSQLiteStatement.bindNull(4);
                } else {
                    supportSQLiteStatement.bindString(4, playlist.getBannerArtworkUrl());
                }
                supportSQLiteStatement.bindLong(5, playlist.getNumSongs());
                if (playlist.getDurationSeconds() == null) {
                    supportSQLiteStatement.bindNull(6);
                } else {
                    supportSQLiteStatement.bindLong(6, playlist.getDurationSeconds().intValue());
                }
                if (playlist.getVersion() == null) {
                    supportSQLiteStatement.bindNull(7);
                } else {
                    supportSQLiteStatement.bindString(7, playlist.getVersion());
                }
                supportSQLiteStatement.bindLong(8, playlist.getExpiryTime());
                if ((playlist.getIsPersonalized() == null ? null : Integer.valueOf(playlist.getIsPersonalized().booleanValue() ? 1 : 0)) == null) {
                    supportSQLiteStatement.bindNull(9);
                } else {
                    supportSQLiteStatement.bindLong(9, r0.intValue());
                }
                if ((playlist.getIsFollowed() == null ? null : Integer.valueOf(playlist.getIsFollowed().booleanValue() ? 1 : 0)) == null) {
                    supportSQLiteStatement.bindNull(10);
                } else {
                    supportSQLiteStatement.bindLong(10, r0.intValue());
                }
                if ((playlist.getIsShareable() == null ? null : Integer.valueOf(playlist.getIsShareable().booleanValue() ? 1 : 0)) == null) {
                    supportSQLiteStatement.bindNull(11);
                } else {
                    supportSQLiteStatement.bindLong(11, r0.intValue());
                }
                if ((playlist.getIsPrime() == null ? null : Integer.valueOf(playlist.getIsPrime().booleanValue() ? 1 : 0)) == null) {
                    supportSQLiteStatement.bindNull(12);
                } else {
                    supportSQLiteStatement.bindLong(12, r0.intValue());
                }
                if ((playlist.getIsMusicUnlimited() == null ? null : Integer.valueOf(playlist.getIsMusicUnlimited().booleanValue() ? 1 : 0)) == null) {
                    supportSQLiteStatement.bindNull(13);
                } else {
                    supportSQLiteStatement.bindLong(13, r0.intValue());
                }
                if ((playlist.getIsNightwing() == null ? null : Integer.valueOf(playlist.getIsNightwing().booleanValue() ? 1 : 0)) == null) {
                    supportSQLiteStatement.bindNull(14);
                } else {
                    supportSQLiteStatement.bindLong(14, r0.intValue());
                }
                if ((playlist.getIsNightwingOnDemand() == null ? null : Integer.valueOf(playlist.getIsNightwingOnDemand().booleanValue() ? 1 : 0)) == null) {
                    supportSQLiteStatement.bindNull(15);
                } else {
                    supportSQLiteStatement.bindLong(15, r0.intValue());
                }
                if ((playlist.getCanRename() == null ? null : Integer.valueOf(playlist.getCanRename().booleanValue() ? 1 : 0)) == null) {
                    supportSQLiteStatement.bindNull(16);
                } else {
                    supportSQLiteStatement.bindLong(16, r0.intValue());
                }
                if ((playlist.getCanEditContent() == null ? null : Integer.valueOf(playlist.getCanEditContent().booleanValue() ? 1 : 0)) == null) {
                    supportSQLiteStatement.bindNull(17);
                } else {
                    supportSQLiteStatement.bindLong(17, r0.intValue());
                }
                if ((playlist.getCanRemove() != null ? Integer.valueOf(playlist.getCanRemove().booleanValue() ? 1 : 0) : null) == null) {
                    supportSQLiteStatement.bindNull(18);
                } else {
                    supportSQLiteStatement.bindLong(18, r1.intValue());
                }
            }
        };
        this.__preparedStmtOfDeletePlaylist = new SharedSQLiteStatement(__db) { // from class: com.amazon.music.curate.data.database.PlaylistDao_Impl.2
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "Delete from Playlist where id = (?)";
            }
        };
        this.__preparedStmtOfDeletePersonalizedPlaylists = new SharedSQLiteStatement(__db) { // from class: com.amazon.music.curate.data.database.PlaylistDao_Impl.3
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "Delete from Playlist where is_personalized = 1";
            }
        };
    }

    @Override // com.amazon.music.curate.data.database.PlaylistDao
    public void putPlaylists(final List<? extends Playlist> playlists) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__insertionAdapterOfPlaylist.insert(playlists);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // com.amazon.music.curate.data.database.PlaylistDao
    public void deletePlaylist(final String playlistId) {
        this.__db.assertNotSuspendingTransaction();
        SupportSQLiteStatement supportSQLiteStatementAcquire = this.__preparedStmtOfDeletePlaylist.acquire();
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
            this.__preparedStmtOfDeletePlaylist.release(supportSQLiteStatementAcquire);
        }
    }

    @Override // com.amazon.music.curate.data.database.PlaylistDao
    public void deletePersonalizedPlaylists() {
        this.__db.assertNotSuspendingTransaction();
        SupportSQLiteStatement supportSQLiteStatementAcquire = this.__preparedStmtOfDeletePersonalizedPlaylists.acquire();
        this.__db.beginTransaction();
        try {
            supportSQLiteStatementAcquire.executeUpdateDelete();
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
            this.__preparedStmtOfDeletePersonalizedPlaylists.release(supportSQLiteStatementAcquire);
        }
    }

    @Override // com.amazon.music.curate.data.database.PlaylistDao
    public DataSource.Factory<Integer, Playlist> getPlaylists() {
        final RoomSQLiteQuery roomSQLiteQueryAcquire = RoomSQLiteQuery.acquire("Select * from Playlist", 0);
        return new DataSource.Factory<Integer, Playlist>() { // from class: com.amazon.music.curate.data.database.PlaylistDao_Impl.4
            @Override // androidx.paging.DataSource.Factory
            public DataSource<Integer, Playlist> create() {
                return new LimitOffsetDataSource<Playlist>(PlaylistDao_Impl.this.__db, roomSQLiteQueryAcquire, false, true, "Playlist") { // from class: com.amazon.music.curate.data.database.PlaylistDao_Impl.4.1
                    @Override // androidx.room.paging.LimitOffsetDataSource
                    protected List<Playlist> convertRows(Cursor cursor) {
                        Boolean boolValueOf;
                        Boolean boolValueOf2;
                        Boolean boolValueOf3;
                        Boolean boolValueOf4;
                        Boolean boolValueOf5;
                        Boolean boolValueOf6;
                        Boolean boolValueOf7;
                        Boolean boolValueOf8;
                        Boolean boolValueOf9;
                        Cursor cursor2 = cursor;
                        int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(cursor2, "id");
                        int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(cursor2, "title");
                        int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(cursor2, "artwork_url");
                        int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(cursor2, "banner_artwork_url");
                        int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(cursor2, "num_songs");
                        int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(cursor2, "duration_seconds");
                        int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(cursor2, "version");
                        int columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(cursor2, "expiry_time");
                        int columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(cursor2, "is_personalized");
                        int columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(cursor2, "is_followed");
                        int columnIndexOrThrow11 = CursorUtil.getColumnIndexOrThrow(cursor2, "is_shareable");
                        int columnIndexOrThrow12 = CursorUtil.getColumnIndexOrThrow(cursor2, "is_prime");
                        int columnIndexOrThrow13 = CursorUtil.getColumnIndexOrThrow(cursor2, "is_music_unlimited");
                        int columnIndexOrThrow14 = CursorUtil.getColumnIndexOrThrow(cursor2, "is_nightwing");
                        int columnIndexOrThrow15 = CursorUtil.getColumnIndexOrThrow(cursor2, "is_nightwing_on_demand");
                        int columnIndexOrThrow16 = CursorUtil.getColumnIndexOrThrow(cursor2, "can_rename");
                        int columnIndexOrThrow17 = CursorUtil.getColumnIndexOrThrow(cursor2, "can_edit_content");
                        int columnIndexOrThrow18 = CursorUtil.getColumnIndexOrThrow(cursor2, "can_remove");
                        int i = columnIndexOrThrow14;
                        ArrayList arrayList = new ArrayList(cursor.getCount());
                        while (cursor.moveToNext()) {
                            Boolean boolValueOf10 = null;
                            String string = cursor2.isNull(columnIndexOrThrow) ? null : cursor2.getString(columnIndexOrThrow);
                            String string2 = cursor2.isNull(columnIndexOrThrow2) ? null : cursor2.getString(columnIndexOrThrow2);
                            String string3 = cursor2.isNull(columnIndexOrThrow3) ? null : cursor2.getString(columnIndexOrThrow3);
                            String string4 = cursor2.isNull(columnIndexOrThrow4) ? null : cursor2.getString(columnIndexOrThrow4);
                            int i2 = cursor2.getInt(columnIndexOrThrow5);
                            Integer numValueOf = cursor2.isNull(columnIndexOrThrow6) ? null : Integer.valueOf(cursor2.getInt(columnIndexOrThrow6));
                            String string5 = cursor2.isNull(columnIndexOrThrow7) ? null : cursor2.getString(columnIndexOrThrow7);
                            long j = cursor2.getLong(columnIndexOrThrow8);
                            Integer numValueOf2 = cursor2.isNull(columnIndexOrThrow9) ? null : Integer.valueOf(cursor2.getInt(columnIndexOrThrow9));
                            if (numValueOf2 == null) {
                                boolValueOf = null;
                            } else {
                                boolValueOf = Boolean.valueOf(numValueOf2.intValue() != 0);
                            }
                            Integer numValueOf3 = cursor2.isNull(columnIndexOrThrow10) ? null : Integer.valueOf(cursor2.getInt(columnIndexOrThrow10));
                            if (numValueOf3 == null) {
                                boolValueOf2 = null;
                            } else {
                                boolValueOf2 = Boolean.valueOf(numValueOf3.intValue() != 0);
                            }
                            Integer numValueOf4 = cursor2.isNull(columnIndexOrThrow11) ? null : Integer.valueOf(cursor2.getInt(columnIndexOrThrow11));
                            if (numValueOf4 == null) {
                                boolValueOf3 = null;
                            } else {
                                boolValueOf3 = Boolean.valueOf(numValueOf4.intValue() != 0);
                            }
                            Integer numValueOf5 = cursor2.isNull(columnIndexOrThrow12) ? null : Integer.valueOf(cursor2.getInt(columnIndexOrThrow12));
                            if (numValueOf5 == null) {
                                boolValueOf4 = null;
                            } else {
                                boolValueOf4 = Boolean.valueOf(numValueOf5.intValue() != 0);
                            }
                            Integer numValueOf6 = cursor2.isNull(columnIndexOrThrow13) ? null : Integer.valueOf(cursor2.getInt(columnIndexOrThrow13));
                            if (numValueOf6 == null) {
                                boolValueOf5 = null;
                            } else {
                                boolValueOf5 = Boolean.valueOf(numValueOf6.intValue() != 0);
                            }
                            int i3 = i;
                            int i4 = columnIndexOrThrow;
                            Integer numValueOf7 = cursor2.isNull(i3) ? null : Integer.valueOf(cursor2.getInt(i3));
                            if (numValueOf7 == null) {
                                boolValueOf6 = null;
                            } else {
                                boolValueOf6 = Boolean.valueOf(numValueOf7.intValue() != 0);
                            }
                            int i5 = columnIndexOrThrow15;
                            Integer numValueOf8 = cursor2.isNull(i5) ? null : Integer.valueOf(cursor2.getInt(i5));
                            if (numValueOf8 == null) {
                                boolValueOf7 = null;
                            } else {
                                boolValueOf7 = Boolean.valueOf(numValueOf8.intValue() != 0);
                            }
                            int i6 = columnIndexOrThrow16;
                            Integer numValueOf9 = cursor2.isNull(i6) ? null : Integer.valueOf(cursor2.getInt(i6));
                            if (numValueOf9 == null) {
                                boolValueOf8 = null;
                            } else {
                                boolValueOf8 = Boolean.valueOf(numValueOf9.intValue() != 0);
                            }
                            int i7 = columnIndexOrThrow17;
                            Integer numValueOf10 = cursor2.isNull(i7) ? null : Integer.valueOf(cursor2.getInt(i7));
                            if (numValueOf10 == null) {
                                boolValueOf9 = null;
                            } else {
                                boolValueOf9 = Boolean.valueOf(numValueOf10.intValue() != 0);
                            }
                            int i8 = columnIndexOrThrow18;
                            Integer numValueOf11 = cursor2.isNull(i8) ? null : Integer.valueOf(cursor2.getInt(i8));
                            if (numValueOf11 != null) {
                                boolValueOf10 = Boolean.valueOf(numValueOf11.intValue() != 0);
                            }
                            arrayList.add(new Playlist(string, string2, string3, string4, i2, numValueOf, string5, j, boolValueOf, boolValueOf2, boolValueOf3, boolValueOf4, boolValueOf5, boolValueOf6, boolValueOf7, boolValueOf8, boolValueOf9, boolValueOf10));
                            cursor2 = cursor;
                            columnIndexOrThrow18 = i8;
                            columnIndexOrThrow = i4;
                            i = i3;
                            columnIndexOrThrow15 = i5;
                            columnIndexOrThrow16 = i6;
                            columnIndexOrThrow17 = i7;
                        }
                        return arrayList;
                    }
                };
            }
        };
    }

    @Override // com.amazon.music.curate.data.database.PlaylistDao
    public DataSource.Factory<Integer, Playlist> getPersonalizedPlaylists() {
        final RoomSQLiteQuery roomSQLiteQueryAcquire = RoomSQLiteQuery.acquire("Select * from Playlist where is_personalized = 1", 0);
        return new DataSource.Factory<Integer, Playlist>() { // from class: com.amazon.music.curate.data.database.PlaylistDao_Impl.5
            @Override // androidx.paging.DataSource.Factory
            public DataSource<Integer, Playlist> create() {
                return new LimitOffsetDataSource<Playlist>(PlaylistDao_Impl.this.__db, roomSQLiteQueryAcquire, false, true, "Playlist") { // from class: com.amazon.music.curate.data.database.PlaylistDao_Impl.5.1
                    @Override // androidx.room.paging.LimitOffsetDataSource
                    protected List<Playlist> convertRows(Cursor cursor) {
                        Boolean boolValueOf;
                        Boolean boolValueOf2;
                        Boolean boolValueOf3;
                        Boolean boolValueOf4;
                        Boolean boolValueOf5;
                        Boolean boolValueOf6;
                        Boolean boolValueOf7;
                        Boolean boolValueOf8;
                        Boolean boolValueOf9;
                        Cursor cursor2 = cursor;
                        int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(cursor2, "id");
                        int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(cursor2, "title");
                        int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(cursor2, "artwork_url");
                        int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(cursor2, "banner_artwork_url");
                        int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(cursor2, "num_songs");
                        int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(cursor2, "duration_seconds");
                        int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(cursor2, "version");
                        int columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(cursor2, "expiry_time");
                        int columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(cursor2, "is_personalized");
                        int columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(cursor2, "is_followed");
                        int columnIndexOrThrow11 = CursorUtil.getColumnIndexOrThrow(cursor2, "is_shareable");
                        int columnIndexOrThrow12 = CursorUtil.getColumnIndexOrThrow(cursor2, "is_prime");
                        int columnIndexOrThrow13 = CursorUtil.getColumnIndexOrThrow(cursor2, "is_music_unlimited");
                        int columnIndexOrThrow14 = CursorUtil.getColumnIndexOrThrow(cursor2, "is_nightwing");
                        int columnIndexOrThrow15 = CursorUtil.getColumnIndexOrThrow(cursor2, "is_nightwing_on_demand");
                        int columnIndexOrThrow16 = CursorUtil.getColumnIndexOrThrow(cursor2, "can_rename");
                        int columnIndexOrThrow17 = CursorUtil.getColumnIndexOrThrow(cursor2, "can_edit_content");
                        int columnIndexOrThrow18 = CursorUtil.getColumnIndexOrThrow(cursor2, "can_remove");
                        int i = columnIndexOrThrow14;
                        ArrayList arrayList = new ArrayList(cursor.getCount());
                        while (cursor.moveToNext()) {
                            Boolean boolValueOf10 = null;
                            String string = cursor2.isNull(columnIndexOrThrow) ? null : cursor2.getString(columnIndexOrThrow);
                            String string2 = cursor2.isNull(columnIndexOrThrow2) ? null : cursor2.getString(columnIndexOrThrow2);
                            String string3 = cursor2.isNull(columnIndexOrThrow3) ? null : cursor2.getString(columnIndexOrThrow3);
                            String string4 = cursor2.isNull(columnIndexOrThrow4) ? null : cursor2.getString(columnIndexOrThrow4);
                            int i2 = cursor2.getInt(columnIndexOrThrow5);
                            Integer numValueOf = cursor2.isNull(columnIndexOrThrow6) ? null : Integer.valueOf(cursor2.getInt(columnIndexOrThrow6));
                            String string5 = cursor2.isNull(columnIndexOrThrow7) ? null : cursor2.getString(columnIndexOrThrow7);
                            long j = cursor2.getLong(columnIndexOrThrow8);
                            Integer numValueOf2 = cursor2.isNull(columnIndexOrThrow9) ? null : Integer.valueOf(cursor2.getInt(columnIndexOrThrow9));
                            if (numValueOf2 == null) {
                                boolValueOf = null;
                            } else {
                                boolValueOf = Boolean.valueOf(numValueOf2.intValue() != 0);
                            }
                            Integer numValueOf3 = cursor2.isNull(columnIndexOrThrow10) ? null : Integer.valueOf(cursor2.getInt(columnIndexOrThrow10));
                            if (numValueOf3 == null) {
                                boolValueOf2 = null;
                            } else {
                                boolValueOf2 = Boolean.valueOf(numValueOf3.intValue() != 0);
                            }
                            Integer numValueOf4 = cursor2.isNull(columnIndexOrThrow11) ? null : Integer.valueOf(cursor2.getInt(columnIndexOrThrow11));
                            if (numValueOf4 == null) {
                                boolValueOf3 = null;
                            } else {
                                boolValueOf3 = Boolean.valueOf(numValueOf4.intValue() != 0);
                            }
                            Integer numValueOf5 = cursor2.isNull(columnIndexOrThrow12) ? null : Integer.valueOf(cursor2.getInt(columnIndexOrThrow12));
                            if (numValueOf5 == null) {
                                boolValueOf4 = null;
                            } else {
                                boolValueOf4 = Boolean.valueOf(numValueOf5.intValue() != 0);
                            }
                            Integer numValueOf6 = cursor2.isNull(columnIndexOrThrow13) ? null : Integer.valueOf(cursor2.getInt(columnIndexOrThrow13));
                            if (numValueOf6 == null) {
                                boolValueOf5 = null;
                            } else {
                                boolValueOf5 = Boolean.valueOf(numValueOf6.intValue() != 0);
                            }
                            int i3 = i;
                            int i4 = columnIndexOrThrow;
                            Integer numValueOf7 = cursor2.isNull(i3) ? null : Integer.valueOf(cursor2.getInt(i3));
                            if (numValueOf7 == null) {
                                boolValueOf6 = null;
                            } else {
                                boolValueOf6 = Boolean.valueOf(numValueOf7.intValue() != 0);
                            }
                            int i5 = columnIndexOrThrow15;
                            Integer numValueOf8 = cursor2.isNull(i5) ? null : Integer.valueOf(cursor2.getInt(i5));
                            if (numValueOf8 == null) {
                                boolValueOf7 = null;
                            } else {
                                boolValueOf7 = Boolean.valueOf(numValueOf8.intValue() != 0);
                            }
                            int i6 = columnIndexOrThrow16;
                            Integer numValueOf9 = cursor2.isNull(i6) ? null : Integer.valueOf(cursor2.getInt(i6));
                            if (numValueOf9 == null) {
                                boolValueOf8 = null;
                            } else {
                                boolValueOf8 = Boolean.valueOf(numValueOf9.intValue() != 0);
                            }
                            int i7 = columnIndexOrThrow17;
                            Integer numValueOf10 = cursor2.isNull(i7) ? null : Integer.valueOf(cursor2.getInt(i7));
                            if (numValueOf10 == null) {
                                boolValueOf9 = null;
                            } else {
                                boolValueOf9 = Boolean.valueOf(numValueOf10.intValue() != 0);
                            }
                            int i8 = columnIndexOrThrow18;
                            Integer numValueOf11 = cursor2.isNull(i8) ? null : Integer.valueOf(cursor2.getInt(i8));
                            if (numValueOf11 != null) {
                                boolValueOf10 = Boolean.valueOf(numValueOf11.intValue() != 0);
                            }
                            arrayList.add(new Playlist(string, string2, string3, string4, i2, numValueOf, string5, j, boolValueOf, boolValueOf2, boolValueOf3, boolValueOf4, boolValueOf5, boolValueOf6, boolValueOf7, boolValueOf8, boolValueOf9, boolValueOf10));
                            cursor2 = cursor;
                            columnIndexOrThrow18 = i8;
                            columnIndexOrThrow = i4;
                            i = i3;
                            columnIndexOrThrow15 = i5;
                            columnIndexOrThrow16 = i6;
                            columnIndexOrThrow17 = i7;
                        }
                        return arrayList;
                    }
                };
            }
        };
    }

    @Override // com.amazon.music.curate.data.database.PlaylistDao
    public Playlist getPlaylist(final String playlistId) throws Throwable {
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
        int columnIndexOrThrow13;
        int columnIndexOrThrow14;
        Playlist playlist;
        Boolean boolValueOf;
        Boolean boolValueOf2;
        Boolean boolValueOf3;
        Boolean boolValueOf4;
        Boolean boolValueOf5;
        Boolean boolValueOf6;
        int i;
        Boolean boolValueOf7;
        int i2;
        Boolean boolValueOf8;
        int i3;
        Boolean boolValueOf9;
        Boolean boolValueOf10;
        RoomSQLiteQuery roomSQLiteQueryAcquire = RoomSQLiteQuery.acquire("Select * from Playlist where id = (?)", 1);
        if (playlistId == null) {
            roomSQLiteQueryAcquire.bindNull(1);
        } else {
            roomSQLiteQueryAcquire.bindString(1, playlistId);
        }
        this.__db.assertNotSuspendingTransaction();
        Cursor cursorQuery = DBUtil.query(this.__db, roomSQLiteQueryAcquire, false, null);
        try {
            columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(cursorQuery, "id");
            columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "title");
            columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "artwork_url");
            columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "banner_artwork_url");
            columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "num_songs");
            columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "duration_seconds");
            columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "version");
            columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "expiry_time");
            columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "is_personalized");
            columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "is_followed");
            columnIndexOrThrow11 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "is_shareable");
            columnIndexOrThrow12 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "is_prime");
            columnIndexOrThrow13 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "is_music_unlimited");
            columnIndexOrThrow14 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "is_nightwing");
            roomSQLiteQuery = roomSQLiteQueryAcquire;
        } catch (Throwable th) {
            th = th;
            roomSQLiteQuery = roomSQLiteQueryAcquire;
        }
        try {
            int columnIndexOrThrow15 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "is_nightwing_on_demand");
            int columnIndexOrThrow16 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "can_rename");
            int columnIndexOrThrow17 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "can_edit_content");
            int columnIndexOrThrow18 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "can_remove");
            if (cursorQuery.moveToFirst()) {
                String string = cursorQuery.isNull(columnIndexOrThrow) ? null : cursorQuery.getString(columnIndexOrThrow);
                String string2 = cursorQuery.isNull(columnIndexOrThrow2) ? null : cursorQuery.getString(columnIndexOrThrow2);
                String string3 = cursorQuery.isNull(columnIndexOrThrow3) ? null : cursorQuery.getString(columnIndexOrThrow3);
                String string4 = cursorQuery.isNull(columnIndexOrThrow4) ? null : cursorQuery.getString(columnIndexOrThrow4);
                int i4 = cursorQuery.getInt(columnIndexOrThrow5);
                Integer numValueOf = cursorQuery.isNull(columnIndexOrThrow6) ? null : Integer.valueOf(cursorQuery.getInt(columnIndexOrThrow6));
                String string5 = cursorQuery.isNull(columnIndexOrThrow7) ? null : cursorQuery.getString(columnIndexOrThrow7);
                long j = cursorQuery.getLong(columnIndexOrThrow8);
                Integer numValueOf2 = cursorQuery.isNull(columnIndexOrThrow9) ? null : Integer.valueOf(cursorQuery.getInt(columnIndexOrThrow9));
                if (numValueOf2 == null) {
                    boolValueOf = null;
                } else {
                    boolValueOf = Boolean.valueOf(numValueOf2.intValue() != 0);
                }
                Integer numValueOf3 = cursorQuery.isNull(columnIndexOrThrow10) ? null : Integer.valueOf(cursorQuery.getInt(columnIndexOrThrow10));
                if (numValueOf3 == null) {
                    boolValueOf2 = null;
                } else {
                    boolValueOf2 = Boolean.valueOf(numValueOf3.intValue() != 0);
                }
                Integer numValueOf4 = cursorQuery.isNull(columnIndexOrThrow11) ? null : Integer.valueOf(cursorQuery.getInt(columnIndexOrThrow11));
                if (numValueOf4 == null) {
                    boolValueOf3 = null;
                } else {
                    boolValueOf3 = Boolean.valueOf(numValueOf4.intValue() != 0);
                }
                Integer numValueOf5 = cursorQuery.isNull(columnIndexOrThrow12) ? null : Integer.valueOf(cursorQuery.getInt(columnIndexOrThrow12));
                if (numValueOf5 == null) {
                    boolValueOf4 = null;
                } else {
                    boolValueOf4 = Boolean.valueOf(numValueOf5.intValue() != 0);
                }
                Integer numValueOf6 = cursorQuery.isNull(columnIndexOrThrow13) ? null : Integer.valueOf(cursorQuery.getInt(columnIndexOrThrow13));
                if (numValueOf6 == null) {
                    boolValueOf5 = null;
                } else {
                    boolValueOf5 = Boolean.valueOf(numValueOf6.intValue() != 0);
                }
                Integer numValueOf7 = cursorQuery.isNull(columnIndexOrThrow14) ? null : Integer.valueOf(cursorQuery.getInt(columnIndexOrThrow14));
                if (numValueOf7 == null) {
                    i = columnIndexOrThrow15;
                    boolValueOf6 = null;
                } else {
                    boolValueOf6 = Boolean.valueOf(numValueOf7.intValue() != 0);
                    i = columnIndexOrThrow15;
                }
                Integer numValueOf8 = cursorQuery.isNull(i) ? null : Integer.valueOf(cursorQuery.getInt(i));
                if (numValueOf8 == null) {
                    i2 = columnIndexOrThrow16;
                    boolValueOf7 = null;
                } else {
                    boolValueOf7 = Boolean.valueOf(numValueOf8.intValue() != 0);
                    i2 = columnIndexOrThrow16;
                }
                Integer numValueOf9 = cursorQuery.isNull(i2) ? null : Integer.valueOf(cursorQuery.getInt(i2));
                if (numValueOf9 == null) {
                    i3 = columnIndexOrThrow17;
                    boolValueOf8 = null;
                } else {
                    boolValueOf8 = Boolean.valueOf(numValueOf9.intValue() != 0);
                    i3 = columnIndexOrThrow17;
                }
                Integer numValueOf10 = cursorQuery.isNull(i3) ? null : Integer.valueOf(cursorQuery.getInt(i3));
                if (numValueOf10 == null) {
                    boolValueOf9 = null;
                } else {
                    boolValueOf9 = Boolean.valueOf(numValueOf10.intValue() != 0);
                }
                Integer numValueOf11 = cursorQuery.isNull(columnIndexOrThrow18) ? null : Integer.valueOf(cursorQuery.getInt(columnIndexOrThrow18));
                if (numValueOf11 == null) {
                    boolValueOf10 = null;
                } else {
                    boolValueOf10 = Boolean.valueOf(numValueOf11.intValue() != 0);
                }
                playlist = new Playlist(string, string2, string3, string4, i4, numValueOf, string5, j, boolValueOf, boolValueOf2, boolValueOf3, boolValueOf4, boolValueOf5, boolValueOf6, boolValueOf7, boolValueOf8, boolValueOf9, boolValueOf10);
            } else {
                playlist = null;
            }
            cursorQuery.close();
            roomSQLiteQuery.release();
            return playlist;
        } catch (Throwable th2) {
            th = th2;
            cursorQuery.close();
            roomSQLiteQuery.release();
            throw th;
        }
    }

    @Override // com.amazon.music.curate.data.database.PlaylistDao
    public LiveData<Playlist> getPlaylistLiveData(final String playlistId) {
        final RoomSQLiteQuery roomSQLiteQueryAcquire = RoomSQLiteQuery.acquire("Select * from Playlist where id = (?)", 1);
        if (playlistId == null) {
            roomSQLiteQueryAcquire.bindNull(1);
        } else {
            roomSQLiteQueryAcquire.bindString(1, playlistId);
        }
        return this.__db.getInvalidationTracker().createLiveData(new String[]{"Playlist"}, false, new Callable<Playlist>() { // from class: com.amazon.music.curate.data.database.PlaylistDao_Impl.6
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // java.util.concurrent.Callable
            public Playlist call() throws Exception {
                Playlist playlist;
                Boolean boolValueOf;
                Boolean boolValueOf2;
                Boolean boolValueOf3;
                Boolean boolValueOf4;
                Boolean boolValueOf5;
                Boolean boolValueOf6;
                int i;
                Boolean boolValueOf7;
                int i2;
                Boolean boolValueOf8;
                int i3;
                Boolean boolValueOf9;
                Boolean boolValueOf10;
                Cursor cursorQuery = DBUtil.query(PlaylistDao_Impl.this.__db, roomSQLiteQueryAcquire, false, null);
                try {
                    int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(cursorQuery, "id");
                    int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "title");
                    int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "artwork_url");
                    int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "banner_artwork_url");
                    int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "num_songs");
                    int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "duration_seconds");
                    int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "version");
                    int columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "expiry_time");
                    int columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "is_personalized");
                    int columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "is_followed");
                    int columnIndexOrThrow11 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "is_shareable");
                    int columnIndexOrThrow12 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "is_prime");
                    int columnIndexOrThrow13 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "is_music_unlimited");
                    int columnIndexOrThrow14 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "is_nightwing");
                    int columnIndexOrThrow15 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "is_nightwing_on_demand");
                    int columnIndexOrThrow16 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "can_rename");
                    int columnIndexOrThrow17 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "can_edit_content");
                    int columnIndexOrThrow18 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "can_remove");
                    if (cursorQuery.moveToFirst()) {
                        String string = cursorQuery.isNull(columnIndexOrThrow) ? null : cursorQuery.getString(columnIndexOrThrow);
                        String string2 = cursorQuery.isNull(columnIndexOrThrow2) ? null : cursorQuery.getString(columnIndexOrThrow2);
                        String string3 = cursorQuery.isNull(columnIndexOrThrow3) ? null : cursorQuery.getString(columnIndexOrThrow3);
                        String string4 = cursorQuery.isNull(columnIndexOrThrow4) ? null : cursorQuery.getString(columnIndexOrThrow4);
                        int i4 = cursorQuery.getInt(columnIndexOrThrow5);
                        Integer numValueOf = cursorQuery.isNull(columnIndexOrThrow6) ? null : Integer.valueOf(cursorQuery.getInt(columnIndexOrThrow6));
                        String string5 = cursorQuery.isNull(columnIndexOrThrow7) ? null : cursorQuery.getString(columnIndexOrThrow7);
                        long j = cursorQuery.getLong(columnIndexOrThrow8);
                        Integer numValueOf2 = cursorQuery.isNull(columnIndexOrThrow9) ? null : Integer.valueOf(cursorQuery.getInt(columnIndexOrThrow9));
                        if (numValueOf2 == null) {
                            boolValueOf = null;
                        } else {
                            boolValueOf = Boolean.valueOf(numValueOf2.intValue() != 0);
                        }
                        Integer numValueOf3 = cursorQuery.isNull(columnIndexOrThrow10) ? null : Integer.valueOf(cursorQuery.getInt(columnIndexOrThrow10));
                        if (numValueOf3 == null) {
                            boolValueOf2 = null;
                        } else {
                            boolValueOf2 = Boolean.valueOf(numValueOf3.intValue() != 0);
                        }
                        Integer numValueOf4 = cursorQuery.isNull(columnIndexOrThrow11) ? null : Integer.valueOf(cursorQuery.getInt(columnIndexOrThrow11));
                        if (numValueOf4 == null) {
                            boolValueOf3 = null;
                        } else {
                            boolValueOf3 = Boolean.valueOf(numValueOf4.intValue() != 0);
                        }
                        Integer numValueOf5 = cursorQuery.isNull(columnIndexOrThrow12) ? null : Integer.valueOf(cursorQuery.getInt(columnIndexOrThrow12));
                        if (numValueOf5 == null) {
                            boolValueOf4 = null;
                        } else {
                            boolValueOf4 = Boolean.valueOf(numValueOf5.intValue() != 0);
                        }
                        Integer numValueOf6 = cursorQuery.isNull(columnIndexOrThrow13) ? null : Integer.valueOf(cursorQuery.getInt(columnIndexOrThrow13));
                        if (numValueOf6 == null) {
                            boolValueOf5 = null;
                        } else {
                            boolValueOf5 = Boolean.valueOf(numValueOf6.intValue() != 0);
                        }
                        Integer numValueOf7 = cursorQuery.isNull(columnIndexOrThrow14) ? null : Integer.valueOf(cursorQuery.getInt(columnIndexOrThrow14));
                        if (numValueOf7 == null) {
                            i = columnIndexOrThrow15;
                            boolValueOf6 = null;
                        } else {
                            boolValueOf6 = Boolean.valueOf(numValueOf7.intValue() != 0);
                            i = columnIndexOrThrow15;
                        }
                        Integer numValueOf8 = cursorQuery.isNull(i) ? null : Integer.valueOf(cursorQuery.getInt(i));
                        if (numValueOf8 == null) {
                            i2 = columnIndexOrThrow16;
                            boolValueOf7 = null;
                        } else {
                            boolValueOf7 = Boolean.valueOf(numValueOf8.intValue() != 0);
                            i2 = columnIndexOrThrow16;
                        }
                        Integer numValueOf9 = cursorQuery.isNull(i2) ? null : Integer.valueOf(cursorQuery.getInt(i2));
                        if (numValueOf9 == null) {
                            i3 = columnIndexOrThrow17;
                            boolValueOf8 = null;
                        } else {
                            boolValueOf8 = Boolean.valueOf(numValueOf9.intValue() != 0);
                            i3 = columnIndexOrThrow17;
                        }
                        Integer numValueOf10 = cursorQuery.isNull(i3) ? null : Integer.valueOf(cursorQuery.getInt(i3));
                        if (numValueOf10 == null) {
                            boolValueOf9 = null;
                        } else {
                            boolValueOf9 = Boolean.valueOf(numValueOf10.intValue() != 0);
                        }
                        Integer numValueOf11 = cursorQuery.isNull(columnIndexOrThrow18) ? null : Integer.valueOf(cursorQuery.getInt(columnIndexOrThrow18));
                        if (numValueOf11 == null) {
                            boolValueOf10 = null;
                        } else {
                            boolValueOf10 = Boolean.valueOf(numValueOf11.intValue() != 0);
                        }
                        playlist = new Playlist(string, string2, string3, string4, i4, numValueOf, string5, j, boolValueOf, boolValueOf2, boolValueOf3, boolValueOf4, boolValueOf5, boolValueOf6, boolValueOf7, boolValueOf8, boolValueOf9, boolValueOf10);
                    } else {
                        playlist = null;
                    }
                    return playlist;
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
