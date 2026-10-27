package b.b.h.f;

import android.content.Context;
import android.text.TextUtils;
import b.b.h.f.b;
import com.byd.db.ex.DbException;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import net.sqlcipher.Cursor;
import net.sqlcipher.database.SQLiteDatabase;
import net.sqlcipher.database.SQLiteOpenHelper;
import net.sqlcipher.database.SQLiteStatement;

/* JADX INFO: compiled from: DbManagerImpl.java */
/* JADX INFO: loaded from: classes2.dex */
public final class c implements b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static HashMap<b.a, c> f1866a = new HashMap<>();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public SQLiteDatabase f1867b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public b.a f1868c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public boolean f1869d;

    /* JADX INFO: compiled from: DbManagerImpl.java */
    public class a extends SQLiteOpenHelper {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ b.a f1870a;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public a(Context context, String str, SQLiteDatabase.CursorFactory cursorFactory, int i, b.a aVar) {
            super(context, str, cursorFactory, i);
            this.f1870a = aVar;
        }

        @Override // net.sqlcipher.database.SQLiteOpenHelper
        public void onCreate(SQLiteDatabase sQLiteDatabase) {
            b.b.m.c.a.a("DbManagerImpl", "---createDatabase---onCreate------");
        }

        @Override // net.sqlcipher.database.SQLiteOpenHelper
        public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
            b.InterfaceC0077b interfaceC0077bC = this.f1870a.c();
            if (interfaceC0077bC != null) {
                interfaceC0077bC.a(c.this, i, i2);
            }
        }
    }

    public c(b.a aVar) {
        if (aVar == null) {
            throw new IllegalArgumentException("daoConfig may not be null");
        }
        this.f1867b = c(aVar);
        this.f1868c = aVar;
        this.f1869d = aVar.g();
        try {
            b.b.h.f.i.d.c(this, aVar.f());
        } catch (DbException e2) {
            e2.printStackTrace();
        }
    }

    public static synchronized b i(b.a aVar) {
        c cVar;
        if (aVar == null) {
            aVar = new b.a();
        }
        cVar = f1866a.get(aVar);
        if (cVar == null) {
            cVar = new c(aVar);
            f1866a.put(aVar, cVar);
        } else {
            cVar.f1868c = aVar;
        }
        return cVar;
    }

    @Override // b.b.h.f.b
    public <T> e<T> D(Class<T> cls) throws DbException {
        return e.c(b.b.h.f.i.d.c(this, cls));
    }

    @Override // b.b.h.f.b
    public void I(Class<?> cls) throws DbException {
        try {
            e(b.b.h.f.i.d.c(this, cls));
        } catch (Throwable th) {
            throw new DbException(th);
        }
    }

    @Override // b.b.h.f.b
    public b.a K() {
        return this.f1868c;
    }

    @Override // b.b.h.f.b
    public void P(Object obj) throws DbException {
        try {
            a();
            if (obj instanceof List) {
                List list = (List) obj;
                b.b.h.f.i.d<?> dVarC = b.b.h.f.i.d.c(this, list.get(0).getClass());
                e(dVarC);
                Iterator it = list.iterator();
                while (it.hasNext()) {
                    l(dVarC, it.next());
                }
            } else {
                b.b.h.f.i.d<?> dVarC2 = b.b.h.f.i.d.c(this, obj.getClass());
                e(dVarC2);
                l(dVarC2, obj);
            }
            n();
        } finally {
            f();
        }
    }

    public final void a() {
        if (this.f1869d) {
            this.f1867b.beginTransaction();
        }
    }

    @Override // b.b.h.f.b
    public void b(Class<?> cls) throws DbException {
        p(cls, null);
    }

    public final SQLiteDatabase c(b.a aVar) {
        File fileA = aVar.a();
        if (fileA != null && (fileA.exists() || fileA.mkdirs())) {
            return SQLiteDatabase.openOrCreateDatabase(new File(fileA, aVar.b()).getPath(), aVar.e(), (SQLiteDatabase.CursorFactory) null);
        }
        if (b.b.h.b.f1851b.get() != null) {
            return new a(b.b.h.b.f1851b.get().getApplicationContext(), aVar.b(), null, aVar.d(), aVar).getWritableDatabase(aVar.e());
        }
        throw new IllegalStateException("DBEnv need init first");
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        if (f1866a.containsKey(this.f1868c)) {
            f1866a.remove(this.f1868c);
            this.f1867b.close();
        }
    }

    @Override // b.b.h.f.b
    public Cursor d(String str) throws DbException {
        try {
            return this.f1867b.rawQuery(str, (String[]) null);
        } catch (Throwable th) {
            throw new DbException(th);
        }
    }

    public void e(b.b.h.f.i.d<?> dVar) throws DbException {
        if (dVar.m()) {
            return;
        }
        g(b.b.h.f.h.b.a(dVar));
        String strI = dVar.i();
        if (!TextUtils.isEmpty(strI)) {
            h(strI);
        }
        dVar.l(true);
    }

    public final void f() {
        if (this.f1869d) {
            this.f1867b.endTransaction();
        }
    }

    public void g(b.b.h.f.h.a aVar) throws DbException {
        SQLiteStatement sQLiteStatementC = null;
        try {
            sQLiteStatementC = aVar.c(this.f1867b);
            sQLiteStatementC.execute();
            try {
                sQLiteStatementC.releaseReference();
            } catch (Throwable th) {
                b.b.m.c.a.c(b.b.h.b.f1850a, th.getMessage());
            }
        } catch (Throwable th2) {
            try {
                throw new DbException(th2);
            } catch (Throwable th3) {
                if (sQLiteStatementC != null) {
                    try {
                        sQLiteStatementC.releaseReference();
                    } catch (Throwable th4) {
                        b.b.m.c.a.c(b.b.h.b.f1850a, th4.getMessage());
                    }
                }
                throw th3;
            }
        }
    }

    public void h(String str) throws DbException {
        try {
            this.f1867b.execSQL(str);
        } catch (Throwable th) {
            throw new DbException(th);
        }
    }

    public final long j(String str) throws DbException {
        Cursor cursorD = d("SELECT seq FROM sqlite_sequence WHERE name='" + str + "' LIMIT 1");
        if (cursorD != null) {
            try {
                j = cursorD.moveToNext() ? cursorD.getLong(0) : -1L;
            } catch (Throwable th) {
                try {
                    throw new DbException(th);
                } finally {
                    if (!cursorD.isClosed()) {
                        cursorD.close();
                    }
                }
            }
        }
        return j;
    }

    public final boolean k(b.b.h.f.i.d<?> dVar, Object obj) throws DbException {
        b.b.h.f.i.a aVarG = dVar.g();
        if (!aVarG.f()) {
            g(b.b.h.f.h.b.c(dVar, obj));
            return true;
        }
        g(b.b.h.f.h.b.c(dVar, obj));
        long j = j(dVar.h());
        if (j == -1) {
            return false;
        }
        aVarG.h(obj, j);
        return true;
    }

    public final void l(b.b.h.f.i.d<?> dVar, Object obj) throws DbException {
        b.b.h.f.i.a aVarG = dVar.g();
        if (!aVarG.f()) {
            g(b.b.h.f.h.b.d(dVar, obj));
        } else if (aVarG.b(obj) != null) {
            g(b.b.h.f.h.b.e(dVar, obj, new String[0]));
        } else {
            k(dVar, obj);
        }
    }

    @Override // b.b.h.f.b
    public void m(Object obj) throws DbException {
        try {
            a();
            if (obj instanceof List) {
                List list = (List) obj;
                b.b.h.f.i.d<?> dVarC = b.b.h.f.i.d.c(this, list.get(0).getClass());
                e(dVarC);
                Iterator it = list.iterator();
                while (it.hasNext()) {
                    g(b.b.h.f.h.b.c(dVarC, it.next()));
                }
            } else {
                b.b.h.f.i.d<?> dVarC2 = b.b.h.f.i.d.c(this, obj.getClass());
                e(dVarC2);
                g(b.b.h.f.h.b.c(dVarC2, obj));
            }
            n();
        } finally {
            f();
        }
    }

    public final void n() {
        if (this.f1869d) {
            this.f1867b.setTransactionSuccessful();
        }
    }

    @Override // b.b.h.f.b
    public void p(Class<?> cls, b.b.h.f.h.c cVar) throws DbException {
        b.b.h.f.i.d dVarC = b.b.h.f.i.d.c(this, cls);
        if (dVarC.m()) {
            try {
                a();
                g(b.b.h.f.h.b.b(dVarC, cVar));
                n();
            } finally {
                f();
            }
        }
    }

    @Override // b.b.h.f.b
    public void r(Class<?> cls, String str) throws DbException {
        try {
            a();
            b.b.h.f.i.d.k(this, cls);
            b.b.h.f.i.d dVarC = b.b.h.f.i.d.c(this, cls);
            b.b.h.f.i.a aVar = dVarC.d().get(str);
            if (aVar != null) {
                h("ALTER TABLE \"" + dVarC.h() + "\" ADD COLUMN \"" + aVar.d() + "\" " + aVar.a() + " " + aVar.e());
            }
            dVarC.l(true);
            n();
        } finally {
            f();
        }
    }
}
