package com.android.inputmethod.latin.personalization;

import android.content.Context;
import android.util.Log;
import com.android.inputmethod.latin.common.FileUtils;
import java.io.File;
import java.io.FilenameFilter;
import java.lang.ref.SoftReference;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public class PersonalizationHelper {
    private static final boolean DEBUG = false;
    private static final String TAG = "PersonalizationHelper";
    private static final ConcurrentHashMap<String, SoftReference<UserHistoryDictionary>> sLangUserHistoryDictCache = new ConcurrentHashMap<>();

    @Nonnull
    public static UserHistoryDictionary getUserHistoryDictionary(final Context context, final Locale locale, @Nullable final String accountName) {
        String string = locale.toString();
        if (accountName != null) {
            string = string + "." + accountName;
        }
        ConcurrentHashMap<String, SoftReference<UserHistoryDictionary>> concurrentHashMap = sLangUserHistoryDictCache;
        synchronized (concurrentHashMap) {
            if (concurrentHashMap.containsKey(string)) {
                SoftReference<UserHistoryDictionary> softReference = concurrentHashMap.get(string);
                UserHistoryDictionary userHistoryDictionary = softReference == null ? null : softReference.get();
                if (userHistoryDictionary != null) {
                    userHistoryDictionary.reloadDictionaryIfRequired();
                    return userHistoryDictionary;
                }
            }
            UserHistoryDictionary userHistoryDictionary2 = new UserHistoryDictionary(context, locale, accountName);
            concurrentHashMap.put(string, new SoftReference<>(userHistoryDictionary2));
            return userHistoryDictionary2;
        }
    }

    public static void removeAllUserHistoryDictionaries(final Context context) {
        UserHistoryDictionary userHistoryDictionary;
        ConcurrentHashMap<String, SoftReference<UserHistoryDictionary>> concurrentHashMap = sLangUserHistoryDictCache;
        synchronized (concurrentHashMap) {
            for (Map.Entry<String, SoftReference<UserHistoryDictionary>> entry : concurrentHashMap.entrySet()) {
                if (entry.getValue() != null && (userHistoryDictionary = entry.getValue().get()) != null) {
                    userHistoryDictionary.clear();
                }
            }
            sLangUserHistoryDictCache.clear();
            File filesDir = context.getFilesDir();
            if (filesDir == null) {
                Log.e(TAG, "context.getFilesDir() returned null.");
            } else if (!FileUtils.deleteFilteredFiles(filesDir, new DictFilter(UserHistoryDictionary.NAME))) {
                Log.e(TAG, "Cannot remove dictionary files. filesDir: " + filesDir.getAbsolutePath() + ", dictNamePrefix: " + UserHistoryDictionary.NAME);
            }
        }
    }

    private static class DictFilter implements FilenameFilter {
        private final String mName;

        DictFilter(final String name) {
            this.mName = name;
        }

        @Override // java.io.FilenameFilter
        public boolean accept(final File dir, final String name) {
            return name.startsWith(this.mName);
        }
    }
}
