package com.amazon.podcast.categoryFollow;

import android.database.Cursor;
import androidx.lifecycle.LiveData;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;

/* JADX INFO: loaded from: classes5.dex */
public final class CategoryFollowsDao_Impl implements CategoryFollowsDao {
    private final RoomDatabase __db;
    private final EntityInsertionAdapter<CategoryFollow> __insertionAdapterOfCategoryFollow;
    private final SharedSQLiteStatement __preparedStmtOfDeleteAll;

    public CategoryFollowsDao_Impl(RoomDatabase __db) {
        this.__db = __db;
        this.__insertionAdapterOfCategoryFollow = new EntityInsertionAdapter<CategoryFollow>(__db) { // from class: com.amazon.podcast.categoryFollow.CategoryFollowsDao_Impl.1
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "INSERT OR REPLACE INTO `category_follows` (`id`,`title`,`followed`,`updatedTime`) VALUES (?,?,?,?)";
            }

            @Override // androidx.room.EntityInsertionAdapter
            public void bind(SupportSQLiteStatement supportSQLiteStatement, CategoryFollow categoryFollow) {
                if (categoryFollow.getId() == null) {
                    supportSQLiteStatement.bindNull(1);
                } else {
                    supportSQLiteStatement.bindString(1, categoryFollow.getId());
                }
                if (categoryFollow.getTitle() == null) {
                    supportSQLiteStatement.bindNull(2);
                } else {
                    supportSQLiteStatement.bindString(2, categoryFollow.getTitle());
                }
                supportSQLiteStatement.bindLong(3, categoryFollow.isFollowed() ? 1L : 0L);
                supportSQLiteStatement.bindLong(4, categoryFollow.getUpdatedTime());
            }
        };
        this.__preparedStmtOfDeleteAll = new SharedSQLiteStatement(__db) { // from class: com.amazon.podcast.categoryFollow.CategoryFollowsDao_Impl.2
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "DELETE FROM category_follows";
            }
        };
    }

    @Override // com.amazon.podcast.categoryFollow.CategoryFollowsDao
    public void insert(final CategoryFollow categoryFollow) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__insertionAdapterOfCategoryFollow.insert(categoryFollow);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // com.amazon.podcast.categoryFollow.CategoryFollowsDao
    public LiveData<CategoryFollow> getLiveData(final String id) {
        final RoomSQLiteQuery roomSQLiteQueryAcquire = RoomSQLiteQuery.acquire("SELECT * FROM category_follows WHERE id = ?", 1);
        if (id == null) {
            roomSQLiteQueryAcquire.bindNull(1);
        } else {
            roomSQLiteQueryAcquire.bindString(1, id);
        }
        return this.__db.getInvalidationTracker().createLiveData(new String[]{"category_follows"}, false, new Callable<CategoryFollow>() { // from class: com.amazon.podcast.categoryFollow.CategoryFollowsDao_Impl.3
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // java.util.concurrent.Callable
            public CategoryFollow call() throws Exception {
                CategoryFollow categoryFollow = null;
                Cursor cursorQuery = DBUtil.query(CategoryFollowsDao_Impl.this.__db, roomSQLiteQueryAcquire, false, null);
                try {
                    int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(cursorQuery, "id");
                    int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "title");
                    int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "followed");
                    int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "updatedTime");
                    if (cursorQuery.moveToFirst()) {
                        categoryFollow = new CategoryFollow(cursorQuery.isNull(columnIndexOrThrow) ? null : cursorQuery.getString(columnIndexOrThrow), cursorQuery.isNull(columnIndexOrThrow2) ? null : cursorQuery.getString(columnIndexOrThrow2), cursorQuery.getInt(columnIndexOrThrow3) != 0, cursorQuery.getLong(columnIndexOrThrow4));
                    }
                    return categoryFollow;
                } finally {
                    cursorQuery.close();
                }
            }

            protected void finalize() {
                roomSQLiteQueryAcquire.release();
            }
        });
    }

    @Override // com.amazon.podcast.categoryFollow.CategoryFollowsDao
    public CategoryFollow get(String str) {
        RoomSQLiteQuery roomSQLiteQueryAcquire = RoomSQLiteQuery.acquire("SELECT * FROM category_follows WHERE id = ?", 1);
        if (str == null) {
            roomSQLiteQueryAcquire.bindNull(1);
        } else {
            roomSQLiteQueryAcquire.bindString(1, str);
        }
        this.__db.assertNotSuspendingTransaction();
        CategoryFollow categoryFollow = null;
        Cursor cursorQuery = DBUtil.query(this.__db, roomSQLiteQueryAcquire, false, null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(cursorQuery, "id");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "title");
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "followed");
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "updatedTime");
            if (cursorQuery.moveToFirst()) {
                categoryFollow = new CategoryFollow(cursorQuery.isNull(columnIndexOrThrow) ? null : cursorQuery.getString(columnIndexOrThrow), cursorQuery.isNull(columnIndexOrThrow2) ? null : cursorQuery.getString(columnIndexOrThrow2), cursorQuery.getInt(columnIndexOrThrow3) != 0, cursorQuery.getLong(columnIndexOrThrow4));
            }
            return categoryFollow;
        } finally {
            cursorQuery.close();
            roomSQLiteQueryAcquire.release();
        }
    }

    @Override // com.amazon.podcast.categoryFollow.CategoryFollowsDao
    public LiveData<List<CategoryFollow>> getAll(boolean z) {
        final RoomSQLiteQuery roomSQLiteQueryAcquire = RoomSQLiteQuery.acquire("SELECT * FROM category_follows WHERE followed = ? ORDER BY updatedTime DESC", 1);
        roomSQLiteQueryAcquire.bindLong(1, z ? 1L : 0L);
        return this.__db.getInvalidationTracker().createLiveData(new String[]{"category_follows"}, false, new Callable<List<CategoryFollow>>() { // from class: com.amazon.podcast.categoryFollow.CategoryFollowsDao_Impl.4
            @Override // java.util.concurrent.Callable
            public List<CategoryFollow> call() throws Exception {
                Cursor cursorQuery = DBUtil.query(CategoryFollowsDao_Impl.this.__db, roomSQLiteQueryAcquire, false, null);
                try {
                    int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(cursorQuery, "id");
                    int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "title");
                    int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "followed");
                    int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "updatedTime");
                    ArrayList arrayList = new ArrayList(cursorQuery.getCount());
                    while (cursorQuery.moveToNext()) {
                        arrayList.add(new CategoryFollow(cursorQuery.isNull(columnIndexOrThrow) ? null : cursorQuery.getString(columnIndexOrThrow), cursorQuery.isNull(columnIndexOrThrow2) ? null : cursorQuery.getString(columnIndexOrThrow2), cursorQuery.getInt(columnIndexOrThrow3) != 0, cursorQuery.getLong(columnIndexOrThrow4)));
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
