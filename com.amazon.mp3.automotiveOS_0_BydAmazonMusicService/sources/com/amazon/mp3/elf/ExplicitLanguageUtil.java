package com.amazon.mp3.elf;

import android.database.Cursor;
import com.amazon.mp3.util.Log;

/* JADX INFO: loaded from: classes3.dex */
public class ExplicitLanguageUtil {
    private static final String TAG = "ExplicitLanguageUtil";

    public static boolean containsOnlyExplicitContent(final Cursor cursor) {
        if (cursor == null || cursor.getCount() < 1) {
            return false;
        }
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            int columnIndex = cursor.getColumnIndex("is_explicit");
            if (columnIndex == -1) {
                Log.error(TAG, "IS_EXPLICIT projection is unavailable in cursor");
                return false;
            }
            if (cursor.getInt(columnIndex) != 1) {
                return false;
            }
            cursor.moveToNext();
        }
        return true;
    }
}
