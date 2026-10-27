package com.android.inputmethod.latin;

import com.android.inputmethod.latin.common.NativeSuggestOptions;
import com.android.inputmethod.latin.utils.JniUtils;
import java.util.Locale;

/* JADX INFO: loaded from: classes2.dex */
public final class DicTraverseSession {
    private static final int MAX_RESULTS = 18;
    private long mNativeDicTraverseSession;
    public final int[] mInputCodePoints = new int[48];
    public final int[][] mPrevWordCodePointArrays = new int[3][];
    public final boolean[] mIsBeginningOfSentenceArray = new boolean[3];
    public final int[] mOutputSuggestionCount = new int[1];
    public final int[] mOutputCodePoints = new int[864];
    public final int[] mSpaceIndices = new int[18];
    public final int[] mOutputScores = new int[18];
    public final int[] mOutputTypes = new int[18];
    public final int[] mOutputAutoCommitFirstWordConfidence = new int[1];
    public final float[] mInputOutputWeightOfLangModelVsSpatialModel = new float[1];
    public final NativeSuggestOptions mNativeSuggestOptions = new NativeSuggestOptions();

    private static native void initDicTraverseSessionNative(long nativeDicTraverseSession, long dictionary, int[] previousWord, int previousWordLength);

    private static native void releaseDicTraverseSessionNative(long nativeDicTraverseSession);

    private static native long setDicTraverseSessionNative(String locale, long dictSize);

    static {
        JniUtils.loadNativeLibrary();
    }

    public DicTraverseSession(Locale locale, long dictionary, long dictSize) {
        this.mNativeDicTraverseSession = createNativeDicTraverseSession(locale != null ? locale.toString() : "", dictSize);
        initSession(dictionary);
    }

    public long getSession() {
        return this.mNativeDicTraverseSession;
    }

    public void initSession(long dictionary) {
        initSession(dictionary, null, 0);
    }

    public void initSession(long dictionary, int[] previousWord, int previousWordLength) {
        initDicTraverseSessionNative(this.mNativeDicTraverseSession, dictionary, previousWord, previousWordLength);
    }

    private static long createNativeDicTraverseSession(String locale, long dictSize) {
        return setDicTraverseSessionNative(locale, dictSize);
    }

    private void closeInternal() {
        long j = this.mNativeDicTraverseSession;
        if (j != 0) {
            releaseDicTraverseSessionNative(j);
            this.mNativeDicTraverseSession = 0L;
        }
    }

    public void close() {
        closeInternal();
    }

    protected void finalize() throws Throwable {
        try {
            closeInternal();
        } finally {
            super.finalize();
        }
    }
}
