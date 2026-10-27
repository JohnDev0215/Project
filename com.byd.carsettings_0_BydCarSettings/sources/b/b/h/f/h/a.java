package b.b.h.f.h;

import b.b.h.f.g.f;
import com.byd.db.db.sqlite.ColumnDbType;
import java.util.ArrayList;
import java.util.List;
import net.sqlcipher.database.SQLiteDatabase;
import net.sqlcipher.database.SQLiteStatement;

/* JADX INFO: compiled from: SqlInfo.java */
/* JADX INFO: loaded from: classes2.dex */
public final class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public String f1885a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public List<b.b.h.e.a> f1886b;

    /* JADX INFO: renamed from: b.b.h.f.h.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: SqlInfo.java */
    public static /* synthetic */ class C0078a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final /* synthetic */ int[] f1887a;

        static {
            int[] iArr = new int[ColumnDbType.values().length];
            f1887a = iArr;
            try {
                iArr[ColumnDbType.INTEGER.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f1887a[ColumnDbType.REAL.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f1887a[ColumnDbType.TEXT.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f1887a[ColumnDbType.BLOB.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    public a() {
    }

    public void a(b.b.h.e.a aVar) {
        if (this.f1886b == null) {
            this.f1886b = new ArrayList();
        }
        this.f1886b.add(aVar);
    }

    public void b(List<b.b.h.e.a> list) {
        List<b.b.h.e.a> list2 = this.f1886b;
        if (list2 == null) {
            this.f1886b = list;
        } else {
            list2.addAll(list);
        }
    }

    public SQLiteStatement c(SQLiteDatabase sQLiteDatabase) {
        SQLiteStatement sQLiteStatementCompileStatement = sQLiteDatabase.compileStatement(this.f1885a);
        if (this.f1886b != null) {
            for (int i = 1; i < this.f1886b.size() + 1; i++) {
                Object objA = b.b.h.f.i.b.a(this.f1886b.get(i - 1).f1859b);
                if (objA == null) {
                    sQLiteStatementCompileStatement.bindNull(i);
                } else {
                    int i2 = C0078a.f1887a[f.a(objA.getClass()).b().ordinal()];
                    if (i2 == 1) {
                        sQLiteStatementCompileStatement.bindLong(i, ((Number) objA).longValue());
                    } else if (i2 == 2) {
                        sQLiteStatementCompileStatement.bindDouble(i, ((Number) objA).doubleValue());
                    } else if (i2 == 3) {
                        sQLiteStatementCompileStatement.bindString(i, objA.toString());
                    } else if (i2 != 4) {
                        sQLiteStatementCompileStatement.bindNull(i);
                    } else {
                        sQLiteStatementCompileStatement.bindBlob(i, (byte[]) objA);
                    }
                }
            }
        }
        return sQLiteStatementCompileStatement;
    }

    public void d(String str) {
        this.f1885a = str;
    }

    public a(String str) {
        this.f1885a = str;
    }
}
