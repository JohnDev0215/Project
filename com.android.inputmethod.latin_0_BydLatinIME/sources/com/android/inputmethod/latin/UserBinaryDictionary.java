package com.android.inputmethod.latin;

import android.content.ContentResolver;
import android.content.Context;
import android.database.ContentObserver;
import android.database.Cursor;
import android.database.sqlite.SQLiteException;
import android.net.Uri;
import android.provider.UserDictionary;
import android.text.TextUtils;
import android.util.Log;
import com.android.inputmethod.latin.utils.SubtypeLocaleUtils;
import java.io.File;
import java.util.Arrays;
import java.util.Locale;
import java.util.Objects;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public class UserBinaryDictionary extends ExpandableBinaryDictionary {
    private static final int HISTORICAL_DEFAULT_USER_DICTIONARY_FREQUENCY = 250;
    private static final int LATINIME_DEFAULT_USER_DICTIONARY_FREQUENCY = 160;
    private static final String NAME = "userunigram";
    private static final String USER_DICTIONARY_ALL_LANGUAGES = "";
    private final boolean mAlsoUseMoreRestrictiveLocales;
    private final String mLocaleString;
    private ContentObserver mObserver;
    private static final String TAG = ExpandableBinaryDictionary.class.getSimpleName();
    private static final String[] PROJECTION_QUERY = {"word", "frequency"};

    protected UserBinaryDictionary(final Context context, final Locale locale, final boolean alsoUseMoreRestrictiveLocales, final File dictFile, final String name) {
        super(context, getDictName(name, locale, dictFile), locale, Dictionary.TYPE_USER, dictFile);
        Objects.requireNonNull(locale);
        String string = locale.toString();
        if (SubtypeLocaleUtils.NO_LANGUAGE.equals(string)) {
            this.mLocaleString = "";
        } else {
            this.mLocaleString = string;
        }
        this.mAlsoUseMoreRestrictiveLocales = alsoUseMoreRestrictiveLocales;
        ContentResolver contentResolver = context.getContentResolver();
        this.mObserver = new ContentObserver(null) { // from class: com.android.inputmethod.latin.UserBinaryDictionary.1
            @Override // android.database.ContentObserver
            public void onChange(final boolean self) {
                onChange(self, null);
            }

            @Override // android.database.ContentObserver
            public void onChange(final boolean self, final Uri uri) {
                UserBinaryDictionary.this.setNeedsToRecreate();
            }
        };
        contentResolver.registerContentObserver(UserDictionary.Words.CONTENT_URI, true, this.mObserver);
        reloadDictionaryIfRequired();
    }

    public static UserBinaryDictionary getDictionary(final Context context, final Locale locale, final File dictFile, final String dictNamePrefix, @Nullable final String account) {
        return new UserBinaryDictionary(context, locale, false, dictFile, dictNamePrefix + NAME);
    }

    @Override // com.android.inputmethod.latin.ExpandableBinaryDictionary, com.android.inputmethod.latin.Dictionary
    public synchronized void close() {
        if (this.mObserver != null) {
            this.mContext.getContentResolver().unregisterContentObserver(this.mObserver);
            this.mObserver = null;
        }
        super.close();
    }

    @Override // com.android.inputmethod.latin.ExpandableBinaryDictionary
    public void loadInitialContentsLocked() {
        String[] strArrSplit = TextUtils.isEmpty(this.mLocaleString) ? new String[0] : this.mLocaleString.split("_", 3);
        int length = strArrSplit.length;
        StringBuilder sb = new StringBuilder("(locale is NULL)");
        String str = "";
        for (int i = 0; i < length; i++) {
            strArrSplit[i] = str + strArrSplit[i];
            str = strArrSplit[i] + "_";
            sb.append(" or (locale=?)");
        }
        if (this.mAlsoUseMoreRestrictiveLocales && length < 3) {
            sb.append(" or (locale like ?)");
            String[] strArr = (String[]) Arrays.copyOf(strArrSplit, length + 1);
            strArr[length] = strArrSplit[length - 1] + "_%";
            strArrSplit = strArr;
        }
        addWordsFromProjectionLocked(PROJECTION_QUERY, sb.toString(), strArrSplit);
    }

    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:8:0x001d -> B:24:0x0030). Please report as a decompilation issue!!! */
    private void addWordsFromProjectionLocked(final String[] query, String request, final String[] requestArguments) throws IllegalArgumentException {
        Cursor cursorQuery = null;
        try {
        } catch (SQLiteException e) {
            Log.e(TAG, "SQLiteException in the remote User dictionary process.", e);
        }
        try {
            try {
                cursorQuery = this.mContext.getContentResolver().query(UserDictionary.Words.CONTENT_URI, query, request, requestArguments, null);
                addWordsLocked(cursorQuery);
                if (cursorQuery != null) {
                    cursorQuery.close();
                }
            } catch (SQLiteException e2) {
                Log.e(TAG, "SQLiteException in the remote User dictionary process.", e2);
                if (cursorQuery != null) {
                    cursorQuery.close();
                }
            }
        } catch (Throwable th) {
            if (cursorQuery != null) {
                try {
                    cursorQuery.close();
                } catch (SQLiteException e3) {
                    Log.e(TAG, "SQLiteException in the remote User dictionary process.", e3);
                }
            }
            throw th;
        }
    }

    private static int scaleFrequencyFromDefaultToLatinIme(final int defaultFrequency) {
        if (defaultFrequency > 13421772) {
            return (defaultFrequency / 250) * 160;
        }
        return (defaultFrequency * 160) / 250;
    }

    private void addWordsLocked(final Cursor cursor) {
        if (cursor != null && cursor.moveToFirst()) {
            int columnIndex = cursor.getColumnIndex("word");
            int columnIndex2 = cursor.getColumnIndex("frequency");
            while (!cursor.isAfterLast()) {
                String string = cursor.getString(columnIndex);
                int iScaleFrequencyFromDefaultToLatinIme = scaleFrequencyFromDefaultToLatinIme(cursor.getInt(columnIndex2));
                if (string.length() <= 48) {
                    runGCIfRequiredLocked(true);
                    addUnigramLocked(string, iScaleFrequencyFromDefaultToLatinIme, false, false, -1);
                }
                cursor.moveToNext();
            }
        }
    }
}
