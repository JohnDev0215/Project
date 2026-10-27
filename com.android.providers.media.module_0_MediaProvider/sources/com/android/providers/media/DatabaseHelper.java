package com.android.providers.media;

import android.content.ContentProviderClient;
import android.content.ContentValues;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.ProviderInfo;
import android.database.ContentObserver;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.SystemClock;
import android.os.Trace;
import android.provider.MediaStore;
import android.system.ErrnoException;
import android.system.Os;
import android.system.OsConstants;
import android.util.ArrayMap;
import android.util.ArraySet;
import android.util.Log;
import android.util.SparseArray;
import com.android.providers.media.util.BackgroundThread;
import com.android.providers.media.util.DatabaseUtils;
import com.android.providers.media.util.FileUtils;
import com.android.providers.media.util.ForegroundThread;
import com.android.providers.media.util.Logging;
import com.android.providers.media.util.MimeUtils;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.function.Function;
import java.util.function.UnaryOperator;
import java.util.regex.Matcher;

/* JADX INFO: loaded from: classes.dex */
public class DatabaseHelper extends SQLiteOpenHelper implements AutoCloseable {
    public static final String CURRENT_GENERATION_CLAUSE = "SELECT generation FROM local_metadata";
    static final String EXTERNAL_DATABASE_NAME = "external.db";
    static final String INTERNAL_DATABASE_NAME = "internal.db";
    private static final long RENAME_TIMEOUT = 10000;
    static final int VERSION_J = 509;
    static final int VERSION_K = 700;
    static final int VERSION_L = 700;
    static final int VERSION_LATEST = 1115;
    static final int VERSION_M = 800;
    static final int VERSION_N = 800;
    static final int VERSION_O = 800;
    static final int VERSION_P = 900;
    static final int VERSION_Q = 1023;
    static final int VERSION_R = 1115;
    private static final String XATTR_UUID = "user.uuid";
    private static final ArraySet<String> sMigrateColumns = new ArraySet<>();
    final Class<? extends Annotation> mColumnAnnotation;
    final Context mContext;
    final boolean mEarlyUpgrade;
    final OnFilesChangeListener mFilesListener;
    final Set<String> mFilterVolumeNames;
    final UnaryOperator<String> mIdGenerator;
    final boolean mInternal;
    final boolean mLegacyProvider;
    boolean mMigrateFromLegacy;
    final OnLegacyMigrationListener mMigrationListener;
    final String mName;
    private final ArrayMap<Class<?>, ArrayMap<String, String>> mProjectionMapCache;
    long mScanStartTime;
    long mScanStopTime;
    final OnSchemaChangeListener mSchemaListener;
    private final ReentrantReadWriteLock mSchemaLock;
    private final ThreadLocal<TransactionState> mTransactionState;
    final int mVersion;
    final String mVolumeName;

    public interface OnFilesChangeListener {
        void onDelete(DatabaseHelper databaseHelper, String str, long j, int i, boolean z, String str2, String str3);

        void onInsert(DatabaseHelper databaseHelper, String str, long j, int i, boolean z);

        void onUpdate(DatabaseHelper databaseHelper, String str, long j, int i, boolean z, long j2, int i2, boolean z2, String str2, String str3, String str4);
    }

    public interface OnLegacyMigrationListener {
        void onFinished(ContentProviderClient contentProviderClient, String str);

        void onProgress(ContentProviderClient contentProviderClient, String str, long j, long j2);

        void onStarted(ContentProviderClient contentProviderClient, String str);
    }

    public interface OnSchemaChangeListener {
        void onSchemaChange(String str, int i, int i2, long j, long j2);
    }

    @Deprecated
    public static int getDatabaseVersion(Context context) {
        return 1115;
    }

    public DatabaseHelper(Context context, String str, boolean z, boolean z2, boolean z3, Class<? extends Annotation> cls, OnSchemaChangeListener onSchemaChangeListener, OnFilesChangeListener onFilesChangeListener, OnLegacyMigrationListener onLegacyMigrationListener, UnaryOperator<String> unaryOperator) {
        this(context, str, getDatabaseVersion(context), z, z2, z3, cls, onSchemaChangeListener, onFilesChangeListener, onLegacyMigrationListener, unaryOperator);
    }

    public DatabaseHelper(Context context, String str, int i, boolean z, boolean z2, boolean z3, Class<? extends Annotation> cls, OnSchemaChangeListener onSchemaChangeListener, OnFilesChangeListener onFilesChangeListener, OnLegacyMigrationListener onLegacyMigrationListener, UnaryOperator<String> unaryOperator) {
        super(context, str, (SQLiteDatabase.CursorFactory) null, i);
        this.mFilterVolumeNames = new ArraySet();
        this.mSchemaLock = new ReentrantReadWriteLock();
        this.mProjectionMapCache = new ArrayMap<>();
        this.mTransactionState = new ThreadLocal<>();
        sMigrateColumns.add("_id");
        sMigrateColumns.add("_data");
        sMigrateColumns.add("volume_name");
        sMigrateColumns.add("media_type");
        sMigrateColumns.add("date_added");
        sMigrateColumns.add("date_expires");
        sMigrateColumns.add("is_pending");
        sMigrateColumns.add("is_trashed");
        sMigrateColumns.add("is_favorite");
        sMigrateColumns.add("owner_package_name");
        sMigrateColumns.add("bookmark");
        sMigrateColumns.add("tags");
        sMigrateColumns.add("category");
        sMigrateColumns.add("bookmark");
        sMigrateColumns.add("download_uri");
        sMigrateColumns.add("referer_uri");
        this.mContext = context;
        this.mName = str;
        this.mVersion = i;
        this.mVolumeName = z ? "internal" : "external";
        this.mInternal = z;
        this.mEarlyUpgrade = z2;
        this.mLegacyProvider = z3;
        this.mColumnAnnotation = cls;
        this.mSchemaListener = onSchemaChangeListener;
        this.mFilesListener = onFilesChangeListener;
        this.mMigrationListener = onLegacyMigrationListener;
        this.mIdGenerator = unaryOperator;
        if (z) {
            this.mFilterVolumeNames.add("internal");
        } else {
            this.mFilterVolumeNames.add("external_primary");
        }
        setWriteAheadLoggingEnabled(true);
    }

    public void setFilterVolumeNames(Set<String> set) {
        synchronized (this.mFilterVolumeNames) {
            if (this.mFilterVolumeNames.equals(set)) {
                return;
            }
            this.mFilterVolumeNames.clear();
            this.mFilterVolumeNames.addAll(set);
            SQLiteDatabase writableDatabase = super.getWritableDatabase();
            this.mSchemaLock.writeLock().lock();
            try {
                writableDatabase.beginTransaction();
                createLatestViews(writableDatabase, this.mInternal);
                writableDatabase.setTransactionSuccessful();
            } finally {
                writableDatabase.endTransaction();
                this.mSchemaLock.writeLock().unlock();
            }
        }
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public SQLiteDatabase getReadableDatabase() {
        throw new UnsupportedOperationException("All database operations must be routed through runWithTransaction() or runWithoutTransaction() to avoid deadlocks");
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public SQLiteDatabase getWritableDatabase() {
        throw new UnsupportedOperationException("All database operations must be routed through runWithTransaction() or runWithoutTransaction() to avoid deadlocks");
    }

    SQLiteDatabase getWritableDatabaseForTest() {
        return super.getWritableDatabase();
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onConfigure(SQLiteDatabase sQLiteDatabase) {
        Log.v(Logging.TAG, "onConfigure() for " + this.mName);
        sQLiteDatabase.setCustomScalarFunction("_INSERT", new UnaryOperator() { // from class: com.android.providers.media.-$$Lambda$DatabaseHelper$_NJSMrm4OvwFhBk-RPih-15K8N8
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return this.f$0.lambda$onConfigure$0$DatabaseHelper((String) obj);
            }
        });
        sQLiteDatabase.setCustomScalarFunction("_UPDATE", new UnaryOperator() { // from class: com.android.providers.media.-$$Lambda$DatabaseHelper$HyRArSf9ZxqrkAvhPI93vIvQUhA
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return this.f$0.lambda$onConfigure$1$DatabaseHelper((String) obj);
            }
        });
        sQLiteDatabase.setCustomScalarFunction("_DELETE", new UnaryOperator() { // from class: com.android.providers.media.-$$Lambda$DatabaseHelper$CbQ_baQjWlp_eP4-onDKW55y6Hw
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return this.f$0.lambda$onConfigure$2$DatabaseHelper((String) obj);
            }
        });
        sQLiteDatabase.setCustomScalarFunction("_GET_ID", new UnaryOperator() { // from class: com.android.providers.media.-$$Lambda$DatabaseHelper$BIryDvGFSZW2x0RDhH-YQG5DDUI
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return this.f$0.lambda$onConfigure$3$DatabaseHelper((String) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: lambda$onConfigure$0, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ String lambda$onConfigure$0$DatabaseHelper(String str) {
        if (str == null || this.mFilesListener == null || this.mSchemaLock.isWriteLockedByCurrentThread()) {
            return null;
        }
        String[] strArrSplit = str.split(":", 4);
        String str2 = strArrSplit[0];
        long j = Long.parseLong(strArrSplit[1]);
        int i = Integer.parseInt(strArrSplit[2]);
        boolean z = Integer.parseInt(strArrSplit[3]) != 0;
        Trace.beginSection("_INSERT");
        try {
            this.mFilesListener.onInsert(this, str2, j, i, z);
            return null;
        } finally {
            Trace.endSection();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: lambda$onConfigure$1, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ String lambda$onConfigure$1$DatabaseHelper(String str) {
        if (str == null || this.mFilesListener == null || this.mSchemaLock.isWriteLockedByCurrentThread()) {
            return null;
        }
        String[] strArrSplit = str.split(":", 10);
        String str2 = strArrSplit[0];
        long j = Long.parseLong(strArrSplit[1]);
        int i = Integer.parseInt(strArrSplit[2]);
        boolean z = Integer.parseInt(strArrSplit[3]) != 0;
        long j2 = Long.parseLong(strArrSplit[4]);
        int i2 = Integer.parseInt(strArrSplit[5]);
        boolean z2 = Integer.parseInt(strArrSplit[6]) != 0;
        String str3 = strArrSplit[7];
        String str4 = strArrSplit[8];
        String str5 = strArrSplit[9];
        Trace.beginSection("_UPDATE");
        try {
            this.mFilesListener.onUpdate(this, str2, j, i, z, j2, i2, z2, str3, str4, str5);
            return null;
        } finally {
            Trace.endSection();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: lambda$onConfigure$2, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ String lambda$onConfigure$2$DatabaseHelper(String str) {
        if (str == null || this.mFilesListener == null || this.mSchemaLock.isWriteLockedByCurrentThread()) {
            return null;
        }
        String[] strArrSplit = str.split(":", 6);
        String str2 = strArrSplit[0];
        long j = Long.parseLong(strArrSplit[1]);
        int i = Integer.parseInt(strArrSplit[2]);
        boolean z = Integer.parseInt(strArrSplit[3]) != 0;
        String str3 = strArrSplit[4];
        String str4 = strArrSplit[5];
        Trace.beginSection("_DELETE");
        try {
            this.mFilesListener.onDelete(this, str2, j, i, z, str3, str4);
            return null;
        } finally {
            Trace.endSection();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: lambda$onConfigure$3, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ String lambda$onConfigure$3$DatabaseHelper(String str) {
        if (this.mIdGenerator == null || this.mSchemaLock.isWriteLockedByCurrentThread()) {
            return null;
        }
        Trace.beginSection("_GET_ID");
        try {
            return (String) this.mIdGenerator.apply(str);
        } finally {
            Trace.endSection();
        }
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onCreate(SQLiteDatabase sQLiteDatabase) {
        Log.v(Logging.TAG, "onCreate() for " + this.mName);
        this.mSchemaLock.writeLock().lock();
        try {
            updateDatabase(sQLiteDatabase, 0, this.mVersion);
        } finally {
            this.mSchemaLock.writeLock().unlock();
        }
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        Log.v(Logging.TAG, "onUpgrade() for " + this.mName + " from " + i + " to " + i2);
        this.mSchemaLock.writeLock().lock();
        try {
            updateDatabase(sQLiteDatabase, i, i2);
        } finally {
            this.mSchemaLock.writeLock().unlock();
        }
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onDowngrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        Log.v(Logging.TAG, "onDowngrade() for " + this.mName + " from " + i + " to " + i2);
        this.mSchemaLock.writeLock().lock();
        try {
            downgradeDatabase(sQLiteDatabase, i, i2);
        } finally {
            this.mSchemaLock.writeLock().unlock();
        }
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onOpen(SQLiteDatabase sQLiteDatabase) {
        Log.v(Logging.TAG, "onOpen() for " + this.mName);
        if (this.mMigrateFromLegacy) {
            this.mMigrateFromLegacy = false;
            this.mSchemaLock.writeLock().lock();
            try {
                makePristineIndexes(sQLiteDatabase);
                migrateFromLegacy(sQLiteDatabase);
                createLatestIndexes(sQLiteDatabase, this.mInternal);
            } finally {
                this.mSchemaLock.writeLock().unlock();
            }
        }
        Log.v(Logging.TAG, "onOpen() finished for " + this.mName);
    }

    public ArrayMap<String, String> getProjectionMap(Class<?>... clsArr) {
        ArrayMap<String, String> arrayMap = new ArrayMap<>();
        synchronized (this.mProjectionMapCache) {
            for (Class<?> cls : clsArr) {
                ArrayMap<String, String> arrayMap2 = this.mProjectionMapCache.get(cls);
                if (arrayMap2 == null) {
                    arrayMap2 = new ArrayMap<>();
                    try {
                        for (Field field : cls.getFields()) {
                            if (Objects.equals(field.getName(), "_ID") || field.isAnnotationPresent(this.mColumnAnnotation)) {
                                String str = (String) field.get(null);
                                arrayMap2.put(str, str);
                            }
                        }
                        this.mProjectionMapCache.put(cls, arrayMap2);
                    } catch (ReflectiveOperationException e) {
                        throw new RuntimeException(e);
                    }
                }
                arrayMap.putAll((ArrayMap<? extends String, ? extends String>) arrayMap2);
            }
        }
        return arrayMap;
    }

    /* JADX INFO: Access modifiers changed from: private */
    static class TransactionState {
        public final ArrayList<Runnable> backgroundTasks;
        public final ArrayList<Runnable> blockingTasks;
        public final SparseArray<ArraySet<Uri>> notifyChanges;
        public boolean successful;

        private TransactionState() {
            this.blockingTasks = new ArrayList<>();
            this.notifyChanges = new SparseArray<>();
            this.backgroundTasks = new ArrayList<>();
        }
    }

    public boolean isTransactionActive() {
        return this.mTransactionState.get() != null;
    }

    public void beginTransaction() {
        Trace.beginSection("transaction " + getDatabaseName());
        Trace.beginSection("beginTransaction");
        try {
            beginTransactionInternal();
        } finally {
            Trace.endSection();
        }
    }

    private void beginTransactionInternal() {
        if (this.mTransactionState.get() != null) {
            throw new IllegalStateException("Nested transactions not supported");
        }
        this.mTransactionState.set(new TransactionState());
        SQLiteDatabase writableDatabase = super.getWritableDatabase();
        this.mSchemaLock.readLock().lock();
        writableDatabase.beginTransaction();
        writableDatabase.execSQL("UPDATE local_metadata SET generation=generation+1;");
    }

    public void setTransactionSuccessful() {
        TransactionState transactionState = this.mTransactionState.get();
        if (transactionState == null) {
            throw new IllegalStateException("No transaction in progress");
        }
        transactionState.successful = true;
        super.getWritableDatabase().setTransactionSuccessful();
    }

    public void endTransaction() {
        Trace.beginSection("endTransaction");
        try {
            endTransactionInternal();
        } finally {
            Trace.endSection();
            Trace.endSection();
        }
    }

    private void endTransactionInternal() {
        final TransactionState transactionState = this.mTransactionState.get();
        if (transactionState == null) {
            throw new IllegalStateException("No transaction in progress");
        }
        this.mTransactionState.remove();
        super.getWritableDatabase().endTransaction();
        this.mSchemaLock.readLock().unlock();
        if (transactionState.successful) {
            for (int i = 0; i < transactionState.blockingTasks.size(); i++) {
                transactionState.blockingTasks.get(i).run();
            }
            ForegroundThread.getExecutor().execute(new Runnable() { // from class: com.android.providers.media.-$$Lambda$DatabaseHelper$g4gEgEIG_GNvHzsowPJlvxYSxdA
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$endTransactionInternal$4$DatabaseHelper(transactionState);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: lambda$endTransactionInternal$4, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void lambda$endTransactionInternal$4$DatabaseHelper(TransactionState transactionState) {
        for (int i = 0; i < transactionState.notifyChanges.size(); i++) {
            notifyChangeInternal(transactionState.notifyChanges.valueAt(i), transactionState.notifyChanges.keyAt(i));
        }
        for (int i2 = 0; i2 < transactionState.backgroundTasks.size(); i2++) {
            BackgroundThread.getExecutor().execute(transactionState.backgroundTasks.get(i2));
        }
    }

    public <T> T runWithTransaction(Function<SQLiteDatabase, T> function) {
        SQLiteDatabase writableDatabase = super.getWritableDatabase();
        if (this.mTransactionState.get() != null) {
            return function.apply(writableDatabase);
        }
        beginTransaction();
        try {
            T tApply = function.apply(writableDatabase);
            setTransactionSuccessful();
            return tApply;
        } finally {
            endTransaction();
        }
    }

    public <T> T runWithoutTransaction(Function<SQLiteDatabase, T> function) {
        SQLiteDatabase writableDatabase = super.getWritableDatabase();
        if (this.mTransactionState.get() != null) {
            return function.apply(writableDatabase);
        }
        this.mSchemaLock.readLock().lock();
        try {
            return function.apply(writableDatabase);
        } finally {
            this.mSchemaLock.readLock().unlock();
        }
    }

    public void notifyInsert(Uri uri) {
        notifyChange(uri, 4);
    }

    public void notifyUpdate(Uri uri) {
        notifyChange(uri, 8);
    }

    public void notifyDelete(Uri uri) {
        notifyChange(uri, 16);
    }

    public void notifyChange(final Uri uri, final int i) {
        if (Logging.LOGV) {
            Log.v(Logging.TAG, "Notifying " + uri);
        }
        TransactionState transactionState = this.mTransactionState.get();
        if (transactionState != null) {
            ArraySet<Uri> arraySet = transactionState.notifyChanges.get(i);
            if (arraySet == null) {
                arraySet = new ArraySet<>();
                transactionState.notifyChanges.put(i, arraySet);
            }
            arraySet.add(uri);
            return;
        }
        ForegroundThread.getExecutor().execute(new Runnable() { // from class: com.android.providers.media.-$$Lambda$DatabaseHelper$kvWk3FWpFgu1jyzAqRB0nw7FjP4
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$notifyChange$5$DatabaseHelper(uri, i);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: notifySingleChangeInternal, reason: merged with bridge method [inline-methods] */
    public void lambda$notifyChange$5$DatabaseHelper(Uri uri, int i) {
        Trace.beginSection("notifySingleChange");
        try {
            this.mContext.getContentResolver().notifyChange(uri, (ContentObserver) null, i);
        } finally {
            Trace.endSection();
        }
    }

    private void notifyChangeInternal(Collection<Uri> collection, int i) {
        Trace.beginSection("notifyChange");
        try {
            this.mContext.getContentResolver().notifyChange(collection, (ContentObserver) null, i);
        } finally {
            Trace.endSection();
        }
    }

    public void postBlocking(Runnable runnable) {
        TransactionState transactionState = this.mTransactionState.get();
        if (transactionState != null) {
            transactionState.blockingTasks.add(runnable);
        } else {
            runnable.run();
        }
    }

    public void postBackground(Runnable runnable) {
        TransactionState transactionState = this.mTransactionState.get();
        if (transactionState != null) {
            transactionState.backgroundTasks.add(runnable);
        } else {
            BackgroundThread.getExecutor().execute(runnable);
        }
    }

    private static void deleteLegacyThumbnailData() {
        File[] fileArrListFiles = new File(Environment.getExternalStorageDirectory(), "/DCIM/.thumbnails").listFiles(new FilenameFilter() { // from class: com.android.providers.media.-$$Lambda$DatabaseHelper$mjNM6NKW1wog4qZIJGvH3Io8aIc
            @Override // java.io.FilenameFilter
            public final boolean accept(File file, String str) {
                return str.startsWith(".thumbdata");
            }
        });
        if (fileArrListFiles == null) {
            fileArrListFiles = new File[0];
        }
        for (File file : fileArrListFiles) {
            if (!file.delete()) {
                Log.e(Logging.TAG, "Failed to delete legacy thumbnail data " + file.getAbsolutePath());
            }
        }
    }

    static void makePristineSchema(SQLiteDatabase sQLiteDatabase) {
        Cursor cursorQuery = sQLiteDatabase.query("sqlite_master", new String[]{"name"}, "type is 'trigger'", null, null, null, null);
        while (cursorQuery.moveToNext()) {
            if (!cursorQuery.getString(0).startsWith("sqlite_")) {
                sQLiteDatabase.execSQL("DROP TRIGGER IF EXISTS " + cursorQuery.getString(0));
            }
        }
        cursorQuery.close();
        Cursor cursorQuery2 = sQLiteDatabase.query("sqlite_master", new String[]{"name"}, "type is 'view'", null, null, null, null);
        while (cursorQuery2.moveToNext()) {
            if (!cursorQuery2.getString(0).startsWith("sqlite_")) {
                sQLiteDatabase.execSQL("DROP VIEW IF EXISTS " + cursorQuery2.getString(0));
            }
        }
        cursorQuery2.close();
        Cursor cursorQuery3 = sQLiteDatabase.query("sqlite_master", new String[]{"name"}, "type is 'index'", null, null, null, null);
        while (cursorQuery3.moveToNext()) {
            if (!cursorQuery3.getString(0).startsWith("sqlite_")) {
                sQLiteDatabase.execSQL("DROP INDEX IF EXISTS " + cursorQuery3.getString(0));
            }
        }
        cursorQuery3.close();
        Cursor cursorQuery4 = sQLiteDatabase.query("sqlite_master", new String[]{"name"}, "type is 'table'", null, null, null, null);
        while (cursorQuery4.moveToNext()) {
            if (!cursorQuery4.getString(0).startsWith("sqlite_")) {
                sQLiteDatabase.execSQL("DROP TABLE IF EXISTS " + cursorQuery4.getString(0));
            }
        }
        cursorQuery4.close();
    }

    private void createLatestSchema(SQLiteDatabase sQLiteDatabase) {
        try {
            PackageInfo packageInfo = this.mContext.getPackageManager().getPackageInfo(this.mContext.getPackageName(), 8);
            if (packageInfo != null && packageInfo.providers != null) {
                for (ProviderInfo providerInfo : packageInfo.providers) {
                    this.mContext.revokeUriPermission(Uri.parse("content://" + providerInfo.authority), 3);
                }
            }
        } catch (Exception e) {
            Log.w(Logging.TAG, "Failed to revoke permissions", e);
        }
        makePristineSchema(sQLiteDatabase);
        sQLiteDatabase.execSQL("CREATE TABLE local_metadata (generation INTEGER DEFAULT 0)");
        sQLiteDatabase.execSQL("INSERT INTO local_metadata VALUES (0)");
        sQLiteDatabase.execSQL("CREATE TABLE android_metadata (locale TEXT)");
        sQLiteDatabase.execSQL("CREATE TABLE thumbnails (_id INTEGER PRIMARY KEY,_data TEXT,image_id INTEGER,kind INTEGER,width INTEGER,height INTEGER)");
        sQLiteDatabase.execSQL("CREATE TABLE album_art (album_id INTEGER PRIMARY KEY,_data TEXT)");
        sQLiteDatabase.execSQL("CREATE TABLE videothumbnails (_id INTEGER PRIMARY KEY,_data TEXT,video_id INTEGER,kind INTEGER,width INTEGER,height INTEGER)");
        sQLiteDatabase.execSQL("CREATE TABLE files (_id INTEGER PRIMARY KEY AUTOINCREMENT,_data TEXT UNIQUE COLLATE NOCASE,_size INTEGER,format INTEGER,parent INTEGER,date_added INTEGER,date_modified INTEGER,mime_type TEXT,title TEXT,description TEXT,_display_name TEXT,picasa_id TEXT,orientation INTEGER,latitude DOUBLE,longitude DOUBLE,datetaken INTEGER,mini_thumb_magic INTEGER,bucket_id TEXT,bucket_display_name TEXT,isprivate INTEGER,title_key TEXT,artist_id INTEGER,album_id INTEGER,composer TEXT,track INTEGER,year INTEGER CHECK(year!=0),is_ringtone INTEGER,is_music INTEGER,is_alarm INTEGER,is_notification INTEGER,is_podcast INTEGER,album_artist TEXT,duration INTEGER,bookmark INTEGER,artist TEXT,album TEXT,resolution TEXT,tags TEXT,category TEXT,language TEXT,mini_thumb_data TEXT,name TEXT,media_type INTEGER,old_id INTEGER,is_drm INTEGER,width INTEGER, height INTEGER, title_resource_uri TEXT,owner_package_name TEXT DEFAULT NULL,color_standard INTEGER, color_transfer INTEGER, color_range INTEGER,_hash BLOB DEFAULT NULL, is_pending INTEGER DEFAULT 0,is_download INTEGER DEFAULT 0, download_uri TEXT DEFAULT NULL,referer_uri TEXT DEFAULT NULL, is_audiobook INTEGER DEFAULT 0,date_expires INTEGER DEFAULT NULL,is_trashed INTEGER DEFAULT 0,group_id INTEGER DEFAULT NULL,primary_directory TEXT DEFAULT NULL,secondary_directory TEXT DEFAULT NULL,document_id TEXT DEFAULT NULL,instance_id TEXT DEFAULT NULL,original_document_id TEXT DEFAULT NULL,relative_path TEXT DEFAULT NULL,volume_name TEXT DEFAULT NULL,artist_key TEXT DEFAULT NULL,album_key TEXT DEFAULT NULL,genre TEXT DEFAULT NULL,genre_key TEXT DEFAULT NULL,genre_id INTEGER,author TEXT DEFAULT NULL, bitrate INTEGER DEFAULT NULL,capture_framerate REAL DEFAULT NULL, cd_track_number TEXT DEFAULT NULL,compilation INTEGER DEFAULT NULL, disc_number TEXT DEFAULT NULL,is_favorite INTEGER DEFAULT 0, num_tracks INTEGER DEFAULT NULL,writer TEXT DEFAULT NULL, exposure_time TEXT DEFAULT NULL,f_number TEXT DEFAULT NULL, iso INTEGER DEFAULT NULL,scene_capture_type INTEGER DEFAULT NULL, generation_added INTEGER DEFAULT 0,generation_modified INTEGER DEFAULT 0, xmp BLOB DEFAULT NULL)");
        sQLiteDatabase.execSQL("CREATE TABLE log (time DATETIME, message TEXT)");
        if (!this.mInternal) {
            sQLiteDatabase.execSQL("CREATE TABLE audio_playlists_map (_id INTEGER PRIMARY KEY,audio_id INTEGER NOT NULL,playlist_id INTEGER NOT NULL,play_order INTEGER NOT NULL)");
        }
        createLatestViews(sQLiteDatabase, this.mInternal);
        createLatestTriggers(sQLiteDatabase, this.mInternal);
        createLatestIndexes(sQLiteDatabase, this.mInternal);
        if (this.mLegacyProvider) {
            return;
        }
        this.mMigrateFromLegacy = true;
    }

    private void migrateFromLegacy(SQLiteDatabase sQLiteDatabase) {
        boolean z;
        String str;
        Cursor cursorQuery;
        ContentProviderClient contentProviderClientAcquireContentProviderClient = this.mContext.getContentResolver().acquireContentProviderClient("media_legacy");
        try {
            if (contentProviderClientAcquireContentProviderClient == null) {
                Log.d(Logging.TAG, "No legacy provider available for migration");
                if (contentProviderClientAcquireContentProviderClient != null) {
                    contentProviderClientAcquireContentProviderClient.close();
                    return;
                }
                return;
            }
            Uri uriRewriteToLegacy = MediaStore.rewriteToLegacy(MediaStore.Files.getContentUri(this.mVolumeName));
            Bundle bundle = new Bundle();
            bundle.putInt("android:query-arg-match-pending", 1);
            bundle.putInt("android:query-arg-match-trashed", 1);
            bundle.putInt("android:query-arg-match-favorite", 1);
            sQLiteDatabase.beginTransaction();
            Log.d(Logging.TAG, "Starting migration from legacy provider");
            this.mMigrationListener.onStarted(contentProviderClientAcquireContentProviderClient, this.mVolumeName);
            try {
                z = false;
                str = null;
                cursorQuery = contentProviderClientAcquireContentProviderClient.query(uriRewriteToLegacy, (String[]) sMigrateColumns.toArray(new String[0]), bundle, null);
            } catch (Exception e) {
                Log.wtf(Logging.TAG, "Failed migration from legacy provider", e);
            }
            try {
                ContentValues contentValues = new ContentValues();
                while (cursorQuery.moveToNext()) {
                    contentValues.clear();
                    String string = cursorQuery.getString(cursorQuery.getColumnIndex("_data"));
                    contentValues.put("_data", string);
                    FileUtils.computeValuesFromData(contentValues, z);
                    Iterator<String> it = sMigrateColumns.iterator();
                    while (it.hasNext()) {
                        DatabaseUtils.copyFromCursorToContentValues(it.next(), cursorQuery, contentValues);
                    }
                    String strExtractVolumePath = FileUtils.extractVolumePath(string);
                    if (strExtractVolumePath != null) {
                        FileUtils.computeDataFromValues(contentValues, new File(strExtractVolumePath), z);
                        String asString = contentValues.getAsString("_data");
                        if (!Objects.equals(string, asString)) {
                            try {
                                renameWithRetry(string, asString);
                            } catch (IOException e2) {
                                Log.wtf(Logging.TAG, "Failed to rename " + contentValues + "; continuing", e2);
                                FileUtils.computeValuesFromData(contentValues, z);
                            }
                        }
                    }
                    if (sQLiteDatabase.insert("files", str, contentValues) == -1) {
                        Log.w(Logging.TAG, "Failed to insert " + contentValues + "; continuing");
                    }
                    if (cursorQuery.getPosition() % 2000 == 0) {
                        sQLiteDatabase.setTransactionSuccessful();
                        sQLiteDatabase.endTransaction();
                        sQLiteDatabase.beginTransaction();
                        int position = cursorQuery.getPosition();
                        int count = cursorQuery.getCount();
                        Log.v(Logging.TAG, "Migrated " + position + " of " + count + "...");
                        this.mMigrationListener.onProgress(contentProviderClientAcquireContentProviderClient, this.mVolumeName, (long) position, (long) count);
                    }
                    z = false;
                    str = null;
                }
                Log.d(Logging.TAG, "Finished migration from legacy provider");
                if (cursorQuery != null) {
                    cursorQuery.close();
                }
                sQLiteDatabase.setTransactionSuccessful();
                sQLiteDatabase.endTransaction();
                this.mMigrationListener.onFinished(contentProviderClientAcquireContentProviderClient, this.mVolumeName);
                if (contentProviderClientAcquireContentProviderClient != null) {
                    contentProviderClientAcquireContentProviderClient.close();
                }
            } finally {
            }
        } catch (Throwable th) {
            if (contentProviderClientAcquireContentProviderClient == null) {
                throw th;
            }
            try {
                contentProviderClientAcquireContentProviderClient.close();
                throw th;
            } catch (Throwable th2) {
                th.addSuppressed(th2);
                throw th;
            }
        }
    }

    private static void makePristineViews(SQLiteDatabase sQLiteDatabase) {
        Cursor cursorQuery = sQLiteDatabase.query("sqlite_master", new String[]{"name"}, "type is 'view'", null, null, null, null);
        while (cursorQuery.moveToNext()) {
            sQLiteDatabase.execSQL("DROP VIEW IF EXISTS " + cursorQuery.getString(0));
        }
        cursorQuery.close();
    }

    private void createLatestViews(SQLiteDatabase sQLiteDatabase, boolean z) {
        String strBindList;
        makePristineViews(sQLiteDatabase);
        if (this.mColumnAnnotation == null) {
            Log.w(Logging.TAG, "No column annotation provided; not creating views");
            return;
        }
        synchronized (this.mFilterVolumeNames) {
            strBindList = DatabaseUtils.bindList(this.mFilterVolumeNames.toArray());
        }
        if (!z) {
            sQLiteDatabase.execSQL("CREATE VIEW audio_playlists AS SELECT " + String.join(",", getProjectionMap(MediaStore.Audio.Playlists.class).keySet()) + " FROM files WHERE media_type=4");
        }
        sQLiteDatabase.execSQL("CREATE VIEW searchhelpertitle AS SELECT * FROM audio ORDER BY title_key");
        sQLiteDatabase.execSQL("CREATE VIEW search AS SELECT _id,'artist' AS mime_type,artist,NULL AS album,NULL AS title,artist AS text1,NULL AS text2,number_of_albums AS data1,number_of_tracks AS data2,artist_key AS match,'content://media/external/audio/artists/'||_id AS suggest_intent_data,1 AS grouporder FROM artist_info WHERE (artist!='<unknown>') UNION ALL SELECT _id,'album' AS mime_type,artist,album,NULL AS title,album AS text1,artist AS text2,NULL AS data1,NULL AS data2,artist_key||' '||album_key AS match,'content://media/external/audio/albums/'||_id AS suggest_intent_data,2 AS grouporder FROM album_info WHERE (album!='<unknown>') UNION ALL SELECT searchhelpertitle._id AS _id,mime_type,artist,album,title,title AS text1,artist AS text2,NULL AS data1,NULL AS data2,artist_key||' '||album_key||' '||title_key AS match,'content://media/external/audio/media/'||searchhelpertitle._id AS suggest_intent_data,3 AS grouporder FROM searchhelpertitle WHERE (title != '')");
        sQLiteDatabase.execSQL("CREATE VIEW audio AS SELECT " + String.join(",", getProjectionMap(MediaStore.Audio.Media.class).keySet()) + " FROM files WHERE media_type=2");
        sQLiteDatabase.execSQL("CREATE VIEW video AS SELECT " + String.join(",", getProjectionMap(MediaStore.Video.Media.class).keySet()) + " FROM files WHERE media_type=3");
        sQLiteDatabase.execSQL("CREATE VIEW images AS SELECT " + String.join(",", getProjectionMap(MediaStore.Images.Media.class).keySet()) + " FROM files WHERE media_type=1");
        sQLiteDatabase.execSQL("CREATE VIEW downloads AS SELECT " + String.join(",", getProjectionMap(MediaStore.Downloads.class).keySet()) + " FROM files WHERE is_download=1");
        sQLiteDatabase.execSQL("CREATE VIEW audio_artists AS SELECT   artist_id AS _id, MIN(artist) AS artist, artist_key AS artist_key, COUNT(DISTINCT album_id) AS number_of_albums, COUNT(DISTINCT _id) AS number_of_tracks FROM audio WHERE is_music=1 AND volume_name IN " + strBindList + " GROUP BY artist_id");
        sQLiteDatabase.execSQL("CREATE VIEW audio_albums AS SELECT   album_id AS _id, album_id AS album_id, MIN(album) AS album, album_key AS album_key, artist_id AS artist_id, artist AS artist, artist_key AS artist_key, COUNT(DISTINCT _id) AS numsongs, COUNT(DISTINCT _id) AS numsongs_by_artist, MIN(year) AS minyear, MAX(year) AS maxyear, NULL AS album_art FROM audio WHERE is_music=1 AND volume_name IN " + strBindList + " GROUP BY album_id");
        sQLiteDatabase.execSQL("CREATE VIEW audio_genres AS SELECT   genre_id AS _id, MIN(genre) AS name FROM audio WHERE volume_name IN " + strBindList + " GROUP BY genre_id");
    }

    private static void makePristineTriggers(SQLiteDatabase sQLiteDatabase) {
        Cursor cursorQuery = sQLiteDatabase.query("sqlite_master", new String[]{"name"}, "type is 'trigger'", null, null, null, null);
        while (cursorQuery.moveToNext()) {
            if (!cursorQuery.getString(0).startsWith("sqlite_")) {
                sQLiteDatabase.execSQL("DROP TRIGGER IF EXISTS " + cursorQuery.getString(0));
            }
        }
        cursorQuery.close();
    }

    private static void createLatestTriggers(SQLiteDatabase sQLiteDatabase, boolean z) {
        makePristineTriggers(sQLiteDatabase);
        sQLiteDatabase.execSQL("CREATE TRIGGER files_insert AFTER INSERT ON files BEGIN SELECT _INSERT(new.volume_name||':'||new._id||':'||new.media_type||':'||new.is_download); END");
        sQLiteDatabase.execSQL("CREATE TRIGGER files_update AFTER UPDATE ON files BEGIN SELECT _UPDATE(old.volume_name||':'||old._id||':'||old.media_type||':'||old.is_download||':'||new._id||':'||new.media_type||':'||new.is_download||':'||ifnull(old.owner_package_name,'null')||':'||ifnull(new.owner_package_name,'null')||':'||old._data); END");
        sQLiteDatabase.execSQL("CREATE TRIGGER files_delete AFTER DELETE ON files BEGIN SELECT _DELETE(old.volume_name||':'||old._id||':'||old.media_type||':'||old.is_download||':'||ifnull(old.owner_package_name,'null')||':'||old._data); END");
    }

    private static void makePristineIndexes(SQLiteDatabase sQLiteDatabase) {
        Cursor cursorQuery = sQLiteDatabase.query("sqlite_master", new String[]{"name"}, "type is 'index'", null, null, null, null);
        while (cursorQuery.moveToNext()) {
            if (!cursorQuery.getString(0).startsWith("sqlite_")) {
                sQLiteDatabase.execSQL("DROP INDEX IF EXISTS " + cursorQuery.getString(0));
            }
        }
        cursorQuery.close();
    }

    private static void createLatestIndexes(SQLiteDatabase sQLiteDatabase, boolean z) {
        makePristineIndexes(sQLiteDatabase);
        sQLiteDatabase.execSQL("CREATE INDEX image_id_index on thumbnails(image_id)");
        sQLiteDatabase.execSQL("CREATE INDEX video_id_index on videothumbnails(video_id)");
        sQLiteDatabase.execSQL("CREATE INDEX album_id_idx ON files(album_id)");
        sQLiteDatabase.execSQL("CREATE INDEX artist_id_idx ON files(artist_id)");
        sQLiteDatabase.execSQL("CREATE INDEX genre_id_idx ON files(genre_id)");
        sQLiteDatabase.execSQL("CREATE INDEX bucket_index on files(bucket_id,media_type,datetaken, _id)");
        sQLiteDatabase.execSQL("CREATE INDEX bucket_name on files(bucket_id,media_type,bucket_display_name)");
        sQLiteDatabase.execSQL("CREATE INDEX format_index ON files(format)");
        sQLiteDatabase.execSQL("CREATE INDEX media_type_index ON files(media_type)");
        sQLiteDatabase.execSQL("CREATE INDEX parent_index ON files(parent)");
        sQLiteDatabase.execSQL("CREATE INDEX path_index ON files(_data)");
        sQLiteDatabase.execSQL("CREATE INDEX sort_index ON files(datetaken ASC, _id ASC)");
        sQLiteDatabase.execSQL("CREATE INDEX title_idx ON files(title)");
        sQLiteDatabase.execSQL("CREATE INDEX titlekey_index ON files(title_key)");
    }

    private static void updateCollationKeys(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("DELETE from albums");
        sQLiteDatabase.execSQL("DELETE from artists");
        sQLiteDatabase.execSQL("UPDATE files SET date_modified=0;");
    }

    private static void updateAddTitleResource(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("ALTER TABLE files ADD COLUMN title_resource_uri TEXT");
        sQLiteDatabase.execSQL("UPDATE files SET date_modified=0 WHERE (is_alarm IS 1) OR (is_ringtone IS 1) OR (is_notification IS 1)");
    }

    private static void updateAddOwnerPackageName(SQLiteDatabase sQLiteDatabase, boolean z) {
        sQLiteDatabase.execSQL("ALTER TABLE files ADD COLUMN owner_package_name TEXT DEFAULT NULL");
        Cursor cursorQuery = sQLiteDatabase.query("files", new String[]{"_id", "_data"}, "_data REGEXP '" + FileUtils.PATTERN_OWNED_PATH.pattern() + "'", null, null, null, null, null);
        try {
            Log.d(Logging.TAG, "Updating " + cursorQuery.getCount() + " entries with well-known owners");
            Matcher matcher = FileUtils.PATTERN_OWNED_PATH.matcher("");
            ContentValues contentValues = new ContentValues();
            while (cursorQuery.moveToNext()) {
                long j = cursorQuery.getLong(0);
                matcher.reset(cursorQuery.getString(1));
                if (matcher.matches()) {
                    String strGroup = matcher.group(1);
                    contentValues.clear();
                    contentValues.put("owner_package_name", strGroup);
                    sQLiteDatabase.update("files", contentValues, "_id=" + j, null);
                }
            }
            if (cursorQuery != null) {
                cursorQuery.close();
            }
        } catch (Throwable th) {
            if (cursorQuery != null) {
                try {
                    cursorQuery.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
            }
            throw th;
        }
    }

    private static void updateAddColorSpaces(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("ALTER TABLE files ADD COLUMN color_standard INTEGER;");
        sQLiteDatabase.execSQL("ALTER TABLE files ADD COLUMN color_transfer INTEGER;");
        sQLiteDatabase.execSQL("ALTER TABLE files ADD COLUMN color_range INTEGER;");
    }

    private static void updateAddHashAndPending(SQLiteDatabase sQLiteDatabase, boolean z) {
        sQLiteDatabase.execSQL("ALTER TABLE files ADD COLUMN _hash BLOB DEFAULT NULL;");
        sQLiteDatabase.execSQL("ALTER TABLE files ADD COLUMN is_pending INTEGER DEFAULT 0;");
    }

    private static void updateAddDownloadInfo(SQLiteDatabase sQLiteDatabase, boolean z) {
        sQLiteDatabase.execSQL("ALTER TABLE files ADD COLUMN is_download INTEGER DEFAULT 0;");
        sQLiteDatabase.execSQL("ALTER TABLE files ADD COLUMN download_uri TEXT DEFAULT NULL;");
        sQLiteDatabase.execSQL("ALTER TABLE files ADD COLUMN referer_uri TEXT DEFAULT NULL;");
    }

    private static void updateAddAudiobook(SQLiteDatabase sQLiteDatabase, boolean z) {
        sQLiteDatabase.execSQL("ALTER TABLE files ADD COLUMN is_audiobook INTEGER DEFAULT 0;");
    }

    private static void updateClearLocation(SQLiteDatabase sQLiteDatabase, boolean z) {
        sQLiteDatabase.execSQL("UPDATE files SET latitude=NULL, longitude=NULL;");
    }

    private static void updateSetIsDownload(SQLiteDatabase sQLiteDatabase, boolean z) {
        sQLiteDatabase.execSQL("UPDATE files SET is_download=1 WHERE _data REGEXP '" + FileUtils.PATTERN_DOWNLOADS_FILE + "'");
    }

    private static void updateAddExpiresAndTrashed(SQLiteDatabase sQLiteDatabase, boolean z) {
        sQLiteDatabase.execSQL("ALTER TABLE files ADD COLUMN date_expires INTEGER DEFAULT NULL;");
        sQLiteDatabase.execSQL("ALTER TABLE files ADD COLUMN is_trashed INTEGER DEFAULT 0;");
    }

    private static void updateAddGroupId(SQLiteDatabase sQLiteDatabase, boolean z) {
        sQLiteDatabase.execSQL("ALTER TABLE files ADD COLUMN group_id INTEGER DEFAULT NULL;");
    }

    private static void updateAddDirectories(SQLiteDatabase sQLiteDatabase, boolean z) {
        sQLiteDatabase.execSQL("ALTER TABLE files ADD COLUMN primary_directory TEXT DEFAULT NULL;");
        sQLiteDatabase.execSQL("ALTER TABLE files ADD COLUMN secondary_directory TEXT DEFAULT NULL;");
    }

    private static void updateAddXmpMm(SQLiteDatabase sQLiteDatabase, boolean z) {
        sQLiteDatabase.execSQL("ALTER TABLE files ADD COLUMN document_id TEXT DEFAULT NULL;");
        sQLiteDatabase.execSQL("ALTER TABLE files ADD COLUMN instance_id TEXT DEFAULT NULL;");
        sQLiteDatabase.execSQL("ALTER TABLE files ADD COLUMN original_document_id TEXT DEFAULT NULL;");
    }

    private static void updateAddPath(SQLiteDatabase sQLiteDatabase, boolean z) {
        sQLiteDatabase.execSQL("ALTER TABLE files ADD COLUMN relative_path TEXT DEFAULT NULL;");
    }

    private static void updateAddVolumeName(SQLiteDatabase sQLiteDatabase, boolean z) {
        sQLiteDatabase.execSQL("ALTER TABLE files ADD COLUMN volume_name TEXT DEFAULT NULL;");
    }

    private static void updateDirsMimeType(SQLiteDatabase sQLiteDatabase, boolean z) {
        sQLiteDatabase.execSQL("UPDATE files SET mime_type=NULL WHERE format=12289");
    }

    private static void updateRelativePath(SQLiteDatabase sQLiteDatabase, boolean z) {
        sQLiteDatabase.execSQL("UPDATE files SET relative_path=relative_path||'/' WHERE relative_path IS NOT NULL AND relative_path NOT LIKE '%/';");
    }

    private static void updateClearDirectories(SQLiteDatabase sQLiteDatabase, boolean z) {
        sQLiteDatabase.execSQL("UPDATE files SET primary_directory=NULL, secondary_directory=NULL;");
    }

    private static void updateRestructureAudio(SQLiteDatabase sQLiteDatabase, boolean z) {
        sQLiteDatabase.execSQL("ALTER TABLE files ADD COLUMN artist_key TEXT DEFAULT NULL;");
        sQLiteDatabase.execSQL("ALTER TABLE files ADD COLUMN album_key TEXT DEFAULT NULL;");
        sQLiteDatabase.execSQL("ALTER TABLE files ADD COLUMN genre TEXT DEFAULT NULL;");
        sQLiteDatabase.execSQL("ALTER TABLE files ADD COLUMN genre_key TEXT DEFAULT NULL;");
        sQLiteDatabase.execSQL("ALTER TABLE files ADD COLUMN genre_id INTEGER;");
        sQLiteDatabase.execSQL("DROP TABLE IF EXISTS artists;");
        sQLiteDatabase.execSQL("DROP TABLE IF EXISTS albums;");
        sQLiteDatabase.execSQL("DROP TABLE IF EXISTS audio_genres;");
        sQLiteDatabase.execSQL("DROP TABLE IF EXISTS audio_genres_map;");
        sQLiteDatabase.execSQL("CREATE INDEX genre_id_idx ON files(genre_id)");
        sQLiteDatabase.execSQL("DROP INDEX IF EXISTS album_idx");
        sQLiteDatabase.execSQL("DROP INDEX IF EXISTS albumkey_index");
        sQLiteDatabase.execSQL("DROP INDEX IF EXISTS artist_idx");
        sQLiteDatabase.execSQL("DROP INDEX IF EXISTS artistkey_index");
        sQLiteDatabase.execSQL("UPDATE files SET date_modified=0 WHERE media_type=2;");
    }

    private static void updateAddMetadata(SQLiteDatabase sQLiteDatabase, boolean z) {
        sQLiteDatabase.execSQL("ALTER TABLE files ADD COLUMN author TEXT DEFAULT NULL;");
        sQLiteDatabase.execSQL("ALTER TABLE files ADD COLUMN bitrate INTEGER DEFAULT NULL;");
        sQLiteDatabase.execSQL("ALTER TABLE files ADD COLUMN capture_framerate REAL DEFAULT NULL;");
        sQLiteDatabase.execSQL("ALTER TABLE files ADD COLUMN cd_track_number TEXT DEFAULT NULL;");
        sQLiteDatabase.execSQL("ALTER TABLE files ADD COLUMN compilation INTEGER DEFAULT NULL;");
        sQLiteDatabase.execSQL("ALTER TABLE files ADD COLUMN disc_number TEXT DEFAULT NULL;");
        sQLiteDatabase.execSQL("ALTER TABLE files ADD COLUMN is_favorite INTEGER DEFAULT 0;");
        sQLiteDatabase.execSQL("ALTER TABLE files ADD COLUMN num_tracks INTEGER DEFAULT NULL;");
        sQLiteDatabase.execSQL("ALTER TABLE files ADD COLUMN writer TEXT DEFAULT NULL;");
        sQLiteDatabase.execSQL("ALTER TABLE files ADD COLUMN exposure_time TEXT DEFAULT NULL;");
        sQLiteDatabase.execSQL("ALTER TABLE files ADD COLUMN f_number TEXT DEFAULT NULL;");
        sQLiteDatabase.execSQL("ALTER TABLE files ADD COLUMN iso INTEGER DEFAULT NULL;");
    }

    private static void updateAddSceneCaptureType(SQLiteDatabase sQLiteDatabase, boolean z) {
        sQLiteDatabase.execSQL("ALTER TABLE files ADD COLUMN scene_capture_type INTEGER DEFAULT NULL;");
    }

    private static void updateMigrateLogs(SQLiteDatabase sQLiteDatabase, boolean z) {
        Cursor cursorQuery = sQLiteDatabase.query("log", new String[]{"time", "message"}, null, null, null, null, null);
        while (cursorQuery.moveToNext()) {
            try {
                Logging.logPersistent("Historical log " + cursorQuery.getString(0) + " " + cursorQuery.getString(1));
            } catch (Throwable th) {
                if (cursorQuery != null) {
                    try {
                        cursorQuery.close();
                    } catch (Throwable th2) {
                        th.addSuppressed(th2);
                    }
                }
                throw th;
            }
        }
        if (cursorQuery != null) {
            cursorQuery.close();
        }
        sQLiteDatabase.execSQL("DELETE FROM log;");
    }

    private static void updateAddLocalMetadata(SQLiteDatabase sQLiteDatabase, boolean z) {
        sQLiteDatabase.execSQL("CREATE TABLE local_metadata (generation INTEGER DEFAULT 0)");
        sQLiteDatabase.execSQL("INSERT INTO local_metadata VALUES (0)");
    }

    private static void updateAddGeneration(SQLiteDatabase sQLiteDatabase, boolean z) {
        sQLiteDatabase.execSQL("ALTER TABLE files ADD COLUMN generation_added INTEGER DEFAULT 0;");
        sQLiteDatabase.execSQL("ALTER TABLE files ADD COLUMN generation_modified INTEGER DEFAULT 0;");
    }

    private static void updateAddXmp(SQLiteDatabase sQLiteDatabase, boolean z) {
        sQLiteDatabase.execSQL("ALTER TABLE files ADD COLUMN xmp BLOB DEFAULT NULL;");
    }

    private static void updateAudioAlbumId(SQLiteDatabase sQLiteDatabase, boolean z) {
        sQLiteDatabase.execSQL("UPDATE files SET date_modified=0 WHERE media_type=2;");
    }

    private static void recomputeDataValues(SQLiteDatabase sQLiteDatabase, boolean z) {
        Cursor cursorQuery = sQLiteDatabase.query("files", new String[]{"_id", "_data"}, null, null, null, null, null, null);
        try {
            Log.d(Logging.TAG, "Recomputing " + cursorQuery.getCount() + " data values");
            ContentValues contentValues = new ContentValues();
            while (cursorQuery.moveToNext()) {
                contentValues.clear();
                long j = cursorQuery.getLong(0);
                contentValues.put("_data", cursorQuery.getString(1));
                FileUtils.computeValuesFromData(contentValues, false);
                contentValues.remove("_data");
                if (!contentValues.isEmpty()) {
                    sQLiteDatabase.update("files", contentValues, "_id=" + j, null);
                }
            }
            if (cursorQuery != null) {
                cursorQuery.close();
            }
        } catch (Throwable th) {
            if (cursorQuery != null) {
                try {
                    cursorQuery.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
            }
            throw th;
        }
    }

    private static void recomputeMediaTypeValues(SQLiteDatabase sQLiteDatabase) {
        Cursor cursorQuery = sQLiteDatabase.query("files", new String[]{"_id", "mime_type"}, "media_type=?", new String[]{String.valueOf(0)}, null, null, null, null);
        try {
            Log.d(Logging.TAG, "Recomputing " + cursorQuery.getCount() + " MediaType values");
            ContentValues contentValues = new ContentValues();
            while (cursorQuery.moveToNext()) {
                contentValues.clear();
                long j = cursorQuery.getLong(0);
                String string = cursorQuery.getString(1);
                if (MimeUtils.isDocumentMimeType(string)) {
                    contentValues.put("media_type", (Integer) 6);
                } else if (MimeUtils.isSubtitleMimeType(string)) {
                    contentValues.put("media_type", (Integer) 5);
                }
                if (!contentValues.isEmpty()) {
                    sQLiteDatabase.update("files", contentValues, "_id=" + j, null);
                }
            }
            if (cursorQuery != null) {
                cursorQuery.close();
            }
        } catch (Throwable th) {
            if (cursorQuery != null) {
                try {
                    cursorQuery.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
            }
            throw th;
        }
    }

    private void updateDatabase(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        long jElapsedRealtime = SystemClock.elapsedRealtime();
        boolean z = this.mInternal;
        if (i < 700) {
            createLatestSchema(sQLiteDatabase);
        } else {
            if (i < 800) {
                updateCollationKeys(sQLiteDatabase);
            }
            if (i < VERSION_P) {
                updateAddTitleResource(sQLiteDatabase);
            }
            if (i < 1000) {
                updateAddOwnerPackageName(sQLiteDatabase, z);
            }
            if (i < 1003) {
                updateAddColorSpaces(sQLiteDatabase);
            }
            if (i < 1004) {
                updateAddHashAndPending(sQLiteDatabase, z);
            }
            if (i < 1005) {
                updateAddDownloadInfo(sQLiteDatabase, z);
            }
            if (i < 1006) {
                updateAddAudiobook(sQLiteDatabase, z);
            }
            if (i < 1007) {
                updateClearLocation(sQLiteDatabase, z);
            }
            if (i < 1008) {
                updateSetIsDownload(sQLiteDatabase, z);
            }
            if (i < 1010) {
                updateAddExpiresAndTrashed(sQLiteDatabase, z);
            }
            boolean z2 = i < 1012;
            if (i < 1013) {
                updateAddGroupId(sQLiteDatabase, z);
                updateAddDirectories(sQLiteDatabase, z);
                z2 = true;
            }
            if (i < 1014) {
                updateAddXmpMm(sQLiteDatabase, z);
            }
            if (i < 1017) {
                updateSetIsDownload(sQLiteDatabase, z);
                z2 = true;
            }
            if (i < 1018) {
                updateAddPath(sQLiteDatabase, z);
                z2 = true;
            }
            if (i < 1019 && !z) {
                deleteLegacyThumbnailData();
            }
            if (i < 1020) {
                updateAddVolumeName(sQLiteDatabase, z);
                z2 = true;
            }
            if (i < 1022) {
                updateDirsMimeType(sQLiteDatabase, z);
            }
            if (i < VERSION_Q) {
                updateRelativePath(sQLiteDatabase, z);
            }
            if (i < 1101) {
                updateClearDirectories(sQLiteDatabase, z);
            }
            if (i < 1102) {
                updateRestructureAudio(sQLiteDatabase, z);
            }
            if (i < 1103) {
                updateAddMetadata(sQLiteDatabase, z);
            }
            boolean z3 = i >= 1105 ? z2 : true;
            if (i < 1106) {
                updateMigrateLogs(sQLiteDatabase, z);
            }
            if (i < 1107) {
                updateAddSceneCaptureType(sQLiteDatabase, z);
            }
            if (i < 1108) {
                updateAddLocalMetadata(sQLiteDatabase, z);
            }
            if (i < 1109) {
                updateAddGeneration(sQLiteDatabase, z);
            }
            if (i < 1111) {
                recomputeMediaTypeValues(sQLiteDatabase);
            }
            if (i < 1112) {
                updateAddXmp(sQLiteDatabase, z);
            }
            if (i < 1115) {
                updateAudioAlbumId(sQLiteDatabase, z);
            }
            if (this.mLegacyProvider ? false : z3) {
                recomputeDataValues(sQLiteDatabase, z);
            }
        }
        createLatestViews(sQLiteDatabase, z);
        createLatestTriggers(sQLiteDatabase, z);
        getOrCreateUuid(sQLiteDatabase);
        long jElapsedRealtime2 = SystemClock.elapsedRealtime() - jElapsedRealtime;
        OnSchemaChangeListener onSchemaChangeListener = this.mSchemaListener;
        if (onSchemaChangeListener != null) {
            onSchemaChangeListener.onSchemaChange(this.mVolumeName, i, i2, getItemCount(sQLiteDatabase), jElapsedRealtime2);
        }
    }

    private void downgradeDatabase(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        long jElapsedRealtime = SystemClock.elapsedRealtime();
        createLatestSchema(sQLiteDatabase);
        long jElapsedRealtime2 = SystemClock.elapsedRealtime() - jElapsedRealtime;
        OnSchemaChangeListener onSchemaChangeListener = this.mSchemaListener;
        if (onSchemaChangeListener != null) {
            onSchemaChangeListener.onSchemaChange(this.mVolumeName, i, i2, getItemCount(sQLiteDatabase), jElapsedRealtime2);
        }
    }

    public static String getOrCreateUuid(SQLiteDatabase sQLiteDatabase) {
        try {
            return new String(Os.getxattr(sQLiteDatabase.getPath(), XATTR_UUID));
        } catch (ErrnoException e) {
            if (e.errno == OsConstants.ENODATA) {
                String string = UUID.randomUUID().toString();
                try {
                    Os.setxattr(sQLiteDatabase.getPath(), XATTR_UUID, string.getBytes(), 0);
                    return string;
                } catch (ErrnoException unused) {
                    throw new RuntimeException(e);
                }
            }
            throw new RuntimeException(e);
        }
    }

    private static void renameWithRetry(String str, String str2) throws IOException {
        long jElapsedRealtime = SystemClock.elapsedRealtime();
        while (SystemClock.elapsedRealtime() - jElapsedRealtime <= RENAME_TIMEOUT) {
            try {
                Os.rename(str, str2);
                return;
            } catch (ErrnoException e) {
                Log.i(Logging.TAG, "Failed to rename: " + e);
                Log.i(Logging.TAG, "Waiting for passthrough to be mounted...");
                SystemClock.sleep(100L);
            }
        }
        throw new IOException("Passthrough failed to mount");
    }

    public static long getGeneration(SQLiteDatabase sQLiteDatabase) {
        return android.database.DatabaseUtils.longForQuery(sQLiteDatabase, "SELECT generation FROM local_metadata;", null);
    }

    public static long getItemCount(SQLiteDatabase sQLiteDatabase) {
        return android.database.DatabaseUtils.longForQuery(sQLiteDatabase, "SELECT COUNT(_id) FROM files WHERE mime_type IS NOT NULL", null);
    }

    public boolean isExternal() {
        return !this.mInternal;
    }
}
