package android.database.sqlite;

import android.app.ActivityThread;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.DatabaseUtils;
import android.database.SQLException;
import android.database.sqlite.SQLiteDebug;
import android.os.CancellationSignal;
import android.os.Looper;
import android.os.SystemProperties;
import android.text.TextUtils;
import android.util.ArraySet;
import android.util.EventLog;
import android.util.Log;
import android.util.Pair;
import android.util.Printer;
import com.android.internal.util.Preconditions;
import dalvik.system.CloseGuard;
import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.WeakHashMap;
import java.util.function.BinaryOperator;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

/* JADX INFO: loaded from: classes.dex */
public final class SQLiteDatabase extends SQLiteClosable {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    public static final int CONFLICT_ABORT = 2;
    public static final int CONFLICT_FAIL = 3;
    public static final int CONFLICT_IGNORE = 4;
    public static final int CONFLICT_NONE = 0;
    public static final int CONFLICT_REPLACE = 5;
    public static final int CONFLICT_ROLLBACK = 1;
    public static final int CREATE_IF_NECESSARY = 268435456;
    public static final int ENABLE_LEGACY_COMPATIBILITY_WAL = Integer.MIN_VALUE;
    public static final int ENABLE_WRITE_AHEAD_LOGGING = 536870912;
    private static final int EVENT_DB_CORRUPT = 75004;
    public static final int MAX_SQL_CACHE_SIZE = 100;
    public static final int NO_LOCALIZED_COLLATORS = 16;
    public static final int OPEN_READONLY = 1;
    public static final int OPEN_READWRITE = 0;
    private static final int OPEN_READ_MASK = 1;
    public static final int SQLITE_MAX_LIKE_PATTERN_LENGTH = 50000;
    private static final String TAG = "SQLiteDatabase";
    private final SQLiteDatabaseConfiguration mConfigurationLocked;
    private SQLiteConnectionPool mConnectionPoolLocked;
    private final CursorFactory mCursorFactory;
    private final DatabaseErrorHandler mErrorHandler;
    private boolean mHasAttachedDbsLocked;
    private static final boolean DEBUG_CLOSE_IDLE_CONNECTIONS = SystemProperties.getBoolean("persist.debug.sqlite.close_idle_connections", false);
    private static WeakHashMap<SQLiteDatabase, Object> sActiveDatabases = new WeakHashMap<>();
    public static final String[] CONFLICT_VALUES = {"", " OR ROLLBACK ", " OR ABORT ", " OR FAIL ", " OR IGNORE ", " OR REPLACE "};
    private final ThreadLocal<SQLiteSession> mThreadSession = ThreadLocal.withInitial(new Supplier() { // from class: android.database.sqlite.-$$Lambda$RBWjWVyGrOTsQrLCYzJ_G8Uk25Q
        @Override // java.util.function.Supplier
        public final Object get() {
            return this.f$0.createSession();
        }
    });
    private final Object mLock = new Object();
    private final CloseGuard mCloseGuardLocked = CloseGuard.get();

    public interface CursorFactory {
        Cursor newCursor(SQLiteDatabase sQLiteDatabase, SQLiteCursorDriver sQLiteCursorDriver, String str, SQLiteQuery sQLiteQuery);
    }

    public interface CustomFunction {
        void callback(String[] strArr);
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface DatabaseOpenFlags {
    }

    @Deprecated
    public boolean isDbLockedByOtherThreads() {
        return false;
    }

    @Deprecated
    public void markTableSyncable(String str, String str2) {
    }

    @Deprecated
    public void markTableSyncable(String str, String str2, String str3) {
    }

    @Deprecated
    public void setLockingEnabled(boolean z) {
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x0061  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private SQLiteDatabase(java.lang.String r2, int r3, android.database.sqlite.SQLiteDatabase.CursorFactory r4, android.database.DatabaseErrorHandler r5, int r6, int r7, long r8, java.lang.String r10, java.lang.String r11) {
        /*
            r1 = this;
            r1.<init>()
            android.database.sqlite.-$$Lambda$RBWjWVyGrOTsQrLCYzJ_G8Uk25Q r0 = new android.database.sqlite.-$$Lambda$RBWjWVyGrOTsQrLCYzJ_G8Uk25Q
            r0.<init>()
            java.lang.ThreadLocal r0 = java.lang.ThreadLocal.withInitial(r0)
            r1.mThreadSession = r0
            java.lang.Object r0 = new java.lang.Object
            r0.<init>()
            r1.mLock = r0
            dalvik.system.CloseGuard r0 = dalvik.system.CloseGuard.get()
            r1.mCloseGuardLocked = r0
            r1.mCursorFactory = r4
            if (r5 == 0) goto L20
            goto L25
        L20:
            android.database.DefaultDatabaseErrorHandler r5 = new android.database.DefaultDatabaseErrorHandler
            r5.<init>()
        L25:
            r1.mErrorHandler = r5
            android.database.sqlite.SQLiteDatabaseConfiguration r4 = new android.database.sqlite.SQLiteDatabaseConfiguration
            r4.<init>(r2, r3)
            r1.mConfigurationLocked = r4
            r4.lookasideSlotSize = r6
            android.database.sqlite.SQLiteDatabaseConfiguration r2 = r1.mConfigurationLocked
            r2.lookasideSlotCount = r7
            boolean r2 = android.app.ActivityManager.isLowRamDeviceStatic()
            if (r2 == 0) goto L43
            android.database.sqlite.SQLiteDatabaseConfiguration r2 = r1.mConfigurationLocked
            r3 = 0
            r2.lookasideSlotCount = r3
            android.database.sqlite.SQLiteDatabaseConfiguration r2 = r1.mConfigurationLocked
            r2.lookasideSlotSize = r3
        L43:
            r2 = 9223372036854775807(0x7fffffffffffffff, double:NaN)
            android.database.sqlite.SQLiteDatabaseConfiguration r4 = r1.mConfigurationLocked
            boolean r4 = r4.isInMemoryDb()
            if (r4 != 0) goto L61
            r4 = 0
            int r4 = (r8 > r4 ? 1 : (r8 == r4 ? 0 : -1))
            if (r4 < 0) goto L57
            goto L62
        L57:
            boolean r4 = android.database.sqlite.SQLiteDatabase.DEBUG_CLOSE_IDLE_CONNECTIONS
            if (r4 == 0) goto L61
            int r2 = android.database.sqlite.SQLiteGlobal.getIdleConnectionTimeout()
            long r8 = (long) r2
            goto L62
        L61:
            r8 = r2
        L62:
            android.database.sqlite.SQLiteDatabaseConfiguration r2 = r1.mConfigurationLocked
            r2.idleConnectionTimeoutMs = r8
            android.database.sqlite.SQLiteDatabaseConfiguration r2 = r1.mConfigurationLocked
            r2.journalMode = r10
            android.database.sqlite.SQLiteDatabaseConfiguration r2 = r1.mConfigurationLocked
            r2.syncMode = r11
            boolean r2 = android.database.sqlite.SQLiteCompatibilityWalFlags.isLegacyCompatibilityWalEnabled()
            if (r2 == 0) goto L7d
            android.database.sqlite.SQLiteDatabaseConfiguration r2 = r1.mConfigurationLocked
            int r3 = r2.openFlags
            r4 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r3 | r4
            r2.openFlags = r3
        L7d:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: android.database.sqlite.SQLiteDatabase.<init>(java.lang.String, int, android.database.sqlite.SQLiteDatabase$CursorFactory, android.database.DatabaseErrorHandler, int, int, long, java.lang.String, java.lang.String):void");
    }

    protected void finalize() throws Throwable {
        try {
            dispose(true);
        } finally {
            super.finalize();
        }
    }

    @Override // android.database.sqlite.SQLiteClosable
    protected void onAllReferencesReleased() {
        dispose(false);
    }

    private void dispose(boolean z) {
        SQLiteConnectionPool sQLiteConnectionPool;
        synchronized (this.mLock) {
            if (this.mCloseGuardLocked != null) {
                if (z) {
                    this.mCloseGuardLocked.warnIfOpen();
                }
                this.mCloseGuardLocked.close();
            }
            sQLiteConnectionPool = this.mConnectionPoolLocked;
            this.mConnectionPoolLocked = null;
        }
        if (z) {
            return;
        }
        synchronized (sActiveDatabases) {
            sActiveDatabases.remove(this);
        }
        if (sQLiteConnectionPool != null) {
            sQLiteConnectionPool.close();
        }
    }

    public static int releaseMemory() {
        return SQLiteGlobal.releaseMemory();
    }

    String getLabel() {
        String str;
        synchronized (this.mLock) {
            str = this.mConfigurationLocked.label;
        }
        return str;
    }

    void onCorruption() {
        EventLog.writeEvent(EVENT_DB_CORRUPT, getLabel());
        this.mErrorHandler.onCorruption(this);
    }

    SQLiteSession getThreadSession() {
        return this.mThreadSession.get();
    }

    SQLiteSession createSession() {
        SQLiteConnectionPool sQLiteConnectionPool;
        synchronized (this.mLock) {
            throwIfNotOpenLocked();
            sQLiteConnectionPool = this.mConnectionPoolLocked;
        }
        return new SQLiteSession(sQLiteConnectionPool);
    }

    int getThreadDefaultConnectionFlags(boolean z) {
        int i = z ? 1 : 2;
        return isMainThread() ? i | 4 : i;
    }

    private static boolean isMainThread() {
        Looper looperMyLooper = Looper.myLooper();
        return looperMyLooper != null && looperMyLooper == Looper.getMainLooper();
    }

    public void beginTransaction() {
        beginTransaction(null, true);
    }

    public void beginTransactionNonExclusive() {
        beginTransaction(null, false);
    }

    public void beginTransactionWithListener(SQLiteTransactionListener sQLiteTransactionListener) {
        beginTransaction(sQLiteTransactionListener, true);
    }

    public void beginTransactionWithListenerNonExclusive(SQLiteTransactionListener sQLiteTransactionListener) {
        beginTransaction(sQLiteTransactionListener, false);
    }

    private void beginTransaction(SQLiteTransactionListener sQLiteTransactionListener, boolean z) {
        acquireReference();
        try {
            getThreadSession().beginTransaction(z ? 2 : 1, sQLiteTransactionListener, getThreadDefaultConnectionFlags(false), null);
        } finally {
            releaseReference();
        }
    }

    public void endTransaction() {
        acquireReference();
        try {
            getThreadSession().endTransaction(null);
        } finally {
            releaseReference();
        }
    }

    public void setTransactionSuccessful() {
        acquireReference();
        try {
            getThreadSession().setTransactionSuccessful();
        } finally {
            releaseReference();
        }
    }

    public boolean inTransaction() {
        acquireReference();
        try {
            return getThreadSession().hasTransaction();
        } finally {
            releaseReference();
        }
    }

    public boolean isDbLockedByCurrentThread() {
        acquireReference();
        try {
            return getThreadSession().hasConnection();
        } finally {
            releaseReference();
        }
    }

    @Deprecated
    public boolean yieldIfContended() {
        return yieldIfContendedHelper(false, -1L);
    }

    public boolean yieldIfContendedSafely() {
        return yieldIfContendedHelper(true, -1L);
    }

    public boolean yieldIfContendedSafely(long j) {
        return yieldIfContendedHelper(true, j);
    }

    private boolean yieldIfContendedHelper(boolean z, long j) {
        acquireReference();
        try {
            return getThreadSession().yieldTransaction(j, z, null);
        } finally {
            releaseReference();
        }
    }

    @Deprecated
    public Map<String, String> getSyncedTables() {
        return new HashMap(0);
    }

    public static SQLiteDatabase openDatabase(String str, CursorFactory cursorFactory, int i) {
        return openDatabase(str, cursorFactory, i, null);
    }

    public static SQLiteDatabase openDatabase(File file, OpenParams openParams) {
        return openDatabase(file.getPath(), openParams);
    }

    private static SQLiteDatabase openDatabase(String str, OpenParams openParams) {
        Preconditions.checkArgument(openParams != null, "OpenParams cannot be null");
        SQLiteDatabase sQLiteDatabase = new SQLiteDatabase(str, openParams.mOpenFlags, openParams.mCursorFactory, openParams.mErrorHandler, openParams.mLookasideSlotSize, openParams.mLookasideSlotCount, openParams.mIdleConnectionTimeout, openParams.mJournalMode, openParams.mSyncMode);
        sQLiteDatabase.open();
        return sQLiteDatabase;
    }

    public static SQLiteDatabase openDatabase(String str, CursorFactory cursorFactory, int i, DatabaseErrorHandler databaseErrorHandler) {
        SQLiteDatabase sQLiteDatabase = new SQLiteDatabase(str, i, cursorFactory, databaseErrorHandler, -1, -1, -1L, null, null);
        sQLiteDatabase.open();
        return sQLiteDatabase;
    }

    public static SQLiteDatabase openOrCreateDatabase(File file, CursorFactory cursorFactory) {
        return openOrCreateDatabase(file.getPath(), cursorFactory);
    }

    public static SQLiteDatabase openOrCreateDatabase(String str, CursorFactory cursorFactory) {
        return openDatabase(str, cursorFactory, 268435456, null);
    }

    public static SQLiteDatabase openOrCreateDatabase(String str, CursorFactory cursorFactory, DatabaseErrorHandler databaseErrorHandler) {
        return openDatabase(str, cursorFactory, 268435456, databaseErrorHandler);
    }

    public static boolean deleteDatabase(File file) {
        return deleteDatabase(file, true);
    }

    public static boolean deleteDatabase(File file, boolean z) {
        if (file == null) {
            throw new IllegalArgumentException("file must not be null");
        }
        boolean zDelete = file.delete() | false | new File(file.getPath() + "-journal").delete() | new File(file.getPath() + "-shm").delete() | new File(file.getPath() + "-wal").delete();
        new File(file.getPath() + "-wipecheck").delete();
        File parentFile = file.getParentFile();
        if (parentFile != null) {
            final String str = file.getName() + "-mj";
            File[] fileArrListFiles = parentFile.listFiles(new FileFilter() { // from class: android.database.sqlite.SQLiteDatabase.1
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

    public void reopenReadWrite() {
        synchronized (this.mLock) {
            throwIfNotOpenLocked();
            if (isReadOnlyLocked()) {
                int i = this.mConfigurationLocked.openFlags;
                this.mConfigurationLocked.openFlags = (this.mConfigurationLocked.openFlags & (-2)) | 0;
                try {
                    this.mConnectionPoolLocked.reconfigure(this.mConfigurationLocked);
                } catch (RuntimeException e) {
                    this.mConfigurationLocked.openFlags = i;
                    throw e;
                }
            }
        }
    }

    private void open() {
        try {
            try {
                openInner();
            } catch (RuntimeException e) {
                if (SQLiteDatabaseCorruptException.isCorruptException(e)) {
                    Log.e(TAG, "Database corruption detected in open()", e);
                    onCorruption();
                    openInner();
                    return;
                }
                throw e;
            }
        } catch (SQLiteException e2) {
            Log.e(TAG, "Failed to open database '" + getLabel() + "'.", e2);
            close();
            throw e2;
        }
    }

    private void openInner() {
        synchronized (this.mLock) {
            this.mConnectionPoolLocked = SQLiteConnectionPool.open(this.mConfigurationLocked);
            this.mCloseGuardLocked.open("close");
        }
        synchronized (sActiveDatabases) {
            sActiveDatabases.put(this, null);
        }
    }

    public static SQLiteDatabase create(CursorFactory cursorFactory) {
        return openDatabase(SQLiteDatabaseConfiguration.MEMORY_DB_PATH, cursorFactory, 268435456);
    }

    public static SQLiteDatabase createInMemory(OpenParams openParams) {
        return openDatabase(SQLiteDatabaseConfiguration.MEMORY_DB_PATH, openParams.toBuilder().addOpenFlags(268435456).build());
    }

    public void setCustomScalarFunction(String str, UnaryOperator<String> unaryOperator) throws SQLiteException {
        Objects.requireNonNull(str);
        Objects.requireNonNull(unaryOperator);
        synchronized (this.mLock) {
            throwIfNotOpenLocked();
            this.mConfigurationLocked.customScalarFunctions.put(str, unaryOperator);
            try {
                this.mConnectionPoolLocked.reconfigure(this.mConfigurationLocked);
            } catch (RuntimeException e) {
                this.mConfigurationLocked.customScalarFunctions.remove(str);
                throw e;
            }
        }
    }

    public void setCustomAggregateFunction(String str, BinaryOperator<String> binaryOperator) throws SQLiteException {
        Objects.requireNonNull(str);
        Objects.requireNonNull(binaryOperator);
        synchronized (this.mLock) {
            throwIfNotOpenLocked();
            this.mConfigurationLocked.customAggregateFunctions.put(str, binaryOperator);
            try {
                this.mConnectionPoolLocked.reconfigure(this.mConfigurationLocked);
            } catch (RuntimeException e) {
                this.mConfigurationLocked.customAggregateFunctions.remove(str);
                throw e;
            }
        }
    }

    public void execPerConnectionSQL(String str, Object[] objArr) throws SQLException {
        Objects.requireNonNull(str);
        Object[] objArrDeepCopyOf = DatabaseUtils.deepCopyOf(objArr);
        synchronized (this.mLock) {
            throwIfNotOpenLocked();
            int size = this.mConfigurationLocked.perConnectionSql.size();
            this.mConfigurationLocked.perConnectionSql.add(Pair.create(str, objArrDeepCopyOf));
            try {
                this.mConnectionPoolLocked.reconfigure(this.mConfigurationLocked);
            } catch (RuntimeException e) {
                this.mConfigurationLocked.perConnectionSql.remove(size);
                throw e;
            }
        }
    }

    public int getVersion() {
        return Long.valueOf(DatabaseUtils.longForQuery(this, "PRAGMA user_version;", null)).intValue();
    }

    public void setVersion(int i) {
        execSQL("PRAGMA user_version = " + i);
    }

    public long getMaximumSize() {
        return DatabaseUtils.longForQuery(this, "PRAGMA max_page_count;", null) * getPageSize();
    }

    public long setMaximumSize(long j) {
        long pageSize = getPageSize();
        long j2 = j / pageSize;
        if (j % pageSize != 0) {
            j2++;
        }
        return DatabaseUtils.longForQuery(this, "PRAGMA max_page_count = " + j2, null) * pageSize;
    }

    public long getPageSize() {
        return DatabaseUtils.longForQuery(this, "PRAGMA page_size;", null);
    }

    public void setPageSize(long j) {
        execSQL("PRAGMA page_size = " + j);
    }

    public static String findEditTable(String str) {
        if (!TextUtils.isEmpty(str)) {
            int iIndexOf = str.indexOf(32);
            int iIndexOf2 = str.indexOf(44);
            if (iIndexOf <= 0 || (iIndexOf >= iIndexOf2 && iIndexOf2 >= 0)) {
                return iIndexOf2 > 0 ? (iIndexOf2 < iIndexOf || iIndexOf < 0) ? str.substring(0, iIndexOf2) : str : str;
            }
            return str.substring(0, iIndexOf);
        }
        throw new IllegalStateException("Invalid tables");
    }

    public SQLiteStatement compileStatement(String str) throws SQLException {
        acquireReference();
        try {
            return new SQLiteStatement(this, str, null);
        } finally {
            releaseReference();
        }
    }

    public Cursor query(boolean z, String str, String[] strArr, String str2, String[] strArr2, String str3, String str4, String str5, String str6) {
        return queryWithFactory(null, z, str, strArr, str2, strArr2, str3, str4, str5, str6, null);
    }

    public Cursor query(boolean z, String str, String[] strArr, String str2, String[] strArr2, String str3, String str4, String str5, String str6, CancellationSignal cancellationSignal) {
        return queryWithFactory(null, z, str, strArr, str2, strArr2, str3, str4, str5, str6, cancellationSignal);
    }

    public Cursor queryWithFactory(CursorFactory cursorFactory, boolean z, String str, String[] strArr, String str2, String[] strArr2, String str3, String str4, String str5, String str6) {
        return queryWithFactory(cursorFactory, z, str, strArr, str2, strArr2, str3, str4, str5, str6, null);
    }

    public Cursor queryWithFactory(CursorFactory cursorFactory, boolean z, String str, String[] strArr, String str2, String[] strArr2, String str3, String str4, String str5, String str6, CancellationSignal cancellationSignal) {
        acquireReference();
        try {
            return rawQueryWithFactory(cursorFactory, SQLiteQueryBuilder.buildQueryString(z, str, strArr, str2, str3, str4, str5, str6), strArr2, findEditTable(str), cancellationSignal);
        } finally {
            releaseReference();
        }
    }

    public Cursor query(String str, String[] strArr, String str2, String[] strArr2, String str3, String str4, String str5) {
        return query(false, str, strArr, str2, strArr2, str3, str4, str5, null);
    }

    public Cursor query(String str, String[] strArr, String str2, String[] strArr2, String str3, String str4, String str5, String str6) {
        return query(false, str, strArr, str2, strArr2, str3, str4, str5, str6);
    }

    public Cursor rawQuery(String str, String[] strArr) {
        return rawQueryWithFactory(null, str, strArr, null, null);
    }

    public Cursor rawQuery(String str, String[] strArr, CancellationSignal cancellationSignal) {
        return rawQueryWithFactory(null, str, strArr, null, cancellationSignal);
    }

    public Cursor rawQueryWithFactory(CursorFactory cursorFactory, String str, String[] strArr, String str2) {
        return rawQueryWithFactory(cursorFactory, str, strArr, str2, null);
    }

    public Cursor rawQueryWithFactory(CursorFactory cursorFactory, String str, String[] strArr, String str2, CancellationSignal cancellationSignal) {
        acquireReference();
        try {
            SQLiteDirectCursorDriver sQLiteDirectCursorDriver = new SQLiteDirectCursorDriver(this, str, str2, cancellationSignal);
            if (cursorFactory == null) {
                cursorFactory = this.mCursorFactory;
            }
            return sQLiteDirectCursorDriver.query(cursorFactory, strArr);
        } finally {
            releaseReference();
        }
    }

    public long insert(String str, String str2, ContentValues contentValues) {
        try {
            return insertWithOnConflict(str, str2, contentValues, 0);
        } catch (SQLException e) {
            Log.e(TAG, "Error inserting " + contentValues, e);
            return -1L;
        }
    }

    public long insertOrThrow(String str, String str2, ContentValues contentValues) throws SQLException {
        return insertWithOnConflict(str, str2, contentValues, 0);
    }

    public long replace(String str, String str2, ContentValues contentValues) {
        try {
            return insertWithOnConflict(str, str2, contentValues, 5);
        } catch (SQLException e) {
            Log.e(TAG, "Error inserting " + contentValues, e);
            return -1L;
        }
    }

    public long replaceOrThrow(String str, String str2, ContentValues contentValues) throws SQLException {
        return insertWithOnConflict(str, str2, contentValues, 5);
    }

    public long insertWithOnConflict(String str, String str2, ContentValues contentValues, int i) {
        acquireReference();
        try {
            StringBuilder sb = new StringBuilder();
            sb.append("INSERT");
            sb.append(CONFLICT_VALUES[i]);
            sb.append(" INTO ");
            sb.append(str);
            sb.append('(');
            Object[] objArr = null;
            int i2 = 0;
            int size = (contentValues == null || contentValues.isEmpty()) ? 0 : contentValues.size();
            if (size > 0) {
                objArr = new Object[size];
                int i3 = 0;
                for (String str3 : contentValues.keySet()) {
                    sb.append(i3 > 0 ? "," : "");
                    sb.append(str3);
                    objArr[i3] = contentValues.get(str3);
                    i3++;
                }
                sb.append(')');
                sb.append(" VALUES (");
                while (i2 < size) {
                    sb.append(i2 > 0 ? ",?" : "?");
                    i2++;
                }
            } else {
                sb.append(str2 + ") VALUES (NULL");
            }
            sb.append(')');
            SQLiteStatement sQLiteStatement = new SQLiteStatement(this, sb.toString(), objArr);
            try {
                return sQLiteStatement.executeInsert();
            } finally {
                sQLiteStatement.close();
            }
        } finally {
            releaseReference();
        }
    }

    public int delete(String str, String str2, String[] strArr) {
        String str3;
        acquireReference();
        try {
            StringBuilder sb = new StringBuilder();
            sb.append("DELETE FROM ");
            sb.append(str);
            if (TextUtils.isEmpty(str2)) {
                str3 = "";
            } else {
                str3 = " WHERE " + str2;
            }
            sb.append(str3);
            SQLiteStatement sQLiteStatement = new SQLiteStatement(this, sb.toString(), strArr);
            try {
                return sQLiteStatement.executeUpdateDelete();
            } finally {
                sQLiteStatement.close();
            }
        } finally {
            releaseReference();
        }
    }

    public int update(String str, ContentValues contentValues, String str2, String[] strArr) {
        return updateWithOnConflict(str, contentValues, str2, strArr, 0);
    }

    public int updateWithOnConflict(String str, ContentValues contentValues, String str2, String[] strArr, int i) {
        if (contentValues == null || contentValues.isEmpty()) {
            throw new IllegalArgumentException("Empty values");
        }
        acquireReference();
        try {
            StringBuilder sb = new StringBuilder(120);
            sb.append("UPDATE ");
            sb.append(CONFLICT_VALUES[i]);
            sb.append(str);
            sb.append(" SET ");
            int size = contentValues.size();
            int length = strArr == null ? size : strArr.length + size;
            Object[] objArr = new Object[length];
            int i2 = 0;
            for (String str3 : contentValues.keySet()) {
                sb.append(i2 > 0 ? "," : "");
                sb.append(str3);
                objArr[i2] = contentValues.get(str3);
                sb.append("=?");
                i2++;
            }
            if (strArr != null) {
                for (int i3 = size; i3 < length; i3++) {
                    objArr[i3] = strArr[i3 - size];
                }
            }
            if (!TextUtils.isEmpty(str2)) {
                sb.append(" WHERE ");
                sb.append(str2);
            }
            SQLiteStatement sQLiteStatement = new SQLiteStatement(this, sb.toString(), objArr);
            try {
                return sQLiteStatement.executeUpdateDelete();
            } finally {
                sQLiteStatement.close();
            }
        } finally {
            releaseReference();
        }
    }

    public void execSQL(String str) throws SQLException {
        executeSql(str, null);
    }

    public void execSQL(String str, Object[] objArr) throws SQLException {
        if (objArr == null) {
            throw new IllegalArgumentException("Empty bindArgs");
        }
        executeSql(str, objArr);
    }

    public int executeSql(String str, Object[] objArr) throws SQLException {
        acquireReference();
        try {
            int sqlStatementType = DatabaseUtils.getSqlStatementType(str);
            if (sqlStatementType == 3) {
                boolean z = false;
                synchronized (this.mLock) {
                    if (!this.mHasAttachedDbsLocked) {
                        this.mHasAttachedDbsLocked = true;
                        this.mConnectionPoolLocked.disableIdleConnectionHandler();
                        z = true;
                    }
                }
                if (z) {
                    disableWriteAheadLogging();
                }
            }
            try {
                SQLiteStatement sQLiteStatement = new SQLiteStatement(this, str, objArr);
                try {
                    int iExecuteUpdateDelete = sQLiteStatement.executeUpdateDelete();
                    sQLiteStatement.close();
                    return iExecuteUpdateDelete;
                } finally {
                }
            } finally {
                if (sqlStatementType == 8) {
                    this.mConnectionPoolLocked.closeAvailableNonPrimaryConnectionsAndLogExceptions();
                }
            }
        } finally {
            releaseReference();
        }
    }

    public void validateSql(String str, CancellationSignal cancellationSignal) {
        getThreadSession().prepare(str, getThreadDefaultConnectionFlags(true), cancellationSignal, null);
    }

    public boolean isReadOnly() {
        boolean zIsReadOnlyLocked;
        synchronized (this.mLock) {
            zIsReadOnlyLocked = isReadOnlyLocked();
        }
        return zIsReadOnlyLocked;
    }

    private boolean isReadOnlyLocked() {
        return (this.mConfigurationLocked.openFlags & 1) == 1;
    }

    public boolean isInMemoryDatabase() {
        boolean zIsInMemoryDb;
        synchronized (this.mLock) {
            zIsInMemoryDb = this.mConfigurationLocked.isInMemoryDb();
        }
        return zIsInMemoryDb;
    }

    public boolean isOpen() {
        boolean z;
        synchronized (this.mLock) {
            z = this.mConnectionPoolLocked != null;
        }
        return z;
    }

    public boolean needUpgrade(int i) {
        return i > getVersion();
    }

    public final String getPath() {
        String str;
        synchronized (this.mLock) {
            str = this.mConfigurationLocked.path;
        }
        return str;
    }

    public void setLocale(Locale locale) {
        if (locale == null) {
            throw new IllegalArgumentException("locale must not be null.");
        }
        synchronized (this.mLock) {
            throwIfNotOpenLocked();
            Locale locale2 = this.mConfigurationLocked.locale;
            this.mConfigurationLocked.locale = locale;
            try {
                this.mConnectionPoolLocked.reconfigure(this.mConfigurationLocked);
            } catch (RuntimeException e) {
                this.mConfigurationLocked.locale = locale2;
                throw e;
            }
        }
    }

    public void setMaxSqlCacheSize(int i) {
        if (i > 100 || i < 0) {
            throw new IllegalStateException("expected value between 0 and 100");
        }
        synchronized (this.mLock) {
            throwIfNotOpenLocked();
            int i2 = this.mConfigurationLocked.maxSqlCacheSize;
            this.mConfigurationLocked.maxSqlCacheSize = i;
            try {
                this.mConnectionPoolLocked.reconfigure(this.mConfigurationLocked);
            } catch (RuntimeException e) {
                this.mConfigurationLocked.maxSqlCacheSize = i2;
                throw e;
            }
        }
    }

    public void setForeignKeyConstraintsEnabled(boolean z) {
        synchronized (this.mLock) {
            throwIfNotOpenLocked();
            if (this.mConfigurationLocked.foreignKeyConstraintsEnabled == z) {
                return;
            }
            this.mConfigurationLocked.foreignKeyConstraintsEnabled = z;
            try {
                this.mConnectionPoolLocked.reconfigure(this.mConfigurationLocked);
            } catch (RuntimeException e) {
                this.mConfigurationLocked.foreignKeyConstraintsEnabled = !z;
                throw e;
            }
        }
    }

    public boolean enableWriteAheadLogging() {
        synchronized (this.mLock) {
            throwIfNotOpenLocked();
            if ((this.mConfigurationLocked.openFlags & 536870912) != 0) {
                return true;
            }
            if (isReadOnlyLocked()) {
                return false;
            }
            if (this.mConfigurationLocked.isInMemoryDb()) {
                Log.i(TAG, "can't enable WAL for memory databases.");
                return false;
            }
            if (this.mHasAttachedDbsLocked) {
                if (Log.isLoggable(TAG, 3)) {
                    Log.d(TAG, "this database: " + this.mConfigurationLocked.label + " has attached databases. can't  enable WAL.");
                }
                return false;
            }
            SQLiteDatabaseConfiguration sQLiteDatabaseConfiguration = this.mConfigurationLocked;
            sQLiteDatabaseConfiguration.openFlags = 536870912 | sQLiteDatabaseConfiguration.openFlags;
            try {
                this.mConnectionPoolLocked.reconfigure(this.mConfigurationLocked);
                return true;
            } catch (RuntimeException e) {
                this.mConfigurationLocked.openFlags &= -536870913;
                throw e;
            }
        }
    }

    public void disableWriteAheadLogging() {
        synchronized (this.mLock) {
            throwIfNotOpenLocked();
            int i = this.mConfigurationLocked.openFlags;
            boolean z = true;
            boolean z2 = (536870912 & i) != 0;
            if ((Integer.MIN_VALUE & i) == 0) {
                z = false;
            }
            if (z2 || z) {
                this.mConfigurationLocked.openFlags &= -536870913;
                this.mConfigurationLocked.openFlags &= Integer.MAX_VALUE;
                try {
                    this.mConnectionPoolLocked.reconfigure(this.mConfigurationLocked);
                } catch (RuntimeException e) {
                    this.mConfigurationLocked.openFlags = i;
                    throw e;
                }
            }
        }
    }

    public boolean isWriteAheadLoggingEnabled() {
        boolean z;
        synchronized (this.mLock) {
            throwIfNotOpenLocked();
            z = (this.mConfigurationLocked.openFlags & 536870912) != 0;
        }
        return z;
    }

    static ArrayList<SQLiteDebug.DbStats> getDbStats() {
        ArrayList<SQLiteDebug.DbStats> arrayList = new ArrayList<>();
        Iterator<SQLiteDatabase> it = getActiveDatabases().iterator();
        while (it.hasNext()) {
            it.next().collectDbStats(arrayList);
        }
        return arrayList;
    }

    private void collectDbStats(ArrayList<SQLiteDebug.DbStats> arrayList) {
        synchronized (this.mLock) {
            if (this.mConnectionPoolLocked != null) {
                this.mConnectionPoolLocked.collectDbStats(arrayList);
            }
        }
    }

    private static ArrayList<SQLiteDatabase> getActiveDatabases() {
        ArrayList<SQLiteDatabase> arrayList = new ArrayList<>();
        synchronized (sActiveDatabases) {
            arrayList.addAll(sActiveDatabases.keySet());
        }
        return arrayList;
    }

    static void dumpAll(Printer printer, boolean z, boolean z2) {
        ArraySet arraySet = new ArraySet();
        Iterator<SQLiteDatabase> it = getActiveDatabases().iterator();
        while (it.hasNext()) {
            it.next().dump(printer, z, z2, arraySet);
        }
        if (arraySet.size() > 0) {
            String[] strArr = (String[]) arraySet.toArray(new String[arraySet.size()]);
            Arrays.sort(strArr);
            for (String str : strArr) {
                dumpDatabaseDirectory(printer, new File(str), z2);
            }
        }
    }

    private void dump(Printer printer, boolean z, boolean z2, ArraySet arraySet) {
        synchronized (this.mLock) {
            if (this.mConnectionPoolLocked != null) {
                printer.println("");
                this.mConnectionPoolLocked.dump(printer, z, arraySet);
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:20:0x005f  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static void dumpDatabaseDirectory(android.util.Printer r7, java.io.File r8, boolean r9) {
        /*
            java.lang.String r0 = ""
            r7.println(r0)
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "Database files in "
            r0.append(r1)
            java.lang.String r1 = r8.getAbsolutePath()
            r0.append(r1)
            java.lang.String r1 = ":"
            r0.append(r1)
            java.lang.String r0 = r0.toString()
            r7.println(r0)
            java.io.File[] r8 = r8.listFiles()
            if (r8 == 0) goto L8b
            int r0 = r8.length
            if (r0 != 0) goto L2c
            goto L8b
        L2c:
            android.database.sqlite.-$$Lambda$SQLiteDatabase$1FsSJH2q7x3eeDFXCAu9l4piDsE r0 = new java.util.Comparator() { // from class: android.database.sqlite.-$$Lambda$SQLiteDatabase$1FsSJH2q7x3eeDFXCAu9l4piDsE
                static {
                    /*
                        android.database.sqlite.-$$Lambda$SQLiteDatabase$1FsSJH2q7x3eeDFXCAu9l4piDsE r0 = new android.database.sqlite.-$$Lambda$SQLiteDatabase$1FsSJH2q7x3eeDFXCAu9l4piDsE
                        r0.<init>()
                        
                        // error: 0x0005: SPUT (r0 I:android.database.sqlite.-$$Lambda$SQLiteDatabase$1FsSJH2q7x3eeDFXCAu9l4piDsE) android.database.sqlite.-$$Lambda$SQLiteDatabase$1FsSJH2q7x3eeDFXCAu9l4piDsE.INSTANCE android.database.sqlite.-$$Lambda$SQLiteDatabase$1FsSJH2q7x3eeDFXCAu9l4piDsE
                        return
                    */
                    throw new UnsupportedOperationException("Method not decompiled: android.database.sqlite.$$Lambda$SQLiteDatabase$1FsSJH2q7x3eeDFXCAu9l4piDsE.<clinit>():void");
                }

                {
                    /*
                        r0 = this;
                        r0.<init>()
                        return
                    */
                    throw new UnsupportedOperationException("Method not decompiled: android.database.sqlite.$$Lambda$SQLiteDatabase$1FsSJH2q7x3eeDFXCAu9l4piDsE.<init>():void");
                }

                @Override // java.util.Comparator
                public final int compare(java.lang.Object r1, java.lang.Object r2) {
                    /*
                        r0 = this;
                        java.io.File r1 = (java.io.File) r1
                        java.io.File r2 = (java.io.File) r2
                        int r1 = android.database.sqlite.SQLiteDatabase.lambda$dumpDatabaseDirectory$0(r1, r2)
                        return r1
                    */
                    throw new UnsupportedOperationException("Method not decompiled: android.database.sqlite.$$Lambda$SQLiteDatabase$1FsSJH2q7x3eeDFXCAu9l4piDsE.compare(java.lang.Object, java.lang.Object):int");
                }
            }
            java.util.Arrays.sort(r8, r0)
            int r0 = r8.length
            r1 = 0
            r2 = r1
        L34:
            if (r2 >= r0) goto L8a
            r3 = r8[r2]
            if (r9 == 0) goto L5f
            java.lang.String r4 = r3.getName()
            java.lang.String r5 = ".db"
            boolean r5 = r4.endsWith(r5)
            if (r5 != 0) goto L5f
            java.lang.String r5 = ".db-wal"
            boolean r5 = r4.endsWith(r5)
            if (r5 != 0) goto L5f
            java.lang.String r5 = ".db-journal"
            boolean r5 = r4.endsWith(r5)
            if (r5 != 0) goto L5f
            java.lang.String r5 = "-wipecheck"
            boolean r4 = r4.endsWith(r5)
            if (r4 != 0) goto L5f
            goto L87
        L5f:
            r4 = 3
            java.lang.Object[] r4 = new java.lang.Object[r4]
            java.lang.String r5 = r3.getName()
            r4[r1] = r5
            long r5 = r3.length()
            java.lang.Long r5 = java.lang.Long.valueOf(r5)
            r6 = 1
            r4[r6] = r5
            r5 = 2
            java.lang.String r3 = r3.getAbsolutePath()
            java.lang.String r3 = getFileTimestamps(r3)
            r4[r5] = r3
            java.lang.String r3 = "  %-40s %7db %s"
            java.lang.String r3 = java.lang.String.format(r3, r4)
            r7.println(r3)
        L87:
            int r2 = r2 + 1
            goto L34
        L8a:
            return
        L8b:
            java.lang.String r8 = "  [none]"
            r7.println(r8)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: android.database.sqlite.SQLiteDatabase.dumpDatabaseDirectory(android.util.Printer, java.io.File, boolean):void");
    }

    public List<Pair<String, String>> getAttachedDbs() {
        ArrayList arrayList = new ArrayList();
        synchronized (this.mLock) {
            Cursor cursorRawQuery = null;
            if (this.mConnectionPoolLocked == null) {
                return null;
            }
            if (!this.mHasAttachedDbsLocked) {
                arrayList.add(new Pair("main", this.mConfigurationLocked.path));
                return arrayList;
            }
            acquireReference();
            try {
                try {
                    cursorRawQuery = rawQuery("pragma database_list;", null);
                    while (cursorRawQuery.moveToNext()) {
                        arrayList.add(new Pair(cursorRawQuery.getString(1), cursorRawQuery.getString(2)));
                    }
                    return arrayList;
                } finally {
                    if (cursorRawQuery != null) {
                        cursorRawQuery.close();
                    }
                }
            } finally {
                releaseReference();
            }
        }
    }

    public boolean isDatabaseIntegrityOk() {
        List<Pair<String, String>> arrayList;
        acquireReference();
        try {
            try {
                arrayList = getAttachedDbs();
            } catch (SQLiteException unused) {
                arrayList = new ArrayList<>();
                arrayList.add(new Pair<>("main", getPath()));
            }
            if (arrayList == null) {
                throw new IllegalStateException("databaselist for: " + getPath() + " couldn't be retrieved. probably because the database is closed");
            }
            for (int i = 0; i < arrayList.size(); i++) {
                Pair<String, String> pair = arrayList.get(i);
                SQLiteStatement sQLiteStatementCompileStatement = null;
                try {
                    sQLiteStatementCompileStatement = compileStatement("PRAGMA " + pair.first + ".integrity_check(1);");
                    String strSimpleQueryForString = sQLiteStatementCompileStatement.simpleQueryForString();
                    if (!strSimpleQueryForString.equalsIgnoreCase("ok")) {
                        Log.e(TAG, "PRAGMA integrity_check on " + pair.second + " returned: " + strSimpleQueryForString);
                        return false;
                    }
                    if (sQLiteStatementCompileStatement != null) {
                        sQLiteStatementCompileStatement.close();
                    }
                } finally {
                    if (sQLiteStatementCompileStatement != null) {
                        sQLiteStatementCompileStatement.close();
                    }
                }
            }
            releaseReference();
            return true;
        } finally {
            releaseReference();
        }
    }

    public String toString() {
        return "SQLiteDatabase: " + getPath();
    }

    private void throwIfNotOpenLocked() {
        if (this.mConnectionPoolLocked != null) {
            return;
        }
        throw new IllegalStateException("The database '" + this.mConfigurationLocked.label + "' is not open.");
    }

    public static final class OpenParams {
        private final CursorFactory mCursorFactory;
        private final DatabaseErrorHandler mErrorHandler;
        private final long mIdleConnectionTimeout;
        private final String mJournalMode;
        private final int mLookasideSlotCount;
        private final int mLookasideSlotSize;
        private final int mOpenFlags;
        private final String mSyncMode;

        private OpenParams(int i, CursorFactory cursorFactory, DatabaseErrorHandler databaseErrorHandler, int i2, int i3, long j, String str, String str2) {
            this.mOpenFlags = i;
            this.mCursorFactory = cursorFactory;
            this.mErrorHandler = databaseErrorHandler;
            this.mLookasideSlotSize = i2;
            this.mLookasideSlotCount = i3;
            this.mIdleConnectionTimeout = j;
            this.mJournalMode = str;
            this.mSyncMode = str2;
        }

        public int getLookasideSlotSize() {
            return this.mLookasideSlotSize;
        }

        public int getLookasideSlotCount() {
            return this.mLookasideSlotCount;
        }

        public int getOpenFlags() {
            return this.mOpenFlags;
        }

        public CursorFactory getCursorFactory() {
            return this.mCursorFactory;
        }

        public DatabaseErrorHandler getErrorHandler() {
            return this.mErrorHandler;
        }

        public long getIdleConnectionTimeout() {
            return this.mIdleConnectionTimeout;
        }

        public String getJournalMode() {
            return this.mJournalMode;
        }

        public String getSynchronousMode() {
            return this.mSyncMode;
        }

        public Builder toBuilder() {
            return new Builder(this);
        }

        public static final class Builder {
            private CursorFactory mCursorFactory;
            private DatabaseErrorHandler mErrorHandler;
            private long mIdleConnectionTimeout;
            private String mJournalMode;
            private int mLookasideSlotCount;
            private int mLookasideSlotSize;
            private int mOpenFlags;
            private String mSyncMode;

            public Builder() {
                this.mLookasideSlotSize = -1;
                this.mLookasideSlotCount = -1;
                this.mIdleConnectionTimeout = -1L;
            }

            public Builder(OpenParams openParams) {
                this.mLookasideSlotSize = -1;
                this.mLookasideSlotCount = -1;
                this.mIdleConnectionTimeout = -1L;
                this.mLookasideSlotSize = openParams.mLookasideSlotSize;
                this.mLookasideSlotCount = openParams.mLookasideSlotCount;
                this.mOpenFlags = openParams.mOpenFlags;
                this.mCursorFactory = openParams.mCursorFactory;
                this.mErrorHandler = openParams.mErrorHandler;
                this.mJournalMode = openParams.mJournalMode;
                this.mSyncMode = openParams.mSyncMode;
            }

            public Builder setLookasideConfig(int i, int i2) {
                boolean z = true;
                Preconditions.checkArgument(i >= 0, "lookasideSlotCount cannot be negative");
                Preconditions.checkArgument(i2 >= 0, "lookasideSlotSize cannot be negative");
                if ((i <= 0 || i2 <= 0) && (i2 != 0 || i != 0)) {
                    z = false;
                }
                Preconditions.checkArgument(z, "Invalid configuration: " + i + ", " + i2);
                this.mLookasideSlotSize = i;
                this.mLookasideSlotCount = i2;
                return this;
            }

            public boolean isWriteAheadLoggingEnabled() {
                return (this.mOpenFlags & 536870912) != 0;
            }

            public Builder setOpenFlags(int i) {
                this.mOpenFlags = i;
                return this;
            }

            public Builder addOpenFlags(int i) {
                this.mOpenFlags = i | this.mOpenFlags;
                return this;
            }

            public Builder removeOpenFlags(int i) {
                this.mOpenFlags = (~i) & this.mOpenFlags;
                return this;
            }

            public void setWriteAheadLoggingEnabled(boolean z) {
                if (z) {
                    addOpenFlags(536870912);
                } else {
                    removeOpenFlags(536870912);
                }
            }

            public Builder setCursorFactory(CursorFactory cursorFactory) {
                this.mCursorFactory = cursorFactory;
                return this;
            }

            public Builder setErrorHandler(DatabaseErrorHandler databaseErrorHandler) {
                this.mErrorHandler = databaseErrorHandler;
                return this;
            }

            @Deprecated
            public Builder setIdleConnectionTimeout(long j) {
                Preconditions.checkArgument(j >= 0, "idle connection timeout cannot be negative");
                this.mIdleConnectionTimeout = j;
                return this;
            }

            public Builder setJournalMode(String str) {
                Objects.requireNonNull(str);
                this.mJournalMode = str;
                return this;
            }

            public Builder setSynchronousMode(String str) {
                Objects.requireNonNull(str);
                this.mSyncMode = str;
                return this;
            }

            public OpenParams build() {
                return new OpenParams(this.mOpenFlags, this.mCursorFactory, this.mErrorHandler, this.mLookasideSlotSize, this.mLookasideSlotCount, this.mIdleConnectionTimeout, this.mJournalMode, this.mSyncMode);
            }
        }
    }

    public static void wipeDetected(String str, String str2) {
        StringBuilder sb = new StringBuilder();
        sb.append("DB wipe detected: package=");
        sb.append(ActivityThread.currentPackageName());
        sb.append(" reason=");
        sb.append(str2);
        sb.append(" file=");
        sb.append(str);
        sb.append(" ");
        sb.append(getFileTimestamps(str));
        sb.append(" checkfile ");
        sb.append(getFileTimestamps(str + "-wipecheck"));
        wtfAsSystemServer(TAG, sb.toString(), new Throwable("STACKTRACE"));
    }

    public static String getFileTimestamps(String str) {
        try {
            BasicFileAttributes attributes = Files.readAttributes(FileSystems.getDefault().getPath(str, new String[0]), (Class<BasicFileAttributes>) BasicFileAttributes.class, new LinkOption[0]);
            return "ctime=" + attributes.creationTime() + " mtime=" + attributes.lastModifiedTime() + " atime=" + attributes.lastAccessTime();
        } catch (IOException unused) {
            return "[unable to obtain timestamp]";
        }
    }

    static void wtfAsSystemServer(String str, String str2, Throwable th) {
        Log.e(str, str2, th);
        ContentResolver.onDbCorruption(str, str2, th);
    }
}
