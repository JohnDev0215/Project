package org.chromium.base.library_loader;

import android.content.Context;
import android.os.AsyncTask;
import android.os.SystemClock;
import java.util.concurrent.atomic.AtomicBoolean;
import javax.annotation.Nullable;
import org.chromium.base.CommandLine;
import org.chromium.base.ContextUtils;
import org.chromium.base.Log;
import org.chromium.base.TraceEvent;
import org.chromium.base.annotations.JNINamespace;
import org.chromium.base.metrics.RecordHistogram;

/* JADX INFO: loaded from: classes3.dex */
@JNINamespace("base::android")
public class LibraryLoader {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final boolean DEBUG = false;
    private static final String TAG = "LibraryLoader";
    private static volatile LibraryLoader sInstance;
    private static NativeLibraryPreloader sLibraryPreloader;
    private static final Object sLock = new Object();
    private boolean mCommandLineSwitched;
    private volatile boolean mInitialized;
    private boolean mIsUsingBrowserSharedRelros;
    private long mLibraryLoadTimeMs;
    private final int mLibraryProcessType;
    private boolean mLibraryWasLoadedFromApk;
    private boolean mLoadAtFixedAddressFailed;
    private boolean mLoaded;
    private int mLibraryPreloaderStatus = -1;
    private final AtomicBoolean mPrefetchLibraryHasBeenCalled = new AtomicBoolean();

    /* JADX INFO: Access modifiers changed from: private */
    public static native boolean nativeForkAndPrefetchNativeLibrary();

    private native String nativeGetVersionNumber();

    private native void nativeInitCommandLine(String[] strArr);

    private native boolean nativeLibraryLoaded();

    /* JADX INFO: Access modifiers changed from: private */
    public static native int nativePercentageOfResidentNativeLibraryCode();

    private native void nativeRecordChromiumAndroidLinkerBrowserHistogram(boolean z, boolean z2, int i, long j);

    private native void nativeRecordLibraryPreloaderBrowserHistogram(int i);

    private native void nativeRegisterChromiumAndroidLinkerRendererHistogram(boolean z, boolean z2, long j);

    private native void nativeRegisterLibraryPreloaderRendererHistogram(int i);

    public static void setNativeLibraryPreloader(NativeLibraryPreloader nativeLibraryPreloader) {
        synchronized (sLock) {
            sLibraryPreloader = nativeLibraryPreloader;
        }
    }

    public static LibraryLoader get(int i) throws ProcessInitException {
        synchronized (sLock) {
            if (sInstance != null) {
                if (sInstance.mLibraryProcessType != i) {
                    throw new ProcessInitException(2);
                }
                return sInstance;
            }
            sInstance = new LibraryLoader(i);
            return sInstance;
        }
    }

    private LibraryLoader(int i) {
        this.mLibraryProcessType = i;
    }

    public void ensureInitialized(Context context) throws ProcessInitException {
        ContextUtils.initApplicationContext(context.getApplicationContext());
        synchronized (sLock) {
            if (this.mInitialized) {
                return;
            }
            loadAlreadyLocked(context);
            initializeAlreadyLocked();
        }
    }

    public static boolean isInitialized() {
        return sInstance != null && sInstance.mInitialized;
    }

    public void loadNow(Context context) throws ProcessInitException {
        synchronized (sLock) {
            loadAlreadyLocked(context);
        }
    }

    public void initialize() throws ProcessInitException {
        synchronized (sLock) {
            initializeAlreadyLocked();
        }
    }

    /* JADX WARN: Type inference failed for: r1v1, types: [org.chromium.base.library_loader.LibraryLoader$1] */
    public void asyncPrefetchLibrariesToMemory() {
        final boolean zCompareAndSet = this.mPrefetchLibraryHasBeenCalled.compareAndSet(false, true);
        new AsyncTask<Void, Void, Void>() { // from class: org.chromium.base.library_loader.LibraryLoader.1
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // android.os.AsyncTask
            public Void doInBackground(Void... voidArr) {
                TraceEvent.begin("LibraryLoader.asyncPrefetchLibrariesToMemory");
                int iNativePercentageOfResidentNativeLibraryCode = LibraryLoader.nativePercentageOfResidentNativeLibraryCode();
                boolean z = false;
                if (zCompareAndSet) {
                    boolean zNativeForkAndPrefetchNativeLibrary = LibraryLoader.nativeForkAndPrefetchNativeLibrary();
                    if (!zNativeForkAndPrefetchNativeLibrary) {
                        Log.w(LibraryLoader.TAG, "Forking a process to prefetch the native library failed.", new Object[0]);
                    }
                    z = zNativeForkAndPrefetchNativeLibrary;
                }
                RecordHistogram.initialize();
                if (zCompareAndSet) {
                    RecordHistogram.recordBooleanHistogram("LibraryLoader.PrefetchStatus", z);
                }
                if (iNativePercentageOfResidentNativeLibraryCode != -1) {
                    RecordHistogram.recordPercentageHistogram("LibraryLoader.PercentageOfResidentCodeBeforePrefetch".concat(zCompareAndSet ? ".ColdStartup" : ".WarmStartup"), iNativePercentageOfResidentNativeLibraryCode);
                }
                TraceEvent.end("LibraryLoader.asyncPrefetchLibrariesToMemory");
                return null;
            }
        }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
    }

    private void loadLibrary(Linker linker, @Nullable String str, String str2) {
        if (linker.isUsingBrowserSharedRelros()) {
            this.mIsUsingBrowserSharedRelros = true;
            try {
                linker.loadLibrary(str, str2);
            } catch (UnsatisfiedLinkError unused) {
                Log.w(TAG, "Failed to load native library with shared RELRO, retrying without", new Object[0]);
                this.mLoadAtFixedAddressFailed = true;
                linker.loadLibraryNoFixedAddress(str, str2);
            }
        } else {
            linker.loadLibrary(str, str2);
        }
        if (str != null) {
            this.mLibraryWasLoadedFromApk = true;
        }
    }

    private void loadAlreadyLocked(Context context) throws ProcessInitException {
        String str;
        try {
            if (!this.mLoaded) {
                long jUptimeMillis = SystemClock.uptimeMillis();
                if (Linker.isUsed()) {
                    Linker linker = Linker.getInstance();
                    linker.prepareLibraryLoad();
                    for (String str2 : NativeLibraries.LIBRARIES) {
                        if (!linker.isChromiumLinkerLibrary(str2)) {
                            String strMapLibraryName = System.mapLibraryName(str2);
                            if (Linker.isInZipFile()) {
                                str = context.getApplicationInfo().sourceDir;
                                Log.i(TAG, "Loading " + str2 + " from within " + str, new Object[0]);
                            } else {
                                Log.i(TAG, "Loading " + str2, new Object[0]);
                                str = null;
                            }
                            loadLibrary(linker, str, strMapLibraryName);
                        }
                    }
                    linker.finishLibraryLoad();
                } else {
                    NativeLibraryPreloader nativeLibraryPreloader = sLibraryPreloader;
                    if (nativeLibraryPreloader != null) {
                        this.mLibraryPreloaderStatus = nativeLibraryPreloader.loadLibrary(context);
                    }
                    for (String str3 : NativeLibraries.LIBRARIES) {
                        System.loadLibrary(str3);
                    }
                }
                long jUptimeMillis2 = SystemClock.uptimeMillis();
                long j = jUptimeMillis2 - jUptimeMillis;
                this.mLibraryLoadTimeMs = j;
                Log.i(TAG, String.format("Time to load native libraries: %d ms (timestamps %d-%d)", Long.valueOf(j), Long.valueOf(jUptimeMillis % 10000), Long.valueOf(jUptimeMillis2 % 10000)), new Object[0]);
                this.mLoaded = true;
            }
            Log.i(TAG, String.format("Expected native library version number \"%s\", actual native library version number \"%s\"", NativeLibraries.sVersionNumber, nativeGetVersionNumber()), new Object[0]);
            if (!NativeLibraries.sVersionNumber.equals(nativeGetVersionNumber())) {
                throw new ProcessInitException(3);
            }
        } catch (UnsatisfiedLinkError e) {
            throw new ProcessInitException(2, e);
        }
    }

    private static boolean isAbiSplit(String str) {
        return str.startsWith("abi_");
    }

    public void switchCommandLineForWebView() {
        synchronized (sLock) {
            ensureCommandLineSwitchedAlreadyLocked();
        }
    }

    private void ensureCommandLineSwitchedAlreadyLocked() {
        if (this.mCommandLineSwitched) {
            return;
        }
        nativeInitCommandLine(CommandLine.getJavaSwitchesOrNull());
        CommandLine.enableNativeProxy();
        this.mCommandLineSwitched = true;
        ContextUtils.initApplicationContextForNative();
    }

    private void initializeAlreadyLocked() throws ProcessInitException {
        if (this.mInitialized) {
            return;
        }
        ensureCommandLineSwitchedAlreadyLocked();
        if (!nativeLibraryLoaded()) {
            Log.e(TAG, "error calling nativeLibraryLoaded", new Object[0]);
            throw new ProcessInitException(1);
        }
        TraceEvent.registerNativeEnabledObserver();
        this.mInitialized = true;
    }

    public void onNativeInitializationComplete(Context context) {
        recordBrowserProcessHistogram(context);
    }

    private void recordBrowserProcessHistogram(Context context) {
        Linker.getInstance();
        if (Linker.isUsed()) {
            nativeRecordChromiumAndroidLinkerBrowserHistogram(this.mIsUsingBrowserSharedRelros, this.mLoadAtFixedAddressFailed, getLibraryLoadFromApkStatus(context), this.mLibraryLoadTimeMs);
        }
        if (sLibraryPreloader != null) {
            nativeRecordLibraryPreloaderBrowserHistogram(this.mLibraryPreloaderStatus);
        }
    }

    private int getLibraryLoadFromApkStatus(Context context) {
        return this.mLibraryWasLoadedFromApk ? 3 : 0;
    }

    public void registerRendererProcessHistogram(boolean z, boolean z2) {
        Linker.getInstance();
        if (Linker.isUsed()) {
            nativeRegisterChromiumAndroidLinkerRendererHistogram(z, z2, this.mLibraryLoadTimeMs);
        }
        if (sLibraryPreloader != null) {
            nativeRegisterLibraryPreloaderRendererHistogram(this.mLibraryPreloaderStatus);
        }
    }

    public static int getLibraryProcessType() {
        if (sInstance == null) {
            return 0;
        }
        return sInstance.mLibraryProcessType;
    }
}
