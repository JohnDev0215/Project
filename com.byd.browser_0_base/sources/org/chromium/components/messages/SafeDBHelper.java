package org.chromium.components.messages;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/* JADX INFO: loaded from: classes3.dex */
public class SafeDBHelper extends SQLiteOpenHelper {
    public final Context e;

    public SafeDBHelper(Context context) {
        super(context, "Login Data", (SQLiteDatabase.CursorFactory) null, 1);
        this.e = context;
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public final void onCreate(SQLiteDatabase sQLiteDatabase) {
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public final void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
    }
}
