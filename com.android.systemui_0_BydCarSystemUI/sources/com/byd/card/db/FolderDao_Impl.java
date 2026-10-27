package com.byd.card.db;

import android.database.Cursor;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.byd.card.bean.Folder;
import com.byd.card.bean.ShortcutInfo;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class FolderDao_Impl implements FolderDao {
    private final RoomDatabase __db;
    private final EntityDeletionOrUpdateAdapter<Folder> __deletionAdapterOfFolder;
    private final EntityInsertionAdapter<Folder> __insertionAdapterOfFolder;
    private final EntityDeletionOrUpdateAdapter<Folder> __updateAdapterOfFolder;

    public FolderDao_Impl(RoomDatabase __db) {
        this.__db = __db;
        this.__insertionAdapterOfFolder = new EntityInsertionAdapter<Folder>(__db) { // from class: com.byd.card.db.FolderDao_Impl.1
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "INSERT OR ABORT INTO `folder` (`folder_id`,`title`) VALUES (nullif(?, 0),?)";
            }

            @Override // androidx.room.EntityInsertionAdapter
            public void bind(SupportSQLiteStatement stmt, Folder value) {
                stmt.bindLong(1, value.folder_id);
                if (value.title == null) {
                    stmt.bindNull(2);
                } else {
                    stmt.bindString(2, value.title);
                }
            }
        };
        this.__deletionAdapterOfFolder = new EntityDeletionOrUpdateAdapter<Folder>(__db) { // from class: com.byd.card.db.FolderDao_Impl.2
            @Override // androidx.room.EntityDeletionOrUpdateAdapter, androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "DELETE FROM `folder` WHERE `folder_id` = ?";
            }

            @Override // androidx.room.EntityDeletionOrUpdateAdapter
            public void bind(SupportSQLiteStatement stmt, Folder value) {
                stmt.bindLong(1, value.folder_id);
            }
        };
        this.__updateAdapterOfFolder = new EntityDeletionOrUpdateAdapter<Folder>(__db) { // from class: com.byd.card.db.FolderDao_Impl.3
            @Override // androidx.room.EntityDeletionOrUpdateAdapter, androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "UPDATE OR REPLACE `folder` SET `folder_id` = ?,`title` = ? WHERE `folder_id` = ?";
            }

            @Override // androidx.room.EntityDeletionOrUpdateAdapter
            public void bind(SupportSQLiteStatement stmt, Folder value) {
                stmt.bindLong(1, value.folder_id);
                if (value.title == null) {
                    stmt.bindNull(2);
                } else {
                    stmt.bindString(2, value.title);
                }
                stmt.bindLong(3, value.folder_id);
            }
        };
    }

    @Override // com.byd.card.db.FolderDao
    public void insertFolder(final Folder... folder) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__insertionAdapterOfFolder.insert(folder);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // com.byd.card.db.FolderDao
    public void insertFolderList(final List<Folder> folderList) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__insertionAdapterOfFolder.insert(folderList);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // com.byd.card.db.FolderDao
    public void deleteFolder(final Folder... folder) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__deletionAdapterOfFolder.handleMultiple(folder);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // com.byd.card.db.FolderDao
    public void deleteFolderList(final List<Folder> folderList) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__deletionAdapterOfFolder.handleMultiple(folderList);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // com.byd.card.db.FolderDao
    public void updateFolder(final Folder... folder) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__updateAdapterOfFolder.handleMultiple(folder);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // com.byd.card.db.FolderDao
    public void updateFolderList(final List<Folder> folderList) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__updateAdapterOfFolder.handleMultiple(folderList);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // com.byd.card.db.FolderDao
    public List<Folder> getFolders() {
        RoomSQLiteQuery roomSQLiteQueryAcquire = RoomSQLiteQuery.acquire("SELECT * FROM folder", 0);
        this.__db.assertNotSuspendingTransaction();
        Cursor cursorQuery = DBUtil.query(this.__db, roomSQLiteQueryAcquire, false, null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(cursorQuery, "folder_id");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(cursorQuery, ShortcutInfo.Column.COLUMN_TITLE);
            ArrayList arrayList = new ArrayList(cursorQuery.getCount());
            while (cursorQuery.moveToNext()) {
                Folder folder = new Folder(cursorQuery.getString(columnIndexOrThrow2));
                folder.folder_id = cursorQuery.getInt(columnIndexOrThrow);
                arrayList.add(folder);
            }
            return arrayList;
        } finally {
            cursorQuery.close();
            roomSQLiteQueryAcquire.release();
        }
    }

    @Override // com.byd.card.db.FolderDao
    public int getMaxFolderId() {
        RoomSQLiteQuery roomSQLiteQueryAcquire = RoomSQLiteQuery.acquire("SELECT max(folder_id) AS Maximum FROM folder", 0);
        this.__db.assertNotSuspendingTransaction();
        Cursor cursorQuery = DBUtil.query(this.__db, roomSQLiteQueryAcquire, false, null);
        try {
            return cursorQuery.moveToFirst() ? cursorQuery.getInt(0) : 0;
        } finally {
            cursorQuery.close();
            roomSQLiteQueryAcquire.release();
        }
    }

    @Override // com.byd.card.db.FolderDao
    public Folder getFolderByTitle(final String title) {
        RoomSQLiteQuery roomSQLiteQueryAcquire = RoomSQLiteQuery.acquire("SELECT * FROM folder WHERE title=?", 1);
        if (title == null) {
            roomSQLiteQueryAcquire.bindNull(1);
        } else {
            roomSQLiteQueryAcquire.bindString(1, title);
        }
        this.__db.assertNotSuspendingTransaction();
        Folder folder = null;
        Cursor cursorQuery = DBUtil.query(this.__db, roomSQLiteQueryAcquire, false, null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(cursorQuery, "folder_id");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(cursorQuery, ShortcutInfo.Column.COLUMN_TITLE);
            if (cursorQuery.moveToFirst()) {
                Folder folder2 = new Folder(cursorQuery.getString(columnIndexOrThrow2));
                folder2.folder_id = cursorQuery.getInt(columnIndexOrThrow);
                folder = folder2;
            }
            return folder;
        } finally {
            cursorQuery.close();
            roomSQLiteQueryAcquire.release();
        }
    }

    @Override // com.byd.card.db.FolderDao
    public Folder getFolderById(final int folderId) {
        RoomSQLiteQuery roomSQLiteQueryAcquire = RoomSQLiteQuery.acquire("SELECT * FROM folder WHERE folder_id=?", 1);
        roomSQLiteQueryAcquire.bindLong(1, folderId);
        this.__db.assertNotSuspendingTransaction();
        Folder folder = null;
        Cursor cursorQuery = DBUtil.query(this.__db, roomSQLiteQueryAcquire, false, null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(cursorQuery, "folder_id");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(cursorQuery, ShortcutInfo.Column.COLUMN_TITLE);
            if (cursorQuery.moveToFirst()) {
                Folder folder2 = new Folder(cursorQuery.getString(columnIndexOrThrow2));
                folder2.folder_id = cursorQuery.getInt(columnIndexOrThrow);
                folder = folder2;
            }
            return folder;
        } finally {
            cursorQuery.close();
            roomSQLiteQueryAcquire.release();
        }
    }
}
