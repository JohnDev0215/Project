package com.bumptech.glide.load.engine.x;

import android.util.Log;
import java.util.HashMap;
import java.util.Map;
import java.util.NavigableMap;
import java.util.TreeMap;

/* JADX INFO: compiled from: LruArrayPool.java */
/* JADX INFO: loaded from: classes.dex */
public final class j implements com.bumptech.glide.load.engine.x.b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final h<a, Object> f2857a = new h<>();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final b f2858b = new b();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final Map<Class<?>, NavigableMap<Integer, Integer>> f2859c = new HashMap();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final Map<Class<?>, com.bumptech.glide.load.engine.x.a<?>> f2860d = new HashMap();
    private final int e;
    private int f;

    /* JADX INFO: compiled from: LruArrayPool.java */
    private static final class a implements m {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final b f2861a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        int f2862b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private Class<?> f2863c;

        a(b bVar) {
            this.f2861a = bVar;
        }

        @Override // com.bumptech.glide.load.engine.x.m
        public void a() {
            this.f2861a.c(this);
        }

        void b(int i, Class<?> cls) {
            this.f2862b = i;
            this.f2863c = cls;
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof a)) {
                return false;
            }
            a aVar = (a) obj;
            return this.f2862b == aVar.f2862b && this.f2863c == aVar.f2863c;
        }

        public int hashCode() {
            int i = this.f2862b * 31;
            Class<?> cls = this.f2863c;
            return i + (cls != null ? cls.hashCode() : 0);
        }

        public String toString() {
            return "Key{size=" + this.f2862b + "array=" + this.f2863c + '}';
        }
    }

    /* JADX INFO: compiled from: LruArrayPool.java */
    private static final class b extends d<a> {
        b() {
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.bumptech.glide.load.engine.x.d
        /* JADX INFO: renamed from: d, reason: merged with bridge method [inline-methods] */
        public a a() {
            return new a(this);
        }

        a e(int i, Class<?> cls) {
            a aVarB = b();
            aVarB.b(i, cls);
            return aVarB;
        }
    }

    public j(int i) {
        this.e = i;
    }

    private void e(int i, Class<?> cls) {
        NavigableMap<Integer, Integer> navigableMapL = l(cls);
        Integer num = (Integer) navigableMapL.get(Integer.valueOf(i));
        if (num != null) {
            if (num.intValue() == 1) {
                navigableMapL.remove(Integer.valueOf(i));
                return;
            } else {
                navigableMapL.put(Integer.valueOf(i), Integer.valueOf(num.intValue() - 1));
                return;
            }
        }
        throw new NullPointerException("Tried to decrement empty size, size: " + i + ", this: " + this);
    }

    private void f() {
        g(this.e);
    }

    private void g(int i) {
        while (this.f > i) {
            Object objF = this.f2857a.f();
            com.bumptech.glide.m.j.d(objF);
            com.bumptech.glide.load.engine.x.a aVarH = h(objF);
            this.f -= aVarH.b(objF) * aVarH.c();
            e(aVarH.b(objF), objF.getClass());
            if (Log.isLoggable(aVarH.a(), 2)) {
                Log.v(aVarH.a(), "evicted: " + aVarH.b(objF));
            }
        }
    }

    private <T> com.bumptech.glide.load.engine.x.a<T> h(T t) {
        return i(t.getClass());
    }

    private <T> com.bumptech.glide.load.engine.x.a<T> i(Class<T> cls) {
        com.bumptech.glide.load.engine.x.a<T> gVar = (com.bumptech.glide.load.engine.x.a) this.f2860d.get(cls);
        if (gVar == null) {
            if (cls.equals(int[].class)) {
                gVar = new i();
            } else {
                if (!cls.equals(byte[].class)) {
                    throw new IllegalArgumentException("No array pool found for: " + cls.getSimpleName());
                }
                gVar = new g();
            }
            this.f2860d.put(cls, gVar);
        }
        return gVar;
    }

    private <T> T j(a aVar) {
        return (T) this.f2857a.a(aVar);
    }

    private <T> T k(a aVar, Class<T> cls) {
        com.bumptech.glide.load.engine.x.a<T> aVarI = i(cls);
        T t = (T) j(aVar);
        if (t != null) {
            this.f -= aVarI.b(t) * aVarI.c();
            e(aVarI.b(t), cls);
        }
        if (t != null) {
            return t;
        }
        if (Log.isLoggable(aVarI.a(), 2)) {
            Log.v(aVarI.a(), "Allocated " + aVar.f2862b + " bytes");
        }
        return aVarI.newArray(aVar.f2862b);
    }

    private NavigableMap<Integer, Integer> l(Class<?> cls) {
        NavigableMap<Integer, Integer> navigableMap = this.f2859c.get(cls);
        if (navigableMap != null) {
            return navigableMap;
        }
        TreeMap treeMap = new TreeMap();
        this.f2859c.put(cls, treeMap);
        return treeMap;
    }

    private boolean m() {
        int i = this.f;
        return i == 0 || this.e / i >= 2;
    }

    private boolean n(int i) {
        return i <= this.e / 2;
    }

    private boolean o(int i, Integer num) {
        return num != null && (m() || num.intValue() <= i * 8);
    }

    @Override // com.bumptech.glide.load.engine.x.b
    public synchronized void a(int i) {
        try {
            if (i >= 40) {
                b();
            } else if (i >= 20 || i == 15) {
                g(this.e / 2);
            }
        } catch (Throwable th) {
            throw th;
        }
    }

    @Override // com.bumptech.glide.load.engine.x.b
    public synchronized void b() {
        g(0);
    }

    @Override // com.bumptech.glide.load.engine.x.b
    public synchronized <T> T c(int i, Class<T> cls) {
        return (T) k(this.f2858b.e(i, cls), cls);
    }

    @Override // com.bumptech.glide.load.engine.x.b
    public synchronized <T> T d(int i, Class<T> cls) {
        Integer numCeilingKey;
        numCeilingKey = l(cls).ceilingKey(Integer.valueOf(i));
        return (T) k(o(i, numCeilingKey) ? this.f2858b.e(numCeilingKey.intValue(), cls) : this.f2858b.e(i, cls), cls);
    }

    @Override // com.bumptech.glide.load.engine.x.b
    public synchronized <T> void put(T t) {
        Class<?> cls = t.getClass();
        com.bumptech.glide.load.engine.x.a<T> aVarI = i(cls);
        int iB = aVarI.b(t);
        int iC = aVarI.c() * iB;
        if (n(iC)) {
            a aVarE = this.f2858b.e(iB, cls);
            this.f2857a.d(aVarE, t);
            NavigableMap<Integer, Integer> navigableMapL = l(cls);
            Integer num = (Integer) navigableMapL.get(Integer.valueOf(aVarE.f2862b));
            Integer numValueOf = Integer.valueOf(aVarE.f2862b);
            int iIntValue = 1;
            if (num != null) {
                iIntValue = 1 + num.intValue();
            }
            navigableMapL.put(numValueOf, Integer.valueOf(iIntValue));
            this.f += iC;
            f();
        }
    }
}
