package android.app;

import android.compat.Compatibility;
import android.content.SharedPreferences;
import android.os.FileUtils;
import android.os.Looper;
import android.system.ErrnoException;
import android.system.Os;
import android.system.StructStat;
import android.system.StructTimespec;
import android.util.Log;
import com.android.internal.util.ExponentiallyBucketedHistogram;
import com.android.internal.util.XmlUtils;
import dalvik.system.BlockGuard;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.WeakHashMap;
import java.util.concurrent.CountDownLatch;
import libcore.io.IoUtils;
import org.xmlpull.v1.XmlPullParserException;

/* JADX INFO: loaded from: classes.dex */
final class SharedPreferencesImpl implements SharedPreferences {
    private static final long CALLBACK_ON_CLEAR_CHANGE = 119147584;
    private static final Object CONTENT = new Object();
    private static final boolean DEBUG = false;
    private static final long MAX_FSYNC_DURATION_MILLIS = 256;
    private static final String TAG = "SharedPreferencesImpl";
    private final File mBackupFile;
    private long mCurrentMemoryStateGeneration;
    private long mDiskStateGeneration;
    private final File mFile;
    private boolean mLoaded;
    private final int mMode;
    private long mStatSize;
    private StructTimespec mStatTimestamp;
    private final Object mLock = new Object();
    private final Object mWritingToDiskLock = new Object();
    private int mDiskWritesInFlight = 0;
    private final WeakHashMap<SharedPreferences.OnSharedPreferenceChangeListener, Object> mListeners = new WeakHashMap<>();
    private final ExponentiallyBucketedHistogram mSyncTimes = new ExponentiallyBucketedHistogram(16);
    private int mNumSync = 0;
    private Map<String, Object> mMap = null;
    private Throwable mThrowable = null;

    static /* synthetic */ int access$308(SharedPreferencesImpl sharedPreferencesImpl) {
        int i = sharedPreferencesImpl.mDiskWritesInFlight;
        sharedPreferencesImpl.mDiskWritesInFlight = i + 1;
        return i;
    }

    static /* synthetic */ int access$310(SharedPreferencesImpl sharedPreferencesImpl) {
        int i = sharedPreferencesImpl.mDiskWritesInFlight;
        sharedPreferencesImpl.mDiskWritesInFlight = i - 1;
        return i;
    }

    static /* synthetic */ long access$608(SharedPreferencesImpl sharedPreferencesImpl) {
        long j = sharedPreferencesImpl.mCurrentMemoryStateGeneration;
        sharedPreferencesImpl.mCurrentMemoryStateGeneration = 1 + j;
        return j;
    }

    SharedPreferencesImpl(File file, int i) {
        this.mLoaded = false;
        this.mFile = file;
        this.mBackupFile = makeBackupFile(file);
        this.mMode = i;
        this.mLoaded = false;
        startLoadFromDisk();
    }

    /* JADX WARN: Type inference failed for: r0v1, types: [android.app.SharedPreferencesImpl$1] */
    private void startLoadFromDisk() {
        synchronized (this.mLock) {
            this.mLoaded = false;
        }
        new Thread("SharedPreferencesImpl-load") { // from class: android.app.SharedPreferencesImpl.1
            @Override // java.lang.Thread, java.lang.Runnable
            public void run() {
                SharedPreferencesImpl.this.loadFromDisk();
            }
        }.start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v3, types: [boolean] */
    /* JADX WARN: Type inference failed for: r2v4, types: [java.lang.AutoCloseable] */
    /* JADX WARN: Type inference failed for: r2v5 */
    public void loadFromDisk() {
        HashMap<String, ?> mapXml;
        StructStat structStatStat;
        Object obj;
        ?? CanRead;
        BufferedInputStream bufferedInputStream;
        synchronized (this.mLock) {
            if (this.mLoaded) {
                return;
            }
            if (this.mBackupFile.exists()) {
                this.mFile.delete();
                this.mBackupFile.renameTo(this.mFile);
            }
            if (this.mFile.exists() && !this.mFile.canRead()) {
                Log.w(TAG, "Attempt to read preferences file " + this.mFile + " without permission");
            }
            Throwable th = null;
            try {
                structStatStat = Os.stat(this.mFile.getPath());
                try {
                    CanRead = this.mFile.canRead();
                } catch (ErrnoException unused) {
                } catch (Throwable th2) {
                    mapXml = null;
                    th = th2;
                }
            } catch (ErrnoException unused2) {
                structStatStat = null;
                mapXml = null;
            } catch (Throwable th3) {
                mapXml = null;
                th = th3;
                structStatStat = null;
            }
            try {
                if (CanRead != 0) {
                    try {
                        bufferedInputStream = new BufferedInputStream(new FileInputStream(this.mFile), 16384);
                    } catch (Exception e) {
                        e = e;
                        bufferedInputStream = null;
                    } catch (Throwable th4) {
                        th = th4;
                        CanRead = 0;
                        IoUtils.closeQuietly((AutoCloseable) CanRead);
                        throw th;
                    }
                    try {
                        mapXml = XmlUtils.readMapXml(bufferedInputStream);
                        try {
                            IoUtils.closeQuietly(bufferedInputStream);
                        } catch (ErrnoException unused3) {
                        } catch (Throwable th5) {
                            th = th5;
                        }
                    } catch (Exception e2) {
                        e = e2;
                        Log.w(TAG, "Cannot read " + this.mFile.getAbsolutePath(), e);
                        IoUtils.closeQuietly(bufferedInputStream);
                        mapXml = null;
                    }
                } else {
                    mapXml = null;
                }
                synchronized (this.mLock) {
                    this.mLoaded = true;
                    this.mThrowable = th;
                    if (th == null) {
                        try {
                            if (mapXml != null) {
                                this.mMap = mapXml;
                                this.mStatTimestamp = structStatStat.st_mtim;
                                this.mStatSize = structStatStat.st_size;
                            } else {
                                this.mMap = new HashMap();
                            }
                            obj = this.mLock;
                        } catch (Throwable th6) {
                            try {
                                this.mThrowable = th6;
                                obj = this.mLock;
                            } catch (Throwable th7) {
                                this.mLock.notifyAll();
                                throw th7;
                            }
                        }
                    } else {
                        obj = this.mLock;
                    }
                    obj.notifyAll();
                }
            } catch (Throwable th8) {
                th = th8;
            }
        }
    }

    static File makeBackupFile(File file) {
        return new File(file.getPath() + ".bak");
    }

    void startReloadIfChangedUnexpectedly() {
        synchronized (this.mLock) {
            if (hasFileChangedUnexpectedly()) {
                startLoadFromDisk();
            }
        }
    }

    private boolean hasFileChangedUnexpectedly() {
        boolean z;
        synchronized (this.mLock) {
            if (this.mDiskWritesInFlight > 0) {
                return false;
            }
            try {
                BlockGuard.getThreadPolicy().onReadFromDisk();
                StructStat structStatStat = Os.stat(this.mFile.getPath());
                synchronized (this.mLock) {
                    z = (structStatStat.st_mtim.equals(this.mStatTimestamp) && this.mStatSize == structStatStat.st_size) ? false : true;
                }
                return z;
            } catch (ErrnoException unused) {
                return true;
            }
        }
    }

    @Override // android.content.SharedPreferences
    public void registerOnSharedPreferenceChangeListener(SharedPreferences.OnSharedPreferenceChangeListener onSharedPreferenceChangeListener) {
        synchronized (this.mLock) {
            this.mListeners.put(onSharedPreferenceChangeListener, CONTENT);
        }
    }

    @Override // android.content.SharedPreferences
    public void unregisterOnSharedPreferenceChangeListener(SharedPreferences.OnSharedPreferenceChangeListener onSharedPreferenceChangeListener) {
        synchronized (this.mLock) {
            this.mListeners.remove(onSharedPreferenceChangeListener);
        }
    }

    private void awaitLoadedLocked() {
        if (!this.mLoaded) {
            BlockGuard.getThreadPolicy().onReadFromDisk();
        }
        while (!this.mLoaded) {
            try {
                this.mLock.wait();
            } catch (InterruptedException unused) {
            }
        }
        if (this.mThrowable != null) {
            throw new IllegalStateException(this.mThrowable);
        }
    }

    @Override // android.content.SharedPreferences
    public Map<String, ?> getAll() {
        HashMap map;
        synchronized (this.mLock) {
            awaitLoadedLocked();
            map = new HashMap(this.mMap);
        }
        return map;
    }

    @Override // android.content.SharedPreferences
    public String getString(String str, String str2) {
        synchronized (this.mLock) {
            awaitLoadedLocked();
            String str3 = (String) this.mMap.get(str);
            if (str3 != null) {
                str2 = str3;
            }
        }
        return str2;
    }

    @Override // android.content.SharedPreferences
    public Set<String> getStringSet(String str, Set<String> set) {
        synchronized (this.mLock) {
            awaitLoadedLocked();
            Set<String> set2 = (Set) this.mMap.get(str);
            if (set2 != null) {
                set = set2;
            }
        }
        return set;
    }

    @Override // android.content.SharedPreferences
    public int getInt(String str, int i) {
        synchronized (this.mLock) {
            awaitLoadedLocked();
            Integer num = (Integer) this.mMap.get(str);
            if (num != null) {
                i = num.intValue();
            }
        }
        return i;
    }

    @Override // android.content.SharedPreferences
    public long getLong(String str, long j) {
        synchronized (this.mLock) {
            awaitLoadedLocked();
            Long l = (Long) this.mMap.get(str);
            if (l != null) {
                j = l.longValue();
            }
        }
        return j;
    }

    @Override // android.content.SharedPreferences
    public float getFloat(String str, float f) {
        synchronized (this.mLock) {
            awaitLoadedLocked();
            Float f2 = (Float) this.mMap.get(str);
            if (f2 != null) {
                f = f2.floatValue();
            }
        }
        return f;
    }

    @Override // android.content.SharedPreferences
    public boolean getBoolean(String str, boolean z) {
        synchronized (this.mLock) {
            awaitLoadedLocked();
            Boolean bool = (Boolean) this.mMap.get(str);
            if (bool != null) {
                z = bool.booleanValue();
            }
        }
        return z;
    }

    @Override // android.content.SharedPreferences
    public boolean contains(String str) {
        boolean zContainsKey;
        synchronized (this.mLock) {
            awaitLoadedLocked();
            zContainsKey = this.mMap.containsKey(str);
        }
        return zContainsKey;
    }

    @Override // android.content.SharedPreferences
    public SharedPreferences.Editor edit() {
        synchronized (this.mLock) {
            awaitLoadedLocked();
        }
        return new EditorImpl();
    }

    /* JADX INFO: Access modifiers changed from: private */
    static class MemoryCommitResult {
        final boolean keysCleared;
        final List<String> keysModified;
        final Set<SharedPreferences.OnSharedPreferenceChangeListener> listeners;
        final Map<String, Object> mapToWriteToDisk;
        final long memoryStateGeneration;
        boolean wasWritten;
        volatile boolean writeToDiskResult;
        final CountDownLatch writtenToDiskLatch;

        private MemoryCommitResult(long j, boolean z, List<String> list, Set<SharedPreferences.OnSharedPreferenceChangeListener> set, Map<String, Object> map) {
            this.writtenToDiskLatch = new CountDownLatch(1);
            this.writeToDiskResult = false;
            this.wasWritten = false;
            this.memoryStateGeneration = j;
            this.keysCleared = z;
            this.keysModified = list;
            this.listeners = set;
            this.mapToWriteToDisk = map;
        }

        void setDiskWriteResult(boolean z, boolean z2) {
            this.wasWritten = z;
            this.writeToDiskResult = z2;
            this.writtenToDiskLatch.countDown();
        }
    }

    public final class EditorImpl implements SharedPreferences.Editor {
        private final Object mEditorLock = new Object();
        private final Map<String, Object> mModified = new HashMap();
        private boolean mClear = false;

        public EditorImpl() {
        }

        @Override // android.content.SharedPreferences.Editor
        public SharedPreferences.Editor putString(String str, String str2) {
            synchronized (this.mEditorLock) {
                this.mModified.put(str, str2);
            }
            return this;
        }

        @Override // android.content.SharedPreferences.Editor
        public SharedPreferences.Editor putStringSet(String str, Set<String> set) {
            synchronized (this.mEditorLock) {
                this.mModified.put(str, set == null ? null : new HashSet(set));
            }
            return this;
        }

        @Override // android.content.SharedPreferences.Editor
        public SharedPreferences.Editor putInt(String str, int i) {
            synchronized (this.mEditorLock) {
                this.mModified.put(str, Integer.valueOf(i));
            }
            return this;
        }

        @Override // android.content.SharedPreferences.Editor
        public SharedPreferences.Editor putLong(String str, long j) {
            synchronized (this.mEditorLock) {
                this.mModified.put(str, Long.valueOf(j));
            }
            return this;
        }

        @Override // android.content.SharedPreferences.Editor
        public SharedPreferences.Editor putFloat(String str, float f) {
            synchronized (this.mEditorLock) {
                this.mModified.put(str, Float.valueOf(f));
            }
            return this;
        }

        @Override // android.content.SharedPreferences.Editor
        public SharedPreferences.Editor putBoolean(String str, boolean z) {
            synchronized (this.mEditorLock) {
                this.mModified.put(str, Boolean.valueOf(z));
            }
            return this;
        }

        @Override // android.content.SharedPreferences.Editor
        public SharedPreferences.Editor remove(String str) {
            synchronized (this.mEditorLock) {
                this.mModified.put(str, this);
            }
            return this;
        }

        @Override // android.content.SharedPreferences.Editor
        public SharedPreferences.Editor clear() {
            synchronized (this.mEditorLock) {
                this.mClear = true;
            }
            return this;
        }

        @Override // android.content.SharedPreferences.Editor
        public void apply() {
            final long jCurrentTimeMillis = System.currentTimeMillis();
            final MemoryCommitResult memoryCommitResultCommitToMemory = commitToMemory();
            final Runnable runnable = new Runnable() { // from class: android.app.SharedPreferencesImpl.EditorImpl.1
                @Override // java.lang.Runnable
                public void run() {
                    try {
                        memoryCommitResultCommitToMemory.writtenToDiskLatch.await();
                    } catch (InterruptedException unused) {
                    }
                }
            };
            QueuedWork.addFinisher(runnable);
            SharedPreferencesImpl.this.enqueueDiskWrite(memoryCommitResultCommitToMemory, new Runnable() { // from class: android.app.SharedPreferencesImpl.EditorImpl.2
                @Override // java.lang.Runnable
                public void run() {
                    runnable.run();
                    QueuedWork.removeFinisher(runnable);
                }
            });
            lambda$notifyListeners$0$SharedPreferencesImpl$EditorImpl(memoryCommitResultCommitToMemory);
        }

        /* JADX WARN: Removed duplicated region for block: B:45:0x00b8 A[Catch: all -> 0x00db, TryCatch #0 {, blocks: (B:17:0x005a, B:19:0x005e, B:21:0x0064, B:23:0x006a, B:25:0x0070, B:26:0x007a, B:28:0x0080, B:32:0x0095, B:34:0x009b, B:36:0x00a1, B:39:0x00a8, B:45:0x00b8, B:40:0x00ac, B:43:0x00b3, B:47:0x00bd, B:49:0x00c4, B:50:0x00c9, B:51:0x00cf), top: B:61:0x005a, outer: #1 }] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        private android.app.SharedPreferencesImpl.MemoryCommitResult commitToMemory() {
            /*
                Method dump skipped, instruction units count: 225
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: android.app.SharedPreferencesImpl.EditorImpl.commitToMemory():android.app.SharedPreferencesImpl$MemoryCommitResult");
        }

        @Override // android.content.SharedPreferences.Editor
        public boolean commit() {
            MemoryCommitResult memoryCommitResultCommitToMemory = commitToMemory();
            SharedPreferencesImpl.this.enqueueDiskWrite(memoryCommitResultCommitToMemory, null);
            try {
                memoryCommitResultCommitToMemory.writtenToDiskLatch.await();
                lambda$notifyListeners$0$SharedPreferencesImpl$EditorImpl(memoryCommitResultCommitToMemory);
                return memoryCommitResultCommitToMemory.writeToDiskResult;
            } catch (InterruptedException unused) {
                return false;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: notifyListeners, reason: merged with bridge method [inline-methods] */
        public void lambda$notifyListeners$0$SharedPreferencesImpl$EditorImpl(final MemoryCommitResult memoryCommitResult) {
            if (memoryCommitResult.listeners != null) {
                if (memoryCommitResult.keysModified != null || memoryCommitResult.keysCleared) {
                    if (Looper.myLooper() == Looper.getMainLooper()) {
                        if (memoryCommitResult.keysCleared && Compatibility.isChangeEnabled(SharedPreferencesImpl.CALLBACK_ON_CLEAR_CHANGE)) {
                            for (SharedPreferences.OnSharedPreferenceChangeListener onSharedPreferenceChangeListener : memoryCommitResult.listeners) {
                                if (onSharedPreferenceChangeListener != null) {
                                    onSharedPreferenceChangeListener.onSharedPreferenceChanged(SharedPreferencesImpl.this, null);
                                }
                            }
                        }
                        for (int size = memoryCommitResult.keysModified.size() - 1; size >= 0; size--) {
                            String str = memoryCommitResult.keysModified.get(size);
                            for (SharedPreferences.OnSharedPreferenceChangeListener onSharedPreferenceChangeListener2 : memoryCommitResult.listeners) {
                                if (onSharedPreferenceChangeListener2 != null) {
                                    onSharedPreferenceChangeListener2.onSharedPreferenceChanged(SharedPreferencesImpl.this, str);
                                }
                            }
                        }
                        return;
                    }
                    ActivityThread.sMainThreadHandler.post(new Runnable() { // from class: android.app.-$$Lambda$SharedPreferencesImpl$EditorImpl$xMt3av_jX0oSy8XrWpULfBhYg_E
                        @Override // java.lang.Runnable
                        public final void run() {
                            this.f$0.lambda$notifyListeners$0$SharedPreferencesImpl$EditorImpl(memoryCommitResult);
                        }
                    });
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void enqueueDiskWrite(final MemoryCommitResult memoryCommitResult, final Runnable runnable) {
        boolean z;
        final boolean z2 = runnable == null;
        Runnable runnable2 = new Runnable() { // from class: android.app.SharedPreferencesImpl.2
            @Override // java.lang.Runnable
            public void run() {
                synchronized (SharedPreferencesImpl.this.mWritingToDiskLock) {
                    SharedPreferencesImpl.this.writeToFile(memoryCommitResult, z2);
                }
                synchronized (SharedPreferencesImpl.this.mLock) {
                    SharedPreferencesImpl.access$310(SharedPreferencesImpl.this);
                }
                Runnable runnable3 = runnable;
                if (runnable3 != null) {
                    runnable3.run();
                }
            }
        };
        if (z2) {
            synchronized (this.mLock) {
                z = this.mDiskWritesInFlight == 1;
            }
            if (z) {
                runnable2.run();
                return;
            }
        }
        QueuedWork.queue(runnable2, !z2);
    }

    private static FileOutputStream createFileOutputStream(File file) {
        FileOutputStream fileOutputStream;
        try {
            fileOutputStream = new FileOutputStream(file);
        } catch (FileNotFoundException unused) {
            File parentFile = file.getParentFile();
            if (!parentFile.mkdir()) {
                Log.e(TAG, "Couldn't create directory for SharedPreferences file " + file);
                return null;
            }
            FileUtils.setPermissions(parentFile.getPath(), 505, -1, -1);
            try {
                fileOutputStream = new FileOutputStream(file);
            } catch (FileNotFoundException e) {
                Log.e(TAG, "Couldn't create SharedPreferences file " + file, e);
                return null;
            }
        }
        return fileOutputStream;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void writeToFile(MemoryCommitResult memoryCommitResult, boolean z) {
        boolean z2;
        if (this.mFile.exists()) {
            if (this.mDiskStateGeneration >= memoryCommitResult.memoryStateGeneration) {
                z2 = false;
            } else if (z) {
                z2 = true;
            } else {
                synchronized (this.mLock) {
                    z2 = this.mCurrentMemoryStateGeneration == memoryCommitResult.memoryStateGeneration;
                }
            }
            if (!z2) {
                memoryCommitResult.setDiskWriteResult(false, true);
                return;
            } else if (!this.mBackupFile.exists()) {
                if (!this.mFile.renameTo(this.mBackupFile)) {
                    Log.e(TAG, "Couldn't rename file " + this.mFile + " to backup file " + this.mBackupFile);
                    memoryCommitResult.setDiskWriteResult(false, false);
                    return;
                }
            } else {
                this.mFile.delete();
            }
        }
        try {
            FileOutputStream fileOutputStreamCreateFileOutputStream = createFileOutputStream(this.mFile);
            if (fileOutputStreamCreateFileOutputStream == null) {
                memoryCommitResult.setDiskWriteResult(false, false);
                return;
            }
            XmlUtils.writeMapXml(memoryCommitResult.mapToWriteToDisk, fileOutputStreamCreateFileOutputStream);
            long jCurrentTimeMillis = System.currentTimeMillis();
            FileUtils.sync(fileOutputStreamCreateFileOutputStream);
            long jCurrentTimeMillis2 = System.currentTimeMillis();
            fileOutputStreamCreateFileOutputStream.close();
            ContextImpl.setFilePermissionsFromMode(this.mFile.getPath(), this.mMode, 0);
            try {
                StructStat structStatStat = Os.stat(this.mFile.getPath());
                synchronized (this.mLock) {
                    this.mStatTimestamp = structStatStat.st_mtim;
                    this.mStatSize = structStatStat.st_size;
                }
            } catch (ErrnoException unused) {
            }
            this.mBackupFile.delete();
            this.mDiskStateGeneration = memoryCommitResult.memoryStateGeneration;
            memoryCommitResult.setDiskWriteResult(true, true);
            long j = jCurrentTimeMillis2 - jCurrentTimeMillis;
            this.mSyncTimes.add((int) j);
            int i = this.mNumSync + 1;
            this.mNumSync = i;
            if (i % 1024 == 0 || j > 256) {
                this.mSyncTimes.log(TAG, "Time required to fsync " + this.mFile + ": ");
            }
        } catch (IOException e) {
            Log.w(TAG, "writeToFile: Got exception:", e);
            if (this.mFile.exists() && !this.mFile.delete()) {
                Log.e(TAG, "Couldn't clean up partially-written file " + this.mFile);
            }
            memoryCommitResult.setDiskWriteResult(false, false);
        } catch (XmlPullParserException e2) {
            Log.w(TAG, "writeToFile: Got exception:", e2);
            if (this.mFile.exists()) {
                Log.e(TAG, "Couldn't clean up partially-written file " + this.mFile);
            }
            memoryCommitResult.setDiskWriteResult(false, false);
        }
    }
}
