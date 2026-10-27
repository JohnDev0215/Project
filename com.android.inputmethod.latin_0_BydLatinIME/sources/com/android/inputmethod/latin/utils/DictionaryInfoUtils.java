package com.android.inputmethod.latin.utils;

import android.content.ContentValues;
import android.content.Context;
import android.content.res.Resources;
import android.text.TextUtils;
import android.util.Log;
import android.view.inputmethod.InputMethodSubtype;
import com.android.inputmethod.latin.AssetFileAddress;
import com.android.inputmethod.latin.BinaryDictionaryGetter;
import com.android.inputmethod.latin.R;
import com.android.inputmethod.latin.RichInputMethodManager;
import com.android.inputmethod.latin.common.FileUtils;
import com.android.inputmethod.latin.common.LocaleUtils;
import com.android.inputmethod.latin.makedict.DictionaryHeader;
import com.android.inputmethod.latin.makedict.UnsupportedFormatException;
import com.android.inputmethod.latin.settings.SpacingAndPunctuations;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Locale;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public class DictionaryInfoUtils {
    private static final String DECODER_DICT_SUFFIX = "";
    private static final String DEFAULT_MAIN_DICT = "main";
    private static final String MAIN_DICT_PREFIX = "main_";
    private static final int MAX_HEX_DIGITS_FOR_CODEPOINT = 6;
    public static final String RESOURCE_PACKAGE_NAME = R.class.getPackage().getName();
    private static final String TAG = "DictionaryInfoUtils";
    private static final String TEMP_DICT_FILE_SUB = "___";

    private static boolean isFileNameCharacter(int codePoint) {
        if (codePoint >= 48 && codePoint <= 57) {
            return true;
        }
        if (codePoint < 65 || codePoint > 90) {
            return (codePoint >= 97 && codePoint <= 122) || codePoint == 95;
        }
        return true;
    }

    public static class DictionaryInfo {
        private static final String DATE_COLUMN = "date";
        private static final String DESCRIPTION_COLUMN = "description";
        private static final String FILESIZE_COLUMN = "filesize";
        private static final String LOCALE_COLUMN = "locale";
        private static final String LOCAL_FILENAME_COLUMN = "filename";
        private static final String VERSION_COLUMN = "version";
        private static final String WORDLISTID_COLUMN = "id";

        @Nullable
        public final String mDescription;

        @Nullable
        public final String mFilename;
        public final long mFilesize;

        @Nonnull
        public final String mId;

        @Nonnull
        public final Locale mLocale;
        public final long mModifiedTimeMillis;
        public final int mVersion;

        public DictionaryInfo(@Nonnull String id, @Nonnull Locale locale, @Nullable String description, @Nullable String filename, long filesize, long modifiedTimeMillis, int version) {
            this.mId = id;
            this.mLocale = locale;
            this.mDescription = description;
            this.mFilename = filename;
            this.mFilesize = filesize;
            this.mModifiedTimeMillis = modifiedTimeMillis;
            this.mVersion = version;
        }

        public ContentValues toContentValues() {
            ContentValues contentValues = new ContentValues();
            contentValues.put("id", this.mId);
            contentValues.put("locale", this.mLocale.toString());
            contentValues.put("description", this.mDescription);
            String str = this.mFilename;
            if (str == null) {
                str = "";
            }
            contentValues.put("filename", str);
            contentValues.put("date", Long.valueOf(TimeUnit.MILLISECONDS.toSeconds(this.mModifiedTimeMillis)));
            contentValues.put("filesize", Long.valueOf(this.mFilesize));
            contentValues.put("version", Integer.valueOf(this.mVersion));
            return contentValues;
        }

        public String toString() {
            return "DictionaryInfo : Id = '" + this.mId + "' : Locale=" + this.mLocale + " : Version=" + this.mVersion;
        }
    }

    private DictionaryInfoUtils() {
    }

    public static String replaceFileNameDangerousCharacters(final String name) {
        StringBuilder sb = new StringBuilder();
        int length = name.length();
        int iOffsetByCodePoints = 0;
        while (iOffsetByCodePoints < length) {
            int iCodePointAt = name.codePointAt(iOffsetByCodePoints);
            if (isFileNameCharacter(iCodePointAt)) {
                sb.appendCodePoint(iCodePointAt);
            } else {
                sb.append(String.format((Locale) null, "%%%1$06x", Integer.valueOf(iCodePointAt)));
            }
            iOffsetByCodePoints = name.offsetByCodePoints(iOffsetByCodePoints, 1);
        }
        return sb.toString();
    }

    private static String getWordListCacheDirectory(final Context context) {
        return context.getFilesDir() + File.separator + "dicts";
    }

    public static String getWordListStagingDirectory(final Context context) {
        return context.getFilesDir() + File.separator + "staging";
    }

    public static String getWordListTempDirectory(final Context context) {
        return context.getFilesDir() + File.separator + "tmp";
    }

    @Nonnull
    public static String getWordListIdFromFileName(@Nonnull final String fname) {
        StringBuilder sb = new StringBuilder();
        int length = fname.length();
        int iOffsetByCodePoints = 0;
        while (iOffsetByCodePoints < length) {
            int iCodePointAt = fname.codePointAt(iOffsetByCodePoints);
            if (37 != iCodePointAt) {
                sb.appendCodePoint(iCodePointAt);
            } else {
                int i = iOffsetByCodePoints + 1;
                iOffsetByCodePoints += 6;
                sb.appendCodePoint(Integer.parseInt(fname.substring(i, i + 6), 16));
            }
            iOffsetByCodePoints = fname.offsetByCodePoints(iOffsetByCodePoints, 1);
        }
        return sb.toString();
    }

    public static File[] getCachedDirectoryList(final Context context) {
        return new File(getWordListCacheDirectory(context)).listFiles();
    }

    public static File[] getStagingDirectoryList(final Context context) {
        return new File(getWordListStagingDirectory(context)).listFiles();
    }

    @Nullable
    public static File[] getUnusedDictionaryList(final Context context) {
        return context.getFilesDir().listFiles(new FilenameFilter() { // from class: com.android.inputmethod.latin.utils.DictionaryInfoUtils.1
            @Override // java.io.FilenameFilter
            public boolean accept(File dir, String filename) {
                return !TextUtils.isEmpty(filename) && filename.endsWith(".dict") && filename.contains("___");
            }
        });
    }

    @Nullable
    public static String getCategoryFromFileName(@Nonnull final String fileName) {
        String[] strArrSplit = getWordListIdFromFileName(fileName).split(":");
        if (2 != strArrSplit.length) {
            return null;
        }
        return strArrSplit[0];
    }

    public static String getCacheDirectoryForLocale(final String locale, final Context context) {
        String str = getWordListCacheDirectory(context) + File.separator + replaceFileNameDangerousCharacters(locale);
        File file = new File(str);
        if (!file.exists() && !file.mkdirs()) {
            Log.e(TAG, "Could not create the directory for locale" + locale);
        }
        return str;
    }

    public static String getCacheFileName(String id, String locale, Context context) {
        return getCacheDirectoryForLocale(locale, context) + File.separator + replaceFileNameDangerousCharacters(id);
    }

    public static String getStagingFileName(String id, String locale, Context context) {
        String wordListStagingDirectory = getWordListStagingDirectory(context);
        File file = new File(wordListStagingDirectory);
        if (!file.exists() && !file.mkdirs()) {
            Log.e(TAG, "Could not create the staging directory.");
        }
        return wordListStagingDirectory + File.separator + replaceFileNameDangerousCharacters(locale + "___" + id);
    }

    public static void moveStagingFilesIfExists(Context context) {
        File[] stagingDirectoryList = getStagingDirectoryList(context);
        if (stagingDirectoryList == null || stagingDirectoryList.length <= 0) {
            return;
        }
        for (File file : stagingDirectoryList) {
            String name = file.getName();
            if (name.indexOf("___") == -1) {
                Log.e(TAG, "Staging file does not have ___ substring.");
            } else {
                String[] strArrSplit = name.split("___");
                if (strArrSplit.length != 2) {
                    Log.e(TAG, String.format("malformed staging file %s. Deleting.", file.getAbsoluteFile()));
                    file.delete();
                } else {
                    File file2 = new File(getCacheDirectoryForLocale(strArrSplit[0], context) + File.separator + strArrSplit[1]);
                    if (!FileUtils.renameTo(file, file2)) {
                        Log.e(TAG, String.format("Failed to rename from %s to %s.", file.getAbsoluteFile(), file2.getAbsoluteFile()));
                    }
                }
            }
        }
    }

    public static boolean isMainWordListId(final String id) {
        String[] strArrSplit = id.split(":");
        if (2 != strArrSplit.length) {
            return false;
        }
        return "main".equals(strArrSplit[0]);
    }

    public static boolean isDictionaryAvailable(final Context context, final Locale locale) {
        return getMainDictionaryResourceIdIfAvailableForLocale(context.getResources(), locale) != 0;
    }

    public static int getMainDictionaryResourceIdIfAvailableForLocale(final Resources res, final Locale locale) {
        int identifier;
        if (!locale.getCountry().isEmpty() && (identifier = res.getIdentifier(MAIN_DICT_PREFIX + locale.toString().toLowerCase(Locale.ROOT) + "", "raw", RESOURCE_PACKAGE_NAME)) != 0) {
            return identifier;
        }
        int identifier2 = res.getIdentifier(MAIN_DICT_PREFIX + locale.getLanguage() + "", "raw", RESOURCE_PACKAGE_NAME);
        if (identifier2 != 0) {
            return identifier2;
        }
        return 0;
    }

    public static int getMainDictionaryResourceId(final Resources res, final Locale locale) {
        int mainDictionaryResourceIdIfAvailableForLocale = getMainDictionaryResourceIdIfAvailableForLocale(res, locale);
        return mainDictionaryResourceIdIfAvailableForLocale != 0 ? mainDictionaryResourceIdIfAvailableForLocale : res.getIdentifier("main", "raw", RESOURCE_PACKAGE_NAME);
    }

    public static String getMainDictId(@Nonnull final Locale locale) {
        return "main:" + locale.toString().toLowerCase();
    }

    public static DictionaryHeader getDictionaryFileHeaderOrNull(final File file, final long offset, final long length) {
        try {
            return BinaryDictionaryUtils.getHeaderWithOffsetAndLength(file, offset, length);
        } catch (UnsupportedFormatException | IOException unused) {
            return null;
        }
    }

    private static DictionaryInfo createDictionaryInfoFromFileAddress(@Nonnull final AssetFileAddress fileAddress, final Locale locale) {
        return new DictionaryInfo(getMainDictId(locale), locale, SubtypeLocaleUtils.getSubtypeLocaleDisplayName(locale.toString()), null, fileAddress.mLength, new File(fileAddress.mFilename).lastModified(), DictionaryHeaderUtils.getContentVersion(fileAddress));
    }

    @Nullable
    private static DictionaryInfo createDictionaryInfoForUnCachedFile(@Nonnull final AssetFileAddress fileAddress, final Locale locale) {
        String mainDictId = getMainDictId(locale);
        int contentVersion = DictionaryHeaderUtils.getContentVersion(fileAddress);
        if (contentVersion == -1) {
            fileAddress.deleteUnderlyingFile();
            return null;
        }
        String subtypeLocaleDisplayName = SubtypeLocaleUtils.getSubtypeLocaleDisplayName(locale.toString());
        File file = new File(fileAddress.mFilename);
        return new DictionaryInfo(mainDictId, locale, subtypeLocaleDisplayName, file.getName(), fileAddress.mLength, file.lastModified(), contentVersion);
    }

    private static DictionaryInfo createDictionaryInfoFromLocale(Locale locale) {
        return new DictionaryInfo(getMainDictId(locale), locale, SubtypeLocaleUtils.getSubtypeLocaleDisplayName(locale.toString()), null, 0L, 0L, -1);
    }

    private static void addOrUpdateDictInfo(final ArrayList<DictionaryInfo> dictList, final DictionaryInfo newElement) {
        Iterator<DictionaryInfo> it = dictList.iterator();
        while (it.hasNext()) {
            DictionaryInfo next = it.next();
            if (next.mLocale.equals(newElement.mLocale)) {
                if (newElement.mVersion <= next.mVersion) {
                    return;
                } else {
                    it.remove();
                }
            }
        }
        dictList.add(newElement);
    }

    public static ArrayList<DictionaryInfo> getCurrentDictionaryFileNameAndVersionInfo(final Context context) {
        DictionaryInfo dictionaryInfoCreateDictionaryInfoFromFileAddress;
        Locale localeConstructLocaleFromString;
        DictionaryInfo dictionaryInfoCreateDictionaryInfoFromFileAddress2;
        ArrayList<DictionaryInfo> arrayList = new ArrayList<>();
        File[] cachedDirectoryList = getCachedDirectoryList(context);
        if (cachedDirectoryList != null) {
            for (File file : cachedDirectoryList) {
                String wordListIdFromFileName = getWordListIdFromFileName(file.getName());
                for (File file2 : BinaryDictionaryGetter.getCachedWordLists(wordListIdFromFileName, context)) {
                    if (isMainWordListId(getWordListIdFromFileName(file2.getName())) && (dictionaryInfoCreateDictionaryInfoFromFileAddress2 = createDictionaryInfoFromFileAddress(AssetFileAddress.makeFromFile(file2), (localeConstructLocaleFromString = LocaleUtils.constructLocaleFromString(wordListIdFromFileName)))) != null && dictionaryInfoCreateDictionaryInfoFromFileAddress2.mLocale.equals(localeConstructLocaleFromString)) {
                        addOrUpdateDictInfo(arrayList, dictionaryInfoCreateDictionaryInfoFromFileAddress2);
                    }
                }
            }
        }
        File[] unusedDictionaryList = getUnusedDictionaryList(context);
        if (unusedDictionaryList != null) {
            for (File file3 : unusedDictionaryList) {
                String name = file3.getName();
                int iIndexOf = name.indexOf("___");
                if (iIndexOf != -1) {
                    DictionaryInfo dictionaryInfoCreateDictionaryInfoForUnCachedFile = createDictionaryInfoForUnCachedFile(AssetFileAddress.makeFromFile(file3), LocaleUtils.constructLocaleFromString(name.substring(0, iIndexOf)));
                    if (dictionaryInfoCreateDictionaryInfoForUnCachedFile != null) {
                        addOrUpdateDictInfo(arrayList, dictionaryInfoCreateDictionaryInfoForUnCachedFile);
                    }
                }
            }
        }
        for (String str : context.getResources().getAssets().getLocales()) {
            Locale localeConstructLocaleFromString2 = LocaleUtils.constructLocaleFromString(str);
            int mainDictionaryResourceIdIfAvailableForLocale = getMainDictionaryResourceIdIfAvailableForLocale(context.getResources(), localeConstructLocaleFromString2);
            if (mainDictionaryResourceIdIfAvailableForLocale != 0 && (dictionaryInfoCreateDictionaryInfoFromFileAddress = createDictionaryInfoFromFileAddress(BinaryDictionaryGetter.loadFallbackResource(context, mainDictionaryResourceIdIfAvailableForLocale), localeConstructLocaleFromString2)) != null && dictionaryInfoCreateDictionaryInfoFromFileAddress.mLocale.equals(localeConstructLocaleFromString2)) {
                addOrUpdateDictInfo(arrayList, dictionaryInfoCreateDictionaryInfoFromFileAddress);
            }
        }
        RichInputMethodManager.init(context);
        Iterator<InputMethodSubtype> it = RichInputMethodManager.getInstance().getMyEnabledInputMethodSubtypeList(true).iterator();
        while (it.hasNext()) {
            addOrUpdateDictInfo(arrayList, createDictionaryInfoFromLocale(LocaleUtils.constructLocaleFromString(it.next().getLocale())));
        }
        return arrayList;
    }

    public static boolean looksValidForDictionaryInsertion(final CharSequence text, final SpacingAndPunctuations spacingAndPunctuations) {
        int length;
        if (TextUtils.isEmpty(text) || (length = text.length()) > 48) {
            return false;
        }
        int i = 0;
        int i2 = 0;
        while (i < length) {
            int iCodePointAt = Character.codePointAt(text, i);
            int iCharCount = Character.charCount(iCodePointAt);
            i += iCharCount;
            if (Character.isDigit(iCodePointAt)) {
                i2 += iCharCount;
            } else if (!spacingAndPunctuations.isWordCodePoint(iCodePointAt)) {
                return false;
            }
        }
        return i2 < length;
    }
}
