package com.byd.vrdownload.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;
import android.util.Log;
import android.util.SparseArray;
import com.byd.vrdownload.App;
import com.byd.vrdownload.model.LanguageUpdate;
import com.byd.vrdownload.utils.FileUtils;
import com.byd.vrdownload.utils.LogUtils;
import com.liulishuo.filedownloader.FileDownloader;
import com.liulishuo.filedownloader.model.FileDownloadModel;
import com.liulishuo.filedownloader.util.FileDownloadUtils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class LanguageTaskDatabase implements IDatabase<LanguageUpdate> {
    public static final String TABLE_NAME = "languagetasksmanger";
    private static final String TAG = "LanguageTaskDatabase";
    private final SQLiteDatabase db;
    LanguageDBOpenHelper openHelper;
    private final List<LanguageUpdate> tasks = new ArrayList();
    private final HashMap<String, LanguageUpdate> taskMap = new HashMap<>();
    private final SparseArray<LanguageUpdate> taskIdArray = new SparseArray<>();

    public LanguageTaskDatabase() {
        Log.e(TAG, "LanguageTaskDatabase: init");
        this.db = new LanguageDBOpenHelper(App.CONTEXT).getWritableDatabase();
        DaoUtils.maintainDatabase(getMaintainer());
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.byd.vrdownload.dao.IDatabase
    public LanguageUpdate find(int i) {
        try {
            Cursor cursorRawQuery = this.db.rawQuery(FileDownloadUtils.formatString("SELECT * FROM %s WHERE %s = ?", TABLE_NAME, "id"), new String[]{Integer.toString(i)});
            try {
                if (cursorRawQuery.moveToNext()) {
                    LanguageUpdate languageUpdateCreateFromCursor = createFromCursor(cursorRawQuery);
                    if (cursorRawQuery != null) {
                        cursorRawQuery.close();
                    }
                    return languageUpdateCreateFromCursor;
                }
                if (cursorRawQuery == null) {
                    return null;
                }
                cursorRawQuery.close();
                return null;
            } finally {
            }
        } catch (Exception e) {
            Log.e(TAG, "find: " + e.getMessage());
            return null;
        }
        Log.e(TAG, "find: " + e.getMessage());
        return null;
    }

    @Override // com.byd.vrdownload.dao.IDatabase
    public List<LanguageUpdate> getAll() {
        return this.tasks;
    }

    @Override // com.byd.vrdownload.dao.IDatabase
    public HashMap<String, LanguageUpdate> getAllMap() {
        return this.taskMap;
    }

    @Override // com.byd.vrdownload.dao.IDatabase
    public SparseArray<LanguageUpdate> getAllIdMap() {
        return this.taskIdArray;
    }

    @Override // com.byd.vrdownload.dao.IDatabase
    public Maintainer<LanguageUpdate> getMaintainer() {
        return new LanguageMaintainer();
    }

    @Override // com.byd.vrdownload.dao.IDatabase
    public void insert(LanguageUpdate languageUpdate) {
        this.db.insert(TABLE_NAME, null, languageUpdate.toContentValues());
        this.tasks.remove(languageUpdate);
        this.tasks.add(languageUpdate);
        Log.i(TAG, "insert: languageUpdate=" + languageUpdate.toContentValues());
    }

    @Override // com.byd.vrdownload.dao.IDatabase
    public void update(LanguageUpdate languageUpdate) {
        if (languageUpdate == null) {
            Log.w(TAG, "update but model == null!");
            return;
        }
        if (find(languageUpdate.getId()) != null) {
            ContentValues contentValues = languageUpdate.toContentValues();
            Log.e(TAG, "update: LanguageUpdate=" + languageUpdate);
            this.db.update(TABLE_NAME, contentValues, "id = ? ", new String[]{String.valueOf(languageUpdate.getId())});
            Log.e(TAG, "update: " + this.db);
            return;
        }
        insert(languageUpdate);
    }

    @Override // com.byd.vrdownload.dao.IDatabase
    public boolean remove(int i) {
        this.tasks.remove(find(i));
        Log.i(TAG, "remove:id=" + i + ", ret=" + this.db.delete(TABLE_NAME, "id = ? ", new String[]{String.valueOf(i)}));
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static LanguageUpdate createFromCursor(Cursor cursor) {
        LanguageUpdate languageUpdate = new LanguageUpdate();
        languageUpdate.setId(cursor.getInt(cursor.getColumnIndex("id")));
        languageUpdate.setLanguage(cursor.getString(cursor.getColumnIndex(LanguageUpdate.LANGUAGE)));
        languageUpdate.setApkVersion(cursor.getString(cursor.getColumnIndex(LanguageUpdate.APK_VERSION)));
        languageUpdate.setCurrentVersion(cursor.getString(cursor.getColumnIndex(LanguageUpdate.CURRENT_VERSION)));
        languageUpdate.setDownloadingApkVersion(cursor.getString(cursor.getColumnIndex(LanguageUpdate.DOWNLOADING_APK_VERSION)));
        languageUpdate.setDownloadingPkgVersion(cursor.getString(cursor.getColumnIndex(LanguageUpdate.DOWNLOADING_PKG_VERSION)));
        languageUpdate.setDownloadingPkgKey(cursor.getString(cursor.getColumnIndex(LanguageUpdate.DOWNLOADING_PKG_KEY)));
        languageUpdate.setDownloadingPkgUrl(cursor.getString(cursor.getColumnIndex(LanguageUpdate.DOWNLOADING_URL)));
        languageUpdate.setDownloadingPkgMd5(cursor.getString(cursor.getColumnIndex(LanguageUpdate.DOWNLOADING_PKGMd5)));
        languageUpdate.setDownloadingPkgSize(cursor.getString(cursor.getColumnIndex(LanguageUpdate.DOWNLOADING_PKG_SIZE)));
        languageUpdate.setDownloadingPkgDesc(cursor.getString(cursor.getColumnIndex(LanguageUpdate.DOWNLOADING_PKG_DESC)));
        languageUpdate.setLatestApkVersion(cursor.getString(cursor.getColumnIndex(LanguageUpdate.LATEST_APK_VERSION)));
        languageUpdate.setLatestPkgVersion(cursor.getString(cursor.getColumnIndex(LanguageUpdate.LATEST_PKG_VERSION)));
        languageUpdate.setLatestPkgKey(cursor.getString(cursor.getColumnIndex(LanguageUpdate.LATEST_PKG_KEY)));
        languageUpdate.setLatestPkgUrl(cursor.getString(cursor.getColumnIndex(LanguageUpdate.LATEST_URL)));
        languageUpdate.setLatestPkgMd5(cursor.getString(cursor.getColumnIndex(LanguageUpdate.LATEST_PKGMd5)));
        languageUpdate.setLatestPkgSize(cursor.getString(cursor.getColumnIndex(LanguageUpdate.LATEST_PKG_SIZE)));
        languageUpdate.setLatestPkgDesc(cursor.getString(cursor.getColumnIndex(LanguageUpdate.LATEST_PKG_DESC)));
        languageUpdate.setDownloadingPatchPkgVersion(cursor.getString(cursor.getColumnIndex(LanguageUpdate.DOWNLOADING_PATCH_PKG_VERSION)));
        languageUpdate.setLatestPatchPkgKey(cursor.getString(cursor.getColumnIndex(LanguageUpdate.LATEST_PATCH_PKG_KEY)));
        languageUpdate.setLatestPatchPkgVersion(cursor.getString(cursor.getColumnIndex(LanguageUpdate.LATEST_PATCH_PKG_VERSION)));
        languageUpdate.setLatestPatchPkgSize(cursor.getString(cursor.getColumnIndex(LanguageUpdate.LATEST_PATCH_PKG_SIZE)));
        languageUpdate.setLatestPatchPkgMd5(cursor.getString(cursor.getColumnIndex(LanguageUpdate.LATEST_PATCH_PKG_MD5)));
        languageUpdate.setDownloadingPath(cursor.getString(cursor.getColumnIndex(LanguageUpdate.DOWNLOADING_PATH)));
        return languageUpdate;
    }

    public class LanguageMaintainer implements Maintainer<LanguageUpdate> {
        private MaintainerIterator currentIterator;
        private final SparseArray<FileDownloadModel> needChangeIdList = new SparseArray<>();

        @Override // com.byd.vrdownload.dao.Maintainer
        public void onRemovedInvalidData(LanguageUpdate languageUpdate) {
        }

        LanguageMaintainer() {
        }

        @Override // java.lang.Iterable
        public Iterator<LanguageUpdate> iterator() {
            MaintainerIterator maintainerIterator = LanguageTaskDatabase.this.new MaintainerIterator();
            this.currentIterator = maintainerIterator;
            return maintainerIterator;
        }

        @Override // com.byd.vrdownload.dao.Maintainer
        public void onFinishMaintain() {
            MaintainerIterator maintainerIterator = this.currentIterator;
            if (maintainerIterator != null) {
                maintainerIterator.onFinishMaintain();
            }
        }

        @Override // com.byd.vrdownload.dao.Maintainer
        public void onRefreshedValidData(LanguageUpdate languageUpdate) {
            LogUtils.i(LanguageTaskDatabase.TAG, "onRefreshedValidData: " + languageUpdate);
            LanguageTaskDatabase.this.tasks.remove(languageUpdate);
            String filePathOld = FileUtils.getFilePathOld(languageUpdate);
            if (FileUtils.isFileExists(filePathOld)) {
                FileUtils.rename(filePathOld, FileUtils.getFilePath(languageUpdate));
            }
            if (FileUtils.isFileExists(filePathOld + ".temp")) {
                FileUtils.rename(filePathOld + ".temp", FileUtils.getFilePathTemp(languageUpdate));
            }
            if (FileUtils.isFileExists(FileUtils.getFilePath(languageUpdate)) || FileUtils.isFileExists(FileUtils.getFilePathTemp(languageUpdate))) {
                LogUtils.i(LanguageTaskDatabase.TAG, "onRefreshedValidData:this task is valid, filepath=" + languageUpdate.getDownloadingPath());
                LanguageTaskDatabase.this.tasks.add(languageUpdate);
                LanguageTaskDatabase.this.taskMap.put(languageUpdate.getLanguage(), languageUpdate);
                LanguageTaskDatabase.this.taskIdArray.put(languageUpdate.getId(), languageUpdate);
                return;
            }
            LogUtils.i(LanguageTaskDatabase.TAG, "onRefreshedValidData: remove task because file is not exist ,path=" + languageUpdate.getDownloadingPath());
            LanguageTaskDatabase.this.remove(languageUpdate.getId());
            FileDownloader.getImpl().clear(languageUpdate.getId(), languageUpdate.getDownloadingPath());
        }
    }

    public class MaintainerIterator implements Iterator<LanguageUpdate> {
        private final Cursor c;
        private int currentId;
        private final List<Integer> needRemoveId = new ArrayList();

        MaintainerIterator() {
            Log.i(LanguageTaskDatabase.TAG, "MaintainerIterator: " + LanguageTaskDatabase.this.db);
            Cursor cursorRawQuery = LanguageTaskDatabase.this.db.rawQuery("SELECT * FROM languagetasksmanger", null);
            this.c = cursorRawQuery;
            Log.i(LanguageTaskDatabase.TAG, "MaintainerIterator: c=" + cursorRawQuery);
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return this.c.moveToNext();
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // java.util.Iterator
        public LanguageUpdate next() {
            LanguageUpdate languageUpdateCreateFromCursor = LanguageTaskDatabase.createFromCursor(this.c);
            this.currentId = languageUpdateCreateFromCursor.getId();
            return languageUpdateCreateFromCursor;
        }

        @Override // java.util.Iterator
        public void remove() {
            this.needRemoveId.add(Integer.valueOf(this.currentId));
        }

        void onFinishMaintain() {
            this.c.close();
            if (this.needRemoveId.isEmpty()) {
                return;
            }
            String strJoin = TextUtils.join(", ", this.needRemoveId);
            Log.i(LanguageTaskDatabase.TAG, "onFinishMaintain: " + LanguageTaskDatabase.this.db);
            LanguageTaskDatabase.this.db.execSQL(FileDownloadUtils.formatString("DELETE FROM %s WHERE %s IN (%s);", LanguageTaskDatabase.TABLE_NAME, "id", strJoin));
        }
    }
}
