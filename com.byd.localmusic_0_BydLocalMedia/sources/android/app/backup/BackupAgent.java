package android.app.backup;

import android.app.IBackupAgent;
import android.app.QueuedWork;
import android.app.backup.FullBackup;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.pm.ApplicationInfo;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.ParcelFileDescriptor;
import android.os.Process;
import android.os.RemoteException;
import android.os.UserHandle;
import android.system.ErrnoException;
import android.system.Os;
import android.system.OsConstants;
import android.system.StructStat;
import android.util.ArraySet;
import android.util.Log;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CountDownLatch;
import libcore.io.IoUtils;
import org.xmlpull.v1.XmlPullParserException;

/* JADX INFO: loaded from: classes.dex */
public abstract class BackupAgent extends ContextWrapper {
    private static final boolean DEBUG = false;
    public static final int FLAG_CLIENT_SIDE_ENCRYPTION_ENABLED = 1;
    public static final int FLAG_DEVICE_TO_DEVICE_TRANSFER = 2;
    public static final int FLAG_FAKE_CLIENT_SIDE_ENCRYPTION_ENABLED = Integer.MIN_VALUE;
    public static final int RESULT_ERROR = -1;
    public static final int RESULT_SUCCESS = 0;
    private static final String TAG = "BackupAgent";
    public static final int TYPE_DIRECTORY = 2;
    public static final int TYPE_EOF = 0;
    public static final int TYPE_FILE = 1;
    public static final int TYPE_SYMLINK = 3;
    private final IBinder mBinder;
    Handler mHandler;
    private UserHandle mUser;

    private boolean areIncludeRequiredTransportFlagsSatisfied(int i, int i2) {
        return (i2 & i) == i;
    }

    public abstract void onBackup(ParcelFileDescriptor parcelFileDescriptor, BackupDataOutput backupDataOutput, ParcelFileDescriptor parcelFileDescriptor2) throws IOException;

    public void onCreate() {
    }

    public void onDestroy() {
    }

    public void onQuotaExceeded(long j, long j2) {
    }

    public abstract void onRestore(BackupDataInput backupDataInput, int i, ParcelFileDescriptor parcelFileDescriptor) throws IOException;

    public void onRestoreFinished() {
    }

    Handler getHandler() {
        if (this.mHandler == null) {
            this.mHandler = new Handler(Looper.getMainLooper());
        }
        return this.mHandler;
    }

    class SharedPrefsSynchronizer implements Runnable {
        public final CountDownLatch mLatch = new CountDownLatch(1);

        SharedPrefsSynchronizer() {
        }

        @Override // java.lang.Runnable
        public void run() {
            QueuedWork.waitToFinish();
            this.mLatch.countDown();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void waitForSharedPrefs() {
        Handler handler = getHandler();
        SharedPrefsSynchronizer sharedPrefsSynchronizer = new SharedPrefsSynchronizer();
        handler.postAtFrontOfQueue(sharedPrefsSynchronizer);
        try {
            sharedPrefsSynchronizer.mLatch.await();
        } catch (InterruptedException unused) {
        }
    }

    public BackupAgent() {
        super(null);
        this.mHandler = null;
        this.mBinder = new BackupServiceBinder().asBinder();
    }

    public void onCreate(UserHandle userHandle) {
        onCreate();
        this.mUser = userHandle;
    }

    public void onRestore(BackupDataInput backupDataInput, long j, ParcelFileDescriptor parcelFileDescriptor) throws IOException {
        onRestore(backupDataInput, (int) j, parcelFileDescriptor);
    }

    public void onRestore(BackupDataInput backupDataInput, long j, ParcelFileDescriptor parcelFileDescriptor, Set<String> set) throws IOException {
        onRestore(backupDataInput, j, parcelFileDescriptor);
    }

    public void onFullBackup(FullBackupDataOutput fullBackupDataOutput) throws IOException {
        FullBackup.BackupScheme backupScheme = FullBackup.getBackupScheme(this);
        if (backupScheme.isFullBackupContentEnabled()) {
            try {
                Map<String, Set<FullBackup.BackupScheme.PathWithRequiredFlags>> mapMaybeParseAndGetCanonicalIncludePaths = backupScheme.maybeParseAndGetCanonicalIncludePaths();
                ArraySet<FullBackup.BackupScheme.PathWithRequiredFlags> arraySetMaybeParseAndGetCanonicalExcludePaths = backupScheme.maybeParseAndGetCanonicalExcludePaths();
                String packageName = getPackageName();
                ApplicationInfo applicationInfo = getApplicationInfo();
                Context contextCreateCredentialProtectedStorageContext = createCredentialProtectedStorageContext();
                String canonicalPath = contextCreateCredentialProtectedStorageContext.getDataDir().getCanonicalPath();
                String canonicalPath2 = contextCreateCredentialProtectedStorageContext.getFilesDir().getCanonicalPath();
                String canonicalPath3 = contextCreateCredentialProtectedStorageContext.getNoBackupFilesDir().getCanonicalPath();
                String canonicalPath4 = contextCreateCredentialProtectedStorageContext.getDatabasePath("foo").getParentFile().getCanonicalPath();
                String canonicalPath5 = contextCreateCredentialProtectedStorageContext.getSharedPreferencesPath("foo").getParentFile().getCanonicalPath();
                String canonicalPath6 = contextCreateCredentialProtectedStorageContext.getCacheDir().getCanonicalPath();
                String canonicalPath7 = contextCreateCredentialProtectedStorageContext.getCodeCacheDir().getCanonicalPath();
                Context contextCreateDeviceProtectedStorageContext = createDeviceProtectedStorageContext();
                String canonicalPath8 = contextCreateDeviceProtectedStorageContext.getDataDir().getCanonicalPath();
                String canonicalPath9 = contextCreateDeviceProtectedStorageContext.getFilesDir().getCanonicalPath();
                String canonicalPath10 = contextCreateDeviceProtectedStorageContext.getNoBackupFilesDir().getCanonicalPath();
                String canonicalPath11 = contextCreateDeviceProtectedStorageContext.getDatabasePath("foo").getParentFile().getCanonicalPath();
                String canonicalPath12 = contextCreateDeviceProtectedStorageContext.getSharedPreferencesPath("foo").getParentFile().getCanonicalPath();
                String canonicalPath13 = contextCreateDeviceProtectedStorageContext.getCacheDir().getCanonicalPath();
                String canonicalPath14 = contextCreateDeviceProtectedStorageContext.getCodeCacheDir().getCanonicalPath();
                String canonicalPath15 = applicationInfo.nativeLibraryDir != null ? new File(applicationInfo.nativeLibraryDir).getCanonicalPath() : null;
                ArraySet<String> arraySet = new ArraySet<>();
                arraySet.add(canonicalPath2);
                arraySet.add(canonicalPath3);
                arraySet.add(canonicalPath4);
                arraySet.add(canonicalPath5);
                arraySet.add(canonicalPath6);
                arraySet.add(canonicalPath7);
                arraySet.add(canonicalPath9);
                arraySet.add(canonicalPath10);
                arraySet.add(canonicalPath11);
                arraySet.add(canonicalPath12);
                arraySet.add(canonicalPath13);
                arraySet.add(canonicalPath14);
                if (canonicalPath15 != null) {
                    arraySet.add(canonicalPath15);
                }
                applyXmlFiltersAndDoFullBackupForDomain(packageName, "r", mapMaybeParseAndGetCanonicalIncludePaths, arraySetMaybeParseAndGetCanonicalExcludePaths, arraySet, fullBackupDataOutput);
                arraySet.add(canonicalPath);
                applyXmlFiltersAndDoFullBackupForDomain(packageName, FullBackup.DEVICE_ROOT_TREE_TOKEN, mapMaybeParseAndGetCanonicalIncludePaths, arraySetMaybeParseAndGetCanonicalExcludePaths, arraySet, fullBackupDataOutput);
                arraySet.add(canonicalPath8);
                arraySet.remove(canonicalPath2);
                applyXmlFiltersAndDoFullBackupForDomain(packageName, FullBackup.FILES_TREE_TOKEN, mapMaybeParseAndGetCanonicalIncludePaths, arraySetMaybeParseAndGetCanonicalExcludePaths, arraySet, fullBackupDataOutput);
                arraySet.add(canonicalPath2);
                arraySet.remove(canonicalPath9);
                applyXmlFiltersAndDoFullBackupForDomain(packageName, FullBackup.DEVICE_FILES_TREE_TOKEN, mapMaybeParseAndGetCanonicalIncludePaths, arraySetMaybeParseAndGetCanonicalExcludePaths, arraySet, fullBackupDataOutput);
                arraySet.add(canonicalPath9);
                arraySet.remove(canonicalPath4);
                applyXmlFiltersAndDoFullBackupForDomain(packageName, FullBackup.DATABASE_TREE_TOKEN, mapMaybeParseAndGetCanonicalIncludePaths, arraySetMaybeParseAndGetCanonicalExcludePaths, arraySet, fullBackupDataOutput);
                arraySet.add(canonicalPath4);
                arraySet.remove(canonicalPath11);
                applyXmlFiltersAndDoFullBackupForDomain(packageName, FullBackup.DEVICE_DATABASE_TREE_TOKEN, mapMaybeParseAndGetCanonicalIncludePaths, arraySetMaybeParseAndGetCanonicalExcludePaths, arraySet, fullBackupDataOutput);
                arraySet.add(canonicalPath11);
                arraySet.remove(canonicalPath5);
                applyXmlFiltersAndDoFullBackupForDomain(packageName, FullBackup.SHAREDPREFS_TREE_TOKEN, mapMaybeParseAndGetCanonicalIncludePaths, arraySetMaybeParseAndGetCanonicalExcludePaths, arraySet, fullBackupDataOutput);
                arraySet.add(canonicalPath5);
                arraySet.remove(canonicalPath12);
                applyXmlFiltersAndDoFullBackupForDomain(packageName, FullBackup.DEVICE_SHAREDPREFS_TREE_TOKEN, mapMaybeParseAndGetCanonicalIncludePaths, arraySetMaybeParseAndGetCanonicalExcludePaths, arraySet, fullBackupDataOutput);
                arraySet.add(canonicalPath12);
                if (Process.myUid() == 1000 || getExternalFilesDir(null) == null) {
                    return;
                }
                applyXmlFiltersAndDoFullBackupForDomain(packageName, FullBackup.MANAGED_EXTERNAL_TREE_TOKEN, mapMaybeParseAndGetCanonicalIncludePaths, arraySetMaybeParseAndGetCanonicalExcludePaths, arraySet, fullBackupDataOutput);
            } catch (IOException | XmlPullParserException e) {
                if (Log.isLoggable("BackupXmlParserLogging", 2)) {
                    Log.v("BackupXmlParserLogging", "Exception trying to parse fullBackupContent xml file! Aborting full backup.", e);
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int getBackupUserId() {
        UserHandle userHandle = this.mUser;
        return userHandle == null ? super.getUserId() : userHandle.getIdentifier();
    }

    private void applyXmlFiltersAndDoFullBackupForDomain(String str, String str2, Map<String, Set<FullBackup.BackupScheme.PathWithRequiredFlags>> map, ArraySet<FullBackup.BackupScheme.PathWithRequiredFlags> arraySet, ArraySet<String> arraySet2, FullBackupDataOutput fullBackupDataOutput) throws IOException {
        if (map == null || map.size() == 0) {
            fullBackupFileTree(str, str2, FullBackup.getBackupScheme(this).tokenToDirectoryPath(str2), arraySet, arraySet2, fullBackupDataOutput);
            return;
        }
        if (map.get(str2) != null) {
            for (FullBackup.BackupScheme.PathWithRequiredFlags pathWithRequiredFlags : map.get(str2)) {
                if (areIncludeRequiredTransportFlagsSatisfied(pathWithRequiredFlags.getRequiredFlags(), fullBackupDataOutput.getTransportFlags())) {
                    fullBackupFileTree(str, str2, pathWithRequiredFlags.getPath(), arraySet, arraySet2, fullBackupDataOutput);
                }
            }
        }
    }

    public final void fullBackupFile(File file, FullBackupDataOutput fullBackupDataOutput) {
        String str;
        String str2;
        String str3;
        String str4;
        ApplicationInfo applicationInfo = getApplicationInfo();
        try {
            Context contextCreateCredentialProtectedStorageContext = createCredentialProtectedStorageContext();
            String canonicalPath = contextCreateCredentialProtectedStorageContext.getDataDir().getCanonicalPath();
            String canonicalPath2 = contextCreateCredentialProtectedStorageContext.getFilesDir().getCanonicalPath();
            String canonicalPath3 = contextCreateCredentialProtectedStorageContext.getNoBackupFilesDir().getCanonicalPath();
            String canonicalPath4 = contextCreateCredentialProtectedStorageContext.getDatabasePath("foo").getParentFile().getCanonicalPath();
            String canonicalPath5 = contextCreateCredentialProtectedStorageContext.getSharedPreferencesPath("foo").getParentFile().getCanonicalPath();
            String canonicalPath6 = contextCreateCredentialProtectedStorageContext.getCacheDir().getCanonicalPath();
            String canonicalPath7 = contextCreateCredentialProtectedStorageContext.getCodeCacheDir().getCanonicalPath();
            Context contextCreateDeviceProtectedStorageContext = createDeviceProtectedStorageContext();
            String canonicalPath8 = contextCreateDeviceProtectedStorageContext.getDataDir().getCanonicalPath();
            String canonicalPath9 = contextCreateDeviceProtectedStorageContext.getFilesDir().getCanonicalPath();
            String canonicalPath10 = contextCreateDeviceProtectedStorageContext.getNoBackupFilesDir().getCanonicalPath();
            String canonicalPath11 = contextCreateDeviceProtectedStorageContext.getDatabasePath("foo").getParentFile().getCanonicalPath();
            String canonicalPath12 = contextCreateDeviceProtectedStorageContext.getSharedPreferencesPath("foo").getParentFile().getCanonicalPath();
            String canonicalPath13 = contextCreateDeviceProtectedStorageContext.getCacheDir().getCanonicalPath();
            String canonicalPath14 = contextCreateDeviceProtectedStorageContext.getCodeCacheDir().getCanonicalPath();
            try {
                String canonicalPath15 = null;
                String canonicalPath16 = applicationInfo.nativeLibraryDir == null ? null : new File(applicationInfo.nativeLibraryDir).getCanonicalPath();
                if (Process.myUid() != 1000) {
                    try {
                        File externalFilesDir = getExternalFilesDir(null);
                        if (externalFilesDir != null) {
                            canonicalPath15 = externalFilesDir.getCanonicalPath();
                        }
                    } catch (IOException unused) {
                        str = TAG;
                        Log.w(str, "Unable to obtain canonical paths");
                        return;
                    }
                }
                String canonicalPath17 = file.getCanonicalPath();
                if (canonicalPath17.startsWith(canonicalPath6) || canonicalPath17.startsWith(canonicalPath7) || canonicalPath17.startsWith(canonicalPath3) || canonicalPath17.startsWith(canonicalPath13) || canonicalPath17.startsWith(canonicalPath14) || canonicalPath17.startsWith(canonicalPath10) || canonicalPath17.startsWith(canonicalPath16)) {
                    Log.w(TAG, "lib, cache, code_cache, and no_backup files are not backed up");
                    return;
                }
                if (canonicalPath17.startsWith(canonicalPath4)) {
                    str2 = FullBackup.DATABASE_TREE_TOKEN;
                    str3 = canonicalPath4;
                } else if (canonicalPath17.startsWith(canonicalPath5)) {
                    str2 = FullBackup.SHAREDPREFS_TREE_TOKEN;
                    str3 = canonicalPath5;
                } else if (canonicalPath17.startsWith(canonicalPath2)) {
                    str2 = FullBackup.FILES_TREE_TOKEN;
                    str3 = canonicalPath2;
                } else if (canonicalPath17.startsWith(canonicalPath)) {
                    str2 = "r";
                    str3 = canonicalPath;
                } else if (canonicalPath17.startsWith(canonicalPath11)) {
                    str2 = FullBackup.DEVICE_DATABASE_TREE_TOKEN;
                    str3 = canonicalPath11;
                } else if (canonicalPath17.startsWith(canonicalPath12)) {
                    str2 = FullBackup.DEVICE_SHAREDPREFS_TREE_TOKEN;
                    str3 = canonicalPath12;
                } else {
                    String str5 = canonicalPath9;
                    if (canonicalPath17.startsWith(str5)) {
                        str4 = FullBackup.DEVICE_FILES_TREE_TOKEN;
                    } else {
                        str5 = canonicalPath8;
                        if (canonicalPath17.startsWith(str5)) {
                            str4 = FullBackup.DEVICE_ROOT_TREE_TOKEN;
                        } else {
                            if (canonicalPath15 == null || !canonicalPath17.startsWith(canonicalPath15)) {
                                Log.w(TAG, "File " + canonicalPath17 + " is in an unsupported location; skipping");
                                return;
                            }
                            str2 = FullBackup.MANAGED_EXTERNAL_TREE_TOKEN;
                            str3 = canonicalPath15;
                        }
                    }
                    str3 = str5;
                    str2 = str4;
                }
                FullBackup.backupToTar(getPackageName(), str2, null, str3, canonicalPath17, fullBackupDataOutput);
            } catch (IOException unused2) {
            }
        } catch (IOException unused3) {
            str = TAG;
        }
    }

    protected final void fullBackupFileTree(String str, String str2, String str3, ArraySet<FullBackup.BackupScheme.PathWithRequiredFlags> arraySet, ArraySet<String> arraySet2, FullBackupDataOutput fullBackupDataOutput) {
        StructStat structStatLstat;
        File[] fileArrListFiles;
        String str4 = FullBackup.getBackupScheme(this).tokenToDirectoryPath(str2);
        if (str4 == null) {
            return;
        }
        File file = new File(str3);
        if (file.exists()) {
            LinkedList linkedList = new LinkedList();
            linkedList.add(file);
            while (linkedList.size() > 0) {
                File file2 = (File) linkedList.remove(0);
                try {
                    structStatLstat = Os.lstat(file2.getPath());
                } catch (ErrnoException e) {
                    e = e;
                } catch (IOException unused) {
                }
                if (OsConstants.S_ISREG(structStatLstat.st_mode) || OsConstants.S_ISDIR(structStatLstat.st_mode)) {
                    String canonicalPath = file2.getCanonicalPath();
                    if (arraySet != null) {
                        try {
                        } catch (ErrnoException e2) {
                            e = e2;
                            if (Log.isLoggable("BackupXmlParserLogging", 2)) {
                                Log.v("BackupXmlParserLogging", "Error scanning file " + file2 + " : " + e);
                            }
                        } catch (IOException unused2) {
                            if (Log.isLoggable("BackupXmlParserLogging", 2)) {
                                Log.v("BackupXmlParserLogging", "Error canonicalizing path of " + file2);
                            }
                        }
                        if (manifestExcludesContainFilePath(arraySet, canonicalPath)) {
                        }
                    }
                    if (arraySet2 == null || !arraySet2.contains(canonicalPath)) {
                        if (OsConstants.S_ISDIR(structStatLstat.st_mode) && (fileArrListFiles = file2.listFiles()) != null) {
                            for (File file3 : fileArrListFiles) {
                                linkedList.add(0, file3);
                            }
                        }
                        FullBackup.backupToTar(str, str2, null, str4, canonicalPath, fullBackupDataOutput);
                    }
                }
            }
        }
    }

    private boolean manifestExcludesContainFilePath(ArraySet<FullBackup.BackupScheme.PathWithRequiredFlags> arraySet, String str) {
        Iterator<FullBackup.BackupScheme.PathWithRequiredFlags> it = arraySet.iterator();
        while (it.hasNext()) {
            String path = it.next().getPath();
            if (path != null && path.equals(str)) {
                return true;
            }
        }
        return false;
    }

    public void onRestoreFile(ParcelFileDescriptor parcelFileDescriptor, long j, File file, int i, long j2, long j3) throws IOException {
        File file2 = file;
        if (!isFileEligibleForRestore(file)) {
            file2 = null;
        }
        FullBackup.restoreFile(parcelFileDescriptor, j, i, j2, j3, file2);
    }

    private boolean isFileEligibleForRestore(File file) throws IOException {
        FullBackup.BackupScheme backupScheme = FullBackup.getBackupScheme(this);
        if (!backupScheme.isFullBackupContentEnabled()) {
            if (Log.isLoggable("BackupXmlParserLogging", 2)) {
                Log.v("BackupXmlParserLogging", "onRestoreFile \"" + file.getCanonicalPath() + "\" : fullBackupContent not enabled for " + getPackageName());
            }
            return false;
        }
        String canonicalPath = file.getCanonicalPath();
        try {
            Map<String, Set<FullBackup.BackupScheme.PathWithRequiredFlags>> mapMaybeParseAndGetCanonicalIncludePaths = backupScheme.maybeParseAndGetCanonicalIncludePaths();
            ArraySet<FullBackup.BackupScheme.PathWithRequiredFlags> arraySetMaybeParseAndGetCanonicalExcludePaths = backupScheme.maybeParseAndGetCanonicalExcludePaths();
            if (arraySetMaybeParseAndGetCanonicalExcludePaths != null && BackupUtils.isFileSpecifiedInPathList(file, arraySetMaybeParseAndGetCanonicalExcludePaths)) {
                if (Log.isLoggable("BackupXmlParserLogging", 2)) {
                    Log.v("BackupXmlParserLogging", "onRestoreFile: \"" + canonicalPath + "\": listed in excludes; skipping.");
                }
                return false;
            }
            if (mapMaybeParseAndGetCanonicalIncludePaths == null || mapMaybeParseAndGetCanonicalIncludePaths.isEmpty()) {
                return true;
            }
            Iterator<Set<FullBackup.BackupScheme.PathWithRequiredFlags>> it = mapMaybeParseAndGetCanonicalIncludePaths.values().iterator();
            boolean zIsFileSpecifiedInPathList = false;
            while (it.hasNext() && !((zIsFileSpecifiedInPathList = zIsFileSpecifiedInPathList | BackupUtils.isFileSpecifiedInPathList(file, it.next())))) {
            }
            if (zIsFileSpecifiedInPathList) {
                return true;
            }
            if (Log.isLoggable("BackupXmlParserLogging", 2)) {
                Log.v("BackupXmlParserLogging", "onRestoreFile: Trying to restore \"" + canonicalPath + "\" but it isn't specified in the included files; skipping.");
            }
            return false;
        } catch (XmlPullParserException e) {
            if (Log.isLoggable("BackupXmlParserLogging", 2)) {
                Log.v("BackupXmlParserLogging", "onRestoreFile \"" + canonicalPath + "\" : Exception trying to parse fullBackupContent xml file! Aborting onRestoreFile.", e);
            }
            return false;
        }
    }

    protected void onRestoreFile(ParcelFileDescriptor parcelFileDescriptor, long j, int i, String str, String str2, long j2, long j3) throws IOException {
        String str3 = FullBackup.getBackupScheme(this).tokenToDirectoryPath(str);
        long j4 = str.equals(FullBackup.MANAGED_EXTERNAL_TREE_TOKEN) ? -1L : j2;
        if (str3 != null) {
            File file = new File(str3, str2);
            if (file.getCanonicalPath().startsWith(str3 + File.separatorChar)) {
                onRestoreFile(parcelFileDescriptor, j, file, i, j4, j3);
                return;
            }
        }
        FullBackup.restoreFile(parcelFileDescriptor, j, i, j4, j3, null);
    }

    public final IBinder onBind() {
        return this.mBinder;
    }

    public void attach(Context context) {
        attachBaseContext(context);
    }

    private class BackupServiceBinder extends IBackupAgent.Stub {
        private static final String TAG = "BackupServiceBinder";

        private BackupServiceBinder() {
        }

        @Override // android.app.IBackupAgent
        public void doBackup(ParcelFileDescriptor parcelFileDescriptor, ParcelFileDescriptor parcelFileDescriptor2, ParcelFileDescriptor parcelFileDescriptor3, long j, IBackupCallback iBackupCallback, int i) throws RemoteException {
            long jClearCallingIdentity = Binder.clearCallingIdentity();
            try {
                try {
                    try {
                        BackupAgent.this.onBackup(parcelFileDescriptor, new BackupDataOutput(parcelFileDescriptor2.getFileDescriptor(), j, i), parcelFileDescriptor3);
                        BackupAgent.this.waitForSharedPrefs();
                        Binder.restoreCallingIdentity(jClearCallingIdentity);
                        try {
                            iBackupCallback.operationComplete(0L);
                        } catch (RemoteException unused) {
                        }
                        if (Binder.getCallingPid() != Process.myPid()) {
                            IoUtils.closeQuietly(parcelFileDescriptor);
                            IoUtils.closeQuietly(parcelFileDescriptor2);
                            IoUtils.closeQuietly(parcelFileDescriptor3);
                        }
                    } catch (RuntimeException e) {
                        Log.d(TAG, "onBackup (" + BackupAgent.this.getClass().getName() + ") threw", e);
                        throw e;
                    }
                } catch (IOException e2) {
                    Log.d(TAG, "onBackup (" + BackupAgent.this.getClass().getName() + ") threw", e2);
                    throw new RuntimeException(e2);
                }
            } finally {
            }
        }

        @Override // android.app.IBackupAgent
        public void doRestore(ParcelFileDescriptor parcelFileDescriptor, long j, ParcelFileDescriptor parcelFileDescriptor2, int i, IBackupManager iBackupManager) throws Throwable {
            doRestoreInternal(parcelFileDescriptor, j, parcelFileDescriptor2, i, iBackupManager, null);
        }

        @Override // android.app.IBackupAgent
        public void doRestoreWithExcludedKeys(ParcelFileDescriptor parcelFileDescriptor, long j, ParcelFileDescriptor parcelFileDescriptor2, int i, IBackupManager iBackupManager, List<String> list) throws Throwable {
            doRestoreInternal(parcelFileDescriptor, j, parcelFileDescriptor2, i, iBackupManager, list);
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Removed duplicated region for block: B:39:0x00e2  */
        /* JADX WARN: Removed duplicated region for block: B:54:? A[SYNTHETIC] */
        /* JADX WARN: Type inference failed for: r4v0, types: [java.lang.String] */
        /* JADX WARN: Type inference failed for: r4v1 */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        private void doRestoreInternal(android.os.ParcelFileDescriptor r19, long r20, android.os.ParcelFileDescriptor r22, int r23, android.app.backup.IBackupManager r24, java.util.List<java.lang.String> r25) throws java.lang.Throwable {
            /*
                Method dump skipped, instruction units count: 233
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: android.app.backup.BackupAgent.BackupServiceBinder.doRestoreInternal(android.os.ParcelFileDescriptor, long, android.os.ParcelFileDescriptor, int, android.app.backup.IBackupManager, java.util.List):void");
        }

        /* JADX WARN: Removed duplicated region for block: B:40:0x00e0  */
        /* JADX WARN: Removed duplicated region for block: B:57:? A[SYNTHETIC] */
        @Override // android.app.IBackupAgent
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public void doFullBackup(android.os.ParcelFileDescriptor r17, long r18, int r20, android.app.backup.IBackupManager r21, int r22) throws java.lang.Throwable {
            /*
                Method dump skipped, instruction units count: 228
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: android.app.backup.BackupAgent.BackupServiceBinder.doFullBackup(android.os.ParcelFileDescriptor, long, int, android.app.backup.IBackupManager, int):void");
        }

        @Override // android.app.IBackupAgent
        public void doMeasureFullBackup(long j, int i, IBackupManager iBackupManager, int i2) {
            long jClearCallingIdentity = Binder.clearCallingIdentity();
            FullBackupDataOutput fullBackupDataOutput = new FullBackupDataOutput(j, i2);
            BackupAgent.this.waitForSharedPrefs();
            try {
                try {
                    BackupAgent.this.onFullBackup(fullBackupDataOutput);
                    try {
                        iBackupManager.opCompleteForUser(BackupAgent.this.getBackupUserId(), i, fullBackupDataOutput.getSize());
                    } catch (RemoteException unused) {
                    }
                } catch (IOException e) {
                    Log.d(TAG, "onFullBackup[M] (" + BackupAgent.this.getClass().getName() + ") threw", e);
                    throw new RuntimeException(e);
                } catch (RuntimeException e2) {
                    Log.d(TAG, "onFullBackup[M] (" + BackupAgent.this.getClass().getName() + ") threw", e2);
                    throw e2;
                }
            } finally {
                Binder.restoreCallingIdentity(jClearCallingIdentity);
                try {
                    iBackupManager.opCompleteForUser(BackupAgent.this.getBackupUserId(), i, fullBackupDataOutput.getSize());
                } catch (RemoteException unused2) {
                }
            }
        }

        @Override // android.app.IBackupAgent
        public void doRestoreFile(ParcelFileDescriptor parcelFileDescriptor, long j, int i, String str, String str2, long j2, long j3, int i2, IBackupManager iBackupManager) throws RemoteException {
            long jClearCallingIdentity = Binder.clearCallingIdentity();
            try {
                try {
                    BackupAgent.this.onRestoreFile(parcelFileDescriptor, j, i, str, str2, j2, j3);
                    BackupAgent.this.waitForSharedPrefs();
                    BackupAgent.this.reloadSharedPreferences();
                    Binder.restoreCallingIdentity(jClearCallingIdentity);
                    try {
                        iBackupManager.opCompleteForUser(BackupAgent.this.getBackupUserId(), i2, 0L);
                    } catch (RemoteException unused) {
                    }
                    if (Binder.getCallingPid() != Process.myPid()) {
                        IoUtils.closeQuietly(parcelFileDescriptor);
                    }
                } catch (IOException e) {
                    Log.d(TAG, "onRestoreFile (" + BackupAgent.this.getClass().getName() + ") threw", e);
                    throw new RuntimeException(e);
                }
            } finally {
            }
        }

        @Override // android.app.IBackupAgent
        public void doRestoreFinished(int i, IBackupManager iBackupManager) {
            long jClearCallingIdentity = Binder.clearCallingIdentity();
            try {
                try {
                    BackupAgent.this.onRestoreFinished();
                    try {
                        iBackupManager.opCompleteForUser(BackupAgent.this.getBackupUserId(), i, 0L);
                    } catch (RemoteException unused) {
                    }
                } finally {
                    BackupAgent.this.waitForSharedPrefs();
                    Binder.restoreCallingIdentity(jClearCallingIdentity);
                    try {
                        iBackupManager.opCompleteForUser(BackupAgent.this.getBackupUserId(), i, 0L);
                    } catch (RemoteException unused2) {
                    }
                }
            } catch (Exception e) {
                Log.d(TAG, "onRestoreFinished (" + BackupAgent.this.getClass().getName() + ") threw", e);
                throw e;
            }
        }

        @Override // android.app.IBackupAgent
        public void fail(String str) {
            BackupAgent.this.getHandler().post(new FailRunnable(str));
        }

        @Override // android.app.IBackupAgent
        public void doQuotaExceeded(long j, long j2, IBackupCallback iBackupCallback) {
            long jClearCallingIdentity = Binder.clearCallingIdentity();
            try {
                try {
                    BackupAgent.this.onQuotaExceeded(j, j2);
                    BackupAgent.this.waitForSharedPrefs();
                    Binder.restoreCallingIdentity(jClearCallingIdentity);
                    try {
                        iBackupCallback.operationComplete(0L);
                    } catch (RemoteException unused) {
                    }
                } catch (Exception e) {
                    Log.d(TAG, "onQuotaExceeded(" + BackupAgent.this.getClass().getName() + ") threw", e);
                    throw e;
                }
            } catch (Throwable th) {
                BackupAgent.this.waitForSharedPrefs();
                Binder.restoreCallingIdentity(jClearCallingIdentity);
                try {
                    iBackupCallback.operationComplete(-1L);
                } catch (RemoteException unused2) {
                }
                throw th;
            }
        }
    }

    static class FailRunnable implements Runnable {
        private String mMessage;

        FailRunnable(String str) {
            this.mMessage = str;
        }

        @Override // java.lang.Runnable
        public void run() {
            throw new IllegalStateException(this.mMessage);
        }
    }
}
