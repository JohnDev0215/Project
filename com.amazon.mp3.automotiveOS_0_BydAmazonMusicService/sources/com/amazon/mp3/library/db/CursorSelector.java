package com.amazon.mp3.library.db;

import android.database.Cursor;
import com.amazon.mp3.util.Log;

/* JADX INFO: loaded from: classes3.dex */
public class CursorSelector {
    private static final String TAG = "CursorSelector";

    public static Cursor getProperCursor(Cursor cursor) {
        Cursor multiWindowedCursor;
        if (cursor == null) {
            return cursor;
        }
        int count = cursor.getCount();
        if (count > 13000) {
            Log.debug(TAG, "Cursor count high, using SlidingWindowCursor");
            multiWindowedCursor = new SlidingWindowCursor(cursor);
        } else if (count > 2000) {
            Log.debug(TAG, "Cursor count moderate, using regular MultiWindowCursor");
            multiWindowedCursor = new MultiWindowedCursor(cursor);
        } else {
            Log.debug(TAG, "Cursor count low, using regular Cursor");
            return cursor;
        }
        return multiWindowedCursor;
    }
}
