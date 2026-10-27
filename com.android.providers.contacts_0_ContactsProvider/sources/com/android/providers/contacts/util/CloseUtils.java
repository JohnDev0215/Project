package com.android.providers.contacts.util;

import android.database.Cursor;

/* JADX INFO: loaded from: classes.dex */
public class CloseUtils {
    public static void closeQuietly(Cursor cursor) {
        if (cursor != null) {
            cursor.close();
        }
    }
}
