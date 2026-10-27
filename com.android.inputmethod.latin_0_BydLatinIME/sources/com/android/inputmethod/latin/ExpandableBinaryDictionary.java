package com.android.inputmethod.latin;

import android.content.Context;
import android.util.Log;
import com.android.inputmethod.latin.BinaryDictionary;
import com.android.inputmethod.latin.common.FileUtils;
import com.android.inputmethod.latin.makedict.DictionaryHeader;
import com.android.inputmethod.latin.makedict.UnsupportedFormatException;
import com.android.inputmethod.latin.makedict.WordProperty;
import com.android.inputmethod.latin.utils.AsyncResultHolder;
import com.android.inputmethod.latin.utils.CombinedFormatUtils;
import com.android.inputmethod.latin.utils.ExecutorUtils;
import com.android.inputmethod.latin.utils.WordInputEventForPersonalization;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public abstract class ExpandableBinaryDictionary extends Dictionary {
    private static final boolean DBG_STRESS_TEST = false;
    private static final boolean DEBUG = false;
    private static final WordProperty[] DEFAULT_WORD_PROPERTIES_FOR_SYNC = new WordProperty[0];
    private static final int DICTIONARY_FORMAT_VERSION = 403;
    protected static final String DICT_FILE_EXTENSION = ".dict";
    protected static final int MAX_WORD_LENGTH = 48;
    private static final String TAG = "ExpandableBinaryDictionary";
    private static final int TIMEOUT_FOR_READ_OPS_IN_MILLISECONDS = 100;
    private Map<String, String> mAdditionalAttributeMap;
    private BinaryDictionary mBinaryDictionary;
    protected final Context mContext;
    private final File mDictFile;
    private final String mDictName;
    private final AtomicBoolean mIsReloading;
    private final ReentrantReadWriteLock mLock;
    private boolean mNeedsToRecreate;

    public interface UpdateEntriesForInputEventsCallback {
        void onFinished();
    }

    static boolean matchesExpectedBinaryDictFormatVersionForThisType(final int formatVersion) {
        return formatVersion == 403;
    }

    private static boolean needsToMigrateDictionary(final int formatVersion) {
        return formatVersion == 402;
    }

    protected abstract void loadInitialContentsLocked();

    public boolean isValidDictionaryLocked() {
        return this.mBinaryDictionary.isValidDictionary();
    }

    public ExpandableBinaryDictionary(final Context context, final String dictName, final Locale locale, final String dictType, final File dictFile) {
        super(dictType, locale);
        this.mAdditionalAttributeMap = null;
        this.mDictName = dictName;
        this.mContext = context;
        this.mDictFile = getDictFile(context, dictName, dictFile);
        this.mBinaryDictionary = null;
        this.mIsReloading = new AtomicBoolean();
        this.mNeedsToRecreate = false;
        this.mLock = new ReentrantReadWriteLock();
    }

    public static File getDictFile(final Context context, final String dictName, final File dictFile) {
        return dictFile != null ? dictFile : new File(context.getFilesDir(), dictName + DICT_FILE_EXTENSION);
    }

    public static String getDictName(final String name, final Locale locale, final File dictFile) {
        return dictFile != null ? dictFile.getName() : name + "." + locale.toString();
    }

    private void asyncExecuteTaskWithWriteLock(final Runnable task) {
        asyncExecuteTaskWithLock(this.mLock.writeLock(), task);
    }

    private static void asyncExecuteTaskWithLock(final Lock lock, final Runnable task) {
        ExecutorUtils.getBackgroundExecutor(ExecutorUtils.KEYBOARD).execute(new Runnable() { // from class: com.android.inputmethod.latin.ExpandableBinaryDictionary.1
            @Override // java.lang.Runnable
            public void run() {
                lock.lock();
                try {
                    task.run();
                } finally {
                    lock.unlock();
                }
            }
        });
    }

    @Nullable
    BinaryDictionary getBinaryDictionary() {
        return this.mBinaryDictionary;
    }

    void closeBinaryDictionary() {
        BinaryDictionary binaryDictionary = this.mBinaryDictionary;
        if (binaryDictionary != null) {
            binaryDictionary.close();
            this.mBinaryDictionary = null;
        }
    }

    @Override // com.android.inputmethod.latin.Dictionary
    public void close() {
        asyncExecuteTaskWithWriteLock(new Runnable() { // from class: com.android.inputmethod.latin.ExpandableBinaryDictionary.2
            @Override // java.lang.Runnable
            public void run() {
                ExpandableBinaryDictionary.this.closeBinaryDictionary();
            }
        });
    }

    protected Map<String, String> getHeaderAttributeMap() {
        HashMap map = new HashMap();
        Map<String, String> map2 = this.mAdditionalAttributeMap;
        if (map2 != null) {
            map.putAll(map2);
        }
        map.put("dictionary", this.mDictName);
        map.put("locale", this.mLocale.toString());
        map.put("version", String.valueOf(TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis())));
        return map;
    }

    private void removeBinaryDictionary() {
        asyncExecuteTaskWithWriteLock(new Runnable() { // from class: com.android.inputmethod.latin.ExpandableBinaryDictionary.3
            @Override // java.lang.Runnable
            public void run() {
                ExpandableBinaryDictionary.this.removeBinaryDictionaryLocked();
            }
        });
    }

    void removeBinaryDictionaryLocked() {
        closeBinaryDictionary();
        if (!this.mDictFile.exists() || FileUtils.deleteRecursively(this.mDictFile)) {
            return;
        }
        Log.e(TAG, "Can't remove a file: " + this.mDictFile.getName());
    }

    private void openBinaryDictionaryLocked() {
        this.mBinaryDictionary = new BinaryDictionary(this.mDictFile.getAbsolutePath(), 0L, this.mDictFile.length(), true, this.mLocale, this.mDictType, true);
    }

    void createOnMemoryBinaryDictionaryLocked() {
        this.mBinaryDictionary = new BinaryDictionary(this.mDictFile.getAbsolutePath(), true, this.mLocale, this.mDictType, 403L, getHeaderAttributeMap());
    }

    public void clear() {
        asyncExecuteTaskWithWriteLock(new Runnable() { // from class: com.android.inputmethod.latin.ExpandableBinaryDictionary.4
            @Override // java.lang.Runnable
            public void run() {
                ExpandableBinaryDictionary.this.removeBinaryDictionaryLocked();
                ExpandableBinaryDictionary.this.createOnMemoryBinaryDictionaryLocked();
            }
        });
    }

    public void runGCIfRequired(final boolean mindsBlockByGC) {
        asyncExecuteTaskWithWriteLock(new Runnable() { // from class: com.android.inputmethod.latin.ExpandableBinaryDictionary.5
            @Override // java.lang.Runnable
            public void run() {
                if (ExpandableBinaryDictionary.this.getBinaryDictionary() == null) {
                    return;
                }
                ExpandableBinaryDictionary.this.runGCIfRequiredLocked(mindsBlockByGC);
            }
        });
    }

    protected void runGCIfRequiredLocked(final boolean mindsBlockByGC) {
        if (this.mBinaryDictionary.needsToRunGC(mindsBlockByGC)) {
            this.mBinaryDictionary.flushWithGC();
        }
    }

    private void updateDictionaryWithWriteLock(@Nonnull final Runnable updateTask) {
        reloadDictionaryIfRequired();
        asyncExecuteTaskWithWriteLock(new Runnable() { // from class: com.android.inputmethod.latin.ExpandableBinaryDictionary.6
            @Override // java.lang.Runnable
            public void run() {
                if (ExpandableBinaryDictionary.this.getBinaryDictionary() == null) {
                    return;
                }
                ExpandableBinaryDictionary.this.runGCIfRequiredLocked(true);
                updateTask.run();
            }
        });
    }

    public void addUnigramEntry(final String word, final int frequency, final boolean isNotAWord, final boolean isPossiblyOffensive, final int timestamp) {
        updateDictionaryWithWriteLock(new Runnable() { // from class: com.android.inputmethod.latin.ExpandableBinaryDictionary.7
            @Override // java.lang.Runnable
            public void run() {
                ExpandableBinaryDictionary.this.addUnigramLocked(word, frequency, isNotAWord, isPossiblyOffensive, timestamp);
            }
        });
    }

    protected void addUnigramLocked(final String word, final int frequency, final boolean isNotAWord, final boolean isPossiblyOffensive, final int timestamp) {
        if (this.mBinaryDictionary.addUnigramEntry(word, frequency, false, isNotAWord, isPossiblyOffensive, timestamp)) {
            return;
        }
        Log.e(TAG, "Cannot add unigram entry. word: " + word);
    }

    public void removeUnigramEntryDynamically(final String word) {
        reloadDictionaryIfRequired();
        asyncExecuteTaskWithWriteLock(new Runnable() { // from class: com.android.inputmethod.latin.ExpandableBinaryDictionary.8
            @Override // java.lang.Runnable
            public void run() {
                BinaryDictionary binaryDictionary = ExpandableBinaryDictionary.this.getBinaryDictionary();
                if (binaryDictionary == null) {
                    return;
                }
                ExpandableBinaryDictionary.this.runGCIfRequiredLocked(true);
                binaryDictionary.removeUnigramEntry(word);
            }
        });
    }

    public void addNgramEntry(@Nonnull final NgramContext ngramContext, final String word, final int frequency, final int timestamp) {
        reloadDictionaryIfRequired();
        asyncExecuteTaskWithWriteLock(new Runnable() { // from class: com.android.inputmethod.latin.ExpandableBinaryDictionary.9
            @Override // java.lang.Runnable
            public void run() {
                if (ExpandableBinaryDictionary.this.getBinaryDictionary() == null) {
                    return;
                }
                ExpandableBinaryDictionary.this.runGCIfRequiredLocked(true);
                ExpandableBinaryDictionary.this.addNgramEntryLocked(ngramContext, word, frequency, timestamp);
            }
        });
    }

    protected void addNgramEntryLocked(@Nonnull final NgramContext ngramContext, final String word, final int frequency, final int timestamp) {
        this.mBinaryDictionary.addNgramEntry(ngramContext, word, frequency, timestamp);
    }

    public void updateEntriesForWord(@Nonnull final NgramContext ngramContext, final String word, final boolean isValidWord, final int count, final int timestamp) {
        updateDictionaryWithWriteLock(new Runnable() { // from class: com.android.inputmethod.latin.ExpandableBinaryDictionary.10
            @Override // java.lang.Runnable
            public void run() {
                BinaryDictionary binaryDictionary = ExpandableBinaryDictionary.this.getBinaryDictionary();
                if (binaryDictionary == null) {
                    return;
                }
                binaryDictionary.updateEntriesForWordWithNgramContext(ngramContext, word, isValidWord, count, timestamp);
            }
        });
    }

    public void updateEntriesForInputEvents(@Nonnull final ArrayList<WordInputEventForPersonalization> inputEvents, final UpdateEntriesForInputEventsCallback callback) {
        reloadDictionaryIfRequired();
        asyncExecuteTaskWithWriteLock(new Runnable() { // from class: com.android.inputmethod.latin.ExpandableBinaryDictionary.11
            @Override // java.lang.Runnable
            public void run() {
                UpdateEntriesForInputEventsCallback updateEntriesForInputEventsCallback;
                try {
                    BinaryDictionary binaryDictionary = ExpandableBinaryDictionary.this.getBinaryDictionary();
                    if (binaryDictionary == null) {
                        if (updateEntriesForInputEventsCallback != null) {
                            return;
                        } else {
                            return;
                        }
                    }
                    ArrayList arrayList = inputEvents;
                    binaryDictionary.updateEntriesForInputEvents((WordInputEventForPersonalization[]) arrayList.toArray(new WordInputEventForPersonalization[arrayList.size()]));
                    UpdateEntriesForInputEventsCallback updateEntriesForInputEventsCallback2 = callback;
                    if (updateEntriesForInputEventsCallback2 != null) {
                        updateEntriesForInputEventsCallback2.onFinished();
                    }
                } finally {
                    updateEntriesForInputEventsCallback = callback;
                    if (updateEntriesForInputEventsCallback != null) {
                        updateEntriesForInputEventsCallback.onFinished();
                    }
                }
            }
        });
    }

    /* JADX WARN: Removed duplicated region for block: B:24:0x007c  */
    @Override // com.android.inputmethod.latin.Dictionary
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.util.ArrayList<com.android.inputmethod.latin.SuggestedWords.SuggestedWordInfo> getSuggestions(final com.android.inputmethod.latin.common.ComposedData r14, final com.android.inputmethod.latin.NgramContext r15, final long r16, final com.android.inputmethod.latin.settings.SettingsValuesForSuggestion r18, final int r19, final float r20, final float[] r21) {
        /*
            r13 = this;
            r1 = r13
            r13.reloadDictionaryIfRequired()
            r2 = 0
            r3 = 0
            java.util.concurrent.locks.ReentrantReadWriteLock r0 = r1.mLock     // Catch: java.lang.Throwable -> L70 java.lang.InterruptedException -> L72
            java.util.concurrent.locks.ReentrantReadWriteLock$ReadLock r0 = r0.readLock()     // Catch: java.lang.Throwable -> L70 java.lang.InterruptedException -> L72
            r4 = 100
            java.util.concurrent.TimeUnit r6 = java.util.concurrent.TimeUnit.MILLISECONDS     // Catch: java.lang.Throwable -> L70 java.lang.InterruptedException -> L72
            boolean r3 = r0.tryLock(r4, r6)     // Catch: java.lang.Throwable -> L70 java.lang.InterruptedException -> L72
            if (r3 == 0) goto L6d
            com.android.inputmethod.latin.BinaryDictionary r4 = r1.mBinaryDictionary     // Catch: java.lang.Throwable -> L70 java.lang.InterruptedException -> L72
            if (r4 != 0) goto L26
            if (r3 == 0) goto L25
            java.util.concurrent.locks.ReentrantReadWriteLock r0 = r1.mLock
            java.util.concurrent.locks.ReentrantReadWriteLock$ReadLock r0 = r0.readLock()
            r0.unlock()
        L25:
            return r2
        L26:
            r5 = r14
            r6 = r15
            r7 = r16
            r9 = r18
            r10 = r19
            r11 = r20
            r12 = r21
            java.util.ArrayList r0 = r4.getSuggestions(r5, r6, r7, r9, r10, r11, r12)     // Catch: java.lang.Throwable -> L70 java.lang.InterruptedException -> L72
            com.android.inputmethod.latin.BinaryDictionary r4 = r1.mBinaryDictionary     // Catch: java.lang.Throwable -> L70 java.lang.InterruptedException -> L72
            boolean r4 = r4.isCorrupted()     // Catch: java.lang.Throwable -> L70 java.lang.InterruptedException -> L72
            if (r4 == 0) goto L61
            java.lang.String r4 = com.android.inputmethod.latin.ExpandableBinaryDictionary.TAG     // Catch: java.lang.Throwable -> L70 java.lang.InterruptedException -> L72
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L70 java.lang.InterruptedException -> L72
            r5.<init>()     // Catch: java.lang.Throwable -> L70 java.lang.InterruptedException -> L72
            java.lang.String r6 = "Dictionary ("
            java.lang.StringBuilder r5 = r5.append(r6)     // Catch: java.lang.Throwable -> L70 java.lang.InterruptedException -> L72
            java.lang.String r6 = r1.mDictName     // Catch: java.lang.Throwable -> L70 java.lang.InterruptedException -> L72
            java.lang.StringBuilder r5 = r5.append(r6)     // Catch: java.lang.Throwable -> L70 java.lang.InterruptedException -> L72
            java.lang.String r6 = ") is corrupted. Remove and regenerate it."
            java.lang.StringBuilder r5 = r5.append(r6)     // Catch: java.lang.Throwable -> L70 java.lang.InterruptedException -> L72
            java.lang.String r5 = r5.toString()     // Catch: java.lang.Throwable -> L70 java.lang.InterruptedException -> L72
            android.util.Log.i(r4, r5)     // Catch: java.lang.Throwable -> L70 java.lang.InterruptedException -> L72
            r13.removeBinaryDictionary()     // Catch: java.lang.Throwable -> L70 java.lang.InterruptedException -> L72
        L61:
            if (r3 == 0) goto L6c
            java.util.concurrent.locks.ReentrantReadWriteLock r2 = r1.mLock
            java.util.concurrent.locks.ReentrantReadWriteLock$ReadLock r2 = r2.readLock()
            r2.unlock()
        L6c:
            return r0
        L6d:
            if (r3 == 0) goto L85
            goto L7c
        L70:
            r0 = move-exception
            goto L86
        L72:
            r0 = move-exception
            java.lang.String r4 = com.android.inputmethod.latin.ExpandableBinaryDictionary.TAG     // Catch: java.lang.Throwable -> L70
            java.lang.String r5 = "Interrupted tryLock() in getSuggestionsWithSessionId()."
            android.util.Log.e(r4, r5, r0)     // Catch: java.lang.Throwable -> L70
            if (r3 == 0) goto L85
        L7c:
            java.util.concurrent.locks.ReentrantReadWriteLock r0 = r1.mLock
            java.util.concurrent.locks.ReentrantReadWriteLock$ReadLock r0 = r0.readLock()
            r0.unlock()
        L85:
            return r2
        L86:
            if (r3 == 0) goto L91
            java.util.concurrent.locks.ReentrantReadWriteLock r2 = r1.mLock
            java.util.concurrent.locks.ReentrantReadWriteLock$ReadLock r2 = r2.readLock()
            r2.unlock()
        L91:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.inputmethod.latin.ExpandableBinaryDictionary.getSuggestions(com.android.inputmethod.latin.common.ComposedData, com.android.inputmethod.latin.NgramContext, long, com.android.inputmethod.latin.settings.SettingsValuesForSuggestion, int, float, float[]):java.util.ArrayList");
    }

    /* JADX WARN: Not initialized variable reg: 1, insn: 0x0051: MOVE (r0 I:??[int, float, boolean, short, byte, char, OBJECT, ARRAY]) = (r1 I:??[int, float, boolean, short, byte, char, OBJECT, ARRAY]), block:B:27:0x0051 */
    /* JADX WARN: Removed duplicated region for block: B:24:0x0046  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x0054  */
    @Override // com.android.inputmethod.latin.Dictionary
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean isInDictionary(final java.lang.String r6) throws java.lang.Throwable {
        /*
            r5 = this;
            r5.reloadDictionaryIfRequired()
            r0 = 0
            java.util.concurrent.locks.ReentrantReadWriteLock r1 = r5.mLock     // Catch: java.lang.Throwable -> L39 java.lang.InterruptedException -> L3b
            java.util.concurrent.locks.ReentrantReadWriteLock$ReadLock r1 = r1.readLock()     // Catch: java.lang.Throwable -> L39 java.lang.InterruptedException -> L3b
            r2 = 100
            java.util.concurrent.TimeUnit r4 = java.util.concurrent.TimeUnit.MILLISECONDS     // Catch: java.lang.Throwable -> L39 java.lang.InterruptedException -> L3b
            boolean r1 = r1.tryLock(r2, r4)     // Catch: java.lang.Throwable -> L39 java.lang.InterruptedException -> L3b
            if (r1 == 0) goto L36
            com.android.inputmethod.latin.BinaryDictionary r2 = r5.mBinaryDictionary     // Catch: java.lang.InterruptedException -> L34 java.lang.Throwable -> L50
            if (r2 != 0) goto L24
            if (r1 == 0) goto L23
            java.util.concurrent.locks.ReentrantReadWriteLock r6 = r5.mLock
            java.util.concurrent.locks.ReentrantReadWriteLock$ReadLock r6 = r6.readLock()
            r6.unlock()
        L23:
            return r0
        L24:
            boolean r6 = r5.isInDictionaryLocked(r6)     // Catch: java.lang.InterruptedException -> L34 java.lang.Throwable -> L50
            if (r1 == 0) goto L33
            java.util.concurrent.locks.ReentrantReadWriteLock r0 = r5.mLock
            java.util.concurrent.locks.ReentrantReadWriteLock$ReadLock r0 = r0.readLock()
            r0.unlock()
        L33:
            return r6
        L34:
            r6 = move-exception
            goto L3d
        L36:
            if (r1 == 0) goto L4f
            goto L46
        L39:
            r6 = move-exception
            goto L52
        L3b:
            r6 = move-exception
            r1 = r0
        L3d:
            java.lang.String r2 = com.android.inputmethod.latin.ExpandableBinaryDictionary.TAG     // Catch: java.lang.Throwable -> L50
            java.lang.String r3 = "Interrupted tryLock() in isInDictionary()."
            android.util.Log.e(r2, r3, r6)     // Catch: java.lang.Throwable -> L50
            if (r1 == 0) goto L4f
        L46:
            java.util.concurrent.locks.ReentrantReadWriteLock r6 = r5.mLock
            java.util.concurrent.locks.ReentrantReadWriteLock$ReadLock r6 = r6.readLock()
            r6.unlock()
        L4f:
            return r0
        L50:
            r6 = move-exception
            r0 = r1
        L52:
            if (r0 == 0) goto L5d
            java.util.concurrent.locks.ReentrantReadWriteLock r0 = r5.mLock
            java.util.concurrent.locks.ReentrantReadWriteLock$ReadLock r0 = r0.readLock()
            r0.unlock()
        L5d:
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.inputmethod.latin.ExpandableBinaryDictionary.isInDictionary(java.lang.String):boolean");
    }

    protected boolean isInDictionaryLocked(final String word) {
        BinaryDictionary binaryDictionary = this.mBinaryDictionary;
        if (binaryDictionary == null) {
            return false;
        }
        return binaryDictionary.isInDictionary(word);
    }

    /* JADX WARN: Removed duplicated region for block: B:21:0x0044  */
    @Override // com.android.inputmethod.latin.Dictionary
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public int getMaxFrequencyOfExactMatches(final java.lang.String r7) {
        /*
            r6 = this;
            r6.reloadDictionaryIfRequired()
            r0 = -1
            r1 = 0
            java.util.concurrent.locks.ReentrantReadWriteLock r2 = r6.mLock     // Catch: java.lang.Throwable -> L38 java.lang.InterruptedException -> L3a
            java.util.concurrent.locks.ReentrantReadWriteLock$ReadLock r2 = r2.readLock()     // Catch: java.lang.Throwable -> L38 java.lang.InterruptedException -> L3a
            r3 = 100
            java.util.concurrent.TimeUnit r5 = java.util.concurrent.TimeUnit.MILLISECONDS     // Catch: java.lang.Throwable -> L38 java.lang.InterruptedException -> L3a
            boolean r1 = r2.tryLock(r3, r5)     // Catch: java.lang.Throwable -> L38 java.lang.InterruptedException -> L3a
            if (r1 == 0) goto L35
            com.android.inputmethod.latin.BinaryDictionary r2 = r6.mBinaryDictionary     // Catch: java.lang.Throwable -> L38 java.lang.InterruptedException -> L3a
            if (r2 != 0) goto L25
            if (r1 == 0) goto L24
            java.util.concurrent.locks.ReentrantReadWriteLock r7 = r6.mLock
            java.util.concurrent.locks.ReentrantReadWriteLock$ReadLock r7 = r7.readLock()
            r7.unlock()
        L24:
            return r0
        L25:
            int r7 = r2.getMaxFrequencyOfExactMatches(r7)     // Catch: java.lang.Throwable -> L38 java.lang.InterruptedException -> L3a
            if (r1 == 0) goto L34
            java.util.concurrent.locks.ReentrantReadWriteLock r0 = r6.mLock
            java.util.concurrent.locks.ReentrantReadWriteLock$ReadLock r0 = r0.readLock()
            r0.unlock()
        L34:
            return r7
        L35:
            if (r1 == 0) goto L4d
            goto L44
        L38:
            r7 = move-exception
            goto L4e
        L3a:
            r7 = move-exception
            java.lang.String r2 = com.android.inputmethod.latin.ExpandableBinaryDictionary.TAG     // Catch: java.lang.Throwable -> L38
            java.lang.String r3 = "Interrupted tryLock() in getMaxFrequencyOfExactMatches()."
            android.util.Log.e(r2, r3, r7)     // Catch: java.lang.Throwable -> L38
            if (r1 == 0) goto L4d
        L44:
            java.util.concurrent.locks.ReentrantReadWriteLock r7 = r6.mLock
            java.util.concurrent.locks.ReentrantReadWriteLock$ReadLock r7 = r7.readLock()
            r7.unlock()
        L4d:
            return r0
        L4e:
            if (r1 == 0) goto L59
            java.util.concurrent.locks.ReentrantReadWriteLock r0 = r6.mLock
            java.util.concurrent.locks.ReentrantReadWriteLock$ReadLock r0 = r0.readLock()
            r0.unlock()
        L59:
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.inputmethod.latin.ExpandableBinaryDictionary.getMaxFrequencyOfExactMatches(java.lang.String):int");
    }

    void loadBinaryDictionaryLocked() {
        BinaryDictionary binaryDictionary = this.mBinaryDictionary;
        openBinaryDictionaryLocked();
        if (binaryDictionary != null) {
            binaryDictionary.close();
        }
        if (this.mBinaryDictionary.isValidDictionary() && needsToMigrateDictionary(this.mBinaryDictionary.getFormatVersion()) && !this.mBinaryDictionary.migrateTo(403)) {
            Log.e(TAG, "Dictionary migration failed: " + this.mDictName);
            removeBinaryDictionaryLocked();
        }
    }

    void createNewDictionaryLocked() {
        removeBinaryDictionaryLocked();
        createOnMemoryBinaryDictionaryLocked();
        loadInitialContentsLocked();
        this.mBinaryDictionary.flushWithGCIfHasUpdated();
    }

    protected void setNeedsToRecreate() {
        this.mNeedsToRecreate = true;
    }

    void clearNeedsToRecreate() {
        this.mNeedsToRecreate = false;
    }

    boolean isNeededToRecreate() {
        return this.mNeedsToRecreate;
    }

    public final void reloadDictionaryIfRequired() {
        if (isReloadRequired()) {
            asyncReloadDictionary();
        }
    }

    private boolean isReloadRequired() {
        return this.mBinaryDictionary == null || this.mNeedsToRecreate;
    }

    private void asyncReloadDictionary() {
        final AtomicBoolean atomicBoolean = this.mIsReloading;
        if (atomicBoolean.compareAndSet(false, true)) {
            final File file = this.mDictFile;
            asyncExecuteTaskWithWriteLock(new Runnable() { // from class: com.android.inputmethod.latin.ExpandableBinaryDictionary.12
                @Override // java.lang.Runnable
                public void run() {
                    try {
                        if (!file.exists() || ExpandableBinaryDictionary.this.isNeededToRecreate()) {
                            ExpandableBinaryDictionary.this.createNewDictionaryLocked();
                        } else if (ExpandableBinaryDictionary.this.getBinaryDictionary() == null) {
                            ExpandableBinaryDictionary.this.loadBinaryDictionaryLocked();
                            BinaryDictionary binaryDictionary = ExpandableBinaryDictionary.this.getBinaryDictionary();
                            if (binaryDictionary != null && (!ExpandableBinaryDictionary.this.isValidDictionaryLocked() || !ExpandableBinaryDictionary.matchesExpectedBinaryDictFormatVersionForThisType(binaryDictionary.getFormatVersion()))) {
                                ExpandableBinaryDictionary.this.createNewDictionaryLocked();
                            }
                        }
                        ExpandableBinaryDictionary.this.clearNeedsToRecreate();
                    } finally {
                        atomicBoolean.set(false);
                    }
                }
            });
        }
    }

    public void asyncFlushBinaryDictionary() {
        asyncExecuteTaskWithWriteLock(new Runnable() { // from class: com.android.inputmethod.latin.ExpandableBinaryDictionary.13
            @Override // java.lang.Runnable
            public void run() {
                BinaryDictionary binaryDictionary = ExpandableBinaryDictionary.this.getBinaryDictionary();
                if (binaryDictionary == null) {
                    return;
                }
                if (binaryDictionary.needsToRunGC(false)) {
                    binaryDictionary.flushWithGC();
                } else {
                    binaryDictionary.flush();
                }
            }
        });
    }

    public DictionaryStats getDictionaryStats() {
        reloadDictionaryIfRequired();
        final String str = this.mDictName;
        final File file = this.mDictFile;
        final AsyncResultHolder asyncResultHolder = new AsyncResultHolder("DictionaryStats");
        asyncExecuteTaskWithLock(this.mLock.readLock(), new Runnable() { // from class: com.android.inputmethod.latin.ExpandableBinaryDictionary.14
            @Override // java.lang.Runnable
            public void run() {
                AsyncResultHolder asyncResultHolder2 = asyncResultHolder;
                Locale locale = ExpandableBinaryDictionary.this.mLocale;
                String str2 = str;
                asyncResultHolder2.set(new DictionaryStats(locale, str2, str2, file, 0));
            }
        });
        return (DictionaryStats) asyncResultHolder.get(null, 100L);
    }

    public void waitAllTasksForTests() {
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        asyncExecuteTaskWithWriteLock(new Runnable() { // from class: com.android.inputmethod.latin.ExpandableBinaryDictionary.15
            @Override // java.lang.Runnable
            public void run() {
                countDownLatch.countDown();
            }
        });
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            Log.e(TAG, "Interrupted while waiting for finishing dictionary operations.", e);
        }
    }

    public void clearAndFlushDictionaryWithAdditionalAttributes(final Map<String, String> attributeMap) {
        this.mAdditionalAttributeMap = attributeMap;
        clear();
    }

    public void dumpAllWordsForDebug() {
        reloadDictionaryIfRequired();
        final String str = TAG;
        final String str2 = this.mDictName;
        asyncExecuteTaskWithLock(this.mLock.readLock(), new Runnable() { // from class: com.android.inputmethod.latin.ExpandableBinaryDictionary.16
            @Override // java.lang.Runnable
            public void run() {
                Log.d(str, "Dump dictionary: " + str2 + " for " + ExpandableBinaryDictionary.this.mLocale);
                BinaryDictionary binaryDictionary = ExpandableBinaryDictionary.this.getBinaryDictionary();
                if (binaryDictionary == null) {
                    return;
                }
                try {
                    DictionaryHeader header = binaryDictionary.getHeader();
                    Log.d(str, "Format version: " + binaryDictionary.getFormatVersion());
                    Log.d(str, CombinedFormatUtils.formatAttributeMap(header.mDictionaryOptions.mAttributes));
                } catch (UnsupportedFormatException e) {
                    Log.d(str, "Cannot fetch header information.", e);
                }
                int i = 0;
                do {
                    BinaryDictionary.GetNextWordPropertyResult nextWordProperty = binaryDictionary.getNextWordProperty(i);
                    WordProperty wordProperty = nextWordProperty.mWordProperty;
                    if (wordProperty == null) {
                        Log.d(str, " dictionary is empty.");
                        return;
                    } else {
                        Log.d(str, wordProperty.toString());
                        i = nextWordProperty.mNextToken;
                    }
                } while (i != 0);
            }
        });
    }

    public WordProperty[] getWordPropertiesForSyncing() {
        reloadDictionaryIfRequired();
        final AsyncResultHolder asyncResultHolder = new AsyncResultHolder("WordPropertiesForSync");
        asyncExecuteTaskWithLock(this.mLock.readLock(), new Runnable() { // from class: com.android.inputmethod.latin.ExpandableBinaryDictionary.17
            @Override // java.lang.Runnable
            public void run() {
                ArrayList arrayList = new ArrayList();
                BinaryDictionary binaryDictionary = ExpandableBinaryDictionary.this.getBinaryDictionary();
                if (binaryDictionary == null) {
                    return;
                }
                int i = 0;
                do {
                    BinaryDictionary.GetNextWordPropertyResult nextWordProperty = binaryDictionary.getNextWordProperty(i);
                    WordProperty wordProperty = nextWordProperty.mWordProperty;
                    if (wordProperty == null) {
                        break;
                    }
                    arrayList.add(wordProperty);
                    i = nextWordProperty.mNextToken;
                } while (i != 0);
                asyncResultHolder.set((WordProperty[]) arrayList.toArray(new WordProperty[arrayList.size()]));
            }
        });
        return (WordProperty[]) asyncResultHolder.get(DEFAULT_WORD_PROPERTIES_FOR_SYNC, 100L);
    }
}
