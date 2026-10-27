package com.amazon.mp3.util;

import android.database.Cursor;

/* JADX INFO: loaded from: classes4.dex */
public class PrimeUtil {
    public static String getUniqueValue(Cursor cursor, String columnName) {
        if (cursor == null || cursor.getCount() == 0) {
            return "";
        }
        int position = cursor.getPosition();
        cursor.moveToFirst();
        int columnIndexOrThrow = cursor.getColumnIndexOrThrow(columnName);
        boolean z = true;
        String str = "";
        do {
            String string = cursor.getString(columnIndexOrThrow);
            if (string != null && string.length() > 0) {
                if (str.length() == 0) {
                    str = string;
                } else if (!string.equals(str)) {
                    z = false;
                }
            }
            if (!z) {
                break;
            }
        } while (cursor.moveToNext());
        cursor.moveToPosition(position);
        return z ? str : "";
    }
}
