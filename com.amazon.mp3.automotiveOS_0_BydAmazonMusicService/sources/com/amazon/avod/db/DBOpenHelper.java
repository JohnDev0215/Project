package com.amazon.avod.db;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteAccessPermException;
import android.database.sqlite.SQLiteCantOpenDatabaseException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import com.amazon.avod.acos.StorageHelper;
import com.amazon.avod.db.util.DBSchemaUtils;
import com.amazon.avod.perf.Profiler;
import com.amazon.avod.upgrade.UpgradeManager;
import com.amazon.avod.util.DLog;
import com.google.common.base.Preconditions;
import java.io.File;
import java.io.FileFilter;
import java.util.Iterator;
import java.util.Map;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.inject.Provider;

/* JADX INFO: loaded from: classes2.dex */
public class DBOpenHelper extends SQLiteOpenHelper {
    private final ADatabaseInstance mDbInstance;
    private volatile boolean mIsVersionChange;
    private final StorageHelper mStorageHelper;
    private final UpgradeManager<SQLiteDatabase> mUpgradeManager;

    DBOpenHelper(@Nonnull Context context, @Nonnull ADatabaseInstance aDatabaseInstance, @Nonnull UpgradeManager<SQLiteDatabase> upgradeManager) {
        this(context, aDatabaseInstance, upgradeManager, StorageHelper.getInstance());
    }

    public DBOpenHelper(@Nonnull Context context, @Nonnull ADatabaseInstance aDatabaseInstance, @Nonnull UpgradeManager<SQLiteDatabase> upgradeManager, @Nonnull StorageHelper storageHelper) {
        super(context, aDatabaseInstance.getFilePath(storageHelper), (SQLiteDatabase.CursorFactory) null, aDatabaseInstance.getDBVersion());
        this.mIsVersionChange = false;
        this.mDbInstance = (ADatabaseInstance) Preconditions.checkNotNull(aDatabaseInstance, "instance");
        this.mUpgradeManager = (UpgradeManager) Preconditions.checkNotNull(upgradeManager, "upgradeManager");
        this.mStorageHelper = (StorageHelper) Preconditions.checkNotNull(storageHelper, "storageHelper");
        setWriteAheadLoggingEnabled(true);
    }

    private void createIndexes(SQLiteDatabase sQLiteDatabase, DBTable dBTable) {
        DLog.logf("Creating indexes for table \"%s\"", dBTable.getTableName());
        try {
            for (Map.Entry<String, String> entry : dBTable.getIndexes().entrySet()) {
                String strCreateIndexStatement = DBSchemaUtils.createIndexStatement(dBTable.getTableName(), entry.getKey(), entry.getValue());
                DLog.devf("Executing SQL statement: %s", strCreateIndexStatement);
                sQLiteDatabase.execSQL(strCreateIndexStatement);
            }
        } catch (SQLException e) {
            DLog.exceptionf(e, "Creating indexes", new Object[0]);
        }
    }

    private void createIndexes(SQLiteDatabase sQLiteDatabase) {
        Iterator<DBTable> it = this.mDbInstance.getDBTables().iterator();
        while (it.hasNext()) {
            createIndexes(sQLiteDatabase, it.next());
        }
    }

    private void createTables(SQLiteDatabase sQLiteDatabase) {
        Iterator<DBTable> it = this.mDbInstance.getDBTables().iterator();
        while (it.hasNext()) {
            createTable(sQLiteDatabase, it.next());
        }
    }

    private void createTable(SQLiteDatabase sQLiteDatabase, DBTable dBTable) {
        DLog.logf("Creating table \"%s\" in database \"%s\"", dBTable.getTableName(), this.mDbInstance.getDBName());
        try {
            String strCreateTableStatement = DBSchemaUtils.createTableStatement(dBTable.getTableName(), dBTable.getColumns(), dBTable.getConstraints());
            DLog.devf("Executing SQL statement: %s", strCreateTableStatement);
            sQLiteDatabase.execSQL(strCreateTableStatement);
        } catch (SQLException e) {
            DLog.exceptionf(e, "Creating table", new Object[0]);
        }
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public synchronized SQLiteDatabase getWritableDatabase() {
        return getDatabase(new Provider<SQLiteDatabase>() { // from class: com.amazon.avod.db.DBOpenHelper.1WritableDatabaseProvider
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // javax.inject.Provider
            public SQLiteDatabase get() {
                return DBOpenHelper.super.getWritableDatabase();
            }
        });
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public synchronized SQLiteDatabase getReadableDatabase() {
        return getDatabase(new Provider<SQLiteDatabase>() { // from class: com.amazon.avod.db.DBOpenHelper.1ReadableDatabaseProvider
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // javax.inject.Provider
            public SQLiteDatabase get() {
                try {
                    return DBOpenHelper.super.getReadableDatabase();
                } catch (SQLiteAccessPermException | SQLiteCantOpenDatabaseException e) {
                    throw new SQLiteException(String.format("Unable to force-fix permissions for %s", DBOpenHelper.this.mDbInstance.getClass().getSimpleName()), e);
                }
            }
        });
    }

    @Nonnull
    private SQLiteDatabase getDatabase(@Nonnull Provider<SQLiteDatabase> provider) {
        createDbDirectoryIfNeeded(getDatabaseName());
        SQLiteDatabase sQLiteDatabase = provider.get();
        if (!this.mIsVersionChange) {
            return sQLiteDatabase;
        }
        DLog.logf("Detected version change for %s, resetting DB connection", this.mDbInstance.getDBName());
        sQLiteDatabase.close();
        this.mIsVersionChange = false;
        SQLiteDatabase sQLiteDatabase2 = provider.get();
        Preconditions.checkState(!this.mIsVersionChange, "Database upgrade occurred on two subsequent fetch attempts for %s", this.mDbInstance.getDBName());
        Profiler.incrementCounter("AIVMCCORE-2849:DatabaseUpgradeConnectionReset-Occurrence");
        if (sQLiteDatabase == sQLiteDatabase2) {
            Profiler.incrementCounter("AIVMCCORE-2849:DatabaseUpgradeConnectionReset-Failed");
            DLog.warnf("Attempted to acquire a fresh database connection following an upgrade but obtained the same instance: %s", this.mDbInstance.getDBName());
        }
        return sQLiteDatabase2;
    }

    private void createDbDirectoryIfNeeded(@Nullable String str) {
        if (str == null) {
            return;
        }
        File parentFile = new File(str).getParentFile();
        if (parentFile.exists() ? true : parentFile.mkdirs()) {
            DLog.devf("Created directory %s", DLog.maskString(parentFile.getAbsolutePath()));
        }
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onCreate(SQLiteDatabase sQLiteDatabase) {
        createTables(sQLiteDatabase);
        createIndexes(sQLiteDatabase);
        configureDatabase(sQLiteDatabase);
        DLog.logf("Created database \"%s\"", this.mDbInstance.getDBName());
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onOpen(SQLiteDatabase sQLiteDatabase) {
        super.onOpen(sQLiteDatabase);
        configureDatabase(sQLiteDatabase);
        DLog.devf("Opened database \"%s\"", this.mDbInstance.getDBName());
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onConfigure(SQLiteDatabase sQLiteDatabase) {
        configureDatabase(sQLiteDatabase);
        DLog.devf("Configured database \"%s\"", this.mDbInstance.getDBName());
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        DLog.logf("Upgrading database \"%s\" from version %d to %d", this.mDbInstance.getDBName(), Integer.valueOf(i), Integer.valueOf(i2));
        upgradeDB(sQLiteDatabase, i, i2);
    }

    private void upgradeDB(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        this.mIsVersionChange = true;
        this.mUpgradeManager.upgrade(sQLiteDatabase, i, i2);
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    @Deprecated
    public void onDowngrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        DLog.logf("Downgrading database is not supported. The application will now crash. Please cleardata in order to resume normal functioning");
        super.onDowngrade(sQLiteDatabase, i, i2);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:21:0x005a A[PHI: r2
  0x005a: PHI (r2v7 android.database.sqlite.SQLiteDatabase) = (r2v6 android.database.sqlite.SQLiteDatabase), (r2v8 android.database.sqlite.SQLiteDatabase) binds: [B:20:0x0058, B:9:0x0040] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:25:0x0063  */
    /* JADX WARN: Type inference failed for: r2v0, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r2v2 */
    /* JADX WARN: Type inference failed for: r2v5, types: [android.database.sqlite.SQLiteClosable] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void cleanupDB() throws java.lang.Throwable {
        /*
            r8 = this;
            com.amazon.avod.perf.Profiler$TraceLevel r0 = com.amazon.avod.perf.Profiler.TraceLevel.INFO
            java.lang.Class r1 = r8.getClass()
            java.lang.String r1 = r1.getSimpleName()
            java.lang.String r2 = "%s:CleanupDB"
            com.amazon.avod.perf.TraceKey r0 = com.amazon.avod.perf.Profiler.beginTrace(r0, r2, r1)
            r1 = 0
            android.database.sqlite.SQLiteDatabase r2 = r8.getWritableDatabase()     // Catch: java.lang.Throwable -> L47 android.database.sqlite.SQLiteException -> L4c
            com.amazon.avod.db.ADatabaseInstance r3 = r8.mDbInstance     // Catch: java.lang.Throwable -> L43 android.database.sqlite.SQLiteException -> L45
            java.util.List r3 = r3.getDBTables()     // Catch: java.lang.Throwable -> L43 android.database.sqlite.SQLiteException -> L45
            java.util.Iterator r3 = r3.iterator()     // Catch: java.lang.Throwable -> L43 android.database.sqlite.SQLiteException -> L45
        L1f:
            boolean r4 = r3.hasNext()     // Catch: java.lang.Throwable -> L43 android.database.sqlite.SQLiteException -> L45
            if (r4 == 0) goto L40
            java.lang.Object r4 = r3.next()     // Catch: java.lang.Throwable -> L43 android.database.sqlite.SQLiteException -> L45
            com.amazon.avod.db.DBTable r4 = (com.amazon.avod.db.DBTable) r4     // Catch: java.lang.Throwable -> L43 android.database.sqlite.SQLiteException -> L45
            java.lang.String r5 = r4.getTableName()     // Catch: java.lang.Throwable -> L43 android.database.sqlite.SQLiteException -> L45
            java.lang.String r6 = r4.getCleanUpWhereClause()     // Catch: java.lang.Throwable -> L43 android.database.sqlite.SQLiteException -> L45
            r2.delete(r5, r6, r1)     // Catch: java.lang.Throwable -> L43 android.database.sqlite.SQLiteException -> L45
            java.lang.String r5 = "Cleaned up DB table: %s"
            java.lang.String r4 = r4.getTableName()     // Catch: java.lang.Throwable -> L43 android.database.sqlite.SQLiteException -> L45
            com.amazon.avod.util.DLog.devf(r5, r4)     // Catch: java.lang.Throwable -> L43 android.database.sqlite.SQLiteException -> L45
            goto L1f
        L40:
            if (r2 == 0) goto L5d
            goto L5a
        L43:
            r1 = move-exception
            goto L61
        L45:
            r1 = move-exception
            goto L50
        L47:
            r2 = move-exception
            r7 = r2
            r2 = r1
            r1 = r7
            goto L61
        L4c:
            r2 = move-exception
            r7 = r2
            r2 = r1
            r1 = r7
        L50:
            java.lang.String r3 = "Couldn't clean up database"
            r4 = 0
            java.lang.Object[] r4 = new java.lang.Object[r4]     // Catch: java.lang.Throwable -> L43
            com.amazon.avod.util.DLog.exceptionf(r1, r3, r4)     // Catch: java.lang.Throwable -> L43
            if (r2 == 0) goto L5d
        L5a:
            r2.close()
        L5d:
            com.amazon.avod.perf.Profiler.endTrace(r0)
            return
        L61:
            if (r2 == 0) goto L66
            r2.close()
        L66:
            com.amazon.avod.perf.Profiler.endTrace(r0)
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.avod.db.DBOpenHelper.cleanupDB():void");
    }

    boolean deleteDatabase() {
        SQLiteDatabase writableDatabase = getWritableDatabase();
        String path = writableDatabase.getPath();
        writableDatabase.close();
        return deleteDatabase(new File(path));
    }

    private void configureDatabase(@Nonnull SQLiteDatabase sQLiteDatabase) {
        boolean zEnableWriteAheadLogging = sQLiteDatabase.enableWriteAheadLogging();
        if (!sQLiteDatabase.isReadOnly() && !zEnableWriteAheadLogging) {
            DLog.errorf("Write ahead logging could not be enabled for a writable database connection: %s", this.mDbInstance.getDBName());
        }
        enableForeignKeys(sQLiteDatabase);
    }

    private void enableForeignKeys(@Nonnull SQLiteDatabase sQLiteDatabase) {
        if (sQLiteDatabase.isReadOnly()) {
            return;
        }
        sQLiteDatabase.execSQL("PRAGMA foreign_keys=ON;");
    }

    public static boolean deleteDatabase(@Nullable File file) {
        if (file == null) {
            return false;
        }
        boolean zDelete = file.delete() | false | new File(file.getPath() + "-journal").delete() | new File(file.getPath() + "-shm").delete() | new File(file.getPath() + "-wal").delete();
        File parentFile = file.getParentFile();
        if (parentFile != null) {
            final String str = file.getName() + "-mj";
            File[] fileArrListFiles = parentFile.listFiles(new FileFilter() { // from class: com.amazon.avod.db.DBOpenHelper.1
                @Override // java.io.FileFilter
                public boolean accept(File file2) {
                    return file2.getName().startsWith(str);
                }
            });
            if (fileArrListFiles != null) {
                for (File file2 : fileArrListFiles) {
                    zDelete |= file2.delete();
                }
            }
        }
        return zDelete;
    }
}
