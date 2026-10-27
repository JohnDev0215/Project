package com.amazon.podcast.completed;

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
import com.google.android.gms.cast.MediaTrack;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;

/* JADX INFO: loaded from: classes5.dex */
public final class CompletedDao_Impl implements CompletedDao {
    private final RoomDatabase __db;
    private final EntityInsertionAdapter<Completed> __insertionAdapterOfCompleted;
    private final SharedSQLiteStatement __preparedStmtOfDeleteAll;

    public CompletedDao_Impl(RoomDatabase __db) {
        this.__db = __db;
        this.__insertionAdapterOfCompleted = new EntityInsertionAdapter<Completed>(__db) { // from class: com.amazon.podcast.completed.CompletedDao_Impl.1
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "INSERT OR REPLACE INTO `completions` (`id`,`podcastId`,`podcastTitle`,`title`,`image`,`podcastImage`,`publishDate`,`description`,`authors`,`seasonNumber`,`completed`,`updatedTime`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
            }

            @Override // androidx.room.EntityInsertionAdapter
            public void bind(SupportSQLiteStatement supportSQLiteStatement, Completed completed) {
                if (completed.getId() == null) {
                    supportSQLiteStatement.bindNull(1);
                } else {
                    supportSQLiteStatement.bindString(1, completed.getId());
                }
                if (completed.getPodcastId() == null) {
                    supportSQLiteStatement.bindNull(2);
                } else {
                    supportSQLiteStatement.bindString(2, completed.getPodcastId());
                }
                if (completed.getPodcastTitle() == null) {
                    supportSQLiteStatement.bindNull(3);
                } else {
                    supportSQLiteStatement.bindString(3, completed.getPodcastTitle());
                }
                if (completed.getTitle() == null) {
                    supportSQLiteStatement.bindNull(4);
                } else {
                    supportSQLiteStatement.bindString(4, completed.getTitle());
                }
                if (completed.getImage() == null) {
                    supportSQLiteStatement.bindNull(5);
                } else {
                    supportSQLiteStatement.bindString(5, completed.getImage());
                }
                if (completed.getPodcastImage() == null) {
                    supportSQLiteStatement.bindNull(6);
                } else {
                    supportSQLiteStatement.bindString(6, completed.getPodcastImage());
                }
                if (completed.getPublishDate() == null) {
                    supportSQLiteStatement.bindNull(7);
                } else {
                    supportSQLiteStatement.bindString(7, completed.getPublishDate());
                }
                if (completed.getDescription() == null) {
                    supportSQLiteStatement.bindNull(8);
                } else {
                    supportSQLiteStatement.bindString(8, completed.getDescription());
                }
                if (completed.getAuthors() == null) {
                    supportSQLiteStatement.bindNull(9);
                } else {
                    supportSQLiteStatement.bindString(9, completed.getAuthors());
                }
                if (completed.getSeasonNumber() == null) {
                    supportSQLiteStatement.bindNull(10);
                } else {
                    supportSQLiteStatement.bindString(10, completed.getSeasonNumber());
                }
                supportSQLiteStatement.bindLong(11, completed.getCompleted() ? 1L : 0L);
                supportSQLiteStatement.bindLong(12, completed.getUpdatedTime());
            }
        };
        this.__preparedStmtOfDeleteAll = new SharedSQLiteStatement(__db) { // from class: com.amazon.podcast.completed.CompletedDao_Impl.2
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "DELETE FROM completions";
            }
        };
    }

    @Override // com.amazon.podcast.completed.CompletedDao
    public void insert(final Completed completed) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__insertionAdapterOfCompleted.insert(completed);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // com.amazon.podcast.completed.CompletedDao
    public LiveData<Completed> getLiveData(final String id) {
        final RoomSQLiteQuery roomSQLiteQueryAcquire = RoomSQLiteQuery.acquire("SELECT * FROM completions WHERE id = ?", 1);
        if (id == null) {
            roomSQLiteQueryAcquire.bindNull(1);
        } else {
            roomSQLiteQueryAcquire.bindString(1, id);
        }
        return this.__db.getInvalidationTracker().createLiveData(new String[]{"completions"}, false, new Callable<Completed>() { // from class: com.amazon.podcast.completed.CompletedDao_Impl.3
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // java.util.concurrent.Callable
            public Completed call() throws Exception {
                Completed completed = null;
                Cursor cursorQuery = DBUtil.query(CompletedDao_Impl.this.__db, roomSQLiteQueryAcquire, false, null);
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
                    int columnIndexOrThrow11 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "completed");
                    int columnIndexOrThrow12 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "updatedTime");
                    if (cursorQuery.moveToFirst()) {
                        completed = new Completed(cursorQuery.isNull(columnIndexOrThrow) ? null : cursorQuery.getString(columnIndexOrThrow), cursorQuery.isNull(columnIndexOrThrow2) ? null : cursorQuery.getString(columnIndexOrThrow2), cursorQuery.isNull(columnIndexOrThrow3) ? null : cursorQuery.getString(columnIndexOrThrow3), cursorQuery.isNull(columnIndexOrThrow4) ? null : cursorQuery.getString(columnIndexOrThrow4), cursorQuery.isNull(columnIndexOrThrow5) ? null : cursorQuery.getString(columnIndexOrThrow5), cursorQuery.isNull(columnIndexOrThrow6) ? null : cursorQuery.getString(columnIndexOrThrow6), cursorQuery.isNull(columnIndexOrThrow7) ? null : cursorQuery.getString(columnIndexOrThrow7), cursorQuery.isNull(columnIndexOrThrow8) ? null : cursorQuery.getString(columnIndexOrThrow8), cursorQuery.isNull(columnIndexOrThrow9) ? null : cursorQuery.getString(columnIndexOrThrow9), cursorQuery.isNull(columnIndexOrThrow10) ? null : cursorQuery.getString(columnIndexOrThrow10), cursorQuery.getInt(columnIndexOrThrow11) != 0, cursorQuery.getLong(columnIndexOrThrow12));
                    }
                    return completed;
                } finally {
                    cursorQuery.close();
                }
            }

            protected void finalize() {
                roomSQLiteQueryAcquire.release();
            }
        });
    }

    @Override // com.amazon.podcast.completed.CompletedDao
    public Completed get(String str) {
        RoomSQLiteQuery roomSQLiteQueryAcquire = RoomSQLiteQuery.acquire("SELECT * FROM completions WHERE id = ?", 1);
        if (str == null) {
            roomSQLiteQueryAcquire.bindNull(1);
        } else {
            roomSQLiteQueryAcquire.bindString(1, str);
        }
        this.__db.assertNotSuspendingTransaction();
        Completed completed = null;
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
            int columnIndexOrThrow11 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "completed");
            int columnIndexOrThrow12 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "updatedTime");
            if (cursorQuery.moveToFirst()) {
                completed = new Completed(cursorQuery.isNull(columnIndexOrThrow) ? null : cursorQuery.getString(columnIndexOrThrow), cursorQuery.isNull(columnIndexOrThrow2) ? null : cursorQuery.getString(columnIndexOrThrow2), cursorQuery.isNull(columnIndexOrThrow3) ? null : cursorQuery.getString(columnIndexOrThrow3), cursorQuery.isNull(columnIndexOrThrow4) ? null : cursorQuery.getString(columnIndexOrThrow4), cursorQuery.isNull(columnIndexOrThrow5) ? null : cursorQuery.getString(columnIndexOrThrow5), cursorQuery.isNull(columnIndexOrThrow6) ? null : cursorQuery.getString(columnIndexOrThrow6), cursorQuery.isNull(columnIndexOrThrow7) ? null : cursorQuery.getString(columnIndexOrThrow7), cursorQuery.isNull(columnIndexOrThrow8) ? null : cursorQuery.getString(columnIndexOrThrow8), cursorQuery.isNull(columnIndexOrThrow9) ? null : cursorQuery.getString(columnIndexOrThrow9), cursorQuery.isNull(columnIndexOrThrow10) ? null : cursorQuery.getString(columnIndexOrThrow10), cursorQuery.getInt(columnIndexOrThrow11) != 0, cursorQuery.getLong(columnIndexOrThrow12));
            }
            return completed;
        } finally {
            cursorQuery.close();
            roomSQLiteQueryAcquire.release();
        }
    }

    @Override // com.amazon.podcast.completed.CompletedDao
    public List<Completed> getAll(boolean z) {
        RoomSQLiteQuery roomSQLiteQueryAcquire = RoomSQLiteQuery.acquire("SELECT * FROM completions WHERE completed = ? ORDER BY updatedTime DESC", 1);
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
            int columnIndexOrThrow11 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "completed");
            int columnIndexOrThrow12 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "updatedTime");
            ArrayList arrayList = new ArrayList(cursorQuery.getCount());
            while (cursorQuery.moveToNext()) {
                arrayList.add(new Completed(cursorQuery.isNull(columnIndexOrThrow) ? null : cursorQuery.getString(columnIndexOrThrow), cursorQuery.isNull(columnIndexOrThrow2) ? null : cursorQuery.getString(columnIndexOrThrow2), cursorQuery.isNull(columnIndexOrThrow3) ? null : cursorQuery.getString(columnIndexOrThrow3), cursorQuery.isNull(columnIndexOrThrow4) ? null : cursorQuery.getString(columnIndexOrThrow4), cursorQuery.isNull(columnIndexOrThrow5) ? null : cursorQuery.getString(columnIndexOrThrow5), cursorQuery.isNull(columnIndexOrThrow6) ? null : cursorQuery.getString(columnIndexOrThrow6), cursorQuery.isNull(columnIndexOrThrow7) ? null : cursorQuery.getString(columnIndexOrThrow7), cursorQuery.isNull(columnIndexOrThrow8) ? null : cursorQuery.getString(columnIndexOrThrow8), cursorQuery.isNull(columnIndexOrThrow9) ? null : cursorQuery.getString(columnIndexOrThrow9), cursorQuery.isNull(columnIndexOrThrow10) ? null : cursorQuery.getString(columnIndexOrThrow10), cursorQuery.getInt(columnIndexOrThrow11) != 0, cursorQuery.getLong(columnIndexOrThrow12)));
            }
            return arrayList;
        } finally {
            cursorQuery.close();
            roomSQLiteQueryAcquire.release();
        }
    }

    @Override // com.amazon.podcast.completed.CompletedDao
    public List<Completed> getAll(boolean z, String str) {
        RoomSQLiteQuery roomSQLiteQueryAcquire = RoomSQLiteQuery.acquire("SELECT * FROM completions WHERE completed = ? AND podcastId = ? ORDER BY updatedTime DESC", 2);
        roomSQLiteQueryAcquire.bindLong(1, z ? 1L : 0L);
        if (str == null) {
            roomSQLiteQueryAcquire.bindNull(2);
        } else {
            roomSQLiteQueryAcquire.bindString(2, str);
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
            int columnIndexOrThrow11 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "completed");
            int columnIndexOrThrow12 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "updatedTime");
            ArrayList arrayList = new ArrayList(cursorQuery.getCount());
            while (cursorQuery.moveToNext()) {
                arrayList.add(new Completed(cursorQuery.isNull(columnIndexOrThrow) ? null : cursorQuery.getString(columnIndexOrThrow), cursorQuery.isNull(columnIndexOrThrow2) ? null : cursorQuery.getString(columnIndexOrThrow2), cursorQuery.isNull(columnIndexOrThrow3) ? null : cursorQuery.getString(columnIndexOrThrow3), cursorQuery.isNull(columnIndexOrThrow4) ? null : cursorQuery.getString(columnIndexOrThrow4), cursorQuery.isNull(columnIndexOrThrow5) ? null : cursorQuery.getString(columnIndexOrThrow5), cursorQuery.isNull(columnIndexOrThrow6) ? null : cursorQuery.getString(columnIndexOrThrow6), cursorQuery.isNull(columnIndexOrThrow7) ? null : cursorQuery.getString(columnIndexOrThrow7), cursorQuery.isNull(columnIndexOrThrow8) ? null : cursorQuery.getString(columnIndexOrThrow8), cursorQuery.isNull(columnIndexOrThrow9) ? null : cursorQuery.getString(columnIndexOrThrow9), cursorQuery.isNull(columnIndexOrThrow10) ? null : cursorQuery.getString(columnIndexOrThrow10), cursorQuery.getInt(columnIndexOrThrow11) != 0, cursorQuery.getLong(columnIndexOrThrow12)));
            }
            return arrayList;
        } finally {
            cursorQuery.close();
            roomSQLiteQueryAcquire.release();
        }
    }

    @Override // com.amazon.podcast.completed.CompletedDao
    public List<Completed> getAll(boolean z, String str, String str2) {
        RoomSQLiteQuery roomSQLiteQueryAcquire = RoomSQLiteQuery.acquire("SELECT * FROM completions WHERE completed = ? AND podcastId = ? AND seasonNumber = ? ORDER BY updatedTime DESC", 3);
        roomSQLiteQueryAcquire.bindLong(1, z ? 1L : 0L);
        if (str == null) {
            roomSQLiteQueryAcquire.bindNull(2);
        } else {
            roomSQLiteQueryAcquire.bindString(2, str);
        }
        if (str2 == null) {
            roomSQLiteQueryAcquire.bindNull(3);
        } else {
            roomSQLiteQueryAcquire.bindString(3, str2);
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
            int columnIndexOrThrow11 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "completed");
            int columnIndexOrThrow12 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "updatedTime");
            ArrayList arrayList = new ArrayList(cursorQuery.getCount());
            while (cursorQuery.moveToNext()) {
                arrayList.add(new Completed(cursorQuery.isNull(columnIndexOrThrow) ? null : cursorQuery.getString(columnIndexOrThrow), cursorQuery.isNull(columnIndexOrThrow2) ? null : cursorQuery.getString(columnIndexOrThrow2), cursorQuery.isNull(columnIndexOrThrow3) ? null : cursorQuery.getString(columnIndexOrThrow3), cursorQuery.isNull(columnIndexOrThrow4) ? null : cursorQuery.getString(columnIndexOrThrow4), cursorQuery.isNull(columnIndexOrThrow5) ? null : cursorQuery.getString(columnIndexOrThrow5), cursorQuery.isNull(columnIndexOrThrow6) ? null : cursorQuery.getString(columnIndexOrThrow6), cursorQuery.isNull(columnIndexOrThrow7) ? null : cursorQuery.getString(columnIndexOrThrow7), cursorQuery.isNull(columnIndexOrThrow8) ? null : cursorQuery.getString(columnIndexOrThrow8), cursorQuery.isNull(columnIndexOrThrow9) ? null : cursorQuery.getString(columnIndexOrThrow9), cursorQuery.isNull(columnIndexOrThrow10) ? null : cursorQuery.getString(columnIndexOrThrow10), cursorQuery.getInt(columnIndexOrThrow11) != 0, cursorQuery.getLong(columnIndexOrThrow12)));
            }
            return arrayList;
        } finally {
            cursorQuery.close();
            roomSQLiteQueryAcquire.release();
        }
    }

    @Override // com.amazon.podcast.completed.CompletedDao
    public LiveData<List<Completed>> getAllLiveData(boolean z) {
        final RoomSQLiteQuery roomSQLiteQueryAcquire = RoomSQLiteQuery.acquire("SELECT * FROM completions WHERE completed = ? ORDER BY updatedTime DESC", 1);
        roomSQLiteQueryAcquire.bindLong(1, z ? 1L : 0L);
        return this.__db.getInvalidationTracker().createLiveData(new String[]{"completions"}, false, new Callable<List<Completed>>() { // from class: com.amazon.podcast.completed.CompletedDao_Impl.4
            @Override // java.util.concurrent.Callable
            public List<Completed> call() throws Exception {
                Cursor cursorQuery = DBUtil.query(CompletedDao_Impl.this.__db, roomSQLiteQueryAcquire, false, null);
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
                    int columnIndexOrThrow11 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "completed");
                    int columnIndexOrThrow12 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "updatedTime");
                    ArrayList arrayList = new ArrayList(cursorQuery.getCount());
                    while (cursorQuery.moveToNext()) {
                        arrayList.add(new Completed(cursorQuery.isNull(columnIndexOrThrow) ? null : cursorQuery.getString(columnIndexOrThrow), cursorQuery.isNull(columnIndexOrThrow2) ? null : cursorQuery.getString(columnIndexOrThrow2), cursorQuery.isNull(columnIndexOrThrow3) ? null : cursorQuery.getString(columnIndexOrThrow3), cursorQuery.isNull(columnIndexOrThrow4) ? null : cursorQuery.getString(columnIndexOrThrow4), cursorQuery.isNull(columnIndexOrThrow5) ? null : cursorQuery.getString(columnIndexOrThrow5), cursorQuery.isNull(columnIndexOrThrow6) ? null : cursorQuery.getString(columnIndexOrThrow6), cursorQuery.isNull(columnIndexOrThrow7) ? null : cursorQuery.getString(columnIndexOrThrow7), cursorQuery.isNull(columnIndexOrThrow8) ? null : cursorQuery.getString(columnIndexOrThrow8), cursorQuery.isNull(columnIndexOrThrow9) ? null : cursorQuery.getString(columnIndexOrThrow9), cursorQuery.isNull(columnIndexOrThrow10) ? null : cursorQuery.getString(columnIndexOrThrow10), cursorQuery.getInt(columnIndexOrThrow11) != 0, cursorQuery.getLong(columnIndexOrThrow12)));
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

    @Override // com.amazon.podcast.completed.CompletedDao
    public LiveData<List<Completed>> getAllLiveData(boolean z, String str) {
        final RoomSQLiteQuery roomSQLiteQueryAcquire = RoomSQLiteQuery.acquire("SELECT * FROM completions WHERE completed = ? AND podcastId = ? ORDER BY updatedTime DESC", 2);
        roomSQLiteQueryAcquire.bindLong(1, z ? 1L : 0L);
        if (str == null) {
            roomSQLiteQueryAcquire.bindNull(2);
        } else {
            roomSQLiteQueryAcquire.bindString(2, str);
        }
        return this.__db.getInvalidationTracker().createLiveData(new String[]{"completions"}, false, new Callable<List<Completed>>() { // from class: com.amazon.podcast.completed.CompletedDao_Impl.5
            @Override // java.util.concurrent.Callable
            public List<Completed> call() throws Exception {
                Cursor cursorQuery = DBUtil.query(CompletedDao_Impl.this.__db, roomSQLiteQueryAcquire, false, null);
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
                    int columnIndexOrThrow11 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "completed");
                    int columnIndexOrThrow12 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "updatedTime");
                    ArrayList arrayList = new ArrayList(cursorQuery.getCount());
                    while (cursorQuery.moveToNext()) {
                        arrayList.add(new Completed(cursorQuery.isNull(columnIndexOrThrow) ? null : cursorQuery.getString(columnIndexOrThrow), cursorQuery.isNull(columnIndexOrThrow2) ? null : cursorQuery.getString(columnIndexOrThrow2), cursorQuery.isNull(columnIndexOrThrow3) ? null : cursorQuery.getString(columnIndexOrThrow3), cursorQuery.isNull(columnIndexOrThrow4) ? null : cursorQuery.getString(columnIndexOrThrow4), cursorQuery.isNull(columnIndexOrThrow5) ? null : cursorQuery.getString(columnIndexOrThrow5), cursorQuery.isNull(columnIndexOrThrow6) ? null : cursorQuery.getString(columnIndexOrThrow6), cursorQuery.isNull(columnIndexOrThrow7) ? null : cursorQuery.getString(columnIndexOrThrow7), cursorQuery.isNull(columnIndexOrThrow8) ? null : cursorQuery.getString(columnIndexOrThrow8), cursorQuery.isNull(columnIndexOrThrow9) ? null : cursorQuery.getString(columnIndexOrThrow9), cursorQuery.isNull(columnIndexOrThrow10) ? null : cursorQuery.getString(columnIndexOrThrow10), cursorQuery.getInt(columnIndexOrThrow11) != 0, cursorQuery.getLong(columnIndexOrThrow12)));
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

    @Override // com.amazon.podcast.completed.CompletedDao
    public LiveData<List<Completed>> getAllLiveData(boolean z, String str, String str2) {
        final RoomSQLiteQuery roomSQLiteQueryAcquire = RoomSQLiteQuery.acquire("SELECT * FROM completions WHERE completed = ? AND podcastId = ? AND seasonNumber = ? ORDER BY updatedTime DESC", 3);
        roomSQLiteQueryAcquire.bindLong(1, z ? 1L : 0L);
        if (str == null) {
            roomSQLiteQueryAcquire.bindNull(2);
        } else {
            roomSQLiteQueryAcquire.bindString(2, str);
        }
        if (str2 == null) {
            roomSQLiteQueryAcquire.bindNull(3);
        } else {
            roomSQLiteQueryAcquire.bindString(3, str2);
        }
        return this.__db.getInvalidationTracker().createLiveData(new String[]{"completions"}, false, new Callable<List<Completed>>() { // from class: com.amazon.podcast.completed.CompletedDao_Impl.6
            @Override // java.util.concurrent.Callable
            public List<Completed> call() throws Exception {
                Cursor cursorQuery = DBUtil.query(CompletedDao_Impl.this.__db, roomSQLiteQueryAcquire, false, null);
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
                    int columnIndexOrThrow11 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "completed");
                    int columnIndexOrThrow12 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "updatedTime");
                    ArrayList arrayList = new ArrayList(cursorQuery.getCount());
                    while (cursorQuery.moveToNext()) {
                        arrayList.add(new Completed(cursorQuery.isNull(columnIndexOrThrow) ? null : cursorQuery.getString(columnIndexOrThrow), cursorQuery.isNull(columnIndexOrThrow2) ? null : cursorQuery.getString(columnIndexOrThrow2), cursorQuery.isNull(columnIndexOrThrow3) ? null : cursorQuery.getString(columnIndexOrThrow3), cursorQuery.isNull(columnIndexOrThrow4) ? null : cursorQuery.getString(columnIndexOrThrow4), cursorQuery.isNull(columnIndexOrThrow5) ? null : cursorQuery.getString(columnIndexOrThrow5), cursorQuery.isNull(columnIndexOrThrow6) ? null : cursorQuery.getString(columnIndexOrThrow6), cursorQuery.isNull(columnIndexOrThrow7) ? null : cursorQuery.getString(columnIndexOrThrow7), cursorQuery.isNull(columnIndexOrThrow8) ? null : cursorQuery.getString(columnIndexOrThrow8), cursorQuery.isNull(columnIndexOrThrow9) ? null : cursorQuery.getString(columnIndexOrThrow9), cursorQuery.isNull(columnIndexOrThrow10) ? null : cursorQuery.getString(columnIndexOrThrow10), cursorQuery.getInt(columnIndexOrThrow11) != 0, cursorQuery.getLong(columnIndexOrThrow12)));
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
