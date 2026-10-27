package androidx.sqlite.db.framework;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.os.CancellationSignal;
import android.text.TextUtils;
import java.io.Closeable;
import p.hz1;
import p.ky0;
import p.ld2;
import p.nh2;
import p.sh2;
import p.th2;
import p.u11;
import p.v03;

/* JADX INFO: loaded from: classes.dex */
public final class b implements nh2 {
    public static final String[] e = {"", " OR ROLLBACK ", " OR ABORT ", " OR FAIL ", " OR IGNORE ", " OR REPLACE "};
    public static final String[] k = new String[0];
    public final SQLiteDatabase c;

    public b(SQLiteDatabase sQLiteDatabase) {
        v03.j(sQLiteDatabase, "delegate");
        this.c = sQLiteDatabase;
    }

    @Override // p.nh2
    public final Cursor F0(sh2 sh2Var) {
        Cursor cursorRawQueryWithFactory = this.c.rawQueryWithFactory(new a(1, new FrameworkSQLiteDatabase$query$cursorFactory$1(sh2Var)), sh2Var.c(), k, null);
        v03.i(cursorRawQueryWithFactory, "delegate.rawQueryWithFac…EMPTY_STRING_ARRAY, null)");
        return cursorRawQueryWithFactory;
    }

    @Override // p.nh2
    public final th2 H(String str) {
        v03.j(str, "sql");
        SQLiteStatement sQLiteStatementCompileStatement = this.c.compileStatement(str);
        v03.i(sQLiteStatementCompileStatement, "delegate.compileStatement(sql)");
        return new ky0(sQLiteStatementCompileStatement);
    }

    @Override // p.nh2
    public final boolean S() {
        return this.c.inTransaction();
    }

    public final void b(String str, Object[] objArr) {
        v03.j(str, "sql");
        v03.j(objArr, "bindArgs");
        this.c.execSQL(str, objArr);
    }

    public final Cursor c(String str) {
        v03.j(str, "query");
        return F0(new ld2(str));
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        this.c.close();
    }

    @Override // p.nh2
    public final boolean e0() {
        SQLiteDatabase sQLiteDatabase = this.c;
        v03.j(sQLiteDatabase, "sQLiteDatabase");
        return sQLiteDatabase.isWriteAheadLoggingEnabled();
    }

    public final int i(String str, int i, ContentValues contentValues, String str2, Object[] objArr) {
        v03.j(str, "table");
        v03.j(contentValues, "values");
        if (contentValues.size() == 0) {
            throw new IllegalArgumentException("Empty values".toString());
        }
        int size = contentValues.size();
        int length = objArr == null ? size : objArr.length + size;
        Object[] objArr2 = new Object[length];
        StringBuilder sb = new StringBuilder("UPDATE ");
        sb.append(e[i]);
        sb.append(str);
        sb.append(" SET ");
        int i2 = 0;
        for (String str3 : contentValues.keySet()) {
            sb.append(i2 > 0 ? "," : "");
            sb.append(str3);
            objArr2[i2] = contentValues.get(str3);
            sb.append("=?");
            i2++;
        }
        if (objArr != null) {
            for (int i3 = size; i3 < length; i3++) {
                objArr2[i3] = objArr[i3 - size];
            }
        }
        if (!TextUtils.isEmpty(str2)) {
            sb.append(" WHERE ");
            sb.append(str2);
        }
        String string = sb.toString();
        v03.i(string, "StringBuilder().apply(builderAction).toString()");
        Closeable closeableH = H(string);
        u11.q((hz1) closeableH, objArr2);
        return ((ky0) closeableH).k.executeUpdateDelete();
    }

    @Override // p.nh2
    public final boolean isOpen() {
        return this.c.isOpen();
    }

    @Override // p.nh2
    public final void j0() {
        this.c.setTransactionSuccessful();
    }

    @Override // p.nh2
    public final void k0() {
        this.c.beginTransactionNonExclusive();
    }

    @Override // p.nh2
    public final void m() {
        this.c.endTransaction();
    }

    @Override // p.nh2
    public final void n() {
        this.c.beginTransaction();
    }

    @Override // p.nh2
    public final Cursor o(sh2 sh2Var, CancellationSignal cancellationSignal) {
        String strC = sh2Var.c();
        String[] strArr = k;
        v03.h(cancellationSignal);
        a aVar = new a(0, sh2Var);
        SQLiteDatabase sQLiteDatabase = this.c;
        v03.j(sQLiteDatabase, "sQLiteDatabase");
        v03.j(strC, "sql");
        Cursor cursorRawQueryWithFactory = sQLiteDatabase.rawQueryWithFactory(aVar, strC, strArr, null, cancellationSignal);
        v03.i(cursorRawQueryWithFactory, "sQLiteDatabase.rawQueryW…ationSignal\n            )");
        return cursorRawQueryWithFactory;
    }

    @Override // p.nh2
    public final void w(String str) {
        v03.j(str, "sql");
        this.c.execSQL(str);
    }
}
