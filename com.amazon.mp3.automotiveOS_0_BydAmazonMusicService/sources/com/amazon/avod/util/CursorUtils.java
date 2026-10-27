package com.amazon.avod.util;

import android.database.Cursor;
import com.google.common.base.Preconditions;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public class CursorUtils {
    @Nullable
    public static String getStringFromCursor(@Nonnull Cursor cursor, @Nonnull String str, @Nullable String str2) {
        Preconditions.checkNotNull(cursor, "cursor");
        Preconditions.checkNotNull(str, "columnName");
        int columnIndex = cursor.getColumnIndex(str);
        return columnIndex != -1 ? cursor.getString(columnIndex) : str2;
    }

    @Nullable
    public static String getStringFromCursor(@Nonnull Cursor cursor, @Nonnull String str) {
        Preconditions.checkNotNull(cursor, "cursor");
        Preconditions.checkNotNull(str, "columnName");
        return getStringFromCursor(cursor, str, null);
    }

    public static int getIntFromCursor(@Nonnull Cursor cursor, @Nonnull String str, int i) {
        Preconditions.checkNotNull(cursor, "cursor");
        Preconditions.checkNotNull(str, "columnName");
        int columnIndex = cursor.getColumnIndex(str);
        return columnIndex != -1 ? cursor.getInt(columnIndex) : i;
    }

    public static int getIntFromCursor(@Nonnull Cursor cursor, @Nonnull String str) {
        Preconditions.checkNotNull(cursor, "cursor");
        Preconditions.checkNotNull(str, "columnName");
        return getIntFromCursor(cursor, str, -1);
    }

    public static long getLongFromCursor(@Nonnull Cursor cursor, @Nonnull String str, long j) {
        Preconditions.checkNotNull(cursor, "cursor");
        Preconditions.checkNotNull(str, "columnName");
        int columnIndex = cursor.getColumnIndex(str);
        return columnIndex != -1 ? cursor.getLong(columnIndex) : j;
    }

    public static long getLongFromCursor(@Nonnull Cursor cursor, @Nonnull String str) {
        Preconditions.checkNotNull(cursor, "cursor");
        Preconditions.checkNotNull(str, "columnName");
        return getLongFromCursor(cursor, str, -1L);
    }
}
