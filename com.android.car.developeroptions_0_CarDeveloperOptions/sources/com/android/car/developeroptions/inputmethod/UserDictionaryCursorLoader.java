package com.android.car.developeroptions.inputmethod;

import android.content.Context;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.provider.UserDictionary;
import android.util.ArraySet;
import androidx.loader.content.CursorLoader;
import java.util.Locale;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public class UserDictionaryCursorLoader extends CursorLoader {
    static final String[] QUERY_PROJECTION = {"_id", "word", "shortcut"};
    private final String mLocale;

    public UserDictionaryCursorLoader(Context context, String str) {
        super(context);
        this.mLocale = str;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // androidx.loader.content.AsyncTaskLoader
    public Cursor loadInBackground() {
        Cursor cursorQuery;
        MatrixCursor matrixCursor = new MatrixCursor(QUERY_PROJECTION);
        if ("".equals(this.mLocale)) {
            cursorQuery = getContext().getContentResolver().query(UserDictionary.Words.CONTENT_URI, QUERY_PROJECTION, "locale is null", null, "UPPER(word)");
        } else {
            String string = this.mLocale;
            if (string == null) {
                string = Locale.getDefault().toString();
            }
            cursorQuery = getContext().getContentResolver().query(UserDictionary.Words.CONTENT_URI, QUERY_PROJECTION, "locale=?", new String[]{string}, "UPPER(word)");
        }
        ArraySet arraySet = new ArraySet();
        cursorQuery.moveToFirst();
        while (!cursorQuery.isAfterLast()) {
            int i = cursorQuery.getInt(0);
            String string2 = cursorQuery.getString(1);
            String string3 = cursorQuery.getString(2);
            int iHash = Objects.hash(string2, string3);
            if (!arraySet.contains(Integer.valueOf(iHash))) {
                arraySet.add(Integer.valueOf(iHash));
                matrixCursor.addRow(new Object[]{Integer.valueOf(i), string2, string3});
            }
            cursorQuery.moveToNext();
        }
        return matrixCursor;
    }
}
