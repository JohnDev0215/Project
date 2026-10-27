package com.amazon.identity.auth.device;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import java.util.Collection;
import java.util.Date;
import java.util.Locale;
import java.util.Map;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public final class x9 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final String f661a = "com.amazon.identity.auth.device.x9";

    public static boolean a(Cursor cursor, String str) {
        return cursor.getLong(cursor.getColumnIndex(str)) == 1;
    }

    public static Date b(Cursor cursor, String str) {
        return new Date(cursor.getLong(cursor.getColumnIndex(str)));
    }

    public static Cursor a(String[] strArr, Collection<Map<String, String>> collection) {
        MatrixCursor matrixCursor = null;
        if (strArr != null && collection != null) {
            if (strArr.length >= 10000) {
                Log.e(ga.a(f661a), String.format(Locale.ENGLISH, "The number of columns %d is too large to be handled. Issue tracked in SSO-160.", Integer.valueOf(strArr.length)));
                return null;
            }
            matrixCursor = new MatrixCursor(strArr);
            for (Map<String, String> map : collection) {
                String[] strArr2 = new String[strArr.length];
                for (int i = 0; i < strArr.length; i++) {
                    String str = strArr[i];
                    if (map.containsKey(str)) {
                        strArr2[i] = map.get(str);
                    } else {
                        String str2 = f661a;
                        String.format("Column %s not supported", str);
                        ga.a(str2);
                    }
                }
                matrixCursor.addRow(strArr2);
            }
        }
        return matrixCursor;
    }

    public static String a(String str, String str2) {
        return String.format("%s.%s", str, str2);
    }

    public static String a(String str, String str2, String str3) {
        return String.format("%s.%s as %s", str, str2, str3);
    }

    public static boolean a(SQLiteDatabase sQLiteDatabase, String str, ContentValues contentValues, String str2, String[] strArr) {
        try {
            if (sQLiteDatabase.update(str, contentValues, str2, strArr) > 0) {
                return true;
            }
            return sQLiteDatabase.insertOrThrow(str, null, contentValues) >= 0;
        } catch (SQLException unused) {
            String str3 = f661a;
            Log.w(ga.a(str3), "Error inserting into database in ifCannotUpdateThenInsert");
            ga.a(str3);
            return false;
        }
    }

    public static void a(Cursor cursor) {
        if (cursor == null) {
            return;
        }
        cursor.close();
    }
}
