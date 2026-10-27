package com.bumptech.glide.load.n.a0;

import android.util.Log;
import java.util.HashMap;
import java.util.Map;
import java.util.NavigableMap;
import java.util.TreeMap;

/* JADX INFO: loaded from: classes.dex */
public final class j implements com.bumptech.glide.load.n.a0.b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final h<a, Object> f2043a = new h<>();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final b f2044b = new b();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final Map<Class<?>, NavigableMap<Integer, Integer>> f2045c = new HashMap();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final Map<Class<?>, com.bumptech.glide.load.n.a0.a<?>> f2046d = new HashMap();
    private final int e;
    private int f;

    private static final class a implements m {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final b f2047a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        int f2048b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private Class<?> f2049c;

        a(b bVar) {
            this.f2047a = bVar;
        }

        @Override // com.bumptech.glide.load.n.a0.m
        public void a() {
            this.f2047a.c(this);
        }

        void b(int i, Class<?> cls) {
            this.f2048b = i;
            this.f2049c = cls;
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof a)) {
                return false;
            }
            a aVar = (a) obj;
            return this.f2048b == aVar.f2048b && this.f2049c == aVar.f2049c;
        }

        public int hashCode() {
            int i = this.f2048b * 31;
            Class<?> cls = this.f2049c;
            return i + (cls != null ? cls.hashCode() : 0);
        }

        public String toString() {
            return "Key{size=" + this.f2048b + "array=" + this.f2049c + '}';
        }
    }

    private static final class b extends d<a> {
        b() {
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.bumptech.glide.load.n.a0.d
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

    private void f(int i, Class<?> cls) {
        NavigableMap<Integer, Integer> navigableMapM = m(cls);
        Integer num = (Integer) navigableMapM.get(Integer.valueOf(i));
        if (num == null) {
            throw new NullPointerException("Tried to decrement empty size, size: " + i + ", this: " + this);
        }
        int iIntValue = num.intValue();
        Integer numValueOf = Integer.valueOf(i);
        if (iIntValue == 1) {
            navigableMapM.remove(numValueOf);
        } else {
            navigableMapM.put(numValueOf, Integer.valueOf(num.intValue() - 1));
        }
    }

    private void g() {
        h(this.e);
    }

    private void h(int i) {
        while (this.f > i) {
            Object objF = this.f2043a.f();
            com.bumptech.glide.t.j.d(objF);
            com.bumptech.glide.load.n.a0.a aVarI = i(objF);
            this.f -= aVarI.b(objF) * aVarI.c();
            f(aVarI.b(objF), objF.getClass());
            if (Log.isLoggable(aVarI.a(), 2)) {
                Log.v(aVarI.a(), "evicted: " + aVarI.b(objF));
            }
        }
    }

    private <T> com.bumptech.glide.load.n.a0.a<T> i(T t) {
        return j(t.getClass());
    }

    private <T> com.bumptech.glide.load.n.a0.a<T> j(Class<T> cls) {
        com.bumptech.glide.load.n.a0.a<T> gVar = (com.bumptech.glide.load.n.a0.a) this.f2046d.get(cls);
        if (gVar == null) {
            if (cls.equals(int[].class)) {
                gVar = new i();
            } else {
                if (!cls.equals(byte[].class)) {
                    throw new IllegalArgumentException("No array pool found for: " + cls.getSimpleName());
                }
                gVar = new g();
            }
            this.f2046d.put(cls, gVar);
        }
        return gVar;
    }

    private <T> T k(a aVar) {
        return (T) this.f2043a.a(aVar);
    }

    private <T> T l(a aVar, Class<T> cls) {
        com.bumptech.glide.load.n.a0.a<T> aVarJ = j(cls);
        T t = (T) k(aVar);
        if (t != null) {
            this.f -= aVarJ.b(t) * aVarJ.c();
            f(aVarJ.b(t), cls);
        }
        if (t != null) {
            return t;
        }
        if (Log.isLoggable(aVarJ.a(), 2)) {
            Log.v(aVarJ.a(), "Allocated " + aVar.f2048b + " bytes");
        }
        return aVarJ.newArray(aVar.f2048b);
    }

    private NavigableMap<Integer, Integer> m(Class<?> cls) {
        NavigableMap<Integer, Integer> navigableMap = this.f2045c.get(cls);
        if (navigableMap != null) {
            return navigableMap;
        }
        TreeMap treeMap = new TreeMap();
        this.f2045c.put(cls, treeMap);
        return treeMap;
    }

    private boolean n() {
        int i = this.f;
        return i == 0 || this.e / i >= 2;
    }

    private boolean o(int i) {
        return i <= this.e / 2;
    }

    private boolean p(int i, Integer num) {
        return num != null && (n() || num.intValue() <= i * 8);
    }

    @Override // com.bumptech.glide.load.n.a0.b
    public synchronized void a(int i) {
        try {
            if (i >= 40) {
                b();
            } else if (i >= 20 || i == 15) {
                h(this.e / 2);
            }
        } catch (Throwable th) {
            throw th;
        }
    }

    @Override // com.bumptech.glide.load.n.a0.b
    public synchronized void b() {
        h(0);
    }

    @Override // com.bumptech.glide.load.n.a0.b
    public synchronized <T> T c(int i, Class<T> cls) {
        return (T) l(this.f2044b.e(i, cls), cls);
    }

    @Override // com.bumptech.glide.load.n.a0.b
    public synchronized <T> void d(T t) {
        Class<?> cls = t.getClass();
        com.bumptech.glide.load.n.a0.a<T> aVarJ = j(cls);
        int iB = aVarJ.b(t);
        int iC = aVarJ.c() * iB;
        if (o(iC)) {
            a aVarE = this.f2044b.e(iB, cls);
            this.f2043a.d(aVarE, t);
            NavigableMap<Integer, Integer> navigableMapM = m(cls);
            Integer num = (Integer) navigableMapM.get(Integer.valueOf(aVarE.f2048b));
            Integer numValueOf = Integer.valueOf(aVarE.f2048b);
            int iIntValue = 1;
            if (num != null) {
                iIntValue = 1 + num.intValue();
            }
            navigableMapM.put(numValueOf, Integer.valueOf(iIntValue));
            this.f += iC;
            g();
        }
    }

    @Override // com.bumptech.glide.load.n.a0.b
    public synchronized <T> T e(int i, Class<T> cls) {
        Integer numCeilingKey;
        numCeilingKey = m(cls).ceilingKey(Integer.valueOf(i));
        return (T) l(p(i, numCeilingKey) ? this.f2044b.e(numCeilingKey.intValue(), cls) : this.f2044b.e(i, cls), cls);
    }
}
