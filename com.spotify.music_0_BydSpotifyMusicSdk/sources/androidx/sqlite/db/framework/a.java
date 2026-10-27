package androidx.sqlite.db.framework;

import android.database.Cursor;
import android.database.sqlite.SQLiteCursor;
import android.database.sqlite.SQLiteCursorDriver;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQuery;
import p.hz1;
import p.iz0;
import p.sh2;
import p.v03;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class a implements SQLiteDatabase.CursorFactory {
    public final /* synthetic */ int a;
    public final /* synthetic */ Object b;

    public /* synthetic */ a(int i, Object obj) {
        this.a = i;
        this.b = obj;
    }

    @Override // android.database.sqlite.SQLiteDatabase.CursorFactory
    public final Cursor newCursor(SQLiteDatabase sQLiteDatabase, SQLiteCursorDriver sQLiteCursorDriver, String str, SQLiteQuery sQLiteQuery) {
        int i = this.a;
        Object obj = this.b;
        switch (i) {
            case 0:
                sh2 sh2Var = (sh2) obj;
                v03.j(sh2Var, "$query");
                v03.h(sQLiteQuery);
                sh2Var.b(new hz1(sQLiteQuery));
                break;
            default:
                iz0 iz0Var = (iz0) obj;
                v03.j(iz0Var, "$tmp0");
                sh2 sh2Var2 = ((FrameworkSQLiteDatabase$query$cursorFactory$1) iz0Var).$query;
                v03.h(sQLiteQuery);
                sh2Var2.b(new hz1(sQLiteQuery));
                break;
        }
        return new SQLiteCursor(sQLiteCursorDriver, str, sQLiteQuery);
    }
}
