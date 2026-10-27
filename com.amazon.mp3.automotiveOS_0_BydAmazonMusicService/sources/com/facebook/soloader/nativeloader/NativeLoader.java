package com.facebook.soloader.nativeloader;

/* JADX INFO: loaded from: classes6.dex */
public class NativeLoader {
    private static NativeLoaderDelegate sDelegate;

    private NativeLoader() {
    }

    public static boolean loadLibrary(String str) {
        return loadLibrary(str, 0);
    }

    public static boolean loadLibrary(String str, int i) {
        NativeLoaderDelegate nativeLoaderDelegate;
        synchronized (NativeLoader.class) {
            nativeLoaderDelegate = sDelegate;
            if (nativeLoaderDelegate == null) {
                throw new IllegalStateException("NativeLoader has not been initialized.  To use standard native library loading, call NativeLoader.init(new SystemDelegate()).");
            }
        }
        return nativeLoaderDelegate.loadLibrary(str, i);
    }

    public static int getSoSourcesVersion() {
        NativeLoaderDelegate nativeLoaderDelegate;
        synchronized (NativeLoader.class) {
            nativeLoaderDelegate = sDelegate;
            if (nativeLoaderDelegate == null) {
                throw new IllegalStateException("NativeLoader has not been initialized.  To use standard native library loading, call NativeLoader.init(new SystemDelegate()).");
            }
        }
        return nativeLoaderDelegate.getSoSourcesVersion();
    }

    public static synchronized void init(NativeLoaderDelegate nativeLoaderDelegate) {
        if (sDelegate != null) {
            throw new IllegalStateException("Cannot re-initialize NativeLoader.");
        }
        sDelegate = nativeLoaderDelegate;
    }

    public static synchronized boolean isInitialized() {
        return sDelegate != null;
    }

    public static synchronized void initIfUninitialized(NativeLoaderDelegate nativeLoaderDelegate) {
        if (!isInitialized()) {
            init(nativeLoaderDelegate);
        }
    }
}
