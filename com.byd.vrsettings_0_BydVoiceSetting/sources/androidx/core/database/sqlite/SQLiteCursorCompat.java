package androidx.core.database.sqlite;

import android.database.sqlite.SQLiteCursor;

/* JADX INFO: loaded from: classes.dex */
public final class SQLiteCursorCompat {
    private SQLiteCursorCompat() {
    }

    public static void setFillWindowForwardOnly(SQLiteCursor sQLiteCursor, boolean z) {
        sQLiteCursor.setFillWindowForwardOnly(z);
    }
}
