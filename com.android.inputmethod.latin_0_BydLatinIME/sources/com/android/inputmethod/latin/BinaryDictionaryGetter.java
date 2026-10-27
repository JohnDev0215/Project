package com.android.inputmethod.latin;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.AssetFileDescriptor;
import android.util.Log;
import com.android.inputmethod.latin.common.LocaleUtils;
import com.android.inputmethod.latin.makedict.UnsupportedFormatException;
import com.android.inputmethod.latin.utils.BinaryDictionaryUtils;
import com.android.inputmethod.latin.utils.DictionaryInfoUtils;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.BufferUnderflowException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;

/* JADX INFO: loaded from: classes2.dex */
public final class BinaryDictionaryGetter {
    private static final String COMMON_PREFERENCES_NAME = "LatinImeDictPrefs";
    private static final File[] EMPTY_FILE_ARRAY = new File[0];
    public static final String ID_CATEGORY_SEPARATOR = ":";
    public static final String MAIN_DICTIONARY_CATEGORY = "main";
    private static final boolean SHOULD_USE_DICT_VERSION = true;
    private static final String TAG = "BinaryDictionaryGetter";
    private static String VERSION_KEY = "version";

    private BinaryDictionaryGetter() {
    }

    public static String getTempFileName(final String id, final Context context) throws IOException {
        String strReplaceFileNameDangerousCharacters = DictionaryInfoUtils.replaceFileNameDangerousCharacters(id);
        File file = new File(DictionaryInfoUtils.getWordListTempDirectory(context));
        if (!file.exists() && !file.mkdirs()) {
            Log.e(TAG, "Could not create the temporary directory");
        }
        return File.createTempFile("xxx" + strReplaceFileNameDangerousCharacters, null, file).getAbsolutePath();
    }

    public static AssetFileAddress loadFallbackResource(final Context context, final int fallbackResId) {
        try {
            AssetFileDescriptor assetFileDescriptorOpenRawResourceFd = context.getResources().openRawResourceFd(fallbackResId);
            if (assetFileDescriptorOpenRawResourceFd == null) {
                Log.e(TAG, "Resource cannot be opened: " + fallbackResId);
                return null;
            }
            try {
                return AssetFileAddress.makeFromFileNameAndOffset(context.getApplicationInfo().sourceDir, assetFileDescriptorOpenRawResourceFd.getStartOffset(), assetFileDescriptorOpenRawResourceFd.getLength());
            } finally {
                try {
                    assetFileDescriptorOpenRawResourceFd.close();
                } catch (IOException unused) {
                }
            }
        } catch (RuntimeException unused2) {
            Log.e(TAG, "Resource not found: " + fallbackResId);
            return null;
        }
    }

    private static final class DictPackSettings {
        final SharedPreferences mDictPreferences;

        public DictPackSettings(final Context context) {
            this.mDictPreferences = context == null ? null : context.getSharedPreferences(BinaryDictionaryGetter.COMMON_PREFERENCES_NAME, 4);
        }

        public boolean isWordListActive(final String dictId) {
            SharedPreferences sharedPreferences = this.mDictPreferences;
            if (sharedPreferences == null) {
                return true;
            }
            return sharedPreferences.getBoolean(dictId, true);
        }
    }

    private static final class FileAndMatchLevel {
        final File mFile;
        final int mMatchLevel;

        public FileAndMatchLevel(final File file, final int matchLevel) {
            this.mFile = file;
            this.mMatchLevel = matchLevel;
        }
    }

    public static File[] getCachedWordLists(final String locale, final Context context) {
        File[] fileArrListFiles;
        File[] cachedDirectoryList = DictionaryInfoUtils.getCachedDirectoryList(context);
        if (cachedDirectoryList == null) {
            return EMPTY_FILE_ARRAY;
        }
        HashMap map = new HashMap();
        int i = 0;
        for (File file : cachedDirectoryList) {
            if (file.isDirectory()) {
                int matchLevel = LocaleUtils.getMatchLevel(DictionaryInfoUtils.getWordListIdFromFileName(file.getName()), locale);
                if (LocaleUtils.isMatch(matchLevel) && (fileArrListFiles = file.listFiles()) != null) {
                    for (File file2 : fileArrListFiles) {
                        String categoryFromFileName = DictionaryInfoUtils.getCategoryFromFileName(file2.getName());
                        FileAndMatchLevel fileAndMatchLevel = (FileAndMatchLevel) map.get(categoryFromFileName);
                        if (fileAndMatchLevel == null || fileAndMatchLevel.mMatchLevel < matchLevel) {
                            map.put(categoryFromFileName, new FileAndMatchLevel(file2, matchLevel));
                        }
                    }
                }
            }
        }
        if (map.isEmpty()) {
            return EMPTY_FILE_ARRAY;
        }
        File[] fileArr = new File[map.size()];
        Iterator it = map.values().iterator();
        while (it.hasNext()) {
            fileArr[i] = ((FileAndMatchLevel) it.next()).mFile;
            i++;
        }
        return fileArr;
    }

    private static boolean hackCanUseDictionaryFile(final File file) {
        try {
            String str = BinaryDictionaryUtils.getHeader(file).mDictionaryOptions.mAttributes.get(VERSION_KEY);
            if (str == null) {
                return false;
            }
            return Integer.parseInt(str) >= 18;
        } catch (UnsupportedFormatException | FileNotFoundException | IOException | NumberFormatException | BufferUnderflowException unused) {
            return false;
        }
    }

    public static ArrayList<AssetFileAddress> getDictionaryFiles(final Locale locale, final Context context, boolean notifyDictionaryPackForUpdates) {
        AssetFileAddress assetFileAddressLoadFallbackResource;
        if (notifyDictionaryPackForUpdates) {
            BinaryDictionaryFileDumper.downloadDictIfNeverRequested(locale, context, DictionaryInfoUtils.isDictionaryAvailable(context, locale));
            DictionaryInfoUtils.moveStagingFilesIfExists(context);
        }
        File[] cachedWordLists = getCachedWordLists(locale.toString(), context);
        String mainDictId = DictionaryInfoUtils.getMainDictId(locale);
        DictPackSettings dictPackSettings = new DictPackSettings(context);
        ArrayList<AssetFileAddress> arrayList = new ArrayList<>();
        boolean z = false;
        for (File file : cachedWordLists) {
            String wordListIdFromFileName = DictionaryInfoUtils.getWordListIdFromFileName(file.getName());
            boolean z2 = file.canRead() && hackCanUseDictionaryFile(file);
            if (z2 && DictionaryInfoUtils.isMainWordListId(wordListIdFromFileName)) {
                z = true;
            }
            if (dictPackSettings.isWordListActive(wordListIdFromFileName)) {
                if (z2) {
                    AssetFileAddress assetFileAddressMakeFromFileName = AssetFileAddress.makeFromFileName(file.getPath());
                    if (assetFileAddressMakeFromFileName != null) {
                        arrayList.add(assetFileAddressMakeFromFileName);
                    }
                } else {
                    Log.e(TAG, "Found a cached dictionary file for " + locale.toString() + " but cannot read or use it");
                }
            }
        }
        if (!z && dictPackSettings.isWordListActive(mainDictId) && (assetFileAddressLoadFallbackResource = loadFallbackResource(context, DictionaryInfoUtils.getMainDictionaryResourceId(context.getResources(), locale))) != null) {
            arrayList.add(assetFileAddressLoadFallbackResource);
        }
        return arrayList;
    }
}
