package androidx.room;

import android.content.Context;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.room.util.CopyLock;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import androidx.sqlite.db.framework.FrameworkSQLiteOpenHelperFactory;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.Callable;

/* JADX INFO: loaded from: classes.dex */
class SQLiteCopyOpenHelper implements SupportSQLiteOpenHelper, DelegatingOpenHelper {

    @NonNull
    private final Context mContext;

    @Nullable
    private final String mCopyFromAssetPath;

    @Nullable
    private final File mCopyFromFile;

    @Nullable
    private final Callable<InputStream> mCopyFromInputStream;

    @Nullable
    private DatabaseConfiguration mDatabaseConfiguration;
    private final int mDatabaseVersion;

    @NonNull
    private final SupportSQLiteOpenHelper mDelegate;
    private boolean mVerified;

    public SQLiteCopyOpenHelper(@NonNull Context context, @Nullable String str, @Nullable File file, @Nullable Callable<InputStream> callable, int i, @NonNull SupportSQLiteOpenHelper supportSQLiteOpenHelper) {
        this.mContext = context;
        this.mCopyFromAssetPath = str;
        this.mCopyFromFile = file;
        this.mCopyFromInputStream = callable;
        this.mDatabaseVersion = i;
        this.mDelegate = supportSQLiteOpenHelper;
    }

    /* JADX WARN: Removed duplicated region for block: B:23:0x0080 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:24:0x0081  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void copyDatabaseFile(java.io.File r5, boolean r6) throws java.io.IOException {
        /*
            r4 = this;
            java.lang.String r0 = r4.mCopyFromAssetPath
            if (r0 == 0) goto L11
            android.content.Context r0 = r4.mContext
            android.content.res.AssetManager r0 = r0.getAssets()
            java.lang.String r1 = r4.mCopyFromAssetPath
            java.io.InputStream r0 = r0.open(r1)
            goto L2b
        L11:
            java.io.File r0 = r4.mCopyFromFile
            if (r0 == 0) goto L21
            java.io.FileInputStream r0 = new java.io.FileInputStream
            java.io.File r1 = r4.mCopyFromFile
            r0.<init>(r1)
            java.nio.channels.FileChannel r0 = r0.getChannel()
            goto L2f
        L21:
            java.util.concurrent.Callable<java.io.InputStream> r0 = r4.mCopyFromInputStream
            if (r0 == 0) goto Lb2
            java.lang.Object r0 = r0.call()     // Catch: java.lang.Exception -> La9
            java.io.InputStream r0 = (java.io.InputStream) r0     // Catch: java.lang.Exception -> La9
        L2b:
            java.nio.channels.ReadableByteChannel r0 = java.nio.channels.Channels.newChannel(r0)
        L2f:
            android.content.Context r1 = r4.mContext
            java.io.File r1 = r1.getCacheDir()
            java.lang.String r2 = "room-copy-helper"
            java.lang.String r3 = ".tmp"
            java.io.File r1 = java.io.File.createTempFile(r2, r3, r1)
            r1.deleteOnExit()
            java.io.FileOutputStream r2 = new java.io.FileOutputStream
            r2.<init>(r1)
            java.nio.channels.FileChannel r2 = r2.getChannel()
            androidx.room.util.FileUtil.copy(r0, r2)
            java.io.File r0 = r5.getParentFile()
            if (r0 == 0) goto L77
            boolean r2 = r0.exists()
            if (r2 != 0) goto L77
            boolean r0 = r0.mkdirs()
            if (r0 == 0) goto L60
            goto L77
        L60:
            java.io.IOException r6 = new java.io.IOException
            java.lang.String r0 = "Failed to create directories for "
            java.lang.StringBuilder r0 = spi.e.k(r0)
            java.lang.String r5 = r5.getAbsolutePath()
            r0.append(r5)
            java.lang.String r5 = r0.toString()
            r6.<init>(r5)
            throw r6
        L77:
            r4.dispatchOnOpenPrepackagedDatabase(r1, r6)
            boolean r6 = r1.renameTo(r5)
            if (r6 == 0) goto L81
            return
        L81:
            java.io.IOException r6 = new java.io.IOException
            java.lang.String r0 = "Failed to move intermediate file ("
            java.lang.StringBuilder r0 = spi.e.k(r0)
            java.lang.String r1 = r1.getAbsolutePath()
            r0.append(r1)
            java.lang.String r1 = ") to destination ("
            r0.append(r1)
            java.lang.String r5 = r5.getAbsolutePath()
            r0.append(r5)
            java.lang.String r5 = ")."
            r0.append(r5)
            java.lang.String r5 = r0.toString()
            r6.<init>(r5)
            throw r6
        La9:
            r5 = move-exception
            java.io.IOException r6 = new java.io.IOException
            java.lang.String r0 = "inputStreamCallable exception on call"
            r6.<init>(r0, r5)
            throw r6
        Lb2:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "copyFromAssetPath, copyFromFile and copyFromInputStream are all null!"
            r5.<init>(r6)
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.room.SQLiteCopyOpenHelper.copyDatabaseFile(java.io.File, boolean):void");
    }

    private SupportSQLiteOpenHelper createFrameworkOpenHelper(File file) {
        try {
            return new FrameworkSQLiteOpenHelperFactory().create(SupportSQLiteOpenHelper.Configuration.builder(this.mContext).name(file.getAbsolutePath()).callback(new SupportSQLiteOpenHelper.Callback(Math.max(DBUtil.readVersion(file), 1)) { // from class: androidx.room.SQLiteCopyOpenHelper.1
                @Override // androidx.sqlite.db.SupportSQLiteOpenHelper.Callback
                public void onCreate(@NonNull SupportSQLiteDatabase supportSQLiteDatabase) {
                }

                @Override // androidx.sqlite.db.SupportSQLiteOpenHelper.Callback
                public void onOpen(@NonNull SupportSQLiteDatabase supportSQLiteDatabase) {
                    int i = this.version;
                    if (i < 1) {
                        supportSQLiteDatabase.setVersion(i);
                    }
                }

                @Override // androidx.sqlite.db.SupportSQLiteOpenHelper.Callback
                public void onUpgrade(@NonNull SupportSQLiteDatabase supportSQLiteDatabase, int i, int i2) {
                }
            }).build());
        } catch (IOException e) {
            throw new RuntimeException("Malformed database file, unable to read version.", e);
        }
    }

    private void dispatchOnOpenPrepackagedDatabase(File file, boolean z) {
        DatabaseConfiguration databaseConfiguration = this.mDatabaseConfiguration;
        if (databaseConfiguration == null || databaseConfiguration.prepackagedDatabaseCallback == null) {
            return;
        }
        SupportSQLiteOpenHelper supportSQLiteOpenHelperCreateFrameworkOpenHelper = createFrameworkOpenHelper(file);
        try {
            this.mDatabaseConfiguration.prepackagedDatabaseCallback.onOpenPrepackagedDatabase(z ? supportSQLiteOpenHelperCreateFrameworkOpenHelper.getWritableDatabase() : supportSQLiteOpenHelperCreateFrameworkOpenHelper.getReadableDatabase());
        } finally {
            supportSQLiteOpenHelperCreateFrameworkOpenHelper.close();
        }
    }

    private void verifyDatabaseFile(boolean z) {
        String databaseName = getDatabaseName();
        File databasePath = this.mContext.getDatabasePath(databaseName);
        DatabaseConfiguration databaseConfiguration = this.mDatabaseConfiguration;
        CopyLock copyLock = new CopyLock(databaseName, this.mContext.getFilesDir(), databaseConfiguration == null || databaseConfiguration.multiInstanceInvalidation);
        try {
            copyLock.lock();
            if (!databasePath.exists()) {
                try {
                    copyDatabaseFile(databasePath, z);
                    copyLock.unlock();
                    return;
                } catch (IOException e) {
                    throw new RuntimeException("Unable to copy database file.", e);
                }
            }
            if (this.mDatabaseConfiguration == null) {
                copyLock.unlock();
                return;
            }
            try {
                int version = DBUtil.readVersion(databasePath);
                int i = this.mDatabaseVersion;
                if (version == i) {
                    copyLock.unlock();
                    return;
                }
                if (this.mDatabaseConfiguration.isMigrationRequired(version, i)) {
                    copyLock.unlock();
                    return;
                }
                if (this.mContext.deleteDatabase(databaseName)) {
                    try {
                        copyDatabaseFile(databasePath, z);
                    } catch (IOException e2) {
                        Log.w(Room.LOG_TAG, "Unable to copy database file.", e2);
                    }
                } else {
                    Log.w(Room.LOG_TAG, "Failed to delete database file (" + databaseName + ") for a copy destructive migration.");
                }
                copyLock.unlock();
                return;
            } catch (IOException e3) {
                Log.w(Room.LOG_TAG, "Unable to read database version.", e3);
                copyLock.unlock();
                return;
            }
        } catch (Throwable th) {
            copyLock.unlock();
            throw th;
        }
        copyLock.unlock();
        throw th;
    }

    @Override // androidx.sqlite.db.SupportSQLiteOpenHelper, java.io.Closeable, java.lang.AutoCloseable
    public synchronized void close() {
        this.mDelegate.close();
        this.mVerified = false;
    }

    @Override // androidx.sqlite.db.SupportSQLiteOpenHelper
    public String getDatabaseName() {
        return this.mDelegate.getDatabaseName();
    }

    @Override // androidx.room.DelegatingOpenHelper
    @NonNull
    public SupportSQLiteOpenHelper getDelegate() {
        return this.mDelegate;
    }

    @Override // androidx.sqlite.db.SupportSQLiteOpenHelper
    public synchronized SupportSQLiteDatabase getReadableDatabase() {
        if (!this.mVerified) {
            verifyDatabaseFile(false);
            this.mVerified = true;
        }
        return this.mDelegate.getReadableDatabase();
    }

    @Override // androidx.sqlite.db.SupportSQLiteOpenHelper
    public synchronized SupportSQLiteDatabase getWritableDatabase() {
        if (!this.mVerified) {
            verifyDatabaseFile(true);
            this.mVerified = true;
        }
        return this.mDelegate.getWritableDatabase();
    }

    public void setDatabaseConfiguration(@Nullable DatabaseConfiguration databaseConfiguration) {
        this.mDatabaseConfiguration = databaseConfiguration;
    }

    @Override // androidx.sqlite.db.SupportSQLiteOpenHelper
    @RequiresApi(api = 16)
    public void setWriteAheadLoggingEnabled(boolean z) {
        this.mDelegate.setWriteAheadLoggingEnabled(z);
    }
}
