package b.b.h.f.g;

import com.byd.db.db.sqlite.ColumnDbType;
import java.util.Date;
import java.util.concurrent.ConcurrentHashMap;

/* JADX INFO: compiled from: ColumnConverterFactory.java */
/* JADX INFO: loaded from: classes2.dex */
public final class f {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final ConcurrentHashMap<String, e> f1884a;

    static {
        ConcurrentHashMap<String, e> concurrentHashMap = new ConcurrentHashMap<>();
        f1884a = concurrentHashMap;
        a aVar = new a();
        concurrentHashMap.put(Boolean.TYPE.getName(), aVar);
        concurrentHashMap.put(Boolean.class.getName(), aVar);
        concurrentHashMap.put(byte[].class.getName(), new b());
        c cVar = new c();
        concurrentHashMap.put(Byte.TYPE.getName(), cVar);
        concurrentHashMap.put(Byte.class.getName(), cVar);
        d dVar = new d();
        concurrentHashMap.put(Character.TYPE.getName(), dVar);
        concurrentHashMap.put(Character.class.getName(), dVar);
        concurrentHashMap.put(Date.class.getName(), new g());
        h hVar = new h();
        concurrentHashMap.put(Double.TYPE.getName(), hVar);
        concurrentHashMap.put(Double.class.getName(), hVar);
        i iVar = new i();
        concurrentHashMap.put(Float.TYPE.getName(), iVar);
        concurrentHashMap.put(Float.class.getName(), iVar);
        j jVar = new j();
        concurrentHashMap.put(Integer.TYPE.getName(), jVar);
        concurrentHashMap.put(Integer.class.getName(), jVar);
        k kVar = new k();
        concurrentHashMap.put(Long.TYPE.getName(), kVar);
        concurrentHashMap.put(Long.class.getName(), kVar);
        l lVar = new l();
        concurrentHashMap.put(Short.TYPE.getName(), lVar);
        concurrentHashMap.put(Short.class.getName(), lVar);
        concurrentHashMap.put(java.sql.Date.class.getName(), new m());
        concurrentHashMap.put(String.class.getName(), new n());
    }

    public static e<?> a(Class<?> cls) {
        e<?> eVar;
        ConcurrentHashMap<String, e> concurrentHashMap = f1884a;
        if (concurrentHashMap.containsKey(cls.getName())) {
            eVar = concurrentHashMap.get(cls.getName());
        } else if (e.class.isAssignableFrom(cls)) {
            try {
                e<?> eVar2 = (e) cls.newInstance();
                if (eVar2 != null) {
                    concurrentHashMap.put(cls.getName(), eVar2);
                }
                eVar = eVar2;
            } catch (Throwable th) {
                b.b.m.c.a.c(b.b.h.b.f1850a, th.getMessage());
                eVar = null;
            }
        } else {
            eVar = null;
        }
        if (eVar != null) {
            return eVar;
        }
        throw new RuntimeException("Database Column Not Support: " + cls.getName() + ", please impl ColumnConverter or use ColumnConverterFactory#registerColumnConverter(...)");
    }

    public static ColumnDbType b(Class<?> cls) {
        return a(cls).b();
    }

    public static boolean c(Class<?> cls) {
        ConcurrentHashMap<String, e> concurrentHashMap = f1884a;
        if (concurrentHashMap.containsKey(cls.getName())) {
            return true;
        }
        if (e.class.isAssignableFrom(cls)) {
            try {
                e eVar = (e) cls.newInstance();
                if (eVar != null) {
                    concurrentHashMap.put(cls.getName(), eVar);
                }
                return eVar == null;
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
        return false;
    }
}
