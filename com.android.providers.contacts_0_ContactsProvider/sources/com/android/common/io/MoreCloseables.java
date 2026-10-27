package com.android.common.io;

import android.database.Cursor;

/* JADX INFO: loaded from: classes.dex */
public class MoreCloseables {
    public static void closeQuietly(Cursor cursor) {
        if (cursor != null) {
            cursor.close();
        }
    }
}
