package com.android.providers.media.util;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.net.Uri;
import android.os.Bundle;
import android.os.Trace;
import android.text.TextUtils;
import android.util.ArrayMap;
import android.util.Log;
import java.util.Locale;
import java.util.function.Consumer;
import java.util.function.Function;

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

    public static String maybeBalance(String str) {
        if (str == null) {
            return null;
        }
        int i = 0;
        char c = 0;
        for (int i2 = 0; i2 < str.length(); i2++) {
            char cCharAt = str.charAt(i2);
            if (cCharAt == '\'' || cCharAt == '\"') {
                if (c == 0) {
                    c = cCharAt;
                } else if (c == cCharAt) {
                    c = 0;
                }
            }
            if (c == 0) {
                if (cCharAt == '(') {
                    i++;
                } else if (cCharAt == ')') {
                    i--;
                }
            }
        }
        while (i > 0) {
            str = str + ")";
            i--;
        }
        while (i < 0) {
            str = "(" + str;
            i++;
        }
        return str;
    }

    public static void resolveQueryArgs(Bundle bundle, Consumer<String> consumer, Function<String, String> function) {
        consumer.accept("android:query-arg-sql-selection");
        consumer.accept("android:query-arg-sql-selection-args");
        resolveGroupBy(bundle, consumer);
        resolveSortOrder(bundle, consumer, function);
        resolveLimit(bundle, consumer);
    }

    private static void resolveGroupBy(Bundle bundle, Consumer<String> consumer) {
        String[] stringArray = bundle.getStringArray("android:query-arg-group-columns");
        if (stringArray != null && stringArray.length != 0) {
            String strJoin = TextUtils.join(", ", stringArray);
            consumer.accept("android:query-arg-group-columns");
            bundle.putString("android:query-arg-sql-group-by", strJoin);
            return;
        }
        consumer.accept("android:query-arg-sql-group-by");
    }

    private static void resolveSortOrder(Bundle bundle, Consumer<String> consumer, Function<String, String> function) {
        String[] stringArray = bundle.getStringArray("android:query-arg-sort-columns");
        if (stringArray != null && stringArray.length != 0) {
            String strJoin = TextUtils.join(", ", stringArray);
            consumer.accept("android:query-arg-sort-columns");
            if (bundle.containsKey("android:query-arg-sort-locale")) {
                strJoin = strJoin + " COLLATE " + function.apply(bundle.getString("android:query-arg-sort-locale"));
                consumer.accept("android:query-arg-sort-locale");
            } else {
                int i = bundle.getInt("android:query-arg-sort-collation", 3);
                if (i == 0 || i == 1) {
                    strJoin = strJoin + " COLLATE NOCASE";
                    consumer.accept("android:query-arg-sort-collation");
                } else if (i == 3) {
                    consumer.accept("android:query-arg-sort-collation");
                }
            }
            int i2 = bundle.getInt("android:query-arg-sort-direction", Integer.MIN_VALUE);
            if (i2 == 0) {
                strJoin = strJoin + " ASC";
                consumer.accept("android:query-arg-sort-direction");
            } else if (i2 == 1) {
                strJoin = strJoin + " DESC";
                consumer.accept("android:query-arg-sort-direction");
            }
            bundle.putString("android:query-arg-sql-sort-order", strJoin);
            return;
        }
        consumer.accept("android:query-arg-sql-sort-order");
    }

    private static void resolveLimit(Bundle bundle, Consumer<String> consumer) {
        int i = bundle.getInt("android:query-arg-limit", Integer.MIN_VALUE);
        if (i != Integer.MIN_VALUE) {
            String string = Integer.toString(i);
            consumer.accept("android:query-arg-limit");
            int i2 = bundle.getInt("android:query-arg-offset", Integer.MIN_VALUE);
            if (i2 != Integer.MIN_VALUE) {
                string = string + " OFFSET " + i2;
                consumer.accept("android:query-arg-offset");
            }
            bundle.putString("android:query-arg-sql-limit", string);
            return;
        }
        consumer.accept("android:query-arg-sql-limit");
    }

    public static void recoverAbusiveLimit(Uri uri, Bundle bundle) {
        String string = bundle.getString("android:query-arg-sql-limit");
        String queryParameter = uri.getQueryParameter("limit");
        if (TextUtils.isEmpty(queryParameter)) {
            return;
        }
        if (!TextUtils.isEmpty(string)) {
            throw new IllegalArgumentException("Abusive '" + queryParameter + "' conflicts with requested '" + string + "'");
        }
        Log.w(Logging.TAG, "Recovered abusive '" + queryParameter + "' from '" + uri + "'");
        bundle.putString("android:query-arg-sql-limit", queryParameter);
    }

    public static void recoverAbusiveSelection(Bundle bundle) {
        String string = bundle.getString("android:query-arg-sql-selection");
        String string2 = bundle.getString("android:query-arg-sql-group-by");
        int iIndexOf = string != null ? string.toUpperCase(Locale.ROOT).indexOf(" GROUP BY ") : -1;
        if (iIndexOf != -1) {
            String strSubstring = string.substring(0, iIndexOf);
            String strSubstring2 = string.substring(iIndexOf + 10);
            String strMaybeBalance = maybeBalance(strSubstring);
            String strMaybeBalance2 = maybeBalance(strSubstring2);
            if (!TextUtils.isEmpty(string2)) {
                throw new IllegalArgumentException("Abusive '" + strMaybeBalance2 + "' conflicts with requested '" + string2 + "'");
            }
            Log.w(Logging.TAG, "Recovered abusive '" + strMaybeBalance + "' and '" + strMaybeBalance2 + "' from '" + string + "'");
            bundle.putString("android:query-arg-sql-selection", strMaybeBalance);
            bundle.putString("android:query-arg-sql-group-by", strMaybeBalance2);
        }
    }

    public static void recoverAbusiveSortOrder(Bundle bundle) {
        String string = bundle.getString("android:query-arg-sql-sort-order");
        String string2 = bundle.getString("android:query-arg-sql-limit");
        int iIndexOf = string != null ? string.toUpperCase(Locale.ROOT).indexOf(" LIMIT ") : -1;
        if (iIndexOf != -1) {
            String strSubstring = string.substring(0, iIndexOf);
            String strSubstring2 = string.substring(iIndexOf + 7);
            if (!TextUtils.isEmpty(string2)) {
                throw new IllegalArgumentException("Abusive '" + strSubstring2 + "' conflicts with requested '" + string2 + "'");
            }
            Log.w(Logging.TAG, "Recovered abusive '" + strSubstring + "' and '" + strSubstring2 + "' from '" + string + "'");
            bundle.putString("android:query-arg-sql-sort-order", strSubstring);
            bundle.putString("android:query-arg-sql-limit", strSubstring2);
        }
    }

    public static Bundle createSqlQueryBundle(String str, String[] strArr, String str2) {
        if (str == null && strArr == null && str2 == null) {
            return null;
        }
        Bundle bundle = new Bundle();
        if (str != null) {
            bundle.putString("android:query-arg-sql-selection", str);
        }
        if (strArr != null) {
            bundle.putStringArray("android:query-arg-sql-selection-args", strArr);
        }
        if (str2 != null) {
            bundle.putString("android:query-arg-sql-sort-order", str2);
        }
        return bundle;
    }

    public static ArrayMap<String, Object> getValues(ContentValues contentValues) {
        ArrayMap<String, Object> arrayMap = new ArrayMap<>();
        for (String str : contentValues.keySet()) {
            arrayMap.put(str, contentValues.get(str));
        }
        return arrayMap;
    }

    public static long executeInsert(SQLiteDatabase sQLiteDatabase, String str, Object[] objArr) throws SQLException {
        Trace.beginSection("executeInsert");
        try {
            SQLiteStatement sQLiteStatementCompileStatement = sQLiteDatabase.compileStatement(str);
            try {
                bindArgs(sQLiteStatementCompileStatement, objArr);
                long jExecuteInsert = sQLiteStatementCompileStatement.executeInsert();
                if (sQLiteStatementCompileStatement != null) {
                    sQLiteStatementCompileStatement.close();
                }
                return jExecuteInsert;
            } finally {
            }
        } finally {
            Trace.endSection();
        }
    }

    public static int executeUpdateDelete(SQLiteDatabase sQLiteDatabase, String str, Object[] objArr) throws SQLException {
        Trace.beginSection("executeUpdateDelete");
        try {
            SQLiteStatement sQLiteStatementCompileStatement = sQLiteDatabase.compileStatement(str);
            try {
                bindArgs(sQLiteStatementCompileStatement, objArr);
                int iExecuteUpdateDelete = sQLiteStatementCompileStatement.executeUpdateDelete();
                if (sQLiteStatementCompileStatement != null) {
                    sQLiteStatementCompileStatement.close();
                }
                return iExecuteUpdateDelete;
            } finally {
            }
        } finally {
            Trace.endSection();
        }
    }

    private static void bindArgs(SQLiteStatement sQLiteStatement, Object[] objArr) {
        if (objArr == null) {
            return;
        }
        for (int i = 0; i < objArr.length; i++) {
            Object obj = objArr[i];
            int typeOfObject = getTypeOfObject(obj);
            if (typeOfObject == 0) {
                sQLiteStatement.bindNull(i + 1);
            } else if (typeOfObject == 1) {
                sQLiteStatement.bindLong(i + 1, ((Number) obj).longValue());
            } else if (typeOfObject == 2) {
                sQLiteStatement.bindDouble(i + 1, ((Number) obj).doubleValue());
            } else if (typeOfObject == 4) {
                sQLiteStatement.bindBlob(i + 1, (byte[]) obj);
            } else if (obj instanceof Boolean) {
                sQLiteStatement.bindLong(i + 1, ((Boolean) obj).booleanValue() ? 1L : 0L);
            } else {
                sQLiteStatement.bindString(i + 1, obj.toString());
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

    public static String escapeForLike(String str) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            char cCharAt = str.charAt(i);
            if (cCharAt == '%') {
                sb.append('\\');
            } else if (cCharAt == '_') {
                sb.append('\\');
            }
            sb.append(cCharAt);
        }
        return sb.toString();
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

    public static boolean getAsBoolean(Bundle bundle, String str, boolean z) {
        return parseBoolean(bundle.get(str), z);
    }

    public static boolean getAsBoolean(ContentValues contentValues, String str, boolean z) {
        return parseBoolean(contentValues.get(str), z);
    }

    public static long getAsLong(ContentValues contentValues, String str, long j) {
        Long asLong = contentValues.getAsLong(str);
        return asLong != null ? asLong.longValue() : j;
    }
}
