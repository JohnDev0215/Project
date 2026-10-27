package io.objectbox;

import io.objectbox.converter.PropertyConverter;
import io.objectbox.exception.DbException;
import io.objectbox.exception.DbExceptionListener;
import io.objectbox.exception.DbSchemaException;
import io.objectbox.internal.Feature;
import io.objectbox.internal.NativeLibraryLoader;
import io.objectbox.internal.ObjectBoxThreadPool;
import io.objectbox.reactive.SubscriptionBuilder;
import io.objectbox.sync.SyncClient;
import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.WeakHashMap;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nullable;
import org.greenrobot.essentials.collections.LongHashMap;

/* JADX INFO: loaded from: classes5.dex */
public class BoxStore implements Closeable {
    public static final String JNI_VERSION = "3.5.0";
    private static final String VERSION = "3.5.0-2022-12-05";

    @Nullable
    private static Object context;
    private static BoxStore defaultStore;
    private static final Set<String> openFiles = new HashSet();
    private static volatile Thread openFilesCheckerThread;

    @Nullable
    private static Object relinker;
    private final int[] allEntityTypeIds;
    private final String canonicalPath;
    private volatile boolean closed;
    volatile int commitCount;
    final boolean debugRelations;
    final boolean debugTxRead;
    final boolean debugTxWrite;
    private final File directory;
    private final TxCallback<?> failedReadTxAttemptCallback;
    private final long handle;
    private int objectBrowserPort;
    private final ObjectClassPublisher objectClassPublisher;
    private final int queryAttempts;

    @Nullable
    private SyncClient syncClient;
    private final Map<Class<?>, String> dbNameByClass = new HashMap();
    private final Map<Class<?>, Integer> entityTypeIdByClass = new HashMap();
    private final Map<Class<?>, EntityInfo<?>> propertiesByClass = new HashMap();
    private final LongHashMap<Class<?>> classByEntityTypeId = new LongHashMap<>();
    private final Map<Class<?>, Box<?>> boxes = new ConcurrentHashMap();
    private final Set<Transaction> transactions = Collections.newSetFromMap(new WeakHashMap());
    private final ExecutorService threadPool = new ObjectBoxThreadPool(this);
    final ThreadLocal<Transaction> activeTx = new ThreadLocal<>();
    final Object txCommitCountLock = new Object();

    public static String getVersion() {
        return VERSION;
    }

    static native long nativeBeginReadTx(long j);

    static native long nativeBeginTx(long j);

    static native int nativeCleanStaleReadTransactions(long j);

    static native long nativeCreateWithFlatOptions(byte[] bArr, byte[] bArr2);

    static native void nativeDelete(long j);

    static native String nativeDiagnose(long j);

    static native void nativeDropAllData(long j);

    static native String nativeGetVersion();

    static native long nativeGloballyActiveEntityTypes();

    private static native boolean nativeHasFeature(int i);

    static native boolean nativeIsObjectBrowserAvailable();

    static native boolean nativeIsReadOnly(long j);

    static native void nativeRegisterCustomType(long j, int i, int i2, String str, Class<? extends PropertyConverter> cls, Class<?> cls2);

    static native int nativeRegisterEntityClass(long j, String str, Class<?> cls);

    static native void nativeSetDbExceptionListener(long j, @Nullable DbExceptionListener dbExceptionListener);

    static native void nativeSetDebugFlags(long j, int i);

    private native String nativeStartObjectBrowser(long j, @Nullable String str, int i);

    private native boolean nativeStopObjectBrowser(long j);

    static native long nativeSysProcMeminfoKb(String str);

    static native long nativeSysProcStatusKb(String str);

    native long nativePanicModeRemoveAllObjects(long j, int i);

    native long nativeSizeOnDisk(long j);

    native long nativeValidate(long j, long j2, boolean z);

    @Nullable
    public static synchronized Object getContext() {
        return context;
    }

    @Nullable
    public static synchronized Object getRelinker() {
        return relinker;
    }

    public static synchronized BoxStore getDefault() {
        if (defaultStore == null) {
            throw new IllegalStateException("Please call buildDefault() before calling this method");
        }
        return defaultStore;
    }

    static synchronized void setDefault(BoxStore boxStore) {
        if (defaultStore != null) {
            throw new IllegalStateException("Default store was already built before. ");
        }
        defaultStore = boxStore;
    }

    public static synchronized boolean clearDefaultStore() {
        boolean z;
        z = defaultStore != null;
        defaultStore = null;
        return z;
    }

    public static String getVersionNative() {
        NativeLibraryLoader.ensureLoaded();
        return nativeGetVersion();
    }

    public static boolean hasFeature(Feature feature) {
        try {
            NativeLibraryLoader.ensureLoaded();
            return nativeHasFeature(feature.id);
        } catch (UnsatisfiedLinkError e) {
            System.err.println("Old JNI lib? " + e);
            return false;
        }
    }

    public static boolean isObjectBrowserAvailable() {
        return hasFeature(Feature.ADMIN);
    }

    public static boolean isSyncAvailable() {
        return hasFeature(Feature.SYNC);
    }

    public static boolean isSyncServerAvailable() {
        return hasFeature(Feature.SYNC_SERVER);
    }

    BoxStore(BoxStoreBuilder boxStoreBuilder) {
        context = boxStoreBuilder.context;
        relinker = boxStoreBuilder.relinker;
        NativeLibraryLoader.ensureLoaded();
        File file = boxStoreBuilder.directory;
        this.directory = file;
        String canonicalPath = getCanonicalPath(file);
        this.canonicalPath = canonicalPath;
        verifyNotAlreadyOpen(canonicalPath);
        try {
            long jNativeCreateWithFlatOptions = nativeCreateWithFlatOptions(boxStoreBuilder.buildFlatStoreOptions(this.canonicalPath), boxStoreBuilder.model);
            this.handle = jNativeCreateWithFlatOptions;
            if (jNativeCreateWithFlatOptions == 0) {
                throw new DbException("Could not create native store");
            }
            int i = boxStoreBuilder.debugFlags;
            if (i != 0) {
                this.debugTxRead = (i & 1) != 0;
                this.debugTxWrite = (i & 2) != 0;
            } else {
                this.debugTxWrite = false;
                this.debugTxRead = false;
            }
            this.debugRelations = boxStoreBuilder.debugRelations;
            for (EntityInfo<?> entityInfo : boxStoreBuilder.entityInfoList) {
                try {
                    this.dbNameByClass.put(entityInfo.getEntityClass(), entityInfo.getDbName());
                    int iNativeRegisterEntityClass = nativeRegisterEntityClass(this.handle, entityInfo.getDbName(), entityInfo.getEntityClass());
                    this.entityTypeIdByClass.put(entityInfo.getEntityClass(), Integer.valueOf(iNativeRegisterEntityClass));
                    this.classByEntityTypeId.put(iNativeRegisterEntityClass, entityInfo.getEntityClass());
                    this.propertiesByClass.put(entityInfo.getEntityClass(), entityInfo);
                    for (Property<?> property : entityInfo.getAllProperties()) {
                        if (property.customType != null) {
                            if (property.converterClass == null) {
                                throw new RuntimeException("No converter class for custom type of " + property);
                            }
                            nativeRegisterCustomType(this.handle, iNativeRegisterEntityClass, 0, property.dbName, property.converterClass, property.customType);
                        }
                    }
                } catch (RuntimeException e) {
                    throw new RuntimeException("Could not setup up entity " + entityInfo.getEntityClass(), e);
                }
            }
            int size = this.classByEntityTypeId.size();
            this.allEntityTypeIds = new int[size];
            long[] jArrKeys = this.classByEntityTypeId.keys();
            for (int i2 = 0; i2 < size; i2++) {
                this.allEntityTypeIds[i2] = (int) jArrKeys[i2];
            }
            this.objectClassPublisher = new ObjectClassPublisher(this);
            this.failedReadTxAttemptCallback = boxStoreBuilder.failedReadTxAttemptCallback;
            this.queryAttempts = Math.max(boxStoreBuilder.queryAttempts, 1);
        } catch (RuntimeException e2) {
            close();
            throw e2;
        }
    }

    static String getCanonicalPath(File file) {
        if (file.exists()) {
            if (!file.isDirectory()) {
                throw new DbException("Is not a directory: " + file.getAbsolutePath());
            }
        } else if (!file.mkdirs()) {
            throw new DbException("Could not create directory: " + file.getAbsolutePath());
        }
        try {
            return file.getCanonicalPath();
        } catch (IOException e) {
            throw new DbException("Could not verify dir", e);
        }
    }

    static void verifyNotAlreadyOpen(String str) {
        synchronized (openFiles) {
            isFileOpen(str);
            if (!openFiles.add(str)) {
                throw new DbException("Another BoxStore is still open for this directory: " + str + ". Hint: for most apps it's recommended to keep a BoxStore for the app's life time.");
            }
        }
    }

    static boolean isFileOpen(final String str) {
        boolean zContains;
        synchronized (openFiles) {
            if (!openFiles.contains(str)) {
                return false;
            }
            Thread thread = openFilesCheckerThread;
            if (thread == null || !thread.isAlive()) {
                Thread thread2 = new Thread(new Runnable() { // from class: io.objectbox.-$$Lambda$BoxStore$nOyLBBlDQ2aO3YuerqcWrs43QhU
                    @Override // java.lang.Runnable
                    public final void run() {
                        BoxStore.lambda$isFileOpen$0(str);
                    }
                });
                thread2.setDaemon(true);
                openFilesCheckerThread = thread2;
                thread2.start();
                try {
                    thread2.join(500L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (openFiles) {
                    zContains = openFiles.contains(str);
                }
                return zContains;
            }
            return isFileOpenSync(str, false);
        }
    }

    static /* synthetic */ void lambda$isFileOpen$0(String str) {
        isFileOpenSync(str, true);
        openFilesCheckerThread = null;
    }

    static boolean isFileOpenSync(String str, boolean z) {
        boolean zContains;
        synchronized (openFiles) {
            int i = 0;
            while (i < 5) {
                if (!openFiles.contains(str)) {
                    break;
                }
                i++;
                System.gc();
                if (z && i > 1) {
                    System.runFinalization();
                }
                System.gc();
                if (z && i > 1) {
                    System.runFinalization();
                }
                try {
                    openFiles.wait(100L);
                } catch (InterruptedException unused) {
                }
            }
            zContains = openFiles.contains(str);
        }
        return zContains;
    }

    public static boolean isDatabaseOpen(Object obj, @Nullable String str) throws IOException {
        return isFileOpen(BoxStoreBuilder.getAndroidDbDir(obj, str).getCanonicalPath());
    }

    public static boolean isDatabaseOpen(@Nullable File file, @Nullable String str) throws IOException {
        return isFileOpen(BoxStoreBuilder.getDbDir(file, str).getCanonicalPath());
    }

    public static boolean isDatabaseOpen(File file) throws IOException {
        return isFileOpen(file.getCanonicalPath());
    }

    public static long sysProcMeminfoKb(String str) {
        return nativeSysProcMeminfoKb(str);
    }

    public static long sysProcStatusKb(String str) {
        return nativeSysProcStatusKb(str);
    }

    public long sizeOnDisk() {
        checkOpen();
        return nativeSizeOnDisk(this.handle);
    }

    protected void finalize() throws Throwable {
        close();
        super.finalize();
    }

    private void checkOpen() {
        if (this.closed) {
            throw new IllegalStateException("Store is closed");
        }
    }

    String getDbName(Class<?> cls) {
        return this.dbNameByClass.get(cls);
    }

    Integer getEntityTypeId(Class<?> cls) {
        return this.entityTypeIdByClass.get(cls);
    }

    public int getEntityTypeIdOrThrow(Class<?> cls) {
        Integer num = this.entityTypeIdByClass.get(cls);
        if (num == null) {
            throw new DbSchemaException("No entity registered for " + cls);
        }
        return num.intValue();
    }

    public Collection<Class<?>> getAllEntityClasses() {
        return this.dbNameByClass.keySet();
    }

    int[] getAllEntityTypeIds() {
        return this.allEntityTypeIds;
    }

    Class<?> getEntityClassOrThrow(int i) {
        Class<?> cls = this.classByEntityTypeId.get(i);
        if (cls != null) {
            return cls;
        }
        throw new DbSchemaException("No entity registered for type ID " + i);
    }

    <T> EntityInfo<T> getEntityInfo(Class<T> cls) {
        return (EntityInfo) this.propertiesByClass.get(cls);
    }

    public Transaction beginTx() {
        checkOpen();
        int i = this.commitCount;
        if (this.debugTxWrite) {
            System.out.println("Begin TX with commit count " + i);
        }
        long jNativeBeginTx = nativeBeginTx(this.handle);
        if (jNativeBeginTx == 0) {
            throw new DbException("Could not create native transaction");
        }
        Transaction transaction = new Transaction(this, jNativeBeginTx, i);
        synchronized (this.transactions) {
            this.transactions.add(transaction);
        }
        return transaction;
    }

    public Transaction beginReadTx() {
        checkOpen();
        int i = this.commitCount;
        if (this.debugTxRead) {
            System.out.println("Begin read TX with commit count " + i);
        }
        long jNativeBeginReadTx = nativeBeginReadTx(this.handle);
        if (jNativeBeginReadTx == 0) {
            throw new DbException("Could not create native read transaction");
        }
        Transaction transaction = new Transaction(this, jNativeBeginReadTx, i);
        synchronized (this.transactions) {
            this.transactions.add(transaction);
        }
        return transaction;
    }

    public boolean isClosed() {
        return this.closed;
    }

    public boolean isReadOnly() {
        checkOpen();
        return nativeIsReadOnly(this.handle);
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        boolean z;
        ArrayList arrayList;
        synchronized (this) {
            z = this.closed;
            if (!this.closed) {
                if (this.objectBrowserPort != 0) {
                    try {
                        stopObjectBrowser();
                    } catch (Throwable th) {
                        th.printStackTrace();
                    }
                }
                this.closed = true;
                synchronized (this.transactions) {
                    arrayList = new ArrayList(this.transactions);
                }
                Iterator it = arrayList.iterator();
                while (it.hasNext()) {
                    ((Transaction) it.next()).close();
                }
                if (this.handle != 0) {
                    nativeDelete(this.handle);
                }
                this.threadPool.shutdown();
                checkThreadTermination();
            }
        }
        if (z) {
            return;
        }
        synchronized (openFiles) {
            openFiles.remove(this.canonicalPath);
            openFiles.notifyAll();
        }
    }

    private void checkThreadTermination() {
        try {
            if (this.threadPool.awaitTermination(1L, TimeUnit.SECONDS)) {
                return;
            }
            int iActiveCount = Thread.activeCount();
            System.err.println("Thread pool not terminated in time; printing stack traces...");
            Thread[] threadArr = new Thread[iActiveCount + 2];
            int iEnumerate = Thread.enumerate(threadArr);
            for (int i = 0; i < iEnumerate; i++) {
                System.err.println("Thread: " + threadArr[i].getName());
                Thread.dumpStack();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public boolean deleteAllFiles() {
        if (!this.closed) {
            throw new IllegalStateException("Store must be closed");
        }
        return deleteAllFiles(this.directory);
    }

    public static boolean deleteAllFiles(File file) {
        if (!file.exists()) {
            return true;
        }
        if (isFileOpen(getCanonicalPath(file))) {
            throw new IllegalStateException("Cannot delete files: store is still open");
        }
        File[] fileArrListFiles = file.listFiles();
        if (fileArrListFiles == null) {
            return false;
        }
        for (File file2 : fileArrListFiles) {
            if (!file2.delete() && file2.exists()) {
                return false;
            }
        }
        return file.delete();
    }

    public static boolean deleteAllFiles(Object obj, @Nullable String str) {
        return deleteAllFiles(BoxStoreBuilder.getAndroidDbDir(obj, str));
    }

    public static boolean deleteAllFiles(@Nullable File file, @Nullable String str) {
        return deleteAllFiles(BoxStoreBuilder.getDbDir(file, str));
    }

    public void removeAllObjects() {
        checkOpen();
        nativeDropAllData(this.handle);
    }

    public void unregisterTransaction(Transaction transaction) {
        synchronized (this.transactions) {
            this.transactions.remove(transaction);
        }
    }

    void txCommitted(Transaction transaction, @Nullable int[] iArr) {
        synchronized (this.txCommitCountLock) {
            this.commitCount++;
            if (this.debugTxWrite) {
                PrintStream printStream = System.out;
                StringBuilder sb = new StringBuilder();
                sb.append("TX committed. New commit count: ");
                sb.append(this.commitCount);
                sb.append(", entity types affected: ");
                sb.append(iArr != null ? iArr.length : 0);
                printStream.println(sb.toString());
            }
        }
        Iterator<Box<?>> it = this.boxes.values().iterator();
        while (it.hasNext()) {
            it.next().txCommitted(transaction);
        }
        if (iArr != null) {
            this.objectClassPublisher.publish(iArr);
        }
    }

    public <T> Box<T> boxFor(Class<T> cls) {
        Box<?> box;
        Box<T> box2 = (Box) this.boxes.get(cls);
        if (box2 != null) {
            return box2;
        }
        if (!this.dbNameByClass.containsKey(cls)) {
            throw new IllegalArgumentException(cls + " is not a known entity. Please add it and trigger generation again.");
        }
        synchronized (this.boxes) {
            box = this.boxes.get(cls);
            if (box == null) {
                box = new Box<>(this, cls);
                this.boxes.put(cls, box);
            }
        }
        return (Box<T>) box;
    }

    public void runInTx(Runnable runnable) {
        Transaction transaction = this.activeTx.get();
        if (transaction == null) {
            Transaction transactionBeginTx = beginTx();
            this.activeTx.set(transactionBeginTx);
            try {
                runnable.run();
                transactionBeginTx.commit();
                return;
            } finally {
                this.activeTx.remove();
                transactionBeginTx.close();
            }
        }
        if (transaction.isReadOnly()) {
            throw new IllegalStateException("Cannot start a transaction while a read only transaction is active");
        }
        runnable.run();
    }

    public void runInReadTx(Runnable runnable) {
        if (this.activeTx.get() == null) {
            Transaction transactionBeginReadTx = beginReadTx();
            this.activeTx.set(transactionBeginReadTx);
            try {
                runnable.run();
                return;
            } finally {
                this.activeTx.remove();
                Iterator<Box<?>> it = this.boxes.values().iterator();
                while (it.hasNext()) {
                    it.next().readTxFinished(transactionBeginReadTx);
                }
                transactionBeginReadTx.close();
            }
        }
        runnable.run();
    }

    public <T> T callInReadTxWithRetry(Callable<T> callable, int i, int i2, boolean z) {
        if (i == 1) {
            return (T) callInReadTx(callable);
        }
        if (i < 1) {
            throw new IllegalArgumentException("Illegal value of attempts: " + i);
        }
        long j = i2;
        DbException e = null;
        for (int i3 = 1; i3 <= i; i3++) {
            try {
                return (T) callInReadTx(callable);
            } catch (DbException e2) {
                e = e2;
                String strDiagnose = diagnose();
                String str = i3 + " of " + i + " attempts of calling a read TX failed:";
                if (z) {
                    System.err.println(str);
                    e.printStackTrace();
                    System.err.println(strDiagnose);
                    System.err.flush();
                    System.gc();
                    System.runFinalization();
                    cleanStaleReadTransactions();
                }
                TxCallback<?> txCallback = this.failedReadTxAttemptCallback;
                if (txCallback != null) {
                    txCallback.txFinished(null, new DbException(str + " \n" + strDiagnose, e));
                }
                try {
                    Thread.sleep(j);
                    j *= 2;
                } catch (InterruptedException e3) {
                    e3.printStackTrace();
                    throw e;
                }
            }
        }
        throw e;
    }

    public <T> T callInReadTx(Callable<T> callable) {
        if (this.activeTx.get() == null) {
            Transaction transactionBeginReadTx = beginReadTx();
            this.activeTx.set(transactionBeginReadTx);
            try {
                try {
                    return callable.call();
                } catch (RuntimeException e) {
                    throw e;
                } catch (Exception e2) {
                    throw new RuntimeException("Callable threw exception", e2);
                }
            } finally {
                this.activeTx.remove();
                Iterator<Box<?>> it = this.boxes.values().iterator();
                while (it.hasNext()) {
                    it.next().readTxFinished(transactionBeginReadTx);
                }
                transactionBeginReadTx.close();
            }
        }
        try {
            return callable.call();
        } catch (Exception e3) {
            throw new RuntimeException("Callable threw exception", e3);
        }
    }

    public <R> R callInTx(Callable<R> callable) throws Exception {
        Transaction transaction = this.activeTx.get();
        if (transaction == null) {
            Transaction transactionBeginTx = beginTx();
            this.activeTx.set(transactionBeginTx);
            try {
                R rCall = callable.call();
                transactionBeginTx.commit();
                return rCall;
            } finally {
                this.activeTx.remove();
                transactionBeginTx.close();
            }
        }
        if (transaction.isReadOnly()) {
            throw new IllegalStateException("Cannot start a transaction while a read only transaction is active");
        }
        return callable.call();
    }

    public <R> R callInTxNoException(Callable<R> callable) {
        try {
            return (R) callInTx(callable);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void runInTxAsync(final Runnable runnable, @Nullable final TxCallback<Void> txCallback) {
        this.threadPool.submit(new Runnable() { // from class: io.objectbox.-$$Lambda$BoxStore$P_oHQ5Hy31VkWhLF__Z7tyBxc6k
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$runInTxAsync$1$BoxStore(runnable, txCallback);
            }
        });
    }

    public /* synthetic */ void lambda$runInTxAsync$1$BoxStore(Runnable runnable, TxCallback txCallback) {
        try {
            runInTx(runnable);
            if (txCallback != null) {
                txCallback.txFinished(null, null);
            }
        } catch (Throwable th) {
            if (txCallback != null) {
                txCallback.txFinished(null, th);
            }
        }
    }

    public <R> void callInTxAsync(final Callable<R> callable, @Nullable final TxCallback<R> txCallback) {
        this.threadPool.submit(new Runnable() { // from class: io.objectbox.-$$Lambda$BoxStore$mp6kSIytJP6E6WlJcmGS242Js34
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$callInTxAsync$2$BoxStore(callable, txCallback);
            }
        });
    }

    public /* synthetic */ void lambda$callInTxAsync$2$BoxStore(Callable callable, TxCallback txCallback) {
        try {
            Object objCallInTx = callInTx(callable);
            if (txCallback != null) {
                txCallback.txFinished(objCallInTx, null);
            }
        } catch (Throwable th) {
            if (txCallback != null) {
                txCallback.txFinished(null, th);
            }
        }
    }

    public String diagnose() {
        checkOpen();
        return nativeDiagnose(this.handle);
    }

    public long validate(long j, boolean z) {
        if (j < 0) {
            throw new IllegalArgumentException("pageLimit must be zero or positive");
        }
        checkOpen();
        return nativeValidate(this.handle, j, z);
    }

    public int cleanStaleReadTransactions() {
        checkOpen();
        return nativeCleanStaleReadTransactions(this.handle);
    }

    public void closeThreadResources() {
        Iterator<Box<?>> it = this.boxes.values().iterator();
        while (it.hasNext()) {
            it.next().closeThreadResources();
        }
    }

    long internalHandle() {
        return this.handle;
    }

    public SubscriptionBuilder<Class> subscribe() {
        checkOpen();
        return new SubscriptionBuilder<>(this.objectClassPublisher, null);
    }

    public <T> SubscriptionBuilder<Class<T>> subscribe(Class<T> cls) {
        checkOpen();
        return new SubscriptionBuilder<>(this.objectClassPublisher, cls);
    }

    @Nullable
    public String startObjectBrowser() {
        String strStartObjectBrowser;
        verifyObjectBrowserNotRunning();
        for (int i = 8090; i < 8100; i++) {
            try {
                strStartObjectBrowser = startObjectBrowser(i);
            } catch (DbException e) {
                if (e.getMessage() == null || !e.getMessage().contains("port")) {
                    throw e;
                }
            }
            if (strStartObjectBrowser != null) {
                return strStartObjectBrowser;
            }
        }
        return null;
    }

    @Nullable
    public String startObjectBrowser(int i) {
        verifyObjectBrowserNotRunning();
        checkOpen();
        String strNativeStartObjectBrowser = nativeStartObjectBrowser(this.handle, null, i);
        if (strNativeStartObjectBrowser != null) {
            this.objectBrowserPort = i;
        }
        return strNativeStartObjectBrowser;
    }

    @Nullable
    public String startObjectBrowser(String str) {
        verifyObjectBrowserNotRunning();
        checkOpen();
        try {
            int port = new URL(str).getPort();
            String strNativeStartObjectBrowser = nativeStartObjectBrowser(this.handle, str, 0);
            if (strNativeStartObjectBrowser != null) {
                this.objectBrowserPort = port;
            }
            return strNativeStartObjectBrowser;
        } catch (MalformedURLException e) {
            throw new RuntimeException("Can not start Object Browser at " + str, e);
        }
    }

    public synchronized boolean stopObjectBrowser() {
        if (this.objectBrowserPort == 0) {
            throw new IllegalStateException("ObjectBrowser has not been started before");
        }
        this.objectBrowserPort = 0;
        checkOpen();
        return nativeStopObjectBrowser(this.handle);
    }

    public int getObjectBrowserPort() {
        return this.objectBrowserPort;
    }

    public boolean isObjectBrowserRunning() {
        return this.objectBrowserPort != 0;
    }

    private void verifyObjectBrowserNotRunning() {
        if (this.objectBrowserPort == 0) {
            return;
        }
        throw new DbException("ObjectBrowser is already running at port " + this.objectBrowserPort);
    }

    public void setDbExceptionListener(@Nullable DbExceptionListener dbExceptionListener) {
        checkOpen();
        nativeSetDbExceptionListener(this.handle, dbExceptionListener);
    }

    public Future<?> internalScheduleThread(Runnable runnable) {
        return this.threadPool.submit(runnable);
    }

    public ExecutorService internalThreadPool() {
        return this.threadPool;
    }

    public boolean isDebugRelations() {
        return this.debugRelations;
    }

    public int internalQueryAttempts() {
        return this.queryAttempts;
    }

    public TxCallback<?> internalFailedReadTxAttemptCallback() {
        return this.failedReadTxAttemptCallback;
    }

    void setDebugFlags(int i) {
        checkOpen();
        nativeSetDebugFlags(this.handle, i);
    }

    long panicModeRemoveAllObjects(int i) {
        checkOpen();
        return nativePanicModeRemoveAllObjects(this.handle, i);
    }

    public long getNativeStore() {
        checkOpen();
        return this.handle;
    }

    @Nullable
    public SyncClient getSyncClient() {
        return this.syncClient;
    }

    void setSyncClient(@Nullable SyncClient syncClient) {
        this.syncClient = syncClient;
    }
}
