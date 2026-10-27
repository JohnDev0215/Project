package com.amazon.podcast.biteBookmark;

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
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Callable;

/* JADX INFO: loaded from: classes5.dex */
public final class BiteBookmarksDao_Impl implements BiteBookmarksDao {
    private final RoomDatabase __db;
    private final EntityInsertionAdapter<BiteBookmark> __insertionAdapterOfBiteBookmark;
    private final SharedSQLiteStatement __preparedStmtOfDeleteAll;

    public BiteBookmarksDao_Impl(RoomDatabase __db) {
        this.__db = __db;
        this.__insertionAdapterOfBiteBookmark = new EntityInsertionAdapter<BiteBookmark>(__db) { // from class: com.amazon.podcast.biteBookmark.BiteBookmarksDao_Impl.1
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "INSERT OR REPLACE INTO `bite_bookmarks` (`id`,`podcastId`,`episodeId`,`totalDurationMilliseconds`,`progressMilliseconds`,`updatedTime`) VALUES (?,?,?,?,?,?)";
            }

            @Override // androidx.room.EntityInsertionAdapter
            public void bind(SupportSQLiteStatement stmt, BiteBookmark value) {
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
                if (value.getEpisodeId() == null) {
                    stmt.bindNull(3);
                } else {
                    stmt.bindString(3, value.getEpisodeId());
                }
                stmt.bindLong(4, value.getTotalDurationMilliseconds());
                stmt.bindLong(5, value.getProgressMilliseconds());
                stmt.bindLong(6, value.getUpdatedTime());
            }
        };
        this.__preparedStmtOfDeleteAll = new SharedSQLiteStatement(__db) { // from class: com.amazon.podcast.biteBookmark.BiteBookmarksDao_Impl.2
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "DELETE FROM bite_bookmarks";
            }
        };
    }

    @Override // com.amazon.podcast.biteBookmark.BiteBookmarksDao
    public void insert(final BiteBookmark biteBookmark) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__insertionAdapterOfBiteBookmark.insert(biteBookmark);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // com.amazon.podcast.biteBookmark.BiteBookmarksDao
    public BiteBookmark getData(String str, Long l, Integer num) {
        RoomSQLiteQuery roomSQLiteQueryAcquire = RoomSQLiteQuery.acquire("SELECT * FROM bite_bookmarks WHERE id = ? AND progressMilliseconds > ? AND (((progressMilliseconds) * 100) / totalDurationMilliseconds) < ?", 3);
        if (str == null) {
            roomSQLiteQueryAcquire.bindNull(1);
        } else {
            roomSQLiteQueryAcquire.bindString(1, str);
        }
        if (l == null) {
            roomSQLiteQueryAcquire.bindNull(2);
        } else {
            roomSQLiteQueryAcquire.bindLong(2, l.longValue());
        }
        if (num == null) {
            roomSQLiteQueryAcquire.bindNull(3);
        } else {
            roomSQLiteQueryAcquire.bindLong(3, num.intValue());
        }
        this.__db.assertNotSuspendingTransaction();
        BiteBookmark biteBookmark = null;
        Cursor cursorQuery = DBUtil.query(this.__db, roomSQLiteQueryAcquire, false, null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(cursorQuery, "id");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "podcastId");
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "episodeId");
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "totalDurationMilliseconds");
            int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "progressMilliseconds");
            int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "updatedTime");
            if (cursorQuery.moveToFirst()) {
                biteBookmark = new BiteBookmark(cursorQuery.isNull(columnIndexOrThrow) ? null : cursorQuery.getString(columnIndexOrThrow), cursorQuery.isNull(columnIndexOrThrow2) ? null : cursorQuery.getString(columnIndexOrThrow2), cursorQuery.isNull(columnIndexOrThrow3) ? null : cursorQuery.getString(columnIndexOrThrow3), cursorQuery.getLong(columnIndexOrThrow4), cursorQuery.getLong(columnIndexOrThrow5), cursorQuery.getLong(columnIndexOrThrow6));
            }
            return biteBookmark;
        } finally {
            cursorQuery.close();
            roomSQLiteQueryAcquire.release();
        }
    }

    @Override // com.amazon.podcast.biteBookmark.BiteBookmarksDao
    public LiveData<BiteBookmark> getLiveData(final String id) {
        final RoomSQLiteQuery roomSQLiteQueryAcquire = RoomSQLiteQuery.acquire("SELECT * FROM bite_bookmarks WHERE id = ?", 1);
        if (id == null) {
            roomSQLiteQueryAcquire.bindNull(1);
        } else {
            roomSQLiteQueryAcquire.bindString(1, id);
        }
        return this.__db.getInvalidationTracker().createLiveData(new String[]{"bite_bookmarks"}, false, new Callable<BiteBookmark>() { // from class: com.amazon.podcast.biteBookmark.BiteBookmarksDao_Impl.3
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // java.util.concurrent.Callable
            public BiteBookmark call() throws Exception {
                BiteBookmark biteBookmark = null;
                Cursor cursorQuery = DBUtil.query(BiteBookmarksDao_Impl.this.__db, roomSQLiteQueryAcquire, false, null);
                try {
                    int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(cursorQuery, "id");
                    int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "podcastId");
                    int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "episodeId");
                    int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "totalDurationMilliseconds");
                    int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "progressMilliseconds");
                    int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "updatedTime");
                    if (cursorQuery.moveToFirst()) {
                        biteBookmark = new BiteBookmark(cursorQuery.isNull(columnIndexOrThrow) ? null : cursorQuery.getString(columnIndexOrThrow), cursorQuery.isNull(columnIndexOrThrow2) ? null : cursorQuery.getString(columnIndexOrThrow2), cursorQuery.isNull(columnIndexOrThrow3) ? null : cursorQuery.getString(columnIndexOrThrow3), cursorQuery.getLong(columnIndexOrThrow4), cursorQuery.getLong(columnIndexOrThrow5), cursorQuery.getLong(columnIndexOrThrow6));
                    }
                    return biteBookmark;
                } finally {
                    cursorQuery.close();
                }
            }

            protected void finalize() {
                roomSQLiteQueryAcquire.release();
            }
        });
    }

    @Override // com.amazon.podcast.biteBookmark.BiteBookmarksDao
    public BiteBookmark get(String str) {
        RoomSQLiteQuery roomSQLiteQueryAcquire = RoomSQLiteQuery.acquire("SELECT * FROM bite_bookmarks WHERE id = ?", 1);
        if (str == null) {
            roomSQLiteQueryAcquire.bindNull(1);
        } else {
            roomSQLiteQueryAcquire.bindString(1, str);
        }
        this.__db.assertNotSuspendingTransaction();
        BiteBookmark biteBookmark = null;
        Cursor cursorQuery = DBUtil.query(this.__db, roomSQLiteQueryAcquire, false, null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(cursorQuery, "id");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "podcastId");
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "episodeId");
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "totalDurationMilliseconds");
            int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "progressMilliseconds");
            int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "updatedTime");
            if (cursorQuery.moveToFirst()) {
                biteBookmark = new BiteBookmark(cursorQuery.isNull(columnIndexOrThrow) ? null : cursorQuery.getString(columnIndexOrThrow), cursorQuery.isNull(columnIndexOrThrow2) ? null : cursorQuery.getString(columnIndexOrThrow2), cursorQuery.isNull(columnIndexOrThrow3) ? null : cursorQuery.getString(columnIndexOrThrow3), cursorQuery.getLong(columnIndexOrThrow4), cursorQuery.getLong(columnIndexOrThrow5), cursorQuery.getLong(columnIndexOrThrow6));
            }
            return biteBookmark;
        } finally {
            cursorQuery.close();
            roomSQLiteQueryAcquire.release();
        }
    }

    @Override // com.amazon.podcast.biteBookmark.BiteBookmarksDao
    public LiveData<List<BiteBookmark>> getAll(final Long lowerBoundProgressMilliseconds, final Integer upperBoundProgressPercentage) {
        final RoomSQLiteQuery roomSQLiteQueryAcquire = RoomSQLiteQuery.acquire("SELECT * FROM bite_bookmarks WHERE progressMilliseconds > ? AND (((progressMilliseconds) * 100) / totalDurationMilliseconds) < ? ORDER BY updatedTime DESC", 2);
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
        return this.__db.getInvalidationTracker().createLiveData(new String[]{"bite_bookmarks"}, false, new Callable<List<BiteBookmark>>() { // from class: com.amazon.podcast.biteBookmark.BiteBookmarksDao_Impl.4
            @Override // java.util.concurrent.Callable
            public List<BiteBookmark> call() throws Exception {
                Cursor cursorQuery = DBUtil.query(BiteBookmarksDao_Impl.this.__db, roomSQLiteQueryAcquire, false, null);
                try {
                    int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(cursorQuery, "id");
                    int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "podcastId");
                    int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "episodeId");
                    int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "totalDurationMilliseconds");
                    int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "progressMilliseconds");
                    int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "updatedTime");
                    ArrayList arrayList = new ArrayList(cursorQuery.getCount());
                    while (cursorQuery.moveToNext()) {
                        arrayList.add(new BiteBookmark(cursorQuery.isNull(columnIndexOrThrow) ? null : cursorQuery.getString(columnIndexOrThrow), cursorQuery.isNull(columnIndexOrThrow2) ? null : cursorQuery.getString(columnIndexOrThrow2), cursorQuery.isNull(columnIndexOrThrow3) ? null : cursorQuery.getString(columnIndexOrThrow3), cursorQuery.getLong(columnIndexOrThrow4), cursorQuery.getLong(columnIndexOrThrow5), cursorQuery.getLong(columnIndexOrThrow6)));
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

    @Override // com.amazon.podcast.biteBookmark.BiteBookmarksDao
    public LiveData<List<BiteBookmark>> getAll(final Set<String> ids) {
        StringBuilder sbNewStringBuilder = StringUtil.newStringBuilder();
        sbNewStringBuilder.append("SELECT * FROM bite_bookmarks WHERE id IN (");
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
        return this.__db.getInvalidationTracker().createLiveData(new String[]{"bite_bookmarks"}, false, new Callable<List<BiteBookmark>>() { // from class: com.amazon.podcast.biteBookmark.BiteBookmarksDao_Impl.6
            @Override // java.util.concurrent.Callable
            public List<BiteBookmark> call() throws Exception {
                Cursor cursorQuery = DBUtil.query(BiteBookmarksDao_Impl.this.__db, roomSQLiteQueryAcquire, false, null);
                try {
                    int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(cursorQuery, "id");
                    int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "podcastId");
                    int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "episodeId");
                    int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "totalDurationMilliseconds");
                    int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "progressMilliseconds");
                    int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "updatedTime");
                    ArrayList arrayList = new ArrayList(cursorQuery.getCount());
                    while (cursorQuery.moveToNext()) {
                        arrayList.add(new BiteBookmark(cursorQuery.isNull(columnIndexOrThrow) ? null : cursorQuery.getString(columnIndexOrThrow), cursorQuery.isNull(columnIndexOrThrow2) ? null : cursorQuery.getString(columnIndexOrThrow2), cursorQuery.isNull(columnIndexOrThrow3) ? null : cursorQuery.getString(columnIndexOrThrow3), cursorQuery.getLong(columnIndexOrThrow4), cursorQuery.getLong(columnIndexOrThrow5), cursorQuery.getLong(columnIndexOrThrow6)));
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

    @Override // com.amazon.podcast.biteBookmark.BiteBookmarksDao
    public List<BiteBookmark> getAllList(final Set<String> ids) {
        StringBuilder sbNewStringBuilder = StringUtil.newStringBuilder();
        sbNewStringBuilder.append("SELECT * FROM bite_bookmarks WHERE id IN (");
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
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "episodeId");
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "totalDurationMilliseconds");
            int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "progressMilliseconds");
            int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "updatedTime");
            ArrayList arrayList = new ArrayList(cursorQuery.getCount());
            while (cursorQuery.moveToNext()) {
                arrayList.add(new BiteBookmark(cursorQuery.isNull(columnIndexOrThrow) ? null : cursorQuery.getString(columnIndexOrThrow), cursorQuery.isNull(columnIndexOrThrow2) ? null : cursorQuery.getString(columnIndexOrThrow2), cursorQuery.isNull(columnIndexOrThrow3) ? null : cursorQuery.getString(columnIndexOrThrow3), cursorQuery.getLong(columnIndexOrThrow4), cursorQuery.getLong(columnIndexOrThrow5), cursorQuery.getLong(columnIndexOrThrow6)));
            }
            return arrayList;
        } finally {
            cursorQuery.close();
            roomSQLiteQueryAcquire.release();
        }
    }

    public static List<Class<?>> getRequiredConverters() {
        return Collections.emptyList();
    }
}
