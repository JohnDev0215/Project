package com.amazon.podcast.save;

import android.database.Cursor;
import androidx.lifecycle.LiveData;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.amazon.music.curate.skyfire.bootstrap.ContextMappingConstants;
import com.amazon.podcast.storage.PodcastRoomTypeConverters;
import com.google.android.gms.cast.MediaTrack;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;

/* JADX INFO: loaded from: classes5.dex */
public final class SavesDao_Impl implements SavesDao {
    private final RoomDatabase __db;
    private final EntityInsertionAdapter<Save> __insertionAdapterOfSave;
    private final PodcastRoomTypeConverters __podcastRoomTypeConverters = new PodcastRoomTypeConverters();
    private final SharedSQLiteStatement __preparedStmtOfDeleteAll;

    public SavesDao_Impl(RoomDatabase __db) {
        this.__db = __db;
        this.__insertionAdapterOfSave = new EntityInsertionAdapter<Save>(__db) { // from class: com.amazon.podcast.save.SavesDao_Impl.1
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "INSERT OR REPLACE INTO `saves` (`id`,`podcastId`,`podcastTitle`,`title`,`image`,`podcastImage`,`publishDate`,`description`,`authors`,`seasonNumber`,`totalDurationMilliseconds`,`saved`,`updatedTime`,`playbackMode`,`contentTraits`,`availableUpsells`,`availabilityDate`,`podcastShowVariantId`,`podcastEpisodeVariantId`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            }

            @Override // androidx.room.EntityInsertionAdapter
            public void bind(SupportSQLiteStatement supportSQLiteStatement, Save save) {
                if (save.getId() == null) {
                    supportSQLiteStatement.bindNull(1);
                } else {
                    supportSQLiteStatement.bindString(1, save.getId());
                }
                if (save.getPodcastId() == null) {
                    supportSQLiteStatement.bindNull(2);
                } else {
                    supportSQLiteStatement.bindString(2, save.getPodcastId());
                }
                if (save.getPodcastTitle() == null) {
                    supportSQLiteStatement.bindNull(3);
                } else {
                    supportSQLiteStatement.bindString(3, save.getPodcastTitle());
                }
                if (save.getTitle() == null) {
                    supportSQLiteStatement.bindNull(4);
                } else {
                    supportSQLiteStatement.bindString(4, save.getTitle());
                }
                if (save.getImage() == null) {
                    supportSQLiteStatement.bindNull(5);
                } else {
                    supportSQLiteStatement.bindString(5, save.getImage());
                }
                if (save.getPodcastImage() == null) {
                    supportSQLiteStatement.bindNull(6);
                } else {
                    supportSQLiteStatement.bindString(6, save.getPodcastImage());
                }
                if (save.getPublishDate() == null) {
                    supportSQLiteStatement.bindNull(7);
                } else {
                    supportSQLiteStatement.bindString(7, save.getPublishDate());
                }
                if (save.getDescription() == null) {
                    supportSQLiteStatement.bindNull(8);
                } else {
                    supportSQLiteStatement.bindString(8, save.getDescription());
                }
                if (save.getAuthors() == null) {
                    supportSQLiteStatement.bindNull(9);
                } else {
                    supportSQLiteStatement.bindString(9, save.getAuthors());
                }
                if (save.getSeasonNumber() == null) {
                    supportSQLiteStatement.bindNull(10);
                } else {
                    supportSQLiteStatement.bindString(10, save.getSeasonNumber());
                }
                supportSQLiteStatement.bindLong(11, save.getTotalDurationMilliseconds());
                supportSQLiteStatement.bindLong(12, save.isSaved() ? 1L : 0L);
                supportSQLiteStatement.bindLong(13, save.getUpdatedTime());
                if (save.getPlaybackMode() == null) {
                    supportSQLiteStatement.bindNull(14);
                } else {
                    supportSQLiteStatement.bindString(14, save.getPlaybackMode());
                }
                String strStringListToString = SavesDao_Impl.this.__podcastRoomTypeConverters.stringListToString(save.getContentTraits());
                if (strStringListToString == null) {
                    supportSQLiteStatement.bindNull(15);
                } else {
                    supportSQLiteStatement.bindString(15, strStringListToString);
                }
                String strStringListToString2 = SavesDao_Impl.this.__podcastRoomTypeConverters.stringListToString(save.getAvailableUpsells());
                if (strStringListToString2 == null) {
                    supportSQLiteStatement.bindNull(16);
                } else {
                    supportSQLiteStatement.bindString(16, strStringListToString2);
                }
                if (save.getAvailabilityDate() == null) {
                    supportSQLiteStatement.bindNull(17);
                } else {
                    supportSQLiteStatement.bindString(17, save.getAvailabilityDate());
                }
                if (save.getPodcastShowVariantId() == null) {
                    supportSQLiteStatement.bindNull(18);
                } else {
                    supportSQLiteStatement.bindString(18, save.getPodcastShowVariantId());
                }
                if (save.getPodcastEpisodeVariantId() == null) {
                    supportSQLiteStatement.bindNull(19);
                } else {
                    supportSQLiteStatement.bindString(19, save.getPodcastEpisodeVariantId());
                }
            }
        };
        this.__preparedStmtOfDeleteAll = new SharedSQLiteStatement(__db) { // from class: com.amazon.podcast.save.SavesDao_Impl.2
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "DELETE FROM saves";
            }
        };
    }

    @Override // com.amazon.podcast.save.SavesDao
    public void insert(final Save save) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__insertionAdapterOfSave.insert(save);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // com.amazon.podcast.save.SavesDao
    public LiveData<Save> getLiveData(final String id) {
        final RoomSQLiteQuery roomSQLiteQueryAcquire = RoomSQLiteQuery.acquire("SELECT * FROM saves WHERE id = ?", 1);
        if (id == null) {
            roomSQLiteQueryAcquire.bindNull(1);
        } else {
            roomSQLiteQueryAcquire.bindString(1, id);
        }
        return this.__db.getInvalidationTracker().createLiveData(new String[]{"saves"}, false, new Callable<Save>() { // from class: com.amazon.podcast.save.SavesDao_Impl.3
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // java.util.concurrent.Callable
            public Save call() throws Exception {
                Save save;
                String string;
                int i;
                String string2;
                int i2;
                Cursor cursorQuery = DBUtil.query(SavesDao_Impl.this.__db, roomSQLiteQueryAcquire, false, null);
                try {
                    int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(cursorQuery, "id");
                    int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "podcastId");
                    int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "podcastTitle");
                    int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "title");
                    int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(cursorQuery, ContextMappingConstants.IMAGE);
                    int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "podcastImage");
                    int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "publishDate");
                    int columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(cursorQuery, MediaTrack.ROLE_DESCRIPTION);
                    int columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "authors");
                    int columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "seasonNumber");
                    int columnIndexOrThrow11 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "totalDurationMilliseconds");
                    int columnIndexOrThrow12 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "saved");
                    int columnIndexOrThrow13 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "updatedTime");
                    int columnIndexOrThrow14 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "playbackMode");
                    try {
                        int columnIndexOrThrow15 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "contentTraits");
                        int columnIndexOrThrow16 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "availableUpsells");
                        int columnIndexOrThrow17 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "availabilityDate");
                        int columnIndexOrThrow18 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "podcastShowVariantId");
                        int columnIndexOrThrow19 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "podcastEpisodeVariantId");
                        if (cursorQuery.moveToFirst()) {
                            String string3 = cursorQuery.isNull(columnIndexOrThrow) ? null : cursorQuery.getString(columnIndexOrThrow);
                            String string4 = cursorQuery.isNull(columnIndexOrThrow2) ? null : cursorQuery.getString(columnIndexOrThrow2);
                            String string5 = cursorQuery.isNull(columnIndexOrThrow3) ? null : cursorQuery.getString(columnIndexOrThrow3);
                            String string6 = cursorQuery.isNull(columnIndexOrThrow4) ? null : cursorQuery.getString(columnIndexOrThrow4);
                            String string7 = cursorQuery.isNull(columnIndexOrThrow5) ? null : cursorQuery.getString(columnIndexOrThrow5);
                            String string8 = cursorQuery.isNull(columnIndexOrThrow6) ? null : cursorQuery.getString(columnIndexOrThrow6);
                            String string9 = cursorQuery.isNull(columnIndexOrThrow7) ? null : cursorQuery.getString(columnIndexOrThrow7);
                            String string10 = cursorQuery.isNull(columnIndexOrThrow8) ? null : cursorQuery.getString(columnIndexOrThrow8);
                            String string11 = cursorQuery.isNull(columnIndexOrThrow9) ? null : cursorQuery.getString(columnIndexOrThrow9);
                            String string12 = cursorQuery.isNull(columnIndexOrThrow10) ? null : cursorQuery.getString(columnIndexOrThrow10);
                            long j = cursorQuery.getLong(columnIndexOrThrow11);
                            boolean z = cursorQuery.getInt(columnIndexOrThrow12) != 0;
                            long j2 = cursorQuery.getLong(columnIndexOrThrow13);
                            if (cursorQuery.isNull(columnIndexOrThrow14)) {
                                i = columnIndexOrThrow15;
                                string = null;
                            } else {
                                string = cursorQuery.getString(columnIndexOrThrow14);
                                i = columnIndexOrThrow15;
                            }
                            try {
                                List<String> listStringToStringList = SavesDao_Impl.this.__podcastRoomTypeConverters.stringToStringList(cursorQuery.isNull(i) ? null : cursorQuery.getString(i));
                                List<String> listStringToStringList2 = SavesDao_Impl.this.__podcastRoomTypeConverters.stringToStringList(cursorQuery.isNull(columnIndexOrThrow16) ? null : cursorQuery.getString(columnIndexOrThrow16));
                                if (cursorQuery.isNull(columnIndexOrThrow17)) {
                                    i2 = columnIndexOrThrow18;
                                    string2 = null;
                                } else {
                                    string2 = cursorQuery.getString(columnIndexOrThrow17);
                                    i2 = columnIndexOrThrow18;
                                }
                                save = new Save(string3, string4, string5, string6, string7, string8, string9, string10, string11, string12, j, z, j2, string, listStringToStringList, listStringToStringList2, string2, cursorQuery.isNull(i2) ? null : cursorQuery.getString(i2), cursorQuery.isNull(columnIndexOrThrow19) ? null : cursorQuery.getString(columnIndexOrThrow19));
                            } catch (Throwable th) {
                                th = th;
                                cursorQuery.close();
                                throw th;
                            }
                        } else {
                            save = null;
                        }
                        cursorQuery.close();
                        return save;
                    } catch (Throwable th2) {
                        th = th2;
                    }
                } catch (Throwable th3) {
                    th = th3;
                }
            }

            protected void finalize() {
                roomSQLiteQueryAcquire.release();
            }
        });
    }

    @Override // com.amazon.podcast.save.SavesDao
    public Save get(final String id) throws Throwable {
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
        Save save;
        String string;
        int i;
        String string2;
        int i2;
        RoomSQLiteQuery roomSQLiteQueryAcquire = RoomSQLiteQuery.acquire("SELECT * FROM saves WHERE id = ?", 1);
        if (id == null) {
            roomSQLiteQueryAcquire.bindNull(1);
        } else {
            roomSQLiteQueryAcquire.bindString(1, id);
        }
        this.__db.assertNotSuspendingTransaction();
        Cursor cursorQuery = DBUtil.query(this.__db, roomSQLiteQueryAcquire, false, null);
        try {
            columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(cursorQuery, "id");
            columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "podcastId");
            columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "podcastTitle");
            columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "title");
            columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(cursorQuery, ContextMappingConstants.IMAGE);
            columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "podcastImage");
            columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "publishDate");
            columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(cursorQuery, MediaTrack.ROLE_DESCRIPTION);
            columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "authors");
            columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "seasonNumber");
            columnIndexOrThrow11 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "totalDurationMilliseconds");
            columnIndexOrThrow12 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "saved");
            columnIndexOrThrow13 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "updatedTime");
            roomSQLiteQuery = roomSQLiteQueryAcquire;
        } catch (Throwable th) {
            th = th;
            roomSQLiteQuery = roomSQLiteQueryAcquire;
        }
        try {
            int columnIndexOrThrow14 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "playbackMode");
            try {
                int columnIndexOrThrow15 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "contentTraits");
                int columnIndexOrThrow16 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "availableUpsells");
                int columnIndexOrThrow17 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "availabilityDate");
                int columnIndexOrThrow18 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "podcastShowVariantId");
                int columnIndexOrThrow19 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "podcastEpisodeVariantId");
                if (cursorQuery.moveToFirst()) {
                    String string3 = cursorQuery.isNull(columnIndexOrThrow) ? null : cursorQuery.getString(columnIndexOrThrow);
                    String string4 = cursorQuery.isNull(columnIndexOrThrow2) ? null : cursorQuery.getString(columnIndexOrThrow2);
                    String string5 = cursorQuery.isNull(columnIndexOrThrow3) ? null : cursorQuery.getString(columnIndexOrThrow3);
                    String string6 = cursorQuery.isNull(columnIndexOrThrow4) ? null : cursorQuery.getString(columnIndexOrThrow4);
                    String string7 = cursorQuery.isNull(columnIndexOrThrow5) ? null : cursorQuery.getString(columnIndexOrThrow5);
                    String string8 = cursorQuery.isNull(columnIndexOrThrow6) ? null : cursorQuery.getString(columnIndexOrThrow6);
                    String string9 = cursorQuery.isNull(columnIndexOrThrow7) ? null : cursorQuery.getString(columnIndexOrThrow7);
                    String string10 = cursorQuery.isNull(columnIndexOrThrow8) ? null : cursorQuery.getString(columnIndexOrThrow8);
                    String string11 = cursorQuery.isNull(columnIndexOrThrow9) ? null : cursorQuery.getString(columnIndexOrThrow9);
                    String string12 = cursorQuery.isNull(columnIndexOrThrow10) ? null : cursorQuery.getString(columnIndexOrThrow10);
                    long j = cursorQuery.getLong(columnIndexOrThrow11);
                    boolean z = cursorQuery.getInt(columnIndexOrThrow12) != 0;
                    long j2 = cursorQuery.getLong(columnIndexOrThrow13);
                    if (cursorQuery.isNull(columnIndexOrThrow14)) {
                        i = columnIndexOrThrow15;
                        string = null;
                    } else {
                        string = cursorQuery.getString(columnIndexOrThrow14);
                        i = columnIndexOrThrow15;
                    }
                    try {
                        List<String> listStringToStringList = this.__podcastRoomTypeConverters.stringToStringList(cursorQuery.isNull(i) ? null : cursorQuery.getString(i));
                        List<String> listStringToStringList2 = this.__podcastRoomTypeConverters.stringToStringList(cursorQuery.isNull(columnIndexOrThrow16) ? null : cursorQuery.getString(columnIndexOrThrow16));
                        if (cursorQuery.isNull(columnIndexOrThrow17)) {
                            i2 = columnIndexOrThrow18;
                            string2 = null;
                        } else {
                            string2 = cursorQuery.getString(columnIndexOrThrow17);
                            i2 = columnIndexOrThrow18;
                        }
                        save = new Save(string3, string4, string5, string6, string7, string8, string9, string10, string11, string12, j, z, j2, string, listStringToStringList, listStringToStringList2, string2, cursorQuery.isNull(i2) ? null : cursorQuery.getString(i2), cursorQuery.isNull(columnIndexOrThrow19) ? null : cursorQuery.getString(columnIndexOrThrow19));
                    } catch (Throwable th2) {
                        th = th2;
                        cursorQuery.close();
                        roomSQLiteQuery.release();
                        throw th;
                    }
                } else {
                    save = null;
                }
                cursorQuery.close();
                roomSQLiteQuery.release();
                return save;
            } catch (Throwable th3) {
                th = th3;
            }
        } catch (Throwable th4) {
            th = th4;
            cursorQuery.close();
            roomSQLiteQuery.release();
            throw th;
        }
    }

    @Override // com.amazon.podcast.save.SavesDao
    public LiveData<List<Save>> getAllLiveData(boolean z) {
        final RoomSQLiteQuery roomSQLiteQueryAcquire = RoomSQLiteQuery.acquire("SELECT * FROM saves WHERE saved = ? ORDER BY updatedTime DESC", 1);
        roomSQLiteQueryAcquire.bindLong(1, z ? 1L : 0L);
        return this.__db.getInvalidationTracker().createLiveData(new String[]{"saves"}, false, new Callable<List<Save>>() { // from class: com.amazon.podcast.save.SavesDao_Impl.4
            @Override // java.util.concurrent.Callable
            public List<Save> call() throws Exception {
                int i;
                int i2;
                String string;
                int i3;
                String string2;
                String string3;
                int i4;
                String string4;
                int i5;
                String string5;
                Cursor cursorQuery = DBUtil.query(SavesDao_Impl.this.__db, roomSQLiteQueryAcquire, false, null);
                try {
                    int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(cursorQuery, "id");
                    int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "podcastId");
                    int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "podcastTitle");
                    int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "title");
                    int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(cursorQuery, ContextMappingConstants.IMAGE);
                    int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "podcastImage");
                    int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "publishDate");
                    int columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(cursorQuery, MediaTrack.ROLE_DESCRIPTION);
                    int columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "authors");
                    int columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "seasonNumber");
                    int columnIndexOrThrow11 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "totalDurationMilliseconds");
                    int columnIndexOrThrow12 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "saved");
                    int columnIndexOrThrow13 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "updatedTime");
                    int columnIndexOrThrow14 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "playbackMode");
                    try {
                        int columnIndexOrThrow15 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "contentTraits");
                        int columnIndexOrThrow16 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "availableUpsells");
                        int columnIndexOrThrow17 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "availabilityDate");
                        int columnIndexOrThrow18 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "podcastShowVariantId");
                        int columnIndexOrThrow19 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "podcastEpisodeVariantId");
                        int i6 = columnIndexOrThrow14;
                        ArrayList arrayList = new ArrayList(cursorQuery.getCount());
                        while (cursorQuery.moveToNext()) {
                            String string6 = cursorQuery.isNull(columnIndexOrThrow) ? null : cursorQuery.getString(columnIndexOrThrow);
                            String string7 = cursorQuery.isNull(columnIndexOrThrow2) ? null : cursorQuery.getString(columnIndexOrThrow2);
                            String string8 = cursorQuery.isNull(columnIndexOrThrow3) ? null : cursorQuery.getString(columnIndexOrThrow3);
                            String string9 = cursorQuery.isNull(columnIndexOrThrow4) ? null : cursorQuery.getString(columnIndexOrThrow4);
                            String string10 = cursorQuery.isNull(columnIndexOrThrow5) ? null : cursorQuery.getString(columnIndexOrThrow5);
                            String string11 = cursorQuery.isNull(columnIndexOrThrow6) ? null : cursorQuery.getString(columnIndexOrThrow6);
                            String string12 = cursorQuery.isNull(columnIndexOrThrow7) ? null : cursorQuery.getString(columnIndexOrThrow7);
                            String string13 = cursorQuery.isNull(columnIndexOrThrow8) ? null : cursorQuery.getString(columnIndexOrThrow8);
                            String string14 = cursorQuery.isNull(columnIndexOrThrow9) ? null : cursorQuery.getString(columnIndexOrThrow9);
                            String string15 = cursorQuery.isNull(columnIndexOrThrow10) ? null : cursorQuery.getString(columnIndexOrThrow10);
                            long j = cursorQuery.getLong(columnIndexOrThrow11);
                            boolean z2 = cursorQuery.getInt(columnIndexOrThrow12) != 0;
                            long j2 = cursorQuery.getLong(columnIndexOrThrow13);
                            int i7 = i6;
                            String string16 = cursorQuery.isNull(i7) ? null : cursorQuery.getString(i7);
                            int i8 = columnIndexOrThrow15;
                            int i9 = columnIndexOrThrow;
                            if (cursorQuery.isNull(i8)) {
                                i = i8;
                                i3 = columnIndexOrThrow13;
                                i2 = i7;
                                string = null;
                            } else {
                                i = i8;
                                i2 = i7;
                                string = cursorQuery.getString(i8);
                                i3 = columnIndexOrThrow13;
                            }
                            try {
                                List<String> listStringToStringList = SavesDao_Impl.this.__podcastRoomTypeConverters.stringToStringList(string);
                                int i10 = columnIndexOrThrow16;
                                if (cursorQuery.isNull(i10)) {
                                    columnIndexOrThrow16 = i10;
                                    string2 = null;
                                } else {
                                    string2 = cursorQuery.getString(i10);
                                    columnIndexOrThrow16 = i10;
                                }
                                List<String> listStringToStringList2 = SavesDao_Impl.this.__podcastRoomTypeConverters.stringToStringList(string2);
                                int i11 = columnIndexOrThrow17;
                                if (cursorQuery.isNull(i11)) {
                                    i4 = columnIndexOrThrow18;
                                    string3 = null;
                                } else {
                                    string3 = cursorQuery.getString(i11);
                                    i4 = columnIndexOrThrow18;
                                }
                                if (cursorQuery.isNull(i4)) {
                                    columnIndexOrThrow17 = i11;
                                    i5 = columnIndexOrThrow19;
                                    string4 = null;
                                } else {
                                    string4 = cursorQuery.getString(i4);
                                    columnIndexOrThrow17 = i11;
                                    i5 = columnIndexOrThrow19;
                                }
                                if (cursorQuery.isNull(i5)) {
                                    columnIndexOrThrow19 = i5;
                                    string5 = null;
                                } else {
                                    columnIndexOrThrow19 = i5;
                                    string5 = cursorQuery.getString(i5);
                                }
                                arrayList.add(new Save(string6, string7, string8, string9, string10, string11, string12, string13, string14, string15, j, z2, j2, string16, listStringToStringList, listStringToStringList2, string3, string4, string5));
                                columnIndexOrThrow18 = i4;
                                columnIndexOrThrow = i9;
                                columnIndexOrThrow13 = i3;
                                columnIndexOrThrow15 = i;
                                i6 = i2;
                            } catch (Throwable th) {
                                th = th;
                                cursorQuery.close();
                                throw th;
                            }
                        }
                        cursorQuery.close();
                        return arrayList;
                    } catch (Throwable th2) {
                        th = th2;
                    }
                } catch (Throwable th3) {
                    th = th3;
                }
            }

            protected void finalize() {
                roomSQLiteQueryAcquire.release();
            }
        });
    }

    @Override // com.amazon.podcast.save.SavesDao
    public List<Save> getAll(boolean z) throws Throwable {
        RoomSQLiteQuery roomSQLiteQuery;
        int i;
        int i2;
        String string;
        int i3;
        String string2;
        String string3;
        int i4;
        String string4;
        int i5;
        String string5;
        RoomSQLiteQuery roomSQLiteQueryAcquire = RoomSQLiteQuery.acquire("SELECT * FROM saves WHERE saved = ? ORDER BY updatedTime DESC", 1);
        roomSQLiteQueryAcquire.bindLong(1, z ? 1L : 0L);
        this.__db.assertNotSuspendingTransaction();
        Cursor cursorQuery = DBUtil.query(this.__db, roomSQLiteQueryAcquire, false, null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(cursorQuery, "id");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "podcastId");
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "podcastTitle");
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "title");
            int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(cursorQuery, ContextMappingConstants.IMAGE);
            int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "podcastImage");
            int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "publishDate");
            int columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(cursorQuery, MediaTrack.ROLE_DESCRIPTION);
            int columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "authors");
            int columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "seasonNumber");
            int columnIndexOrThrow11 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "totalDurationMilliseconds");
            int columnIndexOrThrow12 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "saved");
            int columnIndexOrThrow13 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "updatedTime");
            roomSQLiteQuery = roomSQLiteQueryAcquire;
            try {
                int columnIndexOrThrow14 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "playbackMode");
                try {
                    int columnIndexOrThrow15 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "contentTraits");
                    int columnIndexOrThrow16 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "availableUpsells");
                    int columnIndexOrThrow17 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "availabilityDate");
                    int columnIndexOrThrow18 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "podcastShowVariantId");
                    int columnIndexOrThrow19 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "podcastEpisodeVariantId");
                    int i6 = columnIndexOrThrow14;
                    ArrayList arrayList = new ArrayList(cursorQuery.getCount());
                    while (cursorQuery.moveToNext()) {
                        String string6 = cursorQuery.isNull(columnIndexOrThrow) ? null : cursorQuery.getString(columnIndexOrThrow);
                        String string7 = cursorQuery.isNull(columnIndexOrThrow2) ? null : cursorQuery.getString(columnIndexOrThrow2);
                        String string8 = cursorQuery.isNull(columnIndexOrThrow3) ? null : cursorQuery.getString(columnIndexOrThrow3);
                        String string9 = cursorQuery.isNull(columnIndexOrThrow4) ? null : cursorQuery.getString(columnIndexOrThrow4);
                        String string10 = cursorQuery.isNull(columnIndexOrThrow5) ? null : cursorQuery.getString(columnIndexOrThrow5);
                        String string11 = cursorQuery.isNull(columnIndexOrThrow6) ? null : cursorQuery.getString(columnIndexOrThrow6);
                        String string12 = cursorQuery.isNull(columnIndexOrThrow7) ? null : cursorQuery.getString(columnIndexOrThrow7);
                        String string13 = cursorQuery.isNull(columnIndexOrThrow8) ? null : cursorQuery.getString(columnIndexOrThrow8);
                        String string14 = cursorQuery.isNull(columnIndexOrThrow9) ? null : cursorQuery.getString(columnIndexOrThrow9);
                        String string15 = cursorQuery.isNull(columnIndexOrThrow10) ? null : cursorQuery.getString(columnIndexOrThrow10);
                        long j = cursorQuery.getLong(columnIndexOrThrow11);
                        boolean z2 = cursorQuery.getInt(columnIndexOrThrow12) != 0;
                        long j2 = cursorQuery.getLong(columnIndexOrThrow13);
                        int i7 = i6;
                        String string16 = cursorQuery.isNull(i7) ? null : cursorQuery.getString(i7);
                        int i8 = columnIndexOrThrow15;
                        int i9 = columnIndexOrThrow;
                        if (cursorQuery.isNull(i8)) {
                            i = i8;
                            i3 = columnIndexOrThrow11;
                            i2 = i7;
                            string = null;
                        } else {
                            i = i8;
                            i2 = i7;
                            string = cursorQuery.getString(i8);
                            i3 = columnIndexOrThrow11;
                        }
                        try {
                            List<String> listStringToStringList = this.__podcastRoomTypeConverters.stringToStringList(string);
                            int i10 = columnIndexOrThrow16;
                            if (cursorQuery.isNull(i10)) {
                                columnIndexOrThrow16 = i10;
                                string2 = null;
                            } else {
                                string2 = cursorQuery.getString(i10);
                                columnIndexOrThrow16 = i10;
                            }
                            List<String> listStringToStringList2 = this.__podcastRoomTypeConverters.stringToStringList(string2);
                            int i11 = columnIndexOrThrow17;
                            if (cursorQuery.isNull(i11)) {
                                i4 = columnIndexOrThrow18;
                                string3 = null;
                            } else {
                                string3 = cursorQuery.getString(i11);
                                i4 = columnIndexOrThrow18;
                            }
                            if (cursorQuery.isNull(i4)) {
                                columnIndexOrThrow17 = i11;
                                i5 = columnIndexOrThrow19;
                                string4 = null;
                            } else {
                                string4 = cursorQuery.getString(i4);
                                columnIndexOrThrow17 = i11;
                                i5 = columnIndexOrThrow19;
                            }
                            if (cursorQuery.isNull(i5)) {
                                columnIndexOrThrow19 = i5;
                                string5 = null;
                            } else {
                                columnIndexOrThrow19 = i5;
                                string5 = cursorQuery.getString(i5);
                            }
                            arrayList.add(new Save(string6, string7, string8, string9, string10, string11, string12, string13, string14, string15, j, z2, j2, string16, listStringToStringList, listStringToStringList2, string3, string4, string5));
                            columnIndexOrThrow18 = i4;
                            columnIndexOrThrow = i9;
                            columnIndexOrThrow11 = i3;
                            columnIndexOrThrow15 = i;
                            i6 = i2;
                        } catch (Throwable th) {
                            th = th;
                            cursorQuery.close();
                            roomSQLiteQuery.release();
                            throw th;
                        }
                    }
                    cursorQuery.close();
                    roomSQLiteQuery.release();
                    return arrayList;
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (Throwable th3) {
                th = th3;
            }
        } catch (Throwable th4) {
            th = th4;
            roomSQLiteQuery = roomSQLiteQueryAcquire;
        }
    }

    public static List<Class<?>> getRequiredConverters() {
        return Collections.emptyList();
    }
}
