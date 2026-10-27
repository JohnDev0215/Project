package r1;

import android.util.Log;
import java.util.HashMap;
import java.util.Map;
import java.util.NavigableMap;
import java.util.TreeMap;

/* JADX INFO: compiled from: LruArrayPool.java */
/* JADX INFO: loaded from: classes.dex */
public final class i implements r1.b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final g<a, Object> f9339a = new g<>();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final b f9340b = new b();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final Map<Class<?>, NavigableMap<Integer, Integer>> f9341c = new HashMap();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final Map<Class<?>, r1.a<?>> f9342d = new HashMap();

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final int f9343e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private int f9344f;

    /* JADX INFO: compiled from: LruArrayPool.java */
    private static final class a implements l {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final b f9345a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        int f9346b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private Class<?> f9347c;

        a(b bVar) {
            this.f9345a = bVar;
        }

        @Override // r1.l
        public void a() {
            this.f9345a.c(this);
        }

        void b(int i7, Class<?> cls) {
            this.f9346b = i7;
            this.f9347c = cls;
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof a)) {
                return false;
            }
            a aVar = (a) obj;
            return this.f9346b == aVar.f9346b && this.f9347c == aVar.f9347c;
        }

        public int hashCode() {
            int i7 = this.f9346b * 31;
            Class<?> cls = this.f9347c;
            return i7 + (cls != null ? cls.hashCode() : 0);
        }

        public String toString() {
            return "Key{size=" + this.f9346b + "array=" + this.f9347c + '}';
        }
    }

    /* JADX INFO: compiled from: LruArrayPool.java */
    private static final class b extends c<a> {
        b() {
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // r1.c
        /* JADX INFO: renamed from: d, reason: merged with bridge method [inline-methods] */
        public a a() {
            return new a(this);
        }

        a e(int i7, Class<?> cls) {
            a aVarB = b();
            aVarB.b(i7, cls);
            return aVarB;
        }
    }

    public i(int i7) {
        this.f9343e = i7;
    }

    private void f(int i7, Class<?> cls) {
        NavigableMap<Integer, Integer> navigableMapM = m(cls);
        Integer num = (Integer) navigableMapM.get(Integer.valueOf(i7));
        if (num != null) {
            if (num.intValue() == 1) {
                navigableMapM.remove(Integer.valueOf(i7));
                return;
            } else {
                navigableMapM.put(Integer.valueOf(i7), Integer.valueOf(num.intValue() - 1));
                return;
            }
        }
        throw new NullPointerException("Tried to decrement empty size, size: " + i7 + ", this: " + this);
    }

    private void g() {
        h(this.f9343e);
    }

    private void h(int i7) {
        while (this.f9344f > i7) {
            Object objF = this.f9339a.f();
            k2.j.d(objF);
            r1.a aVarI = i(objF);
            this.f9344f -= aVarI.b(objF) * aVarI.c();
            f(aVarI.b(objF), objF.getClass());
            if (Log.isLoggable(aVarI.a(), 2)) {
                Log.v(aVarI.a(), "evicted: " + aVarI.b(objF));
            }
        }
    }

    private <T> r1.a<T> i(T t6) {
        return j(t6.getClass());
    }

    private <T> r1.a<T> j(Class<T> cls) {
        r1.a<T> fVar = (r1.a) this.f9342d.get(cls);
        if (fVar == null) {
            if (cls.equals(int[].class)) {
                fVar = new h();
            } else {
                if (!cls.equals(byte[].class)) {
                    throw new IllegalArgumentException("No array pool found for: " + cls.getSimpleName());
                }
                fVar = new f();
            }
            this.f9342d.put(cls, fVar);
        }
        return fVar;
    }

    private <T> T k(a aVar) {
        return (T) this.f9339a.a(aVar);
    }

    private <T> T l(a aVar, Class<T> cls) {
        r1.a<T> aVarJ = j(cls);
        T t6 = (T) k(aVar);
        if (t6 != null) {
            this.f9344f -= aVarJ.b(t6) * aVarJ.c();
            f(aVarJ.b(t6), cls);
        }
        if (t6 != null) {
            return t6;
        }
        if (Log.isLoggable(aVarJ.a(), 2)) {
            Log.v(aVarJ.a(), "Allocated " + aVar.f9346b + " bytes");
        }
        return aVarJ.newArray(aVar.f9346b);
    }

    private NavigableMap<Integer, Integer> m(Class<?> cls) {
        NavigableMap<Integer, Integer> navigableMap = this.f9341c.get(cls);
        if (navigableMap != null) {
            return navigableMap;
        }
        TreeMap treeMap = new TreeMap();
        this.f9341c.put(cls, treeMap);
        return treeMap;
    }

    private boolean n() {
        int i7 = this.f9344f;
        return i7 == 0 || this.f9343e / i7 >= 2;
    }

    private boolean o(int i7) {
        return i7 <= this.f9343e / 2;
    }

    private boolean p(int i7, Integer num) {
        return num != null && (n() || num.intValue() <= i7 * 8);
    }

    @Override // r1.b
    public synchronized void a(int i7) {
        try {
            if (i7 >= 40) {
                b();
            } else if (i7 >= 20 || i7 == 15) {
                h(this.f9343e / 2);
            }
        } catch (Throwable th) {
            throw th;
        }
    }

    @Override // r1.b
    public synchronized void b() {
        h(0);
    }

    @Override // r1.b
    public synchronized <T> T c(int i7, Class<T> cls) {
        return (T) l(this.f9340b.e(i7, cls), cls);
    }

    @Override // r1.b
    public synchronized <T> void d(T t6) {
        Class<?> cls = t6.getClass();
        r1.a<T> aVarJ = j(cls);
        int iB = aVarJ.b(t6);
        int iC = aVarJ.c() * iB;
        if (o(iC)) {
            a aVarE = this.f9340b.e(iB, cls);
            this.f9339a.d(aVarE, t6);
            NavigableMap<Integer, Integer> navigableMapM = m(cls);
            Integer num = (Integer) navigableMapM.get(Integer.valueOf(aVarE.f9346b));
            Integer numValueOf = Integer.valueOf(aVarE.f9346b);
            int iIntValue = 1;
            if (num != null) {
                iIntValue = 1 + num.intValue();
            }
            navigableMapM.put(numValueOf, Integer.valueOf(iIntValue));
            this.f9344f += iC;
            g();
        }
    }

    @Override // r1.b
    public synchronized <T> T e(int i7, Class<T> cls) {
        Integer numCeilingKey;
        numCeilingKey = m(cls).ceilingKey(Integer.valueOf(i7));
        return (T) l(p(i7, numCeilingKey) ? this.f9340b.e(numCeilingKey.intValue(), cls) : this.f9340b.e(i7, cls), cls);
    }
}
