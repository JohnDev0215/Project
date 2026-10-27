package b.b.h.f.i;

import b.b.h.f.g.f;
import com.byd.db.db.sqlite.ColumnDbType;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import net.sqlcipher.Cursor;

/* JADX INFO: compiled from: ColumnEntity.java */
/* JADX INFO: loaded from: classes2.dex */
public final class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final String f1891a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final String f1892b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final boolean f1893c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final boolean f1894d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final Method f1895e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final Method f1896f;
    public final Field g;
    public final b.b.h.f.g.e h;

    public a(Class<?> cls, Field field, b.b.h.f.f.a aVar) {
        field.setAccessible(true);
        this.g = field;
        this.f1891a = aVar.name();
        this.f1892b = aVar.property();
        boolean zIsId = aVar.isId();
        this.f1893c = zIsId;
        Class<?> type = field.getType();
        this.f1894d = zIsId && aVar.autoGen() && b.f(type);
        this.h = f.a(type);
        Method methodD = b.d(cls, field);
        this.f1895e = methodD;
        if (methodD != null && !methodD.isAccessible()) {
            methodD.setAccessible(true);
        }
        Method methodE = b.e(cls, field);
        this.f1896f = methodE;
        if (methodE == null || methodE.isAccessible()) {
            return;
        }
        methodE.setAccessible(true);
    }

    public ColumnDbType a() {
        return this.h.b();
    }

    public Object b(Object obj) {
        Object objC = c(obj);
        if (this.f1894d && (objC.equals(0L) || objC.equals(0))) {
            return null;
        }
        return this.h.c(objC);
    }

    public Object c(Object obj) {
        if (obj != null) {
            Method method = this.f1895e;
            if (method != null) {
                try {
                    return method.invoke(obj, new Object[0]);
                } catch (Throwable th) {
                    b.b.m.c.a.c(b.b.h.b.f1850a, th.getMessage());
                }
            } else {
                try {
                    return this.g.get(obj);
                } catch (Throwable th2) {
                    b.b.m.c.a.c(b.b.h.b.f1850a, th2.getMessage());
                }
            }
        }
        return null;
    }

    public String d() {
        return this.f1891a;
    }

    public String e() {
        return this.f1892b;
    }

    public boolean f() {
        return this.f1894d;
    }

    public boolean g() {
        return this.f1893c;
    }

    public void h(Object obj, long j) {
        Object objValueOf = Long.valueOf(j);
        if (b.h(this.g.getType())) {
            objValueOf = Integer.valueOf((int) j);
        }
        Method method = this.f1896f;
        if (method != null) {
            try {
                method.invoke(obj, objValueOf);
                return;
            } catch (Throwable th) {
                b.b.m.c.a.c(b.b.h.b.f1850a, th.getMessage());
                return;
            }
        }
        try {
            this.g.set(obj, objValueOf);
        } catch (Throwable th2) {
            b.b.m.c.a.c(b.b.h.b.f1850a, th2.getMessage());
        }
    }

    public void i(Object obj, Cursor cursor, int i) {
        Object objA = this.h.a(cursor, i);
        if (objA == null) {
            return;
        }
        Method method = this.f1896f;
        if (method != null) {
            try {
                method.invoke(obj, objA);
                return;
            } catch (Throwable th) {
                b.b.m.c.a.c(b.b.h.b.f1850a, th.getMessage());
                return;
            }
        }
        try {
            this.g.set(obj, objA);
        } catch (Throwable th2) {
            b.b.m.c.a.c(b.b.h.b.f1850a, th2.getMessage());
        }
    }

    public String toString() {
        return this.f1891a;
    }
}
