package com.google.android.textclassifier;

import java.util.concurrent.atomic.AtomicBoolean;

/* JADX INFO: loaded from: classes.dex */
public final class LangIdModel implements AutoCloseable {
    private final AtomicBoolean isClosed = new AtomicBoolean(false);
    private long modelPtr;

    private native void nativeClose(long j);

    private native LanguageResult[] nativeDetectLanguages(long j, String str);

    private native float nativeGetLangIdNoiseThreshold(long j);

    private native float nativeGetLangIdThreshold(long j);

    private native int nativeGetMinTextSizeInBytes(long j);

    private native int nativeGetVersion(long j);

    private static native int nativeGetVersionFromFd(int i);

    private static native long nativeNew(int i);

    private static native long nativeNewFromPath(String str);

    static {
        System.loadLibrary("textclassifier");
    }

    public LangIdModel(int i) {
        long jNativeNew = nativeNew(i);
        this.modelPtr = jNativeNew;
        if (jNativeNew == 0) {
            throw new IllegalArgumentException("Couldn't initialize LangId from given file descriptor.");
        }
    }

    public LangIdModel(String str) {
        long jNativeNewFromPath = nativeNewFromPath(str);
        this.modelPtr = jNativeNewFromPath;
        if (jNativeNewFromPath == 0) {
            throw new IllegalArgumentException("Couldn't initialize LangId from given file.");
        }
    }

    public LanguageResult[] detectLanguages(String str) {
        return nativeDetectLanguages(this.modelPtr, str);
    }

    @Override // java.lang.AutoCloseable
    public void close() {
        if (this.isClosed.compareAndSet(false, true)) {
            nativeClose(this.modelPtr);
            this.modelPtr = 0L;
        }
    }

    protected void finalize() throws Throwable {
        try {
            close();
        } finally {
            super.finalize();
        }
    }

    public static final class LanguageResult {
        final String mLanguage;
        final float mScore;

        LanguageResult(String str, float f) {
            this.mLanguage = str;
            this.mScore = f;
        }

        public final String getLanguage() {
            return this.mLanguage;
        }

        public final float getScore() {
            return this.mScore;
        }
    }

    public int getVersion() {
        return nativeGetVersion(this.modelPtr);
    }

    public float getLangIdThreshold() {
        return nativeGetLangIdThreshold(this.modelPtr);
    }

    public static int getVersion(int i) {
        return nativeGetVersionFromFd(i);
    }

    long getNativePointer() {
        return this.modelPtr;
    }

    float getLangIdNoiseThreshold() {
        return nativeGetLangIdNoiseThreshold(this.modelPtr);
    }

    int getMinTextSizeInBytes() {
        return nativeGetMinTextSizeInBytes(this.modelPtr);
    }

    long getNativeLangIdPointer() {
        return this.modelPtr;
    }
}
