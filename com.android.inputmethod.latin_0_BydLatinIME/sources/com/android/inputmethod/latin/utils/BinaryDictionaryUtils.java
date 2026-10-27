package com.android.inputmethod.latin.utils;

import com.android.inputmethod.latin.BinaryDictionary;
import com.android.inputmethod.latin.common.StringUtils;
import com.android.inputmethod.latin.makedict.DictionaryHeader;
import com.android.inputmethod.latin.makedict.UnsupportedFormatException;
import java.io.File;
import java.io.IOException;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* JADX INFO: loaded from: classes2.dex */
public final class BinaryDictionaryUtils {
    private static final String TAG = "BinaryDictionaryUtils";

    private static native float calcNormalizedScoreNative(int[] before, int[] after, int score);

    private static native boolean createEmptyDictFileNative(String filePath, long dictVersion, String locale, String[] attributeKeyStringArray, String[] attributeValueStringArray);

    private static native int setCurrentTimeForTestNative(int currentTime);

    private BinaryDictionaryUtils() {
    }

    static {
        JniUtils.loadNativeLibrary();
    }

    public static DictionaryHeader getHeader(final File dictFile) throws IOException, UnsupportedFormatException {
        return getHeaderWithOffsetAndLength(dictFile, 0L, dictFile.length());
    }

    public static DictionaryHeader getHeaderWithOffsetAndLength(final File dictFile, final long offset, final long length) throws IOException, UnsupportedFormatException {
        BinaryDictionary binaryDictionary = new BinaryDictionary(dictFile.getAbsolutePath(), offset, length, true, null, "", false);
        DictionaryHeader header = binaryDictionary.getHeader();
        binaryDictionary.close();
        if (header != null) {
            return header;
        }
        throw new IOException();
    }

    public static boolean renameDict(final File dictFile, final File newDictFile) {
        if (dictFile.isFile()) {
            return dictFile.renameTo(newDictFile);
        }
        if (!dictFile.isDirectory()) {
            return false;
        }
        String name = dictFile.getName();
        String name2 = newDictFile.getName();
        if (newDictFile.exists()) {
            return false;
        }
        for (File file : dictFile.listFiles()) {
            if (file.isFile() && !file.renameTo(new File(dictFile, file.getName().replaceFirst(Pattern.quote(name), Matcher.quoteReplacement(name2))))) {
                return false;
            }
        }
        return dictFile.renameTo(newDictFile);
    }

    public static boolean createEmptyDictFile(final String filePath, final long dictVersion, final Locale locale, final Map<String, String> attributeMap) {
        String[] strArr = new String[attributeMap.size()];
        String[] strArr2 = new String[attributeMap.size()];
        int i = 0;
        for (String str : attributeMap.keySet()) {
            strArr[i] = str;
            strArr2[i] = attributeMap.get(str);
            i++;
        }
        return createEmptyDictFileNative(filePath, dictVersion, locale.toString(), strArr, strArr2);
    }

    public static float calcNormalizedScore(final String before, final String after, final int score) {
        return calcNormalizedScoreNative(StringUtils.toCodePointArray(before), StringUtils.toCodePointArray(after), score);
    }

    public static int setCurrentTimeForTest(final int currentTime) {
        return setCurrentTimeForTestNative(currentTime);
    }
}
