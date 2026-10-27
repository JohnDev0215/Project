package com.amazon.podcast.bookmark;

import android.database.Cursor;
import androidx.lifecycle.LiveData;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.room.util.StringUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.amazon.music.curate.skyfire.bootstrap.ContextMappingConstants;
import com.google.android.gms.cast.MediaTrack;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Callable;

/* JADX INFO: loaded from: classes5.dex */
public final class BookmarksDao_Impl implements BookmarksDao {
    private final RoomDatabase __db;
    private final EntityInsertionAdapter<Bookmark> __insertionAdapterOfBookmark;
    private final SharedSQLiteStatement __preparedStmtOfDeleteAll;

    public BookmarksDao_Impl(RoomDatabase __db) {
        this.__db = __db;
        this.__insertionAdapterOfBookmark = new EntityInsertionAdapter<Bookmark>(__db) { // from class: com.amazon.podcast.bookmark.BookmarksDao_Impl.1
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "INSERT OR REPLACE INTO `bookmarks` (`id`,`podcastId`,`podcastTitle`,`title`,`image`,`podcastImage`,`publishDate`,`description`,`authors`,`seasonNumber`,`totalDurationMilliseconds`,`progressMilliseconds`,`updatedTime`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)";
            }

            @Override // androidx.room.EntityInsertionAdapter
            public void bind(SupportSQLiteStatement stmt, Bookmark value) {
                if (value.getId() == null) {
                    stmt.bindNull(1);
                } else {
                    stmt.bindString(1, value.getId());
                }
                if (value.getPodcastId() == null) {
                    stmt.bindNull(2);
                } else {
                    stmt.bindString(2, value.getPodcastId());
                }
                if (value.getPodcastTitle() == null) {
                    stmt.bindNull(3);
                } else {
                    stmt.bindString(3, value.getPodcastTitle());
                }
                if (value.getTitle() == null) {
                    stmt.bindNull(4);
                } else {
                    stmt.bindString(4, value.getTitle());
                }
                if (value.getImage() == null) {
                    stmt.bindNull(5);
                } else {
                    stmt.bindString(5, value.getImage());
                }
                if (value.getPodcastImage() == null) {
                    stmt.bindNull(6);
                } else {
                    stmt.bindString(6, value.getPodcastImage());
                }
                if (value.getPublishDate() == null) {
                    stmt.bindNull(7);
                } else {
                    stmt.bindString(7, value.getPublishDate());
                }
                if (value.getDescription() == null) {
                    stmt.bindNull(8);
                } else {
                    stmt.bindString(8, value.getDescription());
                }
                if (value.getAuthors() == null) {
                    stmt.bindNull(9);
                } else {
                    stmt.bindString(9, value.getAuthors());
                }
                if (value.getSeasonNumber() == null) {
                    stmt.bindNull(10);
                } else {
                    stmt.bindString(10, value.getSeasonNumber());
                }
                stmt.bindLong(11, value.getTotalDurationMilliseconds());
                stmt.bindLong(12, value.getProgressMilliseconds());
                stmt.bindLong(13, value.getUpdatedTime());
            }
        };
        this.__preparedStmtOfDeleteAll = new SharedSQLiteStatement(__db) { // from class: com.amazon.podcast.bookmark.BookmarksDao_Impl.2
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "DELETE FROM bookmarks";
            }
        };
    }

    @Override // com.amazon.podcast.bookmark.BookmarksDao
    public void insert(final Bookmark bookmark) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__insertionAdapterOfBookmark.insert(bookmark);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // com.amazon.podcast.bookmark.BookmarksDao
    public Bookmark getData(final String id, final Long lowerBoundProgressMilliseconds, final Integer upperBoundProgressPercentage) {
        Bookmark bookmark;
        RoomSQLiteQuery roomSQLiteQueryAcquire = RoomSQLiteQuery.acquire("SELECT * FROM bookmarks WHERE id = ? AND progressMilliseconds > ? AND (((progressMilliseconds) * 100) / totalDurationMilliseconds) < ?", 3);
        if (id == null) {
            roomSQLiteQueryAcquire.bindNull(1);
        } else {
            roomSQLiteQueryAcquire.bindString(1, id);
        }
        if (lowerBoundProgressMilliseconds == null) {
            roomSQLiteQueryAcquire.bindNull(2);
        } else {
            roomSQLiteQueryAcquire.bindLong(2, lowerBoundProgressMilliseconds.longValue());
        }
        if (upperBoundProgressPercentage == null) {
            roomSQLiteQueryAcquire.bindNull(3);
        } else {
            roomSQLiteQueryAcquire.bindLong(3, upperBoundProgressPercentage.intValue());
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
            int columnIndexOrThrow11 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "totalDurationMilliseconds");
            int columnIndexOrThrow12 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "progressMilliseconds");
            int columnIndexOrThrow13 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "updatedTime");
            if (cursorQuery.moveToFirst()) {
                bookmark = new Bookmark(cursorQuery.isNull(columnIndexOrThrow) ? null : cursorQuery.getString(columnIndexOrThrow), cursorQuery.isNull(columnIndexOrThrow2) ? null : cursorQuery.getString(columnIndexOrThrow2), cursorQuery.isNull(columnIndexOrThrow3) ? null : cursorQuery.getString(columnIndexOrThrow3), cursorQuery.isNull(columnIndexOrThrow4) ? null : cursorQuery.getString(columnIndexOrThrow4), cursorQuery.isNull(columnIndexOrThrow5) ? null : cursorQuery.getString(columnIndexOrThrow5), cursorQuery.isNull(columnIndexOrThrow6) ? null : cursorQuery.getString(columnIndexOrThrow6), cursorQuery.isNull(columnIndexOrThrow7) ? null : cursorQuery.getString(columnIndexOrThrow7), cursorQuery.isNull(columnIndexOrThrow8) ? null : cursorQuery.getString(columnIndexOrThrow8), cursorQuery.isNull(columnIndexOrThrow9) ? null : cursorQuery.getString(columnIndexOrThrow9), cursorQuery.isNull(columnIndexOrThrow10) ? null : cursorQuery.getString(columnIndexOrThrow10), cursorQuery.getLong(columnIndexOrThrow11), cursorQuery.getLong(columnIndexOrThrow12), cursorQuery.getLong(columnIndexOrThrow13));
            } else {
                bookmark = null;
            }
            return bookmark;
        } finally {
            cursorQuery.close();
            roomSQLiteQueryAcquire.release();
        }
    }

    @Override // com.amazon.podcast.bookmark.BookmarksDao
    public LiveData<Bookmark> getLiveData(final String id) {
        final RoomSQLiteQuery roomSQLiteQueryAcquire = RoomSQLiteQuery.acquire("SELECT * FROM bookmarks WHERE id = ?", 1);
        if (id == null) {
            roomSQLiteQueryAcquire.bindNull(1);
        } else {
            roomSQLiteQueryAcquire.bindString(1, id);
        }
        return this.__db.getInvalidationTracker().createLiveData(new String[]{"bookmarks"}, false, new Callable<Bookmark>() { // from class: com.amazon.podcast.bookmark.BookmarksDao_Impl.3
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // java.util.concurrent.Callable
            public Bookmark call() throws Exception {
                Bookmark bookmark = null;
                Cursor cursorQuery = DBUtil.query(BookmarksDao_Impl.this.__db, roomSQLiteQueryAcquire, false, null);
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
                    int columnIndexOrThrow12 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "progressMilliseconds");
                    int columnIndexOrThrow13 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "updatedTime");
                    if (cursorQuery.moveToFirst()) {
                        bookmark = new Bookmark(cursorQuery.isNull(columnIndexOrThrow) ? null : cursorQuery.getString(columnIndexOrThrow), cursorQuery.isNull(columnIndexOrThrow2) ? null : cursorQuery.getString(columnIndexOrThrow2), cursorQuery.isNull(columnIndexOrThrow3) ? null : cursorQuery.getString(columnIndexOrThrow3), cursorQuery.isNull(columnIndexOrThrow4) ? null : cursorQuery.getString(columnIndexOrThrow4), cursorQuery.isNull(columnIndexOrThrow5) ? null : cursorQuery.getString(columnIndexOrThrow5), cursorQuery.isNull(columnIndexOrThrow6) ? null : cursorQuery.getString(columnIndexOrThrow6), cursorQuery.isNull(columnIndexOrThrow7) ? null : cursorQuery.getString(columnIndexOrThrow7), cursorQuery.isNull(columnIndexOrThrow8) ? null : cursorQuery.getString(columnIndexOrThrow8), cursorQuery.isNull(columnIndexOrThrow9) ? null : cursorQuery.getString(columnIndexOrThrow9), cursorQuery.isNull(columnIndexOrThrow10) ? null : cursorQuery.getString(columnIndexOrThrow10), cursorQuery.getLong(columnIndexOrThrow11), cursorQuery.getLong(columnIndexOrThrow12), cursorQuery.getLong(columnIndexOrThrow13));
                    }
                    return bookmark;
                } finally {
                    cursorQuery.close();
                }
            }

            protected void finalize() {
                roomSQLiteQueryAcquire.release();
            }
        });
    }

    @Override // com.amazon.podcast.bookmark.BookmarksDao
    public Bookmark get(final String id) {
        Bookmark bookmark;
        RoomSQLiteQuery roomSQLiteQueryAcquire = RoomSQLiteQuery.acquire("SELECT * FROM bookmarks WHERE id = ?", 1);
        if (id == null) {
            roomSQLiteQueryAcquire.bindNull(1);
        } else {
            roomSQLiteQueryAcquire.bindString(1, id);
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
            int columnIndexOrThrow11 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "totalDurationMilliseconds");
            int columnIndexOrThrow12 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "progressMilliseconds");
            int columnIndexOrThrow13 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "updatedTime");
            if (cursorQuery.moveToFirst()) {
                bookmark = new Bookmark(cursorQuery.isNull(columnIndexOrThrow) ? null : cursorQuery.getString(columnIndexOrThrow), cursorQuery.isNull(columnIndexOrThrow2) ? null : cursorQuery.getString(columnIndexOrThrow2), cursorQuery.isNull(columnIndexOrThrow3) ? null : cursorQuery.getString(columnIndexOrThrow3), cursorQuery.isNull(columnIndexOrThrow4) ? null : cursorQuery.getString(columnIndexOrThrow4), cursorQuery.isNull(columnIndexOrThrow5) ? null : cursorQuery.getString(columnIndexOrThrow5), cursorQuery.isNull(columnIndexOrThrow6) ? null : cursorQuery.getString(columnIndexOrThrow6), cursorQuery.isNull(columnIndexOrThrow7) ? null : cursorQuery.getString(columnIndexOrThrow7), cursorQuery.isNull(columnIndexOrThrow8) ? null : cursorQuery.getString(columnIndexOrThrow8), cursorQuery.isNull(columnIndexOrThrow9) ? null : cursorQuery.getString(columnIndexOrThrow9), cursorQuery.isNull(columnIndexOrThrow10) ? null : cursorQuery.getString(columnIndexOrThrow10), cursorQuery.getLong(columnIndexOrThrow11), cursorQuery.getLong(columnIndexOrThrow12), cursorQuery.getLong(columnIndexOrThrow13));
            } else {
                bookmark = null;
            }
            return bookmark;
        } finally {
            cursorQuery.close();
            roomSQLiteQueryAcquire.release();
        }
    }

    @Override // com.amazon.podcast.bookmark.BookmarksDao
    public LiveData<List<Bookmark>> getAll(final Long lowerBoundProgressMilliseconds, final Integer upperBoundProgressPercentage) {
        final RoomSQLiteQuery roomSQLiteQueryAcquire = RoomSQLiteQuery.acquire("SELECT * FROM bookmarks WHERE progressMilliseconds > ? AND (((progressMilliseconds) * 100) / totalDurationMilliseconds) < ? ORDER BY updatedTime DESC", 2);
        if (lowerBoundProgressMilliseconds == null) {
            roomSQLiteQueryAcquire.bindNull(1);
        } else {
            roomSQLiteQueryAcquire.bindLong(1, lowerBoundProgressMilliseconds.longValue());
        }
        if (upperBoundProgressPercentage == null) {
            roomSQLiteQueryAcquire.bindNull(2);
        } else {
            roomSQLiteQueryAcquire.bindLong(2, upperBoundProgressPercentage.intValue());
        }
        return this.__db.getInvalidationTracker().createLiveData(new String[]{"bookmarks"}, false, new Callable<List<Bookmark>>() { // from class: com.amazon.podcast.bookmark.BookmarksDao_Impl.4
            @Override // java.util.concurrent.Callable
            public List<Bookmark> call() throws Exception {
                Cursor cursorQuery = DBUtil.query(BookmarksDao_Impl.this.__db, roomSQLiteQueryAcquire, false, null);
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
                    int columnIndexOrThrow12 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "progressMilliseconds");
                    int columnIndexOrThrow13 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "updatedTime");
                    ArrayList arrayList = new ArrayList(cursorQuery.getCount());
                    while (cursorQuery.moveToNext()) {
                        arrayList.add(new Bookmark(cursorQuery.isNull(columnIndexOrThrow) ? null : cursorQuery.getString(columnIndexOrThrow), cursorQuery.isNull(columnIndexOrThrow2) ? null : cursorQuery.getString(columnIndexOrThrow2), cursorQuery.isNull(columnIndexOrThrow3) ? null : cursorQuery.getString(columnIndexOrThrow3), cursorQuery.isNull(columnIndexOrThrow4) ? null : cursorQuery.getString(columnIndexOrThrow4), cursorQuery.isNull(columnIndexOrThrow5) ? null : cursorQuery.getString(columnIndexOrThrow5), cursorQuery.isNull(columnIndexOrThrow6) ? null : cursorQuery.getString(columnIndexOrThrow6), cursorQuery.isNull(columnIndexOrThrow7) ? null : cursorQuery.getString(columnIndexOrThrow7), cursorQuery.isNull(columnIndexOrThrow8) ? null : cursorQuery.getString(columnIndexOrThrow8), cursorQuery.isNull(columnIndexOrThrow9) ? null : cursorQuery.getString(columnIndexOrThrow9), cursorQuery.isNull(columnIndexOrThrow10) ? null : cursorQuery.getString(columnIndexOrThrow10), cursorQuery.getLong(columnIndexOrThrow11), cursorQuery.getLong(columnIndexOrThrow12), cursorQuery.getLong(columnIndexOrThrow13)));
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

    @Override // com.amazon.podcast.bookmark.BookmarksDao
    public LiveData<List<Bookmark>> getAll(final Set<String> ids) {
        StringBuilder sbNewStringBuilder = StringUtil.newStringBuilder();
        sbNewStringBuilder.append("SELECT * FROM bookmarks WHERE id IN (");
        int size = ids.size();
        StringUtil.appendPlaceholders(sbNewStringBuilder, size);
        sbNewStringBuilder.append(")");
        final RoomSQLiteQuery roomSQLiteQueryAcquire = RoomSQLiteQuery.acquire(sbNewStringBuilder.toString(), size + 0);
        int i = 1;
        for (String str : ids) {
            if (str == null) {
                roomSQLiteQueryAcquire.bindNull(i);
            } else {
                roomSQLiteQueryAcquire.bindString(i, str);
            }
            i++;
        }
        return this.__db.getInvalidationTracker().createLiveData(new String[]{"bookmarks"}, false, new Callable<List<Bookmark>>() { // from class: com.amazon.podcast.bookmark.BookmarksDao_Impl.6
            @Override // java.util.concurrent.Callable
            public List<Bookmark> call() throws Exception {
                Cursor cursorQuery = DBUtil.query(BookmarksDao_Impl.this.__db, roomSQLiteQueryAcquire, false, null);
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
                    int columnIndexOrThrow12 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "progressMilliseconds");
                    int columnIndexOrThrow13 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "updatedTime");
                    ArrayList arrayList = new ArrayList(cursorQuery.getCount());
                    while (cursorQuery.moveToNext()) {
                        arrayList.add(new Bookmark(cursorQuery.isNull(columnIndexOrThrow) ? null : cursorQuery.getString(columnIndexOrThrow), cursorQuery.isNull(columnIndexOrThrow2) ? null : cursorQuery.getString(columnIndexOrThrow2), cursorQuery.isNull(columnIndexOrThrow3) ? null : cursorQuery.getString(columnIndexOrThrow3), cursorQuery.isNull(columnIndexOrThrow4) ? null : cursorQuery.getString(columnIndexOrThrow4), cursorQuery.isNull(columnIndexOrThrow5) ? null : cursorQuery.getString(columnIndexOrThrow5), cursorQuery.isNull(columnIndexOrThrow6) ? null : cursorQuery.getString(columnIndexOrThrow6), cursorQuery.isNull(columnIndexOrThrow7) ? null : cursorQuery.getString(columnIndexOrThrow7), cursorQuery.isNull(columnIndexOrThrow8) ? null : cursorQuery.getString(columnIndexOrThrow8), cursorQuery.isNull(columnIndexOrThrow9) ? null : cursorQuery.getString(columnIndexOrThrow9), cursorQuery.isNull(columnIndexOrThrow10) ? null : cursorQuery.getString(columnIndexOrThrow10), cursorQuery.getLong(columnIndexOrThrow11), cursorQuery.getLong(columnIndexOrThrow12), cursorQuery.getLong(columnIndexOrThrow13)));
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

    @Override // com.amazon.podcast.bookmark.BookmarksDao
    public LiveData<List<Bookmark>> getAllUniquePodcastEntries(final Long lowerBoundProgressMilliseconds, final Integer upperBoundProgressPercentage, final Long uptoNDaysInMilliseconds) {
        final RoomSQLiteQuery roomSQLiteQueryAcquire = RoomSQLiteQuery.acquire("SELECT * FROM bookmarks WHERE progressMilliseconds > ? AND (((progressMilliseconds) * 100) / totalDurationMilliseconds) < ? AND updatedTime >= ? GROUP BY podcastId HAVING MAX(updatedTime) ORDER BY updatedTime DESC", 3);
        if (lowerBoundProgressMilliseconds == null) {
            roomSQLiteQueryAcquire.bindNull(1);
        } else {
            roomSQLiteQueryAcquire.bindLong(1, lowerBoundProgressMilliseconds.longValue());
        }
        if (upperBoundProgressPercentage == null) {
            roomSQLiteQueryAcquire.bindNull(2);
        } else {
            roomSQLiteQueryAcquire.bindLong(2, upperBoundProgressPercentage.intValue());
        }
        if (uptoNDaysInMilliseconds == null) {
            roomSQLiteQueryAcquire.bindNull(3);
        } else {
            roomSQLiteQueryAcquire.bindLong(3, uptoNDaysInMilliseconds.longValue());
        }
        return this.__db.getInvalidationTracker().createLiveData(new String[]{"bookmarks"}, false, new Callable<List<Bookmark>>() { // from class: com.amazon.podcast.bookmark.BookmarksDao_Impl.7
            @Override // java.util.concurrent.Callable
            public List<Bookmark> call() throws Exception {
                Cursor cursorQuery = DBUtil.query(BookmarksDao_Impl.this.__db, roomSQLiteQueryAcquire, false, null);
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
                    int columnIndexOrThrow12 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "progressMilliseconds");
                    int columnIndexOrThrow13 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "updatedTime");
                    ArrayList arrayList = new ArrayList(cursorQuery.getCount());
                    while (cursorQuery.moveToNext()) {
                        arrayList.add(new Bookmark(cursorQuery.isNull(columnIndexOrThrow) ? null : cursorQuery.getString(columnIndexOrThrow), cursorQuery.isNull(columnIndexOrThrow2) ? null : cursorQuery.getString(columnIndexOrThrow2), cursorQuery.isNull(columnIndexOrThrow3) ? null : cursorQuery.getString(columnIndexOrThrow3), cursorQuery.isNull(columnIndexOrThrow4) ? null : cursorQuery.getString(columnIndexOrThrow4), cursorQuery.isNull(columnIndexOrThrow5) ? null : cursorQuery.getString(columnIndexOrThrow5), cursorQuery.isNull(columnIndexOrThrow6) ? null : cursorQuery.getString(columnIndexOrThrow6), cursorQuery.isNull(columnIndexOrThrow7) ? null : cursorQuery.getString(columnIndexOrThrow7), cursorQuery.isNull(columnIndexOrThrow8) ? null : cursorQuery.getString(columnIndexOrThrow8), cursorQuery.isNull(columnIndexOrThrow9) ? null : cursorQuery.getString(columnIndexOrThrow9), cursorQuery.isNull(columnIndexOrThrow10) ? null : cursorQuery.getString(columnIndexOrThrow10), cursorQuery.getLong(columnIndexOrThrow11), cursorQuery.getLong(columnIndexOrThrow12), cursorQuery.getLong(columnIndexOrThrow13)));
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

    @Override // com.amazon.podcast.bookmark.BookmarksDao
    public List<Bookmark> getAllList(final Set<String> ids) throws Throwable {
        RoomSQLiteQuery roomSQLiteQuery;
        StringBuilder sbNewStringBuilder = StringUtil.newStringBuilder();
        sbNewStringBuilder.append("SELECT * FROM bookmarks WHERE id IN (");
        int size = ids.size();
        StringUtil.appendPlaceholders(sbNewStringBuilder, size);
        sbNewStringBuilder.append(")");
        RoomSQLiteQuery roomSQLiteQueryAcquire = RoomSQLiteQuery.acquire(sbNewStringBuilder.toString(), size + 0);
        int i = 1;
        for (String str : ids) {
            if (str == null) {
                roomSQLiteQueryAcquire.bindNull(i);
            } else {
                roomSQLiteQueryAcquire.bindString(i, str);
            }
            i++;
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
            int columnIndexOrThrow11 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "totalDurationMilliseconds");
            int columnIndexOrThrow12 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "progressMilliseconds");
            int columnIndexOrThrow13 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "updatedTime");
            roomSQLiteQuery = roomSQLiteQueryAcquire;
            try {
                ArrayList arrayList = new ArrayList(cursorQuery.getCount());
                while (cursorQuery.moveToNext()) {
                    arrayList.add(new Bookmark(cursorQuery.isNull(columnIndexOrThrow) ? null : cursorQuery.getString(columnIndexOrThrow), cursorQuery.isNull(columnIndexOrThrow2) ? null : cursorQuery.getString(columnIndexOrThrow2), cursorQuery.isNull(columnIndexOrThrow3) ? null : cursorQuery.getString(columnIndexOrThrow3), cursorQuery.isNull(columnIndexOrThrow4) ? null : cursorQuery.getString(columnIndexOrThrow4), cursorQuery.isNull(columnIndexOrThrow5) ? null : cursorQuery.getString(columnIndexOrThrow5), cursorQuery.isNull(columnIndexOrThrow6) ? null : cursorQuery.getString(columnIndexOrThrow6), cursorQuery.isNull(columnIndexOrThrow7) ? null : cursorQuery.getString(columnIndexOrThrow7), cursorQuery.isNull(columnIndexOrThrow8) ? null : cursorQuery.getString(columnIndexOrThrow8), cursorQuery.isNull(columnIndexOrThrow9) ? null : cursorQuery.getString(columnIndexOrThrow9), cursorQuery.isNull(columnIndexOrThrow10) ? null : cursorQuery.getString(columnIndexOrThrow10), cursorQuery.getLong(columnIndexOrThrow11), cursorQuery.getLong(columnIndexOrThrow12), cursorQuery.getLong(columnIndexOrThrow13)));
                }
                cursorQuery.close();
                roomSQLiteQuery.release();
                return arrayList;
            } catch (Throwable th) {
                th = th;
                cursorQuery.close();
                roomSQLiteQuery.release();
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
            roomSQLiteQuery = roomSQLiteQueryAcquire;
        }
    }

    public static List<Class<?>> getRequiredConverters() {
        return Collections.emptyList();
    }
}
