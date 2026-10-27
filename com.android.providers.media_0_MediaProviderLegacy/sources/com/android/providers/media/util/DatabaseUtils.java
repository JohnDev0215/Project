package com.android.providers.media.util;

import android.content.ContentValues;
import android.database.Cursor;
import java.util.Locale;

/* JADX INFO: loaded from: classes.dex */
public class DatabaseUtils {
    public static String bindSelection(String str, Object... objArr) {
        char cCharAt;
        if (str == null) {
            return null;
        }
        if (objArr == null || objArr.length == 0 || str.indexOf(63) == -1) {
            return str;
        }
        int length = str.length();
        StringBuilder sb = new StringBuilder(length);
        int i = 0;
        int i2 = 0;
        char c = ' ';
        while (i < length) {
            int i3 = i + 1;
            char cCharAt2 = str.charAt(i);
            if (cCharAt2 == '?') {
                i = i3;
                while (true) {
                    if (i >= length) {
                        cCharAt = ' ';
                        break;
                    }
                    cCharAt = str.charAt(i);
                    if (cCharAt < '0' || cCharAt > '9') {
                        break;
                    }
                    i++;
                }
                if (i3 != i) {
                    i2 = Integer.parseInt(str.substring(i3, i)) - 1;
                }
                int i4 = i2 + 1;
                Object obj = objArr[i2];
                if (c != ' ' && c != '=') {
                    sb.append(' ');
                }
                int typeOfObject = getTypeOfObject(obj);
                if (typeOfObject == 0) {
                    sb.append("NULL");
                } else if (typeOfObject == 1) {
                    sb.append(((Number) obj).longValue());
                } else if (typeOfObject == 2) {
                    sb.append(((Number) obj).doubleValue());
                } else {
                    if (typeOfObject == 4) {
                        throw new IllegalArgumentException("Blobs not supported");
                    }
                    if (obj instanceof Boolean) {
                        sb.append(((Boolean) obj).booleanValue() ? 1 : 0);
                    } else {
                        sb.append('\'');
                        sb.append(obj.toString().replace("'", "''"));
                        sb.append('\'');
                    }
                }
                if (cCharAt != ' ') {
                    sb.append(' ');
                }
                i2 = i4;
            } else {
                sb.append(cCharAt2);
                c = cCharAt2;
                i = i3;
            }
        }
        return sb.toString();
    }

    public static int getTypeOfObject(Object obj) {
        if (obj == null) {
            return 0;
        }
        if (obj instanceof byte[]) {
            return 4;
        }
        if ((obj instanceof Float) || (obj instanceof Double)) {
            return 2;
        }
        return ((obj instanceof Long) || (obj instanceof Integer) || (obj instanceof Short) || (obj instanceof Byte)) ? 1 : 3;
    }

    public static void copyFromCursorToContentValues(String str, Cursor cursor, ContentValues contentValues) {
        int columnIndex = cursor.getColumnIndex(str);
        if (columnIndex != -1) {
            if (cursor.isNull(columnIndex)) {
                contentValues.putNull(str);
            } else {
                contentValues.put(str, cursor.getString(columnIndex));
            }
        }
    }

    public static String bindList(Object... objArr) {
        StringBuilder sb = new StringBuilder();
        sb.append('(');
        for (int i = 0; i < objArr.length; i++) {
            sb.append('?');
            if (i < objArr.length - 1) {
                sb.append(',');
            }
        }
        sb.append(')');
        return bindSelection(sb.toString(), objArr);
    }

    public static boolean parseBoolean(Object obj, boolean z) {
        if (obj instanceof Boolean) {
            return ((Boolean) obj).booleanValue();
        }
        if (obj instanceof Number) {
            return ((Number) obj).intValue() != 0;
        }
        if (!(obj instanceof String)) {
            return z;
        }
        String lowerCase = ((String) obj).toLowerCase(Locale.ROOT);
        return ("false".equals(lowerCase) || "0".equals(lowerCase)) ? false : true;
    }

    public static boolean getAsBoolean(ContentValues contentValues, String str, boolean z) {
        return parseBoolean(contentValues.get(str), z);
    }

    public static long getAsLong(ContentValues contentValues, String str, long j) {
        Long asLong = contentValues.getAsLong(str);
        return asLong != null ? asLong.longValue() : j;
    }
}
