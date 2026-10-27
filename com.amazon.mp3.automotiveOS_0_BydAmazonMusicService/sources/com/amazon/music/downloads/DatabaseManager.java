package com.amazon.music.downloads;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.Collection;
import java.util.Set;

/* JADX INFO: loaded from: classes4.dex */
class DatabaseManager {
    private static DatabaseManager sInstance;
    private final SQLiteDatabase mReadOnlyDbInstance;
    private final SQLiteDatabase mWritableDbInstance;
    private final RequestItemTable mRequestItemTable = new RequestItemTable();
    private final GroupTable mGroupTable = new GroupTable();
    private final ItemTable mItemTable = new ItemTable();

    private DatabaseManager(Context context) {
        OpenHelper openHelper = new OpenHelper(context);
        this.mWritableDbInstance = openHelper.getWritableDatabase();
        this.mReadOnlyDbInstance = openHelper.getReadableDatabase();
    }

    public static DatabaseManager getInstance(Context context) {
        if (sInstance == null) {
            synchronized (DatabaseManager.class) {
                if (sInstance == null) {
                    sInstance = new DatabaseManager(context);
                }
            }
        }
        return sInstance;
    }

    class OpenHelper extends SQLiteOpenHelper {
        OpenHelper(Context context) {
            super(context, "DMMDownloadManager.db", (SQLiteDatabase.CursorFactory) null, 2);
        }

        @Override // android.database.sqlite.SQLiteOpenHelper
        public void onCreate(SQLiteDatabase sQLiteDatabase) {
            sQLiteDatabase.beginTransaction();
            try {
                DatabaseManager.this.mRequestItemTable.createTable(sQLiteDatabase);
                DatabaseManager.this.mGroupTable.createTable(sQLiteDatabase);
                DatabaseManager.this.mItemTable.createTable(sQLiteDatabase);
                sQLiteDatabase.setTransactionSuccessful();
            } finally {
                sQLiteDatabase.endTransaction();
            }
        }

        @Override // android.database.sqlite.SQLiteOpenHelper
        public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
            if (i2 == 2) {
                upgradeToVersion2(sQLiteDatabase);
            }
        }

        private void upgradeToVersion2(SQLiteDatabase sQLiteDatabase) {
            sQLiteDatabase.execSQL("ALTER TABLE ItemTable ADD COLUMN download_reason TEXT DEFAULT 'USERINITIATED'");
            sQLiteDatabase.execSQL("ALTER TABLE ItemTable ADD COLUMN download_priority TEXT DEFAULT 'USERINITIATED'");
            sQLiteDatabase.execSQL("ALTER TABLE ItemTable ADD COLUMN destination_directory TEXT DEFAULT ' '");
            sQLiteDatabase.execSQL("ALTER TABLE ItemTable ADD COLUMN original_download_state INTEGER DEFAULT NULL");
        }
    }

    public Set<RequestItem> loadRequestItems() {
        return this.mRequestItemTable.loadRequestItems(this.mReadOnlyDbInstance);
    }

    public RequestItem generateRequestItem(String str, ModifiableGroup modifiableGroup) {
        if (str == null || modifiableGroup == null) {
            return null;
        }
        return this.mRequestItemTable.generateRequestItem(str, modifiableGroup, this.mWritableDbInstance);
    }

    public RequestItem generateRequestItem(String str, ModifiableItem modifiableItem) {
        if (str == null || modifiableItem == null) {
            return null;
        }
        return this.mRequestItemTable.generateRequestItem(str, modifiableItem, this.mReadOnlyDbInstance);
    }

    public void removeRequestItem(String str) {
        if (str == null) {
            return;
        }
        this.mRequestItemTable.removeRequestItem(str, this.mWritableDbInstance);
    }

    public ModifiableItem loadDownloadItem(String str) {
        if (str == null) {
            return null;
        }
        return this.mItemTable.load(str, this.mReadOnlyDbInstance);
    }

    public void addDownloadItem(String str, ModifiableItem modifiableItem) {
        if (str == null || modifiableItem == null) {
            return;
        }
        this.mItemTable.add(str, modifiableItem, this.mWritableDbInstance);
    }

    public void removeDownloadItems(Collection<String> collection) {
        if (collection == null) {
            return;
        }
        this.mItemTable.remove(collection, this.mWritableDbInstance);
    }

    public void updateItemState(String str, DownloadState downloadState) {
        if (str == null || downloadState == null) {
            return;
        }
        this.mItemTable.updateDownloadState(str, downloadState, this.mWritableDbInstance);
    }

    public void updateItemErrorReason(Collection<String> collection, ErrorReason errorReason) {
        if (collection == null || errorReason == null) {
            return;
        }
        this.mItemTable.updateErrorReason(collection, errorReason, this.mWritableDbInstance);
    }

    public ModifiableGroup loadDownloadGroup(String str) {
        if (str == null) {
            return null;
        }
        return this.mGroupTable.load(str, this.mItemTable, this.mReadOnlyDbInstance);
    }

    public void addDownloadGroup(String str, ModifiableGroup modifiableGroup) {
        if (str == null || modifiableGroup == null) {
            return;
        }
        this.mGroupTable.add(str, modifiableGroup, this.mItemTable, this.mWritableDbInstance);
    }

    public void removeDownloadGroups(Collection<String> collection) {
        if (collection == null) {
            return;
        }
        this.mGroupTable.remove(collection, this.mWritableDbInstance);
    }

    public void updateGroupState(String str, DownloadState downloadState) {
        if (str == null || downloadState == null) {
            return;
        }
        this.mGroupTable.updateDownloadState(str, downloadState, this.mWritableDbInstance);
    }

    public void updateGroupErrorReason(Collection<String> collection, ErrorReason errorReason) {
        if (collection == null || errorReason == null) {
            return;
        }
        this.mGroupTable.updateErrorReason(collection, errorReason, this.mWritableDbInstance);
    }
}
