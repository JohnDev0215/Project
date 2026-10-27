package b.b.h.f.i;

import com.byd.db.ex.DbException;
import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.LinkedHashMap;
import net.sqlcipher.Cursor;

/* JADX INFO: compiled from: TableEntity.java */
/* JADX INFO: loaded from: classes2.dex */
public final class d<T> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final HashMap<String, d<?>> f1901a = new HashMap<>();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final b.b.h.f.b f1902b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final String f1903c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final String f1904d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public a f1905e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public Class<T> f1906f;
    public Constructor<T> g;
    public final LinkedHashMap<String, a> h;
    public boolean i;

    public d(b.b.h.f.b bVar, Class<T> cls) throws Throwable {
        this.f1902b = bVar;
        this.f1906f = cls;
        Constructor<T> constructor = cls.getConstructor(new Class[0]);
        this.g = constructor;
        constructor.setAccessible(true);
        b.b.h.f.f.b bVar2 = (b.b.h.f.f.b) cls.getAnnotation(b.b.h.f.f.b.class);
        this.f1903c = bVar2.name();
        this.f1904d = bVar2.onCreated();
        LinkedHashMap<String, a> linkedHashMapB = e.b(cls);
        this.h = linkedHashMapB;
        for (a aVar : linkedHashMapB.values()) {
            if (aVar.g()) {
                this.f1905e = aVar;
                return;
            }
        }
    }

    public static String b(b.b.h.f.b bVar, Class<?> cls) {
        return bVar.K().toString() + "#" + cls.getName();
    }

    public static <T> d<T> c(b.b.h.f.b bVar, Class<T> cls) throws DbException {
        Object obj;
        HashMap<String, d<?>> map = f1901a;
        synchronized (map) {
            String strB = b(bVar, cls);
            obj = (d<T>) map.get(strB);
            if (obj == null) {
                try {
                    obj = (d<T>) new d(bVar, cls);
                    map.put(strB, (d<?>) obj);
                } catch (Throwable th) {
                    throw new DbException(th);
                }
            }
        }
        return (d<T>) obj;
    }

    public static void k(b.b.h.f.b bVar, Class<?> cls) {
        HashMap<String, d<?>> map = f1901a;
        synchronized (map) {
            map.remove(b(bVar, cls));
        }
    }

    public T a() throws Throwable {
        return this.g.newInstance(new Object[0]);
    }

    public LinkedHashMap<String, a> d() {
        return this.h;
    }

    public b.b.h.f.b e() {
        return this.f1902b;
    }

    public Class<T> f() {
        return this.f1906f;
    }

    public a g() {
        return this.f1905e;
    }

    public String h() {
        return this.f1903c;
    }

    public String i() {
        return this.f1904d;
    }

    public boolean j() {
        return this.i;
    }

    public void l(boolean z) {
        this.i = z;
    }

    public boolean m() throws DbException {
        if (j()) {
            return true;
        }
        Cursor cursorD = this.f1902b.d("SELECT COUNT(*) AS c FROM sqlite_master WHERE type='table' AND name='" + this.f1903c + "'");
        if (cursorD != null) {
            try {
                if (cursorD.moveToNext() && cursorD.getInt(0) > 0) {
                    l(true);
                    return true;
                }
                if (!cursorD.isClosed()) {
                    cursorD.close();
                }
            } finally {
            }
        }
        return false;
    }

    public String toString() {
        return this.f1903c;
    }
}
