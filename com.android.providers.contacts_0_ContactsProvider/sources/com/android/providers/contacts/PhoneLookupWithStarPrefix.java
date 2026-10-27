package com.android.providers.contacts;

import android.database.Cursor;
import android.database.MatrixCursor;
import android.telephony.PhoneNumberUtils;
import android.text.TextUtils;
import android.util.Log;
import com.android.internal.annotations.VisibleForTesting;

/* JADX INFO: loaded from: classes.dex */
final class PhoneLookupWithStarPrefix {
    public static Cursor removeNonStarMatchesFromCursor(String str, Cursor cursor) {
        try {
            if (TextUtils.isEmpty(str)) {
                return cursor;
            }
            String strNormalizeNumberWithStar = normalizeNumberWithStar(str);
            if (!strNormalizeNumberWithStar.startsWith("*") && !matchingNumberStartsWithStar(cursor)) {
                cursor.moveToPosition(-1);
                return cursor;
            }
            MatrixCursor matrixCursor = new MatrixCursor(cursor.getColumnNames());
            try {
                cursor.moveToPosition(-1);
                while (cursor.moveToNext()) {
                    String strNormalizeNumberWithStar2 = normalizeNumberWithStar(cursor.getString(cursor.getColumnIndex("number")));
                    if ((!strNormalizeNumberWithStar2.startsWith("*") && !strNormalizeNumberWithStar.startsWith("*")) || strNormalizeNumberWithStar2.equals(strNormalizeNumberWithStar)) {
                        MatrixCursor.RowBuilder rowBuilderNewRow = matrixCursor.newRow();
                        for (int i = 0; i < cursor.getColumnCount(); i++) {
                            rowBuilderNewRow.add(cursor.getColumnName(i), cursorValue(cursor, i));
                        }
                    }
                }
                return matrixCursor;
            } catch (Throwable th) {
                matrixCursor.close();
                throw th;
            }
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
    }

    @VisibleForTesting
    static String normalizeNumberWithStar(String str) {
        if (TextUtils.isEmpty(str)) {
            return str;
        }
        if (str.startsWith("*")) {
            return "*" + PhoneNumberUtils.normalizeNumber(str.substring(1).replace("+", ""));
        }
        return PhoneNumberUtils.normalizeNumber(str);
    }

    private static boolean matchingNumberStartsWithStar(Cursor cursor) {
        cursor.moveToPosition(-1);
        while (cursor.moveToNext()) {
            String strNormalizeNumberWithStar = normalizeNumberWithStar(cursor.getString(cursor.getColumnIndex("number")));
            if (strNormalizeNumberWithStar != null && strNormalizeNumberWithStar.startsWith("*")) {
                return true;
            }
        }
        return false;
    }

    private static Object cursorValue(Cursor cursor, int i) {
        int type = cursor.getType(i);
        if (type == 0) {
            return null;
        }
        if (type == 1) {
            return Integer.valueOf(cursor.getInt(i));
        }
        if (type == 2) {
            return Float.valueOf(cursor.getFloat(i));
        }
        if (type == 3) {
            return cursor.getString(i);
        }
        if (type == 4) {
            return cursor.getBlob(i);
        }
        Log.d("PhoneLookupWSP", "Invalid value in cursor: " + cursor.getType(i));
        return null;
    }
}
