package com.amazon.podcast.downloads;

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
public final class DownloadsDao_Impl implements DownloadsDao {
    private final RoomDatabase __db;
    private final EntityInsertionAdapter<Download> __insertionAdapterOfDownload;
    private final PodcastRoomTypeConverters __podcastRoomTypeConverters = new PodcastRoomTypeConverters();
    private final SharedSQLiteStatement __preparedStmtOfDelete;
    private final SharedSQLiteStatement __preparedStmtOfDeleteAll;
    private final SharedSQLiteStatement __preparedStmtOfDeleteAllContainingState;

    public DownloadsDao_Impl(RoomDatabase __db) {
        this.__db = __db;
        this.__insertionAdapterOfDownload = new EntityInsertionAdapter<Download>(__db) { // from class: com.amazon.podcast.downloads.DownloadsDao_Impl.1
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "INSERT OR REPLACE INTO `downloads` (`id`,`podcastId`,`podcastTitle`,`title`,`image`,`podcastImage`,`publishDate`,`description`,`authors`,`seasonNumber`,`durationMilliseconds`,`explicit`,`state`,`localAudioUrl`,`updatedTime`,`playbackMode`,`contentTraits`,`availableUpsells`,`availabilityDate`,`podcastShowVariantId`,`podcastEpisodeVariantId`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            }

            @Override // androidx.room.EntityInsertionAdapter
            public void bind(SupportSQLiteStatement supportSQLiteStatement, Download download) {
                if (download.getId() == null) {
                    supportSQLiteStatement.bindNull(1);
                } else {
                    supportSQLiteStatement.bindString(1, download.getId());
                }
                if (download.getPodcastId() == null) {
                    supportSQLiteStatement.bindNull(2);
                } else {
                    supportSQLiteStatement.bindString(2, download.getPodcastId());
                }
                if (download.getPodcastTitle() == null) {
                    supportSQLiteStatement.bindNull(3);
                } else {
                    supportSQLiteStatement.bindString(3, download.getPodcastTitle());
                }
                if (download.getTitle() == null) {
                    supportSQLiteStatement.bindNull(4);
                } else {
                    supportSQLiteStatement.bindString(4, download.getTitle());
                }
                if (download.getImage() == null) {
                    supportSQLiteStatement.bindNull(5);
                } else {
                    supportSQLiteStatement.bindString(5, download.getImage());
                }
                if (download.getPodcastImage() == null) {
                    supportSQLiteStatement.bindNull(6);
                } else {
                    supportSQLiteStatement.bindString(6, download.getPodcastImage());
                }
                if (download.getPublishDate() == null) {
                    supportSQLiteStatement.bindNull(7);
                } else {
                    supportSQLiteStatement.bindString(7, download.getPublishDate());
                }
                if (download.getDescription() == null) {
                    supportSQLiteStatement.bindNull(8);
                } else {
                    supportSQLiteStatement.bindString(8, download.getDescription());
                }
                if (download.getAuthors() == null) {
                    supportSQLiteStatement.bindNull(9);
                } else {
                    supportSQLiteStatement.bindString(9, download.getAuthors());
                }
                if (download.getSeasonNumber() == null) {
                    supportSQLiteStatement.bindNull(10);
                } else {
                    supportSQLiteStatement.bindString(10, download.getSeasonNumber());
                }
                supportSQLiteStatement.bindLong(11, download.getDurationMilliseconds());
                supportSQLiteStatement.bindLong(12, download.isExplicit() ? 1L : 0L);
                if (download.getState() == null) {
                    supportSQLiteStatement.bindNull(13);
                } else {
                    supportSQLiteStatement.bindString(13, download.getState());
                }
                if (download.getLocalAudioUri() == null) {
                    supportSQLiteStatement.bindNull(14);
                } else {
                    supportSQLiteStatement.bindString(14, download.getLocalAudioUri());
                }
                supportSQLiteStatement.bindLong(15, download.getUpdatedTime());
                if (download.getPlaybackMode() == null) {
                    supportSQLiteStatement.bindNull(16);
                } else {
                    supportSQLiteStatement.bindString(16, download.getPlaybackMode());
                }
                String strStringListToString = DownloadsDao_Impl.this.__podcastRoomTypeConverters.stringListToString(download.getContentTraits());
                if (strStringListToString == null) {
                    supportSQLiteStatement.bindNull(17);
                } else {
                    supportSQLiteStatement.bindString(17, strStringListToString);
                }
                String strStringListToString2 = DownloadsDao_Impl.this.__podcastRoomTypeConverters.stringListToString(download.getAvailableUpsells());
                if (strStringListToString2 == null) {
                    supportSQLiteStatement.bindNull(18);
                } else {
                    supportSQLiteStatement.bindString(18, strStringListToString2);
                }
                if (download.getAvailabilityDate() == null) {
                    supportSQLiteStatement.bindNull(19);
                } else {
                    supportSQLiteStatement.bindString(19, download.getAvailabilityDate());
                }
                if (download.getPodcastShowVariantId() == null) {
                    supportSQLiteStatement.bindNull(20);
                } else {
                    supportSQLiteStatement.bindString(20, download.getPodcastShowVariantId());
                }
                if (download.getPodcastEpisodeVariantId() == null) {
                    supportSQLiteStatement.bindNull(21);
                } else {
                    supportSQLiteStatement.bindString(21, download.getPodcastEpisodeVariantId());
                }
            }
        };
        this.__preparedStmtOfDelete = new SharedSQLiteStatement(__db) { // from class: com.amazon.podcast.downloads.DownloadsDao_Impl.2
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "DELETE FROM downloads WHERE id = ?";
            }
        };
        this.__preparedStmtOfDeleteAllContainingState = new SharedSQLiteStatement(__db) { // from class: com.amazon.podcast.downloads.DownloadsDao_Impl.3
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "DELETE FROM downloads WHERE state = ?";
            }
        };
        this.__preparedStmtOfDeleteAll = new SharedSQLiteStatement(__db) { // from class: com.amazon.podcast.downloads.DownloadsDao_Impl.4
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "DELETE FROM downloads";
            }
        };
    }

    @Override // com.amazon.podcast.downloads.DownloadsDao
    public void insert(final Download download) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__insertionAdapterOfDownload.insert(download);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // com.amazon.podcast.downloads.DownloadsDao
    public void delete(final String id) {
        this.__db.assertNotSuspendingTransaction();
        SupportSQLiteStatement supportSQLiteStatementAcquire = this.__preparedStmtOfDelete.acquire();
        if (id == null) {
            supportSQLiteStatementAcquire.bindNull(1);
        } else {
            supportSQLiteStatementAcquire.bindString(1, id);
        }
        this.__db.beginTransaction();
        try {
            supportSQLiteStatementAcquire.executeUpdateDelete();
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
            this.__preparedStmtOfDelete.release(supportSQLiteStatementAcquire);
        }
    }

    @Override // com.amazon.podcast.downloads.DownloadsDao
    public void deleteAllContainingState(final String state) {
        this.__db.assertNotSuspendingTransaction();
        SupportSQLiteStatement supportSQLiteStatementAcquire = this.__preparedStmtOfDeleteAllContainingState.acquire();
        if (state == null) {
            supportSQLiteStatementAcquire.bindNull(1);
        } else {
            supportSQLiteStatementAcquire.bindString(1, state);
        }
        this.__db.beginTransaction();
        try {
            supportSQLiteStatementAcquire.executeUpdateDelete();
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
            this.__preparedStmtOfDeleteAllContainingState.release(supportSQLiteStatementAcquire);
        }
    }

    @Override // com.amazon.podcast.downloads.DownloadsDao
    public LiveData<Download> getLiveData(final String id) {
        final RoomSQLiteQuery roomSQLiteQueryAcquire = RoomSQLiteQuery.acquire("SELECT * FROM downloads WHERE id = ?", 1);
        if (id == null) {
            roomSQLiteQueryAcquire.bindNull(1);
        } else {
            roomSQLiteQueryAcquire.bindString(1, id);
        }
        return this.__db.getInvalidationTracker().createLiveData(new String[]{"downloads"}, false, new Callable<Download>() { // from class: com.amazon.podcast.downloads.DownloadsDao_Impl.5
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // java.util.concurrent.Callable
            public Download call() throws Exception {
                Download download;
                String string;
                int i;
                String string2;
                int i2;
                String string3;
                int i3;
                Cursor cursorQuery = DBUtil.query(DownloadsDao_Impl.this.__db, roomSQLiteQueryAcquire, false, null);
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
                    int columnIndexOrThrow11 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "durationMilliseconds");
                    int columnIndexOrThrow12 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "explicit");
                    int columnIndexOrThrow13 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "state");
                    int columnIndexOrThrow14 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "localAudioUrl");
                    try {
                        int columnIndexOrThrow15 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "updatedTime");
                        int columnIndexOrThrow16 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "playbackMode");
                        int columnIndexOrThrow17 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "contentTraits");
                        int columnIndexOrThrow18 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "availableUpsells");
                        int columnIndexOrThrow19 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "availabilityDate");
                        int columnIndexOrThrow20 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "podcastShowVariantId");
                        int columnIndexOrThrow21 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "podcastEpisodeVariantId");
                        if (cursorQuery.moveToFirst()) {
                            String string4 = cursorQuery.isNull(columnIndexOrThrow) ? null : cursorQuery.getString(columnIndexOrThrow);
                            String string5 = cursorQuery.isNull(columnIndexOrThrow2) ? null : cursorQuery.getString(columnIndexOrThrow2);
                            String string6 = cursorQuery.isNull(columnIndexOrThrow3) ? null : cursorQuery.getString(columnIndexOrThrow3);
                            String string7 = cursorQuery.isNull(columnIndexOrThrow4) ? null : cursorQuery.getString(columnIndexOrThrow4);
                            String string8 = cursorQuery.isNull(columnIndexOrThrow5) ? null : cursorQuery.getString(columnIndexOrThrow5);
                            String string9 = cursorQuery.isNull(columnIndexOrThrow6) ? null : cursorQuery.getString(columnIndexOrThrow6);
                            String string10 = cursorQuery.isNull(columnIndexOrThrow7) ? null : cursorQuery.getString(columnIndexOrThrow7);
                            String string11 = cursorQuery.isNull(columnIndexOrThrow8) ? null : cursorQuery.getString(columnIndexOrThrow8);
                            String string12 = cursorQuery.isNull(columnIndexOrThrow9) ? null : cursorQuery.getString(columnIndexOrThrow9);
                            String string13 = cursorQuery.isNull(columnIndexOrThrow10) ? null : cursorQuery.getString(columnIndexOrThrow10);
                            long j = cursorQuery.getLong(columnIndexOrThrow11);
                            boolean z = cursorQuery.getInt(columnIndexOrThrow12) != 0;
                            String string14 = cursorQuery.isNull(columnIndexOrThrow13) ? null : cursorQuery.getString(columnIndexOrThrow13);
                            if (cursorQuery.isNull(columnIndexOrThrow14)) {
                                i = columnIndexOrThrow15;
                                string = null;
                            } else {
                                string = cursorQuery.getString(columnIndexOrThrow14);
                                i = columnIndexOrThrow15;
                            }
                            long j2 = cursorQuery.getLong(i);
                            if (cursorQuery.isNull(columnIndexOrThrow16)) {
                                i2 = columnIndexOrThrow17;
                                string2 = null;
                            } else {
                                string2 = cursorQuery.getString(columnIndexOrThrow16);
                                i2 = columnIndexOrThrow17;
                            }
                            try {
                                List<String> listStringToStringList = DownloadsDao_Impl.this.__podcastRoomTypeConverters.stringToStringList(cursorQuery.isNull(i2) ? null : cursorQuery.getString(i2));
                                List<String> listStringToStringList2 = DownloadsDao_Impl.this.__podcastRoomTypeConverters.stringToStringList(cursorQuery.isNull(columnIndexOrThrow18) ? null : cursorQuery.getString(columnIndexOrThrow18));
                                if (cursorQuery.isNull(columnIndexOrThrow19)) {
                                    i3 = columnIndexOrThrow20;
                                    string3 = null;
                                } else {
                                    string3 = cursorQuery.getString(columnIndexOrThrow19);
                                    i3 = columnIndexOrThrow20;
                                }
                                download = new Download(string4, string5, string6, string7, string8, string9, string10, string11, string12, string13, j, z, string14, string, j2, string2, listStringToStringList, listStringToStringList2, string3, cursorQuery.isNull(i3) ? null : cursorQuery.getString(i3), cursorQuery.isNull(columnIndexOrThrow21) ? null : cursorQuery.getString(columnIndexOrThrow21));
                            } catch (Throwable th) {
                                th = th;
                                cursorQuery.close();
                                throw th;
                            }
                        } else {
                            download = null;
                        }
                        cursorQuery.close();
                        return download;
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

    @Override // com.amazon.podcast.downloads.DownloadsDao
    public Download get(final String id) throws Throwable {
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
        Download download;
        String string;
        int i;
        String string2;
        int i2;
        String string3;
        int i3;
        RoomSQLiteQuery roomSQLiteQueryAcquire = RoomSQLiteQuery.acquire("SELECT * FROM downloads WHERE id = ?", 1);
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
            columnIndexOrThrow11 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "durationMilliseconds");
            columnIndexOrThrow12 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "explicit");
            columnIndexOrThrow13 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "state");
            roomSQLiteQuery = roomSQLiteQueryAcquire;
        } catch (Throwable th) {
            th = th;
            roomSQLiteQuery = roomSQLiteQueryAcquire;
        }
        try {
            int columnIndexOrThrow14 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "localAudioUrl");
            try {
                int columnIndexOrThrow15 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "updatedTime");
                int columnIndexOrThrow16 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "playbackMode");
                int columnIndexOrThrow17 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "contentTraits");
                int columnIndexOrThrow18 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "availableUpsells");
                int columnIndexOrThrow19 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "availabilityDate");
                int columnIndexOrThrow20 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "podcastShowVariantId");
                int columnIndexOrThrow21 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "podcastEpisodeVariantId");
                if (cursorQuery.moveToFirst()) {
                    String string4 = cursorQuery.isNull(columnIndexOrThrow) ? null : cursorQuery.getString(columnIndexOrThrow);
                    String string5 = cursorQuery.isNull(columnIndexOrThrow2) ? null : cursorQuery.getString(columnIndexOrThrow2);
                    String string6 = cursorQuery.isNull(columnIndexOrThrow3) ? null : cursorQuery.getString(columnIndexOrThrow3);
                    String string7 = cursorQuery.isNull(columnIndexOrThrow4) ? null : cursorQuery.getString(columnIndexOrThrow4);
                    String string8 = cursorQuery.isNull(columnIndexOrThrow5) ? null : cursorQuery.getString(columnIndexOrThrow5);
                    String string9 = cursorQuery.isNull(columnIndexOrThrow6) ? null : cursorQuery.getString(columnIndexOrThrow6);
                    String string10 = cursorQuery.isNull(columnIndexOrThrow7) ? null : cursorQuery.getString(columnIndexOrThrow7);
                    String string11 = cursorQuery.isNull(columnIndexOrThrow8) ? null : cursorQuery.getString(columnIndexOrThrow8);
                    String string12 = cursorQuery.isNull(columnIndexOrThrow9) ? null : cursorQuery.getString(columnIndexOrThrow9);
                    String string13 = cursorQuery.isNull(columnIndexOrThrow10) ? null : cursorQuery.getString(columnIndexOrThrow10);
                    long j = cursorQuery.getLong(columnIndexOrThrow11);
                    boolean z = cursorQuery.getInt(columnIndexOrThrow12) != 0;
                    String string14 = cursorQuery.isNull(columnIndexOrThrow13) ? null : cursorQuery.getString(columnIndexOrThrow13);
                    if (cursorQuery.isNull(columnIndexOrThrow14)) {
                        i = columnIndexOrThrow15;
                        string = null;
                    } else {
                        string = cursorQuery.getString(columnIndexOrThrow14);
                        i = columnIndexOrThrow15;
                    }
                    long j2 = cursorQuery.getLong(i);
                    if (cursorQuery.isNull(columnIndexOrThrow16)) {
                        i2 = columnIndexOrThrow17;
                        string2 = null;
                    } else {
                        string2 = cursorQuery.getString(columnIndexOrThrow16);
                        i2 = columnIndexOrThrow17;
                    }
                    try {
                        List<String> listStringToStringList = this.__podcastRoomTypeConverters.stringToStringList(cursorQuery.isNull(i2) ? null : cursorQuery.getString(i2));
                        List<String> listStringToStringList2 = this.__podcastRoomTypeConverters.stringToStringList(cursorQuery.isNull(columnIndexOrThrow18) ? null : cursorQuery.getString(columnIndexOrThrow18));
                        if (cursorQuery.isNull(columnIndexOrThrow19)) {
                            i3 = columnIndexOrThrow20;
                            string3 = null;
                        } else {
                            string3 = cursorQuery.getString(columnIndexOrThrow19);
                            i3 = columnIndexOrThrow20;
                        }
                        download = new Download(string4, string5, string6, string7, string8, string9, string10, string11, string12, string13, j, z, string14, string, j2, string2, listStringToStringList, listStringToStringList2, string3, cursorQuery.isNull(i3) ? null : cursorQuery.getString(i3), cursorQuery.isNull(columnIndexOrThrow21) ? null : cursorQuery.getString(columnIndexOrThrow21));
                    } catch (Throwable th2) {
                        th = th2;
                        cursorQuery.close();
                        roomSQLiteQuery.release();
                        throw th;
                    }
                } else {
                    download = null;
                }
                cursorQuery.close();
                roomSQLiteQuery.release();
                return download;
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

    @Override // com.amazon.podcast.downloads.DownloadsDao
    public Download getNext(final long updateTime) throws Throwable {
        RoomSQLiteQuery roomSQLiteQuery;
        Download download;
        String string;
        int i;
        String string2;
        int i2;
        String string3;
        int i3;
        RoomSQLiteQuery roomSQLiteQueryAcquire = RoomSQLiteQuery.acquire("SELECT * FROM downloads WHERE updatedTime < ? ORDER BY updatedTime DESC LIMIT 1", 1);
        roomSQLiteQueryAcquire.bindLong(1, updateTime);
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
            int columnIndexOrThrow11 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "durationMilliseconds");
            int columnIndexOrThrow12 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "explicit");
            int columnIndexOrThrow13 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "state");
            roomSQLiteQuery = roomSQLiteQueryAcquire;
            try {
                int columnIndexOrThrow14 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "localAudioUrl");
                try {
                    int columnIndexOrThrow15 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "updatedTime");
                    int columnIndexOrThrow16 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "playbackMode");
                    int columnIndexOrThrow17 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "contentTraits");
                    int columnIndexOrThrow18 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "availableUpsells");
                    int columnIndexOrThrow19 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "availabilityDate");
                    int columnIndexOrThrow20 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "podcastShowVariantId");
                    int columnIndexOrThrow21 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "podcastEpisodeVariantId");
                    if (cursorQuery.moveToFirst()) {
                        String string4 = cursorQuery.isNull(columnIndexOrThrow) ? null : cursorQuery.getString(columnIndexOrThrow);
                        String string5 = cursorQuery.isNull(columnIndexOrThrow2) ? null : cursorQuery.getString(columnIndexOrThrow2);
                        String string6 = cursorQuery.isNull(columnIndexOrThrow3) ? null : cursorQuery.getString(columnIndexOrThrow3);
                        String string7 = cursorQuery.isNull(columnIndexOrThrow4) ? null : cursorQuery.getString(columnIndexOrThrow4);
                        String string8 = cursorQuery.isNull(columnIndexOrThrow5) ? null : cursorQuery.getString(columnIndexOrThrow5);
                        String string9 = cursorQuery.isNull(columnIndexOrThrow6) ? null : cursorQuery.getString(columnIndexOrThrow6);
                        String string10 = cursorQuery.isNull(columnIndexOrThrow7) ? null : cursorQuery.getString(columnIndexOrThrow7);
                        String string11 = cursorQuery.isNull(columnIndexOrThrow8) ? null : cursorQuery.getString(columnIndexOrThrow8);
                        String string12 = cursorQuery.isNull(columnIndexOrThrow9) ? null : cursorQuery.getString(columnIndexOrThrow9);
                        String string13 = cursorQuery.isNull(columnIndexOrThrow10) ? null : cursorQuery.getString(columnIndexOrThrow10);
                        long j = cursorQuery.getLong(columnIndexOrThrow11);
                        boolean z = cursorQuery.getInt(columnIndexOrThrow12) != 0;
                        String string14 = cursorQuery.isNull(columnIndexOrThrow13) ? null : cursorQuery.getString(columnIndexOrThrow13);
                        if (cursorQuery.isNull(columnIndexOrThrow14)) {
                            i = columnIndexOrThrow15;
                            string = null;
                        } else {
                            string = cursorQuery.getString(columnIndexOrThrow14);
                            i = columnIndexOrThrow15;
                        }
                        long j2 = cursorQuery.getLong(i);
                        if (cursorQuery.isNull(columnIndexOrThrow16)) {
                            i2 = columnIndexOrThrow17;
                            string2 = null;
                        } else {
                            string2 = cursorQuery.getString(columnIndexOrThrow16);
                            i2 = columnIndexOrThrow17;
                        }
                        try {
                            List<String> listStringToStringList = this.__podcastRoomTypeConverters.stringToStringList(cursorQuery.isNull(i2) ? null : cursorQuery.getString(i2));
                            List<String> listStringToStringList2 = this.__podcastRoomTypeConverters.stringToStringList(cursorQuery.isNull(columnIndexOrThrow18) ? null : cursorQuery.getString(columnIndexOrThrow18));
                            if (cursorQuery.isNull(columnIndexOrThrow19)) {
                                i3 = columnIndexOrThrow20;
                                string3 = null;
                            } else {
                                string3 = cursorQuery.getString(columnIndexOrThrow19);
                                i3 = columnIndexOrThrow20;
                            }
                            download = new Download(string4, string5, string6, string7, string8, string9, string10, string11, string12, string13, j, z, string14, string, j2, string2, listStringToStringList, listStringToStringList2, string3, cursorQuery.isNull(i3) ? null : cursorQuery.getString(i3), cursorQuery.isNull(columnIndexOrThrow21) ? null : cursorQuery.getString(columnIndexOrThrow21));
                        } catch (Throwable th) {
                            th = th;
                            cursorQuery.close();
                            roomSQLiteQuery.release();
                            throw th;
                        }
                    } else {
                        download = null;
                    }
                    cursorQuery.close();
                    roomSQLiteQuery.release();
                    return download;
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

    @Override // com.amazon.podcast.downloads.DownloadsDao
    public Download getPrevious(final long updateTime) throws Throwable {
        RoomSQLiteQuery roomSQLiteQuery;
        Download download;
        String string;
        int i;
        String string2;
        int i2;
        String string3;
        int i3;
        RoomSQLiteQuery roomSQLiteQueryAcquire = RoomSQLiteQuery.acquire("SELECT * FROM downloads WHERE updatedTime > ? ORDER BY updatedTime ASC LIMIT 1", 1);
        roomSQLiteQueryAcquire.bindLong(1, updateTime);
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
            int columnIndexOrThrow11 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "durationMilliseconds");
            int columnIndexOrThrow12 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "explicit");
            int columnIndexOrThrow13 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "state");
            roomSQLiteQuery = roomSQLiteQueryAcquire;
            try {
                int columnIndexOrThrow14 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "localAudioUrl");
                try {
                    int columnIndexOrThrow15 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "updatedTime");
                    int columnIndexOrThrow16 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "playbackMode");
                    int columnIndexOrThrow17 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "contentTraits");
                    int columnIndexOrThrow18 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "availableUpsells");
                    int columnIndexOrThrow19 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "availabilityDate");
                    int columnIndexOrThrow20 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "podcastShowVariantId");
                    int columnIndexOrThrow21 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "podcastEpisodeVariantId");
                    if (cursorQuery.moveToFirst()) {
                        String string4 = cursorQuery.isNull(columnIndexOrThrow) ? null : cursorQuery.getString(columnIndexOrThrow);
                        String string5 = cursorQuery.isNull(columnIndexOrThrow2) ? null : cursorQuery.getString(columnIndexOrThrow2);
                        String string6 = cursorQuery.isNull(columnIndexOrThrow3) ? null : cursorQuery.getString(columnIndexOrThrow3);
                        String string7 = cursorQuery.isNull(columnIndexOrThrow4) ? null : cursorQuery.getString(columnIndexOrThrow4);
                        String string8 = cursorQuery.isNull(columnIndexOrThrow5) ? null : cursorQuery.getString(columnIndexOrThrow5);
                        String string9 = cursorQuery.isNull(columnIndexOrThrow6) ? null : cursorQuery.getString(columnIndexOrThrow6);
                        String string10 = cursorQuery.isNull(columnIndexOrThrow7) ? null : cursorQuery.getString(columnIndexOrThrow7);
                        String string11 = cursorQuery.isNull(columnIndexOrThrow8) ? null : cursorQuery.getString(columnIndexOrThrow8);
                        String string12 = cursorQuery.isNull(columnIndexOrThrow9) ? null : cursorQuery.getString(columnIndexOrThrow9);
                        String string13 = cursorQuery.isNull(columnIndexOrThrow10) ? null : cursorQuery.getString(columnIndexOrThrow10);
                        long j = cursorQuery.getLong(columnIndexOrThrow11);
                        boolean z = cursorQuery.getInt(columnIndexOrThrow12) != 0;
                        String string14 = cursorQuery.isNull(columnIndexOrThrow13) ? null : cursorQuery.getString(columnIndexOrThrow13);
                        if (cursorQuery.isNull(columnIndexOrThrow14)) {
                            i = columnIndexOrThrow15;
                            string = null;
                        } else {
                            string = cursorQuery.getString(columnIndexOrThrow14);
                            i = columnIndexOrThrow15;
                        }
                        long j2 = cursorQuery.getLong(i);
                        if (cursorQuery.isNull(columnIndexOrThrow16)) {
                            i2 = columnIndexOrThrow17;
                            string2 = null;
                        } else {
                            string2 = cursorQuery.getString(columnIndexOrThrow16);
                            i2 = columnIndexOrThrow17;
                        }
                        try {
                            List<String> listStringToStringList = this.__podcastRoomTypeConverters.stringToStringList(cursorQuery.isNull(i2) ? null : cursorQuery.getString(i2));
                            List<String> listStringToStringList2 = this.__podcastRoomTypeConverters.stringToStringList(cursorQuery.isNull(columnIndexOrThrow18) ? null : cursorQuery.getString(columnIndexOrThrow18));
                            if (cursorQuery.isNull(columnIndexOrThrow19)) {
                                i3 = columnIndexOrThrow20;
                                string3 = null;
                            } else {
                                string3 = cursorQuery.getString(columnIndexOrThrow19);
                                i3 = columnIndexOrThrow20;
                            }
                            download = new Download(string4, string5, string6, string7, string8, string9, string10, string11, string12, string13, j, z, string14, string, j2, string2, listStringToStringList, listStringToStringList2, string3, cursorQuery.isNull(i3) ? null : cursorQuery.getString(i3), cursorQuery.isNull(columnIndexOrThrow21) ? null : cursorQuery.getString(columnIndexOrThrow21));
                        } catch (Throwable th) {
                            th = th;
                            cursorQuery.close();
                            roomSQLiteQuery.release();
                            throw th;
                        }
                    } else {
                        download = null;
                    }
                    cursorQuery.close();
                    roomSQLiteQuery.release();
                    return download;
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

    @Override // com.amazon.podcast.downloads.DownloadsDao
    public List<Download> getAll() throws Throwable {
        RoomSQLiteQuery roomSQLiteQuery;
        String string;
        int i;
        String string2;
        int i2;
        int i3;
        int i4;
        String string3;
        int i5;
        String string4;
        String string5;
        int i6;
        String string6;
        int i7;
        String string7;
        RoomSQLiteQuery roomSQLiteQueryAcquire = RoomSQLiteQuery.acquire("SELECT * FROM downloads ORDER BY updatedTime DESC", 0);
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
            int columnIndexOrThrow11 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "durationMilliseconds");
            int columnIndexOrThrow12 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "explicit");
            int columnIndexOrThrow13 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "state");
            roomSQLiteQuery = roomSQLiteQueryAcquire;
            try {
                int columnIndexOrThrow14 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "localAudioUrl");
                try {
                    int columnIndexOrThrow15 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "updatedTime");
                    int columnIndexOrThrow16 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "playbackMode");
                    int columnIndexOrThrow17 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "contentTraits");
                    int columnIndexOrThrow18 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "availableUpsells");
                    int columnIndexOrThrow19 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "availabilityDate");
                    int columnIndexOrThrow20 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "podcastShowVariantId");
                    int columnIndexOrThrow21 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "podcastEpisodeVariantId");
                    int i8 = columnIndexOrThrow14;
                    ArrayList arrayList = new ArrayList(cursorQuery.getCount());
                    while (cursorQuery.moveToNext()) {
                        String string8 = cursorQuery.isNull(columnIndexOrThrow) ? null : cursorQuery.getString(columnIndexOrThrow);
                        String string9 = cursorQuery.isNull(columnIndexOrThrow2) ? null : cursorQuery.getString(columnIndexOrThrow2);
                        String string10 = cursorQuery.isNull(columnIndexOrThrow3) ? null : cursorQuery.getString(columnIndexOrThrow3);
                        String string11 = cursorQuery.isNull(columnIndexOrThrow4) ? null : cursorQuery.getString(columnIndexOrThrow4);
                        String string12 = cursorQuery.isNull(columnIndexOrThrow5) ? null : cursorQuery.getString(columnIndexOrThrow5);
                        String string13 = cursorQuery.isNull(columnIndexOrThrow6) ? null : cursorQuery.getString(columnIndexOrThrow6);
                        String string14 = cursorQuery.isNull(columnIndexOrThrow7) ? null : cursorQuery.getString(columnIndexOrThrow7);
                        String string15 = cursorQuery.isNull(columnIndexOrThrow8) ? null : cursorQuery.getString(columnIndexOrThrow8);
                        String string16 = cursorQuery.isNull(columnIndexOrThrow9) ? null : cursorQuery.getString(columnIndexOrThrow9);
                        String string17 = cursorQuery.isNull(columnIndexOrThrow10) ? null : cursorQuery.getString(columnIndexOrThrow10);
                        long j = cursorQuery.getLong(columnIndexOrThrow11);
                        boolean z = cursorQuery.getInt(columnIndexOrThrow12) != 0;
                        if (cursorQuery.isNull(columnIndexOrThrow13)) {
                            i = i8;
                            string = null;
                        } else {
                            string = cursorQuery.getString(columnIndexOrThrow13);
                            i = i8;
                        }
                        String string18 = cursorQuery.isNull(i) ? null : cursorQuery.getString(i);
                        int i9 = columnIndexOrThrow15;
                        int i10 = columnIndexOrThrow;
                        long j2 = cursorQuery.getLong(i9);
                        int i11 = columnIndexOrThrow16;
                        if (cursorQuery.isNull(i11)) {
                            columnIndexOrThrow16 = i11;
                            i2 = columnIndexOrThrow17;
                            string2 = null;
                        } else {
                            string2 = cursorQuery.getString(i11);
                            columnIndexOrThrow16 = i11;
                            i2 = columnIndexOrThrow17;
                        }
                        if (cursorQuery.isNull(i2)) {
                            i3 = i2;
                            i5 = columnIndexOrThrow12;
                            i4 = i;
                            string3 = null;
                        } else {
                            i3 = i2;
                            i4 = i;
                            string3 = cursorQuery.getString(i2);
                            i5 = columnIndexOrThrow12;
                        }
                        try {
                            List<String> listStringToStringList = this.__podcastRoomTypeConverters.stringToStringList(string3);
                            int i12 = columnIndexOrThrow18;
                            if (cursorQuery.isNull(i12)) {
                                columnIndexOrThrow18 = i12;
                                string4 = null;
                            } else {
                                string4 = cursorQuery.getString(i12);
                                columnIndexOrThrow18 = i12;
                            }
                            List<String> listStringToStringList2 = this.__podcastRoomTypeConverters.stringToStringList(string4);
                            int i13 = columnIndexOrThrow19;
                            if (cursorQuery.isNull(i13)) {
                                i6 = columnIndexOrThrow20;
                                string5 = null;
                            } else {
                                string5 = cursorQuery.getString(i13);
                                i6 = columnIndexOrThrow20;
                            }
                            if (cursorQuery.isNull(i6)) {
                                columnIndexOrThrow19 = i13;
                                i7 = columnIndexOrThrow21;
                                string6 = null;
                            } else {
                                string6 = cursorQuery.getString(i6);
                                columnIndexOrThrow19 = i13;
                                i7 = columnIndexOrThrow21;
                            }
                            if (cursorQuery.isNull(i7)) {
                                columnIndexOrThrow21 = i7;
                                string7 = null;
                            } else {
                                columnIndexOrThrow21 = i7;
                                string7 = cursorQuery.getString(i7);
                            }
                            arrayList.add(new Download(string8, string9, string10, string11, string12, string13, string14, string15, string16, string17, j, z, string, string18, j2, string2, listStringToStringList, listStringToStringList2, string5, string6, string7));
                            columnIndexOrThrow20 = i6;
                            columnIndexOrThrow = i10;
                            columnIndexOrThrow12 = i5;
                            columnIndexOrThrow15 = i9;
                            columnIndexOrThrow17 = i3;
                            i8 = i4;
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

    @Override // com.amazon.podcast.downloads.DownloadsDao
    public List<Download> getAll(final String podcastId) throws Throwable {
        RoomSQLiteQuery roomSQLiteQuery;
        String string;
        int i;
        String string2;
        int i2;
        int i3;
        int i4;
        String string3;
        int i5;
        String string4;
        String string5;
        int i6;
        String string6;
        int i7;
        String string7;
        RoomSQLiteQuery roomSQLiteQueryAcquire = RoomSQLiteQuery.acquire("SELECT * FROM downloads WHERE podcastId = ? ORDER BY updatedTime DESC", 1);
        if (podcastId == null) {
            roomSQLiteQueryAcquire.bindNull(1);
        } else {
            roomSQLiteQueryAcquire.bindString(1, podcastId);
        }
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
            int columnIndexOrThrow11 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "durationMilliseconds");
            int columnIndexOrThrow12 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "explicit");
            int columnIndexOrThrow13 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "state");
            roomSQLiteQuery = roomSQLiteQueryAcquire;
            try {
                int columnIndexOrThrow14 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "localAudioUrl");
                try {
                    int columnIndexOrThrow15 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "updatedTime");
                    int columnIndexOrThrow16 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "playbackMode");
                    int columnIndexOrThrow17 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "contentTraits");
                    int columnIndexOrThrow18 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "availableUpsells");
                    int columnIndexOrThrow19 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "availabilityDate");
                    int columnIndexOrThrow20 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "podcastShowVariantId");
                    int columnIndexOrThrow21 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "podcastEpisodeVariantId");
                    int i8 = columnIndexOrThrow14;
                    ArrayList arrayList = new ArrayList(cursorQuery.getCount());
                    while (cursorQuery.moveToNext()) {
                        String string8 = cursorQuery.isNull(columnIndexOrThrow) ? null : cursorQuery.getString(columnIndexOrThrow);
                        String string9 = cursorQuery.isNull(columnIndexOrThrow2) ? null : cursorQuery.getString(columnIndexOrThrow2);
                        String string10 = cursorQuery.isNull(columnIndexOrThrow3) ? null : cursorQuery.getString(columnIndexOrThrow3);
                        String string11 = cursorQuery.isNull(columnIndexOrThrow4) ? null : cursorQuery.getString(columnIndexOrThrow4);
                        String string12 = cursorQuery.isNull(columnIndexOrThrow5) ? null : cursorQuery.getString(columnIndexOrThrow5);
                        String string13 = cursorQuery.isNull(columnIndexOrThrow6) ? null : cursorQuery.getString(columnIndexOrThrow6);
                        String string14 = cursorQuery.isNull(columnIndexOrThrow7) ? null : cursorQuery.getString(columnIndexOrThrow7);
                        String string15 = cursorQuery.isNull(columnIndexOrThrow8) ? null : cursorQuery.getString(columnIndexOrThrow8);
                        String string16 = cursorQuery.isNull(columnIndexOrThrow9) ? null : cursorQuery.getString(columnIndexOrThrow9);
                        String string17 = cursorQuery.isNull(columnIndexOrThrow10) ? null : cursorQuery.getString(columnIndexOrThrow10);
                        long j = cursorQuery.getLong(columnIndexOrThrow11);
                        boolean z = cursorQuery.getInt(columnIndexOrThrow12) != 0;
                        if (cursorQuery.isNull(columnIndexOrThrow13)) {
                            i = i8;
                            string = null;
                        } else {
                            string = cursorQuery.getString(columnIndexOrThrow13);
                            i = i8;
                        }
                        String string18 = cursorQuery.isNull(i) ? null : cursorQuery.getString(i);
                        int i9 = columnIndexOrThrow15;
                        int i10 = columnIndexOrThrow;
                        long j2 = cursorQuery.getLong(i9);
                        int i11 = columnIndexOrThrow16;
                        if (cursorQuery.isNull(i11)) {
                            columnIndexOrThrow16 = i11;
                            i2 = columnIndexOrThrow17;
                            string2 = null;
                        } else {
                            string2 = cursorQuery.getString(i11);
                            columnIndexOrThrow16 = i11;
                            i2 = columnIndexOrThrow17;
                        }
                        if (cursorQuery.isNull(i2)) {
                            i3 = i2;
                            i5 = i;
                            i4 = columnIndexOrThrow11;
                            string3 = null;
                        } else {
                            i3 = i2;
                            i4 = columnIndexOrThrow11;
                            string3 = cursorQuery.getString(i2);
                            i5 = i;
                        }
                        try {
                            List<String> listStringToStringList = this.__podcastRoomTypeConverters.stringToStringList(string3);
                            int i12 = columnIndexOrThrow18;
                            if (cursorQuery.isNull(i12)) {
                                columnIndexOrThrow18 = i12;
                                string4 = null;
                            } else {
                                string4 = cursorQuery.getString(i12);
                                columnIndexOrThrow18 = i12;
                            }
                            List<String> listStringToStringList2 = this.__podcastRoomTypeConverters.stringToStringList(string4);
                            int i13 = columnIndexOrThrow19;
                            if (cursorQuery.isNull(i13)) {
                                i6 = columnIndexOrThrow20;
                                string5 = null;
                            } else {
                                string5 = cursorQuery.getString(i13);
                                i6 = columnIndexOrThrow20;
                            }
                            if (cursorQuery.isNull(i6)) {
                                columnIndexOrThrow19 = i13;
                                i7 = columnIndexOrThrow21;
                                string6 = null;
                            } else {
                                string6 = cursorQuery.getString(i6);
                                columnIndexOrThrow19 = i13;
                                i7 = columnIndexOrThrow21;
                            }
                            if (cursorQuery.isNull(i7)) {
                                columnIndexOrThrow21 = i7;
                                string7 = null;
                            } else {
                                columnIndexOrThrow21 = i7;
                                string7 = cursorQuery.getString(i7);
                            }
                            arrayList.add(new Download(string8, string9, string10, string11, string12, string13, string14, string15, string16, string17, j, z, string, string18, j2, string2, listStringToStringList, listStringToStringList2, string5, string6, string7));
                            columnIndexOrThrow20 = i6;
                            columnIndexOrThrow = i10;
                            columnIndexOrThrow15 = i9;
                            columnIndexOrThrow11 = i4;
                            i8 = i5;
                            columnIndexOrThrow17 = i3;
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
                cursorQuery.close();
                roomSQLiteQuery.release();
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            roomSQLiteQuery = roomSQLiteQueryAcquire;
        }
    }

    @Override // com.amazon.podcast.downloads.DownloadsDao
    public List<Download> getAll(final String podcastId, final String seasonNumber) throws Throwable {
        RoomSQLiteQuery roomSQLiteQuery;
        String string;
        int i;
        String string2;
        int i2;
        int i3;
        int i4;
        String string3;
        int i5;
        String string4;
        String string5;
        int i6;
        String string6;
        int i7;
        String string7;
        RoomSQLiteQuery roomSQLiteQueryAcquire = RoomSQLiteQuery.acquire("SELECT * FROM downloads WHERE podcastId = ? AND seasonNumber = ? ORDER BY updatedTime DESC", 2);
        if (podcastId == null) {
            roomSQLiteQueryAcquire.bindNull(1);
        } else {
            roomSQLiteQueryAcquire.bindString(1, podcastId);
        }
        if (seasonNumber == null) {
            roomSQLiteQueryAcquire.bindNull(2);
        } else {
            roomSQLiteQueryAcquire.bindString(2, seasonNumber);
        }
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
            int columnIndexOrThrow11 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "durationMilliseconds");
            int columnIndexOrThrow12 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "explicit");
            int columnIndexOrThrow13 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "state");
            roomSQLiteQuery = roomSQLiteQueryAcquire;
            try {
                int columnIndexOrThrow14 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "localAudioUrl");
                try {
                    int columnIndexOrThrow15 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "updatedTime");
                    int columnIndexOrThrow16 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "playbackMode");
                    int columnIndexOrThrow17 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "contentTraits");
                    int columnIndexOrThrow18 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "availableUpsells");
                    int columnIndexOrThrow19 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "availabilityDate");
                    int columnIndexOrThrow20 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "podcastShowVariantId");
                    int columnIndexOrThrow21 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "podcastEpisodeVariantId");
                    int i8 = columnIndexOrThrow14;
                    ArrayList arrayList = new ArrayList(cursorQuery.getCount());
                    while (cursorQuery.moveToNext()) {
                        String string8 = cursorQuery.isNull(columnIndexOrThrow) ? null : cursorQuery.getString(columnIndexOrThrow);
                        String string9 = cursorQuery.isNull(columnIndexOrThrow2) ? null : cursorQuery.getString(columnIndexOrThrow2);
                        String string10 = cursorQuery.isNull(columnIndexOrThrow3) ? null : cursorQuery.getString(columnIndexOrThrow3);
                        String string11 = cursorQuery.isNull(columnIndexOrThrow4) ? null : cursorQuery.getString(columnIndexOrThrow4);
                        String string12 = cursorQuery.isNull(columnIndexOrThrow5) ? null : cursorQuery.getString(columnIndexOrThrow5);
                        String string13 = cursorQuery.isNull(columnIndexOrThrow6) ? null : cursorQuery.getString(columnIndexOrThrow6);
                        String string14 = cursorQuery.isNull(columnIndexOrThrow7) ? null : cursorQuery.getString(columnIndexOrThrow7);
                        String string15 = cursorQuery.isNull(columnIndexOrThrow8) ? null : cursorQuery.getString(columnIndexOrThrow8);
                        String string16 = cursorQuery.isNull(columnIndexOrThrow9) ? null : cursorQuery.getString(columnIndexOrThrow9);
                        String string17 = cursorQuery.isNull(columnIndexOrThrow10) ? null : cursorQuery.getString(columnIndexOrThrow10);
                        long j = cursorQuery.getLong(columnIndexOrThrow11);
                        boolean z = cursorQuery.getInt(columnIndexOrThrow12) != 0;
                        if (cursorQuery.isNull(columnIndexOrThrow13)) {
                            i = i8;
                            string = null;
                        } else {
                            string = cursorQuery.getString(columnIndexOrThrow13);
                            i = i8;
                        }
                        String string18 = cursorQuery.isNull(i) ? null : cursorQuery.getString(i);
                        int i9 = columnIndexOrThrow15;
                        int i10 = columnIndexOrThrow;
                        long j2 = cursorQuery.getLong(i9);
                        int i11 = columnIndexOrThrow16;
                        if (cursorQuery.isNull(i11)) {
                            columnIndexOrThrow16 = i11;
                            i2 = columnIndexOrThrow17;
                            string2 = null;
                        } else {
                            string2 = cursorQuery.getString(i11);
                            columnIndexOrThrow16 = i11;
                            i2 = columnIndexOrThrow17;
                        }
                        if (cursorQuery.isNull(i2)) {
                            i3 = i2;
                            i5 = columnIndexOrThrow11;
                            i4 = i;
                            string3 = null;
                        } else {
                            i3 = i2;
                            i4 = i;
                            string3 = cursorQuery.getString(i2);
                            i5 = columnIndexOrThrow11;
                        }
                        try {
                            List<String> listStringToStringList = this.__podcastRoomTypeConverters.stringToStringList(string3);
                            int i12 = columnIndexOrThrow18;
                            if (cursorQuery.isNull(i12)) {
                                columnIndexOrThrow18 = i12;
                                string4 = null;
                            } else {
                                string4 = cursorQuery.getString(i12);
                                columnIndexOrThrow18 = i12;
                            }
                            List<String> listStringToStringList2 = this.__podcastRoomTypeConverters.stringToStringList(string4);
                            int i13 = columnIndexOrThrow19;
                            if (cursorQuery.isNull(i13)) {
                                i6 = columnIndexOrThrow20;
                                string5 = null;
                            } else {
                                string5 = cursorQuery.getString(i13);
                                i6 = columnIndexOrThrow20;
                            }
                            if (cursorQuery.isNull(i6)) {
                                columnIndexOrThrow19 = i13;
                                i7 = columnIndexOrThrow21;
                                string6 = null;
                            } else {
                                string6 = cursorQuery.getString(i6);
                                columnIndexOrThrow19 = i13;
                                i7 = columnIndexOrThrow21;
                            }
                            if (cursorQuery.isNull(i7)) {
                                columnIndexOrThrow21 = i7;
                                string7 = null;
                            } else {
                                columnIndexOrThrow21 = i7;
                                string7 = cursorQuery.getString(i7);
                            }
                            arrayList.add(new Download(string8, string9, string10, string11, string12, string13, string14, string15, string16, string17, j, z, string, string18, j2, string2, listStringToStringList, listStringToStringList2, string5, string6, string7));
                            columnIndexOrThrow20 = i6;
                            columnIndexOrThrow = i10;
                            columnIndexOrThrow11 = i5;
                            columnIndexOrThrow15 = i9;
                            columnIndexOrThrow17 = i3;
                            i8 = i4;
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

    @Override // com.amazon.podcast.downloads.DownloadsDao
    public LiveData<List<Download>> getAllLiveData() {
        final RoomSQLiteQuery roomSQLiteQueryAcquire = RoomSQLiteQuery.acquire("SELECT * FROM downloads ORDER BY updatedTime DESC", 0);
        return this.__db.getInvalidationTracker().createLiveData(new String[]{"downloads"}, false, new Callable<List<Download>>() { // from class: com.amazon.podcast.downloads.DownloadsDao_Impl.6
            @Override // java.util.concurrent.Callable
            public List<Download> call() throws Exception {
                String string;
                int i;
                String string2;
                int i2;
                int i3;
                int i4;
                String string3;
                int i5;
                String string4;
                String string5;
                int i6;
                String string6;
                int i7;
                String string7;
                Cursor cursorQuery = DBUtil.query(DownloadsDao_Impl.this.__db, roomSQLiteQueryAcquire, false, null);
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
                    int columnIndexOrThrow11 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "durationMilliseconds");
                    int columnIndexOrThrow12 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "explicit");
                    int columnIndexOrThrow13 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "state");
                    int columnIndexOrThrow14 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "localAudioUrl");
                    try {
                        int columnIndexOrThrow15 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "updatedTime");
                        int columnIndexOrThrow16 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "playbackMode");
                        int columnIndexOrThrow17 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "contentTraits");
                        int columnIndexOrThrow18 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "availableUpsells");
                        int columnIndexOrThrow19 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "availabilityDate");
                        int columnIndexOrThrow20 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "podcastShowVariantId");
                        int columnIndexOrThrow21 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "podcastEpisodeVariantId");
                        int i8 = columnIndexOrThrow14;
                        ArrayList arrayList = new ArrayList(cursorQuery.getCount());
                        while (cursorQuery.moveToNext()) {
                            String string8 = cursorQuery.isNull(columnIndexOrThrow) ? null : cursorQuery.getString(columnIndexOrThrow);
                            String string9 = cursorQuery.isNull(columnIndexOrThrow2) ? null : cursorQuery.getString(columnIndexOrThrow2);
                            String string10 = cursorQuery.isNull(columnIndexOrThrow3) ? null : cursorQuery.getString(columnIndexOrThrow3);
                            String string11 = cursorQuery.isNull(columnIndexOrThrow4) ? null : cursorQuery.getString(columnIndexOrThrow4);
                            String string12 = cursorQuery.isNull(columnIndexOrThrow5) ? null : cursorQuery.getString(columnIndexOrThrow5);
                            String string13 = cursorQuery.isNull(columnIndexOrThrow6) ? null : cursorQuery.getString(columnIndexOrThrow6);
                            String string14 = cursorQuery.isNull(columnIndexOrThrow7) ? null : cursorQuery.getString(columnIndexOrThrow7);
                            String string15 = cursorQuery.isNull(columnIndexOrThrow8) ? null : cursorQuery.getString(columnIndexOrThrow8);
                            String string16 = cursorQuery.isNull(columnIndexOrThrow9) ? null : cursorQuery.getString(columnIndexOrThrow9);
                            String string17 = cursorQuery.isNull(columnIndexOrThrow10) ? null : cursorQuery.getString(columnIndexOrThrow10);
                            long j = cursorQuery.getLong(columnIndexOrThrow11);
                            boolean z = cursorQuery.getInt(columnIndexOrThrow12) != 0;
                            if (cursorQuery.isNull(columnIndexOrThrow13)) {
                                i = i8;
                                string = null;
                            } else {
                                string = cursorQuery.getString(columnIndexOrThrow13);
                                i = i8;
                            }
                            String string18 = cursorQuery.isNull(i) ? null : cursorQuery.getString(i);
                            int i9 = columnIndexOrThrow15;
                            int i10 = columnIndexOrThrow;
                            long j2 = cursorQuery.getLong(i9);
                            int i11 = columnIndexOrThrow16;
                            if (cursorQuery.isNull(i11)) {
                                columnIndexOrThrow16 = i11;
                                i2 = columnIndexOrThrow17;
                                string2 = null;
                            } else {
                                string2 = cursorQuery.getString(i11);
                                columnIndexOrThrow16 = i11;
                                i2 = columnIndexOrThrow17;
                            }
                            if (cursorQuery.isNull(i2)) {
                                i3 = i2;
                                i5 = columnIndexOrThrow13;
                                i4 = i;
                                string3 = null;
                            } else {
                                i3 = i2;
                                i4 = i;
                                string3 = cursorQuery.getString(i2);
                                i5 = columnIndexOrThrow13;
                            }
                            try {
                                List<String> listStringToStringList = DownloadsDao_Impl.this.__podcastRoomTypeConverters.stringToStringList(string3);
                                int i12 = columnIndexOrThrow18;
                                if (cursorQuery.isNull(i12)) {
                                    columnIndexOrThrow18 = i12;
                                    string4 = null;
                                } else {
                                    string4 = cursorQuery.getString(i12);
                                    columnIndexOrThrow18 = i12;
                                }
                                List<String> listStringToStringList2 = DownloadsDao_Impl.this.__podcastRoomTypeConverters.stringToStringList(string4);
                                int i13 = columnIndexOrThrow19;
                                if (cursorQuery.isNull(i13)) {
                                    i6 = columnIndexOrThrow20;
                                    string5 = null;
                                } else {
                                    string5 = cursorQuery.getString(i13);
                                    i6 = columnIndexOrThrow20;
                                }
                                if (cursorQuery.isNull(i6)) {
                                    columnIndexOrThrow19 = i13;
                                    i7 = columnIndexOrThrow21;
                                    string6 = null;
                                } else {
                                    string6 = cursorQuery.getString(i6);
                                    columnIndexOrThrow19 = i13;
                                    i7 = columnIndexOrThrow21;
                                }
                                if (cursorQuery.isNull(i7)) {
                                    columnIndexOrThrow21 = i7;
                                    string7 = null;
                                } else {
                                    columnIndexOrThrow21 = i7;
                                    string7 = cursorQuery.getString(i7);
                                }
                                arrayList.add(new Download(string8, string9, string10, string11, string12, string13, string14, string15, string16, string17, j, z, string, string18, j2, string2, listStringToStringList, listStringToStringList2, string5, string6, string7));
                                columnIndexOrThrow20 = i6;
                                columnIndexOrThrow = i10;
                                columnIndexOrThrow13 = i5;
                                columnIndexOrThrow15 = i9;
                                columnIndexOrThrow17 = i3;
                                i8 = i4;
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

    @Override // com.amazon.podcast.downloads.DownloadsDao
    public LiveData<List<Download>> getAllLiveData(final String podcastId) {
        final RoomSQLiteQuery roomSQLiteQueryAcquire = RoomSQLiteQuery.acquire("SELECT * FROM downloads WHERE podcastId = ? ORDER BY updatedTime DESC", 1);
        if (podcastId == null) {
            roomSQLiteQueryAcquire.bindNull(1);
        } else {
            roomSQLiteQueryAcquire.bindString(1, podcastId);
        }
        return this.__db.getInvalidationTracker().createLiveData(new String[]{"downloads"}, false, new Callable<List<Download>>() { // from class: com.amazon.podcast.downloads.DownloadsDao_Impl.7
            @Override // java.util.concurrent.Callable
            public List<Download> call() throws Exception {
                String string;
                int i;
                String string2;
                int i2;
                int i3;
                int i4;
                String string3;
                int i5;
                String string4;
                String string5;
                int i6;
                String string6;
                int i7;
                String string7;
                Cursor cursorQuery = DBUtil.query(DownloadsDao_Impl.this.__db, roomSQLiteQueryAcquire, false, null);
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
                    int columnIndexOrThrow11 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "durationMilliseconds");
                    int columnIndexOrThrow12 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "explicit");
                    int columnIndexOrThrow13 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "state");
                    int columnIndexOrThrow14 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "localAudioUrl");
                    try {
                        int columnIndexOrThrow15 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "updatedTime");
                        int columnIndexOrThrow16 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "playbackMode");
                        int columnIndexOrThrow17 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "contentTraits");
                        int columnIndexOrThrow18 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "availableUpsells");
                        int columnIndexOrThrow19 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "availabilityDate");
                        int columnIndexOrThrow20 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "podcastShowVariantId");
                        int columnIndexOrThrow21 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "podcastEpisodeVariantId");
                        int i8 = columnIndexOrThrow14;
                        ArrayList arrayList = new ArrayList(cursorQuery.getCount());
                        while (cursorQuery.moveToNext()) {
                            String string8 = cursorQuery.isNull(columnIndexOrThrow) ? null : cursorQuery.getString(columnIndexOrThrow);
                            String string9 = cursorQuery.isNull(columnIndexOrThrow2) ? null : cursorQuery.getString(columnIndexOrThrow2);
                            String string10 = cursorQuery.isNull(columnIndexOrThrow3) ? null : cursorQuery.getString(columnIndexOrThrow3);
                            String string11 = cursorQuery.isNull(columnIndexOrThrow4) ? null : cursorQuery.getString(columnIndexOrThrow4);
                            String string12 = cursorQuery.isNull(columnIndexOrThrow5) ? null : cursorQuery.getString(columnIndexOrThrow5);
                            String string13 = cursorQuery.isNull(columnIndexOrThrow6) ? null : cursorQuery.getString(columnIndexOrThrow6);
                            String string14 = cursorQuery.isNull(columnIndexOrThrow7) ? null : cursorQuery.getString(columnIndexOrThrow7);
                            String string15 = cursorQuery.isNull(columnIndexOrThrow8) ? null : cursorQuery.getString(columnIndexOrThrow8);
                            String string16 = cursorQuery.isNull(columnIndexOrThrow9) ? null : cursorQuery.getString(columnIndexOrThrow9);
                            String string17 = cursorQuery.isNull(columnIndexOrThrow10) ? null : cursorQuery.getString(columnIndexOrThrow10);
                            long j = cursorQuery.getLong(columnIndexOrThrow11);
                            boolean z = cursorQuery.getInt(columnIndexOrThrow12) != 0;
                            if (cursorQuery.isNull(columnIndexOrThrow13)) {
                                i = i8;
                                string = null;
                            } else {
                                string = cursorQuery.getString(columnIndexOrThrow13);
                                i = i8;
                            }
                            String string18 = cursorQuery.isNull(i) ? null : cursorQuery.getString(i);
                            int i9 = columnIndexOrThrow15;
                            int i10 = columnIndexOrThrow;
                            long j2 = cursorQuery.getLong(i9);
                            int i11 = columnIndexOrThrow16;
                            if (cursorQuery.isNull(i11)) {
                                columnIndexOrThrow16 = i11;
                                i2 = columnIndexOrThrow17;
                                string2 = null;
                            } else {
                                string2 = cursorQuery.getString(i11);
                                columnIndexOrThrow16 = i11;
                                i2 = columnIndexOrThrow17;
                            }
                            if (cursorQuery.isNull(i2)) {
                                i3 = i2;
                                i5 = columnIndexOrThrow13;
                                i4 = i;
                                string3 = null;
                            } else {
                                i3 = i2;
                                i4 = i;
                                string3 = cursorQuery.getString(i2);
                                i5 = columnIndexOrThrow13;
                            }
                            try {
                                List<String> listStringToStringList = DownloadsDao_Impl.this.__podcastRoomTypeConverters.stringToStringList(string3);
                                int i12 = columnIndexOrThrow18;
                                if (cursorQuery.isNull(i12)) {
                                    columnIndexOrThrow18 = i12;
                                    string4 = null;
                                } else {
                                    string4 = cursorQuery.getString(i12);
                                    columnIndexOrThrow18 = i12;
                                }
                                List<String> listStringToStringList2 = DownloadsDao_Impl.this.__podcastRoomTypeConverters.stringToStringList(string4);
                                int i13 = columnIndexOrThrow19;
                                if (cursorQuery.isNull(i13)) {
                                    i6 = columnIndexOrThrow20;
                                    string5 = null;
                                } else {
                                    string5 = cursorQuery.getString(i13);
                                    i6 = columnIndexOrThrow20;
                                }
                                if (cursorQuery.isNull(i6)) {
                                    columnIndexOrThrow19 = i13;
                                    i7 = columnIndexOrThrow21;
                                    string6 = null;
                                } else {
                                    string6 = cursorQuery.getString(i6);
                                    columnIndexOrThrow19 = i13;
                                    i7 = columnIndexOrThrow21;
                                }
                                if (cursorQuery.isNull(i7)) {
                                    columnIndexOrThrow21 = i7;
                                    string7 = null;
                                } else {
                                    columnIndexOrThrow21 = i7;
                                    string7 = cursorQuery.getString(i7);
                                }
                                arrayList.add(new Download(string8, string9, string10, string11, string12, string13, string14, string15, string16, string17, j, z, string, string18, j2, string2, listStringToStringList, listStringToStringList2, string5, string6, string7));
                                columnIndexOrThrow20 = i6;
                                columnIndexOrThrow = i10;
                                columnIndexOrThrow13 = i5;
                                columnIndexOrThrow15 = i9;
                                columnIndexOrThrow17 = i3;
                                i8 = i4;
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

    @Override // com.amazon.podcast.downloads.DownloadsDao
    public LiveData<List<Download>> getAllLiveData(final String podcastId, final String seasonNumber) {
        final RoomSQLiteQuery roomSQLiteQueryAcquire = RoomSQLiteQuery.acquire("SELECT * FROM downloads WHERE podcastId = ? AND seasonNumber = ? ORDER BY updatedTime DESC", 2);
        if (podcastId == null) {
            roomSQLiteQueryAcquire.bindNull(1);
        } else {
            roomSQLiteQueryAcquire.bindString(1, podcastId);
        }
        if (seasonNumber == null) {
            roomSQLiteQueryAcquire.bindNull(2);
        } else {
            roomSQLiteQueryAcquire.bindString(2, seasonNumber);
        }
        return this.__db.getInvalidationTracker().createLiveData(new String[]{"downloads"}, false, new Callable<List<Download>>() { // from class: com.amazon.podcast.downloads.DownloadsDao_Impl.8
            @Override // java.util.concurrent.Callable
            public List<Download> call() throws Exception {
                String string;
                int i;
                String string2;
                int i2;
                int i3;
                int i4;
                String string3;
                int i5;
                String string4;
                String string5;
                int i6;
                String string6;
                int i7;
                String string7;
                Cursor cursorQuery = DBUtil.query(DownloadsDao_Impl.this.__db, roomSQLiteQueryAcquire, false, null);
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
                    int columnIndexOrThrow11 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "durationMilliseconds");
                    int columnIndexOrThrow12 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "explicit");
                    int columnIndexOrThrow13 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "state");
                    int columnIndexOrThrow14 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "localAudioUrl");
                    try {
                        int columnIndexOrThrow15 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "updatedTime");
                        int columnIndexOrThrow16 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "playbackMode");
                        int columnIndexOrThrow17 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "contentTraits");
                        int columnIndexOrThrow18 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "availableUpsells");
                        int columnIndexOrThrow19 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "availabilityDate");
                        int columnIndexOrThrow20 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "podcastShowVariantId");
                        int columnIndexOrThrow21 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "podcastEpisodeVariantId");
                        int i8 = columnIndexOrThrow14;
                        ArrayList arrayList = new ArrayList(cursorQuery.getCount());
                        while (cursorQuery.moveToNext()) {
                            String string8 = cursorQuery.isNull(columnIndexOrThrow) ? null : cursorQuery.getString(columnIndexOrThrow);
                            String string9 = cursorQuery.isNull(columnIndexOrThrow2) ? null : cursorQuery.getString(columnIndexOrThrow2);
                            String string10 = cursorQuery.isNull(columnIndexOrThrow3) ? null : cursorQuery.getString(columnIndexOrThrow3);
                            String string11 = cursorQuery.isNull(columnIndexOrThrow4) ? null : cursorQuery.getString(columnIndexOrThrow4);
                            String string12 = cursorQuery.isNull(columnIndexOrThrow5) ? null : cursorQuery.getString(columnIndexOrThrow5);
                            String string13 = cursorQuery.isNull(columnIndexOrThrow6) ? null : cursorQuery.getString(columnIndexOrThrow6);
                            String string14 = cursorQuery.isNull(columnIndexOrThrow7) ? null : cursorQuery.getString(columnIndexOrThrow7);
                            String string15 = cursorQuery.isNull(columnIndexOrThrow8) ? null : cursorQuery.getString(columnIndexOrThrow8);
                            String string16 = cursorQuery.isNull(columnIndexOrThrow9) ? null : cursorQuery.getString(columnIndexOrThrow9);
                            String string17 = cursorQuery.isNull(columnIndexOrThrow10) ? null : cursorQuery.getString(columnIndexOrThrow10);
                            long j = cursorQuery.getLong(columnIndexOrThrow11);
                            boolean z = cursorQuery.getInt(columnIndexOrThrow12) != 0;
                            if (cursorQuery.isNull(columnIndexOrThrow13)) {
                                i = i8;
                                string = null;
                            } else {
                                string = cursorQuery.getString(columnIndexOrThrow13);
                                i = i8;
                            }
                            String string18 = cursorQuery.isNull(i) ? null : cursorQuery.getString(i);
                            int i9 = columnIndexOrThrow15;
                            int i10 = columnIndexOrThrow;
                            long j2 = cursorQuery.getLong(i9);
                            int i11 = columnIndexOrThrow16;
                            if (cursorQuery.isNull(i11)) {
                                columnIndexOrThrow16 = i11;
                                i2 = columnIndexOrThrow17;
                                string2 = null;
                            } else {
                                string2 = cursorQuery.getString(i11);
                                columnIndexOrThrow16 = i11;
                                i2 = columnIndexOrThrow17;
                            }
                            if (cursorQuery.isNull(i2)) {
                                i3 = i2;
                                i5 = columnIndexOrThrow13;
                                i4 = i;
                                string3 = null;
                            } else {
                                i3 = i2;
                                i4 = i;
                                string3 = cursorQuery.getString(i2);
                                i5 = columnIndexOrThrow13;
                            }
                            try {
                                List<String> listStringToStringList = DownloadsDao_Impl.this.__podcastRoomTypeConverters.stringToStringList(string3);
                                int i12 = columnIndexOrThrow18;
                                if (cursorQuery.isNull(i12)) {
                                    columnIndexOrThrow18 = i12;
                                    string4 = null;
                                } else {
                                    string4 = cursorQuery.getString(i12);
                                    columnIndexOrThrow18 = i12;
                                }
                                List<String> listStringToStringList2 = DownloadsDao_Impl.this.__podcastRoomTypeConverters.stringToStringList(string4);
                                int i13 = columnIndexOrThrow19;
                                if (cursorQuery.isNull(i13)) {
                                    i6 = columnIndexOrThrow20;
                                    string5 = null;
                                } else {
                                    string5 = cursorQuery.getString(i13);
                                    i6 = columnIndexOrThrow20;
                                }
                                if (cursorQuery.isNull(i6)) {
                                    columnIndexOrThrow19 = i13;
                                    i7 = columnIndexOrThrow21;
                                    string6 = null;
                                } else {
                                    string6 = cursorQuery.getString(i6);
                                    columnIndexOrThrow19 = i13;
                                    i7 = columnIndexOrThrow21;
                                }
                                if (cursorQuery.isNull(i7)) {
                                    columnIndexOrThrow21 = i7;
                                    string7 = null;
                                } else {
                                    columnIndexOrThrow21 = i7;
                                    string7 = cursorQuery.getString(i7);
                                }
                                arrayList.add(new Download(string8, string9, string10, string11, string12, string13, string14, string15, string16, string17, j, z, string, string18, j2, string2, listStringToStringList, listStringToStringList2, string5, string6, string7));
                                columnIndexOrThrow20 = i6;
                                columnIndexOrThrow = i10;
                                columnIndexOrThrow13 = i5;
                                columnIndexOrThrow15 = i9;
                                columnIndexOrThrow17 = i3;
                                i8 = i4;
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

    public static List<Class<?>> getRequiredConverters() {
        return Collections.emptyList();
    }
}
