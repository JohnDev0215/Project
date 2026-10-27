package com.amazon.mp3.util;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;
import com.amazon.device.crashmanager.CrashDetectionHelper;
import com.amazon.mp3.service.metrics.MetricsLogger;
import com.amazon.music.metrics.mts.event.definition.flex.FlexEvent;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;

/* JADX INFO: loaded from: classes4.dex */
public class DbUtil {
    private static final String LOGTAG = "DbUtil";
    private static HashSet<String> sIntColumnNames;

    public static void closeCursor(Cursor c) {
        if (c == null || c.isClosed()) {
            return;
        }
        c.close();
    }

    static {
        HashSet<String> hashSet = new HashSet<>();
        sIntColumnNames = hashSet;
        hashSet.add("_id");
        sIntColumnNames.add("album_id");
        sIntColumnNames.add("album_art_id");
        sIntColumnNames.add("album_artist_id");
        sIntColumnNames.add("artist_id");
        sIntColumnNames.add("track_num");
        sIntColumnNames.add("disc_num");
        sIntColumnNames.add("exists_flag");
        sIntColumnNames.add("date_created");
        sIntColumnNames.add("date_updated");
        sIntColumnNames.add("date_purchased");
        sIntColumnNames.add("purchased");
        sIntColumnNames.add("uploaded");
        sIntColumnNames.add("size");
        sIntColumnNames.add("duration");
        sIntColumnNames.add("bookmark");
        sIntColumnNames.add("is_podcast");
        sIntColumnNames.add("luid_hash");
        sIntColumnNames.add("is_explicit");
        sIntColumnNames.add("playlist_track_status");
    }

    /* JADX WARN: Code restructure failed: missing block: B:103:?, code lost:
    
        return true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x0050, code lost:
    
        r16.moveToPrevious();
     */
    /* JADX WARN: Code restructure failed: missing block: B:70:0x0165, code lost:
    
        r17.releaseReference();
        r0 = new java.lang.Object[3];
        r0[0] = java.lang.Integer.valueOf(r17.getNumRows());
     */
    /* JADX WARN: Code restructure failed: missing block: B:71:0x017e, code lost:
    
        if (java.lang.Thread.currentThread().isInterrupted() == false) goto L73;
     */
    /* JADX WARN: Code restructure failed: missing block: B:73:0x0181, code lost:
    
        r3 = "false";
     */
    /* JADX WARN: Code restructure failed: missing block: B:74:0x0182, code lost:
    
        r0[1] = r3;
        r0[2] = java.lang.Long.valueOf(java.lang.Thread.currentThread().getId());
        com.amazon.mp3.util.Log.debug("DbUtil", "fillCursorWindow: %d, interrupted=%s, threadId=%d", r0);
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r16v0, types: [android.database.Cursor] */
    /* JADX WARN: Type inference failed for: r17v0, types: [android.database.CursorWindow, android.database.sqlite.SQLiteClosable] */
    /* JADX WARN: Type inference failed for: r9v0 */
    /* JADX WARN: Type inference failed for: r9v1, types: [boolean] */
    /* JADX WARN: Type inference failed for: r9v2, types: [int] */
    /* JADX WARN: Type inference failed for: r9v3 */
    /* JADX WARN: Type inference failed for: r9v5 */
    /* JADX WARN: Type inference failed for: r9v6 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static boolean fillWindowFromCursor(android.database.Cursor r16, android.database.CursorWindow r17, int r18) {
        /*
            Method dump skipped, instruction units count: 512
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.mp3.util.DbUtil.fillWindowFromCursor(android.database.Cursor, android.database.CursorWindow, int):boolean");
    }

    public static long longFromCursor(Cursor c) {
        try {
            c.moveToFirst();
            return Long.valueOf(c.getLong(0)).longValue();
        } catch (Exception unused) {
            Log.debug(LOGTAG, "Could not parse long");
            return -1L;
        } finally {
            c.close();
        }
    }

    public static boolean deleteDatabase(Context context, String name) {
        try {
            return context.deleteDatabase(name);
        } catch (Exception e) {
            Log.warning(LOGTAG, String.format(Locale.US, "Could not delete %s database", name), e);
            return false;
        }
    }

    public static String applyBinaryOperator(String lhs, String operator, String rhs) {
        if (lhs == null || lhs.length() == 0) {
            return rhs;
        }
        if (rhs == null || rhs.length() == 0) {
            return lhs;
        }
        return '(' + lhs + ") " + operator + " (" + rhs + ')';
    }

    public static String[] mergeColumnArrays(String[] left, String[] right) {
        int i;
        int i2 = 0;
        String[] strArr = new String[(left != null ? left.length : 0) + (right != null ? right.length : 0)];
        if (left != null) {
            int length = left.length;
            int i3 = 0;
            i = 0;
            while (i3 < length) {
                strArr[i] = left[i3];
                i3++;
                i++;
            }
        } else {
            i = 0;
        }
        if (right != null) {
            int length2 = right.length;
            while (i2 < length2) {
                strArr[i] = right[i2];
                i2++;
                i++;
            }
        }
        return strArr;
    }

    public static int intFromCursor(Cursor c) {
        try {
            try {
                if (c.moveToFirst()) {
                    return c.getInt(0);
                }
            } catch (Exception unused) {
                Log.debug(LOGTAG, "Could not parse int", new Throwable());
            }
            return -1;
        } finally {
            c.close();
        }
    }

    public static String stringFromCursor(Cursor c) {
        try {
            c.moveToFirst();
            return c.getString(0);
        } catch (Exception unused) {
            Log.debug(LOGTAG, "Could not parse string");
            return null;
        } finally {
            c.close();
        }
    }

    public static String[] buildLikeCriteriaAndSortOrderForFilter(String filter, String[] columns) {
        if (columns.length == 0) {
            throw new IllegalArgumentException("columns must have at least one entry");
        }
        String str = " LIKE '%" + filter.replace("'", "''") + "%'";
        StringBuilder sb = new StringBuilder();
        StringBuilder sb2 = new StringBuilder();
        if (filter.length() > 0) {
            for (int i = 0; i < columns.length; i++) {
                String str2 = columns[i];
                sb2.append(str2);
                sb2.append(str);
                sb.append("LOWER(");
                sb.append(str2);
                sb.append(") ASC");
                if (i < columns.length - 1) {
                    sb2.append(" OR ");
                    sb.append(", ");
                }
            }
        } else {
            sb.append("LOWER(");
            sb.append(columns[0]);
            sb.append(") ASC");
        }
        return new String[]{sb2.toString(), sb.toString()};
    }

    public static String[] remapProjection(final HashMap<String, String> columnMap, final String[] input) {
        int i = 0;
        if (input == null) {
            return new String[0];
        }
        String[] strArr = new String[input.length];
        int length = input.length;
        int i2 = 0;
        while (i < length) {
            String str = input[i];
            String str2 = columnMap.get(str);
            int i3 = i2 + 1;
            if (str2 == null) {
                str2 = "null AS " + str;
            }
            strArr[i2] = str2;
            i++;
            i2 = i3;
        }
        return strArr;
    }

    public static ContentValues remapContentValues(final HashMap<String, String> columnMap, ContentValues values) {
        ContentValues contentValues = new ContentValues();
        for (Map.Entry<String, Object> entry : values.valueSet()) {
            Object value = entry.getValue();
            String key = entry.getKey();
            if (columnMap.containsKey(key)) {
                key = columnMap.get(key);
            }
            if (value instanceof String) {
                contentValues.put(key, (String) value);
            } else if (value instanceof Integer) {
                contentValues.put(key, (Integer) value);
            } else if (value instanceof Long) {
                contentValues.put(key, (Long) value);
            } else {
                throw new UnsupportedOperationException("Unsupported type");
            }
        }
        return contentValues;
    }

    public static int[] getIndexesFromProjection(Cursor cursor, String[] projection) {
        int[] iArr = new int[projection.length];
        if (cursor != null) {
            int length = projection.length;
            int i = 0;
            int i2 = 0;
            while (i < length) {
                iArr[i2] = cursor.getColumnIndexOrThrow(projection[i]);
                i++;
                i2++;
            }
        }
        return iArr;
    }

    public static void beginTransaction(SQLiteDatabase db) {
        db.beginTransactionNonExclusive();
    }

    public static class WhereClause {
        private String[] mArgs;
        private String mClause;

        public WhereClause(String field, Collection<String> values) {
            this(field, values, 50);
        }

        public WhereClause(String field, Collection<String> values, int max) {
            this(field, values, max, false);
        }

        public WhereClause(String field, Collection<String> values, int max, boolean useNotIn) throws IllegalArgumentException {
            if (values.size() > 0 && TextUtils.isEmpty(field)) {
                throw new IllegalArgumentException("field is empty in WhereClause");
            }
            StringBuilder sb = new StringBuilder(field);
            if (useNotIn) {
                sb.append(" not");
            }
            sb.append(" in (");
            this.mArgs = new String[Math.min(values.size(), max)];
            HashSet hashSet = new HashSet();
            int i = 0;
            Iterator<String> it = values.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                String next = it.next();
                if (!TextUtils.isEmpty(next)) {
                    if (i > 0) {
                        sb.append(", ");
                    }
                    sb.append('?');
                    int i2 = i + 1;
                    this.mArgs[i] = next;
                    hashSet.add(next);
                    if (i2 == this.mArgs.length) {
                        i = i2;
                        break;
                    }
                    i = i2;
                }
            }
            String[] strArr = this.mArgs;
            if (i != strArr.length) {
                this.mArgs = (String[]) Arrays.copyOf(strArr, i);
            }
            sb.append(')');
            this.mClause = sb.toString();
            values.removeAll(hashSet);
        }

        public String getClause() {
            return this.mClause;
        }

        public String[] getArgs() {
            String[] strArr = this.mArgs;
            String[] strArr2 = new String[strArr.length];
            System.arraycopy(strArr, 0, strArr2, 0, strArr.length);
            return strArr2;
        }
    }

    public static void reportDbUpgradeException(final float oldVersion, final float newVersion, final String dbClassName, final Exception exception) {
        FlexEvent.Builder builderWithFlexNum2 = FlexEvent.builder("dbUpgradeError").withFlexNum1(Float.valueOf(oldVersion)).withFlexNum2(Float.valueOf(newVersion));
        if (exception != null) {
            builderWithFlexNum2.withFlexStr1(dbClassName);
            builderWithFlexNum2.withFlexStr2(exception.getMessage());
            Throwable cause = exception.getCause();
            if (cause != null) {
                String string = cause.toString();
                if (string.length() > 128) {
                    string = string.substring(0, 128);
                }
                builderWithFlexNum2.withFlexStr3(string);
            }
            CrashDetectionHelper.getInstance().caughtException(exception);
        }
        Log.warning(LOGTAG, "Sending Flex Event %s for oldVersion %s and new version %s", "dbUpgradeError", Float.valueOf(oldVersion), Float.valueOf(newVersion));
        MetricsLogger.sendEvent(builderWithFlexNum2.build());
    }
}
