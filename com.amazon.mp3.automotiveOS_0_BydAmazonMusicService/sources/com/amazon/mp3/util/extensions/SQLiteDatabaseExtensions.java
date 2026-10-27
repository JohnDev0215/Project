package com.amazon.mp3.util.extensions;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.amazon.mp3.library.db.ColumnType;
import kotlin.Metadata;
import kotlin.io.CloseableKt;
import kotlin.jvm.JvmName;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: SQLiteDatabase.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\"\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\u001a0\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u00072\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\tH\u0007\u001a\u001a\u0010\n\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0004¨\u0006\u000b"}, d2 = {"addColumn", "", "Landroid/database/sqlite/SQLiteDatabase;", "tableName", "", "columnName", "columnType", "Lcom/amazon/mp3/library/db/ColumnType;", "defaultValue", "", "columnExists", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 2, mv = {1, 6, 0}, xi = 48)
@JvmName(name = "SQLiteDatabaseExtensions")
public final class SQLiteDatabaseExtensions {
    @JvmOverloads
    public static final boolean addColumn(SQLiteDatabase sQLiteDatabase, String tableName, String columnName, ColumnType columnType) {
        Intrinsics.checkNotNullParameter(sQLiteDatabase, "<this>");
        Intrinsics.checkNotNullParameter(tableName, "tableName");
        Intrinsics.checkNotNullParameter(columnName, "columnName");
        Intrinsics.checkNotNullParameter(columnType, "columnType");
        return addColumn$default(sQLiteDatabase, tableName, columnName, columnType, null, 8, null);
    }

    public static /* synthetic */ boolean addColumn$default(SQLiteDatabase sQLiteDatabase, String str, String str2, ColumnType columnType, Object obj, int i, Object obj2) {
        if ((i & 8) != 0) {
            obj = null;
        }
        return addColumn(sQLiteDatabase, str, str2, columnType, obj);
    }

    @JvmOverloads
    public static final boolean addColumn(SQLiteDatabase sQLiteDatabase, String tableName, String columnName, ColumnType columnType, Object obj) {
        String strStringPlus;
        Intrinsics.checkNotNullParameter(sQLiteDatabase, "<this>");
        Intrinsics.checkNotNullParameter(tableName, "tableName");
        Intrinsics.checkNotNullParameter(columnName, "columnName");
        Intrinsics.checkNotNullParameter(columnType, "columnType");
        if (columnExists(sQLiteDatabase, tableName, columnName)) {
            return false;
        }
        String str = "";
        if (obj != null && (strStringPlus = Intrinsics.stringPlus("DEFAULT ", obj)) != null) {
            str = strStringPlus;
        }
        sQLiteDatabase.execSQL("ALTER TABLE " + tableName + " ADD COLUMN " + columnName + ' ' + columnType + ' ' + str);
        return true;
    }

    public static final boolean columnExists(SQLiteDatabase sQLiteDatabase, String tableName, String columnName) {
        Intrinsics.checkNotNullParameter(sQLiteDatabase, "<this>");
        Intrinsics.checkNotNullParameter(tableName, "tableName");
        Intrinsics.checkNotNullParameter(columnName, "columnName");
        Cursor cursorRawQuery = sQLiteDatabase.rawQuery("PRAGMA table_info(" + tableName + ')', null);
        try {
            int columnIndex = cursorRawQuery.getColumnIndex("name");
            while (cursorRawQuery.moveToNext()) {
                if (Intrinsics.areEqual(columnName, cursorRawQuery.getString(columnIndex))) {
                    CloseableKt.closeFinally(cursorRawQuery, null);
                    return true;
                }
            }
            CloseableKt.closeFinally(cursorRawQuery, null);
            return false;
        } finally {
        }
    }
}
