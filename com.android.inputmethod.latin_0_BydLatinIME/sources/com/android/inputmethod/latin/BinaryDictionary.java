package com.android.inputmethod.latin;

import android.text.TextUtils;
import android.util.Log;
import android.util.SparseArray;
import com.android.inputmethod.latin.SuggestedWords;
import com.android.inputmethod.latin.common.ComposedData;
import com.android.inputmethod.latin.common.FileUtils;
import com.android.inputmethod.latin.common.InputPointers;
import com.android.inputmethod.latin.common.StringUtils;
import com.android.inputmethod.latin.makedict.DictionaryHeader;
import com.android.inputmethod.latin.makedict.FormatSpec;
import com.android.inputmethod.latin.makedict.UnsupportedFormatException;
import com.android.inputmethod.latin.makedict.WordProperty;
import com.android.inputmethod.latin.settings.SettingsValuesForSuggestion;
import com.android.inputmethod.latin.utils.BinaryDictionaryUtils;
import com.android.inputmethod.latin.utils.JniUtils;
import com.android.inputmethod.latin.utils.WordInputEventForPersonalization;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public final class BinaryDictionary extends Dictionary {
    public static final String BIGRAM_COUNT_QUERY = "BIGRAM_COUNT";
    private static final int CONFIDENCE_TO_AUTO_COMMIT = 1000000;
    public static final int DICTIONARY_MAX_WORD_LENGTH = 48;
    public static final String DICT_FILE_NAME_SUFFIX_FOR_MIGRATION = ".migrate";
    public static final String DIR_NAME_SUFFIX_FOR_RECORD_MIGRATION = ".migrating";
    public static final int FORMAT_WORD_PROPERTY_COUNT_INDEX = 3;
    private static final int FORMAT_WORD_PROPERTY_HAS_NGRAMS_INDEX = 2;
    private static final int FORMAT_WORD_PROPERTY_HAS_SHORTCUTS_INDEX = 3;
    private static final int FORMAT_WORD_PROPERTY_IS_BEGINNING_OF_SENTENCE_INDEX = 4;
    private static final int FORMAT_WORD_PROPERTY_IS_NOT_A_WORD_INDEX = 0;
    private static final int FORMAT_WORD_PROPERTY_IS_POSSIBLY_OFFENSIVE_INDEX = 1;
    public static final int FORMAT_WORD_PROPERTY_LEVEL_INDEX = 2;
    private static final int FORMAT_WORD_PROPERTY_OUTPUT_FLAG_COUNT = 5;
    public static final int FORMAT_WORD_PROPERTY_OUTPUT_PROBABILITY_INFO_COUNT = 4;
    public static final int FORMAT_WORD_PROPERTY_PROBABILITY_INDEX = 0;
    public static final int FORMAT_WORD_PROPERTY_TIMESTAMP_INDEX = 1;
    public static final String MAX_BIGRAM_COUNT_QUERY = "MAX_BIGRAM_COUNT";
    public static final int MAX_PREV_WORD_COUNT_FOR_N_GRAM = 3;
    public static final String MAX_UNIGRAM_COUNT_QUERY = "MAX_UNIGRAM_COUNT";
    public static final int NOT_A_VALID_TIMESTAMP = -1;
    private static final String TAG = "BinaryDictionary";
    public static final String UNIGRAM_COUNT_QUERY = "UNIGRAM_COUNT";
    private final SparseArray<DicTraverseSession> mDicTraverseSessions;
    private final String mDictFilePath;
    private final long mDictSize;
    private boolean mHasUpdated;
    private final boolean mIsUpdatable;
    private long mNativeDict;
    private final boolean mUseFullEditDistance;

    private static native boolean addNgramEntryNative(long dict, int[][] prevWordCodePointArrays, boolean[] isBeginningOfSentenceArray, int[] word, int probability, int timestamp);

    private static native boolean addUnigramEntryNative(long dict, int[] word, int probability, int[] shortcutTarget, int shortcutProbability, boolean isBeginningOfSentence, boolean isNotAWord, boolean isPossiblyOffensive, int timestamp);

    private static native void closeNative(long dict);

    private static native long createOnMemoryNative(long formatVersion, String locale, String[] attributeKeyStringArray, String[] attributeValueStringArray);

    private static native boolean flushNative(long dict, String filePath);

    private static native boolean flushWithGCNative(long dict, String filePath);

    private static native int getFormatVersionNative(long dict);

    private static native void getHeaderInfoNative(long dict, int[] outHeaderSize, int[] outFormatVersion, ArrayList<int[]> outAttributeKeys, ArrayList<int[]> outAttributeValues);

    private static native int getMaxProbabilityOfExactMatchesNative(long dict, int[] word);

    private static native int getNextWordNative(long dict, int token, int[] outCodePoints, boolean[] outIsBeginningOfSentence);

    private static native int getNgramProbabilityNative(long dict, int[][] prevWordCodePointArrays, boolean[] isBeginningOfSentenceArray, int[] word);

    private static native int getProbabilityNative(long dict, int[] word);

    private static native String getPropertyNative(long dict, String query);

    private static native void getSuggestionsNative(long dict, long proximityInfo, long traverseSession, int[] xCoordinates, int[] yCoordinates, int[] times, int[] pointerIds, int[] inputCodePoints, int inputSize, int[] suggestOptions, int[][] prevWordCodePointArrays, boolean[] isBeginningOfSentenceArray, int prevWordCount, int[] outputSuggestionCount, int[] outputCodePoints, int[] outputScores, int[] outputIndices, int[] outputTypes, int[] outputAutoCommitFirstWordConfidence, float[] inOutWeightOfLangModelVsSpatialModel);

    private static native void getWordPropertyNative(long dict, int[] word, boolean isBeginningOfSentence, int[] outCodePoints, boolean[] outFlags, int[] outProbabilityInfo, ArrayList<int[][]> outNgramPrevWordsArray, ArrayList<boolean[]> outNgramPrevWordIsBeginningOfSentenceArray, ArrayList<int[]> outNgramTargets, ArrayList<int[]> outNgramProbabilityInfo, ArrayList<int[]> outShortcutTargets, ArrayList<Integer> outShortcutProbabilities);

    private static native boolean isCorruptedNative(long dict);

    private static native boolean migrateNative(long dict, String dictFilePath, long newFormatVersion);

    private static native boolean needsToRunGCNative(long dict, boolean mindsBlockByGC);

    private static native long openNative(String sourceDir, long dictOffset, long dictSize, boolean isUpdatable);

    private static native boolean removeNgramEntryNative(long dict, int[][] prevWordCodePointArrays, boolean[] isBeginningOfSentenceArray, int[] word);

    private static native boolean removeUnigramEntryNative(long dict, int[] word);

    private static native int updateEntriesForInputEventsNative(long dict, WordInputEventForPersonalization[] inputEvents, int startIndex);

    private static native boolean updateEntriesForWordWithNgramContextNative(long dict, int[][] prevWordCodePointArrays, boolean[] isBeginningOfSentenceArray, int[] word, boolean isValidWord, int count, int timestamp);

    private DicTraverseSession getTraverseSession(final int traverseSessionId) {
        DicTraverseSession dicTraverseSession;
        synchronized (this.mDicTraverseSessions) {
            dicTraverseSession = this.mDicTraverseSessions.get(traverseSessionId);
            if (dicTraverseSession == null) {
                dicTraverseSession = new DicTraverseSession(this.mLocale, this.mNativeDict, this.mDictSize);
                this.mDicTraverseSessions.put(traverseSessionId, dicTraverseSession);
            }
        }
        return dicTraverseSession;
    }

    public BinaryDictionary(final String filename, final long offset, final long length, final boolean useFullEditDistance, final Locale locale, final String dictType, final boolean isUpdatable) {
        super(dictType, locale);
        this.mDicTraverseSessions = new SparseArray<>();
        this.mDictSize = length;
        this.mDictFilePath = filename;
        this.mIsUpdatable = isUpdatable;
        this.mHasUpdated = false;
        this.mUseFullEditDistance = useFullEditDistance;
        loadDictionary(filename, offset, length, isUpdatable);
    }

    public BinaryDictionary(final String filename, final boolean useFullEditDistance, final Locale locale, final String dictType, final long formatVersion, final Map<String, String> attributeMap) {
        super(dictType, locale);
        this.mDicTraverseSessions = new SparseArray<>();
        this.mDictSize = 0L;
        this.mDictFilePath = filename;
        this.mIsUpdatable = true;
        int i = 0;
        this.mHasUpdated = false;
        this.mUseFullEditDistance = useFullEditDistance;
        String[] strArr = new String[attributeMap.size()];
        String[] strArr2 = new String[attributeMap.size()];
        for (String str : attributeMap.keySet()) {
            strArr[i] = str;
            strArr2[i] = attributeMap.get(str);
            i++;
        }
        this.mNativeDict = createOnMemoryNative(formatVersion, locale.toString(), strArr, strArr2);
    }

    static {
        JniUtils.loadNativeLibrary();
    }

    private void loadDictionary(final String path, final long startOffset, final long length, final boolean isUpdatable) {
        this.mHasUpdated = false;
        this.mNativeDict = openNative(path, startOffset, length, isUpdatable);
    }

    public boolean isCorrupted() {
        if (!isValidDictionary() || !isCorruptedNative(this.mNativeDict)) {
            return false;
        }
        String str = TAG;
        Log.e(str, "BinaryDictionary (" + this.mDictFilePath + ") is corrupted.");
        Log.e(str, "locale: " + this.mLocale);
        Log.e(str, "dict size: " + this.mDictSize);
        Log.e(str, "updatable: " + this.mIsUpdatable);
        return true;
    }

    public DictionaryHeader getHeader() throws UnsupportedFormatException {
        if (this.mNativeDict == 0) {
            return null;
        }
        int[] iArr = new int[1];
        int[] iArr2 = new int[1];
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        getHeaderInfoNative(this.mNativeDict, iArr, iArr2, arrayList, arrayList2);
        HashMap map = new HashMap();
        for (int i = 0; i < arrayList.size(); i++) {
            map.put(StringUtils.getStringFromNullTerminatedCodePointArray((int[]) arrayList.get(i)), StringUtils.getStringFromNullTerminatedCodePointArray((int[]) arrayList2.get(i)));
        }
        return new DictionaryHeader(iArr[0], new FormatSpec.DictionaryOptions(map), new FormatSpec.FormatOptions(iArr2[0], DictionaryHeader.ATTRIBUTE_VALUE_TRUE.equals(map.get(DictionaryHeader.HAS_HISTORICAL_INFO_KEY))));
    }

    @Override // com.android.inputmethod.latin.Dictionary
    public ArrayList<SuggestedWords.SuggestedWordInfo> getSuggestions(final ComposedData composedData, final NgramContext ngramContext, final long proximityInfoHandle, final SettingsValuesForSuggestion settingsValuesForSuggestion, final int sessionId, final float weightForLocale, final float[] inOutWeightOfLangModelVsSpatialModel) {
        int pointerSize;
        if (!isValidDictionary()) {
            return null;
        }
        DicTraverseSession traverseSession = getTraverseSession(sessionId);
        Arrays.fill(traverseSession.mInputCodePoints, -1);
        ngramContext.outputToArray(traverseSession.mPrevWordCodePointArrays, traverseSession.mIsBeginningOfSentenceArray);
        InputPointers inputPointers = composedData.mInputPointers;
        boolean z = composedData.mIsBatchMode;
        if (!z) {
            pointerSize = composedData.copyCodePointsExceptTrailingSingleQuotesAndReturnCodePointCount(traverseSession.mInputCodePoints);
            if (pointerSize < 0) {
                return null;
            }
        } else {
            pointerSize = inputPointers.getPointerSize();
        }
        int i = pointerSize;
        traverseSession.mNativeSuggestOptions.setUseFullEditDistance(this.mUseFullEditDistance);
        traverseSession.mNativeSuggestOptions.setIsGesture(z);
        traverseSession.mNativeSuggestOptions.setBlockOffensiveWords(settingsValuesForSuggestion.mBlockPotentiallyOffensive);
        traverseSession.mNativeSuggestOptions.setWeightForLocale(weightForLocale);
        if (inOutWeightOfLangModelVsSpatialModel != null) {
            traverseSession.mInputOutputWeightOfLangModelVsSpatialModel[0] = inOutWeightOfLangModelVsSpatialModel[0];
        } else {
            traverseSession.mInputOutputWeightOfLangModelVsSpatialModel[0] = -1.0f;
        }
        getSuggestionsNative(this.mNativeDict, proximityInfoHandle, getTraverseSession(sessionId).getSession(), inputPointers.getXCoordinates(), inputPointers.getYCoordinates(), inputPointers.getTimes(), inputPointers.getPointerIds(), traverseSession.mInputCodePoints, i, traverseSession.mNativeSuggestOptions.getOptions(), traverseSession.mPrevWordCodePointArrays, traverseSession.mIsBeginningOfSentenceArray, ngramContext.getPrevWordCount(), traverseSession.mOutputSuggestionCount, traverseSession.mOutputCodePoints, traverseSession.mOutputScores, traverseSession.mSpaceIndices, traverseSession.mOutputTypes, traverseSession.mOutputAutoCommitFirstWordConfidence, traverseSession.mInputOutputWeightOfLangModelVsSpatialModel);
        if (inOutWeightOfLangModelVsSpatialModel != null) {
            inOutWeightOfLangModelVsSpatialModel[0] = traverseSession.mInputOutputWeightOfLangModelVsSpatialModel[0];
        }
        int i2 = traverseSession.mOutputSuggestionCount[0];
        ArrayList<SuggestedWords.SuggestedWordInfo> arrayList = new ArrayList<>();
        for (int i3 = 0; i3 < i2; i3++) {
            int i4 = i3 * 48;
            int i5 = 0;
            while (i5 < 48 && traverseSession.mOutputCodePoints[i4 + i5] != 0) {
                i5++;
            }
            if (i5 > 0) {
                arrayList.add(new SuggestedWords.SuggestedWordInfo(new String(traverseSession.mOutputCodePoints, i4, i5), "", (int) (traverseSession.mOutputScores[i3] * weightForLocale), traverseSession.mOutputTypes[i3], this, traverseSession.mSpaceIndices[i3], traverseSession.mOutputAutoCommitFirstWordConfidence[0]));
            }
        }
        return arrayList;
    }

    public boolean isValidDictionary() {
        return this.mNativeDict != 0;
    }

    public int getFormatVersion() {
        return getFormatVersionNative(this.mNativeDict);
    }

    @Override // com.android.inputmethod.latin.Dictionary
    public boolean isInDictionary(final String word) {
        return getFrequency(word) != -1;
    }

    @Override // com.android.inputmethod.latin.Dictionary
    public int getFrequency(final String word) {
        if (TextUtils.isEmpty(word)) {
            return -1;
        }
        return getProbabilityNative(this.mNativeDict, StringUtils.toCodePointArray(word));
    }

    @Override // com.android.inputmethod.latin.Dictionary
    public int getMaxFrequencyOfExactMatches(final String word) {
        if (TextUtils.isEmpty(word)) {
            return -1;
        }
        return getMaxProbabilityOfExactMatchesNative(this.mNativeDict, StringUtils.toCodePointArray(word));
    }

    public boolean isValidNgram(final NgramContext ngramContext, final String word) {
        return getNgramProbability(ngramContext, word) != -1;
    }

    public int getNgramProbability(final NgramContext ngramContext, final String word) {
        if (!ngramContext.isValid() || TextUtils.isEmpty(word)) {
            return -1;
        }
        int[][] iArr = new int[ngramContext.getPrevWordCount()][];
        boolean[] zArr = new boolean[ngramContext.getPrevWordCount()];
        ngramContext.outputToArray(iArr, zArr);
        return getNgramProbabilityNative(this.mNativeDict, iArr, zArr, StringUtils.toCodePointArray(word));
    }

    public WordProperty getWordProperty(final String word, final boolean isBeginningOfSentence) {
        if (word == null) {
            return null;
        }
        int[] codePointArray = StringUtils.toCodePointArray(word);
        boolean[] zArr = new boolean[5];
        int[] iArr = new int[4];
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        ArrayList arrayList3 = new ArrayList();
        ArrayList arrayList4 = new ArrayList();
        ArrayList arrayList5 = new ArrayList();
        ArrayList arrayList6 = new ArrayList();
        getWordPropertyNative(this.mNativeDict, codePointArray, isBeginningOfSentence, new int[48], zArr, iArr, arrayList, arrayList2, arrayList3, arrayList4, arrayList5, arrayList6);
        return new WordProperty(codePointArray, zArr[0], zArr[1], zArr[2], zArr[4], iArr, arrayList, arrayList2, arrayList3, arrayList4);
    }

    public static class GetNextWordPropertyResult {
        public int mNextToken;
        public WordProperty mWordProperty;

        public GetNextWordPropertyResult(final WordProperty wordProperty, final int nextToken) {
            this.mWordProperty = wordProperty;
            this.mNextToken = nextToken;
        }
    }

    public GetNextWordPropertyResult getNextWordProperty(final int token) {
        int[] iArr = new int[48];
        boolean[] zArr = new boolean[1];
        return new GetNextWordPropertyResult(getWordProperty(StringUtils.getStringFromNullTerminatedCodePointArray(iArr), zArr[0]), getNextWordNative(this.mNativeDict, token, iArr, zArr));
    }

    public boolean addUnigramEntry(final String word, final int probability, final boolean isBeginningOfSentence, final boolean isNotAWord, final boolean isPossiblyOffensive, final int timestamp) {
        if (word == null) {
            return false;
        }
        if (word.isEmpty() && !isBeginningOfSentence) {
            return false;
        }
        if (!addUnigramEntryNative(this.mNativeDict, StringUtils.toCodePointArray(word), probability, null, 0, isBeginningOfSentence, isNotAWord, isPossiblyOffensive, timestamp)) {
            return false;
        }
        this.mHasUpdated = true;
        return true;
    }

    public boolean removeUnigramEntry(final String word) {
        if (TextUtils.isEmpty(word)) {
            return false;
        }
        if (!removeUnigramEntryNative(this.mNativeDict, StringUtils.toCodePointArray(word))) {
            return false;
        }
        this.mHasUpdated = true;
        return true;
    }

    public boolean addNgramEntry(final NgramContext ngramContext, final String word, final int probability, final int timestamp) {
        if (!ngramContext.isValid() || TextUtils.isEmpty(word)) {
            return false;
        }
        int[][] iArr = new int[ngramContext.getPrevWordCount()][];
        boolean[] zArr = new boolean[ngramContext.getPrevWordCount()];
        ngramContext.outputToArray(iArr, zArr);
        if (!addNgramEntryNative(this.mNativeDict, iArr, zArr, StringUtils.toCodePointArray(word), probability, timestamp)) {
            return false;
        }
        this.mHasUpdated = true;
        return true;
    }

    public boolean updateEntriesForWordWithNgramContext(@Nonnull final NgramContext ngramContext, final String word, final boolean isValidWord, final int count, final int timestamp) {
        if (TextUtils.isEmpty(word)) {
            return false;
        }
        int[][] iArr = new int[ngramContext.getPrevWordCount()][];
        boolean[] zArr = new boolean[ngramContext.getPrevWordCount()];
        ngramContext.outputToArray(iArr, zArr);
        if (!updateEntriesForWordWithNgramContextNative(this.mNativeDict, iArr, zArr, StringUtils.toCodePointArray(word), isValidWord, count, timestamp)) {
            return false;
        }
        this.mHasUpdated = true;
        return true;
    }

    public void updateEntriesForInputEvents(final WordInputEventForPersonalization[] inputEvents) {
        if (isValidDictionary()) {
            int iUpdateEntriesForInputEventsNative = 0;
            while (iUpdateEntriesForInputEventsNative < inputEvents.length) {
                if (needsToRunGC(true)) {
                    flushWithGC();
                }
                iUpdateEntriesForInputEventsNative = updateEntriesForInputEventsNative(this.mNativeDict, inputEvents, iUpdateEntriesForInputEventsNative);
                this.mHasUpdated = true;
                if (iUpdateEntriesForInputEventsNative <= 0) {
                    return;
                }
            }
        }
    }

    private void reopen() {
        close();
        File file = new File(this.mDictFilePath);
        loadDictionary(file.getAbsolutePath(), 0L, file.length(), this.mIsUpdatable);
    }

    public boolean flush() {
        if (!isValidDictionary()) {
            return false;
        }
        if (!this.mHasUpdated) {
            return true;
        }
        if (!flushNative(this.mNativeDict, this.mDictFilePath)) {
            return false;
        }
        reopen();
        return true;
    }

    public boolean flushWithGCIfHasUpdated() {
        if (this.mHasUpdated) {
            return flushWithGC();
        }
        return true;
    }

    public boolean flushWithGC() {
        if (!isValidDictionary() || !flushWithGCNative(this.mNativeDict, this.mDictFilePath)) {
            return false;
        }
        reopen();
        return true;
    }

    public boolean needsToRunGC(final boolean mindsBlockByGC) {
        if (isValidDictionary()) {
            return needsToRunGCNative(this.mNativeDict, mindsBlockByGC);
        }
        return false;
    }

    public boolean migrateTo(final int newFormatVersion) {
        if (!isValidDictionary()) {
            return false;
        }
        File file = new File(this.mDictFilePath + DIR_NAME_SUFFIX_FOR_RECORD_MIGRATION);
        if (file.exists()) {
            file.delete();
            Log.e(TAG, "Previous migration attempt failed probably due to a crash. Giving up using the old dictionary (" + this.mDictFilePath + ").");
            return false;
        }
        if (!file.mkdir()) {
            Log.e(TAG, "Cannot create a dir (" + file.getAbsolutePath() + ") to record migration.");
            return false;
        }
        try {
            String str = this.mDictFilePath + DICT_FILE_NAME_SUFFIX_FOR_MIGRATION;
            if (!migrateNative(this.mNativeDict, str, newFormatVersion)) {
                return false;
            }
            close();
            File file2 = new File(this.mDictFilePath);
            File file3 = new File(str);
            if (!FileUtils.deleteRecursively(file2)) {
                return false;
            }
            if (!BinaryDictionaryUtils.renameDict(file3, file2)) {
                return false;
            }
            loadDictionary(file2.getAbsolutePath(), 0L, file2.length(), this.mIsUpdatable);
            return true;
        } finally {
            file.delete();
        }
    }

    public String getPropertyForGettingStats(final String query) {
        return !isValidDictionary() ? "" : getPropertyNative(this.mNativeDict, query);
    }

    @Override // com.android.inputmethod.latin.Dictionary
    public boolean shouldAutoCommit(final SuggestedWords.SuggestedWordInfo candidate) {
        return candidate.mAutoCommitFirstWordConfidence > 1000000;
    }

    @Override // com.android.inputmethod.latin.Dictionary
    public void close() {
        synchronized (this.mDicTraverseSessions) {
            int size = this.mDicTraverseSessions.size();
            for (int i = 0; i < size; i++) {
                DicTraverseSession dicTraverseSessionValueAt = this.mDicTraverseSessions.valueAt(i);
                if (dicTraverseSessionValueAt != null) {
                    dicTraverseSessionValueAt.close();
                }
            }
            this.mDicTraverseSessions.clear();
        }
        closeInternalLocked();
    }

    private synchronized void closeInternalLocked() {
        long j = this.mNativeDict;
        if (j != 0) {
            closeNative(j);
            this.mNativeDict = 0L;
        }
    }

    protected void finalize() throws Throwable {
        try {
            closeInternalLocked();
        } finally {
            super.finalize();
        }
    }
}
